
package com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnUIFrameworkCheckedComboBoxConfig {

	private String loadingUrl;
	private String checkedComboBoxType;
	private String defaultValue;
	private String blurListenerClassName;

	private List<String> itemValueList;

	public GtnUIFrameworkCheckedComboBoxConfig() {
		super();
	}

	public String getLoadingUrl() {
		return loadingUrl;
	}

	public void setLoadingUrl(String loadingUrl) {
		this.loadingUrl = loadingUrl;
	}

	public String getCheckedComboBoxType() {
		return checkedComboBoxType;
	}

	public void setCheckedComboBoxType(String checkedComboBoxType) {
		this.checkedComboBoxType = checkedComboBoxType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getBlurListenerClassName() {
		return blurListenerClassName;
	}

	public void setBlurListenerClassName(String blurListenerClassName) {
		this.blurListenerClassName = blurListenerClassName;
	}

	public List<String> getItemValueList() {
		return itemValueList == null ? itemValueList : Collections.unmodifiableList(itemValueList);
	}

	public void setItemValueList(List<String> itemValueList) {
		this.itemValueList = new ArrayList<>(itemValueList);
	}
}
