package com.stpl.gtn.gtn2o.ui.module.transaction.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkTransactionSearchTemplateConfig {

	private final boolean isInvalid;

	public GtnFrameworkTransactionSearchTemplateConfig(boolean isInvalid) {

		this.isInvalid = isInvalid;
	}

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Search View");
		view.setViewId("V001");
		view.setDefaultView(true);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.RELOAD_HELPER_TABLE_ACTION);
		view.addViewAction(customAction);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addSearchCriteriaPanel(componentList);
		addButtonLayout(componentList, "searchButtonlayout");
		addResultPanel(componentList);
		addExcelButton(componentList);
		addButtonLayout(componentList, "reprocessButtonlayout");

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Search Criteria");
		panel.setComponentId("searchCriteriaPanel");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(false);

		componentList.add(panel);
		addFieldLayout(componentList);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setComponentId("resultPanelLayout");
		gtnLayout.setAddToParent(false);
		gtnLayout.setMargin(true);
		gtnLayout.setSpacing(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig panelConfig = new GtnUIFrameworkComponentConfig();
		panelConfig.setComponentName("Results");
		panelConfig.setComponentId("resultPanel");
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAddToParent(true);
		panelConfig.setParentComponentId("resultPanelLayout");
		if (isInvalid) {
			panelConfig.setVisible(false);
		} else {
			panelConfig.setVisible(true);
		}
		componentList.add(panelConfig);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setComponentId("resultlayout");
		gtnLayout.setParentComponentId("resultPanel");
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig mainLayout = new GtnUIFrameworkLayoutConfig();
		mainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig mainGtnLayout = new GtnUIFrameworkComponentConfig();
		mainGtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		mainGtnLayout.setComponentId("searchCriteriaMainlayout");
		mainGtnLayout.setParentComponentId("searchCriteriaPanel");
		mainGtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		mainGtnLayout.setAddToParent(true);
		mainGtnLayout.setGtnLayoutConfig(mainLayout);
		componentList.add(mainGtnLayout);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("searchCriterialayout");
		gtnLayout.setParentComponentId(mainGtnLayout.getComponentId());
		gtnLayout.addComponentStyle("no-margin");
		gtnLayout.setComponentWidth("98%");
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkLayoutConfig invalidDateLayout = new GtnUIFrameworkLayoutConfig();
		invalidDateLayout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig invalidDateGtnLayout = new GtnUIFrameworkComponentConfig();
		invalidDateGtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		invalidDateGtnLayout.setComponentId("companyIdlayoutInterfaceNameCss5");
		invalidDateGtnLayout.setParentComponentId(mainGtnLayout.getComponentId());
		invalidDateGtnLayout.setAddToParent(true);
		invalidDateGtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		invalidDateGtnLayout.setGtnLayoutConfig(invalidDateLayout);
		componentList.add(invalidDateGtnLayout);

		GtnUIFrameworkLayoutConfig layout1 = new GtnUIFrameworkLayoutConfig();
		layout1.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout1 = new GtnUIFrameworkComponentConfig();
		gtnLayout1.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout1.setComponentId("searchCriterialayout1");
		gtnLayout1.setParentComponentId(mainGtnLayout.getComponentId());
		gtnLayout1.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		gtnLayout1.setAddToParent(true);
		gtnLayout1.setGtnLayoutConfig(layout1);
		componentList.add(gtnLayout1);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String layoutName) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layout.setComponentColumnSize(2);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(layoutName);
		gtnLayout.setAddToParent(false);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_20);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
	}

	private void addExcelButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("gtnExcelButtonlayout");
		gtnLayout.setAddToParent(false);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

	}

}
