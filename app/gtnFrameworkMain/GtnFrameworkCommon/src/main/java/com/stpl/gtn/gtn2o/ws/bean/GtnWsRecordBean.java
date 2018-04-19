/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.components.duallistbox.GtnWsDualListBoxFilterManager;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsRecordBean implements Serializable {

	public static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsRecordBean.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<GtnWsRecordBean> childList = null;

	private List<Object> properties = null;

	@SuppressWarnings("rawtypes")
	private List recordHeader;

	private List<Object> additionalProperties = null;

	private Boolean parentFlag = Boolean.FALSE;

	private Object[] rawObjectArray;

	private static final int PRIME = 31;

	public GtnWsRecordBean(Object[] rawObj) {
		this.rawObjectArray = rawObj == null ? rawObj : rawObj.clone();
	}

	public GtnWsRecordBean() {
	}

	public List<Object> getAdditionalProperties() {
		return additionalProperties == null ? additionalProperties : Collections.unmodifiableList(additionalProperties);
	}

	public void addAdditionalProperty(Object value) {
		if (additionalProperties == null) {
			this.additionalProperties = new ArrayList<>();
		}
		additionalProperties.add(value);
	}

	public Object getAdditionalPropertyByIndex(int index) {
		return additionalProperties.get(index);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getRecordHeader() {
		return recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setRecordHeader(List recordHeader) {
		this.recordHeader = recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
	}

	public List<Object> getProperties() {
		return properties;
	}

	public Object removeProperties(String propertyId) {
		int ind = getIndex(propertyId);
		Object value = null;
		if (ind > -1) {
			value = this.properties.remove(ind);
		}
		return value;
	}

	public boolean addProperties(String propertyId, Object value) {
		if (properties == null) {
			this.properties = new ArrayList<>();
		}
		return addProperties(getIndex(propertyId), value, properties);
	}

	public void setProperties(List<Object> properties) {
		this.properties = new ArrayList<>(properties);
	}

	public static boolean addProperties(int index, Object value, final List<Object> propertyList) {
		boolean added = index > -1;
		if (added) {
			if (propertyList.size() > index) {
				propertyList.set(index, value);
			} else {
				for (int i = propertyList.size(); i < index; i++) {
					propertyList.add(i, null);
				}
				propertyList.add(index, value);
			}
		}
		return added;
	}

	public boolean addStringProperties(String propertyId, String value) {
		return addProperties(propertyId, value);
	}

	public boolean addDoubleProperties(String propertyId, Double value) {
		return addProperties(propertyId, value);
	}

	public boolean addBooleanProperties(String propertyId, Boolean value) {
		return addProperties(propertyId, value);
	}

	public boolean addIntegerProperties(String propertyId, Integer value) {
		return addProperties(propertyId, value);
	}

	public boolean addLongProperties(String propertyId, Long value) {
		return addProperties(propertyId, value);
	}

	public boolean addDateProperties(String propertyId, Date value) {
		return addProperties(propertyId, value);
	}

	public Object getPropertyValue(String propertyId) {
		return getPropertyValueByIndex(getIndex(propertyId));
	}

	public String getStringProperty(String propertyId) {
		return getString(getPropertyValue(propertyId));
	}

	public double getDoubleProperty(String propertyId) {
		String value = getStringProperty(propertyId);
		if (isEmpty(value)) {
			return 0.0;
		}
		return Double.parseDouble(value);
	}

	public boolean getBooleanProperty(String propertyId) {
		String value = getStringProperty(propertyId);
		if (isEmpty(value)) {
			return false;
		}
		return Boolean.parseBoolean(getStringProperty(propertyId));
	}

	public int getIntegerProperty(String propertyId) {
		String value = getStringProperty(propertyId);
		if (isEmpty(value)) {
			return 0;
		}
		return Integer.parseInt(getStringProperty(propertyId));
	}

	public long getLongProperty(String propertyId) {
		String value = getStringProperty(propertyId);
		if (isEmpty(value)) {
			return 0L;
		}
		return Long.parseLong(getStringProperty(propertyId));
	}

	public Date getDateProperty(String propertyId) {
		return (Date) getPropertyValue(propertyId);
	}

	public static int getIndexOfProperty(String propertyId) {
		int ind = propertyId.lastIndexOf('.');
		if (ind > -1) {
			try {
				ind = Integer.parseInt(propertyId.substring(ind + 1));
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return ind;
	}

	public int getIndex(String propertyId) {
		return recordHeader.indexOf(propertyId);
	}

	public static String getPropertyId(String propertyId) {
		String proId = propertyId;
		int ind = propertyId.lastIndexOf('.');
		if (ind > -1) {
			try {
				proId = propertyId.substring(0, ind);
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return proId;
	}

	public Object getPropertyValueByIndex(int index) {
		Object value = null;
		if (index > -1 && properties.size() > index) {
			value = properties.get(index);
		}
		return value;
	}

	public void setPropertyValueByIndex(int index, String value) {
		if (properties != null && index > -1 && properties.size() > index) {
			properties.set(index, value);
		}

	}
	public String getStringPropertyByIndex(int index) {
		return getString(getPropertyValueByIndex(index));
	}

	public double getDoublePropertyByIndex(int index) {
		String value = getStringPropertyByIndex(index);
		if (isEmpty(value)) {
			return 0.0;
		}
		return Double.parseDouble(value);
	}

	public boolean getBooleanPropertyByIndex(int index) {
		String value = getStringPropertyByIndex(index);
		if (isEmpty(value)) {
			return false;
		}
		return Boolean.parseBoolean(value);
	}

	public int getIntegerPropertyByIndex(int index) {
		String value = getStringPropertyByIndex(index);
		if (isEmpty(value)) {
			return 0;
		}
		return Integer.parseInt(value);
	}

	public long getLongPropertyByIndex(int index) {
		String value = getStringPropertyByIndex(index);
		if (isEmpty(value)) {
			return 0l;
		}
		return Long.parseLong(value);
	}

	public Date getDatePropertyByIndex(int index) {
		return (Date) getPropertyValueByIndex(index);
	}

	public String getAdditionalStringPropertyByIndex(int index) {
		return getString(getAdditionalPropertyByIndex(index));
	}

	public double getAdditionalDoublePropertyByIndex(int index) {
		String value = getAdditionalStringPropertyByIndex(index);
		if (isEmpty(value)) {
			return 0.0;
		}
		return Double.parseDouble(value);
	}

	public boolean getAdditionalBooleanPropertyByIndex(int index) {
		String value = getAdditionalStringPropertyByIndex(index);
		if (isEmpty(value)) {
			return false;
		}
		return Boolean.parseBoolean(value);
	}

	public int getAdditionalIntegerPropertyByIndex(int index) {
		String value = getAdditionalStringPropertyByIndex(index);
		if (isEmpty(value)) {
			return 0;
		}
		return Integer.parseInt(value);
	}

	public long getAdditionalLongPropertyByIndex(int index) {
		String value = getAdditionalStringPropertyByIndex(index);
		if (isEmpty(value)) {
			return 0L;
		}
		return Long.parseLong(value);
	}

	public Date getAdditionalDatePropertyByIndex(int index) {
		return (Date) getAdditionalPropertyByIndex(index);
	}

	private boolean isNull(Object value) {
		return value == null;
	}

	private String getString(Object value) {
		if (isNull(value)) {
			return "";
		}
		return String.valueOf(value);
	}

	private boolean isEmpty(Object value) {
		return getString(value).isEmpty();
	}

	public boolean addProperties(Object value) {
		if (properties == null) {
			this.properties = new ArrayList<>();
		}
		return this.properties.add(value);
	}

	public Boolean getParentFlag() {
		return parentFlag;
	}

	public void setParentFlag(Boolean parentFlag) {
		this.parentFlag = parentFlag;
	}

	public void setAdditionalProperties(List<Object> additionalProperties) {
		this.additionalProperties = additionalProperties == null ? additionalProperties
				: Collections.unmodifiableList(additionalProperties);
	}

	public List<GtnWsRecordBean> getChildList() {
		return childList == null ? childList : Collections.unmodifiableList(childList);
	}

	public void setChildList(List<GtnWsRecordBean> childList) {
		this.childList = childList == null ? childList : Collections.unmodifiableList(childList);
	}

	public void addChild(GtnWsRecordBean childNode) {
		if (getChildList() == null) {
			this.childList = new ArrayList<>();
		}
		this.childList.add(childNode);
	}

	public void removeChild(GtnWsRecordBean childNode) {
		if (getChildList() != null) {
			this.childList.remove(childNode);
		}
	}

	// Dont delete. this Method is called during Serialization

	public Object[] getRawObjectArray() {
		return rawObjectArray == null ? rawObjectArray : rawObjectArray.clone();
	}

	public void setRawObjectArray(Object[] rawObjectArray) {
		this.rawObjectArray = rawObjectArray == null ? rawObjectArray : rawObjectArray.clone();
	}

	@Override
	public String toString() {
		return "RecordBean [rawObj=" + Arrays.toString(rawObjectArray) + "]";
	}

	@Override
	public int hashCode() {
		if (rawObjectArray == null) {
			return super.hashCode();
		}

		int result = 1;
		result = PRIME * result + Arrays.hashCode(rawObjectArray);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (rawObjectArray == null) {
			return super.equals(obj);
		}
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GtnWsRecordBean other = (GtnWsRecordBean) obj;
		return Arrays.equals(rawObjectArray, other.rawObjectArray);
	}

        private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
	}
	// Dont delete. this Method is called during Serialization

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
		GtnWsRecordBean dat = GtnWsDualListBoxFilterManager.getFilter().applyFilter(this);
		this.rawObjectArray = (dat == null ? null : dat.getRawObjectArray());
	}

	public void addAdditionalProperties(int index, Object value) {
		if (additionalProperties == null) {
			this.additionalProperties = new ArrayList<>();
		}
		additionalProperties.set(index, value);
	}

	@SuppressWarnings("unchecked")
	public GtnWsRecordBean cloneGtnWsRecordBean() {
		GtnWsRecordBean newObject = new GtnWsRecordBean();
		newObject.childList = new ArrayList<>(checkAndReturnList(this.childList));
		newObject.parentFlag = this.parentFlag;
		newObject.recordHeader = new ArrayList<>(checkAndReturnList(this.recordHeader));
		newObject.properties = new ArrayList<>(checkAndReturnList(this.properties));
		newObject.additionalProperties = new ArrayList<>(checkAndReturnList(this.additionalProperties));
		return newObject;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List checkAndReturnList(List childList) {
		if (childList == null) {
			return Collections.emptyList();
		}
		return new ArrayList<>(childList);
	}

}
