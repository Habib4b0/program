IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_PROJECTION_VARIANCE'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_PROJECTION_VARIANCE]
  END

GO
CREATE PROCEDURE [dbo].[PRC_PROJECTION_VARIANCE] (
	@PROJECTION_SID VARCHAR(500),
	@USER_ID INT,
	@SESSION_ID VARCHAR(50),
	@DISCOUNT_LEVEL VARCHAR(50),
	@FREQUENCY CHAR(1),
	@CP_INDICATOR CHAR(1),
	@GROUP_FILTER VARCHAR(50),
	@GROUP_FILTER_VALUE VARCHAR(50),
	@HIERARCHY_NO VARCHAR(500) = NULL,
	@LEVEL_NO INT = NULL,
	@VIEW VARCHAR(50),
	@CUSTOM_VIEW_MASTER_SID INT = NULL,
	@VIEW_TAB VARCHAR(50),
	@UOM_CODE VARCHAR(10),
	@SALES_INCLUSION BIT,
	@DEDUCTION_INCLUSION BIT,
	@FILTER_UDCS VARCHAR(MAX) = NULL,
		@SCREEN_NAME VARCHAR(50)=NULL

	)
AS

BEGIN

DECLARE @FROM_DATE                     DATE,
        @STARTFROM                     DATE,
        @PROJECTION_DATE               DATE,
        @ACTUALPERIODS                 INT,
        @PROJECTIONPERIODS             INT
        --, @ITEM_ID VARCHAR(50)
        ,
        @NDC8                          VARCHAR(200),
        @SP                            INT,
        @SP_PROJ_SID                   INT,
        @TEMP_PROJ_SID                 VARCHAR(500),
        @START_PERIOD_SID              INT,
        @END_PERIOD_SID                INT,
        @ITEM_UDT                      UDT_ITEM,
        @FORECAST_NAME                 VARCHAR(50),
        @FORECAST_VERSION              VARCHAR(15),
        @FORECAST_NAME_INV             VARCHAR(50),
        @FORECAST_VERSION_INV          VARCHAR(15),
        @FORECAST_NAME_DM              VARCHAR(50),
        @FORECAST_VERSION_DM           VARCHAR(15),
        @CUST_RELATIONSHIP_BUILDER_SID INT,
        @DED_RELATIONSHIP_BUILDER_SID  INT,
        @BUSINESS_UNIT                 INT -----------GAL-808
        ,
        @COMPANY                       INT -----GAL-808
