IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_DEDUCTION_HIERARCHY_DYNAMIC_ADD'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_DEDUCTION_HIERARCHY_DYNAMIC_ADD]
  END

GO
CREATE PROCEDURE [dbo].[PRC_DEDUCTION_HIERARCHY_DYNAMIC_ADD]
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
  
 


          IF Object_id('tempdb..#LEVEL') IS NOT NULL
            DROP TABLE #LEVEL

          CREATE TABLE #LEVEL
            (
               LEVEL_NAME               VARCHAR(50),
               LEVEL_NO                 INT,
               RELATIONSHIP_BUILDER_SID INT,
               DEDUCTION_RELATION       INT,
               HIERARCHY_DEFINATION_SID INT
            )

          IF Object_id('tempdb..#NEWLEVEL') IS NOT NULL
            DROP TABLE #NEWLEVEL

          CREATE TABLE #NEWLEVEL
            (
               LEVEL_NAME               VARCHAR(50),
               LEVEL_NO                 INT,
               RELATIONSHIP_BUILDER_SID INT,
               DEDUCTION_RELATION       INT,
               HIERARCHY_DEFINATION_SID INT
            )

          IF Object_id('tempdb..#NEWLEVEL1') IS NOT NULL
            DROP TABLE #NEWLEVEL1

          CREATE TABLE #NEWLEVEL1
            (
               LEVEL_NAME               VARCHAR(50),
               LEVEL_NO                 INT,
               RELATIONSHIP_BUILDER_SID INT,
               DEDUCTION_RELATION       INT,
               HIERARCHY_DEFINATION_SID INT
            )

          IF Object_id('tempdb..#LEVEL1') IS NOT NULL
            DROP TABLE #LEVEL1

          CREATE TABLE #LEVEL1
            (
               RELATIONSHIP_BUILDER_SID INT,
               DEDUCTION_RELATION       INT,
            )

          INSERT INTO #LEVEL1
          SELECT DISTINCT DEDUCTION_RELATION,
                          RELATIONSHIP_BUILDER_SID
          FROM   RELATIONSHIP_BUILDER
          WHERE  DEDUCTION_RELATION IN (SELECT DISTINCT RELATIONSHIP_BUILDER_SID
                                        FROM   RELATIONSHIP_LEVEL_DEFINITION
                                        WHERE  RELATIONSHIP_LEVEL_VALUES IN (SELECT DISTINCT a.ITEM_MASTER_SID
                                                                             FROM   #TEMP_DEDUCTION_HIERARCHY a)  )

          INSERT INTO #LEVEL
                      (LEVEL_NAME,
                       LEVEL_NO,
                       RELATIONSHIP_BUILDER_SID)
          SELECT b.LEVEL_NAME,
                 b.LEVEL_NO,
                 a.RELATIONSHIP_BUILDER_SID
          FROM   #LEVEL1 a
                 LEFT JOIN RELATIONSHIP_LEVEL_DEFINITION b
                        ON a.RELATIONSHIP_BUILDER_SID = b.RELATIONSHIP_BUILDER_SID

          IF Object_id('tempdb..#LEVEL_DEDUCTION_RELATION') IS NOT NULL
            DROP TABLE #LEVEL_DEDUCTION_RELATION

          CREATE TABLE #LEVEL_DEDUCTION_RELATION
            (
               RELATIONSHIP_BUILDER_SID INT,
               DEDUCTION_RELATION       INT,
            )

          IF Object_id('tempdb..#LEVEL_HIERARCHY_DEFINATION_SID') IS NOT NULL
            DROP TABLE #LEVEL_HIERARCHY_DEFINATION_SID

          CREATE TABLE #LEVEL_HIERARCHY_DEFINATION_SID
            (
               RELATIONSHIP_BUILDER_SID INT,
               DEDUCTION_RELATION       INT,
            )

          INSERT INTO #NEWLEVEL
          SELECT a.LEVEL_NAME,
                 a.LEVEL_NO,
                 a.RELATIONSHIP_BUILDER_SID,
                 b.DEDUCTION_RELATION AS DEDUCTION_RELATION,
                 b.HIERARCHY_DEFINITION_SID
          FROM   #LEVEL a
                 LEFT JOIN RELATIONSHIP_BUILDER b
                        ON a.RELATIONSHIP_BUILDER_SID = b.RELATIONSHIP_BUILDER_SID

          --WHERE  b.DEDUCTION_RELATION IS NOT NULL 
          INSERT INTO #NEWLEVEL1
          SELECT a.LEVEL_NAME,
                 a.LEVEL_NO,
                 a.RELATIONSHIP_BUILDER_SID,
                 b.deduction_relation,
                 a.HIERARCHY_DEFINATION_SID
          FROM   #NEWLEVEL a
                 LEFT JOIN #LEVEL1 b
                        ON a.RELATIONSHIP_BUILDER_SID = b.RELATIONSHIP_BUILDER_SID

          IF Object_id('tempdb..#RELATION_SHIP_BUILDER_SID') IS NOT NULL
            DROP TABLE #RELATION_SHIP_BUILDER_SID

          CREATE TABLE #RELATION_SHIP_BUILDER_SID
            (
               ROWNUMBER                INT IDENTITY(1, 1) NOT NULL,
               RELATIONSHIP_BUILDER_SID INT,
               DEDUCTION_RELATION_SID   INT
            )

          INSERT INTO #RELATION_SHIP_BUILDER_SID
                      (RELATIONSHIP_BUILDER_SID,
                       DEDUCTION_RELATION_SID)
          SELECT DISTINCT RELATIONSHIP_BUILDER_SID,
                          DEDUCTION_RELATION
          FROM   #NEWLEVEL1

          DECLARE @LEVEL_NAME  VARCHAR(50),
                  @COLUMN_NAME VARCHAR(50),
                  @ITERATION   VARCHAR(10),
                  @SQL         NVARCHAR(2000),
                  @rowcnt      INT
          DECLARE @intFlag                  INT,
                  @sql1                     VARCHAR(max),
                  @RELATIONSHIP_BUILDER_SID NVARCHAR(max),
                  @DEDUCTION_RELATION_SID   NVARCHAR(max),
                  @VERSION                  NVARCHAR(max),
                  @maxcount                 INT,
                  @query                    VARCHAR(max),
                  @sid                      INT

          SET @intFlag = 1
          SET @maxcount =(SELECT Count(*)
                          FROM   #RELATION_SHIP_BUILDER_SID)

          DECLARE @xx INT

          SET @xx = 0

          WHILE ( @intFlag < @maxcount + 1 )
            BEGIN
                IF Object_id('tempdb..#FINAL_RELATIONSHIP_LEVEL_DEFINITION') IS NOT NULL
                  DROP TABLE #FINAL_RELATIONSHIP_LEVEL_DEFINITION

                CREATE TABLE #FINAL_RELATIONSHIP_LEVEL_DEFINITION
                  (
                     RELATIONSHIP_BUILDER_SID       INT,
                     HIERARCHY_LEVEL_DEFINITION_SID INT,
                     RELATIONSHIP_LEVEL_VALUES      VARCHAR(100),
                     LEVEL_NO                       VARCHAR(100),
                     LEVEL_NAME                     VARCHAR(100),
                     PARENT_NODE                    VARCHAR(100),
                     HIERARCHY_NO                   VARCHAR(100),
                     CREATED_BY                     INT,
                     CREATED_DATE                   DATETIME,
                     MODIFIED_BY                    INT,
                     MODIFIED_DATE                  DATETIME,
                     VERSION_NO                     INT
                  )

                IF Object_id('tempdb..#INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION') IS NOT NULL
                  DROP TABLE #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION

                CREATE TABLE #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION
                  (
                     RELATIONSHIP_BUILDER_SID       INT,
                     HIERARCHY_LEVEL_DEFINITION_SID INT,
                     RELATIONSHIP_LEVEL_VALUES      VARCHAR(100),
                     LEVEL_NO                       VARCHAR(100),
                     LEVEL_NAME                     VARCHAR(100),
                     PARENT_NODE                    VARCHAR(100),
                     HIERARCHY_NO                   VARCHAR(100),
                     CREATED_BY                     INT,
                     CREATED_DATE                   DATETIME,
                     MODIFIED_BY                    INT,
                     MODIFIED_DATE                  DATETIME,
                     VERSION_NO                     INT
                  )

                --DECLARE @RELATIONSHIP_BUILDER_SID VARCHAR(max),@intFlag VARCHAR =9
                SET @RELATIONSHIP_BUILDER_SID= 'select RELATIONSHIP_BUILDER_SID  from #RELATION_SHIP_BUILDER_SID   where ROWNUMBER ='
                                               + Cast(@intFlag AS VARCHAR)

                --select @RELATIONSHIP_BUILDER_SID
                IF Object_id('tempdb..#tem') IS NOT NULL
                  DROP TABLE #tem

                CREATE TABLE #tem
                  (
                     RELATIONSHIP_BUILDER_SID INT
                  )

                INSERT INTO #tem
                EXEC (@RELATIONSHIP_BUILDER_SID)

                --DECLARE @DEDUCTION_RELATION_SID VARCHAR(max),@intFlag VARCHAR =9,@VERSION VARCHAR(MAX)
                SET @DEDUCTION_RELATION_SID= 'select DEDUCTION_RELATION_SID  from #RELATION_SHIP_BUILDER_SID   where ROWNUMBER ='
                                             + Cast(@intFlag AS VARCHAR)

                --select @RELATIONSHIP_BUILDER_SID
                IF Object_id('tempdb..#tem1') IS NOT NULL
                  DROP TABLE #tem1

                CREATE TABLE #tem1
                  (
                     DEDUCTION_RELATION_SID INT
                  )

                INSERT INTO #tem1
                EXEC (@DEDUCTION_RELATION_SID)

                SET @VERSION= (SELECT VERSION_NO
                               FROM   RELATIONSHIP_BUILDER
                               WHERE  RELATIONSHIP_BUILDER_SID = (SELECT DEDUCTION_RELATION_SID
                                                                  FROM   #TEM1))

                --SELECT @VERSION
                IF Object_id('tempdb..#RELATIONSHIP_LEVEL_VALUES') IS NOT NULL
                  DROP TABLE #RELATIONSHIP_LEVEL_VALUES

                CREATE TABLE #RELATIONSHIP_LEVEL_VALUES
                  (
                     ITEM_MASTER_SID INT
                  )

                IF 3 = (SELECT Count (DISTINCT LEVEL_NO)
                        FROM   #NEWLEVEL1
                        WHERE  RELATIONSHIP_BUILDER_SID = (SELECT RELATIONSHIP_BUILDER_SID
                                                           FROM   #tem))
                  BEGIN
                      INSERT INTO #RELATIONSHIP_LEVEL_VALUES
                      SELECT DISTINCT RELATIONSHIP_LEVEL_VALUES
                      FROM   RELATIONSHIP_LEVEL_DEFINITION
                      WHERE  RELATIONSHIP_BUILDER_SID = (SELECT RELATIONSHIP_BUILDER_SID
                                                         FROM   #tem)
                             AND LEVEL_NAME = 'ndc'
                  END
                ELSE IF 2 = (SELECT Count (DISTINCT LEVEL_NO)
                        FROM   #NEWLEVEL1
                        WHERE  RELATIONSHIP_BUILDER_SID = (SELECT RELATIONSHIP_BUILDER_SID
                                                           FROM   #tem))
                  BEGIN
                      IF EXISTS (SELECT 1
                                 FROM   #NEWLEVEL1
                                 WHERE  RELATIONSHIP_BUILDER_SID = (SELECT RELATIONSHIP_BUILDER_SID
                                                                    FROM   #tem)
                                        AND level_name = 'ndc')
                        BEGIN
                            --IF Object_id('tempdb..#RELATIONSHIP_LEVEL_VALUES') IS NOT NULL
                            --  DROP TABLE #RELATIONSHIP_LEVEL_VALUES
                            --CREATE TABLE #RELATIONSHIP_LEVEL_VALUES
                            --  (
                            --     ITEM_MASTER_SID INT
                            --  )
                            INSERT INTO #RELATIONSHIP_LEVEL_VALUES
                            SELECT DISTINCT RELATIONSHIP_LEVEL_VALUES
                            FROM   RELATIONSHIP_LEVEL_DEFINITION
                            WHERE  RELATIONSHIP_BUILDER_SID = (SELECT RELATIONSHIP_BUILDER_SID
                                                               FROM   #tem)
                                   AND LEVEL_NAME = 'ndc'
                        END

                      IF EXISTS (SELECT 1
                                 FROM   #NEWLEVEL1
                                 WHERE  RELATIONSHIP_BUILDER_SID = (SELECT RELATIONSHIP_BUILDER_SID
                                                                    FROM   #tem)
                                        AND level_name = 'BRAND')
                        BEGIN
                            INSERT INTO #RELATIONSHIP_LEVEL_VALUES
                            SELECT DISTINCT ITEM_MASTER_SID
                            FROM   ITEM_MASTER
                            WHERE  BRAND_MASTER_SID IN (SELECT BRAND_MASTER_SID
                                                        FROM   BRAND_MASTER
                                                        WHERE  BRAND_MASTER_SID IN (SELECT DISTINCT RELATIONSHIP_LEVEL_VALUES
                                                                                    FROM   RELATIONSHIP_LEVEL_DEFINITION
                                                                                    WHERE  RELATIONSHIP_BUILDER_SID = (SELECT RELATIONSHIP_BUILDER_SID
                                                                                                                       FROM   #tem)
                                                                                           AND LEVEL_NAME = 'Brand'))
                        END
                  END
                ELSE IF 1 = (SELECT Count (DISTINCT LEVEL_NO)
                        FROM   #NEWLEVEL1
                        WHERE  RELATIONSHIP_BUILDER_SID = (SELECT RELATIONSHIP_BUILDER_SID
                                                           FROM   #tem))
                  BEGIN
                      INSERT INTO #RELATIONSHIP_LEVEL_VALUES
                      SELECT DISTINCT IM.ITEM_NAME
                      FROM   ITEM_MASTER IM
                             JOIN BRAND_MASTER B1
                               ON B1.BRAND_MASTER_SID = IM.BRAND_MASTER_SID
                             JOIN GL_COST_CENTER_MASTER GLC
                               ON GLC.NDC8 = IM.NDC8
                             JOIN COMPANY_MASTER CM
                               ON CM.COMPANY_ID = GLC.COMPANY_CODE
                      WHERE  IM.ITEM_NAME IS NOT NULL
                             --                              AND IM.ITEM_MASTER_SID in (128)
                             AND CM.COMPANY_MASTER_SID IN (SELECT DISTINCT RELATIONSHIP_LEVEL_VALUES
                                                           FROM   RELATIONSHIP_LEVEL_DEFINITION
                                                           WHERE  RELATIONSHIP_BUILDER_SID = (SELECT RELATIONSHIP_BUILDER_SID
                                                                                              FROM   #tem)
                                                                  AND LEVEL_NAME = 'Company')
                  END

                IF Object_id('tempdb..#udc') IS NOT NULL
                  DROP TABLE #udc

                CREATE TABLE #udc
                  (
                     HELPER_TABLE_SID INT,
                     DESCRIPTION      VARCHAR(50),
                     LIST_NAME        VARCHAR(50)
                  )

                INSERT INTO #udc
                SELECT HELPER_TABLE_SID,
                       DESCRIPTION,
                       LIST_NAME
                FROM   HELPER_TABLE
                WHERE  DESCRIPTION IN ( 'UDC1', 'UDC2', 'UDC3', 'UDC4',
                                        'UDC5', 'UDC6' )
                       AND LIST_NAME IN ( 'RS_UDC1', 'RS_UDC2', 'RS_UDC3', 'RS_UDC4',
                                          'RS_UDC5', 'RS_UDC6' )

                IF Object_id('tempdb..#TEMP_DEDUCTION') IS NOT NULL
                  DROP TABLE #TEMP_DEDUCTION

                CREATE TABLE #TEMP_DEDUCTION
                  (
                     RS_CONTRACT_SID          INT,
                     RS_ID                    VARCHAR(50),
                     REBATE_SCHEDULE_CATEGORY INT,
                     REBATE_SCHEDULE_TYPE     INT,
                     REBATE_PROGRAM_TYPE      INT,
                     UDC1                     INT,
                     UDC2                     INT,
                     UDC3                     INT,
                     UDC4                     INT,
                     UDC5                     INT,
                     UDC6                     INT
                  )

               INSERT INTO #TEMP_DEDUCTIOn
                SELECT DISTINCT c.RS_CONTRACT_SID,
                                C.RS_ID,
                                C.RS_CATEGORY,
                                C.RS_TYPE,
                                C.REBATE_PROGRAM_TYPE,
                                Isnull(CASE
                                         WHEN ( U.UDC1  = 0 or U.UDC1  is null) THEN (SELECT HELPER_TABLE_SID
                                                                   FROM   #udc
                                                                   WHERE  DESCRIPTION = 'UDC1'
                                                                          AND LIST_NAME = 'RS_UDC1')
                                       END, U.UDC1) AS UDC1,
                                Isnull(CASE
                                         WHEN ( U.UDC2  = 0 or U.UDC2  is null) THEN (SELECT HELPER_TABLE_SID
                                                                   FROM   #udc
                                                                   WHERE  DESCRIPTION = 'udc2'
                                                                          AND LIST_NAME = 'RS_UDC2')
                                       END, U.UDC2  ) AS udc2,
                                Isnull(CASE
                                         WHEN ( U.UDC3  = 0 or U.UDC3  is null) THEN (SELECT HELPER_TABLE_SID
                                                                   FROM   #udc
                                                                   WHERE  DESCRIPTION = 'UDC3'
                                                                          AND LIST_NAME = 'RS_UDC3')
                                       END, U.UDC3) AS UDC3,
                                Isnull(CASE
                                         WHEN ( U.UDC4  = 0 or U.UDC4  is null) THEN (SELECT HELPER_TABLE_SID
                                                                   FROM   #udc
                                                                   WHERE  DESCRIPTION = 'UDC4'
                                                                          AND LIST_NAME = 'RS_UDC4')
                                       END, U.UDC4) AS UDC4,
                                Isnull(CASE
                                         WHEN ( U.UDC5  = 0 or U.UDC5  is null) THEN (SELECT HELPER_TABLE_SID
                                                                   FROM   #udc
                                                                   WHERE  DESCRIPTION = 'UDC5'
                                                                          AND LIST_NAME = 'RS_UDC5')
                                       END, U.UDC5) AS UDC5,
                                Isnull(CASE
                                         WHEN ( U.UDC6  = 0 or U.UDC6  is null)  THEN (SELECT HELPER_TABLE_SID
                                                                   FROM   #udc
                                                                   WHERE  DESCRIPTION = 'UDC6'
                                                                          AND LIST_NAME = 'RS_UDC6')
                                       END, U.UDC6) AS UDC6
                FROM   #RELATIONSHIP_LEVEL_VALUES A
                       LEFT JOIN RS_CONTRACT_DETAILS B
                              ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                 AND B.INBOUND_STATUS <> 'D'
                       LEFT JOIN RS_CONTRACT C
                              ON C.RS_CONTRACT_SID = B.RS_CONTRACT_SID
                                 AND C.INBOUND_STATUS <> 'D'
                       LEFT JOIN UDCS U
                              ON U.MASTER_SID = B.RS_CONTRACT_SID AND U.MASTER_TYPE='RS_CONTRACT'
where c.RS_ID is not null 
                ----------------------------------------------
                DECLARE @COLUMN_NAME1 VARCHAR(50),
                        @COLUMN_NAME2 VARCHAR(50),
                        @HIERARCHY    VARCHAR(50)='DeductionHierarchy'

                SET @iteration = 1

                WHILE( @iteration <= 10 )
                  BEGIN
                      IF( @ITERATION = 1 )
                        BEGIN
                            SET @LEVEL_NAME='Schedule Category'
                            SET @COLUMN_NAME='REBATE_SCHEDULE_CATEGORY'
                        END
                      ELSE IF ( @ITERATION = 2 )
                        BEGIN
                            SET @LEVEL_NAME='Schedule Type'
                            --SET @COLUMN_NAME='REBATE_SCHEDULE_TYPE'
                            SET @COLUMN_NAME1='REBATE_SCHEDULE_CATEGORY'
                            SET @COLUMN_NAME2='REBATE_SCHEDULE_TYPE'
                        END
                      ELSE IF ( @ITERATION = 3 )
                        BEGIN
                            SET @LEVEL_NAME='Program Type'
                            --SET @COLUMN_NAME='REBATE_PROGRAM_TYPE'
                            SET @COLUMN_NAME1='REBATE_SCHEDULE_TYPE'
                            SET @COLUMN_NAME2='REBATE_PROGRAM_TYPE'
                        END
                      ELSE IF ( @ITERATION = 4 )
                        BEGIN
                            SET @LEVEL_NAME='UDC 1'
                            --SET @COLUMN_NAME='UDC1'
                            SET @COLUMN_NAME1='REBATE_PROGRAM_TYPE'
                            SET @COLUMN_NAME2='UDC1'
                        END
                      ELSE IF ( @ITERATION = 5 )
                        BEGIN
                            SET @LEVEL_NAME='UDC 2'
                            --SET @COLUMN_NAME='UDC2'
                            SET @COLUMN_NAME1='UDC1'
                            SET @COLUMN_NAME2='UDC2'
                        END
                      ELSE IF ( @ITERATION = 6 )
                        BEGIN
                            SET @LEVEL_NAME='UDC 3'
                            --SET @COLUMN_NAME='UDC3'
                            SET @COLUMN_NAME1='UDC2'
                            SET @COLUMN_NAME2='UDC3'
                        END
                      ELSE IF ( @ITERATION = 7 )
                        BEGIN
                            SET @LEVEL_NAME='UDC 4'
                            --SET @COLUMN_NAME='UDC4'
                            SET @COLUMN_NAME1='UDC3'
                            SET @COLUMN_NAME2='UDC4'
                        END
                      ELSE IF ( @ITERATION = 8 )
                        BEGIN
                            SET @LEVEL_NAME='UDC 5'
                            --SET @COLUMN_NAME='UDC5'
                            SET @COLUMN_NAME1='UDC4'
                            SET @COLUMN_NAME2='UDC5'
                        END
                      ELSE IF ( @ITERATION = 9 )
                        BEGIN
                            SET @LEVEL_NAME='UDC 6'
                            --SET @COLUMN_NAME='UDC6'
                            SET @COLUMN_NAME1='UDC5'
                            SET @COLUMN_NAME2='UDC6'
                        END
                      ELSE IF ( @ITERATION = 10 )
                        BEGIN
                            SET @LEVEL_NAME='Schedule ID'
                            --SET @COLUMN_NAME='RS_CONTRACT_SID'
                            SET @COLUMN_NAME1='UDC6'
                            SET @COLUMN_NAME2='RS_CONTRACT_SID'
                        END

                      IF( @ITERATION = 1 )
                        BEGIN
                            SET @SQL ='INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION select 
 a.RELATIONSHIP_BUILDER_SID
,a.HIERARCHY_LEVEL_DEFINITION_SID
,a.RELATIONSHIP_LEVEL_VALUES
,a.LEVEL_NO
,a.LEVEL_NAME
,case when a.LEVEL_NO=1 then ''0'' else CONCAT(FINAL.LEVEL_NO,''~'',FINAL.RELATIONSHIP_LEVEL_VALUES ) end AS PARENT_NODE
,case when a.LEVEL_NO=1 then a.HIERARCHY_NO 
else  CONCAT(FINAL.HIERARCHY_NO,'''', DENSE_RANK () OVER( ORDER BY A.RELATIONSHIP_LEVEL_VALUES  ))+''.'' end AS HIERARCHY_NO
,a.CREATED_BY
,a.CREATED_DATE
,a.MODIFIED_BY
,a.MODIFIED_DATE
,b.VERSION_NO+1 as VERSION_NO
       
       
       from (
       
       SELECT         
       DEDUCTION_RELATION as RELATIONSHIP_BUILDER_SID,

       HIERARCHY_LEVEL_DEFINITION_SID,

       ' + @COLUMN_NAME + '  as RELATIONSHIP_LEVEL_VALUES,
       ' + @ITERATION
                                      + ' AS LEVEL_NO,''' + @LEVEL_NAME
                                      + ''' AS LEVEL_NAME,0 as PARENT_NODE,
              concat(DEDUCTION_RELATION ,''-'', ROW_NUMBER () OVER(ORDER BY '
                                      + @COLUMN_NAME
                                      + '))+''.'' as HIERARCHY_NO,
              1 as CREATED_BY
,getdate() as CREATED_DATE
,1 as MODIFIED_BY
,getdate() as MODIFIED_DATE

       FROM #TEMP_DEDUCTION T1 ,#NEWLEVEL1  T2
       JOIN 
                HIERARCHY_LEVEL_DEFINITION  B 
                ON  T2.HIERARCHY_DEFINATION_SID=B.HIERARCHY_DEFINITION_SID WHERE B.LEVEL_NO='
                                      + @ITERATION + '
                 AND T2.RELATIONSHIP_BUILDER_SID=(SELECT RELATIONSHIP_BUILDER_SID FROM #tem)
       GROUP BY ' + @COLUMN_NAME
                                      + ',DEDUCTION_RELATION,HIERARCHY_DEFINATION_SID,HIERARCHY_LEVEL_DEFINITION_SID


       )a left join RELATIONSHIP_BUILDER b on  a.RELATIONSHIP_BUILDER_SID=b.RELATIONSHIP_BUILDER_SID
       left join #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION FINAL ON FINAL.RELATIONSHIP_BUILDER_SID=A.RELATIONSHIP_BUILDER_SID
       WHERE A. RELATIONSHIP_LEVEL_VALUES IS NOT NULL AND A. RELATIONSHIP_LEVEL_VALUES<>0'

                            EXEC (@SQL)

                            SET @SQL ='INSERT INTO #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION select 
 a.RELATIONSHIP_BUILDER_SID
,a.HIERARCHY_LEVEL_DEFINITION_SID
,a.RELATIONSHIP_LEVEL_VALUES
,a.LEVEL_NO
,a.LEVEL_NAME
,case when a.LEVEL_NO=1 then ''0'' else CONCAT(FINAL.LEVEL_NO,''~'',FINAL.RELATIONSHIP_LEVEL_VALUES ) end AS PARENT_NODE
,case when a.LEVEL_NO=1 then a.HIERARCHY_NO 
else  CONCAT(FINAL.HIERARCHY_NO,'''', DENSE_RANK () OVER( ORDER BY A.RELATIONSHIP_LEVEL_VALUES  ))+''.'' end AS HIERARCHY_NO
,a.CREATED_BY
,a.CREATED_DATE
,a.MODIFIED_BY
,a.MODIFIED_DATE
,b.VERSION_NO+1 as VERSION_NO
       
       
       from (
       
       SELECT         
       DEDUCTION_RELATION as RELATIONSHIP_BUILDER_SID,

       HIERARCHY_LEVEL_DEFINITION_SID,

       ' + @COLUMN_NAME + '  as RELATIONSHIP_LEVEL_VALUES,
       ' + @ITERATION
                                      + ' AS LEVEL_NO,''' + @LEVEL_NAME
                                      + ''' AS LEVEL_NAME,0 as PARENT_NODE,
              concat(DEDUCTION_RELATION ,''-'', ROW_NUMBER () OVER(ORDER BY '
                                      + @COLUMN_NAME
                                      + '))+''.'' as HIERARCHY_NO,
              1 as CREATED_BY
,getdate() as CREATED_DATE
,1 as MODIFIED_BY
,getdate() as MODIFIED_DATE

       FROM #TEMP_DEDUCTION T1 ,#NEWLEVEL1  T2
       JOIN 
                HIERARCHY_LEVEL_DEFINITION  B 
                ON  T2.HIERARCHY_DEFINATION_SID=B.HIERARCHY_DEFINITION_SID WHERE B.LEVEL_NO='
                                      + @ITERATION + '
                 AND T2.RELATIONSHIP_BUILDER_SID=(SELECT RELATIONSHIP_BUILDER_SID FROM #tem)
       GROUP BY ' + @COLUMN_NAME
                                      + ',DEDUCTION_RELATION,HIERARCHY_DEFINATION_SID,HIERARCHY_LEVEL_DEFINITION_SID


       )a left join RELATIONSHIP_BUILDER b on  a.RELATIONSHIP_BUILDER_SID=b.RELATIONSHIP_BUILDER_SID
       left join #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION FINAL ON FINAL.RELATIONSHIP_BUILDER_SID=A.RELATIONSHIP_BUILDER_SID
       WHERE A. RELATIONSHIP_LEVEL_VALUES IS NOT NULL AND A. RELATIONSHIP_LEVEL_VALUES<>0'

                            EXEC(@SQL)

                            DELETE FROM #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION
                            WHERE  LEVEL_NO <> @ITERATION

                            UPDATE #FINAL_RELATIONSHIP_LEVEL_DEFINITION
                            SET    HIERARCHY_LEVEL_DEFINITION_SID = (SELECT TOP 1 HIERARCHY_LEVEL_DEFINITION_SID
                                                                     FROM   HIERARCHY_LEVEL_DEFINITION
                                                                     WHERE  LEVEL_NAME = @LEVEL_NAME)
                        END
                      ELSE IF ( @ITERATION = 10 )
                        BEGIN
                            IF Object_id('tempdb..#TEMP2') IS NOT NULL
                              DROP TABLE #TEMP2

                            CREATE TABLE #TEMP2
                              (
                                 REBATE_SCHEDULE_CATEGORY INT,
                                 REBATE_SCHEDULE_TYPE     INT,
                                 REBATE_PROGRAM_TYPE      INT,
                                 UDC6                     INT,
                                 HIERARCHY_NO             VARCHAR(100),
                                 RS_CONTRACT_SID          INT,
                                 ROWNUMBER                INT
                              )

                            INSERT INTO #TEMP2
                            SELECT a.REBATE_SCHEDULE_CATEGORY,
                                   a.REBATE_SCHEDULE_TYPE,
                                   a.REBATE_PROGRAM_TYPE,
                                   a.UDC6,
                                   a.HIERARCHY_NO,
                                   a.rs_contract_sid,
                                   Row_number ()
                                     OVER(
                                       partition BY a.UDC6, a.REBATE_SCHEDULE_CATEGORY, a.REBATE_SCHEDULE_TYPE, a.REBATE_PROGRAM_TYPE, a.HIERARCHY_NO
                                       ORDER BY a.UDC6)
                            FROM   (SELECT B.REBATE_SCHEDULE_CATEGORY,
                                           B.REBATE_SCHEDULE_TYPE,
                                           B.REBATE_PROGRAM_TYPE,
                                           B.RS_CONTRACT_SID,
                                           b.udc6,
                                           a.HIERARCHY_NO
                                    FROM   #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION A
                                           JOIN #TEMP_DEDUCTION B
                                             ON A.RELATIONSHIP_LEVEL_VALUES = B.UDC6
                                           JOIN (SELECT IRLD.HIERARCHY_NO,
                                                        Max(CASE
                                                              WHEN FRLD.LEVEL_NAME = 'SCHEDULE CATEGORY' THEN FRLD.RELATIONSHIP_LEVEL_VALUES
                                                            END) SCHEDULE_CATEGORY,
                                                        Max(CASE
                                                              WHEN FRLD.LEVEL_NAME = 'SCHEDULE TYPE' THEN FRLD.RELATIONSHIP_LEVEL_VALUES
                                                            END) SCHEDULE_TYPE,
                                                        Max(CASE
                                                              WHEN FRLD.LEVEL_NAME = 'PROGRAM TYPE' THEN FRLD.RELATIONSHIP_LEVEL_VALUES
                                                            END) PROGRAM_TYPE,
                                                                                                       Max(CASE
                                                              WHEN FRLD.LEVEL_NAME = 'UDC 1' THEN FRLD.RELATIONSHIP_LEVEL_VALUES
                                                            END) udc1,                     
                                                                                                       Max(CASE                                   
                                                              WHEN FRLD.LEVEL_NAME = 'udc 2' THEN FRLD.RELATIONSHIP_LEVEL_VALUES
                                                            END) udc2,                     
                                                                                                       Max(CASE                                   
                                                              WHEN FRLD.LEVEL_NAME = 'udc 3'THEN FRLD.RELATIONSHIP_LEVEL_VALUES
                                                            END) udc3,                     
                                                                                                       Max(CASE                                   
                                                              WHEN FRLD.LEVEL_NAME = 'udc 4' THEN FRLD.RELATIONSHIP_LEVEL_VALUES
                                                            END) udc4,                     
                                                                                                       Max(CASE                                   
                                                              WHEN FRLD.LEVEL_NAME = 'udc 5' THEN FRLD.RELATIONSHIP_LEVEL_VALUES
                                                            END) udc5,                     
                                                                                                       Max(CASE                                   
                                                              WHEN FRLD.LEVEL_NAME = 'udc 6' THEN FRLD.RELATIONSHIP_LEVEL_VALUES
                                                            END) udc6
                                                 FROM   #FINAL_RELATIONSHIP_LEVEL_DEFINITION FRLD
                                                        JOIN #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION IRLD
                                                          ON FRLD. LEVEL_NO <=9
                                                             AND IRLD.HIERARCHY_NO LIKE FRLD.HIERARCHY_NO + '%'
                                                 GROUP  BY IRLD.HIERARCHY_NO)c
                                             ON c.PROGRAM_TYPE = b.REBATE_PROGRAM_TYPE
                                                AND c.SCHEDULE_CATEGORY = b.REBATE_SCHEDULE_CATEGORY
                                                AND c.SCHEDULE_TYPE = b.REBATE_SCHEDULE_TYPE
                                                AND A.HIERARCHY_NO = c.HIERARCHY_NO
                                                                                  and c.udc1 = b.udc1
                                                                                  and c.udc2 = b.udc2
                                                                                  and c.udc3 = b.udc3
                                                                                  and c.udc4 = b.udc4
                                                                                  and c.udc5 = b.udc5
                                                                                  and c.udc6 = b.udc6
                                                                                  
                                                                                  
                                                                                  )a

                            INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION
                                        (RELATIONSHIP_BUILDER_SID,
                                         HIERARCHY_LEVEL_DEFINITION_SID,
                                         RELATIONSHIP_LEVEL_VALUES,
                                         LEVEL_NO,
                                         LEVEL_NAME,
                                         PARENT_NODE,
                                         HIERARCHY_NO,
                                         CREATED_BY,
                                         CREATED_DATE,
                                         MODIFIED_BY,
                                         MODIFIED_DATE)
                            SELECT DISTINCT b.RELATIONSHIP_BUILDER_SID,
                                            d.HIERARCHY_LEVEL_DEFINITION_SID,
                                            a.RS_CONTRACT_SID,
                                            '10'                                          AS level_no,
                                            'Schedule ID'                                  AS LEVEL_NAME,
                                            B.LEVEL_NO + '~' + B.RELATIONSHIP_LEVEL_VALUES AS PARENT_NODE,
                                            Cast(A.HIERARCHY_NO AS VARCHAR) + '.'
                                            + Cast(A.ROWNUMBER AS VARCHAR) + '.'           AS HIERARCHY_NO,
                                            1                                              AS CREATED_BY,
                                            Getdate()                                      AS CREATED_DATE,
                                            1                                              AS MODIFIED_BY,
                                            Getdate()                                      AS MODIFIED_DATE
                            FROM   #TEMP2 a
                                   LEFT JOIN #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION b
                                          ON A.HIERARCHY_NO = B.HIERARCHY_NO
                                   LEFT JOIN (SELECT HIERARCHY_LEVEL_DEFINITION_SID
                                              FROM   HIERARCHY_LEVEL_DEFINITION
                                              WHERE  HIERARCHY_DEFINITION_SID = (SELECT HIERARCHY_DEFINITION_SID
                                                                                 FROM   HIERARCHY_DEFINITION
                                                                                 WHERE  HIERARCHY_NAME = 'DeductionHierarchy')
                                                     AND LEVEL_NO = 10)d
                                          ON 1 = 1
                        END
                      ELSE
                        BEGIN
                            IF Object_id('tempdb..#TEMP1') IS NOT NULL
                              DROP TABLE #TEMP1

                            CREATE TABLE #TEMP1
                              (
                                 column1 INT,
                                 column2 INT,
                                 column3 INT
                              )

                            SET @SQL =' insert into  #TEMP1
select 
a.' + @COLUMN_NAME1 + ',
a.' + @COLUMN_NAME2
                                      + ',
ROW_NUMBER () OVER(partition by a.'
                                      + @COLUMN_NAME1 + ' ORDER BY a.' + @COLUMN_NAME1
                                      + ')
from
(
select 
distinct b.' + @COLUMN_NAME1 + ',
b.'
                                      + @COLUMN_NAME2
                                      + '
from #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION a left join
#TEMP_DEDUCTION b on a.RELATIONSHIP_LEVEL_VALUES=b.'
                                      + @COLUMN_NAME1 + '
)a'

                            EXEC (@SQL)

                            SET @SQL='INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION(RELATIONSHIP_BUILDER_SID,HIERARCHY_LEVEL_DEFINITION_SID, RELATIONSHIP_LEVEL_VALUES,
LEVEL_NO,LEVEL_NAME,PARENT_NODE,HIERARCHY_NO,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE

)

SELECT  
 A.RELATIONSHIP_BUILDER_SID
,A.HIERARCHY_LEVEL_DEFINITION_SID
,A.RELATIONSHIP_LEVEL_VALUES
,A.LEVEL_NO
,A.LEVEL_NAME
,B.LEVEL_NO+''~''+B.RELATIONSHIP_LEVEL_VALUES AS PARENT_NODE
  ,A.HIERARCHY_NO+''.''
,            1 as CREATED_BY
,getdate() as CREATED_DATE
,1 as MODIFIED_BY
,getdate() as MODIFIED_DATE

FROM 
 (
select DISTINCT a.RELATIONSHIP_BUILDER_SID
, d.HIERARCHY_LEVEL_DEFINITION_SID
,b.column2 AS RELATIONSHIP_LEVEL_VALUES,
' + @iteration + ' AS LEVEL_NO,
''' + @LEVEL_NAME
                                     + ''' AS LEVEL_NAME ,
A.HIERARCHY_NO+CAST(B.COLUMN3 AS VARCHAR) AS HIERARCHY_NO,
A.HIERARCHY_NO AS ORIGINAL_HIERARCHY_NO

  from #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION a
left join #TEMP1 b on a.RELATIONSHIP_LEVEL_VALUES=b.column1
  INNER join #NEWLEVEL1 c on c.DEDUCTION_RELATION=a.RELATIONSHIP_BUILDER_SID
  AND C.RELATIONSHIP_BUILDER_SID= (SELECT RELATIONSHIP_BUILDER_SID FROM #TEM)
    left join (select HIERARCHY_LEVEL_DEFINITION_SID from HIERARCHY_LEVEL_DEFINITION where  HIERARCHY_DEFINITION_SID =(
select HIERARCHY_DEFINITION_SID from HIERARCHY_DEFINITION where HIERARCHY_NAME='''
                                     + @HIERARCHY + ''') and LEVEL_NO=' + @iteration
                                     + '

)d 
on 1=1)A LEFT JOIN  #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION B
ON A.ORIGINAL_HIERARCHY_NO=B.HIERARCHY_NO'

                            EXEC(@SQL)

                            SET @SQL='INSERT INTO #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION(RELATIONSHIP_BUILDER_SID,HIERARCHY_LEVEL_DEFINITION_SID, RELATIONSHIP_LEVEL_VALUES,
LEVEL_NO,LEVEL_NAME,PARENT_NODE,HIERARCHY_NO,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE
)
SELECT  
 A.RELATIONSHIP_BUILDER_SID
,A.HIERARCHY_LEVEL_DEFINITION_SID
,A.RELATIONSHIP_LEVEL_VALUES
,A.LEVEL_NO
,A.LEVEL_NAME
,B.LEVEL_NO+''~''+B.RELATIONSHIP_LEVEL_VALUES AS PARENT_NODE
  ,A.HIERARCHY_NO+''.''
,            1 as CREATED_BY
,getdate() as CREATED_DATE
,1 as MODIFIED_BY
,getdate() as MODIFIED_DATE

FROM 
 (
select DISTINCT a.RELATIONSHIP_BUILDER_SID
, d.HIERARCHY_LEVEL_DEFINITION_SID
,b.column2 AS RELATIONSHIP_LEVEL_VALUES,
' + @iteration + ' AS LEVEL_NO,
''' + @LEVEL_NAME
                                     + ''' AS LEVEL_NAME ,
A.HIERARCHY_NO+CAST(B.COLUMN3 AS VARCHAR) AS HIERARCHY_NO,
A.HIERARCHY_NO AS ORIGINAL_HIERARCHY_NO

  from #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION a
left join #TEMP1 b on a.RELATIONSHIP_LEVEL_VALUES=b.column1
  INNER join #NEWLEVEL1 c on c.DEDUCTION_RELATION=a.RELATIONSHIP_BUILDER_SID
  AND C.RELATIONSHIP_BUILDER_SID= (SELECT RELATIONSHIP_BUILDER_SID FROM #TEM)
    left join (select HIERARCHY_LEVEL_DEFINITION_SID from HIERARCHY_LEVEL_DEFINITION where  HIERARCHY_DEFINITION_SID =(
select HIERARCHY_DEFINITION_SID from HIERARCHY_DEFINITION where HIERARCHY_NAME='''
                                     + @HIERARCHY + ''') and LEVEL_NO=' + @iteration
                                     + '

)d 
on 1=1)A LEFT JOIN  #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION B
ON A.ORIGINAL_HIERARCHY_NO=B.HIERARCHY_NO'

                            EXEC (@SQL)

                            DELETE FROM #INTERMEDIATE_RELATIONSHIP_LEVEL_DEFINITION
                            WHERE  LEVEL_NO <> @iteration
                        END

                      SET @iteration+=1

                      UPDATE #FINAL_RELATIONSHIP_LEVEL_DEFINITION  SET    VERSION_NO = (SELECT 
                                         COALESCE((SELECT TOP 1 VERSION_NO + 1 
                                                FROM   RELATIONSHIP_LEVEL_DEFINITION 
                                                WHERE  RELATIONSHIP_BUILDER_SID = 
                                                       (SELECT TOP 1 RELATIONSHIP_BUILDER_SID 
                                                       FROM   #FINAL_RELATIONSHIP_LEVEL_DEFINITION) 
                                                       ORDER  BY VERSION_NO DESC), 1)) 

                      RAISERROR('',10,1) WITH NOWAIT
                  END

                INSERT INTO RELATIONSHIP_LEVEL_DEFINITION
                            (RELATIONSHIP_BUILDER_SID,
                             HIERARCHY_LEVEL_DEFINITION_SID,
                             RELATIONSHIP_LEVEL_VALUES,
                             LEVEL_NO,
                             LEVEL_NAME,
                             PARENT_NODE,
                             HIERARCHY_NO,
                             CREATED_BY,
                             CREATED_DATE,
                             MODIFIED_BY,
                             MODIFIED_DATE,
                             VERSION_NO)
                SELECT RB.RELATIONSHIP_BUILDER_SID,
                       HRD.HIERARCHY_LEVEL_DEFINITION_SID,
                       RELATIONSHIP_LEVEL_VALUES,
                       FLD.LEVEL_NO,
                       FLD.LEVEL_NAME,
                       PARENT_NODE,
                       HIERARCHY_NO,
                       FLD.CREATED_BY,
                       FLD.CREATED_DATE,
                       FLD.MODIFIED_BY,
                       FLD.MODIFIED_DATE,
                       FLD.VERSION_NO
                FROM   #FINAL_RELATIONSHIP_LEVEL_DEFINITION FLD
                           JOIN HIERARCHY_LEVEL_DEFINITION HRD ON HRD.LEVEL_NAME=FLD.LEVEL_NAME
                           JOIN RELATIONSHIP_BUILDER RB ON RB.RELATIONSHIP_BUILDER_SID=FLD.RELATIONSHIP_BUILDER_SID
                           AND HRD.HIERARCHY_DEFINITION_SID=RB.HIERARCHY_DEFINITION_SID






                SET @intFlag+=1



                RAISERROR('',10,1) WITH NOWAIT

                           
            END

IF OBJECT_ID('TEMPDB..#TEMP1_DED') IS NOT NULL
       DROP TABLE #TEMP1_DED

CREATE TABLE #TEMP1_DED (
       RELATIONSHIP_BUILDER_SID INT
       ,VERSION_NO INT
       )

INSERT INTO #TEMP1_DED
SELECT RELATIONSHIP_BUILDER_SID
       ,VERSION_NO
FROM (
       SELECT *
              ,row_number() OVER (
                     PARTITION BY RELATIONSHIP_BUILDER_SID ORDER BY RELATIONSHIP_BUILDER_SID
                           ,version_no DESC
                     ) rno
       FROM (
              SELECT DISTINCT RELATIONSHIP_BUILDER_SID
                     ,VERSION_NO
              FROM RELATIONSHIP_LEVEL_DEFINITION rld
              WHERE EXISTS (
                           SELECT 1
                           FROM RELATIONSHIP_BUILDER rb
                           WHERE rb.RELATIONSHIP_BUILDER_SID = rld.RELATIONSHIP_BUILDER_SID
                                  AND rb.RELATIONSHIP_NAME LIKE '%deduction'
                           )
              ) c
       ) B
WHERE rno > 1
ORDER BY RELATIONSHIP_BUILDER_SID

DELETE A
FROM RELATIONSHIP_LEVEL_DEFINITION A
JOIN #TEMP1_DED B ON A.RELATIONSHIP_BUILDER_SID = B.RELATIONSHIP_BUILDER_SID
       AND A.VERSION_NO = B.VERSION_NO

	   UPDATE A SET A.PARENT_HIERARCHY_NO=V.C,a.VERSION_NO=a.VERSION_NO+1 FROM RELATIONSHIP_LEVEL_DEFINITION A
JOIN 
(
SELECT R1.RELATIONSHIP_LEVEL_SID,CONCAT(R1.RELATIONSHIP_BUILDER_SID,B.PARENT_HI) FROM RELATIONSHIP_LEVEL_DEFINITION  R1
CROSS APPLY 
(
SELECT '~'+RELATIONSHIP_LEVEL_VALUES    FROM  RELATIONSHIP_LEVEL_DEFINITION R2 WHERE R1.RELATIONSHIP_BUILDER_SID=R2.RELATIONSHIP_BUILDER_SID AND CONVERT(INT,R1.LEVEL_NO)>=CONVERT(INT,R2.LEVEL_NO) AND R1.HIERARCHY_NO LIKE R2.HIERARCHY_NO+'%'
ORDER BY CONVERT(INT,LEVEL_NO)
FOR XML PATH('')

)B(PARENT_HI)
)V(A,C) 
ON V.A=A.RELATIONSHIP_LEVEL_SID 

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





