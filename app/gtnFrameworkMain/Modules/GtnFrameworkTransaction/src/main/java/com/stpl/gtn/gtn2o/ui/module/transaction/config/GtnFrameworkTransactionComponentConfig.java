package com.stpl.gtn.gtn2o.ui.module.transaction.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.stpl.gtn.gtn2o.gtnframeworkjsonbuilder.GtnFrameworkJSONReader;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.button.GtnUIFrameworkButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.button.GtnUiFrameworkButtonType;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameWorkTransactionTableColumnFormatAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameWorkTransactioneRecordTypeAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionAlertAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionCustomResultViewAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionInvalidViewLoadAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionReprocessRemoveAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionResetBeanAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionViewAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.fieldfactory.GtnUIFrameworkTransactionGeneratedCoumnAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.fieldfactory.GtnUIFrameworkTransactionRefreshBeanAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.fieldfactory.GtnUIFrameworkTransactionTableCheckAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.fieldfactory.GtnUIFrameworkTransactionTableCheckAllAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.validation.GtnFrameworkTransactionReprocessRemoveValidation;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentListForInvalidBean;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentTypeListBean;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnFrameworkTransactionClassContants;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnFrameworkTransactionExcelName;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnUIFrameworkTransactionTabsheetBean;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionColumnBean;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionModuleBean;
import com.stpl.gtn.gtn2o.ws.transaction.constants.GtnWsTransactionConstants;

public class GtnFrameworkTransactionComponentConfig {

	private List<Object> visibleColumns = new ArrayList<>();
	private List<String> visibleHeaders = new ArrayList<>();
	private List<String> searchComponentID = new ArrayList<>();
	private List<String> descriptionList = new ArrayList<>();
	private List<String> defaultVisibleColumn = new ArrayList<>();
	private List<String> defaultColumnHeader = new ArrayList<>();
	private List<String> viewVisibleColumn = new ArrayList<>();
	private List<String> defaultViewVisibleColumn = new ArrayList<>();
	private List<String> viewVisibleColumnLayout = new ArrayList<>();
	private List<Object> onlyViewColumns = new ArrayList<>();
	private List<String> mandatoryComponentList = new ArrayList<>();
	private List<String> mandatoryMsgHeaderList = new ArrayList<>();
	private List<String> mandatoryMsgBodyList = new ArrayList<>();
	private List<String>[] visibleColumnArray = null;
	private List<String>[] visibleHeaderArray = null;
	private int totalCombination = 0;
	private List<String> helperComponentID = new ArrayList<>();
	private List<String> columnAlignments = new ArrayList<>();
	private List<Object> columnToAlign = new ArrayList<>();

	public void getComponentsForModules(String portletName, boolean isInvalid,
			List<GtnUIFrameworkComponentConfig> componentList, List<GtnUIFrameworkComponentConfig> viewComponentList,
			String tableName, GtnUIFrameworkTransactionComponentListForInvalidBean bean, String moduleName) {
		List<List<GtnUIFrameworkComponentConfig>> componentListForTransaction = new ArrayList<>();
		componentListForTransaction.add(componentList);
		componentListForTransaction.add(viewComponentList);
		GtnWSTransactionModuleBean transactionModuleBeanList = getTransactionColumnBeanList(portletName);
		seperateComponentsByType(transactionModuleBeanList, isInvalid, componentListForTransaction, portletName,
				tableName, bean, moduleName);
	}

	private Boolean setEnableFlag(String tableName) {
		return Arrays.asList(GtnTransactionUIConstants.getViewEnable()).contains(tableName);

	}

	private void setComponentToList(List<GtnUIFrameworkComponentConfig> componentList, String portletName,
			boolean isInvalid, GtnUIFrameworkTransactionComponentTypeListBean componentBean) {
		addFieldComponent(componentList, "searchCriterialayout1", componentBean.getSearchComponent(), isInvalid);
		addSearchButtonComponent(componentList, portletName, componentBean, isInvalid);
		addPagedTableComponent(componentList, portletName, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				componentBean.isOutBoundModule(), componentBean, GtnFrameworkCommonStringConstants.STRING_EMPTY);

		addResetButtonComponent(componentList, componentBean.getSearchComponent(),
				GtnTransactionUIConstants.SEARCH_BUTTON_LAYOUT, GtnTransactionUIConstants.RESET_DEFAULT_ALERT_MSG,
				!componentBean.isOutBoundModule());
		addExcelButtonComponent(componentList, portletName, componentBean);
	}

	public void seperateComponentsByType(GtnWSTransactionModuleBean transactionModuleBeanList, boolean isInvalid,
			List<List<GtnUIFrameworkComponentConfig>> componentList, String portletName, String tableName,
			GtnUIFrameworkTransactionComponentListForInvalidBean transactionBean, String moduleName) {

		boolean isValidComponentCondition = isInvalid;
		List<GtnWSTransactionColumnBean> transactionModuleConfigBeanList = transactionModuleBeanList.getModule();
		List<GtnWSTransactionColumnBean> transactionBeanList = transactionModuleBeanList.getModuleDetails();

		GtnUIFrameworkTransactionComponentTypeListBean componentBean = getComponentListForComponents(
				isValidComponentCondition, transactionBeanList, isInvalid);
		getConfigurationForModules(componentBean, transactionModuleConfigBeanList);

		getVisibleColumnsAndHeader(componentBean.getSearchComponent(), componentBean.getListViewComponent(), isInvalid,
				componentBean);

		if (isInvalid) {
			List<GtnUIFrameworkComponentConfig> staticComponentList1 = new ArrayList<>();
			addInvalidFieldComponent(staticComponentList1, "companyIdlayoutInterfaceNameCss5", 2, 3,
					componentBean.getStaticComponent());
			transactionBean.setStaticComponent1List(staticComponentList1);
			List<GtnUIFrameworkComponentConfig> staticComponentList2 = new ArrayList<>();
			addInvalidFieldComponent(staticComponentList2, "companyIdlayoutInterfaceNameCss4", 0, 2,
					componentBean.getStaticComponent());
			transactionBean.setStaticComponent2List(staticComponentList2);
			List<GtnUIFrameworkComponentConfig> searchComponentList = new ArrayList<>();
			addFieldComponent(searchComponentList, "searchCriterialayout1",
					componentBean.getSearchComponent().subList(0, componentBean.getSearchComponent().size() - 3), true);
			transactionBean.setSearchComponentList(searchComponentList);
			List<GtnUIFrameworkComponentConfig> searchAndResetComponentList = new ArrayList<>();
			if ("InvalidIntegration".equals(tableName)) {
				transactionBean.setSearchAndResetComponentList(searchAndResetComponentList);
				transactionBean.setListViewComponentList(searchAndResetComponentList);
				transactionBean.setViewComponentList(searchAndResetComponentList);
				transactionBean.setReprocessAndRemoveComponentList(searchAndResetComponentList);
			} else {
				addSearchButtonComponent(searchAndResetComponentList, tableName, componentBean, true);

				addResetButtonComponent(searchAndResetComponentList, componentBean.getSearchComponent(),
						GtnTransactionUIConstants.SEARCH_BUTTON_LAYOUT,
						GtnTransactionUIConstants.RESET_DEFAULT_ALERT_MSG, true);
				transactionBean.setSearchAndResetComponentList(searchAndResetComponentList);
				List<GtnUIFrameworkComponentConfig> tableComponentList = new ArrayList<>();
				addPagedTableComponent(tableComponentList, tableName, portletName, true, componentBean, moduleName);
				transactionBean.setListViewComponentList(tableComponentList);
				List<GtnUIFrameworkComponentConfig> viewOnlyComponentList = new ArrayList<>();
				generateViewOnlyColumns(transactionBeanList, componentBean.getListViewComponent(),
						viewOnlyComponentList, componentBean.isViewIndexFlag(), tableName, isInvalid);
				transactionBean.setViewComponentList(viewOnlyComponentList);
				List<GtnUIFrameworkComponentConfig> reprocessAndRemoveComponentList = new ArrayList<>();
				addReprocessButtonComponent(reprocessAndRemoveComponentList,
						componentBean.getReprocessingWebServiceURL(), componentBean, portletName);
				addRemoveButtonComponent(reprocessAndRemoveComponentList, componentBean, portletName);
				transactionBean.setReprocessAndRemoveComponentList(reprocessAndRemoveComponentList);
			}
			List<GtnUIFrameworkComponentConfig> excelBtnComponentList = new ArrayList<>();
			addExcelButtonComponent(excelBtnComponentList, portletName.toUpperCase(Locale.ENGLISH), componentBean);
			transactionBean.setExcelButtonComponentList(excelBtnComponentList);

		} else {
			setComponentToList(componentList.get(GtnWsNumericConstants.ZERO), portletName, isInvalid, componentBean);

			generateViewOnlyColumns(transactionBeanList, componentBean.getListViewComponent(),
					componentList.get(GtnWsNumericConstants.ONE), componentBean.isViewIndexFlag(),

					portletName, isInvalid);
			addOutBoundModuleCriteria(componentList.get(GtnWsNumericConstants.ZERO), componentBean, portletName);
		}

	}