DECLARE @D_MASTER_TABLE     VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @D_ACTUAL_TABLE     VARCHAR(200) = Concat ('ST_NM_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @D_PROJECTION_TABLE VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
DECLARE @S_MASTER_TABLE     VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @S_ACTUAL_TABLE     VARCHAR(200) = Concat ('ST_NM_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @S_PROJECTION_TABLE VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @P_PROJECTION_TABLE VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @P_MASTER_TABLE     VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @P_ACTUAL_TABLE     VARCHAR(200) = Concat ('ST_NM_ACTUAL_PPA_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @CCP_HIERARCHY      VARCHAR(200) = Concat ('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @PRODUCT_TABLE      VARCHAR(MAX) = Concat ('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
        @ITEM_UOM_TABLE     VARCHAR(MAX) = Concat ('ST_ITEM_UOM_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
DECLARE @SQL1 NVARCHAR(MAX)

IF Object_id('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL
  DROP TABLE #CCP_DETAILS_TEMP

CREATE TABLE #CCP_DETAILS_TEMP
  (
     CCP_DETAILS_SID     INT,
     COMPANY_MASTER_SID  INT,
     ITEM_MASTER_SID     INT,
     CONTRACT_MASTER_SID INT
  )

EXEC ( 'INSERT INTO #CCP_DETAILS_TEMP(CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID)

                                           SELECT CH.CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID FROM ' + @CCP_HIERARCHY + ' CH JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID ' )

IF Object_id('TEMPDB..#ITEM_UOM_DETAILS') IS NOT NULL
  DROP TABLE #ITEM_UOM_DETAILS

CREATE TABLE #ITEM_UOM_DETAILS
  (
     ITEM_MASTER_SID INT,
     UOM_VALUE       NUMERIC(38, 6),
     UOM_CODE        VARCHAR(50)
  )

SET @SQL1 = 'INSERT INTO #ITEM_UOM_DETAILS

(ITEM_MASTER_SID,UOM_VALUE,UOM_CODE)

SELECT ITEM_MASTER_SID,UOM_VALUE,UOM_CODE FROM '
            + @ITEM_UOM_TABLE
            + ' WHERE UOM_CODE=@UOM_CODE'

EXEC Sp_executesql
  @SQL1,
  N'@UOM_CODE VARCHAR(50)',
  @UOM_CODE = @UOM_CODE

IF Object_id('TEMPDB..#MULTISELECT_DISCOUNTS') IS NOT NULL
  DROP TABLE #MULTISELECT_DISCOUNTS;

CREATE TABLE #MULTISELECT_DISCOUNTS
  (
     CCP_DETAILS_SID     INT,
     RS_CONTRACT_SID     INT,
     PRICE_GROUP_TYPE    VARCHAR(50),
     CHECK_RECORD        BIT,
     PV_FILTERS          BIT,
     SELECTED_LEVEL      VARCHAR(8000),
     RS_MODEL_SID        INT,
     DEDUCTION_INCLUSION BIT,
     SALES_INCLUSION     BIT,
     --FILTER_CCP BIT,
     SELECTED_SID        INT
  )

SET @SQL1 = Concat ('INSERT INTO #MULTISELECT_DISCOUNTS (

       CCP_DETAILS_SID

       ,RS_CONTRACT_SID

       ,PRICE_GROUP_TYPE

       ,CHECK_RECORD

       ,PV_FILTERS

       ,RS_MODEL_SID

       ,DEDUCTION_INCLUSION,SALES_INCLUSION--,FILTER_CCP

       )

SELECT DISTINCT A.CCP_DETAILS_SID

       ,A.RS_CONTRACT_SID

       ,A.PRICE_GROUP_TYPE

       ,A.CHECK_RECORD

       ,A.PV_FILTERS

       ,A.RS_MODEL_SID

       ,A.DEDUCTION_INCLUSION

       ,B1.SALES_INCLUSION--,A.FILTER_CCP

FROM  ', @D_MASTER_TABLE, ' A JOIN ', @S_MASTER_TABLE, ' B1 ON A.CCP_DETAILS_SID=B1.CCP_DETAILS_SID 

WHERE  CASE WHEN @SCREEN_NAME IN (''SALES'' ,''DISCOUNT'') THEN  1 ELSE PV_FILTERS END = 1

AND EXISTS (SELECT 1 FROM #CCP_DETAILS_TEMP B WHERE A.CCP_DETAILS_SID=B.CCP_DETAILS_SID)')

EXEC Sp_executesql
  @SQL1,
  N'@SCREEN_NAME VARCHAR(50)',
  @SCREEN_NAME = @SCREEN_NAME

IF Object_id('TEMPDB..#HELPER_TABLE') IS NOT NULL
  DROP TABLE #HELPER_TABLE;

CREATE TABLE #HELPER_TABLE
  (
     HELPER_TABLE_SID INT,
     DESCRIPTION      VARCHAR(100)
  )

INSERT INTO #HELPER_TABLE
            (HELPER_TABLE_SID,
             DESCRIPTION)
SELECT HELPER_TABLE_SID,
       DESCRIPTION
FROM   HELPER_TABLE HT
WHERE  ( EXISTS (SELECT 1
                 FROM   Udf_splitstring(@FILTER_UDCS, ',') A
                 WHERE  A.TOKEN = CONVERT(VARCHAR(100), HT.HELPER_TABLE_SID))
          OR @FILTER_UDCS IS NULL )
       AND @DISCOUNT_LEVEL <> 'PROGRAM'
       AND @DISCOUNT_LEVEL <> 'SCHEDULE ID'

IF ( @DISCOUNT_LEVEL LIKE '%UDC%' )
  BEGIN
      UPDATE A
      SET    A.SELECTED_LEVEL = HT.DESCRIPTION,
             SELECTED_SID = CASE @DISCOUNT_LEVEL
                              WHEN 'UDC 1' THEN UDC1
                              WHEN 'UDC 2' THEN UDC2
                              WHEN 'UDC 3' THEN UDC3
                              WHEN 'UDC 4' THEN UDC4
                              WHEN 'UDC 5' THEN UDC5
                              WHEN 'UDC 6' THEN UDC6
                            END
      FROM   #MULTISELECT_DISCOUNTS A
             INNER JOIN UDCS B
                     ON B.MASTER_TYPE = 'RS_CONTRACT'
                        AND A.RS_CONTRACT_SID = B.MASTER_SID
             INNER JOIN #HELPER_TABLE HT
                     ON HT.HELPER_TABLE_SID = CASE @DISCOUNT_LEVEL
                                                WHEN 'UDC 1' THEN UDC1
                                                WHEN 'UDC 2' THEN UDC2
                                                WHEN 'UDC 3' THEN UDC3
                                                WHEN 'UDC 4' THEN UDC4
                                                WHEN 'UDC 5' THEN UDC5
                                                WHEN 'UDC 6' THEN UDC6
                                              END
  END
ELSE IF ( @DISCOUNT_LEVEL = 'PROGRAM'
      OR @DISCOUNT_LEVEL = 'SCHEDULE ID' )
  BEGIN
      UPDATE A
      SET    A.SELECTED_LEVEL = Iif(@DISCOUNT_LEVEL = 'PROGRAM', RS_NAME, RS_ID),
             SELECTED_SID = B.RS_CONTRACT_SID
      FROM   #MULTISELECT_DISCOUNTS A
             INNER JOIN RS_CONTRACT B
                     ON A.RS_CONTRACT_SID = B.RS_CONTRACT_SID
      WHERE  ( EXISTS (SELECT 1
                       FROM   Udf_splitstring(@FILTER_UDCS, ',') A
                       WHERE  A.TOKEN = CONVERT(VARCHAR(100), B.RS_CONTRACT_SID))
                OR @FILTER_UDCS IS NULL )
  END
ELSE
  BEGIN
      UPDATE A
      SET    A.SELECTED_LEVEL = HT.DESCRIPTION,
             SELECTED_SID = CASE @DISCOUNT_LEVEL
                              WHEN 'SCHEDULE CATEGORY' THEN RS_CATEGORY
                              WHEN 'SCHEDULE TYPE' THEN RS_TYPE
                              WHEN 'PROGRAM CATEGORY' THEN REBATE_PROGRAM_TYPE
                              WHEN 'PROGRAM TYPE' THEN REBATE_PROGRAM_TYPE
                            END
      FROM   #MULTISELECT_DISCOUNTS A
             INNER JOIN RS_CONTRACT B
                     ON A.RS_CONTRACT_SID = B.RS_CONTRACT_SID
             INNER JOIN #HELPER_TABLE HT
                     ON HT.HELPER_TABLE_SID = CASE @DISCOUNT_LEVEL
                                                WHEN 'SCHEDULE CATEGORY' THEN RS_CATEGORY
                                                WHEN 'SCHEDULE TYPE' THEN RS_TYPE
                                                WHEN 'PROGRAM CATEGORY' THEN REBATE_PROGRAM_TYPE
                                                WHEN 'PROGRAM TYPE' THEN REBATE_PROGRAM_TYPE
                                              END
  END

DELETE FROM #MULTISELECT_DISCOUNTS
WHERE  SELECTED_LEVEL IS NULL
       AND @SCREEN_NAME NOT IN ( 'SALES', 'DISCOUNT' )

IF Object_id('TEMPDB..#PROJECTION_MASTER') IS NOT NULL
  TRUNCATE TABLE #PROJECTION_MASTER
ELSE
  CREATE TABLE #PROJECTION_MASTER
    (
       ID                    INT IDENTITY(1, 1),
       PROJECTION_MASTER_SID INT
    )

IF Object_id('TEMPDB..#PIVOT_RESULT') IS NOT NULL
  TRUNCATE TABLE #PIVOT_RESULT
ELSE
  CREATE TABLE #PIVOT_RESULT
    (
       PROJECTION_ID                                      INT,
       HIERARCHY_NO                                       VARCHAR(8000),
       PARENT_HIERARCHY_NO                                VARCHAR(8000),
       LEVEL_NAME                                         VARCHAR(100),
       level_no                                           INT,
       [PERIOD]                                           SMALLINT,
       [YEAR]                                             INT,
       CP_INDICATOR                                       CHAR(1),
       EX_FACTORY_SALES_ACCRUALS                          NUMERIC(38, 6),
       EX_FACTORY_SALES_ACTUALS                           NUMERIC(38, 6),
       EX_FACTORY_SALES_PROJECTED                         NUMERIC(38, 6),
       DEMAND_SALES_ACCRUALS                              NUMERIC(38, 6),
       DEMAND_SALES_ACTUALS                               NUMERIC(38, 6),
       DEMAND_SALES_PROJECTED                             NUMERIC(38, 6),
       INVENTORY_WITHDRAWAL_SALES_ACCRUALS                NUMERIC(38, 6),
       INVENTORY_WITHDRAWAL_SALES_ACTUALS                 NUMERIC(38, 6),
       INVENTORY_WITHDRAWAL_SALES_PROJECTED               NUMERIC(38, 6),
       EX_FACTORY_SALES_ACCRUAL_PERCENT                   NUMERIC(38, 6),
       EX_FACTORY_SALES_ACTUALS_PERCENT                   NUMERIC(38, 15),
       EX_FACTORY_SALES_PROJECTED_PERCENT                 NUMERIC(38, 15),
       DEMAND_SALES_ACCRUAL_PERCENT                       NUMERIC(38, 6),
       DEMAND_SALES_ACTUALS_PERCENT                       NUMERIC(38, 15),
       DEMAND_SALES_PROJECTED_PERCENT                     NUMERIC(38, 15),
       INVENTORY_WITHDRAWAL_SALES_ACCRUAL_PERCENT         NUMERIC(38, 6),
       INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT         NUMERIC(38, 15),
       INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT       NUMERIC(38, 15),
       CONTRACT_SALES_ACCRUAL                             NUMERIC(38, 6),
       CONTRACT_SALES_ACTUALS                             NUMERIC(38, 15),
       CONTRACT_SALES_PROJECTED                           NUMERIC(38, 15),
       CONTRACT_UNITS_ACCRUAL                             NUMERIC(38, 6),
       CONTRACT_UNITS_ACTUALS                             NUMERIC(38, 15),
       CONTRACT_UNITS_PROJECTED                           NUMERIC(38, 15),
       TOTAL_DISCOUNT_ACCRUAL                             NUMERIC(38, 6),
       TOTAL_DISCOUNT_ACTUALS                             NUMERIC(38, 15),
       TOTAL_DISCOUNT_PROJECTED                           NUMERIC(38, 15),
       TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE                  NUMERIC(38, 6),
       TOTAL_DISCOUNT_ACTUALS_PERCENTAGE                  NUMERIC(38, 15),
       TOTAL_DISCOUNT_PROJECTED_PERCENTAGE                NUMERIC(38, 15),
       TOTAL_RPU_ACCRUAL                                  NUMERIC(38, 6),
       TOTAL_RPU_ACTUALS                                  NUMERIC(38, 6),
       TOTAL_RPU_PROJECTED                                NUMERIC(38, 6),
       NET_SALES_ACCRUAL                                  NUMERIC(38, 6),
       NET_SALES_ACTUALS                                  NUMERIC(38, 15),
       NET_SALES_PROJECTED                                NUMERIC(38, 15),
       COGS_ACCRUAL                                       NUMERIC(38, 6),
       COGS_ACTUALS                                       NUMERIC(38, 6),
       COGS_PROJECTED                                     NUMERIC(38, 6),
       NET_PROFIT_ACCRUAL                                 NUMERIC(38, 6),
       NET_PROFIT_ACTUALS                                 NUMERIC(38, 6),
       NET_PROFIT_PROJECTED                               NUMERIC(38, 6),
       NET_SALES_OF_EX_FACTORY_ACCRUAL                    NUMERIC(38, 6),
       NET_SALES_OF_EX_FACTORY_ACTUALS                    NUMERIC(38, 15),
       NET_SALES_OF_EX_FACTORY_PROJECTED                  NUMERIC(38, 15),
       DISCOUNT_OF_EX_FACTORY_ACCRUAL                     NUMERIC(38, 6),
       DISCOUNT_OF_EX_FACTORY_ACTUALS                     NUMERIC(38, 6),
       DISCOUNT_OF_EX_FACTORY_PROJECTED                   NUMERIC(38, 6),
       NET_EX_FACTORY_SALES_ACCRUAL                       NUMERIC(38, 6),
       NET_EX_FACTORY_SALES_ACTUALS                       NUMERIC(38, 6)-----------CEL-386
       ,
       NET_EX_FACTORY_SALES_PROJECTED                     NUMERIC(38, 6)-----------CEL-386
       ,
       NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS   NUMERIC(38, 6)-----------CEL-386                              
       ,
       NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED NUMERIC(38, 6)-----------CEL-386
       ,
       NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACCRUAL   NUMERIC(38, 6)
    )

DECLARE @STAT_SALES_SID INT
DECLARE @END_SALES_SID INT
DECLARE @PROJ_START_SID INT
DECLARE @PROJ_END_SID INT
DECLARE @SQL_DT11 NVARCHAR(MAX)

/*

SET @SQL_DT11 = 'SELECT @STAT_SALES_SID=MIN(PERIOD_SID)

                                FROM  ' + @S_ACTUAL_TABLE + ''

 

 

 

EXEC SP_EXECUTESQL @SQL_DT11

       ,N'

              @STAT_SALES_SID INT OUT'

       ,@STAT_SALES_SID = @STAT_SALES_SID OUTPUT

 

SET @SQL_DT11 = 'SELECT @END_SALES_SID=MAX(PERIOD_SID)

                                FROM  ' + @S_ACTUAL_TABLE + ''

 

 

EXEC SP_EXECUTESQL @SQL_DT11

       ,N'

              @END_SALES_SID INT OUT'

       ,@END_SALES_SID = @END_SALES_SID OUTPUT

 

SET @SQL_DT11 = 'SELECT @PROJ_START_SID=MIN(PERIOD_SID)

                                FROM  ' + @S_PROJECTION_TABLE + ''

 

 

EXEC SP_EXECUTESQL @SQL_DT11

       ,N'

              @PROJ_START_SID INT OUT'

       ,@PROJ_START_SID = @PROJ_START_SID OUTPUT

 

SET @SQL_DT11 = 'SELECT @PROJ_END_SID=MAX(PERIOD_SID)

                                FROM  ' + @S_PROJECTION_TABLE + ''

 

EXEC SP_EXECUTESQL @SQL_DT11

       ,N'

              @PROJ_END_SID INT OUT'

       ,@PROJ_END_SID = @PROJ_END_SID OUTPUT                

 

 

 

       IF OBJECT_ID('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL

              DROP TABLE #PERIOD;

 

       SELECT PERIOD_SID,

              PERIOD_DATE,

              MONTH,

              QUARTER,

              SEMI_ANNUAL,

              YEAR,

              PERIOD = CASE WHEN LEFT(@FREQUENCY, 1) = 'M' THEN MONTH WHEN LEFT(@FREQUENCY, 1) = 'Q' THEN QUARTER WHEN LEFT(@FREQUENCY, 1) = 'S' THEN SEMI_ANNUAL ELSE 01 END

       INTO #PERIOD

       FROM PERIOD

*/
SET @TEMP_PROJ_SID = Replace(@PROJECTION_SID + ',', ',,', ',')

WHILE Patindex('%,%', @TEMP_PROJ_SID) <> 0
  BEGIN
      SELECT @SP = Patindex('%,%', @TEMP_PROJ_SID)

      SELECT @SP_PROJ_SID = LEFT(@TEMP_PROJ_SID, @SP - 1)

      SELECT @TEMP_PROJ_SID = Stuff(@TEMP_PROJ_SID, 1, @SP, '')

      INSERT INTO #PROJECTION_MASTER
                  (PROJECTION_MASTER_SID)
      SELECT @SP_PROJ_SID
  END

DECLARE @FIRST_PROJ_SID                INT,
        @PROJ_START_PERIOD_SID         INT,
        @START_DATE                    DATETIME,
        @PROD_RELATIONSHIP_BUILDER_SID INT
        --,@DED_RELATIONSHIP_BUILDER_SID INT
        ,
        @MIN_LEVEL                     INT,
        @CURRENT_DATE                  DATE,
        @VERSION_NO                    INT,
        @PROJECTION_CUST_VERSION       INT,
        @PROJECTION_PROD_VERSION       INT

SELECT @FIRST_PROJ_SID = PM.PROJECTION_MASTER_SID
FROM   #PROJECTION_MASTER PM
WHERE  ID = 1

SELECT @STAT_SALES_SID = Max(CASE
                               WHEN PERIOD_DATE = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0) THEN PERIOD_SID
                             END),
       @END_SALES_SID = Max(CASE
                              WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0) THEN PERIOD_SID
                            END),
       @PROJ_START_SID = Max(CASE
                               WHEN PERIOD_DATE = Datefromparts(Year(FROM_DATE), 1, 1) THEN PERIOD_SID
                             END),
       @PROJ_END_SID = Max(CASE
                             WHEN PERIOD_DATE = Datefromparts(Year(TO_DATE), 12, 1) THEN PERIOD_SID
                           END)
FROM   PROJECTION_MASTER
       INNER JOIN PERIOD P
               ON PERIOD_DATE IN ( Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0), Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0), Datefromparts(Year(FROM_DATE), 1, 1), Datefromparts(Year(TO_DATE), 12, 1) )
WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID

IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
  DROP TABLE #PERIOD;

SELECT PERIOD_SID,
       PERIOD_DATE,
       MONTH,
       QUARTER,
       SEMI_ANNUAL,
       YEAR,
       PERIOD = CASE
                  WHEN LEFT(@FREQUENCY, 1) = 'M' THEN MONTH
                  WHEN LEFT(@FREQUENCY, 1) = 'Q' THEN QUARTER
                  WHEN LEFT(@FREQUENCY, 1) = 'S' THEN SEMI_ANNUAL
                  ELSE 01
                END
INTO   #PERIOD
FROM   PERIOD
WHERE  PERIOD_SID BETWEEN @STAT_SALES_SID AND @PROJ_END_SID

SELECT TOP 1 @STARTFROM = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0),
             @START_DATE = Dateadd(DD, 1, Eomonth(FROM_DATE, -1)),
             @PROJECTION_DATE = Dateadd(DD, 1, Eomonth(TO_DATE, -1)),
             @CUST_RELATIONSHIP_BUILDER_SID = CUST_RELATIONSHIP_BUILDER_SID,
             @PROD_RELATIONSHIP_BUILDER_SID = PROD_RELATIONSHIP_BUILDER_SID,
             @DED_RELATIONSHIP_BUILDER_SID = DED_RELATIONSHIP_BULDER_SID,
             @MIN_LEVEL = CASE @CP_INDICATOR
                            WHEN 'C' THEN CUSTOMER_HIERARCHY_LEVEL
                            WHEN 'P' THEN PRODUCT_HIERARCHY_LEVEL
                          END,
             @BUSINESS_UNIT = BUSINESS_UNIT,
             -----GAL-808
             @COMPANY = COMPANY_MASTER_SID -----GAL-808
             ,
             @PROJECTION_CUST_VERSION = PROJECTION_CUST_VERSION,
             @PROJECTION_PROD_VERSION = PROJECTION_PROD_VERSION
FROM   PROJECTION_MASTER
WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID

SELECT @START_PERIOD_SID = Max(CASE
                                 WHEN PERIOD_DATE = @STARTFROM THEN PERIOD_SID
                               END),
       @END_PERIOD_SID = Max(CASE
                               WHEN PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0) THEN PERIOD_SID
                             END),
       @PROJ_START_PERIOD_SID = Max(CASE
                                      WHEN PERIOD_DATE = @START_DATE THEN PERIOD_SID
                                    END)
FROM   #PERIOD
WHERE  PERIOD_DATE IN ( @STARTFROM, @PROJECTION_DATE, @START_DATE )

SET @ACTUALPERIODS = Datediff(YY, @FROM_DATE, @STARTFROM)
SET @PROJECTIONPERIODS = Datediff(YY, @STARTFROM, @PROJECTION_DATE)

IF Object_id('TEMPDB..#CCP') IS NOT NULL
  DROP TABLE #CCP

CREATE TABLE #CCP
  (
     CCP_DETAILS_SID     INT,
     HIERARCHY_NO        VARCHAR(8000),
     LEVEL_NAME          VARCHAR(100),
     PARENT_HIERARCHY_NO VARCHAR(8000),
     LEVEL_NO            INT
  )

IF @CP_INDICATOR IN ( 'C', 'P' )
  BEGIN
      DECLARE @RELATIONSHIP_BUILDER_SID INT

      SET @RELATIONSHIP_BUILDER_SID = CASE @CP_INDICATOR
                                        WHEN 'C' THEN @CUST_RELATIONSHIP_BUILDER_SID
                                        ELSE @PROD_RELATIONSHIP_BUILDER_SID
                                      END
      SET @VERSION_NO = CASE @CP_INDICATOR
                          WHEN 'C' THEN @PROJECTION_CUST_VERSION
                          ELSE @PROJECTION_PROD_VERSION
                        END
      SET @SQL1 = Concat ('SELECT DISTINCT HIERARCHY_NO,

       CH.CCP_DETAILS_SID,

       LEVEL_NAME,RLD.LEVEL_NO,HIERARCHY_NO AS PARENT_HIERARCHY_NO

FROM   RELATIONSHIP_LEVEL_DEFINITION RLD

       JOIN ', @CCP_HIERARCHY, ' CH

         ON ( CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''

              AND @CP_INDICATOR = ''C'' )

             OR ( CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''

                  AND @CP_INDICATOR = ''P'' )

  ', CASE @GROUP_FILTER
                         WHEN 'SALES' THEN '

                                         JOIN '
                                           + @S_MASTER_TABLE
                                           + ' S ON S.CCP_DETAILS_SID = CH.CCP_DETAILS_SID'
                         WHEN 'DISCOUNT' THEN '

                                         JOIN '
                                              + @D_MASTER_TABLE
                                              + ' S ON S.CCP_DETAILS_SID = CH.CCP_DETAILS_SID'
                         WHEN 'PPA' THEN '

                                         JOIN '
                                         + @P_MASTER_TABLE
                                         + ' S ON S.CCP_DETAILS_SID = CH.CCP_DETAILS_SID'
                       END,
					    
					   CASE WHEN @SCREEN_NAME IS NULL THEN ' JOIN '+@D_MASTER_TABLE+' D ON S.CCP_DETAILS_SID = D.CCP_DETAILS_SID  AND PV_FILTERS=1 '
					   END 
					   ,'

                        

                WHERE  (S.USER_GROUP = ''', @GROUP_FILTER_VALUE, ''' OR ''ALL'' = ''', @GROUP_FILTER_VALUE, ''')

AND  RLD.RELATIONSHIP_BUILDER_SID =  ', @RELATIONSHIP_BUILDER_SID, '

AND RLD.VERSION_NO= ', @VERSION_NO, '

AND  RLD.LEVEL_NO >= ', @MIN_LEVEL, ' ')

      INSERT INTO #CCP
                  (HIERARCHY_NO,
                   CCP_DETAILS_SID,
                   LEVEL_NAME,
                   LEVEL_NO,
                   PARENT_HIERARCHY_NO)
      EXEC Sp_executesql
        @SQL1,
        N'@CP_INDICATOR CHAR(1)',
        @CP_INDICATOR = @CP_INDICATOR
  END
ELSE
  BEGIN
      --INSERT INTO #CCP
      --            (CCP_DETAILS_SID,
      --             HIERARCHY_NO,
      --             LEVEL_NAME)
      --SELECT DISTINCT CCM.CCP_DETAILS_SID,
      --                CRB.CUSTOM_HIERARCHY_NO,
      --                RLD.LEVEL_NAME
      --FROM   CUSTOM_RELATIONSHIP_BUILDER CRB
      --       INNER JOIN CUSTOM_CCP_MAP CCM
      --               ON CCM.CUSTOM_VIEW_DETAILS_SID = CRB.CUSTOM_VIEW_DETAILS_SID
      --                  AND CCM.CUSTOM_HIERARCHY_NO = CRB.CUSTOM_HIERARCHY_NO
      --       INNER JOIN CUSTOM_VIEW_DETAILS CVD
      --               ON CVD.CUSTOM_VIEW_DETAILS_SID = CRB.CUSTOM_VIEW_DETAILS_SID
      --       INNER JOIN CUSTOM_VIEW_MASTER CVM
      --               ON CVM.CUSTOM_VIEW_MASTER_SID = CVD.CUSTOM_VIEW_MASTER_SID
      --       INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
      --               ON RLD.HIERARCHY_LEVEL_DEFINITION_SID = CVD.HIERARCHY_ID
      --WHERE  CVM.PROJECTION_MASTER_SID = @PROJECTION_SID
      --       AND CVD.CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID
      --       AND ( CVD.LEVEL_NO = @LEVEL_NO
      --              OR @LEVEL_NO IS NULL
      --              OR @LEVEL_NO = 0 )
      DECLARE @CUSTOM_SQL NVARCHAR(MAX)

      --SELECT GETDATE()
      IF Object_id('TEMPDB..#PARENT_HIERARCHY') IS NOT NULL
        DROP TABLE #PARENT_HIERARCHY

      CREATE TABLE #PARENT_HIERARCHY
        (
           CCP_DETAILS_SID           INT NOT NULL,
           HIERARCHY_NO              VARCHAR(1000) NOT NULL,
           LEVEL_NAME                VARCHAR(75),
           LEVEL_NO                  SMALLINT NOT NULL,
           RELATIONSHIP_LEVEL_VALUES VARCHAR(50),
           DEDUCTION_HIRARCHY_NO     VARCHAR(1000),
           RS_CONTRACT_SID           INT NOT NULL,
           INDICATOR                 CHAR(1),
           PARENT_HIERARCHY_NO       CHAR(2000),
           PRIMARY KEY ( LEVEL_NO, HIERARCHY_NO, CCP_DETAILS_SID, RS_CONTRACT_SID )
        )

      SET @CUSTOM_SQL = Concat ('

                     INSERT INTO #PARENT_HIERARCHY

                     (

                     CCP_DETAILS_SID,               

                     HIERARCHY_NO,                         

                     LEVEL_NAME,

                     LEVEL_NO,

                                  DEDUCTION_HIRARCHY_NO

                                  ,INDICATOR

                                  ,RS_CONTRACT_SID

                                         ,RELATIONSHIP_LEVEL_VALUES

                     )

SELECT CH.CCP_DETAILS_SID,

       RLD.HIERARCHY_NO,

       RLD.LEVEL_NAME,

          C.LEVEL_NO,

                RLD.PARENT_HIERARCHY_NO,HIERARCHY_INDICATOR,isnull(RS_CONTRACT_SID,-1) ,RELATIONSHIP_LEVEL_VALUES

FROM   CUSTOM_VIEW_DETAILS C

       JOIN RELATIONSHIP_LEVEL_DEFINITION RLD

         ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID

            AND RELATIONSHIP_BUILDER_SID IN ( @CUST_RELATIONSHIP_BUILDER_SID)

       JOIN ', @CCP_HIERARCHY, ' CH

         ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''

                     LEFT  JOIN ', @D_MASTER_TABLE, ' DM ON DM.CCP_DETAILS_SID=CH.CCP_DETAILS_SID

                       

                WHERE  RLD.VERSION_NO = ', @PROJECTION_CUST_VERSION, ' AND

                           CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID ', CASE
                                                                                                         WHEN @SCREEN_NAME IS NULL THEN ' AND PV_FILTERS=1 '
                                                                                                         WHEN @SCREEN_NAME = 'DISCOUNT' THEN ' AND FILTER_CCP=1 '
                                                                                                       END)

      EXEC Sp_executesql
        @CUSTOM_SQL,
        N'@CUST_RELATIONSHIP_BUILDER_SID INT, @PROD_RELATIONSHIP_BUILDER_SID  INT,@GROUP_FILTER_VALUE VARCHAR(50),@CUSTOM_VIEW_MASTER_SID INT,@SCREEN_NAME VARCHAR(100) ',
        @CUST_RELATIONSHIP_BUILDER_SID = @CUST_RELATIONSHIP_BUILDER_SID,
        @PROD_RELATIONSHIP_BUILDER_SID = @PROD_RELATIONSHIP_BUILDER_SID,
        @GROUP_FILTER_VALUE = @GROUP_FILTER_VALUE,
        @CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID,
        @SCREEN_NAME = @SCREEN_NAME

      SET @CUSTOM_SQL = Concat ('

                  INSERT INTO #PARENT_HIERARCHY

                     (

                     CCP_DETAILS_SID,               

                     HIERARCHY_NO,                         

                     LEVEL_NAME,

                     LEVEL_NO,

                                  DEDUCTION_HIRARCHY_NO

                                  ,INDICATOR

                                  ,RS_CONTRACT_SID

                                         ,RELATIONSHIP_LEVEL_VALUES

                     )         

       SELECT CH.CCP_DETAILS_SID,

       RLD.HIERARCHY_NO,

       RLD.LEVEL_NAME,

          C.LEVEL_NO,

                RLD.PARENT_HIERARCHY_NO,HIERARCHY_INDICATOR,isnull(RS_CONTRACT_SID,-1) ,RELATIONSHIP_LEVEL_VALUES

FROM   CUSTOM_VIEW_DETAILS C

       JOIN RELATIONSHIP_LEVEL_DEFINITION RLD

         ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID

            AND RELATIONSHIP_BUILDER_SID = @PROD_RELATIONSHIP_BUILDER_SID

       JOIN ', @CCP_HIERARCHY, ' CH

         ON CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''

                     LEFT  JOIN ', @D_MASTER_TABLE, ' DM ON DM.CCP_DETAILS_SID=CH.CCP_DETAILS_SID

                       

                 WHERE  RLD.VERSION_NO = ', @PROJECTION_PROD_VERSION, ' AND

                            CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID ', CASE
                                                                                                          WHEN @SCREEN_NAME IS NULL THEN ' AND PV_FILTERS=1 '
                                                                                                          WHEN @SCREEN_NAME = 'DISCOUNT' THEN ' AND FILTER_CCP=1 '
                                                                                                        END)

      EXEC Sp_executesql
        @CUSTOM_SQL,
        N'@CUST_RELATIONSHIP_BUILDER_SID INT, @PROD_RELATIONSHIP_BUILDER_SID  INT,@GROUP_FILTER_VALUE VARCHAR(50),@CUSTOM_VIEW_MASTER_SID INT,@SCREEN_NAME VARCHAR(100) ',
        @CUST_RELATIONSHIP_BUILDER_SID = @CUST_RELATIONSHIP_BUILDER_SID,
        @PROD_RELATIONSHIP_BUILDER_SID = @PROD_RELATIONSHIP_BUILDER_SID,
        @GROUP_FILTER_VALUE = @GROUP_FILTER_VALUE,
        @CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID,
        @SCREEN_NAME = @SCREEN_NAME

      --SELECT GETDATE()
      DECLARE @VAR1 INT

      IF EXISTS (SELECT 1
                 FROM   #PARENT_HIERARCHY
                 WHERE  INDICATOR = 'C')
        BEGIN
            SET @VAR1 = 1
        END

      IF @SCREEN_NAME IN ( 'SALES' )
        BEGIN
            --SELECT GETDATE()
            IF Object_id('TEMPDB..#CROSS_APPLY_TAB') IS NOT NULL
              DROP TABLE #CROSS_APPLY_TAB

            SELECT T.CCP_DETAILS_SID,
                   T.HIERARCHY_NO,
                   T.LEVEL_NO,
                   LEFT(M.PARENT_HIERARCHY_NO, Len(M.PARENT_HIERARCHY_NO) - 1) AS PARENT_HIERARCHY_NO1
            INTO   #CROSS_APPLY_TAB
            FROM   (SELECT CCP_DETAILS_SID,
                           HIERARCHY_NO,
                           LEVEL_NO
                    FROM   #PARENT_HIERARCHY
                    GROUP  BY LEVEL_NO,
                              CCP_DETAILS_SID,
                              HIERARCHY_NO) T
                   CROSS APPLY (SELECT CONVERT(VARCHAR(8000), R.HIERARCHY_NO + '~')
                                FROM   (SELECT LEVEL_NO,
                                               HIERARCHY_NO,
                                               CCP_DETAILS_SID
                                        FROM   #PARENT_HIERARCHY
                                        GROUP  BY LEVEL_NO,
                                                  HIERARCHY_NO,
                                                  CCP_DETAILS_SID) R
                                WHERE  T.CCP_DETAILS_SID = R.CCP_DETAILS_SID
                                       AND T.LEVEL_NO > R.LEVEL_NO
                                ORDER  BY T.LEVEL_NO
                                FOR XML PATH('')) M(PARENT_HIERARCHY_NO)

            --SELECT GETDATE() AS CROSS_APPL
            UPDATE B
            SET    B.PARENT_HIERARCHY_NO = AB.PARENT_HIERARCHY_NO1
            FROM   #CROSS_APPLY_TAB AB
                   INNER JOIN #PARENT_HIERARCHY B
                           ON AB.HIERARCHY_NO = B.HIERARCHY_NO
                              AND AB.LEVEL_NO = B.LEVEL_NO
        --SELECT GETDATE()
        ---    SELECT * FROM #PARENT_HIERARCHY ORDER BY LEVEL_NO
        /*
        
        UPDATE B
        
        SET B.PARENT_HIERARCHY_NO = AB.PARENT_HIERARCHY_NO1
        
        FROM (
        
               SELECT T.CCP_DETAILS_SID
        
                      ,T.HIERARCHY_NO
        
                      ,T.LEVEL_NAME
        
                      ,T.LEVEL_NO
        
                      ,LEFT(M.PARENT_HIERARCHY_NO, LEN(M.PARENT_HIERARCHY_NO) - 1) AS PARENT_HIERARCHY_NO1
        
               FROM (SELECT CCP_DETAILS_SID,HIERARCHY_NO,LEVEL_NAME,LEVEL_NO,RELATIONSHIP_LEVEL_VALUES FROM #PARENT_HIERARCHY
        
                      GROUP BY LEVEL_NO,CCP_DETAILS_SID,HIERARCHY_NO,LEVEL_NAME,RELATIONSHIP_LEVEL_VALUES) T
        
               CROSS APPLY (
        
                      SELECT  CONVERT(VARCHAR(8000), R.HIERARCHY_NO + '~')
        
                      FROM (SELECT CCP_DETAILS_SID,HIERARCHY_NO,LEVEL_NAME,LEVEL_NO,RELATIONSHIP_LEVEL_VALUES FROM #PARENT_HIERARCHY
        
                      GROUP BY LEVEL_NO,CCP_DETAILS_SID,HIERARCHY_NO,LEVEL_NAME,RELATIONSHIP_LEVEL_VALUES ) R
        
                      WHERE T.CCP_DETAILS_SID = R.CCP_DETAILS_SID
        
                             AND T.LEVEL_NO > R.LEVEL_NO
        
                             ORDER BY T.LEVEL_NO
        
                      FOR XML PATH('')
        
                      )M(PARENT_HIERARCHY_NO)
        
         
        
               ) AB
        
        JOIN #PARENT_HIERARCHY B ON AB.CCP_DETAILS_SID = B.CCP_DETAILS_SID
        
               AND AB.HIERARCHY_NO = B.HIERARCHY_NO
        
               AND AB.LEVEL_NO = B.LEVEL_NO */
        ---    SELECT * FROM #PARENT_HIERARCHY ORDER BY LEVEL_NO
        END
      ELSE
        BEGIN
            IF Object_id('TEMPDB..#CROSS_APPLY_TAB1') IS NOT NULL
              DROP TABLE #CROSS_APPLY_TAB1

            SELECT T.CCP_DETAILS_SID,
                   T.HIERARCHY_NO,
                   T.LEVEL_NAME,
                   T.LEVEL_NO,
                   LEFT(M.PARENT_HIERARCHY_NO, Len(M.PARENT_HIERARCHY_NO) - 1) AS PARENT_HIERARCHY_NO1,
                   T.RS_CONTRACT_SID
            INTO   #CROSS_APPLY_TAB1
            FROM   (SELECT CCP_DETAILS_SID,
                           HIERARCHY_NO,
                           LEVEL_NAME,
                           LEVEL_NO,
                           RELATIONSHIP_LEVEL_VALUES,
                           RS_CONTRACT_SID,
                           INDICATOR
                    FROM   #PARENT_HIERARCHY
                    GROUP  BY LEVEL_NO,
                              CCP_DETAILS_SID,
                              HIERARCHY_NO,
                              LEVEL_NAME,
                              RELATIONSHIP_LEVEL_VALUES,
                              RS_CONTRACT_SID,
                              INDICATOR) T
                   CROSS APPLY (SELECT CONVERT(VARCHAR(8000), R.HIERARCHY_NO + '~')
                                FROM   (SELECT CCP_DETAILS_SID,
                                               HIERARCHY_NO,
                                               LEVEL_NAME,
                                               LEVEL_NO,
                                               RELATIONSHIP_LEVEL_VALUES,
                                               RS_CONTRACT_SID,
                                               INDICATOR
                                        FROM   #PARENT_HIERARCHY
                                        GROUP  BY LEVEL_NO,
                                                  CCP_DETAILS_SID,
                                                  HIERARCHY_NO,
                                                  LEVEL_NAME,
                                                  RELATIONSHIP_LEVEL_VALUES,
                                                  RS_CONTRACT_SID,
                                                  INDICATOR) R
                                WHERE  T.CCP_DETAILS_SID = R.CCP_DETAILS_SID
                                       ---AND IIF(INDICATOR='D',T.RS_CONTRACT_SID,1)=IIF(INDICATOR='D',R.RS_CONTRACT_SID,1)
                                       AND Iif(@VAR1 = 1, T.RS_CONTRACT_SID, 1) = Iif(@VAR1 = 1, R.RS_CONTRACT_SID, 1)
                                       AND T.LEVEL_NO > R.LEVEL_NO
                                ORDER  BY T.LEVEL_NO
                                FOR XML PATH('')) M(PARENT_HIERARCHY_NO)

            UPDATE B
            SET    B.PARENT_HIERARCHY_NO = AB.PARENT_HIERARCHY_NO1
            FROM   #CROSS_APPLY_TAB1 AB
                   INNER JOIN #PARENT_HIERARCHY B
                           ON AB.HIERARCHY_NO = B.HIERARCHY_NO
                              --AND IIF(INDICATOR='D',AB.RS_CONTRACT_SID,1)=IIF(INDICATOR='D',B.RS_CONTRACT_SID,1)
                              AND Iif(@VAR1 = 1, AB.RS_CONTRACT_SID, 1) = Iif(@VAR1 = 1, b.RS_CONTRACT_SID, 1)
                              AND AB.LEVEL_NO = B.LEVEL_NO
        /*UPDATE B
        
        SET B.PARENT_HIERARCHY_NO = AB.PARENT_HIERARCHY_NO1
        
        FROM (
        
               SELECT   T.CCP_DETAILS_SID
        
                      ,T.HIERARCHY_NO
        
                      ,T.LEVEL_NAME
        
                      ,T.LEVEL_NO
        
                      ,LEFT(M.PARENT_HIERARCHY_NO, LEN(M.PARENT_HIERARCHY_NO) - 1) AS PARENT_HIERARCHY_NO1
        
                      ,T.RS_CONTRACT_SID
        
               FROM (SELECT CCP_DETAILS_SID,HIERARCHY_NO,LEVEL_NAME,LEVEL_NO,RELATIONSHIP_LEVEL_VALUES,RS_CONTRACT_SID,INDICATOR FROM #PARENT_HIERARCHY
        
                      GROUP BY LEVEL_NO,CCP_DETAILS_SID,HIERARCHY_NO,LEVEL_NAME,RELATIONSHIP_LEVEL_VALUES,RS_CONTRACT_SID,INDICATOR) T
        
               CROSS APPLY (
        
                      SELECT  CONVERT(VARCHAR(8000), R.HIERARCHY_NO + '~')
        
                      FROM (SELECT CCP_DETAILS_SID,HIERARCHY_NO,LEVEL_NAME,LEVEL_NO,RELATIONSHIP_LEVEL_VALUES,RS_CONTRACT_SID,INDICATOR FROM #PARENT_HIERARCHY
        
                      GROUP BY LEVEL_NO,CCP_DETAILS_SID,HIERARCHY_NO,LEVEL_NAME,RELATIONSHIP_LEVEL_VALUES,RS_CONTRACT_SID,INDICATOR ) R
        
                      WHERE T.CCP_DETAILS_SID = R.CCP_DETAILS_SID
        
                      AND IIF(INDICATOR='D',T.RS_CONTRACT_SID,1)=IIF(INDICATOR='D',R.RS_CONTRACT_SID,1)
        
                             AND T.LEVEL_NO > R.LEVEL_NO
        
                             ORDER BY T.LEVEL_NO
        
                      FOR XML PATH('')
        
                      )M(PARENT_HIERARCHY_NO)
        
         
        
               ) AB
        
        JOIN #PARENT_HIERARCHY B ON AB.CCP_DETAILS_SID = B.CCP_DETAILS_SID
        
               AND AB.HIERARCHY_NO = B.HIERARCHY_NO
        
               AND IIF(INDICATOR='D',AB.RS_CONTRACT_SID,1)=IIF(INDICATOR='D',B.RS_CONTRACT_SID,1)
        
               AND AB.LEVEL_NO = B.LEVEL_NO */
        END

      IF EXISTS (SELECT 1
                 FROM   (SELECT HIERARCHY_NO,
                                LEVEL_NO,
                                RELATIONSHIP_LEVEL_VALUES,
                                PARENT_HIERARCHY_NO,
                                Row_number()
                                  OVER (
                                    PARTITION BY LEVEL_NO, RELATIONSHIP_LEVEL_VALUES, PARENT_HIERARCHY_NO
                                    ORDER BY LEVEL_NO ) RNO
                         FROM   #PARENT_HIERARCHY
                         GROUP  BY LEVEL_NO,
                                   HIERARCHY_NO,
                                   RELATIONSHIP_LEVEL_VALUES,
                                   PARENT_HIERARCHY_NO) B
                 WHERE  RNO > 1)
        BEGIN
            UPDATE A
            SET    A.HIERARCHY_NO = Isnull(G.HIER, A.HIERARCHY_NO)
            FROM   #PARENT_HIERARCHY A
                   INNER JOIN (SELECT DISTINCT PR.HIERARCHY_NO -- PR.*
                                               ,
                                               Stuff((SELECT DISTINCT ', ' + R.HIERARCHY_NO HIER
                                                      FROM   (SELECT *
                                                              FROM   (SELECT HIERARCHY_NO,
                                                                             LEVEL_NO,
                                                                             RELATIONSHIP_LEVEL_VALUES,
                                                                             PARENT_HIERARCHY_NO,
                                                                             Row_number()
                                                                               OVER (
                                                                                 PARTITION BY LEVEL_NO, RELATIONSHIP_LEVEL_VALUES, PARENT_HIERARCHY_NO
                                                                                 ORDER BY LEVEL_NO ) RNO
                                                                      FROM   #PARENT_HIERARCHY
                                                                      GROUP  BY LEVEL_NO,
                                                                                HIERARCHY_NO,
                                                                                RELATIONSHIP_LEVEL_VALUES,
                                                                                PARENT_HIERARCHY_NO) B
                                                              WHERE  RNO > 1) R
                                                      WHERE  CONVERT(INT, R.RELATIONSHIP_LEVEL_VALUES) = CONVERT(INT, PR.RELATIONSHIP_LEVEL_VALUES)
                                                             AND ( CASE
                                                                     WHEN R.LEVEL_NO > 1 THEN R.PARENT_HIERARCHY_NO
                                                                     ELSE '1'
                                                                   END ) = ( CASE
                                                                               WHEN PR.LEVEL_NO > 1 THEN PR.PARENT_HIERARCHY_NO
                                                                               ELSE '1'
                                                                             END )
                                                             AND R.LEVEL_NO = PR.LEVEL_NO
                                                      FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, '') AS HIER
                               FROM   (SELECT *
                                       FROM   (SELECT HIERARCHY_NO,
                                                      LEVEL_NO,
                                                      RELATIONSHIP_LEVEL_VALUES,
                                                      PARENT_HIERARCHY_NO,
                                                      Row_number()
                                                        OVER (
                                                          PARTITION BY LEVEL_NO, RELATIONSHIP_LEVEL_VALUES, PARENT_HIERARCHY_NO
                                                          ORDER BY LEVEL_NO ) RNO
                                               FROM   #PARENT_HIERARCHY
                                               GROUP  BY LEVEL_NO,
                                                         HIERARCHY_NO,
                                                         RELATIONSHIP_LEVEL_VALUES,
                                                         PARENT_HIERARCHY_NO) B
                                       WHERE  RNO > 1) PR) G
                           ON G.HIERARCHY_NO = A.HIERARCHY_NO
        --SELECT GETDATE() AS UPDATER
        END

      IF @SCREEN_NAME IN ( 'SALES' )
        BEGIN
            --SELECT GETDATE() AS HASHCCP
            INSERT INTO #CCP
                        (CCP_DETAILS_SID,
                         HIERARCHY_NO,
                         PARENT_HIERARCHY_NO,
                         LEVEL_NO)
            SELECT DISTINCT CCP_DETAILS_SID,
                            HIERARCHY_NO,
                            Replace(Iif(A.LEVEL_NO = 1, NULL, Concat (Replace(LEFT(CS.PARENT_HIERARCHY_NO, Len(CASE
                                                                                                                 WHEN CS.PARENT_HIERARCHY_NO = '' THEN NULL
                                                                                                                 ELSE CS.PARENT_HIERARCHY_NO
                                                                                                               END) - 1), '~', '.~'), '.')), '..', '.'),
                            A.LEVEL_NO
            FROM   (SELECT CCP_DETAILS_SID,
                           HIERARCHY_NO,
                           LEVEL_NO
                    FROM   #PARENT_HIERARCHY
                    GROUP  BY LEVEL_NO,
                              CCP_DETAILS_SID,
                              HIERARCHY_NO) A
                   CROSS APPLY (SELECT HIERARCHY_NO + '~'
                                FROM   (SELECT CCP_DETAILS_SID,
                                               HIERARCHY_NO,
                                               LEVEL_NO
                                        FROM   #PARENT_HIERARCHY
                                        GROUP  BY LEVEL_NO,
                                                  CCP_DETAILS_SID,
                                                  HIERARCHY_NO) B
                                WHERE  A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                       AND A.LEVEL_NO > B.LEVEL_NO
                                ---AND  (A.RS_CONTRACT_SID =B.RS_CONTRACT_SID OR A.RS_CONTRACT_SID IS NULL)
                                ORDER  BY LEVEL_NO
                                FOR XML PATH('')) CS(PARENT_HIERARCHY_NO)
            ORDER  BY LEVEL_NO
        --SELECT GETDATE() AS HASHCCP
        END

      IF @SCREEN_NAME NOT IN ( 'SALES' )
          OR @SCREEN_NAME IS NULL
        BEGIN
            -- UPDATE A SET  A.HIERARCHY_NO=G.HIER FROM  #PARENT_HIERARCHY A
            --JOIN
            --(
            --SELECT PR.*,
            --STUFF((SELECT   DISTINCT ', '+  R.HIERARCHY_NO HIER FROM  #PARENT_HIERARCHY R WHERE  CONVERT(INT,R.RELATIONSHIP_LEVEL_VALUES)= CONVERT(INT,PR.RELATIONSHIP_LEVEL_VALUES)  FOR XML PATH(''), TYPE) .VALUE('.','NVARCHAR(MAX)'),1,2,' ') AS HIER
            -- FROM  #PARENT_HIERARCHY PR)G ON G.HIERARCHY_NO=A.HIERARCHY_NO
            SET @CUSTOM_SQL = Concat ('

                   INSERT INTO #PARENT_HIERARCHY

                   (

                     CCP_DETAILS_SID,               

                     HIERARCHY_NO,                         

                     LEVEL_NAME,

                     LEVEL_NO,

                                  DEDUCTION_HIRARCHY_NO

                                  ,RS_CONTRACT_SID

                                  ,INDICATOR

                     )

SELECT DISTINCT  CH.CCP_DETAILS_SID,

       RLD.RELATIONSHIP_LEVEL_VALUES,

       RLD.LEVEL_NAME,

          C.LEVEL_NO,

                PARENT_HIERARCHY_NO

                ,RS_CONTRACT_SID

                ,HIERARCHY_INDICATOR

FROM   CUSTOM_VIEW_DETAILS C

       JOIN RELATIONSHIP_LEVEL_DEFINITION RLD

         ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID

            AND RELATIONSHIP_BUILDER_SID IN (@DED_RELATIONSHIP_BUILDER_SID )

       JOIN ', @D_MASTER_TABLE, ' DM

 

ON DM.DEDUCTION_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''

JOIN ', @CCP_HIERARCHY, ' CH

ON DM.CCP_DETAILS_SID=CH.CCP_DETAILS_SID

 

                WHERE   CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID AND IIF(@SCREEN_NAME  IS NULL,PV_FILTERS,IIF(@SCREEN_NAME=''DISCOUNT'',FILTER_CCP,1))=1 ')

            EXEC Sp_executesql
              @CUSTOM_SQL,
              N'@DED_RELATIONSHIP_BUILDER_SID INT,@CUSTOM_VIEW_MASTER_SID INT,@SCREEN_NAME  VARCHAR(100) ',
              @DED_RELATIONSHIP_BUILDER_SID = @DED_RELATIONSHIP_BUILDER_SID,
              @CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID,
              @SCREEN_NAME = @SCREEN_NAME

            INSERT INTO #CCP
                        (CCP_DETAILS_SID,
                         HIERARCHY_NO,
                         PARENT_HIERARCHY_NO,
                         LEVEL_NO,LEVEL_NAME)
            SELECT DISTINCT CCP_DETAILS_SID,
                            HIERARCHY_NO,
                            Replace(Iif(A.LEVEL_NO = 1, NULL, Concat (Replace(LEFT(CS.PARENT_HIERARCHY_NO, Len(CASE
                                                                                                                 WHEN CS.PARENT_HIERARCHY_NO = '' THEN NULL
                                                                                                                 ELSE CS.PARENT_HIERARCHY_NO
                                                                                                               END) - 1), '~', '.~'), '.')), '..', '.'),
                            A.LEVEL_NO,a.LEVEL_NAME
            FROM   (SELECT CCP_DETAILS_SID,
                           HIERARCHY_NO,
                           LEVEL_NO,
                           RS_CONTRACT_SID,
                           INDICATOR,LEVEL_NAME
                    FROM   #PARENT_HIERARCHY
                    GROUP  BY LEVEL_NO,
                              CCP_DETAILS_SID,
                              HIERARCHY_NO,
                              RS_CONTRACT_SID,
                              INDICATOR,LEVEL_NAME) A
                   CROSS APPLY (SELECT HIERARCHY_NO + '~'
                                FROM   (SELECT CCP_DETAILS_SID,
                                               HIERARCHY_NO,
                                               LEVEL_NAME,
                                               LEVEL_NO,
                                               RELATIONSHIP_LEVEL_VALUES,
                                               RS_CONTRACT_SID,
                                               INDICATOR
                                        FROM   #PARENT_HIERARCHY
                                        GROUP  BY LEVEL_NO,
                                                  CCP_DETAILS_SID,
                                                  HIERARCHY_NO,
                                                  LEVEL_NAME,
                                                  RELATIONSHIP_LEVEL_VALUES,
                                                  RS_CONTRACT_SID,
                                                  INDICATOR) B
                                WHERE  A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                       --AND IIF(INDICATOR='D',A.RS_CONTRACT_SID,1)=IIF(INDICATOR='D',B.RS_CONTRACT_SID,1)
                                       AND Iif(@VAR1 = 1, A.RS_CONTRACT_SID, 1) = Iif(@VAR1 = 1, B.RS_CONTRACT_SID, 1)
                                       AND A.LEVEL_NO > B.LEVEL_NO
                                ---AND  (A.RS_CONTRACT_SID =B.RS_CONTRACT_SID OR A.RS_CONTRACT_SID IS NULL)
                                ORDER  BY LEVEL_NO
                                FOR XML PATH('')) CS(PARENT_HIERARCHY_NO)
            ORDER  BY LEVEL_NO
        /*SELECT DISTINCT CCP_DETAILS_SID,
        
                             HIERARCHY_NO,
        
                             LEVEL_NAME,
        
                             REPLACE(IIF(A.LEVEL_NO=1,NULL,CONCAT(REPLACE(LEFT(CS.PARENT_HIERARCHY_NO, LEN(CASE WHEN CS.PARENT_HIERARCHY_NO='' THEN NULL ELSE CS.PARENT_HIERARCHY_NO END) - 1),'~','.~'),'.')),'..','.'),
        
                             A.LEVEL_NO
        
                      FROM #PARENT_HIERARCHY  A
        
                      CROSS APPLY (
        
                             SELECT   DISTINCT HIERARCHY_NO + '~'
        
                             FROM   #PARENT_HIERARCHY  B
        
                             WHERE A.CCP_DETAILS_SID = B.CCP_DETAILS_SID AND A.LEVEL_NO > B.LEVEL_NO
        
                             AND  (A.RS_CONTRACT_SID =B.RS_CONTRACT_SID OR A.RS_CONTRACT_SID IS NULL)
        
                             --ORDER BY LEVEL_NO
        
                             FOR XML PATH('')
        
                             ) CS(PARENT_HIERARCHY_NO)
        
                             ORDER BY LEVEL_NO*/
        END
  END

---SELECT * FROM #CCP
 
DECLARE @LEVEL_DISC   VARCHAR(100),
        @FIELD_VALUES VARCHAR(500)

SELECT @LEVEL_DISC = Max(CASE
                           WHEN FIELD_NAME = 'LEVEL' THEN FIELD_VALUES
                         END),
       @FIELD_VALUES = Max(CASE
                             WHEN FIELD_NAME = 'SELECTED DISCOUNTS' THEN FIELD_VALUES
                           END)
FROM   NM_PROJECTION_SELECTION
WHERE  SCREEN_NAME = 'DISCOUNT PROJECTION'
       AND PROJECTION_MASTER_SID = @FIRST_PROJ_SID
       AND FIELD_NAME IN ( 'LEVEL', 'SELECTED DISCOUNTS' )

DECLARE @D_SQL NVARCHAR(MAX)

--SELECT GETDATE() AS DI
IF Object_id('TEMPDB..#DISCOUNT_INFO') IS NOT NULL
  DROP TABLE #DISCOUNT_INFO

CREATE TABLE #DISCOUNT_INFO
  (
     TOKEN        VARCHAR(100),
     DISCOUNT     VARCHAR(100),
     SELECTED_SID VARCHAR(50)
  )

SET @D_SQL = Concat ('INSERT INTO #DISCOUNT_INFO (

       TOKEN

       ,DISCOUNT

       ,SELECTED_SID

       )

SELECT DISTINCT CASE

              WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM''

                     THEN CAST(RS_CONTRACT_SID AS VARCHAR(50))

              ELSE SELECTED_LEVEL

              END AS TOKEN

       ,SELECTED_LEVEL AS DISCOUNT

       ,SELECTED_SID

FROM #MULTISELECT_DISCOUNTS

WHERE

SELECTED_SID IS NOT NULL

/*UNION ALL

 

SELECT DISTINCT CASE

              WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM''

                     THEN CAST(R.RS_CONTRACT_SID AS VARCHAR(50))

              ELSE '' PRICE PROTECTION ''

              END AS TOKEN

       ,CASE

              WHEN ''', @DISCOUNT_LEVEL, ''' = '' PROGRAM ''

                     THEN RS_NAME

              ELSE '' PRICE PROTECTION ''

              END AS DISCOUNT,'''' AS SELECTED_SID

FROM ', @P_MASTER_TABLE, ' S

INNER JOIN RS_CONTRACT R ON R.RS_CONTRACT_SID = S.RS_CONTRACT_SID*/

')

EXEC (@D_SQL)

SET @D_SQL = Concat ('SELECT CCP.COMPANY_MASTER_SID,

              CCP.CONTRACT_MASTER_SID,

              CCP.ITEM_MASTER_SID,

              CCP.CCP_DETAILS_SID,

              HIERARCHY_NO,

              LEVEL_NAME,

              --@FIRST_PROJ_SID AS PROJECTION_MASTER_SID,

              --COALESCE(PARENT_HIERARCHY_NO, HIERARCHY_NO) PARENT_HIERARCHY_NO

              PARENT_HIERARCHY_NO,LEVEL_NO

       FROM #CCP_DETAILS_TEMP CCP

       INNER JOIN #CCP C

              ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID

       ', CASE
                         WHEN @SCREEN_NAME <> 'SALES' THEN ' WHERE  EXISTS  (SELECT  1 FROM #MULTISELECT_DISCOUNTS M WHERE M.CCP_DETAILS_SID=C.CCP_DETAILS_SID) '
                       END, ' ')

--SELECT GETDATE() AS DI
IF Object_id('TEMPDB..#TEMP_CCP') IS NOT NULL
  DROP TABLE #TEMP_CCP

CREATE TABLE #TEMP_CCP
  (
     COMPANY_MASTER_SID  INT,
     CONTRACT_MASTER_SID INT,
     ITEM_MASTER_SID     INT,
     CCP_DETAILS_SID     INT,
     --PROJECTION_MASTER_SID INT,
     --BUSINESS_UNIT INT,
     --COMPANY INT,
     HIERARCHY_NO        VARCHAR(8000),
     LEVEL_NAME          VARCHAR(100),
     PARENT_HIERARCHY_NO VARCHAR(8000),
     LEVEL_NO            INT
  )

--SELECT GETDATE() AS TCCP
INSERT INTO #TEMP_CCP
            (COMPANY_MASTER_SID,
             CONTRACT_MASTER_SID,
             ITEM_MASTER_SID,
             CCP_DETAILS_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             --PROJECTION_MASTER_SID,
             PARENT_HIERARCHY_NO,
             LEVEL_NO)
EXEC (@D_SQL)

IF Object_id('TEMPDB..#PERF') IS NOT NULL
  DROP TABLE #PERF

CREATE TABLE #PERF
  (
     [HIERARCHY_NO]        VARCHAR(8000) NOT NULL,
     [PARENT_HIERARCHY_NO] VARCHAR(8000),
     [LEVEL_NAME]          VARCHAR(500),
     [CCP_DETAILS_SID]     INT NOT NULL,
     [USER_GROUP]          VARCHAR(500),
     [SALES_INCLUSION]     INT,
     [METHODOLOGY]         VARCHAR(100),
     [CALCULATION_PERIODS] VARCHAR(100),
     LEVEL_NO              SMALLINT NOT NULL
  );

--SELECT GETDATE() AS PERF
CREATE NONCLUSTERED INDEX idx_PERF
  ON #PERF ( LEVEL_NO, [CCP_DETAILS_SID] )
  include ( [HIERARCHY_NO], [PARENT_HIERARCHY_NO] )

SET @D_SQL = Concat ('SELECT  A.HIERARCHY_NO,

              A.PARENT_HIERARCHY_NO,

              A.LEVEL_NAME,

              A.CCP_DETAILS_SID,SPM.USER_GROUP

,SPM.SALES_INCLUSION,A.LEVEL_NO

, CAST(CASE

                           WHEN COUNT(SPM.METHODOLOGY) OVER (PARTITION BY  A.HIERARCHY_NO,A.PARENT_HIERARCHY_NO,A.LEVEL_NAME,SPM.USER_GROUP,SPM.SALES_INCLUSION,SPM.METHODOLOGY)> 1 THEN ''-''

                           ELSE MAX(SPM.METHODOLOGY) OVER (PARTITION BY  A.HIERARCHY_NO,A.PARENT_HIERARCHY_NO,A.LEVEL_NAME,SPM.USER_GROUP,SPM.SALES_INCLUSION)

                       END AS VARCHAR(100))METHODOLOGY

,CAST(CASE

                           WHEN COUNT(SPM.CALCULATION_PERIODS) OVER (PARTITION BY  A.HIERARCHY_NO,A.PARENT_HIERARCHY_NO,A.LEVEL_NAME,SPM.USER_GROUP,SPM.SALES_INCLUSION,SPM.CALCULATION_PERIODS)> 1 THEN ''-''

                           ELSE MAX(SPM.CALCULATION_PERIODS) OVER (PARTITION BY  A.HIERARCHY_NO,A.PARENT_HIERARCHY_NO,A.LEVEL_NAME,SPM.USER_GROUP,SPM.SALES_INCLUSION)

                       END AS VARCHAR(100)) CALCULATION_PERIODS

FROM (SELECT HIERARCHY_NO,

              --COALESCE(PARENT_HIERARCHY_NO, HIERARCHY_NO) PARENT_HIERARCHY_NO,

              PARENT_HIERARCHY_NO,

              LEVEL_NAME,

              CCP_DETAILS_SID,LEVEL_NO

       --INTO #PERF

       FROM (

              SELECT HIERARCHY_NO,

                     LEVEL_NAME,

                     PARENT_HIERARCHY_NO,

                     CCP_DETAILS_SID,

                     ITEM_MASTER_SID,LEVEL_NO

              FROM #TEMP_CCP

              ) C

              GROUP BY HIERARCHY_NO,

              --COALESCE(PARENT_HIERARCHY_NO, HIERARCHY_NO),

              PARENT_HIERARCHY_NO,

              LEVEL_NAME,LEVEL_NO,

              CCP_DETAILS_SID) A

                JOIN ', @S_MASTER_TABLE, ' SPM ON SPM.CCP_DETAILS_SID = A.CCP_DETAILS_SID  AND SPM.FILTER_CCP=1

                ', CASE
                                  WHEN @SALES_INCLUSION IS NOT NULL THEN Concat ('AND SALES_INCLUSION =', @SALES_INCLUSION)
                                END, '

              --  GROUP BY  A.HIERARCHY_NO,

              --A.PARENT_HIERARCHY_NO,

              --A.LEVEL_NAME,

              --A.CCP_DETAILS_SID

              --,SPM.USER_GROUP

--,SPM.SALES_INCLUSION

')

DECLARE @SQL_DT1      NVARCHAR(MAX) = N'',
        @LEVEL_NO1    INT = 1,
        @MAX_LEVEL_NO INT

SET @MAX_LEVEL_NO = (SELECT Max(LEVEL_NO)
                     FROM   #TEMP_CCP)

IF Object_id('TEMPDB..#SALES_RESULT') IS NOT NULL
  DROP TABLE #SALES_RESULT

CREATE TABLE #SALES_RESULT
  (
     RNO              INT,
     LEVEL_NO         SMALLINT,
     YEAR             SMALLINT,
     PERIOD           SMALLINT,
     ACTUALSALES      NUMERIC(22, 6),
     ACTUALUNITS      NUMERIC(22, 6),
     PROJECTION_SALES NUMERIC(22, 6),
     PROJECTION_UNITS NUMERIC(22, 6),
     ACCOUNT_GROWTH   NUMERIC(22, 6),
     PRODUCT_GROWTH   NUMERIC(22, 6),
     ACTUALPROJ       BIT
  )

IF @SCREEN_NAME = 'SALES'
  BEGIN
      ---SELECT @D_SQL
      INSERT INTO #PERF
                  ([HIERARCHY_NO],
                   [PARENT_HIERARCHY_NO],
                   [LEVEL_NAME],
                   [CCP_DETAILS_SID],
                   [USER_GROUP],
                   [SALES_INCLUSION],
                   LEVEL_NO,
                   [METHODOLOGY],
                   [CALCULATION_PERIODS])
      EXEC (@D_SQL)

      --SELECT GETDATE() AS PERF
      IF Object_id('tempdb..#T1234') IS NOT NULL
        DROP TABLE #T1234

      CREATE TABLE #T1234
        (
           HIERARCHY_NO        VARCHAR(1000),
           PARENT_HIERARCHY_NO VARCHAR(1000),
           level_no            SMALLINT NOT NULL,
           calculation_periods VARCHAR(100),
           methodology         VARCHAR(50),
           user_group          VARCHAR(50),
           sales_inclusion     VARCHAR(50),
           rno                 INT NOT NULL,
        )

      INSERT INTO #T1234
      SELECT *,
             Dense_rank()
               OVER (
                 PARTITION BY level_no
                 ORDER BY hierarchy_no, parent_hierarchy_no ) rno
      FROM   (SELECT HIERARCHY_NO,
                     PARENT_HIERARCHY_NO,
                     level_no,
                     Max(calculation_periods) calculation_periods,
                     Max(methodology)         AS methodology,
                     Max(user_group)          AS user_group,
                     sales_inclusion
              FROM   #perf
              GROUP  BY level_no,
                        HIERARCHY_NO,
                        PARENT_HIERARCHY_NO,
                        sales_inclusion) b

      --SELECT GETDATE() AST1234
      SET @SQL_DT1 = Concat ('   INSERT INTO #SALES_RESULT(

RNO,

LEVEL_NO,

YEAR,

PERIOD,

ACTUALSALES,

ACTUALUNITS,

PROJECTION_SALES,

PROJECTION_UNITS,

ACCOUNT_GROWTH,

PRODUCT_GROWTH,

ACTUALPROJ

              )

            

       SELECT

              RNO,

                        LEVEL_NO,

                        [YEAR],

                        PERIOD, 

          CONTRACT_SALES_ACTUALS

                    ,CONTRACT_UNITS_ACTUALS

                     ,NULL AS CONTRACT_SALES_PROJECTED

                     ,NULL AS CONTRACT_UNITS_PROJECTED

                     ,0 AS ACCOUNT_GROWTH

                     ,0 AS PRODUCT_GROWTH

                    

                     --,LEVEL_NAME

                     ,1 AS ACTUAL_PROJ

                   

              FROM (

SELECT P.RNO,

       P.LEVEL_NO,

       CONTRACT_SALES_ACTUALS = SUM(IIF(( b.SALES_INCLUSION = @SALES_INCLUSION

                                           OR @SALES_INCLUSION IS NULL ), ISNULL(ACTUAL_SALES, 0), NULL)),

       CONTRACT_UNITS_ACTUALS = SUM(IIF(( b.SALES_INCLUSION = @SALES_INCLUSION

                                           OR @SALES_INCLUSION IS NULL ), ISNULL(ACTUAL_UNITS, 0), NULL)),

       AA.PERIOD,

       AA.YEAR --,MAX(ACCOUNT_GROWTH) AS ACCOUNT_GROWTH,MAX(PRODUCT_GROWTH) AS PRODUCT_GROWTH

       

       

       

FROM  ', @S_ACTUAL_TABLE, '   A

       jOIN ', @S_MASTER_TABLE, ' B ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID

       JOIN ', @CCP_HIERARCHY, ' CCP ON CCP.CCP_DETAILS_SID=A.CCP_DETAILS_SID

       JOIN #PERF PE ON PE.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID

            JOIN #T1234 P

              ON P.HIERARCHY_NO=PE.HIERARCHY_NO AND (P.PARENT_HIERARCHY_NO=PE.PARENT_HIERARCHY_NO OR (P.LEVEL_NO=1 AND PE.LEVEL_NO=1))

       JOIN #PERIOD AA

         ON A.PERIOD_SID = AA.PERIOD_SID

           AND AA.PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, '

GROUP  BY P.RNO,

                p.LEVEL_NO,

                 AA.[YEAR],

          AA.PERIOD

          ')
      --SELECT CONTRACT_SALES_ACTUALS = SUM(IIF((
      --                           SALES_INCLUSION = @SALES_INCLUSION
      --                           OR @SALES_INCLUSION IS NULL
      --                           ), ISNULL(ACTUAL_SALES, 0), NULL))
      --      ,CONTRACT_UNITS_ACTUALS = SUM(IIF((
      --                           SALES_INCLUSION = @SALES_INCLUSION
      --                           OR @SALES_INCLUSION IS NULL
      --                           ), ISNULL(ACTUAL_UNITS, 0), NULL))
      --      ,AA.PERIOD
      --      ,AA.[YEAR]
      --      ,A.HIERARCHY_NO
      --      ,A.LEVEL_NAME
      --      ,A.PARENT_HIERARCHY_NO
      --      ,USER_GROUP
      --      , MAX(A.METHODOLOGY) METHODOLOGY
      --      ,MAX(A.CALCULATION_PERIODS) CALCULATION_PERIODS,SALES_INCLUSION
      --FROM (SELECT HIERARCHY_NO  ,PARENT_HIERARCHY_NO ,LEVEL_NAME   ,CCP_DETAILS_SID,       USER_GROUP    ,SALES_INCLUSION,METHODOLOGY,CALCULATION_PERIODS FROM #PERF) A
      -- JOIN ',@S_ACTUAL_TABLE,' NPS ON NPS.CCP_DETAILS_SID = A.CCP_DETAILS_SID
      --JOIN #PERIOD AA ON NPS.PERIOD_SID = AA.PERIOD_SID
      --      AND AA.PERIOD_SID BETWEEN ',@STAT_SALES_SID,' AND ',@END_SALES_SID,'
      --GROUP BY AA.PERIOD
      --      ,AA.[YEAR]
      --      ,A.HIERARCHY_NO
      --      ,A.LEVEL_NAME
      --      ,A.PARENT_HIERARCHY_NO
      --      ,USER_GROUP
      --      ,SALES_INCLUSION                 
      SET @SQL_DT1 = Concat (@SQL_DT1, ' ) ACT

             

              UNION ALL

             

              SELECT

                                 RNO,

                        LEVEL_NO,

                        [YEAR],

                        PERIOD,   

                               NULL AS CONTRACT_SALES_ACTUALS

                     ,NULL AS CONTRACT_UNITS_ACTUALS

                     ,CONTRACT_SALES_PROJECTED

                     ,CONTRACT_UNITS_PROJECTED

                     ,ACCOUNT_GROWTH

                     ,PRODUCT_GROWTH

                   

                     

                    -- ,LEVEL_NAME

                   

                    

                    

                     ,0 AS ACTUAL_PROJ

                   

              FROM (SELECT P.RNO,

       P.LEVEL_NO,

       CONTRACT_SALES_PROJECTED = SUM(IIF(( b.SALES_INCLUSION = @SALES_INCLUSION

                                           OR @SALES_INCLUSION IS NULL ), ISNULL(PROJECTION_SALES, 0), NULL)),

       CONTRACT_UNITS_PROJECTED = SUM(IIF(( b.SALES_INCLUSION = @SALES_INCLUSION

                                           OR @SALES_INCLUSION IS NULL ), ISNULL(PROJECTION_UNITS, 0), NULL)),

       AA.PERIOD,

       AA.YEAR,MAX(ACCOUNT_GROWTH) AS ACCOUNT_GROWTH,MAX(PRODUCT_GROWTH) AS PRODUCT_GROWTH

      

     FROM    ', @S_PROJECTION_TABLE, '   A

            jOIN ', @S_MASTER_TABLE, ' B ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID

       JOIN ', @CCP_HIERARCHY, ' CCP ON CCP.CCP_DETAILS_SID=A.CCP_DETAILS_SID

       JOIN #PERF PE ON PE.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID

            JOIN #T1234 P

              ON P.HIERARCHY_NO=PE.HIERARCHY_NO AND (P.PARENT_HIERARCHY_NO=PE.PARENT_HIERARCHY_NO OR (P.LEVEL_NO=1 AND PE.LEVEL_NO=1))

       JOIN #PERIOD AA

         ON A.PERIOD_SID = AA.PERIOD_SID

           AND AA.PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '

GROUP  BY P.RNO,

                p.LEVEL_NO,

                 AA.[YEAR],

          AA.PERIOD ')
      SET @SQL_DT1= Concat(@SQL_DT1, ' ) PROJ
     
       ')

      EXEC Sp_executesql
        @SQL_DT1,
        N'@SALES_INCLUSION BIT,@DEDUCTION_INCLUSION  BIT',
        @SALES_INCLUSION = @SALES_INCLUSION,
        @DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION

      SELECT t.HIERARCHY_NO,
             PERIOD,
             YEAR,
             ACTUALPROJ,
             Iif(@CP_INDICATOR = 'C'
                  OR @CP_INDICATOR = 'P', NULL, T.PARENT_HIERARCHY_NO) PARENT_HIERARCHY_NO,
             ACCOUNT_GROWTH,
             PRODUCT_GROWTH,
             PROJECTION_SALES,
             PROJECTION_UNITS,
             ACTUALSALES,
             ACTUALUNITS,
             t.METHODOLOGY,
             t.USER_GROUP,
             t.CALCULATION_PERIODS,
             t.SALES_INCLUSION
      FROM   #SALES_RESULT C
             INNER JOIN #T1234 t
                     ON c.rno = t.rno
                        AND c.level_no = T.level_no
      ORDER  BY T.LEVEL_NO,
                T.HIERARCHY_NO,
                T.PARENT_HIERARCHY_NO,
                YEAR,
                PERIOD
  END
ELSE
  BEGIN
      --------------------------------------------------------------------
      -------------------------------------------------------------------------------------
      DECLARE @ITEMID [DBO].[UDT_ITEM]

      INSERT INTO @ITEMID
      /*SELECT IM.ITEM_MASTER_SID
      
             FROM ITEM_MASTER IM
      
             WHERE EXISTS (
      
                           SELECT 1
      
                           FROM #TEMP_CCP A
      
                           WHERE PROJECTION_MASTER_SID = @FIRST_PROJ_SID AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID
      
                           )*/
      SELECT DISTINCT ITEM_MASTER_SID
      FROM   #TEMP_CCP A

      SET @CURRENT_DATE = CASE
                            WHEN LEFT(@FREQUENCY, 1) = 'M' THEN Dateadd(DD, 1, Eomonth(Getdate(), -1))
                            WHEN LEFT(@FREQUENCY, 1) = 'Q' THEN Datefromparts(Year(Getdate()), Datepart(QUARTER, Getdate()), 01)
                            WHEN LEFT(@FREQUENCY, 1) = 'S' THEN Datefromparts(Year(Getdate()), ( ( ( ( ( Month(Getdate()) ) - 1 ) / 6 ) * 6 ) + 1 ), 01)
                            ELSE Datefromparts(Year(Getdate()), 01, 01)
                          END
      SET @SALES_INCLUSION = CONVERT(CHAR(1), @SALES_INCLUSION)
      SET @DEDUCTION_INCLUSION = CONVERT(CHAR(1), @DEDUCTION_INCLUSION)

      IF Object_id('TEMPDB..#ITEM_PRICING') IS NOT NULL
        DROP TABLE #ITEM_PRICING

      SELECT *
      INTO   #ITEM_PRICING
      FROM   [DBO].[Udf_item_pricing](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH')

      IF Object_id('TEMPDB..#ACCRUAL_DISCOUNT') IS NOT NULL
        DROP TABLE #ACCRUAL_DISCOUNT;

      CREATE TABLE #ACCRUAL_DISCOUNT
        (
           --PROJECTION_MASTER_SID INT,
           CCP_DETAILS_SID     INT,
           PERIOD_SID          INT,
           RS_CONTRACT_SID     INT,
           DISCOUNT_AMOUNT     NUMERIC(38, 6),
           HIERARCHY_NO        VARCHAR(100),
           LEVEL_NO            VARCHAR(100),
           PARENT_HIERARCHY_NO VARCHAR(8000)
        )

      DECLARE @SQL_ACC NVARCHAR(max)

      SET @SQL_ACC = ' ;

 

WITH CTE

AS (

       SELECT DISTINCT COMPANY_MASTER_SID

              ,CONTRACT_MASTER_SID

              ,ITEM_MASTER_SID

              --,PROJECTION_MASTER_SID

              ,A.CCP_DETAILS_SID

              ,PERIOD_SID

              ,PERIOD_DATE

              ,YEAR

              ,RS.RS_CONTRACT_SID

              ,RS.RS_MODEL_SID

              ,HIERARCHY_NO

        ,LEVEL_NO

              ,COALESCE(PARENT_HIERARCHY_NO,HIERARCHY_NO) PARENT_HIERARCHY_NO

             

       FROM #TEMP_CCP A

       JOIN #MULTISELECT_DISCOUNTS RS ON RS.CCP_DETAILS_SID = A.CCP_DETAILS_SID

       JOIN #PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID

                     AND @END_PERIOD_SID

      

       )

INSERT INTO #ACCRUAL_DISCOUNT (

       --PROJECTION_MASTER_SID,

       CCP_DETAILS_SID

       ,PERIOD_SID

       ,RS_CONTRACT_SID

       ,DISCOUNT_AMOUNT

              ,HIERARCHY_NO

        ,LEVEL_NO

              ,PARENT_HIERARCHY_NO

       )

SELECT --PROJECTION_MASTER_SID,

       A2.CCP_DETAILS_SID

       ,PERIOD_SID

       ,A2.RS_CONTRACT_SID

       ,SUM(DEDUCTION_AMOUNT) / (DATEDIFF(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE) + 1) DISCOUNT_AMOUNT

              ,HIERARCHY_NO

        ,LEVEL_NO

              ,PARENT_HIERARCHY_NO

FROM ACCRUAL_MASTER A1

JOIN CTE A2 ON A2.PERIOD_DATE BETWEEN CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_START_DATE, 0))))

              AND CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_END_DATE, 0))))

       AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID

       AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID

       AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID

       AND A2.RS_MODEL_SID = A1.RS_MODEL_SID

       AND DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_START_DATE, - 1)) >= @STARTFROM

       AND A1.ACCRUAL_PERIOD_END_DATE <= @PROJECTION_DATE

GROUP BY --PROJECTION_MASTER_SID,

       A2.CCP_DETAILS_SID

       ,PERIOD_SID

       ,A2.RS_CONTRACT_SID

       ,ACCRUAL_PERIOD_START_DATE

       ,ACCRUAL_PERIOD_END_DATE

              ,HIERARCHY_NO

       ,LEVEL_NO

              ,PARENT_HIERARCHY_NO'

      EXEC Sp_executesql
        @SQL_ACC,
        N'@START_PERIOD_SID INT,@END_PERIOD_SID INT,@STARTFROM DATETIME,@PROJECTION_DATE DATETIME',
        @START_PERIOD_SID = @START_PERIOD_SID,
        @END_PERIOD_SID = @END_PERIOD_SID,
        @STARTFROM = @STARTFROM,
        @PROJECTION_DATE = @PROJECTION_DATE

      IF Object_id('tempdb..#PRODUCT_FILE_TEMP') IS NOT NULL
        DROP TABLE #PRODUCT_FILE_TEMP

      CREATE TABLE #PRODUCT_FILE_TEMP
        (
           PROJECTION_MASTER_SID          INT,
           ITEM_MASTER_SID                INT,
           PERIOD_SID                     INT,
           EXFACTORY_ACTUAL_SALES         NUMERIC(38, 6),
           EXFACTORY_ACTUAL_UNITS         NUMERIC(38, 6),
           EXFACTORY_FORECAST_SALES       NUMERIC(38, 6),
           EXFACTORY_FORECAST_UNITS       NUMERIC(38, 6),
           DEMAND_ACTUAL_SALES            NUMERIC(38, 6),
           DEMAND_ACTUAL_UNITS            NUMERIC(38, 6),
           DEMAND_FORECAST_SALES          NUMERIC(38, 6),
           DEMAND_FORECAST_UNITS          NUMERIC(38, 6),
           ADJUSTED_DEMAND_ACTUAL_SALES   NUMERIC(38, 6),
           ADJUSTED_DEMAND_ACTUAL_UNITS   NUMERIC(38, 6),
           ADJUSTED_DEMAND_FORECAST_SALES NUMERIC(38, 6),
           ADJUSTED_DEMAND_FORECAST_UNITS NUMERIC(38, 6),
           INVENTORY_ACTUAL_SALES         NUMERIC(38, 6),
           INVENTORY_ACTUAL_UNITS         NUMERIC(38, 6),
           INVENTORY_FORECAST_SALES       NUMERIC(38, 6),
           INVENTORY_FORECAST_UNITS       NUMERIC(38, 6),
           ITEM_PRICE                     NUMERIC(38, 6),
           EXFACTORY_CUST_ACTUAL_SALES    NUMERIC(38, 6),
           EXFACTORY_CUST_ACTUAL_UNITS    NUMERIC(38, 6),
           EXFACTORY_CUST_FORECAST_SALES  NUMERIC(38, 6),
           EXFACTORY_CUST_FORECAST_UNITS  NUMERIC(38, 6),
           INVENTORY_CUST_ACTUAL_SALES    NUMERIC(38, 6),
           INVENTORY_CUST_ACTUAL_UNITS    NUMERIC(38, 6),
           INVENTORY_CUST_FORECAST_SALES  NUMERIC(38, 6),
           INVENTORY_CUST_FORECAST_UNITS  NUMERIC(38, 6)
        )

      IF ( (SELECT Count(ID)
            FROM   #PROJECTION_MASTER) > 1 )
        BEGIN
            INSERT INTO #PRODUCT_FILE_TEMP
            EXEC dbo.Prc_prior_proj_prod_file_data
        END

      IF @VIEW = 'DETAIL_TOTAL_DISCOUNT'
        BEGIN
            IF Object_id('TEMPDB..#DATA_TABLE') IS NOT NULL
              DROP TABLE #DATA_TABLE

            SELECT HIERARCHY_NO,
                   COALESCE(PARENT_HIERARCHY_NO, HIERARCHY_NO) PARENT_HIERARCHY_NO,
                   LEVEL_NO,level_name,
                   PERIOD,
                   YEAR,
                   PERIOD_SID,
                   CCP_DETAILS_SID,
                   ITEM_MASTER_SID
            INTO   #DATA_TABLE
            FROM   (SELECT DISTINCT HIERARCHY_NO,
                                    LEVEL_NO,level_name,
                                    PARENT_HIERARCHY_NO,
                                    CCP_DETAILS_SID,
                                    ITEM_MASTER_SID
                    FROM   #TEMP_CCP ) C
                   CROSS JOIN (SELECT DISTINCT PERIOD,
                                               YEAR,
                                               PERIOD_SID
                               FROM   #PERIOD
                               WHERE  PERIOD_SID BETWEEN @PROJ_START_SID AND @PROJ_END_SID) P

            DECLARE @ITEM_INFO TABLE
              (
                 ITEM_ID         VARCHAR(50),
                 NDC8            VARCHAR(20),
                 ITEM_MASTER_SID INT
              )
            DECLARE @SQL_DT NVARCHAR(MAX) = ''

            SET @SQL_DT = Concat (' INSERT INTO #PIVOT_RESULT (

                     PROJECTION_ID

                     ,LEVEL_NO

                     ,HIERARCHY_NO

                     ,PARENT_HIERARCHY_NO

                     ,CP_INDICATOR

                     ,PERIOD

                     ,YEAR

                     ,EX_FACTORY_SALES_ACTUALS

                     ,EX_FACTORY_SALES_PROJECTED

                     ,DEMAND_SALES_ACTUALS

                     ,DEMAND_SALES_PROJECTED

                     ,INVENTORY_WITHDRAWAL_SALES_ACTUALS

                     ,INVENTORY_WITHDRAWAL_SALES_PROJECTED

                     ,EX_FACTORY_SALES_ACTUALS_PERCENT

                     ,EX_FACTORY_SALES_PROJECTED_PERCENT

                     ,DEMAND_SALES_ACTUALS_PERCENT

                     ,DEMAND_SALES_PROJECTED_PERCENT

                     ,INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT

                     ,INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT

                     ,CONTRACT_SALES_ACTUALS

                     ,CONTRACT_SALES_PROJECTED

                     ,CONTRACT_UNITS_ACTUALS

                     ,CONTRACT_UNITS_PROJECTED

                     ,TOTAL_DISCOUNT_ACTUALS

                     ,TOTAL_DISCOUNT_PROJECTED

                     ,TOTAL_DISCOUNT_ACTUALS_PERCENTAGE

                     ,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE

                     ,TOTAL_RPU_ACTUALS

                     ,TOTAL_RPU_PROJECTED

                     ,NET_SALES_ACTUALS

                     ,NET_SALES_PROJECTED

                     ,COGS_ACTUALS

                     ,COGS_PROJECTED

                     ,NET_PROFIT_ACTUALS

                     ,NET_PROFIT_PROJECTED

                     ,NET_SALES_OF_EX_FACTORY_ACTUALS

                     ,NET_SALES_OF_EX_FACTORY_PROJECTED

                     ,DISCOUNT_OF_EX_FACTORY_ACTUALS

                     ,DISCOUNT_OF_EX_FACTORY_PROJECTED

                     ,NET_EX_FACTORY_SALES_ACTUALS ---------------CEL-386

                     ,NET_EX_FACTORY_SALES_PROJECTED ---------------CEL-386

                     ,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS ---------------CEL-386                         

                     ,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED ---------------CEL-386

                     ,TOTAL_DISCOUNT_ACCRUAL

                     )

                SELECT  @FIRST_PROJ_SID  PROJECTION_ID,

                    DT.LEVEL_NO,

                   DT.HIERARCHY_NO,

                              DT.PARENT_HIERARCHY_NO,

                    @CP_INDICATOR ,

                   DT.PERIOD,

                   DT.YEAR,

                   EX_FACTORY_SALES_ACTUALS = CASE WHEN @CURRENT_DATE > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)  ELSE NULL END ,

                   EX_FACTORY_SALES_PROJECTED = ISNULL(GTS.GTS_SALES_PROJECTED, 0),

                   DEMAND_SALES_ACTUALS =CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(GTS.DEMAND_SALES_ACTUAL, 0)  ELSE NULL END,

                   DEMAND_SALES_PROJECTED = ISNULL(GTS.DEMAND_SALES_PROJECTED, 0),

                   INVENTORY_WITHDRAWAL_SALES_ACTUALS =CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(GTS.ACT_AMOUNT_WITHDRAWN, 0)  ELSE NULL END,

                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = ISNULL(GTS.FOR_AMOUNT_WITHDRAWN, 0),

                   EX_FACTORY_SALES_ACTUALS_PERCENT = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100  ELSE NULL END,

                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,

                   DEMAND_SALES_ACTUALS_PERCENT =CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100  ELSE NULL END,

                   DEMAND_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,

                   INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT =CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100  ELSE NULL END,

                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,

                   CONTRACT_SALES_ACTUALS = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0)  ELSE NULL END,

                   CONTRACT_SALES_PROJECTED = ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0),

                   CONTRACT_UNITS_ACTUALS = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(CONTRACT_UNITS_ACTUALS, 0)  ELSE NULL END,

                  CONTRACT_UNITS_PROJECTED = ISNULL(CONTRACT_UNITS_PROJECTED, 0),

                              TOTAL_DISCOUNT_ACTUALS = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL

                                              + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0),0)  ELSE NULL END,

                   TOTAL_DISCOUNT_PROJECTED = ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED

                                              + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0), 0),

                   TOTAL_DISCOUNT_ACTUALS_PERCENTAGE = CASE WHEN @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ( ISNULL(( DISC.CONTRACT_DISCOUNT_ACTUAL

                                                                    + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100  ELSE NULL END,

                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( ISNULL(( DISC.CONTRACT_DISCOUNT_PROJECTED

                                                                    + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,

                   TOTAL_RPU_ACTUALS = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ( ISNULL(( DISC.CONTRACT_DISCOUNT_ACTUAL

                                                    + ISNULL(PPA.PPA_ACTUAL_RPU, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) )  ELSE NULL END,

                   TOTAL_RPU_PROJECTED = ( ISNULL(( DISC.CONTRACT_DISCOUNT_PROJECTED

                                                    + ISNULL(PPA.PPA_PROJECTED_RPU, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),

                   NET_SALES_ACTUALS =CASE WHEN  @CURRENT_DATE > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(SALES.CONTRACT_SALES_ACTUALS - ( DISC.CONTRACT_DISCOUNT_ACTUAL

                                                                                       + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) ), 0)  ELSE NULL END,

                   NET_SALES_PROJECTED = ISNULL(SALES.CONTRACT_SALES_PROJECTED - ( DISC.CONTRACT_DISCOUNT_PROJECTED

                                                                                       + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ),0),

                   COGS_ACTUALS = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(SALES.COGS_ACTUALS, 0)  ELSE NULL END,

                   COGS_PROJECTED = ISNULL(SALES.COGS_PROJECTED, 0),

                   NET_PROFIT_ACTUALS =CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  isnull(( ( SALES.CONTRACT_SALES_ACTUALS - ( DISC.CONTRACT_DISCOUNT_ACTUAL

                                                                                            + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) ) ) - ( SALES.COGS_ACTUALS  )),0)  ELSE NULL END,

                   NET_PROFIT_PROJECTED = isnull(( ( SALES.CONTRACT_SALES_PROJECTED - ( DISC.CONTRACT_DISCOUNT_PROJECTED

                                                                                            + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( SALES.COGS_PROJECTED ) ),0),

                                                                                                                                                             

                              NET_SALES_OF_EX_FACTORY_ACTUALS =CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  (COALESCE((( SALES.CONTRACT_SALES_ACTUALS - ( DISC.CONTRACT_DISCOUNT_ACTUAL

                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUAL, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100)  ELSE NULL END,

                   NET_SALES_OF_EX_FACTORY_PROJECTED = (COALESCE((( SALES.CONTRACT_SALES_PROJECTED - ( DISC.CONTRACT_DISCOUNT_PROJECTED

                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100)

                     ,DISCOUNT_OF_EX_FACTORY_ACTUALS = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN COALESCE(((DISC.CONTRACT_DISCOUNT_ACTUAL + Isnull(PPA.PPA_DISCOUNT_ACTUAL, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100  ELSE NULL END,

                           DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE((isnull((DISC.CONTRACT_DISCOUNT_PROJECTED)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),0))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100

                           ,NET_EX_FACTORY_SALES_ACTUALS=((CASE WHEN @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)  ELSE NULL END )-(CASE WHEN  @CURRENT_DATE > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL

                                              + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0), 0)  ELSE NULL END))

                                                -----------cel-386

                                  ,NET_EX_FACTORY_SALES_PROJECTED =ISNULL(GTS.GTS_SALES_PROJECTED, 0)-(DISC.CONTRACT_DISCOUNT_PROJECTED + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0))-----------cel-386

                        ,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS=ISNULL(((CASE WHEN  @CURRENT_DATE > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)  ELSE NULL END )-(CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN isnull(DISC.CONTRACT_DISCOUNT_ACTUAL

                                              + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0),0)  ELSE NULL END))/nullif(CASE WHEN @CURRENT_DATE > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)  ELSE NULL END ,0),0)*100

                                                                             -----------cel-386

                                 

                                   ,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED=ISNULL((ISNULL(GTS.GTS_SALES_PROJECTED, 0)-(DISC.CONTRACT_DISCOUNT_PROJECTED + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED, 0),0)*100

                                                                    -----------cel-386

                                  ,ACCRUAL_DISCOUNT AS TOTAL_DISCOUNT_ACCRUAL

 

            FROM   (

       SELECT DISTINCT HIERARCHY_NO

              ,PERIOD

              ,YEAR

              ,LEVEL_NO

              ,PARENT_HIERARCHY_NO

       FROM #DATA_TABLE

       ) DT

                    JOIN (SELECT ACT_AMOUNT_WITHDRAWN = SUM(INVENTORY_ACTUAL_SALES)

              ,ACT_UNITS_WITHDRAWN = SUM(INVENTORY_ACTUAL_UNITS)

              ,FOR_AMOUNT_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES))

              ,FOR_UNITS_WITHDRAWN = SUM(INVENTORY_FORECAST_UNITS)

              ,DEMAND_SALES_ACTUAL = SUM(DEMAND_ACTUAL_SALES)

              ,ACT_GROSS_UNITS = SUM(DEMAND_ACTUAL_UNITS)

              ,DEMAND_SALES_PROJECTED = SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES))

              ,FOR_GROSS_UNITS = SUM(DEMAND_FORECAST_UNITS)

              ,GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES)

              ,GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES))

              ,UNITS = SUM(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS))

              ,A.PERIOD

              ,A.YEAR

              ,A.HIERARCHY_NO

              ,A.LEVEL_NO

              ,A.PARENT_HIERARCHY_NO

       FROM (SELECT distinct HIERARCHY_NO,LEVEL_NO,PARENT_HIERARCHY_NO,ITEM_MASTER_SID,period_sid,period,year FROM  #DATA_TABLE) A

       JOIN ', @PRODUCT_TABLE, ' PF

              ON PF.ITEM_MASTER_SID = A.ITEM_MASTER_SID AND PF.PERIOD_SID = A.PERIOD_SID

       GROUP BY A.PERIOD

              ,A.YEAR

              ,A.HIERARCHY_NO

              ,A.LEVEL_NO

              ,A.PARENT_HIERARCHY_NO) GTS

                          ON GTS.PERIOD = DT.PERIOD

                             AND GTS.YEAR = DT.YEAR

                                                  AND GTS.HIERARCHY_NO = DT.HIERARCHY_NO

                                                                                    AND GTS.PARENT_HIERARCHY_NO=DT.PARENT_HIERARCHY_NO

                             AND GTS.LEVEL_NO = DT.LEVEL_NO

                    JOIN (SELECT CONTRACT_SALES_ACTUALS = SUM(IIF((SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL), ISNULL(ACTUAL_SALES,0), NULL))

              ,CONTRACT_UNITS_ACTUALS = SUM(IIF((SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL), ISNULL(ACTUAL_UNITS,0), NULL))

              ,COGS_ACTUALS = SUM(ISNULL(IIF((SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL), ISNULL(ACTUAL_UNITS,0), NULL), 0) *  COALESCE(NULLIF(UOM_VALUE, 0),1)  *  COALESCE(NULLIF(UOM_VALUE, 0),1) )

              ,CONTRACT_SALES_PROJECTED = SUM(IIF((SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL), ISNULL(PROJECTION_SALES,0), NULL))

              ,CONTRACT_UNITS_PROJECTED = SUM(IIF((SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL), ISNULL(PROJECTION_UNITS,0), NULL))

              ,COGS_PROJECTED = SUM(ISNULL(IIF((SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL), ISNULL(PROJECTION_UNITS,0), NULL), 0) *  COALESCE(NULLIF(UOM_VALUE, 0),1)  *  COALESCE(NULLIF(UOM_VALUE, 0),1) )

              ,A.PERIOD

              ,A.[YEAR]

              ,A.HIERARCHY_NO

              ,A.LEVEL_NO

              ,A.PARENT_HIERARCHY_NO

       FROM #DATA_TABLE A

       INNER JOIN CCP_DETAILS B

              ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID

       INNER JOIN ', @S_MASTER_TABLE, ' SPM

              ON SPM.CCP_DETAILS_SID = B.CCP_DETAILS_SID

              AND FILTER_CCP=1 AND IIF(@SCREEN_NAME  IS NULL,1,CHECK_RECORD)=1

       LEFT JOIN ', @S_ACTUAL_TABLE, ' NPS

              ON NPS.CCP_DETAILS_SID = B.CCP_DETAILS_SID AND NPS.PERIOD_SID = A.PERIOD_SID

       LEFT JOIN ', @S_PROJECTION_TABLE, ' NP

              ON NP.CCP_DETAILS_SID = B.CCP_DETAILS_SID AND NP.PERIOD_SID = A.PERIOD_SID

       LEFT JOIN #ITEM_UOM_DETAILS UOM

              ON UOM.ITEM_MASTER_SID = B.ITEM_MASTER_SID

       LEFT JOIN #ITEM_PRICING U

              ON B.ITEM_MASTER_SID = U.ITEM_MASTER_SID AND A.PERIOD_SID = U.PERIOD_SID

       GROUP BY A.PERIOD

              ,A.[YEAR]

              ,A.HIERARCHY_NO

              ,A.LEVEL_NO

              ,A.PARENT_HIERARCHY_NO

       ) SALES

                          ON SALES.PERIOD = DT.PERIOD

                             AND SALES.[YEAR] = DT.YEAR

                             AND SALES.HIERARCHY_NO = DT.HIERARCHY_NO

                                                AND SALES.PARENT_HIERARCHY_NO=DT.PARENT_HIERARCHY_NO

                             AND SALES.LEVEL_NO = DT.LEVEL_NO

LEFT JOIN (  SELECT CONTRACT_DISCOUNT_ACTUAL = SUM(IIF((DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION OR @DEDUCTION_INCLUSION IS NULL), ISNULL(ACTUAL_SALES,0), NULL))

              ,A.PERIOD

              ,A.[YEAR]

              ,A.HIERARCHY_NO

              ,A.LEVEL_NO

              ,SUM(DISCOUNT_AMOUNT) ACCRUAL_DISCOUNT

              ,A.PARENT_HIERARCHY_NO

              ,CONTRACT_DISCOUNT_PROJECTED = SUM(IIF((DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION OR @DEDUCTION_INCLUSION IS NULL), ISNULL(PROJECTION_SALES,0), NULL))

       FROM #DATA_TABLE A

       INNER JOIN CCP_DETAILS B

              ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID

       INNER JOIN ', @D_MASTER_TABLE, ' NDPM

              ON NDPM.CCP_DETAILS_SID = B.CCP_DETAILS_SID
			  AND IIF(A.LEVEL_NAME=''SCHEDULE ID'',A.HIERARCHY_NO,1)=IIF(A.LEVEL_NAME=''SCHEDULE ID'',NDPM.RS_CONTRACT_SID,1)
                           AND PV_FILTERS=1---FILTER_CCP=1

       LEFT JOIN ', @D_ACTUAL_TABLE, ' NAD

              ON NAD.CCP_DETAILS_SID = NDPM.CCP_DETAILS_SID AND NDPM.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID

              AND NAD.PERIOD_SID=A.PERIOD_SID

       LEFT JOIN ', @D_PROJECTION_TABLE, ' NDP

              ON NDP.CCP_DETAILS_SID = NDPM.CCP_DETAILS_SID AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID

              AND NDP.PERIOD_SID=A.PERIOD_SID

       LEFT JOIN #ACCRUAL_DISCOUNT AD

              ON NDPM.CCP_DETAILS_SID = AD.CCP_DETAILS_SID AND NDPM.RS_CONTRACT_SID = AD.RS_CONTRACT_SID AND A.PERIOD_SID = AD.PERIOD_SID AND A.HIERARCHY_NO = AD.HIERARCHY_NO AND A.PARENT_HIERARCHY_NO = AD.PARENT_HIERARCHY_NO AND A.LEVEL_NO = AD.LEVEL_NO

WHERE EXISTS (

                     SELECT 1

                     FROM #DISCOUNT_INFO A

                     WHERE CASE WHEN @DISCOUNT_LEVEL IN ( ''SCHEDULE ID'', ''PROGRAM'') THEN CAST(NDPM.RS_CONTRACT_SID AS VARCHAR(20)) ELSE A.TOKEN END = A.TOKEN

                     )

       GROUP BY PERIOD

              ,[YEAR]

              ,A.HIERARCHY_NO

              ,A.LEVEL_NO

              ,A.PARENT_HIERARCHY_NO) DISC

                          ON DISC.PERIOD = DT.PERIOD

                             AND DISC.[YEAR] = DT.[YEAR]

                             AND DISC.HIERARCHY_NO = DT.HIERARCHY_NO

                                                AND DISC.PARENT_HIERARCHY_NO=DT.PARENT_HIERARCHY_NO

                             AND DISC.LEVEL_NO = DT.LEVEL_NO

                   LEFT JOIN (

                              SELECT PPA_DISCOUNT_PROJECTED = PROJECTION_PPA_SALES

              ,PPA_PROJECTED_RPU = PPA_RPU

              ,PROJECTION_SALES AS PPA_PROJECTION_SALES

              ,PROJECTION_UNITS AS PPA_PROJECTION_UNITS

              ,PPA_DISCOUNT_ACTUAL = ACTUAL_PPA_SALES

              ,PPA_ACTUAL_RPU = PPA_RPU

              ,ACTUAL_SALES AS PPA_ACTUAL_SALES

              ,ACTUAL_UNITS AS PPA_ACTUAL_UNITS

              ,PERIOD

              ,YEAR

              ,HIERARCHY_NO

              ,PARENT_HIERARCHY_NO

              ,LEVEL_NO

       FROM (SELECT PPA_RPU = SUM(ACTUAL_DISCOUNT_DOLLAR)

                     ,ACTUAL_PPA_SALES = SUM(ACTUAL_DISCOUNT_DOLLAR)

                     ,A.PERIOD

                     ,A.[YEAR]

                     ,A.HIERARCHY_NO

                     ,A.LEVEL_NO

                     ,A.PARENT_HIERARCHY_NO

                     ,ACTUAL_SALES = SUM(IIF((SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL), ISNULL(NS.ACTUAL_SALES,0), NULL))

                     ,ACTUAL_UNITS = SUM(IIF((SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL), ISNULL(NS.ACTUAL_UNITS,0), NULL) *  COALESCE(NULLIF(UOM_VALUE, 0),1) )

                     ,PPA_RPU_PROJ = SUM(PROJECTION_DISCOUNT_DOLLAR)

                     ,PROJECTION_PPA_SALES = SUM(PROJECTION_DISCOUNT_DOLLAR)

                     ,PROJECTION_SALES = SUM(IIF((SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL), ISNULL(NSP.PROJECTION_SALES,0), NULL))

                     ,PROJECTION_UNITS = SUM(IIF((SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL), ISNULL(NSP.PROJECTION_UNITS,0), NULL) *  COALESCE(NULLIF(UOM_VALUE, 0),1) )

              FROM #DATA_TABLE A

              INNER JOIN CCP_DETAILS B

              ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID

              INNER JOIN ', @S_MASTER_TABLE, ' SPM

                     ON SPM.CCP_DETAILS_SID = B.CCP_DETAILS_SID

                     AND FILTER_CCP=1 AND IIF(@SCREEN_NAME  IS NULL,1,CHECK_RECORD)=1

              LEFT JOIN ', @P_ACTUAL_TABLE, ' NPP

                     ON SPM.CCP_DETAILS_SID = NPP.CCP_DETAILS_SID AND NPP.PERIOD_SID = A.PERIOD_SID

              LEFT JOIN ', @P_PROJECTION_TABLE, ' NP

                     ON SPM.CCP_DETAILS_SID = NP.CCP_DETAILS_SID AND NP.PERIOD_SID = A.PERIOD_SID

              INNER JOIN ', @S_ACTUAL_TABLE, ' NS

                     ON NS.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID AND NS.PERIOD_SID = A.PERIOD_SID

              INNER JOIN ', @S_PROJECTION_TABLE, ' NSP

                     ON NSP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID AND NSP.PERIOD_SID = A.PERIOD_SID

              LEFT JOIN #ITEM_UOM_DETAILS UOM

                     ON UOM.ITEM_MASTER_SID = B.ITEM_MASTER_SID

              GROUP BY A.PERIOD

                     ,A.[YEAR]

                     ,A.HIERARCHY_NO

                     ,A.LEVEL_NO

                     ,A.PARENT_HIERARCHY_NO)A) PPA

                          ON PPA.PERIOD = DT.PERIOD

                             AND PPA.[YEAR] = DT.[YEAR]

                             AND PPA.HIERARCHY_NO = DT.HIERARCHY_NO

                                                AND PPA.PARENT_HIERARCHY_NO=DT.PARENT_HIERARCHY_NO

                             AND PPA.LEVEL_NO = DT.LEVEL_NO

            ORDER  BY DT.PERIOD,

                      DT.[YEAR],

                      DT.HIERARCHY_NO,

                                    DT.PARENT_HIERARCHY_NO')

            EXEC Sp_executesql
              @SQL_DT,
              N'@SALES_INCLUSION BIT,@DEDUCTION_INCLUSION  BIT,@FIRST_PROJ_SID  INT,@CP_INDICATOR CHAR(1),@CURRENT_DATE DATE,@DISCOUNT_LEVEL VARCHAR(50),@SCREEN_NAME VARCHAR(100)',
              @SALES_INCLUSION = @SALES_INCLUSION,
              @DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION,
              @FIRST_PROJ_SID = @FIRST_PROJ_SID,
              @CP_INDICATOR = @CP_INDICATOR,
              @CURRENT_DATE = @CURRENT_DATE,
              @DISCOUNT_LEVEL = @DISCOUNT_LEVEL,
              @SCREEN_NAME = @SCREEN_NAME
			 ----- SELECT @SQL_DT,@SALES_INCLUSION,@DEDUCTION_INCLUSION,@FIRST_PROJ_SID,@CP_INDICATOR,@CURRENT_DATE,@DISCOUNT_LEVEL,@SCREEN_NAME
           
       

            IF EXISTS (SELECT 1
                       FROM   #PROJECTION_MASTER
                       WHERE  ID > 1)
              BEGIN
                  IF Object_id('TEMPDB..#PRIOR_PROJECTIONS') IS NOT NULL
                    DROP TABLE #PRIOR_PROJECTIONS

                  SELECT PD.PROJECTION_DETAILS_SID,
                         PM.PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         LEVEL_NO,
                         COALESCE(PARENT_HIERARCHY_NO, HIERARCHY_NO) PARENT_HIERARCHY_NO
                  INTO   #PRIOR_PROJECTIONS
                  FROM   #PROJECTION_MASTER PM
                         INNER JOIN PROJECTION_DETAILS PD
                                 ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                         INNER JOIN #CCP C
                                 ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                  WHERE  PM.ID <> 1

                  IF Object_id('TEMPDB..#SALES_INCLUSION') IS NOT NULL
                    DROP TABLE #SALES_INCLUSION

                  SELECT DISTINCT A.PROJECTION_DETAILS_SID,
                                  B.CCP_DETAILS_SID,
                                  CASE
                                    WHEN DESCRIPTION = 'YES' THEN 1
                                    ELSE 0
                                  END SALES_INCLUSION
                  INTO   #SALES_INCLUSION
                  FROM   NM_SALES_PROJECTION_MASTER A
                         INNER JOIN PROJECTION_DETAILS B
                                 ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                    AND EXISTS (SELECT 1
                                                FROM   #PRIOR_PROJECTIONS PM
                                                WHERE  PM.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID
                                                       AND PM.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID)
                         INNER JOIN CCP_DETAILS CD
                                 ON CD.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                         INNER JOIN CFP_CONTRACT_DETAILS CC
                                 ON CC.COMPANY_MASTER_SID = CD.COMPANY_MASTER_SID
                         INNER JOIN CFP_CONTRACT CC1
                                 ON CC1.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
                                    AND CC1.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
                         INNER JOIN HELPER_TABLE HT
                                 ON HT.HELPER_TABLE_SID = CC1.SALES_INCLUSION

                  IF Object_id('TEMPDB..#DEDUCTION_INCLUSION') IS NOT NULL
                    DROP TABLE #DEDUCTION_INCLUSION

                  SELECT distinct A.PROJECTION_DETAILS_SID,
                         B.CCP_DETAILS_SID,
                         RS.RS_CONTRACT_SID,
                         CASE
                           WHEN DESCRIPTION = 'YES' THEN 1
                           ELSE 0
                         END DEDUCTION_INCLUSION
                  INTO   #DEDUCTION_INCLUSION
                  FROM   NM_DISCOUNT_PROJ_MASTER A
                         INNER JOIN PROJECTION_DETAILS B
                                 ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                    AND EXISTS (SELECT 1
                                                FROM   #PRIOR_PROJECTIONS PM
                                                WHERE  PM.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID
                                                       AND PM.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID)
                         INNER JOIN RS_CONTRACT RS
                                 ON A.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
                         INNER JOIN HELPER_TABLE HT
                                 ON HT.HELPER_TABLE_SID = RS.DEDUCTION_INCLUSION

                  IF Object_id('TEMPDB..#PRIOR_ACCRUAL_DISCOUNT') IS NOT NULL
                    DROP TABLE #PRIOR_ACCRUAL_DISCOUNT

                  CREATE TABLE #PRIOR_ACCRUAL_DISCOUNT
                    (
                       PROJECTION_MASTER_SID  INT,
                       PROJECTION_DETAILS_SID INT,
                       PERIOD_SID             INT,
                       RS_CONTRACT_SID        INT,
                       DISCOUNT_AMOUNT        NUMERIC(38, 6),
                       HIERARCHY_NO           VARCHAR(8000),
                       LEVEL_NO               INT,
                       PARENT_HIERARCHY_NO    VARCHAR(8000)
                    );

                  WITH CTE
                       AS (SELECT DISTINCT COMPANY_MASTER_SID,
                                           CONTRACT_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           A.PROJECTION_MASTER_SID,
                                           A.PROJECTION_DETAILS_SID,
                                           P.PERIOD_SID,
                                           PERIOD_DATE,
                                           YEAR,
                                           RS.RS_CONTRACT_SID,
                                           RS.RS_MODEL_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NO,
                                           PARENT_HIERARCHY_NO
                           FROM   #PRIOR_PROJECTIONS A
                                  INNER JOIN PROJECTION_DETAILS PD
                                          ON PD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                             AND PD.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID
                                  INNER JOIN CCP_DETAILS CD
                                          ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                  INNER JOIN NM_DISCOUNT_PROJECTION RS
                                          ON RS.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                  INNER JOIN PERIOD P
                                          ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID)
                  INSERT INTO #PRIOR_ACCRUAL_DISCOUNT
                              (PROJECTION_MASTER_SID,
                               PROJECTION_DETAILS_SID,
                               PERIOD_SID,
                               RS_CONTRACT_SID,
                               DISCOUNT_AMOUNT,
                               HIERARCHY_NO,
                               LEVEL_NO,
                               PARENT_HIERARCHY_NO)
                  SELECT PROJECTION_MASTER_SID,
                         A2.PROJECTION_DETAILS_SID,
                         PERIOD_SID,
                         A2.RS_CONTRACT_SID,
                         Sum(DEDUCTION_AMOUNT) / ( Datediff(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE)
                                                   + 1 ) DISCOUNT_AMOUNT,
                         HIERARCHY_NO,
                         LEVEL_NO,
                         PARENT_HIERARCHY_NO
                  FROM   ACCRUAL_MASTER A1
                         INNER JOIN CTE A2
                                 ON A2.PERIOD_DATE BETWEEN CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_START_DATE, 0)))) AND CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_END_DATE, 0))))
                                    AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID
                                    AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID
                                    AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID
                                    AND A2.RS_MODEL_SID = A1.RS_MODEL_SID
                                    AND Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_START_DATE, -1)) >= @STARTFROM
                                    AND A1.ACCRUAL_PERIOD_END_DATE <= @PROJECTION_DATE
                  GROUP  BY PROJECTION_MASTER_SID,
                            A2.PROJECTION_DETAILS_SID,
                            PERIOD_SID,
                            A2.RS_CONTRACT_SID,
                            ACCRUAL_PERIOD_START_DATE,
                            ACCRUAL_PERIOD_END_DATE,
                            HIERARCHY_NO,
                            LEVEL_NO,
                            PARENT_HIERARCHY_NO

                  IF Object_id('TEMPDB..#PRIOR_DATA_TABLE') IS NOT NULL
                    DROP TABLE #PRIOR_DATA_TABLE

                  SELECT PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         PARENT_HIERARCHY_NO,
                         LEVEL_NO,
                         PERIOD,
                         YEAR,
                         PERIOD_SID,
                         CCP_DETAILS_SID,level_name
                  INTO   #PRIOR_DATA_TABLE
                  FROM   (SELECT DISTINCT HIERARCHY_NO,
                                          LEVEL_NO,
                                          CCP_DETAILS_SID,
                                          COALESCE(PARENT_HIERARCHY_NO, HIERARCHY_NO) PARENT_HIERARCHY_NO,level_name
                          FROM   #CCP) C
                         CROSS JOIN (SELECT DISTINCT PERIOD,
                                                     YEAR,
                                                     PERIOD_SID
                                     FROM   #PERIOD
                                     WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID) P
                         CROSS JOIN #PROJECTION_MASTER PM
                  WHERE  PM.ID <> 1
				  /*
				   SELECT PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         PARENT_HIERARCHY_NO,
                         LEVEL_NO,
                         PERIOD,
                         YEAR,
                         PERIOD_SID,
                         CCP_DETAILS_SID
						 into #PRIOR_DATA_TABLE
						 from #data_table ft cross join #PROJECTION_MASTER PM
                  WHERE  PM.ID <> 1*/

                  IF Object_id('tempdb..#CURRENT_CPP_COMP_PRIOR_CPP') IS NOT NULL
                    DROP TABLE #CURRENT_CPP_COMP_PRIOR_CPP

                  SELECT PM.PROJECTION_MASTER_SID,
                         CC.CCP_DETAILS_SID
                  INTO   #CURRENT_CPP_COMP_PRIOR_CPP
                  FROM   #PROJECTION_MASTER PM
                         INNER JOIN PROJECTION_DETAILS PD
                                 ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                         INNER JOIN CCP_DETAILS CC
                                 ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
                  WHERE  ID = 1
				  

                  INSERT INTO #PIVOT_RESULT
                              (PROJECTION_ID,
                               LEVEL_NO,
                               HIERARCHY_NO,
                               PARENT_HIERARCHY_NO,
                               CP_INDICATOR,
                               PERIOD,
                               YEAR,
                               EX_FACTORY_SALES_ACTUALS,
                               EX_FACTORY_SALES_PROJECTED,
                               DEMAND_SALES_ACTUALS,
                               DEMAND_SALES_PROJECTED,
                               INVENTORY_WITHDRAWAL_SALES_ACTUALS,
                               INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                               EX_FACTORY_SALES_ACTUALS_PERCENT,
                               EX_FACTORY_SALES_PROJECTED_PERCENT,
                               DEMAND_SALES_ACTUALS_PERCENT,
                               DEMAND_SALES_PROJECTED_PERCENT,
                               INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT,
                               INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                               CONTRACT_SALES_ACTUALS,
                               CONTRACT_SALES_PROJECTED,
                               CONTRACT_UNITS_ACTUALS,
                               CONTRACT_UNITS_PROJECTED,
                               TOTAL_DISCOUNT_ACTUALS,
                               TOTAL_DISCOUNT_PROJECTED,
                               TOTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                               TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                               TOTAL_RPU_ACTUALS,
                               TOTAL_RPU_PROJECTED,
                               NET_SALES_ACTUALS,
                               NET_SALES_PROJECTED,
                               COGS_ACTUALS,
                               COGS_PROJECTED,
                               NET_PROFIT_ACTUALS,
                               NET_PROFIT_PROJECTED,
                               NET_SALES_OF_EX_FACTORY_ACTUALS,
                               NET_SALES_OF_EX_FACTORY_PROJECTED,
                               DISCOUNT_OF_EX_FACTORY_ACTUALS,
                               DISCOUNT_OF_EX_FACTORY_PROJECTED,
                               NET_EX_FACTORY_SALES_ACTUALS -----------CEL-386
                               ,
                               NET_EX_FACTORY_SALES_PROJECTED -----------CEL-386
                               ,
                               NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS -----------CEL-386                              
                               ,
                               NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED -----------CEL-386
                               ,
                               TOTAL_DISCOUNT_ACCRUAL)
                  SELECT PDT.PROJECTION_MASTER_SID AS PROJECTION_ID,
                         PDT.LEVEL_NO,
                         PDT.HIERARCHY_NO,
                         PDT.PARENT_HIERARCHY_NO,
                         @CP_INDICATOR,
                         PDT.PERIOD,
                         PDT.YEAR,
                         EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                         EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                         DEMAND_SALES_ACTUALS = Isnull(GTS.DEMAND_SALES_ACTUAL, 0),
                         DEMAND_SALES_PROJECTED = Isnull(GTS.DEMAND_SALES_PROJECTED, 0),
                         INVENTORY_WITHDRAWAL_SALES_ACTUALS = Isnull(GTS.ACT_AMOUNT_WITHDRAWN, 0),
                         INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(GTS.FOR_AMOUNT_WITHDRAWN, 0),
                         EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100,
                         EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                         DEMAND_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                         DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                         INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                         CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                         CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                         CONTRACT_UNITS_ACTUALS = Isnull(CONTRACT_UNITS_ACTUALS, 0),
                         CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                         TOTAL_DISCOUNT_ACTUALS = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                                                                                              WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                                                                                              ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                                                                                            END ), 0),
                         TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                  WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                  ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                END ), 0),
                         TOTAL_DISCOUNT_ACTUALS_PERCENTAGE = ( Isnull(( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                                                                                                             WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                                                                                                             ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                                                                                                           END ) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                                 WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                                 ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                               END ) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                         TOTAL_RPU_ACTUALS = ( Isnull(( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                                                                                             WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                                                                                             ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                                                                                           END ) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_RPU_PROJECTED = ( Isnull(( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                 WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                 ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                               END ) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                         NET_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS - ( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                                                                                                                          WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                                                                                                                          ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                                                                                                                        END ) ), 0),
                         NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED - ( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                                                WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                                                ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                                              END ) ), 0),
                         COGS_ACTUALS = Isnull(SALES.COGS_ACTUALS, 0),
                         COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                         NET_PROFIT_ACTUALS = Isnull(( ( SALES.CONTRACT_SALES_ACTUALS - ( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                                                                                                                               WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                                                                                                                               ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                                                                                                                             END ) ) ) - ( SALES.COGS_ACTUALS ) ), 0),
                         NET_PROFIT_PROJECTED = Isnull(( SALES.CONTRACT_SALES_PROJECTED - ( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                                                   WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                                                   ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                                                 END ) ) ) - ( SALES.COGS_PROJECTED ), 0),
                         NET_SALES_OF_EX_FACTORY_ACTUALS = COALESCE((( SALES.CONTRACT_SALES_ACTUALS - ( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                                                                                                                                             WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                                                                                                                                             ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                                                                                                                                           END ) ) )) / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) * 100,
                         NET_SALES_OF_EX_FACTORY_PROJECTED = COALESCE((( SALES.CONTRACT_SALES_PROJECTED - ( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                                                                   WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                                                                   ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                                                                 END ) ) )) / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) * 100,
                         DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE((( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                                                                                                           WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                                                                                                           ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                                                                                                         END ) )) / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) * 100,
                         DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE((( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                               WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                               ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                             END ) )) / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) * 100,
                         NET_EX_FACTORY_SALES_ACTUALS = Isnull(Isnull(GTS.GTS_SALES_ACTUALS, 0) - ( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                                                                                                                                         WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                                                                                                                                         ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                                                                                                                                       END ) ), 0),
                         NET_EX_FACTORY_SALES_PROJECTED = Isnull(Isnull(GTS.GTS_SALES_PROJECTED, 0) - ( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                                                               WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                                                               ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                                                             END ) ), 0),
                         NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS = Isnull(( Isnull(GTS.GTS_SALES_ACTUALS, 0) - ( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                                                                                                                                                               WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                                                                                                                                                               ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                                                                                                                                                             END ) ) ) / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) * 100,
                         NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED = Isnull(( Isnull(GTS.GTS_SALES_PROJECTED, 0) - ( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                                                                                     WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                                                                                     ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                                                                                   END ) ) ) / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) * 100,
                         ACCRUAL_DISCOUNT          AS TOTAL_DISCOUNT_ACCRUAL
                  FROM   (SELECT DISTINCT HIERARCHY_NO,
                                          PERIOD,
                                          YEAR,
                                          LEVEL_NO,
                                          PARENT_HIERARCHY_NO,
                                          PROJECTION_MASTER_SID
                          FROM   #PRIOR_DATA_TABLE) PDT
                         LEFT JOIN (SELECT ACT_AMOUNT_WITHDRAWN = Sum(INVENTORY_ACTUAL_SALES),
                                           ACT_UNITS_WITHDRAWN = Sum(INVENTORY_ACTUAL_UNITS),
                                           FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                           FOR_UNITS_WITHDRAWN = Sum(INVENTORY_FORECAST_UNITS),
                                           DEMAND_SALES_ACTUAL = Sum(DEMAND_ACTUAL_SALES),
                                           ACT_GROSS_UNITS = Sum(DEMAND_ACTUAL_UNITS),
                                           DEMAND_SALES_PROJECTED = Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                           FOR_GROSS_UNITS = Sum(DEMAND_FORECAST_UNITS),
                                           GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                           GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                           UNITS = Sum(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS)),
                                           A.PERIOD,
                                           A.YEAR,
                                           A.HIERARCHY_NO,
                                           A.LEVEL_NO,
                                           A.PARENT_HIERARCHY_NO,
                                           A.PROJECTION_MASTER_SID
                                    FROM   #PRIOR_DATA_TABLE A
                                           INNER JOIN CCP_DETAILS B
                                                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                           LEFT JOIN #PRODUCT_FILE_TEMP PF
                                                  ON PF.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID
                                                     AND PF.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                                     AND PF.PERIOD_SID = A.PERIOD_SID
                                    GROUP  BY A.PERIOD,
                                              A.YEAR,
                                              A.HIERARCHY_NO,
                                              A.LEVEL_NO,
                                              A.PARENT_HIERARCHY_NO,
                                              A.PROJECTION_MASTER_SID) GTS
                                ON PDT.PROJECTION_MASTER_SID = GTS.PROJECTION_MASTER_SID
                                   AND PDT.[YEAR] = GTS.YEAR
                                   AND PDT.PERIOD = GTS.PERIOD
                                   AND GTS.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND GTS.LEVEL_NO = PDT.LEVEL_NO
                                   AND GTS.PARENT_HIERARCHY_NO = PDT.PARENT_HIERARCHY_NO
                         LEFT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                               OR @SALES_INCLUSION IS NULL ), Isnull(ACTUAL_SALES, 0), NULL)),
                                           CONTRACT_UNITS_ACTUALS = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                               OR @SALES_INCLUSION IS NULL ), Isnull(ACTUAL_UNITS, 0), NULL)),
                                           COGS_ACTUALS = Sum(Isnull(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                            OR @SALES_INCLUSION IS NULL ), Isnull(ACTUAL_UNITS, 0), NULL), 0) * COALESCE(NULLIF(UOM_VALUE, 0), 1) * COALESCE(NULLIF(UOM_VALUE, 0), 1)),
                                           CONTRACT_SALES_PROJECTED = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                                 OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_SALES, 0), NULL)),
                                           CONTRACT_UNITS_PROJECTED = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                                 OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_UNITS, 0), NULL)),
                                           COGS_PROJECTED = Sum(Isnull(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                              OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_UNITS, 0), NULL), 0) * COALESCE(NULLIF(UOM_VALUE, 0), 1) * COALESCE(NULLIF(UOM_VALUE, 0), 1)),
                                           A.PERIOD,
                                           A.[YEAR],
                                           A.HIERARCHY_NO,
                                           A.LEVEL_NO,
                                           A.PARENT_HIERARCHY_NO,
                                           A.PROJECTION_MASTER_SID
                                    FROM   #PRIOR_DATA_TABLE A
                                           INNER JOIN CCP_DETAILS B
                                                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                           INNER JOIN NM_SALES_PROJECTION_MASTER SPM
                                                   ON SPM.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                           INNER JOIN #SALES_INCLUSION SI
                                                   ON SI.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                           LEFT JOIN NM_ACTUAL_SALES NPS
                                                  ON NPS.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                                     AND NPS.PERIOD_SID = A.PERIOD_SID
                                           LEFT JOIN NM_SALES_PROJECTION NP
                                                  ON NP.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                                     AND NP.PERIOD_SID = A.PERIOD_SID
                                           LEFT JOIN #ITEM_UOM_DETAILS UOM
                                                  ON UOM.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                           LEFT JOIN #ITEM_PRICING U
                                                  ON B.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                     AND A.PERIOD_SID = U.PERIOD_SID
                                    GROUP  BY A.PERIOD,
                                              A.[YEAR],
                                              A.HIERARCHY_NO,
                                              A.LEVEL_NO,
                                              A.PARENT_HIERARCHY_NO,
                                              A.PROJECTION_MASTER_SID) SALES
                                ON SALES.PERIOD = PDT.PERIOD
                                   AND SALES.[YEAR] = PDT.YEAR
                                   AND SALES.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND SALES.PARENT_HIERARCHY_NO = PDT.PARENT_HIERARCHY_NO
                                   AND SALES.LEVEL_NO = PDT.LEVEL_NO
                                   AND SALES.PROJECTION_MASTER_SID = PDT.PROJECTION_MASTER_SID
                         LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(Iif(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
                                                                                  OR @DEDUCTION_INCLUSION IS NULL ), Isnull(ACTUAL_SALES, 0), NULL)),
                                           A.PERIOD,
                                           A.[YEAR],
                                           A.HIERARCHY_NO,
                                           A.LEVEL_NO,
                                           Sum(DISCOUNT_AMOUNT) ACCRUAL_DISCOUNT,
                                           A.PARENT_HIERARCHY_NO,
                                           CONTRACT_DISCOUNT_PROJECTED = Sum(Iif(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
                                                                                    OR @DEDUCTION_INCLUSION IS NULL ), Isnull(PROJECTION_SALES, 0), NULL)),
                                           A.PROJECTION_MASTER_SID
                                    FROM   #PRIOR_DATA_TABLE A
                                           INNER JOIN CCP_DETAILS B
                                                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                           INNER JOIN NM_DISCOUNT_PROJ_MASTER NDPM
                                                   ON NDPM.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
												   and iif(a.level_name='Schedule ID',a.HIERARCHY_NO,1)=iif(a.level_name='Schedule ID',NDPM.RS_CONTRACT_SID,1)
                                           INNER JOIN #DEDUCTION_INCLUSION DI
                                                   ON DI.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                                      AND DI.RS_CONTRACT_SID = NDPM.RS_CONTRACT_SID
                                           LEFT JOIN NM_ACTUAL_DISCOUNT NAD
                                                  ON NAD.PROJECTION_DETAILS_SID = NDPM.PROJECTION_DETAILS_SID
                                                     AND NDPM.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID
                                                     AND NAD.PERIOD_SID = A.PERIOD_SID
                                           LEFT JOIN NM_DISCOUNT_PROJECTION NDP
                                                  ON NDPM.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                     AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                                                     AND NDP.PERIOD_SID = A.PERIOD_SID
                                           LEFT JOIN #PRIOR_ACCRUAL_DISCOUNT AD
                                                  ON AD.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID
                                                     AND NDPM.RS_CONTRACT_SID = AD.RS_CONTRACT_SID
                                                     AND A.PERIOD_SID = AD.PERIOD_SID
                                                     AND A.HIERARCHY_NO = AD.HIERARCHY_NO
                                                     AND A.PARENT_HIERARCHY_NO = AD.PARENT_HIERARCHY_NO
                                                     AND A.LEVEL_NO = AD.LEVEL_NO
                                    WHERE  EXISTS (SELECT 1
                                                   FROM   #DISCOUNT_INFO A
                                                   WHERE  CASE
                                                            WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN Cast(NDPM.RS_CONTRACT_SID AS VARCHAR(20))
                                                            ELSE NDPM.PRICE_GROUP_TYPE
                                                          END = A.TOKEN)
                                    GROUP  BY PERIOD,
                                              [YEAR],
                                              A.HIERARCHY_NO,
                                              A.LEVEL_NO,
                                              A.PARENT_HIERARCHY_NO,
                                              A.PROJECTION_MASTER_SID) DISC
                                ON DISC.PERIOD = PDT.PERIOD
                                   AND DISC.[YEAR] = PDT.YEAR
                                   AND DISC.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND DISC.PARENT_HIERARCHY_NO = PDT.PARENT_HIERARCHY_NO
                                   AND DISC.LEVEL_NO = PDT.LEVEL_NO
                                   AND DISC.PROJECTION_MASTER_SID = PDT.PROJECTION_MASTER_SID
                         LEFT JOIN (SELECT PPA_DISCOUNT_PROJECTED = PROJECTION_PPA_SALES,
                                           PPA_PROJECTED_RPU = PPA_RPU,
                                           PROJECTION_SALES AS PPA_PROJECTION_SALES,
                                           PROJECTION_UNITS AS PPA_PROJECTION_UNITS,
                                           PPA_DISCOUNT_ACTUALS = ACTUAL_PPA_SALES,
                                           PPA_ACTUAL_RPU = PPA_RPU,
                                           ACTUAL_SALES     AS PPA_ACTUAL_SALES,
                                           ACTUAL_UNITS     AS PPA_ACTUAL_UNITS,
                                           PERIOD,
                                           YEAR,
                                           HIERARCHY_NO,
                                           PARENT_HIERARCHY_NO,
                                           LEVEL_NO,
                                           PROJECTION_MASTER_SID
                                    FROM   (SELECT PPA_RPU = Sum(ACTUAL_DISCOUNT_DOLLAR),
                                                   ACTUAL_PPA_SALES = Sum(ACTUAL_DISCOUNT_DOLLAR),
                                                   A.PERIOD,
                                                   A.[YEAR],
                                                   A.HIERARCHY_NO,
                                                   A.LEVEL_NO,
                                                   A.PARENT_HIERARCHY_NO,
                                                   ACTUAL_SALES = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                             OR @SALES_INCLUSION IS NULL ), Isnull(NS.ACTUAL_SALES, 0), NULL)),
                                                   ACTUAL_UNITS = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                             OR @SALES_INCLUSION IS NULL ), Isnull(NS.ACTUAL_UNITS, 0), NULL) * COALESCE(NULLIF(UOM_VALUE, 0), 1)),
                                                   PPA_RPU_PROJ = Sum(PROJECTION_DISCOUNT_DOLLAR),
                                                   PROJECTION_PPA_SALES = Sum(PROJECTION_DISCOUNT_DOLLAR),
                                                   PROJECTION_SALES = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                                 OR @SALES_INCLUSION IS NULL ), Isnull(NSP.PROJECTION_SALES, 0), NULL)),
                                                   PROJECTION_UNITS = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                                 OR @SALES_INCLUSION IS NULL ), Isnull(NSP.PROJECTION_UNITS, 0), NULL) * COALESCE(NULLIF(UOM_VALUE, 0), 1)),
                                                   A.PROJECTION_MASTER_SID
                                            FROM   #PRIOR_DATA_TABLE A
                                                   INNER JOIN CCP_DETAILS B
                                                           ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                                   INNER JOIN PROJECTION_DETAILS PD
                                                           ON PD.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                                   INNER JOIN NM_SALES_PROJECTION_MASTER SPM
                                                           ON SPM.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                                   INNER JOIN #SALES_INCLUSION SI
                                                           ON SI.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                                   LEFT JOIN NM_ACTUAL_PPA NPP
                                                          ON SPM.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                             AND NPP.PERIOD_SID = A.PERIOD_SID
                                                   LEFT JOIN NM_PPA_PROJECTION NP
                                                          ON SPM.PROJECTION_DETAILS_SID = NP.PROJECTION_DETAILS_SID
                                                             AND NP.PERIOD_SID = A.PERIOD_SID
                                                   INNER JOIN NM_ACTUAL_SALES NS
                                                           ON NS.PROJECTION_DETAILS_SID = SPM.PROJECTION_DETAILS_SID
                                                              AND NS.PERIOD_SID = A.PERIOD_SID
                                                   LEFT JOIN NM_SALES_PROJECTION NSP
                                                          ON NSP.PROJECTION_DETAILS_SID = SPM.PROJECTION_DETAILS_SID
                                                             AND NSP.PERIOD_SID = A.PERIOD_SID
                                                   LEFT JOIN #ITEM_UOM_DETAILS UOM
                                                          ON UOM.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                            GROUP  BY A.PERIOD,
                                                      A.[YEAR],
                                                      A.HIERARCHY_NO,
                                                      A.LEVEL_NO,
                                                      A.PARENT_HIERARCHY_NO,
                                                      A.PROJECTION_MASTER_SID) A) PPA
                                ON PPA.PERIOD = PDT.PERIOD
                                   AND PPA.[YEAR] = PDT.YEAR
                                   AND PPA.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND PPA.PARENT_HIERARCHY_NO = PDT.PARENT_HIERARCHY_NO
                                   AND PPA.LEVEL_NO = PDT.LEVEL_NO
                                   AND PPA.PROJECTION_MASTER_SID = PDT.PROJECTION_MASTER_SID
              END

            DECLARE @SQL       NVARCHAR(MAX),
                    @LOOP_CNTR INT,
                    @MAX_CCP   INT

            SET @SQL = '

                           SELECT c.LEVEL_NAME,

                   pr.HIERARCHY_NO,

                   CP_INDICATOR,

                   PERIOD,

                   YEAR, '
            SET @LOOP_CNTR = 1
            SET @MAX_CCP = (SELECT Max(ID)
                            FROM   #PROJECTION_MASTER)

            WHILE @LOOP_CNTR <= @MAX_CCP
              BEGIN
                  SET @SQL += 'EX_FACTORY_SALES_ACCRUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN EX_FACTORY_SALES_ACCRUALS END),

                                                         EX_FACTORY_SALES_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN EX_FACTORY_SALES_ACTUALS END),

                                                         EX_FACTORY_SALES_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN EX_FACTORY_SALES_PROJECTED END),0),

                                                         DEMAND_SALES_ACCRUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN DEMAND_SALES_ACCRUALS END),

                                                         DEMAND_SALES_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN DEMAND_SALES_ACTUALS END),

                                                         DEMAND_SALES_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN DEMAND_SALES_PROJECTED END),0),

                                  INVENTORY_WITHDRAWAL_SALES_ACCRUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN INVENTORY_WITHDRAWAL_SALES_ACCRUALS END),

                                  INVENTORY_WITHDRAWAL_SALES_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN INVENTORY_WITHDRAWAL_SALES_ACTUALS END),

                                  INVENTORY_WITHDRAWAL_SALES_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED END),0),

                                                         EX_FACTORY_SALES_ACCRUAL_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN EX_FACTORY_SALES_ACCRUAL_PERCENT END),

                                                         EX_FACTORY_SALES_ACTUALS_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN EX_FACTORY_SALES_ACTUALS_PERCENT END),

                                                         EX_FACTORY_SALES_PROJECTED_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN EX_FACTORY_SALES_PROJECTED_PERCENT END),0),

                                                         DEMAND_SALES_ACCRUAL_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN DEMAND_SALES_ACCRUAL_PERCENT END),

                                                         DEMAND_SALES_ACTUALS_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN DEMAND_SALES_ACTUALS_PERCENT END),

                                                         DEMAND_SALES_PROJECTED_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN DEMAND_SALES_PROJECTED_PERCENT END),0),

                                                         INVENTORY_WITHDRAWAL_SALES_ACCRUAL_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN INVENTORY_WITHDRAWAL_SALES_ACCRUAL_PERCENT END),

                                                         INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT END),

                                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT END),0),

                                                         CONTRACT_SALES_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN CONTRACT_SALES_ACCRUAL END),

                                                         CONTRACT_SALES_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN CONTRACT_SALES_ACTUALS END),

                                                         CONTRACT_SALES_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN CONTRACT_SALES_PROJECTED END),0),

                                                         CONTRACT_UNITS_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN CONTRACT_UNITS_ACCRUAL END),

                                                         CONTRACT_UNITS_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN CONTRACT_UNITS_ACTUALS END),

                                                         CONTRACT_UNITS_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN CONTRACT_UNITS_PROJECTED END),0),

                                                         TOTAL_DISCOUNT_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN IIF(TOTAL_DISCOUNT_ACCRUAL=0,NULL,TOTAL_DISCOUNT_ACCRUAL) END),

                                                         TOTAL_DISCOUNT_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_DISCOUNT_ACTUALS END),

                                                         TOTAL_DISCOUNT_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_DISCOUNT_PROJECTED END),0),

                                                         TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE END),

                                                         TOTAL_DISCOUNT_ACTUALS_PERCENTAGE_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_DISCOUNT_ACTUALS_PERCENTAGE END),

                                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),

                                                         TOTAL_RPU_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_RPU_ACCRUAL END),

                                                         TOTAL_RPU_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_RPU_ACTUALS END),

                                                         TOTAL_RPU_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_RPU_PROJECTED END),0),

                                                         NET_SALES_ACCRUAL_'
                              + Cast( @LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_SALES_ACCRUAL END),

                                                         NET_SALES_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_SALES_ACTUALS END),

                                                         NET_SALES_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_SALES_PROJECTED END),0),

                                                         COGS_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN COGS_ACCRUAL END),

                                                         COGS_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN COGS_ACTUALS END),

                                                         COGS_PROJECTED_'
                              + Cast( @LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN COGS_PROJECTED END),0),

                                                         NET_PROFIT_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_PROFIT_ACCRUAL END),

                                                         NET_PROFIT_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_PROFIT_ACTUALS END),

                                                         NET_PROFIT_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_PROFIT_PROJECTED END),0),

                                                         NET_SALES_OF_EX_FACTORY_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_SALES_OF_EX_FACTORY_ACCRUAL END),

                                                         NET_SALES_OF_EX_FACTORY_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_SALES_OF_EX_FACTORY_ACTUALS END),

                                                         NET_SALES_OF_EX_FACTORY_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN  NET_SALES_OF_EX_FACTORY_PROJECTED END),0),

                                                         DISCOUNT_OF_EX_FACTORY_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(20))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(20))
                              + ' THEN DISCOUNT_OF_EX_FACTORY_ACCRUAL END),

                                                         DISCOUNT_OF_EX_FACTORY_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(20))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(20))
                              + ' THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),

                                                         DISCOUNT_OF_EX_FACTORY_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(20))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(20))
                              + ' THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0), 

                                                         NET_EX_FACTORY_SALES_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_EX_FACTORY_SALES_ACCRUAL END),

                                                         NET_EX_FACTORY_SALES_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_EX_FACTORY_SALES_ACTUALS END),

                                                         NET_EX_FACTORY_SALES_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_EX_FACTORY_SALES_PROJECTED END),

                                                         NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(MAX))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(MAX))
                              + ' THEN NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACCRUAL END),

                                                         NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(MAX))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(MAX))
                              + ' THEN NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS END),

                                                         NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(MAX))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(MAX))
                              + ' THEN NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED END),

                                  '
                  SET @LOOP_CNTR += 1
              END

            --SET @SQL = LEFT(@SQL, Len(@SQL) - 1)
            SET @SQL += ' C.PARENT_HIERARCHY_NO FROM   #PIVOT_RESULT PR JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID  = PR.PROJECTION_ID

                       JOIN #CCP C ON PR.HIERARCHY_NO = C.HIERARCHY_NO
