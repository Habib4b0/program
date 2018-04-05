package com.stpl.gtn.gtn2o.ui.module.workflowinbox.config.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.crud.GtnFrameworkWorkflowPublicPrivateViewSaveAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnUIFrameworkWorkflowSaveProfilePopupConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {

		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWPOPUP,
				GtnFrameworkWorkflowInboxClassConstants.DSSAVEVIEWLOOKUP, false);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRootLayout(componentList);
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig vLayout = configProvider.getVerticalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWROOTVERTICAL_LAYOUT, false, null);
		vLayout.setSpacing(true);
		vLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(vLayout);

		GtnUIFrameworkComponentConfig horizontalLayoutType = configProvider.getHorizontalLayoutConfig(
				"saveView-ViewType" + GtnFrameworkCssConstants.HORIZONTAL, true,
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWROOTVERTICAL_LAYOUT);
		horizontalLayoutType.setComponentName("saveView-ViewType" + GtnFrameworkCssConstants.HORIZONTAL);
		horizontalLayoutType.setSpacing(true);
		horizontalLayoutType.setMargin(true);
		componentList.add(horizontalLayoutType);

		addTypeOptionGroup(componentList, horizontalLayoutType.getComponentId());

		GtnUIFrameworkComponentConfig horizontalLayoutName = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWVIEWNAME + GtnFrameworkCssConstants.HORIZONTAL, true,
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWROOTVERTICAL_LAYOUT);
		horizontalLayoutType.setComponentName(
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWVIEWNAME + GtnFrameworkCssConstants.HORIZONTAL);
		horizontalLayoutName.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		horizontalLayoutName.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_BOTTOM_10);
		horizontalLayoutName.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(horizontalLayoutName);

		addNameTextField(componentList, horizontalLayoutName.getComponentId());

		GtnUIFrameworkComponentConfig horizontalLayoutButton = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWVIEWBUTTONS + GtnFrameworkCssConstants.HORIZONTAL, true,
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWROOTVERTICAL_LAYOUT);
		horizontalLayoutType.setComponentName(
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWVIEWBUTTONS + GtnFrameworkCssConstants.HORIZONTAL);
		componentList.add(horizontalLayoutButton);

		addControlPopUpButtonLayout(componentList, horizontalLayoutButton.getComponentId());
	}

	private void addTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWTYPE, true, parentId,
				GtnUIFrameworkComponentType.OPTIONGROUP);
		addHierarchyTypeOptionGroup.setAuthorizationIncluded(true);
		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList(GtnFrameworkWorkflowInboxClassConstants.PRIVATE,
				GtnFrameworkWorkflowInboxClassConstants.PUBLIC));
		comboConfig.setValuesFromService(false);
		addHierarchyTypeOptionGroup.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		addHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(addHierarchyTypeOptionGroup);
	}

	private void addNameTextField(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWNAME, true, parentId,
				GtnUIFrameworkComponentType.TEXTBOX);
		addHierarchyTypeOptionGroup.setAuthorizationIncluded(true);
		addHierarchyTypeOptionGroup.setComponentName(GtnFrameworkWorkflowInboxClassConstants.VIEWNAMECOLUMN);

		GtnUIFrameworkValidationConfig valConfigForViewName = new GtnUIFrameworkValidationConfig();
		valConfigForViewName.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addHierarchyTypeOptionGroup.setGtnUIFrameworkValidationConfig(valConfigForViewName);
		componentList.add(addHierarchyTypeOptionGroup);
	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig saveViewCancel = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWCANCEL, true, parentId,
				GtnUIFrameworkComponentType.BUTTON);
		saveViewCancel.setAuthorizationIncluded(true);
		saveViewCancel.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CANCEL);

		List<GtnUIFrameWorkActionConfig> actionConfigCloseList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.DSSAVEVIEWLOOKUP);
		actionConfigCloseList.add(closeAction);
		saveViewCancel.setGtnUIFrameWorkActionConfigList(actionConfigCloseList);

		componentList.add(saveViewCancel);

		GtnUIFrameworkComponentConfig saveViewAdd = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWADD, true, parentId,
				GtnUIFrameworkComponentType.BUTTON);
		saveViewAdd.setAuthorizationIncluded(true);
		saveViewAdd.setComponentName(GtnFrameworkWorkflowInboxClassConstants.ADD);

		componentList.add(saveViewAdd);
		List<GtnUIFrameWorkActionConfig> saveConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig viewValidationActionConfig = new GtnUIFrameWorkActionConfig();

		viewValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		viewValidationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWNAME));

		GtnUIFrameWorkActionConfig viewAlertActionConfig = new GtnUIFrameWorkActionConfig();
		viewAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> viewAlertParams = new ArrayList<>();
		viewAlertParams.add(GtnFrameworkWorkflowInboxClassConstants.INVALIDVIEWNAME);
		viewAlertParams.add(GtnFrameworkWorkflowInboxClassConstants.ENTERTHEVIEWNAME);

		viewAlertActionConfig.setActionParameterList(viewAlertParams);
		viewValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(viewAlertActionConfig)));
		saveConfigList.add(viewValidationActionConfig);

		GtnUIFrameWorkActionConfig saveAction = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveAction.addActionParameter(GtnFrameworkWorkflowPublicPrivateViewSaveAction.class.getName());
		saveAction.addActionParameter("add");
		saveAction.setFieldValues(GtnFrameworkWorkflowInboxClassConstants.getViewSaveFields());
		saveConfigList.add(saveAction);

		GtnUIFrameWorkActionConfig savelookupaddcloseAction = new GtnUIFrameWorkActionConfig();
		savelookupaddcloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		savelookupaddcloseAction.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.DSSAVEVIEWLOOKUP);
		saveConfigList.add(savelookupaddcloseAction);

		saveViewAdd.setGtnUIFrameWorkActionConfigList(saveConfigList);
		GtnUIFrameworkComponentConfig saveViewUpdate = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.SAVEVIEWUPDATE, true, parentId,
				GtnUIFrameworkComponentType.BUTTON);
		saveViewUpdate.setComponentName(GtnFrameworkWorkflowInboxClassConstants.UPDATE_UPPERCASE);

		componentList.add(saveViewUpdate);

		List<GtnUIFrameWorkActionConfig> updateConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig updateAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		updateAction.addActionParameter(GtnFrameworkWorkflowPublicPrivateViewSaveAction.class.getName());
		updateAction.addActionParameter("update");
		updateAction.setFieldValues(GtnFrameworkWorkflowInboxClassConstants.getViewSaveFields());

		updateConfigList.add(updateAction);

		GtnUIFrameWorkActionConfig savelookupupdatecloseAction = new GtnUIFrameWorkActionConfig();
		savelookupupdatecloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		savelookupupdatecloseAction.addActionParameter(GtnFrameworkWorkflowInboxClassConstants.DSSAVEVIEWLOOKUP);
		updateConfigList.add(savelookupupdatecloseAction);

		saveViewUpdate.setGtnUIFrameWorkActionConfigList(updateConfigList);

	}
}
