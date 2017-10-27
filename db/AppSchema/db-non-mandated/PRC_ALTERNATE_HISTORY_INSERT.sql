IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_ALTERNATE_HISTORY_INSERT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_ALTERNATE_HISTORY_INSERT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_ALTERNATE_HISTORY_INSERT] (@CURRENT_PROJECTION_DETAILS_SID VARCHAR(8000), -- Note : this list of current ccp_details_sid, though name is CURRENT_PROJECTION_DETAILS_SID
                                                      @ALTERNATE_CCP_DETAIL_SID       VARCHAR(8000),
                                                      @START_DATE                     DATETIME,
                                                      @END_DATE                       DATETIME,
                                                      @USER_ID                        INT,
                                                      @SESSION_ID                     VARCHAR(50))
AS
  BEGIN
      SET NOCOUNT ON

      --DECLARE @ACTUALS_CCP UDT_CCP_DETAILS

      BEGIN TRY

	  DECLARE @CCP_ACTUAL_DETAILS     VARCHAR(200) = Concat('ST_CCP_ACTUAL_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @ALT_HIST_ALLOCATION     VARCHAR(200) = Concat('ST_ALTERNATE_HIST_ALLOCATION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

          --INSERT INTO @ACTUALS_CCP
          --            (CONTRACT_MASTER_SID,
          --             COMPANY_MASTER_SID,
          --             ITEM_MASTER_SID)
          --SELECT CD.CONTRACT_MASTER_SID,
          --       CD.COMPANY_MASTER_SID,
          --       CD.ITEM_MASTER_SID
          --FROM   CCP_DETAILS CD
          --WHERE  CD.CCP_DETAILS_SID IN (SELECT *
          --                              FROM   UDF_SPLITSTRING(@ALTERNATE_CCP_DETAIL_SID, ','))





SELECT token AS CCP_DETAILS_SID, PERIOD_SID into #ALTERNATE_ccp_details
FROM   PERIOD cross join Udf_splitstring(@ALTERNATE_CCP_DETAIL_SID, ',')
WHERE  PERIOD_DATE between  Dateadd(dd, 1, Eomonth(@START_DATE, -1)) and Dateadd(dd, 1, Eomonth(@END_DATE, -1)) 



		--IF Object_id ('TEMPDB..#UDF_ACTUALS_DETAILS') IS NOT NULL
		--  DROP TABLE #UDF_ACTUALS_DETAILS

		--SELECT *
		--INTO   #UDF_ACTUALS_DETAILS
		--FROM   Udf_actuals_details(@ACTUALS_CCP, @END_DATE, @START_DATE) U
		--WHERE  QUANTITY_INCLUSION = 'Y' 


          IF OBJECT_ID('TEMPDB.DBO.#TEMP', 'U') IS NOT NULL
            DROP TABLE #TEMP;

          SELECT CCD.CCP_DETAILS_SID,
                 CONTRACT_MASTER_SID,
                 COMPANY_MASTER_SID,
                 ITEM_MASTER_SID,
                 PERIOD_SID,
                 COALESCE(PROJECTION_SALES, 0) AS PROJECTION_SALES,
                 COALESCE(PROJECTION_UNITS, 0) AS PROJECTION_UNITS
          INTO   #TEMP
          FROM   UDF_SPLITSTRING(@ALTERNATE_CCP_DETAIL_SID, ',') CD
                 INNER JOIN CCP_DETAILS CCD
                         ON CCD.CCP_DETAILS_SID = CD.TOKEN
                 CROSS APPLY (SELECT OP.PROJECTION_DETAILS_SID,
                                     OP.CCP_DETAILS_SID,
                                     SUM(SP.PROJECTION_SALES) PROJECTION_SALES,
                                     SUM(SP.PROJECTION_UNITS) PROJECTION_UNITS,
                                     P.PERIOD_SID
                              FROM   (SELECT TOP 1 PD.PROJECTION_DETAILS_SID,
                                                   PD.CCP_DETAILS_SID
                                      FROM   PROJECTION_MASTER PM
                                             INNER JOIN WORKFLOW_MASTER WM
                                                     ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID
													 AND EXISTS (SELECT HELPER_TABLE_SID
                                                                              FROM   HELPER_TABLE H1
                                                                              WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                                                                     AND DESCRIPTION = 'APPROVED'
																					 AND H1.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID)
                                                        --AND WM.WORKFLOW_STATUS_ID IN (SELECT HELPER_TABLE_SID
                                                        --                             FROM   HELPER_TABLE
                                                        --                             WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                                        --                                    AND DESCRIPTION = 'APPROVED')
                                             INNER JOIN PROJECTION_DETAILS PD
                                                     ON WM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                      WHERE  PD.CCP_DETAILS_SID = CD.TOKEN
                                             AND PM.FORECASTING_TYPE = 'NON MANDATED'
                                      ORDER  BY WM.MODIFIED_DATE DESC) OP
                                     INNER JOIN NM_SALES_PROJECTION SP
                                             ON OP.PROJECTION_DETAILS_SID = SP.PROJECTION_DETAILS_SID
                                     INNER JOIN PERIOD P
                                             ON SP.PERIOD_SID = P.PERIOD_SID
                              WHERE  P.PERIOD_DATE BETWEEN @START_DATE AND @END_DATE
                              GROUP  BY OP.CCP_DETAILS_SID,
                                        P.PERIOD_SID,
                                        OP.PROJECTION_DETAILS_SID) CS

DECLARE @SQL nVARCHAR(max)= ''

        SET @SQL=   CONCAT('INSERT INTO ',@CCP_ACTUAL_DETAILS,'
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS,
                       PROJECTION_SALES,
                       PROJECTION_UNITS)
          SELECT CCP_DETAILS_SID,
                 PERIOD_SID,
                 ACTUAL_SALES,
                 ACTUAL_UNITS,
                 PROJECTION_SALES,
                 PROJECTION_UNITS
          FROM   (SELECT CCP.CCP_DETAILS_SID,
                         CCP.PERIOD_SID,
                         ISNULL(SUM(SALES), 0)            AS ACTUAL_SALES,
                         ISNULL(SUM(QUANTITY), 0)         AS ACTUAL_UNITS,
                         ISNULL(SUM(PROJECTION_SALES), 0) AS PROJECTION_SALES,
                         ISNULL(SUM(PROJECTION_UNITS), 0) AS PROJECTION_UNITS
                  FROM  #ALTERNATE_ccp_details CCP 
                         Left JOIN ACTUALS_DETAILS U
                                 ON CCP.CCP_DETAILS_SID = U.CCP_DETAILS_SID
								 and u.period_sid = ccp.period_sid
								 and  u.QUANTITY_INCLUSION = ''Y''
                         LEFT JOIN #TEMP T
                                ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                   AND T.PERIOD_SID = CCP.PERIOD_SID
                  WHERE  NOT EXISTS (SELECT 1
                                     FROM   ',@CCP_ACTUAL_DETAILS,' SDCAD
                                     WHERE  SDCAD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                            AND SDCAD.PERIOD_SID = CCP.PERIOD_SID)
											
                  GROUP  BY CCP.CCP_DETAILS_SID,
                            CCP.PERIOD_SID) A')

EXEC SP_EXECUTESQL @SQL

DECLARE @SQL1 NVARCHAR(max)= ''

      SET @SQL1 =   CONCAT('INSERT INTO ',@ALT_HIST_ALLOCATION,'
                      (CCP_DETAILS_SID,
                       PERIOD_SID)
          SELECT CCP_DETAILS_SID,
                 PERIOD_SID
          FROM   PERIOD P
                 CROSS JOIN (SELECT TOKEN as CCP_DETAILS_SID FROM   UDF_SPLITSTRING(@CURRENT_PROJECTION_DETAILS_SID, '','')) STHA
          WHERE  NOT EXISTS (SELECT 1
                             FROM   ',@ALT_HIST_ALLOCATION,' SDCAD
                             WHERE  SDCAD.CCP_DETAILS_SID = CCP_DETAILS_SID
                                    AND SDCAD.PERIOD_SID = P.PERIOD_SID)
                 AND PERIOD_DATE BETWEEN @START_DATE AND @END_DATE')

		
				EXEC SP_EXECUTESQL @SQL1,N'@CURRENT_PROJECTION_DETAILS_SID VARCHAR(8000), @START_DATE DATETIME, @END_DATE DATETIME',@CURRENT_PROJECTION_DETAILS_SID = @CURRENT_PROJECTION_DETAILS_SID,
				@START_DATE= @START_DATE,
				@END_DATE  = @END_DATE 
				 
      END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC USPERRORCOLLECTOR

          SELECT @ERRORMESSAGE = ERROR_MESSAGE(),
                 @ERRORSEVERITY = ERROR_SEVERITY(),
                 @ERRORSTATE = ERROR_STATE(),
                 @ERRORPROCEDURE = ERROR_PROCEDURE(),
                 @ERRORLINE = ERROR_LINE(),
                 @ERRORNUMBER = ERROR_NUMBER()

          RAISERROR ( @ERRORMESSAGE,-- MESSAGE TEXT.
                      @ERRORSEVERITY,-- SEVERITY.
                      @ERRORSTATE,-- STATE.
                      @ERRORPROCEDURE,-- PROCEDURE_NAME.
                      @ERRORNUMBER,-- ERRORNUMBER
                      @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END 