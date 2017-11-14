IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NM_PROJECTION_INSERT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NM_PROJECTION_INSERT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_NM_PROJECTION_INSERT] (
	@PROJECTION INT
	,@USER_ID INT
	,@SESSION_ID VARCHAR(50)
	,@SCREEN_NAME VARCHAR(50)
	)
AS
BEGIN
	SET NOCOUNT ON

	DECLARE @FORECASTING_TYPE VARCHAR(50)

	SET @FORECASTING_TYPE = (
			SELECT FORECASTING_TYPE
			FROM PROJECTION_MASTER
			WHERE PROJECTION_MASTER_SID = @PROJECTION
			)

	DECLARE @MASTER_TABLE VARCHAR(200) = CASE 
			WHEN @SCREEN_NAME = 'SALES'
				AND @FORECASTING_TYPE = 'NON MANDATED'
				THEN CONCAT (
						'ST_NM_SALES_PROJECTION_MASTER_'
						,@USER_ID
						,'_'
						,@SESSION_ID
						,'_'
						,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
						)
			WHEN @SCREEN_NAME = 'SALES'
				AND @FORECASTING_TYPE = 'MANDATED'
				THEN CONCAT (
						'ST_M_SALES_PROJECTION_MASTER_'
						,@USER_ID
						,'_'
						,@SESSION_ID
						,'_'
						,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
						)
			WHEN @SCREEN_NAME = 'DISCOUNT'
				AND @FORECASTING_TYPE = 'NON MANDATED'
				THEN CONCAT (
						'ST_NM_DISCOUNT_PROJ_MASTER_'
						,@USER_ID
						,'_'
						,@SESSION_ID
						,'_'
						,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
						)
			END
		,@PROJECTION_TABLE VARCHAR(200) = CASE 
			WHEN @SCREEN_NAME = 'SALES'
				AND @FORECASTING_TYPE = 'NON MANDATED'
				THEN CONCAT (
						'ST_NM_SALES_PROJECTION_'
						,@USER_ID
						,'_'
						,@SESSION_ID
						,'_'
						,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
						)
			WHEN @SCREEN_NAME = 'SALES'
				AND @FORECASTING_TYPE = 'MANDATED'
				THEN CONCAT (
						'ST_M_SALES_PROJECTION_'
						,@USER_ID
						,'_'
						,@SESSION_ID
						,'_'
						,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
						)
			WHEN @SCREEN_NAME = 'DISCOUNT'
				AND @FORECASTING_TYPE = 'NON MANDATED'
				THEN CONCAT (
						'ST_NM_DISCOUNT_PROJECTION_'
						,@USER_ID
						,'_'
						,@SESSION_ID
						,'_'
						,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
						)
			END
		,@CCP_HIERARCHY VARCHAR(200) = CONCAT (
			'ST_CCP_HIERARCHY_'
			,@USER_ID
			,'_'
			,@SESSION_ID
			,'_'
			,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
			)
	DECLARE @PROJECTION_START_DATE DATETIME
		,@PROJECTION_END_DATE DATETIME
		,@SQL NVARCHAR(MAX)

	SELECT @PROJECTION_START_DATE = Datefromparts(Year(FROM_DATE), 1, 1)
		,@PROJECTION_END_DATE = Datefromparts(Year(TO_DATE), 12, 1)
	FROM PROJECTION_MASTER
	WHERE PROJECTION_MASTER_SID = @PROJECTION

	IF Object_id('TEMPDB..#APPROVED_CCP_DETAILS') IS NOT NULL
		DROP TABLE #APPROVED_CCP_DETAILS

	CREATE TABLE #APPROVED_CCP_DETAILS (
		PROJECTION_MASTER_SID INT
		,PROJECTION_DETAILS_SID INT
		,CCP_DETAILS_SID INT
		)

	INSERT INTO #APPROVED_CCP_DETAILS (
		PROJECTION_MASTER_SID
		,PROJECTION_DETAILS_SID
		,CCP_DETAILS_SID
		)
	EXEC Prc_approved_ccp_details @PROJECTION
		,@FORECASTING_TYPE
		,@USER_ID
		,@SESSION_ID

	IF @SCREEN_NAME = 'SALES'
	BEGIN
		IF Object_id('TEMPDB..#ST_NM_SALES_PROJECTION') IS NOT NULL
			DROP TABLE #ST_NM_SALES_PROJECTION

		CREATE TABLE #ST_NM_SALES_PROJECTION (
			CCP_DETAILS_SID INT
			,PERIOD_SID INT
			)

		SET @SQL = CONCAT (
				'INSERT INTO #ST_NM_SALES_PROJECTION
                      (CCP_DETAILS_SID,
                       PERIOD_SID)
          SELECT CCP_DETAILS_SID,
                 PERIOD_SID
          FROM   PERIOD
                 CROSS JOIN (SELECT CCP_DETAILS_SID
                             FROM  '
				,@CCP_HIERARCHY
				,' NSPM                                      
                             WHERE  NOT EXISTS (SELECT 1
                                                FROM   '
				,@PROJECTION_TABLE
				,' N
                                                WHERE  N.CCP_DETAILS_SID = NSPM.CCP_DETAILS_SID)) A
          WHERE  PERIOD_DATE BETWEEN '''
				,@PROJECTION_START_DATE
				,''' AND '''
				,@PROJECTION_END_DATE
				,''''
				)

		EXEC sp_executesql @SQL

		SET @SQL = CONCAT (
				'INSERT INTO '
				,@PROJECTION_TABLE
				,'
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       ACCOUNT_GROWTH,
                       PRODUCT_GROWTH,
                       PROJECTION_SALES,
                       PROJECTION_UNITS)
          SELECT NSP.CCP_DETAILS_SID,
                 NSP.PERIOD_SID,
                 COALESCE(ACCOUNT_GROWTH, 0),
                 COALESCE(PRODUCT_GROWTH, 0),
                 PROJECTION_SALES,
                 PROJECTION_UNITS
          FROM   #ST_NM_SALES_PROJECTION NSP
                 LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                                   SPM.PRODUCT_GROWTH,
                                   SPM.PROJECTION_SALES,
                                   SPM.PROJECTION_UNITS,
                                   SPM.PERIOD_SID,
                                   ACD.CCP_DETAILS_SID
                            FROM  '
				,CASE 
					WHEN @FORECASTING_TYPE = 'NON MANDATED'
						THEN 'NM_SALES_PROJECTION'
					WHEN @FORECASTING_TYPE = 'MANDATED'
						THEN 'M_SALES_PROJECTION'
					END
				,' SPM
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID) A
                        ON NSP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NSP.PERIOD_SID = A.PERIOD_SID'
				)

		--SELECT @SQL 
		EXEC sp_executesql @SQL

		-----------------------------addition of addition periods for the existing CCP'S-------
		IF Object_id('TEMPDB..#ST_NM_SALES_PROJECTION1') IS NOT NULL
			DROP TABLE #ST_NM_SALES_PROJECTION1

		CREATE TABLE #ST_NM_SALES_PROJECTION1 (
			CCP_DETAILS_SID INT
			,PERIOD_SID INT
			)

		SET @SQL = CONCAT (
				'INSERT INTO #ST_NM_SALES_PROJECTION1
                      (CCP_DETAILS_SID,
                       PERIOD_SID)
          SELECT CCP_DETAILS_SID,
                 PERIOD_SID
          FROM   PERIOD
                 CROSS JOIN (SELECT CCP_DETAILS_SID
                             FROM  '
				,@CCP_HIERARCHY
				,' NSPM                                      
                             WHERE  EXISTS (SELECT 1
                                                FROM   '
				,@PROJECTION_TABLE
				,' N
                                                WHERE  N.CCP_DETAILS_SID = NSPM.CCP_DETAILS_SID)) A
          WHERE  PERIOD_DATE BETWEEN '''
				,@PROJECTION_START_DATE
				,''' AND '''
				,@PROJECTION_END_DATE
				,''''
				)

		EXEC sp_executesql @SQL

		-----------------------------addition of addition periods for the existing CCP'S-------
		SET @SQL = CONCAT (
				'INSERT INTO '
				,@PROJECTION_TABLE
				,'
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       ACCOUNT_GROWTH,
                       PRODUCT_GROWTH,
                       PROJECTION_SALES,
                       PROJECTION_UNITS)
				SELECT NSP.CCP_DETAILS_SID,
                 NSP.PERIOD_SID,
                 COALESCE(ACCOUNT_GROWTH, 0),
                 COALESCE(PRODUCT_GROWTH, 0),
                 PROJECTION_SALES,
                 PROJECTION_UNITS
          FROM   #ST_NM_SALES_PROJECTION1 NSP
                 LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                                   SPM.PRODUCT_GROWTH,
                                   SPM.PROJECTION_SALES,
                                   SPM.PROJECTION_UNITS,
                                   SPM.PERIOD_SID,
                                   ACD.CCP_DETAILS_SID
                            FROM  '
				,CASE 
					WHEN @FORECASTING_TYPE = 'NON MANDATED'
						THEN 'NM_SALES_PROJECTION'
					WHEN @FORECASTING_TYPE = 'MANDATED'
						THEN 'M_SALES_PROJECTION'
					END
				,' SPM
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID) A
                        ON NSP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NSP.PERIOD_SID = A.PERIOD_SID
				where not exists  (SELECT 1
                                                FROM   '
				,@PROJECTION_TABLE
				,' N
                                                WHERE  N.CCP_DETAILS_SID = NSP.CCP_DETAILS_SID and N.PERIOD_SID = NSP.PERIOD_SID)'
				)

		EXEC sp_executesql @SQL

		-------------------if date was moved to some future periods those periods has to be deleted---------------------------------------------------------
		SET @SQL = CONCAT (
				'DELETE NSP
          FROM   '
				,@PROJECTION_TABLE
				,' NSP                
				where not exists  (SELECT 1
                                                FROM   #ST_NM_SALES_PROJECTION1 N
                                                WHERE  N.CCP_DETAILS_SID = NSP.CCP_DETAILS_SID and N.PERIOD_SID = NSP.PERIOD_SID)'
				)

		--select @sql
		EXEC sp_executesql @SQL
	END
	ELSE IF @SCREEN_NAME = 'DISCOUNT'
	BEGIN
		--------------------------------------------------------   
		IF Object_id('TEMPDB.DBO.#NM_DISCOUNT_PROJECTION', 'U') IS NOT NULL
			DROP TABLE #NM_DISCOUNT_PROJECTION;

		CREATE TABLE #NM_DISCOUNT_PROJECTION (
			CCP_DETAILS_SID INT
			,PERIOD_SID INT
			,RS_MODEL_SID INT
			,RS_CONTRACT_SID INT
			)

		SET @SQL = CONCAT (
				' INSERT INTO #NM_DISCOUNT_PROJECTION
                      (
                       CCP_DETAILS_SID,
                       PERIOD_SID,
                       RS_MODEL_SID,
                                     RS_CONTRACT_SID)
 
      SELECT
                                            CCP_DETAILS_SID,
                                                                           P.PERIOD_SID,
                                            RS_MODEL_SID,
                                                                           RS_CONTRACT_SID
                                     FROM   '
				,@MASTER_TABLE
				,' NDPM
                                                                  JOIN PERIOD P ON  PERIOD_DATE BETWEEN '''
				,@PROJECTION_START_DATE
				,''' AND '''
				,@PROJECTION_END_DATE
				,'''
                                     WHERE  NOT EXISTS
                                            (SELECT 1
                                             FROM
                                                '
				,@PROJECTION_TABLE
				,' N
                                            WHERE N.CCP_DETAILS_SID = NDPM.CCP_DETAILS_SID
                                                                             AND N.RS_MODEL_SID = NDPM.RS_MODEL_SID
                                                                             AND N.RS_CONTRACT_SID=NDPM.RS_CONTRACT_SID
                                                                             AND N.PERIOD_SID = P.PERIOD_SID
                                                                             )
                                         '
				)

		EXEC sp_executesql @SQL

		--       SET @SQL='  INSERT INTO ' + @PROJECTION_TABLE
		--                + ' 
		--                 (CCP_DETAILS_SID,
		--                  PERIOD_SID,
		--                  RS_MODEL_SID,
		--                  PROJECTION_RATE,
		--                  PROJECTION_SALES,
		--                  PROJECTION_RPU,
		--                  GROWTH,
		--   RS_CONTRACT_SID)
		--     SELECT NDP.CCP_DETAILS_SID,
		--            NDP.PERIOD_SID,
		--            NDP.RS_MODEL_SID,
		--            PROJECTION_RATE,
		--            PROJECTION_AMOUNT,
		--            PROJECTION_RPU,
		--            ISNULL(GROWTH, 0),
		--NDP.RS_CONTRACT_SID
		--     FROM   #NM_DISCOUNT_PROJECTION NDP
		--            LEFT JOIN (SELECT 
		--                              NDP.PERIOD_SID,
		--                              NDP.PROJECTION_RATE,
		--                              NDP.PROJECTION_SALES AS PROJECTION_AMOUNT,
		--                              NDP.PROJECTION_RPU,
		--                              NDP.GROWTH,
		--                              NDP.RS_MODEL_SID,
		--                              ACD.CCP_DETAILS_SID,
		--			   NDP.RS_CONTRACT_SID
		--                       FROM   NM_DISCOUNT_PROJECTION NDP
		--                              INNER JOIN #APPROVED_CCP_DETAILS ACD
		--                                      ON NDP.PROJECTION_DETAILS_SID =
		--                                         ACD.PROJECTION_DETAILS_SID) A
		--                   ON NDP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
		--                      AND NDP.RS_MODEL_SID = A.RS_MODEL_SID
		--                      AND NDP.PERIOD_SID = A.PERIOD_SID'
		--       EXEC sp_executesql @SQL
		-----------------------------existing ccps---------------------------------------
		--IF Object_id('TEMPDB.DBO.#NM_DISCOUNT_PROJECTION1', 'U') IS NOT NULL
		--	DROP TABLE #NM_DISCOUNT_PROJECTION1;
		--CREATE TABLE #NM_DISCOUNT_PROJECTION1 (
		--	CCP_DETAILS_SID INT
		--	,PERIOD_SID INT
		--	,RS_MODEL_SID INT
		--	,RS_CONTRACT_SID INT
		--	)
		--SET @SQL = CONCAT (
		--		' INSERT INTO #NM_DISCOUNT_PROJECTION1
		--                    (
		--                     CCP_DETAILS_SID,
		--                     PERIOD_SID,
		--                     RS_MODEL_SID,
		--			   RS_CONTRACT_SID)
		--        SELECT 
		--               CCP_DETAILS_SID,
		--               PERIOD_SID,
		--               RS_MODEL_SID,
		--		 RS_CONTRACT_SID
		--        FROM   (SELECT 
		--                       CCP_DETAILS_SID,
		--                       PERIOD_DATE,
		--                       PERIOD_SID,
		--                       RS_MODEL_SID,
		--				 RS_CONTRACT_SID
		--                FROM   PERIOD
		--                       CROSS JOIN (SELECT 
		--                                          CCP_DETAILS_SID,
		--                                          RS_MODEL_SID,
		--									RS_CONTRACT_SID
		--                                   FROM   '
		--		,@MASTER_TABLE
		--		,' NDPM
		--                                   WHERE  EXISTS
		--                                          (SELECT 1
		--                                           FROM
		--                                              '
		--		,@PROJECTION_TABLE
		--		,' N
		--                                          WHERE N.CCP_DETAILS_SID = NDPM.CCP_DETAILS_SID 
		--									  AND N.RS_MODEL_SID = NDPM.RS_MODEL_SID)) CCP
		--				WHERE  PERIOD_DATE BETWEEN '''
		--		,@PROJECTION_START_DATE
		--		,''' AND '''
		--		,@PROJECTION_END_DATE
		--		,''') A'
		--		)
		-- EXEC sp_executesql @SQL
		SET @SQL = CONCAT (
				'  INSERT INTO '
				,@PROJECTION_TABLE
				,
				'
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       RS_MODEL_SID,
                       PROJECTION_RATE,
                       PROJECTION_SALES,
                       PROJECTION_RPU,
                       GROWTH,
                                     RS_CONTRACT_SID)
          SELECT NDP.CCP_DETAILS_SID,
                 NDP.PERIOD_SID,
                 NDP.RS_MODEL_SID,
                 PROJECTION_RATE,
                 PROJECTION_AMOUNT,
                 PROJECTION_RPU,
                 ISNULL(GROWTH, 0),
                           NDP.RS_CONTRACT_SID
          FROM   #NM_DISCOUNT_PROJECTION NDP
                 LEFT JOIN (SELECT
                                   NDP.PERIOD_SID,
                                   NDP.PROJECTION_RATE,
                                   NDP.PROJECTION_SALES AS PROJECTION_AMOUNT,
                                   NDP.PROJECTION_RPU,
                                   NDP.GROWTH,
                                   NDP.RS_MODEL_SID,
                                   ACD.CCP_DETAILS_SID,
                                                          NDP.RS_CONTRACT_SID
                            FROM   NM_DISCOUNT_PROJECTION NDP
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON NDP.PROJECTION_DETAILS_SID =
                                              ACD.PROJECTION_DETAILS_SID) A
                        ON NDP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NDP.RS_MODEL_SID = A.RS_MODEL_SID
                           AND NDP.PERIOD_SID = A.PERIOD_SID
                                            AND A.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                                           where not EXISTS
                                            (SELECT 1
                                             FROM
                                                '
				,@PROJECTION_TABLE
				,' N
                                            WHERE N.CCP_DETAILS_SID = NDP.CCP_DETAILS_SID
                                                                             AND N.RS_MODEL_SID = NDP.RS_MODEL_SID
                                                                             AND N.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                                                                             and N.PERIOD_SID = NDP.PERIOD_SID)'
				)

		EXEC sp_executesql @SQL

		SET @SQL = CONCAT (
				'DELETE NSP
          FROM   '
				,@PROJECTION_TABLE
				,' NSP JOIN PERIOD P ON P.PERIOD_SID=NSP.PERIOD_SID AND P.PERIOD_DATE  NOT BETWEEN '''
				,@PROJECTION_START_DATE
				,''' AND '''
				,@PROJECTION_END_DATE
				,''''
				)

		EXEC sp_executesql @SQL
	END
END