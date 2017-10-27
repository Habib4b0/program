IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_ARP_OUTBOUND'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_ARP_OUTBOUND]
  END

GO

CREATE PROCEDURE [dbo].[PRC_ARP_OUTBOUND] (@PROJECTION INT)
AS

/**********************************************************************************************************
** File Name		:	PRC_ARP_OUTBOUND.sql
** Procedure Name	:	PRC_ARP_OUTBOUND
** Description		:	To generate ARP Outbound in Transaction Management for Latest approved projection in ARP
** Input Parameters	:	@PROJECTION             - Respective Projection ID Creted for ARP Outbound Process 										
** Output Parameters:	NA
** Author Name		:	@Lakshmi
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application in Transaction Management for ARP Outbound Process for Approved ARP Projections
                        
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date        Ticket No        Author           Description 
** ---   ----------  ---------        -------------    -----------------------------
** 1	 08/07/2017  GAL-12264        @Kishore Kumar   MI003 - Unqualified column name
*********************************************************************************************************/

  BEGIN
   SET NOCOUNT ON
   BEGIN TRY

-- Variables Initialization and taking from date as 12 months history and 36 months future Starts here

      DECLARE @ACTUAL_START_DATE  DATETIME,
              @PROJ_START_DATE    DATETIME = Dateadd(MM, Datediff(MM, 0, Dateadd(MM, 0, Getdate())), 0),
              @PROJ_END_DATE      DATETIME,
              @START_PERIOD_SID   INT,
              @END_PERIOD_SID     INT,
              @CURRENT_PERIOD_SID INT,
              @END_PERIOD         INT

      SELECT @CURRENT_PERIOD_SID = PERIOD_SID
      FROM   PERIOD
      WHERE  PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, Dateadd(MM, 0, Getdate())), 0)

      SET @ACTUAL_START_DATE = (SELECT Dateadd(MONTH, 0, Dateadd(Year, -1 * Datediff(Year, Getdate(), 0), 0)))
      SET @PROJ_END_DATE = (SELECT Dateadd(d, Datediff(d, 0, Dateadd(DAY, -1, Dateadd(YY, Datediff(YY, 0, Getdate()) + 3, 0))), 0))

-- Variables Initialization and taking from date as 12 months history and 36 months future Ends here

--Pulling Perios Sid's based on Projection Start date and End date Starts here

      SELECT @START_PERIOD_SID = PERIOD_SID
      FROM   PERIOD
      WHERE  PERIOD_DATE = @ACTUAL_START_DATE

      
      SELECT @END_PERIOD = ( PERIOD_SID ) - 1
      FROM   PERIOD
      WHERE  PERIOD_DATE = Dateadd(mm, Datediff(mm, 0, @PROJ_END_DATE) + 1, 0)

      SELECT @END_PERIOD_SID = PERIOD_SID
      FROM   period
      WHERE  PERIOD_DATE >= Dateadd(MONTH, 0, Dateadd(Year, -1 * Datediff(Year, @ACTUAL_START_DATE + 1, 0), 0))
             AND PERIOD_DATE < Dateadd(MM, Datediff(MM, 0, Dateadd(MM, 0, @PROJ_START_DATE)), 0)
			 
--Pulling Perios Sid's based on Projection Start date and End date Ends here

--Pulling CCP+D Combination for the Latest approved Projection and storing values Starts here

      IF Object_id('TEMPDB..#TEMP_CCP') IS NOT NULL
        DROP TABLE #TEMP_CCP

      CREATE TABLE #TEMP_CCP
        (
           PROJECTION_MASTER_SID    INT,
           WORKFLOW_ID              VARCHAR(100),
           PROJECTION_NAME          VARCHAR(500),
           ACCRUAL_PROJ_DETAILS_SID INT,
           CCP_DETAILS_SID          INT,
           RS_MODEL_SID             INT,
           COMPANY_MASTER_SID       INT,
           ITEM_MASTER_SID          INT,
           CREATED_DATE             DATETIME,
           APPROVED_DATE            DATETIME,
           BRAND_MASTER_SID         INT,
           CONTRACT_MASTER_SID      INT,
		   RS_CONTRACT_SID INT
           PRIMARY KEY ( PROJECTION_MASTER_SID, ACCRUAL_PROJ_DETAILS_SID )
        );

      WITH CTE
           AS (SELECT PD.PROJECTION_MASTER_SID,
                      WM.WORKFLOW_ID,
                      PD.CCP_DETAILS_SID,
                      PD.ACCRUAL_PROJ_DETAILS_SID,
                      PD.RS_MODEL_SID,
					  PD.RS_CONTRACT_SID,
                      P.PROJECTION_NAME,
                      P.COMPANY_MASTER_SID,
                      WM.CREATED_DATE,
                      WM.APPROVED_DATE
               FROM   WORKFLOW_MASTER WM
                      INNER JOIN HELPER_TABLE HT
                              ON WM.WORKFLOW_STATUS_ID = HT.HELPER_TABLE_SID
                      INNER JOIN ACCRUAL_PROJ_DETAILS PD
                              ON PD.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID
                      INNER JOIN PROJECTION_MASTER P
                              ON PD.PROJECTION_MASTER_SID = P.PROJECTION_MASTER_SID
                                 AND P.FORECASTING_TYPE = 'ACCRUALRATEPROJECTION'
               WHERE  HT.DESCRIPTION = 'APPROVED'
                      AND P.PROJECTION_MASTER_SID = @PROJECTION)
      INSERT INTO #TEMP_CCP
                  (PROJECTION_MASTER_SID,
                   WORKFLOW_ID,
                   PROJECTION_NAME,
                   ACCRUAL_PROJ_DETAILS_SID,
                   CCP_DETAILS_SID,
                   RS_MODEL_SID,
				   RS_CONTRACT_SID,
                   CREATED_DATE,
                   APPROVED_DATE,
                   COMPANY_MASTER_SID,
                   ITEM_MASTER_SID,
                   BRAND_MASTER_SID,
                   CONTRACT_MASTER_SID)
      SELECT C.PROJECTION_MASTER_SID,
             C.WORKFLOW_ID,
             C.PROJECTION_NAME,
             C.ACCRUAL_PROJ_DETAILS_SID,
             C.CCP_DETAILS_SID,
             C.RS_MODEL_SID,
			 C.RS_CONTRACT_SID,
             C.CREATED_DATE,
             C.APPROVED_DATE,
             C.COMPANY_MASTER_SID,
             IM.ITEM_MASTER_SID,
             IM.BRAND_MASTER_SID,
             CC.CONTRACT_MASTER_SID
      FROM   CTE C
             INNER JOIN CCP_DETAILS CC
                     ON C.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
             JOIN ITEM_MASTER IM
               ON IM.ITEM_MASTER_SID = CC.ITEM_MASTER_SID

