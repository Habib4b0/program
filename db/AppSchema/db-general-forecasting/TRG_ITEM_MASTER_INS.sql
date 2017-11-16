IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_ITEM_MASTER_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_MASTER_INS
  END

GO
CREATE TRIGGER [dbo].[TRG_ITEM_MASTER_INS]
ON [dbo].[ITEM_MASTER]
AFTER INSERT
AS
  BEGIN
      SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_ITEM_MASTER
                    (ITEM_MASTER_SID,
                     ITEM_ID,
                     ITEM_NO,
                     ITEM_CODE,
                     ITEM_NAME,
                     BRAND_MASTER_SID,
                     ITEM_DESC,
                     PACKAGE_SIZE,
                     PACKAGE_SIZE_INTRO_DATE,
                     UPPS,
                     ITEM_START_DATE,
                     ITEM_END_DATE,
                     ITEM_STATUS,
                     MANUFACTURER_ID,
                     LABELER_CODE,
                     ORGANIZATION_KEY,
                     ACQUISITION_DATE,
                     AUTHORIZED_GENERIC,
                     AUTHORIZED_GENERIC_START_DATE,
                     AUTHORIZED_GENERIC_END_DATE,
                     FORM,
                     STRENGTH,
                     THERAPEUTIC_CLASS,
                     FIRST_SALE_DATE,
                     ITEM_TYPE_INDICATION,
                     ITEM_CLASS,
                     ITEM_TYPE,
                     MARKET_TERMINATION_DATE,
                     NEW_FORMULATION_INDICATOR,
                     NEW_FORMULATION,
                     NEW_FORMULATION_START_DATE,
                     NEW_FORMULATION_END_DATE,
                     PEDIATRIC_EXCLUSIVE_INDICATOR,
                     PEDIATRIC_EXCLUSIVE_START_DATE,
                     PEDIATRIC_EXCLUSIVE_END_DATE,
                     CLOTTING_FACTOR_INDICATOR,
                     CLOTTING_FACTOR_START_DATE,
                     CLOTTING_FACTOR_END_DATE,
                     PRIMARY_UOM,
                     SECONDARY_UOM,
                     SHELF_LIFE,
                     SHELF_LIFE_TYPE,
                     DUAL_PRICING_INDICATOR,
                     ITEM_FAMILY_ID,
                     ACQUIRED_AMP,
                     ACQUIRED_BAMP,
                     OBRA_BAMP,
                     DRA,
                     DOSES_PER_UNIT,
                     DISCONTINUATION_DATE,
                     LAST_LOT_EXPIRATION_DATE,
                     DIVESTITURE_DATE,
                     NON_FEDERAL_EXPIRATION_DATE,
                     NDC9,
                     NDC8,
                     ITEM_CATEGORY,
                     BASELINE_AMP,
					 ALT_BASELINE_AMP,
                     BASE_CPI_PERIOD,
                     BASE_CPI,
					 ALT_BASE_CPI,
                     PACKAGE_SIZE_CODE,
                     INTERNAL_NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_MASTER_SID,
               ITEM_ID,
               ITEM_NO,
               ITEM_CODE,
               ITEM_NAME,
               BRAND_MASTER_SID,
               ITEM_DESC,
               PACKAGE_SIZE,
               PACKAGE_SIZE_INTRO_DATE,
               UPPS,
               ITEM_START_DATE,
               ITEM_END_DATE,
               ITEM_STATUS,
               MANUFACTURER_ID,
               LABELER_CODE,
               ORGANIZATION_KEY,
               ACQUISITION_DATE,
               AUTHORIZED_GENERIC,
               AUTHORIZED_GENERIC_START_DATE,
               AUTHORIZED_GENERIC_END_DATE,
               FORM,
               STRENGTH,
               THERAPEUTIC_CLASS,
               FIRST_SALE_DATE,
               ITEM_TYPE_INDICATION,
               ITEM_CLASS,
               ITEM_TYPE,
               MARKET_TERMINATION_DATE,
               NEW_FORMULATION_INDICATOR,
               NEW_FORMULATION,
               NEW_FORMULATION_START_DATE,
               NEW_FORMULATION_END_DATE,
               PEDIATRIC_EXCLUSIVE_INDICATOR,
               PEDIATRIC_EXCLUSIVE_START_DATE,
               PEDIATRIC_EXCLUSIVE_END_DATE,
               CLOTTING_FACTOR_INDICATOR,
               CLOTTING_FACTOR_START_DATE,
               CLOTTING_FACTOR_END_DATE,
               PRIMARY_UOM,
               SECONDARY_UOM,
               SHELF_LIFE,
               SHELF_LIFE_TYPE,
               DUAL_PRICING_INDICATOR,
               ITEM_FAMILY_ID,
               ACQUIRED_AMP,
               ACQUIRED_BAMP,
               OBRA_BAMP,
               DRA,
               DOSES_PER_UNIT,
               DISCONTINUATION_DATE,
               LAST_LOT_EXPIRATION_DATE,
               DIVESTITURE_DATE,
               NON_FEDERAL_EXPIRATION_DATE,
               NDC9,
               NDC8,
               ITEM_CATEGORY,
               BASELINE_AMP,
			   ALT_BASELINE_AMP,
               BASE_CPI_PERIOD,
               BASE_CPI,
			   ALT_BASE_CPI,
               PACKAGE_SIZE_CODE,
               INTERNAL_NOTES,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'A'
        FROM   INSERTED
		---------------------------------Relationship Implementation  starts here------------------------
      IF Object_id('tempdb..##TEMP_GLOBAL_CCP') IS NOT NULL
        DROP TABLE ##TEMP_GLOBAL_CCP

      CREATE TABLE ##TEMP_GLOBAL_CCP
        (
           MARKET_TYPE_SID            INT,
           CONTRACT_HOLDER_SID        INT,
           CONTRACT_MASTER_SID        INT,
           COMPANY_MASTER_SID         INT,
           ITEM_MASTER_SID            INT,
           BRAND_MASTER_SID           INT NULL,
           GL_COMP_COMPANY_MASTER_SID INT NULL,
           THERAPEUTIC_CLASS_HLPR_SID INT NULL
        )

      INSERT INTO ##TEMP_GLOBAL_CCP
                  (ITEM_MASTER_SID,
                   GL_COMP_COMPANY_MASTER_SID,
                   THERAPEUTIC_CLASS_HLPR_SID,
                   BRAND_MASTER_SID)
      SELECT A.ITEM_MASTER_SID,
             CM.COMPANY_MASTER_SID AS GL_COMP_COMPANY_MASTER_SID,
             IM.THERAPEUTIC_CLASS  AS THERAPEUTIC_CLASS_HLPR_SID,
             IM.BRAND_MASTER_SID   AS BRAND_MASTER_SID
      FROM   INSERTED A
             JOIN ITEM_MASTER IM
               ON A.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
             LEFT JOIN GL_COST_CENTER_MASTER GM
                    ON IM.NDC8 = GM.NDC8
             LEFT JOIN COMPANY_MASTER CM
                    ON CM.COMPANY_ID = GM.COMPANY_CODE

      -------------------------Procedure Call ---------------------------------------
      -- IF @@ROWCOUNT > 0
        -- BEGIN
            -- BEGIN TRY
                -- BEGIN TRANSACTION

                -- EXEC Prc_product_hierarchy_dynamic_add --PRODUCT

                -- COMMIT
            -- END TRY

            -- BEGIN CATCH
                -- IF @@TRANCOUNT <> 0
                  -- ROLLBACK
            -- END CATCH
        -- END
  ---------------------------------Relationship Implementation  ends here------------------------

  END

GO

