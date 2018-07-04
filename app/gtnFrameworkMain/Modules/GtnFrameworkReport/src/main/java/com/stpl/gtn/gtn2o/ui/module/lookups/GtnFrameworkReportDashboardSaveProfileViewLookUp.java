package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.action.GtnReportingDashboardSaveProfileAddAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportingDashboardUpdateProfileAddAction;
import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkReportDashboardSaveProfileViewLookUp {
	private GtnFrameworkReportLayoutsConfig layoutConfig = new GtnFrameworkReportLayoutsConfig();

	public GtnUIFrameworkViewConfig getSaveViewLookUpView(String namespace) {

		GtnUIFrameworkViewConfig reportDashboardSaveProfileView = new GtnUIFrameworkViewConfig();
		reportDashboardSaveProfileView.setViewName("Profile Save View");
		reportDashboardSaveProfileView.setViewId(GtnFrameworkReportStringConstants.REPORT_DASHBOARD_SAVE_PROFILE_LOOKUP_VIEW_ID);
		reportDashboardSaveProfileView.setDefaultView(false);
		addReportDashboardSaveProfileComponentList(reportDashboardSaveProfileView, namespace);
		return reportDashboardSaveProfileView;
	}


	private void addReportDashboardSaveProfileComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> reportDashboardSaveProfileComponentList = new ArrayList<>();
		view.setGtnComponentList(reportDashboardSaveProfileComponentList);
		addReportDashboardSaveProfileRootLayout(reportDashboardSaveProfileComponentList, namespace);
	}

	private void addReportDashboardSaveProfileRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig reportDashboardSaveProfileRootLayout = new GtnUIFrameworkComponentConfig();
		reportDashboardSaveProfileRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDashboardSaveProfileRootLayout.setComponentId(GtnFrameworkCommonConstants.SAVE_VIEW_LOOK_UP_ROOT_VERTICAL_LAYOUT);
		reportDashboardSaveProfileRootLayout.setAddToParent(false);
		reportDashboardSaveProfileRootLayout.setSpacing(true);
		reportDashboardSaveProfileRootLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkLayoutConfig reportDashboardSaveProfileRootConfig = new GtnUIFrameworkLayoutConfig();
		reportDashboardSaveProfileRootConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportDashboardSaveProfileRootLayout.setGtnLayoutConfig(reportDashboardSaveProfileRootConfig);
		componentList.add(reportDashboardSaveProfileRootLayout);

		String rootId = "saveViewLookUp-RootVerticalLayout";

		GtnUIFrameworkComponentConfig reportDashboardSaveProfileHorizontalLayoutType = layoutConfig.getHorizontalLayoutConfig("saveView-ViewType",
				rootId);
		reportDashboardSaveProfileHorizontalLayoutType.setSpacing(true);
		reportDashboardSaveProfileHorizontalLayoutType.setMargin(true);
		componentList.add(reportDashboardSaveProfileHorizontalLayoutType);

		addreportDashboardSaveProfileTypeOptionGroup(componentList, reportDashboardSaveProfileHorizontalLayoutType.getComponentId(),namespace);

		GtnUIFrameworkComponentConfig reportDashboardSaveProfileHorizontalLayoutName = layoutConfig.getHorizontalLayoutConfig("saveView-ViewName",
				"saveViewLookUp-RootVerticalLayout");
		reportDashboardSaveProfileHorizontalLayoutName.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		componentList.add(reportDashboardSaveProfileHorizontalLayoutName);

		addReportDashboardSaveProfileNameTextField(componentList, reportDashboardSaveProfileHorizontalLayoutName.getComponentId(),namespace);

		GtnUIFrameworkComponentConfig reportDashboardSaveProfileHorizontalLayoutButton = layoutConfig
				.getHorizontalLayoutConfig("saveView-ViewButtons", rootId);
		componentList.add(reportDashboardSaveProfileHorizontalLayoutButton);

		addReportDashboardControlPopUpButtonLayout(componentList, reportDashboardSaveProfileHorizontalLayoutButton.getComponentId(), namespace);
	}

	private void addreportDashboardSaveProfileTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String namespace) {
		GtnUIFrameworkComponentConfig reportDashboardSaveProfileTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		reportDashboardSaveProfileTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		reportDashboardSaveProfileTypeOptionGroup.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkCommonConstants.SAVE_VIEW_TYPE);
		reportDashboardSaveProfileTypeOptionGroup.setAddToParent(true);
		reportDashboardSaveProfileTypeOptionGroup.setParentComponentId(parentId);
		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList(GtnFrameworkCommonConstants.A_PRIVATE, "Public"));
		comboConfig.setValuesFromService(false);
		reportDashboardSaveProfileTypeOptionGroup.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		reportDashboardSaveProfileTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(reportDashboardSaveProfileTypeOptionGroup);
	}

	private void addReportDashboardSaveProfileNameTextField(List<GtnUIFrameworkComponentConfig> componentList, String parentId,String namespace) {
		GtnUIFrameworkComponentConfig reportDashboardSaveProfileNameTextField = new GtnUIFrameworkComponentConfig();
		reportDashboardSaveProfileNameTextField.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportDashboardSaveProfileNameTextField.setComponentId(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"ReportDashboardSaveProfileNameTextField");
		reportDashboardSaveProfileNameTextField.setComponentName("View Name:");
		reportDashboardSaveProfileNameTextField.setAddToParent(true);
		reportDashboardSaveProfileNameTextField.setParentComponentId(parentId);

		componentList.add(reportDashboardSaveProfileNameTextField);
	}

	private void addReportDashboardControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig reportDashboardSaveProfileSaveViewAdd = new GtnUIFrameworkComponentConfig();
		reportDashboardSaveProfileSaveViewAdd.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportDashboardSaveProfileSaveViewAdd.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportDashboardSaveProfileSaveViewAdd");
		reportDashboardSaveProfileSaveViewAdd.setComponentName("ADD");
		reportDashboardSaveProfileSaveViewAdd.setParentComponentId(parentId);
		reportDashboardSaveProfileSaveViewAdd.setAddToParent(true);
		
		GtnUIFrameWorkActionConfig reportDashboardSaveProfileAddActionConfig = new GtnUIFrameWorkActionConfig();
		reportDashboardSaveProfileAddActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportDashboardSaveProfileAddActionConfig.addActionParameter(GtnReportingDashboardSaveProfileAddAction.class.getName());
		reportDashboardSaveProfileAddActionConfig.addActionParameter(GtnFrameworkReportStringConstants.REPORT_DASHBOARD_SAVE_PROFILE_LOOKUP_VIEW_ID);
		reportDashboardSaveProfileAddActionConfig.addActionParameter(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"ReportDashboardSaveProfileNameTextField");
		reportDashboardSaveProfileAddActionConfig.addActionParameter(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkCommonConstants.SAVE_VIEW_TYPE);
		reportDashboardSaveProfileSaveViewAdd.addGtnUIFrameWorkActionConfig(reportDashboardSaveProfileAddActionConfig);
		
		componentList.add(reportDashboardSaveProfileSaveViewAdd);

		GtnUIFrameworkComponentConfig reportDashboardSaveProfileSaveViewUpdate = new GtnUIFrameworkComponentConfig();
		reportDashboardSaveProfileSaveViewUpdate.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportDashboardSaveProfileSaveViewUpdate.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportDashboardSaveProfileSaveViewUpdate");
		reportDashboardSaveProfileSaveViewUpdate.setComponentName("UPDATE");
		reportDashboardSaveProfileSaveViewUpdate.setParentComponentId(parentId);
		reportDashboardSaveProfileSaveViewUpdate.setAddToParent(true);
		
		GtnUIFrameWorkActionConfig reportDashboardUpdateProfileAddActionConfig = new GtnUIFrameWorkActionConfig();
		reportDashboardUpdateProfileAddActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportDashboardUpdateProfileAddActionConfig.addActionParameter(GtnReportingDashboardUpdateProfileAddAction.class.getName());
		reportDashboardUpdateProfileAddActionConfig.addActionParameter(GtnFrameworkReportStringConstants.REPORT_DASHBOARD_SAVE_PROFILE_LOOKUP_VIEW_ID);
		reportDashboardUpdateProfileAddActionConfig.addActionParameter(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"ReportDashboardSaveProfileNameTextField");
		reportDashboardUpdateProfileAddActionConfig.addActionParameter(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkCommonConstants.SAVE_VIEW_TYPE);
		reportDashboardSaveProfileSaveViewUpdate.addGtnUIFrameWorkActionConfig(reportDashboardUpdateProfileAddActionConfig);
		componentList.add(reportDashboardSaveProfileSaveViewUpdate);

		GtnUIFrameworkComponentConfig reportDashboardSaveProfileSaveViewCancel = new GtnUIFrameworkComponentConfig();
		reportDashboardSaveProfileSaveViewCancel.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportDashboardSaveProfileSaveViewCancel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportDashboardSaveProfileSaveViewCancel");
		reportDashboardSaveProfileSaveViewCancel.setComponentName("CLOSE");
		reportDashboardSaveProfileSaveViewCancel.setParentComponentId(parentId);
		reportDashboardSaveProfileSaveViewCancel.setAddToParent(true);
		
		GtnUIFrameWorkActionConfig reportDashboardCloseActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		reportDashboardCloseActionConfig.addActionParameter(GtnFrameworkReportStringConstants.REPORT_DASHBOARD_SAVE_PROFILE_LOOKUP_VIEW_ID);
		reportDashboardSaveProfileSaveViewCancel.addGtnUIFrameWorkActionConfig(reportDashboardCloseActionConfig);
		
		componentList.add(reportDashboardSaveProfileSaveViewCancel);

	}
}