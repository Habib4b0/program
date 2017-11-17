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
                                        WHERE  RELATIONSHIP_LEVEL_VALUES IN (SELECT DISTINCT A.ITEM_MASTER_SID
                                                                             FROM   #TEMP_DEDUCTION_HIERARCHY A)  )

          INSERT INTO #LEVEL
                      (LEVEL_NAME,
                       LEVEL_NO,
                       RELATIONSHIP_BUILDER_SID)
          SELECT B.LEVEL_NAME,
                 B.LEVEL_NO,
                 A.RELATIONSHIP_BUILDER_SID
          FROM   #LEVEL1 A
                 LEFT JOIN RELATIONSHIP_LEVEL_DEFINITION B
                        ON A.RELATIONSHIP_BUILDER_SID = B.RELATIONSHIP_BUILDER_SID

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
				DECLARE @RELATIONSHIP_BUILDER_SID_DEDU INT

			

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

					SET @RELATIONSHIP_BUILDER_SID_DEDU=( SELECT DEDUCTION_RELATION_SID FROM #tem1)

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
                      SELECT DISTINCT IM.ITEM_MASTER_SID
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

               INSERT INTO #TEMP_DEDUCTION
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
WHERE C.RS_ID IS NOT NULL 
                ----------------------------------------------
                DECLARE @COLUMN_NAME1 VARCHAR(50),
                        @COLUMN_NAME2 VARCHAR(50),
                        @HIERARCHY    VARCHAR(50)='DeductionHierarchy'


	 INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION 
(
RELATIONSHIP_BUILDER_SID  ,    
RELATIONSHIP_LEVEL_VALUES    , 
LEVEL_NO   ,                   
LEVEL_NAME   ,                 
PARENT_NODE  ,                 
HIERARCHY_NO                  
)

SELECT 
@RELATIONSHIP_BUILDER_SID_DEDU,

REBATE_SCHEDULE_CATEGORY,
1,
'Schedule Category',
0,
concat(@RELATIONSHIP_BUILDER_SID_DEDU,'-',
DENSE_RANK() OVER( ORDER BY REBATE_SCHEDULE_CATEGORY),'.' )

 FROM #TEMP_DEDUCTION
GROUP BY 

REBATE_SCHEDULE_CATEGORY
ORDER BY  REBATE_SCHEDULE_CATEGORY       



INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION 
(
RELATIONSHIP_BUILDER_SID  ,    
RELATIONSHIP_LEVEL_VALUES    , 
LEVEL_NO   ,                   
LEVEL_NAME   ,                 
PARENT_NODE  ,                 
HIERARCHY_NO                  
)

SELECT 
@RELATIONSHIP_BUILDER_SID_DEDU,

REBATE_SCHEDULE_TYPE,
2,
'Schedule Type',
concat(2,'~',REBATE_SCHEDULE_CATEGORY),
concat(@RELATIONSHIP_BUILDER_SID_DEDU,'-',
DENSE_RANK() OVER( ORDER BY REBATE_SCHEDULE_CATEGORY) ,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY ORDER BY REBATE_SCHEDULE_TYPE) ,'.') as HIERARCHY_NO

 FROM #TEMP_DEDUCTION
GROUP BY 

REBATE_SCHEDULE_CATEGORY,
REBATE_SCHEDULE_TYPE
ORDER BY  REBATE_SCHEDULE_CATEGORY  


INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION 
(
RELATIONSHIP_BUILDER_SID  ,    
RELATIONSHIP_LEVEL_VALUES    , 
LEVEL_NO   ,                   
LEVEL_NAME   ,                 
PARENT_NODE  ,                 
HIERARCHY_NO                  
)

SELECT 
@RELATIONSHIP_BUILDER_SID_DEDU,

REBATE_PROGRAM_TYPE,
3,
'Program Type',
concat(2,'~',REBATE_SCHEDULE_TYPE),
concat(@RELATIONSHIP_BUILDER_SID_DEDU,'-',
DENSE_RANK() OVER( ORDER BY REBATE_SCHEDULE_CATEGORY) ,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY ORDER BY REBATE_SCHEDULE_TYPE) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE ORDER BY  REBATE_PROGRAM_TYPE) ,'.') as HIERARCHY_NO

 FROM #TEMP_DEDUCTION
GROUP BY 

REBATE_SCHEDULE_CATEGORY,
REBATE_SCHEDULE_TYPE,
REBATE_PROGRAM_TYPE
ORDER BY  REBATE_SCHEDULE_CATEGORY   

INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION 
(
RELATIONSHIP_BUILDER_SID  ,    
RELATIONSHIP_LEVEL_VALUES    , 
LEVEL_NO   ,                   
LEVEL_NAME   ,                 
PARENT_NODE  ,                 
HIERARCHY_NO                  
)

SELECT 
@RELATIONSHIP_BUILDER_SID_DEDU,

