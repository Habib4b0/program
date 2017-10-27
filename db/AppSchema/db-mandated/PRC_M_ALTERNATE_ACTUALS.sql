IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_M_ALTERNATE_ACTUALS'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_M_ALTERNATE_ACTUALS]
  END

GO

CREATE PROCEDURE [dbo].[PRC_M_ALTERNATE_ACTUALS] (@CONT_HIERARCHY_NO         VARCHAR(50),
                                                  @BRAND_HIERARCHY_NO        VARCHAR(50),
                                                  @ALTER_CONTRACT_MASTER_SID INT,
                                                  @ALTER_BRAND_MASTER_SID    INT,
                                                  @PROJECTION_MASTER_SID     INT,
                                                  @SESSION_ID                INT,
                                                  @USER_ID                   INT)
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          DECLARE @CNT INT

          IF Object_id('TEMPDB..#PROJECTION_DETAILS') IS NOT NULL
            DROP TABLE #PROJECTION_DETAILS

          CREATE TABLE #PROJECTION_DETAILS --ALTERNATE ACTUALS PROJECTION_DETAILS_SID
            (
               PROJECTION_DETAILS_SID INT
            )

          INSERT INTO #PROJECTION_DETAILS
                      (PROJECTION_DETAILS_SID)
          SELECT PD.PROJECTION_DETAILS_SID
          FROM   RELATIONSHIP_LEVEL_DEFINITION RLD
                 JOIN CCP_MAP CM
                   ON RLD.RELATIONSHIP_LEVEL_SID = CM.RELATIONSHIP_LEVEL_SID
                      AND EXISTS (SELECT RELATIONSHIP_LEVEL_SID
                                                         FROM   RELATIONSHIP_LEVEL_DEFINITION T
                                                         WHERE  HIERARCHY_NO LIKE COALESCE(@CONT_HIERARCHY_NO, @BRAND_HIERARCHY_NO)+ '%'
														 AND RLD.RELATIONSHIP_LEVEL_SID=T.RELATIONSHIP_LEVEL_SID)
                 JOIN PROJECTION_DETAILS PD
                   ON CM.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
          WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          DECLARE @ACTUALS_CCP UDT_CCP_DETAILS

          -------------------------------------------------FOR ALTERNATE CONTRACT---------------------------------------
          IF @BRAND_HIERARCHY_NO IS NULL
            BEGIN
                INSERT INTO @ACTUALS_CCP
                            (CONTRACT_MASTER_SID,
                             COMPANY_MASTER_SID,
                             ITEM_MASTER_SID)
                SELECT DISTINCT CONTRACT_MASTER_SID,
                                COMPANY_MASTER_SID,
                                ITEM_MASTER_SID
                FROM   CCP_DETAILS CD
                       JOIN PROJECTION_DETAILS PDS
                         ON CD.CCP_DETAILS_SID = PDS.CCP_DETAILS_SID
                WHERE  EXISTS (SELECT DISTINCT CONTRACT_MASTER_SID --NEW CONTRACT_HOLDER
                                               FROM   CONTRACT_MASTER CM
                                               WHERE  CONTRACT_MASTER_SID = @ALTER_CONTRACT_MASTER_SID
											   AND CM.CONTRACT_MASTER_SID=CD.CONTRACT_MASTER_SID)
                       AND EXISTS (SELECT DISTINCT CCD.ITEM_MASTER_SID --WITH OLD BRAND 
                                               FROM   PROJECTION_DETAILS PD
                                                      JOIN #PROJECTION_DETAILS PD_TEMP
                                                        ON PD.PROJECTION_DETAILS_SID = PD_TEMP.PROJECTION_DETAILS_SID
                                                      JOIN CCP_DETAILS CCD
                                                        ON PD.CCP_DETAILS_SID = CCD.CCP_DETAILS_SID
                                                           AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
														   AND CCD.ITEM_MASTER_SID=CD.ITEM_MASTER_SID)
                       AND EXISTS (SELECT DISTINCT CCD.COMPANY_MASTER_SID
                                                 FROM   PROJECTION_DETAILS PD
                                                        JOIN #PROJECTION_DETAILS PD_TEMP
                                                          ON PD.PROJECTION_DETAILS_SID = PD_TEMP.PROJECTION_DETAILS_SID
                                                        JOIN CCP_DETAILS CCD
                                                          ON PD.CCP_DETAILS_SID = CCD.CCP_DETAILS_SID
                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
															 AND CCD.COMPANY_MASTER_SID=CD.COMPANY_MASTER_SID)
            END
          -------------------------------------------------FOR ALTERNATE BRAND_NAME---------------------------------------
          ELSE
            BEGIN
                INSERT INTO @ACTUALS_CCP
                            (CONTRACT_MASTER_SID,
                             COMPANY_MASTER_SID,
                             ITEM_MASTER_SID)
                SELECT DISTINCT CONTRACT_MASTER_SID,
                                COMPANY_MASTER_SID,
                                ITEM_MASTER_SID
                FROM   CCP_DETAILS CD
                       JOIN PROJECTION_DETAILS PDS
                         ON CD.CCP_DETAILS_SID = PDS.CCP_DETAILS_SID
                WHERE  EXISTS (SELECT DISTINCT ITEM_MASTER_SID --NEW BRAND 
                                           FROM   ITEM_MASTER CM
                                                  JOIN BRAND_MASTER B
                                                    ON CM.BRAND_MASTER_SID = B.BRAND_MASTER_SID
                                           WHERE  B.BRAND_MASTER_SID = @ALTER_BRAND_MASTER_SID
										   AND CM.ITEM_MASTER_SID=CD.ITEM_MASTER_SID)
                       AND EXISTS (SELECT DISTINCT CCD.CONTRACT_MASTER_SID --WITH OLD CONTRACT
                                                   FROM   PROJECTION_DETAILS PD
                                                          JOIN #PROJECTION_DETAILS PD_TEMP
                                                            ON PD.PROJECTION_DETAILS_SID = PD_TEMP.PROJECTION_DETAILS_SID
                                                          JOIN CCP_DETAILS CCD
                                                            ON PD.CCP_DETAILS_SID = CCD.CCP_DETAILS_SID
                                                               AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
															   AND CCD.CONTRACT_MASTER_SID=CD.CONTRACT_MASTER_SID)
            END

          -------------------------------------------FETCHING ACTUALS--------------------------------------------------------
          IF Object_id('TEMPDB..#ACTUALS_RESULT') IS NOT NULL
            DROP TABLE #ACTUALS_RESULT

          CREATE TABLE #ACTUALS_RESULT
            (
               PERIOD_SID INT,
               SM_SALES   NUMERIC(22, 6),
               SM_QTY     NUMERIC(22, 6)
            )

          INSERT INTO #ACTUALS_RESULT
                      (PERIOD_SID,
                       SM_SALES,
                       SM_QTY)
          SELECT PERIOD_SID,
                 Sum(SALES)    AS SM_SALES,
                 Sum(QUANTITY) AS SM_QTY
          FROM   Udf_actuals_details(@ACTUALS_CCP, Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0), Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0))U
          WHERE  QUANTITY_INCLUSION = 'Y'
          GROUP  BY PERIOD_SID

          ------------------------------------------END OF FETCHING ACTUALS--------------------------------------------------
          SELECT @CNT = Count(*) --COUNT OF PROJECTION_DETAILS_SID FOR WHICH ACTUALS = 0
          FROM   #PROJECTION_DETAILS

          --------------------------------------------UPDATE ST_M_ACTUAL_SALES TABLE----------------------------------------- 
          UPDATE NMS
          SET    NMS.ACTUAL_SALES = SM_SALES / NULLIF(@CNT, 0),
                 NMS.ACTUAL_UNITS = SM_QTY / NULLIF(@CNT, 0)
          FROM   ST_M_ACTUAL_SALES NMS
                 JOIN #PROJECTION_DETAILS PD
                   ON NMS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                 JOIN #ACTUALS_RESULT AR
                   ON AR.PERIOD_SID = NMS.PERIOD_SID
          WHERE  NMS.SESSION_ID = @SESSION_ID
                 AND NMS.[USER_ID] = @USER_ID
      ------------------------------------------END OF UPDATE ST_M_ACTUAL_SALES TABLE------------------------------------
      END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC Usperrorcollector

          SELECT @ERRORMESSAGE = Error_message(),
                 @ERRORSEVERITY = Error_severity(),
                 @ERRORSTATE = Error_state(),
                 @ERRORPROCEDURE = Error_procedure(),
                 @ERRORLINE = Error_line(),
                 @ERRORNUMBER = Error_number()

          RAISERROR (@ERRORMESSAGE,-- MESSAGE TEXT.
                     @ERRORSEVERITY,-- SEVERITY.
                     @ERRORSTATE,-- STATE.
                     @ERRORPROCEDURE,-- PROCEDURE_NAME.
                     @ERRORNUMBER,-- ERRORNUMBER
                     @ERRORLINE -- ERRORLINE
          )
      END CATCH
  END --END OF PROCEDURE
GO 
