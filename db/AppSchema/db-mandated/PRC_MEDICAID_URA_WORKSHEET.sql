IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_MEDICAID_URA_WORKSHEET'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_MEDICAID_URA_WORKSHEET]
  END

GO

CREATE PROCEDURE [dbo].[PRC_MEDICAID_URA_WORKSHEET] (@NA_PROJ_MASTER_SID INT,
                                                    @PRICE_BASIS        VARCHAR(30),
                                                    @USER_ID            INT,
                                                    @SESSION_ID         VARCHAR(100))
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY

         DECLARE 
@MEDICAID_ACTUAL_TABLE     VARCHAR(200) = CONCAT('ST_MEDICAID_URA_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
@MEDICAID_PROJECTION_TABLE VARCHAR(200) = CONCAT('ST_MEDICAID_URA_PROJ_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),


@NA_ACTUAL_TABLE      VARCHAR(200) = CONCAT('ST_NATIONAL_ASSUMPTIONS_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
@NA_PROJECTION_TABLE  VARCHAR(200) = CONCAT('ST_NATIONAL_ASSUMPTIONS_PROJ_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),

@MEDICATED_TABLE VARCHAR(200) = CONCAT('ST_MEDICAID_NEW_NDC_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
        @NA_NDC9_WAC                  VARCHAR(100) =Concat('ST_NA_NDC9_WAC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

          DECLARE @ACTUAL_START_DATE     DATETIME,
                  @PROJECTION_START_DATE DATETIME,
                  @PROJECTION_END_DATE   DATETIME,
                  @ACTUAL_END_DATE       DATETIME,
                  @BUSINESS_PROCESS_TYPE VARCHAR(50),
                  @PROJ_PERIOD_START_SID INT,
                  @PROJ_PERIOD_END_SID   INT,
                  @ACT_PERIOD_START_SID  INT,
                  @ACT_PERIOD_END_SID    INT
          
set @BUSINESS_PROCESS_TYPE = 'NATIONAL ASSUMPTIONS' --CHANGE TO NATIONAL ASSUMPTIONS

          SELECT @ACTUAL_START_DATE = ACTUAL_START_DATE,
                 @ACTUAL_END_DATE = ACTUAL_END_DATE,
                 @PROJECTION_START_DATE = PROJECTION_START_DATE,
                 @PROJECTION_END_DATE = PROJECTION_END_DATE
          FROM   [DBO].[UDF_NA_PROJ_DATES](@BUSINESS_PROCESS_TYPE)

          ------------------GL COMPANY ID---------------
		    IF OBJECT_ID('TEMPDB..#PERIOD_QUARTER') IS NOT NULL
            DROP TABLE #PERIOD_QUARTER
          CREATE TABLE #PERIOD_QUARTER 
            (
               PERIOD_SID     INT PRIMARY KEY,
               PERIOD_YEAR    INT,
               PERIOD_QUARTER INT,
               SEMI_ANNUAL    INT,
               PERIOD_DATE    DATETIME
            )

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
          FROM   PERIOD
          WHERE  PERIOD_DATE BETWEEN DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_START_DATE), 0) AND @PROJECTION_END_DATE
          GROUP  BY [YEAR],
                    [QUARTER],
                    SEMI_ANNUAL

          SELECT @ACT_PERIOD_START_SID = MAX(CASE
                                               WHEN PERIOD_DATE = DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_START_DATE), 0) THEN PERIOD_SID
                                             END),
                 @ACT_PERIOD_END_SID = MAX(CASE
                                             WHEN PERIOD_DATE = DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_END_DATE), 0) THEN PERIOD_SID
                                           END),
                 @PROJ_PERIOD_START_SID = MAX(CASE
                                                WHEN PERIOD_DATE = DATEADD(QQ, DATEDIFF(QQ, 0, @PROJECTION_START_DATE), 0) THEN PERIOD_SID
                                              END),
                 @PROJ_PERIOD_END_SID = MAX(CASE
                                              WHEN PERIOD_DATE = DATEADD(QQ, DATEDIFF(QQ, 0, @PROJECTION_END_DATE), 0) THEN PERIOD_SID
                                            END)
          FROM   (SELECT PERIOD_SID,
                         PERIOD_DATE
                  FROM   #PERIOD_QUARTER
                  WHERE  PERIOD_DATE IN ( DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_START_DATE), 0), DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_END_DATE), 0), DATEADD(QQ, DATEDIFF(QQ, 0, @PROJECTION_START_DATE), 0), DATEADD(QQ, DATEDIFF(QQ, 0, @PROJECTION_END_DATE), 0) )) A

          ------------------------------------------  ITEM DETAILS TABLE BASED ON PROJECTION 
		        IF OBJECT_ID('TEMPDB..#PROJECTION_DETAILS') IS NOT NULL
            DROP TABLE #PROJECTION_DETAILS
          CREATE TABLE  #PROJECTION_DETAILS 
            (
               NA_PROJ_DETAILS_SID INT PRIMARY KEY,
               ITEM_MASTER_SID     INT UNIQUE,
               NA_PROJ_MASTER_SID  INT,
               NDC9                VARCHAR(20),
               ITEM_ID             VARCHAR(50),
               UPPS                NUMERIC(22, 6)
            )

          INSERT INTO #PROJECTION_DETAILS
                      (NA_PROJ_DETAILS_SID,
                       ITEM_MASTER_SID,
                       NA_PROJ_MASTER_SID,
                       ITEM_ID,
                       NDC9,
                       UPPS)
          SELECT NA_PROJ_DETAILS_SID,
                 NPD.ITEM_MASTER_SID,
                 NA_PROJ_MASTER_SID,
                 ITEM_ID,
                 IM.NDC9,
                 IM.UPPS
          FROM   NA_PROJ_DETAILS NPD
                 INNER JOIN ITEM_MASTER IM
                         ON IM.ITEM_MASTER_SID = NPD.ITEM_MASTER_SID
          WHERE  NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID
		    --------------------------------------------------GALUAT-808-BUSINESS_UNIT-----------------------------------------

		 
          -------------------------------------------------------------------------------------------- WAC CALCULATION STARTS HERE
          IF OBJECT_ID('TEMPDB..#ITEM_PRICING') IS NOT NULL
            TRUNCATE TABLE #ITEM_PRICING
          ELSE
            CREATE TABLE #ITEM_PRICING
              (
                 ITEM_MASTER_SID  INT,
                 PRICE_TYPE       VARCHAR(30),
                 PERIOD_SID       INT,
                 ACTUAL_PRICE     NUMERIC(22, 6),
                 PROJECTION_PRICE NUMERIC(22, 6),
                 WAC_INCREASE     NUMERIC(22, 6),
                 UPPS             NUMERIC(22, 6),
                 BASELINE_AMP     NUMERIC(22, 6),
                 BASE_CPI         NUMERIC(22, 6),
                 NDC9             VARCHAR(20)
                 PRIMARY KEY (ITEM_MASTER_SID, PRICE_TYPE, PERIOD_SID)
              )

          INSERT INTO #ITEM_PRICING
                      (ITEM_MASTER_SID,
                       PRICE_TYPE,
                       PERIOD_SID,
                       NDC9)
          SELECT ID.ITEM_MASTER_SID,
                 PRICE_TYPE,
                 PQ.PERIOD_SID,
                 NDC9
          FROM   #PERIOD_QUARTER PQ
                 CROSS JOIN #PROJECTION_DETAILS ID
                 CROSS JOIN (SELECT PRICE_TYPE
                             FROM   (VALUES('AMP'),
                                           ('BEST PRICE'),
                                           ('CPI-U'),
                                           ('CPI URA'),
                                           ('BASIC URA'),
                                           ('TOTAL URA'),
                                           ('WAC'),
                                           ('CMS UNITS'),
                                           ('LOWEST COMMERCIAL BEST PRICE'),
                                           ('ADJUSTMENT CPI (ALT)'))V(PRICE_TYPE)) PT
          WHERE  PQ.PERIOD_SID BETWEEN @ACT_PERIOD_START_SID AND @ACT_PERIOD_END_SID
                  OR PQ.PERIOD_SID BETWEEN @PROJ_PERIOD_START_SID AND @PROJ_PERIOD_END_SID

          ---------------------------------------------------------------------------------- WAC INCREASE PERCENTAGE UPDATION 
          DECLARE @ITEMID   [DBO].[UDT_ITEM],
                  @ITEM_UOM VARCHAR(50) = 'UN'

          INSERT INTO @ITEMID
          SELECT ITEM_MASTER_SID
          FROM   NA_PROJ_DETAILS
          WHERE  NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID


          DECLARE @CURR_QUART_PERIOD_SID INT


		  IF OBJECT_ID('TEMPDB..#I_PRICING') IS NOT NULL
            DROP TABLE #I_PRICING
          SELECT @CURR_QUART_PERIOD_SID = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = ( DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) - 1, 0) )

          SELECT NDC9 AS NDC,
                 CASE
                              WHEN PRICING_QUALIFIER = @PRICE_BASIS THEN 'WAC'
                            END AS PRICE_TYPE,
                 PQ.PERIOD_SID,
                 ITEM_PRICE AS ACTUAL_PRICE,
                 PERIOD_YEAR,
                 PERIOD_QUARTER,
                 UPPS
          INTO   #I_PRICING
          FROM   UDF_ITEM_PRICING(@ITEMID, @PRICE_BASIS, @ACT_PERIOD_START_SID, @CURR_QUART_PERIOD_SID - 1, @ITEM_UOM) IP
                 INNER JOIN #PERIOD_QUARTER PQ
                         ON IP.PERIOD_SID = PQ.PERIOD_SID
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON PD.ITEM_MASTER_SID = IP.ITEM_MASTER_SID

  declare @sql nvarchar(max)= ''
      IF @PRICE_BASIS IN( 'EQWAC', 'BQWAC', 'MQWAC', 'AVGQWAC' )
        BEGIN 

	SET @sql = 'UPDATE IP
                SET    IP.PROJECTION_PRICE = WAC
                FROM   #ITEM_PRICING IP
                       INNER JOIN (select ndc9 as ndc,period_sid,''wac'' as price_type,'+@PRICE_BASIS+' as wac  from '+ @NA_NDC9_WAC 
               + ') A
                               ON IP.NDC9 = A.ndc
                                  AND IP.PERIOD_SID = A.PERIOD_SID
                                  AND IP.PRICE_TYPE = A.PRICE_TYPE'
    
    EXEC sp_executesql @sql
    
        END

      ELSE IF @PRICE_BASIS = 'DAY WEIGHTED WAC'
        BEGIN 

			SET @sql = 'UPDATE IP
                SET    IP.PROJECTION_PRICE = WAC
                FROM   #ITEM_PRICING IP
                       INNER JOIN (select ndc9 as ndc,period_sid,''wac'' as price_type,DAY_WEIGHTED_WAC as wac  from '+ @NA_NDC9_WAC 
               + ') A
                               ON IP.NDC9 = A.ndc
                                  AND IP.PERIOD_SID = A.PERIOD_SID
                                  AND IP.PRICE_TYPE = A.PRICE_TYPE'
		

							  EXEC sp_executesql @sql

        END
      ELSE IF @PRICE_BASIS = 'SALES WEIGHTED WAC'
        BEGIN 

			SET @sql = 'UPDATE IP
                SET    IP.PROJECTION_PRICE = WAC
                FROM   #ITEM_PRICING IP
                       INNER JOIN (select ndc9 as ndc,period_sid,''wac'' as price_type,sales_WEIGHTED_WAC as wac  from '+ @NA_NDC9_WAC 
               + ') A
                               ON IP.NDC9 = A.ndc
                                  AND IP.PERIOD_SID = A.PERIOD_SID
                                  AND IP.PRICE_TYPE = A.PRICE_TYPE'
							 
							  EXEC sp_executesql @sql
        END


          UPDATE IP
          SET    IP.UPPS = IM.UPPS
          FROM   #ITEM_PRICING IP
                 INNER JOIN ITEM_MASTER IM
                         ON IM.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
          WHERE  PRICE_TYPE = 'WAC';

          WITH WAC_INCREASE_UPDATE_CTE
               AS (SELECT ROW_NUMBER()
                            OVER(
                              PARTITION BY ITEM_MASTER_SID
                              ORDER BY PERIOD_SID ASC) RN,
                          ITEM_MASTER_SID,
                          PRICE_TYPE,
                          PERIOD_SID,
                          PROJECTION_PRICE AS ITEM_PRICE,
                          WAC_INCREASE
                   FROM   #ITEM_PRICING
                   WHERE  PRICE_TYPE = 'WAC')
          UPDATE C
          SET    C.WAC_INCREASE = ISNULL(D.WAC_INCREASE, 0)
          FROM   WAC_INCREASE_UPDATE_CTE C
                 INNER JOIN (SELECT A.ITEM_MASTER_SID,
                                    A.PRICE_TYPE,
                                    A.PERIOD_SID,
                                    A.ITEM_PRICE,
                                    ( ( A.ITEM_PRICE - B.ITEM_PRICE ) / NULLIF(B.ITEM_PRICE, 0) ) * 100 AS WAC_INCREASE
                             FROM   WAC_INCREASE_UPDATE_CTE A
                                    LEFT OUTER JOIN WAC_INCREASE_UPDATE_CTE B
                                                 ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                                    AND A.RN = B.RN + 1) D
                         ON C.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                            AND C.PERIOD_SID = D.PERIOD_SID

          ----------------------------------------------------------------------------REMOVE ALL PRODUCT FROM MEDICAID HISTORICAL TABLE
		DECLARE @SQL1 NVARCHAR(MAX)=''
          SET @SQL1=CONCAT('DELETE MUA
          FROM   ',@MEDICAID_ACTUAL_TABLE,' MUA
          WHERE  EXISTS (SELECT 1
                         FROM   #PROJECTION_DETAILS PD
                         WHERE  PD.NA_PROJ_DETAILS_SID = MUA.NA_PROJ_DETAILS_SID)')
						 EXEC sp_executesql @sql1
             

          ------------------------------------------------------------------------  RE-INSERT EXISTING PRODUCT WITH NEW PRODUCT FOR GIVEN PROJECTION IN  MEDICAID HISTORICAL TABLE 
          SET @SQL1=CONCAT('INSERT INTO ',@MEDICAID_ACTUAL_TABLE,'
                      (NA_PROJ_DETAILS_SID,
                       PERIOD_SID,
                       PRICE_TYPE,
                       ACTUAL_PRICE,
                       BASE_YEAR)
                      
          SELECT NA_PROJ_DETAILS_SID,
                 P.PERIOD_SID,
                 PT.PRICE_TYPE,
                 0,
                 0
          FROM   #PERIOD_QUARTER P
                 CROSS JOIN #PROJECTION_DETAILS PD
                 CROSS JOIN (SELECT PRICE_TYPE
                             FROM   (VALUES(''AMP''),
                                           (''BEST PRICE''),
                                           (''CPI-U''),
                                           (''CPI URA''),
                                           (''BASIC URA''),
                                           (''TOTAL URA''),
                                           (''WAC''),
                                           (''WAC INCREASE %''),
                                           (''CMS UNITS''),
                                           (''LOWEST COMMERCIAL BEST PRICE''),
                                           (''ADJUSTMENT CPI (ALT)''))V(PRICE_TYPE)) PT
          WHERE  P.PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' AND ',@ACT_PERIOD_END_SID,'')
		  EXEC sp_executesql @sql1
		
          ---------------------------------------------------------------------------------------------------- INSERTING ITEM DETAILS (HISTORICAL AND PROJECTED) FOR AMP,BEST PRICE,CPI-U 
          SET @SQL1= CONCAT('UPDATE IP
          SET    IP.ACTUAL_PRICE = NAA.ACTUAL_PRICE
          FROM   #ITEM_PRICING IP
                 INNER JOIN ',@NA_ACTUAL_TABLE,' NAA
                         ON IP.ITEM_MASTER_SID = NAA.ITEM_MASTER_SID
                            AND IP.PRICE_TYPE = NAA.PRICE_TYPE
                            AND IP.PERIOD_SID = NAA.PERIOD_SID
          WHERE  NAA.PRICE_TYPE IN ( ''AMP'', ''BEST PRICE'', ''CPI-U'' )
                 AND NAA.PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' AND ',@ACT_PERIOD_END_SID,'')
				   EXEC sp_executesql @sql1

         SET @SQL1= CONCAT('UPDATE IP
          SET    IP.PROJECTION_PRICE = NAP.PROJECTION_PRICE
          FROM   #ITEM_PRICING IP
                 INNER JOIN ',@NA_PROJECTION_TABLE ,' NAP
                         ON IP.ITEM_MASTER_SID = NAP.ITEM_MASTER_SID
                            AND IP.PRICE_TYPE = NAP.PRICE_TYPE
                            AND IP.PERIOD_SID = NAP.PERIOD_SID
          WHERE  NAP.PRICE_TYPE IN ( ''AMP'', ''BEST PRICE'', ''CPI-U'' )
                 AND NAP.PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' AND ',@PROJ_PERIOD_END_SID,'')
   EXEC sp_executesql @sql1

          ------------------------------------------------------------------------------------------------- INSERTING ITEM DETAILS (HISTORICAL) FOR BASIC,CPI URA AND TOTAL_URA
          UPDATE IP
          SET    IP.ACTUAL_PRICE = URA.ACTUAL_PRICE,
                 IP.PROJECTION_PRICE = URA.ACTUAL_PRICE
          FROM   #ITEM_PRICING IP
                 INNER JOIN (SELECT ITEM_MASTER_SID,
                                    CASE PRICING_QUALIFIER
                                                 WHEN 'BURA' THEN 'BASIC URA'
                                                 WHEN 'CPIURA' THEN 'CPI URA'
                                                 WHEN 'ALTURA' THEN 'ADJUSTMENT CPI (ALT)'
                                                 ELSE 'TOTAL URA'
                                               END AS PRICE_TYPE,
                                    PERIOD_SID,
                                    ITEM_PRICE AS ACTUAL_PRICE
                             FROM   UDF_ITEM_PRICING(@ITEMID, 'BURA,CPIURA,ALTURA,URA', @ACT_PERIOD_START_SID, @PROJ_PERIOD_END_SID, @ITEM_UOM))URA
                         ON IP.ITEM_MASTER_SID = URA.ITEM_MASTER_SID
                            AND IP.PRICE_TYPE = URA.PRICE_TYPE
                            AND IP.PERIOD_SID = URA.PERIOD_SID

          ------------------------------------------- UPDATING MEDICAID ACTUAL TABLE WITH HISTORICAL PRICE FOR ALL PRICE TYPE EXCEPT WAC INCREASE % AND CMS UNITS
         SET @SQL1= CONCAT('UPDATE MUA
          SET    MUA.ACTUAL_PRICE = CASE
                                      WHEN MUA.PRICE_TYPE = ''WAC'' THEN ISNULL(IP.PROJECTION_PRICE, 0)
                                      ELSE ISNULL(IP.ACTUAL_PRICE, 0)
                                    END
          FROM   ',@MEDICAID_ACTUAL_TABLE,' MUA
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON PD.NA_PROJ_DETAILS_SID = MUA.NA_PROJ_DETAILS_SID
                 INNER JOIN #ITEM_PRICING IP
                         ON PD.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                            AND MUA.PERIOD_SID = IP.PERIOD_SID
                            AND MUA.PRICE_TYPE = IP.PRICE_TYPE')
       EXEC sp_executesql @sql1

          ------------------------------------------- UPDATING MEDICAID ACTUAL TABLE WITH HISTORICAL PRICE FOR WAC INCREASE % AND CMS UNITS
           SET @SQL1= CONCAT('UPDATE MUA
          SET    MUA.ACTUAL_PRICE = ISNULL(A.ITEM_PRICE, 0)
          FROM   ',@MEDICAID_ACTUAL_TABLE,' MUA
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON PD.NA_PROJ_DETAILS_SID = MUA.NA_PROJ_DETAILS_SID
                 INNER JOIN (SELECT IP.ITEM_MASTER_SID,
                                    CS.ITEM_PRICE,
                                    CS.PRICE_TYPE,
                                    PERIOD_SID
                             FROM   #ITEM_PRICING IP
                                    CROSS APPLY (VALUES (WAC_INCREASE,
                                                ''WAC INCREASE %''),
                                                        (UPPS,
                                                ''CMS UNITS'')) CS (ITEM_PRICE, PRICE_TYPE)
                             WHERE  IP.PRICE_TYPE = ''WAC'') A
                         ON PD.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                            AND MUA.PERIOD_SID = A.PERIOD_SID
                            AND MUA.PRICE_TYPE = A.PRICE_TYPE')
          EXEC sp_executesql @sql1

        SET @SQL1= CONCAT('UPDATE MUA
          SET    MUA.BASE_YEAR = CASE
                                   WHEN MUA.PRICE_TYPE = ''AMP'' THEN COALESCE(C.BASE_YEAR_AMP, IM.BASELINE_AMP, 0)--------------cel-333
                                   WHEN MUA.PRICE_TYPE = ''CPI-U'' THEN COALESCE(C.BASE_YEAR_CPI, IM.BASE_CPI, 0)
                                 END
          FROM   ',@MEDICAID_ACTUAL_TABLE,' MUA
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON MUA.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
                 INNER JOIN ITEM_MASTER IM
                         ON PD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                 LEFT OUTER JOIN (SELECT MNN.BASE_YEAR_AMP,
                                         MNN.BASE_YEAR_CPI,
                                         ITEM_MASTER_SID
                                  FROM   ',@MEDICATED_TABLE,' MNN
                                         INNER JOIN ITEM_MASTER IM
                                                 ON IM.NDC9 = MNN.NDC9
                                 ) C
                              ON C.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
          WHERE  MUA.PRICE_TYPE IN ( ''AMP'', ''CPI-U'' )')
                    EXEC sp_executesql @sql1    

          -------------------------------------------------------------  INSERTING PRICE TYPE WITH PERIOD_SID FOR ALL ITEM BELONGS TO GIVEN PROJECTION IN MEDICAID PROJECTION TABLE
          SET @SQL1= CONCAT('INSERT INTO ',@MEDICAID_PROJECTION_TABLE,'
                      (NA_PROJ_DETAILS_SID,
                       PERIOD_SID,
                       PRICE_TYPE,
                       PROJECTION_PRICE)
                     
          SELECT NA_PROJ_DETAILS_SID,
                 PERIOD_SID,
                 PRICE_TYPE,
                 0 AS PROJECTION_PRICE
          FROM   (SELECT PD.NA_PROJ_DETAILS_SID,
                         PERIOD_SID
                  FROM   #PROJECTION_DETAILS PD
                         CROSS JOIN #PERIOD_QUARTER PQ
                  WHERE  PQ.PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' AND ',@PROJ_PERIOD_END_SID,'
                  EXCEPT
                  SELECT MUP.NA_PROJ_DETAILS_SID,
                         MUP.PERIOD_SID
                  FROM   ',@MEDICAID_PROJECTION_TABLE,' MUP
                         INNER JOIN #PROJECTION_DETAILS PD
                                 ON MUP.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
                  WHERE  PD.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,'
                       ) A
                 CROSS JOIN (SELECT PRICE_TYPE
                             FROM   (VALUES(''AMP''),
                                           (''BEST PRICE''),
                                           (''CPI-U''),
                                           (''CPI URA''),
                                           (''BASIC URA''),
                                           (''TOTAL URA''),
                                           (''WAC''),
                                           (''WAC INCREASE %''),
                                           (''CMS UNITS''),
                                           (''LOWEST COMMERCIAL BEST PRICE''),
                                           (''ADJUSTMENT CPI (ALT)'')) V(PRICE_TYPE)) PT')
        
		 EXEC sp_executesql @sql1

	


          ;

          WITH PROJ_DETAILS
               AS (SELECT ROW_NUMBER()
                               OVER(
                                 PARTITION BY CD.CONTRACT_MASTER_SID, PD1.ITEM_MASTER_SID
                                 ORDER BY ORDER_DATE DESC) AS RN,
                          PD1.ITEM_MASTER_SID,
                          CD.CONTRACT_MASTER_SID,
                          CD.COMPANY_MASTER_SID,
                          CD.CCP_DETAILS_SID,
                          PD.PROJECTION_DETAILS_SID,
                          PD.PROJECTION_MASTER_SID
                   FROM   #PROJECTION_DETAILS PD1
                          INNER JOIN CCP_DETAILS CD
                                  ON PD1.ITEM_MASTER_SID = CD.ITEM_MASTER_SID
                          INNER JOIN PROJECTION_DETAILS PD
                                  ON PD.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                          INNER JOIN WORKFLOW_MASTER WM
                                  ON WM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                          CROSS APPLY (VALUES (CREATED_DATE),
                                              (MODIFIED_DATE)) CS (ORDER_DATE)
                   WHERE  EXISTS (SELECT 1
                                  FROM   HELPER_TABLE HT
                                  WHERE  HT.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID
                                         AND LIST_NAME = 'WORKFLOWSTATUS'
                                         AND [DESCRIPTION] = 'APPROVED')
                          AND EXISTS (SELECT 1
                                      FROM   PROJECTION_MASTER PM
                                      WHERE  PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                             AND PM.FORECASTING_TYPE = 'NON MANDATED')),
               SALES
               AS (SELECT PD.CONTRACT_MASTER_SID,
                          PD.ITEM_MASTER_SID,
                          SUM(PROJECTION_SALES) AS PROJECTION_SALES,
                          SUM(PROJECTION_UNITS) AS PROJECTION_UNITS,
                          [QUARTER],
                          [YEAR]
                   FROM   NM_SALES_PROJECTION NSP
                          INNER JOIN PROJ_DETAILS PD
                                  ON NSP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                          INNER JOIN PERIOD P
                                  ON NSP.PERIOD_SID = P.PERIOD_SID
                   WHERE  RN = 1
                   GROUP  BY PD.CONTRACT_MASTER_SID,
                             PD.ITEM_MASTER_SID,
                             [QUARTER],
                             [YEAR]),
               DISCOUNT
               AS (SELECT PD.CONTRACT_MASTER_SID,
                          PD.ITEM_MASTER_SID,
                          SUM(PROJECTION_SALES) AS PROJECTION_DISCOUNT,
                          [QUARTER],
                          [YEAR]
                   FROM   NM_DISCOUNT_PROJECTION NDP
                          INNER JOIN PROJ_DETAILS PD
                                  ON NDP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                          INNER JOIN PERIOD P
                                  ON NDP.PERIOD_SID = P.PERIOD_SID
                   WHERE  RN = 1
                   GROUP  BY PD.CONTRACT_MASTER_SID,
                             PD.ITEM_MASTER_SID,
                             [QUARTER],
                             [YEAR]),
               NET_PRICE_CALC
               AS (SELECT S.CONTRACT_MASTER_SID,
                          S.ITEM_MASTER_SID,
                          S.PROJECTION_SALES - D.PROJECTION_DISCOUNT aS NET_SALES,
                          ISNULL(( S.PROJECTION_SALES - D.PROJECTION_DISCOUNT ) / NULLIF(S.PROJECTION_UNITS, 0), 0) AS NET_PRICE_PER_UNIT,
                          S.[QUARTER],
                          S.[YEAR]
                   FROM   SALES S
                          INNER JOIN DISCOUNT D
                                  ON S.CONTRACT_MASTER_SID = D.CONTRACT_MASTER_SID
                                     AND S.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                                     AND S.[QUARTER] = D.[QUARTER]
                                     AND S.[YEAR] = D.[YEAR])
          UPDATE IP
          SET    PROJECTION_PRICE = LOWEST_NM_NET_PRICE
          FROM   #ITEM_PRICING IP
                 INNER JOIN (SELECT MIN(NET_PRICE_PER_UNIT) AS LOWEST_NM_NET_PRICE,
                                    ITEM_MASTER_SID,
                                    PERIOD_SID,
                                    'LOWEST COMMERCIAL BEST PRICE' AS PRICE_TYPE
                             FROM   NET_PRICE_CALC NPC
                                    INNER JOIN #PERIOD_QUARTER PQ
                                            ON NPC.[QUARTER] = PQ.PERIOD_QUARTER
                                               AND NPC.[YEAR] = PQ.PERIOD_YEAR
                             GROUP  BY ITEM_MASTER_SID,
                                       PERIOD_SID) A
                         ON A.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                            AND A.PERIOD_SID = IP.PERIOD_SID
                            AND IP.PRICE_TYPE = A.PRICE_TYPE

          --------------------------------------------------------------------------------------------- UPDATING PROJECTION PRICE FOR WAC, WAC INCREASE % AND CMS UNITS
          SET @SQL1= CONCAT('UPDATE MUP
          SET    MUP.PROJECTION_PRICE = ISNULL(IP.PROJECTION_PRICE, 0)
          FROM   ',@MEDICAID_PROJECTION_TABLE,' MUP
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON PD.NA_PROJ_DETAILS_SID = MUP.NA_PROJ_DETAILS_SID
                 INNER JOIN #ITEM_PRICING IP
                         ON PD.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                            AND MUP.PERIOD_SID = IP.PERIOD_SID
                            AND MUP.PRICE_TYPE = IP.PRICE_TYPE')
							EXEC sp_executesql @sql1
      

            SET @SQL1= CONCAT('UPDATE MUP
          SET    MUP.PROJECTION_PRICE = ISNULL(A.ITEM_PRICE, 0)
          FROM   ',@MEDICAID_PROJECTION_TABLE,'  MUP
                 INNER JOIN #PROJECTION_DETAILS PD
                         ON PD.NA_PROJ_DETAILS_SID = MUP.NA_PROJ_DETAILS_SID
                 INNER JOIN (SELECT IP.ITEM_MASTER_SID,
                                    CS.ITEM_PRICE,
                                    CS.PRICE_TYPE,
                                    PERIOD_SID
                             FROM   #ITEM_PRICING IP
                                    CROSS APPLY (VALUES (WAC_INCREASE,
                                                ''WAC INCREASE %''),
                                                        (UPPS,
                                                ''CMS UNITS'')) CS (ITEM_PRICE, PRICE_TYPE)
                             WHERE  IP.PRICE_TYPE = ''WAC'') A
                         ON PD.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                            AND MUP.PERIOD_SID = A.PERIOD_SID
                            AND MUP.PRICE_TYPE = A.PRICE_TYPE')
							EXEC sp_executesql @sql1
       
          ------------------------------------------------------------------------------------------------ UPDATING FUTURE BASIC URA, CPI URA AND TOTAL URA
          

          SET @SQL1= CONCAT(';WITH URA
               AS (SELECT A.NA_PROJ_DETAILS_SID,
                          PERIOD_SID,
                          CASE
                            WHEN ISNULL(AMP, 0) - ISNULL(BP, 0) > ISNULL(AMP, 0) * 0.231 THEN ISNULL(AMP, 0) - ISNULL(BP, 0)
                            ELSE ISNULL(AMP, 0) * 0.231
                          END BASIC_URA,
                          CASE
                            WHEN ( AMP - ( ( CPI / NULLIF(BASE_YEAR_CPI, 0) ) * BASE_YEAR_AMP ) ) < 1 THEN 0
                            ELSE ( AMP - ( ( CPI / NULLIF(BASE_YEAR_CPI, 0) ) * BASE_YEAR_AMP ) )
                          END CPI_URA
						  ,AMP-----------CEL-337
                   FROM   (SELECT NA_PROJ_DETAILS_SID,
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
                           FROM    ',@MEDICAID_PROJECTION_TABLE,'  MUP
                           WHERE  PRICE_TYPE IN( ''AMP'', ''BEST PRICE'', ''CPI-U'' )
                                  AND EXISTS (SELECT 1
                                              FROM   #PROJECTION_DETAILS PD
                                              WHERE  MUP.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID)
                           GROUP  BY NA_PROJ_DETAILS_SID,
                                     PERIOD_SID) A
                          INNER JOIN (SELECT BASE_YEAR_AMP= COALESCE(C.BASE_YEAR_AMP, BASELINE_AMP),
                                             BASE_YEAR_CPI=COALESCE(C.BASE_YEAR_CPI, BASE_CPI),
                                             NA_PROJ_DETAILS_SID
                                      FROM   ITEM_MASTER I
                                             INNER JOIN #PROJECTION_DETAILS PD
                                                     ON I.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
                                             LEFT OUTER JOIN (SELECT MNN.BASE_YEAR_AMP,
                                                                     MNN.BASE_YEAR_CPI,
                                                                     ITEM_MASTER_SID
                                                              FROM   ',@MEDICATED_TABLE,' MNN
                                                                     INNER JOIN ITEM_MASTER IM
                                                                             ON IM.NDC9 = MNN.NDC9
                                                              ) C
                                                          ON C.ITEM_MASTER_SID = I.ITEM_MASTER_SID) B
                                  ON A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID)
		
			
UPDATE MUP
          SET    MUP.PROJECTION_PRICE = ISNULL(ITEM_PRICE, 0)
          FROM    ',@MEDICAID_PROJECTION_TABLE,' MUP
                 INNER JOIN (SELECT NA_PROJ_DETAILS_SID,
                                    PERIOD_SID,
                                    ITEM_PRICE,
                                    PRICE_TYPE
                             FROM   URA
                                    CROSS APPLY (VALUES (BASIC_URA,
                                                ''BASIC URA''),
                                                        (CPI_URA,
                                                ''CPI URA''),
                                                        ( CASE WHEN (ISNULL(BASIC_URA, 0) + ISNULL(CPI_URA, 0)) > AMP THEN AMP ELSE (ISNULL(BASIC_URA, 0) + ISNULL(CPI_URA, 0)) END,--------CEL-337
                                                ''TOTAL URA'')) CS (ITEM_PRICE, PRICE_TYPE)) A
                         ON MUP.NA_PROJ_DETAILS_SID = A.NA_PROJ_DETAILS_SID
                            AND MUP.PRICE_TYPE = A.PRICE_TYPE
                            AND MUP.PERIOD_SID = A.PERIOD_SID')
							EXEC sp_executesql @sql1

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
          )
      END CATCH
  END 


GO


