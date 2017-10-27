IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_CUST_GTS_WAC'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP FUNCTION [DBO].[UDF_CUST_GTS_WAC]
  END

GO

CREATE FUNCTION [dbo].[UDF_CUST_GTS_WAC] (@CUST_ITEM_DETAILS [UDT_CUST_ITEM] READONLY,--ITEM LIST
                                             @START_PERIOD_SID  INT,--START PERIOD 
                                             @END_PERIOD_SID    INT,--END PERIOD
                                             @FORECAST_NAME     VARCHAR(50),-- ACTIVE FILE NAME IN FILE MANAGEMENT
                                             @FORECAST_VERSION  VARCHAR(15))
RETURNS TABLE
AS
    RETURN
      WITH DATA
           AS (SELECT COMPANY_MASTER_SID,
                      ITEM_MASTER_SID,
                      PERIOD_SID,
                      [YEAR] AS PERIOD_YEAR,
                      [QUARTER] AS PERIOD_QUARTER,
                      [MONTH] AS PERIOD_MONTH
               FROM   @CUST_ITEM_DETAILS
                      CROSS JOIN PERIOD
               WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID),
           ACTUAL_GTS
           AS   (SELECT CI.COMPANY_MASTER_SID AS COMPANY_MASTER_SID,
                              IM.ITEM_MASTER_SID,
                              P.PERIOD_SID,
                              SUM( AMOUNT )                       AS CUST_ACT_GTS_SALES,
                              SUM( QUANTITY )                     AS CUST_ACT_GTS_UNITS
                       FROM   CUSTOMER_GTS_ACTUAL CG
                              JOIN COMPANY_MASTER CM
                                ON CM.COMPANY_ID = CG.ACCOUNT_ID
                              JOIN ITEM_MASTER IM
                                ON IM.ITEM_ID = CG.ITEM_ID
                              JOIN PERIOD P
                                ON Year(INVOICE_DATE) = P.YEAR
                                   AND Month(INVOICE_DATE) = P.MONTH
                              JOIN @CUST_ITEM_DETAILS CI
                                ON ( CI.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID
                                      OR CI.COMPANY_MASTER_SID IS NULL )
                                   AND CI.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                       WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
               GROUP  BY CI.COMPANY_MASTER_SID,
                         IM.ITEM_MASTER_SID,
                         PERIOD_SID),
           FORECAST_GTS
           AS (SELECT CI.COMPANY_MASTER_SID            AS COMPANY_MASTER_SID,
                              CG.ITEM_MASTER_SID,
                              SUM(SALES_AMOUNT)                     AS CUST_FORE_GTS_SALES,
                              SUM(UNITS)                            AS CUST_FORE_GTS_UNITS,
                              P.PERIOD_SID,
                              CG.FORECAST_NAME,
                              CG.FORECAST_VER
                       FROM   CUSTOMER_GTS_FORECAST CG
                              JOIN @CUST_ITEM_DETAILS CI
                                ON ( CI.COMPANY_MASTER_SID = CG.COMPANY_MASTER_SID
                                      OR CI.COMPANY_MASTER_SID IS NULL )
                                   AND CI.ITEM_MASTER_SID = CG.ITEM_MASTER_SID
                              JOIN PERIOD P
                                ON P.MONTH = CG.FORECAST_MONTH
                                   AND P.YEAR = CG.FORECAST_YEAR
                       WHERE  CG.FORECAST_NAME = @FORECAST_NAME
                              AND FORECAST_VER IN ( @FORECAST_VERSION, Floor(@FORECAST_VERSION) )
                              AND PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
               GROUP  BY CI.COMPANY_MASTER_SID,
                         CG.ITEM_MASTER_SID,
                         PERIOD_SID,
                         FORECAST_NAME,
                         FORECAST_VER)


						 
      SELECT D.COMPANY_MASTER_SID AS COMPANY_MASTER_SID,
             D.ITEM_MASTER_SID    AS ITEM_MASTER_SID,
             D.PERIOD_SID,
             AG.CUST_ACT_GTS_SALES,
             AG.CUST_ACT_GTS_UNITS,
             FG.CUST_FORE_GTS_SALES,
             FG.CUST_FORE_GTS_UNITS,
             CUST_ACT_GTS_SALES / NULLIF(CUST_ACT_GTS_UNITS, 0) AS ACTUAL_PRICE,
             CUST_FORE_GTS_SALES / NULLIF(CUST_FORE_GTS_UNITS, 0) AS FORECAST_PRICE 
      FROM   DATA D
             LEFT OUTER JOIN ACTUAL_GTS AG
                          ON D.ITEM_MASTER_SID = AG.ITEM_MASTER_SID
                             AND D.PERIOD_SID = AG.PERIOD_SID
                             AND (D.COMPANY_MASTER_SID = AG.COMPANY_MASTER_SID OR D.COMPANY_MASTER_SID IS NULL)
             LEFT OUTER JOIN FORECAST_GTS FG
                          ON D.ITEM_MASTER_SID = FG.ITEM_MASTER_SID
                             AND D.PERIOD_SID = FG.PERIOD_SID
                             AND (D.COMPANY_MASTER_SID = FG.COMPANY_MASTER_SID OR D.COMPANY_MASTER_SID IS NULL) 



GO 
