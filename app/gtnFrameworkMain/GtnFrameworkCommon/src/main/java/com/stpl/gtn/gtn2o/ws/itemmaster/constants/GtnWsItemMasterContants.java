/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.itemmaster.constants;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsItemMasterContants {
	private GtnWsItemMasterContants() {
		/**
		 * empty constructor
		 */
	}

	public static final String GTN_WS_ITEM_MASTER_SERVICE = "/GtnWsItemMaster";
	public static final String GTN_WS_ITEM_MASTER_FETCH_INFORMATION_SERVICE = "/fetchItemMasterInformation";
	public static final String GTN_WS_ITEM_MASTER_DELETE_SERVICE = "/deleteItemMasterModel";
	public static final String GTN_WS_ITEM_MASTER_SAVE_SERVICE = "/saveService";
	public static final String GTN_WS_ITEM_MASTER_IM_IDENTIFIER_VALIDATION_SERVICE = "/imIdentifierValidation";
	public static final String GTN_WS_IM_BRAND_MASTER_FETCH_INFORMATION_SERVICE = "/fetchImBrandMasterInformation";
	public static final String GTN_WS_IM_COMPANY_INFO_FETCH_INFORMATION_SERVICE = "/fetchImCompanyInfoInformation";
	public static final String GTN_WS_ITEM_MASTER_ID_NAME_NDC8_VALIDATION_SERVICE = "/itemMasterIdNoNdc8Validation";
	public static final String GTN_WS_CP_DETAILS_INSERT_FOR_ITEMS_INSERT_SERVICE = "/insertCPDetailsForItemsInsertService";

	// identifier Tab edit List
	public static final String GTN_WS_ITEM_MASTER_IDENTIFIER_QUALIFIER_NAMEANDVALUE_VALIDATION_SERVICE = "/imIdentifierQualifierNameAndValueValidation";

	public static final String GTN_WS_ITEM_MASTER_INDENTIFIER_QUALIFER_SAVE_SERVICE = "/itemMasterItemIdentifierQulifierSave";

	// Pricing Tab Edit List
	public static final String GTN_WS_ITEM_MASTER_PRICING_QUALIFER_SAVE_SERVICE = "/itemMasterItemPricingQulifierSave";
	public static final String GTN_WS_ITEM_MASTER_PRICING_QUALIFIER_NAMEANDVALUE_VALIDATION_SERVICE = "/imPricingQualifierNameAndValueValidation";

	public static final String GTN_WS_ITEM_MASTER_INDENTIFIER_QUALIFER_DELETE_VALIDATION_SERVICE = "/imIdentifierQualifierDeleteValidation";

	public static final String GTN_WS_ITEM_INDENTIFIER_QUALIFIER_DELETE_SERVICE = "/deleteItemQualifier";

	public static final String GTN_WS_ITEM_MASTER_PRICING_QUALIFER_DELETE_VALIDATION_SERVICE = "/imPricingQualifierDeleteValidation";

	public static final String GTN_WS_ITEM_PRICING_QUALIFIER_DELETE_SERVICE = "/deletePricingQualifier";

	// Pricing
	public static final String GTN_WS_PRICING_TABLE_LOAD_SERVICE = "/fetchitemPricing";
	public static final String GTN_WS_PRICING_CREATE_TEMP_TABLE = "/createPricingTempTable";
	public static final String GTN_WS_PRICING_UPDATE_SERVICE = "/updatePricingData";
	public static final String GTN_WS_PRICING_INSERT_SERVICE = "/insertPricingData";
	public static final String GTN_WS_PRICING_DELETE_SERVICE = "/deletePricingData";
	public static final String GTN_WS_PRICING_DROP_TEMP_TABLE_SERVICE = "/dropTempTableService";
	public static final String GTN_WS_PRICING_VALIDATION_SERVICE = "/validatePricing";

	// Validation Msg
	public static final String GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_004 = "Item UOM is Mandatory";
	public static final String GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_005 = "Item Price is Mandatory";
	public static final String GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_006 = "Pricing status is Mandatory";
	public static final String GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_003 = "Start Date is Mandatory ";
	public static final String GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_010 = "Item Pricing combination already exists for this Item";

}
