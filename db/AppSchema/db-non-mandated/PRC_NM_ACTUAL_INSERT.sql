IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NM_ACTUAL_INSERT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NM_ACTUAL_INSERT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_NM_ACTUAL_INSERT] (@PROJECTION INT,
                                               @USER_ID    INT,
                                               @SESSION_ID VARCHAR(50),
                                               @SCREEN_NAME       VARCHAR(25))
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          DECLARE
                  @CCP_HIERARCHY      VARCHAR(200) = CONCAT('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
				   @FORECASTING_TYPE VARCHAR(50)
          DECLARE @FROM_DATE DATETIME
          DECLARE @START_FROM DATETIME
          DECLARE @PROJECTION_SAVE_FALG BIT
		  DECLARE @SQL  NVARCHAR(MAX)

		     SET @FORECASTING_TYPE=(SELECT FORECASTING_TYPE
                                 FROM   PROJECTION_MASTER
                                 WHERE  PROJECTION_MASTER_SID = @PROJECTION)

          SET @FROM_DATE = (SELECT DATEADD(YY, DATEDIFF(YY, 0, GETDATE()) - 3, 0))
          SET @START_FROM = (SELECT DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0))

          SELECT @PROJECTION_SAVE_FALG = SAVE_FLAG
          FROM   PROJECTION_MASTER PM
          WHERE  PROJECTION_MASTER_SID = @PROJECTION------GALUAT-871(ONLINE)
          /*------GALUAT-871(ONLINE)
	IF OBJECT_ID('TEMPDB..#APPROVED_CCP_DETAILS') IS NOT NULL
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
                  @SESSION_ID*/


          IF @SCREEN_NAME = 'SALES'
            BEGIN
                 DECLARE @SALES_ACTUAL_TABLE VARCHAR(200) =CASE WHEN @FORECASTING_TYPE='NON MANDATED'  THEN CONCAT('ST_NM_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
				                                             WHEN @FORECASTING_TYPE='MANDATED'  THEN CONCAT('ST_M_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')) END
                IF OBJECT_ID('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL
                  DROP TABLE #CCP_DETAILS_TEMP

                CREATE TABLE #CCP_DETAILS_TEMP
                  (
                     CCP_DETAILS_SID     INT,
                     COMPANY_MASTER_SID  INT,
                     ITEM_MASTER_SID     INT,
                     CONTRACT_MASTER_SID INT,
                     NEW                 BIT DEFAULT (0)
                  )

				SET @SQL='INSERT INTO #CCP_DETAILS_TEMP(CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID)
						  SELECT CH.CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID FROM '+@CCP_HIERARCHY+ ' CH JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID '
				EXEC sp_executesql @SQL

              
			 

           IF OBJECT_ID('TEMPDB..#ST_NM_ACTUAL_SALES') IS NOT NULL
                DROP TABLE #ST_NM_ACTUAL_SALES

                CREATE TABLE #ST_NM_ACTUAL_SALES
                  (
                     CCP_DETAILS_SID INT,
                     PERIOD_SID      INT,
                     ACTUAL_SALES    NUMERIC(22, 6),
                     ACTUAL_UNITS    NUMERIC(22, 6)
                  )

            

                --INSERT INTO @ACTUAL_CCP
                --            (CONTRACT_MASTER_SID,
                --             COMPANY_MASTER_SID,
                --             ITEM_MASTER_SID,
                --             PROJECTION_DETAILS_SID) -- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID WE ARE PASSING CCP_DETAILS_SID
                --SELECT CONTRACT_MASTER_SID,
                --       COMPANY_MASTER_SID,
                --       ITEM_MASTER_SID,
                --       CCP_DETAILS_SID
                --FROM   #CCP_DETAILS_TEMP

				INSERT INTO #ST_NM_ACTUAL_SALES
							(CCP_DETAILS_SID,
							 PERIOD_SID,
							 ACTUAL_SALES,
							 ACTUAL_UNITS)
				SELECT c.CCP_DETAILS_SID,-- THOUGH COLUMN NAME IS PROJECTION_DETAILS_SID IT WILL CONTAIN CCP_DETAILS_SID
					   A.PERIOD_SID,
					   SALES,
					   QUANTITY
				FROM   #CCP_DETAILS_TEMP c
					   CROSS JOIN (SELECT PERIOD_SID
								   FROM   PERIOD
								   WHERE  PERIOD_DATE BETWEEN @FROM_DATE AND @START_FROM)A
				   LEFT JOIN (SELECT CCP_DETAILS_SID,
									 PERIOD_SID,
									 Sum(SALES)    SALES,
									 Sum(QUANTITY) QUANTITY
							  FROM   [ACTUALS_DETAILS] ad
							  WHERE  QUANTITY_INCLUSION = 'Y'
							  GROUP  BY CCP_DETAILS_SID,
										PERIOD_SID) ad
						  ON a.PERIOD_SID = ad.PERIOD_SID
							 AND ad.CCP_DETAILS_SID = c.CCP_DETAILS_SID



				 IF @FORECASTING_TYPE='NON MANDATED' 
			  BEGIN
			  /*-----------GALUAT871(ONLINE)
                  IF OBJECT_ID('TEMPDB..#NEWLY_CCP_DETAILS') IS NOT NULL
                    DROP TABLE #NEWLY_CCP_DETAILS

                CREATE TABLE #NEWLY_CCP_DETAILS
                  (
                     CCP_DETAILS_SID INT PRIMARY KEY
                  )

                INSERT INTO #NEWLY_CCP_DETAILS
                            (CCP_DETAILS_SID)
                SELECT CCP_DETAILS_SID
                FROM   #CCP_DETAILS_TEMP
                WHERE  NOT EXISTS (SELECT PD.CCP_DETAILS_SID
                                               FROM   NM_ACTUAL_SALES NM
                                                      INNER JOIN PROJECTION_DETAILS PD
                                                              ON NM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                               WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION
											   AND PD.CCP_DETAILS_SID=#CCP_DETAILS_TEMP.CCP_DETAILS_SID)*/
		
                IF OBJECT_ID('TEMPDB..#ACTUAL_CCP_DETAILS') IS NOT NULL
                  DROP TABLE #ACTUAL_CCP_DETAILS

                CREATE TABLE #ACTUAL_CCP_DETAILS
                  (
                     CCP_DETAILS_SID INT PRIMARY KEY
                  )

                INSERT INTO #ACTUAL_CCP_DETAILS
                            (CCP_DETAILS_SID)
                SELECT CCP_DETAILS_SID
                FROM   #ST_NM_ACTUAL_SALES
                GROUP  BY CCP_DETAILS_SID
                HAVING ISNULL(SUM(ACTUAL_SALES), 0) = 0
                       AND ISNULL(SUM(ACTUAL_UNITS), 0) = 0

                /*-----------GALUAT871(ONLINE)
				IF ( @PROJECTION_SAVE_FALG = 0 )
                  BEGIN
                      UPDATE SS -----------PULL APPROVED ACTUALS FOR NEWLY CREATED PROJECTION
                      SET    SS.PERIOD_SID = NM.PERIOD_SID,
                             SS.ACTUAL_SALES = NM.ACTUAL_SALES,
                             SS.ACTUAL_UNITS = NM.ACTUAL_UNITS
                      FROM   #ST_NM_ACTUAL_SALES SS
                             INNER JOIN #ACTUAL_CCP_DETAILS S
                                     ON SS.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                             INNER JOIN #APPROVED_CCP_DETAILS A
                                     ON S.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                             INNER JOIN NM_ACTUAL_SALES NM
                                     ON A.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                        AND SS.PERIOD_SID = NM.PERIOD_SID
                  END
                ELSE*/
				IF ( @PROJECTION_SAVE_FALG <> 0 )
                  BEGIN/*-----------GALUAT871(ONLINE)
                      UPDATE SS ------------------PULL ACTUALS ACTUALS FOR EDITED PROJECTION
                      SET    SS.PERIOD_SID = O.PERIOD_SID,
                             SS.ACTUAL_SALES = O.ACTUAL_SALES,
                             SS.ACTUAL_UNITS = O.ACTUAL_UNITS
                      FROM   #ST_NM_ACTUAL_SALES SS
                             INNER JOIN (SELECT S.CCP_DETAILS_SID,
                                                NM.PERIOD_SID,
                                                NM.ACTUAL_SALES,
                                                NM.ACTUAL_UNITS
                                         FROM   #ACTUAL_CCP_DETAILS S
                                                INNER JOIN PROJECTION_DETAILS PD
                                                        ON PD.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                                                INNER JOIN NM_ACTUAL_SALES NM
                                                        ON PD.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                         WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION
                                         UNION
                                         SELECT S.CCP_DETAILS_SID,
                                                NM.PERIOD_SID,
                                                NM.ACTUAL_SALES,
                                                NM.ACTUAL_UNITS
                                         FROM   #NEWLY_CCP_DETAILS S
                                                INNER JOIN #ACTUAL_CCP_DETAILS AA
                                                        ON AA.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                                                INNER JOIN #APPROVED_CCP_DETAILS A
                                                        ON S.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                INNER JOIN NM_ACTUAL_SALES NM
                                                        ON A.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID) O
                                     ON SS.CCP_DETAILS_SID = O.CCP_DETAILS_SID
                                        AND SS.PERIOD_SID = O.PERIOD_SID*/
                      UPDATE SS ------------------PULL ACTUALS ACTUALS FOR EDITED PROJECTION
                      SET    SS.PERIOD_SID = O.PERIOD_SID,
                             SS.ACTUAL_SALES = O.ACTUAL_SALES,
                             SS.ACTUAL_UNITS = O.ACTUAL_UNITS
                      FROM   #ST_NM_ACTUAL_SALES SS
                             INNER JOIN (SELECT S.CCP_DETAILS_SID,
                                                NM.PERIOD_SID,
                                                NM.ACTUAL_SALES,
                                                NM.ACTUAL_UNITS
                                         FROM   #ACTUAL_CCP_DETAILS S
                                                INNER JOIN PROJECTION_DETAILS PD
                                                        ON PD.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                                                INNER JOIN NM_ACTUAL_SALES NM
                                                        ON PD.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                         WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION
                                         /*-----------GALUAT871(ONLINE)
										 UNION
                                         SELECT S.CCP_DETAILS_SID,
                                                NM.PERIOD_SID,
                                                NM.ACTUAL_SALES,
                                                NM.ACTUAL_UNITS
                                         FROM   #NEWLY_CCP_DETAILS S
                                                INNER JOIN #ACTUAL_CCP_DETAILS AA
                                                        ON AA.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                                                INNER JOIN #APPROVED_CCP_DETAILS A
                                                        ON S.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                INNER JOIN NM_ACTUAL_SALES NM
                                                        ON A.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID*/) O
                                     ON SS.CCP_DETAILS_SID = O.CCP_DETAILS_SID
                                        AND SS.PERIOD_SID = O.PERIOD_SID
                  END
				  	END
                DECLARE @SQL3 NVARCHAR(MAX)=''

                SET @SQL3 = CONCAT(' TRUNCATE TABLE ', @SALES_ACTUAL_TABLE)

                EXEC sp_executesql @SQL3

                DECLARE @SQL4 NVARCHAR(MAX)=''

                ------------------------------------TEMP ST_NM_ACTUAL_SALES TABLE INSERTION STARTS HERE-----------------------------------
                SET @SQL4 =CONCAT('INSERT INTO ', @SALES_ACTUAL_TABLE, '
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS)
          SELECT CCP_DETAILS_SID,
                 PERIOD_SID,
                 ACTUAL_SALES,
                 ACTUAL_UNITS
          FROM   #ST_NM_ACTUAL_SALES')

                EXEC sp_executesql @SQL4
            END
ELSE IF @SCREEN_NAME='DISCOUNT'
BEGIN 

 DECLARE @DISCOUNT_ACTUAL_TABLE VARCHAR(200) = CONCAT('ST_NM_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
 DECLARE @DISCOUNT_MASTER_TABLE VARCHAR(200) = CONCAT('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))



	     SET  @SQL= CONCAT('SELECT
		 
		        D.CCP_DETAILS_SID,  
				 R.RS_MODEL_SID,
				 R.RS_CONTRACT_SID
          FROM ', @DISCOUNT_MASTER_TABLE ,'   D
				 INNER JOIN CCP_DETAILS NDPM
				 ON NDPM.CCP_DETAILS_SID=D.CCP_DETAILS_SID
				 LEFT JOIN RS_Contract R ON
				 R.RS_MODEL_SID=D.RS_MODEL_SID
				 and r.contract_master_sid = NDPM.contract_master_sid
				 -----------and r.inbound_status <> ''D''
         ')
	   CREATE TABLE #CCP_INFO_DISCOUNT
         (
            CCP_DETAILS_SID     INT,
            CONTRACT_MASTER_SID INT,
			COMPANY_MASTER_SID  INT,
            ITEM_MASTER_SID     INT,
            RS_MODEL_SID        INT,
			RS_CONTRACT_SID     INT,
            RS_ID               VARCHAR(50)
         ) 
       
    INSERT INTO #CCP_INFO_DISCOUNT
	(
	 CCP_DETAILS_SID      	 ,
	 RS_MODEL_SID       	 ,
	 RS_CONTRACT_SID         
	)
          
EXEC sp_executesql @SQL 
    --    INSERT INTO   @ACTUAL_CCP
    --                  (CONTRACT_MASTER_SID,
    --                   COMPANY_MASTER_SID,
    --                   ITEM_MASTER_SID,
    --                   DISCOUNT_ID)
    -- SELECT  CONTRACT_MASTER_SID,
	   --      COMPANY_MASTER_SID,
			 --ITEM_MASTER_SID ,   
	        
			 --RS_ID     FROM   #CCP_INFO_DISCOUNT        
		
          ----------------------------------------------------------------------- ACTUAL DISCOUNT,RATE AND RATE PER UNIT CALCULATION  
                IF Object_id('TEMPDB.DBO.#NM_ACTUAL_DISCOUNT', 'U') IS NOT NULL
                  DROP TABLE #NM_ACTUAL_DISCOUNT;

                CREATE TABLE #NM_ACTUAL_DISCOUNT
                  (
                     CCP_DETAILS_SID INT,
                     PERIOD_SID      INT,
                     RS_MODEL_SID    INT,
                     RS_CONTRACT_SID INT,
                     ACTUAL_RATE     NUMERIC(22, 6),
                     ACTUAL_AMOUNT   NUMERIC(22, 6),
                     ACTUAL_RPU      NUMERIC(22, 6)
                  )

                 INSERT INTO #NM_ACTUAL_DISCOUNT
                            (CCP_DETAILS_SID,
                             PERIOD_SID,
                             RS_MODEL_SID,
                             RS_CONTRACT_SID,
                             ACTUAL_RATE,
                             ACTUAL_AMOUNT,
                             ACTUAL_RPU)
        		SELECT ccp.CCP_DETAILS_SID,
				   p.PERIOD_SID,
				   ccp.RS_MODEL_SID,
				   ccp.RS_CONTRACT_SID,
				   Isnull(( Max(DISCOUNT) ) / NULLIF(Max(SALES), 0), 0) * 100 ACTUAL_RATE,
				   Isnull(Max(DISCOUNT), 0)                                   ACTUAL_AMOUNT,
				   Isnull(( Max(DISCOUNT) ) / NULLIF(Max(QUANTITY), 0), 0)    ACTUAL_RPU
			FROM   #CCP_INFO_DISCOUNT ccp
				   CROSS JOIN (SELECT PERIOD_SID
							   FROM   PERIOD
							   WHERE  PERIOD_DATE BETWEEN @FROM_DATE AND @START_FROM) p
				   LEFT JOIN (SELECT CCP_DETAILS_SID,
									 PERIOD_SID,
									 RS_MODEL_SID,
									 Sum(DISCOUNT) DISCOUNT,
									 Sum(SALES)    SALES,
									 Sum(QUANTITY) QUANTITY
							  FROM   [ACTUALS_DETAILS]
							  GROUP  BY CCP_DETAILS_SID,
										PERIOD_SID,
										RS_MODEL_SID,
										QUANTITY_INCLUSION) A
						  ON a.CCP_DETAILS_SID = ccp.CCP_DETAILS_SID
							 AND a.RS_MODEL_SID = ccp.RS_MODEL_SID
							 AND a.PERIOD_SID = p.PERIOD_SID
			GROUP  BY ccp.CCP_DETAILS_SID,
					  p.PERIOD_SID,
					  ccp.RS_MODEL_SID,
					  ccp.RS_CONTRACT_SID 
          --------------- ---------------------------------------------------------------NEW PART STARTS  ---------------------------------------------------------------  
          /*-----------GALUAT871(ONLINE)
		  IF OBJECT_ID('TEMPDB..#NEW_CCP_DETAILS_DISCOUNT') IS NOT NULL
            DROP TABLE #NEW_CCP_DETAILS_DISCOUNT

          CREATE TABLE #NEW_CCP_DETAILS_DISCOUNT
            (
               CCP_DETAILS_SID INT,
               RS_CONTRACT_SID           INT
               PRIMARY KEY(CCP_DETAILS_SID, RS_CONTRACT_SID)
            )
	
          INSERT INTO #NEW_CCP_DETAILS_DISCOUNT
                      (CCP_DETAILS_SID,
                       RS_CONTRACT_SID)
          SELECT DISTINCT CCP_DETAILS_SID,
                          RS_MODEL_SID
          FROM   #CCP_INFO_DISCOUNT
          WHERE  NOT EXISTS (SELECT PD.CCP_DETAILS_SID
                                                FROM   NM_ACTUAL_DISCOUNT NM
                 INNER JOIN PROJECTION_DETAILS PD                --CHANGE 2
                         ON NM.PROJECTION_DETAILS_SID =PD.PROJECTION_DETAILS_SID
				 WHERE PD.PROJECTION_MASTER_SID=@PROJECTION AND PD.CCP_DETAILS_SID=#CCP_INFO_DISCOUNT.CCP_DETAILS_SID
             )*/

          IF OBJECT_ID('TEMPDB..#ACTUAL_CCP_DETAILS1') IS NOT NULL
            DROP TABLE #ACTUAL_CCP_DETAILS1

          CREATE TABLE #ACTUAL_CCP_DETAILS1
            (
               CCP_DETAILS_SID INT PRIMARY KEY
            )

          INSERT INTO #ACTUAL_CCP_DETAILS1
                      (CCP_DETAILS_SID)
          SELECT DISTINCT CCP_DETAILS_SID
          FROM   #NM_ACTUAL_DISCOUNT
          GROUP  BY CCP_DETAILS_SID
          HAVING ISNULL(SUM(ACTUAL_AMOUNT), 0) = 0

          IF @PROJECTION_SAVE_FALG = 1
            BEGIN/*-----------GALUAT871(ONLINE)
                UPDATE SS
                SET    SS.ACTUAL_AMOUNT = O.ACTUAL_SALES
                FROM   #NM_ACTUAL_DISCOUNT SS
                       INNER JOIN (SELECT S.CCP_DETAILS_SID,
                                          NM.RS_MODEL_SID,
										  NM.RS_CONTRACT_SID,
                                          NM.PERIOD_SID,
                                          NM.ACTUAL_SALES
                                   FROM   #ACTUAL_CCP_DETAILS1 S
										   INNER JOIN PROJECTION_DETAILS PD   --CHANGE 3
                                                  ON PD.CCP_DETAILS_SID =
                                                     S.CCP_DETAILS_SID
                                          INNER JOIN NM_ACTUAL_DISCOUNT NM
                                                  ON PD.PROJECTION_DETAILS_SID =
                                                     NM.PROJECTION_DETAILS_SID
                                          
                                   WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION
                                   UNION
                                   SELECT S.CCP_DETAILS_SID,
                                          NM.RS_MODEL_SID,
										  NM.RS_CONTRACT_SID,
                                          NM.PERIOD_SID,
                                          NM.ACTUAL_SALES
                                   FROM   #NEW_CCP_DETAILS_DISCOUNT S
                                          INNER JOIN #ACTUAL_CCP_DETAILS1 AA
                                                  ON AA.CCP_DETAILS_SID =
                                                     S.CCP_DETAILS_SID
                                          INNER JOIN PROJECTION_DETAILS PD   --CHANGE 4
                                                  ON PD.CCP_DETAILS_SID =
                                                     S.CCP_DETAILS_SID
                                          INNER JOIN #APPROVED_CCP_DETAILS A
                                                  ON PD.CCP_DETAILS_SID =
                                                     A.CCP_DETAILS_SID
                                          INNER JOIN NM_ACTUAL_DISCOUNT NM
                                                  ON A.PROJECTION_DETAILS_SID =
                                                     NM.PROJECTION_DETAILS_SID)
                                  O
                               ON SS.CCP_DETAILS_SID =
                                  O.CCP_DETAILS_SID
                                  AND SS.PERIOD_SID = O.PERIOD_SID
                                  AND SS.RS_CONTRACT_SID = O.RS_CONTRACT_SID*/
                UPDATE SS
                SET    SS.ACTUAL_AMOUNT = O.ACTUAL_SALES
                FROM   #NM_ACTUAL_DISCOUNT SS
                       INNER JOIN (SELECT S.CCP_DETAILS_SID,
                                          NM.RS_MODEL_SID,
										  NM.RS_CONTRACT_SID,
                                          NM.PERIOD_SID,
                                          NM.ACTUAL_SALES
                                   FROM   #ACTUAL_CCP_DETAILS1 S
										   INNER JOIN PROJECTION_DETAILS PD   --CHANGE 3
                                                  ON PD.CCP_DETAILS_SID =
                                                     S.CCP_DETAILS_SID
                                          INNER JOIN NM_ACTUAL_DISCOUNT NM
                                                  ON PD.PROJECTION_DETAILS_SID =
                                                     NM.PROJECTION_DETAILS_SID
                                          
                                   WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION
                                   /*-----------GALUAT871(ONLINE)
								   UNION
                                   SELECT S.CCP_DETAILS_SID,
                                          NM.RS_MODEL_SID,
										  NM.RS_CONTRACT_SID,
                                          NM.PERIOD_SID,
                                          NM.ACTUAL_SALES
                                   FROM   #NEW_CCP_DETAILS_DISCOUNT S
                                          INNER JOIN #ACTUAL_CCP_DETAILS1 AA
                                                  ON AA.CCP_DETAILS_SID =
                                                     S.CCP_DETAILS_SID
                                          INNER JOIN PROJECTION_DETAILS PD   --CHANGE 4
                                                  ON PD.CCP_DETAILS_SID =
                                                     S.CCP_DETAILS_SID
                                          INNER JOIN #APPROVED_CCP_DETAILS A
                                                  ON PD.CCP_DETAILS_SID =
                                                     A.CCP_DETAILS_SID
                                          INNER JOIN NM_ACTUAL_DISCOUNT NM
                                                  ON A.PROJECTION_DETAILS_SID =
                                                     NM.PROJECTION_DETAILS_SID*/)
                                  O
                               ON SS.CCP_DETAILS_SID =
                                  O.CCP_DETAILS_SID
                                  AND SS.PERIOD_SID = O.PERIOD_SID
                                  AND SS.RS_CONTRACT_SID = O.RS_CONTRACT_SID
            END
          /*-----------GALUAT871(ONLINE)
		  ELSE
            BEGIN
                UPDATE SS
                SET    SS.CCP_DETAILS_SID = S.CCP_DETAILS_SID,
                       SS.PERIOD_SID = NM.PERIOD_SID,
                       SS.ACTUAL_AMOUNT = NM.ACTUAL_SALES
                FROM   #NM_ACTUAL_DISCOUNT SS
                       INNER JOIN #ACTUAL_CCP_DETAILS1 S
                               ON SS.CCP_DETAILS_SID =
                                  S.CCP_DETAILS_SID
                       INNER JOIN PROJECTION_DETAILS PD   --CHANGE 5
                               ON PD.CCP_DETAILS_SID =
                                  S.CCP_DETAILS_SID
                       INNER JOIN #APPROVED_CCP_DETAILS A
                               ON PD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       INNER JOIN NM_ACTUAL_DISCOUNT NM
                               ON A.PROJECTION_DETAILS_SID =
                                  NM.PROJECTION_DETAILS_SID
                                  AND SS.PERIOD_SID = NM.PERIOD_SID
                                  AND SS.RS_CONTRACT_SID = NM.RS_CONTRACT_SID
            END
			*/

          --------------------------------------------------------NEW PART ENDS -------------------------------------------------------------------------------  
        SET @SQL=  CONCAT('TRUNCATE TABLE  ',@DISCOUNT_ACTUAL_TABLE )
		EXEC sp_executesql @SQL

	
          ----------------------------- IF (NORMAL MODE - ACTUAL DISCOUNT,RATE AND RATE PER UNIT ) ELSE (EDIT MODE- REINSERT ACTUAL DISCOUNT,RATE AND RATE PER UNIT FOR OLD AND NEWLY ADDED CCPD)  
          SET @SQL= 'INSERT INTO '+@DISCOUNT_ACTUAL_TABLE+' 
                      (CCP_DETAILS_SID,
                       RS_MODEL_SID,
					   RS_CONTRACT_SID,
                       PERIOD_SID,
                       ACTUAL_RATE,
                       ACTUAL_SALES,
                       ACTUAL_RPU)
          SELECT CCP_DETAILS_SID,
                 RS_MODEL_SID,
				 RS_CONTRACT_SID,
                 PERIOD_SID,
                 ACTUAL_RATE,
                 ACTUAL_AMOUNT,
                 ACTUAL_RPU
          FROM   #NM_ACTUAL_DISCOUNT'


		  EXEC sp_executesql @SQL

END
      END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC USPERRORCOLLECTOR

          SELECT @ERRORMESSAGE = ERROR_MESSAGE(),
                 @ERRORSEVERITY = ERROR_SEVERITY(),
                 @ERRORSTATE = ERROR_STATE(),
                 @ERRORPROCEDURE = ERROR_PROCEDURE(),
                 @ERRORLINE = ERROR_LINE(),
                 @ERRORNUMBER = ERROR_NUMBER()

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