IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_DEDUCTION_CALENDAR_ADJUSTMENT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_DEDUCTION_CALENDAR_ADJUSTMENT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_DEDUCTION_CALENDAR_ADJUSTMENT] (@SELECTED_PERIODS                  NVARCHAR(MAX),
                                                            @USER_ID                           INT,
                                                            @SESSION_ID                        VARCHAR(100),
                                                            @ADJUSTMENT_TYPE                   VARCHAR(100),
                                                            @ADJUSTMENT_VALUE                  NUMERIC(22, 6),
                                                            @ADJUSTMENT_BASIS                  VARCHAR(100),
                                                            @ADJUSTMENT_VARIABLE               VARCHAR(100),
                                                            @ADJUSTMENT_ALLOCATION_METHODOLOGY VARCHAR(100))
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          DECLARE @DEDUCTION_TABLE VARCHAR(200) = Concat('ST_DEDUCTION_CALENDAR_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          IF Object_id('TEMPDB.DBO.#ST_DEDUCTION_CALENDAR_DETAILS', 'U') IS NOT NULL
            DROP TABLE #ST_DEDUCTION_CALENDAR_DETAILS;
 
          CREATE TABLE  #ST_DEDUCTION_CALENDAR_DETAILS
          (
         
         CP_DETAILS_SID INT,
         YEAR INT,
         MONTH INT,
         PERIOD_SID INT,
         DISCOUNT_AMOUNT NUMERIC(22,8),
         CHECK_RECORD bit
         )



      ;DECLARE @SQL1 NVARCHAR(MAX)=''
            SET @SQL1=CONCAT('WITH CTE
         AS (SELECT CP_DETAILS_SID,
                          YEAR,
                          MONTH,
                          DISCOUNT_AMOUNT,
                          CHECK_RECORD
                   FROM   (SELECT *
                           FROM   ',@DEDUCTION_TABLE,'
                          )ST
                          CROSS APPLY ( VALUES (JAN_DISCOUNT_AMOUNT,
                                      1),
                                               (FEB_DISCOUNT_AMOUNT,
                                      2),
                                               (MAR_DISCOUNT_AMOUNT,
                                      3),
                                               (APR_DISCOUNT_AMOUNT,
                                      4),
                                               (MAY_DISCOUNT_AMOUNT,
                                      5),
                                               (JUN_DISCOUNT_AMOUNT,
                                      6),
                                               (JUL_DISCOUNT_AMOUNT,
                                      7),
                                               (AUG_DISCOUNT_AMOUNT,
                                      8),
                                               (SEP_DISCOUNT_AMOUNT,
                                      9),
                                               (OCT_DISCOUNT_AMOUNT,
                                      10),
                                               (NOV_DISCOUNT_AMOUNT,
                                      11),
                                               (DEC_DISCOUNT_AMOUNT,
                                      12) ) X(DISCOUNT_AMOUNT, MONTH))
   
             INSERT INTO  #ST_DEDUCTION_CALENDAR_DETAILS (CP_DETAILS_SID,
                 P.YEAR,
                 P.MONTH,
                 P.PERIOD_SID,
                 DISCOUNT_AMOUNT,
                 CHECK_RECORD)
				 
          SELECT CP_DETAILS_SID,
                 P.YEAR,
                 P.MONTH,
                 P.PERIOD_SID,
                 DISCOUNT_AMOUNT,
                 CHECK_RECORD
          FROM   CTE ST
                 INNER JOIN PERIOD P
                         ON P.YEAR = ST.YEAR
                            AND P.MONTH = ST.MONTH','')
 
             EXEC sp_executesql @SQL1
			 --------------------------

                     ---------------table create
          -------------------------------------
          IF Object_id('TEMPDB.DBO.#PERCENTAGE_DISCOUNT', 'U') IS NOT NULL
            DROP TABLE #PERCENTAGE_DISCOUNT;
 
          WITH CTE
               AS (SELECT CP_DETAILS_SID,
                          DISCOUNT_AMOUNT,
                          STDCD.MONTH,
                          P.YEAR,
                          P.PERIOD_SID,
                          PERIODS,
                          CHECK_RECORD
                   FROM   #ST_DEDUCTION_CALENDAR_DETAILS STDCD
                          INNER JOIN (SELECT PERIOD_SID,
                                             YEAR,
                                             'M' + Cast(MONTH AS VARCHAR(3)) + ' '
                                             + Cast(YEAR AS VARCHAR(4)) PERIODS
                                      FROM   PERIOD) P
                                  ON STDCD.PERIOD_SID = P.PERIOD_SID
                   WHERE  EXISTS (SELECT 1
                                  FROM   UDF_SPLITSTRING(@SELECTED_PERIODS, ',') A
                                  WHERE  A.TOKEN = P.PERIODS)
                        ),
               CTE1
               AS (SELECT CP_DETAILS_SID,
                          YEAR,
                          DISCOUNT_AMOUNT,
                          Sum(CONVERT(NUMERIC(22, 6), DISCOUNT_AMOUNT))
                            OVER(
                              PARTITION BY PERIOD_SID) PERIOD_DISCOUNT_AMOUNT,
                          Sum(CONVERT(NUMERIC(22, 6), DISCOUNT_AMOUNT))
                            OVER()                     TOTAL_DISCOUNT_AMOUNT,
                          PERIOD_SID,
                          PERIODS
                   FROM   CTE)
          SELECT C.CP_DETAILS_SID,
                 C.DISCOUNT_AMOUNT,
                 TOTAL_DISCOUNT_AMOUNT,
                 PERIOD_DISCOUNT_AMOUNT,
                 ( C1.PERIOD_DISCOUNT_AMOUNT * 100 ) / NULLIF(TOTAL_DISCOUNT_AMOUNT, 0) TOTAL_DISCOUNT_PERCENT,
                 ( C1.DISCOUNT_AMOUNT * 100 ) / NULLIF(PERIOD_DISCOUNT_AMOUNT, 0)       PERIOD_DISCOUNT_PERCENT,
                 C.YEAR,
                 C.MONTH,
                 C1.PERIOD_SID,
                 C1.PERIODS,
                 CHECK_RECORD
          INTO   #PERCENTAGE_DISCOUNT
          FROM   CTE C
                 INNER JOIN CTE1 C1
                         ON C.CP_DETAILS_SID = C1.CP_DETAILS_SID
                            AND C.PERIOD_SID = C1.PERIOD_SID
                            AND C.YEAR = C1.YEAR
 
          -----------------------------------------------------------------------
          IF Object_id('TEMPDB.DBO.#TEMP_FINAL_SALES', 'U') IS NOT NULL
            DROP TABLE #TEMP_FINAL_SALES;
 
      ;
          WITH CTE
               AS (SELECT CP_DETAILS_SID,
                          PERIOD_SID,
                          YEAR,
                          PERIODS,
                          MONTH,
                          DISCOUNT_AMOUNT,
                          PERIOD_DISCOUNT_PERCENT,
                          TOTAL_DISCOUNT_PERCENT,
                          CHECK_RECORD,
                          CASE
                            WHEN @ADJUSTMENT_BASIS = 'AMOUNT' THEN ( @ADJUSTMENT_VALUE / 100 ) * TOTAL_DISCOUNT_PERCENT
                            ELSE ( @ADJUSTMENT_VALUE / 100 ) * TOTAL_DISCOUNT_PERCENT
                          END ADJUSTMENT_VALUE
                   FROM   #PERCENTAGE_DISCOUNT
                   WHERE  @ADJUSTMENT_TYPE IN ( 'OVERRIDE', 'INCREMENTAL' ))
          SELECT CP_DETAILS_SID,
                 PERIOD_SID,
                 PERIODS,
                 YEAR,
                 MONTH,
                 ADJUSTMENT_VALUE,
                 DISCOUNT_AMOUNT,
                 PERIOD_DISCOUNT_PERCENT,
                 TOTAL_DISCOUNT_PERCENT,
                 CASE
                   WHEN @ADJUSTMENT_TYPE = 'OVERRIDE' THEN (( ( PERIOD_DISCOUNT_PERCENT / 100 ) * ADJUSTMENT_VALUE ))
                   ELSE ( ISNULL(DISCOUNT_AMOUNT, 0) + ( ( PERIOD_DISCOUNT_PERCENT / 100.0 ) * ADJUSTMENT_VALUE ) )
                 END CAL_DISCOUNT_AMT,
                 CHECK_RECORD
          INTO   #TEMP_FINAL_SALES
          FROM   CTE
 
              SET @SQL1=CONCAT('
                UPDATE  STDCD
          SET    STDCD.JAN_DISCOUNT_AMOUNT =IIF(A.JAN_DISCOUNT_AMOUNT IS  NOT NULL,A.JAN_DISCOUNT_AMOUNT,STDCD.JAN_DISCOUNT_AMOUNT),
                 STDCD.FEB_DISCOUNT_AMOUNT = IIF(A.FEB_DISCOUNT_AMOUNT IS NOT NULL,A.FEB_DISCOUNT_AMOUNT,STDCD.FEB_DISCOUNT_AMOUNT),
                 STDCD.MAR_DISCOUNT_AMOUNT = IIF(A.MAR_DISCOUNT_AMOUNT IS NOT NULL,A.MAR_DISCOUNT_AMOUNT,STDCD.MAR_DISCOUNT_AMOUNT) ,
                 STDCD.APR_DISCOUNT_AMOUNT = IIF(A.APR_DISCOUNT_AMOUNT IS NOT NULL,A.APR_DISCOUNT_AMOUNT,STDCD.APR_DISCOUNT_AMOUNT) ,
                 STDCD.MAY_DISCOUNT_AMOUNT = IIF(A.MAY_DISCOUNT_AMOUNT IS NOT NULL,A.MAY_DISCOUNT_AMOUNT,STDCD.MAY_DISCOUNT_AMOUNT),
                 STDCD.JUN_DISCOUNT_AMOUNT = IIF(A.JUN_DISCOUNT_AMOUNT IS NOT NULL,A.JUN_DISCOUNT_AMOUNT,STDCD.JUN_DISCOUNT_AMOUNT) ,
                 STDCD.JUL_DISCOUNT_AMOUNT = IIF(A.JUL_DISCOUNT_AMOUNT IS NOT NULL,A.JUL_DISCOUNT_AMOUNT,STDCD.JUL_DISCOUNT_AMOUNT) ,
                 STDCD.AUG_DISCOUNT_AMOUNT = IIF(A.AUG_DISCOUNT_AMOUNT IS NOT NULL,A.AUG_DISCOUNT_AMOUNT,STDCD.AUG_DISCOUNT_AMOUNT) ,
                 STDCD.SEP_DISCOUNT_AMOUNT = IIF(A.SEP_DISCOUNT_AMOUNT IS NOT NULL,A.SEP_DISCOUNT_AMOUNT,STDCD.SEP_DISCOUNT_AMOUNT) ,
                 STDCD.OCT_DISCOUNT_AMOUNT = IIF(A.OCT_DISCOUNT_AMOUNT IS NOT NULL,A.OCT_DISCOUNT_AMOUNT,STDCD.OCT_DISCOUNT_AMOUNT) ,
                 STDCD.NOV_DISCOUNT_AMOUNT = IIF(A.NOV_DISCOUNT_AMOUNT IS NOT NULL,A.NOV_DISCOUNT_AMOUNT,STDCD.NOV_DISCOUNT_AMOUNT) ,
                 STDCD.DEC_DISCOUNT_AMOUNT = IIF(A.DEC_DISCOUNT_AMOUNT IS NOT NULL,A.DEC_DISCOUNT_AMOUNT,STDCD.DEC_DISCOUNT_AMOUNT)
          FROM   ',@DEDUCTION_TABLE,' STDCD
                 INNER JOIN (SELECT CP_DETAILS_SID,
                                    YEAR,
                                    [1]  AS JAN_DISCOUNT_AMOUNT,
                                    [2]  AS FEB_DISCOUNT_AMOUNT,
                                    [3]  AS MAR_DISCOUNT_AMOUNT,
                                    [4]  AS APR_DISCOUNT_AMOUNT,
                                    [5]  AS MAY_DISCOUNT_AMOUNT,
                                    [6]  AS JUN_DISCOUNT_AMOUNT,
                                    [7]  AS JUL_DISCOUNT_AMOUNT,
                                    [8]  AS AUG_DISCOUNT_AMOUNT,
                                    [9]  AS SEP_DISCOUNT_AMOUNT,
                                    [10] AS OCT_DISCOUNT_AMOUNT,
                                    [11] AS NOV_DISCOUNT_AMOUNT,
                                    [12] AS DEC_DISCOUNT_AMOUNT,
                                    CHECK_RECORD
                             FROM   #TEMP_FINAL_SALES
                                    PIVOT(Sum(CAL_DISCOUNT_AMT)
                                         FOR MONTH IN([1],
                                                      [2],
                                                      [3],
                                                      [4],
                                                      [5],
                                                      [6],
                                                      [7],
                                                      [8],
                                                      [9],
                                                      [10],
                                                      [11],
                                                      [12]) ) AS PIVOTSALES) A
                         ON A.CP_DETAILS_SID = STDCD.CP_DETAILS_SID
                            AND A.YEAR = STDCD.YEAR','')
                                                EXEC sp_executesql @SQL1
              --  IF((SELECT COUNT(1) FROM ST_DEDUCTION_CALENDAR_DETAILS WHERE USER_ID=@USER_ID AND SESSION_ID=@SESSION_ID)>0)
              --  BEGIN
  --        --------------------------pivote the table --------------------------
  --        UPDATE  STDCD
  --        SET    STDCD.JAN_DISCOUNT_AMOUNT =IIF(A.JAN_DISCOUNT_AMOUNT IS  NOT NULL,A.JAN_DISCOUNT_AMOUNT,STDCD.JAN_DISCOUNT_AMOUNT),
  --               STDCD.FEB_DISCOUNT_AMOUNT = IIF(A.FEB_DISCOUNT_AMOUNT IS NOT NULL,A.FEB_DISCOUNT_AMOUNT,STDCD.FEB_DISCOUNT_AMOUNT),
  --               STDCD.MAR_DISCOUNT_AMOUNT = IIF(A.MAR_DISCOUNT_AMOUNT IS NOT NULL,A.MAR_DISCOUNT_AMOUNT,STDCD.MAR_DISCOUNT_AMOUNT) ,
  --               STDCD.APR_DISCOUNT_AMOUNT = IIF(A.APR_DISCOUNT_AMOUNT IS NOT NULL,A.APR_DISCOUNT_AMOUNT,STDCD.APR_DISCOUNT_AMOUNT) ,
  --               STDCD.MAY_DISCOUNT_AMOUNT = IIF(A.MAY_DISCOUNT_AMOUNT IS NOT NULL,A.MAY_DISCOUNT_AMOUNT,STDCD.MAY_DISCOUNT_AMOUNT),
  --               STDCD.JUN_DISCOUNT_AMOUNT = IIF(A.JUN_DISCOUNT_AMOUNT IS NOT NULL,A.JUN_DISCOUNT_AMOUNT,STDCD.JUN_DISCOUNT_AMOUNT) ,
  --               STDCD.JUL_DISCOUNT_AMOUNT = IIF(A.JUL_DISCOUNT_AMOUNT IS NOT NULL,A.JUL_DISCOUNT_AMOUNT,STDCD.JUL_DISCOUNT_AMOUNT) ,
  --               STDCD.AUG_DISCOUNT_AMOUNT = IIF(A.AUG_DISCOUNT_AMOUNT IS NOT NULL,A.AUG_DISCOUNT_AMOUNT,STDCD.AUG_DISCOUNT_AMOUNT) ,
  --               STDCD.SEP_DISCOUNT_AMOUNT = IIF(A.SEP_DISCOUNT_AMOUNT IS NOT NULL,A.SEP_DISCOUNT_AMOUNT,STDCD.SEP_DISCOUNT_AMOUNT) ,
  --               STDCD.OCT_DISCOUNT_AMOUNT = IIF(A.OCT_DISCOUNT_AMOUNT IS NOT NULL,A.OCT_DISCOUNT_AMOUNT,STDCD.OCT_DISCOUNT_AMOUNT) ,
  --               STDCD.NOV_DISCOUNT_AMOUNT = IIF(A.NOV_DISCOUNT_AMOUNT IS NOT NULL,A.NOV_DISCOUNT_AMOUNT,STDCD.NOV_DISCOUNT_AMOUNT) ,
  --               STDCD.DEC_DISCOUNT_AMOUNT = IIF(A.DEC_DISCOUNT_AMOUNT IS NOT NULL,A.DEC_DISCOUNT_AMOUNT,STDCD.DEC_DISCOUNT_AMOUNT)
  --        FROM   ST_DEDUCTION_CALENDAR_DETAILS STDCD
  --               INNER JOIN (SELECT CP_DETAILS_SID,
  --                                  YEAR,
  --                                  [1]  AS JAN_DISCOUNT_AMOUNT,
  --                                  [2]  AS FEB_DISCOUNT_AMOUNT,
  --                                  [3]  AS MAR_DISCOUNT_AMOUNT,
  --                                  [3]  AS APR_DISCOUNT_AMOUNT,
  --                                  [5]  AS MAY_DISCOUNT_AMOUNT,
  --                                  [6]  AS JUN_DISCOUNT_AMOUNT,
  --                                  [7]  AS JUL_DISCOUNT_AMOUNT,
  --                                  [8]  AS AUG_DISCOUNT_AMOUNT,
  --                                  [9]  AS SEP_DISCOUNT_AMOUNT,
  --                                  [10] AS OCT_DISCOUNT_AMOUNT,
  --                                  [11] AS NOV_DISCOUNT_AMOUNT,
  --                                  [12] AS DEC_DISCOUNT_AMOUNT,
  --                                  CHECK_RECORD,
  --                                  USER_ID,
  --                                  SESSION_ID
  --                           FROM   #TEMP_FINAL_SALES
  --                                  PIVOT(Sum(CAL_DISCOUNT_AMT)
  --                                       FOR MONTH IN([1],
  --                                                    [2],
  --                                                    [3],
  --                                                    [4],
  --                                                    [5],
  --                                                    [6],
  --                                                    [7],
  --                                                    [8],
  --                                                    [9],
  --                                                    [10],
  --                                                    [11],
  --                                                    [12]) ) AS PIVOTSALES) A
  --                       ON A.CP_DETAILS_SID = STDCD.CP_DETAILS_SID
  --                          AND A.YEAR = STDCD.YEAR
    
       --END
       --ELSE
       -- BEGIN
       -- UPDATE  STDCD
  --        SET    STDCD.JAN_DISCOUNT_AMOUNT = A.JAN_DISCOUNT_AMOUNT,
  --               STDCD.FEB_DISCOUNT_AMOUNT = A.FEB_DISCOUNT_AMOUNT,
  --               STDCD.MAR_DISCOUNT_AMOUNT = A.MAR_DISCOUNT_AMOUNT,
  --               STDCD.APR_DISCOUNT_AMOUNT = A.APR_DISCOUNT_AMOUNT,
  --               STDCD.MAY_DISCOUNT_AMOUNT = A.MAY_DISCOUNT_AMOUNT,
  --               STDCD.JUN_DISCOUNT_AMOUNT = A.JUN_DISCOUNT_AMOUNT,
  --               STDCD.JUL_DISCOUNT_AMOUNT = A.JUL_DISCOUNT_AMOUNT,
  --               STDCD.AUG_DISCOUNT_AMOUNT = A.AUG_DISCOUNT_AMOUNT,
  --               STDCD.SEP_DISCOUNT_AMOUNT = A.SEP_DISCOUNT_AMOUNT,
  --               STDCD.OCT_DISCOUNT_AMOUNT = A.OCT_DISCOUNT_AMOUNT,
  --               STDCD.NOV_DISCOUNT_AMOUNT = A.NOV_DISCOUNT_AMOUNT,
  --               STDCD.DEC_DISCOUNT_AMOUNT = A.DEC_DISCOUNT_AMOUNT
  --        FROM   ST_DEDUCTION_CALENDAR_DETAILS STDCD
  --               INNER JOIN (SELECT CP_DETAILS_SID,
  --                                  YEAR,
  --                                  [1]  AS JAN_DISCOUNT_AMOUNT,
  --                                  [2]  AS FEB_DISCOUNT_AMOUNT,
  --                                  [3]  AS MAR_DISCOUNT_AMOUNT,
  --                                  [3]  AS APR_DISCOUNT_AMOUNT,
  --                                  [5]  AS MAY_DISCOUNT_AMOUNT,
  --                                  [6]  AS JUN_DISCOUNT_AMOUNT,
  --                                  [7]  AS JUL_DISCOUNT_AMOUNT,
 --                                  [8]  AS AUG_DISCOUNT_AMOUNT,
  --                                  [9]  AS SEP_DISCOUNT_AMOUNT,
  --                                  [10] AS OCT_DISCOUNT_AMOUNT,
  --                                  [11] AS NOV_DISCOUNT_AMOUNT,
  --                                  [12] AS DEC_DISCOUNT_AMOUNT,
  --                                  CHECK_RECORD,
  --                                  USER_ID,
  --                                  SESSION_ID
  --                           FROM   #TEMP_FINAL_SALES
  --                                  PIVOT(SUM(CAL_DISCOUNT_AMT)
  --                                       FOR MONTH IN([1],
  --                                                    [2],
  --                                                    [3],
  --                                                    [4],
  --                                                    [5],
  --                                                    [6],
  --                                                    [7],
  --                                                    [8],
  --                                                    [9],
  --                                                    [10],
  --                                                    [11],
  --                                                    [12]) ) AS PIVOTSALES) A
  --                       ON A.CP_DETAILS_SID = STDCD.CP_DETAILS_SID
  --                          AND A.YEAR = STDCD.YEAR
              --                                END
 
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

          RAISERROR ( @ERRORMESSAGE,-- MESSAGE TEXT.
                      @ERRORSEVERITY,-- SEVERITY.
                      @ERRORSTATE,-- STATE.
                      @ERRORPROCEDURE,-- PROCEDURE_NAME.
                      @ERRORNUMBER,-- ERRORNUMBER
                      @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END
