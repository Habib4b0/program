package com.stpl.gtn.gtn2o.config;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textarea.GtnUIFrameworkTextAreaConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkComponentConfigProvider {

	private static volatile GtnFrameworkComponentConfigProvider commonConfigProvider = null;

	private GtnFrameworkComponentConfigProvider() {

	}

	public static GtnFrameworkComponentConfigProvider getInstance() {
		if (commonConfigProvider == null) {
			synchronized (GtnFrameworkComponentConfigProvider.class) {
				if (commonConfigProvider == null) {
					commonConfigProvider = new GtnFrameworkComponentConfigProvider();
				}
			}
		}
		return commonConfigProvider;
	}

	public GtnUIFrameworkComponentConfig getUIFrameworkLayoutComponentConfig(String componentId, boolean hasparent,
			String parentComponentId, GtnUIFrameworkLayoutType layoutType, GtnUIFrameworkComponentType componentType) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = getUIFrameworkComponentConfig(componentId, hasparent,
				parentComponentId, componentType);
		gtnLayoutConfig.setGtnLayoutConfig(getUIFrameworkLayoutConfig(layoutType));
		return gtnLayoutConfig;
	}

	public GtnUIFrameworkLayoutConfig getUIFrameworkLayoutConfig(final GtnUIFrameworkLayoutType layoutType) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(layoutType);
		return layout;
	}

	public GtnUIFrameWorkActionConfig getUIFrameworkActionConfig(final GtnUIFrameworkActionType type) {
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionType(type);
		return actionConfig;
	}

	public GtnUIFrameWorkActionConfig getUIFrameworkActionConfig(final GtnUIFrameworkActionType type,
			Object... actionParameters) {
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionType(type);
		for (int i = 0; i < actionParameters.length; i++) {
			actionConfig.addActionParameter(actionParameters[i]);
		}
		return actionConfig;
	}

	public GtnUIFrameworkComponentConfig getUIFrameworkComponentConfig(String componentId, boolean hasparent,
			String parentComponentId, GtnUIFrameworkComponentType componentType) {
		GtnUIFrameworkComponentConfig gtnComponentConfig = new GtnUIFrameworkComponentConfig();
		gtnComponentConfig.setComponentType(componentType);
		gtnComponentConfig.setComponentId(componentId);
		gtnComponentConfig.setParentComponentId(parentComponentId);
		gtnComponentConfig.setAddToParent(hasparent);
		return gtnComponentConfig;
	}

	public GtnUIFrameworkComponentConfig getHorizontalLayoutConfig(String componentId, boolean hasparent,
			String parentComponentId) {
		return getUIFrameworkLayoutComponentConfig(componentId, hasparent, parentComponentId,
				GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);

	}

	public GtnUIFrameworkComponentConfig getVerticalLayoutConfig(String componentId, boolean hasparent,
			String parentComponentId) {
		return getUIFrameworkLayoutComponentConfig(componentId, hasparent, parentComponentId,
				GtnUIFrameworkLayoutType.VERTICAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);
	}

	public GtnUIFrameworkComponentConfig getCssLayoutConfig(String componentId, boolean hasparent,
			String parentComponentId) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = getUIFrameworkLayoutComponentConfig(componentId, hasparent,
				parentComponentId, GtnUIFrameworkLayoutType.CSS_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayoutConfig.setSpacing(Boolean.TRUE);
		return gtnLayoutConfig;
	}

	public GtnUIFrameworkComponentConfig getGtnCssLayoutConfig(String componentId, boolean hasparent,
			String parentComponentId, GtnUIFrameworkLayoutType layoutType) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = getUIFrameworkLayoutComponentConfig(componentId, hasparent,
				parentComponentId, layoutType, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayoutConfig.setSpacing(Boolean.TRUE);
		return gtnLayoutConfig;
	}

	public GtnUIFrameworkComponentConfig getRootLayoutConfig(String componentId, GtnUIFrameworkLayoutType layoutType,
			boolean istabComponent) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = getUIFrameworkLayoutComponentConfig(componentId, false, null,
				layoutType, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayoutConfig.setSpacing(Boolean.TRUE);
		gtnLayoutConfig.setTabComponent(istabComponent);
		return gtnLayoutConfig;
	}

	public GtnUIFrameworkComponentConfig getPanelConfig(String componentId, boolean hasparent,
			String parentComponentId) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = getUIFrameworkComponentConfig(componentId, hasparent,
				parentComponentId, GtnUIFrameworkComponentType.PANEL);
		gtnPanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		return gtnPanelConfig;
	}

	public GtnUIFrameworkComboBoxConfig getComboBoxConfig(final String type, final String url) {
		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setComboBoxType(type);
		comboBoxConfig.setLoadingUrl(url);
		return comboBoxConfig;
	}

	public GtnUIFrameworkTextBoxConfig getTextBoxConfig(final boolean enabled, final boolean required,
			final boolean immediate) {
		GtnUIFrameworkTextBoxConfig textBoxConfig = new GtnUIFrameworkTextBoxConfig();
		textBoxConfig.setEnable(enabled);
		textBoxConfig.setRequired(required);
		textBoxConfig.setImmediate(immediate);
		return textBoxConfig;
	}

	public GtnUIFrameworkValidationConfig getValidationConfig(
			List<GtnUIFrameworkConditionalValidationType> conditionList, boolean hasRegxValidation,
			String regxValidationMessage, String formatString) {
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(conditionList);
		validationConfig.setAttachRegxValidatior(hasRegxValidation);
		validationConfig.setRegxValidationMessage(regxValidationMessage);
		validationConfig.setFormatString(formatString);
		return validationConfig;
	}

	public GtnUIFrameworkOptionGroupConfig getOptionGroupConfig(List<String> itemValues, Object defaultSelection,
			boolean valuesFromService) {
		GtnUIFrameworkOptionGroupConfig optionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		optionGroupConfig.setItemValues(itemValues);
		optionGroupConfig.setDefaultSelection(defaultSelection);
		optionGroupConfig.setValuesFromService(valuesFromService);
		return optionGroupConfig;
	}

	public GtnUIFrameworkViewConfig getViewConfig(String viewName, String viewId, boolean isDefaultView) {

		GtnUIFrameworkViewConfig viewConfig = new GtnUIFrameworkViewConfig();
		viewConfig.setViewName(viewName);
		viewConfig.setViewId(viewId);
		viewConfig.setDefaultView(isDefaultView);

		return viewConfig;
	}

	public GtnUIFrameworkTabConfig getTabConfig(String componentId, String tabCaption) {

		GtnUIFrameworkTabConfig tabConfig = new GtnUIFrameworkTabConfig();
		tabConfig.setComponentId(componentId);
		tabConfig.setTabCaption(tabCaption);
		return tabConfig;
	}

	public GtnUIFrameworkExcelButtonConfig getExcelBtnconfig(String exportFileName, boolean exportFromTable,
			String exportTableId, boolean writeFileInWebService) {

		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnUIFrameworkExcelButtonConfig.setExportFileName(exportFileName);
		gtnUIFrameworkExcelButtonConfig.setExportFromTable(exportFromTable);
		gtnUIFrameworkExcelButtonConfig.setExportTableId(exportTableId);
		gtnUIFrameworkExcelButtonConfig.setWriteFileInWebService(writeFileInWebService);
		return gtnUIFrameworkExcelButtonConfig;

	}

	public GtnUIFrameworkPagedTableConfig getPagedTableConfig(boolean filterBar, boolean selectable, String countUrl,
			String resultSetUrl, String moduleName, String queryName) {

		GtnUIFrameworkPagedTableConfig tableConfig = new GtnUIFrameworkPagedTableConfig();
		tableConfig.setFilterBar(filterBar);
		tableConfig.setSelectable(selectable);
		tableConfig.setCountUrl(countUrl);
		tableConfig.setResultSetUrl(resultSetUrl);
		tableConfig.setModuleName(moduleName);
		tableConfig.setQueryName(queryName);
		return tableConfig;
	}

	public GtnUIFrameworkDateFieldConfig getDateFieldConfig(boolean isEnable, boolean isRequired,
			List<GtnUIFrameWorkActionConfig> actionConfigList) {
		GtnUIFrameworkDateFieldConfig datefieldConfig = new GtnUIFrameworkDateFieldConfig();
		datefieldConfig.setEnable(isEnable);
		datefieldConfig.setRequired(isRequired);
		datefieldConfig.setValueChangeActionConfigList(actionConfigList);
		return datefieldConfig;
	}

	public GtnUIFrameworkValidationConfig getRegexOnlyValidationConfig(String formatString,
			String regexValidationMessage) {
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setFormatString(formatString);
		gtnUIFrameworkValidationConfig.setAttachRegxValidatior(true);
		gtnUIFrameworkValidationConfig.setRegxValidationMessage(regexValidationMessage);
		return gtnUIFrameworkValidationConfig;
	}

	public GtnUIFrameworkTextAreaConfig getTextAreaConfig(final String inputPrompt, final boolean enabled,
			final int rows) {
		GtnUIFrameworkTextAreaConfig textAreaConfig = new GtnUIFrameworkTextAreaConfig();
		textAreaConfig.setInputPrompt(inputPrompt);
		textAreaConfig.setEnable(enabled);
		textAreaConfig.setRows(rows);
		return textAreaConfig;
	}

	public GtnUIFrameworkValidationConfig getValidationConfigForConditionList(
			List<GtnUIFrameworkConditionalValidationType> conditionList) {
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(conditionList);
		return validationConfig;
	}
}
