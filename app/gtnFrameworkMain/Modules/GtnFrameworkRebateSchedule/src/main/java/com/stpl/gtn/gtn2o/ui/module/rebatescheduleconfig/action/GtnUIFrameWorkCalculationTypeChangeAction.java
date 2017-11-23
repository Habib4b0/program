package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkCalculationTypeChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String calculationType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("calculationType1")
				.getCaptionFromComboBox();

		GtnUIFrameworkBaseComponent rebateSetupMassField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("rebateSetupTabMassField");

		Class<?>[] tableColumnDataType = null;
		String[] tableHeader = null;
		List<Object> visibleColumnList = new ArrayList<>();
		List<String> fieldFactoryColumnList = null;
		boolean isEditable = true;
		Object checkRecordId = GtnFrameworkCommonConstants.CHECK_RECORD_ID;
		if ("null".equals(calculationType) || "".equals(calculationType) || calculationType.equals("-Select One-")) {
			isEditable = isEditMode();
			if (!isEditable) {
				tableColumnDataType = new Class<?>[] { Boolean.class, String.class, String.class, Integer.class,
						Date.class, Date.class };

				tableHeader = new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
						GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
						GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE };

				visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
						GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.RS_STATUS,
						GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1);
				fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
						GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
						GtnFrameworkRSConstants.RS_END_DATE1);

				rebateSetupMassField.resetMassupdateCombobox(Arrays.asList(GtnFrameworkRSConstants.RS_STATUS1,
						GtnFrameworkRSConstants.START_DATE, GtnFrameworkRSConstants.END_DATE));
			} else {
				tableColumnDataType = new Class<?>[] { Boolean.class, String.class, String.class, String.class,
						Date.class, Date.class };

				tableHeader = new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
						GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
						GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE };

				visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
						GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkCommonConstants.RS_DESCRIPTION,
						GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1);
			}

		} else if (calculationType.equals("Rebate Plan")) {
			tableColumnDataType = new Class<?>[] { Boolean.class, String.class, String.class, Integer.class, Date.class,
					Date.class, String.class, String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class, Date.class };

			tableHeader = new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
					GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE, "Bundle No",
					"Rebate Plan No", "Rebate Plan Name", GtnFrameworkCommonConstants.NET_SALES_FORMULA,
					GtnFrameworkCommonConstants.NET_SALES_RULE, GtnFrameworkRSConstants.EVALUATION_RULE,
					GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE, GtnFrameworkRSConstants.CALCULATION_RULE1,
					GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE, GtnFrameworkRSConstants.ATTACHED_DATE };

			visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
					GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.RS_STATUS,
					GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1, "rsBundleNo",
					"rebatePlanNo", "rebatePlanName", GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME,
					GtnFrameworkRSConstants.NET_SALES_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_NAME,
					GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1, GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
					GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1, GtnFrameworkRSConstants.ATTACHED_DATE1);
			fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
					GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
					GtnFrameworkRSConstants.RS_END_DATE1, "rsBundleNo", "rebatePlanNo",
					GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME, GtnFrameworkRSConstants.NET_SALES_RULE_NAME,
					GtnFrameworkRSConstants.EVALUATION_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1,
					GtnFrameworkRSConstants.CALCULATION_RULE_NAME, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1);
			rebateSetupMassField.resetMassupdateCombobox(Arrays.asList(GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.START_DATE, GtnFrameworkRSConstants.END_DATE, "Bundle No", "Rebate Plan No",
					GtnFrameworkCommonConstants.NET_SALES_FORMULA, GtnFrameworkCommonConstants.NET_SALES_RULE,
					GtnFrameworkRSConstants.EVALUATION_RULE, GtnFrameworkRSConstants.CALCULATION_RULE1,
					GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE2));
		} else if (calculationType.equals("Formula")) {

			tableColumnDataType = new Class<?>[] { Boolean.class, String.class, String.class, Integer.class, Date.class,
					Date.class, String.class, String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class, Date.class };

			tableHeader = new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
					GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE, "Formula Type",
					"Formula No", "Formula Name", GtnFrameworkCommonConstants.NET_SALES_FORMULA,
					GtnFrameworkCommonConstants.NET_SALES_RULE, GtnFrameworkRSConstants.EVALUATION_RULE,
					GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE, GtnFrameworkRSConstants.CALCULATION_RULE1,
					GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE, GtnFrameworkRSConstants.ATTACHED_DATE };

			visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
					GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.RS_STATUS,
					GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1, "formulaType",
					"formulaNo", "formulaName", GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME,
					GtnFrameworkRSConstants.NET_SALES_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_NAME,
					GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1, GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
					GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1, GtnFrameworkRSConstants.ATTACHED_DATE1);
			fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
					GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
					GtnFrameworkRSConstants.RS_END_DATE1, "formulaNo", "formulaName",
					GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME, GtnFrameworkRSConstants.NET_SALES_RULE_NAME,
					GtnFrameworkRSConstants.EVALUATION_RULE_NAME, GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1,
					GtnFrameworkRSConstants.CALCULATION_RULE_NAME, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1);
			rebateSetupMassField.resetMassupdateCombobox(Arrays.asList(GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.START_DATE, GtnFrameworkRSConstants.END_DATE, "Formula No",
					GtnFrameworkCommonConstants.NET_SALES_FORMULA, GtnFrameworkCommonConstants.NET_SALES_RULE,
					GtnFrameworkRSConstants.EVALUATION_RULE, GtnFrameworkRSConstants.CALCULATION_RULE1,
					GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE2));
		} else if (calculationType.equals("Deduction Calendar")) {
			tableColumnDataType = new Class<?>[] { Boolean.class, String.class, String.class, Integer.class, Date.class,
					Date.class, String.class, String.class, String.class, String.class, String.class, String.class,
					Date.class };

			tableHeader = new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
					GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE,
					"Deduction Calendar No", "Deduction Calendar Name", GtnFrameworkRSConstants.EVALUATION_RULE,
					GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE, GtnFrameworkRSConstants.CALCULATION_RULE1,
					GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE, GtnFrameworkRSConstants.ATTACHED_DATE };

			visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
					GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.RS_STATUS,
					GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1, "deductionName",
					"deductionNo", GtnFrameworkRSConstants.EVALUATION_RULE_NAME,
					GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1, GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
					GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1, GtnFrameworkRSConstants.ATTACHED_DATE1);
			fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
					GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
					GtnFrameworkRSConstants.RS_END_DATE1, "deductionNo", GtnFrameworkRSConstants.EVALUATION_RULE_NAME,
					GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE1, GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
					GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE1);
			rebateSetupMassField.resetMassupdateCombobox(Arrays.asList(GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.START_DATE, GtnFrameworkRSConstants.END_DATE, "Deduction Calendar No",
					GtnFrameworkRSConstants.EVALUATION_RULE, GtnFrameworkRSConstants.CALCULATION_RULE1,
					GtnFrameworkRSConstants.EVALUATION_RULE_BUNDLE, GtnFrameworkRSConstants.CALCULATION_RULE_BUNDLE2));
		} else{
			tableColumnDataType = new Class<?>[] { Boolean.class, String.class, String.class, Integer.class, Date.class,
					Date.class };

			tableHeader = new String[] { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
					GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.RS_START_DATE1, GtnFrameworkRSConstants.RS_END_DATE };

			visibleColumnList = Arrays.asList(checkRecordId, GtnFrameworkCommonConstants.ITEM_NO,
					GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkRSConstants.RS_STATUS,
					GtnFrameworkRSConstants.RS_START_DATE, GtnFrameworkRSConstants.RS_END_DATE1);

			fieldFactoryColumnList = Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
					GtnFrameworkRSConstants.RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
					GtnFrameworkRSConstants.RS_END_DATE1);

			rebateSetupMassField.resetMassupdateCombobox(Arrays.asList(GtnFrameworkRSConstants.RS_STATUS1,
					GtnFrameworkRSConstants.START_DATE, GtnFrameworkRSConstants.END_DATE));
		}

		List<Integer> dateColumn = new ArrayList<>();
		Map<String, Class<?>> dataType = new HashMap<>();
		if (tableColumnDataType != null) {
			for (int i = 0; i < tableColumnDataType.length; i++) {
				if (Date.class.equals(tableColumnDataType[i])) {
					dateColumn.add(i);
				}
				dataType.put(visibleColumnList.get(i).toString(), tableColumnDataType[i]);
			}
		}

		GtnUIFrameworkComponentConfig componentConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psRebateSetupTabResultDataTable").getComponentConfig();
		GtnUIFrameworkPagedTableConfig rebateSetupPagedTableConfig = componentConfig.getGtnPagedTableConfig();

		rebateSetupPagedTableConfig.setTableColumnDataType(tableColumnDataType);

		rebateSetupPagedTableConfig.setTableColumnMappingId(visibleColumnList.toArray());

		rebateSetupPagedTableConfig.setTableVisibleHeader(tableHeader);
		rebateSetupPagedTableConfig.setEditableColumnList(fieldFactoryColumnList);
		rebateSetupPagedTableConfig.setEditableField(GtnFrameworkRebateScheduleSetUpTabConfig
				.createTableFieldFactoryComponents(rebateSetupPagedTableConfig.getEditableColumnList()));
		rebateSetupPagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		componentConfig.setGtnPagedTableConfig(rebateSetupPagedTableConfig);
		GtnUIFrameworkGlobalUI.addChildComponent("rebateSetupTabResultLayout", Arrays.asList(componentConfig));
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("psRebateSetupTabResultDataTable");
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
	}

	private boolean isEditMode() {
		boolean editFlag = true;
		Object mode = GtnUIFrameworkGlobalUI.getSessionProperty("mode");
		if (mode != null || mode == GtnUIFrameworkModeType.VIEW) {
			editFlag = false;
		}
		return editFlag;
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

}