	private GtnUIFrameworkTransactionComponentTypeListBean getComponentListForComponents(
			boolean isValidComponentCondition, List<GtnWSTransactionColumnBean> transactionBeanList,
			boolean isInvalid) {
		GtnUIFrameworkTransactionComponentTypeListBean componentBean = new GtnUIFrameworkTransactionComponentTypeListBean();
		List<GtnWSTransactionColumnBean> listViewComponent = new ArrayList<>();
		List<GtnWSTransactionColumnBean> defaultListViewComponent = new ArrayList<>();
		List<GtnWSTransactionColumnBean> searchComponent = new ArrayList<>();
		List<GtnWSTransactionColumnBean> viewModeComponents = new ArrayList<>();
		List<GtnWSTransactionColumnBean> viewModeOrderComponents = new ArrayList<>();
		List<GtnWSTransactionColumnBean> staticComponent = new ArrayList<>();
		boolean viewIndexFlag = false;
		boolean isInvalidComponent = isValidComponentCondition;
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : transactionBeanList) {

			if (!isInvalid) {
				isInvalidComponent = !gtnWSTransactionColumnBean.getIsInvalidComponent();
			}

			if (isInvalidComponent) {
				getSearchComponentsList(gtnWSTransactionColumnBean, searchComponent, listViewComponent,
						defaultListViewComponent, staticComponent);
				getViewRelatedComponentList(gtnWSTransactionColumnBean, viewModeComponents, viewModeOrderComponents);
				if (gtnWSTransactionColumnBean.isViewModeIndexFlag()) {
					viewIndexFlag = true;
				}

				if (gtnWSTransactionColumnBean.getTotalCombination() > 0) {
					totalCombination = gtnWSTransactionColumnBean.getTotalCombination();
				}
			}

			if (gtnWSTransactionColumnBean.isOnlyValidComponent() && isInvalid) {
				getInvalidOnlyComponentList(gtnWSTransactionColumnBean, searchComponent, listViewComponent);
			}
			setDecimalFormatLogic(gtnWSTransactionColumnBean, componentBean);

		}
		componentBean.setSearchComponent(searchComponent);
		componentBean.setDefaultListViewComponent(defaultListViewComponent);
		Collections.sort(listViewComponent);
		componentBean.setListViewComponent(listViewComponent);
		componentBean.setStaticComponent(staticComponent);
		componentBean.setViewIndexFlag(viewIndexFlag);
		componentBean.setViewModeComponents(viewModeComponents);
		componentBean.setViewModeOrderComponents(viewModeOrderComponents);
		return componentBean;
	}

	private void getInvalidOnlyComponentList(GtnWSTransactionColumnBean gtnWSTransactionColumnBean,
			List<GtnWSTransactionColumnBean> searchComponent, List<GtnWSTransactionColumnBean> listViewComponent) {
		if (gtnWSTransactionColumnBean.getIsSearchCriteria()) {
			searchComponent.remove(gtnWSTransactionColumnBean);
		}
		if (gtnWSTransactionColumnBean.getIsResultView()) {
			listViewComponent.remove(gtnWSTransactionColumnBean);
		}

	}

	private void getViewRelatedComponentList(GtnWSTransactionColumnBean gtnWSTransactionColumnBean,
			List<GtnWSTransactionColumnBean> viewModeComponents,
			List<GtnWSTransactionColumnBean> viewModeOrderComponents) {

		if (gtnWSTransactionColumnBean.getViewModeIndex() > 0) {
			viewModeComponents.add(gtnWSTransactionColumnBean);
		}
		if (gtnWSTransactionColumnBean.getViewModeOrder() > 0) {
			viewModeOrderComponents.add(gtnWSTransactionColumnBean);
		}

	}

	private void getSearchComponentsList(GtnWSTransactionColumnBean gtnWSTransactionColumnBean,
			List<GtnWSTransactionColumnBean> searchComponent, List<GtnWSTransactionColumnBean> listViewComponent,
			List<GtnWSTransactionColumnBean> defaultListViewComponent,
			List<GtnWSTransactionColumnBean> staticComponent) {
		if (gtnWSTransactionColumnBean.getIsSearchCriteria()) {
			searchComponent.add(gtnWSTransactionColumnBean);
		}
		if (gtnWSTransactionColumnBean.getIsResultView()) {
			listViewComponent.add(gtnWSTransactionColumnBean);
		}

		if (gtnWSTransactionColumnBean.isDefaultResultView()) {
			defaultListViewComponent.add(gtnWSTransactionColumnBean);
		}
		if (gtnWSTransactionColumnBean.isStaticSearchCriterian()) {
			staticComponent.add(gtnWSTransactionColumnBean);
		}
	}

	private void getVisibleColumnsAndHeader(List<GtnWSTransactionColumnBean> searchComponent,
			List<GtnWSTransactionColumnBean> listViewComponent, boolean isInvalid,
			GtnUIFrameworkTransactionComponentTypeListBean componentBean) {

		for (GtnWSTransactionColumnBean obj : listViewComponent) {
			visibleColumns.add(obj.getColumnID());
			visibleHeaders.add(obj.getColumnName());
			if (obj.getColumnAlignment() != null) {
				columnToAlign.add(obj.getColumnID());
				columnAlignments.add(obj.getColumnAlignment());
			}
			componentBean.addFormatterList(obj.getPattern());
		}
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : searchComponent) {
			searchComponentID.add(gtnWSTransactionColumnBean.getColumnID());

			if (gtnWSTransactionColumnBean.isLoadDescription()) {
				descriptionList.add(gtnWSTransactionColumnBean.getColumnID());
				searchComponentID.remove(gtnWSTransactionColumnBean.getColumnID());
			}

			if (gtnWSTransactionColumnBean.isInvalidLoadDescription() && isInvalid) {
				descriptionList.add(gtnWSTransactionColumnBean.getColumnID());
				searchComponentID.remove(gtnWSTransactionColumnBean.getColumnID());
			}

		}

	}

	private GtnWSTransactionModuleBean getTransactionColumnBeanList(String portletName) {
		return new GtnFrameworkJSONReader().getTypeConfigFromJson(GtnTransactionUIConstants.TRANSACTION_JSON_PATH,
				portletName + ".json", GtnWSTransactionModuleBean.class);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String layout,
			List<GtnWSTransactionColumnBean> searchComponent, boolean isInvalid) {
		String layoutName = GtnTransactionUIConstants.COMPONENT_LAYOUT;
		for (GtnWSTransactionColumnBean component : searchComponent) {
			switch (component.getComponentType()) {
			case GtnTransactionUIConstants.JSON_TEXTBOX:
				addTextField(componentList, component, layout, layoutName,
						GtnFrameworkCommonStringConstants.STRING_EMPTY, true);
				break;
			case GtnTransactionUIConstants.JSON_COMBOBOX:
				helperComponentID.add(component.getColumnID());
				addComboBox(componentList, component, layout);
				break;
			case GtnTransactionUIConstants.JSON_DATEFIELD:
				addDateField(componentList, component, layout, layoutName,
						GtnFrameworkCommonStringConstants.STRING_EMPTY, true, isInvalid);
				break;
			default:
				break;
			}
		}

	}

	private void addInvalidFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String layout,
			int fromIndex, int toIndex, List<GtnWSTransactionColumnBean> staticComponent) {
		String layoutName = GtnTransactionUIConstants.COMPONENT_LAYOUT;
		for (GtnWSTransactionColumnBean component : staticComponent.subList(fromIndex, toIndex)) {
			switch (component.getComponentType()) {
			case GtnTransactionUIConstants.JSON_TEXTBOX:
				addTextField(componentList, component, layout, layoutName,
						GtnFrameworkCommonStringConstants.STRING_EMPTY, true);
				break;
			case GtnTransactionUIConstants.JSON_COMBOBOX:
				addComboBox(componentList, component, layout);
				break;
			case GtnTransactionUIConstants.JSON_DATEFIELD:
				addDateField(componentList, component, layout, layoutName,
						GtnFrameworkCommonStringConstants.STRING_EMPTY, true, false);
				break;
			default:
				break;
			}
		}

	}

	private void addTextField(List<GtnUIFrameworkComponentConfig> componentList, GtnWSTransactionColumnBean component,
			String parentLayoutName, String layoutPrefix, String componentPrefix, boolean isEnable) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(layoutPrefix + component.getColumnID());
		gtnLayout.setParentComponentId(parentLayoutName);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = new GtnUIFrameworkComponentConfig();
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentId(componentPrefix + component.getColumnID());
		companyIdConfig.setComponentName(component.getColumnName());
		if (component.getColumnMappingId() != null) {
			companyIdConfig.setComponentWsFieldId(component.getColumnMappingId());
		}
		if (component.getExpressionType() != null) {
			companyIdConfig.setExpressionType(component.getExpressionType());
		}
		if ("reprocessedFlag".equals(component.getColumnID())
				&& !GtnTransactionUIConstants.TRANSACTION_VIEW.equals(componentPrefix)) {
			companyIdConfig.setVisible(false);
			companyIdConfig.setComponentValue(component.getDefaultValue());
		}
		if ("addChgDelIndicator".equals(component.getColumnID())
				&& !GtnTransactionUIConstants.TRANSACTION_VIEW.equals(componentPrefix)) {
			companyIdConfig.setVisible(false);
			companyIdConfig.setComponentValue(component.getDefaultValue());
		}
		companyIdConfig.setParentComponentId(layoutPrefix + component.getColumnID());
		companyIdConfig.setAddToParent(true);
		companyIdConfig.setEnable(isEnable);
		companyIdConfig.setDefaultFocus(component.isDefaultFocus());

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();

		if (component.isLengthValidator()) {
			validationConfig.setAttachLengthValidatior(true);
			validationConfig.setMinSize(component.getMinLength());
			validationConfig.setMaxLength(component.getMaxLength());
			validationConfig
					.setRegxValidationMessage("Length should be less than " + component.getMaxLength() + " Characters");

		}
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdConfig.setGtnUIFrameworkValidationConfig(validationConfig);
		componentList.add(companyIdConfig);
	}

	private void addComboBox(List<GtnUIFrameworkComponentConfig> componentList, GtnWSTransactionColumnBean component,
			String parentLayoutName) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnTransactionUIConstants.COMPONENT_LAYOUT + component.getColumnID());
		gtnLayout.setParentComponentId(parentLayoutName);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = new GtnUIFrameworkComponentConfig();
		companyStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentId(component.getColumnID());
		companyStatus.setComponentName(component.getColumnName());
		companyStatus.setParentComponentId(GtnTransactionUIConstants.COMPONENT_LAYOUT + component.getColumnID());
		companyStatus.setAddToParent(true);

		componentList.add(companyStatus);
		if (component.getExpressionType() != null) {
			companyStatus.setExpressionType(component.getExpressionType());
		}
		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatusConfig.setComboBoxType(component.getListName());
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
		companyStatusConfig.setIntegerItemCode(!component.isLoadDescription());
		getAdditonalSetting(component, companyStatus, companyStatusConfig);
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyStatus.setGtnUIFrameworkValidationConfig(validationConfig);
		companyStatus.setDefaultFocus(component.isDefaultFocus());

	}

	private void getAdditonalSetting(GtnWSTransactionColumnBean component, GtnUIFrameworkComponentConfig companyStatus,
			GtnUIFrameworkComboBoxConfig companyStatusConfig) {
		if (component.isMandatory()) {
			GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
			validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
			companyStatus.setGtnUIFrameworkValidationConfig(validationConfig);
		}
		if (component.getListValues() != null) {
			companyStatusConfig.setItemValues(component.getListValues());
		}
		if (component.getCustomClassName() != null) {
			List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig customResultViewAction = new GtnUIFrameWorkActionConfig();
			customResultViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			customResultViewAction.addActionParameter(
					GtnFrameworkTransactionClassContants.TRANSACTION_ACTION_PACKAGE + component.getCustomClassName());
			actionConfigList.add(customResultViewAction);
			companyStatus.setGtnUIFrameWorkActionConfigList(actionConfigList);
		}
	}

	private void addDateField(List<GtnUIFrameworkComponentConfig> componentList, GtnWSTransactionColumnBean component,
			String parentLayoutName, String layoutPrefix, String componentPrefix, boolean isEnable, boolean isInvalid) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(layoutPrefix + component.getColumnID());
		gtnLayout.setParentComponentId(parentLayoutName);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = new GtnUIFrameworkComponentConfig();
		companyStatus.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentId(componentPrefix + component.getColumnID());
		companyStatus.setComponentName(component.getColumnName());
		companyStatus.setParentComponentId(layoutPrefix + component.getColumnID());
		companyStatus.setAddToParent(true);
		if (component.getColumnMappingId() != null) {
			companyStatus.setComponentWsFieldId(component.getColumnMappingId());
		}
		if (component.getExpressionType() != null) {

			companyStatus.setExpressionType(component.getExpressionType());
		}
		if (isInvalid) {
			companyStatus.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.DATE_CENTER_ALIGN));
		}
		companyStatus.setEnable(isEnable);
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyStatus.setGtnUIFrameworkValidationConfig(validationConfig);
		componentList.add(companyStatus);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String tableName,
			String moduleName, boolean isInvalid, GtnUIFrameworkTransactionComponentTypeListBean componentBean,
			String invalidModule) {
		boolean viewEnabled;
		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setAuthorizationIncluded(true);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultConfig.setComponentId(GtnTransactionUIConstants.SEARCH_TABLE_ID);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setParentComponentId("resultlayout");
		searchResultConfig.setVisible(true);
		searchResultConfig.setAddToParent(true);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setTableColumnDataTypeURL(GtnWsTransactionConstants.GTN_WS_TRANSACTION_SERVICE
				+ GtnWsTransactionConstants.GTN_WS_TRANSACTION_GETDATATYPE_SERVICE);
		List<GtnWSTransactionColumnBean> viewModeComponents = componentBean.getViewModeComponents();
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : viewModeComponents) {
			viewVisibleColumn.add(gtnWSTransactionColumnBean.getColumnID());
		}
		if (totalCombination > 0) {
			searchResults.setTableVisibleHeader(defaultColumnHeader.toArray(new String[defaultColumnHeader.size()]));
			searchResults
					.setTableColumnMappingId(defaultVisibleColumn.toArray(new Object[defaultVisibleColumn.size()]));
		} else {
			searchResults.setTableVisibleHeader(visibleHeaders.toArray(new String[visibleHeaders.size()]));
			searchResults.setTableColumnMappingId(visibleColumns.toArray(new Object[visibleColumns.size()]));
		}

		searchResults.setColumnToAlign(columnToAlign.toArray(new String[columnToAlign.size()]));
		searchResults.setColumnAlignment(columnAlignments.toArray(new String[columnAlignments.size()]));

		searchResults.setCountUrl(GtnWsTransactionConstants.GTN_WS_TRANSACTION_SERVICE
				+ GtnWsTransactionConstants.GTN_WS_TRANSACTION_GETSEARCHRESULTS_SERVICE);
		searchResults.setResultSetUrl(GtnWsTransactionConstants.GTN_WS_TRANSACTION_SERVICE
				+ GtnWsTransactionConstants.GTN_WS_TRANSACTION_GETSEARCHRESULTS_SERVICE);
		searchResults.setModuleName(tableName);
		searchResults.setPageLength(10);
		searchResults.setItemPerPage(10);
		searchResults.setSinkItemPerPageWithPageLength(false);

		if (isInvalid) {
			viewEnabled = setEnableFlag(moduleName);
			getInvalidTableConfig(searchResults, tableName, viewEnabled, componentBean, invalidModule);
		} else {
			viewEnabled = setEnableFlag(tableName);
			getValidTableConfig(searchResults, searchResultConfig, tableName, viewEnabled, viewModeComponents,
					componentBean);
		}
		getCustomFilter(tableName, searchResults);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void getValidTableConfig(GtnUIFrameworkPagedTableConfig searchResults,
			GtnUIFrameworkComponentConfig searchResultConfig, String tableName, boolean viewEnabled,
			List<GtnWSTransactionColumnBean> viewModeComponents,
			GtnUIFrameworkTransactionComponentTypeListBean componentBean) {
		List<GtnWSTransactionColumnBean> viewModeOrderComponents = componentBean.getViewModeOrderComponents();
		List<GtnWSTransactionColumnBean> listViewComponent = componentBean.getListViewComponent();
		if (viewEnabled) {
			searchResults.setSelectable(true);
			searchResults.setItemClickListener(true);
			List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
			navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
			navigationActionConfig.addActionParameter("V002");
			actionConfigList.add(navigationActionConfig);
			GtnUIFrameWorkActionConfig viewActionConfig = new GtnUIFrameWorkActionConfig();
			getViewActionconfig(viewActionConfig, tableName, Boolean.FALSE, viewModeComponents, viewModeOrderComponents,
					listViewComponent);

			actionConfigList.add(viewActionConfig);

			GtnUIFrameWorkActionConfig recordTypeAction = new GtnUIFrameWorkActionConfig();
			recordTypeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			recordTypeAction.addActionParameter(GtnUIFrameWorkTransactionTableColumnFormatAction.class.getName());
			recordTypeAction.addActionParameter(GtnTransactionUIConstants.SEARCH_TABLE_ID);
			recordTypeAction.addActionParameter(GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT);
			recordTypeAction.addActionParameter(componentBean);
			searchResults.setRecordTypeManageActionConfig(recordTypeAction);
			searchResultConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		}

	}

	private void getViewActionconfig(GtnUIFrameWorkActionConfig viewActionConfig, String tableName, Boolean isInvalid,
			List<GtnWSTransactionColumnBean> viewModeComponents,
			List<GtnWSTransactionColumnBean> viewModeOrderComponents,
			List<GtnWSTransactionColumnBean> listViewComponent) {
		viewActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		viewActionConfig.addActionParameter(GtnUIFrameworkTransactionViewAction.class.getName());
		String layoutName = GtnTransactionUIConstants.VIEW_LAYOUT;
		if (totalCombination > 0) {
			for (String viewColumns : defaultVisibleColumn) {
				String viewCol = layoutName + viewColumns;
				defaultViewVisibleColumn.add(viewCol);
			}
			for (String viewColumn : viewVisibleColumn) {
				String viewCol = layoutName + viewColumn;
				viewVisibleColumnLayout.add(viewCol);
			}
			viewActionConfig.addActionParameter(viewVisibleColumnLayout);
		} else {
			onlyViewColumns.addAll(visibleColumns);
			removeColumnIdNoInView(onlyViewColumns, listViewComponent);
			addViewOnlyId(viewModeComponents, onlyViewColumns);
			viewActionConfig.addActionParameter(onlyViewColumns);
		}
		viewActionConfig.addActionParameter(tableName);
		viewActionConfig.addActionParameter(helperComponentID);
		viewActionConfig.addActionParameter(viewVisibleColumn);
		viewActionConfig.addActionParameter(defaultViewVisibleColumn);
		viewActionConfig.addActionParameter(viewModeComponents);
		viewActionConfig.addActionParameter(viewModeOrderComponents);
		viewActionConfig.addActionParameter(isInvalid);

	}

	private void getInvalidTableConfig(GtnUIFrameworkPagedTableConfig searchResults, String tableName,
			boolean viewEnabled, GtnUIFrameworkTransactionComponentTypeListBean componentBean, String invalidModule) {
		searchResults.setEditable(true);
		List<String> editablelist = Arrays.asList(GtnTransactionUIConstants.CHECK_RECORD);
		searchResults.setColumnCheckBoxId(GtnTransactionUIConstants.CHECK_RECORD);
		searchResults.setEditableColumnList(editablelist);
		searchResults.setEditableField(createTableFieldFactoryComponents(editablelist));
		GtnUIFrameWorkActionConfig checkAllAction = new GtnUIFrameWorkActionConfig();
		checkAllAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllAction.addActionParameter(GtnUIFrameworkTransactionTableCheckAllAction.class.getName());
		checkAllAction.addActionParameter(GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT);
		searchResults.addColumnCheckActionConfig(checkAllAction);
		if (!componentBean.getStaticComponent().isEmpty()) {
			List<String> generatedColumnlist = Arrays.asList(componentBean.getStaticComponent().get(0).getColumnID());
			searchResults.setGeneratedColumnList(generatedColumnlist);
			searchResults.setGeneratedColumn(createTableGeneratedColumnComponents(
					componentBean.getStaticComponent().get(0).getColumnID(), tableName, viewEnabled,
					componentBean.getViewModeComponents(), componentBean.getViewModeOrderComponents(),
					componentBean.getListViewComponent(), invalidModule));
		}

		GtnUIFrameWorkActionConfig recordTypeAction = new GtnUIFrameWorkActionConfig();
		recordTypeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		recordTypeAction.addActionParameter(GtnUIFrameWorkTransactioneRecordTypeAction.class.getName());
		recordTypeAction.addActionParameter(GtnTransactionUIConstants.SEARCH_TABLE_ID);
		recordTypeAction.addActionParameter(GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT);
		recordTypeAction.addActionParameter(componentBean);
		searchResults.setRecordTypeManageActionConfig(recordTypeAction);

	}

	@SuppressWarnings("unchecked")
	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String tableName,
			GtnUIFrameworkTransactionComponentTypeListBean componentBean, boolean isInvalid) {

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("gtnSearch01");
		searchButtonConfig.setComponentName("Search");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setParentComponentId(GtnTransactionUIConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(searchButtonConfig);
		visibleColumnArray = new List[totalCombination];
		visibleHeaderArray = new List[totalCombination];
		for (int i = 0; i < totalCombination; i++) {
			visibleColumnArray[i] = new ArrayList<>();
			visibleHeaderArray[i] = new ArrayList<>();
		}
		getMandatoryComponentList(componentBean.getSearchComponent());
		getDefaultVisibleColumnAndHeader(componentBean.getListViewComponent(),
				componentBean.getDefaultListViewComponent());
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		if (isInvalid) {
			GtnUIFrameWorkActionConfig resetBeanAction = new GtnUIFrameWorkActionConfig();
			resetBeanAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			resetBeanAction.addActionParameter(GtnUIFrameworkTransactionResetBeanAction.class.getName());
			resetBeanAction.addActionParameter(GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT);
			actionConfigList.add(resetBeanAction);
		}
		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(mandatoryComponentList);
		GtnUIFrameWorkActionConfig customAlertAction = new GtnUIFrameWorkActionConfig();
		customAlertAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAlertAction.addActionParameter(GtnUIFrameworkTransactionAlertAction.class.getName());
		customAlertAction.addActionParameter(mandatoryMsgHeaderList);
		customAlertAction.addActionParameter(mandatoryMsgBodyList);

		GtnUIFrameWorkActionConfig searchValidationActionConfig = new GtnUIFrameWorkActionConfig();
		searchValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		List searchComponentIdList = new ArrayList<>(searchComponentID);
		searchComponentIdList.addAll(descriptionList);
		searchValidationActionConfig.setFieldValues(searchComponentIdList);

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add("Please enter/select search criteria.");

		alertActionConfig.setActionParameterList(alertParamsList);
		searchValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig)));

		validationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(customAlertAction)));
		actionConfigList.add(validationActionConfig);
		actionConfigList.add(searchValidationActionConfig);

		if (totalCombination > 0) {
			Object tableNameForDemand = tableName;
			GtnUIFrameWorkActionConfig customResultViewAction = new GtnUIFrameWorkActionConfig();
			customResultViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			customResultViewAction.setActionParameterList(
					Arrays.asList(GtnUIFrameworkTransactionCustomResultViewAction.class.getName(), visibleColumnArray,
							visibleHeaderArray, GtnTransactionUIConstants.SEARCH_TABLE_ID, tableNameForDemand));
			actionConfigList.add(customResultViewAction);
		}
		if (componentBean.isOutBoundModule()) {
			GtnUIFrameWorkActionConfig outBoundAction = new GtnUIFrameWorkActionConfig();
			outBoundAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			outBoundAction.addActionParameter(GtnUIFrameworkTransactionResetBeanAction.class.getName());
			outBoundAction.addActionParameter(GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT);
			actionConfigList.add(outBoundAction);
		}
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnTransactionUIConstants.SEARCH_TABLE_ID);

		loadDataTableActionConfig.setActionParameterList(actionParams);

		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : componentBean.getSearchComponent()) {

			if (gtnWSTransactionColumnBean.isDemandTypeFlag()) {
				searchComponentID.remove(gtnWSTransactionColumnBean.getColumnID());
			}
		}
		loadDataTableActionConfig.setFieldValues(searchComponentID);
		loadDataTableActionConfig.setFieldDescription(descriptionList);

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationMessageAction = new GtnUIFrameWorkActionConfig();
		notificationMessageAction.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationMessageAction.addActionParameter(GtnTransactionUIConstants.SEARCH_TABLE_ID);
		actionConfigList.add(notificationMessageAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			List<GtnWSTransactionColumnBean> searchComponent, String parentComponentId, String alertMessage,
			boolean isReprocessLayout) {

		GtnUIFrameworkComponentConfig renameButtonConfig = new GtnUIFrameworkComponentConfig();
		renameButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		renameButtonConfig.setComponentId("gtnReset01");
		renameButtonConfig.setComponentName("Reset");
		renameButtonConfig.setAddToParent(true);
		renameButtonConfig.setAuthorizationIncluded(true);
		renameButtonConfig.setParentComponentId(parentComponentId);
		componentList.add(renameButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add(alertMessage);

		Map<String, Object> resetMap = new HashMap<>();
		for (GtnWSTransactionColumnBean component : searchComponent) {
			switch (component.getComponentType()) {
			case "text":
				resetMap.put(component.getColumnID(), component.getDefaultValue());
				break;
			case "combobox":
				resetMap.put(component.getColumnID(), null);
				break;
			case "date":
				resetMap.put(component.getColumnID(), null);
				break;

			default:
				break;
			}
		}
		if (isReprocessLayout) {
			resetMap.put(GtnTransactionUIConstants.SEARCH_TABLE_ID, null);
		}
		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);

		actionConfigList.add(resetActionConfig);

		GtnUIFrameWorkActionConfig resetBeanAction = new GtnUIFrameWorkActionConfig();
		resetBeanAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetBeanAction.addActionParameter(GtnUIFrameworkTransactionRefreshBeanAction.class.getName());
		resetBeanAction.addActionParameter(GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT);
		resetBeanAction.addActionParameter(GtnTransactionUIConstants.SEARCH_TABLE_ID);
		resetBeanAction.addActionParameter(GtnTransactionUIConstants.CHECK_RECORD);
		actionConfigList.add(resetBeanAction);

		renameButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addReprocessButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String wsReprocessURL,
			GtnUIFrameworkTransactionComponentTypeListBean componentBean, String portletName) {
		GtnUIFrameworkComponentConfig reprocessButtonConfig = new GtnUIFrameworkComponentConfig();
		reprocessButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reprocessButtonConfig.setComponentId("gtnReprocess01");
		reprocessButtonConfig.setComponentName("Reprocess");
		reprocessButtonConfig.setAddToParent(true);
		reprocessButtonConfig.setParentComponentId(GtnTransactionUIConstants.REPROCESS_BUTTON_LAYOUT);
		reprocessButtonConfig.setAuthorizationIncluded(true);
		List<GtnUIFrameWorkActionConfig> gtnUIFrameworkReprocessConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reprocessAlertAction = new GtnUIFrameWorkActionConfig();
		reprocessAlertAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reprocessAlertAction.addActionParameter(GtnFrameworkTransactionReprocessRemoveValidation.class.getName());
		reprocessAlertAction.addActionParameter(GtnTransactionUIConstants.REPROCESS);
		reprocessAlertAction.addActionParameter(GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT);
		reprocessAlertAction.addActionParameter(componentBean.isOutBoundModule());
		reprocessAlertAction.addActionParameter(portletName);
		gtnUIFrameworkReprocessConfigList.add(reprocessAlertAction);
		List<GtnUIFrameWorkActionConfig> reprocess = new ArrayList<>();
		Object resultpanelLayout = GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT;
		GtnUIFrameWorkActionConfig reprocessAction = new GtnUIFrameWorkActionConfig();
		reprocessAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reprocessAction.setActionParameterList(Arrays.asList(
				GtnUIFrameworkTransactionReprocessRemoveAction.class.getName(), GtnTransactionUIConstants.REPROCESS,
				resultpanelLayout, GtnTransactionUIConstants.SEARCH_TABLE_ID, wsReprocessURL,
				componentBean.isOutBoundModule(), componentBean.getStagingInsertColumns(),
				componentBean.getStagingUpdateColumns(), componentBean.getStagingUpdateColumnsValues()));
		reprocess.add(reprocessAction);
		GtnUIFrameWorkActionConfig resetBeanAction = new GtnUIFrameWorkActionConfig();
		resetBeanAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetBeanAction.addActionParameter(GtnUIFrameworkTransactionRefreshBeanAction.class.getName());
		resetBeanAction.addActionParameter(GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT);
		resetBeanAction.addActionParameter(GtnTransactionUIConstants.SEARCH_TABLE_ID);
		resetBeanAction.addActionParameter(GtnTransactionUIConstants.CHECK_RECORD);
		reprocess.add(resetBeanAction);

		GtnUIFrameWorkActionConfig reprocessConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		reprocessConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> deleteConfirmationAlertParams = new ArrayList<>();
		deleteConfirmationAlertParams.add("Confirmation");
		deleteConfirmationAlertParams.add("Are you sure you want to Reprocess the selected record(s)  ?");
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnTransactionUIConstants.SEARCH_TABLE_ID);

		loadDataTableActionConfig.setActionParameterList(actionParams);

		loadDataTableActionConfig.setFieldValues(searchComponentID);
		loadDataTableActionConfig.setFieldDescription(descriptionList);

		reprocess.add(loadDataTableActionConfig);
		deleteConfirmationAlertParams.add(reprocess);
		reprocessConfirmationActionConfig.setActionParameterList(deleteConfirmationAlertParams);
		gtnUIFrameworkReprocessConfigList.add(reprocessConfirmationActionConfig);
		reprocessButtonConfig.setGtnUIFrameWorkActionConfigList(gtnUIFrameworkReprocessConfigList);
		componentList.add(reprocessButtonConfig);

	}

	private void addRemoveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnUIFrameworkTransactionComponentTypeListBean componentBean, String portletName) {
		GtnUIFrameworkComponentConfig resetButtonConfig = new GtnUIFrameworkComponentConfig();
		resetButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentId("gtnRemove01");
		resetButtonConfig.setComponentName("Remove");
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setAddToParent(true);
		resetButtonConfig.setParentComponentId(GtnTransactionUIConstants.REPROCESS_BUTTON_LAYOUT);
		Object remove = GtnTransactionUIConstants.REMOVE;
		List<GtnUIFrameWorkActionConfig> gtnUIFrameworkRemoveConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig removeAlertAction = new GtnUIFrameWorkActionConfig();
		removeAlertAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeAlertAction.setActionParameterList(
				Arrays.asList(GtnFrameworkTransactionReprocessRemoveValidation.class.getName(), remove,
						GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT, componentBean.isOutBoundModule(), portletName));
		gtnUIFrameworkRemoveConfigList.add(removeAlertAction);
		List<GtnUIFrameWorkActionConfig> removeActionList = new ArrayList<>();

		GtnUIFrameWorkActionConfig removeAction = new GtnUIFrameWorkActionConfig();
		removeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeAction
				.setActionParameterList(Arrays.asList(GtnUIFrameworkTransactionReprocessRemoveAction.class.getName(),
						remove, GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT,
						GtnTransactionUIConstants.SEARCH_TABLE_ID, componentBean.getReprocessingWebServiceURL(),
						componentBean.isOutBoundModule(), componentBean.getStagingInsertColumns(),
						componentBean.getStagingUpdateColumns(), componentBean.getStagingUpdateColumnsValues()));
		removeActionList.add(removeAction);

		GtnUIFrameWorkActionConfig removeConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		removeConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> removeConfirmationAlertParams = new ArrayList<>();
		removeConfirmationAlertParams.add("Confirmation");
		removeConfirmationAlertParams.add(
				"Are you sure you want to Remove the selected record(s)?  Removed records will no longer be visible in the Invalid Table search Results ?");

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnTransactionUIConstants.SEARCH_TABLE_ID);

		loadDataTableActionConfig.setActionParameterList(actionParams);

		loadDataTableActionConfig.setFieldValues(searchComponentID);
		loadDataTableActionConfig.setFieldDescription(descriptionList);

		removeActionList.add(loadDataTableActionConfig);
		removeConfirmationAlertParams.add(removeActionList);
		removeConfirmationActionConfig.setActionParameterList(removeConfirmationAlertParams);
		gtnUIFrameworkRemoveConfigList.add(removeConfirmationActionConfig);

		resetButtonConfig.setGtnUIFrameWorkActionConfigList(gtnUIFrameworkRemoveConfigList);

		componentList.add(resetButtonConfig);

	}

	private List<GtnUIFrameworkComponentConfig> createTableGeneratedColumnComponents(String propertyId,
			String tableName, boolean viewEnabled, List<GtnWSTransactionColumnBean> viewModeComponents,
			List<GtnWSTransactionColumnBean> viewModeOrderComponents,
			List<GtnWSTransactionColumnBean> listViewComponent, String invalidModule) {
		List<GtnUIFrameworkComponentConfig> generatedColumnComponent = new ArrayList<>();

		GtnUIFrameworkComponentConfig hylinkButton = new GtnUIFrameworkComponentConfig();
		hylinkButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		GtnUIFrameworkButtonConfig buttonConfig = new GtnUIFrameworkButtonConfig();
		buttonConfig.setButtonType(GtnUiFrameworkButtonType.LINK_BUTTON);
		hylinkButton.setButtonConfig(buttonConfig);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkTransactionGeneratedCoumnAction.class.getName());
		customAction.addActionParameter(propertyId);
		hylinkButton.setGtnUIFrameWorkColumnGeneratorConfig(customAction);
		if (viewEnabled) {
			Object invalidModuleName = "Invalid Table : " + invalidModule;
			GtnUIFrameWorkActionConfig popupAction = new GtnUIFrameWorkActionConfig();
			popupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
			popupAction.setActionParameterList(Arrays.asList("V002", invalidModuleName, "60%", null, null, null,
					GtnFrameworkCssConstants.AUTO_HEIGHT_FOR_WINDOW));
			hylinkButton.addGtnUIFrameWorkActionConfig(popupAction);
			GtnUIFrameWorkActionConfig viewAction = new GtnUIFrameWorkActionConfig();
			viewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			viewAction.addActionParameter(GtnUIFrameworkTransactionInvalidViewLoadAction.class.getName());
			viewAction.setFieldValues(Arrays.asList("intefaceName", "transactionViewLayout"));
			hylinkButton.addGtnUIFrameWorkActionConfig(viewAction);
			GtnUIFrameWorkActionConfig viewActionConfig = new GtnUIFrameWorkActionConfig();
			getViewActionconfig(viewActionConfig, tableName, Boolean.TRUE, viewModeComponents, viewModeOrderComponents,
					listViewComponent);
			hylinkButton.addGtnUIFrameWorkActionConfig(viewActionConfig);
		}
		generatedColumnComponent.add(hylinkButton);

		return generatedColumnComponent;
	}

	private List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds) {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();
		Object checkAll = GtnTransactionUIConstants.CHECK_RECORD;
		for (String propertyId : propertyIds) {
			GtnUIFrameworkComponentType gtnUIFrameworkComponentType = GtnTransactionUIConstants.CHECK_RECORD
					.equals(propertyId) ? GtnUIFrameworkComponentType.CHECKBOX : GtnUIFrameworkComponentType.BUTTON;
			GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
			fieldConfig.setComponentType(gtnUIFrameworkComponentType);

			List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);
			customAction.addActionParameter(GtnUIFrameworkTransactionTableCheckAction.class.getName());
			customAction.addActionParameter(GtnTransactionUIConstants.SEARCH_TABLE_ID);
			customAction.addActionParameter(GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT);
			customAction.addActionParameter(checkAll);
			actionConfigList.add(customAction);
			fieldConfig.setGtnUIFrameWorkValueChangeActionConfigList(actionConfigList);

			editableFields.add(fieldConfig);
		}

		return editableFields;
	}

	private void generateViewOnlyColumns(List<GtnWSTransactionColumnBean> transactionBeanList,
			List<GtnWSTransactionColumnBean> listViewComponent, List<GtnUIFrameworkComponentConfig> viewComponentList,
			boolean viewIndexFlag, String portletName, boolean isInvalid) {
		List<GtnWSTransactionColumnBean> viewComponent = new ArrayList<>(listViewComponent);

		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : transactionBeanList) {
			if (gtnWSTransactionColumnBean.isViewOnlyColumn()) {
				viewComponent.add(gtnWSTransactionColumnBean);
			}
		}
		if (portletName.contains("ActualsMaster")) {
			generateTabsheetView(viewComponent, viewComponentList, "ActualsMaster", isInvalid);
		} else {
			generateViewMode(viewComponent, viewComponentList, viewIndexFlag, isInvalid);
		}

	}

	private void generateViewMode(List<GtnWSTransactionColumnBean> viewComponent,
			List<GtnUIFrameworkComponentConfig> viewComponentList, boolean viewIndexFlag, boolean isInvalid) {
		List<GtnWSTransactionColumnBean> viewComponents;
		if (viewIndexFlag) {
			GtnWSTransactionColumnBean[] viewFieldArray = new GtnWSTransactionColumnBean[viewComponent.size()];
			for (GtnWSTransactionColumnBean component : viewComponent) {
				if (component.isViewFlag()) {
					viewFieldArray[component.getViewModeIndex() - 1] = component;
				}

			}
			viewComponents = Arrays.asList(viewFieldArray);
		} else {
			viewComponents = new ArrayList<>(viewComponent);
		}
		addViewFieldComponent(viewComponentList, GtnTransactionUIConstants.VIEW_LAYOUT, viewComponents, isInvalid);
	}

	private void addViewFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String layoutName,
			List<GtnWSTransactionColumnBean> viewComponent, boolean isInvalid) {
		String viewLayoutName = GtnTransactionUIConstants.VIEW_LAYOUT;
		for (GtnWSTransactionColumnBean component : viewComponent) {
			if (component != null && component.isViewFlag()) {
				if (GtnTransactionUIConstants.JSON_DATEFIELD.equals(component.getComponentType())) {

					addDateField(componentList, component, layoutName, viewLayoutName,
							GtnTransactionUIConstants.TRANSACTION_VIEW, false, isInvalid);

				} else {
					addTextField(componentList, component, layoutName, viewLayoutName,
							GtnTransactionUIConstants.TRANSACTION_VIEW, false);

				}
			}

		}

	}

	private void addViewOnlyId(List<GtnWSTransactionColumnBean> viewModeComponents2, List<Object> visibleColumns2) {

		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : viewModeComponents2) {
			if (gtnWSTransactionColumnBean.isViewOnlyColumn()) {
				visibleColumns2.add(gtnWSTransactionColumnBean.getColumnID());
			}
		}

	}

	private void removeColumnIdNoInView(List<Object> visibleColumns2,
			List<GtnWSTransactionColumnBean> listViewComponent) {
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : listViewComponent) {
			if (gtnWSTransactionColumnBean.getIsResultView() && !gtnWSTransactionColumnBean.isViewFlag()) {
				visibleColumns2.remove(gtnWSTransactionColumnBean.getColumnID());
			}
		}

	}

	private void generateTabsheetView(List<GtnWSTransactionColumnBean> viewComponent,
			List<GtnUIFrameworkComponentConfig> viewComponentList, String portletName, boolean isInvalid) {
		GtnWSTransactionColumnBean[] viewFieldArray = new GtnWSTransactionColumnBean[viewComponent.size()];
		for (GtnWSTransactionColumnBean listComponent : viewComponent) {
			if (listComponent.getViewModeIndex() > 0) {
				viewFieldArray[listComponent.getViewModeIndex() - 1] = listComponent;
			}
		}

		GtnUIFrameworkTransactionTabsheetBean tabsheetBean = getTransactioTabsheenBeanFromJson(portletName);
		addViewFieldLayout(viewComponentList, tabsheetBean, Arrays.asList(viewFieldArray), isInvalid);
	}

	private void addViewFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnUIFrameworkTransactionTabsheetBean tabsheetBean, List<GtnWSTransactionColumnBean> viewComponent,
			boolean isInvalid) {

		GtnUIFrameworkComponentConfig tabSheetConfig = new GtnUIFrameworkComponentConfig();
		tabSheetConfig.setComponentType(GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentId("transacTionViewTabsheet");
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		tabSheetConfig.setAddToParent(true);
		tabSheetConfig.setParentComponentId("transactionViewLayout");
		componentList.add(tabSheetConfig);
		tabSheetConfig.setGtnTabSheetConfigList(generateTabsheet(tabsheetBean, viewComponent, isInvalid));
	}

	private List<GtnUIFrameworkTabConfig> generateTabsheet(GtnUIFrameworkTransactionTabsheetBean tabsheetBean,
			List<GtnWSTransactionColumnBean> viewComponent, boolean isInvalid) {
		List<GtnUIFrameworkTabConfig> tabsheetConfigList = new ArrayList<>();
		int start = 0;
		int noOfComponents = 0;
		for (int i = 0; i < tabsheetBean.getNoOfTabs(); i++) {
			GtnUIFrameworkTabConfig tabSheetConfig = new GtnUIFrameworkTabConfig();
			tabSheetConfig.setComponentId(tabsheetBean.getTabNameArray()[i].toLowerCase());
			tabSheetConfig.setTabCaption(tabsheetBean.getTabNameArray()[i]);
			List<GtnUIFrameworkComponentConfig> tabConfigList = new ArrayList<>();
			tabSheetConfig.setTabLayoutComponentConfigList(tabConfigList);
			if (isInvalid) {
				noOfComponents = tabsheetBean.getNoOfInvalidElementsInTabArray()[i];
			} else {
				noOfComponents = tabsheetBean.getNoOfElementsInTabArray()[i];
			}
			addTabLayout(tabSheetConfig, start, start + noOfComponents - 1, viewComponent, isInvalid, tabConfigList);
			start += noOfComponents;
			tabsheetConfigList.add(tabSheetConfig);
		}
		return tabsheetConfigList;
	}

	private void addTabLayout(GtnUIFrameworkTabConfig tabSheetConfig, int start, int offset,
			List<GtnWSTransactionColumnBean> viewComponent, boolean isInvalid,
			List<GtnUIFrameworkComponentConfig> tabConfigList) {

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("tabsheetLayout" + tabSheetConfig.getComponentId());
		gtnLayout.setTabComponent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		tabConfigList.add(gtnLayout);

		GtnUIFrameworkLayoutConfig cssLayout = new GtnUIFrameworkLayoutConfig();
		cssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig cssGtnLayout = new GtnUIFrameworkComponentConfig();
		cssGtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		cssGtnLayout.setComponentId("tabSheetCssLayout" + tabSheetConfig.getComponentId());

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		cssGtnLayout.setComponentStyle(styleList);
		cssGtnLayout.setAddToParent(true);
		cssGtnLayout.setParentComponentId("tabsheetLayout" + tabSheetConfig.getComponentId());
		cssGtnLayout.setGtnLayoutConfig(cssLayout);
		tabConfigList.add(cssGtnLayout);
		addViewFieldComponent(tabConfigList, start, offset, cssGtnLayout.getComponentId(), viewComponent, isInvalid);
	}

	private void addViewFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, int start, int offset,
			String parent, List<GtnWSTransactionColumnBean> viewComponent, boolean isInvalid) {
		String layoutName = GtnTransactionUIConstants.VIEW_LAYOUT;
		for (int i = start; i <= offset; i++) {
			GtnWSTransactionColumnBean component = viewComponent.get(i);
			if (component.isViewFlag()) {
				if (GtnTransactionUIConstants.JSON_DATEFIELD.equals(component.getComponentType())) {

					addDateField(componentList, component, parent, layoutName,
							GtnTransactionUIConstants.TRANSACTION_VIEW, false, isInvalid);
				} else {

					addTextField(componentList, component, parent, layoutName,
							GtnTransactionUIConstants.TRANSACTION_VIEW, false);

				}
			}

		}

	}

	private GtnUIFrameworkTransactionTabsheetBean getTransactioTabsheenBeanFromJson(String portletName) {
		return new GtnFrameworkJSONReader().getTypeConfigFromJson(GtnTransactionUIConstants.TRANSACTION_JSON_PATH,
				portletName + GtnTransactionUIConstants.TRANSACTION_JSON_VIEW + ".json",
				GtnUIFrameworkTransactionTabsheetBean.class);
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String tableName,
			GtnUIFrameworkTransactionComponentTypeListBean componentBean) {
		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAddToParent(true);
		excelButtonConfig.setParentComponentId("gtnExcelButtonlayout");
		componentList.add(excelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnUIFrameworkExcelButtonConfig.setIsTreeTable(false);
		gtnUIFrameworkExcelButtonConfig.setExportFileName(
				GtnFrameworkTransactionExcelName.valueOf(tableName.toUpperCase(Locale.ENGLISH)).getTableName());

		gtnUIFrameworkExcelButtonConfig.setExportFromTable(true);
		gtnUIFrameworkExcelButtonConfig.setExportTableId(GtnTransactionUIConstants.SEARCH_TABLE_ID);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelAction.addActionParameter(componentBean.getFormatterList());
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void getCustomFilter(String tableName, GtnUIFrameworkPagedTableConfig searchResults) {
		if (tableName.contains("GlBalance")) {
			Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
			GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			customFilterConfig.setPropertId("isActive");
			customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			customFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			customFilterComponentConfig.setComponentId("customFilterComboBox");
			customFilterComponentConfig.setComponentName("customFilterComboBox");
			customFilterComponentConfig.getGtnComboboxConfig().setItemValues(Arrays.asList("YES", "NO"));
			customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
			customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
			searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		}

	}

	private void getMandatoryComponentList(List<GtnWSTransactionColumnBean> searchComponent) {
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : searchComponent) {
			if (gtnWSTransactionColumnBean.isMandatory()) {
				mandatoryComponentList.add(gtnWSTransactionColumnBean.getColumnID());
				mandatoryMsgHeaderList.add(gtnWSTransactionColumnBean.getMessageName());
				mandatoryMsgBodyList.add(gtnWSTransactionColumnBean.getMessageBody());

			}
		}
	}

	private void getDefaultVisibleColumnAndHeader(List<GtnWSTransactionColumnBean> listViewComponent,
			List<GtnWSTransactionColumnBean> defaultListViewComponent) {
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : listViewComponent) {
			if (totalCombination > 0) {
				visibleColumnArray[gtnWSTransactionColumnBean.getCombination() - 1]
						.add(gtnWSTransactionColumnBean.getColumnID());
				visibleHeaderArray[gtnWSTransactionColumnBean.getCombination() - 1]
						.add(gtnWSTransactionColumnBean.getColumnName());
			}
		}
		for (GtnWSTransactionColumnBean gtnWSTransactionColumnBean : defaultListViewComponent) {
			defaultVisibleColumn.add(gtnWSTransactionColumnBean.getColumnID());
			defaultColumnHeader.add(gtnWSTransactionColumnBean.getColumnName());
		}

	}

	private void getConfigurationForModules(GtnUIFrameworkTransactionComponentTypeListBean componentBean,
			List<GtnWSTransactionColumnBean> moduleComponentList) {
		for (GtnWSTransactionColumnBean gtnWSTransactionModuleBean : moduleComponentList) {
			componentBean.setOutBoundModule(gtnWSTransactionModuleBean.isOutBoundModule());
			componentBean.setReprocessingWebServiceURL(gtnWSTransactionModuleBean.getReprocessingWebServiceURL());
			componentBean.setStagingInsertColumns(gtnWSTransactionModuleBean.getStagingInsertColumns());
			componentBean.setStagingUpdateColumns(gtnWSTransactionModuleBean.getStagingUpdateColumns());
			componentBean.setStagingUpdateColumnsValues(gtnWSTransactionModuleBean.getStagingUpdateColumnsValues());
		}
	}

	private void addOutBoundModuleCriteria(List<GtnUIFrameworkComponentConfig> componentList,
			GtnUIFrameworkTransactionComponentTypeListBean componentBean, String portletName) {
		if (componentBean.isOutBoundModule()) {
			addResetButtonComponent(componentList, componentBean.getSearchComponent(),
					GtnTransactionUIConstants.REPROCESS_BUTTON_LAYOUT,
					GtnTransactionUIConstants.RESET_REPROCESS_ALERT_MSG, componentBean.isOutBoundModule());
			addReprocessButtonComponent(componentList, componentBean.getReprocessingWebServiceURL(), componentBean,
					portletName);
		}
	}

	private void setDecimalFormatLogic(GtnWSTransactionColumnBean gtnWSTransactionColumnBean,
			GtnUIFrameworkTransactionComponentTypeListBean componentBean) {
		if (gtnWSTransactionColumnBean.isDecimalFormatNeeded()) {
			componentBean.putFormatterMap(gtnWSTransactionColumnBean.getColumnID(),
					gtnWSTransactionColumnBean.getPattern());
		}
	}

}
