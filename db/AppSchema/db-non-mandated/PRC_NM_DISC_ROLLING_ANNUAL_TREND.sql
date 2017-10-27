IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NM_DISC_ROLLING_ANNUAL_TREND'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NM_DISC_ROLLING_ANNUAL_TREND]
  END

GO

CREATE PROCEDURE [dbo].[PRC_NM_DISC_ROLLING_ANNUAL_TREND](@PROJECTION INT,
                                                          @FREQUENCY  VARCHAR(20),
                                                          @USER_ID    INT,
                                                          @SESSION_ID VARCHAR(50))
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          DECLARE @PROJECTION_START_SID INT,
                  @PROJECTION_END_SID   INT,
				  @SQL                  NVARCHAR(MAX)


		     DECLARE @MASTER_TABLE        VARCHAR(200) = Concat('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID,'_',replace(convert(varchar(50),getdate(),2),'.','')),
					@ACTUAL_TABLE        VARCHAR(200) = Concat('ST_NM_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID,'_',replace(convert(varchar(50),getdate(),2),'.','')),
					@PROJECTION_TABLE    VARCHAR(200) = Concat('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID,'_',replace(convert(varchar(50),getdate(),2),'.','')),
					@S_PROJECTION_TABLE  VARCHAR(200)=Concat('ST_NM_SALES_PROJECTION_', @user_id, '_', @session_id,'_',replace(convert(varchar(50),getdate(),2),'.','')),
                    @S_ACTUAL_TABLE      VARCHAR(200)=Concat('ST_NM_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID,'_',replace(convert(varchar(50),getdate(),2),'.','')),
					@S_MASTER_TABLE      VARCHAR(200)=Concat('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID,'_',replace(convert(varchar(50),getdate(),2),'.',''))



          SET @FREQUENCY = LEFT(@FREQUENCY, 1)

-------------------------------------------------------------------------PROJECTION_STARTS AND END DATES---------------------------------------------------------------------------------
          
		  SELECT @PROJECTION_START_SID = PROJECTION_START_SID,
                 @PROJECTION_END_SID = PROJECTION_END_SID + CASE WHEN @FREQUENCY = 'M' THEN 0 WHEN @FREQUENCY = 'Q' THEN 2 WHEN @FREQUENCY = 'S' THEN 5 ELSE 11 END -- ADDED CASE PART TO GET CORRECT PERIOD_SID (ADDED ON 22 MARCH 2016 - UAT  )
          FROM   [DBO].[UDF_PROJECTION_DATES](@PROJECTION, @FREQUENCY)
		  

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
          IF OBJECT_ID('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
            DROP TABLE #PERIOD;

          SELECT PERIOD_SID,
                 PERIOD_DATE,
                 MONTH AS PERIOD_MONTH,
                 QUARTER AS PERIOD_QUARTER, 
                 SEMI_ANNUAL AS PERIOD_SEMI,
                 YEAR AS PERIOD_YEAR, 
                 CASE
                            WHEN @FREQUENCY = 'M' THEN CONCAT ('M', MONTH, ' ', YEAR)
                            WHEN @FREQUENCY = 'Q' THEN CONCAT ('Q', QUARTER, ' ', YEAR)
                            WHEN @FREQUENCY = 'S' THEN CONCAT ('S', SEMI_ANNUAL, ' ', YEAR)
                            ELSE CAST(YEAR AS CHAR(4))
                          END AS PERIOD
          INTO   #PERIOD
          FROM   PERIOD
-------------------------------------------------------------------TEMP_EFFECTIVE--------------------------------------------------------------------------------------------------------
      
	  
	  
      IF OBJECT_ID('TEMPDB.DBO.#TEMP_EFFECTIVE', 'U') IS NOT NULL
        DROP TABLE #TEMP_EFFECTIVE;

      WITH CTE
           AS (SELECT PROJECTION_DETAILS_SID,
                      START_DATE,
                      END_DATE
               FROM   [DBO].[UDF_DATE_FINDER](@PROJECTION, 'C'))
      SELECT A.PROJECTION_DETAILS_SID,
            
			  MAX(CASE WHEN START_DATE = P.PERIOD_DATE THEN P.PERIOD_SID
                                   END) AS START_SID,
             
			   MAX(CASE WHEN ( DATEADD(DAY, 1, EOMONTH(END_DATE, -1)) ) = P1.PERIOD_DATE THEN P1.PERIOD_SID
                                   END) AS END_SID
      INTO   #TEMP_EFFECTIVE
      FROM   CTE A
             LEFT JOIN #PERIOD P
                    ON P.PERIOD_DATE = A.START_DATE
             LEFT JOIN #PERIOD P1
                    ON P1.PERIOD_DATE = ( DATEADD(DAY, 1, EOMONTH(END_DATE, -1)) )
      GROUP  BY A.PROJECTION_DETAILS_SID

-------------------------------------------------------------------------------CCP_DETAILS-----------------------------------------------------------------------------------------------
         
		 IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL
            DROP TABLE #TEMP_CCP;

			 IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL
                           DROP TABLE #TEMP_CCP;
                      
					  CREATE TABLE #TEMP_CCP
					  (
					  CONTRACT_MASTER_SID            INT,
					  COMPANY_MASTER_SID             INT,
					  ITEM_MASTER_SID                INT,
					  DISCOUNT_ID					 VARCHAR(50),
					  PROJECTION_DETAILS_SID         INT,
					  PROJECTION_MASTER_SID          INT   ,
					  RS_MODEL_SID                   INT  ,
					  METHODOLOGY                    VARCHAR(50),
					  CALCULATION_BASED              VARCHAR(20),
					  CALCULATION_PERIODS            VARCHAR(MAX)    ,
					 FORECAST_START_PERIOD_SID          INT      ,
					 FORECAST_END_PERIOD_SID            INT      ,
                      CALC_START_PERIOD_SID				 INT,
					  CALC_END_PERIOD_SID				INT,
					  )
      SET @SQL=CONCAT(    'INSERT INTO #TEMP_CCP
		  (
		   CONTRACT_MASTER_SID       ,
		   COMPANY_MASTER_SID        ,
		   ITEM_MASTER_SID           ,
		   DISCOUNT_ID				 ,
		   PROJECTION_DETAILS_SID    ,
		   PROJECTION_MASTER_SID     ,
		   RS_MODEL_SID              ,
		   METHODOLOGY               ,
		   CALCULATION_BASED         ,
		   CALCULATION_PERIODS       ,
		  FORECAST_START_PERIOD_SID  ,
		  FORECAST_END_PERIOD_SID    ,
		   CALC_START_PERIOD_SID	,
		   CALC_END_PERIOD_SID		
		  )
          SELECT CONTRACT_MASTER_SID,
                 COMPANY_MASTER_SID,
                 ITEM_MASTER_SID,
                 RS_ID AS DISCOUNT_ID,
                 PD.PROJECTION_DETAILS_SID,
                 PD.PROJECTION_MASTER_SID,
                 SNDPM.RS_MODEL_SID,
                 SNDPM.METHODOLOGY,
                 CALCULATION_BASED,
                 SNDPM.CALCULATION_PERIODS,
                 SNDPM.FORECAST_START_PERIOD_SID,
                 SNDPM.FORECAST_END_PERIOD_SID,
                 IIF(FORECAST_START_PERIOD_SID>START_SID,
												IIF(FORECAST_START_PERIOD_SID>CS.MAX_BASELINE_PERIOD,FORECAST_START_PERIOD_SID,CS.MAX_BASELINE_PERIOD),
												IIF(START_SID>CS.MAX_BASELINE_PERIOD,START_SID,CS.MAX_BASELINE_PERIOD)) AS CALC_START_PERIOD_SID,
                 
				 IIF(COALESCE(FORECAST_END_PERIOD_SID, @PROJECTION_END_SID) < END_SID ,COALESCE(FORECAST_END_PERIOD_SID,  @PROJECTION_END_SID),END_SID) AS CALC_END_PERIOD_SID

          FROM   CCP_DETAILS CCP
                 JOIN PROJECTION_DETAILS PD
                   ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                 JOIN ',@MASTER_TABLE, '  SNDPM
                   ON PD.PROJECTION_DETAILS_SID = SNDPM.PROJECTION_DETAILS_SID
                 JOIN RS_MODEL R
                   ON R.RS_MODEL_SID = SNDPM.RS_MODEL_SID
				 JOIN #TEMP_EFFECTIVE TE
				   ON TE.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                 CROSS APPLY (SELECT TOP 1 P.PERIOD_SID + 1 AS MAX_BASELINE_PERIOD
                              FROM   UDF_SPLITSTRING(SNDPM.CALCULATION_PERIODS, '','') FN
                                     JOIN #PERIOD P
                                       ON PERIOD = FN.TOKEN
                              ORDER  BY PERIOD_SID DESC) CS
          WHERE  PD.PROJECTION_MASTER_SID =', @PROJECTION,'
                 
                 AND SNDPM.CHECK_RECORD = 1
                 AND SNDPM.METHODOLOGY = ''ROLLING ANNUAL TREND''
				 AND SNDPM.PRICE_GROUP_TYPE<>''PRICE PROTECTION''	')--GALUAT-360

  EXEC SP_EXECUTESQL @SQL,N'@PROJECTION_END_SID INT,@PROJECTION_START_SID INT',
                            @PROJECTION_START_SID= @PROJECTION_START_SID,
							@PROJECTION_END_SID =@PROJECTION_END_SID


         SET  @SQL=CONCAT('UPDATE  TC SET TC.CALC_START_PERIOD_SID = CASE WHEN LEFT(SNSPM.CALCULATION_PERIODS,1) = ''M'' THEN  TC.CALC_START_PERIOD_SID
		                                                 WHEN LEFT(SNSPM.CALCULATION_PERIODS,1) = ''Q'' THEN (TC.CALC_START_PERIOD_SID - (TC.CALC_START_PERIOD_SID%3))+1
														 WHEN LEFT(SNSPM.CALCULATION_PERIODS,1) = ''S'' THEN (TC.CALC_START_PERIOD_SID - (TC.CALC_START_PERIOD_SID%6))+1
														 ELSE (TC.CALC_START_PERIOD_SID - (TC.CALC_START_PERIOD_SID%12))+1 
														 END
														 ,
						 TC.CALC_END_PERIOD_SID =   CASE WHEN LEFT(SNSPM.CALCULATION_PERIODS,1) = ''M'' THEN  TC.CALC_START_PERIOD_SID
		                                                 WHEN LEFT(SNSPM.CALCULATION_PERIODS,1) = ''Q'' THEN (TC.CALC_START_PERIOD_SID - (TC.CALC_START_PERIOD_SID%3))+3
														 WHEN LEFT(SNSPM.CALCULATION_PERIODS,1) = ''S'' THEN (TC.CALC_START_PERIOD_SID - (TC.CALC_START_PERIOD_SID%6))+6
														 ELSE (TC.CALC_START_PERIOD_SID - (TC.CALC_START_PERIOD_SID%12))+12 
														 END
						 FROM 
		                     #TEMP_CCP TC JOIN ',@S_MASTER_TABLE,' SNSPM ON TC.PROJECTION_DETAILS_SID = SNSPM.PROJECTION_DETAILS_SID')


			EXEC sp_executesql @SQL
		                      

------------------------------------------------------------------------------------ BASELENE--------------------------------------------------------------------------------------------
          DECLARE @PERIOD_COUNT NUMERIC(22, 6)

          SET @PERIOD_COUNT = CASE @FREQUENCY
                                WHEN 'Q' THEN 3
                                WHEN 'S' THEN 6
                                WHEN 'A' THEN 12
                                ELSE 1
                              END

          IF OBJECT_ID('TEMPDB.DBO.#TEMP_AGGREGATED_BASELINE', 'U') IS NOT NULL
            DROP TABLE #TEMP_AGGREGATED_BASELINE;
			CREATE TABLE #TEMP_AGGREGATED_BASELINE
			(
			PROJECTION_DETAILS_SID			INT,
            RS_MODEL_SID				INT,
            CALCULATION_BASED			VARCHAR(50),
			PERIOD_SID					INT,
			TOKEN						VARCHAR(50),
			ACTUAL_BASELINE             NUMERIC(22,6)
			)

        SET @SQL=CONCAT(  'INSERT INTO #TEMP_AGGREGATED_BASELINE
		  (
		  PROJECTION_DETAILS_SID,
		  RS_MODEL_SID			,
		  CALCULATION_BASED		,
		  PERIOD_SID			,	
		  TOKEN					,
		  ACTUAL_BASELINE       
		  )
          SELECT PROJECTION_DETAILS_SID,
                 RS_MODEL_SID,
                 CALCULATION_BASED,
                 MIN(PERIOD_SID) AS PERIOD_SID,
                 TOKEN,
                 CASE
                   WHEN CALCULATION_BASED = ''AMOUNT'' THEN SUM(ACTUAL_BASELINE)
                   WHEN CALCULATION_BASED = ''RPU'' THEN SUM(ACTUAL_BASELINE) / NULLIF(SUM(UNITS), 0)
                   ELSE ( CASE
                            WHEN @FREQUENCY = ''M'' THEN CONVERT(NUMERIC(22, 6), SUM(ACTUAL_BASELINE))
                            WHEN @FREQUENCY = ''Q'' THEN CONVERT(NUMERIC(22, 6), SUM(ACTUAL_BASELINE))
                            WHEN @FREQUENCY = ''S'' THEN CONVERT(NUMERIC(22, 6), SUM(ACTUAL_BASELINE))
                            ELSE CONVERT(NUMERIC(22, 6), SUM(ACTUAL_BASELINE))
                          END / NULLIF(SUM(SALES), 0) ) * 100.0
                 END             AS ACTUAL_BASELINE
        
          FROM   (SELECT T_CCP.PROJECTION_DETAILS_SID,
                         T_CCP.RS_MODEL_SID,
                         T_CCP.CALCULATION_BASED,
                         T_CCP.CALCULATION_PERIODS,
                         CASE
                           WHEN @FREQUENCY = ''M'' THEN NUMBER_OF_SELECTED_PERIOD
                           WHEN @FREQUENCY = ''Q'' THEN NUMBER_OF_SELECTED_PERIOD / 3
                           WHEN @FREQUENCY = ''S'' THEN NUMBER_OF_SELECTED_PERIOD / 6
                           ELSE NUMBER_OF_SELECTED_PERIOD / 12
                         END                                                                                                                                                                        AS NUMBER_OF_SELECTED_PERIOD,
                         T_CCP.CALC_START_PERIOD_SID,
                         T_CCP.CALC_END_PERIOD_SID,
                         SNAD.PERIOD_SID,
                         CA.PERIOD_MONTH                                                                                                                                                            AS MONTH,
                         CA.PERIOD_QUARTER                                                                                                                                                          AS QUARTER,
                         CA.PERIOD_SEMI                                                                                                                                                             AS SEMI_ANNUAL,
                         CA.PERIOD_YEAR                                                                                                                                                             AS YEAR,
                         CA.TOKEN,
                         SNAD.ACTUAL_SALES                                                                                                                                                          AS ACTUAL_BASELINE,
                         SNAS.ACTUAL_SALES                                                                                                                                                          AS SALES,
                         SNAS.ACTUAL_UNITS                                                                                                                                                          AS UNITS,
                        
                         DENSE_RANK()
                           OVER (
                             PARTITION BY T_CCP.PROJECTION_DETAILS_SID, T_CCP.RS_MODEL_SID
                             ORDER BY CASE WHEN @FREQUENCY = ''M'' THEN CA.PERIOD_MONTH WHEN @FREQUENCY = ''Q'' THEN CA.PERIOD_QUARTER WHEN @FREQUENCY = ''S'' THEN CA.PERIOD_SEMI ELSE PERIOD_YEAR END ) AS RN
                  FROM   #TEMP_CCP T_CCP
                         JOIN ',@ACTUAL_TABLE,' SNAD
                           ON T_CCP.PROJECTION_DETAILS_SID = SNAD.PROJECTION_DETAILS_SID
                              AND T_CCP.RS_MODEL_SID = SNAD.RS_MODEL_SID
                              
                         JOIN ',@S_ACTUAL_TABLE,' SNAS
                           ON T_CCP.PROJECTION_DETAILS_SID = SNAS.PROJECTION_DETAILS_SID
                              AND SNAS.PERIOD_SID = SNAD.PERIOD_SID
                              
                         CROSS APPLY (SELECT PERIOD_SID,
                                             P.PERIOD_MONTH,
                                             P.PERIOD_QUARTER,
                                             P.PERIOD_SEMI,
                                             P.PERIOD_YEAR,
                                             NUMBER_OF_SELECTED_PERIOD = COUNT(FN.TOKEN)
                                                                           OVER (),
                                             FN.TOKEN
                                      FROM   UDF_SPLITSTRING(T_CCP.CALCULATION_PERIODS, '','') FN
                                             JOIN #PERIOD P
                                               ON P.PERIOD = FN.TOKEN) CA
                  WHERE  SNAD.PERIOD_SID = CA.PERIOD_SID) A
          GROUP  BY PROJECTION_DETAILS_SID,
                    RS_MODEL_SID,
                    CALCULATION_BASED,
                    SUBSTRING(TOKEN, 4, 4),
                    TOKEN
          ORDER  BY PROJECTION_DETAILS_SID,
                    RS_MODEL_SID')
EXEC SP_EXECUTESQL @SQL, N'@FREQUENCY VARCHAR(2)',
					@FREQUENCY=@FREQUENCY

          -----------------------------------------------------------------------------------------------------------------------------------------------
          IF OBJECT_ID('TEMPDB.DBO.#TEMP_FACTOR', 'U') IS NOT NULL
            DROP TABLE #TEMP_FACTOR;
				CREATE TABLE #TEMP_FACTOR
								(
								PROJECTION_DETAILS_SID	  INT				,
                                 RS_MODEL_SID			  INT					,
                                 METHODOLOGY			  VARCHAR(50)						,
                                 CALCULATION_BASED		  VARCHAR(50)		,    
                                 DISCOUNT_ID              VARCHAR(50)      ,
                             
                                 GROWTH						NUMERIC(38,15),
                                
                                PERIOD_YEAR					INT,
                                PERIOD						VARCHAR(30),
                                PERIOD_SID					INT,
								FACTOR						NUMERIC(36, 15),
								RN							 int
								)

         
		SET @SQL=CONCAT(' INSERT INTO #TEMP_FACTOR
		 (
		 PROJECTION_DETAILS_SID	,
		  RS_MODEL_SID			,
		  METHODOLOGY			,
		  CALCULATION_BASED		,
		  DISCOUNT_ID           , 
		  GROWTH				,
		 PERIOD_YEAR			,	
		 PERIOD					,
		 PERIOD_SID				,
		 FACTOR					,
		 RN						
		 )
		  SELECT PD.PROJECTION_DETAILS_SID,
                 SNDP.RS_MODEL_SID,
                 T_CCP.METHODOLOGY,
                 T_CCP.CALCULATION_BASED,
                 T_CCP.DISCOUNT_ID,
               
                 SNDP.GROWTH,
                 
                 P.PERIOD_YEAR,
                 P.PERIOD,
                 P.PERIOD_SID,
                 CONVERT(NUMERIC(36, 15), NULL)       AS FACTOR,
                 ROW_NUMBER()
                   OVER (
                     PARTITION BY SNDP.PROJECTION_DETAILS_SID, SNDP.RS_MODEL_SID
                     ORDER BY PERIOD_YEAR, P.PERIOD ) RN
        
          FROM  ',@PROJECTION_TABLE,' SNDP
                 JOIN #TEMP_CCP T_CCP
                   ON SNDP.PROJECTION_DETAILS_SID = T_CCP.PROJECTION_DETAILS_SID
                      AND SNDP.RS_MODEL_SID = T_CCP.RS_MODEL_SID
                 JOIN PROJECTION_DETAILS PD
                   ON PD.PROJECTION_DETAILS_SID = SNDP.PROJECTION_DETAILS_SID
                 JOIN PROJECTION_MASTER PM
                   ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                 JOIN (SELECT MIN(PERIOD_SID) PERIOD_SID,
                              PERIOD_YEAR,
                              PERIOD
                       FROM   #PERIOD
                       GROUP  BY PERIOD,
                                 PERIOD_YEAR) P
                   ON SNDP.PERIOD_SID = P.PERIOD_SID
                      AND P.PERIOD_SID BETWEEN CALC_START_PERIOD_SID AND CALC_END_PERIOD_SID')
       
	   EXEC sp_executesql @SQL
          -------------------------------------------------------ROLLING TREND STARTS--------------------------------------------------------------------------				
          IF OBJECT_ID('TEMPDB.DBO.#TEMP_ROLLING', 'U') IS NOT NULL
            DROP TABLE #TEMP_ROLLING;

          SELECT PROJECTION_DETAILS_SID,
                 RS_MODEL_SID,
                 PERIOD_SID,
                 TOKEN,
                 ACTUAL_BASELINE,
                 GROWTH,
                 RN
          INTO   #TEMP_ROLLING
          FROM   (SELECT A.PROJECTION_DETAILS_SID,
                         A.RS_MODEL_SID,
                         A.PERIOD_SID,
                         A.TOKEN,
                         A.ACTUAL_BASELINE,
                         A.GROWTH,
                         CONVERT(INT, SUBSTRING(TOKEN, 2, 2)) AS RN
                  FROM   (SELECT TAB.PROJECTION_DETAILS_SID,
                                 TAB.RS_MODEL_SID,
                                 TAB.PERIOD_SID,
                                 TAB.TOKEN,
                                 TAB.ACTUAL_BASELINE,
                                 0 AS GROWTH
                          FROM   #TEMP_AGGREGATED_BASELINE TAB
                          UNION ALL
                          SELECT TF.PROJECTION_DETAILS_SID,
                                 TF.RS_MODEL_SID,
                                 TF.PERIOD_SID,
                                 TF.PERIOD,
                                 TAB.ACTUAL_BASELINE AS ACTUAL_BASELINE,
                                 TF.GROWTH
                          FROM   #TEMP_FACTOR TF
                                 JOIN #TEMP_AGGREGATED_BASELINE TAB
                                   ON TF.PROJECTION_DETAILS_SID = TAB.PROJECTION_DETAILS_SID
                                      AND TF.RS_MODEL_SID = TAB.RS_MODEL_SID
                                      AND SUBSTRING(TF.PERIOD, 2, 2) = SUBSTRING(TAB.TOKEN, 2, 2)) A
                  GROUP  BY PROJECTION_DETAILS_SID,
                            RS_MODEL_SID,
                            TOKEN,
                            PERIOD_SID,
                            ACTUAL_BASELINE,
                            GROWTH) B
          ORDER  BY PROJECTION_DETAILS_SID,
                    RS_MODEL_SID,
                    RN

          --------------------------------------------------ROLLING TREND(1+GROWTH) INTO TEMP RESULT STARTS--------------------------------------------------------------               
          IF OBJECT_ID('TEMPDB.DBO.#TEMP_RESULT', 'U') IS NOT NULL
            DROP TABLE #TEMP_RESULT;

          SELECT A.PROJECTION_DETAILS_SID,
                 A.RS_MODEL_SID,
                 METHODOLOGY,
                 CALC_END_PERIOD_SID,
                 CALCULATION_BASED,
                
                 GROWTH,
                
                 P.PERIOD_YEAR,
                 P.PERIOD_SEMI,
                 P.PERIOD_QUARTER,
                 P.PERIOD_MONTH,
                 P.PERIOD_SID,
                 RESULT
          --  = CASE
          --                            WHEN CALCULATION_BASED IN ( 'RATE', 'RPU' ) THEN RESULT
          --                            ELSE ( CASE
          --                                     WHEN @FREQUENCY = 'M' THEN   RESULT
          --                                     WHEN @FREQUENCY = 'Q' THEN ( RESULT  )
          --                                     WHEN @FREQUENCY = 'S' THEN ( RESULT  )
          --                                     ELSE ( RESULT)
          --END
          INTO   #TEMP_RESULT
          FROM   (SELECT TR1.*,
                         T_CCP.CALCULATION_BASED,
                         T_CCP.METHODOLOGY,
                         CONVERT(INT, T_CCP.CALC_END_PERIOD_SID)               AS CALC_END_PERIOD_SID,
                         CONVERT(NUMERIC(35, 16), ( TR1.ACTUAL_BASELINE * R )) AS RESULT
                  FROM   #TEMP_ROLLING TR1
                         INNER JOIN #TEMP_CCP T_CCP
                                 ON TR1.PROJECTION_DETAILS_SID = T_CCP.PROJECTION_DETAILS_SID
                                    AND TR1.RS_MODEL_SID = T_CCP.RS_MODEL_SID
                         CROSS APPLY (SELECT ( [DBO].UDF_MUL((SELECT ( 1 + ISNULL((TR2.GROWTH / 100.0), 0) )
                                                              FROM   #TEMP_ROLLING TR2
                                                              WHERE  TR1.PROJECTION_DETAILS_SID = TR2.PROJECTION_DETAILS_SID
                                                                     AND TR2.RS_MODEL_SID = TR1.RS_MODEL_SID
                                                                     AND TR2.RN = TR1.RN
                                                                     AND TR1.PERIOD_SID >= TR2.PERIOD_SID
                                                                     AND TR1.PERIOD_SID >= T_CCP.CALC_START_PERIOD_SID
                                                              FOR XML PATH, ROOT('ROOT'))) ) AS R) CS) A
                 INNER JOIN #PERIOD P
                         ON P.PERIOD = A.TOKEN
          WHERE  P.PERIOD_SID <= A.CALC_END_PERIOD_SID

          --SELECT * FROM #TEMP_RESULT TR ORDER BY TR.PROJECTION_DETAILS_SID, TR.RS_MODEL_SID,PERIOD_MONTH,PERIOD_YEAR
          --------------------------------------------------------FINAL_RESULTS--------------------------------------------------------------------
          IF OBJECT_ID('TEMPDB.DBO.#TEMP_FINAL_RESULTS', 'U') IS NOT NULL
            DROP TABLE #TEMP_FINAL_RESULTS;
			CREATE TABLE #TEMP_FINAL_RESULTS
								(
								PROJECTION_DETAILS_SID          INT,
								PERIOD_SID                      INT, 
								RS_MODEL_SID                     INT,
								PROJECTION_REBATE_PER_UNIT      NUMERIC(22,6),    
								PROJECTION_RATE					NUMERIC(22,6),
								PROJECTION_AMOUNT               NUMERIC(22,6)
								)

                          
     SET @SQL=CONCAT(' ;
          WITH GC
               AS (SELECT TR.*,
                          SNSP.PROJECTION_SALES,
                          SNSP.PROJECTION_UNITS
                   FROM   ',@S_PROJECTION_TABLE, ' SNSP
                          INNER JOIN #TEMP_RESULT TR
                                  ON SNSP.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID
                                     AND SNSP.PERIOD_SID = TR.PERIOD_SID
                                    ),
               HC
               AS (
                  ---PROJ_DIS_AMOUNT CALCULATON FORMULA
                  SELECT GC.*,
                         ( CASE
                             WHEN GC.CALCULATION_BASED = ''RATE'' THEN COALESCE(( GC.RESULT * NULLIF(GC.PROJECTION_SALES, 0) ) / 100.000, 0)
                             WHEN GC.CALCULATION_BASED = ''RPU'' THEN COALESCE(( GC.RESULT * GC.PROJECTION_UNITS ), 0)
                             ELSE GC.RESULT /', @PERIOD_COUNT,'
                           END ) AS PROJECTION_AMOUNT
                   FROM   GC),
               IC
               AS (
                  ---PROJ_RPU CALCULATON FORMULA
                  SELECT HC.*,
                         ( CASE
                             WHEN HC.CALCULATION_BASED = ''RATE'' THEN COALESCE(( HC.PROJECTION_AMOUNT / NULLIF(HC.PROJECTION_UNITS, 0) ), 0)
                             WHEN HC.CALCULATION_BASED = ''RPU'' THEN HC.RESULT
                             ELSE COALESCE(( HC.PROJECTION_AMOUNT / ( NULLIF(HC.PROJECTION_UNITS, 0) ) ), 0)
                           END ) AS PROJECTION_REBATE_PER_UNIT
                   FROM   HC)
          INSERT INTO #TEMP_FINAL_RESULTS
		  (
		  PROJECTION_DETAILS_SID,
		  PERIOD_SID   ,             
		  RS_MODEL_SID  ,            
		  PROJECTION_REBATE_PER_UNIT,
		  PROJECTION_AMOUNT   ,
		  PROJECTION_RATE
		  )
		  
		  SELECT 
		  PROJECTION_DETAILS_SID  ,  
		  PERIOD_SID   ,             
		  RS_MODEL_SID  ,            
		  PROJECTION_REBATE_PER_UNIT,
		  PROJECTION_AMOUNT   ,
                 ( CASE
                     WHEN IC.CALCULATION_BASED = ''RATE'' THEN IC.RESULT
                     WHEN IC.CALCULATION_BASED = ''RPU'' THEN COALESCE(( PROJECTION_AMOUNT / NULLIF(IC.PROJECTION_SALES, 0) ), 0) * 100.000 --CHANGE
                     ELSE COALESCE(( IC.PROJECTION_AMOUNT / NULLIF(IC.PROJECTION_SALES, 0) ), 0) * 100.000
                   END ) AS PROJECTION_RATE
          
          FROM   IC')

		  EXEC sp_executesql @SQL
          --SELECT * FROM #TEMP_FINAL_RESULTS ORDER BY PROJECTION_DETAILS_SID , RS_MODEL_SID, PERIOD_MONTH
          -------------------------------------- MAIN TABLE UPDATION ------------------------------------------------

				   SET @SQL= 'UPDATE SNDP
                                  SET    SNDP.PROJECTION_SALES = TFR.PROJECTION_AMOUNT,
                                            SNDP.PROJECTION_RPU = TFR.PROJECTION_REBATE_PER_UNIT,
                                            SNDP.PROJECTION_RATE = TFR.PROJECTION_RATE
                                  FROM   '+@PROJECTION_TABLE+' SNDP
                                            INNER JOIN #TEMP_FINAL_RESULTS TFR
                                                          ON SNDP.PROJECTION_DETAILS_SID = TFR.PROJECTION_DETAILS_SID
                                                                AND SNDP.RS_MODEL_SID = TFR.RS_MODEL_SID
                                                                AND SNDP.PERIOD_SID = TFR.PERIOD_SID'
      EXEC sp_executesql @SQL

                                  SET @SQL= ' UPDATE SNDP
                                  SET    SNDP.PROJECTION_SALES = 0,
                                            SNDP.PROJECTION_RPU = 0,
                                            SNDP.PROJECTION_RATE = 0
                                  FROM   '+@PROJECTION_TABLE+' SNDP
                                            JOIN '+@MASTER_TABLE+' SNDPM
                                                ON SNDP.PROJECTION_DETAILS_SID = SNDPM.PROJECTION_DETAILS_SID
                                                       AND SNDP.RS_MODEL_SID = SNDPM.RS_MODEL_SID
                                                       AND SNDPM.CHECK_RECORD = 1
                                  WHERE  SNDP.PROJECTION_SALES IS NULL
                                            AND SNDP.PROJECTION_RPU IS NULL
                                            AND SNDP.PROJECTION_RATE IS NULL'
 
    EXEC sp_executesql @SQL


				 
				 
		SET @SQL='  UPDATE SNDP
          SET    SNDP.PROJECTION_SALES = 0,
                 SNDP.PROJECTION_RPU   = 0,
                 SNDP.PROJECTION_RATE  = 0
          FROM   '+@PROJECTION_TABLE+'  SNDP
		  JOIN   #TEMP_CCP TC ON SNDP.PERIOD_SID NOT BETWEEN TC.CALC_START_PERIOD_SID AND TC.CALC_END_PERIOD_SID'
	    EXEC sp_executesql @SQL
		  
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