IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_SALES_ALTERNATE_HISTORY'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_SALES_ALTERNATE_HISTORY]
  END

GO

CREATE PROCEDURE [dbo].[PRC_SALES_ALTERNATE_HISTORY] (@PROJECTION_MASTERS_ID INT,
                                                     @FREQUENCY             VARCHAR(50),
                                                     @START_DATE            DATETIME,
                                                     @END_DATE              DATETIME,
                                                     @USER_ID               INT,
                                                     @SESSION_ID            VARCHAR(50))
AS
  BEGIN
      SET NOCOUNT ON
      DECLARE @ITEM_UDT         UDT_ITEM,
              @START_PERIOD     INT,
              @END_PERIOD       INT,
              @FORECAST_NAME    VARCHAR(50),
              @FORECAST_VERSION VARCHAR(15),
              @COMPANY_ID       VARCHAR(50),---gal-808
              @SQL1             NVARCHAR(MAX),
              @BUSINESS_UNIT    INT--gal-808


			
       DECLARE @ACTUAL_TABLE     VARCHAR(200) = Concat('ST_NM_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @PROJECTION_TABLE VARCHAR(200) = Concat('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
     		@ST_ALT_HIST_TABLE VARCHAR(200) = CONCAT('ST_ALTERNATE_HIST_ALLOCATION_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
			  ,@CCP_HIERARCHY  VARCHAR(200)  = CONCAT('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))



      SET @FREQUENCY = CASE
                         WHEN LEFT(@FREQUENCY, 1) = 'Q' THEN 'QUARTER'
                         WHEN LEFT(@FREQUENCY, 1) = 'M' THEN 'MONTH'
                         WHEN LEFT(@FREQUENCY, 1) = 'S' THEN 'SEMI_ANNUAL'
                         WHEN LEFT(@FREQUENCY, 1) = 'Y' THEN 'YEAR'
                       END

      BEGIN TRY
          IF Object_id('TEMPDB.DBO.#ITEM_INFO', 'U') IS NOT NULL
            DROP TABLE #ITEM_INFO;

          CREATE TABLE #ITEM_INFO
            (
               ITEM_ID                VARCHAR(50),
               NDC8                   VARCHAR(20),
               ITEM_MASTER_SID        INT,
               CCP_DETAILS_SID INT
            )


			DECLARE @SQL nVARCHAR(max)= ''
       SET @SQL =  CONCAT('INSERT INTO #ITEM_INFO
                      (ITEM_ID,
                       NDC8,
                       ITEM_MASTER_SID,
                       CCP_DETAILS_SID)
          SELECT DISTINCT IM.ITEM_ID,
                          IM.NDC8,
                          IM.ITEM_MASTER_SID,
                          STA.CCP_DETAILS_SID
          FROM   ITEM_MASTER IM
                 INNER JOIN ',@CCP_HIERARCHY,' CCP join ccp_details cd on ccp.ccp_Details_sid = cd.ccp_Details_sid
                         ON Cd.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                 INNER JOIN ',@ST_ALT_HIST_TABLE,' STA
                         ON CCP.CCP_DETAILS_SID = STA.CCP_DETAILS_SID
          WHERE  CHECK_RECORD = 1')

				 EXEC sp_executesql @SQL

          -----------------------------------------------GAL-808-CHANGES---------------------------------
          SELECT @BUSINESS_UNIT = BUSINESS_UNIT,
                 @COMPANY_ID = PM.COMPANY_MASTER_SID
          FROM   PROJECTION_MASTER PM
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTERS_ID

          --------------------------------------END--------------------------------
          SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME,
                       @FORECAST_VERSION = FT.[VERSION]
          FROM   FILE_MANAGEMENT FT
                 INNER JOIN HELPER_TABLE HT
                         ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
          WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                   AND FT.FROM_PERIOD IS NOT NULL )
                 AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                        OR FT.TO_PERIOD IS NULL )
                 AND HT.LIST_NAME = 'FILE_TYPE'
                 AND HT.[DESCRIPTION] IN ( 'EX-FACTORY SALES' )
                 AND FT.BUSINESS_UNIT = @BUSINESS_UNIT-----------gal-808
                 AND FT.COMPANY = @COMPANY_ID----gal-808 
				 ORDER BY FILE_MANAGEMENT_SID
          SELECT @START_PERIOD = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = @START_DATE

          SELECT @END_PERIOD = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @END_DATE), 0)

          INSERT INTO @ITEM_UDT
                      (ITEM_MASTER_SID)
          SELECT DISTINCT ITEM_MASTER_SID
          FROM   #ITEM_INFO

          IF Object_id('TEMPDB..#TEMP_EXFACTORY') IS NOT NULL
            DROP TABLE #TEMP_EXFACTORY

          CREATE TABLE #TEMP_EXFACTORY
            (
               CCP_DETAILS_SID INT,
               ITEM_MASTER_SID        INT,
               PERIOD_SID             INT,
               ACTUAL_GTS_UNITS       NUMERIC(22, 6),
               ACTUAL_GTS_PERCENT     NUMERIC(22, 6),
               FORECAST_GTS_UNITS     NUMERIC(22, 6),
               FORECAST_GTS_PERCENT   NUMERIC(22, 6),
               FREQUENCY              INT,
               YEAR                   INT
            )

          SET @SQL1 = '
      SELECT CCP_DETAILS_SID,
       U.ITEM_MASTER_SID,
           P.PERIOD_SID,
         ACTUAL_GTS_UNITS, 
         ACTUAL_GTS_UNITS/SUM(ACTUAL_GTS_UNITS) OVER(PARTITION BY U.ITEM_MASTER_SID,YEAR,'
                      + @FREQUENCY
                      + ') AS  ACTUAL_GTS_PERCENT,
         FORECAST_GTS_UNITS,
         FORECAST_GTS_UNITS/SUM(FORECAST_GTS_UNITS) OVER(PARTITION BY U.ITEM_MASTER_SID,YEAR,'
                      + @FREQUENCY
                      + ' ORDER BY P.PERIOD_SID) AS  FORECAST_GTS_PERCENT,CASE 
                                    WHEN '''
                      + @FREQUENCY
                      + '''=''QUARTER'' THEN P.QUARTER 
                                    WHEN '''
                      + @FREQUENCY
                      + '''=''YEAR'' THEN P.YEAR 
                                    WHEN '''
                      + @FREQUENCY
                      + '''=''SEMI_ANNUAL'' THEN P.SEMI_ANNUAL 
                                    WHEN '''
                      + @FREQUENCY + '''=''MONTH'' THEN P.MONTH END,P.YEAR
      FROM UDF_GTS_WAC(@ITEM_UDT, @START_PERIOD, @END_PERIOD, @FORECAST_NAME, @FORECAST_VERSION) U
         JOIN PERIOD P
         ON P.PERIOD_SID=U.PERIOD_SID
         INNER JOIN #ITEM_INFO I
         ON U.ITEM_MASTER_SID=I.ITEM_MASTER_SID'

          INSERT INTO #TEMP_EXFACTORY
                      (CCP_DETAILS_SID,
                       ITEM_MASTER_SID,
                       PERIOD_SID,
                       ACTUAL_GTS_UNITS,
                       ACTUAL_GTS_PERCENT,
                       FORECAST_GTS_UNITS,
                       FORECAST_GTS_PERCENT,
                       FREQUENCY,
                       YEAR)
          EXEC Sp_executesql
            @SQL1,
            N'@FREQUENCY VARCHAR(50),@ITEM_UDT UDT_ITEM READONLY, @START_PERIOD INT, @END_PERIOD INT, @FORECAST_NAME VARCHAR(50), @FORECAST_VERSION VARCHAR(50)',
            @FREQUENCY,
            @ITEM_UDT,
            @START_PERIOD,
            @END_PERIOD,
            @FORECAST_NAME,
            @FORECAST_VERSION

          ----------------------------------------------------------GTS CALCULATION END--------------------------------------------------------------------------------------
       DECLARE @SQL2 nVARCHAR(max) = ''
	      SET @SQL2 = CONCAT('IF EXISTS (SELECT 1
                     FROM   ',@ACTUAL_TABLE,' SDCAD
                            INNER JOIN ',@ST_ALT_HIST_TABLE,' S
                                    ON SDCAD.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                                       AND SDCAD.PERIOD_SID = S.PERIOD_SID)
            BEGIN
                DELETE SDCAD
                FROM   ',@ACTUAL_TABLE,' SDCAD
                       INNER JOIN  ',@ST_ALT_HIST_TABLE,' S
                               ON SDCAD.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                                  AND SDCAD.PERIOD_SID = S.PERIOD_SID
                WHERE  S.CHECK_RECORD = 1
            END

          IF EXISTS (SELECT 1
                     FROM   ',@PROJECTION_TABLE,' SDCAD
                            INNER JOIN ',@ST_ALT_HIST_TABLE,' S
                                    ON SDCAD.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                                       AND SDCAD.PERIOD_SID = S.PERIOD_SID)
            BEGIN
                DELETE SDCAD
                FROM   ',@PROJECTION_TABLE,' SDCAD
                       INNER JOIN ',@ST_ALT_HIST_TABLE,' S
                               ON SDCAD.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                                  AND SDCAD.PERIOD_SID = S.PERIOD_SID
                WHERE  S.CHECK_RECORD = 1
            END')

	EXEC sp_executesql @SQL2


	DECLARE @SQL3 nVARCHAR(MAX)= ''
     SET @SQL3 =  CONCAT(  'INSERT INTO ',@ACTUAL_TABLE,'
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_UNITS)
          SELECT S.CCP_DETAILS_SID,
                 S.PERIOD_SID,
                 ACTUAL_UNITS= Isnull(Sum(S.ACTUAL_UNITS)
                                        OVER(
                                          PARTITION BY S.CCP_DETAILS_SID, T.FREQUENCY, T.YEAR), 0) * Isnull(NULLIF(T.ACTUAL_GTS_PERCENT, 0), ( CASE
                                                                                                                                                        WHEN @FREQUENCY = ''MONTH'' THEN 1
                                                                                                                                                        WHEN @FREQUENCY = ''QUARTER'' THEN ( 1.0 / 3.0 )
                                                                                                                                                        WHEN @FREQUENCY = ''SEMI_ANNUAL'' THEN ( 1.0 / 6.0 )
                                                                                                                                                        WHEN @FREQUENCY = ''YEAR'' THEN ( 1.0 / 12.0 )
                                                                                                                                                      END ))
          FROM   #TEMP_EXFACTORY T
                 LEFT JOIN ',@ST_ALT_HIST_TABLE,' S
                        ON S.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                           AND S.PERIOD_SID = T.PERIOD_SID
                           AND CHECK_RECORD = 1

          INSERT INTO ',@PROJECTION_TABLE,'
                      (CCP_DETAILS_SID,
                       PERIOD_SID,
                       PROJECTION_UNITS)
          SELECT S.CCP_DETAILS_SID,
                 S.PERIOD_SID,
                 PROJECTION_UNITS = Isnull(Sum(PROJECTION_UNITS)
                                             OVER(
                                               PARTITION BY S.CCP_DETAILS_SID, T.FREQUENCY, T.YEAR), 0) * Isnull(NULLIF(T.FORECAST_GTS_PERCENT, 0), ( CASE
                                                                                                                                                               WHEN @FREQUENCY = ''MONTH'' THEN 1
                                                                                                                                                               WHEN @FREQUENCY = ''QUARTER'' THEN ( 1.0 / 3.0 )
                                                                                                                                                               WHEN @FREQUENCY = ''SEMI_ANNUAL'' THEN ( 1.0 / 6.0 )
                                                                                                                                                               WHEN @FREQUENCY = ''YEAR'' THEN ( 1.0 / 12.0 )
                                                                                                                                                             END ))
          FROM   #TEMP_EXFACTORY T
                 LEFT JOIN ',@ST_ALT_HIST_TABLE,' S
                        ON S.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                           AND S.PERIOD_SID = T.PERIOD_SID
                           AND CHECK_RECORD = 1')

EXEC SP_EXECUTESQL @SQL3,N'@FREQUENCY VARCHAR(50)',@FREQUENCY = @FREQUENCY

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

          RAISERROR ( @ERRORMESSAGE,-- MESSAGE TEXT.
                      @ERRORSEVERITY,-- SEVERITY.
                      @ERRORSTATE,-- STATE.
                      @ERRORPROCEDURE,-- PROCEDURE_NAME.
                      @ERRORNUMBER,-- ERRORNUMBER
                      @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END 

