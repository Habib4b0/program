IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_PIVOT_PERIOD_RESULT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_PIVOT_PERIOD_RESULT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_PIVOT_PERIOD_RESULT] (@PROJECTION_MASTERS_ID INT,
                                                  @FREQUENCY             VARCHAR(20),
                                                  @VIEW                  VARCHAR(50),
                                                  @USER_ID               INT,
                                                  @SESSION_ID            VARCHAR(50),
                                                  @START_MONTH           INT,
                                                  @START_YEAR            INT,
                                                  @END_MONTH             INT,
                                                  @END_YEAR              INT,
                                                  @START_DATE            DATETIME,
                                                  @END_DATE              DATETIME)
AS
  BEGIN
      SET NOCOUNT ON
      DECLARE @ALT_HIST_ALLOCATION     VARCHAR(200) = Concat('ST_ALTERNATE_HIST_ALLOCATION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
			  @SQL                    NVARCHAR(MAX)

      SET @FREQUENCY = CASE
                         WHEN LEFT(@FREQUENCY, 1) = 'Q' THEN 'QUARTER'
                         WHEN LEFT(@FREQUENCY, 1) = 'M' THEN 'MONTH'
                         WHEN LEFT(@FREQUENCY, 1) = 'S' THEN 'SEMI_ANNUAL'
                         WHEN LEFT(@FREQUENCY, 1) = 'Y' THEN 'YEAR'
                       END

      BEGIN TRY
          IF OBJECT_ID('TEMPDB..#PIVOT_QUERY') IS NOT NULL
            DROP TABLE #PIVOT_QUERY

          CREATE TABLE #PIVOT_QUERY
            (
               CCP_DETAILS_SID		  INT,
               PERIOD_SID             INT,
               ACTUAL_UNITS           NUMERIC(22, 6),
               PROJECTION_UNITS       NUMERIC(22, 6),
               USER_ID                INT,
               SESSION_ID             INT
            )

        SET @SQL=CONCAT( 'INSERT INTO #PIVOT_QUERY
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_UNITS)
          SELECT CCP_DETAILS_SID,
                PERIOD_SID,
                 ACTUAL_UNITS = ISNULL(SUM(ISNULL(ACTUAL_UNITS, 0)), 0)
          FROM    ',@ALT_HIST_ALLOCATION,' 
                           WHERE CHECK_RECORD = 1
     
          GROUP  BY CCP_DETAILS_SID,
                    PERIOD_SID')
EXEC sp_executesql @SQL

        SET @SQL=CONCAT( ' INSERT INTO #PIVOT_QUERY
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       PROJECTION_UNITS)
          SELECT CCP_DETAILS_SID,
                PERIOD_SID,
                 PROJECTION_UNITS = ISNULL(SUM(ISNULL(PROJECTION_UNITS, 0)), 0)
          FROM   ',@ALT_HIST_ALLOCATION,' 
                           WHERE CHECK_RECORD = 1
         
          GROUP  BY CCP_DETAILS_SID,
                    PERIOD_SID')

          IF OBJECT_ID('TEMPDB.DBO.#FINAL', 'U') IS NOT NULL
            DROP TABLE #FINAL;

          SELECT CASE
                   WHEN @VIEW = 'PIVOT' THEN NULL
                   ELSE P.YEAR
                 END                                      AS YEAR,
                 CASE
                   WHEN @VIEW = 'PIVOT' THEN NULL
                   ELSE
                     CASE
                       WHEN @FREQUENCY = 'MONTH' THEN P.MONTH
                       WHEN @FREQUENCY = 'QUARTER' THEN P.QUARTER
                       WHEN @FREQUENCY = 'SEMI_ANNUAL' THEN P.SEMI_ANNUAL
                       ELSE P.YEAR
                     END
                 END                                      AS PERIOD,
                 ISNULL(ISNULL(T.ACTUAL_UNITS, 0), 0)     AS ACTUAL_UNITS,
                 ISNULL(ISNULL(T.PROJECTION_UNITS, 0), 0) AS PROJECTION_UNITS
          INTO   #FINAL
          FROM   #PIVOT_QUERY T
                 LEFT JOIN PERIOD P
                        ON P.PERIOD_SID = T.PERIOD_SID
          ORDER  BY CASE
                      WHEN @VIEW = 'PIVOT' THEN NULL
                      ELSE P.YEAR
                    END,
                    CASE
                      WHEN @VIEW = 'PIVOT' THEN NULL
                      ELSE
                        CASE
                          WHEN @FREQUENCY = 'MONTH' THEN P.MONTH
                          WHEN @FREQUENCY = 'QUARTER' THEN P.QUARTER
                          WHEN @FREQUENCY = 'SEMI_ANNUAL' THEN P.SEMI_ANNUAL
                          ELSE P.YEAR
                        END
                    END

          IF @VIEW = 'PERIOD'
            SELECT ISNULL(ISNULL(SUM(T.ACTUAL_UNITS), 0), 0)     AS ACTUAL_UNITS,
                   ISNULL(ISNULL(SUM(T.PROJECTION_UNITS), 0), 0) AS PROJECTION_UNITS,
                   P.YEAR,
                   CASE
                     WHEN @FREQUENCY = 'MONTH' THEN P.MONTH
                     WHEN @FREQUENCY = 'QUARTER' THEN P.QUARTER
                     WHEN @FREQUENCY = 'SEMI_ANNUAL' THEN P.SEMI_ANNUAL
                     ELSE P.YEAR
                   END                                           PERIOD
            FROM   #PIVOT_QUERY T
                   LEFT JOIN PERIOD P
                          ON P.PERIOD_SID = T.PERIOD_SID
            GROUP  BY P.YEAR,
                      CASE
                        WHEN @FREQUENCY = 'MONTH' THEN P.MONTH
                        WHEN @FREQUENCY = 'QUARTER' THEN P.QUARTER
                        WHEN @FREQUENCY = 'SEMI_ANNUAL' THEN P.SEMI_ANNUAL
                        ELSE P.YEAR
                      END
            ORDER  BY P.YEAR,
                      CASE
                        WHEN @FREQUENCY = 'MONTH' THEN P.MONTH
                        WHEN @FREQUENCY = 'QUARTER' THEN P.QUARTER
                        WHEN @FREQUENCY = 'SEMI_ANNUAL' THEN P.SEMI_ANNUAL
                        ELSE P.YEAR
                      END
          ELSE
            SELECT ISNULL(SUM(ISNULL(T.ACTUAL_UNITS, 0)), 0)     AS ACTUAL_UNITS,
                   ISNULL(SUM(ISNULL(T.PROJECTION_UNITS, 0)), 0) AS PROJECTION_UNITS
            FROM   #PIVOT_QUERY T
                   LEFT JOIN PERIOD P
                          ON P.PERIOD_SID = T.PERIOD_SID
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
