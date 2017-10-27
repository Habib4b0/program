IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_GTS_DETAILS')
  BEGIN
      DROP FUNCTION [DBO].[UDF_GTS_DETAILS]
  END

GO

CREATE  FUNCTION [dbo].[UDF_GTS_DETAILS] (@ACTUAL_GTS      UDT_ACTUAL_GTS_ITEM READONLY,
                                 --@COMPANY_ID   VARCHAR(50),
                                 @FROM_DATE    DATETIME,
                                 @TO_DATE      DATETIME)
RETURNS @GTS TABLE(
  ITEM_ID  VARCHAR(50),
  FORECAST_YEAR             INT,
  FORECAST_MONTH            INT,
  FORECAST_QUARTER                INT,
  START_PERIOD           DATETIME,
  END_PERIOD             DATETIME,
  START_QUARTER AS 'Q'+Cast(Datepart(QQ, START_PERIOD) AS VARCHAR(10))+' '+Cast(Year(START_PERIOD) AS VARCHAR(10)),
  SALES       NUMERIC(22, 6),
  UNITS       NUMERIC(22, 6),
  WAC_PRICE AS SALES/NULLIF(UNITS, 0),
  [SOURCE]        BIT -- GL_BALANCE_MASTER (0) / FORECASTING_MASTER(1) 
)
AS
  BEGIN
