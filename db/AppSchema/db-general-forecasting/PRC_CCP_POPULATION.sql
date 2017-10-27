IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_CCP_POPULATION'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [dbo].[PRC_CCP_POPULATION]
  END
GO
CREATE PROCEDURE [dbo].[PRC_CCP_POPULATION]
AS
  BEGIN
      SET NOCOUNT ON

      MERGE INTO CCP_DETAILS AS Target
      USING (SELECT DISTINCT CM.CONTRACT_MASTER_SID,
                             CMP_MAS.COMPANY_MASTER_SID,
                             IM.ITEM_MASTER_SID
             FROM   CONTRACT_MASTER CM
                    INNER JOIN CFP_CONTRACT CCT
                            ON CM.CONTRACT_MASTER_SID = CCT.CONTRACT_MASTER_SID
                               AND CM.INBOUND_STATUS <> 'D'
                               AND CCT.INBOUND_STATUS <> 'D'
                    INNER JOIN CFP_CONTRACT_DETAILS CCD
                            ON CCT.CFP_CONTRACT_SID = CCD.CFP_CONTRACT_SID
                    INNER JOIN COMPANY_MASTER CMP_MAS
                            ON CMP_MAS.COMPANY_MASTER_SID = CCD.COMPANY_MASTER_SID
                               AND CMP_MAS.INBOUND_STATUS <> 'D'
                    INNER JOIN IFP_CONTRACT IFP_CONT
                            ON IFP_CONT.CFP_CONTRACT_SID = CCT.CFP_CONTRACT_SID
                               AND IFP_CONT.INBOUND_STATUS <> 'D'
                               AND CM.CONTRACT_MASTER_SID = IFP_CONT.CONTRACT_MASTER_SID
                    INNER JOIN IFP_CONTRACT_DETAILS ICD
                            ON IFP_CONT.IFP_CONTRACT_SID = ICD.IFP_CONTRACT_SID
                    INNER JOIN ITEM_MASTER IM
                            ON IM.ITEM_MASTER_SID = ICD.ITEM_MASTER_SID
                               AND IM.INBOUND_STATUS <> 'D') AS Source
      ON Target.contract_master_sid = Source.contract_master_sid
         AND Target.COMPANY_MASTER_SID = Source.COMPANY_MASTER_SID
         AND Target.ITEM_MASTER_SID = Source.ITEM_MASTER_SID
      WHEN NOT MATCHED THEN
        INSERT (CONTRACT_MASTER_SID,
                COMPANY_MASTER_SID,
                ITEM_MASTER_SID)
        VALUES (Source.contract_master_sid,
                Source.COMPANY_MASTER_SID,
                Source.ITEM_MASTER_SID);
  END

GO 