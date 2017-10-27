IF EXISTS(SELECT 1 
          FROM   INFORMATION_SCHEMA.ROUTINES 
          WHERE  ROUTINE_NAME = 'PRC_APPROVED_CCP_DETAILS' 
                 AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].[PRC_APPROVED_CCP_DETAILS] 
  END 

GO

CREATE PROCEDURE [dbo].[PRC_APPROVED_CCP_DETAILS] (@PROJECTION_MASTER_SID INT,
                                         @FORECASTING_TYPE      VARCHAR(50),
										 @USER_ID               INT,
										 @SESSION_ID             VARCHAR(50))
AS 
  BEGIN 
      SET NOCOUNT ON 


       BEGIN TRY 
   DECLARE @CCP_HIERARCHY VARCHAR(150)=Concat('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID,'_',replace(convert(varchar(50),getdate(),2),'.','')),
           @D_SQL         NVARCHAR(MAX)

     SET @D_SQL=CONCAT(' ;WITH CTE
     AS (SELECT PD.PROJECTION_MASTER_SID,
                PD.CCP_DETAILS_SID,
                RN=ROW_NUMBER()
                     OVER(
                       PARTITION BY CCP_DETAILS_SID
                       ORDER BY wm.MODIFIED_DATE DESC),
                PD.PROJECTION_DETAILS_SID
         FROM   WORKFLOW_MASTER WM
                INNER JOIN HELPER_TABLE HT
                        ON WM.WORKFLOW_STATUS_ID = HT.HELPER_TABLE_SID
                INNER JOIN PROJECTION_DETAILS PD
                        ON PD.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID
         WHERE  HT.DESCRIPTION = ''APPROVED''
		  AND EXISTS (SELECT 1
                            FROM   ',@CCP_HIERARCHY, ' PD1 WHERE PD1.CCP_DETAILS_SID = PD.CCP_DETAILS_SID)
                AND EXISTS (SELECT 1
                            FROM   PROJECTION_MASTER PM
                            WHERE  PM.PROJECTION_MASTER_SID =',@PROJECTION_MASTER_SID ,' 
                                   AND FORECASTING_TYPE =''', @FORECASTING_TYPE,''')
               )
SELECT PROJECTION_MASTER_SID,
       PROJECTION_DETAILS_SID,
       CCP_DETAILS_SID
FROM   CTE
WHERE  RN = 1 ')

EXEC sp_executesql @D_SQL
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

