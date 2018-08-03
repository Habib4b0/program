IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_ARM_RETURN_RATE' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].PRC_ARM_RETURN_RATE 
  END 

GO 

CREATE PROCEDURE [dbo].[PRC_ARM_RETURN_RATE] (@PROJECTION_MASTER_SID INT, 
                                         @USER_ID               INT, 
                                         @SESSION_ID            INT) 
AS 

  BEGIN 
      SET NOCOUNT ON 

      BEGIN TRY 


DECLARE @PERIOD_SID      SMALLINT, 
        @BUISNESS_UNIT   INT, 
        @PERIOD_DATE     DATETIME, 
        @VERSION         VARCHAR(20),
		@SQL    NVARCHAR(MAX)



DECLARE @ARM_RETURN_RATE_TABLE  VARCHAR(200) = CONCAT('ST_ARM_RETURN_RATE_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
----------------Creating all necessray temp tables(all ddl statements) for the logic Starts here (PE010 CodeGuarderror)
IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_DETAILS') IS NOT NULL 
  DROP TABLE #TEMP_ARM_PROJ_DETAILS 

CREATE TABLE #TEMP_ARM_PROJ_DETAILS 
  ( 
     ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL, 
     PROJECTION_MASTER_SID      INT NOT NULL, 
     CCP_DETAILS_SID            INT NOT NULL, 
     RS_MODEL_SID               INT NOT NULL,  
     CONTRACT_MASTER_SID        INT NOT NULL, 
     COMPANY_MASTER_SID         INT NOT NULL, 
     ITEM_MASTER_SID            INT NOT NULL, 
     PERIOD_SID                 INT NOT NULL, 
     METHODOLGY                 VARCHAR(50) NOT NULL 
  ) 
		        
IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_ITEM') IS NOT NULL 
  DROP TABLE #TEMP_ARM_PROJ_ITEM 

CREATE TABLE #TEMP_ARM_PROJ_ITEM 
  ( 
     PROJECTION_MASTER_SID INT NOT NULL, 
     ITEM_MASTER_SID       INT NOT NULL, 
     ITEM_ID               VARCHAR(50) NOT NULL, 
     UDC2                  VARCHAR(20) NULL
     PRIMARY KEY ( ITEM_ID, ITEM_MASTER_SID, PROJECTION_MASTER_SID ) 
  ) 

IF OBJECT_ID('TEMPDB..#A') IS NOT NULL 
  DROP TABLE #A 

CREATE TABLE #A 
  ( 
     ITEM_MASTER_SID INT NOT NULL, 
     UDC1            VARCHAR(50) NULL 
  ) 

IF OBJECT_ID('TEMPDB..#ITEM_UDC1') IS NOT NULL 
  DROP TABLE #ITEM_UDC1 

CREATE TABLE #ITEM_UDC1 
  ( 
     ITEM_MASTER_SID INT NOT NULL, 
     UDC1            VARCHAR(50) NULL 
  ) 

IF OBJECT_ID('TEMPDB..#TIER_RATE') IS NOT NULL 
  DROP TABLE #TIER_RATE 

CREATE TABLE #TIER_RATE 
  ( 
     ITEM_MASTER_SID INT NOT NULL, 
     UDC2            VARCHAR(50) NULL, 
     RATE            NUMERIC(22, 6) NULL
  ) 

IF OBJECT_ID('TEMPDB..#NATURAL_RATE') IS NOT NULL 
  DROP TABLE #NATURAL_RATE 

CREATE TABLE #NATURAL_RATE 
  ( 
     ITEM_MASTER_SID INT NOT NULL, 
     UDC2            VARCHAR(50) NULL, 
     RATE            NUMERIC(22, 6) NULL 
  ) 
----------------Creating all necessray temp tables(all ddl statements) for the logic Ends here (PE010 CodeGuarderror)
--TAKING PERIOD AND BU AND GL 
SELECT @PERIOD_SID = P.PERIOD_SID, 
       @PERIOD_DATE = P.PERIOD_DATE 
FROM   PROJECTION_MASTER A 
       JOIN PERIOD P 
         ON CONVERT(DATETIME, DATEADD(MM, -1, DATEADD(DD, 1, EOMONTH(A.FROM_DATE, 0)))) = P.PERIOD_DATE
WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

SELECT @BUISNESS_UNIT = BU_COMPANY_MASTER_SID 
FROM   ARM_ADJUSTMENT_MASTER 
WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 


SELECT @VERSION = SUBSTRING(CAST(DATENAME(MM, @PERIOD_DATE) AS VARCHAR(20)),1,3) 
                  + '-' 
                  + CAST(DATEPART(YY, @PERIOD_DATE) AS VARCHAR(10))  

INSERT INTO #TEMP_ARM_PROJ_DETAILS 
            (ARM_ADJUSTMENT_DETAILS_SID, 
             PROJECTION_MASTER_SID, 
             CCP_DETAILS_SID, 
             RS_MODEL_SID, 
             CONTRACT_MASTER_SID, 
             COMPANY_MASTER_SID, 
             ITEM_MASTER_SID, 
             PERIOD_SID, 
             METHODOLGY) 
SELECT ARM_ADJUSTMENT_DETAILS_SID, 
       AD.PROJECTION_MASTER_SID, 
       AD.CCP_DETAILS_SID, 
       AD.RS_MODEL_SID, 
       CD.CONTRACT_MASTER_SID, 
       CD.COMPANY_MASTER_SID, 
       CD.ITEM_MASTER_SID, 
       @PERIOD_SID AS PERIOD_SID, 
       HT.DESCRIPTION 
FROM   ARM_ADJUSTMENT_DETAILS AD 
       JOIN CCP_DETAILS CD 
         ON AD.CCP_DETAILS_SID = CD.CCP_DETAILS_SID 
       JOIN ARM_ADJUSTMENT_MASTER AM 
         ON AM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 
       JOIN ARM_ADJUSTMENT_CONFIG AC 
         ON AC.ARM_ADJUSTMENT_CONFIG_SID = AM.TRANSACTION_TYPE 
       JOIN HELPER_TABLE HT 
         ON HT.HELPER_TABLE_SID = AC.METHODOLGY 
WHERE  AD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

--PULLING PRODUCT RELATED INFORMATION FOR CURRENT PROJECTION STARTS HERE 
INSERT INTO #TEMP_ARM_PROJ_ITEM 
            (ITEM_ID, 
             ITEM_MASTER_SID, 
             PROJECTION_MASTER_SID, 
             UDC2) 
SELECT DISTINCT IM.ITEM_ID, 
                B.ITEM_MASTER_SID, 
                @PROJECTION_MASTER_SID, 
                HT2.DESCRIPTION AS UDC2 
FROM   #TEMP_ARM_PROJ_DETAILS A 
       JOIN CCP_DETAILS B 
         ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID 
       JOIN ITEM_MASTER IM 
         ON IM.ITEM_MASTER_SID = B.ITEM_MASTER_SID
		 AND IM.ORGANIZATION_KEY=@BUISNESS_UNIT 
       LEFT JOIN UDCS U 
              ON U.MASTER_SID = B.ITEM_MASTER_SID 
                 AND U.MASTER_TYPE = 'ITEM_MASTER' 
       LEFT JOIN HELPER_TABLE HT2 
              ON HT2.HELPER_TABLE_SID = U.UDC2 

----PULLING ALL ITEMS UDC VALUES FOR PRESENTED IN INTERFACE 
INSERT INTO #A 
            (ITEM_MASTER_SID, 
             UDC1) 
SELECT DISTINCT RM.ITEM_MASTER_SID, 
                HT1.DESCRIPTION AS UDC1 
FROM   RETURNS_MASTER RM 
       JOIN UDCS U 
         ON U.MASTER_SID = RM.ITEM_MASTER_SID 
            AND U.MASTER_TYPE = 'ITEM_MASTER' 
       JOIN ITEM_MASTER IM 
         ON IM.ITEM_MASTER_SID = RM.ITEM_MASTER_SID 
            AND IM.ORGANIZATION_KEY = @BUISNESS_UNIT 
       LEFT JOIN HELPER_TABLE HT1 
              ON HT1.HELPER_TABLE_SID = U.UDC1 
DECLARE @DELIMITER VARCHAR(50) 

SET @DELIMITER=','; 

WITH CTE 
     AS (SELECT ITEM_MASTER_SID, 
                CAST('<M>' + REPLACE(UDC1, @DELIMITER, '</M><M>') 
                     + '</M>' AS XML) AS UDC1 
         FROM   #A)
INSERT INTO  #ITEM_UDC1(ITEM_MASTER_SID,UDC1)
SELECT ITEM_MASTER_SID, 
               Split.a.value('.', 'VARCHAR(MAX)') AS UDC1 
        FROM   CTE 
               CROSS APPLY UDC1.nodes('/M')SPLIT(A)

----------------------PULLING RETURNS INFORMATION FOR TIER-1 RATE AND TIER-2 RATE FOR THE SELECTED TIME PERIOD COMBINATION 
INSERT INTO #TIER_RATE 
            (ITEM_MASTER_SID, 
             UDC2, 
             RATE) 
SELECT ITEM_MASTER_SID, 
       UDC2, 
       ISNULL(TIER1_RATE, TIER2_RATE) AS RATE 
FROM   #TEMP_ARM_PROJ_ITEM TA 
       LEFT JOIN (SELECT ISNULL(SUM(CUM_RETURN_UNITS) / COALESCE(SUM(ORIG_SALE_UNITS), 0), 0) AS TIER1_RATE,
                         IU1.UDC1                                                             AS METHODOLGY
                  FROM   RETURNS_MASTER RM 
                         JOIN #ITEM_UDC1 IU1 
                           ON RM.ITEM_MASTER_SID = IU1.ITEM_MASTER_SID 
                              AND IU1.UDC1 = 'TIER 1' 
                              AND RM.VERSION = @VERSION
							  AND RETURN_COMPLETE != 'N' 
                              AND RM.WITHIN_50QRTILE != '1'  
                  GROUP  BY IU1.UDC1) A 
              ON TA.UDC2 = A.METHODOLGY 
       LEFT JOIN (SELECT ISNULL(SUM(CUM_RETURN_UNITS) / COALESCE(SUM(ORIG_SALE_UNITS), 0), 0) AS TIER2_RATE,
                         IU2.UDC1                                                             AS METHODOLGY
                  FROM   RETURNS_MASTER RM 
                         JOIN #ITEM_UDC1 IU2 
                           ON RM.ITEM_MASTER_SID = IU2.ITEM_MASTER_SID 
                              AND IU2.UDC1 = 'TIER 2' 
                              AND RM.VERSION = @VERSION 
							  AND RETURN_COMPLETE != 'N' 
                             AND RM.WITHIN_50QRTILE != '1' 
                  GROUP  BY IU2.UDC1) B 
              ON TA.UDC2 = B.METHODOLGY 

-----UDC2 NATURAL RATE CALCULATION  STARTS HERE 
INSERT INTO #NATURAL_RATE 
            (ITEM_MASTER_SID, 
             UDC2, 
             RATE) 
SELECT ITEM_MASTER_SID, 
       DESCRIPTION          AS UDC2, 
       EXPECTED_RETURN_RATE AS RATE 
FROM   (SELECT TAM.ITEM_MASTER_SID, 
               HT.DESCRIPTION, 
               RM.EXPECTED_RETURN_RATE, 
               ROW_NUMBER() 
                 OVER( PARTITION BY RM.ITEM_MASTER_SID
                   ORDER BY ORIG_SALE_MONTH DESC) AS RN 
        FROM   RETURNS_MASTER RM 
               JOIN UDCS U 
                 ON U.MASTER_SID = RM.ITEM_MASTER_SID 
                    AND U.MASTER_TYPE = 'ITEM_MASTER' 
               JOIN HELPER_TABLE HT 
                 ON HT.HELPER_TABLE_SID = U.UDC2 
                    AND HT.DESCRIPTION = 'NATURAL RATE' 
               JOIN #TEMP_ARM_PROJ_ITEM TAM 
                 ON TAM.ITEM_MASTER_SID = RM.ITEM_MASTER_SID 
                    AND TAM.UDC2 = 'NATURAL RATE' 
                    AND RETURN_COMPLETE != 'Y' 
                    AND RM.VERSION = @VERSION) A 
WHERE  A.RN = 1 

------------INSERTION INTO MAIN TABLE STARTS HERE 

SET @SQL=''

SET @SQL=CONCAT('IF EXISTS (SELECT 1 FROM ', @ARM_RETURN_RATE_TABLE, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
                                          ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
					                   BEGIN 
												TRUNCATE TABLE ', @ARM_RETURN_RATE_TABLE, '  
									   END
				INSERT INTO ',@ARM_RETURN_RATE_TABLE, 
                            '(ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             METHODOLOGY,
							 RATE 
							 )
                SELECT TAP.ARM_ADJUSTMENT_DETAILS_SID AS ARM_ADJUSTMENT_DETAILS_SID, 
       TAP.PERIOD_SID                 AS PERIOD_SID, 
       ISNULL(TR.UDC2, NR.UDC2)       AS METHODOLOGY, 
       ISNULL(ISNULL(TR.RATE, NR.RATE/100)  * 100,0)      AS RATE 
FROM   #TEMP_ARM_PROJ_DETAILS TAP 
       JOIN #TEMP_ARM_PROJ_ITEM TAM 
         ON TAP.ITEM_MASTER_SID = TAM.ITEM_MASTER_SID 
       LEFT JOIN #TIER_RATE TR 
              ON TAM.ITEM_MASTER_SID = TR.ITEM_MASTER_SID 
       LEFT JOIN #NATURAL_RATE NR 
              ON NR.ITEM_MASTER_SID = TR.ITEM_MASTER_SID ')
					
                    EXEC sp_executesql @SQL


      END TRY 

      BEGIN CATCH 
          DECLARE @ERRORMESSAGE NVARCHAR(4000); 
          DECLARE @ERRORSEVERITY INT; 
          DECLARE @ERRORSTATE INT; 
          DECLARE @ERRORNUMBER INT; 
          DECLARE @ERRORPROCEDURE VARCHAR(200); 
          DECLARE @ERRORLINE INT; 

          EXEC [dbo].USPERRORCOLLECTOR 

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
  GO