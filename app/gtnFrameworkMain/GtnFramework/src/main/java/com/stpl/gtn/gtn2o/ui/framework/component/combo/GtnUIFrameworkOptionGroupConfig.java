package com.stpl.gtn.gtn2o.ui.framework.component.combo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnUIFrameworkOptionGroupConfig {

	private String loadingUrl;
	private String optionGroupType;
	private String valueChangeListenerClassName;
	private List<String> itemValues;
	private boolean valuesFromService = true;
	private boolean isMultiSelect;
	private boolean enable = true;
	private Object defaultSelection;

	public GtnUIFrameworkOptionGroupConfig() {
		super();
	}

	public String getLoadingUrl() {
		return loadingUrl;
	}

	public void setLoadingUrl(String loadingUrl) {
		this.loadingUrl = loadingUrl;
	}

	public String getOptionGroupType() {
		return optionGroupType;
	}

	public void setOptionGroupType(String optionGroupType) {
		this.optionGroupType = optionGroupType;
	}

	public String getValueChangeListenerClassName() {
		return valueChangeListenerClassName;
	}

	public void setValueChangeListenerClassName(String valueChangeListenerClassName) {
		this.valueChangeListenerClassName = valueChangeListenerClassName;
	}

	public List<String> getItemValues() {
		return itemValues == null ? itemValues : Collections.unmodifiableList(itemValues);
	}

	public void setItemValues(List<String> itemValues) {
		this.itemValues = new ArrayList<>(itemValues);
	}

	public boolean isValuesFromService() {
		return valuesFromService;
	}

	public void setValuesFromService(boolean valuesFromService) {
		this.valuesFromService = valuesFromService;
	}

	public boolean isIsMultiSelect() {
		return isMultiSelect;
	}

	public void setIsMultiSelect(boolean isMultiSelect) {
		this.isMultiSelect = isMultiSelect;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Object getDefaultSelection() {
		return defaultSelection;
	}

	public void setDefaultSelection(Object defaultSelection) {
		this.defaultSelection = defaultSelection;
	}

}
