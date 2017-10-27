IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NM_PPA_PROJ_INIT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].PRC_NM_PPA_PROJ_INIT
  END

GO

CREATE PROCEDURE [dbo].[PRC_NM_PPA_PROJ_INIT] (@PROJECTION_MASTER_SID INT,
                                               @USER_ID               VARCHAR(100),
                                               @SESSION_ID            VARCHAR(100))
AS
  BEGIN
      SET NOCOUNT ON

  /**********************************************************************************************************
  ** FILE NAME		:	PRC_NM_PPA_PROJ_INIT.SQL
  ** PROCEDURE NAME	:	PRC_NM_PPA_PROJ_INIT
  ** DESCRIPTION		:	this procedure will calculate the ppa discount for the ccp's having no netting logic for all reset types
  ** INPUT PARAMETERS	:	@PROJECTION_SID	  - passing input as PROJECTION_MASTER_SID
  				@USER_ID          - user id for the particular projection 
  				@SESSION_ID       - session id for the particular projection 
  				                        
  ** OUTPUT PARAMETERS:	na
  ** AUTHOR NAME		:	@pradeepthanga
  ** CREATION DATE	:	 
  ** CALLED BY		:   This procedure will be called from UI after hitting calculate button in sales projection screen.
  **********************************************************************************************************
  ** CHANGE HISTORY
  **********************************************************************************************************
  ** VER   DATE       LOCAL ALMTICKET NO      SUBTICKET NO        ONLINE ALM TICKET      AUTHOR                              DESCRIPTION 
  ** ---   --------      ---------             -------------        -------------     ----------------                       ------------------
  ** 1                                                                                   @pradeepthanga and @SandeepKumar.A  inorder to improve the performance the existing procedure was splitted into two one was this one and other was prc_nm_ppa_projeciton.
  ** 2                                                                                    @pradeepthanga						removed the recursive cte which were effecting the performance and implement the logic using the cross joins
  *****************************************************************************************************/
      --------------------------- VARIABLES DECLARATION SATRTS HERE-------------------
      DECLARE @ROWCOUNT INT = 0
      DECLARE @PROJ_START_DATE DATETIME
      DECLARE @PROJ_END_DATE               DATETIME,
              @PRICE_TYPES                 VARCHAR(MAX),
              @PROJ_START_DATE_SID         INT,
              @PROJ_END_DATE_SID           INT,
              @LOOPCOUNTER                 INT = 1,
              @PRICE_BASIS                 VARCHAR(50),
              @CALCULATION_START_DATE_SID  INT,
              @CALCULATION_END_DATE_SID    INT,
              @PRICE_PROTECTION_STATUS     VARCHAR(50),
              @FORMULA                     VARCHAR(MAX),
              @SQL                         NVARCHAR(MAX),
              @COMPANY_ID                  VARCHAR(50),
              @CAL_START_DATE              DATETIME,
              @CAL_END_DATE                DATETIME,
              @PROJECTION_DETAILS_SID      INT,
              @USER_GROUP                  VARCHAR(50),
              @PRICE_PROTECTION_START_DATE DATETIME,
              @PRICE_PROTECTION_END_DATE   DATETIME,
              @ITEM_MASTER_SID             INT,
              @CONTRACT_SID                INT,
              @BUSINESS_UNIT               INT ------------------GAL-808
              ,
              @COMPANY_SID                 INT ------------------GAL-808
              ,
              @ITEM_UOM                    VARCHAR(50) = 'EACH',
              @REBATE_FREQUENCY            VARCHAR(100),
              @RS_MODEL_SID                INT,
              @FREQUENCY                   VARCHAR(10),
              @FORECAST_VERSION            VARCHAR(10),
              @FORECAST_NAME               VARCHAR(1000),
              @REBATE_INCR                 INT,
              @REBATE_ROLLOUT              INT,
              @RESET_PRICE_TYPE            NUMERIC(22, 6),
              @FROM_DATE                   DATETIME,
              @TO_DATE                     DATETIME,
              @ACTUAL_START_DATE           DATE,
              @CURRENT_DATE                DATE,
              @CURRENT_PERIOD              INT,
              @ACTUAL_PERIOD_SID           INT,
              @PROJ_START_PERIOD_SID       INT,
              @PROJ_END_PERIOD_SID         INT,
              @END_DATE_SID                INT,
              @MAP                         NUMERIC(22, 6),
              @WAC                         NUMERIC(22, 6),
              @MAX_RN                      INT,
              @NEW_WAC                     NUMERIC(22, 6),
              @ITEMID                      [DBO].[UDT_ITEM],
              @PRODUCT_TABLE               VARCHAR(200) = Concat ('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
      DECLARE @D_PROJECTION_TABLE   VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @D_SQL                NVARCHAR(MAX),
              @PROJECTION_TABLE     VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @MASTER_TABLE         VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @ACTUAL_TABLE         VARCHAR(200) = Concat ('ST_NM_ACTUAL_PPA_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @S_MASTER_TABLE       VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @S_PROJECTION_TABLE   VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @CCP_HIERARCHY        VARCHAR(200) = Concat ('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @NETTING_LOGIC_CCPS   VARCHAR(200) = Concat ('ST_NETTING_LOGIC_CCPS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @ST_NM_PPA_SETUP      VARCHAR(200) = Concat ('ST_NM_PPA_SETUP_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @CONTRACT_SETUP       VARCHAR(200) = Concat ('ST_CONTRACT_SETUP_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @ST_PPA_WAC_PRICE     VARCHAR(200) = Concat ('ST_PPA_WAC_PRICE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @ST_PPA_RESET_PERIODS VARCHAR(200) = Concat ('ST_PPA_RESET_PERIODS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

      BEGIN
          SELECT @FROM_DATE = FROM_DATE,
                 @TO_DATE = TO_DATE
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
      END

      ----------------------- VARIABLES DECLARATION  DATE ENDS HERE--------------------
      IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
        DROP TABLE #PERIOD;

      SELECT PERIOD_SID,
             PERIOD_DATE,
             PERIOD_MONTH = MONTH,
             PERIOD_QUARTER = QUARTER,
             PERIOD_SEMI = SEMI_ANNUAL,
             PERIOD_YEAR = YEAR,
             MPERIOD = Concat ('M', MONTH, ' ', YEAR),
             QPERIOD = Concat ('Q', QUARTER, ' ', YEAR),
             SPERIOD = Concat ('S', SEMI_ANNUAL, ' ', YEAR),
             YPERIOD = Cast(YEAR AS CHAR(4))
      INTO   #PERIOD
      FROM   PERIOD

      SET @ACTUAL_START_DATE = Dateadd(YY, Datediff(YY, 0, Getdate()) - 3, 0) -- FIRST DAY OF -3 YEARS
      SET @CURRENT_DATE = Dateadd(MM, Datediff(MM, 0, Getdate()), 0)

      ---------------------taking the ccp and projection information from respective tables--------------------------
      IF Object_id('TEMPDB.DBO.#PROJECTION_DATES', 'U') IS NOT NULL
        DROP TABLE #PROJECTION_DATES;

      CREATE TABLE #PROJECTION_DATES
        (
           CCP_DETAILS_SID     INT,
           CONTRACT_MASTER_SID INT,
           COMPANY_MASTER_SID  INT,
           ITEM_MASTER_SID     INT,
           PROJ_START_DATE     DATETIME,
           PROJ_END_DATE       DATETIME
        )

      BEGIN
          SET @SQL = Concat ('  INSERT INTO #PROJECTION_DATES
		  (
		  
		  CCP_DETAILS_SID        ,
		  CONTRACT_MASTER_SID    ,
		  COMPANY_MASTER_SID     ,
		  ITEM_MASTER_SID        ,
		  PROJ_START_DATE        ,
		  PROJ_END_DATE          
		  )

          SELECT DISTINCT 
                 CD.CCP_DETAILS_SID, 
                 CD.CONTRACT_MASTER_SID, 
                 CD.COMPANY_MASTER_SID, 
                 CD.ITEM_MASTER_SID, 
                 CASE 
                   WHEN LEFT(CALCULATION_PERIODS, 1) = ''M'' 
                         OR CALCULATION_PERIODS IS NULL THEN DATEADD(MM, DATEDIFF(MM, 0, @FROM_DATE), 0) 
                   WHEN LEFT(CALCULATION_PERIODS, 1) = ''Q'' THEN DATEADD(QQ, DATEDIFF(QQ, 0, @FROM_DATE), 0) 
                   WHEN LEFT(CALCULATION_PERIODS, 1) = ''S'' THEN 
                     CASE 
                       WHEN DATEPART(QQ, @FROM_DATE) IN ( 2, 4 ) THEN DATEADD(QQ, DATEDIFF(QQ, 0, @FROM_DATE) - 1, 0) 
                       ELSE DATEADD(QQ, DATEDIFF(QQ, 0, @FROM_DATE), 0) 
                     END 
                   ELSE DATEADD(YYYY, DATEDIFF(YYYY, 0, @FROM_DATE), 0) 
                 END PROJ_START_DATE, 
                 CASE 
                   WHEN LEFT(CALCULATION_PERIODS, 1) = ''M'' THEN DATEADD(MM, DATEDIFF(MM, 0, @TO_DATE) + 1, 0) - 1 
                   WHEN LEFT(CALCULATION_PERIODS, 1) = ''Q'' THEN DATEADD(QQ, DATEDIFF(QQ, 0, @TO_DATE) + 1, 0) - 1 
                   WHEN LEFT(CALCULATION_PERIODS, 1) = ''S'' THEN 
                     CASE 
                       WHEN DATEPART(QQ, @TO_DATE) IN ( 2, 4 ) THEN DATEADD(QQ, DATEDIFF(QQ, 0, @TO_DATE) + 1, 0) - 1 
                       ELSE DATEADD(QQ, DATEDIFF(QQ, 0, @TO_DATE) + 2, 0) - 1 
                     END 
                   ELSE DATEADD(YYYY, DATEDIFF(YYYY, 0, @TO_DATE) + 1, 0) - 1 
                 END  PROJ_END_DATE 
        
          FROM  CCP_DETAILS CD JOIN ', @S_MASTER_TABLE, ' S ON S.CCP_DETAILS_SID=CD.CCP_DETAILS_SID
                     WHERE EXISTS (
				   SELECT 1 FROM ', @MASTER_TABLE, ' S WHERE CD.CCP_DETAILS_SID = S.CCP_DETAILS_SID)
				
				   ', 'AND EXISTS (
				  SELECT 1 FROM ', @PROJECTION_TABLE, ' S
				  LEFT JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=S.NET_BASE_PRICE
				  LEFT JOIN HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID=S.NET_SUBSEQUENT_PERIOD_PRICE
				  LEFT JOIN HELPER_TABLE HT2 ON HT2.HELPER_TABLE_SID=S.NET_RESET_PRICE_TYPE
				  LEFT JOIN HELPER_TABLE HT3 ON HT3.HELPER_TABLE_SID=S.NET_PRICE_TYPE
				  LEFT JOIN HELPER_TABLE HT4 ON HT4.HELPER_TABLE_SID=S.RESET_ELIGIBLE 
				  LEFT JOIN HELPER_TABLE HT5 ON HT5.HELPER_TABLE_SID=S.RESET_TYPE
				   WHERE CD.CCP_DETAILS_SID = S.CCP_DETAILS_SID )')
      END

      EXEC Sp_executesql
        @SQL,
        N'@FROM_DATE DATETIME,@TO_DATE DATETIME',
        @FROM_DATE = @FROM_DATE,
        @TO_DATE = @TO_DATE

      -------------------------FETCH DATA FROM ST_NM_PPA_PROJECTION_MASTER FOR A GIVEN PROJECTION_ID STARTS HERE-----------------
      IF Object_id('TEMPDB..#TEMP_NM_PPA_PROJECTION_MASTER') IS NOT NULL
        DROP TABLE #TEMP_NM_PPA_PROJECTION_MASTER

      CREATE TABLE #TEMP_NM_PPA_PROJECTION_MASTER
        (
           RN                          INT
           --,PROJECTION_DETAILS_SID INT
           ,
           CCP_DETAILS_SID             INT,
           RS_CONTRACT_SID             INT,
           CONTRACT_MASTER_SID         INT,
           COMPANY_MASTER_SID          INT,
           ITEM_MASTER_SID             INT,
           USER_GROUP                  VARCHAR(50),
           PRICE_PROTECTION_START_DATE DATETIME,
           PRICE_PROTECTION_END_DATE   DATETIME,
           PRICE_PROTECTION_STATUS     INT,
           CAL_START_DATE              DATE,
           CAL_END_DATE                DATE,
           REBATE_FREQUENCY            VARCHAR(100)
        );

      SET @SQL = Concat ('  INSERT INTO #TEMP_NM_PPA_PROJECTION_MASTER 
                      (RN,
					  CCP_DETAILS_SID, 
                       CONTRACT_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       ITEM_MASTER_SID, 
                       USER_GROUP, 
                       PRICE_PROTECTION_START_DATE, 
                       PRICE_PROTECTION_END_DATE, 
                       PRICE_PROTECTION_STATUS, 
                       CAL_START_DATE, 
                       CAL_END_DATE, 
                       REBATE_FREQUENCY, 
                       RS_CONTRACT_SID) 
          SELECT ROW_NUMBER () OVER (ORDER BY A.CCP_DETAILS_SID, A.RS_CONTRACT_SID), 
                 A.CCP_DETAILS_SID, 
                 C.CONTRACT_MASTER_SID, 
                 C.COMPANY_MASTER_SID, 
                 C.ITEM_MASTER_SID, 
                 A.USER_GROUP, 
                 A.PRICE_PROTECTION_START_DATE, 
                 A.PRICE_PROTECTION_END_DATE, 
                 A.PRICE_PROTECTION_STATUS, 
                 IIF(PROJ_START_DATE > PRICE_PROTECTION_START_DATE, PROJ_START_DATE, PRICE_PROTECTION_START_DATE) AS CAL_START_DATE 
                 , 
                 IIF(PROJ_END_DATE < PRICE_PROTECTION_END_DATE, PROJ_END_DATE, PRICE_PROTECTION_END_DATE) AS CAL_END_DATE 
                 , 
                 HT.DESCRIPTION, 
                 A.RS_CONTRACT_SID 
          FROM   ', @MASTER_TABLE, ' A 
                 JOIN CCP_DETAILS C 
                   ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID 
                 LEFT JOIN HELPER_TABLE HT 
                        ON HT.HELPER_TABLE_SID = A.REBATE_FREQUENCY 
                 JOIN #PROJECTION_DATES P_DATES 
                        ON P_DATES.CCP_DETAILS_SID = A.CCP_DETAILS_SID 
          ')

      EXEC (@SQL)

      -----------------------fetching prices from the different tables like history from item master and future periods from product tables----------------------
      SELECT @ACTUAL_PERIOD_SID = Max(CASE
                                        WHEN @ACTUAL_START_DATE = PERIOD_DATE THEN PERIOD_SID
                                      END),
             @CURRENT_PERIOD = Max(CASE
                                     WHEN @CURRENT_DATE = PERIOD_DATE THEN PERIOD_SID
                                   END)
      FROM   #PERIOD
      WHERE  PERIOD_DATE IN ( @ACTUAL_START_DATE, @PROJ_START_DATE, @CURRENT_DATE )

      SELECT @PROJ_START_PERIOD_SID = (SELECT PERIOD_SID
                                       FROM   #PERIOD
                                       WHERE  PERIOD_DATE = Min(Dateadd(MM, Datediff(MM, 0, PRICE_PROTECTION_START_DATE), 0))),
             @PROJ_END_PERIOD_SID = (SELECT PERIOD_SID
                                     FROM   #PERIOD
                                     WHERE  PERIOD_DATE = Max(Dateadd(MM, Datediff(MM, 0, PRICE_PROTECTION_END_DATE), 0)))
      FROM   #TEMP_NM_PPA_PROJECTION_MASTER T

      SELECT @BUSINESS_UNIT = BUSINESS_UNIT,
             @COMPANY_SID = COMPANY_MASTER_SID
      FROM   PROJECTION_MASTER PM
      WHERE  PM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

      INSERT INTO @ITEMID
      SELECT DISTINCT ITEM_MASTER_SID
      FROM   #TEMP_NM_PPA_PROJECTION_MASTER

      ------------------history price information for the items in the projection which were eligible for ppa discount-----------
      IF Object_id('TEMPDB.DBO.#UDF_ITEM_PRICING', 'U') IS NOT NULL
        DROP TABLE #UDF_ITEM_PRICING;

      SELECT *
      INTO   #UDF_ITEM_PRICING
      FROM   DBO.Udf_item_pricing(@ITEMID, 'BQWAC,AVGQWAC,MQWAC,EQWAC,WAC', Iif(@ACTUAL_PERIOD_SID > @PROJ_START_PERIOD_SID, @PROJ_START_PERIOD_SID, @ACTUAL_PERIOD_SID), @CURRENT_PERIOD, @ITEM_UOM)

      ------------------forecast price information for the items in the projection which were eligible for ppa discount-----------
      IF Object_id('TEMPDB.DBO.#GTS_WAC', 'U') IS NOT NULL
        DROP TABLE #GTS_WAC;

      CREATE TABLE #GTS_WAC
        (
           ITEM_MASTER_SID    INT,
           PERIOD_SID         INT,
           ACTUAL_GTS_SALES   NUMERIC(38, 15),
           ACTUAL_GTS_UNITS   NUMERIC(38, 15),
           FORECAST_GTS_SALES NUMERIC(38, 15),
           FORECAST_GTS_UNITS NUMERIC(38, 15),
           ACTUAL_PRICE       NUMERIC(38, 15),
           FORECAST_PRICE     NUMERIC(38, 15),
           ITEM_PRICE         NUMERIC(38, 15),
           WAC_PRICE          NUMERIC(38, 15)
           --,F_DAY_WEIGHTED_WAC_PRICE NUMERIC(38, 15)
           --,A_DAY_WEIGHTED_WAC_PRICE NUMERIC(38, 15)
           --,NO_OF_DAYS INT
           ,
           MONTH              INT,
           QUARTER            INT,
           SEMI_ANNUAL        INT,
           YEAR               INT,
           PRIMARY KEY ( ITEM_MASTER_SID, PERIOD_SID )
        )

      SET @SQL = Concat ('INSERT INTO #GTS_WAC (
			ITEM_MASTER_SID
			,PERIOD_SID
			,ACTUAL_GTS_SALES
			,ACTUAL_GTS_UNITS
			,FORECAST_GTS_SALES
			,FORECAST_GTS_UNITS
			,ACTUAL_PRICE
			,FORECAST_PRICE
			,ITEM_PRICE
			,WAC_PRICE
			--,F_DAY_WEIGHTED_WAC_PRICE
			--,A_DAY_WEIGHTED_WAC_PRICE
			--,NO_OF_DAYS
			,MONTH
			,QUARTER
			,SEMI_ANNUAL
			,YEAR
			)
		SELECT GTS.ITEM_MASTER_SID
			,GTS.PERIOD_SID
			,EXFACTORY_ACTUAL_SALES
			,EXFACTORY_ACTUAL_UNITS
			,EXFACTORY_FORECAST_SALES
			,EXFACTORY_FORECAST_UNITS			
			,EXFACTORY_ACTUAL_SALES/NULLIF(EXFACTORY_ACTUAL_UNITS,0) AS ACTUAL_PRICE
			,EXFACTORY_FORECAST_SALES/NULLIF(EXFACTORY_FORECAST_UNITS,0) AS FORECAST_PRICE
			,IP.ITEM_PRICE
			,COALESCE(EXFACTORY_FORECAST_SALES/NULLIF(EXFACTORY_FORECAST_UNITS,0) , IP.ITEM_PRICE) WAC_PRICE
			--,FORECAST_PRICE * DAY(DATEADD(DAY, - DAY(DATEADD(MONTH, 1, PERIOD_DATE)), DATEADD(MONTH, 1, PERIOD_DATE))) F_DAY_WEIGHTED_WAC_PRICE
			--,IP.ITEM_PRICE * DAY(DATEADD(DAY, - DAY(DATEADD(MONTH, 1, PERIOD_DATE)), DATEADD(MONTH, 1, PERIOD_DATE))) A_DAY_WEIGHTED_WAC_PRICE
			--,DAY(DATEADD(DAY, - DAY(DATEADD(MONTH, 1, PERIOD_DATE)), DATEADD(MONTH, 1, PERIOD_DATE))) NO_OF_DAYS
			,P.PERIOD_MONTH
			,P.PERIOD_QUARTER
			,P.PERIOD_SEMI
			,P.PERIOD_YEAR
		FROM ', @PRODUCT_TABLE, ' GTS
		LEFT OUTER JOIN #UDF_ITEM_PRICING IP ON GTS.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
			AND GTS.PERIOD_SID = IP.PERIOD_SID
			AND IP.PRICING_QUALIFIER = ''WAC''
		JOIN #PERIOD P ON P.PERIOD_SID = GTS.PERIOD_SID')

      EXEC Sp_executesql
        @SQL,
        N'@REBATE_FREQUENCY CHAR(1)',
        @REBATE_FREQUENCY = @REBATE_FREQUENCY

      -------------identification of BQWAC,EQWAC,MQWAC,AVQWAC AND WAC from the above tables for all the periods-----------------------------------------------------------------
      IF Object_id('TEMPDB.DBO.#ITEM_WAC_PRICES', 'U') IS NOT NULL
        DROP TABLE #ITEM_WAC_PRICES;

  ;
      WITH FORECAST_PRICE
           AS (SELECT ITEM_MASTER_SID,
                      Max(CASE
                            WHEN MONTH IN ( 1, 4, 7, 10 ) THEN FORECAST_GTS_SALES
                          END) / NULLIF(Max(CASE
                                              WHEN MONTH IN ( 1, 4, 7, 10 ) THEN FORECAST_GTS_UNITS
                                            END), 0) 'F_BQWAC',
                      Max(CASE
                            WHEN MONTH IN ( 3, 6, 9, 12 ) THEN FORECAST_GTS_SALES
                          END) / NULLIF(Max(CASE
                                              WHEN MONTH IN ( 3, 6, 9, 12 ) THEN FORECAST_GTS_UNITS
                                            END), 0) 'F_EQWAC',
                      Max(CASE
                            WHEN MONTH IN ( 2, 5, 8, 11 ) THEN FORECAST_GTS_SALES
                          END) / NULLIF(Max(CASE
                                              WHEN MONTH IN ( 2, 5, 8, 11 ) THEN FORECAST_GTS_UNITS
                                            END), 0) 'F_MQWAC'
                      -- AVG(COALESCE(ITEM_PRICE,FORECAST_PRICE)) AS 'F_AVGQWAC',
                      --AVG(ACTUAL_GTS_SALES / NULLIF(ACTUAL_GTS_UNITS, 0)) AS 'A_SALES_WEIGHTED_AVERAGE_WAC'
                      --,AVG(FORECAST_GTS_SALES / NULLIF(FORECAST_GTS_UNITS, 0)) AS 'F_SALES_WEIGHTED_AVERAGE_WAC'
                      --,SUM(F_DAY_WEIGHTED_WAC_PRICE) / SUM(NO_OF_DAYS) AS 'F_DAY_WEIGHTED_WAC_PRICE'
                      --,SUM(A_DAY_WEIGHTED_WAC_PRICE) / SUM(NO_OF_DAYS) AS 'A_DAY_WEIGHTED_WAC_PRICE'
                      ,
                      PERIOD_SID = Min(PERIOD_SID),
                      Min(QUARTER)                   AS QUARTER,
                      Min(SEMI_ANNUAL)               AS SEMI_ANNUAL,
                      Min(YEAR)                      AS YEAR
               FROM   #GTS_WAC C
               GROUP  BY ITEM_MASTER_SID,
                         YEAR,
                         QUARTER),
           BQWAC
           AS (SELECT ITEM_MASTER_SID,
                      ITEM_PRICE 'A_BQWAC',
                      IP.PERIOD_SID
               FROM   #UDF_ITEM_PRICING IP
               WHERE  IP.PRICING_QUALIFIER = 'BQWAC'),
           EQWAC
           AS (SELECT ITEM_MASTER_SID,
                      ITEM_PRICE 'A_EQWAC',
                      IP.PERIOD_SID
               FROM   #UDF_ITEM_PRICING IP
               WHERE  IP.PRICING_QUALIFIER = 'EQWAC'
                      AND IP.PERIOD_SID < @CURRENT_PERIOD),
           MQWAC
           AS (SELECT ITEM_MASTER_SID,
                      ITEM_PRICE 'A_MQWAC',
                      IP.PERIOD_SID
               FROM   #UDF_ITEM_PRICING IP
               WHERE  IP.PRICING_QUALIFIER = 'MQWAC'),
           AVGWAC
           AS (SELECT ITEM_MASTER_SID,
                      ITEM_PRICE 'A_AVGQWAC',
                      IP.PERIOD_SID
               FROM   #UDF_ITEM_PRICING IP
               WHERE  IP.PRICING_QUALIFIER = 'AVGQWAC'
                      AND IP.PERIOD_SID < @CURRENT_PERIOD),
           WAC
           AS (SELECT ITEM_MASTER_SID,
                      ITEM_PRICE 'A_WAC',
                      IP.PERIOD_SID
               FROM   #UDF_ITEM_PRICING IP
               WHERE  IP.PRICING_QUALIFIER = 'WAC'
                      AND IP.PERIOD_SID < @CURRENT_PERIOD),
           ACTUAL_PRICE
           AS (SELECT P.PERIOD_SID,
                      P.PERIOD_QUARTER                               QUARTER,
                      P.PERIOD_SEMI                                  SEMI_ANNUAL,
                      P.PERIOD_YEAR                                  YEAR,
                      Dateadd(QQ, Datediff(QQ, 0, P.PERIOD_DATE), 0) QUART_DATE,
                      P.ITEM_MASTER_SID,
                      B.A_BQWAC,
                      A.A_AVGQWAC,
                      E.A_EQWAC,
                      M.A_MQWAC,
                      W.A_WAC
               FROM   (SELECT *
                       FROM   #PERIOD P
                              CROSS JOIN @ITEMID I
                       WHERE  P.PERIOD_SID BETWEEN Iif(@ACTUAL_PERIOD_SID > @PROJ_START_PERIOD_SID, @PROJ_START_PERIOD_SID, @ACTUAL_PERIOD_SID) --@ACTUAL_PERIOD_SID
                                                   AND @PROJ_END_PERIOD_SID) P
                      LEFT JOIN BQWAC B
                             ON B.PERIOD_SID = P.PERIOD_SID
                                AND B.ITEM_MASTER_SID = P.ITEM_MASTER_SID
                      LEFT JOIN EQWAC E
                             ON E.PERIOD_SID = P.PERIOD_SID
                                AND E.ITEM_MASTER_SID = P.ITEM_MASTER_SID
                      LEFT JOIN MQWAC M
                             ON M.PERIOD_SID = P.PERIOD_SID
                                AND M.ITEM_MASTER_SID = P.ITEM_MASTER_SID
                      LEFT JOIN AVGWAC A
                             ON A.PERIOD_SID = P.PERIOD_SID
                                AND A.ITEM_MASTER_SID = P.ITEM_MASTER_SID
                      LEFT JOIN WAC W
                             ON W.PERIOD_SID = P.PERIOD_SID
                                AND W.ITEM_MASTER_SID = P.ITEM_MASTER_SID)
      SELECT COALESCE(AP.ITEM_MASTER_SID, FP.ITEM_MASTER_SID)     ITEM_MASTER_SID,
             AP.PERIOD_SID,
             CASE
               WHEN AP.QUART_DATE <= Dateadd(QQ, Datediff(QQ, 0, @CURRENT_DATE), 0) THEN COALESCE(AP.A_BQWAC, Lag(AP.A_BQWAC)
                                                                                                                OVER (
                                                                                                                  PARTITION BY AP.QUARTER, AP.YEAR
                                                                                                                  ORDER BY AP.QUARTER, AP.YEAR ))
               ELSE FP.F_BQWAC
             END                                                  F_BQWAC,
             Avg(CASE
                   WHEN AP.PERIOD_SID < @CURRENT_PERIOD THEN AP.A_WAC
                   ELSE G.FORECAST_PRICE
                 END)
               OVER (
                 PARTITION BY AP.ITEM_MASTER_SID, G.YEAR, G.QUARTER
                 ORDER BY AP.ITEM_MASTER_SID, G.YEAR, G.QUARTER ) F_AVGQWAC,
             CASE
               WHEN AP.QUART_DATE < Dateadd(QQ, Datediff(QQ, 0, @CURRENT_DATE), 0) THEN AP.A_EQWAC
               ELSE FP.F_EQWAC
             END                                                  F_EQWAC,
             CASE
               WHEN AP.QUART_DATE < Dateadd(QQ, Datediff(QQ, 0, @CURRENT_DATE), 0)
                    AND Dateadd(QQ, Datediff(QQ, 0, @CURRENT_DATE), 0) <> @CURRENT_DATE THEN COALESCE(AP.A_MQWAC, Lag(AP.A_MQWAC)
                                                                                                                    OVER (
                                                                                                                      PARTITION BY AP.QUARTER, AP.YEAR
                                                                                                                      ORDER BY AP.QUARTER, AP.YEAR ))
               WHEN AP.QUART_DATE <= Dateadd(QQ, Datediff(QQ, 0, @CURRENT_DATE), 0)
                    AND Dateadd(QQ, Datediff(QQ, 0, @CURRENT_DATE), 0) = @CURRENT_DATE THEN COALESCE(AP.A_MQWAC, Lag(AP.A_MQWAC)
                                                                                                                   OVER (
                                                                                                                     PARTITION BY AP.QUARTER, AP.YEAR
                                                                                                                     ORDER BY AP.QUARTER, AP.YEAR ))
               ELSE FP.F_MQWAC
             END                                                  F_MQWAC,
             G.FORECAST_PRICE                                     FRCT_PRICE,
             F_WAC = CASE
                       WHEN AP.PERIOD_SID >= @CURRENT_PERIOD THEN G.FORECAST_PRICE
                       ELSE G.ITEM_PRICE
                     END --AP.A_MQWAC
      INTO   #ITEM_WAC_PRICES
      FROM   ACTUAL_PRICE AP
             LEFT JOIN FORECAST_PRICE FP
                    ON AP.QUARTER = FP.QUARTER
                       AND AP.YEAR = FP.YEAR
                       AND FP.ITEM_MASTER_SID = AP.ITEM_MASTER_SID
             LEFT JOIN #GTS_WAC G
                    ON AP.PERIOD_SID = G.PERIOD_SID
                       AND G.ITEM_MASTER_SID = AP.ITEM_MASTER_SID

      ----------------for some condition the price information was not updated hence those will be updated here-------------------
      IF Dateadd(QQ, Datediff(QQ, 0, @CURRENT_DATE), 0) = @CURRENT_DATE
        BEGIN       
        ;
            WITH CTE
                 AS (SELECT ITEM_MASTER_SID,
                            Max(CASE
                                  WHEN MONTH IN ( 1, 4, 7, 10 ) THEN FORECAST_GTS_SALES
                                END) / NULLIF(Max(CASE
                                                    WHEN MONTH IN ( 1, 4, 7, 10 ) THEN FORECAST_GTS_UNITS
                                                  END), 0) 'F_BQWAC',
                            Max(CASE
                                  WHEN MONTH IN ( 3, 6, 9, 12 ) THEN FORECAST_GTS_SALES
                                END) / NULLIF(Max(CASE
                                                    WHEN MONTH IN ( 3, 6, 9, 12 ) THEN FORECAST_GTS_UNITS
                                                  END), 0) 'F_EQWAC',
                            Max(CASE
                                  WHEN MONTH IN ( 2, 5, 8, 11 ) THEN FORECAST_GTS_SALES
                                END) / NULLIF(Max(CASE
                                                    WHEN MONTH IN ( 2, 5, 8, 11 ) THEN FORECAST_GTS_UNITS
                                                  END), 0) 'F_MQWAC',
                            PERIOD_SID = Min(PERIOD_SID),
                            Min(QUARTER)                   AS QUARTER,
                            Min(SEMI_ANNUAL)               AS SEMI_ANNUAL,
                            Min(YEAR)                      AS YEAR
                     FROM   #GTS_WAC C
                     WHERE  C.QUARTER = Datepart(QQ, @CURRENT_DATE)
                            AND C.YEAR = Datepart(YYYY, @CURRENT_DATE)
                     GROUP  BY ITEM_MASTER_SID,
                               YEAR,
                               QUARTER)
            UPDATE I
            SET    I.F_BQWAC = C.F_BQWAC,
                   I.F_EQWAC = C.F_EQWAC,
                   I.F_MQWAC = C.F_MQWAC
            FROM   CTE C
                   JOIN #ITEM_WAC_PRICES I
                     ON I.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                   JOIN #PERIOD P
                     ON P.PERIOD_SID = I.PERIOD_SID
                        AND C.YEAR = P.PERIOD_YEAR
                        AND C.QUARTER = P.PERIOD_QUARTER
        END

      CREATE NONCLUSTERED INDEX IDX_ITEM_WAC_PRICES
        ON #ITEM_WAC_PRICES ( ITEM_MASTER_SID, PERIOD_SID )

      -------------taking the period level information of the ccp+d from the projection tables-------------------------------
      IF Object_id('TEMPDB..#TEMP_NM_PPA_PROJECTION') IS NOT NULL
        DROP TABLE #TEMP_NM_PPA_PROJECTION

      CREATE TABLE #TEMP_NM_PPA_PROJECTION
        (
           TEMP_NM_PPA_PROJECTION_SID          INT IDENTITY(1, 1),
           DENSE_RNK                           INT
           --,PROJECTION_DETAILS_SID INT
           ,
           CCP_DETAILS_SID                     INT,
           RS_CONTRACT_SID                     INT,
           ITEM_PRICING_QUALIFIER_SID          VARCHAR(100),
           NEP                                 NUMERIC(22, 6),
           BASE_PRICE_TYPE                     NUMERIC(22, 6),
           NET_BASE_PRICE                      VARCHAR(100),
           NET_BASE_PRICE_FORMULA              INT,
           SUBSEQUENT_PERIOD_PRICE_TYPE        VARCHAR(100),
           NET_SUBSEQUENT_PERIOD_PRICE         VARCHAR(100),
           NET_SUBSEQUENT_PERIOD_PRICE_FORMULA INT,
           PRICE_TOLERANCE_INTERVAL            INT,
           PRICE_TOLERANCE_FREQUENCY           VARCHAR(100),
           PRICE_TOLERANCE_TYPE                VARCHAR(100),
           PRICE_TOLERANCE                     NUMERIC(22, 6),
           MAX_INCREMENTAL_CHANGE              NUMERIC(22, 6),
           NET_PRICE_TYPE                      VARCHAR(100),
           NET_PRICE_TYPE_FORMULA              INT,
           RESET_PRICE_CAP                     BIT,
           [RESET]                             BIT,
           PERIOD_SID                          INT,
           WAC                                 NUMERIC(22, 6),
           MAP                                 NUMERIC(38, 15),
           QUARTER                             INT,
           YEAR                                INT,
           RESET_ELIGIBLE                      VARCHAR(100),
           RESET_TYPE                          VARCHAR(100),
           RESET_DATE                          DATE,
           RESET_INTERVAL                      INT,
           RESET_FREQUENCY                     VARCHAR(100),
           NET_WAC                             NUMERIC(22, 6),
           NET_MAP                             NUMERIC(22, 6),
           PRICE                               NUMERIC(22, 6),
           PRICE_CHANGE                        NUMERIC(22, 6),
           RN                                  INT,
           NET_RESET_PRICE_TYPE                VARCHAR(100),
           NET_RESET_PRICE_FORMULA             INT,
           RESET_PRICE_TYPE                    VARCHAR(100),
           PPA                                 NUMERIC(22, 6),
           CONTRACT_MASTER_SID                 INT,
           COMPANY_MASTER_SID                  INT,
           ITEM_MASTER_SID                     INT,
           USER_GROUP                          VARCHAR(50),
           PRICE_PROTECTION_START_DATE         DATETIME,
           PRICE_PROTECTION_END_DATE           DATETIME,
           PRICE_PROTECTION_STATUS             INT,
           CAL_START_DATE                      DATE,
           CAL_END_DATE                        DATE,
           REBATE_FREQUENCY                    VARCHAR(100)
        )

      CREATE NONCLUSTERED INDEX IDX_1
        ON #TEMP_NM_PPA_PROJECTION ( RN, DENSE_RNK
      --,PROJECTION_DETAILS_SID
      , CCP_DETAILS_SID, RS_CONTRACT_SID, ITEM_PRICING_QUALIFIER_SID, PRICE_TOLERANCE_INTERVAL, PRICE_TOLERANCE_FREQUENCY, PRICE_TOLERANCE_TYPE, PERIOD_SID, SUBSEQUENT_PERIOD_PRICE_TYPE, RESET_ELIGIBLE, RESET_TYPE, RESET_DATE, RESET_INTERVAL, RESET_FREQUENCY, RESET_PRICE_TYPE );

      SET @SQL = Concat (';WITH RPU_CAL
		AS (
			SELECT SNP.CCP_DETAILS_SID
				,IPQ3.PRICING_QUALIFIER ITEM_PRICING_QUALIFIER_SID --SNP.ITEM_PRICING_QUALIFIER_SID
				,NULLIF(SNP.NEP, 0) NEP
				,SNP.NEP_FORMULA
				,SNP.BASE_PRICE_MANUAL
				,SNP.BASE_PRICE_DATE
				,SNP.BASE_PRICE_PRICE_TYPE
				,SNP.BASE_PRICE_TYPE
				,HT10.DESCRIPTION NET_BASE_PRICE --SNP.NET_BASE_PRICE
				,SNP.NET_BASE_PRICE_FORMULA
				,IPQ4.PRICING_QUALIFIER SUBSEQUENT_PERIOD_PRICE_TYPE --SNP.SUBSEQUENT_PERIOD_PRICE_TYPE
				,HT9.DESCRIPTION NET_SUBSEQUENT_PERIOD_PRICE --SNP.NET_SUBSEQUENT_PERIOD_PRICE
				,SNP.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA
				,HT1.DESCRIPTION PRICE_TOLERANCE_INTERVAL --SNP.PRICE_TOLERANCE_INTERVAL
				,HT.DESCRIPTION PRICE_TOLERANCE_FREQUENCY --	SNP.PRICE_TOLERANCE_FREQUENCY
				,HT2.DESCRIPTION PRICE_TOLERANCE_TYPE --SNP.PRICE_TOLERANCE_TYPE
				,SNP.PRICE_TOLERANCE
				,SNP.MAX_INCREMENTAL_CHANGE
				,HT8.DESCRIPTION NET_PRICE_TYPE --SNP.NET_PRICE_TYPE
				,SNP.NET_PRICE_TYPE_FORMULA
				,HT3.DESCRIPTION RESET_ELIGIBLE
				,HT4.DESCRIPTION RESET_TYPE
				,DATEADD(MM, DATEDIFF(MM, 0, RESET_DATE), 0) RESET_DATE
				,HT5.DESCRIPTION RESET_INTERVAL
				,HT6.DESCRIPTION RESET_FREQUENCY
				,IPQ2.PRICING_QUALIFIER RESET_PRICE_TYPE
				,HT7.DESCRIPTION NET_RESET_PRICE_TYPE
				,NET_RESET_PRICE_FORMULA
				,CASE 
					WHEN HEL.DESCRIPTION = ''MANUAL''
						THEN ISNULL(SNP.BASE_PRICE_MANUAL, 0)
					WHEN HEL.DESCRIPTION = ''DATE''
						THEN IWB.F_WAC
					WHEN HEL.DESCRIPTION = ''PRICE TYPE''
						THEN CASE 
								WHEN IPQ1.PRICING_QUALIFIER = ''BQWAC''
									THEN IWR.F_BQWAC
								WHEN IPQ1.PRICING_QUALIFIER = ''EQWAC''
									THEN IWR.F_EQWAC
								WHEN IPQ1.PRICING_QUALIFIER = ''AVGQWAC''
									THEN IWR.F_AVGQWAC
								WHEN IPQ1.PRICING_QUALIFIER = ''MQWAC''
									THEN IWR.F_MQWAC
								WHEN IPQ1.PRICING_QUALIFIER = ''WAC''
									THEN IWR.F_WAC
										--WHEN IPQ1.PRICING_QUALIFIER = ''DAY WEIGHTED WAC''
										--	THEN COALESCE(IWR.F_DAY_WEIGHTED_WAC_PRICE / NULLIF(IWR.NO_OF_DAYS, 0), 0)
										--WHEN IPQ1.PRICING_QUALIFIER = ''SALES WEIGHTED WAC''
										--	THEN IWR.F_SALES_WEIGHTED_AVERAGE_WAC
								END
							--ELSE COALESCE(IW.WAC, 0)
					END AS BASE_PRICE
				,T_MAS.RN
				,SNP.RS_CONTRACT_SID,
                       T_MAS.CONTRACT_MASTER_SID, 
                       T_MAS.COMPANY_MASTER_SID, 
                       T_MAS.ITEM_MASTER_SID, 
                       T_MAS.USER_GROUP, 
                       T_MAS.PRICE_PROTECTION_START_DATE, 
                       T_MAS.PRICE_PROTECTION_END_DATE, 
                       T_MAS.PRICE_PROTECTION_STATUS, 
                       T_MAS.CAL_START_DATE, 
                       T_MAS.CAL_END_DATE, 
                       T_MAS.REBATE_FREQUENCY


				
			FROM  ', @CONTRACT_SETUP, '  SNP
			JOIN #TEMP_NM_PPA_PROJECTION_MASTER T_MAS ON T_MAS.CCP_DETAILS_SID = SNP.CCP_DETAILS_SID
				AND T_MAS.RS_CONTRACT_SID = SNP.RS_CONTRACT_SID
			LEFT JOIN ITEM_PRICING_QUALIFIER IPQ ON IPQ.ITEM_PRICING_QUALIFIER_SID = SNP.ITEM_PRICING_QUALIFIER_SID
			LEFT JOIN ITEM_PRICING_QUALIFIER IPQ1 ON IPQ1.ITEM_PRICING_QUALIFIER_SID = SNP.BASE_PRICE_PRICE_TYPE
			left join #period p on p.period_date = T_MAS.PRICE_PROTECTION_START_DATE
			LEFT JOIN #ITEM_WAC_PRICES IWR ON IWR.PERIOD_SID = p.PERIOD_SID
				AND IWR.ITEM_MASTER_SID = T_MAS.ITEM_MASTER_SID
			LEFT JOIN PERIOD PE ON PE.PERIOD_DATE = DATEADD(MM, DATEDIFF(MM, 0, SNP.BASE_PRICE_DATE), 0)			
			LEFT JOIN #ITEM_WAC_PRICES IWB ON IWB.PERIOD_SID = PE.PERIOD_SID--SNP.PERIOD_SID
				AND IWB.ITEM_MASTER_SID = T_MAS.ITEM_MASTER_SID --AND IW.PERIOD_SID BETWEEN @START_DATE_SID AND @END_DATE_SID
			LEFT JOIN #ITEM_WAC_PRICES IWE ON IWE.PERIOD_SID = PE.PERIOD_SID
				AND IWE.ITEM_MASTER_SID = T_MAS.ITEM_MASTER_SID
			LEFT JOIN HELPER_TABLE HEL ON HEL.HELPER_TABLE_SID = SNP.BASE_PRICE_TYPE
			LEFT JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = SNP.PRICE_TOLERANCE_FREQUENCY
				AND HT.LIST_NAME = ''PRICE_TOLERANCE_FREQUENCY''
			LEFT JOIN HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID = SNP.PRICE_TOLERANCE_INTERVAL
				AND HT1.LIST_NAME = ''PRICE_TOLERANCE_INTERVAL''
			LEFT JOIN HELPER_TABLE HT2 ON HT2.HELPER_TABLE_SID = SNP.PRICE_TOLERANCE_TYPE
				AND HT2.LIST_NAME = ''PRICE_TOLERANCE_TYPE''
			LEFT JOIN HELPER_TABLE HT3 ON HT3.HELPER_TABLE_SID = SNP.RESET_ELIGIBLE
			LEFT JOIN HELPER_TABLE HT4 ON HT4.HELPER_TABLE_SID = SNP.RESET_TYPE
				AND HT4.LIST_NAME = ''RESET_TYPE''
			LEFT JOIN HELPER_TABLE HT5 ON HT5.HELPER_TABLE_SID = SNP.RESET_INTERVAL
				AND HT5.LIST_NAME = ''PRICE_TOLERANCE_INTERVAL''
			LEFT JOIN HELPER_TABLE HT6 ON HT6.HELPER_TABLE_SID = SNP.RESET_FREQUENCY
				AND HT6.LIST_NAME = ''PRICE_TOLERANCE_FREQUENCY''
			LEFT JOIN ITEM_PRICING_QUALIFIER IPQ2 ON IPQ2.ITEM_PRICING_QUALIFIER_SID = SNP.RESET_PRICE_TYPE
			LEFT JOIN HELPER_TABLE HT7 ON HT7.HELPER_TABLE_SID = SNP.NET_RESET_PRICE_TYPE
			LEFT JOIN HELPER_TABLE HT8 ON HT8.HELPER_TABLE_SID = SNP.NET_PRICE_TYPE
			LEFT JOIN HELPER_TABLE HT9 ON HT9.HELPER_TABLE_SID = SNP.NET_SUBSEQUENT_PERIOD_PRICE
			LEFT JOIN HELPER_TABLE HT10 ON HT10.HELPER_TABLE_SID = SNP.NET_BASE_PRICE
			LEFT JOIN ITEM_PRICING_QUALIFIER IPQ3 ON IPQ3.ITEM_PRICING_QUALIFIER_SID = SNP.ITEM_PRICING_QUALIFIER_SID
			LEFT JOIN ITEM_PRICING_QUALIFIER IPQ4 ON IPQ4.ITEM_PRICING_QUALIFIER_SID = SNP.SUBSEQUENT_PERIOD_PRICE_TYPE
			)
INSERT INTO #TEMP_NM_PPA_PROJECTION
            (CCP_DETAILS_SID,
             RS_CONTRACT_SID,
             ITEM_PRICING_QUALIFIER_SID,
             NEP,
             BASE_PRICE_TYPE,
             NET_BASE_PRICE,
             PRICE_TOLERANCE_INTERVAL,
             PRICE_TOLERANCE_FREQUENCY,
             PRICE_TOLERANCE_TYPE,
             PRICE_TOLERANCE,
             RN,
             SUBSEQUENT_PERIOD_PRICE_TYPE,
             NET_SUBSEQUENT_PERIOD_PRICE,
             RESET_ELIGIBLE,
             RESET_TYPE,
             RESET_DATE,
             RESET_INTERVAL,
             RESET_FREQUENCY,
             RESET_PRICE_TYPE,
             MAX_INCREMENTAL_CHANGE,
             NET_RESET_PRICE_TYPE,
             NET_RESET_PRICE_FORMULA,
             NET_PRICE_TYPE,
             NET_PRICE_TYPE_FORMULA,
             NET_BASE_PRICE_FORMULA,
             NET_SUBSEQUENT_PERIOD_PRICE_FORMULA,
             CONTRACT_MASTER_SID,
             COMPANY_MASTER_SID,
             ITEM_MASTER_SID,
             USER_GROUP,
             PRICE_PROTECTION_START_DATE,
             PRICE_PROTECTION_END_DATE,
             PRICE_PROTECTION_STATUS,
             CAL_START_DATE,
             CAL_END_DATE,
             REBATE_FREQUENCY)
SELECT CCP_DETAILS_SID,
       RS_CONTRACT_SID,
       ITEM_PRICING_QUALIFIER_SID,
       NEP,
       BASE_PRICE,
       NET_BASE_PRICE,
       PRICE_TOLERANCE_INTERVAL,
       PRICE_TOLERANCE_FREQUENCY,
       PRICE_TOLERANCE_TYPE,
       PRICE_TOLERANCE,
       RN,
       SUBSEQUENT_PERIOD_PRICE_TYPE,
       NET_SUBSEQUENT_PERIOD_PRICE,
       RESET_ELIGIBLE,
       RESET_TYPE,
       RESET_DATE,
       RESET_INTERVAL,
       RESET_FREQUENCY,
       RESET_PRICE_TYPE,
       MAX_INCREMENTAL_CHANGE,
       NET_RESET_PRICE_TYPE,
       NET_RESET_PRICE_FORMULA,
       NET_PRICE_TYPE,
       NET_PRICE_TYPE_FORMULA,
       NET_BASE_PRICE_FORMULA,
       NET_SUBSEQUENT_PERIOD_PRICE_FORMULA,
       CONTRACT_MASTER_SID,
       COMPANY_MASTER_SID,
       ITEM_MASTER_SID,
       USER_GROUP,
       PRICE_PROTECTION_START_DATE,
       PRICE_PROTECTION_END_DATE,
       PRICE_PROTECTION_STATUS,
       CAL_START_DATE,
       CAL_END_DATE,
       REBATE_FREQUENCY
FROM   RPU_CAL 
');

      EXEC Sp_executesql
        @SQL;

      --------------netting information was availble or not and these tables does not effect calculation----------------------
      DECLARE @PRICE_TOLERANCE             NUMERIC(22, 6),
              @PRICE_TOLERANCE_INTERVAL    INT,
              @PRICE_TOLERANCE_FREQUENCY   INT,
              @PRICE_TOLERANCE_TYPE        INT,
              @ITEM_PRICING_QUALIFIER_SID  INT,
              @PRICE_TOLERANCE1            NUMERIC(22, 6),
              @PRICE_TOLERANCE_INTERVAL1   INT,
              @PRICE_TOLERANCE_FREQUENCY1  INT,
              @PRICE_TOLERANCE_TYPE1       INT,
              @ITEM_PRICING_QUALIFIER_SID1 INT,
              @RESET_EFFECTIVE_DATE        INT,
              @RESET_VIOLATION_DATE        INT,
              @RESET_INTERVAL_FREQUENCY    INT,
              @PRIOR_PERIOD_MAP            NUMERIC(22, 6),
              @PRIOR_PERIOD_NET_WAC        NUMERIC(22, 6),
              @PRIOR_PERIOD_WAC            NUMERIC(22, 6),
              @PRICE_TOLERENCE_TYPE        VARCHAR(100),
              @NET_MAP                     VARCHAR(1000),
              @INCR                        INT = 1,
              @RESET_ELIGIBLE              VARCHAR(100),
              @RESET_TYPE                  VARCHAR(100),
              @ROLLOUT                     INT,
              @PREV_TOL                    INT,
              @RESET_INC                   INT = 1,
              @OUTER_LOOP                  INT = 1,
              @INNER_LOOP                  INT = 1,
              @CNT                         INT,
              @PRICE_TOL_FRQ               VARCHAR(50),
              @PRICE_TOL_INT               INT,
              @PRICE_TOL_TYPE              VARCHAR(100),
              @RESET_TOL_INT               INT,
              @RESET_TOL_FREQ              VARCHAR(50),
              @RESET_PERIOD                INT,
              @RESET_DATE                  DATE,
              @RESET                       INT,
              @TEST_DATE                   DATE

      -------------taken ccp+d level information from master table which will be used to know the periods for calcuating the nep and wac information to achieve ppa discount--------		  
      SET @SQL= Concat('
            IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''', @ST_NM_PPA_SETUP, '''
                      AND TABLE_SCHEMA = ''DBO'')
					  BEGIN
			   DROP TABLE ', @ST_NM_PPA_SETUP, ' 
       END

	   create table ' + @ST_NM_PPA_SETUP
                                                      + ' (
	   CONTRACT_MASTER_SID int,
                      ITEM_MASTER_SID int,
                      RS_CONTRACT_SID int,
                    CAL_START_DATE int,
                    CAL_END_DATE int,
                    PRICE_PROTECTION_START_DATE int,
                    PRICE_PROTECTION_END_DATE int,
                      RESET_TOL_FREQ   varchar(100),
                      RESET_TOL_INT  varchar(100),
                      RESET_ELIGIBLE  varchar(100),
                      RESET_TYPE  varchar(100) ,
                      RESET_PERIOD   int,
                      RESET_DATE  datetime,
                      PRICE_TOL_FRQ varchar(100),
                      PRICE_TOL_INT  varchar(100),
                      PRICE_TOL_TYPE   varchar(100),
                      REBATE_FREQUENCY varchar(100),
                      CCP_DETAILS_SID int
					  )

          insert into ' + @ST_NM_PPA_SETUP + '
     SELECT DISTINCT CONTRACT_MASTER_SID,
                      ITEM_MASTER_SID,
                      TN.RS_CONTRACT_SID,
                      P.PERIOD_SID  CAL_START_DATE,
                      P2.PERIOD_SID CAL_END_DATE,
                      P3.PERIOD_SID PRICE_PROTECTION_START_DATE,
                      P4.PERIOD_SID PRICE_PROTECTION_END_DATE,
                      RESET_TOL_FREQ = TN.RESET_FREQUENCY,
                      RESET_TOL_INT = TN.RESET_INTERVAL,
                      RESET_ELIGIBLE = TN.RESET_ELIGIBLE,
                      RESET_TYPE = TN.RESET_TYPE,
                      RESET_PERIOD = TN.PERIOD_SID,
                      RESET_DATE = TN.RESET_DATE,
                      PRICE_TOL_FRQ = TN.PRICE_TOLERANCE_FREQUENCY,
                      PRICE_TOL_INT = TN.PRICE_TOLERANCE_INTERVAL,
                      PRICE_TOL_TYPE = TN.PRICE_TOLERANCE_TYPE,
                      TN.REBATE_FREQUENCY,
                      TN.CCP_DETAILS_SID
      FROM   #TEMP_NM_PPA_PROJECTION TN
             JOIN #PERIOD P
               ON P.PERIOD_DATE = DATEADD(MM, DATEDIFF(MM, 0, TN.CAL_START_DATE), 0)
             JOIN #PERIOD P2
               ON P2.PERIOD_DATE = DATEADD(MM, DATEDIFF(MM, 0, TN.CAL_END_DATE), 0)
             JOIN #PERIOD P3
               ON P3.PERIOD_DATE = DATEADD(MM, DATEDIFF(MM, 0, TN.PRICE_PROTECTION_START_DATE), 0)
             JOIN #PERIOD P4
               ON P4.PERIOD_DATE = DATEADD(MM, DATEDIFF(MM, 0, TN.PRICE_PROTECTION_END_DATE), 0)
')

      EXEC Sp_executesql
        @SQL;

      --------------taking the price infor based on the rebate frequency----------------------
      IF Object_id('TEMPDB..#TEMP_WAC_PRICES') IS NOT NULL
        DROP TABLE #TEMP_WAC_PRICES

      CREATE TABLE #TEMP_WAC_PRICES
        (
           CONTRACT_MASTER_SID INT,
           RS_CONTRACT_SID     INT,
           ITEM_MASTER_SID     INT,
           PERIOD_SID          INT,
           PERIOD_QUARTER      INT,
           PERIOD_YEAR         INT,
           PERIOD_SEMI         INT,
           F_BQWAC             NUMERIC(22, 6),
           F_AVGQWAC           NUMERIC(22, 6),
           F_EQWAC             NUMERIC(22, 6),
           F_MQWAC             NUMERIC(22, 6),
           F_WAC               NUMERIC(22, 6)
        --,F_SALES_WEIGHTED_AVERAGE_WAC NUMERIC(22, 6)
        --,F_DAY_WEIGHTED_WAC_PRICE NUMERIC(22, 6)
        );

      CREATE NONCLUSTERED INDEX IDX_TEMP_WAC_PRICES
        ON #TEMP_WAC_PRICES ( ITEM_MASTER_SID, PERIOD_SID );

      EXEC(';
      WITH CTE
           AS (SELECT T2.CONTRACT_MASTER_SID,
       T2.RS_CONTRACT_SID,
       I.PERIOD_SID,
       I.ITEM_MASTER_SID,
       I.F_BQWAC,
       I.F_AVGQWAC,
       I.F_EQWAC,
       I.F_MQWAC,
       I.F_WAC,
       T2.REBATE_FREQUENCY,
       Row_number()
         OVER (
           PARTITION BY T2.CONTRACT_MASTER_SID, T2.ITEM_MASTER_SID, T2.RS_CONTRACT_SID
           ORDER BY I.PERIOD_SID ) RN
FROM   #ITEM_WAC_PRICES I
       JOIN (SELECT DISTINCT T2.CONTRACT_MASTER_SID,
                             T2.RS_CONTRACT_SID,
                             ITEM_MASTER_SID,
                             T2.CAL_START_DATE,
                             T2.CAL_END_DATE,
                             T2.REBATE_FREQUENCY
             FROM   '+@ST_NM_PPA_SETUP+' T2) t2
         ON I.ITEM_MASTER_SID = T2.ITEM_MASTER_SID
WHERE  I.PERIOD_SID BETWEEN T2.CAL_START_DATE AND T2.CAL_END_DATE ),
           CTE3
           AS (SELECT *,
                      ( ( RN - 1 ) % CASE
                                       WHEN LEFT(REBATE_FREQUENCY, 1) = ''Q'' THEN 3
                                       WHEN LEFT(REBATE_FREQUENCY, 1) = ''M'' THEN 1
                                       WHEN LEFT(REBATE_FREQUENCY, 1) = ''S'' THEN 6
                                       WHEN LEFT(REBATE_FREQUENCY, 1) = ''A'' THEN 12
                                     END ) + 1 REBATE_FREQ
               FROM   CTE)
      INSERT INTO #TEMP_WAC_PRICES
                  (PERIOD_SID,
                   ITEM_MASTER_SID,
                   CONTRACT_MASTER_SID,
                   RS_CONTRACT_SID,
                   F_BQWAC,
                   F_AVGQWAC,
                   F_EQWAC,
                   F_MQWAC,
                   F_WAC,
                   PERIOD_QUARTER,
                   PERIOD_YEAR,
                   PERIOD_SEMI)
      SELECT C.PERIOD_SID,
             ITEM_MASTER_SID,
             CONTRACT_MASTER_SID,
             RS_CONTRACT_SID,
             F_BQWAC,
             F_AVGQWAC,
             F_EQWAC,
             F_MQWAC,
             F_WAC,
             PERIOD_QUARTER,
             PERIOD_YEAR,
             PERIOD_SEMI
      FROM   CTE3 C
             JOIN #PERIOD P
               ON P.PERIOD_SID = C.PERIOD_SID
      WHERE  REBATE_FREQ = 1')

      ------------------------combining the reset information, ccp+D period wise information and price information in single temp table so for the future this will be used in calculated-------------------------------------------
      IF Object_id('TEMPDB..#REC_USED_TABLE') IS NOT NULL
        DROP TABLE #REC_USED_TABLE

      SELECT T.CCP_DETAILS_SID,
             p1.PERIOD_SID,
             T.RS_CONTRACT_SID,
             T.BASE_PRICE_TYPE,
             T.PRICE_TOLERANCE_FREQUENCY,
             T.PRICE_TOLERANCE_INTERVAL,
             T.ITEM_PRICING_QUALIFIER_SID,
             T.NET_BASE_PRICE NET_BASE_PRICE,
             T.NET_SUBSEQUENT_PERIOD_PRICE,
             T.NET_PRICE_TYPE,
             T.NET_RESET_PRICE_TYPE,
             T.RESET_ELIGIBLE,
             T.RESET_TYPE,
             T.RESET_PRICE_TYPE,
             T.SUBSEQUENT_PERIOD_PRICE_TYPE,
             T.PRICE_TOLERANCE,
             T.PRICE_TOLERANCE_TYPE,
             WAC_START_SID = P.PERIOD_SID,
             WAC_END_SID = P2.PERIOD_SID,
             T.RESET_DATE,
             T.REBATE_FREQUENCY,
             RESET_PRICE_VALUE = CASE
                                   WHEN T.RESET_PRICE_TYPE = 'BQWAC' THEN COALESCE(TWP.F_BQWAC, IWP.F_BQWAC, 0)
                                   WHEN T.RESET_PRICE_TYPE = 'EQWAC' THEN COALESCE(TWP.F_EQWAC, IWP.F_EQWAC, 0)
                                   WHEN T.RESET_PRICE_TYPE = 'AVGQWAC' THEN COALESCE(TWP.F_AVGQWAC, IWP.F_AVGQWAC, 0)
                                   WHEN T.RESET_PRICE_TYPE = 'MQWAC' THEN COALESCE(TWP.F_MQWAC, IWP.F_MQWAC, 0)
                                   WHEN T.RESET_PRICE_TYPE = 'WAC' THEN COALESCE(TWP.F_WAC, IWP.F_WAC, 0)
                                 END,
             WAC_PRICE_VALUE = CASE
                                 WHEN p1.PERIOD_SID BETWEEN P.PERIOD_SID AND P2.PERIOD_SID THEN
                                   CASE
                                     WHEN T.ITEM_PRICING_QUALIFIER_SID = 'BQWAC' THEN COALESCE(TWP.F_BQWAC, IWP.F_BQWAC, 0)
                                     WHEN T.ITEM_PRICING_QUALIFIER_SID = 'EQWAC' THEN COALESCE(TWP.F_EQWAC, IWP.F_EQWAC, 0)
                                     WHEN T.ITEM_PRICING_QUALIFIER_SID = 'AVGQWAC' THEN COALESCE(TWP.F_AVGQWAC, IWP.F_AVGQWAC, 0)
                                     WHEN T.ITEM_PRICING_QUALIFIER_SID = 'MQWAC' THEN COALESCE(TWP.F_MQWAC, IWP.F_MQWAC, 0)
                                     WHEN T.ITEM_PRICING_QUALIFIER_SID = 'WAC' THEN COALESCE(TWP.F_WAC, IWP.F_WAC, 0)
                                   END
                                 ELSE 0
                               END,
             SUBSEQUENT_PERIOD_PRICE= CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                                        WHEN SUBSEQUENT_PERIOD_PRICE_TYPE = 'BQWAC' THEN COALESCE(TWP.F_BQWAC, IWP.F_BQWAC, 0)
                                        WHEN SUBSEQUENT_PERIOD_PRICE_TYPE = 'EQWAC' THEN COALESCE(TWP.F_EQWAC, IWP.F_EQWAC, 0)
                                        WHEN SUBSEQUENT_PERIOD_PRICE_TYPE = 'AVGQWAC' THEN COALESCE(TWP.F_AVGQWAC, IWP.F_AVGQWAC, 0)
                                        WHEN SUBSEQUENT_PERIOD_PRICE_TYPE = 'MQWAC' THEN COALESCE(TWP.F_MQWAC, IWP.F_MQWAC, 0)
                                        WHEN SUBSEQUENT_PERIOD_PRICE_TYPE = 'WAC' THEN COALESCE(TWP.F_WAC, IWP.F_WAC, 0)
                                      END,
             T.RESET_FREQUENCY,
             T.RESET_INTERVAL,
             NEP_RESULT = Cast(0 AS NUMERIC(22, 6)),
             WAC_RESULT = Cast(0 AS NUMERIC(22, 6))
			 ,NEP              AS INITIAL_NEP-----------CEL-1465
      INTO   #REC_USED_TABLE
      FROM   #TEMP_NM_PPA_PROJECTION T
             JOIN #period p1
               ON p1.PERIOD_DATE BETWEEN T.PRICE_PROTECTION_START_DATE AND T.PRICE_PROTECTION_END_DATE
             LEFT JOIN #TEMP_WAC_PRICES TWP
                    ON T.CONTRACT_MASTER_SID = TWP.CONTRACT_MASTER_SID
                       AND TWP.ITEM_MASTER_SID = T.ITEM_MASTER_SID
                       AND TWP.PERIOD_SID = p1.PERIOD_SID
                       AND TWP.RS_CONTRACT_SID = T.RS_CONTRACT_SID
             JOIN #ITEM_WAC_PRICES IWP
               ON IWP.ITEM_MASTER_SID = T.ITEM_MASTER_SID
                  AND IWP.PERIOD_SID = p1.PERIOD_SID
             JOIN #PERIOD P
               ON P.PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, T.CAL_START_DATE), 0)
             JOIN #PERIOD P2
               ON P2.PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, T.CAL_END_DATE), 0)

      -------------------------after calcuating the nep for the ccp and rs they will be stored in the temp_map_data table-------------------------------------------
/*
  ------GALUAT-870
      IF Object_id('TEMPDB..#TEMP_MAP_DATA') IS NOT NULL
        DROP TABLE #TEMP_MAP_DATA

      CREATE TABLE #TEMP_MAP_DATA
        (
           ID               INT IDENTITY(1, 1),
           CCP_DETAILS_SID  INT NOT NULL,
           RS_CONTRACT_SID  INT NOT NULL,
           PERIOD_SID       INT NOT NULL,
           REBATE_FREQUENCY VARCHAR(50),
           REBATE_PERIOD    VARCHAR(50),
           WAC              NUMERIC(38, 15),
           NET_WAC          NUMERIC(38, 15),
           NET_MAP          NUMERIC(22, 6),
           PPA              NUMERIC(22, 6),
           PRICE_CHANGE     NUMERIC(22, 6),
           [RESET]          INT,
           WAC_RESET        BIT,
           PRIMARY KEY ( CCP_DETAILS_SID, RS_CONTRACT_SID, PERIOD_SID )
        )*/
      ----------------the following cte will identify the periods where nep and waq prices has to be reset irrespective of extenal resets due to reset varibales or dure to price tolerence variables
      IF Object_id('TEMPDB..#RESULT_PREW') IS NOT NULL
        DROP TABLE #RESULT_PREW;

      WITH CTE
           AS (SELECT ( Row_number()
                          OVER (
                            PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                            ORDER BY PERIOD_SID ) - 1 ) + 1                                            AS hard_reset_periods,
                      ( ( Row_number()
                            OVER (
                              PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                              ORDER BY PERIOD_SID ) - 1 ) % ( CASE LEFT(A.PRICE_TOLERANCE_FREQUENCY, 1)
                                                                WHEN 'M' THEN 1
                                                                WHEN 'Q' THEN 3
                                                                WHEN 'S' THEN 6
                                                                ELSE 12
                                                              END * A.PRICE_TOLERANCE_INTERVAL ) ) + 1 AS RESET_PERIODS_RN,
                      ( CASE LEFT(A.PRICE_TOLERANCE_FREQUENCY, 1)
                          WHEN 'M' THEN 1
                          WHEN 'Q' THEN 3
                          WHEN 'S' THEN 6
                          ELSE 12
                        END * A.PRICE_TOLERANCE_INTERVAL )                                             AS PT_SEQ,
                      ( CASE LEFT(A.REBATE_FREQUENCY, 1)
                          WHEN 'M' THEN 1
                          WHEN 'Q' THEN 3
                          WHEN 'S' THEN 6
                          ELSE 12
                        END )                                                                          RESET_SEQ,
                      *
               FROM   (SELECT T.*,
                              CASE
                                WHEN T.RESET_ELIGIBLE = 'YES'
                                     AND T.RESET_TYPE = 'EFFECTIVE DATE' THEN Sum(CASE WHEN T.RESET_DATE = P.PERIOD_DATE THEN 1 ELSE 0 END) OVER ( PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID ORDER BY T.PERIOD_SID )
                                                                              + 1
                                WHEN T.RESET_ELIGIBLE = 'YES'
                                     AND T.RESET_TYPE = 'INTERVAL FREQUENCY' THEN ( ( Row_number()
                                                                                        OVER (
                                                                                          PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID
                                                                                          ORDER BY P.PERIOD_SID ) - 1 ) / ( CASE LEFT(T.RESET_FREQUENCY, 1)
                                                                                                                              WHEN 'M' THEN 1
                                                                                                                              WHEN 'Q' THEN 3
                                                                                                                              WHEN 'S' THEN 6
                                                                                                                              ELSE 12
                                                                                                                            END * T.RESET_INTERVAL ) ) + 1
                                ELSE 1
                              END AS RESET_GRP
                       FROM   #REC_USED_TABLE T
                              JOIN #PERIOD P
                                ON P.PERIOD_SID = T.PERIOD_SID) A)
      SELECT ( ( RESET_PERIODS_RN - 1 ) % CASE LEFT(REBATE_FREQUENCY, 1)
                                            WHEN 'M' THEN 1
                                            WHEN 'Q' THEN 3
                                            WHEN 'S' THEN 6
                                            ELSE 12
                                          END + 1 ) REBATE_FREQUENCY1,--CHANGE 3 TO FREQUENCT CASE STATEMENT 
             Sum(CASE
                   WHEN PERIOD_SID >= WAC_START_SID
                        AND ( RESET_PERIODS_RN - 1 ) % CASE LEFT(REBATE_FREQUENCY, 1)
                                                         WHEN 'M' THEN 1
                                                         WHEN 'Q' THEN 3
                                                         WHEN 'S' THEN 6
                                                         ELSE 12
                                                       END + 1 <> 1 THEN 0
                   ELSE 1
                 END)
               OVER(
                 ORDER BY PERIOD_SID)               TTE,
             *,--CHANGE 3 TO FREQUENCT CASE STATEMENT 
             NEW_WAC_PRICE_VALUE = CASE
                                     WHEN PERIOD_SID BETWEEN WAC_START_SID AND WAC_END_SID THEN WAC_PRICE_VALUE
                                   END
      INTO   #RESULT_PREW
      FROM   CTE T

      --WHERE RESET_PERIODS_RN = 1
      ----------the following temp table will identify ccp+d who not having the reset type violation date and calculate NEP values for period wise based on the ppa setup -----     
      IF Object_id('TEMPDB..#NEP_RESULT') IS NOT NULL
        DROP TABLE #NEP_RESULT;

  ;
      WITH CTE
           AS (SELECT *,
                      Row_number()
                        OVER (
                          PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                          ORDER BY PERIOD_SID ) FIRST_PERIOD
               FROM   #RESULT_PREW RP
               WHERE  RESET_PERIODS_RN = 1
                      --- and  RESET_TYPE IN ( 'EFFECTIVE DATE', 'INTERVAL FREQUENCY' )
                      --- OR RESET_ELIGIBLE = 'NO'
                      --- OR RESET_ELIGIBLE IS NULL )
                      AND NOT EXISTS(SELECT 1
                                     FROM   #TEMP_NM_PPA_PROJECTION th
                                     WHERE  th.RESET_TYPE = 'VIOLATION DATE'
                                            AND th.RESET_ELIGIBLE = 'YES'
                                            AND rp.CCP_DETAILS_SID = th.CCP_DETAILS_SID
                                            AND rp.RS_CONTRACT_SID = th.RS_CONTRACT_SID))---------GALUAT-870
    SELECT *,
           CASE
             WHEN LEFT(PRICE_TOLERANCE_TYPE, 1) = 'D' THEN
               CASE
                 WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                 ELSE Iif(RESET_GRP = 1, Iif(NULLIF(INITIAL_NEP, 0) IS NOT NULL AND RESET_GRP = 1, INITIAL_NEP, BASE_PRICE_TYPE), First_value(RESET_PRICE_VALUE)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                                                                    ORDER BY PERIOD_SID )) + ( Sum(PRICE_TOLERANCE)
                                                                                                                                                 OVER (
                                                                                                                                                   PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                                                                                                   ORDER BY PERIOD_SID ) - Iif(NULLIF(INITIAL_NEP, 0) IS NOT NULL AND RESET_GRP = 1, PRICE_TOLERANCE, 0) )
               END
             WHEN LEFT(PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
               CASE
                 WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                 ELSE Iif(RESET_GRP = 1, Iif(NULLIF(INITIAL_NEP, 0) IS NOT NULL AND RESET_GRP = 1, INITIAL_NEP, BASE_PRICE_TYPE), First_value(RESET_PRICE_VALUE)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                                                                    ORDER BY PERIOD_SID )) * Power(( 1 + PRICE_TOLERANCE / 100.0 ), FIRST_PERIOD - Iif(NULLIF(INITIAL_NEP, 0) IS NOT NULL AND RESET_GRP = 1, 1, 0))
               END
           END AS NEP_WITHOUT_NETTING
    INTO   #NEP_RESULT
    FROM   CTE---------------CEL-1465
	/*      SELECT *,
             CASE
               WHEN LEFT(PRICE_TOLERANCE_TYPE, 1) = 'D' THEN
                 CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                   WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                   ELSE Iif(RESET_GRP = 1, BASE_PRICE_TYPE, First_value(RESET_PRICE_VALUE) OVER ( PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP ORDER BY PERIOD_SID ))
                        + Sum(PRICE_TOLERANCE) OVER ( PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP ORDER BY PERIOD_SID )
                 END
               WHEN LEFT(PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
                 CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                   WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                   ELSE Iif(RESET_GRP = 1, BASE_PRICE_TYPE, First_value(RESET_PRICE_VALUE)
                                                              OVER (
                                                                PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                ORDER BY PERIOD_SID )) * Power(( 1 + PRICE_TOLERANCE / 100.0 ), FIRST_PERIOD)
                 END
             END AS NEP_WITHOUT_NETTING
      INTO   #NEP_RESULT
      FROM   CTE
	  */
      ----------the following temp table will identify ccp+d who having the reset type violation date only and calculate NEP values for period wise based on the ppa setup -----
      IF EXISTS (SELECT 1
                 FROM   #TEMP_NM_PPA_PROJECTION
                 WHERE  RESET_TYPE = 'VIOLATION DATE'
                        AND RESET_ELIGIBLE = 'YES')
        BEGIN
            --------------identified the wac price information for the periods before the projection start date----------
            IF Object_id('tempdb..#BEFORE_WAC') IS NOT NULL
              DROP TABLE #BEFORE_WAC

            CREATE TABLE #BEFORE_WAC
              (
                 CCP_DETAILS_SID INT,
                 RS_CONTRACT_SID INT,
                 PERIOD_SID      INT
              )

            EXEC('Insert into #BEFORE_WAC(CCP_DETAILS_SID,
             RS_CONTRACT_SID,PERIOD_SID)
      SELECT CCP_DETAILS_SID,
             RS_CONTRACT_SID,
             MAX(PERIOD_SID) AS PERIOD_SID
      FROM   (SELECT C.CCP_DETAILS_SID,
                     RS_CONTRACT_SID,
                     PERIOD_SID,
                     ( PERIOD_SID - PRICE_PROTECTION_START_DATE ) / ( CASE LEFT(C.PRICE_TOL_FRQ, 1)
                                                                        WHEN ''M'' THEN 1
                                                                        WHEN ''Q'' THEN 3
                                                                        WHEN ''S'' THEN 6
                                                                        ELSE 12
                                                                      END * C.PRICE_TOL_INT ) + 1 AS GRP,
                     MAX(CASE
                           WHEN PD.PROJ_START_DATE = PERIOD_SID THEN ( PERIOD_SID - PRICE_PROTECTION_START_DATE ) / ( CASE LEFT(C.PRICE_TOL_FRQ, 1)
                                                                        WHEN ''M'' THEN 1
                                                                        WHEN ''Q'' THEN 3
                                                                        WHEN ''S'' THEN 6
                                                                        ELSE 12
                                                                      END * C.PRICE_TOL_INT ) + 1
                         END)
                       OVER(
                         PARTITION BY C.CCP_DETAILS_SID, RS_CONTRACT_SID)                         AS PROJ_STAT_GRP
              FROM   #PERIOD P
                     JOIN '+@ST_NM_PPA_SETUP+' C
                          JOIN #PROJECTION_DATES PD
                            ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                       ON P.PERIOD_SID >= C.PRICE_PROTECTION_START_DATE
                          AND P.PERIOD_SID <= C.PRICE_PROTECTION_END_DATE
              WHERE  C.RESET_TYPE = ''VIOLATION_DATE''
                     AND C.RESET_ELIGIBLE = ''YES'') A
      WHERE  GRP <= PROJ_STAT_GRP
      GROUP  BY CCP_DETAILS_SID,
                RS_CONTRACT_SID')
            ----------for the projeciton periods we need to calcualte the nep, but when ever nep>wac(price protectyion violation) occurs the next period has to be reset, hence at the reset period  both the nep and wac has to be reset irrespective of price tolerance variables--------------
            ----------so based on that logic we need to use recurssive cte for the calculation----------------------
            IF Object_id('tempdb..#VIOLATION_NEP_RESULT') IS NOT NULL
            DROP TABLE #VIOLATION_NEP_RESULT;;

            WITH CTE
                 AS (SELECT *,
                            Row_number()
                              OVER (
                                PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                ORDER BY PERIOD_SID ) FIRST_PERIOD
                     FROM   #RESULT_PREW RP
                     WHERE  RESET_PERIODS_RN = 1
                          AND EXISTS(SELECT 1
                                     FROM   #TEMP_NM_PPA_PROJECTION th
                                     WHERE  th.RESET_TYPE = 'VIOLATION DATE'
                                            AND th.RESET_ELIGIBLE = 'YES'
                                            AND rp.CCP_DETAILS_SID = th.CCP_DETAILS_SID
                                            AND rp.RS_CONTRACT_SID = th.RS_CONTRACT_SID))---------CEL-1465
          SELECT C.*,
                 CASE
                   WHEN LEFT(c.PRICE_TOLERANCE_TYPE, 1) = 'D' THEN
                     CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                       WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                       ELSE Iif(c.RESET_GRP = 1, Iif(NULLIF(c.INITIAL_NEP, 0) IS NOT NULL AND RESET_GRP = 1, INITIAL_NEP, BASE_PRICE_TYPE), First_value(RESET_PRICE_VALUE)
                                                                                                                            OVER (
                                                                                                                              PARTITION BY c.CCP_DETAILS_SID, c.RS_CONTRACT_SID, c.RESET_GRP
                                                                                                                              ORDER BY c.PERIOD_SID )) + ( Sum(c.PRICE_TOLERANCE)
                                                                                                                                                             OVER (
                                                                                                                                                               PARTITION BY c.CCP_DETAILS_SID, c.RS_CONTRACT_SID, c.RESET_GRP
                                                                                                                                                               ORDER BY c.PERIOD_SID ) - Iif(NULLIF(c.INITIAL_NEP, 0) IS NOT NULL and RESET_GRP = 1, PRICE_TOLERANCE, 0) )
                     END
                   WHEN LEFT(c.PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
                     CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                       WHEN c.SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                       ELSE Iif(c.RESET_GRP = 1, Iif(NULLIF(c.INITIAL_NEP, 0) IS NOT NULL AND RESET_GRP = 1, c.INITIAL_NEP, c.BASE_PRICE_TYPE), First_value(c.RESET_PRICE_VALUE)
                                                                                                                                OVER (
                                                                                                                                  PARTITION BY c.CCP_DETAILS_SID, c.RS_CONTRACT_SID, c.RESET_GRP
                                                                                                                                  ORDER BY c.PERIOD_SID )) * ( Power(( 1 + c.PRICE_TOLERANCE / 100.0 ), FIRST_PERIOD - Iif(NULLIF(c.INITIAL_NEP, 0) IS NOT NULL and RESET_GRP = 1, 1, 0)) )
                     END
                 END AS NEP_WITHOUT_NETTING
          INTO   #VIOLATION_NEP_RESULT
          FROM   CTE C
                 LEFT JOIN #BEFORE_WAC B
                        ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                           AND C.RS_CONTRACT_SID = B.RS_CONTRACT_SID
                           AND C.PERIOD_SID <= B.PERIOD_SID
          WHERE  C.RESET_TYPE = 'VIOLATION DATE'
                 AND C.RESET_ELIGIBLE = 'YES'
                   AND NOT EXISTS(SELECT 1
                                  FROM   #TEMP_NM_PPA_PROJECTION T
                                  WHERE  ( (( T.NET_BASE_PRICE = 'YES'
                                              AND NULLIF(T.NET_BASE_PRICE_FORMULA, 0) IS NOT NULL ))
                                            OR (( T.NET_PRICE_TYPE = 'YES'
                                                  AND NULLIF(T.NET_PRICE_TYPE_FORMULA, 0) IS NOT NULL ))
                                            OR (( T.NET_SUBSEQUENT_PERIOD_PRICE = 'YES'
                                                  AND NULLIF(T.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA, 0) IS NOT NULL ))
                                            OR (( T.NET_RESET_PRICE_TYPE = 'YES'
                                                  AND NULLIF(T.NET_RESET_PRICE_FORMULA, 0) IS NOT NULL )) )
                                         AND T.CCP_DETAILS_SID = C.CCP_DETAILS_SID
                                         AND T.RS_CONTRACT_SID = C.RS_CONTRACT_SID
                                         AND T.PERIOD_SID = C.PERIOD_SID)
/*SELECT C.*,
                   CASE
                     WHEN LEFT(PRICE_TOLERANCE_TYPE, 1) = 'D' THEN
                       CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                         WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                         ELSE Iif(RESET_GRP = 1, BASE_PRICE_TYPE, First_value(RESET_PRICE_VALUE) OVER ( PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP ORDER BY PERIOD_SID ))
                              + Sum(PRICE_TOLERANCE) OVER ( PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP ORDER BY PERIOD_SID )
                       END
                     WHEN LEFT(PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
                       CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                         WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                         ELSE Iif(RESET_GRP = 1, BASE_PRICE_TYPE, First_value(RESET_PRICE_VALUE)
                                                                    OVER (
                                                                      PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                      ORDER BY PERIOD_SID )) * Power(( 1 + PRICE_TOLERANCE / 100.0 ), FIRST_PERIOD)
                       END
                   END AS NEP_WITHOUT_NETTING
            INTO   #VIOLATION_NEP_RESULT
            FROM   CTE C
                   LEFT JOIN #BEFORE_WAC B
                          ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                             AND C.RS_CONTRACT_SID = B.RS_CONTRACT_SID
                             AND C.PERIOD_SID <= B.PERIOD_SID
            WHERE  C.RESET_TYPE = 'VIOLATION DATE'
                   AND C.RESET_ELIGIBLE = 'YES'
                   AND NOT EXISTS(SELECT 1
                                  FROM   #TEMP_NM_PPA_PROJECTION T
                                  WHERE  ( (( T.NET_BASE_PRICE = 'YES'
                                              AND NULLIF(T.NET_BASE_PRICE_FORMULA, 0) IS NOT NULL ))
                                            OR (( T.NET_PRICE_TYPE = 'YES'
                                                  AND NULLIF(T.NET_PRICE_TYPE_FORMULA, 0) IS NOT NULL ))
                                            OR (( T.NET_SUBSEQUENT_PERIOD_PRICE = 'YES'
                                                  AND NULLIF(T.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA, 0) IS NOT NULL ))
                                            OR (( T.NET_RESET_PRICE_TYPE = 'YES'
                                                  AND NULLIF(T.NET_RESET_PRICE_FORMULA, 0) IS NOT NULL )) )
                                         AND T.CCP_DETAILS_SID = C.CCP_DETAILS_SID
                                         AND T.RS_CONTRACT_SID = C.RS_CONTRACT_SID
                                         AND T.PERIOD_SID = C.PERIOD_SID)*/
            IF Object_id('tempdb..#TEST') IS NOT NULL
            DROP TABLE #TEST;;
		  SELECT *
          INTO   #TEST
          FROM   (SELECT A.*,
                         Row_number()
                           OVER(
                             PARTITION BY a.CCP_DETAILS_SID, a.RS_CONTRACT_SID
                             ORDER BY a.PERIOD_SID DESC) LAST_PERIOD,
                         /*SUM(a.PRICE_TOLERANCE)
                           OVER (
                             PARTITION BY a.CCP_DETAILS_SID, a.RS_CONTRACT_SID
                             ORDER BY a.PERIOD_SID )     AS NEP */
                         NEP_WITHOUT_NETTING             AS NEP
                  FROM   (SELECT *,
                                 ( Row_number()
                                     OVER(
                                       PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID
                                       ORDER BY PERIOD_SID) - 1 ) % PT_SEQ + 1 GRP
                          FROM   #VIOLATION_NEP_RESULT) A
                         JOIN #PROJECTION_DATES PD
                           ON PD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         JOIN #PERIOD P
                           ON P.PERIOD_DATE = PD.PROJ_START_DATE
                  WHERE  a.GRP = 1
                         AND A.PERIOD_SID >= P.PERIOD_SID) A
          WHERE  LAST_PERIOD = 1----------cel-1465
            /*SELECT *
            INTO   #TEST
            FROM   (SELECT A.*,
                           Row_number()
                             OVER(
                               PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID
                               ORDER BY PERIOD_SID DESC) LAST_PERIOD,
                           Sum(PRICE_TOLERANCE)
                             OVER (
                               PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID
                               ORDER BY PERIOD_SID )     AS NEP
                    FROM   (SELECT *,
                                   ( Row_number()
                                       OVER(
                                         PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID
                                         ORDER BY PERIOD_SID) - 1 ) % PT_SEQ + 1 GRP
                            FROM   #VIOLATION_NEP_RESULT) A
                           JOIN #PROJECTION_DATES PD
                             ON PD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           JOIN #PERIOD P
                             ON P.PERIOD_DATE = PD.PROJ_START_DATE
                    WHERE  GRP = 1
                           AND A.PERIOD_SID <= P.PERIOD_SID) A
            WHERE  LAST_PERIOD = 1*/

            CREATE UNIQUE CLUSTERED INDEX IX_LOAD_RESET_RUNN_TOTAL
              ON #RESULT_PREW(PERIOD_SID, CCP_DETAILS_SID, RS_CONTRACT_SID);

        ----------updating the price information both nep and wac in the RESULT_PREW table for those ccp+d combination
        ;
          WITH REC_CTE
               AS (SELECT RP.CCP_DETAILS_SID,
                          RP.RS_CONTRACT_SID,
                          RP.PRICE_TOLERANCE_TYPE,
                          RP.BASE_PRICE_TYPE,
                          T.NEP,
                          RP.NEW_WAC_PRICE_VALUE,
                          T.NEP                                     AS RUNNING_TOTAL,
                          Iif (T.NEP > T.NEW_WAC_PRICE_VALUE, 1, 0) AS FLAG,
                          1                                         AS RESET_PERIOD,
                          1                                         AS PTF_RESET,
                          T.NEW_WAC_PRICE_VALUE                     AS PREV_WAC,
                          T.PERIOD_SID
                   FROM   #TEST T
                          JOIN #RESULT_PREW RP
                            ON T.CCP_DETAILS_SID = RP.CCP_DETAILS_SID
                               AND T.RS_CONTRACT_SID = RP.RS_CONTRACT_SID
                               AND T.PERIOD_SID = RP.PERIOD_SID
                   UNION ALL
                   SELECT R.CCP_DETAILS_SID,
                          R.RS_CONTRACT_SID,
                          R.PRICE_TOLERANCE_TYPE,
                          R.BASE_PRICE_TYPE,
                          C.NEP,
                          R.RESET_PRICE_VALUE,
                          Iif(C.FLAG = 1, CASE
                                            WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'D' THEN
                                              CASE
                                                WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                                                ELSE R.RESET_PRICE_VALUE + R.PRICE_TOLERANCE
                                              END
                                            WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
                                              CASE
                                                WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                                                ELSE R.RESET_PRICE_VALUE * ( 1 + R.PRICE_TOLERANCE / 100 )
                                              END
                                          END, CASE
                                                 WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'D' THEN
                                                   CASE
                                                     WHEN PTF_RESET <> PT_SEQ THEN C.RUNNING_TOTAL
                                                     ELSE
                                                       CASE
                                                         WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                                                         ELSE C.RUNNING_TOTAL + R.PRICE_TOLERANCE
                                                       END
                                                   END
                                                 WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
                                                   CASE
                                                     WHEN PTF_RESET <> PT_SEQ THEN C.RUNNING_TOTAL
                                                     ELSE
                                                       CASE
                                                         WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                                                         ELSE C.RUNNING_TOTAL *(1+ R.PRICE_TOLERANCE/100)
                                                       END
                                                   END
                                               END),
                          Iif(Iif(C.FLAG = 1, CASE
                                            WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'D' THEN
                                              CASE
                                                WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                                                ELSE R.RESET_PRICE_VALUE + R.PRICE_TOLERANCE
                                              END
                                            WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
                                              CASE
                                                WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                                                ELSE R.RESET_PRICE_VALUE * ( 1 + R.PRICE_TOLERANCE / 100 )
                                              END
                                          END, CASE
                                                 WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'D' THEN
                                                   CASE
                                                     WHEN PTF_RESET <> PT_SEQ THEN C.RUNNING_TOTAL
                                                     ELSE
                                                       CASE
                                                         WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                                                         ELSE C.RUNNING_TOTAL + R.PRICE_TOLERANCE
                                                       END
                                                   END
                                                 WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
                                                   CASE
                                                     WHEN PTF_RESET <> PT_SEQ THEN C.RUNNING_TOTAL
                                                     ELSE
                                                       CASE
                                                         WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                                                         ELSE C.RUNNING_TOTAL *(1+ R.PRICE_TOLERANCE/100)
                                                       END
                                                   END
                                               END) > Iif(C.FLAG = 1
                                                               OR RESET_PERIOD = RESET_SEQ, R.NEW_WAC_PRICE_VALUE, C.PREV_WAC), 1, 0),
                          RESET_PERIOD = ( Iif(C.FLAG = 0, RESET_PERIOD + 1, 1) - 1 ) % RESET_SEQ + 1,
                          PTF_RESET = ( Iif(C.FLAG = 0, PTF_RESET + 1, 1) - 1 ) % PT_SEQ + 1,
                          PREV_WAC = Iif(C.FLAG = 1
                                          OR RESET_PERIOD = RESET_SEQ, R.NEW_WAC_PRICE_VALUE, C.PREV_WAC),
                          R.PERIOD_SID
                   FROM   REC_CTE C
                          JOIN #RESULT_PREW R
                            ON R.PERIOD_SID = C.PERIOD_SID + 1
                               AND C.CCP_DETAILS_SID = R.CCP_DETAILS_SID
                               AND C.RS_CONTRACT_SID = R.RS_CONTRACT_SID)
          UPDATE RP
          SET    RP.NEP_RESULT = RUNNING_TOTAL,
                 RP.WAC_RESULT = PREV_WAC,
                 RP.HARD_RESET_PERIODS = RC.FLAG
          FROM   #RESULT_PREW RP
                 JOIN REC_CTE RC
                   ON RP.CCP_DETAILS_SID = RC.CCP_DETAILS_SID
                      AND RP.RS_CONTRACT_SID = RC.RS_CONTRACT_SID
                      AND RP.PERIOD_SID = RC.PERIOD_SID
			/*;WITH REC_CTE
                 AS (SELECT RP.CCP_DETAILS_SID,
                            RP.RS_CONTRACT_SID,
                            RP.BASE_PRICE_TYPE,
                            NEP,
                            RP.NEW_WAC_PRICE_VALUE,
                            NEP                                     AS RUNNING_TOTAL,
                            Iif (NEP > T.NEW_WAC_PRICE_VALUE, 1, 0) AS FLAG,
                            1                                       AS RESET_PERIOD,
                            1                                       AS PTF_RESET,
                            T.NEW_WAC_PRICE_VALUE                   AS PREV_WAC,
                            T.PERIOD_SID
                     FROM   #TEST T
                            JOIN #RESULT_PREW RP
                              ON T.CCP_DETAILS_SID = RP.CCP_DETAILS_SID
                                 AND T.RS_CONTRACT_SID = RP.RS_CONTRACT_SID
                                 AND T.PERIOD_SID = RP.PERIOD_SID
                     UNION ALL
                     SELECT R.CCP_DETAILS_SID,
                            R.RS_CONTRACT_SID,
                            R.BASE_PRICE_TYPE,
                            C.NEP,
                            R.RESET_PRICE_VALUE,
                            Iif(C.FLAG = 1, CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                                              WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                                              ELSE R.RESET_PRICE_VALUE + R.PRICE_TOLERANCE
                                            END, CASE
                                                   WHEN PTF_RESET <> PT_SEQ THEN C.RUNNING_TOTAL
                                                   ELSE
                                                     CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                                                       WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                                                       ELSE C.RUNNING_TOTAL + R.PRICE_TOLERANCE
                                                     END
                                                 END),
                            Iif(Iif(C.FLAG = 1, CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                                                  WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                                                  ELSE R.RESET_PRICE_VALUE + R.PRICE_TOLERANCE
                                                END, CASE
                                                       WHEN PTF_RESET <> PT_SEQ THEN C.RUNNING_TOTAL
                                                       ELSE
                                                         CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                                                           WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                                                           ELSE C.RUNNING_TOTAL + R.PRICE_TOLERANCE
                                                         END
                                                     END) > Iif(C.FLAG = 1
                                                                 OR RESET_PERIOD = RESET_SEQ, R.NEW_WAC_PRICE_VALUE, C.PREV_WAC), 1, 0),
                            RESET_PERIOD = ( Iif(C.FLAG = 0, RESET_PERIOD + 1, 1) - 1 ) % RESET_SEQ + 1,
                            PTF_RESET = ( Iif(C.FLAG = 0, PTF_RESET + 1, 1) - 1 ) % PT_SEQ + 1,
                            PREV_WAC = Iif(C.FLAG = 1
                                            OR RESET_PERIOD = RESET_SEQ, R.NEW_WAC_PRICE_VALUE, C.PREV_WAC),
                            R.PERIOD_SID
                     FROM   REC_CTE C
                            JOIN #RESULT_PREW R
                              ON R.PERIOD_SID = C.PERIOD_SID + 1
                                 AND C.CCP_DETAILS_SID = R.CCP_DETAILS_SID
                                 AND C.RS_CONTRACT_SID = R.RS_CONTRACT_SID)
            UPDATE RP
            SET    RP.NEP_RESULT = RUNNING_TOTAL,
                   RP.WAC_RESULT = PREV_WAC,
                   RP.hard_reset_periods = rc.FLAG
            FROM   #RESULT_PREW RP
                   JOIN REC_CTE RC
                     ON RP.CCP_DETAILS_SID = RC.CCP_DETAILS_SID
                        AND RP.RS_CONTRACT_SID = RC.RS_CONTRACT_SID
                        AND RP.PERIOD_SID = RC.PERIOD_SID
            UPDATE RP
            SET    NEP_RESULT = OA.NEP_WITHOUT_NETTING
            FROM   #RESULT_PREW RP
                   CROSS APPLY (SELECT TOP 1 NEP_WITHOUT_NETTING,
                                             PERIOD_SID
                                FROM   #VIOLATION_NEP_RESULT NR
                                WHERE  RP.CCP_DETAILS_SID = NR.CCP_DETAILS_SID
                                       AND RP.RS_CONTRACT_SID = NR.RS_CONTRACT_SID
                                       AND RP.PERIOD_SID >= NR.PERIOD_SID
                                ORDER  BY PERIOD_SID DESC) OA*/
        END

      CREATE NONCLUSTERED INDEX NIX_RESULT_PREW
        ON #RESULT_PREW (PERIOD_SID, CCP_DETAILS_SID, RS_CONTRACT_SID)

      CREATE NONCLUSTERED INDEX NIX_NEP_RESULT
        ON #NEP_RESULT (PERIOD_SID, CCP_DETAILS_SID, RS_CONTRACT_SID)
        INCLUDE (NEP_WITHOUT_NETTING)

      UPDATE RP
      SET    NEP_RESULT = OA.NEP_WITHOUT_NETTING
      FROM   #RESULT_PREW RP
             OUTER APPLY (SELECT TOP 1 NEP_WITHOUT_NETTING
                          FROM   #NEP_RESULT NR
                          WHERE  RP.CCP_DETAILS_SID = NR.CCP_DETAILS_SID
                                 AND RP.RS_CONTRACT_SID = NR.RS_CONTRACT_SID
                                 AND RP.PERIOD_SID >= NR.PERIOD_SID
                          ORDER  BY PERIOD_SID DESC) OA
    WHERE  NOT EXISTS(SELECT 1
                      FROM   #TEMP_NM_PPA_PROJECTION th
                      WHERE  th.RESET_TYPE = 'VIOLATION DATE'
                             AND th.RESET_ELIGIBLE = 'YES'
                             AND rp.CCP_DETAILS_SID = th.CCP_DETAILS_SID
                             AND rp.RS_CONTRACT_SID = th.RS_CONTRACT_SID)

      ----------------wac information  was obtained for the periods for the projection periods excpet for the reset type violation date----------
      IF Object_id('TEMPDB..#WAC_RESULT') IS NOT NULL
        DROP TABLE #WAC_RESULT;

  ; /*
           SELECT CCP_DETAILS_SID,
                  RS_CONTRACT_SID,
                  PERIOD_SID,
                  WAC_PRICE_VALUE RES_WAC
           INTO   #WAC_RESULT
           FROM   #RESULT_PREW
           WHERE  REBATE_FREQUENCY1 = 1
       	*/
      SELECT CCP_DETAILS_SID,
             RS_CONTRACT_SID,
             PERIOD_SID,
             RES_WAC=WAC_PRICE_VALUE
      INTO   #WAC_RESULT
      FROM   (SELECT Row_number()
                       OVER(
                         PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, TTE
                         ORDER BY PERIOD_SID)RNO,
                     CCP_DETAILS_SID,
                     RS_CONTRACT_SID,
                     PERIOD_SID,
                     WAC_PRICE_VALUE
              FROM   #RESULT_PREW R
              WHERE  PERIOD_SID >= WAC_START_SID
                     AND PERIOD_SID <= WAC_END_SID
                     -- AND RESET_ELIGIBLE <> 'YES'
                     --  AND RESET_TYPE <> 'VIOLATION DATE' or RESET_TYPE is null
                     AND NOT EXISTS (SELECT 1
                                     FROM   #TEMP_NM_PPA_PROJECTION m
                                     WHERE  m.CCP_DETAILS_SID = r.CCP_DETAILS_SID
                                            AND m.RS_CONTRACT_SID = r.RS_CONTRACT_SID
                                            AND m.RESET_TYPE = 'VIOLATION DATE'
                                            AND m.RESET_ELIGIBLE = 'yes'))A
      WHERE  RNO = 1

      CREATE NONCLUSTERED INDEX nix_WAC_RESULT
        ON #WAC_RESULT (PERIOD_SID, CCP_DETAILS_SID, RS_CONTRACT_SID)
        include (RES_WAC)

      UPDATE RP
      SET    WAC_RESULT = Isnull(OA.RES_WAC, 0) --SELECT RES_WAC
      FROM   #RESULT_PREW RP
             OUTER APPLY (SELECT TOP 1 RES_WAC
                          FROM   #WAC_RESULT NR
                          WHERE  RP.CCP_DETAILS_SID = NR.CCP_DETAILS_SID
                                 AND RP.RS_CONTRACT_SID = NR.RS_CONTRACT_SID
                                 AND RP.PERIOD_SID >= NR.PERIOD_SID
                          ORDER  BY PERIOD_SID DESC) OA
    WHERE  NOT EXISTS(SELECT 1
                      FROM   #TEMP_NM_PPA_PROJECTION th
                      WHERE  th.RESET_TYPE = 'VIOLATION DATE'
                             AND th.RESET_ELIGIBLE = 'YES'
                             AND rp.CCP_DETAILS_SID = th.CCP_DETAILS_SID
                             AND rp.RS_CONTRACT_SID = th.RS_CONTRACT_SID)

      --------------identified the netting ccp's and placed the information in the table which will be used in ppa projection--------------
      --------------instead of calling the same information from first step this table will gives the result--------------
      SET @SQL= Concat('
            IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''', @NETTING_LOGIC_CCPS, '''
                      AND TABLE_SCHEMA = ''DBO'')
					  BEGIN
			   DROP TABLE ', @NETTING_LOGIC_CCPS, ' 
       END
          
      SELECT NC.*,
             T.NET_BASE_PRICE_FORMULA,
             T.NET_PRICE_TYPE_FORMULA,
             T.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA,
             T.NET_RESET_PRICE_FORMULA
      INTO   ', @NETTING_LOGIC_CCPS, '
      FROM   #RESULT_PREW NC
             JOIN #TEMP_NM_PPA_PROJECTION T
               ON T.CCP_DETAILS_SID = NC.CCP_DETAILS_SID
                  AND T.RS_CONTRACT_SID = NC.RS_CONTRACT_SID
      WHERE  ( (( T.NET_BASE_PRICE = ''YES''
                  AND NULLIF(T.NET_BASE_PRICE_FORMULA, 0) IS NOT NULL ))
                OR (( T.NET_PRICE_TYPE = ''YES''
                      AND NULLIF(T.NET_PRICE_TYPE_FORMULA, 0) IS NOT NULL ))
                OR (( T.NET_SUBSEQUENT_PERIOD_PRICE = ''YES''
                      AND NULLIF(T.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA, 0) IS NOT NULL ))
                OR (( T.NET_RESET_PRICE_TYPE = ''YES''
                      AND NULLIF(T.NET_RESET_PRICE_FORMULA, 0) IS NOT NULL )) )
')

      EXEC Sp_executesql
        @sql

      --------------identified the reset periods for the ccps which will be used for the calcualtion of price change--------------
      SET @SQL= Concat('
            IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''', @ST_PPA_RESET_PERIODS, '''
                      AND TABLE_SCHEMA = ''DBO'')
					  BEGIN
			   DROP TABLE ', @ST_PPA_RESET_PERIODS, ' 
       END
          
CREATE TABLE ', @ST_PPA_RESET_PERIODS, '
  (
     CCP_DETAILS_SID  INT,
     RS_CONTRACT_SID  INT,
     PERIOD_SID       SMALLINT,
     REBATE_FREQUENCY CHAR(1)
  )
  ')

      EXEC Sp_executesql
        @sql

      SET @SQL= Concat('insert into ', @ST_PPA_RESET_PERIODS, '(CCP_DETAILS_SID,RS_CONTRACT_SID,PERIOD_SID,REBATE_FREQUENCY)
SELECT  CCP_DETAILS_SID,
                RS_CONTRACT_SID,
				PERIOD_SID,
                LEFT(REBATE_FREQUENCY, 1) AS REBATE_FREQUENCY
FROM   #RESULT_PREW C
WHERE  hard_reset_periods = 1 
    and not exists(select 1 from #TEMP_NM_PPA_PROJECTION T
      WHERE  ( (( T.NET_BASE_PRICE = ''YES''
                  AND NULLIF(T.NET_BASE_PRICE_FORMULA, 0) IS NOT NULL ))
                OR (( T.NET_PRICE_TYPE = ''YES''
                      AND NULLIF(T.NET_PRICE_TYPE_FORMULA, 0) IS NOT NULL ))
                OR (( T.NET_SUBSEQUENT_PERIOD_PRICE = ''YES''
                      AND NULLIF(T.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA, 0) IS NOT NULL ))
                OR (( T.NET_RESET_PRICE_TYPE = ''YES''
                      AND NULLIF(T.NET_RESET_PRICE_FORMULA, 0) IS NOT NULL )) )
					  and 
               T.CCP_DETAILS_SID = C.CCP_DETAILS_SID
                  AND T.RS_CONTRACT_SID = C.RS_CONTRACT_SID)
')

      EXEC Sp_executesql
        @sql

      --------------identified the wac for the ccps which will be used for the calcualtion of price change--------------
      SET @SQL= Concat('
            IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''', @ST_PPA_WAC_PRICE, '''
                      AND TABLE_SCHEMA = ''DBO'')
					  BEGIN
			   DROP TABLE ', @ST_PPA_WAC_PRICE, ' 
       END
          
CREATE TABLE ', @ST_PPA_WAC_PRICE, '
  (
     CCP_DETAILS_SID  INT,
     RS_CONTRACT_SID  INT,
     PERIOD_SID      SMALLINT,
     WAC_PRICE       NUMERIC(22, 6)
  )
  ')

      EXEC Sp_executesql
        @sql

      SET @SQL= Concat('insert into ', @ST_PPA_WAC_PRICE, '(CCP_DETAILS_SID,RS_CONTRACT_SID,PERIOD_SID,WAC_PRICE)
select distinct CCP_DETAILS_SID,RS_CONTRACT_SID,PERIOD_SID,WAC_PRICE_VALUE from #RESULT_PREW C where PERIOD_SID >= WAC_START_SID
                     AND PERIOD_SID <= WAC_END_SID 
					 ')

      EXEC Sp_executesql
        @sql

      --      EXEC('
      --;WITH CTE
      --     AS (SELECT R.*,
      --                FIRST_VALUE(CASE
      --                              WHEN WAC_ZERO_PERIOD = CASE LEFT(REBATE_FREQUENCY, 1)
      --                                                       WHEN ''M'' THEN CONCAT(''M'', PERIOD_MONTH, PERIOD_YEAR)
      --                                                       WHEN ''Q'' THEN CONCAT(''Q'', PERIOD_QUARTER, PERIOD_YEAR)
      --                                                       WHEN ''S'' THEN CONCAT(''S'', PERIOD_SEMI, PERIOD_YEAR)
      --                                                       ELSE CONCAT(''Y'', PERIOD_YEAR)
      --                                                     END THEN NULL
      --                              ELSE WAC_PRICE_VALUE
      --                            END)
      --                  OVER(
      --                    PARTITION BY r.CCP_DETAILS_SID, r.RS_CONTRACT_SID, CASE LEFT(REBATE_FREQUENCY, 1) WHEN ''M'' THEN CONCAT( PERIOD_MONTH, PERIOD_YEAR) WHEN ''Q'' THEN
      --					 CONCAT(PERIOD_QUARTER, PERIOD_YEAR) WHEN ''S'' THEN CONCAT( PERIOD_SEMI, PERIOD_YEAR) ELSE PERIOD_YEAR END order by R.PERIOD_SID) AS WAC_FOR_PRICE_CHANGE
      --         FROM   #RESULT_PREW R
      --                JOIN (SELECT DISTINCT CCP_DETAILS_SID,
      --                                      RS_CONTRACT_SID,
      --                                      CASE LEFT(REBATE_FREQUENCY, 1)
      --                                        WHEN ''M'' THEN CONCAT(''M'', PERIOD_MONTH, PERIOD_YEAR)
      --                                        WHEN ''Q'' THEN CONCAT(''Q'', PERIOD_QUARTER, PERIOD_YEAR)
      --                                        WHEN ''S'' THEN CONCAT(''S'', PERIOD_SEMI, PERIOD_YEAR)
      --                                        ELSE CONCAT(''Y'', PERIOD_YEAR)
      --                                      END AS WAC_ZERO_PERIOD
      --                      FROM   #RESULT_PREW R
      --                             JOIN #PERIOD P
      --                               ON P.PERIOD_SID = R.PERIOD_SID
      --                                  AND RESET_PERIODS_RN = 1) B
      --                  ON R.CCP_DETAILS_SID = B.CCP_DETAILS_SID
      --                     AND R.RS_CONTRACT_SID = B.RS_CONTRACT_SID
      --                JOIN #PERIOD P1
      --                  ON P1.PERIOD_SID = R.PERIOD_SID)
      --UPDATE PP
      --SET    PP.PRICECHANGE = A.WAC_CHANGE
      --FROM   '+@PROJECTION_TABLE+' PP
      --       JOIN (SELECT WAC_CHANGE= ISNULL(( WAC_FOR_PRICE_CHANGE - LAG(WAC_FOR_PRICE_CHANGE, CASE LEFT(REBATE_FREQUENCY, 1)
      --                                                                                            WHEN ''M'' THEN 1
      --                                                                                            WHEN ''Q'' THEN 3
      --                                                                                            WHEN ''S'' THEN 6
      --                                                                                            ELSE 12
      --                                                                                          END)
      --                                                                  OVER(
      --                                                                    PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID order by PERIOD_SID ) / NULLIF(LAG(WAC_FOR_PRICE_CHANGE, CASE LEFT(REBATE_FREQUENCY, 1)
      --                                                                                                                                                         WHEN ''M'' THEN 1
      --                                                                                                                                                         WHEN ''Q'' THEN 3
      --                                                                                                                                                         WHEN ''S'' THEN 6
      --                                                                                                                                                         ELSE 12
      --                                                                                                                                                       END)
      --                                                                                                                               OVER(
      --                                                                                                                                 PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID order by PERIOD_SID ), 0) * 100 ), 0),
      --                    CCP_DETAILS_SID,
      --                    RS_CONTRACT_SID,
      --                    PERIOD_SID
      --             FROM   CTE) A
      --         ON A.CCP_DETAILS_SID = PP.CCP_DETAILS_SID
      --            AND A.RS_CONTRACT_SID = PP.RS_CONTRACT_SID
      --            AND A.PERIOD_SID = PP.PERIOD_SID ')
      ---------final update in the main table----------------------
      EXEC('
UPDATE PP
SET    PP.NETMAP = NEP_RESULT,
       PP.PRICE = WAC_RESULT,
       PP.PROJECTION_SALES = IIF(WAC_RESULT - NEP_RESULT > 0, WAC_RESULT - NEP_RESULT, 0),
	   PP.PROJECTION_DISCOUNT_UNITS = SP.PROJECTION_UNITS ,
	   PP.PROJECTION_RATE = (SP.PROJECTION_UNITS * IIF(WAC_RESULT - NEP_RESULT > 0, WAC_RESULT - NEP_RESULT, 0)) / NULLIF(SP.PROJECTION_SALES,0) * 100,
	   PP.PROJECTION_DISCOUNT_DOLLAR = (SP.PROJECTION_UNITS * IIF(WAC_RESULT - NEP_RESULT > 0, WAC_RESULT - NEP_RESULT, 0)) 
	   ,TOTALDEDUCTIONS					=(SP.PROJECTION_UNITS * IIF(WAC_RESULT - NEP_RESULT > 0, WAC_RESULT - NEP_RESULT, 0))
	   ,NETPRICE						=WAC_RESULT	  
	   ,PRICEPROTECTIONAMOUNTPERUNIT	=IIF(WAC_RESULT - NEP_RESULT > 0, WAC_RESULT - NEP_RESULT, 0)
	   ,PRICEPROTECTIONPERCENTAGE		=(SP.PROJECTION_UNITS * IIF(WAC_RESULT - NEP_RESULT > 0, WAC_RESULT - NEP_RESULT, 0)) / NULLIF(SP.PROJECTION_SALES,0) * 100
	   ,TOTALPRICEPROTECTIONDEDUCTION	=(SP.PROJECTION_UNITS * IIF(WAC_RESULT - NEP_RESULT > 0, WAC_RESULT - NEP_RESULT, 0))
	   ,DEDUCTION_PER_UNIT				=IIF(WAC_RESULT - NEP_RESULT > 0, WAC_RESULT - NEP_RESULT, 0)
FROM   '+@PROJECTION_TABLE+' PP
       JOIN #RESULT_PREW RP
         ON PP.CCP_DETAILS_SID = RP.CCP_DETAILS_SID
            AND PP.RS_CONTRACT_SID = RP.RS_CONTRACT_SID
            AND PP.PERIOD_SID = RP.PERIOD_SID
       JOIN '+@S_PROJECTION_TABLE+' SP
         ON SP.CCP_DETAILS_SID = PP.CCP_DETAILS_SID
            AND SP.PERIOD_SID = PP.PERIOD_SID
WHERE  NOT EXISTS (SELECT 1
                   FROM   '+@NETTING_LOGIC_CCPS+' NLC
                   WHERE  NLC.CCP_DETAILS_SID = RP.CCP_DETAILS_SID
                          AND NLC.RS_CONTRACT_SID = RP.RS_CONTRACT_SID) ')---------GALUAT-870
  END


GO


