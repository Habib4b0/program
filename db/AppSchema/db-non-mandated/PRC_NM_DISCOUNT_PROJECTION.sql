IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NM_DISCOUNT_PROJECTION'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NM_DISCOUNT_PROJECTION]
  END

GO
CREATE   PROCEDURE [dbo].[PRC_NM_DISCOUNT_PROJECTION] (@PROJECTION INT,
                                                       @FREQUENCY  VARCHAR(100),
                                                       @USER_ID    INT,
                                                       @SESSION_ID VARCHAR(50),
													   @FORECAST_START_PERIOD_SID INT,
													   @FORECAST_END_PERIOD_SID INT,
													   @CALCULATION_BASED VARCHAR(100),
													   @DEDUCTION_INCLUSION BIT =null)
AS
  BEGIN

     
      SET NOCOUNT ON
	  
   DECLARE        @PROJECTION_START_SID INT,
                  @PROJECTION_END_SID   INT, 
                  @METHODOLOGY        VARCHAR(50)

          DECLARE @MASTER_TABLE       VARCHAR(200) = Concat('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ACTUAL_TABLE       VARCHAR(200) = Concat('ST_NM_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @PROJECTION_TABLE   VARCHAR(200) = Concat('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @S_PROJECTION_TABLE VARCHAR(200)=  Concat('ST_NM_SALES_PROJECTION_', @user_id, '_', @session_id, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @S_ACTUAL_TABLE     VARCHAR(200)=  Concat('ST_NM_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @S_MASTER_TABLE     VARCHAR(200)=  Concat('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @GROWTH_TABLE       VARCHAR(200)=  Concat('ST_DISC_GROWTH_FACTOR_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @PRODUCT_TABLE      VARCHAR(200)=  Concat('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

          SET @FREQUENCY = LEFT(@FREQUENCY, 1)

          DECLARE @X_FACTORY  VARCHAR(50) = '% OF EX-FACTORY SALES',
                  @INVENTORY  VARCHAR(50) = '% OF INVENTORY WITHDRAWAL',
                  @DEMAND     VARCHAR(50) = '% OF DEMAND',
                  @ADJ_DEMAND VARCHAR(50) = '% OF ADJUSTED DEMAND',
				 @SEASONAL_TREND     VARCHAR(50) = '% OF EX-FACTORY - SEASONAL TREND'
          -----PROJECTION_STARTS AND END DATES-------------------------------------
          SELECT @PROJECTION_START_SID = PROJECTION_START_SID,
                 @PROJECTION_END_SID = PROJECTION_END_SID
          FROM   [DBO].[Udf_projection_dates](@PROJECTION, @FREQUENCY)

          ----------------------------------------------------
DECLARE @ACTUAL_PERIOD INT
		SET @ACTUAL_PERIOD=(SELECT PERIOD_SID
                            FROM   PERIOD
                            WHERE  PERIOD_DATE = 
 CONVERT(DATETIME,DATEADD(MM,-1,DATEADD(DD,1,EOMONTH(GETDATE(), 0)))))
          IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
            DROP TABLE #PERIOD;

          CREATE TABLE #PERIOD
            (
               PERIOD_SID     INT,
               PERIOD_DATE    DATE,
               PERIOD_MONTH   INT,
               PERIOD_QUARTER INT,
               PERIOD_SEMI    INT,
               PERIOD_YEAR    INT,
               PERIOD         VARCHAR(10),
               ROLL_PERIOD    INT
               PRIMARY KEY (PERIOD_SID)
            )

          INSERT INTO #PERIOD
                      (PERIOD_SID,
                       PERIOD_DATE,
                       PERIOD_MONTH,
                       PERIOD_QUARTER,
                       PERIOD_SEMI,
                       PERIOD_YEAR,
                       PERIOD,
                       ROLL_PERIOD)
          SELECT PERIOD_SID,
                 PERIOD_DATE,
                 MONTH AS PERIOD_MONTH,
                 QUARTER AS PERIOD_QUARTER,
                 SEMI_ANNUAL AS PERIOD_SEMI,
                 YEAR AS PERIOD_YEAR,
                  CASE
                            WHEN @FREQUENCY = 'M' THEN Concat ('M', MONTH, ' ', YEAR)
                            WHEN @FREQUENCY = 'Q' THEN Concat ('Q', QUARTER, ' ', YEAR)
                            WHEN @FREQUENCY = 'S' THEN Concat ('S', SEMI_ANNUAL, ' ', YEAR)
                            ELSE Cast(YEAR AS CHAR(4))
                          END AS PERIOD,
                  CASE
                                 WHEN @FREQUENCY = 'M' THEN MONTH
                                 WHEN @FREQUENCY = 'Q' THEN QUARTER
                                 WHEN @FREQUENCY = 'S' THEN SEMI_ANNUAL
                                 ELSE 1
                               END AS ROLL_PERIOD
          FROM   PERIOD

                   DECLARE @SQL NVARCHAR(MAX)

                SET @SQL= CONCAT('
            IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''',@GROWTH_TABLE,'''
                      AND TABLE_SCHEMA = ''DBO'')
                 BEGIN             
           CREATE TABLE ',@GROWTH_TABLE,'
                   (
                              CCP_DETAILS_SID     INT,
                              RS_MODEL_SID        INT,
                              RS_CONTRACT_SID     INT,
                              PERIOD_SID          INT,
                              GROWTH              NUMERIC(22,15)
                   )
           END'

)

EXEC sp_executesql @SQL


          ----------------------------CCP_DETAILS-----------------------
          IF Object_id('TEMPDB.DBO.#SALES_FREQUENCY_DATE', 'U') IS NOT NULL
            DROP TABLE #SALES_FREQUENCY_DATE;

          CREATE TABLE #SALES_FREQUENCY_DATE
            (
               --CONTRACT_MASTER_SID         INT,
               --COMPANY_MASTER_SID          INT,
              ITEM_MASTER_SID             INT,
              -- DISCOUNT_ID                 VARCHAR(50),
               CCP_DETAILS_SID             INT,
               --PROJECTION_MASTER_SID       INT,
               --BUSINESS_UNIT               VARCHAR(50),
               --GL_COMPANY_MASTER_SID       INT,
               RS_MODEL_SID                INT,
                        RS_CONTRACT_SID             INT,
              METHODOLOGY                 VARCHAR(50),
               FREQUENCY                   CHAR(10),
               CALCULATION_BASED           VARCHAR(20),
               CALCULATION_PERIODS         VARCHAR(MAX),
               PROJ_START_PERIOD_SID       INT,
               PROJ_END_PERIOD_SID         INT,
               --ALLOCATION_BASIS            VARCHAR(70),
               CALC_START_PERIOD_SID       INT,
               CALC_END_PERIOD_SID         INT,
               EFFECT_UPD_START_PERIOD_SID INT,
               EFFECT_UPD_END_PERIOD_SID   INT
            )

       

          SET @SQL=CONCAT( ' INSERT INTO #SALES_FREQUENCY_DATE
                                  (
                                  --CONTRACT_MASTER_SID           ,      
                                  --COMPANY_MASTER_SID            ,
                                  ITEM_MASTER_SID               ,
                                  --DISCOUNT_ID                              ,
                                  CCP_DETAILS_SID               ,
                                  --PROJECTION_MASTER_SID         ,
                                  RS_MODEL_SID                  ,
                                  RS_CONTRACT_SID               ,

                                  METHODOLOGY                   ,
                                  FREQUENCY                     ,
                                  CALCULATION_BASED             ,
                                  CALCULATION_PERIODS           ,
                                  --ALLOCATION_BASIS              ,
                                  CALC_START_PERIOD_SID         ,
                    CALC_END_PERIOD_SID           ,
                                  EFFECT_UPD_START_PERIOD_SID   ,
                                  EFFECT_UPD_END_PERIOD_SID
                                  )

                 
                
                   SELECT  ITEM_MASTER_SID,
                                                         CCP.CCP_DETAILS_SID,
                                  
                                                         SNDPM.RS_MODEL_SID,
														 SNDPM.RS_CONTRACT_SID,
                                                         SNDPM.METHODOLOGY,
                                  
                                  FREQUENCY=CASE WHEN LEFT(SNSPM.CALCULATION_PERIODS,1) IN (''Q'',''M'',''S'') THEN LEFT(SNSPM.CALCULATION_PERIODS,1) ELSE ''A'' END,
                                  ''',@CALCULATION_BASED,''',

                                  SNDPM.CALCULATION_PERIODS,
                                CALC_START_PERIOD_SID = CASE
                                                                              WHEN SNDPM.METHODOLOGY IN ( @X_FACTORY, @INVENTORY, @DEMAND,@SEASONAL_TREND ) THEN IIF(',@FORECAST_START_PERIOD_SID,' > SNDPM.EFFECTIVE_START_PERIOD_SID, ',@FORECAST_START_PERIOD_SID,', SNDPM.EFFECTIVE_START_PERIOD_SID)
                                                                              ELSE SNDPM.FREQ_CAL_START_PERIOD_SID
                                                                           END,
                                  CALC_END_PERIOD_SID =CASE
                                                                              WHEN SNDPM.METHODOLOGY IN ( @X_FACTORY, @INVENTORY, @DEMAND,@SEASONAL_TREND ) THEN IIF(', @PROJECTION_END_SID, ' > SNDPM.EFFECTIVE_END_PERIOD_SID, SNDPM.EFFECTIVE_END_PERIOD_SID, ', @PROJECTION_END_SID, ')
                                                         
                                                         
                                                         ELSE
                                                          SNDPM.FREQ_CAL_END_PERIOD_SID END,
                                 
                                
                                                          EFFECTIVE_START_PERIOD_SID=IIF(SNDPM.FREQ_CAL_START_PERIOD_SID>SNDPM.EFFECTIVE_START_PERIOD_SID,SNDPM.EFFECTIVE_START_PERIOD_SID,SNDPM.FREQ_CAL_START_PERIOD_SID),
                                                EFFECTIVE_END_PERIOD_SID=IIF(SNDPM.FREQ_CAL_END_PERIOD_SID>SNDPM.EFFECTIVE_END_PERIOD_SID,SNDPM.FREQ_CAL_END_PERIOD_SID,SNDPM.EFFECTIVE_END_PERIOD_SID)  
          
                       FROM   CCP_DETAILS CCP
                                  
                                  JOIN ',@MASTER_TABLE,' SNDPM
                                     ON CCP.CCP_DETAILS_SID = SNDPM.CCP_DETAILS_SID
									   AND (EFFECTIVE_START_PERIOD_SID IS NOT NULL OR EFFECTIVE_END_PERIOD_SID IS NOT NULL)
									  and  FILTER_CCP=1  ',case when @DEDUCTION_INCLUSION is not null then concat('AND DEDUCTION_INCLUSION =',@DEDUCTION_INCLUSION) END,'       
                                  OUTER APPLY (SELECT TOP 1 P.PERIOD_SID + 1 AS MAX_BASELINE_PERIOD
                                                         FROM   UDF_SPLITSTRING(SNDPM.CALCULATION_PERIODS, '','') FN
                                                                     JOIN #PERIOD P
                                                                        ON PERIOD = FN.TOKEN
                                                         ORDER  BY PERIOD_SID DESC) CS
                                  INNER JOIN ',@S_MASTER_TABLE,' SNSPM
                                  ON SNSPM.CCP_DETAILS_SID=SNDPM.CCP_DETAILS_SID
                       WHERE     SNDPM.CHECK_RECORD = 1
                                  AND SNDPM.PRICE_GROUP_TYPE<>''PRICE PROTECTION''  
                                  ')
          EXEC Sp_executesql
            @SQL,
            N'@X_FACTORY NVARCHAR(50),@INVENTORY NVARCHAR(50),@DEMAND NVARCHAR(50),@SEASONAL_TREND NVARCHAR(50) ,@PROJECTION_START_SID INT,@PROJECTION_END_SID INT',
            @PROJECTION_END_SID = @PROJECTION_END_SID,
            @X_FACTORY=@X_FACTORY,
            @INVENTORY=@INVENTORY,
            @DEMAND=@DEMAND,
			@SEASONAL_TREND=@SEASONAL_TREND,
            @PROJECTION_START_SID=@PROJECTION_START_SID

                    -- DECLARE @NUMBER_OF_SELECTED_PERIODS INT


          ---------------------------------------------------------------------------------- BASELINE
          IF EXISTS (SELECT 1
                     FROM   #SALES_FREQUENCY_DATE CCP
                     WHERE  METHODOLOGY IN ( 'SINGLE PERIOD', 'AVERAGE', 'ROLLING ANNUAL TREND' ))
            BEGIN


                     IF Object_id('TEMPDB.DBO.#ACTUAL_BASELINE', 'U') IS NOT NULL
                  DROP TABLE #ACTUAL_BASELINE;

                CREATE TABLE #ACTUAL_BASELINE
                  (
                     ACTUAL_BASELINE           NUMERIC(22, 6),
                     CCP_DETAILS_SID           INT,
                     RS_MODEL_SID              INT,
					 RS_CONTRACT_SID              INT,
                     ROLL_PERIOD               INT,
                     NUMBER_OF_SELECTED_PERIOD  INT
                    
                  )


                DECLARE @PERIOD_COUNT NUMERIC(22, 6)

                SET @PERIOD_COUNT = CASE @FREQUENCY
                                      WHEN 'Q' THEN 3
                                      WHEN 'S' THEN 6
                                      WHEN 'A' THEN 12
                                      ELSE 1
                                    END

                DECLARE @VAR1 VARCHAR(MAX)
                        

                SET @methodology=(SELECT TOP 1 METHODOLOGY
                                  FROM   #SALES_FREQUENCY_DATE ORDER BY METHODOLOGY)
               

                  ---      SET @VAR1= (SELECT CASE
                     ---                WHEN @METHODOLOGY = 'ROLLING ANNUAL TREND' THEN ' NUMBER_OF_SELECTED_PERIOD = 1 '

                      --                                                               + ',FN.TOKEN'
                      ---               ELSE ' NUMBER_OF_SELECTED_PERIOD = COUNT(1) OVER () / '
                     ---                     + CONVERT(VARCHAR(MAX), @PERIOD_COUNT)

                      ---             END)



      IF EXISTS (SELECT 1
                     FROM   #SALES_FREQUENCY_DATE CCP
                     WHERE  METHODOLOGY IN ( 'SINGLE PERIOD', 'ROLLING ANNUAL TREND' ))
            BEGIN	   
							   
   SET @SQL=CONCAT( 
   'INSERT INTO #ACTUAL_BASELINE
         (
         
         CCP_DETAILS_SID,
         RS_MODEL_SID,
		 RS_CONTRACT_SID,
         ACTUAL_BASELINE,
         ROLL_PERIOD,
         NUMBER_OF_SELECTED_PERIOD
       )
    
       SELECT T_CCP.CCP_DETAILS_SID,
              T_CCP.RS_MODEL_SID,
			  T_CCP.RS_CONTRACT_SID,
              (CASE
                WHEN CALCULATION_BASED = ''RATE'' THEN Sum(SNAD.ACTUAL_SALES) / NULLIF(Sum(SNA.ACTUAL_SALES), 0)
                WHEN CALCULATION_BASED = ''RPU'' THEN Sum(SNAD.ACTUAL_SALES) / NULLIF(Sum(SNA.ACTUAL_UNITS), 0)
                WHEN CALCULATION_BASED = ''AMOUNT'' THEN Sum(SNAD.ACTUAL_SALES)
              END)/NULLIF(NUMBER_OF_SELECTED_PERIOD,0) AS ACTUAL_BASELINE,
              CASE
                WHEN T_CCP.METHODOLOGY = ''ROLLING ANNUAL TREND'' THEN CASE WHEN '''+@FREQUENCY+''' <> ''A''  THEN CS.ROLL_PERIOD ELSE 1 END ELSE NULL
              END,
                       NUMBER_OF_SELECTED_PERIOD
       FROM  #SALES_FREQUENCY_DATE  T_CCP
              CROSS APPLY (SELECT PERIOD_SID,
                                  ROLL_PERIOD,
             NUMBER_OF_SELECTED_PERIOD= CASE
                                     WHEN  T_CCP.METHODOLOGY= ''ROLLING ANNUAL TREND'' THEN   1 
                                                                                  
                                     ELSE    COUNT(1) OVER () / 
                                          NULLIF(',@PERIOD_COUNT,',0)
                                   END 
                           FROM   Udf_splitstring(T_CCP.CALCULATION_PERIODS, '','') FN
                                  JOIN #PERIOD P
                                    ON P.PERIOD = FN.TOKEN)CS
              INNER JOIN ',@ACTUAL_TABLE,' SNAD
                      ON CS.PERIOD_SID = SNAD.PERIOD_SID
                         AND T_CCP.CCP_DETAILS_SID = SNAD.CCP_DETAILS_SID
                         AND T_CCP.RS_CONTRACT_SID = SNAD.RS_CONTRACT_SID
              INNER JOIN ',@S_ACTUAL_TABLE,' SNA
                      ON CS.PERIOD_SID = SNA.PERIOD_SID
                         AND T_CCP.CCP_DETAILS_SID = SNA.CCP_DETAILS_SID
       GROUP  BY T_CCP.CCP_DETAILS_SID,
                 T_CCP.RS_MODEL_SID,
				  T_CCP.RS_CONTRACT_SID,
                 CALCULATION_BASED,
                           NUMBER_OF_SELECTED_PERIOD,
                 CASE
                   WHEN T_CCP.METHODOLOGY = ''ROLLING ANNUAL TREND'' THEN CASE WHEN '''+@FREQUENCY+''' <> ''A''  THEN CS.ROLL_PERIOD ELSE 1 END ELSE NULL
                    END ')
 end  
                 
      IF EXISTS (SELECT 1
                     FROM   #SALES_FREQUENCY_DATE CCP
                     WHERE  METHODOLOGY IN ( 'AVERAGE' ))

            BEGIN									
							   
   SET @SQL=CONCAT( 
   'INSERT INTO #ACTUAL_BASELINE
         (
         
         CCP_DETAILS_SID,
         RS_MODEL_SID,
		 RS_CONTRACT_SID,
         ACTUAL_BASELINE,
         ROLL_PERIOD,
         NUMBER_OF_SELECTED_PERIOD
       )

	   select CCP_DETAILS_SID,RS_MODEL_SID,RS_CONTRACT_SID,sum(ACTUAL_BASELINE)/nullif(NUMBER_OF_SELECTED_PERIOD,0),ROLL_PERIOD,NUMBER_OF_SELECTED_PERIOD from 
	   (    
       SELECT T_CCP.CCP_DETAILS_SID,
              T_CCP.RS_MODEL_SID,
			  T_CCP.RS_CONTRACT_SID,
              (CASE
                WHEN CALCULATION_BASED = ''RATE'' THEN Sum(SNAD.ACTUAL_SALES) / NULLIF(Sum(SNA.ACTUAL_SALES), 0)
                WHEN CALCULATION_BASED = ''RPU'' THEN Sum(SNAD.ACTUAL_SALES) / NULLIF(Sum(SNA.ACTUAL_UNITS), 0)
                WHEN CALCULATION_BASED = ''AMOUNT'' THEN Sum(SNAD.ACTUAL_SALES)
              END) AS ACTUAL_BASELINE,
              CASE
                WHEN T_CCP.METHODOLOGY = ''ROLLING ANNUAL TREND'' THEN CASE WHEN '''+@FREQUENCY+''' <> ''A''  THEN CS.ROLL_PERIOD ELSE 1 END ELSE NULL
              END as ROLL_PERIOD,
                       NUMBER_OF_SELECTED_PERIOD
					   ,cs.PERIOD
       FROM  #SALES_FREQUENCY_DATE  T_CCP
              CROSS APPLY (SELECT PERIOD_SID,
                                  ROLL_PERIOD,
								  period,
                                   NUMBER_OF_SELECTED_PERIOD= CASE
                                     WHEN  T_CCP.METHODOLOGY= ''ROLLING ANNUAL TREND'' THEN   1 
                                                                                  
                                     ELSE    COUNT(1) OVER () / 
                                          NULLIF(',@PERIOD_COUNT,',0)
                                   END 
                           FROM   Udf_splitstring(T_CCP.CALCULATION_PERIODS, '','') FN
                                  JOIN #PERIOD P
                                    ON P.PERIOD = FN.TOKEN)CS
              INNER JOIN ',@ACTUAL_TABLE,' SNAD
                      ON CS.PERIOD_SID = SNAD.PERIOD_SID
                         AND T_CCP.CCP_DETAILS_SID = SNAD.CCP_DETAILS_SID
                         AND T_CCP.RS_CONTRACT_SID = SNAD.RS_CONTRACT_SID
              INNER JOIN ',@S_ACTUAL_TABLE,' SNA
                      ON CS.PERIOD_SID = SNA.PERIOD_SID
                         AND T_CCP.CCP_DETAILS_SID = SNA.CCP_DETAILS_SID
       GROUP  BY T_CCP.CCP_DETAILS_SID,
                 T_CCP.RS_MODEL_SID,
				 T_CCP.RS_CONTRACT_SID,
                 CALCULATION_BASED,
                           NUMBER_OF_SELECTED_PERIOD,
                 CASE
                   WHEN T_CCP.METHODOLOGY = ''ROLLING ANNUAL TREND'' THEN CASE WHEN '''+@FREQUENCY+''' <> ''A''  THEN CS.ROLL_PERIOD ELSE 1 END ELSE NULL
                    END 
					 ,cs.PERIOD) a 
			   group by CCP_DETAILS_SID,RS_MODEL_SID,RS_CONTRACT_SID,ROLL_PERIOD,NUMBER_OF_SELECTED_PERIOD')
		
end 




EXEC sp_executesql @SQL


                             --------------------------CALCULATION_BASED(1+GROWTH)  STARTS HERE-----------------------------------------------------------------------
                --IF Object_id('TEMPDB.DBO.#TEMP_FACTOR', 'U') IS NOT NULL
                --  DROP TABLE #TEMP_FACTOR;

                --CREATE TABLE #TEMP_FACTOR
                --  (
                --     CCP_DETAILS_SID   INT,
                --     RS_MODEL_SID      INT,
                                  
                --     METHODOLOGY       VARCHAR(50),
                --     CALCULATION_BASED VARCHAR(50),
                     
                --     GROWTH            NUMERIC(38, 15),
                --     PERIOD_YEAR       INT,
                --     PERIOD            VARCHAR(30),
                --     PERIOD_SID        INT,
                --     FACTOR            NUMERIC(36, 15),
                --     ROLL_PERIOD       INT
                --  )

       --         SET @SQL= Concat('
                                                -- INSERT INTO #TEMP_FACTOR
                                                -- (
                                                --  CCP_DETAILS_SID  , 
                                                --  RS_MODEL_SID           ,
                                                
                                                --  PERIOD_SID             ,
                                                --  CALCULATION_BASED      ,
                                                --  PERIOD            ,
                                                --  FACTOR            ,
                                                --  ROLL_PERIOD                          
                                                -- )
                                                
                                                -- SELECT A.CCP_DETAILS_SID,
                                                --        A.RS_MODEL_SID,
                                                              
                                                --            P.PERIOD_SID,
                                                --            CALCULATION_BASED,
                                                --            P.PERIOD,
                                                              
                                                --        CASE WHEN CALCULATION_BASED=''RATE''         THEN (A.ACTUAL_BASELINE*ISNULL(B.GROWTH,1))
                                                --                 WHEN CALCULATION_BASED=''RPU''                     THEN (A.ACTUAL_BASELINE*ISNULL(B.GROWTH,1))
                                                --                   WHEN CALCULATION_BASED=''AMOUNT''             THEN (A.ACTUAL_BASELINE*ISNULL(B.GROWTH,1))/', @PERIOD_COUNT, '
                                                --                   END ,
                                                --                   P.ROLL_PERIOD
                                                          
                                                --  FROM #ACTUAL_BASELINE A
                                                --    JOIN  #SALES_FREQUENCY_DATE M ON A.CCP_DETAILS_SID=M.CCP_DETAILS_SID
                                                --                 AND A.RS_MODEL_SID=M.RS_MODEL_SID

                                                -- CROSS JOIN #PERIOD P 
                                                -- LEFT JOIN 
                                                -- (
                                                -- SELECT G.CCP_DETAILS_SID,G.RS_MODEL_SID,P.PERIOD_SID,PERIOD,GROWTH 
                                                -- FROM ', @GROWTH_TABLE, ' G 
                                                -- JOIN #PERIOD P
                                                -- ON P.PERIOD_SID=G.PERIOD_SID )B
                                                -- ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID
                                                --    AND A.RS_MODEL_SID=B.RS_MODEL_SID 
                                                --     AND B.PERIOD=P.PERIOD
                                                       
                                                --WHERE P.PERIOD_SID BETWEEN M.CALC_START_PERIOD_SID AND M.CALC_END_PERIOD_SID
                                                -- ', CASE
       --                                WHEN @METHODOLOGY = 'ROLLING ANNUAL TREND' THEN 'AND A.ROLL_PERIOD=P.ROLL_PERIOD'
       --                                ELSE ' '
       --                              END)

       --         EXEC(@SQL)

                -----------------------------------------------FINAL_RESULTS-------------------------------------------------
                --IF Object_id('TEMPDB.DBO.#TEMP_FINAL_RESULTS', 'U') IS NOT NULL
                --  DROP TABLE #TEMP_FINAL_RESULTS;

                --CREATE TABLE #TEMP_FINAL_RESULTS
                --  (
                --     CCP_DETAILS_SID            INT,
                --     PERIOD_SID                 INT,
                --     RS_MODEL_SID               INT,
                                  
                --     PROJECTION_REBATE_PER_UNIT NUMERIC(22, 6),
                --     PROJECTION_RATE            NUMERIC(22, 6),
                --     PROJECTION_AMOUNT          NUMERIC(22, 6)
                --  )

                           ------------------------------------------------------------
--                           set @sql  = ''
--       set @SQL = concat(@SQL ,'
--                                          INSERT #TEMP_FINAL_RESULTS
--                                                          (
--                                                          CCP_DETAILS_SID ,   
--                                                          PERIOD_SID ,   
--                                                          RS_MODEL_SID,     
                                                              
--                                                          PROJECTION_REBATE_PER_UNIT,
--                                                          PROJECTION_RATE   ,                    
--                                                          PROJECTION_AMOUNT         
--                                                          )
--              SELECT 
--SNSP.CCP_DETAILS_SID,
--SNSP.PERIOD_SID,
--RS_MODEL_SID,
--PROJECTION_AMOUNT=CASE
--     WHEN CALCULATION_BASED = ''RATE'' THEN COALESCE(( FACTOR * NULLIF(PROJECTION_SALES, 0) ), 0)
--     WHEN CALCULATION_BASED = ''RPU'' THEN COALESCE(( FACTOR * PROJECTION_UNITS ), 0)
--     ELSE  FACTOR
--END ,
--PROJECTION_REBATE_PER_UNIT=
--CASE
--WHEN CALCULATION_BASED = ''RATE'' THEN COALESCE(( CASE
--     WHEN CALCULATION_BASED = ''RATE'' THEN COALESCE(( FACTOR * NULLIF(PROJECTION_SALES, 0) ), 0)
--     WHEN CALCULATION_BASED = ''RPU'' THEN COALESCE(( FACTOR * PROJECTION_UNITS ), 0)
--     ELSE  FACTOR
--END / NULLIF(PROJECTION_UNITS, 0) ), 0)
--WHEN CALCULATION_BASED = ''RPU'' THEN FACTOR
--ELSE COALESCE(( CASE
--     WHEN CALCULATION_BASED = ''RATE'' THEN COALESCE(( FACTOR * NULLIF(PROJECTION_SALES, 0) ), 0)
--     WHEN CALCULATION_BASED = ''RPU'' THEN COALESCE(( FACTOR * PROJECTION_UNITS ), 0)
--     ELSE  FACTOR
--END / NULLIF(PROJECTION_UNITS, 0) ), 0)
--END ,

--PROJECTION_RATE=CASE
--WHEN CALCULATION_BASED = ''RATE'' THEN FACTOR * 100.0
--WHEN CALCULATION_BASED = ''RPU'' THEN COALESCE(( CASE
--     WHEN CALCULATION_BASED = ''RATE'' THEN COALESCE(( FACTOR * NULLIF(PROJECTION_SALES, 0) ), 0)
--     WHEN CALCULATION_BASED = ''RPU'' THEN COALESCE(( FACTOR * PROJECTION_UNITS ), 0)
--     ELSE  FACTOR
--END * 100 / NULLIF(PROJECTION_SALES, 0) ), 0) --CHANGE
--ELSE COALESCE(( CASE
--     WHEN CALCULATION_BASED = ''RATE'' THEN COALESCE(( FACTOR * NULLIF(PROJECTION_SALES, 0) ), 0)
--     WHEN CALCULATION_BASED = ''RPU'' THEN COALESCE(( FACTOR * PROJECTION_UNITS ), 0)
--     ELSE  FACTOR
--END * 100.0 / NULLIF(PROJECTION_SALES, 0) ), 0)
--END
--FROM   '
--                          + @S_PROJECTION_TABLE
--                          + ' SNSP
--INNER JOIN (

--SELECT A.CCP_DETAILS_SID,
--                                                        A.RS_MODEL_SID,
                                                              
--                                                              P.PERIOD_SID,
--                                                              CALCULATION_BASED,
--                                                              P.PERIOD,
                                                              
--                                                        CASE WHEN CALCULATION_BASED=''RATE''           THEN (A.ACTUAL_BASELINE*ISNULL(B.GROWTH,1))
--                                                                   WHEN CALCULATION_BASED=''RPU''                     THEN (A.ACTUAL_BASELINE*ISNULL(B.GROWTH,1))
--                                                                     WHEN CALCULATION_BASED=''AMOUNT''             THEN (A.ACTUAL_BASELINE*ISNULL(B.GROWTH,1))/', @PERIOD_COUNT, '
--                                                                     END FACTOR,
--                                                                     P.ROLL_PERIOD
                                                          
--                                                  FROM #ACTUAL_BASELINE A
--                                                    JOIN  #SALES_FREQUENCY_DATE M ON A.CCP_DETAILS_SID=M.CCP_DETAILS_SID
--                                                                   AND A.RS_MODEL_SID=M.RS_MODEL_SID

--                                                  JOIN #PERIOD P on P.PERIOD_SID BETWEEN M.CALC_START_PERIOD_SID AND M.CALC_END_PERIOD_SID
--                                                LEFT JOIN 
--                                                 (
--                                                SELECT G.CCP_DETAILS_SID,G.RS_MODEL_SID,p.PERIOD_SID,PERIOD,GROWTH 
--                                                 FROM '+ @GROWTH_TABLE+ ' G join #PERIOD P
--                                                ON P.PERIOD_SID=G.PERIOD_SID  )B
--                                                ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID
--                                                    AND A.RS_MODEL_SID=B.RS_MODEL_SID 
--                                                       AND B.PERIOD=P.PERIOD
--                                                ', CASE
--                                       WHEN @METHODOLOGY = 'ROLLING ANNUAL TREND' THEN 'WHERE A.ROLL_PERIOD=P.ROLL_PERIOD'
--                                       ELSE ' '
--                                     END ,'

--) TR
--ON SNSP.CCP_DETAILS_SID = TR.CCP_DETAILS_SID
--    AND SNSP.PERIOD_SID = TR.PERIOD_SID')

--exec (@sql)

set @sql = ''


                ---------------------------------------- MAIN TABLE UPDATION
                SET @SQL=CONCAT(@SQL, 'UPDATE SNDP
                                  SET    SNDP.PROJECTION_SALES = TFR.PROJECTION_AMOUNT,
                                            SNDP.PROJECTION_RPU = TFR.PROJECTION_REBATE_PER_UNIT,
                                            SNDP.PROJECTION_RATE = TFR.PROJECTION_RATE
                                  FROM   '
                          + @PROJECTION_TABLE
                          + ' SNDP
                                            INNER JOIN (
											
											  SELECT 
SNSP.CCP_DETAILS_SID,
SNSP.PERIOD_SID,
RS_MODEL_SID,
RS_CONTRACT_SID,
PROJECTION_AMOUNT=CASE
     WHEN CALCULATION_BASED = ''RATE'' THEN COALESCE(( FACTOR * NULLIF(PROJECTION_SALES, 0) ), 0)
     WHEN CALCULATION_BASED = ''RPU'' THEN COALESCE(( FACTOR * PROJECTION_UNITS ), 0)
     ELSE  FACTOR
END ,
PROJECTION_REBATE_PER_UNIT=
CASE
WHEN CALCULATION_BASED = ''RATE'' THEN COALESCE(( CASE
     WHEN CALCULATION_BASED = ''RATE'' THEN COALESCE(( FACTOR * NULLIF(PROJECTION_SALES, 0) ), 0)
     WHEN CALCULATION_BASED = ''RPU'' THEN COALESCE(( FACTOR * PROJECTION_UNITS ), 0)
     ELSE  FACTOR
END / NULLIF(PROJECTION_UNITS, 0) ), 0)
WHEN CALCULATION_BASED = ''RPU'' THEN FACTOR
ELSE COALESCE(( CASE
     WHEN CALCULATION_BASED = ''RATE'' THEN COALESCE(( FACTOR * NULLIF(PROJECTION_SALES, 0) ), 0)
     WHEN CALCULATION_BASED = ''RPU'' THEN COALESCE(( FACTOR * PROJECTION_UNITS ), 0)
     ELSE  FACTOR
END / NULLIF(PROJECTION_UNITS, 0) ), 0)
END ,

PROJECTION_RATE=CASE
WHEN CALCULATION_BASED = ''RATE'' THEN FACTOR * 100.0
WHEN CALCULATION_BASED = ''RPU'' THEN COALESCE(( CASE
     WHEN CALCULATION_BASED = ''RATE'' THEN COALESCE(( FACTOR * NULLIF(PROJECTION_SALES, 0) ), 0)
     WHEN CALCULATION_BASED = ''RPU'' THEN COALESCE(( FACTOR * PROJECTION_UNITS ), 0)
     ELSE  FACTOR
END * 100 / NULLIF(PROJECTION_SALES, 0) ), 0) --CHANGE
ELSE COALESCE(( CASE
     WHEN CALCULATION_BASED = ''RATE'' THEN COALESCE(( FACTOR * NULLIF(PROJECTION_SALES, 0) ), 0)
     WHEN CALCULATION_BASED = ''RPU'' THEN COALESCE(( FACTOR * PROJECTION_UNITS ), 0)
     ELSE  FACTOR
END * 100.0 / NULLIF(PROJECTION_SALES, 0) ), 0)
END
FROM   '
                          + @S_PROJECTION_TABLE
                          + ' SNSP
INNER JOIN (

SELECT A.CCP_DETAILS_SID,
                                                        A.RS_MODEL_SID,
														A.RS_CONTRACT_SID,
                                                              
                                                              P.PERIOD_SID,
                                                              CALCULATION_BASED,
                                                              P.PERIOD,
                                                              
                                                        CASE WHEN CALCULATION_BASED=''RATE''           THEN (A.ACTUAL_BASELINE*ISNULL(B.GROWTH,1))
                                                                   WHEN CALCULATION_BASED=''RPU''                     THEN (A.ACTUAL_BASELINE*ISNULL(B.GROWTH,1))
                                                                     WHEN CALCULATION_BASED=''AMOUNT''             THEN (A.ACTUAL_BASELINE*ISNULL(B.GROWTH,1))/', @PERIOD_COUNT, '
                                                                     END FACTOR,
                                                                     P.ROLL_PERIOD
                                                          
                                                  FROM #ACTUAL_BASELINE A
                                                    JOIN  #SALES_FREQUENCY_DATE M ON A.CCP_DETAILS_SID=M.CCP_DETAILS_SID
                                                                   AND A.RS_contract_SID=M.RS_contract_SID


                                                  JOIN #PERIOD P on P.PERIOD_SID BETWEEN M.CALC_START_PERIOD_SID AND M.CALC_END_PERIOD_SID
												    and (( A.ROLL_PERIOD=
                                         p.ROLL_PERIOD AND M.METHODOLOGY=''ROLLING ANNUAL TREND'') OR (A.ROLL_PERIOD  IS NULL AND  M.METHODOLOGY<>''ROLLING ANNUAL TREND''))
                                                LEFT JOIN 
                                                 (
                                              SELECT G.CCP_DETAILS_SID,G.RS_MODEL_SID,G.RS_CONTRACT_SID,p.PERIOD_SID,PERIOD,GROWTH 
                                                 FROM '+ @GROWTH_TABLE+ ' G join #PERIOD P
                                                ON P.PERIOD_SID=G.PERIOD_SID  )B
                                                ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID
                                                    AND A.RS_CONTRACT_SID=B.RS_CONTRACT_SID 

                                                       AND B.PERIOD=P.PERIOD and 
                                                 (CASE
                                       WHEN m.methodology = ''ROLLING ANNUAL TREND'' THEN  A.ROLL_PERIOD
                                       ELSE '' ''
                                     END )=( CASE
                                       WHEN m.methodology = ''ROLLING ANNUAL TREND'' THEN  b.PERIOD
                                       ELSE '' ''
                                     END)






) TR
ON SNSP.CCP_DETAILS_SID = TR.CCP_DETAILS_SID
    AND SNSP.PERIOD_SID = TR.PERIOD_SID
											) TFR
                                                          ON SNDP.CCP_DETAILS_SID = TFR.CCP_DETAILS_SID
                                                                AND SNDP.RS_CONTRACT_SID = TFR.RS_CONTRACT_SID

                                                                AND SNDP.PERIOD_SID = TFR.PERIOD_SID')

                EXEC sp_executesql @SQL

				    END

                --SET @SQL= ' UPDATE SNDP
                --                  SET    SNDP.PROJECTION_SALES = 0,
                --                            SNDP.PROJECTION_RPU = 0,
                --                            SNDP.PROJECTION_RATE = 0
                --                  FROM   '
                --          + @PROJECTION_TABLE
                --          + ' SNDP
                --                            JOIN '
                --          + @MASTER_TABLE
                --          + ' SNDPM
                --                                ON SNDP.CCP_DETAILS_SID = SNDPM.CCP_DETAILS_SID
                --                                       AND SNDP.RS_MODEL_SID = SNDPM.RS_MODEL_SID
                --                                       AND SNDPM.CHECK_RECORD = 1
                --                  WHERE  SNDP.PROJECTION_SALES IS NULL
                --                            AND SNDP.PROJECTION_RPU IS NULL
                --                            AND SNDP.PROJECTION_RATE IS NULL'

                --EXEC(@SQL)



          -------------------------------------------------------------------------------------------------------------------------------------
          --DECLARE @ITEM_DETAILS     UDT_ITEM,
          --        @START_PERIOD_SID INT,
          --        @END_PERIOD_SID   INT,
          --        @FORECAST_NAME    VARCHAR(50),
          --        @FORECAST_VERSION VARCHAR(15)

          --SET @START_PERIOD_SID = @ACTUAL_START_SID
          --SET @END_PERIOD_SID = @PROJECTION_END_SID

          ---------------------------------------------      EX-FACTORY SALES------------------------------------ 
          IF EXISTS (SELECT 1
                     FROM   #SALES_FREQUENCY_DATE CCP
                     WHERE  METHODOLOGY IN ( @X_FACTORY, @INVENTORY, @ADJ_DEMAND, @DEMAND,@SEASONAL_TREND ))
            BEGIN
                IF Object_id('TEMPDB.DBO.#BASELINE', 'U') IS NOT NULL
                  DROP TABLE #BASELINE

                CREATE TABLE #BASELINE
                  (
                     CCP_DETAILS_SID       INT,
                     RS_MODEL_SID          INT,
                     DISCOUNT			   NUMERIC(22,6),
                     METHODOLOGY           VARCHAR(50),
                     CALC_START_PERIOD_SID INT,
                     CALC_END_PERIOD_SID   INT,   
                     PERIOD_SID            INT,
                     RATIO                 NUMERIC(22, 15),
                     ITEM_MASTER_SID       INT,
					 ROLL_PERIOD            INT
                  )



                SET @SQL=Concat(' ;WITH CTE
                              AS (SELECT T_CCP.CCP_DETAILS_SID,
                                                  T_CCP.RS_MODEL_SID,
                                                                                  
                                                  
                                                  METHODOLOGY,
                                                  CALC_START_PERIOD_SID,
                                                  CALC_END_PERIOD_SID,
                                                 ACTUAL_SALES AS DISCOUNT,
                                                  CS.PERIOD_SID,
                                                                                    CASE WHEN T_CCP.METHODOLOGY IN (''', @X_FACTORY, ''',''', @SEASONAL_TREND, ''') THEN P.EXFACTORY_ACTUAL_SALES
                                                                                         WHEN T_CCP.METHODOLOGY=''', @DEMAND, '''    THEN P.DEMAND_ACTUAL_SALES
                                                                                            WHEN T_CCP.METHODOLOGY=''', @INVENTORY, ''' THEN P.INVENTORY_ACTUAL_SALES
                                                                                            WHEN T_CCP.METHODOLOGY=''', @ADJ_DEMAND, ''' THEN P.ADJUSTED_DEMAND_ACTUAL_SALES
                                                                                  END FILE_SALES,
                                                                                  P.ITEM_MASTER_SID,ROLL_PERIOD
                                     FROM   #SALES_FREQUENCY_DATE T_CCP
                                                  CROSS APPLY (SELECT PERIOD_SID,ROLL_PERIOD
                                                                        FROM   UDF_SPLITSTRING(T_CCP.CALCULATION_PERIODS, '','') FN
                                                                                    JOIN #PERIOD P
                                                                                         ON P.PERIOD = FN.TOKEN) CS
                                                  INNER JOIN ', @ACTUAL_TABLE, ' SNAD
                                                                ON CS.PERIOD_SID = SNAD.PERIOD_SID
                                                                     AND T_CCP.CCP_DETAILS_SID = SNAD.CCP_DETAILS_SID
                                                                     AND T_CCP.RS_MODEL_SID = SNAD.RS_MODEL_SID

                                                                                    INNER JOIN       ', @PRODUCT_TABLE, ' P ON P.ITEM_MASTER_SID=T_CCP.ITEM_MASTER_SID
                                                                                                     AND CS.PERIOD_SID =P.PERIOD_SID
                                     WHERE   T_CCP.METHODOLOGY IN (''', @X_FACTORY, ''',', '''', @DEMAND, ''',', '''', @INVENTORY, ''',', '''', @ADJ_DEMAND, ''',''', @SEASONAL_TREND, '''))
                       
                                     INSERT INTO #BASELINE
                                     (
                                       CCP_DETAILS_SID,
                         RS_MODEL_SID,
                                         
                         
                         METHODOLOGY,
                         CALC_START_PERIOD_SID,
                         CALC_END_PERIOD_SID,
                                         DISCOUNT,
                                         PERIOD_SID,
                                         RATIO,
                                         ITEM_MASTER_SID
										 ,ROLL_PERIOD
                                     )
                                     
                                     SELECT CCP_DETAILS_SID,
                                  RS_MODEL_SID,
                                                       
                                  
                                  METHODOLOGY,
                                  CALC_START_PERIOD_SID,
                                  CALC_END_PERIOD_SID,
                                  SUM(DISCOUNT)     DISCOUNT,
                                  MIN(C.PERIOD_SID) PERIOD_SID,
                                                         SUM(DISCOUNT)/NULLIF(SUM(FILE_SALES),0) RATIO,
                                                         ITEM_MASTER_SID,
														 case when C.METHODOLOGY<>''', @X_FACTORY, ''' THEN 
														 C.ROLL_PERIOD  END

                     
                       FROM   CTE C
                                  JOIN #PERIOD P
                                     ON C.PERIOD_SID = P.PERIOD_SID
									 AND C.ROLL_PERIOD=P.ROLL_PERIOD
                       GROUP  BY CCP_DETAILS_SID,
                                         RS_MODEL_SID,
                                                                     
                                         
                                         METHODOLOGY,
                                         CALC_START_PERIOD_SID,
                                         CALC_END_PERIOD_SID,
										 case when C.METHODOLOGY<>''', @X_FACTORY, ''' THEN 
														 PERIOD_YEAR END
                                         ,
                                        case when C.METHODOLOGY<>''', @X_FACTORY, ''' THEN 
														 PERIOD END ,

                                         ITEM_MASTER_SID
										 ,case when C.METHODOLOGY<>''', @X_FACTORY, ''' THEN 
														 C.ROLL_PERIOD  END')

                EXEC sp_executesql @SQL


                --------------------------------------------------------#EX_FINAL_RESULTS------------------------------------------------
            
				DECLARE @STATIC_SEASON_TREND VARCHAR(50)
SELECT TOP 1 @STATIC_SEASON_TREND = METHODOLOGY 
FROM #SALES_FREQUENCY_DATE
WHERE METHODOLOGY = @SEASONAL_TREND

                IF Object_id('TEMPDB.DBO.#EX_FINAL_RESULTS', 'U') IS NOT NULL
                  DROP TABLE #EX_FINAL_RESULTS;

                CREATE TABLE #EX_FINAL_RESULTS
                  (
                     CCP_DETAILS_SID            INT,
                     RS_MODEL_SID               INT,
                     PERIOD_SID                 INT,
                     PROJECTION_RATE            NUMERIC(22, 6),
                     PROJECTION_REBATE_PER_UNIT NUMERIC(22, 6),
                     PROJECTION_AMOUNT          NUMERIC(22, 6)
                  )

                SET @SQL=Concat('INSERT INTO #EX_FINAL_RESULTS
                                                         (
                                                         CCP_DETAILS_SID,    
                                                         RS_MODEL_SID,
                                                         PERIOD_SID,     
                                                         PROJECTION_RATE,     
                                                         PROJECTION_REBATE_PER_UNIT,
                                                         PROJECTION_AMOUNT          
                                                         )

                                  SELECT A.CCP_DETAILS_SID,
                                         A.RS_MODEL_SID,
                                         A.PERIOD_SID,
                                         ( PROJECTION_AMOUNT / PROJECTION_SALES ) * 100.0 AS PROJECTION_RATE,
                                         ( PROJECTION_AMOUNT / PROJECTION_UNITS )         AS PROJECTION_REBATE_PER_UNIT,
                                         A.PROJECTION_AMOUNT
                                
                                  FROM   (SELECT CCP_DETAILS_SID,
                                                          PERIOD_SID,
                                                          NULLIF(PROJECTION_SALES, 0) PROJECTION_SALES,
                                                          NULLIF(PROJECTION_UNITS, 0) PROJECTION_UNITS
                                                         
                                                FROM ', @S_PROJECTION_TABLE, ' 
                                                  ) SNAP
                                            JOIN (
                                                                            
                                                                           
                                                                           SELECT B.CCP_DETAILS_SID,
                                                                             B.RS_MODEL_SID,
                                                                           
                                                                             P.PERIOD_SID,
                                                                           (B.RATIO)* 
                                                                             (CASE WHEN T_CCP.METHODOLOGY IN (''', @X_FACTORY, ''',''', @SEASONAL_TREND, ''')  THEN  case when P1.period_sid<',@ACTUAL_PERIOD,'  THEN ISNULL(EXFACTORY_ACTUAL_SALES,0) ELSE ISNULL(EXFACTORY_FORECAST_SALES,0) END
                                                                                         WHEN T_CCP.METHODOLOGY=''', @DEMAND, '''    THEN  ISNULL(DEMAND_FORECAST_SALES,DEMAND_ACTUAL_SALES)
                                                                                            WHEN T_CCP.METHODOLOGY=''', @INVENTORY, ''' THEN  ISNULL(INVENTORY_FORECAST_SALES,INVENTORY_ACTUAL_SALES)
                                                                                            WHEN T_CCP.METHODOLOGY=''', @ADJ_DEMAND, ''' THEN ISNULL(ADJUSTED_DEMAND_FORECAST_SALES,ADJUSTED_DEMAND_ACTUAL_SALES)
                                                                                  END) AS PROJECTION_AMOUNT

                                                                           FROM #BASELINE B  ',CASE WHEN @SEASONAL_TREND= @STATIC_SEASON_TREND THEN '    JOIN #PERIOD P ON P.ROLL_PERIOD=B.ROLL_PERIOD' ELSE 'CROSS JOIN #PERIOD P'  END,'
																		   
                                                                           
                                                                            JOIN #SALES_FREQUENCY_DATE T_CCP
                                                                           ON T_CCP.CCP_DETAILS_SID=B.CCP_DETAILS_SID
                                                                           AND T_CCP.RS_MODEL_SID = B.RS_MODEL_SID 
                                                                            JOIN
                                                                           ', @PRODUCT_TABLE, '  P1
                                                                           ON P1.ITEM_MASTER_SID=T_CCP.ITEM_MASTER_SID 
                                                                            AND P.PERIOD_SID=P1.PERIOD_SID
                                                                           WHERE P.PERIOD_SID BETWEEN T_CCP.CALC_START_PERIOD_SID AND T_CCP.CALC_END_PERIOD_SID
                                                                           ) A
                                                ON A.CCP_DETAILS_SID = SNAP.CCP_DETAILS_SID
                                                       AND A.PERIOD_SID = SNAP.PERIOD_SID
                                
                                  ')
				
                EXEC sp_executesql @SQL
                     
                ---------------------------------------- MAIN TABLE UPDATION
                SET @SQL= 'UPDATE SNDP
                                  SET    SNDP.PROJECTION_SALES = TFR.PROJECTION_AMOUNT,
                                            SNDP.PROJECTION_RPU = TFR.PROJECTION_REBATE_PER_UNIT,
                                            SNDP.PROJECTION_RATE = TFR.PROJECTION_RATE
                                  FROM   '
                          + @PROJECTION_TABLE
                          + ' SNDP
                                            INNER JOIN #EX_FINAL_RESULTS TFR
                                                          ON SNDP.CCP_DETAILS_SID = TFR.CCP_DETAILS_SID
                                                                AND SNDP.RS_MODEL_SID = TFR.RS_MODEL_SID
                                                                AND SNDP.PERIOD_SID = TFR.PERIOD_SID'

                EXEC sp_executesql @SQL

                --SET @SQL= ' UPDATE SNDP
                --                  SET    SNDP.PROJECTION_SALES = 0,
                --                            SNDP.PROJECTION_RPU = 0,
                --                            SNDP.PROJECTION_RATE = 0
                --                  FROM   '
                --          + @PROJECTION_TABLE
                --          + ' SNDP
                --                            JOIN '
                --          + @MASTER_TABLE
                --          + ' SNDPM
                --                                ON SNDP.CCP_DETAILS_SID = SNDPM.CCP_DETAILS_SID
                --                                       AND SNDP.RS_MODEL_SID = SNDPM.RS_MODEL_SID
                --                                       AND SNDPM.CHECK_RECORD = 1
                --                  WHERE  SNDP.PROJECTION_SALES IS NULL
                --                            AND SNDP.PROJECTION_RPU IS NULL
                --                            AND SNDP.PROJECTION_RATE IS NULL'

                --EXEC(@SQL)
            END

          -------------------------------------------------------------------------------------
          IF EXISTS (SELECT 1
                     FROM   #SALES_FREQUENCY_DATE CCP
                     WHERE  METHODOLOGY = 'CONTRACT DETAILS')
            BEGIN
                EXEC Prc_contract_details
                  @PROJECTION,
                  @FREQUENCY,
                  @USER_ID,
                  @SESSION_ID,
				  @FORECAST_START_PERIOD_SID,
				  @FORECAST_END_PERIOD_SID,
				  @DEDUCTION_INCLUSION 
            END

          ---------------------------------------NEW ADDITION---------------------------------------------------------------------------------------


          SET @SQL= Concat('   UPDATE SNDP
                                  SET    SNDP.PROJECTION_SALES = 0,
                                            SNDP.PROJECTION_RPU   = 0,
                                            SNDP.PROJECTION_RATE  = 0
                                  FROM   ', @PROJECTION_TABLE, ' SNDP
                                            INNER JOIN #SALES_FREQUENCY_DATE TEF
                                                          ON (SNDP.CCP_DETAILS_SID = TEF.CCP_DETAILS_SID
                                                                           AND SNDP.RS_MODEL_SID = TEF.RS_MODEL_SID
                                                                           AND SNDP.PERIOD_SID NOT BETWEEN TEF.EFFECT_UPD_START_PERIOD_SID AND TEF.EFFECT_UPD_END_PERIOD_SID)
                                                                                                                             ')

          EXEC sp_executesql @SQL


	      END

		


	      


	      


                                  


	      


 