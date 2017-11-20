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

public class GtnFrameworkTransactionViewTemplateConfig {

	private final boolean isInvalid;

	public GtnFrameworkTransactionViewTemplateConfig(boolean isInvalid) {

		this.isInvalid = isInvalid;
	}

	public GtnUIFrameworkViewConfig getTransactionView(){
		GtnUIFrameworkViewConfig addView = new GtnUIFrameworkViewConfig();
		addView.setViewName("Add View");
		addView.setViewId("V002");
		addView.setDefaultView(false);
		addComponentList(addView);
		return addView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addViewFieldLayout(componentList);
		addBackButtonLayout(componentList);
	}

	private void addViewFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("transactionMainViewLayout");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setAddToParent(false);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		
		GtnUIFrameworkLayoutConfig horiLayout = new GtnUIFrameworkLayoutConfig();
		horiLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig horiGtnLayout = new GtnUIFrameworkComponentConfig();
		horiGtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		horiGtnLayout.setComponentStyle(styleList);
		horiGtnLayout.setComponentId("transactionViewLayout");
		horiGtnLayout.setParentComponentId(gtnLayout.getComponentId());
		horiGtnLayout.setAddToParent(true);
		horiGtnLayout.setGtnLayoutConfig(horiLayout);
		componentList.add(horiGtnLayout);

	}

	private void addBackButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layout.setComponentColumnSize(1);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("backButtonlayout");
		gtnLayout.setParentComponentId("transactionMainViewLayout");
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		addBackButtonComponent(componentList);
	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("BackButton");
		searchButtonConfig.setComponentName("Back");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId("backButtonlayout");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();

		navigationActionConfig.setActionType(
				isInvalid ? GtnUIFrameworkActionType.POPUP_CLOSE_ACTION : GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("");
		actionConfigList.add(navigationActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}
}
