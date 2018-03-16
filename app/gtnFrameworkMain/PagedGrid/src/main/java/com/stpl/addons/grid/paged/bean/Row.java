package com.stpl.addons.grid.paged.bean;

import java.util.HashMap;
import java.util.Map;

public class Row {
	private Map<String, Object> values = new HashMap<>();

	public Object getValue(String column) {
		return values.get(column);
	}

	public void setValue(String column, Object value) {
		values.put(column, value);
	}
}