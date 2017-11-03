IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NATIONAL_ASSUMPTIONS'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NATIONAL_ASSUMPTIONS]
  END

GO

CREATE PROCEDURE [dbo].[PRC_NATIONAL_ASSUMPTIONS] (@NA_PROJ_MASTER_SID INT,
                                             @USER_ID            INT,
                                             @SESSION_ID         VARCHAR(100))
AS

/****************************************************************************************************************************
** File Name		:	PRC_NATIONAL_ASSUMPTIONS.sql
** Procedure Name	:	PRC_NATIONAL_ASSUMPTIONS
** Description		:	To generate Forecast prices for various price types for future periods based on actuals (History)

** Input Parameters	:	@NA_PROJ_MASTER_SID     - National Assumptions Projection_master_sid from data selection
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection

											
** Output Parameters:	NA
** Author Name		:	@Pradeep
** Creation Date	:	04/10/2015 - MM/DD/YYYY
** Called By		:   Called by Application on clicking next button in NATIONAL ASSUMPTIONS screen
*****************************************************************************************************************************
** Change History
*****************************************************************************************************************************
** VER   Date      Ticket No          Author          Description 
** ---   --------  -----------        ----------      -----------------------------------------------
** 1    11/08/2016  GALUAT-808        @Lakshmi        Business Unit Changes
** 1    22/07/2016  GALUAT-48         @Pradeep        ROLLING AVERAGE FOR CPI-U
*****************************************************************************************************************************/

  BEGIN
      SET NOCOUNT ON

      BEGIN TRY





	    -- Variables Initialization starts here
DECLARE @ACTUAL_START_DATE     DATETIME,
        @ACTUAL_END_DATE       DATETIME,
        @PROJECTION_START_DATE DATETIME,
        @PROJECTION_END_DATE   DATETIME,
        @BASELINE_METHODOLOGY  VARCHAR(30),
        @BASELINE_PERIOD       VARCHAR(500),
        @FORECAST_METHODOLOGY  VARCHAR(30),
        @ROLLING_PERIOD        VARCHAR(500),
        @GROWTH_RATE           NUMERIC(22, 6),
        @GROWTH_FREQUENCY      VARCHAR(25),
        @START_PERIOD          VARCHAR(10),
        @END_PERIOD            VARCHAR(10),
        @PROJ_PERIOD_START_SID INT,
        @PROJ_PERIOD_END_SID   INT,
        @ACT_PERIOD_START_SID  INT,
        @ACT_PERIOD_END_SID    INT,
        @PRICE_TYPE            VARCHAR(25),
        @PRICE_BASIS           VARCHAR(30),
        @ITEM_UOM              VARCHAR(50),
        @UN_UOM_SID            INT,
        @BUSINESS_PROCESS_TYPE VARCHAR(50) ='NATIONAL ASSUMPTIONS',
        @CPI_COMPOUNDING       VARCHAR(50),
		@ITEMID                [DBO].[UDT_ITEM] 
		-- Variables Initialization ends here

		


----------------------------------------------TEMP TABLE CHANGES--------------------------------------------------------------------------------------------------------------------