AND PR.LEVEL_NO=c.LEVEL_NO
                                     AND PR.PARENT_HIERARCHY_NO=COALESCE(C.PARENT_HIERARCHY_NO,C.HIERARCHY_NO)


                GROUP  BY   C.LEVEL_NO,c.LEVEL_NAME,PR.HIERARCHY_NO,CP_INDICATOR,PERIOD,YEAR,C.PARENT_HIERARCHY_NO                              

                ORDER  BY C.LEVEL_NO, PR.HIERARCHY_NO,C.PARENT_HIERARCHY_NO,[YEAR],PERIOD                   

                           '

            EXEC Sp_executesql
              @SQL
        END
      ELSE
        BEGIN
            IF Object_id('TEMPDB..#RS_COMBINATION') IS NOT NULL
              DROP TABLE #RS_COMBINATION

            CREATE TABLE #RS_COMBINATION
              (
                 HIERARCHY_NO        VARCHAR(100),
                 LEVEL_NAME VARCHAR(100),
                 CCP_DETAILS_SID     INT,
                 RS_CONTRACT_SID     INT,
                 ITEM_MASTER_SID     INT,
                 PARENT_HIERARCHY_NO VARCHAR(8000),
                 DISCOUNT            VARCHAR(100),
                 DEDUCTION_INCLUSION BIT,
                 SALES_INCLUSION     BIT,--FILTER_CCP bit,
                 LEVEL_NO            INT,
                 SELECTED_SID        VARCHAR(50)
              )

            SET @SQL = 'INSERT INTO #RS_COMBINATION (

       CCP_DETAILS_SID

       ,RS_CONTRACT_SID

       ,ITEM_MASTER_SID

       ,HIERARCHY_NO

       ,LEVEL_NAME

       ,PARENT_HIERARCHY_NO

       ,DISCOUNT

       ,DEDUCTION_INCLUSION,SALES_INCLUSION--,FILTER_CCP

       ,LEVEL_NO

       ,SELECTED_SID

       )

