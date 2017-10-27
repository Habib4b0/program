
IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_FE_PROJ_DET_TRANSFER_SALES'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_FE_PROJ_DET_TRANSFER_SALES]
  END

GO

CREATE PROCEDURE [dbo].[PRC_FE_PROJ_DET_TRANSFER_SALES] (@SESSION_ID INT)
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          DECLARE @FROM_PROJECTION          INT,
                  @COPY_FROM_PROJECTION     INT,
                  @TO_PROJECTION            INT,
                  @COPY_TO_PROJECTION       INT,
                  @CUSTOMER_END_DATE        DATETIME,
                  @ITEM_END_DATE            DATETIME,
                  @FROM_CONTRACT_MASTER_SID INT,
                  @TO_CONTRACT_MASTER_SID   INT,
                  @COMPANY_MASTER_SID       VARCHAR(100),
                  @FROM_FORECAST_TYPE       VARCHAR(50),
                  @TO_FORECAST_TYPE         VARCHAR(50),
                  @TRANSFER_FLAG            BIT,
                  @CUST_PERIOD_END          INT,
                  @TO_START_DATE            DATETIME,
                  @CUST_PERIOD_START        INT,
                  @FORECAST_TO_START_DATE   DATETIME,
                  @FORECAST_TO_END_DATE     DATETIME,
				  @TRANSFER_SALES_FLAG      BIT

          SELECT @FROM_PROJECTION = FROM_PROJECTION,
                 @TO_PROJECTION = TO_PROJECTION,
                 @COPY_FROM_PROJECTION = COPY_FROM_PROJECTION,
                 @COPY_TO_PROJECTION = COPY_TO_PROJECTION,
                 @CUSTOMER_END_DATE = FROM_END_DATE,
                 @FROM_CONTRACT_MASTER_SID = FROM_CONTRACT_MASTER_SID,
                 @TO_CONTRACT_MASTER_SID = TO_CONTRACT_MASTER_SID,
                 @COMPANY_MASTER_SID = COMPANY_MASTER_SID,
                 @TO_START_DATE = TO_START_DATE,
                 @TRANSFER_FLAG = TRANSFER_FLAG,
				 @TRANSFER_SALES_FLAG=TRANSFER_SALES_FLAG
          FROM   [dbo].FUTURE_EVENTS_INPUT
          WHERE  SESSION_ID = @SESSION_ID

          SELECT @FROM_FORECAST_TYPE = FORECASTING_TYPE
          FROM   [dbo].PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @COPY_FROM_PROJECTION

          SELECT @TO_FORECAST_TYPE = FORECASTING_TYPE,
                 @FORECAST_TO_START_DATE = FROM_DATE,
                 @FORECAST_TO_END_DATE = TO_DATE
          FROM   [dbo].PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @COPY_TO_PROJECTION

          SELECT @CUST_PERIOD_END = PERIOD_SID
          FROM   PERIOD
          WHERE  MONTH = Month(@CUSTOMER_END_DATE)
                 AND YEAR = Year(@CUSTOMER_END_DATE)

          SELECT @CUST_PERIOD_START = PERIOD_SID
          FROM   PERIOD
          WHERE  MONTH = Month(@TO_START_DATE)
                 AND YEAR = Year(@TO_START_DATE)

          DECLARE @SOURCE TABLE
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT,
               CONTRACT_MASTER_SID    INT,
               COMPANY_MASTER_SID     INT,
               ITEM_MASTER_SID        INT
            )
          DECLARE @MAP TABLE
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT,
               CONTRACT_MASTER_SID    INT,
               COMPANY_MASTER_SID     INT,
               ITEM_MASTER_SID        INT
            )

          IF Object_id('TEMPDB..#TEMP_MAP_COMPANY') IS NOT NULL
            DROP TABLE #TEMP_MAP_COMPANY

          CREATE TABLE #TEMP_MAP_COMPANY
            (
               SOURCE_CONTRACT INT,
               SOURCE_COMPANY  INT,
               MAP_CONTRACT    INT,
               MAPPED_COMPANY  INT
            )

          INSERT INTO #TEMP_MAP_COMPANY
                      (SOURCE_CONTRACT,
                       SOURCE_COMPANY,
                       MAP_CONTRACT,
                       MAPPED_COMPANY)
          SELECT @FROM_CONTRACT_MASTER_SID               AS SOURCE_CONTRACT,
                 LEFT(TOKEN, Charindex('-', TOKEN) - 1)  AS SOURCE_COMPANY,
                 @TO_CONTRACT_MASTER_SID                 AS MAP_CONTRACT,
                 RIGHT(TOKEN, Charindex('-', TOKEN) - 1) AS MAPPED_COMPANY
          FROM   UDF_SPLITSTRING(@COMPANY_MASTER_SID, ',')

          INSERT INTO @SOURCE
          SELECT PD.PROJECTION_DETAILS_SID,
                 CCP.CCP_DETAILS_SID,
                 IFP.CONTRACT_MASTER_SID,
                 CFPD.COMPANY_MASTER_SID,
                 IFD.ITEM_MASTER_SID
          FROM   PROJECTION_DETAILS PD
          JOIN   CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
          JOIN   CFP_CONTRACT CFP ON CFP.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID
          JOIN   CFP_CONTRACT_DETAILS CFPD ON CFP.CFP_CONTRACT_SID = CFPD.CFP_CONTRACT_SID
                                          AND CFPD.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID
          JOIN   IFP_CONTRACT IFP ON CFP.CFP_CONTRACT_SID = IFP.CFP_CONTRACT_SID
                                 AND IFP.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID
          JOIN   IFP_CONTRACT_DETAILS IFD ON IFD.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID
                                         AND IFD.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID
          WHERE  CCP.CONTRACT_MASTER_SID = @FROM_CONTRACT_MASTER_SID
                 AND PD.PROJECTION_MASTER_SID = @COPY_FROM_PROJECTION
                 AND CFPD.COMPANY_MASTER_SID IN (SELECT SOURCE_COMPANY
                                                 FROM   #TEMP_MAP_COMPANY)

          INSERT INTO @MAP
          SELECT PD.PROJECTION_DETAILS_SID,
                 CCP.CCP_DETAILS_SID,
                 IFP.CONTRACT_MASTER_SID,
                 CFPD.COMPANY_MASTER_SID,
                 IFD.ITEM_MASTER_SID
          FROM   PROJECTION_DETAILS PD
          JOIN   CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
          JOIN   CFP_CONTRACT CFP ON CFP.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID
          JOIN   CFP_CONTRACT_DETAILS CFPD ON CFP.CFP_CONTRACT_SID = CFPD.CFP_CONTRACT_SID
                                          AND CFPD.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID
          JOIN   IFP_CONTRACT IFP ON CFP.CFP_CONTRACT_SID = IFP.CFP_CONTRACT_SID
                                 AND IFP.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID
          JOIN   IFP_CONTRACT_DETAILS IFD ON IFD.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID
                                         AND IFD.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID
          WHERE  CCP.CONTRACT_MASTER_SID = @TO_CONTRACT_MASTER_SID
                 AND PD.PROJECTION_MASTER_SID = @COPY_TO_PROJECTION
                 AND CFPD.COMPANY_MASTER_SID IN (SELECT MAPPED_COMPANY
                                                 FROM   #TEMP_MAP_COMPANY)

          IF Object_id('TEMPDB..#TEMP_DETAILS_MAPPED_CCP') IS NOT NULL
            DROP TABLE #TEMP_DETAILS_MAPPED_CCP

          CREATE TABLE #TEMP_DETAILS_MAPPED_CCP
            (
               PROJECTION_DETAILS_SID_SRC INT,
               CCP_SOURCE                 INT,
               PROJECTION_DETAILS_SID_MAP INT,
               CCP_MAP                    INT
            )

          INSERT INTO #TEMP_DETAILS_MAPPED_CCP
                      (PROJECTION_DETAILS_SID_SRC,
                       CCP_SOURCE,
                       PROJECTION_DETAILS_SID_MAP,
                       CCP_MAP)
          SELECT S.PROJECTION_DETAILS_SID AS PROJECTION_DETAILS_SID_SRC,
                 S.CCP_DETAILS_SID        AS CCP_SOURCE,
                 M.PROJECTION_DETAILS_SID AS PROJECTION_DETAILS_SID_MAP,
                 M.CCP_DETAILS_SID        AS CCP_MAP
          FROM   @SOURCE S
          JOIN   @MAP M ON S.ITEM_MASTER_SID = M.ITEM_MASTER_SID
          WHERE  M.COMPANY_MASTER_SID IN (SELECT TD.MAPPED_COMPANY
                                          FROM   #TEMP_MAP_COMPANY TD
                                          WHERE  TD.SOURCE_COMPANY = S.COMPANY_MASTER_SID)

          IF Object_id('tempdb..#TEMP_MASTER') IS NOT NULL
            DROP TABLE #TEMP_MASTER

          CREATE TABLE #TEMP_MASTER
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT,
               METHODOLOGY            VARCHAR(100),
               USER_GROUP             VARCHAR(50),
               CALCULATION_PERIODS    VARCHAR(4000),
               CALCULATION_BASED      VARCHAR(100),
               CHECK_RECORD           BIT
            )

          IF Object_id('tempdb..#TEMP_ACTUALS') IS NOT NULL
            DROP TABLE #TEMP_ACTUALS

          CREATE TABLE #TEMP_ACTUALS
            (
               PROJECTION_DETAILS_SID   INT,
               CCP_DETAILS_SID          INT,
               PERIOD_SID               INT,
               ACTUAL_SALES             NUMERIC(22, 6),
               ACTUAL_UNITS             NUMERIC(22, 6),
               HISTORY_PROJECTION_SALES NUMERIC(22, 6),
               HISTORY_PROJECTION_UNITS NUMERIC(22, 6)
            )

          IF Object_id('tempdb..#TEMP_PROJECTION') IS NOT NULL
            DROP TABLE #TEMP_PROJECTION

          CREATE TABLE #TEMP_PROJECTION
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT,
               ACCOUNT_GROWTH         NUMERIC(22, 6),
               PRODUCT_GROWTH         NUMERIC(22, 6),
               PROJECTION_SALES       NUMERIC(22, 6),
               PROJECTION_UNITS       NUMERIC(22, 6),
               PERIOD_SID             INT,
               ADJUSTMENT_TYPE        VARCHAR(20),
               ADJUSTMENT_BASIS       VARCHAR(20),
               ADJUSTMENT_VARIABLE    BIT,
               ADJUSTMENT_METHODOLOGY VARCHAR(50),
               ADJUSTMENT_VALUES      NUMERIC(22, 6),
            )

          IF Object_id('tempdb..#TEMP_PROJECTION_TEMP') IS NOT NULL
            DROP TABLE #TEMP_PROJECTION_TEMP

          CREATE TABLE #TEMP_PROJECTION_TEMP
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT,
               ACCOUNT_GROWTH         NUMERIC(22, 6),
               PRODUCT_GROWTH         NUMERIC(22, 6),
               PROJECTION_SALES       NUMERIC(22, 6),
               PROJECTION_UNITS       NUMERIC(22, 6),
               PERIOD_SID             INT,
               ADJUSTMENT_TYPE        VARCHAR(20),
               ADJUSTMENT_BASIS       VARCHAR(20),
               ADJUSTMENT_VARIABLE    BIT,
               ADJUSTMENT_METHODOLOGY VARCHAR(50),
               ADJUSTMENT_VALUES      NUMERIC(22, 6),
            )

          IF Object_id('tempdb..#TEMP_MASTER_TO') IS NOT NULL
            DROP TABLE #TEMP_MASTER_TO

          CREATE TABLE #TEMP_MASTER_TO
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT,
               METHODOLOGY            VARCHAR(100),
               USER_GROUP             VARCHAR(50),
               CALCULATION_PERIODS    VARCHAR(4000),
               CALCULATION_BASED      VARCHAR(100),
               CHECK_RECORD           BIT
            )

          IF ( @FROM_FORECAST_TYPE = 'Non Mandated' )
            BEGIN ;
                WITH TEMP_FROM_PROJECTION_MASTER
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.METHODOLOGY,
                                B.USER_GROUP,
                                B.CALCULATION_PERIODS,
                               -- B.CALCULATION_BASED,
                                B.CHECK_RECORD
                         FROM   PROJECTION_DETAILS A
                         JOIN   (SELECT B.CCP_DETAILS_SID,
                                        A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                        METHODOLOGY,
                                        USER_GROUP,
                                        CALCULATION_PERIODS,
                                       -- CALCULATION_BASED,
                                        CHECK_RECORD
                                 FROM   NM_SALES_PROJECTION_MASTER A
                                 JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                 WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION) B ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_FROM_PROJECTION)
                INSERT INTO #TEMP_MASTER
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             METHODOLOGY,
                             USER_GROUP,
                             CALCULATION_PERIODS,
                             --CALCULATION_BASED,
                             CHECK_RECORD)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       METHODOLOGY,
                       USER_GROUP,
                       CALCULATION_PERIODS,
                      -- CALCULATION_BASED,
                       CHECK_RECORD
                FROM   TEMP_FROM_PROJECTION_MASTER

                INSERT INTO NM_SALES_PROJECTION_MASTER
                            (PROJECTION_DETAILS_SID,
                             METHODOLOGY,
                             USER_GROUP,
                             CALCULATION_PERIODS,
                             CALCULATION_BASED,
                             CHECK_RECORD)
                SELECT PROJECTION_DETAILS_SID,
                       METHODOLOGY,
                       USER_GROUP,
                       CALCULATION_PERIODS,
                       CALCULATION_BASED,
                       CHECK_RECORD
                FROM   #TEMP_MASTER A
                WHERE  A.PROJECTION_DETAILS_SID NOT IN(SELECT B.PROJECTION_DETAILS_SID
                                                       FROM   NM_SALES_PROJECTION_MASTER B
                                                       WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID);

                WITH TEMP_FROM_PROJECTION_ACTUAL
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.PERIOD_SID,
                                B.ACTUAL_SALES,
                                B.ACTUAL_UNITS
                         FROM   PROJECTION_DETAILS A
                         JOIN   (SELECT B.CCP_DETAILS_SID,
                                        A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                        A.PERIOD_SID,
                                        A.ACTUAL_SALES,
                                        A.ACTUAL_UNITS
                                 FROM   NM_ACTUAL_SALES A
                                 JOIN   NM_SALES_PROJECTION_MASTER A1 ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                 JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                 WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION) B ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_FROM_PROJECTION)
                INSERT INTO #TEMP_ACTUALS
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             PERIOD_SID,
                             ACTUAL_SALES,
                             ACTUAL_UNITS)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       PERIOD_SID,
                       isnull(ACTUAL_SALES,0),
                       isnull(ACTUAL_UNITS,0)
                FROM   TEMP_FROM_PROJECTION_ACTUAL

                INSERT INTO NM_ACTUAL_SALES
                            (PROJECTION_DETAILS_SID,
                             PERIOD_SID,
                             ACTUAL_SALES,
                             ACTUAL_UNITS)
                SELECT PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       isnull(ACTUAL_SALES,0),
                       isnull(ACTUAL_UNITS,0)
                FROM   #TEMP_ACTUALS A
                WHERE  A.PROJECTION_DETAILS_SID NOT IN(SELECT B.PROJECTION_DETAILS_SID
                                                       FROM   NM_ACTUAL_SALES B
                                                       WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                                              AND A.PERIOD_SID = B.PERIOD_SID);

                WITH TEMP_FROM_PROJECTION_PROJECTION
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.ACCOUNT_GROWTH,
                                B.PRODUCT_GROWTH,
                                B.PROJECTION_SALES,
                                B.PROJECTION_UNITS,
                                B.PERIOD_SID
                         FROM   PROJECTION_DETAILS A
                         JOIN   (SELECT B.CCP_DETAILS_SID,
                                        A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                        A.ACCOUNT_GROWTH,
                                        A.PRODUCT_GROWTH,
                                        isnull(A.PROJECTION_SALES,0) PROJECTION_SALES,
                                        isnull(A.PROJECTION_UNITS,0) PROJECTION_UNITS,
                                        A.PERIOD_SID
                                 FROM   NM_SALES_PROJECTION A
                                 JOIN   NM_SALES_PROJECTION_MASTER A1 ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                 JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                 WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION) B ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_FROM_PROJECTION)
                INSERT INTO #TEMP_PROJECTION
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             ACCOUNT_GROWTH,
                             PRODUCT_GROWTH,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PERIOD_SID)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       ACCOUNT_GROWTH,
                       PRODUCT_GROWTH,
                        isnull(PROJECTION_SALES,0) PROJECTION_SALES,
                        isnull(PROJECTION_UNITS,0) PROJECTION_UNITS,
                       PERIOD_SID
                FROM   TEMP_FROM_PROJECTION_PROJECTION

                INSERT INTO #TEMP_PROJECTION_TEMP
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             ACCOUNT_GROWTH,
                             PRODUCT_GROWTH,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PERIOD_SID)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       ACCOUNT_GROWTH,
                       PRODUCT_GROWTH,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       PERIOD_SID
                FROM   #TEMP_PROJECTION

                UPDATE T
                SET    PROJECTION_SALES = 0,
                       PROJECTION_UNITS = 0,
                       ACCOUNT_GROWTH = 0,
                       PRODUCT_GROWTH = 0
                FROM   #TEMP_PROJECTION_TEMP T
                WHERE  @TRANSFER_FLAG = 1
                       AND PERIOD_SID > @CUST_PERIOD_END
                       AND PROJECTION_DETAILS_SID IN (SELECT PROJECTION_DETAILS_SID_SRC
                                                      FROM   #TEMP_DETAILS_MAPPED_CCP)

                INSERT INTO NM_SALES_PROJECTION
                            (PROJECTION_DETAILS_SID,
                             ACCOUNT_GROWTH,
                             PRODUCT_GROWTH,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PERIOD_SID)
                SELECT PROJECTION_DETAILS_SID,
                       ACCOUNT_GROWTH,
                       PRODUCT_GROWTH,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       PERIOD_SID
                FROM   #TEMP_PROJECTION_TEMP A
                WHERE  A.PROJECTION_DETAILS_SID NOT IN(SELECT B.PROJECTION_DETAILS_SID
                                                       FROM   NM_SALES_PROJECTION B
                                                       WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                                              AND A.PERIOD_SID = B.PERIOD_SID);
            END
          ELSE IF ( @FROM_FORECAST_TYPE = 'Mandated' )
            BEGIN ;
                WITH TEMP_FROM_PROJECTION_MASTER
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.METHODOLOGY,
                                B.CALCULATION_PERIODS,
                                B.CALCULATION_BASED,
                                B.CHECK_RECORD
                         FROM   PROJECTION_DETAILS A
                         JOIN   (SELECT B.CCP_DETAILS_SID,
                                        A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                        METHODOLOGY,
                                        CALCULATION_PERIODS,
                                        CALCULATION_BASED,
                                        CHECK_RECORD
                                 FROM   M_SALES_PROJECTION_MASTER A
                                 JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                 WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION) B ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_FROM_PROJECTION)
                INSERT INTO #TEMP_MASTER
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             METHODOLOGY,
                             CALCULATION_PERIODS,
                             CALCULATION_BASED,
                             CHECK_RECORD)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       METHODOLOGY,
                       CALCULATION_PERIODS,
                       CALCULATION_BASED,
                       CHECK_RECORD
                FROM   TEMP_FROM_PROJECTION_MASTER

                INSERT INTO M_SALES_PROJECTION_MASTER
                            (PROJECTION_DETAILS_SID,
                             METHODOLOGY,
                             CALCULATION_PERIODS,
                             CALCULATION_BASED,
                             CHECK_RECORD)
                SELECT PROJECTION_DETAILS_SID,
                       METHODOLOGY,
                       CALCULATION_PERIODS,
                       CALCULATION_BASED,
                       ISNULL(CHECK_RECORD, 0)
                FROM   #TEMP_MASTER A
                WHERE  A.PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
                                                        FROM   M_SALES_PROJECTION_MASTER B
                                                        WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID);

                WITH TEMP_FROM_PROJECTION_ACTUAL
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.PERIOD_SID,
                                B.ACTUAL_SALES,
                                B.ACTUAL_UNITS,
                                B.ACTUAL_PROJECTION_SALES,
                                B.ACTUAL_PROJECTION_UNITS
                         FROM   PROJECTION_DETAILS A
                         JOIN   (SELECT B.CCP_DETAILS_SID,
                                        A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                        A.PERIOD_SID,
                                        A.ACTUAL_SALES,
                                        A.ACTUAL_UNITS,
                                        A.ACTUAL_PROJECTION_SALES,
                                        A.ACTUAL_PROJECTION_UNITS
                                 FROM   M_ACTUAL_SALES A
                                 JOIN   M_SALES_PROJECTION_MASTER A1 ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                 JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                 WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION) B ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_FROM_PROJECTION)
                INSERT INTO #TEMP_ACTUALS
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             PERIOD_SID,
                             ACTUAL_SALES,
                             ACTUAL_UNITS,
                             HISTORY_PROJECTION_SALES,
                             HISTORY_PROJECTION_UNITS)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS,
                       ACTUAL_PROJECTION_SALES,
                       ACTUAL_PROJECTION_UNITS
                FROM   TEMP_FROM_PROJECTION_ACTUAL

                INSERT INTO M_ACTUAL_SALES
                            (PROJECTION_DETAILS_SID,
                             PERIOD_SID,
                             ACTUAL_SALES,
                             ACTUAL_UNITS,
                             ACTUAL_PROJECTION_SALES,
                             ACTUAL_PROJECTION_UNITS)
                SELECT PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS,
                       HISTORY_PROJECTION_SALES,
                       HISTORY_PROJECTION_UNITS
                FROM   #TEMP_ACTUALS A
                WHERE  A.PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
                                                        FROM   M_ACTUAL_SALES B
                                                        WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                                               AND A.PERIOD_SID = B.PERIOD_SID);

                WITH TEMP_FROM_PROJECTION_PROJECTION
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.ACCOUNT_GROWTH,
                                B.PRODUCT_GROWTH,
                                B.PROJECTION_SALES,
                                B.PROJECTION_UNITS,
                                B.PERIOD_SID,
                                B.ADJUSTMENT_TYPE,
                                B.ADJUSTMENT_BASIS,
                                B.ADJUSTMENT_VARIABLE,
                                B.ADJUSTMENT_METHODOLOGY,
                                B.ADJUSTMENT_VALUES
                         FROM   PROJECTION_DETAILS A
                         JOIN   (SELECT B.CCP_DETAILS_SID,
                                        A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                        A.ACCOUNT_GROWTH,
                                        A.PRODUCT_GROWTH,
                                        A.PROJECTION_SALES,
                                        A.PROJECTION_UNITS,
                                        A.PERIOD_SID,
                                        A.ADJUSTMENT_TYPE,
                                        A.ADJUSTMENT_BASIS,
                                        A.ADJUSTMENT_VARIABLE,
                                        A.ADJUSTMENT_METHODOLOGY,
                                        A.ADJUSTMENT_VALUES
                                 FROM   M_SALES_PROJECTION A
                                 JOIN   M_SALES_PROJECTION_MASTER A1 ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                 JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                 WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION) B ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_FROM_PROJECTION)
                INSERT INTO #TEMP_PROJECTION
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             ACCOUNT_GROWTH,
                             PRODUCT_GROWTH,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PERIOD_SID,
                             ADJUSTMENT_TYPE,
                             ADJUSTMENT_BASIS,
                             ADJUSTMENT_VARIABLE,
                             ADJUSTMENT_METHODOLOGY,
                             ADJUSTMENT_VALUES)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       ACCOUNT_GROWTH,
                       PRODUCT_GROWTH,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       PERIOD_SID,
                       ADJUSTMENT_TYPE,
                       ADJUSTMENT_BASIS,
                       ADJUSTMENT_VARIABLE,
                       ADJUSTMENT_METHODOLOGY,
                       ADJUSTMENT_VALUES
                FROM   TEMP_FROM_PROJECTION_PROJECTION

                INSERT INTO #TEMP_PROJECTION_TEMP
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             ACCOUNT_GROWTH,
                             PRODUCT_GROWTH,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PERIOD_SID,
                             ADJUSTMENT_TYPE,
                             ADJUSTMENT_BASIS,
                             ADJUSTMENT_VARIABLE,
                             ADJUSTMENT_METHODOLOGY,
                             ADJUSTMENT_VALUES)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       ACCOUNT_GROWTH,
                       PRODUCT_GROWTH,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       PERIOD_SID,
                       ADJUSTMENT_TYPE,
                       ADJUSTMENT_BASIS,
                       ADJUSTMENT_VARIABLE,
                       ADJUSTMENT_METHODOLOGY,
                       ADJUSTMENT_VALUES
                FROM   #TEMP_PROJECTION

                UPDATE T
                SET    PROJECTION_SALES = 0,
                       PROJECTION_UNITS = 0,
                       ACCOUNT_GROWTH = 0,
                       PRODUCT_GROWTH = 0,
                       ADJUSTMENT_TYPE = '',
                       ADJUSTMENT_BASIS = '',
                       ADJUSTMENT_VARIABLE = NULL,
                       ADJUSTMENT_METHODOLOGY = NULL,
                       ADJUSTMENT_VALUES = 0
                FROM   #TEMP_PROJECTION_TEMP T
                WHERE  @TRANSFER_FLAG = 1
                       AND PERIOD_SID > @CUST_PERIOD_END
                       AND PROJECTION_DETAILS_SID IN (SELECT PROJECTION_DETAILS_SID_SRC
                                                      FROM   #TEMP_DETAILS_MAPPED_CCP)

                INSERT INTO M_SALES_PROJECTION
                            (PROJECTION_DETAILS_SID,
                             ACCOUNT_GROWTH,
                             PRODUCT_GROWTH,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PERIOD_SID,
                             ADJUSTMENT_TYPE,
                             ADJUSTMENT_BASIS,
                             ADJUSTMENT_VARIABLE,
                             ADJUSTMENT_METHODOLOGY,
                             ADJUSTMENT_VALUES)
                SELECT PROJECTION_DETAILS_SID,
                       ACCOUNT_GROWTH,
                       PRODUCT_GROWTH,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       PERIOD_SID,
                       ADJUSTMENT_TYPE,
                       ADJUSTMENT_BASIS,
                       ADJUSTMENT_VARIABLE,
                       ADJUSTMENT_METHODOLOGY,
                       ADJUSTMENT_VALUES
                FROM   #TEMP_PROJECTION_TEMP A
                WHERE  A.PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
                                                        FROM   M_SALES_PROJECTION B
                                                        WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                                               AND A.PERIOD_SID = B.PERIOD_SID);
            END

          IF ( @TO_FORECAST_TYPE = 'Non Mandated' )
            BEGIN ;
                WITH TEMP_TO_PROJECTION_MASTER
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.METHODOLOGY,
                                B.USER_GROUP,
                                B.CALCULATION_PERIODS,
                                --B.CALCULATION_BASED,
                                B.CHECK_RECORD
                         FROM   PROJECTION_DETAILS A
                         JOIN   (SELECT B.CCP_DETAILS_SID,
                                        A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                        METHODOLOGY,
                                        USER_GROUP,
                                        CALCULATION_PERIODS,
                                        --CALCULATION_BASED,
                                        CHECK_RECORD
                                 FROM   NM_SALES_PROJECTION_MASTER A
                                 JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                 WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION) B ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)
                INSERT INTO #TEMP_MASTER_TO
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             METHODOLOGY,
                             USER_GROUP,
                             CALCULATION_PERIODS,
                             --CALCULATION_BASED,
                             CHECK_RECORD)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       METHODOLOGY,
                       USER_GROUP,
                       CALCULATION_PERIODS,
                      -- CALCULATION_BASED,
                       CHECK_RECORD
                FROM   TEMP_TO_PROJECTION_MASTER;

                INSERT INTO NM_SALES_PROJECTION_MASTER
                            (PROJECTION_DETAILS_SID,
                             METHODOLOGY,
                             USER_GROUP,
                             CALCULATION_PERIODS,
                             --CALCULATION_BASED,
                             CHECK_RECORD)
                SELECT PROJECTION_DETAILS_SID,
                       METHODOLOGY,
                       USER_GROUP,
                       CALCULATION_PERIODS,
                       --CALCULATION_BASED,
                       ISNULL(CHECK_RECORD, 0)
                FROM   #TEMP_MASTER_TO A
                WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
                                                      FROM   NM_SALES_PROJECTION_MASTER B
                                                      WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID);

                WITH TEMP_TO_PROJECTION_ACTUAL
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.PERIOD_SID,
                                B.ACTUAL_SALES,
                                B.ACTUAL_UNITS
                         FROM   PROJECTION_DETAILS A
                         JOIN   (SELECT B.CCP_DETAILS_SID,
                                        A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                        A.PERIOD_SID,
                                        A.ACTUAL_SALES,
                                        A.ACTUAL_UNITS
                                 FROM   NM_ACTUAL_SALES A
                                 JOIN   NM_SALES_PROJECTION_MASTER A1 ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                 JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                 WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION) B ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)
                INSERT INTO NM_ACTUAL_SALES
                            (PROJECTION_DETAILS_SID,
                             PERIOD_SID,
                             ACTUAL_SALES,
                             ACTUAL_UNITS)
                SELECT PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS
                FROM   TEMP_TO_PROJECTION_ACTUAL A
                WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
                                                      FROM   NM_ACTUAL_SALES B
                                                      WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                                             AND A.PERIOD_SID = B.PERIOD_SID);

                
				--Added the below check of @TRANSFER_SALES_FLAG to Handle Transfer Sales Flag scenario CEL-354--
				IF (@TRANSFER_SALES_FLAG=1)
					BEGIN
					/*IF @TRANSFER_FLAG=1
					BEGIN
					;WITH TEMP_TO_PROJECTION_PROJECTION
						 AS (SELECT    A.PROJECTION_DETAILS_SID,
									   A.CCP_DETAILS_SID,
									   ISNULL(D.ACCOUNT_GROWTH, B.ACCOUNT_GROWTH)                AS ACCOUNT_GROWTH,
									   ISNULL(D.PRODUCT_GROWTH, B.PRODUCT_GROWTH)                AS PRODUCT_GROWTH,
									   ISNULL(D.PROJECTION_SALES, B.PROJECTION_SALES)            AS PROJECTION_SALES,
									   ISNULL(D.PROJECTION_UNITS, B.PROJECTION_UNITS)            AS PROJECTION_UNITS,
									   ISNULL(D.PERIOD_SID, B.PERIOD_SID)                        AS PERIOD_SID,
									   ISNULL(D.ADJUSTMENT_TYPE, B.ADJUSTMENT_TYPE)              AS ADJUSTMENT_TYPE,
									   ISNULL(D.ADJUSTMENT_BASIS, B.ADJUSTMENT_BASIS)            AS ADJUSTMENT_BASIS,
									   ISNULL(D.ADJUSTMENT_VARIABLE, B.ADJUSTMENT_VARIABLE)      AS ADJUSTMENT_VARIABLE,
									   ISNULL(D.ADJUSTMENT_METHODOLOGY, B.ADJUSTMENT_METHODOLOGY)AS ADJUSTMENT_METHODOLOGY,
									   ISNULL(D.ADJUSTMENT_VALUES, B.ADJUSTMENT_VALUES)          AS ADJUSTMENT_VALUES
							 FROM      PROJECTION_DETAILS A
							 JOIN      (SELECT B.PROJECTION_DETAILS_SID,
											   B.CCP_DETAILS_SID,
											   A.ACCOUNT_GROWTH,
											   A.PRODUCT_GROWTH,
											   A.PROJECTION_SALES,
											   A.PROJECTION_UNITS,
											   A.PERIOD_SID,
											   A.ADJUSTMENT_TYPE,
											   A.ADJUSTMENT_BASIS,
											   A.ADJUSTMENT_VARIABLE,
											   A.ADJUSTMENT_METHODOLOGY,
											   A.ADJUSTMENT_VALUES
										FROM   NM_SALES_PROJECTION A
										JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
										WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION) B ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
							 LEFT JOIN #TEMP_DETAILS_MAPPED_CCP C ON A.CCP_DETAILS_SID = C.CCP_MAP
							 LEFT JOIN ((SELECT PROJECTION_DETAILS_SID,
												ACCOUNT_GROWTH,
												PRODUCT_GROWTH,
												PROJECTION_SALES,
												PROJECTION_UNITS,
												PERIOD_SID,
												ADJUSTMENT_TYPE,
												ADJUSTMENT_BASIS,
												ADJUSTMENT_VARIABLE,
												ADJUSTMENT_METHODOLOGY,
												ADJUSTMENT_VALUES,
												CCP_DETAILS_SID
										 FROM   #TEMP_PROJECTION_TEMP
										 WHERE  PERIOD_SID >= @CUST_PERIOD_START))D ON C.CCP_SOURCE = D.CCP_DETAILS_SID
																				   AND D.PERIOD_SID = B.PERIOD_SID
							 WHERE     A.PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)

							 INSERT INTO NM_SALES_PROJECTION
                            (PROJECTION_DETAILS_SID,
                             ACCOUNT_GROWTH,
                             PRODUCT_GROWTH,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PERIOD_SID,
                             ADJUSTMENT_TYPE,
                             ADJUSTMENT_BASIS,
                             ADJUSTMENT_VARIABLE,
                             ADJUSTMENT_METHODOLOGY,
                             ADJUSTMENT_VALUES)
							SELECT PROJECTION_DETAILS_SID,
									ACCOUNT_GROWTH,
									PRODUCT_GROWTH,
									PROJECTION_SALES,
									PROJECTION_UNITS,
									PERIOD_SID,
									ADJUSTMENT_TYPE,
									ADJUSTMENT_BASIS,
									ADJUSTMENT_VARIABLE,
									ADJUSTMENT_METHODOLOGY,
									ADJUSTMENT_VALUES
							FROM   TEMP_TO_PROJECTION_PROJECTION A
							WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
																	FROM   NM_SALES_PROJECTION B
																	WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
																			AND A.PERIOD_SID = B.PERIOD_SID);
					END
					ELSE 
					BEGIN*/
					;WITH TEMP_TO_PROJECTION_PROJECTION
						 AS (SELECT    A.PROJECTION_DETAILS_SID,
									   A.CCP_DETAILS_SID,
									   ISNULL(D.ACCOUNT_GROWTH, B.ACCOUNT_GROWTH)                AS ACCOUNT_GROWTH,
									   ISNULL(D.PRODUCT_GROWTH, B.PRODUCT_GROWTH)                AS PRODUCT_GROWTH,
									   ISNULL(D.PROJECTION_SALES, B.PROJECTION_SALES)            AS PROJECTION_SALES,
									   ISNULL(D.PROJECTION_UNITS, B.PROJECTION_UNITS)            AS PROJECTION_UNITS,
									   ISNULL(D.PERIOD_SID, B.PERIOD_SID)                        AS PERIOD_SID
							 FROM      PROJECTION_DETAILS A
							 JOIN      (SELECT B.PROJECTION_DETAILS_SID,
											   B.CCP_DETAILS_SID,
											   A.ACCOUNT_GROWTH,
											   A.PRODUCT_GROWTH,
											   A.PROJECTION_SALES,
											   A.PROJECTION_UNITS,
											   A.PERIOD_SID
										FROM   NM_SALES_PROJECTION A
										JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
										WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION) B ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
							 LEFT JOIN #TEMP_DETAILS_MAPPED_CCP C ON A.CCP_DETAILS_SID = C.CCP_MAP
							 LEFT JOIN ((SELECT PROJECTION_DETAILS_SID,
												ACCOUNT_GROWTH,
												PRODUCT_GROWTH,
												PROJECTION_SALES,
												PROJECTION_UNITS,
												PERIOD_SID
												CCP_DETAILS_SID
										 FROM   #TEMP_PROJECTION
										 WHERE  PERIOD_SID >= @CUST_PERIOD_START))D ON C.CCP_SOURCE = D.CCP_DETAILS_SID
																				   AND D.PERIOD_SID = B.PERIOD_SID
							 WHERE     A.PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)

							 INSERT INTO NM_SALES_PROJECTION
                            (PROJECTION_DETAILS_SID,
                             ACCOUNT_GROWTH,
                             PRODUCT_GROWTH,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PERIOD_SID
                            )
							SELECT PROJECTION_DETAILS_SID,
									ACCOUNT_GROWTH,
									PRODUCT_GROWTH,
									PROJECTION_SALES,
									PROJECTION_UNITS,
									PERIOD_SID
							FROM   TEMP_TO_PROJECTION_PROJECTION A
							WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
																	FROM   NM_SALES_PROJECTION B
																	WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
																			AND A.PERIOD_SID = B.PERIOD_SID);
					END
					--END
					
				  ELSE IF (@TRANSFER_SALES_FLAG=0)
						BEGIN;
					  	WITH TEMP_TO_PROJECTION_PROJECTION
						 AS (SELECT    A.PROJECTION_DETAILS_SID,
									   A.CCP_DETAILS_SID,
									   B.ACCOUNT_GROWTH,
									   B.PRODUCT_GROWTH,
									   B.PROJECTION_SALES,
									   B.PROJECTION_UNITS,
									   B.PERIOD_SID
							 FROM      PROJECTION_DETAILS A
							 JOIN      (SELECT B.PROJECTION_DETAILS_SID,
											   B.CCP_DETAILS_SID,
											   A.ACCOUNT_GROWTH,
											   A.PRODUCT_GROWTH,
											   A.PROJECTION_SALES,
											   A.PROJECTION_UNITS,
											   A.PERIOD_SID
										FROM   NM_SALES_PROJECTION A
										JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
										WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION) B ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
							 LEFT JOIN #TEMP_DETAILS_MAPPED_CCP C ON A.CCP_DETAILS_SID = C.CCP_MAP
							 WHERE     A.PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)
							 INSERT INTO NM_SALES_PROJECTION
                            (PROJECTION_DETAILS_SID,
                             ACCOUNT_GROWTH,
                             PRODUCT_GROWTH,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PERIOD_SID)
							SELECT PROJECTION_DETAILS_SID,
								   ACCOUNT_GROWTH,
								   PRODUCT_GROWTH,
								   PROJECTION_SALES,
								   PROJECTION_UNITS,
								   PERIOD_SID
							FROM   TEMP_TO_PROJECTION_PROJECTION A
							WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
																  FROM   NM_SALES_PROJECTION B
																  WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
																		 AND A.PERIOD_SID = B.PERIOD_SID);
							END
               
            END
          ELSE IF ( @TO_FORECAST_TYPE = 'Mandated' )
            BEGIN ;
                WITH TEMP_TO_PROJECTION_MASTER
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.METHODOLOGY,
                                B.CALCULATION_PERIODS,
                                B.CALCULATION_BASED,
                                B.CHECK_RECORD
                         FROM   PROJECTION_DETAILS A
                         JOIN   (SELECT B.CCP_DETAILS_SID,
                                        A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                        METHODOLOGY,
                                        CALCULATION_PERIODS,
                                        CALCULATION_BASED,
                                        CHECK_RECORD
                                 FROM   M_SALES_PROJECTION_MASTER A
                                 JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                 WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION) B ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)
                INSERT INTO #TEMP_MASTER_TO
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             METHODOLOGY,
                             CALCULATION_PERIODS,
                             CALCULATION_BASED,
                             CHECK_RECORD)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       METHODOLOGY,
                       CALCULATION_PERIODS,
                       CALCULATION_BASED,
                       CHECK_RECORD
                FROM   TEMP_TO_PROJECTION_MASTER;

                INSERT INTO M_SALES_PROJECTION_MASTER
                            (PROJECTION_DETAILS_SID,
                             METHODOLOGY,
                             CALCULATION_PERIODS,
                             CALCULATION_BASED,
                             CHECK_RECORD)
                SELECT PROJECTION_DETAILS_SID,
                       METHODOLOGY,
                       CALCULATION_PERIODS,
                       CALCULATION_BASED,
                       ISNULL(CHECK_RECORD, 0)
                FROM   #TEMP_MASTER_TO A
                WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
                                                      FROM   M_SALES_PROJECTION_MASTER B
                                                      WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID);

                WITH TEMP_TO_PROJECTION_ACTUAL
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.PERIOD_SID,
                                B.ACTUAL_SALES,
                                B.ACTUAL_UNITS,
                                B.ACTUAL_PROJECTION_SALES,
                                B.ACTUAL_PROJECTION_UNITS
                         FROM   PROJECTION_DETAILS A
                         JOIN   (SELECT B.CCP_DETAILS_SID,
                                        A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                        A.PERIOD_SID,
                                        A.ACTUAL_SALES,
                                        A.ACTUAL_UNITS,
                                        A.ACTUAL_PROJECTION_SALES,
                                        A.ACTUAL_PROJECTION_UNITS
                                 FROM   M_ACTUAL_SALES A
                                 JOIN   M_SALES_PROJECTION_MASTER A1 ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                 JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                 WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION) B ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)
                INSERT INTO NM_ACTUAL_SALES
                            (PROJECTION_DETAILS_SID,
                             PERIOD_SID,
                             ACTUAL_SALES,
                             ACTUAL_UNITS)
                SELECT PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS
                FROM   TEMP_TO_PROJECTION_ACTUAL A
                WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
                                                      FROM   M_ACTUAL_SALES B
                                                      WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                                             AND A.PERIOD_SID = B.PERIOD_SID);
				IF (@TRANSFER_SALES_FLAG=1)
				BEGIN;
								WITH TEMP_TO_PROJECTION_PROJECTION
									 AS (SELECT    A.PROJECTION_DETAILS_SID,
												   A.CCP_DETAILS_SID,
												   ISNULL(D.ACCOUNT_GROWTH, B.ACCOUNT_GROWTH)                AS ACCOUNT_GROWTH,
												   ISNULL(D.PRODUCT_GROWTH, B.PRODUCT_GROWTH)                AS PRODUCT_GROWTH,
												   ISNULL(D.PROJECTION_SALES, B.PROJECTION_SALES)            AS PROJECTION_SALES,
												   ISNULL(D.PROJECTION_UNITS, B.PROJECTION_UNITS)            AS PROJECTION_UNITS,
												   ISNULL(D.PERIOD_SID, B.PERIOD_SID)                        AS PERIOD_SID,
												   ISNULL(D.ADJUSTMENT_TYPE, B.ADJUSTMENT_TYPE)              AS ADJUSTMENT_TYPE,
												   ISNULL(D.ADJUSTMENT_BASIS, B.ADJUSTMENT_BASIS)            AS ADJUSTMENT_BASIS,
												   ISNULL(D.ADJUSTMENT_VARIABLE, B.ADJUSTMENT_VARIABLE)      AS ADJUSTMENT_VARIABLE,
												   ISNULL(D.ADJUSTMENT_METHODOLOGY, B.ADJUSTMENT_METHODOLOGY)AS ADJUSTMENT_METHODOLOGY,
												   ISNULL(D.ADJUSTMENT_VALUES, B.ADJUSTMENT_VALUES)          AS ADJUSTMENT_VALUES
										 FROM      PROJECTION_DETAILS A
										 JOIN      (SELECT B.PROJECTION_DETAILS_SID,
														   B.CCP_DETAILS_SID,
														   A.ACCOUNT_GROWTH,
														   A.PRODUCT_GROWTH,
														   A.PROJECTION_SALES,
														   A.PROJECTION_UNITS,
														   A.PERIOD_SID,
														   A.ADJUSTMENT_TYPE,
														   A.ADJUSTMENT_BASIS,
														   A.ADJUSTMENT_VARIABLE,
														   A.ADJUSTMENT_METHODOLOGY,
														   A.ADJUSTMENT_VALUES
													FROM   M_SALES_PROJECTION A
													JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
													WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION) B ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
										 LEFT JOIN #TEMP_DETAILS_MAPPED_CCP C ON A.CCP_DETAILS_SID = C.CCP_MAP
										 LEFT JOIN (SELECT PROJECTION_DETAILS_SID,
														   ACCOUNT_GROWTH,
														   PRODUCT_GROWTH,
														   PROJECTION_SALES,
														   PROJECTION_UNITS,
														   PERIOD_SID,
														   ADJUSTMENT_TYPE,
														   ADJUSTMENT_BASIS,
														   ADJUSTMENT_VARIABLE,
														   ADJUSTMENT_METHODOLOGY,
														   ADJUSTMENT_VALUES,
														   CCP_DETAILS_SID
													FROM   #TEMP_PROJECTION
													WHERE  PERIOD_SID >= @CUST_PERIOD_START)D ON C.CCP_SOURCE = D.CCP_DETAILS_SID
																							 AND D.PERIOD_SID = B.PERIOD_SID
										 WHERE     A.PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)
								INSERT INTO M_SALES_PROJECTION
											(PROJECTION_DETAILS_SID,
											 ACCOUNT_GROWTH,
											 PRODUCT_GROWTH,
											 PROJECTION_SALES,
											 PROJECTION_UNITS,
											 PERIOD_SID,
											 ADJUSTMENT_TYPE,
											 ADJUSTMENT_BASIS,
											 ADJUSTMENT_VARIABLE,
											 ADJUSTMENT_METHODOLOGY,
											 ADJUSTMENT_VALUES)
								SELECT PROJECTION_DETAILS_SID,
									   ACCOUNT_GROWTH,
									   PRODUCT_GROWTH,
									   PROJECTION_SALES,
									   PROJECTION_UNITS,
									   PERIOD_SID,
									   ADJUSTMENT_TYPE,
									   ADJUSTMENT_BASIS,
									   ADJUSTMENT_VARIABLE,
									   ADJUSTMENT_METHODOLOGY,
									   ADJUSTMENT_VALUES
								FROM   TEMP_TO_PROJECTION_PROJECTION A
								WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
																	  FROM   M_SALES_PROJECTION B
																	  WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
																			 AND A.PERIOD_SID = B.PERIOD_SID);
				END
				ELSE IF (@TRANSFER_SALES_FLAG=0)
				BEGIN;
									WITH TEMP_TO_PROJECTION_PROJECTION
									 AS (SELECT    A.PROJECTION_DETAILS_SID,
												   A.CCP_DETAILS_SID,
												   B.ACCOUNT_GROWTH,
												   B.PRODUCT_GROWTH,
												   B.PROJECTION_SALES,
												   B.PROJECTION_UNITS,
												   B.PERIOD_SID,
												   B.ADJUSTMENT_TYPE,
												   B.ADJUSTMENT_BASIS,
												   B.ADJUSTMENT_VARIABLE,
												   B.ADJUSTMENT_METHODOLOGY,
												   B.ADJUSTMENT_VALUES
										 FROM      PROJECTION_DETAILS A
										 JOIN      (SELECT B.PROJECTION_DETAILS_SID,
														   B.CCP_DETAILS_SID,
														   A.ACCOUNT_GROWTH,
														   A.PRODUCT_GROWTH,
														   A.PROJECTION_SALES,
														   A.PROJECTION_UNITS,
														   A.PERIOD_SID,
														   A.ADJUSTMENT_TYPE,
														   A.ADJUSTMENT_BASIS,
														   A.ADJUSTMENT_VARIABLE,
														   A.ADJUSTMENT_METHODOLOGY,
														   A.ADJUSTMENT_VALUES
													FROM   M_SALES_PROJECTION A
													JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
													WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION) B ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
										 LEFT JOIN #TEMP_DETAILS_MAPPED_CCP C ON A.CCP_DETAILS_SID = C.CCP_MAP
										 WHERE     A.PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)
								INSERT INTO M_SALES_PROJECTION
											(PROJECTION_DETAILS_SID,
											 ACCOUNT_GROWTH,
											 PRODUCT_GROWTH,
											 PROJECTION_SALES,
											 PROJECTION_UNITS,
											 PERIOD_SID,
											 ADJUSTMENT_TYPE,
											 ADJUSTMENT_BASIS,
											 ADJUSTMENT_VARIABLE,
											 ADJUSTMENT_METHODOLOGY,
											 ADJUSTMENT_VALUES)
								SELECT PROJECTION_DETAILS_SID,
									   ACCOUNT_GROWTH,
									   PRODUCT_GROWTH,
									   PROJECTION_SALES,
									   PROJECTION_UNITS,
									   PERIOD_SID,
									   ADJUSTMENT_TYPE,
									   ADJUSTMENT_BASIS,
									   ADJUSTMENT_VARIABLE,
									   ADJUSTMENT_METHODOLOGY,
									   ADJUSTMENT_VALUES
								FROM   TEMP_TO_PROJECTION_PROJECTION A
								WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
																	  FROM   M_SALES_PROJECTION B
																	  WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
																			 AND A.PERIOD_SID = B.PERIOD_SID);
				END
            END
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