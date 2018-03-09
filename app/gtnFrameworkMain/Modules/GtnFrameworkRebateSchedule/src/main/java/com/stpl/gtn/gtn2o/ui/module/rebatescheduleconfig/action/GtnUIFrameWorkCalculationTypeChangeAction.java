package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.GtnFrameworkRebateScheduleSetUpTabConfig;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkCalculationTypeChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private static final Logger LOGGER = LoggerFactory.getLogger(GtnUIFrameWorkCalculationTypeChangeAction.class);
	private static Class<?>[] tableColumnDataType;
	private static String[] tableHeader ;
	public static Class<?>[] getTableColumnDataType() {
		return tableColumnDataType.clone();
	}

	public static void setTableColumnDataType(Class<?>[] tableColumnDataType) {
		GtnUIFrameWorkCalculationTypeChangeAction.tableColumnDataType = tableColumnDataType.clone();
	}

	public static String[] getTableHeader() {
		return tableHeader.clone();
	}

	public static void setTableHeader(String[] tableHeader) {
		GtnUIFrameWorkCalculationTypeChangeAction.tableHeader = tableHeader.clone();
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		
		try{
			checkCopyMode(componentId);
		
		String calculationType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("calculationType1")
				.getCaptionFromComboBox();

		GtnUIFrameworkBaseComponent rebateSetupMassField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("rebateSetupTabMassField");
		Object mode = GtnUIFrameworkGlobalUI.getSessionProperty("mode");
		List<String> visibleColumnList = null;
		List<String> fieldFactoryColumnList = null;
		String checkRecordId = GtnFrameworkCommonConstants.CHECK_RECORD_ID;
		if (mode == GtnUIFrameworkModeType.EDIT || mode == GtnUIFrameworkModeType.ADD) {
			switch (calculationType) {
			case "Rebate Plan":
				makeRebatePlanColumns();
				getTableColumnDataType();
				getTableHeader();
				visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
						GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.RS_STATUS,
						GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1,
						GtnFrameworkRSConstants.RS_BUNDLE_NO, GtnFrameworkRSConstants.REBATE_PLAN_NO1, "rebatePlanName",
						GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME, GtnFrameworkRSConstants.NET_SALES_RULE_NAME,
						GtnFrameworkRSConstants.EVALUATION_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1,
						GtnFrameworkRSConstants.CALCULATION_RULE_NAME, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1,
						GtnFrameworkRSConstants.ATTACHED_DATE1);
				fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
						GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
						GtnFrameworkRSConstants.RS_END_DATE1, GtnFrameworkRSConstants.RS_BUNDLE_NO,
						GtnFrameworkRSConstants.REBATE_PLAN_NO1, GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME,
						GtnFrameworkRSConstants.NET_SALES_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_NAME,
						GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1, GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
						GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1);
				rebateSetupMassField.resetMassupdateCombobox(Arrays.asList(GtnFrameworkRSConstants.RS_STATUS1,
						GtnFrameworkRSConstants.START_DATE, GtnFrameworkRSConstants.END_DATE, "Bundle No",
						GtnFrameworkRSConstants.REBATE_PLAN_NO2, GtnFrameworkCommonConstants.NET_SALES_FORMULA,
						GtnFrameworkCommonConstants.NET_SALES_RULE, GtnFrameworkRSConstants.EVALUATION_RULE,
						GtnFrameworkRSConstants.CALCULATION_RULE1, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE,
						GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE2));

				break;
			case "Formula":

				makeFormulaTypeColumns();
				getTableColumnDataType();
				getTableHeader();
				visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
						GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.RS_STATUS,
						GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1, "formulaType",
						GtnFrameworkRSConstants.FORMULA_NO, GtnFrameworkRSConstants.FORMULA_NAME,
						GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME, GtnFrameworkRSConstants.NET_SALES_RULE_NAME,
						GtnFrameworkRSConstants.EVALUATION_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1,
						GtnFrameworkRSConstants.CALCULATION_RULE_NAME, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1,
						GtnFrameworkRSConstants.ATTACHED_DATE1);
				fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
						GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
						GtnFrameworkRSConstants.RS_END_DATE1, GtnFrameworkRSConstants.FORMULA_NO,
						GtnFrameworkRSConstants.FORMULA_NAME, GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME,
						GtnFrameworkRSConstants.NET_SALES_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_NAME,
						GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1, GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
						GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1);
				rebateSetupMassField.resetMassupdateCombobox(Arrays.asList(GtnFrameworkRSConstants.RS_STATUS1,
						GtnFrameworkRSConstants.START_DATE, GtnFrameworkRSConstants.END_DATE,
						GtnFrameworkRSConstants.FORMULA_NO2, GtnFrameworkCommonConstants.NET_SALES_FORMULA,
						GtnFrameworkCommonConstants.NET_SALES_RULE, GtnFrameworkRSConstants.EVALUATION_RULE,
						GtnFrameworkRSConstants.CALCULATION_RULE1, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE,
						GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE2));
				break;
			case "Deduction Calendar":
				makeDeductionCalendarColumns();
				getTableColumnDataType();
				getTableHeader();
				visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
						GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.RS_STATUS,
						GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1, GtnFrameworkRSConstants.DEDUCTION_NAME,
						GtnFrameworkRSConstants.DEDUCTION_NO, GtnFrameworkRSConstants.EVALUATION_RULE_NAME,
						GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1, GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
						GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1, GtnFrameworkRSConstants.ATTACHED_DATE1);
				fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
						GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
						GtnFrameworkRSConstants.RS_END_DATE1, GtnFrameworkRSConstants.DEDUCTION_NO,GtnFrameworkRSConstants.DEDUCTION_NAME,
						GtnFrameworkRSConstants.EVALUATION_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1,
						GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
						GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1);
				rebateSetupMassField.resetMassupdateCombobox(Arrays.asList(GtnFrameworkRSConstants.RS_STATUS1,
						GtnFrameworkRSConstants.START_DATE, GtnFrameworkRSConstants.END_DATE,
						GtnFrameworkRSConstants.DEDUCTION_CALENDAR_NO, GtnFrameworkRSConstants.EVALUATION_RULE,
						GtnFrameworkRSConstants.CALCULATION_RULE1, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE,
						GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE2));

				break;

			default:

				makeDefaultColumns();
				getTableColumnDataType();
				getTableHeader();
				visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
						GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.RS_STATUS,
						GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1);

				fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
						GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
						GtnFrameworkRSConstants.RS_END_DATE1);

				rebateSetupMassField.resetMassupdateCombobox(Arrays.asList(GtnFrameworkRSConstants.RS_STATUS1,
						GtnFrameworkRSConstants.START_DATE, GtnFrameworkRSConstants.END_DATE));
			}
		}
		if (mode == GtnUIFrameworkModeType.VIEW) {

			switch (calculationType) {
			case "Rebate Plan":

				makeRebatePlanViewColumns();
				getTableColumnDataType();
				getTableHeader();
				visibleColumnList = Arrays.asList(GtnFrameworkCommonConstants.ITEM_NO,
						GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.DESCRIPTION,
						GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1,
						GtnFrameworkRSConstants.RS_BUNDLE_NO, GtnFrameworkRSConstants.REBATE_PLAN_NO1, "rebatePlanName",
						GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME, GtnFrameworkRSConstants.NET_SALES_RULE_NAME,
						GtnFrameworkRSConstants.EVALUATION_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1,
						GtnFrameworkRSConstants.CALCULATION_RULE_NAME, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1,
						GtnFrameworkRSConstants.ATTACHED_DATE1);
				fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
						GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
						GtnFrameworkRSConstants.RS_END_DATE1, GtnFrameworkRSConstants.RS_BUNDLE_NO,
						GtnFrameworkRSConstants.REBATE_PLAN_NO1, GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME,
						GtnFrameworkRSConstants.NET_SALES_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_NAME,
						GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1, GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
						GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1);

				break;
			case "Formula":

				makeFormulaTypeViewColumns();
				getTableColumnDataType();
				getTableHeader();
				visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
						GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.DESCRIPTION,
						GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1, "formulaType",
						GtnFrameworkRSConstants.FORMULA_NO, GtnFrameworkRSConstants.FORMULA_NAME,
						GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME, GtnFrameworkRSConstants.NET_SALES_RULE_NAME,
						GtnFrameworkRSConstants.EVALUATION_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1,
						GtnFrameworkRSConstants.CALCULATION_RULE_NAME, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1,
						GtnFrameworkRSConstants.ATTACHED_DATE1);
				fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
						GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
						GtnFrameworkRSConstants.RS_END_DATE1, GtnFrameworkRSConstants.FORMULA_NO,
						GtnFrameworkRSConstants.FORMULA_NAME, GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME,
						GtnFrameworkRSConstants.NET_SALES_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_NAME,
						GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1, GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
						GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1);
				break;
			case "Deduction Calendar":
				makeDeductionCalendarViewColumns();
				getTableColumnDataType();
				getTableHeader();
				visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
						GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.DESCRIPTION,
						GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1, GtnFrameworkRSConstants.DEDUCTION_NAME,
						GtnFrameworkRSConstants.DEDUCTION_NO, GtnFrameworkRSConstants.EVALUATION_RULE_NAME,
						GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1, GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
						GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1, GtnFrameworkRSConstants.ATTACHED_DATE1);
				fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
						GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
						GtnFrameworkRSConstants.RS_END_DATE1, GtnFrameworkRSConstants.DEDUCTION_NO,
						GtnFrameworkRSConstants.EVALUATION_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1,
						GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
						GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1);
				break;
			default:
				makeDefaultViewColumns();
				getTableColumnDataType();
				getTableHeader();
				visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
						GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.DESCRIPTION,
						GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1);

				fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
						GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
						GtnFrameworkRSConstants.RS_END_DATE1);
			}

		}
		List<Integer> dateColumn = new ArrayList<>();
		Map<String, Class<?>> dataType = new HashMap<>(tableColumnDataType.length-2);
		for (int i = 0; i < tableColumnDataType.length; i++) {
			if (Date.class.equals(tableColumnDataType[i])) {
				dateColumn.add(i);
			}
			dataType.put(visibleColumnList.get(i), tableColumnDataType[i]);
		}

		GtnUIFrameworkComponentConfig componentConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE)
				.getComponentConfig();
		GtnUIFrameworkPagedTableConfig rebateSetupPagedTableConfig = componentConfig.getGtnPagedTableConfig();

		rebateSetupPagedTableConfig.setTableColumnDataType(tableColumnDataType);

		rebateSetupPagedTableConfig.setTableColumnMappingId(visibleColumnList.toArray());

		rebateSetupPagedTableConfig.setTableVisibleHeader(tableHeader);
		rebateSetupPagedTableConfig.setEditableColumnList(fieldFactoryColumnList);
		rebateSetupPagedTableConfig.setEditableField(GtnFrameworkRebateScheduleSetUpTabConfig
				.createTableFieldFactoryComponents(rebateSetupPagedTableConfig.getEditableColumnList()));

		if (mode == GtnUIFrameworkModeType.VIEW) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE)
					.getExtFilterTable().clearFilters();
		} else {
			rebateSetupPagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		}
		componentConfig.setGtnPagedTableConfig(rebateSetupPagedTableConfig);
		GtnUIFrameworkGlobalUI.addChildComponent("rebateSetupTabResultLayout", Arrays.asList(componentConfig));
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE);
		GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();

		List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = new ArrayList<>();

		
		GtnWebServiceSearchCriteria userIdCriteria = new GtnWebServiceSearchCriteria();
		GtnWebServiceSearchCriteria sessionIdCriteria = new GtnWebServiceSearchCriteria();
		additioanlSearchCriteriaList.add(userIdCriteria);
		additioanlSearchCriteriaList.add(sessionIdCriteria);

		userIdCriteria.setFieldId("userId");
		userIdCriteria.setExpression("EQUALS");
		userIdCriteria.setFilterValue1(GtnUIFrameworkGlobalUI.getCurrentUser());

		sessionIdCriteria.setFieldId("sessionId");
		sessionIdCriteria.setExpression("EQUALS");
		sessionIdCriteria.setFilterValue1(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());

		tableLogic.setAdditioanlSearchCriteriaList(additioanlSearchCriteriaList);
		tableLogic.startSearchProcess(new ArrayList<String>(), true);
	    getValueFromList(dateColumn);
	    getKeyFromMap(dataType);
	}
		catch(Exception e)
		{
			LOGGER.error(e.getMessage());
		}
		
	}

	private static void  makeDefaultViewColumns() {

		 setTableColumnDataType( new Class<?>[] { Boolean.class, String.class, String.class, String.class,
				Date.class, Date.class });
	        setTableHeader(new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
					GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE });
	}

	private static void makeDeductionCalendarViewColumns() {

		 setTableColumnDataType(new Class<?>[] { Boolean.class, String.class, String.class, String.class,
				Date.class, Date.class, String.class, String.class, String.class, String.class, String.class,
				String.class, Date.class });
	        setTableHeader(new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
					GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE,
					GtnFrameworkRSConstants.DEDUCTION_CALENDAR_NO, "Deduction Calendar Name",
					GtnFrameworkRSConstants.EVALUATION_RULE, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE,
					GtnFrameworkRSConstants.CALCULATION_RULE1, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE,
					GtnFrameworkRSConstants.ATTACHED_DATE });
	}

	private static void makeFormulaTypeViewColumns() {
		 setTableColumnDataType(new Class<?>[] { Boolean.class, String.class, String.class, String.class,
				Date.class, Date.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, Date.class });
	        setTableHeader(new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
					GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE, "Formula Type",
					GtnFrameworkRSConstants.FORMULA_NO2, "Formula Name",
					GtnFrameworkCommonConstants.NET_SALES_FORMULA, GtnFrameworkCommonConstants.NET_SALES_RULE,
					GtnFrameworkRSConstants.EVALUATION_RULE, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE,
					GtnFrameworkRSConstants.CALCULATION_RULE1, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE,
					GtnFrameworkRSConstants.ATTACHED_DATE });
	}

	private static void makeRebatePlanViewColumns() {
		 setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class, Date.class, Date.class,
				String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, Date.class });
	        setTableHeader(new String[] { GtnFrameworkCommonConstants.ITEM_NO_HEADER,
					GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE, "Bundle No",
					GtnFrameworkRSConstants.REBATE_PLAN_NO2, "Rebate Plan Name",
					GtnFrameworkCommonConstants.NET_SALES_FORMULA, GtnFrameworkCommonConstants.NET_SALES_RULE,
					GtnFrameworkRSConstants.EVALUATION_RULE, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE,
					GtnFrameworkRSConstants.CALCULATION_RULE1, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE,
					GtnFrameworkRSConstants.ATTACHED_DATE });
	}

	private static void makeDefaultColumns() {
		 setTableColumnDataType(new Class<?>[] { Boolean.class, String.class, String.class, Integer.class,
				Date.class, Date.class });
	        setTableHeader(new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
					GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE });
	}

	private static void makeDeductionCalendarColumns() {
		 setTableColumnDataType(new Class<?>[] { Boolean.class, String.class, String.class, Integer.class,
				Date.class, Date.class, String.class, String.class, String.class, String.class, String.class,
				String.class, Date.class });
	        setTableHeader(new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
					GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE,
					GtnFrameworkRSConstants.DEDUCTION_CALENDAR_NO, "Deduction Calendar Name",
					GtnFrameworkRSConstants.EVALUATION_RULE, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE,
					GtnFrameworkRSConstants.CALCULATION_RULE1, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE,
					GtnFrameworkRSConstants.ATTACHED_DATE });
	}

	private static void makeFormulaTypeColumns() {
		 setTableColumnDataType(new Class<?>[] { Boolean.class, String.class, String.class, Integer.class,
				Date.class, Date.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, Date.class });
	        setTableHeader(new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
					GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE, "Formula Type",
					GtnFrameworkRSConstants.FORMULA_NO2, "Formula Name",
					GtnFrameworkCommonConstants.NET_SALES_FORMULA, GtnFrameworkCommonConstants.NET_SALES_RULE,
					GtnFrameworkRSConstants.EVALUATION_RULE, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE,
					GtnFrameworkRSConstants.CALCULATION_RULE1, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE,
					GtnFrameworkRSConstants.ATTACHED_DATE });
	}

	private static void makeRebatePlanColumns() {
        setTableColumnDataType( new Class<?>[] { Boolean.class, String.class, String.class, Integer.class,
			Date.class, Date.class, String.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class, Date.class });
		setTableHeader(new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
				GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
				GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE,
				GtnFrameworkRSConstants.BUNDLE_NO, GtnFrameworkRSConstants.REBATE_PLAN_NO2, "Rebate Plan Name",
				GtnFrameworkCommonConstants.NET_SALES_FORMULA, GtnFrameworkCommonConstants.NET_SALES_RULE,
				GtnFrameworkRSConstants.EVALUATION_RULE, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE,
				GtnFrameworkRSConstants.CALCULATION_RULE1, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE,
				GtnFrameworkRSConstants.ATTACHED_DATE });
	}

	private void getKeyFromMap(Map<String, Class<?>> dataType) {
		Entry<String, Class<?>> entry = dataType.entrySet().iterator().next();
	    entry.getKey();
	}

	private void getValueFromList(List<Integer> dateColumn) {
		for(int i=0;i<dateColumn.size();i++)
	    {
	    	dateColumn.get(i);
	    }
	}
	
	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		GtnUIFrameworkPagedTableCustomFilterConfig rsCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		rsCustomFilterConfig.setPropertId(GtnFrameworkRSConstants.RS_STATUS);
		rsCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig rsCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		rsCustomFilterComponentConfig.setComponentId("customFilterComboBox");
		rsCustomFilterComponentConfig.setComponentName("customFilterComboBox");
		rsCustomFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
		rsCustomFilterComponentConfig.getGtnComboboxConfig().setComboBoxType("STATUS");
		rsCustomFilterComponentConfig.getGtnComboboxConfig()
				.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
		rsCustomFilterComponentConfig.getGtnComboboxConfig()
				.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rsCustomFilterConfig.setGtnComponentConfig(rsCustomFilterComponentConfig);
		customFilterConfigMap.put(rsCustomFilterConfig.getPropertId(), rsCustomFilterConfig);
		return customFilterConfigMap;
	}
	
	private void checkCopyMode(String componentId) {
		if (componentId.toLowerCase(Locale.ENGLISH).contains(("COPY").toLowerCase(Locale.ENGLISH))) {
			GtnUIFrameworkGlobalUI.addSessionProperty("mode", "EDIT");
		}
	}
}

	