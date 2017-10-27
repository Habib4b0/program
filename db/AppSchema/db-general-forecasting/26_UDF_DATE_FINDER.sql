IF EXISTS (SELECT 1 
           FROM   SYS.OBJECTS 
           WHERE  TYPE = 'TF' 
                  AND NAME = 'UDF_DATE_FINDER') 
  BEGIN 
      DROP FUNCTION UDF_DATE_FINDER 
  END 

GO 

CREATE FUNCTION [DBO].[UDF_DATE_FINDER] (@PROJECTION_MASTER_SID INT, 
                                         @VAR                   CHAR(1)) 
RETURNS @T TABLE( 
  PROJECTION_DETAILS_SID INT, 
  CCP_DETAILS_SID        INT, 
  RS_MODEL_SID           INT, 
  RS_CATEGORY            VARCHAR(50), 
  START_DATE             DATETIME, 
  END_DATE               DATETIME) 
AS 
  BEGIN 
      DECLARE @START_DATE DATETIME, 
              @END_DATE   DATETIME, 
              @STATUS     INT 

      SELECT @START_DATE = DATEADD(MM, DATEDIFF(MM, 0, FROM_DATE), 0), 
             @END_DATE = DATEADD(MM, DATEDIFF(MM, 0, TO_DATE) + 1, 0) - 1 
      FROM   PROJECTION_MASTER 
      WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

      SELECT @STATUS = HELPER_TABLE_SID 
      FROM   HELPER_TABLE 
      WHERE  LIST_NAME = 'STATUS' 
             AND DESCRIPTION = 'ACTIVE' 
      
	  DECLARE @TEMP_PROJECTION AS TABLE
	  (
			PROJECTION_MASTER_SID INT,
			PROJECTION_DETAILS_SID INT,
			CCP_DETAILS_SID INT,
			RS_MODEL_SID INT
	  )

	  INSERT INTO @TEMP_PROJECTION (PROJECTION_MASTER_SID,PROJECTION_DETAILS_SID,CCP_DETAILS_SID,RS_MODEL_SID)
	  SELECT A.PROJECTION_MASTER_SID,ISNULL(B1.PROJECTION_DETAILS_SID, B2.ARM_ADJUSTMENT_DETAILS_SID),ISNULL(B1.CCP_DETAILS_SID, B2.CCP_DETAILS_SID),RS_MODEL_SID FROM PROJECTION_MASTER A 
             LEFT JOIN PROJECTION_DETAILS B1 
                    ON A.PROJECTION_MASTER_SID = B1.PROJECTION_MASTER_SID 
             LEFT JOIN ARM_ADJUSTMENT_DETAILS B2 
                    ON A.PROJECTION_MASTER_SID = B2.PROJECTION_MASTER_SID 
		WHERE A.PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID

      DECLARE @TEMP_CONTRACT_JOIN AS TABLE 
        ( 
           PROJECTION_MASTER_SID     INT, 
           PROJECTION_DETAILS_SID    INT, 
           CCP_DETAILS_SID           INT, 
           CONTRACT_MASTER_SID       INT, 
           CONTRACT_START_DATE       DATETIME, 
           CONTRACT_END_DATE         DATETIME, 
           COMPANY_MASTER_SID        INT, 
           COMPANY_START_DATE        DATETIME, 
           COMPANY_END_DATE          DATETIME, 
           ITEM_MASTER_SID           INT, 
           ITEM_START_DATE           DATETIME, 
           ITEM_END_DATE             DATETIME, 
           RS_MODEL_SID              INT, 
           RS_CATEGORY               VARCHAR(50), 
           CFP_START_DATE            DATETIME, 
           CFP_END_DATE              DATETIME, 
           CFP_COMPANY_START_DATE    DATETIME, 
           CFP_COMPANY_END_DATE      DATETIME, 
           IFP_START_DATE            DATETIME, 
           IFP_END_DATE              DATETIME, 
           IFP_ITEM_START_DATE       DATETIME, 
           IFP_ITEM_END_DATE         DATETIME, 
           PS_START_DATE             DATETIME, 
           PS_END_DATE               DATETIME, 
           CONTRACT_PRICE_START_DATE DATETIME, 
           CONTRACT_PRICE_END_DATE   DATETIME, 
           RS_START_DATE             DATETIME, 
           RS_END_DATE               DATETIME, 
           ITEM_REBATE_START_DATE    DATETIME, 
           ITEM_REBATE_END_DATE      DATETIME 
        ) 

      INSERT INTO @TEMP_CONTRACT_JOIN 
                  (PROJECTION_MASTER_SID, 
                   PROJECTION_DETAILS_SID, 
                   CCP_DETAILS_SID, 
                   CONTRACT_MASTER_SID, 
                   CONTRACT_START_DATE, 
                   CONTRACT_END_DATE, 
                   COMPANY_MASTER_SID, 
                   COMPANY_START_DATE, 
                   COMPANY_END_DATE, 
                   ITEM_MASTER_SID, 
                   ITEM_START_DATE, 
                   ITEM_END_DATE, 
                   RS_MODEL_SID, 
                   RS_CATEGORY, 
                   CFP_START_DATE, 
                   CFP_END_DATE, 
                   CFP_COMPANY_START_DATE, 
                   CFP_COMPANY_END_DATE, 
                   IFP_START_DATE, 
                   IFP_END_DATE, 
                   IFP_ITEM_START_DATE, 
                   IFP_ITEM_END_DATE, 
                   PS_START_DATE, 
                   PS_END_DATE, 
                   CONTRACT_PRICE_START_DATE, 
                   CONTRACT_PRICE_END_DATE, 
                   RS_START_DATE, 
                   RS_END_DATE, 
                   ITEM_REBATE_START_DATE, 
                   ITEM_REBATE_END_DATE) 
      SELECT A.PROJECTION_MASTER_SID, 
             A.PROJECTION_DETAILS_SID,
             A.CCP_DETAILS_SID, 
             C.CONTRACT_MASTER_SID, 
             CON.START_DATE           AS CONTRACT_START_DATE, 
             CON.END_DATE             AS CONTRACT_END_DATE, 
             C.COMPANY_MASTER_SID, 
             COM.COMPANY_START_DATE, 
             COM.COMPANY_END_DATE, 
             C.ITEM_MASTER_SID, 
             IM.ITEM_START_DATE, 
             IM.ITEM_END_DATE, 
             RS_M.RS_MODEL_SID, 
             H.DESCRIPTION, 
             CFP_M.CFP_START_DATE, 
             CFP_M.CFP_END_DATE, 
             CFP_D.COMPANY_START_DATE AS CFP_COMPANY_START_DATE, 
             CFP_D.COMPANY_END_DATE   AS CFP_COMPANY_END_DATE, 
             IFP_M.IFP_START_DATE, 
             IFP_M.IFP_END_DATE, 
             IFP_D.ITEM_START_DATE    AS IFP_ITEM_START_DATE, 
             IFP_D.ITEM_END_DATE      AS IFP_ITEM_END_DATE, 
             PS_M.PS_START_DATE, 
             PS_M.PS_END_DATE, 
             PS_D.CONTRACT_PRICE_START_DATE, 
             PS_D.CONTRACT_PRICE_END_DATE, 
             RS_M.RS_START_DATE, 
             RS_M.RS_END_DATE, 
             RS_D.ITEM_REBATE_START_DATE, 
             RS_D.ITEM_REBATE_END_DATE 
      FROM   @TEMP_PROJECTION A
             JOIN CCP_DETAILS C 
               ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
             JOIN CONTRACT_MASTER CON 
               ON CON.CONTRACT_MASTER_SID = C.CONTRACT_MASTER_SID 
                  AND CON.CONTRACT_STATUS = @STATUS 
                  AND CON.INBOUND_STATUS <> 'D' 
             JOIN COMPANY_MASTER COM 
               ON COM.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID 
                  AND COM.COMPANY_STATUS = @STATUS 
                  AND COM.INBOUND_STATUS <> 'D' 
             JOIN ITEM_MASTER IM 
               ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID 
                  AND IM.ITEM_STATUS = @STATUS 
                  AND IM.INBOUND_STATUS <> 'D' 
             JOIN CFP_CONTRACT CFP_M 
               ON CFP_M.CONTRACT_MASTER_SID = CON.CONTRACT_MASTER_SID 
                  AND CFP_M.CFP_STATUS = @STATUS 
                  AND CFP_M.INBOUND_STATUS <> 'D' 
             JOIN CFP_CONTRACT_DETAILS CFP_D 
               ON CFP_M.CFP_CONTRACT_SID = CFP_D.CFP_CONTRACT_SID 
                  AND CFP_D.COMPANY_MASTER_SID = COM.COMPANY_MASTER_SID 
                  AND CFP_D.INBOUND_STATUS <> 'D' 
             JOIN IFP_CONTRACT IFP_M 
               ON IFP_M.CONTRACT_MASTER_SID = CON.CONTRACT_MASTER_SID 
                  AND IFP_M.CFP_CONTRACT_SID = CFP_M.CFP_CONTRACT_SID 
                  AND IFP_M.IFP_STATUS = @STATUS 
                  AND IFP_M.INBOUND_STATUS <> 'D' 
             JOIN IFP_CONTRACT_DETAILS IFP_D 
               ON IFP_D.IFP_CONTRACT_SID = IFP_M.IFP_CONTRACT_SID 
                  AND IFP_D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID 
                  AND IFP_D.INBOUND_STATUS <> 'D' 
             LEFT JOIN PS_CONTRACT PS_M 
                    ON PS_M.CONTRACT_MASTER_SID = CON.CONTRACT_MASTER_SID 
                       AND PS_M.CFP_CONTRACT_SID = CFP_M.CFP_CONTRACT_SID 
                       AND PS_M.IFP_CONTRACT_SID = IFP_M.IFP_CONTRACT_SID 
             LEFT JOIN PS_CONTRACT_DETAILS PS_D 
                    ON PS_D.PS_CONTRACT_SID = PS_M.PS_CONTRACT_SID 
                       AND PS_D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID 
                       AND PS_D.INBOUND_STATUS <> 'D' 
             LEFT JOIN RS_CONTRACT RS_M 
                    ON RS_M.CONTRACT_MASTER_SID = CON.CONTRACT_MASTER_SID 
                       AND RS_M.CFP_CONTRACT_SID = CFP_M.CFP_CONTRACT_SID 
                       AND RS_M.IFP_CONTRACT_SID = IFP_M.IFP_CONTRACT_SID 
                       AND RS_M.PS_CONTRACT_SID = PS_M.PS_CONTRACT_SID 
                       AND CASE 
                             WHEN A.RS_MODEL_SID IS NOT NULL THEN RS_M.RS_MODEL_SID 
                             ELSE 1 
                           END = CASE 
                                   WHEN A.RS_MODEL_SID IS NOT NULL THEN A.RS_MODEL_SID 
                                   ELSE 1 
                                 END 
             LEFT JOIN ARM_DEDUCTION_SELECTION ADS 
                    ON ADS.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 
                       AND ADS.RS_CONTRACT_SID = RS_M.RS_CONTRACT_SID 
             LEFT JOIN RS_CONTRACT_DETAILS RS_D 
                    ON RS_D.RS_CONTRACT_SID = RS_M.RS_CONTRACT_SID 
                       AND RS_D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID 
                       AND RS_D.INBOUND_STATUS <> 'D' 
             LEFT JOIN RS_MODEL RS 
                    ON RS.RS_MODEL_SID = RS_M.RS_MODEL_SID 
                       AND RS.INBOUND_STATUS <> 'D' 
             LEFT JOIN HELPER_TABLE H 
                    ON H.HELPER_TABLE_SID = RS_M.RS_CATEGORY 
      WHERE  A.PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID
	         AND PS_M.INBOUND_STATUS <> 'D' 
             AND RS_M.INBOUND_STATUS <> 'D' 
             AND CASE 
                   WHEN @VAR = 'D' THEN RS_M.RS_STATUS
                   ELSE 1 
                 END = CASE 
                          WHEN @VAR = 'D' THEN @STATUS
                          ELSE 1 
                        END 
			 AND CASE 
                   WHEN @VAR = 'P' THEN PS_M.PS_STATUS
                   ELSE 1 
                 END = CASE 
                          WHEN @VAR = 'P' THEN @STATUS
                          ELSE 1 
                        END
             AND CASE 
                   WHEN @VAR = 'P' THEN PS_D.INBOUND_STATUS 
                   ELSE 'A' 
                 END <> CASE 
                          WHEN @VAR = 'P' THEN 'D' 
                          ELSE 'B'
                        END 
             AND CASE 
                   WHEN @VAR = 'D' THEN RS_D.INBOUND_STATUS 
                   ELSE 'A'
                 END <> CASE 
                          WHEN @VAR = 'D' THEN 'D' 
                          ELSE 'B'
                        END 

      -------- WITH CROSS APPLY STARTS HERE 
      INSERT INTO @T 
                  (PROJECTION_DETAILS_SID, 
                   CCP_DETAILS_SID, 
                   RS_MODEL_SID, 
                   RS_CATEGORY, 
                   START_DATE, 
                   END_DATE) 
      SELECT PROJECTION_DETAILS_SID, 
             CCP_DETAILS_SID, 
             RS_MODEL_SID, 
             RS_CATEGORY, 
             CASE 
                          WHEN START_DATE > END_DATE THEN DATEADD(MM, -2, @START_DATE) 
                          ELSE DATEADD(MM, DATEDIFF(MM, 0, START_DATE), 0) 
                        END AS START_DATE,  
             CASE 
                        WHEN START_DATE > END_DATE THEN DATEADD(MM, -1, @START_DATE) 
                        ELSE DATEADD(MM, DATEDIFF(MM, 0, END_DATE) + 1, 0) - 1 
                      END AS END_DATE
      FROM   (SELECT PROJECTION_DETAILS_SID, 
                     CCP_DETAILS_SID, 
                     CASE 
                                    WHEN @VAR IN ( 'C', 'P' ) THEN 0 
                                    ELSE RS_MODEL_SID 
                                  END AS RS_MODEL_SID,  
                     CASE 
                                   WHEN @VAR IN ( 'C', 'P' ) THEN ' ' 
                                   ELSE RS_CATEGORY 
                                 END AS RS_CATEGORY,  
                     CASE 
                                  WHEN @VAR = 'C' THEN MAX(START_DATE) 
                                  WHEN @VAR = 'P' THEN 
                                    CASE 
                                      WHEN MAX(START_DATE) > MAX(PS_START_DATE) THEN MAX(START_DATE)
                                      ELSE MAX(PS_START_DATE) 
                                    END 
                                  ELSE 
                                    CASE 
                                      WHEN MAX(START_DATE) > MAX(RS_START_DATE) THEN MAX(START_DATE)
                                      ELSE MAX(RS_START_DATE) 
                                    END 
                                END AS START_DATE, 
                      CASE 
                                WHEN @VAR = 'C' THEN MIN(END_DATE) 
                                WHEN @VAR = 'P' THEN 
                                  CASE 
                                    WHEN MIN(END_DATE) < MIN(PS_END_DATE) THEN MIN(END_DATE) 
                                    ELSE MIN(PS_END_DATE) 
                                  END 
                                ELSE 
                                  CASE 
                                    WHEN MIN(END_DATE) < MIN(RS_END_DATE) THEN MIN(END_DATE) 
                                    ELSE MIN(RS_END_DATE) 
                                  END 
                              END END_DATE
              FROM   (SELECT PROJECTION_MASTER_SID, 
                             PROJECTION_DETAILS_SID, 
                             CCP_DETAILS_SID, 
                             CONTRACT_MASTER_SID, 
                             COMPANY_MASTER_SID, 
                             ITEM_MASTER_SID, 
                             RS_MODEL_SID, 
                             RS_CATEGORY, 
                             CASE 
                                            WHEN MAX(MIN_START_DATE) <= @START_DATE THEN @START_DATE
                                            ELSE MAX(MIN_START_DATE) 
                                          END AS [START_DATE], 
                             CASE 
                                          WHEN ISNULL(MIN(MIN_END_DATE), @END_DATE) >= @END_DATE THEN @END_DATE
                                          ELSE MIN(MIN_END_DATE) 
                                        END AS [END_DATE], 
                             CASE 
                                               WHEN MAX(MIN_PS_START_DATE) <= @START_DATE THEN @START_DATE
                                               ELSE MAX(MIN_PS_START_DATE) 
                                             END AS [PS_START_DATE], 
                             CASE 
                                             WHEN ISNULL(MIN(MIN_PS_END_DATE), @END_DATE) >= @END_DATE THEN @END_DATE
                                             ELSE MIN(MIN_PS_END_DATE) 
                                           END AS [PS_END_DATE],  
                             CASE 
                                               WHEN MAX(MIN_RS_START_DATE) <= @START_DATE THEN @START_DATE
                                               ELSE MAX(MIN_RS_START_DATE) 
                                             END AS [RS_START_DATE], 
                             CASE 
                                             WHEN ISNULL(MIN(MIN_RS_END_DATE), @END_DATE) >= @END_DATE THEN @END_DATE
                                             ELSE MIN(MIN_RS_END_DATE) 
                                           END AS [RS_END_DATE]
                      FROM   @TEMP_CONTRACT_JOIN A 
                             CROSS APPLY (VALUES (CONTRACT_START_DATE, 
                                         CONTRACT_END_DATE), 
                                                 (CFP_START_DATE, 
                                         CFP_END_DATE), 
                                                 (CFP_COMPANY_START_DATE, 
                                         CFP_COMPANY_END_DATE), 
                                                 (IFP_START_DATE, 
                                         IFP_END_DATE), 
                                                 (IFP_ITEM_START_DATE, 
                                         IFP_ITEM_END_DATE) )CS (MIN_START_DATE, MIN_END_DATE)
                             CROSS APPLY (VALUES(PS_START_DATE, 
                                         PS_END_DATE), 
                                                (CONTRACT_PRICE_START_DATE, 
                                         CONTRACT_PRICE_END_DATE) )CSP (MIN_PS_START_DATE, MIN_PS_END_DATE)
                             CROSS APPLY (VALUES (RS_START_DATE, 
                                         RS_END_DATE), 
                                                 (ITEM_REBATE_START_DATE, 
                                         ITEM_REBATE_END_DATE)) CSR (MIN_RS_START_DATE, MIN_RS_END_DATE)
                      GROUP  BY PROJECTION_MASTER_SID, 
                                PROJECTION_DETAILS_SID, 
                                CCP_DETAILS_SID, 
                                CONTRACT_MASTER_SID, 
                                COMPANY_MASTER_SID, 
                                ITEM_MASTER_SID, 
                                RS_MODEL_SID, 
                                RS_CATEGORY)A 
              GROUP  BY PROJECTION_DETAILS_SID, 
                        CCP_DETAILS_SID, 
                        CASE 
                          WHEN @VAR IN ( 'C', 'P' ) THEN 0 
                          ELSE RS_MODEL_SID 
                        END, 
                        CASE 
                          WHEN @VAR IN ( 'C', 'P' ) THEN ' ' 
                          ELSE RS_CATEGORY 
                        END)A 

      RETURN 
  END 

GO