/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.deductioncalendarconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkDCDedctionDetailsConfig {

	public void addDeductionDetailsTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("deductionDetailsTab");
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		GtnUIFrameworkComponentConfig priceScheduleMainCssConfig = new GtnUIFrameworkComponentConfig();
		priceScheduleMainCssConfig.setComponentId("deductionDetailsTabMainCssLayout");
		priceScheduleMainCssConfig.setAddToParent(true);
		priceScheduleMainCssConfig.setParentComponentId("deductionDetailsTab");
		priceScheduleMainCssConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		List<String> priceScheduleMainCssCStyleList = new ArrayList<>();
		priceScheduleMainCssCStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		priceScheduleMainCssCStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		priceScheduleMainCssConfig.setComponentStyle(priceScheduleMainCssCStyleList);

		GtnUIFrameworkLayoutConfig priceScheduleMainCssLayout = new GtnUIFrameworkLayoutConfig();
		priceScheduleMainCssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		priceScheduleMainCssConfig.setGtnLayoutConfig(priceScheduleMainCssLayout);

		componentList.add(priceScheduleMainCssConfig);

		addDeductionCalendarDetailsPanel(componentList);
	}

	private void addDeductionCalendarDetailsPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Deduction Calendar Details");
		panel.setComponentId("deductionCalendarDetailsPanel");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(true);
		panel.setParentComponentId("deductionDetailsTabMainCssLayout");
		componentList.add(panel);
		addDeductionCalendarDetailsPanelLayout(componentList);
	}

	private void addDeductionCalendarDetailsPanelLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("deductionCalendarDetailsPanelLayout");
		layoutConfig.setAddToParent(true);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setParentComponentId("deductionCalendarDetailsPanel");

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);
		addDisplayDetailsPanel(componentList);
		addCustomerSearchFieldComponent(componentList);

	}

	private void addDisplayDetailsPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Display Details");
		panel.setComponentId("displayDetailsPanel");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(true);
		panel.setParentComponentId("deductionCalendarDetailsPanelLayout");
		componentList.add(panel);
		addDisplayDetailsPanelFieldLayout(componentList);
	}

	private void addDisplayDetailsPanelFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("displayDetailsPanelLayout");
		layoutConfig.setAddToParent(true);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setParentComponentId("displayDetailsPanel");

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		List<String> styleList = new ArrayList<>();
		styleList.add("gtnGrid-single-ln-layout-5");
		layoutConfig.setComponentStyle(styleList);
		componentList.add(layoutConfig);
		addCustomerSearchFieldComponent(componentList);
	}

	private void addCustomerSearchFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		addDataView(componentList);

	}

	private void addDataView(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("ddDataViewLayout");
		gtnLayout.setParentComponentId("displayDetailsPanelLayout");
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contactSelection = new GtnUIFrameworkComponentConfig();
		contactSelection.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		contactSelection.setComponentId("ddDataView");
		contactSelection.setComponentName("Data View");
		contactSelection.setParentComponentId("ddDataViewLayout");
		contactSelection.setAddToParent(true);

		componentList.add(contactSelection);

		GtnUIFrameworkOptionGroupConfig companyQualifierNameConfig = new GtnUIFrameworkOptionGroupConfig();
		companyQualifierNameConfig.setValuesFromService(false);
		companyQualifierNameConfig.setItemValues(Arrays.asList("Customer", "Product"));

		contactSelection
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		contactSelection.setGtnUIFrameworkOptionGroupConfig(companyQualifierNameConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.setActionParameterList(Arrays.asList(new Object[] {
				"com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.logic.GtnUIFrameWorkContractSelectionChangeAction" }));

		actionConfigList.add(customAction);
		contactSelection.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
