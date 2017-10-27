IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_M_SUPP_INSERT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_M_SUPP_INSERT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_M_SUPP_INSERT]( @PROJECTION_MASTER_SID INT,
                                               @MARKET_TYPE           VARCHAR(50),
                                               @USER_ID               INT,
                                               @SESSION_ID            VARCHAR(50))
AS
 

 BEGIN
      SET NOCOUNT ON


	  DECLARE  @PROJ_START_DATE               DATETIME,
               @PROJ_END_DATE                 DATETIME,
               @HEL_SID_FOR_SUPP_RS_CATEGORY  INT        = (SELECT HELPER_TABLE_SID FROM   HELPER_TABLE WHERE  LIST_NAME = 'RS_CATEGORY' AND DESCRIPTION = 'STATE_SUPP')
	    DECLARE @MASTER_TABLE   VARCHAR(200)= Concat('ST_M_SUPPLEMENTAL_DISC_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				@ACTUAL_TABLE   VARCHAR(200)= Concat('ST_M_SUPPLEMENTAL_DISC_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				@PROJECTION_TABLE   VARCHAR(200)= Concat('ST_M_SUPPLEMENTAL_DISC_PROJ_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
        DECLARE @CCP_HIERARCHY VARCHAR(200) = Concat('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
		        @SQL   NVARCHAR(MAX)
				
--------------------------------------------------------------------------(GALUAT29 CHANGE APPLIED HERE)-------------------------------------------------------------------------------------------------------------

SELECT @PROJ_START_DATE=FROM_DATE,@PROJ_END_DATE=TO_DATE FROM PROJECTION_MASTER WHERE PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID 
 


IF OBJECT_ID('TEMPDB..#PROJECTION_FREQUENCY') IS NOT NULL
DROP TABLE #PROJECTION_FREQUENCY
CREATE TABLE #PROJECTION_FREQUENCY
(
CCP_DETAILS_SID INT
)

SET @SQL=CONCAT('

INSERT INTO #PROJECTION_FREQUENCY
(
CCP_DETAILS_SID
)
SELECT 
	  
	    CCP_DETAILS_SID


FROM  ',@CCP_HIERARCHY)

EXEC sp_executesql @SQL



	    --CASE   WHEN FREQUENCY = 'M' THEN (SELECT DATEADD( MM , DATEDIFF(MM, 0, FROM_DATE), 0))
     --                                   WHEN FREQUENCY = 'Q' THEN (SELECT DATEADD( QQ , DATEDIFF(QQ, 0, FROM_DATE), 0))
			  --                          WHEN FREQUENCY = 'S' THEN (SELECT CASE WHEN DATEPART(QQ,FROM_DATE) = 1 or DATEPART(QQ,FROM_DATE) = 2 THEN   
					--				(SELECT DATEADD( MM ,0,(SELECT DATEADD( YYYY , DATEDIFF(YYYY, 0, FROM_DATE), 0)) )) ELSE  (SELECT DATEADD( MM ,6,(SELECT DATEADD( YYYY , DATEDIFF(YYYY, 0, FROM_DATE), 0)) ))END)
			  --                      ELSE (SELECT DATEADD( YYYY , DATEDIFF(YYYY, 0, FROM_DATE), 0))
			  --                      END
									
					--				FROM_DATE 
					--				AS PROJ_START_DATE ,
									
     --   CASE  WHEN FREQUENCY = 'M' THEN (SELECT DATEADD(DAY, -1, DATEADD(MM, DATEDIFF(MM, 0, TO_DATE) + 1, 0)))
     --                                   WHEN FREQUENCY = 'Q' THEN (SELECT DATEADD(DAY, -1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0)))
			  --                          WHEN FREQUENCY = 'S' THEN (SELECT CASE WHEN DATEPART(QQ,TO_DATE) = 1 or DATEPART(QQ,TO_DATE) = 2 THEN   
					--				(SELECT DATEADD(MONTH, -6, DATEADD(DAY, -1, DATEADD(YYYY, DATEDIFF(YYYY, 0, TO_DATE) + 1, 0)))) 
					--				ELSE  (SELECT DATEADD(DAY, -1, DATEADD(YYYY, DATEDIFF(YYYY, 0, TO_DATE) + 1, 0)))
					--				END)
			  --                      ELSE (SELECT  DATEADD(DAY, -1, DATEADD(YYYY, DATEDIFF(YYYY, 0, TO_DATE) + 1, 0)))
			  --                      END  
									
					--				TO_DATE 
					--				AS PROJ_END_DATE

	  
------------------------------------------------------------------------- TO FIND THE APPROVED CCP DETAILS START---------------------------------------------------------------------------
IF OBJECT_ID('TEMPDB..#APPROVED_CCP_DETAILS') IS NOT NULL
TRUNCATE TABLE #APPROVED_CCP_DETAILS

ELSE
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
  EXEC Prc_approved_ccp_details
            @PROJECTION_MASTER_SID,
            'MANDATED',
            @USER_ID,
            @SESSION_ID




------------------------------------------------------------------------ TO FIND THE APPROVED CCP DETAILS END-----------------------------------------------------------------------------------


-----------------------------------------------------------------------ST_M_SUPPLEMENTAL_DISC_MASTER STARTS HERE--------------------------------------------------------------------------
      IF OBJECT_ID('TEMPDB..#TEMP_MASTER') IS NOT NULL
        DROP TABLE #TEMP_MASTER

      CREATE TABLE #TEMP_MASTER
        (
        
           CCP_DETAILS_SID        INT,
           MARKET_TYPE            VARCHAR(50),
           ACTUAL_DISCOUNT        NUMERIC(22, 6),
           CASH_PAID_DATE         VARCHAR(10),
           ACTUAL_METHODOLOGY     VARCHAR(50),
           ACTUAL_CONTRACT_PRICE  NUMERIC(22, 6),
           ACTUAL_DISCOUNT_RATE1  NUMERIC(22, 6),
           ACTUAL_DISCOUNT_RATE2  NUMERIC(22, 6),
           CONTRACT_END_DATE      DATETIME
           
        )

     SET @SQL=CONCAT( '
	 INSERT INTO #TEMP_MASTER
                  (
                   CCP_DETAILS_SID,
                   MARKET_TYPE,
                   ACTUAL_DISCOUNT,
                   CASH_PAID_DATE,
                   --ACTUAL_METHODOLOGY,
                   --ACTUAL_CONTRACT_PRICE,
                   --ACTUAL_DISCOUNT_RATE1,
                   --ACTUAL_DISCOUNT_RATE2,
                   CONTRACT_END_DATE
                  )
      SELECT 
             PD.CCP_DETAILS_SID,
             @MARKET_TYPE,
             AM.AMOUNT,
             ''Q''
             + CAST(DATEPART(QQ, AM.ACCRUAL_ACTUAL_START_DATE) AS CHAR(1))
             + '' ''
             + CAST(DATEPART(YY, AM.ACCRUAL_ACTUAL_START_DATE) AS CHAR(4)) AS CASH_PAID_DATE,
            
             CNT_M.END_DATE
      FROM   ',@CCP_HIERARCHY,' PD
             JOIN CCP_DETAILS CCP
               ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
             JOIN CONTRACT_MASTER CNT_M
               ON CCP.CONTRACT_MASTER_SID = CNT_M.CONTRACT_MASTER_SID
             JOIN COMPANY_MASTER CM
               ON CCP.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID
             JOIN ITEM_MASTER IM
               ON CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
			
            
             OUTER APPLY(SELECT TOP 1 AM.AMOUNT,--LATEST CASH_PAID_DATE OF A CCP FOR SUPP DISC(OUTPUT: AMOUNT,ACCRUAL_ACTUAL_START_DATE) 
                                      AM.ACCRUAL_ACTUAL_START_DATE
                         FROM   ACTUALS_MASTER AM
                                JOIN RS_CONTRACT RS_C
                                  ON AM.PROVISION_ID = RS_C.RS_ID
                         WHERE  AM.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID
                                AND AM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID
                                AND AM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID
                                AND RS_C.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID
                                AND RS_C.RS_CATEGORY = ',@HEL_SID_FOR_SUPP_RS_CATEGORY,'
                         ORDER  BY CASH_PAID_DATE DESC) AM
      WHERE  CNT_M.INBOUND_STATUS <> ''D''
             AND CM.INBOUND_STATUS <> ''D''
             AND IM.INBOUND_STATUS <> ''D''
             AND NOT EXISTS (SELECT 1 --EDIT SCENARIO
                             FROM   ',@MASTER_TABLE,' SDM
                             WHERE  PD.CCP_DETAILS_SID = SDM.CCP_DETAILS_SID
                                    )')

EXEC SP_EXECUTESQL @SQL,N'@MARKET_TYPE VARCHAR(50)',@MARKET_TYPE=@MARKET_TYPE


--ST_M_SUPPLEMENTAL_DISC_MASTER INSERT STARTS HERE
 SET @SQL=CONCAT(' 
 
 INSERT INTO ',@MASTER_TABLE,' 
                  (CCP_DETAILS_SID,
                   MARKET_TYPE,
                   ACTUAL_DISCOUNT,
                   CASH_PAID_DATE,
                   --ACTUAL_METHODOLOGY,
                   --ACTUAL_CONTRACT_PRICE,
                   --ACTUAL_DISCOUNT_RATE1,
                   --ACTUAL_DISCOUNT_RATE2,
                   CONTRACT_END_DATE)
      SELECT a.CCP_DETAILS_SID,
             COALESCE(B.MARKET_TYPE, A.MARKET_TYPE),
             ACTUAL_DISCOUNT,
             COALESCE(B.CASH_PAID_DATE, A.CASH_PAID_DATE),
             --ACTUAL_METHODOLOGY,
             --ACTUAL_CONTRACT_PRICE,
             --ACTUAL_DISCOUNT_RATE1,
             --ACTUAL_DISCOUNT_RATE2,
             COALESCE(B.CONTRACT_END_DATE, A.CONTRACT_END_DATE)
			
      FROM   #TEMP_MASTER A
             LEFT OUTER JOIN (SELECT MARKET_TYPE,
                                     CASH_PAID_DATE,
                                     CONTRACT_END_DATE,
                                     ACD.CCP_DETAILS_SID
                              FROM   #APPROVED_CCP_DETAILS ACD
                                     INNER JOIN M_SUPPLEMENTAL_DISC_MASTER SDM
                                             ON ACD.PROJECTION_DETAILS_SID = SDM.PROJECTION_DETAILS_SID) B
                          ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID')



EXEC sp_executesql @SQL

      -----------------------------------------------ST_M_SUPPLEMENTAL_DISC_MASTER ENDS HERE--------------------------------------
    

 -----------------------------------------------------------------DELETE ST_M_SUPPLEMENTAL_DISC_ACTUALS DURING EDIT SCENARIO------------------------------------------------------------------
    SET @SQL='TRUNCATE TABLE '+@ACTUAL_TABLE
	EXEC sp_executesql @SQL

 ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
 
 -------------------------------------------------------------------------ST_M_SUPPLEMENTAL_DISC_ACTUALS STARTS HERE-------------------------------------------------------------------


        SET @SQL=CONCAT('INSERT INTO ',@ACTUAL_TABLE,'
                  (CCP_DETAILS_SID,
                   PERIOD_SID,
                   ACTUAL_SALES,
                   ACTUAL_RATE,
                   ACTUAL_RPU)
      SELECT DISTINCT B.CCP_DETAILS_SID,
                      p.PERIOD_SID,
                      SUM(DISCOUNT)
                        OVER (
                          PARTITION BY B.CCP_DETAILS_SID, P.MONTH, P.YEAR )                                                                                                  AS ACTUAL_SALES,
                      COALESCE(SUM(DISCOUNT)
                                 OVER (
                                   PARTITION BY B.CCP_DETAILS_SID, P.MONTH, P.YEAR ) / NULLIF(SUM(CASE
                                                                                                               WHEN FN_OP.QUANTITY_INCLUSION = ''Y'' THEN FN_OP.SALES
                                                                                                             END)
                                                                                                           OVER (
                                                                                                             PARTITION BY B.CCP_DETAILS_SID, P.MONTH, P.YEAR ), 0) * 100, 0) AS ACTUAL_RATE,
                      COALESCE(SUM(DISCOUNT)
                                 OVER (
                                   PARTITION BY B.CCP_DETAILS_SID, P.MONTH, P.YEAR ) / NULLIF(SUM(CASE
                                                                                                               WHEN FN_OP.QUANTITY_INCLUSION = ''Y'' THEN FN_OP.QUANTITY
                                                                                                             END)
                                                                                                           OVER (
                                                                                                             PARTITION BY B.CCP_DETAILS_SID, P.MONTH, P.YEAR ), 0), 0)       AS ACTUAL_RPU
                    
      FROM  #PROJECTION_FREQUENCY B 
	  CROSS JOIN PERIOD P 
	    LEFT JOIN ACTUALS_DETAILS FN_OP ON FN_OP.CCP_DETAILS_SID=B.CCP_DETAILS_SID AND FN_OP.PERIOD_SID=B.PERIOD_SID
	  WHERE P.PERIOD_SID BETWEEN DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, -3, GETDATE())), 0)  AND
            
	 DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0)  
     ')

	EXEC SP_EXECUTESQL @SQL

	
--------------------------------------------------------------------ST_M_SUPPLEMENTAL_DISC_ACTUALS ENDS HERE--------------------------------------------------------------------------------

---------------------------------------------------------------------ST_M_SUPPLEMENTAL_DISC_PROJ ENDS HERE----------------------------------------------------------------------------------
       SET @SQL=CONCAT(' INSERT INTO ',@PROJECTION_TABLE,'
                  (CCP_DETAILS_SID,
                   METHODOLOGY,
                   CONTRACT_PRICE,
                   DISCOUNT_RATE_1,
                   DISCOUNT_RATE_2,
                   ACCESS,
                   PARITY,
                   PARITY_DISCOUNT,
                   PARITY_REFERENCE,
                   PROJECTION_RATE,
                   PROJECTION_SALES,
                   PERIOD_SID)
      SELECT PF.CCP_DETAILS_SID,
             COALESCE(A.METHODOLOGY, FM.FORMULA_DESC),
             COALESCE(A.CONTRACT_PRICE, FM.CONTRACT_PRICE_1),
             COALESCE(A.DISCOUNT_RATE_1, FM.REBATE_PERCENT_1),
             COALESCE(A.DISCOUNT_RATE_2, FM.REBATE_PERCENT_2),
             A.ACCESS,
             A.PARITY,
             A.PARITY_DISCOUNT,
             A.PARITY_REFERENCE,
             A.PROJECTION_RATE,
             A.PROJECTION_SALES,
             P.PERIOD_SID
      FROM   #PROJECTION_FREQUENCY PF
	         JOIN CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID= PF.CCP_DETAILS_SID 
             CROSS JOIN PERIOD P
             JOIN COMPANY_MASTER CM
               ON CCP.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID
             JOIN ITEM_MASTER IM
               ON CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID 
             OUTER APPLY (SELECT TOP 1     COALESCE(  FM1.FORMULA_DESC    ,    FM.FORMULA_DESC     ) AS FORMULA_DESC,
                                           COALESCE(  FM1.CONTRACT_PRICE_1,    FM.CONTRACT_PRICE_1 ) AS CONTRACT_PRICE_1,
                                           COALESCE(  FM1.REBATE_PERCENT_1,    FM.REBATE_PERCENT_1 ) AS REBATE_PERCENT_1,
                                           COALESCE(  FM1.REBATE_PERCENT_2,    FM.REBATE_PERCENT_2 ) AS REBATE_PERCENT_2
                          FROM   RS_CONTRACT RS_C
                                 JOIN RS_CONTRACT_DETAILS RS_D
                                   ON RS_C.RS_CONTRACT_SID = RS_D.RS_CONTRACT_SID
                                      AND RS_C.RS_CATEGORY = ',@HEL_SID_FOR_SUPP_RS_CATEGORY,' -- TO IDENTIFY ONLY SUPPLEMENTAL DISCOUNT
									  LEFT JOIN FORMULA_DETAILS_MASTER FM1 ON FM1.FORMULA_ID = RS_D.FORMULA_ID
                                 LEFT JOIN RS_CONTRACT_DETAILS_FR RS_D_FR
                                   ON RS_D.RS_CONTRACT_DETAILS_SID = RS_D_FR.RS_CONTRACT_DETAILS_SID
                                 LEFT JOIN FORMULA_DETAILS_MASTER FM
                                   ON FM.FORMULA_ID =  RS_D_FR.FORMULA_ID
                                      AND FM.COMPANY_ID = CM.COMPANY_ID
                                      AND FM.ITEM_ID = IM.ITEM_ID
                          WHERE  RS_C.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID
                                 AND FM.INBOUND_STATUS <> ''D''
                                 OR RS_D_FR.INBOUND_STATUS <> ''D''
                                 AND ( FM1.START_DATE = P.PERIOD_DATE
                                        OR FM.START_DATE <= P.PERIOD_DATE )
                                 AND ( FM1.END_DATE >= DATEADD(D, -1, DATEADD(M, DATEDIFF(M, 0, P.PERIOD_DATE) + 1, 0))
                                        OR ISNULL(FM.END_DATE, 1) = 1 ) --LAST DAY OF THE MONTH + END_DATE NULL HANDLING.
                         )FM
             LEFT OUTER JOIN (SELECT ACD.CCP_DETAILS_SID,
                                     SDP.CONTRACT_PRICE,
                                     DISCOUNT_RATE_1,
                                     DISCOUNT_RATE_2,
                                     METHODOLOGY,
                                     ACCESS,
                                     PARITY,
                                     PARITY_REFERENCE,
                                     PARITY_DISCOUNT,
                                     PROJECTION_RATE,
                                     PROJECTION_SALES,
                                     PERIOD_SID
                              FROM   #APPROVED_CCP_DETAILS ACD
                                     INNER JOIN M_SUPPLEMENTAL_DISC_PROJ SDP
                                             ON ACD.PROJECTION_DETAILS_SID = SDP.PROJECTION_DETAILS_SID) A
                          ON A.CCP_DETAILS_SID = PF.CCP_DETAILS_SID
                             AND A.PERIOD_SID = P.PERIOD_SID
      WHERE  P.PERIOD_DATE BETWEEN  DATEADD( YYYY , DATEDIFF(YYYY, 0, @PROJ_START_DATE ), 0) and  DATEADD(DAY, -1, DATEADD(YYYY, DATEDIFF(YYYY, 0, @PROJ_END_DATE ) + 1, 0)) 
             AND NOT EXISTS (SELECT 1
                             FROM   ',@PROJECTION_TABLE,' MSDP
                             WHERE  MSDP.CCP_DETAILS_SID = PF.CCP_DETAILS_SID
                                    AND MSDP.PERIOD_SID = P.PERIOD_SID
                                    )')



EXEC SP_EXECUTESQL @SQL,N'@PROJ_START_DATE DATETIME,@PROJ_END_DATE DATETIME' , @PROJ_START_DATE=@PROJ_START_DATE,@PROJ_END_DATE=@PROJ_END_DATE
  --------------------------------------------------------------------------ST_M_SUPPLEMENTAL_DISC_PROJ ENDS HERE--------------------------------------------------------------------------
  END
