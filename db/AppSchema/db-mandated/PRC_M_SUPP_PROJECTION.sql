IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_M_SUPP_PROJECTION'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_M_SUPP_PROJECTION]
  END

GO

CREATE PROCEDURE [dbo].[PRC_M_SUPP_PROJECTION] (@PROJECTION_MASTER_SID INT,
                                                @USER_ID               INT,
                                                @SESSION_ID            VARCHAR(50))
AS
  BEGIN
      SET NOCOUNT ON

      DECLARE @ROWCOUNT    INT,
              @LOOPCOUNTER INT=1
      DECLARE @FORMULA_CNT       INT = 0
      DECLARE @PROJECTION_DETAILS_SID INT,
              @ITEM_MASTER_SID        INT,
              @CONTRACT_MASTER_SID    INT,
              @START_PERIOD_SID       NUMERIC(5),
              @END_PERIOD_SID         NUMERIC(5),
              @PRICING_QUALIFIER      VARCHAR(50)='WAC',
              @ITEM_UOM               VARCHAR(50)='EACH',-- 'UN' TO 'EACH' GALUAT-46
              @MARKET_TYPE            VARCHAR(50),
              @HFCA_UPP               NUMERIC(22, 6),
              @ITEM_DETAILS           DBO.UDT_ITEM,
              @BUSINESS_UNIT          INT,
              @COMPANY                INT,
              @SQL                    NVARCHAR(MAX)
      DECLARE @S_MASTER_TABLE     VARCHAR(200) = Concat('ST_M_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @MASTER_TABLE       VARCHAR(200)= Concat('ST_M_SUPPLEMENTAL_DISC_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @PROJECTION_TABLE   VARCHAR(200)= Concat('ST_M_SUPPLEMENTAL_DISC_PROJ_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @S_PROJECTION_TABLE VARCHAR(200)=Concat('ST_M_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

      SELECT @BUSINESS_UNIT = BUSINESS_UNIT,
             @COMPANY = COMPANY_MASTER_SID
      FROM   PROJECTION_MASTER
      WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

      --------------------------------------------------------------------------------------------------------------------------------------------------------------------------	  
      IF Object_id('TEMPDB..#EFFECTIVE_DATE') IS NOT NULL
        DROP TABLE #EFFECTIVE_DATE

      CREATE TABLE #EFFECTIVE_DATE
        (
           CCP_DETAILS_SID INT,
           RS_MODEL_SID    INT,
           RS_CATOGERY     VARCHAR(100),
           START_DATE      DATETIME,
           END_DATE        DATETIME,
           START_SID       INT,
           END_SID         INT
        )

      INSERT INTO #EFFECTIVE_DATE
                  (CCP_DETAILS_SID,
                   RS_MODEL_SID,
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

      -----------------------------------------------------------------------------------------------------------------------------------------				
      -----------------------------------FETCH PROJECTION_DETAILS_SID FROM ST_M_SUPPLEMENTAL_DISC_MASTER TABLE-------------------------------
      IF Object_id('TEMPDB..#PROJ_DETAILS') IS NOT NULL
        DROP TABLE #PROJ_DETAILS

      CREATE TABLE #PROJ_DETAILS
        (
           ROWID               INT IDENTITY(1, 1),
           CCP_DETAILS_SID     INT,
           CONTRACT_MASTER_SID INT,
           COMPANY_MASTER_SID  INT,
           ITEM_MASTER_SID     INT,
           MARKET_TYPE         VARCHAR(50),
           CAL_START_DATE      DATETIME,
           CAL_END_DATE        DATETIME,
           ZERO_OUT_START      DATETIME,
           ZERO_OUT_END        DATETIME
        )

      -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
      SET @SQL=Concat('INSERT INTO #PROJ_DETAILS
                  (CCP_DETAILS_SID,
                   CONTRACT_MASTER_SID,
                   COMPANY_MASTER_SID,
                   ITEM_MASTER_SID,
                   MARKET_TYPE,
                   CAL_START_DATE,
                   CAL_END_DATE,
				   ZERO_OUT_START,
				   ZERO_OUT_END)
				   
SELECT B.CCP_DETAILS_SID,
       B.CONTRACT_MASTER_SID,
	   B.COMPANY_MASTER_SID,
	   B.ITEM_MASTER_SID,
	   B.MARKET_TYPE,
	   B.CAL_START_DATE,
	   B.CAL_END_DATE,
	   IIF(B.CAL_START_DATE < B.START_DATE, B.CAL_START_DATE, B.START_DATE) AS ZERO_OUT_START,
       IIF(B.CAL_END_DATE   > B.END_DATE  , B.CAL_END_DATE  , B.END_DATE  ) AS ZERO_OUT_END    
FROM 
(SELECT 
       A.CCP_DETAILS_SID,
       A.CONTRACT_MASTER_SID,
	   A.COMPANY_MASTER_SID,
	   A.ITEM_MASTER_SID,
	   A.MARKET_TYPE,
	   CASE WHEN FREQUENCY = ''M'' THEN (SELECT DATEADD( MM , DATEDIFF(MM, 0, A.CAL_START_DATE), 0))
                                    WHEN FREQUENCY = ''Q'' THEN (SELECT DATEADD( QQ , DATEDIFF(QQ, 0, A.CAL_START_DATE), 0))
			                        WHEN FREQUENCY = ''S'' THEN (SELECT CASE WHEN DATEPART(QQ,A.CAL_START_DATE) = 1 or DATEPART(QQ,A.CAL_START_DATE) = 2 THEN   
									(SELECT DATEADD( MM ,0,(SELECT DATEADD( YYYY , DATEDIFF(YYYY, 0, A.CAL_START_DATE), 0)) )) ELSE  (SELECT DATEADD( MM ,6,(SELECT DATEADD( YYYY , DATEDIFF(YYYY, 0, A.CAL_START_DATE), 0)) ))END)
			                        ELSE (SELECT DATEADD( YYYY , DATEDIFF(YYYY, 0, A.CAL_START_DATE), 0))
			                        END AS CAL_START_DATE ,
									
	   CASE WHEN FREQUENCY = ''M'' THEN (SELECT DATEADD(DAY, -1, DATEADD(MM, DATEDIFF(MM, 0, A.CAL_END_DATE) + 1, 0)))
                                    WHEN FREQUENCY = ''Q'' THEN (SELECT DATEADD(DAY, -1, DATEADD(QQ, DATEDIFF(QQ, 0, A.CAL_END_DATE) + 1, 0)))
			                        WHEN FREQUENCY = ''S'' THEN (SELECT CASE WHEN DATEPART(QQ, A.CAL_END_DATE) = 1 or DATEPART(QQ, A.CAL_END_DATE) = 2 THEN   
									(SELECT DATEADD(MONTH, -6, DATEADD(DAY, -1, DATEADD(YYYY, DATEDIFF(YYYY, 0, A.CAL_END_DATE) + 1, 0)))) 
									ELSE  (SELECT DATEADD(DAY, -1, DATEADD(YYYY, DATEDIFF(YYYY, 0, A.CAL_END_DATE) + 1, 0)))
									END)
			                        ELSE (SELECT  DATEADD(DAY, -1, DATEADD(YYYY, DATEDIFF(YYYY, 0, A.CAL_END_DATE) + 1, 0)))
			                        END	AS CAL_END_DATE,
       A.START_DATE,
	   A.END_DATE
							
FROM 									
	   
(				   
      SELECT SM.CCP_DETAILS_SID,
             CCP.CONTRACT_MASTER_SID,
             CCP.COMPANY_MASTER_SID,
             CCP.ITEM_MASTER_SID,
             SM.MARKET_TYPE,
			 UDF.START_DATE,
			 UDF.END_DATE,			 
             IIF(@PROJ_START_DATE > UDF.START_DATE, @PROJ_START_DATE, UDF.START_DATE) AS CAL_START_DATE,
             IIF(@PROJ_END_DATE < UDF.END_DATE, @PROJ_END_DATE, UDF.END_DATE)         AS CAL_END_DATE,
			 CASE WHEN ISNUMERIC(LEFT(SNSPM.CALCULATION_PERIODS,1)) = 0 THEN LEFT(SNSPM.CALCULATION_PERIODS,1)
                  ELSE ''A'' END AS FREQUENCY        
      FROM   ', @MASTER_TABLE, ' SM
             JOIN ', @S_MASTER_TABLE, ' SNSPM 
			   ON SNSPM.CCP_DETAILS_SID = PD.CCP_DETAILS_SID 
             JOIN #EFFECTIVE_DATE UDF
               ON PD.CCP_DETAILS_SID = UDF.CCP_DETAILS_SID ----- CHEGESS MADE
             JOIN CCP_DETAILS CCP
               ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
) A ) B')

      EXEC sp_executesql @SQL

      SET @ROWCOUNT=@@ROWCOUNT

      -----------------------------------FETCH PROJECTION_DETAILS_SID FROM ST_M_SUPPLEMENTAL_DISC_MASTER TABLE-------------------------------
      WHILE ( @LOOPCOUNTER <= @ROWCOUNT ) --START OF WHILE LOOP
        BEGIN
            DECLARE @CAL_SATRT_DATE DATETIME,
                    @CAL_END_DATE   DATETIME
            DECLARE @ZERO_OUT_START DATETIME,
                    @ZERO_OUT_END   DATETIME
            DECLARE @ZERO_OUT_START_SID INT,
                    @ZERO_OUT_END_SID   INT

            SELECT @PROJECTION_DETAILS_SID = CCP_DETAILS_SID,
                   @ITEM_MASTER_SID = ITEM_MASTER_SID,
                   @CONTRACT_MASTER_SID = CONTRACT_MASTER_SID,
                   @MARKET_TYPE = MARKET_TYPE,
                   @CAL_SATRT_DATE = CAL_START_DATE,
                   @CAL_END_DATE = CAL_END_DATE,
                   @ZERO_OUT_START = ZERO_OUT_START,
                   @ZERO_OUT_END = ZERO_OUT_END
            FROM   #PROJ_DETAILS
            WHERE  ROWID = @LOOPCOUNTER

            ---------------------------------------------ZERO OUT RANGE VARIABLES-----------------------------------------------
            SELECT @ZERO_OUT_START_SID = PERIOD_SID
            FROM   PERIOD
            WHERE  PERIOD_DATE = @ZERO_OUT_START

            SELECT @ZERO_OUT_END_SID = PERIOD_SID
            FROM   PERIOD
            WHERE  PERIOD_DATE = Dateadd(mm, Datediff(mm, 0, @ZERO_OUT_END), 0)

            --------------------------------------------------------------------------------------------------------------------
            SELECT @HFCA_UPP = COALESCE(UPPS, 0)
            FROM   ITEM_MASTER
            WHERE  ITEM_MASTER_SID = @ITEM_MASTER_SID

            IF @MARKET_TYPE = 'SPAP'
              BEGIN
                  SELECT @FORMULA_CNT = Count(*)
                  FROM   RS_CONTRACT RSC
                         JOIN RS_CONTRACT_DETAILS RSD
                           ON RSC.RS_CONTRACT_SID = RSD.RS_CONTRACT_SID
                         JOIN RS_CONTRACT_DETAILS_FR RS_D_FR
                           ON RSD.RS_CONTRACT_DETAILS_SID = RS_D_FR.RS_CONTRACT_DETAILS_SID
                         JOIN FORMULA_DETAILS_MASTER FM
                           ON RSD.FORMULA_ID = FM.FORMULA_ID
                         JOIN FORECASTING_FORMULA MFS
                           ON FM.FORMULA_DESC = MFS.FORMULA_NAME
                  WHERE  RSC.CONTRACT_MASTER_SID = @CONTRACT_MASTER_SID
                         AND RSD.ITEM_MASTER_SID = @ITEM_MASTER_SID
                         AND MFS.FORMULA LIKE '%BASIC%'
                         AND EXISTS (SELECT HELPER_TABLE_SID
                                                FROM   HELPER_TABLE H1
                                                WHERE  LIST_NAME = 'RS_CATEGORY'
                                                       AND DESCRIPTION = 'STATE_SUPP'
													   AND RSC.RS_CATEGORY=H1.HELPER_TABLE_SID)
                         AND RS_D_FR.INBOUND_STATUS <> 'D'
              END

            IF Object_id('TEMPDB..#TEMP_VALUES') IS NOT NULL
              DROP TABLE #TEMP_VALUES

            CREATE TABLE #TEMP_VALUES
              (
                 PERIOD_SID INT PRIMARY KEY,
                 QUARTER    INT,
                 YEAR       INT,
                 WAC        NUMERIC(22, 6),
                 AWP AS ( WAC + ( WAC * 20 / 100 ) ),
                 AMP        NUMERIC(22, 6),
                 BASIC_URA  NUMERIC(22, 6),
                 CPI_URA    NUMERIC(22, 6),
                 URA        NUMERIC(22, 6)
              )

            ----------------------------------------------------FUTURE WAC FROM ITEM_PRICING AND FORECASTING_MASTER STARTS HERE----------------------------------------------
            DELETE FROM @ITEM_DETAILS

            INSERT INTO @ITEM_DETAILS
            SELECT ITEM_MASTER_SID
            FROM   #PROJ_DETAILS PD
            WHERE  PD.CCP_DETAILS_SID = @PROJECTION_DETAILS_SID

            SELECT @START_PERIOD_SID = PERIOD_SID
            FROM   PERIOD
            WHERE  PERIOD_DATE = @CAL_SATRT_DATE

            SELECT @END_PERIOD_SID = PERIOD_SID
            FROM   PERIOD
            WHERE  PERIOD_DATE = Dateadd(mm, Datediff(mm, 0, @CAL_END_DATE), 0)

            INSERT INTO #TEMP_VALUES
                        (PERIOD_SID,
                         QUARTER,
                         YEAR,
                         WAC)
            SELECT P.PERIOD_SID,
                   P.QUARTER,
                   P.YEAR,
                   COALESCE(ITEM_PRIC.WAC, 0) AS WAC
            FROM   PERIOD P
                   LEFT JOIN (SELECT DISTINCT Avg(COALESCE(IP.ITEM_PRICE, 0)) AS WAC,
                                              IP.PERIOD_SID
                              FROM   [DBO].[Udf_item_pricing](@ITEM_DETAILS, @PRICING_QUALIFIER, @START_PERIOD_SID, @END_PERIOD_SID, @ITEM_UOM) IP
                              GROUP  BY IP.PERIOD_SID) ITEM_PRIC
                          ON ITEM_PRIC.PERIOD_SID = P.PERIOD_SID
            WHERE  P.PERIOD_DATE BETWEEN @CAL_SATRT_DATE AND @CAL_END_DATE----- CHEGESS MADE

            -------------INDEX ON TEMP TABLE FOR PERFORMANCE---------------------
            CREATE NONCLUSTERED INDEX #IX_TEMP_VALUES_QUARTER_YEAR
              ON #TEMP_VALUES(QUARTER, YEAR)

            ---------------------------------------------------------------------
            UPDATE A
            SET    A.WAC = B.WAC
            FROM   #TEMP_VALUES A
                   JOIN (SELECT P.PERIOD_SID,
                                COALESCE(FORC_MAST.WAC, 0) AS WAC
                         FROM   PERIOD P
                                LEFT JOIN (SELECT DISTINCT COALESCE(FM.PRICE, 0) AS WAC,
                                                           P.PERIOD_SID
                                           FROM   FORECASTING_MASTER FM
                                                  JOIN (SELECT TOP 1 FT.FORECAST_NAME,
                                                                     FT.[VERSION]
                                                        FROM   FILE_MANAGEMENT FT
                                                               INNER JOIN HELPER_TABLE HT
                                                                       ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
                                                        WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                                                                 AND FT.FROM_PERIOD IS NOT NULL )
                                                               AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                                                                      OR FT.TO_PERIOD IS NULL )
                                                               AND HT.LIST_NAME = 'FILE_TYPE'
                                                               AND HT.DESCRIPTION = 'EX-FACTORY SALES'
                                                               AND FT.BUSINESS_UNIT = @BUSINESS_UNIT
                                                               AND FT.COMPANY = @COMPANY
                                                        ORDER  BY FT.FROM_PERIOD DESC)FT
                                                    ON FM.FORECAST_NAME = FT.FORECAST_NAME
                                                       AND FM.FORECAST_VER = FT.VERSION
                                                  JOIN PERIOD P
                                                    ON P.YEAR = FM.FORECAST_YEAR
                                                       AND P.MONTH = FORECAST_MONTH
                                                  JOIN ITEM_MASTER IM
                                                    ON FM.NDC = IM.ITEM_ID
                                                       AND IM.ITEM_MASTER_SID = @ITEM_MASTER_SID
                                           WHERE  FM.FORECAST_VER IN ( FT.VERSION, Floor(FT.VERSION) )) FORC_MAST
                                       ON P.PERIOD_SID = FORC_MAST.PERIOD_SID
                         WHERE  P.PERIOD_DATE BETWEEN @CAL_SATRT_DATE AND @CAL_END_DATE)B------- CHEGESS MADE
                     ON A.PERIOD_SID = B.PERIOD_SID
            WHERE  B.WAC <> 0

            ----------------------------------------------------FUTURE WAC FROM ITEM_PRICING AND FORECASTING_MASTER ENDS HERE----------------------------------------------
            ----------------------------------------------------AMP FROM NATIONAL_ASSUMPTIONS STARTS HERE----------------------------------------------------------------------
            UPDATE A
            SET    A.AMP = COALESCE(OUT_NA_PROJ.PROJECTION_PRICE, 0)
            FROM   #TEMP_VALUES A
                   LEFT JOIN (SELECT B.PROJECTION_PRICE,
                                     P.QUARTER,
                                     P.YEAR
                              FROM   NATIONAL_ASSUMPTIONS_PROJ B
                                     JOIN PERIOD P --JOIN WITH PERIOD TABLE TO CONVERT QUARTERLY WISE DATA TO MONTHLY WISE
                                       ON B.PERIOD_SID = P.PERIOD_SID
                              WHERE  B.ITEM_MASTER_SID = @ITEM_MASTER_SID
                                     AND B.PRICE_TYPE = 'AMP') OUT_NA_PROJ
                          ON A.QUARTER = OUT_NA_PROJ.QUARTER
                             AND A.YEAR = OUT_NA_PROJ.YEAR

            ----------------------------------------------------AMP FROM NATIONAL_ASSUMPTIONS ENDS HERE-------------------------------------------------------------------------
            ----------------------------------------------------FUTURE AMP,BASIC_URA,CPI_URA STARTS HERE-------------------------------------------------------------------
            IF LEFT(@MARKET_TYPE, 3) = 'FED' --FOR MARKET TYPE FEDERAL
              BEGIN
                  UPDATE A
                  SET    A.URA = COALESCE(OUT_FCP_PROJ.PROJECTION_PRICE, 0)
                  FROM   #TEMP_VALUES A
                         JOIN (SELECT PROJECTION_PRICE,
                                      P.QUARTER,
                                      P.YEAR
                               FROM   FCP_PROJ B
                                      JOIN (SELECT TOP 1 NA_PD.NA_PROJ_DETAILS_SID
                                            FROM   NA_PROJ_DETAILS NA_PD
                                                   JOIN NA_PROJ_MASTER NA_PM
                                                     ON NA_PM.NA_PROJ_MASTER_SID = NA_PD.NA_PROJ_MASTER_SID
                                                        AND NA_PD.ITEM_MASTER_SID = @ITEM_MASTER_SID
                                                        AND NA_PM.SAVE_FLAG = 1
                                            ORDER  BY NA_PM.MODIFIED_DATE DESC) C
                                        ON B.NA_PROJ_DETAILS_SID = C.NA_PROJ_DETAILS_SID
                                      JOIN PERIOD P --JOIN WITH PERIOD TABLE TO CONVERT QUARTERLY WISE DATA TO MONTHLY WISE
                                        ON B.PERIOD_SID = P.PERIOD_SID
                               WHERE  B.PRICE_TYPE = 'FCP') OUT_FCP_PROJ
                           ON A.QUARTER = OUT_FCP_PROJ.QUARTER
                              AND A.YEAR = OUT_FCP_PROJ.YEAR
              END
            ELSE IF @MARKET_TYPE = 'PHS'--FOR MARKET TYPE PHS
              BEGIN
                  UPDATE A
                  SET    A.URA = COALESCE(OUT_PHS_PROJ.PROJECTION_PRICE, 0)
                  FROM   #TEMP_VALUES A
                         JOIN (SELECT PROJECTION_PRICE,
                                      P.QUARTER,
                                      P.YEAR
                               FROM   PHS_PROJ B
                                      JOIN (SELECT TOP 1 NA_PD.NA_PROJ_DETAILS_SID
                                            FROM   NA_PROJ_DETAILS NA_PD
                                                   JOIN NA_PROJ_MASTER NA_PM
                                                     ON NA_PM.NA_PROJ_MASTER_SID = NA_PD.NA_PROJ_MASTER_SID
                                                        AND NA_PD.ITEM_MASTER_SID = @ITEM_MASTER_SID
                                                        AND NA_PM.SAVE_FLAG = 1
                                            ORDER  BY NA_PM.MODIFIED_DATE DESC) C
                                        ON B.NA_PROJ_DETAILS_SID = C.NA_PROJ_DETAILS_SID
                                      JOIN PERIOD P --JOIN WITH PERIOD TABLE TO CONVERT QUARTERLY WISE DATA TO MONTHLY WISE
                                        ON B.PERIOD_SID = P.PERIOD_SID
                               WHERE  B.PRICE_TYPE = 'TOTAL URA') OUT_PHS_PROJ
                           ON A.QUARTER = OUT_PHS_PROJ.QUARTER
                              AND A.YEAR = OUT_PHS_PROJ.YEAR
              END
            ELSE IF ( @MARKET_TYPE = 'SPAP'
                 AND @FORMULA_CNT > 1 ) --SOME OF SPAP CONTRACTS
              BEGIN
                  UPDATE A
                  SET    A.URA = COALESCE(OUT_MED_PROJ.PROJECTION_PRICE, 0)
                  FROM   #TEMP_VALUES A
                         JOIN (SELECT PROJECTION_PRICE,
                                      P.QUARTER,
                                      P.YEAR
                               FROM   MEDICAID_URA_PROJ B
                                      JOIN (SELECT TOP 1 NA_PD.NA_PROJ_DETAILS_SID
                                            FROM   NA_PROJ_DETAILS NA_PD
                                                   JOIN NA_PROJ_MASTER NA_PM
                                                     ON NA_PM.NA_PROJ_MASTER_SID = NA_PD.NA_PROJ_MASTER_SID
                                                        AND NA_PD.ITEM_MASTER_SID = @ITEM_MASTER_SID
                                                        AND NA_PM.SAVE_FLAG = 1
                                            ORDER  BY NA_PM.MODIFIED_DATE DESC) C
                                        ON B.NA_PROJ_DETAILS_SID = C.NA_PROJ_DETAILS_SID
                                      JOIN PERIOD P --JOIN WITH PERIOD TABLE TO CONVERT QUARTERLY WISE DATA TO MONTHLY WISE
                                        ON B.PERIOD_SID = P.PERIOD_SID
                               WHERE  B.PRICE_TYPE = 'BASIC URA') OUT_MED_PROJ
                           ON A.QUARTER = OUT_MED_PROJ.QUARTER
                              AND A.YEAR = OUT_MED_PROJ.YEAR
              END
            ELSE --FOR MARKET TYPES 'MANAGED MEDICAID','ADAP','MANAGED FFS',SOME OF SPAP CONTRACTS
              BEGIN
                  UPDATE A
                  SET    A.URA = COALESCE(OUT_MED_PROJ.PROJECTION_PRICE, 0)
                  FROM   #TEMP_VALUES A
                         JOIN (SELECT PROJECTION_PRICE,
                                      P.QUARTER,
                                      P.YEAR
                               FROM   MEDICAID_URA_PROJ B
                                      JOIN (SELECT TOP 1 NA_PD.NA_PROJ_DETAILS_SID
                                            FROM   NA_PROJ_DETAILS NA_PD
                                                   JOIN NA_PROJ_MASTER NA_PM
                                                     ON NA_PM.NA_PROJ_MASTER_SID = NA_PD.NA_PROJ_MASTER_SID
                                                        AND NA_PD.ITEM_MASTER_SID = @ITEM_MASTER_SID
                                                        AND NA_PM.SAVE_FLAG = 1
                                            ORDER  BY NA_PM.MODIFIED_DATE DESC) C
                                        ON B.NA_PROJ_DETAILS_SID = C.NA_PROJ_DETAILS_SID
                                      JOIN PERIOD P --JOIN WITH PERIOD TABLE TO CONVERT QUARTERLY WISE DATA TO MONTHLY WISE
                                        ON B.PERIOD_SID = P.PERIOD_SID
                               WHERE  B.PRICE_TYPE = 'TOTAL URA') OUT_MED_PROJ
                           ON A.QUARTER = OUT_MED_PROJ.QUARTER
                              AND A.YEAR = OUT_MED_PROJ.YEAR
              END

            ----------------------------------------------------FUTURE AMP,BASIC_URA,CPI_URA ENDS HERE-------------------------------------------------------------------
            IF Object_id('TEMPDB..#TEMP_CALC') IS NOT NULL
              DROP TABLE #TEMP_CALC

            CREATE TABLE #TEMP_CALC
              (
                 RN                     INT PRIMARY KEY,
                 PROJECTION_DETAILS_SID INT,
                 QTR_METHODOLOGY        VARCHAR(50),
                 PERIOD_SID             INT,
                 QUARTER                INT,
                 YEAR                   INT,
                 PARITY                 BIT,
                 QTR_WAC                NUMERIC(22, 6),
                 QTR_AWP                NUMERIC(22, 6),
                 QTR_AMP                NUMERIC(22, 6),
                 QTR_BASIC_URA          NUMERIC(22, 6),
                 QTR_CPI_URA            NUMERIC(22, 6),
                 QTR_URA                NUMERIC(22, 6),
                 CONTRACT_PRICE         NUMERIC(22, 6),
                 DISCOUNT_RATE_1        NUMERIC(22, 6),
                 DISCOUNT_RATE_2        NUMERIC(22, 6),
                 PARITY_DISCOUNT        NUMERIC(22, 6),
                 FINAL_RESULT           NUMERIC(38, 15)
              )

            SET @SQL=Concat('INSERT INTO #TEMP_CALC
                        (RN,
                         PROJECTION_DETAILS_SID,
                         QTR_METHODOLOGY,
                         PERIOD_SID,
                         QUARTER,
                         YEAR,
                         PARITY,
                         QTR_WAC,
                         QTR_AWP,
                         QTR_AMP,
                         QTR_BASIC_URA,
                         QTR_CPI_URA,
                         QTR_URA,
                         CONTRACT_PRICE,
                         DISCOUNT_RATE_1,
                         DISCOUNT_RATE_2,
                         PARITY_DISCOUNT)
            SELECT ROW_NUMBER()
                     OVER(
                       ORDER BY P.QUARTER, P.YEAR, P.MONTH),
                   SUPP_PROJ.PROJECTION_DETAILS_SID,
                   SUPP_PROJ.METHODOLOGY,
                   P.PERIOD_SID,
                   P.QUARTER,
                   P.YEAR,
                   SUPP_PROJ.PARITY,
                   TV.WAC,
                   TV.AWP,
                   TV.AMP,
                   TV.BASIC_URA,
                   TV.CPI_URA,
                   TV.URA,
                   CONTRACT_PRICE,
                   DISCOUNT_RATE_1,
                   DISCOUNT_RATE_2,
                   PARITY_DISCOUNT
            FROM   ', @PROJECTION_TABLE, ' SUPP_PROJ
                   JOIN #TEMP_VALUES TV
                     ON SUPP_PROJ.PERIOD_SID = TV.PERIOD_SID
                   JOIN PERIOD P
                     ON SUPP_PROJ.PERIOD_SID = P.PERIOD_SID
                        AND TV.PERIOD_SID = P.PERIOD_SID
          ')

            EXEC sp_executesql @SQL

            -------------INDEX ON TEMP TABLE FOR PERFORMANCE
            CREATE NONCLUSTERED INDEX #IX_TEMP_CALC_PROJECTION_DETAILS_SID_PERIOD_SID
              ON #TEMP_CALC(PROJECTION_DETAILS_SID, PERIOD_SID)

            ------------------------------------------------------
            DECLARE @MAX_RN INT =0
            DECLARE @INCR INT = 1

            SELECT @MAX_RN = Max(RN)
            FROM   #TEMP_CALC

            DECLARE @QTR_METHODOLOGY VARCHAR(50),
                    @QUARTER         INT,
                    @YEAR            INT,
                    @QTR_WAC         NUMERIC(22, 6),
                    @QTR_AWP         NUMERIC(22, 6),
                    @QTR_AMP         NUMERIC(22, 6),
                    @QTR_URA         NUMERIC(22, 6),
                    @CONTRACT_PRICE  NUMERIC(22, 6),
                    @DISCOUNT_RATE_1 NUMERIC(22, 6),
                    @DISCOUNT_RATE_2 NUMERIC(22, 6),
                    @PARITY_DISCOUNT NUMERIC(22, 6),
                    @FINAL_RESULT    NUMERIC(38, 15),
                    @FORMULA         VARCHAR(500),
                    @PARITY          BIT

            WHILE ( @INCR <= @MAX_RN )--FOR EACH MONTH
              BEGIN
                  SELECT TOP 1 @QTR_METHODOLOGY = COALESCE(QTR_METHODOLOGY, ''),
                               @QTR_WAC = QTR_WAC,
                               @QTR_AWP = QTR_AWP,
                               @QTR_AMP = QTR_AMP,
                               @QTR_URA = QTR_URA,
                               @QUARTER = QUARTER,
                               @YEAR = YEAR,
                               @PARITY = PARITY,
                               @CONTRACT_PRICE = COALESCE(CONTRACT_PRICE, 0),
                               @DISCOUNT_RATE_1 = COALESCE(DISCOUNT_RATE_1, 0),
                               @DISCOUNT_RATE_2 = COALESCE(DISCOUNT_RATE_2, 0),
                               @PARITY_DISCOUNT = COALESCE(PARITY_DISCOUNT, 0)
                  FROM   #TEMP_CALC
                  WHERE  RN = @INCR
				  ORDER BY RN

                  DECLARE @CNT INT

                  IF @PARITY = 1 --PARITY OPTION
                    BEGIN
                        SELECT @CNT = Count(*)
                        FROM   M_PARITY_LOOKUP
                        WHERE  CCP_DETAILS_SID = @PROJECTION_DETAILS_SID
                               AND QUARTER = @QUARTER
                               AND YEAR = @YEAR

                        IF @CNT = 1 --PARITY_REFERENCE = 'SINGLE'
                          BEGIN
                              SELECT @QTR_METHODOLOGY = METHODOLOGY,
                                     @CONTRACT_PRICE = COALESCE(CONTRACT_PRICE, 0),
                                     @DISCOUNT_RATE_1 = COALESCE(DISCOUNT_RATE_1, 0),
                                     @DISCOUNT_RATE_2 = COALESCE(DISCOUNT_RATE_2, 0)
                              FROM   M_PARITY_LOOKUP
                              WHERE  CCP_DETAILS_SID = @PROJECTION_DETAILS_SID
                                     AND QUARTER = @QUARTER
                                     AND YEAR = @YEAR

                              SET @FORMULA = ''-------------ADDED AFTER AN ISSUE

                              SELECT @FORMULA = FORMULA
                              FROM   FORECASTING_FORMULA
                              WHERE  FORMULA_NAME = @QTR_METHODOLOGY

                              ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                              IF @QTR_METHODOLOGY = 'PCTAMPMC'
                                BEGIN
                                    IF @QTR_URA >= ( @DISCOUNT_RATE_1 * @QTR_AMP )
                                      SET @FORMULA = 0
                                    ELSE
                                      SET @FORMULA = Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(@FORMULA, 'WAC', '@QTR_WAC'), 'CARS/MA CONTRACT UNIT PRICE 1', '@CONTRACT_PRICE'), 'URA', '@QTR_URA'), 'AMP', '@QTR_AMP'), 'AWP', '@QTR_AWP'), 'HCFA UNITS PER PACKAGE', '@HFCA_UPP'), 'CARS/MA CONTRACT REBATE % 1', '@DISCOUNT_RATE_1'), 'CARS/MA CONTRACT REBATE % 2', '@DISCOUNT_RATE_2'), 'FORECASTED PACKAGE UNITS', '1')
                                END
                              ELSE IF @QTR_METHODOLOGY = 'WAC-PFCM'
                                BEGIN
                                    IF ( ( @QTR_WAC * @DISCOUNT_RATE_1 ) - @QTR_URA - @CONTRACT_PRICE ) > 0
                                      SET @FORMULA = Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(@FORMULA, 'WAC', '@QTR_WAC'), 'CARS/MA CONTRACT UNIT PRICE 1', '@CONTRACT_PRICE'), 'URA', '@QTR_URA'), 'AMP', '@QTR_AMP'), 'AWP', '@QTR_AWP'), 'HCFA UNITS PER PACKAGE', '@HFCA_UPP'), 'CARS/MA CONTRACT REBATE % 1', '@DISCOUNT_RATE_1'), 'CARS/MA CONTRACT REBATE % 2', '@DISCOUNT_RATE_2'), 'FORECASTED PACKAGE UNITS', '1')
                                    ELSE
                                      SET @FORMULA = ( @QTR_AMP * 0.01 ) * @HFCA_UPP * 1
                                END
                              ELSE IF @QTR_METHODOLOGY = 'PCTAMPMI'
                                BEGIN
                                    IF ( ( @DISCOUNT_RATE_1 * @QTR_AMP ) + @QTR_URA ) >= ( @DISCOUNT_RATE_2 * @QTR_AMP )
                                      SET @FORMULA = ( @DISCOUNT_RATE_1 * @QTR_AMP * @HFCA_UPP * 1 )
                                    ELSE
                                      SET @FORMULA = ( ( ( @DISCOUNT_RATE_2 * @QTR_AMP ) - @QTR_URA ) * @HFCA_UPP * 1 )
                                END
                              ELSE
                                BEGIN
                                    SET @FORMULA = Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(@FORMULA, 'WAC', '@QTR_WAC'), 'CARS/MA CONTRACT UNIT PRICE 1', '@CONTRACT_PRICE'), 'URA', '@QTR_URA'), 'AMP', '@QTR_AMP'), 'AWP', '@QTR_AWP'), 'HCFA UNITS PER PACKAGE', '@HFCA_UPP'), 'CARS/MA CONTRACT REBATE % 1', '@DISCOUNT_RATE_1'), 'CARS/MA CONTRACT REBATE % 2', '@DISCOUNT_RATE_2'), 'FORECASTED PACKAGE UNITS', '1')
                                END

                              ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                              SET @FORMULA = Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(@FORMULA, 'WAC', '@QTR_WAC'), 'CARS/MA CONTRACT UNIT PRICE 1', '@CONTRACT_PRICE'), 'URA', '@QTR_URA'), 'AMP', '@QTR_AMP'), 'AWP', '@QTR_AWP'), 'HCFA UNITS PER PACKAGE', '@HFCA_UPP'), 'CARS/MA CONTRACT REBATE % 1', '@DISCOUNT_RATE_1'), 'CARS/MA CONTRACT REBATE % 2', '@DISCOUNT_RATE_2'), 'FORECASTED PACKAGE UNITS', '1')
                              SET @SQL='SELECT @FINAL_RESULT=' + @FORMULA
                                       + NULLIF(@FORMULA, '')

                              EXEC Sp_executesql
                                @SQL,
                                N'@FINAL_RESULT NUMERIC(38,15) OUTPUT,@QTR_WAC NUMERIC(38,15) OUTPUT,@CONTRACT_PRICE NUMERIC(38,15) OUTPUT,@QTR_AMP NUMERIC(38,15) OUTPUT,@QTR_AWP NUMERIC(38,15) OUTPUT,@QTR_URA NUMERIC(38,15) OUTPUT,@HFCA_UPP NUMERIC(38,15) OUTPUT,@DISCOUNT_RATE_1 NUMERIC(38,15) OUTPUT,@DISCOUNT_RATE_2 NUMERIC(38,15) OUTPUT',
                                @FINAL_RESULT OUTPUT,
                                @QTR_WAC OUTPUT,
                                @CONTRACT_PRICE OUTPUT,
                                @QTR_AMP OUTPUT,
                                @QTR_AWP OUTPUT,
                                @QTR_URA OUTPUT,
                                @HFCA_UPP OUTPUT,
                                @DISCOUNT_RATE_1 OUTPUT,
                                @DISCOUNT_RATE_2 OUTPUT
                          END
                        ELSE --PARITY_REFERENCE = 'MULTIPLE'
                          BEGIN
                              DECLARE @MULTIPLE_PARITY TABLE
                                (
                                   ID                     INT IDENTITY(1, 1),
                                   PROJECTION_DETAILS_SID INT,
                                   QTR_METHODOLOGY        VARCHAR(50),
                                   CONTRACT_PRICE         NUMERIC(22, 6),
                                   DISCOUNT_RATE_1        NUMERIC(22, 6),
                                   DISCOUNT_RATE_2        NUMERIC(22, 6)
                                )

                              INSERT INTO @MULTIPLE_PARITY
                                          (QTR_METHODOLOGY,
                                           CONTRACT_PRICE,
                                           DISCOUNT_RATE_1,
                                           DISCOUNT_RATE_2)
                              SELECT METHODOLOGY,
                                     CONTRACT_PRICE,
                                     DISCOUNT_RATE_1,
                                     DISCOUNT_RATE_2
                              FROM   M_PARITY_LOOKUP
                              WHERE  CCP_DETAILS_SID = @PROJECTION_DETAILS_SID
                                     AND QUARTER = @QUARTER
                                     AND YEAR = @YEAR

                              SELECT @CNT = Count(*)
                              FROM   @MULTIPLE_PARITY

                              DECLARE @P_INCR INT = 1
                              DECLARE @FINAL_RESULT_SUM NUMERIC(38, 15)

                              WHILE ( @P_INCR <= @CNT )
                                BEGIN
                                    SELECT @QTR_METHODOLOGY = QTR_METHODOLOGY,
                                           @CONTRACT_PRICE = COALESCE(CONTRACT_PRICE, 0),
                                           @DISCOUNT_RATE_1 = COALESCE(DISCOUNT_RATE_1, 0),
                                           @DISCOUNT_RATE_2 = COALESCE(DISCOUNT_RATE_2, 0)
                                    FROM   @MULTIPLE_PARITY
                                    WHERE  ID = @P_INCR

                                    SET @FORMULA = ''-------------ADDED AFTER AN ISSUE

                                    SELECT @FORMULA = FORMULA
                                    FROM   FORECASTING_FORMULA
                                    WHERE  FORMULA_NAME = @QTR_METHODOLOGY

                                    ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                                    IF @QTR_METHODOLOGY = 'PCTAMPMC'
                                      BEGIN
                                          IF @QTR_URA >= ( @DISCOUNT_RATE_1 * @QTR_AMP )
                                            SET @FORMULA = 0
                                          ELSE
                                            SET @FORMULA = Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(@FORMULA, 'WAC', '@QTR_WAC'), 'CARS/MA CONTRACT UNIT PRICE 1', '@CONTRACT_PRICE'), 'URA', '@QTR_URA'), 'AMP', '@QTR_AMP'), 'AWP', '@QTR_AWP'), 'HCFA UNITS PER PACKAGE', '@HFCA_UPP'), 'CARS/MA CONTRACT REBATE % 1', '@DISCOUNT_RATE_1'), 'CARS/MA CONTRACT REBATE % 2', '@DISCOUNT_RATE_2'), 'FORECASTED PACKAGE UNITS', '1')
                                      END
                                    ELSE IF @QTR_METHODOLOGY = 'WAC-PFCM'
                                      BEGIN
                                          IF ( ( @QTR_WAC * @DISCOUNT_RATE_1 ) - @QTR_URA - @CONTRACT_PRICE ) > 0
                                            SET @FORMULA = Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(@FORMULA, 'WAC', '@QTR_WAC'), 'CARS/MA CONTRACT UNIT PRICE 1', '@CONTRACT_PRICE'), 'URA', '@QTR_URA'), 'AMP', '@QTR_AMP'), 'AWP', '@QTR_AWP'), 'HCFA UNITS PER PACKAGE', '@HFCA_UPP'), 'CARS/MA CONTRACT REBATE % 1', '@DISCOUNT_RATE_1'), 'CARS/MA CONTRACT REBATE % 2', '@DISCOUNT_RATE_2'), 'FORECASTED PACKAGE UNITS', '1')
                                          ELSE
                                            SET @FORMULA = ( @QTR_AMP * 0.01 ) * @HFCA_UPP * 1
                                      END
                                    ELSE IF @QTR_METHODOLOGY = 'PCTAMPMI'
                                      BEGIN
                                          IF ( ( @DISCOUNT_RATE_1 * @QTR_AMP ) + @QTR_URA ) >= ( @DISCOUNT_RATE_2 * @QTR_AMP )
                                            SET @FORMULA = ( @DISCOUNT_RATE_1 * @QTR_AMP * @HFCA_UPP * 1 )
                                          ELSE
                                            SET @FORMULA = ( ( ( @DISCOUNT_RATE_2 * @QTR_AMP ) - @QTR_URA ) * @HFCA_UPP * 1 )
                                      END
                                    ELSE
                                      BEGIN
                                          SET @FORMULA = Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(@FORMULA, 'WAC', '@QTR_WAC'), 'CARS/MA CONTRACT UNIT PRICE 1', '@CONTRACT_PRICE'), 'URA', '@QTR_URA'), 'AMP', '@QTR_AMP'), 'AWP', '@QTR_AWP'), 'HCFA UNITS PER PACKAGE', '@HFCA_UPP'), 'CARS/MA CONTRACT REBATE % 1', '@DISCOUNT_RATE_1'), 'CARS/MA CONTRACT REBATE % 2', '@DISCOUNT_RATE_2'), 'FORECASTED PACKAGE UNITS', '1')
                                      END

                                    ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                                    SET @FORMULA = Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(@FORMULA, 'WAC', '@QTR_WAC'), 'CARS/MA CONTRACT UNIT PRICE 1', '@CONTRACT_PRICE'), 'URA', '@QTR_URA'), 'AMP', '@QTR_AMP'), 'AWP', '@QTR_AWP'), 'HCFA UNITS PER PACKAGE', '@HFCA_UPP'), 'CARS/MA CONTRACT REBATE % 1', '@DISCOUNT_RATE_1'), 'CARS/MA CONTRACT REBATE % 2', '@DISCOUNT_RATE_2'), 'FORECASTED PACKAGE UNITS', '1')
                                    SET @SQL='SELECT @FINAL_RESULT=' + @FORMULA
                                             + NULLIF(@FORMULA, '')

                                    EXEC Sp_executesql
                                      @SQL,
                                      N'@FINAL_RESULT NUMERIC(38,15) OUTPUT,@QTR_WAC NUMERIC(38,15) OUTPUT,@CONTRACT_PRICE NUMERIC(38,15) OUTPUT,@QTR_AMP NUMERIC(38,15) OUTPUT,@QTR_AWP NUMERIC(38,15) OUTPUT,@QTR_URA NUMERIC(38,15) OUTPUT,@HFCA_UPP NUMERIC(38,15) OUTPUT,@DISCOUNT_RATE_1 NUMERIC(38,15) OUTPUT,@DISCOUNT_RATE_2 NUMERIC(38,15) OUTPUT',
                                      @FINAL_RESULT OUTPUT,
                                      @QTR_WAC OUTPUT,
                                      @CONTRACT_PRICE OUTPUT,
                                      @QTR_AMP OUTPUT,
                                      @QTR_AWP OUTPUT,
                                      @QTR_URA OUTPUT,
                                      @HFCA_UPP OUTPUT,
                                      @DISCOUNT_RATE_1 OUTPUT,
                                      @DISCOUNT_RATE_2 OUTPUT

                                    SET @FINAL_RESULT_SUM += @FINAL_RESULT
                                    SET @P_INCR = @P_INCR + 1
                                END --END OF WHILE LOOP(PARITY_REFERENCE = 'MULTIPLE')

                              SET @FINAL_RESULT = @FINAL_RESULT_SUM
                          END--END OF ELSE LOOP(PARITY_REFERENCE = 'MULTIPLE')

                        SET @FINAL_RESULT = @FINAL_RESULT + @PARITY_DISCOUNT --ADD ADDITIONAL PARITY 
                    END
                  ELSE --METHODOLOGY OPTION
                    BEGIN
                        SET @FORMULA = ''-------------ADDED AFTER AN ISSUE

                        SELECT @FORMULA = FORMULA
                        FROM   FORECASTING_FORMULA
                        WHERE  FORMULA_NAME = @QTR_METHODOLOGY

                        SET @FORMULA = Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(Replace(@FORMULA, 'WAC', '@QTR_WAC'), 'CARS/MA CONTRACT UNIT PRICE 1', '@CONTRACT_PRICE'), 'URA', '@QTR_URA'), 'AMP', '@QTR_AMP'), 'AWP', '@QTR_AWP'), 'HCFA UNITS PER PACKAGE', '@HFCA_UPP'), 'CARS/MA CONTRACT REBATE % 1', '@DISCOUNT_RATE_1'), 'CARS/MA CONTRACT REBATE % 2', '@DISCOUNT_RATE_2'), 'FORECASTED PACKAGE UNITS', '1')
                        SET @SQL='SELECT @FINAL_RESULT='
                                 + NULLIF(@FORMULA, '')

                        --PRINT @SQL
                        EXEC Sp_executesql
                          @SQL,
                          N'@FINAL_RESULT NUMERIC(38,15) OUTPUT,@QTR_WAC NUMERIC(38,15) OUTPUT,@CONTRACT_PRICE NUMERIC(38,15) OUTPUT,@QTR_AMP NUMERIC(38,15) OUTPUT,@QTR_AWP NUMERIC(38,15) OUTPUT,@QTR_URA NUMERIC(38,15) OUTPUT,@HFCA_UPP NUMERIC(38,15) OUTPUT,@DISCOUNT_RATE_1 NUMERIC(38,15) OUTPUT,@DISCOUNT_RATE_2 NUMERIC(38,15) OUTPUT',
                          @FINAL_RESULT OUTPUT,
                          @QTR_WAC OUTPUT,
                          @CONTRACT_PRICE OUTPUT,
                          @QTR_AMP OUTPUT,
                          @QTR_AWP OUTPUT,
                          @QTR_URA OUTPUT,
                          @HFCA_UPP OUTPUT,
                          @DISCOUNT_RATE_1 OUTPUT,
                          @DISCOUNT_RATE_2 OUTPUT
                    END

                  UPDATE #TEMP_CALC
                  SET    FINAL_RESULT = @FINAL_RESULT
                  WHERE  RN = @INCR

                  SET @INCR = @INCR + 1
              END--INNER WHILE LOOP ENDS HERE

            --SELECT * FROM #TEMP_CALC TEMP
            ------------------------------------------------UPDATE ST_M_SUPPLEMENTAL_DISC_PROJ TABLE PER PROJECTION_DETAILS_SID STARTS HERE--------------------------------------------------------
            --PROJECTED AMOUNT = PROJECTION_UNITS * METHODOLOGY_VALUE(METHODOLOGY IS CHOSEN)
            --PROJECTED RATE   = (PROJECTION_UNITS * METHODOLOGY_VALUE*100)/PROJECTION_SALES(METHODOLOGY IS CHOSEN)
            --PROJECTED AMOUNT = (PROJECTION_UNITS * SUM_OF_DISCOUNTS_OF_OTHER_NDC)/100(PARITY IS CHOSEN)
            --PROJECTED RATE   = SUM_OF_DISCOUNTS_OF_OTHER_NDC(PARITY IS CHOSEN)
            SET @SQL= Concat('UPDATE SUPP_PROJ
            SET    PROJECTION_SALES = SALES_PROJ.PROJECTION_UNITS * TEMP.FINAL_RESULT,
                   PROJECTION_RATE = COALESCE(( SALES_PROJ.PROJECTION_UNITS * TEMP.FINAL_RESULT * 100 ) / NULLIF(SALES_PROJ.PROJECTION_SALES, 0), 0),
                   PROJECTION_RPU = TEMP.FINAL_RESULT
            FROM   ', @PROJECTION_TABLE, ' SUPP_PROJ
                   JOIN #TEMP_CALC TEMP
                     ON SUPP_PROJ.CCP_DETAILS_SID = TEMP.CCP_DETAILS_SID
                        AND SUPP_PROJ.PERIOD_SID = TEMP.PERIOD_SID
                   JOIN ', @S_PROJECTION_TABLE, ' SALES_PROJ
                     ON SALES_PROJ.CCP_DETAILS_SID = TEMP.CCP_DETAILS_SID
                        AND SALES_PROJ.PERIOD_SID = TEMP.PERIOD_SID
            AND  SUPP_PROJ.PROJECTION_DETAILS_SID = @PROJECTION_DETAILS_SID')

            EXEC sp_executesql @SQL

            --------------------------------------------------------NEW ADDITION (ZEROING OUT THE VALUES OUT OF CALCULATION PERIOD )-----------------------------------------------------------------
            SET @SQL= Concat(' UPDATE SMSDP
      SET    SMSDP.PROJECTION_SALES = 0,
             SMSDP.PROJECTION_RPU = 0,
             SMSDP.PROJECTION_RATE = 0
      FROM   ', @PROJECTION_TABLE, ' SMSDP 
                        WHERE SMSDP.PERIOD_SID NOT BETWEEN @ZERO_OUT_START_SID AND @ZERO_OUT_END_SID
						')
			EXEC Sp_executesql @SQL , N'@ZERO_OUT_START_SID INT, @ZERO_OUT_END_SID INT'
			, @ZERO_OUT_START_SID = @ZERO_OUT_START_SID, @ZERO_OUT_END_SID = @ZERO_OUT_END_SID
            --------------------------------------------UPDATE ST_M_SUPPLEMENTAL_DISC_PROJ TABLE PER PROJECTION_DETAILS_SID ENDS HERE-------------------------------------------
            SET @LOOPCOUNTER = @LOOPCOUNTER + 1
        END --END OF WHILE LOOP
  END 
