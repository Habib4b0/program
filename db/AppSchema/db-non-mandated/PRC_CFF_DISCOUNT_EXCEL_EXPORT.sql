IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_CFF_DISCOUNT_EXCEL_EXPORT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_CFF_DISCOUNT_EXCEL_EXPORT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_CFF_DISCOUNT_EXCEL_EXPORT](@CFF_MASTER_SID         VARCHAR(500),
                                                       @PROJ_FREQUENCY         VARCHAR(20),
                                                       @SCREEN_NAME            VARCHAR(50),
                                                       @VIEW                   VARCHAR(50) = NULL,
                                                       @DISCOUNT_VIEW          VARCHAR(50) = NULL,
                                                       @DISCOUNT_LEVEL         VARCHAR(50),
                                                       @CP_INDICATOR           CHAR(1),
                                                       @HIERARCHY_NO           VARCHAR(500) = NULL,
                                                       @LEVEL_NO               INT = NULL,
                                                       @CUSTOM_VIEW_MASTER_SID INT = NULL,
                                                       @START_DATE             DATETIME = NULL,
                                                       @USER_ID                INT,
                                                       @SESSION_ID             VARCHAR(50))
AS
BEGIN
  /**********************************************************************************************************
  ** FILE NAME		:	PRC_CFF_DISCOUNT_EXCEL_EXPORT.SQL
  ** PROCEDURE NAME	:	PRC_CFF_DISCOUNT_EXCEL_EXPORT
  ** DESCRIPTION		:	  aggregation of projections attached to the particualr cff with hierarchy level showing as output for both cff_results screen and cff_varicnace screen
  ** INPUT PARAMETERS	:	@CFF_MASTER_SID	         - passing input as cff_master_sid
  						@PROJ_FREQUENCY          - based on frequency it will display the data  monthly,quarterly semi annualy and annulaly
                          @SCREEN_NAME             - based on screen name if we pass assumptions it will display aggregation of actuals, projections attached to the first cff
  						             	                    screen_name<> assumptions it will display rest of cffs  display aggregation of projections attached to each cff as pivot view
                          @DISCOUNTVIEW            - if we pass view  as program means it wil dispaly based on rs_ame else price protection
  						@DISCOUNT_LEVEL          - if we pass level  as program means it wil dispaly based on rs_ame else price protection
  						@CP_INDICATOR            - if we pass indicator as c it will dispaly information about CUSTOMER_HIERARCHY_LEVEL  and pass p means display information about PRODUCT_HIERARCHY_LEVEL
  						@HIERARCHY_NO            - based on the hierarchy number it will display the information 
  						@LEVEL_NO                - based on level name chossen by user it will display the information
                          @START_DATE              - From the @start_date only it will generate output
  				        @CUSTOM_VIEW_MASTER_SID  - for each cff one customeview master will be present so we pass we pass customeviewmastersid for paricular cff it will displau information
  ** OUTPUT PARAMETERS:	na
  ** AUTHOR NAME		:	@pradeep thanga, @leela venkatesh
  ** CREATION DATE	:	28/03/2016 - 
  ** CALLED BY		:   from application side they click on projection  results screen the inside that proecure will call dispaly information 
  **********************************************************************************************************
  ** CHANGE HISTORY
  **********************************************************************************************************
  ** VER   DATE      TICKET NO              AUTHOR              DESCRIPTION 
  ** ---   --------  ---------             -------------        -----------------------------
  ** 1    09/23/2016 GAL-3722/GALUAT-377   @LEELA VENKATESH    sales inclusion & deduction inclusion logic id salesinclustion is 0 then we need to display as null else we need display values
  ** 2    11/02/2016 GAL-7472/GALUAT-543   @LEELA VENKATESH    removing returns part and what ever flavour choosen by user that should appear in the screen
  *********************************************************************************************************/
      -------------declaring variables--------------------------
      DECLARE @SP                            INT,
              @SP_PROJ_SID                   INT,
              @TEMP_PROJ_SID                 VARCHAR(500),
              @CUST_RELATIONSHIP_BUILDER_SID INT
      DECLARE @FIRST_PROJ_SID                INT,
              @PROD_RELATIONSHIP_BUILDER_SID INT,
              @MIN_LEVEL                     INT,
              @PIVOT_START_PERIOD_SID        INT
      DECLARE @ITEMID [DBO].[UDT_ITEM]

      -----------------PASSING MULTIPLE CFF AS INPUT CONVERTING INTO COMMA SEPERATED VALUES --------------------------
      IF Object_id('TEMPDB..#CFF_PROJECTION_MASTER') IS NOT NULL
        TRUNCATE TABLE #CFF_PROJECTION_MASTER
      ELSE
        CREATE TABLE #CFF_PROJECTION_MASTER
          (
             ID                        INT IDENTITY(1, 1),
             CFF_PROJECTION_MASTER_SID INT
          )

      SET @TEMP_PROJ_SID = Replace(@CFF_MASTER_SID + ',', ',,', ',')

      WHILE Patindex('%,%', @TEMP_PROJ_SID) <> 0
        BEGIN
            SELECT @SP = Patindex('%,%', @TEMP_PROJ_SID)

            SELECT @SP_PROJ_SID = LEFT(@TEMP_PROJ_SID, @SP - 1)

            SELECT @TEMP_PROJ_SID = Stuff(@TEMP_PROJ_SID, 1, @SP, '')

            INSERT INTO #CFF_PROJECTION_MASTER
                        (CFF_PROJECTION_MASTER_SID)
            SELECT @SP_PROJ_SID
        END

      DECLARE @CCP_TABLE VARCHAR(200) = Concat ('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
      -------------------------------GALUAT_29 CHANGES----------------------------
      ------PULLING ACTUAL START_DATE , ACTUAL_END_DATE,ROJ_START_PERIOD_DATE,PROJ_END_PERIOD_DATE BASED ON DESCRIPTION FROM HELPER_TABLE -----------
      DECLARE @ACTUAL_START_DATE      DATETIME = Cast(Year(Getdate()) - 3 AS VARCHAR(4))
                + '-01-01',
              @ACTUAL_END_DATE        DATETIME = Dateadd(MM, -3, Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0)),
              @PROJ_START_PERIOD_DATE DATETIME,
              @PROJ_END_PERIOD_DATE   DATETIME

      SELECT TOP 1 @PROJ_START_PERIOD_DATE = Dateadd(DD, 1, Eomonth(FROM_DATE, -1)),
                   @PROJ_END_PERIOD_DATE = Dateadd(DD, 1, Eomonth(TO_DATE, -1))
      FROM   FORECAST_CONFIG FA
             LEFT JOIN HELPER_TABLE HT
                    ON HT.HELPER_TABLE_SID = FA.BUSINESS_PROCESS_TYPE
      WHERE  [DESCRIPTION] = 'CONSOLIDATED FINANCIAL FORECAST'
      ORDER  BY VERSION_NO DESC

      DECLARE @ACT_START_PERIOD_SID INT = (SELECT PERIOD_SID
         FROM   PERIOD
         WHERE  PERIOD_DATE = @ACTUAL_START_DATE)
      DECLARE @ACT_END_PERIOD_SID INT = (SELECT PERIOD_SID
         FROM   PERIOD
         WHERE  PERIOD_DATE = @ACTUAL_END_DATE)
      DECLARE @PROJ_START_PERIOD_SID INT = (SELECT PERIOD_SID
         FROM   PERIOD
         WHERE  PERIOD_DATE = @PROJ_START_PERIOD_DATE)
      DECLARE @PROJ_END_PERIOD_SID INT = (SELECT PERIOD_SID
         FROM   PERIOD
         WHERE  PERIOD_DATE = @PROJ_END_PERIOD_DATE)

      --SELECT @ACT_START_PERIOD_SID = Max(IIF(F.ACTUAL_START_DATE = PERIOD_DATE, PERIOD_SID, NULL)),
      --       @ACT_END_PERIOD_SID = Max(IIF(F.ACTUAL_END_DATE = PERIOD_DATE, PERIOD_SID, NULL)),
      --       @PROJ_START_PERIOD_SID = Max(IIF(Cast(F.PROJECTION_START_DATE AS DATE) = PERIOD_DATE, PERIOD_SID, NULL)),
      --       @PROJ_END_PERIOD_SID = Max(IIF(Dateadd(DD, 1, EOMONTH(F.PROJECTION_END_DATE, -1)) = PERIOD_DATE, PERIOD_SID, NULL))
      --FROM   [DBO].[UDF_NA_PROJ_DATES]('Consolidated Financial Forecast') F
      --       INNER JOIN PERIOD P
      --               ON P.PERIOD_DATE IN ( F.ACTUAL_END_DATE, F.ACTUAL_START_DATE, Dateadd(DD, 1, EOMONTH(F.PROJECTION_END_DATE, -1)), Cast(F.PROJECTION_START_DATE AS DATE) );
      SELECT @FIRST_PROJ_SID = PM.CFF_PROJECTION_MASTER_SID
      FROM   #CFF_PROJECTION_MASTER PM
      WHERE  ID = 1

      SELECT @PIVOT_START_PERIOD_SID = PERIOD_SID
      FROM   PERIOD
      WHERE  PERIOD_DATE = @START_DATE

      ---------------------------BASED ON FREQUENCY CALUCLATIONS SHOLUD HAPPEN---------------------------
      IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
        DROP TABLE #PERIOD;

      SELECT PERIOD_SID,
             PERIOD_DATE,
             MONTH,
             QUARTER,
             SEMI_ANNUAL,
             YEAR,
             CASE
               WHEN LEFT(@PROJ_FREQUENCY, 1) = 'M' THEN MONTH
               WHEN LEFT(@PROJ_FREQUENCY, 1) = 'Q' THEN QUARTER
               WHEN LEFT(@PROJ_FREQUENCY, 1) = 'S' THEN SEMI_ANNUAL
               ELSE YEAR
             END AS PERIOD
      INTO   #PERIOD
      FROM   PERIOD

      ----------------------------------PULLING   CUSTOMER_HIERARCHY_LEVEL and   PRODUCT_HIERARCHY_LEVEL  FOR PARICULAR Cff-------------
      SELECT TOP 1 @CUST_RELATIONSHIP_BUILDER_SID = CUST_RELATIONSHIP_BUILDER_SID,
                   @PROD_RELATIONSHIP_BUILDER_SID = PROD_RELATIONSHIP_BUILDER_SID,
                   @MIN_LEVEL = CASE @CP_INDICATOR
                                  WHEN 'C' THEN CUSTOMER_HIERARCHY_LEVEL
                                  WHEN 'P' THEN PRODUCT_HIERARCHY_LEVEL
                                END
      FROM   CFF_MASTER
      WHERE  CFF_MASTER_SID = @FIRST_PROJ_SID
      ORDER  BY CFF_MASTER_SID

      ----------------------------------PULLING CCP and HIERARCHY_NO   FOR PARICULAR Cff-------------
      IF Object_id('TEMPDB..#CCP') IS NOT NULL
        DROP TABLE #CCP

      CREATE TABLE #CCP
        (
           CCP_DETAILS_SID     INT,
           HIERARCHY_NO        VARCHAR(50),
           LEVEL_NAME          VARCHAR(100),
           PARENT_HIERARCHY_NO VARCHAR(8000),
           LEVEL_NO            INT
        )

      IF @CP_INDICATOR IN ( 'C', 'P' )
        BEGIN
            DECLARE @SQL1 NVARCHAR(MAX)
            --                SET @SQL1 = Concat('SELECT DISTINCT LCCP.CCP_DETAILS_SID,
            --                LCCP.HIERARCHY_NO,
            --                LEVEL_NAME
            --FROM   (SELECT CCPMAP.CCP_DETAILS_SID,
            --               HLD.HIERARCHY_NO,
            --               HLD.RELATIONSHIP_LEVEL_SID,
            --               LEVEL_NAME
            --        FROM   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,
            --                       RLD.HIERARCHY_NO,
            --                       CCP.CCP_DETAILS_SID
            --                FROM   RELATIONSHIP_LEVEL_DEFINITION RLD
            --                       JOIN CCP_MAP CCP
            --                         ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID
            --                            AND RLD.RELATIONSHIP_BUILDER_SID = ', CASE @CP_INDICATOR
            --                                                                                                WHEN 'C' THEN @CUST_RELATIONSHIP_BUILDER_SID
            --                                                                                                WHEN 'P' THEN @PROD_RELATIONSHIP_BUILDER_SID
            --                                                                                              END, '
            --                      		 JOIN CFF_DETAILS CD ON CD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
            --                            AND CD.CFF_MASTER_SID = ', @FIRST_PROJ_SID, '
            --                       ) CCPMAP,
            --               (SELECT RLD1.HIERARCHY_NO,
            --                       RLD1.RELATIONSHIP_LEVEL_SID,
            --                       LEVEL_NAME
            --                FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1
            --                       JOIN ', CASE @CP_INDICATOR
            --                                                             WHEN 'C' THEN 'CFF_CUST_HIERARCHY'
            --                                                             WHEN 'P' THEN 'CFF_PROD_HIERARCHY'
            --                                                           END, ' PCH
            --                         ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID
            --                            AND PCH.CFF_MASTER_SID = ', @FIRST_PROJ_SID, '
            --							', CASE
            --                                        WHEN @LEVEL_NO <> 0 THEN 'WHERE  RLD1.HIERARCHY_NO LIKE '
            --                                                                 + @HIERARCHY_NO + '%'
            --                                        ELSE ''
            --                                      END, '
            --               ) HLD
            --        WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO + ''%'') LCCP
            --WHERE  LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO
            --                             FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2
            --                                    JOIN '
            --                                           + CASE @CP_INDICATOR WHEN 'C' THEN 'CFF_CUST_HIERARCHY' WHEN 'P' THEN 'CFF_PROD_HIERARCHY' END
            --                                           + ' PCH2
            --                                      ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID
            --                                         AND PCH2.CFF_MASTER_SID = '
            --                                           + Cast(@FIRST_PROJ_SID AS VARCHAR(20))
            --                                           + '
            --                             WHERE  RLD2.LEVEL_NO  '
            --                                           + CASE WHEN @LEVEL_NO = 0 THEN ' >= '+Cast(@MIN_LEVEL AS VARCHAR(50)) ELSE '= '+ Cast(@LEVEL_NO AS VARCHAR(50)) END + ')')
            --	print @sql1
            DECLARE @VAR1 VARCHAR(100) = ( CASE
                  WHEN @CP_INDICATOR = 'C' THEN 'CUST_HIERARCHY_NO'
                  WHEN @CP_INDICATOR = 'P' THEN 'PROD_HIERARCHY_NO'
                END )

            SET @SQL1 = Concat ('SELECT DISTINCT CCPMAP.CCP_DETAILS_SID,
               CCPMAP.HIERARCHY_NO,
              
               LEVEL_NAME
        FROM   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,
                       RLD.HIERARCHY_NO,
                       CCP.CCP_DETAILS_SID,
					   RLD.LEVEL_NAME
                FROM   RELATIONSHIP_LEVEL_DEFINITION RLD
                       JOIN ', @CCP_TABLE, ' CCP
                         ON  CCP.', @VAR1, ' LIKE RLD.HIERARCHY_NO + ''%''
                      		 JOIN CFF_DETAILS CD ON CD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                            AND CD.CFF_MASTER_SID = ', @FIRST_PROJ_SID, '  AND RLD.RELATIONSHIP_BUILDER_SID = ', CASE
                                                                                                                                           WHEN @CP_INDICATOR = 'C' THEN @CUST_RELATIONSHIP_BUILDER_SID
                                                                                                                                           WHEN @CP_INDICATOR = 'P' THEN @PROD_RELATIONSHIP_BUILDER_SID
                                                                                                                                         END, '
AND  RLD.LEVEL_NO >= ', @MIN_LEVEL, '
                       ) CCPMAP')

            INSERT INTO #CCP
                        (CCP_DETAILS_SID,
                         HIERARCHY_NO,
                         LEVEL_NAME)
            EXEC (@SQL1)
        END
      ELSE
        BEGIN
            ----------------------------------PULLING CCP S FOR PARICULAR Cff-------------
            IF Object_id('TEMPDB..#PARENT_HIERARCHY') IS NOT NULL
              DROP TABLE #PARENT_HIERARCHY

            CREATE TABLE #PARENT_HIERARCHY
              (
                 CCP_DETAILS_SID           INT,
                 HIERARCHY_NO              VARCHAR(50),
                 LEVEL_NAME                VARCHAR(75),
                 LEVEL_NO                  SMALLINT,
                 RELATIONSHIP_LEVEL_VALUES VARCHAR(50)
              )

            SET @SQL1 = Concat (' 
			INSERT INTO #PARENT_HIERARCHY
			(
			 CCP_DETAILS_SID,			
			 HIERARCHY_NO,				
			 LEVEL_NAME,				
			 LEVEL_NO	,				
			 RELATIONSHIP_LEVEL_VALUES
			)
			SELECT DISTINCT CH.CCP_DETAILS_SID,
       RLD.HIERARCHY_NO,
       RLD.LEVEL_NAME,
	   C.LEVEL_NO,
	   RLD.RELATIONSHIP_LEVEL_VALUES

     
FROM   CFF_CUSTOM_VIEW_DETAILS C
       JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
         ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
            AND RELATIONSHIP_BUILDER_SID IN ( @CUST_RELATIONSHIP_BUILDER_SID, @PROD_RELATIONSHIP_BUILDER_SID )
       JOIN ', @CCP_TABLE, ' CH
         ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
             OR CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
	   JOIN CFF_DETAILS CF ON CF.CCP_DETAILS_SID=CH.CCP_DETAILS_SID
                         
                WHERE   CFF_CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID')

            EXEC Sp_executesql
              @SQL1,
              N'@CUST_RELATIONSHIP_BUILDER_SID INT, @PROD_RELATIONSHIP_BUILDER_SID  INT,@CUSTOM_VIEW_MASTER_SID INT',
              @CUST_RELATIONSHIP_BUILDER_SID = @CUST_RELATIONSHIP_BUILDER_SID,
              @PROD_RELATIONSHIP_BUILDER_SID = @PROD_RELATIONSHIP_BUILDER_SID,
              @CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID

            INSERT INTO #CCP
                        (CCP_DETAILS_SID,
                         HIERARCHY_NO,
                         LEVEL_NAME,
                         PARENT_HIERARCHY_NO,
                         LEVEL_NO)
            SELECT ccp_details_sid,
                   HIERARCHY_NO,
                   level_name,
                   LEFT(parent_hierarchy_no, Len(parent_hierarchy_no) - 1),
                   LEVEL_NO
            FROM   #PARENT_HIERARCHY a
                   CROSS APPLY (SELECT HIERARCHY_NO + '~'
                                FROM   #PARENT_HIERARCHY b
                                WHERE  a.ccp_details_sid = b.ccp_details_sid
                                       AND a.level_no > b.level_no
                                ORDER  BY level_no
                                FOR XML path('')) cs(parent_hierarchy_no)
            ORDER  BY level_no
        END

      ------------------for HIERARCHY_NO,LEVEL_NAME finding start to end dates-------------------------------------
      IF Object_id('TEMPDB..#DATA_TABLE') IS NOT NULL
        DROP TABLE #DATA_TABLE

      SELECT HIERARCHY_NO,
             LEVEL_NAME,
             PERIOD,
             YEAR
      INTO   #DATA_TABLE
      FROM   (SELECT DISTINCT HIERARCHY_NO,
                              LEVEL_NAME
              FROM   #CCP) C
             CROSS JOIN (SELECT DISTINCT PERIOD,
                                         YEAR
                         FROM   #PERIOD
                         WHERE  PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @PROJ_END_PERIOD_SID) P

      ----------------------------------PULLING CCP S FOR PARICULAR Cff-------------
      IF Object_id('TEMPDB..#TEMP_CCP') IS NOT NULL
        DROP TABLE #TEMP_CCP

      CREATE TABLE #TEMP_CCP
        (
           CCP_DETAILS_SID        INT,
           COMPANY_MASTER_SID     INT,
           CONTRACT_MASTER_SID    INT,
           ITEM_MASTER_SID        INT,
           PROJECTION_DETAILS_SID INT,
           CFF_MASTER_SID         INT,
           HIERARCHY_NO           VARCHAR(100),
           LEVEL_NAME             VARCHAR(100),
           PROJECTION_MASTER_SID  INT,
           PARENT_HIERARCHY_NO    VARCHAR(100)
        )

      INSERT INTO #TEMP_CCP
                  (CCP_DETAILS_SID,
                   COMPANY_MASTER_SID,
                   CONTRACT_MASTER_SID,
                   ITEM_MASTER_SID,
                   PROJECTION_DETAILS_SID,
                   CFF_MASTER_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PROJECTION_MASTER_SID,
                   PARENT_HIERARCHY_NO)
      SELECT CCP.CCP_DETAILS_SID,
             CCP.COMPANY_MASTER_SID,
             CCP.CONTRACT_MASTER_SID,
             CCP.ITEM_MASTER_SID,
             PD.PROJECTION_DETAILS_SID,
             CD.CFF_MASTER_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             CD.PROJECTION_MASTER_SID,
             PARENT_HIERARCHY_NO
      FROM   CCP_DETAILS CCP
             INNER JOIN PROJECTION_DETAILS PD
                     ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
             JOIN #CCP C
               ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
             JOIN CFF_DETAILS CD
               ON C.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                  AND CD.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
      WHERE  CD.CFF_MASTER_SID = @FIRST_PROJ_SID

      --DECLARE @COUNT_1 INT,
      --        @COUNT_2 INT,
      --        @COUNT_3 INT
      --SELECT @COUNT_1 = Count(DISTINCT PROJECTION_MASTER_SID)
      --FROM   #TEMP_CCP
      --SELECT @COUNT_2 = Count(DISTINCT PM.PROJECTION_MASTER_SID)
      --FROM   #TEMP_CCP TC
      --       INNER JOIN PROJECTION_MASTER PM
      --               ON TC.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
      --WHERE  FORECASTING_TYPE = 'Non Mandated'
      --SELECT @COUNT_3 = Count(DISTINCT PM.PROJECTION_MASTER_SID)
      --FROM   #TEMP_CCP TC
      --       INNER JOIN PROJECTION_MASTER PM
      --               ON TC.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
      --WHERE  FORECASTING_TYPE = 'Mandated'
      --IF Object_id('TEMPDB..#DISCOUNT_INFO') IS NOT NULL
      --  DROP TABLE #DISCOUNT_INFO
      --CREATE TABLE #DISCOUNT_INFO
      --  (
      --     TOKEN    VARCHAR(100),
      --     DISCOUNT VARCHAR(100)
      --  )
      --INSERT INTO #DISCOUNT_INFO
      --            (TOKEN,
      --             DISCOUNT)
      --SELECT DISTINCT CASE
      --                  WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN Cast(R.RS_MODEL_SID AS VARCHAR(50))
      --                  ELSE PRICE_GROUP_TYPE
      --                END AS TOKEN,
      --                CASE
      --                  WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN RS_NAME
      --                  ELSE PRICE_GROUP_TYPE
      --                END AS DISCOUNT
      --FROM   NM_DISCOUNT_PROJ_MASTER S
      --       JOIN RS_MODEL R
      --         ON R.RS_MODEL_SID = S.RS_MODEL_SID
      --       JOIN #TEMP_CCP TC
      --         ON TC.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
      --    union all               
      --                    SELECT DISTINCT CASE 
      --                     WHEN @DISCOUNT_LEVEL = 'PROGRAM'
      --                           THEN CAST(RM.RS_MODEL_SID AS VARCHAR(50))
      --                     ELSE 'PRICE PROTECTION'
      --                     END AS TOKEN
      --              ,CASE 
      --                     WHEN @DISCOUNT_LEVEL = 'PROGRAM'
      --                           THEN RS_NAME
      --                     ELSE 'PRICE PROTECTION'
      --                     END AS DISCOUNT
      --   from 
      --   NM_PPA_PROJECTION_MASTER NMDP 
      --       INNER JOIN RS_MODEL RM ON RM.RS_MODEL_SID = NMDP.RS_MODEL_SID
      --       JOIN #TEMP_CCP TC
      --       ON TC.PROJECTION_DETAILS_SID = NMDP.PROJECTION_DETAILS_SID
      -----finding items attached to particular cff---------------------------        
      INSERT INTO @ITEMID
      SELECT IM.ITEM_MASTER_SID
      FROM   ITEM_MASTER IM
      WHERE  EXISTS (SELECT 1
                     FROM   #TEMP_CCP A
                     WHERE  CFF_MASTER_SID = @FIRST_PROJ_SID
                            AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID)

      -------------------------------------------------------------RETURNS-------------------------------------------------------------------------------
      --IF Object_id('TEMPDB..#TEMP_CCPD') IS NOT NULL
      --   DROP TABLE #TEMP_CCPD
      -- CREATE TABLE #TEMP_CCPD
      --   (
      --      COMPANY_MASTER_SID     INT,
      --      CONTRACT_MASTER_SID    INT,
      --      ITEM_MASTER_SID        INT,
      --      PROJECTION_DETAILS_SID INT,
      --      CFF_MASTER_SID         INT,
      --RS_NAME VARCHAR(100)
      --   )
      -- INSERT INTO #TEMP_CCPD
      --             (COMPANY_MASTER_SID,
      --              CONTRACT_MASTER_SID,
      --              ITEM_MASTER_SID,
      --              PROJECTION_DETAILS_SID,
      --              CFF_MASTER_SID,
      --  RS_NAME)
      -- SELECT DISTINCT COMPANY_MASTER_SID,
      --                 CONTRACT_MASTER_SID,
      --                 ITEM_MASTER_SID,
      --                 PROJECTION_DETAILS_SID,
      --                 CFF_MASTER_SID,
      --  RS_NAME
      -- FROM   (SELECT t_ccp.COMPANY_MASTER_SID,
      --                T_CCP.CONTRACT_MASTER_SID,
      --                T_CCP.ITEM_MASTER_SID,
      --                PROJECTION_DETAILS_SID,
      --                CFF_MASTER_SID,
      --                RS_ID,
      --                RS_TYPE,
      -- RS_NAME=	   CASE
      --				WHEN @DISCOUNT_VIEW = 'PROGRAM'
      --				 OR @DISCOUNT_VIEW IS NULL THEN RS_NAME
      --				ELSE HR.DESCRIPTION 
      --				END
      --         FROM   #TEMP_CCP T_CCP
      --                INNER JOIN RS_CONTRACT RS
      --                        ON T_CCP.CONTRACT_MASTER_SID = RS.CONTRACT_MASTER_SID
      --                INNER JOIN RS_CONTRACT_DETAILS RSC
      --                        ON RS.RS_CONTRACT_SID = RSC.RS_CONTRACT_SID
      --                           AND T_CCP.ITEM_MASTER_SID = RSC.ITEM_MASTER_SID
      --                JOIN projection_master pm
      --                  ON pm.PROJECTION_MASTER_SID = T_CCP.PROJECTION_master_SID
      --                     AND PM.FORECASTING_TYPE = 'NON MANDATED'
      --            INNER JOIN HELPER_TABLE HR
      --                ON RS.REBATE_PROGRAM_TYPE = HR.HELPER_TABLE_SID
      -- AND HR.LIST_NAME='REBATE_PROGRAM_TYPE'
      --   WHERE  EXISTS (SELECT 1
      --                        FROM   CFP_CONTRACT CF
      --                               INNER JOIN CFP_CONTRACT_DETAILS CFD
      --                                       ON CF.CFP_CONTRACT_SID = CFD.CFP_CONTRACT_SID
      --                                          AND T_CCP.COMPANY_MASTER_SID = CFD.COMPANY_MASTER_SID
      --                                          AND RS.CFP_CONTRACT_SID = CF.CFP_CONTRACT_SID
      --                                          AND CF.CONTRACT_MASTER_SID = T_CCP.CONTRACT_MASTER_SID)
      -- ) R
      --        INNER JOIN HELPER_TABLE HT
      --                ON R.RS_TYPE = HT.HELPER_TABLE_SID
      -- WHERE  HT.DESCRIPTION = 'RETURNS'
      --IF Object_id('TEMPDB..#TEMP_RETURNS') IS NOT NULL
      --  DROP TABLE #TEMP_RETURNS
      --CREATE TABLE #TEMP_RETURNS
      --  (
      --     ITEM_MASTER_SID     INT,
      --     RS_NAME             VARCHAR(100),
      --     RETURNS_DETAILS_SID INT,
      --     PERIOD_SID          INT,
      --     ACTUAL_RATE         NUMERIC(22, 6),
      --     PROJECTED_RATE      NUMERIC(22, 6),
      --     PRIMARY KEY(ITEM_MASTER_SID, PERIOD_SID)
      --  );
      --WITH ITEM_PROJ_DETAILS
      --     AS (SELECT Row_number()
      --                  OVER (
      --                    PARTITION BY RD.ITEM_MASTER_SID, TD.RS_NAME
      --                    ORDER BY LASTEST_DATE DESC ) RN,
      --                RD.ITEM_MASTER_SID,
      --                PM.PROJECTION_MASTER_SID,
      --                RETURNS_DETAILS_SID,
      --                TD.RS_NAME
      --         FROM   RETURNS_DETAILS RD
      --                INNER JOIN PROJECTION_MASTER PM
      --                        ON RD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
      --                CROSS APPLY ( VALUES (MODIFIED_DATE),
      --                                     (CREATED_DATE) ) CS(LASTEST_DATE)
      --                INNER JOIN PROJECTION_DETAILS PD
      --                        ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
      --                INNER JOIN #TEMP_CCPD TD
      --                        ON TD.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
      --                           AND RD.ITEM_MASTER_SID = TD.ITEM_MASTER_SID
      --         WHERE  SAVE_FLAG = 1
      --                AND RD.ITEM_MASTER_SID IN (SELECT ITEM_MASTER_SID
      --                                           FROM   @ITEMID))
      --INSERT INTO #TEMP_RETURNS
      --            (ITEM_MASTER_SID,
      --             RS_NAME,
      --             RETURNS_DETAILS_SID,
      --             PERIOD_SID,
      --             ACTUAL_RATE,
      --             PROJECTED_RATE)
      --SELECT COALESCE(R_ACTUALS.ITEM_MASTER_SID, R_PROJ.ITEM_MASTER_SID),
      --       COALESCE(R_ACTUALS.RS_NAME, R_PROJ.RS_NAME),
      --       COALESCE(R_ACTUALS.RETURNS_DETAILS_SID, R_PROJ.RETURNS_DETAILS_SID) AS RETURNS_DETAILS_SID,
      --       COALESCE(R_ACTUALS.PERIOD_SID, R_PROJ.PERIOD_SID)                   AS PERIOD_SID,
      --       R_ACTUALS.ACTUAL_RETURN_PERCENT,
      --       R_PROJ.PROJECTED_RETURN_PERCENT
      --FROM   (SELECT IMPD.RETURNS_DETAILS_SID,
      --               ITEM_MASTER_SID,
      --               RS_NAME,
      --               PERIOD_SID,
      --               ACTUAL_RETURN_PERCENT
      --        FROM   RETURNS_ACTUALS NAP
      --               JOIN ITEM_PROJ_DETAILS IMPD
      --                 ON IMPD.RETURNS_DETAILS_SID = NAP.RETURNS_DETAILS_SID
      --        WHERE  IMPD.RN = 1) R_ACTUALS
      --       FULL JOIN (SELECT NPP.RETURNS_DETAILS_SID,
      --                         ITEM_MASTER_SID,
      --                         RS_NAME,
      --                         PERIOD_SID,
      --                         PROJECTED_RETURN_PERCENT
      --                  FROM   RETURNS_PROJ_DETAILS NPP
      --                         JOIN ITEM_PROJ_DETAILS IMPD
      --                           ON IMPD.RETURNS_DETAILS_SID = NPP.RETURNS_DETAILS_SID
      --                  WHERE  IMPD.RN = 1) R_PROJ
      --              ON R_ACTUALS.RETURNS_DETAILS_SID = R_PROJ.RETURNS_DETAILS_SID
      --                 AND R_ACTUALS.PERIOD_SID = R_PROJ.PERIOD_SID
      --IF NOT EXISTS (SELECT 1
      --               FROM   #TEMP_RETURNS)
      --  BEGIN
      --      INSERT INTO #TEMP_RETURNS
      --                  (ITEM_MASTER_SID,
      --                   RS_NAME,
      --                   RETURNS_DETAILS_SID,
      --                   PERIOD_SID,
      --                   ACTUAL_RATE,
      --                   PROJECTED_RATE)
      --      SELECT ITEM_MASTER_SID,
      --             RS_NAME,
      --             RETURNS_DETAILS_SID=NULL,
      --             PERIOD_SID,
      --             ACTUAL_RATE=0,
      --             PROJECTED_RATE=0
      --      FROM   #TEMP_CCPD
      --             CROSS JOIN #PERIOD P
      --      WHERE  P.PERIOD_SID BETWEEN @ACT_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
      --  END
      --------------------------------------------------PULLING PRICE FOR CFF ITEMS------------
      IF Object_id('TEMPDB..#ITEM_PRICING') IS NOT NULL
        DROP TABLE #ITEM_PRICING

      CREATE TABLE #ITEM_PRICING
        (
           ITEM_MASTER_SID INT,
           PERIOD_SID      INT,
           ITEM_PRICE      NUMERIC(22, 6),
           PRIMARY KEY ( ITEM_MASTER_SID, PERIOD_SID )
        )

      INSERT INTO #ITEM_PRICING
                  (ITEM_MASTER_SID,
                   PERIOD_SID,
                   ITEM_PRICE)
      SELECT ITEM_MASTER_SID,
             PERIOD_SID,
             ITEM_PRICE
      FROM   [DBO].[Udf_item_pricing](@ITEMID, 'COGS', COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID), @PROJ_END_PERIOD_SID, 'EACH')

      --------------------------------------------------STROING OUTPUT IN RESULT TABLE ------------
      IF Object_id('TEMPDB.DBO.#RESULT', 'U') IS NOT NULL
        DROP TABLE #RESULT;

      CREATE TABLE #RESULT
        (
           CFF_PROJECTION_MASTER_SID                INT,
           HIERARCHY_NO                             VARCHAR(100),
           LEVEL_NAME                               VARCHAR(100),
           YEAR                                     INT,
           PERIOD                                   INT,
           RS_NAME                                  VARCHAR(100),
           PARENT_HIERARCHY_NO                      VARCHAR(100),
           TOTAL_DISCOUNT                           NUMERIC(22, 6),
           TOTAL_DISCOUNT_PROJECTED                 NUMERIC(22, 6),
           TOTAL_DISCOUNT_PERCENTAGE                NUMERIC(22, 6),
           TOTAL_DISCOUNT_PROJECTED_PERCENTAGE      NUMERIC(22, 6),
           TOTAL_RPU                                NUMERIC(22, 6),
           TOTAL_PROJECTED_RPU                      NUMERIC(22, 6),
           DISCOUNT_OF_EX_FACTORY_ACTUALS_PERCENT   NUMERIC(22, 6),
           DISCOUNT_OF_EX_FACTORY_PROJECTED_PERCENT NUMERIC(22, 6),
           TOTAL_DISCOUNT_ACCRUAL                   NUMERIC(22, 6),
           TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE        NUMERIC(22, 6),
           TOTAL_RPU_ACCRUAL                        NUMERIC(22, 6),
           DISCOUNT_OF_EX_FACTORY_ACCRUAL           NUMERIC(22, 6)
        )

      ---------------------pulling rs_name information for first cff----------------------
      DECLARE @VAR INT

      SET @VAR = CASE
                   WHEN @DISCOUNT_VIEW = 'PROGRAM'
                         OR @DISCOUNT_VIEW IS NULL THEN 1
                   ELSE 0
                 END

      IF Object_id('TEMPDB..#PRIOR_RS_DATA') IS NOT NULL
        DROP TABLE #PRIOR_RS_DATA

      SELECT DISTINCT DISCOUNT----cel-462
                      ,
                      NM_PPA_IND,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO,
                      CCP_DETAILS_SID
      INTO   #PRIOR_RS_DATA
      FROM   (SELECT CASE
                       WHEN @DISCOUNT_VIEW = 'program' THEN CONVERT(VARCHAR(100), NMDP.RS_CONTRACT_SID)
                       ELSE PRICE_GROUP_TYPE
                     END  AS discount,
                     'NM' AS NM_PPA_IND,
                     HIERARCHY_NO,
                     LEVEL_NAME,
                     PARENT_HIERARCHY_NO,
                     PD.CCP_DETAILS_SID
              FROM   CFF_DETAILS CFF
                     JOIN PROJECTION_DETAILS PD
                       ON CFF.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                          AND CFF.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                     JOIN NM_DISCOUNT_PROJ_MASTER NMDP
                       ON NMDP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                     JOIN RS_CONTRACT RM
                       ON RM.RS_MODEL_SID = NMDP.RS_MODEL_SID
                          AND RM.RS_CONTRACT_SID = NMDP.RS_CONTRACT_SID
                     INNER JOIN #CCP C
                             ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
              WHERE  CFF.CFF_MASTER_SID = @FIRST_PROJ_SID
              UNION ALL
              SELECT CASE
                       WHEN @DISCOUNT_VIEW = 'PROGRAM'
                             OR @DISCOUNT_VIEW IS NULL THEN CONVERT(VARCHAR(100), RM.RS_CONTRACT_SID)
                       ELSE 'Price Protection'
                     END   AS PPA_DISCOUNT,
                     'PPA' AS NM_PPA_IND,
                     HIERARCHY_NO,
                     LEVEL_NAME,
                     PARENT_HIERARCHY_NO,
                     PD.CCP_DETAILS_SID
              FROM   CFF_DETAILS CFF
                     JOIN PROJECTION_DETAILS PD
                       ON CFF.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                          AND CFF.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                     JOIN NM_PPA_PROJECTION_MASTER PPA
                       ON PPA.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                     JOIN RS_CONTRACT RM
                       ON RM.RS_MODEL_SID = PPA.RS_MODEL_SID
                          AND RM.RS_CONTRACT_SID = PPA.RS_CONTRACT_SID
                     INNER JOIN #CCP C
                             ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
              WHERE  CFF.CFF_MASTER_SID = @FIRST_PROJ_SID) A

      IF Object_id('TEMPDB.DBO.#CURRENT_RS_DATA_PERIOD', 'U') IS NOT NULL
        DROP TABLE #CURRENT_RS_DATA_PERIOD;

      SELECT DISTINCT DISCOUNT----cel-462
                      ,
                      YEAR,
                      PERIOD,
                      F.CFF_MASTER_SID,
                      pd.PROJECTION_DETAILS_SID,
                      NM_PPA_IND,
                      PERIOD_SID,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO
      INTO   #CURRENT_RS_DATA_PERIOD
      FROM   #PRIOR_RS_DATA PR
             CROSS JOIN (SELECT CFF_PROJECTION_MASTER_SID AS CFF_MASTER_SID
                         FROM   #CFF_PROJECTION_MASTER
                         WHERE  ID = 1) F
             JOIN CFF_DETAILS CD
               ON PR.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                  AND F.CFF_MASTER_SID = CD.CFF_MASTER_SID
             JOIN PROJECTION_DETAILS PD
               ON PR.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                  AND CD.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
             CROSS JOIN (SELECT DISTINCT YEAR,
                                         PERIOD,
                                         PERIOD_SID
                         FROM   #PERIOD
                         WHERE  PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @PROJ_END_PERIOD_SID) P

      ------------------finding actual start period and end period for each RS_NAME,HIERARCHY_NO------------------------------
      --  IF Object_id('TEMPDB.DBO.#RS_DATA_PERIOD', 'U') IS NOT NULL
      --    DROP TABLE #RS_DATA_PERIOD;
      --  SELECT RS_CONTRACT_SID,
      --         RS_NAME,
      --         HIERARCHY_NO,
      --         LEVEL_NAME,
      --         YEAR,
      --         PERIOD,
      --NM_PPA_IND
      --  INTO   #RS_DATA_PERIOD
      --  FROM   #RS_DATA A
      --         CROSS JOIN (SELECT DISTINCT year,
      --                                     period
      --                     FROM   #PERIOD
      --                     WHERE  PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @PROJ_END_PERIOD_SID) P
      --SELECT DISTINCT NMDP.RS_MODEL_SID,
      --                               case when @DISCOUNT_VIEW = 'program' or @DISCOUNT_VIEW is null then 
      --                            RS_NAME    else PRICE_GROUP_TYPE end as discount
      --               FROM   #CFF_DETAILS CFF
      -------------PULLING CCP LEVEL + HIERARCHY_NO,LEVEL_NAME INFORMATION FOR ONE PROJECTION---------------------
      IF Object_id('TEMPDB.DBO.#CCP_INFO', 'U') IS NOT NULL
        DROP TABLE #CCP_INFO;

      SELECT CD.CFF_MASTER_SID,
             PD.PROJECTION_MASTER_SID,
             PD.PROJECTION_DETAILS_SID,
             PD.CCP_DETAILS_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             FORECASTING_TYPE,
             PARENT_HIERARCHY_NO
      INTO   #CCP_INFO
      FROM   PROJECTION_DETAILS PD
             JOIN CFF_DETAILS CD
               ON CD.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                  AND CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
             JOIN PROJECTION_MASTER PM
               ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
             JOIN #CCP C
               ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
      WHERE  CFF_MASTER_SID = @FIRST_PROJ_SID

      ------------------------------------------------------------------- finding sales inclusion --------------------------------------
      IF Object_id('TEMPDB.DBO.#SALES_INFORMATION', 'U') IS NOT NULL
        DROP TABLE #SALES_INFORMATION;

      SELECT DISTINCT CF.CFF_MASTER_SID,
                      CC.CCP_DETAILS_SID,
                      PD.PROJECTION_MASTER_SID,
                      PD.PROJECTION_DETAILS_SID,
                      cc.item_master_sid
      INTO   #SALES_INFORMATION
      FROM   CCP_DETAILS cc
             JOIN CFF_DETAILS CF
               ON CF.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
             JOIN PROJECTION_DETAILS PD
               ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
                  AND CF.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
      WHERE  CF.CFF_MASTER_SID = @FIRST_PROJ_SID
             AND SALES_INCLUSION = 'Y'

      ----------------------------------finding dicount inclusion ----------------------------------------
      IF Object_id('TEMPDB.DBO.#DISCOUNT_INFORMATION', 'U') IS NOT NULL
        DROP TABLE #DISCOUNT_INFORMATION;

      SELECT DISTINCT CF.CFF_MASTER_SID,
                      CC.CCP_DETAILS_SID,
                      PD.PROJECTION_MASTER_SID,
                      PD.PROJECTION_DETAILS_SID,
                      CDI.RS_MODEL_SID,
                      prd.DISCOUNT
      INTO   #DISCOUNT_INFORMATION
      FROM   CFF_DEDUCTION_INCLUSION CDI
             JOIN CFF_DETAILS CF
               ON CF.CFF_DETAILS_SID = CDI.CFF_DETAILS_SID
             JOIN CCP_DETAILS CC
               ON CF.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
             JOIN PROJECTION_DETAILS PD
               ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
                  AND CF.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
             JOIN (SELECT DISTINCT CFF_MASTER_SID,
                                   PROJECTION_DETAILS_SID,
                                   DISCOUNT
                   FROM   #CURRENT_RS_DATA_PERIOD) PRD
               ON PRD.CFF_MASTER_SID = CF.CFF_MASTER_SID
                  AND PRD.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
      WHERE  CF.CFF_MASTER_SID = @FIRST_PROJ_SID
             AND CDI.DEDUCTION_INCLUSION = 'Y'

      ---------------------------------------------------------------------------------
      IF Object_id('TEMPDB..#ACCRUAL_DISCOUNT') IS NOT NULL
        DROP TABLE #ACCRUAL_DISCOUNT;

      WITH CTE
           AS (SELECT A.CCP_DETAILS_SID,
                      A.COMPANY_MASTER_SID,
                      A.CONTRACT_MASTER_SID,
                      A.ITEM_MASTER_SID,
                      A.PROJECTION_DETAILS_SID,
                      A.CFF_MASTER_SID,
                      A.HIERARCHY_NO,
                      A.LEVEL_NAME,
                      A.PROJECTION_MASTER_SID,
                      A.PARENT_HIERARCHY_NO,
                      p.PERIOD_SID,
                      PERIOD_DATE,
                      NMDP.RS_CONTRACT_SID,
                      RS.DISCOUNT
               FROM   #TEMP_CCP A
                      JOIN #PRIOR_RS_DATA RS
                        ON RS.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                      JOIN #PERIOD P
                        ON P.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @PROJ_END_PERIOD_SID
                      JOIN NM_DISCOUNT_PROJ_MASTER NMDP
                        ON NMDP.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                           AND CASE
                                 WHEN @DISCOUNT_VIEW = 'PROGRAM'
                                       OR @DISCOUNT_VIEW IS NULL THEN CONVERT(VARCHAR(100), NMDP.RS_CONTRACT_SID)
                                 ELSE PRICE_GROUP_TYPE
                               END = RS.DISCOUNT)
      SELECT CFF_MASTER_SID,
             PROJECTION_DETAILS_SID,
             PERIOD_SID,
             RC.RS_CONTRACT_SID,
             DISCOUNT,
             Sum(DEDUCTION_AMOUNT) / ( Datediff(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE)
                                       + 1 ) DISCOUNT_AMOUNT,
             HIERARCHY_NO,
             LEVEL_NAME,
             PARENT_HIERARCHY_NO
      INTO   #ACCRUAL_DISCOUNT
      FROM   ACCRUAL_MASTER A1
             JOIN RS_CONTRACT RC
               ON RC.RS_MODEL_SID = A1.RS_MODEL_SID
                  AND RC.CONTRACT_MASTER_SID = A1.CONTRACT_MASTER_SID
             JOIN CTE A2
               ON A2.PERIOD_DATE BETWEEN CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_START_DATE, 0)))) AND CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_END_DATE, 0))))
                  AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID
                  AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID
                  AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID
                  AND A2.RS_CONTRACT_SID = RC.RS_CONTRACT_SID
                  AND Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_START_DATE, -1)) >= @ACTUAL_START_DATE
                  AND A1.ACCRUAL_PERIOD_END_DATE <= @PROJ_END_PERIOD_DATE
      GROUP  BY CFF_MASTER_SID,
                PROJECTION_DETAILS_SID,
                PERIOD_SID,
                RC.RS_CONTRACT_SID,
                DISCOUNT,
                ACCRUAL_PERIOD_START_DATE,
                ACCRUAL_PERIOD_END_DATE,
                HIERARCHY_NO,
                LEVEL_NAME,
                PARENT_HIERARCHY_NO

      ---------------------------------------finding indicator foreach hierarchy(for salesinformation)---------------------------		
      IF Object_id('TEMPDB..#TEMP_HIER_SALES') IS NOT NULL
        DROP TABLE #TEMP_HIER_SALES

      CREATE TABLE #TEMP_HIER_SALES
        (
           HIERARCHY_NO VARCHAR(50),
           INDICATOR    BIT,
           PRIMARY KEY (HIERARCHY_NO)
        )

      INSERT INTO #TEMP_HIER_SALES
                  (HIERARCHY_NO,
                   INDICATOR)
      SELECT DISTINCT HIERARCHY_NO,
                      1
      FROM   #CCP A
             JOIN #SALES_INFORMATION B
               ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID

      ---------------------------------------finding indicator foreach hierarchy(for discount information)---------------------------
      IF Object_id('TEMPDB..#TEMP_HIER_DISC') IS NOT NULL
        DROP TABLE #TEMP_HIER_DISC

      CREATE TABLE #TEMP_HIER_DISC
        (
           HIERARCHY_NO VARCHAR(50),
           INDICATOR    BIT,
           PRIMARY KEY (HIERARCHY_NO)
        )

      INSERT INTO #TEMP_HIER_DISC
                  (HIERARCHY_NO,
                   INDICATOR)
      SELECT DISTINCT HIERARCHY_NO,
                      1
      FROM   #CCP A
             JOIN #DISCOUNT_INFORMATION B
               ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID

      ------------------------------------------------------------------- Newly added ends here
      --------------------------------------------------------- pulling Discount projected information----------------------
      IF Object_id('TEMPDB..#NM_DISCOUNT_PROJECTION') IS NOT NULL
        DROP TABLE #NM_DISCOUNT_PROJECTION --NM

      SELECT Isnull(Sum(NSP.PROJECTION_SALES), 0) AS PROJECTION_SALES,
             Isnull(Sum(AD.DISCOUNT_AMOUNT), 0)   AS ACCRUAL_AMOUNT_PROJECTION,
             PERIOD,
             YEAR,
             PRD.DISCOUNT,
             PRD.CFF_MASTER_SID,
             PRD.HIERARCHY_NO,
             PRD.LEVEL_NAME,
             PRD.PARENT_HIERARCHY_NO
      INTO   #NM_DISCOUNT_PROJECTION
      FROM   #CURRENT_RS_DATA_PERIOD PRD
             JOIN NM_DISCOUNT_PROJ_MASTER NMDP
               ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                  AND CASE
                        WHEN @VAR = 1 THEN CONVERT(VARCHAR(100), NMDP.RS_CONTRACT_SID)
                        ELSE PRICE_GROUP_TYPE
                      END = PRD.DISCOUNT
             JOIN NM_DISCOUNT_PROJECTION NSP
               ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                  AND NMDP.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                  AND PRD.PERIOD_SID = NSP.PERIOD_SID
                  AND prd.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
             LEFT JOIN #ACCRUAL_DISCOUNT AD
                    ON PRD.CFF_MASTER_SID = AD.CFF_MASTER_SID
                       AND NSP.PROJECTION_DETAILS_SID = AD.PROJECTION_DETAILS_SID
                       AND NSP.RS_CONTRACT_SID = AD.RS_CONTRACT_SID
                       AND PRD.DISCOUNT = AD.DISCOUNT
                       AND NSP.PERIOD_SID = AD.PERIOD_SID
                       AND PRD.HIERARCHY_NO = AD.HIERARCHY_NO
                       AND PRD.LEVEL_NAME = AD.LEVEL_NAME
      WHERE  EXISTS (SELECT 1
                     FROM   #DISCOUNT_INFORMATION A
                     WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                            AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                            AND PRD.DISCOUNT = A.DISCOUNT)
      GROUP  BY PERIOD,
                YEAR,
                PRD.DISCOUNT,
                PRD.CFF_MASTER_SID,
                PRD.HIERARCHY_NO,
                PRD.LEVEL_NAME,
                PRD.PARENT_HIERARCHY_NO

      --------------------------------------------------------- pulling Discount actual information----------------------
      IF Object_id('TEMPDB..#NM_ACTUAL_DISCOUNT') IS NOT NULL
        DROP TABLE #NM_ACTUAL_DISCOUNT

      SELECT Isnull(Sum(NSP.ACTUAL_SALES), 0)   AS ACTUAL_SALES,
             Isnull(Sum(AD.DISCOUNT_AMOUNT), 0) AS ACCRUAL_AMOUNT_ACTUAL,
             PERIOD,
             YEAR,
             PRD.DISCOUNT,
             PRD.CFF_MASTER_SID,
             PRD.HIERARCHY_NO,
             PRD.LEVEL_NAME,
             PRD.PARENT_HIERARCHY_NO
      INTO   #NM_ACTUAL_DISCOUNT
      FROM   #CURRENT_RS_DATA_PERIOD PRD
             JOIN NM_DISCOUNT_PROJ_MASTER NMDP
               ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                  AND CASE
                        WHEN @VAR = 1 THEN CONVERT(VARCHAR(100), NMDP.RS_CONTRACT_SID)
                        ELSE PRICE_GROUP_TYPE
                      END = PRD.DISCOUNT
             JOIN NM_ACTUAL_DISCOUNT NSP
               ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                  AND NMDP.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                  AND PRD.PERIOD_SID = NSP.PERIOD_SID
                  AND PRD.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @ACT_END_PERIOD_SID
             LEFT JOIN #ACCRUAL_DISCOUNT AD
                    ON PRD.CFF_MASTER_SID = AD.CFF_MASTER_SID
                       AND NSP.PROJECTION_DETAILS_SID = AD.PROJECTION_DETAILS_SID
                       AND NSP.RS_CONTRACT_SID = AD.RS_CONTRACT_SID
                       AND PRD.DISCOUNT = AD.DISCOUNT
                       AND NSP.PERIOD_SID = AD.PERIOD_SID
                       AND prd.HIERARCHY_NO = AD.HIERARCHY_NO
                       AND PRD.LEVEL_NAME = AD.LEVEL_NAME
      WHERE  EXISTS (SELECT 1
                     FROM   #DISCOUNT_INFORMATION A
                     WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                            AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                            AND PRD.DISCOUNT = A.DISCOUNT)
      GROUP  BY PERIOD,
                YEAR,
                PRD.DISCOUNT,
                PRD.CFF_MASTER_SID,
                PRD.HIERARCHY_NO,
                PRD.LEVEL_NAME,
                PRD.PARENT_HIERARCHY_NO

      --------------------------------------------------------- pulling PPA projeted information---------------------- 
      IF Object_id('TEMPDB..#NM_PPA_PROJECTION') IS NOT NULL
        DROP TABLE #NM_PPA_PROJECTION

      SELECT Isnull(Sum(NSP.PROJECTION_DISCOUNT_DOLLAR), 0) PROJECTION_DISCOUNT_DOLLAR,
             PERIOD,
             YEAR,
             PRD.DISCOUNT,
             PRD.CFF_MASTER_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             PARENT_HIERARCHY_NO
      INTO   #NM_PPA_PROJECTION
      FROM   #CURRENT_RS_DATA_PERIOD PRD
             JOIN NM_PPA_PROJECTION_MASTER NMDP
               ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                  AND CASE
                        WHEN @VAR = 1 THEN CONVERT(VARCHAR(100), NMDP.RS_CONTRACT_SID)
                        ELSE 'Price Protection'
                      END = PRD.DISCOUNT
             JOIN NM_PPA_PROJECTION NSP
               ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                  AND NMDP.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                  AND PRD.PERIOD_SID = NSP.PERIOD_SID
                  AND PRD.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
      WHERE  EXISTS (SELECT 1
                     FROM   #DISCOUNT_INFORMATION A
                     WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                            AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                            AND PRD.DISCOUNT = A.DISCOUNT)
      GROUP  BY PERIOD,
                YEAR,
                PRD.DISCOUNT,
                PRD.CFF_MASTER_SID,
                HIERARCHY_NO,
                LEVEL_NAME,
                PARENT_HIERARCHY_NO

      --------------------------------------------------------- pulling PPA actual information----------------------   		
      IF Object_id('TEMPDB..#NM_ACTUAL_PPA') IS NOT NULL
        DROP TABLE #NM_ACTUAL_PPA

      SELECT Isnull(Sum(NSP.ACTUAL_DISCOUNT_DOLLAR), 0) ACTUAL_DISCOUNT_DOLLAR,
             PERIOD,
             YEAR,
             PRD.DISCOUNT,
             PRD.CFF_MASTER_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             PARENT_HIERARCHY_NO
      INTO   #NM_ACTUAL_PPA
      FROM   #CURRENT_RS_DATA_PERIOD PRD
             JOIN NM_PPA_PROJECTION_MASTER NMDP
               ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                  AND CASE
                        WHEN @VAR = 1 THEN CONVERT(VARCHAR(100), NMDP.RS_CONTRACT_SID)
                        ELSE 'Price Protection'
                      END = PRD.DISCOUNT
             JOIN NM_ACTUAL_PPA NSP
               ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                  AND NMDP.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                  AND PRD.PERIOD_SID = NSP.PERIOD_SID
                  AND PRD.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @ACT_END_PERIOD_SID
      WHERE  EXISTS (SELECT 1
                     FROM   #DISCOUNT_INFORMATION A
                     WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                            AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                            AND PRD.DISCOUNT = A.DISCOUNT)
      GROUP  BY PERIOD,
                YEAR,
                PRD.DISCOUNT,
                PRD.CFF_MASTER_SID,
                HIERARCHY_NO,
                LEVEL_NAME,
                PARENT_HIERARCHY_NO

      --------------------------------------------------------- pulling Supplemental projection  information-------------------
      IF Object_id('TEMPDB..#M_SUPPLEMENTAL_DISC_PROJ') IS NOT NULL
        DROP TABLE #M_SUPPLEMENTAL_DISC_PROJ

      SELECT Isnull(Sum(NSP.PROJECTION_SALES), 0) PROJECTION_SALES,
             PERIOD,
             YEAR,
             PRD.DISCOUNT,
             PRD.CFF_MASTER_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             PARENT_HIERARCHY_NO
      INTO   #M_SUPPLEMENTAL_DISC_PROJ
      FROM   #CURRENT_RS_DATA_PERIOD PRD
             JOIN M_SUPPLEMENTAL_DISC_MASTER NMDP
               ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
             JOIN M_SUPPLEMENTAL_DISC_PROJ NSP
               ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                  AND PRD.PERIOD_SID = NSP.PERIOD_SID
                  AND PRD.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
      WHERE  EXISTS (SELECT 1
                     FROM   #DISCOUNT_INFORMATION A
                     WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                            AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                            AND PRD.DISCOUNT = A.DISCOUNT)
      GROUP  BY PERIOD,
                YEAR,
                PRD.DISCOUNT,
                PRD.CFF_MASTER_SID,
                HIERARCHY_NO,
                LEVEL_NAME,
                PARENT_HIERARCHY_NO

      --------------------------------------------------------- pulling Supplemental actual  information-------------------
      IF Object_id('TEMPDB..#M_SUPPLEMENTAL_DISC_ACTUALS') IS NOT NULL
        DROP TABLE #M_SUPPLEMENTAL_DISC_ACTUALS

      SELECT Sum(NSP.ACTUAL_SALES) ACTUAL_SALES,
             PERIOD,
             YEAR,
             PRD.DISCOUNT,
             PRD.CFF_MASTER_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             PARENT_HIERARCHY_NO
      INTO   #M_SUPPLEMENTAL_DISC_ACTUALS
      FROM   #CURRENT_RS_DATA_PERIOD PRD
             JOIN M_SUPPLEMENTAL_DISC_MASTER NMDP
               ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
             JOIN M_SUPPLEMENTAL_DISC_ACTUALS NSP
               ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                  AND PRD.PERIOD_SID = NSP.PERIOD_SID
                  AND PRD.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @ACT_END_PERIOD_SID
      WHERE  EXISTS (SELECT 1
                     FROM   #DISCOUNT_INFORMATION A
                     WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                            AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                            AND PRD.DISCOUNT = A.DISCOUNT)
      GROUP  BY PERIOD,
                YEAR,
                PRD.DISCOUNT,
                PRD.CFF_MASTER_SID,
                HIERARCHY_NO,
                LEVEL_NAME,
                PARENT_HIERARCHY_NO

      --------------------------------------------------------- pulling mandated discount projection  information-------------------
      IF Object_id('TEMPDB..#M_DISCOUNT_PROJECTION') IS NOT NULL
        DROP TABLE #M_DISCOUNT_PROJECTION

      SELECT Sum(NMDP.PROJECTION_SALES) PROJECTION_SALES,
             PERIOD,
             YEAR,
             PRD.DISCOUNT,
             PRD.CFF_MASTER_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             PARENT_HIERARCHY_NO
      INTO   #M_DISCOUNT_PROJECTION
      FROM   #CURRENT_RS_DATA_PERIOD PRD
             JOIN M_DISCOUNT_PROJECTION NMDP
               ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                  AND PRD.PERIOD_SID = NMDP.PERIOD_SID
                  AND SAVE_FLAG = 1
                  AND PRD.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
      WHERE  EXISTS (SELECT 1
                     FROM   #DISCOUNT_INFORMATION A
                     WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                            AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                            AND PRD.DISCOUNT = A.DISCOUNT)
      GROUP  BY PERIOD,
                YEAR,
                PRD.DISCOUNT,
                PRD.CFF_MASTER_SID,
                HIERARCHY_NO,
                LEVEL_NAME,
                PARENT_HIERARCHY_NO

      --------------------------------------------------------- pulling mandated discount actual  information-------------------   		
      IF Object_id('TEMPDB..#M_ACTUAL_DISCOUNT') IS NOT NULL
        DROP TABLE #M_ACTUAL_DISCOUNT

      SELECT Sum(NMDP.ACTUAL_SALES) ACTUAL_SALES,
             PERIOD,
             YEAR,
             PRD.DISCOUNT,
             PRD.CFF_MASTER_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             PARENT_HIERARCHY_NO
      INTO   #M_ACTUAL_DISCOUNT
      FROM   #CURRENT_RS_DATA_PERIOD PRD
             JOIN M_ACTUAL_DISCOUNT NMDP
               ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                  AND PRD.PERIOD_SID = NMDP.PERIOD_SID
                  AND SAVE_FLAG = 1
                  AND PRD.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @ACT_END_PERIOD_SID
      WHERE  EXISTS (SELECT 1
                     FROM   #DISCOUNT_INFORMATION A
                     WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                            AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                            AND PRD.DISCOUNT = A.DISCOUNT)
      GROUP  BY PERIOD,
                YEAR,
                PRD.DISCOUNT,
                PRD.CFF_MASTER_SID,
                HIERARCHY_NO,
                LEVEL_NAME,
                PARENT_HIERARCHY_NO

      --------------------------------------------------------- pulling non mandated sales projection  information-------------------  
      IF Object_id('TEMPDB..#NM_SALES_PROJECTION') IS NOT NULL
        DROP TABLE #NM_SALES_PROJECTION

      SELECT Sum(PROJECTION_SALES)                PROJECTION_SALES,
             Sum(PROJECTION_UNITS)                PROJECTION_UNITS,
             Sum(PROJECTION_SALES * U.ITEM_PRICE) AS NM_COGS_PROJECTED,
             PERIOD,
             YEAR,
             PRD.DISCOUNT,
             PRD.CFF_MASTER_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             PARENT_HIERARCHY_NO
      INTO   #NM_SALES_PROJECTION
      FROM   #CURRENT_RS_DATA_PERIOD PRD
             JOIN NM_SALES_PROJECTION_MASTER NMDP
               ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
             JOIN NM_SALES_PROJECTION NSP
               ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                  AND PRD.PERIOD_SID = NSP.PERIOD_SID
                  AND PRD.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
             JOIN PROJECTION_DETAILS PD
               ON PD.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
             JOIN CCP_DETAILS CD
               ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
             LEFT JOIN #ITEM_PRICING U
                    ON CD.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                       AND PRD.PERIOD_SID = U.PERIOD_SID
      WHERE  EXISTS (SELECT 1
                     FROM   #SALES_INFORMATION A
                     WHERE  A.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                            AND A.CFF_MASTER_SID = PRD.CFF_MASTER_SID)
             AND EXISTS (SELECT 1
                         FROM   #DISCOUNT_INFORMATION A
                         WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                                AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                AND PRD.DISCOUNT = A.DISCOUNT)
      GROUP  BY PERIOD,
                YEAR,
                PRD.DISCOUNT,
                PRD.CFF_MASTER_SID,
                HIERARCHY_NO,
                LEVEL_NAME,
                PARENT_HIERARCHY_NO

      --------------------------------------------------------- pulling non mandated  actual  information-------------------				
      IF Object_id('TEMPDB..#NM_ACTUAL_SALES') IS NOT NULL
        DROP TABLE #NM_ACTUAL_SALES

      SELECT Sum(ACTUAL_SALES)                ACTUAL_SALES,
             Sum(ACTUAL_UNITS)                ACTUAL_UNITS,
             Sum(ACTUAL_SALES * U.ITEM_PRICE) AS NM_COGS_ACTUAL,
             PERIOD,
             YEAR,
             PRD.DISCOUNT,
             PRD.CFF_MASTER_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             PARENT_HIERARCHY_NO
      INTO   #NM_ACTUAL_SALES
      FROM   #CURRENT_RS_DATA_PERIOD PRD
             JOIN NM_SALES_PROJECTION_MASTER NMDP
               ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
             JOIN NM_ACTUAL_SALES NSP
               ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                  AND PRD.PERIOD_SID = NSP.PERIOD_SID
                  AND PRD.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @ACT_END_PERIOD_SID
             JOIN PROJECTION_DETAILS PD
               ON PD.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
             JOIN CCP_DETAILS CD
               ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
             LEFT JOIN #ITEM_PRICING U
                    ON CD.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                       AND PRD.PERIOD_SID = U.PERIOD_SID
      WHERE  EXISTS (SELECT 1
                     FROM   #SALES_INFORMATION A
                     WHERE  A.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                            AND A.CFF_MASTER_SID = PRD.CFF_MASTER_SID)
             AND EXISTS (SELECT 1
                         FROM   #DISCOUNT_INFORMATION A
                         WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                                AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                AND PRD.DISCOUNT = A.DISCOUNT)
      GROUP  BY PERIOD,
                YEAR,
                PRD.DISCOUNT,
                PRD.CFF_MASTER_SID,
                HIERARCHY_NO,
                LEVEL_NAME,
                PARENT_HIERARCHY_NO

      --------------------------------------------------------- pulling  mandated  projection  information-------------------   
      IF Object_id('TEMPDB..#M_SALES_PROJECTION') IS NOT NULL
        DROP TABLE #M_SALES_PROJECTION

      SELECT Sum(PROJECTION_SALES)                PROJECTION_SALES,
             Sum(PROJECTION_UNITS)                PROJECTION_UNITS,
             Sum(PROJECTION_SALES * U.ITEM_PRICE) AS M_COGS_PROJECTED,
             PERIOD,
             YEAR,
             PRD.DISCOUNT,
             PRD.CFF_MASTER_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             PARENT_HIERARCHY_NO
      INTO   #M_SALES_PROJECTION
      FROM   #CURRENT_RS_DATA_PERIOD PRD
             JOIN M_SALES_PROJECTION_MASTER NMDP
               ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
             JOIN M_SALES_PROJECTION NSP
               ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                  AND PRD.PERIOD_SID = NSP.PERIOD_SID
                  AND PRD.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @ACT_END_PERIOD_SID
             JOIN #DISCOUNT_INFORMATION A
               ON A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                  AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                  AND PRD.DISCOUNT = A.DISCOUNT
             JOIN CCP_DETAILS CD
               ON CD.CCP_DETAILS_SID = a.CCP_DETAILS_SID
             LEFT JOIN #ITEM_PRICING U
                    ON CD.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                       AND PRD.PERIOD_SID = U.PERIOD_SID
      GROUP  BY PERIOD,
                YEAR,
                PRD.DISCOUNT,
                PRD.CFF_MASTER_SID,
                HIERARCHY_NO,
                LEVEL_NAME,
                PARENT_HIERARCHY_NO

      --------------------------------------------------------- pulling  mandated  actual  information-------------------  				 
      IF Object_id('TEMPDB..#M_ACTUAL_SALES') IS NOT NULL
        DROP TABLE #M_ACTUAL_SALES

      SELECT Sum(ACTUAL_SALES)                ACTUAL_SALES,
             Sum(ACTUAL_UNITS)                ACTUAL_UNITS,
             Sum(ACTUAL_SALES * U.ITEM_PRICE) AS M_COGS_ACTUAL,
             PERIOD,
             YEAR,
             PRD.DISCOUNT,
             PRD.CFF_MASTER_SID,
             HIERARCHY_NO,
             LEVEL_NAME,
             PARENT_HIERARCHY_NO
      INTO   #M_ACTUAL_SALES
      FROM   #CURRENT_RS_DATA_PERIOD PRD
             JOIN M_SALES_PROJECTION_MASTER NMDP
               ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
             JOIN M_ACTUAL_SALES NSP
               ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                  AND PRD.PERIOD_SID = NSP.PERIOD_SID
                  AND PRD.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
             JOIN #DISCOUNT_INFORMATION A
               ON A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                  AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                  AND PRD.DISCOUNT = A.DISCOUNT
             JOIN CCP_DETAILS CD
               ON CD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
             LEFT JOIN #ITEM_PRICING U
                    ON CD.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                       AND PRD.PERIOD_SID = U.PERIOD_SID
      GROUP  BY PERIOD,
                YEAR,
                PRD.DISCOUNT,
                PRD.CFF_MASTER_SID,
                HIERARCHY_NO,
                LEVEL_NAME,
                PARENT_HIERARCHY_NO

      --------------------------------------------------------- Returnsgaluat-543--------------------------
      --SELECT HIERARCHY_NO,
      --             LEVEL_NAME,
      --             YEAR,
      --             PERIOD,
      --             AVG(TR.ACTUAL_RATE)    ACTUAL_RATE,
      --             AVG(TR.PROJECTED_RATE) PROJECTED_RATE
      --      INTO   #RETURNS
      --      FROM   #TEMP_RETURNS TR
      --      JOIN   CCP_DETAILS CC ON TR.ITEM_MASTER_SID = CC.ITEM_MASTER_SID
      --      JOIN   #CCP_INFO C ON C.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
      --      JOIN   #PERIOD P ON P.PERIOD_SID = TR.PERIOD_SID
      --      GROUP  BY HIERARCHY_NO,
      --                LEVEL_NAME,
      --                YEAR,
      --                PERIOD
      --IF Object_id('TEMPDB..#RETURNS') IS NOT NULL
      --  DROP TABLE #RETURNS
      --SELECT HIERARCHY_NO,
      --       LEVEL_NAME,
      --       YEAR,
      --       PERIOD,
      --       Isnull(Sum(( Isnull(NSP.projection_sales, 0)
      --                    + Isnull(MSP.projection_sales, 0) ) * TR.PROJECTED_RATE), 0)AS Returns_Projected,
      --       Isnull(Sum(( Isnull(NAS.actual_sales, 0)
      --                    + Isnull(MAS.actual_sales, 0) ) * TR.ACTUAL_RATE), 0)       AS Returns_actuals,
      --       RS_NAME
      --INTO   #RETURNS
      --FROM   #TEMP_RETURNS TR
      --       JOIN CCP_DETAILS CC
      --         ON TR.ITEM_MASTER_SID = CC.ITEM_MASTER_SID
      --       JOIN #CCP_INFO C
      --         ON C.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
      --       JOIN #PERIOD P
      --         ON P.PERIOD_SID = TR.PERIOD_SID
      --       LEFT OUTER JOIN NM_SALES_PROJECTION NSP
      --                    ON NSP.PROJECTION_DETAILS_SID = C.PROJECTION_DETAILS_SID
      --                       AND p.PERIOD_SID = NSP.PERIOD_SID
      --                       AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
      --       LEFT OUTER JOIN M_SALES_PROJECTION MSP
      --                    ON MSP.PROJECTION_DETAILS_SID = c.PROJECTION_DETAILS_SID
      --                       AND p.PERIOD_SID = MSP.PERIOD_SID
      --                       AND MSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
      --       LEFT OUTER JOIN NM_ACTUAL_SALES NAS
      --                    ON NAS.PROJECTION_DETAILS_SID = c.PROJECTION_DETAILS_SID
      --                       AND p.PERIOD_SID = NAS.PERIOD_SID
      --                       AND NAS.PERIOD_SID BETWEEN @ACT_START_PERIOD_SID AND @ACT_END_PERIOD_SID
      --       LEFT OUTER JOIN M_ACTUAL_SALES MAS
      --                    ON MAS.PROJECTION_DETAILS_SID = c.PROJECTION_DETAILS_SID
      --                       AND p.PERIOD_SID = MAS.PERIOD_SID
      --                       AND MAS.PERIOD_SID BETWEEN @ACT_START_PERIOD_SID AND @ACT_END_PERIOD_SID
      --GROUP  BY HIERARCHY_NO,
      --          LEVEL_NAME,
      --          YEAR,
      --          PERIOD,
      --          RS_NAME
      -------------------------------------------------aggregating  sales information ------------------------------
      IF Object_id('TEMPDB..#SALES') IS NOT NULL
        DROP TABLE #SALES

      SELECT FD.YEAR,
             FD.PERIOD,
             FD.CFF_MASTER_SID,
             FD.DISCOUNT,
             FD.HIERARCHY_NO,
             FD.LEVEL_NAME,
             FD.PARENT_HIERARCHY_NO,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE((NSP.PROJECTION_SALES), 0)
                    + COALESCE((MSP.PROJECTION_SALES), 0)
             END AS PROJECTION_SALES,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE((NSP.PROJECTION_UNITS), 0)
                    + COALESCE((MSP.PROJECTION_UNITS), 0)
             END AS PROJECTION_UNITS,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE((NAS.ACTUAL_SALES), 0)
                    + COALESCE((MAS.ACTUAL_SALES), 0)
             END AS ACTUAL_SALES,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE((NAS.ACTUAL_UNITS), 0)
                    + COALESCE((MAS.ACTUAL_UNITS), 0)
             END AS ACTUAL_UNITS,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE((MSP.M_COGS_PROJECTED), 0)
                    + COALESCE((NSP.NM_COGS_PROJECTED), 0)
             END AS COGS_PROJECTED,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE((MAS.M_COGS_ACTUAL), 0)
                    + COALESCE((NAS.NM_COGS_ACTUAL), 0)
             END AS COGS_ACTUAL,
             --RETURNS_ACTUALS = CASE
             --                    WHEN Thd.INDICATOR IS NULL THEN NULL
             --                    ELSE RETURNS_ACTUALS
             --                  END,
             --RETURNS_PROJECTED = CASE
             --                      WHEN THd.INDICATOR IS NULL THEN NULL
             --                      ELSE RETURNS_PROJECTED
             --                    END,
             --ACTUAL_RATE = ( CASE
             --                  WHEN TH.INDICATOR IS NULL
             --                        OR THd.INDICATOR IS NULL THEN NULL
             --                  ELSE Isnull(( RETURNS_ACTUALS / NULLIF(COALESCE((NAS.ACTUAL_SALES), 0)
             --                                                         + COALESCE((MAS.ACTUAL_SALES), 0), 0) ), 0)
             --                END ),
             --PROJECTED_RATE = ( CASE
             --                     WHEN TH.INDICATOR IS NULL
             --                           OR THd.INDICATOR IS NULL THEN NULL
             --                     ELSE Isnull(( RETURNS_PROJECTED / NULLIF(COALESCE((NSP.PROJECTION_SALES), 0)
             --                                                              + COALESCE((MSP.PROJECTION_SALES), 0), 0) ), 0)
             --                   END ),
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE(( NAS.ACTUAL_SALES ), 0)
             END AS NM_ACTUAL_SALES,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE(( NSP.PROJECTION_SALES ), 0)
             END AS NM_PROJECTED_SALES,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE(( NAS.ACTUAL_units ), 0)
             END AS NM_ACTUAL_units,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE(( NSP.PROJECTION_units ), 0)
             END AS NM_PROJECTED_units,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE(( MAS.ACTUAL_SALES ), 0)
             END AS M_ACTUAL_SALES,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE(( MSP.PROJECTION_SALES ), 0)
             END AS M_PROJECTED_SALES,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE(( MAS.ACTUAL_UNITS ), 0)
             END AS M_ACTUAL_UNITS,
             CASE
               WHEN TH.INDICATOR IS NULL THEN NULL
               ELSE COALESCE(( MSP.PROJECTION_UNITS ), 0)
             END AS M_PROJECTED_UNITS
      ---RS_NAME
      INTO   #SALES
      FROM   (SELECT DISTINCT CFF_MASTER_SID,
                              YEAR,
                              PERIOD,
                              DISCOUNT,
                              LEVEL_NAME,
                              HIERARCHY_NO,
                              PARENT_HIERARCHY_NO
              FROM   #CURRENT_RS_DATA_PERIOD) FD
             LEFT JOIN #NM_SALES_PROJECTION NSP
                    ON NSP.YEAR = FD.YEAR
                       AND NSP.PERIOD = FD.PERIOD
                       AND NSP.CFF_MASTER_SID = FD.CFF_MASTER_SID
                       AND NSP.DISCOUNT = FD.DISCOUNT
                       AND NSP.HIERARCHY_NO = FD.HIERARCHY_NO
                       AND NSP.LEVEL_NAME = FD.LEVEL_NAME
                       AND COALESCE(NSP.PARENT_HIERARCHY_NO, NSP.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
             LEFT JOIN #M_SALES_PROJECTION MSP
                    ON MSP.YEAR = FD.YEAR
                       AND MSP.PERIOD = FD.PERIOD
                       AND MSP.CFF_MASTER_SID = FD.CFF_MASTER_SID
                       AND MSP.DISCOUNT = FD.DISCOUNT
                       AND MSP.HIERARCHY_NO = FD.HIERARCHY_NO
                       AND MSP.LEVEL_NAME = FD.LEVEL_NAME
                       AND COALESCE(MSP.PARENT_HIERARCHY_NO, MSP.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
             LEFT JOIN #NM_ACTUAL_SALES NAS
                    ON NAS.YEAR = FD.YEAR
                       AND NAS.PERIOD = FD.PERIOD
                       AND NAS.CFF_MASTER_SID = FD.CFF_MASTER_SID
                       AND NAS.DISCOUNT = FD.DISCOUNT
                       AND NAS.HIERARCHY_NO = FD.HIERARCHY_NO
                       AND NAS.LEVEL_NAME = FD.LEVEL_NAME
                       AND COALESCE(NAS.PARENT_HIERARCHY_NO, NAS.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
             LEFT JOIN #M_ACTUAL_SALES MAS
                    ON MAS.YEAR = FD.YEAR
                       AND MAS.PERIOD = FD.PERIOD
                       AND MAS.CFF_MASTER_SID = FD.CFF_MASTER_SID
                       AND MAS.DISCOUNT = FD.DISCOUNT
                       AND MAS.HIERARCHY_NO = FD.HIERARCHY_NO
                       AND MAS.LEVEL_NAME = FD.LEVEL_NAME
                       AND COALESCE(MAS.PARENT_HIERARCHY_NO, MAS.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
             LEFT JOIN #TEMP_HIER_SALES TH
                    ON TH.HIERARCHY_NO = FD.HIERARCHY_NO
             LEFT OUTER JOIN #TEMP_HIER_DISC THd
                          ON THD.HIERARCHY_NO = FD.HIERARCHY_NO

      -------------------------------------------------aggregating  discount information ------------------------------ 						
      IF Object_id('TEMPDB..#DISCOUNT') IS NOT NULL
        DROP TABLE #DISCOUNT

      SELECT FD.YEAR,
             FD.PERIOD,
             COALESCE(( NSP.PROJECTION_SALES ), 0) AS PROJECTION_SALES,
             COALESCE(( NAD.ACTUAL_SALES ), 0)     AS ACTUAL_SALES,
             FD.CFF_MASTER_SID,
             FD.DISCOUNT                           AS RS_NAME,
             FD.LEVEL_NAME,
             FD.HIERARCHY_NO,
             FD.PARENT_HIERARCHY_NO,
             ACCRUAL_AMOUNT_PROJECTION,
             ACCRUAL_AMOUNT_ACTUAL
      INTO   #DISCOUNT
      FROM   (SELECT DISTINCT CFF_MASTER_SID,
                              YEAR,
                              PERIOD,
                              DISCOUNT,
                              NM_PPA_IND,
                              LEVEL_NAME,
                              HIERARCHY_NO,
                              PARENT_HIERARCHY_NO
              FROM   #CURRENT_RS_DATA_PERIOD) FD
             LEFT JOIN #NM_DISCOUNT_PROJECTION NSP
                    ON NSP.YEAR = FD.YEAR
                       AND NSP.PERIOD = FD.PERIOD
                       AND NSP.DISCOUNT = FD.DISCOUNT
                       AND FD.CFF_MASTER_SID = NSP.CFF_MASTER_SID
                       AND NSP.HIERARCHY_NO = FD.HIERARCHY_NO
                       AND NSP.LEVEL_NAME = FD.LEVEL_NAME
                       AND COALESCE(NSP.PARENT_HIERARCHY_NO, NSP.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
             LEFT JOIN #NM_ACTUAL_DISCOUNT NAD
                    ON NAD.YEAR = FD.YEAR
                       AND NAD.PERIOD = FD.PERIOD
                       AND FD.CFF_MASTER_SID = NAD.CFF_MASTER_SID
                       AND NAD.DISCOUNT = FD.DISCOUNT
                       AND NAD.HIERARCHY_NO = FD.HIERARCHY_NO
                       AND NAD.LEVEL_NAME = FD.LEVEL_NAME
                       AND COALESCE(NAD.PARENT_HIERARCHY_NO, NAD.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
             LEFT OUTER JOIN #TEMP_HIER_DISC TH
                          ON TH.HIERARCHY_NO = FD.HIERARCHY_NO
      WHERE  EXISTS (SELECT 1
                     FROM   #DISCOUNT_INFORMATION C
                     WHERE  C.CFF_MASTER_SID = FD.CFF_MASTER_SID
                            AND C.DISCOUNT = FD.DISCOUNT)
             AND FD.NM_PPA_IND = 'NM'

      -------------------------------------------------aggregating  ppa  information ------------------------------  
      IF Object_id('TEMPDB..#PPA') IS NOT NULL
        DROP TABLE #PPA

      SELECT FD.YEAR,
             FD.PERIOD,
             FD.DISCOUNT,
             FD.CFF_MASTER_SID,
             COALESCE(( NSP.PROJECTION_DISCOUNT_DOLLAR ), 0) AS PPA_DISCOUNT_PROJECTED,
             COALESCE(( NAS.ACTUAL_DISCOUNT_DOLLAR ), 0)     AS PPA_ACTUAL_SALES,
             FD.LEVEL_NAME,
             FD.HIERARCHY_NO,
             FD.PARENT_HIERARCHY_NO
      INTO   #PPA
      FROM   (SELECT DISTINCT CFF_MASTER_SID,
                              YEAR,
                              PERIOD,
                              DISCOUNT,
                              NM_PPA_IND,
                              LEVEL_NAME,
                              HIERARCHY_NO,
                              PARENT_HIERARCHY_NO
              FROM   #CURRENT_RS_DATA_PERIOD) FD
             LEFT JOIN #NM_PPA_PROJECTION NSP
                    ON NSP.YEAR = FD.YEAR
                       AND NSP.PERIOD = FD.PERIOD
                       AND NSP.CFF_MASTER_SID = FD.CFF_MASTER_SID
                       AND NSP.DISCOUNT = FD.DISCOUNT
                       AND NSP.HIERARCHY_NO = FD.HIERARCHY_NO
                       AND NSP.LEVEL_NAME = FD.LEVEL_NAME
                       AND COALESCE(NSP.PARENT_HIERARCHY_NO, NSP.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
             LEFT JOIN #NM_ACTUAL_PPA NAS
                    ON NAS.YEAR = FD.YEAR
                       AND NAS.PERIOD = FD.PERIOD
                       AND NAS.CFF_MASTER_SID = FD.CFF_MASTER_SID
                       AND NAS.DISCOUNT = FD.DISCOUNT
                       AND NAS.HIERARCHY_NO = FD.HIERARCHY_NO
                       AND NAS.LEVEL_NAME = FD.LEVEL_NAME
                       AND COALESCE(NAS.PARENT_HIERARCHY_NO, NAS.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
      WHERE  FD.NM_PPA_IND = 'PPA'

      -------------------------------------------------aggregating    supplemental information ------------------------------  
      IF Object_id('TEMPDB..#PPA_SUPP_MANDATED') IS NOT NULL
        DROP TABLE #PPA_SUPP_MANDATED

      SELECT FD.YEAR,
             FD.PERIOD,
             CASE
               WHEN TD.INDICATOR IS NULL THEN NULL
               ELSE COALESCE(( SDP.PROJECTION_SALES ), 0)
             END AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
             CASE
               WHEN TD.INDICATOR IS NULL THEN NULL
               ELSE COALESCE(( MDP.PROJECTION_SALES ), 0)
             END AS MANDATED_DISCOUNT_PROJECTED,
             CASE
               WHEN TD.INDICATOR IS NULL THEN NULL
               ELSE COALESCE(( SDA.ACTUAL_SALES ), 0)
             END AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
             CASE
               WHEN TD.INDICATOR IS NULL THEN NULL
               ELSE COALESCE(( MAD.ACTUAL_SALES ), 0)
             END AS MANDATED_DISCOUNT_ACTUALS,
             FD.CFF_MASTER_SID,
             FD.HIERARCHY_NO,
             FD.LEVEL_NAME,
             FD.DISCOUNT,
             FD.PARENT_HIERARCHY_NO
      INTO   #PPA_SUPP_MANDATED
      FROM   (SELECT DISTINCT CFF_MASTER_SID,
                              YEAR,
                              PERIOD,
                              DISCOUNT,
                              LEVEL_NAME,
                              HIERARCHY_NO,
                              PARENT_HIERARCHY_NO
              FROM   #CURRENT_RS_DATA_PERIOD) FD
             LEFT JOIN #M_SUPPLEMENTAL_DISC_PROJ SDP
                    ON SDP.YEAR = FD.YEAR
                       AND SDP.PERIOD = FD.PERIOD
                       AND SDP.CFF_MASTER_SID = FD.CFF_MASTER_SID
                       AND SDP.DISCOUNT = FD.DISCOUNT
                       AND SDP.HIERARCHY_NO = fd.HIERARCHY_NO
                       AND SDP.LEVEL_NAME = fd.LEVEL_NAME
                       AND COALESCE(SDP.PARENT_HIERARCHY_NO, SDP.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
             LEFT JOIN #M_SUPPLEMENTAL_DISC_ACTUALS SDA
                    ON SDA.YEAR = FD.YEAR
                       AND SDA.PERIOD = FD.PERIOD
                       AND SDA.CFF_MASTER_SID = FD.CFF_MASTER_SID
                       AND SDA.DISCOUNT = FD.DISCOUNT
                       AND SDA.HIERARCHY_NO = fd.HIERARCHY_NO
                       AND SDA.LEVEL_NAME = fd.LEVEL_NAME
                       AND COALESCE(SDA.PARENT_HIERARCHY_NO, SDA.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
             LEFT JOIN #M_DISCOUNT_PROJECTION MDP
                    ON MDP.YEAR = FD.YEAR
                       AND MDP.PERIOD = FD.PERIOD
                       AND MDP.CFF_MASTER_SID = FD.CFF_MASTER_SID
                       AND MDP.DISCOUNT = FD.DISCOUNT
                       AND MDP.HIERARCHY_NO = fd.HIERARCHY_NO
                       AND MDP.LEVEL_NAME = fd.LEVEL_NAME
                       AND COALESCE(MDP.PARENT_HIERARCHY_NO, MDP.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
             LEFT JOIN #M_ACTUAL_DISCOUNT MAD
                    ON MAD.YEAR = FD.YEAR
                       AND MAD.PERIOD = FD.PERIOD
                       AND MAD.CFF_MASTER_SID = FD.CFF_MASTER_SID
                       AND MAD.DISCOUNT = FD.DISCOUNT
                       AND MAD.HIERARCHY_NO = fd.HIERARCHY_NO
                       AND MAD.LEVEL_NAME = fd.LEVEL_NAME
                       AND COALESCE(MAD.PARENT_HIERARCHY_NO, MAD.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
             LEFT JOIN #TEMP_HIER_DISC TD
                    ON TD.HIERARCHY_NO = FD.HIERARCHY_NO;

      /*
      IF Object_id('TEMPDB..#PRODUCT_FILE_DATA') IS NOT NULL
      	DROP TABLE #PRODUCT_FILE_DATA
      
      SELECT P.YEAR
      	,P.PERIOD
      	,SUM(EX_FACTORY_SALES_ACTUALS) AS EX_FACTORY_SALES_ACTUALS
      	,SUM(EX_FACTORY_SALES_PROJ) AS EX_FACTORY_SALES_PROJ
      	,SUM(DEMAND_SALES_ACTUALS) AS DEMAND_SALES_ACTUALS
      	,SUM(DEMAND_SALES_PROJ) AS DEMAND_SALES_PROJ
      	,SUM(ADJUSTED_DEMAND_ACTUALS) AS ADJUSTED_DEMAND_ACTUALS
      	,SUM(ADJUSTED_DEMAND_PROJ) AS ADJUSTED_DEMAND_PROJ
      	,SUM(IW_MASTER_ACTUALS) AS IW_MASTER_ACTUALS
      	,SUM(IW_MASTER_PROJ) AS IW_MASTER_PROJ
      	,CF.CFF_MASTER_SID AS CFF_MASTER_SID
      	,b.DISCOUNT
      INTO #PRODUCT_FILE_DATA
      FROM CFF_PRODUCT_LEVEL_FILES_DATA CF
      JOIN (
      	SELECT DISTINCT a.CFF_MASTER_SID
      		,ITEM_MASTER_SID
      		,DISCOUNT
      	FROM #TEMP_CCP A
      	JOIN #CURRENT_RS_DATA_PERIOD B ON A.CFF_MASTER_SID = B.CFF_MASTER_SID
      	and a.projection_details_sid=b.projection_details_sid
      		AND A.HIERARCHY_NO = B.HIERARCHY_NO
      		AND A.LEVEL_NAME = B.LEVEL_NAME
      		AND COALESCE(A.PARENT_HIERARCHY_NO, A.HIERARCHY_NO) = COALESCE(B.PARENT_HIERARCHY_NO, B.HIERARCHY_NO)
      	) B ON CF.CFF_MASTER_SID = B.CFF_MASTER_SID
      	AND CF.ITEM_MASTER_SID = B.ITEM_MASTER_SID
      INNER JOIN #PERIOD P ON P.PERIOD_SID = CF.PERIOD_SID
      GROUP BY P.YEAR
      	,P.PERIOD
      	,CF.CFF_MASTER_SID
      	,B.DISCOUNT
      	*/
      IF Object_id('TEMPDB..#PRODUCT_FILE_DATA') IS NOT NULL
        DROP TABLE #PRODUCT_FILE_DATA

      SELECT P.YEAR,
             P.PERIOD,
             Sum(EX_FACTORY_SALES_ACTUALS) AS EX_FACTORY_SALES_ACTUALS,
             Sum(EX_FACTORY_SALES_PROJ)    AS EX_FACTORY_SALES_PROJ,
             Sum(DEMAND_SALES_ACTUALS)     AS DEMAND_SALES_ACTUALS,
             Sum(DEMAND_SALES_PROJ)        AS DEMAND_SALES_PROJ,
             Sum(ADJUSTED_DEMAND_ACTUALS)  AS ADJUSTED_DEMAND_ACTUALS,
             Sum(ADJUSTED_DEMAND_PROJ)     AS ADJUSTED_DEMAND_PROJ,
             Sum(IW_MASTER_ACTUALS)        AS IW_MASTER_ACTUALS,
             Sum(IW_MASTER_PROJ)           AS IW_MASTER_PROJ,
             CF.CFF_MASTER_SID             AS CFF_MASTER_SID,
             B.HIERARCHY_NO,
             B.LEVEL_NAME,
             B.PARENT_HIERARCHY_NO,
             b.DISCOUNT
      INTO   #PRODUCT_FILE_DATA
      FROM   CFF_PRODUCT_LEVEL_FILES_DATA CF
             JOIN (SELECT DISTINCT a.CFF_MASTER_SID,
                                   a.ITEM_MASTER_SID,
                                   a.HIERARCHY_NO,
                                   a.LEVEL_NAME,
                                   a.PARENT_HIERARCHY_NO,
                                   b.DISCOUNT
                   FROM   #TEMP_CCP A
                          JOIN #CURRENT_RS_DATA_PERIOD B
                            ON A.CFF_MASTER_SID = B.CFF_MASTER_SID
                               AND a.projection_details_sid = b.projection_details_sid
                               AND A.HIERARCHY_NO = B.HIERARCHY_NO
                               AND A.LEVEL_NAME = B.LEVEL_NAME
                               AND COALESCE(A.PARENT_HIERARCHY_NO, A.HIERARCHY_NO) = COALESCE(B.PARENT_HIERARCHY_NO, B.HIERARCHY_NO)) B
               ON CF.CFF_MASTER_SID = B.CFF_MASTER_SID
                  AND CF.ITEM_MASTER_SID = B.ITEM_MASTER_SID
             INNER JOIN #PERIOD P
                     ON P.PERIOD_SID = CF.PERIOD_SID
      GROUP  BY P.YEAR,
                P.PERIOD,
                CF.CFF_MASTER_SID,
                B.DISCOUNT,
                B.HIERARCHY_NO,
                B.LEVEL_NAME,
                B.PARENT_HIERARCHY_NO

      -------------------------based on the forecasting_type result will be stored in result---------------------------
      IF EXISTS (SELECT 1
                 FROM   #CCP_INFO
                 WHERE  forecasting_type = 'non mandated')
        BEGIN
            IF @var = 1
              BEGIN
                  INSERT INTO #RESULT
                              (CFF_PROJECTION_MASTER_SID,
                               HIERARCHY_NO,
                               LEVEL_NAME,
                               YEAR,
                               PERIOD,
                               RS_NAME,
                               PARENT_HIERARCHY_NO,
                               TOTAL_DISCOUNT,
                               TOTAL_DISCOUNT_PROJECTED,
                               TOTAL_DISCOUNT_PERCENTAGE,
                               TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                               TOTAL_RPU,
                               TOTAL_PROJECTED_RPU,
                               DISCOUNT_OF_EX_FACTORY_ACTUALS_PERCENT,
                               DISCOUNT_OF_EX_FACTORY_PROJECTED_PERCENT,
                               TOTAL_DISCOUNT_ACCRUAL)
                  --SELECT @FIRST_PROJ_SID,
                  --       RD.HIERARCHY_NO,
                  --       LEVEL_NAME,
                  --       YEAR,
                  --       PERIOD,
                  --       RS_NAME,
                  --       RETURNS_ACTUALS,
                  --       RETURNS_PROJECTED,
                  --       ACTUAL_RATE * 100,
                  --       PROJECTED_RATE * 100,
                  --       CASE
                  --         WHEN TS.INDICATOR IS NULL
                  --               OR TD.INDICATOR IS NULL THEN NULL
                  --         ELSE COALESCE(RETURNS_ACTUALS / NULLIF(RD.NM_ACTUAL_UNITS, 0), 0)
                  --       END,
                  --       CASE
                  --         WHEN TS.INDICATOR IS NULL
                  --               OR TD.INDICATOR IS NULL THEN NULL
                  --         ELSE COALESCE(RETURNS_PROJECTED / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
                  --       END
                  --FROM   #sales RD
                  --       LEFT JOIN #TEMP_HIER_SALES TS
                  --              ON TS.HIERARCHY_NO = RD.HIERARCHY_NO
                  --       LEFT JOIN #TEMP_HIER_DISC TD
                  --              ON TD.HIERARCHY_NO = RD.HIERARCHY_NO
                  --UNION ALL
                  SELECT @FIRST_PROJ_SID,
                         RD.HIERARCHY_NO,
                         RD.LEVEL_NAME,
                         ND.YEAR,
                         ND.PERIOD,
                         rc.RS_NAME,
                         ND.PARENT_HIERARCHY_NO,
                         ND.ACTUAL_SALES,
                         ND.PROJECTION_SALES,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                         --OR ND.INDICATOR = 0
                         THEN NULL
                           ELSE COALESCE(ND.ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_SALES, 0), 0) * 100
                         END,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                         --OR ND.INDICATOR = 0
                         THEN NULL
                           ELSE COALESCE(ND.PROJECTION_SALES / NULLIF(RD.NM_PROJECTED_SALES, 0), 0) * 100
                         END,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                         --OR ND.INDICATOR = 0
                         THEN NULL
                           ELSE COALESCE(ND.ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_UNITS, 0), 0)
                         END,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                         --OR ND.INDICATOR = 0
                         THEN NULL
                           ELSE COALESCE(ND.PROJECTION_SALES / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
                         END,
                         COALESCE(Isnull(ND.ACTUAL_SALES, 0) / NULLIF(EX_FACTORY_SALES_ACTUALS, 0), 0) * 100,
                         COALESCE(Isnull(ND.PROJECTION_SALES, 0) / NULLIF(EX_FACTORY_SALES_PROJ, 0), 0) * 100,
                         COALESCE(ACCRUAL_AMOUNT_PROJECTION, ACCRUAL_AMOUNT_ACTUAL) AS TOTAL_DISCOUNT_ACCRUAL
                  FROM   #DISCOUNT ND
                         JOIN #SALES RD
                           ON RD.YEAR = ND.YEAR
                              AND RD.PERIOD = ND.PERIOD
                              AND RD.DISCOUNT = ND.RS_NAME
                              AND RD.CFF_MASTER_SID = ND.CFF_MASTER_SID
                              AND RD.HIERARCHY_NO = ND.HIERARCHY_NO
                              AND RD.LEVEL_NAME = ND.LEVEL_NAME
                              AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(ND.PARENT_HIERARCHY_NO, ND.HIERARCHY_NO)
                         JOIN #PRODUCT_FILE_DATA PFD
                           ON PFD.YEAR = ND.YEAR
                              AND PFD.PERIOD = ND.PERIOD
                              AND PFD.DISCOUNT = ND.RS_NAME
                              AND PFD.CFF_MASTER_SID = ND.CFF_MASTER_SID
                              AND PFD.HIERARCHY_NO = ND.HIERARCHY_NO---------cel-462
                              AND PFD.LEVEL_NAME = ND.LEVEL_NAME---------cel-462
                              AND COALESCE(PFD.PARENT_HIERARCHY_NO, PFD.HIERARCHY_NO) = COALESCE(ND.PARENT_HIERARCHY_NO, ND.HIERARCHY_NO)---------cel-462
                         JOIN RS_CONTRACT RC
                           ON RS_CONTRACT_SID = ND.RS_NAME
                         LEFT JOIN #TEMP_HIER_SALES TS
                                ON TS.HIERARCHY_NO = RD.HIERARCHY_NO
                         LEFT JOIN #TEMP_HIER_DISC TD
                                ON TD.HIERARCHY_NO = ND.HIERARCHY_NO
                  UNION ALL
                  SELECT @FIRST_PROJ_SID,
                         RD.HIERARCHY_NO,
                         RD.LEVEL_NAME,
                         PD.YEAR,
                         PD.PERIOD,
                         RC.RS_NAME,
                         pd.PARENT_HIERARCHY_NO,
                         PD.PPA_ACTUAL_SALES,
                         PD.PPA_DISCOUNT_PROJECTED,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                                 OR TD.INDICATOR IS NULL THEN NULL
                           ELSE COALESCE(PD.PPA_ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_SALES, 0), 0) * 100
                         END,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                                 OR TD.INDICATOR IS NULL THEN NULL
                           ELSE COALESCE(PD.PPA_DISCOUNT_PROJECTED / NULLIF(RD.NM_PROJECTED_SALES, 0), 0) * 100
                         END,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                                 OR TD.INDICATOR IS NULL THEN NULL
                           ELSE COALESCE(PD.PPA_ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_UNITS, 0), 0)
                         END,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                                 OR TD.INDICATOR IS NULL THEN NULL
                           ELSE COALESCE(PD.PPA_DISCOUNT_PROJECTED / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
                         END,
                         COALESCE(Isnull(pd.PPA_ACTUAL_SALES, 0) / NULLIF(EX_FACTORY_SALES_ACTUALS, 0), 0) * 100,
                         COALESCE(Isnull(pd.PPA_DISCOUNT_PROJECTED, 0) / NULLIF(EX_FACTORY_SALES_PROJ, 0), 0) * 100,
                         0.00000 AS TOTAL_DISCOUNT_ACCRUAL
                  FROM   #PPA PD
                         JOIN #SALES RD
                           ON RD.YEAR = PD.YEAR
                              AND RD.PERIOD = PD.PERIOD
                              AND RD.DISCOUNT = PD.DISCOUNT
                              AND RD.CFF_MASTER_SID = PD.CFF_MASTER_SID
                              AND RD.HIERARCHY_NO = PD.HIERARCHY_NO
                              AND RD.LEVEL_NAME = PD.LEVEL_NAME
                              AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(PD.PARENT_HIERARCHY_NO, PD.HIERARCHY_NO)
                         JOIN #PRODUCT_FILE_DATA PFD
                           ON PFD.YEAR = RD.YEAR
                              AND PFD.PERIOD = RD.PERIOD
                              AND PFD.DISCOUNT = RD.DISCOUNT
                              AND PFD.CFF_MASTER_SID = RD.CFF_MASTER_SID
                              AND PFD.HIERARCHY_NO = RD.HIERARCHY_NO---------cel-462
                              AND PFD.LEVEL_NAME = RD.LEVEL_NAME---------cel-462
                              AND COALESCE(PFD.PARENT_HIERARCHY_NO, PFD.HIERARCHY_NO) = COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO)---------cel-462
                         JOIN RS_CONTRACT RC
                           ON RS_CONTRACT_SID = PD.DISCOUNT
                         LEFT JOIN #TEMP_HIER_SALES TS
                                ON TS.HIERARCHY_NO = RD.HIERARCHY_NO
                         LEFT JOIN #TEMP_HIER_DISC TD
                                ON TD.HIERARCHY_NO = PD.HIERARCHY_NO
              END
            ELSE
              BEGIN
                  INSERT INTO #RESULT
                              (CFF_PROJECTION_MASTER_SID,
                               HIERARCHY_NO,
                               LEVEL_NAME,
                               YEAR,
                               PERIOD,
                               RS_NAME,
                               PARENT_HIERARCHY_NO,
                               TOTAL_DISCOUNT,
                               TOTAL_DISCOUNT_PROJECTED,
                               TOTAL_DISCOUNT_PERCENTAGE,
                               TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                               TOTAL_RPU,
                               TOTAL_PROJECTED_RPU,
                               DISCOUNT_OF_EX_FACTORY_ACTUALS_PERCENT,
                               DISCOUNT_OF_EX_FACTORY_PROJECTED_PERCENT,
                               TOTAL_DISCOUNT_ACCRUAL)
                  --SELECT @FIRST_PROJ_SID,
                  --       RD.HIERARCHY_NO,
                  --       LEVEL_NAME,
                  --       YEAR,
                  --       PERIOD,
                  --       RS_NAME,
                  --       RETURNS_ACTUALS,
                  --       RETURNS_PROJECTED,
                  --       ACTUAL_RATE * 100,
                  --       PROJECTED_RATE * 100,
                  --       CASE
                  --         WHEN TS.INDICATOR IS NULL
                  --               OR TD.INDICATOR IS NULL THEN NULL
                  --         ELSE COALESCE(RETURNS_ACTUALS / NULLIF(RD.NM_ACTUAL_UNITS, 0), 0)
                  --       END,
                  --       CASE
                  --         WHEN TS.INDICATOR IS NULL
                  --               OR TD.INDICATOR IS NULL THEN NULL
                  --         ELSE COALESCE(RETURNS_PROJECTED / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
                  --       END
                  --FROM   #sales RD
                  --       LEFT JOIN #TEMP_HIER_SALES TS
                  --              ON TS.HIERARCHY_NO = RD.HIERARCHY_NO
                  --       LEFT JOIN #TEMP_HIER_DISC TD
                  --              ON TD.HIERARCHY_NO = RD.HIERARCHY_NO
                  --UNION ALL
                  SELECT @FIRST_PROJ_SID,
                         RD.HIERARCHY_NO,
                         RD.LEVEL_NAME,
                         ND.YEAR,
                         ND.PERIOD,
                         nd.RS_NAME,
                         nd.PARENT_HIERARCHY_NO,
                         ND.ACTUAL_SALES,
                         ND.PROJECTION_SALES,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                         --OR ND.INDICATOR = 0
                         THEN NULL
                           ELSE COALESCE(ND.ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_SALES, 0), 0) * 100
                         END,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                         --OR ND.INDICATOR = 0
                         THEN NULL
                           ELSE COALESCE(ND.PROJECTION_SALES / NULLIF(RD.NM_PROJECTED_SALES, 0), 0) * 100
                         END,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                         --OR ND.INDICATOR = 0
                         THEN NULL
                           ELSE COALESCE(ND.ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_UNITS, 0), 0)
                         END,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                         --OR ND.INDICATOR = 0
                         THEN NULL
                           ELSE COALESCE(ND.PROJECTION_SALES / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
                         END,
                         COALESCE(Isnull(ND.ACTUAL_SALES, 0) / NULLIF(EX_FACTORY_SALES_ACTUALS, 0), 0) * 100,
                         COALESCE(Isnull(ND.PROJECTION_SALES, 0) / NULLIF(EX_FACTORY_SALES_PROJ, 0), 0) * 100,
                         COALESCE(ACCRUAL_AMOUNT_PROJECTION, ACCRUAL_AMOUNT_ACTUAL) AS TOTAL_DISCOUNT_ACCRUAL
                  FROM   #DISCOUNT ND
                         JOIN #SALES RD
                           ON RD.YEAR = ND.YEAR
                              AND RD.PERIOD = ND.PERIOD
                              AND RD.DISCOUNT = ND.RS_NAME
                              AND RD.CFF_MASTER_SID = ND.CFF_MASTER_SID
                              AND RD.HIERARCHY_NO = ND.HIERARCHY_NO
                              AND RD.LEVEL_NAME = ND.LEVEL_NAME
                              AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(ND.PARENT_HIERARCHY_NO, ND.HIERARCHY_NO)
                         JOIN #PRODUCT_FILE_DATA PFD
                           ON PFD.YEAR = ND.YEAR
                              AND PFD.PERIOD = ND.PERIOD
                              AND PFD.DISCOUNT = ND.RS_NAME
                              AND PFD.CFF_MASTER_SID = ND.CFF_MASTER_SID
                              AND PFD.HIERARCHY_NO = ND.HIERARCHY_NO---------cel-462
                              AND PFD.LEVEL_NAME = ND.LEVEL_NAME---------cel-462
                              AND COALESCE(PFD.PARENT_HIERARCHY_NO, PFD.HIERARCHY_NO) = COALESCE(ND.PARENT_HIERARCHY_NO, ND.HIERARCHY_NO)---------cel-462
                         LEFT JOIN #TEMP_HIER_SALES TS
                                ON TS.HIERARCHY_NO = RD.HIERARCHY_NO
                         LEFT JOIN #TEMP_HIER_DISC TD
                                ON TD.HIERARCHY_NO = ND.HIERARCHY_NO
                  UNION ALL
                  SELECT @FIRST_PROJ_SID,
                         RD.HIERARCHY_NO,
                         RD.LEVEL_NAME,
                         PD.YEAR,
                         PD.PERIOD,
                         PD.discount RS_NAME,
                         pd.PARENT_HIERARCHY_NO,
                         PD.PPA_ACTUAL_SALES,
                         PD.PPA_DISCOUNT_PROJECTED,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                                 OR TD.INDICATOR IS NULL THEN NULL
                           ELSE COALESCE(PD.PPA_ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_SALES, 0), 0) * 100
                         END,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                                 OR TD.INDICATOR IS NULL THEN NULL
                           ELSE COALESCE(PD.PPA_DISCOUNT_PROJECTED / NULLIF(RD.NM_PROJECTED_SALES, 0), 0) * 100
                         END,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                                 OR TD.INDICATOR IS NULL THEN NULL
                           ELSE COALESCE(PD.PPA_ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_UNITS, 0), 0)
                         END,
                         CASE
                           WHEN TS.INDICATOR IS NULL
                                 OR TD.INDICATOR IS NULL THEN NULL
                           ELSE COALESCE(PD.PPA_DISCOUNT_PROJECTED / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
                         END,
                         COALESCE(Isnull(pd.PPA_ACTUAL_SALES, 0) / NULLIF(EX_FACTORY_SALES_ACTUALS, 0), 0) * 100,
                         COALESCE(Isnull(pd.PPA_DISCOUNT_PROJECTED, 0) / NULLIF(EX_FACTORY_SALES_PROJ, 0), 0) * 100,
                         0.00000     AS TOTAL_DISCOUNT_ACCRUAL
                  FROM   #PPA PD
                         JOIN #SALES RD
                           ON RD.YEAR = PD.YEAR
                              AND RD.PERIOD = PD.PERIOD
                              AND RD.DISCOUNT = PD.DISCOUNT
                              AND RD.CFF_MASTER_SID = PD.CFF_MASTER_SID
                              AND RD.HIERARCHY_NO = PD.HIERARCHY_NO
                              AND RD.LEVEL_NAME = PD.LEVEL_NAME
                              AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(PD.PARENT_HIERARCHY_NO, PD.HIERARCHY_NO)
                         JOIN #PRODUCT_FILE_DATA PFD
                           ON PFD.YEAR = RD.YEAR
                              AND PFD.PERIOD = RD.PERIOD
                              AND PFD.DISCOUNT = RD.DISCOUNT
                              AND PFD.CFF_MASTER_SID = RD.CFF_MASTER_SID
                              AND PFD.HIERARCHY_NO = RD.HIERARCHY_NO---------cel-462
                              AND PFD.LEVEL_NAME = RD.LEVEL_NAME---------cel-462
                              AND COALESCE(PFD.PARENT_HIERARCHY_NO, PFD.HIERARCHY_NO) = COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO)---------cel-462
                         LEFT JOIN #TEMP_HIER_SALES TS
                                ON TS.HIERARCHY_NO = RD.HIERARCHY_NO
                         LEFT JOIN #TEMP_HIER_DISC TD
                                ON TD.HIERARCHY_NO = PD.HIERARCHY_NO
              END
        END

      -------------------------based on the forecasting_type result will be stored in result---------------------------
      IF EXISTS (SELECT 1
                 FROM   #CCP_INFO
                 WHERE  forecasting_type = 'mandated')
        BEGIN
            INSERT INTO #RESULT
                        (CFF_PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         LEVEL_NAME,
                         YEAR,
                         PERIOD,
                         RS_NAME,
                         PARENT_HIERARCHY_NO,
                         TOTAL_DISCOUNT,
                         TOTAL_DISCOUNT_PROJECTED,
                         TOTAL_DISCOUNT_PERCENTAGE,
                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                         TOTAL_RPU,
                         TOTAL_PROJECTED_RPU,
                         DISCOUNT_OF_EX_FACTORY_ACTUALS_PERCENT,
                         DISCOUNT_OF_EX_FACTORY_PROJECTED_PERCENT)
            SELECT @FIRST_PROJ_SID,
                   RD.HIERARCHY_NO,
                   RD.LEVEL_NAME,
                   MD.YEAR,
                   MD.PERIOD,
                   'MANDATED',
                   md.PARENT_HIERARCHY_NO,
                   Isnull(MD.MANDATED_DISCOUNT_ACTUALS, 0),
                   Isnull(MD.MANDATED_DISCOUNT_PROJECTED, 0),
                   CASE
                     WHEN TS.INDICATOR IS NULL
                           OR TD.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(MD.MANDATED_DISCOUNT_ACTUALS / NULLIF(RD.M_ACTUAL_SALES, 0), 0) * 100
                   END,
                   CASE
                     WHEN TS.INDICATOR IS NULL
                           OR TD.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(MD.MANDATED_DISCOUNT_PROJECTED / NULLIF(RD.M_PROJECTED_SALES, 0), 0) * 100
                   END,
                   CASE
                     WHEN TS.INDICATOR IS NULL
                           OR TD.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(MD.MANDATED_DISCOUNT_ACTUALS / NULLIF(RD.M_ACTUAL_UNITS, 0), 0)
                   END,
                   CASE
                     WHEN TS.INDICATOR IS NULL
                           OR TD.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(MD.MANDATED_DISCOUNT_PROJECTED / NULLIF(RD.M_PROJECTED_UNITS, 0), 0)
                   END,
                   COALESCE(Isnull(md.MANDATED_DISCOUNT_ACTUALS, 0) / NULLIF(EX_FACTORY_SALES_ACTUALS, 0), 0) * 100,
                   COALESCE(Isnull(md.MANDATED_DISCOUNT_PROJECTED, 0) / NULLIF(EX_FACTORY_SALES_PROJ, 0), 0) * 100
            FROM   #PPA_SUPP_MANDATED MD
                   JOIN #sales RD
                     ON RD.YEAR = MD.YEAR
                        AND RD.PERIOD = MD.PERIOD
                        AND RD.DISCOUNT = md.DISCOUNT
                        AND RD.HIERARCHY_NO = MD.HIERARCHY_NO
                        AND RD.LEVEL_NAME = MD.LEVEL_NAME
                        AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(MD.PARENT_HIERARCHY_NO, MD.HIERARCHY_NO)
                   JOIN #PRODUCT_FILE_DATA PFD
                     ON PFD.YEAR = md.YEAR
                        AND PFD.PERIOD = md.PERIOD
                        AND PFD.DISCOUNT = md.DISCOUNT
                        AND PFD.CFF_MASTER_SID = md.CFF_MASTER_SID
                        AND PFD.HIERARCHY_NO = md.HIERARCHY_NO---------cel-462
                        AND PFD.LEVEL_NAME = md.LEVEL_NAME---------cel-462
                        AND COALESCE(PFD.PARENT_HIERARCHY_NO, PFD.HIERARCHY_NO) = COALESCE(MD.PARENT_HIERARCHY_NO, MD.HIERARCHY_NO)---------cel-462
                   LEFT JOIN #TEMP_HIER_SALES TS
                          ON TS.HIERARCHY_NO = MD.HIERARCHY_NO
                   LEFT JOIN #TEMP_HIER_DISC TD
                          ON TD.HIERARCHY_NO = MD.HIERARCHY_NO
            UNION ALL
            SELECT @FIRST_PROJ_SID,
                   RD.HIERARCHY_NO,
                   RD.LEVEL_NAME,
                   SD.YEAR,
                   SD.PERIOD,
                   'SUPPLEMENTAL',
                   sd.PARENT_HIERARCHY_NO,
                   Isnull(SD.SUPPLEMENTAL_DISCOUNT_ACTUALS, 0),
                   Isnull(SD.SUPPLEMENTAL_DISCOUNT_PROJECTED, 0),
                   CASE
                     WHEN TS.INDICATOR IS NULL
                           OR TD.INDICATOR IS NULL THEN NULL
                     ELSE Isnull(COALESCE(SD.SUPPLEMENTAL_DISCOUNT_ACTUALS / NULLIF(RD.M_ACTUAL_SALES, 0), 0) * 100, 0)
                   END,
                   CASE
                     WHEN TS.INDICATOR IS NULL
                           OR TD.INDICATOR IS NULL THEN NULL
                     ELSE Isnull(COALESCE(SD.SUPPLEMENTAL_DISCOUNT_PROJECTED / NULLIF(RD.M_PROJECTED_SALES, 0), 0), 0) * 100
                   END,
                   CASE
                     WHEN TS.INDICATOR IS NULL
                           OR TD.INDICATOR IS NULL THEN NULL
                     ELSE Isnull(COALESCE(SD.SUPPLEMENTAL_DISCOUNT_ACTUALS / NULLIF(RD.M_ACTUAL_UNITS, 0), 0), 0)
                   END,
                   CASE
                     WHEN TS.INDICATOR IS NULL
                           OR TD.INDICATOR IS NULL THEN NULL
                     ELSE Isnull(COALESCE(SD.SUPPLEMENTAL_DISCOUNT_PROJECTED / NULLIF(RD.M_PROJECTED_UNITS, 0), 0), 0)
                   END,
                   COALESCE(Isnull(sd.SUPPLEMENTAL_DISCOUNT_ACTUALS, 0) / NULLIF(EX_FACTORY_SALES_ACTUALS, 0), 0) * 100,
                   COALESCE(Isnull(sd.SUPPLEMENTAL_DISCOUNT_ACTUALS, 0) / NULLIF(EX_FACTORY_SALES_PROJ, 0), 0) * 100
            FROM   #PPA_SUPP_MANDATED sd
                   JOIN #SALES RD
                     ON RD.YEAR = sd.YEAR
                        AND RD.PERIOD = sd.PERIOD
                        AND RD.DISCOUNT = sd.DISCOUNT
                        AND RD.CFF_MASTER_SID = sd.CFF_MASTER_SID
                        AND RD.HIERARCHY_NO = sd.HIERARCHY_NO
                        AND RD.LEVEL_NAME = sd.LEVEL_NAME
                        AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(sd.PARENT_HIERARCHY_NO, sd.HIERARCHY_NO)
                   JOIN #PRODUCT_FILE_DATA PFD
                     ON PFD.YEAR = sd.YEAR
                        AND PFD.PERIOD = sd.PERIOD
                        AND PFD.DISCOUNT = sd.DISCOUNT
                        AND PFD.CFF_MASTER_SID = sd.CFF_MASTER_SID
                        AND PFD.HIERARCHY_NO = sd.HIERARCHY_NO---------cel-462
                        AND PFD.LEVEL_NAME = sd.LEVEL_NAME---------cel-462
                        AND COALESCE(PFD.PARENT_HIERARCHY_NO, PFD.HIERARCHY_NO) = COALESCE(sd.PARENT_HIERARCHY_NO, sd.HIERARCHY_NO)---------cel-462
                   LEFT JOIN #TEMP_HIER_SALES TS
                          ON TS.HIERARCHY_NO = sd.HIERARCHY_NO
                   LEFT JOIN #TEMP_HIER_DISC TD
                          ON TD.HIERARCHY_NO = SD.HIERARCHY_NO
        END

      -------------	 INSERTING INTO RESULT TABLE  BASED ON FORMULAS FOR EACH COLUMN----------------------------- 
      IF @SCREEN_NAME = 'ASSUMPTIONS'
        BEGIN
            SELECT CFF_PROJECTION_MASTER_SID,
                   r.HIERARCHY_NO,
                   LEVEL_NAME,
                   YEAR,
                   PERIOD,
                   RS_NAME,
                   TOTAL_DISCOUNT,
                   TOTAL_DISCOUNT_PROJECTED,
                   TOTAL_DISCOUNT_PERCENTAGE,
                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                   TOTAL_RPU,
                   TOTAL_PROJECTED_RPU,
                   PARENT_HIERARCHY_NO,
                   DISCOUNT_OF_EX_FACTORY_ACTUALS_PERCENT,
                   DISCOUNT_OF_EX_FACTORY_PROJECTED_PERCENT
            FROM   #RESULT R
            ORDER  BY HIERARCHY_NO,
                      PARENT_HIERARCHY_NO,
                      CASE
                        WHEN @VIEW = 'PIVOT' THEN YEAR
                      END,
                      CASE
                        WHEN @VIEW = 'PIVOT' THEN PERIOD
                      END,
                      CASE
                        WHEN @VIEW = 'PIVOT' THEN RS_NAME
                      END,
                      CASE
                        WHEN @VIEW <> 'PIVOT' THEN RS_NAME
                      END,
                      CASE
                        WHEN @VIEW <> 'PIVOT' THEN YEAR
                      END,
                      CASE
                        WHEN @VIEW <> 'PIVOT' THEN PERIOD
                      END
        END

      ------------------BASED ON THE SCREEN NAME <>ASSUMPTIONS starts here ---------------------
      ------PULLING CFFDETAILS ALL CFF PASSING as  INPUT----------------------------------
      IF @SCREEN_NAME <> 'ASSUMPTIONS'
        BEGIN
            IF Object_id('TEMPDB..#CFF_DETAILS') IS NOT NULL
              DROP TABLE #CFF_DETAILS

            SELECT CFF_DETAILS_SID,
                   CFF_MASTER_SID,
                   PROJECTION_MASTER_SID,
                   CCP_DETAILS_SID,
                   INBOUND_STATUS,
                   SALES_INCLUSION
            INTO   #CFF_DETAILS
            FROM   CFF_DETAILS A
            WHERE  EXISTS (SELECT CFF_PROJECTION_MASTER_SID
                           FROM   #CFF_PROJECTION_MASTER B
                           WHERE  A.CFF_MASTER_SID = B.CFF_PROJECTION_MASTER_SID)

            IF Object_id('TEMPDB..#PRIOR_TEMP_CCP') IS NOT NULL
              DROP TABLE #PRIOR_TEMP_CCP

            CREATE TABLE #PRIOR_TEMP_CCP
              (
                 CCP_DETAILS_SID        INT,
                 COMPANY_MASTER_SID     INT,
                 CONTRACT_MASTER_SID    INT,
                 ITEM_MASTER_SID        INT,
                 PROJECTION_DETAILS_SID INT,
                 CFF_MASTER_SID         INT,
                 HIERARCHY_NO           VARCHAR(100),
                 LEVEL_NAME             VARCHAR(100),
                 PROJECTION_MASTER_SID  INT,
                 PARENT_HIERARCHY_NO    VARCHAR(100)
              )

            INSERT INTO #PRIOR_TEMP_CCP
                        (CCP_DETAILS_SID,
                         COMPANY_MASTER_SID,
                         CONTRACT_MASTER_SID,
                         ITEM_MASTER_SID,
                         PROJECTION_DETAILS_SID,
                         CFF_MASTER_SID,
                         HIERARCHY_NO,
                         LEVEL_NAME,
                         PROJECTION_MASTER_SID,
                         PARENT_HIERARCHY_NO)
            SELECT CCP.CCP_DETAILS_SID,
                   CCP.COMPANY_MASTER_SID,
                   CCP.CONTRACT_MASTER_SID,
                   CCP.ITEM_MASTER_SID,
                   PD.PROJECTION_DETAILS_SID,
                   CD.CFF_MASTER_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   CD.PROJECTION_MASTER_SID,
                   PARENT_HIERARCHY_NO
            FROM   CCP_DETAILS CCP
                   INNER JOIN PROJECTION_DETAILS PD
                           ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                   JOIN #CCP C
                     ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                   JOIN CFF_DETAILS CD
                     ON C.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                        AND CD.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
            WHERE  EXISTS (SELECT CFF_PROJECTION_MASTER_SID
                           FROM   #CFF_PROJECTION_MASTER CFF
                           WHERE  ID <> 1
                                  AND CD.CFF_MASTER_SID = CFF.CFF_PROJECTION_MASTER_SID)

            --WHERE  CFF_MASTER_SID IN(SELECT CFF_PROJECTION_MASTER_SID
            --                         FROM   #CFF_PROJECTION_MASTER)
            ---------------------------------pulling rs_name information based on @DISCOUNT_VIEW ------------------------------------
            ------FINDING HIERARCHY_NO,LEVEL_NAME ACTUAL START DATE PROJ END DATE  ----------------------------------
            IF Object_id('TEMPDB.DBO.#PRIOR_RS_DATA_PERIOD', 'U') IS NOT NULL
              DROP TABLE #PRIOR_RS_DATA_PERIOD;

            SELECT DISCOUNT,
                   YEAR,
                   PERIOD,
                   F.CFF_MASTER_SID,
                   pd.PROJECTION_DETAILS_SID,
                   NM_PPA_IND,
                   PERIOD_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PARENT_HIERARCHY_NO
            INTO   #PRIOR_RS_DATA_PERIOD
            FROM   #PRIOR_RS_DATA PR
                   CROSS JOIN (SELECT CFF_PROJECTION_MASTER_SID AS CFF_MASTER_SID
                               FROM   #CFF_PROJECTION_MASTER
                               WHERE  ID <> 1) F
                   JOIN CFF_DETAILS CD
                     ON PR.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                        AND F.CFF_MASTER_SID = CD.CFF_MASTER_SID
                   JOIN PROJECTION_DETAILS PD
                     ON PR.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                        AND CD.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                   CROSS JOIN (SELECT DISTINCT YEAR,
                                               PERIOD,
                                               PERIOD_SID
                               FROM   #PERIOD
                               WHERE  PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @PROJ_END_PERIOD_SID) P

            ------finding HIERARCHY_NO,LEVEL_NAME_ccp  other than first cff ----------------------------------
            IF Object_id('TEMPDB.DBO.#PRIOR_CPP_INFO', 'U') IS NOT NULL
              DROP TABLE #PRIOR_CPP_INFO;

            SELECT CD.CFF_MASTER_SID,
                   PD.PROJECTION_MASTER_SID,
                   PD.PROJECTION_DETAILS_SID,
                   PD.CCP_DETAILS_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   FORECASTING_TYPE
            INTO   #PRIOR_CPP_INFO
            FROM   PROJECTION_DETAILS PD
                   JOIN CFF_DETAILS CD
                     ON CD.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                        AND CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                   JOIN PROJECTION_MASTER PM
                     ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                   JOIN #CCP C
                     ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
            WHERE  EXISTS (SELECT CFF_PROJECTION_MASTER_SID
                           FROM   #CFF_PROJECTION_MASTER CFF
                           WHERE  ID <> 1
                                  AND CD.CFF_MASTER_SID = CFF.CFF_PROJECTION_MASTER_SID)

            ------finding sales_inclusion   other than first cff ----------------------------------
            IF Object_id('TEMPDB.DBO.#PRIOR_SALES_INFORMATION', 'U') IS NOT NULL
              DROP TABLE #PRIOR_SALES_INFORMATION;

            SELECT DISTINCT CF.CFF_MASTER_SID,
                            CC.CCP_DETAILS_SID,
                            PD.PROJECTION_MASTER_SID,
                            PD.PROJECTION_DETAILS_SID,
                            cc.item_master_sid
            INTO   #PRIOR_SALES_INFORMATION
            FROM   CCP_DETAILS cc
                   JOIN CFF_DETAILS CF
                     ON CF.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
                   JOIN PROJECTION_DETAILS PD
                     ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
                        AND CF.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
            WHERE  EXISTS (SELECT CFF_PROJECTION_MASTER_SID
                           FROM   #CFF_PROJECTION_MASTER CFF
                           WHERE  ID <> 1
                                  AND CF.CFF_MASTER_SID = CFF.CFF_PROJECTION_MASTER_SID)
                   AND SALES_INCLUSION = 'Y'

            ------finding DEDUCTION_INCLUSION   other than first cff ----------------------------------
            IF Object_id('TEMPDB.DBO.#PRIOR_DISC_INFORMATION', 'U') IS NOT NULL
              DROP TABLE #PRIOR_DISC_INFORMATION;

            SELECT DISTINCT CF.CFF_MASTER_SID,
                            CC.CCP_DETAILS_SID,
                            PD.PROJECTION_MASTER_SID,
                            PD.PROJECTION_DETAILS_SID,
                            PRD.DISCOUNT
            INTO   #PRIOR_DISC_INFORMATION
            FROM   CFF_DEDUCTION_INCLUSION CDI
                   JOIN CFF_DETAILS CF
                     ON CF.CFF_DETAILS_SID = CDI.CFF_DETAILS_SID
                   JOIN CCP_DETAILS CC
                     ON CF.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
                   JOIN PROJECTION_DETAILS PD
                     ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
                        AND CF.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                   JOIN (SELECT DISTINCT CFF_MASTER_SID,
                                         PROJECTION_DETAILS_SID,
                                         DISCOUNT
                         FROM   #PRIOR_RS_DATA_PERIOD) PRD
                     ON PRD.CFF_MASTER_SID = CF.CFF_MASTER_SID
                        AND PRD.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
            WHERE  EXISTS (SELECT CFF_PROJECTION_MASTER_SID
                           FROM   #CFF_PROJECTION_MASTER T
                           WHERE  ID <> 1
                                  AND T.CFF_PROJECTION_MASTER_SID = CF.CFF_MASTER_SID)
                   AND CDI.DEDUCTION_INCLUSION = 'Y'

            ------finding INDICATOR(SALES_INCLUSION)   other than first cff ----------------------------------
            IF Object_id('TEMPDB..#TEMP_PRIOR_HIER_SALES') IS NOT NULL
              DROP TABLE #TEMP_PRIOR_HIER_SALES

            CREATE TABLE #TEMP_PRIOR_HIER_SALES
              (
                 HIERARCHY_NO   VARCHAR(50),
                 CFF_MASTER_SID INT,
                 INDICATOR      BIT,
                 PRIMARY KEY (CFF_MASTER_SID, HIERARCHY_NO)
              )

            INSERT INTO #TEMP_PRIOR_HIER_SALES
                        (HIERARCHY_NO,
                         CFF_MASTER_SID,
                         INDICATOR)
            SELECT DISTINCT A.HIERARCHY_NO,
                            A.CFF_MASTER_SID,
                            1
            FROM   #PRIOR_CPP_INFO A
                   JOIN #PRIOR_SALES_INFORMATION B
                     ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                        AND A.CFF_MASTER_SID = B.CFF_MASTER_SID

            ------finding INDICATOR(DEDUCTION_INCLUSION)   other than first cff ----------------------------------
            IF Object_id('TEMPDB..#TEMP_PRIOR_HIER_DISC') IS NOT NULL
              DROP TABLE #TEMP_PRIOR_HIER_DISC

            CREATE TABLE #TEMP_PRIOR_HIER_DISC
              (
                 HIERARCHY_NO   VARCHAR(50),
                 CFF_MASTER_SID INT,
                 INDICATOR      BIT,
                 PRIMARY KEY (CFF_MASTER_SID, HIERARCHY_NO)
              )

            INSERT INTO #TEMP_PRIOR_HIER_DISC
                        (HIERARCHY_NO,
                         CFF_MASTER_SID,
                         INDICATOR)
            SELECT DISTINCT A.HIERARCHY_NO,
                            A.CFF_MASTER_SID,
                            1
            FROM   #PRIOR_CPP_INFO A
                   JOIN #PRIOR_DISC_INFORMATION B
                     ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                        AND A.CFF_MASTER_SID = B.CFF_MASTER_SID

            ------------------------------------------------------------------------------------
            IF Object_id('TEMPDB..#PRIOR_ACCRUAL_DISCOUNT') IS NOT NULL
              DROP TABLE #PRIOR_ACCRUAL_DISCOUNT;

            WITH CTE
                 AS (SELECT A.CCP_DETAILS_SID,
                            A.COMPANY_MASTER_SID,
                            A.CONTRACT_MASTER_SID,
                            A.ITEM_MASTER_SID,
                            A.PROJECTION_DETAILS_SID,
                            A.CFF_MASTER_SID,
                            A.HIERARCHY_NO,
                            A.LEVEL_NAME,
                            A.PROJECTION_MASTER_SID,
                            A.PARENT_HIERARCHY_NO,
                            p.PERIOD_SID,
                            PERIOD_DATE,
                            NMDP.RS_CONTRACT_SID,
                            RS.DISCOUNT
                     FROM   #PRIOR_TEMP_CCP A
                            JOIN #PRIOR_RS_DATA RS
                              ON RS.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                            JOIN #PERIOD P
                              ON P.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @PROJ_END_PERIOD_SID
                            JOIN NM_DISCOUNT_PROJ_MASTER NMDP
                              ON NMDP.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                 AND CASE
                                       WHEN @DISCOUNT_VIEW = 'PROGRAM'
                                             OR @DISCOUNT_VIEW IS NULL THEN CONVERT(VARCHAR(100), NMDP.RS_CONTRACT_SID)
                                       ELSE PRICE_GROUP_TYPE
                                     END = RS.DISCOUNT)
            SELECT CFF_MASTER_SID,
                   PROJECTION_DETAILS_SID,
                   PERIOD_SID,
                   RC.RS_CONTRACT_SID,
                   DISCOUNT,
                   Sum(DEDUCTION_AMOUNT) / ( Datediff(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE)
                                             + 1 ) DISCOUNT_AMOUNT,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PARENT_HIERARCHY_NO
            INTO   #PRIOR_ACCRUAL_DISCOUNT
            FROM   ACCRUAL_MASTER A1
                   JOIN RS_CONTRACT RC
                     ON RC.RS_MODEL_SID = A1.RS_MODEL_SID
                        AND RC.CONTRACT_MASTER_SID = A1.CONTRACT_MASTER_SID
                   JOIN CTE A2
                     ON A2.PERIOD_DATE BETWEEN CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_START_DATE, 0)))) AND CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_END_DATE, 0))))
                        AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID
                        AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID
                        AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID
                        AND A2.RS_CONTRACT_SID = RC.RS_CONTRACT_SID
                        AND Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_START_DATE, -1)) >= @ACTUAL_START_DATE
                        AND A1.ACCRUAL_PERIOD_END_DATE <= @PROJ_END_PERIOD_DATE
            GROUP  BY CFF_MASTER_SID,
                      PROJECTION_DETAILS_SID,
                      PERIOD_SID,
                      RC.RS_CONTRACT_SID,
                      DISCOUNT,
                      ACCRUAL_PERIOD_START_DATE,
                      ACCRUAL_PERIOD_END_DATE,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO

            ------------------------------------------------------------------- Newly added ends here
            ----------------------------PULLING DISCOUNT OF PROJECTED  LEVEL INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_NM_DISCOUNT_PROJECTION') IS NOT NULL
              DROP TABLE #PRIOR_NM_DISCOUNT_PROJECTION

            SELECT Isnull(Sum(NSP.PROJECTION_SALES), 0) AS PROJECTION_SALES,
                   Sum(AD.DISCOUNT_AMOUNT)              AS ACCRUAL_AMOUNT_PROJECTION,
                   PERIOD,
                   YEAR,
                   PRD.DISCOUNT,
                   PRD.CFF_MASTER_SID,
                   prd.HIERARCHY_NO,
                   prd.LEVEL_NAME,
                   prd.PARENT_HIERARCHY_NO
            INTO   #PRIOR_NM_DISCOUNT_PROJECTION
            FROM   #PRIOR_RS_DATA_PERIOD PRD
                   JOIN NM_DISCOUNT_PROJ_MASTER NMDP
                     ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                        AND CASE
                              WHEN @VAR = 1 THEN CONVERT(VARCHAR(100), NMDP.RS_CONTRACT_SID)
                              ELSE PRICE_GROUP_TYPE
                            END = PRD.DISCOUNT
                   JOIN NM_DISCOUNT_PROJECTION NSP
                     ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                        AND NMDP.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                        AND PRD.PERIOD_SID = NSP.PERIOD_SID
                        AND prd.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
                   LEFT JOIN #ACCRUAL_DISCOUNT AD
                          ON PRD.CFF_MASTER_SID = AD.CFF_MASTER_SID
                             AND NSP.PROJECTION_DETAILS_SID = AD.PROJECTION_DETAILS_SID
                             AND NSP.RS_CONTRACT_SID = AD.RS_CONTRACT_SID
                             AND PRD.DISCOUNT = AD.DISCOUNT
                             AND NSP.PERIOD_SID = AD.PERIOD_SID
                             AND PRD.HIERARCHY_NO = AD.HIERARCHY_NO
                             AND PRD.LEVEL_NAME = AD.LEVEL_NAME
            WHERE  EXISTS (SELECT 1
                           FROM   #PRIOR_DISC_INFORMATION A
                           WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                                  AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                  AND PRD.DISCOUNT = A.DISCOUNT)
            GROUP  BY PERIOD,
                      YEAR,
                      PRD.DISCOUNT,
                      PRD.CFF_MASTER_SID,
                      PRD.HIERARCHY_NO,
                      PRD.LEVEL_NAME,
                      PRD.PARENT_HIERARCHY_NO

            ----------------------------PULLING DISCOUNT OF ACTULS   INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_NM_ACTUAL_DISCOUNT') IS NOT NULL
              DROP TABLE #PRIOR_NM_ACTUAL_DISCOUNT

            SELECT Isnull(Sum(NSP.ACTUAL_SALES), 0) AS ACTUAL_SALES,
                   Sum(AD.DISCOUNT_AMOUNT)          AS ACCRUAL_AMOUNT_ACTUAL,
                   PERIOD,
                   YEAR,
                   PRD.DISCOUNT,
                   PRD.CFF_MASTER_SID,
                   prd.HIERARCHY_NO,
                   prd.LEVEL_NAME,
                   prd.PARENT_HIERARCHY_NO
            INTO   #PRIOR_NM_ACTUAL_DISCOUNT
            FROM   #PRIOR_RS_DATA_PERIOD PRD
                   JOIN NM_DISCOUNT_PROJ_MASTER NMDP
                     ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                        AND CASE
                              WHEN @VAR = 1 THEN CONVERT(VARCHAR(100), NMDP.RS_CONTRACT_SID)
                              ELSE PRICE_GROUP_TYPE
                            END = PRD.DISCOUNT
                   JOIN NM_ACTUAL_DISCOUNT NSP
                     ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                        AND NMDP.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                        AND PRD.PERIOD_SID = NSP.PERIOD_SID
                        AND PRD.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @ACT_END_PERIOD_SID
                   LEFT JOIN #ACCRUAL_DISCOUNT AD
                          ON PRD.CFF_MASTER_SID = AD.CFF_MASTER_SID
                             AND NSP.PROJECTION_DETAILS_SID = AD.PROJECTION_DETAILS_SID
                             AND NSP.RS_CONTRACT_SID = AD.RS_CONTRACT_SID
                             AND PRD.DISCOUNT = AD.DISCOUNT
                             AND NSP.PERIOD_SID = AD.PERIOD_SID
                             AND PRD.HIERARCHY_NO = AD.HIERARCHY_NO
                             AND PRD.LEVEL_NAME = AD.LEVEL_NAME
            WHERE  EXISTS (SELECT 1
                           FROM   #PRIOR_DISC_INFORMATION A
                           WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                                  AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                  AND PRD.DISCOUNT = A.DISCOUNT)
            GROUP  BY PERIOD,
                      YEAR,
                      PRD.DISCOUNT,
                      PRD.CFF_MASTER_SID,
                      prd.HIERARCHY_NO,
                      prd.LEVEL_NAME,
                      prd.PARENT_HIERARCHY_NO

            ----------------------------PULLING   PPA PROJECTIONS VALUES    INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_NM_PPA_PROJECTION') IS NOT NULL
              DROP TABLE #PRIOR_NM_PPA_PROJECTION

            SELECT Isnull(Sum(NSP.PROJECTION_DISCOUNT_DOLLAR), 0) PROJECTION_DISCOUNT_DOLLAR,
                   PERIOD,
                   YEAR,
                   PRD.DISCOUNT,
                   PRD.CFF_MASTER_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PARENT_HIERARCHY_NO
            INTO   #PRIOR_NM_PPA_PROJECTION
            FROM   #PRIOR_RS_DATA_PERIOD PRD
                   JOIN NM_PPA_PROJECTION_MASTER NMDP
                     ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                        AND CASE
                              WHEN @VAR = 1 THEN CONVERT(VARCHAR(100), NMDP.RS_CONTRACT_SID)
                              ELSE 'Price Protection'
                            END = PRD.DISCOUNT
                   JOIN NM_PPA_PROJECTION NSP
                     ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                        AND NMDP.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                        AND PRD.PERIOD_SID = NSP.PERIOD_SID
                        AND PRD.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
            WHERE  EXISTS (SELECT 1
                           FROM   #PRIOR_DISC_INFORMATION A
                           WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                                  AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                  AND PRD.DISCOUNT = A.DISCOUNT)
            GROUP  BY PERIOD,
                      YEAR,
                      PRD.DISCOUNT,
                      PRD.CFF_MASTER_SID,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO

            ----------------------------PULLING   PPA ACTUALS   INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_NM_ACTUAL_PPA') IS NOT NULL
              DROP TABLE #PRIOR_NM_ACTUAL_PPA

            SELECT Isnull(Sum(NSP.ACTUAL_DISCOUNT_DOLLAR), 0) ACTUAL_DISCOUNT_DOLLAR,
                   PERIOD,
                   YEAR,
                   PRD.DISCOUNT,
                   PRD.CFF_MASTER_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PARENT_HIERARCHY_NO
            INTO   #PRIOR_NM_ACTUAL_PPA
            FROM   #PRIOR_RS_DATA_PERIOD PRD
                   JOIN NM_PPA_PROJECTION_MASTER NMDP
                     ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                        AND CASE
                              WHEN @VAR = 1 THEN CONVERT(VARCHAR(100), NMDP.RS_CONTRACT_SID)
                              ELSE 'Price Protection'
                            END = PRD.DISCOUNT
                   JOIN NM_ACTUAL_PPA NSP
                     ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                        AND NMDP.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                        AND PRD.PERIOD_SID = NSP.PERIOD_SID
                        AND PRD.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @ACT_END_PERIOD_SID
            WHERE  EXISTS (SELECT 1
                           FROM   #PRIOR_DISC_INFORMATION A
                           WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                                  AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                  AND PRD.DISCOUNT = A.DISCOUNT)
            GROUP  BY PERIOD,
                      YEAR,
                      PRD.DISCOUNT,
                      PRD.CFF_MASTER_SID,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO

            -----------------------PULLING   SUPLEMTNAL PROJ   INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_M_SUPPLEMENTAL_DISC_PROJ') IS NOT NULL
              DROP TABLE #PRIOR_M_SUPPLEMENTAL_DISC_PROJ

            SELECT Isnull(Sum(NSP.PROJECTION_SALES), 0) PROJECTION_SALES,
                   PERIOD,
                   YEAR,
                   PRD.DISCOUNT,
                   PRD.CFF_MASTER_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PARENT_HIERARCHY_NO
            INTO   #PRIOR_M_SUPPLEMENTAL_DISC_PROJ
            FROM   #PRIOR_RS_DATA_PERIOD PRD
                   JOIN M_SUPPLEMENTAL_DISC_MASTER NMDP
                     ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                   JOIN M_SUPPLEMENTAL_DISC_PROJ NSP
                     ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                        AND PRD.PERIOD_SID = NSP.PERIOD_SID
                        AND PRD.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
            WHERE  EXISTS (SELECT 1
                           FROM   #PRIOR_DISC_INFORMATION A
                           WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                                  AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                  AND PRD.DISCOUNT = A.DISCOUNT)
            GROUP  BY PERIOD,
                      YEAR,
                      PRD.DISCOUNT,
                      PRD.CFF_MASTER_SID,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO

            -----------------------PULLING   SUPLEMTNAL ACTUALS   INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_M_SUPPLEMENTAL_DISC_ACTUALS') IS NOT NULL
              DROP TABLE #PRIOR_M_SUPPLEMENTAL_DISC_ACTUALS

            SELECT Sum(NSP.ACTUAL_SALES) ACTUAL_SALES,
                   PERIOD,
                   YEAR,
                   PRD.DISCOUNT,
                   PRD.CFF_MASTER_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PARENT_HIERARCHY_NO
            INTO   #PRIOR_M_SUPPLEMENTAL_DISC_ACTUALS
            FROM   #PRIOR_RS_DATA_PERIOD PRD
                   JOIN M_SUPPLEMENTAL_DISC_MASTER NMDP
                     ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                   JOIN M_SUPPLEMENTAL_DISC_ACTUALS NSP
                     ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                        AND PRD.PERIOD_SID = NSP.PERIOD_SID
                        AND PRD.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @ACT_END_PERIOD_SID
            WHERE  EXISTS (SELECT 1
                           FROM   #PRIOR_DISC_INFORMATION A
                           WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                                  AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                  AND PRD.DISCOUNT = A.DISCOUNT)
            GROUP  BY PERIOD,
                      YEAR,
                      PRD.DISCOUNT,
                      PRD.CFF_MASTER_SID,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO

            -----------------------PULLING   MANDTED DISCOUNT PROJECTIONS   INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_M_DISCOUNT_PROJECTION') IS NOT NULL
              DROP TABLE #PRIOR_M_DISCOUNT_PROJECTION

            SELECT Sum(NMDP.PROJECTION_SALES) PROJECTION_SALES,
                   PERIOD,
                   YEAR,
                   PRD.DISCOUNT,
                   PRD.CFF_MASTER_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PARENT_HIERARCHY_NO
            INTO   #PRIOR_M_DISCOUNT_PROJECTION
            FROM   #PRIOR_RS_DATA_PERIOD PRD
                   JOIN M_DISCOUNT_PROJECTION NMDP
                     ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                        AND PRD.PERIOD_SID = NMDP.PERIOD_SID
                        AND SAVE_FLAG = 1
                        AND PRD.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
            WHERE  EXISTS (SELECT 1
                           FROM   #PRIOR_DISC_INFORMATION A
                           WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                                  AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                  AND PRD.DISCOUNT = A.DISCOUNT)
            GROUP  BY PERIOD,
                      YEAR,
                      PRD.DISCOUNT,
                      PRD.CFF_MASTER_SID,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO

            -----------------------PULLING   MANDTED DISCOUNT ACTUALS   INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_M_ACTUAL_DISCOUNT') IS NOT NULL
              DROP TABLE #PRIOR_M_ACTUAL_DISCOUNT

            SELECT Sum(NMDP.ACTUAL_SALES) ACTUAL_SALES,
                   PERIOD,
                   YEAR,
                   PRD.DISCOUNT,
                   PRD.CFF_MASTER_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PARENT_HIERARCHY_NO
            INTO   #PRIOR_M_ACTUAL_DISCOUNT
            FROM   #PRIOR_RS_DATA_PERIOD PRD
                   JOIN M_ACTUAL_DISCOUNT NMDP
                     ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                        AND PRD.PERIOD_SID = NMDP.PERIOD_SID
                        AND SAVE_FLAG = 1
                        AND PRD.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @ACT_END_PERIOD_SID
            WHERE  EXISTS (SELECT 1
                           FROM   #PRIOR_DISC_INFORMATION A
                           WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                                  AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                  AND PRD.DISCOUNT = A.DISCOUNT)
            GROUP  BY PERIOD,
                      YEAR,
                      PRD.DISCOUNT,
                      PRD.CFF_MASTER_SID,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO

            -----------------------PULLING  NON  MANDTED SALES PROJECTIONS   INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_NM_SALES_PROJECTION') IS NOT NULL
              DROP TABLE #PRIOR_NM_SALES_PROJECTION

            SELECT Sum(PROJECTION_SALES)                PROJECTION_SALES,
                   Sum(PROJECTION_UNITS)                PROJECTION_UNITS,
                   Sum(PROJECTION_SALES * U.ITEM_PRICE) AS NM_COGS_PROJECTED,
                   PERIOD,
                   YEAR,
                   PRD.DISCOUNT,
                   PRD.CFF_MASTER_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PARENT_HIERARCHY_NO
            INTO   #PRIOR_NM_SALES_PROJECTION
            FROM   #PRIOR_RS_DATA_PERIOD PRD
                   JOIN NM_SALES_PROJECTION_MASTER NMDP
                     ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                   JOIN NM_SALES_PROJECTION NSP
                     ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                        AND PRD.PERIOD_SID = NSP.PERIOD_SID
                        AND PRD.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
                   JOIN PROJECTION_DETAILS PD
                     ON PD.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                   JOIN CCP_DETAILS CD
                     ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                   LEFT JOIN #ITEM_PRICING U
                          ON CD.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                             AND PRD.PERIOD_SID = U.PERIOD_SID
            WHERE  EXISTS (SELECT 1
                           FROM   #PRIOR_SALES_INFORMATION A
                           WHERE  A.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                                  AND A.CFF_MASTER_SID = PRD.CFF_MASTER_SID)
                   AND EXISTS (SELECT 1
                               FROM   #PRIOR_DISC_INFORMATION A
                               WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                                      AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                      AND PRD.DISCOUNT = A.DISCOUNT)
            GROUP  BY PERIOD,
                      YEAR,
                      PRD.DISCOUNT,
                      PRD.CFF_MASTER_SID,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO

            -----------------------PULLING  NON  MANDTED SALES ACTUALS   INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_NM_ACTUAL_SALES') IS NOT NULL
              DROP TABLE #PRIOR_NM_ACTUAL_SALES

            SELECT Sum(ACTUAL_SALES)                ACTUAL_SALES,
                   Sum(ACTUAL_UNITS)                ACTUAL_UNITS,
                   Sum(ACTUAL_SALES * U.ITEM_PRICE) AS NM_COGS_ACTUAL,
                   PERIOD,
                   YEAR,
                   PRD.DISCOUNT,
                   PRD.CFF_MASTER_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PARENT_HIERARCHY_NO
            INTO   #PRIOR_NM_ACTUAL_SALES
            FROM   #PRIOR_RS_DATA_PERIOD PRD
                   JOIN NM_SALES_PROJECTION_MASTER NMDP
                     ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                   JOIN NM_ACTUAL_SALES NSP
                     ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                        AND PRD.PERIOD_SID = NSP.PERIOD_SID
                        AND PRD.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @ACT_END_PERIOD_SID
                   JOIN PROJECTION_DETAILS PD
                     ON PD.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                   JOIN CCP_DETAILS CD
                     ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                   LEFT JOIN #ITEM_PRICING U
                          ON CD.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                             AND PRD.PERIOD_SID = U.PERIOD_SID
            WHERE  EXISTS (SELECT 1
                           FROM   #PRIOR_SALES_INFORMATION A
                           WHERE  A.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                                  AND A.CFF_MASTER_SID = PRD.CFF_MASTER_SID)
                   AND EXISTS (SELECT 1
                               FROM   #PRIOR_DISC_INFORMATION A
                               WHERE  A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                                      AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                      AND PRD.DISCOUNT = A.DISCOUNT)
            GROUP  BY PERIOD,
                      YEAR,
                      PRD.DISCOUNT,
                      PRD.CFF_MASTER_SID,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO

            --------------------------------------------------------- Returns---GALUAT-543--------------------------------
            --SELECT YEAR,
            --       PERIOD,
            --       AVG(TR.ACTUAL_RATE)    ACTUAL_RATE,
            --       AVG(TR.PROJECTED_RATE) PROJECTED_RATE,
            --       CFF_MASTER_SID,
            --       HIERARCHY_NO,
            --       LEVEL_NAME
            --INTO   #PRIOR_RETURNS
            --FROM   #TEMP_RETURNS TR
            --JOIN   CCP_DETAILS CC ON TR.ITEM_MASTER_SID = CC.ITEM_MASTER_SID
            --JOIN   #PRIOR_CPP_INFO C ON C.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
            --JOIN   #PERIOD P ON P.PERIOD_SID = TR.PERIOD_SID
            --GROUP  BY YEAR,
            --          PERIOD,
            --          CFF_MASTER_SID,
            --          HIERARCHY_NO,
            ----          LEVEL_NAME  
            --IF Object_id('TEMPDB..#PRIOR_RETURNS') IS NOT NULL
            --  DROP TABLE #PRIOR_RETURNS
            --SELECT YEAR,
            --       PERIOD,
            --       Isnull(Sum(( Isnull(NSP.projection_sales, 0)
            --                    + Isnull(MSP.projection_sales, 0) ) * TR.PROJECTED_RATE), 0) AS Returns_Projected,
            --       Isnull(Sum(( Isnull(NAS.actual_sales, 0)
            --                    + Isnull(MAS.actual_sales, 0) ) * TR.ACTUAL_RATE), 0)        AS Returns_actuals,
            --       CFF_MASTER_SID,
            --       HIERARCHY_NO,
            --       LEVEL_NAME,
            --       RS_NAME
            --INTO   #PRIOR_RETURNS
            --FROM   #TEMP_RETURNS TR
            --       JOIN CCP_DETAILS CC
            --         ON TR.ITEM_MASTER_SID = CC.ITEM_MASTER_SID
            --       JOIN #PRIOR_CPP_INFO C
            --         ON C.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
            --       JOIN #PERIOD P
            --         ON P.PERIOD_SID = TR.PERIOD_SID
            --       LEFT OUTER JOIN NM_SALES_PROJECTION NSP
            --                    ON NSP.PROJECTION_DETAILS_SID = C.PROJECTION_DETAILS_SID
            --                       AND p.PERIOD_SID = NSP.PERIOD_SID
            --                       AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
            --       LEFT OUTER JOIN M_SALES_PROJECTION MSP
            --                    ON MSP.PROJECTION_DETAILS_SID = c.PROJECTION_DETAILS_SID
            --                       AND p.PERIOD_SID = MSP.PERIOD_SID
            --                       AND MSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
            --       LEFT OUTER JOIN NM_ACTUAL_SALES NAS
            --                    ON NAS.PROJECTION_DETAILS_SID = c.PROJECTION_DETAILS_SID
            --                       AND p.PERIOD_SID = NAS.PERIOD_SID
            --                       AND NAS.PERIOD_SID BETWEEN @ACT_START_PERIOD_SID AND @ACT_END_PERIOD_SID
            --       LEFT OUTER JOIN M_ACTUAL_SALES MAS
            --                    ON MAS.PROJECTION_DETAILS_SID = c.PROJECTION_DETAILS_SID
            --                       AND p.PERIOD_SID = MAS.PERIOD_SID
            --                       AND MAS.PERIOD_SID BETWEEN @ACT_START_PERIOD_SID AND @ACT_END_PERIOD_SID
            --GROUP  BY YEAR,
            --          PERIOD,
            --          CFF_MASTER_SID,
            --          HIERARCHY_NO,
            --          LEVEL_NAME,
            --          RS_NAME
            -----------------------PULLING   MANDTED SALES PROJETIONS   INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_M_SALES_PROJECTION') IS NOT NULL
              DROP TABLE #PRIOR_M_SALES_PROJECTION

            SELECT Sum(PROJECTION_SALES)                PROJECTION_SALES,
                   Sum(PROJECTION_UNITS)                PROJECTION_UNITS,
                   Sum(PROJECTION_SALES * U.ITEM_PRICE) AS M_COGS_PROJECTED,
                   PERIOD,
                   YEAR,
                   PRD.DISCOUNT,
                   PRD.CFF_MASTER_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PARENT_HIERARCHY_NO
            INTO   #PRIOR_M_SALES_PROJECTION
            FROM   #PRIOR_RS_DATA_PERIOD PRD
                   JOIN M_SALES_PROJECTION_MASTER NMDP
                     ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                   JOIN M_SALES_PROJECTION NSP
                     ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                        AND PRD.PERIOD_SID = NSP.PERIOD_SID
                        AND PRD.PERIOD_SID BETWEEN COALESCE(@PIVOT_START_PERIOD_SID, @ACT_START_PERIOD_SID) AND @ACT_END_PERIOD_SID
                   JOIN #PRIOR_DISC_INFORMATION A
                     ON A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                        AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                        AND PRD.DISCOUNT = A.DISCOUNT
                   JOIN CCP_DETAILS CD
                     ON CD.CCP_DETAILS_SID = a.CCP_DETAILS_SID
                   LEFT JOIN #ITEM_PRICING U
                          ON CD.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                             AND PRD.PERIOD_SID = U.PERIOD_SID
            GROUP  BY PERIOD,
                      YEAR,
                      PRD.DISCOUNT,
                      PRD.CFF_MASTER_SID,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO

            -----------------------PULLING    MANDTED SALES ACTUALS   INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_M_ACTUAL_SALES') IS NOT NULL
              DROP TABLE #PRIOR_M_ACTUAL_SALES

            SELECT Sum(ACTUAL_SALES)                ACTUAL_SALES,
                   Sum(ACTUAL_UNITS)                ACTUAL_UNITS,
                   Sum(ACTUAL_SALES * U.ITEM_PRICE) AS M_COGS_ACTUAL,
                   PERIOD,
                   YEAR,
                   PRD.DISCOUNT,
                   PRD.CFF_MASTER_SID,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PARENT_HIERARCHY_NO
            INTO   #PRIOR_M_ACTUAL_SALES
            FROM   #PRIOR_RS_DATA_PERIOD PRD
                   JOIN M_SALES_PROJECTION_MASTER NMDP
                     ON NMDP.PROJECTION_DETAILS_SID = PRD.PROJECTION_DETAILS_SID
                   JOIN M_ACTUAL_SALES NSP
                     ON NMDP.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                        AND PRD.PERIOD_SID = NSP.PERIOD_SID
                        AND PRD.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
                   JOIN #PRIOR_DISC_INFORMATION A
                     ON A.CFF_MASTER_SID = PRD.CFF_MASTER_SID
                        AND PRD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                        AND PRD.DISCOUNT = A.DISCOUNT
                   JOIN CCP_DETAILS CD
                     ON CD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                   LEFT JOIN #ITEM_PRICING U
                          ON CD.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                             AND PRD.PERIOD_SID = U.PERIOD_SID
            GROUP  BY PERIOD,
                      YEAR,
                      PRD.DISCOUNT,
                      PRD.CFF_MASTER_SID,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PARENT_HIERARCHY_NO

            -----------------------AGGREGATING     MANDATED&NON MANDATED SALES OF BOTH ACTUALS AND PROJECTIONS INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_SALES') IS NOT NULL
              DROP TABLE #PRIOR_SALES

            SELECT FD.YEAR,
                   FD.PERIOD,
                   FD.CFF_MASTER_SID,
                   FD.DISCOUNT,
                   FD.HIERARCHY_NO,
                   FD.LEVEL_NAME,
                   FD.PARENT_HIERARCHY_NO,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE((NSP.PROJECTION_SALES), 0)
                          + COALESCE((MSP.PROJECTION_SALES), 0)
                   END AS PROJECTION_SALES,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE((NSP.PROJECTION_UNITS), 0)
                          + COALESCE((MSP.PROJECTION_UNITS), 0)
                   END AS PROJECTION_UNITS,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE((NAS.ACTUAL_SALES), 0)
                          + COALESCE((MAS.ACTUAL_SALES), 0)
                   END AS ACTUAL_SALES,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE((NAS.ACTUAL_UNITS), 0)
                          + COALESCE((MAS.ACTUAL_UNITS), 0)
                   END AS ACTUAL_UNITS,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE((MSP.M_COGS_PROJECTED), 0)
                          + COALESCE((NSP.NM_COGS_PROJECTED), 0)
                   END AS COGS_PROJECTED,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE((MAS.M_COGS_ACTUAL), 0)
                          + COALESCE((NAS.NM_COGS_ACTUAL), 0)
                   END AS COGS_ACTUAL,
                   --RETURNS_ACTUALS = CASE
                   --                    WHEN TD.INDICATOR IS NULL THEN NULL
                   --                    ELSE RETURNS_ACTUALS
                   --                  END,
                   --RETURNS_PROJECTED = CASE
                   --                      WHEN TD.INDICATOR IS NULL THEN NULL
                   --                      ELSE RETURNS_PROJECTED
                   --                    END,
                   --ACTUAL_RATE = ( CASE
                   --                  WHEN TS.INDICATOR IS NULL
                   --                        OR TD.INDICATOR IS NULL THEN NULL
                   --                  ELSE ISNULL(( RETURNS_ACTUALS / NULLIF(COALESCE((NAS.ACTUAL_SALES), 0)
                   --                                                         + COALESCE((MAS.ACTUAL_SALES), 0), 0) ), 0)
                   --                END ),
                   --PROJECTED_RATE = ( CASE
                   --                     WHEN TS.INDICATOR IS NULL
                   --                           OR TD.INDICATOR IS NULL THEN NULL
                   --                     ELSE ISNULL(( RETURNS_PROJECTED / NULLIF(COALESCE((NSP.PROJECTION_SALES), 0)
                   --                                                              + COALESCE((MSP.PROJECTION_SALES), 0), 0) ), 0)
                   --                   END ),
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(( NAS.ACTUAL_SALES ), 0)
                   END AS NM_ACTUAL_SALES,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(( NSP.PROJECTION_SALES ), 0)
                   END AS NM_PROJECTED_SALES,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(( NAS.ACTUAL_UNITS ), 0)
                   END AS NM_ACTUAL_UNITS,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(( NSP.PROJECTION_UNITS ), 0)
                   END AS NM_PROJECTED_UNITS,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(( MAS.ACTUAL_SALES ), 0)
                   END AS M_ACTUAL_SALES,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(( MSP.PROJECTION_SALES ), 0)
                   END AS M_PROJECTED_SALES,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(( MAS.ACTUAL_UNITS ), 0)
                   END AS M_ACTUAL_UNITS,
                   CASE
                     WHEN TS.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(( MSP.PROJECTION_UNITS ), 0)
                   END AS M_PROJECTED_UNITS
            INTO   #PRIOR_SALES
            FROM   (SELECT DISTINCT CFF_MASTER_SID,
                                    YEAR,
                                    PERIOD,
                                    DISCOUNT,
                                    LEVEL_NAME,
                                    HIERARCHY_NO,
                                    PARENT_HIERARCHY_NO
                    FROM   #PRIOR_RS_DATA_PERIOD) FD
                   LEFT JOIN #PRIOR_NM_SALES_PROJECTION NSP
                          ON NSP.YEAR = FD.YEAR
                             AND NSP.PERIOD = FD.PERIOD
                             AND NSP.CFF_MASTER_SID = FD.CFF_MASTER_SID
                             AND NSP.DISCOUNT = FD.DISCOUNT
                             AND NSP.HIERARCHY_NO = FD.HIERARCHY_NO
                             AND NSP.LEVEL_NAME = FD.LEVEL_NAME
                             AND COALESCE(NSP.PARENT_HIERARCHY_NO, NSP.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
                   LEFT JOIN #PRIOR_M_SALES_PROJECTION MSP
                          ON MSP.YEAR = FD.YEAR
                             AND MSP.PERIOD = FD.PERIOD
                             AND MSP.CFF_MASTER_SID = FD.CFF_MASTER_SID
                             AND MSP.DISCOUNT = FD.DISCOUNT
                             AND MSP.HIERARCHY_NO = FD.HIERARCHY_NO
                             AND MSP.LEVEL_NAME = FD.LEVEL_NAME
                             AND COALESCE(MSP.PARENT_HIERARCHY_NO, MSP.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
                   LEFT JOIN #PRIOR_NM_ACTUAL_SALES NAS
                          ON NAS.YEAR = FD.YEAR
                             AND NAS.PERIOD = FD.PERIOD
                             AND NAS.CFF_MASTER_SID = FD.CFF_MASTER_SID
                             AND NAS.DISCOUNT = FD.DISCOUNT
                             AND NAS.HIERARCHY_NO = FD.HIERARCHY_NO
                             AND NAS.LEVEL_NAME = FD.LEVEL_NAME
                             AND COALESCE(NAS.PARENT_HIERARCHY_NO, NAS.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
                   LEFT JOIN #PRIOR_M_ACTUAL_SALES MAS
                          ON MAS.YEAR = FD.YEAR
                             AND MAS.PERIOD = FD.PERIOD
                             AND MAS.CFF_MASTER_SID = FD.CFF_MASTER_SID
                             AND MAS.DISCOUNT = FD.DISCOUNT
                             AND MAS.HIERARCHY_NO = FD.HIERARCHY_NO
                             AND MAS.LEVEL_NAME = FD.LEVEL_NAME
                             AND COALESCE(MAS.PARENT_HIERARCHY_NO, MAS.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
                   --LEFT JOIN #PRIOR_RETURNS TR
                   --       ON TR.YEAR = FD.YEAR
                   --          AND TR.PERIOD = FD.PERIOD
                   --          AND TR.CFF_MASTER_SID = FD.CFF_MASTER_SID
                   --          AND TR.HIERARCHY_NO = FD.HIERARCHY_NO
                   --          AND TR.LEVEL_NAME = FD.LEVEL_NAME
                   LEFT JOIN #TEMP_PRIOR_HIER_SALES TS
                          ON TS.CFF_MASTER_SID = FD.CFF_MASTER_SID
                             AND TS.HIERARCHY_NO = FD.HIERARCHY_NO
                   LEFT OUTER JOIN #TEMP_PRIOR_HIER_DISC TD
                                ON TD.HIERARCHY_NO = FD.HIERARCHY_NO
                                   AND FD.CFF_MASTER_SID = TD.CFF_MASTER_SID

            -----------------------AGGREGATING     NON MANDATED DISCOUNT OF BOTH ACTUALS AND PROJECTIONS INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_DISCOUNT') IS NOT NULL
              DROP TABLE #PRIOR_DISCOUNT

            SELECT FD.YEAR,
                   FD.PERIOD,
                   COALESCE(( NSP.PROJECTION_SALES ), 0) AS PROJECTION_SALES,
                   COALESCE(( NAD.ACTUAL_SALES ), 0)     AS ACTUAL_SALES,
                   FD.CFF_MASTER_SID,
                   FD.DISCOUNT                           AS RS_NAME,
                   FD.LEVEL_NAME,
                   FD.HIERARCHY_NO,
                   FD.PARENT_HIERARCHY_NO,
                   ACCRUAL_AMOUNT_PROJECTION,
                   ACCRUAL_AMOUNT_ACTUAL
            INTO   #PRIOR_DISCOUNT
            FROM   (SELECT DISTINCT CFF_MASTER_SID,
                                    YEAR,
                                    PERIOD,
                                    DISCOUNT,
                                    NM_PPA_IND,
                                    LEVEL_NAME,
                                    HIERARCHY_NO,
                                    PARENT_HIERARCHY_NO
                    FROM   #PRIOR_RS_DATA_PERIOD) FD
                   LEFT JOIN #PRIOR_NM_DISCOUNT_PROJECTION NSP
                          ON NSP.YEAR = FD.YEAR
                             AND NSP.PERIOD = FD.PERIOD
                             AND NSP.DISCOUNT = FD.DISCOUNT
                             AND FD.CFF_MASTER_SID = NSP.CFF_MASTER_SID
                             AND NSP.HIERARCHY_NO = FD.HIERARCHY_NO
                             AND NSP.LEVEL_NAME = FD.LEVEL_NAME
                             AND COALESCE(NSP.PARENT_HIERARCHY_NO, NSP.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
                   LEFT JOIN #PRIOR_NM_ACTUAL_DISCOUNT NAD
                          ON NAD.YEAR = FD.YEAR
                             AND NAD.PERIOD = FD.PERIOD
                             AND FD.CFF_MASTER_SID = NAD.CFF_MASTER_SID
                             AND NAD.DISCOUNT = FD.DISCOUNT
                             AND NAD.HIERARCHY_NO = FD.HIERARCHY_NO
                             AND NAD.LEVEL_NAME = FD.LEVEL_NAME
                             AND COALESCE(NAD.PARENT_HIERARCHY_NO, NAD.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
            WHERE  EXISTS (SELECT 1
                           FROM   #PRIOR_DISC_INFORMATION C
                           WHERE  C.CFF_MASTER_SID = FD.CFF_MASTER_SID
                                  AND C.DISCOUNT = FD.DISCOUNT)
                   AND FD.NM_PPA_IND = 'NM'

            -----------------------AGGREGATING    PPA AND SUPPLEMENTAL OF BOTH ACTUALS AND PROJECTIONS INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_PPA') IS NOT NULL
              DROP TABLE #PRIOR_PPA

            SELECT FD.YEAR,
                   FD.PERIOD,
                   FD.DISCOUNT,
                   FD.CFF_MASTER_SID,
                   COALESCE(( NSP.PROJECTION_DISCOUNT_DOLLAR ), 0) AS PPA_DISCOUNT_PROJECTED,
                   COALESCE(( NAS.ACTUAL_DISCOUNT_DOLLAR ), 0)     AS PPA_ACTUAL_SALES,
                   FD.LEVEL_NAME,
                   FD.HIERARCHY_NO,
                   FD.PARENT_HIERARCHY_NO
            INTO   #PRIOR_PPA
            FROM   (SELECT DISTINCT CFF_MASTER_SID,
                                    YEAR,
                                    PERIOD,
                                    DISCOUNT,
                                    NM_PPA_IND,
                                    LEVEL_NAME,
                                    HIERARCHY_NO,
                                    PARENT_HIERARCHY_NO
                    FROM   #PRIOR_RS_DATA_PERIOD) FD
                   LEFT JOIN #PRIOR_NM_PPA_PROJECTION NSP
                          ON NSP.YEAR = FD.YEAR
                             AND NSP.PERIOD = FD.PERIOD
                             AND NSP.CFF_MASTER_SID = FD.CFF_MASTER_SID
                             AND NSP.DISCOUNT = FD.DISCOUNT
                             AND NSP.HIERARCHY_NO = FD.HIERARCHY_NO
                             AND NSP.LEVEL_NAME = FD.LEVEL_NAME
                             AND COALESCE(NSP.PARENT_HIERARCHY_NO, NSP.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
                   LEFT JOIN #PRIOR_NM_ACTUAL_PPA NAS
                          ON NAS.YEAR = FD.YEAR
                             AND NAS.PERIOD = FD.PERIOD
                             AND NAS.CFF_MASTER_SID = FD.CFF_MASTER_SID
                             AND NAS.DISCOUNT = FD.DISCOUNT
                             AND NAS.HIERARCHY_NO = FD.HIERARCHY_NO
                             AND NAS.LEVEL_NAME = FD.LEVEL_NAME
                             AND COALESCE(NAS.PARENT_HIERARCHY_NO, NAS.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
            WHERE  FD.NM_PPA_IND = 'PPA'

            -----------------------AGGREGATING     madated SUPPLEMENTAL OF BOTH ACTUALS AND PROJECTIONS INFORMATION OTHER THAN FIRST CFF------------------------
            IF Object_id('TEMPDB..#PRIOR_PPA_SUPP_MANDATED') IS NOT NULL
              DROP TABLE #PRIOR_PPA_SUPP_MANDATED

            SELECT FD.YEAR,
                   FD.PERIOD,
                   CASE
                     WHEN TD.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(( SDP.PROJECTION_SALES ), 0)
                   END AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                   CASE
                     WHEN TD.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(( MDP.PROJECTION_SALES ), 0)
                   END AS MANDATED_DISCOUNT_PROJECTED,
                   CASE
                     WHEN TD.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(( SDA.ACTUAL_SALES ), 0)
                   END AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                   CASE
                     WHEN TD.INDICATOR IS NULL THEN NULL
                     ELSE COALESCE(( MAD.ACTUAL_SALES ), 0)
                   END AS MANDATED_DISCOUNT_ACTUALS,
                   FD.CFF_MASTER_SID,
                   FD.HIERARCHY_NO,
                   FD.LEVEL_NAME,
                   FD.DISCOUNT,
                   FD.PARENT_HIERARCHY_NO
            INTO   #PRIOR_PPA_SUPP_MANDATED
            FROM   (SELECT DISTINCT CFF_MASTER_SID,
                                    YEAR,
                                    PERIOD,
                                    DISCOUNT,
                                    LEVEL_NAME,
                                    HIERARCHY_NO,
                                    PARENT_HIERARCHY_NO
                    FROM   #PRIOR_RS_DATA_PERIOD) FD
                   LEFT JOIN #PRIOR_M_SUPPLEMENTAL_DISC_PROJ SDP
                          ON SDP.YEAR = FD.YEAR
                             AND SDP.PERIOD = FD.PERIOD
                             AND SDP.CFF_MASTER_SID = FD.CFF_MASTER_SID
                             AND SDP.DISCOUNT = FD.DISCOUNT
                             AND SDP.HIERARCHY_NO = fd.HIERARCHY_NO
                             AND SDP.LEVEL_NAME = fd.LEVEL_NAME
                             AND COALESCE(SDP.PARENT_HIERARCHY_NO, SDP.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
                   LEFT JOIN #PRIOR_M_SUPPLEMENTAL_DISC_ACTUALS SDA
                          ON SDA.YEAR = FD.YEAR
                             AND SDA.PERIOD = FD.PERIOD
                             AND SDA.CFF_MASTER_SID = FD.CFF_MASTER_SID
                             AND SDA.DISCOUNT = FD.DISCOUNT
                             AND SDA.HIERARCHY_NO = fd.HIERARCHY_NO
                             AND SDA.LEVEL_NAME = fd.LEVEL_NAME
                             AND COALESCE(SDA.PARENT_HIERARCHY_NO, SDA.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
                   LEFT JOIN #PRIOR_M_DISCOUNT_PROJECTION MDP
                          ON MDP.YEAR = FD.YEAR
                             AND MDP.PERIOD = FD.PERIOD
                             AND MDP.CFF_MASTER_SID = FD.CFF_MASTER_SID
                             AND MDP.DISCOUNT = FD.DISCOUNT
                             AND MDP.HIERARCHY_NO = fd.HIERARCHY_NO
                             AND MDP.LEVEL_NAME = fd.LEVEL_NAME
                             AND COALESCE(MDP.PARENT_HIERARCHY_NO, MDP.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
                   LEFT JOIN #PRIOR_M_ACTUAL_DISCOUNT MAD
                          ON MAD.YEAR = FD.YEAR
                             AND MAD.PERIOD = FD.PERIOD
                             AND MAD.CFF_MASTER_SID = FD.CFF_MASTER_SID
                             AND MAD.DISCOUNT = FD.DISCOUNT
                             AND MAD.HIERARCHY_NO = fd.HIERARCHY_NO
                             AND MAD.LEVEL_NAME = fd.LEVEL_NAME
                             AND COALESCE(MAD.PARENT_HIERARCHY_NO, MAD.HIERARCHY_NO) = COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO)
                   LEFT JOIN #TEMP_PRIOR_HIER_DISC TD
                          ON TD.HIERARCHY_NO = FD.HIERARCHY_NO
                             AND FD.CFF_MASTER_SID = TD.CFF_MASTER_SID;

            /*
            	IF Object_id('TEMPDB..#PRIOR_PRODUCT_FILE_DATA') IS NOT NULL
            	DROP TABLE #PRIOR_PRODUCT_FILE_DATA
            
            
            SELECT P.YEAR
            	,P.PERIOD
            	,SUM(EX_FACTORY_SALES_ACTUALS) AS EX_FACTORY_SALES_ACTUALS
            	,SUM(EX_FACTORY_SALES_PROJ) AS EX_FACTORY_SALES_PROJ
            	,SUM(DEMAND_SALES_ACTUALS) AS DEMAND_SALES_ACTUALS
            	,SUM(DEMAND_SALES_PROJ) AS DEMAND_SALES_PROJ
            	,SUM(ADJUSTED_DEMAND_ACTUALS) AS ADJUSTED_DEMAND_ACTUALS
            	,SUM(ADJUSTED_DEMAND_PROJ) AS ADJUSTED_DEMAND_PROJ
            	,SUM(IW_MASTER_ACTUALS) AS IW_MASTER_ACTUALS
            	,SUM(IW_MASTER_PROJ) AS IW_MASTER_PROJ
            	,CF.CFF_MASTER_SID AS CFF_MASTER_SID
            	,B.DISCOUNT
            INTO #PRIOR_PRODUCT_FILE_DATA
            FROM CFF_PRODUCT_LEVEL_FILES_DATA CF
            JOIN (
            	SELECT DISTINCT A.CFF_MASTER_SID
            		,ITEM_MASTER_SID
            		,DISCOUNT
            	FROM #PRIOR_TEMP_CCP A
            	JOIN #CURRENT_RS_DATA_PERIOD B ON A.CFF_MASTER_SID = B.CFF_MASTER_SID
            		AND A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
            		AND A.HIERARCHY_NO = B.HIERARCHY_NO
            		AND A.LEVEL_NAME = B.LEVEL_NAME
            		AND COALESCE(A.PARENT_HIERARCHY_NO, A.HIERARCHY_NO) = COALESCE(B.PARENT_HIERARCHY_NO, B.HIERARCHY_NO)
            	) B ON CF.CFF_MASTER_SID = B.CFF_MASTER_SID
            	AND CF.ITEM_MASTER_SID = B.ITEM_MASTER_SID
            INNER JOIN #PERIOD P ON P.PERIOD_SID = CF.PERIOD_SID
            GROUP BY P.YEAR
            	,P.PERIOD
            	,CF.CFF_MASTER_SID
            	,B.DISCOUNT
            */
			/*
            IF Object_id('TEMPDB..#PRIOR_PRODUCT_FILE_DATA') IS NOT NULL
              DROP TABLE #PRIOR_PRODUCT_FILE_DATA

            SELECT P.YEAR,
                   P.PERIOD,
                   Sum(EX_FACTORY_SALES_ACTUALS) AS EX_FACTORY_SALES_ACTUALS,
                   Sum(EX_FACTORY_SALES_PROJ)    AS EX_FACTORY_SALES_PROJ,
                   Sum(DEMAND_SALES_ACTUALS)     AS DEMAND_SALES_ACTUALS,
                   Sum(DEMAND_SALES_PROJ)        AS DEMAND_SALES_PROJ,
                   Sum(ADJUSTED_DEMAND_ACTUALS)  AS ADJUSTED_DEMAND_ACTUALS,
                   Sum(ADJUSTED_DEMAND_PROJ)     AS ADJUSTED_DEMAND_PROJ,
                   Sum(IW_MASTER_ACTUALS)        AS IW_MASTER_ACTUALS,
                   Sum(IW_MASTER_PROJ)           AS IW_MASTER_PROJ,
                   CF.CFF_MASTER_SID             AS CFF_MASTER_SID,
                   B.HIERARCHY_NO,
                   B.LEVEL_NAME,
                   B.PARENT_HIERARCHY_NO
            INTO   #PRIOR_PRODUCT_FILE_DATA
            FROM   CFF_PRODUCT_LEVEL_FILES_DATA CF
                   JOIN (SELECT DISTINCT CFF_MASTER_SID,
                                         ITEM_MASTER_SID,
                                         HIERARCHY_NO,
                                         LEVEL_NAME,
                                         PARENT_HIERARCHY_NO
                         FROM   #PRIOR_TEMP_CCP) B
                     ON CF.CFF_MASTER_SID = B.CFF_MASTER_SID
                        AND CF.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID = CF.PERIOD_SID
            GROUP  BY P.YEAR,
                      P.PERIOD,
                      CF.CFF_MASTER_SID,
                      B.HIERARCHY_NO,
                      B.LEVEL_NAME,
                      B.PARENT_HIERARCHY_NO
					  */-------------------------------------CEL-462(2)
            IF Object_id('TEMPDB..#PRIOR_PRODUCT_FILE_DATA') IS NOT NULL
              DROP TABLE #PRIOR_PRODUCT_FILE_DATA

            SELECT P.YEAR,
                   P.PERIOD,
                   Sum(EX_FACTORY_SALES_ACTUALS) AS EX_FACTORY_SALES_ACTUALS,
                   Sum(EX_FACTORY_SALES_PROJ)    AS EX_FACTORY_SALES_PROJ,
                   Sum(DEMAND_SALES_ACTUALS)     AS DEMAND_SALES_ACTUALS,
                   Sum(DEMAND_SALES_PROJ)        AS DEMAND_SALES_PROJ,
                   Sum(ADJUSTED_DEMAND_ACTUALS)  AS ADJUSTED_DEMAND_ACTUALS,
                   Sum(ADJUSTED_DEMAND_PROJ)     AS ADJUSTED_DEMAND_PROJ,
                   Sum(IW_MASTER_ACTUALS)        AS IW_MASTER_ACTUALS,
                   Sum(IW_MASTER_PROJ)           AS IW_MASTER_PROJ,
                   CF.CFF_MASTER_SID             AS CFF_MASTER_SID,
                   B.HIERARCHY_NO,
                   B.LEVEL_NAME,
                   B.PARENT_HIERARCHY_NO
				   ,B.DISCOUNT
            INTO   #PRIOR_PRODUCT_FILE_DATA
            FROM   CFF_PRODUCT_LEVEL_FILES_DATA CF
                   JOIN (SELECT DISTINCT A.CFF_MASTER_SID,
                                         A.ITEM_MASTER_SID,
                                         A.HIERARCHY_NO,
                                         A.LEVEL_NAME,
                                         A.PARENT_HIERARCHY_NO
										 ,B.DISCOUNT
            	FROM #PRIOR_TEMP_CCP A
            	JOIN #CURRENT_RS_DATA_PERIOD B ON A.CFF_MASTER_SID = B.CFF_MASTER_SID
            		AND A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
            		AND A.HIERARCHY_NO = B.HIERARCHY_NO
            		AND A.LEVEL_NAME = B.LEVEL_NAME
            		AND COALESCE(A.PARENT_HIERARCHY_NO, A.HIERARCHY_NO) = COALESCE(B.PARENT_HIERARCHY_NO, B.HIERARCHY_NO) ) B
                     ON CF.CFF_MASTER_SID = B.CFF_MASTER_SID
                        AND CF.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID = CF.PERIOD_SID
            GROUP  BY P.YEAR,
                      P.PERIOD,
                      CF.CFF_MASTER_SID,
                      B.HIERARCHY_NO,
                      B.LEVEL_NAME,
                      B.PARENT_HIERARCHY_NO,B.DISCOUNT
            ------------based on forecasting type it will insert into result-------------------------------------------------
            IF EXISTS (SELECT 1
                       FROM   #PRIOR_CPP_INFO
                       WHERE  FORECASTING_TYPE = 'NON MANDATED')
              BEGIN
                  IF @VAR = 1
                    BEGIN
                        INSERT INTO #RESULT
                                    (CFF_PROJECTION_MASTER_SID,
                                     YEAR,
                                     PERIOD,
                                     RS_NAME,
                                     PARENT_HIERARCHY_NO,
                                     TOTAL_DISCOUNT_PROJECTED,
                                     TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                     TOTAL_PROJECTED_RPU,
                                     HIERARCHY_NO,
                                     LEVEL_NAME,
                                     DISCOUNT_OF_EX_FACTORY_ACTUALS_PERCENT,
                                     DISCOUNT_OF_EX_FACTORY_PROJECTED_PERCENT,
                                     TOTAL_DISCOUNT_ACCRUAL)
                        --SELECT RD.CFF_MASTER_SID,
                        --       YEAR,
                        --       PERIOD,
                        --       RD.RS_NAME,
                        --       Isnull(RETURNS_PROJECTED, 0),
                        --       Isnull(PROJECTED_RATE * 100, 0),
                        --       Isnull(CASE
                        --                WHEN TPS.INDICATOR IS NULL
                        --                      OR TPD.INDICATOR IS NULL THEN NULL
                        --                ELSE COALESCE(Isnull(RETURNS_PROJECTED, 0) / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
                        --              END, 0),
                        --       RD.HIERARCHY_NO,
                        --       LEVEL_NAME
                        --FROM   #PRIOR_SALES RD
                        --       LEFT JOIN #TEMP_PRIOR_HIER_SALES TPS
                        --              ON TPS.HIERARCHY_NO = RD.HIERARCHY_NO
                        --                 AND TPS.CFF_MASTER_SID = RD.CFF_MASTER_SID
                        --       LEFT JOIN #TEMP_PRIOR_HIER_DISC TPD
                        --              ON TPD.HIERARCHY_NO = RD.HIERARCHY_NO
                        --                 AND TPD.CFF_MASTER_SID = RD.CFF_MASTER_SID
                        --UNION ALL
                        SELECT ND.CFF_MASTER_SID,
                               ND.YEAR,
                               ND.PERIOD,
                               RC.RS_NAME                                                 AS RS_NAME,
                               ND.PARENT_HIERARCHY_NO,
                               Isnull(ND.PROJECTION_SALES, 0),
                               Isnull(CASE
                                        WHEN TPS.INDICATOR IS NULL THEN NULL
                                        ELSE COALESCE(Isnull(ND.PROJECTION_SALES, 0) / NULLIF(RD.NM_PROJECTED_SALES, 0), 0) * 100
                                      END, 0),
                               Isnull(CASE
                                        WHEN TPS.INDICATOR IS NULL THEN NULL
                                        ELSE COALESCE(Isnull(ND.PROJECTION_SALES, 0) / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
                                      END, 0),
                               RD.HIERARCHY_NO,
                               RD.LEVEL_NAME,
                               COALESCE(Isnull(ND.ACTUAL_SALES, 0) / NULLIF(EX_FACTORY_SALES_ACTUALS, 0), 0) * 100,
                               COALESCE(Isnull(ND.PROJECTION_SALES, 0) / NULLIF(EX_FACTORY_SALES_PROJ, 0), 0) * 100,
                               COALESCE(ACCRUAL_AMOUNT_PROJECTION, ACCRUAL_AMOUNT_ACTUAL) AS TOTAL_DISCOUNT_ACCRUAL
                        FROM   #PRIOR_DISCOUNT ND
                               JOIN #PRIOR_SALES RD
                                 ON RD.YEAR = ND.YEAR
                                    AND RD.PERIOD = ND.PERIOD
                                    AND RD.DISCOUNT = ND.RS_NAME
                                    AND RD.CFF_MASTER_SID = ND.CFF_MASTER_SID
                                    AND RD.HIERARCHY_NO = ND.HIERARCHY_NO
                                    AND RD.LEVEL_NAME = ND.LEVEL_NAME
                                    AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(ND.PARENT_HIERARCHY_NO, ND.HIERARCHY_NO)
                               JOIN #PRIOR_PRODUCT_FILE_DATA PFD
                                 ON PFD.YEAR = ND.YEAR
                                    AND PFD.PERIOD = ND.PERIOD
                                    AND PFD.CFF_MASTER_SID = ND.CFF_MASTER_SID
                                    AND PFD.HIERARCHY_NO = ND.HIERARCHY_NO
                                    AND PFD.LEVEL_NAME = ND.LEVEL_NAME
                                    AND COALESCE(PFD.PARENT_HIERARCHY_NO, PFD.HIERARCHY_NO) = COALESCE(ND.PARENT_HIERARCHY_NO, ND.HIERARCHY_NO)
                                    AND PFD.DISCOUNT = ND.RS_NAME
                               JOIN RS_CONTRACT RC
                                 ON RS_CONTRACT_SID = ND.RS_NAME
                               LEFT JOIN #TEMP_PRIOR_HIER_SALES TPS
                                      ON TPS.HIERARCHY_NO = RD.HIERARCHY_NO
                                         AND TPS.CFF_MASTER_SID = RD.CFF_MASTER_SID
                               LEFT JOIN #TEMP_PRIOR_HIER_DISC TPD
                                      ON TPD.HIERARCHY_NO = ND.HIERARCHY_NO
                                         AND TPD.CFF_MASTER_SID = ND.CFF_MASTER_SID
                        UNION ALL
                        SELECT PD.CFF_MASTER_SID,
                               PD.YEAR,
                               PD.PERIOD,
                               RC.RS_NAME AS RS_NAME,
                               PD.PARENT_HIERARCHY_NO,
                               Isnull(PD.PPA_DISCOUNT_PROJECTED, 0),
                               Isnull(CASE
                                        WHEN TPS.INDICATOR IS NULL
                                              OR TPD.INDICATOR IS NULL THEN NULL
                                        ELSE COALESCE(Isnull(PD.PPA_DISCOUNT_PROJECTED, 0) / NULLIF(RD.NM_PROJECTED_SALES, 0), 0) * 100
                                      END, 0),
                               Isnull(CASE
                                        WHEN TPS.INDICATOR IS NULL
                                              OR TPD.INDICATOR IS NULL THEN NULL
                                        ELSE COALESCE(Isnull(PD.PPA_DISCOUNT_PROJECTED, 0) / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
                                      END, 0),
                               RD.HIERARCHY_NO,
                               RD.LEVEL_NAME,
                               COALESCE(Isnull(PD.PPA_ACTUAL_SALES, 0) / NULLIF(EX_FACTORY_SALES_ACTUALS, 0), 0) * 100,
                               COALESCE(Isnull(PD.PPA_DISCOUNT_PROJECTED, 0) / NULLIF(EX_FACTORY_SALES_PROJ, 0), 0) * 100,
                               0.00000    AS TOTAL_DISCOUNT_ACCRUAL
                        FROM   #PRIOR_PPA PD
                               JOIN #PRIOR_SALES RD
                                 ON RD.YEAR = PD.YEAR
                                    AND RD.PERIOD = PD.PERIOD
                                    AND RD.DISCOUNT = PD.DISCOUNT
                                    AND RD.CFF_MASTER_SID = PD.CFF_MASTER_SID
                                    AND RD.HIERARCHY_NO = PD.HIERARCHY_NO
                                    AND RD.LEVEL_NAME = PD.LEVEL_NAME
                                    AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(PD.PARENT_HIERARCHY_NO, PD.HIERARCHY_NO)
                               JOIN #PRIOR_PRODUCT_FILE_DATA PFD
                                 ON PFD.YEAR = RD.YEAR
                                    AND PFD.PERIOD = RD.PERIOD
                                    AND PFD.DISCOUNT = PD.DISCOUNT
                                    AND PFD.CFF_MASTER_SID = RD.CFF_MASTER_SID
                                    AND PFD.HIERARCHY_NO = PD.HIERARCHY_NO
                                    AND PFD.LEVEL_NAME = PD.LEVEL_NAME
                                    AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(PD.PARENT_HIERARCHY_NO, PD.HIERARCHY_NO)
                               JOIN RS_CONTRACT RC
                                 ON RS_CONTRACT_SID = PD.DISCOUNT
                               LEFT JOIN #TEMP_PRIOR_HIER_SALES TPS
                                      ON TPS.HIERARCHY_NO = RD.HIERARCHY_NO
                                         AND TPS.CFF_MASTER_SID = RD.CFF_MASTER_SID
                               LEFT JOIN #TEMP_PRIOR_HIER_DISC TPD
                                      ON TPD.HIERARCHY_NO = PD.HIERARCHY_NO
                                         AND TPD.CFF_MASTER_SID = PD.CFF_MASTER_SID
                    END
                  ELSE
                    BEGIN
                        INSERT INTO #RESULT
                                    (CFF_PROJECTION_MASTER_SID,
                                     YEAR,
                                     PERIOD,
                                     RS_NAME,
                                     PARENT_HIERARCHY_NO,
                                     TOTAL_DISCOUNT_PROJECTED,
                                     TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                     TOTAL_PROJECTED_RPU,
                                     HIERARCHY_NO,
                                     LEVEL_NAME,
                                     DISCOUNT_OF_EX_FACTORY_ACTUALS_PERCENT,
                                     DISCOUNT_OF_EX_FACTORY_PROJECTED_PERCENT,
                                     TOTAL_DISCOUNT_ACCRUAL)
                        --SELECT RD.CFF_MASTER_SID,
                        --       YEAR,
                        --       PERIOD,
                        --       RD.RS_NAME,
                        --       Isnull(RETURNS_PROJECTED, 0),
                        --       Isnull(PROJECTED_RATE * 100, 0),
                        --       Isnull(CASE
                        --                WHEN TPS.INDICATOR IS NULL
                        --                      OR TPD.INDICATOR IS NULL THEN NULL
                        --                ELSE COALESCE(Isnull(RETURNS_PROJECTED, 0) / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
                        --              END, 0),
                        --       RD.HIERARCHY_NO,
                        --       LEVEL_NAME
                        --FROM   #PRIOR_SALES RD
                        --       LEFT JOIN #TEMP_PRIOR_HIER_SALES TPS
                        --              ON TPS.HIERARCHY_NO = RD.HIERARCHY_NO
                        --                 AND TPS.CFF_MASTER_SID = RD.CFF_MASTER_SID
                        --       LEFT JOIN #TEMP_PRIOR_HIER_DISC TPD
                        --              ON TPD.HIERARCHY_NO = RD.HIERARCHY_NO
                        --                 AND TPD.CFF_MASTER_SID = RD.CFF_MASTER_SID
                        --UNION ALL
                        SELECT ND.CFF_MASTER_SID,
                               ND.YEAR,
                               ND.PERIOD,
                               nd.RS_NAME,
                               ND.PARENT_HIERARCHY_NO,
                               Isnull(ND.PROJECTION_SALES, 0),
                               Isnull(CASE
                                        WHEN TPS.INDICATOR IS NULL
                                      --OR ND.PRIOR_INDICATOR = 0
                                      THEN NULL
                                        ELSE COALESCE(Isnull(ND.PROJECTION_SALES, 0) / NULLIF(RD.NM_PROJECTED_SALES, 0), 0) * 100
                                      END, 0),
                               Isnull(CASE
                                        WHEN TPS.INDICATOR IS NULL
                                      --OR ND.PRIOR_INDICATOR = 0
                                      THEN NULL
                                        ELSE COALESCE(Isnull(ND.PROJECTION_SALES, 0) / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
                                      END, 0),
                               RD.HIERARCHY_NO,
                               RD.LEVEL_NAME,
                               COALESCE(Isnull(ND.ACTUAL_SALES, 0) / NULLIF(EX_FACTORY_SALES_ACTUALS, 0), 0) * 100,
                               COALESCE(Isnull(ND.PROJECTION_SALES, 0) / NULLIF(EX_FACTORY_SALES_PROJ, 0), 0) * 100,
                               COALESCE(ACCRUAL_AMOUNT_PROJECTION, ACCRUAL_AMOUNT_ACTUAL) AS TOTAL_DISCOUNT_ACCRUAL
                        FROM   #PRIOR_DISCOUNT ND
                               JOIN #PRIOR_SALES RD
                                 ON RD.YEAR = ND.YEAR
                                    AND RD.PERIOD = ND.PERIOD
                                    AND RD.DISCOUNT = ND.RS_NAME
                                    AND RD.CFF_MASTER_SID = ND.CFF_MASTER_SID
                                    AND RD.HIERARCHY_NO = ND.HIERARCHY_NO
                                    AND RD.LEVEL_NAME = ND.LEVEL_NAME
                                    AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(ND.PARENT_HIERARCHY_NO, ND.HIERARCHY_NO)
                               JOIN #PRIOR_PRODUCT_FILE_DATA PFD
                                 ON PFD.YEAR = ND.YEAR
                                    AND PFD.PERIOD = ND.PERIOD
                                    AND PFD.DISCOUNT = ND.RS_NAME
                                    AND PFD.CFF_MASTER_SID = ND.CFF_MASTER_SID
                                    AND PFD.HIERARCHY_NO = ND.HIERARCHY_NO
                                    AND PFD.LEVEL_NAME = ND.LEVEL_NAME
                                    AND COALESCE(PFD.PARENT_HIERARCHY_NO, PFD.HIERARCHY_NO) = COALESCE(ND.PARENT_HIERARCHY_NO, ND.HIERARCHY_NO)
                               LEFT JOIN #TEMP_PRIOR_HIER_SALES TPS
                                      ON TPS.HIERARCHY_NO = RD.HIERARCHY_NO
                                         AND TPS.CFF_MASTER_SID = RD.CFF_MASTER_SID
                               LEFT JOIN #TEMP_PRIOR_HIER_DISC TPD
                                      ON TPD.HIERARCHY_NO = ND.HIERARCHY_NO
                                         AND TPD.CFF_MASTER_SID = ND.CFF_MASTER_SID
                        UNION ALL
                        SELECT PD.CFF_MASTER_SID,
                               PD.YEAR,
                               PD.PERIOD,
                               pd.discount AS rs_name,
                               PD.PARENT_HIERARCHY_NO,
                               Isnull(PD.PPA_DISCOUNT_PROJECTED, 0),
                               Isnull(CASE
                                        WHEN TPS.INDICATOR IS NULL
                                              OR TPD.INDICATOR IS NULL THEN NULL
                                        ELSE COALESCE(Isnull(PD.PPA_DISCOUNT_PROJECTED, 0) / NULLIF(RD.NM_PROJECTED_SALES, 0), 0) * 100
                                      END, 0),
                               Isnull(CASE
                                        WHEN TPS.INDICATOR IS NULL
                                              OR TPD.INDICATOR IS NULL THEN NULL
                                        ELSE COALESCE(Isnull(PD.PPA_DISCOUNT_PROJECTED, 0) / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
                                      END, 0),
                               RD.HIERARCHY_NO,
                               RD.LEVEL_NAME,
                               COALESCE(Isnull(PD.PPA_ACTUAL_SALES, 0) / NULLIF(EX_FACTORY_SALES_ACTUALS, 0), 0) * 100,
                               COALESCE(Isnull(PD.PPA_DISCOUNT_PROJECTED, 0) / NULLIF(EX_FACTORY_SALES_PROJ, 0), 0) * 100,
                               0.00000     AS TOTAL_DISCOUNT_ACCRUAL
                        FROM   #PRIOR_PPA PD
                               JOIN #PRIOR_SALES RD
                                 ON RD.YEAR = PD.YEAR
                                    AND RD.PERIOD = PD.PERIOD
                                    AND RD.DISCOUNT = PD.DISCOUNT
                                    AND RD.CFF_MASTER_SID = PD.CFF_MASTER_SID
                                    AND RD.HIERARCHY_NO = PD.HIERARCHY_NO
                                    AND RD.LEVEL_NAME = PD.LEVEL_NAME
                                    AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(PD.PARENT_HIERARCHY_NO, PD.HIERARCHY_NO)
                               JOIN #PRIOR_PRODUCT_FILE_DATA PFD
                                 ON PFD.YEAR = RD.YEAR
                                    AND PFD.PERIOD = RD.PERIOD
                                    AND PFD.DISCOUNT = PD.DISCOUNT
                                    AND PFD.CFF_MASTER_SID = RD.CFF_MASTER_SID
                                    AND PFD.HIERARCHY_NO = PD.HIERARCHY_NO
                                    AND PFD.LEVEL_NAME = PD.LEVEL_NAME
                                    AND COALESCE(PFD.PARENT_HIERARCHY_NO, PFD.HIERARCHY_NO) = COALESCE(PD.PARENT_HIERARCHY_NO, PD.HIERARCHY_NO)
                               LEFT JOIN #TEMP_PRIOR_HIER_SALES TPS
                                      ON TPS.HIERARCHY_NO = RD.HIERARCHY_NO
                                         AND TPS.CFF_MASTER_SID = RD.CFF_MASTER_SID
                               LEFT JOIN #TEMP_PRIOR_HIER_DISC TPD
                                      ON TPD.HIERARCHY_NO = PD.HIERARCHY_NO
                                         AND TPD.CFF_MASTER_SID = PD.CFF_MASTER_SID
                    END
              END

            ------------based on forecasting type it will insert into result-------------------------------------------------
            IF EXISTS (SELECT 1
                       FROM   #PRIOR_CPP_INFO
                       WHERE  FORECASTING_TYPE = 'MANDATED')
              BEGIN


                  INSERT INTO #RESULT
                              (CFF_PROJECTION_MASTER_SID,
                               YEAR,
                               PERIOD,
                               RS_NAME,
                               PARENT_HIERARCHY_NO,
                               TOTAL_DISCOUNT_PROJECTED,
                               TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                               TOTAL_PROJECTED_RPU,
                               HIERARCHY_NO,
                               LEVEL_NAME,
                               DISCOUNT_OF_EX_FACTORY_ACTUALS_PERCENT,
                               DISCOUNT_OF_EX_FACTORY_PROJECTED_PERCENT)
                  SELECT MD.CFF_MASTER_SID,
                         MD.YEAR,
                         MD.PERIOD,
                         'MANDATED',
                         MD.PARENT_HIERARCHY_NO,
                         Isnull(MD.MANDATED_DISCOUNT_PROJECTED, 0),
                         Isnull(CASE
                                  WHEN TPS.INDICATOR IS NULL
                                        OR TPD.INDICATOR IS NULL THEN NULL
                                  ELSE COALESCE(Isnull(MD.MANDATED_DISCOUNT_PROJECTED, 0) / NULLIF(RD.M_PROJECTED_SALES, 0), 0) * 100
                                END, 0),
                         Isnull(CASE
                                  WHEN TPS.INDICATOR IS NULL
                                        OR TPD.INDICATOR IS NULL THEN NULL
                                  ELSE COALESCE(Isnull(MD.MANDATED_DISCOUNT_PROJECTED, 0) / NULLIF(RD.M_PROJECTED_UNITS, 0), 0)
                                END, 0),
                         RD.HIERARCHY_NO,
                         RD.LEVEL_NAME,
                         COALESCE(Isnull(MD.MANDATED_DISCOUNT_ACTUALS, 0) / NULLIF(EX_FACTORY_SALES_ACTUALS, 0), 0) * 100,
                         COALESCE(Isnull(MD.MANDATED_DISCOUNT_PROJECTED, 0) / NULLIF(EX_FACTORY_SALES_PROJ, 0), 0) * 100
                  FROM   #PRIOR_PPA_SUPP_MANDATED MD
                         JOIN #PRIOR_SALES RD
                           ON RD.YEAR = MD.YEAR
                              AND RD.PERIOD = MD.PERIOD
                              AND MD.DISCOUNT = RD.DISCOUNT
                              AND RD.CFF_MASTER_SID = MD.CFF_MASTER_SID
                              AND RD.HIERARCHY_NO = MD.HIERARCHY_NO
                              AND RD.LEVEL_NAME = MD.LEVEL_NAME
                              AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(MD.PARENT_HIERARCHY_NO, MD.HIERARCHY_NO)
                         JOIN #PRIOR_PRODUCT_FILE_DATA PFD
                           ON PFD.YEAR = MD.YEAR
                              AND PFD.PERIOD = MD.PERIOD
							  AND PFD.DISCOUNT = MD.DISCOUNT------------------CEL-462(2)
							  AND PFD.CFF_MASTER_SID = MD.CFF_MASTER_SID------------------CEL-462(2)
                              AND PFD.HIERARCHY_NO = MD.HIERARCHY_NO
                              AND PFD.LEVEL_NAME = MD.LEVEL_NAME
                              AND COALESCE(PFD.PARENT_HIERARCHY_NO, PFD.HIERARCHY_NO) = COALESCE(MD.PARENT_HIERARCHY_NO, MD.HIERARCHY_NO)
                         LEFT JOIN #TEMP_PRIOR_HIER_SALES TPS
                                ON TPS.HIERARCHY_NO = RD.HIERARCHY_NO
                                   AND TPS.CFF_MASTER_SID = RD.CFF_MASTER_SID
                         LEFT JOIN #TEMP_PRIOR_HIER_DISC TPD
                                ON TPD.HIERARCHY_NO = MD.HIERARCHY_NO
                                   AND TPD.CFF_MASTER_SID = MD.CFF_MASTER_SID

                  UNION ALL
                  SELECT SD.CFF_MASTER_SID,
                         SD.YEAR,
                         SD.PERIOD,
                         'SUPPLEMENTAL',
                         SD.PARENT_HIERARCHY_NO,
                         Isnull(CASE
                                  WHEN TPS.INDICATOR IS NULL
                                        OR TPD.INDICATOR IS NULL THEN NULL
                                  ELSE Isnull(SD.SUPPLEMENTAL_DISCOUNT_PROJECTED, 0)
                                END, 0) SUPPLEMENTAL_DISCOUNT_PROJECTED,
                         Isnull(CASE
                                  WHEN TPS.INDICATOR IS NULL
                                        OR TPD.INDICATOR IS NULL THEN NULL
                                  ELSE COALESCE(Isnull(SD.SUPPLEMENTAL_DISCOUNT_PROJECTED, 0) / NULLIF(RD.M_PROJECTED_SALES, 0), 0) * 100
                                END, 0),
                         Isnull(CASE
                                  WHEN TPS.INDICATOR IS NULL
                                        OR TPD.INDICATOR IS NULL THEN NULL
                                  ELSE COALESCE(Isnull(SD.SUPPLEMENTAL_DISCOUNT_PROJECTED, 0) / NULLIF(RD.M_PROJECTED_UNITS, 0), 0)
                                END, 0),
                         RD.HIERARCHY_NO,
                         RD.LEVEL_NAME,
                         COALESCE(Isnull(SD.SUPPLEMENTAL_DISCOUNT_ACTUALS, 0) / NULLIF(EX_FACTORY_SALES_ACTUALS, 0), 0) * 100,
                         COALESCE(Isnull(SD.SUPPLEMENTAL_DISCOUNT_PROJECTED, 0) / NULLIF(EX_FACTORY_SALES_PROJ, 0), 0) * 100
                  FROM   #PRIOR_PPA_SUPP_MANDATED SD
                         JOIN #PRIOR_SALES RD
                           ON RD.YEAR = SD.YEAR
                              AND RD.PERIOD = SD.PERIOD
                              AND RD.CFF_MASTER_SID = SD.CFF_MASTER_SID
                              AND RD.HIERARCHY_NO = SD.HIERARCHY_NO
                              AND RD.LEVEL_NAME = SD.LEVEL_NAME
                              AND SD.DISCOUNT = RD.DISCOUNT
                              AND COALESCE(RD.PARENT_HIERARCHY_NO, RD.HIERARCHY_NO) = COALESCE(sd.PARENT_HIERARCHY_NO, sd.HIERARCHY_NO)
                         JOIN #PRIOR_PRODUCT_FILE_DATA PFD
                           ON PFD.YEAR = RD.YEAR
                              AND PFD.PERIOD = RD.PERIOD
							  AND PFD.DISCOUNT = RD.DISCOUNT------------------CEL-462(2)
							  AND PFD.CFF_MASTER_SID = RD.CFF_MASTER_SID------------------CEL-462(2)                              
                              AND PFD.HIERARCHY_NO = SD.HIERARCHY_NO
                              AND PFD.LEVEL_NAME = SD.LEVEL_NAME
                              AND COALESCE(PFD.PARENT_HIERARCHY_NO, PFD.HIERARCHY_NO) = COALESCE(sd.PARENT_HIERARCHY_NO, sd.HIERARCHY_NO)
                         LEFT JOIN #TEMP_PRIOR_HIER_SALES TPS
                                ON TPS.HIERARCHY_NO = RD.HIERARCHY_NO
                                   AND TPS.CFF_MASTER_SID = RD.CFF_MASTER_SID
                         LEFT JOIN #TEMP_PRIOR_HIER_DISC TPD
                                ON TPD.HIERARCHY_NO = SD.HIERARCHY_NO
                                   AND TPD.CFF_MASTER_SID = SD.CFF_MASTER_SID
              END

            ------------- PIVOTING STARThere 
            DECLARE @SQL       NVARCHAR(MAX),
                    @LOOP_CNTR INT,
                    @MAX_CCP   INT

            SET @SQL = 'SELECT pr.HIERARCHY_NO,
                                         LEVEL_NAME,[YEAR],PERIOD,RS_NAME, '
            SET @LOOP_CNTR = 1
            SET @MAX_CCP = (SELECT Max(ID)
                            FROM   #CFF_PROJECTION_MASTER)

            WHILE @LOOP_CNTR <= @MAX_CCP
              BEGIN
                  SET @SQL += 'TOTAL_DISCOUNT_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + 'THEN TOTAL_DISCOUNT_ACCRUAL END),0), 
						   TOTAL_DISCOUNT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_DISCOUNT END),
TOTAL_DISCOUNT_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_DISCOUNT_PROJECTED END),
TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + 'THEN TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE END),
								  TOTAL_DISCOUNT_PERCENTAGE_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_DISCOUNT_PERCENTAGE END),

TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),
TOTAL_RPU_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + 'THEN TOTAL_RPU_ACCRUAL END),
                                   TOTAL_RPU_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_RPU END),  
TOTAL_PROJECTED_RPU_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_PROJECTED_RPU END),
DISCOUNT_OF_EX_FACTORY_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + 'THEN DISCOUNT_OF_EX_FACTORY_ACCRUAL END),
                                   DISCOUNT_OF_EX_FACTORY_ACTUALS_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN DISCOUNT_OF_EX_FACTORY_ACTUALS_PERCENT END), 
DISCOUNT_OF_EX_FACTORY_PROJECTED_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN DISCOUNT_OF_EX_FACTORY_PROJECTED_PERCENT END), '
                  SET @LOOP_CNTR += 1
              END

            SET @SQL = LEFT(@SQL, Len(@SQL) - 1)
            SET @SQL += ' ,C.PARENT_HIERARCHY_NO FROM   #RESULT PR
      					INNER JOIN #CFF_PROJECTION_MASTER PM
      					ON PR.CFF_PROJECTION_MASTER_SID = PM.CFF_PROJECTION_MASTER_SID
						JOIN(SELECT DISTINCT HIERARCHY_NO,PARENT_HIERARCHY_NO,LEVEL_NO FROM #CCP) C ON C.HIERARCHY_NO=PR.HIERARCHY_NO
						AND  COALESCE(C.PARENT_HIERARCHY_NO,C.HIERARCHY_NO) = COALESCE(PR.PARENT_HIERARCHY_NO,PR.HIERARCHY_NO)---------------CEL-462(2)
      					GROUP  BY C.LEVEL_NO,PR.HIERARCHY_NO,C.PARENT_HIERARCHY_NO,
						 PR.LEVEL_NAME,[YEAR],PERIOD,RS_NAME
      					ORDER BY C.LEVEL_NO,PR.HIERARCHY_NO,C.PARENT_HIERARCHY_NO,'
                        + CASE WHEN @VIEW = 'PERIOD' THEN 'RS_NAME,YEAR,PERIOD' ELSE 'YEAR,PERIOD,RS_NAME' END
        END

      --PRINT @SQL
      EXEC Sp_executesql
        @SQL
  END
  GO


