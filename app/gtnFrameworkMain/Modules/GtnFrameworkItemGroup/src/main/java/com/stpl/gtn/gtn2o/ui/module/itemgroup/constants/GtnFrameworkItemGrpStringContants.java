/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.itemgroup.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkItemGrpStringContants {

	public static final String GTN_ITEM_GRP_VALIDATION_MSG = "Please select atleast one Item to save the Item Group";
	public static final String GTN_ITEM_GRP_VALIDATION_MSG_DUPLICATION_NAME = "Entered Item group Name already exists ";
	public static final String GTN_ITEM_GRP_VALIDATION_MSG_SAVE = "Information for the following Mandatory fields need to be provided: ";
	public static final String GTN_ITEM_GRP_VALIDATION_MSG_ERROR_HEADER = GtnFrameworkCommonStringConstants.ERROR;
	public static final String GTN_ITEM_GRP_VALIDATION_MSG_EDIT_ERROR_HEADER = "Edit Error";
	public static final String GTN_ITEM_GRP_VALIDATION_MSG_EDIT = "No Record has been selected.Please select a record to edit";
	public static final String GTN_ITEM_GRP_VALIDATION_MSG_VIEW = "No Record has been selected.Please select a record to view";
	public static final String GTN_ITEM_GRP_VALIDATION_MSG_COPY = "No Record has been selected.Please select a record to Copy";
	public static final String GTN_ITEM_GRP_VALIDATION_MSG_DELETE = "No Record has been selected.  Please select a Record and try again.";
	public static final String GTN_ITEM_GRP_ALERT_MSG = "No Item Group has been selected.Please select an Item Group and try again.";
	public static final String GTN_ITEM_GRP_CONFIRMATION_MSG_RESET_HEADER = "Reset Confirmation";
	public static final String GTN_ITEM_GRP_CONFIRMATION_MSG_RESET = "Are you sure you want to reset the values in the Search Criteria group box?";
	public static final String GTN_ITEM_GRP_CONFIRMATION_RESET_MSG = "Are you sure you want to reset the page to default/previous values?";
	public static final String GTN_ITEM_GRP_CONFIRMATION_MSG_SEARCH = "Search Criteria ";
	public static final String GTN_ITEM_GRP_CONFIRMATION_MSG_SEARCH_001 = "Please enter Search Criteria";
	public static final String GTN_ITEM_GRP_VALIDATION_SEARCH_ERROR = "Search Error";
	public static final String GTN_ITEM_GRP_VALIDATION_NO_SEARCH_FOUND = "No search criteria have been entered.  Please enter search criteria and try again.";

	public static final String GTN_ITEM_GRP_VALIDATION_ADD_MSG = "No Records Selected ";
	public static final String GTN_ITEM_GRP_VALIDATION_ADD_MSG_BODY = "No items were selected in the Results list view.  Please select at least one item and try again.";

	public static final String GTN_ITEM_GRP_VALIDATION_REMOVE_MSG_BODY = "No items were selected in the Selected Items list view.  Please select at least one item and try again.";

	public static final String GTN_ITEM_GRP_CONFIRMATION_MSG = "Are you sure you want to delete the record";
	public static final String GTN_ITEM_GRP_CONFIRMATION_HEADER = "Confirmation";
	public static final String GTN_ITEM_GRP_NO_RESULTS_FOUND_MSG = "No results could be found that match the entered search criteria";

        
        private static final String[] RESET_ID_ARRAY = { GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_CUSTOMER_TYPE,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_I_GRP_DESC,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_CUSTOMER_NO,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_BRAND,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_STRENGTH,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_THERAPEUTIC_CLASS,
				GtnFrameworkCommonConstants.I_GRP_INFORMATION_TAB_FORM };
        private static final Object[] ADD_SEARCH_DISABLE_FIELD = new String[] { GtnFrameworkCommonConstants.ITEM_GRPGTN_EDIT_BUTTON,
				GtnFrameworkCommonConstants.ITEM_GRP_GTN_COPY_BUTTON,
				GtnFrameworkCommonConstants.ITEM_GRP_GTN_DELETE_BUTTON };
        private static final Object[] AUDIT_SEARCH_DISABLE_FIELD = new String[] { GtnFrameworkCommonConstants.ITEM_GRPGTN_EDIT_BUTTON,
				GtnFrameworkCommonConstants.ITEM_GRP_GTN_COPY_BUTTON,
				GtnFrameworkCommonConstants.ITEM_GRP_GTN_DELETE_BUTTON };
        private static final Object[] RESET_VALUE_ARRAY = { GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null };
        public static final List<String> RESET_ID_ARRAYY = Collections.unmodifiableList(Arrays.asList(GtnFrameworkCommonConstants.ITEM_GROUP_NAME,
				GtnFrameworkCommonConstants.ITEM_GROUP_NO, GtnFrameworkCommonConstants.ITEM_GROUP_DESC,
				GtnFrameworkCommonConstants.I_GRP_INFO_COMPANY,
				GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE ));

    public static Object[] getResetValueArray() {
        return RESET_VALUE_ARRAY.clone();
    }
        

    public static Object[] getAuditSearchDisableField() {
        return AUDIT_SEARCH_DISABLE_FIELD.clone();
    }
        

    public static Object[] getAddSearchDisableField() {
        return ADD_SEARCH_DISABLE_FIELD.clone();
    }
        

    public static String[] getResetIdArray() {
        return RESET_ID_ARRAY.clone();
    }
        
	private GtnFrameworkItemGrpStringContants() {
	}
}
