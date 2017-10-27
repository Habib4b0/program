IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_DEMAND_WAC'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP FUNCTION [DBO].[UDF_DEMAND_WAC]
  END

GO

CREATE FUNCTION [dbo].[UDF_DEMAND_WAC] (@ITEM_DETAILS     UDT_ITEM READONLY,--ITEM LIST
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
					  TOTAL_DEMAND_UNITS ACT_GROSS_UNITS,
					  TOTAL_DEMAND_AMOUNT ACT_GROSS_AMOUNT
               FROM   DEMAND_ACTUAL DM
                      JOIN PERIOD P
                        ON FORECAST_YEAR = P.YEAR
                           AND FORECAST_MONTH = P.MONTH
						    AND EXISTS (SELECT 1
                                       FROM   ITEM_DETAILS ID
                                       WHERE  ID.ITEM_MASTER_SID = DM.ITEM_MASTER_SID)),
           FORECAST
           AS (SELECT ITEM_MASTER_SID,
					  NULLIF(TOTAL_DEMAND_UNITS,0) FOR_GROSS_UNITS,
					  NULLIF(TOTAL_DEMAND_AMOUNT,0) FOR_GROSS_AMOUNT, 
                      FORECAST_YEAR,
                      FORECAST_MONTH,
                      DF.FORECAST_NAME,
                      FORECAST_VER,
                      PERIOD_SID
               FROM   DEMAND_FORECAST DF
                      JOIN PERIOD P
                        ON FORECAST_YEAR = P.YEAR
                           AND FORECAST_MONTH = P.MONTH
               WHERE  FORECAST_NAME = @FORECAST_NAME
                      AND FORECAST_VER IN( @FORECAST_VERSION, FLOOR(@FORECAST_VERSION) )
                      AND PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
					   AND EXISTS (SELECT 1
                                       FROM   ITEM_DETAILS ID
                                       WHERE  ID.ITEM_MASTER_SID = DF.ITEM_MASTER_SID))
      SELECT D.ITEM_MASTER_SID,
             D.PERIOD_SID,
			 ACT_GROSS_UNITS,
			 ACT_GROSS_AMOUNT,
			 FOR_GROSS_UNITS,
			 FOR_GROSS_AMOUNT,
			 AG.ACT_GROSS_AMOUNT / NULLIF( AG.ACT_GROSS_UNITS, 0) AS ACTUAL_PRICE,
             FG.FOR_GROSS_AMOUNT / NULLIF(FG.FOR_GROSS_UNITS, 0) AS FORECAST_PRICE
      FROM   DATA D
             LEFT OUTER JOIN ACTUALS AG
                          ON D.ITEM_MASTER_SID = AG.ITEM_MASTER_SID
                             AND D.PERIOD_SID = AG.PERIOD_SID
             LEFT OUTER JOIN FORECAST FG
                          ON D.ITEM_MASTER_SID = FG.ITEM_MASTER_SID
                             AND D.PERIOD_SID = FG.PERIOD_SID 

GO

