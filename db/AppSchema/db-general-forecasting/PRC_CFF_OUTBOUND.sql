IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_CFF_OUTBOUND'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_CFF_OUTBOUND]
  END

GO

CREATE PROCEDURE [dbo].[PRC_CFF_OUTBOUND] (@CFF_MASTER_SID INT)
AS
/**********************************************************************************************************
** FILE NAME		:	PRC_CFF_OUTBOUND.SQL
** PROCEDURE NAME	:	PRC_CFF_OUTBOUND
** DESCRIPTION		:   for cff  forecasting information will stored in master table 
** INPUT PARAMETERS	:	@CFF_MASTER_SID	- passing input as cff_master_sid
** OUTPUT PARAMETERS:   na
** AUTHOR NAME		:   @leela venkatesh
** CREATION DATE	:   28/03/2016 - 
** CALLED BY		:   inserting total forecasting information attached to the patricular cff 

*********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No                 Author          Description 
** ---   --------  ---------                -------------    -----------------------------
** 1    04/28/2016 GAL-3451/GAL_UAT-290   Leela venkatesh    while inserting information into master table   procedure will call
*********************************************************************************************************/



------------------------taking projections attached to the cfff--------------------------------
	  IF Object_id('TEMPDB..#TEMP_CFF') IS NOT NULL
        DROP TABLE #TEMP_CFF

	 SELECT DISTINCT CD.CFF_MASTER_SID,
                      CFF_DETAILS_SID,
                      PD.PROJECTION_MASTER_SID,
                      PD.PROJECTION_DETAILS_SID,
                      CCP.ITEM_MASTER_SID
      INTO   #TEMP_CFF
      FROM   CFF_DETAILS CD
             JOIN CFF_MASTER C
               ON CD.CFF_MASTER_SID = C.CFF_MASTER_SID
             JOIN PROJECTION_DETAILS PD
               ON PD.PROJECTION_MASTER_SID = CD.PROJECTION_MASTER_SID
                  AND PD.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
             JOIN CCP_DETAILS CCP
               ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
      WHERE  CD.CFF_MASTER_SID = @CFF_MASTER_SID
