
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.processmonitor.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.label.GtnUIFrameworkLabelConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.processmonitor.constants.GtnFrameworkProcessMonitorStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.processmonitor.constants.GtnWsProcessMonitorConstants;

/**
 *
 * @author
 */
public class GtnFrameworkProcessMonitorLandingScreenConfig {

	private GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig processMonitorView = componentConfig.getViewConfig("Search View", "V001", true);
		addComponentList(processMonitorView);
		return processMonitorView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addProcessMonitorMainLayout(componentList);

	}

	private void addProcessMonitorMainLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		addMonitorResultTable(componentList);
		addMonitorEditor(componentList);
		addADDButtonLayout(componentList);

	}

	private void addMonitorEditor(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig processMonitorEditorPanel = componentConfig.getPanelConfig("monitorEditorPanel",
				false, null);
		processMonitorEditorPanel.setComponentName("Monitor Editor");
		processMonitorEditorPanel.setAuthorizationIncluded(true);
		componentList.add(processMonitorEditorPanel);
		addFieldLayout(componentList);

	}

	private void addMonitorResultTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig processMonitorResultPanel = componentConfig.getPanelConfig("monitorResultPanel",
				false, null);
		processMonitorResultPanel.setComponentName("Monitor Result Table");
		processMonitorResultPanel.setAuthorizationIncluded(true);
		componentList.add(processMonitorResultPanel);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layout = componentConfig.getVerticalLayoutConfig("processMonitorResultCsslayout",
				true, "monitorResultPanel");
		layout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layout);
		GtnUIFrameworkComponentConfig layout1 = componentConfig.getHorizontalLayoutConfig("processMonitorResultlayout",
				true, "processMonitorResultCsslayout");
		layout1.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layout1);
		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig processMonitorFieldLayout = componentConfig
				.getHorizontalLayoutConfig("fieldLayout", true, "monitorEditorPanel");
		processMonitorFieldLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_14);
		componentList.add(processMonitorFieldLayout);

		addMonitorEditorLayout(componentList);
	}

	private void addMonitorEditorLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig monitorEditorLayout = componentConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.MONITOR_EDITOR_CSS_LAYOUT, true, "fieldLayout");
		monitorEditorLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		monitorEditorLayout.setSpacing(true);
		monitorEditorLayout.setMargin(true);
		componentList.add(monitorEditorLayout);

		addMonitorInformationCSSLayout(componentList);
		addRunTimesCSSLayout(componentList);
	}

	private void addMonitorInformationCSSLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig monitorInformationCSSLayout = componentConfig.getCssLayoutConfig(
				"monitorInformationCSSLayout", true, GtnFrameworkCommonConstants.MONITOR_EDITOR_CSS_LAYOUT);
		monitorInformationCSSLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_5);
		monitorInformationCSSLayout.setSpacing(true);
		monitorInformationCSSLayout.setMargin(true);
		componentList.add(monitorInformationCSSLayout);

		addMonitorInformationPanel(componentList);
		addMonitorLayout(componentList);
		addMonitorInformationComponent(componentList);
	}

	private void addRunTimesCSSLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig runTimesCSSLayout = componentConfig.getCssLayoutConfig("runTimesCSSLayout", true,
				GtnFrameworkCommonConstants.MONITOR_EDITOR_CSS_LAYOUT);
		runTimesCSSLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_7);
		componentList.add(runTimesCSSLayout);

		addRunTimesPanel(componentList);
		addHorizontalCSSLayout(componentList);

	}

	private void addMonitorInformationPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = new GtnUIFrameworkComponentConfig();
		panelConfig.setComponentName("Monitor Information");
		panelConfig.setComponentId("monitorInformationPanel");
		panelConfig.setParentComponentId("monitorInformationCSSLayout");
		panelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAuthorizationIncluded(true);
		panelConfig.setAddToParent(true);
		componentList.add(panelConfig);

	}

	private void addMonitorLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig monitorLayout = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.PROCESS_MONITOR_LAYOUT, true, "monitorInformationPanel",
				GtnUIFrameworkLayoutType.COL1_LAYOUT);
		componentList.add(monitorLayout);

	}

	private void addMonitorInformationComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		addMonitorProcessName(componentList);
		addMonitorProcessType(componentList);
		addMonitorCalender(componentList);
		addMonitorStartDate(componentList);
		addMonitorEndDate(componentList);
	}

	private void addRunTimesPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig runTimesPanel = componentConfig.getPanelConfig("runTimesPanel", true,
				"runTimesCSSLayout");
		runTimesPanel.setComponentName("Run Times");
		runTimesPanel.setAuthorizationIncluded(true);
		runTimesPanel.setComponentHight("225px");
		componentList.add(runTimesPanel);

	}

	private void addHorizontalCSSLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig runTimesLayout = componentConfig
				.getVerticalLayoutConfig(GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT, true, "runTimesPanel");
		runTimesLayout.setSpacing(false);
		componentList.add(runTimesLayout);

		addHorizontalLayoutOneComponent(componentList);
		addHorizontalLayoutTwoComponent(componentList);
		addHorizontalLayoutThreeComponent(componentList);
	}

	private void addHorizontalLayoutOneComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig horizontalLayoutOne = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.HORIZONTAL_LAYOUT_ONE, true,
				GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT);
		horizontalLayoutOne.setAuthorizationIncluded(true);
		horizontalLayoutOne.setComponentHight(GtnFrameworkCssConstants.PIXEL_30);
		componentList.add(horizontalLayoutOne);

		addRun1Ddlb(componentList);
		addHoursLabel(componentList, GtnFrameworkCommonConstants.HORIZONTAL_LAYOUT_ONE);
		addHours1Ddlb(componentList);
		addMinsLabel(componentList);

	}

	private void addHorizontalLayoutTwoComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig horizontalLayoutTwo = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT2, true,
				GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT);
		horizontalLayoutTwo.setAuthorizationIncluded(true);
		horizontalLayoutTwo.setComponentHight(GtnFrameworkCssConstants.PIXEL_30);
		componentList.add(horizontalLayoutTwo);
		addRun2Ddlb(componentList);
		addHoursLabel(componentList, GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT2);
		addHours2Ddlb(componentList);
		addMins2Label(componentList);
	}

	private void addHorizontalLayoutThreeComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig horizontalLayoutThree = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT3, true,
				GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT);
		horizontalLayoutThree.setAuthorizationIncluded(true);
		horizontalLayoutThree.setComponentHight(GtnFrameworkCssConstants.PIXEL_30);
		componentList.add(horizontalLayoutThree);

		addRun3Ddlb(componentList);
		addHoursLabel(componentList, GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT3);
		addHours3Ddlb(componentList);
		addMins3Label(componentList);
	}

	private void addHours1Ddlb(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig run1HoursParentLayout = componentConfig.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.RUN1_HOURS_PARENT_LAYOUT, true,
				GtnFrameworkCommonConstants.HORIZONTAL_LAYOUT_ONE);
		run1HoursParentLayout.setSpacing(false);
		run1HoursParentLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run1HoursParentLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(run1HoursParentLayout);
		GtnUIFrameworkComponentConfig hours1ddlb = new GtnUIFrameworkComponentConfig();
		hours1ddlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		hours1ddlb.setComponentId(GtnFrameworkCommonConstants.HOURS1_DDLB);

		hours1ddlb.setParentComponentId(run1HoursParentLayout.getComponentId());
		hours1ddlb.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig hours1DdlbConfig = new GtnUIFrameworkComboBoxConfig();
		hours1DdlbConfig.setDefaultValue(GtnWsProcessMonitorConstants.SET_DEFAULT_MINUTES);
		hours1DdlbConfig.setItemValues(Arrays.asList(GtnFrameworkProcessMonitorStringContants.HOURS.split(",")));
		hours1ddlb.setGtnComboboxConfig(hours1DdlbConfig);

		componentList.add(hours1ddlb);

	}

	private void addHoursLabel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig hoursLabelParentLayout = componentConfig.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.RUN1_HOURS_PARENT_LAYOUT + parentComponentId, true, parentComponentId);
		hoursLabelParentLayout.setSpacing(false);
		hoursLabelParentLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		hoursLabelParentLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		hoursLabelParentLayout.setComponentWidth(GtnFrameworkCssConstants.PIXEL_65);
		componentList.add(hoursLabelParentLayout);

		GtnUIFrameworkComponentConfig hoursLabel = new GtnUIFrameworkComponentConfig();
		hoursLabel.setComponentType(GtnUIFrameworkComponentType.LABEL);
		hoursLabel.setComponentId(GtnFrameworkCommonConstants.HOURS + parentComponentId);
		hoursLabel.setComponentName(GtnFrameworkCommonConstants.HOURS);
		hoursLabel.setComponentValue(GtnFrameworkCommonConstants.HOURS);

		hoursLabel.setParentComponentId(hoursLabelParentLayout.getComponentId());
		hoursLabel.setAddToParent(true);
		hoursLabel.setEnable(true);

		GtnUIFrameworkLabelConfig hoursLabelConfig = new GtnUIFrameworkLabelConfig();
		hoursLabelConfig.setEnable(true);
		componentList.add(hoursLabel);
	}

	private void addMinsLabel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig run1MinsParentLayout = componentConfig.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.RUN1_HOURS_PARENT_LAYOUT, true,
				GtnFrameworkCommonConstants.HORIZONTAL_LAYOUT_ONE);
		run1MinsParentLayout.setSpacing(false);
		run1MinsParentLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run1MinsParentLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		run1MinsParentLayout.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		componentList.add(run1MinsParentLayout);

		GtnUIFrameworkComponentConfig mins1 = new GtnUIFrameworkComponentConfig();
		mins1.setComponentType(GtnUIFrameworkComponentType.LABEL);
		mins1.setComponentId("Mins1");
		mins1.setComponentName(GtnFrameworkCommonConstants.MINUTES);
		mins1.setComponentValue(GtnFrameworkCommonConstants.MINUTES);

		mins1.setParentComponentId(run1MinsParentLayout.getComponentId());
		mins1.setAddToParent(true);
		mins1.setEnable(true);

		GtnUIFrameworkLabelConfig labelConfig = new GtnUIFrameworkLabelConfig();
		labelConfig.setEnable(true);
		componentList.add(mins1);
	}

	private void addMins2Label(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig run2MinuteParentLayout = componentConfig.getHorizontalLayoutConfig(
				"run2MinuteParentLayout", true, GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT2);
		run2MinuteParentLayout.setSpacing(false);
		run2MinuteParentLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run2MinuteParentLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		run2MinuteParentLayout.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		componentList.add(run2MinuteParentLayout);

		GtnUIFrameworkComponentConfig mins2 = new GtnUIFrameworkComponentConfig();
		mins2.setComponentType(GtnUIFrameworkComponentType.LABEL);
		mins2.setComponentId("Mins2");
		mins2.setComponentName(GtnFrameworkCommonConstants.MINUTES);
		mins2.setComponentValue(GtnFrameworkCommonConstants.MINUTES);
		mins2.setParentComponentId(run2MinuteParentLayout.getComponentId());
		mins2.setAddToParent(true);
		mins2.setEnable(true);

		GtnUIFrameworkLabelConfig labelConfig = new GtnUIFrameworkLabelConfig();
		labelConfig.setEnable(true);
		componentList.add(mins2);
	}

	private void addHours2Ddlb(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig run2HourParentLayout = componentConfig.getHorizontalLayoutConfig(
				"run2HourParentLayout", true, GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT2);
		run2HourParentLayout.setSpacing(false);
		run2HourParentLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run2HourParentLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(run2HourParentLayout);

		GtnUIFrameworkComponentConfig hours2ddlb = new GtnUIFrameworkComponentConfig();
		hours2ddlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		hours2ddlb.setComponentId(GtnFrameworkCommonConstants.HOURS2_DDLB);
		hours2ddlb.setParentComponentId(run2HourParentLayout.getComponentId());
		hours2ddlb.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig hours2DdlbConfig = new GtnUIFrameworkComboBoxConfig();
		hours2DdlbConfig.setDefaultValue(GtnWsProcessMonitorConstants.SET_DEFAULT_MINUTES);
		hours2DdlbConfig.setItemValues(Arrays.asList(GtnFrameworkProcessMonitorStringContants.HOURS.split(",")));
		hours2ddlb.setGtnComboboxConfig(hours2DdlbConfig);

		componentList.add(hours2ddlb);

	}

	private void addMins3Label(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig run3MinParentLayout = componentConfig.getHorizontalLayoutConfig(
				"run3MinParentLayout", true, GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT3);
		run3MinParentLayout.setSpacing(false);
		run3MinParentLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run3MinParentLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		run3MinParentLayout.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		componentList.add(run3MinParentLayout);

		GtnUIFrameworkComponentConfig mins3 = new GtnUIFrameworkComponentConfig();
		mins3.setComponentType(GtnUIFrameworkComponentType.LABEL);
		mins3.setComponentId("Mins3");
		mins3.setComponentName(GtnFrameworkCommonConstants.MINUTES);
		mins3.setComponentValue(GtnFrameworkCommonConstants.MINUTES);
		mins3.setParentComponentId(run3MinParentLayout.getComponentId());
		mins3.setAddToParent(true);
		mins3.setEnable(true);

		GtnUIFrameworkLabelConfig labelConfig = new GtnUIFrameworkLabelConfig();
		labelConfig.setEnable(true);
		componentList.add(mins3);
	}

	private void addHours3Ddlb(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig run3HourParentLayout = componentConfig.getHorizontalLayoutConfig(
				"run3HourParentLayout", true, GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT3);
		run3HourParentLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run3HourParentLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(run3HourParentLayout);

		GtnUIFrameworkComponentConfig hours3ddlb = new GtnUIFrameworkComponentConfig();
		hours3ddlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		hours3ddlb.setComponentId(GtnFrameworkCommonConstants.HOURS3_DDLB);
		hours3ddlb.setParentComponentId(run3HourParentLayout.getComponentId());
		hours3ddlb.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig hours3DdlbConfig = new GtnUIFrameworkComboBoxConfig();
		hours3DdlbConfig.setDefaultValue(GtnWsProcessMonitorConstants.SET_DEFAULT_MINUTES);
		hours3DdlbConfig.setItemValues(Arrays.asList(GtnFrameworkProcessMonitorStringContants.HOURS.split(",")));
		hours3ddlb.setGtnComboboxConfig(hours3DdlbConfig);

		componentList.add(hours3ddlb);

	}

	private void addRun1Ddlb(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig run1ParentLayout = componentConfig.getHorizontalLayoutConfig("run1ParentLayout",
				true, GtnFrameworkCommonConstants.HORIZONTAL_LAYOUT_ONE);
		run1ParentLayout.setSpacing(false);
		run1ParentLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run1ParentLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(run1ParentLayout);
		GtnUIFrameworkComponentConfig run1Ddlb = new GtnUIFrameworkComponentConfig();
		run1Ddlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		run1Ddlb.setComponentId(GtnFrameworkCommonConstants.RUN1_DDLB);
		run1Ddlb.setComponentName(GtnFrameworkProcessMonitorStringContants.RUN_1);
		run1Ddlb.setParentComponentId(run1ParentLayout.getComponentId());
		run1Ddlb.setAddToParent(true);

		run1Ddlb.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);

		GtnUIFrameworkComboBoxConfig run1DdlbConfig = new GtnUIFrameworkComboBoxConfig();
		run1DdlbConfig.setDefaultValue(GtnWsProcessMonitorConstants.SET_DEFAULT_HOURS);
		run1DdlbConfig.setItemValues(Arrays.asList(GtnFrameworkProcessMonitorStringContants.RUN.split(",")));
		run1Ddlb.setGtnComboboxConfig(run1DdlbConfig);

		componentList.add(run1Ddlb);

	}

	private void addRun2Ddlb(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig run2ParentLayout = componentConfig.getHorizontalLayoutConfig("run2ParentLayout",
				true, GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT2);
		run2ParentLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run2ParentLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(run2ParentLayout);

		GtnUIFrameworkComponentConfig run2Ddlb = new GtnUIFrameworkComponentConfig();
		run2Ddlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		run2Ddlb.setComponentId(GtnFrameworkCommonConstants.RUN2_DDLB);
		run2Ddlb.setComponentName(GtnFrameworkProcessMonitorStringContants.RUN_2);
		run2Ddlb.setParentComponentId(run2ParentLayout.getComponentId());
		run2Ddlb.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig run2DdlbConfig = new GtnUIFrameworkComboBoxConfig();
		run2DdlbConfig.setDefaultValue(GtnWsProcessMonitorConstants.SET_DEFAULT_HOURS);
		run2DdlbConfig.setItemValues(Arrays.asList(GtnFrameworkProcessMonitorStringContants.RUN.split(",")));
		run2Ddlb.setGtnComboboxConfig(run2DdlbConfig);

		componentList.add(run2Ddlb);

	}

	private void addRun3Ddlb(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig run3ParentLayout = componentConfig.getHorizontalLayoutConfig("run3ParentLayout",
				true, GtnFrameworkCommonConstants.RUN_HORIZONTAL_LAYOUT3);
		run3ParentLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run3ParentLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(run3ParentLayout);

		GtnUIFrameworkComponentConfig run3Ddlb = new GtnUIFrameworkComponentConfig();
		run3Ddlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		run3Ddlb.setComponentId(GtnFrameworkCommonConstants.RUN3_DDLB);
		run3Ddlb.setComponentName(GtnFrameworkProcessMonitorStringContants.RUN_3);
		run3Ddlb.setParentComponentId(run3ParentLayout.getComponentId());
		run3Ddlb.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig run3DdlbConfig = new GtnUIFrameworkComboBoxConfig();
		run3DdlbConfig.setDefaultValue(GtnWsProcessMonitorConstants.SET_DEFAULT_HOURS);
		run3DdlbConfig.setItemValues(Arrays.asList(GtnFrameworkProcessMonitorStringContants.RUN.split(",")));
		run3Ddlb.setGtnComboboxConfig(run3DdlbConfig);

		componentList.add(run3Ddlb);

	}

	private void addMonitorProcessName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig monitorProcessNameLayout = componentConfig.getHorizontalLayoutConfig(
				"processNamelayout", true, GtnFrameworkCommonConstants.PROCESS_MONITOR_LAYOUT);
		monitorProcessNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(monitorProcessNameLayout);

		GtnUIFrameworkComponentConfig monitorProcessName = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PROCESS_NAME, true, "processNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		monitorProcessName.setAuthorizationIncluded(true);
		monitorProcessName.setComponentName("Process Name");
		monitorProcessName.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkValidationConfig monitorProcessNameValidationConfig = new GtnUIFrameworkValidationConfig();
		monitorProcessNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		monitorProcessName.setGtnUIFrameworkValidationConfig(monitorProcessNameValidationConfig);

		componentList.add(monitorProcessName);
	}

	private void addMonitorProcessType(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig monitorProcessTypeLayout = componentConfig.getHorizontalLayoutConfig(
				"processTypelayout", true, GtnFrameworkCommonConstants.PROCESS_MONITOR_LAYOUT);
		componentList.add(monitorProcessTypeLayout);

		GtnUIFrameworkComponentConfig monitorProcessType = componentConfig.getUIFrameworkComponentConfig("processType",
				true, "processTypelayout", GtnUIFrameworkComponentType.COMBOBOX);
		monitorProcessType.setComponentName("process Type");
		monitorProcessType.setAuthorizationIncluded(true);
		monitorProcessType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkComboBoxConfig monitorProcessTypeConfig = componentConfig.getComboBoxConfig("PROCESS_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		List<GtnUIFrameWorkActionConfig> monitorProcessTypeActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnWsProcessMonitorConstants.GTNFRAMEWORK_PROCESS_TYPE_DDLB_CHANGE_ACTION);
		customAction.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.RUN1_DDLB, GtnFrameworkCommonConstants.RUN2_DDLB,
						GtnFrameworkCommonConstants.RUN3_DDLB, GtnFrameworkCommonConstants.HOURS1_DDLB,
						GtnFrameworkCommonConstants.HOURS2_DDLB, GtnFrameworkCommonConstants.HOURS3_DDLB));
		monitorProcessTypeActionConfigList.add(customAction);
		monitorProcessType.setGtnUIFrameWorkActionConfigList(monitorProcessTypeActionConfigList);

		GtnUIFrameworkValidationConfig monitorProcessTypeValConfig = new GtnUIFrameworkValidationConfig();
		monitorProcessTypeValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		monitorProcessType.setGtnUIFrameworkValidationConfig(monitorProcessTypeValConfig);
		monitorProcessType.setGtnComboboxConfig(monitorProcessTypeConfig);

		componentList.add(monitorProcessType);
	}

	private void addMonitorCalender(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig monitorCalenderLayout = componentConfig
				.getHorizontalLayoutConfig("calenderlayout", true, GtnFrameworkCommonConstants.PROCESS_MONITOR_LAYOUT);
		componentList.add(monitorCalenderLayout);

		GtnUIFrameworkComponentConfig monitorCalender = componentConfig.getUIFrameworkComponentConfig("calender", true,
				"calenderlayout", GtnUIFrameworkComponentType.COMBOBOX);
		monitorCalender.setComponentName("Calendar");
		monitorCalender.setAuthorizationIncluded(true);
		monitorCalender.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkComboBoxConfig monitorCalenderConfig = new GtnUIFrameworkComboBoxConfig();
		monitorCalenderConfig.setItemValues(Arrays.asList("Holiday Schedule", "Holiday"));
		monitorCalenderConfig.setHasDefaultValue(true);
		monitorCalenderConfig.setDefaultValue(GtnWsProcessMonitorConstants.SET_DEFAULT_HOLIDAY_SCHEDULE);
		monitorCalender.setGtnComboboxConfig(monitorCalenderConfig);

		GtnUIFrameworkValidationConfig monitorCalenderValidationConfig = new GtnUIFrameworkValidationConfig();
		monitorCalenderValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		monitorCalender.setGtnUIFrameworkValidationConfig(monitorCalenderValidationConfig);

		componentList.add(monitorCalender);

	}

	private void addMonitorStartDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig monitorStartDateLayout = componentConfig
				.getHorizontalLayoutConfig("startDatelayout", true, GtnFrameworkCommonConstants.PROCESS_MONITOR_LAYOUT);
		componentList.add(monitorStartDateLayout);

		GtnUIFrameworkComponentConfig monitorStartDate = componentConfig.getUIFrameworkComponentConfig("startDate",
				true, "startDatelayout", GtnUIFrameworkComponentType.DATEFIELD);
		monitorStartDate.setAuthorizationIncluded(true);
		monitorStartDate.setComponentName("Start Date");
		monitorStartDate.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkValidationConfig monitorStartDateValidationConfig = new GtnUIFrameworkValidationConfig();
		monitorStartDateValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		monitorStartDate.setGtnUIFrameworkValidationConfig(monitorStartDateValidationConfig);

		componentList.add(monitorStartDate);
	}

	private void addMonitorEndDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig monitorEndDateLayout = componentConfig.getHorizontalLayoutConfig("endDatelayout",
				true, GtnFrameworkCommonConstants.PROCESS_MONITOR_LAYOUT);
		componentList.add(monitorEndDateLayout);

		GtnUIFrameworkComponentConfig monitorEndDate = componentConfig.getUIFrameworkComponentConfig("endDate", true,
				"endDatelayout", GtnUIFrameworkComponentType.DATEFIELD);
		monitorEndDate.setAuthorizationIncluded(true);
		monitorEndDate.setComponentName("End Date");
		monitorEndDate.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		monitorEndDate.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(monitorEndDate);
	}

	private void addADDButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig addButtonLayout = componentConfig
				.getHorizontalLayoutConfig(GtnFrameworkProcessMonitorStringContants.BUTTON_LAYOUT, false, null);
		addButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(addButtonLayout);
		addAddButtonComponent(componentList);
		addDeleteButtonComponent(componentList);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig addButton = componentConfig.getUIFrameworkComponentConfig("gtnAddButton", true,
				GtnFrameworkProcessMonitorStringContants.BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		addButton.setComponentName(GtnFrameworkCommonStringConstants.ADD);
		addButton.setAuthorizationIncluded(true);
		componentList.add(addButton);

		List<GtnUIFrameWorkActionConfig> addButtonActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig addButtonCustomValidationAction = new GtnUIFrameWorkActionConfig();
		addButtonCustomValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addButtonCustomValidationAction
				.addActionParameter(GtnWsProcessMonitorConstants.PROCESS_MONITOR_VALIDATION_ACTION);

		addButtonActionConfigList.add(addButtonCustomValidationAction);

		GtnUIFrameWorkActionConfig addButtonConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		addButtonConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_CONFIRMATION);
		alertParamsList.add(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_SAVE_MESSAGE);
		alertParamsList.add(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_PROCESS_NAME);
		addButtonActionConfigList.add(addButtonConfirmationActionConfig);

		List<GtnUIFrameWorkActionConfig> addButtonOnSucessActionConfigList = new ArrayList<>();

		alertParamsList.add(addButtonOnSucessActionConfigList);

		GtnUIFrameWorkActionConfig addButtonCustomSaveAction = new GtnUIFrameWorkActionConfig();
		addButtonCustomSaveAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addButtonCustomSaveAction.addActionParameter(GtnWsProcessMonitorConstants.PROCESS_MONITOR_SAVE_ACTION);
		addButtonCustomSaveAction.addActionParameter(GtnFrameworkProcessMonitorStringContants.PROCESS_MONITOR_FIELDS);
		addButtonOnSucessActionConfigList.add(addButtonCustomSaveAction);

		addButtonConfirmationActionConfig.setActionParameterList(alertParamsList);

		addButton.setGtnUIFrameWorkActionConfigList(addButtonActionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig deleteButton = componentConfig.getUIFrameworkComponentConfig(
				GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_DELETE, true,
				GtnFrameworkProcessMonitorStringContants.BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		deleteButton.setAuthorizationIncluded(true);
		deleteButton.setComponentName("DELETE");
		deleteButton.setEnable(false);
		GtnUIFrameWorkActionConfig deleteButtonConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		deleteButtonConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<GtnUIFrameWorkActionConfig> deleteButtonActionConfigList = new ArrayList<>();
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_CONFIRMATION);
		alertParamsList.add(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_DELETE_MESSAGE);
		alertParamsList.add(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_PROCESS_NAME);
		deleteButtonActionConfigList.add(deleteButtonConfirmationActionConfig);

		List<GtnUIFrameWorkActionConfig> deleteButtonOnSucessActionConfigList = new ArrayList<>();

		alertParamsList.add(deleteButtonOnSucessActionConfigList);

		GtnUIFrameWorkActionConfig deleteButtonCustomSaveAction = new GtnUIFrameWorkActionConfig();
		deleteButtonCustomSaveAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteButtonCustomSaveAction.addActionParameter(GtnWsProcessMonitorConstants.PROCESS_MONITOR_SAVE_ACTION);
		deleteButtonCustomSaveAction
				.addActionParameter(GtnFrameworkProcessMonitorStringContants.PROCESS_MONITOR_FIELDS);
		deleteButtonOnSucessActionConfigList.add(deleteButtonCustomSaveAction);
		deleteButtonConfirmationActionConfig.setActionParameterList(alertParamsList);
		GtnUIFrameWorkActionConfig pmDefaultValueActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		pmDefaultValueActionConfig.addActionParameter(GtnFrameworkProcessMonitorStringContants.PROCESS_MONITOR_FIELDS);
		pmDefaultValueActionConfig
				.addActionParameter(Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
						null, null, null, null, null, null, null, null, null }));
		deleteButtonOnSucessActionConfigList.add(pmDefaultValueActionConfig);
		deleteButton.setGtnUIFrameWorkActionConfigList(deleteButtonActionConfigList);
		componentList.add(deleteButton);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig processMonitorSearchResultConfig = componentConfig.getUIFrameworkComponentConfig(
				"searchResultTable", true, "processMonitorResultlayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		processMonitorSearchResultConfig.setAuthorizationIncluded(true);
		processMonitorSearchResultConfig.setComponentName("Results");
		processMonitorSearchResultConfig.setResetToDefaultAllowed(false);
		processMonitorSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		componentList.add(processMonitorSearchResultConfig);

		GtnUIFrameworkPagedTableConfig processMonitorSearchResult = componentConfig.getPagedTableConfig(false, true,
				GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_SERVICE_SCREEN
						+ GtnWsProcessMonitorConstants.GET_PROCESS_MONITOR_TABLE_DATA,
				GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_SERVICE_SCREEN
						+ GtnWsProcessMonitorConstants.GET_PROCESS_MONITOR_TABLE_DATA,
				"processMonitor", "SearchQuery");
		processMonitorSearchResult.setEditable(false);
		processMonitorSearchResult.setSinkItemPerPageWithPageLength(false);

		processMonitorSearchResult
				.setTableColumnDataType(GtnFrameworkProcessMonitorStringContants.getProcessMonitorTableColumnType());

		processMonitorSearchResult
				.setTableVisibleHeader(GtnFrameworkProcessMonitorStringContants.getProcessMonitorTableHeader());

		processMonitorSearchResult
				.setTableColumnMappingId(GtnFrameworkProcessMonitorStringContants.getProcessMonitorTableColumns());
		processMonitorSearchResult.setExtraColumn(new Object[] { "processTypeId" });

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(processMonitorSearchResultConfig.getComponentId());
		processMonitorSearchResult.addPostCreationActionConfig(loadDataTableActionConfig);
		processMonitorSearchResult.setDoubleClickEnable(true);

		List<GtnUIFrameWorkActionConfig> itemDoubleClickActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig editActionConfig = new GtnUIFrameWorkActionConfig();
		editActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnWsProcessMonitorConstants.PROCESS_MONITOR_TABLE_CLICK_ACTION);
		editActionConfig.addActionParameter("searchResultTable");
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.PROCESS_NAME);
		editActionConfig.addActionParameter(true);
		itemDoubleClickActionConfigList.add(editActionConfig);
		processMonitorSearchResult.setGtnUIFrameWorkActionConfigList(itemDoubleClickActionConfigList);

		processMonitorSearchResultConfig.setGtnPagedTableConfig(processMonitorSearchResult);
	}
}
