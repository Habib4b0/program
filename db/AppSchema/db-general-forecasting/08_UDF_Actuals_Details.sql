IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_ACTUALS_DETAILS')
  BEGIN
      DROP FUNCTION UDF_ACTUALS_DETAILS
  END

GO

CREATE FUNCTION [DBO].[UDF_ACTUALS_DETAILS] (@ACTUALS_CCP UDT_CCP_DETAILS READONLY,
                                            @START_DATE  DATETIME,
                                            @FROM_DATE   DATETIME)



RETURNS TABLE

AS
RETURN
      SELECT PROJECTION_DETAILS_SID,
             PERIOD_SID,
             DISCOUNT_ID,
             ISNULL(SALES, 0)                SALES,
             ISNULL(QUANTITY, 0)             QUANTITY,
             ISNULL(DISCOUNT, 0)             DISCOUNT,
             ISNULL(QUANTITY_INCLUSION, 'Y') QUANTITY_INCLUSION,
             START_DATE,
             END_DATE,
             A.YEAR,
             A.MONTH,
             A.QUARTER,
             A.COMPANY_MASTER_SID,
             A.CONTRACT_MASTER_SID,
             A.ITEM_MASTER_SID
      FROM   (SELECT CONTRACT_MASTER_SID,
                     COMPANY_MASTER_SID,
                     ITEM_MASTER_SID,
                     PROJECTION_DETAILS_SID,
                     DISCOUNT_ID,
                     PERIOD_SID,
                     YEAR,
                     MONTH,
                     QUARTER,
                     PERIOD_DATE
              FROM   PERIOD
                     CROSS JOIN @ACTUALS_CCP
              WHERE  PERIOD_DATE BETWEEN @FROM_DATE AND DATEADD(DD, -1, @START_DATE)) A---DATEADD(DD, -1, @START_DATE)) A
             LEFT JOIN (SELECT PROVISION_ID,
                               ACCRUAL_ACTUAL_START_DATE   START_DATE,
                               ACCRUAL_ACTUAL_END_DATE     END_DATE,
                               QUANTITY_INCLUSION,
                               A1.CONTRACT_MASTER_SID,
                               A1.COMPANY_MASTER_SID,
                               A1.ITEM_MASTER_SID,
                               SUM(SALES_AMOUNT) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
                                                     + 1 ) SALES,
                               SUM(QUANTITY) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
                                                 + 1 )     QUANTITY,
                               SUM(AMOUNT) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
                                               + 1 )       DISCOUNT
                        FROM   ACTUALS_MASTER A1
                        WHERE EXISTS (SELECT 1 FROM   @ACTUALS_CCP A2
                                       WHERE  A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID
                                              AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID
                                              AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID
                                              AND ( A1.PROVISION_ID = A2.DISCOUNT_ID
                                                     OR A2.DISCOUNT_ID IS NULL ))
                        GROUP  BY PROVISION_ID,
                                  ACCRUAL_ACTUAL_START_DATE,
                                  ACCRUAL_ACTUAL_END_DATE,
                                  QUANTITY_INCLUSION,
                                  A1.COMPANY_MASTER_SID,
                                  A1.CONTRACT_MASTER_SID,
                                  A1.ITEM_MASTER_SID) B
                    ON A.PERIOD_DATE BETWEEN B.START_DATE AND B.END_DATE
                       AND A.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID
                       AND A.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
                       AND A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                       AND ( A.DISCOUNT_ID = B.PROVISION_ID
                              OR A.DISCOUNT_ID IS NULL )
GO