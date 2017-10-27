IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_M_PROJ_RESULTS_CONTRACT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_M_PROJ_RESULTS_CONTRACT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_M_PROJ_RESULTS_CONTRACT] (@PROJECTION_MASTER_SID INT,
                                                     @USER_ID               INT,
                                                     @SESSION_ID            VARCHAR(50),
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
              @PROJ_START_PERIOD_SID INT,
              @SQL                   NVARCHAR(MAX)
	 DECLARE @S_ACTUAL_TABLE  VARCHAR(max)= Concat('ST_M_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @S_PROJECTION_TABLE  VARCHAR(200)= Concat('ST_M_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @DISCOUNT_ACTUAL   VARCHAR(200)= Concat('ST_M_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @DISCOUNT_PROJECTION   VARCHAR(200)= Concat('ST_M_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @SUPP_ACTUAL   VARCHAR(200)= Concat('ST_M_SUPPLEMENTAL_DISC_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @SUPP_PROJ   VARCHAR(200)= Concat('ST_M_SUPPLEMENTAL_DISC_PROJ_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

      SELECT TOP 1 @STARTFROM = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0),
                   @START_DATE = Dateadd(dd, 1, Eomonth(FROM_DATE, -1)),
                   @PROJECTION_DATE = Dateadd(dd, 1, Eomonth(TO_DATE, -1))
      FROM   PROJECTION_MASTER
      WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
	   ORDER BY PROJECTION_MASTER_SID

      SELECT @PROJ_START_PERIOD_SID = Max(CASE
                                            WHEN PERIOD_DATE = @START_DATE THEN PERIOD_SID
                                          END)
      FROM   PERIOD
      WHERE  PERIOD_DATE IN ( @STARTFROM, @PROJECTION_DATE, @START_DATE )

      IF @SCREEN_NAME = 'PROJECTION RESULTS'
        BEGIN
            IF @FREQUENCY = 'MONTHLY'
              BEGIN
                  SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                  FROM   PERIOD
                  WHERE  MONTH = @ACTUAL_START_FREQ
                         AND YEAR = @ACTUAL_START_YEAR

                  SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                  FROM   PERIOD
                  WHERE  PERIOD_DATE =Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0)
                                       

              SET @SQL=CONCAT('    SELECT PROJECTION_ID = ',@PROJECTION_MASTER_SID,',
                         [MONTH],
                         [YEAR],
                         CM.CONTRACT_ID,
                         CM.CONTRACT_NO,
                         CM.CONTRACT_NAME,
                         Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                         CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                         CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
                         CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                         MANDATED_DISCOUNT_ACTUALS = Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0),
                         MANDATED_DISCOUNT_PROJECTED = Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0),
                         SUPPLEMENTAL_DISCOUNT_ACTUALS = Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0),
                         SUPPLEMENTAL_DISCOUNT_PROJECTED = Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0),
                         MANDATED_DISCOUNT_ACTUALS_PERCENTAGE = ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                         MANDATED_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                         SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE = ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                         SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                         TOTAL_MANDATED_RPU_ACTUAL = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_MANDATED_RPU_PROPJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_SUPPLEMENTAL_RPU_ACTUAL = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_SUPPLEMENTAL_RPU_PROPJECTED = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) )
                  FROM   (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                 CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                 CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                 CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                 PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID),
                                 CONTRACT_MASTER_SID = COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID)
                          FROM   (SELECT NAS.CCP_DETAILS_SID,
                                         PERIOD_SID,
                                         CCP.CONTRACT_MASTER_SID,
                                         ACTUAL_SALES,
                                         ACTUAL_UNITS
                                  FROM   ',@S_ACTUAL_TABLE,' NAS
                                         
                                         INNER JOIN CCP_DETAILS CCP
                                                 ON CCP.CCP_DETAILS_SID = NAS.CCP_DETAILS_SID
                                   ) ACT
                                 FULL JOIN (SELECT NPS.CCP_DETAILS_SID,
                                                   PERIOD_SID,
                                                   CCP.CONTRACT_MASTER_SID,
                                                   PROJECTION_SALES,
                                                   PROJECTION_UNITS
                                            FROM   ',@S_PROJECTION_TABLE,' NPS
                                                  
                                                   INNER JOIN CCP_DETAILS CCP
                                                           ON CCP.CCP_DETAILS_SID = NPS.CCP_DETAILS_SID
                                            WHERE   NPS.PERIOD_SID BETWEEN ',@PROJ_START_PERIOD_SID,' AND ',@ACTUAL_END_PERIOD,') PROJ
                                        ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                           AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           AND ACT.CONTRACT_MASTER_SID = PROJ.CONTRACT_MASTER_SID
                          GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID),
                                    COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID)) SALES
                         INNER JOIN PERIOD P
                                 ON P.PERIOD_SID = SALES.PERIOD_SID
                         INNER JOIN CONTRACT_MASTER CM
                                 ON CM.CONTRACT_MASTER_SID = SALES.CONTRACT_MASTER_SID
                         LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                           CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                           PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    FROM   (SELECT CCP_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   ',@DISCOUNT_ACTUAL,' NAS
                                            ) ACT
                                           FULL JOIN (SELECT CCP_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   ',@DISCOUNT_PROJECTION,' NPS
                                                      WHERE   NPS.PERIOD_SID BETWEEN ',@PROJ_START_PERIOD_SID,' AND ',@ACTUAL_END_PERIOD,') PROJ
                                                  ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                    GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) DISCOUNT
                                ON DISCOUNT.PERIOD_SID = SALES.PERIOD_SID
                         LEFT JOIN (SELECT CONTRACT_SUPPLEMENTAL_ACTUALS = Sum(ACTUAL_SALES),
                                           CONTRACT_SUPPLEMENTAL_PROJECTED = Sum(PROJECTION_SALES),
                                           PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    FROM   (SELECT CCP_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   ',@SUPP_ACTUAL,' NAS
                                            ) ACT
                                           FULL JOIN (SELECT CCP_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   ',@SUPP_PROJ,' NPS
                                                       ) PROJ
                                                  ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                    GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SUPPLEMENTAL
                                ON SUPPLEMENTAL.PERIOD_SID = DISCOUNT.PERIOD_SID
                  WHERE  P.PERIOD_SID BETWEEN Isnull(',@ACTUAL_START_PERIOD,', 1) AND ',@ACTUAL_END_PERIOD)
			EXEC sp_executesql @SQL
              END
            ELSE IF @FREQUENCY = 'QUARTERLY'
              BEGIN
                  SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                  FROM   PERIOD
                  WHERE  QUARTER = @ACTUAL_START_FREQ
                         AND YEAR = @ACTUAL_START_YEAR

                  SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                  FROM   PERIOD
                  WHERE  PERIOD_DATE =Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0)

                   SET @SQL=CONCAT('    SELECT PROJECTION_ID = ',@PROJECTION_MASTER_SID,',
                         SALES.[QUARTER],
                         SALES.[YEAR],
                         CM.CONTRACT_ID,
                         CM.CONTRACT_NO,
                         CM.CONTRACT_NAME,
                         Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                         CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                         CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
                         CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                         MANDATED_DISCOUNT_ACTUALS = Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0),
                         MANDATED_DISCOUNT_PROJECTED = Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0),
                         SUPPLEMENTAL_DISCOUNT_ACTUALS = Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0),
                         SUPPLEMENTAL_DISCOUNT_PROJECTED = Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0),
                         MANDATED_DISCOUNT_ACTUALS_PERCENTAGE = ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                         MANDATED_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                         SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE = ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                         SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                         TOTAL_MANDATED_RPU_ACTUAL = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_MANDATED_RPU_PROPJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_SUPPLEMENTAL_RPU_ACTUAL = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_SUPPLEMENTAL_RPU_PROPJECTED = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) )
                  FROM   (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                 CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                 CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                 CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                 CONTRACT_MASTER_SID = COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID),
                                 [QUARTER],
                                 [YEAR]
                          FROM   (SELECT NAS.CCP_DETAILS_SID,
                                         PERIOD_SID,
                                         CCP.CONTRACT_MASTER_SID,
                                         ACTUAL_SALES,
                                         ACTUAL_UNITS
                                  FROM   ',@S_ACTUAL_TABLE,' NAS
                                        
                                         INNER JOIN CCP_DETAILS CCP
                                                 ON CCP.CCP_DETAILS_SID = NAS.CCP_DETAILS_SID
                                    ) ACT
                                 FULL JOIN (SELECT NPS.CCP_DETAILS_SID,
                                                   PERIOD_SID,
                                                   CCP.CONTRACT_MASTER_SID,
                                                   PROJECTION_SALES,
                                                   PROJECTION_UNITS
                                            FROM   ',@S_PROJECTION_TABLE,' NPS
                                                   
                                                   INNER JOIN CCP_DETAILS CCP
                                                           ON CCP.CCP_DETAILS_SID = NPS.CCP_DETAILS_SID
                                            WHERE  NPS.PERIOD_SID BETWEEN ',@PROJ_START_PERIOD_SID,' AND ',@ACTUAL_END_PERIOD,') PROJ
                                        ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                           AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                 INNER JOIN PERIOD P
                                         ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                          WHERE  P.PERIOD_SID BETWEEN Isnull(',@ACTUAL_START_PERIOD,', 1) AND ',@ACTUAL_END_PERIOD,'
                          GROUP  BY COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID),
                                    [QUARTER],
                                    [YEAR]) SALES
                         INNER JOIN CONTRACT_MASTER CM
                                 ON CM.CONTRACT_MASTER_SID = SALES.CONTRACT_MASTER_SID
                         LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                           CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                           [QUARTER],
                                           [YEAR]
                                    FROM   (SELECT CCP_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   ',@DISCOUNT_ACTUAL,' NAS
                                            ) ACT
                                           FULL JOIN (SELECT CCP_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   ',@DISCOUNT_PROJECTION,' NPS
                                                     WHERE NPS.PERIOD_SID BETWEEN ',@PROJ_START_PERIOD_SID,' AND ',@ACTUAL_END_PERIOD,') PROJ
                                                  ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           INNER JOIN PERIOD P
                                                   ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    GROUP  BY [QUARTER],
                                              [YEAR]) DISCOUNT
                                ON DISCOUNT.[QUARTER] = SALES.[QUARTER]
                                   AND DISCOUNT.[YEAR] = SALES.[YEAR]
                         LEFT JOIN (SELECT CONTRACT_SUPPLEMENTAL_ACTUALS = Sum(ACTUAL_SALES),
                                           CONTRACT_SUPPLEMENTAL_PROJECTED = Sum(PROJECTION_SALES),
                                           [QUARTER],
                                           [YEAR]
                                    FROM   (SELECT CCP_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   ',@SUPP_ACTUAL,' NAS
                                           ) ACT
                                           FULL JOIN (SELECT CCP_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   ',@SUPP_PROJ,' NPS
                                                     ) PROJ
                                                  ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           INNER JOIN PERIOD P
                                                   ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    GROUP  BY [QUARTER],
                                              [YEAR]) SUPPLEMENTAL
                                ON SUPPLEMENTAL.[QUARTER] = SALES.[QUARTER]
                                   AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]')
								  
						EXEC sp_executesql @SQL
              END
            ELSE IF @FREQUENCY = 'SEMI-ANNUALLY'
              BEGIN
                  SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                  FROM   PERIOD
                  WHERE  SEMI_ANNUAL = @ACTUAL_START_FREQ
                         AND YEAR = @ACTUAL_START_YEAR

                  SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                  FROM   PERIOD
                  WHERE  PERIOD_DATE =Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0)

            SET @SQL=CONCAT('    SELECT PROJECTION_ID = ',@PROJECTION_MASTER_SID,',
                         SALES.[SEMI_ANNUAL],
                         SALES.[YEAR],
                         CM.CONTRACT_ID,
                         CM.CONTRACT_NO,
                         CM.CONTRACT_NAME,
                         Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                         CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                         CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
                         CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                         MANDATED_DISCOUNT_ACTUALS = Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0),
                         MANDATED_DISCOUNT_PROJECTED = Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0),
                         SUPPLEMENTAL_DISCOUNT_ACTUALS = Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0),
                         SUPPLEMENTAL_DISCOUNT_PROJECTED = Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0),
                         MANDATED_DISCOUNT_ACTUALS_PERCENTAGE = ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                         MANDATED_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                         SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE = ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                         SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                         TOTAL_MANDATED_RPU_ACTUAL = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_MANDATED_RPU_PROPJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_SUPPLEMENTAL_RPU_ACTUAL = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_SUPPLEMENTAL_RPU_PROPJECTED = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) )
                  FROM   (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                 CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                 CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                 CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                 CONTRACT_MASTER_SID = COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID),
                                 [SEMI_ANNUAL],
                                 [YEAR]
                          FROM   (SELECT NAS.CCP_DETAILS_SID,
                                         PERIOD_SID,
                                         CCP.CONTRACT_MASTER_SID,
                                         ACTUAL_SALES,
                                         ACTUAL_UNITS
                                  FROM   ',@S_ACTUAL_TABLE,' NAS
                                        
                                         INNER JOIN CCP_DETAILS CCP
                                                 ON CCP.CCP_DETAILS_SID = NAS.CCP_DETAILS_SID
                                    ) ACT
                                 FULL JOIN (SELECT NPS.CCP_DETAILS_SID,
                                                   PERIOD_SID,
                                                   CCP.CONTRACT_MASTER_SID,
                                                   PROJECTION_SALES,
                                                   PROJECTION_UNITS
                                            FROM   ',@S_PROJECTION_TABLE,' NPS
                                                   
                                                   INNER JOIN CCP_DETAILS CCP
                                                           ON CCP.CCP_DETAILS_SID = NPS.CCP_DETAILS_SID
                                            WHERE  NPS.PERIOD_SID BETWEEN ',@PROJ_START_PERIOD_SID,' AND ',@ACTUAL_END_PERIOD,') PROJ
                                        ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                           AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                 INNER JOIN PERIOD P
                                         ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                          WHERE  P.PERIOD_SID BETWEEN Isnull(',@ACTUAL_START_PERIOD,', 1) AND ',@ACTUAL_END_PERIOD,'
                          GROUP  BY COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID),
                                    [SEMI_ANNUAL],
                                    [YEAR]) SALES
                         INNER JOIN CONTRACT_MASTER CM
                                 ON CM.CONTRACT_MASTER_SID = SALES.CONTRACT_MASTER_SID
                         LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                           CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                           [SEMI_ANNUAL],
                                           [YEAR]
                                    FROM   (SELECT CCP_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   ',@DISCOUNT_ACTUAL,' NAS
                                            ) ACT
                                           FULL JOIN (SELECT CCP_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   ',@DISCOUNT_PROJECTION,' NPS
                                                     WHERE NPS.PERIOD_SID BETWEEN ',@PROJ_START_PERIOD_SID,' AND ',@ACTUAL_END_PERIOD,') PROJ
                                                  ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           INNER JOIN PERIOD P
                                                   ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    GROUP  BY [SEMI_ANNUAL],
                                              [YEAR]) DISCOUNT
                                ON DISCOUNT.[SEMI_ANNUAL] = SALES.[SEMI_ANNUAL]
                                   AND DISCOUNT.[YEAR] = SALES.[YEAR]
                         LEFT JOIN (SELECT CONTRACT_SUPPLEMENTAL_ACTUALS = Sum(ACTUAL_SALES),
                                           CONTRACT_SUPPLEMENTAL_PROJECTED = Sum(PROJECTION_SALES),
                                           [SEMI_ANNUAL],
                                           [YEAR]
                                    FROM   (SELECT CCP_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   ',@SUPP_ACTUAL,' NAS
                                           ) ACT
                                           FULL JOIN (SELECT CCP_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   ',@SUPP_PROJ,' NPS
                                                     ) PROJ
                                                  ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           INNER JOIN PERIOD P
                                                   ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    GROUP  BY [SEMI_ANNUAL],
                                              [YEAR]) SUPPLEMENTAL
                                ON SUPPLEMENTAL.[SEMI_ANNUAL] = SALES.[SEMI_ANNUAL]
                                   AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]')
						EXEC sp_executesql @SQL
              END
            ELSE
              BEGIN
                  SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                  FROM   PERIOD
                  WHERE  YEAR = @ACTUAL_START_YEAR

                  SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                  FROM   PERIOD
                  WHERE  PERIOD_DATE =Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0)

        SET @SQL=CONCAT('    SELECT PROJECTION_ID = ',@PROJECTION_MASTER_SID,',
                        
                         SALES.[YEAR],
                         CM.CONTRACT_ID,
                         CM.CONTRACT_NO,
                         CM.CONTRACT_NAME,
                         Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                         CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                         CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
                         CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                         MANDATED_DISCOUNT_ACTUALS = Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0),
                         MANDATED_DISCOUNT_PROJECTED = Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0),
                         SUPPLEMENTAL_DISCOUNT_ACTUALS = Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0),
                         SUPPLEMENTAL_DISCOUNT_PROJECTED = Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0),
                         MANDATED_DISCOUNT_ACTUALS_PERCENTAGE = ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                         MANDATED_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                         SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE = ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                         SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                         TOTAL_MANDATED_RPU_ACTUAL = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_MANDATED_RPU_PROPJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_SUPPLEMENTAL_RPU_ACTUAL = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_SUPPLEMENTAL_RPU_PROPJECTED = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) )
                  FROM   (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                 CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                 CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                 CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                 CONTRACT_MASTER_SID = COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID),
                                
                                 [YEAR]
                          FROM   (SELECT NAS.CCP_DETAILS_SID,
                                         PERIOD_SID,
                                         CCP.CONTRACT_MASTER_SID,
                                         ACTUAL_SALES,
                                         ACTUAL_UNITS
                                  FROM   ',@S_ACTUAL_TABLE,' NAS
                                        
                                         INNER JOIN CCP_DETAILS CCP
                                                 ON CCP.CCP_DETAILS_SID = NAS.CCP_DETAILS_SID
                                    ) ACT
                                 FULL JOIN (SELECT NPS.CCP_DETAILS_SID,
                                                   PERIOD_SID,
                                                   CCP.CONTRACT_MASTER_SID,
                                                   PROJECTION_SALES,
                                                   PROJECTION_UNITS
                                            FROM   ',@S_PROJECTION_TABLE,' NPS
                                                   
                                                   INNER JOIN CCP_DETAILS CCP
                                                           ON CCP.CCP_DETAILS_SID = NPS.CCP_DETAILS_SID
                                            WHERE  NPS.PERIOD_SID BETWEEN ',@PROJ_START_PERIOD_SID,' AND ',@ACTUAL_END_PERIOD,') PROJ
                                        ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                           AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                 INNER JOIN PERIOD P
                                         ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                          WHERE  P.PERIOD_SID BETWEEN Isnull(',@ACTUAL_START_PERIOD,', 1) AND ',@ACTUAL_END_PERIOD,'
                          GROUP  BY COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID),
                                    
                                    [YEAR]) SALES
                         INNER JOIN CONTRACT_MASTER CM
                                 ON CM.CONTRACT_MASTER_SID = SALES.CONTRACT_MASTER_SID
                         LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                           CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                           
                                           [YEAR]
                                    FROM   (SELECT CCP_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   ',@DISCOUNT_ACTUAL,' NAS
                                            ) ACT
                                           FULL JOIN (SELECT CCP_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   ',@DISCOUNT_PROJECTION,' NPS
                                                     WHERE NPS.PERIOD_SID BETWEEN ',@PROJ_START_PERIOD_SID,' AND ',@ACTUAL_END_PERIOD,') PROJ
                                                  ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           INNER JOIN PERIOD P
                                                   ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    GROUP  BY 
                                              [YEAR]) DISCOUNT
                                ON  DISCOUNT.[YEAR] = SALES.[YEAR]
                         LEFT JOIN (SELECT CONTRACT_SUPPLEMENTAL_ACTUALS = Sum(ACTUAL_SALES),
                                           CONTRACT_SUPPLEMENTAL_PROJECTED = Sum(PROJECTION_SALES),
                                          
                                           [YEAR]
                                    FROM   (SELECT CCP_DETAILS_SID,
                                                   PERIOD_SID,
                                                   ACTUAL_SALES
                                            FROM   ',@SUPP_ACTUAL,' NAS
                                           ) ACT
                                           FULL JOIN (SELECT CCP_DETAILS_SID,
                                                             PERIOD_SID,
                                                             PROJECTION_SALES
                                                      FROM   ',@SUPP_PROJ,' NPS
                                                     ) PROJ
                                                  ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                     AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                           INNER JOIN PERIOD P
                                                   ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    GROUP  BY 
                                              [YEAR]) SUPPLEMENTAL
                                ON 
                                    SUPPLEMENTAL.[YEAR] = SALES.[YEAR]')
						EXEC sp_executesql @SQL
              END
        END
      ELSE
        BEGIN
            IF @FREQUENCY = 'MONTHLY'
              SELECT  @PROJECTION_MASTER_SID AS PROJECTION_ID,
                     [MONTH],
                     [YEAR],
                     CM.CONTRACT_ID,
                     CM.CONTRACT_NO,
                     CM.CONTRACT_NAME,
                      Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) aS CONTRACT_SALES_ACTUALS,
                      Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) As CONTRACT_SALES_PROJECTED,
                      Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS, 
                      Isnull(CONTRACT_UNITS_PROJECTED, 0) aS CONTRACT_UNITS_PROJECTED,
                      Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS, 
                       Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) As MANDATED_DISCOUNT_PROJECTED,
                      Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) aS SUPPLEMENTAL_DISCOUNT_ACTUALS, 
                      Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0)as SUPPLEMENTAL_DISCOUNT_PROJECTED, 
                      ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                      ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                      ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 As SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE, 
                      ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 as SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                      ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUAL,
                      ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) As TOTAL_MANDATED_RPU_PROPJECTED, 
                      ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUAL,
                       ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROPJECTED
              FROM   (SELECT  Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                              Sum(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                              Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                              Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
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
                     LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                        Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
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
                     LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                       Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED, 
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
              SELECT  @PROJECTION_MASTER_SID aS PROJECTION_ID,
                     SALES.[QUARTER],
                     SALES.[YEAR],
                     CM.CONTRACT_ID,
                     CM.CONTRACT_NO,
                     CM.CONTRACT_NAME,
                     Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                     Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                      Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                      Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                      Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) As MANDATED_DISCOUNT_ACTUALS,
                      Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                      Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) aS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                      Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) as SUPPLEMENTAL_DISCOUNT_PROJECTED,
                      ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                      ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 As MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                      ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                      ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,  
                      ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUAL, 
                      ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) As TOTAL_MANDATED_RPU_PROPJECTED,
                      ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUAL,
                       ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROPJECTED
              FROM   (SELECT  Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                              Sum(PROJECTION_SALES) aS CONTRACT_SALES_PROJECTED,
                              Sum(ACTUAL_UNITS) aS CONTRACT_UNITS_ACTUALS, 
                              Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                              COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID) AS CONTRACT_MASTER_SID, 
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
                     LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                        Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED, 
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
                                               AND NAS.SAVE_FLAG = 1
                                               ) ACT
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
                     LEFT JOIN (SELECT  Sum(ACTUAL_SALES) as CONTRACT_SUPPLEMENTAL_ACTUALS,
                                        Sum(PROJECTION_SALES) aS CONTRACT_SUPPLEMENTAL_PROJECTED,
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
              SELECT  @PROJECTION_MASTER_SID as PROJECTION_ID,
                     SALES.[SEMI_ANNUAL],
                     SALES.[YEAR],
                     CM.CONTRACT_ID,
                     CM.CONTRACT_NO,
                     CM.CONTRACT_NAME,
                     Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                     Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) As CONTRACT_SALES_PROJECTED,
                     Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0) as CONTRACT_UNITS_ACTUALS,
                     Isnull(CONTRACT_UNITS_PROJECTED, 0) as CONTRACT_UNITS_PROJECTED,
                     Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) as MANDATED_DISCOUNT_ACTUALS,
                     Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) as MANDATED_DISCOUNT_PROJECTED,
                      Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) as SUPPLEMENTAL_DISCOUNT_ACTUALS,
                      Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) as SUPPLEMENTAL_DISCOUNT_PROJECTED,
                      ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 as MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                      ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 as MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                      ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 as SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                      ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 as SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                      ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) as TOTAL_MANDATED_RPU_ACTUAL,
                      ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) as TOTAL_MANDATED_RPU_PROPJECTED,
                      ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) as TOTAL_SUPPLEMENTAL_RPU_ACTUAL, 
                      ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) as TOTAL_SUPPLEMENTAL_RPU_PROPJECTED
              FROM   (SELECT  Sum(ACTUAL_SALES) As CONTRACT_SALES_ACTUALS,  
                              Sum(PROJECTION_SALES) as CONTRACT_SALES_PROJECTED,
                              Sum(ACTUAL_UNITS) as CONTRACT_UNITS_ACTUALS,
                               Sum(PROJECTION_UNITS) as CONTRACT_UNITS_PROJECTED,
                             SEMI_ANNUAL,
                             [YEAR],
                             COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID) as CONTRACT_MASTER_SID
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
                     LEFT JOIN (SELECT  Sum(ACTUAL_SALES) as CONTRACT_DISCOUNT_ACTUALS,
                                        Sum(PROJECTION_SALES) as CONTRACT_DISCOUNT_PROJECTED, 
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
                     LEFT JOIN (SELECT  Sum(ACTUAL_SALES) as CONTRACT_SUPPLEMENTAL_ACTUALS,
                                        Sum(PROJECTION_SALES) as CONTRACT_SUPPLEMENTAL_PROJECTED,
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
              SELECT  @PROJECTION_MASTER_SID as PROJECTION_ID,
                     SALES.[YEAR],
                     CM.CONTRACT_ID,
                     CM.CONTRACT_NO,
                     CM.CONTRACT_NAME,
                     Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                      Isnull(SALES.CONTRACT_SALES_PROJECTED, 0)as CONTRACT_SALES_PROJECTED,
                      Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0) as CONTRACT_UNITS_ACTUALS,
                      Isnull(CONTRACT_UNITS_PROJECTED, 0) as CONTRACT_UNITS_PROJECTED,
                      Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)as MANDATED_DISCOUNT_ACTUALS,
                     Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) as MANDATED_DISCOUNT_PROJECTED,
                      Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) as SUPPLEMENTAL_DISCOUNT_ACTUALS,
                      Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0)as SUPPLEMENTAL_DISCOUNT_PROJECTED,
                      ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 as MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                      ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 as MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                      ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                      ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 as SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                      ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) as TOTAL_MANDATED_RPU_ACTUAL,
                      ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) as TOTAL_MANDATED_RPU_PROPJECTED,
                      ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) as TOTAL_SUPPLEMENTAL_RPU_ACTUAL, 
                      ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) as TOTAL_SUPPLEMENTAL_RPU_PROPJECTED
              FROM   (SELECT Sum(ACTUAL_SALES) aS CONTRACT_SALES_ACTUALS,
                              Sum(PROJECTION_SALES) as CONTRACT_SALES_PROJECTED,
                             Sum(ACTUAL_UNITS) as CONTRACT_UNITS_ACTUALS,
                              Sum(PROJECTION_UNITS) as CONTRACT_UNITS_PROJECTED,
                             [YEAR],
                              COALESCE(ACT.CONTRACT_MASTER_SID, PROJ.CONTRACT_MASTER_SID) as CONTRACT_MASTER_SID
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
                     LEFT JOIN (SELECT  Sum(ACTUAL_SALES) as CONTRACT_DISCOUNT_ACTUALS, 
                                        Sum(PROJECTION_SALES) as CONTRACT_DISCOUNT_PROJECTED, 
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
                     LEFT JOIN (SELECT  Sum(ACTUAL_SALES) as CONTRACT_SUPPLEMENTAL_ACTUALS,
                                        Sum(PROJECTION_SALES) as CONTRACT_SUPPLEMENTAL_PROJECTED, 
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
