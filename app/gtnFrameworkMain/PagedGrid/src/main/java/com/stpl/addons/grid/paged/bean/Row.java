package com.stpl.addons.grid.paged.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Row {
        private static final Logger logger = LogManager.getLogger(Row.class);
        private List<Object> properties = null;

	private List<Object> additionalProperties = null;

        @SuppressWarnings("rawtypes")
	private List recordHeader;
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getRecordHeader() {
		return recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setRecordHeader(List recordHeader) {
		this.recordHeader = recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
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
		return Double.valueOf(value);
	}

	public boolean getBooleanProperty(String propertyId) {
		String value = getStringProperty(propertyId);
		if (isEmpty(value)) {
			return false;
		}
		return Boolean.valueOf(getStringProperty(propertyId));
	}

	public int getIntegerProperty(String propertyId) {
		String value = getStringProperty(propertyId);
		if (isEmpty(value)) {
			return 0;
		}
		return Integer.valueOf(getStringProperty(propertyId));
	}

	public long getLongProperty(String propertyId) {
		String value = getStringProperty(propertyId);
		if (isEmpty(value)) {
			return 0L;
		}
		return Long.valueOf(getStringProperty(propertyId));
	}

	public Date getDateProperty(String propertyId) {
		return (Date) getPropertyValue(propertyId);
	}

	public static int getIndexOfProperty(String propertyId) {
		int ind = propertyId.lastIndexOf('.');
		if (ind > -1) {
			try {
				ind = Integer.valueOf(propertyId.substring(ind + 1));
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
		return Double.valueOf(value);
	}

	public boolean getBooleanPropertyByIndex(int index) {
		String value = getStringPropertyByIndex(index);
		if (isEmpty(value)) {
			return false;
		}
		return Boolean.valueOf(value);
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
		return Long.valueOf(value);
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
		return Double.valueOf(value);
	}

	public boolean getAdditionalBooleanPropertyByIndex(int index) {
		String value = getAdditionalStringPropertyByIndex(index);
		if (isEmpty(value)) {
			return false;
		}
		return Boolean.valueOf(value);
	}

	public int getAdditionalIntegerPropertyByIndex(int index) {
		String value = getAdditionalStringPropertyByIndex(index);
		if (isEmpty(value)) {
			return 0;
		}
		return Integer.valueOf(value);
	}

	public long getAdditionalLongPropertyByIndex(int index) {
		String value = getAdditionalStringPropertyByIndex(index);
		if (isEmpty(value)) {
			return 0L;
		}
		return Long.valueOf(value);
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


	public void setAdditionalProperties(List<Object> additionalProperties) {
		this.additionalProperties = additionalProperties == null ? additionalProperties
				: Collections.unmodifiableList(additionalProperties);
	}
}