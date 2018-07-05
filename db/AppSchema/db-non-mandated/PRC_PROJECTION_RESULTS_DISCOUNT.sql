IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_PROJECTION_RESULTS_DISCOUNT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_PROJECTION_RESULTS_DISCOUNT]
  END

GO
CREATE PROCEDURE [dbo].[PRC_PROJECTION_RESULTS_DISCOUNT] (@PROJECTION_SID         VARCHAR(500),
                                                          @PROJ_FREQUENCY         VARCHAR(20),
                                                          @DISCOUNT_ID            NVARCHAR(MAX),---INPUT IS  RS_CONTRACT_SID --GAL-1163
                                                          @SCREEN_NAME            VARCHAR(50),
                                                          @SESSION_ID             VARCHAR(50),
                                                          @USER_ID                INT,
                                                          @ORDER_FLAG             BIT,
                                                          @PROGRAM_TYPE           VARCHAR(50) = 'Program',
                                                          @CCP                    VARCHAR(MAX) = NULL,
                                                          @SALES_INCLUSION        BIT,
                                                          @DEDUCTION_INCLUSION    BIT,
                                                          @UOM_CODE               VARCHAR(10),
                                                          @FILTER_UDCS            VARCHAR(MAX)=NULL,
                                                          @INDICATOR              CHAR(1),
                                                          @CUSTOM_VIEW_MASTER_SID INT=NULL,
                                                          @CUSTOM_LEVEL_NO        INT=NULL)
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
       DECLARE @SP                    INT,
                  @SP_PROJ_SID           INT,
                  @TEMP_PROJ_SID         VARCHAR(500),
                  @START_PERIOD_SID      INT,
                  @END_PERIOD_SID        INT,
                  @STARTFROM             DATE,
                  @START_DATE            DATE,
                  @PROJECTION_DATE       DATE,
                  @PROJ_START_PERIOD_SID INT,
                  @CURRENT_DATE          DATE
          DECLARE @SALES_ACTUAL_TABLE     VARCHAR(200) = Concat ('ST_NM_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @SALES_PROJECTION_TABLE VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @SALES_MASTER_TABLE     VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @DISC_PROJECTION_TABLE  VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @DISC_ACTUAL_TABLE      VARCHAR(200) = Concat ('ST_NM_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @DISC_MASTER_TABLE      VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @PPA_PROJECTION_TABLE   VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @PPA_ACTUAL_TABLE       VARCHAR(200) = Concat ('ST_NM_ACTUAL_PPA_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @PPA_MASTER_TABLE       VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @CCP_HIERARCHY          VARCHAR(200) = Concat ('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),---- ccp deatils information
                  @PRODUCT_FILE           VARCHAR(100) = Concat ('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),-----lastest file of that product based on projection
                  @ITEM_UOM_TABLE         VARCHAR(100) = Concat ('ST_ITEM_UOM_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @CUSTOMER_TABLE_SALES VARCHAR(200) = Concat('ST_CUSTOMER_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @PRODUCT_TABLE_SALES VARCHAR(200) = Concat ('ST_PRODUCT_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @CUSTOM_TABLE_SALES  VARCHAR(200) = Concat ('ST_CUSTOM_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @CUSTOMER_TABLE_DISCOUNT VARCHAR(200) = Concat('ST_CUSTOMER_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @PRODUCT_TABLE_DISCOUNT VARCHAR(200) = Concat ('ST_PRODUCT_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @STATUS_TABLE           VARCHAR(200) = Concat ('ST_STATUS_TABLE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @CUSTOM_TABLE_DISCOUNT  VARCHAR(200) = Concat ('ST_CUSTOM_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @sales_table            VARCHAR(200),
                  @discount_table         VARCHAR(200)

          IF Object_id('TEMPDB..#PROJECTION_MASTER') IS NOT NULL
            TRUNCATE TABLE #PROJECTION_MASTER
          ELSE
            CREATE TABLE #PROJECTION_MASTER
              (
                 ID                    INT IDENTITY(1, 1),
                 PROJECTION_MASTER_SID INT
              )

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

          IF @indicator = 'c'
            BEGIN
                SET @sales_table=@CUSTOMER_TABLE_SALES
                SET @discount_table=@CUSTOMER_TABLE_DISCOUNT
            END
          ELSE IF @indicator = 'P'
            BEGIN
                SET @sales_table=@PRODUCT_TABLE_SALES
                SET @discount_table=@PRODUCT_TABLE_DISCOUNT
            END
          ELSE IF @indicator NOT IN ( 'C', 'P' )
            BEGIN
                SET @sales_table=@CUSTOM_TABLE_SALES
                SET @discount_table=@CUSTOM_TABLE_DISCOUNT
            END

          DECLARE @FIRST_PROJ_SID INT

          SELECT @FIRST_PROJ_SID = PM.PROJECTION_MASTER_SID
          FROM   #PROJECTION_MASTER PM
          WHERE  ID = 1

          DECLARE @SQL NVARCHAR(MAX)

          SET @SQL = Concat ('SELECT @START_PERIOD_SID = Min(ST.PERIOD_SID)
	,@END_PERIOD_SID = Max(ST.PERIOD_SID)
FROM ', @SALES_PROJECTION_TABLE, ' ST
')

          EXEC Sp_executesql
            @sql,
            N'@START_PERIOD_SID int OUTPUT,  @END_PERIOD_SID int OUTPUT',
            @START_PERIOD_SID = @START_PERIOD_SID OUTPUT,
            @END_PERIOD_SID = @END_PERIOD_SID OUTPUT

          SELECT TOP 1 @STARTFROM = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0),
                       @START_DATE = Dateadd(qq, Datediff(qq, 0, FROM_DATE), 0),---Dateadd(dd, 1, Eomonth(FROM_DATE, - 1))                   
                       @PROJECTION_DATE = Dateadd(dd, 1, Eomonth(TO_DATE, -1))
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
          ORDER  BY PROJECTION_MASTER_SID

          SELECT @START_PERIOD_SID = Max(CASE
                                           WHEN PERIOD_DATE = @STARTFROM THEN PERIOD_SID
                                         END),
                 @END_PERIOD_SID = Max(CASE
                                         WHEN PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0) THEN PERIOD_SID
                                       END),
                 @PROJ_START_PERIOD_SID = Max(CASE
                                                WHEN PERIOD_DATE = @START_DATE THEN PERIOD_SID
                                              END)
          FROM   PERIOD
          WHERE  PERIOD_DATE IN ( @STARTFROM, @PROJECTION_DATE, @START_DATE )

          SET @CURRENT_DATE = CASE
                                WHEN LEFT(@PROJ_FREQUENCY, 1) = 'M' THEN Dateadd(DD, 1, Eomonth(Getdate(), -1))
                                WHEN LEFT(@PROJ_FREQUENCY, 1) = 'Q' THEN Datefromparts(Year(Getdate()), Datepart(QUARTER, Getdate()), 01)
                                WHEN LEFT(@PROJ_FREQUENCY, 1) = 'S' THEN Datefromparts(Year(Getdate()), ( ( ( ( ( Month(Getdate()) ) - 1 ) / 6 ) * 6 ) + 1 ), 01)
                                ELSE Datefromparts(Year(Getdate()), 01, 01)
                              END

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
                   ELSE 01
                 END AS PERIOD
          INTO   #PERIOD
          FROM   PERIOD
          WHERE  period_sid BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID

          IF Object_id('TEMPDB..#TEMP_CCP') IS NOT NULL
            DROP TABLE #TEMP_CCP

          CREATE TABLE #TEMP_CCP
            (
               COMPANY_MASTER_SID  INT,
               CONTRACT_MASTER_SID INT,
               ITEM_MASTER_SID     INT
               --,PROJECTION_DETAILS_SID INT
               --,PROJECTION_MASTER_SID INT
               ,
               CCP_DETAILS_SID     INT,
               hierarchy_no        VARCHAR(8000),
               RS_CONTRACT_SID     INT
            )

          IF @indicator IN ( 'C', 'p' )
            BEGIN
                SET @SQL = Concat('INSERT INTO #TEMP_CCP (	
	COMPANY_MASTER_SID
	,CONTRACT_MASTER_SID
	,ITEM_MASTER_SID
	,CCP_DETAILS_SID
	,HIERARCHY_NO
	)
SELECT CCP.COMPANY_MASTER_SID
	,CCP.CONTRACT_MASTER_SID
	,CCP.ITEM_MASTER_SID
	,CCP.CCP_DETAILS_SID ', CASE @indicator
                                                      WHEN 'C' THEN ', ch.CUST_HIERARCHY_NO'
                                                      WHEN 'P' THEN ',ch.PROD_HIERARCHY_NO'
                                                    END, '
FROM CCP_DETAILS CCP
--INNER JOIN PROJECTION_DETAILS PD ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
--	AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
INNER JOIN ' + @CCP_HIERARCHY + ' CH ON CH.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
	AND (
		EXISTS (
			SELECT TOKEN
			FROM UDF_SPLITSTRING(@CCP, '','') U
			WHERE ', CASE @indicator
                                         WHEN 'C' THEN ' ch.CUST_HIERARCHY_NO'
                                         WHEN 'P' THEN 'ch.PROD_HIERARCHY_NO'
                                       END, ' like U.TOKEN+''%''
			)
		OR @CCP IS NULL
		)  ')

                EXEC Sp_executesql
                  @SQL,
                  N'@CCP VARCHAR(MAX),@FIRST_PROJ_SID INT',
                  @CCP = @CCP,
                  @FIRST_PROJ_SID=@FIRST_PROJ_SID
            END
          ELSE IF @INDICATOR NOT IN ( 'C', 'P' )
            BEGIN
                SET @SQL = Concat('INSERT INTO #TEMP_CCP (	
	COMPANY_MASTER_SID
	,CONTRACT_MASTER_SID
	,ITEM_MASTER_SID
	,CCP_DETAILS_SID
	,HIERARCHY_NO,RS_CONTRACT_SID
	)
SELECT CCP.COMPANY_MASTER_SID
	,CCP.CONTRACT_MASTER_SID
	,CCP.ITEM_MASTER_SID
	,CCP.CCP_DETAILS_SID ,c.rowid,c.RS_CONTRACT_SID
FROM  CUSTOM_CCP_sales C
                  join ', @CCP_HIERARCHY, ' ch
                     on  ch.CCP_DETAILS_SID = c.CCP_DETAILS_SID
					 --and ch.RS_CONTRACT_SID = c.RS_CONTRACT_SID
				join  CCP_DETAILS CCP on ch.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
	AND (
		EXISTS (
			SELECT TOKEN
			FROM UDF_SPLITSTRING(@CCP, '','') U
			WHERE HIERARCHY_NO like U.TOKEN+''%''
			)
		OR @CCP IS NULL
		)  ')

                EXEC Sp_executesql
                  @SQL,
                  N'@CCP VARCHAR(MAX),@FIRST_PROJ_SID INT',
                  @CCP = @CCP,
                  @FIRST_PROJ_SID=@FIRST_PROJ_SID
            END

          IF Object_id('TEMPDB..#SPLIT_TABLE') IS NOT NULL
            DROP TABLE #SPLIT_TABLE

          SELECT TOKEN
          INTO   #SPLIT_TABLE
          FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',')

          IF Object_id('TEMPDB..#ITEM_UOM_DETAILS') IS NOT NULL
            DROP TABLE #ITEM_UOM_DETAILS

          CREATE TABLE #ITEM_UOM_DETAILS
            (
               ITEM_MASTER_SID INT,
               UOM_VALUE       NUMERIC(22, 6),
               UOM_CODE        VARCHAR(50)
            )

          SET @SQL = 'INSERT INTO #ITEM_UOM_DETAILS (
	ITEM_MASTER_SID
	,UOM_VALUE
	,UOM_CODE
	)
SELECT IU.ITEM_MASTER_SID
	,UOM_VALUE
	,UOM_CODE
FROM ' + @ITEM_UOM_TABLE + ' IU
WHERE IU.UOM_CODE = @UOM_CODE
	AND EXISTS (
		SELECT 1
		FROM #TEMP_CCP CD
		WHERE CD.ITEM_MASTER_SID = IU.ITEM_MASTER_SID
		)
 '

          EXEC Sp_executesql
            @SQL,
            N'@UOM_CODE VARCHAR(50)',
            @UOM_CODE = @UOM_CODE

          IF Object_id('TEMPDB..#RS_INFO') IS NOT NULL
            DROP TABLE #RS_INFO

          CREATE TABLE #RS_INFO
            (
               -- CCP_DETAILS_SID INT,
               RS_CONTRACT_SID     INT,
               SELECTED_LEVEL      INT,
               SELECTED_LEVEL_DESC VARCHAR(100)
            )

          SET @SQL=Concat('SELECT RS_CONTRACT_SID,SELECTED_LEVEL,SELECTED_LEVEL_DESC FROM 
	  (SELECT DISTINCT RS_CONTRACT_SID,SELECTED_LEVEL,SELECTED_LEVEL_DESC FROM 
	  (SELECT DISTINCT --CCP_DETAILS_SID,
		R.RS_CONTRACT_SID
            ,', CASE @PROGRAM_TYPE
                                     WHEN 'SCHEDULE ID' THEN ' R.RS_CONTRACT_SID'
                                     WHEN 'PROGRAM' THEN ' R.RS_CONTRACT_SID'
                                     --WHEN 'SCHEDULE TYPE' then ' R_TYPE.DESCRIPTION'
                                     --WHEN 'PROGRAM CATEGORY' then ' RPT.DESCRIPTION'
                                     --WHEN 'PROGRAM TYPE' then ' RPT.DESCRIPTION'
                                     --WHEN 'SCHEDULE CATEGORY' then ' RC.DESCRIPTION		'	  
                                     WHEN 'SCHEDULE TYPE' THEN ' RS_TYPE'
                                     WHEN 'PROGRAM CATEGORY' THEN ' REBATE_PROGRAM_TYPE'
                                     WHEN 'PROGRAM TYPE' THEN ' REBATE_PROGRAM_TYPE'
                                     WHEN 'SCHEDULE CATEGORY' THEN ' RS_CATEGORY'
                                     WHEN 'UDC1' THEN ' UD.UDC1 '
                                     WHEN 'UDC2' THEN ' UD.UDC2 '
                                     WHEN 'UDC3' THEN ' UD.UDC3 '
                                     WHEN 'UDC4' THEN ' UD.UDC4 '
                                     WHEN 'UDC5' THEN ' UD.UDC5 '
                                     WHEN 'UDC6' THEN ' UD.UDC6 '
                                   END, ' SELECTED_LEVEL, ', CASE @PROGRAM_TYPE
                                                               WHEN 'SCHEDULE ID' THEN ' CONVERT(VARCHAR(100),R.RS_ID)'
                                                               WHEN 'PROGRAM' THEN ' CONVERT(VARCHAR(100),R.RS_NAME)'
                                                               WHEN 'SCHEDULE TYPE' THEN ' R_TYPE.DESCRIPTION'
                                                               WHEN 'PROGRAM CATEGORY' THEN ' RPT.DESCRIPTION'
                                                               WHEN 'PROGRAM TYPE' THEN ' RPT.DESCRIPTION'
                                                               WHEN 'SCHEDULE CATEGORY' THEN ' RC.DESCRIPTION	'
                                                               --WHEN 'SCHEDULE TYPE' then ' RS_TYPE'
                                                               --WHEN 'PROGRAM CATEGORY' then ' REBATE_PROGRAM_TYPE'
                                                               --WHEN 'PROGRAM TYPE' then ' REBATE_PROGRAM_TYPE'
                                                               --WHEN 'SCHEDULE CATEGORY' then ' RS_CATEGORY'
                                                               WHEN 'UDC1' THEN ' UD.UDC1_1 '
                                                               WHEN 'UDC2' THEN ' UD.UDC2_1 '
                                                               WHEN 'UDC3' THEN ' UD.UDC3_1 '
                                                               WHEN 'UDC4' THEN ' UD.UDC4_1 '
                                                               WHEN 'UDC5' THEN ' UD.UDC5_1 '
                                                               WHEN 'UDC6' THEN ' UD.UDC6_1 '
                                                             END, ' SELECTED_LEVEL_DESC
     FROM   RS_CONTRACT R
            LEFT JOIN HELPER_TABLE R_TYPE
                   ON R_TYPE.HELPER_TABLE_SID = R.RS_TYPE
            LEFT JOIN HELPER_TABLE RPT
                   ON RPT.HELPER_TABLE_SID = R.REBATE_PROGRAM_TYPE
            LEFT JOIN HELPER_TABLE RC
                   ON RC.HELPER_TABLE_SID = R.RS_CATEGORY
            LEFT JOIN (SELECT U.MASTER_SID,
                              U1.DESCRIPTION AS UDC1_1,
                              U2.DESCRIPTION AS UDC2_1,
                              U3.DESCRIPTION AS UDC3_1,
                              U4.DESCRIPTION AS UDC4_1,
                              U5.DESCRIPTION AS UDC5_1,
                              U6.DESCRIPTION UDC6_1
							  ,U.UDC1		UDC1
							  ,U.UDC2		UDC2
							  ,U.UDC3		UDC3
							  ,U.UDC4		UDC4
							  ,U.UDC5		UDC5
							  ,U.UDC6		UDC6
                       FROM   UDCS U
                              LEFT JOIN HELPER_TABLE U1
                                     ON U1.HELPER_TABLE_SID = U.UDC1
                              LEFT JOIN HELPER_TABLE U2
                                     ON U2.HELPER_TABLE_SID = U.UDC2
                              LEFT JOIN HELPER_TABLE U3
                                     ON U3.HELPER_TABLE_SID = U.UDC3
                              LEFT JOIN HELPER_TABLE U4
                                     ON U4.HELPER_TABLE_SID = U.UDC4
                              LEFT JOIN HELPER_TABLE U5
                                     ON U5.HELPER_TABLE_SID = U.UDC5
                              LEFT JOIN HELPER_TABLE U6
                                     ON U6.HELPER_TABLE_SID = U.UDC6
                       WHERE  MASTER_TYPE = ''RS_CONTRACT'') UD
					   ON UD.MASTER_SID = R.RS_CONTRACT_SID
			INNER JOIN ', @DISC_MASTER_TABLE, ' D
                    ON  R.RS_CONTRACT_SID = D.RS_CONTRACT_SID 

			--LEFT JOIN UDCS UD
   --                ON UD.MASTER_SID = R.RS_CONTRACT_SID
			--	   AND MASTER_TYPE = ''RS_CONTRACT''    
			WHERE EXISTS (SELECT 1 FROM [UDF_SPLITSTRING](@DISCOUNT_ID, '','') HD WHERE HD.TOKEN=R.RS_CONTRACT_SID
			) OR @DISCOUNT_ID IS NULL) A WHERE EXISTS (SELECT 1 FROM [UDF_SPLITSTRING](@FILTER_UDCS, '','') HD WHERE HD.TOKEN=A.SELECTED_LEVEL
			) OR @FILTER_UDCS IS NULL) B  WHERE EXISTS (SELECT 1 FROM  #TEMP_CCP HD WHERE HD.RS_CONTRACT_SID=B.RS_CONTRACT_SID OR HD.RS_CONTRACT_SID IS NULL
			) 
			')

          --select @sql
          INSERT INTO #RS_INFO
          EXEC Sp_executesql
            @sql,
            N'@DISCOUNT_ID varchar(max),@PROGRAM_TYPE varcHar(100),@FILTER_UDCS varchar(max)',
            @DISCOUNT_ID=@DISCOUNT_ID,
            @PROGRAM_TYPE=@PROGRAM_TYPE,
            @FILTER_UDCS=@FILTER_UDCS

          DELETE FROM #RS_INFO
          WHERE  SELECTED_LEVEL IS NULL

          IF Object_id('TEMPDB..#MULTISELECT_DISCOUNTS') IS NOT NULL
            DROP TABLE #MULTISELECT_DISCOUNTS;

          CREATE TABLE #MULTISELECT_DISCOUNTS
            (
               CCP_DETAILS_SID     INT,
               RS_CONTRACT_SID     INT,
               RS_MODEL_SID        INT,
               item_master_Sid     INT,
               HIERARCHY_NO        VARCHAR(8000),
               SELECTED_LEVEL      INT,
               SELECTED_LEVEL_DESC VARCHAR(100)
            )

          SET @SQL = Concat ('INSERT INTO #MULTISELECT_DISCOUNTS (
	CCP_DETAILS_SID
	,RS_CONTRACT_SID
	,RS_MODEL_SID
	,item_master_Sid
	,HIERARCHY_NO	,SELECTED_LEVEL
	,SELECTED_LEVEL_DESC
	)
SELECT A.CCP_DETAILS_SID
	,A.RS_CONTRACT_SID
	,A.RS_MODEL_SID
	,b.item_master_Sid
	,B.HIERARCHY_NO
	,RI.SELECTED_LEVEL
	,RI.SELECTED_LEVEL_DESC
FROM ', @DISC_MASTER_TABLE, ' A
JOIN #TEMP_CCP B ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID
JOIN #RS_INFO RI ON RI.RS_CONTRACT_sID=A.RS_CONTRACT_sID
WHERE --PV_FILTERS = 1 and 
EXISTS (SELECT 1 FROM #TEMP_CCP B WHERE A.CCP_DETAILS_SID=B.CCP_DETAILS_SID and (A.RS_CONTRACT_SID=B.RS_CONTRACT_SID or B.RS_CONTRACT_SID is null)) 
', CASE
                          WHEN @DISCOUNT_ID IS NOT NULL THEN ' AND  (EXISTS ( SELECT 1 FROM #SPLIT_TABLE C WHERE C.TOKEN = A.RS_CONTRACT_SID ))'
                          ELSE ''
                        END)

          EXEC Sp_executesql
            @SQL,
            N'@DISCOUNT_ID NVARCHAR(MAX)',
            @DISCOUNT_ID=@DISCOUNT_ID

          IF Object_id('TEMPDB..#PIVOT_RESULT') IS NOT NULL
            TRUNCATE TABLE #PIVOT_RESULT
          ELSE
            CREATE TABLE #PIVOT_RESULT
              (
                 PROJECTION_ID                       INT,
                 [PERIOD]                            SMALLINT,
                 [YEAR]                              INT,
                 RS_NAME                             VARCHAR(100),
                 CONTRACT_DISCOUNT_ACTUAL            NUMERIC(22, 6),
                 CONTRACT_DISCOUNT_PROJECTED         NUMERIC(22, 6),
                 TOTAL_DISCOUNT_ACTUAL_PERCENTAGE    NUMERIC(22, 6),
                 TOTAL_DISCOUNT_PROJECTED_PERCENTAGE NUMERIC(22, 6),
                 TOTAL_RPU_ACTUAL                    NUMERIC(22, 6),
                 TOTAL_RPU_PROJECTED                 NUMERIC(22, 6),
                 DISCOUNT_OF_EX_FACTORY_ACTUALS      NUMERIC(22, 6),
                 DISCOUNT_OF_EX_FACTORY_PROJECTED    NUMERIC(22, 6),
                 RS_CONTRACT_SID                     INT,
                 CONTRACT_DISCOUNT_ACCRUAL           NUMERIC(22, 6),
                 TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE   NUMERIC(22, 6),
                 TOTAL_RPU_ACCRUAL                   NUMERIC(22, 6),
                 DISCOUNT_OF_EX_FACTORY_ACCRUAL      NUMERIC(22, 6)
              )

          DECLARE @VAR INT

          SET @VAR = CASE
                       WHEN @PROGRAM_TYPE = 'PROGRAM'
                             OR @PROGRAM_TYPE = 'SCHEDULE ID'
                             OR @PROGRAM_TYPE IS NULL THEN 1
                       ELSE 0
                     END

          IF Object_id('tempdb..#PRODUCT_FILE_TEMP') IS NOT NULL
            DROP TABLE #PRODUCT_FILE_TEMP

          CREATE TABLE #PRODUCT_FILE_TEMP
            (
               PROJECTION_MASTER_SID          INT,
               ITEM_MASTER_SID                INT,
               PERIOD_SID                     INT,
               EXFACTORY_ACTUAL_SALES         NUMERIC(22, 6),
               EXFACTORY_ACTUAL_UNITS         NUMERIC(22, 6),
               EXFACTORY_FORECAST_SALES       NUMERIC(22, 6),
               EXFACTORY_FORECAST_UNITS       NUMERIC(22, 6),
               DEMAND_ACTUAL_SALES            NUMERIC(22, 6),
               DEMAND_ACTUAL_UNITS            NUMERIC(22, 6),
               DEMAND_FORECAST_SALES          NUMERIC(22, 6),
               DEMAND_FORECAST_UNITS          NUMERIC(22, 6),
               ADJUSTED_DEMAND_ACTUAL_SALES   NUMERIC(22, 6),
               ADJUSTED_DEMAND_ACTUAL_UNITS   NUMERIC(22, 6),
               ADJUSTED_DEMAND_FORECAST_SALES NUMERIC(22, 6),
               ADJUSTED_DEMAND_FORECAST_UNITS NUMERIC(22, 6),
               INVENTORY_ACTUAL_SALES         NUMERIC(22, 6),
               INVENTORY_ACTUAL_UNITS         NUMERIC(22, 6),
               INVENTORY_FORECAST_SALES       NUMERIC(22, 6),
               INVENTORY_FORECAST_UNITS       NUMERIC(22, 6),
               ITEM_PRICE                     NUMERIC(22, 6),
               EXFACTORY_CUST_ACTUAL_SALES    NUMERIC(22, 6),
               EXFACTORY_CUST_ACTUAL_UNITS    NUMERIC(22, 6),
               EXFACTORY_CUST_FORECAST_SALES  NUMERIC(22, 6),
               EXFACTORY_CUST_FORECAST_UNITS  NUMERIC(22, 6),
               INVENTORY_CUST_ACTUAL_SALES    NUMERIC(22, 6),
               INVENTORY_CUST_ACTUAL_UNITS    NUMERIC(22, 6),
               INVENTORY_CUST_FORECAST_SALES  NUMERIC(22, 6),
               INVENTORY_CUST_FORECAST_UNITS  NUMERIC(22, 6)
            )

          IF ( (SELECT Count(ID)
                FROM   #PROJECTION_MASTER) > 1 )
            BEGIN
                INSERT INTO #PRODUCT_FILE_TEMP
                            (PROJECTION_MASTER_SID,
                             ITEM_MASTER_SID,
                             PERIOD_SID,
                             EXFACTORY_ACTUAL_SALES,
                             EXFACTORY_ACTUAL_UNITS,
                             EXFACTORY_FORECAST_SALES,
                             EXFACTORY_FORECAST_UNITS,
                             DEMAND_ACTUAL_SALES,
                             DEMAND_ACTUAL_UNITS,
                             DEMAND_FORECAST_SALES,
                             DEMAND_FORECAST_UNITS,
                             ADJUSTED_DEMAND_ACTUAL_SALES,
                             ADJUSTED_DEMAND_ACTUAL_UNITS,
                             ADJUSTED_DEMAND_FORECAST_SALES,
                             ADJUSTED_DEMAND_FORECAST_UNITS,
                             INVENTORY_ACTUAL_SALES,
                             INVENTORY_ACTUAL_UNITS,
                             INVENTORY_FORECAST_SALES,
                             INVENTORY_FORECAST_UNITS,
                             ITEM_PRICE,
                             EXFACTORY_CUST_ACTUAL_SALES,
                             EXFACTORY_CUST_ACTUAL_UNITS,
                             EXFACTORY_CUST_FORECAST_SALES,
                             EXFACTORY_CUST_FORECAST_UNITS,
                             INVENTORY_CUST_ACTUAL_SALES,
                             INVENTORY_CUST_ACTUAL_UNITS,
                             INVENTORY_CUST_FORECAST_SALES,
                             INVENTORY_CUST_FORECAST_UNITS)
                EXEC [dbo].[Prc_prior_proj_prod_file_data]---dbo.[Prc_prior_proj_prod_file_data_change]
            END

          DECLARE @SQL_ACC NVARCHAR(max)

          IF Object_id('TEMPDB..#ACCRUAL_DISCOUNT') IS NOT NULL
            DROP TABLE #ACCRUAL_DISCOUNT;

          CREATE TABLE #ACCRUAL_DISCOUNT
            (
               HIERARCHY_NO        VARCHAR(8000),
               PERIOD              SMALLINT,
               YEAR                INT,
               DISCOUNT_AMOUNT     NUMERIC(33, 8),
               SELECTED_LEVEL_DESC VARCHAR(100),
               SELECTED_LEVEL      INT
            )

          SET @SQL_ACC= Concat(' ;
WITH CTE
AS (
	SELECT DISTINCT COMPANY_MASTER_SID
		,CONTRACT_MASTER_SID
		,ITEM_MASTER_SID		
		,A.CCP_DETAILS_SID
		,PERIOD_SID
		,PERIOD_DATE
		,YEAR
		,RS.RS_CONTRACT_SID
		,RS.RS_MODEL_SID,a.hierarchy_no,period,ri.selected_level_desc,ri.selected_level
	FROM #TEMP_CCP A
	JOIN ', @DISC_MASTER_TABLE, ' RS ON RS.CCP_DETAILS_SID = A.CCP_DETAILS_SID
	AND PV_FILTERS = 1 and (RS.RS_CONTRACT_SID = A.RS_CONTRACT_SID or A.RS_CONTRACT_SID is null)
	JOIN #PERIOD P on 1=1
	--JOIN PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
	--		AND @END_PERIOD_SID
	join #rs_info ri on ri.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
	--WHERE EXISTS (
	--		SELECT 1
	--		--FROM #RS_DATA Aa
	--		FROM #RS_INFO Aa
	--		WHERE Aa.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
	--		) 
	)
INSERT INTO #ACCRUAL_DISCOUNT (
	HIERARCHY_NO,PERIOD,YEAR
	,DISCOUNT_AMOUNT,selected_level_desc,selected_level
	)
select hierarchy_no,period,year,selected_level_desc,selected_level,SUM(DISCOUNT_AMOUNT) from (SELECT A2.CCP_DETAILS_SID
	,PERIOD_SID
	,A2.RS_CONTRACT_SID,selected_level_desc,selected_level
	,SUM(DEDUCTION_AMOUNT) / (DATEDIFF(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE) + 1) DISCOUNT_AMOUNT,hierarchy_no,period,year
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
	,ACCRUAL_PERIOD_END_DATE,hierarchy_no,period,year,selected_level_desc,selected_level)a group by hierarchy_no,period,year,selected_level_desc,selected_level')

          EXEC Sp_executesql
            @SQL_ACC,
            N'@START_PERIOD_SID INT,@END_PERIOD_SID INT,@STARTFROM DATETIME,@PROJECTION_DATE DATETIME, @DISCOUNT_ID NVARCHAR(MAX)',
            @START_PERIOD_SID = @START_PERIOD_SID,
            @END_PERIOD_SID = @END_PERIOD_SID,
            @STARTFROM = @STARTFROM,
            @PROJECTION_DATE = @PROJECTION_DATE,
            @DISCOUNT_ID = @DISCOUNT_ID

          IF Object_id('TEMPDB.DBO.#DATA_TABLE', 'U') IS NOT NULL
            DROP TABLE #DATA_TABLE;

          WITH CTE
               AS (SELECT DISTINCT SELECTED_LEVEL,
                                   SELECTED_LEVEL_DESC
                   FROM   #RS_INFO
                   WHERE  SELECTED_LEVEL IS NOT NULL)
          SELECT DISTINCT SELECTED_LEVEL,
                          SELECTED_LEVEL_DESC,
                          PERIOD,
                          YEAR
          INTO   #DATA_TABLE
          FROM   CTE A
                 JOIN #PERIOD P
                   ON PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID

          IF Object_id('TEMPDB.DBO.#CURRENT_ST_NM_ACTUAL_DISCOUNT', 'U') IS NOT NULL
            DROP TABLE #CURRENT_ST_NM_ACTUAL_DISCOUNT;

          /*
          CREATE TABLE #CURRENT_ST_NM_ACTUAL_DISCOUNT (
          ACTUAL_SALES NUMERIC(22, 6)
          ,ACCRUAL_DISCOUNT_ACTUAL NUMERIC(22, 6)
          ,PERIOD INT
          ,YEAR INT
          ,DISCOUNT VARCHAR(100)
          )*/
          CREATE TABLE #CURRENT_ST_NM_ACTUAL_DISCOUNT
            (
               ACTUAL_SALES            NUMERIC(22, 6),
               ACCRUAL_DISCOUNT_ACTUAL NUMERIC(22, 6),
               PERIOD                  INT,
               YEAR                    INT,
               selected_level          INT,
               selected_level_desc     VARCHAR(100),
               HIERARCHY_NO            VARCHAR(8000),
               DISCOUNT                VARCHAR(100),
               quantity                NUMERIC(22, 6),
               sales                   NUMERIC(22, 6)
            )

          DECLARE @DISC_Asql NVARCHAR(4000) = ''

          SET @DISC_Asql = '
INSERT INTO #CURRENT_ST_NM_ACTUAL_DISCOUNT
            (SELECTED_LEVEL,
             SELECTED_LEVEL_DESC,
             YEAR,
             PERIOD,
             ACTUAL_SALES,
             ACCRUAL_DISCOUNT_ACTUAL,
             SALES,
             QUANTITY)
SELECT --DT.HIERARCHY_NO,
DT.SELECTED_LEVEL,
DT.SELECTED_LEVEL_DESC,
DT.YEAR,
DT.PERIOD,
Sum(DISCOUNT)        AS CONTRACT_DISCOUNT_ACTUALS,
Max(DISCOUNT_AMOUNT) ACCRUAL_DISCOUNT_ACTUAL,
Sum(SALES)           SALES,
Sum(QUANTITY)        QUANTITY
FROM #DATA_TABLE DT
LEFT JOIN #ACCRUAL_DISCOUNT AD
       ON AD.SELECTED_LEVEL = DT.SELECTED_LEVEL
          AND AD.PERIOD = DT.PERIOD
          AND AD.YEAR = DT.YEAR
          AND AD.SELECTED_LEVEL_DESC = DT.SELECTED_LEVEL_DESC
LEFT JOIN (SELECT SELECTED_LEVEL,
                  SELECTED_LEVEL_DESC,
                  PERIOD,
                  YEAR,
                  Sum(DISCOUNT) AS DISCOUNT,
                  Sum(SALES)    AS SALES,
                  Sum(QUANTITY) AS QUANTITY
           FROM   (SELECT CCP_DETAILS_SID,
                          PERIOD_SID,
                          RS_MODEL_SID,
                          Max(DISCOUNT) DISCOUNT,
                          Sum(CASE
                                WHEN QUANTITY_INCLUSION = ''Y'' THEN SALES
                              END)      SALES,
                          Sum(CASE
                                WHEN QUANTITY_INCLUSION = ''Y'' THEN QUANTITY
                              END)      QUANTITY,
                          SELECTED_LEVEL,
                          SELECTED_LEVEL_DESC
                   FROM   (SELECT AD.CCP_DETAILS_SID,
                                  PERIOD_SID,
                                  AD.RS_MODEL_SID,
                                  Sum(DISCOUNT) DISCOUNT,
                                  Sum(SALES)    SALES,
                                  Sum(QUANTITY) QUANTITY,
                                  QUANTITY_INCLUSION,
                                  SELECTED_LEVEL,
                                  SELECTED_LEVEL_DESC
                           FROM   [ACTUALS_DETAILS] AD
                                  JOIN #MULTISELECT_DISCOUNTS MDS
                                    ON MDS.CCP_DETAILS_SID = AD.CCP_DETAILS_SID
                                       AND MDS.RS_MODEL_SID = AD.RS_MODEL_SID
                           GROUP  BY AD.CCP_DETAILS_SID,
                                     PERIOD_SID,
                                     AD.RS_MODEL_SID,
                                     QUANTITY_INCLUSION,
                                     SELECTED_LEVEL,
                                     SELECTED_LEVEL_DESC)A
                   GROUP  BY CCP_DETAILS_SID,
                             PERIOD_SID,
                             RS_MODEL_SID,
                             SELECTED_LEVEL,
                             SELECTED_LEVEL_DESC)B
                  JOIN (SELECT *
                        FROM   #PERIOD) P
                    ON B.PERIOD_SID = P.PERIOD_SID
           GROUP  BY SELECTED_LEVEL,
                     SELECTED_LEVEL_DESC,
                     PERIOD,
                     YEAR) NAS
       ON NAS.SELECTED_LEVEL = DT.SELECTED_LEVEL
          AND NAS.PERIOD = DT.PERIOD
          AND NAS.YEAR = DT.YEAR
          AND NAS.SELECTED_LEVEL_DESC = DT.SELECTED_LEVEL_DESC
GROUP  BY DT.SELECTED_LEVEL,
          DT.SELECTED_LEVEL_DESC,
          DT.YEAR,
          DT.PERIOD  '

          EXEC Sp_executesql
            @DISC_Asql

          IF Object_id('TEMPDB..#FILE_DATA') IS NOT NULL
            DROP TABLE #FILE_DATA

          CREATE TABLE #FILE_DATA
            (
               HIERARCHY_NO        VARCHAR(8000),
               SELECTED_LEVEL      INT,
               SELECTED_LEVEL_DESC VARCHAR(100),
               PERIOD              INT,
               YEAR                INT,
               GTS_SALES_ACTUALS   NUMERIC(22, 6),
               GTS_SALES_PROJECTED NUMERIC(22, 6)
            )

          SET @SQL = Concat ('
	INSERT INTO #FILE_DATA (
	--HIERARCHY_NO	,
	SELECTED_LEVEL
	,SELECTED_LEVEL_DESC,PERIOD
	,YEAR
	,GTS_SALES_ACTUALS
	,GTS_SALES_PROJECTED
	) ', ' SELECT -- cd1.HIERARCHY_NO	,
	FD.SELECTED_LEVEL
	,FD.SELECTED_LEVEL_DESC	
	,P.PERIOD
	,P.YEAR
	,GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES)
	,GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) 
	FROM #DATA_TABLE FD JOIN 
	#PERIOD P ON P.PERIOD=FD.PERIOD
	AND P.YEAR=FD.YEAR
	JOIN (select distinct item_master_Sid
	--,HIERARCHY_NO	
	,SELECTED_LEVEL
	,SELECTED_LEVEL_DESC from #MULTISELECT_DISCOUNTS) CD1 on fd.SELECTED_LEVEL=cd1.SELECTED_LEVEL
	and fd.SELECTED_LEVEL_DESC=cd1.SELECTED_LEVEL_DESC
	JOIN  ', @PRODUCT_FILE, '  PF ON PF.ITEM_MASTER_SID = CD1.ITEM_MASTER_SID 
	AND PF.PERIOD_SID=P.PERIOD_SID 
	GROUP BY  --cd1.HIERARCHY_NO	,
	FD.SELECTED_LEVEL
	,FD.SELECTED_LEVEL_DESC	
	,P.PERIOD
	,P.YEAR
')

          EXEC Sp_executesql
            @SQL

          IF @INDICATOR IN ( 'C', 'P' )
            BEGIN
                SET @SQL=Concat('INSERT INTO #PIVOT_RESULT (
	PROJECTION_ID	
	,[YEAR]
	,[PERIOD]
	,RS_NAME
	,CONTRACT_DISCOUNT_ACTUAL
	,CONTRACT_DISCOUNT_PROJECTED
	,TOTAL_DISCOUNT_ACTUAL_PERCENTAGE
	,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE
	,TOTAL_RPU_ACTUAL
	,TOTAL_RPU_PROJECTED
	,DISCOUNT_OF_EX_FACTORY_ACTUALS
	,DISCOUNT_OF_EX_FACTORY_PROJECTED
	,RS_CONTRACT_SID
	,CONTRACT_DISCOUNT_ACCRUAL
	)
	SELECT ', @FIRST_PROJ_SID, ',
       DT.YEAR,
       DT.PERIOD,
       DT.SELECTED_LEVEL_DESC,
       TOTAL_DISCOUNT=SUM(ACTUAL_SALES),
       TOTAL_DISCOUNT_PROJECTED=SUM(PROJ.DISCOUNT),
       TOTAL_DISCOUNT_ACTUAL_PERCENTAGE=Isnull(SUM(ACTUAL_SALES) / NULLIF(SUM(ACT.SALES), 0), 0) * 100,
       TOTAL_DISCOUNT_PROJECTED_PERCENTAGE=Isnull(SUM(PROJ.DISCOUNT) / NULLIF(SUM(PROJ.SALES), 0), 0) * 100,
       TOTAL_ACTUAL_RPU=Isnull(SUM(ACTUAL_SALES) / NULLIF(SUM(ACT.quantity), 0), 0),
       TOTAL_PROJECTED_RPU=Isnull(SUM(PROJ.DISCOUNT) / NULLIF(SUM(PROJ.UNITS), 0), 0),
       DISCOUNT_OF_EX_FACTORY_ACTUALS=Isnull(SUM(ACTUAL_SALES) / NULLIF(SUM(PD.GTS_SALES_ACTUALS), 0), 0) * 100,
       DISCOUNT_OF_EX_FACTORY_PROJECTED=Isnull(SUM(PROJ.DISCOUNT) / NULLIF(SUM(PD.GTS_SALES_PROJECTED), 0), 0) * 100,
	   Iif(@VAR = 1, DT.SELECTED_LEVEL, NULL) RS_CONTRACT_SID,
       TOTAL_DISCOUNT_ACCRUAL=SUM(ACD.DISCOUNT_AMOUNT)
       
FROM   #DATA_TABLE DT
       LEFT JOIN #FILE_DATA PD
              ON PD.SELECTED_LEVEL = DT.SELECTED_LEVEL
                 AND PD.YEAR = DT.YEAR
                 AND PD.PERIOD = DT.PERIOD
       LEFT JOIN #CURRENT_ST_NM_ACTUAL_DISCOUNT ACT
              ON ACT.SELECTED_LEVEL = DT.SELECTED_LEVEL
                 AND ACT.YEAR = DT.YEAR
                 AND ACT.PERIOD = DT.PERIOD
       LEFT JOIN (SELECT *
                  FROM   ', @discount_table, ' DIS
                  WHERE  EXISTS (SELECT 1
                                 FROM   #MULTISELECT_DISCOUNTS MD
                                 WHERE  MD.HIERARCHY_NO = DIS.HIERARCHY_NO
                                        AND MD.SELECTED_LEVEL = DIS.RS_CONTRACT_SID)
                         AND DIS.INDICATOR = 1) PROJ
              ON PROJ.RS_CONTRACT_SID = DT.SELECTED_LEVEL
                 AND PROJ.YEAR = DT.YEAR
                 AND PROJ.PERIOD = DT.PERIOD
       LEFT JOIN #ACCRUAL_DISCOUNT ACD
              ON ACD.SELECTED_LEVEL = DT.SELECTED_LEVEL
                 AND ACD.YEAR = DT.YEAR
                 AND ACD.PERIOD = DT.PERIOD
GROUP  BY DT.YEAR,
          DT.PERIOD,
          DT.SELECTED_LEVEL_DESC,
         Iif(@VAR = 1, DT.SELECTED_LEVEL, NULL)')

                EXEC Sp_executesql
                  @SQL,
                  N' @VAR int ',
                  @VAR=@VAR
            END
          ELSE IF @INDICATOR NOT IN ( 'C', 'P' )
            BEGIN
                IF Object_id('TEMPDB..#CUSTOM_SALES_DETAILS') IS NOT NULL
                  DROP TABLE #CUSTOM_SALES_DETAILS

                CREATE TABLE #CUSTOM_SALES_DETAILS
                  (
                     HIERARCHY_NO   VARCHAR(8000),
                     SELECTED_LEVEL INT,
                     PERIOD         INT,
                     YEAR           INT,
                     SALES          NUMERIC(22, 6),
                     UNITS          NUMERIC(22, 6)
                  )

                SET @SQL=Concat('INSERT INTO #CUSTOM_SALES_DETAILS(HIERARCHY_NO,SELECTED_LEVEL,PERIOD,YEAR,SALES,UNITS)
 SELECT HIERARCHY_NO,
        SELECTED_LEVEL,
        PERIOD,
        YEAR,
        Sum(PROJECTION_SALES) SALES,
        Sum(PROJECTION_UNITS) UNITS
 FROM   (SELECT DISTINCT HIERARCHY_NO,
                         SELECTED_LEVEL,
                         CCP_DETAILS_SID
         FROM   #MULTISELECT_DISCOUNTS) MSD
        JOIN ', @SALES_PROJECTION_TABLE, ' SALES
          ON SALES.CCP_DETAILS_SID = MSD.CCP_DETAILS_SID
        JOIN #PERIOD P
          ON P.PERIOD_SID = SALES.PERIOD_SID
		  GROUP BY HIERARCHY_NO,
        SELECTED_LEVEL,
        PERIOD,
        YEAR
 ')

                EXEC Sp_executesql
                  @sql

                IF Object_id('TEMPDB..#CUSTOM_DISCOUNT_DETAILS') IS NOT NULL
                  DROP TABLE #CUSTOM_DISCOUNT_DETAILS

                CREATE TABLE #CUSTOM_DISCOUNT_DETAILS
                  (
                     HIERARCHY_NO   VARCHAR(8000),
                     SELECTED_LEVEL INT,
                     PERIOD         INT,
                     YEAR           INT,
                     DISCOUNT       NUMERIC(22, 6)
                  )

                SET @SQL=Concat('INSERT INTO #CUSTOM_DISCOUNT_DETAILS(HIERARCHY_NO,SELECTED_LEVEL,PERIOD,YEAR,DISCOUNT)
 SELECT MSD.HIERARCHY_NO,
        MSD.SELECTED_LEVEL,
        p.PERIOD,
        p.YEAR,
        Sum(SALES.DISCOUNT) DISCOUNT
 FROM   (SELECT DISTINCT HIERARCHY_NO,
                         SELECTED_LEVEL
         FROM   #MULTISELECT_DISCOUNTS) MSD
        JOIN ', @CUSTOM_TABLE_DISCOUNT, ' SALES
          ON SALES.HIERARCHY_NO = MSD.HIERARCHY_NO
        JOIN #PERIOD P
          ON P.period = SALES.period
		  and P.YEAR = SALES.YEAR
		  where sales.INDICATOR=1
		  group by MSD.HIERARCHY_NO,
        MSD.SELECTED_LEVEL,
        p.PERIOD,
        p.YEAR
 ')

                EXEC Sp_executesql
                  @sql

                SET @SQL=Concat('INSERT INTO #PIVOT_RESULT (
	PROJECTION_ID	
	,[YEAR]
	,[PERIOD]
	,RS_NAME
	,CONTRACT_DISCOUNT_ACTUAL
	,CONTRACT_DISCOUNT_PROJECTED
	,TOTAL_DISCOUNT_ACTUAL_PERCENTAGE
	,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE
	,TOTAL_RPU_ACTUAL
	,TOTAL_RPU_PROJECTED
	,DISCOUNT_OF_EX_FACTORY_ACTUALS
	,DISCOUNT_OF_EX_FACTORY_PROJECTED
	,RS_CONTRACT_SID
	,CONTRACT_DISCOUNT_ACCRUAL
	)
	SELECT ', @FIRST_PROJ_SID, ',
       DT.YEAR,
       DT.PERIOD,
       DT.SELECTED_LEVEL_DESC,
       TOTAL_DISCOUNT=SUM(ACTUAL_SALES),
       TOTAL_DISCOUNT_PROJECTED=SUM(disc_proj.DISCOUNT),
       TOTAL_DISCOUNT_ACTUAL_PERCENTAGE=Isnull(SUM(ACTUAL_SALES) / NULLIF(SUM(ACT.SALES), 0), 0) * 100,
       TOTAL_DISCOUNT_PROJECTED_PERCENTAGE=Isnull(SUM(disc_proj.DISCOUNT) / NULLIF(SUM(PROJ.SALES), 0), 0) * 100,
       TOTAL_ACTUAL_RPU=Isnull(SUM(ACTUAL_SALES) / NULLIF(SUM(ACT.quantity), 0), 0),
       TOTAL_PROJECTED_RPU=Isnull(SUM(disc_proj.DISCOUNT) / NULLIF(SUM(PROJ.UNITS), 0), 0),
       DISCOUNT_OF_EX_FACTORY_ACTUALS=Isnull(SUM(ACTUAL_SALES) / NULLIF(SUM(PD.GTS_SALES_ACTUALS), 0), 0) * 100,
       DISCOUNT_OF_EX_FACTORY_PROJECTED=Isnull(SUM(disc_proj.DISCOUNT) / NULLIF(SUM(PD.GTS_SALES_PROJECTED), 0), 0) * 100,
	   Iif(@VAR = 1, DT.SELECTED_LEVEL, NULL) RS_CONTRACT_SID,
       TOTAL_DISCOUNT_ACCRUAL=SUM(ACD.DISCOUNT_AMOUNT)
       
FROM   #DATA_TABLE DT
       LEFT JOIN #FILE_DATA PD
              ON PD.SELECTED_LEVEL = DT.SELECTED_LEVEL
                 AND PD.YEAR = DT.YEAR
                 AND PD.PERIOD = DT.PERIOD
       LEFT JOIN #CURRENT_ST_NM_ACTUAL_DISCOUNT ACT
              ON ACT.SELECTED_LEVEL = DT.SELECTED_LEVEL
                 AND ACT.YEAR = DT.YEAR
                 AND ACT.PERIOD = DT.PERIOD
	  left join  #CUSTOM_DISCOUNT_DETAILS disc_proj on disc_proj.SELECTED_LEVEL = DT.SELECTED_LEVEL
                 AND disc_proj.YEAR = DT.YEAR
                 AND disc_proj.PERIOD = DT.PERIOD
      left join #CUSTOM_SALES_DETAILS PROJ
              ON PROJ.SELECTED_LEVEL = DT.SELECTED_LEVEL
                 AND PROJ.YEAR = DT.YEAR
                 AND PROJ.PERIOD = DT.PERIOD
       LEFT JOIN #ACCRUAL_DISCOUNT ACD
              ON ACD.SELECTED_LEVEL = DT.SELECTED_LEVEL
                 AND ACD.YEAR = DT.YEAR
                 AND ACD.PERIOD = DT.PERIOD
GROUP  BY DT.YEAR,
          DT.PERIOD,
          DT.SELECTED_LEVEL_DESC,
         Iif(@VAR = 1, DT.SELECTED_LEVEL, NULL)')

                EXEC Sp_executesql
                  @SQL,
                  N' @VAR int ',
                  @VAR=@VAR
            END

          IF @SCREEN_NAME <> 'ASSUMPTIONS'
            BEGIN
                IF Object_id('TEMPDB..#PRIOR_TEMP_CCP') IS NOT NULL
                  DROP TABLE #PRIOR_TEMP_CCP

                CREATE TABLE #PRIOR_TEMP_CCP
                  (
                     COMPANY_MASTER_SID     INT,
                     CONTRACT_MASTER_SID    INT,
                     ITEM_MASTER_SID        INT,
                     PROJECTION_DETAILS_SID INT,
                     PROJECTION_MASTER_SID  INT,
                     CCP_DETAILS_SID        INT,
                     RS_CONTRACT_SID        INT,
                     HIERARCHY_NO           VARCHAR(8000),
                     SELECTED_LEVEL         INT,
                     SELECTED_LEVEL_DESC    VARCHAR(100)
                  )

                INSERT INTO #PRIOR_TEMP_CCP
                            (COMPANY_MASTER_SID,
                             CONTRACT_MASTER_SID,
                             ITEM_MASTER_SID,
                             PROJECTION_DETAILS_SID,
                             PROJECTION_MASTER_SID,
                             CCP_DETAILS_SID,
                             RS_CONTRACT_SID,
                             HIERARCHY_NO,
                             SELECTED_LEVEL,
                             SELECTED_LEVEL_DESC)
                SELECT CCP.COMPANY_MASTER_SID,
                       CCP.CONTRACT_MASTER_SID,
                       CCP.ITEM_MASTER_SID,
                       PD.PROJECTION_DETAILS_SID,
                       PD.PROJECTION_MASTER_SID,
                       CCP.CCP_DETAILS_SID,
                       MD.RS_CONTRACT_SID,
                       HIERARCHY_NO,
                       SELECTED_LEVEL,
                       SELECTED_LEVEL_DESC
                FROM   CCP_DETAILS CCP
                       INNER JOIN PROJECTION_DETAILS PD
                               ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                       INNER JOIN #MULTISELECT_DISCOUNTS md
                               ON md.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                WHERE  EXISTS (SELECT 1
                               FROM   #PROJECTION_MASTER PM
                               WHERE  PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                      AND ID <> 1)
                       AND EXISTS (SELECT 1
                                   FROM   #TEMP_CCP TC
                                   WHERE  TC.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID)

                --	select * from #MULTISELECT_DISCOUNTS 
                IF Object_id('TEMPDB..#SALES_INCLUSION') IS NOT NULL
                  DROP TABLE #SALES_INCLUSION

                SELECT DISTINCT A.PROJECTION_DETAILS_SID,
                                a.CCP_DETAILS_SID,
                                CASE
                                  WHEN DESCRIPTION = 'YES' THEN 1
                                  ELSE 0
                                END SALES_INCLUSION
                INTO   #SALES_INCLUSION
                FROM   NM_SALES_PROJECTION_MASTER A
                       --JOIN PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                       --AND 
                       JOIN CCP_DETAILS CD
                         ON CD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       JOIN CFP_CONTRACT_DETAILS CC
                         ON CC.COMPANY_MASTER_SID = CD.COMPANY_MASTER_SID
                       JOIN CFP_CONTRACT CC1
                         ON CC1.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
                            AND CC1.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
                       JOIN HELPER_TABLE HT
                         ON HT.HELPER_TABLE_SID = CC1.SALES_INCLUSION
                WHERE  EXISTS (SELECT 1
                               FROM   #PRIOR_TEMP_CCP Aa
                               WHERE  Aa.CCP_DETAILS_SID = a.CCP_DETAILS_SID
                                      AND Aa.PROJECTION_MASTER_SID = a.PROJECTION_MASTER_SID)

                IF Object_id('TEMPDB..#DEDUCTION_INCLUSION') IS NOT NULL
                  DROP TABLE #DEDUCTION_INCLUSION

                SELECT A.PROJECTION_DETAILS_SID,
                       a.CCP_DETAILS_SID,
                       RS.RS_CONTRACT_SID,
                       CASE
                         WHEN DESCRIPTION = 'YES' THEN 1
                         ELSE 0
                       END DEDUCTION_INCLUSION
                INTO   #DEDUCTION_INCLUSION
                FROM   NM_DISCOUNT_PROJ_MASTER A
                       --JOIN PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                       --AND EXISTS (
                       --	SELECT 1
                       --	FROM #PRIOR_TEMP_CCP A
                       --	WHERE A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                       --	AND A.PROJECTION_MASTER_SID=B.PROJECTION_MASTER_SID
                       --	)
                       --AND EXISTS (
                       --	SELECT 1
                       --	FROM #MULTISELECT_DISCOUNTS MD
                       --	WHERE MD.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                       --		AND MD.RS_CONTRACT_SID = A.RS_CONTRACT_SID
                       --	)
                       JOIN RS_CONTRACT RS
                         ON A.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
                       JOIN HELPER_TABLE HT
                         ON HT.HELPER_TABLE_SID = RS.DEDUCTION_INCLUSION
                WHERE  EXISTS (SELECT 1
                               FROM   #PRIOR_TEMP_CCP Aa
                               WHERE  Aa.CCP_DETAILS_SID = a.CCP_DETAILS_SID
                                      AND Aa.PROJECTION_MASTER_SID = a.PROJECTION_MASTER_SID
                                      AND Aa.RS_CONTRACT_SID = a.RS_CONTRACT_SID)

                IF Object_id('TEMPDB.DBO.#PRIOR_DATA_TABLE', 'U') IS NOT NULL
                  DROP TABLE #PRIOR_DATA_TABLE;

                WITH CTE
                     AS (SELECT DISTINCT SELECTED_LEVEL
                                         --,PTC.CCP_DETAILS_SID
                                         ,
                                         PROJECTION_MASTER_SID,
                                         SELECTED_LEVEL_DESC
                         FROM   #PRIOR_TEMP_CCP PTC)
                SELECT DISTINCT PROJECTION_MASTER_SID,
                                SELECTED_LEVEL,
                                PERIOD,
                                YEAR,
                                SELECTED_LEVEL_DESC
                INTO   #PRIOR_DATA_TABLE
                FROM   CTE A
                       JOIN #PERIOD P
                         ON PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID

                IF Object_id('TEMPDB..#PRIOR_DISCOUNT_FILE_DATA') IS NOT NULL
                  DROP TABLE #PRIOR_DISCOUNT_FILE_DATA

                CREATE TABLE #PRIOR_DISCOUNT_FILE_DATA
                  (
                     PERIOD                INT,
                     YEAR                  INT,
                     SELECTED_LEVEL        VARCHAR(100),
                     GTS_SALES_ACTUALS     NUMERIC(22, 6),
                     GTS_SALES_PROJECTED   NUMERIC(22, 6),
                     PROJECTION_MASTER_SID INT,
                     SELECTED_LEVEL_DESC   VARCHAR(100)
                  )

                INSERT INTO #PRIOR_DISCOUNT_FILE_DATA
                            (PERIOD,
                             YEAR,
                             SELECTED_LEVEL,
                             GTS_SALES_ACTUALS,
                             GTS_SALES_PROJECTED,
                             PROJECTION_MASTER_SID,
                             SELECTED_LEVEL_DESC)
                SELECT P.PERIOD,
                       P.YEAR,
                       FD.SELECTED_LEVEL,
                       GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                       GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                       FD.PROJECTION_MASTER_SID,
                       fd.SELECTED_LEVEL_DESC
                FROM   #PRIOR_DATA_TABLE FD
                       JOIN #PERIOD P
                         ON P.PERIOD = FD.PERIOD
                            AND P.YEAR = FD.YEAR
                       JOIN (SELECT DISTINCT SELECTED_LEVEL,
                                             ITEM_MASTER_SID
                             FROM   #MULTISELECT_DISCOUNTS) CD
                         ON CD.SELECTED_LEVEL = FD.SELECTED_LEVEL
                       LEFT JOIN #PRODUCT_FILE_TEMP PF
                              ON PF.ITEM_MASTER_SID = CD.ITEM_MASTER_SID
                                 AND PF.PERIOD_SID = P.PERIOD_SID
                                 AND PF.PROJECTION_MASTER_SID = FD.PROJECTION_MASTER_SID
                GROUP  BY FD.SELECTED_LEVEL,
                          fd.SELECTED_LEVEL_DESC,
                          P.PERIOD,
                          P.YEAR,
                          FD.PROJECTION_MASTER_SID

                IF Object_id('TEMPDB.DBO.#NM_ACTUAL_DISCOUNT', 'U') IS NOT NULL
                  DROP TABLE #NM_ACTUAL_DISCOUNT;

                SELECT NAD.PROJECTION_MASTER_SID,
                       Sum(Iif(( DI.DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
                                  OR @DEDUCTION_INCLUSION IS NULL ), Isnull(ACTUAL_SALES, 0), NULL)) AS ACTUAL_SALES
                       --,SUM(DISCOUNT_AMOUNT) ACCRUAL_DISCOUNT_ACTUAL
                       ,
                       P.PERIOD,
                       P.YEAR,
                       SELECTED_LEVEL
                INTO   #NM_ACTUAL_DISCOUNT
                FROM   NM_ACTUAL_DISCOUNT NAD
                       INNER JOIN #PERIOD P
                               ON P.PERIOD_SID = NAD.PERIOD_SID
                       INNER JOIN #PRIOR_TEMP_CCP A
                               ON A.CCP_DETAILS_SID = NAD.CCP_DETAILS_SID
                                  AND A.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID
                                  AND A.PROJECTION_MASTER_SID = NAD.PROJECTION_MASTER_SID
                       --INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                       --AND EXISTS (
                       --	SELECT 1
                       --	FROM #PRIOR_TEMP_CCP A
                       --	WHERE A.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
                       --	)
                       --INNER JOIN #MULTISELECT_DISCOUNTS DM ON DM.CCP_DETAILS_SID = pd.CCP_DETAILS_SID
                       --	AND DM.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID
                       LEFT JOIN #DEDUCTION_INCLUSION DI
                              ON DI.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                 AND DI.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID
                --LEFT JOIN #ACCRUAL_DISCOUNT AD ON AD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                --	AND AD.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID
                --	AND AD.PERIOD_SID = NAD.PERIOD_SID
                GROUP  BY NAD.PROJECTION_MASTER_SID,
                          P.PERIOD,
                          P.YEAR,
                          SELECTED_LEVEL

                IF Object_id('TEMPDB.DBO.#NM_DISCOUNT_PROJECTION', 'U') IS NOT NULL
                  DROP TABLE #NM_DISCOUNT_PROJECTION;

                SELECT NSP.PROJECTION_MASTER_SID,
                       Sum(Iif(( DI.DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
                                  OR @DEDUCTION_INCLUSION IS NULL ), Isnull(PROJECTION_SALES, 0), NULL)) AS PROJECTION_SALES,
                       P.PERIOD,
                       P.YEAR,
                       SELECTED_LEVEL
                --Sum(DISCOUNT_AMOUNT)                                                              ACCRUAL_DISCOUNT_PROJ
                INTO   #NM_DISCOUNT_PROJECTION
                FROM   NM_DISCOUNT_PROJECTION NSP
                       INNER JOIN #PERIOD P
                               ON P.PERIOD_SID = NSP.PERIOD_SID
                       INNER JOIN #PRIOR_TEMP_CCP A
                               ON A.CCP_DETAILS_SID = NSP.CCP_DETAILS_SID
                                  AND A.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                                  AND A.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID
                       --INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                       --	AND EXISTS (
                       --		SELECT 1
                       --		FROM #PRIOR_TEMP_CCP A
                       --		WHERE A.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
                       --		)
                       INNER JOIN #DEDUCTION_INCLUSION DI
                               ON DI.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                                  AND DI.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                --INNER JOIN #MULTISELECT_DISCOUNTS DM ON DM.CCP_DETAILS_SID = DI.CCP_DETAILS_SID
                --	AND DM.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                --LEFT JOIN #ACCRUAL_DISCOUNT AD ON AD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                --	AND AD.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                --	AND AD.PERIOD_SID = NSP.PERIOD_SID
                GROUP  BY NSP.PROJECTION_MASTER_SID,
                          P.PERIOD,
                          P.YEAR,
                          SELECTED_LEVEL

                IF Object_id('TEMPDB.DBO.#NM_ACTUAL_SALES', 'U') IS NOT NULL
                  DROP TABLE #NM_ACTUAL_SALES;

                SELECT Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                  OR @SALES_INCLUSION IS NULL ), Isnull(ACTUAL_SALES, 0), NULL))                                     ACTUAL_SALES,
                       Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                  OR @SALES_INCLUSION IS NULL ), Isnull(ACTUAL_UNITS, 0), NULL) * COALESCE(NULLIF(UOM_VALUE, 0), 1)) ACTUAL_UNITS,
                       P.PERIOD,
                       P.YEAR,
                       a.SELECTED_LEVEL,
                       NSP.PROJECTION_MASTER_SID
                INTO   #NM_ACTUAL_SALES
                FROM   NM_ACTUAL_SALES NSP
                       --INNER JOIN PROJECTION_DETAILS PD
                       --        ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                       --JOIN (SELECT DISTINCT CCP_DETAILS_SID,
                       --                      SELECTED_LEVEL
                       --      FROM   #MULTISELECT_DISCOUNTS) B
                       --  ON pd.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                       INNER JOIN #PRIOR_TEMP_CCP A
                               ON A.CCP_DETAILS_SID = NSP.CCP_DETAILS_SID
                                  --  AND A.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                                  AND A.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID
                       INNER JOIN #PERIOD P
                               ON P.PERIOD_SID = NSP.PERIOD_SID
                       INNER JOIN #SALES_INCLUSION SI
                               ON SI.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                                  AND SI.CCP_DETAILS_SID = NSP.CCP_DETAILS_SID
                       --INNER JOIN CCP_DETAILS CD
                       --        ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                       LEFT JOIN #ITEM_UOM_DETAILS UOM
                              ON UOM.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                GROUP  BY NSP.PROJECTION_MASTER_SID,
                          PERIOD,
                          YEAR,
                          a.SELECTED_LEVEL

                IF Object_id('TEMPDB.DBO.#NM_SALES_PROJECTION', 'U') IS NOT NULL
                  DROP TABLE #NM_SALES_PROJECTION;

                SELECT Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                  OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_SALES, 0), NULL))                                     PROJECTION_SALES,
                       Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                  OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_UNITS, 0), NULL) * COALESCE(NULLIF(UOM_VALUE, 0), 1)) PROJECTION_UNITS,
                       P.PERIOD,
                       P.YEAR,
                       a.SELECTED_LEVEL,
                       NSP.PROJECTION_MASTER_SID
                INTO   #NM_SALES_PROJECTION
                FROM   NM_SALES_PROJECTION NSP
                       --INNER JOIN PROJECTION_DETAILS PD
                       --        ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                       --JOIN (SELECT DISTINCT CCP_DETAILS_SID,
                       --                      SELECTED_LEVEL
                       --      FROM   #MULTISELECT_DISCOUNTS) B
                       --  ON pd.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                       INNER JOIN #PRIOR_TEMP_CCP A
                               ON A.CCP_DETAILS_SID = NSP.CCP_DETAILS_SID
                                  --  AND A.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                                  AND A.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID
                       INNER JOIN #PERIOD P
                               ON P.PERIOD_SID = NSP.PERIOD_SID
                       INNER JOIN #SALES_INCLUSION SI
                               ON SI.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                                  AND SI.CCP_DETAILS_SID = NSP.CCP_DETAILS_SID
                       --INNER JOIN CCP_DETAILS CD
                       --        ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                       LEFT JOIN #ITEM_UOM_DETAILS UOM
                              ON UOM.ITEM_MASTER_SID = a.ITEM_MASTER_SID
                GROUP  BY NSP.PROJECTION_MASTER_SID,
                          PERIOD,
                          YEAR,
                          a.SELECTED_LEVEL

                IF Object_id('TEMPDB.DBO.#PRIOR_SALES', 'U') IS NOT NULL
                  DROP TABLE #PRIOR_SALES;

                SELECT FD.PROJECTION_MASTER_SID,
                       FD.YEAR,
                       FD.PERIOD,
                       FD.SELECTED_LEVEL,
                       ( NAS.ACTUAL_SALES )     AS NM_ACTUAL_SALES,
                       ( NAS.ACTUAL_UNITS )     AS NM_ACTUAL_UNITS,
                       ( NSP.PROJECTION_SALES ) AS NM_PROJECTED_SALES,
                       ( NSP.PROJECTION_UNITS ) AS NM_PROJECTED_UNITS,
                       fd.SELECTED_LEVEL_DESC
                INTO   #PRIOR_SALES
                FROM   #PRIOR_DATA_TABLE FD
                       LEFT JOIN #NM_SALES_PROJECTION NSP
                              ON FD.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID
                                 AND NSP.YEAR = FD.YEAR
                                 AND NSP.PERIOD = FD.PERIOD
                                 AND NSP.SELECTED_LEVEL = FD.SELECTED_LEVEL
                       LEFT JOIN #NM_ACTUAL_SALES NAS
                              ON FD.PROJECTION_MASTER_SID = NAS.PROJECTION_MASTER_SID
                                 AND NAS.YEAR = FD.YEAR
                                 AND NAS.PERIOD = FD.PERIOD
                                 AND NAS.SELECTED_LEVEL = FD.SELECTED_LEVEL

                IF Object_id('TEMPDB.DBO.#PRIOR_DISCOUNT', 'U') IS NOT NULL
                  DROP TABLE #PRIOR_DISCOUNT;

                SELECT FD.PROJECTION_MASTER_SID,
                       FD.YEAR,
                       FD.PERIOD,
                       ( ACTUAL_SALES )            AS ACTUAL_SALES,
                       ( PROJECTION_SALES )        AS PROJECTION_SALES,
                       COALESCE(Discount_amount, 0)ACCRUAL_DISCOUNT,
                       FD.SELECTED_LEVEL,
                       fd.SELECTED_LEVEL_DESC
                INTO   #PRIOR_DISCOUNT
                FROM   #PRIOR_DATA_TABLE FD
                       LEFT JOIN #NM_ACTUAL_DISCOUNT NAD
                              ON NAD.PERIOD = FD.PERIOD
                                 AND NAD.YEAR = FD.YEAR
                                 AND NAD.SELECTED_LEVEL = FD.SELECTED_LEVEL
                                 AND NAD.PROJECTION_MASTER_SID = FD.PROJECTION_MASTER_SID
                       LEFT JOIN #NM_DISCOUNT_PROJECTION NSP
                              ON FD.PERIOD = NSP.PERIOD
                                 AND FD.YEAR = NSP.YEAR
                                 AND FD.SELECTED_LEVEL = NSP.SELECTED_LEVEL
                                 AND FD.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID
                       LEFT JOIN #ACCRUAL_DISCOUNT ad
                              ON ad.period = fd.period
                                 AND FD.YEAR = ad.YEAR
                                 AND FD.SELECTED_LEVEL = ad.SELECTED_LEVEL

                --ORDER  BY FD.PERIOD,
                --          FD.YEAR,
                --          FD.SELECTED_LEVEL,fd.SELECTED_LEVEL_DESC
                IF Object_id('TEMPDB.DBO.#RESULT_DETAILS_INFO', 'U') IS NOT NULL
                  DROP TABLE #RESULT_DETAILS_INFO;

                CREATE TABLE #RESULT_DETAILS_INFO
                  (
                     PROJECTION_MASTER_SID               INT,
                     YEAR                                INT,
                     PERIOD                              INT,
                     RS_NAME                             VARCHAR(100),
                     TOTAL_DISCOUNT_ACCRUAL              NUMERIC(22, 6),
                     TOTAL_DISCOUNT                      NUMERIC(22, 6),
                     TOTAL_DISCOUNT_PROJECTED            NUMERIC(22, 6),
                     TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE   NUMERIC(22, 6),
                     TOTAL_DISCOUNT_ACTUAL_PERCENTAGE    NUMERIC(22, 6),
                     TOTAL_DISCOUNT_PROJECTED_PERCENTAGE NUMERIC(22, 6),
                     TOTAL_RPU_ACCRUAL                   NUMERIC(22, 6),
                     TOTAL_PROJECTED_RPU                 NUMERIC(22, 6),
                     TOTAL_ACTUAL_RPU                    NUMERIC(22, 6),
                     RS_CONTRACT_SID                     INT,
                     DISCOUNT_OF_EX_FACTORY_ACCRUAL      NUMERIC(22, 6),
                     DISCOUNT_OF_EX_FACTORY_ACTUALS      NUMERIC(22, 6),
                     DISCOUNT_OF_EX_FACTORY_PROJECTED    NUMERIC(22, 6)
                  )

                INSERT INTO #RESULT_DETAILS_INFO
                            (PROJECTION_MASTER_SID,
                             YEAR,
                             PERIOD,
                             RS_NAME,
                             TOTAL_DISCOUNT,
                             TOTAL_DISCOUNT_PROJECTED,
                             TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                             TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             TOTAL_ACTUAL_RPU,
                             TOTAL_PROJECTED_RPU,
                             RS_CONTRACT_SID,
                             DISCOUNT_OF_EX_FACTORY_ACTUALS,
                             DISCOUNT_OF_EX_FACTORY_PROJECTED,
                             TOTAL_DISCOUNT_ACCRUAL)
                SELECT PROJECTION_MASTER_SID=RD.PROJECTION_MASTER_SID,
                       ND.YEAR,
                       ND.PERIOD,
                       RS_NAME=ND.SELECTED_LEVEL_DESC,
                       TOTAL_DISCOUNT=ND.ACTUAL_SALES,
                       TOTAL_DISCOUNT_PROJECTED=ND.PROJECTION_SALES,
                       TOTAL_DISCOUNT_ACTUAL_PERCENTAGE=COALESCE(ND.ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_SALES, 0), 0) * 100,
                       TOTAL_DISCOUNT_PROJECTED_PERCENTAGE=COALESCE(ND.PROJECTION_SALES / NULLIF(RD.NM_PROJECTED_SALES, 0), 0) * 100,
                       TOTAL_ACTUAL_RPU=COALESCE(ND.ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_UNITS, 0), 0),
                       TOTAL_PROJECTED_RPU=COALESCE(ND.PROJECTION_SALES / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0),
                       CASE
                         WHEN @VAR = 1 THEN ND.SELECTED_LEVEL
                         ELSE NULL
                       END                                                                                                          RS_CONTRACT_SID,
                       Isnull(Cast(COALESCE(ND.ACTUAL_SALES / NULLIF(B.GTS_SALES_ACTUALS, 0), 0) * 100 AS NUMERIC(22, 6)), 0)       AS DISCOUNT_OF_EX_FACTORY_ACTUALS,
                       Isnull(Cast(COALESCE(ND.PROJECTION_SALES / NULLIF(B.GTS_SALES_PROJECTED, 0), 0) * 100 AS NUMERIC(22, 6)), 0) AS DISCOUNT_OF_EX_FACTORY_PROJECTED,
                       ACCRUAL_DISCOUNT                                                                                             AS TOTAL_DISCOUNT_ACCRUAL
                FROM   #PRIOR_DISCOUNT ND
                       INNER JOIN #PRIOR_SALES RD
                               ON ND.PROJECTION_MASTER_SID = RD.PROJECTION_MASTER_SID
                                  AND RD.YEAR = ND.YEAR
                                  AND RD.PERIOD = ND.PERIOD
                                  AND RD.SELECTED_LEVEL = ND.SELECTED_LEVEL
                       LEFT JOIN #PRIOR_DISCOUNT_FILE_DATA B
                              ON ND.SELECTED_LEVEL = B.SELECTED_LEVEL
                                 AND ND.YEAR = B.YEAR
                                 AND ND.PERIOD = B.PERIOD
                                 AND B.PROJECTION_MASTER_SID = ND.PROJECTION_MASTER_SID

                IF @VAR = 1
                  BEGIN
                      INSERT INTO #PIVOT_RESULT
                                  (PROJECTION_ID,
                                   [PERIOD],
                                   [YEAR],
                                   RS_NAME,
                                   CONTRACT_DISCOUNT_ACTUAL,
                                   CONTRACT_DISCOUNT_PROJECTED,
                                   TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   TOTAL_RPU_ACTUAL,
                                   TOTAL_RPU_PROJECTED,
                                   RS_CONTRACT_SID,
                                   DISCOUNT_OF_EX_FACTORY_ACTUALS,
                                   DISCOUNT_OF_EX_FACTORY_PROJECTED,
                                   CONTRACT_DISCOUNT_ACCRUAL)
                      SELECT DISTINCT RD.PROJECTION_MASTER_SID,
                                      RD.PERIOD,
                                      RD.YEAR,
                                      Iif(@PROGRAM_TYPE = 'PROGRAM', RC.RS_NAME, RS_ID) RS_NAME,
                                      Isnull(TOTAL_DISCOUNT, 0),
                                      Isnull(TOTAL_DISCOUNT_PROJECTED, 0),
                                      Isnull(TOTAL_DISCOUNT_ACTUAL_PERCENTAGE, 0),
                                      Isnull(TOTAL_DISCOUNT_PROJECTED_PERCENTAGE, 0),
                                      Isnull(TOTAL_ACTUAL_RPU, 0),
                                      Isnull(TOTAL_PROJECTED_RPU, 0),
                                      RC.RS_CONTRACT_SID,
                                      DISCOUNT_OF_EX_FACTORY_ACTUALS,
                                      DISCOUNT_OF_EX_FACTORY_PROJECTED,
                                      TOTAL_DISCOUNT_ACCRUAL
                      FROM   #RESULT_DETAILS_INFO RD
                             JOIN RS_CONTRACT RC
                               ON RC.RS_CONTRACT_SID = RD.RS_CONTRACT_SID
                  END
                ELSE
                  BEGIN
                      INSERT INTO #PIVOT_RESULT
                                  (PROJECTION_ID,
                                   [PERIOD],
                                   [YEAR],
                                   RS_NAME,
                                   CONTRACT_DISCOUNT_ACTUAL,
                                   CONTRACT_DISCOUNT_PROJECTED,
                                   TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   TOTAL_RPU_ACTUAL,
                                   TOTAL_RPU_PROJECTED,
                                   DISCOUNT_OF_EX_FACTORY_ACTUALS,
                                   DISCOUNT_OF_EX_FACTORY_PROJECTED,
                                   RS_CONTRACT_SID,
                                   CONTRACT_DISCOUNT_ACCRUAL)
                      SELECT DISTINCT PROJECTION_MASTER_SID,
                                      RD.PERIOD,
                                      RD.YEAR,
                                      RS_NAME,
                                      TOTAL_DISCOUNT,
                                      TOTAL_DISCOUNT_PROJECTED,
                                      TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                                      TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                      TOTAL_ACTUAL_RPU,
                                      TOTAL_PROJECTED_RPU,
                                      DISCOUNT_OF_EX_FACTORY_ACTUALS,
                                      DISCOUNT_OF_EX_FACTORY_PROJECTED,
                                      RS_CONTRACT_SID,
                                      TOTAL_DISCOUNT_ACCRUAL
                      FROM   #RESULT_DETAILS_INFO RD
                  --WHERE  PROJECTION_MASTER_SID <> @FIRST_PROJ_SID
                  --ORDER  BY RS_NAME,
                  --          RD.YEAR,
                  --          RD.PERIOD,
                  --          RS_CONTRACT_SID
                  END
            END

          DECLARE @SQL1      NVARCHAR(MAX),
                  @LOOP_CNTR INT,
                  @MAX_CCP   INT

          SET @SQL1 = 'SELECT YEAR,PERIOD,RS_NAME,'
          SET @LOOP_CNTR = 1
          SET @MAX_CCP = (SELECT Max(ID)
                          FROM   #PROJECTION_MASTER)

          WHILE @LOOP_CNTR <= @MAX_CCP
            BEGIN
                IF @LOOP_CNTR = 1
                  BEGIN
                      SET @SQL1 += 'CONTRACT_DISCOUNT_ACCRUAL_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN IIF(CONTRACT_DISCOUNT_ACCRUAL=0,NULL,CONTRACT_DISCOUNT_ACCRUAL) END), 
						   CONTRACT_DISCOUNT_ACTUAL_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = CASE WHEN '''
                                   + Cast(@CURRENT_DATE AS VARCHAR(10))
                                   + ''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN CONTRACT_DISCOUNT_ACTUAL END) ELSE NULL END, 
								  CONTRACT_DISCOUNT_PROJECTED_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = ISNULL(SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN CONTRACT_DISCOUNT_PROJECTED END),0), 
								  TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE END),
								  TOTAL_DISCOUNT_ACTUAL_PERCENTAGE_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' =CASE WHEN '''
                                   + Cast(@CURRENT_DATE AS VARCHAR(10))
                                   + ''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN TOTAL_DISCOUNT_ACTUAL_PERCENTAGE END) ELSE NULL END,  
                                  TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = ISNULL(SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
                                   TOTAL_RPU_ACCRUAL_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN TOTAL_RPU_ACCRUAL END),
                                   TOTAL_RPU_ACTUAL_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' =CASE WHEN '''
                                   + Cast(@CURRENT_DATE AS VARCHAR(10))
                                   + ''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN TOTAL_RPU_ACTUAL END) ELSE NULL END,
                                   TOTAL_RPU_PROJECTED_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = ISNULL(SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN TOTAL_RPU_PROJECTED END),0),
                                   DISCOUNT_OF_EX_FACTORY_ACCRUAL_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN DISCOUNT_OF_EX_FACTORY_ACCRUAL END),
                                   DISCOUNT_OF_EX_FACTORY_ACTUALS_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' =CASE WHEN '''
                                   + Cast(@CURRENT_DATE AS VARCHAR(10))
                                   + ''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END) ELSE NULL END,
                                   DISCOUNT_OF_EX_FACTORY_PROJECTED_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = ISNULL(SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0),'
                  END
                ELSE
                  BEGIN
                      SET @SQL1 += 'CONTRACT_DISCOUNT_ACCRUAL_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN CONTRACT_DISCOUNT_ACCRUAL END), 
						   CONTRACT_DISCOUNT_ACTUAL_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = ISNULL(SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN CONTRACT_DISCOUNT_ACTUAL END),0), 
								  CONTRACT_DISCOUNT_PROJECTED_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = ISNULL(SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN CONTRACT_DISCOUNT_PROJECTED END),0), 
								  TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE END),
								  TOTAL_DISCOUNT_ACTUAL_PERCENTAGE_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = ISNULL(SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN TOTAL_DISCOUNT_ACTUAL_PERCENTAGE END),0),      
                                  TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = ISNULL(SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
                                   TOTAL_RPU_ACCRUAL_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN TOTAL_RPU_ACCRUAL END),
                                   TOTAL_RPU_ACTUAL_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = ISNULL(SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN TOTAL_RPU_ACTUAL END),0),
                                   TOTAL_RPU_PROJECTED_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = ISNULL(SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN TOTAL_RPU_PROJECTED END),0),
								     DISCOUNT_OF_EX_FACTORY_ACCRUAL_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN DISCOUNT_OF_EX_FACTORY_ACCRUAL END),
                                   DISCOUNT_OF_EX_FACTORY_ACTUALS_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = ISNULL(SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),0) ,
                                   DISCOUNT_OF_EX_FACTORY_PROJECTED_'
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + ' = ISNULL(SUM(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR AS VARCHAR(10))
                                   + 'THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0),'
                  END

                SET @LOOP_CNTR += 1
            END

          SET @SQL1 = LEFT(@SQL1, Len(@SQL1) - 1)

          IF ( @PROGRAM_TYPE = 'Program'
                OR @PROGRAM_TYPE = 'Schedule Id' )
            BEGIN
                SET @SQL1 += ',RS_CONTRACT_SID FROM   #PIVOT_RESULT PR
                                                     INNER JOIN #PROJECTION_MASTER PM
                                                             ON PR.PROJECTION_ID = PM.PROJECTION_MASTER_SID
                                                       GROUP  BY [YEAR],PERIOD,RS_NAME,RS_CONTRACT_SID
                                                       ORDER BY RS_NAME,RS_CONTRACT_SID,[YEAR],PERIOD'
            END
          ELSE
            BEGIN
                SET @SQL1 += ',RS_NAME RS_CONTRACT_SID FROM   #PIVOT_RESULT PR
                                                     INNER JOIN #PROJECTION_MASTER PM
                                                             ON PR.PROJECTION_ID = PM.PROJECTION_MASTER_SID
                                                       GROUP  BY [YEAR],PERIOD,RS_NAME
                                                       ORDER BY RS_NAME,[YEAR],PERIOD'
            END

          --SELECT @SQL1
          /*SET @SQL1 += ',RS_CONTRACT_SID FROM   #PIVOT_RESULT PR
                                                             INNER JOIN #PROJECTION_MASTER PM
                                                                     ON PR.PROJECTION_ID = PM.PROJECTION_MASTER_SID
                                                               GROUP  BY [YEAR],PERIOD,RS_NAME,RS_CONTRACT_SID
                                                               ORDER BY RS_NAME,RS_CONTRACT_SID,[YEAR],PERIOD'*/
          EXEC Sp_executesql
            @SQL1
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

