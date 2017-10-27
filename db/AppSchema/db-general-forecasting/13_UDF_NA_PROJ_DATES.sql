IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_NA_PROJ_DATES')
  BEGIN
      DROP FUNCTION [DBO].[UDF_NA_PROJ_DATES]
  END
GO
CREATE FUNCTION [dbo].[UDF_NA_PROJ_DATES](@BUSINESS_PROCESS_TYPE VARCHAR(50))
RETURNS @NA_PROJ_DATES TABLE (
  ACTUAL_START_DATE     DATETIME,
  ACTUAL_END_DATE       DATETIME,
  PROJECTION_START_DATE DATETIME,
  PROJECTION_END_DATE   DATETIME )
AS
  BEGIN
      INSERT INTO @NA_PROJ_DATES
      SELECT TOP 1 Cast(Year(Getdate())-3 AS VARCHAR(4))
                   + '-01-01'                                                  AS ACTUAL_START_DATE,
                   Dateadd(MM, -3, Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0)) AS ACTUAL_END_DATE,
                   CASE
                     WHEN PROCESS_MODE = 1
                         THEN
                       CASE
                         WHEN HT1.DESCRIPTION = 'ANNUAL' THEN Dateadd(MONTH, -12 * HIST_VALUE, Dateadd(YY, Datediff(YY, 0, Getdate()), 0))
                         WHEN HT1.DESCRIPTION = 'MONTH' THEN Dateadd(MONTH, -1 * HIST_VALUE, Dateadd(MM, Datediff(MM, 0, Getdate()), 0))
                         WHEN HT1.DESCRIPTION = 'QUARTER' THEN Dateadd(MONTH, -3 * HIST_VALUE, Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0))
                         WHEN HT1.DESCRIPTION LIKE 'SEMI%ANN%' THEN
                           CASE
                             WHEN Month(Getdate()) < 7 THEN Dateadd(MONTH, -6 * HIST_VALUE, Cast(Cast(Year(Getdate()) AS CHAR(4))
                                                                                                 + '-01-01' AS DATE))
                             ELSE Dateadd(MONTH, -6 * HIST_VALUE, Cast(Cast(Year(Getdate()) AS CHAR(4))
                                                                       + '-06-01' AS DATE))
                           END
                       END
                     ELSE FROM_DATE
                   END                                                         AS PROJECTION_START_DATE,
                   CASE
                     WHEN PROCESS_MODE = 1
                         THEN
                       CASE
                         WHEN HT2.DESCRIPTION = 'ANNUAL' THEN Dateadd(MONTH, 12 * PROJ_VALUE, Dateadd(YY, Datediff(YY, 0, Getdate()) + 1, -1))
                         WHEN HT2.DESCRIPTION = 'MONTH' THEN Dateadd(MONTH, PROJ_VALUE, Dateadd (DD, -1, Dateadd(MM, Datediff(MM, 0, Getdate()) + 1, 0)))
                         WHEN HT2.DESCRIPTION = 'QUARTER' THEN Dateadd(MONTH, 3 * PROJ_VALUE, Dateadd (DD, -1, Dateadd(QQ, Datediff(QQ, 0, Getdate()) + 1, 0)))
                         WHEN HT2.DESCRIPTION LIKE 'SEMI%ANN%' THEN
                           CASE
                             WHEN Month(Getdate()) < 7 THEN Dateadd(MONTH, 6 * PROJ_VALUE, Cast(Cast(Year(Getdate()) AS CHAR(4))
                                                                                                + '-06-30' AS DATE))
                             ELSE Dateadd(MONTH, 6 * PROJ_VALUE, Cast(Cast(Year(Getdate()) AS CHAR(4))
                                                                      + '-12-31' AS DATE))
                           END
                     
                     WHEN   (PROCESS_MODE = 1 AND PROCESS_TYPE=1) THEN (SELECT CONVERT(DATETIME, Datefromparts(Max(FORECAST_YEAR), Max(FORECAST_MONTH), 01))
                           FROM   FORECASTING_MASTER
                           WHERE  FORECAST_NAME IN (SELECT TOP 1 FORECAST_NAME
                                                    FROM   DBO.FILE_MANAGEMENT
                                                    WHERE  FILE_TYPE IN (SELECT HELPER_TABLE_SID
                                                                         FROM   HELPER_TABLE
                                                                         WHERE  DESCRIPTION = 'EX-FACTORY SALES'
                                                                                AND LIST_NAME = 'FILE_TYPE')
                                                    ORDER  BY CREATED_DATE DESC)
                                  AND FORECAST_VER IN (SELECT TOP 1 VERSION
                                                       FROM   DBO.FILE_MANAGEMENT
                                                       WHERE  FILE_TYPE IN (SELECT HELPER_TABLE_SID
                                                                            FROM   HELPER_TABLE
                                                                            WHERE  DESCRIPTION = 'EX-FACTORY SALES'
                                                                                   AND LIST_NAME = 'FILE_TYPE')
                                                       ORDER  BY CREATED_DATE DESC))   END

				ELSE TO_DATE
                   END                                                         AS PROJECTION_END_DATE
      FROM   FORECAST_CONFIG FC
             LEFT JOIN HELPER_TABLE HT
                    ON FC.BUSINESS_PROCESS_TYPE = HT.HELPER_TABLE_SID
             LEFT JOIN HELPER_TABLE HT1
                    ON HT1.HELPER_TABLE_SID = FC.HIST_FREQ
             LEFT JOIN HELPER_TABLE HT2
                    ON HT2.HELPER_TABLE_SID = FC.PROJ_FREQ
      WHERE  HT.DESCRIPTION = CASE
                                WHEN @BUSINESS_PROCESS_TYPE = 'MANDATED' THEN 'GOVERNMENT'
                                WHEN @BUSINESS_PROCESS_TYPE LIKE 'NON%MANDATED' THEN 'COMMERCIAL'
                                ELSE @BUSINESS_PROCESS_TYPE
                              END
      ORDER  BY VERSION_NO DESC

      RETURN
  END