----------------------PULLING CONTRACT LEVEL INFORMATION FOR THE CFF---------------------------------------
      IF Object_id('TEMPDB..#INPUT_INFO2') IS NOT NULL
        DROP TABLE #INPUT_INFO2

      CREATE TABLE #INPUT_INFO2
        (
           [CFF_MASTER_SID]         [INT] NOT NULL,
           [CFF_DETAILS_SID]        [INT] NOT NULL,
           [CFF_NAME]               [VARCHAR](100) NOT NULL,
           [APPROVED_DATE]          [DATETIME] NULL,
           [PROJECTION_MASTER_SID]  [INT] NOT NULL,
           [PROJECTION_DETAILS_SID] [INT] NOT NULL,
           [PROJECTION_NAME]        [VARCHAR](100) NULL,
           [CONTRACT_ID]            [VARCHAR](50) NOT NULL,
           [CONTRACT_NO]            [VARCHAR](50) NOT NULL,
           [CONTRACT_NAME]          [VARCHAR](100) NULL,
           [CONTRACT_TYPE]          [INT] NULL,
           [COMPANY_MASTER_SID]     [INT] NULL,
           [CONTRACT_MASTER_SID]    [INT] NOT NULL,
           [CUSTOMER_ID]            [VARCHAR](50) NOT NULL,
           [CUSTOMER_NO]            [VARCHAR](50) NOT NULL,
           [CUSTOMER_NAME]          [VARCHAR](50) NOT NULL,
           [ITEM_ID]                [VARCHAR](38) NOT NULL,
           [ITEM_NO]                [VARCHAR](50) NOT NULL,
           [ITEM_NAME]              [VARCHAR](100) NOT NULL,
           [ITEM_MASTER_SID]        [INT] NOT NULL,
           [GL_COMPANY]             [INT] NULL,
           [FORECASTING_TYPE]       [VARCHAR](50) NULL,
           [CFF_TYPE]               [INT] NULL,
           [SALES_INCLUSION]        [CHAR](1) NULL,
           [DEDUCTION_CATEGORY1]    [INT] NULL,
           [DEDUCTION_CATEGORY2]    [INT] NULL,
           [DEDUCTION_CATEGORY3]    [INT] NULL,
           [DEDUCTION_CATEGORY4]    [INT] NULL,
           [DEDUCTION_CATEGORY5]    [INT] NULL,
           [DEDUCTION_CATEGORY6]    [INT] NULL,
           [CREATED_BY]             [INT] NOT NULL,
           [CREATED_DATE]           [DATETIME] NOT NULL,
           [MODIFIED_BY]            [INT] NOT NULL,
           [MODIFIED_DATE]          [DATETIME] NOT NULL,
           [RS_MODEL_SID]           [INT] NOT NULL,
           [RS_ID]                  [VARCHAR](50) NULL,
           [RS_NO]                  [VARCHAR](50) NULL,
           [RS_NAME]                [VARCHAR](100) NULL,
           [RS_CATEGORY]            [INT] NULL,
           [RS_TYPE]                [INT] NULL,
           [REBATE_PROGRAM_TYPE]    [INT] NULL,
           [DEDUCTION_INCLUSION]    [INT] NULL,
           [CONTRACT_HOLDER_ID]     [INT] NOT NULL,
           [CONTRACT_HOLDER_NO]     [VARCHAR](50) NOT NULL,
           [CONTRACT_HOLDER_NAME]   [VARCHAR](50) NOT NULL,
           [COMPANY_ID]             [INT] NOT NULL,
           [COMPANY_NO]             [VARCHAR](50) NOT NULL,
           [COMPANY_NAME]           [VARCHAR](50) NOT NULL,
           PRIMARY KEY (CFF_DETAILS_SID, PROJECTION_DETAILS_SID, RS_MODEL_SID)
        );

      WITH APRV_DATE
           AS (SELECT TOP 1 CFF_MASTER_SID,
                            APPROVED_DATE
               FROM   CFF_APPROVAL_DETAILS D
               WHERE  D.CFF_MASTER_SID = @CFF_MASTER_SID
               ORDER  BY APPROVED_DATE DESC),
           MAIN_INFO
           AS (SELECT DISTINCT C.CFF_MASTER_SID,
                               D.CFF_DETAILS_SID,
                               C.CFF_NAME,
                               APPROVED_DATE,
                               P.PROJECTION_MASTER_SID,
                               PD.PROJECTION_DETAILS_SID,
                               P.PROJECTION_NAME,
                               CO.CONTRACT_ID,
                               CO.CONTRACT_NO,
                               CO.CONTRACT_NAME,
                               CO.CONTRACT_TYPE,
                               CO.CONT_HOLD_COMPANY_MASTER_SID AS COMPANY_MASTER_SID,
                               CO.CONTRACT_MASTER_SID,
                               CM.COMPANY_ID                   AS CUSTOMER_ID,
                               CM.COMPANY_NO                   AS CUSTOMER_NO,
                               CM.COMPANY_NAME                 AS CUSTOMER_NAME,
                               IM.ITEM_ID,
                               IM.ITEM_NO,
                               IM.ITEM_NAME,
                               IM.ITEM_MASTER_SID,
                               C.COMPANY_MASTER_SID            AS GL_COMPANY,
                               P.FORECASTING_TYPE,
                               H.HELPER_TABLE_SID              AS CFF_TYPE,
                               D.SALES_INCLUSION,
                               UD.UDC1                         AS DEDUCTION_CATEGORY1,
                               UD.UDC2                         AS DEDUCTION_CATEGORY2,
                               UD.UDC3                         AS DEDUCTION_CATEGORY3,
                               UD.UDC4                         AS DEDUCTION_CATEGORY4,
                               UD.UDC5                         AS DEDUCTION_CATEGORY5,
                               UD.UDC6                         AS DEDUCTION_CATEGORY6,
                               C.CREATED_BY,
                               C.CREATED_DATE,
                               C.MODIFIED_BY,
                               C.MODIFIED_DATE,
                               Isnull(RS_M.RS_MODEL_SID, 0)    RS_MODEL_SID,
                               RS_M.RS_ID,
                               RS_M.RS_NO,
                               RS_M.RS_NAME,
                               RS_M.RS_CATEGORY,
                               RS_M.RS_TYPE,
                               RS_M.REBATE_PROGRAM_TYPE,
                               RS_M.DEDUCTION_INCLUSION
               FROM   CFF_MASTER C
                      INNER JOIN CFF_DETAILS D
                              ON C.CFF_MASTER_SID = D.CFF_MASTER_SID
                      INNER JOIN APRV_DATE CAD
                              ON CAD.CFF_MASTER_SID = C.CFF_MASTER_SID
                      INNER JOIN PROJECTION_MASTER P
                              ON P.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID
                      INNER JOIN PROJECTION_DETAILS PD
                              ON D.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                 AND PD.CCP_DETAILS_SID = D.CCP_DETAILS_SID
                      INNER JOIN CCP_DETAILS CD
                              ON D.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                      INNER JOIN CONTRACT_MASTER CO
                              ON CO.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
                                 AND CO.INBOUND_STATUS <> 'D'
                      INNER JOIN COMPANY_MASTER CM
                              ON CM.COMPANY_MASTER_SID = CD.COMPANY_MASTER_SID
                                 AND CM.INBOUND_STATUS <> 'D'
                      INNER JOIN ITEM_MASTER IM
                              ON IM.ITEM_MASTER_SID = CD.ITEM_MASTER_SID
                                 AND IM.INBOUND_STATUS <> 'D'
                      INNER JOIN CFP_CONTRACT CFP_M
                              ON CFP_M.CONTRACT_MASTER_SID = CO.CONTRACT_MASTER_SID
                                 AND CFP_M.INBOUND_STATUS <> 'D'
                      INNER JOIN CFP_CONTRACT_DETAILS CFP_D
                              ON CFP_M.CFP_CONTRACT_SID = CFP_D.CFP_CONTRACT_SID
                                 AND CFP_D.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID
                                 AND CFP_D.INBOUND_STATUS <> 'D'
                      INNER JOIN IFP_CONTRACT IFP_M
                              ON IFP_M.CONTRACT_MASTER_SID = CO.CONTRACT_MASTER_SID
                                 AND IFP_M.CFP_CONTRACT_SID = CFP_M.CFP_CONTRACT_SID
                                 AND IFP_M.INBOUND_STATUS <> 'D'
                      INNER JOIN IFP_CONTRACT_DETAILS IFP_D
                              ON IFP_D.IFP_CONTRACT_SID = IFP_M.IFP_CONTRACT_SID
                                 AND IFP_D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                 AND IFP_D.INBOUND_STATUS <> 'D'
                      LEFT JOIN PS_CONTRACT PS_M
                             ON PS_M.CONTRACT_MASTER_SID = CO.CONTRACT_MASTER_SID
                                AND PS_M.CFP_CONTRACT_SID = CFP_M.CFP_CONTRACT_SID
                                AND PS_M.IFP_CONTRACT_SID = IFP_M.IFP_CONTRACT_SID
                                AND PS_M.INBOUND_STATUS <> 'D'
                      LEFT JOIN PS_CONTRACT_DETAILS PS_D
                             ON PS_D.PS_CONTRACT_SID = PS_M.PS_CONTRACT_SID
                                AND PS_D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                AND PS_D.INBOUND_STATUS <> 'D'
                      LEFT JOIN RS_CONTRACT RS_M
                             ON RS_M.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
                                AND RS_M.CFP_CONTRACT_SID = CFP_M.CFP_CONTRACT_SID
                                AND RS_M.IFP_CONTRACT_SID = IFP_M.IFP_CONTRACT_SID
                                AND RS_M.PS_CONTRACT_SID = PS_M.PS_CONTRACT_SID
                                AND RS_M.INBOUND_STATUS <> 'D'
                      LEFT JOIN RS_CONTRACT_DETAILS RS_D
                             ON RS_D.RS_CONTRACT_SID = RS_M.RS_CONTRACT_SID
                                AND RS_D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                AND RS_D.INBOUND_STATUS <> 'D'
                      LEFT JOIN HELPER_TABLE H
                             ON H.HELPER_TABLE_SID = C.CFF_TYPE
                                AND H.LIST_NAME = 'CFF_TYPE'
                      LEFT JOIN UDCS UD
                             ON RS_M.RS_CONTRACT_SID = UD.MASTER_SID
                                AND UD.MASTER_TYPE = 'RS_CONTRACT'
                      LEFT JOIN HELPER_TABLE HT1
                             ON HT1.HELPER_TABLE_SID = UD.UDC1
                                AND HT1.LIST_NAME = 'RS_UDC1'
                      LEFT JOIN HELPER_TABLE HT2
                             ON HT2.HELPER_TABLE_SID = UD.UDC2
                                AND HT2.LIST_NAME = 'RS_UDC2'
                      LEFT JOIN HELPER_TABLE HT3
                             ON HT3.HELPER_TABLE_SID = UD.UDC3
                                AND HT3.LIST_NAME = 'RS_UDC3'
                      LEFT JOIN HELPER_TABLE HT4
                             ON HT4.HELPER_TABLE_SID = UD.UDC4
                                AND HT4.LIST_NAME = 'RS_UDC4'
                      LEFT JOIN HELPER_TABLE HT5
                             ON HT5.HELPER_TABLE_SID = UD.UDC5
                                AND HT5.LIST_NAME = 'RS_UDC5'
                      LEFT JOIN HELPER_TABLE HT6
                             ON HT6.HELPER_TABLE_SID = UD.UDC6
                                AND HT6.LIST_NAME = 'RS_UDC6'
               WHERE  C.CFF_MASTER_SID = @CFF_MASTER_SID),
           INPUT_INFO1
           AS (SELECT   I.CFF_MASTER_SID,
						I.CFF_DETAILS_SID,
						I.CFF_NAME,
						I.APPROVED_DATE,
						I.PROJECTION_MASTER_SID,
						I.PROJECTION_DETAILS_SID,
						I.PROJECTION_NAME,
						I.CONTRACT_ID,
						I.CONTRACT_NO,
						I.CONTRACT_NAME,
						I.CONTRACT_TYPE,
						I.COMPANY_MASTER_SID,
						I.CONTRACT_MASTER_SID,
						I.CUSTOMER_ID,
						I.CUSTOMER_NO,
						I.CUSTOMER_NAME,
						I.ITEM_ID,
						I.ITEM_NO,
						I.ITEM_NAME,
						I.ITEM_MASTER_SID,
						I.GL_COMPANY,
						I.FORECASTING_TYPE,
						I.CFF_TYPE,
						I.SALES_INCLUSION,
						I.DEDUCTION_CATEGORY1,
						I.DEDUCTION_CATEGORY2,
						I.DEDUCTION_CATEGORY3,
						I.DEDUCTION_CATEGORY4,
						I.DEDUCTION_CATEGORY5,
						I.DEDUCTION_CATEGORY6,
						I.CREATED_BY,
						I.CREATED_DATE,
						I.MODIFIED_BY,
						I.MODIFIED_DATE,
						I.RS_MODEL_SID,
						I.RS_ID,
						I.RS_NO,
						I.RS_NAME,
						I.RS_CATEGORY,
						I.RS_TYPE,
						I.REBATE_PROGRAM_TYPE,
						I.DEDUCTION_INCLUSION,
                      C.COMPANY_MASTER_SID AS CONTRACT_HOLDER_ID,
                      C.COMPANY_NO         AS CONTRACT_HOLDER_NO,
                      C.COMPANY_NAME       AS CONTRACT_HOLDER_NAME
               FROM   MAIN_INFO I
                      INNER JOIN COMPANY_MASTER C
                              ON I.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID
               WHERE  C.INBOUND_STATUS <> 'D')
      INSERT INTO #INPUT_INFO2(CFF_MASTER_SID,CFF_DETAILS_SID,CFF_NAME,APPROVED_DATE,PROJECTION_MASTER_SID,PROJECTION_DETAILS_SID,PROJECTION_NAME,CONTRACT_ID,CONTRACT_NO,CONTRACT_NAME,CONTRACT_TYPE,COMPANY_MASTER_SID,CONTRACT_MASTER_SID,CUSTOMER_ID,CUSTOMER_NO,CUSTOMER_NAME,ITEM_ID,ITEM_NO,ITEM_NAME,ITEM_MASTER_SID,GL_COMPANY,FORECASTING_TYPE,CFF_TYPE,SALES_INCLUSION,DEDUCTION_CATEGORY1,DEDUCTION_CATEGORY2,DEDUCTION_CATEGORY3,DEDUCTION_CATEGORY4,DEDUCTION_CATEGORY5,DEDUCTION_CATEGORY6,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,RS_MODEL_SID,RS_ID,RS_NO,RS_NAME,RS_CATEGORY,RS_TYPE,REBATE_PROGRAM_TYPE,DEDUCTION_INCLUSION,CONTRACT_HOLDER_ID,CONTRACT_HOLDER_NO,CONTRACT_HOLDER_NAME,COMPANY_ID,COMPANY_NO,COMPANY_NAME)
      SELECT I.CFF_MASTER_SID,
