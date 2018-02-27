package com.stpl.gtn.gtn2o.ui.module.transaction.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionInvalidIntegrationLoadAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionReprocessInVisibleAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkTransactionInvalidComponentConfig {

	public void getComponentsForModules(List<GtnUIFrameworkComponentConfig> componentList,
			List<GtnUIFrameworkComponentConfig> viewComponentList) {

		addFieldComponentForInvalidIntegration(componentList, viewComponentList);
	}

	private void addFieldComponentForInvalidIntegration(List<GtnUIFrameworkComponentConfig> componentList,
			List<GtnUIFrameworkComponentConfig> viewComponentList) {
		addInvalidTableComboBox(componentList, viewComponentList);
		addMainLayoutForInvalidId(componentList);

		addInvalidToDate(componentList);
	}

	private void addMainLayoutForInvalidId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("companyIdlayoutInterfaceNameCss3");
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		gtnLayout.setParentComponentId(GtnTransactionUIConstants.SEARCH_CRITERIA_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		getMainLayoutForIdAndFromDate(componentList);
	}

	private void addInvalidFromDate(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("companyIdlayoutinvalidFromDate");
		gtnLayout.setParentComponentId(parentId);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig invaidFromDate = new GtnUIFrameworkComponentConfig();
		invaidFromDate.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		invaidFromDate.setComponentId("invalidFromDate");
		invaidFromDate.setComponentName("Invalid From");
		invaidFromDate.setParentComponentId("companyIdlayoutinvalidFromDate");
		invaidFromDate.setAddToParent(true);

		componentList.add(invaidFromDate);

	}

	private void addInvalidIdTextBox(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkLayoutConfig layout1 = new GtnUIFrameworkLayoutConfig();
		layout1.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout1 = new GtnUIFrameworkComponentConfig();
		gtnLayout1.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout1.setComponentId("companyIdlayoutInvaidId");
		gtnLayout1.setParentComponentId(parentId);
		gtnLayout1.setAddToParent(true);
		gtnLayout1.setGtnLayoutConfig(layout1);
		componentList.add(gtnLayout1);

		GtnUIFrameworkComponentConfig invaidIdTextBox = new GtnUIFrameworkComponentConfig();
		invaidIdTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		invaidIdTextBox.setComponentId("invalidId");
		invaidIdTextBox.setComponentName("Invalid Record ID");
		invaidIdTextBox.setParentComponentId("companyIdlayoutInvaidId");
		invaidIdTextBox.setAddToParent(true);

		componentList.add(invaidIdTextBox);

	}

	private void addInvalidTableComboBox(List<GtnUIFrameworkComponentConfig> componentList,
			List<GtnUIFrameworkComponentConfig> viewComponentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("companyIdlayoutInterfaceNameCss1");
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		gtnLayout.setParentComponentId(GtnTransactionUIConstants.SEARCH_CRITERIA_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkLayoutConfig layout2 = new GtnUIFrameworkLayoutConfig();
		layout2.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout1 = new GtnUIFrameworkComponentConfig();
		gtnLayout1.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout1.setComponentId("companyIdlayoutInterfaceNameCss2");
		gtnLayout1.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		gtnLayout1.setParentComponentId("companyIdlayoutInterfaceNameCss1");
		gtnLayout1.setAddToParent(true);
		gtnLayout1.setGtnLayoutConfig(layout2);
		componentList.add(gtnLayout1);

		GtnUIFrameworkLayoutConfig layout3 = new GtnUIFrameworkLayoutConfig();
		layout3.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout3 = new GtnUIFrameworkComponentConfig();
		gtnLayout3.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout3.setComponentId("companyIdlayoutInterfaceName");
		gtnLayout3.setParentComponentId("companyIdlayoutInterfaceNameCss2");
		gtnLayout3.setAddToParent(true);
		gtnLayout3.setGtnLayoutConfig(layout3);
		componentList.add(gtnLayout3);

		GtnUIFrameworkComponentConfig interfaceNameComboBox = new GtnUIFrameworkComponentConfig();
		interfaceNameComboBox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		interfaceNameComboBox.setComponentId("intefaceName");
		interfaceNameComboBox.setComponentName("Table");
		interfaceNameComboBox.setParentComponentId("companyIdlayoutInterfaceName");
		interfaceNameComboBox.setAddToParent(true);

		componentList.add(interfaceNameComboBox);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatusConfig.setComboBoxType("INVALID_MODULE_NAME");
		interfaceNameComboBox.setGtnComboboxConfig(companyStatusConfig);
		GtnUIFrameWorkActionConfig customReprocessInVisibleAction = new GtnUIFrameWorkActionConfig();
		customReprocessInVisibleAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customReprocessInVisibleAction
				.addActionParameter(GtnUIFrameworkTransactionReprocessInVisibleAction.class.getName());

		List<GtnUIFrameWorkActionConfig> gtnUiFramwworkActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig gtnUiFramwworkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUiFramwworkActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUiFramwworkActionConfig.setActionParameterList(Arrays
				.asList(GtnUIFrameworkTransactionInvalidIntegrationLoadAction.class.getName(), viewComponentList));
		gtnUiFramwworkActionConfig.setFieldValues(Arrays.asList("companyIdlayoutInterfaceNameCss5",
				"companyIdlayoutInterfaceNameCss4", "searchCriterialayout1", "resultPanel", "resultPanelLayout",
				"resultlayout", "searchButtonlayout", "reprocessButtonlayout", "intefaceName",
				GtnTransactionUIConstants.SEARCH_CRITERIA_LAYOUT, "gtnExcelButtonlayout"));
		gtnUiFramwworkActionConfigList.add(gtnUiFramwworkActionConfig);
		gtnUiFramwworkActionConfigList.add(customReprocessInVisibleAction);

		interfaceNameComboBox.setGtnUIFrameWorkActionConfigList(gtnUiFramwworkActionConfigList);

	}

	private void addInvalidToDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig layout1 = new GtnUIFrameworkLayoutConfig();
		layout1.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout1 = new GtnUIFrameworkComponentConfig();
		gtnLayout1.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout1.setComponentId("companyIdlayoutinvalidToDate");
		gtnLayout1.setParentComponentId("companyIdlayoutInterfaceNameCss5");
		gtnLayout1.setAddToParent(true);
		gtnLayout1.setGtnLayoutConfig(layout1);
		componentList.add(gtnLayout1);

		GtnUIFrameworkComponentConfig invaidFromDate = new GtnUIFrameworkComponentConfig();
		invaidFromDate.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		invaidFromDate.setComponentId("invalidToDate");
		invaidFromDate.setComponentName("Invalid To");
		invaidFromDate.setParentComponentId("companyIdlayoutinvalidToDate");
		invaidFromDate.setAddToParent(true);
		componentList.add(invaidFromDate);

	}

	private void getMainLayoutForIdAndFromDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("companyIdlayoutInterfaceNameCss4");
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		gtnLayout.setParentComponentId("companyIdlayoutInterfaceNameCss3");
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addInvalidIdTextBox(componentList, gtnLayout.getComponentId());
		addInvalidFromDate(componentList, gtnLayout.getComponentId());
	}
}
