IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_FE_ADD_EVENT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_FE_ADD_EVENT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_FE_ADD_EVENT] (@SOURCE_PROJECTION_MASTER_SID INT,
                                           @TARGET_PROJECTION_MASTER_SID INT,
                                           @MARKET_TYPE                  VARCHAR(100)=NULL)
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          DECLARE @PROJECTION_START_DATE DATETIME,
                  @PROJECTION_END_DATE   DATETIME,
                  @ACTUALS_START_DATE    DATETIME,
                  @ACTUAL_END_DATE       DATETIME,
                  @FORECASTING_TYPE      VARCHAR(50)

          SELECT @ACTUALS_START_DATE = Dateadd(YY, Datediff(YY, 0, Getdate()) - 3, 0),
                 @ACTUAL_END_DATE = Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0),
                 @PROJECTION_START_DATE = Dateadd(QQ, Datediff(QQ, 0, FROM_DATE), 0),
                 @PROJECTION_END_DATE = Dateadd(DAY, -1, Dateadd(QQ, Datediff(QQ, 0, TO_DATE) + 1, 0)),
                 @FORECASTING_TYPE = FORECASTING_TYPE
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID;

          DECLARE @V INT

          SELECT @V = HELPER_TABLE_SID
          FROM   HELPER_TABLE
          WHERE  LIST_NAME = 'RS_CATEGORY'
                 AND DESCRIPTION = 'STATE_SUPP'

          DECLARE @TEMP_TABLE AS TABLE
            (
               PROJECTION_DETAILS_SID INT,
               PROJECTION_MASTER_SID  INT,
               CCP_DETAILS_SID        INT,
               CONTRACT_MASTER_SID    INT,
               COMPANY_MASTER_SID     INT,
               ITEM_MASTER_SID        INT
            )

          INSERT INTO @TEMP_TABLE
                      (PROJECTION_DETAILS_SID,
                       PROJECTION_MASTER_SID,
                       CCP_DETAILS_SID,
                       CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID)
          SELECT PROJECTION_DETAILS_SID,
                 PROJECTION_MASTER_SID,
                 PD.CCP_DETAILS_SID,
                 CONTRACT_MASTER_SID,
                 COMPANY_MASTER_SID,
                 ITEM_MASTER_SID
          FROM   PROJECTION_DETAILS PD
                 JOIN CCP_DETAILS CCP
                   ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
          WHERE  PD.PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID

          CREATE TABLE #TEMP_TABLE_SOURCE
            (
               PROJECTION_DETAILS_SID INT,
               PROJECTION_MASTER_SID  INT,
               CCP_DETAILS_SID        INT,
               CONTRACT_MASTER_SID    INT,
               COMPANY_MASTER_SID     INT,
               ITEM_MASTER_SID        INT
            )

          INSERT INTO #TEMP_TABLE_SOURCE
                      (PROJECTION_DETAILS_SID,
                       PROJECTION_MASTER_SID,
                       CCP_DETAILS_SID,
                       CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID)
          SELECT PROJECTION_DETAILS_SID,
                 PROJECTION_MASTER_SID,
                 PD.CCP_DETAILS_SID,
                 CONTRACT_MASTER_SID,
                 COMPANY_MASTER_SID,
                 ITEM_MASTER_SID
          FROM   PROJECTION_DETAILS PD
                 JOIN CCP_DETAILS CCP
                   ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
          WHERE  PD.PROJECTION_MASTER_SID = @SOURCE_PROJECTION_MASTER_SID

          ------------------TARGET CCP DETAILS INSERT END
          -------------------------------------------------------SALES START--------------------------------------------------------
          ----------------MASTER DATA INSERT START
          DECLARE @MASTER_SQL NVARCHAR(MAX)=''

          SET @MASTER_SQL = '
;WITH TARGET_CCP_DETAILS
     AS (SELECT PROJECTION_MASTER_SID,
                PROJECTION_DETAILS_SID,
                CCP_DETAILS_SID
         FROM   PROJECTION_DETAILS
         WHERE  PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID),
     SOURCE_PROJECTION_DETAILS
     AS (SELECT CCP_DETAILS_SID,
                METHODOLOGY,
               ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'USER_GROUP,' ELSE '' END + '
                CALCULATION_PERIODS,
                CALCULATION_BASED,
                CHECK_RECORD
         FROM   ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'NM_SALES_PROJECTION_MASTER' ELSE 'M_SALES_PROJECTION_MASTER' END
                            + ' NSP
                INNER JOIN #TEMP_TABLE_SOURCE PD
                        ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID )
INSERT INTO ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'NM_SALES_PROJECTION_MASTER' ELSE 'M_SALES_PROJECTION_MASTER' END + '
            (PROJECTION_DETAILS_SID,
             METHODOLOGY,
             ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'USER_GROUP,' ELSE '' END
                            + '
             CALCULATION_PERIODS,
             CALCULATION_BASED,
             CHECK_RECORD)
SELECT T.PROJECTION_DETAILS_SID,
       METHODOLOGY,
       ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'ISNULL(USER_GROUP,0),' ELSE '' END + '
       CALCULATION_PERIODS,
       CALCULATION_BASED,
       COALESCE(CHECK_RECORD,0)
