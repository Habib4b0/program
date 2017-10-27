IF OBJECT_ID('PRC_M_DISCOUNT_INSERT','P') IS NOT NULL
 DROP PROCEDURE PRC_M_DISCOUNT_INSERT
 GO

CREATE PROCEDURE [dbo].[PRC_M_DISCOUNT_INSERT](@PROJECTION_MASTER_SID  INT,
                                               @USER_ID               INT,
                                               @MARKET_TYPE           VARCHAR(100),
                                               @SESSION_ID            varchar(100))
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          DECLARE @FROM_DATE    DATETIME,
                  @START_ACTUAL DATETIME,
                  @COUNT        INT=0
		  DECLARE @SQL NVARCHAR(MAX)
          SET @FROM_DATE= (SELECT DATEADD(YY, DATEDIFF(YY, 0, GETDATE()) - 3, 0))
          SET @START_ACTUAL = (SELECT DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0))

		DECLARE @CCP_HIERARCHY                VARCHAR(100) = Concat('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				@M_DISCOUNT_PROJECTION        VARCHAR(100) = Concat('ST_M_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				@ST_M_SALES_PROJECTION_MASTER VARCHAR(100)= Concat('ST_M_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				@ST_M_SALES_PROJECTION        VARCHAR(100)= Concat('ST_M_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				@M_ACTUAL_DISCOUNT            VARCHAR(100)= Concat('ST_M_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')) 



		       IF Object_id('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL
        DROP TABLE #CCP_DETAILS_TEMP

      CREATE TABLE #CCP_DETAILS_TEMP
        (
           CCP_DETAILS_SID           INT,
           COMPANY_MASTER_SID        INT,
           ITEM_MASTER_SID           INT,
           CONTRACT_MASTER_SID       INT   )

SET @SQL='INSERT INTO #CCP_DETAILS_TEMP
            (CCP_DETAILS_SID,
             COMPANY_MASTER_SID,
             ITEM_MASTER_SID,
             CONTRACT_MASTER_SID)
SELECT cd.CCP_DETAILS_SID,
       COMPANY_MASTER_SID,
       ITEM_MASTER_SID,
       CONTRACT_MASTER_SID
FROM   CCP_DETAILS cd
       JOIN '+@CCP_HIERARCHY+' ch
         ON ch.CCP_DETAILS_SID = cd.CCP_DETAILS_SID '
EXEC sp_executesql @SQL


		   --------------------------------------------------- PERIODS 
                       IF OBJECT_ID('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
                           DROP TABLE #PERIOD;

						   CREATE TABLE #PERIOD
						   (
						   PERIOD_SID INT,
						   PERIOD_DATE DATE,
						   [QUARTER] INT,
						   [YEAR] INT
						   PRIMARY KEY (PERIOD_SID,PERIOD_DATE,[QUARTER],[YEAR])
						   )
						INSERT INTO  #PERIOD
						(
						PERIOD_SID,
						PERIOD_DATE,
						[QUARTER],
						[YEAR]
						)
                       SELECT     PERIOD_SID,
                                  PERIOD_DATE,
                                  [QUARTER],
                                  [YEAR]
                       FROM   PERIOD

          -----------------------EFFECTIVE DATE-----------------------
          IF OBJECT_ID('TEMPDB..#TEMP_EFFECT') IS NOT NULL
            DROP TABLE #TEMP_EFFECT

          CREATE TABLE #TEMP_EFFECT
            (
               PROJECTION_MASTER_SID  INT,
               CCP_DETAILS_SID INT,
               START_DATE             DATETIME,
               END_DATE               DATETIME,
			   START_SID              INT,
			   END_SID                INT,
               MIN_START_DATE         DATETIME,
               MAX_END_DATE           DATETIME
            )

				 IF OBJECT_ID('TEMPDB..#EFFECTIVE_DATE') IS NOT NULL
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

			          INSERT INTO #EFFECTIVE_DATE
                      (CCP_DETAILS_SID,
                       RS_MODEL_SID,
					   RS_CONTRACT_SID,
                       RS_CATOGERY,
                       START_DATE,
                       END_DATE,
					   START_SID,
					   END_SID)
          EXEC Prc_date_finder
            @PROJECTION_MASTER_SID,
            'C',
            @USER_ID,
            @SESSION_ID


          INSERT INTO #TEMP_EFFECT
                      (PROJECTION_MASTER_SID,
                       CCP_DETAILS_SID,
                       START_DATE,
                       END_DATE,
					   START_SID,
					   END_SID,
                       MIN_START_DATE,
                       MAX_END_DATE)
        SELECT 
		              PROJECTION_MASTER_SID,
		              CCP_DETAILS_SID,
		              START_DATE,
		              END_DATE,
					   MAX(CASE
                                      WHEN MIN_START_DATE = P.PERIOD_DATE THEN P.PERIOD_SID
                                      END) AS START_SID,

                       MAX(CASE
                                      WHEN ( DATEADD(DAY, 1, EOMONTH(MAX_END_DATE, -1)) ) = P1.PERIOD_DATE THEN P1.PERIOD_SID
                                      END) AS END_SID ,
		              MIN_START_DATE,         
		              MAX_END_DATE           
					  FROM
		 ( SELECT 
		         @PROJECTION_MASTER_SID PROJECTION_MASTER_SID,
                 CCP_DETAILS_SID,
                 START_DATE,
                 END_DATE,
                 DATEADD(DD, -DAY(MIN_START_DATE) + 1, MIN_START_DATE) AS MIN_START_DATE,
                 DATEADD(DD, -DAY(MAX_END_DATE) + 1, MAX_END_DATE) AS MAX_END_DATE
          FROM   (SELECT F.CCP_DETAILS_SID,
                         START_DATE,
                         END_DATE,
                         MIN(START_DATE)
                                          OVER (
                                            PARTITION BY F.CCP_DETAILS_SID) AS MIN_START_DATE,
                         MAX(END_DATE)
                                        OVER (
                                          PARTITION BY F.CCP_DETAILS_SID) AS MAX_END_DATE
                  FROM   #EFFECTIVE_DATE F
						  )A)B
						 LEFT  JOIN #PERIOD  P   ON P.PERIOD_DATE = B.START_DATE
						 LEFT  JOIN #PERIOD  P1 ON P1.PERIOD_DATE = ( DATEADD(DAY, 1, EOMONTH(B.END_DATE, -1)) )
						 GROUP BY PROJECTION_MASTER_SID,CCP_DETAILS_SID, START_DATE,END_DATE,MIN_START_DATE,MAX_END_DATE
		

          ---------------------EFFECTIVE ENDS HERE------------------

		  IF OBJECT_ID('TEMPDB.DBO.#SALES_FREQUENCY_DATE', 'U') IS NOT NULL
                       DROP TABLE #SALES_FREQUENCY_DATE

                     CREATE TABLE #SALES_FREQUENCY_DATE
                       (
                           
                           CCP_DETAILS_SID INT,
                           ALLOCATION_START_DATE  DATETIME,
                           ALLOCATION_END_DATE    DATETIME,
                           FREQUENCY              CHAR(6),
						   ZERO_OUT_START         DATETIME,
						   ZERO_OUT_END           DATETIME
                           PRIMARY KEY (CCP_DETAILS_SID)
                       )

					   
					   SET @SQL = '   INSERT INTO #SALES_FREQUENCY_DATE
                                         (
                                         CCP_DETAILS_SID,
                                         ALLOCATION_START_DATE,
                                         ALLOCATION_END_DATE,
										 FREQUENCY,
										 ZERO_OUT_START,
										 ZERO_OUT_END)
			
			SELECT B.CCP_DETAILS_SID,
	               B.ALLOCATION_START_DATE,
				   B.ALLOCATION_END_DATE,
				   B.FREQUENCY,
				   IIF(B.ALLOCATION_START_DATE < B.MIN_START_DATE ,B.ALLOCATION_START_DATE ,B.MIN_START_DATE )ZERO_OUT_START,
				   IIF(B.ALLOCATION_END_DATE   > B.MAX_END_DATE   ,B.ALLOCATION_END_DATE   ,B.MAX_END_DATE  )ZERO_OUT_END
	 		FROM
			(SELECT A.CCP_DETAILS_SID,
					ALLOCATION_START_DATE=MAX(CASE WHEN ALLOCATION_BASIS IS NULL THEN MIN_START_DATE ELSE
												  CASE
												      WHEN FREQUENCY = ''Q'' THEN DATEADD(QQ, DATEDIFF(QQ, 0,MIN_START_DATE), 0) 
												      WHEN FREQUENCY = ''S'' AND MONTH(MIN_START_DATE) <= 6 THEN DATEFROMPARTS(YEAR(MIN_START_DATE),01,01) 
												      WHEN FREQUENCY = ''S'' AND MONTH(MIN_START_DATE) > 6 THEN DATEFROMPARTS(YEAR(MIN_START_DATE),07,01) 
												      WHEN FREQUENCY = ''M'' THEN MIN_START_DATE
													  ELSE DATEFROMPARTS(YEAR(MIN_START_DATE),01,01) 
												  END
												  END),
					 ALLOCATION_END_DATE=MAX(CASE WHEN ALLOCATION_BASIS IS NULL THEN MAX_END_DATE ELSE
												  CASE
												      WHEN FREQUENCY = ''Q'' THEN DATEADD (MM, -1,DATEADD(QQ, DATEDIFF(QQ, 0,MAX_END_DATE) + 1, 0))  
												      WHEN FREQUENCY = ''S'' AND MONTH(MAX_END_DATE) <= 6 THEN DATEFROMPARTS(YEAR(MAX_END_DATE),06,01)  
												      WHEN FREQUENCY = ''S'' AND MONTH(MAX_END_DATE) > 6 THEN DATEFROMPARTS(YEAR(MAX_END_DATE),12,01)
												      WHEN FREQUENCY = ''M'' THEN MAX_END_DATE
													  ELSE DATEFROMPARTS(YEAR(MAX_END_DATE),01,01)
												 END
												 END),
												 MIN_START_DATE,
												 MAX_END_DATE,
												 FREQUENCY
							 FROM #TEMP_EFFECT A
							 INNER JOIN (SELECT CCP_DETAILS_SID,
												FREQUENCY=LEFT(CALCULATION_PERIODS,1),
												ALLOCATION_BASIS 
										  FROM '+@ST_M_SALES_PROJECTION_MASTER+' ) SMSPM
							 ON A.CCP_DETAILS_SID=SMSPM.CCP_DETAILS_SID
							 CROSS APPLY #PERIOD
							 GROUP BY A.CCP_DETAILS_SID,FREQUENCY, A.MIN_START_DATE, A.MAX_END_DATE) B'

					

                  EXEC sp_executesql @SQL
				 

          ---------------- TO FIND THE APPROVED CCP DETAILS START---------------------
          IF OBJECT_ID('TEMPDB..#APPROVED_CCP_DETAILS') IS NOT NULL
            DROP TABLE #APPROVED_CCP_DETAILS

          --ELSE
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

          EXEC Prc_approved_ccp_details
            @PROJECTION_MASTER_SID,
            'Mandated',
            @USER_ID,
            @SESSION_ID

          ---------------- TO FIND THE APPROVED CCP DETAILS END---------------------
          ------------------------- ACTUAL ENDS HERE	--------------
		
          IF OBJECT_ID('TEMPDB..#M_ACTUAL_DISCOUNT') IS NOT NULL
            DROP TABLE #M_ACTUAL_DISCOUNT

          CREATE TABLE #M_ACTUAL_DISCOUNT
            (
               CCP_DETAILS_SID INT,
               PERIOD_SID             INT,
               ACTUAL_SALES           NUMERIC(22, 6),
               ACTUAL_RATE            NUMERIC(22, 6),
               ACTUAL_RPU             NUMERIC(22, 6)
            )

          INSERT INTO #M_ACTUAL_DISCOUNT
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_RATE,
                       ACTUAL_RPU)
        SELECT A.CCP_DETAILS_SID,
               P.PERIOD_SID,
               Sum(DISCOUNT)                                              ACTUAL_SALES,
               Isnull(( Sum(DISCOUNT) ) / NULLIF(Max(SALES), 0), 0) * 100 ACTUAL_RATE,
               Isnull(( Sum(DISCOUNT) ) / NULLIF(Max(QUANTITY), 0), 0)    ACTUAL_RPU
        FROM #CCP_DETAILS_TEMP  A
		CROSS JOIN #PERIOD P 
		JOIN    
		   ACTUALS_DETAILS B ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID
        WHERE  QUANTITY_INCLUSION = 'Y' AND P.PERIOD_DATE BETWEEN @FROM_DATE AND @START_ACTUAL
        GROUP  BY A.CCP_DETAILS_SID,
                  P.PERIOD_SID 
        
					SET @SQL='truncate table '+@M_ACTUAL_DISCOUNT
					EXEC sp_executesql @SQL

          --DELETE D
          --FROM   M_ACTUAL_DISCOUNT D
          --       JOIN PROJECTION_DETAILS P
          --         ON D.PROJECTION_DETAILS_SID = P.PROJECTION_DETAILS_SID
          --            AND P.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
          --            AND D.USER_ID = @USER_ID
          --            AND D.SESSION_ID = @SESSION_ID




  SET @SQL= CONCAT('INSERT INTO ',@M_ACTUAL_DISCOUNT,'
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_RATE,
                       ACTUAL_RPU)
                SELECT A.CCP_DETAILS_SID,
               P.PERIOD_SID,
               Sum(DISCOUNT)                                              ACTUAL_SALES,
               Isnull(( Sum(DISCOUNT) ) / NULLIF(Max(SALES), 0), 0) * 100 ACTUAL_RATE,
               Isnull(( Sum(DISCOUNT) ) / NULLIF(Max(QUANTITY), 0), 0)    ACTUAL_RPU
        FROM #CCP_DETAILS_TEMP  A
		CROSS JOIN #PERIOD P 
		LEFT JOIN    
		   ACTUALS_DETAILS B ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID AND B.PERIOD_SID=P.PERIOD_SID
        WHERE  QUANTITY_INCLUSION = ''Y'' AND P.PERIOD_DATE BETWEEN @FROM_DATE AND @START_ACTUAL
        GROUP  BY A.CCP_DETAILS_SID,
                  P.PERIOD_SID ')


				  EXEC SP_EXECUTESQL @SQL,N' @FROM_DATE DATETIME,@START_ACTUAL DATETIME',@START_ACTUAL=@START_ACTUAL,@FROM_DATE=@FROM_DATE

          ----------------------- ACTUAL ENDS HERE	--------------
          ----------------------- PROJECTION STARTS HERE	--------------

					SET @SQL='truncate table '+@M_DISCOUNT_PROJECTION
					EXEC sp_executesql @SQL

          --DELETE D
          --FROM   M_DISCOUNT_PROJECTION D
          --       JOIN PROJECTION_DETAILS P
          --         ON D.PROJECTION_DETAILS_SID = P.PROJECTION_DETAILS_SID
          --            AND P.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
          --            AND D.USER_ID = @USER_ID
          --            AND D.SESSION_ID = @SESSION_ID

          IF OBJECT_ID('TEMPDB..#TEMP_ITEM') IS NOT NULL
            DROP TABLE #TEMP_ITEM

          CREATE TABLE #TEMP_ITEM
            (
               ITEM_MASTER_SID     INT,
               [QUARTER]             INT,
               [YEAR]                INT,
               PROJECTION_PRICE    NUMERIC(22, 6),
               NA_PROJ_DETAILS_SID INT
            )

          IF OBJECT_ID('TEMPDB..#TEMP_CCP') IS NOT NULL
            DROP TABLE #TEMP_CCP

          CREATE TABLE #TEMP_CCP
            (
               ID              INT IDENTITY (1, 1),
               ITEM_MASTER_SID INT
            )

          DECLARE @LOOPCOUNTER INT =1
          DECLARE @ROW_COUNT INT
          DECLARE @ITEM INT

          INSERT INTO #TEMP_CCP
                      (ITEM_MASTER_SID)
          SELECT DISTINCT ITEM_MASTER_SID
          FROM   #CCP_DETAILS_TEMP

          SET @ROW_COUNT=@@ROWCOUNT

          WHILE( @LOOPCOUNTER <= @ROW_COUNT )
            BEGIN
                SELECT @ITEM = ITEM_MASTER_SID
                FROM   #TEMP_CCP
                WHERE  ID = @LOOPCOUNTER

                IF ( @MARKET_TYPE = 'PHS' )
                  BEGIN
                      INSERT INTO #TEMP_ITEM
                                  (ITEM_MASTER_SID,
                                   [QUARTER],
                                   [YEAR],
                                   PROJECTION_PRICE,
                                   NA_PROJ_DETAILS_SID)
                      SELECT @ITEM,
                             P.[QUARTER],
                             P.[YEAR],
                             PROJECTION_PRICE,
                             B.NA_PROJ_DETAILS_SID
                      FROM   [DBO].[PHS_PROJ] B
                             JOIN (SELECT TOP 1 NA_PD.NA_PROJ_DETAILS_SID
                                   FROM   NA_PROJ_DETAILS NA_PD
                                          JOIN NA_PROJ_MASTER NA_PM
                                            ON NA_PM.NA_PROJ_MASTER_SID = NA_PD.NA_PROJ_MASTER_SID
                                               AND NA_PD.ITEM_MASTER_SID = @ITEM
                                   WHERE  SAVE_FLAG = 1
                                   ORDER  BY NA_PM.MODIFIED_DATE DESC) C
                               ON B.NA_PROJ_DETAILS_SID = C.NA_PROJ_DETAILS_SID
                             JOIN DBO.#PERIOD P
                               ON P.PERIOD_SID = B.PERIOD_SID
                      WHERE  B.PRICE_TYPE = 'PHS'
                  END
			
               ELSE IF ( @MARKET_TYPE IN ( 'FCP', 'FEDERAL' ) )
                  BEGIN
                      INSERT INTO #TEMP_ITEM
                                  (ITEM_MASTER_SID,
                                   [QUARTER],
                                   [YEAR],
                                   PROJECTION_PRICE,
                                   NA_PROJ_DETAILS_SID)
                      SELECT @ITEM,
                             P.[QUARTER],
                             P.[YEAR],
                             PROJECTION_PRICE,
                             B.NA_PROJ_DETAILS_SID
                      FROM   [DBO].[FCP_PROJ] B
                             JOIN (SELECT TOP 1 NA_PD.NA_PROJ_DETAILS_SID
                                   FROM   NA_PROJ_DETAILS NA_PD
                                          JOIN NA_PROJ_MASTER NA_PM
                                            ON NA_PM.NA_PROJ_MASTER_SID = NA_PD.NA_PROJ_MASTER_SID
                                               AND NA_PD.ITEM_MASTER_SID = @ITEM
                                   WHERE  SAVE_FLAG = 1
                                   ORDER  BY NA_PM.MODIFIED_DATE DESC) C
                               ON B.NA_PROJ_DETAILS_SID = C.NA_PROJ_DETAILS_SID
                             JOIN DBO.#PERIOD P
                               ON P.PERIOD_SID = B.PERIOD_SID
                      WHERE  B.PRICE_TYPE = 'FCP'
                  END
                ELSE --( 'MEDICAID FFS', 'ADAP', 'SPAP', 'MANAGED MEDICAID' ) )
                  BEGIN
                      IF( @MARKET_TYPE = 'SPAP' )
                        BEGIN
                            SELECT @COUNT = COUNT(1)
                            FROM   (SELECT CONTRACT_MASTER_SID
                                    FROM   CONTRACT_MASTER CM
                                           JOIN HELPER_TABLE H
                                             ON H.HELPER_TABLE_SID = CM.CONTRACT_TYPE
                                    WHERE  H.DESCRIPTION = 'SPAP')CM
                                   JOIN RS_CONTRACT RSC
                                     ON RSC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID
                                   JOIN RS_CONTRACT_DETAILS RSCD
                                     ON RSCD.RS_CONTRACT_SID = RSC.RS_CONTRACT_SID
                                        AND ITEM_MASTER_SID = @ITEM
                                   JOIN (SELECT CONTRACT_MASTER_SID,
                                                B.COMPANY_MASTER_SID,
                                                ITEM_MASTER_SID,
                                                COM.COMPANY_ID
                                         FROM   PROJECTION_DETAILS A
                                                JOIN CCP_DETAILS B
                                                  ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                                JOIN COMPANY_MASTER COM
                                                  ON COM.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID
                                         WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)C
                                     ON C.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID
                                        AND C.ITEM_MASTER_SID = RSCD.ITEM_MASTER_SID
                                   LEFT JOIN FORMULA_DETAILS_MASTER FDM
                                          ON FDM.FORMULA_ID = RSCD.FORMULA_ID
                                             AND FDM.COMPANY_ID = C.COMPANY_ID
                                             AND CHARINDEX('BASIC URA', FDM.FORMULA_DESC) > 1

                            IF ( @COUNT >= 1 )
                              BEGIN
                                  INSERT INTO #TEMP_ITEM
                                              (ITEM_MASTER_SID,
                                               [QUARTER],
                                               [YEAR],
                                               PROJECTION_PRICE,
                                               NA_PROJ_DETAILS_SID)
                                  SELECT @ITEM,
                                         P.[QUARTER],
                                         P.[YEAR],
                                         PROJECTION_PRICE,
                                         B.NA_PROJ_DETAILS_SID
                                  FROM   [DBO].[MEDICAID_URA_PROJ] B
                                         JOIN (SELECT TOP 1 NA_PD.NA_PROJ_DETAILS_SID
                                               FROM   NA_PROJ_DETAILS NA_PD
                                                      JOIN NA_PROJ_MASTER NA_PM
                                                        ON NA_PM.NA_PROJ_MASTER_SID = NA_PD.NA_PROJ_MASTER_SID
                                                           AND NA_PD.ITEM_MASTER_SID = @ITEM
                                               WHERE  SAVE_FLAG = 1
                                               ORDER  BY NA_PM.MODIFIED_DATE DESC) C
                                           ON B.NA_PROJ_DETAILS_SID = C.NA_PROJ_DETAILS_SID
                                         JOIN DBO.#PERIOD P
                                           ON P.PERIOD_SID = B.PERIOD_SID
                                  WHERE  B.PRICE_TYPE = 'BASIC URA'
                              END
                            ELSE
                              BEGIN
                                  INSERT INTO #TEMP_ITEM
                                              (ITEM_MASTER_SID,
                                               [QUARTER],
                                               [YEAR],
                                               PROJECTION_PRICE,
                                               NA_PROJ_DETAILS_SID)
                                  SELECT @ITEM,
                                         P.[QUARTER],
                                         P.[YEAR],
                                         PROJECTION_PRICE,
                                         B.NA_PROJ_DETAILS_SID
                                  FROM   [DBO].[MEDICAID_URA_PROJ] B
                                         JOIN (SELECT TOP 1 NA_PD.NA_PROJ_DETAILS_SID
                                               FROM   NA_PROJ_DETAILS NA_PD
                                                      JOIN NA_PROJ_MASTER NA_PM
                                                        ON NA_PM.NA_PROJ_MASTER_SID = NA_PD.NA_PROJ_MASTER_SID
                                                           AND NA_PD.ITEM_MASTER_SID = @ITEM
                                               WHERE  SAVE_FLAG = 1
                                               ORDER  BY NA_PM.MODIFIED_DATE DESC) C
                                           ON B.NA_PROJ_DETAILS_SID = C.NA_PROJ_DETAILS_SID
                                         JOIN DBO.#PERIOD P
                                           ON P.PERIOD_SID = B.PERIOD_SID
                                  WHERE  B.PRICE_TYPE = 'TOTAL URA'
                              END
                        END
                      ELSE
                        BEGIN
                            INSERT INTO #TEMP_ITEM
                                        (ITEM_MASTER_SID,
                                         [QUARTER],
                                         [YEAR],
                                         PROJECTION_PRICE,
                                         NA_PROJ_DETAILS_SID)
                            SELECT @ITEM,
                                   P.[QUARTER],
                                   P.[YEAR],
                                   PROJECTION_PRICE,
                                   B.NA_PROJ_DETAILS_SID
                            FROM   [DBO].[MEDICAID_URA_PROJ] B
                                   JOIN (SELECT TOP 1 NA_PD.NA_PROJ_DETAILS_SID
                                         FROM   NA_PROJ_DETAILS NA_PD
                                                JOIN NA_PROJ_MASTER NA_PM
                                                  ON NA_PM.NA_PROJ_MASTER_SID = NA_PD.NA_PROJ_MASTER_SID
                                                     AND NA_PD.ITEM_MASTER_SID = @ITEM
                                         WHERE  SAVE_FLAG = 1
                                         ORDER  BY NA_PM.MODIFIED_DATE DESC) C
                                     ON B.NA_PROJ_DETAILS_SID = C.NA_PROJ_DETAILS_SID
                                   JOIN DBO.#PERIOD P
                                     ON P.PERIOD_SID = B.PERIOD_SID
                            WHERE  B.PRICE_TYPE = 'TOTAL URA'
                        END
                  END

                SET @COUNT=0
                SET @LOOPCOUNTER+=1
            END



		SET @SQL = CONCAT('INSERT INTO ',@M_DISCOUNT_PROJECTION,'
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       PROJECTION_SALES,
                       PROJECTION_RATE,
                       PROJECTION_RPU)
          SELECT A.CCP_DETAILS_SID,
                 B.PERIOD_SID,
                 COALESCE(PRO.PROJECTION_SALES, ( B.PROJECTION_UNITS * URA.PROJECTION_PRICE * CASE
                                                                                                WHEN IM.UPPS IS NULL
                                                                                                      OR IM.UPPS = 0 THEN 1
                                                                                                ELSE IM.UPPS
                                                                                              END ), 0)                                         AS MANDATED_DISCOUNT,
                 COALESCE(PRO.PROJECTION_RATE, ( ( B.PROJECTION_UNITS * URA.PROJECTION_PRICE * CASE
                                                                                                 WHEN IM.UPPS IS NULL
                                                                                                       OR IM.UPPS = 0 THEN 1
                                                                                                 ELSE IM.UPPS
                                                                                               END ) / NULLIF(B.PROJECTION_SALES, 0) ) * 100, 0)AS MANDATED_DISCOUNT_RATE,
                 COALESCE(PRO.PROJECTION_RPU, ( ( B.PROJECTION_UNITS * URA.PROJECTION_PRICE * CASE
                                                                                                WHEN IM.UPPS IS NULL
                                                                                                      OR IM.UPPS = 0 THEN 1
                                                                                      ELSE IM.UPPS
                                                                                              END ) / NULLIF(B.PROJECTION_UNITS, 0) ), 0)       AS MANDATED_DISCOUNT_RPU
 FROM   ',@ST_M_SALES_PROJECTION_MASTER,' A
                 JOIN ',@ST_M_SALES_PROJECTION,' B
                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                 JOIN #CCP_DETAILS_TEMP C
                   ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                 JOIN DBO.ITEM_MASTER IM
                   ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                 JOIN #PERIOD PRD
                   ON PRD.PERIOD_SID = B.PERIOD_SID
				 JOIN #TEMP_ITEM URA
                        ON URA.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                           AND PRD.[QUARTER] = URA.[QUARTER]
                           AND URA.[YEAR] = PRD.[YEAR]
                 LEFT JOIN #SALES_FREQUENCY_DATE TU
                   ON TU.CCP_DETAILS_SID = C.CCP_DETAILS_SID
                      AND PRD.PERIOD_DATE BETWEEN ALLOCATION_START_DATE AND ALLOCATION_END_DATE
                  
                 LEFT OUTER JOIN (SELECT MDP.PROJECTION_RATE,
                                         MDP.PROJECTION_SALES,
                                         MDP.PROJECTION_RPU,
                                         ACD.CCP_DETAILS_SID,
                                         PERIOD_SID
                                  FROM   M_DISCOUNT_PROJECTION MDP
                                         INNER JOIN #APPROVED_CCP_DETAILS ACD
                                                 ON ACD.PROJECTION_DETAILS_SID = MDP.PROJECTION_DETAILS_SID
                                  WHERE  SAVE_FLAG = 1) PRO
                              ON PRO.CCP_DETAILS_SID = C.CCP_DETAILS_SID
                                 AND PRO.PERIOD_SID = B.PERIOD_SID')

		EXEC sp_executesql @SQL

      ------------------------- PROJECTION ENDS HERE	--------------
	  ----------------------------------------NEW ADDITION---------------------------------------------------------------------------------------
	    SET @SQL=' UPDATE SNDP
              SET    SNDP.PROJECTION_SALES = 0,
                     SNDP.PROJECTION_RPU   = 0,
                     SNDP.PROJECTION_RATE  = 0
              FROM   '+@M_DISCOUNT_PROJECTION+' SNDP
			  INNER JOIN #PERIOD P
			  ON SNDP.PERIOD_SID=P.PERIOD_SID
                     INNER JOIN #SALES_FREQUENCY_DATE TEF
                             ON SNDP.CCP_DETAILS_SID = TEF.CCP_DETAILS_SID
                                       AND P.PERIOD_DATE NOT BETWEEN TEF.ZERO_OUT_START AND TEF.ZERO_OUT_END'
		EXEC sp_executesql @SQL

      --------------------------------------------------------------------------------------------------------------------------------------------



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