IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_GTS_WAC')
  BEGIN
      DROP FUNCTION [DBO].[UDF_GTS_WAC]
  END

GO

CREATE FUNCTION [dbo].[UDF_GTS_WAC] (@ITEM_DETAILS     UDT_ITEM READONLY,--ITEM LIST
                                     @START_PERIOD_SID INT,--START PERIOD 
                                     @END_PERIOD_SID   INT,--END PERIOD
                                     @FORECAST_NAME    VARCHAR(50),-- ACTIVE FILE NAME IN FILE MANAGEMENT
                                     @FORECAST_VERSION VARCHAR(15))
RETURNS TABLE
AS
    RETURN
      WITH DATA
           AS (SELECT ITEM_MASTER_SID,
                      PERIOD_SID,
                      [YEAR] AS PERIOD_YEAR,
                      [QUARTER] AS PERIOD_QUARTER,
                      [MONTH] AS PERIOD_MONTH
               FROM   @ITEM_DETAILS
                      CROSS JOIN PERIOD
               WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID),
           ITEM_DETAILS
           AS (SELECT ITEM_ID,
                      ITEM_MASTER_SID
               FROM   ITEM_MASTER IM
               WHERE  EXISTS (SELECT 1
                              FROM   @ITEM_DETAILS ID
                              WHERE  ID.ITEM_MASTER_SID = IM.ITEM_MASTER_SID)),
           ACTUAL_GTS
           AS (SELECT IM.ITEM_MASTER_SID,
                      P.PERIOD_SID,
                      SUM(SM.QUANTITY) AS ACTUAL_GTS_UNITS,
                      SUM(SM.AMOUNT) AS ACTUAL_GTS_SALES
               FROM   CUSTOMER_GTS_ACTUAL SM
                      JOIN ITEM_DETAILS IM
                        ON IM.ITEM_ID = SM.ITEM_ID
                      JOIN PERIOD P 
                        ON P.PERIOD_DATE = DATEADD(MM, DATEDIFF(MM, 0, SM.INVOICE_DATE), 0)
               WHERE  P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
               GROUP  BY IM.ITEM_MASTER_SID,
                         P.PERIOD_SID),
           FORECAST_GTS
           AS (SELECT ITEM_MASTER_SID,
                      PERIOD_SID,
                      DOLLARS AS FORECAST_GTS_SALES,
                      UNITS AS FORECAST_GTS_UNITS
               FROM   FORECASTING_MASTER FM
                      INNER JOIN ITEM_DETAILS ID
                              ON ID.ITEM_ID = FM.NDC
                      INNER JOIN PERIOD P
                              ON P.MONTH = FM.FORECAST_MONTH
                                 AND P.YEAR = FM.FORECAST_YEAR
               WHERE  FORECAST_NAME = @FORECAST_NAME
                      AND FORECAST_VER IN ( @FORECAST_VERSION, FLOOR(@FORECAST_VERSION) )
                      AND PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID)
      SELECT D.ITEM_MASTER_SID,
             D.PERIOD_SID,
             AG.ACTUAL_GTS_SALES,
             AG.ACTUAL_GTS_UNITS,
             FG.FORECAST_GTS_SALES,
             FG.FORECAST_GTS_UNITS,
             ACTUAL_GTS_SALES / NULLIF(ACTUAL_GTS_UNITS, 0) AS ACTUAL_PRICE, 
             FORECAST_GTS_SALES / NULLIF(FORECAST_GTS_UNITS, 0) AS FORECAST_PRICE
      FROM   DATA D
             LEFT OUTER JOIN ACTUAL_GTS AG
                          ON D.ITEM_MASTER_SID = AG.ITEM_MASTER_SID
                             AND D.PERIOD_SID = AG.PERIOD_SID
             LEFT OUTER JOIN FORECAST_GTS FG
                          ON D.ITEM_MASTER_SID = FG.ITEM_MASTER_SID
                             AND D.PERIOD_SID = FG.PERIOD_SID 
GO 
