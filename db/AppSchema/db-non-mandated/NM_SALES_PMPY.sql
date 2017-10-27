IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'NM_SALES_PMPY'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[NM_SALES_PMPY]
  END

GO

CREATE PROCEDURE [dbo].[NM_SALES_PMPY](@PROJECTION_MASTER_SID  INT,
                                       @PROJECTION_DETAILS_SID VARCHAR(4000),
                                       @CONTRACT_HOLDER_SID    INT,
                                       @TRADING_PARTNER_SID    INT)
AS
  BEGIN
      SET NOCOUNT ON
      DECLARE @ACTUALS_CCP UDT_CCP_DETAILS
      DECLARE @FROM_DATE DATETIME = Dateadd(yy, Datediff(yy, 0, Getdate()) - 3, 0)
      DECLARE @START_DATE DATETIME = Dateadd(qq, Datediff(qq, 0, Getdate()), 0) - 1 --LAST DAY of the Previous Quarter
      DECLARE @CNT_PROJ_DETAILS_SID INT = 0

      BEGIN TRY
          ---------------------Fetch Contract,Company,Item for the given PROJECTION_DETAILS_SID(s)------------------------------
          SELECT @CNT_PROJ_DETAILS_SID = Count(token)
          FROM   DBO.Udf_splitstring(@PROJECTION_DETAILS_SID, ','); --Count of PROJECTION_DETAILS_SID

          IF Object_id('tempdb..#TEMP_PMPY') IS NOT NULL
            DROP TABLE #TEMP_PMPY

          CREATE TABLE #TEMP_PMPY
            (
               SALES    NUMERIC(22, 6),
               QUANTITY NUMERIC(22, 6),
               LIVES    NUMERIC(22, 6),
               QUARTER  INT,
               YEAR     INT
            )

          INSERT INTO #TEMP_PMPY
                      (QUARTER,
                       YEAR)
          SELECT DISTINCT QUARTER,
                          YEAR
          FROM   PERIOD
          WHERE  PERIOD_DATE BETWEEN @FROM_DATE AND @START_DATE

          -------------------------------------------------For Alternate Contract_Holder starts here---------------------------------------
          IF @CONTRACT_HOLDER_SID <> 0
            BEGIN ;
                WITH MYCTE1(PROJECTION_DETAILS_SID)
                     AS (SELECT token
                         FROM   DBO.Udf_splitstring(@PROJECTION_DETAILS_SID, ',')),
                     MYCTE2 --fetch existing company & items
                     AS (SELECT CCP.COMPANY_MASTER_SID,
                                CCP.ITEM_MASTER_SID
                         FROM   CCP_DETAILS CCP
                                JOIN PROJECTION_DETAILS PD
                                  ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                JOIN MYCTE1 C1
                                  ON PD.PROJECTION_DETAILS_SID = C1.PROJECTION_DETAILS_SID
                         WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID),
                     MYCTE3
                     AS (SELECT CCP.CONTRACT_MASTER_SID,
                                CCP.COMPANY_MASTER_SID,
                                CCP.ITEM_MASTER_SID
                         FROM   CCP_DETAILS CCP
                                JOIN MYCTE2 C2
                                  ON CCP.COMPANY_MASTER_SID = C2.COMPANY_MASTER_SID --existing company
                                     AND CCP.ITEM_MASTER_SID = C2.ITEM_MASTER_SID --existing item
									 AND EXISTS (SELECT CONTRACT_MASTER_SID
                                                                    FROM   CONTRACT_MASTER CON
                                                                    WHERE  CONT_HOLD_COMPANY_MASTER_SID = @CONTRACT_HOLDER_SID
																	AND CON.CONTRACT_MASTER_SID=CCP.CONTRACT_MASTER_SID)
                                     --AND CCP.CONTRACT_MASTER_SID IN(SELECT CONTRACT_MASTER_SID
                                     --                               FROM   CONTRACT_MASTER
                                     --                               WHERE  CONT_HOLD_COMPANY_MASTER_SID = @CONTRACT_HOLDER_SID) --new contract
                        )
                INSERT INTO @ACTUALS_CCP
                            (CONTRACT_MASTER_SID,
                             COMPANY_MASTER_SID,
                             ITEM_MASTER_SID)
                SELECT CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID
                FROM   MYCTE3
            END
          -------------------------------------------------For Alternate Contract_Holder ends here---------------------------------------
          -----------------------------------------------For Alternate Trading_Partner starts here---------------------------------------
          ELSE
            BEGIN ;
                WITH MYCTE1(PROJECTION_DETAILS_SID)
                     AS (SELECT token
                         FROM   DBO.Udf_splitstring(@PROJECTION_DETAILS_SID, ',')),
                     MYCTE2 --fetch existing contract & items
                     AS (SELECT CCP.CONTRACT_MASTER_SID,
                                CCP.ITEM_MASTER_SID
                         FROM   CCP_DETAILS CCP
                                JOIN PROJECTION_DETAILS PD
                                  ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                JOIN MYCTE1 C1
                                  ON PD.PROJECTION_DETAILS_SID = C1.PROJECTION_DETAILS_SID
                         WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID),
                     MYCTE3
                     AS (SELECT CCP.CONTRACT_MASTER_SID,
                                CCP.COMPANY_MASTER_SID,
                                CCP.ITEM_MASTER_SID
                         FROM   CCP_DETAILS CCP
                                JOIN MYCTE2 C2
                                  ON CCP.CONTRACT_MASTER_SID = C2.CONTRACT_MASTER_SID --existing contract
                                     AND CCP.ITEM_MASTER_SID = C2.ITEM_MASTER_SID --existing item
                                     AND CCP.COMPANY_MASTER_SID = @TRADING_PARTNER_SID --new company
                        )
                INSERT INTO @ACTUALS_CCP
                            (CONTRACT_MASTER_SID,
                             COMPANY_MASTER_SID,
                             ITEM_MASTER_SID)
                SELECT CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID
                FROM   MYCTE3
            END
          -----------------------------------------------For Alternate Trading_Partner ends here---------------------------------------
          ------------------------Covered Lives Quarterly wise data for all Company(s) in UDT + Actuals starts here---------------------
          ;

          WITH mycte1
               AS (SELECT DISTINCT b.COMPANY_NO,
                                   b.COMPANY_ID
                   FROM   @ACTUALS_CCP a
                          JOIN COMPANY_MASTER b
                            ON a.COMPANY_MASTER_SID = b.COMPANY_MASTER_SID),
               MYCTE2
               AS (SELECT cast(MD.COLUMN_3 as numeric(22,6) )                        AS LIVES,
                          P.QUARTER,
                          P.YEAR,
                          Row_number()
                            OVER(
                              PARTITION BY MD.MASTER_ID, MD.MASTER_ATTRIBUTE, MD.MASTER_TYPE, P.QUARTER, P.YEAR
                              ORDER BY MD.MODIFIED_DATE DESC) AS RN ----to fetch the latest among two records if overlap exists
                   FROM   MASTER_DATA_ATTRIBUTE md
                          JOIN mycte1 c1
                            ON md.MASTER_ID = c1.COMPANY_NO
                               AND md.MASTER_ATTRIBUTE = 'COV_LIVES'
                               AND md.MASTER_TYPE = 'COMPANY_MASTER'
                          JOIN PERIOD P
                            ON ( Datepart(QQ, Cast(MD.COLUMN_1 AS DATETIME)) = P.QUARTER
                                 AND Year(Cast(MD.COLUMN_1 AS DATETIME)) = P.YEAR )
                               AND ( (( Datepart(QQ, Cast(MD.COLUMN_2 AS DATETIME)) = P.QUARTER
                                        AND Year(Cast(MD.COLUMN_2 AS DATETIME)) = P.YEAR ))
                                      OR Isnull(Cast(MD.COLUMN_2 AS DATETIME), 1) = 1 )
                   WHERE  P.PERIOD_DATE BETWEEN @FROM_DATE AND @START_DATE)
          UPDATE A
          SET    A.LIVES = B.SM_LIVES,
                 A.SALES = C.SALES,
                 A.QUANTITY = C.QUANTITY
          FROM   #TEMP_PMPY A
                 LEFT JOIN (SELECT Sum(LIVES) AS SM_LIVES,
                                   QUARTER,
                                   YEAR
                            FROM   MYCTE2
                            WHERE  RN = 1 --to fetch the latest among two records if overlap exists
                            GROUP  BY QUARTER,
                                      YEAR)B
                        ON A.QUARTER = B.QUARTER
                           AND A.YEAR = B.YEAR
                 LEFT JOIN (SELECT Sum(SALES) / NULLIF(@CNT_PROJ_DETAILS_SID, 0)    AS SALES,---ACTUALS CALCULATION STARTS HERE                  
                                   Sum(QUANTITY) / NULLIF(@CNT_PROJ_DETAILS_SID, 0) AS QUANTITY,
                                   YEAR,
                                   QUARTER
                            FROM   Udf_actuals_details(@ACTUALS_CCP, @START_DATE, @FROM_DATE)
                            WHERE  QUANTITY_INCLUSION = 'Y'
                            GROUP  BY YEAR,
                                      QUARTER)C
                        ON A.QUARTER = C.QUARTER
                           AND A.YEAR = C.YEAR

				SELECT QUARTER,
					   YEAR,
					   SALES,
					   QUANTITY,
					   LIVES
				FROM   #TEMP_PMPY 
          -----------------------Covered Lives Quarterly wise data for all Company(s) in UDT + Actuals ends here---------------------
          /*
          
                    INSERT INTO #TEMP_PMPY
                                (SALES,
                                 QUANTITY,
                                 QUARTER,
                                 YEAR,
                                 LIVES)
                    SELECT COALESCE(ACT.SALES, 0),
                           COALESCE(ACT.QUANTITY, 0),
                           ACT.QUARTER,
                           ACT.YEAR,
                           COALESCE(LVS.column_3, 0) AS LIVES
                    FROM   #TEMP_PMPY tmp
                           LEFT JOIN (SELECT Sum(SALES)    AS SALES,---Actuals Calculation starts Here                  
                                             Sum(QUANTITY) AS QUANTITY,
                                             COMPANY_MASTER_SID,
                                             year,
                                             QUARTER
                                      FROM   Udf_actuals_details(@ACTUALS_CCP, @START_DATE, @FROM_DATE)
                                      WHERE  QUANTITY_INCLUSION = 'Y'
                                      GROUP  BY year,
                                                QUARTER,
          									  CONTRACT_MASTER_SID,
                                                COMPANY_MASTER_SID,
          									  ITEM_MASTER_SID) ACT ---Actuals Ends Here
                                  ON TMP.QUARTER = ACT.QUARTER
                                     AND TMP.YEAR = ACT.YEAR
                           LEFT JOIN (SELECT cm.COMPANY_MASTER_SID,-------Covered Lives Calculation Starts Here-------
                                             md.MASTER_ID,
                                             md.column_3,
                                             op.QUARTER,
                                             op.YEAR
                                      FROM   MASTER_DATA_ATTRIBUTE md
                                             JOIN (SELECT Max(modified_date) AS max_date,
                                                          p.QUARTER,
                                                          p.year,
                                                          m.master_id
                                                   FROM   master_data_attribute m
                                                          JOIN Period p
                                                            ON Year(Cast(m.column_1 AS DATETIME)) = Year(p.PERIOD_DATE)
                                                               AND P.PERIOD_DATE >= @FROM_DATE
                                                               AND P.PERIOD_DATE < @START_DATE
                                                   WHERE  m.MASTER_ATTRIBUTE = 'COV_LIVES'
                                                          AND m.MASTER_TYPE = 'COMPANY_MASTER'
                                                          AND ( Cast(m.COLUMN_1 AS DATETIME) < p.PERIOD_DATE
                                                                 OR Cast(m.COLUMN_1 AS DATETIME) = p.PERIOD_DATE )
                                                          AND ( ( Isnull(Cast(m.COLUMN_2 AS DATETIME), 1) = 1 )
                                                                 OR ( Cast(m.COLUMN_2 AS DATETIME) > Dateadd (dd, -1, Dateadd(qq, Datediff(qq, 0, p.PERIOD_DATE) + 1, 0)) )
                                                                 OR ( Cast(m.COLUMN_2 AS DATETIME) = Dateadd (dd, -1, Dateadd(qq, Datediff(qq, 0, p.PERIOD_DATE) + 1, 0)) ) ) --NULL Handling
                                                   GROUP  BY p.QUARTER,
                                                             p.year,
                                                             m.master_id) op
                                               ON md.MODIFIED_DATE = op.max_date
                                                  AND md.master_id = op.master_id
                                                  AND Year(Cast(md.column_1 AS DATETIME)) = op.year
                                                  AND md.MASTER_ATTRIBUTE = 'COV_LIVES'
                                                  AND md.MASTER_TYPE = 'COMPANY_MASTER'
                                             JOIN company_master cm
                                               ON cm.company_no = md.master_id)LVS -----Lives ends Here  
                                  ON LVS.COMPANY_MASTER_SID = ACT.COMPANY_MASTER_SID
                                     AND LVS.YEAR = ACT.YEAR
                                     AND LVS.QUARTER = ACT.QUARTER
          */
          
      END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC Usperrorcollector

          SELECT @ERRORMESSAGE = Error_message(),
                 @ERRORSEVERITY = Error_severity(),
                 @ERRORSTATE = Error_state(),
                 @ERRORPROCEDURE = Error_procedure(),
                 @ERRORLINE = Error_line(),
                 @ERRORNUMBER = Error_number()

          RAISERROR (@ERRORMESSAGE,-- MESSAGE TEXT.
                     @ERRORSEVERITY,-- SEVERITY.
                     @ERRORSTATE,-- STATE.
                     @ERRORPROCEDURE,-- PROCEDURE_NAME.
                     @ERRORNUMBER,-- ERRORNUMBER
                     @ERRORLINE -- ERRORLINE
          )
      END CATCH
  END