SELECT DISTINCT S.CCP_DETAILS_SID

       ,S.RS_CONTRACT_SID

       ,CD.ITEM_MASTER_SID

       ,C.HIERARCHY_NO

       ,C.LEVEL_NAME

       --,COALESCE(C.PARENT_HIERARCHY_NO, C.HIERARCHY_NO) PARENT_HIERARCHY_NO

       ,C.PARENT_HIERARCHY_NO

       ,SELECTED_LEVEL AS DISCOUNT

       ,DEDUCTION_INCLUSION

       ,SALES_INCLUSION--,FILTER_CCP

       ,c.LEVEL_NO

       ,SELECTED_SID

FROM #MULTISELECT_DISCOUNTS S

JOIN #CCP C ON C.CCP_DETAILS_SID = S.CCP_DETAILS_SID and iif(c.LEVEL_NAME=''Schedule ID'',c.HIERARCHY_NO,1)=iif(c.LEVEL_NAME=''Schedule ID'',s.rs_contract_Sid,1)

JOIN #CCP_DETAILS_TEMP CD ON C.CCP_DETAILS_SID = CD.CCP_DETAILS_SID

/*
UNION ALL

SELECT DISTINCT S.CCP_DETAILS_SID

       ,S.RS_CONTRACT_SID

       ,CD.ITEM_MASTER_SID

       ,C.HIERARCHY_NO

       ,C.LEVEL_NAME

       ,COALESCE(C.PARENT_HIERARCHY_NO, C.PARENT_HIERARCHY_NO) PARENT_HIERARCHY_NO

       ,D.DISCOUNT

       ,NULL AS DEDUCTION_INCLUSION

       ,NULL AS SELECTED_SID

FROM ' + @P_MASTER_TABLE
                       + ' S

JOIN RS_CONTRACT r ON R.RS_CONTRACT_SID = S.RS_CONTRACT_SID

JOIN HELPER_TABLE h ON h.HELPER_TABLE_SID = r.REBATE_PROGRAM_TYPE

JOIN #DISCOUNT_INFO D ON D.TOKEN = CASE

              WHEN @DISCOUNT_LEVEL = '' PROGRAM ''

                     THEN CAST(S.RS_CONTRACT_SID AS VARCHAR(100))

              ELSE CAST(h.DESCRIPTION AS VARCHAR(100))

              END

JOIN #CCP C ON C.CCP_DETAILS_SID = S.CCP_DETAILS_SID

JOIN #CCP_DETAILS_TEMP CD ON C.CCP_DETAILS_SID = CD.CCP_DETAILS_SID*/'
--SELECT * FROM #RS_COMBINATION WHERE HIERARCHY_NO='844-58.'
            EXEC Sp_executesql
              @SQL,
              N'@DISCOUNT_LEVEL VARCHAR(50)',
              @DISCOUNT_LEVEL = @DISCOUNT_LEVEL

            IF Object_id('TEMPDB..#FILE_DATA') IS NOT NULL
              DROP TABLE #FILE_DATA

            CREATE TABLE #FILE_DATA
              (
                 PERIOD              INT,
                 YEAR                INT,
                 RS_CONTRACT_SID     INT,
                 GTS_SALES_ACTUALS   NUMERIC(38, 6),
                 GTS_SALES_PROJECTED NUMERIC(38, 6),
                 HIERARCHY_NO        VARCHAR(100),
                 LEVEL_NO            INT,
                 DISCOUNT            VARCHAR(100),
                 PARENT_HIERARCHY_NO VARCHAR(8000)
              )

            SET @SQL = 'INSERT INTO #FILE_DATA (

       PERIOD

       ,YEAR

       ,RS_CONTRACT_SID

       ,GTS_SALES_ACTUALS

       ,GTS_SALES_PROJECTED

       ,HIERARCHY_NO

       ,LEVEL_NO

       ,DISCOUNT

       ,PARENT_HIERARCHY_NO

       )

       SELECT P.PERIOD

       ,P.YEAR

       ,RS_CONTRACT_SID

       ,GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES)

       ,GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES))

       ,HIERARCHY_NO

       ,LEVEL_NO

       ,DISCOUNT

       ,PARENT_HIERARCHY_NO FROM '
                       + @PRODUCT_TABLE
                       + ' PF JOIN #PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID INNER JOIN (

       SELECT DISTINCT HIERARCHY_NO

              ,DISCOUNT

              ,ITEM_MASTER_SID

              ,LEVEL_NO

              ,RS_CONTRACT_SID

              ,PARENT_HIERARCHY_NO

       FROM #RS_COMBINATION

       ) CI ON PF.ITEM_MASTER_SID = CI.ITEM_MASTER_SID GROUP BY P.PERIOD

       ,P.YEAR

       ,RS_CONTRACT_SID

       ,DISCOUNT

       ,HIERARCHY_NO

       ,LEVEL_NO

       ,PARENT_HIERARCHY_NO'

            EXEC Sp_executesql
              @SQL

            IF Object_id('TEMPDB..#DISCOUNT_DATA_TABLE') IS NOT NULL
              DROP TABLE #DISCOUNT_DATA_TABLE

            SELECT C.HIERARCHY_NO,
                   PERIOD,
                   YEAR,
                   --CASE 
                   --  WHEN @CP_INDICATOR = 'D'
                   --  AND @SCREEN_NAME = 'DISCOUNT'
                   --  THEN ''
                   --  ELSE DISCOUNT
                   --  END DISCOUNT,
                   DISCOUNT,
                   C.PARENT_HIERARCHY_NO,
                   SELECTED_SID,
                   C1.LEVEL_NO
            INTO   #DISCOUNT_DATA_TABLE
            FROM   (SELECT DISTINCT HIERARCHY_NO,
                                    LEVEL_NO,
                                    PARENT_HIERARCHY_NO
                    FROM   #CCP) C
                   CROSS JOIN (SELECT DISTINCT PERIOD,
                                               YEAR
                               FROM   #PERIOD
                               WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID) P
                   CROSS JOIN (SELECT DISCOUNT,
                                      SELECTED_SID
                               FROM   #DISCOUNT_INFO) D
                   INNER JOIN #CCP C1
                           ON C.HIERARCHY_NO = C1.HIERARCHY_NO
                              AND ( C.PARENT_HIERARCHY_NO = C1.PARENT_HIERARCHY_NO
                                     OR C.LEVEL_NO = C1.LEVEL_NO )
                              AND C.LEVEL_NO = C1.LEVEL_NO
            WHERE  EXISTS (SELECT 1
                           FROM   #MULTISELECT_DISCOUNTS M
                           WHERE  M.CCP_DETAILS_SID = C1.CCP_DETAILS_SID
                                  AND M.SELECTED_LEVEL = d.DISCOUNT)
            GROUP  BY C.HIERARCHY_NO,
                      C.LEVEL_NO,
                      PERIOD,
                      YEAR,
                      --CASE 
                      --  WHEN @CP_INDICATOR = 'D'
                      --  AND @SCREEN_NAME = 'DISCOUNT'
                      --  THEN ''
                      --  ELSE DISCOUNT
                      --  END,
                      DISCOUNT,
                      C.PARENT_HIERARCHY_NO,
                      SELECTED_SID,
                      C1.LEVEL_NO					 

            IF Object_id('TEMPDB..#SALES_ACTUAL_DATA') IS NOT NULL
              DROP TABLE #SALES_ACTUAL_DATA

            CREATE TABLE #SALES_ACTUAL_DATA
              (
                 CONTRACT_SALES_ACTUALS NUMERIC(38, 6),
                 CONTRACT_UNITS_ACTUALS NUMERIC(38, 6),
                 PERIOD                 VARCHAR(50),
                 [YEAR]                 INT,
                 HIERARCHY_NO           VARCHAR(100),
                 PARENT_HIERARCHY_NO    VARCHAR(8000),
                 LEVEL_NO               INT,
                 --     LEVEL_NO INT,
                 DISCOUNT               VARCHAR(100)
              )

            SET @D_SQL = ' INSERT INTO #SALES_ACTUAL_DATA(

                     CONTRACT_SALES_ACTUALS ,

                     CONTRACT_UNITS_ACTUALS,

                     PERIOD  ,               

                     [YEAR],                 

                     HIERARCHY_NO,            

                     LEVEL_NO ,DISCOUNT ,PARENT_HIERARCHY_NO-- ,level_no          

                     )

            SELECT CONTRACT_SALES_ACTUALS = SUM(IIF((SALES_INCLUSION=CONVERT (CHAR(1),@SALES_INCLUSION) OR @SALES_INCLUSION  IS NULL),ISNULL(ACTUAL_SALES,0),NULL) ),

                   CONTRACT_UNITS_ACTUALS = SUM(IIF((SALES_INCLUSION=CONVERT (CHAR(1),@SALES_INCLUSION) OR @SALES_INCLUSION  IS NULL),ISNULL(ACTUAL_UNITS,0),NULL) * COALESCE(NULLIF(UOM_VALUE, 0),1)),

                   PERIOD,

                   [YEAR],

                   HIERARCHY_NO,

                   level_no,

                            --  CASE WHEN @CP_INDICATOR=''D'' AND  @SCREEN_NAME = ''DISCOUNT''  THEN ''''  ELSE   DISCOUNT END DISCOUNT,
DISCOUNT,

                              PARENT_HIERARCHY_NO--,level_no

          

            FROM   ' + @S_ACTUAL_TABLE
                         + ' NPS

                   INNER JOIN (SELECT DISTINCT  HIERARCHY_NO,CCP_DETAILS_SID,ITEM_MASTER_SID,

                   level_no,PARENT_HIERARCHY_NO,SALES_INCLUSION,

                              DISCOUNT  FROM #RS_COMBINATION) RS ON NPS.CCP_DETAILS_SID=RS.CCP_DETAILS_SID

                   INNER JOIN #PERIOD P ON P.PERIOD_SID = NPS.PERIOD_SID

                              --INNER JOIN '
                         + @S_MASTER_TABLE
                         + ' SMT ON SMT.CCP_DETAILS_SID=NPS.CCP_DETAILS_SID

                              --INNER JOIN CCP_DETAILS CD ON CD.CCP_DETAILS_SID =NPS.CCP_DETAILS_SID

                              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=rs.ITEM_MASTER_SID

            GROUP  BY PERIOD,

                      [YEAR],

                      DISCOUNT,

                      HIERARCHY_NO,

                      level_no,PARENT_HIERARCHY_NO'

            EXEC Sp_executesql
              @D_SQL,
              N'@SALES_INCLUSION BIT,@CP_INDICATOR CHAR(10),@SCREEN_NAME VARCHAR(50)',
              @SALES_INCLUSION = @SALES_INCLUSION,
              @CP_INDICATOR = @CP_INDICATOR,
              @SCREEN_NAME = @SCREEN_NAME

            IF Object_id('TEMPDB..#SALES_DATA') IS NOT NULL
              DROP TABLE #SALES_DATA

            CREATE TABLE #SALES_DATA
              (
                 CONTRACT_SALES_PROJECTED NUMERIC(38, 6),
                 CONTRACT_UNITS_PROJECTED NUMERIC(38, 6),
                 PERIOD                   VARCHAR(50),
                 [YEAR]                   INT,
                 HIERARCHY_NO             VARCHAR(8000),
                 LEVEL_NO                 INT,
                 DISCOUNT                 VARCHAR(100),
                 PARENT_HIERARCHY_NO      VARCHAR(8000)
              )

            SET @D_SQL = 'INSERT INTO #SALES_DATA(

                     CONTRACT_SALES_PROJECTED ,

                     CONTRACT_UNITS_PROJECTED,

                     PERIOD  ,               

                     [YEAR],                 

                     HIERARCHY_NO,            

                     LEVEL_NO   

                                   ,DISCOUNT

                                   ,PARENT_HIERARCHY_NO  -- ,level_no      

                     )

            SELECT CONTRACT_SALES_PROJECTED = SUM( IIF((SALES_INCLUSION=CONVERT (CHAR(1),@SALES_INCLUSION) OR @SALES_INCLUSION   IS NULL),ISNULL(PROJECTION_SALES,0),NULL)),

                   CONTRACT_UNITS_PROJECTED = SUM( IIF((SALES_INCLUSION=CONVERT (CHAR(1),@SALES_INCLUSION) OR @SALES_INCLUSION  IS NULL),ISNULL(PROJECTION_UNITS,0),NULL)  * COALESCE(NULLIF(UOM_VALUE, 0),1)),

                   PERIOD,

                   [YEAR],

                   HIERARCHY_NO,

                   LEVEL_NO

                             -- ,CASE WHEN @CP_INDICATOR=''D'' AND  @SCREEN_NAME = ''DISCOUNT''  THEN ''''  ELSE   DISCOUNT END 
,DISCOUNT

                              ,PARENT_HIERARCHY_NO--,level_no

          

            FROM   ' + @S_PROJECTION_TABLE
                         + ' NPS

                   INNER JOIN (SELECT DISTINCT  HIERARCHY_NO,CCP_DETAILS_SID,ITEM_MASTER_SID,

                   LEVEL_NO,SALES_INCLUSION,

 

                              DISCOUNT,PARENT_HIERARCHY_NO  FROM #RS_COMBINATION) RS ON NPS.CCP_DETAILS_SID=RS.CCP_DETAILS_SID

                   INNER JOIN #PERIOD P ON P.PERIOD_SID = NPS.PERIOD_SID

                              --INNER JOIN '
                         + @S_MASTER_TABLE
                         + ' SMT ON SMT.CCP_DETAILS_SID=NPS.CCP_DETAILS_SID

                              --INNER JOIN CCP_DETAILS CD ON CD.CCP_DETAILS_SID =NPS.CCP_DETAILS_SID

                              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=RS.ITEM_MASTER_SID

            GROUP  BY PERIOD,

                      [YEAR]

                                    ,DISCOUNT,

                      HIERARCHY_NO,

                      LEVEL_NO,PARENT_HIERARCHY_NO'

            EXEC Sp_executesql
              @D_SQL,
              N'@SALES_INCLUSION BIT,@CP_INDICATOR CHAR(10),@SCREEN_NAME VARCHAR(50)',
              @SALES_INCLUSION = @SALES_INCLUSION,
              @CP_INDICATOR = @CP_INDICATOR,
              @SCREEN_NAME = @SCREEN_NAME

            IF Object_id('TEMPDB..#ACCRUAL_DISCOUNT1') IS NOT NULL
              DROP TABLE #ACCRUAL_DISCOUNT1;

            CREATE TABLE #ACCRUAL_DISCOUNT1
              (
                 CCP_DETAILS_SID     INT,
                 PERIOD_SID          INT,
                 RS_CONTRACT_SID     INT,
                 DISCOUNT_AMOUNT     NUMERIC(38, 6),
                 PARENT_HIERARCHY_NO VARCHAR(8000),
                 HIERARCHY_NO        VARCHAR(100),
                 LEVEL_NO            INT
              )

            SET @SQL_ACC = ' ;

 

WITH CTE

AS (

       SELECT DISTINCT COMPANY_MASTER_SID

              ,CONTRACT_MASTER_SID

              ,CD.ITEM_MASTER_SID

              ,A.CCP_DETAILS_SID

              ,PERIOD_SID

              ,PERIOD_DATE

              ,YEAR

              ,RS.RS_CONTRACT_SID

              ,RS.RS_MODEL_SID

              ,A.PARENT_HIERARCHY_NO

              ,A.HIERARCHY_NO

              ,A.LEVEL_NO

       FROM #RS_COMBINATION A

       JOIN #CCP_DETAILS_TEMP CD ON CD.CCP_DETAILS_SID=A.CCP_DETAILS_SID

       JOIN #MULTISELECT_DISCOUNTS RS ON RS.CCP_DETAILS_SID = A.CCP_DETAILS_SID

       AND RS.SELECTED_LEVEL=A.DISCOUNT

       JOIN #PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID

                     AND @END_PERIOD_SID

                    

       )

INSERT INTO #ACCRUAL_DISCOUNT1 (

       CCP_DETAILS_SID

       ,PERIOD_SID

       ,RS_CONTRACT_SID

       ,DISCOUNT_AMOUNT

       ,HIERARCHY_NO

       ,PARENT_HIERARCHY_NO

       ,LEVEL_NO

       )

SELECT A2.CCP_DETAILS_SID

       ,PERIOD_SID

       ,A2.RS_CONTRACT_SID

       ,SUM(DEDUCTION_AMOUNT) / (DATEDIFF(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE) + 1) DISCOUNT_AMOUNT

       ,HIERARCHY_NO

       ,PARENT_HIERARCHY_NO

       ,LEVEL_NO

FROM ACCRUAL_MASTER A1

JOIN CTE A2 ON A2.PERIOD_DATE BETWEEN CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_START_DATE, 0))))

              AND CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_END_DATE, 0))))

       AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID

       AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID

       AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID

       AND A2.RS_MODEL_SID = A1.RS_MODEL_SID

       AND DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_START_DATE, - 1)) >= @STARTFROM

       AND A1.ACCRUAL_PERIOD_END_DATE <= @PROJECTION_DATE

