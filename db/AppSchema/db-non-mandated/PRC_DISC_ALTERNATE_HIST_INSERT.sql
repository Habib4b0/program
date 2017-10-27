IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_DISC_ALTERNATE_HIST_INSERT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_DISC_ALTERNATE_HIST_INSERT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_DISC_ALTERNATE_HIST_INSERT] (@SOURCE_CCP_DETAILS_SID VARCHAR(4000), 
                                                        @PROJECTION_DETAILS_SID VARCHAR(4000), 
                                                        @RS_MODEL_SID           INT, 
                                                        @USER_ID                INT, 
                                                        @SESSION_ID             VARCHAR(50)) 
AS 
  BEGIN 
      SET NOCOUNT ON 

      BEGIN TRY 
          --   @SOURCE_CCP_DETAILS_SID VARCHAR(4000) = '6371'  --SOURCE CCPS 
          --,@PROJECTION_DETAILS_SID VARCHAR(4000) = '1,2,3' --LIST OF TARGET CCPS WITH NO ACTUALS  
          --,@RS_MODEL_SID INT = 10                          --AND THEIR DISCOUNT IDS 
          --,@USER_ID INT = 1 
          --,@SESSION_ID INT = 1 
          -------------------------------------------------------------------------------------------------------------------------- 
          DECLARE @ACTUALS_CCP     UDT_CCP_DETAILS, 
                  @START_DATE      DATETIME = DATEADD(YY, DATEDIFF(YY, 0, GETDATE()) - 3, 0), 
                  @END_DATE        DATETIME = DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0), 
                  @DISC_ALLOCATION VARCHAR(200)= CONCAT('ST_DISC_ALTERNATE_HIST_ALLOCATION_', @user_id, '_', @session_id,'_',replace(convert(varchar(50),getdate(),2),'.','')),
				 @DISC_CCP VARCHAR(200)= CONCAT('ST_DISC_CCP_ACTUAL_DETAILS_', @user_id, '_', @session_id,'_',replace(convert(varchar(50),getdate(),2),'.','')),
				 @SQL      NVARCHAR(MAX),
				 @RS_CONTRACT_SID INT

				 SELECT @RS_CONTRACT_SID = RC.RS_CONTRACT_SID FROM RS_CONTRACT RC WHERE RC.RS_MODEL_SID = @RS_MODEL_SID

          -------------------------------------------------------------------------------------------------------------------------- 
          INSERT INTO @ACTUALS_CCP 
                      (CONTRACT_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       ITEM_MASTER_SID) 
          SELECT CD.CONTRACT_MASTER_SID, 
                 CD.COMPANY_MASTER_SID, 
                 CD.ITEM_MASTER_SID 
          FROM   CCP_DETAILS CD 
          WHERE  EXISTS (SELECT TOKEN 
                                        FROM   UDF_SPLITSTRING(@SOURCE_CCP_DETAILS_SID, ',') A WHERE A.token=CD.CCP_DETAILS_SID) 

          -------------------------------------------------------------------------------------------------------------------------- 
          IF OBJECT_ID('TEMPDB.DBO.#TEMP', 'U') IS NOT NULL 
            DROP TABLE #TEMP; 

          SELECT PROJECTION_DETAILS_SID, 
                 CD.TOKEN                         AS CCP_DETAILS_SID, 
                 PERIOD_SID, 
                 COALESCE(PROJECTION_DISCOUNT, 0) AS PROJECTION_DISCOUNT 
          INTO   #TEMP 
          FROM   UDF_SPLITSTRING(@SOURCE_CCP_DETAILS_SID, ',') CD 
                 CROSS APPLY (SELECT OP.PROJECTION_DETAILS_SID, 
                                     OP.CCP_DETAILS_SID, 
                                     SUM(SP.PROJECTION_SALES) PROJECTION_DISCOUNT, 
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
																							AND WM.WORKFLOW_STATUS_ID=H1.HELPER_TABLE_SID) 
                                             INNER JOIN PROJECTION_DETAILS PD 
                                                     ON WM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID 
                                      WHERE  PD.CCP_DETAILS_SID = CD.TOKEN 
                                             AND PM.FORECASTING_TYPE = 'NON MANDATED' 
                                      ORDER  BY WM.MODIFIED_DATE DESC) OP 
                                     INNER JOIN NM_DISCOUNT_PROJECTION SP 
                                             ON OP.PROJECTION_DETAILS_SID = SP.PROJECTION_DETAILS_SID 
                                     INNER JOIN PERIOD P 
                                             ON SP.PERIOD_SID = P.PERIOD_SID 
                              WHERE  P.PERIOD_DATE BETWEEN @START_DATE AND @END_DATE 
                              GROUP  BY OP.PROJECTION_DETAILS_SID, 
                                        OP.CCP_DETAILS_SID, 
                                        P.PERIOD_SID) CS 

          ------------------------------------------------------------------------------------------------------------------------------------- 
         SET @SQL=CONCAT('INSERT INTO ',@DISC_CCP,' 
                      (CCP_DETAILS_SID, 
                       PERIOD_SID, 
                       ACTUAL_AMOUNT, 
                       PROJECTION_AMOUNT
                      ) 
          SELECT CCP.CCP_DETAILS_SID, 
                 U.PERIOD_SID, 
                 SUM(U.DISCOUNT)            AS ACTUAL_DISCOUNT, 
                 SUM(T.PROJECTION_DISCOUNT) PROJECTION_DISCOUNT
          FROM   UDF_ACTUALS_DETAILS(@ACTUALS_CCP',',''', @END_DATE,''',''', @START_DATE,''') U 
                 INNER JOIN PERIOD P 
                         ON P.PERIOD_SID = U.PERIOD_SID 
                 LEFT JOIN #TEMP T 
                        ON T.PROJECTION_DETAILS_SID = U.PROJECTION_DETAILS_SID 
                           AND T.PERIOD_SID = P.PERIOD_SID 
                 INNER JOIN CCP_DETAILS CCP 
                         ON CCP.COMPANY_MASTER_SID = U.COMPANY_MASTER_SID 
                            AND CCP.CONTRACT_MASTER_SID = U.CONTRACT_MASTER_SID 
                            AND CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID 
          --WHERE QUANTITY_INCLUSION = ''N'' 
          WHERE  NOT EXISTS (SELECT 1 
                             FROM   ',@DISC_CCP,' SDCAD 
                             WHERE  SDCAD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID 
                                    AND SDCAD.PERIOD_SID = P.PERIOD_SID 
                                   ) 
          GROUP  BY CCP.CCP_DETAILS_SID, 
                    U.PERIOD_SID 
          ORDER  BY CCP.CCP_DETAILS_SID, 
                    PERIOD_SID' )


		
EXEC SP_EXECUTESQL @SQL,N'@ACTUALS_CCP UDT_CCP_DETAILS READONLY',
@ACTUALS_CCP=@ACTUALS_CCP

-------------------------------------------------------------------------------------------------------------------------------------- 
        SET @SQL=CONCAT('  INSERT INTO ',@DISC_ALLOCATION,' 
                      (CCP_DETAILS_SID, 
                       RS_MODEL_SID, 
                       PERIOD_SID,
					   RS_CONTRACT_SID) 
          SELECT DISTINCT U.TOKEN, 
                          ',@RS_MODEL_SID,', 
                          P.PERIOD_SID, 
                          ',@RS_CONTRACT_SID,'
          FROM   UDF_SPLITSTRING(''',@PROJECTION_DETAILS_SID,'''',',',''','') U 
                 CROSS JOIN PERIOD P 
          WHERE  P.PERIOD_DATE >= DATEADD(YY, DATEDIFF(YY, 0, GETDATE()) - 3, 0) 
                 AND PERIOD_DATE < DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0) 
                 AND NOT EXISTS (SELECT 1 
                                 FROM   ',@DISC_ALLOCATION,' SDAHA 
                                 WHERE  SDAHA.CCP_DETAILS_SID = U.TOKEN 
                                        AND SDAHA.RS_CONTRACT_SID =', @RS_CONTRACT_SID ,'
                                        AND SDAHA.PERIOD_SID = P.PERIOD_SID 
                                       )') 

EXEC sp_executesql @SQL
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




