IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_CONTRACT_DETAILS_REBATE'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_CONTRACT_DETAILS_REBATE]
  END

GO


CREATE  PROCEDURE [dbo].[PRC_CONTRACT_DETAILS_REBATE](@PROJECTION_MASTER_SID INT,
                                                    @USER_ID               INT,
                                                    @SESSION_ID            VARCHAR(50))
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY

DECLARE @DISC_MASTER_TABLE   VARCHAR(200) = CONCAT('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
						     @REBATE_INFO VARCHAR(200) = CONCAT('ST_NM_DISC_REBATE_INFO_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
                             @TIER_INFO VARCHAR(200) = CONCAT('ST_NM_DISC_TIER_INFO_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
                             @RETURNS_INFO  VARCHAR(200) = CONCAT('ST_RETURNS_CONTRACT_INFO_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
							 @SQL NVARCHAR(MAX),
                             @NATIONAL_INFO VARCHAR(200) = CONCAT('ST_NATIONAL_CONTRACT_INFO_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
							 @QUALIFIER   VARCHAR(MAX),
							 @PROJ_END_PERIOD_SID INT,
                             @PROJECTION_START_PERIOD_SID  INT,
                             @ACTUAL_START_PERIOD_SID INT,
                             @ITEM_UOM    VARCHAR(50) ='EACH'-- 'UN' TO 'EACH' GALUAT-46

        SELECT @ACTUAL_START_PERIOD_SID = ACTUAL_START_SID,
                  @PROJECTION_START_PERIOD_SID = PROJECTION_START_SID,
                  @PROJ_END_PERIOD_SID = PROJECTION_END_SID
          FROM   [DBO].[UDF_PROJECTION_DATES](@PROJECTION_MASTER_SID, 'A')
		         DECLARE @CURRENT_QUARTER INT =(SELECT MIN(PERIOD_SID) FROM PERIOD WHERE PERIOD_DATE= DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0) )------------cel-394


                     IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
                           DROP TABLE #PERIOD;

                                            CREATE TABLE #PERIOD
                                            (
                                            PERIOD_SID INT,
                                            PERIOD_DATE DATE,
                                            PERIOD_MONTH INT,
                                            PERIOD_QUARTER INT,
                                            PERIOD_SEMI INT,
                                            PERIOD_YEAR INT,
                                           
                                            PERIOD_QUAR  VARCHAR(10),
                                            PERIOD_SEMIF  VARCHAR(10),
                                            PERIOD_YEARF  VARCHAR(10)
                                            PRIMARY KEY (PERIOD_SID,PERIOD_DATE,PERIOD_MONTH,PERIOD_QUARTER,PERIOD_SEMI,PERIOD_YEAR)
                                            )
                                         INSERT INTO  #PERIOD
                                         (
                                         PERIOD_SID,
                                         PERIOD_DATE,
                                         PERIOD_MONTH,
                                         PERIOD_QUARTER,
                                         PERIOD_SEMI,
                                         PERIOD_YEAR,
                                  
                                         PERIOD_QUAR, 
                                         PERIOD_SEMIF,
                                         PERIOD_YEARF
                                         )
                       SELECT     PERIOD_SID,
                                  PERIOD_DATE,
                                  MONTH AS PERIOD_MONTH,
                                  QUARTER AS PERIOD_QUARTER, 
                                  SEMI_ANNUAL aS PERIOD_SEMI,
                                  YEAR AS PERIOD_YEAR,
                                 
                                  Concat ('Q', QUARTER, ' ', YEAR) AS PERIOD_QUAR,
                                  Concat ('S', QUARTER, ' ', YEAR) AS PERIOD_SEMIF,
                                  Cast(YEAR AS CHAR(4)) AS PERIOD_YEARF
                       FROM   PERIOD



					   
  DECLARE @BUSINESS_UNIT INT,@COMPANY_ID INT

   SELECT @BUSINESS_UNIT = BUSINESS_UNIT,
             @COMPANY_ID = NA.COMPANY_MASTER_SID
      FROM   PROJECTION_MASTER NA
      WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID


   DECLARE @FORECAST_NAME    VARCHAR(50),
              @FORECAST_VERSION VARCHAR(15)

      SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME,
                   @FORECAST_VERSION = FT.[VERSION]
      FROM   FILE_MANAGEMENT FT
             INNER JOIN HELPER_TABLE HT
                     ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
      WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
               AND FT.FROM_PERIOD IS NOT NULL )
             AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                    OR FT.TO_PERIOD IS NULL )
             AND HT.LIST_NAME = 'FILE_TYPE'
             AND HT.DESCRIPTION = 'EX-FACTORY SALES'
             AND FT.BUSINESS_UNIT = @BUSINESS_UNIT --------------------GAL-808
             AND FT.COMPANY = @COMPANY_ID --------------------GAL-808
      ORDER  BY FT.FROM_PERIOD DESC

IF Object_id('TEMPDB..#GTS') IS NOT NULL
  DROP TABLE #GTS

CREATE TABLE #GTS
  (
     ITEM_MASTER_SID          INT,
     QUARTER                  INT,
     YEAR                     INT,
	 PERIOD_SID               INT,
     SALES_WEIGHTEDWAC_NORMAL NUMERIC(22, 6),
     SALES_WEIGHTEDWAC_UPPS   NUMERIC(22, 6)
  )

          IF OBJECT_ID('TEMPDB..#RS_INFO') IS NOT NULL
            DROP TABLE #RS_INFO

          CREATE TABLE #RS_INFO
            (
               CCP_DETAILS_SID     INT,
               RS_CONTRACT_SID     INT,
               RS_MODEL_SID        INT,
               CONTRACT_MASTER_SID INT,
               COMPANY_MASTER_SID  INT,
               ITEM_MASTER_SID     INT,
            )

          SET @SQL=CONCAT('INSERT INTO #RS_INFO
   (
   CCP_DETAILS_SID,
   CONTRACT_MASTER_SID,
   COMPANY_MASTER_SID,
   ITEM_MASTER_SID,
   RS_CONTRACT_SID,
   RS_MODEL_SID   
   )
   
   SELECT 
   s.CCP_DETAILS_SID, 
   CONTRACT_MASTER_SID,
   COMPANY_MASTER_SID,
   ITEM_MASTER_SID,
   RS_CONTRACT_SID,
   RS_MODEL_SID   
                        
                FROM  ', @DISC_MASTER_TABLE, ' S

                JOIN CCP_DETAILS CCP ON S.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID')


          EXEC sp_executesql @SQL
       
SET @SQL=CONCAT('
IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''',@REBATE_INFO,''' 
                      AND TABLE_SCHEMA = ''DBO'')
                                    BEGIN
                        DROP TABLE ',@REBATE_INFO,' 
       END
           
 CREATE TABLE ',@REBATE_INFO,'
  (
     CCP_DETAILS_SID                 INT,
     RS_MODEL_SID                    INT,
     RS_CONTRACT_SID                 INT,
     ITEM_MASTER_SID                 INT,
     DEDUCTION_CALENDAR_MASTER_SID   INT,
     BUNDLE_NO                       INT,
     REBATE_PLAN_MASTER_SID          INT,
     TIER_OPERATOR                   CHAR(1),
     REBATE_STRUCTURE                VARCHAR(50),
     REBATE_BASED_ON                 VARCHAR(50),
     REBATE_RANGE_BASED_ON           VARCHAR(50),
     CALCULATION_TYPE                VARCHAR(50),
     CALCULATION_LEVEL               VARCHAR(50),
     REBATE_FREQUENCY                VARCHAR(50),
     RP_NET_SALES_FORMULA_MASTER_SID INT,
     RS_NET_SALES_FORMULA_MASTER_SID INT,
     CALCULATION_RULE                INT,
     EVALUATION_RULE                 INT,
       ITEM_PRICING_QUALIFIER_SID   VARCHAR(1000),
       RETURN_RATE_SID  int ,
       PERIOD_REBATE    VARCHAR(100),
       REBATE_GROUP     VARCHAR(150)
  ) ')



EXEC sp_executesql @SQL


SET @SQL=CONCAT('
IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''',@TIER_INFO,''' 
                      AND TABLE_SCHEMA = ''DBO'')
                                    BEGIN
                        DROP TABLE ',@TIER_INFO,' 
       END


CREATE TABLE ',@TIER_INFO,'
  (
     CCP_DETAILS_SID             INT,
     RS_MODEL_SID                INT,
     ITEM_MASTER_SID             INT,
     TIER_FROM                   NUMERIC(22, 6),
     TIER_TO                     NUMERIC(22, 6),
     DISCOUNT_RATE               NUMERIC(22, 6),
     REBATE_PER_UNIT             NUMERIC(22, 6),
     FLAT_DISCOUNT               NUMERIC(22, 6),
     ITEM_PRICING_QUALIFIER_SID  VARCHAR(1000),
     RETURN_RATE_SID             INT,
     ITEM_PRICING_QUALIFIER_NAME VARCHAR(50)
       
  ) ')

EXEC sp_executesql @SQL   

SET @SQL=CONCAT('INSERT INTO ',@REBATE_INFO,'
          (
                CCP_DETAILS_SID,
                 RS_MODEL_SID                     ,
                 RS_CONTRACT_SID                  ,
                 ITEM_MASTER_SID                  ,
                 DEDUCTION_CALENDAR_MASTER_SID    ,
                 BUNDLE_NO                        ,
                 REBATE_PLAN_MASTER_SID           ,
                 TIER_OPERATOR                    ,
                 REBATE_STRUCTURE                 ,
                 REBATE_BASED_ON                  ,
                 REBATE_RANGE_BASED_ON            ,
                 CALCULATION_TYPE                 ,
                 CALCULATION_LEVEL                ,
                 REBATE_FREQUENCY                 ,
                 RP_NET_SALES_FORMULA_MASTER_SID  ,
                 RS_NET_SALES_FORMULA_MASTER_SID  ,
                 CALCULATION_RULE                 ,
                 EVALUATION_RULE,
                 ITEM_PRICING_QUALIFIER_SID,
                 RETURN_RATE_SID,
                 REBATE_GROUP        
                )

                SELECT CCP_DETAILS_SID,
						RS_MODEL_SID,
						RS_CONTRACT_SID,
						ITEM_MASTER_SID,
						DEDUCTION_CALENDAR_MASTER_SID,
						BUNDLE_NO,
						REBATE_PLAN_MASTER_SID,
						TIER_OPERATOR,
						REBATE_STRUCTURE,
						REBATE_BASED_ON,
						REBATE_RANGE_BASED_ON,
						CALCULATION_TYPE,
						CALCULATION_LEVEL,
						REBATE_FREQUENCY,
						RP_NET_SALES_FORMULA_MASTER_SID,
						RS_NET_SALES_FORMULA_MASTER_SID,
                        CALCULATION_RULE,
                        EVALUATION_RULE,
                        ITEM_PRICING_QUALIFIER_SID  ,
                        RETURN_RATE_SID ,
                             
                           

               CASE
                                                        WHEN CALCULATION_LEVEL LIKE ''%SINGLE%'' THEN
                                                         
                                                     concat(  RS_MODEL_SID,'','',COMPANY_MASTER_SID)
                                                         
                                                        WHEN CALCULATION_LEVEL LIKE ''%LINE%'' THEN
                                                         
                                                           concat( RS_MODEL_SID,'','', CCP_DETAILS_SID)
                                                             
                                                          
                                                        WHEN CALCULATION_LEVEL LIKE ''%BUNDLE%'' THEN
                                                         
                                                         
                                                           concat(  COMPANY_MASTER_SID,'','', RS_MODEL_SID,'','', BUNDLE_NO)
                                                         
                                                      END

                 FROM 
                (
SELECT   
 
 
 ROW_NUMBER()
                                    OVER (
                                      PARTITION BY DPM.CCP_DETAILS_SID, DPM.RS_MODEL_SID
                                      ORDER BY DPM.CCP_DETAILS_SID)RN,
                DPM.CCP_DETAILS_SID ,
                 DPM.RS_MODEL_SID,
                 DPM.RS_CONTRACT_SID,
                 DPM.ITEM_MASTER_SID,
                           DPM.COMPANY_MASTER_SID,
                 DEDUCTION_CALENDAR_MASTER_SID,
                 RS.BUNDLE_NO,
                 RS.REBATE_PLAN_MASTER_SID,
                 HTO.DESCRIPTION                  AS TIER_OPERATOR,
                 HRS.DESCRIPTION                  AS REBATE_STRUCTURE,
                 HRB.DESCRIPTION                  AS REBATE_BASED_ON,
                 HRR.DESCRIPTION                  AS REBATE_RANGE_BASED_ON,
                 HT.[DESCRIPTION]                 AS CALCULATION_TYPE,
                 HL.[DESCRIPTION]                 AS CALCULATION_LEVEL,
                 HF.[DESCRIPTION]                 AS REBATE_FREQUENCY,
                 RPM.NET_SALES_FORMULA_MASTER_SID AS RP_NET_SALES_FORMULA_MASTER_SID,
                 RS.NET_SALES_FORMULA_MASTER_SID  AS RS_NET_SALES_FORMULA_MASTER_SID,
                 RS.CALCULATION_RULE,
                 RS.EVALUATION_RULE,
                             ITEM_PRICING_QUALIFIER_SID  ,
                             RETURN_RATE_SID        
          FROM   #RS_INFO DPM
                 INNER JOIN RS_CONTRACT RC
                         ON RC.RS_CONTRACT_SID = DPM.RS_CONTRACT_SID
                            AND RC.CONTRACT_MASTER_SID = DPM.CONTRACT_MASTER_SID
                 INNER JOIN RS_CONTRACT_DETAILS RS
                         ON RS.RS_CONTRACT_SID = RC.RS_CONTRACT_SID
                            AND RS.ITEM_MASTER_SID = DPM.ITEM_MASTER_SID
                 LEFT JOIN HELPER_TABLE HT
                        ON HT.HELPER_TABLE_SID = RC.CALCULATION_TYPE
                           AND HT.LIST_NAME = ''CALCULATION_TYPE''
                 LEFT JOIN HELPER_TABLE HL
                        ON HL.HELPER_TABLE_SID = RC.CALCULATION_LEVEL
                           AND HL.LIST_NAME = ''CALCULATION_LEVEL''
                 LEFT JOIN HELPER_TABLE HF
                        ON HF.HELPER_TABLE_SID = RC.REBATE_FREQUENCY
                           AND HF.LIST_NAME = ''REBATE_FREQUENCY''
                 LEFT JOIN REBATE_PLAN_MASTER RPM
                        ON RS.REBATE_PLAN_MASTER_SID = RPM.REBATE_PLAN_MASTER_SID
                 LEFT JOIN REBATE_PLAN_TIER RPT
                        ON RPM.REBATE_PLAN_MASTER_SID = RPT.REBATE_PLAN_MASTER_SID
                 LEFT JOIN HELPER_TABLE HRS
                        ON HRS.HELPER_TABLE_SID = RPM.REBATE_STRUCTURE
                           AND HRS.LIST_NAME = ''REBATE_STRUCTURE''
                 LEFT JOIN HELPER_TABLE HTO
                        ON HTO.HELPER_TABLE_SID = RPT.TIER_OPERATOR
                           AND HTO.LIST_NAME = ''TIER_OPERATOR''
                 LEFT JOIN HELPER_TABLE HRB
                        ON HRB.HELPER_TABLE_SID = RPM.REBATE_BASED_ON
                           AND HRB.LIST_NAME = ''REBATE_BASED_ON''
                 LEFT JOIN HELPER_TABLE HRR
                        ON HRR.HELPER_TABLE_SID = RPM.REBATE_RANGE_BASED_ON
                           AND HRR.LIST_NAME = ''REBATE_RANGE_BASED_ON'')a
                                            where rn=1
                
                ') 


EXEC sp_executesql @SQL



                
                


          IF OBJECT_ID('TEMPDB..#TIER_INFO') IS NOT NULL
            DROP TABLE #TIER_INFO
          
            CREATE TABLE #TIER_INFO
              (
                 CCP_DETAILS_SID             INT,
                 RS_MODEL_SID                INT,
                 ITEM_MASTER_SID             INT,
                 TIER_FROM                   NUMERIC(22, 6),
                 TIER_TO                     NUMERIC(22, 6),
                 DISCOUNT_RATE               NUMERIC(22, 6),
                 REBATE_PER_UNIT             NUMERIC(22, 6),
                 FLAT_DISCOUNT               NUMERIC(22, 6),
                 ITEM_PRICING_QUALIFIER_SID  VARCHAR(1000),
                 RETURN_RATE_SID             INT,
				  REBATE_BASED_ON            VARCHAR(50),-----------------cel-345,cel-346
                 ITEM_PRICING_QUALIFIER_NAME VARCHAR(50), FORMULA_TYPE VARCHAR(1000),ADJUSTED_COMPLEX_FORMULA  VARCHAR(8000)
              )

	SET @SQL = CONCAT (
		'INSERT INTO  #TIER_INFO
                      (CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       ITEM_MASTER_SID,
                       TIER_FROM,
                       TIER_TO,
                       DISCOUNT_RATE,
                       REBATE_PER_UNIT,
                       FLAT_DISCOUNT,
                       ITEM_PRICING_QUALIFIER_SID,
                       RETURN_RATE_SID,
					   REBATE_BASED_ON,-----------------cel-345,cel-346
                       ITEM_PRICING_QUALIFIER_NAME
					   ,FORMULA_TYPE,ADJUSTED_COMPLEX_FORMULA)
          SELECT CCP_DETAILS_SID,
                 RS_MODEL_SID,
                 ITEM_MASTER_SID,
                 TIER_FROM,
                 TIER_TO,CASE WHEN (HT.DESCRIPTION =''SIMPLE'' OR HT.DESCRIPTION IS NULL) THEN 
                 ISNULL(CASE
                          WHEN RS.TIER_OPERATOR = ''%'' THEN RPT.TIER_VALUE
                        END, 0) ELSE NULL END AS DISCOUNT_RATE,
                 CASE WHEN (HT.DESCRIPTION =''SIMPLE'' OR HT.DESCRIPTION IS NULL) THEN  ISNULL(CASE
                          WHEN RS.TIER_OPERATOR = ''$'' THEN
                            CASE
                              WHEN RS.REBATE_STRUCTURE = ''TIER''
                                    OR RS.REBATE_STRUCTURE = ''LEVEL'' THEN RPT.TIER_VALUE
                            END
                        END, 0) ELSE NULL END AS REBATE_PER_UNIT,
                 CASE WHEN (HT.DESCRIPTION =''SIMPLE'' OR HT.DESCRIPTION IS NULL) THEN  ISNULL(CASE
                          WHEN RS.TIER_OPERATOR = ''$'' THEN
                            CASE
                              WHEN RS.REBATE_STRUCTURE = ''FLAT'' THEN RPT.TIER_VALUE
                            END
                        END, 0) ELSE NULL END AS FLAT_DISCOUNT,
                 RS.ITEM_PRICING_QUALIFIER_SID,
                 RS.RETURN_RATE_SID,
				 RS.REBATE_BASED_ON,-----------------cel-345,cel-346
                 --IPQ.ITEM_PRICING_QUALIFIER_NAME
				  COALESCE(IPQ.ITEM_PRICING_QUALIFIER_name,UDF.TOKEN)PRICING_QUALIFIER,-----------cel-345,346
				  HT.DESCRIPTION AS FORMULA_TYPE,ADJUSTED_COMPLEX_FORMULA
          FROM   '
		,@REBATE_INFO
		,' RS
                 JOIN REBATE_PLAN_MASTER RPM
                   ON RS.REBATE_PLAN_MASTER_SID = RPM.REBATE_PLAN_MASTER_SID
                 JOIN REBATE_PLAN_TIER RPT
                   ON RPM.REBATE_PLAN_MASTER_SID = RPT.REBATE_PLAN_MASTER_SID
				   outer apply (SELECT TOKEN FROM 
				   UDF_SPLITSTRING( replace(replace(replace(replace(replace(replace(replace(replace(replace(RPT.ITEM_PRICING_QUALIFIER_SID, ''$'', ''''), ''%'', ''''), ''[0-9]'', ''''), '')'', ''''), ''('', ''''), ''+'', '',''), ''-'', '',''), ''*'', '',''), ''/'', '','')
,'','')) udf
				 LEFT JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=RPM.FORMULA_TYPE
                 LEFT JOIN ITEM_PRICING_QUALIFIER IPQ
                   ON IPQ.ITEM_PRICING_QUALIFIER_NAME = udf.TOKEN ')
                           
                           
  EXEC sp_executesql @SQL 


          SET @QUALIFIER=STUFF((SELECT DISTINCT ',' + ITEM_PRICING_QUALIFIER_NAME
                                FROM   #TIER_INFO
                                FOR XML PATH('')), 1, 1, '')
                                  

          DECLARE @ITEM_UDT UDT_ITEM

          INSERT INTO @ITEM_UDT
          SELECT DISTINCT ITEM_MASTER_SID
          FROM   #TIER_INFO
   
   INSERT INTO #GTS
            (ITEM_MASTER_SID,
             QUARTER,
             YEAR,
			 PERIOD_SID,
             SALES_WEIGHTEDWAC_NORMAL,
             SALES_WEIGHTEDWAC_UPPS			
			 )

SELECT IM.ITEM_MASTER_SID,
       P.QUARTER,
       P.YEAR,
	   P.PERIOD_SID,
       ( ISNULL(SUM(CASE
                      WHEN P.PERIOD_SID < @CURRENT_QUARTER THEN ACTUAL_GTS_SALES
                      ELSE FORECAST_GTS_SALES
                    END)
                  OVER(
                    PARTITION BY IM.ITEM_MASTER_SID,QUARTER, YEAR
                    ) / NULLIF(SUM(CASE
                                                        WHEN P.PERIOD_SID < @CURRENT_QUARTER THEN ACTUAL_GTS_UNITS
                                                        ELSE FORECAST_GTS_UNITS
                                                      END) OVER(
                    PARTITION BY IM.ITEM_MASTER_SID,QUARTER, YEAR
                    ) , 0), 0) )                      SALES_WEIGHTEDWAC_NORMAL,
       ( ISNULL(SUM(CASE
                      WHEN P.PERIOD_SID < @CURRENT_QUARTER THEN ACTUAL_GTS_SALES
                      ELSE FORECAST_GTS_SALES
                    END)
                  OVER(
                    PARTITION BY IM.ITEM_MASTER_SID,QUARTER, YEAR
                    ) / NULLIF(SUM(CASE
                                                        WHEN P.PERIOD_SID < @CURRENT_QUARTER THEN ACTUAL_GTS_UNITS
                                                        ELSE FORECAST_GTS_UNITS
                                                      END) OVER(
                    PARTITION BY IM.ITEM_MASTER_SID,QUARTER, YEAR
                    ) , 0), 0) ) / NULLIF(IM.UPPS, 0) AS SALES_WEIGHTEDWAC_UPPS
					
FROM   [DBO].[UDF_GTS_WAC] (@ITEM_UDT, @ACTUAL_START_PERIOD_SID, @PROJ_END_PERIOD_SID + 2, @FORECAST_NAME, @FORECAST_VERSION) GTS
       INNER JOIN ITEM_MASTER IM
               ON IM.ITEM_MASTER_SID = GTS.ITEM_MASTER_SID
       INNER JOIN PERIOD P
               ON GTS.PERIOD_SID = P.PERIOD_SID 



          IF OBJECT_ID('TEMPDB..#ITEM_NA_PROJ') IS NOT NULL
            DROP TABLE #ITEM_NA_PROJ

          CREATE TABLE #ITEM_NA_PROJ
            (
               ITEM_MASTER_SID     INT,
               NA_PROJ_MASTER_SID  INT,
               NA_PROJ_DETAILS_SID INT
			   ,UPPS				   NUMERIC(22,6)-----------------cel-345,cel-346
            )

          INSERT INTO #ITEM_NA_PROJ
                      (ITEM_MASTER_SID,
                       NA_PROJ_MASTER_SID,
                       NA_PROJ_DETAILS_SID
					   ,UPPS)-----------------cel-345,cel-346
          SELECT ITEM_MASTER_SID,
                 NA_PROJ_MASTER_SID,
                 NA_PROJ_DETAILS_SID
				 ,UPPS-----------------cel-345,cel-346
          FROM   (SELECT IT.ITEM_MASTER_SID,
                         NA.NA_PROJ_MASTER_SID,
                         PD.NA_PROJ_DETAILS_SID,
						 ISNULL(IM.UPPS,0) UPPS,-----------------cel-345,cel-346
                         ROW_NUMBER()
                           OVER (
                             PARTITION BY IT.ITEM_MASTER_SID
                             ORDER BY COALESCE(NA.MODIFIED_DATE, NA.CREATED_DATE) DESC ) AS RN
                  FROM   NA_PROJ_DETAILS PD
                         JOIN NA_PROJ_MASTER NA
                           ON PD.NA_PROJ_MASTER_SID = NA.NA_PROJ_MASTER_SID
                         JOIN @ITEM_UDT IT
                           ON IT.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
						 JOIN ITEM_MASTER Im-----------------cel-345,cel-346
                           ON IT.ITEM_MASTER_SID = Im.ITEM_MASTER_SID-----------------cel-345,cel-346
                  WHERE  SAVE_FLAG = 1
				  ) A
          WHERE  RN = 1


   SET @SQL=Concat('
IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''', @NATIONAL_INFO, ''' 
                      AND TABLE_SCHEMA = ''DBO'')
                                    BEGIN
                        DROP TABLE ', @NATIONAL_INFO, ' 
       END
  
CREATE TABLE ', @NATIONAL_INFO, '
  (
     ITEM_MASTER_SID        INT,
     PERIOD_SID             INT,
     ITEM_PRICING_QUALIFIER  VARCHAR(1000),
     ITEM_PRICE        NUMERIC(22, 6)
	 ,ITEM_PRICE_UPPS   NUMERIC(22, 6),-----------------cel-345,cel-346
	 FORMULA_TYPE VARCHAR(100),
	 COMPLEX_FORMULA	VARCHAR(1000) 
  ) 
  ')
  EXEC sp_executesql @SQL 
              SET @SQL=CONCAT(' INSERT INTO ',@NATIONAL_INFO,'
                     (
                     ITEM_MASTER_SID       ,
                     PERIOD_SID            ,
                     ITEM_PRICING_QUALIFIER,
                     ITEM_PRICE            ,
					 ITEM_PRICE_UPPS,-----------------cel-345,cel-346
					 FORMULA_TYPE,COMPLEX_FORMULA
                     )

      
          SELECT distinct  I.ITEM_MASTER_SID,
                 i.PERIOD_SID,
                 i.ITEM_PRICING_QUALIFIER_NAME,
                     --ITEM_PRICE=COALESCE(UDF.ITEM_PRICE,M.PROJECTION_PRICE,F.PROJECTION_PRICE,S.PROJECTION_PRICE)
					  ITEM_PRICE= COALESCE(NULLIF(case when i.ITEM_PRICING_QUALIFIER_NAME in(''FCP'',''AFSS'') AND UDF.ITEM_PRICE<>0  THEN (ISNULL(GTS.SALES_WEIGHTEDWAC_NORMAL,0)-ISNULL(UDF.ITEM_PRICE,0)) ELSE UDF.ITEM_PRICE END,0),
							  NULLIF(M.PROJECTION_PRICE,0),
							 NULLIF((case when i.ITEM_PRICING_QUALIFIER_NAME in(''FCP'',''AFSS'')   THEN (ISNULL(GTS.SALES_WEIGHTEDWAC_UPPS,0)-ISNULL(F.PROJECTION_PRICE,0)) ELSE F.PROJECTION_PRICE END),0),
							 ISNULL(S.PROJECTION_PRICE,0))------CEL-345,346
					  , ITEM_PRICE_UPPS=
							 COALESCE(NULLIF(case when i.ITEM_PRICING_QUALIFIER_NAME in(''FCP'',''AFSS'') AND UDF.ITEM_PRICE<>0  THEN (ISNULL(GTS.SALES_WEIGHTEDWAC_NORMAL,0)-ISNULL(UDF.ITEM_PRICE,0)) ELSE UDF.ITEM_PRICE END,0),
							  NULLIF(M.PROJECTION_PRICE*M.UPPS,0),
							 NULLIF((case when i.ITEM_PRICING_QUALIFIER_NAME in(''FCP'',''AFSS'')   THEN (ISNULL(GTS.SALES_WEIGHTEDWAC_UPPS,0)-ISNULL(F.PROJECTION_PRICE,0)) ELSE F.PROJECTION_PRICE END) *F.UPPS,0),
							 ISNULL(S.PROJECTION_PRICE*S.UPPS,0)),-----CEL-345,346
							 FORMULA_TYPE,COMPLEX_FORMULA
          FROM          (SELECT DISTINCT i.ITEM_MASTER_SID,TI.ITEM_PRICING_QUALIFIER_NAME,PERIOD_SID,PERIOD_QUARTER,PERIOD_YEAR,COALESCE(FORMULA_TYPE,''COMPLEX'') FORMULA_TYPE,TI.ADJUSTED_COMPLEX_FORMULA AS COMPLEX_FORMULA FROM @ITEM_UDT I
                 JOIN #TIER_INFO TI
                   ON TI.ITEM_MASTER_SID = I.ITEM_MASTER_SID
                 CROSS JOIN #PERIOD P WHERE  P.PERIOD_SID BETWEEN @PROJECTION_START_PERIOD_SID AND @PROJ_END_PERIOD_SID
                 AND TI.ITEM_PRICING_QUALIFIER_NAME IS NOT NULL and EXISTS(SELECT 1 FROM ITEM_PRICING_QUALIFIER A WHERE A.ITEM_PRICING_QUALIFIER_NAME=TI.ITEM_PRICING_QUALIFIER_NAME )
				 )I
                 LEFT JOIN (SELECT MP.PROJECTION_PRICE AS PROJECTION_PRICE,
                                   P.PERIOD_QUARTER,
                                   P.PERIOD_YEAR,
                                   MP.PRICE_TYPE,
                                   MP.NA_PROJ_DETAILS_SID,
                                   APP.ITEM_MASTER_SID
								   ,APP.UPPS-----------------cel-345,cel-346
                            FROM   MEDICAID_URA_PROJ MP
                                   JOIN #ITEM_NA_PROJ APP
                                     ON APP.NA_PROJ_DETAILS_SID = MP.NA_PROJ_DETAILS_SID
                                                              join #period p on p.period_sid=mp.period_sid
                                   ) M
                        ON I.ITEM_MASTER_SID = M.ITEM_MASTER_SID
                           AND i.PERIOD_QUARTER = M.PERIOD_QUARTER
                           AND i.PERIOD_YEAR = M.PERIOD_YEAR
                           AND M.PRICE_TYPE = CASE
                                                WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''BP'' THEN ''BEST PRICE''
                                                WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''ALTURA'' THEN ''ADJUSTMENT CPI (ALT)''
                                                WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''URA'' THEN ''TOTAL URA''
                                                WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''BURA'' THEN ''BASIC URA''
												WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''CPIURA'' then ''CPI URA''
                                                ELSE i.ITEM_PRICING_QUALIFIER_NAME
                                              END-----------cel-345,346
                 LEFT JOIN ( SELECT ( MP.PROJECTION_PRICE ) AS PROJECTION_PRICE,
                                   PN.PERIOD_YEAR,
                                   PN.PERIOD_QUARTER,
                                  MP.PRICE_TYPE  ,
                                   MP.NA_PROJ_DETAILS_SID,
                                   APP.ITEM_MASTER_SID
								   ,APP.UPPS-----------------cel-345,cel-346
                            FROM   FCP_PROJ MP
                                   JOIN #ITEM_NA_PROJ APP
                                     ON APP.NA_PROJ_DETAILS_SID = MP.NA_PROJ_DETAILS_SID
                                   JOIN #PERIOD PN
                                     ON PN.PERIOD_SID = MP.PERIOD_SID									 
									 ) F')
                      SET @SQL=CONCAT(  @SQL,'
						ON I.ITEM_MASTER_SID = F.ITEM_MASTER_SID
                           AND  CASE WHEN F.PRICE_TYPE IN ((''ANON-FAMP''),
                                            (''CPI-U''),
                                            (''FCP''),
                                            (''TRI-CARE''),
                                            (''AVGY''),
                                            (''CPI-URA''),
                                            (''AFSS''),
                                            (''CPI-U ADJUSTMENT''),
                                            (''MAX FSS''))
						   THEN 1
						   ELSE I.PERIOD_QUARTER END = CASE WHEN F.PRICE_TYPE IN ((''ANON-FAMP''),
                                            (''CPI-U''),
                                            (''FCP''),
                                            (''TRI-CARE''),
                                            (''AVGY''),
                                            (''CPI-URA''),
                                            (''AFSS''),
                                            (''CPI-U ADJUSTMENT''),
                                            (''MAX FSS''))
						   THEN 1 ELSE F.PERIOD_QUARTER END -----------cel-345,346
                                  and i.PERIOD_YEAR = F.PERIOD_YEAR 
                           AND CASE
                                                WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''BP'' THEN ''BEST PRICE''
                                                WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''ALTURA'' THEN ''ADJUSTMENT CPI (ALT)''
                                                WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''URA'' THEN ''TOTAL URA''
                                                WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''BURA'' THEN ''BASIC URA''
												WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''CPIURA'' then ''CPI URA''
                                                ELSE i.ITEM_PRICING_QUALIFIER_NAME
                                              END = F.PRICE_TYPE-----------cel-345,346
                 LEFT JOIN (SELECT ( MP.PROJECTION_PRICE ) AS PROJECTION_PRICE,
                                   PN.PERIOD_QUARTER,
                                   PN.PERIOD_YEAR,
                                   case when MP.PRICE_TYPE = ''phs discount'' then ''phs'' else MP.PRICE_TYPE end price_type,  -- CEL 357 change 
                                   MP.NA_PROJ_DETAILS_SID,
                                   APP.ITEM_MASTER_SID
								   ,APP.UPPS-----------------cel-345,cel-346
                            FROM   PHS_PROJ MP
                                   JOIN #ITEM_NA_PROJ APP
                                     ON APP.NA_PROJ_DETAILS_SID = MP.NA_PROJ_DETAILS_SID
                                   JOIN #PERIOD PN
                                     ON PN.PERIOD_SID = MP.PERIOD_SID
									  where price_type <> ''phs''  -- CEL 357 change 
									 ) S
                        ON I.ITEM_MASTER_SID = S.ITEM_MASTER_SID
                           AND i.PERIOD_QUARTER = S.PERIOD_QUARTER
                           AND i.PERIOD_YEAR = S.PERIOD_YEAR
                           AND CASE
                                                WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''BP'' THEN ''BEST PRICE''
                                                WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''ALTURA'' THEN ''ADJUSTMENT CPI (ALT)''
                                                WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''URA'' THEN ''TOTAL URA''
                                                WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''BURA'' THEN ''BASIC URA''
												WHEN i.ITEM_PRICING_QUALIFIER_NAME = ''CPIURA'' then ''CPI URA''
                                                ELSE i.ITEM_PRICING_QUALIFIER_NAME
                                              END = S.PRICE_TYPE-----------cel-345,346
                           LEFT JOIN (SELECT ITEM_MASTER_SID,CASE WHEN A.PRICING_QUALIFIER  IN (''PHS'',''AFSS'',''FCP'') AND A.PERIOD_SID>=@CURRENT_QUARTER THEN 0 ELSE A.ITEM_PRICE END ITEM_PRICE--------cel-394
						   ,A.PERIOD_SID,A.PRICING_QUALIFIER 
                           FROM UDF_ITEM_PRICING(@ITEM_UDT, @QUALIFIER, @ACTUAL_START_PERIOD_SID, @PROJ_END_PERIOD_SID, @ITEM_UOM)A 
                     
                           )UDF
                           ON I.ITEM_MASTER_SID = UDF.ITEM_MASTER_SID
                             AND i.PERIOD_SID=UDF.PERIOD_SID
                             AND i.ITEM_PRICING_QUALIFIER_NAME=UDF.PRICING_QUALIFIER

							LEFT JOIN (select * from #GTS) GTS ON GTS.ITEM_MASTER_SID=I.ITEM_MASTER_SID
							AND GTS.PERIOD_SID=I.PERIOD_SID
        ')

                           EXEC sp_executesql @SQL ,N'@ITEM_UDT UDT_ITEM READONLY,@PROJECTION_START_PERIOD_SID INT,@PROJ_END_PERIOD_SID INT,@ITEM_UOM VARCHAR(50),
                             @ACTUAL_START_PERIOD_SID INT,@QUALIFIER VARCHAR(MAX),@CURRENT_QUARTER INT',@ITEM_UDT=@ITEM_UDT,@PROJECTION_START_PERIOD_SID=@PROJECTION_START_PERIOD_SID,
                             @PROJ_END_PERIOD_SID=@PROJ_END_PERIOD_SID,@ITEM_UOM=@ITEM_UOM,@ACTUAL_START_PERIOD_SID=@ACTUAL_START_PERIOD_SID,@QUALIFIER=@QUALIFIER
							 ,@CURRENT_QUARTER=@CURRENT_QUARTER--------------------cel-394

							

 
SET @SQL=Concat('
IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''', @RETURNS_INFO, ''' 
                      AND TABLE_SCHEMA = ''DBO'')
                                    BEGIN
                        DROP TABLE ', @RETURNS_INFO, ' 
       END
  
CREATE TABLE ', @RETURNS_INFO, '
  (
     ITEM_MASTER_SID INT,
     PERIOD_SID      INT,
     RETURN_RATE  NUMERIC(22,6)
  ) 
  ') 
  EXEC sp_executesql @SQL

          SET @SQL =CONCAT( '
                       INSERT INTO ',@RETURNS_INFO,'  
                      ( ITEM_MASTER_SID,
                        PERIOD_SID,
					    RETURN_RATE )
                        SELECT 
                           TI.ITEM_MASTER_SID,
                           P.PERIOD_SID,
                           RATE AS RETURN_RATE
                           FROM  #TIER_INFO TI
						   JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID=TI.ITEM_MASTER_SID
                           LEFT JOIN RETURN_RATE_FORECAST RRF ON  IM.ITEM_ID=RRF.ITEM_ID
						   AND IM.ITEM_MASTER_SID=RRF.ITEM_MASTER_SID
                           CROSS JOIN #PERIOD P
                           WHERE  PERIOD_SID BETWEEN  @ACTUAL_START_PERIOD_SID AND @PROJ_END_PERIOD_SID AND RETURN_RATE_SID IS NOT NULL 
						   AND P.PERIOD_DATE=DATEFROMPARTS(FORECAST_YEAR,FORECAST_MONTH,01)')
                           
           EXEC SP_EXECUTESQL
            @SQL,
            N' @ACTUAL_START_PERIOD_SID INT, @PROJ_END_PERIOD_SID INT',
            @ACTUAL_START_PERIOD_SID=@ACTUAL_START_PERIOD_SID,
            @PROJ_END_PERIOD_SID=@PROJ_END_PERIOD_SID

  SET @SQL= CONCAT(' INSERT INTO ',@TIER_INFO,'
  
(
					   CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       ITEM_MASTER_SID,
                       TIER_FROM,
                       TIER_TO,
                       DISCOUNT_RATE,
                       REBATE_PER_UNIT,
                       FLAT_DISCOUNT,
                       ITEM_PRICING_QUALIFIER_SID,
                       RETURN_RATE_SID
)
				SELECT DISTINCT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       ITEM_MASTER_SID,
                       TIER_FROM,
                       TIER_TO,
                       DISCOUNT_RATE,
                       REBATE_PER_UNIT,
                       FLAT_DISCOUNT,
                       ITEM_PRICING_QUALIFIER_SID,
                       RETURN_RATE_SID
                FROM #TIER_INFO
'
  )              
  EXEC sp_executesql @SQL
  
     SET @SQL=CONCAT('
            UPDATE A
            SET    FREQ_CAL_START_PERIOD_SID = EFFECTIVE_START_PERIOD_SID - ( ( EFFECTIVE_START_PERIOD_SID - 1 ) % 12 ),
                   FREQ_CAL_END_PERIOD_SID = EFFECTIVE_END_PERIOD_SID - ( ( EFFECTIVE_END_PERIOD_SID - 1 ) % 12 ) + ( 12 - 1 )
           FROM ', @DISC_MASTER_TABLE, ' A ')

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

          RAISERROR (@ERRORMESSAGE,-- MESSAGE TEXT.
                     @ERRORSEVERITY,-- SEVERITY.
                     @ERRORSTATE,-- STATE.
                     @ERRORPROCEDURE,-- PROCEDURE_NAME.
                     @ERRORNUMBER,-- ERRORNUMBER
                     @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END
GO