FROM   TARGET_CCP_DETAILS T
       LEFT OUTER JOIN SOURCE_PROJECTION_DETAILS S
                    ON S.CCP_DETAILS_SID = T.CCP_DETAILS_SID 
'

          EXEC SP_EXECUTESQL
            @MASTER_SQL,
            N'@TARGET_PROJECTION_MASTER_SID INT,@SOURCE_PROJECTION_MASTER_SID INT',
            @TARGET_PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID,
            @SOURCE_PROJECTION_MASTER_SID = @SOURCE_PROJECTION_MASTER_SID

          -------------MASTER DATA INSERT END
          ----------------ACTUAL DATA INSERT START 
          DECLARE @ACTUAL_SQL NVARCHAR(MAX)=''

          SET @ACTUAL_SQL = '
;WITH TARGET_CCP_DETAILS
     AS (SELECT PROJECTION_MASTER_SID,
                PROJECTION_DETAILS_SID,
                CCP_DETAILS_SID,
                PERIOD_SID
         FROM   PROJECTION_DETAILS PD
                CROSS JOIN PERIOD P
         WHERE  P.PERIOD_DATE BETWEEN @ACTUALS_START_DATE AND @ACTUAL_END_DATE
                AND PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID),
     SOURCE_PROJECTION_DETAILS
     AS (SELECT CCP_DETAILS_SID,
                ACTUAL_SALES,
				ACTUAL_UNITS,
				PERIOD_SID
         FROM   ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'NM_ACTUAL_SALES' ELSE 'M_ACTUAL_SALES' END + ' NSP
                INNER JOIN #TEMP_TABLE_SOURCE PD
                        ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID)
INSERT INTO ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'NM_ACTUAL_SALES' ELSE 'M_ACTUAL_SALES' END + '
            (PROJECTION_DETAILS_SID,
             PERIOD_SID,
             ACTUAL_SALES,
		     ACTUAL_UNITS)
SELECT T.PROJECTION_DETAILS_SID,
       T.PERIOD_SID,
       ISNULL(S.ACTUAL_SALES,0),
	   ISNULL(S.ACTUAL_UNITS,0)
FROM   TARGET_CCP_DETAILS T
       LEFT OUTER JOIN SOURCE_PROJECTION_DETAILS S
                    ON S.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                       AND T.PERIOD_SID = S.PERIOD_SID 
'

          EXEC SP_EXECUTESQL
            @ACTUAL_SQL,
            N'@TARGET_PROJECTION_MASTER_SID INT,@SOURCE_PROJECTION_MASTER_SID INT,@ACTUAL_END_DATE DATETIME, @ACTUALS_START_DATE DATETIME',
            @TARGET_PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID,
            @SOURCE_PROJECTION_MASTER_SID = @SOURCE_PROJECTION_MASTER_SID,
            @ACTUAL_END_DATE = @ACTUAL_END_DATE,
            @ACTUALS_START_DATE = @ACTUALS_START_DATE

          ----------------ACTUAL DATA INSERT END
          ---------------PROJECTION DATA INSERT START
          DECLARE @PROJ_SQL NVARCHAR(MAX)=''

          SET @PROJ_SQL = '
;WITH TARGET_CCP_DETAILS
     AS (SELECT PROJECTION_MASTER_SID,
                PROJECTION_DETAILS_SID,
                CCP_DETAILS_SID,
                PERIOD_SID
         FROM   PROJECTION_DETAILS PD
                CROSS JOIN PERIOD P
         WHERE  P.PERIOD_DATE BETWEEN @PROJECTION_START_DATE AND @PROJECTION_END_DATE
                AND PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID),
     SOURCE_PROJECTION_DETAILS
     AS (SELECT NSP.ACCOUNT_GROWTH,
                NSP.PRODUCT_GROWTH,
                NSP.PROJECTION_SALES,
                NSP.PROJECTION_UNITS,
                NSP.PERIOD_SID,
                CCP_DETAILS_SID,
                ADJUSTMENT_TYPE,
                ADJUSTMENT_BASIS,
                ADJUSTMENT_VARIABLE,
                ADJUSTMENT_METHODOLOGY,
                ADJUSTMENT_VALUES
         FROM   ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'NM_SALES_PROJECTION' ELSE 'M_SALES_PROJECTION' END + ' NSP
                INNER JOIN #TEMP_TABLE_SOURCE PD
                        ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID)
INSERT INTO ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'NM_SALES_PROJECTION' ELSE 'M_SALES_PROJECTION' END + '
            (PROJECTION_DETAILS_SID,
             ACCOUNT_GROWTH,
             PRODUCT_GROWTH,
             PROJECTION_SALES,
             PROJECTION_UNITS,
             PERIOD_SID,
             ADJUSTMENT_TYPE,
             ADJUSTMENT_BASIS,
             ADJUSTMENT_VARIABLE,
             ADJUSTMENT_METHODOLOGY,
             ADJUSTMENT_VALUES)