GROUP BY A2.CCP_DETAILS_SID

       ,PERIOD_SID

       ,A2.RS_CONTRACT_SID

       ,ACCRUAL_PERIOD_START_DATE

       ,ACCRUAL_PERIOD_END_DATE

       ,HIERARCHY_NO

       ,PARENT_HIERARCHY_NO

       ,LEVEL_NO

'

            EXEC Sp_executesql
              @SQL_ACC,
              N'@START_PERIOD_SID INT,@END_PERIOD_SID INT,@STARTFROM DATETIME,@PROJECTION_DATE DATETIME',
              @START_PERIOD_SID = @START_PERIOD_SID,
              @END_PERIOD_SID = @END_PERIOD_SID,
              @STARTFROM = @STARTFROM,
              @PROJECTION_DATE = @PROJECTION_DATE

            IF Object_id('TEMPDB..#DISCOUNT_ACTUAL_DATA') IS NOT NULL
              DROP TABLE #DISCOUNT_ACTUAL_DATA

            CREATE TABLE #DISCOUNT_ACTUAL_DATA
              (
                 CONTRACT_SALES_ACTUALS  NUMERIC(38, 6),
                 DISCOUNT                VARCHAR(50),
                 HIERARCHY_NO            VARCHAR(100),
                 LEVEL_NO                INT,
                 PERIOD                  VARCHAR(50),
                 ACCRUAL_DISCOUNT_ACTUAL NUMERIC(38, 6),
                 [YEAR]                  INT,
                 PARENT_HIERARCHY_NO     VARCHAR(8000),
                 SELECTED_SID            VARCHAR(50)
              )
			  
            SET @D_SQL = Concat (' INSERT INTO #DISCOUNT_ACTUAL_DATA (

       CONTRACT_SALES_ACTUALS

       ,DISCOUNT

       ,HIERARCHY_NO

       ,LEVEL_NO

       ,PERIOD

       ,[YEAR]

       ,ACCRUAL_DISCOUNT_ACTUAL

       ,PARENT_HIERARCHY_NO

       ,SELECTED_SID

       )