--Pulling CCP+D Combination for the Latest approved Projection and storing values Ends here

--Pulling Rebate wise and Account Category wise data for Approved Projection Starts here

      IF Object_id('TEMPDB..#period') IS NOT NULL
        DROP TABLE #period

      SELECT PERIOD_SID,
             YEAR,
             MONTH
      INTO   #period
      FROM   PERIOD
      WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD

      IF Object_id('TEMPDB..#DATA') IS NOT NULL
        DROP TABLE #DATA

      SELECT PROJECTION_MASTER_SID,
                     WORKFLOW_ID,
                     PROJECTION_NAME,
                     ACCRUAL_PROJ_DETAILS_SID,
                     CCP_DETAILS_SID,
                     RS_MODEL_SID,
                     CREATED_DATE,
                     APPROVED_DATE,
                     COMPANY_MASTER_SID,
                     ITEM_MASTER_SID,
                     BRAND_MASTER_SID,ACCOUNT_CATEGORY,ACCOUNT_GROUP,YEAR,REBATE_PROGRAM_TYPE,RS_CATEGORY,RS_TYPE
      INTO   #data
      FROM   (SELECT CC.PROJECTION_MASTER_SID,
                     CC.WORKFLOW_ID,
                     CC.PROJECTION_NAME,
                     CC.ACCRUAL_PROJ_DETAILS_SID,
                     CC.CCP_DETAILS_SID,
                     CC.RS_MODEL_SID,
                     CC.CREATED_DATE,
                     CC.APPROVED_DATE,
                     CC.COMPANY_MASTER_SID,
                     CC.ITEM_MASTER_SID,
                     CC.BRAND_MASTER_SID,
                     IIF(h1.helper_table_sid = 0 ,'', h1.DESCRIPTION ) AS ACCOUNT_CATEGORY,
                     IIF(h.helper_table_sid = 0 ,'', h.DESCRIPTION ) AS ACCOUNT_GROUP,
                     P.YEAR,
                     H2.DESCRIPTION AS REBATE_PROGRAM_TYPE,
                     H3.DESCRIPTION AS RS_CATEGORY,
                     H4.DESCRIPTION AS RS_TYPE
              FROM   #TEMP_CCP CC
                     CROSS JOIN (SELECT DISTINCT YEAR
                                 FROM   #PERIOD) P
                     LEFT JOIN RS_CONTRACT R
                            ON CC.RS_MODEL_SID = R.RS_MODEL_SID
                               AND CC.CONTRACT_MASTER_SID = R.CONTRACT_MASTER_SID
							   AND R.INBOUND_STATUS <> 'D'
--Changed for GAL-1163
							   AND CC.RS_CONTRACT_SID=R.RS_CONTRACT_SID
                     LEFT JOIN UDCS U
                            ON R.RS_CONTRACT_SID = U.MASTER_SID
                               AND U.MASTER_TYPE = 'RS_CONTRACT'
                               --AND R.INBOUND_STATUS <> 'D'
                     -----------------------------
                     LEFT JOIN HELPER_TABLE H
                            ON H.HELPER_TABLE_SID = U.UDC5
                     LEFT JOIN HELPER_TABLE H1
                            ON H1.HELPER_TABLE_SID = U.UDC6
                     LEFT JOIN HELPER_TABLE H2
                            ON H2.HELPER_TABLE_SID = R.REBATE_PROGRAM_TYPE
                     LEFT JOIN HELPER_TABLE H3
                            ON H3.HELPER_TABLE_SID = R.RS_CATEGORY
                     LEFT JOIN HELPER_TABLE H4
                            ON H4.HELPER_TABLE_SID = R.RS_TYPE)a;
--Pulling Rebate wise and Account Category wise data for Approved Projection Ends here

--taking Units based on Projection and item wise Starts here
      WITH CTE
           AS (SELECT PROJECTION_MASTER_SID,
                      ITEM_MASTER_SID,
                      Sum([1])                 AS JAN,
                      Sum([2])                 AS FEB,
                      Sum([3])                 AS MAR,
                      Sum([4])                 AS APR,
                      Sum([5])                 AS MAY,
                      Sum([6])                 AS JUN,
                      Sum([7])                 AS JUL,
                      Sum([8])                 AS AUG,
                      Sum([9])                 AS SEP,
                      Sum([10])                AS OCT,
                      Sum([11])                AS NOV,
                      Sum([12])                AS DEC,
                      YEAR,
                      'SALES UNITS'            ACCOUNT,
                      'EX-FACTORY SALES UNITS' ACCOUNT_TYPE,
                      0                        AS OUTBOUND_STATUS
               FROM   (SELECT COALESCE(DA.PROJECTION_MASTER_SID, D.PROJECTION_MASTER_SID) AS PROJECTION_MASTER_SID,
                              COALESCE(DA.ITEM_MASTER_SID, D.ITEM_MASTER_SID)             AS ITEM_MASTER_SID,
                              P.YEAR,
                              P.MONTH,
                              COALESCE(DA.TOTAL_UNITS, D.TOTAL_UNITS)                     AS TOTAL_UNITS --INTO #ACCRUAL_SALES
                       FROM   (SELECT DA.PROJECTION_MASTER_SID,
                                      DA.TOTAL_UNITS,
                                      DA.PERIOD_SID,
                                      DA.ITEM_MASTER_SID
                               FROM   ACCRUAL_SALES_ACTUALS DA
                               WHERE  EXISTS (SELECT 1
                                              FROM   #TEMP_CCP T
                                              WHERE  T.PROJECTION_MASTER_SID = DA.PROJECTION_MASTER_SID
                                                     AND T.ITEM_MASTER_SID = DA.ITEM_MASTER_SID
                                                     AND DA.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID)) DA
                              FULL OUTER JOIN (SELECT D.PROJECTION_MASTER_SID,
                                                      D.TOTAL_UNITS,
                                                      D.PERIOD_SID,
                                                      D.ITEM_MASTER_SID
                                               FROM   ACCRUAL_SALES_DETAILS D
                                               WHERE  EXISTS (SELECT 1
                                                              FROM   #TEMP_CCP T
                                                              WHERE  T.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID
                                                                     AND T.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                                                                     AND D.PERIOD_SID BETWEEN @CURRENT_PERIOD_SID AND @END_PERIOD)) D
                                           ON DA.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID
                                              AND DA.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                                              AND DA.PERIOD_SID = D.PERIOD_SID
                              JOIN PERIOD P
                                ON P.PERIOD_SID = COALESCE(DA.PERIOD_SID, D.PERIOD_SID)) X
                      PIVOT(Sum(TOTAL_UNITS)
                           FOR MONTH IN ( [1],
                                          [2],
                                          [3],
                                          [4],
                                          [5],
                                          [6],
                                          [7],
                                          [8],
                                          [9],
                                          [10],
                                          [11],
                                          [12] )) PIV
               GROUP  BY PROJECTION_MASTER_SID,
                         ITEM_MASTER_SID,
                         YEAR)
--taking Units based on Projection and item wise Ends here

--insertion into main table monthly wise data Starts here

      INSERT INTO ARP_OUTBOUND
                  (PROJECTION_MASTER_SID,
                   ARP_WORKFLOW_ID,
                   RS_MODEL_SID,
                   ITEM_MASTER_SID,
                   COMPANY_MASTER_SID,
                   BRAND_MASTER_SID,
                   JAN,
                   FEB,
                   MAR,
                   APR,
                   MAY,
                   JUN,
                   JUL,
                   AUG,
                   SEP,
                   OCT,
                   NOV,
                   DEC,
                   YEAR,
                   ACCOUNT,
                   ACCOUNT_TYPE,
                   UOM,
                   ACCOUNT_CATEGORY,
                   ACCOUNT_GROUP,
                   ARP_CREATION_DATE,
                   ARP_APPROVAL_DATE,
                   OUTBOUND_STATUS)
      SELECT DISTINCT da.PROJECTION_MASTER_SID,
                      da.WORKFLOW_ID,
                      NULL                     AS RS_MODEL_SID,
                      da.ITEM_MASTER_SID,
                      da.COMPANY_MASTER_SID,
                      da.BRAND_MASTER_SID,
                      c.JAN,
                      c.FEB,
                      c.MAR,
                      c.APR,
                      c.MAY,
                      c.JUN,
                      c.JUL,
                      c.AUG,
                      c.SEP,
                      c.OCT,
                      c.NOV,
                      c.DEC,
                      da.YEAR,
                      'SALES UNITS'            ACCOUNT,
                      'EX-FACTORY SALES UNITS' ACCOUNT_TYPE,
                      'Units',
                      'Gross Units',
                      'Gross Units',
                      da.CREATED_DATE,
                      da.APPROVED_DATE,
                      0                        AS OUTBOUND_STATUS
      FROM   #DATA DA
             LEFT JOIN CTE C
                    ON DA.PROJECTION_MASTER_SID = C.PROJECTION_MASTER_SID
                       AND DA.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                       AND DA.YEAR = C.YEAR

--insertion into main table monthly wise data Ends here

      --CREATE NONCLUSTERED INDEX [NIX_ACCRUAL_RATE_DETAILS_ACCRUAL_PROJ_DETAILS_SID_DETAILS_ACCRUAL_RATE_INCLUDE_PERIOD_SID]
      --ON [dbo].[ACCRUAL_RATE_DETAILS] ([PERIOD_SID])
      --INCLUDE ([ACCRUAL_PROJ_DETAILS_SID],[DETAILS_ACCRUAL_RATE])
      --CREATE NONCLUSTERED INDEX [NIX_TEST]
      --ON [dbo].[ACCRUAL_RATE_DETAILS] ([PERIOD_SID])
      --INCLUDE ([ACCRUAL_PROJ_DETAILS_SID],[DETAILS_ACCRUAL_RATE])
      --CREATE NONCLUSTERED INDEX NIX_ARP_OUTBOUND_ARP_PROJ_SID_YEAR_INCLUDE_ACCOUNT_TYPE
      --ON [dbo].[ARP_OUTBOUND] ([ACCOUNT_TYPE])
      --INCLUDE ([ARP_PROJ_SID],[YEAR])

--Pulling Deduction Combination for Rate basis DDLB Starts here

      IF Object_id('TEMPDB..#RATE_BASIS') IS NOT NULL
        DROP TABLE #RATE_BASIS

      SELECT DISTINCT T.PROJECTION_MASTER_SID,
                      P.FIELD_VALUES
      INTO   #RATE_BASIS
      FROM   ACCRUAL_PROJ_SELECTION P
             INNER JOIN #TEMP_CCP T
                     ON P.PROJECTION_MASTER_SID = T.PROJECTION_MASTER_SID
      WHERE  P.FIELD_NAME = 'Rate Basis'
--Pulling Deduction Combination for Rate basis DDLB Ends here

--Pulling Demand and Adjusted demand for item and period combination Starts here

      IF Object_id('TEMPDB..#SALES') IS NOT NULL
        DROP TABLE #SALES

      CREATE TABLE #SALES
        (
           PERIOD_SID               INT,
           PROJECTION_MASTER_SID    INT,
           WORKFLOW_ID              VARCHAR(100),
           ACCRUAL_PROJ_DETAILS_SID INT,
           ITEM_MASTER_SID          INT,
           EXCLUDED_DOLLARS         NUMERIC(22, 6),
           DEMAND                   NUMERIC(22, 6),
           ADJUSTED_DEMAND          NUMERIC(22, 6)
        )

      INSERT INTO #SALES
                  (PROJECTION_MASTER_SID,
                   ITEM_MASTER_SID,
                   EXCLUDED_DOLLARS,
                   DEMAND,
                   ADJUSTED_DEMAND,
                   PERIOD_SID)
      SELECT COALESCE(DA.PROJECTION_MASTER_SID, D.PROJECTION_MASTER_SID) AS PROJECTION_MASTER_SID,
             COALESCE(DA.ITEM_MASTER_SID, D.ITEM_MASTER_SID)             AS ITEM_MASTER_SID,
             Sum(COALESCE(DA.EXCLUDED_DOLLARS, D.EXCLUDED_DOLLARS))      AS EXCLUDED_DOLLARS,
             Sum(COALESCE(DA.DEMAND, D.DEMAND))                          AS DEMAND,
             Sum(COALESCE(DA.ADJUSTED_DEMAND, D.ADJUSTED_DEMAND))        AS ADJUSTED_DEMAND,
             COALESCE(DA.PERIOD_SID, D.PERIOD_SID)                       AS PERIOD_SID
      FROM   (SELECT DA.PROJECTION_MASTER_SID,
                     DA.PERIOD_SID,
                     DA.ITEM_MASTER_SID,
                     DA.EXCLUDED_DOLLARS,
                     DA.DEMAND,
                     DA.ADJUSTED_DEMAND
              FROM   ACCRUAL_SALES_ACTUALS DA
              WHERE  EXISTS (SELECT 1
                             FROM   #TEMP_CCP T
                             WHERE  T.PROJECTION_MASTER_SID = DA.PROJECTION_MASTER_SID
                                    AND T.ITEM_MASTER_SID = DA.ITEM_MASTER_SID
                                    AND DA.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID)) DA
             FULL OUTER JOIN (SELECT D.PROJECTION_MASTER_SID,
                                     D.PERIOD_SID,
                                     D.ITEM_MASTER_SID,
                                     D.EXCLUDED_DOLLARS,
                                     D.DEMAND,
                                     D.ADJUSTED_DEMAND
                              FROM   ACCRUAL_SALES_DETAILS D
                              WHERE  EXISTS (SELECT 1
                                             FROM   #TEMP_CCP T
                                             WHERE  T.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID
                                                    AND T.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                                                    AND D.PERIOD_SID BETWEEN @CURRENT_PERIOD_SID AND @END_PERIOD)) D
                          ON DA.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                             AND DA.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID
                             AND DA.PERIOD_SID = D.PERIOD_SID
      GROUP  BY COALESCE(DA.PROJECTION_MASTER_SID, D.PROJECTION_MASTER_SID),
                COALESCE(DA.ITEM_MASTER_SID, D.ITEM_MASTER_SID),
                COALESCE(DA.PERIOD_SID, D.PERIOD_SID)

--Pulling Demand and Adjusted demand for item and period combination Ends here

--Pulling values from Details tab for approved projection for the item and period combination Starts here

      IF Object_id('TEMPDB..#DETAILS') IS NOT NULL
        DROP TABLE #DETAILS

      CREATE TABLE #DETAILS
        (
           PROJECTION_MASTER_SID      INT,
           ITEM_MASTER_SID            INT,
           PERIOD_SID                 INT,
           GROSS_TRADE_SALES          NUMERIC(22, 6),
           INVENTORY_WITHDRAWALS      NUMERIC(22, 6),
           ELIGIBLE_GROSS_TRADE_SALES NUMERIC(22, 6),
           PERIOD_BASIS_PRICE_CHANGE  NUMERIC(22, 6),
           GTS_ACCRUAL_BASIS          NUMERIC(22, 6)
        )

      INSERT INTO #DETAILS
                  (PROJECTION_MASTER_SID,
                   ITEM_MASTER_SID,
                   PERIOD_SID,
                   GROSS_TRADE_SALES,
                   INVENTORY_WITHDRAWALS,
                   ELIGIBLE_GROSS_TRADE_SALES,
                   PERIOD_BASIS_PRICE_CHANGE,
                   GTS_ACCRUAL_BASIS)
      SELECT COALESCE(DA.PROJECTION_MASTER_SID, D.PROJECTION_MASTER_SID)                AS PROJECTION_MASTER_SID,
             COALESCE(DA.ITEM_MASTER_SID, D.ITEM_MASTER_SID)                            AS ITEM_MASTER_SID,
             COALESCE(DA.PERIOD_SID, D.PERIOD_SID)                                      AS PERIOD_SID,
             Sum(COALESCE(DA.GROSS_TRADE_SALES, D.GROSS_TRADE_SALES))                   AS GROSS_TRADE_SALES,
             Sum(COALESCE(DA.INVENTORY_WITHDRAWALS, D.INVENTORY_WITHDRAWALS))           AS INVENTORY_WITHDRAWALS,
             Sum(COALESCE(DA.ELIGIBLE_GROSS_TRADE_SALES, D.ELIGIBLE_GROSS_TRADE_SALES)) AS ELIGIBLE_GROSS_TRADE_SALES,
             Sum(COALESCE(DA.PERIOD_BASIS_PRICE_CHANGE, D.PERIOD_BASIS_PRICE_CHANGE))   AS PERIOD_BASIS_PRICE_CHANGE,
             Sum(COALESCE(DA.GTS_ACCRUAL_BASIS, D.GTS_ACCRUAL_BASIS))                   AS GROSS_TRADE_SALES --INTO #ACCRUAL_SALES_DOLLAR
      FROM   (SELECT DA.PROJECTION_MASTER_SID,
                     DA.ITEM_MASTER_SID,
                     DA.PERIOD_SID,
                     DA.GROSS_TRADE_SALES,
                     DA.INVENTORY_WITHDRAWALS,
                     DA.ELIGIBLE_GROSS_TRADE_SALES,
                     DA.PERIOD_BASIS_PRICE_CHANGE,
                     DA.GTS_ACCRUAL_BASIS
              FROM   ACCRUAL_DETAILS_ACTUALS DA
              WHERE  EXISTS (SELECT 1
                             FROM   #TEMP_CCP T
                             WHERE  T.PROJECTION_MASTER_SID = DA.PROJECTION_MASTER_SID
                                    AND T.ITEM_MASTER_SID = DA.ITEM_MASTER_SID
                                    --   AND DA.COMPANY_MASTER_SID = T.COMPANY_MASTER_SID
                                    AND DA.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID)) DA
             FULL OUTER JOIN (SELECT D.PROJECTION_MASTER_SID,
                                     D.ITEM_MASTER_SID,
                                     D.PERIOD_SID,
                                     D.GROSS_TRADE_SALES,
                                     D.INVENTORY_WITHDRAWALS,
                                     D.ELIGIBLE_GROSS_TRADE_SALES,
                                     D.PERIOD_BASIS_PRICE_CHANGE,
                                     D.GTS_ACCRUAL_BASIS
                              FROM   ACCRUAL_DETAILS_INFO D
                              WHERE  EXISTS (SELECT 1
                                             FROM   #TEMP_CCP T
                                             WHERE  T.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID
                                                    AND T.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                                                    --  AND D.COMPANY_MASTER_SID = T.COMPANY_MASTER_SID
                                                    AND D.PERIOD_SID BETWEEN @CURRENT_PERIOD_SID AND @END_PERIOD)) D
                          ON DA.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID
                             AND DA.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                             AND DA.PERIOD_SID = D.PERIOD_SID
      GROUP  BY COALESCE(DA.PROJECTION_MASTER_SID, D.PROJECTION_MASTER_SID),
                COALESCE(DA.ITEM_MASTER_SID, D.ITEM_MASTER_SID),
                COALESCE(DA.PERIOD_SID, D.PERIOD_SID)
--Pulling values from Details tab for approved projection for the item and period combination Ends here

--Pulling Rate for Item and period combination which were calculated in Rate and Details tab Starts here

      IF Object_id('TEMPDB..#RATES') IS NOT NULL
        DROP TABLE #RATES

      CREATE TABLE #RATES
        (
           PERIOD_SID             INT,
           ITEM_MASTER_SID        INT,
           RS_MODEL_SID           INT,
           PAYMENTS               NUMERIC(22, 6),
           DETAILS_ACCRUAL_RATE   NUMERIC(22, 6),
           EFFECTIVE_ACCRUAL_RATE NUMERIC(22, 6)
        )

      INSERT INTO #RATES
                  (ITEM_MASTER_SID,
                   RS_MODEL_SID,
                   PERIOD_SID,
                   PAYMENTS,
                   EFFECTIVE_ACCRUAL_RATE,
                   DETAILS_ACCRUAL_RATE)
      SELECT COALESCE(DA.ITEM_MASTER_SID, D.ITEM_MASTER_SID)                    AS ITEM_MASTER_SID,
             COALESCE(DA.RS_MODEL_SID, D.RS_MODEL_SID)                          AS RS_MODEL_SID,
             COALESCE(DA.PERIOD_SID, D.PERIOD_SID)                              AS PERIOD_SID,
             Sum(COALESCE(DA.PAYMENTS, D.PAYMENTS))                             AS PAYMENTS,
             Sum(COALESCE(DA.EFFECTIVE_ACCRUAL_RATE, D.EFFECTIVE_ACCRUAL_RATE)) AS EFFECTIVE_ACCRUAL_RATE,
             Sum(COALESCE(DA.DETAILS_ACCRUAL_RATE, D.DETAILS_ACCRUAL_RATE))     AS DETAILS_ACCRUAL_RATE
      FROM   (SELECT DA.EFFECTIVE_ACCRUAL_RATE,
                     DA.PAYMENTS,
                     DA.DETAILS_ACCRUAL_RATE,
                     T.ITEM_MASTER_SID,
                     T.RS_MODEL_SID,
                     DA.PERIOD_SID
              FROM   ACCRUAL_RATE_ACTUALS DA
                     JOIN #TEMP_CCP T
                       ON T.ACCRUAL_PROJ_DETAILS_SID = DA.ACCRUAL_PROJ_DETAILS_SID
                          AND DA.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID) DA
             FULL OUTER JOIN (SELECT D.EFFECTIVE_ACCRUAL_RATE,
                                     D.PAYMENTS,
                                     D.DETAILS_ACCRUAL_RATE,
                                     T.ITEM_MASTER_SID,
                                     T.RS_MODEL_SID,
                                     D.PERIOD_SID
                              FROM   ACCRUAL_RATE_DETAILS D
                                     JOIN #TEMP_CCP T
                                       ON T.ACCRUAL_PROJ_DETAILS_SID = D.ACCRUAL_PROJ_DETAILS_SID
                                          AND D.PERIOD_SID BETWEEN @CURRENT_PERIOD_SID AND @END_PERIOD) D
                          ON DA.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                             AND DA.RS_MODEL_SID = D.RS_MODEL_SID
                             AND DA.PERIOD_SID = D.PERIOD_SID
      GROUP  BY COALESCE(DA.ITEM_MASTER_SID, D.ITEM_MASTER_SID),
                COALESCE(DA.RS_MODEL_SID, D.RS_MODEL_SID),
                COALESCE(DA.PERIOD_SID, D.PERIOD_SID)

--Pulling Rate for Item and period combination which were calculated in Rate and Details tab Ends here

--Rate calculation based on selected file in ARP Projection Starts here

      IF Object_id('TEMPDB..#ACCRUAL_RATE') IS NOT NULL
        DROP TABLE #ACCRUAL_RATE

      SELECT P."MONTH",
             P."YEAR",
             A.ITEM_MASTER_SID,
             a.RS_MODEL_SID,
             Isnull(d.GROSS_TRADE_SALES, 0)                                                                                                                                                          AS GROSS_TRADE_SALES,
             Isnull(b.EXCLUDED_DOLLARS, 0)                                                                                                                                                           AS EXCLUDED_DOLLARS,
             ( Isnull(d.GROSS_TRADE_SALES, 0)
               + Isnull(b.EXCLUDED_DOLLARS, 0) )                                                                                                                                                     AS ELIGIBLE_GROSS_TRADE_SALES,
             ( Isnull(d.PERIOD_BASIS_PRICE_CHANGE, 0) * 100 )                                                                                                                                        AS PERIOD_BASIS_PRICE_CHANGE,
             ( ( Isnull(d.GROSS_TRADE_SALES, 0)
                 + Isnull(b.EXCLUDED_DOLLARS, 0) ) * ( 1 + Isnull(d.PERIOD_BASIS_PRICE_CHANGE, 0) ) )                                                                                                  AS GTS_ACCRUAL_BASIS,
             d.INVENTORY_WITHDRAWALS,
             b.DEMAND,
             b.ADJUSTED_DEMAND,
             c.PAYMENTS,
             c.DETAILS_ACCRUAL_RATE,
             ( c.PAYMENTS / NULLIF(CASE
                                   WHEN R.FIELD_VALUES = 'Forecasted Ex-Factory Sales - Customer/Product' THEN D.GROSS_TRADE_SALES
                                   WHEN R.FIELD_VALUES = 'Forecasted Demand' THEN B.DEMAND
                                   WHEN R.FIELD_VALUES = 'Forecasted Adjusted Demand' THEN B.ADJUSTED_DEMAND
                                   WHEN R.FIELD_VALUES = 'Forecasted Inventory Withdrawals - Detail' THEN D.INVENTORY_WITHDRAWALS
                                 END, 0) ) * ( ( Isnull(d.GROSS_TRADE_SALES, 0)  + Isnull(b.EXCLUDED_DOLLARS, 0) ) * ( 1 + Isnull(d.PERIOD_BASIS_PRICE_CHANGE, 0) ) )                                                              AS DETAILS_ACCRUAL_AMOUNT,
             ( ( ( c.PAYMENTS / NULLIF(CASE
                                       WHEN R.FIELD_VALUES = 'Forecasted Ex-Factory Sales - Customer/Product' THEN D.GROSS_TRADE_SALES
                                       WHEN R.FIELD_VALUES = 'Forecasted Demand' THEN B.DEMAND
                                       WHEN R.FIELD_VALUES = 'Forecasted Adjusted Demand' THEN B.ADJUSTED_DEMAND
                                       WHEN R.FIELD_VALUES = 'Forecasted Inventory Withdrawals - Detail' THEN D.INVENTORY_WITHDRAWALS
                                     END, 0) ) * ( ( Isnull(d.GROSS_TRADE_SALES, 0)  + Isnull(b.EXCLUDED_DOLLARS, 0) ) * ( 1 + Isnull(d.PERIOD_BASIS_PRICE_CHANGE, 0) ) ) ) / NULLIF(( Isnull(d.GROSS_TRADE_SALES, 0)
                                                                                                                                                       + Isnull(b.EXCLUDED_DOLLARS, 0) ), 0) )  AS EFFECTIVE_ACCRUAL_RATE
      INTO   #ACCRUAL_RATE
      FROM   (SELECT DISTINCT PROJECTION_MASTER_SId,
                              ITEM_MASTER_SID,
                              RS_MODEL_SID
              FROM   #TEMP_CCP) a
             JOIN #SALES b
              ON a.PROJECTION_MASTER_SId = B.PROJECTION_MASTER_SID
                  AND a.ITEM_MASTER_SID = b.ITEM_MASTER_SID
             INNER JOIN PERIOD P
                     ON P.PERIOD_SID = B.PERIOD_SID
             LEFT JOIN #RATES C
                    ON c.ITEM_MASTER_SID = a.ITEM_MASTER_SID
                       AND C.RS_MODEL_SID = a.RS_MODEL_SID
                       AND C.PERIOD_SID = b.PERIOD_SID
                       AND P.PERIOD_SID = B.PERIOD_SID
             LEFT JOIN #DETAILS d
                    ON d.ITEM_MASTER_SID = a.ITEM_MASTER_SID
                       AND d.PROJECTION_MASTER_SID = a.PROJECTION_MASTER_SID
                       AND P.PERIOD_SID = d.PERIOD_SID
             JOIN #RATE_BASIS r
               ON r.PROJECTION_MASTER_SID = a.PROJECTION_MASTER_SID;

--Rate calculation based on selected file in ARP Projection Ends here

  --SELECT P."MONTH", 
  --       P."YEAR", 
  --      A.ITEM_MASTER_SID,
  --    ( ( ( PAYMENTS / NULLIF(CASE WHEN R.FIELD_VALUES='Forecasted Ex-Factory Sales - Customer/Product' THEN D.GROSS_TRADE_SALES
  --      WHEN R.FIELD_VALUES='Forecasted Demand' THEN B.DEMAND
  --      WHEN R.FIELD_VALUES='Forecasted Adjusted Demand' THEN B.ADJUSTED_DEMAND
  --      WHEN R.FIELD_VALUES='Forecasted Inventory Withdrawals - Detail' THEN D.INVENTORY_WITHDRAWALS
  --      END, 0) ) * ( ISNULL( 
  --             ELIGIBLE_GROSS_TRADE_SALES, 0) * ( 
  --               1 + ISNULL(PERIOD_BASIS_PRICE_CHANGE, 0) ) ) ) / NULLIF ( 
  --         ( ISNULL( GROSS_TRADE_SALES, 0 ) 
  --           + ISNULL( 
  --         EXCLUDED_DOLLARS, 0 ) ), 0) ) * 100             AS 
  --       EFFECTIVE_ACCRUAL_RATE 
  --       into #ACCRUAL_RATE FROM #TEMP_CCP a join #SALES b on a.PROJECTION_MASTER_SId=B.PROJECTION_MASTER_SID and a.ITEM_MASTER_SID=b.ITEM_MASTER_SID 
  --     INNER JOIN PERIOD P
  --                   ON P.PERIOD_SID=B.PERIOD_SID
  --                 LEFT joIN #RATES C on c.ITEM_MASTER_SID=a.ITEM_MASTER_SID
  --                 AND C.RS_MODEL_SID=a.RS_MODEL_SID 
  --                 AND C.PERIOD_SID=b.PERIOD_SID
  --                 AND P.PERIOD_SID=B.PERIOD_SID
  --                 LEFT joIN #DETAILS d on d.ITEM_MASTER_SID=a.ITEM_MASTER_SID and d.PROJECTION_MASTER_SID=a.PROJECTION_MASTER_SID 
  --                        AND P.PERIOD_SID=d.PERIOD_SID
  --                   join #RATE_BASIS r on r.PROJECTION_MASTER_SID=a.PROJECTION_MASTER_SID

  --Pulling rate and amount based on Account type Starts here
  ;
      WITH CTE
           AS (SELECT ITEM_MASTER_SID,
                      RS_MODEL_SID,
                      Sum([1])       AS JAN,
                      Sum([2])       AS FEB,
                      Sum([3])       AS MAR,
                      Sum([4])       AS APR,
                      Sum([5])       AS MAY,
                      Sum([6])       AS JUN,
                      Sum([7])       AS JUL,
                      Sum([8])       AS AUG,
                      Sum([9])       AS SEP,
                      Sum([10])      AS OCT,
                      Sum([11])      AS NOV,
                      Sum([12])      AS DEC,
                      YEAR,
                      'DEDUCTION'    ACCOUNT,
                      'Accrual Rate' ACCOUNT_TYPE,
                      0              AS OUTBOUND_STATUS
               FROM   #ACCRUAL_RATE x
                      PIVOT(Sum(EFFECTIVE_ACCRUAL_RATE)
                           FOR MONTH IN ( [1],
                                          [2],
                                          [3],
                                          [4],
                                          [5],
                                          [6],
                                          [7],
                                          [8],
                                          [9],
                                          [10],
                                          [11],
                                          [12] )) PIV
               GROUP  BY ITEM_MASTER_SID,
                         RS_MODEL_SID,
                         YEAR),
           CTE1
           AS (SELECT ITEM_MASTER_SID,
                      RS_MODEL_SID,
                      Sum([1])          AS JAN,
                      Sum([2])          AS FEB,
                      Sum([3])          AS MAR,
                      Sum([4])          AS APR,
                      Sum([5])          AS MAY,
                      Sum([6])          AS JUN,
                      Sum([7])          AS JUL,
                      Sum([8])          AS AUG,
                      Sum([9])          AS SEP,
                      Sum([10])         AS OCT,
                      Sum([11])         AS NOV,
                      Sum([12])         AS DEC,
                      YEAR,
                      'DEDUCTION'       ACCOUNT,
                      'Accrual Dollars' ACCOUNT_TYPE,
                      0                 AS OUTBOUND_STATUS
               FROM   #ACCRUAL_RATE x
                      PIVOT(Sum(DETAILS_ACCRUAL_AMOUNT)
                           FOR MONTH IN ( [1],
                                          [2],
                                          [3],
                                          [4],
                                          [5],
                                          [6],
                                          [7],
                                          [8],
                                          [9],
                                          [10],
                                          [11],
                                          [12] )) PIV
               GROUP  BY ITEM_MASTER_SID,
                         RS_MODEL_SID,
                         YEAR)

  --Pulling rate and amount based on Account type Ends here

  --insertion into main table for calculated rate based on account type for each month and year combination Starts here

      INSERT INTO ARP_OUTBOUND
                  (PROJECTION_MASTER_SID,
                   ARP_WORKFLOW_ID,
                   RS_MODEL_SID,
                   REBATE_PROGRAM_TYPE,
                   RS_CATEGORY,
                   RS_TYPE,
                   ITEM_MASTER_SID,
                   da.COMPANY_MASTER_SID,
                   BRAND_MASTER_SID,
                   JAN,
                   FEB,
                   MAR,
                   APR,
                   MAY,
                   JUN,
                   JUL,
                   AUG,
                   SEP,
                   OCT,
                   NOV,
                   DEC,
                   YEAR,
                   ACCOUNT,
                   ACCOUNT_TYPE,
                   UOM,
                   ACCOUNT_CATEGORY,
                   ACCOUNT_GROUP,
                   ARP_CREATION_DATE,
                   ARP_APPROVAL_DATE,
                   OUTBOUND_STATUS)
      SELECT DISTINCT PROJECTION_MASTER_SID,
                             WORKFLOW_ID,
                             RS_MODEL_SID,
                             REBATE_PROGRAM_TYPE,
                             RS_CATEGORY,
                             RS_TYPE,
                             ITEM_MASTER_SID,
                             COMPANY_MASTER_SID,
                             BRAND_MASTER_SID,
                             JAN,
                             FEB,
                             MAR,
                             APR,
                             MAY,
                             JUN,
                             JUL,
                             AUG,
                             SEP,
                             OCT,
                             NOV,
                             DEC,
                             YEAR,ACCOUNT,ACCOUNT_TYPE,UOM,ACCOUNT_CATEGORY,ACCOUNT_GROUP,CREATED_DATE,APPROVED_DATE,OUTBOUND_STATUS
      FROM  (SELECT DISTINCT da.PROJECTION_MASTER_SID,
                             da.WORKFLOW_ID,
                             da.RS_MODEL_SID,
                             DA.REBATE_PROGRAM_TYPE,
                             DA.RS_CATEGORY,
                             DA.RS_TYPE,
                             da.ITEM_MASTER_SID,
                             da.COMPANY_MASTER_SID,
                             da.BRAND_MASTER_SID,
                             c.JAN,
                             c.FEB,
                             c.MAR,
                             c.APR,
                             c.MAY,
                             c.JUN,
                             c.JUL,
                             c.AUG,
                             c.SEP,
                             c.OCT,
                             c.NOV,
                             c.DEC,
                             da.YEAR,
                             'DEDUCTION'    ACCOUNT,
                             'Accrual Rate' ACCOUNT_TYPE,
                             'Rate'         AS UOM,
                             DA.ACCOUNT_CATEGORY,
                             DA.ACCOUNT_GROUP,
                             da.CREATED_DATE,
                             da.APPROVED_DATE,
                             0              AS OUTBOUND_STATUS
             FROM   #DATA DA
                    LEFT JOIN CTE C
                           ON DA.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                              AND c.RS_MODEL_SID = da.RS_MODEL_SID
                              AND DA.YEAR = C.YEAR
             UNION
             SELECT DISTINCT da.PROJECTION_MASTER_SID,
                             da.WORKFLOW_ID,
                             da.RS_MODEL_SID,
                             DA.REBATE_PROGRAM_TYPE,
                             DA.RS_CATEGORY,
                             DA.RS_TYPE,
                             da.ITEM_MASTER_SID,
                             da.COMPANY_MASTER_SID,
                             da.BRAND_MASTER_SID,
                             c.JAN,
                             c.FEB,
                             c.MAR,
                             c.APR,
                             c.MAY,
                             c.JUN,
                             c.JUL,
                             c.AUG,
                             c.SEP,
                             c.OCT,
                             c.NOV,
                             c.DEC,
                             da.YEAR,
                             'DEDUCTION'       ACCOUNT,
                             'Accrual Dollars' ACCOUNT_TYPE,
                             'US Dollars'      AS UOM,
                             DA.ACCOUNT_CATEGORY,
                             DA.ACCOUNT_GROUP,
                             da.CREATED_DATE,
                             da.APPROVED_DATE,
                             0                 AS OUTBOUND_STATUS
             FROM   #DATA DA
                    LEFT JOIN CTE1 C
                           ON DA.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                              AND c.RS_MODEL_SID = da.RS_MODEL_SID
                              AND DA.YEAR = C.YEAR)a;

 --insertion into main table for calculated rate based on account type for each month and year combination Ends here

 -- for Account type EX-FACTORY SALES DOLLARS taking month wise data starts here 

      WITH CTE
           AS (SELECT PROJECTION_MASTER_SID,
                      -- COMPANY_MASTER_SID,
                      ITEM_MASTER_SID,
                      Sum([1])                   AS JAN,
                      Sum([2])                   AS FEB,
                      Sum([3])                   AS MAR,
                      Sum([4])                   AS APR,
                      Sum([5])                   AS MAY,
                      Sum([6])                   AS JUN,
                      Sum([7])                   AS JUL,
                      Sum([8])                   AS AUG,
                      Sum([9])                   AS SEP,
                      Sum([10])                  AS OCT,
                      Sum([11])                  AS NOV,
                      Sum([12])                  AS DEC,
                      YEAR,
                      'SALES DOLLARS'            ACCOUNT,
                      'EX-FACTORY SALES DOLLARS' ACCOUNT_TYPE,
                      0                          AS OUTBOUND_STATUS
               FROM   (SELECT COALESCE(DA.PROJECTION_MASTER_SID, D.PROJECTION_MASTER_SID) AS PROJECTION_MASTER_SID,
                              --  COALESCE(DA.COMPANY_MASTER_SID, D.COMPANY_MASTER_SID)       AS COMPANY_MASTER_SID,
                              COALESCE(DA.ITEM_MASTER_SID, D.ITEM_MASTER_SID)             AS ITEM_MASTER_SID,
                              P.YEAR,
                              P.MONTH,
                              COALESCE(DA.GROSS_TRADE_SALES, D.GROSS_TRADE_SALES)         AS TOTAL_UNITS --INTO #ACCRUAL_SALES_DOLLAR
                       FROM   (SELECT DA.PROJECTION_MASTER_SID,
                                      DA.GROSS_TRADE_SALES,
                                      PERIOD_SID,
                                      DA.ITEM_MASTER_SID
                               --,
                               -- DA.COMPANY_MASTER_SID
                               FROM   ACCRUAL_DETAILS_ACTUALS DA
                               WHERE  EXISTS (SELECT 1
                                              FROM   #TEMP_CCP T
                                              WHERE  T.PROJECTION_MASTER_SID = DA.PROJECTION_MASTER_SID
                                                     AND T.ITEM_MASTER_SID = DA.ITEM_MASTER_SID
                                                     --   AND DA.COMPANY_MASTER_SID = T.COMPANY_MASTER_SID
                                                     AND DA.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID)) DA
                              FULL OUTER JOIN (SELECT D.PROJECTION_MASTER_SID,
                                                      D.GROSS_TRADE_SALES,
                                                      PERIOD_SID,
                                                      D.ITEM_MASTER_SID
                                               --,
                                               --   D.COMPANY_MASTER_SID
                                               FROM   ACCRUAL_DETAILS_INFO D
                                               WHERE  EXISTS (SELECT 1
                                                              FROM   #TEMP_CCP T
                                                              WHERE  T.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID
                                                                     AND T.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                                                                     -- AND D.COMPANY_MASTER_SID = T.COMPANY_MASTER_SID
                                                                     AND D.PERIOD_SID BETWEEN @CURRENT_PERIOD_SID AND @END_PERIOD)) D
                                           ON DA.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID
                                              AND DA.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                                              -- AND DA.COMPANY_MASTER_SID = D.COMPANY_MASTER_SID
                                              AND DA.PERIOD_SID = D.PERIOD_SID
                              JOIN PERIOD P
                                ON P.PERIOD_SID = COALESCE(DA.PERIOD_SID, D.PERIOD_SID)) X
                      PIVOT(Sum(TOTAL_UNITS)
                           FOR MONTH IN ( [1],
                                          [2],
                                          [3],
                                          [4],
                                          [5],
                                          [6],
                                          [7],
                                          [8],
                                          [9],
                                          [10],
                                          [11],
                                          [12] )) PIV
               GROUP  BY PROJECTION_MASTER_SID,
                         -- COMPANY_MASTER_SID,
                         ITEM_MASTER_SID,
                         YEAR)
 -- for Account type EX-FACTORY SALES DOLLARS taking month wise data Ends here 

 --insertion into main table for Account type EX-FACTORY SALES DOLLARS for that projection Peiord wise Starts here

      INSERT INTO ARP_OUTBOUND
                  (PROJECTION_MASTER_SID,
                   ARP_WORKFLOW_ID,
                   RS_MODEL_SID,
                   ITEM_MASTER_SID,
                   COMPANY_MASTER_SID,
                   BRAND_MASTER_SID,
                   JAN,
                   FEB,
                   MAR,
                   APR,
                   MAY,
                   JUN,
                   JUL,
                   AUG,
                   SEP,
                   OCT,
                   NOV,
                   DEC,
                   YEAR,
                   ACCOUNT,
                   ACCOUNT_TYPE,
                   UOM,
                   ACCOUNT_CATEGORY,
                   ACCOUNT_GROUP,
                   ARP_CREATION_DATE,
                   ARP_APPROVAL_DATE,
                   OUTBOUND_STATUS)
      SELECT DISTINCT da.PROJECTION_MASTER_SID,
                      da.WORKFLOW_ID,
                      NULL                       AS RS_MODEL_SID,
                      da.ITEM_MASTER_SID,
                      da.COMPANY_MASTER_SID,
                      da.BRAND_MASTER_SID,
                      c.JAN,
                      c.FEB,
                      c.MAR,
                      c.APR,
                      c.MAY,
                      c.JUN,
                      c.JUL,
                      c.AUG,
                      c.SEP,
                      c.OCT,
                      c.NOV,
                      c.DEC,
                      da.YEAR,
                      'SALES DOLLARS'            ACCOUNT,
                      'EX-FACTORY SALES DOLLARS' ACCOUNT_TYPE,
                      'US Dollars',
                      'Gross Sales',
                      'Gross Sales',
                      da.CREATED_DATE,
                      da.APPROVED_DATE,
                      0                          AS OUTBOUND_STATUS
      FROM   #DATA DA
             LEFT JOIN CTE C
                    ON DA.PROJECTION_MASTER_SID = C.PROJECTION_MASTER_SID
                       -- AND DA.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID
                       AND DA.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                       AND DA.YEAR = C.YEAR
	END TRY

      BEGIN CATCH
          DECLARE @ErrorMessage NVARCHAR(4000);
          DECLARE @ErrorSeverity INT;
          DECLARE @ErrorState INT;
          DECLARE @ErrorNumber INT;
          DECLARE @ErrorProcedure VARCHAR(200);
          DECLARE @Errorline INT;

          EXEC USPERRORCOLLECTOR

          SELECT @ErrorMessage = ERROR_MESSAGE(),
                 @ErrorSeverity = ERROR_SEVERITY(),
                 @ErrorState = ERROR_STATE(),
                 @ErrorProcedure = ERROR_PROCEDURE(),
                 @Errorline = ERROR_LINE(),
                 @ErrorNumber = ERROR_NUMBER()

          RAISERROR (@ErrorMessage,-- Message text.
                     @ErrorSeverity,-- Severity.
                     @ErrorState,-- State.
                     @ErrorProcedure,-- Procedure_Name.
                     @ErrorNumber,-- ErrorNumber
                     @Errorline -- ErrorLine
          );
      END CATCH
  END

 --insertion into main table for Account type EX-FACTORY SALES DOLLARS for that projection Peiord wise Ends here

GO


