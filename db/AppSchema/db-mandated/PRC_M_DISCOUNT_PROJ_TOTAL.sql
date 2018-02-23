IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_M_DISCOUNT_PROJ_TOTAL'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_M_DISCOUNT_PROJ_TOTAL]
  END

GO

CREATE PROCEDURE [dbo].[PRC_M_DISCOUNT_PROJ_TOTAL](@PROJECTION_MASTER_SID INT,
                                                   @USER_ID               INT,
                                                   @SESSION_ID             VARCHAR(50),
                                                   @MARKET_TYPE           VARCHAR(100),
                                                   @FREQUENCY             VARCHAR(50),
                                                   @THERAPEUTIC_CLASS     VARCHAR(100),
                                                   @BRAND                 VARCHAR(100),
                                                   @ACTUAL_START_FREQ     INT,
                                                   @ACTUAL_START_YEAR     INT)
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          DECLARE @ACTUAL_START_PERIOD INT
          DECLARE @ACTUAL_END_PERIOD INT

		  DECLARE @CCP_HIERARCHY VARCHAR(200) = Concat('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
		 DECLARE @S_ACTUAL_TABLE  VARCHAR(200)= Concat('ST_M_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @S_PROJECTION_TABLE  VARCHAR(max)= Concat('ST_M_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @DISCOUNT_ACTUAL   VARCHAR(max)= Concat('ST_M_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @DISCOUNT_PROJECTION   VARCHAR(max)= Concat('ST_M_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @SUPP_ACTUAL   VARCHAR(max)= Concat('ST_M_SUPPLEMENTAL_DISC_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @SUPP_PROJ   VARCHAR(max)= Concat('ST_M_SUPPLEMENTAL_DISC_PROJ_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @SQL         NVARCHAR(MAX)

          SELECT @THERAPEUTIC_CLASS = HELPER_TABLE_SID
          FROM   DBO.HELPER_TABLE
          WHERE  DESCRIPTION = @THERAPEUTIC_CLASS

          SELECT @BRAND = BRAND_MASTER_SID
          FROM   DBO.BRAND_MASTER
          WHERE  BRAND_NAME = @BRAND


          SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
          FROM   PERIOD
          WHERE  QUARTER = @ACTUAL_START_FREQ AND YEAR = @ACTUAL_START_YEAR
          
          SELECT @ACTUAL_END_PERIOD = PERIOD_SID
          FROM   PERIOD P1
          WHERE  EXISTS (SELECT 1
			FROM   PROJECTION_MASTER P2
			WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
			AND P1.PERIOD_DATE=CASE WHEN @FREQUENCY = 'QUARTERLY'      THEN (SELECT DATEADD (MM,DATEDIFF(MM,0,DATEADD(DAY, -1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))),0))
		                                     
			WHEN @FREQUENCY = 'SEMI-ANNUALLY' THEN (SELECT DATEADD (MM,DATEDIFF(MM,0,(SELECT CASE WHEN DATEPART(QQ, TO_DATE) = 1 OR DATEPART(QQ, TO_DATE) = 2 THEN   
																																							(SELECT DATEADD(MONTH, -6, DATEADD(DAY, -1, DATEADD(YYYY, DATEDIFF(YYYY, 0, TO_DATE) + 1, 0)))) 
																																								ELSE  (SELECT DATEADD(DAY, -1, DATEADD(YYYY, DATEDIFF(YYYY, 0, TO_DATE) + 1, 0)))
																																							END)),0))
											 
			WHEN @FREQUENCY = 'ANNUALLY'      THEN (SELECT DATEADD (MM,DATEDIFF(MM,0, (SELECT  DATEADD(DAY, -1, DATEADD(YYYY, DATEDIFF(YYYY, 0,TO_DATE) + 1, 0)))),0))
											
			ELSE (SELECT DATEADD (MM,DATEDIFF(MM,0,DATEADD(DAY, -1, DATEADD(MM, DATEDIFF(MM, 0, TO_DATE) + 1, 0))),0))
											 
			END)

          IF ( @MARKET_TYPE = 'MANAGED MEDICAID' )
            BEGIN
                CREATE TABLE #TEMP_ITEM
                  (
                     ITEM_MASTER_SID  INT,
                     PERIOD_SID       INT,
                     PROJECTION_SALES NUMERIC(22, 6),
                     PROJECTION_RATE  NUMERIC(22, 6),
                     PROJECTION_RPU   NUMERIC(22, 6)
                  )

                CREATE TABLE #TEMP_ITEM_ACTUALS
                  (
                     ITEM_MASTER_SID INT,
                     PERIOD_SID      INT,
                     ACTUAL_SALES    NUMERIC(22, 6),
                     ACTUAL_RATE     NUMERIC(22, 6),
                     ACTUAL_RPU      NUMERIC(22, 6)
                  )

                CREATE TABLE #TEMP_CCP
                  (
                     ID              INT IDENTITY (1, 1),
                     ITEM_MASTER_SID INT
                  )

                CREATE TABLE #TEMP_NM
                  (
                     PROJECTION_SALES NUMERIC(22, 6),
                     PROJECTION_RATE  NUMERIC(22, 6),
                     PROJECTION_RPU   NUMERIC(22, 6),
                     SALES            NUMERIC(22, 6),
                     UNITS            NUMERIC(22, 6),
                     PERIOD_SID       INT,
                     TYPE             VARCHAR(20)
                  )

                CREATE TABLE #TEMP_M
                  (
                     PROJECTION_SALES NUMERIC(22, 6),
                     PROJECTION_RATE  NUMERIC(22, 6),
                     PROJECTION_RPU   NUMERIC(22, 6),
                     SALES            NUMERIC(22, 6),
                     UNITS            NUMERIC(22, 6),
                     PERIOD_SID       INT,
                     TYPE             VARCHAR(20)
                  )

                DECLARE @LOOPCOUNTER INT =1
                DECLARE @ROW_COUNT INT
                DECLARE @ITEM INT

             SET @SQL=CONCAT(  ' INSERT INTO #TEMP_CCP
                            (ITEM_MASTER_SID)
                SELECT DISTINCT ITEM_MASTER_SID
                FROM   ',@CCP_HIERARCHY,' A
                       JOIN CCP_DETAILS B
                         ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
               ')
			EXEC sp_executesql @SQL

                SET @ROW_COUNT=@@ROWCOUNT

                WHILE( @LOOPCOUNTER <= @ROW_COUNT )
                  BEGIN
                      SELECT @ITEM = ITEM_MASTER_SID
                      FROM   #TEMP_CCP
                      WHERE  ID = @LOOPCOUNTER

              INSERT INTO #TEMP_ITEM
                                  (ITEM_MASTER_SID,
                                   PERIOD_SID,
                                   PROJECTION_SALES--,
                                  -- PROJECTION_RATE,
                                   --PROJECTION_RPU
								   )
                      SELECT @ITEM,
                             P.PERIOD_SID,
                             SUM(PROJECTION_SALES)--,
                             --AVG(PROJECTION_RATE),
                           --  SUM(PROJECTION_RPU)
                      FROM   [DBO].[NM_DISCOUNT_PROJECTION] B
                             JOIN PROJECTION_DETAILS PROJ
                               ON PROJ.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                             JOIN (SELECT TOP 1 PM.PROJECTION_MASTER_SID
                                   FROM   PROJECTION_MASTER PM
                                          JOIN WORKFLOW_MASTER WM
                                            ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID
                                               AND EXISTS (SELECT HELPER_TABLE_SID
                                                                            FROM   HELPER_TABLE H1
                                                                            WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                                                                   AND DESCRIPTION = 'APPROVED'
																				   AND H1.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID)
                                          JOIN PROJECTION_DETAILS PD
                                            ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
                                          JOIN CCP_DETAILS CCP
                                            ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                               AND CCP.ITEM_MASTER_SID = @ITEM
                                   ORDER  BY WM.MODIFIED_DATE DESC) C
                               ON PROJ.PROJECTION_MASTER_SID = C.PROJECTION_MASTER_SID
                             JOIN DBO.PERIOD P
                               ON P.PERIOD_SID = B.PERIOD_SID
                             JOIN CCP_DETAILS CCP
                               ON CCP.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                             JOIN COMPANY_MASTER CM
                               ON CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID
                             JOIN DBO.HELPER_TABLE H
                               ON CM.COMPANY_CATEGORY = H.HELPER_TABLE_SID
                      WHERE  CCP.ITEM_MASTER_SID = @ITEM
                             AND H.DESCRIPTION IN ( 'MM MEDI', 'MCG PLAN' )
                      GROUP  BY ITEM_MASTER_SID,
                                P.PERIOD_SID

                      INSERT INTO #TEMP_ITEM_ACTUALS
                                  (ITEM_MASTER_SID,
                                   PERIOD_SID,
                                   ACTUAL_SALES--,
                                  -- ACTUAL_RATE,
                                  -- ACTUAL_RPU
								   )
                      SELECT @ITEM,
                             P.PERIOD_SID,
                             SUM(ACTUAL_SALES)--,
                            -- AVG(ACTUAL_RATE),
                            -- SUM(ACTUAL_RATE)
                      FROM   [DBO].[NM_ACTUAL_DISCOUNT] B
                             JOIN PROJECTION_DETAILS PROJ
                               ON PROJ.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                             JOIN (SELECT TOP 1 PM.PROJECTION_MASTER_SID
                                   FROM   PROJECTION_MASTER PM
                                          JOIN WORKFLOW_MASTER WM
                                            ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID
                                               AND EXISTS (SELECT HELPER_TABLE_SID
                                                                            FROM   HELPER_TABLE H1
                                                                            WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                                                                   AND DESCRIPTION = 'APPROVED'
																				   AND H1.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID)
                                          JOIN PROJECTION_DETAILS PD
                                            ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
                                          JOIN CCP_DETAILS CCP
                                            ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                               AND CCP.ITEM_MASTER_SID = @ITEM
                                   ORDER  BY WM.MODIFIED_DATE DESC) C
                               ON PROJ.PROJECTION_MASTER_SID = C.PROJECTION_MASTER_SID
                             JOIN DBO.PERIOD P
                               ON P.PERIOD_SID = B.PERIOD_SID
                             JOIN CCP_DETAILS CCP
                               ON CCP.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                             JOIN COMPANY_MASTER CM
                               ON CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID
                             JOIN DBO.HELPER_TABLE H
                               ON CM.COMPANY_CATEGORY = H.HELPER_TABLE_SID
                      WHERE  CCP.ITEM_MASTER_SID = @ITEM
                             AND H.DESCRIPTION IN ( 'MM MEDI', 'MCG PLAN' )
                      GROUP  BY ITEM_MASTER_SID,
                                P.PERIOD_SID

                      SET @LOOPCOUNTER+=1
                  END

                --- NON MANDATED ----
            SET @SQL=CONCAT(  '  INSERT INTO #TEMP_NM
                            (PROJECTION_SALES,
                             PROJECTION_RATE,
                             PROJECTION_RPU,
                             SALES,
                             UNITS,
                             PERIOD_SID,
                             TYPE)
                SELECT SUM(PROJECTION_SALES)                                            AS PROJECTION_SALES,
                       COALESCE(SUM(PROJECTION_SALES) / NULLIF(SUM(SALES), 0), 0) * 100 AS PROJECTION_RATE,
                       COALESCE(SUM(PROJECTION_SALES) / NULLIF(SUM(UNITS), 0), 0) * 100 AS PROJECTION_RPU,
                       SUM(SALES),
                       SUM(UNITS),
                       PERIOD_SID,
                       TYPE
                FROM   (SELECT PROJECTION_SALES,
                               PROJECTION_RATE,
                               PROJECTION_RPU,
                               SALES,
                               UNITS,
                               PERIOD_SID,
                               TYPE,
                               ITEM_MASTER_SID,
                               BRAND_MASTER_SID,
                               THERAPEUTIC_CLASS
                        FROM   (SELECT SUM(TI.PROJECTION_SALES)                                                   AS PROJECTION_SALES,
                                       COALESCE(SUM(TI.PROJECTION_SALES) / NULLIF(SUM(B.PROJECTION_SALES), 0), 0) AS PROJECTION_RATE,
                                       COALESCE(SUM(TI.PROJECTION_SALES) / NULLIF(SUM(B.PROJECTION_UNITS), 0), 0) AS PROJECTION_RPU,
                                       SUM(B.PROJECTION_SALES)                                                    AS SALES,
                                       SUM(B.PROJECTION_UNITS)                                                    AS UNITS,
                                       PER.PERIOD_SID,
                                       ''PROJECTION''                                                               AS TYPE,
                                       IM.ITEM_MASTER_SID,
                                       IM.BRAND_MASTER_SID,
                                       IM.THERAPEUTIC_CLASS
                                FROM   ',@S_PROJECTION_TABLE,' B
                                        
                                       JOIN DBO.CCP_DETAILS C
                                         ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                       JOIN DBO.ITEM_MASTER IM
                                         ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                                       LEFT JOIN #TEMP_ITEM TI
                                              ON TI.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                                 AND B.PERIOD_SID = TI.PERIOD_SID
                                       JOIN PERIOD PER
                                         ON PER.PERIOD_SID = B.PERIOD_SID
                              
                                GROUP  BY PER.PERIOD_SID,
                                          IM.ITEM_MASTER_SID,
                                          IM.BRAND_MASTER_SID,
                                          IM.THERAPEUTIC_CLASS)A
                        WHERE  ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS
                                 AND @THERAPEUTIC_CLASS IS NOT NULL
                                 AND @BRAND IS NULL )
                                OR ( A.BRAND_MASTER_SID = @BRAND
                                     AND @BRAND IS NOT NULL
                                     AND @THERAPEUTIC_CLASS IS NULL )
                                OR ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS
                                     AND A.BRAND_MASTER_SID = @BRAND )
                                OR ( @THERAPEUTIC_CLASS IS NULL
                                     AND @BRAND IS NULL )
                        UNION ALL
                        SELECT PROJECTION_SALES,
                               PROJECTION_RATE,
                               PROJECTION_RPU,
                               SALES,
                               UNITS,
                               PERIOD_SID,
                               TYPE,
                               ITEM_MASTER_SID,
                               BRAND_MASTER_SID,
                               THERAPEUTIC_CLASS
                        FROM   (SELECT SUM(TI.ACTUAL_SALES)                                  AS PROJECTION_SALES,
                                       SUM(TI.ACTUAL_SALES) / NULLIF(SUM(B.ACTUAL_SALES), 0) AS PROJECTION_RATE,
                                       SUM(TI.ACTUAL_SALES) / NULLIF(SUM(B.ACTUAL_UNITS), 0) AS PROJECTION_RPU,
                                       SUM(B.ACTUAL_SALES)                                   AS SALES,
                                       SUM(B.ACTUAL_UNITS)                                   AS UNITS,
                                       PER.PERIOD_SID,
                                       ''ACTUAL''                                              AS TYPE,
                                       IM.ITEM_MASTER_SID,
                                       IM.BRAND_MASTER_SID,
                                       IM.THERAPEUTIC_CLASS
                                FROM   ',@S_ACTUAL_TABLE,' B
                                         
                                       JOIN DBO.CCP_DETAILS C
                                         ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                       JOIN DBO.ITEM_MASTER IM
                                         ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                                       JOIN DBO.BRAND_MASTER BM
                                         ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID
                                       LEFT JOIN #TEMP_ITEM_ACTUALS TI
                                              ON TI.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                                 AND B.PERIOD_SID = TI.PERIOD_SID
                                       JOIN PERIOD PER
                                         ON PER.PERIOD_SID = B.PERIOD_SID
                               
                                GROUP  BY PER.PERIOD_SID,
                                          IM.ITEM_MASTER_SID,
                                          IM.BRAND_MASTER_SID,
                                          IM.THERAPEUTIC_CLASS)A
                        WHERE  ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS
                                 AND @THERAPEUTIC_CLASS IS NOT NULL
                                 AND @BRAND IS NULL )
                                OR ( A.BRAND_MASTER_SID = @BRAND
                                     AND @BRAND IS NOT NULL
                                     AND @THERAPEUTIC_CLASS IS NULL )
                                OR ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS
                                     AND A.BRAND_MASTER_SID = @BRAND )
                                OR ( @THERAPEUTIC_CLASS IS NULL
                                     AND @BRAND IS NULL ))U
                GROUP  BY PERIOD_SID,
                          TYPE')
	EXEC SP_EXECUTESQL @SQL,N'@THERAPEUTIC_CLASS VARCHAR(100),@BRAND VARCHAR(100)',@THERAPEUTIC_CLASS=@THERAPEUTIC_CLASS,@BRAND=@BRAND


                --- MANDATED ---
              SET @SQL=CONCAT(' INSERT INTO #TEMP_M
                            (PROJECTION_SALES,
                             PROJECTION_RATE,
                             PROJECTION_RPU,
                             SALES,
                             UNITS,
                             PERIOD_SID,
                             TYPE)
                SELECT SUM(PROJECTION_SALES) AS PROJECTION_SALES,
                       AVG(PROJECTION_RATE)  AS PROJECTION_RATE,
                       SUM(PROJECTION_RPU)   AS PROJECTION_RPU,
                       SUM(SALES)            AS SALES,
                       SUM(UNITS)            AS UNITS,
                       PERIOD_SID,
                       TYPE
                FROM   (SELECT ( MANDATED_SALES + ISNULL(SUPP_SALES, 0) ) AS PROJECTION_SALES,
                               ( MANDATED_RATE + ISNULL(SUPP_RATE, 0) )   AS PROJECTION_RATE,
                               ( MANDATED_RPU + ISNULL(SUPP_RPU, 0) )     AS PROJECTION_RPU,
                               SALES,
                               UNITS,
                               PERIOD_SID,
                               TYPE,
                               ITEM_MASTER_SID,
                               BRAND_MASTER_SID,
                               THERAPEUTIC_CLASS
                        FROM   (SELECT SUM(A.PROJECTION_SALES)     AS MANDATED_SALES,
                                       AVG(A.PROJECTION_RATE)      AS MANDATED_RATE,
                                       SUM(A.PROJECTION_RPU)       AS MANDATED_RPU,
                                       SUM(SUPMP.PROJECTION_SALES) AS SUPP_SALES,
                                       AVG(SUPMP.PROJECTION_RATE)  AS SUPP_RATE,
                                       SUM(SUPMP.PROJECTION_RPU)   AS SUPP_RPU,
                                       SUM(SMP.PROJECTION_SALES)   AS SALES,
                                       SUM(SMP.PROJECTION_UNITS)   AS UNITS,
                                       IM.ITEM_MASTER_SID,
                                       IM.BRAND_MASTER_SID,
                                       IM.THERAPEUTIC_CLASS,
                                       P.PERIOD_SID,
                                       ''PROJECTION''                AS TYPE
                                FROM   ',@DISCOUNT_PROJECTION,' A
                                      
                                       JOIN CCP_DETAILS C
                                         ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                       JOIN ITEM_MASTER IM
                                         ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                                      
                                       JOIN ',@S_PROJECTION_TABLE,' SMP
                                         ON SMP.CCP_DETAILS_SID = C.CCP_DETAILS_SID
                                            
                                            AND SMP.PERIOD_SID = A.PERIOD_SID
                                      
                                       LEFT JOIN ',@SUPP_PROJ,' SUPMP
                                              ON SUPMP.CCP_DETAILS_SID = C.CCP_DETAILS_SID
                                                 
                                                 AND SUPMP.PERIOD_SID = A.PERIOD_SID
                                       JOIN PERIOD P
                                         ON P.PERIOD_SID = A.PERIOD_SID
                               
                                GROUP  BY P.PERIOD_SID,
                                          IM.ITEM_MASTER_SID,
                                          IM.BRAND_MASTER_SID,
                                          IM.THERAPEUTIC_CLASS)A
                        WHERE  ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS
                                 AND @THERAPEUTIC_CLASS IS NOT NULL
                                 AND @BRAND IS NULL )
                                OR ( A.BRAND_MASTER_SID = @BRAND
                                     AND @BRAND IS NOT NULL
                                     AND @THERAPEUTIC_CLASS IS NULL )
                                OR ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS
                                     AND A.BRAND_MASTER_SID = @BRAND )
                                OR ( @THERAPEUTIC_CLASS IS NULL
                                     AND @BRAND IS NULL )
                        UNION ALL
                        SELECT ( MANDATED_SALES + ISNULL(SUPP_SALES, 0) ) AS PROJECTION_SALES,
                               ( MANDATED_RATE + ISNULL(SUPP_RATE, 0) )   AS PROJECTION_RATE,
                               ( MANDATED_RPU + ISNULL(SUPP_RPU, 0) )     AS PROJECTION_RPU,
                               SALES,
                               UNITS,
                               PERIOD_SID,
                               TYPE,
                               ITEM_MASTER_SID,
                               BRAND_MASTER_SID,
                               THERAPEUTIC_CLASS
                        FROM   (SELECT SUM(A.ACTUAL_SALES)     AS MANDATED_SALES,
                                       AVG(A.ACTUAL_RATE)      AS MANDATED_RATE,
                                       SUM(A.ACTUAL_RPU)       AS MANDATED_RPU,
                                       SUM(SUPMP.ACTUAL_SALES) AS SUPP_SALES,
                                       AVG(SUPMP.ACTUAL_RATE)  AS SUPP_RATE,
                                       SUM(SUPMP.ACTUAL_RPU)   AS SUPP_RPU,
                                       SUM(SMP.ACTUAL_SALES)   AS SALES,
                                       SUM(SMP.ACTUAL_UNITS)   AS UNITS,
                                       IM.ITEM_MASTER_SID,
                                       IM.BRAND_MASTER_SID,
                                       IM.THERAPEUTIC_CLASS,
                                       P.PERIOD_SID,
                                       ''ACTUAL''                AS TYPE
                                FROM   ',@DISCOUNT_ACTUAL,' A
                                       JOIN CCP_DETAILS C
                                         ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                       JOIN ITEM_MASTER IM
                                         ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                                       
                                       JOIN ',@S_ACTUAL_TABLE,' SMP
                                         ON SMP.CCP_DETAILS_SID = C.CCP_DETAILS_SID
                                           
                                            AND SMP.PERIOD_SID = A.PERIOD_SID
                                      
                                       LEFT JOIN ',@SUPP_ACTUAL,' SUPMP
                                              ON SUPMP.CCP_DETAILS_SID = SMP.CCP_DETAILS_SID
                                                
                                                 AND SUPMP.PERIOD_SID = A.PERIOD_SID
                                       JOIN PERIOD P
                                         ON P.PERIOD_SID = A.PERIOD_SID
                               
                                GROUP  BY P.PERIOD_SID,
                                          IM.ITEM_MASTER_SID,
                                          IM.BRAND_MASTER_SID,
                                          IM.THERAPEUTIC_CLASS)A
                        WHERE  ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS
                                 AND @THERAPEUTIC_CLASS IS NOT NULL
                                 AND @BRAND IS NULL )
                                OR ( A.BRAND_MASTER_SID = @BRAND
                                     AND @BRAND IS NOT NULL
                                     AND @THERAPEUTIC_CLASS IS NULL )
                                OR ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS
                                     AND A.BRAND_MASTER_SID = @BRAND )
                                OR ( @THERAPEUTIC_CLASS IS NULL
                                     AND @BRAND IS NULL ))U
                GROUP  BY PERIOD_SID,
                          TYPE')