SELECT DISTINCT CONTRACT_SALES_ACTUALS = SUM(IIF(( rs.DEDUCTION_INCLUSION = CONVERT(CHAR(1), @DEDUCTION_INCLUSION) OR @DEDUCTION_INCLUSION IS NULL ), ISNULL(ACTUAL_SALES,0), NULL))

       ,RS.DISCOUNT DISCOUNT

       --,CASE WHEN @CP_INDICATOR=''D'' AND  @SCREEN_NAME = ''DISCOUNT''  THEN ''''  ELSE RS.DISCOUNT END

       ,RS.HIERARCHY_NO

       ,RS.LEVEL_NO

       ,P.PERIOD

       ,P.YEAR

       ,SUM(ISNULL(DISCOUNT_AMOUNT, 0)) ACCRUAL_DISCOUNT_ACTUAL

       ,RS.PARENT_HIERARCHY_NO

       ,rs.SELECTED_SID

 

FROM ' + @D_ACTUAL_TABLE + ' NDP

       JOIN ', @D_MASTER_TABLE, ' MSD ON MSD.CCP_DETAILS_SID=NDP.CCP_DETAILS_SID

and msd.rs_contract_sid=NDP.rs_contract_sid

---AND FILTER_CCP=1
AND iif(@SCREEN_NAME = ''DISCOUNT'',FILTER_CCP,iif(@SCREEN_NAME is null,PV_FILTERS,1))=1

INNER JOIN #RS_COMBINATION RS ON MSD.CCP_DETAILS_SID = RS.CCP_DETAILS_SID

       AND MSD.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
	   
	   AND IIF(RS.LEVEL_NAME=''SCHEDULE ID'',RS.HIERARCHY_NO,1)=IIF(RS.LEVEL_NAME=''SCHEDULE ID'',MSD.RS_CONTRACT_SID,1)
INNER JOIN #PERIOD P ON P.PERIOD_SID = NDP.PERIOD_SID

LEFT JOIN #ACCRUAL_DISCOUNT1 AD ON AD.CCP_DETAILS_SID = NDP.CCP_DETAILS_SID

       AND AD.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID

       AND AD.PERIOD_SID = NDP.PERIOD_SID

       AND AD.HIERARCHY_NO = RS.HIERARCHY_NO

       AND AD.LEVEL_NO = RS.LEVEL_NO

       AND RS.PARENT_HIERARCHY_NO = AD.PARENT_HIERARCHY_NO

       ', CASE
                                     WHEN @DEDUCTION_INCLUSION IS NOT NULL THEN Concat ('AND rs.DEDUCTION_INCLUSION =', @DEDUCTION_INCLUSION)
                                   END, '

where exists (select 1 from #MULTISELECT_DISCOUNTS md  where md.ccp_details_sid=MSD.CCP_DETAILS_SID 
and md.RS_CONTRACT_SID=MSD.RS_CONTRACT_SID
and md.SELECTED_SID=RS.SELECTED_SID)

GROUP BY
RS.DISCOUNT

    --   CASE WHEN @CP_INDICATOR=''D'' AND  @SCREEN_NAME = ''DISCOUNT''  THEN ''''  ELSE RS.DISCOUNT END

       ,RS.HIERARCHY_NO

       ,RS.LEVEL_NO

       ,P.PERIOD

       ,P.YEAR

       ,RS.PARENT_HIERARCHY_NO

       ,rs.SELECTED_SID

', '')

            EXEC Sp_executesql
              @D_SQL,
              N'@DEDUCTION_INCLUSION BIT,@CP_INDICATOR CHAR(10),@SCREEN_NAME VARCHAR(50) ',
              @DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION,
              @CP_INDICATOR = @CP_INDICATOR,
              @SCREEN_NAME = @SCREEN_NAME

			
            IF Object_id('TEMPDB..#DISCOUNT_DATA') IS NOT NULL
              DROP TABLE #DISCOUNT_DATA

            CREATE TABLE #DISCOUNT_DATA
              (
                 CONTRACT_SALES_PROJECTED NUMERIC(38, 6),
                 DISCOUNT                 VARCHAR(50),
                 HIERARCHY_NO             VARCHAR(8000),
                 LEVEL_NO                 INT,
                 PERIOD                   VARCHAR(50),
                 [YEAR]                   INT,
                 ACCRUAL_DISCOUNT_PROJ    NUMERIC(38, 6),
                 PARENT_HIERARCHY_NO      VARCHAR(8000),
                 SELECTED_SID             VARCHAR(50),
                 DEDUCTION_INCLUSION      INT,
                 GROWTH                   NUMERIC(38, 6),
                 USER_GROUP               VARCHAR(50)
              )

            SET @D_SQL = Concat (' INSERT INTO #DISCOUNT_DATA (

       CONTRACT_SALES_PROJECTED

       ,DISCOUNT

       ,HIERARCHY_NO

       ,LEVEL_NO

       ,PERIOD

       ,[YEAR]

       ,ACCRUAL_DISCOUNT_PROJ

       ,PARENT_HIERARCHY_NO

       ,SELECTED_SID

       ,DEDUCTION_INCLUSION

       ,GROWTH

       ,USER_GROUP

       )

SELECT CONTRACT_SALES_PROJECTED = SUM(IIF((

                           rs.DEDUCTION_INCLUSION = CONVERT(CHAR(1), @DEDUCTION_INCLUSION)

                           OR @DEDUCTION_INCLUSION IS NULL

                           ), ISNULL(PROJECTION_SALES,0), NULL))

       ,RS.DISCOUNT DISCOUNT

      -- ,CASE WHEN @CP_INDICATOR=''D'' AND  @SCREEN_NAME = ''DISCOUNT''  THEN ''''  ELSE RS.DISCOUNT END

       ,RS.HIERARCHY_NO

       ,RS.LEVEL_NO

       ,P.PERIOD

       ,P.YEAR

       ,SUM(ISNULL(DISCOUNT_AMOUNT, 0)) ACCRUAL_DISCOUNT_PROJ

       ,RS.PARENT_HIERARCHY_NO

       ,rs.SELECTED_SID

       ,rs.DEDUCTION_INCLUSION

       ,AVG(GROWTH) GROWTH

       ,MAX(USER_GROUP)USER_GROUP

FROM ', @D_PROJECTION_TABLE, ' NDP

JOIN ', @D_MASTER_TABLE, ' MSD ON MSD.CCP_DETAILS_SID=NDP.CCP_DETAILS_SID

and msd.rs_contract_sid=ndp.rs_contract_sid

--AND FILTER_CCP=1
AND iif(@SCREEN_NAME = ''DISCOUNT'',FILTER_CCP,iif(@SCREEN_NAME is null,PV_FILTERS,1))=1

INNER JOIN #RS_COMBINATION RS ON MSD.CCP_DETAILS_SID = RS.CCP_DETAILS_SID

       AND MSD.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
	    AND IIF(RS.LEVEL_NAME=''SCHEDULE ID'',RS.HIERARCHY_NO,1)=IIF(RS.LEVEL_NAME=''SCHEDULE ID'',MSD.RS_CONTRACT_SID,1)
INNER JOIN #PERIOD P ON P.PERIOD_SID = NDP.PERIOD_SID

LEFT JOIN #ACCRUAL_DISCOUNT1 AD ON AD.CCP_DETAILS_SID = NDP.CCP_DETAILS_SID

       AND AD.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID

       AND AD.PERIOD_SID = NDP.PERIOD_SID

       AND AD.HIERARCHY_NO = RS.HIERARCHY_NO

       AND AD.PARENT_HIERARCHY_NO = RS.PARENT_HIERARCHY_NO

       AND AD.LEVEL_NO = RS.LEVEL_NO

', CASE
                              WHEN @DEDUCTION_INCLUSION IS NOT NULL THEN Concat ('AND rs.DEDUCTION_INCLUSION =', @DEDUCTION_INCLUSION)
                            END, '

where exists (select 1 from #MULTISELECT_DISCOUNTS md  where md.ccp_details_sid=MSD.CCP_DETAILS_SID 
and md.RS_CONTRACT_SID=MSD.RS_CONTRACT_SID)

GROUP BY

RS.DISCOUNT

      -- CASE WHEN @CP_INDICATOR=''D'' AND  @SCREEN_NAME = ''DISCOUNT''  THEN ''''  ELSE RS.DISCOUNT END

       ,RS.HIERARCHY_NO

       ,RS.LEVEL_NO

       ,P.PERIOD

       ,P.YEAR

       ,RS.PARENT_HIERARCHY_NO,RS.SELECTED_SID ,RS.DEDUCTION_INCLUSION', '')

            EXEC Sp_executesql
              @D_SQL,
              N'@DEDUCTION_INCLUSION BIT,@CP_INDICATOR CHAR(10),@SCREEN_NAME VARCHAR(50) ',
              @DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION,
              @CP_INDICATOR = @CP_INDICATOR,
              @SCREEN_NAME = @SCREEN_NAME

            IF Object_id('TEMPDB..#PPA_ACTUAL_DATA') IS NOT NULL
              DROP TABLE #PPA_ACTUAL_DATA

            CREATE TABLE #PPA_ACTUAL_DATA
              (
                 CONTRACT_SALES_ACTUALS NUMERIC(38, 6),
                 DISCOUNT               VARCHAR(50),
                 HIERARCHY_NO           VARCHAR(8000),
                 LEVEL_NAME             VARCHAR(100),
                 PERIOD                 VARCHAR(50),
                 [YEAR]                 INT,
                 PARENT_HIERARCHY_NO    VARCHAR(8000)
              )

            /*SET @D_SQL = 'INSERT INTO #PPA_ACTUAL_DATA (
            
                   CONTRACT_SALES_ACTUALS
            
                   ,DISCOUNT
            
                   ,HIERARCHY_NO
            
                   ,LEVEL_NAME
            
                   ,PERIOD
            
                   ,[YEAR]
            
                   ,PARENT_HIERARCHY_NO
            
                   )
            
            SELECT CONTRACT_SALES_ACTUALS = SUM(ACTUAL_DISCOUNT_DOLLAR)
            
                   ,RS.DISCOUNT DISCOUNT
            
                   ,RS.HIERARCHY_NO
            
                   ,RS.LEVEL_NAME
            
                   ,P.PERIOD
            
                   ,P.YEAR
            
                   ,RS.PARENT_HIERARCHY_NO
            
            FROM ' + @P_ACTUAL_TABLE + ' NDP
            
            INNER JOIN ' + @P_MASTER_TABLE + ' NDPM ON NDPM.CCP_DETAILS_SID = NDP.CCP_DETAILS_SID
            
                   AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
            
            INNER JOIN #RS_COMBINATION RS ON NDPM.CCP_DETAILS_SID = RS.CCP_DETAILS_SID
            
                   AND NDPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
            
            INNER JOIN #PERIOD P ON P.PERIOD_SID = NDP.PERIOD_SID
            
            GROUP BY RS.DISCOUNT
            
                   ,RS.HIERARCHY_NO
            
                   ,RS.LEVEL_NAME
            
                   ,P.PERIOD
            
                   ,P.YEAR
            
                   ,PARENT_HIERARCHY_NO
            
            '
            
             
            
                          EXEC SP_EXECUTESQL @D_SQL
            
            */
            IF Object_id('TEMPDB..#PPA_DATA') IS NOT NULL
              DROP TABLE #PPA_DATA

            CREATE TABLE #PPA_DATA
              (
                 CONTRACT_SALES_PROJECTED NUMERIC(38, 6),
                 DISCOUNT                 VARCHAR(50),
                 HIERARCHY_NO             VARCHAR(8000),
                 PARENT_HIERARCHY_NO      VARCHAR(8000),
                 LEVEL_NAME               VARCHAR(100),
                 PERIOD                   VARCHAR(50),
                 [YEAR]                   INT
              )

            /*SET @D_SQL = 'INSERT INTO #PPA_DATA (
            
                   CONTRACT_SALES_PROJECTED
            
                   ,DISCOUNT
            
                   ,HIERARCHY_NO
            
                   ,LEVEL_NAME
            
                   ,PERIOD
            
                   ,[YEAR]
            
                   ,PARENT_HIERARCHY_NO
            
                   )
            
            SELECT CONTRACT_SALES_PROJECTED = ISNULL(SUM(PROJECTION_DISCOUNT_DOLLAR), 0)
            
                   ,RS.DISCOUNT DISCOUNT
            
                   ,RS.HIERARCHY_NO
            
                   ,RS.LEVEL_NAME
            
                   ,P.PERIOD
            
                   ,P.YEAR
            
                   ,PARENT_HIERARCHY_NO
            
            FROM ' + @P_PROJECTION_TABLE + ' NDP
            
            INNER JOIN ' + @P_MASTER_TABLE + ' NDPM ON NDPM.CCP_DETAILS_SID = NDP.CCP_DETAILS_SID
            
                   AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
            
            INNER JOIN #RS_COMBINATION RS ON NDPM.CCP_DETAILS_SID = RS.CCP_DETAILS_SID
            
                   AND NDPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
            
            INNER JOIN #PERIOD P ON P.PERIOD_SID = NDP.PERIOD_SID
            
            GROUP BY RS.DISCOUNT
            
                   ,RS.HIERARCHY_NO
            
                   ,RS.LEVEL_NAME
            
                   ,P.PERIOD
            
                   ,P.YEAR
            
                   ,PARENT_HIERARCHY_NO
            
            '
            
             
            
                          EXEC SP_EXECUTESQL @D_SQL
            
            */
            IF Object_id('TEMPDB.DBO.#CURRENT_SALES', 'U') IS NOT NULL
              DROP TABLE #CURRENT_SALES;

            SELECT DISTINCT FD.PERIOD,
                            FD.YEAR,
                            fd.DISCOUNT,
                            FD.HIERARCHY_NO,
                            FD.PARENT_HIERARCHY_NO,
                            fd.level_no,
                            NM_ACTUAL_SALES = COALESCE(( NAS.CONTRACT_SALES_ACTUALS ), 0),
                            NM_ACTUAL_UNITS = COALESCE(( NAS.CONTRACT_UNITS_ACTUALS ), 0),
                            NM_PROJECTED_SALES = COALESCE(( NSP.CONTRACT_SALES_PROJECTED ), 0),
                            NM_PROJECTED_UNITS = COALESCE(( NSP.CONTRACT_UNITS_PROJECTED ), 0),FD.SELECTED_SID
            INTO   #CURRENT_SALES
            FROM   (SELECT DISTINCT PERIOD,
                                    YEAR,
                                    DISCOUNT,
                                    HIERARCHY_NO,
                                    PARENT_HIERARCHY_NO,
                                    LEVEL_NO,SELECTED_SID
                    FROM   #DISCOUNT_DATA_TABLE) FD
                   LEFT JOIN #SALES_ACTUAL_DATA NAS
                          ON FD.YEAR = NAS.YEAR
                             AND FD.PERIOD = NAS.PERIOD
                             AND FD.HIERARCHY_NO = NAS.HIERARCHY_NO
                             AND FD.LEVEL_NO = NAS.LEVEL_NO
                             AND FD.DISCOUNT = NAS.DISCOUNT
                             AND COALESCE(FD.PARENT_HIERARCHY_NO, fd.HIERARCHY_NO) = COALESCE(NAS.PARENT_HIERARCHY_NO, nas.HIERARCHY_NO)
                   LEFT JOIN #SALES_DATA NSP
                          ON NSP.YEAR = FD.YEAR
                             AND NSP.PERIOD = FD.PERIOD
                             AND NSP.HIERARCHY_NO = FD.HIERARCHY_NO
                             AND NSP.LEVEL_NO = FD.LEVEL_NO
                             AND FD.DISCOUNT = nsp.DISCOUNT
                             AND COALESCE(FD.PARENT_HIERARCHY_NO, fd.HIERARCHY_NO) = COALESCE(nsp.PARENT_HIERARCHY_NO, nsp.HIERARCHY_NO)
--SELECT * FROM #CURRENT_DISCOUNT WHERE HIERARCHY_NO='844-58.'
            IF Object_id('TEMPDB.DBO.#CURRENT_DISCOUNT', 'U') IS NOT NULL
              DROP TABLE #CURRENT_DISCOUNT;

            SELECT DISTINCT FD.YEAR,
                   FD.PERIOD,
                   FD.DISCOUNT,
                   ACTUAL_SALES = COALESCE(( NAD.CONTRACT_SALES_ACTUALS ), 0),
                   PROJECTION_SALES = COALESCE(( NSP.CONTRACT_SALES_PROJECTED ), 0),
                   FD.HIERARCHY_NO,
                   FD.PARENT_HIERARCHY_NO,
                   FD.LEVEL_NO,
                   COALESCE(ACCRUAL_DISCOUNT_PROJ, ACCRUAL_DISCOUNT_ACTUAL) AS ACCRUAL_DISCOUNT
                   ---,NSP.DEDUCTION_INCLUSION
                   ,
                   NSP.AP,
                   NAD.TS,FD.SELECTED_SID
            ---,nsp.USER_GROUP
            INTO   #CURRENT_DISCOUNT
            FROM   (SELECT DISTINCT PERIOD,
                                    YEAR,
                                    DISCOUNT,
                                    HIERARCHY_NO,
                                    PARENT_HIERARCHY_NO,
                                    LEVEL_NO,SELECTED_SID
                    FROM   #DISCOUNT_DATA_TABLE D
                   --WHERE EXISTS (
                   --            SELECT 1
                   --            FROM #RS_COMBINATION R
                   --            INNER JOIN RS_CONTRACT RS
                   --                   ON RS.RS_CONTRACT_SID = R.RS_CONTRACT_SID
                   --            INNER JOIN HELPER_TABLE H
                   --                   ON H.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE
                   --            WHERE H.DESCRIPTION <> 'PRICE PROTECTION' AND D.DISCOUNT = R.DISCOUNT AND D.PARENT_HIERARCHY_NO = R.PARENT_HIERARCHY_NO
                   --            )
                   ) FD
                    JOIN (SELECT *,
                                     1 ap
                              FROM   #DISCOUNT_DATA) NSP
                          ON FD.YEAR = NSP.YEAR
                             AND FD.PERIOD = NSP.PERIOD
                             AND FD.DISCOUNT = NSP.DISCOUNT
                             AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
                             AND COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO) = COALESCE(NSP.PARENT_HIERARCHY_NO, NSP.HIERARCHY_NO)
                             AND FD.LEVEL_NO = NSP.LEVEL_NO
							 AND FD.SELECTED_SID = NSP.SELECTED_SID
                    left JOIN (SELECT *,
                                     0 ts
                              FROM   #DISCOUNT_ACTUAL_DATA) NAD
                          ON NAD.YEAR = FD.YEAR
                             AND NAD.PERIOD = FD.PERIOD
                             AND NAD.DISCOUNT = FD.DISCOUNT
                             AND NAD.HIERARCHY_NO = FD.HIERARCHY_NO
                             AND COALESCE(NAD.PARENT_HIERARCHY_NO, NAD.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
                             AND NAD.LEVEL_NO = FD.LEVEL_NO
							  AND NAD.SELECTED_SID = FD.SELECTED_SID

            /*     IF Object_id('TEMPDB.DBO.#CURRENT_PPA', 'U') IS NOT NULL
            
                                 DROP TABLE #CURRENT_PPA;
            
             
            
                          SELECT FD.YEAR,
            
                                 FD.PERIOD,
            
                                 PPA_DISCOUNT_PROJECTED = COALESCE((NSP.CONTRACT_SALES_PROJECTED), 0),
            
                                 PPA_ACTUAL_SALES = COALESCE((NAS.CONTRACT_SALES_ACTUALS), 0),
            
                                 FD.DISCOUNT,
            
                                 FD.HIERARCHY_NO,
            
                                 FD.PARENT_HIERARCHY_NO,
            
                                 FD.LEVEL_NAME
            
                          INTO #CURRENT_PPA
            
                          FROM (
            
                                 SELECT DISTINCT PERIOD,
            
                                       YEAR,
            
                                       DISCOUNT,
            
                                       HIERARCHY_NO,
            
                                       LEVEL_NAME,
            
                                       PARENT_HIERARCHY_NO
            
                                 FROM #DISCOUNT_DATA_TABLE D
            
                                 --WHERE EXISTS (
            
                                 --            SELECT 1
            
                                 --            FROM #RS_COMBINATION R
            
                                 --            INNER JOIN RS_CONTRACT RS
            
                                 --                   ON RS.RS_CONTRACT_SID = R.RS_CONTRACT_SID
            
                                 --            INNER JOIN HELPER_TABLE H
            
                                 --                   ON H.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE
            
                                 --            WHERE H.DESCRIPTION = 'PRICE PROTECTION' AND D.DISCOUNT = R.DISCOUNT AND D.PARENT_HIERARCHY_NO = R.PARENT_HIERARCHY_NO
            
                                 --            )
            
                                 ) FD
            
                          LEFT JOIN #PPA_ACTUAL_DATA NAS
            
                                 ON NAS.YEAR = FD.YEAR AND NAS.PERIOD = FD.PERIOD AND NAS.DISCOUNT = FD.DISCOUNT AND NAS.HIERARCHY_NO = FD.HIERARCHY_NO AND NAS.LEVEL_NAME = FD.LEVEL_NAME AND NAS.PARENT_HIERARCHY_NO = FD.PARENT_HIERARCHY_NO
            
                          LEFT JOIN #PPA_DATA NSP
            
                                 ON FD.YEAR = NSP.YEAR AND FD.PERIOD = NSP.PERIOD AND FD.DISCOUNT = NSP.DISCOUNT AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO AND FD.LEVEL_NAME = NSP.LEVEL_NAME AND FD.PARENT_HIERARCHY_NO = NAS.PARENT_HIERARCHY_NO
            
                                 */
            IF Object_id('TEMPDB..#DPIVOT_TABLE') IS NOT NULL
              DROP TABLE #DPIVOT_TABLE

            CREATE TABLE #DPIVOT_TABLE
              (
                 PROJECTION_MASTER_SID            INT,
                 LEVEL_NAME                       VARCHAR(100),
                 LEVEL_No                         INT,
                 HIERARCHY_NO                     VARCHAR(8000),
                 PARENT_HIERARCHY_NO              VARCHAR(8000),
                 CP_INDICATOR                     CHAR(1),
                 PERIOD                           INT,
                 YEAR                             INT,
                 DISCOUNT_AMOUNT_ACCRUAL          NUMERIC(38, 6),
                 DISCOUNT_AMOUNT                  NUMERIC(38, 6),
                 DISCOUNT_AMOUNT_PROJECTED        NUMERIC(38, 6),
                 DISCOUNT_RATE_ACCRUAL            NUMERIC(38, 6),
                 DISCOUNT_RATE                    NUMERIC(38, 6),
                 DISCOUNT_RATE_PROJECTED          NUMERIC(38, 6),
                 DISCOUNT_RPU_ACCRUAL             NUMERIC(38, 6),
                 DISCOUNT_RPU                     NUMERIC(38, 6),
                 DISCOUNT_RPU_PROJECTED           NUMERIC(38, 6),
                 DISCOUNT_OF_EX_FACTORY_ACRUAL    NUMERIC(38, 6),
                 DISCOUNT_OF_EX_FACTORY_ACTUALS   NUMERIC(38, 6),
                 DISCOUNT_OF_EX_FACTORY_PROJECTED NUMERIC(38, 6),
                 DISCOUNT                         VARCHAR(100),
				 SELECTED_SID	INT
              )

            INSERT INTO #DPIVOT_TABLE
                        (PROJECTION_MASTER_SID,
                         DISCOUNT,
                         CP_INDICATOR,
                         DISCOUNT_AMOUNT,
                         DISCOUNT_AMOUNT_PROJECTED,
                         DISCOUNT_RATE,
                         DISCOUNT_RATE_PROJECTED,
                         DISCOUNT_RPU,
                         DISCOUNT_RPU_PROJECTED,
                         DISCOUNT_OF_EX_FACTORY_ACTUALS,
                         DISCOUNT_OF_EX_FACTORY_PROJECTED,
                         PERIOD,
                         YEAR,
                         HIERARCHY_NO,
                         LEVEL_NAME,
                         DISCOUNT_AMOUNT_ACCRUAL,
                         PARENT_HIERARCHY_NO,
                         LEVEL_No,SELECTED_SID)
            SELECT DISTINCT @FIRST_PROJ_SID  AS PROJECTION_MASTER_SID,
                            dt.DISCOUNT,
                            @CP_INDICATOR    CP_INDICATOR,
                            DISCOUNT_AMOUNT = CASE
                                                WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01) THEN Isnull(DT.ACTUAL_SALES, 0)
                                                ELSE NULL
                                              END,
                            DISCOUNT_AMOUNT_PROJECTED = Isnull(DT.PROJECTION_SALES, 0),
                            DISCOUNT_RATE = CASE
                                              WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01) THEN Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_SALES, 0), 0) * 100
                                              ELSE NULL
                                            END,
                            DISCOUNT_RATE_PROJECTED = Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_SALES, 0), 0) * 100,
                            DISCOUNT_RPU = CASE
                                             WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01) THEN Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_UNITS, 0), 0)
                                             ELSE NULL
                                           END,
                            DISCOUNT_RPU_PROJECTED = Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_UNITS, 0), 0),
                            DISCOUNT_OF_EX_FACTORY_ACTUALS = CASE
                                                               WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01) THEN Isnull(DT.ACTUAL_SALES / NULLIF(F.GTS_SALES_ACTUALS, 0), 0) * 100
                                                               ELSE NULL
                                                             END,
                            DISCOUNT_OF_EX_FACTORY_PROJECTED = Isnull(DT.PROJECTION_SALES / NULLIF(F.GTS_SALES_PROJECTED, 0), 0) * 100,
                            DT.PERIOD,
                            DT.[YEAR],
                            DT.HIERARCHY_NO,
                            NULL             AS LEVEL_NAME,
                            ACCRUAL_DISCOUNT AS DISCOUNT_AMOUNT_ACCRUAL,
                            DT.PARENT_HIERARCHY_NO,
                            dt.LEVEL_No,DT.SELECTED_SID
            FROM   #CURRENT_DISCOUNT DT
                   INNER JOIN #CURRENT_SALES S
                           ON S.HIERARCHY_NO = DT.HIERARCHY_NO
                              AND S.YEAR = DT.YEAR
                              AND S.PERIOD = DT.PERIOD
                              AND S.DISCOUNT = DT.DISCOUNT
                              AND COALESCE(S.PARENT_HIERARCHY_NO, S.HIERARCHY_NO) = COALESCE(DT.PARENT_HIERARCHY_NO, DT.HIERARCHY_NO)
                              AND S.level_no = DT.level_no
							  AND S.SELECTED_SID = DT.SELECTED_SID
                   LEFT JOIN #FILE_DATA F
                          ON F.YEAR = S.YEAR
                             AND F.PERIOD = S.PERIOD
                             AND S.HIERARCHY_NO = F.HIERARCHY_NO
                             AND S.level_no = F.level_no
                             AND S.DISCOUNT = F.DISCOUNT
                             AND COALESCE(S.PARENT_HIERARCHY_NO, S.HIERARCHY_NO) = COALESCE(F.PARENT_HIERARCHY_NO, F.HIERARCHY_NO)

            /*UNION ALL
            
                         
            
                          SELECT DISTINCT @FIRST_PROJ_SID AS PROJECTION_MASTER_SID,
            
                                 p.DISCOUNT,
            
                                 @CP_INDICATOR CP_INDICATOR,
            
                                 DISCOUNT_AMOUNT = CASE WHEN @CURRENT_DATE > Datefromparts(P.[YEAR], P.PERIOD, 01) THEN P.PPA_ACTUAL_SALES ELSE NULL END,
            
                                 DISCOUNT_AMOUNT_PROJECTED = PPA_DISCOUNT_PROJECTED,
            
                                 CASE WHEN @CURRENT_DATE > Datefromparts(P.[YEAR], P.PERIOD, 01) THEN isnull(P.PPA_ACTUAL_SALES / NULLIF(S.NM_ACTUAL_SALES, 0), 0) * 100 ELSE NULL END,
            
                                 Isnull(PPA_DISCOUNT_PROJECTED / NULLIF(S.NM_PROJECTED_SALES, 0), 0) * 100,
            
                                 CASE WHEN @CURRENT_DATE > Datefromparts(P.[YEAR], P.PERIOD, 01) THEN isnull(P.PPA_ACTUAL_SALES / NULLIF(S.NM_ACTUAL_UNITS, 0), 0) ELSE NULL END,
            
                                 Isnull(PPA_DISCOUNT_PROJECTED / NULLIF(S.NM_PROJECTED_UNITS, 0), 0),
            
                                 CASE WHEN @CURRENT_DATE > Datefromparts(P.[YEAR], P.PERIOD, 01) THEN Isnull(P.PPA_ACTUAL_SALES / NULLIF(F.GTS_SALES_ACTUALS, 0), 0) * 100 ELSE NULL END,
            
                                 Isnull(P.PPA_DISCOUNT_PROJECTED / NULLIF(F.GTS_SALES_PROJECTED, 0), 0) * 100,
            
                                 P.PERIOD,
            
                                 P.[YEAR],
            
                                 P.HIERARCHY_NO,
            
                                 P.LEVEL_NAME,
            
                                 NULL AS DISCOUNT_AMOUNT_ACCRUAL,
            
                                 P.PARENT_HIERARCHY_NO
            
                          FROM #CURRENT_PPA P
            
                          INNER JOIN #CURRENT_SALES S
            
                                 ON S.HIERARCHY_NO = p.HIERARCHY_NO AND S.LEVEL_NAME = p.LEVEL_NAME AND S.YEAR = p.YEAR AND S.PERIOD = p.PERIOD AND S.DISCOUNT = p.DISCOUNT AND S.PARENT_HIERARCHY_NO = P.PARENT_HIERARCHY_NO
            
                          LEFT JOIN #FILE_DATA F
            
                                 ON F.YEAR = P.YEAR AND F.PERIOD = P.PERIOD AND p.HIERARCHY_NO = F.HIERARCHY_NO AND p.LEVEL_NAME = F.LEVEL_NAME AND p.DISCOUNT = F.DISCOUNT AND P.PARENT_HIERARCHY_NO = F.PARENT_HIERARCHY_NO
            
            */
            IF EXISTS (SELECT 1
                       FROM   #PROJECTION_MASTER
                       WHERE  ID > 1)
              BEGIN
                  IF Object_id('TEMPDB..#DPRIOR_PROJECTIONS') IS NOT NULL
                    DROP TABLE #DPRIOR_PROJECTIONS

                  SELECT PD.PROJECTION_DETAILS_SID,
                         C.CCP_DETAILS_SID,
                         PM.PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         level_no,
                         PARENT_HIERARCHY_NO PARENT_HIERARCHY_NO
                  INTO   #DPRIOR_PROJECTIONS
                  FROM   #PROJECTION_MASTER PM
                         INNER JOIN PROJECTION_DETAILS PD
                                 ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                         INNER JOIN #CCP C
                                 ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                  WHERE  PM.ID <> 1

                  IF Object_id('TEMPDB..#DPRIOR_DATA_TABLE') IS NOT NULL
                    DROP TABLE #DPRIOR_DATA_TABLE
