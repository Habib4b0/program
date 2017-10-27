IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'UDF_PROJECTION_DATES')
  BEGIN
      DROP FUNCTION [DBO].[UDF_PROJECTION_DATES]
  END

GO

CREATE FUNCTION [dbo].[UDF_PROJECTION_DATES] (@PROJECTION_MASTER_SID INT,
                                        @FREQUENCY             VARCHAR(20))
RETURNS TABLE
AS
    RETURN
      WITH FREQUENCY_CTE
           AS (SELECT DATEADD(YY, DATEDIFF(YY, 0, GETDATE()) - 3, 0) AS ACTUAL_START_DATE,
                      DATEADD(MM, -1, DATEADD(MM, DATEDIFF(MM, 0, GETDATE()), 0)) AS ACTUAL_END_DATE,
                      MAX(CASE
                                                   WHEN @FREQUENCY = 'A' THEN DATEADD(YY, DATEDIFF(YY, 0, FROM_DATE), 0)
                                                   WHEN @FREQUENCY = 'S' THEN
                                                     CASE
                                                       WHEN DATEPART(MM, FROM_DATE) <= 6 THEN ( DATEADD(YY, DATEDIFF(YY, 0, FROM_DATE), 0) )
                                                       ELSE DATEADD(MM, 6, ( DATEADD(YY, DATEDIFF(YY, 0, FROM_DATE), 0) ))
                                                     END
                                                   WHEN @FREQUENCY = 'Q' THEN ( DATEADD(QQ, DATEDIFF(QQ, 0, FROM_DATE), 0) )
                                                   ELSE DATEADD(MM, DATEDIFF(MM, 0, FROM_DATE), 0)
                                                 END) AS PROJECTION_START_DATE ,
                      MAX(CASE
                                                 WHEN @FREQUENCY = 'A' THEN DATEADD(YY, DATEDIFF(YY, 0, TO_DATE), 0)
                                                 WHEN @FREQUENCY = 'S' THEN
                                                   CASE
                                                     WHEN DATEPART(MM, TO_DATE) <= 6 THEN ( DATEADD(YY, DATEDIFF(YY, 0, TO_DATE), 0) )
                                                     ELSE DATEADD(MM, 6, ( DATEADD(YY, DATEDIFF(YY, 0, TO_DATE), 0) ))
                                                   END
                                                 WHEN @FREQUENCY = 'Q' THEN ( DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE), 0) )
                                                 ELSE DATEADD(MM, DATEDIFF(MM, 0, TO_DATE), 0)
                                               END) AS PROJECTION_END_DATE
               FROM   [DBO].[PROJECTION_MASTER]
               WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
      SELECT ACTUAL_START_DATE,
             ACTUAL_END_DATE,
             PROJECTION_START_DATE,
             PROJECTION_END_DATE,
             MAX(CASE
                                      WHEN PERIOD_DATE = ACTUAL_START_DATE THEN PERIOD_SID
                                    END) AS ACTUAL_START_SID,
             MAX(CASE
                                    WHEN PERIOD_DATE = ACTUAL_END_DATE THEN PERIOD_SID
                                  END) AS ACTUAL_END_SID,
             MAX(CASE
                                          WHEN PERIOD_DATE = PROJECTION_START_DATE THEN PERIOD_SID
                                        END) AS PROJECTION_START_SID,
             MAX(CASE
                                        WHEN PERIOD_DATE = PROJECTION_END_DATE THEN PERIOD_SID
                                      END) AS PROJECTION_END_SID
      FROM   PERIOD P
             INNER JOIN FREQUENCY_CTE D
                     ON P.PERIOD_DATE IN ( D.ACTUAL_START_DATE, D.ACTUAL_END_DATE, D.PROJECTION_START_DATE, D.PROJECTION_END_DATE )
      GROUP  BY ACTUAL_START_DATE,
                ACTUAL_END_DATE,
                PROJECTION_START_DATE,
                PROJECTION_END_DATE 


GO