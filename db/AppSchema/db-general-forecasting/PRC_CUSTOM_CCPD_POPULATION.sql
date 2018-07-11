IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_CUSTOM_CCPD_POPULATION'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_CUSTOM_CCPD_POPULATION]
  END

GO

CREATE PROCEDURE [dbo].[PRC_CUSTOM_CCPD_POPULATION] (@CUST_VIEW_MASTER_SID INT)
AS
    SET NOCOUNT ON

  BEGIN
      DECLARE @SQL NVARCHAR(MAX)

      BEGIN TRY
          DELETE FROM CUSTOM_CCP_SALES
          WHERE  CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID

          IF Object_id('TEMPDB..#PARENT_HIERARCHY') IS NOT NULL
            DROP TABLE #PARENT_HIERARCHY

          CREATE TABLE #PARENT_HIERARCHY
            (
               CCP_DETAILS_SID           INT NOT NULL,
               LEVEL_NO                  SMALLINT NOT NULL,
               RELATIONSHIP_LEVEL_VALUES VARCHAR(50),
               RS_CONTRACT_SID           INT NULL,
               PARENT_HIERARCHY_NO       VARCHAR(2000)
            )

          DECLARE @CUSTOMER_RELATIONSHIP_SID INT,
                  @PRODUCT_RELATIONSHIP_SID  INT,
                  @SCREEN_NAME               VARCHAR(100),
                  @DEDUCTION_RELATION_SID    INT

          SELECT @CUSTOMER_RELATIONSHIP_SID = CUSTOMER_RELATIONSHIP_SID,
                 @PRODUCT_RELATIONSHIP_SID = PRODUCT_RELATIONSHIP_SID,
                 @SCREEN_NAME = SCREEN_NAME
          FROM   CUST_VIEW_MASTER
          WHERE  CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID

          SELECT @DEDUCTION_RELATION_SID = RELATIONSHIP_BUILDER_SID
          FROM   RELATIONSHIP_BUILDER
          WHERE  DEDUCTION_RELATION = @PRODUCT_RELATIONSHIP_SID

          IF Object_id('TEMPDB..#T2') IS NOT NULL
            DROP TABLE #T2

          SELECT *
          INTO   #T2
          FROM   CUSTOM_CCP_DETAILS
          WHERE  CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID

          CREATE NONCLUSTERED INDEX IDX_T2_CUST_HIERARCHY_NO
            ON #T2 (CUST_HIERARCHY_NO)
            INCLUDE (CCP_DETAILS_SID)

          CREATE NONCLUSTERED INDEX IDX_T2_PROD_HIERARCHY_NO
            ON #T2 (PROD_HIERARCHY_NO)
            INCLUDE (CCP_DETAILS_SID)

          DECLARE @CUS_VERSION_NO  SMALLINT,
                  @PROD_VERSION_NO SMALLINT

          SET @CUS_VERSION_NO = (SELECT Max(CUSTOMER_VERSION)
                                 FROM   #T2)
          SET @PROD_VERSION_NO = (SELECT Max(PRODUCT_VERSION)
                                  FROM   #T2)

          IF ( @SCREEN_NAME = 'SALES' )
            BEGIN
                INSERT INTO #PARENT_HIERARCHY
                            (CCP_DETAILS_SID,
                             LEVEL_NO,
                             RELATIONSHIP_LEVEL_VALuES)
                SELECT CH.CCP_DETAILS_SID,
                       c.LEVEL_NO,
                       RELATIONSHIP_LEVEL_VALUES + '.'
                FROM   CUST_VIEW_DETAILS C
                       INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
                               ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
                                  AND RLD.RELATIONSHIP_BUILDER_SID IN ( @CUSTOMER_RELATIONSHIP_SID )
                                  AND C.CUSTOM_VIEW_MASTER_SID IN ( @CUST_VIEW_MASTER_SID )
                                  AND RLD.VERSION_NO = @CUS_VERSION_NO
                       INNER JOIN #T2 CH
                               ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + '%'
                UNION ALL
                SELECT CH.CCP_DETAILS_SID,
                       c.LEVEL_NO,
                       RELATIONSHIP_LEVEL_VALUES + '.'
                FROM   CUST_VIEW_DETAILS C
                       INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
                               ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
                                  AND RLD.RELATIONSHIP_BUILDER_SID IN ( @PRODUCT_RELATIONSHIP_SID )
                                  AND C.CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
                                  AND RLD.VERSION_NO = @PROD_VERSION_NO
                       INNER JOIN #T2 CH
                               ON CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + '%'

                INSERT INTO CUSTOM_CCP_SALES
                            (HIERARCHY_NO,
                             CCP_DETAILS_SID,
                             LEVEL_NO,
                             CUST_VIEW_MASTER_SID,
                             RELATIONSHIP_LEVEL_VALES,
                             ROWID,
                             RS_CONTRACT_SID)
                SELECT HIERARCHY_NO,
                       CCP_DETAILS_SID,
                       LEVEL_NO,
                       @CUST_VIEW_MASTER_SID,
                       RELATIONSHIP_LEVEL_VALUES,
                       Dense_rank()
                         OVER (
                           ORDER BY HIERARCHY_NO ),
                       0 AS RS_CONTRACT_SID
                FROM   (SELECT CCP_DETAILS_SID,
                               Concat (@CUST_VIEW_MASTER_SID, '-', PARENT_HIERARCHY_NO, A.RELATIONSHIP_LEVEL_VALUES + '.') AS HIERARCHY_NO,
                               LEVEL_NO,
                               RELATIONSHIP_LEVEL_VALUES
                        FROM   (SELECT CCP_DETAILS_SID,
                                       RELATIONSHIP_LEVEL_VALUES,
                                       LEVEL_NO
                                FROM   #PARENT_HIERARCHY
                                GROUP  BY LEVEL_NO,
                                          CCP_DETAILS_SID,
                                          RELATIONSHIP_LEVEL_VALUES) A
                               CROSS APPLY (SELECT RELATIONSHIP_LEVEL_VALUES + '.'
                                            FROM   (SELECT CCP_DETAILS_SID,
                                                           RELATIONSHIP_LEVEL_VALUES,
                                                           LEVEL_NO
                                                    FROM   #PARENT_HIERARCHY
                                                    GROUP  BY LEVEL_NO,
                                                              CCP_DETAILS_SID,
                                                              RELATIONSHIP_LEVEL_VALUES) B
                                            WHERE  A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                                   AND A.LEVEL_NO > B.LEVEL_NO
                                            ---AND  (A.RS_CONTRACT_SID =B.RS_CONTRACT_SID OR A.RS_CONTRACT_SID IS NULL)
                                            ORDER  BY LEVEL_NO
                                            FOR XML PATH('')) CS(PARENT_HIERARCHY_NO)) B
            END
          ELSE
            BEGIN
                IF Object_id('TEMPDB..#UDC') IS NOT NULL
                  DROP TABLE #UDC

                CREATE TABLE #UDC
                  (
                     HELPER_TABLE_SID INT,
                     DESCRIPTION      VARCHAR(50),
                     LIST_NAME        VARCHAR(50)
                  )

                INSERT INTO #UDC
                SELECT HELPER_TABLE_SID,
                       DESCRIPTION,
                       LIST_NAME
                FROM   HELPER_TABLE
                WHERE  DESCRIPTION IN ( 'UDC1', 'UDC2', 'UDC3', 'UDC4',
                                        'UDC5', 'UDC6' )
                       AND LIST_NAME IN ( 'RS_UDC1', 'RS_UDC2', 'RS_UDC3', 'RS_UDC4',
                                          'RS_UDC5', 'RS_UDC6' )

                IF Object_id('TEMPDB..#TEMP_DEDUCTION') IS NOT NULL
                  DROP TABLE #TEMP_DEDUCTION

                CREATE TABLE #TEMP_DEDUCTION
                  (
                     CCP_DETAILS_SID           INT,
                     RS_CONTRACT_SID           INT,
                     RELATIONSHIP_LEVEL_VALUES INT,
                     level_no                  SMALLINT
                  )

                INSERT INTO #TEMP_DEDUCTION
                SELECT a.CCP_DETAILS_SID,
                       a.RS_CONTRACT_SID,
                       b.RELATIONSHIP_LEVEL_VALUES,
                       b.LEVEL_NO
                FROM   (SELECT DISTINCT CCP.CCP_DETAILS_SID,
                                        B.RS_CONTRACT_SID,
                                        B.RS_ID,
                                        B.RS_CATEGORY,
                                        B.RS_TYPE,
                                        B.REBATE_PROGRAM_TYPE,
                                        Isnull(CASE
                                                 WHEN ( U.UDC1 = 0
                                                         OR U.UDC1 IS NULL ) THEN (SELECT HELPER_TABLE_SID
                                                                                   FROM   #UDC
                                                                                   WHERE  DESCRIPTION = 'UDC1'
                                                                                          AND LIST_NAME = 'RS_UDC1')
                                               END, U.UDC1)                                                                                                                                                                       AS UDC1,
                                        Isnull(CASE
                                                 WHEN ( U.UDC2 = 0
                                                         OR U.UDC2 IS NULL ) THEN (SELECT HELPER_TABLE_SID
                                                                                   FROM   #UDC
                                                                                   WHERE  DESCRIPTION = 'UDC2'
                                                                                          AND LIST_NAME = 'RS_UDC2')
                                               END, U.UDC2)                                                                                                                                                                       AS UDC2,
                                        Isnull(CASE
                                                 WHEN ( U.UDC3 = 0
                                                         OR U.UDC3 IS NULL ) THEN (SELECT HELPER_TABLE_SID
                                                                                   FROM   #UDC
                                                                                   WHERE  DESCRIPTION = 'UDC3'
                                                                                          AND LIST_NAME = 'RS_UDC3')
                                               END, U.UDC3)                                                                                                                                                                       AS UDC3,
                                        Isnull(CASE
                                                 WHEN ( U.UDC4 = 0
                                                         OR U.UDC4 IS NULL ) THEN (SELECT HELPER_TABLE_SID
                                                                                   FROM   #UDC
                                                                                   WHERE  DESCRIPTION = 'UDC4'
                                                                                          AND LIST_NAME = 'RS_UDC4')
                                               END, U.UDC4)                                                                                                                                                                       AS UDC4,
                                        Isnull(CASE
                                                 WHEN ( U.UDC5 = 0
                                                         OR U.UDC5 IS NULL ) THEN (SELECT HELPER_TABLE_SID
                                                                                   FROM   #UDC
                                                                                   WHERE  DESCRIPTION = 'UDC5'
                                                                                          AND LIST_NAME = 'RS_UDC5')
                                               END, U.UDC5)                                                                                                                                                                       AS UDC5,
                                        Isnull(CASE
                                                 WHEN ( U.UDC6 = 0
                                                         OR U.UDC6 IS NULL ) THEN (SELECT HELPER_TABLE_SID
                                                                                   FROM   #UDC
                                                                                   WHERE  DESCRIPTION = 'UDC6'
                                                                                          AND LIST_NAME = 'RS_UDC6')
                                               END, U.UDC6)                                                                                                                                                                       AS UDC6,
                                        Concat (@DEDUCTION_RELATION_SID, '-', RS_CATEGORY, '.', RS_TYPE, '.', REBATE_PROGRAM_TYPE, '.', UDC1, '.', UDC2, '.', UDC3, '.', UDC4, '.', UDC5, '.', UDC6, '.', B.RS_CONTRACT_SID, '.') AS HIERARCHY_NO
                        FROM   CUSTOM_CCP_DETAILS A
                               INNER JOIN CCP_DETAILS CCP
                                       ON CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                          AND A.CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
                               INNER JOIN RS_CONTRACT B
                                       ON CCP.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
                               INNER JOIN RS_CONTRACT_DETAILS RCD
                                       ON RCD.RS_CONTRACT_SID = B.RS_CONTRACT_SID
                                          AND RCD.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID
                                          AND B.INBOUND_STATUS <> 'D'
                                          AND RCD.INBOUND_STATUS <> 'D'
                               LEFT JOIN UDCS U
                                      ON U.MASTER_SID = B.RS_CONTRACT_SID
                                         AND U.MASTER_TYPE = 'RS_CONTRACT')a
                       CROSS APPLY( VALUES(a.RS_CATEGORY,
                                  1),
                                          (a.rs_type,
                                  2),
                                          (a.REBATE_PROGRAM_TYPE,
                                  3),
                                          (a.UDC1,
                                  4),
                                          (a.UDC2,
                                  5),
                                          (a.UDC3,
                                  6),
                                          (a.UDC4,
                                  7),
                                          (a.UDC5,
                                  8),
                                          (a.UDC6,
                                  9),
                                          (a.RS_CATEGORY,
                                  10)) B(RELATIONSHIP_LEVEL_VALUES, LEVEL_NO)

                --CREATE NONCLUSTERED INDEX IDX_T2_PROD_HIERARCHY_NO ON #TEMP_DEDUCTION(HIERARCHY_NO)   include(RS_CONTRACT_SID)
                INSERT INTO #PARENT_HIERARCHY
                            (CCP_DETAILS_SID,
                             LEVEL_NO,
                             RELATIONSHIP_LEVEL_VALUES,
                             RS_CONTRACT_SID)
                SELECT CH.CCP_DETAILS_SID,
                       c.LEVEL_NO,
                       rld.RELATIONSHIP_LEVEL_VALUES + '.',
                       RS_CONTRACT_SID
                FROM   CUST_VIEW_DETAILS C
                       INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
                               ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
                                  AND RLD.RELATIONSHIP_BUILDER_SID IN ( @CUSTOMER_RELATIONSHIP_SID )
                                  AND C.CUSTOM_VIEW_MASTER_SID IN ( @CUST_VIEW_MASTER_SID )
                                  AND RLD.VERSION_NO = @CUS_VERSION_NO
                       INNER JOIN #T2 CH
                               ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + '%'
                       INNER JOIN #TEMP_DEDUCTION TD
                               ON TD.CCP_DETAILS_SID = CH.CCP_DETAILS_SID
                                  AND TD.LEVEL_NO = 1

                INSERT INTO #PARENT_HIERARCHY
                            (CCP_DETAILS_SID,
                             LEVEL_NO,
                             RELATIONSHIP_LEVEL_VALUES,
                             RS_CONTRACT_SID)
                SELECT CH.CCP_DETAILS_SID,
                       C.LEVEL_NO,
                       RLD.RELATIONSHIP_LEVEL_VALUES + '.',
                       RS_CONTRACT_SID
                FROM   CUST_VIEW_DETAILS C
                       INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
                               ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
                                  AND RLD.RELATIONSHIP_BUILDER_SID IN ( @PRODUCT_RELATIONSHIP_SID )
                                  AND C.CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
                                  AND RLD.VERSION_NO = @PROD_VERSION_NO
                       INNER JOIN #T2 CH
                               ON CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + '%'
                       INNER JOIN #TEMP_DEDUCTION TD
                               ON TD.CCP_DETAILS_SID = CH.CCP_DETAILS_SID
                                  AND TD.LEVEL_NO = 1

                INSERT INTO #PARENT_HIERARCHY
                            (CCP_DETAILS_SID,
                             LEVEL_NO,
                             RELATIONSHIP_LEVEL_VALUES,
                             RS_CONTRACT_SID)
                SELECT CH.CCP_DETAILS_SID,
                       c.LEVEL_NO,
                       RLD.RELATIONSHIP_LEVEL_VALUES + '.',
                       pr.RS_CONTRACT_SID
                FROM   CUST_VIEW_DETAILS C
                       INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
                               ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
                                  AND RLD.RELATIONSHIP_BUILDER_SID IN ( @DEDUCTION_RELATION_SID )
                                  AND C.CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
                       INNER JOIN RELATIONSHIP_BUILDER RB
                               ON RB.RELATIONSHIP_BUILDER_SID = @DEDUCTION_RELATION_SID
                                  AND RLD.VERSION_NO = RB.VERSION_NO
                       INNER JOIN #TEMP_DEDUCTION PR
                               ON pr.level_no = rld.LEVEL_NO
                                  AND pr.RELATIONSHIP_LEVEL_VALUES = rld.RELATIONSHIP_LEVEL_VALUES
                       INNER JOIN #T2 CH
                               ON CH.CCP_DETAILS_SID = PR.CCP_DETAILS_SID
                                  AND CH.CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID

                DECLARE @I             INT,
                        @MAXIMUM_VALUE INT

                SET @I = 1
                SET @MAXIMUM_VALUE = (SELECT Max(LEVEL_NO)
                                      FROM   CUST_VIEW_DETAILS
                                      WHERE  CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID)

                IF Object_id('TEMPDB..#COLUMNS') IS NOT NULL
                  DROP TABLE #COLUMNS

                CREATE TABLE #COLUMNS
                  (
                     COLUMN_NAME VARCHAR(100),
                     LEVEL_NO    INT,
                     FORMULA     VARCHAR(1000)
                  )

                WHILE ( @I <= @MAXIMUM_VALUE )
                  BEGIN
                      INSERT INTO #COLUMNS
                      SELECT Concat ('LEVEL_', @I),
                             @I,
                             NULL

                      UPDATE #COLUMNS
                      SET    FORMULA = Stuff((SELECT '+',
                                                     COLUMN_NAME
                                              FROM   #COLUMNS
                                              WHERE  LEVEL_NO <= @I
                                              FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 1, '')
                      WHERE  LEVEL_NO = @I

                      SET @I = @I + 1;
                  END

                DECLARE @COLUMNS   NVARCHAR(MAX),
                        @LEVEL_NO  NVARCHAR(MAX),
                        @LEVEL_NO1 NVARCHAR(MAX),
                        @LEVEL_NO2 NVARCHAR(MAX)

                SELECT @COLUMNS = Stuff((SELECT ',' + Quotename(COLUMN_NAME)
                                         FROM   #COLUMNS
                                         WHERE  LEVEL_NO <= @MAXIMUM_VALUE
                                         ORDER  BY LEVEL_NO
                                         FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 1, ''),
                       @LEVEL_NO = Stuff((SELECT ',' + Quotename(LEVEL_NO)
                                          FROM   #COLUMNS
                                          WHERE  LEVEL_NO <= @MAXIMUM_VALUE
                                          ORDER  BY LEVEL_NO
                                          FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 1, ''),
                       @LEVEL_NO1 = Stuff((SELECT ','
                                                  + Concat ( 'MAX(', Quotename(LEVEL_NO), ')', ' AS LEVEL_', LEVEL_NO )
                                           FROM   #COLUMNS
                                           WHERE  LEVEL_NO <= @MAXIMUM_VALUE
                                           ORDER  BY LEVEL_NO
                                           FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 1, ''),
                       @LEVEL_NO2 = Stuff((SELECT ',' + Concat ( FORMULA, ' AS LEVEL_', LEVEL_NO )
                                           FROM   #COLUMNS
                                           WHERE  LEVEL_NO <= @MAXIMUM_VALUE
                                           ORDER  BY LEVEL_NO
                                           FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 1, '')

                IF Object_id('TEMPDB..#CCP_TABLE') IS NOT NULL
                  DROP TABLE #CCP_TABLE

                CREATE TABLE #CCP_TABLE
                  (
                     ccp_details_sid INT,
                     RS_CONTRACT_SID INT
                  )

                DECLARE @SCRIPT_PREPARE NVARCHAR(MAX);

                SET @SCRIPT_PREPARE = (SELECT 'ALTER TABLE #CCP_TABLE  ADD  '
                                              + COLUMN_NAME + ' varchar(8000) ; '
                                       FROM   #COLUMNS
                                       ORDER  BY LEVEL_NO
                                       FOR XML PATH (''))

                EXEC Sp_executesql
                  @SCRIPT_PREPARE

                SET @SQL = Concat ('
INSERT INTO #CCP_TABLE(CCP_DETAILS_SID,RS_CONTRACT_SID, '
                                   + @COLUMNS + ')
SELECT CCP_DETAILS_SID,
RS_CONTRACT_SID,
' + @LEVEL_NO2 + ' FROM (
SELECT CCP_DETAILS_SID,
RS_CONTRACT_SID,
' + @LEVEL_NO1 + '
FROM #PARENT_HIERARCHY
A
PIVOT(MAX(RELATIONSHIP_LEVEL_VALUES) FOR LEVEL_NO IN (
' + @LEVEL_NO
                                   + '
)) PT
GROUP BY  CCP_DETAILS_SID,
RS_CONTRACT_SID)A
', '')

                EXEC Sp_executesql
                  @SQL

                DECLARE @var1 VARCHAR(1000)

                SET @var1= 'case'
                           + (SELECT ' ' + 'when A.level_no ='
                                     + CONVERT(VARCHAR(100), level_no)
                                     + ' then level_'
                                     + CONVERT(VARCHAR(100), level_no)
                              FROM   #COLUMNS
                              FOR xml path(''))
                           + ' end'
                SET @SQL=Concat('
INSERT INTO CUSTOM_CCP_SALES (
HIERARCHY_NO,
CCP_DETAILS_SID,
LEVEL_NO,
CUST_VIEW_MASTER_SID,
RELATIONSHIP_LEVEL_VALES,
RS_CONTRACT_SID,
ROWID
)
SELECT HIERARCHY_NO,
CCP_DETAILS_SID,
LEVEL_NO,
@CUST_VIEW_MASTER_SID,
RELATIONSHIP_LEVEL_VALUES,
RS_CONTRACT_SID,
DENSE_RANK() OVER (
ORDER BY HIERARCHY_NO
)
FROM (
SELECT A.CCP_DETAILS_SID,
CONCAT(
CONVERT(VARCHAR(100),@CUST_VIEW_MASTER_SID),
''-'' ,' + @var1 + ')
AS HIERARCHY_NO,
LEVEL_NO,
REPLACE(RELATIONSHIP_LEVEL_VALUES,''.'','''') RELATIONSHIP_LEVEL_VALUES,
a.RS_CONTRACT_SID
FROM #PARENT_HIERARCHY A
JOIN #CCP_TABLE B
ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
AND (
A.RS_CONTRACT_SID = B.RS_CONTRACT_SID
--OR A.RS_CONTRACT_SID IS NULL
)
) B', '')

                EXEC Sp_executesql
                  @SQL,
                  N'@CUST_VIEW_MASTER_SID INT',
                  @CUST_VIEW_MASTER_SID=@CUST_VIEW_MASTER_SID
            END
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
