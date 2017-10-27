IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_FE_TRANSFER_SALES'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_FE_TRANSFER_SALES]
  END

GO

CREATE PROCEDURE [dbo].[PRC_FE_TRANSFER_SALES] (@SESSION_ID INT)
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
                  @ITEM_MASTER_SID          VARCHAR(100),
                  @FROM_FORECAST_TYPE       VARCHAR(50),
                  @TO_FORECAST_TYPE         VARCHAR(50),
                  @TRANSFER_FLAG            BIT,
                  @CUST_PERIOD_END          INT,
                  @TO_START_DATE            DATETIME,
                  @CUST_PERIOD_START        INT,
                  @FORECAST_TO_START_DATE   DATETIME,
                  @FORECAST_TO_END_DATE     DATETIME,
                  @ACTUAL_START             INT,
                  @ACTUAL_END               INT

          SELECT @FROM_PROJECTION = FROM_PROJECTION,
                 @TO_PROJECTION = TO_PROJECTION,
                 @COPY_FROM_PROJECTION = COPY_FROM_PROJECTION,
                 @COPY_TO_PROJECTION = COPY_TO_PROJECTION,
                 @CUSTOMER_END_DATE = FROM_END_DATE,
                 @FROM_CONTRACT_MASTER_SID = FROM_CONTRACT_MASTER_SID,
                 @TO_CONTRACT_MASTER_SID = TO_CONTRACT_MASTER_SID,
                 @COMPANY_MASTER_SID = COMPANY_MASTER_SID,
                 @ITEM_MASTER_SID = ITEM_MASTER_SID,
                 @TO_START_DATE = TO_START_DATE,
                 @TRANSFER_FLAG = TRANSFER_FLAG
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

          IF Object_id('tempdb..#TEMP_MASTER_TO') IS NOT NULL
            DROP TABLE #TEMP_MASTER_TO

          CREATE TABLE #TEMP_MASTER_TO
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT,
               METHODOLOGY            VARCHAR(50),
               USER_GROUP             VARCHAR(50),
               CALCULATION_PERIODS    VARCHAR(4000),
               CALCULATION_BASED      VARCHAR(100),
               CHECK_RECORD           BIT
            )

          IF Object_id('tempdb..#TEMP_MASTER_TO_EXCLUDED') IS NOT NULL
            DROP TABLE #TEMP_MASTER_TO_EXCLUDED

          CREATE TABLE #TEMP_MASTER_TO_EXCLUDED
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT
            )

          IF Object_id('tempdb..#TEMP_MASTER') IS NOT NULL
            DROP TABLE #TEMP_MASTER

          CREATE TABLE #TEMP_MASTER
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT,
               METHODOLOGY            VARCHAR(50),
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

          IF Object_id('tempdb..#TEMP_CUSTOMER') IS NOT NULL
            DROP TABLE #TEMP_CUSTOMER

          CREATE TABLE #TEMP_CUSTOMER
            (
               ID                 INT IDENTITY(1, 1),
               COMPANY_MASTER_SID INT
            )

          IF Object_id('tempdb..#TEMP_ITEM') IS NOT NULL
            DROP TABLE #TEMP_ITEM

          CREATE TABLE #TEMP_ITEM
            (
               ID              INT IDENTITY(1, 1),
               ITEM_MASTER_SID INT
            )

          IF( @COMPANY_MASTER_SID IS NOT NULL )
            BEGIN
                PRINT 'Transfer Customer'

                INSERT INTO #TEMP_CUSTOMER
                            (COMPANY_MASTER_SID)
                SELECT token
                FROM   UDF_SPLITSTRING (@COMPANY_MASTER_SID, ',')

                IF ( @FROM_FORECAST_TYPE = 'Non Mandated' )
                  BEGIN
                      INSERT INTO #TEMP_ITEM
                                  (ITEM_MASTER_SID)
                      SELECT DISTINCT C.ITEM_MASTER_SID
                      FROM   NM_SALES_PROJECTION_MASTER A
                             JOIN PROJECTION_DETAILS B
                               ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                             JOIN CCP_DETAILS C
                               ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                             JOIN #TEMP_CUSTOMER T
                               ON T.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID
                      WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION
                             AND C.CONTRACT_MASTER_SID = @FROM_CONTRACT_MASTER_SID
                  END
                ELSE
                  BEGIN
                      INSERT INTO #TEMP_ITEM
                                  (ITEM_MASTER_SID)
                      SELECT DISTINCT C.ITEM_MASTER_SID
                      FROM   M_SALES_PROJECTION_MASTER A
                             JOIN PROJECTION_DETAILS B
                               ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                             JOIN CCP_DETAILS C
                               ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                             JOIN #TEMP_CUSTOMER T
                               ON T.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID
                      WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION
                             AND C.CONTRACT_MASTER_SID = @FROM_CONTRACT_MASTER_SID
                  END
            END
          ELSE
            BEGIN
                PRINT 'Transfer Item'

                INSERT INTO #TEMP_ITEM
                            (ITEM_MASTER_SID)
                SELECT token
                FROM   UDF_SPLITSTRING (@ITEM_MASTER_SID, ',')

                IF ( @FROM_FORECAST_TYPE = 'Non Mandated' )
                  BEGIN
                      INSERT INTO #TEMP_CUSTOMER
                                  (COMPANY_MASTER_SID)
                      SELECT DISTINCT C.COMPANY_MASTER_SID
                      FROM   NM_SALES_PROJECTION_MASTER A
                             JOIN PROJECTION_DETAILS B
                               ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                             JOIN CCP_DETAILS C
                               ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                             JOIN #TEMP_ITEM T
                               ON T.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                      WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION
                             AND C.CONTRACT_MASTER_SID = @FROM_CONTRACT_MASTER_SID
                  END
                ELSE
                  BEGIN
                      INSERT INTO #TEMP_CUSTOMER
                                  (COMPANY_MASTER_SID)
                      SELECT DISTINCT C.COMPANY_MASTER_SID
                      FROM   M_SALES_PROJECTION_MASTER A
                             JOIN PROJECTION_DETAILS B
                               ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                             JOIN CCP_DETAILS C
                               ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                             JOIN #TEMP_ITEM T
                               ON T.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                      WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION
                             AND C.CONTRACT_MASTER_SID = @FROM_CONTRACT_MASTER_SID
                  END
            END

          IF ( @FROM_FORECAST_TYPE = 'Non Mandated' )
            BEGIN ;
                WITH TEMP_FROM_PROJECTION_MASTER
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.METHODOLOGY,
                                B.USER_GROUP,
                                B.CALCULATION_PERIODS,
                                B.CALCULATION_BASED,
                                B.CHECK_RECORD
                         FROM   PROJECTION_DETAILS A
                                JOIN (SELECT B.CCP_DETAILS_SID,
                                             A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                             METHODOLOGY,
                                             USER_GROUP,
                                             CALCULATION_PERIODS,
                                             CALCULATION_BASED,
                                             CHECK_RECORD
                                      FROM   NM_SALES_PROJECTION_MASTER A
                                             JOIN PROJECTION_DETAILS B
                                               ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                      WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION) B
                                  ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_FROM_PROJECTION)
                INSERT INTO #TEMP_MASTER
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             METHODOLOGY,
                             USER_GROUP,
                             CALCULATION_PERIODS,
                             CALCULATION_BASED,
                             CHECK_RECORD)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       METHODOLOGY,
                       USER_GROUP,
                       CALCULATION_PERIODS,
                       CALCULATION_BASED,
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
                                B.ACTUAL_UNITS,
                                B.HISTORY_PROJECTION_SALES,
                                B.HISTORY_PROJECTION_UNITS
                         FROM   PROJECTION_DETAILS A
                                JOIN (SELECT B.CCP_DETAILS_SID,
                                             A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                             A.PERIOD_SID,
                                             A.ACTUAL_SALES,
                                             A.ACTUAL_UNITS,
                                             A.HISTORY_PROJECTION_SALES,
                                             A.HISTORY_PROJECTION_UNITS
                                      FROM   NM_ACTUAL_SALES A
                                             JOIN NM_SALES_PROJECTION_MASTER A1
                                               ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                             JOIN PROJECTION_DETAILS B
                                               ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                      WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION) B
                                  ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
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
                       HISTORY_PROJECTION_SALES,
                       HISTORY_PROJECTION_UNITS
                FROM   TEMP_FROM_PROJECTION_ACTUAL

                INSERT INTO NM_ACTUAL_SALES
                            (PROJECTION_DETAILS_SID,
                             PERIOD_SID,
                             ACTUAL_SALES,
                             ACTUAL_UNITS,
                             HISTORY_PROJECTION_SALES,
                             HISTORY_PROJECTION_UNITS)
                SELECT PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS,
                       HISTORY_PROJECTION_SALES,
                       HISTORY_PROJECTION_UNITS
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
                                B.PERIOD_SID,
                                B.ADJUSTMENT_TYPE,
                                B.ADJUSTMENT_BASIS,
                                B.ADJUSTMENT_VARIABLE,
                                B.ADJUSTMENT_METHODOLOGY,
                                B.ADJUSTMENT_VALUES
                         FROM   PROJECTION_DETAILS A
                                JOIN (SELECT B.CCP_DETAILS_SID,
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
                                      FROM   NM_SALES_PROJECTION A
                                             JOIN NM_SALES_PROJECTION_MASTER A1
                                               ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                             JOIN PROJECTION_DETAILS B
                                               ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                      WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION) B
                                  ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
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
                       JOIN (SELECT PROJECTION_DETAILS_SID,
                                    PERIOD_SID
                             FROM   #TEMP_PROJECTION_TEMP A
                                    JOIN CCP_DETAILS B
                                      ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                             WHERE  @TRANSFER_FLAG = 1
                                    AND PERIOD_SID > @CUST_PERIOD_END
                                    AND CONTRACT_MASTER_SID = @FROM_CONTRACT_MASTER_SID
                                    AND COMPANY_MASTER_SID IN (SELECT COMPANY_MASTER_SID
                                                               FROM   #TEMP_CUSTOMER)
                                    AND ITEM_MASTER_SID IN (SELECT ITEM_MASTER_SID
                                                            FROM   #TEMP_ITEM))A
                         ON A.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID
                            AND A.PERIOD_SID = T.PERIOD_SID

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
                                JOIN (SELECT B.CCP_DETAILS_SID,
                                             A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                             METHODOLOGY,
                                             CALCULATION_PERIODS,
                                             CALCULATION_BASED,
                                             CHECK_RECORD
                                      FROM   M_SALES_PROJECTION_MASTER A
                                             JOIN PROJECTION_DETAILS B
                                               ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                      WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION) B
                                  ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
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
                                JOIN (SELECT B.CCP_DETAILS_SID,
                                             A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                             A.PERIOD_SID,
                                             A.ACTUAL_SALES,
                                             A.ACTUAL_UNITS,
                                             A.ACTUAL_PROJECTION_SALES,
                                             A.ACTUAL_PROJECTION_UNITS
                                      FROM   M_ACTUAL_SALES A
                                             JOIN M_SALES_PROJECTION_MASTER A1
                                               ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                             JOIN PROJECTION_DETAILS B
                                               ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                      WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION) B
                                  ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
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
                                JOIN (SELECT B.CCP_DETAILS_SID,
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
                                             JOIN M_SALES_PROJECTION_MASTER A1
                                               ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                             JOIN PROJECTION_DETAILS B
                                               ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                      WHERE  B.PROJECTION_MASTER_SID = @FROM_PROJECTION) B
                                  ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
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
                       JOIN (SELECT PROJECTION_DETAILS_SID,
                                    PERIOD_SID
                             FROM   #TEMP_PROJECTION_TEMP A
                                    JOIN CCP_DETAILS B
                                      ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                             WHERE  @TRANSFER_FLAG = 1
                                    AND PERIOD_SID > @CUST_PERIOD_END
                                    AND CONTRACT_MASTER_SID = @FROM_CONTRACT_MASTER_SID
                                    AND COMPANY_MASTER_SID IN (SELECT COMPANY_MASTER_SID
                                                               FROM   #TEMP_CUSTOMER)
                                    AND ITEM_MASTER_SID IN (SELECT ITEM_MASTER_SID
                                                            FROM   #TEMP_ITEM))A
                         ON A.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID
                            AND A.PERIOD_SID = T.PERIOD_SID

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

          IF Object_id('tempdb..#TEMP_TO_CCP') IS NOT NULL
            DROP TABLE #TEMP_TO_CCP

          CREATE TABLE #TEMP_TO_CCP
            (
               NEW_PROJECTION_DETAILS_SID INT,
               NEW_CCP                    INT,
               OLD_CCP                    INT,
               OLD_PROJECTION_DETAILS_SID INT,
               FROM_CONTRACT              INT,
               TO_CONTRACT                INT,
               ITEM_MASTER_SID            INT,
               COMPANY_MASTER_SID         INT
            );

          ------------------------------------------- Getting old CCP_DETAILS_SID for newly added combination in COPY_TO_PROJECTION
          INSERT INTO #TEMP_TO_CCP
                      (NEW_CCP,
                       NEW_PROJECTION_DETAILS_SID,
                       OLD_CCP,
                       OLD_PROJECTION_DETAILS_SID,
                       FROM_CONTRACT,
                       TO_CONTRACT,
                       ITEM_MASTER_SID,
                       COMPANY_MASTER_SID)
          SELECT A.CCP_DETAILS_SID     AS NEW_CCP,
                 A.PROJECTION_DETAILS_SID,
                 C.CCP_DETAILS_SID     AS OLD_CCP,
                 C.PROJECTION_DETAILS_SID,
                 C.CONTRACT_MASTER_SID AS FROM_CONTRACT,
                 B.CONTRACT_MASTER_SID AS TO_CONTRACT,
                 B.ITEM_MASTER_SID,
                 B.COMPANY_MASTER_SID
          FROM   PROJECTION_DETAILS A
                 JOIN CCP_DETAILS B
                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                 JOIN (SELECT B.CONTRACT_MASTER_SID,
                              B.COMPANY_MASTER_SID,
                              B.ITEM_MASTER_SID,
                              B.CCP_DETAILS_SID,
                              A.PROJECTION_DETAILS_SID
                       FROM   #TEMP_MASTER A
                              JOIN CCP_DETAILS B
                                ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                       WHERE  B.CONTRACT_MASTER_SID = @FROM_CONTRACT_MASTER_SID
                              AND B.COMPANY_MASTER_SID IN (SELECT COMPANY_MASTER_SID
                                                           FROM   #TEMP_CUSTOMER)
                              AND B.ITEM_MASTER_SID IN (SELECT ITEM_MASTER_SID
                                                        FROM   #TEMP_ITEM)) C
                   ON C.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID
                      AND C.ITEM_MASTER_SID = B.ITEM_MASTER_SID
          WHERE  PROJECTION_MASTER_SID = @COPY_TO_PROJECTION
                 AND B.CONTRACT_MASTER_SID = @TO_CONTRACT_MASTER_SID

          IF ( @TO_FORECAST_TYPE = 'Non Mandated' )
            BEGIN
                SELECT @ACTUAL_START = Min(PERIOD_SID),
                       @ACTUAL_END = Max(PERIOD_SID)
                FROM   NM_ACTUAL_SALES A
                       JOIN PROJECTION_DETAILS B
                         ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION;

                WITH TEMP_TO_PROJECTION_MASTER
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.METHODOLOGY,
                                B.USER_GROUP,
                                B.CALCULATION_PERIODS,
                                B.CALCULATION_BASED,
                                B.CHECK_RECORD
                         FROM   PROJECTION_DETAILS A
                                LEFT JOIN (SELECT B.CCP_DETAILS_SID,
                                                  A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                                  METHODOLOGY,
                                                  USER_GROUP,
                                                  CALCULATION_PERIODS,
                                                  CALCULATION_BASED,
                                                  CHECK_RECORD
                                           FROM   NM_SALES_PROJECTION_MASTER A
                                                  JOIN PROJECTION_DETAILS B
                                                    ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                           WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION
                                                  AND B.CCP_DETAILS_SID NOT IN (SELECT NEW_CCP
                                                                                FROM   #TEMP_TO_CCP)
                                           UNION
                                           SELECT D.NEW_CCP,
                                                  A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                                  METHODOLOGY,
                                                  USER_GROUP,
                                                  CALCULATION_PERIODS,
                                                  CALCULATION_BASED,
                                                  CHECK_RECORD
                                           FROM   #TEMP_MASTER A
                                                  JOIN CCP_DETAILS C
                                                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                  JOIN #TEMP_TO_CCP D
                                                    ON D.OLD_CCP = C.CCP_DETAILS_SID
                                           WHERE  CONTRACT_MASTER_SID = @FROM_CONTRACT_MASTER_SID) B
                                       ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)
                INSERT INTO #TEMP_MASTER_TO
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             METHODOLOGY,
                             USER_GROUP,
                             CALCULATION_PERIODS,
                             CALCULATION_BASED,
                             CHECK_RECORD)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       METHODOLOGY,
                       USER_GROUP,
                       CALCULATION_PERIODS,
                       CALCULATION_BASED,
                       CHECK_RECORD
                FROM   TEMP_TO_PROJECTION_MASTER;

                INSERT INTO #TEMP_MASTER_TO_EXCLUDED
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID
                FROM   #TEMP_MASTER_TO
                WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT A.PROJECTION_DETAILS_SID
                                                      FROM   #TEMP_MASTER_TO A
                                                             JOIN (SELECT B.CCP_DETAILS_SID
                                                                   FROM   NM_SALES_PROJECTION_MASTER A
                                                                          JOIN PROJECTION_DETAILS B
                                                                            ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                                                   WHERE  PROJECTION_MASTER_SID = @TO_PROJECTION) B
                                                               ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                                      UNION
                                                      SELECT NEW_PROJECTION_DETAILS_SID
                                                      FROM   #TEMP_TO_CCP);

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
                       ISNULL(CHECK_RECORD, 0)
                FROM   #TEMP_MASTER_TO A
                WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
                                                      FROM   NM_SALES_PROJECTION_MASTER B
                                                      WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID);

            ;
                WITH TEMP_TO_PROJECTION_ACTUAL
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.PERIOD_SID,
                                B.ACTUAL_SALES,
                                B.ACTUAL_UNITS,
                                B.HISTORY_PROJECTION_SALES,
                                B.HISTORY_PROJECTION_UNITS
                         FROM   PROJECTION_DETAILS A
                                LEFT JOIN (SELECT B.CCP_DETAILS_SID,
                                                  A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                                  A.PERIOD_SID,
                                                  A.ACTUAL_SALES,
                                                  A.ACTUAL_UNITS,
                                                  A.HISTORY_PROJECTION_SALES,
                                                  A.HISTORY_PROJECTION_UNITS
                                           FROM   NM_ACTUAL_SALES A
                                                  JOIN NM_SALES_PROJECTION_MASTER A1
                                                    ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                                  JOIN PROJECTION_DETAILS B
                                                    ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                           WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION
                                                  AND B.CCP_DETAILS_SID NOT IN (SELECT NEW_CCP
                                                                                FROM   #TEMP_TO_CCP)
                                           UNION
                                           SELECT E.NEW_CCP,
                                                  E.NEW_PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                                  E.PERIOD_SID,
                                                  0                            AS ACTUAL_SALES,
                                                  0                            AS ACTUAL_UNITS,
                                                  0                            AS HISTORY_PROJECTION_SALES,
                                                  0                            AS HISTORY_PROJECTION_UNITS
                                           FROM   #TEMP_ACTUALS A
                                                  JOIN CCP_DETAILS C
                                                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                  RIGHT JOIN (SELECT PERIOD_SID,
                                                                     D.NEW_CCP,
                                                                     D.NEW_PROJECTION_DETAILS_SID,
                                                                     D.OLD_CCP
                                                              FROM   PERIOD A
                                                                     JOIN #TEMP_TO_CCP D
                                                                       ON 1 = 1
                                                              WHERE  PERIOD_SID BETWEEN @ACTUAL_START AND @ACTUAL_END) E
                                                          ON E.PERIOD_SID = A.PERIOD_SID
                                                             AND A.CCP_DETAILS_SID = E.OLD_CCP
                                           UNION
                                           SELECT CCP_DETAILS_SID,
                                                  PROJECTION_DETAILS_SID,
                                                  A.PERIOD_SID,
                                                  0 AS ACTUAL_SALES,
                                                  0 AS ACTUAL_UNITS,
                                                  0 AS HISTORY_PROJECTION_SALES,
                                                  0 AS HISTORY_PROJECTION_UNITS
                                           FROM   #TEMP_MASTER_TO_EXCLUDED
                                                  CROSS JOIN (SELECT PERIOD_SID
                                                              FROM   PERIOD
                                                              WHERE  PERIOD_SID BETWEEN @ACTUAL_START AND @ACTUAL_END) A) B
                                       ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)
                INSERT INTO NM_ACTUAL_SALES
                            (PROJECTION_DETAILS_SID,
                             PERIOD_SID,
                             ACTUAL_SALES,
                             ACTUAL_UNITS,
                             HISTORY_PROJECTION_SALES,
                             HISTORY_PROJECTION_UNITS)
                SELECT PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS,
                       HISTORY_PROJECTION_SALES,
                       HISTORY_PROJECTION_UNITS
                FROM   TEMP_TO_PROJECTION_ACTUAL A
                WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
                                                      FROM   NM_ACTUAL_SALES B
                                                      WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                                             AND A.PERIOD_SID = B.PERIOD_SID);

            ;
                WITH TEMP_TO_PROJECTION_PROJECTION
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
                                LEFT JOIN (SELECT B.CCP_DETAILS_SID,
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
                                           FROM   NM_SALES_PROJECTION A
                                                  JOIN NM_SALES_PROJECTION_MASTER A1
                                                    ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                                  JOIN PROJECTION_DETAILS B
                                                    ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                           WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION
                                                  AND B.CCP_DETAILS_SID NOT IN (SELECT NEW_CCP
                                                                                FROM   #TEMP_TO_CCP)
                                           UNION
                                           SELECT E.NEW_CCP,
                                                  A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                                  A.ACCOUNT_GROWTH,
                                                  A.PRODUCT_GROWTH,
                                                  A.PROJECTION_SALES,
                                                  A.PROJECTION_UNITS,
                                                  E.PERIOD_SID,
                                                  A.ADJUSTMENT_TYPE,
                                                  A.ADJUSTMENT_BASIS,
                                                  A.ADJUSTMENT_VARIABLE,
                                                  A.ADJUSTMENT_METHODOLOGY,
                                                  A.ADJUSTMENT_VALUES
                                           FROM   (SELECT PROJECTION_DETAILS_SID,
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
                                                   WHERE  PERIOD_SID >= @CUST_PERIOD_START) A
                                                  JOIN CCP_DETAILS C
                                                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                  RIGHT JOIN (SELECT PERIOD_SID,
                                                                     D.NEW_CCP,
                                                                     D.OLD_CCP
                                                              FROM   PERIOD A
                                                                     JOIN #TEMP_TO_CCP D
                                                                       ON 1 = 1
                                                              WHERE  PERIOD_DATE BETWEEN @FORECAST_TO_START_DATE AND @FORECAST_TO_END_DATE) E
                                                          ON E.PERIOD_SID = A.PERIOD_SID
                                                             AND E.OLD_CCP = A.CCP_DETAILS_SID
                                           UNION
                                           SELECT CCP_DETAILS_SID,
                                                  PROJECTION_DETAILS_SID,
                                                  NULL AS ACCOUNT_GROWTH,
                                                  NULL AS PRODUCT_GROWTH,
                                                  NULL AS PROJECTION_SALES,
                                                  NULL AS PROJECTION_UNITS,
                                                  A.PERIOD_SID,
                                                  NULL AS ADJUSTMENT_TYPE,
                                                  NULL AS ADJUSTMENT_BASIS,
                                                  NULL AS ADJUSTMENT_VARIABLE,
                                                  NULL AS ADJUSTMENT_METHODOLOGY,
                                                  NULL AS ADJUSTMENT_VALUES
                                           FROM   #TEMP_MASTER_TO_EXCLUDED
                                                  CROSS JOIN (SELECT PERIOD_SID
                                                              FROM   PERIOD
                                                              WHERE  PERIOD_DATE BETWEEN @FORECAST_TO_START_DATE AND @FORECAST_TO_END_DATE) A) B
                                       ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)
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
          ELSE IF ( @TO_FORECAST_TYPE = 'Mandated' )
            BEGIN ;
                SELECT @ACTUAL_START = Min(PERIOD_SID),
                       @ACTUAL_END = Max(PERIOD_SID)
                FROM   M_ACTUAL_SALES A
                       JOIN PROJECTION_DETAILS B
                         ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION;

                WITH TEMP_TO_PROJECTION_MASTER
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.METHODOLOGY,
                                B.CALCULATION_PERIODS,
                                B.CALCULATION_BASED,
                                B.CHECK_RECORD
                         FROM   PROJECTION_DETAILS A
                                LEFT JOIN (SELECT B.CCP_DETAILS_SID,
                                                  A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                                  METHODOLOGY,
                                                  CALCULATION_PERIODS,
                                                  CALCULATION_BASED,
                                                  CHECK_RECORD
                                           FROM   M_SALES_PROJECTION_MASTER A
                                                  JOIN PROJECTION_DETAILS B
                                                    ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                           WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION
                                                  AND B.CCP_DETAILS_SID NOT IN (SELECT NEW_CCP
                                                                                FROM   #TEMP_TO_CCP)
                                           UNION
                                           SELECT D.NEW_CCP,
                                                  A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                                  METHODOLOGY,
                                                  CALCULATION_PERIODS,
                                                  CALCULATION_BASED,
                                                  CHECK_RECORD
                                           FROM   #TEMP_MASTER A
                                                  JOIN CCP_DETAILS C
                                                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                  JOIN #TEMP_TO_CCP D
                                                    ON D.OLD_CCP = C.CCP_DETAILS_SID
                                           WHERE  CONTRACT_MASTER_SID = @FROM_CONTRACT_MASTER_SID
                                                  AND C.COMPANY_MASTER_SID IN (SELECT COMPANY_MASTER_SID
                                                                               FROM   #TEMP_CUSTOMER)
                                                  AND C.ITEM_MASTER_SID IN (SELECT ITEM_MASTER_SID
                                                                            FROM   #TEMP_ITEM)) B
                                       ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
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

                INSERT INTO #TEMP_MASTER_TO_EXCLUDED
                            (PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID)
                SELECT PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID
                FROM   #TEMP_MASTER_TO
                WHERE  PROJECTION_DETAILS_SID NOT IN (SELECT A.PROJECTION_DETAILS_SID
                                                      FROM   #TEMP_MASTER_TO A
                                                             JOIN (SELECT B.CCP_DETAILS_SID
                                                                   FROM   M_SALES_PROJECTION_MASTER A
                                                                          JOIN PROJECTION_DETAILS B
                                                                            ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                                                   WHERE  PROJECTION_MASTER_SID = @TO_PROJECTION) B
                                                               ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                                      UNION
                                                      SELECT NEW_PROJECTION_DETAILS_SID
                                                      FROM   #TEMP_TO_CCP);

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
                WHERE  A.PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
                                                        FROM   M_SALES_PROJECTION_MASTER B
                                                        WHERE  B.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID);

                WITH TEMP_TO_PROJECTION_ACTUAL
                     AS (SELECT A.PROJECTION_DETAILS_SID,
                                A.CCP_DETAILS_SID,
                                B.PERIOD_SID,
                                B.ACTUAL_SALES,
                                B.ACTUAL_UNITS,
                                B.ACTUAL_PROJECTION_SALES,
                                B.ACTUAL_PROJECTION_UNITS
                         FROM   PROJECTION_DETAILS A
                                LEFT JOIN (SELECT B.CCP_DETAILS_SID,
                                                  A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                                  A.PERIOD_SID,
                                                  A.ACTUAL_SALES,
                                                  A.ACTUAL_UNITS,
                                                  A.ACTUAL_PROJECTION_SALES,
                                                  A.ACTUAL_PROJECTION_UNITS
                                           FROM   M_ACTUAL_SALES A
                                                  JOIN M_SALES_PROJECTION_MASTER A1
                                                    ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                                  JOIN PROJECTION_DETAILS B
                                                    ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                           WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION
                                                  AND B.CCP_DETAILS_SID NOT IN (SELECT NEW_CCP
                                                                                FROM   #TEMP_TO_CCP)
                                           UNION
                                           SELECT D.NEW_CCP,
                                                  A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                                  E.PERIOD_SID,
                                                  0                        AS ACTUAL_SALES,
                                                  0                        AS ACTUAL_UNITS,
                                                  0                        AS HISTORY_PROJECTION_SALES,
                                                  0                        AS HISTORY_PROJECTION_UNITS
                                           FROM   #TEMP_ACTUALS A
                                                  JOIN CCP_DETAILS C
                                                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                  JOIN #TEMP_TO_CCP D
                                                    ON D.OLD_CCP = C.CCP_DETAILS_SID
                                                  RIGHT JOIN (SELECT PERIOD_SID
                                                              FROM   PERIOD
                                                              WHERE  PERIOD_SID BETWEEN @ACTUAL_START AND @ACTUAL_END) E
                                                          ON E.PERIOD_SID = A.PERIOD_SID
                                           UNION
                                           SELECT CCP_DETAILS_SID,
                                                  PROJECTION_DETAILS_SID,
                                                  A.PERIOD_SID,
                                                  0 AS ACTUAL_SALES,
                                                  0 AS ACTUAL_UNITS,
                                                  0 AS ACTUAL_PROJECTION_SALES,
                                                  0 AS ACTUAL_PROJECTION_UNITS
                                           FROM   #TEMP_MASTER_TO_EXCLUDED
                                                  CROSS JOIN (SELECT PERIOD_SID
                                                              FROM   PERIOD
                                                              WHERE  PERIOD_SID BETWEEN @ACTUAL_START AND @ACTUAL_END) A) B
                                       ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)
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
                       ACTUAL_PROJECTION_SALES,
                       ACTUAL_PROJECTION_UNITS
                FROM   TEMP_TO_PROJECTION_ACTUAL A
                WHERE  A.PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
                                                        FROM   M_ACTUAL_SALES B
                                                        WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                                               AND A.PERIOD_SID = B.PERIOD_SID);

            ;
                WITH TEMP_TO_PROJECTION_PROJECTION
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
                                LEFT JOIN (SELECT B.CCP_DETAILS_SID,
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
                                                  JOIN M_SALES_PROJECTION_MASTER A1
                                                    ON A1.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                                  JOIN PROJECTION_DETAILS B
                                                    ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                           WHERE  B.PROJECTION_MASTER_SID = @TO_PROJECTION
                                                  AND B.CCP_DETAILS_SID NOT IN (SELECT NEW_CCP
                                                                                FROM   #TEMP_TO_CCP)
                                           UNION
                                           SELECT E.NEW_CCP,
                                                  A.PROJECTION_DETAILS_SID AS OLD_PROJECTION_DETAILS_SID,
                                                  A.ACCOUNT_GROWTH,
                                                  A.PRODUCT_GROWTH,
                                                  A.PROJECTION_SALES,
                                                  A.PROJECTION_UNITS,
                                                  E.PERIOD_SID,
                                                  A.ADJUSTMENT_TYPE,
                                                  A.ADJUSTMENT_BASIS,
                                                  A.ADJUSTMENT_VARIABLE,
                                                  A.ADJUSTMENT_METHODOLOGY,
                                                  A.ADJUSTMENT_VALUES
                                           FROM   (SELECT PROJECTION_DETAILS_SID,
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
                                                   WHERE  PERIOD_SID >= @CUST_PERIOD_START) A
                                                  JOIN CCP_DETAILS C
                                                    ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                  RIGHT JOIN (SELECT PERIOD_SID,
                                                                     D.NEW_CCP,
                                                                     D.OLD_CCP
                                                              FROM   PERIOD A
                                                                     JOIN #TEMP_TO_CCP D
                                                                       ON 1 = 1
                                                              WHERE  PERIOD_DATE BETWEEN @FORECAST_TO_START_DATE AND @FORECAST_TO_END_DATE) E
                                                          ON E.PERIOD_SID = A.PERIOD_SID
                                                             AND E.OLD_CCP = A.CCP_DETAILS_SID
                                           UNION
                                           SELECT CCP_DETAILS_SID,
                                                  PROJECTION_DETAILS_SID,
                                                  0 AS ACCOUNT_GROWTH,
                                                  0 AS PRODUCT_GROWTH,
                                                  0 AS PROJECTION_SALES,
                                                  0 AS PROJECTION_UNITS,
                                                  A.PERIOD_SID,
                                                  0 AS ADJUSTMENT_TYPE,
                                                  0 AS ADJUSTMENT_BASIS,
                                                  0 AS ADJUSTMENT_VARIABLE,
                                                  0 AS ADJUSTMENT_METHODOLOGY,
                                                  0 AS ADJUSTMENT_VALUES
                                           FROM   #TEMP_MASTER_TO_EXCLUDED
                                                  CROSS JOIN (SELECT PERIOD_SID
                                                              FROM   PERIOD
                                                              WHERE  PERIOD_DATE BETWEEN @FORECAST_TO_START_DATE AND @FORECAST_TO_END_DATE) A) B
                                       ON B.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                         WHERE  PROJECTION_MASTER_SID = @COPY_TO_PROJECTION)
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
                WHERE  A.PROJECTION_DETAILS_SID NOT IN (SELECT B.PROJECTION_DETAILS_SID
                                                        FROM   M_SALES_PROJECTION B
                                                        WHERE  A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                                                               AND A.PERIOD_SID = B.PERIOD_SID);
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