UDC1,
4,
'UDC 1',
concat(3,'~',REBATE_PROGRAM_TYPE),
concat(@RELATIONSHIP_BUILDER_SID_DEDU,'-',
DENSE_RANK() OVER( ORDER BY REBATE_SCHEDULE_CATEGORY) ,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY ORDER BY REBATE_SCHEDULE_TYPE) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE ORDER BY  REBATE_PROGRAM_TYPE) ,'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE ORDER BY  UDC1) ,'.') as HIERARCHY_NO

 FROM #TEMP_DEDUCTION
GROUP BY 

REBATE_SCHEDULE_CATEGORY,
REBATE_SCHEDULE_TYPE,
REBATE_PROGRAM_TYPE,
udc1
ORDER BY  REBATE_SCHEDULE_CATEGORY   


INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION 
(
RELATIONSHIP_BUILDER_SID  ,    
RELATIONSHIP_LEVEL_VALUES    , 
LEVEL_NO   ,                   
LEVEL_NAME   ,                 
PARENT_NODE  ,                 
HIERARCHY_NO                  
)

SELECT 
@RELATIONSHIP_BUILDER_SID_DEDU,

UDC2,
5,
'UDC 2',
concat(4,'~',UDC2),
concat(@RELATIONSHIP_BUILDER_SID_DEDU,'-',
DENSE_RANK() OVER( ORDER BY REBATE_SCHEDULE_CATEGORY) ,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY ORDER BY REBATE_SCHEDULE_TYPE) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE ORDER BY  REBATE_PROGRAM_TYPE) ,'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE ORDER BY  UDC1) ,
'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1 ORDER BY  UDC2) ,'.') as HIERARCHY_NO

 FROM #TEMP_DEDUCTION
GROUP BY 

REBATE_SCHEDULE_CATEGORY,
REBATE_SCHEDULE_TYPE,
REBATE_PROGRAM_TYPE,
udc1,
UDC2
ORDER BY  REBATE_SCHEDULE_CATEGORY   

--6



INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION 
(
RELATIONSHIP_BUILDER_SID  ,    
RELATIONSHIP_LEVEL_VALUES    , 
LEVEL_NO   ,                   
LEVEL_NAME   ,                 
PARENT_NODE  ,                 
HIERARCHY_NO                  
)

SELECT 
@RELATIONSHIP_BUILDER_SID_DEDU,

UDC3,
6,
'UDC 3',
concat(5,'~',UDC2),
concat(@RELATIONSHIP_BUILDER_SID_DEDU,'-',
DENSE_RANK() OVER( ORDER BY REBATE_SCHEDULE_CATEGORY) ,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY ORDER BY REBATE_SCHEDULE_TYPE) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE ORDER BY  REBATE_PROGRAM_TYPE) ,'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE ORDER BY  UDC1) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1 ORDER BY  UDC2 ),'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2 ORDER BY  UDC3 )

,'.') as HIERARCHY_NO

 FROM #TEMP_DEDUCTION
GROUP BY 

REBATE_SCHEDULE_CATEGORY,
REBATE_SCHEDULE_TYPE,
REBATE_PROGRAM_TYPE,
UDC1,
UDC2,
UDC3
ORDER BY  REBATE_SCHEDULE_CATEGORY   


--7


INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION 
(
RELATIONSHIP_BUILDER_SID  ,    
RELATIONSHIP_LEVEL_VALUES    , 
LEVEL_NO   ,                   
LEVEL_NAME   ,                 
PARENT_NODE  ,                 
HIERARCHY_NO                  
)

SELECT 
@RELATIONSHIP_BUILDER_SID_DEDU,

UDC4,
7,
'UDC 4',
concat(6,'~',UDC3),
concat(@RELATIONSHIP_BUILDER_SID_DEDU,'-',
DENSE_RANK() OVER( ORDER BY REBATE_SCHEDULE_CATEGORY) ,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY ORDER BY REBATE_SCHEDULE_TYPE) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE ORDER BY  REBATE_PROGRAM_TYPE) ,'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE ORDER BY  UDC1) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1 ORDER BY  UDC2 ),'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2 ORDER BY  UDC3 )

,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2,UDC3 ORDER BY  UDC4 ),'.') as HIERARCHY_NO

 FROM #TEMP_DEDUCTION
GROUP BY 

REBATE_SCHEDULE_CATEGORY,
REBATE_SCHEDULE_TYPE,
REBATE_PROGRAM_TYPE,
UDC1,
UDC2,
UDC3,
UDC4
ORDER BY  REBATE_SCHEDULE_CATEGORY   

--8


INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION 
(
RELATIONSHIP_BUILDER_SID  ,    
RELATIONSHIP_LEVEL_VALUES    , 
LEVEL_NO   ,                   
LEVEL_NAME   ,                 
PARENT_NODE  ,                 
HIERARCHY_NO                  
)