I.CFF_DETAILS_SID,
I.CFF_NAME,
I.APPROVED_DATE,
I.PROJECTION_MASTER_SID,
I.PROJECTION_DETAILS_SID,
I.PROJECTION_NAME,
I.CONTRACT_ID,
I.CONTRACT_NO,
I.CONTRACT_NAME,
I.CONTRACT_TYPE,
I.COMPANY_MASTER_SID,
I.CONTRACT_MASTER_SID,
I.CUSTOMER_ID,
I.CUSTOMER_NO,
I.CUSTOMER_NAME,
I.ITEM_ID,
I.ITEM_NO,
I.ITEM_NAME,
I.ITEM_MASTER_SID,
I.GL_COMPANY,
I.FORECASTING_TYPE,
I.CFF_TYPE,
I.SALES_INCLUSION,
I.DEDUCTION_CATEGORY1,
I.DEDUCTION_CATEGORY2,
I.DEDUCTION_CATEGORY3,
I.DEDUCTION_CATEGORY4,
I.DEDUCTION_CATEGORY5,
I.DEDUCTION_CATEGORY6,
I.CREATED_BY,
I.CREATED_DATE,
I.MODIFIED_BY,
I.MODIFIED_DATE,
I.RS_MODEL_SID,
I.RS_ID,
I.RS_NO,
I.RS_NAME,
I.RS_CATEGORY,
I.RS_TYPE,
I.REBATE_PROGRAM_TYPE,
I.DEDUCTION_INCLUSION,
I.CONTRACT_HOLDER_ID,
I.CONTRACT_HOLDER_NO,
I.CONTRACT_HOLDER_NAME,
             CM.COMPANY_MASTER_SID AS COMPANY_ID,
             CM.COMPANY_NO         AS COMPANY_NO,
             CM.COMPANY_NAME       AS COMPANY_NAME
      FROM   INPUT_INFO1 I
             JOIN COMPANY_MASTER CM
               ON I.GL_COMPANY = CM.COMPANY_MASTER_SID
