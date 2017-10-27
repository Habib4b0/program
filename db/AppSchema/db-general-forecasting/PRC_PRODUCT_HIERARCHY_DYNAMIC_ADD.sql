IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_PRODUCT_HIERARCHY_DYNAMIC_ADD'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_PRODUCT_HIERARCHY_DYNAMIC_ADD]
  END

GO

CREATE PROCEDURE [dbo].[PRC_PRODUCT_HIERARCHY_DYNAMIC_ADD]
AS
  BEGIN
      SET NOCOUNT ON
	  ------------------------------------------Variable Declaration-------------------------------
      DECLARE @I                              INT = 1,
              @j                              INT = 1,
              @CNT                            INT = 0,
              @CNT_HLD                        INT = 0,
              @RELATIONSHIP_BUILDER_SID       INT,
              @HIERARCHY_DEFINITION_SID       INT,
              @HIERARCHY_LEVEL_DEFINITION_SID INT,
              @INCLUSION_RULE_TYPE            VARCHAR(50),
              @EXCLUSION_RULE_TYPE            VARCHAR(50),
              @INCLUSION_RULE                 VARCHAR(50),
              @EXCLUSION_RULE                 VARCHAR(50),
              @LEVEL_NO                       INT,
              @LEVEL_NAME                     VARCHAR(50),
              @LEVEL_VALUE_REFERENCE          VARCHAR(50),
              @FORMULA                        NVARCHAR(MAX),
              @EXISTING_VERSION_NO            INT,
              @CNT_GRP                        INT,
              @k                              INT = 1,
              @INCLUSION_CONDITION            VARCHAR(5),
              @EXCLUSION_CONDITION            VARCHAR(5),
              @VAR_AND_CHECK                  INT = 0,
              @max_hier                       VARCHAR(100),
              @len_hier                       INT,
              @hier_no                        VARCHAR(100),
              @F                              INT=1,
              @Parent_no_fix                  VARCHAR(100),
              @Parent_no_Fix_count            INT =0

      IF Object_id('tempdb..#temp_rld_master') IS NOT NULL
        DROP TABLE #temp_rld_master

      CREATE TABLE #temp_rld_master
        (
           RID                      INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
           RELATIONSHIP_BUILDER_SID INT NOT NULL,
           HIERARCHY_DEFINITION_SID INT NOT NULL
        )

      INSERT INTO #temp_rld_master
                  (RELATIONSHIP_BUILDER_SID,
                   HIERARCHY_DEFINITION_SID)
      SELECT DISTINCT a.RELATIONSHIP_BUILDER_SID,
                      a.HIERARCHY_DEFINITION_SID
      FROM   RELATIONSHIP_BUILDER a
      JOIN   HIERARCHY_LEVEL_DEFINITION b ON a.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID
      JOIN   HIERARCHY_DEFINITION hd ON a.HIERARCHY_DEFINITION_SID = hd.HIERARCHY_DEFINITION_SID
      WHERE  a.BUILD_TYPE = 'AUTOMATIC'
             AND EXISTS (SELECT HELPER_TABLE_SID
                                          FROM   HELPER_TABLE H1
                                          WHERE  LIST_NAME = 'HIERARCHY_CATEGORY'
                                                 AND DESCRIPTION = 'Product Hierarchy'
												 AND hd.HIERARCHY_CATEGORY = H1.HELPER_TABLE_SID )
             AND b.LEVEL_VALUE_REFERENCE = 'linked'

      SELECT @CNT = Count(*)
      FROM   #temp_rld_master

      IF Object_id('tempdb..#TEMP_FIX') IS NOT NULL
        DROP TABLE #TEMP_FIX

      CREATE TABLE #TEMP_FIX
        (
           ID          INT IDENTITY(1, 1),
           PARENT_NODE VARCHAR(100)
        )

      IF Object_id('tempdb..#temp_holder') IS NOT NULL
        DROP TABLE #temp_holder

      CREATE TABLE #temp_holder
        (
           RID            INT IDENTITY(1, 1),
           INCLUSION_RULE VARCHAR(100)
        )

      ----------------------------------------------Master While Loop starts here--------------------------------------
      WHILE ( @I <= @CNT )--for each RELATIONSHIP_BUILDER_SID
        BEGIN
            SELECT @RELATIONSHIP_BUILDER_SID = RELATIONSHIP_BUILDER_SID,
                   @HIERARCHY_DEFINITION_SID = HIERARCHY_DEFINITION_SID
            FROM   #temp_rld_master
            WHERE  RID = @I

            SELECT TOP 1 @EXISTING_VERSION_NO = VERSION_NO
            FROM   RELATIONSHIP_LEVEL_DEFINITION
            WHERE  RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
			ORDER BY RELATIONSHIP_BUILDER_SID

            ---------------------Data for each Relationship_builder to check for rules------------------------------------
            IF Object_id('tempdb..#TEMP_GLOBAL_CCP_PROD') IS NOT NULL
              DROP TABLE #TEMP_GLOBAL_CCP_PROD

            CREATE TABLE #TEMP_GLOBAL_CCP_PROD
              (
                 ITEM_MASTER_SID            INT,
                 GL_COMP_COMPANY_MASTER_SID INT,
                 BRAND_MASTER_SID           INT,
                 THERAPEUTIC_CLASS_HLPR_SID INT
              )

            INSERT INTO #TEMP_GLOBAL_CCP_PROD
                        (ITEM_MASTER_SID,
                         GL_COMP_COMPANY_MASTER_SID,
                         BRAND_MASTER_SID,
                         THERAPEUTIC_CLASS_HLPR_SID)
            SELECT DISTINCT ITEM_MASTER_SID,
                            GL_COMP_COMPANY_MASTER_SID,
                            BRAND_MASTER_SID,
                            THERAPEUTIC_CLASS_HLPR_SID
            FROM   ##TEMP_GLOBAL_CCP

            ---------------------Data for each Relationship_builder to check for rules------------------------------------
            ---------------------------Table For each Relationship_builder to store the results starts here-------------------------
            IF Object_id('tempdb..#temp_rld_result') IS NOT NULL
              DROP TABLE #temp_rld_result

            CREATE TABLE #temp_rld_result
              (
                 RID                            INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
                 ITEM_MASTER_SID                INT NOT NULL,
                 GL_COMP_COMPANY_MASTER_SID     INT NULL,
                 THERAPEUTIC_CLASS_HLPR_SID     INT NULL,
                 BRAND_MASTER_SID               INT NULL,
                 RELATIONSHIP_BUILDER_SID       INT NOT NULL,
                 HIERARCHY_LEVEL_DEFINITION_SID INT NOT NULL,
                 LEVEL_NO                       INT NOT NULL,
                 LEVEL_NAME                     VARCHAR(50) NOT NULL,
                 LEVEL_VALUES                   VARCHAR(50) NULL,
                 PARENT_NODE                    VARCHAR(100) NULL,
                 HIERARCHY_NO                   VARCHAR(50) NULL
              )

            ---------------------------Table For each Relationship_builder to store the results ends here-------------------------
            --------------Available levels in the Hierarchy should not be NULL in the new CCP's which is coming to the system for the existing relationship starts here
            IF EXISTS(SELECT 1
                      FROM   HIERARCHY_LEVEL_DEFINITION
                      WHERE  HIERARCHY_DEFINITION_SID = @HIERARCHY_DEFINITION_SID
                             AND LEVEL_NAME IN ( 'COMPANY', 'GL COMPANY' ))
              BEGIN
                  DELETE FROM #TEMP_GLOBAL_CCP_PROD
                  WHERE  GL_COMP_COMPANY_MASTER_SID IS NULL
              END

            IF EXISTS(SELECT 1
                      FROM   HIERARCHY_LEVEL_DEFINITION
                      WHERE  HIERARCHY_DEFINITION_SID = @HIERARCHY_DEFINITION_SID
                             AND LEVEL_NAME = 'Brand')
              BEGIN
                  DELETE FROM #TEMP_GLOBAL_CCP_PROD
                  WHERE  BRAND_MASTER_SID IS NULL
              END

            IF EXISTS(SELECT 1
                      FROM   HIERARCHY_LEVEL_DEFINITION
                      WHERE  HIERARCHY_DEFINITION_SID = @HIERARCHY_DEFINITION_SID
                             AND LEVEL_NAME = 'Therapeutic Class')
              BEGIN
                  DELETE FROM #TEMP_GLOBAL_CCP_PROD
                  WHERE  THERAPEUTIC_CLASS_HLPR_SID IS NULL
              END

            --------------Available levels in the Hierarchy should not be NULL in the new CCP's which is coming to the system for the existing relationship ends here
            -------Table to store data of Hierarchy_level_definition values for each Relationship_Builder starts here-------------------
            IF Object_id('tempdb..#temp_rld_hld') IS NOT NULL
              DROP TABLE #temp_rld_hld

            CREATE TABLE #temp_rld_hld
              (
                 RID                            INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
                 HIERARCHY_LEVEL_DEFINITION_SID INT NOT NULL,
                 LEVEL_NO                       INT NOT NULL,
                 LEVEL_NAME                     VARCHAR(100) NOT NULL,
                 LEVEL_VALUE_REFERENCE          VARCHAR(20),
                 INCLUSION_RULE_TYPE            VARCHAR(5),
                 INCLUSION_RULE                 VARCHAR(100),
                 EXCLUSION_RULE_TYPE            VARCHAR(5),
                 EXCLUSION_RULE                 VARCHAR(100),
                 INCLUSION_CONDITION            VARCHAR(5),
                 EXCLUSION_CONDITION            VARCHAR(5)
              )

            INSERT INTO #temp_rld_hld
                        (HIERARCHY_LEVEL_DEFINITION_SID,
                         LEVEL_NO,
                         LEVEL_NAME,
                         LEVEL_VALUE_REFERENCE,
                         INCLUSION_RULE_TYPE,
                         INCLUSION_RULE,
                         EXCLUSION_RULE_TYPE,
                         EXCLUSION_RULE,
                         INCLUSION_CONDITION,
                         EXCLUSION_CONDITION)
            SELECT HIERARCHY_LEVEL_DEFINITION_SID,
                   LEVEL_NO,
                   LEVEL_NAME,
                   LEVEL_VALUE_REFERENCE,
                   INCLUSION_RULE_TYPE,
                   INCLUSION_RULE,
                   EXCLUSION_RULE_TYPE,
                   EXCLUSION_RULE,
                   INCLUSION_CONDITION,
                   EXCLUSION_CONDITION
            FROM   HIERARCHY_LEVEL_DEFINITION
            WHERE  HIERARCHY_DEFINITION_SID = @HIERARCHY_DEFINITION_SID
                   AND LEVEL_VALUE_REFERENCE = 'linked'

            -------Table to store data of Hierarchy_level_definition values for each Relationship_Builder ends here-------------------
            SELECT @cnt_hld = Count(*)
            FROM   #temp_rld_hld

            WHILE ( @j <= @cnt_hld )--inner while loop(for each HIERARCHY_LEVEL_DEFINITION level in a RELATIONSHIP_BUILDER_SID)
              BEGIN
                  SELECT @INCLUSION_RULE_TYPE = INCLUSION_RULE_TYPE,
                         @INCLUSION_RULE = INCLUSION_RULE,
                         @EXCLUSION_RULE_TYPE = EXCLUSION_RULE_TYPE,
                         @EXCLUSION_RULE = EXCLUSION_RULE,
                         @INCLUSION_CONDITION = INCLUSION_CONDITION,
                         @EXCLUSION_CONDITION = EXCLUSION_CONDITION,
                         @LEVEL_NO = LEVEL_NO,
                         @LEVEL_NAME = LEVEL_NAME,
                         @LEVEL_VALUE_REFERENCE = LEVEL_VALUE_REFERENCE,
                         @HIERARCHY_LEVEL_DEFINITION_SID = HIERARCHY_LEVEL_DEFINITION_SID
                  FROM   #temp_rld_hld
                  WHERE  RID = @j

                  SET @FORMULA = ''

                  IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                    DROP TABLE ##TEMP_DYNAMIC

                  IF @INCLUSION_RULE_TYPE = 'RULE'
                    BEGIN
                        IF @LEVEL_NAME = 'Product'
                            OR @LEVEL_NAME = 'NDC'
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @INCLUSION_RULE
                                                   AND EXISTS (SELECT     1
                                                               FROM       sys.foreign_key_columns fkc
                                                               INNER JOIN sys.columns c ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                               INNER JOIN sys.columns cref ON fkc.referenced_column_id = cref.column_id
                                                                                          AND fkc.referenced_object_id = cref.object_id
                                                               WHERE      Object_name(parent_object_id) = hd.TABLE_NAME
                                                                          AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT ITEM_MASTER_SID INTO ##TEMP_DYNAMIC_PROD FROM ITEM_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @INCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT    @formula = CASE
                                                           WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select ITEM_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from ITEM_MASTER where '
                                                                                                             + a.COLUMN_NAME
                                                                                                             + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                             + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                           ELSE 'select ITEM_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from ITEM_MASTER where '
                                                                + a.COLUMN_NAME + ' in (select '
                                                                + b.MAPPING_COLUMN_NAME + ' from '
                                                                + b.REFERENCE_TABLE_NAME + ' where '
                                                                + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                         END
                                    FROM      HIERARCHY_RULES_DEFINITION a
                                    LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                              AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE     a.RULE_NAME = @INCLUSION_RULE
                                              AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC SP_EXECUTESQL
                                @FORMULA

                              DELETE FROM #TEMP_GLOBAL_CCP_PROD
                              WHERE  NOT EXISTS (SELECT ITEM_MASTER_SID
                              FROM   ##TEMP_DYNAMIC_PROD T WHERE T.ITEM_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.ITEM_MASTER_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC_PROD
                          END
                        ELSE IF @LEVEL_NAME = 'Brand'
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @INCLUSION_RULE
                                                   AND EXISTS (SELECT     1
                                                               FROM       sys.foreign_key_columns fkc
                                                               INNER JOIN sys.columns c ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                               INNER JOIN sys.columns cref ON fkc.referenced_column_id = cref.column_id
                                                                                          AND fkc.referenced_object_id = cref.object_id
                                                               WHERE      Object_name(parent_object_id) = hd.TABLE_NAME
                                                                          AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT BRAND_MASTER_SID INTO ##TEMP_DYNAMIC_PROD FROM BRAND_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @INCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT    @formula = CASE
                                                           WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select BRAND_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from BRAND_MASTER where '
                                                                                                             + a.COLUMN_NAME
                                                                                                             + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                             + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                           ELSE 'select BRAND_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from BRAND_MASTER where '
                                                                + a.COLUMN_NAME + ' in (select '
                                                                + b.MAPPING_COLUMN_NAME + ' from '
                                                                + b.REFERENCE_TABLE_NAME + ' where '
                                                                + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                         END
                                    FROM      HIERARCHY_RULES_DEFINITION a
                                    LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                              AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE     a.RULE_NAME = @INCLUSION_RULE
                                              AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC SP_EXECUTESQL
                                @FORMULA

                              DELETE FROM #TEMP_GLOBAL_CCP_PROD
                              WHERE  NOT EXISTS (SELECT BRAND_MASTER_SID
                                                              FROM   ##TEMP_DYNAMIC_PROD T WHERE T.BRAND_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.BRAND_MASTER_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC_PROD
                          END
                        ELSE IF @LEVEL_NAME = 'Therapeutic Class'
                          BEGIN
                              SELECT    @formula = 'select THERAPEUTIC_CLASS INTO ##TEMP_DYNAMIC_PROD from ITEM_MASTER where '
                                                   + a.COLUMN_NAME
                                                   + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                   + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                              FROM      HIERARCHY_RULES_DEFINITION a
                              LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                        AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                              WHERE     a.RULE_NAME = @INCLUSION_RULE
                                        AND b.MAPPING_COLUMN_NAME IS NOT NULL

                              EXEC SP_EXECUTESQL
                                @FORMULA

                              DELETE FROM #TEMP_GLOBAL_CCP_PROD
                              WHERE  NOT EXISTS (SELECT THERAPEUTIC_CLASS
                                                                        FROM   ##TEMP_DYNAMIC_PROD T WHERE T.THERAPEUTIC_CLASS=#TEMP_GLOBAL_CCP_PROD.THERAPEUTIC_CLASS_HLPR_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC_PROD
                          END
                        ELSE IF @LEVEL_NAME = 'GL Company'
                            OR @LEVEL_NAME = 'Company'
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @INCLUSION_RULE
                                                   AND EXISTS (SELECT     1
                                                               FROM       sys.foreign_key_columns fkc
                                                               INNER JOIN sys.columns c ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                               INNER JOIN sys.columns cref ON fkc.referenced_column_id = cref.column_id
                                                                                          AND fkc.referenced_object_id = cref.object_id
                                                               WHERE      Object_name(parent_object_id) = hd.TABLE_NAME
                                                                          AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC_PROD FROM COMPANY_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @INCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT    @formula = CASE
                                                           WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from COMPANY_MASTER where '
                                                                                                             + a.COLUMN_NAME
                                                                                                             + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                             + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                           ELSE 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from COMPANY_MASTER where '
                                                                + a.COLUMN_NAME + ' in (select '
                                                                + b.MAPPING_COLUMN_NAME + ' from '
                                                                + b.REFERENCE_TABLE_NAME + ' where '
                                                                + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                         END
                                    FROM      HIERARCHY_RULES_DEFINITION a
                                    LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                              AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE     a.RULE_NAME = @INCLUSION_RULE
                                              AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC SP_EXECUTESQL
                                @FORMULA

                              DELETE FROM #TEMP_GLOBAL_CCP_PROD
                              WHERE  NOT EXISTS (SELECT COMPANY_MASTER_SID
                                                                        FROM   ##TEMP_DYNAMIC_PROD T WHERE T.COMPANY_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.GL_COMP_COMPANY_MASTER_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC_PROD
                          END
                    END --End of Inclusion Rule Type
                  SET @FORMULA = ''

                  IF @EXCLUSION_RULE_TYPE = 'RULE'
                    BEGIN
                        IF @LEVEL_NAME = 'Product'
                            OR @LEVEL_NAME = 'NDC'
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @EXCLUSION_RULE
                                                   AND EXISTS (SELECT     1
                                                               FROM       sys.foreign_key_columns fkc
                                                               INNER JOIN sys.columns c ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                               INNER JOIN sys.columns cref ON fkc.referenced_column_id = cref.column_id
                                                                                          AND fkc.referenced_object_id = cref.object_id
                                                               WHERE      Object_name(parent_object_id) = hd.TABLE_NAME
                                                                          AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT ITEM_MASTER_SID INTO ##TEMP_DYNAMIC_PROD FROM ITEM_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @EXCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT    @formula = CASE
                                                           WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select ITEM_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from ITEM_MASTER where '
                                                                                                             + a.COLUMN_NAME
                                                                                                             + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                             + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                           ELSE 'select ITEM_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from ITEM_MASTER where '
                                                                + a.COLUMN_NAME + ' in (select '
                                                                + b.MAPPING_COLUMN_NAME + ' from '
                                                                + b.REFERENCE_TABLE_NAME + ' where '
                                                                + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                         END
                                    FROM      HIERARCHY_RULES_DEFINITION a
                                    LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                              AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE     a.RULE_NAME = @EXCLUSION_RULE
                                              AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC SP_EXECUTESQL
                                @FORMULA

                              DELETE FROM #TEMP_GLOBAL_CCP_PROD
                              WHERE  NOT EXISTS (SELECT ITEM_MASTER_SID
                                                             FROM   ##TEMP_DYNAMIC_PROD T WHERE T.ITEM_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.ITEM_MASTER_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC_PROD
                          END
                        ELSE IF @LEVEL_NAME = 'Brand'
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @EXCLUSION_RULE
                                                   AND EXISTS (SELECT     1
                                                               FROM       sys.foreign_key_columns fkc
                                                               INNER JOIN sys.columns c ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                               INNER JOIN sys.columns cref ON fkc.referenced_column_id = cref.column_id
                                                                                          AND fkc.referenced_object_id = cref.object_id
                                                               WHERE      Object_name(parent_object_id) = hd.TABLE_NAME
                                                                          AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT BRAND_MASTER_SID INTO ##TEMP_DYNAMIC_PROD FROM BRAND_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @EXCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT    @formula = CASE
                                                           WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select BRAND_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from BRAND_MASTER where '
                                                                                                             + a.COLUMN_NAME
                                                                                                             + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                             + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                           ELSE 'select BRAND_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from BRAND_MASTER where '
                                                                + a.COLUMN_NAME + ' in (select '
                                                                + b.MAPPING_COLUMN_NAME + ' from '
                                                                + b.REFERENCE_TABLE_NAME + ' where '
                                                                + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                         END
                                    FROM      HIERARCHY_RULES_DEFINITION a
                                    LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                              AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE     a.RULE_NAME = @EXCLUSION_RULE
                                              AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC SP_EXECUTESQL
                                @FORMULA

                              DELETE FROM #TEMP_GLOBAL_CCP_PROD
                              WHERE  NOT EXISTS (SELECT BRAND_MASTER_SID
                                                              FROM   ##TEMP_DYNAMIC_PROD T WHERE T.BRAND_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.BRAND_MASTER_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC_PROD
                          END
                        ELSE IF @LEVEL_NAME = 'Therapeutic Class'
                          BEGIN
                              SELECT    @formula = 'select THERAPEUTIC_CLASS INTO ##TEMP_DYNAMIC_PROD from ITEM_MASTER where '
                                                   + a.COLUMN_NAME
                                                   + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                   + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                              FROM      HIERARCHY_RULES_DEFINITION a
                              LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                        AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                              WHERE     a.RULE_NAME = @INCLUSION_RULE
                                        AND b.MAPPING_COLUMN_NAME IS NOT NULL

                              EXEC SP_EXECUTESQL
                                @FORMULA

                              DELETE FROM #TEMP_GLOBAL_CCP_PROD
                              WHERE  NOT EXISTS (SELECT THERAPEUTIC_CLASS
                                                                        FROM   ##TEMP_DYNAMIC_PROD T WHERE T.THERAPEUTIC_CLASS=#TEMP_GLOBAL_CCP_PROD.THERAPEUTIC_CLASS_HLPR_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC_PROD
                          END
                        ELSE IF @LEVEL_NAME = 'GL Company'
                            OR @LEVEL_NAME = 'Company'
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @EXCLUSION_RULE
                                                   AND EXISTS (SELECT     1
                                                               FROM       sys.foreign_key_columns fkc
                                                               INNER JOIN sys.columns c ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                               INNER JOIN sys.columns cref ON fkc.referenced_column_id = cref.column_id
                                                                                          AND fkc.referenced_object_id = cref.object_id
                                                               WHERE      Object_name(parent_object_id) = hd.TABLE_NAME
                                                                          AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC_PROD FROM COMPANY_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @EXCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT    @formula = CASE
                                                           WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from COMPANY_MASTER where '
                                                                                                             + a.COLUMN_NAME
                                                                                                             + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                             + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                           ELSE 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from COMPANY_MASTER where '
                                                                + a.COLUMN_NAME + ' in (select '
                                                                + b.MAPPING_COLUMN_NAME + ' from '
                                                                + b.REFERENCE_TABLE_NAME + ' where '
                                                                + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                         END
                                    FROM      HIERARCHY_RULES_DEFINITION a
                                    LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                              AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE     a.RULE_NAME = @EXCLUSION_RULE
                                              AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC SP_EXECUTESQL
                                @FORMULA

                              DELETE FROM #TEMP_GLOBAL_CCP_PROD
                              WHERE  NOT EXISTS (SELECT COMPANY_MASTER_SID
                                                                        FROM   ##TEMP_DYNAMIC_PROD T WHERE T.COMPANY_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.GL_COMP_COMPANY_MASTER_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC_PROD
                          END
                    END--End of Exclusion Rule Type
                  SET @FORMULA = ''

                  IF ( @INCLUSION_CONDITION = 'AND'
                        OR @EXCLUSION_CONDITION = 'AND' )
                    BEGIN
                        IF Object_id('tempdb..#TEMP_GLOBAL_CCP_PROD_SWAP') IS NOT NULL
                          DROP TABLE #TEMP_GLOBAL_CCP_PROD_SWAP

                        CREATE TABLE #TEMP_GLOBAL_CCP_PROD_SWAP
                          (
                             ITEM_MASTER_SID            INT,
                             GL_COMP_COMPANY_MASTER_SID INT,
                             BRAND_MASTER_SID           INT,
                             THERAPEUTIC_CLASS_HLPR_SID INT
                          )
                    END

                  IF ( @INCLUSION_CONDITION = 'OR'
                        OR @EXCLUSION_CONDITION = 'OR' )
                    BEGIN
                        IF Object_id('tempdb..#TEMP_OR_LOOP_SAVE') IS NOT NULL
                          DROP TABLE #TEMP_OR_LOOP_SAVE

                        CREATE TABLE #TEMP_OR_LOOP_SAVE
                          (
                             VALUE INT
                          )
                    END

                  IF @INCLUSION_RULE_TYPE = 'GROUP'
                    BEGIN
                        INSERT INTO #temp_holder --Pick individual rule from a Group
                                    (INCLUSION_RULE)
                        SELECT RULE_NAME
                        FROM   HIERARCHY_RULES_DEFINITION
                        WHERE  RULE_FLOW_GROUP_NAME = @INCLUSION_RULE

                        SELECT @CNT_GRP = Count(*)
                        FROM   #temp_holder

                        IF ( @LEVEL_NAME = 'Product'
                              OR @LEVEL_NAME = 'NDC' )
                          BEGIN
                              ----------------INCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @INCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD_SWAP

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD_SWAP(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD
                                END

                              ----------------INCLUSION_CONDITION CHECK PART 1 ENDS HERE---------------------------------  
                              WHILE( @k <= @CNT_GRP ) --For each rule in a Group starts here
                                BEGIN
                                    SELECT @INCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                                  FROM   HIERARCHY_RULES_DEFINITION hd
                                                  WHERE  RULE_NAME = @INCLUSION_RULE
                                                         AND EXISTS (SELECT     1
                                                                     FROM       sys.foreign_key_columns fkc
                                                                     INNER JOIN sys.columns c ON fkc.parent_column_id = c.column_id
                                                                                             AND fkc.parent_object_id = c.object_id
                                                                     INNER JOIN sys.columns cref ON fkc.referenced_column_id = cref.column_id
                                                                                                AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE      Object_name(parent_object_id) = hd.TABLE_NAME
                                                                                AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT ITEM_MASTER_SID INTO ##TEMP_DYNAMIC_PROD FROM ITEM_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @INCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT    @formula = CASE
                                                                 WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select ITEM_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from ITEM_MASTER where '
                                                                                                                   + a.COLUMN_NAME
                                                                                                                   + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                                   + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                                 ELSE 'select ITEM_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from ITEM_MASTER where '
                                                                      + a.COLUMN_NAME + ' in (select '
                                                                      + b.MAPPING_COLUMN_NAME + ' from '
                                                                      + b.REFERENCE_TABLE_NAME + ' where '
                                                                      + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                               END
                                          FROM      HIERARCHY_RULES_DEFINITION a
                                          LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                                    AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE     a.RULE_NAME = @INCLUSION_RULE
                                                    AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC SP_EXECUTESQL
                                      @FORMULA

                                    ----------------INCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @INCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT ITEM_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT ITEM_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                DELETE FROM #TEMP_GLOBAL_CCP_PROD--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                               FROM   ##TEMP_OR_LOOP_SAVE T WHERE T.ITEM_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.ITEM_MASTER_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------INCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_CCP_PROD
                                    WHERE  NOT EXISTS (SELECT ITEM_MASTER_SID
                                                                   FROM   ##TEMP_DYNAMIC_PROD T WHERE T.ITEM_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.ITEM_MASTER_SID)

                                    ----------------INCLUSION_CONDITION CHECK PART 2 STARTS HERE---------------------------------
                                    --BREAK THE WHILE LOOP IF ANY CONDTION FAILS
                                    IF ( @@ROWCOUNT = 0
                                         AND @INCLUSION_CONDITION = 'AND' )
                                      BEGIN
                                          SET @VAR_AND_CHECK = 1

                                          IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                            DROP TABLE ##TEMP_DYNAMIC

                                          BREAK
                                      END

                                    ----------------INCLUSION_CONDITION CHECK PART 2 ENDS HERE---------------------------------
                                    IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                      DROP TABLE ##TEMP_DYNAMIC

                                    SET @K += 1
                                END--For each rule in a Rule Group ends here

                              ----------------INCLUSION_CONDITION CHECK PART 3 STARTS HERE---------------------------------
                              --SWAP IT BACK TO ORIGINAL STATE
                              IF ( @VAR_AND_CHECK = 1
                                   AND @INCLUSION_CONDITION = 'AND' )
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------INCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                        ELSE IF @LEVEL_NAME = 'Brand'
                          BEGIN
                              ----------------INCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @INCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD_SWAP

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD_SWAP(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD
                                END

                              ----------------INCLUSION_CONDITION CHECK PART 1 ENDS HERE--------------------------------- 
                              WHILE( @k <= @CNT_GRP ) --For each rule in a Group starts here
                                BEGIN
                                    SELECT @INCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                                  FROM   HIERARCHY_RULES_DEFINITION hd
                                                  WHERE  RULE_NAME = @INCLUSION_RULE
                                                         AND EXISTS (SELECT     1
                                                                     FROM       sys.foreign_key_columns fkc
                                                                     INNER JOIN sys.columns c ON fkc.parent_column_id = c.column_id
                                                                                             AND fkc.parent_object_id = c.object_id
                                                                     INNER JOIN sys.columns cref ON fkc.referenced_column_id = cref.column_id
                                                                                                AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE      Object_name(parent_object_id) = hd.TABLE_NAME
                                                                                AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT BRAND_MASTER_SID INTO ##TEMP_DYNAMIC_PROD FROM BRAND_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @INCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT    @formula = CASE
                                                                 WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select BRAND_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from BRAND_MASTER where '
                                                                                                                   + a.COLUMN_NAME
                                                                                                                   + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                                   + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                                 ELSE 'select BRAND_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from BRAND_MASTER where '
                                                                      + a.COLUMN_NAME + ' in (select '
                                                                      + b.MAPPING_COLUMN_NAME + ' from '
                                                                      + b.REFERENCE_TABLE_NAME + ' where '
                                                                      + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                               END
                                          FROM      HIERARCHY_RULES_DEFINITION a
                                          LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                                    AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE     a.RULE_NAME = @INCLUSION_RULE
                                                    AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC SP_EXECUTESQL
                                      @FORMULA

                                    ----------------INCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @INCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT BRAND_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT BRAND_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                DELETE FROM #TEMP_GLOBAL_CCP_PROD--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T WHERE T.VALUE=#TEMP_GLOBAL_CCP_PROD.BRAND_MASTER_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------INCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_CCP_PROD
                                    WHERE  NOT EXISTS (SELECT BRAND_MASTER_SID
                                                       FROM   ##TEMP_DYNAMIC_PROD T WHERE T.BRAND_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.BRAND_MASTER_SID)

                                    ----------------INCLUSION_CONDITION CHECK PART 2 STARTS HERE---------------------------------
                                    --BREAK THE WHILE LOOP IF ANY CONDTION FAILS
                                    IF ( @@ROWCOUNT = 0
                                         AND @INCLUSION_CONDITION = 'AND' )
                                      BEGIN
                                          SET @VAR_AND_CHECK = 1

                                          IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                            DROP TABLE ##TEMP_DYNAMIC

                                          BREAK
                                      END

                                    ----------------INCLUSION_CONDITION CHECK PART 2 ENDS HERE---------------------------------
                                    IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                      DROP TABLE ##TEMP_DYNAMIC

                                    SET @K += 1
                                END--For each rule in a Rule Group ends here

                              ----------------INCLUSION_CONDITION CHECK PART 3 STARTS HERE---------------------------------
                              --SWAP IT BACK TO ORIGINAL STATE
                              IF ( @VAR_AND_CHECK = 1
                                   AND @INCLUSION_CONDITION = 'AND' )
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------INCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                        ELSE IF @LEVEL_NAME = 'Therapeutic Class'
                          BEGIN
                              ----------------INCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @INCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD_SWAP

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD_SWAP(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD
                                END

                              ----------------INCLUSION_CONDITION CHECK PART 1 ENDS HERE---------------------------------   
                              WHILE( @k <= @CNT_GRP ) --For each rule in a Group starts here
                                BEGIN
                                    SELECT @INCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    SELECT    @formula = 'select THERAPEUTIC_CLASS INTO ##TEMP_DYNAMIC_PROD from ITEM_MASTER where '
                                                         + a.COLUMN_NAME
                                                         + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                         + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                    FROM      HIERARCHY_RULES_DEFINITION a
                                    LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                              AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE     a.RULE_NAME = @INCLUSION_RULE
                                              AND b.MAPPING_COLUMN_NAME IS NOT NULL

                                    EXEC SP_EXECUTESQL
                                      @FORMULA

                                    ----------------INCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @INCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT THERAPEUTIC_CLASS
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT THERAPEUTIC_CLASS
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                DELETE FROM #TEMP_GLOBAL_CCP_PROD--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T WHERE T.VALUE=#TEMP_GLOBAL_CCP_PROD.THERAPEUTIC_CLASS_HLPR_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------INCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_CCP_PROD
                                    WHERE  NOT EXISTS (SELECT THERAPEUTIC_CLASS
                                                                              FROM   ##TEMP_DYNAMIC_PROD T WHERE T.THERAPEUTIC_CLASS=#TEMP_GLOBAL_CCP_PROD.THERAPEUTIC_CLASS_HLPR_SID)

                                    ----------------INCLUSION_CONDITION CHECK PART 2 STARTS HERE---------------------------------
                                    --BREAK THE WHILE LOOP IF ANY CONDTION FAILS
                                    IF ( @@ROWCOUNT = 0
                                         AND @INCLUSION_CONDITION = 'AND' )
                                      BEGIN
                                          SET @VAR_AND_CHECK = 1

                                          IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                            DROP TABLE ##TEMP_DYNAMIC

                                          BREAK
                                      END

                                    ----------------INCLUSION_CONDITION CHECK PART 2 ENDS HERE---------------------------------
                                    IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                      DROP TABLE ##TEMP_DYNAMIC

                                    SET @K += 1
                                END--For each rule in a Rule Group ends here

                              ----------------INCLUSION_CONDITION CHECK PART 3 STARTS HERE---------------------------------
                              --SWAP IT BACK TO ORIGINAL STATE
                              IF ( @VAR_AND_CHECK = 1
                                   AND @INCLUSION_CONDITION = 'AND' )
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------INCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                        ELSE IF ( @LEVEL_NAME = 'GL Company'
                              OR @LEVEL_NAME = 'Company' )
                          BEGIN
                              ----------------INCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @INCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD_SWAP

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD_SWAP(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD
                                END

                              ----------------INCLUSION_CONDITION CHECK PART 1 ENDS HERE---------------------------------   
                              WHILE( @k <= @CNT_GRP )--For each rule in a Group starts here
                                BEGIN
                                    SELECT @INCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                                  FROM   HIERARCHY_RULES_DEFINITION hd
                                                  WHERE  RULE_NAME = @INCLUSION_RULE
                                                         AND EXISTS (SELECT     1
                                                                     FROM       sys.foreign_key_columns fkc
                                                                     INNER JOIN sys.columns c ON fkc.parent_column_id = c.column_id
                                                                                             AND fkc.parent_object_id = c.object_id
                                                                     INNER JOIN sys.columns cref ON fkc.referenced_column_id = cref.column_id
                                                                                                AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE      Object_name(parent_object_id) = hd.TABLE_NAME
                                                                                AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC_PROD FROM COMPANY_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @INCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT    @formula = CASE
                                                                 WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from COMPANY_MASTER where '
                                                                                                                   + a.COLUMN_NAME
                                                                                                                   + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                                   + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                                 ELSE 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from COMPANY_MASTER where '
                                                                      + a.COLUMN_NAME + ' in (select '
                                                                      + b.MAPPING_COLUMN_NAME + ' from '
                                                                      + b.REFERENCE_TABLE_NAME + ' where '
                                                                      + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                               END
                                          FROM      HIERARCHY_RULES_DEFINITION a
                                          LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                                    AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE     a.RULE_NAME = @INCLUSION_RULE
                                                    AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC SP_EXECUTESQL
                                      @FORMULA

                                    ----------------INCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @INCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT COMPANY_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT COMPANY_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                DELETE FROM #TEMP_GLOBAL_CCP_PROD--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                FROM   #TEMP_OR_LOOP_SAVE T WHERE T.VALUE=#TEMP_GLOBAL_CCP_PROD.GL_COMP_COMPANY_MASTER_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------INCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_CCP_PROD
                                    WHERE  NOT EXISTS (SELECT COMPANY_MASTER_SID
                                                                              FROM   ##TEMP_DYNAMIC_PROD T WHERE T.COMPANY_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.GL_COMP_COMPANY_MASTER_SID)

                                    ----------------INCLUSION_CONDITION CHECK PART 2 STARTS HERE---------------------------------
                                    --BREAK THE WHILE LOOP IF ANY CONDTION FAILS
                                    IF ( @@ROWCOUNT = 0
                                         AND @INCLUSION_CONDITION = 'AND' )
                                      BEGIN
                                          SET @VAR_AND_CHECK = 1

                                          IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                            DROP TABLE ##TEMP_DYNAMIC

                                          BREAK
                                      END

                                    ----------------INCLUSION_CONDITION CHECK PART 2 ENDS HERE---------------------------------
                                    IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                      DROP TABLE ##TEMP_DYNAMIC

                                    SET @K += 1
                                END--For each rule in a Rule Group ends here

                              ----------------INCLUSION_CONDITION CHECK PART 3 STARTS HERE---------------------------------
                              --SWAP IT BACK TO ORIGINAL STATE
                              IF ( @VAR_AND_CHECK = 1
                                   AND @INCLUSION_CONDITION = 'AND' )
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------INCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                    END --End of Inclusion Rule Type
                  SET @FORMULA = ''

                  TRUNCATE TABLE #temp_holder

                  IF Object_id('tempdb..#TEMP_OR_LOOP_SAVE') IS NOT NULL
                    TRUNCATE TABLE #TEMP_OR_LOOP_SAVE

                  SET @k = 1

                  IF @EXCLUSION_RULE_TYPE = 'GROUP'
                    BEGIN
                        INSERT INTO #temp_holder --Pick individual rule from a Group
                                    (INCLUSION_RULE)
                        SELECT RULE_NAME
                        FROM   HIERARCHY_RULES_DEFINITION
                        WHERE  RULE_FLOW_GROUP_NAME = @EXCLUSION_RULE

                        SELECT @CNT_GRP = Count(*)
                        FROM   #temp_holder

                        IF ( @LEVEL_NAME = 'Product'
                              OR @LEVEL_NAME = 'NDC' )
                          BEGIN
                              ----------------EXCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @EXCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD_SWAP

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD_SWAP(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD
                                END

                              ----------------EXCLUSION_CONDITION CHECK PART 1 ENDS HERE--------------------------------- 
                              WHILE( @k <= @CNT_GRP )--For each rule in a Group starts here
                                BEGIN
                                    SELECT @EXCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                                  FROM   HIERARCHY_RULES_DEFINITION hd
                                                  WHERE  RULE_NAME = @EXCLUSION_RULE
                                                         AND EXISTS (SELECT     1
                                                                     FROM       sys.foreign_key_columns fkc
                                                                     INNER JOIN sys.columns c ON fkc.parent_column_id = c.column_id
                                                                                             AND fkc.parent_object_id = c.object_id
                                                                     INNER JOIN sys.columns cref ON fkc.referenced_column_id = cref.column_id
                                                                                                AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE      Object_name(parent_object_id) = hd.TABLE_NAME
                                                                                AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT ITEM_MASTER_SID INTO ##TEMP_DYNAMIC_PROD FROM ITEM_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @EXCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT    @formula = CASE
                                                                 WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select ITEM_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from ITEM_MASTER where '
                                                                                                                   + a.COLUMN_NAME
                                                                                                                   + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                                   + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                                 ELSE 'select ITEM_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from ITEM_MASTER where '
                                                                      + a.COLUMN_NAME + ' in (select '
                                                                      + b.MAPPING_COLUMN_NAME + ' from '
                                                                      + b.REFERENCE_TABLE_NAME + ' where '
                                                                      + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                               END
                                          FROM      HIERARCHY_RULES_DEFINITION a
                                          LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                                    AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE     a.RULE_NAME = @EXCLUSION_RULE
                                                    AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC SP_EXECUTESQL
                                      @FORMULA

                                    ----------------EXCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @EXCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT ITEM_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT ITEM_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                DELETE FROM #TEMP_GLOBAL_CCP_PROD--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                               FROM   ##TEMP_OR_LOOP_SAVE T WHERE T.ITEM_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.ITEM_MASTER_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------EXCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_CCP_PROD
                                    WHERE  NOT EXISTS (SELECT ITEM_MASTER_SID
                                                                   FROM   ##TEMP_DYNAMIC_PROD T WHERE T.ITEM_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.ITEM_MASTER_SID)

                                    ----------------EXCLUSION_CONDITION CHECK PART 2 STARTS HERE---------------------------------
                                    --BREAK THE WHILE LOOP IF ANY CONDTION FAILS
                                    IF ( @@ROWCOUNT = 0
                                         AND @EXCLUSION_CONDITION = 'AND' )
                                      BEGIN
                                          SET @VAR_AND_CHECK = 1

                                          IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                            DROP TABLE ##TEMP_DYNAMIC

                                          BREAK
                                      END

                                    ----------------EXCLUSION_CONDITION CHECK PART 2 ENDS HERE---------------------------------
                                    IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                      DROP TABLE ##TEMP_DYNAMIC

                                    SET @K += 1
                                END--For each rule in a Rule Group ends here

                              ----------------EXCLUSION_CONDITION CHECK PART 3 STARTS HERE---------------------------------
                              --SWAP IT BACK TO ORIGINAL STATE
                              IF ( @VAR_AND_CHECK = 1
                                   AND @EXCLUSION_CONDITION = 'AND' )
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------EXCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                        ELSE IF @LEVEL_NAME = 'Brand'
                          BEGIN
                              ----------------EXCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @EXCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD_SWAP

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD_SWAP(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD
                                END

                              ----------------EXCLUSION_CONDITION CHECK PART 1 ENDS HERE--------------------------------- 
                              WHILE( @k <= @CNT_GRP )--For each rule in a Group starts here
                                BEGIN
                                    SELECT @EXCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                                  FROM   HIERARCHY_RULES_DEFINITION hd
                                                  WHERE  RULE_NAME = @EXCLUSION_RULE
                                                         AND EXISTS (SELECT     1
                                                                     FROM       sys.foreign_key_columns fkc
                                                                     INNER JOIN sys.columns c ON fkc.parent_column_id = c.column_id
                                                                                             AND fkc.parent_object_id = c.object_id
                                                                     INNER JOIN sys.columns cref ON fkc.referenced_column_id = cref.column_id
                                                                                                AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE      Object_name(parent_object_id) = hd.TABLE_NAME
                                                                                AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT BRAND_MASTER_SID INTO ##TEMP_DYNAMIC_PROD FROM BRAND_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @EXCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT    @formula = CASE
                                                                 WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select BRAND_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from BRAND_MASTER where '
                                                                                                                   + a.COLUMN_NAME
                                                                                                                   + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                                   + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                                 ELSE 'select BRAND_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from BRAND_MASTER where '
                                                                      + a.COLUMN_NAME + ' in (select '
                                                                      + b.MAPPING_COLUMN_NAME + ' from '
                                                                      + b.REFERENCE_TABLE_NAME + ' where '
                                                                      + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                               END
                                          FROM      HIERARCHY_RULES_DEFINITION a
                                          LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                                    AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE     a.RULE_NAME = @EXCLUSION_RULE
                                                    AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC SP_EXECUTESQL
                                      @FORMULA

                                    ----------------EXCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @EXCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT BRAND_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT BRAND_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                DELETE FROM #TEMP_GLOBAL_CCP_PROD--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T WHERE T.VALUE=#TEMP_GLOBAL_CCP_PROD.BRAND_MASTER_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------EXCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_CCP_PROD
                                    WHERE  NOT EXISTS (SELECT BRAND_MASTER_SID
                                                                    FROM   ##TEMP_DYNAMIC_PROD T WHERE T.BRAND_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.BRAND_MASTER_SID)

                                    ----------------EXCLUSION_CONDITION CHECK PART 2 STARTS HERE---------------------------------
                                    --BREAK THE WHILE LOOP IF ANY CONDTION FAILS
                                    IF ( @@ROWCOUNT = 0
                                         AND @EXCLUSION_CONDITION = 'AND' )
                                      BEGIN
                                          SET @VAR_AND_CHECK = 1

                                          IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                            DROP TABLE ##TEMP_DYNAMIC

                                          BREAK
                                      END

                                    ----------------EXCLUSION_CONDITION CHECK PART 2 ENDS HERE---------------------------------
                                    IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                      DROP TABLE ##TEMP_DYNAMIC

                                    SET @K += 1
                                END--For each rule in a Rule Group ends here

                              ----------------EXCLUSION_CONDITION CHECK PART 3 STARTS HERE---------------------------------
                              --SWAP IT BACK TO ORIGINAL STATE
                              IF ( @VAR_AND_CHECK = 1
                                   AND @EXCLUSION_CONDITION = 'AND' )
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------EXCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                        ELSE IF @LEVEL_NAME = 'Therapeutic Class'
                          BEGIN
                              ----------------EXCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @EXCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD_SWAP

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD_SWAP(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD
                                END

                              ----------------EXCLUSION_CONDITION CHECK PART 1 ENDS HERE--------------------------------- 
                              WHILE( @k <= @CNT_GRP )--For each rule in a Group starts here
                                BEGIN
                                    SELECT @EXCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    SELECT    @formula = 'select THERAPEUTIC_CLASS INTO ##TEMP_DYNAMIC_PROD from ITEM_MASTER where '
                                                         + a.COLUMN_NAME
                                                         + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                         + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                    FROM      HIERARCHY_RULES_DEFINITION a
                                    LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                              AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE     a.RULE_NAME = @EXCLUSION_RULE
                                              AND b.MAPPING_COLUMN_NAME IS NOT NULL

                                    EXEC SP_EXECUTESQL
                                      @FORMULA

                                    ----------------EXCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @EXCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT THERAPEUTIC_CLASS
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT THERAPEUTIC_CLASS
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                DELETE FROM #TEMP_GLOBAL_CCP_PROD--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T WHERE T.VALUE=#TEMP_GLOBAL_CCP_PROD.THERAPEUTIC_CLASS_HLPR_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------EXCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_CCP_PROD
                                    WHERE  NOT EXISTS (SELECT THERAPEUTIC_CLASS
                                                                              FROM   ##TEMP_DYNAMIC_PROD T WHERE T.THERAPEUTIC_CLASS=#TEMP_GLOBAL_CCP_PROD.THERAPEUTIC_CLASS_HLPR_SID)

                                    ----------------EXCLUSION_CONDITION CHECK PART 2 STARTS HERE---------------------------------
                                    --BREAK THE WHILE LOOP IF ANY CONDTION FAILS
                                    IF ( @@ROWCOUNT = 0
                                         AND @EXCLUSION_CONDITION = 'AND' )
                                      BEGIN
                                          SET @VAR_AND_CHECK = 1

                                          IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                            DROP TABLE ##TEMP_DYNAMIC

                                          BREAK
                                      END

                                    ----------------EXCLUSION_CONDITION CHECK PART 2 ENDS HERE---------------------------------
                                    IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                      DROP TABLE ##TEMP_DYNAMIC

                                    SET @K += 1
                                END--For each rule in a Rule Group ends here

                              ----------------EXCLUSION_CONDITION CHECK PART 3 STARTS HERE---------------------------------
                              --SWAP IT BACK TO ORIGINAL STATE
                              IF ( @VAR_AND_CHECK = 1
                                   AND @EXCLUSION_CONDITION = 'AND' )
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------EXCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                        ELSE IF ( @LEVEL_NAME = 'GL Company'
                              OR @LEVEL_NAME = 'Company' )
                          BEGIN
                              ----------------EXCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @EXCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD_SWAP

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD_SWAP(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD
                                END

                              ----------------EXCLUSION_CONDITION CHECK PART 1 ENDS HERE--------------------------------- 
                              WHILE( @k <= @CNT_GRP )--For each rule in a Group starts here
                                BEGIN
                                    SELECT @EXCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                                  FROM   HIERARCHY_RULES_DEFINITION hd
                                                  WHERE  RULE_NAME = @EXCLUSION_RULE
                                                         AND EXISTS (SELECT     1
                                                                     FROM       sys.foreign_key_columns fkc
                                                                     INNER JOIN sys.columns c ON fkc.parent_column_id = c.column_id
                                                                                             AND fkc.parent_object_id = c.object_id
                                                                     INNER JOIN sys.columns cref ON fkc.referenced_column_id = cref.column_id
                                                                                                AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE      Object_name(parent_object_id) = hd.TABLE_NAME
                                                                                AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC_PROD FROM COMPANY_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @EXCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT    @formula = CASE
                                                                 WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from COMPANY_MASTER where '
                                                                                                                   + a.COLUMN_NAME
                                                                                                                   + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                                   + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                                 ELSE 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC_PROD from COMPANY_MASTER where '
                                                                      + a.COLUMN_NAME + ' in (select '
                                                                      + b.MAPPING_COLUMN_NAME + ' from '
                                                                      + b.REFERENCE_TABLE_NAME + ' where '
                                                                      + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                               END
                                          FROM      HIERARCHY_RULES_DEFINITION a
                                          LEFT JOIN vw_helper_list b ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                                    AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE     a.RULE_NAME = @EXCLUSION_RULE
                                                    AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC SP_EXECUTESQL
                                      @FORMULA

                                    ----------------EXCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @EXCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT COMPANY_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT COMPANY_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC_PROD

                                                IF Object_id('tempdb..##TEMP_DYNAMIC_PROD') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC_PROD

                                                DELETE FROM #TEMP_GLOBAL_CCP_PROD--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T WHERE T.VALUE=#TEMP_GLOBAL_CCP_PROD.GL_COMP_COMPANY_MASTER_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------EXCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_CCP_PROD
                                    WHERE  NOT EXISTS (SELECT COMPANY_MASTER_SID
                                                                              FROM   ##TEMP_DYNAMIC_PROD T WHERE T.COMPANY_MASTER_SID=#TEMP_GLOBAL_CCP_PROD.GL_COMP_COMPANY_MASTER_SID)

                                    ----------------EXCLUSION_CONDITION CHECK PART 2 STARTS HERE---------------------------------
                                    --BREAK THE WHILE LOOP IF ANY CONDTION FAILS
                                    IF ( @@ROWCOUNT = 0
                                         AND @EXCLUSION_CONDITION = 'AND' )
                                      BEGIN
                                          SET @VAR_AND_CHECK = 1

                                          IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                            DROP TABLE ##TEMP_DYNAMIC

                                          BREAK
                                      END

                                    ----------------EXCLUSION_CONDITION CHECK PART 2 ENDS HERE---------------------------------
                                    IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                      DROP TABLE ##TEMP_DYNAMIC

                                    SET @K += 1
                                END--For each rule in a Rule Group ends here

                              ----------------EXCLUSION_CONDITION CHECK PART 3 STARTS HERE---------------------------------
                              --SWAP IT BACK TO ORIGINAL STATE
                              IF ( @VAR_AND_CHECK = 1
                                   AND @EXCLUSION_CONDITION = 'AND' )
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_CCP_PROD

                                    INSERT INTO #TEMP_GLOBAL_CCP_PROD(ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT ITEM_MASTER_SID,GL_COMP_COMPANY_MASTER_SID,BRAND_MASTER_SID,THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_CCP_PROD_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------EXCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                    END --End of Exclusion Rule Type
                  SET @FORMULA = ''

                  TRUNCATE TABLE #temp_holder

                  SET @k = 1

                  IF @LEVEL_NO = 1
                     AND @LEVEL_VALUE_REFERENCE = 'linked'
                    BEGIN ;
                        WITH mycte
                             AS (SELECT PARENT_NODE,
                                        RELATIONSHIP_LEVEL_VALUES,
                                        HIERARCHY_NO,
                                        LEVEL_NAME,
                                        RELATIONSHIP_BUILDER_SID,
                                        HIERARCHY_LEVEL_DEFINITION_SID,
                                        LEVEL_NO
                                 FROM   RELATIONSHIP_LEVEL_DEFINITION
                                 WHERE  RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                        AND level_no = 1)
                        INSERT INTO #temp_rld_result
                                    (ITEM_MASTER_SID,
                                     GL_COMP_COMPANY_MASTER_SID,
                                     BRAND_MASTER_SID,
                                     THERAPEUTIC_CLASS_HLPR_SID,
                                     RELATIONSHIP_BUILDER_SID,
                                     HIERARCHY_LEVEL_DEFINITION_SID,
                                     LEVEL_NO,
                                     LEVEL_NAME,
                                     LEVEL_VALUES,
                                     PARENT_NODE,
                                     HIERARCHY_NO)
                        SELECT ITEM_MASTER_SID,
                               GL_COMP_COMPANY_MASTER_SID,
                               BRAND_MASTER_SID,
                               THERAPEUTIC_CLASS_HLPR_SID,
                               B.RELATIONSHIP_BUILDER_SID,
                               B.HIERARCHY_LEVEL_DEFINITION_SID,
                               B.LEVEL_NO,
                               B.LEVEL_NAME,
                               CASE
                                 WHEN @LEVEL_NAME = 'Product'
                                       OR @LEVEL_NAME = 'NDC' THEN ITEM_MASTER_SID
                                 WHEN @LEVEL_NAME = 'Brand' THEN BRAND_MASTER_SID
                                 WHEN @LEVEL_NAME = 'Therapeutic Class' THEN THERAPEUTIC_CLASS_HLPR_SID
                                 WHEN @LEVEL_NAME = 'COMPANY'
                                       OR @LEVEL_NAME = 'GL Company' THEN GL_COMP_COMPANY_MASTER_SID
                               END AS LEVEL_VALUES,
                               B.PARENT_NODE,
                               B.HIERARCHY_NO
                        FROM   #TEMP_GLOBAL_CCP_PROD a
                        JOIN   mycte b -- ONLY ONE ROOT LEVEL CHECK HAPPENS HERE
                        ON b.RELATIONSHIP_LEVEL_VALUES = CASE
                                                           WHEN @LEVEL_NAME = 'Product'
                                                                 OR @LEVEL_NAME = 'NDC' THEN ITEM_MASTER_SID
                                                           WHEN @LEVEL_NAME = 'Brand' THEN BRAND_MASTER_SID
                                                           WHEN @LEVEL_NAME = 'Therapeutic Class' THEN THERAPEUTIC_CLASS_HLPR_SID
                                                           WHEN @LEVEL_NAME = 'COMPANY'
                                                                 OR @LEVEL_NAME = 'GL Company' THEN GL_COMP_COMPANY_MASTER_SID
                                                         END

                        IF @@ROWCOUNT = 0
                          BREAK
                    END
                  ELSE IF @LEVEL_NO >= 1
                     AND @LEVEL_VALUE_REFERENCE = 'LINKED'
                    BEGIN
                        IF NOT EXISTS (SELECT 1
                                       FROM   #temp_rld_result
                                       WHERE  LEVEL_NO = @LEVEL_NO - 1) --FIRST LINKED LEVEL AFTER USER_DEFINED LEVELS
                          BEGIN ;
                              WITH MYCTE
                                   AS (SELECT TOP 1 Cast(LEVEL_NO AS VARCHAR(2)) + '~'
                                                    + Cast(RELATIONSHIP_LEVEL_VALUES AS VARCHAR(100)) AS parent_node
                                       FROM   RELATIONSHIP_LEVEL_DEFINITION
                                       WHERE  RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                              AND LEVEL_NO = @LEVEL_NO - 1
											  ORDER BY RELATIONSHIP_BUILDER_SID,LEVEL_NO) --Get previous level parent node value.
                              SELECT @hier_no = Cast(Max(Cast(Reverse(Substring(Stuff(Reverse(rld.hierarchy_no), 1, 1, ''), 1, Charindex('.', Stuff(Reverse(rld.hierarchy_no), 1, 1, '')) - 1))AS INT)) AS VARCHAR(10))
                              FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                              JOIN   mycte b ON 1 = 1
                              WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                     AND rld.LEVEL_NO = @LEVEL_NO
                                     AND rld.PARENT_NODE = b.parent_node;

                              WITH MYCTE
                                   AS (SELECT TOP 1 Cast(LEVEL_NO AS VARCHAR(2)) + '~'
                                                    + Cast(RELATIONSHIP_LEVEL_VALUES AS VARCHAR(100)) AS parent_node
                                       FROM   RELATIONSHIP_LEVEL_DEFINITION
                                       WHERE  RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                              AND LEVEL_NO = @LEVEL_NO - 1
											  ORDER BY RELATIONSHIP_BUILDER_SID,LEVEL_NO) --Get previous level parent node value.
                              SELECT @max_hier = rld.hierarchy_no,
                                     @len_hier = Len(Reverse(Substring(Reverse(Stuff(rld.hierarchy_no, Len(rld.hierarchy_no), 1, '')), 1, Charindex('.', Reverse(Stuff(rld.hierarchy_no, Len(rld.hierarchy_no), 1, ''))) - 1)))
                              FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                              JOIN   mycte b ON 1 = 1
                              WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                     AND rld.LEVEL_NO = @LEVEL_NO
                                     AND rld.PARENT_NODE = b.parent_node
                                     AND rld.HIERARCHY_NO LIKE '%.' + @hier_no + '.';

                              WITH mycte
                                   AS (SELECT TOP 1 Cast(LEVEL_NO AS VARCHAR(2)) + '~'
                                                    + Cast(RELATIONSHIP_LEVEL_VALUES AS VARCHAR(100)) AS parent_node
                                       FROM   RELATIONSHIP_LEVEL_DEFINITION
                                       WHERE  RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                              AND LEVEL_NO = @LEVEL_NO - 1
											  ORDER BY RELATIONSHIP_BUILDER_SID,LEVEL_NO) --Get previous level parent node value.
                              INSERT INTO #temp_rld_result
                                          (ITEM_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID,
                                           RELATIONSHIP_BUILDER_SID,
                                           HIERARCHY_LEVEL_DEFINITION_SID,
                                           LEVEL_NO,
                                           LEVEL_NAME,
                                           LEVEL_VALUES,
                                           PARENT_NODE,
                                           HIERARCHY_NO)
                              SELECT      ITEM_MASTER_SID,
                                          GL_COMP_COMPANY_MASTER_SID,
                                          BRAND_MASTER_SID,
                                          THERAPEUTIC_CLASS_HLPR_SID,
                                          @RELATIONSHIP_BUILDER_SID,
                                          @HIERARCHY_LEVEL_DEFINITION_SID,
                                          @LEVEL_NO,
                                          @LEVEL_NAME,
                                          CASE
                                            WHEN @LEVEL_NAME = 'Product'
                                                  OR @LEVEL_NAME = 'NDC' THEN ITEM_MASTER_SID
                                            WHEN @LEVEL_NAME = 'Brand' THEN BRAND_MASTER_SID
                                            WHEN @LEVEL_NAME = 'Therapeutic Class' THEN THERAPEUTIC_CLASS_HLPR_SID
                                            WHEN @LEVEL_NAME = 'COMPANY'
                                                  OR @LEVEL_NAME = 'GL Company' THEN GL_COMP_COMPANY_MASTER_SID
                                          END AS LEVEL_VALUES,--Level Value formation
                                          B.PARENT_NODE,--from cte
                                          --CONVERT(VARCHAR(10), @RELATIONSHIP_BUILDER_SID)
                                          --+ '-' + Replicate('1.', (@LEVEL_NO-1)) + CONVERT(VARCHAR(10), DENSE_RANK() OVER(PARTITION BY @RELATIONSHIP_BUILDER_SID, @LEVEL_NO, b.parent_node ORDER BY CASE WHEN @LEVEL_NAME = 'Product' OR @LEVEL_NAME = 'NDC' THEN ITEM_MASTER_SID WHEN @LEVEL_NAME = 'Brand' THEN BRAND_MASTER_SID WHEN @LEVEL_NAME = 'Therapeutic Class' THEN THERAPEUTIC_CLASS_HLPR_SID WHEN @LEVEL_NAME = 'COMPANY' OR @LEVEL_NAME = 'GL Company' THEN GL_COMP_COMPANY_MASTER_SID END))
                                          --+ '.' AS HIERARCHY_NO
                                          CASE
                                            WHEN op_rld.HIERARCHY_NO IS NOT NULL THEN op_rld.HIERARCHY_NO --exist in RLD for VALUES + LEVEL_NO + PARENT_NODE + Relationship_Builder_Sid
                                            ELSE
                                              CASE
                                                WHEN op_rld2.max_hier_no IS NOT NULL THEN Stuff(@max_hier, Len(@max_hier) - @len_hier, @len_hier, Cast(Reverse(Substring(Reverse(Stuff(@max_hier, Len(@max_hier), 1, '')), 1, Charindex('.', Reverse(Stuff(@max_hier, Len(@max_hier), 1, ''))) - 1)) AS INT)
                                                                                                                                                  + DENSE_RANK() OVER(PARTITION BY @RELATIONSHIP_BUILDER_SID, @LEVEL_NO, b.parent_node ORDER BY CASE WHEN @LEVEL_NAME = 'Product' OR @LEVEL_NAME = 'NDC' THEN A.ITEM_MASTER_SID WHEN @LEVEL_NAME = 'Brand' THEN A.BRAND_MASTER_SID WHEN @LEVEL_NAME = 'Therapeutic Class' THEN A.THERAPEUTIC_CLASS_HLPR_SID WHEN @LEVEL_NAME = 'COMPANY' OR @LEVEL_NAME = 'GL Company' THEN A.GL_COMP_COMPANY_MASTER_SID END))--exist in RLD for LEVEL_NO + PARENT_NODE
                                                ELSE CONVERT(VARCHAR(10), @RELATIONSHIP_BUILDER_SID)
                                                     + '-' + Replicate('1.', (@LEVEL_NO-1))
                                                     + Cast(DENSE_RANK() OVER(PARTITION BY @RELATIONSHIP_BUILDER_SID, @LEVEL_NO, b.parent_node ORDER BY CASE WHEN @LEVEL_NAME = 'Product' OR @LEVEL_NAME = 'NDC' THEN A.ITEM_MASTER_SID WHEN @LEVEL_NAME = 'Brand' THEN A.BRAND_MASTER_SID WHEN @LEVEL_NAME = 'Therapeutic Class' THEN A.THERAPEUTIC_CLASS_HLPR_SID WHEN @LEVEL_NAME = 'COMPANY' OR @LEVEL_NAME = 'GL Company' THEN A.GL_COMP_COMPANY_MASTER_SID END ) AS VARCHAR(10)) + '.'
                                              END
                                          END AS HIERARCHY_NO
                              FROM        #TEMP_GLOBAL_CCP_PROD a
                              JOIN        mycte B ON 1 = 1
                              OUTER apply (SELECT rld.HIERARCHY_NO --for the matched RLD comb with formed value comb pull the existing hierarchy no itself
                                           FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                           WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                  AND rld.LEVEL_NO = @LEVEL_NO
                                                  AND rld.PARENT_NODE = b.parent_node
                                                  AND rld.RELATIONSHIP_LEVEL_VALUES = CASE
                                                                                        WHEN @LEVEL_NAME = 'Product'
                                                                                              OR @LEVEL_NAME = 'NDC' THEN A.ITEM_MASTER_SID
                                                                                        WHEN @LEVEL_NAME = 'Brand' THEN A.BRAND_MASTER_SID
                                                                                        WHEN @LEVEL_NAME = 'Therapeutic Class' THEN A.THERAPEUTIC_CLASS_HLPR_SID
                                                                                        WHEN @LEVEL_NAME = 'COMPANY'
                                                                                              OR @LEVEL_NAME = 'GL Company' THEN A.GL_COMP_COMPANY_MASTER_SID
                                                                                      END) op_rld
                              OUTER apply(SELECT Max(hierarchy_no) AS max_hier_no
                                          FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                          WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                 AND rld.LEVEL_NO = @LEVEL_NO
                                                 AND rld.PARENT_NODE = b.parent_node) op_rld2

                              SET @max_hier=''
                              SET @hier_no=''
                              SET @len_hier=0
                          END
                        ELSE --FOR ALL OTHER LINKED LEVELS IN HIERARCHY
                          BEGIN ;
                              WITH MYCTE
                                   AS (SELECT Cast(LEVEL_NO AS VARCHAR(2)) + '~'
                                              + Cast(LEVEL_VALUES AS VARCHAR(100)) AS parent_node,
                                              LEVEL_NO,
                                              LEVEL_NAME,
                                              LEVEL_VALUES,
                                              GL_COMP_COMPANY_MASTER_SID,
                                              BRAND_MASTER_SID,
                                              THERAPEUTIC_CLASS_HLPR_SID,
                                              ITEM_MASTER_SID,
                                              HIERARCHY_NO
                                       FROM   #temp_rld_result
                                       WHERE  LEVEL_NO = @LEVEL_NO - 1) --Get previous level parent node value.
                              INSERT INTO #TEMP_FIX
                                          (PARENT_NODE)
                              SELECT DISTINCT PARENT_NODE
                              FROM   MYCTE;

                              SELECT @Parent_no_Fix_count = Count(1)
                              FROM   #TEMP_FIX

                              SET @F=1

                              WHILE ( @Parent_no_Fix_count >= @F )
                                BEGIN
                                    SELECT @PARENT_NO_FIX = PARENT_NODE
                                    FROM   #TEMP_FIX
                                    WHERE  ID = @F;

                                ;
                                    WITH MYCTE
                                         AS (SELECT Cast(LEVEL_NO AS VARCHAR(2)) + '~'
                                                    + Cast(LEVEL_VALUES AS VARCHAR(100)) AS parent_node,
                                                    LEVEL_NO,
                                                    LEVEL_NAME,
                                                    LEVEL_VALUES,
                                                    GL_COMP_COMPANY_MASTER_SID,
                                                    BRAND_MASTER_SID,
                                                    THERAPEUTIC_CLASS_HLPR_SID,
                                                    ITEM_MASTER_SID,
                                                    HIERARCHY_NO
                                             FROM   #temp_rld_result
                                             WHERE  LEVEL_NO = @LEVEL_NO - 1) --Get previous level parent node value.
                                    SELECT @hier_no = Cast(Max(Cast(Reverse(Substring(Stuff(Reverse(rld.hierarchy_no), 1, 1, ''), 1, Charindex('.', Stuff(Reverse(rld.hierarchy_no), 1, 1, '')) - 1))AS INT)) AS VARCHAR(10))
                                    FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                    JOIN   mycte b ON 1 = 1
                                    WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                           AND rld.LEVEL_NO = @LEVEL_NO
                                           AND rld.PARENT_NODE = b.parent_node
                                           AND rld.PARENT_NODE = @PARENT_NO_FIX;

                                    WITH MYCTE
                                         AS (SELECT Cast(LEVEL_NO AS VARCHAR(2)) + '~'
                                                    + Cast(LEVEL_VALUES AS VARCHAR(100)) AS parent_node,
                                                    LEVEL_NO,
                                                    LEVEL_NAME,
                                                    LEVEL_VALUES,
                                                    GL_COMP_COMPANY_MASTER_SID,
                                                    BRAND_MASTER_SID,
                                                    THERAPEUTIC_CLASS_HLPR_SID,
                                                    ITEM_MASTER_SID,
                                                    HIERARCHY_NO
                                             FROM   #temp_rld_result
                                             WHERE  LEVEL_NO = @LEVEL_NO - 1) --Get previous level parent node value.
                                    SELECT @max_hier = rld.hierarchy_no,
                                           @len_hier = Len(Reverse(Substring(Reverse(Stuff(rld.hierarchy_no, Len(rld.hierarchy_no), 1, '')), 1, Charindex('.', Reverse(Stuff(rld.hierarchy_no, Len(rld.hierarchy_no), 1, ''))) - 1)))
                                    FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                    JOIN   mycte b ON 1 = 1
                                    WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                           AND rld.LEVEL_NO = @LEVEL_NO
                                           AND rld.PARENT_NODE = b.parent_node
                                           AND rld.HIERARCHY_NO LIKE '%.' + @hier_no + '.'
                                           AND rld.PARENT_NODE = @PARENT_NO_FIX;

                                    WITH MYCTE
                                         AS (SELECT Cast(LEVEL_NO AS VARCHAR(2)) + '~'
                                                    + Cast(LEVEL_VALUES AS VARCHAR(100)) AS parent_node,
                                                    LEVEL_NO,
                                                    LEVEL_NAME,
                                                    LEVEL_VALUES,
                                                    GL_COMP_COMPANY_MASTER_SID,
                                                    BRAND_MASTER_SID,
                                                    THERAPEUTIC_CLASS_HLPR_SID,
                                                    ITEM_MASTER_SID,
                                                    HIERARCHY_NO
                                             FROM   #temp_rld_result
                                             WHERE  LEVEL_NO = @LEVEL_NO - 1
                                                    AND Cast(LEVEL_NO AS VARCHAR(2)) + '~'
                                                        + Cast(LEVEL_VALUES AS VARCHAR(100)) = @PARENT_NO_FIX) --Get previous level parent node value.
                                    INSERT INTO #temp_rld_result
                                                (ITEM_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID,
                                                 RELATIONSHIP_BUILDER_SID,
                                                 HIERARCHY_LEVEL_DEFINITION_SID,
                                                 LEVEL_NO,
                                                 LEVEL_NAME,
                                                 LEVEL_VALUES,
                                                 PARENT_NODE,
                                                 HIERARCHY_NO)
                                    SELECT      A.ITEM_MASTER_SID,
                                                A.GL_COMP_COMPANY_MASTER_SID,
                                                A.BRAND_MASTER_SID,
                                                A.THERAPEUTIC_CLASS_HLPR_SID,
                                                @RELATIONSHIP_BUILDER_SID,
                                                @HIERARCHY_LEVEL_DEFINITION_SID,
                                                @LEVEL_NO,
                                                @LEVEL_NAME,
                                                CASE
                                                  WHEN @LEVEL_NAME = 'Product'
                                                        OR @LEVEL_NAME = 'NDC' THEN A.ITEM_MASTER_SID
                                                  WHEN @LEVEL_NAME = 'Brand' THEN A.BRAND_MASTER_SID
                                                  WHEN @LEVEL_NAME = 'Therapeutic Class' THEN A.THERAPEUTIC_CLASS_HLPR_SID
                                                  WHEN @LEVEL_NAME = 'COMPANY'
                                                        OR @LEVEL_NAME = 'GL Company' THEN A.GL_COMP_COMPANY_MASTER_SID
                                                END AS LEVEL_VALUES,--level values formation 
                                                B.PARENT_NODE,--from cte
                                                CASE
                                                  WHEN op_rld.HIERARCHY_NO IS NOT NULL THEN op_rld.HIERARCHY_NO --exist in RLD for VALUES + LEVEL_NO + PARENT_NODE + Relationship_Builder_Sid
                                                  ELSE
                                                    CASE
                                                      WHEN op_rld2.max_hier_no IS NOT NULL THEN Stuff(@max_hier, Len(@max_hier) - @len_hier, @len_hier, Cast(Reverse(Substring(Reverse(Stuff(@max_hier, Len(@max_hier), 1, '')), 1, Charindex('.', Reverse(Stuff(@max_hier, Len(@max_hier), 1, ''))) - 1)) AS INT)
                                                                                                                                                        + DENSE_RANK() OVER(PARTITION BY @RELATIONSHIP_BUILDER_SID, @LEVEL_NO, b.parent_node ORDER BY CASE WHEN @LEVEL_NAME = 'Product' OR @LEVEL_NAME = 'NDC' THEN A.ITEM_MASTER_SID WHEN @LEVEL_NAME = 'Brand' THEN A.BRAND_MASTER_SID WHEN @LEVEL_NAME = 'Therapeutic Class' THEN A.THERAPEUTIC_CLASS_HLPR_SID WHEN @LEVEL_NAME = 'COMPANY' OR @LEVEL_NAME = 'GL Company' THEN A.GL_COMP_COMPANY_MASTER_SID END))--exist in RLD for LEVEL_NO + PARENT_NODE
                                                      ELSE B.HIERARCHY_NO + Cast(DENSE_RANK() OVER(PARTITION BY @RELATIONSHIP_BUILDER_SID, @LEVEL_NO, b.parent_node ORDER BY CASE WHEN @LEVEL_NAME = 'Product' OR @LEVEL_NAME = 'NDC' THEN A.ITEM_MASTER_SID WHEN @LEVEL_NAME = 'Brand' THEN A.BRAND_MASTER_SID WHEN @LEVEL_NAME = 'Therapeutic Class' THEN A.THERAPEUTIC_CLASS_HLPR_SID WHEN @LEVEL_NAME = 'COMPANY' OR @LEVEL_NAME = 'GL Company' THEN A.GL_COMP_COMPANY_MASTER_SID END ) AS VARCHAR(10)) + '.'
                                                    END
                                                END AS HIERARCHY_NO
                                    FROM        #TEMP_GLOBAL_CCP_PROD a
                                    JOIN        MYCTE B ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                                       AND A.GL_COMP_COMPANY_MASTER_SID = B.GL_COMP_COMPANY_MASTER_SID
                                                       AND A.BRAND_MASTER_SID = B.BRAND_MASTER_SID
                                                       AND A.THERAPEUTIC_CLASS_HLPR_SID = B.THERAPEUTIC_CLASS_HLPR_SID
                                    OUTER apply (SELECT rld.HIERARCHY_NO --for the matched RLD comb with formed value comb pull the existing hierarchy no itself
                                                 FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                                 WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                        AND rld.LEVEL_NO = @LEVEL_NO
                                                        AND rld.PARENT_NODE = b.parent_node
                                                        AND rld.RELATIONSHIP_LEVEL_VALUES = CASE
                                                                                              WHEN @LEVEL_NAME = 'Product'
                                                                                                    OR @LEVEL_NAME = 'NDC' THEN A.ITEM_MASTER_SID
                                                                                              WHEN @LEVEL_NAME = 'Brand' THEN A.BRAND_MASTER_SID
                                                                                              WHEN @LEVEL_NAME = 'Therapeutic Class' THEN A.THERAPEUTIC_CLASS_HLPR_SID
                                                                                              WHEN @LEVEL_NAME = 'COMPANY'
                                                                                                    OR @LEVEL_NAME = 'GL Company' THEN A.GL_COMP_COMPANY_MASTER_SID
                                                                                            END
                                                        AND rld.PARENT_NODE = @PARENT_NO_FIX) op_rld
                                    OUTER apply(SELECT Max(hierarchy_no) AS max_hier_no
                                                FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                                WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                       AND rld.LEVEL_NO = @LEVEL_NO
                                                       AND rld.PARENT_NODE = b.parent_node
                                                       AND rld.PARENT_NODE = @PARENT_NO_FIX) op_rld2

                                    SET @F+=1
                                END

                              SET @Parent_no_Fix_count=0
                              SET @PARENT_NO_FIX=''
                              SET @max_hier=''
                              SET @hier_no=''
                              SET @len_hier=0

                              IF Object_id('tempdb..#TEMP_FIX') IS NOT NULL
                                TRUNCATE TABLE #TEMP_FIX
                          END
                    END --END OF @LEVEL_NO >=1 AND @LEVEL_VALUE_REFERENCE = 'LINKED'
                  SET @J+=1
              END--inner while loop ends here

            SET @J=1

            /*SELECT DISTINCT RELATIONSHIP_BUILDER_SID,
                               HIERARCHY_LEVEL_DEFINITION_SID,
                               LEVEL_NO,
                               LEVEL_NAME,
                               LEVEL_VALUES,
                               PARENT_NODE,
                               HIERARCHY_NO
               FROM   #temp_rld_result
            */
            MERGE INTO RELATIONSHIP_LEVEL_DEFINITION AS RLD
            USING (SELECT DISTINCT RELATIONSHIP_BUILDER_SID,
                                   HIERARCHY_LEVEL_DEFINITION_SID,
                                   LEVEL_NO,
                                   LEVEL_NAME,
                                   LEVEL_VALUES,
                                   PARENT_NODE,
                                   HIERARCHY_NO
                   FROM   #temp_rld_result) TMP
            ON RLD.RELATIONSHIP_BUILDER_SID = TMP.RELATIONSHIP_BUILDER_SID
               AND RLD.HIERARCHY_LEVEL_DEFINITION_SID = TMP.HIERARCHY_LEVEL_DEFINITION_SID
               AND RLD.LEVEL_NO = TMP.LEVEL_NO
               AND RLD.LEVEL_NAME = TMP.LEVEL_NAME
               AND RLD.RELATIONSHIP_LEVEL_VALUES = TMP.LEVEL_VALUES
               AND RLD.PARENT_NODE = TMP.PARENT_NODE
               AND RLD.HIERARCHY_NO = TMP.HIERARCHY_NO
            WHEN NOT MATCHED THEN
              INSERT (RELATIONSHIP_BUILDER_SID,
                      HIERARCHY_LEVEL_DEFINITION_SID,
                      LEVEL_NO,
                      LEVEL_NAME,
                      RELATIONSHIP_LEVEL_VALUES,
                      PARENT_NODE,
                      HIERARCHY_NO,
                      CREATED_DATE,
                      MODIFIED_DATE,
                      MODIFIED_BY,
                      VERSION_NO)
              VALUES(TMP.RELATIONSHIP_BUILDER_SID,
                     TMP.HIERARCHY_LEVEL_DEFINITION_SID,
                     TMP.LEVEL_NO,
                     TMP.LEVEL_NAME,
                     TMP.LEVEL_VALUES,
                     TMP.PARENT_NODE,
                     HIERARCHY_NO,
                     Getdate(),
                     Getdate(),
                     1,
                     @EXISTING_VERSION_NO);

            IF @@ROWCOUNT > 0--Update version no of RELATIONSHIP_LEVEL_DEFINITION & RELATIONSHIP_BUILDER if insert succeeds
              BEGIN
                  UPDATE RELATIONSHIP_LEVEL_DEFINITION
                  SET    VERSION_NO = VERSION_NO + 1
                  WHERE  RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID

                  UPDATE RELATIONSHIP_BUILDER
                  SET    VERSION_NO = VERSION_NO + 1
                  WHERE  RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
              END

            SET @I+=1
        END------------Master while loop ends here -----------
  END
--, CASE WHEN @LEVEL_NAME = 'Product' OR @LEVEL_NAME = 'NDC' THEN ITEM_MASTER_SID WHEN @LEVEL_NAME = 'Brand' THEN BRAND_MASTER_SID WHEN @LEVEL_NAME = 'Therapeutic Class' THEN THERAPEUTIC_CLASS_HLPR_SID WHEN @LEVEL_NAME = 'COMPANY' OR @LEVEL_NAME = 'GL Company' THEN GL_COMP_COMPANY_MASTER_SID END
