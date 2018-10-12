IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_VIEWS_POPULATION'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_VIEWS_POPULATION]
  END
  
GO

CREATE PROCEDURE [dbo].[PRC_VIEWS_POPULATION] (@PROJECTION_MASTER_SID  INT,
                                              @USER_ID                INT,
                                              @SESSION_ID             VARCHAR(50),
                                              @FLAG                   CHAR(3),
                                              @FREQUENCY              CHAR(1),
                                              @SCREEN_NAME            VARCHAR(10),
                                              @VIEW                   CHAR(2),
                                              @START_PERIOD           INT=NULL,
                                              @END_PERIOD             INT=NULL,
                                              @MASS_UPDATEFIELD       VARCHAR(20)=NULL,
                                              @CUSTOM_VIEW_MASTER_SID INT=NULL,
                                              @CUSTOM_VERSION_NO      INT=NULL,
                                              @CUSTOM_LEVEL_NO        INT=NULL,
                                              @UOM_CODE               VARCHAR(10)=NULL,
                                              @DISCOUNT               VARCHAR(max)=NULL,
                                              @PROGRAM                VARCHAR(100)=NULL)
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          DECLARE @CCP_HIERARCHY VARCHAR(200) = Concat('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @CUSTOMER_TABLE_SALES VARCHAR(200) = Concat('ST_CUSTOMER_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @PRODUCT_TABLE_SALES VARCHAR(200) = Concat ('ST_PRODUCT_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @CUSTOM_TABLE_SALES  VARCHAR(200) = Concat ('ST_CUSTOM_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @CUSTOMER_TABLE_DISCOUNT VARCHAR(200) = Concat('ST_CUSTOMER_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @PRODUCT_TABLE_DISCOUNT VARCHAR(200) = Concat ('ST_PRODUCT_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @CUSTOM_TABLE_DISCOUNT  VARCHAR(200) = Concat ('ST_CUSTOM_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @D_MASTER_TABLE     VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @D_ACTUAL_TABLE     VARCHAR(200) = Concat ('ST_NM_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @D_PROJECTION_TABLE VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @S_MASTER_TABLE     VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @S_ACTUAL_TABLE     VARCHAR(200) = Concat ('ST_NM_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @S_PROJECTION_TABLE VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @STATUS_TABLE       VARCHAR(200) = Concat ('ST_STATUS_TABLE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ITEM_UOM_TABLE     VARCHAR(MAX) = Concat ('ST_ITEM_UOM_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				  @CUSTOM_CCP_SALES VARCHAR(200) = CONCAT ('CUSTOM_CCP_SALES_',@CUSTOM_VIEW_MASTER_SID)
		  DECLARE @CUSTOMER_DISCOUNT_PV VARCHAR(200) = Concat('ST_CUSTOMER_PV_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
		          @PRODUCT_DISCOUNT_PV VARCHAR(200) = Concat('ST_PRODUCT_PV_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				  @CUSTOM_DISCOUNT_PV VARCHAR(200) = Concat('ST_CUSTOM_PV_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				  @CCP_PV_FILTERS VARCHAR(200) = Concat('ST_CCP_PV_FILTERS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @FROM_DATE                     DATE,
                  @STARTFROM                     DATE,
                  @PROJECTION_DATE               DATE,
                  --, @ITEM_ID VARCHAR(50)
                  @CUST_RELATIONSHIP_BUILDER_SID INT,
                  @DED_RELATIONSHIP_BUILDER_SID  INT
          DECLARE @STAT_SALES_SID INT
          DECLARE @END_SALES_SID INT
          DECLARE @PROJ_START_SID INT
          DECLARE @PROJ_END_SID                  INT,
                  @PROJ_START_PERIOD_SID         INT,
                  @START_DATE                    DATETIME,
                  @PROD_RELATIONSHIP_BUILDER_SID INT,
                  --,@DED_RELATIONSHIP_BUILDER_SID INT
                  @PROJECTION_CUST_VERSION       INT,
                  @PROJECTION_PROD_VERSION       INT,
                  @SQL                           NVARCHAR(MAX)=N'',@filter_ccp_adj bit;

          SET @END_PERIOD=COALESCE(@END_PERIOD, @START_PERIOD)

          SELECT @STAT_SALES_SID = Max(CASE
                                         WHEN period_date = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0) THEN period_Sid
                                       END),
                 @END_SALES_SID = Max(CASE
                                        WHEN period_date = Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0)THEN period_Sid
                                      END),
                 @PROJ_START_SID = Max(CASE
                                         WHEN period_date = Datefromparts(Year(FROM_DATE), 1, 1) THEN period_Sid
                                       END),
                 @PROJ_END_SID = Max(CASE
                                       WHEN period_date = Datefromparts(Year(TO_DATE), 12, 1) THEN period_Sid
                                     END)
          FROM   PROJECTION_MASTER
                 JOIN period p
                   ON period_date IN ( Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0), Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0), Datefromparts(Year(FROM_DATE), 1, 1), Datefromparts(Year(TO_DATE), 12, 1) )
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          SELECT @CUSTOM_LEVEL_NO = Max(LEVEL_NO)
          FROM   CUST_VIEW_DETAILS
          WHERE  CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID

          IF NOT EXISTS (SELECT 1
                         FROM   CUST_VIEW_DETAILS
                         WHERE  CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID)
            BEGIN
                SET @CUSTOM_LEVEL_NO = 0
            END

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
                            ELSE 1
                          END
          INTO   #PERIOD
          FROM   PERIOD
          WHERE  PERIOD_SID BETWEEN @STAT_SALES_SID AND @PROJ_END_SID

          CREATE NONCLUSTERED INDEX NIX_PERIOD
            ON #PERIOD ( PERIOD, [YEAR] )
            INCLUDE (PERIOD_SID )

          SELECT TOP 1 @STARTFROM = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0),
                       @START_DATE = Dateadd(dd, 1, Eomonth(FROM_DATE, -1)),
                       @PROJECTION_DATE = Dateadd(dd, 1, Eomonth(TO_DATE, -1)),
                       @CUST_RELATIONSHIP_BUILDER_SID = CUST_RELATIONSHIP_BUILDER_SID,
                       @PROD_RELATIONSHIP_BUILDER_SID = PROD_RELATIONSHIP_BUILDER_SID,
                       @DED_RELATIONSHIP_BUILDER_SID = DED_RELATIONSHIP_BULDER_SID
                       --,
                       --         @MIN_LEVEL = CASE @VIEW
                       --                        WHEN 'C' THEN CUSTOMER_HIERARCHY_LEVEL
                       --                        WHEN 'P' THEN PRODUCT_HIERARCHY_LEVEL
                       --                      END,
                       ,
                       @PROJECTION_CUST_VERSION = PROJECTION_CUST_VERSION,
                       @PROJECTION_PROD_VERSION = PROJECTION_PROD_VERSION
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          --SELECT @START_PERIOD_SID = Max(CASE
          --                                 WHEN PERIOD_DATE = @STARTFROM THEN PERIOD_SID
          --                               END),
          --       @END_PERIOD_SID = Max(CASE
          --                               WHEN PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0) THEN PERIOD_SID
          --                             END),
          --       @PROJ_START_PERIOD_SID = Max(CASE
          --                                      WHEN PERIOD_DATE = @START_DATE THEN PERIOD_SID
          --                                    END)
          --FROM   #PERIOD
          --WHERE  PERIOD_DATE IN ( @STARTFROM, @PROJECTION_DATE, @START_DATE )
          IF Object_id('TEMPDB..#APPROVED_CCP_DETAILS') IS NOT NULL
            DROP TABLE #APPROVED_CCP_DETAILS

          CREATE TABLE #APPROVED_CCP_DETAILS
            (
               PROJECTION_MASTER_SID  INT,
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT
            )

          INSERT INTO #APPROVED_CCP_DETAILS
                      (PROJECTION_MASTER_SID,
                       PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID)
          EXEC Prc_approved_ccp_details
            @projection_master_Sid,
            'Non Mandated',
            @user_id,
            @session_id

          IF Object_id('TEMPDB..#ITEM_UOM_DETAILS', 'U') IS NOT NULL
            DROP TABLE #ITEM_UOM_DETAILS;

          CREATE TABLE #ITEM_UOM_DETAILS
            (
               CCP_DETAILS_SID INT NOT NULL,
               UOM_VALUE       NUMERIC(22, 6)
            )

          SET @sql = Concat (' INSERT INTO  #ITEM_UOM_DETAILS
         SELECT DISTINCT A.CCP_DETAILS_SID,UOM_VALUE 
         FROM ', @CCP_HIERARCHY, ' A JOIN CCP_DETAILS CD ON CD.CCP_DETAILS_SID=A.CCP_DETAILS_SID 
         JOIN  ', @ITEM_UOM_TABLE, ' B ON CD.ITEM_MASTER_SID=B.ITEM_MASTER_SID
         WHERE  UOM_CODE=@UOM_CODE 
         ')

          EXEC Sp_executesql
            @sql,
            N'@UOM_CODE VARCHAR(50)',
            @UOM_CODE = @UOM_CODE

          IF Object_id('TEMPDB..#CUSTOM_SALES', 'U') IS NOT NULL
            DROP TABLE #CUSTOM_SALES;

          CREATE TABLE #CUSTOM_SALES
            (
               HIERARCHY_NO    VARCHAR(8000) NOT NULL,
               PERIOD          INT NOT NULL,
               [YEAR]          INT NOT NULL,
               SALES           NUMERIC(22, 6) NULL,
               UNITS           NUMERIC(22, 6) NULL,
               SALES_INCLUSION BIT NULL,
               INDICATOR       BIT NULL,
               ACCOUNT_GROWTH  NUMERIC(22, 6) NULL,
               PRODUCT_GROWTH  NUMERIC(22, 6) NULL
            )

          SET @sql =Concat('UPDATE ', @STATUS_TABLE, '
                SET    FLAG = ''R''
                WHERE   ''', @SCREEN_NAME, '''  = SCREEN_NAME
                         AND VIEW_NAME = CASE ''', @VIEW, '''
                         WHEN ''C'' THEN ''CUSTOMER''
                         WHEN ''P'' THEN ''PRODUCT''
                         ELSE ''CUSTOM''
                       END ')

        --  EXEC Sp_executesql
         --   @sql
		 SET @SQL=CONCAT('IF EXISTS(SELECT 1 FROM ',@S_MASTER_TABLE,')
BEGIN
SET @FILTER_CCP_adj=1
END
ELSE
SET @FILTER_CCP_adj=0
')
EXEC SP_EXECUTESQL @SQL,N'@FILTER_CCP_adj BIT OUTPUT',@FILTER_CCP_adj=@FILTER_CCP_adj OUTPUT
          ----TO IDENTIFY THE LEVEL SELECTED BY USER
          IF Object_id('TEMPDB..#RS_INFO') IS NOT NULL
            DROP TABLE #RS_INFO

          CREATE TABLE #RS_INFO
            (
               CCP_DETAILS_SID INT,
               RS_CONTRACT_SID INT,
               SELECTED_LEVEL  INT
            )

          SET @sql=Concat('SELECT CCP_DETAILS_SID,R.RS_CONTRACT_SID,
            CASE @PROGRAM
              WHEN ''SCHEDULE ID'' THEN R.RS_CONTRACT_SID
              WHEN ''PROGRAM'' THEN R.RS_CONTRACT_SID
              --WHEN ''SCHEDULE TYPE'' THEN R_TYPE.DESCRIPTION
              --WHEN ''PROGRAM CATEGORY'' THEN RPT.DESCRIPTION
              --WHEN ''PROGRAM TYPE'' THEN RPT.DESCRIPTION
              --WHEN ''SCHEDULE CATEGORY'' THEN RC.DESCRIPTION                                  
              WHEN ''SCHEDULE TYPE'' THEN RS_TYPE
              WHEN ''PROGRAM CATEGORY'' THEN REBATE_PROGRAM_TYPE
              WHEN ''PROGRAM TYPE'' THEN REBATE_PROGRAM_TYPE
              WHEN ''SCHEDULE CATEGORY'' THEN RS_CATEGORY
              WHEN ''UDC1'' THEN UD.UDC1
              WHEN ''UDC2'' THEN UD.UDC2
              WHEN ''UDC3'' THEN UD.UDC3
              WHEN ''UDC4'' THEN UD.UDC4
              WHEN ''UDC5'' THEN UD.UDC5
              WHEN ''UDC6'' THEN UD.UDC6
            END SELECTED_LEVEL
     FROM   RS_CONTRACT R
            --LEFT JOIN HELPER_TABLE R_TYPE
            --       ON R_TYPE.HELPER_TABLE_SID = R.RS_TYPE
            --LEFT JOIN HELPER_TABLE RPT
            --       ON RPT.HELPER_TABLE_SID = R.REBATE_PROGRAM_TYPE
            --LEFT JOIN HELPER_TABLE RC
            --       ON RC.HELPER_TABLE_SID = R.RS_CATEGORY
            --LEFT JOIN (SELECT U.MASTER_SID,
            --                  U1.DESCRIPTION AS UDC1,
            --                  U2.DESCRIPTION AS UDC2,
            --                  U3.DESCRIPTION AS UDC3,
            --                  U4.DESCRIPTION AS UDC4,
            --                  U5.DESCRIPTION AS UDC5,
            --                  U6.DESCRIPTION UDC6
            --           FROM   UDCS U
            --                  LEFT JOIN HELPER_TABLE U1
            --                         ON U1.HELPER_TABLE_SID = U.UDC1
            --                  LEFT JOIN HELPER_TABLE U2
           --                         ON U2.HELPER_TABLE_SID = U.UDC2
            --                  LEFT JOIN HELPER_TABLE U3
            --                         ON U3.HELPER_TABLE_SID = U.UDC3
            --                  LEFT JOIN HELPER_TABLE U4
            --                         ON U4.HELPER_TABLE_SID = U.UDC4
            --                  LEFT JOIN HELPER_TABLE U5
            --                         ON U5.HELPER_TABLE_SID = U.UDC5
            --                  LEFT JOIN HELPER_TABLE U6
            --                         ON U6.HELPER_TABLE_SID = U.UDC6
            --           WHERE  MASTER_TYPE = ''RS_CONTRACT'') UD
                                                INNER JOIN ', @D_MASTER_TABLE, ' D
                    on  R.RS_CONTRACT_SID = D.RS_CONTRACT_SID 
                                                LEFT JOIN UDCS UD
                   ON UD.MASTER_SID = R.RS_CONTRACT_SID
                                                                   and MASTER_TYPE = ''RS_CONTRACT''    
                                                WHERE exists (select 1 from [UDF_SPLITSTRING](@DISCOUNT, '','') hd where hd.token=R.RS_CONTRACT_SID
                                                ) or nullif(@DISCOUNT,'''') is null')

          INSERT INTO #RS_INFO
          EXEC Sp_executesql
            @sql,
            N'@DISCOUNT varchar(max),@PROGRAM varcHar(100)',
            @DISCOUNT=@DISCOUNT,
            @PROGRAM=@PROGRAM

          --- select * from #RS_INFO
          IF Object_id('TEMPDB..#TEMP1') IS NOT NULL
            DROP TABLE #TEMP1

          CREATE TABLE #TEMP1
            (
               [HIERARCHY_NO]        [VARCHAR](8000) NOT NULL,
               [RS_CONTRACT_SID]     [INT] NOT NULL,
               [PERIOD]              [SMALLINT] NOT NULL,
               [YEAR]                [SMALLINT] NOT NULL,
               MASSUPDATE_FIELD      [NUMERIC](22, 6) NULL,
               [DEDUCTION_INCLUSION] [BIT] NULL,
               [INDICATOR]           [BIT] NULL
            )

          IF Object_id('TEMPDB..#Filter') IS NOT NULL
            DROP TABLE #Filter

          CREATE TABLE #Filter
            (
               CCP_DETAILS_SID INT,
               filter_ccp      int
            )

			IF Object_id('TEMPDB..#Filter_CCPD') IS NOT NULL
            DROP TABLE #Filter_CCPD

          CREATE TABLE #Filter_CCPD
            (
               CCP_DETAILS_SID INT,
			   RS_CONTRACT_SID INT,
               filter_ccp      int
            )

          DECLARE @customer_values VARCHAR(max)='',
                  @product_values  VARCHAR(max)='',
				  @DISCOUNT_VALUES VARCHAR(max)=''

          IF @FLAG = 'E'
             AND @SCREEN_NAME = 'SALES'
            BEGIN
                SET @product_values=(SELECT FIELD_VALUES
                                     FROM   nm_projection_selection
                                     WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                            AND SCREEN_NAME = 'Sales Projection'
                                            AND FIELD_NAME = 'productLevelValue'
                                            AND FIELD_VALUES <> ''
                                            AND FIELD_VALUES <> 'null')
                SET @customer_values=(SELECT FIELD_VALUES
                                      FROM   nm_projection_selection
                                      WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                             AND SCREEN_NAME = 'Sales Projection'
                                             AND FIELD_NAME = 'CustomerLevelValue'
                                             AND FIELD_VALUES <> ''
                                             AND FIELD_VALUES <> 'null')
                SET @sql=Concat('
Insert into #Filter(CCP_DETAILS_SID,filter_ccp)
select cd.CCP_DETAILS_SID,1 as filter_ccp from ', @CCP_HIERARCHY, ' A JOIN CCP_DETAILS CD ON CD.CCP_DETAILS_SID=A.CCP_DETAILS_SID
where 
exists (select 1 from udf_splitstring(@customer_values,'','') c where cd.COMPANY_MASTER_SID=c.token)
and exists (select 1 from udf_splitstring(@product_values,'','') p where cd.ITEM_MASTER_SID=p.token)')

                EXEC Sp_executesql
                  @sql,
                  N'@product_values varchar(max),@customer_values varcHar(max)',
                  @customer_values=@customer_values,
                  @product_values=@product_values

				  if not exists(select 1 from #Filter)
				  begin
				   SET @sql=Concat('
Insert into #Filter(CCP_DETAILS_SID,filter_ccp)
select CCP_DETAILS_SID,1 as filter_ccp from ', @CCP_HIERARCHY, '')

                EXEC Sp_executesql
                  @sql

				  end 
            END

 IF @FLAG = 'E'
             AND @SCREEN_NAME = 'DISCOUNT'
            BEGIN
                SET @product_values=(SELECT FIELD_VALUES
                                     FROM   nm_projection_selection
                                     WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                            AND SCREEN_NAME = 'Discount Projection'
                                            AND FIELD_NAME = 'productLevelValue'
                                            AND FIELD_VALUES <> ''
                                            AND FIELD_VALUES <> 'null')
                SET @customer_values=(SELECT FIELD_VALUES
                                      FROM   nm_projection_selection
                                      WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                             AND SCREEN_NAME = 'Discount Projection'
                                             AND FIELD_NAME = 'CustomerLevelValue'
                                             AND FIELD_VALUES <> ''
                                             AND FIELD_VALUES <> 'null')
				SET @DISCOUNT_VALUES =(SELECT FIELD_VALUES
                                      FROM   nm_projection_selection
                                      WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                             AND SCREEN_NAME = 'Discount Projection'
                                             AND FIELD_NAME = 'DeductionLevelValue'
                                             AND FIELD_VALUES <> ''
                                             AND FIELD_VALUES <> 'null')
                SET @sql=Concat('
Insert into #Filter_CCPD (CCP_DETAILS_SID,RS_CONTRACT_SID,filter_ccp)
select cd.CCP_DETAILS_SID,DM.RS_CONTRACT_SID,1 as filter_ccp from ', @CCP_HIERARCHY, ' A JOIN CCP_DETAILS CD ON CD.CCP_DETAILS_SID=A.CCP_DETAILS_SID
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID   
where 
exists (select 1 from udf_splitstring(@customer_values,'','') c where cd.COMPANY_MASTER_SID=c.token)
and exists (select 1 from udf_splitstring(@product_values,'','') p where cd.ITEM_MASTER_SID=p.token)
and exists (select 1 from udf_splitstring(@DISCOUNT_VALUES,'','') D where DM.RS_CONTRACT_SID=D.token) ')

                EXEC Sp_executesql
                  @sql,
                  N'@product_values varchar(max),@customer_values varcHar(max),@DISCOUNT_VALUES varchar(max)',
                  @customer_values=@customer_values,
                  @product_values=@product_values,
				  @DISCOUNT_VALUES=@DISCOUNT_VALUES

				  if not exists(select 1 from #Filter)
				  begin
				   SET @sql=Concat('
Insert into #Filter_CCPD (CCP_DETAILS_SID,RS_CONTRACT_SID,filter_ccp)
select A.CCP_DETAILS_SID,DM.RS_CONTRACT_SID,1 as filter_ccp from ', @CCP_HIERARCHY, ' A JOIN CCP_DETAILS CD ON CD.CCP_DETAILS_SID=A.CCP_DETAILS_SID
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID ')  

                EXEC Sp_executesql
                  @sql

				  end 
            END
			
          -----to identify the selected levels
          IF @FLAG = 'G'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @sql =Concat('

       INSERT INTO ', @CUSTOMER_TABLE_SALES, '(HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR,FILTER_CCP)        
       SELECT c.CUST_HIERARCHY_NO as HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL,
             NULL,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) ACTUAL_UNITS,
             c.SALES_INCLUSION,
             0    INDICATOR,
                                                1  FILTER_CCP
      FROM   ', @CCP_HIERARCHY, ' C
             --JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
             --  ON C.CUST_HIERARCHY_NO = RLD.HIERARCHY_NO 
             --     AND RELATIONSHIP_BUILDER_SID = ', @CUST_RELATIONSHIP_BUILDER_SID, '
             --     AND VERSION_NO = ', @PROJECTION_CUST_VERSION, '
                  --AND LEVEL_NO = 6
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,
                               SUM(SALES)    SALES,
                               SUM(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] AD
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY AD.CCP_DETAILS_SID,
                                  PERIOD_SID) AD
                    ON A.PERIOD_SID = AD.PERIOD_SID
                       AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
                     LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.CUST_HIERARCHY_NO,
             PERIOD,
             YEAR,
             isnull(AVG(ACCOUNT_GROWTH),0),
             isnull(AVG(PRODUCT_GROWTH),0),
             SUM(PROJECTION_SALES),
             SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR,
                                                1  FILTER_CCP
      FROM    ', @CCP_HIERARCHY, ' C
             
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               ACD.CCP_DETAILS_SID
                        FROM   NM_SALES_PROJECTION SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                       ON SPM.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID AND ACD.PROJECTION_MASTER_SID=SPM.PROJECTION_MASTER_SID
                                                                  AND ( SPM.ACCOUNT_GROWTH IS NOT NULL
                                                 OR SPM.PRODUCT_GROWTH IS NOT NULL
                                                 OR SPM.PROJECTION_SALES IS NOT NULL
                                                 OR SPM.PROJECTION_UNITS IS NOT NULL )) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
                                     LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION
                           ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'G'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW = 'P'
            BEGIN
                SET @sql =Concat(' 
       INSERT INTO ', @PRODUCT_TABLE_SALES, '(HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR,FILTER_CCP)
       SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.PERIOD,
             A.YEAR,
             NULL,
             NULL,
             SUM(( SALES ))    SALES,
             SUM(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) ACTUAL_UNITS,
             C.SALES_INCLUSION,
             0                 INDICATOR,
                                                1 FILTER_CCP
      FROM   ', @CCP_HIERARCHY, ' C
             
             --JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
            --   ON C.PROD_HIERARCHY_NO = RLD.HIERARCHY_NO 
              --    AND RELATIONSHIP_BUILDER_SID = ', @PROD_RELATIONSHIP_BUILDER_SID, '
              --    AND VERSION_NO = ', @PROJECTION_PROD_VERSION, '
                  --AND LEVEL_NO = 6
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,
                               SUM(SALES)    SALES,
                               SUM(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] AD
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY AD.CCP_DETAILS_SID,
                                  PERIOD_SID) AD
                    ON A.PERIOD_SID = AD.PERIOD_SID
                       AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
                     LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.PROD_HIERARCHY_NO,
             period,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR,
                                                1  FILTER_CCP
      FROM    ', @CCP_HIERARCHY, ' C
             
            
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               ACD.CCP_DETAILS_SID
                        FROM   NM_SALES_PROJECTION SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                       ON SPM.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID AND ACD.PROJECTION_MASTER_SID=SPM.PROJECTION_MASTER_SID
                                                                  AND ( spm.ACCOUNT_GROWTH IS NOT NULL
                                                 OR spm.PRODUCT_GROWTH IS NOT NULL
                                                 OR spm.PROJECTION_SALES IS NOT NULL
                                                 OR spm.PROJECTION_UNITS IS NOT NULL )) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
                           LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                period,
                YEAR,
                C.SALES_INCLUSION')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'G'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @sql =Concat(' 
       INSERT INTO ', @CUSTOM_TABLE_SALES, '(HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR,FILTER_CCP)
       SELECT c.ROWID,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL,
             NULL,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) ACTUAL_UNITS,
             C1.SALES_INCLUSION,
             0                 INDICATOR,
                                                1 FILTER_CCP
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
                                  LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                     WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                     --   AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY c.ROWID,
                period,
                YEAR,
                c1.SALES_INCLUSION
      UNION 
      SELECT C.ROWID,
             period,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C1.SALES_INCLUSION,
             1 INDICATOR,
                                                1  FILTER_CCP
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               ACD.CCP_DETAILS_SID
                        FROM   NM_SALES_PROJECTION SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                      ON SPM.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID AND ACD.PROJECTION_MASTER_SID=SPM.PROJECTION_MASTER_SID
                                                                  AND ( spm.ACCOUNT_GROWTH IS NOT NULL
                                                 OR spm.PRODUCT_GROWTH IS NOT NULL
                                                 OR spm.PROJECTION_SALES IS NOT NULL
                                                 OR spm.PROJECTION_UNITS IS NOT NULL )) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
                                     LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                      WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                     --   AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                period,
                YEAR,
                c1.SALES_INCLUSION')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'E'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @sql =Concat('

       INSERT INTO ', @CUSTOMER_TABLE_SALES, '(HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR,FILTER_CCP)        
       SELECT c.CUST_HIERARCHY_NO as HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL,
             NULL,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) ACTUAL_UNITS,
             c.SALES_INCLUSION,
             0                 INDICATOR,
                   max(isnull(fccp.filter_ccp,0))        FILTER_CCP
      FROM   ', @CCP_HIERARCHY, ' C
            left join #Filter fccp on fccp.CCP_DETAILS_SID=c.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
                                     LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY c.CUST_HIERARCHY_NO,
                period,
                YEAR,
                c.SALES_INCLUSION
      UNION ALL
      SELECT C.CUST_HIERARCHY_NO,
             period,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             c.SALES_INCLUSION,
             1 INDICATOR,
                  max(isnull(fccp.filter_ccp,0))         FILTER_CCP
      FROM    ', @CCP_HIERARCHY, ' C
           left join #Filter fccp on fccp.CCP_DETAILS_SID=c.CCP_DETAILS_SID                     
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN   NM_SALES_PROJECTION SPM
                              
                    ON C.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID    AND SPM.PROJECTION_MASTER_SID= ', @PROJECTION_MASTER_SID, ' AND ( spm.ACCOUNT_GROWTH IS NOT NULL
                                                 OR spm.PRODUCT_GROWTH IS NOT NULL
                                                 OR spm.PROJECTION_SALES IS NOT NULL
                                                 OR spm.PROJECTION_UNITS IS NOT NULL )
                       AND B.PERIOD_SID = SPM.PERIOD_SID
                                  LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                period,
                YEAR,
                c.SALES_INCLUSION')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'E'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW = 'P'
            BEGIN
                SET @sql =Concat(' 
       INSERT INTO ', @PRODUCT_TABLE_SALES, '(HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR,FILTER_CCP)
       SELECT c.PROD_HIERARCHY_NO as HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL,
             NULL,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) ACTUAL_UNITS,
             c.SALES_INCLUSION,
             0                 INDICATOR,
              max(isnull(fccp.filter_ccp,0))         FILTER_CCP
      FROM   ', @CCP_HIERARCHY, ' C
            left join #Filter fccp on fccp.CCP_DETAILS_SID=c.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY c.PROD_HIERARCHY_NO,
                period,
                YEAR,
                c.SALES_INCLUSION
      UNION ALL
      SELECT C.PROD_HIERARCHY_NO,
             period,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)),
             c.SALES_INCLUSION,
             1 INDICATOR,
              max(isnull(fccp.filter_ccp,0))         FILTER_CCP
      FROM    ', @CCP_HIERARCHY, ' C
            left join #Filter fccp on fccp.CCP_DETAILS_SID=c.CCP_DETAILS_SID
             
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
              LEFT JOIN   NM_SALES_PROJECTION SPM
                              
                    ON C.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID AND SPM.PROJECTION_MASTER_SID= ', @PROJECTION_MASTER_SID, ' and  ( spm.ACCOUNT_GROWTH IS NOT NULL
                                                 OR spm.PRODUCT_GROWTH IS NOT NULL
                                                 OR spm.PROJECTION_SALES IS NOT NULL
                                                 OR spm.PROJECTION_UNITS IS NOT NULL )
                       AND B.PERIOD_SID = SPM.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                period,
                YEAR,
                c.SALES_INCLUSION')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'E'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @sql =Concat(' 
       INSERT INTO ', @CUSTOM_TABLE_SALES, '(HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR,FILTER_CCP)
       SELECT c.ROWID,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL,
             NULL,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) ACTUAL_UNITS,
             c1.SALES_INCLUSION,
             0                 INDICATOR,
               max(isnull(fccp.filter_ccp,0))         FILTER_CCP
      FROM   ',@CUSTOM_CCP_SALES,' C
                  JOIN ', @CCP_HIERARCHY, ' C1
                     ON C1.CCP_DETAILS_SID = C.CCP_DETAILS_SID
				left join #Filter fccp on fccp.CCP_DETAILS_SID=c1.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID

                                      WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                     --   AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '                  
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                PERIOD,
                YEAR,
                C1.SALES_INCLUSION
      UNION ALL
     SELECT C.ROWID,
             period,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             SUM(PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C1.SALES_INCLUSION,
             1 INDICATOR,
               max(isnull(fccp.filter_ccp,0))         FILTER_CCP
      FROM    ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
			   left join #Filter fccp on fccp.CCP_DETAILS_SID=c1.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
           LEFT JOIN   NM_SALES_PROJECTION SPM
                              
                    ON C1.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID AND SPM.PROJECTION_MASTER_SID= ', @PROJECTION_MASTER_SID, ' and  ( spm.ACCOUNT_GROWTH IS NOT NULL
                                                 OR spm.PRODUCT_GROWTH IS NOT NULL
                                                 OR spm.PROJECTION_SALES IS NOT NULL
                                                 OR spm.PROJECTION_UNITS IS NOT NULL )
                       AND B.PERIOD_SID = SPM.PERIOD_SID
                                     LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                      WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        --AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                period,
                YEAR,
                c1.SALES_INCLUSION')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPA'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'C' )
            BEGIN
			
                SET @sql =Concat('

       INSERT INTO ', @CUSTOMER_TABLE_SALES, '(HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR,filter_ccp)        
       select HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR,filter_ccp from ( SELECT c.CUST_HIERARCHY_NO as HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL ACCOUNT_GROWTH,
             NULL PRODUCT_GROWTH,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
             c.SALES_INCLUSION,
             0                 INDICATOR,1 filter_ccp
      FROM   ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                       WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY c.CUST_HIERARCHY_NO,
                period,
                YEAR,
                c.SALES_INCLUSION
      UNION ALL
      SELECT C.CUST_HIERARCHY_NO,
             period,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)),
             c.SALES_INCLUSION,
             1 INDICATOR,1 filter_ccp
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               ACD.CCP_DETAILS_SID
                        FROM   NM_SALES_PROJECTION SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                       ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID
                                                                  AND ( spm.ACCOUNT_GROWTH IS NOT NULL
                                                 OR spm.PRODUCT_GROWTH IS NOT NULL
                                                 OR spm.PROJECTION_SALES IS NOT NULL
                                                 OR spm.PROJECTION_UNITS IS NOT NULL )) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                period,
                YEAR,
                c.SALES_INCLUSION)A WHERE NOT EXISTS (
                           SELECT 1 FROM  ', @CUSTOM_TABLE_SALES, ' B WHERE B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           )')

                EXEC Sp_executesql
                  @sql

                SET @SQL =Concat('
                UPDATE      B SET B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       ( SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.PERIOD,
             A.YEAR,
             NULL ACCOUNT_GROWTH,
             NULL PRODUCT_GROWTH,
             SUM(( SALES ))    SALES,
             SUM(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1) )) UNITS,
             C.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,
                               SUM(SALES)    SALES,
                               SUM(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] AD
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY AD.CCP_DETAILS_SID,
                                  PERIOD_SID) AD
                    ON A.PERIOD_SID = AD.PERIOD_SID
                       AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.CUST_HIERARCHY_NO,
             PERIOD,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             SUM(PROJECTION_SALES),
             SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               ACD.CCP_DETAILS_SID
                        FROM   NM_SALES_PROJECTION SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                       ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID
                                                                  AND ( SPM.ACCOUNT_GROWTH IS NOT NULL
                                                 OR SPM.PRODUCT_GROWTH IS NOT NULL
                                                 OR SPM.PROJECTION_SALES IS NOT NULL
                                                 OR SPM.PROJECTION_UNITS IS NOT NULL )) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
               PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOM_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           WHERE ISNULL(A.ACCOUNT_GROWTH,0)<>ISNULL(B.ACCOUNT_GROWTH,0)
                           AND ISNULL(A.PRODUCT_GROWTH,0)<>ISNULL(B.PRODUCT_GROWTH,0) 
                           AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0)  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPA'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW = 'P'
            BEGIN
                SET @sql =Concat(' 
       INSERT INTO ', @PRODUCT_TABLE_SALES, '(HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR,filter_ccp)
       select HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR,filter_ccp from (SELECT c.PROD_HIERARCHY_NO as HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL ACCOUNT_GROWTH,
             NULL PRODUCT_GROWTH,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
             C.SALES_INCLUSION,
             0                 INDICATOR,1 filter_ccp
      FROM   ', @CCP_HIERARCHY, ' C
             
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY c.PROD_HIERARCHY_NO,
                period,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.PROD_HIERARCHY_NO,
             period,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR,1 filter_ccp
      FROM    ', @CCP_HIERARCHY, ' C
             
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               ACD.CCP_DETAILS_SID
                        FROM   NM_SALES_PROJECTION SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                       ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID
                                                                  AND ( spm.ACCOUNT_GROWTH IS NOT NULL
                                                 OR spm.PRODUCT_GROWTH IS NOT NULL
                                                 OR spm.PROJECTION_SALES IS NOT NULL
                                                 OR spm.PROJECTION_UNITS IS NOT NULL )) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                period,
                YEAR,
                C.SALES_INCLUSION)A WHERE NOT EXISTS (
                           SELECT 1 FROM  ', @PRODUCT_TABLE_SALES, ' B WHERE B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           )')

                EXEC Sp_executesql
                  @sql

                SET @SQL =Concat('
                UPDATE      B SET B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM  (SELECT c.PROD_HIERARCHY_NO as HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL ACCOUNT_GROWTH,
             NULL PRODUCT_GROWTH,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
             C.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY c.PROD_HIERARCHY_NO,
                period,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.PROD_HIERARCHY_NO,
             period,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               ACD.CCP_DETAILS_SID
                        FROM   NM_SALES_PROJECTION SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                       ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID
                                                                  AND ( spm.ACCOUNT_GROWTH IS NOT NULL
                                                 OR spm.PRODUCT_GROWTH IS NOT NULL
                                                 OR spm.PROJECTION_SALES IS NOT NULL
                                                 OR spm.PROJECTION_UNITS IS NOT NULL )) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                period,
                YEAR,
                C.SALES_INCLUSION)A 
                           join ', @PRODUCT_TABLE_SALES, '  B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           WHERE ISNULL(A.ACCOUNT_GROWTH,0)<>ISNULL(B.ACCOUNT_GROWTH,0)
                           AND ISNULL(A.PRODUCT_GROWTH,0)<>ISNULL(B.PRODUCT_GROWTH,0) 
                           AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPA'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @sql =Concat(' 
       INSERT INTO ', @CUSTOM_TABLE_SALES, '(HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR,filter_ccp)
       select HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR,filter_ccp from (SELECT c.ROWID HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL ACCOUNT_GROWTH,
             NULL PRODUCT_GROWTH,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
             C1.SALES_INCLUSION,
             0                 INDICATOR,1 filter_ccp
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                    WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                     --   AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY c.ROWID,
                period,
                YEAR,
                C1.SALES_INCLUSION
      UNION ALL
      SELECT C.ROWID,
             period,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C1.SALES_INCLUSION,
             1 INDICATOR,1 filter_ccp
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               ACD.CCP_DETAILS_SID
                        FROM   NM_SALES_PROJECTION SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                       ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID
                                                                  AND ( spm.ACCOUNT_GROWTH IS NOT NULL
                                                 OR spm.PRODUCT_GROWTH IS NOT NULL
                                                 OR spm.PROJECTION_SALES IS NOT NULL
                                                 OR spm.PROJECTION_UNITS IS NOT NULL )
                                                              ) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                      WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                      --  AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                period,
                YEAR,
                C1.SALES_INCLUSION)A WHERE NOT EXISTS (
                           SELECT 1 FROM  ', @CUSTOM_TABLE_SALES, ' B WHERE B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           )')

                EXEC Sp_executesql
                  @sql

                --  select @sql 
                --   SET @sql =Concat(' 
                --INSERT INTO ', @CUSTOM_TABLE_SALES, '(CUSTOM_VIEW_MASTER_SID,HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR)
                SET @SQL =Concat('
                UPDATE      B SET B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM  (SELECT c.ROWID,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL ACCOUNT_GROWTH,
             NULL PRODUCT_GROWTH,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
            C1.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                     WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY c.ROWID,
                period,
                YEAR,
                C1.SALES_INCLUSION
      UNION 
      SELECT C.ROWID,
             period,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C1.SALES_INCLUSION,
             1 INDICATOR
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                              ACD.CCP_DETAILS_SID
                        FROM   NM_SALES_PROJECTION SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                       ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID
                                                                  AND ( spm.ACCOUNT_GROWTH IS NOT NULL
                                                 OR spm.PRODUCT_GROWTH IS NOT NULL
                                                 OR spm.PROJECTION_SALES IS NOT NULL
                                                 OR spm.PROJECTION_UNITS IS NOT NULL )
                                                              ) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                      WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        --AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                period,
                YEAR,
                C1.SALES_INCLUSION)A
                           join ', @CUSTOM_TABLE_SALES, '  B ON B.HIERARCHY_NO=A.ROWID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           --WHERE ISNULL(A.ACCOUNT_GROWTH,0)<>ISNULL(B.ACCOUNT_GROWTH,0)
                           --AND ISNULL(A.PRODUCT_GROWTH,0)<>ISNULL(B.PRODUCT_GROWTH,0) 
                           --AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           --AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) 
                           ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPD'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @sql =Concat('

       delete b from ', @CUSTOMER_TABLE_SALES, ' B where not exists(
       select 1 from  ( SELECT c.CUST_HIERARCHY_NO as HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,c.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ', @CCP_HIERARCHY, ' C
             --JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
             --  ON C.CUST_HIERARCHY_NO = RLD.HIERARCHY_NO 
              --    AND RELATIONSHIP_BUILDER_SID = ', @CUST_RELATIONSHIP_BUILDER_SID, '
              --    AND VERSION_NO = ', @PROJECTION_CUST_VERSION, '
                  --AND LEVEL_NO = 6
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
      GROUP  BY c.CUST_HIERARCHY_NO,
                period,
                YEAR,
                c.SALES_INCLUSION
      UNION ALL

      SELECT C.CUST_HIERARCHY_NO as HIERARCHY_NO,
             period,
             YEAR,
             c.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
               ON C.CUST_HIERARCHY_NO = RLD.HIERARCHY_NO 
                  AND RELATIONSHIP_BUILDER_SID = ', @CUST_RELATIONSHIP_BUILDER_SID, '
                  AND VERSION_NO = ', @PROJECTION_CUST_VERSION, '
                 -- AND LEVEL_NO = 6
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
      GROUP  BY C.CUST_HIERARCHY_NO,
                period,
                YEAR,
                c.SALES_INCLUSION)A 
                           where B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR)')

                EXEC Sp_executesql
                  @sql

                SET @SQL =Concat('
                UPDATE      B SET B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       ( SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.PERIOD,
             A.YEAR,
             NULL ACCOUNT_GROWTH,
             NULL PRODUCT_GROWTH,
             SUM(( SALES ))    SALES,
             SUM(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
             C.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ', @CCP_HIERARCHY, ' C
             JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
               ON C.CUST_HIERARCHY_NO = RLD.HIERARCHY_NO 
                  AND RELATIONSHIP_BUILDER_SID = ', @CUST_RELATIONSHIP_BUILDER_SID, '
                  AND VERSION_NO = ', @PROJECTION_CUST_VERSION, '
                  --AND LEVEL_NO = 6
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,
                               SUM(SALES)    SALES,
                               SUM(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] AD
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY AD.CCP_DETAILS_SID,
                                  PERIOD_SID) AD
                    ON A.PERIOD_SID = AD.PERIOD_SID
                       AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.CUST_HIERARCHY_NO,
             PERIOD,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             SUM(PROJECTION_SALES),
             SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
               ON C.CUST_HIERARCHY_NO = RLD.HIERARCHY_NO 
                  AND RELATIONSHIP_BUILDER_SID = ', @CUST_RELATIONSHIP_BUILDER_SID, '
                  AND VERSION_NO = ', @PROJECTION_CUST_VERSION, '
                 -- AND LEVEL_NO = 6
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                              ACD.CCP_DETAILS_SID
                        FROM   NM_SALES_PROJECTION SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                       ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID
                                                                  AND ( SPM.ACCOUNT_GROWTH IS NOT NULL
                                                 OR SPM.PRODUCT_GROWTH IS NOT NULL
                                                 OR SPM.PROJECTION_SALES IS NOT NULL
                                                 OR SPM.PROJECTION_UNITS IS NOT NULL )) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOM_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           WHERE ISNULL(A.ACCOUNT_GROWTH,0)<>ISNULL(B.ACCOUNT_GROWTH,0)
                           AND ISNULL(A.PRODUCT_GROWTH,0)<>ISNULL(B.PRODUCT_GROWTH,0) 
                           AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0)  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPD'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW = 'P'
            BEGIN
                SET @sql =Concat(' 
       delete b from ', @PRODUCT_TABLE_SALES, ' B where not exists(
       select 1 from  (SELECT c.PROD_HIERARCHY_NO as HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
            A.YEAR,
             C.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ', @CCP_HIERARCHY, ' C
             
           --  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
           --    ON C.PROD_HIERARCHY_NO = RLD.HIERARCHY_NO 
           --       AND RELATIONSHIP_BUILDER_SID = ', @PROD_RELATIONSHIP_BUILDER_SID, '
           --       AND VERSION_NO = ', @PROJECTION_PROD_VERSION, '
                  --AND LEVEL_NO = 6
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
      GROUP  BY c.PROD_HIERARCHY_NO,
                period,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.PROD_HIERARCHY_NO,
             period,
             YEAR,             
             C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             
             JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
               ON C.PROD_HIERARCHY_NO = RLD.HIERARCHY_NO 
                  AND RELATIONSHIP_BUILDER_SID = ', @PROD_RELATIONSHIP_BUILDER_SID, '
                  AND VERSION_NO = ', @PROJECTION_PROD_VERSION, '
                 -- AND LEVEL_NO = 6
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                period,
                YEAR,
                C.SALES_INCLUSION)A 
                           where B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR)')

                EXEC Sp_executesql
                  @sql

                SET @SQL =Concat('
                UPDATE      B SET B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM  (SELECT c.PROD_HIERARCHY_NO as HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.year,
             NULL ACCOUNT_GROWTH,
             NULL PRODUCT_GROWTH,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
             C.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ', @CCP_HIERARCHY, ' C
             JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
               ON C.PROD_HIERARCHY_NO = RLD.HIERARCHY_NO 
                  AND RELATIONSHIP_BUILDER_SID = ', @PROD_RELATIONSHIP_BUILDER_SID, '
                  AND VERSION_NO = ', @PROJECTION_PROD_VERSION, '
                  --AND LEVEL_NO = 6
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY c.PROD_HIERARCHY_NO,
                period,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.PROD_HIERARCHY_NO,
             period,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             
             JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
               ON C.PROD_HIERARCHY_NO = RLD.HIERARCHY_NO 
                  AND RELATIONSHIP_BUILDER_SID = ', @PROD_RELATIONSHIP_BUILDER_SID, '
                  AND VERSION_NO = ', @PROJECTION_PROD_VERSION, '
                 -- AND LEVEL_NO = 6
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               ACD.CCP_DETAILS_SID
                        FROM   NM_SALES_PROJECTION SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                       ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID
                                                                  AND ( spm.ACCOUNT_GROWTH IS NOT NULL
                                                 OR spm.PRODUCT_GROWTH IS NOT NULL
                                                 OR spm.PROJECTION_SALES IS NOT NULL
                                                 OR spm.PROJECTION_UNITS IS NOT NULL )) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                period,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN ', @PRODUCT_TABLE_SALES, '  B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           WHERE ISNULL(A.ACCOUNT_GROWTH,0)<>ISNULL(B.ACCOUNT_GROWTH,0)
                           AND ISNULL(A.PRODUCT_GROWTH,0)<>ISNULL(B.PRODUCT_GROWTH,0) 
                           AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPD'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @SQL =Concat(' 
       DELETE B FROM ', @CUSTOM_TABLE_SALES, ' B WHERE NOT EXISTS(
       SELECT 1 FROM  (SELECT C.ROWID,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.PERIOD,
             A.YEAR,
             C1.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ',@CUSTOM_CCP_SALES,' C
                  JOIN ', @CCP_HIERARCHY, ' C1
                     ON C1.CCP_DETAILS_SID = C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
                                     WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                     --   AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.HIERARCHY_NO,
                PERIOD,
                YEAR,
                C1.SALES_INCLUSION
      UNION 
      SELECT C.HIERARCHY_NO,
             PERIOD,
             YEAR,
             C1.SALES_INCLUSION,
             1 INDICATOR
      FROM   ',@CUSTOM_CCP_SALES,' C
                  JOIN ', @CCP_HIERARCHY, ' C1
                     ON C1.CCP_DETAILS_SID = C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
                                      WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                PERIOD,
                YEAR,
                C1.SALES_INCLUSION)A
                           WHERE B.ROWID=A.ROWID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR)')

                EXEC Sp_executesql
                  @sql

                --   SET @sql =Concat(' 
                --INSERT INTO ', @CUSTOM_TABLE_SALES, '(CUSTOM_VIEW_MASTER_SID,HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR)
                SET @SQL =Concat('
                UPDATE      B SET B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM  (SELECT c.HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL ACCOUNT_GROWTH,
             NULL PRODUCT_GROWTH,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
             C1.SALES_INCLUSION,
            0                 INDICATOR
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                     WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY c.HIERARCHY_NO,
                period,
                YEAR,
                c1.SALES_INCLUSION
      UNION ALL
      SELECT C.HIERARCHY_NO,
             period,
             YEAR,
             isnull(Avg(ACCOUNT_GROWTH),0),
             isnull(Avg(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)),
             c1.SALES_INCLUSION,
             1 INDICATOR
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               ACD.CCP_DETAILS_SID
                       FROM   NM_SALES_PROJECTION SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                       ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID
                                                                  AND ( spm.ACCOUNT_GROWTH IS NOT NULL
                                                 OR spm.PRODUCT_GROWTH IS NOT NULL
                                                 OR spm.PROJECTION_SALES IS NOT NULL
                                                 OR spm.PROJECTION_UNITS IS NOT NULL )
                                                              ) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                      WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        --AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.HIERARCHY_NO,
                period,
                YEAR,
                c1.SALES_INCLUSION)A
                           join ', @CUSTOM_TABLE_SALES, '  B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           WHERE ISNULL(A.ACCOUNT_GROWTH,0)<>ISNULL(B.ACCOUNT_GROWTH,0)
                           AND ISNULL(A.PRODUCT_GROWTH,0)<>ISNULL(B.PRODUCT_GROWTH,0) 
                           AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) 
                           ')

                EXEC Sp_executesql
                  @sql
            END

         IF @FLAG = 'M'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @SQL =Concat('
              UPDATE      B SET         B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.CUST_HIERARCHY_NO as HIERARCHY_NO,
             PERIOD,
             YEAR,                
               ACCOUNT_GROWTH=isnull(AVG(ACCOUNT_GROWTH),0),
               PRODUCT_GROWTH=isnull(AVG(PRODUCT_GROWTH),0),
               SALES=SUM(PROJECTION_SALES),
               UNITS=SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
                       C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE PERIOD_SID BETWEEN ', @START_PERIOD, 'AND ', @END_PERIOD, ')B
          JOIN ', @S_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1
       JOIN ', @S_PROJECTION_TABLE, ' SP
         ON SP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID           
               and sp.PERIOD_SID=b.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                --SELECT @sql
                EXEC Sp_executesql
                  @sql
            END

           IF @FLAG = 'M'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'P' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET  B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.PROD_HIERARCHY_NO as HIERARCHY_NO,
             PERIOD,
             YEAR,                
               ACCOUNT_GROWTH=isnull(AVG(ACCOUNT_GROWTH),0),
               PRODUCT_GROWTH=isnull(AVG(PRODUCT_GROWTH),0),
               SALES=SUM(PROJECTION_SALES),
               UNITS=SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
                       C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE PERIOD_SID BETWEEN ', @START_PERIOD, 'AND ', @END_PERIOD, ')B
          JOIN ', @S_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1
       JOIN ', @S_PROJECTION_TABLE, ' SP
         ON SP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID           
               and sp.PERIOD_SID=b.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @PRODUCT_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END


IF @FLAG = 'M'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @SQL =Concat('
               UPDATE      B SET         B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.ROWID ,
             PERIOD,
             YEAR,                
               ACCOUNT_GROWTH=isnull(AVG(ACCOUNT_GROWTH),0),
               PRODUCT_GROWTH=isnull(AVG(PRODUCT_GROWTH),0),
               SALES=SUM(PROJECTION_SALES),
               UNITS=SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
                       C1.SALES_INCLUSION,
             1 INDICATOR
      FROM    ',@CUSTOM_CCP_SALES,'  C
                  JOIN ', @CCP_HIERARCHY, ' C1
                             ON C1.CCP_DETAILS_SID = C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE PERIOD_SID BETWEEN ', @START_PERIOD, 'AND ', @END_PERIOD, ')B
          JOIN ', @S_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1
       JOIN ', @S_PROJECTION_TABLE, ' SP
         ON SP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID           
               and sp.PERIOD_SID=b.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                PERIOD,
                YEAR,
                C1.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOM_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.ROWID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                --   SELECT @sql
                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'R'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET         B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.CUST_HIERARCHY_NO as HIERARCHY_NO,
             PERIOD,
             YEAR,                
               ACCOUNT_GROWTH=isnull(AVG(ACCOUNT_GROWTH),0),
               PRODUCT_GROWTH=isnull(AVG(PRODUCT_GROWTH),0),
               SALES=SUM(PROJECTION_SALES),
               UNITS=SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
                       C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE PERIOD_SID BETWEEN ', @START_PERIOD, 'AND ', @END_PERIOD, ')B
						  JOIN ', @S_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND FILTER_CCP = 1
       JOIN ', @S_PROJECTION_TABLE, ' SP
         ON SP.CCP_DETAILS_SID = C.CCP_DETAILS_SID           
               and sp.PERIOD_SID=b.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'R'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'p' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET  B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.PROD_HIERARCHY_NO as HIERARCHY_NO,
             PERIOD,
             YEAR,                
               ACCOUNT_GROWTH=isnull(AVG(ACCOUNT_GROWTH),0),
               PRODUCT_GROWTH=isnull(AVG(PRODUCT_GROWTH),0),
               SALES=SUM(PROJECTION_SALES),
               UNITS=SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
                       C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE PERIOD_SID BETWEEN ', @START_PERIOD, 'AND ', @END_PERIOD, ')B
						  JOIN ', @S_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND FILTER_CCP = 1
       JOIN ', @S_PROJECTION_TABLE, ' SP
         ON SP.CCP_DETAILS_SID = C.CCP_DETAILS_SID           
               and sp.PERIOD_SID=b.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @PRODUCT_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'R'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET         B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.ROWID as HIERARCHY_NO,
             PERIOD,
             YEAR,                
               ACCOUNT_GROWTH=isnull(AVG(ACCOUNT_GROWTH),0),
               PRODUCT_GROWTH=isnull(AVG(PRODUCT_GROWTH),0),
               SALES=SUM(PROJECTION_SALES),
               UNITS=SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
                       C1.SALES_INCLUSION,
             1 INDICATOR
      FROM    ',@CUSTOM_CCP_SALES,'  C
                  JOIN ', @CCP_HIERARCHY, ' C1
                             ON C1.CCP_DETAILS_SID = C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE PERIOD_SID BETWEEN ', @START_PERIOD, 'AND ', @END_PERIOD, ')B
						  JOIN ', @S_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C1.CCP_DETAILS_SID
            AND FILTER_CCP = 1
       JOIN ', @S_PROJECTION_TABLE, ' SP
         ON SP.CCP_DETAILS_SID = C1.CCP_DETAILS_SID           
               and sp.PERIOD_SID=b.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                PERIOD,
                YEAR,
                C1.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOM_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'ADJ'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'C' )
            BEGIN
			
                SET @SQL =Concat('
                UPDATE      B SET  B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.CUST_HIERARCHY_NO as HIERARCHY_NO,
             PERIOD,
             YEAR,                
               ACCOUNT_GROWTH=isnull(AVG(ACCOUNT_GROWTH),0),
               PRODUCT_GROWTH=isnull(AVG(PRODUCT_GROWTH),0),
               SALES=SUM(PROJECTION_SALES),
               UNITS=SUM(PROJECTION_UNITS / COALESCE(NULLIF(UOM_VALUE, 0),1)),
                       C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                                         WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '
                          --WHERE  concat(', LEFT(@FREQUENCY, 1) + 'Period' + ' ,', 'YEAR) BETWEEN ''', @START_PERIOD, ''' AND ''', @END_PERIOD, '''
                                           )B
       JOIN ', @S_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1 ',case when @FILTER_CCP_adj =1 then ' and spm.FILTER_CCP=1 ' else '' end ,'
       JOIN ', @S_PROJECTION_TABLE, ' SP
         ON SP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID           
               AND SP.PERIOD_SID=B.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                                                --WHERE ISNULL(A.ACCOUNT_GROWTH,0)<>ISNULL(B.ACCOUNT_GROWTH,0)
                           --AND ISNULL(A.PRODUCT_GROWTH,0)<>ISNULL(B.PRODUCT_GROWTH,0) 
                           --AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           --AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) 
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'ADJ'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'p' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET  B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.PROD_HIERARCHY_NO as HIERARCHY_NO,
             PERIOD,
             YEAR,                
               ACCOUNT_GROWTH=isnull(AVG(ACCOUNT_GROWTH),0),
               PRODUCT_GROWTH=isnull(AVG(PRODUCT_GROWTH),0),
               SALES=SUM(PROJECTION_SALES),
               UNITS=SUM(PROJECTION_UNITS/ COALESCE(NULLIF(UOM_VALUE, 0),1)),
                       C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                                         WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '
                          --WHERE  concat(', LEFT(@FREQUENCY, 1) + 'Period' + ' ,', 'YEAR) BETWEEN ', @START_PERIOD, ' AND ', @END_PERIOD, '
                                           )B
       JOIN ', @S_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1 ',case when @FILTER_CCP_adj =1 then ' and spm.FILTER_CCP=1 ' else '' end ,'
       JOIN ', @S_PROJECTION_TABLE, ' SP
         ON SP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID           
               AND SP.PERIOD_SID=B.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @PRODUCT_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                          --WHERE ISNULL(A.ACCOUNT_GROWTH,0)<>ISNULL(B.ACCOUNT_GROWTH,0)
                           --AND ISNULL(A.PRODUCT_GROWTH,0)<>ISNULL(B.PRODUCT_GROWTH,0) 
                           --AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           --AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) 
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'ADJ'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET         B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.ROWID as HIERARCHY_NO,
             PERIOD,
             YEAR,                
               ACCOUNT_GROWTH=isnull(AVG(ACCOUNT_GROWTH),0),
               PRODUCT_GROWTH=isnull(AVG(PRODUCT_GROWTH),0),
               SALES=SUM(PROJECTION_SALES),
               UNITS=SUM(PROJECTION_UNITS/ COALESCE(NULLIF(UOM_VALUE, 0),1)),
                       C1.SALES_INCLUSION,
             1 INDICATOR
      FROM    ',@CUSTOM_CCP_SALES,'  C
                  JOIN ', @CCP_HIERARCHY, ' C1
                             ON C1.CCP_DETAILS_SID = C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                                           WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '
                         -- WHERE  concat(', LEFT(@FREQUENCY, 1) + 'Period' + ' ,', 'YEAR) BETWEEN ', @START_PERIOD, ' AND ', @END_PERIOD, '
                                           )B
       JOIN ', @S_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C1.CCP_DETAILS_SID
            AND CHECK_RECORD = 1 ',case when @FILTER_CCP_adj =1 then ' and spm.FILTER_CCP=1 ' else '' end ,'
       JOIN ', @S_PROJECTION_TABLE, ' SP
         ON SP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID           
               AND SP.PERIOD_SID=B.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        --AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                PERIOD,
                YEAR,
                C1.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOM_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                          --WHERE ISNULL(A.ACCOUNT_GROWTH,0)<>ISNULL(B.ACCOUNT_GROWTH,0)
                           --AND ISNULL(A.PRODUCT_GROWTH,0)<>ISNULL(B.PRODUCT_GROWTH,0) 
                           --AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           --AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) 
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'CAL'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET  B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.CUST_HIERARCHY_NO as HIERARCHY_NO,
             PERIOD,
             YEAR,                
               ACCOUNT_GROWTH=isnull(AVG(ACCOUNT_GROWTH),0),
               PRODUCT_GROWTH=isnull(AVG(PRODUCT_GROWTH),0),
               SALES=SUM(PROJECTION_SALES),
               UNITS=SUM(PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)),
                       C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                                         WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '
                          --WHERE  concat(', LEFT(@FREQUENCY, 1) + 'Period' + ' ,', 'YEAR) BETWEEN ', @START_PERIOD, ' AND ', @END_PERIOD, '
                                           )B
       JOIN ', @S_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1
       JOIN ', @S_PROJECTION_TABLE, ' SP
         ON SP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID           
               AND SP.PERIOD_SID=B.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'CAL'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'p' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET  B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.PROD_HIERARCHY_NO as HIERARCHY_NO,
             PERIOD,
             YEAR,                
               ACCOUNT_GROWTH=isnull(AVG(ACCOUNT_GROWTH),0),
               PRODUCT_GROWTH=isnull(AVG(PRODUCT_GROWTH),0),
               SALES=SUM(PROJECTION_SALES),
              UNITS=SUM(PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)),
                       C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                                         WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '
                          --WHERE  concat(', LEFT(@FREQUENCY, 1) + 'Period' + ' ,', 'YEAR) BETWEEN ', @START_PERIOD, ' AND ', @END_PERIOD, '
                                           )B
       JOIN ', @S_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1
       JOIN ', @S_PROJECTION_TABLE, ' SP
         ON SP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID           
               AND SP.PERIOD_SID=B.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @PRODUCT_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                          AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'CAL'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET         B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.ROWID as HIERARCHY_NO,
             PERIOD,
             YEAR,                
               ACCOUNT_GROWTH=isnull(AVG(ACCOUNT_GROWTH),0),
               PRODUCT_GROWTH=isnull(AVG(PRODUCT_GROWTH),0),
               SALES=SUM(PROJECTION_SALES),
               UNITS=SUM(PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)),
                       C1.SALES_INCLUSION,
             1 INDICATOR
      FROM    ',@CUSTOM_CCP_SALES,'  C
                  JOIN ', @CCP_HIERARCHY, ' C1
                             ON C1.CCP_DETAILS_SID = C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                                           WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '
                         -- WHERE  concat(', LEFT(@FREQUENCY, 1) + 'Period' + ' ,', 'YEAR) BETWEEN ', @START_PERIOD, ' AND ', @END_PERIOD, '
                                           )B
       JOIN ', @S_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C1.CCP_DETAILS_SID
            AND CHECK_RECORD = 1
       JOIN ', @S_PROJECTION_TABLE, ' SP
         ON SP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID           
               AND SP.PERIOD_SID=B.PERIOD_SID
        LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
               WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                PERIOD,
                YEAR,
                C1.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOM_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'G'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @sql =Concat('

       INSERT INTO ', @CUSTOMER_TABLE_DISCOUNT, ' (
       HIERARCHY_NO,
       RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DISCOUNT,
       SALES,
       UNITS,
       DEDUCTION_INCLUSION,
       INDICATOR,
       GROWTH,
	   FILTER_CCP
       )
SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       SUM((SALES)) SALES,
       SUM((QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH,
	   1  FILTER_CCP
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
       select CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,sum(DISCOUNT) DISCOUNT,sum(SALES) SALES, sum(QUANTITY) QUANTITY  from(SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN SALES
                           END) SALES,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN QUANTITY
                           END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION) a group by CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID
       ) ad
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.CUST_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
      --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       SUM(SPT.PROJECTION_SALES) SALES,
       SUM(SPT.PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH,
	   1  FILTER_CCP
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
              AND B.PERIOD_SID = SPT.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY C.CUST_HIERARCHY_NO,
      --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION
')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'G'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW = 'P'
            BEGIN
                SET @sql =Concat(' 
       INSERT INTO ', @PRODUCT_TABLE_DISCOUNT, '(
       HIERARCHY_NO,
       RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DISCOUNT,
       SALES,
       UNITS,
       DEDUCTION_INCLUSION,
       INDICATOR,
       GROWTH,
	   FILTER_CCP
       )
SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       SUM((SALES)) SALES,
       SUM((QUANTITY* COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH,
	   1  FILTER_CCP
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
--AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period


       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
      select CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,sum(DISCOUNT) DISCOUNT,sum(SALES) SALES, sum(QUANTITY) QUANTITY  from(SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN SALES
                           END) SALES,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN QUANTITY
                           END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION) a group by CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID
       ) ad
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.PROD_HIERARCHY_NO,
      --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       SUM(SPT.PROJECTION_SALES) SALES,
       SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH,
	   1  FILTER_CCP
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
-- AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
              AND B.PERIOD_SID = SPT.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.PROD_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'G'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @sql =Concat(' 
       INSERT INTO ', @CUSTOM_TABLE_DISCOUNT, '(HIERARCHY_NO
--,RS_CONTRACT_SID
,PERIOD
,YEAR
,DISCOUNT
--,SALES
--,UNITS
,DEDUCTION_INCLUSION
,INDICATOR
,GROWTH
,FILTER_CCP)
SELECT C.ROWID 
--,DM.RS_CONTRACT_SID
,A.PERIOD
,A.YEAR
,ISNULL(sum(DISCOUNT), 0) DISCOUNT,
      -- SUM((SALES)) SALES,
       --SUM((QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH,
	   1  FILTER_CCP
      FROM   ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (
      select CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,sum(DISCOUNT) DISCOUNT--,sum(SALES) SALES, sum(QUANTITY) QUANTITY  
                                                  from(SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
             -- SUM(CASE 
            --               WHEN QUANTITY_INCLUSION = ''Y''
             --                     THEN SALES
             --              END) SALES,
             -- SUM(CASE 
             --              WHEN QUANTITY_INCLUSION = ''Y''
             --                     THEN QUANTITY
              --             END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION) a group by CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID
       ) AD
       ON AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID 
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND A.PERIOD_SID = AD.PERIOD_SID
--LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
              WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID ,
              --  DM.RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       DM.DEDUCTION_INCLUSION

      UNION ALL

SELECT C.ROWID 
       -- ,DM.RS_CONTRACT_SID
     ,PERIOD
     ,YEAR
     ,SUM(PTD.PROJECTION_SALES) DISCOUNT,
       --SUM(SPT.PROJECTION_SALES) SALES,
       --SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH,
	   1  FILTER_CCP
FROM    ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
--LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
--ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
--              AND B.PERIOD_SID = SPT.PERIOD_SID
--LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
               -- DM.RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'E'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @sql =Concat('

       INSERT INTO ', @CUSTOMER_TABLE_DISCOUNT, '(HIERARCHY_NO,
       RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DISCOUNT,
       SALES,
       UNITS,
       DEDUCTION_INCLUSION,
       INDICATOR,
       GROWTH,
	   FILTER_CCP)       
       
SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
        --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       A.PERIOD,
       A.YEAR,
       ISNULL(SUM(DISCOUNT), 0) DISCOUNT,
       SUM((SALES)) SALES,
       SUM((QUANTITY* COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH,
	   MAX(ISNULL(FCCP.FILTER_CCP,0))        FILTER_CCP
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
LEFT JOIN #Filter_CCPD FCCP ON FCCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID
AND FCCP.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
       
       select CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,sum(DISCOUNT) DISCOUNT,sum(SALES) SALES, sum(QUANTITY) QUANTITY  from(SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN SALES
                           END) SALES,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN QUANTITY
                           END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION) a group by CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID
       ) ad
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.CUST_HIERARCHY_NO,
        --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

      UNION ALL
    
                           
       SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
        --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       B.PERIOD,
       B.YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       SUM(SPT.PROJECTION_SALES) SALES,
       SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH,
	   MAX(ISNULL(FCCP.FILTER_CCP,0))        FILTER_CCP
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
LEFT JOIN #Filter_CCPD FCCP ON FCCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID
AND FCCP.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD ON 
        C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
              AND B.PERIOD_SID = SPT.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.CUST_HIERARCHY_NO,
        --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION
       
       ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'E'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW = 'P'
            BEGIN
                SET @sql =Concat(' 
INSERT INTO ', @PRODUCT_TABLE_DISCOUNT, '(
       HIERARCHY_NO,
       RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DISCOUNT,
       SALES,
       UNITS,
       DEDUCTION_INCLUSION,
       INDICATOR,
       GROWTH,
	   FILTER_CCP
       )
SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
        --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL AS RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       SUM((SALES)) SALES,
       SUM((QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH,
	   MAX(ISNULL(FCCP.FILTER_CCP,0))        FILTER_CCP
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
LEFT JOIN #Filter_CCPD FCCP ON FCCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID
AND FCCP.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
       
       select CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,sum(DISCOUNT) DISCOUNT,sum(SALES) SALES, sum(QUANTITY) QUANTITY  from(SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN SALES
                           END) SALES,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN QUANTITY
                           END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION) a group by CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID
       ) ad
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.PROD_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       SUM(SPT.PROJECTION_SALES) SALES,
       SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH,
	   MAX(ISNULL(FCCP.FILTER_CCP,0))        FILTER_CCP
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
LEFT JOIN #Filter_CCPD FCCP ON FCCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID
AND FCCP.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
              AND B.PERIOD_SID = SPT.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.PROD_HIERARCHY_NO,
        --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION')

                --SELECT @sql
                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'E'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @sql =Concat(' 
       INSERT INTO ', @CUSTOM_TABLE_DISCOUNT, '(HIERARCHY_NO
--,RS_CONTRACT_SID
,PERIOD
,YEAR
,DISCOUNT
--,SALES
--,UNITS
,DEDUCTION_INCLUSION
,INDICATOR
,GROWTH
,FILTER_CCP)
       SELECT C.ROWID 
--,DM.RS_CONTRACT_SID
,A.PERIOD
,A.YEAR
,ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       --SUM((SALES)) SALES,
       --SUM((QUANTITY* COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH,
	   MAX(ISNULL(FCCP.FILTER_CCP,0))        FILTER_CCP
      FROM    ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
	   LEFT JOIN #Filter_CCPD FCCP ON FCCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID
       AND FCCP.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (
       
       select CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,sum(DISCOUNT) DISCOUNT--,sum(SALES) SALES, sum(QUANTITY) QUANTITY 
                                                  from(SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              --SUM(CASE 
              --             WHEN QUANTITY_INCLUSION = ''Y''
               --                   THEN SALES
               --            END) SALES,
              --SUM(CASE 
              --             WHEN QUANTITY_INCLUSION = ''Y''
              --                    THEN QUANTITY
               --            END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION) a group by CCP_DETAILS_SID,
             PERIOD_SID,
              RS_MODEL_SID
       ) AD
       ON AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID 
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND A.PERIOD_SID = AD.PERIOD_SID
--LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
              WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
              --  DM.RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       DM.DEDUCTION_INCLUSION

      UNION ALL

      SELECT C.ROWID
--,DM.RS_CONTRACT_SID
,PERIOD
,YEAR
,SUM(PTD.PROJECTION_SALES) DISCOUNT,
       --SUM(SPT.PROJECTION_SALES) SALES,
       --SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH,
	   MAX(ISNULL(FCCP.FILTER_CCP,0))        FILTER_CCP
      FROM    ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
	   LEFT JOIN #Filter_CCPD FCCP ON FCCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID
       AND FCCP.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ',@PROJ_START_SID, '  AND  ', @PROJ_END_SID, ')B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
--LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
--ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
--              AND B.PERIOD_SID = SPT.PERIOD_SID
--LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        --AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
               -- DM.RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPA'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @sql =Concat('

       INSERT INTO ', @CUSTOMER_TABLE_DISCOUNT, '(HIERARCHY_NO,
       RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DISCOUNT,
       SALES,
       UNITS,
       DEDUCTION_INCLUSION,
       INDICATOR,
       GROWTH)              
SELECT HIERARCHY_NO,
       RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DISCOUNT,
       SALES,
       UNITS,
       DEDUCTION_INCLUSION,
       INDICATOR,
       GROWTH FROM ( 
SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, 
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       SUM((SALES)) SALES,
       SUM((QUANTITY* COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
--AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
       SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN SALES
                           END) SALES,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN QUANTITY
                           END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION
       ) ad
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.CUST_HIERARCHY_NO,
        --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
        --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       SUM(SPT.PROJECTION_SALES) SALES,
       SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
-- AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
              AND B.PERIOD_SID = SPT.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.CUST_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A WHERE NOT EXISTS (
                           SELECT 1 FROM  ', @CUSTOMER_TABLE_DISCOUNT, ' B WHERE B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           )')

                EXEC Sp_executesql
                  @sql

                SET @SQL =Concat('
                UPDATE      B SET B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       ( SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, 
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       SUM((SALES)) SALES,
       SUM((QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
--AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
       SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN SALES
                           END) SALES,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN QUANTITY
                           END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION
       ) ad
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.CUST_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       SUM(SPT.PROJECTION_SALES) SALES,
       SUM(SPT.PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
-- AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              PERIOD,
              YEAR
       FROM #PERIOD
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
              AND B.PERIOD_SID = SPT.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.CUST_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           WHERE ISNULL(A.GROWTH,0)<>ISNULL(B.GROWTH,0)
                           AND ISNULL(A.DISCOUNT,0)<>ISNULL(B.DISCOUNT,0) 
                           AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0)  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPA'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW = 'P'
            BEGIN
                SET @sql =Concat(' 
       INSERT INTO ', @PRODUCT_TABLE_DISCOUNT, '(
       HIERARCHY_NO,
       RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DISCOUNT,
       SALES,
       UNITS,
       DEDUCTION_INCLUSION,
       INDICATOR,
       GROWTH
       )
       SELECT 
       HIERARCHY_NO,
       RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DISCOUNT,
       SALES,
       UNITS,
       DEDUCTION_INCLUSION,
       INDICATOR,
       GROWTH 
       FROM (SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       SUM((SALES)) SALES,
       SUM((QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
--AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              PERIOD,
              YEAR
       FROM #PERIOD
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
       SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN SALES
                           END) SALES,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN QUANTITY
                           END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION
       ) AD
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.PROD_HIERARCHY_NO,
      --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       SUM(SPT.PROJECTION_SALES) SALES,
       SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
-- AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
              AND B.PERIOD_SID = SPT.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.PROD_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A WHERE NOT EXISTS (
                           SELECT 1 FROM  ', @PRODUCT_TABLE_DISCOUNT, ' B WHERE B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND  B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           )')

                EXEC Sp_executesql
                  @sql

                SET @SQL =Concat('
                UPDATE      B SET B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM  (SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       SUM((SALES)) SALES,
       SUM((QUANTITY* COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
--AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              PERIOD,
              YEAR
       FROM #PERIOD
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
       SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN SALES
                           END) SALES,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN QUANTITY
                           END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION
       ) AD
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.PROD_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       b.PERIOD,
       b.YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       SUM(SPT.PROJECTION_SALES) SALES,
       SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
-- AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              PERIOD,
              YEAR
       FROM #PERIOD
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
              AND B.PERIOD_SID = SPT.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.PROD_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A 
                           JOIN ', @PRODUCT_TABLE_DISCOUNT, '  B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           WHERE ISNULL(A.GROWTH,0)<>ISNULL(B.GROWTH,0)
                           AND ISNULL(A.DISCOUNT,0)<>ISNULL(B.DISCOUNT,0) 
                           AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPA'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @sql =Concat(' 
INSERT INTO ', @CUSTOM_TABLE_DISCOUNT, '(HIERARCHY_NO
--,RS_CONTRACT_SID
,PERIOD
,YEAR
,DISCOUNT
--,SALES
--,UNITS
,DEDUCTION_INCLUSION
,INDICATOR
,GROWTH)
       SELECT ROWID
--,RS_CONTRACT_SID
,PERIOD
,YEAR
,DISCOUNT
--,SALES
--,UNITS
,DEDUCTION_INCLUSION
,INDICATOR
,GROWTH from (SELECT C.ROWID
--,DM.RS_CONTRACT_SID
,A.PERIOD
,A.YEAR
,ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       --SUM((SALES)) SALES,
       --SUM((QUANTITY* COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
      FROM   
          ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
             CROSS JOIN (SELECT PERIOD_SID,
                               PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (
       SELECT CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              --SUM(CASE 
              --             WHEN QUANTITY_INCLUSION = ''Y''
              --                    THEN SALES
              --             END) SALES,
              --SUM(CASE 
              --             WHEN QUANTITY_INCLUSION = ''Y''
              --                    THEN QUANTITY
              --             END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION
       ) AD
       ON AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID 
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND A.PERIOD_SID = AD.PERIOD_SID
--LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
              WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
             --   DM.RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       DM.DEDUCTION_INCLUSION

      UNION ALL

SELECT C.ROWID
--,DM.RS_CONTRACT_SID
,PERIOD
,YEAR
,SUM(PTD.PROJECTION_SALES) DISCOUNT,
       --SUM(SPT.PROJECTION_SALES) SALES,
       --SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
      FROM    ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
--LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
--ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
--              AND B.PERIOD_SID = SPT.PERIOD_SID
--LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
              --  DM.RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A WHERE NOT EXISTS (
                           SELECT 1 FROM  ',
                          @CUSTOM_TABLE_DISCOUNT
                          ,
                                    ' B WHERE B.HIERARCHY_NO=A.ROWID
                           --AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           )')

                EXEC Sp_executesql
                  @sql

                --   SET @sql =Concat(' 
                --INSERT INTO ', @CUSTOM_TABLE_SALES, '(CUSTOM_VIEW_MASTER_SID,HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR)
                SET @SQL =Concat('
                UPDATE      B SET B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                           --,B.SALES=A.SALES
                           --,B.UNITS=A.UNITS
                FROM  (SELECT C.ROWID
--,DM.RS_CONTRACT_SID
,A.PERIOD
,A.YEAR
,ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       --SUM((SALES)) SALES,
       --SUM((QUANTITY* COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
      FROM    ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (
       SELECT CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              --SUM(CASE 
              --             WHEN QUANTITY_INCLUSION = ''Y''
              --                    THEN SALES
              --             END) SALES,
              --SUM(CASE 
              --             WHEN QUANTITY_INCLUSION = ''Y''
              --                    THEN QUANTITY
              --             END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION
       ) AD
       ON AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID 
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND A.PERIOD_SID = AD.PERIOD_SID
--LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
              WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        --AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
              --  DM.RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       DM.DEDUCTION_INCLUSION

      UNION ALL

      SELECT C.ROWID
--,DM.RS_CONTRACT_SID
,PERIOD
,YEAR
,SUM(PTD.PROJECTION_SALES) DISCOUNT,
       --SUM(SPT.PROJECTION_SALES) SALES,
       --SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
      FROM    ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
--LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
--ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
--              AND B.PERIOD_SID = SPT.PERIOD_SID
--LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
             --   DM.RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A
                           JOIN ', @CUSTOM_TABLE_DISCOUNT, '  B ON B.HIERARCHY_NO=A.ROWID
                           --AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           --WHERE ISNULL(A.GROWTH,0)<>ISNULL(B.GROWTH,0)
                           --AND ISNULL(A.DISCOUNT,0)<>ISNULL(B.DISCOUNT,0) 
                           --AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           --AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) 
                           ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPD'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @sql =Concat('

       DELETE B FROM ', @CUSTOMER_TABLE_DISCOUNT, ' B WHERE NOT EXISTS(
       SELECT 1 FROM  ( SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, 
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
--AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              PERIOD,
              YEAR
       FROM #PERIOD
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A

GROUP BY c.CUST_HIERARCHY_NO,
      --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL RS_CONTRACT_SID,
       B.PERIOD,
       B.YEAR,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
-- AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              PERIOD,
              YEAR
       FROM #PERIOD
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B

GROUP BY c.CUST_HIERARCHY_NO,
      --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A 
                           WHERE B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR)')

                EXEC Sp_executesql
                  @sql

                SET @SQL =Concat('
                UPDATE      B SET B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       (SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, 
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       SUM((SALES)) SALES,
       SUM((QUANTITY* COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
--AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
       SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN SALES
                           END) SALES,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN QUANTITY
                           END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION
       ) ad
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.CUST_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
      PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       SUM(SPT.PROJECTION_SALES) SALES,
       SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
-- AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
              AND B.PERIOD_SID = SPT.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.CUST_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND  B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                          AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           WHERE ISNULL(A.GROWTH,0)<>ISNULL(B.GROWTH,0)
                           AND ISNULL(A.DISCOUNT,0)<>ISNULL(B.DISCOUNT,0) 
                           AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0)  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPD'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW = 'P'
            BEGIN
                SET @sql =Concat(' 
       DELETE B FROM ', @PRODUCT_TABLE_DISCOUNT, ' B WHERE NOT EXISTS(
       SELECT 1 FROM  (SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
        --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
--AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              PERIOD,
              YEAR
       FROM #PERIOD
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
GROUP BY c.PROD_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
-- AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
GROUP BY c.PROD_HIERARCHY_NO,
      --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A 
                           where B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR)')

                EXEC Sp_executesql
                  @sql

                SET @SQL =Concat('
                UPDATE      B SET B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM  (SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
        --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       SUM((SALES)) SALES,
       SUM((QUANTITY* COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
--AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              PERIOD,
              YEAR
       FROM #PERIOD
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
       SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN SALES
                           END) SALES,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN QUANTITY
                           END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION
       ) AD
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY C.PROD_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       SUM(SPT.PROJECTION_SALES) SALES,
       SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
-- AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
              AND B.PERIOD_SID = SPT.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.PROD_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A 
                           join ', @PRODUCT_TABLE_DISCOUNT, '  B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           WHERE ISNULL(A.GROWTH,0)<>ISNULL(B.GROWTH,0)
                           AND ISNULL(A.DISCOUNT,0)<>ISNULL(B.DISCOUNT,0) 
                           AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPD'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @sql =Concat(' 
       DELETE B FROM ', @CUSTOM_TABLE_DISCOUNT, ' B WHERE NOT EXISTS(
       SELECT 1 FROM  (SELECT C.ROWID
--,DM.RS_CONTRACT_SID
,A.PERIOD
,A.YEAR
,DM.DEDUCTION_INCLUSION,
       0 INDICATOR
      FROM   
          ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A 
              WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                      --  AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
             --   DM.RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       DM.DEDUCTION_INCLUSION

      UNION ALL

SELECT C.ROWID
--,DM.RS_CONTRACT_SID
,PERIOD
,YEAR
,DM.DEDUCTION_INCLUSION,
       1 INDICATOR
      FROM    ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')B
WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                     --   AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
                --DM.RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A
                           WHERE B.HIERARCHY_NO=A.ROWID
                           --AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                            AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR)')

                EXEC Sp_executesql
                  @sql

                --   SET @sql =Concat(' 
                --INSERT INTO ', @CUSTOM_TABLE_SALES, '(CUSTOM_VIEW_MASTER_SID,HIERARCHY_NO,PERIOD,YEAR,ACCOUNT_GROWTH,PRODUCT_GROWTH,SALES,UNITS,SALES_INCLUSION,INDICATOR)
                SET @SQL =Concat('
                UPDATE      B SET B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                           --,B.SALES=A.SALES
                           --,B.UNITS=A.UNITS
                FROM  (SELECT C.ROWID
--,DM.RS_CONTRACT_SID
,A.PERIOD
,A.YEAR
,ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       --SUM((SALES)) SALES,
       --SUM((QUANTITY* COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
      FROM   
          ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (
       SELECT CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              --SUM(CASE 
              --             WHEN QUANTITY_INCLUSION = ''Y''
              --                    THEN SALES
              --             END) SALES,
              --SUM(CASE 
              --             WHEN QUANTITY_INCLUSION = ''Y''
              --                    THEN QUANTITY
              --             END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION
       ) AD
       ON AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID 
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND A.PERIOD_SID = AD.PERIOD_SID
--LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
              WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        --AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
                --DM.RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       DM.DEDUCTION_INCLUSION

      UNION ALL

SELECT C.ROWID
--,DM.RS_CONTRACT_SID
,PERIOD
,YEAR
,SUM(PTD.PROJECTION_SALES) DISCOUNT,
       --SUM(SPT.PROJECTION_SALES) SALES,
       --SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
      FROM    ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
--LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
--ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
--              AND B.PERIOD_SID = SPT.PERIOD_SID
----LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        --AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
                --DM.RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A
                           JOIN ', @CUSTOM_TABLE_DISCOUNT, '  B ON B.HIERARCHY_NO=A.ROWID
                           --AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           --WHERE ISNULL(A.GROWTH,0)<>ISNULL(B.GROWTH,0)
                           --AND ISNULL(A.DISCOUNT,0)<>ISNULL(B.DISCOUNT,0) 
                           --AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           --AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) 
                           ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'M'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @SQL =Concat('
       SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO,
                     ri.SELECTED_LEVEL RS_CONTRACT_SID,
             PERIOD,
             YEAR,
                     ', CASE @MASS_UPDATEFIELD
                                                    WHEN 'GROWTH' THEN 'isnull(AVG(GROWTH),0)'
                                                    ELSE 'SUM(PROJECTION_SALES)'
                                                  END, ' AS MASSUPDATE_FIELD, SPM.DEDUCTION_INCLUSION,
             1 INDICATOR
        --- INTO    #TEMP
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                           WHERE PERIOD_SID BETWEEN ', @START_PERIOD, 'AND ', @END_PERIOD, ')B
         JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID
AND SPM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
         JOIN ', @D_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
               AND DP.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID           
               AND DP.PERIOD_SID=B.PERIOD_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                   ri.SELECTED_LEVEL,
                PERIOD,
                YEAR,
                SPM.DEDUCTION_INCLUSION
                     ')

                INSERT INTO #TEMP1
                            (HIERARCHY_NO,
                             RS_CONTRACT_SID,
                             PERIOD,
                             YEAR,
                             MASSUPDATE_FIELD,
                             DEDUCTION_INCLUSION,
                             INDICATOR)
                EXEC Sp_executesql
                  @sql

                SET @SQL =Concat('
                UPDATE      B SET B.', @MASS_UPDATEFIELD, '=A.MASSUPDATE_FIELD
                FROM 
                           #TEMP1 A 
                           JOIN  ', @CUSTOMER_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'M'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'P' )
            BEGIN
                SET @SQL =Concat('SELECT C.PROD_HIERARCHY_NO as HIERARCHY_NO,
                      ri.SELECTED_LEVEL RS_CONTRACT_SID,
             PERIOD,
             YEAR,
                     ', CASE @MASS_UPDATEFIELD
                                                    WHEN 'GROWTH' THEN 'isnull(AVG(GROWTH),0)'
                                                    ELSE 'SUM(PROJECTION_SALES)'
                                                  END, ' AS MASSUPDATE_FIELD, SPM.DEDUCTION_INCLUSION,
             1 INDICATOR
                    -- INTO #TEMP
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                           WHERE PERIOD_SID BETWEEN ', @START_PERIOD, 'AND ', @END_PERIOD, ')B
          JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID and
RI.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID
         JOIN ', @D_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
               AND DP.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID           
               AND DP.PERIOD_SID=B.PERIOD_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                   RI.SELECTED_LEVEL,
                PERIOD,
                YEAR,
                SPM.DEDUCTION_INCLUSION
                     ')

                INSERT INTO #TEMP1
                            (HIERARCHY_NO,
                             RS_CONTRACT_SID,
                             PERIOD,
                             YEAR,
                             MASSUPDATE_FIELD,
                             DEDUCTION_INCLUSION,
                             INDICATOR)
                EXEC Sp_executesql
                  @sql

                SET @SQL =Concat('
                UPDATE      B SET B.', @MASS_UPDATEFIELD, '= A.MASSUPDATE_FIELD
                FROM 
                           #TEMP1 A 
                           JOIN  ', @PRODUCT_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'M'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET B.', @MASS_UPDATEFIELD, '=A.MASSUPDATE_FIELD
                FROM 
       (SELECT C.ROWID,
       --SPM.RS_CONTRACT_SID,
             PERIOD,
             YEAR,
                     ', CASE @MASS_UPDATEFIELD
                                                    WHEN 'GROWTH' THEN 'isnull(AVG(GROWTH),0)'
                                                    ELSE 'SUM(PROJECTION_SALES)'
                                                  END, ' AS MASSUPDATE_FIELD, SPM.DEDUCTION_INCLUSION,
             1 INDICATOR
      FROM   ',@CUSTOM_CCP_SALES,'  C
                  JOIN ', @CCP_HIERARCHY, ' C1
                     ON C1.CCP_DETAILS_SID = C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                           WHERE PERIOD_SID BETWEEN ', @START_PERIOD, 'AND ', @END_PERIOD, ')B
          JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
              AND( C.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID    OR    NULLIF(C.RS_CONTRACT_SID,0) is null)
            AND CHECK_RECORD = 1
         JOIN ', @D_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
               AND DP.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID           
               AND DP.PERIOD_SID=B.PERIOD_SID
                WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        --AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                   --SPM.RS_CONTRACT_SID,
                PERIOD,
                YEAR,
                SPM.DEDUCTION_INCLUSION)A 
                           JOIN  ', @CUSTOM_TABLE_DISCOUNT, ' B ON  B.HIERARCHY_NO=A.ROWID
                           --AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'R'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET         B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                FROM 
       (SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO,
       RI.SELECTED_LEVEL RS_CONTRACT_SID,
             PERIOD,
             YEAR,                
               GROWTH=isnull(AVG(GROWTH),0),
               DISCOUNT=SUM(PROJECTION_SALES),
                       SPM.DEDUCTION_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                           WHERE PERIOD_SID BETWEEN ', @START_PERIOD, 'AND ', @END_PERIOD, ')B
       JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
		 AND FILTER_CCP = 1
            --AND CHECK_RECORD = 1
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID and RI.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
         JOIN ', @D_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
               AND DP.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID           
               AND DP.PERIOD_SID=B.PERIOD_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
         RI.SELECTED_LEVEL,
                PERIOD,
                YEAR,
                SPM.DEDUCTION_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'R'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'p' )
            BEGIN
                SET @SQL =Concat('
                 UPDATE     B SET         B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                FROM 
       (SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO,
       RI.SELECTED_LEVEL  RS_CONTRACT_SID,
             PERIOD,
             YEAR,                
               GROWTH=isnull(AVG(GROWTH),0),
               DISCOUNT=SUM(PROJECTION_SALES),
                       SPM.DEDUCTION_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                           WHERE PERIOD_SID BETWEEN ', @START_PERIOD, 'AND ', @END_PERIOD, ')B
       JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
		 AND FILTER_CCP = 1
            --AND CHECK_RECORD = 1
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID and RI.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID
         JOIN ', @D_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
               AND DP.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID           
               AND DP.PERIOD_SID=B.PERIOD_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
         RI.SELECTED_LEVEL,
                PERIOD,
                YEAR,
                SPM.DEDUCTION_INCLUSION)A 
                           JOIN  ', @PRODUCT_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'R'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @SQL =Concat('
                 UPDATE     B SET         B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                FROM 
       (SELECT C.ROWID ,
             --SPM.RS_CONTRACT_SID,
             PERIOD,
             YEAR,                
               GROWTH=isnull(AVG(GROWTH),0),
               DISCOUNT=SUM(PROJECTION_SALES),
                       SPM.DEDUCTION_INCLUSION,
             1 INDICATOR
      FROM    ',@CUSTOM_CCP_SALES,'  C
                  JOIN ', @CCP_HIERARCHY, ' C1
                             ON C1.CCP_DETAILS_SID = C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                           WHERE PERIOD_SID BETWEEN ', @START_PERIOD, 'AND ', @END_PERIOD, ')B
       JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
               AND ( C.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID    OR    NULLIF(C.RS_CONTRACT_SID,0) IS NULL)
			   AND FILTER_CCP = 1
            --AND CHECK_RECORD = 1
         JOIN ', @D_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
               AND DP.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID           
               AND DP.PERIOD_SID=B.PERIOD_SID
                WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                --SPM.RS_CONTRACT_SID,
                PERIOD,
                YEAR,
                SPM.DEDUCTION_INCLUSION)A 
                           JOIN  ', @CUSTOM_TABLE_DISCOUNT, ' B  ON B.HIERARCHY_NO=A.ROWID
                         --  AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'ADJ'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @SQL =Concat('
                UPDATE             B SET         B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                FROM 
       (SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO,
       RI.SELECTED_LEVEL RS_CONTRACT_SID,
             PERIOD,
             YEAR,                
               GROWTH=isnull(AVG(GROWTH),0),
               DISCOUNT=SUM(PROJECTION_SALES),
                       SPM.DEDUCTION_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                                         WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '
                          --WHERE  concat(', LEFT(@FREQUENCY, 1) + 'Period' + ' ,', 'YEAR) BETWEEN ', @START_PERIOD, ' AND ', @END_PERIOD, '
                                           )B
       JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID and RI.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID
         JOIN ', @D_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
               AND DP.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID           
               AND DP.PERIOD_SID=B.PERIOD_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
         RI.SELECTED_LEVEL,
                PERIOD,
                YEAR,
                SPM.DEDUCTION_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR    
                         --WHERE ISNULL(A.GROWTH,0)<>ISNULL(B.GROWTH,0)
                           --AND ISNULL(A.DISCOUNT,0)<>ISNULL(B.DISCOUNT,0) 
                           --AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           --AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) 
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'ADJ'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'p' )
            BEGIN
                SET @SQL =Concat('
                 UPDATE            B SET         B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                FROM 
       (SELECT C.PROD_HIERARCHY_NO as HIERARCHY_NO,
             RI.SELECTED_LEVEL RS_CONTRACT_SID,
             PERIOD,
             YEAR,                
               GROWTH=isnull(AVG(GROWTH),0),
               DISCOUNT=SUM(PROJECTION_SALES),
                       SPM.DEDUCTION_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                                         WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '
                          --WHERE  concat(', LEFT(@FREQUENCY, 1) + 'Period' + ' ,', 'YEAR) BETWEEN ', @START_PERIOD, ' AND ', @END_PERIOD, '
                                           )B
       JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID and RI.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID
         JOIN ', @D_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
               AND DP.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID           
               AND DP.PERIOD_SID=B.PERIOD_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                RI.SELECTED_LEVEL,
                PERIOD,
                YEAR,
                SPM.DEDUCTION_INCLUSION)A 
                           JOIN  ', @PRODUCT_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR    
                             --WHERE ISNULL(A.GROWTH,0)<>ISNULL(B.GROWTH,0)
                           --AND ISNULL(A.DISCOUNT,0)<>ISNULL(B.DISCOUNT,0) 
                           --AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           --AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) 
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'ADJ'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET         B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                FROM 
       (SELECT C.ROWID,
             --SPM.RS_CONTRACT_SID,
             PERIOD,
             YEAR,                
               GROWTH=isnull(AVG(GROWTH),0),
               DISCOUNT=SUM(PROJECTION_SALES),
                       SPM.DEDUCTION_INCLUSION,
             1 INDICATOR
      FROM    ',@CUSTOM_CCP_SALES,'  C
                  JOIN ', @CCP_HIERARCHY, ' C1
                             ON C1.CCP_DETAILS_SID = C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                                           WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '
                         -- WHERE  concat(', LEFT(@FREQUENCY, 1) + 'Period' + ' ,', 'YEAR) BETWEEN ', @START_PERIOD, ' AND ', @END_PERIOD, '
                                           )B
       JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
               AND( C.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID    OR    NULLIF(C.RS_CONTRACT_SID,0) IS NULL) 
            AND CHECK_RECORD = 1
         JOIN ', @D_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
               AND DP.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID           
               AND DP.PERIOD_SID=B.PERIOD_SID
                WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
       GROUP  BY C.ROWID,
                --SPM.RS_CONTRACT_SID,
                PERIOD,
                YEAR,
                SPM.DEDUCTION_INCLUSION)A 
                           JOIN  ', @CUSTOM_TABLE_DISCOUNT, ' B  ON B.HIERARCHY_NO=A.ROWID
                           --AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR  
                          --WHERE ISNULL(A.GROWTH,0)<>ISNULL(B.GROWTH,0)
                           --AND ISNULL(A.DISCOUNT,0)<>ISNULL(B.DISCOUNT,0) 
                           --AND ISNULL(A.SALES,0)<>ISNULL(B.SALES,0)  
                           --AND ISNULL(A.UNITS,0)<>ISNULL(B.UNITS,0) 
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'CAL'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET         B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                FROM 
       (SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO,
       RI.SELECTED_LEVEL RS_CONTRACT_SID,
             PERIOD,
             YEAR,                
               GROWTH=isnull(AVG(GROWTH),0),
               DISCOUNT=SUM(PROJECTION_SALES),
               SPM.DEDUCTION_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                                         WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '
                          --WHERE  concat(', LEFT(@FREQUENCY, 1) + 'Period' + ' ,', 'YEAR) BETWEEN ', @START_PERIOD, ' AND ', @END_PERIOD, '
                                           )B
       JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID AND RI.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID
         JOIN ', @D_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
               AND DP.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID           
               AND DP.PERIOD_SID=B.PERIOD_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
         RI.SELECTED_LEVEL,
                PERIOD,
                YEAR,
                SPM.DEDUCTION_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR    
                     ')

                -- select @sql
                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'CAL'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'p' )
            BEGIN
                SET @SQL =Concat('
                     UPDATE       B SET         B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                FROM 
       (SELECT C.PROD_HIERARCHY_NO as HIERARCHY_NO,
              RI.SELECTED_LEVEL RS_CONTRACT_SID,
             PERIOD,
             YEAR,                
               GROWTH=isnull(AVG(GROWTH),0),
               DISCOUNT=SUM(PROJECTION_SALES),
                       SPM.DEDUCTION_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                                         WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '
                          --WHERE  concat(', LEFT(@FREQUENCY, 1) + 'Period' + ' ,', 'YEAR) BETWEEN ', @START_PERIOD, ' AND ', @END_PERIOD, '
                                           )B
       JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
            AND CHECK_RECORD = 1
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID  AND RI.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID
         JOIN ', @D_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
               AND DP.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID           
               AND DP.PERIOD_SID=B.PERIOD_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                RI.SELECTED_LEVEL,
                PERIOD,
                YEAR,
                SPM.DEDUCTION_INCLUSION)A 
                            JOIN  ', @PRODUCT_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR    
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'CAL'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET         B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                FROM 
       (SELECT C.ROWID as HIERARCHY_NO,
             --SPM.RS_CONTRACT_SID,
             PERIOD,
             YEAR,                
               GROWTH=isnull(AVG(GROWTH),0),
               DISCOUNT=SUM(PROJECTION_SALES),
                       SPM.DEDUCTION_INCLUSION,
             1 INDICATOR
      FROM    ',@CUSTOM_CCP_SALES,'  C
                  JOIN ', @CCP_HIERARCHY, ' C1
                             ON C1.CCP_DETAILS_SID = C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                                           WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, '
                         -- WHERE  concat(', LEFT(@FREQUENCY, 1) + 'Period' + ' ,', 'YEAR) BETWEEN ', @START_PERIOD, ' AND ', @END_PERIOD, '
                                           )B
       JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
               AND( C.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID    OR    NULLIF(C.RS_CONTRACT_SID,0) is null)  
            AND CHECK_RECORD = 1
         JOIN ', @D_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID = SPM.CCP_DETAILS_SID 
               AND DP.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID           
               AND DP.PERIOD_SID=B.PERIOD_SID
                WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        --AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
      GROUP  BY C.ROWID,
                --SPM.RS_CONTRACT_SID,
                PERIOD,
                YEAR,
                SPM.DEDUCTION_INCLUSION)A 
                           JOIN  ', @CUSTOM_TABLE_DISCOUNT, ' B  ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           --AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR    
                     ')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'UPS'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @SQL =Concat('
                     UPDATE       B SET         B.sales=iif(B.INDICATOR=1,A.PROJECTION_SALES,a.SALES)
                           ,B.units=iif(B.INDICATOR=1,A.PROJECTION_UNITS,a.units)
                FROM 
       (select  a.HIERARCHY_NO,
                a.RS_CONTRACT_SID,
                B.PERIOD,
                B.YEAR , PROJECTION_SALES=sum(IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(PROJECTION_SALES,0), NULL))
                                                                ,PROJECTION_UNITS=sum(IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(PROJECTION_UNITS,0), NULL))
                                                                , SALES=sum(IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(SALES,0), NULL))
                                                                ,units=sum(IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(QUANTITY,0), NULL))
                                                                FROM (SELECT distinct C.CUST_HIERARCHY_NO as HIERARCHY_NO,
              RI.SELECTED_LEVEL RS_CONTRACT_SID,c.CCP_DETAILS_SID,sales_inclusion ,          
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C             
       JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID and  SPM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID) A
            
              -- AND DP.PERIOD_SID=B.PERIOD_SID                    
                                cross JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
               WHERE  PERIOD_SID BETWEEN ', Iif(@PROJ_START_SID < @STAT_SALES_SID, @PROJ_START_SID, @STAT_SALES_SID), ' AND ', @PROJ_END_SID, ')B
                                                --on  DP.PERIOD_SID=B.PERIOD_SID
                                                left  JOIN ', @S_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID =  A.CCP_DETAILS_SID   
                                 AND DP.PERIOD_SID=B.PERIOD_SID
                                                LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,                               
                               SUM(QUANTITY) QUANTITY,sum(SALES) SALES
                        FROM   [ACTUALS_DETAILS] AD
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY AD.CCP_DETAILS_SID,
                                  PERIOD_SID) NAS ON NAS.CCP_DETAILS_SID =  A.CCP_DETAILS_SID
                                                AND B.PERIOD_SID = NAS.PERIOD_SID
      GROUP  BY a.HIERARCHY_NO,
                a.RS_CONTRACT_SID,
                B.PERIOD,
                B.YEAR)A 
                            JOIN  ', @CUSTOMER_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                     ')

                EXEC Sp_executesql
                  @sql,
                  N'@SALES_INCLUSION bit',
                  @SALES_INCLUSION=NULL--@SALES_INCLUSION
            END

          IF @FLAG = 'UPS'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN ( 'p' )
            BEGIN
                SET @SQL =Concat('
                     UPDATE       B SET         B.sales=iif(B.INDICATOR=1,A.PROJECTION_SALES,a.SALES)
                           ,B.units=iif(B.INDICATOR=1,A.PROJECTION_UNITS,a.units)
                FROM 
       (select  a.HIERARCHY_NO,
                a.RS_CONTRACT_SID,
                B.PERIOD,
                B.YEAR , PROJECTION_SALES=sum(IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(PROJECTION_SALES,0), NULL))
                                                                ,PROJECTION_UNITS=sum(IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(PROJECTION_UNITS,0), NULL))
                                                                , SALES=sum(IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(SALES,0), NULL))
                                                                ,units=sum(IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(QUANTITY,0), NULL))
                                                                FROM (SELECT distinct C.PROD_HIERARCHY_NO as HIERARCHY_NO,
              RI.SELECTED_LEVEL RS_CONTRACT_SID,c.CCP_DETAILS_SID,sales_inclusion ,          
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C             
       JOIN ', @D_MASTER_TABLE, ' SPM
         ON SPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID and  SPM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID) A
           
              -- AND DP.PERIOD_SID=B.PERIOD_SID                    
                                cross JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
               WHERE  PERIOD_SID BETWEEN ', Iif(@PROJ_START_SID < @STAT_SALES_SID, @PROJ_START_SID, @STAT_SALES_SID), ' AND ', @PROJ_END_SID, ')B
                                --             on  DP.PERIOD_SID=B.PERIOD_SID
                                left JOIN ', @S_PROJECTION_TABLE, ' DP
         ON DP.CCP_DETAILS_SID =  A.CCP_DETAILS_SID    
                                 AND DP.PERIOD_SID=B.PERIOD_SID                       
                                                LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,                               
                               SUM(QUANTITY) QUANTITY,sum(SALES) SALES
                        FROM   [ACTUALS_DETAILS] AD
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY AD.CCP_DETAILS_SID,
                                  PERIOD_SID) NAS ON NAS.CCP_DETAILS_SID =  A.CCP_DETAILS_SID
                                                AND B.PERIOD_SID = NAS.PERIOD_SID
      GROUP  BY a.HIERARCHY_NO,
                a.RS_CONTRACT_SID,
                B.PERIOD,
                B.YEAR)A 
                            JOIN  ', @PRODUCT_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR 
                     ')

                EXEC Sp_executesql
                  @sql,
                  N'@SALES_INCLUSION bit',
                  @SALES_INCLUSION=NULL--@SALES_INCLUSION
            END

          IF @FLAG = 'F'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN( 'C' )
            BEGIN
                SET @SQL = Concat('UPDATE ', @CUSTOMER_TABLE_SALES, ' 
SET
                FILTER_CCP = 0 ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat('UPDATE ', @CUSTOMER_TABLE_SALES, ' 
SET
                FILTER_CCP = 1
WHERE
                HIERARCHY_NO in(
                                SELECT
                                                stc.CUST_HIERARCHY_NO
                                FROM
                                                ', @S_MASTER_TABLE, ' nms inner join ', @CCP_HIERARCHY, ' stc on
                                                stc.CCP_DETAILS_SID = nms.CCP_DETAILS_SID
                                WHERE
                                                nms.FILTER_CCP = 1 
                )')

                EXEC Sp_executesql
                  @SQL

                SET @SQL =Concat('
                UPDATE      B SET B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       ( SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO,
             A.PERIOD,
             A.YEAR,
             NULL ACCOUNT_GROWTH,
             NULL PRODUCT_GROWTH,
             SUM(( SALES )) SALES,
             SUM(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1) )) UNITS,
             C.SALES_INCLUSION,
             0  INDICATOR
     FROM   ', @CCP_HIERARCHY, ' C           
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,
                               SUM(SALES)    SALES,
                               SUM(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] AD JOIN ', @S_MASTER_TABLE, ' NMS ON NMS.CCP_DETAILS_SID=AD.CCP_DETAILS_SID AND NMS.FILTER_CCP=1
                        WHERE  AD.QUANTITY_INCLUSION = ''Y''
                        GROUP  BY AD.CCP_DETAILS_SID,
                                 PERIOD_SID) AD
                    ON A.PERIOD_SID = AD.PERIOD_SID
                       AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.CUST_HIERARCHY_NO,
                  PERIOD,
             YEAR,
             isnull(AVG(ACCOUNT_GROWTH),0),
             isnull(AVG(PRODUCT_GROWTH),0),
             SUM(PROJECTION_SALES),
             SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                                                                                                                   SPM.CCP_DETAILS_SID
                           
                        FROM   ', @S_PROJECTION_TABLE, ' SPM
                                                                                                JOIN   ', @S_MASTER_TABLE, ' SPMM ON SPM.CCP_DETAILS_SID =SPMM.CCP_DETAILS_SID AND SPMM.FILTER_CCP=1)A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR where B.FILTER_CCP=1 
                     ')

                EXEC Sp_executesql
                  @sql
            END
          IF @FLAG = 'F'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN( 'p' )
            BEGIN
                SET @SQL = Concat('UPDATE ', @PRODUCT_TABLE_SALES, ' 
SET
                FILTER_CCP = 0 ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat('UPDATE ', @PRODUCT_TABLE_SALES, ' 
SET
                FILTER_CCP = 1
WHERE
                HIERARCHY_NO in(
                                SELECT
                                                stc.PROD_HIERARCHY_NO
                                FROM
                                                ', @S_MASTER_TABLE, ' nms inner join ', @CCP_HIERARCHY, ' stc on
                                                stc.CCP_DETAILS_SID = nms.CCP_DETAILS_SID
                                WHERE
                                                nms.FILTER_CCP = 1 
                )')

                EXEC Sp_executesql
                  @SQL

                SET @SQL =Concat('
                UPDATE      B SET B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM  (SELECT c.PROD_HIERARCHY_NO as HIERARCHY_NO,-- THOUGH  COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL ACCOUNT_GROWTH,
             NULL PRODUCT_GROWTH,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
             C.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ', @CCP_HIERARCHY, ' C
            
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad 
                                                                                                JOIN ', @S_MASTER_TABLE, ' NMS ON NMS.CCP_DETAILS_SID=AD.CCP_DETAILS_SID AND NMS.FILTER_CCP=1
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY c.PROD_HIERARCHY_NO,
                period,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.PROD_HIERARCHY_NO,
             period,
             YEAR,
             isnull(AVG(ACCOUNT_GROWTH),0),
             isnull(AVG(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             
           
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                                                                                                                   SPM.CCP_DETAILS_SID
                            FROM   ', @S_PROJECTION_TABLE, ' SPM
                                                                                                JOIN   ', @S_MASTER_TABLE, ' SPMM ON SPM.CCP_DETAILS_SID =SPMM.CCP_DETAILS_SID AND SPMM.FILTER_CCP=1) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
      GROUP  BY C.PROD_HIERARCHY_NO,
                period,
                YEAR,
                C.SALES_INCLUSION)A 
                           join ', @PRODUCT_TABLE_SALES, '  B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR where B.FILTER_CCP=1 ')

                EXEC Sp_executesql
                  @sql
            END

    

          IF @FLAG = 'F'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @SQL = Concat('UPDATE ', @CUSTOM_TABLE_SALES, ' 
SET
                FILTER_CCP = 0 ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat('UPDATE ', @CUSTOM_TABLE_SALES, ' 
SET
                FILTER_CCP = 1
WHERE
                HIERARCHY_NO in(
                                SELECT
                                                stc.ROWID
                                FROM
                                                ', @S_MASTER_TABLE, ' nms inner join ',@CUSTOM_CCP_SALES,' stc on
                                                stc.CCP_DETAILS_SID = nms.CCP_DETAILS_SID AND STC.CUST_VIEW_MASTER_SID =', @CUSTOM_VIEW_MASTER_SID, ' 
                                                 AND STC.LEVEL_NO= ', @CUSTOM_LEVEL_NO, ' 
                                WHERE
                                                nms.FILTER_CCP = 1 
                )')

                EXEC Sp_executesql
                  @SQL

                SET @SQL =Concat('
                UPDATE      B SET B.ACCOUNT_GROWTH=A.ACCOUNT_GROWTH
                           ,B.PRODUCT_GROWTH=A.PRODUCT_GROWTH
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM  (SELECT c.ROWID,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             NULL ACCOUNT_GROWTH,
             NULL PRODUCT_GROWTH,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
            C1.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                                                                                                JOIN ', @S_MASTER_TABLE, ' NMS ON NMS.CCP_DETAILS_SID=AD.CCP_DETAILS_SID AND NMS.FILTER_CCP=1
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                     WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                                                                                                                                                 AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                       
      GROUP  BY c.ROWID,
                period,
                YEAR,
                C1.SALES_INCLUSION
      UNION 
      SELECT C.ROWID,
             period,
             YEAR,
             isnull(AVG(ACCOUNT_GROWTH),0),
             isnull(AVG(PRODUCT_GROWTH),0),
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C1.SALES_INCLUSION,
             1 INDICATOR
      FROM  ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                               SPM.PRODUCT_GROWTH,
                               SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                                                                                                                   SPM.CCP_DETAILS_SID
                            FROM   ', @S_PROJECTION_TABLE, ' SPM
                                                                                                JOIN   ', @S_MASTER_TABLE, ' SPMM ON SPM.CCP_DETAILS_SID =SPMM.CCP_DETAILS_SID AND SPMM.FILTER_CCP=1) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                      WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID,
                          '  
                                                                                                                                                  AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        
      GROUP  BY C.ROWID,
                period,
                YEAR,
                C1.SALES_INCLUSION)A
                           join ', @CUSTOM_TABLE_SALES, '  B ON B.HIERARCHY_NO=A.ROWID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR where B.FILTER_CCP=1
                          
                           ')

                EXEC Sp_executesql
                  @sql
            END
			
			 IF @FLAG = 'UOM'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'C' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET 
                           B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       ( SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.PERIOD,
             A.YEAR,
             SUM(( SALES ))    SALES,
             SUM(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1) )) UNITS,
             C.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ', @CCP_HIERARCHY, ' C
	   JOIN ', @S_MASTER_TABLE, ' MAS ON MAS.CCP_DETAILS_SID=C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,
                               SUM(SALES)    SALES,
                               SUM(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] AD
                        WHERE  QUANTITY_INCLUSION = ''Y'' 
						AND EXISTS (SELECT 1 FROM ', @S_MASTER_TABLE, ' MAS WHERE MAS.CCP_DETAILS_SID=AD.CCP_DETAILS_SID AND MAS.FILTER_CCP=1)
                        GROUP  BY AD.CCP_DETAILS_SID,
                                  PERIOD_SID) AD
                    ON A.PERIOD_SID = AD.PERIOD_SID
                       AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
			WHERE MAS.FILTER_CCP=1
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.CUST_HIERARCHY_NO,
             PERIOD,
             YEAR,
             SUM(PROJECTION_SALES),
             SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             JOIN ', @S_MASTER_TABLE, ' MAS ON MAS.CCP_DETAILS_SID=C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               SPM.CCP_DETAILS_SID
                        FROM   ', @S_PROJECTION_TABLE, ' SPM
						WHERE EXISTS (SELECT 1 FROM ', @S_MASTER_TABLE, ' MAS WHERE MAS.CCP_DETAILS_SID=SPM.CCP_DETAILS_SID AND MAS.FILTER_CCP=1)) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
			  WHERE MAS.FILTER_CCP=1
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                     ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL =Concat('
                UPDATE      B SET 
                           B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       ( SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.PERIOD,
             A.YEAR,
             SUM(( SALES ))    SALES,
             SUM(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1) )) UNITS,
             C.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ', @CCP_HIERARCHY, ' C
	   JOIN ', @S_MASTER_TABLE, ' MAS ON MAS.CCP_DETAILS_SID=C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,
                               SUM(SALES)    SALES,
                               SUM(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] AD
                        WHERE  QUANTITY_INCLUSION = ''Y'' 
						AND EXISTS (SELECT 1 FROM ', @S_MASTER_TABLE, ' MAS WHERE MAS.CCP_DETAILS_SID=AD.CCP_DETAILS_SID AND MAS.FILTER_CCP=0)
                        GROUP  BY AD.CCP_DETAILS_SID,
                                  PERIOD_SID) AD
                    ON A.PERIOD_SID = AD.PERIOD_SID
                       AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
			WHERE MAS.FILTER_CCP=0
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.CUST_HIERARCHY_NO,
             PERIOD,
             YEAR,
             SUM(PROJECTION_SALES),
             SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             JOIN ', @S_MASTER_TABLE, ' MAS ON MAS.CCP_DETAILS_SID=C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               SPM.CCP_DETAILS_SID
                        FROM   ', @S_PROJECTION_TABLE, ' SPM
						WHERE EXISTS (SELECT 1 FROM ', @S_MASTER_TABLE, ' MAS WHERE MAS.CCP_DETAILS_SID=SPM.CCP_DETAILS_SID AND MAS.FILTER_CCP=0)) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
			  WHERE MAS.FILTER_CCP=0
      GROUP  BY C.CUST_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                     ')

                EXEC Sp_executesql
                  @SQL
            END

          IF @FLAG = 'UOM'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW IN ( 'P' )
            BEGIN
                SET @SQL =Concat('
                UPDATE      B SET 
                           B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       ( SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.PERIOD,
             A.YEAR,
             SUM(( SALES ))    SALES,
             SUM(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1) )) UNITS,
             C.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ', @CCP_HIERARCHY, ' C
	   JOIN ', @S_MASTER_TABLE, ' MAS ON MAS.CCP_DETAILS_SID=C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,
                               SUM(SALES)    SALES,
                               SUM(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] AD
                        WHERE  QUANTITY_INCLUSION = ''Y'' 
						AND EXISTS (SELECT 1 FROM ', @S_MASTER_TABLE, ' MAS WHERE MAS.CCP_DETAILS_SID=AD.CCP_DETAILS_SID AND MAS.FILTER_CCP=1)
                        GROUP  BY AD.CCP_DETAILS_SID,
                                  PERIOD_SID) AD
                    ON A.PERIOD_SID = AD.PERIOD_SID
                       AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
			WHERE MAS.FILTER_CCP=1
      GROUP  BY C.PROD_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.PROD_HIERARCHY_NO,
             PERIOD,
             YEAR,
             SUM(PROJECTION_SALES),
             SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             JOIN ', @S_MASTER_TABLE, ' MAS ON MAS.CCP_DETAILS_SID=C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               SPM.CCP_DETAILS_SID
                        FROM   ', @S_PROJECTION_TABLE, ' SPM
						WHERE EXISTS (SELECT 1 FROM ', @S_MASTER_TABLE, ' MAS WHERE MAS.CCP_DETAILS_SID=SPM.CCP_DETAILS_SID AND MAS.FILTER_CCP=1)) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
			  WHERE MAS.FILTER_CCP=1
      GROUP  BY C.PROD_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @PRODUCT_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                     ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL =Concat('
                UPDATE      B SET 
                           B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
       ( SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.PERIOD,
             A.YEAR,
             SUM(( SALES ))    SALES,
             SUM(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1) )) UNITS,
             C.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ', @CCP_HIERARCHY, ' C
	   JOIN ', @S_MASTER_TABLE, ' MAS ON MAS.CCP_DETAILS_SID=C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,
                               SUM(SALES)    SALES,
                               SUM(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] AD
                        WHERE  QUANTITY_INCLUSION = ''Y'' 
						AND EXISTS (SELECT 1 FROM ', @S_MASTER_TABLE, ' MAS WHERE MAS.CCP_DETAILS_SID=AD.CCP_DETAILS_SID AND MAS.FILTER_CCP=0)
                        GROUP  BY AD.CCP_DETAILS_SID,
                                  PERIOD_SID) AD
                    ON A.PERIOD_SID = AD.PERIOD_SID
                       AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
			WHERE MAS.FILTER_CCP=0
      GROUP  BY C.PROD_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION
      UNION ALL
      SELECT C.PROD_HIERARCHY_NO,
             PERIOD,
             YEAR,
             SUM(PROJECTION_SALES),
             SUM(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C.SALES_INCLUSION,
             1 INDICATOR
      FROM    ', @CCP_HIERARCHY, ' C
             JOIN ', @S_MASTER_TABLE, ' MAS ON MAS.CCP_DETAILS_SID=C.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               SPM.CCP_DETAILS_SID
                        FROM   ', @S_PROJECTION_TABLE, ' SPM
						WHERE EXISTS (SELECT 1 FROM ', @S_MASTER_TABLE, ' MAS WHERE MAS.CCP_DETAILS_SID=SPM.CCP_DETAILS_SID AND MAS.FILTER_CCP=0)) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
              LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
			  WHERE MAS.FILTER_CCP=0
      GROUP  BY C.PROD_HIERARCHY_NO,
                PERIOD,
                YEAR,
                C.SALES_INCLUSION)A 
                           JOIN  ', @PRODUCT_TABLE_SALES, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                     ')

                EXEC Sp_executesql
                  @SQL
            END

          IF @FLAG = 'UOM'
             AND @SCREEN_NAME = 'SALES'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @sql =Concat(' 
      
         UPDATE      B SET B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM  (SELECT c.ROWID,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
             C1.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
				JOIN ', @S_MASTER_TABLE, ' MAS ON MAS.CCP_DETAILS_SID=C1.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                        WHERE  QUANTITY_INCLUSION = ''Y''
						AND EXISTS (SELECT 1 FROM ', @S_MASTER_TABLE, ' MAS WHERE MAS.CCP_DETAILS_SID=AD.CCP_DETAILS_SID AND MAS.FILTER_CCP=1)
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
                                  LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                     WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                     --   AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
						 AND MAS.FILTER_CCP=1

      GROUP  BY c.ROWID,
                period,
                YEAR,
                c1.SALES_INCLUSION
      UNION all
      SELECT C.ROWID,
             period,
             YEAR,
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C1.SALES_INCLUSION,
             1 INDICATOR
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
				  JOIN ', @S_MASTER_TABLE, ' MAS ON MAS.CCP_DETAILS_SID=C1.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               SPM.CCP_DETAILS_SID
                        FROM   ', @S_PROJECTION_TABLE, '  SPM
						where EXISTS (SELECT 1 FROM ', @S_MASTER_TABLE, ' MAS WHERE MAS.CCP_DETAILS_SID=SPM.CCP_DETAILS_SID AND MAS.FILTER_CCP=1)) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
                                     LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                      WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
						AND MAS.FILTER_CCP=1
      GROUP  BY C.ROWID,
                period,
                YEAR,
                c1.SALES_INCLUSION)A
                           join ', @CUSTOM_TABLE_SALES,
                          '  B ON B.HIERARCHY_NO=A.ROWID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR')

                EXEC Sp_executesql
                  @sql

                SET @sql =Concat(' 
      
         UPDATE      B SET B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM  (SELECT c.ROWID,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
             A.period,
             A.YEAR,
             Sum(( SALES ))    SALES,
             Sum(( QUANTITY * COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
             C1.SALES_INCLUSION,
             0                 INDICATOR
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
				JOIN ', @S_MASTER_TABLE, ' MAS ON MAS.CCP_DETAILS_SID=C1.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM    #period
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (SELECT ad.CCP_DETAILS_SID,
                               PERIOD_SID,
                               Sum(SALES)    SALES,
                               Sum(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] ad
                        WHERE  QUANTITY_INCLUSION = ''Y''
						AND EXISTS (SELECT 1 FROM ', @S_MASTER_TABLE, ' MAS WHERE MAS.CCP_DETAILS_SID=AD.CCP_DETAILS_SID AND MAS.FILTER_CCP=0)
                        GROUP  BY ad.CCP_DETAILS_SID,
                                  PERIOD_SID) ad
                    ON a.PERIOD_SID = ad.PERIOD_SID
                       AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID
                                  LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                     WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                     --   AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
						 AND MAS.FILTER_CCP=0

      GROUP  BY c.ROWID,
                period,
                YEAR,
                c1.SALES_INCLUSION
      UNION all
      SELECT C.ROWID,
             period,
             YEAR,
             Sum(PROJECTION_SALES),
             Sum(PROJECTION_UNITS * COALESCE(NULLIF(UOM_VALUE, 0),1)),
             C1.SALES_INCLUSION,
             1 INDICATOR
      FROM   ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID
				  JOIN ', @S_MASTER_TABLE, ' MAS ON MAS.CCP_DETAILS_SID=C1.CCP_DETAILS_SID
             CROSS JOIN (SELECT PERIOD_SID,
                                period,
                                YEAR
                         FROM   #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
             LEFT JOIN (SELECT SPM.PROJECTION_SALES,
                               SPM.PROJECTION_UNITS,
                               SPM.PERIOD_SID,
                               SPM.CCP_DETAILS_SID
                        FROM   ', @S_PROJECTION_TABLE, '  SPM
						where EXISTS (SELECT 1 FROM ', @S_MASTER_TABLE, ' MAS WHERE MAS.CCP_DETAILS_SID=SPM.CCP_DETAILS_SID AND MAS.FILTER_CCP=0)) A
                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       AND B.PERIOD_SID = A.PERIOD_SID
                                     LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
                                      WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
						AND MAS.FILTER_CCP=0
      GROUP  BY C.ROWID,
                period,
                YEAR,
                c1.SALES_INCLUSION)A
                           join ', @CUSTOM_TABLE_SALES,
                          '  B ON B.HIERARCHY_NO=A.ROWID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.SALES_INCLUSION=A.SALES_INCLUSION
                           AND A.INDICATOR=B.INDICATOR')

                EXEC Sp_executesql
                  @sql
            END
--------------------------------------------------Discont Filter CCP Implemenatation---------------------------------------------
  IF @FLAG = 'F'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN( 'C' )
            BEGIN
                SET @SQL = Concat('UPDATE ', @CUSTOMER_TABLE_DISCOUNT, ' 
SET
                FILTER_CCP = 0 ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat('UPDATE ', @CUSTOMER_TABLE_DISCOUNT, ' 
SET
                FILTER_CCP = 1
WHERE
                HIERARCHY_NO in(
                                SELECT
                                                stc.CUST_HIERARCHY_NO
                                FROM
                                                ', @D_MASTER_TABLE , ' nms inner join ', @CCP_HIERARCHY, ' stc on
                                                stc.CCP_DETAILS_SID = nms.CCP_DETAILS_SID
												INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=nms.RS_CONTRACT_SID
                                                AND nms.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
                                WHERE
                                                nms.FILTER_CCP = 1 
                )')

                EXEC Sp_executesql
                  @SQL

                SET @SQL =Concat('
                UPDATE      B SET B.GROWTH=A.GROWTH
				           ,B.DISCOUNT=A.DISCOUNT
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM 
      (SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, 
                   RI.SELECTED_LEVEL RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       SUM((SALES)) SALES,
       SUM((QUANTITY* COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID AND DM.FILTER_CCP=1
--AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
       SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN SALES
                           END) SALES,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN QUANTITY
                           END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION
       ) ad
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.CUST_HIERARCHY_NO,
       RI.SELECTED_LEVEL,
      PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION
      UNION ALL
     SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       SUM(SPT.PROJECTION_SALES) SALES,
       SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID  AND DM.FILTER_CCP=1
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
              AND B.PERIOD_SID = SPT.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.CUST_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A 
                           JOIN  ', @CUSTOMER_TABLE_DISCOUNT, ' B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND  B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                          AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                          WHERE B.FILTER_CCP=1 
                     ')
                EXEC Sp_executesql
                  @sql
            END

    IF @FLAG = 'F'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW IN( 'P' )
            BEGIN
                SET @SQL = Concat('UPDATE ', @PRODUCT_TABLE_DISCOUNT, ' 
SET
                FILTER_CCP = 0 ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat('UPDATE ', @PRODUCT_TABLE_DISCOUNT, ' 
SET
                FILTER_CCP = 1
WHERE
                HIERARCHY_NO in(
                                SELECT
                                                stc.PROD_HIERARCHY_NO
                                FROM
                                                ', @D_MASTER_TABLE , ' nms inner join ', @CCP_HIERARCHY, ' stc on
                                                stc.CCP_DETAILS_SID = nms.CCP_DETAILS_SID
												INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=nms.RS_CONTRACT_SID
                                                AND nms.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
                                WHERE
                                                nms.FILTER_CCP = 1 
                )')

                EXEC Sp_executesql
                  @SQL

                SET @SQL =Concat('
              UPDATE      B SET B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                           ,B.SALES=A.SALES
                           ,B.UNITS=A.UNITS
                FROM  (SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
        --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL RS_CONTRACT_SID,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       SUM((SALES)) SALES,
       SUM((QUANTITY* COALESCE(NULLIF(UOM_VALUE, 0),1))) UNITS,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID AND DM.FILTER_CCP=1
CROSS JOIN (
       SELECT PERIOD_SID,
              PERIOD,
              YEAR
       FROM #PERIOD
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
       SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN SALES
                           END) SALES,
              SUM(CASE 
                           WHEN QUANTITY_INCLUSION = ''Y''
                                  THEN QUANTITY
                           END) QUANTITY,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION
       ) AD
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY C.PROD_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       SUM(SPT.PROJECTION_SALES) SALES,
       SUM(SPT.PROJECTION_UNITS* COALESCE(NULLIF(UOM_VALUE, 0),1)) UNITS,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID AND DM.FILTER_CCP=1
-- AND LEVEL_NO = 6
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN ', @S_PROJECTION_TABLE, ' SPT  
ON C.CCP_DETAILS_SID = SPT.CCP_DETAILS_SID
              AND B.PERIOD_SID = SPT.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.PROD_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A 
                           join ', @PRODUCT_TABLE_DISCOUNT, '  B ON B.HIERARCHY_NO=A.HIERARCHY_NO
                           AND B.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           WHERE B.FILTER_CCP=1  ')

                EXEC Sp_executesql
                  @sql
            END

 IF @FLAG = 'F'
             AND @SCREEN_NAME = 'DISCOUNT'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
                SET @SQL = Concat('UPDATE ', @CUSTOM_TABLE_DISCOUNT, ' 
SET
                FILTER_CCP = 0 ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat('UPDATE ', @CUSTOM_TABLE_DISCOUNT, ' 
SET
                FILTER_CCP = 1
WHERE
                HIERARCHY_NO in(
                                SELECT
                                                stc.ROWID
                                FROM
                                                ', @D_MASTER_TABLE, ' nms inner join ',@CUSTOM_CCP_SALES,' stc on
                                                stc.CCP_DETAILS_SID = nms.CCP_DETAILS_SID AND
												(STC.RS_CONTRACT_SID=NMS.RS_CONTRACT_SID OR STC.RS_CONTRACT_SID=0)
												 AND STC.CUST_VIEW_MASTER_SID =', @CUSTOM_VIEW_MASTER_SID, ' 
                                                 AND STC.LEVEL_NO= ', @CUSTOM_LEVEL_NO, ' 
                                WHERE
                                                nms.FILTER_CCP = 1 
                )')

                EXEC Sp_executesql
                  @SQL

                SET @SQL =Concat('
                UPDATE      B SET B.GROWTH=A.GROWTH
                           ,B.DISCOUNT=A.DISCOUNT
                FROM  (SELECT C.ROWID
,A.PERIOD
,A.YEAR
,ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
      FROM   
          ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL) AND DM.FILTER_CCP=1
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (
       SELECT CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION
       ) AD
       ON AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID 
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND A.PERIOD_SID = AD.PERIOD_SID
              WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
       A.PERIOD,
       A.YEAR,
       DM.DEDUCTION_INCLUSION

      UNION ALL

SELECT C.ROWID
,PERIOD
,YEAR
,SUM(PTD.PROJECTION_SALES) DISCOUNT,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
      FROM    ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL) AND DM.FILTER_CCP=1
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION)A
                           JOIN ', @CUSTOM_TABLE_DISCOUNT, '  B ON B.HIERARCHY_NO=A.ROWID
                           AND B.PERIOD=A.PERIOD
                           AND B.YEAR=A.YEAR
                           AND B.DEDUCTION_INCLUSION=A.DEDUCTION_INCLUSION
                           AND A.INDICATOR=B.INDICATOR
                           WHERE B.FILTER_CCP=1
                           ')

                EXEC Sp_executesql
                  @sql
            END

-------------------------------Populating PV Filter CCP View tables----------------------------------
     IF @FLAG = 'G'
             AND @SCREEN_NAME = 'Variance'
             AND @VIEW IN ( 'C' )
            BEGIN
			--set @sql=concat('update ',@CCP_PV_FILTERS,' set PV_FILTERS=1 ')
			-- EXEC Sp_executesql
              --    @sql
                SET @sql =Concat('

       INSERT INTO ', @CUSTOMER_DISCOUNT_PV , ' (
       HIERARCHY_NO,
       RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DISCOUNT,
       DEDUCTION_INCLUSION,
       INDICATOR,
       GROWTH
       )
SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID --AND DM.PV_FILTERS=1
INNER JOIN ', @CCP_PV_FILTERS, '  CPV ON cpv.CCP_DETAILS_SID = RI.CCP_DETAILS_SID 
AND RI.RS_CONTRACT_SID=cpv.RS_CONTRACT_SID AND CPV.PV_FILTERS=1
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
       select CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,sum(discount) DISCOUNT  from(SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION) a group by CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID
       ) ad
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.CUST_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.CUST_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
      --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID --AND DM.PV_FILTERS=1
INNER JOIN ', @CCP_PV_FILTERS, '  CPV ON cpv.CCP_DETAILS_SID = RI.CCP_DETAILS_SID 
AND RI.RS_CONTRACT_SID=cpv.RS_CONTRACT_SID AND CPV.PV_FILTERS=1
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY C.CUST_HIERARCHY_NO,
      --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION
')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'G'
             AND @SCREEN_NAME = 'Variance'
             AND @VIEW = 'P'
            BEGIN
				--set @sql=concat('update ',@CCP_PV_FILTERS,' set PV_FILTERS=1 ')
			 --EXEC Sp_executesql
                  --@sql
                SET @sql =Concat(' 
       INSERT INTO ', @PRODUCT_DISCOUNT_PV, '(
       HIERARCHY_NO,
       RS_CONTRACT_SID,
       PERIOD,
       YEAR,
       DISCOUNT,
       DEDUCTION_INCLUSION,
       INDICATOR,
       GROWTH
       )
SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       A.PERIOD,
       A.YEAR,
       ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR,
       NULL AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID  --AND DM.PV_FILTERS=1
INNER JOIN ', @CCP_PV_FILTERS, '  CPV ON cpv.CCP_DETAILS_SID = RI.CCP_DETAILS_SID 
AND RI.RS_CONTRACT_SID=cpv.RS_CONTRACT_SID AND CPV.PV_FILTERS=1
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period


       WHERE PERIOD_SID BETWEEN ', @STAT_SALES_SID, '
                     AND ', @END_SALES_SID, '
       ) A
LEFT JOIN (
      select CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,sum(discount) DISCOUNT from(SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION) a group by CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID
       ) ad
       ON A.PERIOD_SID = AD.PERIOD_SID
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.PROD_HIERARCHY_NO,
      --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION

UNION ALL

SELECT C.PROD_HIERARCHY_NO AS HIERARCHY_NO, -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
       --DM.RS_CONTRACT_SID,
       RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       SUM(PTD.PROJECTION_SALES) DISCOUNT,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR,
       AVG(PTD.GROWTH) AS GROWTH
FROM ', @CCP_HIERARCHY, ' C
INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID  --AND DM.PV_FILTERS=1
INNER JOIN ', @CCP_PV_FILTERS, '  CPV ON cpv.CCP_DETAILS_SID = RI.CCP_DETAILS_SID 
AND RI.RS_CONTRACT_SID=cpv.RS_CONTRACT_SID AND CPV.PV_FILTERS=1
CROSS JOIN (
       SELECT PERIOD_SID,
              period,
              YEAR
       FROM #period
       WHERE PERIOD_SID BETWEEN ', @PROJ_START_SID, '
                     AND ', @PROJ_END_SID, '
       ) B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
GROUP BY c.PROD_HIERARCHY_NO,
       --DM.RS_CONTRACT_SID,
                   ri.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION')

                EXEC Sp_executesql
                  @sql
            END

          IF @FLAG = 'G'
             AND @SCREEN_NAME = 'Variance'
             AND @VIEW NOT IN ( 'C', 'P' )
            BEGIN
				--set @sql=concat('update ',@CCP_PV_FILTERS,' set PV_FILTERS=1 ')
			 --EXEC Sp_executesql
                  --@sql
                SET @sql =Concat(' 
       INSERT INTO ', @CUSTOM_DISCOUNT_PV , '(HIERARCHY_NO,
RS_CONTRACT_SID
,PERIOD
,YEAR
,DISCOUNT
,DEDUCTION_INCLUSION
,INDICATOR
--,GROWTH
)
SELECT C.ROWID,
RI.SELECTED_LEVEL AS RS_CONTRACT_SID
,A.PERIOD
,A.YEAR
,ISNULL(sum(DISCOUNT), 0) DISCOUNT,
       DM.DEDUCTION_INCLUSION,
       0 INDICATOR
       --,NULL AS GROWTH
      FROM   ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL) 
	   INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
        AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
INNER JOIN ', @CCP_PV_FILTERS, '  CPV ON cpv.CCP_DETAILS_SID = RI.CCP_DETAILS_SID 
AND RI.RS_CONTRACT_SID=cpv.RS_CONTRACT_SID AND CPV.PV_FILTERS=1
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @STAT_SALES_SID, ' AND ', @END_SALES_SID, ')A
             LEFT JOIN (
      select CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,sum(discount) DISCOUNT
                                                  from(SELECT AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              SUM(DISCOUNT) DISCOUNT
       FROM [ACTUALS_DETAILS] AD
       GROUP BY AD.CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID,
              QUANTITY_INCLUSION) a group by CCP_DETAILS_SID,
              PERIOD_SID,
              RS_MODEL_SID
       ) AD
       ON AD.CCP_DETAILS_SID = C.CCP_DETAILS_SID 
              AND AD.RS_MODEL_SID = DM.RS_MODEL_SID
              AND A.PERIOD_SID = AD.PERIOD_SID
--LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.CCP_DETAILS_SID=C.CCP_DETAILS_SID
              WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID ,
              --  DM.RS_CONTRACT_SID,
			 RI.SELECTED_LEVEL,
       A.PERIOD,
       A.YEAR,
       DM.DEDUCTION_INCLUSION

      UNION ALL

SELECT C.ROWID ,
      RI.SELECTED_LEVEL AS RS_CONTRACT_SID
     ,PERIOD
     ,YEAR
     ,SUM(PTD.PROJECTION_SALES) DISCOUNT,
       DM.DEDUCTION_INCLUSION,
       1 INDICATOR
       --,AVG(PTD.GROWTH) AS GROWTH
FROM    ',@CUSTOM_CCP_SALES,' C
       INNER JOIN ', @D_MASTER_TABLE, ' DM
       ON DM.CCP_DETAILS_SID = C.CCP_DETAILS_SID
       AND (DM.RS_CONTRACT_SID=C.RS_CONTRACT_SID OR  NULLIF(C.RS_CONTRACT_SID,0) IS NULL) 
	    INNER JOIN #RS_INFO RI ON RI.RS_CONTRACT_SID=DM.RS_CONTRACT_SID
        AND DM.CCP_DETAILS_SID = RI.CCP_DETAILS_SID
INNER JOIN ', @CCP_PV_FILTERS, '  CPV ON cpv.CCP_DETAILS_SID = RI.CCP_DETAILS_SID 
AND RI.RS_CONTRACT_SID=cpv.RS_CONTRACT_SID AND CPV.PV_FILTERS=1
             CROSS JOIN (SELECT PERIOD_SID,
                                PERIOD,
                                YEAR
                         FROM    #PERIOD
                          WHERE  PERIOD_SID BETWEEN ', @PROJ_START_SID, ' AND ', @PROJ_END_SID, ')B
LEFT JOIN ', @D_PROJECTION_TABLE, ' PTD  
       ON C.CCP_DETAILS_SID = PTD.CCP_DETAILS_SID
              AND DM.RS_CONTRACT_SID = PTD.RS_CONTRACT_SID
              AND B.PERIOD_SID = PTD.PERIOD_SID
WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                       -- AND C.VERSION_NO= ', @CUSTOM_VERSION_NO, '
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
                        AND EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CCP WHERE CCP.CCP_DETAILS_SID=C.CCP_DETAILS_SID)
      GROUP  BY C.ROWID,
               -- DM.RS_CONTRACT_SID,
			    RI.SELECTED_LEVEL,
       PERIOD,
       YEAR,
       DM.DEDUCTION_INCLUSION')

                EXEC Sp_executesql
                  @sql
            END

          SET @sql =Concat('UPDATE ', @STATUS_TABLE, '
SET    FLAG = ''C''
WHERE  SCREEN_NAME= ''', @SCREEN_NAME, ''' 
       AND VIEW_NAME = CASE ''', @VIEW, '''
                         WHEN ''C'' THEN ''CUSTOMER''
                         WHEN ''P'' THEN ''PRODUCT''
                         ELSE ''CUSTOM''
                       END ')

          EXEC Sp_executesql
            @sql
	
      END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC Usperrorcollector

          SELECT @ERRORMESSAGE = Error_message(),
                 @ERRORSEVERITY = Error_severity(),
                 @ERRORSTATE = Error_state(),
                 @ERRORPROCEDURE = Error_procedure(),
                 @ERRORLINE = Error_line(),
                 @ERRORNUMBER = Error_number()

          RAISERROR ( @ERRORMESSAGE,-- MESSAGE TEXT.
                      @ERRORSEVERITY,-- SEVERITY.
                      @ERRORSTATE,-- STATE.
                      @ERRORPROCEDURE,-- PROCEDURE_NAME.
                      @ERRORNUMBER,-- ERRORNUMBER
                      @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END  
GO