----------------------PULLING sales attached to cff---------------------------------------
      IF Object_id('TEMPDB..#NM_SALES_PROJECTION') IS NOT NULL
        DROP TABLE #NM_SALES_PROJECTION

      CREATE TABLE #NM_SALES_PROJECTION
        (
           PROJECTION_DETAILS_SID INT,
           PERIOD_SID             INT,
           PROJECTION_SALES       NUMERIC(22, 6),
           PROJECTION_UNITS       NUMERIC(22, 6)
           PRIMARY KEY (PROJECTION_DETAILS_SID, PERIOD_SID)
        )

      INSERT INTO #NM_SALES_PROJECTION
                  (PROJECTION_DETAILS_SID,
                   PERIOD_SID,
                   PROJECTION_SALES,
                   PROJECTION_UNITS)
      SELECT PROJECTION_DETAILS_SID,
             PERIOD_SID,
             PROJECTION_SALES,
             PROJECTION_UNITS
      FROM   NM_SALES_PROJECTION NS
      WHERE  EXISTS (SELECT 1
                     FROM   #TEMP_CFF I
                     WHERE  I.PROJECTION_DETAILS_SID = NS.PROJECTION_DETAILS_SID)
----------------------PULLING non mandated discount  attached to cff---------------------------------------
      IF OBJECT_ID('TEMPDB..#NM_DISCOUNT_PROJECTION') IS NOT NULL
        DROP TABLE #NM_DISCOUNT_PROJECTION

