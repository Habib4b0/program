package com.stpl.gtn.gtn2o.ws.netsales.bean;

import java.util.Map;

public class GtnWsNsfUpdateBean {

	public GtnWsNsfUpdateBean() {
		super();
	}

	private boolean checkAll;
	private boolean isSalesBasis;
	private String propertyId;
	private Object value;
	private int masterSid;
	private Map<String, String> propertyValueMap;
	private boolean checkRecordFlag;
	private boolean isPopulate;

	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	public boolean isSalesBasis() {
		return isSalesBasis;
	}

	public void setSalesBasis(boolean isSalesBasis) {
		this.isSalesBasis = isSalesBasis;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getMasterSid() {
		return masterSid;
	}

	public void setMasterSid(int masterSid) {
		this.masterSid = masterSid;
	}

	public Map<String, String> getPropertyValueMap() {
		return propertyValueMap;
	}

	public void setPropertyValueMap(Map<String, String> propertyValueMap) {
		this.propertyValueMap = propertyValueMap;
	}

	public boolean isCheckRecordFlag() {
		return checkRecordFlag;
	}

	public void setCheckRecordFlag(boolean checkRecordFlag) {
		this.checkRecordFlag = checkRecordFlag;
	}

	public boolean isPopulate() {
		return isPopulate;
	}

	public void setPopulate(boolean isPopulate) {
		this.isPopulate = isPopulate;
	}

}
