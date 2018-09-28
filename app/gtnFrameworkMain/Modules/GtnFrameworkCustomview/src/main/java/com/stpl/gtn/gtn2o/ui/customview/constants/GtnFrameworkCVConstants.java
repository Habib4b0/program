/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnFrameworkCVConstants {

	public static final String CUSTOMER_ADD_BUTTON_MOVE_LEFT_BUTTON_LAYOUT = "customerAddButtonMoveLeftButtonLayout";
	public static final String PRODUCT_ADD_BUTTON_MOVE_RIGHT_BUTTON_LAYOUT = "productAddButtonMoveRightButtonLayout";
	public static final String PRODUCT_ADD_BUTTON_MOVE_LEFT_BUTTON_LAYOUT = "productAddButtonMoveLeftButtonLayout";

	private GtnFrameworkCVConstants() {
		/**
		 * empty constructor
		 */
	}

	public static final String CUSTOM_VIEW_LANDING_SCREEN = "CVSearch";
	public static final String CUSTOM_VIEW_OPTION_SALES = "Sales";
	public static final String CUSTOM_VIEW_OPTION_DISCOUNT = "Discount";
	public static final String CUSTOM_VIEW_SEARCH_BUTTONLAYOUT = "customViewSearchButtonlayout";
	public static final String GTN_CUSTOM_VIEW_SEARCH_CRITERIA_VALIDATION = "Please enter Search Criteria";
	public static final String GTN_CUSTOM_VIEW_SEARCH_CRITERIA_RESET_VALIDATION = "Are you sure you want to reset the page to default/previous values?";
	public static final String CUSTOM_VIEW_SEARCH_RESULT_TABLE = "searchResultTable";
	public static final String ACTION_BUTTON_LAYOUT = "actionButtonlayout";
	public static final String CUSTOM_VIEW_INFORMATION_LAYOUT = "customViewInformationLayout";
	public static final String CUSTOM_VIEW_TREE_LAYOUT = "customViewTreeLayout";
	public static final String SAVE_BUTTON_LAYOUT = "customViewSaveButtonlayout";
	public static final String GTN_CUSTOM_VIEW_CONFIRMATION_MSG = "Confirmation";
	public static final String GTN_CUSTOM_VIEW_MSG_BACK = "Any unsaved information will not be saved. Do you want to proceed?";
	public static final String CUSTOMER_LEVEL = "customerLevel";
	public static final String CUSTOM_TREE_LEVEL = "customTreeLevel";
	public static final String PRODUCT_LEVEL = "productLevel";
	public static final String DEDUCTION_LEVEL = "deductionLevel";
	public static final String CUSTOM_VIEW_DESCRIPTION = "customerViewDescription";
	public static final String CUSTOM_VIEW_SCREEN_NAME = "customerViewScreenName";
	public static final String CUSTOM_VIEW_TREE = "customerViewTree";
	public static final String GTN_CUSTOM_VIEW_MANDATORY_FIELDS_VALIDATION = "Information for Mandatory fields need to be provided on Custom View information section";
	public static final String CV_LANDING_MAIN_PANEL = "mainPanel";
	public static final String CV_LANDING_SCREEN_PANEL = "landingScreenPanel";
	public static final String CUSTOM_VIEW_NAME_LAYOUT = "treeViewNameLayout";
	public static final String CUSTOM_VIEW_DESCRIPTION_LAYOUT = "customViewDescriptionLayout";
	public static final String CUSTOMER_RELATION_LAYOUT = "customerRelationLayout";
	public static final String PRODUCT_RELATION_LAYOUT = "productRelationLayout";
	public static final String CUSTOM_VIEW_TYPE_LAYOUT = "customViewTypeLayout";
	public static final String SCREEN_NAME_LAYOUT = "screenNameLayout";
	public static final String GTN_SEARCH_LAYOUT = "gtnSearch01Layout";
	public static final String GTN_RESET_LAYOUT = "gtnReset01Layout";
	public static final String CUSTOM_SEARCH_RESULT_PANEL = "CustomViewSearchResultPanel";
	public static final String CUSTOM_SEARCH_RESULT_LAYOUT = "CustomViewSearchResultlayout";
	public static final String GTN_ADD_BUTTON_LAYOUT = "gtnAddButtonLayout";
	public static final String GTN_EDIT_BUTTON_LAYOUT = "gtnEditButtonLayout";
	public static final String GTN_VIEW_BUTTON_LAYOUT = "gtnViewButtonLayout";
	public static final String CUSTOM_VIEW_INFO_PANEL = "customViewInformationPanel";
	public static final String CUSTOM_VIEW_INFO_VERTICAL_LAYOUT = "customViewInformationVerticallayout";
	public static final String CUSTOM_VIEW_TYPE_COMP_LAYOUT = "customViewTypeCompLayout";
	public static final String DEDUCTION_HIER_LAYOUT = "deductionHierarchyLayout";
	public static final String DEDUCTION_MOVE_LAYOUT = "gtnDeductionMoveLayout";
	public static final String CV_ADD_SAVE_BTN_LAYOUT = "customViewAddSaveButtonLayout";
	public static final String CV_ADD_BACK_BTN_LAYOUT = "customViewAddBackButtonLayout";
	public static final String CV_TREE_PANEL = "customViewTreePanel";
	public static final String CV_TREE_VERTICAL_LAYOUT = "customViewTreeVerticallayout";
	public static final String CV_TREE_CONSTRUCT_PANEL = "customViewTreeConstructPanel";
	public static final String CUSTOMER_TREE_LAYOUT = "customerTreeLayout";
	public static final String V002_CUSTOMER_TREE_LAYOUT = "V002_customTreeLayout";
	public static final String DEDUCTION_ADD_BUTTON_MOVE_RIGHT_BUTTON_LAYOUT = "deductionAddButtonMoveRightButtonLayout";
	public static final String DEDUCTION_ADD_BUTTON_MOVE_LEFT_BUTTON_LAYOUT = "deductionAddButtonMoveLeftButtonLayout";
	public static final String LEVEL_NAME = "levelName";
	public static final String CUSTOM_TREE_LAYOUT = "customTreeLayout";
	public static final String LEVEL_NAME_HEADER = "Level Name";
	public static final String LEVEL_NO_COLUMN = "levelNo";
	public static final String TREE_LEVEL_NO_COLUMN = "treeLevelNo";
	public static final String HIERARCHY_INDICATOR_COLUMN = "hierarchyIndicator";
	public static final String HIERARCHY_LEVEL_DEF_SID = "hierarchyLevelDefinitionSid";
	public static final String GTN_CUSTOMER_MOVE_LAYOUT = "gtnCustomerMoveLayout";
	public static final String GTN_PRODUCT_MOVE_LAYOUT = "gtnProductMoveLayout";
	public static final String PRODUCT_TREE_LAYOUT = "productTreeLayout";
	public static final String NO_LEVEL_SELECTED = "No Level Selected";
	public static final String CUSTOMER_ADD_BTN_MOVE_RIGHT_BTN_LAYOUT = "customerAddButtonMoveRightButtonLayout";
	private static final String[] CV_CUSTOM_PROPERTY_IDS = { GtnFrameworkCommonConstants.CUSTOM_VIEW_SCREEN_NAME,
			GtnFrameworkCommonConstants.CUTOMER_RELATION, GtnFrameworkCommonConstants.PRODUCT_RELATION, "createdBy",
			"modifiedBy" };
	private static final String[] CV_LIST_NAME_ARRAY = { "CV_MODULE_TYPE", GtnFrameworkCommonConstants.CUTOMER_RELATION,
			GtnFrameworkCommonConstants.PRODUCT_RELATION, "USERS", "USERS" };

	public static String[] getCvCustomPropertyIds() {
		return CV_CUSTOM_PROPERTY_IDS.clone();
	}

	public static String[] getCvListNameArrays() {
		return CV_LIST_NAME_ARRAY.clone();
	}
        public static List<String> CV_TREENODE_LIST=Collections.unmodifiableList(Arrays.asList("Schedule Category","Schedule Type","Program Type","UDC 1","UDC 2","UDC 3","UDC 4","UDC 5","UDC 6","Schedule ID"));
}
