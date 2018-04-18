package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.util.HashMap;
import java.util.Map;

public class GtnWsAttributeBean {

	private Map<String, Object> attributes = null;

	public Object getAttributes(String key) {
		return attributes.get(key);
	}

	public void putAttributes(String key, Object value) {
		if (this.attributes == null) {
			this.attributes = new HashMap();
		}
		this.attributes.put(key, value);
	}

	public void putAllAttributes(Map<String, Object> attributes) {
		if (this.attributes == null) {
			this.attributes = new HashMap();
		}
		this.attributes.putAll(attributes);
	}

	public Integer getIntegerAttribute(final String key) {
		return (Integer) this.attributes.get(key);
	}

	public Double getDoubleAttribute(final String key) {
		return (Double) this.attributes.get(key);
	}

	public String getStringAttribute(final String key) {
		return (String) this.attributes.get(key);
	}

	public Long getLongAttribute(final String key) {
		return (Long) this.attributes.get(key);
	}

	public Object removeAttribute(final String key) {
		return this.attributes.remove(key);
	}

	public void clearAttributes() {
		this.attributes.clear();
	}

	public Map<String, Object> getAttributeMap() {
		return this.attributes;
	}

}
