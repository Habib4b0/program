IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_PROJECTION_VARIANCE'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_PROJECTION_VARIANCE]
  END

GO



CREATE PROCEDURE [dbo].[PRC_PROJECTION_VARIANCE] (@PROJECTION_SID         VARCHAR(500),
                                                 @USER_ID                INT,
                                                 @SESSION_ID             VARCHAR(50),
                                                 @DISCOUNT_LEVEL         VARCHAR(50),
                                                 @FREQUENCY              CHAR(1),
                                                 @CP_INDICATOR           CHAR(1),
                                                 @GROUP_FILTER           VARCHAR(50),
                                                 @GROUP_FILTER_VALUE     VARCHAR(50),
                                                 @HIERARCHY_NO           VARCHAR(500) = NULL,
                                                 @LEVEL_NO               INT = NULL,
                                                 @VIEW                   VARCHAR(50),
                                                 @CUSTOM_VIEW_MASTER_SID INT = NULL,
                                                 @VIEW_TAB               VARCHAR(50))
                                                
AS
BEGIN
       DECLARE @FROM_DATE DATE
              ,@STARTFROM DATE
              ,@PROJECTION_DATE DATE
              ,@ACTUALPERIODS INT
              ,@PROJECTIONPERIODS INT
              --, @ITEM_ID VARCHAR(50)
              ,@NDC8 VARCHAR(200)
              ,@SP INT
              ,@SP_PROJ_SID INT
              ,@TEMP_PROJ_SID VARCHAR(500)
              ,@START_PERIOD_SID INT
              ,@END_PERIOD_SID INT
              ,@ITEM_UDT UDT_ITEM
              ,@FORECAST_NAME VARCHAR(50)
              ,@FORECAST_VERSION VARCHAR(15)
              ,@FORECAST_NAME_INV VARCHAR(50)
              ,@FORECAST_VERSION_INV VARCHAR(15)
              ,@FORECAST_NAME_DM VARCHAR(50)
              ,@FORECAST_VERSION_DM VARCHAR(15)
              ,@CUST_RELATIONSHIP_BUILDER_SID INT
              ,@BUSINESS_UNIT INT -----------GAL-808
              ,@COMPANY INT -----GAL-808
       DECLARE @D_MASTER_TABLE VARCHAR(200) = CONCAT (
                     'ST_NM_DISCOUNT_PROJ_MASTER_'
                     ,@USER_ID
                     ,'_'
                     ,@SESSION_ID
                     ,'_'
                     ,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
                     )
              ,@D_ACTUAL_TABLE VARCHAR(200) = CONCAT (
                     'ST_NM_ACTUAL_DISCOUNT_'
                     ,@USER_ID
                     ,'_'
                     ,@SESSION_ID
                     ,'_'
                     ,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
                     )
              ,@D_PROJECTION_TABLE VARCHAR(200) = CONCAT (
                     'ST_NM_DISCOUNT_PROJECTION_'
                     ,@USER_ID
                     ,'_'
                     ,@SESSION_ID
                     ,'_'
                     ,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
                     )
       DECLARE @S_MASTER_TABLE VARCHAR(200) = CONCAT (
                     'ST_NM_SALES_PROJECTION_MASTER_'
                     ,@USER_ID
                     ,'_'
                     ,@SESSION_ID
                     ,'_'
                     ,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
                     )
              ,@S_ACTUAL_TABLE VARCHAR(200) = CONCAT (
                     'ST_NM_ACTUAL_SALES_'
                     ,@USER_ID
                     ,'_'
                     ,@SESSION_ID
                     ,'_'
                     ,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
                     )
              ,@S_PROJECTION_TABLE VARCHAR(200) = CONCAT (
                     'ST_NM_SALES_PROJECTION_'
                     ,@USER_ID
                     ,'_'
                     ,@SESSION_ID
                     ,'_'
                     ,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
                     )
              ,@P_PROJECTION_TABLE VARCHAR(200) = CONCAT (
                     'ST_NM_PPA_PROJECTION_'
                     ,@user_id
                     ,'_'
                     ,@session_id
                     ,'_'
                     ,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
                     )
              ,@P_MASTER_TABLE VARCHAR(200) = CONCAT (
                     'ST_NM_PPA_PROJECTION_MASTER_'
                     ,@USER_ID
                     ,'_'
                     ,@SESSION_ID
                     ,'_'
                     ,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
                     )
              ,@P_ACTUAL_TABLE VARCHAR(200) = CONCAT (
                     'ST_NM_ACTUAL_PPA_'
                     ,@USER_ID
                     ,'_'
                     ,@SESSION_ID
                     ,'_'
                     ,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
                     )
              ,@CCP_HIERARCHY VARCHAR(200) = CONCAT (
                     'ST_CCP_HIERARCHY_'
                     ,@USER_ID
                     ,'_'
                     ,@SESSION_ID
                     ,'_'
                     ,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
                     )
              ,@PRODUCT_TABLE VARCHAR(MAX) = CONCAT (
                     'ST_PRODUCT_FILE_'
                     ,@USER_ID
                     ,'_'
                     ,@SESSION_ID
                     ,'_'
                     ,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
                     )
 
       IF Object_id('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL
              DROP TABLE #ccp_details_temp
 
       CREATE TABLE #CCP_DETAILS_TEMP (
              CCP_DETAILS_SID INT
              ,COMPANY_MASTER_SID INT
              ,ITEM_MASTER_SID INT
              ,CONTRACT_MASTER_SID INT
              )
 
       EXEC (
                     'INSERT INTO #CCP_DETAILS_TEMP(CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID)
                                           SELECT ch.CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID FROM ' + @CCP_HIERARCHY + ' CH JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = Cd.CCP_DETAILS_SID '
                     )
 
       IF Object_id('TEMPDB..#PROJECTION_MASTER') IS NOT NULL
              TRUNCATE TABLE #PROJECTION_MASTER
       ELSE
              CREATE TABLE #PROJECTION_MASTER (
                     ID INT IDENTITY(1, 1)
                     ,PROJECTION_MASTER_SID INT
                     )
 
       IF Object_id('TEMPDB..#PIVOT_RESULT') IS NOT NULL
              TRUNCATE TABLE #PIVOT_RESULT
       ELSE
              CREATE TABLE #PIVOT_RESULT (
                     PROJECTION_ID INT
                     ,HIERARCHY_NO VARCHAR(100)
                     ,LEVEL_NAME VARCHAR(100)
                     ,[PERIOD] SMALLINT
                     ,[YEAR] INT
                     ,CP_INDICATOR CHAR(1)
                     ,EX_FACTORY_SALES_ACTUALS NUMERIC(22, 6)
                     ,EX_FACTORY_SALES_PROJECTED NUMERIC(22, 6)
                     ,DEMAND_SALES_ACTUALS NUMERIC(22, 6)
                     ,DEMAND_SALES_PROJECTED NUMERIC(22, 6)
                     ,INVENTORY_WITHDRAWAL_SALES_ACTUALS NUMERIC(22, 6)
                     ,INVENTORY_WITHDRAWAL_SALES_PROJECTED NUMERIC(22, 6)
                     ,EX_FACTORY_SALES_ACTUALS_PERCENT NUMERIC(38, 15)
                     ,EX_FACTORY_SALES_PROJECTED_PERCENT NUMERIC(38, 15)
                     ,DEMAND_SALES_ACTUALS_PERCENT NUMERIC(38, 15)
                     ,DEMAND_SALES_PROJECTED_PERCENT NUMERIC(38, 15)
                     ,INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT NUMERIC(38, 15)
                     ,INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT NUMERIC(38, 15)
                     ,CONTRACT_SALES_ACTUALS NUMERIC(38, 15)
                     ,CONTRACT_SALES_PROJECTED NUMERIC(38, 15)
                     ,CONTRACT_UNITS_ACTUALS NUMERIC(38, 15)
                     ,CONTRACT_UNITS_PROJECTED NUMERIC(38, 15)
                     ,TOTAL_DISCOUNT_ACTUALS NUMERIC(38, 15)
                     ,TOTAL_DISCOUNT_PROJECTED NUMERIC(38, 15)
                     ,TOTAL_DISCOUNT_ACTUALS_PERCENTAGE NUMERIC(38, 15)
                     ,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE NUMERIC(38, 15)
                     ,TOTAL_RPU_ACTUALS NUMERIC(22, 6)
                     ,TOTAL_RPU_PROJECTED NUMERIC(22, 6)
                     ,NET_SALES_ACTUALS NUMERIC(38, 15)
                     ,NET_SALES_PROJECTED NUMERIC(38, 15)
                     ,COGS_ACTUALS NUMERIC(22, 6)
                     ,COGS_PROJECTED NUMERIC(22, 6)
                     ,NET_PROFIT_ACTUALS NUMERIC(22, 6)
                     ,NET_PROFIT_PROJECTED NUMERIC(22, 6)
                     ,NET_SALES_OF_EX_FACTORY_ACTUALS NUMERIC(38, 15)
                     ,NET_SALES_OF_EX_FACTORY_PROJECTED NUMERIC(38, 15)
                     ,DISCOUNT_OF_EX_FACTORY_ACTUALS NUMERIC(22, 6)
                     ,DISCOUNT_OF_EX_FACTORY_PROJECTED NUMERIC(22, 6)
                     )
 
       IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
              DROP TABLE #PERIOD;
 
       SELECT PERIOD_SID
              ,PERIOD_DATE
              ,MONTH
              ,QUARTER
              ,SEMI_ANNUAL
              ,YEAR
              ,PERIOD = CASE
                     WHEN LEFT(@FREQUENCY, 1) = 'M'
                           THEN MONTH
                     WHEN LEFT(@FREQUENCY, 1) = 'Q'
                           THEN QUARTER
                     WHEN LEFT(@FREQUENCY, 1) = 'S'
                           THEN SEMI_ANNUAL
                     ELSE 01
                     END
       INTO #PERIOD
       FROM PERIOD
 
       SET @TEMP_PROJ_SID = Replace(@PROJECTION_SID + ',', ',,', ',')
 
       WHILE Patindex('%,%', @TEMP_PROJ_SID) <> 0
       BEGIN
              SELECT @SP = Patindex('%,%', @TEMP_PROJ_SID)
 
              SELECT @SP_PROJ_SID = LEFT(@TEMP_PROJ_SID, @SP - 1)
 
              SELECT @TEMP_PROJ_SID = Stuff(@TEMP_PROJ_SID, 1, @SP, '')
 
              INSERT INTO #PROJECTION_MASTER (PROJECTION_MASTER_SID)
              SELECT @SP_PROJ_SID
       END
 
       DECLARE @FIRST_PROJ_SID INT
              ,@PROJ_START_PERIOD_SID INT
              ,@START_DATE DATETIME
              ,@PROD_RELATIONSHIP_BUILDER_SID INT
              ,@MIN_LEVEL INT
              ,@CURRENT_DATE DATE
 
       SELECT @FIRST_PROJ_SID = PM.PROJECTION_MASTER_SID
       FROM #PROJECTION_MASTER PM
       WHERE ID = 1
 
       SELECT TOP 1 @STARTFROM = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, - 3, Getdate())), 0)
              ,@START_DATE = Dateadd(dd, 1, Eomonth(FROM_DATE, - 1))
              ,@PROJECTION_DATE = Dateadd(dd, 1, Eomonth(TO_DATE, - 1))
              ,@CUST_RELATIONSHIP_BUILDER_SID = CUST_RELATIONSHIP_BUILDER_SID
              ,@PROD_RELATIONSHIP_BUILDER_SID = PROD_RELATIONSHIP_BUILDER_SID
              ,@MIN_LEVEL = CASE @CP_INDICATOR
                     WHEN 'C'
                           THEN CUSTOMER_HIERARCHY_LEVEL
                     WHEN 'P'
                           THEN PRODUCT_HIERARCHY_LEVEL
                     END
              ,@BUSINESS_UNIT = BUSINESS_UNIT
              ,-----GAL-808
              @COMPANY = COMPANY_MASTER_SID -----GAL-808
       FROM PROJECTION_MASTER
       WHERE PROJECTION_MASTER_SID = @FIRST_PROJ_SID
 
       SELECT @START_PERIOD_SID = Max(CASE
                           WHEN PERIOD_DATE = @STARTFROM
                                  THEN PERIOD_SID
                           END)
              ,@END_PERIOD_SID = Max(CASE
                           WHEN PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0)
                                  THEN PERIOD_SID
                           END)
              ,@PROJ_START_PERIOD_SID = Max(CASE
                           WHEN PERIOD_DATE = @START_DATE
                                  THEN PERIOD_SID
                           END)
       FROM PERIOD
       WHERE PERIOD_DATE IN (
                     @STARTFROM
                     ,@PROJECTION_DATE
                     ,@START_DATE
                     )
 
       SET @ACTUALPERIODS = Datediff(YY, @FROM_DATE, @STARTFROM)
       SET @PROJECTIONPERIODS = Datediff(YY, @STARTFROM, @PROJECTION_DATE)
 
       IF Object_id('TEMPDB..#CCP') IS NOT NULL
              DROP TABLE #CCP
 
       CREATE TABLE #CCP (
              CCP_DETAILS_SID INT
              ,HIERARCHY_NO VARCHAR(50)
              ,LEVEL_NAME VARCHAR(100)
              ,PARENT_HIERARCHY_NO VARCHAR(50)
              ,level_no INT
              )
 
       IF @CP_INDICATOR IN (
                     'C'
                     ,'P'
                     )
       BEGIN
              DECLARE @RELATIONSHIP_BUILDER_SID INT
 
              SET @RELATIONSHIP_BUILDER_SID = CASE @CP_INDICATOR
                           WHEN 'C'
                                  THEN @CUST_RELATIONSHIP_BUILDER_SID
                           ELSE @PROD_RELATIONSHIP_BUILDER_SID
                           END
 
              DECLARE @SQL1 NVARCHAR(MAX)
 
              SET @SQL1 = CONCAT (
                           'SELECT DISTINCT HIERARCHY_NO,
       CH.CCP_DETAILS_SID,
       LEVEL_NAME
FROM   RELATIONSHIP_LEVEL_DEFINITION RLD
       JOIN '
                           ,@CCP_HIERARCHY
                           ,' CH
         ON ( CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
              AND @CP_INDICATOR = ''C'' )
             OR ( CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
                  AND @CP_INDICATOR = ''P'' )
  '
                           ,CASE @GROUP_FILTER
                                  WHEN 'SALES'
                                         THEN '
                                         JOIN ' + @S_MASTER_TABLE + ' S ON S.CCP_DETAILS_SID = CH.CCP_DETAILS_SID'
                                  WHEN 'DISCOUNT'
                                         THEN '
                                         JOIN ' + @D_MASTER_TABLE + ' S ON S.CCP_DETAILS_SID = CH.CCP_DETAILS_SID'
                                  WHEN 'PPA'
                                         THEN '
                                         JOIN ' + @P_MASTER_TABLE + ' S ON S.CCP_DETAILS_SID = CH.CCP_DETAILS_SID'
                                  END
                           ,'
                         
                WHERE  (S.USER_GROUP = '''
                           ,@GROUP_FILTER_VALUE
                           ,''' OR ''ALL'' = '''
                           ,@GROUP_FILTER_VALUE
                           ,''')
AND  RLD.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
AND  RLD.LEVEL_NO >= @MIN_LEVEL'
                           )
 
              INSERT INTO #CCP (
                     HIERARCHY_NO
                     ,CCP_DETAILS_SID
                     ,LEVEL_NAME
                     )
              EXEC Sp_executesql @SQL1
                     ,N'@RELATIONSHIP_BUILDER_SID INT,@CP_INDICATOR CHAR(1),@MIN_LEVEL INT'
                     ,@RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                     ,@CP_INDICATOR = @CP_INDICATOR
                     ,@MIN_LEVEL = @MIN_LEVEL
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
 
              IF Object_id('tempdb..#PARENT_HIERARCHY') IS NOT NULL
                     DROP TABLE #PARENT_HIERARCHY
 
              CREATE TABLE #PARENT_HIERARCHY (
                     CCP_DETAILS_SID INT
                     ,HIERARCHY_NO VARCHAR(50)
                     ,LEVEL_NAME VARCHAR(75)
                     ,LEVEL_NO SMALLINT
                     ,RELATIONSHIP_LEVEL_VALUES VARCHAR(50)
                     )
 
              SET @CUSTOM_SQL = CONCAT (
                           '
                     INSERT INTO #PARENT_HIERARCHY
                     (
                     CCP_DETAILS_SID,                
                     HIERARCHY_NO,                          
                     LEVEL_NAME,
                     LEVEL_NO
                     )
SELECT CH.CCP_DETAILS_SID,
       RLD.HIERARCHY_NO,
       RLD.LEVEL_NAME,
          c.LEVEL_NO
FROM   CUSTOM_VIEW_DETAILS C
       JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
         ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
            AND RELATIONSHIP_BUILDER_SID IN ( @CUST_RELATIONSHIP_BUILDER_SID, @PROD_RELATIONSHIP_BUILDER_SID )
       JOIN '
                           ,@CCP_HIERARCHY
                           ,' CH
         ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
             OR CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
                        
                WHERE   CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID'
                           )
 
              EXEC Sp_executesql @CUSTOM_SQL
                     ,N'@CUST_RELATIONSHIP_BUILDER_SID INT, @PROD_RELATIONSHIP_BUILDER_SID  INT,@GROUP_FILTER_VALUE VARCHAR(50),@CUSTOM_VIEW_MASTER_SID INT'
                     ,@CUST_RELATIONSHIP_BUILDER_SID = @CUST_RELATIONSHIP_BUILDER_SID
                     ,@PROD_RELATIONSHIP_BUILDER_SID = @PROD_RELATIONSHIP_BUILDER_SID
                     ,@GROUP_FILTER_VALUE = @GROUP_FILTER_VALUE
                     ,@CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID
 
              INSERT INTO #CCP (
                     CCP_DETAILS_SID
                     ,HIERARCHY_NO
                     ,LEVEL_NAME
                     ,PARENT_HIERARCHY_NO
                     ,level_no
                     )
              SELECT ccp_details_sid
                     ,HIERARCHY_NO
                     ,level_name
                     ,LEFT(parent_hierarchy_no, Len(parent_hierarchy_no) - 1)
                     ,a.level_no
              FROM #PARENT_HIERARCHY a
              CROSS APPLY (
                     SELECT HIERARCHY_NO + '~'
                     FROM #PARENT_HIERARCHY b
                     WHERE a.ccp_details_sid = b.ccp_details_sid
                           AND a.level_no > b.level_no
                     ORDER BY level_no
                     FOR XML path('')
                     ) cs(parent_hierarchy_no)
       END
 
       --SELECT * FROM #CCP
       --
       --WHERE  LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO
       --                             FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2
       --                                    JOIN PROJECTION_CUST_HIERARCHY PCH2
       --                                      ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID
       --                                         AND PCH2.PROJECTION_MASTER_SID = 1990
       --                             WHERE  RLD2.LEVEL_NO = 4)
       IF Object_id('TEMPDB..#DATA_TABLE') IS NOT NULL
              DROP TABLE #DATA_TABLE
 
       SELECT HIERARCHY_NO
              ,LEVEL_NAME
              ,PERIOD
              ,YEAR
       INTO #DATA_TABLE
       FROM (
              SELECT DISTINCT HIERARCHY_NO
                     ,LEVEL_NAME
              FROM #CCP
              ) C
       CROSS JOIN (
              SELECT DISTINCT PERIOD
                     ,YEAR
              FROM #PERIOD
              WHERE PERIOD_SID BETWEEN @START_PERIOD_SID
                           AND @END_PERIOD_SID
              ) P
 
       DECLARE @LEVEL_DISC VARCHAR(100)
              ,@FIELD_VALUES VARCHAR(500)
 
       SELECT @LEVEL_DISC = Max(CASE
                           WHEN FIELD_NAME = 'LEVEL'
                                  THEN FIELD_VALUES
                           END)
              ,@FIELD_VALUES = Max(CASE
                           WHEN FIELD_NAME = 'SELECTED DISCOUNTS'
                                  THEN FIELD_VALUES
                           END)
       FROM NM_PROJECTION_SELECTION
       WHERE SCREEN_NAME = 'DISCOUNT PROJECTION'
              AND PROJECTION_MASTER_SID = @FIRST_PROJ_SID
              AND FIELD_NAME IN (
                     'LEVEL'
                     ,'SELECTED DISCOUNTS'
                     )
 
       DECLARE @D_SQL VARCHAR(MAX)
 
       IF Object_id('TEMPDB..#DISCOUNT_INFO') IS NOT NULL
              DROP TABLE #DISCOUNT_INFO
 
       CREATE TABLE #DISCOUNT_INFO (
              TOKEN VARCHAR(100)
              ,DISCOUNT VARCHAR(100)
              )
 
       SET @D_SQL = CONCAT (
                     ' INSERT INTO #DISCOUNT_INFO
                  (TOKEN,
                   DISCOUNT)
SELECT DISTINCT CASE
                        WHEN '''
                     ,@DISCOUNT_LEVEL
                     ,''' = ''PROGRAM'' THEN CAST(R.RS_CONTRACT_SID AS VARCHAR(50))
                        ELSE PRICE_GROUP_TYPE
                      END AS TOKEN,
                      CASE
                        WHEN'''
                     ,@DISCOUNT_LEVEL
                     ,''' = ''PROGRAM'' THEN RS_NAME
                        ELSE PRICE_GROUP_TYPE
                      END AS DISCOUNT
      FROM   '
                     ,@D_MASTER_TABLE
                     ,' S
             INNER JOIN RS_CONTRACT R
                     ON R.RS_CONTRACT_SID = S.RS_CONTRACT_SID
      WHERE   ( ('''
                     ,@LEVEL_DISC
                     ,''' = ''PROGRAM CATEGORY''
                     AND PRICE_GROUP_TYPE IN (SELECT TOKEN
                                              FROM   UDF_SPLITSTRING('''
                     ,@FIELD_VALUES
                     ,''','','')) )
                    OR ('''
                     ,@LEVEL_DISC
                     ,''' = ''PROGRAM''
                         AND RS_NAME IN (SELECT TOKEN
                                         FROM   UDF_SPLITSTRING('''
                     ,@FIELD_VALUES
                     ,''','','')) ) )
           
      UNION ALL
      SELECT DISTINCT CASE
                        WHEN '''
                     ,@DISCOUNT_LEVEL
                     ,''' = ''PROGRAM'' THEN CAST(R.RS_CONTRACT_SID AS VARCHAR(50))
                        ELSE ''PRICE PROTECTION''
                      END AS TOKEN,
                      CASE
                        WHEN '''
                     ,@DISCOUNT_LEVEL
                     ,''' = ''PROGRAM'' THEN RS_NAME
                        ELSE ''PRICE PROTECTION''
                      END AS DISCOUNT
      FROM   '
                     ,@P_MASTER_TABLE
                     ,' S
             INNER JOIN RS_CONTRACT R
                     ON R.RS_CONTRACT_SID = S.RS_CONTRACT_SID'
                     )
 
       EXEC (@D_SQL)
 
       IF Object_id('TEMPDB..#TEMP_CCP') IS NOT NULL
              DROP TABLE #TEMP_CCP
 
       CREATE TABLE #TEMP_CCP (
              COMPANY_MASTER_SID INT
              ,CONTRACT_MASTER_SID INT
              ,ITEM_MASTER_SID INT
              ,CCP_DETAILS_SID INT
              ,PROJECTION_MASTER_SID INT
              ,BUSINESS_UNIT INT
              ,COMPANY INT
              ,HIERARCHY_NO VARCHAR(100)
              ,LEVEL_NAME VARCHAR(100)
              )
 
       INSERT INTO #TEMP_CCP (
              COMPANY_MASTER_SID
              ,CONTRACT_MASTER_SID
              ,ITEM_MASTER_SID
              ,CCP_DETAILS_SID
              ,HIERARCHY_NO
              ,LEVEL_NAME
              ,PROJECTION_MASTER_SID
              )
       SELECT CCP.COMPANY_MASTER_SID
              ,CCP.CONTRACT_MASTER_SID
              ,CCP.ITEM_MASTER_SID
              ,CCP.CCP_DETAILS_SID
              ,HIERARCHY_NO
              ,LEVEL_NAME
              ,@FIRST_PROJ_SID AS PROJECTION_MASTER_SID
       FROM #CCP_DETAILS_TEMP CCP
       INNER JOIN #CCP C ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
 
       --------------------------------------------------------------------
       -------------------------------------------------------------------------------------
       DECLARE @ITEMID [DBO].[UDT_ITEM]
 
       INSERT INTO @ITEMID
       SELECT IM.ITEM_MASTER_SID
       FROM ITEM_MASTER IM
       WHERE EXISTS (
                     SELECT 1
                     FROM #TEMP_CCP A
                     WHERE PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                           AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                     )
 
       SET @CURRENT_DATE = CASE
                     WHEN LEFT(@FREQUENCY, 1) = 'M'
                           THEN Dateadd(DD, 1, Eomonth(Getdate(), - 1))
                     WHEN LEFT(@FREQUENCY, 1) = 'Q'
                           THEN Datefromparts(Year(Getdate()), Datepart(QUARTER, Getdate()), 01)
                     WHEN LEFT(@FREQUENCY, 1) = 'S'
                           THEN Datefromparts(Year(Getdate()), (((((Month(Getdate())) - 1) / 6) * 6) + 1), 01)
                     ELSE Datefromparts(Year(Getdate()), 01, 01)
                     END
			
			IF Object_id('tempdb..#PRODUCT_FILE_TEMP') IS NOT NULL
                     DROP TABLE #PRODUCT_FILE_TEMP
 
              CREATE TABLE #PRODUCT_FILE_TEMP (
                     PROJECTION_MASTER_SID INT
                     ,ITEM_MASTER_SID INT
                     ,PERIOD_SID INT
                     ,EXFACTORY_ACTUAL_SALES NUMERIC(22, 6)
                     ,EXFACTORY_ACTUAL_UNITS NUMERIC(22, 6)
                     ,EXFACTORY_FORECAST_SALES NUMERIC(22, 6)
                     ,EXFACTORY_FORECAST_UNITS NUMERIC(22, 6)
                     ,DEMAND_ACTUAL_SALES NUMERIC(22, 6)
                     ,DEMAND_ACTUAL_UNITS NUMERIC(22, 6)
                     ,DEMAND_FORECAST_SALES NUMERIC(22, 6)
                     ,DEMAND_FORECAST_UNITS NUMERIC(22, 6)
                     ,ADJUSTED_DEMAND_ACTUAL_SALES NUMERIC(22, 6)
                     ,ADJUSTED_DEMAND_ACTUAL_UNITS NUMERIC(22, 6)
                     ,ADJUSTED_DEMAND_FORECAST_SALES NUMERIC(22, 6)
                     ,ADJUSTED_DEMAND_FORECAST_UNITS NUMERIC(22, 6)
                     ,INVENTORY_ACTUAL_SALES NUMERIC(22, 6)
                     ,INVENTORY_ACTUAL_UNITS NUMERIC(22, 6)
                     ,INVENTORY_FORECAST_SALES NUMERIC(22, 6)
                     ,INVENTORY_FORECAST_UNITS NUMERIC(22, 6)
                     ,ITEM_PRICE NUMERIC(22, 6)
                     ,EXFACTORY_CUST_ACTUAL_SALES NUMERIC(22, 6)
                     ,EXFACTORY_CUST_ACTUAL_UNITS NUMERIC(22, 6)
                     ,EXFACTORY_CUST_FORECAST_SALES NUMERIC(22, 6)
                     ,EXFACTORY_CUST_FORECAST_UNITS NUMERIC(22, 6)
                     ,INVENTORY_CUST_ACTUAL_SALES NUMERIC(22, 6)
                     ,INVENTORY_CUST_ACTUAL_UNITS NUMERIC(22, 6)
                     ,INVENTORY_CUST_FORECAST_SALES NUMERIC(22, 6)
                     ,INVENTORY_CUST_FORECAST_UNITS NUMERIC(22, 6)
                     )
 
       IF (
                     (
                           SELECT Count(ID)
                           FROM #PROJECTION_MASTER
                           ) > 1
                     )
       BEGIN
             
 
              INSERT INTO #PRODUCT_FILE_TEMP
              EXEC dbo.Prc_prior_proj_prod_file_data
       END
 
 
 
 
       IF @VIEW = 'DETAIL_TOTAL_DISCOUNT'
       BEGIN
              DECLARE @ITEM_INFO TABLE (
                     ITEM_ID VARCHAR(50)
                     ,NDC8 VARCHAR(20)
                     ,ITEM_MASTER_SID INT
                     )
 
              IF Object_id('TEMPDB..#ITEM_PRICING') IS NOT NULL
                     DROP TABLE #ITEM_PRICING
 
              SELECT *
              INTO #ITEM_PRICING
              FROM [DBO].[Udf_item_pricing](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH')
 
              INSERT INTO #PIVOT_RESULT (
                     PROJECTION_ID
                     ,LEVEL_NAME
                     ,HIERARCHY_NO
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
                     )
              EXEC (
                           '  SELECT ' + @FIRST_PROJ_SID + ' PROJECTION_ID,
                   DT.LEVEL_NAME,
                   DT.HIERARCHY_NO,
                   ''' + @CP_INDICATOR + ''',
                   DT.PERIOD,
                   DT.YEAR,
                              EX_FACTORY_SALES_ACTUALS = CASE WHEN ''' + @CURRENT_DATE + ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)  ELSE NULL END ,
                   EX_FACTORY_SALES_PROJECTED = ISNULL(GTS.GTS_SALES_PROJECTED, 0),
                   DEMAND_SALES_ACTUALS =CASE WHEN ''' + @CURRENT_DATE + ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(GTS.DEMAND_SALES_ACTUAL, 0)  ELSE NULL END,
                   DEMAND_SALES_PROJECTED = ISNULL(GTS.DEMAND_SALES_PROJECTED, 0),
                   INVENTORY_WITHDRAWAL_SALES_ACTUALS =CASE WHEN ''' + @CURRENT_DATE +
                           ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(GTS.ACT_AMOUNT_WITHDRAWN, 0)  ELSE NULL END,
                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = ISNULL(GTS.FOR_AMOUNT_WITHDRAWN, 0),
                   EX_FACTORY_SALES_ACTUALS_PERCENT = CASE WHEN ''' + @CURRENT_DATE + ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100  ELSE NULL END,
                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                   DEMAND_SALES_ACTUALS_PERCENT =CASE WHEN ''' + @CURRENT_DATE +
                           ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100  ELSE NULL END,
                   DEMAND_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                   INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT =CASE WHEN ''' + @CURRENT_DATE + ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100  ELSE NULL END,
                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                   CONTRACT_SALES_ACTUALS = CASE WHEN ''' + @CURRENT_DATE +
                           ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0)  ELSE NULL END,
                   CONTRACT_SALES_PROJECTED = ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0),
                   CONTRACT_UNITS_ACTUALS = CASE WHEN ''' + @CURRENT_DATE + ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(CONTRACT_UNITS_ACTUALS, 0)  ELSE NULL END,
                   CONTRACT_UNITS_PROJECTED = ISNULL(CONTRACT_UNITS_PROJECTED, 0),
                              TOTAL_DISCOUNT_ACTUALS = CASE WHEN ''' + @CURRENT_DATE + ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL, 0)
                                              + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0)  ELSE NULL END,
                   TOTAL_DISCOUNT_PROJECTED = ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                              + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0),
                   TOTAL_DISCOUNT_ACTUALS_PERCENTAGE = CASE WHEN ''' + @CURRENT_DATE +
                           ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL, 0)
                                                                    + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100  ELSE NULL END,
                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                   TOTAL_RPU_ACTUALS = CASE WHEN ''' + @CURRENT_DATE +
                           ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL, 0)
                                                    + ISNULL(PPA.PPA_ACTUAL_RPU, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) )  ELSE NULL END,
                   TOTAL_RPU_PROJECTED = ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                    + ISNULL(PPA.PPA_PROJECTED_RPU, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                   NET_SALES_ACTUALS =CASE WHEN ''' + @CURRENT_DATE +
                           ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL, 0)
                                                                                       + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) )  ELSE NULL END,
                   NET_SALES_PROJECTED = ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                       + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
                   COGS_ACTUALS = CASE WHEN ''' + @CURRENT_DATE + ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(SALES.COGS_ACTUALS, 0)  ELSE NULL END,
                   COGS_PROJECTED = ISNULL(SALES.COGS_PROJECTED, 0),
                   NET_PROFIT_ACTUALS =CASE WHEN ''' + @CURRENT_DATE +
                           ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ( ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL, 0)
                                                                                            + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) ) ) - ( ISNULL(SALES.COGS_ACTUALS, 0) ) )  ELSE NULL END,
                   NET_PROFIT_PROJECTED = ( ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                            + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ),
                                                                                                                                                             
                              NET_SALES_OF_EX_FACTORY_ACTUALS =CASE WHEN ''' + @CURRENT_DATE +
                           ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  (COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUAL, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUAL, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100)  ELSE NULL END,
                   NET_SALES_OF_EX_FACTORY_PROJECTED = (COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100)
                     ,DISCOUNT_OF_EX_FACTORY_ACTUALS = CASE WHEN ''' + @CURRENT_DATE +
                           ''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUAL, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUAL, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100  ELSE NULL END,
                           DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
            FROM   #DATA_TABLE DT
                   LEFT JOIN (SELECT ACT_AMOUNT_WITHDRAWN = SUM(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = SUM(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_SALES,INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = SUM(INVENTORY_FORECAST_UNITS),
                                                              DEMAND_SALES_ACTUAL = SUM(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = SUM(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = SUM(DEMAND_FORECAST_UNITS),
                                                              GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES),
                                     GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES,EXFACTORY_ACTUAL_SALES)),
                                     UNITS = SUM(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
                                     PERIOD,
                                     YEAR AS YEARLY,
                                                              HIERARCHY_NO,
                                     LEVEL_NAME
                              FROM   '
                           + @PRODUCT_TABLE +
                           ' PF JOIN #PERIOD P ON PF.PERIOD_SID=P.PERIOD_SID
                           inner join (  select distinct HIERARCHY_NO,ITEM_MASTER_SID,LEVEL_NAME from #CCP_DETAILS_TEMP CCP                                            
                            INNER JOIN #CCP C
                                             ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID) ci on PF.item_master_sid = ci.item_master_sid
                                    
                                                  GROUP BY PERIOD,YEAR,
                                                              HIERARCHY_NO,
                                     LEVEL_NAME
                                                  ) GTS
                          ON GTS.PERIOD = DT.PERIOD
                             AND GTS.YEARLY = DT.YEAR
                                                  AND GTS.HIERARCHY_NO = DT.HIERARCHY_NO
                             AND GTS.LEVEL_NAME = DT.LEVEL_NAME
                   LEFT JOIN (SELECT CONTRACT_SALES_ACTUALS = ACTUAL_SALES,
                                     CONTRACT_UNITS_ACTUALS = ACTUAL_UNITS,
                                                              CONTRACT_SALES_PROJECTED = PROJECTION_SALES,
                                     CONTRACT_UNITS_PROJECTED = PROJECTION_UNITS,
                                     COALESCE(ACT.PERIOD,PROJ.PERIOD)PERIOD,
                                     COALESCE(ACT.[YEAR],PROJ.YEAR)YEAR,
                                                              COGS_ACTUALS = COGS_ACTUAL,
                                     COGS_PROJECTED = COGS_PROJECTED,
                                     COALESCE(ACT.HIERARCHY_NO,PROJ.HIERARCHY_NO)HIERARCHY_NO,
                                     COALESCE(ACT.LEVEL_NAME,PROJ.LEVEL_NAME)LEVEL_NAME
                                                FROM(SELECT ACTUAL_SALES=SUM(ACTUAL_SALES),
                                                              ACTUAL_UNITS=SUM(ACTUAL_UNITS),
                                                              COGS_ACTUAL = SUM( Isnull(NPS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ),
                                                              PERIOD,
                                     [YEAR],
                                                              HIERARCHY_NO,
                                     LEVEL_NAME
                              FROM   '
                           + @S_ACTUAL_TABLE +
                           ' NPS
                                     INNER JOIN #CCP_DETAILS_TEMP CCP
                                             ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                     INNER JOIN #CCP C
                                             ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                     INNER JOIN #ITEM_PRICING U
                                             ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                AND NPS.PERIOD_SID = U.PERIOD_SID
                                     INNER JOIN #PERIOD P
                                             ON P.PERIOD_SID = NPS.PERIOD_SID
                                                                           GROUP BY PERIOD,
                                     [YEAR],
                                                              HIERARCHY_NO,
                                     LEVEL_NAME )ACT
                                                                           FULL JOIN(SELECT PROJECTION_SALES=SUM(PROJECTION_SALES),
                                                              PROJECTION_UNITS=SUM(PROJECTION_UNITS),
                                                                COGS_PROJECTED = SUM( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ),
                                                              PERIOD,
                                     [YEAR],
                                                              HIERARCHY_NO,
                                     LEVEL_NAME
                              FROM   '
                           + @S_PROJECTION_TABLE +
                           ' NPS
                                     INNER JOIN #CCP_DETAILS_TEMP CCP
                                             ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                     INNER JOIN #CCP C
                                             ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                     INNER JOIN #ITEM_PRICING U
                                             ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                AND NPS.PERIOD_SID = U.PERIOD_SID
                                     INNER JOIN #PERIOD P
                                             ON P.PERIOD_SID = NPS.PERIOD_SID
                                                                             GROUP BY PERIOD,
                                     [YEAR],
                                                              HIERARCHY_NO,
                                     LEVEL_NAME )PROJ
                       ON ACT.PERIOD=PROJ.PERIOD
                                      AND ACT.YEAR=PROJ.YEAR
                                     AND ACT.HIERARCHY_NO=PROJ.HIERARCHY_NO
                                     AND ACT.LEVEL_NAME=PROJ.LEVEL_NAME) SALES
                          ON SALES.PERIOD = DT.PERIOD
                             AND SALES.[YEAR] = DT.YEAR
                             AND SALES.HIERARCHY_NO = DT.HIERARCHY_NO
                             AND SALES.LEVEL_NAME = DT.LEVEL_NAME
                   LEFT JOIN (SELECT CONTRACT_DISCOUNT_PROJECTED = PROJECTION_SALES,
                              CONTRACT_DISCOUNT_ACTUAL = ACTUAL_SALES,
                                    COALESCE(ACT.PERIOD,PROJ.PERIOD) PERIOD,
                                        COALESCE(ACT.[YEAR],PROJ.YEAR)YEAR,
                                        COALESCE(ACT.HIERARCHY_NO,PROJ.HIERARCHY_NO) HIERARCHY_NO,
                                        COALESCE(ACT.LEVEL_NAME,PROJ.LEVEL_NAME)LEVEL_NAME
                                                              FROM (SELECT ACTUAL_SALES=SUM(ACTUAL_SALES),
                                                                  PERIOD,
                                     [YEAR],
                                     HIERARCHY_NO,
                                     LEVEL_NAME
                              FROM   '
                           + @D_ACTUAL_TABLE + ' NAD
                                     INNER JOIN ' + @D_MASTER_TABLE +
                           ' NDPM
                                             ON NDPM.CCP_DETAILS_SID = NAD.CCP_DETAILS_SID
                                                AND NDPM.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID
                                     INNER JOIN #CCP_DETAILS_TEMP CCP
                                             ON NDPM.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                     INNER JOIN #CCP C
                                             ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                     INNER JOIN #PERIOD P
                                             ON P.PERIOD_SID = NAD.PERIOD_SID
                              WHERE  EXISTS (SELECT 1
                                                 FROM   #DISCOUNT_INFO A
                                                 WHERE  CASE
                                                          WHEN ''' + @DISCOUNT_LEVEL +
                           ''' = ''PROGRAM'' THEN CAST(NAD.RS_CONTRACT_SID AS VARCHAR(20))
                                                          ELSE NDPM.PRICE_GROUP_TYPE
                                                        END = A.TOKEN)
                                                                                                GROUP BY PERIOD,
                                     [YEAR],
                                     HIERARCHY_NO,
                                     LEVEL_NAME)ACT FULL JOIN(SELECT PROJECTION_SALES=SUM(PROJECTION_SALES),
                                                                  PERIOD,
                                     [YEAR],
                                     HIERARCHY_NO,
                                     LEVEL_NAME
                              FROM   ' + @D_PROJECTION_TABLE + ' NDP
                                     INNER JOIN ' + @D_MASTER_TABLE +
                           ' NDPM
                                             ON NDPM.CCP_DETAILS_SID = NDP.CCP_DETAILS_SID
                                                AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                                     INNER JOIN #CCP_DETAILS_TEMP CCP
                                             ON NDPM.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                     INNER JOIN #CCP C
                                             ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                     INNER JOIN #PERIOD P
                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                              WHERE  EXISTS (SELECT 1
                                                 FROM   #DISCOUNT_INFO A
                                                 WHERE  CASE
                                                          WHEN ''' + @DISCOUNT_LEVEL +
                           ''' = ''PROGRAM'' THEN CAST(NDP.RS_CONTRACT_SID AS VARCHAR(20))
                                                          ELSE NDPM.PRICE_GROUP_TYPE
                                                        END = A.TOKEN)
                                                                                                GROUP BY PERIOD,
                                     [YEAR],
                                     HIERARCHY_NO,
                                     LEVEL_NAME )PROJ
                                     ON ACT.PERIOD=PROJ.PERIOD
                                      AND ACT.YEAR=PROJ.YEAR
                                     AND ACT.HIERARCHY_NO=PROJ.HIERARCHY_NO
                                     AND ACT.LEVEL_NAME=PROJ.LEVEL_NAME) DISC
                          ON DISC.PERIOD = DT.PERIOD
                             AND DISC.[YEAR] = DT.[YEAR]
                             AND DISC.HIERARCHY_NO = DT.HIERARCHY_NO
                             AND DISC.LEVEL_NAME = DT.LEVEL_NAME
                   LEFT JOIN (SELECT PPA_DISCOUNT_PROJECTED = PROJECTION_PPA_SALES,
                                     PPA_PROJECTED_RPU = PROJ.PPA_RPU,
                                     PROJECTION_SALES AS PPA_PROJECTION_SALES,
                                     PROJECTION_UNITS AS PPA_PROJECTION_UNITS,
                                                              PPA_DISCOUNT_ACTUAL = ACTUAL_PPA_SALES,
                                     PPA_ACTUAL_RPU = ACT.PPA_RPU,
                                     ACTUAL_SALES AS PPA_ACTUAL_SALES,
                                     ACTUAL_UNITS AS PPA_ACTUAL_UNITS,
                                     COALESCE(ACT.PERIOD,PROJ.PERIOD)PERIOD,
                                        COALESCE(ACT.[YEAR],PROJ.YEAR)YEAR,
                                        COALESCE(ACT.HIERARCHY_NO,PROJ.HIERARCHY_NO)HIERARCHY_NO,
                                        COALESCE(ACT.LEVEL_NAME,PROJ.LEVEL_NAME)LEVEL_NAME
 
                              FROM  (SELECT PPA_RPU = SUM(ACTUAL_DISCOUNT_DOLLAR),
                                             ACTUAL_PPA_SALES = SUM(ACTUAL_DISCOUNT_DOLLAR),
                                              PERIOD,
                                             [YEAR],
                                             HIERARCHY_NO,
                                             C.LEVEL_NAME,
                                             RS_CONTRACT_SID,
                                             ACTUAL_SALES = SUM(NS.ACTUAL_SALES),
                                             ACTUAL_UNITS = SUM(NS.ACTUAL_UNITS)
                                      FROM   '
                           + @P_ACTUAL_TABLE + ' NPP
                                             INNER JOIN ' + @S_ACTUAL_TABLE +
                           ' NS
                                                     ON NS.CCP_DETAILS_SID = NPP.CCP_DETAILS_SID
                                                        AND NS.PERIOD_SID = NPP.PERIOD_SID
                                             INNER JOIN #CCP_DETAILS_TEMP CCP
                                                     ON NPP.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                             INNER JOIN #CCP C
                                                     ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                             INNER JOIN #PERIOD P
                                                     ON P.PERIOD_SID = NPP.PERIOD_SID
                                         GROUP BY PERIOD,
                                             [YEAR],
                                             HIERARCHY_NO,
                                             C.LEVEL_NAME,
                                             RS_CONTRACT_SID ) ACT FULL JOIN  (SELECT
                                             PPA_RPU = SUM(PROJECTION_DISCOUNT_DOLLAR),
                                             PROJECTION_PPA_SALES = SUM(PROJECTION_DISCOUNT_DOLLAR),
                                              PERIOD,
                                             [YEAR],
                                             HIERARCHY_NO,
                                             C.LEVEL_NAME,
                                             RS_CONTRACT_SID,
                                             PROJECTION_SALES = SUM(NS.PROJECTION_SALES),
                                             PROJECTION_UNITS = SUM(NS.PROJECTION_UNITS)
                                      FROM   '
                           + @P_PROJECTION_TABLE + ' NPP
                                             INNER JOIN ' + @S_PROJECTION_TABLE +
                           ' NS
                                                     ON NS.CCP_DETAILS_SID = NPP.CCP_DETAILS_SID
                                                        AND NS.PERIOD_SID = NPP.PERIOD_SID
                                             INNER JOIN #CCP_DETAILS_TEMP CCP
                                                     ON NPP.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                             INNER JOIN #CCP C
                                                     ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                             INNER JOIN #PERIOD P
                                                     ON P.PERIOD_SID = NPP.PERIOD_SID
                                        GROUP BY PERIOD,
                                             [YEAR],
                                             HIERARCHY_NO,
                                             C.LEVEL_NAME,
                                             RS_CONTRACT_SID  ) PROJ
 
                                         ON ACT.PERIOD=PROJ.PERIOD
                                      AND ACT.YEAR=PROJ.YEAR
                                     AND ACT.HIERARCHY_NO=PROJ.HIERARCHY_NO
                                     AND ACT.LEVEL_NAME=PROJ.LEVEL_NAME
                              ) PPA
                          ON PPA.PERIOD = DT.PERIOD
                             AND PPA.[YEAR] = DT.[YEAR]
                             AND PPA.HIERARCHY_NO = DT.HIERARCHY_NO
                             AND PPA.LEVEL_NAME = DT.LEVEL_NAME
            ORDER  BY DT.PERIOD,
                      DT.[YEAR],
                      DT.HIERARCHY_NO'
                           )

						    
 
              IF EXISTS (
                           SELECT 1
                           FROM #PROJECTION_MASTER
                           WHERE ID > 1
                           )
              BEGIN
                     IF Object_id('TEMPDB..#PRIOR_PROJECTIONS') IS NOT NULL
                           DROP TABLE #PRIOR_PROJECTIONS
 
                     SELECT PD.PROJECTION_DETAILS_SID
                           ,PM.PROJECTION_MASTER_SID
                           ,HIERARCHY_NO
                           ,LEVEL_NAME
                     INTO #PRIOR_PROJECTIONS
                     FROM #PROJECTION_MASTER PM
                     INNER JOIN PROJECTION_DETAILS PD ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                     INNER JOIN #CCP C ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                     WHERE PM.ID <> 1
 
                     IF Object_id('TEMPDB..#PRIOR_DATA_TABLE') IS NOT NULL
                           DROP TABLE #PRIOR_DATA_TABLE
 
                     SELECT PROJECTION_MASTER_SID
                           ,HIERARCHY_NO
                           ,LEVEL_NAME
                           ,PERIOD
                           ,YEAR
                     INTO #PRIOR_DATA_TABLE
                     FROM (
                           SELECT DISTINCT HIERARCHY_NO
                                  ,LEVEL_NAME
                           FROM #CCP
                           ) C
                     CROSS JOIN (
                           SELECT DISTINCT PERIOD
                                  ,YEAR
                           FROM #PERIOD
                           WHERE PERIOD_SID BETWEEN @START_PERIOD_SID
                                         AND @END_PERIOD_SID
                           ) P
                     CROSS JOIN #PROJECTION_MASTER PM
                     WHERE PM.ID <> 1
 
                     IF Object_id('tempdb..#CURRENT_CPP_COMP_PRIOR_CPP') IS NOT NULL
                           DROP TABLE #CURRENT_CPP_COMP_PRIOR_CPP
 
                     SELECT PM.PROJECTION_MASTER_SID
                           ,CC.CCP_DETAILS_SID
                     INTO #CURRENT_CPP_COMP_PRIOR_CPP
                     FROM #PROJECTION_MASTER PM
                     INNER JOIN PROJECTION_DETAILS PD ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                     INNER JOIN CCP_DETAILS CC ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
                     WHERE ID = 1



      
                     INSERT INTO #PIVOT_RESULT (
                           PROJECTION_ID
                           ,LEVEL_NAME
                           ,HIERARCHY_NO
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
                           )
                     EXEC (
                                  ' SELECT PDT.PROJECTION_MASTER_SID AS PROJECTION_ID,
                         PDT.LEVEL_NAME,
                         PDT.HIERARCHY_NO,
                         ''' + @CP_INDICATOR +
                                  ''',
                         PDT.PERIOD,
                         PDT.YEAR,
                              EX_FACTORY_SALES_ACTUALS = ISNULL(GTS.GTS_SALES_ACTUALS, 0),
                   EX_FACTORY_SALES_PROJECTED = ISNULL(GTS.GTS_SALES_PROJECTED, 0),
                   DEMAND_SALES_ACTUALS = ISNULL(GTS.DEMAND_SALES_ACTUAL, 0),
                   DEMAND_SALES_PROJECTED = ISNULL(GTS.DEMAND_SALES_PROJECTED, 0),
                   INVENTORY_WITHDRAWAL_SALES_ACTUALS = ISNULL(GTS.ACT_AMOUNT_WITHDRAWN, 0),
                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = ISNULL(GTS.FOR_AMOUNT_WITHDRAWN, 0),
                   EX_FACTORY_SALES_ACTUALS_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100,
                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                   DEMAND_SALES_ACTUALS_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                   DEMAND_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                   INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                   CONTRACT_SALES_ACTUALS = ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0),
                   CONTRACT_SALES_PROJECTED = ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0),
                   CONTRACT_UNITS_ACTUALS = ISNULL(CONTRACT_UNITS_ACTUALS, 0),
                   CONTRACT_UNITS_PROJECTED = ISNULL(CONTRACT_UNITS_PROJECTED, 0),
                              TOTAL_DISCOUNT_ACTUALS = ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + (case when ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0) end),
                   TOTAL_DISCOUNT_PROJECTED = ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                              + (case when ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) end),
                   TOTAL_DISCOUNT_ACTUALS_PERCENTAGE = ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + (case when ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0) end) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    +  (case when ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) end)) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                   TOTAL_RPU_ACTUALS = ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                    + (case when ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0) end) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                   TOTAL_RPU_PROJECTED = ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                    + (case when ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) end) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                   NET_SALES_ACTUALS = ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + (case when ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0) end) ),
                   NET_SALES_PROJECTED = ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                       + (case when ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) end) ),
                   COGS_ACTUALS = ISNULL(SALES.COGS_ACTUALS, 0),
                   COGS_PROJECTED = ISNULL(SALES.COGS_PROJECTED, 0),
                   NET_PROFIT_ACTUALS = ( ( ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                            + (case when ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0) end) ) ) - ( ISNULL(SALES.COGS_ACTUALS, 0) ) ),
                   NET_PROFIT_PROJECTED = ( ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                            + (case when ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) end) ) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ),
                             NET_SALES_OF_EX_FACTORY_ACTUALS = COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + (case when ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0) end) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
                             NET_SALES_OF_EX_FACTORY_PROJECTED = COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + (case when ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) end) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
                                         ,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + (case when ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0) end)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
                                                DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ (case when ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) = 0 then 0 else ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) end)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
                  FROM   #PRIOR_DATA_TABLE PDT
                         LEFT JOIN (SELECT PF.PROJECTION_MASTER_SID,ACT_AMOUNT_WITHDRAWN = SUM(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = SUM(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_SALES,INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = SUM(INVENTORY_FORECAST_UNITS),
                                                              DEMAND_SALES_ACTUAL = SUM(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = SUM(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = SUM(DEMAND_FORECAST_UNITS),
                                                              GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES),
                                     GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES,EXFACTORY_ACTUAL_SALES)),
                                     UNITS = SUM(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
                                     PERIOD,
                                     YEAR AS YEARLY,cp.HIERARCHY_NO,cp.LEVEL_NAME
                              FROM   #PRODUCT_FILE_TEMP PF JOIN #PERIOD P ON PF.PERIOD_SID=P.PERIOD_SID
                                                join (select distinct HIERARCHY_NO,level_name,cp.projection_master_sid,item_master_Sid from #PRIOR_PROJECTIONS cp                                               
                                                  join PROJECTION_DETAILS pd on pd.PROJECTION_MASTER_SID=cp.PROJECTION_MASTER_SID
                                                   and pd.PROJECTION_DETAILS_SID=cp.PROJECTION_DETAILS_SID
                                                  join CCP_DETAILS cd on cd.CCP_DETAILS_SID=pd.CCP_DETAILS_SID) cp on cp.PROJECTION_MASTER_SID=pf.PROJECTION_MASTER_SID
                                                  and pf.ITEM_MASTER_SID=cp.ITEM_MASTER_SID
                                                  GROUP BY PF.PROJECTION_MASTER_SID,PERIOD,YEAR,cp.HIERARCHY_NO,cp.LEVEL_NAME
                                                  ) GTS
                                ON PDT.PROJECTION_MASTER_SID=GTS.PROJECTION_MASTER_SID AND PDT.[YEAR] = GTS.YEARLY
                                   AND PDT.PERIOD = GTS.PERIOD
                                                          AND GTS.HIERARCHY_NO=PDT.HIERARCHY_NO
                                                              AND GTS.LEVEL_NAME=PDT.LEVEL_NAME
 
                         LEFT JOIN (SELECT CONTRACT_SALES_PROJECTED = PROJECTION_SALES,
                                           CONTRACT_UNITS_PROJECTED = PROJECTION_UNITS,
                                                                        CONTRACT_SALES_ACTUALS = ACTUAL_SALES,
                                           CONTRACT_UNITS_ACTUALS = ACTUAL_UNITS,
                                                                        COGS_ACTUALS = COGS_ACTUAL,
                                           COGS_PROJECTED = COGS_PROJECTED,
                                           COALESCE(ACT.PROJECTION_MASTER_SID,PROJ.PROJECTION_MASTER_SID)PROJECTION_MASTER_SID,
                                              COALESCE(ACT.PERIOD,PROJ.PERIOD)PERIOD,
                                              COALESCE(ACT.[YEAR],PROJ.YEAR)YEAR,
                                              COALESCE(ACT.HIERARCHY_NO,PROJ.HIERARCHY_NO)HIERARCHY_NO,
                                              COALESCE(ACT.LEVEL_NAME,PROJ.LEVEL_NAME)LEVEL_NAME
                                    FROM (SELECT ACTUAL_SALES=SUM(ACTUAL_SALES),
                                                              ACTUAL_UNITS=SUM(ACTUAL_UNITS),
                                                              COGS_ACTUAL = SUM( Isnull(NPS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ),
                                                              PERIOD,
                                                              YEAR,
                                                              Pd.PROJECTION_MASTER_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NAME
                                                                        FROM
                                                              NM_ACTUAL_SALES NPS
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                           INNER JOIN CCP_DETAILS CCP
                                                   ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                        INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
                                                                        ON CC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID
                                           left JOIN #ITEM_PRICING U
                                                   ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                      AND NPS.PERIOD_SID = U.PERIOD_SID
                                              INNER JOIN #PRIOR_PROJECTIONS PP
                                                   ON PP.projection_master_sid = PD.projection_master_sid
                                                                                      and pp.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = NPS.PERIOD_SID
                                                                                        AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                       FROM   #PROJECTION_MASTER PM
                                                                                       WHERE  ID <> 1)
                                                                                     GROUP BY PERIOD,
                                                              YEAR,
                                                              Pd.PROJECTION_MASTER_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NAME
                                                                        )ACT
                                                                                     FULL JOIN(SELECT PROJECTION_SALES=SUM(PROJECTION_SALES),
                                                              PROJECTION_UNITS=SUM(PROJECTION_UNITS),
                                                              COGS_PROJECTED = SUM( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ),
                                                              PERIOD,
                                                              YEAR,
                                                              Pd.PROJECTION_MASTER_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NAME
                                                              FROM NM_SALES_PROJECTION NPS
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                           INNER JOIN CCP_DETAILS CCP
                                                   ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                                     INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
                                                                        ON CC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID
                                           left JOIN #ITEM_PRICING U
                                                   ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                      AND NPS.PERIOD_SID = U.PERIOD_SID
                                              INNER JOIN #PRIOR_PROJECTIONS PP
                                                   ON PP.projection_master_sid = PD.projection_master_sid
                                                                                      and pp.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = NPS.PERIOD_SID
                                                                                        AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                       FROM   #PROJECTION_MASTER PM
                                                                                       WHERE  ID <> 1)
                                                                                     GROUP BY        PERIOD,
                                                              YEAR,
                                                              Pd.PROJECTION_MASTER_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NAME)PROJ
                                                              ON ACT.PROJECTION_MASTER_SID=PROJ.PROJECTION_MASTER_SID
                                                              AND ACT.PERIOD=PROJ.PERIOD
                                                              AND ACT.YEAR=PROJ.YEAR
                                                              AND ACT.HIERARCHY_NO=PROJ.HIERARCHY_NO
                                                              AND ACT.LEVEL_NAME=PROJ.LEVEL_NAME
                                   ) SALES
                                ON SALES.PERIOD = PDT.PERIOD
                                   AND SALES.[YEAR] = PDT.YEAR
                                   AND SALES.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND SALES.LEVEL_NAME = PDT.LEVEL_NAME
                                   AND SALES.PROJECTION_MASTER_SID = PDT.PROJECTION_MASTER_SID
                         LEFT JOIN (SELECT CONTRACT_DISCOUNT_PROJECTED =PROJECTION_SALES,
                                         CONTRACT_DISCOUNT_ACTUALS = ACTUAL_SALES,
                                             COALESCE(ACT.PROJECTION_MASTER_SID,PROJ.PROJECTION_MASTER_SID)PROJECTION_MASTER_SID,
                                              COALESCE(ACT.PERIOD,PROJ.PERIOD)PERIOD,
                                              COALESCE(ACT.[YEAR],PROJ.YEAR)YEAR,
                                              COALESCE(ACT.HIERARCHY_NO,PROJ.HIERARCHY_NO)HIERARCHY_NO,
                                              COALESCE(ACT.LEVEL_NAME,PROJ.LEVEL_NAME)LEVEL_NAME
                                    FROM (SELECT ACTUAL_SALES=SUM(ACTUAL_SALES),   PERIOD,
                                           [YEAR],
                                           PD.PROJECTION_MASTER_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NAME FROM NM_ACTUAL_DISCOUNT NDP
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                     INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
                                                                        ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
                                             INNER JOIN #PRIOR_PROJECTIONS PP
                                                   ON PP.projection_master_sid = PD.projection_master_sid
                                                                                      and pp.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = NDP.PERIOD_SID
                                                                                        AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                       FROM   #PROJECTION_MASTER PM
                                                                                       WHERE  ID <> 1)
                                                                                     GROUP BY PERIOD,
                                           [YEAR],
                                           PD.PROJECTION_MASTER_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NAME)ACT
                                                                                     FULL JOIN(SELECT PROJECTION_SALES=SUM(PROJECTION_SALES),   PERIOD,
                                           [YEAR],
                                           PD.PROJECTION_MASTER_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NAME FROM NM_DISCOUNT_PROJECTION NDP
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                     INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
                                                                        ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
                                             INNER JOIN #PRIOR_PROJECTIONS PP
                                                   ON PP.projection_master_sid = PD.projection_master_sid
                                                                                      and pp.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = NDP.PERIOD_SID
                                                                                        AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                       FROM   #PROJECTION_MASTER PM
                                                                                       WHERE  ID <> 1)
                                                                                     GROUP BY  PERIOD,
                                           [YEAR],
                                           PD.PROJECTION_MASTER_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NAME)PROJ
                                                                            ON ACT.PROJECTION_MASTER_SID=PROJ.PROJECTION_MASTER_SID
                                                              AND ACT.PERIOD=PROJ.PERIOD
                                                              AND ACT.YEAR=PROJ.YEAR
                                                              AND ACT.HIERARCHY_NO=PROJ.HIERARCHY_NO
                                                              AND ACT.LEVEL_NAME=PROJ.LEVEL_NAME
 
                                   ) DISC
                                ON DISC.PERIOD = PDT.PERIOD
                                   AND DISC.[YEAR] = PDT.YEAR
                                   AND DISC.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND DISC.LEVEL_NAME = PDT.LEVEL_NAME
                                   AND DISC.PROJECTION_MASTER_SID = PDT.PROJECTION_MASTER_SID
                         LEFT JOIN (SELECT PPA_DISCOUNT_PROJECTED = PROJECTION_DISCOUNT_DOLLAR,
                                           PPA_PROJECTED_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                           PPA_PROJECTION_SALES = PROJECTION_SALES,
                                           PPA_PROJECTION_UNITS = PROJECTION_UNITS,
                                           PPA_DISCOUNT_ACTUALS = ACTUAL_DISCOUNT_DOLLAR,
                                           PPA_ACTUALS_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                           PPA_ACTUALS_SALES = ACTUAL_SALES,
                                           PPA_ACTUALS_UNITS = ACTUAL_UNITS,
                                            COALESCE(ACT.PROJECTION_MASTER_SID,PROJ.PROJECTION_MASTER_SID)PROJECTION_MASTER_SID,
                                              COALESCE(ACT.PERIOD,PROJ.PERIOD)PERIOD,
                                              COALESCE(ACT.[YEAR],PROJ.YEAR)YEAR,
                                              COALESCE(ACT.HIERARCHY_NO,PROJ.HIERARCHY_NO)HIERARCHY_NO,
                                              COALESCE(ACT.LEVEL_NAME,PROJ.LEVEL_NAME)LEVEL_NAME
                                    FROM  (SELECT ACTUAL_DISCOUNT_DOLLAR=SUM(ACTUAL_DISCOUNT_DOLLAR),ACTUAL_SALES=SUM(NS.ACTUAL_SALES),ACTUAL_UNITS=SUM(ACTUAL_UNITS),PERIOD,
                                           [YEAR],
                                           Pd.PROJECTION_MASTER_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NAME FROM NM_ACTUAL_PPA NPP
                                           INNER JOIN NM_ACTUAL_SALES NS
                                                   ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                      AND NS.PERIOD_SID = NPP.PERIOD_SID
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                     INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
                                                                        ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
                                             INNER JOIN #PRIOR_PROJECTIONS PP
                                                   ON PP.projection_master_sid = PD.projection_master_sid
                                                                                      and pp.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = NPP.PERIOD_SID
                                                      AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                       FROM   #PROJECTION_MASTER PM
                                                                                       WHERE  ID <> 1)
                                                                                                                                                   GROUP BY PERIOD,
                                           [YEAR],
                                           Pd.PROJECTION_MASTER_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NAME)ACT FULL JOIN (SELECT PROJECTION_DISCOUNT_DOLLAR=SUM(PROJECTION_DISCOUNT_DOLLAR),PROJECTION_SALES=SUM(NS.PROJECTION_SALES),PROJECTION_UNITS=SUM(PROJECTION_UNITS),PERIOD,
                                           [YEAR],
                                           Pd.PROJECTION_MASTER_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NAME FROM NM_PPA_PROJECTION NPP
                                           INNER JOIN NM_SALES_PROJECTION NS
                                                   ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                      AND NS.PERIOD_SID = NPP.PERIOD_SID
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                     INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
                                                                        ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
                                             INNER JOIN #PRIOR_PROJECTIONS PP
                                                   ON PP.projection_master_sid = PD.projection_master_sid
                                                                                      and pp.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = NPP.PERIOD_SID
                                                      AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                       FROM   #PROJECTION_MASTER PM
                                                                                       WHERE  ID <> 1)
                                                                                                                                                   GROUP BY PERIOD,
                                          [YEAR],
                                           Pd.PROJECTION_MASTER_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NAME)PROJ
 
                                                                          ON ACT.PROJECTION_MASTER_SID=PROJ.PROJECTION_MASTER_SID
                                                              AND ACT.PERIOD=PROJ.PERIOD
                                                              AND ACT.YEAR=PROJ.YEAR
                                                              AND ACT.HIERARCHY_NO=PROJ.HIERARCHY_NO
                                                              AND ACT.LEVEL_NAME=PROJ.LEVEL_NAME
                                   ) PPA
                                ON PPA.PERIOD = PDT.PERIOD
                                   AND PPA.[YEAR] = PDT.YEAR
                                   AND PPA.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND PPA.LEVEL_NAME = PDT.LEVEL_NAME
                                   AND PPA.PROJECTION_MASTER_SID = PDT.PROJECTION_MASTER_SID'
                                  )


							
              END
 
              DECLARE @SQL NVARCHAR(MAX)
                     ,@LOOP_CNTR INT
                     ,@MAX_CCP INT
 
              SET @SQL = '
                           SELECT pr.LEVEL_NAME,
                   pr.HIERARCHY_NO,
                   CP_INDICATOR,
                   PERIOD,
                   YEAR, '
              SET @LOOP_CNTR = 1
              SET @MAX_CCP = (
                           SELECT Max(ID)
                           FROM #PROJECTION_MASTER
                           )
 
              WHILE @LOOP_CNTR <= @MAX_CCP
              BEGIN
                     SET @SQL += ' EX_FACTORY_SALES_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN EX_FACTORY_SALES_ACTUALS END),EX_FACTORY_SALES_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN EX_FACTORY_SALES_PROJECTED END),0),
                    DEMAND_SALES_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN DEMAND_SALES_ACTUALS END),
                    DEMAND_SALES_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN DEMAND_SALES_PROJECTED END),0),
                                  INVENTORY_WITHDRAWAL_SALES_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN INVENTORY_WITHDRAWAL_SALES_ACTUALS END),
                                  INVENTORY_WITHDRAWAL_SALES_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) +
                           ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED END),0),
                    EX_FACTORY_SALES_ACTUALS_PERCENT_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN EX_FACTORY_SALES_ACTUALS_PERCENT END),
                    EX_FACTORY_SALES_PROJECTED_PERCENT_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN EX_FACTORY_SALES_PROJECTED_PERCENT END),0),
                    DEMAND_SALES_ACTUALS_PERCENT_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN DEMAND_SALES_ACTUALS_PERCENT END),
                    DEMAND_SALES_PROJECTED_PERCENT_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN DEMAND_SALES_PROJECTED_PERCENT END),0),
                    INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) +
                           ' THEN INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT END),
                    INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT END),0),
                    CONTRACT_SALES_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN CONTRACT_SALES_ACTUALS END),
                    CONTRACT_SALES_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN CONTRACT_SALES_PROJECTED END),0),
                    CONTRACT_UNITS_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN CONTRACT_UNITS_ACTUALS END),
                    CONTRACT_UNITS_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) +
                           ' THEN CONTRACT_UNITS_PROJECTED END),0),
                    TOTAL_DISCOUNT_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN TOTAL_DISCOUNT_ACTUALS END),
                    TOTAL_DISCOUNT_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN TOTAL_DISCOUNT_PROJECTED END),0),
                    TOTAL_DISCOUNT_ACTUALS_PERCENTAGE_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN TOTAL_DISCOUNT_ACTUALS_PERCENTAGE END),
                    TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
                    TOTAL_RPU_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN TOTAL_RPU_ACTUALS END),
                    TOTAL_RPU_PROJECTED_' +
                           Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN TOTAL_RPU_PROJECTED END),0),
                    NET_SALES_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN NET_SALES_ACTUALS END),
                    NET_SALES_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN NET_SALES_PROJECTED END),0),
                    COGS_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN COGS_ACTUALS END),
                    COGS_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN COGS_PROJECTED END),0),
                    NET_PROFIT_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN NET_PROFIT_ACTUALS END),
                    NET_PROFIT_PROJECTED_' + Cast(@LOOP_CNTR
                                  AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN NET_PROFIT_PROJECTED END),0),
                    NET_SALES_OF_EX_FACTORY_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN NET_SALES_OF_EX_FACTORY_ACTUALS END),
                    NET_SALES_OF_EX_FACTORY_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' THEN  NET_SALES_OF_EX_FACTORY_PROJECTED END),0),
                    DISCOUNT_OF_EX_FACTORY_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(20)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(20)) + ' THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),
                     DISCOUNT_OF_EX_FACTORY_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(20)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(20)) + ' THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0), '
                     SET @LOOP_CNTR += 1
              END
 
              --SET @SQL = LEFT(@SQL, Len(@SQL) - 1)
              SET @SQL += ' c.PARENT_HIERARCHY_NO FROM   #PIVOT_RESULT PR JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID  = PR.PROJECTION_ID
                       join #CCP c on pr.HIERARCHY_NO = c.HIERARCHY_NO
 
                GROUP  BY   c.level_no, PR.LEVEL_NAME,PR.HIERARCHY_NO,CP_INDICATOR,PERIOD,YEAR         ,c.PARENT_HIERARCHY_NO                               
                ORDER  BY c.level_no, PR.HIERARCHY_NO,c.PARENT_HIERARCHY_NO,[YEAR],PERIOD                    
                           '
 
              EXEC Sp_executesql @SQL
       END
       ELSE
       BEGIN
 
	IF OBJECT_ID('TEMPDB..#RS_COMBINATION') IS NOT NULL
			DROP TABLE #RS_COMBINATION

		CREATE TABLE #RS_COMBINATION (			
			HIERARCHY_NO VARCHAR(100)
			,LEVEL_NAME VARCHAR(100)
			,CCP_DETAILS_SID INT
			,RS_CONTRACT_SID INT
			,ITEM_MASTER_SID INT
			,PARENT_HIERARCHY_NO VARCHAR(100)
			,DISCOUNT VARCHAR(100)
			)

	SET @SQL=''
		SET @SQL=CONCAT('
		INSERT INTO #RS_COMBINATION(CCP_DETAILS_SID,RS_CONTRACT_SID,ITEM_MASTER_SID,HIERARCHY_NO,LEVEL_NAME,PARENT_HIERARCHY_NO,DISCOUNT)	
		SELECT DISTINCT S.CCP_DETAILS_SID,S.RS_CONTRACT_SID,CD.ITEM_MASTER_SID,C.HIERARCHY_NO,C.LEVEL_NAME,C.PARENT_HIERARCHY_NO,D.DISCOUNT 
		FROM ',@D_MASTER_TABLE,' S 
			  JOIN #DISCOUNT_INFO D ON D.TOKEN=CASE WHEN ''',@DISCOUNT_LEVEL,'''= ''PROGRAM'' THEN CAST(S.RS_CONTRACT_SID AS VARCHAR(100)) ELSE S.PRICE_GROUP_TYPE END  
			  JOIN #CCP C ON C.CCP_DETAILS_SID=S.CCP_DETAILS_SID
			  JOIN #CCP_DETAILS_TEMP CD ON C.CCP_DETAILS_SID=CD.CCP_DETAILS_SID
			  UNION ALL
		SELECT DISTINCT S.CCP_DETAILS_SID,S.RS_CONTRACT_SID,CD.ITEM_MASTER_SID,C.HIERARCHY_NO,C.LEVEL_NAME,C.PARENT_HIERARCHY_NO,D.DISCOUNT 
		FROM ',@P_MASTER_TABLE,' S 
			  join RS_CONTRACT r ON R.RS_CONTRACT_SID=S.RS_CONTRACT_SID
			   join HELPER_TABLE h on h.HELPER_TABLE_SID=r.REBATE_PROGRAM_TYPE
			  JOIN #DISCOUNT_INFO D ON D.TOKEN=CASE WHEN ''',@DISCOUNT_LEVEL,'''= ''PROGRAM'' THEN CAST(S.RS_CONTRACT_SID AS VARCHAR(100)) ELSE CAST(h.DESCRIPTION AS VARCHAR(100)) END  
			  JOIN #CCP C ON C.CCP_DETAILS_SID=S.CCP_DETAILS_SID
			  JOIN #CCP_DETAILS_TEMP CD ON C.CCP_DETAILS_SID=CD.CCP_DETAILS_SID')
			

			EXEC (@SQL)	
			
			 
			
		IF OBJECT_ID('TEMPDB..#FILE_DATA') IS NOT NULL
			DROP TABLE #FILE_DATA

		CREATE TABLE #FILE_DATA (
			PERIOD INT
			,YEAR INT
			,RS_CONTRACT_SID INT
			,GTS_SALES_ACTUALS NUMERIC(22, 6)
			,GTS_SALES_PROJECTED NUMERIC(22, 6)
			,HIERARCHY_NO VARCHAR(100)
			,LEVEL_NAME VARCHAR(100)
			,DISCOUNT VARCHAR(100)
			)

		SET @SQL = CONCAT (
				'INSERT INTO #FILE_DATA (PERIOD,YEAR,RS_CONTRACT_SID,GTS_SALES_ACTUALS,GTS_SALES_PROJECTED,HIERARCHY_NO,LEVEL_NAME,DISCOUNT)'
				,'(SELECT P.PERIOD,P.YEAR,RS_CONTRACT_SID,GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES),
                                 GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES))
                                 ,HIERARCHY_NO,LEVEL_NAME,DISCOUNT
                              FROM   '
				,@PRODUCT_TABLE
				,' PF JOIN #PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID
                           INNER JOIN (  SELECT DISTINCT HIERARCHY_NO,DISCOUNT,ITEM_MASTER_SID,LEVEL_NAME,RS_CONTRACT_SID FROM #RS_COMBINATION) CI ON PF.ITEM_MASTER_SID = CI.ITEM_MASTER_SID
                               GROUP  BY P.PERIOD,P.YEAR,RS_CONTRACT_SID,DISCOUNT,HIERARCHY_NO,LEVEL_NAME
                                                  )'
				)

		EXEC (@SQL)
		

	
		IF OBJECT_ID('TEMPDB..#DISCOUNT_DATA_TABLE') IS NOT NULL
			DROP TABLE #DISCOUNT_DATA_TABLE

		SELECT HIERARCHY_NO
			,LEVEL_NAME
			,PERIOD
			,YEAR
			,DISCOUNT
		INTO #DISCOUNT_DATA_TABLE
		FROM (
			SELECT DISTINCT HIERARCHY_NO
				,LEVEL_NAME
			FROM #CCP
			) C
		CROSS JOIN (
			SELECT DISTINCT PERIOD
				,YEAR
			FROM #PERIOD
			WHERE PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
			) P
		CROSS JOIN (
			SELECT DISCOUNT
			FROM #DISCOUNT_INFO
			) D 



		IF OBJECT_ID('TEMPDB..#SALES_ACTUAL_DATA') IS NOT NULL
			DROP TABLE #SALES_ACTUAL_DATA

		CREATE TABLE #SALES_ACTUAL_DATA (
			CONTRACT_SALES_ACTUALS NUMERIC(22, 6)
			,CONTRACT_UNITS_ACTUALS NUMERIC(22, 6)
			,PERIOD VARCHAR(50)
			,[YEAR] INT
			,HIERARCHY_NO VARCHAR(100)
			,LEVEL_NAME VARCHAR(100)
			,DISCOUNT VARCHAR(100)
			)

		SET @D_SQL = CONCAT (
				'INSERT INTO #SALES_ACTUAL_DATA(
                     CONTRACT_SALES_ACTUALS ,
                     CONTRACT_UNITS_ACTUALS,
                     PERIOD  ,                
                     [YEAR],                  
                     HIERARCHY_NO,             
                     LEVEL_NAME ,DISCOUNT             
                     )
            SELECT CONTRACT_SALES_ACTUALS = SUM(ACTUAL_SALES),
                   CONTRACT_UNITS_ACTUALS = SUM(ACTUAL_UNITS),
                   PERIOD,
                   [YEAR],
                   HIERARCHY_NO,
                   LEVEL_NAME,
				   DISCOUNT
          
            FROM   '
				,@S_ACTUAL_TABLE
				,' NPS
                   INNER JOIN (SELECT DISTINCT  HIERARCHY_NO,CCP_DETAILS_SID,
                   LEVEL_NAME,
				   DISCOUNT FROM #RS_COMBINATION) RS ON NPS.CCP_DETAILS_SID=RS.CCP_DETAILS_SID
                   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID = NPS.PERIOD_SID
            GROUP  BY PERIOD,
                      [YEAR],
                      DISCOUNT,
                      HIERARCHY_NO,
                      LEVEL_NAME'
				)

		EXEC (@D_SQL)
      


		IF Object_id('TEMPDB..#SALES_DATA') IS NOT NULL
			DROP TABLE #SALES_DATA

		CREATE TABLE #SALES_DATA (
			CONTRACT_SALES_PROJECTED NUMERIC(22, 6)
			,CONTRACT_UNITS_PROJECTED NUMERIC(22, 6)
			,PERIOD VARCHAR(50)
			,[YEAR] INT
			,HIERARCHY_NO VARCHAR(100)
			,LEVEL_NAME VARCHAR(100)
			,DISCOUNT VARCHAR(100)
			)

		SET @D_SQL = CONCAT (
				'INSERT INTO #SALES_DATA(
                     CONTRACT_SALES_PROJECTED ,
                     CONTRACT_UNITS_PROJECTED,
                     PERIOD  ,                
                     [YEAR],                  
                     HIERARCHY_NO,             
                     LEVEL_NAME    
					 ,DISCOUNT          
                     )
            SELECT CONTRACT_SALES_PROJECTED = SUM(PROJECTION_SALES),
                   CONTRACT_UNITS_PROJECTED = SUM(PROJECTION_UNITS),
                   PERIOD,
                   [YEAR],
                   HIERARCHY_NO,
                   LEVEL_NAME
				   ,DISCOUNT
          
            FROM   '
				,@S_PROJECTION_TABLE
				,' NPS
                   INNER JOIN (SELECT DISTINCT  HIERARCHY_NO,CCP_DETAILS_SID,
                   LEVEL_NAME,
				   DISCOUNT FROM #RS_COMBINATION) RS ON NPS.CCP_DETAILS_SID=RS.CCP_DETAILS_SID
                   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID = NPS.PERIOD_SID
            GROUP  BY PERIOD,
                      [YEAR]
					  ,DISCOUNT,
                      HIERARCHY_NO,
                      LEVEL_NAME'
				)

		EXEC (@D_SQL)
 
 
	 
		IF Object_id('TEMPDB..#DISCOUNT_ACTUAL_DATA') IS NOT NULL
			DROP TABLE #DISCOUNT_ACTUAL_DATA

		CREATE TABLE #DISCOUNT_ACTUAL_DATA (
			CONTRACT_SALES_ACTUALS NUMERIC(22, 6)
			,DISCOUNT VARCHAR(50)
			,HIERARCHY_NO VARCHAR(100)
			,LEVEL_NAME VARCHAR(100)
			,PERIOD VARCHAR(50)
			,[YEAR] INT
			)

		SET @D_SQL = CONCAT (
				'      INSERT INTO #DISCOUNT_ACTUAL_DATA
                     (
                     CONTRACT_SALES_ACTUALS ,
                     DISCOUNT                 ,
                     HIERARCHY_NO               ,
                     LEVEL_NAME               ,
                     PERIOD                   ,
                     [YEAR]                  
                     )
          SELECT CONTRACT_SALES_ACTUALS = SUM(ACTUAL_SALES),
                   rs.DISCOUNT DISCOUNT,
                   rs.HIERARCHY_NO,
                   rs.LEVEL_NAME,
                   P.PERIOD,
                   P.YEAR
         
            FROM   '
				,@D_ACTUAL_TABLE
				,' NDP
                   INNER JOIN '
				,@D_MASTER_TABLE
				,' NDPM
                           ON NDPM.CCP_DETAILS_SID = NDP.CCP_DETAILS_SID
                                            AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                   INNER JOIN #RS_COMBINATION RS ON NDPM.CCP_DETAILS_SID=RS.CCP_DETAILS_SID
				   and NDPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
                   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID = NDP.PERIOD_SID
           
                  
            GROUP  BY rs.DISCOUNT,
                      rs.HIERARCHY_NO,
                      rs.LEVEL_NAME,
                      P.PERIOD,
                      P.YEAR'
				)


		EXEC (@D_SQL)
	
		IF Object_id('TEMPDB..#DISCOUNT_DATA') IS NOT NULL
			DROP TABLE #DISCOUNT_DATA

		CREATE TABLE #DISCOUNT_DATA (
			CONTRACT_SALES_PROJECTED NUMERIC(22, 6)
			,DISCOUNT VARCHAR(50)
			,HIERARCHY_NO VARCHAR(100)
			,LEVEL_NAME VARCHAR(100)
			,PERIOD VARCHAR(50)
			,[YEAR] INT
			)

		SET @D_SQL = CONCAT (
				'      INSERT INTO #DISCOUNT_DATA
                     (
                     CONTRACT_SALES_PROJECTED ,
                     DISCOUNT                 ,
                     HIERARCHY_NO               ,
                     LEVEL_NAME               ,
                     PERIOD                   ,
                     [YEAR]                  
                     )
            SELECT CONTRACT_SALES_PROJECTED = SUM(PROJECTION_SALES),
                   rs.DISCOUNT DISCOUNT,
                   rs.HIERARCHY_NO,
                   rs.LEVEL_NAME,
                   P.PERIOD,
                   P.YEAR
         
            FROM   '
				,@D_PROJECTION_TABLE
				,' NDP
                   INNER JOIN '
				,@D_MASTER_TABLE
				,' NDPM
                           ON NDPM.CCP_DETAILS_SID = NDP.CCP_DETAILS_SID
                                            AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                   INNER JOIN #RS_COMBINATION RS ON NDPM.CCP_DETAILS_SID=RS.CCP_DETAILS_SID
				   and NDPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
                   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID = NDP.PERIOD_SID
           
                  
            GROUP  BY rs.DISCOUNT,
                      rs.HIERARCHY_NO,
                      rs.LEVEL_NAME,
                      P.PERIOD,
                      P.YEAR'
				)


		EXEC (@D_SQL)


		IF Object_id('TEMPDB..#PPA_ACTUAL_DATA') IS NOT NULL
			DROP TABLE #PPA_ACTUAL_DATA

		CREATE TABLE #PPA_ACTUAL_DATA (
			CONTRACT_SALES_ACTUALS NUMERIC(22, 6)
			,DISCOUNT VARCHAR(50)
			,HIERARCHY_NO VARCHAR(100)
			,LEVEL_NAME VARCHAR(100)
			,PERIOD VARCHAR(50)
			,[YEAR] INT
			)

		SET @D_SQL = CONCAT (
				'INSERT INTO #PPA_ACTUAL_DATA
                     (
                     CONTRACT_SALES_ACTUALS,
                     DISCOUNT                ,
                     HIERARCHY_NO               ,
                     LEVEL_NAME              ,
                     PERIOD                  ,
                     [YEAR]                 
                     )
             SELECT CONTRACT_SALES_ACTUALS = SUM(ACTUAL_DISCOUNT_DOLLAR),
                   rs.DISCOUNT DISCOUNT,
                   rs.HIERARCHY_NO,
                   rs.LEVEL_NAME,
                   P.PERIOD,
                   P.YEAR
         
            FROM   '
				,@P_ACTUAL_TABLE
				,' NDP
                   INNER JOIN '
				,@P_MASTER_TABLE
				,' NDPM
                           ON NDPM.CCP_DETAILS_SID = NDP.CCP_DETAILS_SID
                                            AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                   INNER JOIN #RS_COMBINATION RS ON NDPM.CCP_DETAILS_SID=RS.CCP_DETAILS_SID
				   and NDPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
                   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID = NDP.PERIOD_SID
           
                  
            GROUP  BY rs.DISCOUNT,
                      rs.HIERARCHY_NO,
                      rs.LEVEL_NAME,
                      P.PERIOD,
                      P.YEAR'
				)

		EXEC (@D_SQL)
		

		IF Object_id('TEMPDB..#PPA_DATA') IS NOT NULL
			DROP TABLE #PPA_DATA

		CREATE TABLE #PPA_DATA (
			CONTRACT_SALES_PROJECTED NUMERIC(22, 6)
			,DISCOUNT VARCHAR(50)
			,HIERARCHY_NO VARCHAR(100)
			,LEVEL_NAME VARCHAR(100)
			,PERIOD VARCHAR(50)
			,[YEAR] INT
			)

		SET @D_SQL = CONCAT (
				'INSERT INTO #PPA_DATA
                     (
                     CONTRACT_SALES_PROJECTED,
                     DISCOUNT                ,
                     HIERARCHY_NO               ,
                     LEVEL_NAME              ,
                     PERIOD                  ,
                     [YEAR]                 
                     )
					  SELECT CONTRACT_SALES_PROJECTED = isnull(SUM(PROJECTION_DISCOUNT_DOLLAR),0),
                   rs.DISCOUNT DISCOUNT,
                   rs.HIERARCHY_NO,
                   rs.LEVEL_NAME,
                   P.PERIOD,
                   P.YEAR
         
            FROM   '
				,@P_PROJECTION_TABLE
				,' NDP
                   INNER JOIN '
				,@P_MASTER_TABLE
				,' NDPM
                           ON NDPM.CCP_DETAILS_SID = NDP.CCP_DETAILS_SID
                                            AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                   INNER JOIN #RS_COMBINATION RS ON NDPM.CCP_DETAILS_SID=RS.CCP_DETAILS_SID
				   and NDPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
                   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID = NDP.PERIOD_SID
           
                  
            GROUP  BY rs.DISCOUNT,
                      rs.HIERARCHY_NO,
                      rs.LEVEL_NAME,
                      P.PERIOD,
                      P.YEAR'
				)

		EXEC (@D_SQL)
            

		IF Object_id('TEMPDB.DBO.#CURRENT_SALES', 'U') IS NOT NULL
			DROP TABLE #CURRENT_SALES;

		SELECT FD.PERIOD
			,FD.YEAR
			,fd.DISCOUNT
			,FD.HIERARCHY_NO
			,FD.LEVEL_NAME
			,NM_ACTUAL_SALES = COALESCE((NAS.CONTRACT_SALES_ACTUALS), 0)
			,NM_ACTUAL_UNITS = COALESCE((NAS.CONTRACT_UNITS_ACTUALS), 0)
			,NM_PROJECTED_SALES = COALESCE((NSP.CONTRACT_SALES_PROJECTED), 0)
			,NM_PROJECTED_UNITS = COALESCE((NSP.CONTRACT_UNITS_PROJECTED), 0)
		INTO #CURRENT_SALES
		FROM (
			SELECT DISTINCT PERIOD
				,YEAR
				,DISCOUNT
				,HIERARCHY_NO
				,LEVEL_NAME
			FROM #DISCOUNT_DATA_TABLE
			) FD
		LEFT JOIN #SALES_ACTUAL_DATA NAS ON FD.YEAR = NAS.YEAR
			AND FD.PERIOD = NAS.PERIOD
			AND FD.HIERARCHY_NO = NAS.HIERARCHY_NO
			AND FD.LEVEL_NAME = NAS.LEVEL_NAME
			AND FD.DISCOUNT = NAS.DISCOUNT
		LEFT JOIN #SALES_DATA NSP ON NSP.YEAR = FD.YEAR
			AND NSP.PERIOD = FD.PERIOD
			AND NSP.HIERARCHY_NO = FD.HIERARCHY_NO
			AND NSP.LEVEL_NAME = FD.LEVEL_NAME
			AND FD.DISCOUNT = nsp.DISCOUNT
			 
		IF Object_id('TEMPDB.DBO.#CURRENT_DISCOUNT', 'U') IS NOT NULL
			DROP TABLE #CURRENT_DISCOUNT;

		SELECT FD.YEAR
			,FD.PERIOD
			,FD.DISCOUNT
			,ACTUAL_SALES = COALESCE((NAD.CONTRACT_SALES_ACTUALS), 0)
			,PROJECTION_SALES = COALESCE((NSP.CONTRACT_SALES_PROJECTED), 0)
			,FD.HIERARCHY_NO
			,FD.LEVEL_NAME
		INTO #CURRENT_DISCOUNT
		FROM (
			SELECT DISTINCT PERIOD
				,YEAR
				,DISCOUNT
				,HIERARCHY_NO
				,LEVEL_NAME
			FROM #DISCOUNT_DATA_TABLE D
			WHERE exists (select 1 from #RS_COMBINATION r join RS_CONTRACT rs on rs.RS_CONTRACT_SID=r.RS_CONTRACT_SID
			join HELPER_TABLE h on h.HELPER_TABLE_SID=rs.REBATE_PROGRAM_TYPE
			where h.DESCRIPTION<>'price protection' and d.DISCOUNT= r.DISCOUNT)
			) FD
		LEFT JOIN #DISCOUNT_DATA NSP ON FD.YEAR = NSP.YEAR
			AND FD.PERIOD = NSP.PERIOD
			AND FD.DISCOUNT = NSP.DISCOUNT
			AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
			AND FD.LEVEL_NAME = NSP.LEVEL_NAME
		LEFT JOIN #DISCOUNT_ACTUAL_DATA NAD ON NAD.YEAR = FD.YEAR
			AND NAD.PERIOD = FD.PERIOD
			AND NAD.DISCOUNT = FD.DISCOUNT
			AND NAD.HIERARCHY_NO = FD.HIERARCHY_NO
			AND NAD.LEVEL_NAME = FD.LEVEL_NAME
		 

		IF Object_id('TEMPDB.DBO.#CURRENT_PPA', 'U') IS NOT NULL
			DROP TABLE #CURRENT_PPA;

		SELECT FD.YEAR
			,FD.PERIOD
			,PPA_DISCOUNT_PROJECTED = COALESCE((NSP.CONTRACT_SALES_PROJECTED), 0)
			,PPA_ACTUAL_SALES = COALESCE((NAS.CONTRACT_SALES_ACTUALS), 0)
			,FD.DISCOUNT
			,FD.HIERARCHY_NO
			,FD.LEVEL_NAME
		INTO #CURRENT_PPA
		FROM (
			SELECT DISTINCT PERIOD
				,YEAR
				,DISCOUNT
				,HIERARCHY_NO
				,LEVEL_NAME
			FROM #DISCOUNT_DATA_TABLE D
			WHERE exists (select 1 from #RS_COMBINATION r join RS_CONTRACT rs on rs.RS_CONTRACT_SID=r.RS_CONTRACT_SID
			join HELPER_TABLE h on h.HELPER_TABLE_SID=rs.REBATE_PROGRAM_TYPE
			where h.DESCRIPTION='price protection' and d.DISCOUNT= r.DISCOUNT)
			
			) FD
		LEFT JOIN #PPA_ACTUAL_DATA NAS ON NAS.YEAR = FD.YEAR
			AND NAS.PERIOD = FD.PERIOD
			AND NAS.DISCOUNT = FD.DISCOUNT
			AND NAS.HIERARCHY_NO = FD.HIERARCHY_NO
			AND NAS.LEVEL_NAME = FD.LEVEL_NAME
		LEFT JOIN #PPA_DATA NSP ON FD.YEAR = NSP.YEAR
			AND FD.PERIOD = NSP.PERIOD
			AND FD.DISCOUNT = NSP.DISCOUNT
			AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
			AND FD.LEVEL_NAME = NSP.LEVEL_NAME
			
		IF Object_id('TEMPDB..#DPIVOT_TABLE') IS NOT NULL
			DROP TABLE #DPIVOT_TABLE

		CREATE TABLE #DPIVOT_TABLE (
			PROJECTION_MASTER_SID INT
			,LEVEL_NAME VARCHAR(100)
			,HIERARCHY_NO VARCHAR(100)
			,CP_INDICATOR CHAR(1)
			,PERIOD INT
			,YEAR INT
			,DISCOUNT_AMOUNT NUMERIC(22, 6)
			,DISCOUNT_AMOUNT_PROJECTED NUMERIC(22, 6)
			,DISCOUNT_RATE NUMERIC(22, 6)
			,DISCOUNT_RATE_PROJECTED NUMERIC(22, 6)
			,DISCOUNT_RPU NUMERIC(22, 6)
			,DISCOUNT_RPU_PROJECTED NUMERIC(22, 6)
			,DISCOUNT_OF_EX_FACTORY_ACTUALS NUMERIC(22, 6)
			,DISCOUNT_OF_EX_FACTORY_PROJECTED NUMERIC(22, 6)
			,DISCOUNT VARCHAR(100)
			)

		INSERT INTO #DPIVOT_TABLE (
			PROJECTION_MASTER_SID
			,DISCOUNT
			,CP_INDICATOR
			,DISCOUNT_AMOUNT
			,DISCOUNT_AMOUNT_PROJECTED
			,DISCOUNT_RATE
			,DISCOUNT_RATE_PROJECTED
			,DISCOUNT_RPU
			,DISCOUNT_RPU_PROJECTED
			,DISCOUNT_OF_EX_FACTORY_ACTUALS
			,DISCOUNT_OF_EX_FACTORY_PROJECTED
			,PERIOD
			,YEAR
			,HIERARCHY_NO
			,LEVEL_NAME
			)
		SELECT DISTINCT @FIRST_PROJ_SID AS PROJECTION_MASTER_SID
			,dt.DISCOUNT
			,@CP_INDICATOR CP_INDICATOR
			,DISCOUNT_AMOUNT = CASE 
				WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01)
					THEN DT.ACTUAL_SALES
				ELSE NULL
				END
			,DISCOUNT_AMOUNT_PROJECTED = DT.PROJECTION_SALES
			,DISCOUNT_RATE = CASE 
				WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01)
					THEN Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_SALES, 0), 0) * 100
				ELSE NULL
				END
			,DISCOUNT_RATE_PROJECTED = Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_SALES, 0), 0) * 100
			,DISCOUNT_RPU = CASE 
				WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01)
					THEN Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_UNITS, 0), 0)
				ELSE NULL
				END
			,DISCOUNT_RPU_PROJECTED = Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_UNITS, 0), 0)
			,DISCOUNT_OF_EX_FACTORY_ACTUALS = CASE 
				WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01)
					THEN Isnull(DT.ACTUAL_SALES / NULLIF(F.GTS_SALES_ACTUALS, 0), 0) * 100
				ELSE NULL
				END
			,DISCOUNT_OF_EX_FACTORY_PROJECTED = Isnull(DT.PROJECTION_SALES / NULLIF(F.GTS_SALES_PROJECTED, 0), 0) * 100
			,DT.PERIOD
			,DT.[YEAR]
			,DT.HIERARCHY_NO
			,DT.LEVEL_NAME
		FROM #CURRENT_DISCOUNT DT
		INNER JOIN #CURRENT_SALES S ON S.HIERARCHY_NO = DT.HIERARCHY_NO
			AND S.LEVEL_NAME = DT.LEVEL_NAME
			AND S.YEAR = DT.YEAR
			AND S.PERIOD = DT.PERIOD
			AND S.DISCOUNT = DT.DISCOUNT
		LEFT JOIN #FILE_DATA F ON F.YEAR = S.YEAR
			AND F.PERIOD = S.PERIOD
			AND S.HIERARCHY_NO = F.HIERARCHY_NO
			AND S.LEVEL_NAME = F.LEVEL_NAME
			AND S.DISCOUNT = F.DISCOUNT
		
		UNION ALL
		
		SELECT DISTINCT @FIRST_PROJ_SID AS PROJECTION_MASTER_SID
			,p.DISCOUNT
			,@CP_INDICATOR CP_INDICATOR
			,DISCOUNT_AMOUNT = CASE 
				WHEN @CURRENT_DATE > Datefromparts(P.[YEAR], P.PERIOD, 01)
					THEN P.PPA_ACTUAL_SALES
				ELSE NULL
				END
			,DISCOUNT_AMOUNT_PROJECTED = PPA_DISCOUNT_PROJECTED
			,CASE 
				WHEN @CURRENT_DATE > Datefromparts(P.[YEAR], P.PERIOD, 01)
					THEN isnull(P.PPA_ACTUAL_SALES / NULLIF(S.NM_ACTUAL_SALES, 0), 0) * 100
				ELSE NULL
				END
			,Isnull(PPA_DISCOUNT_PROJECTED / NULLIF(S.NM_PROJECTED_SALES, 0), 0) * 100
			,CASE 
				WHEN @CURRENT_DATE > Datefromparts(P.[YEAR], P.PERIOD, 01)
					THEN isnull(P.PPA_ACTUAL_SALES / NULLIF(S.NM_ACTUAL_UNITS, 0), 0)
				ELSE NULL
				END
			,Isnull(PPA_DISCOUNT_PROJECTED / NULLIF(S.NM_PROJECTED_UNITS, 0), 0)
			,CASE 
				WHEN @CURRENT_DATE > Datefromparts(P.[YEAR], P.PERIOD, 01)
					THEN Isnull(P.PPA_ACTUAL_SALES / NULLIF(F.GTS_SALES_ACTUALS, 0), 0) * 100
				ELSE NULL
				END
			,Isnull(P.PPA_DISCOUNT_PROJECTED / NULLIF(F.GTS_SALES_PROJECTED, 0), 0) * 100
			,P.PERIOD
			,P.[YEAR]
			,P.HIERARCHY_NO
			,P.LEVEL_NAME
		FROM #CURRENT_PPA P
		INNER JOIN #CURRENT_SALES S ON S.HIERARCHY_NO = p.HIERARCHY_NO
			AND S.LEVEL_NAME = p.LEVEL_NAME
			AND S.YEAR = p.YEAR
			AND S.PERIOD = p.PERIOD
			AND S.DISCOUNT = p.DISCOUNT
		LEFT JOIN #FILE_DATA F ON F.YEAR = P.YEAR
			AND F.PERIOD = P.PERIOD
			AND p.HIERARCHY_NO = F.HIERARCHY_NO
			AND p.LEVEL_NAME = F.LEVEL_NAME
			AND p.DISCOUNT = F.DISCOUNT
			
		IF EXISTS (
				SELECT 1
				FROM #PROJECTION_MASTER
				WHERE ID > 1
				)
		BEGIN
			IF Object_id('TEMPDB..#DPRIOR_PROJECTIONS') IS NOT NULL
				DROP TABLE #DPRIOR_PROJECTIONS

			SELECT PD.PROJECTION_DETAILS_SID
				,PM.PROJECTION_MASTER_SID
				,HIERARCHY_NO
				,LEVEL_NAME
			INTO #DPRIOR_PROJECTIONS
			FROM #PROJECTION_MASTER PM
			INNER JOIN PROJECTION_DETAILS PD ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
			INNER JOIN #CCP C ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
			WHERE PM.ID <> 1

			IF Object_id('TEMPDB..#DPRIOR_DATA_TABLE') IS NOT NULL
				DROP TABLE #DPRIOR_DATA_TABLE

			SELECT PROJECTION_MASTER_SID
				,HIERARCHY_NO
				,LEVEL_NAME
				,PERIOD
				,YEAR
				,DISCOUNT
			INTO #DPRIOR_DATA_TABLE
			FROM (
				SELECT DISTINCT HIERARCHY_NO
					,LEVEL_NAME
				FROM #CCP
				) C
			CROSS JOIN (
				SELECT DISTINCT PERIOD
					,YEAR
				FROM #PERIOD
				WHERE PERIOD_SID BETWEEN @START_PERIOD_SID
						AND @END_PERIOD_SID
				) P
			CROSS JOIN #PROJECTION_MASTER PM
			CROSS JOIN (
				SELECT DISCOUNT
				FROM #DISCOUNT_INFO
				) D
			WHERE PM.ID <> 1


			IF OBJECT_ID('TEMPDB..#DPRIOR_ACTUAL_SALES_DATA') IS NOT NULL
				DROP TABLE #DPRIOR_ACTUAL_SALES_DATA

			SELECT PROJECTION_MASTER_SID,
				   HIERARCHY_NO,
				   LEVEL_NAME,
				   DISCOUNT,
				   PERIOD,
				   [YEAR],
				   SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
				   SUM(ACTUAL_UNITS ) AS CONTRACT_UNITS_ACTUALS
				INTO #DPRIOR_ACTUAL_SALES_DATA
				FROM (SELECT DISTINCT  PP.PROJECTION_MASTER_SID
				,PP.HIERARCHY_NO
				,PP.LEVEL_NAME
				,RC.DISCOUNT
				,PD.PROJECTION_DETAILS_SID
				FROM #DPRIOR_PROJECTIONS PP
			INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
			INNER JOIN #RS_COMBINATION RC ON RC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
			AND RC.HIERARCHY_NO=PP.HIERARCHY_NO AND RC.LEVEL_NAME=PP.LEVEL_NAME) S 
				INNER JOIN #PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
			LEFT JOIN NM_ACTUAL_SALES NPS ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
				AND P.PERIOD_SID = NPS.PERIOD_SID 
				GROUP BY PROJECTION_MASTER_SID,HIERARCHY_NO,LEVEL_NAME,DISCOUNT,PERIOD
				,[YEAR]
			

		IF Object_id('TEMPDB..#DPRIOR_SALES_DATA') IS NOT NULL
				DROP TABLE #DPRIOR_SALES_DATA
			SELECT PROJECTION_MASTER_SID,
				   HIERARCHY_NO,
				   LEVEL_NAME,
				   DISCOUNT,
				   PERIOD,
				   [YEAR],
				   SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
				   SUM(PROJECTION_UNITS ) AS CONTRACT_UNITS_PROJECTED
				INTO #DPRIOR_SALES_DATA
				FROM (SELECT DISTINCT  PP.PROJECTION_MASTER_SID
				,PP.HIERARCHY_NO
				,PP.LEVEL_NAME
				,RC.DISCOUNT
				,PD.PROJECTION_DETAILS_SID
				FROM #DPRIOR_PROJECTIONS PP
			INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
			INNER JOIN #RS_COMBINATION RC ON RC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
			AND RC.HIERARCHY_NO=PP.HIERARCHY_NO AND RC.LEVEL_NAME=PP.LEVEL_NAME) S 
				INNER JOIN #PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
			LEFT JOIN NM_SALES_PROJECTION NPS ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
				AND P.PERIOD_SID = NPS.PERIOD_SID 
				GROUP BY PROJECTION_MASTER_SID,HIERARCHY_NO,LEVEL_NAME,DISCOUNT,PERIOD
				,[YEAR]
				
			IF Object_id('TEMPDB..#PRIOR_ACTUAL_DISCOUNT_DATA') IS NOT NULL
				DROP TABLE #PRIOR_ACTUAL_DISCOUNT_DATA


				SELECT PROJECTION_MASTER_SID,
				   HIERARCHY_NO,
				   LEVEL_NAME,
				   DISCOUNT,
				   PERIOD,
				   [YEAR],
				   isnull(SUM(ACTUAL_SALES),0) AS CONTRACT_SALES_ACTUALS	
				   into #PRIOR_ACTUAL_DISCOUNT_DATA
				FROM (SELECT DISTINCT  PP.PROJECTION_MASTER_SID
				,PP.HIERARCHY_NO
				,PP.LEVEL_NAME
				,RC.DISCOUNT
				,PD.PROJECTION_DETAILS_SID
				FROM #DPRIOR_PROJECTIONS PP
			INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
			INNER JOIN #RS_COMBINATION RC ON RC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
			AND RC.HIERARCHY_NO=PP.HIERARCHY_NO AND RC.LEVEL_NAME=PP.LEVEL_NAME
			WHERE exists (select 1 from #RS_COMBINATION r join RS_CONTRACT rs on rs.RS_CONTRACT_SID=r.RS_CONTRACT_SID
			join HELPER_TABLE h on h.HELPER_TABLE_SID=rs.REBATE_PROGRAM_TYPE
			where h.DESCRIPTION<>'price protection' and rc.DISCOUNT= r.DISCOUNT)
			) S 
				INNER JOIN #PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
			LEFT JOIN NM_ACTUAL_DISCOUNT NPS ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
				AND P.PERIOD_SID = NPS.PERIOD_SID 
				GROUP BY PROJECTION_MASTER_SID,HIERARCHY_NO,LEVEL_NAME,DISCOUNT,PERIOD
				,[YEAR]

			IF Object_id('TEMPDB..#PRIOR_DISCOUNT_DATA') IS NOT NULL
				DROP TABLE #PRIOR_DISCOUNT_DATA


				SELECT PROJECTION_MASTER_SID,
				   HIERARCHY_NO,
				   LEVEL_NAME,
				   DISCOUNT,
				   PERIOD,
				   [YEAR],
				   isnull(SUM(PROJECTION_SALES),0) AS CONTRACT_SALES_PROJECTED	
				into #PRIOR_DISCOUNT_DATA
				FROM (SELECT DISTINCT  PP.PROJECTION_MASTER_SID
				,PP.HIERARCHY_NO
				,PP.LEVEL_NAME
				,RC.DISCOUNT
				,PD.PROJECTION_DETAILS_SID
				FROM #DPRIOR_PROJECTIONS PP
			INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
			INNER JOIN #RS_COMBINATION RC ON RC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
			AND RC.HIERARCHY_NO=PP.HIERARCHY_NO AND RC.LEVEL_NAME=PP.LEVEL_NAME
			WHERE exists (select 1 from #RS_COMBINATION r join RS_CONTRACT rs on rs.RS_CONTRACT_SID=r.RS_CONTRACT_SID
			join HELPER_TABLE h on h.HELPER_TABLE_SID=rs.REBATE_PROGRAM_TYPE
			where h.DESCRIPTION<>'price protection' and rc.DISCOUNT= r.DISCOUNT)
			) S 
				INNER JOIN #PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
			LEFT JOIN NM_DISCOUNT_PROJECTION NPS ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
				AND P.PERIOD_SID = NPS.PERIOD_SID 
				GROUP BY PROJECTION_MASTER_SID,HIERARCHY_NO,LEVEL_NAME,DISCOUNT,PERIOD
				,[YEAR] 				

			IF Object_id('TEMPDB..#PRIOR_ACTUAL_PPA_DATA') IS NOT NULL
				DROP TABLE #PRIOR_ACTUAL_PPA_DATA

				
				SELECT PROJECTION_MASTER_SID,
				   HIERARCHY_NO,
				   LEVEL_NAME,
				   DISCOUNT,
				   PERIOD,
				   [YEAR],
				   isnull(SUM(ACTUAL_DISCOUNT_DOLLAR),0) AS CONTRACT_SALES_ACTUALS	
				into #PRIOR_ACTUAL_PPA_DATA
				FROM (SELECT DISTINCT  PP.PROJECTION_MASTER_SID
				,PP.HIERARCHY_NO
				,PP.LEVEL_NAME
				,RC.DISCOUNT
				,PD.PROJECTION_DETAILS_SID
				FROM #DPRIOR_PROJECTIONS PP
			INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
			INNER JOIN #RS_COMBINATION RC ON RC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
			AND RC.HIERARCHY_NO=PP.HIERARCHY_NO AND RC.LEVEL_NAME=PP.LEVEL_NAME
			WHERE exists (select 1 from #RS_COMBINATION r join RS_CONTRACT rs on rs.RS_CONTRACT_SID=r.RS_CONTRACT_SID
			join HELPER_TABLE h on h.HELPER_TABLE_SID=rs.REBATE_PROGRAM_TYPE
			where h.DESCRIPTION='price protection' and rc.DISCOUNT= r.DISCOUNT)
			) S 
				INNER JOIN #PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
			LEFT JOIN NM_ACTUAL_PPA NPS ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
				AND P.PERIOD_SID = NPS.PERIOD_SID 
				GROUP BY PROJECTION_MASTER_SID,HIERARCHY_NO,LEVEL_NAME,DISCOUNT,PERIOD
				,[YEAR] 				


	

			IF Object_id('TEMPDB..#PRIOR_PPA_DATA') IS NOT NULL
				DROP TABLE #PRIOR_PPA_DATA

				SELECT PROJECTION_MASTER_SID,
				   HIERARCHY_NO,
				   LEVEL_NAME,
				   DISCOUNT,
				   PERIOD,
				   [YEAR],
				   isnull(SUM(PROJECTION_DISCOUNT_DOLLAR),0) AS CONTRACT_SALES_PROJECTED	
				into #PRIOR_PPA_DATA
				FROM (SELECT DISTINCT  PP.PROJECTION_MASTER_SID
				,PP.HIERARCHY_NO
				,PP.LEVEL_NAME
				,RC.DISCOUNT
				,PD.PROJECTION_DETAILS_SID
				FROM #DPRIOR_PROJECTIONS PP
			INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
			INNER JOIN #RS_COMBINATION RC ON RC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
			AND RC.HIERARCHY_NO=PP.HIERARCHY_NO AND RC.LEVEL_NAME=PP.LEVEL_NAME
			WHERE exists (select 1 from #RS_COMBINATION r join RS_CONTRACT rs on rs.RS_CONTRACT_SID=r.RS_CONTRACT_SID
			join HELPER_TABLE h on h.HELPER_TABLE_SID=rs.REBATE_PROGRAM_TYPE
			where h.DESCRIPTION='price protection' and rc.DISCOUNT= r.DISCOUNT)
			) S 
				INNER JOIN #PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
			LEFT JOIN NM_PPA_PROJECTION NPS ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
				AND P.PERIOD_SID = NPS.PERIOD_SID 
				GROUP BY PROJECTION_MASTER_SID,HIERARCHY_NO,LEVEL_NAME,DISCOUNT,PERIOD
				,[YEAR] 	

			

			IF Object_id('TEMPDB.DBO.#PRIOR_SALES', 'U') IS NOT NULL
				DROP TABLE #PRIOR_SALES;

			SELECT FD.PROJECTION_MASTER_SID
				,FD.YEAR
				,FD.PERIOD
				,FD.HIERARCHY_NO
				,FD.LEVEL_NAME
				,FD.DISCOUNT
				,NM_ACTUAL_SALES = COALESCE((NAS.CONTRACT_SALES_ACTUALS), 0)
				,NM_ACTUAL_UNITS = COALESCE((NAS.CONTRACT_UNITS_ACTUALS), 0)
				,NM_PROJECTED_SALES = COALESCE((NSP.CONTRACT_SALES_PROJECTED), 0)
				,NM_PROJECTED_UNITS = COALESCE((NSP.CONTRACT_UNITS_PROJECTED), 0)
			INTO #PRIOR_SALES
			FROM (
				SELECT DISTINCT YEAR
					,PERIOD
					,HIERARCHY_NO
					,LEVEL_NAME
					 ,DISCOUNT
					,PROJECTION_MASTER_SID
				FROM #DPRIOR_DATA_TABLE
				) FD
			LEFT JOIN #DPRIOR_SALES_DATA NSP ON FD.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID
				AND FD.YEAR = NSP.YEAR
				AND FD.PERIOD = NSP.PERIOD
				AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
				AND FD.DISCOUNT = NSP.DISCOUNT
			LEFT JOIN #DPRIOR_ACTUAL_SALES_DATA NAS ON FD.PROJECTION_MASTER_SID = NAS.PROJECTION_MASTER_SID
				AND FD.YEAR = NAS.YEAR
				AND FD.PERIOD = NAS.PERIOD
				AND FD.HIERARCHY_NO = NAS.HIERARCHY_NO
				AND FD.LEVEL_NAME = NAS.LEVEL_NAME
				AND FD.DISCOUNT = NAS.DISCOUNT

			IF Object_id('TEMPDB.DBO.#PRIOR_DISCOUNT', 'U') IS NOT NULL
				DROP TABLE #PRIOR_DISCOUNT;

			SELECT FD.PROJECTION_MASTER_SID
				,FD.YEAR
				,FD.PERIOD
				,ACTUAL_SALES = COALESCE((NAD.CONTRACT_SALES_ACTUALS), 0)
				,PROJECTION_SALES = COALESCE((NSP.CONTRACT_SALES_PROJECTED), 0)
				,FD.DISCOUNT
				,FD.HIERARCHY_NO
				,FD.LEVEL_NAME
			INTO #PRIOR_DISCOUNT
			FROM (
				SELECT YEAR
					,PERIOD
					,HIERARCHY_NO
					,LEVEL_NAME
					,DISCOUNT
					,PROJECTION_MASTER_SID
				FROM #DPRIOR_DATA_TABLE A
				WHERE EXISTS (
						SELECT 1
						FROM #PRIOR_DISCOUNT_DATA B
						WHERE A.DISCOUNT = B.DISCOUNT
						)
				) FD
			LEFT JOIN #PRIOR_DISCOUNT_DATA NSP ON FD.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID
				AND FD.YEAR = NSP.YEAR
				AND FD.PERIOD = NSP.PERIOD
				AND FD.DISCOUNT = NSP.DISCOUNT
				AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
				AND FD.LEVEL_NAME = NSP.LEVEL_NAME
			LEFT JOIN #PRIOR_ACTUAL_DISCOUNT_DATA NAD ON FD.PROJECTION_MASTER_SID = NAD.PROJECTION_MASTER_SID
				AND FD.YEAR = NAD.YEAR
				AND FD.PERIOD = NAD.PERIOD
				AND FD.DISCOUNT = NAD.DISCOUNT
				AND FD.HIERARCHY_NO = NAD.HIERARCHY_NO
				AND FD.LEVEL_NAME = NAD.LEVEL_NAME
				
			IF Object_id('TEMPDB.DBO.#PRIOR_PPA', 'U') IS NOT NULL
				DROP TABLE #PRIOR_PPA;

			SELECT FD.PROJECTION_MASTER_SID
				,FD.YEAR
				,FD.PERIOD
				,PPA_ACTUAL = COALESCE((NSP.CONTRACT_SALES_ACTUALS), 0)
				,PPA_DISCOUNT_PROJECTED = COALESCE((NAP.CONTRACT_SALES_PROJECTED), 0)
				,FD.DISCOUNT
				,FD.HIERARCHY_NO
				,FD.LEVEL_NAME
			INTO #PRIOR_PPA
			FROM (
				SELECT YEAR
					,PERIOD
					,HIERARCHY_NO
					,LEVEL_NAME
					,DISCOUNT
					,PROJECTION_MASTER_SID
				FROM #DPRIOR_DATA_TABLE A
				WHERE NOT EXISTS (
						SELECT 1
						FROM #PRIOR_DISCOUNT_DATA B
						WHERE A.DISCOUNT = B.DISCOUNT
						)
				) FD
			LEFT JOIN #PRIOR_ACTUAL_PPA_DATA NSP ON FD.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID
				AND fd.YEAR = NSP.YEAR
				AND FD.PERIOD = NSP.PERIOD
				AND FD.DISCOUNT = NSP.DISCOUNT
				AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
				AND FD.LEVEL_NAME = NSP.LEVEL_NAME
			LEFT JOIN #PRIOR_PPA_DATA NAP ON FD.PROJECTION_MASTER_SID = NAP.PROJECTION_MASTER_SID
				AND FD.YEAR = NAP.YEAR
				AND FD.PERIOD = NAP.PERIOD
				AND FD.DISCOUNT = NAP.DISCOUNT
				AND FD.HIERARCHY_NO = NAP.HIERARCHY_NO
				AND FD.LEVEL_NAME = NAP.LEVEL_NAME

			--    select distinct * from   #DPIVOT_TABLE order by PROJECTION_MASTER_SID,HIERARCHY_NO,year,period
			INSERT INTO #DPIVOT_TABLE (
				PROJECTION_MASTER_SID
				,DISCOUNT
				,CP_INDICATOR
				,DISCOUNT_AMOUNT
				,DISCOUNT_AMOUNT_PROJECTED
				,DISCOUNT_RATE
				,DISCOUNT_RATE_PROJECTED
				,DISCOUNT_RPU
				,DISCOUNT_RPU_PROJECTED
				,DISCOUNT_OF_EX_FACTORY_ACTUALS
				,DISCOUNT_OF_EX_FACTORY_PROJECTED
				,PERIOD
				,YEAR
				,HIERARCHY_NO
				,LEVEL_NAME
				)
			SELECT DISTINCT s.PROJECTION_MASTER_SID
				,dt.DISCOUNT
				,@CP_INDICATOR CP_INDICATOR
				,DISCOUNT_AMOUNT = DT.ACTUAL_SALES
				,DISCOUNT_AMOUNT_PROJECTED = DT.PROJECTION_SALES
				,DISCOUNT_RATE = Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_SALES, 0), 0) * 100
				,DISCOUNT_RATE_PROJECTED = Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_SALES, 0), 0) * 100
				,DISCOUNT_RPU = Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_UNITS, 0), 0)
				,DISCOUNT_RPU = Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_UNITS, 0), 0)
				,Isnull(DT.ACTUAL_SALES / NULLIF(F.GTS_SALES_ACTUALS, 0), 0) * 100
				,Isnull(DT.PROJECTION_SALES / NULLIF(F.GTS_SALES_PROJECTED, 0), 0) * 100
				,s.PERIOD
				,s.[YEAR]
				,s.HIERARCHY_NO
				,s.LEVEL_NAME
			FROM #PRIOR_SALES S
			JOIN #PRIOR_DISCOUNT DT ON S.PROJECTION_MASTER_SID = DT.PROJECTION_MASTER_SID
				AND DT.HIERARCHY_NO = S.HIERARCHY_NO
				AND DT.LEVEL_NAME = S.LEVEL_NAME
				AND DT.PERIOD = S.PERIOD
				AND DT.[YEAR] = S.[YEAR]
				AND DT.DISCOUNT = S.DISCOUNT
			LEFT JOIN (
				SELECT PF.PROJECTION_MASTER_SID
					,P.PERIOD
					,P.YEAR
					,dp.HIERARCHY_NO
					,dp.LEVEL_NAME
					,dp.DISCOUNT
					,GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES)
					,GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES))
				FROM #PRODUCT_FILE_TEMP PF
				JOIN #PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID
				JOIN (
					SELECT DISTINCT DP.PROJECTION_MASTER_SID
						,dp.HIERARCHY_NO
						,dp.LEVEL_NAME
						,rs.DISCOUNT
						,rs.ITEM_MASTER_SID
					FROM #DPRIOR_PROJECTIONS DP
					JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_MASTER_SID = DP.PROJECTION_MASTER_SID
						AND PD.PROJECTION_DETAILS_SID = DP.PROJECTION_DETAILS_SID
					JOIN #RS_COMBINATION RS ON RS.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
					) DP ON DP.PROJECTION_MASTER_SID = PF.PROJECTION_MASTER_SID
					AND DP.ITEM_MASTER_SID = PF.ITEM_MASTER_SID
				GROUP BY PF.PROJECTION_MASTER_SID
					,dp.HIERARCHY_NO
					,dp.LEVEL_NAME
					,dp.DISCOUNT
					,P.PERIOD
					,P.YEAR
				) F ON F.PROJECTION_MASTER_SID = DT.PROJECTION_MASTER_SID
				AND F.YEAR = DT.YEAR
				AND F.PERIOD = DT.PERIOD
				AND F.HIERARCHY_NO = DT.HIERARCHY_NO
				AND F.LEVEL_NAME = DT.LEVEL_NAME
				AND F.DISCOUNT = DT.DISCOUNT
			
			UNION ALL
			
			SELECT P.PROJECTION_MASTER_SID
				,P.DISCOUNT
				,@CP_INDICATOR CP_INDICATOR
				,PPA_ACTUAL
				,DISCOUNT_AMOUNT_PROJECTED = P.PPA_DISCOUNT_PROJECTED
				,PPA_RATE = isnull(PPA_ACTUAL / nullif(s.NM_ACTUAL_SALES, 0), 0) * 100
				,PPA_RATE_PROJECTION = isnull(PPA_DISCOUNT_PROJECTED / nullif(s.NM_ACTUAL_SALES, 0), 0) * 100
				,PPA_RPU = isnull(PPA_ACTUAL / nullif(s.NM_PROJECTED_SALES, 0), 0)
				,PPA_RPU_PROJECTION = isnull(PPA_DISCOUNT_PROJECTED / nullif(s.NM_PROJECTED_UNITS, 0), 0)
				,Isnull(P.PPA_ACTUAL / NULLIF(F.GTS_SALES_ACTUALS, 0), 0) * 100
				,Isnull(P.PPA_DISCOUNT_PROJECTED / NULLIF(F.GTS_SALES_PROJECTED, 0), 0) * 100
				,P.PERIOD
				,P.[YEAR]
				,P.HIERARCHY_NO
				,P.LEVEL_NAME
			FROM #PRIOR_PPA P
			JOIN #PRIOR_SALES S ON P.PROJECTION_MASTER_SID = S.PROJECTION_MASTER_SID
				AND S.HIERARCHY_NO = P.HIERARCHY_NO
				AND P.LEVEL_NAME = S.LEVEL_NAME
				AND P.YEAR = S.YEAR
				AND P.PERIOD = S.PERIOD
				AND P.DISCOUNT = S.DISCOUNT
			LEFT JOIN (
				SELECT PF.PROJECTION_MASTER_SID
					,P.PERIOD
					,P.YEAR
					,dp.HIERARCHY_NO
					,dp.LEVEL_NAME
					,dp.DISCOUNT
					,GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES)
					,GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES))
				FROM #PRODUCT_FILE_TEMP PF
				JOIN #PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID
				JOIN (
					SELECT DISTINCT dp.PROJECTION_MASTER_SID
						,dp.HIERARCHY_NO
						,dp.level_name
						,rs.item_master_Sid
						,rs.DISCOUNT
					FROM #DPRIOR_PROJECTIONS dp
					JOIN PROJECTION_DETAILS pd ON pd.PROJECTION_MASTER_SID = dp.PROJECTION_MASTER_SID
						AND pd.projection_details_Sid = dp.projection_details_Sid
					JOIN #RS_COMBINATION RS ON RS.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
					) dp ON dp.PROJECTION_MASTER_SID = pf.PROJECTION_MASTER_SID
					AND dp.ITEM_MASTER_SID = pf.ITEM_MASTER_SID
				GROUP BY PF.PROJECTION_MASTER_SID
					,dp.HIERARCHY_NO
					,dp.LEVEL_NAME
					,dp.DISCOUNT
					,P.PERIOD
					,P.YEAR
				) F ON F.PROJECTION_MASTER_SID = P.PROJECTION_MASTER_SID
				AND F.YEAR = P.YEAR
				AND F.PERIOD = P.PERIOD
				AND F.HIERARCHY_NO = P.HIERARCHY_NO
				AND F.LEVEL_NAME = P.LEVEL_NAME
				AND F.DISCOUNT = P.DISCOUNT
				
		END
              DECLARE @SQL2 NVARCHAR(MAX)
                     ,@LOOP_CNTR1 INT
                     ,@MAX_CCP1 INT
 
              SET @SQL2 = '
                SELECT DISCOUNT,pr.HIERARCHY_NO,
                   PERIOD,
                   YEAR, '
              SET @LOOP_CNTR1 = 1
              SET @MAX_CCP1 = (
                           SELECT Max(ID)
                           FROM #PROJECTION_MASTER
                           )
 
              WHILE @LOOP_CNTR1 <= @MAX_CCP1
              BEGIN
                     SET @SQL2 += ' DISCOUNT_AMOUNT_' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' THEN DISCOUNT_AMOUNT END),
                                                   DISCOUNT_AMOUNT_PROJECTED_' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' THEN DISCOUNT_AMOUNT_PROJECTED END),0),
                     DISCOUNT_RATE_' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' THEN DISCOUNT_RATE END),
                     DISCOUNT_RATE_PROJECTED_' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' THEN DISCOUNT_RATE_PROJECTED END),0),
                                  DISCOUNT_RPU_' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' THEN DISCOUNT_RPU END),
                                  DISCOUNT_RPU_PROJECTED_' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) +
                           ' THEN DISCOUNT_RPU_PROJECTED END),0),
                                  DISCOUNT_OF_EX_FACTORY_ACTUALS_' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' = MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),
                                  DISCOUNT_OF_EX_FACTORY_PROJECTED_' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR1 AS VARCHAR(10)) + ' THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0),'
                     SET @LOOP_CNTR1 += 1
              END
 
              --SET @SQL2 = LEFT(@SQL2, Len(@SQL2) - 1)
              SET @SQL2 += ' c.PARENT_HIERARCHY_NO FROM   #DPIVOT_TABLE PR JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID  = PR.PROJECTION_MASTER_SID
                     join #CCP c on pr.HIERARCHY_NO = c.HIERARCHY_NO
                GROUP  BY c.level_no,DISCOUNT,pr.LEVEL_NAME,pr.HIERARCHY_NO,CP_INDICATOR,PERIOD,YEAR       ,c.PARENT_HIERARCHY_NO                         
                ORDER BY c.level_no,pr.HIERARCHY_NO,c.PARENT_HIERARCHY_NO  ,' + CASE
                           WHEN (
                                         @VIEW_TAB = 'PIVOT'
                                         AND @VIEW = 'DETAIL_DISCOUNT'
                                         )
                                  THEN 'YEAR,PERIOD'
                           ELSE 'DISCOUNT,YEAR,PERIOD'
                           END
 
              EXEC Sp_executesql @SQL2
       END
END






GO


