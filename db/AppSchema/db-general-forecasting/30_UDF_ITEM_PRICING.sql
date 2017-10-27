IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_ITEM_PRICING')
  BEGIN
      DROP FUNCTION [DBO].UDF_ITEM_PRICING
  END

GO
CREATE FUNCTION [dbo].[UDF_ITEM_PRICING] (@ITEM_DETAILS      DBO.UDT_ITEM READONLY,
                                         @PRICING_QUALIFIER VARCHAR(8000),
                                         @START_PERIOD_SID  INT,
                                         @END_PERIOD_SID    INT,
                                         @ITEM_UOM          VARCHAR(50))
RETURNS TABLE
AS
    RETURN
      WITH DATA
           AS (SELECT ITEM_MASTER_SID,
                      TOKEN AS PRICING_QUALIFIER, 
                      PERIOD_SID,
                      [YEAR] AS PERIOD_YEAR,
                      [QUARTER] AS PERIOD_QUARTER,
                      [MONTH] AS PERIOD_MONTH
               FROM   @ITEM_DETAILS
                      CROSS JOIN PERIOD
                      CROSS JOIN DBO.UDF_SPLITSTRING(@PRICING_QUALIFIER, ',')
               WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID),
           ITEM_PRICING_DATA
           AS (SELECT ITEM_MASTER_SID,
                      ITEM_PRICE,
                      PRICING_QUALIFIER,
                      PERIOD_SID,
                      ROW_NUMBER()
                            OVER(
                              PARTITION BY ITEM_MASTER_SID, PRICING_QUALIFIER, PERIOD_SID
                              ORDER BY CREATED_DATE DESC) AS RN -- Based on GALUAT-123 (also this considers there will be WAC price at first day of month)
               FROM   (SELECT IP.ITEM_MASTER_SID,
                              IP.ITEM_PRICE,
                              IPQ.PRICING_QUALIFIER,
                              START_DATE,
                              ISNULL(END_DATE, LEAD(DATEADD(DD, -1, START_DATE), 1, NULL)
                                                            OVER(
                                                              PARTITION BY IP.ITEM_MASTER_SID,IPQ.PRICING_QUALIFIER
                                                              ORDER BY [START_DATE])) AS END_DATE,
															  ip.CREATED_DATE
                       FROM   DBO.ITEM_PRICING IP
                              JOIN DBO.ITEM_PRICING_QUALIFIER IPQ
                                ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                              INNER JOIN HELPER_TABLE HT
                                      ON HT.HELPER_TABLE_SID = IP.ITEM_UOM
                                         AND HT.LIST_NAME = 'UOM'
                       WHERE  EXISTS (SELECT 1
                                      FROM   DBO.UDF_SPLITSTRING(@PRICING_QUALIFIER, ',') F
                                      WHERE  F.TOKEN = IPQ.PRICING_QUALIFIER)
                              AND HT.DESCRIPTION = @ITEM_UOM
                              AND EXISTS (SELECT 1
                                          FROM   @ITEM_DETAILS ID
                                          WHERE  ID.ITEM_MASTER_SID = IP.ITEM_MASTER_SID)
                              AND IP.INBOUND_STATUS <> 'D') A
                      JOIN PERIOD P
                        ON A.START_DATE <= P.PERIOD_DATE
                           AND ( A.END_DATE >= P.PERIOD_DATE
                                  OR A.END_DATE IS NULL )
               WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID)
      SELECT D.ITEM_MASTER_SID,
             D.PERIOD_SID,
             D.PRICING_QUALIFIER,
             COALESCE(IPD.ITEM_PRICE, 0) AS ITEM_PRICE
      FROM   DATA D
             LEFT OUTER JOIN ITEM_PRICING_DATA IPD
                          ON IPD.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                             AND IPD.PRICING_QUALIFIER = D.PRICING_QUALIFIER
                             AND IPD.PERIOD_SID = D.PERIOD_SID
                             AND RN = 1



GO