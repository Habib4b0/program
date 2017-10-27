IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_M_PROJ_RSLTS_CNTRCT_VIEW'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_M_PROJ_RSLTS_CNTRCT_VIEW]
  END

GO

CREATE PROCEDURE [dbo].[PRC_M_PROJ_RSLTS_CNTRCT_VIEW] (@PROJECTION_MASTER_SID INT,
                                                      @FREQUENCY             VARCHAR(50),
                                                      @SCREEN_NAME           VARCHAR(20),
                                                      @ACTUAL_START_FREQ     INT,
                                                      @ACTUAL_START_YEAR     INT)
AS
  BEGIN
    SET NOCOUNT ON
      DECLARE @ACTUAL_START_PERIOD INT
      DECLARE @ACTUAL_END_PERIOD INT
      DECLARE @PROJECTION_DATE       DATETIME,
              @STARTFROM             DATETIME,
              @START_DATE            DATETIME,
              @PROJ_START_PERIOD_SID INT

      SELECT TOP 1 @STARTFROM = DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, -3, GETDATE())), 0),
                   @START_DATE = DATEADD(DD, 1, EOMONTH(FROM_DATE, -1)),
                   @PROJECTION_DATE = DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
      FROM   PROJECTION_MASTER
      WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
	  ORDER BY PROJECTION_MASTER_SID

      SELECT @PROJ_START_PERIOD_SID = MAX(CASE
                                            WHEN PERIOD_DATE = @START_DATE THEN PERIOD_SID
                                          END)
      FROM   PERIOD
      WHERE  PERIOD_DATE IN ( @STARTFROM, @PROJECTION_DATE, @START_DATE )

      IF @SCREEN_NAME = 'PROJECTION RESULTS'
        BEGIN
            IF @FREQUENCY = 'MONTHLY'
              BEGIN
                  SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                  FROM   PERIOD
                  WHERE  MONTH = @ACTUAL_START_FREQ
                         AND YEAR = @ACTUAL_START_YEAR

                  SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                  FROM   PERIOD
                  WHERE  PERIOD_DATE = DATEADD(MM, DATEDIFF(MM, 0, @PROJECTION_DATE), 0)

                  SELECT @PROJECTION_MASTER_SID AS PROJECTION_ID,
                         [MONTH],
                         [YEAR],
                         CM.CONTRACT_ID,
                         CM.CONTRACT_NO,
                         CM.CONTRACT_NAME,
                         ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                         ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                         ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                         ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                         ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                         ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                         ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                         ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                         ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                         ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                         ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                         ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                         ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUAL,
                         ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_PROPJECTED,
                         ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUAL,
                         ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROPJECTED
                  FROM   (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                 SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                 SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                                 SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                 COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID,
                                 COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID) AS CONTRACT_MASTER_SID 
                          FROM   (SELECT NAS.PROJECTION_DETAILS_SID,
                                         PERIOD_SID,
                                         CCP.CONTRACT_MASTER_SID,
                                         ACTUAL_SALES,
                                         ACTUAL_UNITS
                                  FROM   M_ACTUAL_SALES NAS
                                         INNER JOIN PROJECTION_DETAILS PD
                                                 ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                         INNER JOIN CCP_DETAILS CCP
                                                 ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                  WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                 ) ACT
                                 FULL JOIN (SELECT NPS.PROJECTION_DETAILS_SID,
                                                   PERIOD_SID,
                                                   CCP.CONTRACT_MASTER_SID,
                                                   PROJECTION_SALES,
                                                   PROJECTION_UNITS
                                            FROM   M_SALES_PROJECTION NPS
                                                   INNER JOIN PROJECTION_DETAILS PD
                                                           ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                   INNER JOIN CCP_DETAILS CCP
                                                           ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                            WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                                   AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                        ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                           AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           AND ACT.CONTRACT_MASTER_SID = PROJ.CONTRACT_MASTER_SID
                          GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID),
                                    COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID)) SALES
                         INNER JOIN PERIOD P
                                 ON P.PERIOD_SID = SALES.PERIOD_SID
                         INNER JOIN CONTRACT_MASTER CM
                                 ON CM.CONTRACT_MASTER_SID = SALES.CONTRACT_MASTER_SID
                         LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                           SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                           COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID
                                    FROM   (SELECT PROJECTION_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   M_ACTUAL_DISCOUNT NAS
                                            WHERE  EXISTS (SELECT 1
                                                           FROM   PROJECTION_DETAILS PD
                                                           WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                 ) ACT
                                           FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   M_DISCOUNT_PROJECTION NPS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                             AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                                  ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                    GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) DISCOUNT
                                ON DISCOUNT.PERIOD_SID = SALES.PERIOD_SID
                         LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                           SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                           COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID
                                    FROM   (SELECT PROJECTION_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                            WHERE  EXISTS (SELECT 1
                                                           FROM   PROJECTION_DETAILS PD
                                                           WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                           ) ACT
                                           FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                     ) PROJ
                                                  ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                    GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SUPPLEMENTAL
                                ON SUPPLEMENTAL.PERIOD_SID = DISCOUNT.PERIOD_SID
                  WHERE  P.PERIOD_SID BETWEEN ISNULL(@ACTUAL_START_PERIOD, 1) AND @ACTUAL_END_PERIOD
              END
            ELSE IF @FREQUENCY = 'QUARTERLY'
              BEGIN
                  SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                  FROM   PERIOD
                  WHERE  QUARTER = @ACTUAL_START_FREQ
                         AND YEAR = @ACTUAL_START_YEAR

                  SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                  FROM   PERIOD
                  WHERE  PERIOD_DATE = DATEADD(MM, DATEDIFF(MM, 0, @PROJECTION_DATE), 0)

                  SELECT @PROJECTION_MASTER_SID AS PROJECTION_ID,
                         SALES.[QUARTER],
                         SALES.[YEAR],
                         CM.CONTRACT_ID,
                         CM.CONTRACT_NO,
                         CM.CONTRACT_NAME,
                         ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                         ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                         ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                         ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                         ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                         ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                         ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                         ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                         ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                         ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                         ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                         ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                         ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUAL,
                         ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_PROPJECTED,
                         ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUAL,
                         ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROPJECTED
                  FROM   (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                 SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                 SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                                 SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                 COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID) AS CONTRACT_MASTER_SID ,
                                 [QUARTER],
                                 [YEAR]
                          FROM   (SELECT NAS.PROJECTION_DETAILS_SID,
                                         PERIOD_SID,
                                         CCP.CONTRACT_MASTER_SID,
                                         ACTUAL_SALES,
                                         ACTUAL_UNITS
                                  FROM   M_ACTUAL_SALES NAS
                                         INNER JOIN PROJECTION_DETAILS PD
                                                 ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                         INNER JOIN CCP_DETAILS CCP
                                                 ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                  WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                 ) ACT
                                 FULL JOIN (SELECT NPS.PROJECTION_DETAILS_SID,
                                                   PERIOD_SID,
                                                   CCP.CONTRACT_MASTER_SID,
                                                   PROJECTION_SALES,
                                                   PROJECTION_UNITS
                                            FROM   M_SALES_PROJECTION NPS
                                                   INNER JOIN PROJECTION_DETAILS PD
                                                           ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                   INNER JOIN CCP_DETAILS CCP
                                                           ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                            WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                                   AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                        ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                           AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                 INNER JOIN PERIOD P
                                         ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                          WHERE  P.PERIOD_SID BETWEEN ISNULL(@ACTUAL_START_PERIOD, 1) AND @ACTUAL_END_PERIOD
                          GROUP  BY COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID),
                                    [QUARTER],
                                    [YEAR]) SALES
                         INNER JOIN CONTRACT_MASTER CM
                                 ON CM.CONTRACT_MASTER_SID = SALES.CONTRACT_MASTER_SID
                         LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                           SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                           [QUARTER],
                                           [YEAR]
                                    FROM   (SELECT PROJECTION_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   M_ACTUAL_DISCOUNT NAS
                                            WHERE  EXISTS (SELECT 1
                                                           FROM   PROJECTION_DETAILS PD
                                                           WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                           FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   M_DISCOUNT_PROJECTION NPS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                             AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                                  ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           INNER JOIN PERIOD P
                                                   ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    GROUP  BY [QUARTER],
                                              [YEAR]) DISCOUNT
                                ON DISCOUNT.[QUARTER] = SALES.[QUARTER]
                                   AND DISCOUNT.[YEAR] = SALES.[YEAR]
                         LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                           SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                           [QUARTER],
                                           [YEAR]
                                    FROM   (SELECT PROJECTION_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                            WHERE  EXISTS (SELECT 1
                                                           FROM   PROJECTION_DETAILS PD
                                                           WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                           ) ACT
                                           FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                     ) PROJ
                                                  ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           INNER JOIN PERIOD P
                                                   ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    GROUP  BY [QUARTER],
                                              [YEAR]) SUPPLEMENTAL
                                ON SUPPLEMENTAL.[QUARTER] = SALES.[QUARTER]
                                   AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
              END
            ELSE IF @FREQUENCY = 'SEMI-ANNUALLY'
              BEGIN
                  SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                  FROM   PERIOD
                  WHERE  SEMI_ANNUAL = @ACTUAL_START_FREQ
                         AND YEAR = @ACTUAL_START_YEAR

                  SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                  FROM   PERIOD
                  WHERE  PERIOD_DATE = DATEADD(MM, DATEDIFF(MM, 0, @PROJECTION_DATE), 0)

                  SELECT @PROJECTION_MASTER_SID AS PROJECTION_ID,
                         SALES.[SEMI_ANNUAL],
                         SALES.[YEAR],
                         CM.CONTRACT_ID,
                         CM.CONTRACT_NO,
                         CM.CONTRACT_NAME,
                         ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                         ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                         ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                         ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                         ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                         ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                         ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                         ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                         ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                         ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                         ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                         ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                         ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUAL,
                         ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_PROPJECTED,
                         ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUAL,
                         ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROPJECTED
                  FROM   (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                 SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                 SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                                 SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                 SEMI_ANNUAL,
                                 [YEAR],
                                 COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID) AS CONTRACT_MASTER_SID 
                          FROM   (SELECT NAS.PROJECTION_DETAILS_SID,
                                         PERIOD_SID,
                                         CCP.CONTRACT_MASTER_SID,
                                         ACTUAL_SALES,
                                         ACTUAL_UNITS
                                  FROM   M_ACTUAL_SALES NAS
                                         INNER JOIN PROJECTION_DETAILS PD
                                                 ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                         INNER JOIN CCP_DETAILS CCP
                                                 ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                  WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                 ) ACT
                                 FULL JOIN (SELECT NPS.PROJECTION_DETAILS_SID,
                                                   PERIOD_SID,
                                                   CCP.CONTRACT_MASTER_SID,
                                                   PROJECTION_SALES,
                                                   PROJECTION_UNITS
                                            FROM   M_SALES_PROJECTION NPS
                                                   INNER JOIN PROJECTION_DETAILS PD
                                                           ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                   INNER JOIN CCP_DETAILS CCP
                                                           ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                            WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                                   AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                        ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                           AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                 INNER JOIN PERIOD P
                                         ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                          WHERE  P.PERIOD_SID BETWEEN ISNULL(@ACTUAL_START_PERIOD, 1) AND @ACTUAL_END_PERIOD
                          GROUP  BY COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID),
                                    SEMI_ANNUAL,
                                    [YEAR]) SALES
                         INNER JOIN CONTRACT_MASTER CM
                                 ON CM.CONTRACT_MASTER_SID = SALES.CONTRACT_MASTER_SID
                         LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                           SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                           SEMI_ANNUAL,
                                           [YEAR]
                                    FROM   (SELECT NAS.PROJECTION_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   M_ACTUAL_DISCOUNT NAS
                                            WHERE  EXISTS (SELECT 1
                                                           FROM   PROJECTION_DETAILS PD
                                                           WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                           FULL JOIN (SELECT NPS.PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   M_DISCOUNT_PROJECTION NPS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                             AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                                  ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           INNER JOIN PERIOD P
                                                   ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    GROUP  BY SEMI_ANNUAL,
                                              [YEAR]) DISCOUNT
                                ON DISCOUNT.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                   AND DISCOUNT.[YEAR] = SALES.[YEAR]
                         LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                           SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                           SEMI_ANNUAL,
                                           [YEAR]
                                    FROM   (SELECT PROJECTION_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                            WHERE  EXISTS (SELECT 1
                                                           FROM   PROJECTION_DETAILS PD
                                                           WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                           ) ACT
                                           FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                     ) PROJ
                                                  ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           INNER JOIN PERIOD P
                                                   ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    GROUP  BY SEMI_ANNUAL,
                                              [YEAR]) SUPPLEMENTAL
                                ON SUPPLEMENTAL.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                   AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
              END
            ELSE
              BEGIN
                  SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                  FROM   PERIOD
                  WHERE  YEAR = @ACTUAL_START_YEAR

                  SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                  FROM   PERIOD
                  WHERE  PERIOD_DATE = DATEADD(MM, DATEDIFF(MM, 0, @PROJECTION_DATE), 0)

                  SELECT @PROJECTION_MASTER_SID AS PROJECTION_ID,
                         SALES.[YEAR],
                         CM.CONTRACT_ID,
                         CM.CONTRACT_NO,
                         CM.CONTRACT_NAME,
                         ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                         ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                         ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                         ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                         ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                         ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                         ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                         ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                         ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                         ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                         ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                         ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                         ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUAL,
                         ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_PROPJECTED,
                         ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUAL,
                         ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROPJECTED
                  FROM   (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                 SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                 SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                                 SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                 [YEAR],
                                 COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID) AS CONTRACT_MASTER_SID 
                          FROM   (SELECT NAS.PROJECTION_DETAILS_SID,
                                         PERIOD_SID,
                                         CCP.CONTRACT_MASTER_SID,
                                         ACTUAL_SALES,
                                         ACTUAL_UNITS
                                  FROM   M_ACTUAL_SALES NAS
                                         INNER JOIN PROJECTION_DETAILS PD
                                                 ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                         INNER JOIN CCP_DETAILS CCP
                                                 ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                  WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                 ) ACT
                                 FULL JOIN (SELECT NPS.PROJECTION_DETAILS_SID,
                                                   PERIOD_SID,
                                                   CCP.CONTRACT_MASTER_SID,
                                                   PROJECTION_SALES,
                                                   PROJECTION_UNITS
                                            FROM   M_SALES_PROJECTION NPS
                                                   INNER JOIN PROJECTION_DETAILS PD
                                                           ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                   INNER JOIN CCP_DETAILS CCP
                                                           ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                            WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                                   AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                        ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                           AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                 INNER JOIN PERIOD P
                                         ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                          WHERE  P.PERIOD_SID BETWEEN ISNULL(@ACTUAL_START_PERIOD, 1) AND @ACTUAL_END_PERIOD
                          GROUP  BY COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID),
                                    [YEAR]) SALES
                         INNER JOIN CONTRACT_MASTER CM
                                 ON CM.CONTRACT_MASTER_SID = SALES.CONTRACT_MASTER_SID
                         LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                           SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                           [YEAR]
                                    FROM   (SELECT PROJECTION_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   M_ACTUAL_DISCOUNT NAS
                                            WHERE  EXISTS (SELECT 1
                                                           FROM   PROJECTION_DETAILS PD
                                                           WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                              ) ACT
                                           FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   M_DISCOUNT_PROJECTION NPS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                             AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                                  ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           INNER JOIN PERIOD P
                                                   ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    GROUP  BY [YEAR]) DISCOUNT
                                ON DISCOUNT.[YEAR] = SALES.[YEAR]
                         LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                           SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                           [YEAR]
                                    FROM   (SELECT PROJECTION_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                            WHERE  EXISTS (SELECT 1
                                                           FROM   PROJECTION_DETAILS PD
                                                           WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                           ) ACT
                                           FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                     ) PROJ
                                                  ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           INNER JOIN PERIOD P
                                                   ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    GROUP  BY [YEAR]) SUPPLEMENTAL
                                ON SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
              END
        END
      ELSE
        BEGIN
            IF @FREQUENCY = 'MONTHLY'
              SELECT @PROJECTION_MASTER_SID AS PROJECTION_ID,
                     [MONTH],
                     [YEAR],
                     CM.CONTRACT_ID,
                     CM.CONTRACT_NO,
                     CM.CONTRACT_NAME,
                     ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                     ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                     ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                     ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                     ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                     ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                     ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                     ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                     ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                     ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                     ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                     ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                     ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUAL,
                     ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_PROPJECTED,
                     ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUAL,
                     ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROPJECTED
              FROM   (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                             SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                             SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                             SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                             COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID,
                             COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID) AS CONTRACT_MASTER_SID 
                      FROM   (SELECT NAS.PROJECTION_DETAILS_SID,
                                     PERIOD_SID,
                                     CCP.CONTRACT_MASTER_SID,
                                     ACTUAL_SALES,
                                     ACTUAL_UNITS
                              FROM   M_ACTUAL_SALES NAS
                                     INNER JOIN PROJECTION_DETAILS PD
                                             ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                     INNER JOIN CCP_DETAILS CCP
                                             ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                              WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID) ACT
                             FULL JOIN (SELECT NPS.PROJECTION_DETAILS_SID,
                                               PERIOD_SID,
                                               CCP.CONTRACT_MASTER_SID,
                                               PROJECTION_SALES,
                                               PROJECTION_UNITS
                                        FROM   M_SALES_PROJECTION NPS
                                               INNER JOIN PROJECTION_DETAILS PD
                                                       ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                               INNER JOIN CCP_DETAILS CCP
                                                       ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                        WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                               AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                    ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                       AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                       AND ACT.CONTRACT_MASTER_SID = PROJ.CONTRACT_MASTER_SID
                      GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID),
                                COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID)) SALES
                     INNER JOIN PERIOD P
                             ON P.PERIOD_SID = SALES.PERIOD_SID
                     INNER JOIN CONTRACT_MASTER CM
                             ON CM.CONTRACT_MASTER_SID = SALES.CONTRACT_MASTER_SID
                     LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                       SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                       COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID
                                FROM   (SELECT PROJECTION_DETAILS_SID,
                                               PERIOD_SID,
                                               ACTUAL_SALES
                                        FROM   M_ACTUAL_DISCOUNT NAS
                                        WHERE  EXISTS (SELECT 1
                                                       FROM   PROJECTION_DETAILS PD
                                                       WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                              AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                               AND NAS.SAVE_FLAG = 1) ACT
                                       FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                         PERIOD_SID,
                                                         PROJECTION_SALES
                                                  FROM   M_DISCOUNT_PROJECTION NPS
                                                  WHERE  EXISTS (SELECT 1
                                                                 FROM   PROJECTION_DETAILS PD
                                                                 WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                        AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                         AND NPS.SAVE_FLAG = 1
                                                         AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                              ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                 AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) DISCOUNT
                            ON DISCOUNT.PERIOD_SID = SALES.PERIOD_SID
                     LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                       SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                       COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID
                                FROM   (SELECT PROJECTION_DETAILS_SID,
                                               PERIOD_SID,
                                               ACTUAL_SALES
                                        FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                        WHERE  EXISTS (SELECT 1
                                                       FROM   PROJECTION_DETAILS PD
                                                       WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                              AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                       FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                         PERIOD_SID,
                                                         PROJECTION_SALES
                                                  FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                  WHERE  EXISTS (SELECT 1
                                                                 FROM   PROJECTION_DETAILS PD
                                                                 WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                        AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                         AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                              ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                 AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SUPPLEMENTAL
                            ON SUPPLEMENTAL.PERIOD_SID = DISCOUNT.PERIOD_SID
            ELSE IF @FREQUENCY = 'QUARTERLY'
              SELECT @PROJECTION_MASTER_SID AS PROJECTION_ID,
                     SALES.[QUARTER],
                     SALES.[YEAR],
                     CM.CONTRACT_ID,
                     CM.CONTRACT_NO,
                     CM.CONTRACT_NAME,
                     ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                     ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                     ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                     ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                     ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                     ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                     ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                     ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                     ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                     ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                     ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                     ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                     ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUAL,
                     ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_PROPJECTED,
                     ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUAL,
                     ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROPJECTED
              FROM   (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                             SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                             SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                             SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                             COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID) AS CONTRACT_MASTER_SID ,
                             [QUARTER],
                             [YEAR]
                      FROM   (SELECT NAS.PROJECTION_DETAILS_SID,
                                     PERIOD_SID,
                                     CCP.CONTRACT_MASTER_SID,
                                     ACTUAL_SALES,
                                     ACTUAL_UNITS
                              FROM   M_ACTUAL_SALES NAS
                                     INNER JOIN PROJECTION_DETAILS PD
                                             ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                     INNER JOIN CCP_DETAILS CCP
                                             ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                              WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID) ACT
                             FULL JOIN (SELECT NPS.PROJECTION_DETAILS_SID,
                                               PERIOD_SID,
                                               CCP.CONTRACT_MASTER_SID,
                                               PROJECTION_SALES,
                                               PROJECTION_UNITS
                                        FROM   M_SALES_PROJECTION NPS
                                               INNER JOIN PROJECTION_DETAILS PD
                                                       ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                               INNER JOIN CCP_DETAILS CCP
                                                       ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                        WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                               AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                    ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                       AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                             INNER JOIN PERIOD P
                                     ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                      GROUP  BY COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID),
                                [QUARTER],
                                [YEAR]) SALES
                     INNER JOIN CONTRACT_MASTER CM
                             ON CM.CONTRACT_MASTER_SID = SALES.CONTRACT_MASTER_SID
                     LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                       SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                       [QUARTER],
                                       [YEAR]
                                FROM   (SELECT PROJECTION_DETAILS_SID,
                                               PERIOD_SID,
                                               ACTUAL_SALES
                                        FROM   M_ACTUAL_DISCOUNT NAS
                                        WHERE  EXISTS (SELECT 1
                                                       FROM   PROJECTION_DETAILS PD
                                                       WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                              AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                               AND NAS.SAVE_FLAG = 1) ACT
                                       FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                         PERIOD_SID,
                                                         PROJECTION_SALES
                                                  FROM   M_DISCOUNT_PROJECTION NPS
                                                  WHERE  EXISTS (SELECT 1
                                                                 FROM   PROJECTION_DETAILS PD
                                                                 WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                        AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                         AND NPS.SAVE_FLAG = 1
                                                         AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                              ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                 AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                       INNER JOIN PERIOD P
                                               ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                GROUP  BY [QUARTER],
                                          [YEAR]) DISCOUNT
                            ON DISCOUNT.[QUARTER] = SALES.[QUARTER]
                               AND DISCOUNT.[YEAR] = SALES.[YEAR]
                     LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                       SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                       [QUARTER],
                                       [YEAR]
                                FROM   (SELECT PROJECTION_DETAILS_SID,
                                               PERIOD_SID,
                                               ACTUAL_SALES
                                        FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                        WHERE  EXISTS (SELECT 1
                                                       FROM   PROJECTION_DETAILS PD
                                                       WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                              AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                       FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                         PERIOD_SID,
                                                         PROJECTION_SALES
                                                  FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                  WHERE  EXISTS (SELECT 1
                                                                 FROM   PROJECTION_DETAILS PD
                                                                 WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                        AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                         AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                              ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                 AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                       INNER JOIN PERIOD P
                                               ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                GROUP  BY [QUARTER],
                                          [YEAR]) SUPPLEMENTAL
                            ON SUPPLEMENTAL.[QUARTER] = SALES.[QUARTER]
                               AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
            ELSE IF @FREQUENCY = 'SEMI-ANNUAL'
              SELECT @PROJECTION_MASTER_SID AS PROJECTION_ID,
                     SALES.[SEMI_ANNUAL],
                     SALES.[YEAR],
                     CM.CONTRACT_ID,
                     CM.CONTRACT_NO,
                     CM.CONTRACT_NAME,
                     ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                     ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                     ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                     ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                     ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                     ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                     ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                     ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                     ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                     ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                     ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                     ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                     ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUAL,
                     ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_PROPJECTED,
                     ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUAL,
                     ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROPJECTED
              FROM   (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                             SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                             SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                             SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                             SEMI_ANNUAL,
                             [YEAR],
                             COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID) AS CONTRACT_MASTER_SID 
                      FROM   (SELECT NAS.PROJECTION_DETAILS_SID,
                                     PERIOD_SID,
                                     CCP.CONTRACT_MASTER_SID,
                                     ACTUAL_SALES,
                                     ACTUAL_UNITS
                              FROM   M_ACTUAL_SALES NAS
                                     INNER JOIN PROJECTION_DETAILS PD
                                             ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                     INNER JOIN CCP_DETAILS CCP
                                             ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                              WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID) ACT
                             FULL JOIN (SELECT NPS.PROJECTION_DETAILS_SID,
                                               PERIOD_SID,
                                               CCP.CONTRACT_MASTER_SID,
                                               PROJECTION_SALES,
                                               PROJECTION_UNITS
                                        FROM   M_SALES_PROJECTION NPS
                                               INNER JOIN PROJECTION_DETAILS PD
                                                       ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                               INNER JOIN CCP_DETAILS CCP
                                                       ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                        WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                               AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                    ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                       AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                             INNER JOIN PERIOD P
                                     ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                      GROUP  BY COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID),
                                SEMI_ANNUAL,
                                [YEAR]) SALES
                     INNER JOIN CONTRACT_MASTER CM
                             ON CM.CONTRACT_MASTER_SID = SALES.CONTRACT_MASTER_SID
                     LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                       SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                       SEMI_ANNUAL,
                                       [YEAR]
                                FROM   (SELECT NAS.PROJECTION_DETAILS_SID,
                                               PERIOD_SID,
                                               ACTUAL_SALES
                                        FROM   M_ACTUAL_DISCOUNT NAS
                                        WHERE  EXISTS (SELECT 1
                                                       FROM   PROJECTION_DETAILS PD
                                                       WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                              AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                               AND NAS.SAVE_FLAG = 1) ACT
                                       FULL JOIN (SELECT NPS.PROJECTION_DETAILS_SID,
                                                         PERIOD_SID,
                                                         PROJECTION_SALES
                                                  FROM   M_DISCOUNT_PROJECTION NPS
                                                  WHERE  EXISTS (SELECT 1
                                                                 FROM   PROJECTION_DETAILS PD
                                                                 WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                        AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                         AND NPS.SAVE_FLAG = 1
                                                         AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                              ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                 AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                       INNER JOIN PERIOD P
                                               ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                GROUP  BY SEMI_ANNUAL,
                                          [YEAR]) DISCOUNT
                            ON DISCOUNT.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                               AND DISCOUNT.[YEAR] = SALES.[YEAR]
                     LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                       SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                       SEMI_ANNUAL,
                                       [YEAR]
                                FROM   (SELECT PROJECTION_DETAILS_SID,
                                               PERIOD_SID,
                                               ACTUAL_SALES
                                        FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                        WHERE  EXISTS (SELECT 1
                                                       FROM   PROJECTION_DETAILS PD
                                                       WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                              AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                       FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                         PERIOD_SID,
                                                         PROJECTION_SALES
                                                  FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                  WHERE  EXISTS (SELECT 1
                                                                 FROM   PROJECTION_DETAILS PD
                                                                 WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                        AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                         AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                              ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                 AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                       INNER JOIN PERIOD P
                                               ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                GROUP  BY SEMI_ANNUAL,
                                          [YEAR]) SUPPLEMENTAL
                            ON SUPPLEMENTAL.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                               AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
            ELSE
              SELECT @PROJECTION_MASTER_SID AS PROJECTION_ID,
                     SALES.[YEAR],
                     CM.CONTRACT_ID,
                     CM.CONTRACT_NO,
                     CM.CONTRACT_NAME,
                     ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                     ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                     ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                     ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                     ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                     ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                     ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                     ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                     ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                     ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                     ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                     ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                     ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUAL,
                     ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_PROPJECTED,
                     ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUAL,
                     ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROPJECTED
              FROM   (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                             SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                             SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                             SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                             [YEAR],
                             COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID) AS CONTRACT_MASTER_SID 
                      FROM   (SELECT NAS.PROJECTION_DETAILS_SID,
                                     PERIOD_SID,
                                     CCP.CONTRACT_MASTER_SID,
                                     ACTUAL_SALES,
                                     ACTUAL_UNITS
                              FROM   M_ACTUAL_SALES NAS
                                     INNER JOIN PROJECTION_DETAILS PD
                                             ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                     INNER JOIN CCP_DETAILS CCP
                                             ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                              WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID) ACT
                             FULL JOIN (SELECT NPS.PROJECTION_DETAILS_SID,
                                               PERIOD_SID,
                                               CCP.CONTRACT_MASTER_SID,
                                               PROJECTION_SALES,
                                               PROJECTION_UNITS
                                        FROM   M_SALES_PROJECTION NPS
                                               INNER JOIN PROJECTION_DETAILS PD
                                                       ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                               INNER JOIN CCP_DETAILS CCP
                                                       ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                        WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                               AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                    ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                       AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                             INNER JOIN PERIOD P
                                     ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                      GROUP  BY COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID),
                                [YEAR]) SALES
                     INNER JOIN CONTRACT_MASTER CM
                             ON CM.CONTRACT_MASTER_SID = SALES.CONTRACT_MASTER_SID
                     LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                       SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                       [YEAR]
                                FROM   (SELECT PROJECTION_DETAILS_SID,
                                               PERIOD_SID,
                                               ACTUAL_SALES
                                        FROM   M_ACTUAL_DISCOUNT NAS
                                        WHERE  EXISTS (SELECT 1
                                                       FROM   PROJECTION_DETAILS PD
                                                       WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                              AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                               AND NAS.SAVE_FLAG = 1) ACT
                                       FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                         PERIOD_SID,
                                                         PROJECTION_SALES
                                                  FROM   M_DISCOUNT_PROJECTION NPS
                                                  WHERE  EXISTS (SELECT 1
                                                                 FROM   PROJECTION_DETAILS PD
                                                                 WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                        AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                         AND NPS.SAVE_FLAG = 1
                                                         AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                              ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                 AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                       INNER JOIN PERIOD P
                                               ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                GROUP  BY [YEAR]) DISCOUNT
                            ON DISCOUNT.[YEAR] = SALES.[YEAR]
                     LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                       SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                       [YEAR]
                                FROM   (SELECT PROJECTION_DETAILS_SID,
                                               PERIOD_SID,
                                               ACTUAL_SALES
                                        FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                        WHERE  EXISTS (SELECT 1
                                                       FROM   PROJECTION_DETAILS PD
                                                       WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                              AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                       FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                         PERIOD_SID,
                                                         PROJECTION_SALES
                                                  FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                  WHERE  EXISTS (SELECT 1
                                                                 FROM   PROJECTION_DETAILS PD
                                                                 WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                        AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                         AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @ACTUAL_END_PERIOD) PROJ
                                              ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                 AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                       INNER JOIN PERIOD P
                                               ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                GROUP  BY [YEAR]) SUPPLEMENTAL
                            ON SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
        END
  END 
