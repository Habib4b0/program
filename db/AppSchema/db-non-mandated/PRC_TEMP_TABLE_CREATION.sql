IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_TEMP_TABLE_CREATION'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_TEMP_TABLE_CREATION]
  END

GO

CREATE PROCEDURE [dbo].[PRC_TEMP_TABLE_CREATION] (@TABLE_LIST TABLE_LIST READONLY,
                                                  @USER_ID    INT,
                                                  @SESSION_ID VARCHAR(50))
AS
  BEGIN
      SET NOCOUNT ON
      SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED

      BEGIN TRY
          IF Object_id('TEMPDB..#TABLE_LIST') IS NOT NULL
            DROP TABLE #TABLE_LIST

          CREATE TABLE #TABLE_LIST
            (
               ORG_TABLE_NAME  VARCHAR(200),
               TEMP_TABLE_NAME VARCHAR(200)
            )

          INSERT INTO #TABLE_LIST
                      (ORG_TABLE_NAME,
                       TEMP_TABLE_NAME)
          SELECT ORG_TABLE_NAME,
                 Concat('ST_', ORG_TABLE_NAME, '_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')) AS TEMP_TABLE_NAME
          FROM   @TABLE_LIST

          -------------------------------------------------CREATE TABLE SCRIPT START---------------------------------------------------
          DECLARE @SQL NVARCHAR(MAX) = ''

          SELECT @SQL += Concat('SELECT * INTO ', TEMP_TABLE_NAME, ' FROM ', ORG_TABLE_NAME, ' WHERE 1 = 0 ;')
          FROM   #TABLE_LIST

          EXEC Sp_executesql
            @SQL

          -------------------------------------------------CREATE TABLE SCRIPT END---------------------------------------------------
          IF EXISTS (SELECT 1
                     FROM   #TABLE_LIST
                     WHERE  ORG_TABLE_NAME IN ( 'NM_SALES_PROJECTION_MASTER', 'NM_DISCOUNT_PROJ_MASTER' ))
            BEGIN
                DECLARE @SALES_TABLE VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                DECLARE @DISCOUNT_TABLE VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                DECLARE @SALES_PROJ_TABLE VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

                SET @SQL = Concat ('ALTER TABLE ', @SALES_TABLE, ' DROP COLUMN PROJECTION_DETAILS_SID,PROJECTION_MASTER_SID')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat ('ALTER TABLE ', @SALES_PROJ_TABLE, ' DROP COLUMN PROJECTION_DETAILS_SID,PROJECTION_MASTER_SID')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat ('ALTER TABLE ', @DISCOUNT_TABLE, ' DROP COLUMN PROJECTION_DETAILS_SID,PROJECTION_MASTER_SID')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat ('ALTER TABLE ', 'ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''), ' DROP COLUMN PROJECTION_DETAILS_SID,PROJECTION_MASTER_SID')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat ('ALTER TABLE ', 'ST_NM_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''), ' DROP COLUMN PROJECTION_DETAILS_SID,PROJECTION_MASTER_SID')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat ('ALTER TABLE ', 'ST_NM_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''), ' DROP COLUMN PROJECTION_DETAILS_SID,PROJECTION_MASTER_SID')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @SALES_TABLE, ' ADD SALES_INCLUSION CHAR(1)  NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @SALES_TABLE, ' ADD FILTER_CCP BIT  NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @SALES_TABLE, ' ADD EFFECTIVE_START_PERIOD_SID SMALLINT NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @SALES_TABLE, ' ADD EFFECTIVE_END_PERIOD_SID SMALLINT NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @SALES_TABLE, ' ADD FREQ_CAL_START_PERIOD_SID SMALLINT NULL')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @SALES_TABLE, ' ADD FREQ_CAL_END_PERIOD_SID SMALLINT NULL')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @DISCOUNT_TABLE, ' ADD EFFECTIVE_START_PERIOD_SID SMALLINT NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @DISCOUNT_TABLE, ' ADD EFFECTIVE_END_PERIOD_SID SMALLINT NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @DISCOUNT_TABLE, ' ADD FREQ_CAL_START_PERIOD_SID SMALLINT NULL')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @DISCOUNT_TABLE, ' ADD FREQ_CAL_END_PERIOD_SID SMALLINT NULL')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @DISCOUNT_TABLE, ' ADD FILTER_CCP BIT  NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @DISCOUNT_TABLE, ' ADD PV_FILTERS BIT  NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = ''
                SET @SQL = Concat ('ALTER TABLE ', @DISCOUNT_TABLE, ' ADD DEDUCTION_INCLUSION CHAR(1)  NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=Concat('ALTER TABLE ', @DISCOUNT_TABLE, ' ADD DEDUCTION_HIERARCHY_NO VARCHAR(2000)  NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=Concat('ALTER TABLE ', @SALES_TABLE, ' ADD ADJUSTED_CCP BIT  NULL ')

                EXEC Sp_executesql
                  @SQL
            END

          -------------------------------------------------------------------- CREATING IDENTITY PROPERTY IN ARM_ADJ_RES_CONFIG_DETAIL TABLE (ARM SETUP TABLE) -- STARTS HERE 
          IF EXISTS(SELECT 1
                    FROM   #TABLE_LIST
                    WHERE  ORG_TABLE_NAME = 'ARM_ADJ_RES_CONFIG_DETAIL')
            BEGIN
                DECLARE @ARM_DEMAND_ADJ_SUMMARY_EXCEPTION VARCHAR(200) = Concat('ST_ARM_ADJ_RES_CONFIG_DETAIL_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_DEMAND_ADJ_SUMMARY_EXCEPTION, ' DROP COLUMN ARM_ADJ_RES_CONFIG_DETAIL_SID ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_DEMAND_ADJ_SUMMARY_EXCEPTION, ' ADD ARM_ADJ_RES_CONFIG_DETAIL_SID INT IDENTITY(1,1) NOT NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_DEMAND_ADJ_SUMMARY_EXCEPTION, ' ADD CHECK_RECORD BIT NOT NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_DEMAND_ADJ_SUMMARY_EXCEPTION, ' ADD CONSTRAINT DF_ST_ARM_ADJ_RES_CONFIG_DETAIL_CHECK_RECORD_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''), ' DEFAULT 0 FOR CHECK_RECORD ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
            END

          -------------------------------------------------------------------- CREATING IDENTITY PROPERTY IN ARM_ADJ_RES_CONFIG_DETAIL TABLE (ARM SETUP TABLE) -- ENDS HERE
          -------------------------------------------------------------------- CREATING IDENTITY PROPERTY IN ARM_ADJ_RES_CONFIG_DETAIL TABLE (ARM SETUP TABLE) -- STARTS HERE 
          IF EXISTS(SELECT 1
                    FROM   #TABLE_LIST
                    WHERE  ORG_TABLE_NAME = 'ARM_ACC_CONFIG')
            BEGIN
                DECLARE @ARM_ACC_CONFIG_EXCEPTION VARCHAR(200) = Concat('ST_ARM_ACC_CONFIG_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ALTER COLUMN GL_COMPANY_MASTER_SID INT  NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ALTER COLUMN BU_COMPANY_MASTER_SID INT  NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ALTER COLUMN ACCOUNT VARCHAR(50)  NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ALTER COLUMN BRAND_MASTER_SID INT  NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ADD CHECK_RECORD BIT NOT NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ADD CONSTRAINT DF_ST_ARM_ACC_CONFIG_CHECK_RECORD_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''), ' DEFAULT 0 FOR CHECK_RECORD ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ADD ARM_ACC_CONFIG_MASTER_SID INT NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ADD SAVE_RECORD BIT NULL ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ADD CONSTRAINT DF_ST_ARM_ACC_CONFIG_SAVE_RECORD_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''), ' DEFAULT 0 FOR SAVE_RECORD ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ADD DELETE_RECORD BIT NULL DEFAULT 0')

                EXEC Sp_executesql
                  @SQL
            END

          -------------------------------------------------------------------- CREATING IDENTITY PROPERTY IN ARM_ADJ_RES_CONFIG_DETAIL TABLE (ARM SETUP TABLE) -- ENDS HERE
          -------------------------------------------------RENAME COLUMN SCRIPT START---------------------------------------------------
          DECLARE @RENAME_SQL NVARCHAR(MAX)=''

          SET @RENAME_SQL =(SELECT CONCAT('EXEC SP_RENAME ', '''', T.TABLE_NAME, '.', COLUMN_NAME, '''', ',', '''CCP_DETAILS_SID'', ''COLUMN'';')
                            FROM   INFORMATION_SCHEMA.COLUMNS T
                                   JOIN #TABLE_LIST TL
                                     ON TL.TEMP_TABLE_NAME = T.TABLE_NAME
                            WHERE  COLUMN_NAME IN ('PROJECTION_DETAILS_SID','ACCRUAL_PROJ_DETAILS_SID') AND ORG_TABLE_NAME<>'ACCRUAL_PROJ_DETAILS'
                            FOR XML PATH(''))


          EXEC sp_executesql @RENAME_SQL
		  --- FOR ARP COLUMN ADDITION
		  IF EXISTS(SELECT 1
                    FROM   #TABLE_LIST
                    WHERE  ORG_TABLE_NAME IN ( 'ACCRUAL_RATE_ACTUALS', 'ACCRUAL_RATE_DETAILS' ))
            BEGIN
                SET @SQL=''
                SET @SQL =Concat('ALTER TABLE ST_ACCRUAL_RATE_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''),' ADD RS_MODEL_SID INT NOT NULL')

				EXEC sp_executesql @SQL
				-----------galuat-940-------
				SET @SQL =Concat('ALTER TABLE ST_ACCRUAL_RATE_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''),' ADD RS_CONTRACT_SID INT NOT NULL')

				EXEC sp_executesql @SQL
				SET @SQL =Concat('ALTER TABLE ST_ACCRUAL_RATE_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''),' ADD CONSTRAINT PK_ST_ACCRUAL_RATE_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''),'_ACCRUAL_PROJ_DETAILS_SID_PERIOD_SID PRIMARY KEY (CCP_DETAILS_SID,RS_CONTRACT_SID, RS_MODEL_SID, PERIOD_SID)')

				EXEC sp_executesql @SQL
				-----------galuat-940-------
          
                SET @SQL =Concat('ALTER TABLE ST_ACCRUAL_RATE_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''),' ADD RS_MODEL_SID INT NOT NULL')
		
                EXEC sp_executesql @SQL
				-----------galuat-940-------
				SET @SQL =Concat('ALTER TABLE ST_ACCRUAL_RATE_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''),' ADD RS_CONTRACT_SID INT NOT NULL')

				EXEC sp_executesql @SQL

				SET @SQL =Concat('ALTER TABLE ST_ACCRUAL_RATE_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''),' ADD CONSTRAINT PK_ST_ACCRUAL_RATE_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''),'_ACCRUAL_PROJ_DETAILS_SID_PERIOD_SID PRIMARY KEY (CCP_DETAILS_SID,RS_CONTRACT_SID, RS_MODEL_SID, PERIOD_SID)')

				EXEC sp_executesql @SQL
				-----------galuat-940-------
		
            END 

          -------------------------------------------------RENAME COLUMN SCRIPT END---------------------------------------------------
          -------------------------------------------------DEFAULT SCRIPT START---------------------------------------------------
          DECLARE @DEFAULT_SCRIPT NVARCHAR(MAX) =''

          SELECT @DEFAULT_SCRIPT += 'ALTER TABLE [' + Schema_name(SCHEMA_ID)
                                    + '].[' + TL.TEMP_TABLE_NAME
                                    + ']
										ADD CONSTRAINT [DF_'
                                    + CONVERT(VARCHAR(100), DC.OBJECT_ID) + '_'
                                    + @SESSION_ID + '] DEFAULT ' + DEFINITION
                                    + ' FOR ['
                                    + Replace(C.NAME, 'PROJECTION_DETAILS_SID', 'CCP_DETAILS_SID')
                                    + ']
								
								'
          FROM   SYS.DEFAULT_CONSTRAINTS DC
                 INNER JOIN SYS.COLUMNS C
                         ON DC.PARENT_OBJECT_ID = C.OBJECT_ID
                            AND DC.PARENT_COLUMN_ID = C.COLUMN_ID
                 JOIN #TABLE_LIST TL
                   ON Object_id(TL.ORG_TABLE_NAME) = PARENT_OBJECT_ID

          --PRINT @DEFAULT_SCRIPT
          EXEC Sp_executesql
            @DEFAULT_SCRIPT

          -------------------------------------------------DEFAULT SCRIPT END---------------------------------------------------
          -------------------------------------------------PRIMARY SCRIPT START---------------------------------------------------
          DECLARE @PRIMARY_SCRIPT NVARCHAR(MAX) =''

          --SELECT  @PRIMARY_SCRIPT += 'ALTER TABLE ['+TL.TEMP_TABLE_NAME+']
          --							  ADD CONSTRAINT ['+REPLACE(TAB.CONSTRAINT_NAME,TL.ORG_TABLE_NAME,TL.TEMP_TABLE_NAME) +'] PRIMARY KEY ('+LEFT(CS.COL_LIST, LEN(CS.COL_LIST) - 1)+')
          --							'
          --  FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TAB
          --  JOIN  #TABLE_LIST TL ON TL.ORG_TABLE_NAME = TAB.TABLE_NAME
          --	   CROSS APPLY (SELECT QUOTENAME(REPLACE(COLUMN_NAME,'PROJECTION_DETAILS_SID','CCP_DETAILS_SID')) + ','
          --					FROM   INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE COL
          --					WHERE  COL.CONSTRAINT_NAME = TAB.CONSTRAINT_NAME
          --						   AND COL.TABLE_NAME = TAB.TABLE_NAME
          --					FOR XML PATH('')) CS (COL_LIST)
          --WHERE  CONSTRAINT_TYPE = 'PRIMARY KEY'
          --GROUP  BY TAB.TABLE_NAME,
          --		  TAB.CONSTRAINT_NAME,
          --		  CONSTRAINT_SCHEMA,TL.ORG_TABLE_NAME,TL.TEMP_TABLE_NAME,
          --		  LEFT(CS.COL_LIST, LEN(CS.COL_LIST) - 1) 
          ----PRINT @PRIMARY_SCRIPT
          --EXEC (@PRIMARY_SCRIPT)
          IF Object_id('TEMPDB..#PRIMARY') IS NOT NULL
            DROP TABLE #PRIMARY

          SELECT T.NAME AS ORG_TABLE_NAME,
                 K.NAME AS CONSTRAINT_NAME,
                 C.NAME AS COLUMN_NAME,
                 TL.TEMP_TABLE_NAME,
                 IC.key_ordinal
          INTO   #PRIMARY
          FROM   SYS.KEY_CONSTRAINTS AS K
                 JOIN SYS.TABLES AS T
                   ON T.OBJECT_ID = K.PARENT_OBJECT_ID
                 JOIN SYS.INDEX_COLUMNS AS IC
                   ON IC.OBJECT_ID = T.OBJECT_ID
                      AND IC.INDEX_ID = K.UNIQUE_INDEX_ID
                 JOIN SYS.COLUMNS AS C
                   ON C.OBJECT_ID = T.OBJECT_ID
                      AND C.COLUMN_ID = IC.COLUMN_ID
                 JOIN #TABLE_LIST TL
                   ON TL.ORG_TABLE_NAME = T.NAME
          WHERE  K.TYPE = 'PK' AND TL.ORG_TABLE_NAME NOT IN ( 'ACCRUAL_RATE_ACTUALS', 'ACCRUAL_RATE_DETAILS' )-----------galuat-940-------

          SET @PRIMARY_SCRIPT = (SELECT DISTINCT 'ALTER TABLE [' + C.TEMP_TABLE_NAME
                                                 + '] ADD CONSTRAINT ['
                                                 + case when C.TEMP_TABLE_NAME like 'ST_CUSTOM_CFF_DISCOUNT%' then replace(Replace(C.CONSTRAINT_NAME, C.ORG_TABLE_NAME, C.TEMP_TABLE_NAME),'ST_CUSTOM_CFF_DISCOUNT','S_CFF_Disc')
												 when C.TEMP_TABLE_NAME like 'ST_CUSTOM_DISCOUNT_REPORT%' then replace(Replace(C.CONSTRAINT_NAME, C.ORG_TABLE_NAME, C.TEMP_TABLE_NAME),'ST_CUSTOM_DISCOUNT_REPORT','S_CDR')
												 else Replace(C.CONSTRAINT_NAME, C.ORG_TABLE_NAME, C.TEMP_TABLE_NAME)
												 end 
                                                 + '] PRIMARY KEY ('
                                                 + LEFT(CS.COL_LIST, Len(CS.COL_LIST) - 1)
                                                 + '); '
                                 FROM   #PRIMARY C
                                        CROSS APPLY (SELECT Replace(Replace(D.COLUMN_NAME, 'PROJECTION_DETAILS_SID', 'CCP_DETAILS_SID'), 'ACCRUAL_PROJ_DETAILS_SID', 'CCP_DETAILS_SID,RS_MODEL_SID')
                                                            + ','
                                                     FROM   #PRIMARY D
                                                     WHERE  C.ORG_TABLE_NAME = D.ORG_TABLE_NAME
                                                            AND D.COLUMN_NAME <>  CASE WHEN D.ORG_TABLE_NAME ='COMPARISION_REPORT' THEN '1' ELSE 'PROJECTION_MASTER_SID' END
                                                     ORDER  BY D.key_ordinal
                                                     FOR XML PATH ('')) CS (COL_LIST)
													 where c.ORG_TABLE_NAME<>'APPROVED_REPORT'
                                 FOR XML PATH (''))
								 								 
          EXEC Sp_executesql
            @PRIMARY_SCRIPT
			SET @PRIMARY_SCRIPT = (SELECT DISTINCT 'ALTER TABLE [' + C.TEMP_TABLE_NAME
                                                 + '] ADD CONSTRAINT ['
                                                 + case when C.TEMP_TABLE_NAME like 'ST_CUSTOM_CFF_DISCOUNT%' then replace(Replace(C.CONSTRAINT_NAME, C.ORG_TABLE_NAME, C.TEMP_TABLE_NAME),'ST_CUSTOM_CFF_DISCOUNT','S_CFF_Disc')
												 when C.TEMP_TABLE_NAME like 'ST_CUSTOM_DISCOUNT_REPORT%' then replace(Replace(C.CONSTRAINT_NAME, C.ORG_TABLE_NAME, C.TEMP_TABLE_NAME),'ST_CUSTOM_DISCOUNT_REPORT','S_CDR')
												 else Replace(C.CONSTRAINT_NAME, C.ORG_TABLE_NAME, C.TEMP_TABLE_NAME)
												 end 
                                                 + '] PRIMARY KEY ('
                                                 + stuff(cs.COL_LIST,1,1,'')
                                                 + '); '
                                 FROM    #PRIMARY C
                                        CROSS APPLY (SELECT ','+d.COLUMN_NAME
                                                     FROM   #PRIMARY D 
													 where ORG_TABLE_NAME='APPROVED_REPORT'
                                                     ORDER  BY D.key_ordinal
                                                     FOR XML PATH ('')) CS (COL_LIST)
													 where c.ORG_TABLE_NAME='APPROVED_REPORT'
													 FOR XML PATH (''))
			EXEC Sp_executesql
            @PRIMARY_SCRIPT


          -------------------------------------------------PRIMARY SCRIPT END---------------------------------------------------
          -------------------------------------------------UNIQUE CONSTARINT SCRIPT START---------------------------------------------------
          DECLARE @UNIQUE_SCRIPT NVARCHAR(MAX) =''

          SELECT @UNIQUE_SCRIPT += 'ALTER TABLE [' + TL.TEMP_TABLE_NAME
                                   + ']
									 ADD CONSTRAINT [UQ_'
                                   + TL.TEMP_TABLE_NAME + '_'
                                   + CONVERT(VARCHAR(100), Row_number() OVER(PARTITION BY TAB.TABLE_NAME ORDER BY CONSTRAINT_NAME))
                                   + '] UNIQUE ('
                                   + LEFT(CS.COL_LIST, Len(CS.COL_LIST) - 1)
                                   + ')
									
									'
          FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TAB
                 JOIN #TABLE_LIST TL
                   ON TL.ORG_TABLE_NAME = TAB.TABLE_NAME
                 CROSS APPLY (SELECT Quotename(Replace(COLUMN_NAME, 'PROJECTION_DETAILS_SID', 'CCP_DETAILS_SID'))
                                     + ','
                              FROM   INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE COL
                              WHERE  COL.CONSTRAINT_NAME = TAB.CONSTRAINT_NAME
                                     AND COL.TABLE_NAME = TAB.TABLE_NAME
                              FOR XML PATH('')) CS (COL_LIST)
          WHERE  CONSTRAINT_TYPE = 'UNIQUE'
                 AND ORG_TABLE_NAME NOT IN( 'ARM_ADJ_RES_CONFIG_DETAIL', 'ARM_ACC_CONFIG' ) --- ADDED FOR EXCLUDE ARM_ADJ_RES_CONFIG_DETAIL FROM UNIQUE KEY CREATION 
          GROUP  BY TAB.TABLE_NAME,
                    TAB.CONSTRAINT_NAME,
                    CONSTRAINT_SCHEMA,
                    TL.ORG_TABLE_NAME,
                    TL.TEMP_TABLE_NAME,
                    LEFT(CS.COL_LIST, Len(CS.COL_LIST) - 1)

          EXEC Sp_executesql
            @UNIQUE_SCRIPT

          -------------------------------------------------UNIQUE CONSTARINT SCRIPT END---------------------------------------------------
          -------------------------------------------------FOREIGN KEY SCRIPT START---------------------------------------------------
          -- DECLARE @FOREIGNKEY_SCRIPT NVARCHAR(MAX) = ''
          -- SELECT @FOREIGNKEY_SCRIPT += 'ALTER TABLE [' + TL.TEMP_TABLE_NAME
          --                              + ']
          --  ADD CONSTRAINT FK_'
          --                              + CONVERT(VARCHAR(100), F.OBJECT_ID) + '_'
          --                              + @SESSION_ID + ' FOREIGN KEY('
          --                              + QUOTENAME(REPLACE(COL_NAME(FC.PARENT_OBJECT_ID, FC.PARENT_COLUMN_ID), 'PROJECTION_DETAILS_SID', 'CCP_DETAILS_SID'))
          --                              + ') REFERENCES '
          --                              + REPLACE(OBJECT_NAME (F.REFERENCED_OBJECT_ID), 'PROJECTION_DETAILS', 'CCP_DETAILS')
          --                              + '('
          --                              + QUOTENAME(REPLACE(COL_NAME(FC.REFERENCED_OBJECT_ID, FC.REFERENCED_COLUMN_ID), 'PROJECTION_DETAILS_SID', 'CCP_DETAILS_SID'))
          --                              + ')
          --'
          -- FROM   SYS.FOREIGN_KEYS AS F WITH (NOLOCK)
          --        INNER JOIN SYS.FOREIGN_KEY_COLUMNS AS FC WITH (NOLOCK)
          --                ON F.OBJECT_ID = FC.CONSTRAINT_OBJECT_ID
          --        INNER JOIN #TABLE_LIST TL
          --                ON TL.ORG_TABLE_NAME = OBJECT_NAME(F.PARENT_OBJECT_ID)
          -- EXEC (@FOREIGNKEY_SCRIPT)
          --------------------------------------------------------------------INDEX SCRIPT STARTS HERE---------------------------------------------------
          DECLARE @INDEX_SCRIPT NVARCHAR(MAX) =''

          SET @INDEX_SCRIPT = (SELECT 'CREATE ' + CASE WHEN I.IS_UNIQUE = 1 THEN ' UNIQUE ' ELSE '' END + I.TYPE_DESC COLLATE DATABASE_DEFAULT + ' INDEX IDX_' + CONVERT(VARCHAR(100), I.OBJECT_ID) + '_' + CONVERT(VARCHAR(100), Row_number()
                                                                                                                                                                                                                                    OVER(
                                                                                                                                                                                                                                      PARTITION BY I.OBJECT_ID
                                                                                                                                                                                                                                      ORDER BY I.NAME)) + ' ON ' + Schema_name(T.SCHEMA_ID) + '.' + TL.TEMP_TABLE_NAME + ' ( ' + KEYCOLUMNS + ' )  ' + Isnull(' INCLUDE (' + INCLUDEDCOLUMNS + ' ) ', '') + '; '
                               FROM   SYS.INDEXES I
                                      JOIN SYS.TABLES T
                                        ON T.OBJECT_ID = I.OBJECT_ID
                                      JOIN SYS.SYSINDEXES SI
                                        ON I.OBJECT_ID = SI.ID
                                           AND I.INDEX_ID = SI.INDID
                                      JOIN #TABLE_LIST TL
                                        ON TL.ORG_TABLE_NAME = T.NAME
                                      JOIN (SELECT *
                                            FROM   (SELECT IC2.OBJECT_ID,
                                                           IC2.INDEX_ID,
                                                           Stuff((SELECT ' , '
                                                                         + Replace(Replace(C.NAME, 'PROJECTION_DETAILS_SID', 'CCP_DETAILS_SID'), 'ACCRUAL_PROJ_DETAILS_SID', 'CCP_DETAILS_SID,RS_MODEL_SID')
                                                                         + CASE WHEN Max(CONVERT(INT, IC1.IS_DESCENDING_KEY)) = 1 THEN ' DESC ' ELSE ' ASC ' END
                                                                  FROM   SYS.INDEX_COLUMNS IC1
                                                                         JOIN SYS.COLUMNS C
                                                                           ON C.OBJECT_ID = IC1.OBJECT_ID
                                                                              AND C.COLUMN_ID = IC1.COLUMN_ID
                                                                              AND IC1.IS_INCLUDED_COLUMN = 0
                                                                  WHERE  IC1.OBJECT_ID = IC2.OBJECT_ID
                                                                         AND IC1.INDEX_ID = IC2.INDEX_ID
                                                                  GROUP  BY IC1.OBJECT_ID,
                                                                            C.NAME,
                                                                            INDEX_ID
                                                                  ORDER  BY Max(IC1.KEY_ORDINAL)
                                                                  FOR XML PATH('')), 1, 2, '') KEYCOLUMNS
                                                    FROM   SYS.INDEX_COLUMNS IC2
                                                    GROUP  BY IC2.OBJECT_ID,
                                                              IC2.INDEX_ID) TMP3)TMP4
                                        ON I.OBJECT_ID = TMP4.OBJECT_ID
                                           AND I.INDEX_ID = TMP4.INDEX_ID
                                      LEFT JOIN (SELECT *
                                                 FROM   (SELECT IC2.OBJECT_ID,
                                                                IC2.INDEX_ID,
                                                                Stuff((SELECT ' , '
                                                                              + Replace(Replace(C.NAME, 'PROJECTION_DETAILS_SID', 'CCP_DETAILS_SID'), 'ACCRUAL_PROJ_DETAILS_SID', 'CCP_DETAILS_SID,RS_MODEL_SID')
                                                                       FROM   SYS.INDEX_COLUMNS IC1
                                                                              JOIN SYS.COLUMNS C
                                                                                ON C.OBJECT_ID = IC1.OBJECT_ID
                                                                                   AND C.COLUMN_ID = IC1.COLUMN_ID
                                                                                   AND IC1.IS_INCLUDED_COLUMN = 1
                                                                       WHERE  IC1.OBJECT_ID = IC2.OBJECT_ID
                                                                              AND IC1.INDEX_ID = IC2.INDEX_ID
                                                                       GROUP  BY IC1.OBJECT_ID,
                                                                                 C.NAME,
                                                                                 INDEX_ID
                                                                       FOR XML PATH('')), 1, 2, '') INCLUDEDCOLUMNS
                                                         FROM   SYS.INDEX_COLUMNS IC2
                                                         GROUP  BY IC2.OBJECT_ID,
                                                                   IC2.INDEX_ID) TMP1
                                                 WHERE  INCLUDEDCOLUMNS IS NOT NULL) TMP2
                                             ON TMP2.OBJECT_ID = I.OBJECT_ID
                                                AND TMP2.INDEX_ID = I.INDEX_ID
                               WHERE  I.IS_PRIMARY_KEY = 0
                                      AND I.IS_UNIQUE_CONSTRAINT = 0
                               FOR XML PATH(''))

          EXEC Sp_executesql
            @INDEX_SCRIPT

          --------------------------------------------------------------------INDEX SCRIPT ENDS HERE---------------------------------------------------
          -------------------------------------------------------------------- CHANGING NOT NULL TO NULL IN ARM_ADJ_RES_CONFIG_DETAIL TABLE (ARM SETUP TABLE) -- STARTS HERE 
          IF EXISTS(SELECT 1
                    FROM   #TABLE_LIST
                    WHERE  ORG_TABLE_NAME = 'ARM_ADJ_RES_CONFIG_DETAIL')
            BEGIN
                DECLARE @ARM_DEMAND_ADJ_SUMMARY_TABLE VARCHAR(200) = Concat('ST_ARM_ADJ_RES_CONFIG_DETAIL_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

                IF Object_id('TEMPDB..#TEMP_ALTER_NULL') IS NOT NULL
                  DROP TABLE #TEMP_ALTER_NULL

                CREATE TABLE #TEMP_ALTER_NULL
                  (
                     TABLE_NAME  VARCHAR(100),
                     COLUMN_NAME VARCHAR(100),
                     DATATYPE    VARCHAR(100)
                  )

                INSERT INTO #TEMP_ALTER_NULL
                            (TABLE_NAME,
                             COLUMN_NAME,
                             DATATYPE)
                SELECT TABLE_NAME,
                       COLUMN_NAME,
                       DATA_TYPE + CASE WHEN DATA_TYPE IN ( 'VARCHAR', 'CHAR' ) THEN '(' + Cast(CHARACTER_MAXIMUM_LENGTH AS VARCHAR(10)) + ')' WHEN DATA_TYPE IN ('NUMERIC') THEN '(' + Cast(NUMERIC_PRECISION AS VARCHAR(10)) + ',' + Cast(NUMERIC_SCALE AS VARCHAR(10)) + ')' ELSE '' END AS DATATYPE
                FROM   INFORMATION_SCHEMA.COLUMNS
                WHERE  TABLE_NAME = @ARM_DEMAND_ADJ_SUMMARY_TABLE
                       AND IS_NULLABLE = 'NO'
                       AND COLUMN_NAME <> 'ARM_ADJ_RES_CONFIG_DETAIL_SID'

                SET @SQL=''

                SELECT @SQL += Concat('ALTER TABLE ', TABLE_NAME, ' ALTER COLUMN  ', COLUMN_NAME, ' ', DATATYPE, ' NULL ')
                FROM   #TEMP_ALTER_NULL

                EXEC Sp_executesql
                  @SQL
            END

          -------------------------------------------------------------------- CHANGING NOT NULL TO NULL IN ARM_ADJ_RES_CONFIG_DETAIL TABLE (ARM SETUP TABLE) -- ENDS HERE 
          IF EXISTS(SELECT 1
                    FROM   #TABLE_LIST
                    WHERE  ORG_TABLE_NAME = 'STATUS_TABLE')
            BEGIN
                SELECT @SQL = Concat('IF NOT EXISTS (SELECT 1 FROM ', TEMP_TABLE_NAME, ')
					 BEGIN
					 INSERT INTO ', TEMP_TABLE_NAME, '(SCREEN_NAME,VIEW_NAME,FLAG)
					 SELECT SCREEN_NAME,VIEW_NAME,FLAG FROM ', ORG_TABLE_NAME, '
					 END')
                FROM   #TABLE_LIST
                WHERE  ORG_TABLE_NAME = 'STATUS_TABLE'

                EXEC Sp_executesql
                  @SQL
            END

          SELECT 'ST_' + ORG_TABLE_NAME,
                 TEMP_TABLE_NAME
          FROM   #TABLE_LIST
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


