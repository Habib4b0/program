IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UspErrorCollector'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [dbo].[UspErrorCollector]
  END

GO


Create PROCEDURE [dbo].[UspErrorCollector]
AS
  BEGIN
      SET NOCOUNT ON
      SET XACT_ABORT ON

      DECLARE @ErrorNumber   VARCHAR(MAX),
              @ErrorState    VARCHAR(MAX),
              @ErrorSeverity VARCHAR(MAX),
              @ErrorLine     VARCHAR(MAX),
              @ErrorProc     VARCHAR(MAX),
              @ErrorMesg     VARCHAR(MAX),
              @vUserName     VARCHAR(MAX),
              @vHostName     VARCHAR(MAX)

      select @ErrorNumber = Error_number(),
             @ErrorState = Error_state(),
             @ErrorSeverity = Error_severity(),
             @ErrorLine = Error_line(),
             @ErrorProc = Error_procedure(),
             @ErrorMesg = Error_message(),
             @vUserName = Suser_sname(),
             @vHostName = Host_name()

      INSERT INTO ErrorLog
                  (ErrorNumber,
                   ErrorState,
                   ErrorSeverity,
                   ErrorLine,
                   ErrorProc,
                   ErrorMsg,
                   UserName,
                   HostName)
      VALUES      (@ErrorNumber,
                   @ErrorState,
                   @ErrorSeverity,
                   @ErrorLine,
                   @ErrorProc,
                   @ErrorMesg,
                   @vUserName,
                   @vHostName)
  END 


GO


