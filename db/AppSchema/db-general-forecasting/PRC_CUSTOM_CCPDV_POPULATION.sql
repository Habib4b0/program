IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_CUSTOM_CCPDV_POPULATION' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [dbo].[PRC_CUSTOM_CCPDV_POPULATION] 
  END 

GO 

CREATE  PROCEDURE [dbo].[PRC_CUSTOM_CCPDV_POPULATION] (
	@CUST_VIEW_MASTER_SID INT,
	@USER_ID INT,
	@SESSION_ID VARCHAR(200)
	)
AS
SET NOCOUNT ON

BEGIN
	BEGIN TRY

		DECLARE @SQL NVARCHAR(MAX)
DECLARE @CCP_HIERARCHY VARCHAR(MAX) = CONCAT (
		'ST_CCP_HIERARCHY_',
		@USER_ID,
		'_',
		@SESSION_ID,
		'_',
		Replace(CONVERT(VARCHAR(50), getdate(), 2), '.', '')
		),
	@CUSTOM_CCP_SALES VARCHAR(MAX) = CONCAT (
		'ST_CUSTOM_CCP_SALES_',
		@USER_ID,
		'_',
		@SESSION_ID,
		'_',
		Replace(CONVERT(VARCHAR(50), getdate(), 2), '.', '')
		),
	@CUSTOM_VARIABLE_HIERARCHY VARCHAR(MAX) = CONCAT (
		'ST_CUSTOM_VARIABLE_HIERARCHY_',
		@USER_ID,
		'_',
		@SESSION_ID,
		'_',
		Replace(CONVERT(VARCHAR(50), getdate(), 2), '.', '')
		)


SET @SQL = CONCAT (
		'',
		'
		DELETE
		FROM ',
		@CUSTOM_CCP_SALES,
		'
		--WHERE CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID'
		)

EXEC Sp_executesql @SQL,
	N'@CUST_VIEW_MASTER_SID INT',
	@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID

IF Object_id('TEMPDB..#PARENT_HIERARCHY') IS NOT NULL
	DROP TABLE #PARENT_HIERARCHY

CREATE TABLE #PARENT_HIERARCHY (
	CCP_DETAILS_SID INT NOT NULL,
	
	LEVEL_NO SMALLINT NOT NULL,
	RELATIONSHIP_LEVEL_VALUES VARCHAR(50),
	RS_CONTRACT_SID INT NULL,
	PARENT_HIERARCHY_NO VARCHAR(2000)
	)

CREATE NONCLUSTERED INDEX IDX_PH ON #PARENT_HIERARCHY (LEVEL_NO) INCLUDE (
	CCP_DETAILS_SID,
	RELATIONSHIP_LEVEL_VALUES
	)

DECLARE @CUSTOMER_RELATIONSHIP_SID INT,
	@PRODUCT_RELATIONSHIP_SID INT,
	@SCREEN_NAME VARCHAR(100),
	@DEDUCTION_RELATION_SID INT

SELECT @CUSTOMER_RELATIONSHIP_SID = CUSTOMER_RELATIONSHIP_SID,
	@PRODUCT_RELATIONSHIP_SID = PRODUCT_RELATIONSHIP_SID,
	@SCREEN_NAME = SCREEN_NAME
FROM CUST_VIEW_MASTER
WHERE CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID

SELECT @DEDUCTION_RELATION_SID = RELATIONSHIP_BUILDER_SID
FROM RELATIONSHIP_BUILDER
WHERE DEDUCTION_RELATION = @PRODUCT_RELATIONSHIP_SID

IF OBJECT_ID('TEMPDB..#T2') IS NOT NULL
DROP TABLE #T2
 CREATE TABLE #T2(
	[CUSTOMER_RELATIONSHIP_BUILDER_SID] [int] NOT NULL,
	[PRODUCT_RELATIONSHIP_BUILDER_SID] [int] NOT NULL,
	[CUSTOMER_VERSION] [int] NOT NULL,
	[PRODUCT_VERSION] [int] NOT NULL,
	[CCP_DETAILS_SID] [int] NOT NULL,
	[CUST_HIERARCHY_NO] [varchar](100) NULL,
	[PROD_HIERARCHY_NO] [varchar](100) NULL,
	[CUST_VIEW_MASTER_SID] [int] NOT NULL/*,
 CONSTRAINT [PK_CUSTOM_CCP_DETAILS_CUST_VIEW_MASTER_SID_CUSTOMER_RELATIONSHIP_BUILDER_SID_PRODUCT_RELATIONSHIP_BUILDER_SID_CCP_DETAILS_SID] PRIMARY KEY CLUSTERED 
(
	[CUST_VIEW_MASTER_SID] ASC,
	[CUSTOMER_RELATIONSHIP_BUILDER_SID] ASC,
	[PRODUCT_RELATIONSHIP_BUILDER_SID] ASC,
	[CCP_DETAILS_SID] ASC
)*/
) ON [PRIMARY]
INSERT INTO #T2
SELECT *
FROM CUSTOM_CCP_DETAILS
WHERE CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
 
CREATE NONCLUSTERED INDEX IDX_T2_CUST_HIERARCHY_NO ON #T2 (CUST_HIERARCHY_NO) INCLUDE (CCP_DETAILS_SID)
 
CREATE NONCLUSTERED INDEX IDX_T2_PROD_HIERARCHY_NO ON #T2 (PROD_HIERARCHY_NO) INCLUDE (CCP_DETAILS_SID)
 

 DECLARE @CUS_VERSION_NO SMALLINT,
@PROD_VERSION_NO SMALLINT
 