--Appending user_id and Session_id and current date to the table names to identify the uniqueness of user and session information.
DECLARE @NATIONAL_ASSUMPTIONS         VARCHAR(200) = Concat('ST_NATIONAL_ASSUMPTIONS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @MEDICAID_NEW_NDC             VARCHAR(200) = Concat('ST_MEDICAID_NEW_NDC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @FEDERAL_NEW_NDC              VARCHAR(200) = Concat('ST_FEDERAL_NEW_NDC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @NATIONAL_ASSUMPTIONS_PROJ    VARCHAR(200) = Concat('ST_NATIONAL_ASSUMPTIONS_PROJ_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @NATIONAL_ASSUMPTIONS_ACTUALS VARCHAR(200) = Concat('ST_NATIONAL_ASSUMPTIONS_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @NA_NDC9_WAC                  VARCHAR(100) =Concat('ST_NA_NDC9_WAC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @NA_NDC11_WAC                 VARCHAR(100) =Concat('ST_NA_NDC11_WAC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @NDC9_GROWTH_TABLE            VARCHAR(100)= Concat('ST_NA_NDC9_GROWTH_FACTOR_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @NDC11_GROWTH_TABLE           VARCHAR(100)= Concat('ST_NA_NDC11_GROWTH_FACTOR_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')) 

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO @ITEMID
SELECT ITEM_MASTER_SID
FROM   NA_PROJ_DETAILS
WHERE  NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID -- ITEM DETAILS IN THE CURRENT PROJECTION


--ALL OPTIONS CHOOSED BY USER IN NATION ASSUMPTIONS SCREEN FROM MASTER TABLE STARTS HERE
--DYNAMIC SQL USED BECAUSE SOURCE TABLE IS A VARIABLE
IF Object_id('TEMPDB..#NATIONAL_ASSUMPTIONS') IS NOT NULL
DROP TABLE #NATIONAL_ASSUMPTIONS

CREATE TABLE #NATIONAL_ASSUMPTIONS
(
NA_PROJ_MASTER_SID INT,
PRICE_TYPE VARCHAR(100),
BASELINE_METHODOLOGY VARCHAR(100),
FORECAST_METHODOLOGY VARCHAR(100),
BASELINE_PERIOD      varchar(500),
ROLLING_PERIOD       varchar(500),
START_PERIOD         varchar(100),
END_PERIOD           varchar(100),
GROWTH_RATE          NUMERIC(22,6), 
FREQUENCY            VARCHAR(100),
PRICE_BASIS          VARCHAR(100),
CPI_COMPOUNDING      VARCHAR(100),
LAST_MODIFIED_DATE   DATETIME 
)

DECLARE @NA NVARCHAR(MAX) = ''
SET @NA=CONCAT('INSERT INTO #NATIONAL_ASSUMPTIONS

(
         NA_PROJ_MASTER_SID,
         PRICE_TYPE,
         BASELINE_METHODOLOGY,
         FORECAST_METHODOLOGY,
         BASELINE_PERIOD,
         ROLLING_PERIOD,
         START_PERIOD,
         END_PERIOD,
         GROWTH_RATE,
         FREQUENCY,
         PRICE_BASIS,
		 CPI_COMPOUNDING,
		 LAST_MODIFIED_DATE
)

SELECT   NA_PROJ_MASTER_SID,
         PRICE_TYPE,
         BASELINE_METHODOLOGY,
         FORECAST_METHODOLOGY,
         BASELINE_PERIOD,
         ROLLING_PERIOD,
         START_PERIOD,
         END_PERIOD,
         GROWTH_RATE,
         FREQUENCY,
         PRICE_BASIS,
		 CPI_COMPOUNDING,
		 LAST_MODIFIED_DATE
FROM     ',@NATIONAL_ASSUMPTIONS,'')
--select @NA
EXEC SP_EXECUTESQL @NA

--ALL OPTIONS CHOOSED BY USER IN NATION ASSUMPTIONS SCREEN FROM MASTER TABLE ENDS HERE

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  

-- NA_CUR START CURSOR TO PARSE EACH PRICE TYPE THAT HAS BEEN SETUP IN NATIONAL ASSUMPTIONS TAB.
  
  DECLARE NA_CUR CURSOR LOCAL FAST_FORWARD FOR
  SELECT NA_PROJ_MASTER_SID,
         PRICE_TYPE,
         BASELINE_METHODOLOGY,
         FORECAST_METHODOLOGY,
         BASELINE_PERIOD,
         ROLLING_PERIOD,
         START_PERIOD,
         END_PERIOD,
         GROWTH_RATE,
         FREQUENCY,
         PRICE_BASIS,
		 CPI_COMPOUNDING
  FROM   #NATIONAL_ASSUMPTIONS NA
  WHERE  NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID
  ORDER BY NA.LAST_MODIFIED_DATE ASC

OPEN NA_CUR

FETCH NA_CUR INTO @NA_PROJ_MASTER_SID, @PRICE_TYPE, @BASELINE_METHODOLOGY, @FORECAST_METHODOLOGY, @BASELINE_PERIOD, @ROLLING_PERIOD, @START_PERIOD, @END_PERIOD, @GROWTH_RATE, @GROWTH_FREQUENCY, @PRICE_BASIS,@CPI_COMPOUNDING

WHILE @@FETCH_STATUS = 0
  BEGIN
      
	  --CREATION OF TEMP TABLES START 
      IF Object_id('TEMPDB..#ITEM_DETAILS') IS NOT NULL --  TO STORE THE ITEM DETAILS THAT WAS SELECTED IN GIVEN PROJECTION
        TRUNCATE TABLE #ITEM_DETAILS
      ELSE
        CREATE TABLE #ITEM_DETAILS
          (
             ITEM_NO         VARCHAR(50),
             ITEM_MASTER_SID INT PRIMARY KEY,
             NDC9            VARCHAR(50),
             UPPS            NUMERIC(22, 6),
             ITEM_ID         VARCHAR(50)
          )

      IF Object_id('TEMPDB..#PERIOD_QUARTER') IS NOT NULL -- TO STORE THE PERIOD INFORMATION IN QUARTER BASIS
        TRUNCATE TABLE #PERIOD_QUARTER
      ELSE
        CREATE TABLE #PERIOD_QUARTER
          (
             PERIOD_SID     INT PRIMARY KEY,
             PERIOD_YEAR    INT,
             PERIOD_QUARTER INT,
             SEMI_ANNUAL    INT,
             Q_PERIOD       VARCHAR(10),
             PERIOD_DATE    DATETIME
          )

      IF Object_id('TEMPDB..#ITEM_PRICING') IS NOT NULL -- TO STORE ITEM PRICING DETAILS OF SELECTED ITEMS 
        TRUNCATE TABLE #ITEM_PRICING
      ELSE
        CREATE TABLE #ITEM_PRICING
          (
             ITEM_MASTER_SID  INT,
             PRICE_TYPE       VARCHAR(50),
             PERIOD_SID       INT,
             ACTUAL_PRICE     NUMERIC(22, 6),
             PROJECTION_PRICE NUMERIC(22, 6),
             WAC_INCREASE     NUMERIC(22, 6),
             NDC9             VARCHAR(20),
             PERIOD_INDICATOR BIT, -- ACTUAL SYSTEM DATA(0) AND FORECASTED DATA(1)
             PRIMARY KEY (ITEM_MASTER_SID, PRICE_TYPE, PERIOD_SID)
          )

      IF Object_id('TEMPDB..#BASELINE') IS NOT NULL -- TO INSERT THE BASELINE VALUES FOR EACH ITEM'S SELECTED IN 
        TRUNCATE TABLE #BASELINE
      ELSE
        CREATE TABLE #BASELINE
          (
             ITEM_MASTER_SID INT,
             SALES           NUMERIC(22, 6),
             BASELINE_PRICE  NUMERIC(22, 6),
             PERIOD_SID      INT,
             [YEAR]          INT,
             [QUARTER]       INT
          )

      IF Object_id('TEMPDB..#EFFECTIVE_PERIOD') IS NOT NULL -- TO INSERT THE BASELINE VALUES FOR EACH ITEM'S SELECTED IN 
        TRUNCATE TABLE #EFFECTIVE_PERIOD
      ELSE
        CREATE TABLE #EFFECTIVE_PERIOD
          (
             ITEM_MASTER_SID            INT,
             EFFECTIVE_START_PERIOD_SID INT,
             EFFECTIVE_END_PERIOD_SID   INT,
             PROJ_START_PERIOD_SID      INT,
             PROJ_END_PERIOD_SID        INT
          )

      --CREATION OF TEMP TABLES END 

	  
	  
	  
-- INSERTION OF ITEM DETAILS THAT WAS SELECTED IN GIVEN PROJECTION
     
DECLARE @ITEM_DETAILS_INSERT NVARCHAR(MAX) =''

	 
	SET @ITEM_DETAILS_INSERT=CONCAT('INSERT INTO #ITEM_DETAILS
                  (ITEM_NO,
                   ITEM_MASTER_SID,
                   NDC9,
                   UPPS,
                   ITEM_ID)
      SELECT IM.ITEM_NO,
             IM.ITEM_MASTER_SID,
             IM.NDC9,
             IM.UPPS,
             IM.ITEM_ID
      FROM   NA_PROJ_DETAILS NPD
             INNER JOIN ITEM_MASTER IM
                     ON NPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
      WHERE  NPD.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,'
             AND ( ( @PRICE_TYPE IN ( ''AMP'', ''BEST PRICE'' )
                     AND NOT EXISTS (SELECT 1
                                     FROM   ',@MEDICAID_NEW_NDC,' MNN       -- ITEMS NEWLY ADDED ARE PROCESSED BY PRC_NEW_NDC procedure , so not considering them here.
                                     WHERE  MNN.NDC9 = IM.NDC9) )
                    OR ( @PRICE_TYPE IN ( ''FSS(OGA)'', ''NON-FAMP'' )
                         AND NOT EXISTS (SELECT 1
                                         FROM   ',@FEDERAL_NEW_NDC,' FNN    -- ITEMS NEWLY ADDED ARE PROCESSED BY PRC_NEW_NDC procedure , so not considering them here.
                                         WHERE  FNN.ITEM_MASTER_SID = IM.ITEM_MASTER_SID) )
                    OR @PRICE_TYPE = ''CPI-U'' )')

  EXECUTE SP_EXECUTESQL @ITEM_DETAILS_INSERT ,N'@PRICE_TYPE VARCHAR(50)',@PRICE_TYPE  = @PRICE_TYPE

					
-------------------------------------gal-808 business_unit chg-----------------------------------------

	
-------------------------------------------------------------------------------------------------------
      
	  -- INSERTION OF PERIOD INFORMATION IN QUARTER BASIS
     
	  INSERT INTO #PERIOD_QUARTER
                  (PERIOD_SID,
                   PERIOD_YEAR,
                   PERIOD_QUARTER,
                   SEMI_ANNUAL,
                   Q_PERIOD,
                   PERIOD_DATE)
      SELECT Min(PERIOD_SID) AS PERIOD_SID, 
             [YEAR],
             [QUARTER],
             SEMI_ANNUAL,
             'Q' + CONVERT(VARCHAR(2), [QUARTER]) + ' '
             + CONVERT(VARCHAR(5), [YEAR]),
             Min(PERIOD_DATE) AS PERIOD_DATE
      FROM   PERIOD
      GROUP  BY [YEAR],
                [QUARTER],
                SEMI_ANNUAL
 
 
 
 -- PULLING THE DATE RANGE FOR HISTORICAL_START TO PROJECTION_END
       
	  SELECT @ACTUAL_START_DATE = ACTUAL_START_DATE,
              @ACTUAL_END_DATE = ACTUAL_END_DATE,
              @PROJECTION_START_DATE = PROJECTION_START_DATE,
              @PROJECTION_END_DATE = PROJECTION_END_DATE
      FROM   [DBO].[Udf_na_proj_dates](@business_process_type) -- CHANGE TO NATIONAL ASSUMPTIONS

      SELECT @ITEM_UOM = 'UN' -- UOM FOR NATIONAL ASSUMPTIONS IS 'UN'

      SELECT @UN_UOM_SID = (SELECT HELPER_TABLE_SID -- UOM 'UN' HELPER_TABLE_SID HERE
                             FROM    HELPER_TABLE
                             WHERE  LIST_NAME = 'UOM' AND DESCRIPTION = 'un')
								   

      
	  -- PULLING THE PERIOD_SID's FOR HISTORICAL_START TO PROJECTION_END
	  SELECT @ACT_PERIOD_START_SID = Max(CASE
                                           WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_START_DATE), 0) THEN PERIOD_SID
                                           END),
              @ACT_PERIOD_END_SID = Max(CASE
                                         WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_END_DATE), 0) THEN PERIOD_SID
                                       END),
              @PROJ_PERIOD_START_SID = Max(CASE
                                            WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_START_DATE), 0) THEN PERIOD_SID
                                          END),
              @PROJ_PERIOD_END_SID = Max(CASE
                                          WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_END_DATE), 0) THEN PERIOD_SID
                                        END)
      FROM   (SELECT PERIOD_SID,PERIOD_DATE
              FROM   #PERIOD_QUARTER
              WHERE  PERIOD_DATE IN ( Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_START_DATE), 0), Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_END_DATE), 0), Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_START_DATE), 0), Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_END_DATE), 0) )) A

      
	  --INSERTION OF PERIODS  DATA FROM ACTUAL START TO PROJECTION END FOR GIVEN PRICE TYPE AND WAC FOR ALL THE ITEMS . NOTE : WITHOUT ITEM_PRICE DATA.
      INSERT INTO #ITEM_PRICING
                  (ITEM_MASTER_SID,
                   PRICE_TYPE,
                   PERIOD_SID,
                   NDC9)
      SELECT ID.ITEM_MASTER_SID,
              PRICE_TYPE,
              PQ.PERIOD_SID,
              NDC9
      FROM   #PERIOD_QUARTER PQ
             CROSS JOIN #ITEM_DETAILS ID
             CROSS JOIN (SELECT PRICE_TYPE
                         FROM   (VALUES(@PRICE_TYPE),(@PRICE_BASIS))V(PRICE_TYPE)) PT
      WHERE  PQ.PERIOD_SID BETWEEN @ACT_PERIOD_START_SID AND @ACT_PERIOD_END_SID
              OR PQ.PERIOD_SID BETWEEN @PROJ_PERIOD_START_SID AND @PROJ_PERIOD_END_SID

      
	    --UPDATION OF HISTORICAL WAC & @PRICE_TYPE(AMP,BP,NON-FAMP,FSS) START. 
        IF @PRICE_TYPE <> 'CPI-U' --UPDATION OF HISTORICAL ITEM PRICE FOR GIVEN PRICE TYPE 
        
		UPDATE IP
        SET    IP.ACTUAL_PRICE = F.ITEM_PRICE
        FROM   #ITEM_PRICING IP
               INNER JOIN (SELECT ITEM_MASTER_SID,
                                  CASE
                                                      WHEN PRICING_QUALIFIER = 'BP' THEN 'BEST PRICE'
                                                      WHEN PRICING_QUALIFIER = 'QFSS' THEN 'FSS(OGA)'
                                                      WHEN PRICING_QUALIFIER = 'QNON-FAMP' THEN 'NON-FAMP'
                                                      ELSE PRICING_QUALIFIER
                                                    END AS PRICING_QUALIFIER,
                                  PERIOD_SID,
                                  ITEM_PRICE
                           FROM   Udf_item_pricing(@ITEMID, CASE WHEN @PRICE_TYPE = 'BEST PRICE' THEN 'BP' WHEN @PRICE_TYPE = 'FSS(OGA)' THEN 'QFSS' WHEN @PRICE_TYPE = 'NON-FAMP' THEN 'QNON-FAMP' ELSE @PRICE_TYPE END + CASE WHEN @PRICE_BASIS IN( 'EQWAC', 'BQWAC', 'MQWAC', 'AVGQWAC' ) THEN ','+@PRICE_BASIS ELSE '' END, @ACT_PERIOD_START_SID, @PROJ_PERIOD_END_SID, @ITEM_UOM)) F
                       ON IP.ITEM_MASTER_SID = F.ITEM_MASTER_SID
                          AND IP.PRICE_TYPE = F.PRICING_QUALIFIER
                          AND IP.PERIOD_SID = F.PERIOD_SID
      
	   
	   ELSE -- UPDATION OF HISTORICAL ITEM PRICE CPI-U AND WAC(CPI IS NOT ITEM SPECIFIC SO IT IS COMMON FOR ALL ITEM)
        
		
		BEGIN
		
		UPDATE IP
        SET    IP.ACTUAL_PRICE = B.INDEX_VALUE,
               PERIOD_INDICATOR = 0
        FROM   #ITEM_PRICING IP
               INNER JOIN (SELECT ITEM_MASTER_SID,
                                  'CPI-U' AS PRICE_TYPE,
                                  PERIOD_SID + 3 AS PERIOD_SID,
                                  INDEX_VALUE
                           FROM   (SELECT Dense_rank()
                                            OVER(
                                              PARTITION BY Year(EFFECTIVE_DATE), Datepart(QQ, EFFECTIVE_DATE)
                                              ORDER BY Month(EFFECTIVE_DATE)) AS RN,
                                          EFFECTIVE_DATE,
                                          INDEX_VALUE,
                                          ITEM_MASTER_SID,
                                          PERIOD_SID
                                   FROM   CPI_INDEX_MASTER CIM
                                          RIGHT  JOIN #PERIOD_QUARTER PQ
                                                  ON PQ.PERIOD_QUARTER = Datepart(QUARTER, CIM.EFFECTIVE_DATE)
                                                  AND PQ.PERIOD_YEAR = Datepart(YEAR, CIM.EFFECTIVE_DATE)
                                                  AND INDEX_TYPE = 'CPI'
												  AND INBOUND_STATUS <> 'D'
                                          CROSS JOIN #ITEM_DETAILS ID
                                   WHERE  PQ.PERIOD_SID BETWEEN @ACT_PERIOD_START_SID - 3 AND @PROJ_PERIOD_END_SID) A
                           WHERE  RN = 3) B
                       ON IP.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                          AND IP.PERIOD_SID = B.PERIOD_SID
                          AND IP.PRICE_TYPE = B.PRICE_TYPE

        END
	  
	  
	  
	  
	  --UPDATION OF HISTORICAL WAC & @PRICE_TYPE(AMP,BP,NON-FAMP,FSS) START. 
      --UPDATION OF FORECASTED VALUES FOR GIVEN PRICE_TYPE.(BASELINE CAN BE CHOOSED FROM PROJECTION PERIODS ALSO)
	  
	  DECLARE @PROJECTION_PRICE_UPDATE NVARCHAR(MAX) = ''
	  
	  SET @PROJECTION_PRICE_UPDATE=CONCAT('UPDATE IP
      SET    IP.PROJECTION_PRICE = A.PROJECTION_PRICE,
             PERIOD_INDICATOR = 1
      FROM   #ITEM_PRICING IP
             INNER JOIN (SELECT ITEM_MASTER_SID,
                                PRICE_TYPE,
                                PERIOD_SID,
                                PROJECTION_PRICE
                         FROM   ',@NATIONAL_ASSUMPTIONS_PROJ,' NAP
                         WHERE  PRICE_TYPE = @PRICE_TYPE
                                AND PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' AND ',@PROJ_PERIOD_END_SID,'
                                AND EXISTS (SELECT 1
                                            FROM   NA_PROJ_DETAILS NPD
                                            WHERE  NPD.ITEM_MASTER_SID = NAP.ITEM_MASTER_SID
                                                   AND NPD.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,')) A
                     ON IP.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                        AND IP.PERIOD_SID = A.PERIOD_SID
                        AND IP.PRICE_TYPE = A.PRICE_TYPE')

       EXEC SP_EXECUTESQL @PROJECTION_PRICE_UPDATE,N'@PRICE_TYPE VARCHAR(50)',@PRICE_TYPE  = @PRICE_TYPE

      
	  -- UPDATING THE PRICES FOR VARIOUS PRICE BASIS. 
	  -- 'AMP', 'BEST PRICE' ARE NDC9 LEVEL PRICES WHERE AS THE OTHER PRICES ARE NDC11 LEVEL PRICES. FOLLOWING THE SAME IN CASE STATEMENT DOWN.
      -- TABLES @NA_NDC9_WAC AND @NA_NDC11_WAC ARE POPULATED BY PRC_NA_WAC_DATA.
	  
	  
	  DECLARE @SQL NVARCHAR(MAX)= ''
      IF @PRICE_BASIS IN( 'EQWAC', 'BQWAC', 'MQWAC', 'AVGQWAC' )
        BEGIN 

	SET @sql = 'UPDATE IP
                SET    IP.PROJECTION_PRICE = WAC
                FROM   #ITEM_PRICING IP
                       INNER JOIN (' + CASE WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN 'select ndc9 as ndc,period_sid,'''+@PRICE_BASIS+''' as price_type,'+@PRICE_BASIS+' as wac  from '+ @NA_NDC9_WAC ELSE 'select item_master_sid as ndc,period_sid,'''+@PRICE_BASIS+''' as price_type,'+@PRICE_BASIS+' as wac from '+ @NA_NDC11_WAC END
               + ') A
                               ON ' + CASE WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN 'IP.NDC9' ELSE 'IP.ITEM_MASTER_SID' END
               + ' = A.ndc
                                  AND IP.PERIOD_SID = A.PERIOD_SID
                                  AND IP.PRICE_TYPE = A.PRICE_TYPE'
    
    EXEC SP_EXECUTESQL @SQL
    
        END

      ELSE IF @PRICE_BASIS = 'DAY WEIGHTED WAC'
        BEGIN 
		
				set @sql = ' UPDATE IP
            SET    IP.PROJECTION_PRICE = DAY_WEIGHTED_WAC
            FROM   #ITEM_PRICING IP
                   INNER JOIN ('+ CASE WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN 'select ndc9 as ndc,period_sid,'''+@PRICE_BASIS+''' as price_type,DAY_WEIGHTED_WAC from '+ @NA_NDC9_WAC ELSE 'select item_master_sid as ndc,period_sid,'''+@PRICE_BASIS+''' as price_type,DAY_WEIGHTED_WAC from '+ @NA_NDC11_WAC END
               + ') A
                               ON ' + CASE WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN 'IP.NDC9 = A.NDC' ELSE 'IP.ITEM_MASTER_SID = A.NDC' END +' 
                           AND IP.PERIOD_SID = A.PERIOD_SID
                              AND IP.PRICE_TYPE = A.PRICE_TYPE'

							  EXEC sp_executesql @sql

        END
      ELSE IF @PRICE_BASIS = 'SALES WEIGHTED WAC'
        BEGIN 

				set @sql = ' UPDATE IP
            SET    IP.PROJECTION_PRICE = sales_WEIGHTED_WAC
            FROM   #ITEM_PRICING IP
                   INNER JOIN ('+ CASE WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN 'select ndc9 as ndc,period_sid,'''+@PRICE_BASIS+''' as price_type,sales_WEIGHTED_WAC from '+ @NA_NDC9_WAC ELSE 'select item_master_sid as ndc,period_sid,'''+@PRICE_BASIS+''' as price_type,sales_WEIGHTED_WAC from '+ @NA_NDC11_WAC END
               + ') A
                               ON ' + CASE WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN 'IP.NDC9 = A.NDC' ELSE 'IP.ITEM_MASTER_SID = A.NDC' END +' 
                           AND IP.PERIOD_SID = A.PERIOD_SID
                              AND IP.PRICE_TYPE = A.PRICE_TYPE'
							 
							  EXEC sp_executesql @sql
        END

      
	  -- WAC INCREASE PERCENTAGE IS CALCULATED HERE. UPDATING CTE INTURN UPDATES THE TABLE REFERED IN CTE.
	  ;WITH WAC_INCREASE_UPDATE_CTE
           AS (SELECT ITEM_MASTER_SID,
                      PRICE_TYPE,
                      PERIOD_SID,
                      PROJECTION_PRICE AS ITEM_PRICE,
					  lag(PROJECTION_PRICE) over(partition by ITEM_MASTER_SID ORDER BY PERIOD_SID ASC)  AS PREVIOUS_PRICE, 
                      WAC_INCREASE
               FROM   #ITEM_PRICING
               WHERE  PRICE_TYPE = @PRICE_BASIS)
      UPDATE C
      SET    C.WAC_INCREASE = D.WAC_INCREASE
      FROM   WAC_INCREASE_UPDATE_CTE C
             INNER JOIN (SELECT A.ITEM_MASTER_SID,
                                A.PRICE_TYPE,
                                A.PERIOD_SID,
                                A.ITEM_PRICE,
                                ( ( A.ITEM_PRICE - a.PREVIOUS_PRICE ) / NULLIF(a.PREVIOUS_PRICE, 0) ) * 100 AS WAC_INCREASE
                         FROM   WAC_INCREASE_UPDATE_CTE A) D
                     ON C.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                        AND C.PERIOD_SID = D.PERIOD_SID
						

      --DELETE ALL THE ACTUAL DATA OF ALL THE ITEMS FOR THE GIVEN @NA_PROJ_MASTER_SID BECAUSE NEW PRICES MAY BE ADDED IN ITEM_PRICING TABLE. 
      --SO ALWAYS STORE THE LATEST DATA FROM ITEM_PRICING FOR EVERY ITEM AND PRICE TYPE
      
	  DECLARE @DELETE_ACTUALS NVARCHAR(MAX) =''

	  SET @DELETE_ACTUALS=CONCAT('DELETE NAA
      FROM   ',@NATIONAL_ASSUMPTIONS_ACTUALS,' NAA
      WHERE  EXISTS (SELECT 1
                     FROM   #ITEM_DETAILS ID
                     WHERE  ID.ITEM_MASTER_SID = NAA.ITEM_MASTER_SID)
             AND NAA.PRICE_TYPE = @PRICE_TYPE')

      EXEC SP_EXECUTESQL @DELETE_ACTUALS ,N'@PRICE_TYPE VARCHAR(100)',@PRICE_TYPE  = @PRICE_TYPE



	  -- INSERTING HISTORICAL DATA FOR ALL THE ITEMS INTO ST_NATIONAL_ASSUMPTIONS_ACTUALS TABLE FOR GIVEN PRICE TYPES
	  
	  DECLARE @INSERT_ACTUALS NVARCHAR(MAX) = ''

	  SET @INSERT_ACTUALS=CONCat('INSERT INTO ',@NATIONAL_ASSUMPTIONS_ACTUALS,'
                  (ITEM_MASTER_SID,
                   PRICE_TYPE,
                   PERIOD_SID,
                   ACTUAL_PRICE)
      SELECT ID.ITEM_MASTER_SID,
             @PRICE_TYPE,
             PQ.PERIOD_SID,
             IP.ACTUAL_PRICE
      FROM   #PERIOD_QUARTER PQ
             CROSS JOIN #ITEM_DETAILS ID
             LEFT JOIN #ITEM_PRICING IP
                    ON IP.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
                       AND IP.PERIOD_SID = PQ.PERIOD_SID
                       AND IP.PRICE_TYPE = @PRICE_TYPE
      --    AND PERIOD_INDICATOR = 0
      WHERE  PQ.PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' AND ',@ACT_PERIOD_END_SID,'')
	
	  EXEC SP_EXECUTESQL @INSERT_ACTUALS,N'@PRICE_TYPE VARCHAR(50)',@PRICE_TYPE  = @PRICE_TYPE

      ----------------------------------------------------- BASLINE CALCULATION STARTS---------------------------------------------------------
      
	  IF @BASELINE_METHODOLOGY = 'WEIGHTED AVERAGE' -- WEIGHTED AVERAGE BASLINE CALCULATION METHODOLOGY
        BEGIN ;
            WITH ACTUALS_DATA
                 AS (SELECT A.ITEM_MASTER_SID,
                            Isnull(Sum(SALES), 0) AS SALES,
                            Min(PERIOD_SID) AS PERIOD_SID,
                            A.[YEAR],
                            A.[QUARTER]
                     FROM   (SELECT ITEM_MASTER_SID,
                                    PERIOD_SID,
                                    [YEAR],
                                    [QUARTER],
                                    PERIOD_DATE
                             FROM   PERIOD
                                    CROSS JOIN #ITEM_DETAILS
                             WHERE  PERIOD_SID BETWEEN @ACT_PERIOD_START_SID AND @ACT_PERIOD_END_SID) A---DATEADD(DD, -1, @START_DATE)) A
                            LEFT JOIN (SELECT ITEM_ID,
                                              ACCRUAL_ACTUAL_START_DATE AS [START_DATE],
                                              ACCRUAL_ACTUAL_END_DATE AS END_DATE, 
                                              ITEM_MASTER_SID,
                                              Sum(SALES_AMOUNT) / NULLIF(( Datediff(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
                                                                                 + 1 ), 0) AS SALES
                                       FROM   ACTUALS_MASTER AM
                                       WHERE  EXISTS (SELECT 1
                                                      FROM   #ITEM_DETAILS ID
                                                      WHERE  AM.ITEM_MASTER_SID = ID.ITEM_MASTER_SID)
                                              AND QUANTITY_INCLUSION = 'Y'
                                       GROUP  BY ITEM_ID,
                                                 ACCRUAL_ACTUAL_START_DATE,
                                                 ACCRUAL_ACTUAL_END_DATE,
                                                 ITEM_MASTER_SID) B
                                   ON A.PERIOD_DATE BETWEEN B.[START_DATE] AND B.END_DATE
                                      AND A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                     GROUP  BY A.[YEAR],
                               A.[QUARTER],
                               A.ITEM_MASTER_SID
                     HAVING EXISTS (SELECT PERIOD_SID
                                                FROM   #PERIOD_QUARTER P
                                                WHERE  EXISTS (SELECT TOKEN
                                                                    FROM   DBO.Udf_splitstring(@BASELINE_PERIOD, ',')U WHERE U.token=P.Q_PERIOD) AND P.PERIOD_SID=Min(A.PERIOD_SID))),
                 ITEM_PRICE_TYPE_DATA
                 AS (SELECT ITEM_MASTER_SID,
                            COALESCE(ACTUAL_PRICE, PROJECTION_PRICE) AS ITEM_PRICE,
                            PERIOD_SID,
                            PRICE_TYPE
                     FROM   #ITEM_PRICING
                     WHERE  PRICE_TYPE = @PRICE_TYPE
                            AND EXISTS (SELECT PERIOD_SID
                                               FROM   #PERIOD_QUARTER PQ
                                               WHERE  EXISTS (SELECT TOKEN
                                                                   FROM   DBO.Udf_splitstring(@BASELINE_PERIOD, ',')U WHERE U.token=PQ.Q_PERIOD) AND PQ.PERIOD_SID=#ITEM_PRICING.PERIOD_SID)),
                 INTERMEDIATE_CALC
                 AS (SELECT COALESCE(AD.ITEM_MASTER_SID, IPT.ITEM_MASTER_SID) AS ITEM_MASTER_SID, 
                            COALESCE(AD.PERIOD_SID, IPT.PERIOD_SID) AS PERIOD_SID,
                            AD.SALES * IPT.ITEM_PRICE AS TEMP,
                            AD.SALES
                     FROM   ACTUALS_DATA AD
                            FULL OUTER JOIN ITEM_PRICE_TYPE_DATA IPT
                                         ON AD.ITEM_MASTER_SID = IPT.ITEM_MASTER_SID
                                            AND AD.PERIOD_SID = IPT.PERIOD_SID)
           
		   INSERT INTO #BASELINE         -- INSERT INT) #BASELINE TABLE FOR WEIGHTED AVERAGE BASLINE CALCULATION METHODOLOGY
                        (ITEM_MASTER_SID,
                         BASELINE_PRICE)
            SELECT ITEM_MASTER_SID,
                   Sum(TEMP) / NULLIF(Sum(SALES), 0) AS BASELINE_PRICE  -- THIS IS WEIGHTED AVERAGE. WE NEED SALES FROM ACTUALS_MASTER FOR WEIGHTED AVERAGE.
            FROM   INTERMEDIATE_CALC
            GROUP  BY ITEM_MASTER_SID
			
        END
     
	 ELSE -- FOR BOTH AVERAGE AND SINGLE PERIOD METHODOLOGY. HERE NO NEED OF SALES FROM ACTUALS_MASTER FOR WEIGHTED AVERAGE.
        
		INSERT INTO #BASELINE
                    (ITEM_MASTER_SID,
                     BASELINE_PRICE)
        SELECT ITEM_MASTER_SID,
               Avg(COALESCE(ACTUAL_PRICE, PROJECTION_PRICE)) AS BASELINE_PRICE
        FROM   #ITEM_PRICING IP
               INNER JOIN #PERIOD_QUARTER PQ
                       ON IP.PERIOD_SID = PQ.PERIOD_SID
        WHERE  EXISTS (SELECT TOKEN
                               FROM   DBO.Udf_splitstring(@BASELINE_PERIOD, ',') U WHERE U.token=PQ.Q_PERIOD)
               AND PRICE_TYPE = @PRICE_TYPE
        GROUP  BY ITEM_MASTER_SID

      --------------------------------------------------------------- BASLINE CALCULATION ENDS----------------------------------------------------------------------------------------
     
	 --------- TO FIND RANGE BETWEEN EFFECTIVE PERIOD
      DECLARE @START_PERIOD_SID INT,
               @END_PERIOD_SID   INT

      SELECT @START_PERIOD_SID = Min(PERIOD_SID),
              @END_PERIOD_SID = Max(PERIOD_SID)
      FROM   #PERIOD_QUARTER
      WHERE  Q_PERIOD = @START_PERIOD OR Q_PERIOD = @END_PERIOD

      IF @PRICE_TYPE <> 'CPI-U' -- FINDING EFFECTIVE_PERIOD FOR PRICES OTHER THAN 'CPI_U'. IF PRICES ARE THERE TILL Q4 2016 , WE WILL CONSIDER Q1 2017 TO BE PROJECTION START PERIOD
        BEGIN
	select @ACT_PERIOD_START_SID , @PROJ_PERIOD_END_SID
		;WITH CTE
                 AS (SELECT ITEM_MASTER_SID,
                            Max(PERIOD_DATE) AS LAST_DATE
                     FROM   (SELECT IP.ITEM_MASTER_SID,
                                    IP.ITEM_PRICE,
                                    IPQ.PRICING_QUALIFIER,
                                    START_DATE,
                                     Isnull(END_DATE, Lead(Dateadd(DD, -1, START_DATE), 1, NULL)
                                                                  OVER(
                                                                    PARTITION BY IP.ITEM_MASTER_SID
                                                                    ORDER BY [START_DATE])) AS END_DATE
                             FROM   DBO.ITEM_PRICING IP
                                    JOIN DBO.ITEM_PRICING_QUALIFIER IPQ
                                      ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                             WHERE  IPQ.PRICING_QUALIFIER = CASE
                                                              WHEN @PRICE_TYPE = 'BEST PRICE' THEN 'BP'
                                                              WHEN @PRICE_TYPE = 'FSS(OGA)' THEN 'QFSS'
                                                              WHEN @PRICE_TYPE = 'NON-FAMP' THEN 'QNON-FAMP'
                                                              ELSE @PRICE_TYPE
                                                            END
                                    AND IP.ITEM_UOM = @UN_UOM_SID
                                    AND EXISTS (SELECT 1
                                                FROM   #ITEM_DETAILS ID
                                                WHERE  ID.ITEM_MASTER_SID = IP.ITEM_MASTER_SID)
                                    AND IP.INBOUND_STATUS <> 'D') A
                            JOIN PERIOD P
                              ON A.START_DATE <= P.PERIOD_DATE
                                 AND ( A.END_DATE >= P.PERIOD_DATE
                                        OR A.END_DATE IS NULL )
                     WHERE  PERIOD_SID BETWEEN @ACT_PERIOD_START_SID AND @PROJ_PERIOD_END_SID
                     GROUP  BY ITEM_MASTER_SID)
            INSERT INTO #EFFECTIVE_PERIOD
                        (ITEM_MASTER_SID,
                         EFFECTIVE_START_PERIOD_SID,
                         EFFECTIVE_END_PERIOD_SID,
                         PROJ_START_PERIOD_SID,
                         PROJ_END_PERIOD_SID)
            SELECT 
			
			C.ITEM_MASTER_SID,
                   CASE
                                      WHEN PQ.PERIOD_SID + 3 > @START_PERIOD_SID THEN PQ.PERIOD_SID + 3
                                      ELSE @START_PERIOD_SID
                                    END AS START_PERIOD_SID,
                   @END_PERIOD_SID,
                   PQ.PERIOD_SID + 3,
                   @PROJ_PERIOD_END_SID
            FROM   CTE C
                   INNER JOIN #PERIOD_QUARTER PQ
                           ON Datepart(QQ, LAST_DATE) = PQ.PERIOD_QUARTER
                              AND Year(LAST_DATE) = PQ.PERIOD_YEAR
        END
      ELSE
        BEGIN -- FINDING EFFECTIVE_PERIOD FOR PRICE 'CPI_U'.
		
		;WITH CTE
                 AS (SELECT ID.ITEM_MASTER_SID,
                            A.LAST_DATE
                     FROM   (SELECT Max(EFFECTIVE_DATE) AS LAST_DATE
                             FROM   CPI_INDEX_MASTER
                             WHERE  INDEX_TYPE = 'CPI' AND INBOUND_STATUS <> 'D') A
                            CROSS JOIN #ITEM_DETAILS ID)
            INSERT INTO #EFFECTIVE_PERIOD
                        (ITEM_MASTER_SID,
                         EFFECTIVE_START_PERIOD_SID,
                         EFFECTIVE_END_PERIOD_SID,
                         PROJ_START_PERIOD_SID,
                         PROJ_END_PERIOD_SID)
            SELECT C.ITEM_MASTER_SID,
                   CASE
                                      WHEN PQ.PERIOD_SID + 3 > @START_PERIOD_SID THEN PQ.PERIOD_SID + 3
                                      ELSE @START_PERIOD_SID
                                    END AS START_PERIOD_SID,
                   @END_PERIOD_SID,
                   PQ.PERIOD_SID + 3,
                   @PROJ_PERIOD_END_SID
            FROM   CTE C
                   INNER JOIN #PERIOD_QUARTER PQ
                           ON Datepart(QQ, LAST_DATE) = PQ.PERIOD_QUARTER
                              AND Year(LAST_DATE) = PQ.PERIOD_YEAR
        END


      --- FORECASTING PERIOD INSERT FOR NEW ITEM AND NEW PERIOD IN NATIONAL_ASSUMPTIONS_PROJ TABLE. CREATING ENTRIES ITEM + PERIOD ENTRIES.
      
	  DECLARE @PROJECTION_INSERT NVARCHAR(MAX) = ''
	  
	  set @PROJECTION_INSERT=concat('INSERT INTO ',@NATIONAL_ASSUMPTIONS_PROJ,'
                  (ITEM_MASTER_SID,
                   PRICE_TYPE,
                   PERIOD_SID)
      SELECT ID.ITEM_MASTER_SID,
             @PRICE_TYPE,
             PQ.PERIOD_SID
      FROM   #PERIOD_QUARTER PQ
             CROSS JOIN #ITEM_DETAILS ID
      WHERE  PQ.PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' AND ',@PROJ_PERIOD_END_SID,'
             AND NOT EXISTS (SELECT 1
                             FROM   ',@NATIONAL_ASSUMPTIONS_PROJ,' NAP
                             WHERE  NAP.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
                                    AND NAP.PERIOD_SID = PQ.PERIOD_SID
                                    AND NAP.PRICE_TYPE = @PRICE_TYPE)')
	  EXEC SP_EXECUTESQL @PROJECTION_INSERT,N'@PRICE_TYPE VARCHAR(100)',@PRICE_TYPE  = @PRICE_TYPE
   
      
	  
	  --UPDATING NATIONAL_ASSUMPTIONS_PROJ TABLE WITH PRICES WITH DATA AVAILABLE AS OF NOW IN #ITEM_PRICING.
	  
	  DECLARE @PROJECTION_UPDATE NVARCHAR(MAX) = ''

      set @PROJECTION_UPDATE=concat('UPDATE NAP
      SET    NAP.PROJECTION_PRICE = Isnull(IP.ACTUAL_PRICE, 0)
      FROM   ',@NATIONAL_ASSUMPTIONS_PROJ,' NAP
             INNER JOIN #ITEM_PRICING IP
                     ON NAP.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                        AND NAP.PERIOD_SID = IP.PERIOD_SID
                        AND NAP.PRICE_TYPE = IP.PRICE_TYPE
             INNER JOIN #EFFECTIVE_PERIOD EP
                     ON IP.ITEM_MASTER_SID = EP.ITEM_MASTER_SID
                        AND NAP.PERIOD_SID < EP.EFFECTIVE_START_PERIOD_SID
      WHERE  NAP.PRICE_TYPE = @PRICE_TYPE')

	  EXEC SP_EXECUTESQL @PROJECTION_UPDATE,N'@PRICE_TYPE VARCHAR(50)',@PRICE_TYPE  = @PRICE_TYPE

	   
      ------------------------------------------------------------------FORECASTING CALCULATION STARTS ------------------------------------------------------------------------
      
	  
	  IF @FORECAST_METHODOLOGY = 'Price Trending' -- Price Trending FORECASTING METHODOLOGY START
        
		BEGIN 

		     DECLARE @PROJ_UPDATE NVARCHAR(MAX) =''
		    
               set @PROJ_UPDATE=concat(';WITH ORDER_CTE
                 AS (SELECT IP.ITEM_MASTER_SID,IP.PERIOD_SID,'+CASE @PRICE_BASIS WHEN 'EQWAC' THEN 'EQWAC_GROWTH' 
				                                                                  WHEN 'BQWAC' THEN 'BQWAC_GROWTH' 
																				  WHEN 'MQWAC' THEN 'MQWAC_GROWTH' 
																				  WHEN 'AVGQWAC' THEN 'AVGQWAC_GROWTH' 
																				  WHEN 'DAY WEIGHTED WAC' THEN 'DAY_WEIGHTED_WAC_GROWTH'
																				  WHEN 'SALES WEIGHTED WAC' THEN 'SALES_WEIGHTED_WAC_GROWTH' END +' AS GROWTH
                     FROM   #ITEM_PRICING IP
                            INNER JOIN #EFFECTIVE_PERIOD EP
                                    ON EP.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                                       AND IP.PERIOD_SID BETWEEN EFFECTIVE_START_PERIOD_SID AND EFFECTIVE_END_PERIOD_SID
                                       AND PRICE_TYPE = @PRICE_BASIS
									   JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID=IP.ITEM_MASTER_SID
									   INNER JOIN '+CASE WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) 
									   THEN @NDC9_GROWTH_TABLE +' GT ON GT.NDC9 = IM.NDC9 AND GT.PRICE_TYPE = @PRICE_TYPE AND GT.PERIOD_SID = IP.PERIOD_SID'                         -- BOTH THE GROWTH TABLES ARE POPULATED BY APP (CUMULATIVE MULTIPLICATION)
									   ELSE @NDC11_GROWTH_TABLE +' GT ON GT.ITEM_MASTER_SID = IP.ITEM_MASTER_SID AND GT.PRICE_TYPE = @PRICE_TYPE  AND GT.PERIOD_SID = IP.PERIOD_SID'
									   END +'
									    ),
                CTE
                 AS (SELECT OC.ITEM_MASTER_SID,
                            OC.PERIOD_SID,
                            PROJECTION_PRICE= ( B.BASELINE_PRICE * GROWTH )
                     FROM   ORDER_CTE OC
                            JOIN #BASELINE B
                              ON B.ITEM_MASTER_SID = OC.ITEM_MASTER_SID)
		    UPDATE NAP
            SET    NAP.PROJECTION_PRICE = Isnull(RC.PROJECTION_PRICE, 0)
            FROM   ',@NATIONAL_ASSUMPTIONS_PROJ,' NAP
                   INNER JOIN CTE RC
                           ON NAP.ITEM_MASTER_SID = RC.ITEM_MASTER_SID
                              AND NAP.PERIOD_SID = RC.PERIOD_SID
            WHERE  PRICE_TYPE = @PRICE_TYPE')

			EXEC SP_EXECUTESQL @PROJ_UPDATE
			,N'@PRICE_BASIS VARCHAR(100),@PRICE_TYPE VARCHAR(50)',@PRICE_BASIS  = @PRICE_BASIS,@PRICE_TYPE=@PRICE_TYPE


        END -- Price Trending FORECASTING METHODOLOGY END
		 
      ELSE IF @FORECAST_METHODOLOGY = 'GROWTH' -- GROWTH FORECSTING METHODOLOGY START. THIS METHODOLOGY HAS CPI COMPOUNDING. CPI COMP

        BEGIN

            SELECT @GROWTH_RATE = CASE @GROWTH_FREQUENCY
                                    WHEN 'ANNUAL' THEN ( @GROWTH_RATE / 100.0 ) / 4.0
                                    WHEN 'SEMI-ANNUAL' THEN ( @GROWTH_RATE / 100.0 ) / 2.0
                                    ELSE ( @GROWTH_RATE / 100.0 ) -- CPI Compounding Annual or Growth Frequency quarterly 
                                  END;


            IF @CPI_COMPOUNDING <> 'QUARTERLY'
               --AND @PRICE_TYPE = 'CPI-U' --GALUAT-48 CHANGE START (ROLLING AVERAGE FOR CPI-U)
              BEGIN 
			  
			  DECLARE @PROJ_UPDATE_1 NVARCHAR(MAX) = ''
 
			   set @PROJ_UPDATE_1=concat(';WITH ORDER_CTE
                       AS (SELECT IP.ITEM_MASTER_SID,
                                  Min(PQ.PERIOD_SID)                PERIOD_SID,
                                  Row_number()
                                    OVER (
                                      PARTITION BY IP.ITEM_MASTER_SID
                                      ORDER BY Min(pq.PERIOD_SID) ) AS RN,
                                  PERIOD_YEAR
                           FROM   #ITEM_PRICING IP
                                  INNER JOIN #EFFECTIVE_PERIOD EP
                                          ON EP.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                                             AND IP.PERIOD_SID BETWEEN EFFECTIVE_START_PERIOD_SID AND EFFECTIVE_END_PERIOD_SID
                                  JOIN #PERIOD_QUARTER pq
                                    ON pq.PERIOD_SID = ip.PERIOD_SID
                           WHERE  PRICE_TYPE = @PRICE_BASIS
                           GROUP  BY IP.ITEM_MASTER_SID,
                                     PERIOD_YEAR), --THIS PART IS JUST TO GET ALL THE ITEMS WITH PERIOD_SID BETWEEN EFFECTIVE DATE. SINCE #ITEM_PRICING TABLE WILL
                       -- HAVE SAME PERIOD_SID FOR DIFFERENT PRICE TYPES SO I HAVE USED ONE PRICE TYPE(WAC) IN WHERE CLAUSE
                       RECURSIVE_CTE
                       AS (SELECT OC.ITEM_MASTER_SID,
                                  OC.PERIOD_SID,
                                  PROJECTION_PRICE= B.BASELINE_PRICE * POWER(CAST( 1 + ( @GROWTH_RATE ) AS NUMERIC(38,15) ),RN),
								  PERIOD_YEAR
                           FROM   ORDER_CTE OC
                                  JOIN #BASELINE B
                                    ON B.ITEM_MASTER_SID = OC.ITEM_MASTER_SID)
                  UPDATE NAP
                  SET    NAP.PROJECTION_PRICE = Isnull(RC.PROJECTION_PRICE, 0)
                  FROM   ',@NATIONAL_ASSUMPTIONS_PROJ,' NAP
                         JOIN #PERIOD_QUARTER pq
                           ON pq.PERIOD_SID = nap.PERIOD_SID
                         INNER JOIN RECURSIVE_CTE RC
                                 ON NAP.ITEM_MASTER_SID = RC.ITEM_MASTER_SID
                                    AND pq.PERIOD_YEAR = RC.PERIOD_YEAR
									and NAP.PERIOD_SID >= (select top 1 EFFECTIVE_START_PERIOD_SID from #EFFECTIVE_PERIOD order by EFFECTIVE_START_PERIOD_SID asc) -- we can take EFFECTIVE_START_PERIOD_SID from any item since CPI is common for all items
                  WHERE  PRICE_TYPE = @PRICE_TYPE
                  OPTION (MAXRECURSION 0)')--GALUAT-48 CHANGE END
              
			  EXEC SP_EXECUTESQL @PROJ_UPDATE_1 ,N'@PRICE_BASIS VARCHAR(100),@PRICE_TYPE VARCHAR(50),@GROWTH_RATE NUMERIC(22,6)',@PRICE_BASIS  = @PRICE_BASIS,@PRICE_TYPE=@PRICE_TYPE,@GROWTH_RATE=@GROWTH_RATE

			  END

            ELSE  -- ANNUAL CPI COMPOUNDING

              BEGIN 

			       DECLARE @PROJ_UPDATE_2 NVARCHAR(MAX) =''
			    
                  set @PROJ_UPDATE_2=concat(';WITH ORDER_CTE
                       AS (SELECT IP.ITEM_MASTER_SID,
                                  PERIOD_SID,
                                  Row_number()
                                    OVER (
                                      PARTITION BY IP.ITEM_MASTER_SID
                                      ORDER BY PERIOD_SID ) AS RN
                           FROM   #ITEM_PRICING IP
                                  INNER JOIN #EFFECTIVE_PERIOD EP
                                          ON EP.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                                             AND IP.PERIOD_SID BETWEEN EFFECTIVE_START_PERIOD_SID AND EFFECTIVE_END_PERIOD_SID
                           WHERE  PRICE_TYPE = @PRICE_BASIS), --THIS PART IS JUST TO GET ALL THE ITEMS WITH PERIOD_SID BETWEEN EFFECTIVE DATE. SINCE #ITEM_PRICING TABLE WILL
                       -- HAVE SAME PERIOD_SID FOR DIFFERENT PRICE TYPES SO I HAVE USED ONE PRICE TYPE(WAC) IN WHERE CLAUSE
                       RECURSIVE_CTE
                       AS (SELECT OC.ITEM_MASTER_SID,
                                  OC.PERIOD_SID,
                                   PROJECTION_PRICE= B.BASELINE_PRICE * POWER(CAST( 1 + ( @GROWTH_RATE ) AS NUMERIC(38,15) ),RN)
								   
                           FROM   ORDER_CTE OC
                                  JOIN #BASELINE B
                                    ON B.ITEM_MASTER_SID = OC.ITEM_MASTER_SID)
				  UPDATE NAP
                  SET    NAP.PROJECTION_PRICE = Isnull(RC.PROJECTION_PRICE, 0)
                  FROM   ',@NATIONAL_ASSUMPTIONS_PROJ,' NAP
                         INNER JOIN RECURSIVE_CTE RC
                                 ON NAP.ITEM_MASTER_SID = RC.ITEM_MASTER_SID
                                    AND NAP.PERIOD_SID = RC.PERIOD_SID
                  WHERE  PRICE_TYPE = @PRICE_TYPE
                  OPTION (MAXRECURSION 0)')

				  EXEC SP_EXECUTESQL @PROJ_UPDATE_2,N'@PRICE_BASIS VARCHAR(100),@PRICE_TYPE VARCHAR(100),@GROWTH_RATE NUMERIC(22,6)',@PRICE_BASIS  = @PRICE_BASIS,@PRICE_TYPE=@PRICE_TYPE,@GROWTH_RATE=@GROWTH_RATE

              END
        END -- GROWTH FORECSTING METHODOLOGY END

		ELSE IF  @FORECAST_METHODOLOGY = '% OF WAC'
		BEGIN 
		DECLARE @WAC_PER NUMERIC(22,6),@WSQL nVARCHAR(MAX)
		
		

	  SET @WSQL='SELECT  @WAC_PER=GROWTH_RATE FROM '+@NATIONAL_ASSUMPTIONS +' WHERE FORECAST_METHODOLOGY=''% OF WAC''  and price_type='''+@PRICE_TYPE+''''
	 

	
	  EXEC SP_EXECUTESQL @WSQL, N'@WAC_PER NUMERIC(22,6) OUTPUT',@WAC_PER=@WAC_PER OUTPUT

	 SET @WSQL=CONCAT( 'UPDATE NAP SET NAP.PROJECTION_PRICE=
	         ISNULL(',(@WAC_PER/100.00),',0)*ISNULL(IP.PROJECTION_PRICE,0) 
	  FROM #ITEM_PRICING IP
	      JOIN ',@NATIONAL_ASSUMPTIONS_PROJ,' NAP ON NAP.ITEM_MASTER_SID=IP.ITEM_MASTER_SID AND NAP.PERIOD_SID=IP.PERIOD_SID
		  JOIN #EFFECTIVE_PERIOD EP   ON NAP.PERIOD_SID BETWEEN EP.EFFECTIVE_START_PERIOD_SID AND EP.EFFECTIVE_END_PERIOD_SID
	  WHERE IP.PRICE_TYPE=@PRICE_BASIS AND NAP.PRICE_TYPE=@PRICE_TYPE  ')
 
	  EXEC SP_EXECUTESQL @WSQL,N'@PRICE_BASIS VARCHAR(100),@PRICE_TYPE VARCHAR(100)',@PRICE_BASIS=@PRICE_BASIS,@PRICE_TYPE=@PRICE_TYPE

	
		END

		
      
	  ELSE IF @FORECAST_METHODOLOGY = 'ROLLING AVERAGE' -- ROLLING AVERAGE FORECSTING METHODOLOGY START
        
		BEGIN 
------------------------------------ADDED ON 4th AUGUST 2016--------------------------------------------		
		IF OBJECT_ID('TEMPDB.DBO.#ROLLING_AVERAGE', 'U') IS NOT NULL
        DROP TABLE #ROLLING_AVERAGE;
--------------------------------------------------------------------------------------------------------		

		;WITH ITEM_PRICING
                 AS (SELECT IP.ITEM_MASTER_SID,
                            IP.ACTUAL_PRICE,
                            IP.PERIOD_SID,
                            Row_number()
                                 OVER(
                                   PARTITION BY IP.ITEM_MASTER_SID
                                   ORDER BY IP.PERIOD_SID ASC) AS RN
                     FROM   #ITEM_PRICING IP
                            INNER JOIN #PERIOD_QUARTER PQ
                                    ON PQ.PERIOD_SID = IP.PERIOD_SID
                     WHERE  PRICE_TYPE = @PRICE_TYPE
                            AND EXISTS (SELECT 1
                                        FROM   DBO.Udf_splitstring(@ROLLING_PERIOD, ',') S
                                        WHERE  S.TOKEN = PQ.Q_PERIOD))
            SELECT A.ITEM_MASTER_SID,
                   Avg(( ( A.ACTUAL_PRICE - B.ACTUAL_PRICE ) / NULLIF(B.ACTUAL_PRICE, 0) ) * 100) AS ROLLING_AVERGARE
            INTO   #ROLLING_AVERAGE
            FROM   ITEM_PRICING A
                   INNER JOIN ITEM_PRICING B -- USED INNER JOIN SINCE WE DON'T NEED THE UNMATCHED DATA 
                           ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                              AND A.RN = B.RN + 1
            GROUP  BY A.ITEM_MASTER_SID

            IF @CPI_COMPOUNDING <> 'QUARTERLY'
               AND @PRICE_TYPE = 'CPI-U'--GALUAT-48 CHANGE START
              
			  BEGIN 

			  DECLARE @PROJ_UPDATE_3 NVARCHAR(MAX) = ''

			 set @PROJ_UPDATE_3=concat(';WITH ORDER_CTE
                       AS (SELECT IP.ITEM_MASTER_SID,
                                  RA.ROLLING_AVERGARE,
                                  PERIOD_SID= Min(pq.PERIOD_SID),
                                  Row_number()
                                    OVER (
                                      PARTITION BY IP.ITEM_MASTER_SID
                                      ORDER BY Min(pq.PERIOD_SID) ) AS RN,
                                  PERIOD_YEAR
                           FROM   #ITEM_PRICING IP
                                  INNER JOIN #ROLLING_AVERAGE RA
                                          ON IP.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                                  INNER JOIN #EFFECTIVE_PERIOD EP
                                          ON EP.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                                             AND IP.PERIOD_SID BETWEEN EFFECTIVE_START_PERIOD_SID AND EFFECTIVE_END_PERIOD_SID
                                  JOIN #PERIOD_QUARTER pq
                                    ON pq.PERIOD_SID = ip.PERIOD_SID
                           WHERE  PRICE_TYPE = @PRICE_BASIS
                           GROUP  BY IP.ITEM_MASTER_SID,
                                     RA.ROLLING_AVERGARE,
                                     PERIOD_YEAR), --THIS PART IS JUST TO GET ALL THE ITEMS WITH PERIOD_SID BETWEEN EFFECTIVE DATE. SINCE #ITEM_PRICING TABLE WILL
                       -- HAVE SAME PERIOD_SID FOR DIFFERENT PRICE TYPES SO I HAVE USED ONE PRICE TYPE(WAC) IN WHERE CLAUSE
                       RECURSIVE_CTE
                       AS (SELECT OC.ITEM_MASTER_SID,
                                  OC.PERIOD_SID,
                                  OC.PERIOD_YEAR,
                                  PROJECTION_PRICE= B.BASELINE_PRICE * POWER(CAST( 1 + ( Isnull(ROLLING_AVERGARE, 0) / 100) AS NUMERIC(38,15)) ,RN)
                           FROM   ORDER_CTE OC
                                  INNER JOIN #BASELINE B
                                          ON B.ITEM_MASTER_SID = OC.ITEM_MASTER_SID)
                  UPDATE NAP
                  SET    NAP.PROJECTION_PRICE = Isnull(RC.PROJECTION_PRICE, 0)
                  FROM   ',@NATIONAL_ASSUMPTIONS_PROJ,' NAP
                         JOIN #PERIOD_QUARTER pq
                           ON pq.PERIOD_SID = nap.PERIOD_SID
                         INNER JOIN RECURSIVE_CTE RC
                                 ON NAP.ITEM_MASTER_SID = RC.ITEM_MASTER_SID
                                    AND pq.PERIOD_YEAR = RC.PERIOD_YEAR
									and NAP.PERIOD_SID >= (select top 1 EFFECTIVE_START_PERIOD_SID from #EFFECTIVE_PERIOD order by EFFECTIVE_START_PERIOD_SID asc) -- we can take EFFECTIVE_START_PERIOD_SID from any item since CPI is common for all items
                  WHERE  PRICE_TYPE = @PRICE_TYPE
                  OPTION (MAXRECURSION 0);') --GALUAT-48 CHANGE END

			
		      EXEC SP_EXECUTESQL @PROJ_UPDATE_3,N'@PRICE_TYPE VARCHAR(100),@PRICE_BASIS VARCHAR(100)',@PRICE_TYPE=@PRICE_TYPE,@PRICE_BASIS = @PRICE_BASIS
              
			  END ---- ROLLING AVERAGE FORECSTING METHODOLOGY END
            
			ELSE
              
			  BEGIN 
			  
			   DECLARE @PROJ_UPDATE_4 NVARCHAR(MAX) = '' -- FINAL UPDATE OF FORECAST PRICES TO NATIONAL_ASSUMPTIONS_PROJ TABLE

			  set @PROJ_UPDATE_4=concat(';WITH ORDER_CTE
                       AS (SELECT IP.ITEM_MASTER_SID,
                                  RA.ROLLING_AVERGARE,
                                  PERIOD_SID,
                                  Row_number()
                                    OVER (
                                      PARTITION BY IP.ITEM_MASTER_SID
                                      ORDER BY PERIOD_SID ) AS RN
                           FROM   #ITEM_PRICING IP
                                  INNER JOIN #ROLLING_AVERAGE RA
                                          ON IP.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                                  INNER JOIN #EFFECTIVE_PERIOD EP
                                          ON EP.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                                             AND IP.PERIOD_SID BETWEEN EFFECTIVE_START_PERIOD_SID AND EFFECTIVE_END_PERIOD_SID
                           WHERE  PRICE_TYPE = @PRICE_BASIS), --THIS PART IS JUST TO GET ALL THE ITEMS WITH PERIOD_SID BETWEEN EFFECTIVE DATE. SINCE #ITEM_PRICING TABLE WILL
                       -- HAVE SAME PERIOD_SID FOR DIFFERENT PRICE TYPES SO I HAVE USED ONE PRICE TYPE(WAC) IN WHERE CLAUSE
                       RECURSIVE_CTE
                       AS (SELECT OC.ITEM_MASTER_SID,
                                  OC.PERIOD_SID,
                                    PROJECTION_PRICE= B.BASELINE_PRICE * POWER(CAST( 1 + ( Isnull(ROLLING_AVERGARE, 0) / 100) AS NUMERIC(38,15)) ,RN)
                           FROM   ORDER_CTE OC
                                  INNER JOIN #BASELINE B
                                          ON B.ITEM_MASTER_SID = OC.ITEM_MASTER_SID)
                  UPDATE NAP
                  SET    NAP.PROJECTION_PRICE = Isnull(RC.PROJECTION_PRICE, 0)
                  FROM   ',@NATIONAL_ASSUMPTIONS_PROJ,' NAP
                         INNER JOIN RECURSIVE_CTE RC
                                 ON NAP.ITEM_MASTER_SID = RC.ITEM_MASTER_SID
                                    AND NAP.PERIOD_SID = RC.PERIOD_SID
                  WHERE  PRICE_TYPE = @PRICE_TYPE
                  OPTION (MAXRECURSION 0);')
              
			  EXEC SP_EXECUTESQL @PROJ_UPDATE_4,N'@PRICE_TYPE VARCHAR(100),@PRICE_BASIS VARCHAR(100) ',@PRICE_TYPE=@PRICE_TYPE,@PRICE_BASIS=@PRICE_BASIS

			  END
        END 

      FETCH NA_CUR INTO @NA_PROJ_MASTER_SID, @PRICE_TYPE, @BASELINE_METHODOLOGY, @FORECAST_METHODOLOGY, @BASELINE_PERIOD, @ROLLING_PERIOD, @START_PERIOD, @END_PERIOD, @GROWTH_RATE, @GROWTH_FREQUENCY, @PRICE_BASIS,@CPI_COMPOUNDING
  END

CLOSE NA_CUR;

DEALLOCATE NA_CUR; 

      -- NA_CUR END CURSOR TO PARSE EACH PRICE TYPE THAT HAS BEEN SETUP IN NATIONAL ASSUMPTIONS TAB.
  
 
      -- NA_CUR END CURSOR TO PARSE EACH PRICE TYPE THAT HAS BEEN SETUP IN NATIONAL ASSUMPTIONS TAB.
  
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
          );
      END CATCH
  END 
GO