SELECT NS.PROJECTION_DETAILS_SID
	,RS_MODEL_SID
	,NS.PERIOD_SID
	,NS.PROJECTION_SALES
	,ISNULL(NS.PROJECTION_SALES / NULLIF(NMS.PROJECTION_SALES, 0), 0) PROJECTION_RATE
	,ISNULL(NS.PROJECTION_SALES / NULLIF(NMS.PROJECTION_UNITS, 0), 0) PROJECTION_RPU
	,GROWTH
	,REFRESHED_NAME
	,REFRESHED_VALUE
	,RS_CONTRACT_SID
INTO #NM_DISCOUNT_PROJECTION
FROM NM_DISCOUNT_PROJECTION NS
JOIN #NM_SALES_PROJECTION NMS ON NMS.PROJECTION_DETAILS_SID = NS.PROJECTION_DETAILS_SID
and ns.PERIOD_SID=nms.PERIOD_SID
WHERE EXISTS (
		SELECT 1
		FROM #INPUT_INFO2 I
		WHERE NS.PROJECTION_DETAILS_SID = I.PROJECTION_DETAILS_SID
			AND NS.RS_MODEL_SID = I.RS_MODEL_SID
			AND I.FORECASTING_TYPE = 'NON MANDATED'
		)

---------------------------------PULLING PPA DISCOUN discount  attached to cff (GALUAT-817)-----------------------------------------------------------------------

      --IF Object_id('TEMPDB..#NM_PPA_PROJECTION') IS NOT NULL
      --  DROP TABLE #NM_PPA_PROJECTION

      --SELECT *
      --INTO   #NM_PPA_PROJECTION
      --FROM   NM_PPA_PROJECTION NS
      --WHERE  EXISTS (SELECT 1
      --               FROM   #INPUT_INFO2 I
      --               WHERE  NS.PROJECTION_DETAILS_SID = I.PROJECTION_DETAILS_SID
      --                      AND NS.RS_MODEL_SID = I.RS_MODEL_SID
      --                      AND I.FORECASTING_TYPE = 'NON MANDATED')
----------------------PULLING  mandated discount  attached to cff---------------------------------------
      IF Object_id('TEMPDB..#M_DISCOUNT_PROJECTION') IS NOT NULL
        DROP TABLE #M_DISCOUNT_PROJECTION

      SELECT PROJECTION_DETAILS_SID, PERIOD_SID, PROJECTION_SALES, PROJECTION_RATE, SAVE_FLAG, PROJECTION_RPU
      INTO   #M_DISCOUNT_PROJECTION
      FROM   M_DISCOUNT_PROJECTION NS
      WHERE  EXISTS (SELECT 1
                     FROM   #TEMP_CFF I
                     WHERE  NS.PROJECTION_DETAILS_SID = I.PROJECTION_DETAILS_SID
                            AND NS.SAVE_FLAG = 1)
------------------pulling supplemental information------------------------------
      IF Object_id('TEMPDB..#M_SUPPLEMENTAL_DISC_PROJ') IS NOT NULL
        DROP TABLE #M_SUPPLEMENTAL_DISC_PROJ

      SELECT PROJECTION_DETAILS_SID, PERIOD_SID, METHODOLOGY, CONTRACT_PRICE, DISCOUNT_RATE_1, DISCOUNT_RATE_2, ACCESS, PARITY, PARITY_REFERENCE, PARITY_DISCOUNT, PROJECTION_RATE, PROJECTION_SALES, PROJECTION_RPU
      INTO   #M_SUPPLEMENTAL_DISC_PROJ
      FROM   M_SUPPLEMENTAL_DISC_PROJ NS
      WHERE  EXISTS (SELECT 1
                     FROM   #TEMP_CFF I
                     WHERE  NS.PROJECTION_DETAILS_SID = I.PROJECTION_DETAILS_SID)
---------------------------------pulling mandated sales information--------------------
      IF Object_id('TEMPDB..#M_SALES_PROJECTION') IS NOT NULL
        DROP TABLE #M_SALES_PROJECTION

      CREATE TABLE #M_SALES_PROJECTION
        (
           PROJECTION_DETAILS_SID INT,
           PERIOD_SID             INT,
           PROJECTION_SALES       NUMERIC(22, 6),
           PROJECTION_UNITS       NUMERIC(22, 6)
           PRIMARY KEY (PROJECTION_DETAILS_SID, PERIOD_SID)
        )

      INSERT INTO #M_SALES_PROJECTION
                  (PROJECTION_DETAILS_SID,
                   PERIOD_SID,
                   PROJECTION_SALES,
                   PROJECTION_UNITS)
      SELECT PROJECTION_DETAILS_SID,
             PERIOD_SID,
             PROJECTION_SALES,
             PROJECTION_UNITS
      FROM   M_SALES_PROJECTION NS
      WHERE  EXISTS (SELECT 1
                     FROM   #TEMP_CFF I
                     WHERE  I.PROJECTION_DETAILS_SID = NS.PROJECTION_DETAILS_SID)