;WITH ITEM_DETAILS
     AS (SELECT ITEMID AS ITEM_ID,
                NDC8,
				--QUARTER_VAL,
                COMPANY_COST_CENTER
         FROM   @ACTUAL_GTS),
     ACTUAL_GTS
     AS (SELECT ITEM_ID,
                [MONTH],
                [YEAR],
                Datepart(QQ, START_PERIOD)AS[QUARTER],
                START_PERIOD,
                END_PERIOD,
                Max(CASE
                                 WHEN LEDGER_TYPE <> 'AU' THEN 0-BALANCE -- To invert the sign
                               END) AS GTS_SALES,
                Max(CASE
                                WHEN LEDGER_TYPE = 'AU' THEN 0-BALANCE -- To invert the sign
                              END) AS GTS_UNITS
         FROM   (SELECT LEFT(ACCOUNT_NO, Charindex('.', ACCOUNT_NO) - 1) AS LEDGER_TYPE,
                        Substring(ACCOUNT_NO, P1.POS + 1, P2.POS - P1.POS - 1) AS COMPANY_COST_CENTER,
                        Substring(ACCOUNT_NO, P2.POS + 1, P3.POS - P2.POS - 1) AS [OBJECT],
                        Substring(ACCOUNT_NO, P3.POS + 1, P4.POS - P3.POS - 1) AS SUBSIDARY,
                        RIGHT(ACCOUNT_NO, Charindex('.', Reverse(ACCOUNT_NO)) - 1) AS SUBLEDGER,
                        CONVERT(VARCHAR(3), PERIOD) AS [MONTH],
                        '20' + CONVERT(VARCHAR(4), [YEAR]) AS [YEAR],
                        Dateadd(MM, 0, Cast('20'
                                                         + CONVERT(VARCHAR(5), CONVERT(VARCHAR(4), [YEAR]))
                                                         + '-' + CONVERT(VARCHAR(2), PERIOD) + '-01' AS DATETIME)) AS START_PERIOD,
                        Dateadd(MM, 1, Cast('20'
                                                       + CONVERT(VARCHAR(5), CONVERT(VARCHAR(4),[YEAR]))
                                                       + '-' + CONVERT(VARCHAR(2), PERIOD) + '-01' AS DATETIME)) - 1 AS END_PERIOD,
                        BALANCE,
                        Row_number()
                             OVER(
                               PARTITION BY ACCOUNT_NO, PERIOD, [YEAR]
                               ORDER BY UPLOAD_DATE DESC) AS RN
                 FROM   GL_BALANCE_MASTER
                        CROSS APPLY (VALUES (Charindex('.', ACCOUNT_NO))) AS P1(POS)
                        CROSS APPLY (VALUES (Charindex('.', ACCOUNT_NO, P1.POS + 1))) AS P2(POS)
                        CROSS APPLY (VALUES (Charindex('.', ACCOUNT_NO, P2.POS + 1))) AS P3(POS)
                        CROSS APPLY (VALUES (Charindex('.', ACCOUNT_NO, P3.POS + 1))) AS P4(POS)) FD
                INNER JOIN ITEM_DETAILS ID
                        ON FD.COMPANY_COST_CENTER = ID.COMPANY_COST_CENTER
                           AND ID.NDC8 = FD.SUBLEDGER
         WHERE  RN = 1
                AND SUBSIDARY IN( '', '1', '011' )
                AND [OBJECT] = '4100'
                AND CONVERT(DATETIME, [YEAR] + '-' + [MONTH] + '-01') BETWEEN @FROM_DATE AND @TO_DATE
         GROUP  BY ITEM_ID,
                   [MONTH],
                   [YEAR],
                   Datepart(QQ, START_PERIOD),
				   START_PERIOD,
                END_PERIOD),
     FORECAST_GTS
     AS (SELECT ITEM_ID,
                FORECAST_MONTH,
                FORECAST_YEAR,
                CASE
                  WHEN FORECAST_MONTH IN ( 1, 2, 3 ) THEN 1
                  WHEN FORECAST_MONTH IN ( 4, 5, 6 ) THEN 2
                  WHEN FORECAST_MONTH IN ( 7, 8, 9 ) THEN 3
                  WHEN FORECAST_MONTH IN ( 10, 11, 12 ) THEN 4
                END                                           AS FORECAST_QUARTER,
                Dateadd(MM, 0, Cast(Cast(FM.FORECAST_YEAR AS VARCHAR(4)) + '-'
                                    + Cast(FM.FORECAST_MONTH AS VARCHAR(2))
                                    + '-01' AS DATETIME))     START_PERIOD,
                Dateadd(MM, 1, Cast(Cast(FM.FORECAST_YEAR AS VARCHAR(4)) + '-'
                                    + Cast(FM.FORECAST_MONTH AS VARCHAR(2))
                                    + '-01' AS DATETIME)) - 1 END_PERIOD,
                DOLLARS AS GTS_SALES,
                UNITS AS GTS_UNITS
         FROM   FORECASTING_MASTER FM
                JOIN (SELECT TOP 1 FT.FORECAST_NAME,
                                   FT.[VERSION]
                      FROM   FILE_MANAGEMENT FT
                      WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                               AND FT.FROM_PERIOD IS NOT NULL )
                             AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                                    OR FT.TO_PERIOD IS NULL )
                      ORDER  BY FT.FROM_PERIOD DESC) F
                  ON FM.FORECAST_NAME = F.FORECAST_NAME
                     AND FM.FORECAST_VER IN ( F.[VERSION], Floor(F.[VERSION]) )
                INNER JOIN ITEM_DETAILS ID
                        ON ID.ITEM_ID = FM.NDC
         WHERE  CONVERT(DATETIME, CONVERT(VARCHAR(5), FORECAST_YEAR) + '-'
                                  + CONVERT(VARCHAR(3), FORECAST_MONTH) + '-01') BETWEEN @FROM_DATE AND @TO_DATE)
INSERT INTO @GTS
            (ITEM_ID,
            FORECAST_YEAR    ,
			FORECAST_MONTH  , 
			FORECAST_QUARTER, 
			 START_PERIOD,
			 END_PERIOD,
             SALES,
             UNITS,
             [SOURCE])
SELECT ITEM_ID,
        [YEAR],
       [MONTH],
       [QUARTER],
	   START_PERIOD,
			 END_PERIOD,
       GTS_SALES,
       GTS_UNITS,
       0
FROM   ACTUAL_GTS
UNION ALL
SELECT ITEM_ID,
FORECAST_YEAR,
       FORECAST_MONTH,
       FORECAST_QUARTER,
	   START_PERIOD,
		 END_PERIOD,
       GTS_SALES,
       GTS_UNITS,
       1
FROM   FORECAST_GTS

RETURN
END

go