IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NEW_NDC_POPUP'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NEW_NDC_POPUP]
  END

GO

CREATE PROCEDURE [dbo].[PRC_NEW_NDC_POPUP](@NA_PROJ_MASTER_SID INT)
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
	  DECLARE @BUSINESS_UNIT INT --------------------GAL-808
	  DECLARE @COMPANY_ID INT --------------------GAL-808
	   
          IF OBJECT_ID('TEMPDB..#NEWNDC_POPUP') IS NOT NULL
            TRUNCATE TABLE #NEWNDC_POPUP
          ELSE
            CREATE TABLE #NEWNDC_POPUP
              (
                 ITEM_MASTER_SID INT,
                 ITEM_NO         VARCHAR(50),
                 ITEM_DESC       VARCHAR(100),
                 NDC9            VARCHAR(20),
                 AMP             NUMERIC(22, 6),
                 CPI             NUMERIC(22, 6),
                 WAC             NUMERIC(22, 6),
                 [NON-FAMP]      NUMERIC(22, 6),
                 [FSS(OGA)]      NUMERIC(22, 6),
                 WORKSHEET       VARCHAR(30)
              )

          DECLARE @FORECAST_NAME VARCHAR(50),
                  @VERSION       VARCHAR(15)
		  --------------------GAL-808-------------------------
		  SELECT @BUSINESS_UNIT=BUSINESS_UNIT,
		  @COMPANY_ID=NA.COMPANY_MASTER_SID
		  FROM NA_PROJ_MASTER NA
		  WHERE NA_PROJ_MASTER_SID=@NA_PROJ_MASTER_SID

		  -------------------------------------------------------------------

          SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME,
                       @VERSION = FT.[VERSION]
          FROM   FILE_MANAGEMENT FT
                 INNER JOIN HELPER_TABLE HT
                         ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
          WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE())
                   AND FT.FROM_PERIOD IS NOT NULL )
                 AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE())
                        OR FT.TO_PERIOD IS NULL )
                 AND HT.LIST_NAME = 'FILE_TYPE'
                 AND HT.DESCRIPTION = 'EX-FACTORY SALES'
				 AND FT.BUSINESS_UNIT=@BUSINESS_UNIT --------------------GAL-808
				 AND FT.COMPANY=@COMPANY_ID --------------------GAL-808
          ORDER  BY FT.FROM_PERIOD DESC;


          WITH FM_CTE
               AS (SELECT NDC9,
                          CASE
                            WHEN FORECAST_MONTH IN ( 1, 2, 3 ) THEN 1
                            WHEN FORECAST_MONTH IN ( 4, 5, 6 ) THEN 2
                            WHEN FORECAST_MONTH IN ( 7, 8, 9 ) THEN 3
                            WHEN FORECAST_MONTH IN ( 10, 11, 12 ) THEN 4
                          END AS FORECAST_QUART,
                          FORECAST_YEAR,
                          SUM(DOLLARS) / NULLIF(SUM(UNITS), 0) AS WAC,
                          CASE
                                      WHEN COUNT(DISTINCT ITEM_MASTER_SID) > 1 THEN 'MULTIPLE'
                                      ELSE (SELECT TOP 1 ITEM_DESC
                                            FROM   ITEM_MASTER I
                                            WHERE  IM.NDC9 = I.NDC9 order by ITEM_MASTER_SID)
                                    END AS ITEM_DESC
                   FROM   ITEM_MASTER IM
                          LEFT OUTER JOIN FORECASTING_MASTER FM
                                       ON FM.NDC = IM.ITEM_ID
                                          AND FM.FORECAST_NAME = @FORECAST_NAME
                                          AND FM.FORECAST_VER IN ( @VERSION, FLOOR(@VERSION) )
                   WHERE  EXISTS (SELECT 1
                                  FROM   NA_PROJ_DETAILS NPD
                                  WHERE  NPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                         AND NPD.NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID)
                          AND NOT EXISTS (SELECT 1
                                          FROM   DBO.[MEDICAID_NEW_NDC] A
                                          WHERE  IM.NDC9 = A.NDC9)
                          AND 2 <> (SELECT COUNT(DISTINCT IPQ.PRICING_QUALIFIER)
                                    FROM   ITEM_PRICING IP
                                           INNER JOIN ITEM_PRICING_QUALIFIER IPQ
                                                   ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
												   join helper_table h on h.HELPER_TABLE_SID = ip.ITEM_UOM
                                    WHERE  IP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                           AND IPQ.PRICING_QUALIFIER IN ( 'AMP', 'BP' )
										   and H.DESCRIPTION = 'UN')
                   GROUP  BY NDC9,
                             CASE
                               WHEN FORECAST_MONTH IN ( 1, 2, 3 ) THEN 1
                               WHEN FORECAST_MONTH IN ( 4, 5, 6 ) THEN 2
                               WHEN FORECAST_MONTH IN ( 7, 8, 9 ) THEN 3
                               WHEN FORECAST_MONTH IN ( 10, 11, 12 ) THEN 4
                             END,
                             FORECAST_YEAR),
               ORDER_CTE
               AS (SELECT ROW_NUMBER()
                               OVER(
                                 PARTITION BY NDC9
                                 ORDER BY FORECAST_YEAR, FORECAST_QUART) AS RN,
                          NDC9,
                          ITEM_DESC,
                          WAC,
                          FORECAST_YEAR,
                          FORECAST_QUART
                   FROM   FM_CTE),
               CPI
               AS (SELECT CASE
                            WHEN DATEPART(QQ, EFFECTIVE_DATE) = 4 THEN 1
                            ELSE DATEPART(QQ, EFFECTIVE_DATE) + 1
                          END                                 QUART,
                          CASE
                            WHEN DATEPART(QQ, EFFECTIVE_DATE) = 4 THEN DATEPART(YYYY, EFFECTIVE_DATE) + 1
                            ELSE DATEPART(YYYY, EFFECTIVE_DATE)
                          END                                 YR,
                          DENSE_RANK()
                            OVER(
                              PARTITION BY YEAR(EFFECTIVE_DATE), DATEPART(QQ, EFFECTIVE_DATE)
                              ORDER BY MONTH(EFFECTIVE_DATE)) AS RN,
                          INDEX_VALUE,
                          EFFECTIVE_DATE
                   FROM   CPI_INDEX_MASTER
                   WHERE  INDEX_TYPE = 'CPI')
          INSERT INTO #NEWNDC_POPUP
                      (ITEM_MASTER_SID,
                       ITEM_NO,
                       NDC9,
                       ITEM_DESC,
                       WAC,
                       AMP,
                       CPI,
                       WORKSHEET)
          SELECT 0,
                 0,
                 ISNULL(NDC9, 0) NDC9,
                 ITEM_DESC,
                 WAC,
                 ISNULL(( 98.1 / 100 ) * NULLIF(WAC, 0), 0) AS AMP,
                 INDEX_VALUE AS CPI,
                 'MEDICAID FSS' AS WORKSHEET
          FROM   ORDER_CTE OC
                 LEFT JOIN (SELECT *
                            FROM   CPI
                            WHERE  RN = 3) CPI
                        ON OC.FORECAST_QUART = CPI.QUART
                           AND OC.FORECAST_YEAR = CPI.YR
          WHERE  OC.RN = 1;

          WITH FM_CTE
               AS (SELECT ITEM_MASTER_SID,
                          ITEM_NO,
                          ITEM_DESC,
                          CASE
                            WHEN FORECAST_MONTH IN ( 1, 2, 3 ) THEN 1
                            WHEN FORECAST_MONTH IN ( 4, 5, 6 ) THEN 2
                            WHEN FORECAST_MONTH IN ( 7, 8, 9 ) THEN 3
                            WHEN FORECAST_MONTH IN ( 10, 11, 12 ) THEN 4
                          END AS FORECAST_QUART,
                          FORECAST_YEAR,
                          NDC9,
                          SUM(DOLLARS) / NULLIF(SUM(UNITS), 0) AS WAC
                   FROM   ITEM_MASTER IM
                          LEFT OUTER JOIN FORECASTING_MASTER FM
                                       ON FM.NDC = IM.ITEM_ID
                                          AND FM.FORECAST_NAME = @FORECAST_NAME
                                          AND FM.FORECAST_VER IN ( @VERSION, FLOOR(@VERSION) )
                   WHERE  EXISTS (SELECT 1
                                  FROM   NA_PROJ_DETAILS NPD
                                  WHERE  NPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                         AND NPD.NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID)
                          AND NOT EXISTS (SELECT 1
                                          FROM   [DBO].[FEDERAL_NEW_NDC] A
                                          WHERE  IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID)
                          AND 2 <> (SELECT COUNT(DISTINCT IPQ.PRICING_QUALIFIER)
                                    FROM   ITEM_PRICING IP
                                           INNER JOIN ITEM_PRICING_QUALIFIER IPQ
                                                   ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
												    join helper_table h on h.HELPER_TABLE_SID = ip.ITEM_UOM
                                    WHERE  IP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                           AND IPQ.PRICING_QUALIFIER IN ( 'QNON-FAMP', 'QFSS' )
										   and h.DESCRIPTION = 'UN' )
                   GROUP  BY ITEM_MASTER_SID,
                             ITEM_NO,
                             ITEM_DESC,
                             CASE
                               WHEN FORECAST_MONTH IN ( 1, 2, 3 ) THEN 1
                               WHEN FORECAST_MONTH IN ( 4, 5, 6 ) THEN 2
                               WHEN FORECAST_MONTH IN ( 7, 8, 9 ) THEN 3
                               WHEN FORECAST_MONTH IN ( 10, 11, 12 ) THEN 4
                             END,
                             FORECAST_YEAR,
                             NDC9),
               ORDER_CTE
               AS (SELECT ROW_NUMBER()
                               OVER(
                                 PARTITION BY ITEM_MASTER_SID
                                 ORDER BY FORECAST_YEAR, FORECAST_QUART) AS RN,
                          ITEM_MASTER_SID,
                          ITEM_NO,
                          ITEM_DESC,
                          NDC9,
                          WAC
                   FROM   FM_CTE)
          INSERT INTO #NEWNDC_POPUP
                      (ITEM_MASTER_SID,
                       ITEM_NO,
                       ITEM_DESC,
                       NDC9,
                       WAC,
                       [NON-FAMP],
                       [FSS(OGA)],
                       WORKSHEET)
          SELECT ITEM_MASTER_SID,
                 ITEM_NO,
                 ITEM_DESC,
                 NDC9,
                 WAC,
                 ISNULL((( 96 / 100.0 )) * NULLIF(WAC, 0), 0) aS [NON-FAMP],
                 ISNULL((( 76 / 100.0 )) * NULLIF(WAC, 0), 0) AS [FSS(OGA)], 
                 'FEDERAL' aS WORKSHEET
          FROM   ORDER_CTE
          WHERE  RN = 1

          SELECT ITEM_MASTER_SID,
                       ITEM_NO,
                       ITEM_DESC,
                       NDC9,
                       WAC,
                       [NON-FAMP],
                       [FSS(OGA)],
                       WORKSHEET
          FROM   #NEWNDC_POPUP
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

