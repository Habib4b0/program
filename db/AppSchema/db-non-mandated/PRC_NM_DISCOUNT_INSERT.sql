IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NM_DISCOUNT_INSERT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NM_DISCOUNT_INSERT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_NM_DISCOUNT_INSERT] (@PROJECTION INT,
                                                 @USER_ID    INT,
                                                 @SESSION_ID VARCHAR(50))
AS
  BEGIN
      SET NOCOUNT ON

      DECLARE @ACTUAL_START_DATE DATETIME
      DECLARE @PROJECTION_START_DATE DATETIME
      DECLARE @PROJECTION_END_DATE DATETIME
      DECLARE @CURRENT_QUARTER DATETIME
      DECLARE @SAVE_FLAG BIT
	  DECLARE @MASTER_TABLE     VARCHAR(200) = Concat('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID,'_',replace(convert(varchar(50),getdate(),2),'.','')),
              @ACTUAL_TABLE     VARCHAR(200) = Concat('ST_NM_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID,'_',replace(convert(varchar(50),getdate(),2),'.','')),
              @PROJECTION_TABLE VARCHAR(200) = Concat('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID,'_',replace(convert(varchar(50),getdate(),2),'.',''))
      BEGIN TRY
          SELECT @CURRENT_QUARTER = DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0),
                 @ACTUAL_START_DATE = DATEADD(YY, DATEDIFF(YY, 0, GETDATE()) - 3
                                      ,
                                      0),
                 @PROJECTION_START_DATE = DATEFROMPARTS(YEAR(FROM_DATE), 01, 01)
                 ,
                 @PROJECTION_END_DATE =
                 DATEFROMPARTS(YEAR(TO_DATE), 12, 01)
          FROM   [DBO].[PROJECTION_MASTER]
          WHERE  PROJECTION_MASTER_SID = @PROJECTION

          SET @SAVE_FLAG = (SELECT SAVE_FLAG
                            FROM   PROJECTION_MASTER
                            WHERE  PROJECTION_MASTER_SID = @PROJECTION)

          ----------------------------------------- APPROVED CCP   
          IF OBJECT_ID('TEMPDB.DBO.#APPROVED_CCP_DETAILS', 'U') IS NOT NULL
            DROP TABLE #APPROVED_CCP_DETAILS;

          CREATE TABLE #APPROVED_CCP_DETAILS
            (
               PROJECTION_MASTER_SID  INT,
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT
            )

          INSERT INTO #APPROVED_CCP_DETAILS
                      (PROJECTION_MASTER_SID,
                       PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID)
          SELECT PROJECTION_MASTER_SID,
                 PROJECTION_DETAILS_SID,
                 CCP_DETAILS_SID
          FROM   DBO.UDF_APPROVED_CCP_DETAILS(@PROJECTION, 'NON MANDATED')

          ----------------------------------------------- MASTER TABLE (TO STORE INPUT FOR NEW CCP)  
          IF OBJECT_ID('TEMPDB.DBO.#NM_DISCOUNT_PROJ_MASTER', 'U') IS NOT NULL
            DROP TABLE #NM_DISCOUNT_PROJ_MASTER;

          CREATE TABLE #NM_DISCOUNT_PROJ_MASTER
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT,
               CONTRACT_MASTER_SID    INT,
               COMPANY_MASTER_SID     INT,
               ITEM_MASTER_SID        INT,
               PRICE_GROUP_TYPE       VARCHAR(50),
               RS_MODEL_SID           INT,
               RS_ID                  VARCHAR(50),
               NET_FLAG               CHAR(1)
            )

          INSERT INTO #NM_DISCOUNT_PROJ_MASTER
                      (PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID,
                       PRICE_GROUP_TYPE,
                       RS_MODEL_SID,
                       RS_ID,
                       NET_FLAG)
          SELECT PROJECTION_DETAILS_SID,
                 CCP_DETAILS_SID,
                 CONTRACT_MASTER_SID,
                 COMPANY_MASTER_SID,
                 ITEM_MASTER_SID,
                 PRICE_GROUP_TYPE,
                 RS_MODEL_SID,
                 RS_ID,
                 NET_FLAG
          FROM   (SELECT DISTINCT PROJECTION_DETAILS_SID,
                                  B.CCP_DETAILS_SID,
                                  B.CONTRACT_MASTER_SID,
                                  B.COMPANY_MASTER_SID,
                                  B.ITEM_MASTER_SID,
                                  CASE
                                                     WHEN
                                  HT.DESCRIPTION = 'REIMB'
                                                   THEN
                                                     'REIMBURSEMENT'
                                                     WHEN HT.DESCRIPTION = 'OI'
                                                   THEN
                                                     'OFF INVOICE'
                                                     ELSE HT.DESCRIPTION
                                                   END AS PRICE_GROUP_TYPE,
                                  R.RS_MODEL_SID,
                                  R.RS_ID,
                                  ( CASE
                                                 WHEN HT.DESCRIPTION LIKE 'OFF%'
                                               THEN
                                                 'N'
                                                 ELSE 'Y'
                                               END ) AS NET_FLAG
                  FROM   PROJECTION_DETAILS A
                         JOIN CCP_DETAILS B
                           ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                         JOIN RS_CONTRACT R1
                           ON R1.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
                         JOIN RS_CONTRACT_DETAILS R2
                           ON R2.RS_CONTRACT_SID = R1.RS_CONTRACT_SID
                              AND R2.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                         JOIN RS_MODEL R
                           ON R.RS_MODEL_SID = R1.RS_MODEL_SID
                         LEFT JOIN HELPER_TABLE HT
                                ON HT.HELPER_TABLE_SID = R1.REBATE_PROGRAM_TYPE
                                   AND HT.LIST_NAME = 'REBATE_PROGRAM_TYPE'
                         LEFT JOIN UDCS U
                                ON U.MASTER_SID = R1.RS_CONTRACT_SID
                                   AND U.MASTER_TYPE = 'RS_CONTRACT'
                         LEFT JOIN HELPER_TABLE H2
                                ON H2.HELPER_TABLE_SID = U.UDC3
                  WHERE  A.PROJECTION_MASTER_SID = @PROJECTION
                         AND R1.INBOUND_STATUS <> 'D'
                         AND R2.INBOUND_STATUS <> 'D'
                         AND EXISTS (SELECT 1
                                     FROM   CFP_CONTRACT CFP1
                                            JOIN CFP_CONTRACT_DETAILS CFP2
                                              ON CFP1.CFP_CONTRACT_SID =
                                                 CFP2.CFP_CONTRACT_SID
                                                 AND R1.CFP_CONTRACT_SID =
                                                     CFP1.CFP_CONTRACT_SID
                                     WHERE  COMPANY_MASTER_SID =
                                            B.COMPANY_MASTER_SID
                                            AND CFP1.CONTRACT_MASTER_SID =
                 B.CONTRACT_MASTER_SID))A
          WHERE  PRICE_GROUP_TYPE <> 'PRICE PROTECTION' --GALUAT-360  

	


DECLARE @SQL nVARCHAR(max)=''


       SET  @SQL=  'INSERT INTO '+ @MASTER_TABLE+'
                      (PROJECTION_DETAILS_SID,
                       RS_MODEL_SID,
                       METHODOLOGY,
                       CALCULATION_PERIODS,
                       BASELINE_PERIODS,
                       SELECTED_PERIODS,
                       PRICE_GROUP_TYPE,
                       NET_FLAG,
                       USER_GROUP,
                       CHECK_RECORD)
          SELECT PROJECTION_DETAILS_SID,
                 NDPM.RS_MODEL_SID,
                 A.METHODOLOGY,
                 A.CALCULATION_PERIODS,
                 A.BASELINE_PERIODS,
                 A.SELECTED_PERIODS,
                 NDPM.PRICE_GROUP_TYPE,
                 NDPM.NET_FLAG,
                 COALESCE(A.USER_GROUP, ''0''),
                 COALESCE(A.CHECK_RECORD, 0)
          FROM   #NM_DISCOUNT_PROJ_MASTER NDPM
                 LEFT JOIN (SELECT NDPM.METHODOLOGY,
                                   NDPM.USER_GROUP,
                                   NDPM.RS_MODEL_SID,
                                   NDPM.PRICE_GROUP_TYPE,
                                   NDPM.CALCULATION_PERIODS,
                                   NDPM.BASELINE_PERIODS,
                                   NDPM.CHECK_RECORD,
                                   NDPM.SELECTED_PERIODS,
                                   ACD.CCP_DETAILS_SID
                            FROM   NM_DISCOUNT_PROJ_MASTER NDPM
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON NDPM.PROJECTION_DETAILS_SID =
                                              ACD.PROJECTION_DETAILS_SID) A
                        ON NDPM.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NDPM.RS_MODEL_SID = A.RS_MODEL_SID
          WHERE  NOT EXISTS (SELECT 1
                             FROM '+ @MASTER_TABLE +' NM
                             WHERE  NM.PROJECTION_DETAILS_SID =
                                    NDPM.PROJECTION_DETAILS_SID
                                    AND NM.RS_MODEL_SID = NDPM.RS_MODEL_SID
                                    )'



								
EXEC sp_executesql @sql

          ---- FOR NEWLY ADDED CCP ALONE  (EDIT MODE)  
          ------------------------------------------------------------------------ UDT CONTAIN CONTRACT,COMPANY,ITEMA AND DISCOUNT COMBINATION (CCPD)  
          DECLARE @ACTUALS_CCPD UDT_CCP_DETAILS

    
            SET  @SQL= CONCAT('SELECT NDPM.CONTRACT_MASTER_SID,
                 NDPM.COMPANY_MASTER_SID,
                 NDPM.ITEM_MASTER_SID,
                 D.PROJECTION_DETAILS_SID,
                 R.RS_ID
          FROM '+ @MASTER_TABLE +'   D
                 INNER JOIN PROJECTION_DETAILS PD
				 ON PD.PROJECTION_DETAILS_SID=D.PROJECTION_DETAILS_SID
				 INNER JOIN CCP_DETAILS NDPM
				 ON NDPM.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
				 LEFT JOIN RS_MODEL R ON
				 R.RS_MODEL_SID=D.RS_MODEL_SID
          WHERE PD.PROJECTION_MASTER_SID=' ,@PROJECTION)

        INSERT INTO   @ACTUALS_CCPD
                      (CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID,
                       PROJECTION_DETAILS_SID,
                       DISCOUNT_ID)

        EXEC sp_executesql @sql

          ----------------------------------------------------------------------- ACTUAL DISCOUNT,RATE AND RATE PER UNIT CALCULATION  
          IF OBJECT_ID('TEMPDB.DBO.#NM_ACTUAL_DISCOUNT', 'U') IS NOT NULL
            DROP TABLE #NM_ACTUAL_DISCOUNT;

          CREATE TABLE #NM_ACTUAL_DISCOUNT
            (
               PROJECTION_DETAILS_SID INT,
               PERIOD_SID             INT,
               RS_MODEL_SID           INT,
               ACTUAL_RATE            NUMERIC(22, 6),
               ACTUAL_AMOUNT          NUMERIC(22, 6),
               ACTUAL_RPU             NUMERIC(22, 6)
            )

          INSERT INTO #NM_ACTUAL_DISCOUNT
                      (PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       RS_MODEL_SID,
                       ACTUAL_RATE,
                       ACTUAL_AMOUNT,
                       ACTUAL_RPU)
          SELECT NDPM.PROJECTION_DETAILS_SID,
                 PERIOD_SID,
                 NDPM.RS_MODEL_SID,
                 ISNULL(( MAX(DISCOUNT) ) / NULLIF(MAX(SALES), 0), 0) * 100
                 ACTUAL_RATE,
                 ISNULL(MAX(DISCOUNT), 0)
                 ACTUAL_AMOUNT,
                 ISNULL(( MAX(DISCOUNT) ) / NULLIF(MAX(QUANTITY), 0), 0)
                 ACTUAL_RPU
          FROM   [DBO].[UDF_ACTUALS_DETAILS](@ACTUALS_CCPD, @CURRENT_QUARTER,
                 @ACTUAL_START_DATE)
                 A
                 JOIN #NM_DISCOUNT_PROJ_MASTER NDPM
                   ON NDPM.CONTRACT_MASTER_SID = A.CONTRACT_MASTER_SID
                      AND NDPM.COMPANY_MASTER_SID = A.COMPANY_MASTER_SID
                      AND NDPM.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                      AND NDPM.RS_ID = A.DISCOUNT_ID
          GROUP  BY NDPM.PROJECTION_DETAILS_SID,
                    PERIOD_SID,
                    RS_MODEL_SID
				
          --------------- ---------------------------------------------------------------NEW PART STARTS  ---------------------------------------------------------------  
          IF OBJECT_ID('TEMPDB..#NEW_CCP_DETAILS') IS NOT NULL
            DROP TABLE #NEW_CCP_DETAILS

          CREATE TABLE #NEW_CCP_DETAILS
            (
               PROJECTION_DETAILS_SID INT,
               RS_MODEL_SID           INT
               PRIMARY KEY(PROJECTION_DETAILS_SID, RS_MODEL_SID)
            )

          INSERT INTO #NEW_CCP_DETAILS
                      (PROJECTION_DETAILS_SID,
                       RS_MODEL_SID)
          SELECT DISTINCT PROJECTION_DETAILS_SID,
                          RS_MODEL_SID
          FROM   #NM_DISCOUNT_PROJ_MASTER
          WHERE  NOT EXISTS (SELECT NM.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_DISCOUNT NM
                 INNER JOIN PROJECTION_DETAILS PD
                         ON NM.PROJECTION_DETAILS_SID =
                            PD.PROJECTION_DETAILS_SID
                                                WHERE
                 PD.PROJECTION_MASTER_SID = @PROJECTION
				 AND NM.PROJECTION_DETAILS_SID=#NM_DISCOUNT_PROJ_MASTER.PROJECTION_DETAILS_SID)

          IF OBJECT_ID('TEMPDB..#ACTUAL_CCP_DETAILS') IS NOT NULL
            DROP TABLE #ACTUAL_CCP_DETAILS

          CREATE TABLE #ACTUAL_CCP_DETAILS
            (
               PROJECTION_DETAILS_SID INT PRIMARY KEY
            )

          INSERT INTO #ACTUAL_CCP_DETAILS
                      (PROJECTION_DETAILS_SID)
          SELECT DISTINCT PROJECTION_DETAILS_SID
          FROM   #NM_ACTUAL_DISCOUNT
          GROUP  BY PROJECTION_DETAILS_SID
          HAVING ISNULL(SUM(ACTUAL_AMOUNT), 0) = 0

          IF @SAVE_FLAG = 1
            BEGIN
                UPDATE SS
                SET    SS.ACTUAL_AMOUNT = O.ACTUAL_SALES
                FROM   #NM_ACTUAL_DISCOUNT SS
                       INNER JOIN (SELECT S.PROJECTION_DETAILS_SID,
                                          NM.RS_MODEL_SID,
                                          NM.PERIOD_SID,
                                          NM.ACTUAL_SALES
                                   FROM   #ACTUAL_CCP_DETAILS S
                                          INNER JOIN NM_ACTUAL_DISCOUNT NM
                                                  ON S.PROJECTION_DETAILS_SID =
                                                     NM.PROJECTION_DETAILS_SID
                                          INNER JOIN PROJECTION_DETAILS PD
                                                  ON PD.PROJECTION_DETAILS_SID =
                                                     NM.PROJECTION_DETAILS_SID
                                   WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION
                                   UNION
                                   SELECT S.PROJECTION_DETAILS_SID,
                                          NM.RS_MODEL_SID,
                                          NM.PERIOD_SID,
                                          NM.ACTUAL_SALES
                                   FROM   #NEW_CCP_DETAILS S
                                          INNER JOIN #ACTUAL_CCP_DETAILS AA
                                                  ON AA.PROJECTION_DETAILS_SID =
                                                     S.PROJECTION_DETAILS_SID
                                          INNER JOIN PROJECTION_DETAILS PD
                                                  ON PD.PROJECTION_DETAILS_SID =
                                                     S.PROJECTION_DETAILS_SID
                                          INNER JOIN #APPROVED_CCP_DETAILS A
                                                  ON PD.CCP_DETAILS_SID =
                                                     A.CCP_DETAILS_SID
                                          INNER JOIN NM_ACTUAL_DISCOUNT NM
                                                  ON A.PROJECTION_DETAILS_SID =
                                                     NM.PROJECTION_DETAILS_SID)
                                  O
                               ON SS.PROJECTION_DETAILS_SID =
                                  O.PROJECTION_DETAILS_SID
                                  AND SS.PERIOD_SID = O.PERIOD_SID
                                  AND SS.RS_MODEL_SID = O.RS_MODEL_SID
            END
          ELSE
            BEGIN
                UPDATE SS
                SET    SS.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID,
                       SS.PERIOD_SID = NM.PERIOD_SID,
                       SS.ACTUAL_AMOUNT = NM.ACTUAL_SALES
                FROM   #NM_ACTUAL_DISCOUNT SS
                       INNER JOIN #ACTUAL_CCP_DETAILS S
                               ON SS.PROJECTION_DETAILS_SID =
                                  S.PROJECTION_DETAILS_SID
                       INNER JOIN PROJECTION_DETAILS PD
                               ON PD.PROJECTION_DETAILS_SID =
                                  S.PROJECTION_DETAILS_SID
                       INNER JOIN #APPROVED_CCP_DETAILS A
                               ON PD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       INNER JOIN NM_ACTUAL_DISCOUNT NM
                               ON A.PROJECTION_DETAILS_SID =
                                  NM.PROJECTION_DETAILS_SID
                                  AND SS.PERIOD_SID = NM.PERIOD_SID
                                  AND SS.RS_MODEL_SID = NM.RS_MODEL_SID
            END

          --------------------------------------------------------NEW PART ENDS -------------------------------------------------------------------------------  
        SET @SQL=  CONCAT('TRUNCATE TABLE  ',@ACTUAL_TABLE )
		EXEC sp_executesql @sql

	
          ----------------------------- IF (NORMAL MODE - ACTUAL DISCOUNT,RATE AND RATE PER UNIT ) ELSE (EDIT MODE- REINSERT ACTUAL DISCOUNT,RATE AND RATE PER UNIT FOR OLD AND NEWLY ADDED CCPD)  
          SET @SQL= 'INSERT INTO '+@ACTUAL_TABLE+' 
                      (PROJECTION_DETAILS_SID,
                       RS_MODEL_SID,
                       PERIOD_SID,
                       ACTUAL_RATE,
                       ACTUAL_SALES,
                       ACTUAL_RPU)
          SELECT PROJECTION_DETAILS_SID,
                 RS_MODEL_SID,
                 PERIOD_SID,
                 ACTUAL_RATE,
                 ACTUAL_AMOUNT,
                 ACTUAL_RPU
          FROM   #NM_ACTUAL_DISCOUNT'


		  EXEC sp_executesql @sql
          --------------------------------------------------------   
          IF OBJECT_ID('TEMPDB.DBO.#NM_DISCOUNT_PROJECTION', 'U') IS NOT NULL
            DROP TABLE #NM_DISCOUNT_PROJECTION;

          CREATE TABLE #NM_DISCOUNT_PROJECTION
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT,
               PERIOD_SID             INT,
               RS_MODEL_SID           INT
            )

         SET @SQL=CONCAT(' INSERT INTO #NM_DISCOUNT_PROJECTION
                      (PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       PERIOD_SID,
                       RS_MODEL_SID)
          SELECT PROJECTION_DETAILS_SID,
                 CCP_DETAILS_SID,
                 PERIOD_SID,
                 RS_MODEL_SID
          FROM   (SELECT PROJECTION_DETAILS_SID,
                         CCP_DETAILS_SID,
                         PERIOD_DATE,
                         PERIOD_SID,
                         RS_MODEL_SID
                  FROM   PERIOD
                         CROSS JOIN (SELECT NDPM.PROJECTION_DETAILS_SID,
                                            CCP_DETAILS_SID,
                                            RS_MODEL_SID
                                     FROM   #NM_DISCOUNT_PROJ_MASTER NDPM
                                     WHERE  NOT EXISTS
                                            (SELECT 1
                                             FROM
                                                ', @PROJECTION_TABLE ,' N
                                                        WHERE
                                            N.PROJECTION_DETAILS_SID =
      NDPM.PROJECTION_DETAILS_SID
      AND N.RS_MODEL_SID =
          NDPM.RS_MODEL_SID
     )) CCP
      WHERE  PERIOD_DATE BETWEEN ''', @PROJECTION_START_DATE,''' AND ''',
      @PROJECTION_END_DATE,''') A')
	  EXEC sp_executesql @sql
	
        SET @SQL='  INSERT INTO '+@PROJECTION_TABLE+' 
                      (PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       RS_MODEL_SID,
                       PROJECTION_RATE,
                       PROJECTION_SALES,
                       PROJECTION_RPU,
                       GROWTH)
          SELECT PROJECTION_DETAILS_SID,
                 NDP.PERIOD_SID,
                 NDP.RS_MODEL_SID,
                 PROJECTION_RATE,
                 PROJECTION_AMOUNT,
                 PROJECTION_RPU,
                 ISNULL(GROWTH, 0)
          FROM   #NM_DISCOUNT_PROJECTION NDP
                 LEFT JOIN (SELECT NDP.DISCOUNT_RATE,
                                   NDP.PERIOD_SID,
                                   NDP.PROJECTION_RATE,
                                   NDP.PROJECTION_SALES AS PROJECTION_AMOUNT,
                                   NDP.PROJECTION_RPU,
                                   NDP.GROWTH,
                                   NDP.RS_MODEL_SID,
                                   ACD.CCP_DETAILS_SID
                            FROM   NM_DISCOUNT_PROJECTION NDP
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON NDP.PROJECTION_DETAILS_SID =
                                              ACD.PROJECTION_DETAILS_SID) A
                        ON NDP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NDP.RS_MODEL_SID = A.RS_MODEL_SID
                           AND NDP.PERIOD_SID = A.PERIOD_SID'
   EXEC sp_executesql @sql
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