IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_INVENTORY_WAC')
  BEGIN
      DROP FUNCTION [DBO].[UDF_INVENTORY_WAC]
  END

GO

CREATE FUNCTION [dbo].[UDF_INVENTORY_WAC] (@ITEM_DETAILS     UDT_ITEM READONLY,--ITEM LIST
                                          @START_PERIOD_SID INT,--START PERIOD 
                                          @END_PERIOD_SID   INT,--END PERIOD
                                          @FORECAST_NAME    VARCHAR(50),-- ACTIVE FILE NAME IN FILE MANAGEMENT
                                          @FORECAST_VERSION VARCHAR(15))--ACTIVE FILE VERSION IN  FILE MANAGEMENT 
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
           AS (SELECT ITEM_MASTER_SID,
                      ITEM_ID
               FROM   ITEM_MASTER IM
               WHERE  EXISTS (SELECT 1
                              FROM   @ITEM_DETAILS ID
                              WHERE  ID.ITEM_MASTER_SID = IM.ITEM_MASTER_SID)),
           ACTUALS
           AS (SELECT ITEM_MASTER_SID,
                      PERIOD_SID,
                      AMOUNT_WITHDRAWN ACT_AMOUNT_WITHDRAWN,
                      UNITS_WITHDRAWN  ACT_UNITS_WITHDRAWN
               FROM   INVENTORY_WD_ACTUAL_MAS IWA
                      JOIN PERIOD P
                        ON IWA.YEAR = P.YEAR
                           AND IWA.MONTH = P.MONTH
                           AND EXISTS (SELECT 1
                                       FROM   ITEM_DETAILS ID
                                       WHERE  ID.ITEM_MASTER_SID = IWA.ITEM_MASTER_SID)),
           FORECAST
           AS (SELECT ITEM_MASTER_SID,
                      AMOUNT_WITHDRAWN FOR_AMOUNT_WITHDRAWN,
                      UNITS_WITHDRAWN  FOR_UNITS_WITHDRAWN,
                      P.YEAR,
                      P.MONTH,
                      FORECAST_NAME,
                      FORECAST_VER,
                      PERIOD_SID
               FROM   INVENTORY_WD_PROJ_MAS IWP
                      JOIN PERIOD P
                        ON IWP.YEAR = P.YEAR
                           AND IWP.MONTH = P.MONTH
               WHERE  FORECAST_NAME = @FORECAST_NAME
                      AND FORECAST_VER IN( @FORECAST_VERSION, FLOOR(@FORECAST_VERSION) )
                      AND PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                      AND EXISTS (SELECT 1
                                  FROM   ITEM_DETAILS ID
                                  WHERE  ID.ITEM_MASTER_SID = IWP.ITEM_MASTER_SID))
      SELECT D.ITEM_MASTER_SID,
             D.PERIOD_SID,
             AG.ACT_AMOUNT_WITHDRAWN,
             AG.ACT_UNITS_WITHDRAWN,
             FG.FOR_AMOUNT_WITHDRAWN,
             FG.FOR_UNITS_WITHDRAWN,
             ACT_AMOUNT_WITHDRAWN / NULLIF(ACT_UNITS_WITHDRAWN, 0) AS ACTUAL_PRICE,
             FOR_AMOUNT_WITHDRAWN / NULLIF(FOR_UNITS_WITHDRAWN, 0) AS FORECAST_PRICE
      FROM   DATA D
             LEFT OUTER JOIN ACTUALS AG
                          ON D.ITEM_MASTER_SID = AG.ITEM_MASTER_SID
                             AND D.PERIOD_SID = AG.PERIOD_SID
             LEFT OUTER JOIN FORECAST FG
                          ON D.ITEM_MASTER_SID = FG.ITEM_MASTER_SID
                             AND D.PERIOD_SID = FG.PERIOD_SID 


	GO