SET @CUS_VERSION_NO = (
SELECT MAX(CUSTOMER_VERSION)
FROM #T2
)
SET @PROD_VERSION_NO = (
SELECT MAX(PRODUCT_VERSION)
FROM #T2
)

SET @SCREEN_NAME = CASE 
		WHEN 1 = (
				SELECT MAX(1)
				FROM CUST_VIEW_DETAILS
				WHERE CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
					AND HIERARCHY_INDICATOR = 'D'
				)
			THEN 'DISCOUNT'
		ELSE 'SALES'
		END

IF (@SCREEN_NAME = 'SALES')
BEGIN
	SET @SQL = CONCAT (
			'INSERT INTO #PARENT_HIERARCHY (
		
		CCP_DETAILS_SID,
		LEVEL_NO,
		RELATIONSHIP_LEVEL_VALuES
		)
	SELECT CH.CCP_DETAILS_SID,
		c.LEVEL_NO,
		RELATIONSHIP_LEVEL_VALUES
	FROM CUST_VIEW_DETAILS C
	INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
		ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
			AND RLD.RELATIONSHIP_BUILDER_SID IN (@CUSTOMER_RELATIONSHIP_SID)
			AND C.CUSTOM_VIEW_MASTER_SID IN (@CUST_VIEW_MASTER_SID)  AND RLD.VERSION_NO = @CUS_VERSION_NO
	INNER JOIN #T2 CH
		ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
	WHERE EXISTS (SELECT 1 FROM ',
			@CCP_HIERARCHY,
			' CD WHERE CD.CCP_DETAILS_SID=CH.CCP_DETAILS_SID)
	UNION ALL
	SELECT CH.CCP_DETAILS_SID,
		c.LEVEL_NO,
		RELATIONSHIP_LEVEL_VALUES
	FROM CUST_VIEW_DETAILS C
	INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
		ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
			AND RLD.RELATIONSHIP_BUILDER_SID IN (@PRODUCT_RELATIONSHIP_SID)
			AND C.CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID  AND RLD.VERSION_NO =@PROD_VERSION_NO
	INNER JOIN #T2 CH
		ON CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
			WHERE EXISTS (SELECT 1 FROM ',
			@CCP_HIERARCHY,
			' CD WHERE CD.CCP_DETAILS_SID=CH.CCP_DETAILS_SID)
			'
			)

	EXEC Sp_executesql @SQL,
		N'@CUSTOMER_RELATIONSHIP_SID INT,@CUST_VIEW_MASTER_SID INT,@PRODUCT_RELATIONSHIP_SID INT,@CUS_VERSION_NO int,@PROD_VERSION_NO int',
		@CUSTOMER_RELATIONSHIP_SID = @CUSTOMER_RELATIONSHIP_SID,
		@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID,
		@PRODUCT_RELATIONSHIP_SID = @PRODUCT_RELATIONSHIP_SID,
		@CUS_VERSION_NO=@CUS_VERSION_NO,
		@PROD_VERSION_NO=@PROD_VERSION_NO


	SET @SQL = ''
	SET @SQL = CONCAT (
			@SQL,
			'INSERT INTO ',
			@CUSTOM_CCP_SALES,
			' (
				HIERARCHY_NO,
				CCP_DETAILS_SID,
				LEVEL_NO,
				CUST_VIEW_MASTER_SID,
				RELATIONSHIP_LEVEL_VALES,
				ROWID,
				RS_CONTRACT_SID
				)
			SELECT HIERARCHY_NO,
				CCP_DETAILS_SID,
				LEVEL_NO,
				@CUST_VIEW_MASTER_SID,
				RELATIONSHIP_LEVEL_VALUES,
				DENSE_RANK() OVER (
					ORDER BY HIERARCHY_NO
					),
				0 AS RS_CONTRACT_SID
			FROM (
				SELECT CCP_DETAILS_SID,
					CONCAT (
						@CUST_VIEW_MASTER_SID,
						''-'',
						PARENT_HIERARCHY_NO,
						A.RELATIONSHIP_LEVEL_VALUES + ''.''
						) AS HIERARCHY_NO,
					LEVEL_NO,
					RELATIONSHIP_LEVEL_VALUES
				FROM (
					SELECT CCP_DETAILS_SID,
						RELATIONSHIP_LEVEL_VALUES,
						LEVEL_NO
					FROM #PARENT_HIERARCHY
					GROUP BY LEVEL_NO,
						CCP_DETAILS_SID,
						RELATIONSHIP_LEVEL_VALUES
					) A
				CROSS APPLY (
					SELECT RELATIONSHIP_LEVEL_VALUES + ''.''
					FROM (
						SELECT CCP_DETAILS_SID,
							RELATIONSHIP_LEVEL_VALUES,
							LEVEL_NO
						FROM #PARENT_HIERARCHY
						GROUP BY LEVEL_NO,
							CCP_DETAILS_SID,
							RELATIONSHIP_LEVEL_VALUES
						) B
					WHERE A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
						AND A.LEVEL_NO > B.LEVEL_NO
					---AND  (A.RS_CONTRACT_SID =B.RS_CONTRACT_SID OR A.RS_CONTRACT_SID IS NULL)
					ORDER BY LEVEL_NO
					FOR XML PATH('''')
					) CS(PARENT_HIERARCHY_NO)
				) B'
			)

	EXEC Sp_executesql @SQL,
		N'@CUST_VIEW_MASTER_SID INT',
		@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
END
ELSE
BEGIN


 IF OBJECT_ID('TEMPDB..#TEMP_CONTRACT') IS NOT NULL
    DROP TABLE #TEMP_CONTRACT

	CREATE TABLE #TEMP_CONTRACT
	(
	CFP_CONTRACT_SID INT,
	COMPANY_MASTER_SID INT,
	CONTRACT_MASTER_SID INT
	)

	INSERT INTO #TEMP_CONTRACT 
	SELECT  CFP1.CFP_CONTRACT_SID,CFP2.COMPANY_MASTER_SID,CFP1.CONTRACT_MASTER_SID 
	FROM  CFP_CONTRACT CFP1
           JOIN CFP_CONTRACT_DETAILS CFP2   
		   ON CFP1.CFP_CONTRACT_SID =CFP2.CFP_CONTRACT_SID 
		   JOIN CCP_DETAILS CCP ON CCP.CONTRACT_MASTER_SID=CFP1.CONTRACT_MASTER_SID AND CCP.COMPANY_MASTER_SID=CFP2.COMPANY_MASTER_SID
		   JOIN CUSTOM_CCP_DETAILS CUC ON CUC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND CUC.CUST_VIEW_MASTER_SID=@CUST_VIEW_MASTER_SID
		   GROUP BY  CFP1.CFP_CONTRACT_SID,CFP2.COMPANY_MASTER_SID,CFP1.CONTRACT_MASTER_SID 

	IF OBJECT_ID('TEMPDB..#UDC') IS NOT NULL
		DROP TABLE #UDC

	CREATE TABLE #UDC (
		HELPER_TABLE_SID INT,
		DESCRIPTION VARCHAR(50),
		LIST_NAME VARCHAR(50)
		)

	INSERT INTO #UDC
	SELECT HELPER_TABLE_SID,
		DESCRIPTION,
		LIST_NAME
	FROM HELPER_TABLE
	WHERE DESCRIPTION IN (
			'UDC1',
			'UDC2',
			'UDC3',
			'UDC4',
			'UDC5',
			'UDC6'
			)
		AND LIST_NAME IN (
			'RS_UDC1',
			'RS_UDC2',
			'RS_UDC3',
			'RS_UDC4',
			'RS_UDC5',
			'RS_UDC6'
			)

	IF OBJECT_ID('TEMPDB..#TEMP_DEDUCTION') IS NOT NULL
DROP TABLE #TEMP_DEDUCTION
 
CREATE TABLE #TEMP_DEDUCTION (
CCP_DETAILS_SID INT,
RS_CONTRACT_SID INT,
RELATIONSHIP_LEVEL_VALUES int,
level_no smallint
)
 
set @SQL=concat(' INSERT INTO #TEMP_DEDUCTION
select distinct a.CCP_DETAILS_SID,a.RS_CONTRACT_SID,b.RELATIONSHIP_LEVEL_VALUES,b.LEVEL_NO From
(
SELECT DISTINCT CCP.CCP_DETAILS_SID,
B.RS_CONTRACT_SID,
B.RS_ID,
B.RS_CATEGORY,
B.RS_TYPE,
B.REBATE_PROGRAM_TYPE,
ISNULL(CASE
WHEN (
U.UDC1 = 0
OR U.UDC1 IS NULL
)
THEN (
SELECT HELPER_TABLE_SID
FROM #UDC
WHERE DESCRIPTION = ''UDC1''
AND LIST_NAME = ''RS_UDC1''
)
END, U.UDC1) AS UDC1,
ISNULL(CASE
WHEN (
U.UDC2 = 0
OR U.UDC2 IS NULL
)
THEN (
SELECT HELPER_TABLE_SID
FROM #UDC
WHERE DESCRIPTION = ''UDC2''
AND LIST_NAME = ''RS_UDC2''
)
END, U.UDC2) AS UDC2,
ISNULL(CASE
WHEN (
U.UDC3 = 0
OR U.UDC3 IS NULL
)
THEN (
SELECT HELPER_TABLE_SID
FROM #UDC
WHERE DESCRIPTION = ''UDC3''
AND LIST_NAME = ''RS_UDC3''
)
END, U.UDC3) AS UDC3,
ISNULL(CASE
WHEN (
U.UDC4 = 0
OR U.UDC4 IS NULL
)
THEN (
SELECT HELPER_TABLE_SID
FROM #UDC
WHERE DESCRIPTION = ''UDC4''
AND LIST_NAME = ''RS_UDC4''
)
END, U.UDC4) AS UDC4,
ISNULL(CASE
WHEN (
U.UDC5 = 0
OR U.UDC5 IS NULL
)
THEN (
SELECT HELPER_TABLE_SID
FROM #UDC
WHERE DESCRIPTION = ''UDC5''
AND LIST_NAME = ''RS_UDC5''
)
END, U.UDC5) AS UDC5,
ISNULL(CASE
WHEN (
U.UDC6 = 0
OR U.UDC6 IS NULL
)
THEN (
SELECT HELPER_TABLE_SID
FROM #UDC
WHERE DESCRIPTION = ''UDC6''
AND LIST_NAME = ''RS_UDC6''
)
END, U.UDC6) AS UDC6,
CONCAT (
@DEDUCTION_RELATION_SID,
''-'',
RS_CATEGORY,
''.'',
RS_TYPE,
''.'',
REBATE_PROGRAM_TYPE,
''.'',
UDC1,
''.'',
UDC2,
''.'',
UDC3,
''.'',
UDC4,
''.'',
UDC5,
''.'',
UDC6,
''.'',
B.RS_CONTRACT_SID,
''.''
) AS HIERARCHY_NO
FROM CUSTOM_CCP_DETAILS A
INNER JOIN CCP_DETAILS CCP
ON CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
AND A.CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
INNER JOIN RS_CONTRACT B
ON CCP.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
INNER JOIN RS_CONTRACT_DETAILS RCD
ON RCD.RS_CONTRACT_SID = B.RS_CONTRACT_SID
AND RCD.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID
AND B.INBOUND_STATUS <> ''D''
AND RCD.INBOUND_STATUS <> ''D''
LEFT JOIN UDCS U
ON U.MASTER_SID = B.RS_CONTRACT_SID
AND U.MASTER_TYPE = ''RS_CONTRACT''
INNER JOIN #TEMP_CONTRACT TC ON TC.CONTRACT_MASTER_SID=B.CONTRACT_MASTER_SID
AND TC.COMPANY_MASTER_SID=CCP.COMPANY_MASTER_SID AND TC.CFP_CONTRACT_SID=B.CFP_CONTRACT_SID)a
 
CROSS APPLY(
       VALUES(a.RS_CATEGORY,1),
          (a.rs_type,2),
          (a.REBATE_PROGRAM_TYPE,3),
          (a.UDC1,4),
          (a.UDC2,5),
          (a.UDC3,6),
          (a.UDC4,7),
          (a.UDC5,8),
          (a.UDC6,9),
          (a.RS_CONTRACT_SID,10))
          B(RELATIONSHIP_LEVEL_VALUES,LEVEL_NO) 
			WHERE EXISTS (SELECT 1 FROM '
			,
			@CCP_HIERARCHY,
			' CD WHERE CD.CCP_DETAILS_SID=A.CCP_DETAILS_SID)'
			
			
			)
			
	EXEC Sp_executesql @SQL,
		N'@CUST_VIEW_MASTER_SID INT,@DEDUCTION_RELATION_SID INT',
		@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID,
		@DEDUCTION_RELATION_SID = @DEDUCTION_RELATION_SID
		


			


			
	
	SET @SQL = CONCAT (
			'INSERT INTO #PARENT_HIERARCHY (
		
		CCP_DETAILS_SID,
		LEVEL_NO,
		RELATIONSHIP_LEVEL_VALUES,
		RS_CONTRACT_SID
		)
	 SELECT
              CH.CCP_DETAILS_SID,
              c.LEVEL_NO,
              rld.RELATIONSHIP_LEVEL_VALUES +''.'',
              RS_CONTRACT_SID
       FROM CUST_VIEW_DETAILS C
       INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
              ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
                     AND RLD.RELATIONSHIP_BUILDER_SID IN (@CUSTOMER_RELATIONSHIP_SID)
                     AND C.CUSTOM_VIEW_MASTER_SID IN (@CUST_VIEW_MASTER_SID) AND RLD.VERSION_NO = @CUS_VERSION_NO
       INNER JOIN #T2 CH
              ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
       INNER JOIN #TEMP_DEDUCTION TD ON TD.CCP_DETAILS_SID=CH.CCP_DETAILS_SID and TD.LEVEL_NO=1
      
			AND EXISTS (SELECT 1 FROM ',
			@CCP_HIERARCHY,
			' CD WHERE CD.CCP_DETAILS_SID=CH.CCP_DETAILS_SID)
	
	UNION ALL
	
	 SELECT
              CH.CCP_DETAILS_SID,
              C.LEVEL_NO,
              RLD.RELATIONSHIP_LEVEL_VALUES+''.'',
              RS_CONTRACT_SID
       FROM CUST_VIEW_DETAILS C
       INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
              ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
                     AND RLD.RELATIONSHIP_BUILDER_SID IN (@PRODUCT_RELATIONSHIP_SID)
                     AND C.CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID       AND RLD.VERSION_NO =@PROD_VERSION_NO
       INNER JOIN #T2 CH
              ON CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
       INNER JOIN #TEMP_DEDUCTION TD ON TD.CCP_DETAILS_SID=CH.CCP_DETAILS_SID  AND TD.LEVEL_NO=1
      
			AND EXISTS (SELECT 1 FROM '
			,
			@CCP_HIERARCHY,
			' CD WHERE CD.CCP_DETAILS_SID=CH.CCP_DETAILS_SID)
	
	UNION ALL
	
	SELECT
              CH.CCP_DETAILS_SID,
              c.LEVEL_NO,
              RLD.RELATIONSHIP_LEVEL_VALUES+''.'',
              pr.RS_CONTRACT_SID 
       FROM CUST_VIEW_DETAILS C
       INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
              ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
                     AND RLD.RELATIONSHIP_BUILDER_SID IN (@DEDUCTION_RELATION_SID)
                     AND C.CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
       INNER JOIN RELATIONSHIP_BUILDER RB
              ON RB.RELATIONSHIP_BUILDER_SID = @DEDUCTION_RELATION_SID
                     AND RLD.VERSION_NO = RB.VERSION_NO
       INNER JOIN #TEMP_DEDUCTION PR
              ON  pr.level_no=rld.LEVEL_NO and pr.RELATIONSHIP_LEVEL_VALUES=rld.RELATIONSHIP_LEVEL_VALUES   
       INNER JOIN #T2 CH
              ON CH.CCP_DETAILS_SID = PR.CCP_DETAILS_SID
                     AND CH.CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
			AND EXISTS (SELECT 1 FROM '
			,
			@CCP_HIERARCHY,
			' CD WHERE CD.CCP_DETAILS_SID=CH.CCP_DETAILS_SID)
			group by  CH.CCP_DETAILS_SID,
              c.LEVEL_NO,
              RLD.RELATIONSHIP_LEVEL_VALUES,
              pr.RS_CONTRACT_SID 
			'
			)

	EXEC Sp_executesql @SQL,
		N'@CUSTOMER_RELATIONSHIP_SID INT,@CUST_VIEW_MASTER_SID INT,@PRODUCT_RELATIONSHIP_SID INT,@DEDUCTION_RELATION_SID INT,@PROD_VERSION_NO int,@CUS_VERSION_NO int',
		@CUSTOMER_RELATIONSHIP_SID = @CUSTOMER_RELATIONSHIP_SID,
		@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID,
		@PRODUCT_RELATIONSHIP_SID = @PRODUCT_RELATIONSHIP_SID,
		@DEDUCTION_RELATION_SID = @DEDUCTION_RELATION_SID,
		@CUS_VERSION_NO=@CUS_VERSION_NO,
		@PROD_VERSION_NO=@PROD_VERSION_NO

	DECLARE @I INT,
@MAXIMUM_VALUE INT
SET @I = 1
SET @MAXIMUM_VALUE = (
SELECT MAX(LEVEL_NO)
FROM CUST_VIEW_DETAILS
WHERE CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
)
 
IF OBJECT_ID('TEMPDB..#COLUMNS') IS NOT NULL
DROP TABLE #COLUMNS
 
CREATE TABLE #COLUMNS (
COLUMN_NAME VARCHAR(100),
LEVEL_NO INT,
FORMULA VARCHAR(1000)
)
 
WHILE (@I <= @MAXIMUM_VALUE)
BEGIN
INSERT INTO #COLUMNS
SELECT CONCAT (
'LEVEL_',
@I
),
@I,
NULL
 
UPDATE #COLUMNS
SET FORMULA = STUFF((
SELECT '+',
COLUMN_NAME
FROM #COLUMNS
WHERE LEVEL_NO <= @I
FOR XML PATH(''),
TYPE
).value('.', 'NVARCHAR(MAX)'), 1, 1, '')
WHERE LEVEL_NO = @I
 
SET @I = @I + 1;
END
 
DECLARE @COLUMNS NVARCHAR(MAX),
@LEVEL_NO NVARCHAR(MAX),
@LEVEL_NO1 NVARCHAR(MAX),
@LEVEL_NO2 NVARCHAR(MAX)
 
SELECT @COLUMNS = STUFF((
SELECT ',' + QUOTENAME(COLUMN_NAME)
FROM #COLUMNS
WHERE LEVEL_NO <= @MAXIMUM_VALUE
ORDER BY LEVEL_NO
FOR XML PATH(''),
TYPE
).value('.', 'NVARCHAR(MAX)'), 1, 1, ''),@LEVEL_NO = STUFF((
SELECT ',' + QUOTENAME(LEVEL_NO)
FROM #COLUMNS
WHERE LEVEL_NO <= @MAXIMUM_VALUE
ORDER BY LEVEL_NO
FOR XML PATH(''),
TYPE
).value('.', 'NVARCHAR(MAX)'), 1, 1, ''),
@LEVEL_NO1 = STUFF((
SELECT ',' + CONCAT (
'MAX(',
QUOTENAME(LEVEL_NO),
')',
' AS LEVEL_',
LEVEL_NO
)
FROM #COLUMNS
WHERE LEVEL_NO <= @MAXIMUM_VALUE
ORDER BY LEVEL_NO
FOR XML PATH(''),
TYPE
).value('.', 'NVARCHAR(MAX)'), 1, 1, ''),
@LEVEL_NO2 = STUFF((
SELECT ',' + CONCAT (
FORMULA,
' AS LEVEL_',
LEVEL_NO
)
FROM #COLUMNS
WHERE LEVEL_NO <= @MAXIMUM_VALUE
ORDER BY LEVEL_NO
FOR XML PATH(''),
TYPE
).value('.', 'NVARCHAR(MAX)'), 1, 1, '')

IF OBJECT_ID('TEMPDB..#CCP_TABLE') IS NOT NULL
DROP TABLE #CCP_TABLE
 
create table #CCP_TABLE(
ccp_details_sid int,
RS_CONTRACT_SID int
)
 
DECLARE @SCRIPT_PREPARE  NVARCHAR(MAX);

SET @SCRIPT_PREPARE = (SELECT  'ALTER TABLE #CCP_TABLE  ADD  '+ COLUMN_NAME + ' varchar(8000) ; '
                                 FROM    #COLUMNS
order by LEVEL_NO
                                 FOR XML PATH (''))
 
EXEC sp_executesql @SCRIPT_PREPARE
SET @SQL = CONCAT (
'
INSERT INTO #CCP_TABLE(CCP_DETAILS_SID,RS_CONTRACT_SID, '+@COLUMNS+')
SELECT CCP_DETAILS_SID,
RS_CONTRACT_SID,
' + @LEVEL_NO2 + ' FROM (
SELECT CCP_DETAILS_SID,
RS_CONTRACT_SID,
' + @LEVEL_NO1 + '
FROM #PARENT_HIERARCHY
A
PIVOT(MAX(RELATIONSHIP_LEVEL_VALUES) FOR LEVEL_NO IN (
' + @LEVEL_NO + '
)) PT
GROUP BY  CCP_DETAILS_SID,
RS_CONTRACT_SID)A
',
''
)

EXEC SP_EXECUTESQL @SQL
 
DECLARE @VAR1 VARCHAR(1000)
SET @VAR1= 'CASE'+(SELECT ' '+'WHEN A.LEVEL_NO ='+CONVERT(VARCHAR(100),LEVEL_NO) +' THEN LEVEL_' +CONVERT(VARCHAR(100),LEVEL_NO) FROM #COLUMNS FOR XML PATH(''))+ ' END'
SET @VAR1=IIF(@VAR1 IS NULL,'''''',@VAR1)
SET @SQL=CONCAT('
INSERT INTO ',@CUSTOM_CCP_SALES,' (
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
''-'' ,'+ @VAR1 +')
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
) B')

EXEC SP_EXECUTESQL @SQL,
N'@CUST_VIEW_MASTER_SID INT',
@CUST_VIEW_MASTER_SID=@CUST_VIEW_MASTER_SID
END

		
DECLARE @CUST_VIEW_TYPE VARCHAR(100),@COUNT INT 

SELECT  @CUST_VIEW_TYPE = CASE 
		WHEN CUST_VIEW_TYPE LIKE '%report~Expandable~Rows%'
			THEN 'VARIABLE'
		WHEN ( CUST_VIEW_TYPE LIKE '%STATIC%' or CUST_VIEW_TYPE LIKE '%report~Expandable~Columns%' )
			THEN 'STATIC'
		END
FROM CUST_VIEW_MASTER
WHERE CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID


SET @SQL = CONCAT (
		'',
		'
			
			DELETE A
			FROM ',
		@CUSTOM_VARIABLE_HIERARCHY,
		' A
			/*WHERE EXISTS (
					SELECT 1
					FROM CUST_VIEW_DETAILS B
					WHERE B.CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
						AND B.CUSTOM_VIEW_DETAILS_SID = A.CUSTOM_VIEW_DETAILS_SID
					)*/'
		)

EXEC Sp_executesql @SQL,
	N'@CUST_VIEW_MASTER_SID INT',
	@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID


IF (@CUST_VIEW_TYPE = 'VARIABLE')
BEGIN

SELECT    @COUNT=LEVEL_NO
		FROM CUST_VIEW_DETAILS
		WHERE CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
			AND HIERARCHY_INDICATOR = 'V'

	IF Object_id('TEMPDB..#TEMP') IS NOT NULL
		DROP TABLE #TEMP

	CREATE TABLE #TEMP (
		HIERARCHY_NO VARCHAR(8000),
		LEVEL_NO INT,
		VARIABLE_INDICATOR CHAR(1),
		RELATIONSHIP_LEVEL_VALES INT,
		CUSTOM_VIEW_DETAILS_SID INT
		)

	SET @SQL = CONCAT (
			'',
			'
INSERT INTO #TEMP
SELECT HIERARCHY_NO,
	CASE 
		WHEN A.LEVEL_NO = @COUNT - 1
			THEN B.LEVEL_NO
		ELSE A.LEVEL_NO
		END LEVEL_NO,
	VARIABLE_INDICATOR,
	IIF(A.LEVEL_NO = @COUNT - 1, VARIABLE_SID, RELATIONSHIP_LEVEL_VALES) RELATIONSHIP_LEVEL_VALES,
	cd.CUSTOM_VIEW_DETAILS_SID

FROM (
	SELECT HIERARCHY_NO,
		LEVEL_NO,
		RELATIONSHIP_LEVEL_VALES,
		CUST_VIEW_MASTER_SID
	FROM ',@CUSTOM_CCP_SALES,'
	WHERE LEVEL_NO >= @COUNT - 1
	GROUP BY HIERARCHY_NO,
		LEVEL_NO,
		RELATIONSHIP_LEVEL_VALES,
		CUST_VIEW_MASTER_SID
	) A
INNER JOIN (
	SELECT A.CUSTOM_VIEW_MASTER_SID,
		A.LEVEL_NO,
		VARIABLE_INDICATOR,A.HIERARCHY_INDICATOR,VARIABLE_SID
	FROM (
		SELECT CUSTOM_VIEW_MASTER_SID,
			LEVEL_NO,
			CUSTOM_VIEW_DETAILS_SID,HIERARCHY_INDICATOR
		FROM CUST_VIEW_DETAILS
		WHERE CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
			AND HIERARCHY_INDICATOR = ''V'' 
			
		) A
	INNER JOIN CUSTOM_VIEW_VARIABLES B
		ON A.CUSTOM_VIEW_DETAILS_SID = B.CUSTOM_VIEW_DETAILS_SID
	) B
	ON A.CUST_VIEW_MASTER_SID = B.CUSTOM_VIEW_MASTER_SID
		AND A.LEVEL_NO >= B.LEVEL_NO - 1
		join cust_view_details cd on cd.CUSTom_VIEW_MASTER_SID=a.CUST_VIEW_MASTER_SID
		and CASE 
		WHEN A.LEVEL_NO = @COUNT - 1
			THEN B.LEVEL_NO
		ELSE A.LEVEL_NO
		END=cd.level_no
ORDER BY A.HIERARCHY_NO,
	A.LEVEL_NO'
			)

	EXEC Sp_executesql @SQL,
		N'@CUST_VIEW_MASTER_SID INT,@COUNT int',
		@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID,
		@COUNT=@COUNT

	SET @SQL = CONCAT (
			'',
			'INSERT INTO ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' (
	HIERARCHY_NO,
	LEVEL_NO,
	CUSTOM_VIEW_DETAILS_SID,
	RELATIONSHIP_LEVEL_VALUES
	)
	select distinct HIERARCHY_NO,
	LEVEL_NO,
	CUSTOM_VIEW_DETAILS_SID,
	RELATIONSHIP_LEVEL_VALES from (SELECT HIERARCHY_NO,
	LEVEL_NO,
	CUSTOM_VIEW_DETAILS_SID,
	RELATIONSHIP_LEVEL_VALES
FROM (
	SELECT HIERARCHY_NO,
		B.LEVEL_NO,
		RELATIONSHIP_LEVEL_VALES,
		b.CUSTOM_VIEW_DETAILS_SID
	FROM ',@CUSTOM_CCP_SALES,' A
	INNER JOIN CUST_VIEW_DETAILS B
		ON A.CUST_VIEW_MASTER_SID = B.CUSTOM_VIEW_MASTER_SID
			AND A.LEVEL_NO = B.LEVEL_NO
	WHERE A.LEVEL_NO < @COUNT
	GROUP BY HIERARCHY_NO,
		B.LEVEL_NO,
		RELATIONSHIP_LEVEL_VALES,
		b.CUSTOM_VIEW_DETAILS_SID
	
	UNION ALL
	
	SELECT CASE 
			WHEN A.LEVEL_NO = @COUNT
				THEN CONCAT (
						A.HIERARCHY_NO,
						A.VARIABLE_INDICATOR
						)
			ELSE REPLACE(A.HIERARCHY_NO, B.HIERARCHY_NO, CONCAT (
						B.HIERARCHY_NO,
						A.VARIABLE_INDICATOR,
						''.''
						))
			END HIERARCHY_NO,
		A.LEVEL_NO,
		a.RELATIONSHIP_LEVEL_VALES,
		A.CUSTOM_VIEW_DETAILS_SID
	FROM #TEMP A
	INNER JOIN #TEMP B
		ON B.LEVEL_NO = @COUNT
			AND A.VARIABLE_INDICATOR = B.VARIABLE_INDICATOR
			AND A.HIERARCHY_NO LIKE B.HIERARCHY_NO + ''%''
	) A)V
ORDER BY HIERARCHY_NO,
	LEVEL_NO'
			)

	EXEC Sp_executesql @SQL,
		N'@CUST_VIEW_MASTER_SID INT,@COUNT int',
		@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID,
		@COUNT=@COUNT
		DECLARE @INCR INT=@COUNT+1, @TOT_LEVEL INT= (SELECT    MAX(LEVEL_NO)
		FROM CUST_VIEW_DETAILS
		WHERE CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID)
		if object_id('tempdb..#parent_levels') is not null
		drop table #parent_levels
		create table #parent_levels(
		LEVEL_NO int,parent_value varchar(2000),child_values varchar(2000)
		)
		insert into #parent_levels
		select distinct p.LEVEL_NO,replace(q.RELATIONSHIP_LEVEL_VALUES,'.','') parent_value, replace(p.RELATIONSHIP_LEVEL_VALUES,'.','') child_values --, concat(q.RELATIONSHIP_LEVEL_VALUES,p.RELATIONSHIP_LEVEL_VALUES) 
		
from #PARENT_HIERARCHY p join #PARENT_HIERARCHY q on p.LEVEL_NO=Q.LEVEL_NO+1
			and p.CCP_DETAILS_SID=q.CCP_DETAILS_SID and p.RS_CONTRACT_SID=q.RS_CONTRACT_SID
		WHILE (@INCR<=@TOT_LEVEL)
		BEGIN

SET @SQL=CONCAT('
IF NOT EXISTS (SELECT 1 FROM ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' WHERE level_no = ',@INCR,')
BEGIN 
INSERT INTO ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' (
	HIERARCHY_NO,
	LEVEL_NO,
	CUSTOM_VIEW_DETAILS_SID,
	RELATIONSHIP_LEVEL_VALUES
	) SELECT distinct REPLACE(CONCAT (
		S.HIERARCHY_NO
		,''.''
		,T.RELATIONSHIP_LEVEL_VALES,''.''
		),''..'',''.'')
	,T.LEVEL_NO
	
	,T.CUSTOM_VIEW_DETAILS_SID,T.RELATIONSHIP_LEVEL_VALES
FROM ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' s
JOIN #TEMP t1 ON t1.LEVEL_NO = s.level_no and s.RELATIONSHIP_LEVEL_VALUES=t1.RELATIONSHIP_LEVEL_VALES and s.CUSTOM_VIEW_DETAILS_SID=t1.CUSTOM_VIEW_DETAILS_SID
JOIN #TEMP t ON t.LEVEL_NO = t1.level_no + 1',
case when 1=(select top 1 1 from #parent_levels where level_no=@INCR) then '
join #parent_levels h on h.level_no=t.LEVEL_NO
			and h.parent_value=s.RELATIONSHIP_LEVEL_VALUES
			and h.child_values=t.RELATIONSHIP_LEVEL_VALES'
end 
,'
WHERE T.level_no = ',@INCR,'
AND NOT EXISTS (SELECT 1 FROM ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' s1 WHERE  CONCAT (
		S.HIERARCHY_NO
		,''.''
		,T.RELATIONSHIP_LEVEL_VALES,''.''
		)=S1.HIERARCHY_NO
			AND S1.LEVEL_NO=s.LEVEL_NO
			AND S1.RELATIONSHIP_LEVEL_VALUES=S.RELATIONSHIP_LEVEL_VALUES
			AND S1.CUSTOM_VIEW_DETAILS_SID=S.CUSTOM_VIEW_DETAILS_SID) 
	END ')
			--select @SQL
	EXEC Sp_executesql @SQL
SET @INCR=@INCR+1
END
		/*WHILE (@INCR<=@TOT_LEVEL)
		BEGIN

SET @SQL=CONCAT('
IF NOT EXISTS (SELECT 1 FROM ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' WHERE level_no = ',@INCR,')
BEGIN 
INSERT INTO ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' (
	HIERARCHY_NO,
	LEVEL_NO,
	CUSTOM_VIEW_DETAILS_SID,
	RELATIONSHIP_LEVEL_VALUES
	) SELECT distinct REPLACE(CONCAT (
		S.HIERARCHY_NO
		,''.''
		,T.RELATIONSHIP_LEVEL_VALES,''.''
		),''..'',''.'')
	,T.LEVEL_NO
	
	,T.CUSTOM_VIEW_DETAILS_SID,T.RELATIONSHIP_LEVEL_VALES
FROM ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' s
JOIN #TEMP t1 ON t1.LEVEL_NO = s.level_no and s.RELATIONSHIP_LEVEL_VALUES=t1.RELATIONSHIP_LEVEL_VALES and s.CUSTOM_VIEW_DETAILS_SID=t1.CUSTOM_VIEW_DETAILS_SID
JOIN #TEMP t ON t.LEVEL_NO = t1.level_no + 1
WHERE T.level_no = ',@INCR,'
AND NOT EXISTS (SELECT 1 FROM ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' s1 WHERE  CONCAT (
		S.HIERARCHY_NO
		,''.''
		,T.RELATIONSHIP_LEVEL_VALES,''.''
		)=S1.HIERARCHY_NO
			AND S1.LEVEL_NO=s.LEVEL_NO
			AND S1.RELATIONSHIP_LEVEL_VALUES=S.RELATIONSHIP_LEVEL_VALUES
			AND S1.CUSTOM_VIEW_DETAILS_SID=S.CUSTOM_VIEW_DETAILS_SID) 
	END ')
			
	EXEC Sp_executesql @SQL
SET @INCR=@INCR+1
END
	*/	/*WHILE (@INCR<=@TOT_LEVEL)
		BEGIN
SET @SQL=CONCAT('INSERT INTO ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' (
	HIERARCHY_NO,
	LEVEL_NO,
	CUSTOM_VIEW_DETAILS_SID,
	RELATIONSHIP_LEVEL_VALUES
	) SELECT distinct CONCAT (
		S.HIERARCHY_NO
		,''.''
		,T.RELATIONSHIP_LEVEL_VALES,''.''
		)
	,T.LEVEL_NO
	
	,T.CUSTOM_VIEW_DETAILS_SID,T.RELATIONSHIP_LEVEL_VALES
FROM ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' s
JOIN #TEMP t1 ON t1.LEVEL_NO = s.level_no and s.RELATIONSHIP_LEVEL_VALUES=t1.RELATIONSHIP_LEVEL_VALES and s.CUSTOM_VIEW_DETAILS_SID=t1.CUSTOM_VIEW_DETAILS_SID
JOIN #TEMP t ON t.LEVEL_NO = t1.level_no + 1
WHERE T.level_no = ',@INCR,'
AND NOT EXISTS (SELECT 1 FROM ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' s1 WHERE S.HIERARCHY_NO=S1.HIERARCHY_NO
			AND S1.LEVEL_NO=S.LEVEL_NO
			AND S1.RELATIONSHIP_LEVEL_VALUES=S.RELATIONSHIP_LEVEL_VALUES
			AND S1.CUSTOM_VIEW_DETAILS_SID=S.CUSTOM_VIEW_DETAILS_SID) ')

	EXEC Sp_executesql @SQL
SET @INCR=@INCR+1
END*/

END
ELSE
BEGIN
	SET @SQL = CONCAT (
			'',
			'INSERT INTO ',
			@CUSTOM_VARIABLE_HIERARCHY,
			' (
	HIERARCHY_NO,
	LEVEL_NO,
	CUSTOM_VIEW_DETAILS_SID,
	RELATIONSHIP_LEVEL_VALUES,ROWID
	)
SELECT DISTINCT CONCAT (
		HIERARCHY_NO,
		CASE 
			WHEN A.HIERARCHY_INDICATOR = ''V''
				THEN VARIABLE_INDICATOR
			ELSE ''''
			END
		) VARIABLE_HIERARCHY_NO,
	A.LEVEL_NO,
	A.CUSTOM_VIEW_DETAILS_SID,
	IIF(A.HIERARCHY_INDICATOR = ''V'', VARIABLE_SID, B.RELATIONSHIP_LEVEL_VALES),
	DENSE_RANK() OVER (
			ORDER BY HIERARCHY_NO
			)
FROM CUST_VIEW_DETAILS A
INNER JOIN (
	SELECT CUST_VIEW_MASTER_SID CUSTOM_VIEW_MASTER_SID,
		MAX(HIERARCHY_NO) HIERARCHY_NO,
		ROWID,
		LEVEL_NO,RELATIONSHIP_LEVEL_VALES
	FROM ',
			@CUSTOM_CCP_SALES,
			' CS
	WHERE CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
	AND EXISTS (SELECT 1 FROM ',
			@CCP_HIERARCHY,
			' CD WHERE CD.CCP_DETAILS_SID=CS.CCP_DETAILS_SID)
	GROUP BY CUST_VIEW_MASTER_SID,
		ROWID,
		LEVEL_NO,RELATIONSHIP_LEVEL_VALES
	) B
	ON A.CUSTOM_VIEW_MASTER_SID = B.CUSTOM_VIEW_MASTER_SID
		AND A.LEVEL_NO = B.LEVEL_NO + IIF(A.HIERARCHY_INDICATOR = ''V'', 1, 0)
LEFT JOIN CUSTOM_VIEW_VARIABLES C
	ON A.CUSTOM_VIEW_DETAILS_SID = C.CUSTOM_VIEW_DETAILS_SID
--ORDER BY ROWID,A.CUSTOM_VIEW_DETAILS_SID
'
			)

	EXEC Sp_executesql @SQL,
		N'@CUST_VIEW_MASTER_SID INT',
		@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID

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

		RAISERROR (
				@ERRORMESSAGE, -- MESSAGE TEXT.
				@ERRORSEVERITY, -- SEVERITY.
				@ERRORSTATE, -- STATE.
				@ERRORPROCEDURE, -- PROCEDURE_NAME.
				@ERRORNUMBER, -- ERRORNUMBER
				@ERRORLINE -- ERRORLINE
				);
	END CATCH
END
GO
