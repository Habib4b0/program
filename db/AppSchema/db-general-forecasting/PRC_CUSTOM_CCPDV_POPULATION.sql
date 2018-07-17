IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_CUSTOM_CCPDV_POPULATION' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [dbo].[PRC_CUSTOM_CCPDV_POPULATION] 
  END 

GO 


CREATE PROCEDURE [dbo].[PRC_CUSTOM_CCPDV_POPULATION] (
	@CUST_VIEW_MASTER_SID INT,
	@USER_ID INT,
	@SESSION_ID VARCHAR(200)
	)
AS
SET NOCOUNT ON

BEGIN
	BEGIN TRY
		DECLARE @SQL NVARCHAR(MAX)
DECLARE @CCP_HIERARCHY VARCHAR(200) = CONCAT (
		'ST_CCP_HIERARCHY_',
		@USER_ID,
		'_',
		@SESSION_ID,
		'_',
		Replace(CONVERT(VARCHAR(50), getdate(), 2), '.', '')
		),
	@CUSTOM_CCP_SALES VARCHAR(200) = CONCAT (
		'ST_CUSTOM_CCP_SALES_',
		@USER_ID,
		'_',
		@SESSION_ID,
		'_',
		Replace(CONVERT(VARCHAR(50), getdate(), 2), '.', '')
		),
	@CUSTOM_VARIABLE_HIERARCHY VARCHAR(200) = CONCAT (
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
		WHERE CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID'
		)

EXEC Sp_executesql @SQL,
	N'@CUST_VIEW_MASTER_SID INT',
	@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID

IF Object_id('TEMPDB..#PARENT_HIERARCHY') IS NOT NULL
	DROP TABLE #PARENT_HIERARCHY

