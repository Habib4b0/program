IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_SALES_MANUAL_ADJUSTMENT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_SALES_MANUAL_ADJUSTMENT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_SALES_MANUAL_ADJUSTMENT](@BASLINE_PERIODS  VARCHAR(1000)=NULL,
                                                     @SELECTED_PERIODS VARCHAR(1000),
                                                     @PROJECTION_SID   INT,
                                                     @USER_ID          INT,
                                                     @SESSION_ID       INT)
AS
    BEGIN
      SET NOCOUNT ON

      DECLARE @METHODOLOGY_COUNT INT,
              @VARIABLE_COUNT    INT,
              @OUTER_LOOP        INT=1,
              @INNER_LOOP1       INT=1,
              @METHODOLOGY       VARCHAR(50)='',
              @VARIABLE          INT=2,
              @PROJECTION_DATE   DATETIME,
              @ADJUSTMENTTYPE    VARCHAR(20)='',
              @FROM_DATE         DATE,
              @COUNT             INT=0

      BEGIN TRY
          SET @FROM_DATE= (SELECT DateAdd(yy, DatedIff(yy, 0, GetDate()) - 3, 0))
          SET @PROJECTION_DATE = (SELECT DateAdd(day, -1, DateAdd(qq, DatedIff(qq, 0, TO_DATE) + 1, 0))
                                  FROM   PROJECTION_MASTER
                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION_SID)
          
          ------------------ ADJUSTMENT CCP DETAILS -------------------------------------------------
          CREATE TABLE #SALES_MASTER
            (
               SALES_ID   INT,
               ITEM_SID   INT,
               [USER_ID]  INT,
               SESSION_ID INT
            );

          WITH CTE
               AS (SELECT STNMS.PROJECTION_DETAILS_SID AS NM_SALES_ID,
                          CCP.ITEM_MASTER_SID          AS ITEM_SID,
                          STNMS.[USER_ID],
                          STNMS.[SESSION_ID]
                   FROM   ST_NM_SALES_PROJECTION_MASTER STNMS
                   JOIN   PROJECTION_DETAILS PD ON STNMS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                   JOIN   CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                   WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_SID
                      AND STNMS.CHECK_RECORD = 1
                      AND STNMS.[USER_ID] = @USER_ID
                      AND STNMS.SESSION_ID = @SESSION_ID)
          INSERT INTO #SALES_MASTER
                      (SALES_ID,
                       ITEM_SID,
                       [USER_ID],
                       SESSION_ID)
          SELECT NM_SALES_ID,
                 ITEM_SID,
                 [USER_ID],
                 SESSION_ID
          FROM   CTE

          --------------------WAC PRICE FOR THE ITEM STARTS HERE -------------------------------------------
          DECLARE @ITEM_INFO TABLE
            (
               ITEM_ID  VARCHAR(50),
               ITEM_SID INT
            )

          INSERT INTO @ITEM_INFO
                      (ITEM_ID,
                       ITEM_SID)
          SELECT DISTINCT
                 IM.ITEM_ID,
                 IM.ITEM_MASTER_SID
          FROM   ITEM_MASTER IM
          JOIN   #SALES_MASTER SM ON IM.ITEM_MASTER_SID = ITEM_SID

          DECLARE @ITEMID UDT_ITEM_PRICING

          INSERT INTO @ITEMID
          SELECT DISTINCT
                 ITEM_ID
          FROM   @ITEM_INFO

          DECLARE @ITEM_PRICE TABLE
            (
               SALES_ID   INT,
               ITEM_SID   INT,
               PERIOD_SID INT,
               ITEM_PRICE NUMERIC(22, 6)
            )

          INSERT INTO @ITEM_PRICE
                      (SALES_ID,
                       ITEM_SID,
                       PERIOD_SID,
                       ITEM_PRICE)
          SELECT SM.SALES_ID,
                 SM.ITEM_SID,
                 UDF.PERIOD_SID,
                 UDF.ITEM_PRICE
          FROM   #SALES_MASTER SM
          JOIN   @ITEM_INFO ITM ON ITM.ITEM_SID = SM.ITEM_SID
          JOIN   udf_Month_wac(@ITEMID, @FROM_DATE, @PROJECTION_DATE) UDF ON UDF.ITEM_ID = ITM.ITEM_ID
	
          -------------------WAC PRICE FOR THE ITEM ENDS HERE -----------------------------------------
          CREATE TABLE #TEMP_BASELINE_PERCENT
            (
               SALES_ID               INT,
               ITEM_SID               INT,
               BASELINE_SALES_PERCENT NUMERIC(22, 6),
               BASELINE_UNITS_PERCENT NUMERIC(22, 6),
               [USER_ID]              INT,
               SESSION_ID             INT
            )

          CREATE TABLE #TEMP_PERIOD_CONT
            (
               PERIOD_SID           INT,
               PERIOD_SALES_PERCENT NUMERIC(22, 6),
               PERIOD_UNITS_PERCENT NUMERIC(22, 6),
               [USER_ID]            INT,
               SESSION_ID           INT
            )

          CREATE TABLE #TEMP_FINAL_SALES
            (
               SALES_ID   INT,
               PERIOD_SID INT,
               PROJ_SALES NUMERIC(22, 6),
               PROJ_UNITS NUMERIC(22, 6),
               [USER_ID]  INT,
               SESSION_ID INT
            )

          SELECT PROJECTION_DETAILS_SID,
                         PROJECTION_SALES,
                         PROJECTION_UNITS,
                         PERIOD_SID,
                         ADJUSTMENT_TYPE,
                         ADJUSTMENT_BASIS,
                         ADJUSTMENT_METHODOLOGY,
                         ADJUSTMENT_VALUES,
                         ADJUSTMENT_VARIABLE,
                         [USER_ID],
                         SESSION_ID
          INTO   #TEMP_PROJECTION_SALES
          FROM   (SELECT STNMP.PROJECTION_DETAILS_SID,
                         STNMP.PROJECTION_SALES,
                         STNMP.PROJECTION_UNITS,
                         STNMP.PERIOD_SID,
                         STNMP.ADJUSTMENT_TYPE,
                         STNMP.ADJUSTMENT_BASIS,
                         STNMP.ADJUSTMENT_METHODOLOGY,
                         STNMP.ADJUSTMENT_VALUES,
                         STNMP.ADJUSTMENT_VARIABLE,
                         STNMP.[USER_ID],
                         STNMP.SESSION_ID
                  FROM   ST_NM_SALES_PROJECTION STNMP
                  WHERE  EXISTS (SELECT 1
                                 FROM   #SALES_MASTER SM
                                 WHERE  STNMP.PROJECTION_DETAILS_SID = SM.SALES_ID
                                    AND STNMP.[USER_ID] = SM.[USER_ID]
                                    AND STNMP.SESSION_ID = SM.SESSION_ID)) A

          DECLARE @ADJUSTMENT_BASIS NUMERIC(22, 6)
          DECLARE @ADJUSTMENT_VALUE NUMERIC(22, 6)=0
          DECLARE @PRIOR_FLAG BIT
		  DECLARE @CONDITION BIT=0

          SELECT @PRIOR_FLAG = 1
          WHERE  EXISTS(SELECT 1
                        FROM   ST_NM_SALES_PROJECTION A
                        JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                        JOIN   ST_NM_SALES_PROJECTION_MASTER C ON C.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                                              AND A.USER_ID = C.USER_ID
                                                              AND A.SESSION_ID = C.SESSION_ID
                                                              AND PROJECTION_MASTER_SID = @PROJECTION_SID
                                                              AND A.USER_ID = @USER_ID
                                                              AND A.SESSION_ID = @SESSION_ID
                                                              AND C.CHECK_RECORD = 1
                        WHERE  PROJECTION_SALES IS NULL)

		  SELECT @CONDITION = 1
          WHERE  NOT EXISTS(SELECT SM.SALES_ID,
                                   SM.ITEM_SID,
                                   SM.[USER_ID],
                                   SM.SESSION_ID,
                                   Sum(STNMP.PROJECTION_SALES) AS PROJECTION_SALES,
                                   Sum(STNMP.PROJECTION_UNITS) AS PROJECTION_UNITS,
                                   STNMP.ADJUSTMENT_VARIABLE
                            FROM   ST_NM_SALES_PROJECTION STNMP
                            JOIN   #SALES_MASTER SM ON STNMP.PROJECTION_DETAILS_SID = SM.SALES_ID
                                                   AND STNMP.[USER_ID] = SM.[USER_ID]
                                                   AND STNMP.SESSION_ID = SM.SESSION_ID
                            JOIN   (SELECT PERIOD_SID,
                                           'Q' + Cast( QUARTER AS VARCHAR(2)) + ' '
                                           + Cast(YEAR AS VARCHAR(4)) PERIODS
                                    FROM   PERIOD) SUB ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                            WHERE  EXISTS (SELECT 1
                                           FROM   udf_SplitString(@SELECTED_PERIODS, ',') A
                                           WHERE  A.TOKEN = SUB.PERIODS)
                               AND ( STNMP.PROJECTION_SALES IS NULL )
                               AND ( STNMP.PROJECTION_UNITS IS NULL )
                            GROUP  BY SM.SALES_ID,
                                      SM.ITEM_SID,
                                      SM.[USER_ID],
                                      SM.SESSION_ID,
                                      STNMP.ADJUSTMENT_VARIABLE) 
          

          SET @ADJUSTMENT_BASIS =(SELECT CASE
                                           WHEN Max(Cast(a.ADJUSTMENT_VARIABLE AS INT)) = 1 THEN IsNull(Sum(A.PROJECTION_SALES), 0)
                                           ELSE IsNull(Sum(A.PROJECTION_UNITS), 0)
                                         END ADJUSTMENT_RESULT
                                  FROM   (SELECT SM.SALES_ID,
                                                 SM.ITEM_SID,
                                                 SM.[USER_ID],
                                                 SM.SESSION_ID,
                                                 Sum(STNMP.PROJECTION_SALES) AS PROJECTION_SALES,
                                                 Sum(STNMP.PROJECTION_UNITS) AS PROJECTION_UNITS,
                                                 STNMP.ADJUSTMENT_VARIABLE
                                          FROM   ST_NM_SALES_PROJECTION STNMP
                                          JOIN   #SALES_MASTER SM ON STNMP.PROJECTION_DETAILS_SID = SM.SALES_ID
                                                                 AND STNMP.[USER_ID] = SM.[USER_ID]
                                                                 AND STNMP.SESSION_ID = SM.SESSION_ID
                                          JOIN   (SELECT PERIOD_SID,
                                                         'Q' + Cast( QUARTER AS VARCHAR(2)) + ' '
                                                         + Cast(YEAR AS VARCHAR(4)) PERIODS
                                                  FROM   PERIOD) SUB ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                                          WHERE  EXISTS (SELECT 1
                                                         FROM   udf_SplitString(@SELECTED_PERIODS, ',') A
                                                         WHERE  A.TOKEN = SUB.PERIODS)
                                          GROUP  BY SM.SALES_ID,
                                                    SM.ITEM_SID,
                                                    SM.[USER_ID],
                                                    SM.SESSION_ID,
                                                    STNMP.ADJUSTMENT_VARIABLE) A)
		  IF @CONDITION = 1
            SET @ADJUSTMENT_VALUE=0
          ELSE
            SET @ADJUSTMENT_VALUE =(SELECT CASE
                                             WHEN Max(Cast(a.ADJUSTMENT_VARIABLE AS INT)) = 1 THEN Sum(A.PROJECTION_SALES)
                                             ELSE Sum(A.PROJECTION_UNITS)
                                           END ADJUSTMENT_RESULT
                                    FROM   (SELECT SM.SALES_ID,
                                                   SM.ITEM_SID,
                                                   SM.[USER_ID],
                                                   SM.SESSION_ID,
                                                   Sum(STNMP.PROJECTION_SALES) AS PROJECTION_SALES,
                                                   Sum(STNMP.PROJECTION_UNITS) AS PROJECTION_UNITS,
                                                   STNMP.ADJUSTMENT_VARIABLE
                                            FROM   ST_NM_SALES_PROJECTION STNMP
                                            JOIN   #SALES_MASTER SM ON STNMP.PROJECTION_DETAILS_SID = SM.SALES_ID
                                                                   AND STNMP.[USER_ID] = SM.[USER_ID]
                                                                   AND STNMP.SESSION_ID = SM.SESSION_ID
                                            JOIN   (SELECT PERIOD_SID,
                                                           'Q' + Cast( QUARTER AS VARCHAR(2)) + ' '
                                                           + Cast(YEAR AS VARCHAR(4)) PERIODS
                                                    FROM   PERIOD) SUB ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                                            WHERE  EXISTS (SELECT 1
                                                           FROM   udf_SplitString(@SELECTED_PERIODS, ',') A
                                                           WHERE  A.TOKEN = SUB.PERIODS)
                                               AND ( STNMP.PROJECTION_SALES IS NULL )
                                               AND ( STNMP.PROJECTION_UNITS IS NULL )
                                            GROUP  BY SM.SALES_ID,
                                                      SM.ITEM_SID,
                                                      SM.[USER_ID],
                                                      SM.SESSION_ID,
                                                      STNMP.ADJUSTMENT_VARIABLE) A) 
       
          --------------ADJUSTMENT SALES AND PERIOD DETAILS FOR SELECTED PERIODS --------------------------
          DECLARE @PERIOD_CONT TABLE
            (
               PERIOD_SID INT,
               PROJ_SALES NUMERIC(22, 6),
               PROJ_UNITS NUMERIC(22, 6),
               [USER_ID]  INT,
               SESSION_ID INT
            )

          INSERT INTO @PERIOD_CONT
                      (PERIOD_SID,
                       [USER_ID],
                       SESSION_ID,
                       PROJ_SALES,
                       PROJ_UNITS)
          SELECT STNMP.PERIOD_SID,
                 STNMP.[USER_ID],
                 STNMP.SESSION_ID,
                 Sum(STNMP.PROJECTION_SALES) AS PROJ_SALES,
                 Sum(STNMP.PROJECTION_UNITS) AS PROJ_UNITS
          FROM   ST_NM_SALES_PROJECTION STNMP
          WHERE  EXISTS (SELECT 1
                         FROM   #SALES_MASTER SM
                         WHERE  SM.SALES_ID = STNMP.PROJECTION_DETAILS_SID
                            AND SM.[USER_ID] = STNMP.[USER_ID]
                            AND SM.SESSION_ID = STNMP.SESSION_ID)
             AND STNMP.ADJUSTMENT_VALUES IS NOT NULL
          GROUP  BY STNMP.PERIOD_SID,
                    STNMP.[USER_ID],
                    STNMP.SESSION_ID

          ---------------- CONTRIBUTION FOR MONTH IN SELECTED PERIODS --------------------------
          IF ( @ADJUSTMENT_VALUE IS NULL )
              OR ( @PRIOR_FLAG = 1
                   AND @ADJUSTMENT_BASIS = 0 )
            BEGIN
                SELECT @COUNT = Count(1)
                FROM   PERIOD SUB
                WHERE  EXISTS(SELECT 1
                              FROM   udf_SplitString(@SELECTED_PERIODS, ',') A
                              WHERE  A.TOKEN = 'Q' + Cast( SUB.QUARTER AS VARCHAR(2)) + ' '
                                               + Cast(SUB.YEAR AS VARCHAR(4)))

                INSERT INTO #TEMP_PERIOD_CONT
                            (PERIOD_SID,
                             [USER_ID],
                             SESSION_ID,
                             PERIOD_SALES_PERCENT,
                             PERIOD_UNITS_PERCENT)
                SELECT PERIOD_SID,
                       [USER_ID],
                       SESSION_ID,
                       COALESCE(1.0 / NULLIF(@COUNT, 0), 0) AS PERIOD_SALES_PERCENT,
                       COALESCE(1.0 / NULLIF(@COUNT, 0), 0) AS PERIOD_UNITS_PERCENT
                FROM   (SELECT PC.PERIOD_SID,
                               PC.[USER_ID],
                               PC.SESSION_ID,
                               PC.PROJ_SALES,
                               PC.PROJ_UNITS
                        FROM   @PERIOD_CONT PC
                        JOIN   (SELECT PERIOD_SID,
                                       'Q' + Cast( QUARTER AS VARCHAR(2)) + ' '
                                       + Cast(YEAR AS VARCHAR(4)) PERIODS
                                FROM   PERIOD) SUB ON PC.PERIOD_SID = SUB.PERIOD_SID
                        WHERE  EXISTS (SELECT 1
                                       FROM   udf_SplitString(@SELECTED_PERIODS, ',') A
                                       WHERE  A.TOKEN = SUB.PERIODS)) A
            END
          ELSE
            BEGIN
                INSERT INTO #TEMP_PERIOD_CONT
                            (PERIOD_SID,
                             [USER_ID],
                             SESSION_ID,
                             PERIOD_SALES_PERCENT,
                             PERIOD_UNITS_PERCENT)
                SELECT PERIOD_SID,
                       [USER_ID],
                       SESSION_ID,
                       COALESCE(PROJ_SALES / NULLIF(Sum(PROJ_SALES)
                                                      OVER(), 0), 0)  AS SALES_PERIOD_PERCENT,
                       COALESCE (PROJ_UNITS / NULLIF(Sum(PROJ_UNITS)
                                                       OVER(), 0), 0) AS UNITS_PERIOD_PERCENT
                FROM   (SELECT PC.PERIOD_SID,
                               PC.[USER_ID],
                               PC.SESSION_ID,
                               PC.PROJ_SALES,
                               PC.PROJ_UNITS
                        FROM   @PERIOD_CONT PC
                        JOIN   (SELECT PERIOD_SID,
                                       'Q' + Cast( QUARTER AS VARCHAR(2)) + ' '
                                       + Cast(YEAR AS VARCHAR(4)) PERIODS
                                FROM   PERIOD) SUB ON PC.PERIOD_SID = SUB.PERIOD_SID
                        WHERE  EXISTS (SELECT 1
                                       FROM   udf_SplitString(@SELECTED_PERIODS, ',') A
                                       WHERE  A.TOKEN = SUB.PERIODS)) A
            END

          SELECT @METHODOLOGY_COUNT = Count(DISTINCT ADJUSTMENT_METHODOLOGY)
          FROM   #TEMP_PROJECTION_SALES
	
          WHILE ( @OUTER_LOOP <= @METHODOLOGY_COUNT )
            BEGIN
                SELECT DISTINCT TOP 1
                       @METHODOLOGY = ADJUSTMENT_METHODOLOGY
                FROM   #TEMP_PROJECTION_SALES
                WHERE  @METHODOLOGY <> ADJUSTMENT_METHODOLOGY
				ORDER BY SESSION_ID

                DECLARE @METHODOLOGY_HISTORICAL_UNITS VARCHAR(100)
                DECLARE @METHODOLOGY_HISTORICAL_SALES VARCHAR(100)=(SELECT FORMULA
                          FROM   FORECASTING_FORMULA
                          WHERE  FORMULA_NAME = 'HISTORICAL % OF BUSINESS'),--MONTHLY_ACTUAL/BASELINE_PERIODS_ACTUAL
                        @METHODOLOGY_FORECAST_UNITS   VARCHAR(100),
                        @METHODOLOGY_FORECAST_SALES   VARCHAR(100)=(SELECT FORMULA
                          FROM   FORECASTING_FORMULA
                          WHERE  FORMULA_NAME = 'FORECAST % OF BUSINESS'),--MONTHLY_PROJECTION/BASELINE_PERIODS_PROJECTION
                        @INSERT_METHODOLOGY           NVARCHAR(MAX)

                --@PRIOR_FORECAST_PROJ VARCHAR(100)
                --SET @PRIOR_FORECAST_PROJ=
                SET @METHODOLOGY_HISTORICAL_SALES=(SELECT Replace(Replace(@METHODOLOGY_HISTORICAL_SALES, 'MONTHLY_ACTUAL', 'ACTUAL_SALES'), 'BASELINE_PERIODS_ACTUAL', 'NULLIF(BASELINE_SALES,0)'))
                SET @METHODOLOGY_HISTORICAL_UNITS=(SELECT Replace(Replace(@METHODOLOGY_HISTORICAL_SALES, 'ACTUAL_SALES', 'ACTUAL_UNITS'), 'NULLIF(BASELINE_SALES,0)', 'NULLIF(BASELINE_UNITS,0)'))
                SET @METHODOLOGY_FORECAST_SALES=(SELECT Replace(Replace(@METHODOLOGY_HISTORICAL_SALES, 'ACTUAL_SALES', 'PROJECTION_SALES'), 'BASELINE_PERIODS_PROJECTION', 'NULLIF(BASELINE_SALES,0)'))
                SET @METHODOLOGY_FORECAST_UNITS=(SELECT Replace(Replace(@METHODOLOGY_FORECAST_SALES, 'PROJECTION_SALES', 'PROJECTION_UNITS'), 'NULLIF(BASELINE_SALES,0)', 'NULLIF(BASELINE_UNITS,0)'))

                --------------CONTRIBUTION % FOR EACH LINE IN BASELINE PERIODS ------------------------
                IF @METHODOLOGY = 'HISTORICAL % OF BUSINESS'
                  BEGIN
		
                      SET @INSERT_METHODOLOGY = ' INSERT INTO #TEMP_BASELINE_PERCENT
                                  (SALES_ID,
                                   ITEM_SID,
                                   [USER_ID],
                                   SESSION_ID,
                                   BASELINE_SALES_PERCENT,
                                   BASELINE_UNITS_PERCENT)
                                  SELECT SALES_ID,
                             ITEM_SID,
                             [USER_ID],
                             SESSION_ID,
                             '+ @METHODOLOGY_HISTORICAL_SALES+ ' AS BASELINE_SALES_PERCENTAGE,
                           '+ @METHODOLOGY_HISTORICAL_UNITS+ ' AS BASELINE_UNITS_PERCENTAGE
                      FROM   
                                    ( SELECT A.SALES_ID,
                             A.ITEM_SID,
                             A.[USER_ID],
                             A.SESSION_ID,
                              A.ACTUAL_SALES,
                                                SUM(A.ACTUAL_SALES)OVER ()AS BASELINE_SALES,
                             A.ACTUAL_UNITS,
                                                SUM(A.ACTUAL_UNITS)OVER () AS BASELINE_UNITS
                                    
                           FROM     (SELECT SM.SALES_ID,
                                     SM.ITEM_SID,
                                     SM.[USER_ID],
                                     SM.SESSION_ID,
                               SUM(STNMA.ACTUAL_SALES)AS ACTUAL_SALES,
                               SUM(STNMA.ACTUAL_UNITS)AS ACTUAL_UNITS
                              FROM   ST_NM_ACTUAL_SALES STNMA
                                     JOIN #SALES_MASTER SM
                                       ON STNMA.PROJECTION_DETAILS_SID = SM.SALES_ID
                                          AND STNMA.[USER_ID] = SM.[USER_ID]
                                          AND STNMA.SESSION_ID = SM.SESSION_ID
                                     JOIN (SELECT PERIOD_SID,
                                             ''Q'' + Cast( QUARTER AS VARCHAR(2)) + '''
                                                + ' '
                                                + '''
                                             + Cast(YEAR AS VARCHAR(4)) PERIODS
                                      FROM   PERIOD) SUB
                                       ON STNMA.PERIOD_SID = SUB.PERIOD_SID
                              WHERE  EXISTS (SELECT 1
                                             FROM   UDF_SPLITSTRING('''
                                                + @BASLINE_PERIODS + ''',''' + ','
                                                + ''') A
                                             WHERE  A.TOKEN = SUB.PERIODS)
                              GROUP  BY SM.SALES_ID,
                                        SM.ITEM_SID,
                                        SM.[USER_ID],
                                        SM.SESSION_ID) A)J'
									
                  END
                ELSE
                  BEGIN
                      IF ( @ADJUSTMENT_VALUE IS NULL )
                          OR ( @PRIOR_FLAG = 1
                               AND @ADJUSTMENT_BASIS = 0 )
                        BEGIN
					
                            SELECT @COUNT = Count(SALES_ID)
                            FROM   #SALES_MASTER

                            INSERT INTO #TEMP_BASELINE_PERCENT
                                        (SALES_ID,
                                         ITEM_SID,
                                         [USER_ID],
                                         SESSION_ID,
                                         BASELINE_SALES_PERCENT,
                                         BASELINE_UNITS_PERCENT)
                            SELECT DISTINCT
                                   SALES_ID,
                                   ITEM_SID,
                                   [USER_ID],
                                   SESSION_ID,
                                   COALESCE(1.0 / NULLIF(@COUNT, 0), 0) AS BASELINE_SALES_PERCENTAGE,
                                   COALESCE(1.0 / NULLIF(@COUNT, 0), 0) AS BASELINE_UNITS_PERCENTAGE
                            FROM   (SELECT SM.SALES_ID,
                                           SM.ITEM_SID,
                                           SM.[USER_ID],
                                           SM.SESSION_ID,
                                           STNMP.PROJECTION_SALES AS PROJECTION_SALES,
                                           STNMP.PROJECTION_UNITS AS PROJECTION_UNITS
                                    FROM   ST_NM_SALES_PROJECTION STNMP
                                    JOIN   #SALES_MASTER SM ON STNMP.PROJECTION_DETAILS_SID = SM.SALES_ID
                                                           AND STNMP.[USER_ID] = SM.[USER_ID]
                                                           AND STNMP.SESSION_ID = SM.SESSION_ID
                                    JOIN   (SELECT PERIOD_SID,
                                                   'Q' + Cast( QUARTER AS VARCHAR(2)) + ' '
                                                   + Cast(YEAR AS VARCHAR(4)) PERIODS
                                            FROM   PERIOD) SUB ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                                    WHERE  EXISTS (SELECT 1
                                                   FROM   udf_SplitString(@BASLINE_PERIODS, ',') A
                                                   WHERE  A.TOKEN = SUB.PERIODS))A
                        END
                      ELSE
                        BEGIN
					
                            SET @INSERT_METHODOLOGY = 'INSERT INTO #TEMP_BASELINE_PERCENT
                                  (SALES_ID,
                                   ITEM_SID,
                                   [USER_ID],
                                   SESSION_ID,
                                   BASELINE_SALES_PERCENT,
                                   BASELINE_UNITS_PERCENT)
                      SELECT SALES_ID,
                             ITEM_SID,
                             [USER_ID],
                             SESSION_ID,
                             '+ @METHODOLOGY_FORECAST_SALES+ ' AS BASELINE_SALES_PERCENTAGE,
                            '+ @METHODOLOGY_FORECAST_UNITS+ ' AS BASELINE_UNITS_PERCENTAGE
                      FROM  ( 
                                    SELECT A.SALES_ID,
                                     A.ITEM_SID,
                                     A.[USER_ID],
                                     A.SESSION_ID,
                                    A.PROJECTION_SALES,
                                                              SUM(A.PROJECTION_SALES)OVER() AS BASELINE_SALES,
                                     A.PROJECTION_UNITS,
                                                              SUM(A.PROJECTION_UNITS)OVER() AS BASELINE_UNITS
                           FROM     (SELECT SM.SALES_ID,
                                     SM.ITEM_SID,
                                     SM.[USER_ID],
                                     SM.SESSION_ID,
                                     SUM(STNMP.PROJECTION_SALES)AS PROJECTION_SALES,
                                     SUM(STNMP.PROJECTION_UNITS) AS PROJECTION_UNITS
                              FROM   ST_NM_SALES_PROJECTION STNMP
                                     JOIN #SALES_MASTER SM
                                       ON STNMP.PROJECTION_DETAILS_SID = SM.SALES_ID
                                          AND STNMP.[USER_ID] = SM.[USER_ID]
                                          AND STNMP.SESSION_ID = SM.SESSION_ID
                                     JOIN (SELECT PERIOD_SID,
                                             ''Q'' + Cast( QUARTER AS VARCHAR(2)) + '''
                                                      + ' '
                                                      + '''
                                             + Cast(YEAR AS VARCHAR(4)) PERIODS
                                      FROM   PERIOD) SUB
                                       ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                              WHERE  EXISTS (SELECT 1
                                             FROM   UDF_SPLITSTRING('''
                                                      + @BASLINE_PERIODS + ''',''' + ','
                                                      + ''') A
                                             WHERE  A.TOKEN = SUB.PERIODS)
                              GROUP  BY SM.SALES_ID,
                                        SM.ITEM_SID,
                                        SM.[USER_ID],
                                        SM.SESSION_ID) A)J'
                        END
                  END

                EXECUTE sp_Executesql
                  @INSERT_METHODOLOGY

                SELECT @VARIABLE_COUNT = Count(*)
                FROM   (SELECT DISTINCT
                               ADJUSTMENT_VARIABLE,
                               ADJUSTMENT_TYPE
                        FROM   #TEMP_PROJECTION_SALES
                        WHERE  ADJUSTMENT_METHODOLOGY = @METHODOLOGY) A

                WHILE ( @INNER_LOOP1 <= @VARIABLE_COUNT )
                  BEGIN
                      SELECT DISTINCT TOP 1
                             @VARIABLE = ADJUSTMENT_VARIABLE,
                             @ADJUSTMENTTYPE = ADJUSTMENT_TYPE
                      FROM   #TEMP_PROJECTION_SALES
                      WHERE  ( @ADJUSTMENTTYPE <> ADJUSTMENT_TYPE
                           AND @VARIABLE <> ADJUSTMENT_VARIABLE )
						   ORDER BY SESSION_ID

                      IF @VARIABLE = 1
                        BEGIN
                            IF @ADJUSTMENTTYPE = 'OVERRIDE'
                              BEGIN
							  
                                  INSERT INTO #TEMP_FINAL_SALES
                                              (SALES_ID,
                                               PERIOD_SID,
                                               [USER_ID],
                                               SESSION_ID,
                                               PROJ_SALES,
                                               PROJ_UNITS)
                                  SELECT A.PROJECTION_DETAILS_SID,
                                         A.PERIOD_SID,
                                         A.[USER_ID],
                                         A.SESSION_ID,
                                         ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_SALES_PERCENT )                               AS SALES,
                                         ( ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_SALES_PERCENT ) / NULLIF(A.ITEM_PRICE, 0) ) AS UNITS
                                  FROM   (SELECT TPS.PROJECTION_DETAILS_SID,
                                                 TPS.PERIOD_SID,
                                                 TPS.[USER_ID],
                                                 TPS.SESSION_ID,
                                                 TPC.PERIOD_SALES_PERCENT,
                                                 CASE
                                                   WHEN TPS.ADJUSTMENT_BASIS = 'AMOUNT' THEN TPS.ADJUSTMENT_VALUES
                                                   ELSE ( TPS.ADJUSTMENT_VALUES / 100 ) * @ADJUSTMENT_BASIS
                                                 END ADJUSTMENT_VALUES,
                                                 IP.ITEM_PRICE,
                                                 TBP.BASELINE_SALES_PERCENT
                                          FROM   #TEMP_PROJECTION_SALES TPS
                                          JOIN   #TEMP_PERIOD_CONT TPC ON TPC.PERIOD_SID = TPS.PERIOD_SID
                                          JOIN   #TEMP_BASELINE_PERCENT TBP ON TBP.SALES_ID = TPS.PROJECTION_DETAILS_SID
                                          LEFT JOIN   @ITEM_PRICE IP ON IP.SALES_ID = TPS.PROJECTION_DETAILS_SID
                                                               AND IP.SALES_ID = TBP.SALES_ID
                                                               AND IP.PERIOD_SID = TPS.PERIOD_SID
                                                               AND IP.PERIOD_SID = TPC.PERIOD_SID
                                          WHERE  TPS.ADJUSTMENT_METHODOLOGY = @METHODOLOGY
                                             AND TPS.ADJUSTMENT_VARIABLE = @VARIABLE
                                             AND TPS.ADJUSTMENT_TYPE = @ADJUSTMENTTYPE
                                             AND TPS.[USER_ID] = TPC.[USER_ID]
                                             AND TPC.[USER_ID] = TBP.[USER_ID]
                                             AND TPS.SESSION_ID = TPC.SESSION_ID
                                             AND TPC.SESSION_ID = TBP.SESSION_ID) A
                              END
                            ELSE
                              BEGIN
                                  INSERT INTO #TEMP_FINAL_SALES
                                              (SALES_ID,
                                               PERIOD_SID,
                                               [USER_ID],
                                               SESSION_ID,
                                               PROJ_SALES,
                                               PROJ_UNITS)
                                  SELECT A.PROJECTION_DETAILS_SID,
                                         A.PERIOD_SID,
                                         A.[USER_ID],
                                         A.SESSION_ID,
                                         ( IsNull(A.PROJECTION_SALES, 0) + ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_SALES_PERCENT ) )                               AS SALES,
                                         ( ( IsNull(A.PROJECTION_SALES, 0) + ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_SALES_PERCENT ) ) / NULLIF(A.ITEM_PRICE, 0) ) AS UNITS
                                  FROM   (SELECT TPS.PROJECTION_DETAILS_SID,
                                                 TPS.PERIOD_SID,
                                                 TPS.[USER_ID],
                                                 TPS.SESSION_ID,
                                                 TPC.PERIOD_SALES_PERCENT,
                                                 CASE
                                                   WHEN TPS.ADJUSTMENT_BASIS = 'AMOUNT' THEN TPS.ADJUSTMENT_VALUES
                                                   ELSE ( TPS.ADJUSTMENT_VALUES / 100 ) * @ADJUSTMENT_BASIS
                                                 END ADJUSTMENT_VALUES,
                                                 IP.ITEM_PRICE,
                                                 TBP.BASELINE_SALES_PERCENT,
                                                 TPS.PROJECTION_SALES
                                          FROM   #TEMP_PROJECTION_SALES TPS
                                          JOIN   #TEMP_PERIOD_CONT TPC ON TPC.PERIOD_SID = TPS.PERIOD_SID
                                          JOIN   #TEMP_BASELINE_PERCENT TBP ON TBP.SALES_ID = TPS.PROJECTION_DETAILS_SID
                                          LEFT JOIN   @ITEM_PRICE IP ON IP.SALES_ID = TPS.PROJECTION_DETAILS_SID
                                                               AND IP.SALES_ID = TBP.SALES_ID
                                                               AND IP.PERIOD_SID = TPS.PERIOD_SID
                                                               AND IP.PERIOD_SID = TPC.PERIOD_SID
                                          WHERE  TPS.ADJUSTMENT_METHODOLOGY = @METHODOLOGY
                                             AND TPS.ADJUSTMENT_VARIABLE = @VARIABLE
                                             AND TPS.ADJUSTMENT_TYPE = @ADJUSTMENTTYPE
                                             AND TPS.[USER_ID] = TPC.[USER_ID]
                                             AND TPC.[USER_ID] = TBP.[USER_ID]
                                             AND TPS.SESSION_ID = TPC.SESSION_ID
                                             AND TPC.SESSION_ID = TBP.SESSION_ID) A
                              END
                        END
                      ELSE
                        BEGIN
                            IF @ADJUSTMENTTYPE = 'OVERRIDE'
                              BEGIN
                                  INSERT INTO #TEMP_FINAL_SALES
                                              (SALES_ID,
                                               PERIOD_SID,
                                               [USER_ID],
                                               SESSION_ID,
                                               PROJ_SALES,
                                               PROJ_UNITS)
                                  SELECT A.PROJECTION_DETAILS_SID,
                                         A.PERIOD_SID,
                                         A.[USER_ID],
                                         A.SESSION_ID,
                                         ( ( A.PERIOD_UNITS_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_UNITS_PERCENT ) * ( A.ITEM_PRICE ) AS SALES,
                                         ( ( A.PERIOD_UNITS_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_UNITS_PERCENT )                    AS UNITS
                                  FROM   (SELECT TPS.PROJECTION_DETAILS_SID,
                                                 TPS.PERIOD_SID,
                                                 TPS.[USER_ID],
                                                 TPS.SESSION_ID,
                                                 TPC.PERIOD_UNITS_PERCENT,
                                                 CASE
                                                   WHEN TPS.ADJUSTMENT_BASIS = 'AMOUNT' THEN TPS.ADJUSTMENT_VALUES
                                                   ELSE ( TPS.ADJUSTMENT_VALUES / 100 ) * @ADJUSTMENT_BASIS
                                                 END ADJUSTMENT_VALUES,
                                                 IP.ITEM_PRICE,
                                                 TBP.BASELINE_UNITS_PERCENT
                                          FROM   #TEMP_PROJECTION_SALES TPS
                                          JOIN   #TEMP_PERIOD_CONT TPC ON TPC.PERIOD_SID = TPS.PERIOD_SID
                                          JOIN   #TEMP_BASELINE_PERCENT TBP ON TBP.SALES_ID = TPS.PROJECTION_DETAILS_SID
                                          LEFT JOIN   @ITEM_PRICE IP ON IP.SALES_ID = TPS.PROJECTION_DETAILS_SID
                                                               AND IP.SALES_ID = TBP.SALES_ID
                                                               AND IP.PERIOD_SID = TPS.PERIOD_SID
                                                               AND IP.PERIOD_SID = TPC.PERIOD_SID
                                          WHERE  TPS.ADJUSTMENT_METHODOLOGY = @METHODOLOGY
                                             AND TPS.ADJUSTMENT_VARIABLE = @VARIABLE
                                             AND TPS.ADJUSTMENT_TYPE = @ADJUSTMENTTYPE
                                             AND TPS.[USER_ID] = TPC.[USER_ID]
                                             AND TPC.[USER_ID] = TBP.[USER_ID]
                                             AND TPS.SESSION_ID = TPC.SESSION_ID
                                             AND TPC.SESSION_ID = TBP.SESSION_ID) A
                              END
                            ELSE
                              BEGIN
                                  INSERT INTO #TEMP_FINAL_SALES
                                              (SALES_ID,
                                               PERIOD_SID,
                                               [USER_ID],
                                               SESSION_ID,
                                               PROJ_SALES,
                                               PROJ_UNITS)
                                  SELECT A.PROJECTION_DETAILS_SID,
                                         A.PERIOD_SID,
                                         A.[USER_ID],
                                         A.SESSION_ID,
                                         ( ( IsNull(A.PROJECTION_UNITS, 0) + ( ( A.PERIOD_UNITS_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_UNITS_PERCENT ) ) * A.ITEM_PRICE ) AS SALES,
                                         ( IsNull(A.PROJECTION_UNITS, 0) + ( ( A.PERIOD_UNITS_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_UNITS_PERCENT ) )
                                  FROM   (SELECT TPS.PROJECTION_DETAILS_SID,
                                                 TPS.PERIOD_SID,
                                                 TPS.[USER_ID],
                                                 TPS.SESSION_ID,
                                                 TPC.PERIOD_UNITS_PERCENT,
                                                 CASE
                                                   WHEN TPS.ADJUSTMENT_BASIS = 'AMOUNT' THEN TPS.ADJUSTMENT_VALUES
                                                   ELSE ( TPS.ADJUSTMENT_VALUES / 100 ) * @ADJUSTMENT_BASIS
                                                 END ADJUSTMENT_VALUES,
                                                 IP.ITEM_PRICE,
                                                 TBP.BASELINE_UNITS_PERCENT,
                                                 TPS.PROJECTION_UNITS
                                          FROM   #TEMP_PROJECTION_SALES TPS
                                          JOIN   #TEMP_PERIOD_CONT TPC ON TPC.PERIOD_SID = TPS.PERIOD_SID
                                          JOIN   #TEMP_BASELINE_PERCENT TBP ON TBP.SALES_ID = TPS.PROJECTION_DETAILS_SID
                                          LEFT JOIN   @ITEM_PRICE IP ON IP.SALES_ID = TPS.PROJECTION_DETAILS_SID
                                                               AND IP.SALES_ID = TBP.SALES_ID
                                                               AND IP.PERIOD_SID = TPS.PERIOD_SID
                                                               AND IP.PERIOD_SID = TPC.PERIOD_SID
                                          WHERE  TPS.ADJUSTMENT_METHODOLOGY = @METHODOLOGY
                                             AND TPS.ADJUSTMENT_VARIABLE = @VARIABLE
                                             AND TPS.ADJUSTMENT_TYPE = @ADJUSTMENTTYPE
                                             AND TPS.[USER_ID] = TPC.[USER_ID]
                                             AND TPC.[USER_ID] = TBP.[USER_ID]
                                             AND TPS.SESSION_ID = TPC.SESSION_ID
                                             AND TPC.SESSION_ID = TBP.SESSION_ID) A
                              END
                        END

                      SET @INNER_LOOP1=@INNER_LOOP1 + 1
                  END

                SET @INSERT_METHODOLOGY=''
                SET @INNER_LOOP1=1
                SET @COUNT=0

                DELETE FROM #TEMP_BASELINE_PERCENT

                SET @OUTER_LOOP=@OUTER_LOOP + 1
            END
		
          UPDATE STNMP
          SET    STNMP.PROJECTION_SALES = IsNull(TFS.PROJ_SALES, 0),
                 STNMP.PROJECTION_UNITS = IsNull(TFS.PROJ_UNITS, 0)
          FROM   ST_NM_SALES_PROJECTION STNMP
                 JOIN #TEMP_FINAL_SALES TFS ON STNMP.PROJECTION_DETAILS_SID = TFS.SALES_ID
                                           AND STNMP.PERIOD_SID = TFS.PERIOD_SID
          WHERE  STNMP.[USER_ID] = TFS.[USER_ID]
             AND STNMP.SESSION_ID = TFS.SESSION_ID
      END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC UsPerrOrCollector

          SELECT @ERRORMESSAGE = Error_Message(),
                 @ERRORSEVERITY = Error_Severity(),
                 @ERRORSTATE = Error_State(),
                 @ERRORPROCEDURE = Error_Procedure(),
                 @ERRORLINE = Error_Line(),
                 @ERRORNUMBER = Error_Number()

          RAISERROR (@ERRORMESSAGE,-- MESSAGE TEXT.
                     @ERRORSEVERITY,-- SEVERITY.
                     @ERRORSTATE,-- STATE.
                     @ERRORPROCEDURE,-- PROCEDURE_NAME.
                     @ERRORNUMBER,-- ERRORNUMBER
                     @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END