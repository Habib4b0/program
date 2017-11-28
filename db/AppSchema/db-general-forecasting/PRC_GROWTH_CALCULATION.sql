IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_GROWTH_CALCULATION'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_GROWTH_CALCULATION]
  END

GO

CREATE PROCEDURE [dbo].[PRC_GROWTH_CALCULATION]( @PROJECTION        INT,
                                               @USER_ID           INT,
                                               @SESSION_ID        VARCHAR(50),
                                               @SCREEN_NAME       VARCHAR(50),
                                               @CALCULATION_BASED VARCHAR(50)=NULL,
                                               @FREQUENCY         CHAR(1),
											   @TABLE_DATE        DATETIME,--Added for resolving time difference issue,
											   @LEVEL VARCHAR(10)= NULL,
											   @FORECAST_START_PERIOD_SID  INT=null,
											   @FORECAST_END_PERIOD_SID  INT=null,
											   @DISCOUNT VARCHAR(4000)=NULL)





AS
/**********************************************************************************************************
** File Name		:	PRC_GROWTH_CALCULATION.sql
** Procedure Name	:	PRC_GROWTH_CALCULATION
** Description		:	To Calculate the Growth based on modules (sales,discount,national assumptions,returns).
** Input Parameters	:	@PROJECTION             - Respective Projection ID Creted based on module
                        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
						@SCREEN_NAME            - It is used to differntiate the module for which we are performing growth calculation
						@CALCULATION_BASED      - it is used to find out calculation based choosen in sales projection screen
						                          available values are Sales and Units
						@FREQUENCY              - chossen frequency in UI.
                     	@TABLE_DATE             - Added for resolving time difference issue
						@LEVEL                  - this for National assumptions screen for the identification of selecting NDC
				        
											
** Output Parameters:	NA
** Author Name		:	@Tharun M
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by application for Growth calculation for Respective module
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No         Author          Description 
** ---   --------  ---------        -------------    -----------------------------

** 
*********************************************************************************************************/



BEGIN
  SET NOCOUNT ON

