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
          IF OBJECT_ID('TEMPDB..#TABLE_LIST') IS NOT NULL
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
                 CONCAT('ST_', ORG_TABLE_NAME, '_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')) AS TEMP_TABLE_NAME
          FROM   @TABLE_LIST

          -------------------------------------------------CREATE TABLE SCRIPT START---------------------------------------------------
          DECLARE @SQL NVARCHAR(MAX) = ''

          SELECT @SQL += CONCAT('SELECT * INTO ', TEMP_TABLE_NAME, ' FROM ', ORG_TABLE_NAME, ' WHERE 1 = 0 ;')
          FROM   #TABLE_LIST

          EXEC sp_executesql @SQL

          -------------------------------------------------CREATE TABLE SCRIPT END---------------------------------------------------
          -------------------------------------------------------------------- CREATING IDENTITY PROPERTY IN ARM_ADJ_RES_CONFIG_DETAIL TABLE (ARM SETUP TABLE) -- STARTS HERE 
          IF EXISTS(SELECT 1
                    FROM   #TABLE_LIST
                    WHERE  ORG_TABLE_NAME = 'ARM_ADJ_RES_CONFIG_DETAIL')
            BEGIN
                DECLARE @ARM_DEMAND_ADJ_SUMMARY_EXCEPTION VARCHAR(200) = CONCAT('ST_ARM_ADJ_RES_CONFIG_DETAIL_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))

                SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_DEMAND_ADJ_SUMMARY_EXCEPTION, ' DROP COLUMN ARM_ADJ_RES_CONFIG_DETAIL_SID ')

                EXEC sp_executesql @SQL

                SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_DEMAND_ADJ_SUMMARY_EXCEPTION, ' ADD ARM_ADJ_RES_CONFIG_DETAIL_SID INT IDENTITY(1,1) NOT NULL ')

                EXEC sp_executesql @SQL

                SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_DEMAND_ADJ_SUMMARY_EXCEPTION, ' ADD CHECK_RECORD BIT NOT NULL ')

                EXEC sp_executesql @SQL

                SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_DEMAND_ADJ_SUMMARY_EXCEPTION, ' ADD CONSTRAINT DF_ST_ARM_ADJ_RES_CONFIG_DETAIL_CHECK_RECORD_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''), ' DEFAULT 0 FOR CHECK_RECORD ')

                EXEC sp_executesql @SQL

                SET @SQL=''
            END

          -------------------------------------------------------------------- CREATING IDENTITY PROPERTY IN ARM_ADJ_RES_CONFIG_DETAIL TABLE (ARM SETUP TABLE) -- ENDS HERE
		   -------------------------------------------------------------------- CREATING IDENTITY PROPERTY IN ARM_ADJ_RES_CONFIG_DETAIL TABLE (ARM SETUP TABLE) -- STARTS HERE 
          IF EXISTS(SELECT 1
                    FROM   #TABLE_LIST
                    WHERE  ORG_TABLE_NAME = 'ARM_ACC_CONFIG')
            BEGIN
                DECLARE @ARM_ACC_CONFIG_EXCEPTION VARCHAR(200) = CONCAT('ST_ARM_ACC_CONFIG_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
               

                SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ALTER COLUMN GL_COMPANY_MASTER_SID INT  NULL ')

                EXEC sp_executesql @SQL

                SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ALTER COLUMN BU_COMPANY_MASTER_SID INT  NULL ')

                EXEC sp_executesql @SQL

			      SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ALTER COLUMN ACCOUNT VARCHAR(50)  NULL ')

                EXEC sp_executesql @SQL

				  SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ALTER COLUMN BRAND_MASTER_SID INT  NULL ')

                EXEC sp_executesql @SQL

				SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ADD CHECK_RECORD BIT NOT NULL ')

                EXEC sp_executesql @SQL

                SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ADD CONSTRAINT DF_ST_ARM_ACC_CONFIG_CHECK_RECORD_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''), ' DEFAULT 0 FOR CHECK_RECORD ')

                EXEC sp_executesql @SQL

		       
				 SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ADD ARM_ACC_CONFIG_MASTER_SID INT NULL ')

                EXEC sp_executesql @SQL

				SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ADD SAVE_RECORD BIT NULL ')

                EXEC sp_executesql @SQL

				 SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ADD CONSTRAINT DF_ST_ARM_ACC_CONFIG_SAVE_RECORD_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''), ' DEFAULT 0 FOR SAVE_RECORD ')

                EXEC sp_executesql @SQL

				SET @SQL=''
                SET @SQL =CONCAT('ALTER TABLE ', @ARM_ACC_CONFIG_EXCEPTION, ' ADD DELETE_RECORD BIT NULL DEFAULT 0')

                EXEC sp_executesql @SQL
				
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

          
                SET @SQL =Concat('ALTER TABLE ST_ACCRUAL_RATE_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''),' ADD RS_MODEL_SID INT NOT NULL')
		
                EXEC sp_executesql @SQL
		
            END 
          


          -------------------------------------------------RENAME COLUMN SCRIPT END---------------------------------------------------
          -------------------------------------------------DEFAULT SCRIPT START---------------------------------------------------
          DECLARE @DEFAULT_SCRIPT NVARCHAR(MAX) =''

          SELECT @DEFAULT_SCRIPT += 'ALTER TABLE [' + SCHEMA_NAME(SCHEMA_ID)
                                    + '].[' + TL.TEMP_TABLE_NAME
                                    + ']
										ADD CONSTRAINT [DF_'
                                    + CONVERT(VARCHAR(100), DC.OBJECT_ID) + '_'
                                    + @SESSION_ID + '] DEFAULT ' + DEFINITION
                                    + ' FOR ['
                                    + REPLACE(C.NAME, 'PROJECTION_DETAILS_SID', 'CCP_DETAILS_SID')
                                    + ']
								
								'
          FROM   SYS.DEFAULT_CONSTRAINTS DC
                 INNER JOIN SYS.COLUMNS C
                         ON DC.PARENT_OBJECT_ID = C.OBJECT_ID
                            AND DC.PARENT_COLUMN_ID = C.COLUMN_ID
                 JOIN #TABLE_LIST TL
                   ON OBJECT_ID(TL.ORG_TABLE_NAME) = PARENT_OBJECT_ID

          --PRINT @DEFAULT_SCRIPT
          EXEC sp_executesql @DEFAULT_SCRIPT


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
          IF OBJECT_ID('TEMPDB..#PRIMARY') IS NOT NULL
            DROP TABLE #PRIMARY

          SELECT T.NAME AS ORG_TABLE_NAME,
                 K.NAME AS CONSTRAINT_NAME,
                 C.NAME AS COLUMN_NAME,
                 TL.TEMP_TABLE_NAME
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
          WHERE  K.TYPE = 'PK'


          SET @PRIMARY_SCRIPT = (SELECT DISTINCT 'ALTER TABLE [' + C.TEMP_TABLE_NAME
                                                 + '] ADD CONSTRAINT ['
                                                 + REPLACE(C.CONSTRAINT_NAME, C.ORG_TABLE_NAME, C.TEMP_TABLE_NAME)
                                                 + '] PRIMARY KEY ('
                                                 + LEFT(CS.COL_LIST, LEN(CS.COL_LIST) - 1)
                                                 + '); '
                                 FROM   #PRIMARY C
                                        CROSS APPLY (SELECT REPLACE(REPLACE(D.COLUMN_NAME, 'PROJECTION_DETAILS_SID', 'CCP_DETAILS_SID'),'ACCRUAL_PROJ_DETAILS_SID','CCP_DETAILS_SID,RS_MODEL_SID')
                                                            + ','
                                                     FROM   #PRIMARY D
                                                     WHERE  C.ORG_TABLE_NAME = D.ORG_TABLE_NAME
                                                     FOR XML PATH ('')) CS (COL_LIST)
                                 FOR XML PATH (''))



          EXEC sp_executesql @PRIMARY_SCRIPT
	
          -------------------------------------------------PRIMARY SCRIPT END---------------------------------------------------
          -------------------------------------------------UNIQUE CONSTARINT SCRIPT START---------------------------------------------------
          DECLARE @UNIQUE_SCRIPT NVARCHAR(MAX) =''

          SELECT @UNIQUE_SCRIPT += 'ALTER TABLE [' + TL.TEMP_TABLE_NAME
                                   + ']
									 ADD CONSTRAINT [UQ_'
                                   + TL.TEMP_TABLE_NAME + '_'
                                   + CONVERT(VARCHAR(100), ROW_NUMBER() OVER(PARTITION BY TAB.TABLE_NAME ORDER BY CONSTRAINT_NAME))
                                   + '] UNIQUE ('
                                   + LEFT(CS.COL_LIST, LEN(CS.COL_LIST) - 1)
                                   + ')
									
									'
          FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TAB
                 JOIN #TABLE_LIST TL
                   ON TL.ORG_TABLE_NAME = TAB.TABLE_NAME
                 CROSS APPLY (SELECT QUOTENAME(REPLACE(COLUMN_NAME, 'PROJECTION_DETAILS_SID', 'CCP_DETAILS_SID'))
                                     + ','
                              FROM   INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE COL
                              WHERE  COL.CONSTRAINT_NAME = TAB.CONSTRAINT_NAME
                                     AND COL.TABLE_NAME = TAB.TABLE_NAME
                              FOR XML PATH('')) CS (COL_LIST)
          WHERE  CONSTRAINT_TYPE = 'UNIQUE'
		  AND ORG_TABLE_NAME NOT IN('ARM_ADJ_RES_CONFIG_DETAIL','ARM_ACC_CONFIG') --- ADDED FOR EXCLUDE ARM_ADJ_RES_CONFIG_DETAIL FROM UNIQUE KEY CREATION 
          GROUP  BY TAB.TABLE_NAME,
                    TAB.CONSTRAINT_NAME,
                    CONSTRAINT_SCHEMA,
                    TL.ORG_TABLE_NAME,
                    TL.TEMP_TABLE_NAME,
                    LEFT(CS.COL_LIST, LEN(CS.COL_LIST) - 1)

          EXEC sp_executesql @UNIQUE_SCRIPT

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

          SET @INDEX_SCRIPT = (SELECT 'CREATE ' + CASE WHEN I.IS_UNIQUE = 1 THEN ' UNIQUE ' ELSE '' END + I.TYPE_DESC COLLATE DATABASE_DEFAULT + ' INDEX IDX_' + CONVERT(VARCHAR(100), I.OBJECT_ID) + '_' + CONVERT(VARCHAR(100), ROW_NUMBER()
                                                                                                                                                                                                                                    OVER(
                                                                                                                                                                                                                                      PARTITION BY I.OBJECT_ID
                                                                                                                                                                                                                                      ORDER BY I.NAME)) + ' ON ' + SCHEMA_NAME(T.SCHEMA_ID) + '.' + TL.TEMP_TABLE_NAME + ' ( ' + KEYCOLUMNS + ' )  ' + ISNULL(' INCLUDE (' + INCLUDEDCOLUMNS + ' ) ', '') + '; '
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
                                                           STUFF((SELECT ' , '
                                                                         + REPLACE(REPLACE(C.NAME, 'PROJECTION_DETAILS_SID', 'CCP_DETAILS_SID'),'ACCRUAL_PROJ_DETAILS_SID','CCP_DETAILS_SID,RS_MODEL_SID')
                                                                         + CASE WHEN MAX(CONVERT(INT, IC1.IS_DESCENDING_KEY)) = 1 THEN ' DESC ' ELSE ' ASC ' END
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
                                                                  ORDER  BY MAX(IC1.KEY_ORDINAL)
                                                                  FOR XML PATH('')), 1, 2, '') KEYCOLUMNS
                                                    FROM   SYS.INDEX_COLUMNS IC2
                                                    GROUP  BY IC2.OBJECT_ID,
                                                              IC2.INDEX_ID) TMP3)TMP4
                                        ON I.OBJECT_ID = TMP4.OBJECT_ID
                                           AND I.INDEX_ID = TMP4.INDEX_ID
                                      LEFT JOIN (SELECT *
                                                 FROM   (SELECT IC2.OBJECT_ID,
                                                                IC2.INDEX_ID,
                                                                STUFF((SELECT ' , '
                                                                              +REPLACE(REPLACE(C.NAME, 'PROJECTION_DETAILS_SID', 'CCP_DETAILS_SID'),'ACCRUAL_PROJ_DETAILS_SID','CCP_DETAILS_SID,RS_MODEL_SID')
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
							
          EXEC sp_executesql @INDEX_SCRIPT

          --------------------------------------------------------------------INDEX SCRIPT ENDS HERE---------------------------------------------------
          -------------------------------------------------------------------- CHANGING NOT NULL TO NULL IN ARM_ADJ_RES_CONFIG_DETAIL TABLE (ARM SETUP TABLE) -- STARTS HERE 
          IF EXISTS(SELECT 1
                    FROM   #TABLE_LIST
                    WHERE  ORG_TABLE_NAME = 'ARM_ADJ_RES_CONFIG_DETAIL')
            BEGIN
                DECLARE @ARM_DEMAND_ADJ_SUMMARY_TABLE VARCHAR(200) = CONCAT('ST_ARM_ADJ_RES_CONFIG_DETAIL_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))

                IF OBJECT_ID('TEMPDB..#TEMP_ALTER_NULL') IS NOT NULL
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
                       DATA_TYPE + CASE WHEN DATA_TYPE IN ( 'VARCHAR', 'CHAR' ) THEN '(' + CAST(CHARACTER_MAXIMUM_LENGTH AS VARCHAR(10)) + ')' WHEN DATA_TYPE IN ('NUMERIC') THEN '(' + CAST(NUMERIC_PRECISION AS VARCHAR(10)) + ',' + CAST(NUMERIC_SCALE AS VARCHAR(10)) + ')' ELSE '' END AS DATATYPE
                FROM   INFORMATION_SCHEMA.COLUMNS
                WHERE  TABLE_NAME = @ARM_DEMAND_ADJ_SUMMARY_TABLE
                       AND IS_NULLABLE = 'NO'
                       AND COLUMN_NAME <> 'ARM_ADJ_RES_CONFIG_DETAIL_SID'

                SET @SQL=''

                SELECT @SQL += CONCAT('ALTER TABLE ', TABLE_NAME, ' ALTER COLUMN  ', COLUMN_NAME, ' ', DATATYPE, ' NULL ')
                FROM   #TEMP_ALTER_NULL

                EXEC sp_executesql @SQL
            END

          -------------------------------------------------------------------- CHANGING NOT NULL TO NULL IN ARM_ADJ_RES_CONFIG_DETAIL TABLE (ARM SETUP TABLE) -- ENDS HERE 
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


          EXEC USPERRORCOLLECTOR

          SELECT @ERRORMESSAGE = ERROR_MESSAGE(),
                 @ERRORSEVERITY = ERROR_SEVERITY(),
                 @ERRORSTATE = ERROR_STATE(),
                 @ERRORPROCEDURE = ERROR_PROCEDURE(),
                 @ERRORLINE = ERROR_LINE(),
                 @ERRORNUMBER = ERROR_NUMBER()

          RAISERROR ( @ERRORMESSAGE,-- MESSAGE TEXT.
                      @ERRORSEVERITY,-- SEVERITY.
                      @ERRORSTATE,-- STATE.
                      @ERRORPROCEDURE,-- PROCEDURE_NAME.
                      @ERRORNUMBER,-- ERRORNUMBER
                      @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END 
