IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_MASTER_FCP_WORKSHEET'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_MASTER_FCP_WORKSHEET];
  END;

GO

CREATE PROCEDURE [dbo].[PRC_MASTER_FCP_WORKSHEET] (@NA_PROJ_MASTER_SID INT,
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
                             @BUSINESS_UNIT INT   , --------------------GAL-808
                             @COMPANY_ID INT,----GAL-808
                     @NA_NDC11_WAC                 VARCHAR(100) = Concat('ST_NA_NDC11_WAC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''));
          SET @BUSINESS_PROCESS_TYPE ='NATIONAL ASSUMPTIONS';
 
          SELECT @ACTUAL_START_DATE = ACTUAL_START_DATE,
                 @ACTUAL_END_DATE = ACTUAL_END_DATE,
                 @PROJECTION_START_DATE = PROJECTION_START_DATE,
                 @PROJECTION_END_DATE = PROJECTION_END_DATE
          FROM   [DBO].[UDF_NA_PROJ_DATES](@BUSINESS_PROCESS_TYPE);
               
          --------------------------GL COMPANY ID-------------------
                IF OBJECT_ID('TEMPDB..#PERIOD_QUARTER') IS NOT NULL
				BEGIN
            DROP TABLE #PERIOD_QUARTER;
			END;
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
               NA_PROJ_DETAILS_SID INT NOT NULL,
               ITEM_MASTER_SID     INT NULL,
               NA_PROJ_MASTER_SID  INT NULL,
               ITEM_ID             VARCHAR(50) NULL,
               UPPS                NUMERIC(22, 6) NULL
            );
 
          INSERT INTO #PROJECTION_DETAILS(NA_PROJ_DETAILS_SID,ITEM_MASTER_SID,NA_PROJ_MASTER_SID,ITEM_ID,UPPS)
          SELECT NPD.NA_PROJ_DETAILS_SID,
                 IM.ITEM_MASTER_SID,
                 NPD.NA_PROJ_MASTER_SID,
                 IM.ITEM_ID,
                 IM.UPPS
          FROM   dbo.NA_PROJ_DETAILS NPD
                 INNER JOIN dbo.ITEM_MASTER IM
                         ON IM.ITEM_MASTER_SID = NPD.ITEM_MASTER_SID
          WHERE  NPD.NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID;
 
 
                DECLARE @NA_ASS1_TABLE     VARCHAR(200) = CONCAT('ST_NATIONAL_ASSUMPTIONS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''));
                DECLARE @SQL_NM_AS NVARCHAR(MAX);
                              
 
              IF OBJECT_ID('TEMPDB..#EFFECTIVE_PERIOD') IS NOT NULL
			  BEGIN
                DROP TABLE #EFFECTIVE_PERIOD;
 END;
              CREATE TABLE #EFFECTIVE_PERIOD
                (
                     NA_PROJ_DETAILS_SID INT NOT NULL,
                     START_PERIOD        INT NULL,
                     END_PERIOD          INT NULL
                );
 
              SET @SQL_NM_AS =CONCAT('INSERT INTO #EFFECTIVE_PERIOD
                                  (NA_PROJ_DETAILS_SID,
                                  START_PERIOD,
                                  END_PERIOD)
                                 
              SELECT NP.NA_PROJ_DETAILS_SID,
                        MIN(P.PERIOD_SID) AS START_PERIOD,
                        MAX(P1.PERIOD_SID)
              FROM    ',@NA_ASS1_TABLE,' NA
                        JOIN NA_PROJ_DETAILS NP
                           ON NA.NA_PROJ_MASTER_SID = NP.NA_PROJ_MASTER_SID
                        JOIN PERIOD P
                           ON P.YEAR = NA.START_PERIOD
                        JOIN PERIOD P1
                           ON P1.YEAR = NA.END_PERIOD
              WHERE  PRICE_TYPE =''AFSS''
                        AND NA.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID ,'
 
   GROUP BY NP.NA_PROJ_DETAILS_SID');
 
 
  EXEC sp_executesql @SQL_NM_AS;
    
 
    
 
                -----------------------------------------------808 -BUSINESS_UNIT CHG---------------------------------------
                SELECT @BUSINESS_UNIT=BUSINESS_UNIT,
                @COMPANY_ID=NA.COMPANY_MASTER_SID
                FROM dbo.NA_PROJ_MASTER NA
                WHERE NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID;
 
 
              DECLARE  @FCP_ACTUAL_TABLE     VARCHAR(200) = CONCAT('ST_FCP_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
        @FCP_PROJECTION_TABLE VARCHAR(200) = CONCAT('ST_FCP_PROJ_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @NA_ACTUAL_TABLE     VARCHAR(200) = CONCAT('ST_NATIONAL_ASSUMPTIONS_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
        @NA_PROJECTION_TABLE VARCHAR(200) = CONCAT('ST_NATIONAL_ASSUMPTIONS_PROJ_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''));
 
          -------------------------------------------------------------------------------------------- WAC CALCULATION PRIORITY FORECAST WAC
                  IF OBJECT_ID('TEMPDB..#I_PRICING') IS NOT NULL
				  BEGIN
            DROP TABLE #I_PRICING;
			END;
          IF OBJECT_ID('TEMPDB..#ITEM_PRICING') IS NOT NULL
		  BEGIN
            DROP TABLE #ITEM_PRICING;
          END;
            CREATE TABLE #ITEM_PRICING
              (
                 ITEM_MASTER_SID INT NOT NULL,
                 PRICE_TYPE      VARCHAR(25) NULL,
                 PERIOD_SID      INT NULL,
                 ITEM_PRICE      NUMERIC(22, 6) NULL,
                 WAC_INCREASE    NUMERIC(22, 6) NULL
              );
 
          INSERT INTO #ITEM_PRICING
                      (ITEM_MASTER_SID,
                       PRICE_TYPE,
                       PERIOD_SID)
          SELECT PD.ITEM_MASTER_SID,
                 'WAC',
                 PQ.PERIOD_SID
          FROM   #PERIOD_QUARTER PQ
                 CROSS JOIN #PROJECTION_DETAILS PD
          WHERE  PQ.PERIOD_SID BETWEEN @ACT_PERIOD_START_SID - 15 AND @ACT_PERIOD_END_SID
                  OR PQ.PERIOD_SID BETWEEN @PROJ_PERIOD_START_SID AND @PROJ_PERIOD_END_SID;
 
          DECLARE @ITEMID   [DBO].[UDT_ITEM],
                  @ITEM_UOM VARCHAR(50) = 'UN';
 
          INSERT INTO @ITEMID
          SELECT ITEM_MASTER_SID
          FROM   dbo.NA_PROJ_DETAILS
          WHERE  NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID;
 
          DECLARE @FORECAST_NAME    VARCHAR(50),
                  @FORECAST_VERSION VARCHAR(15);
 
          SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME,
                       @FORECAST_VERSION = FT.[VERSION]
          FROM   dbo.FILE_MANAGEMENT FT
                 INNER JOIN dbo.HELPER_TABLE HT
                         ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
          WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE())
                   AND FT.FROM_PERIOD IS NOT NULL )
                 AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE())
                        OR FT.TO_PERIOD IS NULL )
                 AND HT.LIST_NAME = 'FILE_TYPE'
                 AND HT.DESCRIPTION = 'EX-FACTORY SALES'
                           AND FT.BUSINESS_UNIT=@BUSINESS_UNIT -------------GAL-808
                           AND FT.COMPANY=@COMPANY_ID
          ORDER  BY FT.FROM_PERIOD DESC;
 
          SELECT IP.ITEM_MASTER_SID,
                 IP.PRICING_QUALIFIER AS PRICE_TYPE,
                 PQ.PERIOD_SID,
                 ISNULL(IP.ITEM_PRICE,0) AS ACTUAL_PRICE
          INTO   #I_PRICING 
          FROM   UDF_ITEM_PRICING(@ITEMID, 'ANON-FAMP,FCP,TRI-CARE,QFSS,QNON-FAMP,'
                                           + @PRICE_BASIS, @ACT_PERIOD_START_SID - 15, @PROJ_PERIOD_END_SID+2, @ITEM_UOM) IP
                 INNER JOIN #PERIOD_QUARTER PQ
                         ON IP.PERIOD_SID = PQ.PERIOD_SID;
                                        
                     IF Object_id('TEMPDB..#AFSS') IS NOT NULL
					 BEGIN
                       DROP TABLE #AFSS;
					   END;
                     CREATE TABLE #AFSS
                       (
                           ITEM_MASTER_SID INT NOT NULL,
                           PRICE_TYPE      VARCHAR(25) NULL,
                           PERIOD_SID      INT NOT NULL,
                           ITEM_PRICE      NUMERIC(22, 6) NULL,
 
                       );
 
      ;WITH DATA
          AS (SELECT I.ITEM_MASTER_SID,
                      UDF.TOKEN AS PRICING_QUALIFIER,
                      P.PERIOD_SID,
                      P.[YEAR] AS PERIOD_YEAR,
                      P.[QUARTER] AS PERIOD_QUARTER,
                      P.[MONTH] AS PERIOD_MONTH
               FROM   @ITEMID I
                      CROSS JOIN dbo.PERIOD P
                      CROSS JOIN DBO.UDF_SPLITSTRING('AFSS', ',') UDF
               WHERE  P.PERIOD_SID BETWEEN  @ACT_PERIOD_START_SID - 12 AND  @PROJ_PERIOD_END_SID+2),
           ITEM_PRICING_DATA
           AS (SELECT A.ITEM_MASTER_SID,
                      A.ITEM_PRICE,
                      A.PRICING_QUALIFIER,
                      P.PERIOD_SID,
                                    YEAR(A.START_DATE) AS START_YEAR,
                      ROW_NUMBER()
                            OVER(
                              PARTITION BY A.ITEM_MASTER_SID, A.PRICING_QUALIFIER,YEAR(A.START_DATE)
                              ORDER BY A.START_DATE DESC,A.CREATED_DATE DESC) AS RN -- Based on GALUAT-123 (also this considers there will be WAC price at first day of month)
               FROM   (SELECT IP.ITEM_MASTER_SID,
                              IP.ITEM_PRICE,
                              IPQ.PRICING_QUALIFIER,
                              IP.START_DATE,
                              ISNULL(IP.END_DATE, LEAD(DATEADD(DD, -1, IP.START_DATE), 1, NULL)
                                                            OVER(
                                                              PARTITION BY IP.ITEM_MASTER_SID,IPQ.PRICING_QUALIFIER
                                                              ORDER BY IP.[START_DATE])) AS END_DATE,
                                                                                                         ip.CREATED_DATE
                       FROM   DBO.ITEM_PRICING IP
                              JOIN DBO.ITEM_PRICING_QUALIFIER IPQ
                                ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                              INNER JOIN dbo.HELPER_TABLE HT
                                      ON HT.HELPER_TABLE_SID = IP.ITEM_UOM
                                         AND HT.LIST_NAME = 'UOM'
                       WHERE  EXISTS (SELECT 1
                                      FROM   DBO.UDF_SPLITSTRING('AFSS', ',') F
                                      WHERE  F.TOKEN = IPQ.PRICING_QUALIFIER)
                              AND HT.DESCRIPTION = @ITEM_UOM
                              AND EXISTS (SELECT 1
                                          FROM   @ITEMID ID
                                          WHERE  ID.ITEM_MASTER_SID = IP.ITEM_MASTER_SID)
                              AND IP.INBOUND_STATUS <> 'D') A
                      JOIN dbo.PERIOD P
                        ON A.START_DATE <= P.PERIOD_DATE
                           AND ( A.END_DATE >= P.PERIOD_DATE
                                  OR A.END_DATE IS NULL )
               WHERE  P.PERIOD_SID BETWEEN @ACT_PERIOD_START_SID - 12 AND  @PROJ_PERIOD_END_SID+2)
                       
                     INSERT INTO #AFSS
                                         (ITEM_MASTER_SID,
                                         PERIOD_SID,
                                         PRICE_TYPE,
                                         ITEM_PRICE)
      SELECT
         D.ITEM_MASTER_SID,
             MIN(D.PERIOD_SID),
             (D.PRICING_QUALIFIER),
             COALESCE(MAX(IPD.ITEM_PRICE), 0) AS ITEM_PRICE
      FROM   DATA D
                JOIN dbo.PERIOD P ON P.PERIOD_SID=D.PERIOD_SID
          LEFT OUTER JOIN ITEM_PRICING_DATA IPD
                          ON IPD.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                           AND IPD.PRICING_QUALIFIER = D.PRICING_QUALIFIER
                          AND IPD.PERIOD_SID = D.PERIOD_SID
                      and ipd.RN = 1
                     GROUP BY D.ITEM_MASTER_SID,D.PRICING_QUALIFIER,D.PERIOD_YEAR;
 
 
                           INSERT INTO #I_PRICING
                           (
 
                           ITEM_MASTER_SID,
                           PRICE_TYPE,
                           PERIOD_SID,
                           ACTUAL_PRICE
                           )
 
                           SELECT ITEM_MASTER_SID,
                                     PRICE_TYPE,    
                                     PERIOD_SID ,   
                                     ITEM_PRICE
                                     FROM
                                     
                                     
                                     #AFSS WHERE  PRICE_TYPE='AFSS';
DECLARE @SQL NVARCHAR(MAX);
 
     IF @PRICE_BASIS IN( 'EQWAC', 'BQWAC', 'MQWAC', 'AVGQWAC' )
        BEGIN
 
       SET @sql = 'UPDATE IP
                SET    IP.ITEM_PRICE = COALESCE(WAC,0)
                FROM   #ITEM_PRICING IP
                           INNER JOIN (SELECT DISTINCT ITEM_MASTER_SID,UPPS FROM  #PROJECTION_DETAILS) PD ON PD.ITEM_MASTER_SID=IP.ITEM_MASTER_SID
                       INNER JOIN (SELECT ITEM_MASTER_SID,PERIOD_SID,''WAC'' AS PRICE_TYPE,'+@PRICE_BASIS+' AS WAC  FROM '+ @NA_NDC11_WAC
               + ') A
                               ON IP.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                                  AND IP.PERIOD_SID = A.PERIOD_SID
                                  AND IP.PRICE_TYPE = A.PRICE_TYPE';
   
    EXEC sp_executesql @sql;
   
        END;
 
      ELSE IF @PRICE_BASIS = 'DAY WEIGHTED WAC'
        BEGIN
 
                     SET @SQL = 'UPDATE IP
                SET    IP.ITEM_PRICE = COALESCE(WAC,0)
                FROM   #ITEM_PRICING IP
                           INNER JOIN (SELECT DISTINCT ITEM_MASTER_SID,UPPS FROM  #PROJECTION_DETAILS) PD ON PD.ITEM_MASTER_SID=IP.ITEM_MASTER_SID
                       INNER JOIN (SELECT ITEM_MASTER_SID,PERIOD_SID,''WAC'' AS PRICE_TYPE,DAY_WEIGHTED_WAC AS WAC  FROM '+ @NA_NDC11_WAC
               + ') A
                               ON IP.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                                  AND IP.PERIOD_SID = A.PERIOD_SID
                                  AND IP.PRICE_TYPE = A.PRICE_TYPE';
             
 
                                                  EXEC sp_executesql @sql;
 
        END;
      ELSE IF @PRICE_BASIS = 'SALES WEIGHTED WAC'
        BEGIN
 
                     SET @SQL = 'UPDATE IP
                SET    IP.ITEM_PRICE = COALESCE(WAC,0)
                FROM   #ITEM_PRICING IP
                           INNER JOIN (SELECT DISTINCT ITEM_MASTER_SID,UPPS FROM  #PROJECTION_DETAILS) PD ON PD.ITEM_MASTER_SID=IP.ITEM_MASTER_SID
                       INNER JOIN (SELECT ITEM_MASTER_SID,PERIOD_SID,''WAC'' AS PRICE_TYPE,SALES_WEIGHTED_WAC AS WAC  FROM '+ @NA_NDC11_WAC
               + ') A
                               ON IP.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                                  AND IP.PERIOD_SID = A.PERIOD_SID
                                  AND IP.PRICE_TYPE = A.PRICE_TYPE';
                                               
                                                  EXEC sp_executesql @sql;
        END;
 
          ;WITH WAC_INCREASE_UPDATE_CTE
               AS (SELECT ROW_NUMBER()
                            OVER(
                              PARTITION BY ITEM_MASTER_SID
                              ORDER BY PERIOD_SID ASC) RN,
                          ITEM_MASTER_SID,
                          PRICE_TYPE,
                          PERIOD_SID,
                          ITEM_PRICE,
                          WAC_INCREASE
                   FROM   #ITEM_PRICING)
          UPDATE C
          SET    C.WAC_INCREASE = ISNULL(D.WAC_INCREASE, 0)
          FROM   WAC_INCREASE_UPDATE_CTE C
                 INNER JOIN (SELECT A.ITEM_MASTER_SID,
                                    A.PRICE_TYPE,
                                    A.PERIOD_SID,
                                    ISNULL(A.ITEM_PRICE,0) ITEM_PRICE,
                                    COALESCE((( ( A.ITEM_PRICE - B.ITEM_PRICE ) / NULLIF(B.ITEM_PRICE, 0) )) * 100,0) AS WAC_INCREASE
                             FROM   WAC_INCREASE_UPDATE_CTE A
                                    LEFT OUTER JOIN WAC_INCREASE_UPDATE_CTE B
                                                 ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                                    AND A.RN = B.RN + 1) D
                         ON C.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                            AND C.PERIOD_SID = D.PERIOD_SID;
 
          ---------------------------------------------------------------------------- REMOVE ALL PRODUCT FROM ACTUAL TABLE
                DECLARE @DEL NVARCHAR(MAX)='';
          SET @DEL=CONCAT('TRUNCATE TABLE ',@FCP_ACTUAL_TABLE,'');
                EXEC sp_executesql @DEL;
         
 
          ------------------------------------------------------------------------  RE-INSERT EXISTING PRODUCT WITH NEW PRODUCT IN ACTUAL TABLE QUARTERLY 
                SET @SQL ='';              
                --------405
          SET @SQL=CONCAT('INSERT INTO ',@FCP_ACTUAL_TABLE,'
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
                                           (''QFSS''),
                                           (''QNON-FAMP''),
                                           (''WAC INCREASE %''),
                                           (''CMS UNITS''),
                                           (''WAC-FSS''),
                                           (''WAC-FCP''))V(PRICE_TYPE)) PT
          WHERE  P.PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' AND ',@ACT_PERIOD_END_SID,'');
              -- select @SQL
 
                EXEC sp_executesql @sql;
 
                 DECLARE @SQL_FCP_UPD NVARCHAR(MAX)='';
          SET @SQL_FCP_UPD=CONCAT('UPDATE FA
          SET    ACTUAL_PRICE = ISNULL(UPPS,0)
          FROM   ',@FCP_ACTUAL_TABLE,' FA
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON FA.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
          WHERE  FA.PRICE_TYPE = ''CMS UNITS''');
       --select @SQL_FCP_UPD
 
              EXEC sp_executesql @SQL_FCP_UPD;
          ------------------------------------------------------------------------  RE-INSERT EXISTING PRODUCT WITH NEW PRODUCT IN ACTUAL TABLE YEARLY 
          DECLARE @SQL1 NVARCHAR(MAX)='';
 
                SET @SQL1=CONCAT('INSERT INTO ',@FCP_ACTUAL_TABLE,'
                      (NA_PROJ_DETAILS_SID,
                       PERIOD_SID,
                       PRICE_TYPE,
                       ACTUAL_PRICE)
          SELECT PD.NA_PROJ_DETAILS_SID,
                 MIN(P.PERIOD_SID) + 12,
                 PT.PRICE_TYPE,
                 0
          FROM   #PERIOD_QUARTER P
                 CROSS JOIN #PROJECTION_DETAILS PD
                 CROSS JOIN (SELECT PRICE_TYPE
                             FROM   (VALUES(''AFSS''),
                                           (''ANON-FAMP''),
                                           (''CPI-U''),
                                           (''FCP''),
                                           (''TRI-CARE''),
                                           (''AVGY''),
                                           (''CPI-URA''),
                                                                        (''FCP Discount''),
                                                                        (''FSS (OGA) Discount''))V(PRICE_TYPE)) PT
          WHERE  P.PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' AND ',@ACT_PERIOD_END_SID,'
          GROUP  BY NA_PROJ_DETAILS_SID,
                    PT.PRICE_TYPE,
                    P.PERIOD_YEAR'
                                  );
                                  ---select @SQL1
                                  EXEC sp_executesql @SQL1;
          --------------------------------------------------------------------------------------------- QUARTER WISE ACTUAL PRICE FOR PRICE_TYPE('WAC'),('FSS'),('NON-FAMP') AND ('WAC INCREASE %')
             DECLARE @SQL_ITEM_NA NVARCHAR(MAX)='';
 
                SET @SQL_ITEM_NA=CONCAT('INSERT INTO #ITEM_PRICING
                      (ITEM_MASTER_SID,
                       PRICE_TYPE,
                       PERIOD_SID,
                       ITEM_PRICE)
          SELECT A.ITEM_MASTER_SID,
                 A.PRICE_TYPE,
                 A.PERIOD_SID,
                 ISNULL(A.ACTUAL_PRICE,0) AS ITEM_PRICE
          FROM   (SELECT ITEM_MASTER_SID,
                         PRICE_TYPE=CASE
                                      WHEN PRICE_TYPE = ''FSS(OGA)'' THEN ''QFSS''
                                      WHEN PRICE_TYPE = ''NON-FAMP'' THEN ''QNON-FAMP''
                                      ELSE PRICE_TYPE
                                    END,
                         PERIOD_SID,
                         ACTUAL_PRICE
                  FROM   ',@NA_ACTUAL_TABLE,' NAA
                  WHERE  EXISTS (SELECT 1
                                 FROM   #PROJECTION_DETAILS PD
                                 WHERE  PD.ITEM_MASTER_SID = NAA.ITEM_MASTER_SID)
                         AND PRICE_TYPE IN ( ''FSS(OGA)'', ''NON-FAMP'' )
                         AND PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' AND ',@ACT_PERIOD_END_SID,')A
          UNION ALL
          SELECT ITEM_MASTER_SID,
                 PRICE_TYPE,
                 PERIOD_SID,
                 ACTUAL_PRICE
          FROM   #I_PRICING
          WHERE  PRICE_TYPE IN ( ''QFSS'', ''QNON-FAMP'' )
                 AND PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' - 15 AND ',@ACT_PERIOD_START_SID,' - 3');
                           ---select @SQL_ITEM_NA
                           EXEC sp_executesql @SQL_ITEM_NA;
 
              DECLARE @SQL_FCP_UPD1 NVARCHAR(MAX);
 
        SET @SQL_FCP_UPD1=CONCAT('UPDATE FA
          SET    FA.ACTUAL_PRICE = IP.ITEM_PRICE
          FROM   ',@FCP_ACTUAL_TABLE,' FA
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON FA.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
                 INNER JOIN #ITEM_PRICING IP
                         ON IP.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
                            AND IP.PRICE_TYPE = FA.PRICE_TYPE
                            AND IP.PERIOD_SID = FA.PERIOD_SID
          WHERE  FA.PRICE_TYPE IN ( ''QFSS'', ''QNON-FAMP'', ''WAC'' )');
              --- select @SQL_FCP_UPD1
                EXEC sp_executesql @SQL_FCP_UPD1;
 
                DECLARE @SQL_FCP_UPD2 NVARCHAR(MAX);
 
         SET @SQL_FCP_UPD2=CONCAT('UPDATE FA
          SET    FA.ACTUAL_PRICE = IP.WAC_INCREASE
          FROM   ',@FCP_ACTUAL_TABLE,'  FA
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON FA.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
                 INNER JOIN (SELECT PRICE_TYPE = ''WAC INCREASE %'',
                                    ITEM_MASTER_SID,
                                    PERIOD_SID,
                                    WAC_INCREASE
                             FROM   #ITEM_PRICING
                             WHERE  PRICE_TYPE = ''WAC''
                                    AND PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' AND ',@ACT_PERIOD_END_SID,')IP
                         ON IP.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
                            AND IP.PRICE_TYPE = FA.PRICE_TYPE
                            AND IP.PERIOD_SID = FA.PERIOD_SID');
                                                ---select @SQL_FCP_UPD2
                                                EXEC sp_executesql @SQL_FCP_UPD2;
          ---------------------------------------------------------------------------------------------------------- TO FETCH EACH PRODUCT AND PERIOD WHICH IS NOT EXISTS IN PROJECTION TABLE AND INSERT NEW PRODUCT IN PROJECTION TABLE(QUARTERLY)
            DECLARE @SQL2 NVARCHAR(MAX);
               
               ----------cel-405
                SET @SQL2=CONCAT('INSERT INTO ',@FCP_PROJECTION_TABLE,'
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
                                                   (''QFSS''),
                                                   (''QNON-FAMP''),
                                                   (''WAC INCREASE %''),
                                                   (''CMS UNITS''),
                                                   (''WAC-FSS''),
                                                   (''WAC-FCP''))V(PRICE_TYPE)) PT
                  WHERE  PQ.PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' AND ',@PROJ_PERIOD_END_SID,'
                  EXCEPT
                  SELECT FP.NA_PROJ_DETAILS_SID,
                         FP.PERIOD_SID,
                         PRICE_TYPE
                  FROM   ',@FCP_PROJECTION_TABLE,' FP
                         INNER JOIN #PROJECTION_DETAILS PD
                                 ON FP.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
                  WHERE  PD.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,') A
 
          ---------------------------------------------------------------------------------------------------------- TO FETCH EACH PRODUCT AND PERIOD WHICH IS NOT EXISTS IN PROJECTION TABLE AND INSERT NEW PRODUCT IN PROJECTION TABLE(YEARLY)
          INSERT INTO ',@FCP_PROJECTION_TABLE,'
                      (NA_PROJ_DETAILS_SID,
                       PERIOD_SID,
                       PRICE_TYPE)
          SELECT PD.NA_PROJ_DETAILS_SID,
                 PERIOD_SID=MIN(PERIOD_SID) + 12,
                 PRICE_TYPE
          FROM   #PROJECTION_DETAILS PD
                 CROSS JOIN #PERIOD_QUARTER PQ
                 CROSS JOIN (SELECT PRICE_TYPE
                             FROM   (VALUES (''ANON-FAMP''),
                                            (''CPI-U''),
                                            (''FCP''),
                                            (''TRI-CARE''),
                                            (''AVGY''),
                                            (''CPI-URA''),
                                            (''AFSS''),
                                            (''CPI-U ADJUSTMENT''),
                                            (''MAX FSS''),
                                                                        (''FCP Discount''),
                                                                        (''FSS (OGA) Discount''))V(PRICE_TYPE)) PT
          WHERE  PQ.PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' + ( ( ( ( ',@PROJ_PERIOD_START_SID%12,' ) - 1 ) + 1 ) * -1 ) + 1 AND ',@PROJ_PERIOD_END_SID,'
          GROUP  BY PD.NA_PROJ_DETAILS_SID,
                    PRICE_TYPE,
                    PQ.PERIOD_YEAR
          EXCEPT
          SELECT FP.NA_PROJ_DETAILS_SID,
                 FP.PERIOD_SID,
                 FP.PRICE_TYPE
          FROM   ',@FCP_PROJECTION_TABLE,' FP
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON FP.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
          WHERE  PD.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,'
                 AND PRICE_TYPE IN ( ''ANON-FAMP'', ''CPI-U'', ''FCP'', ''TRI-CARE'',
                                     ''AVGY'', ''CPI-URA'', ''AFSS'', ''CPI-U ADJUSTMENT'', ''MAX FSS'',''FSS (OGA) Discount'',''FCP Discount'' )');
                                                              ---select @SQL2
       EXEC sp_executesql @SQL2;
 
       DECLARE @SQL2_FCP_PROJ_UPD NVARCHAR(MAX)='';
 
        SET @SQL2_FCP_PROJ_UPD=CONCAT('UPDATE FA
          SET    PROJECTION_PRICE = ISNULL(UPPS,0)
          FROM   ',@FCP_PROJECTION_TABLE,' FA
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON FA.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
          WHERE  FA.PRICE_TYPE = ''CMS UNITS''');
              --select @SQL2_FCP_PROJ_UPD
                EXEC sp_executesql @SQL2_FCP_PROJ_UPD;
 
          ------------------------------------------------------------------------------------------------------------ FETCHING PROJECTION PRICE FROM NATIONAL ASSUMPTION PROJECTION TABLE AND INSERTING INTO FCP PROJECTION TABLE
        
               DECLARE @SQL_MERGE_NA NVARCHAR(MAX)='';
              SET @SQL_MERGE_NA=CONCAT('MERGE #ITEM_PRICING AS TARGET
          USING (SELECT ITEM_MASTER_SID,
                        PRICE_TYPE= CASE
                                      WHEN PRICE_TYPE = ''FSS(OGA)'' THEN ''QFSS''
                                      WHEN PRICE_TYPE = ''NON-FAMP'' THEN ''QNON-FAMP''
                                      ELSE PRICE_TYPE
                                    END,
                        PERIOD_SID,
                        ISNULL(PROJECTION_PRICE,0) PROJECTION_PRICE
                 FROM   ',@NA_PROJECTION_TABLE,' NAP
                 WHERE  EXISTS (SELECT 1
                                FROM   #PROJECTION_DETAILS PD
                                WHERE  PD.ITEM_MASTER_SID = NAP.ITEM_MASTER_SID)
                        AND PRICE_TYPE IN ( ''FSS(OGA)'', ''NON-FAMP'', ''CPI-U'' )
                        AND PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' AND ',@PROJ_PERIOD_END_SID,') AS SOURCE
          ON ( TARGET.ITEM_MASTER_SID = SOURCE.ITEM_MASTER_SID
               AND TARGET.PERIOD_SID = SOURCE.PERIOD_SID
               AND TARGET.PRICE_TYPE = SOURCE.PRICE_TYPE )
          WHEN MATCHED THEN
            UPDATE SET TARGET.ITEM_PRICE = SOURCE.PROJECTION_PRICE
          WHEN NOT MATCHED THEN
            INSERT (ITEM_MASTER_SID,           
                    PRICE_TYPE,
                    PERIOD_SID,
                    ITEM_PRICE)
            VALUES (SOURCE.ITEM_MASTER_SID,
                    SOURCE.PRICE_TYPE,
                    SOURCE.PERIOD_SID,
                    SOURCE.PROJECTION_PRICE)
                                  ;');
                                  ---select @SQL_MERGE_NA
                                  EXEC sp_executesql @SQL_MERGE_NA;
                                 
     DECLARE @SQL2_FCP_PROJ_UPD1 NVARCHAR(MAX)='';
          SET @SQL2_FCP_PROJ_UPD1=CONCAT('UPDATE FP
          SET    FP.PROJECTION_PRICE = IP.ITEM_PRICE
          FROM   ',@FCP_PROJECTION_TABLE,' FP
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON FP.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
                 INNER JOIN #ITEM_PRICING IP
                         ON IP.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
                            AND IP.PRICE_TYPE = FP.PRICE_TYPE
                            AND IP.PERIOD_SID = FP.PERIOD_SID
          WHERE  FP.PRICE_TYPE IN ( ''QFSS'', ''QNON-FAMP'', ''WAC'' )');
              ---select @SQL2_FCP_PROJ_UPD1
              EXEC sp_executesql @SQL2_FCP_PROJ_UPD1;
 
       DECLARE @SQL2_FCP_PROJ_UPD2 NVARCHAR(MAX)=''; 
              
                SET @SQL2_FCP_PROJ_UPD2=CONCAT('UPDATE FP
          SET    FP.PROJECTION_PRICE = ISNULL(IP.WAC_INCREASE,0)
          FROM   ',@FCP_PROJECTION_TABLE,' FP
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON FP.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
                 INNER JOIN (SELECT PRICE_TYPE = ''WAC INCREASE %'',
                                    ITEM_MASTER_SID,
                                    PERIOD_SID,
                                    WAC_INCREASE
                             FROM   #ITEM_PRICING
                             WHERE  PRICE_TYPE = ''WAC''
                                    AND PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' AND ',@PROJ_PERIOD_END_SID,')IP
                         ON IP.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
                            AND IP.PRICE_TYPE = FP.PRICE_TYPE
                            AND IP.PERIOD_SID = FP.PERIOD_SID');
                                         --select @SQL2_FCP_PROJ_UPD2
                                                EXEC sp_executesql @SQL2_FCP_PROJ_UPD2;
          ----------------------------------------------------- CPI-U PERCENTAGE INCREASE ONLY FOR THIRD QUARTER
          ------IF((((THIRD QUARTER CPI-U)-(PREVIOUS YEAR THIRD QUARTER))/(PREVIOUS YEAR THIRD QUARTER))<0 THEN 0 ELSE ((G14-B14)/B14))
          ---------------------------FETCH PRICE FROM EACH YEAR 3RD QUARTER AND CALCULATE CPI_U_INCREASE_%
          IF OBJECT_ID('TEMPDB..#QUARTER_PRICE') IS NOT NULL
		  BEGIN
            DROP TABLE #QUARTER_PRICE;
          END;
            CREATE TABLE #QUARTER_PRICE
              (
                 NA_PROJ_DETAILS_SID          INT NOT NULL,
                 PERIOD_SID                   INT NOT NULL,
                 CPI_U_THIRD_QUARTER_PRICE    NUMERIC(22, 6) NULL,
                 CPI_INCREASE_PERCENT         NUMERIC(22, 6) NULL,
                 NON_FAMP_THIRD_QUARTER_PRICE NUMERIC(22, 6) NULL,
                 ADDITIONAL_DISCOUNT          NUMERIC(22, 6) NULL,
                 PERIOD_DATE                  DATETIME		 NULL
              );
 
          INSERT INTO #QUARTER_PRICE
                      (NA_PROJ_DETAILS_SID,
                       PERIOD_SID,
                       PERIOD_DATE,
                       CPI_U_THIRD_QUARTER_PRICE)
          SELECT PD.NA_PROJ_DETAILS_SID,
                 PQ.PERIOD_SID,
                 PQ.PERIOD_DATE,
                 0
          FROM   #PERIOD_QUARTER PQ
                 CROSS JOIN #PROJECTION_DETAILS PD
          WHERE  PQ.PERIOD_SID BETWEEN @ACT_PERIOD_START_SID - 6 AND @PROJ_PERIOD_END_SID
                 AND PQ.PERIOD_QUARTER = 3
                           ;
                           DECLARE @SQL_CTE_NA NVARCHAR(MAX)='';
 
        SET @SQL_CTE_NA=CONCAT(' WITH CTE
               AS (SELECT ROW_NUMBER()
                            OVER (
                              PARTITION BY NA_PROJ_DETAILS_SID, PERIOD_SID
                              ORDER BY NUM DESC) RN,
                          NA_PROJ_DETAILS_SID,
                          PERIOD_SID,
                          THIRD_QUARTER_PRICE
                   FROM   (SELECT PD.NA_PROJ_DETAILS_SID,
                                  NAA.PERIOD_SID,
                                  ISNULL(NAA.ACTUAL_PRICE,0) AS THIRD_QUARTER_PRICE,
                                  1                AS NUM
                           FROM   ',@NA_ACTUAL_TABLE,' NAA
                                  INNER JOIN #PERIOD_QUARTER PQ
                                          ON NAA.PERIOD_SID = PQ.PERIOD_SID
                                             AND PQ.[PERIOD_QUARTER] = 3
                                  INNER JOIN #PROJECTION_DETAILS PD
                                          ON PD.ITEM_MASTER_SID = NAA.ITEM_MASTER_SID
                                             AND PD.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,'
                           WHERE  NAA.PRICE_TYPE = ''CPI-U''
                                  AND NAA.PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' AND ',@ACT_PERIOD_END_SID,'
                           UNION ALL
                           SELECT NA_PROJ_DETAILS_SID,
                                  PERIOD_SID,
                                  ISNULL(INDEX_VALUE,0),
                                  NUM
                           FROM   (SELECT TOP 1 NA_PROJ_DETAILS_SID,
                                                PERIOD_SID,
                                                INDEX_VALUE,
                                                1 AS NUM
                                   FROM   CPI_INDEX_MASTER CIM
                                          RIGHT JOIN #PERIOD_QUARTER PQ
                                                  ON PQ.PERIOD_QUARTER = DATEPART(QUARTER, CIM.EFFECTIVE_DATE)
                                                     AND PQ.PERIOD_YEAR = DATEPART(YEAR, CIM.EFFECTIVE_DATE)
                                                     AND INDEX_TYPE = ''CPI''
                                          CROSS JOIN #PROJECTION_DETAILS PD
                                   WHERE  PQ.PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' - 12 AND ',@ACT_PERIOD_START_SID,'
                                          AND PQ.[PERIOD_QUARTER] = 3
                                   ORDER  BY PERIOD_SID DESC) A
                           UNION ALL
                           SELECT PD.NA_PROJ_DETAILS_SID,
                                  NAP.PERIOD_SID,
                                  ISNULL(NAP.PROJECTION_PRICE,0) AS THIRD_QUARTER_PRICE,
                                  2                    AS NUM
                           FROM  ',@NA_PROJECTION_TABLE,' NAP
                                  INNER JOIN #PERIOD_QUARTER PQ
                                          ON NAP.PERIOD_SID = PQ.PERIOD_SID
                                             AND PQ.[PERIOD_QUARTER] = 3
                                  INNER JOIN #PROJECTION_DETAILS PD
                                          ON PD.ITEM_MASTER_SID = NAP.ITEM_MASTER_SID
                                             AND PD.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,'
                           WHERE  NAP.PRICE_TYPE = ''CPI-U''
                                  AND NAP.PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' AND ',@PROJ_PERIOD_END_SID,')OU)
          UPDATE QP
          SET    QP.CPI_U_THIRD_QUARTER_PRICE = ISNULL(C.THIRD_QUARTER_PRICE,0)
          FROM   #QUARTER_PRICE QP
                 INNER JOIN CTE C
                         ON QP.NA_PROJ_DETAILS_SID = C.NA_PROJ_DETAILS_SID
                            AND QP.PERIOD_SID = C.PERIOD_SID
          WHERE  RN = 1'
                );
              ---select @SQL_CTE_NA
              EXEC sp_executesql @SQL_CTE_NA;
                ;
                    
                WITH CPI_U_INCREASE
               AS (SELECT ROW_NUMBER()
                            OVER(
                              PARTITION BY NA_PROJ_DETAILS_SID
                              ORDER BY PERIOD_SID ASC) RN,
                          NA_PROJ_DETAILS_SID,
                          PERIOD_SID,
                          CPI_U_THIRD_QUARTER_PRICE,
                          CPI_INCREASE_PERCENT
                   FROM   #QUARTER_PRICE)
          UPDATE CUI
          SET    CUI.CPI_INCREASE_PERCENT = CASE
                                              WHEN D.CPI_INCREASE_PERCENT < 0 THEN 0
                                              ELSE D.CPI_INCREASE_PERCENT
                                            END
          FROM   CPI_U_INCREASE CUI
                 INNER JOIN (SELECT A.NA_PROJ_DETAILS_SID,
                                    A.PERIOD_SID,
                                    A.CPI_U_THIRD_QUARTER_PRICE,
                                    COALESCE(( ( A.CPI_U_THIRD_QUARTER_PRICE - B.CPI_U_THIRD_QUARTER_PRICE ) / NULLIF(B.CPI_U_THIRD_QUARTER_PRICE, 0) ) * 100,0) AS CPI_INCREASE_PERCENT
                             FROM   CPI_U_INCREASE A
                                    LEFT OUTER JOIN CPI_U_INCREASE B
                                                 ON A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID
                                                    AND A.RN = B.RN + 1) D
                         ON CUI.NA_PROJ_DETAILS_SID = D.NA_PROJ_DETAILS_SID
                            AND CUI.PERIOD_SID = D.PERIOD_SID;
          ---------------------------------------------------------------- ADDITIONAL DISCOUNT ONLY FOR THIRD QUARTER
          ----IF(((SAME YEAR THIRD QUARTER NON-FAMP)-((PREVIOUS YEAR THIRD QUARTER NON-FAMP)*(1+(SAME YEAR CPI-U PERCENTAGE INCREASE))))<0,0,(L10-(G10*(1+L15))))
          
                 DECLARE @SQL_CTE_NA_1 NVARCHAR(MAX)='';
        
          SET @SQL_CTE_NA_1=CONCAT('WITH CTE
               AS (SELECT ROW_NUMBER()
                            OVER (
                              PARTITION BY NA_PROJ_DETAILS_SID, PERIOD_SID
                              ORDER BY NUM DESC) RN,
                          ISNULL(ITEM_PRICE,0) ITEM_PRICE,
                          NA_PROJ_DETAILS_SID,
                          PERIOD_SID
                   FROM   (SELECT PD.NA_PROJ_DETAILS_SID,
                                  NAA.ACTUAL_PRICE AS ITEM_PRICE,
                                  NAA.PERIOD_SID,
                                  1                AS NUM
                           FROM   ',@NA_ACTUAL_TABLE,' NAA
                                  INNER JOIN #PERIOD_QUARTER PQ
                                          ON NAA.PERIOD_SID = PQ.PERIOD_SID
                                             AND PQ.[PERIOD_QUARTER] = 3
                                  INNER JOIN #PROJECTION_DETAILS PD
                                          ON PD.ITEM_MASTER_SID = NAA.ITEM_MASTER_SID
                                             AND PD.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,'
                           WHERE  NAA.PRICE_TYPE = ''NON-FAMP''
                                  AND NAA.PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' AND ',@ACT_PERIOD_END_SID,'
                           UNION ALL
                           SELECT ITEM_MASTER_SID,
                                  ISNULL(ACTUAL_PRICE,0) ACTUAL_PRICE,
                                  I.PERIOD_SID,
                                  1 AS NUM
                           FROM   #I_PRICING I
                                  INNER JOIN #PERIOD_QUARTER PQ
                                          ON PQ.PERIOD_SID = I.PERIOD_SID
                           WHERE  PRICE_TYPE = ''QNON-FAMP''
                                  AND PQ.PERIOD_QUARTER = 3
                                  AND I.PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' - 15 AND ',@ACT_PERIOD_START_SID,' - 3
                           UNION ALL
                           SELECT PD.NA_PROJ_DETAILS_SID,
                                  ISNULL(NAP.PROJECTION_PRICE,0) AS ITEM_PRICE,
                                  NAP.PERIOD_SID,
                                  2                    AS NUM
                           FROM   ',@NA_PROJECTION_TABLE,' NAP
                                 INNER JOIN #PERIOD_QUARTER PQ
                                          ON NAP.PERIOD_SID = PQ.PERIOD_SID
                                             AND PQ.[PERIOD_QUARTER] = 3
                                  INNER JOIN #PROJECTION_DETAILS PD
                                          ON PD.ITEM_MASTER_SID = NAP.ITEM_MASTER_SID
                                             AND PD.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,'
                           WHERE  NAP.PRICE_TYPE = ''NON-FAMP''
                                  AND NAP.PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' AND ',@PROJ_PERIOD_END_SID,')A)
          UPDATE PPT
          SET    NON_FAMP_THIRD_QUARTER_PRICE = ISNULL(P.ITEM_PRICE,0)
          FROM   #QUARTER_PRICE PPT
                 INNER JOIN CTE P
                         ON PPT.NA_PROJ_DETAILS_SID = P.NA_PROJ_DETAILS_SID
                            AND PPT.PERIOD_SID = P.PERIOD_SID
          WHERE  RN = 1'
                );
              -- SELECT @SQL_CTE_NA_1
              EXEC sp_executesql @SQL_CTE_NA_1
                ;
 
          WITH ADDITIONAL_DISCOUNT
               AS (SELECT ROW_NUMBER()
                            OVER(
                              PARTITION BY NA_PROJ_DETAILS_SID
                              ORDER BY PERIOD_SID ASC) RN,
                          NA_PROJ_DETAILS_SID,
                          PERIOD_SID,
                          CPI_INCREASE_PERCENT,
                          NON_FAMP_THIRD_QUARTER_PRICE,
                          ADDITIONAL_DISCOUNT
                   FROM   #QUARTER_PRICE)
          UPDATE AD
          SET    AD.ADDITIONAL_DISCOUNT = CASE
                                            WHEN D.ADDITIONAL_DISCOUNT < 0 THEN 0
                                            ELSE D.ADDITIONAL_DISCOUNT
                                          END
          FROM   ADDITIONAL_DISCOUNT AD
                 INNER JOIN (SELECT A.NA_PROJ_DETAILS_SID,
                                    A.PERIOD_SID,
                                    ISNULL(A.NON_FAMP_THIRD_QUARTER_PRICE,0) NON_FAMP_THIRD_QUARTER_PRICE,
                                    A.CPI_INCREASE_PERCENT,
                                    ISNULL((( A.NON_FAMP_THIRD_QUARTER_PRICE - ( B.NON_FAMP_THIRD_QUARTER_PRICE * ( 1 + ( A.CPI_INCREASE_PERCENT / 100.0 ) ) ) )),0) AS ADDITIONAL_DISCOUNT
                             FROM   ADDITIONAL_DISCOUNT A
                                    LEFT OUTER JOIN ADDITIONAL_DISCOUNT B
                                                 ON A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID
                                                    AND A.RN = B.RN + 1) D
                         ON AD.NA_PROJ_DETAILS_SID = D.NA_PROJ_DETAILS_SID
                            AND AD.PERIOD_SID = D.PERIOD_SID;
 
          ------------- GROSS TRADE SALES FOR PROJECTION = FORECASTING MASTER SUM OF 3 MONTHS AND FOR HISTORICAL = FUNCTION BASED ON GL_BALANCE_MASTER
          DECLARE @FROM_DATE DATETIME;
          DECLARE @TO_DATE DATETIME;
 
          --SET @FROM_DATE =@ACTUAL_START_DATE
          SET @TO_DATE=@PROJECTION_END_DATE;
 
          SELECT @FROM_DATE = CASE
                                WHEN DATEPART(QQ, @PROJECTION_START_DATE) IN ( 1, 2, 3 ) THEN CONVERT(DATE, CONVERT(VARCHAR(30), YEAR(@PROJECTION_START_DATE) - 2)
                                                                                                            + '-10-01')
                                ELSE @PROJECTION_START_DATE
                              END;
 
          DECLARE @FROM_PERIOD_SID INT;
 
          SELECT @FROM_PERIOD_SID = PERIOD_SID
          FROM   dbo.PERIOD
          WHERE  PERIOD_DATE = @FROM_DATE;
 
          IF OBJECT_ID('TEMPDB..#GROSS_TRADE_SALES') IS NOT NULL
		  BEGIN
            DROP TABLE #GROSS_TRADE_SALES;
          END;
            CREATE TABLE #GROSS_TRADE_SALES
              (
                 GROSS_TRADE_SALES       NUMERIC(22, 6) NULL,
                 GROSS_TRADE_UNITS       NUMERIC(22, 6) NULL,
                 PERIOD_SID              INT NOT NULL,
                 ITEM_MASTER_SID         INT NOT NULL,
                 PERIOD_DATE             DATETIME NULL,
                 PERCENT_OF_BUSINESS     NUMERIC(22, 6) NULL,
                 SALES_WEIGHTED_NON_FAMP NUMERIC(22, 6) NULL
              );
 
          WITH GTS
               AS (SELECT SUM(COALESCE(GTS.FORECAST_GTS_SALES, GTS.ACTUAL_GTS_SALES)) AS GROSS_TRADE_SALES,
                          SUM(COALESCE(GTS.FORECAST_GTS_UNITS, GTS.ACTUAL_GTS_UNITS)) AS GROSS_TRADE_UNITS,
                          PQ.QUARTER AS [QUARTER],
                          PQ.YEAR AS [YEAR],
                          GTS.ITEM_MASTER_SID
                   FROM   [DBO].[UDF_GTS_WAC] (@ITEMID, @FROM_PERIOD_SID, @PROJ_PERIOD_END_SID+12, @FORECAST_NAME, @FORECAST_VERSION) GTS
                          INNER JOIN dbo.PERIOD PQ
                                  ON PQ.PERIOD_SID = GTS.PERIOD_SID
                   GROUP  BY GTS.ITEM_MASTER_SID,
                             PQ.QUARTER,
                             PQ.YEAR)
          INSERT INTO #GROSS_TRADE_SALES
                      (ITEM_MASTER_SID,
                       PERIOD_SID,
                       GROSS_TRADE_SALES,
                       GROSS_TRADE_UNITS,
                       PERIOD_DATE)
          SELECT C.ITEM_MASTER_SID,
                 C.PERIOD_SID,
                 COALESCE(GP.GROSS_TRADE_SALES, 0) AS GROSS_TRADE_SALES,
                 COALESCE(GP.GROSS_TRADE_UNITS, 0),
                 C.PERIOD_DATE
          FROM   GTS GP
                 RIGHT JOIN (SELECT PD.ITEM_MASTER_SID,
                                    PQ.PERIOD_QUARTER,
                                    PQ.PERIOD_YEAR,
                                    PQ.PERIOD_DATE,
                                    PQ.PERIOD_SID
                             FROM   #PROJECTION_DETAILS PD
                                    CROSS JOIN #PERIOD_QUARTER PQ
                             WHERE  PQ.PERIOD_DATE BETWEEN @FROM_DATE AND dateadd(MONTH,12,@TO_DATE)) C
                         ON GP.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                            AND GP.[QUARTER] = C.PERIOD_QUARTER
                            AND GP.[YEAR] = C.PERIOD_YEAR;
 
          ------------------------------------- % OF BUSINESS FOR Q1 2015 = GROSS TRADE SALES OF Q1 2015/SUM(GROSS TRADE SALES OF Q4 2014,Q1 2015,Q2 2015,Q3 2015)
          DECLARE @GTS_START_PERIOD_DATE DATETIME;
 
          SELECT @GTS_START_PERIOD_DATE = CASE
                                            WHEN DATEPART(QQ, @PROJECTION_START_DATE) IN ( 1, 2, 3 ) THEN CONVERT(DATE, CONVERT(VARCHAR(30), YEAR(@PROJECTION_START_DATE) - 2)
                                                                                                                        + '-10-01')
                                            ELSE @PROJECTION_START_DATE
                                          END;
 
          WITH PERCENT_BUSINESS
               AS (SELECT ( ( ROW_NUMBER()
                                OVER (
                                  PARTITION BY PD.NA_PROJ_DETAILS_SID
                                  ORDER BY GTS.PERIOD_SID) - 1 ) / 4 ) + 1 RN,
                          PD.NA_PROJ_DETAILS_SID,
                          GTS.PERIOD_SID,
                          GTS.GROSS_TRADE_SALES,
                          GTS.PERIOD_DATE
                   FROM   #GROSS_TRADE_SALES GTS
                          INNER JOIN #PROJECTION_DETAILS PD
                                  ON PD.ITEM_MASTER_SID = GTS.ITEM_MASTER_SID
                   WHERE  GTS.PERIOD_DATE BETWEEN @GTS_START_PERIOD_DATE AND @PROJECTION_END_DATE),
               PER_BUSINESS
               AS (SELECT NA_PROJ_DETAILS_SID,
                          ( GROSS_TRADE_SALES / NULLIF(SUM(GROSS_TRADE_SALES)
                                                         OVER(
                                                           PARTITION BY NA_PROJ_DETAILS_SID, RN), 0) ) * 100 AS PERCENT_OF_BUSINESS,
                          PERIOD_SID
                   FROM   PERCENT_BUSINESS)
          UPDATE GTS
          SET    GTS.PERCENT_OF_BUSINESS = ISNULL(T.PERCENT_OF_BUSINESS,0)
          FROM   #GROSS_TRADE_SALES GTS
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON GTS.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
                 JOIN PER_BUSINESS T
                   ON GTS.PERIOD_SID = T.PERIOD_SID
                      AND PD.NA_PROJ_DETAILS_SID = T.NA_PROJ_DETAILS_SID;
 
          ----------------------------------------------------------------- SALES WEIGHTED NON-FAMP FOR EACH QUARTER = NON-FAMP OF EACH QUARTER * % OF BUSINESS
          UPDATE GTS
          SET    GTS.SALES_WEIGHTED_NON_FAMP = coalesce(IP.ITEM_PRICE * ( GTS.PERCENT_OF_BUSINESS / 100.0 ),0)
          FROM   #GROSS_TRADE_SALES GTS
                 INNER JOIN #ITEM_PRICING IP
                         ON GTS.PERIOD_SID = IP.PERIOD_SID
                            AND GTS.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
          WHERE  IP.PRICE_TYPE = 'QNON-FAMP';
 
          ---------------------------------------------------  YEAR WISE NON-FAMP FOR 2014 = (SUM OF SALES WEIGHTED NON-FAMP OF Q4 2012,Q1 2013,Q2 2013,Q3 2013)
          DECLARE @NFAMP_START_PERIOD_DATE DATETIME;
 
          SELECT @NFAMP_START_PERIOD_DATE = CASE
                                              WHEN DATEPART(QQ, @PROJECTION_START_DATE) IN ( 1, 2, 3 ) THEN CONVERT(DATE, CONVERT(VARCHAR(30), YEAR(@PROJECTION_START_DATE) - 2)
                                                                                                                          + '-10-01')
                                              ELSE @PROJECTION_START_DATE
                                            END;
 
          IF OBJECT_ID('TEMPDB..#YEAR_PRICE') IS NOT NULL
		  BEGIN
            DROP TABLE #YEAR_PRICE;
          END;
            CREATE TABLE #YEAR_PRICE
              (
                 NON_FAMP_PRICE      NUMERIC(22, 6) NULL,
                 PERIOD_SID          INT NOT NULL,
                 NA_PROJ_DETAILS_SID INT NOT NULL,
                 PERIOD_DATE         DATETIME NULL,
                 CALCULATED_CEILING  NUMERIC(22, 6) NULL,
                 FSS_YEAR_PRICE      NUMERIC(22, 6) NULL,
                 MAX_FSS_PRICE       NUMERIC(22, 6) NULL,
                 FORECAST_CPI_U      NUMERIC(22, 6) NULL,
                 FORECAST_FCP        NUMERIC(22, 6) NULL,
                 MANDATED_DISCOUNT   NUMERIC(22, 6) NULL,
                 WAC_YEAR_PRICE      NUMERIC(22, 6) NULL,
                 CPI_URA_YEAR_PRICE  NUMERIC(22, 6) NULL
              )
          ------------------------------------------------------------------------------- NON-FAMP YEARLY CALCULATION
          ;
 
          WITH NON_FAMP
               AS (SELECT ( ( ROW_NUMBER()
                                OVER (
                                  PARTITION BY PD.NA_PROJ_DETAILS_SID
                                  ORDER BY GTS.PERIOD_SID) - 1 ) / 4 ) + 1 RN,
                          PD.NA_PROJ_DETAILS_SID,
                          GTS.PERIOD_SID,
                          GTS.SALES_WEIGHTED_NON_FAMP                      AS NF_PRICE,
                          GTS.PERIOD_DATE
                   FROM   #GROSS_TRADE_SALES GTS
                          INNER JOIN #PROJECTION_DETAILS PD
                                  ON PD.ITEM_MASTER_SID = GTS.ITEM_MASTER_SID
                                     AND PD.NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID
                   WHERE  GTS.PERIOD_DATE BETWEEN @NFAMP_START_PERIOD_DATE AND dateadd(year,1,@PROJECTION_END_DATE)),
               N_FAMP
               AS (SELECT NA_PROJ_DETAILS_SID,
                          PRICE,
                          CONVERT(VARCHAR(4), PERIOD_DATE) + '-01-01' AS PERIOD_DATE,
                          PERIOD_SID
                   FROM   (SELECT DISTINCT NA_PROJ_DETAILS_SID,
                                           SUM(NF_PRICE)
                                             OVER(
                                               PARTITION BY NA_PROJ_DETAILS_SID, RN) AS PRICE,
                                           MAX(YEAR(PERIOD_DATE)) OVER(PARTITION BY NA_PROJ_DETAILS_SID, RN)
                                                                                  AS [PERIOD_DATE],
                                           MAX(PERIOD_SID) OVER( PARTITION BY NA_PROJ_DETAILS_SID, RN)
                                           - 6                                       AS PERIOD_SID
                           FROM   NON_FAMP) A)
          INSERT INTO #YEAR_PRICE
                      (NON_FAMP_PRICE,
                       PERIOD_SID,
                       NA_PROJ_DETAILS_SID,
                       PERIOD_DATE)
          SELECT isnull(PRICE,0) PRICE,
                 PERIOD_SID,
                 NA_PROJ_DETAILS_SID,
                 PERIOD_DATE
          FROM   N_FAMP;
          ------------------------------------------------------------------------------- WAC YEARLY CALCULATION
       
              
              if object_id('tempdb..#WAC_YEAR') is not null
			  BEGIN
              drop table #WAC_YEAR;
               END;
 
          DECLARE @BACK_ACT_SID INT = (SELECT PERIOD_SID
             FROM   dbo.PERIOD
             WHERE  PERIOD_DATE = DATEADD(QQ, -5, @ACTUAL_START_DATE));
 
          WITH CTE
               AS (SELECT GTS.FORECAST_GTS_SALES,
                          GTS.ACTUAL_GTS_SALES,
                          GTS.FORECAST_GTS_UNITS,
                          GTS.ACTUAL_GTS_UNITS,
                          GTS.ITEM_MASTER_SID,
                          P.MONTH,
                          P.QUARTER,
                          P.[YEAR],
                          P.PERIOD_SID,
                          P.PERIOD_DATE
                   FROM   [DBO].[UDF_GTS_WAC] (@ITEMID, @BACK_ACT_SID, @PROJ_PERIOD_END_SID, @FORECAST_NAME, @FORECAST_VERSION) GTS
                          INNER JOIN dbo.PERIOD P
                                  ON GTS.PERIOD_SID = P.PERIOD_SID),
               CTE1
               AS (SELECT ID.NA_PROJ_DETAILS_SID,
                          C.[YEAR],
                          MIN(C.PERIOD_SID) AS PERIOD_SID,
                          'AVGY' AS PRICE_TYPE,
                          ( SUM(CASE
                                                 WHEN C.PERIOD_DATE < CAST(DATEADD(DD, -( DAY(GETDATE()) - 1 ), GETDATE()) AS DATE) THEN C.ACTUAL_GTS_SALES
                                                 ELSE C.FORECAST_GTS_SALES
                                               END) / SUM(CASE
                                                            WHEN C.PERIOD_DATE < CAST(DATEADD(DD, -( DAY(GETDATE()) - 1 ), GETDATE()) AS DATE) THEN NULLIF(C.ACTUAL_GTS_UNITS, 0)
                                                            ELSE NULLIF(C.FORECAST_GTS_UNITS, 0)
                                                          END) ) / NULLIF(ID.UPPS, 0) AS WAC_YEAR_PRICE
                   FROM   CTE C
                          INNER JOIN #PROJECTION_DETAILS ID
                                  ON C.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
                   GROUP  BY ID.NA_PROJ_DETAILS_SID,
                             C.[YEAR],
                             ID.UPPS)
          SELECT NA_PROJ_DETAILS_SID,YEAR,PERIOD_SID,PRICE_TYPE,WAC_YEAR_PRICE
          INTO   #WAC_YEAR
          FROM   CTE1;
 
          ------------------------------------------------------------------------------- CPI-URA YEARLY CALCULATION
          UPDATE YP
          SET    YP.CPI_URA_YEAR_PRICE = ISNULL(PT.ADDITIONAL_DISCOUNT,0)
          FROM   #YEAR_PRICE YP
                 LEFT OUTER JOIN #QUARTER_PRICE PT
                              ON YEAR(YP.PERIOD_DATE) = YEAR(PT.PERIOD_DATE) + 1
                                 AND YP.NA_PROJ_DETAILS_SID = PT.NA_PROJ_DETAILS_SID;
 
          -------------------------------------------------------------- CALCULATED CEILING
          --(NON-FAMP*0.76)-PREVIOUS YEAR THIRD QUARTER ADDITIONAL DISCOUNT
          --UPDATE YP
          --SET    YP.CALCULATED_CEILING = COALESCE(( YP.NON_FAMP_PRICE * 0.76 ),0) - isnull(PT.ADDITIONAL_DISCOUNT,0)
          --FROM   #YEAR_PRICE YP
          --       LEFT OUTER JOIN #QUARTER_PRICE PT
          --                    ON YEAR(YP.PERIOD_DATE) = YEAR(PT.PERIOD_DATE) + 1
          --                       AND YP.NA_PROJ_DETAILS_SID = PT.NA_PROJ_DETAILS_SID
 
 
                ----- CEL-338 change request (Previous year NON-FAMP*0.76)-PREVIOUS YEAR THIRD QUARTER ADDITIONAL DISCOUNT
              ;WITH cte
                     AS (SELECT YP.NA_PROJ_DETAILS_SID,
                                         YP.period_sid,
                                         COALESCE(( Lag(YP.NON_FAMP_PRICE)
                                                                                                  OVER(
                                                                                                       partition BY pt.NA_PROJ_DETAILS_SID
                                                                                                       ORDER BY Year(YP.PERIOD_DATE) ) * 0.76 ), 0) - Isnull(Lag(PT.ADDITIONAL_DISCOUNT)
                                                                                                                                                                                                       OVER(
                                                                                                                                                                                                         partition BY pt.NA_PROJ_DETAILS_SID
                                                                                                                                                                                                         ORDER BY Year(PT.PERIOD_DATE) ), 0) CALCULATED_CEILING
                            FROM   #YEAR_PRICE YP
                                         LEFT OUTER JOIN #QUARTER_PRICE PT
                                                              ON Year(YP.PERIOD_DATE) = Year(PT.PERIOD_DATE)
                                                                     AND YP.NA_PROJ_DETAILS_SID = PT.NA_PROJ_DETAILS_SID)
              UPDATE yp
              SET    yp.CALCULATED_CEILING = c.CALCULATED_CEILING
              FROM   #YEAR_PRICE yp
                        JOIN cte c
                           ON yp.NA_PROJ_DETAILS_SID = c.NA_PROJ_DETAILS_SID
                                  AND yp.PERIOD_SID = c.PERIOD_SID;
 
 
          ------------ FSS = AVERAGE(Q4 OF BEFORE PREVIOUS YEAR,E9:G9) FOR EACH YEAR
          --EX: FSS OF 2016 = AVERAGE OF (Q4 2014,Q1 2015,Q2 2015,Q3 2015)
          --;
 
          --WITH FSS_YEAR_PRICE
          --     AS (SELECT ( ( ROW_NUMBER()
          --                      OVER (
          --                        PARTITION BY NA_PROJ_DETAILS_SID
          --                        ORDER BY PQ.PERIOD_SID) - 1 ) / 4 ) + 1 RN,
          --                PD.NA_PROJ_DETAILS_SID,
          --                IP.PERIOD_SID,
          --                IP.ITEM_PRICE                                   AS PROJECTION_YEAR_PRICE,
          --                PQ.PERIOD_DATE
          --         FROM   #ITEM_PRICING IP
          --                INNER JOIN #PERIOD_QUARTER PQ
          --                        ON IP.PERIOD_SID = PQ.PERIOD_SID
          --                INNER JOIN #PROJECTION_DETAILS PD
          --                        ON PD.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
          --                           AND PD.NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID
          --         WHERE  IP.PRICE_TYPE = 'QFSS'
          --                AND PQ.PERIOD_DATE BETWEEN @NFAMP_START_PERIOD_DATE AND @PROJECTION_END_DATE),
          --     TEMP
          --     AS (SELECT NA_PROJ_DETAILS_SID,
          --                ( PRICE / 4 )                               AS PRICE,
          --                CONVERT(VARCHAR(4), PERIOD_DATE) + '-01-01' AS PERIOD_DATE,
          --                PERIOD_SID
          --         FROM   (SELECT DISTINCT NA_PROJ_DETAILS_SID,
          --                                 SUM(PROJECTION_YEAR_PRICE)
          --                                   OVER(
          --                                     PARTITION BY NA_PROJ_DETAILS_SID, RN) AS PRICE,
          --                                 MIN(YEAR(PERIOD_DATE)) OVER(PARTITION BY NA_PROJ_DETAILS_SID, RN)
          --                                 + 1                                       AS [PERIOD_DATE],
          --                                 MIN(PERIOD_SID) OVER( PARTITION BY NA_PROJ_DETAILS_SID, RN)
          --                                 + 3                                       AS PERIOD_SID
          --                 FROM   FSS_YEAR_PRICE) A)
          --UPDATE YP
          --SET    YP.FSS_YEAR_PRICE = isnull(T.PRICE,0)
          --FROM   #YEAR_PRICE YP
          --       JOIN TEMP T
          --         ON YEAR(YP.PERIOD_DATE) = YEAR(T.PERIOD_DATE) + 1
          --            AND YP.NA_PROJ_DETAILS_SID = T.NA_PROJ_DETAILS_SID
 
 
          ----------------- FSS = Previous year last quarter QFSS * ( 1 + pervious year cpi-u increase percent) CEL-343
          --EX: FSS OF 2016 = Q4 2015 QFSS * 2015 cpi-u increase percent)
                          insert into #QUARTER_PRICE
                         (
                         na_proj_details_sid,
                         period_sid,
                         PERIOD_DATE
                         )
                         select na_proj_details_sid,max(period_sid)+12,dateadd(year,1,max(period_date)) from #QUARTER_PRICE group by na_proj_details_sid;
 
   IF OBJECT_ID('TEMPDB..#FSS_ELIGIBLE') IS NOT NULL
   BEGIN
       DROP TABLE #FSS_ELIGIBLE;
	   END;
                CREATE TABLE #FSS_ELIGIBLE
                (
                NA_PROJ_DETAILS_SID INT NOT NULL,
                PERIOD_SID    INT NOT NULL,
                ITEM_PRICE NUMERIC(22,6) NULL ,
                ID INT NOT NULL
                );
 
                SET @SQL ='
                INSERT INTO #FSS_ELIGIBLE
                (
                NA_PROJ_DETAILS_SID,
                PERIOD_SID,
                 ITEM_PRICE,
                 id
                )
               
              
                SELECT PD.NA_PROJ_DETAILS_SID,PD.PERIOD_SID,0 as itm_price,ROW_NUMBER() OVER(PARTITION BY PD.NA_PROJ_DETAILS_SID ORDER BY PD.PERIOD_SID ) AS ID FROM '+@FCP_PROJECTION_TABLE +' PD
                JOIN  #EFFECTIVE_PERIOD EP ON EP.NA_PROJ_DETAILS_SID=PD.NA_PROJ_DETAILS_SID
                JOIN  #PROJECTION_DETAILS PS ON PS.NA_PROJ_DETAILS_SID=PD.NA_PROJ_DETAILS_SID
                JOIN
                (
                SELECT ITEM_MASTER_SID,MAX(PERIOD_SID) PERIOD_SID FROM #AFSS   WHERE ITEM_PRICE<>0  GROUP BY ITEM_MASTER_SID
                )B
                ON B.ITEM_MASTER_SID=PS.ITEM_MASTER_SID AND PD.PERIOD_SID>B.PERIOD_SID
				
                 WHERE PD.PRICE_TYPE=''AFSS'' AND PD.PERIOD_SID BETWEEN EP.START_PERIOD AND EP.END_PERIOD
                 UNION ALL
                 SELECT PFR.NA_PROJ_DETAILS_SID,PERIOD_SID, ITEM_PRICE ,0 as id
                 FROM
                 (SELECT ITEM_MASTER_SID,
                           PERIOD_SID,
                           ITEM_PRICE,
                           ROW_NUMBER() OVER(PARTITION BY ITEM_MASTER_SID ORDER BY PERIOD_SID DESC) RNO
                  FROM #AFSS WHERE ITEM_PRICE<>0)A
				   
                     JOIN #PROJECTION_DETAILS PFR ON PFR.ITEM_MASTER_SID=A.ITEM_MASTER_SID
					  
					 
                     WHERE RNO=1
                 ';
                 EXEC sp_executesql @SQL;
      

	 ---- DECLARE @VAR INT=(SELECT DISTINCT PERIOD_SID FROM #FSS_ELIGIBLE WHERE ID=0 )-------cel-411
	--------			 DECLARE @VAR1 INT=(SELECT DISTINCT PERIOD_SID FROM #FSS_ELIGIBLE WHERE ID=1 )-------cel-411

				 DECLARE @SQL_NA_CR VARCHAR(MAX);
				SET @SQL_NA_CR= 'UPDATE FSS1 SET ITEM_PRICE=ISNULL(NULLIF(B.PROJECTION_PRICE,0),ITEM_PRICE) FROM #FSS_ELIGIBLE FSS1
				JOIN
				(SELECT FSS.NA_PROJ_DETAILS_SID,FP.PROJECTION_PRICE FROM #FSS_ELIGIBLE FSS JOIN '+@FCP_PROJECTION_TABLE +' FP ON FP.NA_PROJ_DETAILS_SID=FSS.NA_PROJ_DETAILS_SID WHERE FP.PRICE_TYPE=''QFSS'' AND
				  FSS.PERIOD_SID-3=FP.PERIOD_SID AND ID=1)B
				  ON B.NA_PROJ_DETAILS_SID=FSS1.NA_PROJ_DETAILS_SID AND FSS1.ID=0
				  ';
				 
				  EXEC(@SQL_NA_CR);
			;WITH FSS_YEAR_PRICE
                           AS (SELECT PD.NA_PROJ_DETAILS_SID,
                                         PQ.PERIOD_DATE,
                                         pq.period_year,
                                         FS.ID,
                                         Lag(FS.ITEM_PRICE)
                                                                           OVER(
                                                                                  partition BY pd.NA_PROJ_DETAILS_SID
                                                                                  ORDER BY pq.period_year) * ( 1
                                                                                                                                  +isnull(lag(qp.CPI_INCREASE_PERCENT/100.0)OVER(partition BY pd.NA_PROJ_DETAILS_SID ORDER BY pq.period_year),0) )
                                                                                                                                  as FSS_YEAR_PRICE,
                                                                                                                                  qp.CPI_INCREASE_PERCENT as CPI_INCREASE_PERCENT
                                                                                                                                  
                                  FROM  #PROJECTION_DETAILS PD
                                         JOIN #FSS_ELIGIBLE FS ON FS.NA_PROJ_DETAILS_SID=PD.NA_PROJ_DETAILS_SID
                                         JOIN #PERIOD_QUARTER PQ
                                                       ON PQ.PERIOD_SID=FS.PERIOD_SID
                                         JOIN #QUARTER_PRICE qp
                                                ON qp.NA_PROJ_DETAILS_SID = pd.NA_PROJ_DETAILS_SID
                                                       AND Year(qp.PERIOD_DATE) = pq.PERIOD_YEAR
                                                       WHERE FS.ID<=1
                                                       UNION ALL
 
                                         SELECT PD.NA_PROJ_DETAILS_SID,
                                         PQ.PERIOD_DATE,
                                         pq.period_year,
                                         FS.ID,
                                         PD.FSS_YEAR_PRICE* ( 1+ (isnull((PD.CPI_INCREASE_PERCENT/100.0),0))),
                                         qp.CPI_INCREASE_PERCENT
                                                                                                                          
                                  FROM  FSS_YEAR_PRICE PD
                                         JOIN #FSS_ELIGIBLE FS ON FS.NA_PROJ_DETAILS_SID=PD.NA_PROJ_DETAILS_SID
                                         JOIN #PERIOD_QUARTER PQ
                                                       ON PQ.PERIOD_SID=FS.PERIOD_SID
                                         JOIN #QUARTER_PRICE qp
                                                ON qp.NA_PROJ_DETAILS_SID = pd.NA_PROJ_DETAILS_SID
                                                       AND Year(qp.PERIOD_DATE) = pq.PERIOD_YEAR
                                         WHERE PD.ID+1=FS.ID  AND PD.ID<>0 AND PD.PERIOD_YEAR+1=PQ.PERIOD_YEAR
 
                                         )
 
                                         UPDATE YP
              SET    YP.FSS_YEAR_PRICE = Isnull(T.FSS_YEAR_PRICE, 0)
              FROM   #YEAR_PRICE YP
                           JOIN FSS_YEAR_PRICE T
                                  ON Year(YP.PERIOD_DATE) = Year(T.PERIOD_DATE)
                                  AND YP.NA_PROJ_DETAILS_SID = T.NA_PROJ_DETAILS_SID
                                         OPTION (MAXRECURSION 0);
             
 
----------  --------------------------------Max FSS----------------------------
 
   IF OBJECT_ID('TEMPDB..#MAXFSS_ELIGIBLE') IS NOT NULL
   BEGIN
       DROP TABLE #MAXFSS_ELIGIBLE;
	   END;
                CREATE TABLE #MAXFSS_ELIGIBLE
                (
                NA_PROJ_DETAILS_SID INT NOT NULL,
                PERIOD_SID    INT NOT NULL,
                ITEM_PRICE NUMERIC(22,6) NULL,
                ID INT NOT NULL
                );
 
                SET @SQL ='
                INSERT INTO #MAXFSS_ELIGIBLE
                (
                NA_PROJ_DETAILS_SID,
                PERIOD_SID,
                 ITEM_PRICE,
                 id
                )
               
                
                SELECT PD.NA_PROJ_DETAILS_SID,PD.PERIOD_SID,aff1.item_price as itm_price,ROW_NUMBER() OVER(PARTITION BY PD.NA_PROJ_DETAILS_SID ORDER BY PD.PERIOD_SID ) AS ID FROM '+@FCP_PROJECTION_TABLE +' PD
             
                JOIN  #PROJECTION_DETAILS PS ON PS.NA_PROJ_DETAILS_SID=PD.NA_PROJ_DETAILS_SID
                JOIN #EFFECTIVE_PERIOD EP ON EP.NA_PROJ_DETAILS_SID=PS.NA_PROJ_DETAILS_SID
                           AND PD.PERIOD_SID BETWEEN EP.START_PERIOD AND EP.END_PERIOD
				
				JOIN
				(
				SELECT NA_PROJ_DETAILS_SID,MIN(PERIOD_SID) PERIOD_SID FROM #FSS_ELIGIBLE  WHERE ID<>0 GROUP BY NA_PROJ_DETAILS_SID
				)C
				ON C.NA_PROJ_DETAILS_SID=PS.NA_PROJ_DETAILS_SID AND C.PERIOD_SID>PD.PERIOD_SID
				join #AFSS aff1 on aff1.item_master_sid=PS.item_master_sid and pd.period_sid=aff1.period_sid
                 WHERE PD.PRICE_TYPE=''AFSS''
                 UNION ALL
                 SELECT PFR.NA_PROJ_DETAILS_SID,PERIOD_SID, ITEM_PRICE ,0 as id
                 FROM
                 (SELECT PS.ITEM_MASTER_SID,
                           PERIOD_SID,
                           ITEM_PRICE,
                           ROW_NUMBER() OVER(PARTITION BY PS.ITEM_MASTER_SID ORDER BY PERIOD_SID DESC) RNO
                  FROM #AFSS PS
				 JOIN #PROJECTION_DETAILS PD1 ON PD1.ITEM_MASTER_SID=PS.ITEM_MASTER_SID
                             JOIN #EFFECTIVE_PERIOD EP ON EP.NA_PROJ_DETAILS_SID=PD1.NA_PROJ_DETAILS_SID AND EP.START_PERIOD>PS.PERIOD_SID
                              WHERE ITEM_PRICE<>0)A
                     JOIN #PROJECTION_DETAILS PFR ON PFR.ITEM_MASTER_SID=A.ITEM_MASTER_SID
                     WHERE RNO=1
                 ';
				-- SELECT @SQL
                 EXEC sp_executesql @SQL;
 
    ;WITH MAXFSS_YEAR_PRICE
                           AS (SELECT PD.NA_PROJ_DETAILS_SID,
                                         PQ.PERIOD_DATE,
                                         pq.period_year,
                                         FS.ID,
                                         Lag(FS.ITEM_PRICE)
                                                                           OVER(
                                                                                  partition BY pd.NA_PROJ_DETAILS_SID
                                                                                  ORDER BY pq.period_year) * ( 1
                                                                                                                                  +isnull(lag(qp.CPI_INCREASE_PERCENT/100.0)OVER(partition BY pd.NA_PROJ_DETAILS_SID ORDER BY pq.period_year),0) )
                                                                                                                                  as FSS_YEAR_PRICE,
                                                                                                                                  qp.CPI_INCREASE_PERCENT as CPI_INCREASE_PERCENT,
																																 FS.ITEM_PRICE as ITEM_PRICE
                                                                                                                                  
                                  FROM  #PROJECTION_DETAILS PD
                                         JOIN #MAXFSS_ELIGIBLE FS ON FS.NA_PROJ_DETAILS_SID=PD.NA_PROJ_DETAILS_SID
                                         JOIN #PERIOD_QUARTER PQ
                                                       ON PQ.PERIOD_SID=FS.PERIOD_SID
                                         JOIN #QUARTER_PRICE qp
                                                ON qp.NA_PROJ_DETAILS_SID = pd.NA_PROJ_DETAILS_SID
                                                       AND Year(qp.PERIOD_DATE) = pq.PERIOD_YEAR
                                                       WHERE FS.ID<=1
                                                       UNION ALL
 
                                         SELECT PD.NA_PROJ_DETAILS_SID,
                                         PQ.PERIOD_DATE,
                                         pq.period_year,
                                         FS.ID,
                                         PD.ITEM_PRICE* ( 1+ (isnull((PD.CPI_INCREASE_PERCENT/100.0),0))),
                                         qp.CPI_INCREASE_PERCENT,
                                           fs.ITEM_PRICE                                                                                
                                  FROM  MAXFSS_YEAR_PRICE PD
                                         JOIN #MAXFSS_ELIGIBLE FS ON FS.NA_PROJ_DETAILS_SID=PD.NA_PROJ_DETAILS_SID
                                         JOIN #PERIOD_QUARTER PQ
                                                       ON PQ.PERIOD_SID=FS.PERIOD_SID
                                         JOIN #QUARTER_PRICE qp
                                                ON qp.NA_PROJ_DETAILS_SID = pd.NA_PROJ_DETAILS_SID
                                                       AND Year(qp.PERIOD_DATE) = pq.PERIOD_YEAR
                                         WHERE PD.ID+1=FS.ID  AND PD.ID<>0 AND PD.PERIOD_YEAR+1=PQ.PERIOD_YEAR
 
                                         )
										

                                         UPDATE YP
              SET    YP.MAX_FSS_PRICE = Isnull(T.FSS_YEAR_PRICE, 0)
              FROM   #YEAR_PRICE YP
                           JOIN MAXFSS_YEAR_PRICE T
                                  ON Year(YP.PERIOD_DATE) = Year(T.PERIOD_DATE)
                                  AND YP.NA_PROJ_DETAILS_SID = T.NA_PROJ_DETAILS_SID
                                         OPTION (MAXRECURSION 0);
 
 
 
          -------------------------------------------------------------------------------------------------- YEAR WISE ACTUAL PRICE
 
                     IF OBJECT_ID('TEMPDB..#ACTUAL_YEAR_PRICE') IS NOT NULL
					 BEGIN
              DROP TABLE #ACTUAL_YEAR_PRICE;
			  END;

WITH CTE
AS (
       SELECT *
       FROM (
              SELECT PD.NA_PROJ_DETAILS_SID
                     ,IP.PERIOD_SID
                     ,IP.ACTUAL_PRICE AS ACTUAL_YEAR_PRICE
                     ,IP.PRICE_TYPE
              FROM #I_PRICING IP
              INNER JOIN #PROJECTION_DETAILS PD ON IP.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
              WHERE IP.PRICE_TYPE IN (
                           'ANON-FAMP'
                           ,'FCP'
                           ,'AFSS'
                           )
              ) AS S
       PIVOT(SUM(ACTUAL_YEAR_PRICE) FOR PRICE_TYPE IN (
                           [ANON-FAMP]
                           ,[FCP]
                           ,[AFSS]
                           )) AS PVT
       )
       ,CTE2
AS (
       SELECT A.NA_PROJ_DETAILS_SID
              ,MIN(P.PERIOD_SID) PERIOD_SID
              ,MAX(A.[ANON-FAMP]) AS ANON_FAMP
              ,MAX(A.[FCP]) FCP
              ,max(A.[AFSS]) AFSS
       FROM CTE A
       JOIN dbo.PERIOD P ON P.PERIOD_SID = A.PERIOD_SID
       GROUP BY A.NA_PROJ_DETAILS_SID
              ,P.YEAR
       )
SELECT A.NA_PROJ_DETAILS_SID
       ,A.PERIOD_SID
       ,CS.ACTUAL_YEAR_PRICE 
       ,CS.PRICE_TYPE
       INTO #ACTUAL_YEAR_PRICE
FROM (
       SELECT NA_PROJ_DETAILS_SID
              ,PERIOD_SID
              ,ANON_FAMP
              ,FCP
              ,AFSS
              ,COALESCE(LAG(ANON_FAMP, 1) OVER (
                           PARTITION BY NA_PROJ_DETAILS_SID ORDER BY PERIOD_SID
                           ), 0) - ISNULL(FCP, 0) AS MANDATED_DISCOUNT
       FROM CTE2
       ) A
   CROSS APPLY (VALUES (A.ANON_FAMP, 'ANON-FAMP'),
                        (A.FCP,'FCP' ),
                        (A.MANDATED_DISCOUNT,'TRI-CARE'),
                        (A.AFSS,'AFSS')) CS (ACTUAL_YEAR_PRICE, PRICE_TYPE);
 
 
                DECLARE @SQL_FCP_UPD3 NVARCHAR(MAX)='';
          --SET @SQL_FCP_UPD3=CONCAT('UPDATE FA
          --SET    FA.ACTUAL_PRICE = ACTUAL_YEAR_PRICE
          --FROM   ',@FCP_ACTUAL_TABLE,' FA
          --       INNER JOIN (SELECT NA_PROJ_DETAILS_SID,
          --                          PERIOD_SID,
          --                          ACTUAL_PRICE AS ACTUAL_YEAR_PRICE,
          --                          PRICE_TYPE
          --                   FROM   #I_PRICING IP
         --                          INNER JOIN #PROJECTION_DETAILS PD
          --                                  ON IP.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
          --                   WHERE  PRICE_TYPE IN ( ''ANON-FAMP'', ''FCP'', ''TRI-CARE'', ''AFSS'' )) A
          --               ON FA.NA_PROJ_DETAILS_SID = A.NA_PROJ_DETAILS_SID
          --                  AND FA.PRICE_TYPE = A.PRICE_TYPE
          --                  AND FA.PERIOD_SID = A.PERIOD_SID')
                                                --SELECT @SQL_FCP_UPD3
 
                                                  SET @SQL_FCP_UPD3=CONCAT('UPDATE FA
          SET    FA.ACTUAL_PRICE = ACTUAL_YEAR_PRICE
          FROM   ',@FCP_ACTUAL_TABLE,' FA
                 INNER JOIN #ACTUAL_YEAR_PRICE A
                         ON FA.NA_PROJ_DETAILS_SID = A.NA_PROJ_DETAILS_SID
                            AND FA.PRICE_TYPE = A.PRICE_TYPE
                            AND FA.PERIOD_SID = A.PERIOD_SID');
                                                EXEC sp_executesql @SQL_FCP_UPD3;
 
         DECLARE @SQL_FCP_UPD4 NVARCHAR(MAX)='';
          SET @SQL_FCP_UPD4=CONCAT('UPDATE  FA
          SET    FA.ACTUAL_PRICE = isnull(WAC_YEAR_PRICE,0)
          FROM   ',@FCP_ACTUAL_TABLE,' FA
                 INNER JOIN #WAC_YEAR YP
                         ON YP.NA_PROJ_DETAILS_SID = FA.NA_PROJ_DETAILS_SID
                            AND YP.PERIOD_SID = FA.PERIOD_SID
                            AND YP.PRICE_TYPE = FA.PRICE_TYPE');
--SELECT @SQL_FCP_UPD4
                                  EXEC sp_executesql @SQL_FCP_UPD4;
 
-- CEL 357 change  start
 
 
DECLARE @SQL_FCP_UPD5 NVARCHAR(MAX)='';
 
set @SQL_FCP_UPD5 = 'UPDATE fa
SET    fa.ACTUAL_PRICE = a.price
FROM   '+@FCP_ACTUAL_TABLE+' fa
       JOIN (SELECT ip.NA_PROJ_DETAILS_SID,
                    wc.period_sid,
                    price = Isnull(WAC_YEAR_PRICE, 0) - Isnull(ACTUAL_PRICE, 0),
                    CASE
                      WHEN ip.price_type = ''AFSS'' THEN ''FSS (OGA) Discount''
                      ELSE ''FCP Discount''
                    END price_type
             FROM   #WAC_YEAR wc
                    JOIN (SELECT NA_PROJ_DETAILS_SID,
                                 ACTUAL_PRICE,
                                 period_sid,
                                 price_type
                          FROM   '+@FCP_ACTUAL_TABLE+'
                          WHERE  price_type IN ( ''AFSS'', ''FCP'' ))ip
                      ON wc.NA_PROJ_DETAILS_SID = ip.NA_PROJ_DETAILS_SID
                         AND wc.PERIOD_SID = ip.PERIOD_SID) a
         ON a.NA_PROJ_DETAILS_SID = fa.NA_PROJ_DETAILS_SID
            AND a.PERIOD_SID = fa.PERIOD_SID
            AND a.price_type = fa.price_type ';
 
              EXEC sp_executesql @SQL_FCP_UPD5;
 
-- CEL 357 change  start
 
 
 
          -------------------------------------------------------- MAXIMUM FSS PRICE (OGA) FOR EACH YEAR = FSS(PREVIOUS YEAR)*(1 + CPI-U PERCENTAGE INCREASE(THIRD QUARTER OF PREVIOUS YEAR))
         
                DECLARE @SQL_FCP_CTE NVARCHAR(MAX)='';
          SET @SQL_FCP_CTE=CONCAT(
                        
                         '
                         --WITH YEAR_PRICE_CTE
    --           AS (SELECT FSS_YEAR_PRICE,
    --                      PERIOD_SID,
    --                      NA_PROJ_DETAILS_SID,
    --                      PERIOD_DATE
    --               FROM   #YEAR_PRICE YP
    --               WHERE  PERIOD_SID > ',@PROJ_PERIOD_START_SID,'
    --               UNION ALL
    --               SELECT ACTUAL_PRICE,
    --                      FA.PERIOD_SID,
    --                      FA.NA_PROJ_DETAILS_SID,
    --                      PERIOD_DATE
    --               FROM   ',@FCP_ACTUAL_TABLE,' FA
    --                      INNER JOIN #PROJECTION_DETAILS PD
    --                              ON FA.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
    --                      INNER JOIN #PERIOD_QUARTER PQ
    --                              ON PQ.PERIOD_SID = FA.PERIOD_SID
    --               WHERE  PRICE_TYPE = ''AFSS''
    --                      AND FA.PERIOD_SID = ',@PROJ_PERIOD_START_SID,')
         UPDATE YP
        SET    YP.MAX_FSS_PRICE = ISNULL(YP.FSS_YEAR_PRICE,0)
          FROM   #YEAR_PRICE YP 
		 
		  JOIN #FSS_ELIGIBLE MFSS ON MFSS.NA_PROJ_DETAILS_SID= YP.NA_PROJ_DETAILS_SID  AND YP.PERIOD_SID=MFSS.PERIOD_SID
		  AND ID<>0
		   JOIN #EFFECTIVE_PERIOD EP ON
		  EP.NA_PROJ_DETAILS_SID=YP.NA_PROJ_DETAILS_SID AND YP.PERIOD_SID BETWEEN EP.START_PERIOD AND EP.END_PERIOD

                 --INNER JOIN (SELECT ISNULL(YP.FSS_YEAR_PRICE,0) * ( 1 + ( ISNULL(PT.CPI_INCREASE_PERCENT,0) / 100.0 ) )AS MAX_FSS_PRICE,
                 --                   [YEAR]=YEAR(YP.PERIOD_DATE),
                 --                   YP.NA_PROJ_DETAILS_SID
                 --            FROM   YEAR_PRICE_CTE YP
                 --                   LEFT OUTER JOIN #QUARTER_PRICE PT
                 --                                ON YEAR(YP.PERIOD_DATE) = YEAR(PT.PERIOD_DATE)
                 --                                   AND YP.NA_PROJ_DETAILS_SID = PT.NA_PROJ_DETAILS_SID) A
                 --        ON YEAR(YP.PERIOD_DATE) = A.[YEAR] + 1
                        --    AND YP.NA_PROJ_DETAILS_SID = A.NA_PROJ_DETAILS_SID
 
 
          --------------------------------------------------------- FORECAST FCP UPDATED IN FORECAST TABLE
          --MIN(MAX FSS,CALCULATED CEILING)
          UPDATE YP
          SET    YP.FORECAST_FCP =  isnull(nullif(actual_price,0),CASE
                                     WHEN YP.MAX_FSS_PRICE < ISNULL(YP.CALCULATED_CEILING,0) THEN YP.MAX_FSS_PRICE
                                     ELSE YP.CALCULATED_CEILING
                                   END)
          FROM 
		   #YEAR_PRICE YP
		 JOIN #PROJECTION_DETAILS PD ON PD.NA_PROJ_DETAILS_SID=YP.NA_PROJ_DETAILS_SID
		 LEFT JOIN #I_PRICING AF ON PD.ITEM_MASTER_SID=AF.ITEM_MASTER_SID
		 AND AF.PERIOD_SID=YP.PERIOD_SID AND AF.PRICE_TYPE=''FCP''
 
          ---------------------------------------------------------FORECAST CPI-U YEAR WISE (NEDD TO CLARIFY)
          UPDATE YP
          SET    YP.FORECAST_CPI_U = ISNULL(PT.ADDITIONAL_DISCOUNT,0)
          FROM   #YEAR_PRICE YP
                 LEFT OUTER JOIN #QUARTER_PRICE PT
                              ON YEAR(YP.PERIOD_DATE) = YEAR(PT.PERIOD_DATE) + 1
                                 AND YP.NA_PROJ_DETAILS_SID = PT.NA_PROJ_DETAILS_SID
 
 
 
          ----------------------------------------------------- MANDATED DISCOUNT
          --UPDATE YP
          --SET    YP.MANDATED_DISCOUNT = ISNULL(YP.NON_FAMP_PRICE,0) - ISNULL(YP.FORECAST_FCP,0)
          --FROM   #YEAR_PRICE YP
               
                UPDATE YP
          SET    YP.MANDATED_DISCOUNT = ISNULL(YP.PRIOR_NON_FAMP_PRICE,0) - ISNULL(YP.FORECAST_FCP,0)
          FROM  ( SELECT NON_FAMP_PRICE      ,
                           COALESCE(LAG (NON_FAMP_PRICE,1) OVER(PARTITION BY NA_PROJ_DETAILS_SID ORDER BY  PERIOD_SID),0) PRIOR_NON_FAMP_PRICE,
                 PERIOD_SID          ,
                 FORECAST_FCP        ,
                 MANDATED_DISCOUNT    FROM #YEAR_PRICE) YP');
              --SELECT @SQL_FCP_CTE
                EXEC SP_EXECUTESQL @SQL_FCP_CTE;
 
 
DECLARE @SQL_FCP_PRJ_UPDD4 NVARCHAR(MAX)='';
               
SET @SQL_FCP_PRJ_UPDD4 = 'UPDATE B SET PROJECTION_PRICE=A.ITEM_PRICE
FROM
 
'+@FCP_PROJECTION_TABLE+' B   JOIN #PROJECTION_DETAILS PD ON PD.NA_PROJ_DETAILS_SID=B.NA_PROJ_DETAILS_SID JOIN  #AFSS A
ON A.ITEM_MASTER_SID=PD.ITEM_MASTER_SID AND A.PERIOD_SID=B.PERIOD_SID WHERE B.PRICE_TYPE=''AFSS''';
 
EXEC sp_executesql @SQL_FCP_PRJ_UPDD4;
 
 
 
          --------------------------------------------------------------------- UPDATE YEAR WISE DATA IN PROJECTION TABLE
             
 
                          
                DECLARE @SQL_FCP_PRJ_UPDD2 NVARCHAR(MAX)='';
          SET @SQL_FCP_PRJ_UPDD2=CONCAT('UPDATE FP
          SET    FP.PROJECTION_PRICE = ISNULL(FORECAST_YEAR_PRICE,0)
          FROM   ',@FCP_PROJECTION_TABLE,' FP
                 INNER JOIN (SELECT NA_PROJ_DETAILS_SID,
                                    PERIOD_SID,
                                    FORECAST_YEAR_PRICE,
                                    PRICE_TYPE
                             FROM   #YEAR_PRICE YP
                                    CROSS APPLY (VALUES (NON_FAMP_PRICE,
                                                ''ANON-FAMP''),
                                                        (FORECAST_CPI_U,
                                                ''CPI-U''),
                                                        (FORECAST_FCP,
                                                ''FCP'' ),
                                                        (MANDATED_DISCOUNT,
                                                ''TRI-CARE''),
                                                        (CPI_URA_YEAR_PRICE,
                                                ''CPI-URA''),
                                                        (FSS_YEAR_PRICE,
                                                ''AFSS''),
                                                        (MAX_FSS_PRICE,
                                                ''MAX FSS'' )) CS (FORECAST_YEAR_PRICE, PRICE_TYPE)) A
                         ON FP.NA_PROJ_DETAILS_SID = A.NA_PROJ_DETAILS_SID
                            AND FP.PRICE_TYPE = A.PRICE_TYPE
                            AND FP.PERIOD_SID = A.PERIOD_SID AND A.FORECAST_YEAR_PRICE<>0');
                                                --SELECT @SQL_FCP_PRJ_UPDD2
                                                EXEC sp_executesql @SQL_FCP_PRJ_UPDD2;
 
DECLARE @SQL_FCP_PRJ_UPDD3 NVARCHAR(MAX)='';
        SET @SQL_FCP_PRJ_UPDD3=CONCAT('UPDATE FP
          SET    FP.PROJECTION_PRICE = ISNULL(WY.WAC_YEAR_PRICE,0)
          FROM   ',@FCP_PROJECTION_TABLE,' FP
                 INNER JOIN #WAC_YEAR WY
                         ON FP.NA_PROJ_DETAILS_SID = WY.NA_PROJ_DETAILS_SID
                            AND FP.PRICE_TYPE = WY.PRICE_TYPE
                            AND WY.PERIOD_SID = FP.PERIOD_SID');
                                                --sELECT  @SQL_FCP_PRJ_UPDD3
                                                EXEC sp_executesql @SQL_FCP_PRJ_UPDD3;
 
-- CEL 357 change  start
 
 
 
set @SQL_FCP_PRJ_UPDD4 = 'UPDATE fa
SET    fa.projection_PRICE = a.price
FROM   '+@FCP_PROJECTION_TABLE+' fa
       JOIN (SELECT wc.NA_PROJ_DETAILS_SID,
                    wc.period_sid,
                    price = Isnull(WAC_YEAR_PRICE, 0) - Isnull(projection_PRICE, 0),
                    CASE
                      WHEN ip.price_type = ''AFSS'' THEN ''FSS (OGA) Discount''
                      ELSE ''FCP Discount''
                    END price_type
             FROM   #WAC_YEAR wc
                    JOIN (SELECT NA_PROJ_DETAILS_SID,
                                 projection_PRICE,
                                 period_sid,
                                 price_type
                          FROM   '+@FCP_PROJECTION_TABLE+'
                          WHERE  price_type IN ( ''AFSS'', ''FCP'' ))ip
                      ON wc.NA_PROJ_DETAILS_SID = ip.NA_PROJ_DETAILS_SID
                         AND wc.PERIOD_SID = ip.PERIOD_SID) a
         ON a.NA_PROJ_DETAILS_SID = fa.NA_PROJ_DETAILS_SID
            AND a.PERIOD_SID = fa.PERIOD_SID
            AND a.price_type = fa.price_type ';
 
              EXEC sp_executesql @SQL_FCP_PRJ_UPDD4;
 
 
 
 
 
SET @SQL_FCP_PRJ_UPDD4 = 'UPDATE B SET PROJECTION_PRICE=C.PROJECTION_PRICE
FROM
 
'+@FCP_PROJECTION_TABLE+' B
JOIN (
SELECT NA_PROJ_DETAILS_SID,PROJECTION_PRICE,YEAR FROM '+@FCP_PROJECTION_TABLE+' F JOIN PERIOD P ON P.PERIOD_SID=F.PERIOD_SID WHERE PRICE_TYPE=''AFSS''
)C
ON
C.NA_PROJ_DETAILS_SID=B.NA_PROJ_DETAILS_SID
JOIN PERIOD P
ON P.PERIOD_SID=B.PERIOD_SID   AND P.YEAR=C.YEAR
WHERE B.PRICE_TYPE=''QFSS''';
 
 
EXEC sp_executesql @SQL_FCP_PRJ_UPDD4;
 
SET @SQL_FCP_PRJ_UPDD4 = 'UPDATE B SET ACTUAL_PRICE=C.ACTUAL_PRICE
FROM
 
'+@FCP_ACTUAL_TABLE+' B
JOIN (
SELECT NA_PROJ_DETAILS_SID,ACTUAL_PRICE,YEAR FROM '+@FCP_ACTUAL_TABLE+' F JOIN PERIOD P ON P.PERIOD_SID=F.PERIOD_SID WHERE PRICE_TYPE=''AFSS''
)C
ON
C.NA_PROJ_DETAILS_SID=B.NA_PROJ_DETAILS_SID
JOIN PERIOD P
ON P.PERIOD_SID=B.PERIOD_SID   AND P.YEAR=C.YEAR
WHERE B.PRICE_TYPE=''QFSS''';
 
EXEC sp_executesql @SQL_FCP_PRJ_UPDD4;
 
 
-- CEL 357 change  end  
 
 ----------------cel-405
  
set @SQL_FCP_UPD5 = 'UPDATE fa
SET    fa.ACTUAL_PRICE = a.price
FROM   '+@FCP_ACTUAL_TABLE+ N' fa
       JOIN (select a.NA_PROJ_DETAILS_SID,pq.PERIOD_SID,a.PRICE_TYPE,(isnull(ip.ITEM_PRICE,0)-isnull(a.ACTUAL_PRICE,0)) price from (select ACT.NA_PROJ_DETAILS_SID,
                                 ACT.ACTUAL_PRICE,
                                 ACT.PERIOD_SID,
								 pq.period_year,
                                 CASE WHEN ACT.PRICE_TYPE=''AFSS'' THEN ''WAC-FSS'' ELSE ''WAC-FCP'' END  PRICE_TYPE 
								 ,det.ITEM_MASTER_SID
								 from '+@FCP_ACTUAL_TABLE+ ' ACT
join #PERIOD_QUARTER pq on pq.PERIOD_SID=ACT.PERIOD_SID
JOIN NA_PROJ_DETAILS DET ON ACT.NA_PROJ_DETAILS_SID=DET.NA_PROJ_DETAILS_SID AND DET.NA_PROJ_MASTER_SID='+ CAST(@NA_PROJ_MASTER_SID AS VARCHAR(50))+ '
 WHERE  act.price_type IN ( ''AFSS'', ''FCP'' )) a join #PERIOD_QUARTER pq on pq.PERIOD_YEAR=a.PERIOD_YEAR
 left join #ITEM_PRICING ip on ip.ITEM_MASTER_SID=a.ITEM_MASTER_SID and pq.PERIOD_SID=ip.PERIOD_SID and ip.PRICE_TYPE=''WAC'')a
         ON a.NA_PROJ_DETAILS_SID = fa.NA_PROJ_DETAILS_SID
            AND a.PERIOD_SID = fa.PERIOD_SID
            AND a.price_type = fa.price_type ';

			EXEC sp_executesql @SQL_FCP_UPD5;
/*
set @SQL_FCP_UPD5 = 'UPDATE B SET PROJECTION_PRICE=A.price
FROM
 '+@FCP_PROJECTION_TABLE+' B
JOIN 
 (select a.NA_PROJ_DETAILS_SID,pq.PERIOD_SID,a.PRICE_TYPE,(isnull(ip.ITEM_PRICE,0)-isnull(a.PROJECTION_PRICE,0)) price from (select ACT.NA_PROJ_DETAILS_SID,
                                 ACT.PROJECTION_PRICE,
                                 ACT.PERIOD_SID,
								 pq.period_year,
                                 CASE WHEN ACT.PRICE_TYPE=''AFSS'' THEN ''WAC-FSS'' ELSE ''WAC-FCP'' END  PRICE_TYPE 
								 ,det.ITEM_MASTER_SID
								 from '+@FCP_PROJECTION_TABLE+' ACT
join #PERIOD_QUARTER pq on pq.PERIOD_SID=ACT.PERIOD_SID
JOIN NA_PROJ_DETAILS DET ON ACT.NA_PROJ_DETAILS_SID=DET.NA_PROJ_DETAILS_SID AND DET.NA_PROJ_MASTER_SID='+ CAST(@NA_PROJ_MASTER_SID AS VARCHAR(50))+ '
 WHERE  act.price_type IN ( ''AFSS'', ''FCP'' )) a join #PERIOD_QUARTER pq on pq.PERIOD_YEAR=a.PERIOD_YEAR
 left join #ITEM_PRICING ip on ip.ITEM_MASTER_SID=a.ITEM_MASTER_SID and pq.PERIOD_SID=ip.PERIOD_SID and ip.PRICE_TYPE=''WAC'')a
         ON a.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID
            AND a.PERIOD_SID = B.PERIOD_SID
            AND a.price_type = B.price_type '
		---	SELECT @SQL_FCP_UPD5
			EXEC sp_executesql @SQL_FCP_UPD5
			*/



			SET @SQL_FCP_UPD5 = ' IF EXISTS (SELECT 1 FROM '+@FCP_PROJECTION_TABLE+' WHERE  PRICE_TYPE IN ( ''WAC-FSS'' , ''WAC-FCP''  ) )
			BEGIN
			DELETE FROM '+@FCP_PROJECTION_TABLE+' WHERE  PRICE_TYPE IN ( ''WAC-FSS'' , ''WAC-FCP''  )
			END ';
			EXEC SP_EXECUTESQL @SQL_FCP_UPD5;


			SET @SQL_FCP_UPD5 = ' INSERT INTO  '+@FCP_PROJECTION_TABLE+' (NA_PROJ_DETAILS_SID,PERIOD_SID,PRICE_TYPE,PROJECTION_PRICE)

 SELECT A.NA_PROJ_DETAILS_SID,PQ.PERIOD_SID,A.PRICE_TYPE,(ISNULL(IP.ITEM_PRICE,0)-ISNULL(A.PROJECTION_PRICE,0)) PRICE FROM (SELECT ACT.NA_PROJ_DETAILS_SID,
                                 ACT.PROJECTION_PRICE,
                                 ACT.PERIOD_SID,
								 PQ.PERIOD_YEAR,
                                 CASE WHEN ACT.PRICE_TYPE=''AFSS'' THEN ''WAC-FSS'' ELSE ''WAC-FCP'' END  PRICE_TYPE 
								 ,DET.ITEM_MASTER_SID
								 FROM '+@FCP_PROJECTION_TABLE+ ' ACT
JOIN #PERIOD_QUARTER PQ ON PQ.PERIOD_SID=ACT.PERIOD_SID
JOIN NA_PROJ_DETAILS DET ON ACT.NA_PROJ_DETAILS_SID=DET.NA_PROJ_DETAILS_SID AND DET.NA_PROJ_MASTER_SID='+ CAST(@NA_PROJ_MASTER_SID AS VARCHAR(50))+ '
 WHERE  ACT.PRICE_TYPE IN ( ''AFSS'', ''FCP'' )) A JOIN #PERIOD_QUARTER PQ ON PQ.PERIOD_YEAR=A.PERIOD_YEAR
 LEFT JOIN #ITEM_PRICING IP ON IP.ITEM_MASTER_SID=A.ITEM_MASTER_SID AND PQ.PERIOD_SID=IP.PERIOD_SID AND IP.PRICE_TYPE=''WAC''';

 EXEC SP_EXECUTESQL @SQL_FCP_UPD5;
 --------------CEL-316
IF OBJECT_ID('TEMPDB..#FORECAST_PRICE_PERIOD') IS NOT NULL
BEGIN
       DROP TABLE #FORECAST_PRICE_PERIOD;
       END;
DECLARE @ITEM_MASTER_SID INT; 
SELECT @ITEM_MASTER_SID=  ITEM_MASTER_SID FROM  #PROJECTION_DETAILS;
/*
CREATE TABLE #FORECAST_PRICE_PERIOD (
       PRICE_TYPE VARCHAR(100) NULL
       ,PERIOD_SID INT NULL
       );

SET @SQL_FCP_UPD5 ='';
SET @SQL_FCP_UPD5 =CONCAT('
WITH CTE
AS (
       SELECT DISTINCT  PRICE_TYPE 
                       ,CASE PRICE_TYPE WHEN ''CPI-U'' THEN ''CPI''
                                                              WHEN  ''CPI-U ADJUSTMENT''  THEN ''CPI'' 
                                                               WHEN ''FCP DISCOUNT'' THEN ''FCP''
                                                              WHEN ''FSS (OGA) DISCOUNT'' THEN ''AFSS''
                                                              WHEN ''MAX FSS'' THEN ''AFSS''
                                                              WHEN ''WAC INCREASE %'' THEN ''WAC'' 
                                                               WHEN ''WAC-FCP'' THEN ''WAC''
                                                              WHEN ''WAC-FSS'' THEN ''WAC''
                                                              WHEN ''AVGY'' THEN ''WAC'' ELSE PRICE_TYPE END PRICE_TYPE1
                                  ,CASE PRICE_TYPE WHEN ''CPI-U'' THEN ''CPI''
                                                                WHEN  ''CPI-U ADJUSTMENT''  THEN ''CPI'' 
                                                                WHEN ''FCP DISCOUNT'' THEN ''FCP''
                                                                WHEN ''FSS (OGA) DISCOUNT'' THEN ''AFSS''
                                                                WHEN ''MAX FSS'' THEN ''AFSS''
                                                                WHEN ''WAC INCREASE %'' THEN ''WAC'' 
                                                                WHEN ''WAC-FCP'' THEN ''FCP''
                                                                WHEN ''WAC-FSS'' THEN ''AFSS''
                                                                WHEN ''AVGY'' THEN ''WAC'' ELSE PRICE_TYPE END  PRICE_TYPE2
FROM '+@FCP_PROJECTION_TABLE+ '  WHERE PRICE_TYPE NOT IN (''WAC INCREASE %'' , ''WAC'')

       )
       ,CTE2 AS (
       SELECT PRICE_TYPE
              ,PRICE_TYPE1
              ,PRICE_TYPE2
              ,PERIOD_SID PERIOD_SID1
       FROM CTE A
       OUTER APPLY (
              SELECT TOP 1 P.PERIOD_SID
              FROM ITEM_PRICING IP
              JOIN ITEM_PRICING_QUALIFIER IPQ ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                     AND CASE 
                           WHEN IPQ.PRICING_QUALIFIER = ''CPIURA''
                                  THEN ''CPI-URA''
                           ELSE IPQ.PRICING_QUALIFIER
                           END = A.PRICE_TYPE1
              JOIN PERIOD P ON P.PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(COALESCE(END_DATE, START_DATE), 0))))
              WHERE ITEM_MASTER_SID =  ',@ITEM_MASTER_SID,'
              ORDER BY COALESCE(END_DATE, START_DATE) DESC
              ) B
       )
       ,CTE3
AS (
       SELECT PRICE_TYPE
              ,PRICE_TYPE1
              ,PRICE_TYPE2
              ,PERIOD_SID PERIOD_SID2
       FROM CTE A
       OUTER APPLY (
              SELECT TOP 1 P.PERIOD_SID
              FROM ITEM_PRICING IP
              JOIN ITEM_PRICING_QUALIFIER IPQ ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                     AND CASE 
                           WHEN IPQ.PRICING_QUALIFIER = ''CPIURA''
                                  THEN ''CPI-URA''
                           ELSE IPQ.PRICING_QUALIFIER
                           END = A.PRICE_TYPE2
              JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IP.ITEM_UOM
              AND HT.DESCRIPTION='''+@ITEM_UOM+'''
              JOIN PERIOD P ON P.PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(COALESCE(END_DATE, START_DATE), 0))))
              WHERE ITEM_MASTER_SID =  ',@ITEM_MASTER_SID,'
              ORDER BY COALESCE(END_DATE, START_DATE) DESC
              ) B
       )
       INSERT INTO #FORECAST_PRICE_PERIOD(PRICE_TYPE,PERIOD_SID)

SELECT A.PRICE_TYPE
       ,IIF(CASE 
                     WHEN A.PRICE_TYPE1 = INDEX_TYPE
                           THEN C.PERIOD_SID
                     ELSE A.PERIOD_SID1
                     END <= CASE 
                     WHEN A.PRICE_TYPE1 = INDEX_TYPE
                           THEN C.PERIOD_SID
                     ELSE B.PERIOD_SID2
                     END, CASE 
                     WHEN A.PRICE_TYPE1 = INDEX_TYPE
                           THEN C.PERIOD_SID
                     ELSE A.PERIOD_SID1
                     END, CASE 
                     WHEN A.PRICE_TYPE1 = INDEX_TYPE
                           THEN C.PERIOD_SID
                     ELSE B.PERIOD_SID2
                     END) PERIOD_SID
FROM CTE2 A
JOIN CTE3 B ON A.PRICE_TYPE = B.PRICE_TYPE
OUTER APPLY (
       SELECT TOP 1 PERIOD_SID
              ,INDEX_TYPE
       FROM CPI_INDEX_MASTER A
       JOIN PERIOD P ON P.PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(EFFECTIVE_DATE, 0))))
       WHERE INDEX_TYPE = B.PRICE_TYPE1
              AND INBOUND_STATUS <> ''D''
       ORDER BY EFFECTIVE_DATE DESC
       ) C');	   
	   */------cel-1895--------
CREATE TABLE #FORECAST_PRICE_PERIOD (
       PRICE_TYPE VARCHAR(100) NULL
       ,PERIOD_SID INT NULL
	   ,ITEM_MASTER_SID INT
	   ,NA_PROJ_DETAILS_SID INT
       );

SET @SQL_FCP_UPD5 ='';
SET @SQL_FCP_UPD5 =CONCAT('
WITH CTE
AS (
       SELECT DISTINCT P.NA_PROJ_DETAILS_SID,
                           P.ITEM_MASTER_SID,
                           PRICE_TYPE
                       ,CASE PRICE_TYPE WHEN ''CPI-U'' THEN ''CPI''
                                                              WHEN  ''CPI-U ADJUSTMENT''  THEN ''CPI'' 
                                                               WHEN ''FCP DISCOUNT'' THEN ''FCP''
                                                              WHEN ''FSS (OGA) DISCOUNT'' THEN ''AFSS''
                                                              WHEN ''MAX FSS'' THEN ''AFSS''
                                                              WHEN ''WAC INCREASE %'' THEN ''WAC'' 
                                                               WHEN ''WAC-FCP'' THEN ''WAC''
                                                              WHEN ''WAC-FSS'' THEN ''WAC''
                                                              WHEN ''AVGY'' THEN ''WAC'' ELSE PRICE_TYPE END PRICE_TYPE1
                                  ,CASE PRICE_TYPE WHEN ''CPI-U'' THEN ''CPI''
                                                                WHEN  ''CPI-U ADJUSTMENT''  THEN ''CPI'' 
                                                                WHEN ''FCP DISCOUNT'' THEN ''FCP''
                                                                WHEN ''FSS (OGA) DISCOUNT'' THEN ''AFSS''
                                                                WHEN ''MAX FSS'' THEN ''AFSS''
                                                                WHEN ''WAC INCREASE %'' THEN ''WAC'' 
                                                                WHEN ''WAC-FCP'' THEN ''FCP''
                                                                WHEN ''WAC-FSS'' THEN ''AFSS''
                                                                WHEN ''AVGY'' THEN ''WAC'' ELSE PRICE_TYPE END  PRICE_TYPE2
FROM ',@FCP_PROJECTION_TABLE, ' F
                  JOIN #PROJECTION_DETAILS P
                    ON P.NA_PROJ_DETAILS_SID = F.NA_PROJ_DETAILS_SID  WHERE PRICE_TYPE NOT IN (''WAC INCREASE %'' , ''WAC'')

       )
       ,CTE2 AS (
       SELECT A.NA_PROJ_DETAILS_SID,
                  A.ITEM_MASTER_SID,
                  PRICE_TYPE,
                  PRICE_TYPE1,
                  PRICE_TYPE2,
                  PERIOD_SID PERIOD_SID1
           FROM   CTE A
                  OUTER APPLY (SELECT TOP 1 P.PERIOD_SID,
                                            IP.ITEM_MASTER_SID
                               FROM   ITEM_PRICING IP
                                      JOIN ITEM_PRICING_QUALIFIER IPQ
                                        ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                     AND CASE 
                           WHEN IPQ.PRICING_QUALIFIER = ''CPIURA''
                                  THEN ''CPI-URA''
                           ELSE IPQ.PRICING_QUALIFIER
                           END = A.PRICE_TYPE1
						    AND A.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
              JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IP.ITEM_UOM
              AND HT.DESCRIPTION=''',@ITEM_UOM,'''
              JOIN PERIOD P ON P.PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(COALESCE(END_DATE, START_DATE), 0))))
              ORDER BY COALESCE(END_DATE, START_DATE) DESC
              ) B
       )
       ,CTE3
AS (SELECT A.NA_PROJ_DETAILS_SID,
                  A.ITEM_MASTER_SID,
                  PRICE_TYPE,
                  PRICE_TYPE1,
                  PRICE_TYPE2,
                  PERIOD_SID PERIOD_SID2
           FROM   CTE A
                  OUTER APPLY (SELECT TOP 1 P.PERIOD_SID,
                                            IP.ITEM_MASTER_SID
                               FROM   ITEM_PRICING IP
                                      JOIN ITEM_PRICING_QUALIFIER IPQ
                                        ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                     AND CASE 
                           WHEN IPQ.PRICING_QUALIFIER = ''CPIURA''
                                  THEN ''CPI-URA''
                           ELSE IPQ.PRICING_QUALIFIER
                           END = A.PRICE_TYPE2
						    AND A.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
              JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IP.ITEM_UOM
              AND HT.DESCRIPTION=''',@ITEM_UOM,'''
              JOIN PERIOD P ON P.PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(COALESCE(END_DATE, START_DATE), 0))))
              ORDER BY COALESCE(END_DATE, START_DATE) DESC
              ) B
       )
       INSERT INTO #FORECAST_PRICE_PERIOD(NA_PROJ_DETAILS_SID,ITEM_MASTER_SID,PRICE_TYPE,PERIOD_SID)

  SELECT A.NA_PROJ_DETAILS_SID,
         A.ITEM_MASTER_SID,
         A.PRICE_TYPE,
         Iif(CASE
               WHEN A.PRICE_TYPE1 = INDEX_TYPE THEN C.PERIOD_SID
               ELSE A.PERIOD_SID1
             END <= CASE
                      WHEN A.PRICE_TYPE1 = INDEX_TYPE THEN C.PERIOD_SID
                      ELSE B.PERIOD_SID2
                    END, CASE
                           WHEN A.PRICE_TYPE1 = INDEX_TYPE THEN C.PERIOD_SID
                           ELSE A.PERIOD_SID1
                         END, CASE
                                WHEN A.PRICE_TYPE1 = INDEX_TYPE THEN C.PERIOD_SID
                                ELSE B.PERIOD_SID2
                              END) PERIOD_SID
  FROM   CTE2 A
         JOIN CTE3 B
           ON A.PRICE_TYPE = B.PRICE_TYPE
              AND A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
              AND A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID
         OUTER APPLY (SELECT TOP 1 PERIOD_SID,
                                   INDEX_TYPE
                      FROM   CPI_INDEX_MASTER A
                             JOIN PERIOD P
                               ON P.PERIOD_DATE = CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(EFFECTIVE_DATE, 0))))
                      WHERE  INDEX_TYPE = B.PRICE_TYPE1
                             AND INBOUND_STATUS <> ''D''
       ORDER BY EFFECTIVE_DATE DESC
       ) C  where b.PRICE_TYPE2 <>''WAC''');------cel-1895
/*
SET @SQL_FCP_UPD5 =CONCAT('
WITH CTE
AS (
       SELECT DISTINCT P.NA_PROJ_DETAILS_SID,
                           P.ITEM_MASTER_SID,
                           PRICE_TYPE
                       ,CASE PRICE_TYPE WHEN ''CPI-U'' THEN ''CPI''
                                                              WHEN  ''CPI-U ADJUSTMENT''  THEN ''CPI'' 
                                                               WHEN ''FCP DISCOUNT'' THEN ''FCP''
                                                              WHEN ''FSS (OGA) DISCOUNT'' THEN ''AFSS''
                                                              WHEN ''MAX FSS'' THEN ''AFSS''
                                                              WHEN ''WAC INCREASE %'' THEN ''WAC'' 
                                                               WHEN ''WAC-FCP'' THEN ''WAC''
                                                              WHEN ''WAC-FSS'' THEN ''WAC''
                                                              WHEN ''AVGY'' THEN ''WAC'' ELSE PRICE_TYPE END PRICE_TYPE1
                                  ,CASE PRICE_TYPE WHEN ''CPI-U'' THEN ''CPI''
                                                                WHEN  ''CPI-U ADJUSTMENT''  THEN ''CPI'' 
                                                                WHEN ''FCP DISCOUNT'' THEN ''FCP''
                                                                WHEN ''FSS (OGA) DISCOUNT'' THEN ''AFSS''
                                                                WHEN ''MAX FSS'' THEN ''AFSS''
                                                                WHEN ''WAC INCREASE %'' THEN ''WAC'' 
                                                                WHEN ''WAC-FCP'' THEN ''FCP''
                                                                WHEN ''WAC-FSS'' THEN ''AFSS''
                                                                WHEN ''AVGY'' THEN ''WAC'' ELSE PRICE_TYPE END  PRICE_TYPE2
FROM ',@FCP_PROJECTION_TABLE, ' F
                  JOIN #PROJECTION_DETAILS P
                    ON P.NA_PROJ_DETAILS_SID = F.NA_PROJ_DETAILS_SID  WHERE PRICE_TYPE NOT IN (''WAC INCREASE %'' , ''WAC'')

       )
       ,CTE2 AS (
       SELECT A.NA_PROJ_DETAILS_SID,
                  B.ITEM_MASTER_SID,
                  PRICE_TYPE,
                  PRICE_TYPE1,
                  PRICE_TYPE2,
                  PERIOD_SID PERIOD_SID1
           FROM   CTE A
                  OUTER APPLY (SELECT TOP 1 P.PERIOD_SID,
                                            IP.ITEM_MASTER_SID
                               FROM   ITEM_PRICING IP
                                      JOIN ITEM_PRICING_QUALIFIER IPQ
                                        ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                     AND CASE 
                           WHEN IPQ.PRICING_QUALIFIER = ''CPIURA''
                                  THEN ''CPI-URA''
                           ELSE IPQ.PRICING_QUALIFIER
                           END = A.PRICE_TYPE1
						    AND A.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
              JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IP.ITEM_UOM
              AND HT.DESCRIPTION=''',@ITEM_UOM,'''
              JOIN PERIOD P ON P.PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(COALESCE(END_DATE, START_DATE), 0))))
              ORDER BY COALESCE(END_DATE, START_DATE) DESC
              ) B
       )
       ,CTE3
AS (SELECT A.NA_PROJ_DETAILS_SID,
                  B.ITEM_MASTER_SID,
                  PRICE_TYPE,
                  PRICE_TYPE1,
                  PRICE_TYPE2,
                  PERIOD_SID PERIOD_SID2
           FROM   CTE A
                  OUTER APPLY (SELECT TOP 1 P.PERIOD_SID,
                                            IP.ITEM_MASTER_SID
                               FROM   ITEM_PRICING IP
                                      JOIN ITEM_PRICING_QUALIFIER IPQ
                                        ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                     AND CASE 
                           WHEN IPQ.PRICING_QUALIFIER = ''CPIURA''
                                  THEN ''CPI-URA''
                           ELSE IPQ.PRICING_QUALIFIER
                           END = A.PRICE_TYPE2
						    AND A.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
              JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IP.ITEM_UOM
              AND HT.DESCRIPTION=''',@ITEM_UOM,'''
              JOIN PERIOD P ON P.PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(COALESCE(END_DATE, START_DATE), 0))))
              ORDER BY COALESCE(END_DATE, START_DATE) DESC
              ) B
       )
       INSERT INTO #FORECAST_PRICE_PERIOD(NA_PROJ_DETAILS_SID,ITEM_MASTER_SID,PRICE_TYPE,PERIOD_SID)

  SELECT A.NA_PROJ_DETAILS_SID,
         A.ITEM_MASTER_SID,
         A.PRICE_TYPE,
         Iif(CASE
               WHEN A.PRICE_TYPE1 = INDEX_TYPE THEN C.PERIOD_SID
               ELSE A.PERIOD_SID1
             END <= CASE
                      WHEN A.PRICE_TYPE1 = INDEX_TYPE THEN C.PERIOD_SID
                      ELSE B.PERIOD_SID2
                    END, CASE
                           WHEN A.PRICE_TYPE1 = INDEX_TYPE THEN C.PERIOD_SID
                           ELSE A.PERIOD_SID1
                         END, CASE
                                WHEN A.PRICE_TYPE1 = INDEX_TYPE THEN C.PERIOD_SID
                                ELSE B.PERIOD_SID2
                              END) PERIOD_SID
  FROM   CTE2 A
         JOIN CTE3 B
           ON A.PRICE_TYPE = B.PRICE_TYPE
              AND A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
              AND A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID
         OUTER APPLY (SELECT TOP 1 PERIOD_SID,
                                   INDEX_TYPE
                      FROM   CPI_INDEX_MASTER A
                             JOIN PERIOD P
                               ON P.PERIOD_DATE = CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(EFFECTIVE_DATE, 0))))
                      WHERE  INDEX_TYPE = B.PRICE_TYPE1
                             AND INBOUND_STATUS <> ''D''
       ORDER BY EFFECTIVE_DATE DESC
       ) C');*/
EXEC SP_EXECUTESQL @SQL_FCP_UPD5;
/*
SET @SQL_FCP_UPD5 =''
       SET @SQL_FCP_UPD5 = 'UPDATE SFP
       SET PROJECTION_PRICE=0
       FROM '+@FCP_PROJECTION_TABLE+'  SFP
       JOIN #FORECAST_PRICE_PERIOD FPP
       ON SFP.PRICE_TYPE=FPP.PRICE_TYPE
       AND FPP.PERIOD_SID IS NOT NULL
       AND SFP.PERIOD_SID<= CASE WHEN SFP.PRICE_TYPE =''WAC INCREASE %'' then FPP.PERIOD_SID+3 ELSE FPP.PERIOD_SID END';
*/------cel-1895--------
	   SET @SQL_FCP_UPD5 = CONCAT('UPDATE SFP
       SET PROJECTION_PRICE=0
       FROM ',@FCP_PROJECTION_TABLE,'  SFP
       JOIN #FORECAST_PRICE_PERIOD FPP
       ON SFP.PRICE_TYPE=FPP.PRICE_TYPE
	   AND SFP.NA_PROJ_DETAILS_SID = FPP.NA_PROJ_DETAILS_SID
       AND FPP.PERIOD_SID IS NOT NULL
       AND SFP.PERIOD_SID<= CASE WHEN SFP.PRICE_TYPE =''WAC INCREASE %'' THEN FPP.PERIOD_SID+3 ELSE FPP.PERIOD_SID END');
EXEC SP_EXECUTESQL @SQL_FCP_UPD5;


 ------------cel-405
      END TRY
 
      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;
 
          EXEC dbo.USPERRORCOLLECTOR;
 
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