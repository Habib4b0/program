IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_GTS')
  BEGIN
      DROP FUNCTION [DBO].[UDF_GTS]
  END

GO

CREATE FUNCTION [dbo].[UDF_GTS] (@ITEM_DETAILS [DBO].[UDT_ITEM_PRICING] READONLY,
                                 @COMPANY_ID   VARCHAR(50),
                                 @FROM_DATE    DATETIME,
                                 @TO_DATE      DATETIME)
RETURNS @GTS TABLE(
  ITEM_MASTER_SID INT,
  [MONTH]         TINYINT,
  [YEAR]          SMALLINT,
  GTS_SALES       NUMERIC(22, 6),
  GTS_UNITS       NUMERIC(22, 6),
  WAC_PRICE AS GTS_SALES/NULLIF(GTS_UNITS, 0),
  [SOURCE]        BIT -- GL_BALANCE_MASTER (0) / FORECASTING_MASTER(1) 
)
AS
  BEGIN

      ;WITH ITEM_DETAILS
           AS (SELECT GC.COMPANY_COST_CENTER,
                      GC.COMPANY_CODE,
                      ITEM_ID,
                      GC.NDC8,
                      ITEM_MASTER_SID
               FROM   GL_COST_CENTER_MASTER GC
                      INNER JOIN COMPANY_MASTER CM
                              ON GC.COMPANY_CODE = CM.COMPANY_ID
                                 AND COMPANY_CODE = @COMPANY_ID
                      INNER JOIN ITEM_MASTER IM
                              ON GC.NDC8 = IM.NDC8
               WHERE  EXISTS (SELECT 1
                              FROM   @ITEM_DETAILS ID
                              WHERE  ID.ITEMID = IM.ITEM_ID)),
           ACTUAL_GTS
           AS (SELECT ITEM_MASTER_SID,
                      [MONTH],
                      [YEAR],
                      MAX(CASE
                                       WHEN LEDGER_TYPE <> 'AU' THEN 0-BALANCE
                                     END) AS GTS_SALES,
                      MAX(CASE
                                      WHEN LEDGER_TYPE = 'AU' THEN 0-BALANCE
                                    END) AS GTS_UNITS
               FROM   (SELECT LEFT(ACCOUNT_NO, CHARINDEX('.', ACCOUNT_NO) - 1) AS LEDGER_TYPE, 
                              SUBSTRING(ACCOUNT_NO, P1.POS + 1, P2.POS - P1.POS - 1) AS COMPANY_COST_CENTER, 
                              SUBSTRING(ACCOUNT_NO, P2.POS + 1, P3.POS - P2.POS - 1) AS [OBJECT],
                              SUBSTRING(ACCOUNT_NO, P3.POS + 1, P4.POS - P3.POS - 1) AS SUBSIDARY,
                              RIGHT(ACCOUNT_NO, CHARINDEX('.', REVERSE(ACCOUNT_NO)) - 1) AS SUBLEDGER,
                              CONVERT(VARCHAR(3), PERIOD) AS [MONTH],
                              '20' + CONVERT(VARCHAR(4), [YEAR]) AS [YEAR],
                              BALANCE,
                              ROW_NUMBER()
                                   OVER(
                                     PARTITION BY ACCOUNT_NO, PERIOD, [YEAR]
                                     ORDER BY UPLOAD_DATE DESC) AS RN
                       FROM   GL_BALANCE_MASTER
                              CROSS APPLY (VALUES (CHARINDEX('.', ACCOUNT_NO))) AS P1(POS)
                              CROSS APPLY (VALUES (CHARINDEX('.', ACCOUNT_NO, P1.POS + 1))) AS P2(POS)
                              CROSS APPLY (VALUES (CHARINDEX('.', ACCOUNT_NO, P2.POS + 1))) AS P3(POS)
                              CROSS APPLY (VALUES (CHARINDEX('.', ACCOUNT_NO, P3.POS + 1))) AS P4(POS)) FD
                      INNER JOIN ITEM_DETAILS ID
                              ON FD.COMPANY_COST_CENTER = ID.COMPANY_COST_CENTER
                                 AND ID.NDC8 = FD.SUBLEDGER
               WHERE  RN = 1
                      AND SUBSIDARY IN( '', '1', '011' )
                      AND [OBJECT] = '4100'
                      AND CONVERT(DATETIME, [YEAR] + '-' + [MONTH] + '-01') BETWEEN @FROM_DATE AND @TO_DATE
               GROUP  BY ITEM_MASTER_SID,
                         [MONTH],
                         [YEAR]),
           FORECAST_GTS
           AS (SELECT ITEM_MASTER_SID,
                      FORECAST_MONTH,
                      FORECAST_YEAR,
                      DOLLARS AS GTS_SALES,
                      UNITS AS GTS_UNITS
               FROM   FORECASTING_MASTER FM
                      JOIN (SELECT TOP 1 FT.FORECAST_NAME,
                                         FT.[VERSION]
                            FROM   FILE_MANAGEMENT FT
                            WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE())
                                     AND FT.FROM_PERIOD IS NOT NULL )
                                   AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE())
                                          OR FT.TO_PERIOD IS NULL )
                            ORDER  BY FT.FROM_PERIOD DESC) F
                        ON FM.FORECAST_NAME = F.FORECAST_NAME
                           AND FM.FORECAST_VER IN ( F.[VERSION], FLOOR(F.[VERSION]) )
                      INNER JOIN ITEM_DETAILS ID
                              ON ID.ITEM_ID = FM.NDC
               WHERE  CONVERT(DATETIME, CONVERT(VARCHAR(5), FORECAST_YEAR) + '-'
                                        + CONVERT(VARCHAR(3), FORECAST_MONTH) + '-01') BETWEEN @FROM_DATE AND @TO_DATE)
      INSERT INTO @GTS
                  (ITEM_MASTER_SID,
                   [MONTH],
                   [YEAR],
                   GTS_SALES,
                   GTS_UNITS,
                   [SOURCE])
      SELECT ITEM_MASTER_SID,
             [MONTH],
             [YEAR],
             GTS_SALES,
             GTS_UNITS,
             0
      FROM   ACTUAL_GTS
      UNION ALL
      SELECT ITEM_MASTER_SID,
             FORECAST_MONTH,
             FORECAST_YEAR,
             GTS_SALES,
             GTS_UNITS,
             1
      FROM   FORECAST_GTS

      RETURN
  END 

  GO