---------------------------------aggregating  information to get require parmaters in master table information--------------------
      IF Object_id('TEMPDB..#FORECAST_DATA') IS NOT NULL
        DROP TABLE #FORECAST_DATA

      SELECT COALESCE(S.PROJECTION_DETAILS_SID, D.PROJECTION_DETAILS_SID) PROJECTION_DETAILS_SID,
             D.RS_MODEL_SID,
             [YEAR],
             [MONTH],
             Isnull(PROJECTION_SALES, 0)                                  AS SALES_DOLLARS,
             Isnull(PROJECTION_UNITS, 0)                                  AS SALES_UNITS,
             Isnull(PROJECTED_DISCOUNT, 0)                                AS DEDUCTION_DOLLARS,
             Isnull(PROJECTION_RATE, 0)                                   AS DEDUCTION_RATE,
             Isnull(PROJECTION_RPU, 0)                                    AS DEDUCTION_PER_UNIT,
             ( Isnull(PROJECTION_SALES, 0) - PROJECTED_DISCOUNT ) AS NET_SALES_DOLLARS,
             ( ( Isnull(PROJECTION_SALES, 0) - PROJECTED_DISCOUNT ) / NULLIF(PROJECTION_UNITS, 0) ) aS NET_PROFIT_PER_UNIT,
             P.PERIOD_SID
      INTO   #FORECAST_DATA
      FROM   (SELECT PROJECTION_DETAILS_SID,
                     PERIOD_SID,
                     PROJECTION_SALES,
                     PROJECTION_UNITS
              FROM   #NM_SALES_PROJECTION
              UNION
              SELECT PROJECTION_DETAILS_SID,
                     PERIOD_SID,
                     PROJECTION_SALES,
                     PROJECTION_UNITS
              FROM   #M_SALES_PROJECTION) S
             FULL OUTER JOIN (SELECT NS.PROJECTION_DETAILS_SID,
                                     NS.PERIOD_SID,
                                     NS.RS_MODEL_SID,
                                     NS.PROJECTION_SALES AS PROJECTED_DISCOUNT,
                                     NS.PROJECTION_RATE,
                                     NS.PROJECTION_RPU
                              FROM   #NM_DISCOUNT_PROJECTION NS
                              
							  Union All
							  select ndm.PROJECTION_DETAILS_SID,
                                     ndm.PERIOD_SID,
                                     ndm.RS_MODEL_SID,
                                     ndm.PROJECTION_SALES AS PROJECTED_DISCOUNT
	,ISNULL(ndm.PROJECTION_SALES / NULLIF(abc.PROJECTION_SALES, 0), 0) PROJECTION_RATE
	,ISNULL(ndm.PROJECTION_SALES / NULLIF(abc.PROJECTION_UNITS, 0), 0) PROJECTION_RPU
									 from #NM_DISCOUNT_PROJECTION ndm 
									 join #NM_SALES_PROJECTION abc on abc.PROJECTION_DETAILS_SID=ndm.PROJECTION_DETAILS_SID
									 and abc.PERIOD_SID=ndm.PERIOD_SID

                              
							  UNION ALL
                              SELECT PROJECTION_DETAILS_SID,
                                     PERIOD_SID,
                                     RS_MODEL_SID,
                                     ( Isnull(M_PROJECTION_SALES, 0)
                                       + Isnull(S_PROJECTION_SALES, 0) ) AS PROJECTED_DISCOUNT,
                                     ( Isnull(M_PROJECTION_RATE, 0)
                                       + Isnull(S_PROJECTION_RATE, 0) )  AS PROJECTION_RATE,
                                     ( Isnull(M_PROJECTION_RPU, 0)
                                       + Isnull(S_PROJECTION_RPU, 0) )   AS PROJECTION_RPU
                              FROM   (SELECT NS.PROJECTION_DETAILS_SID,
                                             NS.PERIOD_SID,
                                             NULL                AS RS_MODEL_SID,
                                             NS.PROJECTION_SALES AS M_PROJECTION_SALES,
                                             NS.PROJECTION_RATE  AS M_PROJECTION_RATE,
                                             NS.PROJECTION_RPU   AS M_PROJECTION_RPU,
                                             SP.PROJECTION_SALES AS S_PROJECTION_SALES,
                                             SP.PROJECTION_RATE  AS S_PROJECTION_RATE,
                                             SP.PROJECTION_RPU   AS S_PROJECTION_RPU
                                      FROM   #M_DISCOUNT_PROJECTION NS
                                             INNER JOIN #M_SUPPLEMENTAL_DISC_PROJ SP
                                                     ON SP.PROJECTION_DETAILS_SID = NS.PROJECTION_DETAILS_SID
                                                        AND SP.PERIOD_SID = NS.PERIOD_SID)A) D
                          ON S.PROJECTION_DETAILS_SID = D.PROJECTION_DETAILS_SID
                             AND S.PERIOD_SID = D.PERIOD_SID
             INNER JOIN PERIOD P
                     ON P.PERIOD_SID = COALESCE(D.PERIOD_SID, S.PERIOD_SID)
---------------------------------pulling item_price for that cff--------------------
      IF Object_id('TEMPDB..#PROJ_DATES') IS NOT NULL
        DROP TABLE #PROJ_DATES

      SELECT Min(PERIOD_SID) MIN_PERIOD_SID,
             PROJECTION_DETAILS_SID,
             Max(PERIOD_SID) MAX_PERIOD_SID
      INTO   #PROJ_DATES
      FROM   #FORECAST_DATA
      GROUP  BY PROJECTION_DETAILS_SID

      DECLARE @ITEMID           [DBO].[UDT_ITEM]

      INSERT INTO @ITEMID
      SELECT DISTINCT ITEM_MASTER_SID
      FROM   #INPUT_INFO2

      IF Object_id('TEMPDB..#ITEM_PRICING') IS NOT NULL
        DROP TABLE #ITEM_PRICING;

      SELECT ITEM_MASTER_SID,
             PERIOD_SID,
             PRICING_QUALIFIER,
             ITEM_PRICE
      INTO   #ITEM_PRICING
      FROM   [DBO].[Udf_item_pricing](@ITEMID, 'COGS', (SELECT Min(MIN_PERIOD_SID)
                                                        FROM   #PROJ_DATES), (SELECT Max(MAX_PERIOD_SID)
                                                                              FROM   #PROJ_DATES), 'EACH') F
---------------------INSERTING AND CALUCLATING NETSALEES-------------------------
      IF Object_id('TEMPDB..#FINAL') IS NOT NULL
        DROP TABLE #FINAL;

      CREATE TABLE #FINAL
        (
           [CFF_DETAILS_SID]        [INT] NOT NULL,
           [PROJECTION_DETAILS_SID] [INT] NOT NULL,
           [ITEM_MASTER_SID]        [INT] NOT NULL,
           [RS_MODEL_SID]           [INT] NOT NULL,
           [YEAR]                   [INT] NULL,
           [MONTH]                  [INT] NULL,
           [SALES_DOLLARS]          [NUMERIC](23, 6) NULL,
           [SALES_UNITS]            [NUMERIC](23, 6) NULL,
           [DEDUCTION_DOLLARS]      [NUMERIC](23, 6) NULL,
           [DEDUCTION_RATE]         [NUMERIC](23, 6) NULL,
           [DEDUCTION_PER_UNIT]     [NUMERIC](23, 6) NULL,
           [NET_SALES_DOLLARS]      [NUMERIC](24, 6) NULL,
           [NET_PROFIT_PER_UNIT]    [NUMERIC](35, 17) NULL,
           [NET_PROFIT_DOLLARS]     [NUMERIC](34, 6) NULL,
           [COGS_PER_UNIT]          [NUMERIC](22, 6) NULL,
           [COGS_AMOUNT]            [NUMERIC](33, 6) NULL,
           [PERIOD_SID]             [INT] NOT NULL,
           [START_PERIOD_SID]       [INT] NULL,
           [END_PERIOD_SID]         [INT] NULL,
           --PRIMARY KEY (CFF_DETAILS_SID, PROJECTION_DETAILS_SID, RS_MODEL_SID, PERIOD_SID)
        )

      INSERT INTO #FINAL(CFF_DETAILS_SID,PROJECTION_DETAILS_SID,ITEM_MASTER_SID,RS_MODEL_SID,YEAR,MONTH,
	  SALES_DOLLARS,SALES_UNITS,DEDUCTION_DOLLARS,DEDUCTION_RATE,DEDUCTION_PER_UNIT,NET_SALES_DOLLARS,
	  NET_PROFIT_PER_UNIT,NET_PROFIT_DOLLARS,COGS_PER_UNIT,COGS_AMOUNT,PERIOD_SID,START_PERIOD_SID,END_PERIOD_SID)
      SELECT TC.CFF_DETAILS_SID,
             FD.PROJECTION_DETAILS_SID,
             TC.ITEM_MASTER_SID,
             Isnull(RS_MODEL_SID, 0),
             [YEAR],
             [MONTH],
             SALES_DOLLARS,
             SALES_UNITS,
             DEDUCTION_DOLLARS,
             DEDUCTION_RATE,
             DEDUCTION_PER_UNIT,
             NET_SALES_DOLLARS,
             NET_PROFIT_PER_UNIT,
             ( NET_SALES_DOLLARS - ( ITEM_PRICE * SALES_UNITS ) )AS NET_PROFIT_DOLLARS,
             ITEM_PRICE AS COGS_PER_UNIT,
             ( ITEM_PRICE * SALES_UNITS ) aS COGS_AMOUNT,
             FD.PERIOD_SID,
             Min(FD.PERIOD_SID)
                                  OVER (
                                    PARTITION BY CFF_MASTER_SID) AS START_PERIOD_SID,
             Max(FD.PERIOD_SID)
                                OVER (
                                  PARTITION BY CFF_MASTER_SID) AS END_PERIOD_SID
      FROM   #FORECAST_DATA FD
             JOIN #TEMP_CFF TC
               ON TC.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID
             INNER JOIN #ITEM_PRICING T
                     ON TC.ITEM_MASTER_SID = T.ITEM_MASTER_SID
                        AND FD.PERIOD_SID = T.PERIOD_SID
------------------STORING INTO MAIN TABLE ---------------------------------


      INSERT INTO CFF_OUTBOUND_MASTER
                  (FINANCIAL_FORECAST_ID,
                   CFF_DETAILS_SID,
                   FINANCIAL_FORECAST_NAME,
                   TYPE,
                   PROJECT_ID,
                   PROJECTION_NAME,
                   YEAR,
                   MONTH,
                   CONTRACT_ID,
                   CONTRACT_NO,
                   CONTRACT_NAME,
                   CONTRACT_TYPE,
                   CONTRACT_HOLDER_ID,
                   CONTRACT_HOLDER_NO,
                   CONTRACT_HOLDER_NAME,
                   CUSTOMER_ID,
                   CUSTOMER_NO,
                   CUSTOMER_NAME,
                   ITEM_MASTER_SID,
                   COMPANY_MASTER_SID,
                   CONTRACT_MASTER_SID,
                   GL_COMPANY_MASTER_SID,
                   ITEM_ID,
                   ITEM_NO,
                   ITEM_NAME,
                   SALES_DOLLARS,
                   SALES_UNITS,
                   SALES_INCLUSION,
                   DEDUCTION_ID,
                   DEDUCTION_NO,
                   DEDUCTION_NAME,
                   DEDUCTION_CATEGORY,
                   DEDUCTION_TYPE,
                   DEDUCTION_PROGRAM,
                   DEDUCTION_INCLUSION,
                   DEDUCTION_CATEGORY1,
                   DEDUCTION_CATEGORY2,
                   DEDUCTION_CATEGORY3,
                   DEDUCTION_CATEGORY4,
                   DEDUCTION_CATEGORY5,
                   DEDUCTION_CATEGORY6,
                   DEDUCTION_DOLLARS,
                   DEDUCTION_RATE,
                   DEDUCTION_PER_UNIT,
                   NET_SALES_DOLLAR,
                   COGS_PER_UNIT,
                   COGS_AMOUNT,
                   NET_PROFIT_DOLLARS,
                   NET_PROFIT_PER_UNIT,
                   COMPANY_ID,
                   COMPANY_NO,
                   COMPANY_NAME,
                   PERIOD_SID,
                   FINANCIAL_FORECAST_CREATION_DATE,
                   FINANCIAL_FORECAST_APPROVAL_DATE,
                   RS_MODEL_SID,
                   OUTBOUND_STATUS)
      SELECT DISTINCT CFF_MASTER_SID,
                      T.CFF_DETAILS_SID,
                      CFF_NAME,
                      CFF_TYPE,
                      PROJECTION_MASTER_SID,
                      T.PROJECTION_NAME,
                      F.YEAR,
                      F.MONTH,
                      T.CONTRACT_ID,
                      T.CONTRACT_NO,
                      T.CONTRACT_NAME,
                      T.CONTRACT_TYPE,
                      T.CONTRACT_HOLDER_ID,
                      T.CONTRACT_HOLDER_NO,
                      T.CONTRACT_HOLDER_NAME,
                      T.CUSTOMER_ID,
                      T.CUSTOMER_NO,
                      T.CUSTOMER_NAME,
                      T.ITEM_MASTER_SID,
                      T.COMPANY_MASTER_SID,
                      T.CONTRACT_MASTER_SID,
                      T.GL_COMPANY,
                      T.ITEM_ID,
                      T.ITEM_NO,
                      T.ITEM_NAME,
                      F.SALES_DOLLARS,
                      F.SALES_UNITS,
                      T.SALES_INCLUSION,
                      T.RS_ID,
                      T.RS_NO,
                      T.RS_NAME,
                      T.RS_CATEGORY,
                      T.RS_TYPE,
                      T.REBATE_PROGRAM_TYPE,
                      T.DEDUCTION_INCLUSION,
                      T.DEDUCTION_CATEGORY1,
                      T.DEDUCTION_CATEGORY2,
                      T.DEDUCTION_CATEGORY3,
                      T.DEDUCTION_CATEGORY4,
                      T.DEDUCTION_CATEGORY5,
                      T.DEDUCTION_CATEGORY6,
                      F.DEDUCTION_DOLLARS,
                      F.DEDUCTION_RATE,
                      F.DEDUCTION_PER_UNIT,
                      F.NET_SALES_DOLLARS,
                      F.COGS_PER_UNIT,
                      F.COGS_AMOUNT,
                      F.NET_PROFIT_DOLLARS,
                      F.NET_PROFIT_PER_UNIT,
                      T.COMPANY_ID,
                      T.COMPANY_NO,
                      T.COMPANY_NAME,
                      F.PERIOD_SID,
                      T.CREATED_DATE,
                      T.APPROVED_DATE,
                      T.RS_MODEL_SID,
                      'N'
      FROM   #INPUT_INFO2 T
             INNER JOIN #FINAL F
                     ON 
					 T.PROJECTION_DETAILS_SID = F.PROJECTION_DETAILS_SID and
                        T.CFF_DETAILS_SID = F.CFF_DETAILS_SID
                        AND T.RS_MODEL_SID = F.RS_MODEL_SID
GO