SELECT T.PROJECTION_DETAILS_SID,
       ISNULL(S.ACCOUNT_GROWTH,0),
       ISNULL(S.PRODUCT_GROWTH,0),
       ISNULL(S.PROJECTION_SALES,0),
       ISNULL(S.PROJECTION_UNITS,0),
       T.PERIOD_SID,
       S.ADJUSTMENT_TYPE,
       S.ADJUSTMENT_BASIS,
       S.ADJUSTMENT_VARIABLE,
       S.ADJUSTMENT_METHODOLOGY,
       S.ADJUSTMENT_VALUES
FROM   TARGET_CCP_DETAILS T
       LEFT OUTER JOIN SOURCE_PROJECTION_DETAILS S
                    ON S.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                       AND T.PERIOD_SID = S.PERIOD_SID 
'

          EXEC SP_EXECUTESQL
            @PROJ_SQL,
            N'@TARGET_PROJECTION_MASTER_SID INT,@SOURCE_PROJECTION_MASTER_SID INT,@PROJECTION_START_DATE DATETIME,@PROJECTION_END_DATE DATETIME',
            @TARGET_PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID,
            @SOURCE_PROJECTION_MASTER_SID = @SOURCE_PROJECTION_MASTER_SID,
            @PROJECTION_START_DATE = @PROJECTION_START_DATE,
            @PROJECTION_END_DATE = @PROJECTION_END_DATE

          ---------------PROJECTION DATA INSERT END
          ----------------------------------------------------------SALES END-----------------------------------------------------
          ---------------------------------------------------------DISCOUNT INSERT START------------------------------------------
          ----------------MASTER DATA INSERT START
          IF @FORECASTING_TYPE <> 'MANDATED'
            BEGIN
                IF Object_id('TEMPDB..#TARGET_CCP_DETAILS') IS NOT NULL
                  TRUNCATE TABLE #TARGET_CCP_DETAILS
                ELSE
                  CREATE TABLE #TARGET_CCP_DETAILS
                    (
                       PROJECTION_MASTER_SID  INT,
                       PROJECTION_DETAILS_SID INT,
                       CCP_DETAILS_SID        INT,
                       RS_MODEL_SID           INT,
                       PRICE_GROUP_TYPE       VARCHAR(50),
                       NET_FLAG               CHAR(1)
                    )

                INSERT INTO #TARGET_CCP_DETAILS
                            (PROJECTION_MASTER_SID,
                             PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PRICE_GROUP_TYPE,
                             NET_FLAG)
                SELECT DISTINCT PROJECTION_MASTER_SID,
                                PROJECTION_DETAILS_SID,
                                B.CCP_DETAILS_SID,
                                R.RS_MODEL_SID,
                                H1.DESCRIPTION AS PRICE_GROUP_TYPE,
                                NET_FLAG =( CASE
                                              WHEN H1.DESCRIPTION LIKE 'OFF%' THEN 'N'
                                              ELSE 'Y'
                                            END )
                FROM   @TEMP_TABLE B
                       JOIN RS_CONTRACT R1
                         ON R1.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
                       JOIN RS_CONTRACT_DETAILS R2
                         ON R2.RS_CONTRACT_SID = R1.RS_CONTRACT_SID
                            AND R2.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                       JOIN RS_MODEL R
                         ON R.RS_MODEL_SID = R1.RS_MODEL_SID
                       LEFT JOIN UDCS U
                              ON U.MASTER_SID = R1.RS_CONTRACT_SID
                                 AND U.MASTER_TYPE = 'RS_CONTRACT'
                       LEFT JOIN HELPER_TABLE H1
                              ON H1.HELPER_TABLE_SID = U.UDC2
                       LEFT JOIN HELPER_TABLE H2
                              ON H2.HELPER_TABLE_SID = U.UDC3
                WHERE  R1.INBOUND_STATUS <> 'D'
                       AND R2.INBOUND_STATUS <> 'D'
                       AND EXISTS(SELECT 1
                                  FROM   CFP_CONTRACT CFP1
                                         JOIN CFP_CONTRACT_DETAILS CFP2
                                           ON CFP1.CFP_CONTRACT_SID = CFP2.CFP_CONTRACT_SID
                                              AND R1.CFP_CONTRACT_SID = CFP1.CFP_CONTRACT_SID
                                  WHERE  COMPANY_MASTER_SID = B.COMPANY_MASTER_SID
                                         AND CFP1.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID);

                WITH SOURCE_PROJECTION_DETAILS
                     AS (SELECT CCP_DETAILS_SID,
                                RS_MODEL_SID,
                                METHODOLOGY,
                                PRICE_GROUP_TYPE,
                                NET_FLAG,
                                USER_GROUP,
                                CHECK_RECORD,
                                BASELINE_PERIODS,
                                SELECTED_PERIODS
                         FROM   NM_DISCOUNT_PROJ_MASTER NSP
                                INNER JOIN #TEMP_TABLE_SOURCE PD
                                        ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID)
                INSERT INTO NM_DISCOUNT_PROJ_MASTER
                            (PROJECTION_DETAILS_SID,
                             RS_MODEL_SID,
                             METHODOLOGY,
                             PRICE_GROUP_TYPE,
                             NET_FLAG,
                             USER_GROUP,
                             CHECK_RECORD,
                             BASELINE_PERIODS,
                             SELECTED_PERIODS)
                SELECT T.PROJECTION_DETAILS_SID,
                       COALESCE(S.RS_MODEL_SID, T.RS_MODEL_SID),
                       S.METHODOLOGY,
                       COALESCE(S.PRICE_GROUP_TYPE, T.PRICE_GROUP_TYPE),
                       COALESCE(S.NET_FLAG, T.NET_FLAG),
                       ISNULL(S.USER_GROUP, 0),
                       COALESCE(CHECK_RECORD, 0),
                       S.BASELINE_PERIODS,
                       S.SELECTED_PERIODS
                FROM   #TARGET_CCP_DETAILS T
                       LEFT OUTER JOIN SOURCE_PROJECTION_DETAILS S
                                    ON S.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                                       AND S.RS_MODEL_SID = T.RS_MODEL_SID
            END

          -------------MASTER DATA INSERT END
          ----------------ACTUAL DATA INSERT START 
          SET @ACTUAL_SQL =''
          SET @ACTUAL_SQL = '