EXEC SP_EXECUTESQL @SQL,N'@THERAPEUTIC_CLASS VARCHAR(100),@BRAND VARCHAR(100)',@THERAPEUTIC_CLASS=@THERAPEUTIC_CLASS,@BRAND=@BRAND



                IF( @FREQUENCY = 'QUARTERLY' )
                  BEGIN

                SELECT ISNULL(SUM(A.PROJECTION_SALES), 0)
                             + ISNULL(SUM(B.PROJECTION_SALES), 0)                                                  AS PROJECTION_SALES,
                             COALESCE(( ISNULL(SUM(A.PROJECTION_SALES), 0)
                                        + ISNULL(SUM(B.PROJECTION_SALES), 0) ) / NULLIF(SUM(A.SALES), 0), 0) * 100 AS PROJECTION_RATE,
                             P.QUARTER,
                             P.YEAR,
                             A.TYPE,
                             COALESCE(( ISNULL(SUM(A.PROJECTION_SALES), 0)
                                        + ISNULL(SUM(B.PROJECTION_SALES), 0) ) / NULLIF(SUM(A.UNITS), 0), 0)       AS PROJECTION_RPU
                      FROM   #TEMP_M A
                             JOIN #TEMP_NM B
                               ON A.PERIOD_SID = B.PERIOD_SID
                                  AND A.TYPE = B.TYPE
                             JOIN PERIOD P
                               ON P.PERIOD_SID = A.PERIOD_SID
                      WHERE  P.PERIOD_SID BETWEEN @ACTUAL_START_PERIOD AND @ACTUAL_END_PERIOD
                      GROUP  BY P.QUARTER,
                                P.YEAR,
                                A.TYPE
                      ORDER  BY P.YEAR,
                                P.QUARTER,
                                A.TYPE
                  END
                ELSE IF( @FREQUENCY = 'SEMI-ANNUALLY' )
                  BEGIN

                      SELECT ISNULL(SUM(A.PROJECTION_SALES), 0)
                             + ISNULL(SUM(B.PROJECTION_SALES), 0)                                                  AS PROJECTION_SALES,
                             COALESCE(( ISNULL(SUM(A.PROJECTION_SALES), 0)
                                        + ISNULL(SUM(B.PROJECTION_SALES), 0) ) / NULLIF(SUM(A.SALES), 0), 0) * 100 AS PROJECTION_RATE,
                             P.SEMI_ANNUAL,
                             P.YEAR,
                             A.TYPE,
                             COALESCE(( ISNULL(SUM(A.PROJECTION_SALES), 0)
                                        + ISNULL(SUM(B.PROJECTION_SALES), 0) ) / NULLIF(SUM(A.UNITS), 0), 0)       AS PROJECTION_RPU
                      FROM   #TEMP_M A
                             JOIN #TEMP_NM B
                               ON A.PERIOD_SID = B.PERIOD_SID
                                  AND A.TYPE = B.TYPE
                             JOIN PERIOD P
                               ON P.PERIOD_SID = A.PERIOD_SID
                      WHERE  P.PERIOD_SID BETWEEN @ACTUAL_START_PERIOD AND @ACTUAL_END_PERIOD
                      GROUP  BY P.SEMI_ANNUAL,
                                P.YEAR,
                                A.TYPE
                      ORDER  BY P.YEAR,
                                P.SEMI_ANNUAL,
                                A.TYPE
                  END
                ELSE IF( @FREQUENCY = 'ANNUALLY' )
                  BEGIN

                      SELECT ISNULL(SUM(A.PROJECTION_SALES), 0)
                             + ISNULL(SUM(B.PROJECTION_SALES), 0)                                                  AS PROJECTION_SALES,
                             COALESCE(( ISNULL(SUM(A.PROJECTION_SALES), 0)
                                        + ISNULL(SUM(B.PROJECTION_SALES), 0) ) / NULLIF(SUM(A.SALES), 0), 0) * 100 AS PROJECTION_RATE,
                             P.YEAR,
                             A.TYPE,
                             COALESCE(( ISNULL(SUM(A.PROJECTION_SALES), 0)
                                        + ISNULL(SUM(B.PROJECTION_SALES), 0) ) / NULLIF(SUM(A.UNITS), 0), 0)       AS PROJECTION_RPU
                      FROM   #TEMP_M A
                             JOIN #TEMP_NM B
                               ON A.PERIOD_SID = B.PERIOD_SID
                                  AND A.TYPE = B.TYPE
                             JOIN PERIOD P
                               ON P.PERIOD_SID = A.PERIOD_SID
                      WHERE  P.PERIOD_SID BETWEEN @ACTUAL_START_PERIOD AND @ACTUAL_END_PERIOD
                      GROUP  BY P.YEAR,
                                A.TYPE
                      ORDER  BY P.YEAR,
                                A.TYPE
                  END
                ELSE
                  BEGIN

                      SELECT ISNULL(SUM(A.PROJECTION_SALES), 0)
                             + ISNULL(SUM(B.PROJECTION_SALES), 0)                                                  AS PROJECTION_SALES,
                             COALESCE(( ISNULL(SUM(A.PROJECTION_SALES), 0)
                                        + ISNULL(SUM(B.PROJECTION_SALES), 0) ) / NULLIF(SUM(A.SALES), 0), 0) * 100 AS PROJECTION_RATE,
                             P.MONTH,
                             P.YEAR,
                             A.TYPE,
                             COALESCE(( ISNULL(SUM(A.PROJECTION_SALES), 0)
                                        + ISNULL(SUM(B.PROJECTION_SALES), 0) ) / NULLIF(SUM(A.UNITS), 0), 0)       AS PROJECTION_RPU
                      FROM   #TEMP_M A
                             JOIN #TEMP_NM B
                               ON A.PERIOD_SID = B.PERIOD_SID
                                  AND A.TYPE = B.TYPE
                             JOIN PERIOD P
                               ON P.PERIOD_SID = A.PERIOD_SID
                      WHERE  P.PERIOD_SID BETWEEN @ACTUAL_START_PERIOD AND @ACTUAL_END_PERIOD
                      GROUP  BY P.MONTH,
                                P.YEAR,
                                A.TYPE
                      ORDER  BY P.YEAR,
                                P.MONTH,
                                A.TYPE
                  END
            END
          ELSE
            BEGIN
                IF( @FREQUENCY = 'QUARTERLY' )
                  BEGIN

                SET @SQL=CONCAT('      SELECT MANDATED_SALES,
                             MANDATED_RATE,
                             MANDATED_RPU,
                             SUPP_SALES,
                             SUPP_RATE,
                             SUPP_RPU,
                             PROJECTION_TOTAL_SALES,
                             PROJECTION_TOTAL_RATE,
                             PROJECTION_TOTAL_RPU,
                             QUARTER,
                             YEAR,
                             TYPE
                      FROM   (SELECT MANDATED_SALES,
                                     MANDATED_RATE,
                                     MANDATED_RPU,
                                     SUPP_SALES,
                                     SUPP_RATE,
                                     SUPP_RPU,
                                     ( MANDATED_SALES + ISNULL(SUPP_SALES, 0) ) AS PROJECTION_TOTAL_SALES,
                                     ( MANDATED_RATE + ISNULL(SUPP_RATE, 0) )   AS PROJECTION_TOTAL_RATE,
                                     ( MANDATED_RPU + ISNULL(SUPP_RPU, 0) )     AS PROJECTION_TOTAL_RPU,
                                     QUARTER,
                                     YEAR,
                                     ''PROJECTION''                               AS TYPE
                              FROM   (SELECT SUM(A.PROJECTION_SALES)                                                  AS MANDATED_SALES,
                                             SUM(A.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_SALES), 0) * 100     AS MANDATED_RATE,
                                             SUM(A.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_UNITS), 0)           AS MANDATED_RPU,
                                             SUM(SUPMP.PROJECTION_SALES)                                              AS SUPP_SALES,
                                             SUM(SUPMP.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_SALES), 0) * 100 AS SUPP_RATE,
                                             SUM(SUPMP.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_UNITS), 0)       AS SUPP_RPU,
                                             SUM(SMP.PROJECTION_SALES)                                                AS SALES,
                                             AVG(SMP.PROJECTION_UNITS)                                                AS UNITS,
                                             P.QUARTER,
                                             P.YEAR
                                      FROM   ',@DISCOUNT_PROJECTION,' A
                                             JOIN ',@S_PROJECTION_TABLE,' SMP
                                               ON SMP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                
                                                  AND SMP.PERIOD_SID = A.PERIOD_SID
                                            
                                             LEFT JOIN ',@SUPP_PROJ,' SUPMP
                                                    ON SUPMP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                       
                                                       AND SUPMP.PERIOD_SID = A.PERIOD_SID
                                             JOIN PERIOD P
                                               ON P.PERIOD_SID = A.PERIOD_SID
                                     
                                             AND A.PERIOD_SID BETWEEN ',@ACTUAL_START_PERIOD,' AND ',@ACTUAL_END_PERIOD,'
                                      GROUP  BY P.QUARTER,
                                                P.YEAR)A
                              UNION ALL
                              SELECT MANDATED_SALES,
                                     MANDATED_RATE,
                                     MANDATED_RPU,
                                     SUPP_SALES,
                                     SUPP_RATE,
                                     SUPP_RPU,
                                     ( MANDATED_SALES + ISNULL(SUPP_SALES, 0) ) AS PROJECTION_TOTAL_SALES,
                                     ( MANDATED_RATE + ISNULL(SUPP_RATE, 0) )   AS PROJECTION_TOTAL_RATE,
                                     ( MANDATED_RPU + ISNULL(SUPP_RPU, 0) )     AS PROJECTION_TOTAL_RPU,
                                     QUARTER,
                                     YEAR,
                                     ''ACTUAL''                                   AS TYPE
                              FROM   (SELECT SUM(A.ACTUAL_SALES)                                              AS MANDATED_SALES,
                                             SUM(A.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_SALES), 0) * 100     AS MANDATED_RATE,
                                             SUM(A.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_UNITS), 0)           AS MANDATED_RPU,
                                             SUM(SUPMA.ACTUAL_SALES)                                          AS SUPP_SALES,
                                             SUM(SUPMA.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_SALES), 0) * 100 AS SUPP_RATE,
                                             SUM(SUPMA.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_UNITS), 0)       AS SUPP_RPU,
                                             SUM(SMA.ACTUAL_SALES)                                            AS SALES,
                                             AVG(SMA.ACTUAL_UNITS)                                            AS UNITS,
                                             P.QUARTER,
                                             P.YEAR
                                      FROM   ',@DISCOUNT_ACTUAL,' A
                                             
                                             JOIN ',@S_ACTUAL_TABLE,' SMA
                                               ON SMA.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                
                                                  AND SMA.PERIOD_SID = A.PERIOD_SID
                                            
                                             LEFT JOIN ',@SUPP_ACTUAL,' SUPMA
                                                    ON SUPMA.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                       
                                                       AND SUPMA.PERIOD_SID = A.PERIOD_SID
                                             JOIN PERIOD P
                                               ON P.PERIOD_SID = A.PERIOD_SID
                                      WHERE   A.PERIOD_SID BETWEEN ',@ACTUAL_START_PERIOD,' AND ',@ACTUAL_END_PERIOD,'
                                      GROUP  BY P.QUARTER,
                                                P.YEAR)B)C
                      ORDER  BY C.YEAR,
                                C.QUARTER')
				EXEC sp_executesql @SQL
                  END
                ELSE IF( @FREQUENCY = 'SEMI-ANNUALLY' )
                  BEGIN

                    SET @SQL=CONCAT('      SELECT MANDATED_SALES,
                             MANDATED_RATE,
                             MANDATED_RPU,
                             SUPP_SALES,
                             SUPP_RATE,
                             SUPP_RPU,
                             PROJECTION_TOTAL_SALES,
                             PROJECTION_TOTAL_RATE,
                             PROJECTION_TOTAL_RPU,
                             SEMI_ANNUAL,
                             YEAR,
                             TYPE
                      FROM   (SELECT MANDATED_SALES,
                                     MANDATED_RATE,
                                     MANDATED_RPU,
                                     SUPP_SALES,
                                     SUPP_RATE,
                                     SUPP_RPU,
                                     ( MANDATED_SALES + ISNULL(SUPP_SALES, 0) ) AS PROJECTION_TOTAL_SALES,
                                     ( MANDATED_RATE + ISNULL(SUPP_RATE, 0) )   AS PROJECTION_TOTAL_RATE,
                                     ( MANDATED_RPU + ISNULL(SUPP_RPU, 0) )     AS PROJECTION_TOTAL_RPU,
                                     SEMI_ANNUAL,
                                     YEAR,
                                     ''PROJECTION''                               AS TYPE
                              FROM   (SELECT SUM(A.PROJECTION_SALES)                                                  AS MANDATED_SALES,
                                             SUM(A.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_SALES), 0) * 100     AS MANDATED_RATE,
                                             SUM(A.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_UNITS), 0)           AS MANDATED_RPU,
                                             SUM(SUPMP.PROJECTION_SALES)                                              AS SUPP_SALES,
                                             SUM(SUPMP.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_SALES), 0) * 100 AS SUPP_RATE,
                                             SUM(SUPMP.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_UNITS), 0)       AS SUPP_RPU,
                                             SUM(SMP.PROJECTION_SALES)                                                AS SALES,
                                             AVG(SMP.PROJECTION_UNITS)                                                AS UNITS,
                                             P.SEMI_ANNUAL,
                                             P.YEAR
                                      FROM   ',@DISCOUNT_PROJECTION,' A
                                             JOIN ',@S_PROJECTION_TABLE,'  SMP
                                               ON SMP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                
                                                  AND SMP.PERIOD_SID = A.PERIOD_SID
                                            
                                             LEFT JOIN ',@SUPP_PROJ,' SUPMP
                                                    ON SUPMP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                       
                                                       AND SUPMP.PERIOD_SID = A.PERIOD_SID
                                             JOIN PERIOD P
                                               ON P.PERIOD_SID = A.PERIOD_SID
                                     
                                             AND A.PERIOD_SID BETWEEN ',@ACTUAL_START_PERIOD,' AND ',@ACTUAL_END_PERIOD,'
                                      GROUP  BY P.SEMI_ANNUAL,
                                                P.YEAR)A
                              UNION ALL
                              SELECT MANDATED_SALES,
                                     MANDATED_RATE,
                                     MANDATED_RPU,
                                     SUPP_SALES,
                                     SUPP_RATE,
                                     SUPP_RPU,
                                     ( MANDATED_SALES + ISNULL(SUPP_SALES, 0) ) AS PROJECTION_TOTAL_SALES,
                                     ( MANDATED_RATE + ISNULL(SUPP_RATE, 0) )   AS PROJECTION_TOTAL_RATE,
                                     ( MANDATED_RPU + ISNULL(SUPP_RPU, 0) )     AS PROJECTION_TOTAL_RPU,
                                     SEMI_ANNUAL,
                                     YEAR,
                                     ''ACTUAL''                                   AS TYPE
                              FROM   (SELECT SUM(A.ACTUAL_SALES)                                              AS MANDATED_SALES,
                                             SUM(A.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_SALES), 0) * 100     AS MANDATED_RATE,
                                             SUM(A.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_UNITS), 0)           AS MANDATED_RPU,
                                             SUM(SUPMA.ACTUAL_SALES)                                          AS SUPP_SALES,
                                             SUM(SUPMA.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_SALES), 0) * 100 AS SUPP_RATE,
                                             SUM(SUPMA.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_UNITS), 0)       AS SUPP_RPU,
                                             SUM(SMA.ACTUAL_SALES)                                            AS SALES,
                                             AVG(SMA.ACTUAL_UNITS)                                            AS UNITS,
                                             P.SEMI_ANNUAL,
                                             P.YEAR
                                      FROM   ',@DISCOUNT_ACTUAL,' A
                                             
                                             JOIN ',@S_ACTUAL_TABLE,'  SMA
                                               ON SMA.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                
                                                  AND SMA.PERIOD_SID = A.PERIOD_SID
                                            
                                             LEFT JOIN ',@SUPP_ACTUAL,' SUPMA
                                                    ON SUPMA.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                       
                                                       AND SUPMA.PERIOD_SID = A.PERIOD_SID
                                             JOIN PERIOD P
                                               ON P.PERIOD_SID = A.PERIOD_SID
                                      WHERE   A.PERIOD_SID BETWEEN ',@ACTUAL_START_PERIOD,' AND ',@ACTUAL_END_PERIOD,'
                                      GROUP  BY P.SEMI_ANNUAL,
                                                P.YEAR)B)C
                      ORDER  BY C.YEAR,
                                C.SEMI_ANNUAL')
				EXEC sp_executesql @SQL
                  END
                ELSE IF( @FREQUENCY = 'ANNUALLY' )
                  BEGIN

                     SET @SQL=CONCAT('      SELECT MANDATED_SALES,
                             MANDATED_RATE,
                             MANDATED_RPU,
                             SUPP_SALES,
                             SUPP_RATE,
                             SUPP_RPU,
                             PROJECTION_TOTAL_SALES,
                             PROJECTION_TOTAL_RATE,
                             PROJECTION_TOTAL_RPU,
                            
                             YEAR,
                             TYPE
                      FROM   (SELECT MANDATED_SALES,
                                     MANDATED_RATE,
                                     MANDATED_RPU,
                                     SUPP_SALES,
                                     SUPP_RATE,
                                     SUPP_RPU,
                                     ( MANDATED_SALES + ISNULL(SUPP_SALES, 0) ) AS PROJECTION_TOTAL_SALES,
                                     ( MANDATED_RATE + ISNULL(SUPP_RATE, 0) )   AS PROJECTION_TOTAL_RATE,
                                     ( MANDATED_RPU + ISNULL(SUPP_RPU, 0) )     AS PROJECTION_TOTAL_RPU,
                                     
                                     YEAR,
                                     ''PROJECTION''                               AS TYPE
                              FROM   (SELECT SUM(A.PROJECTION_SALES)                                                  AS MANDATED_SALES,
                                             SUM(A.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_SALES), 0) * 100     AS MANDATED_RATE,
                                             SUM(A.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_UNITS), 0)           AS MANDATED_RPU,
                                             SUM(SUPMP.PROJECTION_SALES)                                              AS SUPP_SALES,
                                             SUM(SUPMP.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_SALES), 0) * 100 AS SUPP_RATE,
                                             SUM(SUPMP.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_UNITS), 0)       AS SUPP_RPU,
                                             SUM(SMP.PROJECTION_SALES)                                                AS SALES,
                                             AVG(SMP.PROJECTION_UNITS)                                                AS UNITS,
                                            
                                             P.YEAR
                                      FROM   ',@DISCOUNT_PROJECTION,' A
                                             JOIN ',@S_PROJECTION_TABLE,'  SMP
                                               ON SMP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                
                                                  AND SMP.PERIOD_SID = A.PERIOD_SID
                                            
                                             LEFT JOIN ',@SUPP_PROJ,' SUPMP
                                                    ON SUPMP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                       
                                                       AND SUPMP.PERIOD_SID = A.PERIOD_SID
                                             JOIN PERIOD P
                                               ON P.PERIOD_SID = A.PERIOD_SID
                                     
                                             AND A.PERIOD_SID BETWEEN ',@ACTUAL_START_PERIOD,' AND ',@ACTUAL_END_PERIOD,'
                                      GROUP  BY 
                                                P.YEAR)A
                              UNION ALL
                              SELECT MANDATED_SALES,
                                     MANDATED_RATE,
                                     MANDATED_RPU,
                                     SUPP_SALES,
                                     SUPP_RATE,
                                     SUPP_RPU,
                                     ( MANDATED_SALES + ISNULL(SUPP_SALES, 0) ) AS PROJECTION_TOTAL_SALES,
                                     ( MANDATED_RATE + ISNULL(SUPP_RATE, 0) )   AS PROJECTION_TOTAL_RATE,
                                     ( MANDATED_RPU + ISNULL(SUPP_RPU, 0) )     AS PROJECTION_TOTAL_RPU,
                                     
                                     YEAR,
                                     ''ACTUAL''                                   AS TYPE
                              FROM   (SELECT SUM(A.ACTUAL_SALES)                                              AS MANDATED_SALES,
                                             SUM(A.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_SALES), 0) * 100     AS MANDATED_RATE,
                                             SUM(A.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_UNITS), 0)           AS MANDATED_RPU,
                                             SUM(SUPMA.ACTUAL_SALES)                                          AS SUPP_SALES,
                                             SUM(SUPMA.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_SALES), 0) * 100 AS SUPP_RATE,
                                             SUM(SUPMA.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_UNITS), 0)       AS SUPP_RPU,
                                             SUM(SMA.ACTUAL_SALES)                                            AS SALES,
                                             AVG(SMA.ACTUAL_UNITS)                                            AS UNITS,
                                            
                                             P.YEAR
                                      FROM   ',@DISCOUNT_ACTUAL,' A
                                             
                                             JOIN ',@S_ACTUAL_TABLE,'  SMA
                                               ON SMA.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                
                                                  AND SMA.PERIOD_SID = A.PERIOD_SID
                                            
                                             LEFT JOIN ',@SUPP_ACTUAL,' SUPMA
                                                    ON SUPMA.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                       
                                                       AND SUPMA.PERIOD_SID = A.PERIOD_SID
                                             JOIN PERIOD P
                                               ON P.PERIOD_SID = A.PERIOD_SID
                                      WHERE   A.PERIOD_SID BETWEEN ',@ACTUAL_START_PERIOD,' AND ',@ACTUAL_END_PERIOD,'
                                      GROUP  BY 
                                                P.YEAR)B)C
                      ORDER  BY C.YEAR')
				EXEC sp_executesql @SQL
                  END
                ELSE
                  BEGIN

                      SET @SQL=CONCAT('      SELECT MANDATED_SALES,
                             MANDATED_RATE,
                             MANDATED_RPU,
                             SUPP_SALES,
                             SUPP_RATE,
                             SUPP_RPU,
                             PROJECTION_TOTAL_SALES,
                             PROJECTION_TOTAL_RATE,
                             PROJECTION_TOTAL_RPU,
                             MONTH,
                             YEAR,
                             TYPE
                      FROM   (SELECT MANDATED_SALES,
                                     MANDATED_RATE,
                                     MANDATED_RPU,
                                     SUPP_SALES,
                                     SUPP_RATE,
                                     SUPP_RPU,
                                     ( MANDATED_SALES + ISNULL(SUPP_SALES, 0) ) AS PROJECTION_TOTAL_SALES,
                                     ( MANDATED_RATE + ISNULL(SUPP_RATE, 0) )   AS PROJECTION_TOTAL_RATE,
                                     ( MANDATED_RPU + ISNULL(SUPP_RPU, 0) )     AS PROJECTION_TOTAL_RPU,
                                     MONTH,
                                     YEAR,
                                     ''PROJECTION''                               AS TYPE
                              FROM   (SELECT SUM(A.PROJECTION_SALES)                                                  AS MANDATED_SALES,
                                             SUM(A.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_SALES), 0) * 100     AS MANDATED_RATE,
                                             SUM(A.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_UNITS), 0)           AS MANDATED_RPU,
                                             SUM(SUPMP.PROJECTION_SALES)                                              AS SUPP_SALES,
                                             SUM(SUPMP.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_SALES), 0) * 100 AS SUPP_RATE,
                                             SUM(SUPMP.PROJECTION_SALES) / NULLIF(SUM(SMP.PROJECTION_UNITS), 0)       AS SUPP_RPU,
                                             SUM(SMP.PROJECTION_SALES)                                                AS SALES,
                                             AVG(SMP.PROJECTION_UNITS)                                                AS UNITS,
                                             P.MONTH,
                                             P.YEAR
                                      FROM   ',@DISCOUNT_PROJECTION,' A
                                             JOIN ',@S_PROJECTION_TABLE,'  SMP
                                               ON SMP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                
                                                  AND SMP.PERIOD_SID = A.PERIOD_SID
                                            
                                             LEFT JOIN ',@SUPP_PROJ,' SUPMP
                                                    ON SUPMP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                       
                                                       AND SUPMP.PERIOD_SID = A.PERIOD_SID
                                             JOIN PERIOD P
                                               ON P.PERIOD_SID = A.PERIOD_SID
                                     
                                             AND A.PERIOD_SID BETWEEN ',@ACTUAL_START_PERIOD,' AND ',@ACTUAL_END_PERIOD,'
                                      GROUP  BY P.MONTH,
                                                P.YEAR)A
                              UNION ALL
                              SELECT MANDATED_SALES,
                                     MANDATED_RATE,
                                     MANDATED_RPU,
                                     SUPP_SALES,
                                     SUPP_RATE,
                                     SUPP_RPU,
                                     ( MANDATED_SALES + ISNULL(SUPP_SALES, 0) ) AS PROJECTION_TOTAL_SALES,
                                     ( MANDATED_RATE + ISNULL(SUPP_RATE, 0) )   AS PROJECTION_TOTAL_RATE,
                                     ( MANDATED_RPU + ISNULL(SUPP_RPU, 0) )     AS PROJECTION_TOTAL_RPU,
                                     MONTH,
                                     YEAR,
                                     ''ACTUAL''                                   AS TYPE
                              FROM   (SELECT SUM(A.ACTUAL_SALES)                                              AS MANDATED_SALES,
                                             SUM(A.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_SALES), 0) * 100     AS MANDATED_RATE,
                                             SUM(A.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_UNITS), 0)           AS MANDATED_RPU,
                                             SUM(SUPMA.ACTUAL_SALES)                                          AS SUPP_SALES,
                                             SUM(SUPMA.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_SALES), 0) * 100 AS SUPP_RATE,
                                             SUM(SUPMA.ACTUAL_SALES) / NULLIF(SUM(SMA.ACTUAL_UNITS), 0)       AS SUPP_RPU,
                                             SUM(SMA.ACTUAL_SALES)                                            AS SALES,
                                             AVG(SMA.ACTUAL_UNITS)                                            AS UNITS,
                                             P.MONTH,
                                             P.YEAR
                                      FROM   ',@DISCOUNT_ACTUAL,' A
                                             
                                             JOIN ',@S_ACTUAL_TABLE,'  SMA
                                               ON SMA.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                
                                                  AND SMA.PERIOD_SID = A.PERIOD_SID
                                            
                                             LEFT JOIN ',@SUPP_ACTUAL,' SUPMA
                                                    ON SUPMA.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                       
                                                       AND SUPMA.PERIOD_SID = A.PERIOD_SID
                                             JOIN PERIOD P
                                               ON P.PERIOD_SID = A.PERIOD_SID
                                      WHERE   A.PERIOD_SID BETWEEN ',@ACTUAL_START_PERIOD,' AND ',@ACTUAL_END_PERIOD,'
                                      GROUP  BY P.MONTH,
                                                P.YEAR)B)C
                      ORDER  BY C.YEAR,
                                C.MONTH')
				EXEC sp_executesql @SQL
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