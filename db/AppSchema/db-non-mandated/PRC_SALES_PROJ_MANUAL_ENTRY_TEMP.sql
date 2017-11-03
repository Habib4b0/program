IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_SALES_PROJ_MANUAL_ENTRY_TEMP'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_SALES_PROJ_MANUAL_ENTRY_TEMP]
  END

  go
CREATE  PROCEDURE [dbo].[PRC_SALES_PROJ_MANUAL_ENTRY_TEMP](@PROJECTION_MASTER_SID INT,
                                                     @USER_ID               INT,
                                                     @SESSION_ID            VARCHAR(50),
                                                     @BASED_ON              VARCHAR(20),
													 @SALES_INCLUSION bit =null)
AS
  BEGIN
      SET NOCOUNT ON;

      BEGIN TRY
          DECLARE @START_SID INT
          DECLARE @END_SID   INT
          DECLARE @FORECAST_TYPE VARCHAR(100)
		  DECLARE @UOM  VARCHAR(100) = 'EACH'
		  DECLARE @PRICING_QUALIFIER VARCHAR(100) = 'WAC',
		          @COMPANY_ID        INT,
                  @BUSINESS_UNIT     INT,
                  @FORECAST_NAME     VARCHAR(50),
                  @FORECAST_VER      VARCHAR(10)

          SELECT @FORECAST_TYPE = FORECASTING_TYPE
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID


          SELECT  @COMPANY_ID = COMPANY_MASTER_SID,
                 @BUSINESS_UNIT = BUSINESS_UNIT,
				 @START_SID = PERIOD_SID
          FROM   PROJECTION_MASTER PM JOIN PERIOD P ON DATEADD(M,DATEDIFF(M,0,PM.FROM_DATE),0) = P.PERIOD_DATE 
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
		   
          SELECT @END_SID   = PERIOD_SID
          FROM   PROJECTION_MASTER PM JOIN PERIOD P ON DATEADD(M,DATEDIFF(M,0,PM.TO_DATE),0)   = P.PERIOD_DATE 
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

        DECLARE @MASTER_TABLE     VARCHAR(200) =   CONCAT('ST_',IIF(@FORECAST_TYPE <> 'MANDATED','NM','M'),'_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
                @PROJECTION_TABLE VARCHAR(200) = CONCAT('ST_',IIF(@FORECAST_TYPE <> 'MANDATED','NM','M'),'_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
  ,@CCP_HIERARCHY  VARCHAR(200)  = CONCAT('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))

		IF OBJECT_ID('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL
		  DROP TABLE #CCP_DETAILS_TEMP

		CREATE TABLE #CCP_DETAILS_TEMP
		  (
			 CCP_DETAILS_SID     INT,
			 COMPANY_MASTER_SID  INT,
			 ITEM_MASTER_SID     INT,
			 CONTRACT_MASTER_SID INT,
			 NEW                 BIT DEFAULT (0)
		  )
DECLARE @D_SQL nVARCHAR(max)=''
		SET @D_SQL='INSERT INTO #CCP_DETAILS_TEMP(CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID)
								  SELECT CH.CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID FROM '+@CCP_HIERARCHY+ ' CH JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID '
		EXEC sp_executesql @D_SQL

          --SELECT token,min(PERIOD_SID)
          --FROM   udf_SplitString('Q3 2014,Q4 2014,Q3 2012,Q4 2012', ',') A
          --JOIN   PERIOD P ON 'Q' + Cast( QUARTER AS VARCHAR(2)) + ' '
          --                   + Cast(YEAR AS VARCHAR(4)) = A.token 
          --GROUP  BY QUARTER,YEAR,token

		  		IF OBJECT_ID('TEMPDB..#TEMP_SALES_MASTER') IS NOT NULL
		  DROP TABLE #TEMP_SALES_MASTER
          CREATE TABLE #TEMP_SALES_MASTER
            (
               CCP_DETAILS_SID INT,
               --CALCULATION_BASED      VARCHAR(100),
               CALCULATION_PERIODS    VARCHAR(4000),
               USER_ID                INT,
               SESSION_ID             INT
            )


			
		  		IF OBJECT_ID('TEMPDB..#TEMP_SALES_AFTER_ADJUST') IS NOT NULL
		  DROP TABLE #TEMP_SALES_AFTER_ADJUST
          CREATE TABLE #TEMP_SALES_AFTER_ADJUST
            (
               CCP_DETAILS_SID INT,
               PERIOD_SID             INT,
               PROJECTION_SALES       NUMERIC(22, 6),
               PROJECTION_UNITS       NUMERIC(22, 6),
               WAC_PRICE              NUMERIC(22, 6),
               NEW_SALES              NUMERIC(22, 6),
               NEW_UNITS              NUMERIC(22, 6)
            )

			

			  SET @D_SQL =CONCAT('INSERT INTO #TEMP_SALES_MASTER
                            (CCP_DETAILS_SID,
                    CALCULATION_PERIODS)

                SELECT A.CCP_DETAILS_SID,
                    A.CALCULATION_PERIODS
                FROM   ',@MASTER_TABLE,' A
                WHERE  CHECK_RECORD = 1
				 AND  FILTER_CCP=1  ',case when @SALES_INCLUSION is not null then concat('ANd SALES_INCLUSION =',@SALES_INCLUSION) END,' ')
	
				   EXEC sp_executesql @D_SQL
				  


          --IF( @FORECAST_TYPE = 'Non Mandated' )
          --  BEGIN
          --      INSERT INTO #TEMP_SALES_MASTER
          --                  (PROJECTION_DETAILS_SID,
          --                   CALCULATION_BASED,
          --                   CALCULATION_PERIODS,
          --                   USER_ID,
          --                   SESSION_ID)
          --      SELECT A.PROJECTION_DETAILS_SID,
          --             A.CALCULATION_BASED,
          --             A.CALCULATION_PERIODS,
          --             USER_ID,
          --             SESSION_ID
          --      FROM   ST_NM_SALES_PROJECTION_MASTER A
          --      JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
          --      WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
          --         AND USER_ID = @USER_ID
          --         AND SESSION_ID = @SESSION_ID
          --         AND CHECK_RECORD = 1
          --  END
          --ELSE
          --  BEGIN
          --      INSERT INTO #TEMP_SALES_MASTER
          --                  (PROJECTION_DETAILS_SID,
          --                   CALCULATION_BASED,
          --                   CALCULATION_PERIODS,
          --                   USER_ID,
          --                   SESSION_ID)
          --      SELECT A.PROJECTION_DETAILS_SID,
          --             A.CALCULATION_BASED,
          --             A.CALCULATION_PERIODS,
          --             USER_ID,
          --             SESSION_ID
          --      FROM   ST_M_SALES_PROJECTION_MASTER A
          --      JOIN   PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
          --      WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
          --         AND USER_ID = @USER_ID
          --         AND SESSION_ID = @SESSION_ID
          --         AND CHECK_RECORD = 1
          --  END

            CREATE TABLE #TEMP_ITEM
            (
               ITEM_ID         VARCHAR(50),
               ITEM_MASTER_SID INT
            )

          INSERT INTO #TEMP_ITEM
                      (ITEM_ID,
                       ITEM_MASTER_SID)
          SELECT distinct IM.ITEM_ID,
                 C.ITEM_MASTER_SID
          FROM   #TEMP_SALES_MASTER A
          JOIN   #CCP_DETAILS_TEMP C ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
          JOIN   ITEM_MASTER IM ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID

 -----------newly added part----------
          ----------------------------------------- WAC PRICE CALCULATION  START------------------
          DECLARE @ITEMID [DBO].[UDT_ITEM]
 
          INSERT INTO @ITEMID
          SELECT DISTINCT ITEM_MASTER_SID
          FROM   #TEMP_ITEM
 
          IF Object_id('TEMPDB.DBO.#UDF_ITEM_PRICING', 'U') IS NOT NULL
            DROP TABLE #UDF_ITEM_PRICING;
 
          SELECT ITEM_MASTER_SID,
             PERIOD_SID,
             PRICING_QUALIFIER,
             ITEM_PRICE
          INTO   #UDF_ITEM_PRICING
          FROM   DBO.UDF_ITEM_PRICING(@ITEMID, @PRICING_QUALIFIER, @START_SID, @END_SID, @UOM);
 
          WITH CTE
               AS (SELECT Row_number()
                            OVER(
                              PARTITION BY FT.FILE_TYPE
                              ORDER BY FT.FROM_PERIOD DESC) AS RN,
                          FT.FORECAST_NAME,
                          FT.[VERSION],
                          HT.DESCRIPTION                    AS FILE_TYPE
                   FROM   FILE_MANAGEMENT FT
                          INNER JOIN HELPER_TABLE HT
                                  ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
                   WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                            AND FT.FROM_PERIOD IS NOT NULL )
                          AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                                 OR FT.TO_PERIOD IS NULL )
                          AND HT.LIST_NAME = 'FILE_TYPE'
                          AND HT.DESCRIPTION IN ( 'EX-FACTORY SALES' )
                          AND FT.BUSINESS_UNIT = @BUSINESS_UNIT
                          AND FT.COMPANY = @COMPANY_ID)
          SELECT @FORECAST_NAME = FORECAST_NAME,
                 @FORECAST_VER = [VERSION]
          FROM   CTE
          WHERE  RN = 1
 
          -------------------------------------------------------------------------------------
          /*  DECLARE @ITEM UDT_ITEM_PRICING
         
            INSERT INTO @ITEM
            SELECT DISTINCT
                   ITEM_ID
            FROM   #TEMP_ITEM
          */
          CREATE TABLE #TEMP_ITEM_PRICE
            (
               ITEM_ID         VARCHAR(50),
               ITEM_MASTER_SID INT,
               PERIOD_SID      INT,
               ITEM_PRICE      NUMERIC(22, 6)
            )
 
          INSERT INTO #TEMP_ITEM_PRICE
                      (ITEM_ID,
                       ITEM_MASTER_SID,
                       PERIOD_SID,
                       ITEM_PRICE)
          SELECT IM.ITEM_ID,
                 GTS.ITEM_MASTER_SID,
                 GTS.PERIOD_SID,
                 COALESCE(GTS.FORECAST_PRICE, IP.ITEM_PRICE) AS ITEM_PRICE
          FROM   DBO.UDF_GTS_WAC(@ITEMID, @START_SID, @END_SID, @FORECAST_NAME, @FORECAST_VER) GTS
                 INNER JOIN ITEM_MASTER IM
                         ON GTS.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                 LEFT OUTER JOIN #UDF_ITEM_PRICING IP
                              ON GTS.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                                 AND GTS.PERIOD_SID = IP.PERIOD_SID

		  DECLARE @D_SQL1 nVARCHAR(MAX) = ''

		SET     @D_SQL1 = CONCAT( 'INSERT INTO #TEMP_SALES_AFTER_ADJUST
                            (CCP_DETAILS_SID,
                             PERIOD_SID,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             WAC_PRICE,
                             NEW_SALES,
                             NEW_UNITS)
                SELECT    A.CCP_DETAILS_SID,
                          A.PERIOD_SID,
                          PROJECTION_SALES,
                          PROJECTION_UNITS,
                          E.ITEM_PRICE,
                          ( CASE
                              WHEN ''',@BASED_ON,''' = ''Sales'' THEN PROJECTION_SALES
                              ELSE IsNull(( PROJECTION_UNITS * E.ITEM_PRICE ), 0)
                            END ) AS NEW_SALES,
                          ( CASE
                              WHEN  ''',@BASED_ON,''' = ''Sales'' THEN COALESCE(PROJECTION_SALES / NULLIF(E.ITEM_PRICE, 0), 0)
                              ELSE PROJECTION_UNITS
                            END ) AS NEW_UNITS
                FROM      ',@PROJECTION_TABLE,' A
                JOIN      #TEMP_SALES_MASTER B ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                JOIN      #CCP_DETAILS_TEMP D ON D.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                LEFT JOIN #TEMP_ITEM_PRICE E ON E.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                                            AND E.PERIOD_SID = A.PERIOD_SID

                UPDATE ST
                SET    ST.PROJECTION_SALES = A.NEW_SALES,
                       ST.PROJECTION_UNITS = A.NEW_UNITS
                FROM   ',@PROJECTION_TABLE,' ST
                       JOIN (SELECT CCP_DETAILS_SID,
                                    PERIOD_SID,
                                    PROJECTION_SALES,
                                    PROJECTION_UNITS,
                                    WAC_PRICE,
                                    NEW_SALES,
                                    NEW_UNITS
                             FROM   #TEMP_SALES_AFTER_ADJUST) A ON A.CCP_DETAILS_SID = ST.CCP_DETAILS_SID
                                                               AND A.PERIOD_SID = ST.PERIOD_SID'
															   )
EXEC sp_executesql @D_SQL1

          --IF( @FORECAST_TYPE = 'Non Mandated' )
          --  BEGIN
          --      INSERT INTO #TEMP_SALES_AFTER_ADJUST
          --                  (PROJECTION_DETAILS_SID,
          --                   PERIOD_SID,
          --                   PROJECTION_SALES,
          --                   PROJECTION_UNITS,
          --                   WAC_PRICE,
          --                   NEW_SALES,
          --                   NEW_UNITS,
          --                   USER_ID,
          --                   SESSION_ID)
          --      SELECT    A.PROJECTION_DETAILS_SID,
          --                A.PERIOD_SID,
          --                PROJECTION_SALES,
          --                PROJECTION_UNITS,
          --                E.ITEM_PRICE,
          --                ( CASE
          --                    WHEN @BASED_ON = 'Sales' THEN PROJECTION_SALES
          --                    ELSE IsNull(( PROJECTION_UNITS * E.ITEM_PRICE ), 0)
          --                  END ) AS NEW_SALES,
          --                ( CASE
          --                    WHEN @BASED_ON = 'Sales' THEN COALESCE(PROJECTION_SALES / NULLIF(E.ITEM_PRICE, 0), 0)
          --                    ELSE PROJECTION_UNITS
          --                  END ) AS NEW_UNITS,
          --                A.USER_ID,
          --                A.SESSION_ID
          --      FROM      ST_NM_SALES_PROJECTION A
          --      JOIN      #TEMP_SALES_MASTER B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
          --                                    AND A.USER_ID = B.USER_ID
          --                                    AND A.SESSION_ID = B.SESSION_ID
          --      JOIN      PROJECTION_DETAILS C ON C.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
          --      JOIN      CCP_DETAILS D ON D.CCP_DETAILS_SID = C.CCP_DETAILS_SID
          --      LEFT JOIN #TEMP_ITEM_PRICE E ON E.ITEM_MASTER_SID = D.ITEM_MASTER_SID
          --                                  AND E.PERIOD_SID = A.PERIOD_SID

          --      UPDATE ST
          --      SET    ST.PROJECTION_SALES = A.NEW_SALES,
          --             ST.PROJECTION_UNITS = A.NEW_UNITS
          --      FROM   ST_NM_SALES_PROJECTION ST
          --             JOIN (SELECT PROJECTION_DETAILS_SID,
          --                          PERIOD_SID,
          --                          PROJECTION_SALES,
          --                          PROJECTION_UNITS,
          --                          WAC_PRICE,
          --                          NEW_SALES,
          --                          NEW_UNITS,
          --                          USER_ID,
          --                          SESSION_ID
          --                   FROM   #TEMP_SALES_AFTER_ADJUST) A ON A.PROJECTION_DETAILS_SID = ST.PROJECTION_DETAILS_SID
          --                                                     AND A.PERIOD_SID = ST.PERIOD_SID
          --                                                     AND A.USER_ID = ST.USER_ID
          --                                                     AND A.SESSION_ID = ST.SESSION_ID
          --  END
          --ELSE
          --  BEGIN
          --      INSERT INTO #TEMP_SALES_AFTER_ADJUST
          --                  (PROJECTION_DETAILS_SID,
          --                   PERIOD_SID,
          --                   PROJECTION_SALES,
          --                   PROJECTION_UNITS,
          --                   WAC_PRICE,
          --                   NEW_SALES,
          --                   NEW_UNITS,
          --                   USER_ID,
          --                   SESSION_ID)
          --      SELECT    A.PROJECTION_DETAILS_SID,
          --                A.PERIOD_SID,
          --                PROJECTION_SALES,
          --                PROJECTION_UNITS,
          --                E.ITEM_PRICE,
          --                ( CASE
          --                    WHEN @BASED_ON = 'Sales' THEN PROJECTION_SALES
          --                    ELSE IsNull(( PROJECTION_UNITS * E.ITEM_PRICE ), 0)
          --                  END ) AS NEW_SALES,
          --                ( CASE
          --                    WHEN @BASED_ON = 'Sales' THEN COALESCE(PROJECTION_SALES / NULLIF(E.ITEM_PRICE, 0), 0)
          --                    ELSE PROJECTION_UNITS
          --                  END ) AS NEW_UNITS,
          --                A.USER_ID,
          --                A.SESSION_ID
          --      FROM      ST_M_SALES_PROJECTION A
          --      JOIN      #TEMP_SALES_MASTER B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
          --                                    AND A.USER_ID = B.USER_ID
          --                                    AND A.SESSION_ID = B.SESSION_ID
          --      JOIN      PROJECTION_DETAILS C ON C.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
          --      JOIN      CCP_DETAILS D ON D.CCP_DETAILS_SID = C.CCP_DETAILS_SID
          --      LEFT JOIN #TEMP_ITEM_PRICE E ON E.ITEM_MASTER_SID = D.ITEM_MASTER_SID
          --                                  AND E.PERIOD_SID = A.PERIOD_SID

          --      UPDATE ST
          --      SET    ST.PROJECTION_SALES = A.NEW_SALES,
          --             ST.PROJECTION_UNITS = A.NEW_UNITS
          --      FROM   ST_M_SALES_PROJECTION ST
          --             JOIN (SELECT PROJECTION_DETAILS_SID,
          --                          PERIOD_SID,
          --                          PROJECTION_SALES,
          --                          PROJECTION_UNITS,
          --                          WAC_PRICE,
          --                          NEW_SALES,
          --                          NEW_UNITS,
          --                          USER_ID,
          --                          SESSION_ID
          --                   FROM   #TEMP_SALES_AFTER_ADJUST) A ON A.PROJECTION_DETAILS_SID = ST.PROJECTION_DETAILS_SID
          --                                                     AND A.PERIOD_SID = ST.PERIOD_SID
          --                                                     AND A.USER_ID = ST.USER_ID
          --                                                     AND A.SESSION_ID = ST.SESSION_ID
          --  END

          DROP TABLE #TEMP_SALES_AFTER_ADJUST

          DROP TABLE #TEMP_ITEM_PRICE

          DROP TABLE #TEMP_SALES_MASTER

          DROP TABLE #TEMP_ITEM

      END TRY

      BEGIN CATCH
          DECLARE @ErrorMessage NVARCHAR(4000);
          DECLARE @ErrorSeverity INT;
          DECLARE @ErrorState INT;
          DECLARE @ErrorNumber INT;
          DECLARE @ErrorProcedure VARCHAR(200);
          DECLARE @Errorline INT;

          EXEC UsPerrOrCollector

          SELECT @ErrorMessage = Error_Message(),
                 @ErrorSeverity = Error_Severity(),
                 @ErrorState = Error_State(),
                 @ErrorProcedure = Error_Procedure(),
                 @Errorline = Error_Line(),
                 @ErrorNumber = Error_Number()

          RAISERROR (@ErrorMessage,-- Message text.
                     @ErrorSeverity,-- Severity.
                     @ErrorState,-- State.
                     @ErrorProcedure,-- Procedure_Name.
                     @ErrorNumber,-- ErrorNumber
                     @Errorline -- ErrorLine
          );
      END CATCH
  END

