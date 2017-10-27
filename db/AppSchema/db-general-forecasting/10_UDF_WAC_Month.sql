IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_MONTH_WAC')
  BEGIN
      DROP FUNCTION [DBO].[UDF_MONTH_WAC]
  END

GO

CREATE FUNCTION [dbo].[UDF_MONTH_WAC] (@ITEMID            UDT_ITEM_PRICING READONLY,
                                       @ACTUALPERIODS     DATETIME,
                                       @PROJECTIONPERIODS DATETIME)
RETURNS TABLE
AS
    RETURN
      WITH ITEMPRICING
           AS (SELECT DISTINCT FM.NDC                                                                             ITEM_ID,
                               Dateadd(MM, 0, Cast(Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'
                                                   + Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' AS DATETIME))     STARTING_PERIOD,
                               Dateadd(MM, 1, Cast(Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'
                                                   + Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' AS DATETIME)) - 1 END_PERIOD,
                               FM.PRICE,
                               P.PERIOD_SID


               FROM   FORECASTING_MASTER FM
               JOIN   PERIOD P ON P.YEAR = FM.FORECAST_YEAR
                              AND P.MONTH = FORECAST_MONTH
               JOIN   (SELECT FORECAST_NAME,
                              VERSION
                       FROM   (SELECT TOP 1 FT.FORECAST_NAME,
                                            FT.VERSION,
                                            Row_number()
                                              OVER (
                                                ORDER BY FT.FROM_PERIOD DESC) R_NO
                               FROM   FILE_MANAGEMENT FT
                               WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                                        AND FT.FROM_PERIOD IS NOT NULL )
                                      AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                                             OR FT.TO_PERIOD IS NULL )ORDER BY FT.FROM_PERIOD DESC)A
                       WHERE  R_NO = 1)FT ON FM.FORECAST_NAME = FT.FORECAST_NAME
                                         AND FM.FORECAST_VER IN ( FT.VERSION, LEFT(FT.VERSION, Charindex('.', FT.VERSION) + CASE WHEN Charindex('.', FT.VERSION)=0 THEN Len(FT.VERSION) ELSE -1 END) )
               WHERE  EXISTS (SELECT 1
                              FROM   @ITEMID B
                              WHERE  FM.NDC = B.ITEMID)
                      AND ( Dateadd(MM, 0, Cast(Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'
                                                + Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' AS DATETIME)) >= @ACTUALPERIODS
                            AND ( Dateadd(MM, 0, Cast(Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'
                                                      + Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' AS DATETIME)) <= @PROJECTIONPERIODS ) )),
           ITEMPRICING1
           AS (SELECT ITEM_ID,
                      STARTING_PERIOD,
                      END_PERIOD,
                      PRICE,
                      PERIOD_SID
               FROM   ITEMPRICING
               UNION
               SELECT     DISTINCT ITEM_ID,
                                   PERIOD_DATE                     START_PERIOD,
                                   --START_PERIOD,
                                   Dateadd(mm, 1, PERIOD_DATE) - 1 end_period,
                                   --END_PERIOD,
                                   ITEM_PRICE,
                                   PERIOD_SID
               FROM       (SELECT IM.ITEM_ID,
                                  IP.[START_DATE] START_PERIOD,
                                  IP.END_DATE     END_PERIOD,
                                  IP.ITEM_PRICE
                           FROM   DBO.ITEM_MASTER IM
                           JOIN   DBO.ITEM_PRICING IP ON IP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                           JOIN   DBO.ITEM_PRICING_QUALIFIER IPQ ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                           WHERE  IPQ.PRICING_QUALIFIER = 'WAC') A
               --                JOIN PERIOD P
               --                  ON 
               -----Year(A.START_PERIOD) = [YEAR] AND Month(A.START_PERIOD) = [MONTH]
               RIGHT JOIN PERIOD P ON ( A.START_PERIOD <= P.PERIOD_DATE
                                    AND ( A.END_PERIOD >= P.PERIOD_DATE
                                           OR A.END_PERIOD IS NULL ) )
               WHERE      EXISTS (SELECT 1
                                  FROM   @ITEMID B
                                  WHERE  A.ITEM_ID = B.ITEMID)
                          AND P.PERIOD_DATE >= @ACTUALPERIODS
                          AND P.PERIOD_DATE <= @PROJECTIONPERIODS
                          AND NOT EXISTS (SELECT 1
                                          FROM   ITEMPRICING I
                                          WHERE  I.PERIOD_SID = P.PERIOD_SID))
      ----AND START_PERIOD >= @ACTUALPERIODS
      ----AND END_PERIOD < @STARTFROM
      SELECT ITEM_ID,
             STARTING_PERIOD                                    START_PERIOD,
             END_PERIOD,
             price                                              ITEM_PRICE,
             PERIOD_SID,
             'Q'
             + Cast(Datepart(QQ, STARTING_PERIOD) AS VARCHAR(10))
             + ' '
             + Cast(Datepart(YYYY, STARTING_PERIOD) AS VARCHAR(10)) START_QUARTER,
             'Q'
             + Cast(Datepart(QQ, END_PERIOD) AS VARCHAR(10))
             + ' '
             + Cast(Datepart(YYYY, END_PERIOD) AS VARCHAR(10))      END_QUARTER,
             Datediff(DD, STARTING_PERIOD, END_PERIOD + 1)      DAYS_DIFFERENCE
      FROM   ITEMPRICING1
GO