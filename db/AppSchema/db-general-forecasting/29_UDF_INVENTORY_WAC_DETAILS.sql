IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_INVENTORY_WAC_DETAILS'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP FUNCTION [DBO].[UDF_INVENTORY_WAC_DETAILS]
  END

GO


CREATE FUNCTION [dbo].[UDF_INVENTORY_WAC_DETAILS] (@CUST_ITEM_DETAILS UDT_CUST_ITEM READONLY,--ITEM LIST
                                                  @START_PERIOD_SID  INT,--START PERIOD
                                                  @END_PERIOD_SID    INT,--END PERIOD
                                                  @FORECAST_NAME     VARCHAR(50),-- ACTIVE FILE NAME IN FILE MANAGEMENT
                                                  @FORECAST_VERSION  VARCHAR(15))--ACTIVE FILE VERSION IN  FILE MANAGEMENT
RETURNS TABLE
AS
    RETURN
      WITH DATA
           AS (SELECT ITEM_MASTER_SID,
                      COMPANY_MASTER_SID,
                      PERIOD_SID,
                      PERIOD_YEAR = [YEAR],
                      PERIOD_QUARTER = [QUARTER],
                      PERIOD_MONTH = [MONTH]
               FROM   @CUST_ITEM_DETAILS
                      CROSS JOIN PERIOD
               WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID),
           ACTUALS
           AS (SELECT ID.COMPANY_MASTER_SID,
                              ID.ITEM_MASTER_SID,
                              PERIOD_SID,
                              SUM(AMOUNT_WITHDRAWN) ACT_AMOUNT_WITHDRAWN,
                              SUM(UNITS_WITHDRAWN)  ACT_UNITS_WITHDRAWN
                       FROM   INVENTORY_WD_ACTUAL_DT IWA
                              JOIN PERIOD P
                                ON IWA.YEAR = P.YEAR
                                   AND IWA.MONTH = P.MONTH
                              JOIN @CUST_ITEM_DETAILS ID
                                               ON  ID.ITEM_MASTER_SID = IWA.ITEM_MASTER_SID
                                                      AND ( ID.COMPANY_MASTER_SID = IWA.COMPANY_MASTER_SID
                                                             OR ID.COMPANY_MASTER_SID IS NULL )
               GROUP  BY ID.COMPANY_MASTER_SID,
                         ID.ITEM_MASTER_SID,
                         PERIOD_SID),
           FORECAST
           AS (SELECT ID.COMPANY_MASTER_SID,
                              ID.ITEM_MASTER_SID,
                             SUM(AMOUNT_WITHDRAWN) FOR_AMOUNT_WITHDRAWN,
                              SUM(UNITS_WITHDRAWN)  FOR_UNITS_WITHDRAWN,
                              FORECAST_NAME,
                              FORECAST_VER,
                              PERIOD_SID
                       FROM   INVENTORY_WD_PROJ_DT IWP
                              JOIN PERIOD P
                                ON IWP.YEAR = P.YEAR
                                   AND IWP.MONTH = P.MONTH
								   JOIN @CUST_ITEM_DETAILS ID
                                               ON  ID.ITEM_MASTER_SID = IWP.ITEM_MASTER_SID
                                                      AND ( ID.COMPANY_MASTER_SID = IWP.COMPANY_MASTER_SID
                                                             OR ID.COMPANY_MASTER_SID IS NULL )
                       WHERE  FORECAST_NAME = @FORECAST_NAME
                              AND FORECAST_VER IN( @FORECAST_VERSION, Floor(@FORECAST_VERSION) )
                              AND PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                              
               GROUP  BY ID.COMPANY_MASTER_SID,
                         ID.ITEM_MASTER_SID,
                         FORECAST_NAME,
                         FORECAST_VER,
                         PERIOD_SID)
      SELECT D.COMPANY_MASTER_SID,
             D.ITEM_MASTER_SID,
             D.PERIOD_SID,
             AG.ACT_AMOUNT_WITHDRAWN,
             AG.ACT_UNITS_WITHDRAWN,
             FG.FOR_AMOUNT_WITHDRAWN,
             FG.FOR_UNITS_WITHDRAWN,
             ACTUAL_PRICE = ACT_AMOUNT_WITHDRAWN / NULLIF(ACT_UNITS_WITHDRAWN, 0),
             FORECAST_PRICE = FOR_AMOUNT_WITHDRAWN / NULLIF(FOR_UNITS_WITHDRAWN, 0)
      FROM   DATA D
             LEFT OUTER JOIN ACTUALS AG
                          ON D.ITEM_MASTER_SID = AG.ITEM_MASTER_SID
                             AND (D.COMPANY_MASTER_SID = AG.COMPANY_MASTER_SID OR D.COMPANY_MASTER_SID IS NULL)
                             AND D.PERIOD_SID = AG.PERIOD_SID
             LEFT OUTER JOIN FORECAST FG
                          ON D.ITEM_MASTER_SID = FG.ITEM_MASTER_SID
                             AND (D.COMPANY_MASTER_SID = FG.COMPANY_MASTER_SID OR D.COMPANY_MASTER_SID IS NULL)
                             AND D.PERIOD_SID = FG.PERIOD_SID 

GO