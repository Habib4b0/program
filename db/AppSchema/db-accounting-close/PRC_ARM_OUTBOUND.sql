IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_ARM_OUTBOUND'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_ARM_OUTBOUND]
  END

GO
/*
CREATE PROCEDURE [dbo].[PRC_ARM_OUTBOUND] (@DEDUCTION_LEVEL        INT, 
                                          @TRANSACTION_LEVEL      BIT, 
                                          @DEDUCTION_VALUE        VARCHAR(200), 
                                          @WORKFLOW_ID            VARCHAR(50), 
                                          @WORKFLOW_NAME          VARCHAR(30), 
                                          @GL_COMPANY_MASTER_SID  INT, 
                                          @BU_COMPANY_MASTER_SID  INT, 
                                          @COMPANY_NO             VARCHAR(100), 
                                          @COMPANY_NAME           VARCHAR(100), 
                                          @ITEM_NO                VARCHAR(100), 
                                          @ITEM_NAME              VARCHAR(100), 
                                          @BRAND_NAME             VARCHAR(100), 
                                          @GL_DATE                DATETIME, 
                                          @CREATED_DATE           DATETIME, 
                                          @ADJUSTMENT_TYPE        VARCHAR(100), 
                                          @ACCOUNT_CATEGORY       INT, 
                                          @ACCOUNT_TYPE           INT, 
                                          @ACCOUNT                VARCHAR(100), 
                                          @POSITION_INDICATOR     VARCHAR(100), 
                                          @ADJUSTMENT_LEVEL       INT, 
                                          @REDEMPTION_FROM_PERIOD DATETIME, 
                                          @REDEMPTION_TO_PERIOD   DATETIME, 
                                          @USER_ID                INT, 
                                          @SESSION_ID             INT) 
AS 
  BEGIN 
      SET NOCOUNT ON
SELECT @DEDUCTION_VALUE = NULLIF(@DEDUCTION_VALUE, '*'), 
       @WORKFLOW_ID = NULLIF(@WORKFLOW_ID, '*'), 
       @WORKFLOW_NAME = NULLIF(@WORKFLOW_NAME, '*'), 
       @COMPANY_NO = NULLIF(@COMPANY_NO, '*'), 
       @COMPANY_NAME = NULLIF(@COMPANY_NAME, '*'), 
       @ITEM_NO = NULLIF(@ITEM_NO, '*'), 
       @ITEM_NAME = NULLIF(@ITEM_NAME, '*'), 
       @BRAND_NAME = NULLIF(@BRAND_NAME, '*'), 
       @ACCOUNT = NULLIF(@ACCOUNT, '*'), 
       @POSITION_INDICATOR = NULLIF(@POSITION_INDICATOR, '*'), 
       @ADJUSTMENT_TYPE = NULLIF(@ADJUSTMENT_TYPE, '*') 

DECLARE @VAR   VARCHAR(50), 
        @COUNT INT 

SET @VAR = CASE 
             WHEN @TRANSACTION_LEVEL = 0 THEN 'RESERVE DETAIL' 
             ELSE 'GTN DETAIL' 
           END 
SET @REDEMPTION_FROM_PERIOD = CASE 
                                WHEN @REDEMPTION_FROM_PERIOD IS NULL THEN 
                                DATEADD(MM, DATEDIFF(MM, 0, GETDATE( 
                                            )) - ( 120 ), 0) 
                                ELSE @REDEMPTION_FROM_PERIOD 
                              END 
SET @REDEMPTION_TO_PERIOD = CASE 
                              WHEN @REDEMPTION_TO_PERIOD IS NULL THEN 
                              DATEADD(MM, DATEDIFF(MM, 0, GETDATE()) 
                                          + ( 120 ), 0) 
                              ELSE @REDEMPTION_TO_PERIOD 
                            END 

DECLARE @DEDUCTION VARCHAR(100) 

SELECT @DEDUCTION = DESCRIPTION 
FROM   HELPER_TABLE 
WHERE  HELPER_TABLE_SID = @DEDUCTION_LEVEL 

IF OBJECT_ID('TEMPDB..#TEMP_DEDUCTION') IS NOT NULL 
  DROP TABLE #TEMP_DEDUCTION 

CREATE TABLE #TEMP_DEDUCTION 
  ( 
     DEDUCTION_VALUE VARCHAR(500), 
     PRIMARY KEY (DEDUCTION_VALUE) 
  ) 

INSERT INTO #TEMP_DEDUCTION 
            (DEDUCTION_VALUE) 
SELECT CONVERT(VARCHAR(500), TOKEN) 
FROM   UDF_SPLITSTRING(@DEDUCTION_VALUE, ',') 

IF OBJECT_ID('TEMPDB..#TEMP_HELPER') IS NOT NULL 
  DROP TABLE #TEMP_HELPER 

CREATE TABLE #TEMP_HELPER 
  ( 
     HELPER_TABLE_SID INT, 
     DESCRIPTION      VARCHAR(100), 
     LIST_NAME        VARCHAR(100), 
     PRIMARY KEY ( HELPER_TABLE_SID, LIST_NAME, DESCRIPTION ) 
  ) 

INSERT INTO #TEMP_HELPER 
            (HELPER_TABLE_SID, 
             DESCRIPTION, 
             LIST_NAME) 
SELECT HELPER_TABLE_SID, 
       DESCRIPTION, 
       LIST_NAME 
FROM   HELPER_TABLE 
WHERE  LIST_NAME IN ( 'RS_UDC1', 'RS_UDC2', 'RS_UDC3', 'RS_UDC4', 
                      'RS_UDC5', 'RS_UDC6', 'RS_TYPE', 'RS_CATEGORY', 
                      'REBATE_PROGRAM_TYPE', 'ARM_TRX_METHDOLOGY', 
                      'DEDUCTION_LEVELS' ) 

IF OBJECT_ID('TEMPDB..#TEMP_ARM_APPRVD_PROJ') IS NOT NULL 
  DROP TABLE #TEMP_ARM_APPRVD_PROJ 

CREATE TABLE #TEMP_ARM_APPRVD_PROJ 
  ( 
     APPROVED_ARM_ADJUSTMENT_DETAILS_SID INT, 
     APPRVD_PROJECTION_MASTER_SID        INT, 
     CCP_DETAILS_SID                     INT, 
     RS_MODEL_SID                        INT, 
     ADJUSTMENT_TYPE                     VARCHAR(50), 
     PERIOD_SID                          INT, 
     WORKFLOW_CREATED_DATE               DATETIME, 
     WORKFLOW_ID                         VARCHAR(50), 
     WORKFLOW_STATUS                     INT, 
     WORKFLOW_NAME                       VARCHAR(100), 
     APPRVD_ARM_ADJUSTMENT_CONFIG_SID    INT, 
     APPRVD_TRANSACTION_NAME             VARCHAR(50), 
     APPRVD_METHODOLOGY                  VARCHAR(50), 
     GL_DATE                             DATETIME, 
     GL_COMPANY_MASTER_SID               INT, 
     BU_COMPANY_MASTER_SID               INT, 
     PRIMARY KEY ( APPROVED_ARM_ADJUSTMENT_DETAILS_SID, CCP_DETAILS_SID, 
     RS_MODEL_SID, PERIOD_SID ) 
  ) 

INSERT INTO #TEMP_ARM_APPRVD_PROJ 
            (APPROVED_ARM_ADJUSTMENT_DETAILS_SID, 
             APPRVD_PROJECTION_MASTER_SID, 
             CCP_DETAILS_SID, 
             RS_MODEL_SID, 
             ADJUSTMENT_TYPE, 
             PERIOD_SID, 
             WORKFLOW_CREATED_DATE, 
             WORKFLOW_ID, 
             WORKFLOW_STATUS, 
             WORKFLOW_NAME, 
             APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
             APPRVD_TRANSACTION_NAME, 
             APPRVD_METHODOLOGY, 
             GL_DATE, 
             GL_COMPANY_MASTER_SID, 
             BU_COMPANY_MASTER_SID) 
SELECT AAD.ARM_ADJUSTMENT_DETAILS_SID AS APPROVED_ARM_ADJUSTMENT_DETAILS_SID, 
       PM.PROJECTION_MASTER_SID       AS APPRVD_PROJECTION_MASTER_SID, 
       AAD.CCP_DETAILS_SID, 
       AAD.RS_MODEL_SID, 
       --PM.FORECASTING_TYPE            AS ADJUSTMENT_TYPE, 
	    AC.TRANSACTION_NAME AS ADJUSTMENT_TYPE,
       P.PERIOD_SID                   AS PERIOD_SID, 
       WM.CREATED_DATE                AS WORKFLOW_CREATED_DATE, 
       WM.WORKFLOW_ID, 
       WM.WORKFLOW_STATUS_ID, 
       WM.WORKFLOW_DESCRPTION         AS WORKFLOW_NAME, 
       ARM_ADJUSTMENT_CONFIG_SID      AS APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
       TRANSACTION_NAME, 
       HT.DESCRIPTION                 AS METHODOLOGY, 
       AM.GL_IMPACT_DATE              AS GL_DATE, 
       PM.COMPANY_MASTER_SID          AS GL_COMPANY_MASTER_SID, 
       AM.BU_COMPANY_MASTER_SID 
FROM   WORKFLOW_MASTER WM 
       INNER JOIN ARM_ADJUSTMENT_DETAILS AAD 
               ON AAD.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID 
       INNER JOIN PROJECTION_MASTER PM 
               ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID 
       INNER JOIN ARM_ADJUSTMENT_MASTER AM 
               ON AM.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID 
       INNER JOIN ARM_ADJUSTMENT_CONFIG AC 
               ON AM.TRANSACTION_TYPE = AC.ARM_ADJUSTMENT_CONFIG_SID 
       INNER JOIN HELPER_TABLE HT 
               ON AC.METHODOLGY = HT.HELPER_TABLE_SID 
                  AND WM.WORKFLOW_STATUS_ID IN (SELECT HELPER_TABLE_SID 
                                               FROM   HELPER_TABLE 
                                               WHERE  DESCRIPTION = 'APPROVED' 
                                                      AND LIST_NAME = 
                                                          'WORKFLOWSTATUS') 
                  AND AM.CONFIGURATION_TYPE IN (SELECT HELPER_TABLE_SID 
                                               FROM   HELPER_TABLE 
                                               WHERE  DESCRIPTION = @VAR 
                                              ) 
       INNER JOIN PERIOD P 
               ON P.PERIOD_DATE BETWEEN IIF(DATEADD(DD, 1, EOMONTH(FROM_DATE, -1 
                                                           )) >= 
                                            @REDEMPTION_FROM_PERIOD, DATEADD( 
                                                                         DD, 1, 
                                                   EOMONTH(FROM_DATE, -1)), 
                                        @REDEMPTION_FROM_PERIOD) 
                                                   AND IIF( 
                                        DATEADD(DD, 1, EOMONTH( 
                                        TO_DATE, -1)) <= 
                                        @REDEMPTION_TO_PERIOD, 
                                                       DATEADD(DD, 1, EOMONTH( 
                                                       TO_DATE, -1)), 
                                                       @REDEMPTION_TO_PERIOD) 
WHERE  WORKFLOW_ID LIKE 'ARM%' 
       AND ( WM.WORKFLOW_DESCRPTION = @WORKFLOW_NAME 
              OR @WORKFLOW_NAME IS NULL ) 
       AND ( WM.WORKFLOW_ID = @WORKFLOW_ID 
              OR @WORKFLOW_ID IS NULL ) 
       AND ( AM.BU_COMPANY_MASTER_SID = @BU_COMPANY_MASTER_SID 
              OR @BU_COMPANY_MASTER_SID IS NULL ) 
       AND ( PM.COMPANY_MASTER_SID = @GL_COMPANY_MASTER_SID 
              OR @GL_COMPANY_MASTER_SID IS NULL ) 
       AND ( WM.CREATED_DATE = @CREATED_DATE 
              OR @CREATED_DATE IS NULL ) 

IF OBJECT_ID('TEMPDB..#TEMP_STATUS_AMOUNT') IS NOT NULL 
  DROP TABLE #TEMP_STATUS_AMOUNT 

CREATE TABLE #TEMP_STATUS_AMOUNT 
  ( 
     PROJECTION_MASTER_SID            INT, 
     ARM_ADJUSTMENT_DETAILS_SID       INT, 
     CCP_DETAILS_SID                  INT, 
     RS_MODEL_SID                     INT, 
     ADJUSTMENT_TYPE                  VARCHAR(50), 
     PERIOD_SID                       INT, 
     WORKFLOW_CREATED_DATE            DATETIME, 
     WORKFLOW_ID                      VARCHAR(50), 
     WORKFLOW_STATUS                  INT, 
     WORKFLOW_NAME                    VARCHAR(100), 
     AMOUNT                           NUMERIC(22, 6), 
     RATE                             NUMERIC(22, 6), 
     PIPELINE_ACCRUAL_RATE            NUMERIC(22, 6), 
     PIPELINE_INVENTORY_RATE          NUMERIC(22, 6), 
     DEMAND_ACCRUAL_RATE              NUMERIC(22, 6), 
     DEMAND_REFORECAST_RATE           NUMERIC(22, 6), 
     DEMAND_PAYMENT_RECON_RATE        NUMERIC(22, 6), 
     APPRVD_ARM_ADJUSTMENT_CONFIG_SID INT, 
     APPRVD_TRANSACTION_NAME          VARCHAR(50), 
     APPRVD_METHODOLOGY               VARCHAR(50) 
  ) 

INSERT INTO #TEMP_STATUS_AMOUNT 
            (PROJECTION_MASTER_SID, 
             ARM_ADJUSTMENT_DETAILS_SID, 
             CCP_DETAILS_SID, 
             RS_MODEL_SID, 
             ADJUSTMENT_TYPE, 
             PERIOD_SID, 
             WORKFLOW_CREATED_DATE, 
             WORKFLOW_ID, 
             WORKFLOW_STATUS, 
             WORKFLOW_NAME, 
             AMOUNT, 
             RATE, 
             APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
             APPRVD_TRANSACTION_NAME, 
             APPRVD_METHODOLOGY) 
SELECT A.APPRVD_PROJECTION_MASTER_SID        AS PROJECTION_MASTER_SID, 
       A.APPROVED_ARM_ADJUSTMENT_DETAILS_SID AS ARM_ADJUSTMENT_DETAILS_SID, 
       A.CCP_DETAILS_SID, 
       A.RS_MODEL_SID, 
       A.ADJUSTMENT_TYPE, 
       A.PERIOD_SID, 
       A.WORKFLOW_CREATED_DATE, 
       A.WORKFLOW_ID, 
       A.WORKFLOW_STATUS, 
       A.WORKFLOW_NAME, 
       CASE 
         WHEN A.APPRVD_METHODOLOGY = 'TRANSACTION 1' THEN SUM( 
         ISNULL(B.OVERRIDE, B.VARIANCE)) 
         WHEN A.APPRVD_METHODOLOGY = 'TRANSACTION 2' THEN SUM( 
         ISNULL(D.OVERRIDE, D.VARIANCE)) 
         WHEN A.APPRVD_METHODOLOGY = 'TRANSACTION 3' THEN SUM( 
         ISNULL(C.OVERRIDE, C.VARIANCE)) 
         WHEN A.APPRVD_METHODOLOGY = 'TRANSACTION 4' THEN SUM( 
         ISNULL(E.OVERRIDE, E.VARIANCE)) 
         WHEN A.APPRVD_METHODOLOGY = 'TRANSACTION 5' THEN SUM( 
         ISNULL(F.OVERRIDE, F.VARIANCE)) 
         WHEN A.APPRVD_METHODOLOGY = 'TRANSACTION 6' THEN SUM( 
         ISNULL(AIA.OVERRIDE, AIA.VARIANCE)) 
         ELSE SUM(ISNULL(ADF.OVERRIDE, ADF.VARIANCE)) 
       END                                   AS AMOUNT, 
       CASE 
         WHEN A.APPRVD_METHODOLOGY = 'TRANSACTION 1' THEN SUM(ISNULL(B.RATE, 0)) 
         WHEN A.APPRVD_METHODOLOGY = 'TRANSACTION 2' THEN SUM( 
         ISNULL(D.PROJECTED_RATE, 0)) 
         WHEN A.APPRVD_METHODOLOGY = 'TRANSACTION 3' THEN SUM(ISNULL(C.RATE, 0)) 
         WHEN A.APPRVD_METHODOLOGY = 'TRANSACTION 4' THEN SUM( 
         ISNULL(E.PROJECTED_RATE, 0)) 
         WHEN A.APPRVD_METHODOLOGY = 'TRANSACTION 5' THEN SUM( 
         ISNULL(F.PROJECTED_RATE, 0)) 
         WHEN A.APPRVD_METHODOLOGY = 'TRANSACTION 6' THEN SUM( 
         ISNULL(AIA.VARIANCE, 0)) 
         --INFLATION_FACTOR--NEED TO CHANGE 
         ELSE SUM(ISNULL(ADF.RATE, 0)) 
       END                                   AS RATE, 
       APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
       APPRVD_TRANSACTION_NAME, 
       APPRVD_METHODOLOGY 
FROM   #TEMP_ARM_APPRVD_PROJ A 
       LEFT JOIN ARM_PIPELINE_RATE B 
              ON A.APPROVED_ARM_ADJUSTMENT_DETAILS_SID = 
                 B.ARM_ADJUSTMENT_DETAILS_SID 
                 AND A.PERIOD_SID = B.PERIOD_SID 
       LEFT JOIN ARM_INVENTORY_RATE C 
              ON C.ARM_ADJUSTMENT_DETAILS_SID = 
                 A.APPROVED_ARM_ADJUSTMENT_DETAILS_SID 
                 AND C.PERIOD_SID = A.PERIOD_SID 
       LEFT JOIN ARM_DEMAND_ADJ_SUMMARY D 
              ON D.ARM_ADJUSTMENT_DETAILS_SID = 
                 A.APPROVED_ARM_ADJUSTMENT_DETAILS_SID 
                 AND D.PERIOD_SID = A.PERIOD_SID 
       LEFT JOIN ARM_DEMAND_RECON_SUMMARY E 
              ON E.ARM_ADJUSTMENT_DETAILS_SID = 
                 A.APPROVED_ARM_ADJUSTMENT_DETAILS_SID 
                 AND E.PERIOD_SID = A.PERIOD_SID 
       LEFT JOIN ARM_DEMAND_RF_TRUE_UP_SUMMARY F 
              ON F.ARM_ADJUSTMENT_DETAILS_SID = 
                 A.APPROVED_ARM_ADJUSTMENT_DETAILS_SID 
                 AND F.PERIOD_SID = A.PERIOD_SID 
       LEFT JOIN ARM_INFLATION_INVENTORY_ADJ AIA 
              ON AIA.ARM_ADJUSTMENT_DETAILS_SID = 
                 A.APPROVED_ARM_ADJUSTMENT_DETAILS_SID 
                 AND AIA.PERIOD_SID = A.PERIOD_SID 
       LEFT JOIN ARM_DISTRIBUTION_FEES_RATE ADF 
              ON ADF.ARM_ADJUSTMENT_DETAILS_SID = 
                 A.APPROVED_ARM_ADJUSTMENT_DETAILS_SID 
                 AND ADF.PERIOD_SID = A.PERIOD_SID 
GROUP  BY A.APPRVD_PROJECTION_MASTER_SID, 
          A.APPROVED_ARM_ADJUSTMENT_DETAILS_SID, 
          A.CCP_DETAILS_SID, 
          A.RS_MODEL_SID, 
          A.ADJUSTMENT_TYPE, 
          A.PERIOD_SID, 
          A.WORKFLOW_CREATED_DATE, 
          A.WORKFLOW_ID, 
          A.WORKFLOW_STATUS, 
          A.WORKFLOW_NAME, 
          APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
          APPRVD_TRANSACTION_NAME, 
          APPRVD_METHODOLOGY 

IF ( @TRANSACTION_LEVEL = 1 ) 
  BEGIN 
      IF OBJECT_ID('TEMPDB..#TEMP_GTN') IS NOT NULL 
        DROP TABLE #TEMP_GTN 

      CREATE TABLE #TEMP_GTN 
        ( 
           ARM_ADJUSTMENT_DETAILS           INT, 
           PERIOD_SID                       INT, 
           CONTRACT_MASTER_SID              INT, 
           CONTRACT_ID                      VARCHAR(50), 
           CONTRACT_NO                      VARCHAR(50), 
           [CONTRACT_NAME]                  VARCHAR(100), 
           COMPANY_MASTER_SID               INT, 
           COMPANY_ID                       VARCHAR(50), 
           COMPANY_NO                       VARCHAR(50), 
           COMPANY_NAME                     VARCHAR(100), 
           ITEM_MASTER_SID                  INT, 
           ITEM_ID                          VARCHAR(50), 
           ITEM_NO                          VARCHAR(50), 
           ITEM_NAME                        VARCHAR(100), 
           BRAND_MASTER_SID                 INT, 
           BRAND_ID                         VARCHAR(50), 
           BRAND_NAME                       VARCHAR(100), 
           RS_MODEL_SID                     INT, 
           RS_CATEGORY                      VARCHAR(50), 
           RS_TYPE                          VARCHAR(50), 
           REBATE_PROGRAM_TYPE              VARCHAR(50), 
           DEDUCTION_INCLUSION              INT,
           RS_ID                            VARCHAR(50), 
           RS_NAME                          VARCHAR(100), 
           RS_NO                            VARCHAR(50), 
           RS_UDC_1                         INT, 
           RS_UDC_2                         INT, 
           RS_UDC_3                         INT, 
           RS_UDC_4                         INT, 
           RS_UDC_5                         INT, 
           RS_UDC_6                         INT, 
           PROJECTION_MASTER_SID            INT, 
           WORKFLOW_CREATED_DATE            DATETIME, 
           WORKFLOW_ID                      VARCHAR(50), 
           WORKFLOW_STATUS                  INT, 
           WORKFLOW_NAME                    VARCHAR(100), 
           APPRVD_ARM_ADJUSTMENT_CONFIG_SID INT, 
           APPRVD_TRANSACTION_NAME          VARCHAR(50), 
           APPRVD_METHODOLOGY               VARCHAR(50) 
        ) 

      INSERT INTO #TEMP_GTN 
                  (ARM_ADJUSTMENT_DETAILS, 
                   PERIOD_SID, 
                   CONTRACT_MASTER_SID, 
                   CONTRACT_ID, 
                   CONTRACT_NO, 
                   [CONTRACT_NAME], 
                   COMPANY_MASTER_SID, 
                   COMPANY_ID, 
                   COMPANY_NO, 
                   COMPANY_NAME, 
                   ITEM_MASTER_SID, 
                   ITEM_ID, 
                   ITEM_NO, 
                   ITEM_NAME, 
                   BRAND_MASTER_SID, 
                   BRAND_ID, 
                   BRAND_NAME, 
                   RS_MODEL_SID, 
                   RS_CATEGORY, 
                   RS_TYPE, 
                   REBATE_PROGRAM_TYPE, 
                   DEDUCTION_INCLUSION, 
                   RS_ID, 
                   RS_NO, 
                   RS_NAME, 
                   RS_UDC_1, 
                   RS_UDC_2, 
                   RS_UDC_3, 
                   RS_UDC_4, 
                   RS_UDC_5, 
                   RS_UDC_6, 
                   PROJECTION_MASTER_SID, 
                   WORKFLOW_CREATED_DATE, 
                   WORKFLOW_ID, 
                   WORKFLOW_STATUS, 
                   WORKFLOW_NAME, 
                   APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
                   APPRVD_TRANSACTION_NAME, 
                   APPRVD_METHODOLOGY) 
      SELECT A.ARM_ADJUSTMENT_DETAILS_SID, 
             A.PERIOD_SID, 
             C.CONTRACT_MASTER_SID, 
             CON.CONTRACT_ID, 
             CON.CONTRACT_NO, 
             CON.[CONTRACT_NAME], 
             C.COMPANY_MASTER_SID, 
             CM.COMPANY_ID, 
             CM.COMPANY_NO, 
             CM.COMPANY_NAME, 
             C.ITEM_MASTER_SID, 
             IM.ITEM_ID, 
             IM.ITEM_NO, 
             IM.ITEM_NAME, 
             BM.BRAND_MASTER_SID, 
             BM.BRAND_ID, 
             BM.BRAND_NAME, 
             A.RS_MODEL_SID, 
             RS_CATEGORY, 
             RS_TYPE, 
             REBATE_PROGRAM_TYPE, 
             R.DEDUCTION_INCLUSION, 
             R.RS_ID, 
             R.RS_NO, 
             R.RS_NAME, 
             U.UDC1 AS RS_UDC_1, 
             U.UDC2 AS RS_UDC_2, 
             U.UDC3 AS RS_UDC_3, 
             U.UDC4 AS RS_UDC_4, 
             U.UDC5 AS RS_UDC_5, 
             U.UDC6 AS RS_UDC_6, 
             A.PROJECTION_MASTER_SID, 
             A.WORKFLOW_CREATED_DATE, 
             A.WORKFLOW_ID, 
             A.WORKFLOW_STATUS, 
             A.WORKFLOW_NAME, 
             A.APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
             A.APPRVD_TRANSACTION_NAME, 
             A.APPRVD_METHODOLOGY 
      FROM   #TEMP_STATUS_AMOUNT A 
             JOIN CCP_DETAILS C 
               ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID 
             JOIN ITEM_MASTER IM 
               ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID 
             JOIN COMPANY_MASTER CM 
               ON CM.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID 
             JOIN CONTRACT_MASTER CON 
               ON CON.CONTRACT_MASTER_SID = C.CONTRACT_MASTER_SID 
             JOIN BRAND_MASTER BM 
               ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID 
             JOIN RS_CONTRACT R 
               ON R.RS_MODEL_SID = A.RS_MODEL_SID 
                  AND R.CONTRACT_MASTER_SID = C.CONTRACT_MASTER_SID 
             JOIN ARM_DEDUCTION_SELECTION ADS 
               ON ADS.RS_CONTRACT_SID = R.RS_CONTRACT_SID 
                  AND ADS.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID 
             JOIN HELPER_TABLE HT 
               ON HT.HELPER_TABLE_SID = R.DEDUCTION_INCLUSION 
             JOIN UDCS U 
               ON U.MASTER_SID = R.RS_CONTRACT_SID 
                  AND U.MASTER_TYPE = 'RS_CONTRACT' 
             LEFT JOIN #TEMP_HELPER TH 
                    ON TH.HELPER_TABLE_SID = CASE 
                                               WHEN 
                       @DEDUCTION = 'DEDUCTION CATEGORY' 
                                             THEN 
                                               RS_CATEGORY 
                                               WHEN 
                       @DEDUCTION = 'DEDUCTION TYPE' 
                                             THEN 
                                               RS_TYPE 
                                               WHEN 
                       @DEDUCTION = 'DEDUCTION PROGRAM TYPE' 
                                             THEN 
                                               REBATE_PROGRAM_TYPE 
                                               WHEN 
                       @DEDUCTION = 'DEDUCTION CATEGORY 2' THEN 
                                               U.UDC2 
                                               WHEN 
                       @DEDUCTION = 'DEDUCTION CATEGORY 3' THEN 
                                               U.UDC3 
                                               WHEN 
                       @DEDUCTION = 'DEDUCTION CATEGORY 4' THEN 
                                               U.UDC4 
                                               WHEN 
                       @DEDUCTION = 'DEDUCTION CATEGORY 5' THEN 
                                               U.UDC5 
                                               WHEN 
                       @DEDUCTION = 'DEDUCTION CATEGORY 6' THEN 
                                               U.UDC6 
                                               ELSE '' --R.RS_ID 
                                             END 
             LEFT JOIN #TEMP_DEDUCTION TD 
                    ON TD.DEDUCTION_VALUE = CASE 
                                              WHEN 
                       @DEDUCTION IN ( 'DEDUCTION CATEGORY', 
                                       'DEDUCTION TYPE', 
                                       'DEDUCTION PROGRAM TYPE' 
                                       , 
                                       'DEDUCTION CATEGORY 2', 
                                       'DEDUCTION CATEGORY 3', 
                                       'DEDUCTION CATEGORY 4', 
                                              'DEDUCTION CATEGORY 5', 
                                       'DEDUCTION CATEGORY 6' ) THEN 
                                              TH.DESCRIPTION 
                                              ELSE R.RS_ID 
                                            END 
      WHERE  ( CM.COMPANY_NO = @COMPANY_NO 
                OR @COMPANY_NO IS NULL ) 
             AND ( CM.COMPANY_NAME = @COMPANY_NAME 
                    OR @COMPANY_NAME IS NULL ) 
             AND ( IM.ITEM_NO = @ITEM_NO 
                    OR @ITEM_NO IS NULL ) 
             AND ( IM.ITEM_NAME = @ITEM_NAME 
                    OR @ITEM_NAME IS NULL ) 
             AND ( BM.BRAND_NAME = @BRAND_NAME 
                    OR @BRAND_NAME IS NULL ) 

      IF OBJECT_ID('TEMPDB..#TEMP_GTN_RESULTS') IS NOT NULL 
        DROP TABLE #TEMP_GTN_RESULTS 

      SELECT A.WORKFLOW_ID, 
             A.WORKFLOW_NAME, 
             AAD.ADJUSTMENT_TYPE                   AS ADJUSTMENT_TYPE, 
             YEAR(AM.GL_IMPACT_DATE)               AS GL_YEAR, 
             MONTH(AM.GL_IMPACT_DATE)              AS GL_MONTH, 
             AM.GL_IMPACT_DATE                     GL_DATE, 
             P.PERIOD_DATE                         AS REDEMPTION_PERIOD, 
             GL.COMPANY_ID                         AS GL_COMPANY_ID, 
             GL.COMPANY_NO                         AS GL_COMPANY_NO, 
             GL.COMPANY_NAME                       AS GL_COMPANY_NAME, 
             BU.COMPANY_ID                         AS BUSINESS_UNIT_ID, 
             BU.COMPANY_NO                         AS BUSINESS_UNIT_NO, 
             BU.COMPANY_NAME                       AS BUSINESS_UNIT_NAME, 
             AAD.COST_CENTER                       AS COST_CENTER, 
             AAD.PROJECT                           AS PROJECT, 
             AAD.FUTURE_1                          AS FUTURE_1, 
             AAD.FUTURE_2                          AS FUTURE_2, 
             GL.COMPANY_ID + ' - ' + BU.COMPANY_ID + ' - ' 
             + AAD.COST_CENTER + ' - ' + AAD.ACCOUNT + ' - ' 
             + A.BRAND_ID + ' - ' + AAD.PROJECT + ' - ' 
             + AAD.FUTURE_1 + ' - ' + AAD.FUTURE_2 AS GL_STRING, 
             AAD.ACCOUNT_CATEGORY                  AS ACCOUNT_CATEGORY, 
             AAD.ACCOUNT_TYPE                      AS ACCOUNT_TYPE, 
             AAD.ACCOUNT                           AS ACCOUNT, 
             A.CONTRACT_ID                         AS CONTRACT_ID, 
             A.CONTRACT_NO                         AS CONTRACT_NO, 
             A.CONTRACT_NAME                       AS CONTRACT_NAME, 
             A.COMPANY_ID                          AS COMPANY_ID, 
             A.COMPANY_NO                          AS COMPANY_NO, 
             A.COMPANY_NAME                        AS COMPANY_NAME, 
             A.ITEM_ID                             AS ITEM_ID, 
             A.ITEM_NO                             AS ITEM_NO, 
             A.ITEM_NAME                           AS ITEM_NAME, 
             A.BRAND_ID                            AS BRAND_ID, 
             A.BRAND_NAME                          AS BRAND_NAME, 
             A.RS_ID                               AS DEDUCTION_ID, 
             A.RS_NO                               AS DEDUCTION_NO, 
             A.RS_NAME                             AS DEDUCTION_NAME, 
             A.RS_CATEGORY                         AS DEDUCTION_CATEGORY, 
             A.RS_TYPE                             AS DEDUCTION_TYPE, 
             A.REBATE_PROGRAM_TYPE                 AS DEDUCTION_PROGRAM, 
             A.DEDUCTION_INCLUSION                 AS DEDUCTION_INCLUSION, 
             A.RS_UDC_1                            AS DEDUCTION_UDC_1, 
             A.RS_UDC_2                            AS DEDUCTION_UDC_2, 
             A.RS_UDC_3                            AS DEDUCTION_UDC_3, 
             A.RS_UDC_4                            AS DEDUCTION_UDC_4, 
             A.RS_UDC_5                            AS DEDUCTION_UDC_5, 
             A.RS_UDC_6                            AS DEDUCTION_UDC_6, 
             DEDUCTION_AMOUNT = AMOUNT, 
             DEDUCTION_RATE = RATE, 
             AAD.UDC_1                             AS UDC_1, 
             AAD.UDC_2                             AS UDC_2, 
             AAD.UDC_3                             AS UDC_3, 
             AAD.UDC_4                             AS UDC_4, 
             AAD.UDC_5                             AS UDC_5, 
             AAD.UDC_6                             AS UDC_6, 
             WM.CREATED_BY                         AS WORKFLOW_CREATED_BY, 
             A.WORKFLOW_CREATED_DATE               AS WORKFLOW_CREATED_DATE, 
             APPROVED_BY                           AS WORKFLOW_APPROVED_BY, 
             APPROVED_DATE                         AS WORKFLOW_APPROVED_DATE, 
             PM.CREATED_DATE                       AS ADJUSTMENT_CREATED_DATE, 
             NULL                                  AS BATCH_ID, 
             NULL                                  AS ORIGINAL_BATCH_ID, 
             AAM.SOURCE                            AS SOURCE, 
             NULL                                  GL_POSTING_STATUS, 
             NULL                                  AS OUTBOUND_STATUS, 
             @USER_ID                              USER_ID, 
             @SESSION_ID                           SESSION_ID, 
             0                                     AS CHECK_RECORD, 
             0                                     AS ETL_CHECK_RECORD, 
             AAD.ADJUSTMENT_LEVEL                  AS ADJUSTMENT_LEVEL 
      INTO   #TEMP_GTN_RESULTS 
      FROM   #TEMP_GTN A 
             JOIN ARM_ADJUSTMENT_MASTER AM 
               ON A.PROJECTION_MASTER_SID = AM.PROJECTION_MASTER_SID 
             JOIN PROJECTION_MASTER PM 
               ON PM.PROJECTION_MASTER_SID = AM.PROJECTION_MASTER_SID 
             JOIN #TEMP_STATUS_AMOUNT TP 
               ON TP.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS 
                  AND TP.PERIOD_SID = A.PERIOD_SID 
             JOIN PERIOD P 
               ON P.PERIOD_SID = TP.PERIOD_SID 
             JOIN ARM_ADJ_RES_CONFIG_MASTER AAM 
               ON AAM.RS_CATEGORY = A.RS_CATEGORY 
                  AND AAM.RS_TYPE = A.RS_TYPE 
                  AND AAM.REBATE_PROGRAM_TYPE = A.REBATE_PROGRAM_TYPE 
                  AND AAM.GL_COMPANY_MASTER_SID = PM.COMPANY_MASTER_SID 
                  AND AM.BU_COMPANY_MASTER_SID = AM.BU_COMPANY_MASTER_SID 
                  AND AAM.CONFIGURATION_TYPE = 1 
             INNER JOIN COMPANY_MASTER GL 
                     ON GL.COMPANY_MASTER_SID = PM.COMPANY_MASTER_SID 
             INNER JOIN COMPANY_MASTER BU 
                     ON BU.COMPANY_MASTER_SID = AM.BU_COMPANY_MASTER_SID 
             JOIN ARM_ADJ_RES_CONFIG_DETAIL AAD 
               ON AAD.ARM_ADJ_RES_CONFIG_MASTER_SID = 
                  AAM.ARM_ADJ_RES_CONFIG_MASTER_SID 
                  AND AAD.ADJUSTMENT_TYPE = TP.APPRVD_ARM_ADJUSTMENT_CONFIG_SID 
             INNER JOIN WORKFLOW_MASTER WM 
                     ON WM.PROJECTION_MASTER_SID = TP.PROJECTION_MASTER_SID 
                        AND WM.WORKFLOW_ID = TP.WORKFLOW_ID 
                        AND WM.WORKFLOW_STATUS_ID = TP.WORKFLOW_STATUS 
      WHERE  ( AAD.ACCOUNT_CATEGORY = @ACCOUNT_CATEGORY 
                OR @ACCOUNT_CATEGORY IS NULL ) 
             AND ( AAD.ACCOUNT_TYPE = @ACCOUNT_TYPE 
                    OR @ACCOUNT_TYPE IS NULL ) 
             AND ( AAD.ACCOUNT = @ACCOUNT 
                    OR @ACCOUNT IS NULL ) 
             AND ( AAD.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE 
                    OR @ADJUSTMENT_TYPE IS NULL ) 

      INSERT INTO ST_ADJUSTMENT_GTN_DETAIL 
                  (WORKFLOW_ID, 
                   WORKFLOW_NAME, 
                   ADJUSTMENT_TYPE, 
                   GL_YEAR, 
                   GL_MONTH, 
                   GL_DATE, 
                   REDEMPTION_PERIOD, 
                   GL_COMPANY_ID, 
                   GL_COMPANY_NO, 
                   GL_COMPANY_NAME, 
                   BUSINESS_UNIT_ID, 
                   BUSINESS_UNIT_NO, 
                   BUSINESS_UNIT_NAME, 
                   COST_CENTER, 
                   PROJECT, 
                   FUTURE_1, 
                   FUTURE_2, 
                   GL_STRING, 
                   ACCOUNT_CATEGORY, 
                   ACCOUNT_TYPE, 
                   ACCOUNT, 
                   CONTRACT_ID, 
                   CONTRACT_NO, 
                   CONTRACT_NAME, 
                   COMPANY_ID, 
                   COMPANY_NO, 
                   COMPANY_NAME, 
                   ITEM_ID, 
                   ITEM_NO, 
                   ITEM_NAME, 
                   BRAND_ID, 
                   BRAND_NAME, 
                   DEDUCTION_ID, 
                   DEDUCTION_NO, 
                   DEDUCTION_NAME, 
                   DEDUCTION_CATEGORY, 
                   DEDUCTION_TYPE, 
                   DEDUCTION_PROGRAM, 
                   DEDUCTION_INCLUSION, 
                   DEDUCTION_UDC_1, 
                   DEDUCTION_UDC_2, 
                   DEDUCTION_UDC_3, 
                   DEDUCTION_UDC_4, 
                   DEDUCTION_UDC_5, 
                   DEDUCTION_UDC_6, 
                   DEDUCTION_AMOUNT, 
                   DEDUCTION_RATE, 
                   UDC_1, 
                   UDC_2, 
                   UDC_3, 
                   UDC_4, 
                   UDC_5, 
                   UDC_6, 
                   WORKFLOW_CREATED_BY, 
                   WORKFLOW_CREATED_DATE, 
                   WORKFLOW_APPROVED_BY, 
                   WORKFLOW_APPROVED_DATE, 
                   ADJUSTMENT_CREATED_DATE, 
                   BATCH_ID, 
                   ORIGINAL_BATCH_ID, 
                   SOURCE, 
                   GL_POSTING_STATUS, 
                   OUTBOUND_STATUS, 
                   USER_ID, 
                   SESSION_ID, 
                   CHECK_RECORD, 
                   ETL_CHECK_RECORD, 
                   ADJUSTMENT_LEVEL) 
      SELECT WORKFLOW_ID, 
             WORKFLOW_NAME, 
             ADJUSTMENT_TYPE, 
             GL_YEAR, 
             GL_MONTH, 
             GL_DATE, 
             REDEMPTION_PERIOD, 
             GL_COMPANY_ID, 
             GL_COMPANY_NO, 
             GL_COMPANY_NAME, 
             BUSINESS_UNIT_ID, 
             BUSINESS_UNIT_NO, 
             BUSINESS_UNIT_NAME, 
             COST_CENTER, 
             PROJECT, 
             FUTURE_1, 
             FUTURE_2, 
             GL_STRING, 
             ACCOUNT_CATEGORY, 
             ACCOUNT_TYPE, 
             ACCOUNT, 
             CONTRACT_ID, 
             CONTRACT_NO, 
             CONTRACT_NAME, 
             COMPANY_ID, 
             COMPANY_NO, 
             COMPANY_NAME, 
             ITEM_ID, 
             ITEM_NO, 
             ITEM_NAME, 
             BRAND_ID, 
             BRAND_NAME, 
             DEDUCTION_ID, 
             DEDUCTION_NO, 
             DEDUCTION_NAME, 
             DEDUCTION_CATEGORY, 
             DEDUCTION_TYPE, 
             DEDUCTION_PROGRAM, 
             DEDUCTION_INCLUSION, 
             DEDUCTION_UDC_1, 
             DEDUCTION_UDC_2, 
             DEDUCTION_UDC_3, 
             DEDUCTION_UDC_4, 
             DEDUCTION_UDC_5, 
             DEDUCTION_UDC_6, 
             DEDUCTION_AMOUNT, 
             DEDUCTION_RATE, 
             UDC_1, 
             UDC_2, 
             UDC_3, 
             UDC_4, 
             UDC_5, 
             UDC_6, 
             WORKFLOW_CREATED_BY, 
             WORKFLOW_CREATED_DATE, 
             WORKFLOW_APPROVED_BY, 
             WORKFLOW_APPROVED_DATE, 
             ADJUSTMENT_CREATED_DATE, 
             BATCH_ID, 
             ORIGINAL_BATCH_ID, 
             SOURCE, 
             GL_POSTING_STATUS, 
             OUTBOUND_STATUS, 
             USER_ID, 
             SESSION_ID, 
             CHECK_RECORD, 
             ETL_CHECK_RECORD, 
             ADJUSTMENT_LEVEL 
      FROM   #TEMP_GTN_RESULTS 
      WHERE  NOT EXISTS (SELECT 1 
                         FROM   #TEMP_GTN_RESULTS AGD 
                                INNER JOIN ADJUSTMENT_GTN_DETAIL SAGD 
                                        ON AGD.CONTRACT_ID = SAGD.CONTRACT_ID 
                                           AND AGD.ITEM_ID = SAGD.ITEM_ID 
                                           AND AGD.COMPANY_ID = SAGD.COMPANY_ID 
                                           AND AGD.DEDUCTION_ID = 
                                               SAGD.DEDUCTION_ID 
                                           AND AGD.WORKFLOW_ID = 
                                               SAGD.WORKFLOW_ID 
                                           AND AGD.ACCOUNT_CATEGORY = 
                                               SAGD.ACCOUNT_CATEGORY 
                                           AND AGD.ACCOUNT_TYPE = 
                                               SAGD.ACCOUNT_TYPE 
                                           AND AGD.ADJUSTMENT_TYPE = 
                                               SAGD.ADJUSTMENT_TYPE 
                                           AND AGD.ACCOUNT = SAGD.ACCOUNT 
                                           AND AGD.GL_COMPANY_ID = 
                                               SAGD.GL_COMPANY_ID 
                                           AND AGD.BUSINESS_UNIT_ID = 
                                               SAGD.BUSINESS_UNIT_ID 
                                           AND AGD.REDEMPTION_PERIOD = 
                                               SAGD.REDEMPTION_PERIOD 
                               ) 

      SET @COUNT = @@ROWCOUNT 

      INSERT INTO ST_ADJUSTMENT_GTN_DETAIL 
                  (WORKFLOW_ID, 
                   WORKFLOW_NAME, 
                   ADJUSTMENT_TYPE, 
                   GL_YEAR, 
                   GL_MONTH, 
                   GL_DATE, 
                   REDEMPTION_PERIOD, 
                   GL_COMPANY_ID, 
                   GL_COMPANY_NO, 
                   GL_COMPANY_NAME, 
                   BUSINESS_UNIT_ID, 
                   BUSINESS_UNIT_NO, 
                   BUSINESS_UNIT_NAME, 
                   COST_CENTER, 
                   PROJECT, 
                   FUTURE_1, 
                   FUTURE_2, 
                   GL_STRING, 
                   ACCOUNT_CATEGORY, 
                   ACCOUNT_TYPE, 
                   ACCOUNT, 
                   CONTRACT_ID, 
                   CONTRACT_NO, 
                   CONTRACT_NAME, 
                   COMPANY_ID, 
                   COMPANY_NO, 
                   COMPANY_NAME, 
                   ITEM_ID, 
                   ITEM_NO, 
                   ITEM_NAME, 
                   BRAND_ID, 
                   BRAND_NAME, 
                   DEDUCTION_ID, 
                   DEDUCTION_NO, 
                   DEDUCTION_NAME, 
                   DEDUCTION_CATEGORY, 
                   DEDUCTION_TYPE, 
                   DEDUCTION_PROGRAM, 
                   DEDUCTION_INCLUSION, 
                   DEDUCTION_UDC_1, 
                   DEDUCTION_UDC_2, 
                   DEDUCTION_UDC_3, 
                   DEDUCTION_UDC_4, 
                   DEDUCTION_UDC_5, 
                   DEDUCTION_UDC_6, 
                   DEDUCTION_AMOUNT, 
                   DEDUCTION_RATE, 
                   UDC_1, 
                   UDC_2, 
                   UDC_3, 
                   UDC_4, 
                   UDC_5, 
                   UDC_6, 
                   WORKFLOW_CREATED_BY, 
                   WORKFLOW_CREATED_DATE, 
                   WORKFLOW_APPROVED_BY, 
                   WORKFLOW_APPROVED_DATE, 
                   ADJUSTMENT_CREATED_DATE, 
                   BATCH_ID, 
                   ORIGINAL_BATCH_ID, 
                   SOURCE, 
                   GL_POSTING_STATUS, 
                   OUTBOUND_STATUS, 
                   USER_ID, 
                   SESSION_ID, 
                   CHECK_RECORD, 
                   ETL_CHECK_RECORD, 
                   ADJUSTMENT_LEVEL) 
      SELECT WORKFLOW_ID, 
             WORKFLOW_NAME, 
             ADJUSTMENT_TYPE, 
             GL_YEAR, 
             GL_MONTH, 
             GL_DATE, 
             REDEMPTION_PERIOD, 
             GL_COMPANY_ID, 
             GL_COMPANY_NO, 
             GL_COMPANY_NAME, 
             BUSINESS_UNIT_ID, 
             BUSINESS_UNIT_NO, 
             BUSINESS_UNIT_NAME, 
             COST_CENTER, 
             PROJECT, 
             FUTURE_1, 
             FUTURE_2, 
             GL_STRING, 
             ACCOUNT_CATEGORY, 
             ACCOUNT_TYPE, 
             ACCOUNT, 
             CONTRACT_ID, 
             CONTRACT_NO, 
             CONTRACT_NAME, 
             COMPANY_ID, 
             COMPANY_NO, 
             COMPANY_NAME, 
             ITEM_ID, 
             ITEM_NO, 
             ITEM_NAME, 
             BRAND_ID, 
             BRAND_NAME, 
             DEDUCTION_ID, 
             DEDUCTION_NO, 
             DEDUCTION_NAME, 
             DEDUCTION_CATEGORY, 
             DEDUCTION_TYPE, 
             DEDUCTION_PROGRAM, 
             DEDUCTION_INCLUSION, 
             DEDUCTION_UDC_1, 
             DEDUCTION_UDC_2, 
             DEDUCTION_UDC_3, 
             DEDUCTION_UDC_4, 
             DEDUCTION_UDC_5, 
             DEDUCTION_UDC_6, 
             DEDUCTION_AMOUNT, 
             DEDUCTION_RATE, 
             UDC_1, 
             UDC_2, 
             UDC_3, 
             UDC_4, 
             UDC_5, 
             UDC_6, 
             WORKFLOW_CREATED_BY, 
             WORKFLOW_CREATED_DATE, 
             WORKFLOW_APPROVED_BY, 
             WORKFLOW_APPROVED_DATE, 
             ADJUSTMENT_CREATED_DATE, 
             BATCH_ID, 
             NULL AS ORIGINAL_BATCH_ID, 
             SOURCE, 
             NULL AS GL_POSTING_STATUS, 
             NULL AS OUTBOUND_STATUS, 
             @USER_ID, 
             @SESSION_ID, 
             0    AS CHECK_RECORD, 
             0    AS ETL_CHECK_RECORD, 
             ADJUSTMENT_LEVEL 
      FROM   ADJUSTMENT_GTN_DETAIL 
      WHERE  EXISTS (SELECT 1 
                     FROM   #TEMP_GTN_RESULTS AGD 
                            INNER JOIN ADJUSTMENT_GTN_DETAIL SAGD 
                                    ON AGD.CONTRACT_ID = SAGD.CONTRACT_ID 
                                       AND AGD.ITEM_ID = SAGD.ITEM_ID 
                                       AND AGD.COMPANY_ID = SAGD.COMPANY_ID 
                                       AND AGD.DEDUCTION_ID = SAGD.DEDUCTION_ID 
                                       AND AGD.WORKFLOW_ID = SAGD.WORKFLOW_ID 
                                       AND AGD.ACCOUNT_CATEGORY = 
                                           SAGD.ACCOUNT_CATEGORY 
                                       AND AGD.ACCOUNT_TYPE = SAGD.ACCOUNT_TYPE 
                                       AND AGD.ADJUSTMENT_TYPE = 
                                           SAGD.ADJUSTMENT_TYPE 
                                       AND AGD.ACCOUNT = SAGD.ACCOUNT 
                                       AND AGD.GL_COMPANY_ID = 
                                           SAGD.GL_COMPANY_ID 
                                       AND AGD.BUSINESS_UNIT_ID = 
                                           SAGD.BUSINESS_UNIT_ID 
                                       AND AGD.REDEMPTION_PERIOD = 
                                           SAGD.REDEMPTION_PERIOD) 

      SELECT @COUNT = @COUNT + @@ROWCOUNT 
  END 
ELSE 
  BEGIN 
      IF OBJECT_ID('TEMPDB..#TEMP_PROJ_RES') IS NOT NULL 
        DROP TABLE #TEMP_PROJ_RES 

      CREATE TABLE #TEMP_PROJ_RES 
        ( 
           BRAND_MASTER_SID                 INT, 
           BRAND_ID                         VARCHAR(50), 
           PERIOD_DATE                      DATETIME, 
           RS_CATEGORY                      INT, 
           RS_TYPE                          INT, 
           REBATE_PROGRAM_TYPE              INT, 
           AMOUNT                           NUMERIC(22, 6), 
           RATE                             NUMERIC(22, 6), 
           PROJECTION_MASTER_SID            INT, 
           WORKFLOW_ID                      VARCHAR(50), 
           WORKFLOW_STATUS                  INT, 
           WORKFLOW_NAME                    VARCHAR(100), 
           ADJUSTMENT_CREATED_DATE          DATETIME, 
           WORKFLOW_CREATED_DATE            DATETIME, 
           WORKFLOW_CREATED_BY              INT, 
           WORKFLOW_APPROVED_BY             INT, 
           WORKFLOW_APPROVED_DATE           DATETIME, 
           ADJUSTMENT_TYPE                  VARCHAR(50), 
           APPRVD_ARM_ADJUSTMENT_CONFIG_SID INT, 
           APPRVD_TRANSACTION_NAME          VARCHAR(50), 
           APPRVD_METHODOLOGY               VARCHAR(50) 
        ) 

      INSERT INTO #TEMP_PROJ_RES 
                  (BRAND_MASTER_SID, 
                   BRAND_ID, 
                   PERIOD_DATE, 
                   RS_CATEGORY, 
                   RS_TYPE, 
                   REBATE_PROGRAM_TYPE, 
                   AMOUNT, 
                   RATE, 
                   PROJECTION_MASTER_SID, 
                   WORKFLOW_ID, 
                   WORKFLOW_STATUS, 
                   WORKFLOW_NAME, 
                   ADJUSTMENT_CREATED_DATE, 
                   WORKFLOW_CREATED_BY, 
                   WORKFLOW_CREATED_DATE, 
                   WORKFLOW_APPROVED_BY, 
                   WORKFLOW_APPROVED_DATE, 
                   ADJUSTMENT_TYPE, 
                   APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
                   APPRVD_TRANSACTION_NAME, 
                   APPRVD_METHODOLOGY) 
      SELECT IM.BRAND_MASTER_SID, 
             BM.BRAND_ID, 
             P.PERIOD_DATE, 
             RS_CATEGORY, 
             RS_TYPE, 
             REBATE_PROGRAM_TYPE, 
             SUM(A.AMOUNT)   AS AMOUNT, 
             SUM(A.RATE)     AS RATE, 
             A.PROJECTION_MASTER_SID, 
             A.WORKFLOW_ID, 
             WORKFLOW_STATUS, 
             WORKFLOW_NAME, 
             PM.CREATED_DATE AS ADJUSTMENT_CREATED_DATE, 
             WM.CREATED_BY   AS WORKFLOW_CREATED_BY, 
             WM.CREATED_DATE AS WORKFLOW_CREATED_DATE, 
             APPROVED_BY     AS WORKFLOW_APPROVED_BY, 
             APPROVED_DATE   AS WORKFLOW_APPROVED_DATE, 
             A.ADJUSTMENT_TYPE, 
             APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
             APPRVD_TRANSACTION_NAME, 
             APPRVD_METHODOLOGY 
      FROM   #TEMP_STATUS_AMOUNT A 
             JOIN WORKFLOW_MASTER WM 
               ON WM.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID 
             JOIN PROJECTION_MASTER PM 
               ON PM.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID 
             JOIN CCP_DETAILS C 
               ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID 
             JOIN ITEM_MASTER IM 
               ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID 
             JOIN BRAND_MASTER BM 
               ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID 
             JOIN RS_CONTRACT R 
               ON R.RS_MODEL_SID = A.RS_MODEL_SID 
                  AND R.CONTRACT_MASTER_SID = C.CONTRACT_MASTER_SID 
             JOIN ARM_DEDUCTION_SELECTION ADS 
               ON ADS.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID 
                  AND ADS.RS_CONTRACT_SID = R.RS_CONTRACT_SID 
             JOIN PERIOD P 
               ON P.PERIOD_SID = A.PERIOD_SID 
      GROUP  BY IM.BRAND_MASTER_SID, 
                BM.BRAND_ID, 
                P.PERIOD_DATE, 
                RS_CATEGORY, 
                RS_TYPE, 
                REBATE_PROGRAM_TYPE, 
                A.PROJECTION_MASTER_SID, 
                A.WORKFLOW_ID, 
                WORKFLOW_STATUS, 
                WORKFLOW_NAME, 
                PM.CREATED_DATE, 
                WM.CREATED_BY, 
                WM.CREATED_DATE, 
                APPROVED_BY, 
                APPROVED_DATE, 
                A.ADJUSTMENT_TYPE, 
                APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
                APPRVD_TRANSACTION_NAME, 
                APPRVD_METHODOLOGY 

      IF OBJECT_ID('TEMPDB..#TEMP_RESERVE_RESULTS') IS NOT NULL 
        DROP TABLE #TEMP_RESERVE_RESULTS; 

      WITH CTE 
           AS (SELECT B.GL_IMPACT_DATE AS ACCOUNTING_DATE, 
                      BU.COMPANY_ID    AS BU_COMPANY_ID, 
                      BU.COMPANY_NO    AS BU_COMPANY_NO, 
                      B.BU_COMPANY_MASTER_SID, 
                      A.BRAND_ID, 
                      A.PERIOD_DATE    AS REDEMPTION_PERIOD, 
                      GL.COMPANY_ID    AS GL_COMPANY_ID, 
                      GL.COMPANY_NO    AS GL_COMPANY_NO, 
                      ARM_ADJ_RES_CONFIG_MASTER_SID, 
                      A.AMOUNT, 
                      A.RATE, 
                      PM.PROJECTION_DESCRIPTION, 
                      A.WORKFLOW_ID, 
                      A.WORKFLOW_STATUS, 
                      A.WORKFLOW_NAME, 
                      A.ADJUSTMENT_CREATED_DATE, 
                      A.WORKFLOW_CREATED_BY, 
                      A.WORKFLOW_CREATED_DATE, 
                      A.WORKFLOW_APPROVED_BY, 
                      A.WORKFLOW_APPROVED_DATE, 
                      A.APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
                      A.APPRVD_TRANSACTION_NAME, 
                      A.APPRVD_METHODOLOGY, 
                      PERIOD_DATE      AS REDEMPTION_DATE 
               FROM   #TEMP_PROJ_RES A 
                      JOIN ARM_ADJUSTMENT_MASTER B 
                        ON A.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID 
                      JOIN PROJECTION_MASTER PM 
                        ON PM.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID 
                      JOIN COMPANY_MASTER BU 
                        ON BU.COMPANY_MASTER_SID = B.BU_COMPANY_MASTER_SID 
                      JOIN COMPANY_MASTER GL 
                        ON GL.COMPANY_MASTER_SID = PM.COMPANY_MASTER_SID 
                      JOIN ARM_ADJ_RES_CONFIG_MASTER AAM 
                        ON AAM.RS_CATEGORY = A.RS_CATEGORY 
                           AND AAM.RS_TYPE = A.RS_TYPE 
                           AND AAM.REBATE_PROGRAM_TYPE = A.REBATE_PROGRAM_TYPE 
                           AND PM.COMPANY_MASTER_SID = AAM.GL_COMPANY_MASTER_SID 
                           AND B.BU_COMPANY_MASTER_SID = 
                               AAM.BU_COMPANY_MASTER_SID 
                           AND AAM.CONFIGURATION_TYPE = 0) SELECT WORKFLOW_ID, 
             WORKFLOW_NAME, 
             A.GL_COMPANY_ID          AS COMPANY, 
             A.BU_COMPANY_ID          AS DIVISION, 
             A.GL_COMPANY_ID          AS BUSINESS_UNIT, 
             AAD.JOURNAL_NAME         AS JOURNAL_NAME, 
             AAD.JOURNAL_DESCRIPTION  AS JOURNAL_DESCRIPTION, 
             A.BRAND_ID               AS BRAND, 
             DEBIT = CASE 
                       WHEN A.APPRVD_METHODOLOGY IN ( 
                            'TRANSACTION 1', 'TRANSACTION 3' 
                                                    ) THEN 
                         CASE 
                           WHEN A.AMOUNT < 0 
                                AND HT.DESCRIPTION = 'EXPENSE' THEN A.AMOUNT 
                           WHEN A.AMOUNT > 0 
                                AND HT.DESCRIPTION = 'LIABILITY' THEN A.AMOUNT 
                         END 
                       WHEN A.APPRVD_METHODOLOGY IN ( 
                            'TRANSACTION 2', 'TRANSACTION 4' 
                            , 
                            'TRANSACTION 5' ) THEN 
                         CASE 
                           WHEN A.AMOUNT < 0 
                                AND AAC.TRANSACTION_NAME LIKE '%PIPELINE%' 
                                AND HT.DESCRIPTION = 'LIABILITY' --LIA       
                         THEN A.AMOUNT 
                           WHEN A.AMOUNT > 0 
                                AND AAC.TRANSACTION_NAME LIKE '%DEMAND%' 
                                AND HT.DESCRIPTION = 'LIABILITY' --LIA       
                         THEN A.AMOUNT 
                         END 
                     END, 
             CREDIT = CASE 
                        WHEN A.APPRVD_METHODOLOGY IN ( 
                             'TRANSACTION 1', 'TRANSACTION 3' ) 
                      THEN 
                          CASE 
                            WHEN A.AMOUNT < 0 
                                 AND HT.DESCRIPTION = 'LIABILITY' THEN A.AMOUNT 
                            WHEN A.AMOUNT > 0 
                                 AND HT.DESCRIPTION = 'EXPENSE' THEN A.AMOUNT 
                          END 
                        WHEN A.APPRVD_METHODOLOGY IN ( 
                             'TRANSACTION 2', 'TRANSACTION 4', 
                             'TRANSACTION 5' ) THEN 
                          CASE 
                            WHEN A.AMOUNT < 0 
                                 AND AAC.TRANSACTION_NAME LIKE '%PIPELINE%' 
                                 AND HT.DESCRIPTION = 'LIABILITY' --LIA       
                          THEN A.AMOUNT 
                            WHEN A.AMOUNT > 0 
                                 AND AAC.TRANSACTION_NAME LIKE '%DEMAND%' 
                                 AND HT.DESCRIPTION = 'LIABILITY' --LIA       
                          THEN A.AMOUNT 
                          END 
                      END, 
             AAD.CURRENCY             AS CURRENCY, 
             A.ACCOUNTING_DATE        AS ACCOUNTING_DATE, 
             REDEMPTION_DATE          AS REDEMPTION_PERIOD, 
             AAD.CATEGORY             AS CATEGORY, 
             AAD.BALANCE_TYPE         AS BALANCE_TYPE, 
             AAD."DATABASE"           AS ARD_DB, 
             AAD.DATA_ACCESS_SET      AS DATA_ACCESS_SET, 
             AAD.CHART_OF_ACCOUNTS    AS CHART_OF_ACCOUNTS, 
             AAD.LEDGER               AS LEDGER, 
             AAD.REVERSE_JOURNAL      AS REVERSE_JOURNAL, 
             AAD.REVERSAL_PERIOD_DATE AS REVERSAL_PERIOD_DATE, 
             AAD.LINE_DESCRIPTION     AS LINE_DESCRIPTION, 
             AAD.ACCOUNT_CATEGORY     AS ACCOUNT_CATEGORY, 
             AAD.ACCOUNT_TYPE         AS ACCOUNT_TYPE, 
             AAD.ADJUSTMENT_TYPE      AS ADJUSTMENT_TYPE, 
             ADJUSTMENT_LEVEL, 
             AAD.ACCOUNT              AS ACCOUNT, 
             AAD.COST_CENTER          AS COST_CENTER, 
             AAD.PROJECT              AS PROJECT, 
             AAD.FUTURE_1             AS FUTURE_1, 
             AAD.FUTURE_2             AS FUTURE_2, 
             AAD.UDC_1                AS UDC_1, 
             AAD.UDC_2                AS UDC_2, 
             AAD.UDC_3                AS UDC_3, 
             AAD.UDC_4                AS UDC_4, 
             AAD.UDC_5                AS UDC_5, 
             AAD.UDC_6                AS UDC_6, 
             A.WORKFLOW_CREATED_BY, 
             A.WORKFLOW_CREATED_DATE, 
             A.WORKFLOW_APPROVED_BY, 
             A.WORKFLOW_APPROVED_DATE, 
             A.ADJUSTMENT_CREATED_DATE, 
             NULL                     AS GL_POSTING_STATUS, 
             NULL                     AS OUTBOUND_STATUS, 
             AAD.SOURCE               AS SOURCE, 
             BATCH_NAME = CASE 
                            WHEN A.WORKFLOW_ID IS NULL THEN '' 
                            ELSE CONVERT(VARCHAR(100), AAC.TRANSACTION_NAME) 
                                 + ' - ' + ISNULL(A.WORKFLOW_ID, ' ') + ' - ' 
                                 + A.PROJECTION_DESCRIPTION 
                          END, 
             NULL                     AS BATCH_ID, 
             NULL                     AS ORIGINAL_BATCH_ID, 
             @USER_ID                 USER_ID, 
             @SESSION_ID              SESSION_ID, 
             0                        AS CHECK_RECORD, 
             0                        AS ETL_CHECK_RECORD 
      INTO   #TEMP_RESERVE_RESULTS 
      FROM   CTE A 
             JOIN ARM_ADJ_RES_CONFIG_DETAIL AAD 
               ON AAD.ARM_ADJ_RES_CONFIG_MASTER_SID = 
                  A.ARM_ADJ_RES_CONFIG_MASTER_SID 
             JOIN HELPER_TABLE HT 
               ON HT.HELPER_TABLE_SID = AAD.ACCOUNT_TYPE 
             LEFT JOIN ARM_ADJUSTMENT_CONFIG AAC 
                    ON AAC.ARM_ADJUSTMENT_CONFIG_SID = AAD.ADJUSTMENT_TYPE 
      WHERE  ( AAD.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE 
                OR @ADJUSTMENT_TYPE IS NULL ) 
             AND ( AAD.ACCOUNT_CATEGORY = @ACCOUNT_CATEGORY 
                    OR @ACCOUNT_CATEGORY IS NULL ) 
             AND ( AAD.ACCOUNT_TYPE = @ACCOUNT_TYPE 
                    OR @ACCOUNT_TYPE IS NULL ) 
             AND ( AAD.ACCOUNT = @ACCOUNT 
                    OR @ACCOUNT IS NULL ) 
             AND ( AAD.ADJUSTMENT_LEVEL = @ADJUSTMENT_LEVEL 
                    OR @ADJUSTMENT_LEVEL IS NULL ) 
             AND ( AAD.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE 
                    OR @ADJUSTMENT_TYPE IS NULL ) 
      UNION ALL 
      SELECT WORKFLOW_ID, 
             WORKFLOW_NAME, 
             A.GL_COMPANY_ID          AS COMPANY, 
             A.BU_COMPANY_ID          AS DIVISION, 
             A.GL_COMPANY_ID          AS BUSINESS_UNIT, 
             AAD.JOURNAL_NAME         AS JOURNAL_NAME, 
             AAD.JOURNAL_DESCRIPTION  AS JOURNAL_DESCRIPTION, 
             '0000'                   AS BRAND, 
             DEBIT = CASE 
                       WHEN A.APPRVD_METHODOLOGY IN ( 
                            'TRANSACTION 1', 'TRANSACTION 3' 
                                                    ) THEN 
                         CASE 
                           WHEN A.AMOUNT < 0 
                                AND HT.DESCRIPTION = 'EXPENSE' THEN A.AMOUNT 
                           WHEN A.AMOUNT > 0 
                                AND HT.DESCRIPTION = 'LIABILITY' THEN A.AMOUNT 
                         END 
                       WHEN A.APPRVD_METHODOLOGY IN ( 
                            'TRANSACTION 2', 'TRANSACTION 4' 
                            , 
                            'TRANSACTION 5' ) THEN 
                         CASE 
                           WHEN A.AMOUNT < 0 
                                AND AAC.TRANSACTION_NAME LIKE '%PIPELINE%' 
                                AND HT.DESCRIPTION = 'LIABILITY' --LIA       
                         THEN A.AMOUNT 
                           WHEN A.AMOUNT > 0 
                                AND AAC.TRANSACTION_NAME LIKE '%DEMAND%' 
                                AND HT.DESCRIPTION = 'LIABILITY' --LIA       
                         THEN A.AMOUNT 
                         END 
                     END, 
             CREDIT = CASE 
                        WHEN A.APPRVD_METHODOLOGY IN ( 
                             'TRANSACTION 1', 'TRANSACTION 3' ) 
                      THEN 
                          CASE 
                            WHEN A.AMOUNT < 0 
                                 AND HT.DESCRIPTION = 'LIABILITY' THEN A.AMOUNT 
                            WHEN A.AMOUNT > 0 
                                 AND HT.DESCRIPTION = 'EXPENSE' THEN A.AMOUNT 
                          END 
                        WHEN A.APPRVD_METHODOLOGY IN ( 
                             'TRANSACTION 2', 'TRANSACTION 4', 
                             'TRANSACTION 5' ) THEN 
                          CASE 
                            WHEN A.AMOUNT < 0 
                                 AND AAC.TRANSACTION_NAME LIKE '%PIPELINE%' 
                                 AND HT.DESCRIPTION = 'LIABILITY' --LIA       
                          THEN A.AMOUNT 
                            WHEN A.AMOUNT > 0 
                                 AND AAC.TRANSACTION_NAME LIKE '%DEMAND%' 
                                 AND HT.DESCRIPTION = 'LIABILITY' --LIA       
                          THEN A.AMOUNT 
                          END 
                      END, 
             AAD.CURRENCY             AS CURRENCY, 
             A.ACCOUNTING_DATE        AS ACCOUNTING_DATE, 
             REDEMPTION_DATE          AS REDEMPTION_PERIOD, 
             AAD.CATEGORY             AS CATEGORY, 
             AAD.BALANCE_TYPE         AS BALANCE_TYPE, 
             AAD."DATABASE"           AS ARD_DB, 
             AAD.DATA_ACCESS_SET      AS DATA_ACCESS_SET, 
             AAD.CHART_OF_ACCOUNTS    AS CHART_OF_ACCOUNTS, 
             AAD.LEDGER               AS LEDGER, 
             AAD.REVERSE_JOURNAL      AS REVERSE_JOURNAL, 
             AAD.REVERSAL_PERIOD_DATE AS REVERSAL_PERIOD_DATE, 
             AAD.LINE_DESCRIPTION     AS LINE_DESCRIPTION, 
             AAD.ACCOUNT_CATEGORY     AS ACCOUNT_CATEGORY, 
             AAD.ACCOUNT_TYPE         AS ACCOUNT_TYPE, 
             AAD.ADJUSTMENT_TYPE      AS ADJUSTMENT_TYPE, 
             ADJUSTMENT_LEVEL, 
             AAD.ACCOUNT              AS ACCOUNT, 
             AAD.COST_CENTER          AS COST_CENTER, 
             AAD.PROJECT              AS PROJECT, 
             AAD.FUTURE_1             AS FUTURE_1, 
             AAD.FUTURE_2             AS FUTURE_2, 
             AAD.UDC_1                AS UDC_1, 
             AAD.UDC_2                AS UDC_2, 
             AAD.UDC_3                AS UDC_3, 
             AAD.UDC_4                AS UDC_4, 
             AAD.UDC_5                AS UDC_5, 
             AAD.UDC_6                AS UDC_6, 
             A.WORKFLOW_CREATED_BY, 
             A.WORKFLOW_CREATED_DATE, 
             A.WORKFLOW_APPROVED_BY, 
             A.WORKFLOW_APPROVED_DATE, 
             A.ADJUSTMENT_CREATED_DATE, 
             NULL                     AS GL_POSTING_STATUS, 
             NULL                     AS OUTBOUND_STATUS, 
             NULL                     AS SOURCE, 
             BATCH_NAME = CASE 
                            WHEN A.WORKFLOW_ID IS NULL THEN '' 
                            ELSE CONVERT(VARCHAR(100), AAC.TRANSACTION_NAME) 
                                 + ' - ' + ISNULL(A.WORKFLOW_ID, ' ') + ' - ' 
                                 + A.PROJECTION_DESCRIPTION 
                          END, 
             NULL                     AS BATCH_ID, 
             NULL                     AS ORIGINAL_BATCH_ID, 
             @USER_ID                 USER_ID, 
             @SESSION_ID              SESSION_ID, 
             0                        AS CHECK_RECORD, 
             0                        AS ETL_CHECK_RECORD 
      FROM   (SELECT ARM_ADJ_RES_CONFIG_MASTER_SID, 
                     GL_COMPANY_ID, 
                     GL_COMPANY_NO, 
                     BU_COMPANY_ID, 
                     BU_COMPANY_NO, 
                     BU_COMPANY_MASTER_SID, 
                     ACCOUNTING_DATE, 
                     PROJECTION_DESCRIPTION, 
                     SUM(A.AMOUNT) AMOUNT, 
                     SUM(A.RATE)   RATE, 
                     WORKFLOW_ID, 
                     WORKFLOW_STATUS, 
                     WORKFLOW_NAME, 
                     A.ADJUSTMENT_CREATED_DATE, 
                     A.WORKFLOW_CREATED_BY, 
                     A.WORKFLOW_CREATED_DATE, 
                     A.WORKFLOW_APPROVED_BY, 
                     A.WORKFLOW_APPROVED_DATE, 
                     A.APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
                     A.APPRVD_TRANSACTION_NAME, 
                     A.APPRVD_METHODOLOGY, 
                     REDEMPTION_DATE 
              FROM   CTE A 
              GROUP  BY ARM_ADJ_RES_CONFIG_MASTER_SID, 
                        GL_COMPANY_ID, 
                        GL_COMPANY_NO, 
                        BU_COMPANY_ID, 
                        BU_COMPANY_NO, 
                        BU_COMPANY_MASTER_SID, 
                        ACCOUNTING_DATE, 
                        PROJECTION_DESCRIPTION, 
                        WORKFLOW_ID, 
                        WORKFLOW_STATUS, 
                        WORKFLOW_NAME, 
                        A.ADJUSTMENT_CREATED_DATE, 
                        A.WORKFLOW_CREATED_BY, 
                        A.WORKFLOW_CREATED_DATE, 
                        A.WORKFLOW_APPROVED_BY, 
                        A.WORKFLOW_APPROVED_DATE, 
                        A.APPRVD_ARM_ADJUSTMENT_CONFIG_SID, 
                        A.APPRVD_TRANSACTION_NAME, 
                        A.APPRVD_METHODOLOGY, 
                        REDEMPTION_DATE) A 
             JOIN ARM_ADJ_RES_CONFIG_DETAIL AAD 
               ON AAD.ARM_ADJ_RES_CONFIG_MASTER_SID = 
                  A.ARM_ADJ_RES_CONFIG_MASTER_SID 
             JOIN HELPER_TABLE HT 
               ON HT.HELPER_TABLE_SID = AAD.ACCOUNT_TYPE 
             LEFT JOIN ARM_ADJUSTMENT_CONFIG AAC 
                    ON AAC.ARM_ADJUSTMENT_CONFIG_SID = AAD.ADJUSTMENT_TYPE 
      WHERE  ( AAD.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE 
                OR @ADJUSTMENT_TYPE IS NULL ) 
             AND ( AAD.ACCOUNT_CATEGORY = @ACCOUNT_CATEGORY 
                    OR @ACCOUNT_CATEGORY IS NULL ) 
             AND ( AAD.ACCOUNT_TYPE = @ACCOUNT_TYPE 
                    OR @ACCOUNT_TYPE IS NULL ) 
             AND ( AAD.ACCOUNT = @ACCOUNT 
                    OR @ACCOUNT IS NULL ) 
             AND ( AAD.ADJUSTMENT_LEVEL = @ADJUSTMENT_LEVEL 
                    OR @ADJUSTMENT_LEVEL IS NULL ) 

      INSERT INTO ST_ADJUSTMENT_RESERVE_DETAIL 
                  (WORKFLOW_ID, 
                   WORKFLOW_NAME, 
                   COMPANY, 
                   DIVISION, 
                   BUSINESS_UNIT, 
                   JOURNAL_NAME, 
                   JOURNAL_DESCRIPTION, 
                   BRAND, 
                   DEBIT, 
                   CREDIT, 
                   CURRENCY, 
                   ACCOUNTING_DATE, 
                   REDEMPTION_PERIOD, 
                   CATEGORY, 
                   BALANCE_TYPE, 
                   ARD_DB, 
                   DATA_ACCESS_SET, 
                   CHART_OF_ACCOUNTS, 
                   LEDGER, 
                   REVERSE_JOURNAL, 
                   REVERSAL_PERIOD_DATE, 
                   LINE_DESCRIPTION, 
                   ACCOUNT_CATEGORY, 
                   ACCOUNT_TYPE, 
                   ADJUSTMENT_TYPE, 
                   ADJUSTMENT_LEVEL, 
                   ACCOUNT, 
                   COST_CENTER, 
                   PROJECT, 
                   FUTURE_1, 
                   FUTURE_2, 
                   UDC_1, 
                   UDC_2, 
                   UDC_3, 
                   UDC_4, 
                   UDC_5, 
                   UDC_6, 
                   WORKFLOW_CREATED_BY, 
                   WORKFLOW_CREATED_DATE, 
                   WORKFLOW_APPROVED_BY, 
                   WORKFLOW_APPROVED_DATE, 
                   ADJUSTMENT_CREATED_DATE, 
                   GL_POSTING_STATUS, 
                   OUTBOUND_STATUS, 
                   SOURCE, 
                   BATCH_NAME, 
                   BATCH_ID, 
                   ORIGINAL_BATCH_ID, 
                   USER_ID, 
                   SESSION_ID, 
                   CHECK_RECORD, 
                   ETL_CHECK_RECORD) 
      SELECT TRS.WORKFLOW_ID, 
             TRS.WORKFLOW_NAME, 
             TRS.COMPANY, 
             TRS.DIVISION, 
             TRS.BUSINESS_UNIT, 
             TRS.JOURNAL_NAME, 
             TRS.JOURNAL_DESCRIPTION, 
             TRS.BRAND, 
             TRS.DEBIT, 
             TRS.CREDIT, 
             TRS.CURRENCY, 
             TRS.ACCOUNTING_DATE, 
             TRS.REDEMPTION_PERIOD, 
             TRS.CATEGORY, 
             TRS.BALANCE_TYPE, 
             TRS.ARD_DB, 
             TRS.DATA_ACCESS_SET, 
             TRS.CHART_OF_ACCOUNTS, 
             TRS.LEDGER, 
             TRS.REVERSE_JOURNAL, 
             TRS.REVERSAL_PERIOD_DATE, 
             TRS.LINE_DESCRIPTION, 
             TRS.ACCOUNT_CATEGORY, 
             TRS.ACCOUNT_TYPE, 
             TRS.ADJUSTMENT_TYPE, 
             TRS.ADJUSTMENT_LEVEL, 
             TRS.ACCOUNT, 
             TRS.COST_CENTER, 
             TRS.PROJECT, 
             TRS.FUTURE_1, 
             TRS.FUTURE_2, 
             TRS.UDC_1, 
             TRS.UDC_2, 
             TRS.UDC_3, 
             TRS.UDC_4, 
             TRS.UDC_5, 
             TRS.UDC_6, 
             TRS.WORKFLOW_CREATED_BY, 
             TRS.WORKFLOW_CREATED_DATE, 
             TRS.WORKFLOW_APPROVED_BY, 
             TRS.WORKFLOW_APPROVED_DATE, 
             TRS.ADJUSTMENT_CREATED_DATE, 
             TRS.GL_POSTING_STATUS, 
             TRS.OUTBOUND_STATUS, 
             TRS.SOURCE, 
             TRS.BATCH_NAME, 
             TRS.BATCH_ID, 
             TRS.ORIGINAL_BATCH_ID, 
             TRS.USER_ID, 
             TRS.SESSION_ID, 
             TRS.CHECK_RECORD, 
             TRS.ETL_CHECK_RECORD 
      FROM   #TEMP_RESERVE_RESULTS TRS 
             LEFT JOIN ADJUSTMENT_RESERVE_DETAIL ARD 
                    ON TRS.BRAND = ARD.BRAND 
                       AND TRS.WORKFLOW_ID = ARD.WORKFLOW_ID 
                       AND TRS.ACCOUNT_CATEGORY = ARD.ACCOUNT_CATEGORY 
                       AND TRS.ACCOUNT_TYPE = ARD.ACCOUNT_TYPE 
                       AND TRS.ADJUSTMENT_TYPE = ARD.ADJUSTMENT_TYPE 
                       AND ARD.ACCOUNT = TRS.ACCOUNT 
                       AND ARD.COMPANY = TRS.COMPANY 
                       AND ARD.BUSINESS_UNIT = TRS.BUSINESS_UNIT 
                       AND ARD.REDEMPTION_PERIOD = TRS.REDEMPTION_PERIOD 
      WHERE  ARD.BRAND IS NULL 

      SET @COUNT = @@ROWCOUNT 

      INSERT INTO ST_ADJUSTMENT_RESERVE_DETAIL 
                  (WORKFLOW_ID, 
                   WORKFLOW_NAME, 
                   COMPANY, 
                   DIVISION, 
                   BUSINESS_UNIT, 
                   JOURNAL_NAME, 
                   JOURNAL_DESCRIPTION, 
                   BRAND, 
                   DEBIT, 
                   CREDIT, 
                   CURRENCY, 
                   ACCOUNTING_DATE, 
                   REDEMPTION_PERIOD, 
                   CATEGORY, 
                   BALANCE_TYPE, 
                   ARD_DB, 
                   DATA_ACCESS_SET, 
                   CHART_OF_ACCOUNTS, 
                   LEDGER, 
                   REVERSE_JOURNAL, 
                   REVERSAL_PERIOD_DATE, 
                   LINE_DESCRIPTION, 
                   ACCOUNT_CATEGORY, 
                   ACCOUNT_TYPE, 
                   ADJUSTMENT_TYPE, 
                   ADJUSTMENT_LEVEL, 
                   ACCOUNT, 
                   COST_CENTER, 
                   PROJECT, 
                   FUTURE_1, 
                   FUTURE_2, 
                   UDC_1, 
                   UDC_2, 
                   UDC_3, 
                   UDC_4, 
                   UDC_5, 
                   UDC_6, 
                   WORKFLOW_CREATED_BY, 
                   WORKFLOW_CREATED_DATE, 
                   WORKFLOW_APPROVED_BY, 
                   WORKFLOW_APPROVED_DATE, 
                   ADJUSTMENT_CREATED_DATE, 
                   GL_POSTING_STATUS, 
                   OUTBOUND_STATUS, 
                   SOURCE, 
                   BATCH_NAME, 
                   BATCH_ID, 
                   ORIGINAL_BATCH_ID, 
                   USER_ID, 
                   SESSION_ID, 
                   CHECK_RECORD, 
                   ETL_CHECK_RECORD) 
      SELECT ARD.WORKFLOW_ID, 
             ARD.WORKFLOW_NAME, 
             ARD.COMPANY, 
             ARD.DIVISION, 
             ARD.BUSINESS_UNIT, 
             ARD.JOURNAL_NAME, 
             ARD.JOURNAL_DESCRIPTION, 
             ARD.BRAND, 
             ARD.DEBIT, 
             ARD.CREDIT, 
             ARD.CURRENCY, 
             ARD.ACCOUNTING_DATE, 
             ARD.REDEMPTION_PERIOD, 
             ARD.CATEGORY, 
             ARD.BALANCE_TYPE, 
             ARD.ARD_DB, 
             ARD.DATA_ACCESS_SET, 
             ARD.CHART_OF_ACCOUNTS, 
             ARD.LEDGER, 
             ARD.REVERSE_JOURNAL, 
             ARD.REVERSAL_PERIOD_DATE, 
             ARD.LINE_DESCRIPTION, 
             ARD.ACCOUNT_CATEGORY, 
             ARD.ACCOUNT_TYPE, 
             ARD.ADJUSTMENT_TYPE, 
             ARD.ADJUSTMENT_LEVEL, 
             ARD.ACCOUNT, 
             ARD.COST_CENTER, 
             ARD.PROJECT, 
             ARD.FUTURE_1, 
             ARD.FUTURE_2, 
             ARD.UDC_1, 
             ARD.UDC_2, 
             ARD.UDC_3, 
             ARD.UDC_4, 
             ARD.UDC_5, 
             ARD.UDC_6, 
             ARD.WORKFLOW_CREATED_BY, 
             ARD.WORKFLOW_CREATED_DATE, 
             ARD.WORKFLOW_APPROVED_BY, 
             ARD.WORKFLOW_APPROVED_DATE, 
             ARD.ADJUSTMENT_CREATED_DATE, 
             ARD.GL_POSTING_STATUS, 
             ARD.OUTBOUND_STATUS, 
             ARD.SOURCE, 
             ARD.BATCH_NAME, 
             ARD.BATCH_ID, 
             ARD.ORIGINAL_BATCH_ID, 
             @USER_ID    USER_ID, 
             @SESSION_ID SESSION_ID, 
             0           AS CHECK_RECORD, 
             0           AS ETL_CHECK_RECORD 
      FROM   ADJUSTMENT_RESERVE_DETAIL ARD 
             INNER JOIN #TEMP_RESERVE_RESULTS TRS 
                     ON TRS.BRAND = ARD.BRAND 
                        AND TRS.WORKFLOW_ID = ARD.WORKFLOW_ID 
                        AND TRS.ACCOUNT_CATEGORY = ARD.ACCOUNT_CATEGORY 
                        AND TRS.ACCOUNT_TYPE = ARD.ACCOUNT_TYPE 
                        AND TRS.ADJUSTMENT_TYPE = ARD.ADJUSTMENT_TYPE 
                        AND ARD.ACCOUNT = TRS.ACCOUNT 
                        AND ARD.COMPANY = TRS.COMPANY 
                        AND ARD.BUSINESS_UNIT = TRS.BUSINESS_UNIT 
                        AND ARD.REDEMPTION_PERIOD = TRS.REDEMPTION_PERIOD 

      SELECT @COUNT = @COUNT + @@ROWCOUNT 
  END        
      SELECT @COUNT 
  END 

*/