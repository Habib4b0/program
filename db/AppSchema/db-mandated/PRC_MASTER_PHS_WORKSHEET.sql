IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_MASTER_PHS_WORKSHEET'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_MASTER_PHS_WORKSHEET];
  END;

GO

CREATE PROCEDURE [dbo].[PRC_MASTER_PHS_WORKSHEET] (@NA_PROJ_MASTER_SID INT,
                                                  @PRICE_BASIS        VARCHAR(30),
                                                  @USER_ID            INT,
                                                  @SESSION_ID         VARCHAR(100))
AS
  BEGIN
      SET NOCOUNT ON;
      BEGIN TRY 
DECLARE @ACTUAL_START_DATE     DATETIME,
                  @PROJECTION_START_DATE DATETIME,
                  @PROJECTION_END_DATE   DATETIME,
                  @ACTUAL_END_DATE       DATETIME,
                  @BUSINESS_PROCESS_TYPE VARCHAR(50),
                  @PROJ_PERIOD_START_SID INT,
                  @PROJ_PERIOD_END_SID   INT,
                  @ACT_PERIOD_START_SID  INT,
                  @ACT_PERIOD_END_SID    INT,
				  
			 @NA_NDC11_WAC                 VARCHAR(100) = Concat('ST_NA_NDC11_WAC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''));
          
SET @BUSINESS_PROCESS_TYPE = 'NATIONAL ASSUMPTIONS'; -- CHANGE TO NATIONAL ASSUMPTIONS
		  

          SELECT @ACTUAL_START_DATE = ACTUAL_START_DATE,
                 @ACTUAL_END_DATE = ACTUAL_END_DATE,
                 @PROJECTION_START_DATE = PROJECTION_START_DATE,
                 @PROJECTION_END_DATE = PROJECTION_END_DATE
          FROM   [DBO].[UDF_NA_PROJ_DATES](@BUSINESS_PROCESS_TYPE);

          --------GL COMPANY ID--------------
		      IF OBJECT_ID('TEMPDB..#PERIOD_QUARTER') IS NOT NULL
			  begin
            DROP TABLE #PERIOD_QUARTER;
			end;
         
          CREATE TABLE #PERIOD_QUARTER
            (
               PERIOD_SID     INT NOT NULL PRIMARY KEY,
               PERIOD_YEAR    INT NULL,
               PERIOD_QUARTER INT NULL,
               SEMI_ANNUAL    INT NULL,
               PERIOD_DATE    DATETIME NULL
            );

          INSERT INTO #PERIOD_QUARTER
                      (PERIOD_SID,
                       PERIOD_YEAR,
                       PERIOD_QUARTER,
                       SEMI_ANNUAL,
                       PERIOD_DATE)
          SELECT MIN(PERIOD_SID),
                 [YEAR],
                 [QUARTER],
                 SEMI_ANNUAL,
                 MIN(PERIOD_DATE)
          FROM   dbo.PERIOD
          WHERE  PERIOD_DATE BETWEEN DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_START_DATE), 0) AND @PROJECTION_END_DATE
          GROUP  BY [YEAR],
                    [QUARTER],
                    SEMI_ANNUAL;

          SELECT @ACT_PERIOD_START_SID = MAX(CASE
                                               WHEN PERIOD_DATE = DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_START_DATE), 0) THEN PERIOD_SID
                                             ELSE NULL END),
                 @ACT_PERIOD_END_SID = MAX(CASE
                                             WHEN PERIOD_DATE = DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_END_DATE), 0) THEN PERIOD_SID
                                           ELSE NULL END),
                 @PROJ_PERIOD_START_SID = MAX(CASE
                                                WHEN PERIOD_DATE = DATEADD(QQ, DATEDIFF(QQ, 0, @PROJECTION_START_DATE), 0) THEN PERIOD_SID
                                              ELSE NULL END),
                 @PROJ_PERIOD_END_SID = MAX(CASE
                                              WHEN PERIOD_DATE = DATEADD(QQ, DATEDIFF(QQ, 0, @PROJECTION_END_DATE), 0) THEN PERIOD_SID
                                            ELSE NULL END)
          FROM   (SELECT PERIOD_SID,
                         PERIOD_DATE
                  FROM   #PERIOD_QUARTER
                  WHERE  PERIOD_DATE IN ( DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_START_DATE), 0), DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_END_DATE), 0), DATEADD(QQ, DATEDIFF(QQ, 0, @PROJECTION_START_DATE), 0), DATEADD(QQ, DATEDIFF(QQ, 0, @PROJECTION_END_DATE), 0) )) A;

          ------------------------------------------ SELECTED ITEM BASED ON PROJECTION_DETAILS_SID 
         IF OBJECT_ID('TEMPDB..#PROJECTION_DETAILS') IS NOT NULL
		 BEGIN
            DROP TABLE #PROJECTION_DETAILS;
			END;
          
		  CREATE TABLE #PROJECTION_DETAILS
            (
               NA_PROJ_DETAILS_SID INT NOT NULL PRIMARY KEY,
               ITEM_MASTER_SID     INT NOT NULL UNIQUE,
               ITEM_ID             VARCHAR(50) NULL,
               NA_PROJ_MASTER_SID  INT NULL,
               BASELINE_AMP        NUMERIC(22, 6) NULL,
               BASE_CPI            NUMERIC(22, 6) NULL,
               UPPS                NUMERIC(22, 6) NULL
            );

          INSERT INTO #PROJECTION_DETAILS
                      (NA_PROJ_DETAILS_SID,
                       ITEM_MASTER_SID,
                       ITEM_ID,
                       NA_PROJ_MASTER_SID,
                       BASELINE_AMP,
                       BASE_CPI,
                       UPPS)
          SELECT PD.NA_PROJ_DETAILS_SID,
                 IM.ITEM_MASTER_SID,
                 IM.ITEM_ID,
                 PD.NA_PROJ_MASTER_SID,
                 IM.BASELINE_AMP,
                 IM.BASE_CPI,
                 IM.UPPS
          FROM   dbo.NA_PROJ_DETAILS PD
                 INNER JOIN dbo.ITEM_MASTER IM
                         ON IM.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
          WHERE  PD.NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID;
		   -----------------------------------------------808 -BUSINESS_UNIT CHG---------------------------------------
		 

          -------------------------------------------------------------------------------------------- WAC CALCULATION
          IF OBJECT_ID('TEMPDB..#ITEM_PRICING') IS NOT NULL
		  BEGIN
            DROP TABLE #ITEM_PRICING;
          END;
            CREATE TABLE #ITEM_PRICING
              (
                 ITEM_MASTER_SID  INT NOT NULL,
                 PRICE_TYPE       VARCHAR(25)NOT NULL,
                 PERIOD_SID       INT NOT NULL,
                 ACTUAL_PRICE     NUMERIC(22, 6) NULL,
                 PROJECTION_PRICE NUMERIC(22, 6) NULL
                 PRIMARY KEY (ITEM_MASTER_SID, PRICE_TYPE, PERIOD_SID)
              );

          INSERT INTO #ITEM_PRICING
                      (ITEM_MASTER_SID,
                       PRICE_TYPE,
                       PERIOD_SID)
          SELECT PD.ITEM_MASTER_SID,
                 PT.PRICE_TYPE,
                 PQ.PERIOD_SID
          FROM   #PERIOD_QUARTER PQ
                 CROSS JOIN #PROJECTION_DETAILS PD
                 CROSS JOIN (SELECT PRICE_TYPE
                             FROM   (VALUES('WAC'),
                                           ('TOTAL URA'),
                                           ('PHS'),
                                           ('CMS UNITS'),
                                           ('WAC INCREASE %'),
                                           ('AMP'))V(PRICE_TYPE)) PT
          WHERE  PQ.PERIOD_SID BETWEEN @ACT_PERIOD_START_SID AND @ACT_PERIOD_END_SID
                  OR PQ.PERIOD_SID BETWEEN @PROJ_PERIOD_START_SID AND @PROJ_PERIOD_END_SID;

          DECLARE @ITEMID   [DBO].[UDT_ITEM],
                  @ITEM_UOM VARCHAR(50) = 'UN';

          INSERT INTO @ITEMID
          SELECT ITEM_MASTER_SID
          FROM   dbo.NA_PROJ_DETAILS
          WHERE  NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID;

          UPDATE IP
          SET    IP.ACTUAL_PRICE = A.ACTUAL_PRICE
          FROM   #ITEM_PRICING IP
                 INNER JOIN (SELECT ITEM_MASTER_SID,
                                    CASE PRICING_QUALIFIER
                                                 WHEN 'URA' THEN 'TOTAL URA'
                                                 WHEN @PRICE_BASIS THEN 'WAC'
                                                 ELSE PRICING_QUALIFIER
                                               END AS PRICE_TYPE,
                                    PERIOD_SID,
                                    ITEM_PRICE AS ACTUAL_PRICE
                             FROM   UDF_ITEM_PRICING(@ITEMID, 'AMP,URA,PHS,' + @PRICE_BASIS, @ACT_PERIOD_START_SID, @PROJ_PERIOD_END_SID+2, @ITEM_UOM)) A
                         ON IP.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                            AND IP.PRICE_TYPE = A.PRICE_TYPE
                            AND IP.PERIOD_SID = A.PERIOD_SID;


 DECLARE @sql NVARCHAR(MAX);

     IF @PRICE_BASIS IN( 'EQWAC', 'BQWAC', 'MQWAC', 'AVGQWAC' )
        BEGIN 

	SET @sql = 'UPDATE IP
                SET    IP.PROJECTION_PRICE =  WAC
                FROM   #ITEM_PRICING IP
				INNER JOIN (SELECT DISTINCT ITEM_MASTER_SID,UPPS FROM  #PROJECTION_DETAILS) PD ON PD.ITEM_MASTER_SID=IP.ITEM_MASTER_SID
                       INNER JOIN (select ITEM_MASTER_SID,period_sid,''wac'' as price_type,'+@PRICE_BASIS+' as wac  from '+ @NA_NDC11_WAC 
               + ') A
                               ON IP.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                                  AND IP.PERIOD_SID = A.PERIOD_SID
                                  AND IP.PRICE_TYPE = A.PRICE_TYPE';
    
    EXEC sp_executesql @sql;
    
        END;

      ELSE IF @PRICE_BASIS = 'DAY WEIGHTED WAC'
        BEGIN 

			SET @sql = 'UPDATE IP
                SET    IP.PROJECTION_PRICE =  WAC
                FROM   #ITEM_PRICING IP
				INNER JOIN (SELECT DISTINCT ITEM_MASTER_SID,UPPS FROM  #PROJECTION_DETAILS) PD ON PD.ITEM_MASTER_SID=IP.ITEM_MASTER_SID
                       INNER JOIN (select ITEM_MASTER_SID,period_sid,''wac'' as price_type,DAY_WEIGHTED_WAC as wac  from '+ @NA_NDC11_WAC 
               + ') A
                               ON IP.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                                  AND IP.PERIOD_SID = A.PERIOD_SID
                                  AND IP.PRICE_TYPE = A.PRICE_TYPE';
		

							  EXEC sp_executesql @sql;

        END;
      ELSE IF @PRICE_BASIS = 'SALES WEIGHTED WAC'
        BEGIN 

			SET @sql = 'UPDATE IP
                SET    IP.PROJECTION_PRICE =  WAC
                FROM   #ITEM_PRICING IP
				INNER JOIN (SELECT DISTINCT ITEM_MASTER_SID,UPPS FROM  #PROJECTION_DETAILS) PD ON PD.ITEM_MASTER_SID=IP.ITEM_MASTER_SID
                       INNER JOIN (select ITEM_MASTER_SID,period_sid,''wac'' as price_type,sales_WEIGHTED_WAC as wac  from '+ @NA_NDC11_WAC 
               + ') A
                               ON IP.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                                  AND IP.PERIOD_SID = A.PERIOD_SID
                                  AND IP.PRICE_TYPE = A.PRICE_TYPE';
							 
							  EXEC sp_executesql @sql;
        END;

          ;WITH WAC_INCREASE_UPDATE_CTE
               AS (SELECT ROW_NUMBER()
                            OVER(
                              PARTITION BY ITEM_MASTER_SID, PRICE_TYPE
                              ORDER BY PERIOD_SID ASC) RN,
                          ITEM_MASTER_SID,
                          PRICE_TYPE,
                          PERIOD_SID,
                          PROJECTION_PRICE,
                          ACTUAL_PRICE
                   FROM   #ITEM_PRICING
                   WHERE  PRICE_TYPE IN ( 'WAC', 'WAC INCREASE %' ))
          UPDATE C
          SET    C.PROJECTION_PRICE = D.PROJ_WAC_INCREASE
          FROM   WAC_INCREASE_UPDATE_CTE C
                 INNER JOIN (SELECT A.ITEM_MASTER_SID,
                                    'WAC INCREASE %' AS PRICE_TYPE,
                                    A.PERIOD_SID,
                                    ( ( A.PROJECTION_PRICE - B.PROJECTION_PRICE ) / NULLIF(B.PROJECTION_PRICE, 0) ) * 100 AS PROJ_WAC_INCREASE
                             FROM   WAC_INCREASE_UPDATE_CTE A
                                    LEFT OUTER JOIN WAC_INCREASE_UPDATE_CTE B
                                                 ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                                    AND A.PRICE_TYPE = B.PRICE_TYPE
                                                    AND A.RN = B.RN + 1
                             WHERE  A.PRICE_TYPE = 'WAC') D
                         ON D.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                            AND C.PRICE_TYPE = D.PRICE_TYPE
                            AND D.PERIOD_SID = C.PERIOD_SID;

							-------





	 DECLARE  @PHS_ACTUAL_TABLE     VARCHAR(200) = CONCAT('ST_PHS_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
        @PHS_PROJECTION_TABLE VARCHAR(200) = CONCAT('ST_PHS_PROJ_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@MEDICAID_NEW_NDC_TABLE     VARCHAR(200) = CONCAT('ST_MEDICAID_NEW_NDC_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
        @NA_PROJECTION_TABLE VARCHAR(200) = CONCAT('ST_NATIONAL_ASSUMPTIONS_PROJ_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''));


          ---------------------------------------------------------------------------- REMOVE ALL PRODUCT FROM ACTUAL TABLE

		  DECLARE @SQL_PHS_DEL NVARCHAR(MAX)='';
         SET @SQL_PHS_DEL= CONCAT('TRUNCATE TABLE ',@PHS_ACTUAL_TABLE,'');
         ---SELECT @SQL_PHS_DEL
		 EXEC sp_executesql @SQL_PHS_DEL;
          ------------------------------------------------------------------------  RE-INSERT EXISTING PRODUCT WITH NEW PRODUCT IN ACTUAL TABLE  
		  
		  DECLARE @SQL_PHS_INS NVARCHAR(MAX)='';
          SET @SQL_PHS_INS=CONCAT('INSERT INTO ',@PHS_ACTUAL_TABLE,'
                      (NA_PROJ_DETAILS_SID,
                       PERIOD_SID,
                       PRICE_TYPE,
                       ACTUAL_PRICE)
          SELECT NA_PROJ_DETAILS_SID,
                 P.PERIOD_SID,
                 PT.PRICE_TYPE,
                 0
          FROM   #PERIOD_QUARTER P
                 CROSS JOIN #PROJECTION_DETAILS PD
                 CROSS JOIN (SELECT PRICE_TYPE
                             FROM   (VALUES(''WAC''),
                                           (''TOTAL URA''),
                                           (''PHS''),
                                           (''AMP''),
                                           (''CMS UNITS''),
                                           (''WAC INCREASE %''),
										   (''PHS Discount''))V(PRICE_TYPE)) PT
          WHERE  P.PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' AND ',@ACT_PERIOD_END_SID,'');
		  --- SELECT @SQL_PHS_INS
		 EXEC sp_executesql @SQL_PHS_INS;
          
		  
		--------------------------------------------------------------------------------------------- FETCHING PROJECTION PRICE FROM NATIONAL ASSUMPTION ACTUALS TABLE AND INSERTING INTO FCP ACTUALS TABLE
         	  DECLARE @SQL_PHS_UPD NVARCHAR(MAX)='';
		 
		  SET @SQL_PHS_UPD=CONCAT('UPDATE PA
          SET    PA.ACTUAL_PRICE = CASE
                                     WHEN PA.PRICE_TYPE IN( ''WAC'', ''WAC INCREASE %'' ) THEN ISNULL(IP.PROJECTION_PRICE, 0)
                                     WHEN PA.PRICE_TYPE = ''CMS UNITS'' THEN PD.UPPS
                                     ELSE ISNULL(IP.ACTUAL_PRICE, 0)
                                   END
          FROM   ',@PHS_ACTUAL_TABLE,' PA
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON PD.NA_PROJ_DETAILS_SID = PA.NA_PROJ_DETAILS_SID
                 INNER JOIN #ITEM_PRICING IP
                         ON PD.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                            AND PA.PERIOD_SID = IP.PERIOD_SID
                            AND IP.PRICE_TYPE = PA.PRICE_TYPE');

         ---SELECT @SQL_PHS_UPD
		 EXEC sp_executesql @SQL_PHS_UPD;


		 	  DECLARE @SQL_PHS_act_UPD NVARCHAR(MAX)='';
set @SQL_PHS_act_UPD = '
UPDATE pp
SET    pp.actual_price = a.actual_price
FROM   '+@PHS_ACTUAL_TABLE+' pp
       JOIN (SELECT pp.NA_PROJ_DETAILS_SID,
                    pp.period_sid,
                    ''PHS Discount'' AS price_type,
                    actual_price = MQWAC - actual_price
             FROM   '+@NA_NDC11_WAC+' n
                    JOIN #PROJECTION_DETAILS pd
                      ON pd.ITEM_MASTER_SID = n.ITEM_MASTER_SID
                    JOIN '+@PHS_ACTUAL_TABLE+' pp
                      ON pp.NA_PROJ_DETAILS_SID = pd.NA_PROJ_DETAILS_SID
                         AND pp.PERIOD_SID = n.PERIOD_SID
             WHERE  pp.PRICE_TYPE = ''phs'') a
         ON a.NA_PROJ_DETAILS_SID = pp.NA_PROJ_DETAILS_SID
            AND a.period_sid = pp.period_sid
            AND a.price_type = pp.price_type';

	EXEC sp_executesql @SQL_PHS_act_UPD;
          -------------------------------cel-1828
          DECLARE @NA_ACTUAL_TABLE VARCHAR(200) = Concat('ST_NATIONAL_ASSUMPTIONS_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')); -----------cel-1828         
          DECLARE @SQL_CTE1 NVARCHAR(MAX)='';

          SET @SQL_CTE1=Concat('WITH URA
               AS (SELECT A.NA_PROJ_DETAILS_SID,
                          A.ITEM_MASTER_SID,
                          PERIOD_SID,
                          CASE
                            WHEN ISNULL(AMP, 0) - ISNULL(BP, 0) > ISNULL(AMP, 0) * 0.231 THEN ISNULL(AMP, 0) - ISNULL(BP, 0)
                            ELSE ISNULL(AMP, 0) * 0.231
                          END BASIC_URA,
                          CASE
                            WHEN ( AMP - ( ( CPI / NULLIF(B.BASE_YEAR_CPI, 0) ) * B.BASE_YEAR_AMP ) ) < 1 THEN 0
                            ELSE ( AMP - ( ( CPI / NULLIF(B.BASE_YEAR_CPI, 0) ) * B.BASE_YEAR_AMP ) )
                          END CPI_URA,
                          AMP
                   FROM   (SELECT NAP.ITEM_MASTER_SID,
                                  PD.NA_PROJ_DETAILS_SID,
                                  PERIOD_SID,
                                  AMP=MAX(CASE
                                            WHEN PRICE_TYPE = ''AMP'' THEN ACTUAL_PRICE
                                          END),
                                  BP=MAX(CASE
                                           WHEN PRICE_TYPE = ''BEST PRICE'' THEN ACTUAL_PRICE
                                         END),
                                  CPI=MAX(CASE
                                            WHEN PRICE_TYPE = ''CPI-U'' THEN ACTUAL_PRICE
                                          END)
                           FROM   ', @NA_ACTUAL_TABLE, ' NAP
                                  INNER JOIN #PROJECTION_DETAILS PD
                                          ON NAP.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
                                             AND NAP.PERIOD_SID BETWEEN ', @ACT_PERIOD_START_SID, ' AND ', @ACT_PERIOD_END_SID, '
                           WHERE  PRICE_TYPE IN( ''AMP'', ''BEST PRICE'', ''CPI-U'' )
                           GROUP  BY NAP.ITEM_MASTER_SID,
                                     PD.NA_PROJ_DETAILS_SID,
                                     PERIOD_SID) A
                          INNER JOIN (SELECT BASE_YEAR_AMP= COALESCE(C.BASE_YEAR_AMP, PD.BASELINE_AMP),
                                             BASE_YEAR_CPI=COALESCE(C.BASE_YEAR_CPI, PD.BASE_CPI),
                                             NA_PROJ_DETAILS_SID
                                      FROM   ITEM_MASTER I
                                             INNER JOIN #PROJECTION_DETAILS PD
                                                     ON I.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
                                             LEFT OUTER JOIN (SELECT MNN.BASE_YEAR_AMP,
                                                                     MNN.BASE_YEAR_CPI,
                                                                     ITEM_MASTER_SID
                                                              FROM   ', @MEDICAID_NEW_NDC_TABLE, ' MNN
                                                                     INNER JOIN ITEM_MASTER IM
                                                                             ON IM.NDC9 = MNN.NDC9) C
                                                          ON C.ITEM_MASTER_SID = I.ITEM_MASTER_SID) B
                                  ON A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID)
								 
          UPDATE IP
          SET    IP.ACTUAL_PRICE = iif(isnull(IP.ACTUAL_PRICE,0)=0,A.ITEM_PRICE,IP.ACTUAL_PRICE)
		  FROM   ', @PHS_ACTUAL_TABLE, ' IP
				INNER JOIN #PROJECTION_DETAILS PD ON PD.NA_PROJ_DETAILS_SID=IP.NA_PROJ_DETAILS_SID
                 INNER JOIN (SELECT ITEM_MASTER_SID,
                                    PERIOD_SID,
                                    ITEM_PRICE,
                                    CS.PRICE_TYPE
                             FROM   URA
                                    CROSS APPLY (VALUES (CASE WHEN (ISNULL(BASIC_URA, 0) + ISNULL(CPI_URA, 0))>AMP THEN AMP ELSE (ISNULL(BASIC_URA, 0) + ISNULL(CPI_URA, 0)) END,-----CEL-337
                                                ''TOTAL URA''),
                                                        (AMP,
                                                ''AMP'')) CS (ITEM_PRICE, PRICE_TYPE)) A
                         ON PD.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                            AND IP.PRICE_TYPE = A.PRICE_TYPE
                            AND IP.PERIOD_SID = A.PERIOD_SID
							');

          EXEC Sp_executesql
            @SQL_CTE1

          -------------------------------cel-1828
          ---------------------------------------------------------------------------------------------------------- TO FETCH EACH PRODUCT AND PERIOD WHICH IS NOT EXISTS IN PROJECTION TABLE AND INSERT NEW PRODUCT IN PROJECTION TABLE
          
		  DECLARE @SQL_PHS_INS_PROJ NVARCHAR(MAX)='';
		 SET @SQL_PHS_INS_PROJ=CONCAT('INSERT INTO ',@PHS_PROJECTION_TABLE,'
                      (NA_PROJ_DETAILS_SID,
                       PERIOD_SID,
                       PRICE_TYPE,
                       PROJECTION_PRICE)
          SELECT NA_PROJ_DETAILS_SID,
                 PERIOD_SID,
                 PRICE_TYPE,
                 0
          FROM   (SELECT PD.NA_PROJ_DETAILS_SID,
                         PERIOD_SID,
						 PRICE_TYPE
                  FROM   #PROJECTION_DETAILS PD
                         CROSS JOIN #PERIOD_QUARTER PQ
                 
				  CROSS JOIN (SELECT PRICE_TYPE
                             FROM   (VALUES(''WAC''),
                                           (''PHS''),
                                           (''AMP''),
                                           (''TOTAL URA''),
                                           (''CMS UNITS''),
                                           (''WAC INCREASE %''),
										   (''PHS Discount''))V(PRICE_TYPE)) PT
				WHERE  PQ.PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' AND ',@PROJ_PERIOD_END_SID,'
                  EXCEPT
                  SELECT PP.NA_PROJ_DETAILS_SID,
                         PP.PERIOD_SID,
						 PRICE_TYPE
                  FROM   ',@PHS_PROJECTION_TABLE,' PP
                         INNER JOIN #PROJECTION_DETAILS PD
                                 ON PP.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
                  WHERE  PD.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,'
                         ) A
');


			--SELECT @SQL_PHS_INS_PROJ
			EXEC sp_executesql @SQL_PHS_INS_PROJ;
          ------------------------------------------------------------------------ BASIC URA CALCULATION
          ;
		            
		  DECLARE @SQL_CTE NVARCHAR(MAX)='';
          SET @SQL_CTE=CONCAT('WITH URA
               AS (SELECT A.NA_PROJ_DETAILS_SID,
                          A.ITEM_MASTER_SID,
                          PERIOD_SID,
                          CASE
                            WHEN ISNULL(AMP, 0) - ISNULL(BP, 0) > ISNULL(AMP, 0) * 0.231 THEN ISNULL(AMP, 0) - ISNULL(BP, 0)
                            ELSE ISNULL(AMP, 0) * 0.231
                          END BASIC_URA,
                          CASE
                            WHEN ( AMP - ( ( CPI / NULLIF(B.BASE_YEAR_CPI, 0) ) * B.BASE_YEAR_AMP ) ) < 1 THEN 0
                            ELSE ( AMP - ( ( CPI / NULLIF(B.BASE_YEAR_CPI, 0) ) * B.BASE_YEAR_AMP ) )
                          END CPI_URA,
                          AMP
                   FROM   (SELECT NAP.ITEM_MASTER_SID,
                                  PD.NA_PROJ_DETAILS_SID,
                                  PERIOD_SID,
                                  AMP=MAX(CASE
                                            WHEN PRICE_TYPE = ''AMP'' THEN PROJECTION_PRICE
                                          END),
                                  BP=MAX(CASE
                                           WHEN PRICE_TYPE = ''BEST PRICE'' THEN PROJECTION_PRICE
                                         END),
                                  CPI=MAX(CASE
                                            WHEN PRICE_TYPE = ''CPI-U'' THEN PROJECTION_PRICE
                                          END)
                           FROM   ',@NA_PROJECTION_TABLE,' NAP
                                  INNER JOIN #PROJECTION_DETAILS PD
                                          ON NAP.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
                                             AND NAP.PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' AND ',@PROJ_PERIOD_END_SID,'
                           WHERE  PRICE_TYPE IN( ''AMP'', ''BEST PRICE'', ''CPI-U'' )
                           GROUP  BY NAP.ITEM_MASTER_SID,
                                     PD.NA_PROJ_DETAILS_SID,
                                     PERIOD_SID) A
                          INNER JOIN (SELECT BASE_YEAR_AMP= COALESCE(C.BASE_YEAR_AMP, PD.BASELINE_AMP),
                                             BASE_YEAR_CPI=COALESCE(C.BASE_YEAR_CPI, PD.BASE_CPI),
                                             NA_PROJ_DETAILS_SID
                                      FROM   ITEM_MASTER I
                                             INNER JOIN #PROJECTION_DETAILS PD
                                                     ON I.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
                                             LEFT OUTER JOIN (SELECT MNN.BASE_YEAR_AMP,
                                                                     MNN.BASE_YEAR_CPI,
                                                                     ITEM_MASTER_SID
                                                              FROM   ',@MEDICAID_NEW_NDC_TABLE,' MNN
                                                                     INNER JOIN ITEM_MASTER IM
                                                                             ON IM.NDC9 = MNN.NDC9) C
                                                          ON C.ITEM_MASTER_SID = I.ITEM_MASTER_SID) B
                                  ON A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID)
								 
          UPDATE IP
          SET    IP.PROJECTION_PRICE = A.ITEM_PRICE
          FROM   #ITEM_PRICING IP
                 INNER JOIN (SELECT ITEM_MASTER_SID,
                                    PERIOD_SID,
                                    ITEM_PRICE,
                                    CS.PRICE_TYPE
                             FROM   URA
                                    CROSS APPLY (VALUES (CASE WHEN (ISNULL(BASIC_URA, 0) + ISNULL(CPI_URA, 0))>AMP THEN AMP ELSE (ISNULL(BASIC_URA, 0) + ISNULL(CPI_URA, 0)) END,-----CEL-337
                                                ''TOTAL URA''),
                                                        (AMP,
                                                ''AMP'')) CS (ITEM_PRICE, PRICE_TYPE)) A
                         ON IP.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                            AND IP.PRICE_TYPE = A.PRICE_TYPE
                            AND IP.PERIOD_SID = A.PERIOD_SID
							');
							 --SELECT @SQL_CTE
						     EXEC sp_executesql @SQL_CTE;

		  DECLARE @SQL_PHS_PROJ_UPD NVARCHAR(MAX)='';

        SET @SQL_PHS_PROJ_UPD =CONCAT('UPDATE PP
          SET    PP.PROJECTION_PRICE = ISNULL(IP.PROJECTION_PRICE, 0)
          FROM   ',@PHS_PROJECTION_TABLE,' PP
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON PD.NA_PROJ_DETAILS_SID = PP.NA_PROJ_DETAILS_SID
                 INNER JOIN #ITEM_PRICING IP
                         ON PD.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                            AND PP.PERIOD_SID = IP.PERIOD_SID
                            AND PP.PRICE_TYPE = IP.PRICE_TYPE
          WHERE  IP.PRICE_TYPE IN ( ''WAC'', ''WAC INCREASE %'', ''TOTAL URA'', ''AMP'' )');
		  --SELECT @SQL_PHS_PROJ_UPD
		  EXEC sp_executesql @SQL_PHS_PROJ_UPD;

		  
		  DECLARE @SQL_PHS_PROJ_UPD1 NVARCHAR(MAX)='';
         SET @SQL_PHS_PROJ_UPD1=CONCAT('UPDATE PP
          SET    PROJECTION_PRICE = PD.UPPS
          FROM    ',@PHS_PROJECTION_TABLE,' PP
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON PP.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
          WHERE  PP.PRICE_TYPE = ''CMS UNITS''');

		  --SELECT @SQL_PHS_PROJ_UPD1
		  EXEC sp_executesql @SQL_PHS_PROJ_UPD1;

		  DECLARE @SQL_PHS_PROJ_UPD2 NVARCHAR(MAX)='';
         SET @SQL_PHS_PROJ_UPD2=CONCAT('UPDATE PP
          SET    PP.PROJECTION_PRICE = CASE WHEN (ISNULL(PHS.PHS_PRICE, 0)) <= 0 then 0.01
		                                    ELSE  ISNULL(PHS.PHS_PRICE, 0)
											END
          FROM   ',@PHS_PROJECTION_TABLE,' PP
                 INNER JOIN (SELECT NA_PROJ_DETAILS_SID,
                                    PERIOD_SID,
                                    PRICE_TYPE = ''PHS'',
                                    PHS_PRICE= [AMP] - [TOTAL URA]
                             FROM   (SELECT ITEM_MASTER_SID,
                                            PERIOD_SID,
                                            PROJECTION_PRICE= COALESCE(PROJECTION_PRICE, ACTUAL_PRICE),
                                            PRICE_TYPE
                                     FROM   #ITEM_PRICING
                                     WHERE  PRICE_TYPE IN ( ''TOTAL URA'', ''AMP'' )) A
                                    PIVOT (MAX(PROJECTION_PRICE)
                                          FOR PRICE_TYPE IN ([TOTAL URA],
                                                             [AMP]))P
                                    INNER JOIN #PROJECTION_DETAILS PD
                                            ON PD.ITEM_MASTER_SID = P.ITEM_MASTER_SID
                             WHERE  PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' - 6 AND ',@PROJ_PERIOD_END_SID,') PHS
                         ON PHS.NA_PROJ_DETAILS_SID = PP.NA_PROJ_DETAILS_SID
                            AND PP.PRICE_TYPE = PHS.PRICE_TYPE
                            AND PP.PERIOD_SID = PHS.PERIOD_SID + 6');
							--SELECT @SQL_PHS_PROJ_UPD2
							EXEC sp_executesql @SQL_PHS_PROJ_UPD2;

   

	  DECLARE @SQL_PHS_PROJ_UPD3 NVARCHAR(MAX)='';
set @SQL_PHS_PROJ_UPD3 = '
UPDATE pp
SET    pp.projection_price = a.projection_price
FROM   '+@PHS_PROJECTION_TABLE+' pp
       JOIN (SELECT pp.NA_PROJ_DETAILS_SID,
                    pp.period_sid,
                    ''PHS Discount'' AS price_type,
                    projection_price = MQWAC - projection_price
             FROM   '+@NA_NDC11_WAC +' n
                    JOIN #PROJECTION_DETAILS pd
                      ON pd.ITEM_MASTER_SID = n.ITEM_MASTER_SID
                    JOIN '+@PHS_PROJECTION_TABLE+' pp
                      ON pp.NA_PROJ_DETAILS_SID = pd.NA_PROJ_DETAILS_SID
                         AND pp.PERIOD_SID = n.PERIOD_SID
             WHERE  pp.PRICE_TYPE = ''phs'') a
         ON a.NA_PROJ_DETAILS_SID = pp.NA_PROJ_DETAILS_SID
            AND a.period_sid = pp.period_sid
            AND a.price_type = pp.price_type';

	EXEC sp_executesql @SQL_PHS_PROJ_UPD3;

IF OBJECT_ID('TEMPDB..#FORECAST_PRICE_PERIOD') IS NOT NULL
BEGIN
DROP TABLE #FORECAST_PRICE_PERIOD;
END;
/*DECLARE @ITEM_MASTER_SID INT;
SELECT @ITEM_MASTER_SID=  ITEM_MASTER_SID FROM  #PROJECTION_DETAILS;

CREATE TABLE #FORECAST_PRICE_PERIOD (
	PRICE_TYPE VARCHAR(100) NULL
	,PERIOD_SID INT NULL
	);

	SET @SQL_PHS_PROJ_UPD3 =CONCAT('WITH CTE
AS (
	SELECT DISTINCT  PRICE_TYPE 
	                ,CASE PRICE_TYPE WHEN ''PHS Discount'' THEN ''PHS''
									 WHEN  ''TOTAL URA''  THEN ''URA''
									 WHEN ''WAC INCREASE %'' THEN ''WAC''
									 ELSE PRICE_TYPE END PRICE_TYPE1
FROM  ',@PHS_PROJECTION_TABLE,'

	)
	INSERT INTO #FORECAST_PRICE_PERIOD(PRICE_TYPE,PERIOD_SID)
	SELECT PRICE_TYPE
		,PERIOD_SID 
	FROM CTE A
	OUTER APPLY (
		SELECT TOP 1 P.PERIOD_SID
		FROM ITEM_PRICING IP
		JOIN ITEM_PRICING_QUALIFIER IPQ ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
			AND  IPQ.PRICING_QUALIFIER = A.PRICE_TYPE1
		JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IP.ITEM_UOM
		AND HT.DESCRIPTION=@ITEM_UOM
		JOIN PERIOD P ON P.PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(COALESCE(END_DATE, START_DATE), 0))))
		WHERE ITEM_MASTER_SID = ',@ITEM_MASTER_SID,'
		ORDER BY COALESCE(END_DATE, START_DATE) DESC
		) B
	');
EXEC SP_EXECUTESQL @SQL_PHS_PROJ_UPD3;


SET @SQL_PHS_PROJ_UPD3 = 'UPDATE SFP
	SET PROJECTION_PRICE=0
	FROM '+@PHS_PROJECTION_TABLE+'  SFP
	JOIN #FORECAST_PRICE_PERIOD FPP
	ON SFP.PRICE_TYPE=FPP.PRICE_TYPE
	AND FPP.PERIOD_SID IS NOT NULL
	AND SFP.PERIOD_SID<= CASE WHEN SFP.PRICE_TYPE =''WAC INCREASE %'' then FPP.PERIOD_SID+3 ELSE FPP.PERIOD_SID END';

EXEC SP_EXECUTESQL @SQL_PHS_PROJ_UPD3;
*/
--------------cel-1827(local)
    CREATE TABLE #FORECAST_PRICE_PERIOD
      (
         ITEM_MASTER_SID INT,
         PRICE_TYPE      VARCHAR(100) NULL,
         PERIOD_SID      INT NULL,
		 NA_PROJ_DETAILS_SID int 
      );
    SET @SQL_PHS_PROJ_UPD3 =Concat(';WITH CTE
                                AS (
                                	SELECT DISTINCT   P.NA_PROJ_DETAILS_SID,
													P.ITEM_MASTER_SID,
													PRICE_TYPE
                                	                ,CASE PRICE_TYPE WHEN ''PHS Discount'' THEN ''PHS''
                                									 WHEN  ''TOTAL URA''  THEN ''URA''
                                									 WHEN ''WAC INCREASE %'' THEN ''WAC''
                                									 ELSE PRICE_TYPE END PRICE_TYPE1
                                FROM  ', @PHS_PROJECTION_TABLE, ' F
                  JOIN #PROJECTION_DETAILS P
                    ON P.NA_PROJ_DETAILS_SID = F.NA_PROJ_DETAILS_SID
					  WHERE PRICE_TYPE NOT IN (''WAC INCREASE %'' , ''WAC'')
                                	),
                                CTE1
                                AS (SELECT  A.NA_PROJ_DETAILS_SID,
						PRICE_TYPE,
                       PRICE_TYPE1,
                       PERIOD_SID,
                       A.ITEM_MASTER_SID
                FROM   CTE A
                       OUTER APPLY (SELECT TOP 1 ip.ITEM_MASTER_SID,
                                                 P.PERIOD_SID
                                    FROM   ITEM_PRICING IP
                                           JOIN ITEM_PRICING_QUALIFIER IPQ
                                             ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                                                AND IPQ.PRICING_QUALIFIER = A.PRICE_TYPE1
                                           JOIN HELPER_TABLE HT
                                             ON HT.HELPER_TABLE_SID = IP.ITEM_UOM
                                                AND HT.DESCRIPTION = ''', @ITEM_UOM, '''
                                           JOIN PERIOD P
                                             ON P.PERIOD_DATE = CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(COALESCE(END_DATE, START_DATE), 0))))
											  where exists(select 1 from #PROJECTION_DETAILS pd where pd.ITEM_MASTER_SID=ip.ITEM_MASTER_SID)
                                    ORDER  BY COALESCE(END_DATE, START_DATE) DESC) B)                                
                                	INSERT INTO #FORECAST_PRICE_PERIOD(PRICE_TYPE,ITEM_MASTER_SID,PERIOD_SID,NA_PROJ_DETAILS_SID)
                                	     SELECT C1.PRICE_TYPE,
                                	     C1.ITEM_MASTER_SID,
                                	     C1.PERIOD_SID,
                                	     c1.NA_PROJ_DETAILS_SID
                                	     FROM   CTE1 C1
                                	    ');	 ------cel-1895 
/*
    SET @SQL_PHS_PROJ_UPD3 =Concat(';WITH CTE
                                AS (
                                	SELECT DISTINCT  PRICE_TYPE
                                	                ,CASE PRICE_TYPE WHEN ''PHS Discount'' THEN ''PHS''
                                									 WHEN  ''TOTAL URA''  THEN ''URA''
                                									 WHEN ''WAC INCREASE %'' THEN ''WAC''
                                									 ELSE PRICE_TYPE END PRICE_TYPE1
                                FROM  ', @PHS_PROJECTION_TABLE, '  WHERE PRICE_TYPE NOT IN (''WAC INCREASE %'' , ''WAC'')
                                	),
                                CTE1
                                AS (SELECT ITEM_MASTER_SID,
                                           PERIOD_SID,
                                           PRICING_QUALIFIER
                                    FROM   (SELECT IP.ITEM_MASTER_SID,
                                                   P.PERIOD_SID,
                                                   IPQ.PRICING_QUALIFIER,
                                                   ROW_NUMBER()
                                                     OVER(
                                                       PARTITION BY IP.ITEM_MASTER_SID, IPQ.PRICING_QUALIFIER
                                                       ORDER BY COALESCE(END_DATE, START_DATE) DESC) RN
                                            FROM   ITEM_PRICING IP
                                                   JOIN ITEM_PRICING_QUALIFIER IPQ
                                                     ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                                                   JOIN HELPER_TABLE HT
                                                     ON HT.HELPER_TABLE_SID = IP.ITEM_UOM
                                                        AND HT.DESCRIPTION = ''', @ITEM_UOM, '''
                                                   JOIN PERIOD P
                                                     ON P.PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, -1, DATEADD(DD, 1, EOMONTH(COALESCE(END_DATE, START_DATE), 0))))
                                            WHERE  EXISTS (SELECT 1
                                                           FROM   #PROJECTION_DETAILS PD
                                                           WHERE  PD.ITEM_MASTER_SID = IP.ITEM_MASTER_SID)
                                                   AND EXISTS (SELECT 1
                                                               FROM   CTE CX
                                                               WHERE  CX.PRICE_TYPE1 = IPQ.PRICING_QUALIFIER))A
                                    WHERE  RN = 1)                                
                                	INSERT INTO #FORECAST_PRICE_PERIOD(PRICE_TYPE,ITEM_MASTER_SID,PERIOD_SID,NA_PROJ_DETAILS_SID)
                                	     SELECT C.PRICE_TYPE,
                                	     C1.ITEM_MASTER_SID,
                                	     C1.PERIOD_SID,
                                	     pd.NA_PROJ_DETAILS_SID
                                	     FROM   CTE C
                                	     JOIN CTE1 C1
                                	     ON C.PRICE_TYPE1 = C1.PRICING_QUALIFIER
                                	     JOIN #PROJECTION_DETAILS PD ON PD.ITEM_MASTER_SID=C1.ITEM_MASTER_SID');
*/
    EXEC Sp_executesql
      @SQL_PHS_PROJ_UPD3;

    ---SELECT @SQL_PHS_PROJ_UPD3

      -----------------------------------cel-1828------
      ;
          WITH CTE
               AS (SELECT ITEM_MASTER_SID,
                          NA_PROJ_DETAILS_SID,
                          Min(PERIOD_SID) PERIOD_SID
                   FROM   #FORECAST_PRICE_PERIOD
                   WHERE  PRICE_TYPE IN ( 'AMP' )
                   GROUP  BY ITEM_MASTER_SID,
                             NA_PROJ_DETAILS_SID)
          UPDATE FP
          SET    PERIOD_SID = C.PERIOD_SID
          FROM   #FORECAST_PRICE_PERIOD FP
                 JOIN CTE C
                   ON C.ITEM_MASTER_SID = FP.ITEM_MASTER_SID
                      AND FP.NA_PROJ_DETAILS_SID = C.NA_PROJ_DETAILS_SID
                      AND FP.PRICE_TYPE IN ( 'TOTAL URA' )

          -----------------------------cel-1828----
    SET @SQL_PHS_PROJ_UPD3 = Concat('UPDATE SFP
	SET PROJECTION_PRICE=0
	FROM ', @PHS_PROJECTION_TABLE, '  SFP
	JOIN #FORECAST_PRICE_PERIOD FPP
	ON SFP.PRICE_TYPE=FPP.PRICE_TYPE
	AND FPP.PERIOD_SID IS NOT NULL
	AND FPP.NA_PROJ_DETAILS_SID=SFP.NA_PROJ_DETAILS_SID
	AND SFP.PERIOD_SID<= CASE WHEN SFP.PRICE_TYPE =''WAC INCREASE %'' then FPP.PERIOD_SID+3 ELSE FPP.PERIOD_SID END');
 EXEC Sp_executesql
      @SQL_PHS_PROJ_UPD3;
	  --------------cel-1827(local)
      END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC DBO.USPERRORCOLLECTOR;

          SELECT @ERRORMESSAGE = ERROR_MESSAGE(),
                 @ERRORSEVERITY = ERROR_SEVERITY(),
                 @ERRORSTATE = ERROR_STATE(),
                 @ERRORPROCEDURE = ERROR_PROCEDURE(),
                 @ERRORLINE = ERROR_LINE(),
                 @ERRORNUMBER = ERROR_NUMBER();

          RAISERROR (@ERRORMESSAGE,-- MESSAGE TEXT.
                     @ERRORSEVERITY,-- SEVERITY.
                     @ERRORSTATE,-- STATE.
                     @ERRORPROCEDURE,-- PROCEDURE_NAME.
                     @ERRORNUMBER,-- ERRORNUMBER
                     @ERRORLINE -- ERRORLINE
          );
      END CATCH;
  END;

GO

