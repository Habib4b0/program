IF Object_id('PRC_NM_MASTER_INSERT', 'P') IS NOT NULL
  DROP PROCEDURE PRC_NM_MASTER_INSERT

GO

CREATE PROCEDURE [dbo].[PRC_NM_MASTER_INSERT] (@PROJECTION  INT,
                                               @USER_ID     INT,
                                               @SESSION_ID  VARCHAR(50),
                                               @SCREEN_NAME VARCHAR(25),
											   @FREQUENCY CHAR(1)=NULL,
											   @DEDUCTION_FILTER  VARCHAR(50)=NULL)
/**********************************************************************************************************
** File Name		:	PRC_NM_MASTER_INSERT.sql
** Procedure Name	:	PRC_NM_MASTER_INSERT
** Description		:	Inserting actuals data information for particular projection 
** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Created for showing discount
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
						@SCREEN_NAME            - it will insert into master table based on screenname
											
** Output Parameters:	NA
** Author Name		:	
** Called By		:   called during Generate on Data Selection screen
                        
*********************************************************************************************************/
------------------------DECLARING VARIABLES STARTS HERE-----------------
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          DECLARE @CCP_HIERARCHY    VARCHAR(200) = Concat('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @FORECASTING_TYPE VARCHAR(50),
                  @STATUS_TABLE     VARCHAR(200) = Concat ('ST_STATUS_TABLE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @SQL NVARCHAR(MAX)

          SET @FORECASTING_TYPE=(SELECT FORECASTING_TYPE
                                 FROM   PROJECTION_MASTER
                                 WHERE  PROJECTION_MASTER_SID = @PROJECTION)

          DECLARE @MASTER_TABLE VARCHAR(200) = ( CASE
                WHEN @FORECASTING_TYPE IN ( 'NON MANDATED', 'COMMERCIAL' ) THEN Concat('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                WHEN @FORECASTING_TYPE IN ( 'MANDATED', 'GOVERNMENT' ) THEN Concat('ST_M_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
              END )

          ------------------------DECLARING VARIABLES ENDS HERE-----------------
          ------------------------------PULLING APPROVED CCP DETAILS STARTS HERE--------------------------
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

          ------------------------------PULLING APPROVED CCP DETAILS ENDS HERE--------------------------
          IF Object_id('TEMPDB..#EFFECTIVE_DATE') IS NOT NULL
            DROP TABLE #EFFECTIVE_DATE

          CREATE TABLE #EFFECTIVE_DATE
            (
               CCP_DETAILS_SID INT,
               RS_MODEL_SID    INT,
               RS_CONTRACT_SID INT,
               RS_CATOGERY     VARCHAR(100),
               START_DATE      DATETIME,
               END_DATE        DATETIME,
               START_SID       INT,
               END_SID         INT
            )
/*
          BEGIN TRAN

          SET @SQL =Concat('UPDATE ', @STATUS_TABLE, ' ---WITH (NOLOCK)
SET    FLAG = ''R''
WHERE  ''', @SCREEN_NAME, ''' = SCREEN_NAME
       AND VIEW_NAME = ''PRC_NM_MASTER_INSERT''')

          EXEC Sp_executesql
            @SQL

          COMMIT TRAN;
*/
          ---------------MASTER INSERT OF  SALES STARTS HERE---------------------------------------------
          IF @SCREEN_NAME = 'SALES'
            BEGIN
                IF Object_id('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL
                  DROP TABLE #CCP_DETAILS_TEMP

                CREATE TABLE #CCP_DETAILS_TEMP
                  (
                     CCP_DETAILS_SID     INT,
                     COMPANY_MASTER_SID  INT,
                     ITEM_MASTER_SID     INT,
                     CONTRACT_MASTER_SID INT
                  )

                SET @SQL='INSERT INTO #CCP_DETAILS_TEMP(CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID)
						  SELECT CH.CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID FROM '
                         + @CCP_HIERARCHY
                         + ' CH JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID '

                EXEC Sp_executesql
                  @SQL

                ------------------SETTING ALL INPUT PARMATERS VALUES INTO TABLE--------------------------------
                DECLARE @SQL1 NVARCHAR(MAX)=''

            /*SET @SQL1 = Concat('INSERT INTO ', @MASTER_TABLE, '
                  (CCP_DETAILS_SID,
                   METHODOLOGY,
                   CHECK_RECORD,', CASE
                                                                 WHEN @FORECASTING_TYPE = 'NON MANDATED' THEN 'USER_GROUP,'
                                                               END, '
                   CALCULATION_PERIODS
            --,
                   --CALCULATION_BASED
            )
            SELECT SPM.CCP_DETAILS_SID,
             A.METHODOLOGY,
             0,', CASE
                                                WHEN @FORECASTING_TYPE = 'NON MANDATED' THEN 'COALESCE(A.USER_GROUP, ''0''),'
                                              END, '
             CALCULATION_PERIODS 
             --,CALCULATION_BASED
            FROM   #CCP_DETAILS_TEMP SPM
             LEFT JOIN (SELECT SPM.METHODOLOGY,
                               ', CASE
                                                                WHEN @FORECASTING_TYPE = 'NON MANDATED' THEN 'SPM.USER_GROUP,'
                                                              END, '
                               SPM.CALCULATION_PERIODS,
                               --SPM.CALCULATION_BASED,
                               0 AS CHECK_RECORD,
                               ACD.CCP_DETAILS_SID
                        FROM   ', CASE
                                                                WHEN @FORECASTING_TYPE = 'NON MANDATED' THEN 'NM_SALES_PROJECTION_MASTER'
                                                                WHEN @FORECASTING_TYPE = 'MANDATED' THEN 'M_SALES_PROJECTION_MASTER'
                                                              END, ' SPM
                               INNER JOIN #APPROVED_CCP_DETAILS ACD
                                       ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID) A -- BRINGS THE DATA FROM MAIN TABLE FOR THE APPROVED PROJECTION
                    ON SPM.CCP_DETAILS_SID = A.CCP_DETAILS_SID
            WHERE NOT EXISTS (SELECT 1 FROM ', @MASTER_TABLE, ' CCP   WHERE CCP.CCP_DETAILS_SID=SPM.CCP_DETAILS_SID)
            ')
            */
                --EXEC sp_executesql @SQL1
                ---select @SQL1, @MASTER_TABLE
                SET @SQL1 = Concat('
			if not exists (select 1 from ', @MASTER_TABLE, ')
			begin 
			INSERT INTO ', @MASTER_TABLE, '
                      (CCP_DETAILS_SID,
                       METHODOLOGY,
                       CHECK_RECORD,', CASE
                                                                     WHEN @FORECASTING_TYPE = 'NON MANDATED' THEN 'USER_GROUP,'
                                                                   END, '
                       CALCULATION_PERIODS
					   --,
                       --CALCULATION_BASED
					   )
          SELECT SPM.CCP_DETAILS_SID,
                 A.METHODOLOGY,
                 0,COALESCE(A.USER_GROUP, ''0''),
                 CALCULATION_PERIODS 
                 --,CALCULATION_BASED
          FROM   #CCP_DETAILS_TEMP SPM
                 LEFT JOIN (SELECT SPM.METHODOLOGY,
                                   SPM.USER_GROUP,
                                   SPM.CALCULATION_PERIODS,
                                   --SPM.CALCULATION_BASED,
                                   0 AS CHECK_RECORD,
                                   ACD.CCP_DETAILS_SID
                            FROM   NM_SALES_PROJECTION_MASTER SPM
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON SPM.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
										   AND  SPM.PROJECTION_MASTER_SID = ACD.PROJECTION_MASTER_SID
										   WHERE NOT EXISTS (SELECT 1 FROM NM_SALES_PROJECTION_MASTER ccp --JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=ccp.PROJECTION_DETAILS_SID
										     WHERE ccp.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID and ccp.projection_master_sid=', @PROJECTION, '	)
										   union all
SELECT SPM.METHODOLOGY,
                                   SPM.USER_GROUP,
                                   SPM.CALCULATION_PERIODS,
                                   --SPM.CALCULATION_BASED,
                                   spm.CHECK_RECORD,
                                   SPM.CCP_DETAILS_SID
                            FROM   NM_SALES_PROJECTION_MASTER SPM  
							--JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=SPM.PROJECTION_DETAILS_SID		
							WHERE PROJECTION_MASTER_SID=', @PROJECTION, '								   

										   ) A -- BRINGS THE DATA FROM MAIN TABLE FOR THE APPROVED PROJECTION
                        ON SPM.CCP_DETAILS_SID = A.CCP_DETAILS_SID
						end 
						')

                EXEC Sp_executesql
                  @SQL1

                SET @SQL1 = Concat('
			if exists (select 1 from ', @MASTER_TABLE, ')
			begin 
			INSERT INTO ', @MASTER_TABLE, '
                      (CCP_DETAILS_SID,
                       METHODOLOGY,
                       CHECK_RECORD,', CASE
                                                                     WHEN @FORECASTING_TYPE = 'NON MANDATED' THEN 'USER_GROUP,'
                                                                   END, '
                       CALCULATION_PERIODS
					   --,
                       --CALCULATION_BASED
					   )
          SELECT SPM.CCP_DETAILS_SID,
                 A.METHODOLOGY,
                 0,COALESCE(A.USER_GROUP, ''0''),
                 CALCULATION_PERIODS 
                 --,CALCULATION_BASED
          FROM   #CCP_DETAILS_TEMP SPM
                 LEFT JOIN (SELECT SPM.METHODOLOGY,
                                     SPM.USER_GROUP,
                                     SPM.CALCULATION_PERIODS,
                                     --SPM.CALCULATION_BASED,
                                     0 AS CHECK_RECORD,
                                     ACD.CCP_DETAILS_SID
                              FROM   NM_SALES_PROJECTION_MASTER SPM
                                     INNER JOIN #APPROVED_CCP_DETAILS ACD
                                             ON SPM.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
                                                AND SPM.PROJECTION_MASTER_SID = ACD.PROJECTION_MASTER_SID
                              WHERE  NOT EXISTS (SELECT 1
                                                 FROM   NM_SALES_PROJECTION_MASTER CCP --JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=CCP.PROJECTION_DETAILS_SID
                                                 WHERE  CCP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
                                                        AND CCP.PROJECTION_MASTER_SID = ', @PROJECTION, ')) A -- BRINGS THE DATA FROM MAIN TABLE FOR THE APPROVED PROJECTION
                          ON SPM.CCP_DETAILS_SID = A.CCP_DETAILS_SID
            WHERE  NOT EXISTS (SELECT 1
                               FROM   ', @MASTER_TABLE, ' CCP
                               WHERE  SPM.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID)
						end 
						')

                EXEC Sp_executesql
                  @SQL1

                SET @SQL1= Concat('DELETE NM FROM ', @MASTER_TABLE, ' NM WHERE NOT EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CD WHERE CD.CCP_DETAILS_SID=NM.CCP_DETAILS_SID ) ')

                EXEC Sp_executesql
                  @SQL1

                SET @SQL1=Concat('UPDATE MS
					   SET SALES_INCLUSION=CASE 
               
		WHEN DESCRIPTION = ''YES''
			THEN 1
		ELSE 0
		END
          FROM   #CCP_DETAILS_TEMP SPM JOIN CFP_CONTRACT CFP ON CFP.CONTRACT_MASTER_SID=SPM.CONTRACT_MASTER_SID
		  JOIN CFP_CONTRACT_DETAILS CFPD ON CFPD.CFP_CONTRACT_SID=CFP.CFP_CONTRACT_SID
		  JOIN ', @MASTER_TABLE, ' MS ON MS.CCP_DETAILS_SID=SPM.CCP_DETAILS_SID
		  JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = CFP.SALES_INCLUSION
		  AND CFPD.COMPANY_MASTER_SID=SPM.COMPANY_MASTER_SID
		  AND CFP.INBOUND_STATUS<>''D''')

                EXEC Sp_executesql
                  @SQL1

                ------------------SETTING ALL INPUT PARMATERS VALUES INTO TABLE ENDS HERE--------------------------------
                INSERT INTO #EFFECTIVE_DATE
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             RS_CONTRACT_SID,
                             RS_CATOGERY,
                             START_DATE,
                             END_DATE,
                             START_SID,
                             END_SID)
                EXEC PRC_DATE_FINDER
                  @PROJECTION,
                  'C',
                  @USER_ID,
                  @SESSION_ID

                SET @SQL=Concat(' UPDATE A SET EFFECTIVE_START_PERIOD_SID=E.START_SID,
		                                  EFFECTIVE_END_PERIOD_SID=E.END_SID
								   FROM ', @MASTER_TABLE, ' A JOIN #EFFECTIVE_DATE E
								   ON A.CCP_DETAILS_SID=E.CCP_DETAILS_SID
								   
								   ')

                EXEC Sp_executesql
                  @SQL
            END
          ----------------------------------------------STORING CCP+D+PRICE LEVEL INFORMATION STARTS HERE-------------------------
          ELSE IF @SCREEN_NAME = 'DISCOUNT'
            BEGIN
                DECLARE @DISC_MASTER_TABLE VARCHAR(200) = Concat('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

                IF Object_id('TEMPDB.DBO.#NM_DISCOUNT_PROJ_MASTER', 'U') IS NOT NULL
                  DROP TABLE #NM_DISCOUNT_PROJ_MASTER;

                CREATE TABLE #NM_DISCOUNT_PROJ_MASTER
                  (
                     CCP_DETAILS_SID     INT,
                     CONTRACT_MASTER_SID INT,
                     COMPANY_MASTER_SID  INT,
                     ITEM_MASTER_SID     INT,
                     PRICE_GROUP_TYPE    VARCHAR(50),
                     RS_MODEL_SID        INT,
                     RS_CONTRACT_SID     INT,
                     RS_ID               VARCHAR(50),
                     NET_FLAG            CHAR(1)
                  )

                SET @SQL= Concat('INSERT INTO #NM_DISCOUNT_PROJ_MASTER
                                                    (CCP_DETAILS_SID,
                                                     CONTRACT_MASTER_SID,
                                                     COMPANY_MASTER_SID,
                                                     ITEM_MASTER_SID,
                                                     PRICE_GROUP_TYPE,
                                                     RS_MODEL_SID,
                                                     RS_CONTRACT_SID,
                                                     RS_ID,
                                                     NET_FLAG) SELECT
                                         CCP_DETAILS_SID,
                                         CONTRACT_MASTER_SID,
                                         COMPANY_MASTER_SID,
                                         ITEM_MASTER_SID,
                                         PRICE_GROUP_TYPE,
                                         RS_MODEL_SID,
                        				 RS_CONTRACT_SID,
                                         RS_ID,
                                         NET_FLAG
                                  FROM   (SELECT DISTINCT
                                                          B.CCP_DETAILS_SID,
                                                          B.CONTRACT_MASTER_SID,
                                                          B.COMPANY_MASTER_SID,
                                                          B.ITEM_MASTER_SID,
                                                          PRICE_GROUP_TYPE=CASE
                                                                             WHEN
                                                          HT.DESCRIPTION = ''REIMB''
                                                                           THEN
                                                                             ''REIMBURSEMENT''
                                                                             WHEN HT.DESCRIPTION = ''OI''
                                                                           THEN
                                                                             ''OFF INVOICE''
                                                                             ELSE HT.DESCRIPTION
                                                                           END,
                                                          R1.RS_MODEL_SID,
                        								  R1.RS_CONTRACT_SID,
                                                          R1.RS_ID,
                                                          NET_FLAG = ( CASE
                                                                         WHEN HT.DESCRIPTION LIKE ''OFF%''
                                                                       THEN
                                                                         ''N''
                                                                         ELSE ''Y''
                                                                       END )
                                          FROM   '
                                 + @CCP_HIERARCHY
                                 + ' A  --CHANGE 1
                                                 JOIN CCP_DETAILS B
                                                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                                 JOIN RS_CONTRACT R1
                                                   ON R1.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
                                                 JOIN RS_CONTRACT_DETAILS R2
                                                   ON R2.RS_CONTRACT_SID = R1.RS_CONTRACT_SID
                                                      AND R2.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                                 LEFT JOIN HELPER_TABLE HT
                                                        ON HT.HELPER_TABLE_SID = R1.REBATE_PROGRAM_TYPE
                                                           AND HT.LIST_NAME = ''REBATE_PROGRAM_TYPE''
                                                 LEFT JOIN UDCS U
                                                        ON U.MASTER_SID = R1.RS_CONTRACT_SID
                                                           AND U.MASTER_TYPE = ''RS_CONTRACT''
                                                 LEFT JOIN HELPER_TABLE H2
                                                        ON H2.HELPER_TABLE_SID = U.UDC3
                                          WHERE   R1.INBOUND_STATUS <> ''D''
                                                 AND R2.INBOUND_STATUS <> ''D''
                                                 AND EXISTS (SELECT 1
                                                             FROM   CFP_CONTRACT CFP1
                                                                    JOIN CFP_CONTRACT_DETAILS CFP2
                                                                      ON CFP1.CFP_CONTRACT_SID =
                                                                         CFP2.CFP_CONTRACT_SID
                                                                         AND R1.CFP_CONTRACT_SID =
                                                                             CFP1.CFP_CONTRACT_SID
                                                             WHERE  COMPANY_MASTER_SID =
                                                                    B.COMPANY_MASTER_SID
                                                                    AND CFP1.CONTRACT_MASTER_SID =
                                         B.CONTRACT_MASTER_SID))A WHERE NOT EXISTS (SELECT 1
                         FROM   NM_DISCOUNT_PROJ_MASTER CCP --JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=CCP.PROJECTION_DETAILS_SID
                         WHERE  CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                AND CCP.RS_CONTRACT_SID = A.RS_CONTRACT_SID
                                AND PROJECTION_MASTER_SID = ', @PROJECTION, ')
                        
                        						   UNION ALL
                        
                             SELECT CCP.CCP_DETAILS_SID,
             CD.CONTRACT_MASTER_SID,
             CD.COMPANY_MASTER_SID,
             CD.ITEM_MASTER_SID,
             CCP.PRICE_GROUP_TYPE,
             CCP.RS_MODEL_SID,
             CCP.RS_CONTRACT_SID,
             R.RS_ID,
             CCP.NET_FLAG
      FROM   NM_DISCOUNT_PROJ_MASTER CCP --JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=CCP.PROJECTION_DETAILS_SID
             JOIN RS_CONTRACT R
               ON R.RS_CONTRACT_SID = CCP.RS_CONTRACT_SID
             JOIN CCP_DETAILS CD
               ON CD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
      WHERE  PROJECTION_MASTER_SID = ', @PROJECTION, '') --GALUAT-360 
                EXEC Sp_executesql
                  @SQL

                ----------------------------------------------STORING CCP+D+PRICE LEVEL INFORMATION ENDS HERE-------------------------
                ----------------------------------------------INSERTING  FINAL VALUES INTO MASTER TABLE LEVEL INFORMATION STARTS HERE-------------------------
                DECLARE @RELATION_SID INT

                SET @RELATION_SID=(SELECT DED_RELATIONSHIP_BULDER_SID
                                   FROM   PROJECTION_MASTER
                                   WHERE  PROJECTION_MASTER_SID = @PROJECTION)
                SET @SQL= Concat('
		  IF NOT EXISTS (SELECT 1 FROM ', @DISC_MASTER_TABLE, ')
		  BEGIN
		  INSERT INTO ', @DISC_MASTER_TABLE, '
                      (CCP_DETAILS_SID,
                       RS_MODEL_SID,
					   RS_CONTRACT_SID,
                       METHODOLOGY,
                       CALCULATION_PERIODS,
                      --BASELINE_PERIODS,
                      --SELECTED_PERIODS,
                       PRICE_GROUP_TYPE,
                       NET_FLAG,
                       USER_GROUP,
                       CHECK_RECORD,DEDUCTION_HIERARCHY_NO)
          SELECT NDPM.CCP_DETAILS_SID,
                 NDPM.RS_MODEL_SID,
				 NDPM.RS_CONTRACT_SID,
                 A.METHODOLOGY,
                 A.CALCULATION_PERIODS,
                -- A.BASELINE_PERIODS,
                 --A.SELECTED_PERIODS,
                 NDPM.PRICE_GROUP_TYPE,
                 NDPM.NET_FLAG,
                 COALESCE(A.USER_GROUP, ''0''),
                 COALESCE(A.CHECK_RECORD, 0), ', CASE
                                                                             WHEN @RELATION_SID IS NOT NULL THEN 'RLD.HIERARCHY_NO'
                                                                             ELSE ''' '''
                                                                           END, '  AS DEDUCTION_HIERARCHY_NO
FROM #NM_DISCOUNT_PROJ_MASTER NDPM  
		  ', CASE
                                   WHEN @RELATION_SID IS NOT NULL THEN Concat('JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.RELATIONSHIP_LEVEL_VALUES=NDPM.RS_CONTRACT_SID
		    
		   JOIN RELATIONSHIP_BUILDER RB ON RB.RELATIONSHIP_BUILDER_SID=RLD.RELATIONSHIP_BUILDER_SID AND RB.RELATIONSHIP_BUILDER_SID=', @RELATION_SID, ' AND LEVEL_NO=10 
		   AND RB.VERSION_NO=RLD.VERSION_NO')
                                   ELSE ' '
                                 END, '
                 LEFT JOIN (SELECT NDPM.METHODOLOGY,
                                   NDPM.USER_GROUP,
                                   NDPM.RS_MODEL_SID,
								   NDPM.RS_CONTRACT_SID,
                                   NDPM.PRICE_GROUP_TYPE,
                                   NDPM.CALCULATION_PERIODS,
                                   --NDPM.BASELINE_PERIODS,
                                   NDPM.CHECK_RECORD,
                                   --NDPM.SELECTED_PERIODS,
                                   ACD.CCP_DETAILS_SID
                            FROM    NM_DISCOUNT_PROJ_MASTER NDPM
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON NDPM.PROJECTION_MASTER_SID = ACD.PROJECTION_MASTER_SID
										   and NDPM.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
										    WHERE NOT EXISTS (SELECT 1 FROM NM_DISCOUNT_PROJ_MASTER CCP --JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=CCP.PROJECTION_DETAILS_SID
										     WHERE CCP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
                           AND NDPM.RS_CONTRACT_SID = CCP.RS_CONTRACT_SID  AND CCP.PROJECTION_MASTER_SID=', @PROJECTION, ')
									 UNION ALL

						   SELECT NDPM.METHODOLOGY,
                                   NDPM.USER_GROUP,
                                   NDPM.RS_MODEL_SID,
								   NDPM.RS_CONTRACT_SID,
                                   NDPM.PRICE_GROUP_TYPE,
                                   NDPM.CALCULATION_PERIODS,
                                   --NDPM.BASELINE_PERIODS,
                                   NDPM.CHECK_RECORD,
                                   --NDPM.SELECTED_PERIODS,
                                   NDPM.CCP_DETAILS_SID
                            FROM   NM_DISCOUNT_PROJ_MASTER NDPM
							---JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=NDPM.PROJECTION_DETAILS_SID
						   WHERE NDPM.PROJECTION_MASTER_SID=', @PROJECTION, '	   
										   ) A
                        ON NDPM.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NDPM.RS_CONTRACT_SID = A.RS_CONTRACT_SID
						   	 --WHERE NOT EXISTS (SELECT 1 FROM ', @DISC_MASTER_TABLE, ' A 
										 --    WHERE  NDPM.CCP_DETAILS_SID = A.CCP_DETAILS_SID
           --                AND NDPM.RS_CONTRACT_SID = A.RS_CONTRACT_SID )

					END	   
					')

                EXEC Sp_executesql
                  @SQL

                SET @SQL= Concat('
		  IF EXISTS (SELECT 1 FROM ', @DISC_MASTER_TABLE, ')
		  BEGIN
		  INSERT INTO ' + @DISC_MASTER_TABLE
                                                                              + '
                      (CCP_DETAILS_SID,
                       RS_MODEL_SID,
					   RS_CONTRACT_SID,
                       METHODOLOGY,
                       CALCULATION_PERIODS,
                      --BASELINE_PERIODS,
                      --SELECTED_PERIODS,
                       PRICE_GROUP_TYPE,
                       NET_FLAG,
                       USER_GROUP,
                       CHECK_RECORD,DEDUCTION_HIERARCHY_NO)
          SELECT NDPM.CCP_DETAILS_SID,
                 NDPM.RS_MODEL_SID,
				 NDPM.RS_CONTRACT_SID,
                 A.METHODOLOGY,
                 A.CALCULATION_PERIODS,
                -- A.BASELINE_PERIODS,
                 --A.SELECTED_PERIODS,
                 NDPM.PRICE_GROUP_TYPE,
                 NDPM.NET_FLAG,
                 COALESCE(A.USER_GROUP, ''0''),
                 COALESCE(A.CHECK_RECORD, 0), ', CASE
                                                                             WHEN @RELATION_SID IS NOT NULL THEN 'RLD.HIERARCHY_NO'
                                                                             ELSE ''' '''
                                                                           END, '  AS DEDUCTION_HIERARCHY_NO
FROM #NM_DISCOUNT_PROJ_MASTER NDPM
		  ', CASE
                                   WHEN @RELATION_SID IS NOT NULL THEN Concat('JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.RELATIONSHIP_LEVEL_VALUES=NDPM.RS_CONTRACT_SID
		    
		   JOIN RELATIONSHIP_BUILDER RB ON RB.RELATIONSHIP_BUILDER_SID=RLD.RELATIONSHIP_BUILDER_SID AND RB.RELATIONSHIP_BUILDER_SID=', @RELATION_SID, ' AND LEVEL_NO=10
		   AND RB.VERSION_NO=RLD.VERSION_NO')
                                   ELSE ' '
                                 END, '
                 LEFT JOIN (SELECT NDPM.METHODOLOGY,
                                   NDPM.USER_GROUP,
                                   NDPM.RS_MODEL_SID,
								   NDPM.RS_CONTRACT_SID,
                                   NDPM.PRICE_GROUP_TYPE,
                                   NDPM.CALCULATION_PERIODS,
                                   --NDPM.BASELINE_PERIODS,
                                   NDPM.CHECK_RECORD,
                                   --NDPM.SELECTED_PERIODS,
                                   ACD.CCP_DETAILS_SID
                            FROM   NM_DISCOUNT_PROJ_MASTER NDPM
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON NDPM.PROJECTION_MASTER_SID = ACD.PROJECTION_MASTER_SID
										   and NDPM.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
										    WHERE NOT EXISTS (SELECT 1 FROM NM_DISCOUNT_PROJ_MASTER CCP --JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID=CCP.PROJECTION_DETAILS_SID
										     WHERE CCP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID
                           AND NDPM.RS_CONTRACT_SID = CCP.RS_CONTRACT_SID  AND CCP.PROJECTION_MASTER_SID=', @PROJECTION, '	)
										   ) A
                        ON NDPM.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NDPM.RS_CONTRACT_SID = A.RS_CONTRACT_SID
					 	 WHERE NOT EXISTS (SELECT 1 FROM ', @DISC_MASTER_TABLE, ' A
										     WHERE  NDPM.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NDPM.RS_CONTRACT_SID = A.RS_CONTRACT_SID )

					END
 
					')

                EXEC Sp_executesql
                  @SQL

                SET @SQL= Concat('DELETE NM FROM ', @DISC_MASTER_TABLE, ' NM WHERE NOT EXISTS (SELECT 1 FROM ', @CCP_HIERARCHY, ' CD WHERE CD.CCP_DETAILS_SID=NM.CCP_DETAILS_SID) ')

                EXEC Sp_executesql
                  @SQL

                SET @SQL1=Concat('UPDATE DMT
					   SET DEDUCTION_INCLUSION=CASE 
               
		WHEN DESCRIPTION = ''YES''
			THEN 1
		ELSE 0
		END 
          FROM  #NM_DISCOUNT_PROJ_MASTER NDPM JOIN RS_CONTRACT  RS ON RS.CONTRACT_MASTER_SID=NDPM.CONTRACT_MASTER_SID AND NDPM.RS_MODEL_SID=RS.RS_MODEL_SID
 AND RS.RS_CONTRACT_SID=NDPM.RS_CONTRACT_SID
		  JOIN RS_CONTRACT_DETAILS RCD ON RCD.RS_CONTRACT_SID=RS.RS_CONTRACT_SID
		  JOIN ', @DISC_MASTER_TABLE, ' DMT ON DMT.CCP_DETAILS_SID=NDPM.CCP_DETAILS_SID
		  AND DMT.RS_CONTRACT_SID=NDPM.RS_CONTRACT_SID AND NDPM.RS_MODEL_SID=DMT.RS_MODEL_SID
		  JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.DEDUCTION_INCLUSION   AND NDPM.ITEM_MASTER_SID=RCD.ITEM_MASTER_SID
		  AND RS.INBOUND_STATUS<>''D''')

                EXEC Sp_executesql
                  @SQL1

                ----------------------------------------------INSERTING  FINAL VALUES INTO MASTER TABLE LEVEL INFORMATION ENDS HERE-------------------------
                ----------------------------------------------PULLING START_DATE,END_DATE,START_SID,END_SID  FROM PROCEDURE AND UPDATING IN MASTER TABLESTARTS HERE-------------------------
                INSERT INTO #EFFECTIVE_DATE
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             RS_CONTRACT_SID,
                             RS_CATOGERY,
                             START_DATE,
                             END_DATE,
                             START_SID,
                             END_SID)
                EXEC PRC_DATE_FINDER
                  @PROJECTION,
                  'D',
                  @USER_ID,
                  @SESSION_ID

                SET @SQL=Concat(' UPDATE A SET EFFECTIVE_START_PERIOD_SID=E.START_SID,
		                                  EFFECTIVE_END_PERIOD_SID=E.END_SID
								   FROM ', @DISC_MASTER_TABLE, ' A JOIN #EFFECTIVE_DATE E
								   ON A.CCP_DETAILS_SID=E.CCP_DETAILS_SID AND A.RS_MODEL_SID=E.RS_MODEL_SID
								   
								   ')

                EXEC Sp_executesql
                  @SQL
            ----------------------------------------------PULLING START_DATE,END_DATE,START_SID,END_SID  FROM PROCEDURE AND UPDATING IN MASTER TABLE ENDS HERE-------------------------
            END

          BEGIN TRAN

          SET @SQL =Concat('UPDATE ', @STATUS_TABLE, ' ---WITH (NOLOCK)
SET    FLAG = ''C''
WHERE  ''', @SCREEN_NAME, ''' = SCREEN_NAME
       AND VIEW_NAME = ''PRC_NM_MASTER_INSERT''')

          EXEC Sp_executesql
            @SQL

          COMMIT TRAN;
		   DECLARE @PV_FILTERS_TABLE VARCHAR(100),@SALES_MASTER_TABLE varchar(200)=Concat('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

SET @PV_FILTERS_TABLE= CONCAT('ST_CCP_PV_FILTERS_', @USER_ID, '_', @SESSION_ID, '_',  REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))

SET @SQL= CONCAT('
            IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''',@PV_FILTERS_TABLE,'''
                      AND TABLE_SCHEMA = ''DBO'')
					  BEGIN
			   DROP TABLE ',@PV_FILTERS_TABLE,' 
       END
           CREATE TABLE ',@PV_FILTERS_TABLE,'
                   (
				   CCP_DETAILS_SID     INT,
				   RS_CONTRACT_SID     INT,
				   PV_FILTERS		   BIT

				   )'

)

EXEC SP_EXECUTESQL @SQL
SET @SQL= CONCAT(' insert into ',@PV_FILTERS_TABLE,'  
select distinct ccp_details_sid,rs_contract_sid,1 PV_FILTERS from  ', @DISC_MASTER_TABLE, '
union all
select distinct ccp_details_sid,0 as rs_contract_sid,1 PV_FILTERS from  ', @SALES_MASTER_TABLE, ' s where not exists
( select 1 from ', @DISC_MASTER_TABLE, ' D where d.ccp_details_sid=s.ccp_details_Sid)')

		  
		  IF @SCREEN_NAME='DISCOUNT'
		  BEGIN
		  EXEC SP_EXECUTESQL @SQL
		  EXEC PRC_NM_PROJECTION_INSERT @PROJECTION,@USER_ID,@SESSION_ID,@SCREEN_NAME
		  DECLARE @VAR1 CHAR(1),@CUST_MASTER_SID INT
		  SET @VAR1=(SELECT CASE WHEN SAVE_FLAG=1 THEN 'E' ELSE 'G' END SAVE_FLAG FROM PROJECTION_MASTER WHERE PROJECTION_MASTER_SID=@PROJECTION)
		  SET @CUST_MASTER_SID=(SELECT CUSTOM_VIEW_MASTER_DEDUCTION_SID FROM PROJECTION_MASTER WHERE PROJECTION_MASTER_SID=@PROJECTION)
		  EXEC PRC_VIEWS_POPULATION @PROJECTION,@USER_ID,@SESSION_ID,@VAR1,@FREQUENCY,@SCREEN_NAME,'C',null,null,null,null,null,null,null,null,@DEDUCTION_FILTER
		  EXEC PRC_VIEWS_POPULATION @PROJECTION,@USER_ID,@SESSION_ID,@VAR1,@FREQUENCY,@SCREEN_NAME,'P',null,null,null,null,null,null,null,null,@DEDUCTION_FILTER
		  EXEC PRC_VIEWS_POPULATION @PROJECTION,@USER_ID,@SESSION_ID,@VAR1,@FREQUENCY,@SCREEN_NAME,'U',null,null,null,@CUST_MASTER_SID,null,null,null,null,@DEDUCTION_FILTER
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

