IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NM_PROJECTION_INSERT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NM_PROJECTION_INSERT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_NM_PROJECTION_INSERT] (@PROJECTION  INT,
                                                   @USER_ID     INT,
                                                   @SESSION_ID  VARCHAR(50),
                                                   @SCREEN_NAME VARCHAR(50))
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          DECLARE @FORECASTING_TYPE VARCHAR(50)

          SET @FORECASTING_TYPE = (SELECT FORECASTING_TYPE
                                   FROM   PROJECTION_MASTER
                                   WHERE  PROJECTION_MASTER_SID = @PROJECTION)

          DECLARE @MASTER_TABLE     VARCHAR(200) = CASE
                      WHEN @SCREEN_NAME = 'SALES'
                           AND @FORECASTING_TYPE = 'NON MANDATED' THEN Concat ('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                      WHEN @SCREEN_NAME = 'SALES'
                           AND @FORECASTING_TYPE = 'MANDATED' THEN Concat ('ST_M_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                      WHEN @SCREEN_NAME = 'DISCOUNT'
                           AND @FORECASTING_TYPE = 'NON MANDATED' THEN Concat ('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                    END,
                  @PROJECTION_TABLE VARCHAR(200) = CASE
                      WHEN @SCREEN_NAME = 'SALES'
                           AND @FORECASTING_TYPE = 'NON MANDATED' THEN Concat ('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                      WHEN @SCREEN_NAME = 'SALES'
                           AND @FORECASTING_TYPE = 'MANDATED' THEN Concat ('ST_M_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                      WHEN @SCREEN_NAME = 'DISCOUNT'
                           AND @FORECASTING_TYPE = 'NON MANDATED' THEN Concat ('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                    END,
                  @CCP_HIERARCHY    VARCHAR(200) = Concat ('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @STATUS_TABLE     VARCHAR(200) = Concat ('ST_STATUS_TABLE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @PROJECTION_START_DATE DATETIME,
                  @PROJECTION_END_DATE   DATETIME,
                  @SQL                   NVARCHAR(MAX)

          SELECT @PROJECTION_START_DATE = Datefromparts(Year(FROM_DATE), 1, 1),
                 @PROJECTION_END_DATE = Datefromparts(Year(TO_DATE), 12, 1)
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @PROJECTION

          IF Object_id('TEMPDB..#APPROVED_CCP_DETAILS') IS NOT NULL
            DROP TABLE #APPROVED_CCP_DETAILS

          CREATE TABLE #APPROVED_CCP_DETAILS
            (
               PROJECTION_MASTER_SID  INT,
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT
            )

          INSERT INTO #APPROVED_CCP_DETAILS
                      (PROJECTION_MASTER_SID,
                       PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID)
          EXEC PRC_APPROVED_CCP_DETAILS
            @PROJECTION,
            @FORECASTING_TYPE,
            @USER_ID,
            @SESSION_ID

        /*  SET @SQL =Concat('UPDATE ', @STATUS_TABLE, ' ---with (NOLOCK)
SET    FLAG = ''R''
WHERE   ''', @SCREEN_NAME, '''  = SCREEN_NAME
       AND VIEW_NAME = ''PRC_NM_PROJECTION_INSERT''')

          BEGIN TRAN

          EXEC Sp_executesql
            @SQL

          COMMIT TRAN;*/

          IF @SCREEN_NAME = 'SALES'
            BEGIN
                IF Object_id('TEMPDB..#ST_NM_SALES_PROJECTION') IS NOT NULL
                  DROP TABLE #ST_NM_SALES_PROJECTION

                CREATE TABLE #ST_NM_SALES_PROJECTION
                  (
                     CCP_DETAILS_SID INT,
                     PERIOD_SID      INT
                  )

                SET @SQL = Concat ('INSERT INTO #ST_NM_SALES_PROJECTION
                      (CCP_DETAILS_SID,
                       PERIOD_SID)
          SELECT CCP_DETAILS_SID,
                 PERIOD_SID
          FROM   PERIOD
                 CROSS JOIN (SELECT CCP_DETAILS_SID
                             FROM  ', @CCP_HIERARCHY, ' NSPM ) A
          WHERE  PERIOD_DATE BETWEEN ''', @PROJECTION_START_DATE, ''' AND ''', @PROJECTION_END_DATE, '''')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat ('IF NOT EXISTS (SELECT 1 FROM ', @PROJECTION_TABLE, ')
				BEGIN
				INSERT INTO ', @PROJECTION_TABLE, '
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
                            FROM  ', CASE
                                                                  WHEN @FORECASTING_TYPE = 'NON MANDATED' THEN 'NM_SALES_PROJECTION'
                                                                  WHEN @FORECASTING_TYPE = 'MANDATED' THEN 'M_SALES_PROJECTION'
                                                                END, ' SPM
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON SPM.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
										   AND  SPM.PROJECTION_MASTER_SID = ACD.PROJECTION_MASTER_SID
										   WHERE NOT EXISTS (SELECT 1 FROM NM_SALES_PROJECTION CCP --JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=CCP.PROJECTION_DETAILS_SID
										     WHERE CCP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID AND CCP.PROJECTION_MASTER_SID=', @PROJECTION, ')
									UNION ALL			 
					SELECT SPM.ACCOUNT_GROWTH,
                                   SPM.PRODUCT_GROWTH,
                                   SPM.PROJECTION_SALES,
                                   SPM.PROJECTION_UNITS,
                                   SPM.PERIOD_SID,
                                   SPM.CCP_DETAILS_SID
                            FROM  ', CASE
                                                                  WHEN @FORECASTING_TYPE = 'NON MANDATED' THEN 'NM_SALES_PROJECTION'
                                                                  WHEN @FORECASTING_TYPE = 'MANDATED' THEN 'M_SALES_PROJECTION'
                                                                END, ' SPM --JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=SPM.PROJECTION_DETAILS_SID		
							WHERE SPM.PROJECTION_MASTER_SID=', @PROJECTION, '											 
											 ) A
                        ON NSP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NSP.PERIOD_SID = A.PERIOD_SID 
						   END 
									 		 
								')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat ('IF EXISTS (SELECT 1 FROM ', @PROJECTION_TABLE, ')
				BEGIN
				INSERT INTO ', @PROJECTION_TABLE, '
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
                            FROM  ', CASE
                                                                  WHEN @FORECASTING_TYPE = 'NON MANDATED' THEN 'NM_SALES_PROJECTION'
                                                                  WHEN @FORECASTING_TYPE = 'MANDATED' THEN 'M_SALES_PROJECTION'
                                                                END, ' SPM
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON SPM.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
										   AND  SPM.PROJECTION_MASTER_SID = ACD.PROJECTION_MASTER_SID
										   WHERE NOT EXISTS (SELECT 1 FROM NM_SALES_PROJECTION CCP --JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=CCP.PROJECTION_DETAILS_SID
										     WHERE CCP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID AND CCP.PROJECTION_MASTER_SID= ', @PROJECTION, ')
								) A
                        ON NSP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NSP.PERIOD_SID = A.PERIOD_SID 
						   WHERE NOT EXISTS (SELECT 1 FROM ', @PROJECTION_TABLE, ' CCP WHERE  
                         NSP.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                           AND NSP.PERIOD_SID = CCP.PERIOD_SID)
						   END 
									 		 
								')

                EXEC Sp_executesql
                  @SQL

                -----------------------------ADDITION OF ADDITION PERIODS FOR THE EXISTING CCP'S-------
            /*	IF OBJECT_ID('TEMPDB..#ST_NM_SALES_PROJECTION1') IS NOT NULL
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
            
            	EXEC SP_EXECUTESQL @SQL
            
            	-----------------------------ADDITION OF ADDITION PERIODS FOR THE EXISTING CCP'S-------
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
            			WHERE NOT EXISTS  (SELECT 1
                                                           FROM   '
            			,@PROJECTION_TABLE
            			,' N
                                                           WHERE  N.CCP_DETAILS_SID = NSP.CCP_DETAILS_SID AND N.PERIOD_SID = NSP.PERIOD_SID)'
            			)
            
            	EXEC SP_EXECUTESQL @SQL*/
                -------------------IF DATE WAS MOVED TO SOME FUTURE PERIODS THOSE PERIODS HAS TO BE DELETED---------------------------------------------------------
                SET @SQL = Concat ('DELETE NSP
          FROM   ', @PROJECTION_TABLE, ' NSP                
				WHERE NOT EXISTS  (SELECT 1
                                                FROM   #ST_NM_SALES_PROJECTION N
                                                WHERE  N.CCP_DETAILS_SID = NSP.CCP_DETAILS_SID AND N.PERIOD_SID = NSP.PERIOD_SID)')

                --SELECT @SQL
                EXEC Sp_executesql
                  @SQL
            END
          ELSE IF @SCREEN_NAME = 'DISCOUNT'
            BEGIN
                --------------------------------------------------------   
                IF Object_id('TEMPDB.DBO.#NM_DISCOUNT_PROJECTION', 'U') IS NOT NULL
                  DROP TABLE #NM_DISCOUNT_PROJECTION;

                CREATE TABLE #NM_DISCOUNT_PROJECTION
                  (
                     CCP_DETAILS_SID INT,
                     PERIOD_SID      INT,
                     RS_MODEL_SID    INT,
                     RS_CONTRACT_SID INT
                  )

                SET @SQL = Concat (' INSERT INTO #NM_DISCOUNT_PROJECTION
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
                                     FROM   ', @MASTER_TABLE, ' NDPM
                                                                  JOIN PERIOD P ON  PERIOD_DATE BETWEEN ''', @PROJECTION_START_DATE, ''' AND ''', @PROJECTION_END_DATE, '''')

                EXEC Sp_executesql
                  @SQL

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
                --       EXEC SP_EXECUTESQL @SQL
                -----------------------------EXISTING CCPS---------------------------------------
                --IF OBJECT_ID('TEMPDB.DBO.#NM_DISCOUNT_PROJECTION1', 'U') IS NOT NULL
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
                -- EXEC SP_EXECUTESQL @SQL
                SET @SQL = Concat ('  
				IF NOT EXISTS (SELECT 1 FROM ', @PROJECTION_TABLE, ')
				BEGIN
				INSERT INTO ', @PROJECTION_TABLE, '
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       RS_MODEL_SID,
                      
                       PROJECTION_SALES,
                      
                       GROWTH,
                                     RS_CONTRACT_SID)
          SELECT NDP.CCP_DETAILS_SID,
                 NDP.PERIOD_SID,
                 NDP.RS_MODEL_SID,
                 PROJECTION_AMOUNT,
                 ISNULL(GROWTH, 0),
                 NDP.RS_CONTRACT_SID
          FROM   #NM_DISCOUNT_PROJECTION NDP
                 LEFT JOIN (SELECT
                                   NDP.PERIOD_SID,
                                   NDP.PROJECTION_SALES AS PROJECTION_AMOUNT,
                                   NDP.GROWTH,
                                   NDP.RS_MODEL_SID,
                                   ACD.CCP_DETAILS_SID,
                                   NDP.RS_CONTRACT_SID
                            FROM   NM_DISCOUNT_PROJECTION NDP
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON NDP.PROJECTION_MASTER_SID = ACD.PROJECTION_MASTER_SID
										   and NDP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
										    WHERE NOT EXISTS (SELECT 1 FROM NM_DISCOUNT_PROJECTION CCP --JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=CCP.PROJECTION_DETAILS_SID
										     WHERE CCP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
                           AND NDP.RS_CONTRACT_SID = CCP.RS_CONTRACT_SID 
						   AND NDP.PERIOD_SID = CCP.PERIOD_SID 
						   AND CCP.PROJECTION_MASTER_SID=', @PROJECTION, '	)
				UNION ALL
				SELECT
                                   NDP.PERIOD_SID,
                                   NDP.PROJECTION_SALES AS PROJECTION_AMOUNT,
                                   NDP.GROWTH,
                                   NDP.RS_MODEL_SID,
                                   NDP.CCP_DETAILS_SID,
                                   NDP.RS_CONTRACT_SID
                            FROM   NM_DISCOUNT_PROJECTION NDP
							--JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=NDP.PROJECTION_DETAILS_SID		
							WHERE NDP.PROJECTION_MASTER_SID=', @PROJECTION, '												 										 		  
											  ) A
                        ON NDP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NDP.RS_MODEL_SID = A.RS_MODEL_SID
                           AND NDP.PERIOD_SID = A.PERIOD_SID
                                            AND A.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                                      
									  END 
									     ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat ('  
				IF EXISTS (SELECT 1 FROM ', @PROJECTION_TABLE, ')
				BEGIN
				INSERT INTO ', @PROJECTION_TABLE, '
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       RS_MODEL_SID,
                       PROJECTION_SALES,
                       GROWTH,
                       RS_CONTRACT_SID)
          SELECT NDP.CCP_DETAILS_SID,
                 NDP.PERIOD_SID,
                 NDP.RS_MODEL_SID,
                 PROJECTION_AMOUNT,
                 ISNULL(GROWTH, 0),
                 NDP.RS_CONTRACT_SID
          FROM   #NM_DISCOUNT_PROJECTION NDP
                 LEFT JOIN (SELECT
                                   NDP.PERIOD_SID,
                                   NDP.PROJECTION_SALES AS PROJECTION_AMOUNT,
                                   NDP.GROWTH,
                                   NDP.RS_MODEL_SID,
                                   ACD.CCP_DETAILS_SID,
                                   NDP.RS_CONTRACT_SID
                            FROM   NM_DISCOUNT_PROJECTION NDP
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON NDP.PROJECTION_MASTER_SID = ACD.PROJECTION_MASTER_SID
										   and NDP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
										    WHERE NOT EXISTS (SELECT 1 FROM NM_DISCOUNT_PROJECTION CCP --JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=CCP.PROJECTION_DETAILS_SID
										     WHERE CCP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
                           AND NDP.RS_CONTRACT_SID = CCP.RS_CONTRACT_SID 
						   AND NDP.PERIOD_SID = CCP.PERIOD_SID 
						   AND CCP.PROJECTION_MASTER_SID= ', @PROJECTION, ')) A
                        ON NDP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NDP.RS_MODEL_SID = A.RS_MODEL_SID
                           AND NDP.PERIOD_SID = A.PERIOD_SID
                           AND A.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                           WHERE NOT EXISTS (SELECT 1 FROM ', @PROJECTION_TABLE, ' X WHERE NDP.CCP_DETAILS_SID = X.CCP_DETAILS_SID
                           AND NDP.RS_MODEL_SID = X.RS_MODEL_SID
                           AND NDP.PERIOD_SID = X.PERIOD_SID
                                            AND X.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
									  )
									  END 
									     ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL = Concat ('DELETE NSP
          FROM   ', @PROJECTION_TABLE, ' NSP                
				WHERE NOT EXISTS  (SELECT 1
                          FROM    #NM_DISCOUNT_PROJECTION N
                          WHERE  N.CCP_DETAILS_SID = NSP.CCP_DETAILS_SID 
						  AND N.PERIOD_SID = NSP.PERIOD_SID
						   AND N.RS_MODEL_SID = NSP.RS_MODEL_SID
						   AND N.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
						  )')

                EXEC Sp_executesql
                  @SQL
            END

          SET @SQL =Concat('UPDATE ', @STATUS_TABLE, ' ---with (NOLOCK)
SET    FLAG = ''C''
WHERE   ''', @SCREEN_NAME, '''  = SCREEN_NAME
       AND VIEW_NAME = ''PRC_NM_PROJECTION_INSERT''')

          BEGIN TRAN

          EXEC Sp_executesql
            @SQL

          COMMIT TRAN;
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
