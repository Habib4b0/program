IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_CUSTOMER_HIERARCHY_DYNAMIC_ADD'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_CUSTOMER_HIERARCHY_DYNAMIC_ADD]
  END

GO

CREATE PROCEDURE [dbo].[PRC_CUSTOMER_HIERARCHY_DYNAMIC_ADD]
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
              @FORMULA                        NVARCHAR(MAX),
              @LEVEL_VALUE_REFERENCE          VARCHAR(50),
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
              @Parent_no_Fix_count            INT =0,
              @hierarchy_no_inr               VARCHAR(100)-----CEL-425
			  ,@CONTRACT_UPDATE					INT
			  ,@COMPANY_UPDATE					INT

      ----------------------------------------Temp tables for Relationship Builder & Hierarchy Level Definition-------------------
      IF Object_id('tempdb..#temp_holder') IS NOT NULL
        DROP TABLE #temp_holder

      CREATE TABLE #temp_holder
        (
           RID            INT IDENTITY(1, 1),
           INCLUSION_RULE VARCHAR(100)
        )

      IF Object_id('tempdb..#TEMP_FIX') IS NOT NULL
        DROP TABLE #TEMP_FIX

      CREATE TABLE #TEMP_FIX
        (
           ID          INT IDENTITY(1, 1),
           PARENT_NODE VARCHAR(100)
        )

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
             JOIN HIERARCHY_LEVEL_DEFINITION b
               ON a.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID
             JOIN HIERARCHY_DEFINITION hd
               ON a.HIERARCHY_DEFINITION_SID = hd.HIERARCHY_DEFINITION_SID
      WHERE  a.BUILD_TYPE = 'AUTOMATIC'
             AND EXISTS (SELECT HELPER_TABLE_SID
                         FROM   HELPER_TABLE H1
                         WHERE  LIST_NAME = 'HIERARCHY_CATEGORY'
                                AND DESCRIPTION = 'Customer Hierarchy'
                                AND H1.HELPER_TABLE_SID = hd.HIERARCHY_CATEGORY)
             AND b.LEVEL_VALUE_REFERENCE = 'linked'

      SELECT @CNT = Count(*)
      FROM   #temp_rld_master

      --SELECT * FROM #temp_rld_master
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
            ORDER  BY RELATIONSHIP_BUILDER_SID

            ---------------------Data for each Relationship_builder to check for rules------------------------------------
            IF Object_id('tempdb..#TEMP_GLOBAL_TEMP') IS NOT NULL
              DROP TABLE #TEMP_GLOBAL_TEMP

            CREATE TABLE #TEMP_GLOBAL_TEMP
              (
                 MARKET_TYPE_SID            INT,
                 CONTRACT_HOLDER_SID        INT,
                 CONTRACT_MASTER_SID        INT,
                 COMPANY_MASTER_SID         INT,
                 ITEM_MASTER_SID            INT,
                 BRAND_MASTER_SID           INT NULL,
                 GL_COMP_COMPANY_MASTER_SID INT NULL,
                 THERAPEUTIC_CLASS_HLPR_SID INT NULL
              )

            /* INSERT INTO #TEMP_GLOBAL_TEMP
                        (MARKET_TYPE_SID,
                         CONTRACT_HOLDER_SID,
                         CONTRACT_MASTER_SID,
                         COMPANY_MASTER_SID,
                         ITEM_MASTER_SID,
                         BRAND_MASTER_SID,
                         GL_COMP_COMPANY_MASTER_SID,
                         THERAPEUTIC_CLASS_HLPR_SID)
           SELECT A.MARKET_TYPE_SID,
                   A.CONTRACT_HOLDER_SID,
                   A.CONTRACT_MASTER_SID,
                   A.COMPANY_MASTER_SID,
                   A.ITEM_MASTER_SID,
                   A.BRAND_MASTER_SID,
                   A.GL_COMP_COMPANY_MASTER_SID,
                   A.THERAPEUTIC_CLASS_HLPR_SID
            FROM   ##TEMP_GLOBAL_CCP A
            JOIN   (SELECT COMPANY_MASTER_SID,----------------------------- newly added for removing same company in multiple contracts (GAL UAT Issue)
                           CONTRACT_MASTER_SID,
                           ROW_NUMBER()
                             OVER(
                               PARTITION BY COMPANY_MASTER_SID
                               ORDER BY COMPANY_MASTER_SID) AS RN
                    FROM   (SELECT DISTINCT CONTRACT_MASTER_SID,
                                            COMPANY_MASTER_SID
                            FROM   ##TEMP_GLOBAL_CCP)X)B ON A.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID
                                                        AND A.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
            WHERE  B.RN = 1
			*/------cel-425
            INSERT INTO #TEMP_GLOBAL_TEMP
                        (MARKET_TYPE_SID,
                         CONTRACT_HOLDER_SID,
                         CONTRACT_MASTER_SID,
                         COMPANY_MASTER_SID,
                         ITEM_MASTER_SID,
                         BRAND_MASTER_SID,
                         GL_COMP_COMPANY_MASTER_SID,
                         THERAPEUTIC_CLASS_HLPR_SID)
            SELECT A.MARKET_TYPE_SID,
                   A.CONTRACT_HOLDER_SID,
                   A.CONTRACT_MASTER_SID,
                   A.COMPANY_MASTER_SID,
                   A.ITEM_MASTER_SID,
                   A.BRAND_MASTER_SID,
                   A.GL_COMP_COMPANY_MASTER_SID,
                   A.THERAPEUTIC_CLASS_HLPR_SID
            FROM   ##TEMP_GLOBAL_CCP A
                   JOIN (SELECT COMPANY_MASTER_SID,----------------------------- newly added for removing same company in multiple contracts (GAL UAT Issue)
                                CONTRACT_MASTER_SID,
                                Row_number()
                                  OVER(
                                    PARTITION BY CONTRACT_MASTER_SID, COMPANY_MASTER_SID
                                    ORDER BY CONTRACT_MASTER_SID, COMPANY_MASTER_SID) AS RN
                         FROM   (SELECT DISTINCT CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID
                                 FROM   ##TEMP_GLOBAL_CCP)X)B
                     ON A.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID
                        AND A.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
            WHERE  B.RN = 1
			 IF Object_id('tempdb..#TEMP_GLOBAL_TEMP1') IS NOT NULL
              DROP TABLE #TEMP_GLOBAL_TEMP1

            CREATE TABLE #TEMP_GLOBAL_TEMP1
              (
                 MARKET_TYPE_SID            INT,
                 CONTRACT_HOLDER_SID        INT,
                 CONTRACT_MASTER_SID        INT,
                 COMPANY_MASTER_SID         INT,
                 ITEM_MASTER_SID            INT,
                 BRAND_MASTER_SID           INT NULL,
                 GL_COMP_COMPANY_MASTER_SID INT NULL,
                 THERAPEUTIC_CLASS_HLPR_SID INT NULL
              )

            /* INSERT INTO #TEMP_GLOBAL_TEMP
                        (MARKET_TYPE_SID,
                         CONTRACT_HOLDER_SID,
                         CONTRACT_MASTER_SID,
                         COMPANY_MASTER_SID,
                         ITEM_MASTER_SID,
                         BRAND_MASTER_SID,
                         GL_COMP_COMPANY_MASTER_SID,
                         THERAPEUTIC_CLASS_HLPR_SID)
           SELECT A.MARKET_TYPE_SID,
                   A.CONTRACT_HOLDER_SID,
                   A.CONTRACT_MASTER_SID,
                   A.COMPANY_MASTER_SID,
                   A.ITEM_MASTER_SID,
                   A.BRAND_MASTER_SID,
                   A.GL_COMP_COMPANY_MASTER_SID,
                   A.THERAPEUTIC_CLASS_HLPR_SID
            FROM   ##TEMP_GLOBAL_CCP A
            JOIN   (SELECT COMPANY_MASTER_SID,----------------------------- newly added for removing same company in multiple contracts (GAL UAT Issue)
                           CONTRACT_MASTER_SID,
                           ROW_NUMBER()
                             OVER(
                               PARTITION BY COMPANY_MASTER_SID
                               ORDER BY COMPANY_MASTER_SID) AS RN
                    FROM   (SELECT DISTINCT CONTRACT_MASTER_SID,
                                            COMPANY_MASTER_SID
                            FROM   ##TEMP_GLOBAL_CCP)X)B ON A.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID
                                                        AND A.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
            WHERE  B.RN = 1
			*/------cel-425
            INSERT INTO #TEMP_GLOBAL_TEMP1
                        (MARKET_TYPE_SID,
                         CONTRACT_HOLDER_SID,
                         CONTRACT_MASTER_SID,
                         COMPANY_MASTER_SID,
                         ITEM_MASTER_SID,
                         BRAND_MASTER_SID,
                         GL_COMP_COMPANY_MASTER_SID,
                         THERAPEUTIC_CLASS_HLPR_SID)
            SELECT A.MARKET_TYPE_SID,
                   A.CONTRACT_HOLDER_SID,
                   A.CONTRACT_MASTER_SID,
                   A.COMPANY_MASTER_SID,
                   A.ITEM_MASTER_SID,
                   A.BRAND_MASTER_SID,
                   A.GL_COMP_COMPANY_MASTER_SID,
                   A.THERAPEUTIC_CLASS_HLPR_SID
            FROM   ##TEMP_GLOBAL_CCP A
                   JOIN (SELECT COMPANY_MASTER_SID,----------------------------- newly added for removing same company in multiple contracts (GAL UAT Issue)
                                CONTRACT_MASTER_SID,
                                Row_number()
                                  OVER(
                                    PARTITION BY CONTRACT_MASTER_SID, COMPANY_MASTER_SID
                                    ORDER BY CONTRACT_MASTER_SID, COMPANY_MASTER_SID) AS RN
                         FROM   (SELECT DISTINCT CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID
                                 FROM   ##TEMP_GLOBAL_CCP)X)B
                     ON A.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID
                        AND A.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
            WHERE  B.RN = 1

            ---------------------Data for each Relationship_builder to check for rules------------------------------------
            ---------------------------Table For each Relationship_builder to store the results starts here-------------------------
            IF Object_id('tempdb..#temp_rld_result') IS NOT NULL
              DROP TABLE #temp_rld_result

            CREATE TABLE #temp_rld_result
              (
                 RID                            INT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
                 MARKET_TYPE_SID                INT NOT NULL,
                 CONTRACT_MASTER_SID            INT NOT NULL,
                 COMPANY_MASTER_SID             INT NOT NULL,
                 ITEM_MASTER_SID                INT NOT NULL,
                 RELATIONSHIP_BUILDER_SID       INT NOT NULL,
                 HIERARCHY_LEVEL_DEFINITION_SID INT NOT NULL,
                 LEVEL_NO                       INT NOT NULL,
                 LEVEL_NAME                     VARCHAR(50) NOT NULL,
                 LEVEL_VALUES                   VARCHAR(50) NULL,
                 PARENT_NODE                    VARCHAR(100) NULL,
                 HIERARCHY_NO                   VARCHAR(50) NULL
              )

            ---------------------------Table For each Relationship_builder to store the results ends here-------------------------
            --------------Available levels in the Hierarchy should not have NULL value in the new CCP's which is coming to the system starts here
            IF EXISTS(SELECT 1
                      FROM   HIERARCHY_LEVEL_DEFINITION
                      WHERE  HIERARCHY_DEFINITION_SID = @HIERARCHY_DEFINITION_SID
                             AND LEVEL_NAME = 'Contract Holder')
              BEGIN
                  DELETE FROM #TEMP_GLOBAL_TEMP
                  WHERE  CONTRACT_HOLDER_SID IS NULL
              END

            IF EXISTS(SELECT 1
                      FROM   HIERARCHY_LEVEL_DEFINITION
                      WHERE  HIERARCHY_DEFINITION_SID = @HIERARCHY_DEFINITION_SID
                             AND ( LEVEL_NAME = 'Market Type'
                                    OR LEVEL_NAME = 'Contract Type' ))
              BEGIN
                  DELETE FROM #TEMP_GLOBAL_TEMP
                  WHERE  MARKET_TYPE_SID IS NULL
              END

            --------------Available levels in the Hierarchy should not have NULL value in the new CCP's which is coming to the system ends here
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

            --  SELECT * FROM #temp_rld_hld
            WHILE ( @j <= @cnt_hld )--inner while loop (for each HIERARCHY_LEVEL_DEFINITION level in a RELATIONSHIP_BUILDER_SID)
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

                  IF Object_id('TEMPDB..#TEMP_RLD_HLD1') IS NOT NULL
                    DROP TABLE #TEMP_RLD_HLD1

                  CREATE TABLE #TEMP_RLD_HLD1
                    (
                       MARKET_TYPE_SID            INT,
                       CONTRACT_HOLDER_SID        INT,
                       CONTRACT_MASTER_SID        INT,
                       COMPANY_MASTER_SID         INT,
                       ITEM_MASTER_SID            INT,
                       BRAND_MASTER_SID           INT NULL,
                       GL_COMP_COMPANY_MASTER_SID INT NULL,
                       THERAPEUTIC_CLASS_HLPR_SID INT NULL,
                       PARENT_MARKET_TYPE_SID     INT,
                       PARENT_CONTRACT_HOLDER_SID INT,
                       PARENT_CONTRACT_MASTER_SID INT,
                       PARENT_COMPANY_MASTER_SID  INT
                    )

                  INSERT INTO #TEMP_RLD_HLD1
                              (MARKET_TYPE_SID,
                               CONTRACT_HOLDER_SID,
                               CONTRACT_MASTER_SID,
                               COMPANY_MASTER_SID,
                               ITEM_MASTER_SID,
                               BRAND_MASTER_SID,
                               GL_COMP_COMPANY_MASTER_SID,
                               THERAPEUTIC_CLASS_HLPR_SID,
                               PARENT_MARKET_TYPE_SID,
                               PARENT_CONTRACT_HOLDER_SID,
                               PARENT_CONTRACT_MASTER_SID,
                               PARENT_COMPANY_MASTER_SID)
                  SELECT MARKET_TYPE_SID,
                         CONTRACT_HOLDER_SID,
                         CONTRACT_MASTER_SID,
                         COMPANY_MASTER_SID,
                         ITEM_MASTER_SID,
                         BRAND_MASTER_SID,
                         GL_COMP_COMPANY_MASTER_SID,
                         THERAPEUTIC_CLASS_HLPR_SID,
                         MARKET_TYPE_SID     PARENT_MARKET_TYPE_SID,
                         MARKET_TYPE_SID     PARENT_CONTRACT_HOLDER_SID,
                         MARKET_TYPE_SID     PARENT_CONTRACT_MASTER_SID,
                         CONTRACT_MASTER_SID PARENT_COMPANY_MASTER_SID
                  --into #temp_rld_hld1
                  FROM   #TEMP_GLOBAL_TEMP

                  IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                    DROP TABLE ##TEMP_DYNAMIC

                  IF @INCLUSION_RULE_TYPE = 'RULE'
                    BEGIN
                        IF @LEVEL_NAME = 'Contract'
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @INCLUSION_RULE
                                                   AND EXISTS (SELECT 1
                                                               FROM   sys.foreign_key_columns fkc
                                                                      INNER JOIN sys.columns c
                                                                              ON fkc.parent_column_id = c.column_id
                                                                                 AND fkc.parent_object_id = c.object_id
                                                                      INNER JOIN sys.columns cref
                                                                              ON fkc.referenced_column_id = cref.column_id
                                                                                 AND fkc.referenced_object_id = cref.object_id
                                                               WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                      AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT CONTRACT_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @INCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT @formula = CASE
                                                        WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select contract_master_sid INTO ##TEMP_DYNAMIC from contract_master where '
                                                                                                          + a.COLUMN_NAME
                                                                                                          + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                          + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                        ELSE 'select contract_master_sid INTO ##TEMP_DYNAMIC from contract_master where '
                                                             + a.COLUMN_NAME + ' in (select '
                                                             + b.MAPPING_COLUMN_NAME + ' from '
                                                             + b.REFERENCE_TABLE_NAME + ' where '
                                                             + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                      END
                                    FROM   HIERARCHY_RULES_DEFINITION a
                                           LEFT JOIN vw_helper_list b
                                                  ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                     AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE  a.RULE_NAME = @INCLUSION_RULE
                                           AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC Sp_executesql
                                @FORMULA

                              DELETE FROM #TEMP_GLOBAL_TEMP --Delete records which not matches with the rule for that level
                              WHERE  NOT EXISTS (SELECT CONTRACT_MASTER_SID
                                                 FROM   ##TEMP_DYNAMIC T
                                                 WHERE  T.CONTRACT_MASTER_SID = #TEMP_GLOBAL_TEMP.CONTRACT_MASTER_SID)

                              /*
                                                      DELETE FROM #TEMP_GLOBAL_TEMP --Delete records which already exist in RLD for that level
                              						WHERE CONTRACT_MASTER_SID IN (SELECT RELATIONSHIP_LEVEL_VALUES FROM RELATIONSHIP_LEVEL_DEFINITION 
                              						WHERE LEVEL_NAME = @LEVEL_NAME AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID)
                              */
                              IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC
                          END
                        ELSE IF @LEVEL_NAME IN ( 'Trading Partner', 'Customer' )
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @INCLUSION_RULE
                                                   AND EXISTS (SELECT 1
                                                               FROM   sys.foreign_key_columns fkc
                                                                      INNER JOIN sys.columns c
                                                                              ON fkc.parent_column_id = c.column_id
                                                                                 AND fkc.parent_object_id = c.object_id
                                                                      INNER JOIN sys.columns cref
                                                                              ON fkc.referenced_column_id = cref.column_id
                                                                                 AND fkc.referenced_object_id = cref.object_id
                                                               WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                      AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM COMPANY_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @INCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT @formula = CASE
                                                        WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC from COMPANY_MASTER where '
                                                                                                          + a.COLUMN_NAME
                                                                                                          + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                          + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                        ELSE 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC from COMPANY_MASTER where '
                                                             + a.COLUMN_NAME + ' in (select '
                                                             + b.MAPPING_COLUMN_NAME + ' from '
                                                             + b.REFERENCE_TABLE_NAME + ' where '
                                                             + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                      END
                                    FROM   HIERARCHY_RULES_DEFINITION a
                                           LEFT JOIN vw_helper_list b
                                                  ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                     AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE  a.RULE_NAME = @INCLUSION_RULE
                                           AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC Sp_executesql
                                @FORMULA

                              DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                              WHERE  NOT EXISTS (SELECT COMPANY_MASTER_SID
                                                 FROM   ##TEMP_DYNAMIC T
                                                 WHERE  T.COMPANY_MASTER_SID = #TEMP_GLOBAL_TEMP.COMPANY_MASTER_SID)

                              DELETE FROM #TEMP_GLOBAL_TEMP --Delete records which already exist in RLD for that level
                              WHERE  EXISTS (SELECT RELATIONSHIP_LEVEL_VALUES
                                             FROM   RELATIONSHIP_LEVEL_DEFINITION T
                                             WHERE  LEVEL_NAME = @LEVEL_NAME
                                                    AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                    AND T.RELATIONSHIP_LEVEL_VALUES = COMPANY_MASTER_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC
                          END
                        ELSE IF @LEVEL_NAME = 'Contract Holder'
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @INCLUSION_RULE
                                                   AND EXISTS (SELECT 1
                                                               FROM   sys.foreign_key_columns fkc
                                                                      INNER JOIN sys.columns c
                                                                              ON fkc.parent_column_id = c.column_id
                                                                                 AND fkc.parent_object_id = c.object_id
                                                                      INNER JOIN sys.columns cref
                                                                              ON fkc.referenced_column_id = cref.column_id
                                                                                 AND fkc.referenced_object_id = cref.object_id
                                                               WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                      AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT CONT_HOLD_COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER 
                WHERE CONT_HOLD_COMPANY_MASTER_SID in (SELECT COMPANY_MASTER_SID FROM COMPANY_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @INCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT @formula = CASE WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'SELECT CONT_HOLD_COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER 
                WHERE CONT_HOLD_COMPANY_MASTER_SID in (select COMPANY_MASTER_SID from COMPANY_MASTER where ' + a.COLUMN_NAME + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = ''' + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END+')' ELSE 'SELECT CONT_HOLD_COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER 
                WHERE CONT_HOLD_COMPANY_MASTER_SID in (select COMPANY_MASTER_SID from COMPANY_MASTER where ' + a.COLUMN_NAME + ' in (select ' + b.MAPPING_COLUMN_NAME + ' from ' + b.REFERENCE_TABLE_NAME + ' where ' + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+
                                                      VALUE +
                                                      ''''
                                                      WHEN
                                                      LEFT
                                                      (
                                                      CONDITION
                                                      , 1 ) = 'C'
                                                             THEN
                                                             ' LIKE ''%' +VALUE+'%''' END+')' END + ')'
                                    FROM   HIERARCHY_RULES_DEFINITION a
                                           LEFT JOIN vw_helper_list b
                                                  ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                     AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE  a.RULE_NAME = @INCLUSION_RULE
                                           AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC Sp_executesql
                                @FORMULA

                              --PRINT @FORMULA
                              --SELECT * FROM ##TEMP_DYNAMIC
                              DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                              WHERE  NOT EXISTS (SELECT CONT_HOLD_COMPANY_MASTER_SID
                                                 FROM   ##TEMP_DYNAMIC T
                                                 WHERE  T.CONT_HOLD_COMPANY_MASTER_SID = #TEMP_GLOBAL_TEMP.CONTRACT_HOLDER_SID)

                              /*
                              						DELETE FROM #TEMP_GLOBAL_TEMP --Delete records which already exist in RLD for that level
                              						WHERE CONTRACT_HOLDER_SID IN (SELECT RELATIONSHIP_LEVEL_VALUES FROM RELATIONSHIP_LEVEL_DEFINITION 
                              						WHERE LEVEL_NAME = @LEVEL_NAME AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID)
                              */
                              IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC
                          END
                        ELSE IF @LEVEL_NAME IN ( 'Market Type', 'Contract Type' )
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @INCLUSION_RULE
                                                   AND EXISTS (SELECT 1
                                                               FROM   sys.foreign_key_columns fkc
                                                                      INNER JOIN sys.columns c
                                                                              ON fkc.parent_column_id = c.column_id
                                                                                 AND fkc.parent_object_id = c.object_id
                                                                      INNER JOIN sys.columns cref
                                                                              ON fkc.referenced_column_id = cref.column_id
                                                                                 AND fkc.referenced_object_id = cref.object_id
                                                               WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                      AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT CONTRACT_TYPE INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @INCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT @formula = CASE
                                                        WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select CONTRACT_TYPE INTO ##TEMP_DYNAMIC from contract_master where '
                                                                                                          + a.COLUMN_NAME
                                                                                                          + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                          + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                        ELSE 'select CONTRACT_TYPE INTO ##TEMP_DYNAMIC from contract_master where '
                                                             + a.COLUMN_NAME + ' in (select '
                                                             + b.MAPPING_COLUMN_NAME + ' from '
                                                             + b.REFERENCE_TABLE_NAME + ' where '
                                                             + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                      END
                                    FROM   HIERARCHY_RULES_DEFINITION a
                                           LEFT JOIN vw_helper_list b
                                                  ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                     AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE  a.RULE_NAME = @INCLUSION_RULE
                                           AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC Sp_executesql
                                @FORMULA

                              --PRINT @FORMULA
                              --SELECT * FROM ##TEMP_DYNAMIC
                              DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                              WHERE  NOT EXISTS (SELECT CONTRACT_TYPE
                                                 FROM   ##TEMP_DYNAMIC T
                                                 WHERE  T.CONTRACT_TYPE = MARKET_TYPE_SID)

                              DELETE FROM #TEMP_GLOBAL_TEMP --Delete existing Market Types in the system
                              WHERE  EXISTS (SELECT VALUE
                                             FROM   (SELECT HELPER_TABLE_SID AS VALUE
                                                     FROM   HELPER_TABLE
                                                     WHERE  LIST_NAME = 'CONTRACT_TYPE'
                                                     EXCEPT
                                                     SELECT RELATIONSHIP_LEVEL_VALUES AS VALUE
                                                     FROM   RELATIONSHIP_LEVEL_DEFINITION
                                                     WHERE  LEVEL_NAME = @LEVEL_NAME
                                                            AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID)A
                                             WHERE  A.VALUE = #TEMP_GLOBAL_TEMP.MARKET_TYPE_SID)

                              /*
                                                       DELETE FROM #TEMP_GLOBAL_TEMP --Delete records which already exist in RLD for that level
                              						WHERE MARKET_TYPE_SID IN (SELECT RELATIONSHIP_LEVEL_VALUES FROM RELATIONSHIP_LEVEL_DEFINITION 
                              						WHERE LEVEL_NAME = @LEVEL_NAME AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID)
                              */
                              IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC
                          END
                    END --End of Inclusion Rule Type

                  -- select * from ##TEMP_GLOBAL_CCP
                  SET @FORMULA = ''

                  IF @EXCLUSION_RULE_TYPE = 'RULE'
                    BEGIN
                        IF @LEVEL_NAME = 'Contract'
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @EXCLUSION_RULE
                                                   AND EXISTS (SELECT 1
                                                               FROM   sys.foreign_key_columns fkc
                                                                      INNER JOIN sys.columns c
                                                                              ON fkc.parent_column_id = c.column_id
                                                                                 AND fkc.parent_object_id = c.object_id
                                                                      INNER JOIN sys.columns cref
                                                                              ON fkc.referenced_column_id = cref.column_id
                                                                                 AND fkc.referenced_object_id = cref.object_id
                                                               WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                      AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT CONTRACT_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @EXCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT @formula = CASE
                                                        WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select contract_master_sid INTO ##TEMP_DYNAMIC from contract_master where '
                                                                                                          + a.COLUMN_NAME
                                                                                                          + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                          + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                        ELSE 'select contract_master_sid INTO ##TEMP_DYNAMIC from contract_master where '
                                                             + a.COLUMN_NAME + ' in (select '
                                                             + b.MAPPING_COLUMN_NAME + ' from '
                                                             + b.REFERENCE_TABLE_NAME + ' where '
                                                             + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                      END
                                    FROM   HIERARCHY_RULES_DEFINITION a
                                           LEFT JOIN vw_helper_list b
                                                  ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                     AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE  a.RULE_NAME = @EXCLUSION_RULE
                                           AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC Sp_executesql
                                @FORMULA

                              DELETE FROM #TEMP_GLOBAL_TEMP --Delete records which not matches with the rule for that level
                              WHERE  NOT EXISTS (SELECT CONTRACT_MASTER_SID
                                                 FROM   ##TEMP_DYNAMIC T
                                                 WHERE  T.CONTRACT_MASTER_SID = #TEMP_GLOBAL_TEMP.CONTRACT_MASTER_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC
                          END
                        ELSE IF @LEVEL_NAME IN ( 'Trading Partner', 'Customer' )
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @EXCLUSION_RULE
                                                   AND EXISTS (SELECT 1
                                                               FROM   sys.foreign_key_columns fkc
                                                                      INNER JOIN sys.columns c
                                                                              ON fkc.parent_column_id = c.column_id
                                                                                 AND fkc.parent_object_id = c.object_id
                                                                      INNER JOIN sys.columns cref
                                                                              ON fkc.referenced_column_id = cref.column_id
                                                                                 AND fkc.referenced_object_id = cref.object_id
                                                               WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                      AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM COMPANY_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @EXCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT @formula = CASE
                                                        WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC from COMPANY_MASTER where '
                                                                                                          + a.COLUMN_NAME
                                                                                                          + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                          + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                        ELSE 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC from COMPANY_MASTER where '
                                                             + a.COLUMN_NAME + ' in (select '
                                                             + b.MAPPING_COLUMN_NAME + ' from '
                                                             + b.REFERENCE_TABLE_NAME + ' where '
                                                             + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                      END
                                    FROM   HIERARCHY_RULES_DEFINITION a
                                           LEFT JOIN vw_helper_list b
                                                  ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                     AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE  a.RULE_NAME = @EXCLUSION_RULE
                                           AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC Sp_executesql
                                @FORMULA

                              DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                              WHERE  NOT EXISTS (SELECT COMPANY_MASTER_SID
                                                 FROM   ##TEMP_DYNAMIC T
                                                 WHERE  T.COMPANY_MASTER_SID = #TEMP_GLOBAL_TEMP.COMPANY_MASTER_SID)

                              DELETE FROM #TEMP_GLOBAL_TEMP --Delete records which already exist in RLD for that level
                              WHERE  EXISTS (SELECT RELATIONSHIP_LEVEL_VALUES
                                             FROM   RELATIONSHIP_LEVEL_DEFINITION T
                                             WHERE  LEVEL_NAME = @LEVEL_NAME
                                                    AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                    AND T.RELATIONSHIP_LEVEL_VALUES = #TEMP_GLOBAL_TEMP.COMPANY_MASTER_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC
                          END
                        ELSE IF @LEVEL_NAME = 'Contract Holder'
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @EXCLUSION_RULE
                                                   AND EXISTS (SELECT 1
                                                               FROM   sys.foreign_key_columns fkc
                                                                      INNER JOIN sys.columns c
                                                                              ON fkc.parent_column_id = c.column_id
                                                                                 AND fkc.parent_object_id = c.object_id
                                                                      INNER JOIN sys.columns cref
                                                                              ON fkc.referenced_column_id = cref.column_id
                                                                                 AND fkc.referenced_object_id = cref.object_id
                                                               WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                      AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT CONT_HOLD_COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER 
                WHERE CONT_HOLD_COMPANY_MASTER_SID in (SELECT COMPANY_MASTER_SID FROM COMPANY_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @EXCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT @formula = CASE WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'SELECT CONT_HOLD_COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER 
                WHERE CONT_HOLD_COMPANY_MASTER_SID in (select COMPANY_MASTER_SID from COMPANY_MASTER where ' + a.COLUMN_NAME + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = ''' + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END+')' ELSE 'SELECT CONT_HOLD_COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER 
                WHERE CONT_HOLD_COMPANY_MASTER_SID in (select COMPANY_MASTER_SID from COMPANY_MASTER where ' + a.COLUMN_NAME + ' in (select ' + b.MAPPING_COLUMN_NAME + ' from ' + b.REFERENCE_TABLE_NAME + ' where ' + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+
                                                      VALUE +
                                                      ''''
                                                      WHEN
                                                      LEFT
                                                      (
                                                      CONDITION
                                                      , 1 ) = 'C'
                                                             THEN
                                                             ' LIKE ''%' +VALUE+'%''' END+')' END + ')'
                                    FROM   HIERARCHY_RULES_DEFINITION a
                                           LEFT JOIN vw_helper_list b
                                                  ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                     AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE  a.RULE_NAME = @EXCLUSION_RULE
                                           AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC Sp_executesql
                                @FORMULA

                              --PRINT @FORMULA
                              --SELECT * FROM ##TEMP_DYNAMIC
                              DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                              WHERE  NOT EXISTS (SELECT CONT_HOLD_COMPANY_MASTER_SID
                                                 FROM   ##TEMP_DYNAMIC T
                                                 WHERE  T.CONT_HOLD_COMPANY_MASTER_SID = #TEMP_GLOBAL_TEMP.CONTRACT_HOLDER_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC
                          END
                        ELSE IF @LEVEL_NAME IN ( 'Market Type', 'Contract Type' )
                          BEGIN
                              IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                            FROM   HIERARCHY_RULES_DEFINITION hd
                                            WHERE  RULE_NAME = @EXCLUSION_RULE
                                                   AND EXISTS (SELECT 1
                                                               FROM   sys.foreign_key_columns fkc
                                                                      INNER JOIN sys.columns c
                                                                              ON fkc.parent_column_id = c.column_id
                                                                                 AND fkc.parent_object_id = c.object_id
                                                                      INNER JOIN sys.columns cref
                                                                              ON fkc.referenced_column_id = cref.column_id
                                                                                 AND fkc.referenced_object_id = cref.object_id
                                                               WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                      AND c.name = hd.COLUMN_NAME))
                                BEGIN
                                    SET @FORMULA = 'SELECT CONTRACT_TYPE INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER WHERE '

                                    SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                    FROM   HIERARCHY_RULES_DEFINITION
                                    WHERE  RULE_NAME = @EXCLUSION_RULE
                                END
                              ELSE --FK columns (HELPER table and Other tables)
                                BEGIN
                                    SELECT @formula = CASE
                                                        WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select CONTRACT_TYPE INTO ##TEMP_DYNAMIC from contract_master where '
                                                                                                          + a.COLUMN_NAME
                                                                                                          + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                          + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                        ELSE 'select CONTRACT_TYPE INTO ##TEMP_DYNAMIC from contract_master where '
                                                             + a.COLUMN_NAME + ' in (select '
                                                             + b.MAPPING_COLUMN_NAME + ' from '
                                                             + b.REFERENCE_TABLE_NAME + ' where '
                                                             + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                      END
                                    FROM   HIERARCHY_RULES_DEFINITION a
                                           LEFT JOIN vw_helper_list b
                                                  ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                     AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                    WHERE  a.RULE_NAME = @EXCLUSION_RULE
                                           AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                END

                              EXEC Sp_executesql
                                @FORMULA

                              --PRINT @FORMULA
                              --SELECT * FROM ##TEMP_DYNAMIC
                              DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                              WHERE  NOT EXISTS (SELECT CONTRACT_TYPE
                                                 FROM   ##TEMP_DYNAMIC T
                                                 WHERE  T.CONTRACT_TYPE = #TEMP_GLOBAL_TEMP.MARKET_TYPE_SID)

                              DELETE FROM #TEMP_GLOBAL_TEMP --Delete existing Market Types in the system
                              WHERE  EXISTS (SELECT VALUE
                                             FROM   (SELECT HELPER_TABLE_SID AS VALUE
                                                     FROM   HELPER_TABLE
                                                     WHERE  LIST_NAME = 'CONTRACT_TYPE'
                                                     EXCEPT
                                                     SELECT RELATIONSHIP_LEVEL_VALUES AS VALUE
                                                     FROM   RELATIONSHIP_LEVEL_DEFINITION
                                                     WHERE  LEVEL_NAME = @LEVEL_NAME
                                                            AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID)A
                                             WHERE  A.VALUE = #TEMP_GLOBAL_TEMP.MARKET_TYPE_SID)

                              IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                DROP TABLE ##TEMP_DYNAMIC
                          END
                    END --End of Exclusion Rule Type
                  SET @FORMULA = ''

                  IF ( @INCLUSION_CONDITION = 'AND'
                        OR @EXCLUSION_CONDITION = 'AND' )
                    BEGIN
                        IF Object_id('tempdb..#TEMP_GLOBAL_TEMP_SWAP') IS NOT NULL
                          DROP TABLE #TEMP_GLOBAL_TEMP_SWAP

                        CREATE TABLE #TEMP_GLOBAL_TEMP_SWAP
                          (
                             MARKET_TYPE_SID            INT,
                             CONTRACT_HOLDER_SID        INT,
                             CONTRACT_MASTER_SID        INT,
                             COMPANY_MASTER_SID         INT,
                             ITEM_MASTER_SID            INT,
                             BRAND_MASTER_SID           INT NULL,
                             GL_COMP_COMPANY_MASTER_SID INT NULL,
                             THERAPEUTIC_CLASS_HLPR_SID INT NULL
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
                        INSERT INTO #temp_holder
                                    (INCLUSION_RULE)
                        SELECT RULE_NAME
                        FROM   HIERARCHY_RULES_DEFINITION
                        WHERE  RULE_FLOW_GROUP_NAME = @INCLUSION_RULE

                        SELECT @CNT_GRP = Count(*)
                        FROM   #temp_holder

                        IF @LEVEL_NAME = 'Contract'
                          BEGIN
                              ----------------INCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @INCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP_SWAP

                                    INSERT INTO #TEMP_GLOBAL_TEMP_SWAP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP
                                END

                              ----------------INCLUSION_CONDITION CHECK PART 1 ENDS HERE---------------------------------        
                              WHILE( @k <= @CNT_GRP ) --For each rule in a Rule Group starts here
                                BEGIN
                                    SELECT @INCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                                  FROM   HIERARCHY_RULES_DEFINITION hd
                                                  WHERE  RULE_NAME = @INCLUSION_RULE
                                                         AND EXISTS (SELECT 1
                                                                     FROM   sys.foreign_key_columns fkc
                                                                            INNER JOIN sys.columns c
                                                                                    ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                                            INNER JOIN sys.columns cref
                                                                                    ON fkc.referenced_column_id = cref.column_id
                                                                                       AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                            AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT CONTRACT_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @INCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT @formula = CASE
                                                              WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select contract_master_sid INTO ##TEMP_DYNAMIC from contract_master where '
                                                                                                                + a.COLUMN_NAME
                                                                                                                + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                                + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                              ELSE 'select contract_master_sid INTO ##TEMP_DYNAMIC from contract_master where '
                                                                   + a.COLUMN_NAME + ' in (select '
                                                                   + b.MAPPING_COLUMN_NAME + ' from '
                                                                   + b.REFERENCE_TABLE_NAME + ' where '
                                                                   + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                            END
                                          FROM   HIERARCHY_RULES_DEFINITION a
                                                 LEFT JOIN vw_helper_list b
                                                        ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                           AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE  a.RULE_NAME = @INCLUSION_RULE
                                                 AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC Sp_executesql
                                      @FORMULA

                                    ----------------INCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @INCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT CONTRACT_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT CONTRACT_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T
                                                                   WHERE  T.VALUE = #TEMP_GLOBAL_TEMP.CONTRACT_MASTER_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------INCLUSION_CONDITION CHECK = OR ENDS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_TEMP --Delete records which not matches with the rule for that level
                                    WHERE  NOT EXISTS (SELECT CONTRACT_MASTER_SID
                                                       FROM   ##TEMP_DYNAMIC T
                                                       WHERE  T.CONTRACT_MASTER_SID = #TEMP_GLOBAL_TEMP.CONTRACT_MASTER_SID)

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
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP

                                    INSERT INTO #TEMP_GLOBAL_TEMP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------INCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END --END of @LEVEL_NAME = 'Contract'
                        ELSE IF @LEVEL_NAME IN ( 'Trading Partner', 'Customer' )
                          BEGIN
                              DELETE FROM #TEMP_GLOBAL_TEMP --Delete records which already exist in RLD for that level
                              WHERE  EXISTS (SELECT RELATIONSHIP_LEVEL_VALUES
                                             FROM   RELATIONSHIP_LEVEL_DEFINITION T
                                             WHERE  LEVEL_NAME = @LEVEL_NAME
                                                    AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                    AND T.RELATIONSHIP_LEVEL_VALUES = #TEMP_GLOBAL_TEMP.COMPANY_MASTER_SID)

                              ----------------INCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @INCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP_SWAP

                                    INSERT INTO #TEMP_GLOBAL_TEMP_SWAP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP
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
                                                         AND EXISTS (SELECT 1
                                                                     FROM   sys.foreign_key_columns fkc
                                                                            INNER JOIN sys.columns c
                                                                                    ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                                            INNER JOIN sys.columns cref
                                                                                    ON fkc.referenced_column_id = cref.column_id
                                                                                       AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                            AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM COMPANY_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @INCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT @formula = CASE
                                                              WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC from COMPANY_MASTER where '
                                                                                                                + a.COLUMN_NAME
                                                                                                                + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                                + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                              ELSE 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC from COMPANY_MASTER where '
                                                                   + a.COLUMN_NAME + ' in (select '
                                                                   + b.MAPPING_COLUMN_NAME + ' from '
                                                                   + b.REFERENCE_TABLE_NAME + ' where '
                                                                   + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                            END
                                          FROM   HIERARCHY_RULES_DEFINITION a
                                                 LEFT JOIN vw_helper_list b
                                                        ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                           AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE  a.RULE_NAME = @INCLUSION_RULE
                                                 AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC Sp_executesql
                                      @FORMULA

                                    ----------------INCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @INCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT COMPANY_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT COMPANY_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T
                                                                   WHERE  T.VALUE = #TEMP_GLOBAL_TEMP.COMPANY_MASTER_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------INCLUSION_CONDITION CHECK = OR ENDS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                    WHERE  NOT EXISTS (SELECT COMPANY_MASTER_SID
                                                       FROM   ##TEMP_DYNAMIC T
                                                       WHERE  T.COMPANY_MASTER_SID = #TEMP_GLOBAL_TEMP.COMPANY_MASTER_SID)

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
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP

                                    INSERT INTO #TEMP_GLOBAL_TEMP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------INCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END --END of @LEVEL_NAME = 'Trading Partner'
                        ELSE IF @LEVEL_NAME = 'Contract Holder'
                          BEGIN
                              ----------------INCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @INCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP_SWAP

                                    INSERT INTO #TEMP_GLOBAL_TEMP_SWAP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP
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
                                                         AND EXISTS (SELECT 1
                                                                     FROM   sys.foreign_key_columns fkc
                                                                            INNER JOIN sys.columns c
                                                                                    ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                                            INNER JOIN sys.columns cref
                                                                                    ON fkc.referenced_column_id = cref.column_id
                                                                                       AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                            AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT CONT_HOLD_COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER 
                WHERE CONT_HOLD_COMPANY_MASTER_SID in (SELECT COMPANY_MASTER_SID FROM COMPANY_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @INCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT @formula = CASE WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'SELECT CONT_HOLD_COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER 
                WHERE CONT_HOLD_COMPANY_MASTER_SID in (select COMPANY_MASTER_SID from COMPANY_MASTER where ' + a.COLUMN_NAME + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = ''' + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END+')' ELSE 'SELECT CONT_HOLD_COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER 
                WHERE CONT_HOLD_COMPANY_MASTER_SID in (select COMPANY_MASTER_SID from COMPANY_MASTER where ' + a.COLUMN_NAME + ' in (select ' + b.MAPPING_COLUMN_NAME + ' from ' + b.REFERENCE_TABLE_NAME + ' where ' + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN
                                                            ' ='''
                                                            +
                                                            VALUE +
                                                            ''''
                                                            WHEN
                                                            LEFT (
                                                            CONDITION, 1 ) =
                                                                   'C' THEN
                                                                   ' LIKE ''%' +VALUE+'%''' END+')' END + ')'
                                          FROM   HIERARCHY_RULES_DEFINITION a
                                                 LEFT JOIN vw_helper_list b
                                                        ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                           AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE  a.RULE_NAME = @INCLUSION_RULE
                                                 AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC Sp_executesql
                                      @FORMULA

                                    ----------------INCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @INCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT CONT_HOLD_COMPANY_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT CONT_HOLD_COMPANY_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T
                                                                   WHERE  T.VALUE = #TEMP_GLOBAL_TEMP.CONTRACT_HOLDER_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------INCLUSION_CONDITION CHECK = OR ENDS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                    WHERE  NOT EXISTS (SELECT CONT_HOLD_COMPANY_MASTER_SID
                                                       FROM   ##TEMP_DYNAMIC T
                                                       WHERE  T.CONT_HOLD_COMPANY_MASTER_SID = #TEMP_GLOBAL_TEMP.CONTRACT_HOLDER_SID)

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
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP

                                    INSERT INTO #TEMP_GLOBAL_TEMP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------INCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                        ELSE IF @LEVEL_NAME IN ( 'Market Type', 'Contract Type' )
                          BEGIN
                              DELETE FROM #TEMP_GLOBAL_TEMP --Delete existing Market Types in the system
                              WHERE  EXISTS (SELECT VALUE
                                             FROM   (SELECT HELPER_TABLE_SID AS VALUE
                                                     FROM   HELPER_TABLE
                                                     WHERE  LIST_NAME = 'CONTRACT_TYPE'
                                                     EXCEPT
                                                     SELECT RELATIONSHIP_LEVEL_VALUES AS VALUE
                                                     FROM   RELATIONSHIP_LEVEL_DEFINITION
                                                     WHERE  LEVEL_NAME = @LEVEL_NAME
                                                            AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID)A
                                             WHERE  A.VALUE = #TEMP_GLOBAL_TEMP.MARKET_TYPE_SID)

                              ----------------INCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @INCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP_SWAP

                                    INSERT INTO #TEMP_GLOBAL_TEMP_SWAP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP
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
                                                         AND EXISTS (SELECT 1
                                                                     FROM   sys.foreign_key_columns fkc
                                                                            INNER JOIN sys.columns c
                                                                                    ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                                            INNER JOIN sys.columns cref
                                                                                    ON fkc.referenced_column_id = cref.column_id
                                                                                       AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                            AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT CONTRACT_TYPE INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @INCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT @formula = CASE
                                                              WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select CONTRACT_TYPE INTO ##TEMP_DYNAMIC from contract_master where '
                                                                                                                + a.COLUMN_NAME
                                                                                                                + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                                + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                              ELSE 'select CONTRACT_TYPE INTO ##TEMP_DYNAMIC from contract_master where '
                                                                   + a.COLUMN_NAME + ' in (select '
                                                                   + b.MAPPING_COLUMN_NAME + ' from '
                                                                   + b.REFERENCE_TABLE_NAME + ' where '
                                                                   + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                            END
                                          FROM   HIERARCHY_RULES_DEFINITION a
                                                 LEFT JOIN vw_helper_list b
                                                        ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                           AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE  a.RULE_NAME = @INCLUSION_RULE
                                                 AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC Sp_executesql
                                      @FORMULA

                                    ----------------INCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @INCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT CONTRACT_TYPE
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT CONTRACT_TYPE
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T
                                                                   WHERE  T.VALUE = #TEMP_GLOBAL_TEMP.MARKET_TYPE_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------INCLUSION_CONDITION CHECK = OR ENDS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                    WHERE  NOT EXISTS (SELECT CONTRACT_TYPE
                                                       FROM   ##TEMP_DYNAMIC T
                                                       WHERE  T.CONTRACT_TYPE = #TEMP_GLOBAL_TEMP.MARKET_TYPE_SID)

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
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP

                                    INSERT INTO #TEMP_GLOBAL_TEMP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------INCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END --end of @LEVEL_NAME = 'Market Type' 
                    END --End of Inclusion Rule Group
                  SET @FORMULA = ''

                  TRUNCATE TABLE #temp_holder

                  IF Object_id('tempdb..#TEMP_OR_LOOP_SAVE') IS NOT NULL
                    TRUNCATE TABLE #TEMP_OR_LOOP_SAVE

                  SET @k = 1

                  IF @EXCLUSION_RULE_TYPE = 'GROUP'
                    BEGIN
                        INSERT INTO #temp_holder
                                    (INCLUSION_RULE)
                        SELECT RULE_NAME
                        FROM   HIERARCHY_RULES_DEFINITION
                        WHERE  RULE_FLOW_GROUP_NAME = @EXCLUSION_RULE

                        SELECT @CNT_GRP = Count(*)
                        FROM   #temp_holder

                        IF @LEVEL_NAME = 'Contract'
                          BEGIN
                              ----------------EXCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @EXCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP_SWAP

                                    INSERT INTO #TEMP_GLOBAL_TEMP_SWAP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP
                                END

                              ----------------EXCLUSION_CONDITION CHECK PART 1 ENDS HERE--------------------------------- 
                              WHILE( @k <= @CNT_GRP ) --For each rule in a Group starts here
                                BEGIN
                                    SELECT @EXCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                                  FROM   HIERARCHY_RULES_DEFINITION hd
                                                  WHERE  RULE_NAME = @EXCLUSION_RULE
                                                         AND EXISTS (SELECT 1
                                                                     FROM   sys.foreign_key_columns fkc
                                                                            INNER JOIN sys.columns c
                                                                                    ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                                            INNER JOIN sys.columns cref
                                                                                    ON fkc.referenced_column_id = cref.column_id
                                                                                       AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                            AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT CONTRACT_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @EXCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT @formula = CASE
                                                              WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select contract_master_sid INTO ##TEMP_DYNAMIC from contract_master where '
                                                                                                                + a.COLUMN_NAME
                                                                                                                + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                                + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                              ELSE 'select contract_master_sid INTO ##TEMP_DYNAMIC from contract_master where '
                                                                   + a.COLUMN_NAME + ' in (select '
                                                                   + b.MAPPING_COLUMN_NAME + ' from '
                                                                   + b.REFERENCE_TABLE_NAME + ' where '
                                                                   + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                            END
                                          FROM   HIERARCHY_RULES_DEFINITION a
                                                 LEFT JOIN vw_helper_list b
                                                        ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                           AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE  a.RULE_NAME = @EXCLUSION_RULE
                                                 AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC Sp_executesql
                                      @FORMULA

                                    ----------------EXCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @EXCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT CONTRACT_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT CONTRACT_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T
                                                                   WHERE  T.VALUE = #TEMP_GLOBAL_TEMP.CONTRACT_MASTER_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------EXCLUSION_CONDITION CHECK = OR ENDS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_TEMP --Delete records which not matches with the rule for that level
                                    WHERE  NOT EXISTS (SELECT CONTRACT_MASTER_SID
                                                       FROM   ##TEMP_DYNAMIC T
                                                       WHERE  T.CONTRACT_MASTER_SID = #TEMP_GLOBAL_TEMP.CONTRACT_MASTER_SID)

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
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP

                                    INSERT INTO #TEMP_GLOBAL_TEMP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------EXCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                        ELSE IF @LEVEL_NAME IN ( 'Trading Partner', 'Customer' )
                          BEGIN
                              DELETE FROM #TEMP_GLOBAL_TEMP --Delete records which already exist in RLD for that level
                              WHERE  EXISTS (SELECT RELATIONSHIP_LEVEL_VALUES
                                             FROM   RELATIONSHIP_LEVEL_DEFINITION T
                                             WHERE  LEVEL_NAME = @LEVEL_NAME
                                                    AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                    AND T.RELATIONSHIP_LEVEL_VALUES = #TEMP_GLOBAL_TEMP.COMPANY_MASTER_SID)

                              ----------------EXCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @EXCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP_SWAP

                                    INSERT INTO #TEMP_GLOBAL_TEMP_SWAP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP
                                END

                              ----------------EXCLUSION_CONDITION CHECK PART 1 ENDS HERE--------------------------------- 		
                              WHILE( @k <= @CNT_GRP ) --For each rule in a Group starts here
                                BEGIN
                                    SELECT @EXCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                                  FROM   HIERARCHY_RULES_DEFINITION hd
                                                  WHERE  RULE_NAME = @EXCLUSION_RULE
                                                         AND EXISTS (SELECT 1
                                                                     FROM   sys.foreign_key_columns fkc
                                                                            INNER JOIN sys.columns c
                                                                                    ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                                            INNER JOIN sys.columns cref
                                                                                    ON fkc.referenced_column_id = cref.column_id
                                                                                       AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                            AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM COMPANY_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @EXCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT @formula = CASE
                                                              WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC from COMPANY_MASTER where '
                                                                                                                + a.COLUMN_NAME
                                                                                                                + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                                + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                              ELSE 'select COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC from COMPANY_MASTER where '
                                                                   + a.COLUMN_NAME + ' in (select '
                                                                   + b.MAPPING_COLUMN_NAME + ' from '
                                                                   + b.REFERENCE_TABLE_NAME + ' where '
                                                                   + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                            END
                                          FROM   HIERARCHY_RULES_DEFINITION a
                                                 LEFT JOIN vw_helper_list b
                                                        ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                           AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE  a.RULE_NAME = @EXCLUSION_RULE
                                                 AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC Sp_executesql
                                      @FORMULA

                                    ----------------EXCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @EXCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT COMPANY_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT COMPANY_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T
                                                                   WHERE  T.VALUE = #TEMP_GLOBAL_TEMP.COMPANY_MASTER_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------EXCLUSION_CONDITION CHECK = OR ENDS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                    WHERE  NOT EXISTS (SELECT COMPANY_MASTER_SID
                                                       FROM   ##TEMP_DYNAMIC T
                                                       WHERE  T.COMPANY_MASTER_SID = #TEMP_GLOBAL_TEMP.COMPANY_MASTER_SID)

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
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP

                                    INSERT INTO #TEMP_GLOBAL_TEMP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------EXCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                        ELSE IF @LEVEL_NAME = 'Contract Holder'
                          BEGIN
                              ----------------EXCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @EXCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP_SWAP

                                    INSERT INTO #TEMP_GLOBAL_TEMP_SWAP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP
                                END

                              ----------------EXCLUSION_CONDITION CHECK PART 1 ENDS HERE--------------------------------- 			
                              WHILE( @k <= @CNT_GRP ) --For each rule in a Group starts here
                                BEGIN
                                    SELECT @EXCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                                  FROM   HIERARCHY_RULES_DEFINITION hd
                                                  WHERE  RULE_NAME = @EXCLUSION_RULE
                                                         AND EXISTS (SELECT 1
                                                                     FROM   sys.foreign_key_columns fkc
                                                                            INNER JOIN sys.columns c
                                                                                    ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                                            INNER JOIN sys.columns cref
                                                                                    ON fkc.referenced_column_id = cref.column_id
                                                                                       AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                            AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT CONT_HOLD_COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER 
                WHERE CONT_HOLD_COMPANY_MASTER_SID in (SELECT COMPANY_MASTER_SID FROM COMPANY_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @EXCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT @formula = CASE WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'SELECT CONT_HOLD_COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER 
                WHERE CONT_HOLD_COMPANY_MASTER_SID in (select COMPANY_MASTER_SID from COMPANY_MASTER where ' + a.COLUMN_NAME + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = ''' + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END+')' ELSE 'SELECT CONT_HOLD_COMPANY_MASTER_SID INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER 
                WHERE CONT_HOLD_COMPANY_MASTER_SID in (select COMPANY_MASTER_SID from COMPANY_MASTER where ' + a.COLUMN_NAME + ' in (select ' + b.MAPPING_COLUMN_NAME + ' from ' + b.REFERENCE_TABLE_NAME + ' where ' + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN
                                                            ' ='''
                                                            +
                                                            VALUE +
                                                            ''''
                                                            WHEN
                                                            LEFT (
                                                            CONDITION, 1 ) =
                                                                   'C' THEN
                                                                   ' LIKE ''%' +VALUE+'%''' END+')' END + ')'
                                          FROM   HIERARCHY_RULES_DEFINITION a
                                                 LEFT JOIN vw_helper_list b
                                                        ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                           AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE  a.RULE_NAME = @EXCLUSION_RULE
                                                 AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC Sp_executesql
                                      @FORMULA

                                    ----------------EXCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @EXCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT CONT_HOLD_COMPANY_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT CONT_HOLD_COMPANY_MASTER_SID
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T
                                                                   WHERE  T.VALUE = #TEMP_GLOBAL_TEMP.CONTRACT_HOLDER_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------EXCLUSION_CONDITION CHECK = OR ENDS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                    WHERE  NOT EXISTS (SELECT CONT_HOLD_COMPANY_MASTER_SID
                                                       FROM   ##TEMP_DYNAMIC T
                                                       WHERE  T.CONT_HOLD_COMPANY_MASTER_SID = #TEMP_GLOBAL_TEMP.CONTRACT_HOLDER_SID)

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
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP

                                    INSERT INTO #TEMP_GLOBAL_TEMP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------EXCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                        ELSE IF @LEVEL_NAME IN ( 'Market Type', 'Contract Type' )
                          BEGIN
                              DELETE FROM #TEMP_GLOBAL_TEMP --Delete existing Market Types in the system
                              WHERE  EXISTS (SELECT VALUE
                                             FROM   (SELECT HELPER_TABLE_SID AS VALUE
                                                     FROM   HELPER_TABLE
                                                     WHERE  LIST_NAME = 'CONTRACT_TYPE'
                                                     EXCEPT
                                                     SELECT RELATIONSHIP_LEVEL_VALUES AS VALUE
                                                     FROM   RELATIONSHIP_LEVEL_DEFINITION
                                                     WHERE  LEVEL_NAME = @LEVEL_NAME
                                                            AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID)A
                                             WHERE  A.VALUE = MARKET_TYPE_SID)

                              ----------------EXCLUSION_CONDITION CHECK PART 1 STARTS HERE---------------------------------
                              --MAKING A LOCAL COPY
                              IF @EXCLUSION_CONDITION = 'AND'
                                BEGIN
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP_SWAP

                                    INSERT INTO #TEMP_GLOBAL_TEMP_SWAP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP
                                END

                              ----------------EXCLUSION_CONDITION CHECK PART 1 ENDS HERE--------------------------------- 		
                              WHILE( @k <= @CNT_GRP ) --For each rule in a Group starts here
                                BEGIN
                                    SELECT @EXCLUSION_RULE = INCLUSION_RULE
                                    FROM   #temp_holder
                                    WHERE  RID = @K

                                    IF NOT EXISTS(SELECT 1 --Direct TableName and Column Names from HIERARCHY_RULES_DEFINITION
                                                  FROM   HIERARCHY_RULES_DEFINITION hd
                                                  WHERE  RULE_NAME = @EXCLUSION_RULE
                                                         AND EXISTS (SELECT 1
                                                                     FROM   sys.foreign_key_columns fkc
                                                                            INNER JOIN sys.columns c
                                                                                    ON fkc.parent_column_id = c.column_id
                                                                                       AND fkc.parent_object_id = c.object_id
                                                                            INNER JOIN sys.columns cref
                                                                                    ON fkc.referenced_column_id = cref.column_id
                                                                                       AND fkc.referenced_object_id = cref.object_id
                                                                     WHERE  Object_name(parent_object_id) = hd.TABLE_NAME
                                                                            AND c.name = hd.COLUMN_NAME))
                                      BEGIN
                                          SET @FORMULA = 'SELECT CONTRACT_TYPE INTO ##TEMP_DYNAMIC FROM CONTRACT_MASTER WHERE '

                                          SELECT @FORMULA += COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END
                                          FROM   HIERARCHY_RULES_DEFINITION
                                          WHERE  RULE_NAME = @EXCLUSION_RULE
                                      END
                                    ELSE --FK columns (HELPER table and Other tables)
                                      BEGIN
                                          SELECT @formula = CASE
                                                              WHEN b.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'select CONTRACT_TYPE INTO ##TEMP_DYNAMIC from contract_master where '
                                                                                                                + a.COLUMN_NAME
                                                                                                                + ' in (select HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = '''
                                                                                                                + B.LIST_NAME + ''' AND DESCRIPTION ' + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                              ELSE 'select CONTRACT_TYPE INTO ##TEMP_DYNAMIC from contract_master where '
                                                                   + a.COLUMN_NAME + ' in (select '
                                                                   + b.MAPPING_COLUMN_NAME + ' from '
                                                                   + b.REFERENCE_TABLE_NAME + ' where '
                                                                   + b.DESC_COLUMN_NAME + CASE WHEN LEFT(CONDITION, 1) = 'S' THEN ' LIKE '''+VALUE+'%''' WHEN LEFT(CONDITION, 2) = 'EN' THEN ' LIKE ''%'+VALUE+'''' WHEN LEFT(CONDITION, 2) = 'EQ' THEN ' ='''+VALUE+'''' WHEN LEFT(CONDITION, 1) = 'C' THEN ' LIKE ''%'+VALUE+'%''' END + ')'
                                                            END
                                          FROM   HIERARCHY_RULES_DEFINITION a
                                                 LEFT JOIN vw_helper_list b
                                                        ON a.TABLE_NAME = b.ACTUAL_TABLE_NAME
                                                           AND a.COLUMN_NAME = b.ACTUAL_COLUMN_NAME
                                          WHERE  a.RULE_NAME = @EXCLUSION_RULE
                                                 AND b.MAPPING_COLUMN_NAME IS NOT NULL
                                      END

                                    EXEC Sp_executesql
                                      @FORMULA

                                    ----------------EXCLUSION_CONDITION CHECK = OR STARTS HERE---------------------------------
                                    IF ( @EXCLUSION_CONDITION = 'OR' )
                                      BEGIN
                                          IF( @k < @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT CONTRACT_TYPE
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                SET @K += 1

                                                CONTINUE
                                            END

                                          IF( @k = @CNT_GRP )
                                            BEGIN
                                                INSERT INTO #TEMP_OR_LOOP_SAVE
                                                            (VALUE)
                                                SELECT CONTRACT_TYPE
                                                FROM   ##TEMP_DYNAMIC

                                                IF Object_id('tempdb..##TEMP_DYNAMIC') IS NOT NULL
                                                  DROP TABLE ##TEMP_DYNAMIC

                                                DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                                WHERE  NOT EXISTS (SELECT VALUE
                                                                   FROM   #TEMP_OR_LOOP_SAVE T
                                                                   WHERE  T.VALUE = #TEMP_GLOBAL_TEMP.MARKET_TYPE_SID)

                                                BREAK
                                            END
                                      END

                                    ----------------EXCLUSION_CONDITION CHECK = OR ENDS HERE---------------------------------
                                    DELETE FROM #TEMP_GLOBAL_TEMP--Delete records which not matches with the rule for that level
                                    WHERE  NOT EXISTS (SELECT CONTRACT_TYPE
                                                       FROM   ##TEMP_DYNAMIC T
                                                       WHERE  T.CONTRACT_TYPE = #TEMP_GLOBAL_TEMP.MARKET_TYPE_SID)

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
                                    TRUNCATE TABLE #TEMP_GLOBAL_TEMP

                                    INSERT INTO #TEMP_GLOBAL_TEMP
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_HOLDER_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 BRAND_MASTER_SID,
                                                 GL_COMP_COMPANY_MASTER_SID,
                                                 THERAPEUTIC_CLASS_HLPR_SID)
                                    SELECT MARKET_TYPE_SID,
                                           CONTRACT_HOLDER_SID,
                                           CONTRACT_MASTER_SID,
                                           COMPANY_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           BRAND_MASTER_SID,
                                           GL_COMP_COMPANY_MASTER_SID,
                                           THERAPEUTIC_CLASS_HLPR_SID
                                    FROM   #TEMP_GLOBAL_TEMP_SWAP

                                    SET @VAR_AND_CHECK = 0
                                END
                          ----------------EXCLUSION_CONDITION CHECK PART 3 ENDS HERE---------------------------------
                          END
                    END --End of Exclusion Rule Group
                  SET @FORMULA = ''

                  TRUNCATE TABLE #temp_holder

                  SET @k = 1
                  SET @FORMULA = ''

                  IF @LEVEL_NAME IN ( 'Trading Partner', 'Customer' ) ----------------------------- newly added for removing same company from the relation (GAL UAT Issue)
                    BEGIN
                        DELETE FROM #TEMP_GLOBAL_TEMP --Delete records which already exist in RLD for that level
                        WHERE  EXISTS (SELECT RELATIONSHIP_LEVEL_VALUES
                                       FROM   RELATIONSHIP_LEVEL_DEFINITION T
                                       WHERE  LEVEL_NAME = @LEVEL_NAME
                                              AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                              AND T.RELATIONSHIP_LEVEL_VALUES = #TEMP_GLOBAL_TEMP.COMPANY_MASTER_SID)
                    END

                  -----------------------INSERT INTO RESULT TABLE WITH PARENT NODE STARTS HERE---------------------------
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
                                    (MARKET_TYPE_SID,
                                     CONTRACT_MASTER_SID,
                                     COMPANY_MASTER_SID,
                                     ITEM_MASTER_SID,
                                     RELATIONSHIP_BUILDER_SID,
                                     HIERARCHY_LEVEL_DEFINITION_SID,
                                     LEVEL_NO,
                                     LEVEL_NAME,
                                     LEVEL_VALUES,
                                     PARENT_NODE,
                                     HIERARCHY_NO)
                        SELECT MARKET_TYPE_SID,
                               CONTRACT_MASTER_SID,
                               COMPANY_MASTER_SID,
                               ITEM_MASTER_SID,
                               B.RELATIONSHIP_BUILDER_SID,
                               B.HIERARCHY_LEVEL_DEFINITION_SID,
                               B.LEVEL_NO,
                               B.LEVEL_NAME,
                               CASE
                                 WHEN @LEVEL_NAME IN ( 'Market Type', 'Contract Type' ) THEN MARKET_TYPE_SID
                                 WHEN @LEVEL_NAME = 'Contract Holder' THEN CONTRACT_HOLDER_SID
                                 WHEN @LEVEL_NAME = 'Contract' THEN CONTRACT_MASTER_SID
                                 WHEN @LEVEL_NAME IN ( 'Trading Partner', 'Customer' ) THEN COMPANY_MASTER_SID
                               END AS LEVEL_VALUES,
                               B.PARENT_NODE,
                               B.HIERARCHY_NO
                        FROM   #TEMP_GLOBAL_TEMP a
                               JOIN mycte b -- ONLY ONE ROOT LEVEL CHECK HAPPENS HERE
                                 ON b.RELATIONSHIP_LEVEL_VALUES = CASE
                                                                    WHEN @LEVEL_NAME IN ( 'Market Type', 'Contract Type' ) THEN MARKET_TYPE_SID
                                                                    WHEN @LEVEL_NAME = 'Contract Holder' THEN CONTRACT_HOLDER_SID
                                                                    WHEN @LEVEL_NAME = 'Contract' THEN CONTRACT_MASTER_SID
                                                                    WHEN @LEVEL_NAME IN ( 'Trading Partner', 'Customer' ) THEN COMPANY_MASTER_SID
                                                                  END

                        IF @@ROWCOUNT = 0
                          BREAK
                    END
                  ELSE IF @LEVEL_NO >= 1
                     AND @LEVEL_VALUE_REFERENCE = 'LINKED'
                    BEGIN-------cel-425
                        IF NOT EXISTS (SELECT 1
                                       FROM   #temp_rld_result
                                       WHERE  LEVEL_NO = @LEVEL_NO - 1) --FIRST LINKED LEVEL AFTER USER_DEFINED LEVELS
                          BEGIN ;
                              IF NOT EXISTS(SELECT *
                                            FROM   RELATIONSHIP_LEVEL_DEFINITION RLD
                                            WHERE  RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                   AND EXISTS(SELECT 1
                                                              FROM   #TEMP_RLD_HLD1 T1
                                                              WHERE  T1.MARKET_TYPE_SID = RLD.RELATIONSHIP_LEVEL_VALUES)
                                                   AND LEVEL_NO = (SELECT LEVEL_NO
                                                                   FROM   #TEMP_RLD_HLD
                                                                   WHERE  RID = 1))
                                BEGIN
                                    ----NEW MARKET TYPES SHOULD NOPT BE ADDED TO ANY OF THE SEGMENTS
                                    DELETE HL1
                                    FROM   #TEMP_RLD_HLD1 HL1
                                    WHERE  NOT EXISTS(SELECT 1
                                                      FROM   RELATIONSHIP_LEVEL_DEFINITION RLD
                                                      WHERE  RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                             AND EXISTS(SELECT 1
                                                                        FROM   #TEMP_RLD_HLD1 T1
                                                                        WHERE  T1.MARKET_TYPE_SID = RLD.RELATIONSHIP_LEVEL_VALUES)
                                                             AND LEVEL_NO = (SELECT LEVEL_NO
                                                                             FROM   #TEMP_RLD_HLD
                                                                             WHERE  RID = 1)
                                                             AND HL1.MARKET_TYPE_SID = RLD.RELATIONSHIP_LEVEL_VALUES)
                                END

                              IF EXISTS(SELECT *
                                        FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                        WHERE  RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                               AND LEVEL_NO = @LEVEL_NO - 1
                                               AND EXISTS(SELECT 1
                                                          FROM   #temp_rld_hld1 t1
                                                          WHERE  t1.MARKET_TYPE_SID = rld.RELATIONSHIP_LEVEL_VALUES))
                                BEGIN
                                    --------for the exitsing market types none should be done
                                    IF ( @LEVEL_NAME NOT IN ( 'MARKET TYPE', 'CONTRACT TYPE' )
                                         AND @LEVEL_NAME = 'Contract' )
                                      BEGIN
                                          IF Object_id('tempdb..#contract_list') IS NOT NULL
                                            DROP TABLE #contract_list

                                          CREATE TABLE #contract_list
                                            (
                                               ID                  INT IDENTITY(1, 1),
                                               PARENT_NODE         VARCHAR(100),
                                               CONTRACT_MASTER_SID INT
                                            )

                                          INSERT INTO #contract_list
                                                      (CONTRACT_MASTER_SID,
                                                       parent_node)
                                          SELECT DISTINCT c.CONTRACT_MASTER_SID,
                                                          Cast(LEVEL_NO AS VARCHAR(2)) + '~'
                                                          + Cast(RELATIONSHIP_LEVEL_VALUES AS VARCHAR(100)) AS parent_node
                                          FROM   #temp_rld_hld1 c
                                                 JOIN CONTRACT_MASTER cm
                                                   ON c.CONTRACT_MASTER_SID = cm.CONTRACT_MASTER_SID
                                                 JOIN RELATIONSHIP_LEVEL_DEFINITION r
                                                   ON RELATIONSHIP_LEVEL_VALUES = cm.contract_type
                                                      AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                      AND LEVEL_NO = @LEVEL_NO - 1

                                          DECLARE @cl             INT =1,
                                                  @count_contract INT

                                          SET @count_contract=(SELECT Count(*)
                                                               FROM   #contract_list)

                                          WHILE ( @cl <= @count_contract )
                                            BEGIN ;
                                                WITH MYCTE
                                                     AS (SELECT PARENT_NODE,
                                                                CONTRACT_MASTER_SID
                                                         FROM   #contract_list
                                                         WHERE  id = @cl) --Get previous level parent node value.
                                                SELECT @hier_no = Cast(Max(Cast(Reverse(Substring(Stuff(Reverse(rld.hierarchy_no), 1, 1, ''), 1, Charindex('.', Stuff(Reverse(rld.hierarchy_no), 1, 1, '')) - 1))AS INT)) AS VARCHAR(10))
                                                FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                                       JOIN mycte b
                                                         ON 1 = 1
                                                WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                       AND rld.LEVEL_NO = @LEVEL_NO
                                                       AND rld.PARENT_NODE = b.parent_node
                                                       AND @LEVEL_NAME NOT IN ( 'MARKET TYPE', 'CONTRACT TYPE' )
                                                       AND @LEVEL_NAME = 'Contract';

                                                WITH MYCTE
                                                     AS (SELECT PARENT_NODE,
                                                                CONTRACT_MASTER_SID
                                                         FROM   #contract_list
                                                         WHERE  id = @cl) --Get previous level parent node value.
                                                SELECT @max_hier = rld.hierarchy_no,
                                                       @len_hier = Len(Reverse(Substring(Reverse(Stuff(rld.hierarchy_no, Len(rld.hierarchy_no), 1, '')), 1, Charindex('.', Reverse(Stuff(rld.hierarchy_no, Len(rld.hierarchy_no), 1, ''))) - 1)))
                                                FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                                       JOIN mycte b
                                                         ON 1 = 1
                                                WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                       AND rld.LEVEL_NO = @LEVEL_NO
                                                       AND rld.PARENT_NODE = b.parent_node
                                                       AND rld.HIERARCHY_NO LIKE '%.' + @hier_no + '.'
                                                       AND @LEVEL_NAME NOT IN ( 'MARKET TYPE', 'CONTRACT TYPE' )
                                                       AND @LEVEL_NAME = 'Contract';

                                                WITH mycte
                                                     AS (SELECT PARENT_NODE,
                                                                CONTRACT_MASTER_SID
                                                         FROM   #contract_list
                                                         WHERE  id = @cl) --Get previous level parent node value.
                                                INSERT INTO #temp_rld_result
                                                            (MARKET_TYPE_SID,
                                                             CONTRACT_MASTER_SID,
                                                             COMPANY_MASTER_SID,
                                                             ITEM_MASTER_SID,
                                                             RELATIONSHIP_BUILDER_SID,
                                                             HIERARCHY_LEVEL_DEFINITION_SID,
                                                             LEVEL_NO,
                                                             LEVEL_NAME,
                                                             LEVEL_VALUES,
                                                             PARENT_NODE,
                                                             HIERARCHY_NO)
                                                SELECT DISTINCT MARKET_TYPE_SID,
                                                                a.CONTRACT_MASTER_SID,
                                                                COMPANY_MASTER_SID,
                                                                ITEM_MASTER_SID,
                                                                @RELATIONSHIP_BUILDER_SID,
                                                                @HIERARCHY_LEVEL_DEFINITION_SID,
                                                                @LEVEL_NO,
                                                                @LEVEL_NAME,
                                                                CASE
                                                                  WHEN @LEVEL_NAME IN ( 'Market Type', 'Contract Type' ) THEN MARKET_TYPE_SID
                                                                  WHEN @LEVEL_NAME = 'Contract Holder' THEN CONTRACT_HOLDER_SID
                                                                  WHEN @LEVEL_NAME = 'Contract' THEN a.CONTRACT_MASTER_SID
                                                                  WHEN @LEVEL_NAME IN ( 'Trading Partner', 'Customer' ) THEN COMPANY_MASTER_SID
                                                                END AS LEVEL_VALUES,
                                                                B.PARENT_NODE,--from cte
                                                                CASE
                                                                  WHEN op_rld.HIERARCHY_NO IS NOT NULL THEN op_rld.HIERARCHY_NO --exist in RLD for VALUES + LEVEL_NO + PARENT_NODE + Relationship_Builder_Sid
                                                                  ELSE
                                                                    CASE
                                                                      WHEN op_rld2.max_hier_no IS NOT NULL THEN Stuff(@max_hier, Len(@max_hier) - @len_hier, @len_hier, Cast(Reverse(Substring(Reverse(Stuff(@max_hier, Len(@max_hier), 1, '')), 1, Charindex('.', Reverse(Stuff(@max_hier, Len(@max_hier), 1, ''))) - 1)) AS INT)
                                                                                                                                                                        + Dense_rank() OVER(PARTITION BY @RELATIONSHIP_BUILDER_SID, @LEVEL_NO, b.parent_node ORDER BY CASE WHEN @LEVEL_NAME IN ('Market Type', 'Contract Type') THEN A.MARKET_TYPE_SID WHEN @LEVEL_NAME = 'Contract Holder' THEN A.CONTRACT_HOLDER_SID WHEN @LEVEL_NAME = 'Contract' THEN A.CONTRACT_MASTER_SID WHEN @LEVEL_NAME IN ('Trading Partner', 'Customer') THEN A.COMPANY_MASTER_SID END))--exist in RLD for LEVEL_NO + PARENT_NODE
                                                                      ELSE CONVERT(VARCHAR(10), @RELATIONSHIP_BUILDER_SID)
                                                                           + '-' + Replicate('1.', (@LEVEL_NO-1))
                                                                           + Cast(Dense_rank() OVER(PARTITION BY @RELATIONSHIP_BUILDER_SID, @LEVEL_NO, b.parent_node ORDER BY CASE WHEN @LEVEL_NAME IN ('Market Type', 'Contract Type') THEN A.MARKET_TYPE_SID WHEN @LEVEL_NAME = 'Contract Holder' THEN A.CONTRACT_HOLDER_SID WHEN @LEVEL_NAME = 'Contract' THEN A.CONTRACT_MASTER_SID WHEN @LEVEL_NAME IN ('Trading Partner', 'Customer') THEN A.COMPANY_MASTER_SID END) AS VARCHAR(10)) + '.'
                                                                    END
                                                                END AS HIERARCHY_NO
                                                FROM   #TEMP_GLOBAL_TEMP a
                                                       JOIN mycte B
                                                         ON 1 = 1
                                                       OUTER apply (SELECT rld.HIERARCHY_NO --for the matched RLD comb with formed value comb pull the existing hierarchy no itself
                                                                    FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                                                    WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                                           AND rld.LEVEL_NO = @LEVEL_NO
                                                                           AND rld.PARENT_NODE = b.parent_node
                                                                           AND rld.RELATIONSHIP_LEVEL_VALUES = CASE
                                                                                                                 WHEN @LEVEL_NAME IN ( 'Market Type', 'Contract Type' ) THEN A.MARKET_TYPE_SID
                                                                                                                 WHEN @LEVEL_NAME = 'Contract Holder' THEN A.CONTRACT_HOLDER_SID
                                                                                                                 WHEN @LEVEL_NAME = 'Contract' THEN A.CONTRACT_MASTER_SID
                                                                                                                 WHEN @LEVEL_NAME IN ( 'Trading Partner', 'Customer' ) THEN A.COMPANY_MASTER_SID
                                                                                                               END
                                                                           AND @LEVEL_NAME NOT IN ( 'MARKET TYPE', 'CONTRACT TYPE' )
                                                                           AND @LEVEL_NAME = 'Contract') op_rld
                                                       OUTER apply(SELECT Max(hierarchy_no) AS max_hier_no
                                                                   FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                                                   WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                                          AND rld.LEVEL_NO = @LEVEL_NO
                                                                          AND rld.PARENT_NODE = b.parent_node
                                                                          AND @LEVEL_NAME NOT IN ( 'MARKET TYPE', 'CONTRACT TYPE' )
                                                                          AND @LEVEL_NAME = 'Contract') op_rld2

                                                SET @cl += 1
                                                SET @max_hier=''
                                                SET @hier_no=''
                                                SET @len_hier=0
                                            END
                                      END
                                END
                          END

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
						
					SET @CONTRACT_UPDATE =(SELECT @@ROWCOUNT)
                        IF EXISTS (SELECT 1
                                   FROM   #temp_rld_result
                                   WHERE  LEVEL_NO = @LEVEL_NO - 1
                                          AND @LEVEL_NAME NOT IN ( 'MARKET TYPE', 'CONTRACT TYPE' )
                                          AND @LEVEL_NAME IN ( 'Trading Partner', 'Customer' )) --FIRST LINKED LEVEL AFTER USER_DEFINED LEVELS
                          BEGIN ;
                              IF ( @LEVEL_NAME NOT IN ( 'MARKET TYPE', 'CONTRACT TYPE' )
                                   AND @LEVEL_NAME IN ( 'Trading Partner', 'Customer' ) )
                                BEGIN
                                    IF Object_id('tempdb..#company_list') IS NOT NULL
                                      DROP TABLE #company_list

                                    CREATE TABLE #company_list
                                      (
                                         ID                   INT IDENTITY(1, 1),
                                         PARENT_NODE          VARCHAR(100),
                                         COMPANY_MASTER_SID   INT,
                                         contract_mastrer_sid INT,
                                         hierarchy_no         VARCHAR(100)
                                      )

                                    INSERT INTO #company_list
                                                (COMPANY_MASTER_SID,
                                                 PARENT_NODE,
                                                 contract_mastrer_sid,
                                                 hierarchy_no)
                                    SELECT DISTINCT H1.COMPANY_MASTER_SID,
                                                    Cast(LEVEL_NO AS VARCHAR(2)) + '~'
                                                    + Cast(res.LEVEL_VALUES AS VARCHAR(100)) AS parent_node,
                                                    res.CONTRACT_MASTER_SID,
                                                    res.HIERARCHY_NO
                                    FROM   #temp_rld_hld1 H1
                                           JOIN #temp_rld_result res
                                             ON res.CONTRACT_MASTER_SID = h1.CONTRACT_MASTER_SID
                                    WHERE  @LEVEL_NAME IN ( 'Trading Partner', 'Customer' )

                                    DECLARE @dl            INT =1,
                                            @count_company INT

                                    SET @count_company=(SELECT Count(*)
                                                        FROM   #company_list)
                                    SET @max_hier=''
                                    SET @hier_no=''
                                    SET @len_hier=0

                                    WHILE( @dl <= @count_company )
                                      BEGIN
                                          SELECT @hier_no = CASE
                                                              WHEN Cast(NULLIF(Replace(rld.HIERARCHY_NO, b.HIERARCHY_NO, ''), '') AS INT) IS NULL THEN 1
                                                              ELSE Cast(NULLIF(Replace(Replace(rld.HIERARCHY_NO, b.HIERARCHY_NO, ''), '.', ''), '') AS INT)
                                                                   + 1
                                                            END,
                                                 @hierarchy_no_inr = rld.HIERARCHY_NO
                                          FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                                 JOIN (SELECT PARENT_NODE,
                                                              COMPANY_MASTER_SID,
                                                              CONTRACT_MASTRER_SID,
                                                              hierarchy_no
                                                       FROM   #COMPANY_LIST
                                                       WHERE  ID = @dl) B
                                                   ON 1 = 1
                                          WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                 AND rld.LEVEL_NO = @LEVEL_NO - 1
                                                 AND rld.RELATIONSHIP_LEVEL_VALUES = b.CONTRACT_MASTRER_SID
                                                 AND rld.HIERARCHY_NO LIKE Concat(b.hierarchy_no, '%')
                                                 AND @LEVEL_NAME IN ( 'Trading Partner', 'Customer' )
												 

                                          IF EXISTS(SELECT *
                                                    FROM   RELATIONSHIP_LEVEL_DEFINITION
                                                    WHERE  LEVEL_NO = @LEVEL_NO
                                                           AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                           AND HIERARCHY_NO LIKE @hierarchy_no_inr + '%')
                                            BEGIN
                                                SELECT @hier_no = Cast(NULLIF(Replace(Replace(HIERARCHY_NO, @hierarchy_no_inr, ''), '.', ''), '') AS INT)
                                                                  + 1
                                                FROM   RELATIONSHIP_LEVEL_DEFINITION
                                                WHERE  LEVEL_NO = @LEVEL_NO
                                                       AND RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                       AND HIERARCHY_NO LIKE @hierarchy_no_inr + '%'
                                            END

                                          IF EXISTS(SELECT *
                                                    FROM   #temp_rld_result
                                                    WHERE  LEVEL_NO = @LEVEL_NO
                                                           AND HIERARCHY_NO LIKE @hierarchy_no_inr + '%')
                                            BEGIN
                                                SELECT @hier_no = Cast(NULLIF(Replace(Replace(HIERARCHY_NO, @hierarchy_no_inr, ''), '.', ''), '') AS INT)
                                                                  + 1
                                                FROM   #temp_rld_result
                                                WHERE  LEVEL_NO = @LEVEL_NO
                                                       AND HIERARCHY_NO LIKE @hierarchy_no_inr + '%'
                                            END

                                          SELECT @max_hier = Concat(rld.hierarchy_no, @hier_no),
                                                 @len_hier = Len(Concat(rld.hierarchy_no, @hier_no))
                                          FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                                 JOIN (SELECT PARENT_NODE,
                                                              COMPANY_MASTER_SID,
                                                              CONTRACT_MASTRER_SID,
                                                              hierarchy_no
                                                       FROM   #COMPANY_LIST
                                                       WHERE  ID = @dl) B
                                                   ON 1 = 1
                                          WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                 AND rld.LEVEL_NO = @LEVEL_NO - 1
                                                 AND rld.RELATIONSHIP_LEVEL_VALUES = b.CONTRACT_MASTRER_SID
                                                 AND rld.HIERARCHY_NO LIKE Concat(b.hierarchy_no, '%')
                                                 AND @LEVEL_NAME IN ( 'Trading Partner', 'Customer' );

                                      
                                          ;WITH mycte
                                               AS (SELECT PARENT_NODE,
                                                          COMPANY_MASTER_SID,
                                                          CONTRACT_MASTRER_SID,
                                                          hierarchy_no
                                                   FROM   #COMPANY_LIST
                                                   WHERE  ID = @dl) --Get previous level parent node value.
                                          INSERT INTO #temp_rld_result
                                                      (MARKET_TYPE_SID,
                                                       CONTRACT_MASTER_SID,
                                                       COMPANY_MASTER_SID,
                                                       ITEM_MASTER_SID,
                                                       RELATIONSHIP_BUILDER_SID,
                                                       HIERARCHY_LEVEL_DEFINITION_SID,
                                                       LEVEL_NO,
                                                       LEVEL_NAME,
                                                       LEVEL_VALUES,
                                                       PARENT_NODE,
                                                       HIERARCHY_NO)
                                          SELECT DISTINCT MARKET_TYPE_SID,
                                                          CONTRACT_MASTER_SID,
                                                          a.COMPANY_MASTER_SID,
                                                          ITEM_MASTER_SID,
                                                          @RELATIONSHIP_BUILDER_SID,
                                                          @HIERARCHY_LEVEL_DEFINITION_SID,
                                                          @LEVEL_NO,
                                                          @LEVEL_NAME,
                                                          CASE
                                                            WHEN @LEVEL_NAME IN ( 'Market Type', 'Contract Type' ) THEN MARKET_TYPE_SID
                                                            WHEN @LEVEL_NAME = 'Contract Holder' THEN CONTRACT_HOLDER_SID
                                                            WHEN @LEVEL_NAME = 'Contract' THEN CONTRACT_MASTER_SID
                                                            WHEN @LEVEL_NAME IN ( 'Trading Partner', 'Customer' ) THEN a.COMPANY_MASTER_SID
                                                          END                    AS LEVEL_VALUES,
                                                          B.PARENT_NODE,--from cte                                   
                                                          Concat(@max_hier, '.') AS HIERARCHY_NO
                                          FROM   #TEMP_GLOBAL_TEMP1 a
                                                 JOIN mycte B
                                                   ON b.COMPANY_MASTER_SID = a.COMPANY_MASTER_SID
                                               where not exists(select 1 from RELATIONSHIP_LEVEL_DEFINITION rld1  where rld1.RELATIONSHIP_BUILDER_SID=@RELATIONSHIP_BUILDER_SID
												 and rld1.LEVEL_NO=@LEVEL_NO
												 and rld1.PARENT_NODE=b.PARENT_NODE
												 and rld1.RELATIONSHIP_LEVEL_VALUES=CASE
                                                            WHEN @LEVEL_NAME IN ( 'Market Type', 'Contract Type' ) THEN MARKET_TYPE_SID
                                                            WHEN @LEVEL_NAME = 'Contract Holder' THEN CONTRACT_HOLDER_SID
                                                            WHEN @LEVEL_NAME = 'Contract' THEN CONTRACT_MASTER_SID
                                                            WHEN @LEVEL_NAME IN ( 'Trading Partner', 'Customer' ) THEN a.COMPANY_MASTER_SID
                                                          END    );

                                          SET @dl+=1
                                          SET @max_hier=''
                                          SET @hier_no=''
                                          SET @len_hier=0
                                      END

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
											 SET @COMPANY_UPDATE =(SELECT @@ROWCOUNT)
                                END

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
                                              MARKET_TYPE_SID,
                                              CONTRACT_MASTER_SID,
                                              COMPANY_MASTER_SID,
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
                                                    MARKET_TYPE_SID,
                                                    CONTRACT_MASTER_SID,
                                                    COMPANY_MASTER_SID,
                                                    ITEM_MASTER_SID,
                                                    HIERARCHY_NO
                                             FROM   #temp_rld_result
                                             WHERE  LEVEL_NO = @LEVEL_NO - 1) --Get previous level parent node value.
                                    SELECT @hier_no = Cast(Max(Cast(Reverse(Substring(Stuff(Reverse(rld.hierarchy_no), 1, 1, ''), 1, Charindex('.', Stuff(Reverse(rld.hierarchy_no), 1, 1, '')) - 1))AS INT)) AS VARCHAR(10))
                                    FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                           JOIN mycte b
                                             ON 1 = 1
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
                                                    MARKET_TYPE_SID,
                                                    CONTRACT_MASTER_SID,
                                                    COMPANY_MASTER_SID,
                                                    ITEM_MASTER_SID,
                                                    HIERARCHY_NO
                                             FROM   #temp_rld_result
                                             WHERE  LEVEL_NO = @LEVEL_NO - 1) --Get previous level parent node value.
                                    SELECT @max_hier = rld.hierarchy_no,
                                           @len_hier = Len(Reverse(Substring(Reverse(Stuff(rld.hierarchy_no, Len(rld.hierarchy_no), 1, '')), 1, Charindex('.', Reverse(Stuff(rld.hierarchy_no, Len(rld.hierarchy_no), 1, ''))) - 1)))
                                    FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                           JOIN mycte b
                                             ON 1 = 1
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
                                                    MARKET_TYPE_SID,
                                                    CONTRACT_MASTER_SID,
                                                    COMPANY_MASTER_SID,
                                                    ITEM_MASTER_SID,
                                                    HIERARCHY_NO
                                             FROM   #temp_rld_result
                                             WHERE  LEVEL_NO = @LEVEL_NO - 1
                                                    AND Cast(LEVEL_NO AS VARCHAR(2)) + '~'
                                                        + Cast(LEVEL_VALUES AS VARCHAR(100)) = @PARENT_NO_FIX) --Get previous level parent node value.
                                    INSERT INTO #temp_rld_result
                                                (MARKET_TYPE_SID,
                                                 CONTRACT_MASTER_SID,
                                                 COMPANY_MASTER_SID,
                                                 ITEM_MASTER_SID,
                                                 RELATIONSHIP_BUILDER_SID,
                                                 HIERARCHY_LEVEL_DEFINITION_SID,
                                                 LEVEL_NO,
                                                 LEVEL_NAME,
                                                 LEVEL_VALUES,
                                                 PARENT_NODE,
                                                 HIERARCHY_NO)
                                    SELECT A.MARKET_TYPE_SID,
                                           A.CONTRACT_MASTER_SID,
                                           A.COMPANY_MASTER_SID,
                                           A.ITEM_MASTER_SID,
                                           @RELATIONSHIP_BUILDER_SID,
                                           @HIERARCHY_LEVEL_DEFINITION_SID,
                                           @LEVEL_NO,
                                           @LEVEL_NAME,
                                           CASE
                                             WHEN @LEVEL_NAME IN ( 'Market Type', 'Contract Type' ) THEN A.MARKET_TYPE_SID
                                             WHEN @LEVEL_NAME = 'Contract Holder' THEN A.CONTRACT_HOLDER_SID
                                             WHEN @LEVEL_NAME = 'Contract' THEN A.CONTRACT_MASTER_SID
                                             WHEN @LEVEL_NAME IN ( 'Trading Partner', 'Customer' ) THEN A.COMPANY_MASTER_SID
                                           END AS LEVEL_VALUES,
                                           b.parent_node,
                                           CASE
                                             WHEN op_rld.HIERARCHY_NO IS NOT NULL THEN op_rld.HIERARCHY_NO --exist in RLD for VALUES + LEVEL_NO + PARENT_NODE + Relationship_Builder_Sid
                                             ELSE
                                               CASE
                                                 WHEN op_rld2.max_hier_no IS NOT NULL THEN Stuff(@max_hier, Len(@max_hier) - @len_hier, @len_hier, Cast(Reverse(Substring(Reverse(Stuff(@max_hier, Len(@max_hier), 1, '')), 1, Charindex('.', Reverse(Stuff(@max_hier, Len(@max_hier), 1, ''))) - 1)) AS INT)
                                                                                                                                                   + Dense_rank() OVER(PARTITION BY @RELATIONSHIP_BUILDER_SID, @LEVEL_NO, b.parent_node ORDER BY CASE WHEN @LEVEL_NAME IN ('Market Type', 'Contract Type') THEN A.MARKET_TYPE_SID WHEN @LEVEL_NAME = 'Contract Holder' THEN A.CONTRACT_HOLDER_SID WHEN @LEVEL_NAME = 'Contract' THEN A.CONTRACT_MASTER_SID WHEN @LEVEL_NAME IN ('Trading Partner', 'Customer') THEN A.COMPANY_MASTER_SID END))--exist in RLD for LEVEL_NO + PARENT_NODE
                                                 ELSE B.HIERARCHY_NO + Cast(Dense_rank() OVER(PARTITION BY @RELATIONSHIP_BUILDER_SID, @LEVEL_NO, b.parent_node ORDER BY CASE WHEN @LEVEL_NAME IN ('Market Type', 'Contract Type') THEN A.MARKET_TYPE_SID WHEN @LEVEL_NAME = 'Contract Holder' THEN A.CONTRACT_HOLDER_SID WHEN @LEVEL_NAME = 'Contract' THEN A.CONTRACT_MASTER_SID WHEN @LEVEL_NAME IN ('Trading Partner', 'Customer') THEN A.COMPANY_MASTER_SID END) AS VARCHAR(10)) + '.'
                                               END
                                           END AS HIERARCHY_NO
                                    FROM   #TEMP_GLOBAL_TEMP a
                                           JOIN MYCTE B
                                             ON A.MARKET_TYPE_SID = B.MARKET_TYPE_SID
                                                AND A.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
                                                AND A.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID
                                                AND A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                           OUTER apply (SELECT rld.HIERARCHY_NO --for the matched RLD comb with formed value comb pull the existing hierarchy no itself
                                                        FROM   RELATIONSHIP_LEVEL_DEFINITION rld
                                                        WHERE  rld.RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
                                                               AND rld.LEVEL_NO = @LEVEL_NO
                                                               AND rld.PARENT_NODE = b.parent_node
                                                               AND rld.PARENT_NODE = @PARENT_NO_FIX
                                                               AND rld.RELATIONSHIP_LEVEL_VALUES = CASE
                                                                                                     WHEN @LEVEL_NAME IN ( 'Market Type', 'Contract Type' ) THEN A.MARKET_TYPE_SID
                                                                                                     WHEN @LEVEL_NAME = 'Contract Holder' THEN A.CONTRACT_HOLDER_SID
                                                                                                     WHEN @LEVEL_NAME = 'Contract' THEN A.CONTRACT_MASTER_SID
                                                                                                     WHEN @LEVEL_NAME IN ( 'Trading Partner', 'Customer' ) THEN A.COMPANY_MASTER_SID
                                                                                                   END) op_rld
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
                          END
                    END --END OF @LEVEL_NO >=1 AND @LEVEL_VALUE_REFERENCE = 'LINKED'

                  -----------------------INSERT INTO RESULT TABLE ENDS HERE----------------------------------
                  SET @j += 1
              END --For each Hierarchy Level Definition of a Relationship_Builder_SID.
            /* SELECT DISTINCT RELATIONSHIP_BUILDER_SID,
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

            IF (@@ROWCOUNT > 0 OR @COMPANY_UPDATE >0 OR @CONTRACT_UPDATE>0) --Update version no of RELATIONSHIP_LEVEL_DEFINITION & RELATIONSHIP_BUILDER if insert succeeds
              BEGIN
                  UPDATE RELATIONSHIP_LEVEL_DEFINITION
                  SET    VERSION_NO = VERSION_NO + 1
                  WHERE  RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID

                  UPDATE RELATIONSHIP_BUILDER
                  SET    VERSION_NO = VERSION_NO + 1
                  WHERE  RELATIONSHIP_BUILDER_SID = @RELATIONSHIP_BUILDER_SID
              END

            SET @I += 1
            SET @CNT_HLD=0
            SET @J=1
        END --master while loop ends here (for each Relationship Builder)
  END



GO

