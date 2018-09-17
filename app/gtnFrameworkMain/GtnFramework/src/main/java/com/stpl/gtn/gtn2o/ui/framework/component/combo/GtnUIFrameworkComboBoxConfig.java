package com.stpl.gtn.gtn2o.ui.framework.component.combo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;

public class GtnUIFrameworkComboBoxConfig {

	private String loadingUrl;
	private String comboBoxType;
	private String valueChangeListenerClassName;
	@SuppressWarnings("rawtypes")
	private List itemValues;
	private List<String> itemCaptionValues;
	private boolean hasDefaultValue;
	private GtnUiFrameworkComboBoxSourceType sourceType = GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SERVICE;
	private String sourceComboboxId;
	private boolean inclusionOfSelected = true;
	private boolean exclusionOfLast = false;
	private String defaultDesc;
	private boolean isFilterField;
	private boolean isEnabled = true;
	private boolean required;
	private String requiredMessage;
	private GtnUIFrameworkComboboxSourceSubsetType subsetType = GtnUIFrameworkComboboxSourceSubsetType.BEFORE_SELECTED;
	private Object defaultValue = null;
	private boolean newItemsAllowed = false;
	private GtnUIFrameWorkActionConfig newItemHandlerActionConfig;
	private boolean isIntegerItemCode = true;
	private boolean isReadOnly = false;
	private String moduleName = "";
	private boolean isLoadAtStart = false;
	private boolean isFocus = false;
	private String actualWsUrl;
	private String actualWsContext;
	private String actualWsModuleName;

        
        public boolean isFocus() {
		return isFocus;
	}

	public void setFocus(boolean isFocus) {
		this.isFocus = isFocus;
	}

	public boolean isIsLoadAtStart() {
		return isLoadAtStart;
	}

	public void setIsLoadAtStart(boolean isLoadAtStart) {
		this.isLoadAtStart = isLoadAtStart;
	}

	public GtnUIFrameworkComboBoxConfig() {
		super();
	}

	public String getLoadingUrl() {
		return loadingUrl;
	}

	public void setLoadingUrl(String loadingUrl) {
		this.loadingUrl = loadingUrl;
	}

	public String getComboBoxType() {
		return comboBoxType;
	}

	public void setComboBoxType(String comboBoxType) {
		this.comboBoxType = comboBoxType;
	}

	public String getValueChangeListenerClassName() {
		return valueChangeListenerClassName;
	}

	public void setValueChangeListenerClassName(String valueChangeListenerClassName) {
		this.valueChangeListenerClassName = valueChangeListenerClassName;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getItemValues() {
		return itemValues == null ? itemValues : Collections.unmodifiableList(itemValues);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setItemValues(List itemValues) {
		this.itemValues = new ArrayList<>(itemValues);
	}

	public boolean isHasDefaultValue() {
		return hasDefaultValue;
	}

	public void setHasDefaultValue(boolean hasDefaultValue) {
		this.hasDefaultValue = hasDefaultValue;
	}

	public GtnUiFrameworkComboBoxSourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(GtnUiFrameworkComboBoxSourceType sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceComboboxId() {
		return sourceComboboxId;
	}

	public void setSourceComboboxId(String sourceComboboxId) {
		this.sourceComboboxId = sourceComboboxId;
	}

	public boolean isInclusionOfSelected() {
		return inclusionOfSelected;
	}

	public void setInclusionOfSelected(boolean inclusionOfSelected) {
		this.inclusionOfSelected = inclusionOfSelected;
	}

	public boolean isExclusionOfLast() {
		return exclusionOfLast;
	}

	public void setExclusionOfLast(boolean exclusionOfLast) {
		this.exclusionOfLast = exclusionOfLast;
	}

	public GtnUIFrameworkComboboxSourceSubsetType getSubsetType() {
		return subsetType;
	}

	public void setSubsetType(GtnUIFrameworkComboboxSourceSubsetType subsetType) {
		this.subsetType = subsetType;
	}

	public String getDefaultDesc() {
		return defaultDesc;
	}

	public void setDefaultDesc(String defaultDesc) {
		this.defaultDesc = defaultDesc;
	}

	public boolean isIsFilterField() {
		return isFilterField;
	}

	public void setIsFilterField(boolean isFilterField) {
		this.isFilterField = isFilterField;
	}

	public boolean isIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getRequiredMessage() {
		return requiredMessage;
	}

	public void setRequiredMessage(String requiredMessage) {
		this.requiredMessage = requiredMessage;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isNewItemsAllowed() {
		return newItemsAllowed;
	}

	public void setNewItemsAllowed(boolean newItemsAllowed) {
		this.newItemsAllowed = newItemsAllowed;
	}

	public GtnUIFrameWorkActionConfig getNewItemHandlerActionConfig() {
		return newItemHandlerActionConfig;
	}

	public void setNewItemHandlerActionConfig(GtnUIFrameWorkActionConfig newItemHandlerActionConfig) {
		this.newItemHandlerActionConfig = newItemHandlerActionConfig;
	}

	public List<String> getItemCaptionValues() {
		return itemCaptionValues == null ? null : Collections.unmodifiableList(itemCaptionValues);
	}

	public void setItemCaptionValues(List<String> itemCaptionValues) {
		this.itemCaptionValues = new ArrayList<>(itemCaptionValues);
	}

	public boolean isIntegerItemCode() {
		return isIntegerItemCode;
	}

	public void setIntegerItemCode(boolean isIntegerItemCode) {
		this.isIntegerItemCode = isIntegerItemCode;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getActualWsUrl() {
		return actualWsUrl;
	}

	public void setActualWsUrl(String actualWsUrl) {
		this.actualWsUrl = actualWsUrl;
	}

	public String getActualWsContext() {
		return actualWsContext;
	}

	public void setActualWsContext(String actualWsContext) {
		this.actualWsContext = actualWsContext;
	}

	public String getActualWsModuleName() {
		return actualWsModuleName;
	}

	public void setActualWsModuleName(String actualWsModuleName) {
		this.actualWsModuleName = actualWsModuleName;
	}

}