/*
                  SELECT PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         PERIOD,
                         YEAR,
                         DISCOUNT,
                         PARENT_HIERARCHY_NO,
                         level_no
                  INTO   #DPRIOR_DATA_TABLE
                  FROM   (SELECT DISTINCT HIERARCHY_NO,
                                          level_no,
                                          PARENT_HIERARCHY_NO PARENT_HIERARCHY_NO
                          FROM   #CCP) C
                         CROSS JOIN (SELECT DISTINCT PERIOD,
                                                     YEAR
                                     FROM   #PERIOD
                                     WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID) P
                         CROSS JOIN #PROJECTION_MASTER PM
                         CROSS JOIN (SELECT DISCOUNT
                                     FROM   #DISCOUNT_INFO) D
                  WHERE  PM.ID <> 1
				  */
				  select PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         PERIOD,
                         YEAR,
                         DISCOUNT,
                         PARENT_HIERARCHY_NO,
                         level_no 
						 INTO   #DPRIOR_DATA_TABLE
						 from #DISCOUNT_DATA_TABLE dt cross join  #PROJECTION_MASTER pm
						 WHERE  PM.ID <> 1

                  IF Object_id('TEMPDB..#SALES_INCLUSION1') IS NOT NULL
                    DROP TABLE #SALES_INCLUSION1

                  SELECT DISTINCT b.PROJECTION_DETAILS_SID,
                                  B.CCP_DETAILS_SID,
                                  CASE
                                    WHEN DESCRIPTION = 'YES' THEN 1
                                    ELSE 0
                                  END SALES_INCLUSION
                  INTO   #SALES_INCLUSION1
                  FROM   --NM_SALES_PROJECTION_MASTER A
                  ---join #DPRIOR_PROJECTIONS b --ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                  #DPRIOR_PROJECTIONS b
                  /*INNER JOIN PROJECTION_DETAILS B
                  
                                             ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID AND EXISTS (
                  
                                                           SELECT 1
                  
                                                           FROM #DPRIOR_PROJECTIONS PM
                  
                                                           WHERE PM.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID AND PM.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                  
                                                           )
                  
                                       INNER JOIN CCP_DETAILS CD
                  
                                             ON CD.CCP_DETAILS_SID = B.CCP_DETAILS_SID*/
                  INNER JOIN #CCP_details_temp CD
                          ON CD.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                  INNER JOIN CFP_CONTRACT_DETAILS CC
                          ON CC.COMPANY_MASTER_SID = CD.COMPANY_MASTER_SID
                  INNER JOIN CFP_CONTRACT CC1
                          ON CC1.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
                             AND CC1.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
                  INNER JOIN HELPER_TABLE HT
                          ON HT.HELPER_TABLE_SID = CC1.SALES_INCLUSION
                  WHERE  EXISTS (SELECT 1
                                 FROM   #MULTISELECT_DISCOUNTS MD
                                 WHERE  MD.CCP_DETAILS_SID = CD.CCP_DETAILS_SID)

                  IF Object_id('TEMPDB..#DEDUCTION_INCLUSION1') IS NOT NULL
                    DROP TABLE #DEDUCTION_INCLUSION1

                  SELECT distinct B.PROJECTION_DETAILS_SID,
                         B.CCP_DETAILS_SID,
                         RS.RS_CONTRACT_SID,
                         CASE
                           WHEN DESCRIPTION = 'YES' THEN 1
                           ELSE 0
                         END DEDUCTION_INCLUSION
                  INTO   #DEDUCTION_INCLUSION1
                  FROM   #MULTISELECT_DISCOUNTS A
                         INNER JOIN #DPRIOR_PROJECTIONS b
                                 ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                         /*INNER JOIN PROJECTION_DETAILS B
                         
                                                    ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID AND EXISTS (
                         
                                                                  SELECT 1
                         
                                                                  FROM #DPRIOR_PROJECTIONS PM
                         
                                                                  WHERE PM.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID AND PM.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                         
                                                                  )*/
                         INNER JOIN RS_CONTRACT RS
                                 ON A.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
                         INNER JOIN HELPER_TABLE HT
                                 ON HT.HELPER_TABLE_SID = RS.DEDUCTION_INCLUSION
                  WHERE  EXISTS (SELECT 1
                                 FROM   #MULTISELECT_DISCOUNTS MD
                                 WHERE  MD.CCP_DETAILS_SID = b.CCP_DETAILS_SID)

                  IF Object_id('TEMPDB..#PRIOR_ACCRUAL_DISCOUNT1') IS NOT NULL
                    DROP TABLE #PRIOR_ACCRUAL_DISCOUNT1;

                  CREATE TABLE #PRIOR_ACCRUAL_DISCOUNT1
                    (
                       PROJECTION_MASTER_SID  INT,
                       PROJECTION_DETAILS_SID INT,
                       PERIOD_SID             INT,
                       RS_CONTRACT_SID        INT,
                       DISCOUNT_AMOUNT        NUMERIC(38, 6),
                       HIERARCHY_NO           VARCHAR(100),
                       LEVEL_NO               INT,
                       PARENT_HIERARCHY_NO    VARCHAR(8000),SELECTED_SID INT
                    );

                  WITH CTE
                       AS (SELECT DISTINCT COMPANY_MASTER_SID,
                                           CONTRACT_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           DP.PROJECTION_MASTER_SID,
                                           DP.PROJECTION_DETAILS_SID,
                                           PERIOD_SID,
                                           PERIOD_DATE,
                                           YEAR,
                                           RS.RS_CONTRACT_SID,
                                           RS.RS_MODEL_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NO,
                                           PARENT_HIERARCHY_NO,SELECTED_SID
                           FROM   #CCP_DETAILS_TEMP A
                                  --INNER JOIN PROJECTION_DETAILS PD
                                  --     ON PD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                  INNER JOIN #DPRIOR_PROJECTIONS DP
                                          --ON DP.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID AND DP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                          ON DP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                  INNER JOIN #MULTISELECT_DISCOUNTS RS
                                          ON RS.CCP_DETAILS_SID = DP.CCP_DETAILS_SID
                                  INNER JOIN #PERIOD P
                                          ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID)
                  INSERT INTO #PRIOR_ACCRUAL_DISCOUNT1
                              (PROJECTION_MASTER_SID,
                               PROJECTION_DETAILS_SID,
                               PERIOD_SID,
                               RS_CONTRACT_SID,
                               DISCOUNT_AMOUNT,
                               HIERARCHY_NO,
                               LEVEL_NO,
                               PARENT_HIERARCHY_NO,SELECTED_SID)
                  SELECT PROJECTION_MASTER_SID,
                         A2.PROJECTION_DETAILS_SID,
                         PERIOD_SID,
                         A2.RS_CONTRACT_SID,
                         Sum(DEDUCTION_AMOUNT) / ( Datediff(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE)
                                                   + 1 ) DISCOUNT_AMOUNT,
                         HIERARCHY_NO,
                         LEVEL_NO,
                         PARENT_HIERARCHY_NO,SELECTED_SID
                  FROM   ACCRUAL_MASTER A1
                         INNER JOIN CTE A2
                                 ON A2.PERIOD_DATE BETWEEN CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_START_DATE, 0)))) AND CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_END_DATE, 0))))
                                    AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID
                                    AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID
                                    AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID
                                    AND A2.RS_MODEL_SID = A1.RS_MODEL_SID
                                    AND Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_START_DATE, -1)) >= @STARTFROM
                                    AND A1.ACCRUAL_PERIOD_END_DATE <= @PROJECTION_DATE
                  GROUP  BY PROJECTION_MASTER_SID,
                            A2.PROJECTION_DETAILS_SID,
                            PERIOD_SID,
                            A2.RS_CONTRACT_SID,
                            ACCRUAL_PERIOD_START_DATE,
                            ACCRUAL_PERIOD_END_DATE,
                            HIERARCHY_NO,
                            LEVEL_NO,
                            PARENT_HIERARCHY_NO,SELECTED_SID

                  IF Object_id('TEMPDB..#DPRIOR_ACTUAL_SALES_DATA') IS NOT NULL
                    DROP TABLE #DPRIOR_ACTUAL_SALES_DATA

                  SELECT PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         LEVEL_NO,
                         DISCOUNT,
                         PERIOD,
                         [YEAR],
                         Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                    OR @SALES_INCLUSION IS NULL ), Isnull(ACTUAL_SALES, 0), NULL))                                     AS CONTRACT_SALES_ACTUALS,
                         Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                    OR @SALES_INCLUSION IS NULL ), Isnull(ACTUAL_UNITS, 0), NULL) * COALESCE(NULLIF(UOM_VALUE, 0), 1)) AS CONTRACT_UNITS_ACTUALS,
                         PARENT_HIERARCHY_NO
                  INTO   #DPRIOR_ACTUAL_SALES_DATA
                  FROM   (SELECT DISTINCT PP.PROJECTION_MASTER_SID,
                                          PP.HIERARCHY_NO,
                                          PP.LEVEL_NO,
                                          RC.DISCOUNT,
                                          PP.PROJECTION_DETAILS_SID,
                                          PP.PARENT_HIERARCHY_NO,
                                          PP.CCP_DETAILS_SID
                          FROM   #DPRIOR_PROJECTIONS PP
                                 --INNER JOIN PROJECTION_DETAILS PD
                                 --     ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
                                 INNER JOIN #RS_COMBINATION RC
                                         ON RC.CCP_DETAILS_SID = PP.CCP_DETAILS_SID
                                            AND RC.HIERARCHY_NO = PP.HIERARCHY_NO
                                            AND RC.LEVEL_NO = PP.LEVEL_NO
                                            AND COALESCE(RC.PARENT_HIERARCHY_NO, RC.HIERARCHY_NO) = COALESCE(PP.PARENT_HIERARCHY_NO, PP.HIERARCHY_NO) --PP.PARENT_HIERARCHY_NO
                         ) S
                         INNER JOIN #PERIOD P
                                 ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                         LEFT JOIN NM_ACTUAL_SALES NPS
                                ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                   AND P.PERIOD_SID = NPS.PERIOD_SID
                         INNER JOIN #SALES_INCLUSION1 SI
                                 ON SI.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                         INNER JOIN #CCP_DETAILS_TEMP CD
                                 ON CD.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                         LEFT JOIN #ITEM_UOM_DETAILS UOM
                                ON UOM.ITEM_MASTER_SID = CD.ITEM_MASTER_SID
                  GROUP  BY PROJECTION_MASTER_SID,
                            HIERARCHY_NO,
                            LEVEL_NO,
                            DISCOUNT,
                            PERIOD,
                            [YEAR],
                            PARENT_HIERARCHY_NO

                  IF Object_id('TEMPDB..#DPRIOR_SALES_DATA') IS NOT NULL
                    DROP TABLE #DPRIOR_SALES_DATA

                  SELECT PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         LEVEL_NO,
                         DISCOUNT,
                         PERIOD,
                         [YEAR],
                         Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                    OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_SALES, 0), NULL))                                     AS CONTRACT_SALES_PROJECTED,
                         Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                    OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_UNITS, 0), NULL) * COALESCE(NULLIF(UOM_VALUE, 0), 1)) AS CONTRACT_UNITS_PROJECTED,
                         PARENT_HIERARCHY_NO,SELECTED_SID
                  INTO   #DPRIOR_SALES_DATA
                  FROM   (SELECT DISTINCT PP.PROJECTION_MASTER_SID,
                                          PP.HIERARCHY_NO,
                                          PP.LEVEL_NO,
                                          RC.DISCOUNT,
                                          PP.PROJECTION_DETAILS_SID,
                                          PP.PARENT_HIERARCHY_NO,
                                          PP.CCP_DETAILS_SID,RC.SELECTED_SID
                          FROM   #DPRIOR_PROJECTIONS PP
                                 --INNER JOIN PROJECTION_DETAILS PD
                                 --     ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
                                 INNER JOIN #RS_COMBINATION RC
                                         ON RC.CCP_DETAILS_SID = PP.CCP_DETAILS_SID
                                            AND RC.HIERARCHY_NO = PP.HIERARCHY_NO
                                            AND RC.LEVEL_NO = PP.LEVEL_NO
                                            AND COALESCE(PP.PARENT_HIERARCHY_NO, pp.HIERARCHY_NO) = COALESCE(RC.PARENT_HIERARCHY_NO, RC.HIERARCHY_NO)) S
                         INNER JOIN #PERIOD P
                                 ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                         LEFT JOIN NM_SALES_PROJECTION NPS
                                ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                   AND P.PERIOD_SID = NPS.PERIOD_SID
                         INNER JOIN #SALES_INCLUSION1 SI
                                 ON SI.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                         INNER JOIN #CCP_DETAILS_TEMP CD
                                 ON CD.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                         LEFT JOIN #ITEM_UOM_DETAILS UOM
                                ON UOM.ITEM_MASTER_SID = CD.ITEM_MASTER_SID
                  GROUP  BY PROJECTION_MASTER_SID,
                            HIERARCHY_NO,
                            LEVEL_NO,
                            DISCOUNT,
                            PERIOD,
                            [YEAR],
                            PARENT_HIERARCHY_NO,SELECTED_SID

                  IF Object_id('TEMPDB..#PRIOR_ACTUAL_DISCOUNT_DATA') IS NOT NULL
                    DROP TABLE #PRIOR_ACTUAL_DISCOUNT_DATA

                  SELECT s.PROJECTION_MASTER_SID,
                         S.HIERARCHY_NO,
                         S.LEVEL_NO,
                         DISCOUNT,
                         PERIOD,
                         [YEAR],
                         Isnull(Sum(Iif(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
                                           OR @DEDUCTION_INCLUSION IS NULL ), Isnull(ACTUAL_SALES, 0), NULL)), 0) AS CONTRACT_SALES_ACTUALS,
                         Sum(Isnull(DISCOUNT_AMOUNT, 0))                                                          ACCRUAL_DISCOUNT_ACTUAL,
                         S.PARENT_HIERARCHY_NO,S.SELECTED_SID
                  INTO   #PRIOR_ACTUAL_DISCOUNT_DATA
                  FROM   (SELECT DISTINCT PP.PROJECTION_MASTER_SID,
                                          PP.HIERARCHY_NO,
                                          PP.LEVEL_NO,
                                          RC.DISCOUNT,
                                          PP.PROJECTION_DETAILS_SID,
                                          RC.RS_CONTRACT_SID,
                                          PP.PARENT_HIERARCHY_NO,RC.SELECTED_SID
                          FROM   #DPRIOR_PROJECTIONS PP
                                 --INNER JOIN PROJECTION_DETAILS PD
                                 --ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
                                 INNER JOIN #RS_COMBINATION RC
                                         ON RC.CCP_DETAILS_SID = PP.CCP_DETAILS_SID
                                            AND RC.HIERARCHY_NO = PP.HIERARCHY_NO
                                            AND RC.LEVEL_NO = PP.LEVEL_NO
                                            AND COALESCE(PP.PARENT_HIERARCHY_NO, pp.HIERARCHY_NO) = COALESCE(RC.PARENT_HIERARCHY_NO, RC.HIERARCHY_NO)
                         /*WHERE EXISTS (
                         
                                                                  SELECT 1
                         
                                                                  FROM #RS_COMBINATION R
                         
                                                                  INNER JOIN RS_CONTRACT RS
                         
                                                                         ON RS.RS_CONTRACT_SID = R.RS_CONTRACT_SID
                         
                                                                  INNER JOIN HELPER_TABLE H
                         
                                                                         ON H.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE
                         
                                                                  WHERE H.DESCRIPTION <> 'PRICE PROTECTION' AND RC.DISCOUNT = R.DISCOUNT AND PP.PARENT_HIERARCHY_NO = R.PARENT_HIERARCHY_NO
                         
                                                                  )*/
                         ) S
                         INNER JOIN #PERIOD P
                                 ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                         LEFT JOIN NM_ACTUAL_DISCOUNT NPS
                                ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                   AND P.PERIOD_SID = NPS.PERIOD_SID
                         INNER JOIN #DEDUCTION_INCLUSION1 DI
                                 ON DI.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                    AND DI.RS_CONTRACT_SID = S.RS_CONTRACT_SID
                         LEFT JOIN #PRIOR_ACCRUAL_DISCOUNT1 AD
                                ON AD.PROJECTION_MASTER_SID = S.PROJECTION_MASTER_SID
                                   AND AD.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                   AND AD.RS_CONTRACT_SID = NPS.RS_CONTRACT_SID
                                   AND AD.HIERARCHY_NO = S.HIERARCHY_NO
                                   AND COALESCE(S.PARENT_HIERARCHY_NO, S.HIERARCHY_NO) = COALESCE(AD.PARENT_HIERARCHY_NO, AD.HIERARCHY_NO)
                                   AND AD.LEVEL_NO = S.LEVEL_NO
                                   AND AD.PERIOD_SID = P.PERIOD_SID
						where exists (select 1 from #MULTISELECT_DISCOUNTS md  where md.ccp_details_sid=di.CCP_DETAILS_SID 
						and md.RS_CONTRACT_SID=NPS.RS_CONTRACT_SID)
                  GROUP  BY S.PROJECTION_MASTER_SID,
                            S.HIERARCHY_NO,
                            S.LEVEL_NO,
                            DISCOUNT,
                            PERIOD,
                            [YEAR],
                            S.PARENT_HIERARCHY_NO,S.SELECTED_SID

                  IF Object_id('TEMPDB..#PRIOR_DISCOUNT_DATA') IS NOT NULL
                    DROP TABLE #PRIOR_DISCOUNT_DATA

                  SELECT s.PROJECTION_MASTER_SID,
                         s.HIERARCHY_NO,
                         s.LEVEL_NO,
                         DISCOUNT,
                         PERIOD,
                         [YEAR],
                         Isnull(Sum(Iif(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
                                           OR @DEDUCTION_INCLUSION IS NULL ), Isnull(PROJECTION_SALES, 0), NULL)), 0) AS CONTRACT_SALES_PROJECTED,
                         Sum(Isnull(DISCOUNT_AMOUNT, 0))                                                              ACCRUAL_DISCOUNT_PROJ,
                         S.PARENT_HIERARCHY_NO,S.SELECTED_SID
                  INTO   #PRIOR_DISCOUNT_DATA
                  FROM   (SELECT DISTINCT PP.PROJECTION_MASTER_SID,
                                          PP.HIERARCHY_NO,
                                          PP.LEVEL_NO,
                                          RC.DISCOUNT,
                                          PP.PROJECTION_DETAILS_SID,
                                          RC.RS_CONTRACT_SID,
                                          PP.PARENT_HIERARCHY_NO,RC.SELECTED_SID
                          FROM   #DPRIOR_PROJECTIONS PP
                                 --INNER JOIN PROJECTION_DETAILS PD
                                 --     ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
                                 INNER JOIN #RS_COMBINATION RC
                                         ON RC.CCP_DETAILS_SID = PP.CCP_DETAILS_SID
                                            AND RC.HIERARCHY_NO = PP.HIERARCHY_NO
                                            AND RC.LEVEL_NO = PP.LEVEL_NO
                                            AND COALESCE(PP.PARENT_HIERARCHY_NO, pp.HIERARCHY_NO) = COALESCE(RC.PARENT_HIERARCHY_NO, RC.HIERARCHY_NO)
												where exists (select 1 from #MULTISELECT_DISCOUNTS md  where md.ccp_details_sid=RC.CCP_DETAILS_SID 
						and md.RS_CONTRACT_SID=RC.RS_CONTRACT_SID)
                         /*WHERE EXISTS (
                         
                                                                  SELECT 1
                         
                                                                  FROM #RS_COMBINATION R
                         
                                                                  INNER JOIN RS_CONTRACT RS
                         
                                                                         ON RS.RS_CONTRACT_SID = R.RS_CONTRACT_SID
                         
                                                                  INNER JOIN HELPER_TABLE H
                         
                                                                         ON H.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE
                         
                                                                  WHERE H.DESCRIPTION <> 'PRICE PROTECTION' AND RC.DISCOUNT = R.DISCOUNT AND PP.PARENT_HIERARCHY_NO = R.PARENT_HIERARCHY_NO
                         
                                                                  )*/
                         ) S
                         INNER JOIN #PERIOD P
                                 ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                         LEFT JOIN NM_DISCOUNT_PROJECTION NPS
                                ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                   AND P.PERIOD_SID = NPS.PERIOD_SID
                                   AND NPS.RS_CONTRACT_SID = S.RS_CONTRACT_SID
                         INNER JOIN (select distinct * from #DEDUCTION_INCLUSION1) DI
                                 ON DI.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                    AND DI.RS_CONTRACT_SID = S.RS_CONTRACT_SID
                         LEFT JOIN #PRIOR_ACCRUAL_DISCOUNT1 AD
                                ON AD.PROJECTION_MASTER_SID = S.PROJECTION_MASTER_SID
                                   AND AD.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                   AND AD.RS_CONTRACT_SID = S.RS_CONTRACT_SID
                                   AND AD.HIERARCHY_NO = S.HIERARCHY_NO
                                   AND COALESCE(S.PARENT_HIERARCHY_NO, S.HIERARCHY_NO) = COALESCE(AD.PARENT_HIERARCHY_NO, AD.HIERARCHY_NO)
                                   AND AD.LEVEL_NO = S.LEVEL_NO
                                   AND AD.PERIOD_SID = P.PERIOD_SID
								   	where exists (select 1 from #MULTISELECT_DISCOUNTS md  where md.ccp_details_sid=di.CCP_DETAILS_SID 
						and md.RS_CONTRACT_SID=NPS.RS_CONTRACT_SID)
                  GROUP  BY s.PROJECTION_MASTER_SID,
                            S.HIERARCHY_NO,
                            S.LEVEL_NO,
                            DISCOUNT,
                            PERIOD,
                            [YEAR],
                            S.PARENT_HIERARCHY_NO,S.SELECTED_SID

                  IF Object_id('TEMPDB..#PRIOR_ACTUAL_PPA_DATA') IS NOT NULL
                    DROP TABLE #PRIOR_ACTUAL_PPA_DATA

                  /*
                  
                                       SELECT PROJECTION_MASTER_SID,
                  
                                             HIERARCHY_NO,
                  
                                             LEVEL_NAME,
                  
                                             DISCOUNT,
                  
                                             PERIOD,
                  
                                             [YEAR],
                  
                                             ISNULL(SUM(ACTUAL_DISCOUNT_DOLLAR), 0) AS CONTRACT_SALES_ACTUALS,
                  
                                             PARENT_HIERARCHY_NO
                  
                                       INTO #PRIOR_ACTUAL_PPA_DATA
                  
                                       FROM (
                  
                                             SELECT DISTINCT PP.PROJECTION_MASTER_SID,
                  
                                                    PP.HIERARCHY_NO,
                  
                                                    PP.LEVEL_NAME,
                  
                                                    RC.DISCOUNT,
                  
                                                    PD.PROJECTION_DETAILS_SID,
                  
                                                    PP.PARENT_HIERARCHY_NO
                  
                                             FROM #DPRIOR_PROJECTIONS PP
                  
                                             INNER JOIN PROJECTION_DETAILS PD
                  
                                                    ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
                  
                                             INNER JOIN #RS_COMBINATION RC
                  
                                                    ON RC.CCP_DETAILS_SID = PD.CCP_DETAILS_SID AND RC.HIERARCHY_NO = PP.HIERARCHY_NO AND RC.LEVEL_NAME = PP.LEVEL_NAME AND PP.PARENT_HIERARCHY_NO = RC.PARENT_HIERARCHY_NO
                  
                                             WHERE EXISTS (
                  
                                                           SELECT 1
                  
                                                           FROM #RS_COMBINATION R
                  
                                                           INNER JOIN RS_CONTRACT RS
                  
                                                                  ON RS.RS_CONTRACT_SID = R.RS_CONTRACT_SID
                  
                                                           INNER JOIN HELPER_TABLE H
                  
                                                                  ON H.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE
                  
                                                           WHERE H.DESCRIPTION = 'PRICE PROTECTION' AND RC.DISCOUNT = R.DISCOUNT AND PP.PARENT_HIERARCHY_NO = R.PARENT_HIERARCHY_NO
                  
                                                           )
                  
                                             ) S
                  
                                       INNER JOIN #PERIOD P
                  
                                             ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
                  
                                                           AND @END_PERIOD_SID
                  
                                       LEFT JOIN NM_ACTUAL_PPA NPS
                  
                                             ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID AND P.PERIOD_SID = NPS.PERIOD_SID
                  
                                       GROUP BY PROJECTION_MASTER_SID,
                  
                                             HIERARCHY_NO,
                  
                                             LEVEL_NAME,
                  
                                             DISCOUNT,
                  
                                             PERIOD,
                  
                                             [YEAR],
                  
                                             PARENT_HIERARCHY_NO
                  
                                             */
                  IF Object_id('TEMPDB..#PRIOR_PPA_DATA') IS NOT NULL
                    DROP TABLE #PRIOR_PPA_DATA

                  /*SELECT PROJECTION_MASTER_SID,
                  
                                             HIERARCHY_NO,
                  
                                             LEVEL_NAME,
                  
                                             DISCOUNT,
                  
                                             PERIOD,
                  
                                             [YEAR],
                  
                                             isnull(SUM(PROJECTION_DISCOUNT_DOLLAR), 0) AS CONTRACT_SALES_PROJECTED,
                  
                                             PARENT_HIERARCHY_NO
                  
                                       INTO #PRIOR_PPA_DATA
                  
                                       FROM (
                  
                                             SELECT DISTINCT PP.PROJECTION_MASTER_SID,
                  
                                                    PP.HIERARCHY_NO,
                  
                                                    PP.LEVEL_NAME,
                  
                                                    RC.DISCOUNT,
                  
                                                    PD.PROJECTION_DETAILS_SID,
                  
                                                    PP.PARENT_HIERARCHY_NO
                  
                                             FROM #DPRIOR_PROJECTIONS PP
                  
                                             INNER JOIN PROJECTION_DETAILS PD
                  
                                                    ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
                  
                                             INNER JOIN #RS_COMBINATION RC
                  
                                                    ON RC.CCP_DETAILS_SID = PD.CCP_DETAILS_SID AND RC.HIERARCHY_NO = PP.HIERARCHY_NO AND RC.LEVEL_NAME = PP.LEVEL_NAME AND PP.PARENT_HIERARCHY_NO = RC.PARENT_HIERARCHY_NO
                  
                                             WHERE EXISTS (
                  
                                                           SELECT 1
                  
                                                           FROM #RS_COMBINATION r
                  
                                                           INNER JOIN RS_CONTRACT rs
                  
                                                                  ON rs.RS_CONTRACT_SID = r.RS_CONTRACT_SID
                  
                                                           INNER JOIN HELPER_TABLE h
                  
                                                                  ON h.HELPER_TABLE_SID = rs.REBATE_PROGRAM_TYPE
                  
                                                           WHERE h.DESCRIPTION = 'price protection' AND rc.DISCOUNT = r.DISCOUNT AND PP.PARENT_HIERARCHY_NO = R.PARENT_HIERARCHY_NO
                  
                                                           )
                  
                                             ) S
                  
                                       INNER JOIN #PERIOD P
                  
                                             ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
                  
                                                           AND @END_PERIOD_SID
                  
                                       LEFT JOIN NM_PPA_PROJECTION NPS
                  
                                             ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID AND P.PERIOD_SID = NPS.PERIOD_SID
                  
                                       GROUP BY PROJECTION_MASTER_SID,
                  
                                             HIERARCHY_NO,
                  
                                             LEVEL_NAME,
                  
                                             DISCOUNT,
                  
                                             PERIOD,
                  
                                             [YEAR],
                  
                                             PARENT_HIERARCHY_NO
                  
                                             */
                  IF Object_id('TEMPDB.DBO.#PRIOR_SALES', 'U') IS NOT NULL
                    DROP TABLE #PRIOR_SALES;

                  SELECT FD.PROJECTION_MASTER_SID,
                         FD.YEAR,
                         FD.PERIOD,
                         FD.HIERARCHY_NO,
                         FD.DISCOUNT,
                         NM_ACTUAL_SALES = COALESCE(( NAS.CONTRACT_SALES_ACTUALS ), 0),
                         NM_ACTUAL_UNITS = COALESCE(( NAS.CONTRACT_UNITS_ACTUALS ), 0),
                         NM_PROJECTED_SALES = COALESCE(( NSP.CONTRACT_SALES_PROJECTED ), 0),
                         NM_PROJECTED_UNITS = COALESCE(( NSP.CONTRACT_UNITS_PROJECTED ), 0),
                         FD.PARENT_HIERARCHY_NO,
                         FD.LEVEL_NO,NSP.SELECTED_SID
                  INTO   #PRIOR_SALES
                  FROM   (SELECT DISTINCT YEAR,
                                          PERIOD,
                                          HIERARCHY_NO,
                                          DISCOUNT,
                                          PROJECTION_MASTER_SID,
                                          PARENT_HIERARCHY_NO,
                                          level_no
                          FROM   #DPRIOR_DATA_TABLE) FD
                         LEFT JOIN #DPRIOR_SALES_DATA NSP
                                ON FD.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID
                                   AND FD.YEAR = NSP.YEAR
                                   AND FD.PERIOD = NSP.PERIOD
                                   AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
                                   AND FD.DISCOUNT = NSP.DISCOUNT
                                   AND COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO) = COALESCE(NSP.PARENT_HIERARCHY_NO, NSP.HIERARCHY_NO) ---FD.PARENT_HIERARCHY_NO = NSP.PARENT_HIERARCHY_NO
                         LEFT JOIN #DPRIOR_ACTUAL_SALES_DATA NAS
                                ON FD.PROJECTION_MASTER_SID = NAS.PROJECTION_MASTER_SID
                                   AND FD.YEAR = NAS.YEAR
                                   AND FD.PERIOD = NAS.PERIOD
                                   AND FD.HIERARCHY_NO = NAS.HIERARCHY_NO
                                   AND FD.LEVEL_NO = NAS.LEVEL_NO
                                   AND FD.DISCOUNT = NAS.DISCOUNT
                                   AND COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO) = COALESCE(NAS.PARENT_HIERARCHY_NO, NAS.HIERARCHY_NO) ---NAS.PARENT_HIERARCHY_NO
                  IF Object_id('TEMPDB.DBO.#PRIOR_DISCOUNT', 'U') IS NOT NULL
                    DROP TABLE #PRIOR_DISCOUNT;

                  SELECT FD.PROJECTION_MASTER_SID,
                         FD.YEAR,
                         FD.PERIOD,
                         ACTUAL_SALES = COALESCE(( NAD.CONTRACT_SALES_ACTUALS ), 0),
                         PROJECTION_SALES = COALESCE(( NSP.CONTRACT_SALES_PROJECTED ), 0),
                         COALESCE(ACCRUAL_DISCOUNT_PROJ, ACCRUAL_DISCOUNT_ACTUAL) ACCRUAL_DISCOUNT,
                         FD.DISCOUNT,
                         FD.HIERARCHY_NO,
                         FD.LEVEL_NO,
                         FD.PARENT_HIERARCHY_NO,NSP.SELECTED_SID
                  INTO   #PRIOR_DISCOUNT
                  FROM   (SELECT YEAR,
                                 PERIOD,
                                 HIERARCHY_NO,
                                 LEVEL_NO,
                                 DISCOUNT,
                                 PROJECTION_MASTER_SID,
                                 PARENT_HIERARCHY_NO
                          FROM   #DPRIOR_DATA_TABLE A
                         /*WHERE EXISTS (
                         
                                                                  SELECT 1
                         
                                                                  FROM #PRIOR_DISCOUNT_DATA B
                         
                                                                  WHERE A.DISCOUNT = B.DISCOUNT
                         
                                                                  )*/
                         ) FD
                        JOIN #PRIOR_DISCOUNT_DATA NSP
                                ON FD.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID
                                   AND FD.YEAR = NSP.YEAR
                                   AND FD.PERIOD = NSP.PERIOD
                                   AND FD.DISCOUNT = NSP.DISCOUNT
                                   AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
                                   AND FD.LEVEL_NO = NSP.LEVEL_NO
                                   AND COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO) = COALESCE(NSP.PARENT_HIERARCHY_NO, NSP.HIERARCHY_NO)
                         LEFT JOIN #PRIOR_ACTUAL_DISCOUNT_DATA NAD
                                ON FD.PROJECTION_MASTER_SID = NAD.PROJECTION_MASTER_SID
                                   AND FD.YEAR = NAD.YEAR
                                   AND FD.PERIOD = NAD.PERIOD
                                   AND FD.DISCOUNT = NAD.DISCOUNT
                                   AND FD.HIERARCHY_NO = NAD.HIERARCHY_NO
                                   AND FD.LEVEL_NO = NAD.LEVEL_NO
                                   AND COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO) = COALESCE(NAD.PARENT_HIERARCHY_NO, NAD.HIERARCHY_NO) ---FD.PARENT_HIERARCHY_NO = NAD.PARENT_HIERARCHY_NO
                  IF Object_id('TEMPDB.DBO.#PRIOR_PPA', 'U') IS NOT NULL
                    DROP TABLE #PRIOR_PPA;

              /*
              
                                   SELECT FD.PROJECTION_MASTER_SID,
              
                                         FD.YEAR,
              
                                         FD.PERIOD,
              
                                         PPA_ACTUAL = COALESCE((NSP.CONTRACT_SALES_ACTUALS), 0),
              
                                         PPA_DISCOUNT_PROJECTED = COALESCE((NAP.CONTRACT_SALES_PROJECTED), 0),
              
                                         FD.DISCOUNT,
              
                                         FD.HIERARCHY_NO,
              
                                         FD.LEVEL_NAME,
              
                                         FD.PARENT_HIERARCHY_NO
              
                                   INTO #PRIOR_PPA
              
                                   FROM (
              
                                         SELECT YEAR,
              
                                                PERIOD,
              
                                                HIERARCHY_NO,
              
                                                LEVEL_NAME,
              
                                                DISCOUNT,
              
                                                PROJECTION_MASTER_SID,
              
                                                PARENT_HIERARCHY_NO
              
                                         FROM #DPRIOR_DATA_TABLE A
              
                                         WHERE NOT EXISTS (
              
                                                       SELECT 1
              
                                                       FROM #PRIOR_DISCOUNT_DATA B
              
                                                       WHERE A.DISCOUNT = B.DISCOUNT
              
                                                       )
              
                                         ) FD
              
                                   LEFT JOIN #PRIOR_ACTUAL_PPA_DATA NSP
              
                                         ON FD.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID AND fd.YEAR = NSP.YEAR AND FD.PERIOD = NSP.PERIOD AND FD.DISCOUNT = NSP.DISCOUNT AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO AND FD.LEVEL_NAME = NSP.LEVEL_NAME AND FD.PARENT_HIERARCHY_NO = NSP.PARENT_HIERARCHY_NO
              
                                   LEFT JOIN #PRIOR_PPA_DATA NAP
              
                                         ON FD.PROJECTION_MASTER_SID = NAP.PROJECTION_MASTER_SID AND FD.YEAR = NAP.YEAR AND FD.PERIOD = NAP.PERIOD AND FD.DISCOUNT = NAP.DISCOUNT AND FD.HIERARCHY_NO = NAP.HIERARCHY_NO AND FD.LEVEL_NAME = NAP.LEVEL_NAME AND FD.PARENT_HIERARCHY_NO = NAP.PARENT_HIERARCHY_NO
              
                                         */
                  --    select distinct * from   #DPIVOT_TABLE order by PROJECTION_MASTER_SID,HIERARCHY_NO,year,period
                  INSERT INTO #DPIVOT_TABLE
                              (PROJECTION_MASTER_SID,
                               DISCOUNT,
                               CP_INDICATOR,
                               DISCOUNT_AMOUNT,
                               DISCOUNT_AMOUNT_PROJECTED,
                               DISCOUNT_RATE,
                               DISCOUNT_RATE_PROJECTED,
                               DISCOUNT_RPU,
                               DISCOUNT_RPU_PROJECTED,
                               DISCOUNT_OF_EX_FACTORY_ACTUALS,
                               DISCOUNT_OF_EX_FACTORY_PROJECTED,
                               PERIOD,
                               YEAR,
                               HIERARCHY_NO,
                               LEVEL_NAME,
                               DISCOUNT_AMOUNT_ACCRUAL,
                               PARENT_HIERARCHY_NO,
                               level_no,SELECTED_SID)
                  SELECT DISTINCT s.PROJECTION_MASTER_SID,
                                  dt.DISCOUNT,
                                  @CP_INDICATOR    CP_INDICATOR,
                                  DISCOUNT_AMOUNT = Isnull(DT.ACTUAL_SALES, 0),
                                  DISCOUNT_AMOUNT_PROJECTED = Isnull(DT.PROJECTION_SALES, 0),
                                  DISCOUNT_RATE = Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_SALES, 0), 0) * 100,
                                  DISCOUNT_RATE_PROJECTED = Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_SALES, 0), 0) * 100,
                                  DISCOUNT_RPU = Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_UNITS, 0), 0),
                                  DISCOUNT_RPU = Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_UNITS, 0), 0),
                                  Isnull(DT.ACTUAL_SALES / NULLIF(F.GTS_SALES_ACTUALS, 0), 0) * 100,
                                  Isnull(DT.PROJECTION_SALES / NULLIF(F.GTS_SALES_PROJECTED, 0), 0) * 100,
                                  s.PERIOD,
                                  s.[YEAR],
                                  s.HIERARCHY_NO,
                                  NULL             AS LEVEL_NAME,
                                  ACCRUAL_DISCOUNT AS DISCOUNT_AMOUNT_ACCRUAL,
                                  S.PARENT_HIERARCHY_NO,
                                  DT.level_no,DT.SELECTED_SID
                  FROM   #PRIOR_SALES S
                         INNER JOIN #PRIOR_DISCOUNT DT
                                 ON S.PROJECTION_MASTER_SID = DT.PROJECTION_MASTER_SID
                                    AND S.level_no = DT.level_no
                                    AND DT.HIERARCHY_NO = S.HIERARCHY_NO
                                    --AND DT.LEVEL_NO = S.LEVEL_NO
                                    AND DT.PERIOD = S.PERIOD
                                    AND DT.[YEAR] = S.[YEAR]
                                    AND DT.DISCOUNT = S.DISCOUNT
									AND DT.SELECTED_SID = S.SELECTED_SID
                                    AND COALESCE(DT.PARENT_HIERARCHY_NO, DT.HIERARCHY_NO) = COALESCE(S.PARENT_HIERARCHY_NO, S.HIERARCHY_NO) ---DT.PARENT_HIERARCHY_NO = S.PARENT_HIERARCHY_NO
                         LEFT JOIN (SELECT PF.PROJECTION_MASTER_SID,
                                           P.PERIOD,
                                           P.YEAR,
                                           dp.HIERARCHY_NO,
                                           dp.LEVEL_NO,
                                           dp.DISCOUNT,
                                           GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                           GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                           DP.PARENT_HIERARCHY_NO
                                    FROM   #PRODUCT_FILE_TEMP PF
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = PF.PERIOD_SID
                                           INNER JOIN (SELECT DISTINCT DP.PROJECTION_MASTER_SID,
                                                                       dp.HIERARCHY_NO,
                                                                       dp.LEVEL_NO,
                                                                       rs.DISCOUNT,
                                                                       rs.ITEM_MASTER_SID,
                                                                       DP.PARENT_HIERARCHY_NO
                                                       FROM   #DPRIOR_PROJECTIONS DP
                                                              --INNER JOIN PROJECTION_DETAILS PD
                                                              --     ON PD.PROJECTION_MASTER_SID = DP.PROJECTION_MASTER_SID AND PD.PROJECTION_DETAILS_SID = DP.PROJECTION_DETAILS_SID
                                                              INNER JOIN #RS_COMBINATION RS
                                                                      ON RS.CCP_DETAILS_SID = DP.CCP_DETAILS_SID
                                                                         AND COALESCE(DP.PARENT_HIERARCHY_NO, DP.HIERARCHY_NO) = COALESCE(RS.PARENT_HIERARCHY_NO, RS.HIERARCHY_NO)
                                                                         AND RS.HIERARCHY_NO = DP.HIERARCHY_NO
                                                                         AND RS.LEVEL_NO = DP.LEVEL_NO) DP
                                                   ON DP.PROJECTION_MASTER_SID = PF.PROJECTION_MASTER_SID
                                                      AND DP.ITEM_MASTER_SID = PF.ITEM_MASTER_SID
                                    GROUP  BY PF.PROJECTION_MASTER_SID,
                                              dp.HIERARCHY_NO,
                                              dp.LEVEL_NO,
                                              dp.DISCOUNT,
                                              P.PERIOD,
                                              P.YEAR,
                                              DP.PARENT_HIERARCHY_NO) F
                                ON F.PROJECTION_MASTER_SID = DT.PROJECTION_MASTER_SID
                                   AND F.YEAR = DT.YEAR
                                   AND F.PERIOD = DT.PERIOD
                                   AND F.HIERARCHY_NO = DT.HIERARCHY_NO
                                   AND COALESCE(dt.PARENT_HIERARCHY_NO, dt.HIERARCHY_NO) = COALESCE(f.PARENT_HIERARCHY_NO, f.HIERARCHY_NO)
                                   AND F.LEVEL_NO = DT.LEVEL_NO
                                   AND F.DISCOUNT = DT.DISCOUNT
								   
              /*UNION ALL
              
                                  
              
                                   SELECT P.PROJECTION_MASTER_SID,
              
                                         P.DISCOUNT,
              
                                         @CP_INDICATOR CP_INDICATOR,
              
                                         PPA_ACTUAL,
              
                                         DISCOUNT_AMOUNT_PROJECTED = P.PPA_DISCOUNT_PROJECTED,
              
                                         PPA_RATE = isnull(PPA_ACTUAL / nullif(s.NM_ACTUAL_SALES, 0), 0) * 100,
              
                                         PPA_RATE_PROJECTION = isnull(PPA_DISCOUNT_PROJECTED / nullif(s.NM_ACTUAL_SALES, 0), 0) * 100,
              
                                         PPA_RPU = isnull(PPA_ACTUAL / nullif(s.NM_PROJECTED_SALES, 0), 0),
              
                                         PPA_RPU_PROJECTION = isnull(PPA_DISCOUNT_PROJECTED / nullif(s.NM_PROJECTED_UNITS, 0), 0),
              
                                         Isnull(P.PPA_ACTUAL / NULLIF(F.GTS_SALES_ACTUALS, 0), 0) * 100,
              
                                         Isnull(P.PPA_DISCOUNT_PROJECTED / NULLIF(F.GTS_SALES_PROJECTED, 0), 0) * 100,
              
                                         P.PERIOD,
              
                                         P.[YEAR],
              
                                         P.HIERARCHY_NO,
              
                                         P.LEVEL_NAME,
              
                                         NULL AS DISCOUNT_AMOUNT_ACCRUAL,
              
                                         P.PARENT_HIERARCHY_NO
              
                                   FROM #PRIOR_PPA P
              
                                   INNER JOIN #PRIOR_SALES S
              
                                         ON P.PROJECTION_MASTER_SID = S.PROJECTION_MASTER_SID AND S.HIERARCHY_NO = P.HIERARCHY_NO AND P.LEVEL_NAME = S.LEVEL_NAME AND P.PARENT_HIERARCHY_NO = S.PARENT_HIERARCHY_NO AND P.YEAR = S.YEAR AND P.PERIOD = S.PERIOD AND P.DISCOUNT = S.DISCOUNT
              
                                   LEFT JOIN (
              
                                         SELECT PF.PROJECTION_MASTER_SID,
              
                                                P.PERIOD,
              
                                                P.YEAR,
              
                                                dp.HIERARCHY_NO,
              
                                                dp.LEVEL_NAME,
              
                                                dp.DISCOUNT,
              
                                                GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
              
                                                GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
              
                                                DP.PARENT_HIERARCHY_NO
              
                                         FROM #PRODUCT_FILE_TEMP PF
              
                                         INNER JOIN #PERIOD P
              
                                                ON P.PERIOD_SID = PF.PERIOD_SID
              
                                         INNER JOIN (
              
                                                SELECT DISTINCT DP.PROJECTION_MASTER_SID,
              
                                                       DP.HIERARCHY_NO,
              
                                                       DP.LEVEL_NAME,
              
                                                       RS.ITEM_MASTER_SID,
              
                                                       RS.DISCOUNT,
              
                                                       DP.PARENT_HIERARCHY_NO
              
                                                FROM #DPRIOR_PROJECTIONS DP
              
                                                INNER JOIN PROJECTION_DETAILS PD
              
                                                       ON PD.PROJECTION_MASTER_SID = DP.PROJECTION_MASTER_SID AND PD.PROJECTION_DETAILS_SID = DP.PROJECTION_DETAILS_SID
              
                                                INNER JOIN #RS_COMBINATION RS
              
                                                       ON RS.CCP_DETAILS_SID = PD.CCP_DETAILS_SID AND RS.PARENT_HIERARCHY_NO = DP.PARENT_HIERARCHY_NO AND RS.HIERARCHY_NO = DP.HIERARCHY_NO AND RS.LEVEL_NAME = DP.LEVEL_NAME
              
                                                ) dp
              
                                                ON dp.PROJECTION_MASTER_SID = pf.PROJECTION_MASTER_SID AND dp.ITEM_MASTER_SID = pf.ITEM_MASTER_SID
              
                                         GROUP BY PF.PROJECTION_MASTER_SID,
              
                                                dp.HIERARCHY_NO,
              
                                                dp.LEVEL_NAME,
              
                                                dp.DISCOUNT,
              
                                                P.PERIOD,
              
                                                P.YEAR,
              
                                                DP.PARENT_HIERARCHY_NO
              
                                         ) F
              
                                         ON F.PROJECTION_MASTER_SID = P.PROJECTION_MASTER_SID AND F.YEAR = P.YEAR AND F.PERIOD = P.PERIOD AND F.HIERARCHY_NO = P.HIERARCHY_NO AND F.LEVEL_NAME = P.LEVEL_NAME AND F.DISCOUNT = P.DISCOUNT AND F.PARENT_HIERARCHY_NO = P.PARENT_HIERARCHY_NO*/
              END

            IF @SCREEN_NAME = 'DISCOUNT'
              BEGIN
                  --                         IF Object_id('TEMPDB..#DATA_TABLE122') IS NOT NULL
                  --     DROP TABLE #DATA_TABLE122
                  --SELECT HIERARCHY_NO,
                  -- PARENT_HIERARCHY_NO,
                  --     LEVEL_NAME,
                  --     PERIOD,
                  --     YEAR,
                  --     PERIOD_SID,
                  --     CCP_DETAILS_SID
                  --INTO #DATA_TABLE122
                  --FROM (
                  --     SELECT DISTINCT HIERARCHY_NO,
                  --            LEVEL_NAME,
                  --            PARENT_HIERARCHY_NO,
                  --            CCP_DETAILS_SID
                  --     FROM #CCP
                  --     ) C
                  --CROSS JOIN (
                  --     SELECT DISTINCT PERIOD,
                  --            YEAR,
                  --            PERIOD_SID
                  --     FROM #PERIOD
                  --     WHERE PERIOD_SID BETWEEN @PROJ_START_SID
                  --                   AND @PROJ_END_SID
                  --     ) P
                  IF Object_id('TEMPDB.DBO.#CURRENT_DISCOUNT1', 'U') IS NOT NULL
                    DROP TABLE #CURRENT_DISCOUNT1;

                  SELECT DISTINCT a.YEAR,
                                  a.PERIOD,
                                  A.DISCOUNT,
                                  ACTUAL_SALES,
                                  PROJECTION_SALES,
                                  A.HIERARCHY_NO,
                                  A.PARENT_HIERARCHY_NO,
                                  --  --A.LEVEL_NAME,
                                  APTS,
                                  SELECTED_SID,
                                  DEDUCTION_INCLUSION,
                                  GROWTH,
                                  USER_GROUP,
                                  LEVEL_NO
                  INTO   #CURRENT_DISCOUNT1
                  FROM   (SELECT *
                          FROM   (SELECT DISTINCT PERIOD,
                                                  YEAR,
                                                  DISCOUNT,
                                                  HIERARCHY_NO,
                                                  PARENT_HIERARCHY_NO,
                                                  --LEVEL_NAME,
                                                  LEVEL_NO,
                                                  SELECTED_SID
                                  FROM   #DISCOUNT_DATA_TABLE D) FD
                                 LEFT JOIN (SELECT CONTRACT_SALES_PROJECTED,
                                                   DISCOUNT            AS DISCOUNT1,
                                                   HIERARCHY_NO        AS HIERARCHY_NO_1,
                                                   LEVEL_NO            AS LEVEL_NAME1,
                                                   PERIOD              AS PERIOD1,
                                                   YEAR                AS YEAR1,
                                                   PARENT_HIERARCHY_NO PARENT_HIERARCHY_NO1,
                                                   1                   AP,
                                                   SELECTED_SID        AS SELECTED_SID1,
                                                   DEDUCTION_INCLUSION,
                                                   GROWTH,
                                                   USER_GROUP
                                            FROM   #DISCOUNT_DATA) NSP
                                        ON FD.YEAR = NSP.YEAR1
                                           AND FD.PERIOD = NSP.PERIOD1
                                           AND Isnull(FD.DISCOUNT, '') = Isnull(NSP.DISCOUNT1, '')
                                           AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO_1
                                           AND Isnull(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO) = Isnull(NSP.PARENT_HIERARCHY_NO1, NSP.HIERARCHY_NO_1)
                                           AND FD.LEVEL_NO = NSP.LEVEL_NAME1
                                           AND FD.SELECTED_SID = NSP.SELECTED_SID1
                                 LEFT JOIN (SELECT CONTRACT_SALES_ACTUALS,
                                                   DISCOUNT            AS DISCOUNT2,
                                                   HIERARCHY_NO        AS HIERARCHY_NO_2,
                                                   LEVEL_NO            AS LEVEL_NAME2,
                                                   PERIOD              AS PERIOD2,
                                                   YEAR                AS YEAR2,
                                                   PARENT_HIERARCHY_NO AS PARENT_HIERARCHY_NO2,
                                                   0                   TS,
                                                   SELECTED_SID        AS SELECTED_SID2
                                            FROM   #DISCOUNT_ACTUAL_DATA) NAD
                                        ON NAD.YEAR2 = FD.YEAR
                                           AND NAD.PERIOD2 = FD.PERIOD
                                           AND Isnull(FD.DISCOUNT, '') = Isnull(NAD.DISCOUNT2, '')
                                           AND NAD.HIERARCHY_NO_2 = FD.HIERARCHY_NO
                                           AND Isnull(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO) = Isnull(NAD.PARENT_HIERARCHY_NO2, NAD.HIERARCHY_NO_2)
                                           AND NAD.LEVEL_NAME2 = FD.LEVEL_NO
                                           AND FD.SELECTED_SID = NAD.SELECTED_SID2) A
                         CROSS APPLY ( VALUES ( [CONTRACT_SALES_ACTUALS],
                                     [CONTRACT_SALES_PROJECTED],
                                     [AP],
                                     YEAR,
                                     PERIOD1 ),
                                              ( [CONTRACT_SALES_ACTUALS],
                                     [CONTRACT_SALES_PROJECTED],
                                     [TS],
                                     YEAR2,
                                     PERIOD2 ) ) B(ACTUAL_SALES, PROJECTION_SALES, APTS, YEAR, PERIOD)
                  WHERE  a.YEAR IS NOT NULL
                         AND a.PERIOD IS NOT NULL
                         AND b.APTS IS NOT NULL

                  IF Object_id('TEMPDB.DBO.#DD', 'U') IS NOT NULL
                    DROP TABLE #DD;

                  SELECT DT.HIERARCHY_NO,
                         DT.PERIOD,
                         DT.[YEAR],
                         APTS AS ACTUAL_PROJ,
                         DT.PARENT_HIERARCHY_NO,
                         CASE
                           WHEN @SCREEN_NAME = 'DISCOUNT'
                                AND @CP_INDICATOR = 'D' THEN DT.DISCOUNT
                           ELSE DT.SELECTED_SID
                         END  AS DISCOUNT,
                         DISCOUNT_AMOUNT_ACTUAL = Isnull(DT.ACTUAL_SALES, 0),
                         DISCOUNT_RATE_ACTUAL = Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_SALES, 0), 0) * 100,
                         DISCOUNT_RPU = Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_UNITS, 0), 0),
                         DISCOUNT_AMOUNT_PROJECTED = Isnull(DT.PROJECTION_SALES, 0),
                         DISCOUNT_RATE_PROJECTED = Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_SALES, 0), 0) * 100,
                         DISCOUNT_RPU_PROJECTED = Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_UNITS, 0), 0),
                         DEDUCTION_INCLUSION,
                         --DT.LEVEL_NAME,
                         GROWTH,
                         USER_GROUP,
                         S.LEVEL_no
                  INTO   #DD
                  FROM   #CURRENT_DISCOUNT1 dt
                         LEFT JOIN #CURRENT_SALES s
                                ON S.HIERARCHY_NO = DT.HIERARCHY_NO
                                   AND S.LEVEL_no = DT.LEVEL_no
                                   --AND S.LEVEL_NAME = DT.LEVEL_NAME
                                   AND S.YEAR = DT.YEAR
                                   AND S.PERIOD = DT.PERIOD
                                   AND COALESCE(s.PARENT_HIERARCHY_NO, s.HIERARCHY_NO) = COALESCE(dt.PARENT_HIERARCHY_NO, DT.HIERARCHY_NO)
                  ORDER  BY dt.level_no,
                            DT.HIERARCHY_NO,
                            COALESCE(dt.PARENT_HIERARCHY_NO, DT.HIERARCHY_NO),
                            dt.YEAR,
                            dt.PERIOD

                  SELECT A.HIERARCHY_NO,
                         A.PERIOD,
                         A.[YEAR],
                         A.ACTUAL_PROJ,
                         C.PARENT_HIERARCHY_NO,
                         A.DISCOUNT,
                         A.DISCOUNT_AMOUNT_ACTUAL,
                         A.DISCOUNT_RATE_ACTUAL,
                         A.DISCOUNT_RPU,
                         A.DISCOUNT_AMOUNT_PROJECTED,
                         A.DISCOUNT_RATE_PROJECTED,
                         A.DISCOUNT_RPU_PROJECTED,
                         A.DEDUCTION_INCLUSION,
                         c.LEVEL_NAME,
                         A.GROWTH,
                         A.USER_GROUP
                  FROM   #DD A
                         INNER JOIN #CCP C
                                 ON A.HIERARCHY_NO = C.HIERARCHY_NO
                                    AND COALESCE(C.PARENT_HIERARCHY_NO, C.HIERARCHY_NO) = COALESCE(a.PARENT_HIERARCHY_NO, a.HIERARCHY_NO) ----A.PARENT_HIERARCHY_NO
                                    AND a.level_no = c.level_no
                  ORDER  BY c.level_no,
                            a.HIERARCHY_NO,
                            c.PARENT_HIERARCHY_NO,
                            YEAR,
                            PERIOD
              END
            ELSE
              BEGIN
                  DECLARE @SQL2       NVARCHAR(MAX),
                          @LOOP_CNTR1 INT,
                          @MAX_CCP1   INT

                  SET @SQL2 = '

                SELECT  DISCOUNT,pr.HIERARCHY_NO,

                   PERIOD,

                   YEAR, '
                  SET @LOOP_CNTR1 = 1
                  SET @MAX_CCP1 = (SELECT Max(ID)
                                   FROM   #PROJECTION_MASTER)

                  WHILE @LOOP_CNTR1 <= @MAX_CCP1
                    BEGIN
                        SET @SQL2 += 'DISCOUNT_AMOUNT_ACCRUAL_'
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' = MAX(CASE WHEN ID = '
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' THEN IIF(DISCOUNT_AMOUNT_ACCRUAL=0,NULL,DISCOUNT_AMOUNT_ACCRUAL) END),

                                  DISCOUNT_AMOUNT_'
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' = MAX(CASE WHEN ID = '
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' THEN DISCOUNT_AMOUNT END),

                                  DISCOUNT_AMOUNT_PROJECTED_'
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' = ISNULL(MAX(CASE WHEN ID = '
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' THEN DISCOUNT_AMOUNT_PROJECTED END),0),

                                                         DISCOUNT_RATE_ACCRUAL_'
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' = MAX(CASE WHEN ID = '
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' THEN DISCOUNT_RATE_ACCRUAL END),

                                                         DISCOUNT_RATE_'
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' = MAX(CASE WHEN ID = '
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' THEN DISCOUNT_RATE END),

                                                         DISCOUNT_RATE_PROJECTED_'
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' = ISNULL(MAX(CASE WHEN ID = '
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' THEN DISCOUNT_RATE_PROJECTED END),0),

                                  DISCOUNT_RPU_ACCRUAL_'
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' = MAX(CASE WHEN ID = '
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' THEN DISCOUNT_RPU_ACCRUAL END),

                                  DISCOUNT_RPU_'
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' = MAX(CASE WHEN ID = '
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' THEN DISCOUNT_RPU END),

                                  DISCOUNT_RPU_PROJECTED_'
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' = ISNULL(MAX(CASE WHEN ID = '
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' THEN DISCOUNT_RPU_PROJECTED END),0),

                                  DISCOUNT_OF_EX_FACTORY_ACRUAL_'
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' = MAX(CASE WHEN ID = '
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' THEN DISCOUNT_OF_EX_FACTORY_ACRUAL END),

                                  DISCOUNT_OF_EX_FACTORY_ACTUALS_'
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' = MAX(CASE WHEN ID = '
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),

                                  DISCOUNT_OF_EX_FACTORY_PROJECTED_'
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' = ISNULL(MAX(CASE WHEN ID = '
                                     + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                     + ' THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0),'
                        SET @LOOP_CNTR1 += 1
                    END

                  --SET @SQL2 = LEFT(@SQL2, Len(@SQL2) - 1)
                  SET @SQL2 += ' pr.PARENT_HIERARCHY_NO ,PR.SELECTED_SID   FROM   #DPIVOT_TABLE PR JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID  = PR.PROJECTION_MASTER_SID

                     --join #CCP c on pr.HIERARCHY_NO = c.HIERARCHY_NO

                                  -- AND COALESCE(C.PARENT_HIERARCHY_NO,C.HIERARCHY_NO)=PR.PARENT_HIERARCHY_NO

                GROUP  BY pr.level_no,DISCOUNT,pr.HIERARCHY_NO,CP_INDICATOR,PERIOD,YEAR       ,pr.PARENT_HIERARCHY_NO  ,PR.SELECTED_SID                      

                ORDER BY pr.level_no,pr.HIERARCHY_NO,pr.PARENT_HIERARCHY_NO  ,PR.SELECTED_SID   ,'
                               + CASE WHEN ( @VIEW_TAB = 'PIVOT' AND @VIEW = 'DETAIL_DISCOUNT' ) THEN 'YEAR,PERIOD' ELSE 'DISCOUNT,YEAR,PERIOD' END

                  EXEC Sp_executesql
                    @SQL2
              END
        END
  END 

END 
GO