CREATE TABLE #PARENT_HIERARCHY (
	CCP_DETAILS_SID INT NOT NULL,
	HIERARCHY_NO VARCHAR(1000) NOT NULL,
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
		HIERARCHY_NO,
		CCP_DETAILS_SID,
		LEVEL_NO,
		RELATIONSHIP_LEVEL_VALuES
		)
	SELECT RLD.HIERARCHY_NO,
		CH.CCP_DETAILS_SID,
		c.LEVEL_NO,
		RELATIONSHIP_LEVEL_VALUES
	FROM CUST_VIEW_DETAILS C
	INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
		ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
			AND RLD.RELATIONSHIP_BUILDER_SID IN (@CUSTOMER_RELATIONSHIP_SID)
			AND C.CUSTOM_VIEW_MASTER_SID IN (@CUST_VIEW_MASTER_SID)
	INNER JOIN CUSTOM_CCP_DETAILS CH
		ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
			AND RLD.RELATIONSHIP_BUILDER_SID = CH.CUSTOMER_RELATIONSHIP_BUILDER_SID
			AND RLD.VERSION_NO = CH.CUSTOMER_VERSION
			AND CH.CUST_VIEW_MASTER_SID = C.CUSTOM_VIEW_MASTER_SID
	WHERE EXISTS (SELECT 1 FROM ',
			@CCP_HIERARCHY,
			' CD WHERE CD.CCP_DETAILS_SID=CH.CCP_DETAILS_SID)
	UNION ALL
	
	SELECT RLD.HIERARCHY_NO,
		CH.CCP_DETAILS_SID,
		c.LEVEL_NO,
		RELATIONSHIP_LEVEL_VALUES
	FROM CUST_VIEW_DETAILS C
	INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
		ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
			AND RLD.RELATIONSHIP_BUILDER_SID IN (@PRODUCT_RELATIONSHIP_SID)
			AND C.CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
	INNER JOIN CUSTOM_CCP_DETAILS CH
		ON CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
			AND RLD.RELATIONSHIP_BUILDER_SID = CH.PRODUCT_RELATIONSHIP_BUILDER_SID
			AND RLD.VERSION_NO = CH.PRODUCT_VERSION
			AND CH.CUST_VIEW_MASTER_SID = C.CUSTOM_VIEW_MASTER_SID
			WHERE EXISTS (SELECT 1 FROM ',
			@CCP_HIERARCHY,
			' CD WHERE CD.CCP_DETAILS_SID=CH.CCP_DETAILS_SID)
			'
			)

	EXEC Sp_executesql @SQL,
		N'@CUSTOMER_RELATIONSHIP_SID INT,@CUST_VIEW_MASTER_SID INT,@PRODUCT_RELATIONSHIP_SID INT',
		@CUSTOMER_RELATIONSHIP_SID = @CUSTOMER_RELATIONSHIP_SID,
		@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID,
		@PRODUCT_RELATIONSHIP_SID = @PRODUCT_RELATIONSHIP_SID

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

	IF Object_id('tempdb..#TEMP_DEDUCTION') IS NOT NULL
		DROP TABLE #TEMP_DEDUCTION

	CREATE TABLE #TEMP_DEDUCTION (
		CCP_DETAILS_SID INT,
		RS_CONTRACT_SID INT,
		RS_ID VARCHAR(50),
		REBATE_SCHEDULE_CATEGORY INT,
		REBATE_SCHEDULE_TYPE INT,
		REBATE_PROGRAM_TYPE INT,
		UDC1 INT,
		UDC2 INT,
		UDC3 INT,
		UDC4 INT,
		UDC5 INT,
		UDC6 INT,
		HIERARCHY_NO VARCHAR(1000)
		)

	SET @SQL = CONCAT (
			' INSERT INTO #TEMP_DEDUCTION
	SELECT DISTINCT CCP.CCP_DETAILS_SID,
		B.RS_CONTRACT_SID,
		B.RS_ID,
		B.RS_CATEGORY,
		B.RS_TYPE,
		B.REBATE_PROGRAM_TYPE,
		Isnull(CASE 
				WHEN (
						U.UDC1 = 0
						OR U.UDC1 IS NULL
						)
					THEN (
							SELECT HELPER_TABLE_SID
							FROM #udc
							WHERE DESCRIPTION = ''UDC1''
								AND LIST_NAME = ''RS_UDC1''
							)
				END, U.UDC1) AS UDC1,
		Isnull(CASE 
				WHEN (
						U.UDC2 = 0
						OR U.UDC2 IS NULL
						)
					THEN (
							SELECT HELPER_TABLE_SID
							FROM #udc
							WHERE DESCRIPTION = ''udc2''
								AND LIST_NAME = ''RS_UDC2''
							)
				END, U.UDC2) AS udc2,
		Isnull(CASE 
				WHEN (
						U.UDC3 = 0
						OR U.UDC3 IS NULL
						)
					THEN (
							SELECT HELPER_TABLE_SID
							FROM #udc
							WHERE DESCRIPTION = ''UDC3''
								AND LIST_NAME = ''RS_UDC3''
							)
				END, U.UDC3) AS UDC3,
		Isnull(CASE 
				WHEN (
						U.UDC4 = 0
						OR U.UDC4 IS NULL
						)
					THEN (
							SELECT HELPER_TABLE_SID
							FROM #udc
							WHERE DESCRIPTION = ''UDC4''
								AND LIST_NAME = ''RS_UDC4''
							)
				END, U.UDC4) AS UDC4,
		Isnull(CASE 
				WHEN (
						U.UDC5 = 0
						OR U.UDC5 IS NULL
						)
					THEN (
							SELECT HELPER_TABLE_SID
							FROM #udc
							WHERE DESCRIPTION = ''UDC5''
								AND LIST_NAME = ''RS_UDC5''
							)
				END, U.UDC5) AS UDC5,
		Isnull(CASE 
				WHEN (
						U.UDC6 = 0
						OR U.UDC6 IS NULL
						)
					THEN (
							SELECT HELPER_TABLE_SID
							FROM #udc
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
		ON ccp.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
	INNER JOIN RS_CONTRACT_DETAILS RCD
		ON rcd.RS_CONTRACT_SID = b.RS_CONTRACT_SID
			AND rcd.ITEM_MASTER_SID = ccp.ITEM_MASTER_SID
			AND B.INBOUND_STATUS <> ''D''
			AND rcd.INBOUND_STATUS <> ''D''
	LEFT JOIN UDCS U
		ON U.MASTER_SID = B.RS_CONTRACT_SID
			AND U.MASTER_TYPE = ''RS_CONTRACT'' 
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
		HIERARCHY_NO,
		CCP_DETAILS_SID,
		LEVEL_NO,
		RELATIONSHIP_LEVEL_VALUES,
		RS_CONTRACT_SID
		)
	SELECT RLD.HIERARCHY_NO,
		CH.CCP_DETAILS_SID,
		c.LEVEL_NO,
		RELATIONSHIP_LEVEL_VALUES,
		RS_CONTRACT_SID
	FROM CUST_VIEW_DETAILS C
	INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
		ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
			AND RLD.RELATIONSHIP_BUILDER_SID IN (@CUSTOMER_RELATIONSHIP_SID)
			AND C.CUSTOM_VIEW_MASTER_SID IN (@CUST_VIEW_MASTER_SID)
	INNER JOIN CUSTOM_CCP_DETAILS CH
		ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
			AND RLD.RELATIONSHIP_BUILDER_SID = CH.CUSTOMER_RELATIONSHIP_BUILDER_SID
			AND RLD.VERSION_NO = CH.CUSTOMER_VERSION
			AND CH.CUST_VIEW_MASTER_SID = C.CUSTOM_VIEW_MASTER_SID
			AND EXISTS (SELECT 1 FROM ',
			@CCP_HIERARCHY,
			' CD WHERE CD.CCP_DETAILS_SID=CH.CCP_DETAILS_SID)
	INNER JOIN CCP_DETAILS CD
		ON CD.CCP_DETAILS_SID = CH.CCP_DETAILS_SID
	INNER JOIN RS_CONTRACT RS
		ON RS.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
			AND EXISTS (
				SELECT 1
				FROM CFP_CONTRACT CC
				WHERE CC.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
					AND RS.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
					AND CC.INBOUND_STATUS <> ''D''
				)
			AND EXISTS (
				SELECT 1
				FROM IFP_CONTRACT IC
				WHERE IC.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
					AND RS.IFP_CONTRACT_SID = IC.IFP_CONTRACT_SID
					AND IC.INBOUND_STATUS <> ''D''
				)
	
	UNION ALL
	
	SELECT RLD.HIERARCHY_NO,
		CH.CCP_DETAILS_SID,
		c.LEVEL_NO,
		RELATIONSHIP_LEVEL_VALUES,
		RS_CONTRACT_SID
	FROM CUST_VIEW_DETAILS C
	INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
		ON C.HIERARCHY_ID = RLD.HIERARCHY_LEVEL_DEFINITION_SID
			AND RLD.RELATIONSHIP_BUILDER_SID IN (@PRODUCT_RELATIONSHIP_SID)
			AND C.CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
	INNER JOIN CUSTOM_CCP_DETAILS CH
		ON CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
			AND RLD.RELATIONSHIP_BUILDER_SID = CH.PRODUCT_RELATIONSHIP_BUILDER_SID
			AND RLD.VERSION_NO = CH.PRODUCT_VERSION
			AND CH.CUST_VIEW_MASTER_SID = C.CUSTOM_VIEW_MASTER_SID
			AND EXISTS (SELECT 1 FROM '
			,
			@CCP_HIERARCHY,
			' CD WHERE CD.CCP_DETAILS_SID=CH.CCP_DETAILS_SID)
	INNER JOIN CCP_DETAILS CD
		ON CD.CCP_DETAILS_SID = CH.CCP_DETAILS_SID
	INNER JOIN RS_CONTRACT RS
		ON RS.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
			AND EXISTS (
				SELECT 1
				FROM CFP_CONTRACT CC
				WHERE CC.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
					AND RS.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
					AND CC.INBOUND_STATUS <> ''D''
				)
			AND EXISTS (
				SELECT 1
				FROM IFP_CONTRACT IC
				WHERE IC.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
					AND RS.IFP_CONTRACT_SID = IC.IFP_CONTRACT_SID
					AND IC.INBOUND_STATUS <> ''D''
				)
	
	UNION ALL
	
	SELECT RLD.HIERARCHY_NO,
		CH.CCP_DETAILS_SID,
		c.LEVEL_NO,
		RLD.RELATIONSHIP_LEVEL_VALUES,
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
		ON PR.HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''
	INNER JOIN CUSTOM_CCP_DETAILS CH
		ON CH.CCP_DETAILS_SID = PR.CCP_DETAILS_SID
			AND CH.CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
			AND EXISTS (SELECT 1 FROM '
			,
			@CCP_HIERARCHY,
			' CD WHERE CD.CCP_DETAILS_SID=CH.CCP_DETAILS_SID)'
			)

	EXEC Sp_executesql @SQL,
		N'@CUSTOMER_RELATIONSHIP_SID INT,@CUST_VIEW_MASTER_SID INT,@PRODUCT_RELATIONSHIP_SID INT,@DEDUCTION_RELATION_SID INT',
		@CUSTOMER_RELATIONSHIP_SID = @CUSTOMER_RELATIONSHIP_SID,
		@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID,
		@PRODUCT_RELATIONSHIP_SID = @PRODUCT_RELATIONSHIP_SID,
		@DEDUCTION_RELATION_SID = @DEDUCTION_RELATION_SID

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
				SELECT CCP_DETAILS_SID,
					CONCAT (
						@CUST_VIEW_MASTER_SID,
						''-'',
						PARENT_HIERARCHY_NO,
						A.RELATIONSHIP_LEVEL_VALUES + ''.''
						) AS HIERARCHY_NO,
					LEVEL_NO,
					RELATIONSHIP_LEVEL_VALUES,
					RS_CONTRACT_SID
				FROM (
					SELECT CCP_DETAILS_SID,
						RELATIONSHIP_LEVEL_VALUES,
						LEVEL_NO,
						RS_CONTRACT_SID
					FROM #PARENT_HIERARCHY
					GROUP BY LEVEL_NO,
						CCP_DETAILS_SID,
						RELATIONSHIP_LEVEL_VALUES,
						RS_CONTRACT_SID
					) A
				CROSS APPLY (
					SELECT RELATIONSHIP_LEVEL_VALUES + ''.''
					FROM (
						SELECT CCP_DETAILS_SID,
							RELATIONSHIP_LEVEL_VALUES,
							LEVEL_NO,
							RS_CONTRACT_SID
						FROM #PARENT_HIERARCHY
						GROUP BY LEVEL_NO,
							CCP_DETAILS_SID,
							RELATIONSHIP_LEVEL_VALUES,
							RS_CONTRACT_SID
						) B
					WHERE A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
						AND A.LEVEL_NO > B.LEVEL_NO
						AND (
							A.RS_CONTRACT_SID = B.RS_CONTRACT_SID
							OR A.RS_CONTRACT_SID IS NULL
							)
					ORDER BY LEVEL_NO
					FOR XML PATH('''')
					) CS(PARENT_HIERARCHY_NO)
				) B'
			)

	EXEC Sp_executesql @SQL,
		N'@CUST_VIEW_MASTER_SID INT',
		@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
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
			WHERE EXISTS (
					SELECT 1
					FROM CUST_VIEW_DETAILS B
					WHERE B.CUSTOM_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID
						AND B.CUSTOM_VIEW_DETAILS_SID = A.CUSTOM_VIEW_DETAILS_SID
					)'
		)

EXEC Sp_executesql @SQL,
	N'@CUST_VIEW_MASTER_SID INT',
	@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID


IF (@CUST_VIEW_TYPE = 'VARIABLE' and @COUNT is not null)
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
	SELECT HIERARCHY_NO,
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
	) A
ORDER BY HIERARCHY_NO,
	LEVEL_NO'
			)

	EXEC Sp_executesql @SQL,
		N'@CUST_VIEW_MASTER_SID INT,@COUNT int',
		@CUST_VIEW_MASTER_SID = @CUST_VIEW_MASTER_SID,
		@COUNT=@COUNT

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
SELECT CONCAT (
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
ORDER BY ROWID,A.CUSTOM_VIEW_DETAILS_SID
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