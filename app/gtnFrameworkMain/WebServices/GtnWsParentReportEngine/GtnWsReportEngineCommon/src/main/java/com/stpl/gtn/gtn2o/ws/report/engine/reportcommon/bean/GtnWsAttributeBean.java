package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GtnWsAttributeBean implements Map<String, Object>, Serializable {

	private Map<String, Object> attributes = null;

	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	public void putAttributes(String key, Object value) {
		if (this.attributes == null) {
			this.attributes = new HashMap<>();
		}
		this.attributes.put(key, value);
	}

	public void putAllAttributes(Map<String, Object> attributes) {
		if (this.attributes == null) {
			this.attributes = new HashMap<>();
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

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
	}

	// Dont delete. this Method is called during Serialization
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}

	@Override
	public int size() {
		return attributes.size();
	}

	@Override
	public boolean isEmpty() {
		return attributes.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return attributes.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return attributes.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return attributes.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return attributes.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return attributes.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		attributes.putAll(m);
	}

	@Override
	public void clear() {
		attributes.clear();
	}

	@Override
	public Set<String> keySet() {
		return attributes.keySet();
	}

	@Override
	public Collection<Object> values() {
		return attributes.values();
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return attributes.entrySet();
	}
}
