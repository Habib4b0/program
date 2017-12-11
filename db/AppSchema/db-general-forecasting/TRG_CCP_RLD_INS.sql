---------------Insert Trigger------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_CCP_RLD_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_CCP_RLD_INS
  END

GO

--CREATE TRIGGER [dbo].[TRG_CCP_RLD_INS]
--ON [dbo].[CCP_DETAILS]
--AFTER INSERT
--AS
--  BEGIN
--      SET NOCOUNT ON
--      IF Object_id('tempdb..##TEMP_GLOBAL_CCP') IS NOT NULL
--        DROP TABLE ##TEMP_GLOBAL_CCP

--      CREATE TABLE ##TEMP_GLOBAL_CCP
--        (
--           MARKET_TYPE_SID            INT,
--           CONTRACT_HOLDER_SID        INT,
--           CONTRACT_MASTER_SID        INT,
--           COMPANY_MASTER_SID         INT,
--           ITEM_MASTER_SID            INT,
--           BRAND_MASTER_SID           INT NULL,
--           GL_COMP_COMPANY_MASTER_SID INT NULL,
--           THERAPEUTIC_CLASS_HLPR_SID INT NULL
--        )

--      INSERT INTO ##TEMP_GLOBAL_CCP
--                  (CONTRACT_MASTER_SID,
--                   COMPANY_MASTER_SID,
--                   ITEM_MASTER_SID,
--                   GL_COMP_COMPANY_MASTER_SID,
--                   THERAPEUTIC_CLASS_HLPR_SID,
--                   BRAND_MASTER_SID,
--                   CONTRACT_HOLDER_SID,
--                   MARKET_TYPE_SID)
--      SELECT A.CONTRACT_MASTER_SID,
--             A.COMPANY_MASTER_SID,
--             A.ITEM_MASTER_SID,
--             CM2.COMPANY_MASTER_SID AS GL_COMP_COMPANY_MASTER_SID,
--             IM.THERAPEUTIC_CLASS   AS THERAPEUTIC_CLASS_HLPR_SID,
--             IM.BRAND_MASTER_SID    AS BRAND_MASTER_SID,
--             CM.COMPANY_MASTER_SID  AS CONTRACT_HOLDER_SID,
--             B.CONTRACT_TYPE        AS MARKET_TYPE_SID
--      FROM   INSERTED A
--             JOIN CONTRACT_MASTER B
--               ON B.CONTRACT_MASTER_SID = A.CONTRACT_MASTER_SID
--             LEFT JOIN COMPANY_MASTER CM
--                    ON CM.COMPANY_MASTER_SID = B.CONT_HOLD_COMPANY_MASTER_SID
--             LEFT JOIN ITEM_MASTER IM
--                    ON A.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
--             LEFT JOIN GL_COST_CENTER_MASTER GM
--                    ON IM.NDC8 = GM.NDC8
--             LEFT JOIN COMPANY_MASTER CM2
--                    ON CM2.COMPANY_ID = GM.COMPANY_CODE
					
--	IF OBJECT_ID('TEMPDB..#TEMP_DEDUCTION_HIERARCHY') IS NOT NULL 
--  DROP TABLE #TEMP_DEDUCTION_HIERARCHY 

--CREATE TABLE #TEMP_DEDUCTION_HIERARCHY 
--  ( 
--     CCP_DETAILS_SID     INT, 
--     CONTRACT_MASTER_SID INT, 
--     COMPANY_MASTER_SID  INT, 
--     ITEM_MASTER_SID     INT, 
--  ) 

--INSERT INTO #TEMP_DEDUCTION_HIERARCHY 
--SELECT CCP_DETAILS_SID, 
--       CONTRACT_MASTER_SID, 
--       COMPANY_MASTER_SID, 
--       ITEM_MASTER_SID 
--FROM   INSERTED 



--      -------------------------Procedure Call ---------------------------------------
--      IF @@ROWCOUNT > 0
--        BEGIN
--            BEGIN TRY
--                BEGIN TRANSACTION

--                EXEC PRC_PRODUCT_HIERARCHY_DYNAMIC_ADD --PRODUCT

--                EXEC PRC_CUSTOMER_HIERARCHY_DYNAMIC_ADD --CUSTOMER
				
--				EXEC PRC_DEDUCTION_HIERARCHY_DYNAMIC_ADD --DEDUCTION

--                COMMIT
--            END TRY

--            BEGIN CATCH
--                IF @@TRANCOUNT <> 0
--                  ROLLBACK
--            END CATCH
--        END
--  END 
--GO


--IF EXISTS(
--SELECT 1 FROM SYS.triggers WHERE NAME='TRG_CCP_RLD_INS' AND is_disabled=0 AND OBJECT_NAME(parent_id)='CCP_DETAILS'
--)
--BEGIN 
--ALTER TABLE CCP_DETAILS DISABLE TRIGGER TRG_CCP_RLD_INS 
--END
--GO