SELECT 
@RELATIONSHIP_BUILDER_SID_DEDU,

UDC5,
8,
'UDC 5',
concat(7,'~',UDC4),
concat(@RELATIONSHIP_BUILDER_SID_DEDU,'-',
DENSE_RANK() OVER( ORDER BY REBATE_SCHEDULE_CATEGORY) ,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY ORDER BY REBATE_SCHEDULE_TYPE) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE ORDER BY  REBATE_PROGRAM_TYPE) ,'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE ORDER BY  UDC1) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1 ORDER BY  UDC2 ),'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2 ORDER BY  UDC3 )

,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2,UDC3 ORDER BY  UDC4 ),'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2,UDC3,UDC4 ORDER BY  UDC5 ),'.'
) as HIERARCHY_NO

 FROM #TEMP_DEDUCTION
GROUP BY 

REBATE_SCHEDULE_CATEGORY,
REBATE_SCHEDULE_TYPE,
REBATE_PROGRAM_TYPE,
UDC1,
UDC2,
UDC3,
UDC4,
UDC5
ORDER BY  REBATE_SCHEDULE_CATEGORY   


--9


INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION 
(
RELATIONSHIP_BUILDER_SID  ,    
RELATIONSHIP_LEVEL_VALUES    , 
LEVEL_NO   ,                   
LEVEL_NAME   ,                 
PARENT_NODE  ,                 
HIERARCHY_NO                  
)

SELECT 
@RELATIONSHIP_BUILDER_SID_DEDU,

UDC6,
9,
'UDC 6',
concat(8,'~',UDC5),
concat(@RELATIONSHIP_BUILDER_SID_DEDU,'-',
DENSE_RANK() OVER( ORDER BY REBATE_SCHEDULE_CATEGORY) ,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY ORDER BY REBATE_SCHEDULE_TYPE) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE ORDER BY  REBATE_PROGRAM_TYPE) ,'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE ORDER BY  UDC1) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1 ORDER BY  UDC2 ),'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2 ORDER BY  UDC3 )

,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2,UDC3 ORDER BY  UDC4 ),'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2,UDC3,UDC4 ORDER BY  UDC5 ),'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2,UDC3,UDC4,UDC5 ORDER BY  UDC6 ),'.'
) as HIERARCHY_NO

 FROM #TEMP_DEDUCTION
GROUP BY 

REBATE_SCHEDULE_CATEGORY,
REBATE_SCHEDULE_TYPE,
REBATE_PROGRAM_TYPE,
UDC1,
UDC2,
UDC3,
UDC4,
UDC5,
UDC6
ORDER BY  REBATE_SCHEDULE_CATEGORY   

--10



INSERT INTO #FINAL_RELATIONSHIP_LEVEL_DEFINITION 
(
RELATIONSHIP_BUILDER_SID  ,    
RELATIONSHIP_LEVEL_VALUES    , 
LEVEL_NO   ,                   
LEVEL_NAME   ,                 
PARENT_NODE  ,                 
HIERARCHY_NO                  
)

SELECT 
@RELATIONSHIP_BUILDER_SID_DEDU,

UDC6,
10,
'Schedule ID',
concat(9,'~',UDC6),
concat(@RELATIONSHIP_BUILDER_SID_DEDU,'-',
DENSE_RANK() OVER( ORDER BY REBATE_SCHEDULE_CATEGORY) ,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY ORDER BY REBATE_SCHEDULE_TYPE) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE ORDER BY  REBATE_PROGRAM_TYPE) ,'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE ORDER BY  UDC1) ,'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1 ORDER BY  UDC2 ),'.',
DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2 ORDER BY  UDC3 )

,'.',DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2,UDC3 ORDER BY  UDC4 ),'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2,UDC3,UDC4 ORDER BY  UDC5 ),'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2,UDC3,UDC4,UDC5 ORDER BY  UDC6 ),'.'
,DENSE_RANK() OVER( PARTITION BY REBATE_SCHEDULE_CATEGORY,REBATE_SCHEDULE_TYPE,REBATE_PROGRAM_TYPE,UDC1,UDC2,UDC3,UDC4,UDC5,UDC6 ORDER BY rs_contract_sid  ),'.'
) as HIERARCHY_NO

 FROM #TEMP_DEDUCTION
GROUP BY 

REBATE_SCHEDULE_CATEGORY,
REBATE_SCHEDULE_TYPE,
REBATE_PROGRAM_TYPE,
UDC1,
UDC2,
UDC3,
UDC4,
UDC5,
UDC6,
RS_CONTRACT_SID



DECLARE @VERSION_NO INT =(SELECT MAX(RELATIONSHIP_BUILDER_SID)  FROM   #FINAL_RELATIONSHIP_LEVEL_DEFINITION)

                      RAISERROR('',10,1) WITH NOWAIT
                 

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
                       @VERSION_NO
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




