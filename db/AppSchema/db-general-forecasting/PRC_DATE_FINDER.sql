IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_DATE_FINDER' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].[PRC_DATE_FINDER] 
  END 

GO 

CREATE PROCEDURE [dbo].[PRC_DATE_FINDER] (@PROJECTION_MASTER_SID INT,
                                         @VAR                   CHAR(1),
                                         @USER_ID               INT,
                                         @SESSION_ID            VARCHAR(50))
AS
  BEGIN
      SET NOCOUNT ON

      DECLARE @START_DATE    DATETIME,
              @END_DATE      DATETIME,
              @CCP_HIERARCHY VARCHAR(150)=Concat('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @D_SQL         NVARCHAR(MAX)

      SELECT @START_DATE = Dateadd(MM, Datediff(MM, 0, FROM_DATE), 0),
             @END_DATE = Dateadd(MM, Datediff(MM, 0, TO_DATE) + 1, 0) - 1
      FROM   PROJECTION_MASTER
      WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

     IF Object_id ('#TEMP_CONTRACT_JOIN', 'U') IS NOT NULL
        DROP TABLE dbo.#TEMP_CONTRACT_JOIN;

      CREATE TABLE #TEMP_CONTRACT_JOIN
        (
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
		   RS_CONTRACT_SID           INT,
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

      SET @D_SQL=Concat('INSERT INTO #TEMP_CONTRACT_JOIN 
                  ( 
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
				   RS_CONTRACT_SID, 
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
      SELECT 
            B1.CCP_DETAILS_SID, 
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
			 RS_M.RS_CONTRACT_SID, 
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
      FROM    ', @CCP_HIERARCHY, ' B1 
              JOIN      CCP_DETAILS C ON C.CCP_DETAILS_SID = B1.CCP_DETAILS_SID
      JOIN      CONTRACT_MASTER CON ON CON.CONTRACT_MASTER_SID = C.CONTRACT_MASTER_SID
      JOIN      COMPANY_MASTER COM ON COM.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID
      JOIN      ITEM_MASTER IM ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID
      JOIN      CFP_CONTRACT CFP_M ON CFP_M.CONTRACT_MASTER_SID = CON.CONTRACT_MASTER_SID
      JOIN      CFP_CONTRACT_DETAILS CFP_D ON CFP_M.CFP_CONTRACT_SID = CFP_D.CFP_CONTRACT_SID
                                          AND CFP_D.COMPANY_MASTER_SID = COM.COMPANY_MASTER_SID
      JOIN      IFP_CONTRACT IFP_M ON IFP_M.CONTRACT_MASTER_SID = CON.CONTRACT_MASTER_SID
                                  AND IFP_M.CFP_CONTRACT_SID = CFP_M.CFP_CONTRACT_SID
      JOIN      IFP_CONTRACT_DETAILS IFP_D ON IFP_D.IFP_CONTRACT_SID = IFP_M.IFP_CONTRACT_SID
                                          AND IFP_D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
      LEFT JOIN PS_CONTRACT PS_M ON PS_M.CONTRACT_MASTER_SID = CON.CONTRACT_MASTER_SID
                                AND PS_M.CFP_CONTRACT_SID = CFP_M.CFP_CONTRACT_SID
                                AND PS_M.IFP_CONTRACT_SID = IFP_M.IFP_CONTRACT_SID  AND PS_M.INBOUND_STATUS <> ''D''
      LEFT JOIN PS_CONTRACT_DETAILS PS_D ON PS_D.PS_CONTRACT_SID = PS_M.PS_CONTRACT_SID
                                        AND PS_D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID   AND PS_D.INBOUND_STATUS <> ''D''
      LEFT JOIN RS_CONTRACT RS_M ON RS_M.CONTRACT_MASTER_SID = CON.CONTRACT_MASTER_SID
                                AND RS_M.CFP_CONTRACT_SID = CFP_M.CFP_CONTRACT_SID
                                AND RS_M.IFP_CONTRACT_SID = IFP_M.IFP_CONTRACT_SID
                                AND RS_M.PS_CONTRACT_SID = PS_M.PS_CONTRACT_SID     AND RS_M.INBOUND_STATUS <> ''D''
      LEFT JOIN RS_CONTRACT_DETAILS RS_D ON RS_D.RS_CONTRACT_SID = RS_M.RS_CONTRACT_SID
                                        AND RS_D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID    AND RS_D.INBOUND_STATUS <> ''D''
      LEFT JOIN RS_MODEL RS ON RS.RS_MODEL_SID = RS_M.RS_MODEL_SID     AND RS.INBOUND_STATUS <> ''D''
      LEFT JOIN HELPER_TABLE H ON H.HELPER_TABLE_SID = RS_M.RS_CATEGORY
      WHERE      CON.INBOUND_STATUS <> ''D''
                AND COM.INBOUND_STATUS <> ''D''
                AND IM.INBOUND_STATUS <> ''D''
                AND CFP_M.INBOUND_STATUS <> ''D''
                AND CFP_D.INBOUND_STATUS <> ''D''
                AND IFP_M.INBOUND_STATUS <> ''D''
                AND IFP_D.INBOUND_STATUS <> ''D''
                
                
              
               
              ')

      EXEC sp_executesql @D_SQL

	
      -------- WITH CROSS APPLY STARTS HERE  
      IF Object_id ('#TEMP_CONTRACT_JOIN', 'U') IS NOT NULL
        DROP TABLE dbo.#T;

      CREATE TABLE #T
        (
           CCP_DETAILS_SID INT,
           RS_MODEL_SID    INT,
		   RS_CONTRACT_SID INT,
           RS_CATEGORY     VARCHAR(50),
           START_DATE      DATETIME,
           END_DATE        DATETIME
        )

      INSERT INTO #T
                  (CCP_DETAILS_SID,
                   RS_MODEL_SID,
				   RS_CONTRACT_SID,
                   RS_CATEGORY,
                   START_DATE,
                   END_DATE)
      SELECT CCP_DETAILS_SID,
             RS_MODEL_SID,
			 RS_CONTRACT_SID,
             RS_CATEGORY,
             CASE
                          WHEN START_DATE > END_DATE THEN Dateadd(MM, -2, @START_DATE)
                          ELSE Dateadd(MM, Datediff(MM, 0, START_DATE), 0)
                        END AS START_DATE,
             CASE
                        WHEN START_DATE > END_DATE THEN Dateadd(MM, -1, @START_DATE)
                        ELSE Dateadd(MM, Datediff(MM, 0, END_DATE) + 1, 0) - 1
                      END AS END_DATE
      FROM   (SELECT CCP_DETAILS_SID,
                     CASE
                                    WHEN @VAR IN ( 'C', 'P' ) THEN 0
                                    ELSE RS_MODEL_SID
                                  END AS RS_MODEL_SID,
                     
					 CASE
                                        WHEN @VAR IN ( 'C', 'P' ) THEN 0
                                        ELSE RS_CONTRACT_SID
                                       END AS RS_CONTRACT_SID,

                     CASE
                                   WHEN @VAR IN ( 'C', 'P' ) THEN ' '
                                   ELSE RS_CATEGORY
                                 END AS RS_CATEGORY,
                     CASE
                                  WHEN @VAR = 'C' THEN Max(START_DATE)
                                  WHEN @VAR = 'P' THEN
                                    CASE
                                      WHEN Max(START_DATE) > Max(PS_START_DATE) THEN Max(START_DATE)
                                      ELSE Max(PS_START_DATE)
                                    END
                                  ELSE
                                    CASE
                                      WHEN Max(START_DATE) > Max(RS_START_DATE) THEN Max(START_DATE)
                                      ELSE Max(RS_START_DATE)
                                    END
                                END AS START_DATE,
                     CASE
                                WHEN @VAR = 'C' THEN Min(END_DATE)
                                WHEN @VAR = 'P' THEN
                                  CASE
                                    WHEN Min(END_DATE) < Min(PS_END_DATE) THEN Min(END_DATE)
                                    ELSE Min(PS_END_DATE)
                                  END
                                ELSE
                                  CASE
                                    WHEN Min(END_DATE) < Min(RS_END_DATE) THEN Min(END_DATE)
                                    ELSE Min(RS_END_DATE)
                                  END
                              END AS END_DATE
              FROM   (SELECT CCP_DETAILS_SID,
                             CONTRACT_MASTER_SID,
                             COMPANY_MASTER_SID,
                             ITEM_MASTER_SID,
                             RS_MODEL_SID,
							 RS_CONTRACT_SID,
                             RS_CATEGORY,
                             CASE
                                            WHEN Max(MIN_START_DATE) <= @START_DATE THEN @START_DATE
                                            ELSE Max(MIN_START_DATE)
                                          END AS [START_DATE],
                             CASE
                                          WHEN Isnull(Min(MIN_END_DATE), @END_DATE) >= @END_DATE THEN @END_DATE
                                          ELSE Min(MIN_END_DATE)
                                        END AS [END_DATE],
                             CASE
                                               WHEN Max(MIN_PS_START_DATE) <= @START_DATE THEN @START_DATE
                                               ELSE Max(MIN_PS_START_DATE)
                                             END AS [PS_START_DATE],
                             CASE
                                             WHEN Isnull(Min(MIN_PS_END_DATE), @END_DATE) >= @END_DATE THEN @END_DATE
                                             ELSE Min(MIN_PS_END_DATE)
                                           END AS [PS_END_DATE],
                             CASE
                                               WHEN Max(MIN_RS_START_DATE) <= @START_DATE THEN @START_DATE
                                               ELSE Max(MIN_RS_START_DATE)
                                             END AS [RS_START_DATE],
                             CASE
                                             WHEN Isnull(Min(MIN_RS_END_DATE), @END_DATE) >= @END_DATE THEN @END_DATE
                                             ELSE Min(MIN_RS_END_DATE)
                                           END AS [RS_END_DATE]
                      FROM   #TEMP_CONTRACT_JOIN A
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
                      GROUP  BY CCP_DETAILS_SID,
                                CONTRACT_MASTER_SID,
                                COMPANY_MASTER_SID,
                                ITEM_MASTER_SID,
                                RS_MODEL_SID,
								RS_CONTRACT_SID,
                                RS_CATEGORY)A
              GROUP  BY CCP_DETAILS_SID,
                        CASE
                          WHEN @VAR IN ( 'C', 'P' ) THEN 0
                          ELSE RS_MODEL_SID
                        END,
                        CASE
                          WHEN @VAR IN ( 'C', 'P' ) THEN ' '
                          ELSE RS_CATEGORY
                        END,
						CASE
                                        WHEN @VAR IN ( 'C', 'P' ) THEN 0
                                        ELSE RS_CONTRACT_SID
                                       END)A

		SELECT CCP_DETAILS_SID,
			   RS_MODEL_SID,
			   RS_CONTRACT_SID,
			   RS_CATEGORY,
			   START_DATE,
			   END_DATE,
			   P.PERIOD_SID  AS START_SID,
			   P1.PERIOD_SID AS END_SID
		FROM   #T T
			   JOIN PERIOD P
				 ON P.PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, T.START_DATE), 0)
			   JOIN PERIOD P1
				 ON P1.PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, T.END_DATE), 0) 

    END    
