package com.stpl.gtn.gtn2o.ui.module.ifp.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkIfpStringContants {

	private GtnFrameworkIfpStringContants() {
	}

	public static final String GTN_IFP_VALIDATION_MSG_ATLEAST_ONE_RECORD = "Add at least one item in Items tab for IFP";
	public static final String GTN_IFP_VALIDATION_MSG_ATLEAST_ONE_RECORD_TO_SAVE = "Check Aleast one Item to save in Items tab";
	public static final String GTN_IFP_VALIDATION_MSG_START_DATE_NULL = "Start Date required for selected items in Items tab";
	public static final String GTN_IFP_VALIDATION_MSG_START_DATE_NULL_001 = "Start Date required for Item ID ";
	public static final String GTN_IFP_VALIDATION_MSG_STATUS_NULL = "Status required for selected items in Items tab";
	public static final String GTN_IFP_VALIDATION_MSG_STATUS_NULL_001 = "Status required for Item ID ";
	public static final String GTN_IFP_VALIDATION_MSG_STARTDATE_GREATER = "IFP End date Should be Greater than IFP Start Date ";
	public static final String GTN_IFP_VALIDATION_MSG_MADATORY_FIELDS = "Information for the following Mandatory fields need to be provided: ";
	public static final String GTN_IFP_VALIDATION_MSG_IFP_IDANDNO = "Item Family Plan ID & Item Family Plan NO already exists.";
	public static final String GTN_IFP_VALIDATION_MSG_IFP_ID = "Item Family Plan ID already exists.";
	public static final String GTN_IFP_VALIDATION_MSG_IFP_NO = "Item Family Plan NO already exists.";
	public static final String GTN_IFP_CONFIRMATION_MSG_HEADER = "Confirmation";
	public static final String GTN_IFP_CONFIRMATION_MSG_BACK = "Any unsaved information will not be saved. Do you want to proceed?";
	public static final String GTN_IFP_CONFIRMATION_MSG_RESET_HEADER = "Reset Confirmation";
	public static final String GTN_IFP_CONFIRMATION_MSG_RESET = "Are you sure you want to reset the values in the Search Criteria group box?";
	public static final String GTN_IFP_CONFIRMATION_MSG_RESET_001 = "Are you sure you want to reset the page to default/previous values?";
	public static final String GTN_IFP_CONFIRMATION_MSG_DELETE = "Are you sure you want to delete the record";
	public static final String GTN_IFP_VALIDATION_MSG_EDIT_HEADER = "Edit Error";
	public static final String GTN_IFP_VALIDATION_MSG_ERROR_HEADER = GtnFrameworkCommonStringConstants.ERROR;
	public static final String GTN_IFP_VALIDATION_MSG_EDIT = "Please select a record to edit";
	public static final String GTN_IFP_VALIDATION_MSG_VIEW = "Please select a record to view";
	public static final String GTN_IFP_VALIDATION_MSG_DELETE = "Item Family Plan cannot be deleted, associated as parent to another Item Family Plan";
	public static final String GTN_IFP_CONFIRMATION_MSG_EDIT = "Are you sure you want to Edit the Item Family Plan?";
	public static final String GTN_IFP_CONFIRMATION_MSG_VIEW = "Are you sure you want to View the Item Family Plan?";
	public static final String GTN_IFP_VALIDATION_MSG_POPULATE_001 = "Select Field";
	public static final String GTN_IFP_VALIDATION_MSG_POPULATE_002 = "Select Status";
	public static final String GTN_IFP_VALIDATION_MSG_POPULATE_003 = "Select Start Date";
	public static final String GTN_IFP_VALIDATION_MSG_POPULATE_004 = "Select End Date";
	public static final String GTN_IFP_VALIDATION_MSG_POPULATE_005 = "Select a row to populate";
	public static final String IFP_DATE_EQUAL_VALIDATION = "IFP Start date and IFP End date should not be equal";
	public static final String IFP_DATE_LESS_THAN_VALIDATION = "IFP End date should be greater than IFP Start date";
	private static final List<String> DATEFIELD_PROPERTIES_LIST = Arrays.asList(
			GtnFrameworkCommonConstants.ITEM_FAMILY_PLAN_START_DATE,
			GtnFrameworkCommonConstants.ITEM_FAMILY_PLAN_END_DATE);
	private static final String[] IFP_VISIBLE_HEADER_FOR_VIEW = new String[] { "Item No", "Item Name", "Item Desc",
			"IFP Status", "IFP Start Date", "IFP End Date", "Item Status", "Form", "Strength", "Therapeutic Class",
			"Brand", "Attached Date", "Modified Date", "Modified By", "Created Date", "Created By" };

	private static final Object[] IFP_VISIBLE_COLUMN_FOR_VIEW = new Object[] { "itemNo", "itemName", "itemDesc",
			"itemFamilyPlanStatusValue", GtnFrameworkCommonConstants.ITEM_FAMILY_PLAN_START_DATE,
			GtnFrameworkCommonConstants.ITEM_FAMILY_PLAN_END_DATE, "itemStatusValue", "from", "strength",
			"therapeuticClass", "brand", "ifpAttachedDate", "modifiedDate", "modifiedBy", "createdDate", "createdBy" };

	public static final List<String> IFP_VISIBLE_HEADER = Collections.unmodifiableList(Arrays.asList(
			GtnFrameworkCommonStringConstants.STRING_EMPTY, "Item No", "Item Name", "Item Desc", "IFP Status",
			"IFP Start Date", "IFP End Date", "Item Status", "Form", "Strength", "Therapeutic Class", "Brand",
			"Attached Date", "Modified Date", "Modified By", "Created Date", "Created By"));

	public static final List<?> IFP_VISIBLE_COLUMN = Collections.unmodifiableList(Arrays.asList("checkRecordId",
			"itemNo", "itemName", "itemDesc", "itemFamilyPlanStatus",
			GtnFrameworkCommonConstants.ITEM_FAMILY_PLAN_START_DATE,
			GtnFrameworkCommonConstants.ITEM_FAMILY_PLAN_END_DATE, "itemStatusValue", "from", "strength",
			"therapeuticClass", "brand", "ifpAttachedDate", "modifiedDate", "modifiedBy", "createdDate", "createdBy"));
	public static final String CHECK_RECORD_ID = "checkRecordId";
	public static final List<String> IFP_FIELDS = Collections.unmodifiableList(Arrays.asList("ifpInfoifpId",
			"ifpInfoifpNo", "ifpInfoifpName", "ifpInformationTabIFPId", "ifpInformationTabIFPNo",
			"ifpInformationTabIFPName", "ifpInformationTabIFPStatus", "ifpInformationIFPStartDate",
			"ifpInformationIFPEndDate", "ifpInformationTabIFPType", "ifpInformationTabIFPCategory",
			"ifpInformationTabIFPDesignation", "ifpInformationTabParentIFPId", "ifpInformationTabParentIFPName",
			"ifpInformationCreatedDate", "ifpInformationModifiedDate", "ifpInformationTabModifiedBy"));
	public static final List<Object> DISABLED_IFP_FIELDS = Collections.unmodifiableList(Arrays.asList(
			new Object[] { "ifpInformationTabCreatedBy", "ifpInformationCreatedDate", "ifpInformationTabModifiedBy",
					"ifpInformationModifiedDate", "ifpInformationTabParentIFPName", "ifpInformationTabParentIFPId" }));

	public static List<String> getDateFieldPropertiesList() {
		return Collections.unmodifiableList(DATEFIELD_PROPERTIES_LIST);
	}

	public static String[] getIfpVisibleHeaderForView() {
		return IFP_VISIBLE_HEADER_FOR_VIEW.clone();
	}

	public static Object[] geIfpVisibleColumnForView() {
		return IFP_VISIBLE_COLUMN_FOR_VIEW.clone();
	}
}