--Variables intialization for dynamic temp tables and Periods Starts here
      DECLARE @MASTER_TABLE        VARCHAR(200),
              @PROJECTION_TABLE    VARCHAR(200),
              @CCP_HIERARCHY       VARCHAR(200) = CONCAT('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @S_MASTER_TABLE varchar(100)=CONCAT('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @PROJ_END_PERIOD_SID INT,
			  @METHODOLOGY         VARCHAR(100),
			  @PRODUCT_FILE        VARCHAR(100)=CONCAT('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
			  
				@MIN_PERIOD  INT,
				@MAX_PERIOD  INT,
				@FORECASTING_TYPE   VARCHAR(50)

 --Variables intialization for dynamic temp tables and Periods Ends here

 --Master and Projection temp tables assigning based on screen for sales and discount starts here

 	SELECT @FORECASTING_TYPE=FORECASTING_TYPE FROM PROJECTION_MASTER WHERE PROJECTION_MASTER_SID=@PROJECTION

    SELECT @MASTER_TABLE = ( CASE
                                 WHEN @SCREEN_NAME = 'SALES PROJECTION' AND @FORECASTING_TYPE='NON MANDATED' THEN CONCAT('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
								  WHEN @SCREEN_NAME = 'SALES PROJECTION' AND @FORECASTING_TYPE='MANDATED' THEN CONCAT('ST_M_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
                                 WHEN @SCREEN_NAME = 'DISCOUNT PROJECTION' AND @FORECASTING_TYPE='NON MANDATED' THEN CONCAT('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
                               END ),
             @PROJECTION_TABLE = ( CASE
                                     WHEN @SCREEN_NAME = 'SALES PROJECTION' AND @FORECASTING_TYPE='NON MANDATED'   THEN CONCAT('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
									 WHEN @SCREEN_NAME = 'SALES PROJECTION' AND @FORECASTING_TYPE='MANDATED'   THEN CONCAT('ST_M_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
                                     WHEN @SCREEN_NAME = 'DISCOUNT PROJECTION' AND @FORECASTING_TYPE='NON MANDATED' THEN CONCAT('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
                                   END )


--Particular Projection End period and making Last date of that to period year Starts here

  DECLARE @TO_DATE DATETIME
      SELECT @TO_DATE  =DATEADD(YEAR,DATEDIFF(YEAR,0,TO_DATE)+1,0)-1
      FROM   PROJECTION_MASTER
      WHERE  PROJECTION_MASTER_SID = @PROJECTION

	  SELECT @PROJ_END_PERIOD_SID=PERIOD_SID FROM PERIOD WHERE PERIOD_DATE=DATEADD(MM,DATEDIFF(MM,0,@TO_DATE),0) 
--Particular Projection End period and making Last date of that to period year Ends here
--taking selected methodolgy based on screen Starts here      
	  IF @SCREEN_NAME in ( 'SALES PROJECTION','DISCOUNT PROJECTION')
	  BEGIN
	  
	  	DECLARE @DSQL NVARCHAR(MAX)
	  SET @DSQL=CONCAT('SELECT @METHODOLOGY=METHODOLOGY FROM ',@MASTER_TABLE,' WHERE CHECK_RECORD=1')

		EXEC SP_EXECUTESQL @DSQL ,N'@METHODOLOGY VARCHAR(100) OUTPUT',
		                         @METHODOLOGY OUTPUT

       END
--taking selected methodolgy based on screen Ends here  

--taking periods based on frequency selected Starts here

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
                      END AS PERIOD,
              CASE
                             WHEN @FREQUENCY = 'M' THEN MONTH
                             WHEN @FREQUENCY = 'Q' THEN QUARTER
                             WHEN @FREQUENCY = 'S' THEN SEMI_ANNUAL
                             ELSE YEAR
                           END AS ROLL_PERIOD
      INTO   #PERIOD
      FROM   PERIOD
 SET @FORECAST_END_PERIOD_SID=ISNULL(@FORECAST_END_PERIOD_SID,@PROJ_END_PERIOD_SID)


--taking periods based on frequency selected Ends here

      DECLARE @SQL NVARCHAR(MAX)
      DECLARE @VAR1 VARCHAR(MAX)
      
      DECLARE @PERIOD_COUNT SMALLINT
	  DECLARE @LAG_VALUE TINYINT

  --calculation logic of frequency based calculation and rolling annual trend taking perios Strats here for Sales Projection

      IF @SCREEN_NAME = 'SALES PROJECTION'
        BEGIN

		            SET @PERIOD_COUNT = CASE @FREQUENCY
                                  WHEN 'Q' THEN 3
                                  WHEN 'S' THEN 6
                                  WHEN 'A' THEN 12
                                  ELSE 1
                                END
	
		
     
	   SET @LAG_VALUE = CASE WHEN @METHODOLOGY='ROLLING ANNUAL TREND' THEN
	                     CASE @FREQUENCY
                         WHEN 'Q' THEN 4
                         WHEN 'S' THEN 2
                         WHEN 'A' THEN 1
                         ELSE 12
                       END 
				   END

 --calculation logic of frequency based calculation and rolling annual trend taking perios Ends here for Sales Projection   
 
 --calculation based(Sales and Units) and taking repective tables based on condition starts here
            

            SET @VAR1=CASE
                        WHEN @SCREEN_NAME = 'SALES PROJECTION'
                             AND @CALCULATION_BASED = 'UNITS' THEN '  JOIN ' + @MASTER_TABLE
                                                                   + ' M ON M.CCP_DETAILS_SID=N.CCP_DETAILS_SID WHERE '''+@CALCULATION_BASED+'''=''UNITS'' AND M.CHECK_RECORD = 1 GROUP BY N.CCP_DETAILS_SID,C.ITEM_MASTER_SID HAVING MAX(ABS(ACCOUNT_GROWTH))>0 OR MAX(ABS(PRODUCT_GROWTH))>0'
                        WHEN @SCREEN_NAME = 'SALES PROJECTION'
                             AND @CALCULATION_BASED = 'SALES' THEN '  AND N.CHECK_RECORD = 1'
                      END


 --calculation based(Sales and Units) and taking repective tables based on condition starts here

 --Pulling Items based on selected calculation based condition for the repective projection Starts here

            IF OBJECT_ID('TEMPDB..#CCP_ELIGIBLE') IS NOT NULL
              DROP TABLE #CCP_ELIGIBLE

            CREATE TABLE #CCP_ELIGIBLE
              (
                 CCP_DETAILS_SID INT,ITEM_MASTER_SID INT 
              )

            SET @SQL=CONCAT('INSERT INTO #CCP_ELIGIBLE
	 (
	 CCP_DETAILS_SID,ITEM_MASTER_SID
	 )
	 SELECT N.CCP_DETAILS_SID,C.ITEM_MASTER_SID FROM ',CASE WHEN @CALCULATION_BASED='SALES' THEN  @MASTER_TABLE ELSE @PROJECTION_TABLE  END,' N JOIN CCP_DETAILS C ON C.CCP_DETAILS_SID = N.CCP_DETAILS_SID ',  @VAR1)


            EXEC sp_executesql @SQL

 --Pulling Items based on selected calculation based condition for the repective projection Ends here

 --Pulling CCP information,calcuation periods information based on selected to period Starts here
           
		    IF OBJECT_ID('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL
              DROP TABLE #CCP_DETAILS_TEMP

            CREATE TABLE #CCP_DETAILS_TEMP
              (
                 CCP_DETAILS_SID       INT,
                 COMPANY_MASTER_SID    INT,
                 ITEM_MASTER_SID       INT,
                 CONTRACT_MASTER_SID   INT,
                 CALCULATION_BASED     VARCHAR(25),
                 CALCULATION_PERIODS   VARCHAR(4000),
                 CALC_START_PERIOD_SID INT,
                 CALC_END_PERIOD_SID   INT,
              )


            SET @SQL=CONCAT('

	   ;WITH CTE AS
	   (
                                                  SELECT CH.CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID,''',@CALCULATION_BASED,''' as calculation_based,
                                           CALC_START_PERIOD_SID = IIF(',@FORECAST_START_PERIOD_SID,'>EFFECTIVE_START_PERIOD_SID,
                                                                           IIF(',@FORECAST_START_PERIOD_SID,'>CS.MAX_BASELINE_PERIOD,',@FORECAST_START_PERIOD_SID,',CS.MAX_BASELINE_PERIOD),
                                                                           IIF(EFFECTIVE_START_PERIOD_SID>CS.MAX_BASELINE_PERIOD,EFFECTIVE_START_PERIOD_SID,CS.MAX_BASELINE_PERIOD))
                                         ,
                 CALC_END_PERIOD_SID =  IIF(COALESCE(',@FORECAST_END_PERIOD_SID,', ', @PROJ_END_PERIOD_SID, ') < EFFECTIVE_END_PERIOD_SID ,COALESCE(',@FORECAST_END_PERIOD_SID,',  ', @PROJ_END_PERIOD_SID, '),EFFECTIVE_END_PERIOD_SID)
                           
                             FROM ' + @CCP_HIERARCHY + ' CH
                          JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                           JOIN ' + @MASTER_TABLE + ' NSPM
                      ON CD.CCP_DETAILS_SID = NSPM.CCP_DETAILS_SID
                             AND NSPM.CHECK_RECORD=1
					  --JOIN #CCP_ELIGIBLE CE
					  --  ON CE.CCP_DETAILS_SID=NSPM.CCP_DETAILS_SID
						  
                               OUTER APPLY (SELECT TOP 1 P.PERIOD_SID + 1 AS MAX_BASELINE_PERIOD
                          FROM   UDF_SPLITSTRING(NSPM.CALCULATION_PERIODS, '','') FN
                                 JOIN #PERIOD P
                                   ON PERIOD = FN.TOKEN
                          ORDER BY PERIOD_SID DESC) CS
                             
							 )
							 UPDATE NSPM SET FREQ_CAL_START_PERIOD_SID = EFFECTIVE_START_PERIOD_SID - ( ( EFFECTIVE_START_PERIOD_SID - 1 ) % ',@PERIOD_COUNT,' ),
							  FREQ_CAL_END_PERIOD_SID = EFFECTIVE_END_PERIOD_SID - ( ( EFFECTIVE_END_PERIOD_SID - 1 ) % ',@PERIOD_COUNT,' ) + ( ',@PERIOD_COUNT,' - 1 )
							 FROM ' + @MASTER_TABLE + ' NSPM 
							 JOIN CTE C ON NSPM.CCP_DETAILS_SID = C.CCP_DETAILS_SID


                                 ')
							


            EXEC sp_executesql @SQL

 --Pulling CCP information,calcuation periods information based on selected to period Ends here

            --UPDATE #CCP_DETAILS_TEMP
            --SET    CALC_START_PERIOD_SID = CALC_START_PERIOD_SID - ( ( CALC_START_PERIOD_SID - 1 ) % @PERIOD_COUNT ),
            --       CALC_END_PERIOD_SID = CALC_END_PERIOD_SID - ( ( CALC_END_PERIOD_SID - 1 ) % @PERIOD_COUNT ) + ( @PERIOD_COUNT - 1 )



            --SELECT @DATA_START_PERIOD_SID = MIN(CALC_START_PERIOD_SID),
            --       @DATA_END_PERIOD_SID = MAX(CALC_END_PERIOD_SID)
            --FROM   #CCP_DETAILS_TEMP

--Pulling Account and Product Growth based on methodology selected starts here

            SET @SQL=CONCAT(';

      WITH GROWTH_PERCENT
           AS (SELECT NSP.CCP_DETAILS_SID,
                      MIN(ACCOUNT_GROWTH) ACCOUNT_GROWTH,
                      MIN(PRODUCT_GROWTH) PRODUCT_GROWTH,
                      MIN(NSP.PERIOD_SID) PERIOD_SID,
                      CE.ITEM_MASTER_SID,
					  P.ROLL_PERIOD    ROLLPERIOD,
					  P.PERIOD_YEAR AS PERIOD_YEAR
               FROM   ', @PROJECTION_TABLE, ' NSP
                      INNER JOIN ',@MASTER_TABLE ,'  NSPM
                              ON NSP.CCP_DETAILS_SID = NSPM.CCP_DETAILS_SID
							  AND nsp.PERIOD_SID between NSPM.FREQ_CAL_START_PERIOD_SID and NSPM.FREQ_CAL_END_PERIOD_SID
						JOIN #CCP_ELIGIBLE CE
					    ON CE.CCP_DETAILS_SID=NSP.CCP_DETAILS_SID 	
						JOIN #PERIOD P ON P.PERIOD_SID = NSP.PERIOD_SID
									  GROUP BY NSP.CCP_DETAILS_SID,ITEM_MASTER_SID,P.PERIOD_YEAR,P.PERIOD,P.ROLL_PERIOD,P.PERIOD_YEAR

					  ),
           CURRENT_WAC
           AS (SELECT ITEM_MASTER_SID,
                      PERIOD_SID=MIN(GW.PERIOD_SID),
                      FORECAST_PRICE=SUM(EXFACTORY_FORECAST_SALES) / NULLIF(SUM(EXFACTORY_FORECAST_UNITS), 0),
                      ITEM_PRICE= ISNULL(( SUM(ITEM_PRICE * DAY(EOMONTH(PERIOD_DATE))) / NULLIF(SUM(DAY(EOMONTH(PERIOD_DATE))), 0) ), 0)
               FROM   ',@PRODUCT_FILE,' GW
                      INNER JOIN #PERIOD P
                              ON GW.PERIOD_SID = P.PERIOD_SID
               WHERE  EXISTS (SELECT 1
                              FROM   #CCP_ELIGIBLE CCP JOIN ',@MASTER_TABLE ,'  NSPM ON CCP.CCP_DETAILS_SID = NSPM.CCP_DETAILS_SID
                              WHERE  CCP.ITEM_MASTER_SID = GW.ITEM_MASTER_SID
                                     AND ''',@CALCULATION_BASED,''' = ''SALES'')
               GROUP BY ITEM_MASTER_SID,
                         PERIOD),
           WAC_INCREASE
           AS (SELECT ITEM_MASTER_SID,
                      PERIOD_SID,
                      CURRENT_WAC=COALESCE(FORECAST_PRICE, ITEM_PRICE),
                      PREVIOUS_WAC = LAG(COALESCE(FORECAST_PRICE, ITEM_PRICE), ',ISNULL(@LAG_VALUE,1),')
                                       OVER(
                                         PARTITION BY ITEM_MASTER_SID
                                         ORDER BY PERIOD_SID)
               FROM   CURRENT_WAC)
      SELECT GP.CCP_DETAILS_SID,
             GP.PERIOD_SID,
             ( 1
               + ISNULL((CURRENT_WAC - PREVIOUS_WAC)/NULLIF(PREVIOUS_WAC, 0), 0) ) * ( ISNULL(( 1 + ACCOUNT_GROWTH / 100 ), 0) ) * ( ISNULL(( 1 + PRODUCT_GROWTH / 100 ), 0) ) AS GROWTH,
			   ',CASE WHEN @METHODOLOGY='ROLLING ANNUAL TREND' THEN 'Gp.rollperiod' ELSE '0' end,' AS ROLLPERIOD
      FROM   GROWTH_PERCENT GP
             LEFT OUTER JOIN WAC_INCREASE WI
                          ON GP.ITEM_MASTER_SID = WI.ITEM_MASTER_SID
						  AND GP.PERIOD_SID = WI.PERIOD_SID ',CASE WHEN (@METHODOLOGY='SINGLE PERIOD' OR @METHODOLOGY='AVERAGE') THEN ' ORDER BY GP.CCP_DETAILS_SID,GP.PERIOD_SID' 
						  WHEN @METHODOLOGY='ROLLING ANNUAL TREND' THEN 'ORDER BY GP.CCP_DETAILS_SID,ROLLPERIOD,PERIOD_YEAR'
						        
						  END)
						

            EXEC sp_executesql @SQL

			PRINT @SQL
--Pulling Account and Product Growth based on methodology selected starts here

--Droping the existing and recreating the sales growth table and inserting into temp table Starts here
			 DECLARE @SALES_GROWTH_TABLE VARCHAR(100)

SET @SALES_GROWTH_TABLE= CONCAT('ST_SALES_GROWTH_FACTOR_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), @TABLE_DATE, 2), '.', ''))

SET @SQL= CONCAT('
            IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''',@SALES_GROWTH_TABLE,'''
                      AND TABLE_SCHEMA = ''DBO'')
					  BEGIN
			   DROP TABLE ',@SALES_GROWTH_TABLE,' 
       END
           CREATE TABLE ',@SALES_GROWTH_TABLE,'
                   (
				   CCP_DETAILS_SID     INT,
				   PERIOD_SID          INT,
				   GROWTH              NUMERIC(22,15)

				   )'

)

EXEC sp_executesql @SQL

--Droping the existing and recreating the sales growth table and inserting into temp table Ends here

        END
--Growth calculation of discount projection(screen name) Starts here
      ELSE IF @SCREEN_NAME = 'DISCOUNT PROJECTION'
        BEGIN
		SET @METHODOLOGY=@CALCULATION_BASED
--taking CCP+D Combination Starts here
            IF OBJECT_ID('TEMPDB..#CCP_ELIGIBLE1') IS NOT NULL
              DROP TABLE #CCP_ELIGIBLE1

            CREATE TABLE #CCP_ELIGIBLE1
              (
                 CCP_DETAILS_SID INT,
                 RS_CONTRACT_SID    INT

              )

            SET @SQL=CONCAT('INSERT INTO #CCP_ELIGIBLE1
	 (
	 CCP_DETAILS_SID,
	 RS_CONTRACT_SID
	 )
	 SELECT  P.CCP_DETAILS_SID,P.RS_CONTRACT_SID FROM ', @PROJECTION_TABLE, ' P JOIN ',@MASTER_TABLE,' M ON P.CCP_DETAILS_SID = M.CCP_DETAILS_SID AND 
	 P.RS_CONTRACT_SID = M.RS_CONTRACT_SID
	 WHERE M.CHECK_RECORD = 1
	 GROUP BY P.CCP_DETAILS_SID,P.RS_CONTRACT_SID HAVING MAX(ABS(GROWTH))>0')
	  ---GROUP BY P.CCP_DETAILS_SID,P.RS_MODEL_SID HAVING MAX(GROWTH)>0')
--taking CCP+D Combination Ends here
            EXEC sp_executesql @SQL

--frequency based calculation logic inserting into temp table Starts here
			
 IF OBJECT_ID('TEMPDB..#SALES_FREQ') IS NOT NULL
     DROP TABLE #SALES_FREQ
     CREATE TABLE #SALES_FREQ
	 (
	 CCP_DETAILS_SID  INT,
	 PERIOD_COUNT    INT,
	 FREQUENCY    CHAR(1)
	 )


	 SET @SQL=CONCAT('INSERT INTO #SALES_FREQ
	                  (
					   CCP_DETAILS_SID,
					   PERIOD_COUNT ,
					   FREQUENCY    
					  )
           SELECT CCP_DETAILS_SID, CASE  LEFT(CALCULATION_PERIODS,1)
		   
		   WHEN ''Q'' THEN 3
		   WHEN ''S'' THEN 6
           WHEN ''M'' THEN 1
          ELSE 12
          END,LEFT(CALCULATION_PERIODS,1)
		   
		     FROM  ',@S_MASTER_TABLE
	 
	 )
   EXEC sp_executesql @SQL

--frequency based calculation logic inserting into temp table Ends here
 --Pulling CCP+D information,Calcuation Periods information based on selected to period Starts here

            SET @SQL=CONCAT('
	   ;WITH CTE AS
	   (

                                                  SELECT CH.CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CD.CONTRACT_MASTER_SID,''',@CALCULATION_BASED,''' as calculation_based,NSPM.RS_MODEL_SID,NSPM.RS_CONTRACT_SID,
                                           CALC_START_PERIOD_SID = IIF(',@FORECAST_START_PERIOD_SID,'>EFFECTIVE_START_PERIOD_SID,
                                                                           IIF(',@FORECAST_START_PERIOD_SID,'>CS.MAX_BASELINE_PERIOD,',@FORECAST_START_PERIOD_SID,',CS.MAX_BASELINE_PERIOD),
                                                                           IIF(EFFECTIVE_START_PERIOD_SID>CS.MAX_BASELINE_PERIOD,EFFECTIVE_START_PERIOD_SID,CS.MAX_BASELINE_PERIOD))
                                         ,
                 CALC_END_PERIOD_SID =  IIF(COALESCE(',@FORECAST_END_PERIOD_SID,', ', @PROJ_END_PERIOD_SID, ') < EFFECTIVE_END_PERIOD_SID ,COALESCE(',@FORECAST_END_PERIOD_SID,',  ', @PROJ_END_PERIOD_SID, '),EFFECTIVE_END_PERIOD_SID)
                           
                             FROM ' , @CCP_HIERARCHY , ' CH
                               JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                 JOIN ' , @MASTER_TABLE , ' NSPM
                      ON CD.CCP_DETAILS_SID = NSPM.CCP_DETAILS_SID
                             AND NSPM.CHECK_RECORD=1
					  --JOIN #CCP_ELIGIBLE1 CE
					  --  ON CE.CCP_DETAILS_SID=NSPM.CCP_DETAILS_SID
						--  AND CE.RS_MODEL_SID=NSPM.RS_MODEL_SID
						JOIN RS_CONTRACT RS ON RS.RS_CONTRACT_SID=NSPM.RS_CONTRACT_SID
						AND RS.CONTRACT_MASTER_SID=CD.CONTRACT_MASTER_SID
						 AND NSPM.METHODOLOGY =''',@CALCULATION_BASED,'''
								   AND CASE WHEN ''',@LEVEL,''' =''PROGRAM'' THEN RS.RS_NAME ELSE NSPM.PRICE_GROUP_TYPE END IN (SELECT TOKEN
	FROM [DBO].[UDF_SPLITSTRING](''',@DISCOUNT,''', '',''))
                               OUTER APPLY (SELECT TOP 1 P.PERIOD_SID + 1 AS MAX_BASELINE_PERIOD
                          FROM   UDF_SPLITSTRING(NSPM.CALCULATION_PERIODS, '','') FN
                                 JOIN #PERIOD P
                                   ON PERIOD = FN.TOKEN
                          ORDER BY PERIOD_SID DESC) CS
                             )
							 

            UPDATE A
            SET    FREQ_CAL_START_PERIOD_SID = EFFECTIVE_START_PERIOD_SID - ( ( EFFECTIVE_START_PERIOD_SID - 1 ) % PERIOD_COUNT ),
                   FREQ_CAL_END_PERIOD_SID = EFFECTIVE_END_PERIOD_SID - ( ( EFFECTIVE_END_PERIOD_SID - 1 ) % PERIOD_COUNT ) + ( PERIOD_COUNT - 1 )
           FROM ',@MASTER_TABLE,' A
		     JOIN #SALES_FREQ B
			 ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID
			 JOIN CTE C ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
			 AND C.RS_CONTRACT_SID = A.RS_CONTRACT_SID

                                 ')


--Pulling CCP+D information,Calcuation Periods information based on selected to period Ends here

            EXEC sp_executesql @SQL

--calculating Growth based on CCP+D and Methodolgy selected Starts here 
            SET @SQL=CONCAT(' ;

      WITH GROWTH_PERCENT
           AS (SELECT NSP.CCP_DETAILS_SID,
                      NSP.RS_MODEL_SID,
                      MIN(GROWTH) GROWTH,
                      MIN(P.PERIOD_SID) PERIOD_SID,
					  ROLL_PERIOD ,
					  NSP.RS_CONTRACT_SID,
					  PERIOD_YEAR
               FROM   ', @PROJECTION_TABLE, ' NSP
                      INNER JOIN ',@MASTER_TABLE,' TC
                              ON NSP.CCP_DETAILS_SID = TC.CCP_DETAILS_SID
							     AND NSP.RS_MODEL_SID=TC.RS_MODEL_SID
                     AND  nsp.PERIOD_SID between TC.FREQ_CAL_START_PERIOD_SID and TC.FREQ_CAL_END_PERIOD_SID
						JOIN #CCP_ELIGIBLE1 CE
					    ON CE.CCP_DETAILS_SID=NSP.CCP_DETAILS_SID 
						AND CE.RS_CONTRACT_SID  = NSP.RS_CONTRACT_SID
							JOIN #PERIOD P ON P.PERIOD_SID = NSP.PERIOD_SID
							GROUP BY NSP.CCP_DETAILS_SID,NSP.RS_MODEL_SID,P.PERIOD_YEAR,P.PERIOD,NSP.RS_CONTRACT_SID,ROLL_PERIOD,PERIOD_YEAR

						 )
SELECT GP.CCP_DETAILS_SID,GP.RS_MODEL_SID,GP.PERIOD_SID,(1+ISNULL(GP.GROWTH/100.00,0)) AS GROWTH,
',CASE WHEN @METHODOLOGY='ROLLING ANNUAL TREND' THEN 'Gp.ROLL_PERIOD' ELSE '0' END,' AS ROLLPERIOD,RS_CONTRACT_SID
FROM GROWTH_PERCENT GP '
,CASE WHEN (@METHODOLOGY='SINGLE PERIOD' OR @METHODOLOGY='AVERAGE') THEN ' ORDER BY GP.CCP_DETAILS_SID,RS_MODEL_SID,RS_CONTRACT_SID,GP.PERIOD_SID' 
						  WHEN @METHODOLOGY='ROLLING ANNUAL TREND' THEN 'ORDER BY GP.CCP_DETAILS_SID,RS_MODEL_SID,RS_CONTRACT_SID,ROLL_PERIOD,PERIOD_YEAR' END
)

--calculating Growth based on CCP+D and Methodolgy selected Ends here 

            EXEC sp_executesql @SQL

--Droping the existing and recreating the discount growth table and inserting into temp table Starts here
 DECLARE @DISC_GROWTH_TABLE VARCHAR(100)

SET @DISC_GROWTH_TABLE= CONCAT('ST_DISC_GROWTH_FACTOR_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), @TABLE_DATE, 2), '.', ''))

SET @SQL= CONCAT('
            IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''',@DISC_GROWTH_TABLE,'''
                      AND TABLE_SCHEMA = ''DBO'')
					  BEGIN
			   DROP TABLE ',@DISC_GROWTH_TABLE,' 
       END
           CREATE TABLE ',@DISC_GROWTH_TABLE,'
                   (
				   CCP_DETAILS_SID     INT,
				   RS_MODEL_SID        INT,
				   PERIOD_SID          INT,
				   GROWTH              NUMERIC(22,15),
				   RS_CONTRACT_SID     INT

				   )'

)

EXEC sp_executesql @SQL
--Droping the existing and recreating the discount growth table and inserting into temp table Ends here
  END
--Growth calculation of discount projection(screen name) Ends here
ELSE IF @SCREEN_NAME='RETURNS'
BEGIN
--Growth calculation of Returns item level (screen name) Starts here
DECLARE @RETURNS_MASTER_TABLE     VARCHAR(100)=CONCAT('ST_RETURNS_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
DECLARE @RETURNS_PROJECTION_TABLE VARCHAR(100)=CONCAT('ST_RETURNS_PROJ_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
        @RETURN_METHODOLOGY       VARCHAR(100)

--taking methodolgy and periods combination for the particular projection Starts here

		SET @SQL=Concat(' SELECT  @MIN_PERIOD = MIN(P.PERIOD_SID),
							   @MAX_PERIOD = MAX(P1.PERIOD_SID),
							   @RETURN_METHODOLOGY=MIN(TM.METHODOLOGY)
				  FROM   ', @RETURNS_MASTER_TABLE, '  TM
						 JOIN #PERIOD P
						   ON P.PERIOD = METHODOLOGY_START_DATE
						 LEFT JOIN #PERIOD P1
						   ON P1.PERIOD = METHODOLOGY_END_DATE WHERE TM.CHECK_RECORD=1')

		EXEC Sp_executesql
		  @SQL,
		  N'@MIN_PERIOD INT OUTPUT,@MAX_PERIOD INT OUTPUT, @RETURN_METHODOLOGY VARCHAR(100) OUTPUT',
		  @MIN_PERIOD OUTPUT,
		  @MAX_PERIOD OUTPUT,
		  @RETURN_METHODOLOGY OUTPUT

	
SET @MAX_PERIOD=IIF(@PROJ_END_PERIOD_SID>@MAX_PERIOD,@MAX_PERIOD,@PROJ_END_PERIOD_SID)

--taking methodolgy and periods combination for the particular projection Ends here

--calculating Growth based on item period wise calculation Starts here
SET @SQL=CONCAT('SELECT M.RETURNS_DETAILS_SID,MIN(P.PERIOD_SID) PERIOD_SID,MIN((1+ISNULL(GROWTH_RATE/100,0))) as GROWTH,
                  ',CASE WHEN @RETURN_METHODOLOGY='ROLLING ANNUAL TREND' THEN 'P1.ROLL_PERIOD' ELSE '0' END,' AS ROLL_PERIOD 

 FROM ',
						@RETURNS_MASTER_TABLE,' M JOIN 
						',@RETURNS_PROJECTION_TABLE,' P 
						ON M.RETURNS_DETAILS_SID=P.RETURNS_DETAILS_SID
						  JOIN #PERIOD P1 ON P.PERIOD_SID= P1.PERIOD_SID
						AND M.CHECK_RECORD=1 AND P.PERIOD_SID BETWEEN ',@MIN_PERIOD,' AND ',@MAX_PERIOD
						,' GROUP BY M.RETURNS_DETAILS_SID HAVING MIN(GROWTH_RATE)>0
						
						ORDER BY  M.RETURNS_DETAILS_SID, ', CASE WHEN @RETURN_METHODOLOGY='ROLLING ANNUAL TREND' THEN 'P1.ROLL_PERIOD,PERIOD_YEAR' ELSE 
						'PERIOD_SID' END
						 ,'
						'
						)
--calculating Growth based on item period wise calculation Ends here

EXEC sp_executesql @SQL

--Droping the existing and recreating the Returns growth table and inserting into temp table Starts here

 DECLARE @RETURNS_GROWTH_TABLE VARCHAR(100)

SET @RETURNS_GROWTH_TABLE= CONCAT('ST_RETURNS_GROWTH_FACTOR_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), @TABLE_DATE, 2), '.', ''))

SET @SQL= CONCAT('
            IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''',@RETURNS_GROWTH_TABLE,''' 
                      AND TABLE_SCHEMA = ''DBO'')
					  BEGIN
			   DROP TABLE ',@RETURNS_GROWTH_TABLE,' 
       END
           CREATE TABLE ',@RETURNS_GROWTH_TABLE,'
                   (
				   RETURNS_DETAILS_SID     INT,
				   PERIOD_SID          INT,
				   GROWTH              NUMERIC(22,15)

				   )'

)


EXEC sp_executesql @SQL
--Droping the existing and recreating the Returns growth table and inserting into temp table Ends here
END
--Growth calculation of Returns item level (screen name) Ends here

--Growth calculation of national assumptions (screen name) Starts here
else if @SCREEN_NAME='national assumptions'
begin 
       declare  @BUSINESS_PROCESS_TYPE VARCHAR(50) ='NATIONAL ASSUMPTIONS',
	           @PROJ_PERIOD_START_SID INT,
        @PROJ_PERIOD_END_SID   INT,
        @ACT_PERIOD_START_SID  INT,
        @ACT_PERIOD_END_SID    INT,
		@ACTUAL_START_DATE     DATETIME,
        @ACTUAL_END_DATE       DATETIME,
        @PROJECTION_START_DATE DATETIME,
        @PROJECTION_END_DATE   DATETIME,
		@NATIONAL_ASSUMPTIONS         VARCHAR(200) = Concat('ST_NATIONAL_ASSUMPTIONS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
       
	  SELECT @ACTUAL_START_DATE = ACTUAL_START_DATE,
              @ACTUAL_END_DATE = ACTUAL_END_DATE,
              @PROJECTION_START_DATE = PROJECTION_START_DATE,
              @PROJECTION_END_DATE = PROJECTION_END_DATE
      FROM   [DBO].[Udf_na_proj_dates](@business_process_type)

	

      IF Object_id('TEMPDB..#PERIOD_QUARTER') IS NOT NULL -- TO STORE THE PERIOD INFORMATION IN QUARTER BASIS
        TRUNCATE TABLE #PERIOD_QUARTER
      ELSE
        CREATE TABLE #PERIOD_QUARTER
          (
             PERIOD_SID     INT PRIMARY KEY,
             PERIOD_YEAR    INT,
             PERIOD_QUARTER INT,
             SEMI_ANNUAL    INT,
             Q_PERIOD       VARCHAR(10),
             PERIOD_DATE    DATETIME
          )
		        IF Object_id('TEMPDB..#EFFECTIVE_PERIOD') IS NOT NULL -- TO INSERT THE BASELINE VALUES FOR EACH ITEM'S SELECTED IN 
        TRUNCATE TABLE #EFFECTIVE_PERIOD
      ELSE
        CREATE TABLE #EFFECTIVE_PERIOD
          (
             ndc            varchar(50),
             EFFECTIVE_START_PERIOD_SID INT,
             EFFECTIVE_END_PERIOD_SID   INT,PRICE_TYPE varchar(50)
          )

	  INSERT INTO #PERIOD_QUARTER
                  (PERIOD_SID,
                   PERIOD_YEAR,
                   PERIOD_QUARTER,
                   SEMI_ANNUAL,
                   Q_PERIOD,
                   PERIOD_DATE)
      SELECT Min(PERIOD_SID) AS PERIOD_SID, 
             [YEAR],
             [QUARTER],
             SEMI_ANNUAL,
             'Q' + CONVERT(VARCHAR(2), [QUARTER]) + ' '
             + CONVERT(VARCHAR(5), [YEAR]),
             Min(PERIOD_DATE) AS PERIOD_DATE
      FROM   PERIOD
      GROUP  BY [YEAR],
                [QUARTER],
                SEMI_ANNUAL

    SELECT @ACT_PERIOD_START_SID = Max(CASE
                                           WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_START_DATE), 0) THEN PERIOD_SID
                                           END),
              @ACT_PERIOD_END_SID = Max(CASE
                                         WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_END_DATE), 0) THEN PERIOD_SID
                                       END),
              @PROJ_PERIOD_START_SID = Max(CASE
                                            WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_START_DATE), 0) THEN PERIOD_SID
                                          END),
              @PROJ_PERIOD_END_SID = Max(CASE
                                          WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_END_DATE), 0) THEN PERIOD_SID
                                        END)
      FROM   (SELECT PERIOD_SID,PERIOD_DATE
              FROM   #PERIOD_QUARTER
              WHERE  PERIOD_DATE IN ( Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_START_DATE), 0), Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_END_DATE), 0), Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_START_DATE), 0), Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_END_DATE), 0) )) A


IF Object_id('TEMPDB..#NATIONAL_ASSUMPTIONS') IS NOT NULL
DROP TABLE #NATIONAL_ASSUMPTIONS

CREATE TABLE #NATIONAL_ASSUMPTIONS
(
NA_PROJ_MASTER_SID INT,
PRICE_TYPE VARCHAR(100),
BASELINE_METHODOLOGY VARCHAR(100),
FORECAST_METHODOLOGY VARCHAR(100),
BASELINE_PERIOD      varchar(500),
ROLLING_PERIOD       varchar(500),
START_PERIOD         varchar(100),
END_PERIOD           varchar(100),
GROWTH_RATE          NUMERIC(22,6), 
FREQUENCY            VARCHAR(100),
PRICE_BASIS          VARCHAR(100),
CPI_COMPOUNDING      VARCHAR(100),
LAST_MODIFIED_DATE   DATETIME 
)

DECLARE @NA NVARCHAR(MAX) = ''
SET @NA=CONCAT('INSERT INTO #NATIONAL_ASSUMPTIONS

(
         NA_PROJ_MASTER_SID,
         PRICE_TYPE,
         BASELINE_METHODOLOGY,
         FORECAST_METHODOLOGY,
         BASELINE_PERIOD,
         ROLLING_PERIOD,
         START_PERIOD,
         END_PERIOD,
         GROWTH_RATE,
         FREQUENCY,
         PRICE_BASIS,
		 CPI_COMPOUNDING,
		 LAST_MODIFIED_DATE
)

SELECT   NA_PROJ_MASTER_SID,
         PRICE_TYPE,
         BASELINE_METHODOLOGY,
         FORECAST_METHODOLOGY,
         BASELINE_PERIOD,
         ROLLING_PERIOD,
         START_PERIOD,
         END_PERIOD,
         GROWTH_RATE,
         FREQUENCY,
         PRICE_BASIS,
		 CPI_COMPOUNDING,
		 LAST_MODIFIED_DATE
FROM     ',@NATIONAL_ASSUMPTIONS,'')

--select @NA
EXEC SP_EXECUTESQL @NA

declare @UN_UOM_SID int


SELECT @UN_UOM_SID = HELPER_TABLE_SID -- UOM 'UN' HELPER_TABLE_SID HERE
                             FROM    HELPER_TABLE
                             WHERE  LIST_NAME = 'UOM' AND DESCRIPTION = 'UN'


	;WITH CTE
                 AS (SELECT case when @LEVEL = 'ndc9' then ndc9 else cast(im.ITEM_MASTER_SID as varchar(50)) end as ndc,
				  CASE
                                                      WHEN PRICING_QUALIFIER = 'BP' THEN 'BEST PRICE'
                                                      WHEN PRICING_QUALIFIER = 'QFSS' THEN 'FSS(OGA)'
                                                      WHEN PRICING_QUALIFIER = 'QNON-FAMP' THEN 'NON-FAMP'
                                                      ELSE PRICING_QUALIFIER
                                                    END as price_type,
                            Max(PERIOD_DATE) AS LAST_DATE
                     FROM   (SELECT IP.ITEM_MASTER_SID,
                                    IP.ITEM_PRICE,
                                    IPQ.PRICING_QUALIFIER,
                                    START_DATE,
                                     Isnull(END_DATE, Lead(Dateadd(DD, -1, START_DATE), 1, NULL)
                                                                  OVER(
                                                                    PARTITION BY IP.ITEM_MASTER_SID
                                                                    ORDER BY [START_DATE])) AS END_DATE
                             FROM   DBO.ITEM_PRICING IP
                                    JOIN DBO.ITEM_PRICING_QUALIFIER IPQ
                                      ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                             WHERE  IPQ.PRICING_QUALIFIER in ('BP','QFSS','QNON-FAMP','AMP')
                                    AND IP.ITEM_UOM = @UN_UOM_SID
                                    AND EXISTS (SELECT 1
                                                FROM   NA_PROJ_DETAILS ID
                                                WHERE  ID.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
												and NA_PROJ_MASTER_SID = @PROJECTION)
                                    AND IP.INBOUND_STATUS <> 'D') A
									join item_master im on im.item_master_sid = a.item_master_sid
                            JOIN PERIOD P
                              ON A.START_DATE <= P.PERIOD_DATE
                                 AND ( A.END_DATE >= P.PERIOD_DATE
                                        OR A.END_DATE IS NULL )

                     WHERE  PERIOD_SID BETWEEN @ACT_PERIOD_START_SID AND @PROJ_PERIOD_END_SID
                     GROUP  BY case when @LEVEL = 'ndc9' then ndc9 else cast(im.ITEM_MASTER_SID as varchar(50)) end,CASE
                                                      WHEN PRICING_QUALIFIER = 'BP' THEN 'BEST PRICE'
                                                      WHEN PRICING_QUALIFIER = 'QFSS' THEN 'FSS(OGA)'
                                                      WHEN PRICING_QUALIFIER = 'QNON-FAMP' THEN 'NON-FAMP'
                                                      ELSE PRICING_QUALIFIER
                                                    END)
			 INSERT INTO #EFFECTIVE_PERIOD
                        (ndc,
                         EFFECTIVE_START_PERIOD_SID,
                         EFFECTIVE_END_PERIOD_SID,
						 price_type)
			SELECT C.ndc,
				   (CASE
					 WHEN PQ.PERIOD_SID + 3 > START_PERIOD_SID THEN PQ.PERIOD_SID + 3
					 ELSE START_PERIOD_SID
				   END)-3 AS START_PERIOD_SID,
				   END_PERIOD_SID,
				   c.price_type
			FROM   CTE C
				   INNER JOIN #PERIOD_QUARTER PQ
						   ON Datepart(QQ, LAST_DATE) = PQ.PERIOD_QUARTER
							  AND Year(LAST_DATE) = PQ.PERIOD_YEAR
				   INNER JOIN (SELECT START_PERIOD_SID = Min(PERIOD_SID),
									  END_PERIOD_SID = Max(PERIOD_SID),
									  price_type
							   FROM   #PERIOD_QUARTER
									  JOIN #NATIONAL_ASSUMPTIONS na
										ON Q_PERIOD = START_PERIOD
											OR Q_PERIOD = END_PERIOD
							   GROUP  BY price_type) na
						   ON na.PRICE_TYPE = c.PRICE_TYPE 



	declare 	@NA_NDC9_WAC                  VARCHAR(100) =Concat('ST_NA_NDC9_WAC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
		@NA_NDC11_WAC                 VARCHAR(100) =Concat('ST_NA_NDC11_WAC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')) 

--based on level selected calculation BQWAC,EQWAC,MQWAC,AVGQWAC Starts here
if @level = 'ndc9'
BEGIN
SET @SQL='SELECT n.NDC9 as ndc,
       n.PERIOD_SID,
       BQWAC = 1 + isnull(((BQWAC - lag(BQWAC) over(partition by NDC9,PRICE_TYPE order by PERIOD_SID))/nullif(lag(BQWAC) over(partition by NDC9,PRICE_TYPE order by PERIOD_SID),0)),0) ,
       EQWAC = 1 + isnull( ((EQWAC - lag(EQWAC) over(partition by NDC9,PRICE_TYPE order by PERIOD_SID))/nullif(lag(EQWAC) over(partition by NDC9,PRICE_TYPE order by PERIOD_SID),0)),0) ,
       MQWAC = 1 +  isnull(((MQWAC - lag(MQWAC) over(partition by NDC9,PRICE_TYPE order by PERIOD_SID))/nullif(lag(MQWAC) over(partition by NDC9,PRICE_TYPE order by PERIOD_SID),0)),0) ,
       AVGQWAC =1 +  isnull(((AVGQWAC - lag(AVGQWAC) over(partition by NDC9,PRICE_TYPE order by PERIOD_SID))/nullif(lag(AVGQWAC) over(partition by NDC9,PRICE_TYPE order by PERIOD_SID),0)),0),
       DAY_WEIGHTED_WAC =1 +  isnull(((DAY_WEIGHTED_WAC - lag(DAY_WEIGHTED_WAC) over(partition by NDC9,PRICE_TYPE order by PERIOD_SID))/nullif(lag(DAY_WEIGHTED_WAC) over(partition by NDC9,PRICE_TYPE order by PERIOD_SID),0)) ,0),
       SALES_WEIGHTED_WAC =1 +  isnull(((SALES_WEIGHTED_WAC - lag(SALES_WEIGHTED_WAC) over(partition by NDC9,PRICE_TYPE order by PERIOD_SID))/nullif(lag(SALES_WEIGHTED_WAC) over(partition by NDC9,PRICE_TYPE order by PERIOD_SID),0)) ,0),
	   PRICE_TYPE
FROM   '+@NA_NDC9_WAC  +' n join #EFFECTIVE_PERIOD e on e.ndc = n.ndc9 
and n.period_sid between e.EFFECTIVE_START_PERIOD_SID and e.EFFECTIVE_END_PERIOD_SID
order by ndc9,PRICE_TYPE,period_sid
'


EXEC sp_executesql @SQL
END
else
BEGIN
SET @SQL='SELECT ITEM_MASTER_sid ndc,
       n.PERIOD_SID,
 BQWAC = 1 + isnull(((BQWAC - lag(BQWAC) over(partition by item_master_sid,PRICE_TYPE order by PERIOD_SID))/nullif(lag(BQWAC) over(partition by item_master_sid,PRICE_TYPE order by PERIOD_SID),0)),0) ,
       EQWAC = 1 + isnull( ((EQWAC - lag(EQWAC) over(partition by item_master_sid,PRICE_TYPE order by PERIOD_SID))/nullif(lag(EQWAC) over(partition by item_master_sid,PRICE_TYPE order by PERIOD_SID),0)),0) ,
       MQWAC = 1 +  isnull(((MQWAC - lag(MQWAC) over(partition by item_master_sid,PRICE_TYPE order by PERIOD_SID))/nullif(lag(MQWAC) over(partition by item_master_sid,PRICE_TYPE order by PERIOD_SID),0)),0) ,
       AVGQWAC =1 +  isnull(((AVGQWAC - lag(AVGQWAC) over(partition by item_master_sid,PRICE_TYPE order by PERIOD_SID))/nullif(lag(AVGQWAC) over(partition by item_master_sid,PRICE_TYPE order by PERIOD_SID),0)),0),
       DAY_WEIGHTED_WAC =1 +  isnull(((DAY_WEIGHTED_WAC - lag(DAY_WEIGHTED_WAC) over(partition by item_master_sid,PRICE_TYPE order by PERIOD_SID))/nullif(lag(DAY_WEIGHTED_WAC) over(partition by item_master_sid,PRICE_TYPE order by PERIOD_SID),0)) ,0),
       SALES_WEIGHTED_WAC =1 +  isnull(((SALES_WEIGHTED_WAC - lag(SALES_WEIGHTED_WAC) over(partition by item_master_sid,PRICE_TYPE order by PERIOD_SID))/nullif(lag(SALES_WEIGHTED_WAC) over(partition by item_master_sid,PRICE_TYPE order by PERIOD_SID),0)) ,0),
	   PRICE_TYPE
FROM   '+@NA_NDC11_WAC +' n join #EFFECTIVE_PERIOD e on e.ndc = n.item_master_sid and n.period_sid 
between e.EFFECTIVE_START_PERIOD_SID and e.EFFECTIVE_END_PERIOD_SID
order by ITEM_MASTER_sid,PRICE_TYPE,period_sid
'
EXEC sp_executesql @SQL
END
--based on level selected calculation BQWAC,EQWAC,MQWAC,AVGQWAC Ends here

--Droping the existing and recreating the national assumptions growth table and inserting into temp table Starts here based on level selected

declare  @NDC9_GROWTH_TABLE varchar(100)= CONCAT('ST_NA_NDC9_GROWTH_FACTOR_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), @TABLE_DATE, 2), '.', ''))
, @NDC11_GROWTH_TABLE  varchar(100)= CONCAT('ST_NA_NDC11_GROWTH_FACTOR_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), @TABLE_DATE, 2), '.', ''))

if @level = 'ndc9'
BEGIN
SET @SQL= CONCAT('
            IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''',@NDC9_GROWTH_TABLE,''' 
                      AND TABLE_SCHEMA = ''DBO'')
					  BEGIN
			   DROP TABLE ',@NDC9_GROWTH_TABLE,' 
       END
           CREATE TABLE ',@NDC9_GROWTH_TABLE,'
                   (
NDC9	varchar(50),
PERIOD_SID	int,
BQWAC_GROWTH	numeric(38,15),
EQWAC_GROWTH	numeric(38,15),
MQWAC_GROWTH	numeric(38,15),
AVGQWAC_GROWTH	numeric(38,15),
DAY_WEIGHTED_WAC_GROWTH	numeric(38,15),
SALES_WEIGHTED_WAC_GROWTH	numeric(38,15),
PRICE_TYPE VARCHAR(50)
)')
EXEC sp_executesql @SQL



END
ELSE
BEGIN 

 SET @SQL= CONCAT('IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''',@NDC11_GROWTH_TABLE,''' 
                      AND TABLE_SCHEMA = ''DBO'')
					  BEGIN
			   DROP TABLE ',@NDC11_GROWTH_TABLE,' 
       END
           CREATE TABLE ',@NDC11_GROWTH_TABLE,'
                   (
ITEM_MASTER_SID	int                        ,
PERIOD_SID	int							   ,
BQWAC_GROWTH	numeric(38,15)			   ,
EQWAC_GROWTH	numeric(38,15)			   ,
MQWAC_GROWTH	numeric(38,15)			   ,
AVGQWAC_GROWTH	numeric(38,15)			   ,
DAY_WEIGHTED_WAC_GROWTH	numeric(38,15)	   ,
SALES_WEIGHTED_WAC_GROWTH	NUMERIC(38,15),
PRICE_TYPE VARCHAR(50)
)'
)

EXEC sp_executesql @SQL




END
--DROPING THE EXISTING AND RECREATING THE NATIONAL ASSUMPTIONS GROWTH TABLE AND INSERTING INTO TEMP TABLE ENDS HERE BASED ON LEVEL SELECTED


END

--Growth calculation of national assumptions (screen name) Ends here
END
GO
