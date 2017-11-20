
package com.stpl.gtn.gtn2o.ws.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnUIFrameworkWebserviceComboBoxResponse implements GtnWSResponseData {

	private String key;
	private List<String> itemCodeList;
	private List<String> itemValueList;
	private Object defaultValue;

	public GtnUIFrameworkWebserviceComboBoxResponse() {
		this.itemCodeList = new ArrayList<>();
		this.itemValueList = new ArrayList<>();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getItemCodeList() {
		return itemCodeList == null ? itemCodeList : Collections.unmodifiableList(itemCodeList);
	}

	public void setItemCodeList(List<String> itemCodeList) {
		this.itemCodeList = itemCodeList == null ? itemCodeList : Collections.unmodifiableList(itemCodeList);
	}

	public void addItemCodeList(String itemCode) {
		if (itemCode != null)
			this.itemCodeList.add(itemCode);
	}

	public List<String> getItemValueList() {
		return itemValueList == null ? itemValueList : Collections.unmodifiableList(itemValueList);
	}

	public void setItemValueList(List<String> itemValueList) {
		this.itemValueList = itemValueList == null ? itemValueList : Collections.unmodifiableList(itemValueList);
	}

	public void addItemValueList(String itemValue) {
		if (itemValue != null)
			this.itemValueList.add(itemValue);
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		if (itemCodeList != null && itemCodeList.contains(defaultValue))
			this.defaultValue = defaultValue;
	}

	public void setComboBoxList(List<Object[]> resultList) {

		if (resultList != null) {
			for (int i = 0; i < resultList.size(); i++) {
				Object[] currentRow = resultList.get(i);
				itemCodeList.add(String.valueOf(currentRow[0]));
				itemValueList.add(String.valueOf(currentRow[1]));
			}
		}
	}
}