;WITH TARGET_CCP_DETAILS
     AS (SELECT PROJECTION_DETAILS_SID,
                CCP_DETAILS_SID, ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'RS_MODEL_SID,' ELSE '' END
                            + '
                PERIOD_SID 
         FROM  ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN '#TARGET_CCP_DETAILS' ELSE 'PROJECTION_DETAILS' END + ' PD
                CROSS JOIN PERIOD P
         WHERE  P.PERIOD_DATE BETWEEN @ACTUALS_START_DATE AND @ACTUAL_END_DATE
                AND PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID),
     SOURCE_PROJECTION_DETAILS
     AS (SELECT CCP_DETAILS_SID,
                ACTUAL_SALES,
				ACTUAL_RATE,' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'RS_MODEL_SID,' ELSE 'USER_ID,SESSION_ID,' END
                            + '
				PERIOD_SID
         FROM   ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'NM_ACTUAL_DISCOUNT' ELSE 'M_ACTUAL_DISCOUNT' END
                            + ' NSP
                INNER JOIN #TEMP_TABLE_SOURCE PD
                        ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID '
                            + CASE WHEN @FORECASTING_TYPE = 'MANDATED' THEN 'AND SAVE_FLAG = 1' ELSE '' END + ')
INSERT INTO ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'NM_ACTUAL_DISCOUNT' ELSE 'M_ACTUAL_DISCOUNT' END
                            + '
            (PROJECTION_DETAILS_SID,
             PERIOD_SID,' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'RS_MODEL_SID,' ELSE 'USER_ID,SESSION_ID,' END
                            + '
             ACTUAL_SALES,
		     ACTUAL_RATE)
SELECT T.PROJECTION_DETAILS_SID,
       T.PERIOD_SID,' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'COALESCE(S.RS_MODEL_SID, T.RS_MODEL_SID),' ELSE '1,1,' END + '
       ISNULL(S.ACTUAL_SALES,0),
	   ISNULL(S.ACTUAL_RATE,0)
FROM   TARGET_CCP_DETAILS T
       LEFT OUTER JOIN SOURCE_PROJECTION_DETAILS S
                    ON S.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                       AND T.PERIOD_SID = S.PERIOD_SID 
					   ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'AND T.RS_MODEL_SID=S.RS_MODEL_SID' ELSE '' END + '
'

          EXEC SP_EXECUTESQL
            @ACTUAL_SQL,
            N'@TARGET_PROJECTION_MASTER_SID INT,@SOURCE_PROJECTION_MASTER_SID INT,@ACTUAL_END_DATE DATETIME, @ACTUALS_START_DATE DATETIME',
            @TARGET_PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID,
            @SOURCE_PROJECTION_MASTER_SID = @SOURCE_PROJECTION_MASTER_SID,
            @ACTUAL_END_DATE = @ACTUAL_END_DATE,
            @ACTUALS_START_DATE = @ACTUALS_START_DATE

          ----------------ACTUAL DATA INSERT END
          ----------------PROJECTION DATA INSERT START 
          SET @PROJ_SQL =''
          SET @PROJ_SQL = '
;WITH TARGET_CCP_DETAILS
     AS (SELECT PROJECTION_DETAILS_SID,
                CCP_DETAILS_SID, ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'RS_MODEL_SID,' ELSE '' END
                          + '
                PERIOD_SID 
         FROM  ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN '#TARGET_CCP_DETAILS' ELSE 'PROJECTION_DETAILS' END + ' PD
                CROSS JOIN PERIOD P
         WHERE  P.PERIOD_DATE BETWEEN @PROJECTION_START_DATE AND @PROJECTION_END_DATE
                AND PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID),
     SOURCE_PROJECTION_DETAILS
     AS (SELECT CCP_DETAILS_SID,
                PROJECTION_SALES,
				PROJECTION_RATE,'
                          + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'DISCOUNT_RATE,RS_MODEL_SID,ADJUSTMENT_TYPE,ADJUSTMENT_BASIS,ADJUSTMENT_VALUE,ADJUSTMENT_METHODOLOGY,' ELSE 'USER_ID,SESSION_ID,' END + '
				PERIOD_SID
         FROM   ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'NM_DISCOUNT_PROJECTION' ELSE 'M_DISCOUNT_PROJECTION' END
                          + ' NSP
                INNER JOIN #TEMP_TABLE_SOURCE PD
                        ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID '
                          + CASE WHEN @FORECASTING_TYPE = 'MANDATED' THEN 'AND SAVE_FLAG = 1 ' ELSE '' END + ')
INSERT INTO ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'NM_DISCOUNT_PROJECTION' ELSE 'M_DISCOUNT_PROJECTION' END
                          + '
            (PROJECTION_DETAILS_SID,
             PERIOD_SID,' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'DISCOUNT_RATE,RS_MODEL_SID,ADJUSTMENT_TYPE,ADJUSTMENT_BASIS,ADJUSTMENT_VALUE,ADJUSTMENT_METHODOLOGY,' ELSE 'USER_ID,SESSION_ID,' END
                          + '
             PROJECTION_SALES,
		     PROJECTION_RATE)
SELECT T.PROJECTION_DETAILS_SID,
       T.PERIOD_SID,' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'DISCOUNT_RATE,COALESCE(S.RS_MODEL_SID, T.RS_MODEL_SID),ADJUSTMENT_TYPE,ADJUSTMENT_BASIS,ADJUSTMENT_VALUE,ADJUSTMENT_METHODOLOGY,' ELSE '1,1,' END + '
       ISNULL(S.PROJECTION_SALES,0),
	   ISNULL(S.PROJECTION_RATE,0)
FROM   TARGET_CCP_DETAILS T
       LEFT OUTER JOIN SOURCE_PROJECTION_DETAILS S
                    ON S.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                       AND T.PERIOD_SID = S.PERIOD_SID 
					   ' + CASE WHEN @FORECASTING_TYPE <> 'MANDATED' THEN 'AND T.RS_MODEL_SID=S.RS_MODEL_SID' ELSE '' END + '
'

          EXEC SP_EXECUTESQL
            @PROJ_SQL,
            N'@TARGET_PROJECTION_MASTER_SID INT,@SOURCE_PROJECTION_MASTER_SID INT,@PROJECTION_START_DATE DATETIME,@PROJECTION_END_DATE DATETIME',
            @TARGET_PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID,
            @SOURCE_PROJECTION_MASTER_SID = @SOURCE_PROJECTION_MASTER_SID,
            @PROJECTION_END_DATE = @PROJECTION_END_DATE,
            @PROJECTION_START_DATE = @PROJECTION_START_DATE

          ----------------PROJECTION DATA INSERT END--------------------------
          ---------------------------------------------------------DISCOUNT INSERT END------------------------------------------
          ---------------------------------------------------------PPA INSERT START------------------------------------------------
          -------------------------MASTER DATA INSERT START--------------
          IF @FORECASTING_TYPE <> 'MANDATED'
            BEGIN ;
                WITH CTE
                     AS (SELECT PROJECTION_DETAILS_SID,
                                CCP_DETAILS_SID,
                                ACTUAL_PRICE_CAP=PRICE_TOLERANCE,
                                PRICE_BASIS=PRICING_QUALIFIER,
                                PRICE_PROTECTION_START_DATE,
                                PRICE_PROTECTION_END_DATE
                         FROM   (SELECT PROJECTION_DETAILS_SID,
                                        CCP.CCP_DETAILS_SID,
                                        PRICE_TOLERANCE,
                                        PRICING_QUALIFIER,
                                        PRICE_PROTECTION_START_DATE,
                                        PRICE_PROTECTION_END_DATE,
                                        ROW_NUMBER()
                                          OVER(
                                            PARTITION BY PROJECTION_DETAILS_SID
                                            ORDER BY PROJECTION_DETAILS_SID)RN
                                 FROM   @TEMP_TABLE CCP
                                        JOIN CFP_CONTRACT C1
                                          ON CCP.CONTRACT_MASTER_SID = C1.CONTRACT_MASTER_SID
                                        JOIN CFP_CONTRACT_DETAILS C2
                                          ON C1.CFP_CONTRACT_SID = C2.CFP_CONTRACT_SID
                                             AND C2.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID
                                        JOIN IFP_CONTRACT I1
                                          ON I1.CFP_CONTRACT_SID = C1.CFP_CONTRACT_SID
                                             AND I1.CONTRACT_MASTER_SID = C1.CONTRACT_MASTER_SID
                                        JOIN IFP_CONTRACT_DETAILS I2
                                          ON I2.IFP_CONTRACT_SID = I1.IFP_CONTRACT_SID
                                             AND CCP.ITEM_MASTER_SID = I2.ITEM_MASTER_SID
                                        JOIN PS_CONTRACT PSC
                                          ON PSC.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID
                                             AND PSC.CFP_CONTRACT_SID = C1.CFP_CONTRACT_SID
                                             AND I1.IFP_CONTRACT_SID = PSC.IFP_CONTRACT_SID
                                        JOIN PS_CONTRACT_DETAILS PSCD
                                          ON PSCD.PS_CONTRACT_SID = PSC.PS_CONTRACT_SID
                                             AND PSCD.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID
                                        JOIN ITEM_PRICING_QUALIFIER IPQ
                                          ON PSCD.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                                 WHERE  ( ( @PROJECTION_START_DATE BETWEEN PRICE_PROTECTION_START_DATE AND PRICE_PROTECTION_END_DATE )
                                           OR ( @PROJECTION_END_DATE >= PRICE_PROTECTION_START_DATE
                                                AND PRICE_PROTECTION_END_DATE IS NULL
                                                AND PRICE_PROTECTION_START_DATE <= @PROJECTION_END_DATE )
                                           OR ( PRICE_PROTECTION_START_DATE >= @PROJECTION_START_DATE
                                                AND PRICE_PROTECTION_START_DATE <= @PROJECTION_END_DATE ) ))A
                         WHERE  RN = 1)
                INSERT INTO NM_PPA_PROJECTION_MASTER
                            (PROJECTION_DETAILS_SID,
                             ACTUAL_PRICE_CAP,
                             PRICE_BASIS,
                             PRICE_PROTECTION_START_DATE,
                             PRICE_PROTECTION_END_DATE,
                             CHECK_RECORD,
                             USER_GROUP)
                SELECT PROJECTION_DETAILS_SID,
                       ACTUAL_PRICE_CAP,
                       PRICE_BASIS,
                       PRICE_PROTECTION_START_DATE,
                       PRICE_PROTECTION_END_DATE,
                       0,
                       '0'
                FROM   CTE
                -------------------------MASTER DATA INSERT END--------------
                ----------------------------ACTUAL DATA INSERT START------------------------
                ;

                WITH TARGET_CCP_DETAILS
                     AS (SELECT PROJECTION_MASTER_SID,
                                PROJECTION_DETAILS_SID,
                                CCP_DETAILS_SID,
                                PERIOD_SID
                         FROM   PROJECTION_DETAILS PD
                                CROSS JOIN PERIOD P
                         WHERE  P.PERIOD_DATE BETWEEN @ACTUALS_START_DATE AND @ACTUAL_END_DATE
                                AND PROJECTION_MASTER_SID = @TARGET_PROJECTION_MASTER_SID),
                     SOURCE_PROJECTION_DETAILS
                     AS (SELECT CCP_DETAILS_SID,
                                ACTUAL_SALES,
                                ACTUAL_RATE,
                                ACTUAL_DISCOUNT_DOLLAR,
                                ACTUAL_DISCOUNT_UNITS,
                                PERIOD_SID
                         FROM   NM_ACTUAL_PPA NSP
                                INNER JOIN #TEMP_TABLE_SOURCE PD
                                        ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID)
                INSERT INTO NM_ACTUAL_PPA
                            (PROJECTION_DETAILS_SID,
                             PERIOD_SID,
                             ACTUAL_SALES,
                             ACTUAL_RATE,
                             ACTUAL_DISCOUNT_DOLLAR,
                             ACTUAL_DISCOUNT_UNITS)
                SELECT T.PROJECTION_DETAILS_SID,
                       T.PERIOD_SID,
                       ISNULL(S.ACTUAL_SALES, 0),
                       ISNULL(S.ACTUAL_RATE, 0),
                       ACTUAL_DISCOUNT_DOLLAR,
                       ACTUAL_DISCOUNT_UNITS
                FROM   TARGET_CCP_DETAILS T
                       LEFT OUTER JOIN SOURCE_PROJECTION_DETAILS S
                                    ON S.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                                       AND T.PERIOD_SID = S.PERIOD_SID
                ----------------------------ACTUAL DATA INSERT END------------------------
                ----------------------------PROJECTION DATA INSERT START------------------------
                ;

                WITH TARGET_CCP_DETAILS
                     AS (SELECT PROJECTION_MASTER_SID,
                                PROJECTION_DETAILS_SID,
                                CCP_DETAILS_SID,
                                PERIOD_SID
                         FROM   @TEMP_TABLE PD
                                CROSS JOIN PERIOD P
                         WHERE  P.PERIOD_DATE BETWEEN @PROJECTION_START_DATE AND @PROJECTION_END_DATE),
                     SOURCE_PROJECTION_DETAILS
                     AS (SELECT CCP_DETAILS_SID,
                                PRICE_CAP,
                                RESET_PRICE_CAP,
                                PROJECTION_MAP,
                                PROJECTION_RATE,
                                PROJECTION_SALES,
                                PROJECTION_DISCOUNT_DOLLAR,
                                PROJECTION_DISCOUNT_UNITS,
                                PERIOD_SID
                         FROM   NM_PPA_PROJECTION NSP
                                INNER JOIN #TEMP_TABLE_SOURCE PD
                                        ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID)
                INSERT INTO NM_PPA_PROJECTION
                            (PROJECTION_DETAILS_SID,
                             PERIOD_SID,
                             PRICE_CAP,
                             RESET_PRICE_CAP,
                             PROJECTION_MAP,
                             PROJECTION_RATE,
                             PROJECTION_SALES,
                             PROJECTION_DISCOUNT_DOLLAR,
                             PROJECTION_DISCOUNT_UNITS)
                SELECT T.PROJECTION_DETAILS_SID,
                       T.PERIOD_SID,
                       PRICE_CAP,
                       RESET_PRICE_CAP,
                       ISNULL(PROJECTION_MAP, 0),
                       ISNULL(PROJECTION_RATE, 0),
                       ISNULL(PROJECTION_SALES, 0),
                       ISNULL(PROJECTION_DISCOUNT_DOLLAR, 0),
                       ISNULL(PROJECTION_DISCOUNT_UNITS, 0)
                FROM   TARGET_CCP_DETAILS T
                       LEFT OUTER JOIN SOURCE_PROJECTION_DETAILS S
                                    ON S.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                                       AND T.PERIOD_SID = S.PERIOD_SID
            ----------------------------PROJECTION DATA INSERT END---------------------------
            ---------------------------------------------------------PPA INSERT END------------------------------------------------
            END
          ELSE
            BEGIN
                INSERT INTO M_SUPPLEMENTAL_DISC_MASTER
                            (PROJECTION_DETAILS_SID,
                             MARKET_TYPE,
                             ACTUAL_DISCOUNT,
                             CASH_PAID_DATE,
                             CONTRACT_END_DATE)
                SELECT CCP.PROJECTION_DETAILS_SID,
                       @MARKET_TYPE,
                       AM.AMOUNT,
                       'Q'
                       + Cast(Datepart(QQ, AM.ACCRUAL_ACTUAL_START_DATE) AS CHAR(1))
                       + ' '
                       + Cast(Datepart(YY, AM.ACCRUAL_ACTUAL_START_DATE) AS CHAR(4)) AS CASH_PAID_DATE,
                       CNT_M.END_DATE
                FROM   @TEMP_TABLE CCP
                       JOIN CONTRACT_MASTER CNT_M
                         ON CCP.CONTRACT_MASTER_SID = CNT_M.CONTRACT_MASTER_SID
                       JOIN COMPANY_MASTER CM
                         ON CCP.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID
                       JOIN ITEM_MASTER IM
                         ON CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                       OUTER APPLY(SELECT TOP 1 AM.AMOUNT,--LATEST CASH_PAID_DATE OF A CCP FOR SUPP DISC(OUTPUT: AMOUNT,ACCRUAL_ACTUAL_START_DATE) 
                                                AM.ACCRUAL_ACTUAL_START_DATE
                                   FROM   ACTUALS_MASTER AM
                                          JOIN RS_CONTRACT RS_C
                                            ON AM.PROVISION_ID = RS_C.RS_ID
                                   WHERE  AM.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID
                                          AND AM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID
                                          AND AM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID
                                          AND RS_C.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID
                                          AND RS_C.RS_CATEGORY = @V
                                   ORDER  BY CASH_PAID_DATE DESC) AM
                WHERE  CNT_M.INBOUND_STATUS <> 'D'
                       AND CM.INBOUND_STATUS <> 'D'
                       AND IM.INBOUND_STATUS <> 'D';

                WITH TARGET_CCP_DETAILS
                     AS (SELECT PROJECTION_MASTER_SID,
                                PROJECTION_DETAILS_SID,
                                CCP_DETAILS_SID,
                                PERIOD_SID
                         FROM   @TEMP_TABLE PD
                                CROSS JOIN PERIOD P
                         WHERE  P.PERIOD_DATE BETWEEN @ACTUALS_START_DATE AND @ACTUAL_END_DATE),
                     SOURCE_PROJECTION_DETAILS
                     AS (SELECT CCP_DETAILS_SID,
                                ACTUAL_SALES,
                                ACTUAL_RATE,
                                ACTUAL_PROJECTION_SALES,
                                ACTUAL_PROJECTION_RATE,
                                PERIOD_SID
                         FROM   M_SUPPLEMENTAL_DISC_ACTUALS NSP
                                INNER JOIN #TEMP_TABLE_SOURCE PD
                                        ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID)
                INSERT INTO M_SUPPLEMENTAL_DISC_ACTUALS
                            (PROJECTION_DETAILS_SID,
                             PERIOD_SID,
                             ACTUAL_SALES,
                             ACTUAL_RATE)
                SELECT T.PROJECTION_DETAILS_SID,
                       T.PERIOD_SID,
                       ISNULL(S.ACTUAL_SALES, 0),
                       ISNULL(S.ACTUAL_RATE, 0)
                FROM   TARGET_CCP_DETAILS T
                       LEFT OUTER JOIN SOURCE_PROJECTION_DETAILS S
                                    ON S.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                                       AND T.PERIOD_SID = S.PERIOD_SID;

            ;
                WITH TARGET_CCP_DETAILS
                     AS (SELECT PROJECTION_MASTER_SID,
                                PROJECTION_DETAILS_SID,
                                CCP_DETAILS_SID,
                                PERIOD_SID,
                                CONTRACT_MASTER_SID,
                                COMPANY_MASTER_SID,
                                ITEM_MASTER_SID,
                                PERIOD_DATE
                         FROM   @TEMP_TABLE
                                CROSS JOIN PERIOD P
                         WHERE  P.PERIOD_DATE BETWEEN @PROJECTION_START_DATE AND @PROJECTION_END_DATE),
                     SOURCE_PROJECTION_DETAILS
                     AS (SELECT CCP_DETAILS_SID,
                                METHODOLOGY,
                                CONTRACT_PRICE,
                                DISCOUNT_RATE_1,
                                DISCOUNT_RATE_2,
                                ACCESS,
                                PARITY,
                                PARITY_DISCOUNT,
                                PARITY_REFERENCE,
                                PROJECTION_RATE,
                                PROJECTION_SALES,
                                PERIOD_SID
                         FROM   M_SUPPLEMENTAL_DISC_PROJ NSP
                                INNER JOIN #TEMP_TABLE_SOURCE PD
                                        ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID)
                INSERT INTO M_SUPPLEMENTAL_DISC_PROJ
                            (PROJECTION_DETAILS_SID,
                             PERIOD_SID,
                             METHODOLOGY,
                             CONTRACT_PRICE,
                             DISCOUNT_RATE_1,
                             DISCOUNT_RATE_2,
                             ACCESS,
                             PARITY,
                             PARITY_DISCOUNT,
                             PARITY_REFERENCE,
                             PROJECTION_RATE,
                             PROJECTION_SALES)
                SELECT T.PROJECTION_DETAILS_SID,
                       T.PERIOD_SID,
                       COALESCE(S.METHODOLOGY, FM.FORMULA_DESC),
                       COALESCE(S.CONTRACT_PRICE, FM.CONTRACT_PRICE_1),
                       COALESCE(S.DISCOUNT_RATE_1, FM.REBATE_PERCENT_1),
                       COALESCE(S.DISCOUNT_RATE_2, FM.REBATE_PERCENT_2),
                       S.ACCESS,
                       S.PARITY,
                       S.PARITY_DISCOUNT,
                       S.PARITY_REFERENCE,
                       ISNULL(S.PROJECTION_RATE, 0),
                       ISNULL(S.PROJECTION_SALES, 0)
                FROM   TARGET_CCP_DETAILS T
                       INNER JOIN COMPANY_MASTER CM
                               ON CM.COMPANY_MASTER_SID = T.COMPANY_MASTER_SID
                       INNER JOIN ITEM_MASTER IM
                               ON IM.ITEM_MASTER_SID = T.ITEM_MASTER_SID
                       LEFT OUTER JOIN SOURCE_PROJECTION_DETAILS S
                                    ON S.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                                       AND T.PERIOD_SID = S.PERIOD_SID
                       OUTER APPLY (SELECT TOP 1 FM.FORMULA_DESC,
                                                 FM.CONTRACT_PRICE_1,
                                                 FM.REBATE_PERCENT_1,
                                                 FM.REBATE_PERCENT_2
                                    FROM   RS_CONTRACT RS_C
                                           JOIN RS_CONTRACT_DETAILS RS_D
                                             ON RS_C.RS_CONTRACT_SID = RS_D.RS_CONTRACT_SID
                                                AND RS_C.RS_CATEGORY = @V -- TO IDENTIFY ONLY SUPPLEMENTAL DISCOUNT
                                           JOIN RS_CONTRACT_DETAILS_FR RS_D_FR
                                             ON RS_D.RS_CONTRACT_DETAILS_SID = RS_D_FR.RS_CONTRACT_DETAILS_SID
                                           JOIN FORMULA_DETAILS_MASTER FM
                                             ON FM.FORMULA_ID = RS_D_FR.FORMULA_ID
                                                AND FM.COMPANY_ID = CM.COMPANY_ID
                                                AND FM.ITEM_ID = IM.ITEM_ID
                                    WHERE  RS_C.CONTRACT_MASTER_SID = T.CONTRACT_MASTER_SID
                                           AND FM.INBOUND_STATUS <> 'D'
                                           AND RS_D_FR.INBOUND_STATUS <> 'D'
                                           AND ( START_DATE = T.PERIOD_DATE
                                                  OR START_DATE <= T.PERIOD_DATE )
                                           AND ( END_DATE >= Dateadd(D, -1, Dateadd(M, Datediff(M, 0, T.PERIOD_DATE) + 1, 0))
                                                  OR ISNULL(END_DATE, 1) = 1 ) --LAST DAY OF THE MONTH + END_DATE NULL HANDLING.
                                   )FM
            END
      END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC USPERRORCOLLECTOR

          SELECT @ERRORMESSAGE = ERROR_MESSAGE(),
                 @ERRORSEVERITY = ERROR_SEVERITY(),
                 @ERRORSTATE = ERROR_STATE(),
                 @ERRORPROCEDURE = ERROR_PROCEDURE(),
                 @ERRORLINE = ERROR_LINE(),
                 @ERRORNUMBER = ERROR_NUMBER()

          RAISERROR (@ERRORMESSAGE,-- MESSAGE TEXT.
                     @ERRORSEVERITY,-- SEVERITY.
                     @ERRORSTATE,-- STATE.
                     @ERRORPROCEDURE,-- PROCEDURE_NAME.
                     @ERRORNUMBER,-- ERRORNUMBER
                     @ERRORLINE -- ERRORLINE
          )
      END CATCH
  END 
