IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NM_DISCOUNT_REFRESH'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NM_DISCOUNT_REFRESH]
  END

GO

CREATE PROCEDURE [dbo].[PRC_NM_DISCOUNT_REFRESH](@PROJECTION INT,
                                                 @USER_ID    INT,
                                                 @SESSION_ID VARCHAR(50))
AS
  BEGIN
      SET NOCOUNT ON
	   DECLARE @PROJECTION_TABLE    VARCHAR(200) = Concat('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID,'_',replace(convert(varchar(50),getdate(),2),'.','')),
					@SQL    NVARCHAR(MAX),
					@S_PROJECTION_TABLE  VARCHAR(200)=Concat('ST_NM_SALES_PROJECTION_', @user_id, '_', @session_id,'_',replace(convert(varchar(50),getdate(),2),'.',''))
      BEGIN TRY 
	 SET @SQL=CONCAT( ';
          WITH DISCOUNT_CTE
               AS (SELECT CCP_DETAILS_SID,
                          RS_MODEL_SID,
                          REFRESHED_NAME,
                          REFRESHED_VALUE,
                         
                          PERIOD_SID
                   FROM   ',@PROJECTION_TABLE,' SNDP
                   --WHERE  EXISTS (SELECT 1
                   --               FROM   PROJECTION_DETAILS PD
                   --               WHERE  PD.PROJECTION_DETAILS_SID = SNDP.PROJECTION_DETAILS_SID
                   --                      AND PD.PROJECTION_MASTER_SID =', @PROJECTION,')
                          WHERE SNDP.REFRESHED_NAME IS NOT NULL
                         ),
               RESULT_INFO
               AS (SELECT DISTINCT SNSP.PERIOD_SID,
                                   PROJECTION_SALES,
                                   PROJECTION_UNITS,
                                   D.CCP_DETAILS_SID,
                                   RS_MODEL_SID,
                                   REFRESHED_NAME,
                                   REFRESHED_VALUE
                   FROM   ',@S_PROJECTION_TABLE,' SNSP
                          JOIN DISCOUNT_CTE D
                            ON SNSP.CCP_DETAILS_SID = D.CCP_DETAILS_SID
                               AND SNSP.PERIOD_SID = D.PERIOD_SID
                   ),
               RESULT
               AS (SELECT SNDP.CCP_DETAILS_SID,
                          SNDP.RS_MODEL_SID,
                          ISNULL(SNDP.PROJECTION_SALES, 0) AS PROJECTION_SALES_D,
                          ISNULL(SNDP.PROJECTION_RPU, 0)   PROJECTION_RPU,
                          ISNULL(SNDP.PROJECTION_RATE, 0)  PROJECTION_RATE,
                          SNDP.PERIOD_SID,
                          ISNULL(RI.PROJECTION_SALES, 0)   PROJECTION_SALES,
                          ISNULL(RI.PROJECTION_UNITS, 0)   PROJECTION_UNITS,
                          RI.REFRESHED_NAME,
                          RI.REFRESHED_VALUE
                   FROM  ',@PROJECTION_TABLE,'  SNDP
                          INNER JOIN RESULT_INFO RI
                                  ON SNDP.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
                                     AND SNDP.RS_MODEL_SID = RI.RS_MODEL_SID
                                     AND SNDP.PERIOD_SID = RI.PERIOD_SID
									),
               REFRESHED_PROJECTION
               AS (SELECT REBATE_PER_UNIT=CASE
                                            WHEN REFRESHED_NAME = ''AMOUNT'' THEN REFRESHED_VALUE / NULLIF(PROJECTION_UNITS, 0)
                                            WHEN REFRESHED_NAME = ''RATE'' THEN ( (REFRESHED_VALUE/100.0) * PROJECTION_SALES ) / NULLIF(PROJECTION_UNITS, 0)
                                            ELSE REFRESHED_VALUE
                                          END,
                          RATE= CASE
                                  WHEN REFRESHED_NAME = ''AMOUNT'' THEN ( REFRESHED_VALUE / NULLIF(PROJECTION_SALES, 0) ) * 100
                                  WHEN REFRESHED_NAME = ''RPU'' THEN ( ( REFRESHED_VALUE * PROJECTION_UNITS ) / NULLIF(PROJECTION_SALES, 0) ) * 100
                                  ELSE REFRESHED_VALUE
                                END,
                          AMOUNT= CASE
                                    WHEN REFRESHED_NAME = ''RATE'' THEN (REFRESHED_VALUE/100.0) * PROJECTION_SALES
                                    WHEN REFRESHED_NAME = ''RPU'' THEN REFRESHED_VALUE * PROJECTION_UNITS
                                    ELSE REFRESHED_VALUE
                                  END,
                          CCP_DETAILS_SID,
                          RS_MODEL_SID,
                          PERIOD_SID,
                          REFRESHED_NAME
                   FROM   RESULT)
          UPDATE SNDP
          SET    --PROJECTION_RATE = RATE,
                 PROJECTION_SALES = AMOUNT,
                 --PROJECTION_RPU = REBATE_PER_UNIT,
                 REFRESHED_NAME = NULL,
                 REFRESHED_VALUE = NULL
          FROM   ',@PROJECTION_TABLE,'  SNDP
                 INNER JOIN REFRESHED_PROJECTION RP
                         ON SNDP.CCP_DETAILS_SID = RP.CCP_DETAILS_SID
                            AND SNDP.RS_MODEL_SID = RP.RS_MODEL_SID
                            AND SNDP.PERIOD_SID = RP.PERIOD_SID
                            AND SNDP.REFRESHED_NAME = RP.REFRESHED_NAME')

EXEC sp_executesql @sql

      SELECT @SQL    
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

          RAISERROR (@ERRORMESSAGE,-- MESSAGE TEXT.
                     @ERRORSEVERITY,-- SEVERITY.
                     @ERRORSTATE,-- STATE.
                     @ERRORPROCEDURE,-- PROCEDURE_NAME.
                     @ERRORNUMBER,-- ERRORNUMBER
                     @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END
  GO