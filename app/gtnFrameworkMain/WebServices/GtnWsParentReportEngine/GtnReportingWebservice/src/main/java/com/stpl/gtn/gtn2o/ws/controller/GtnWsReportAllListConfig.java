package com.stpl.gtn.gtn2o.ws.controller;

import java.util.HashMap;
import java.util.Map;

public class GtnWsReportAllListConfig {

	private Map<String, String> comboboxLoadMap = new HashMap<String, String>();

	public GtnWsReportAllListConfig() {
		this.loadComboboxQuerymap();
	}

	public Map<String, String> getComboboxLoadMap() {
		return comboboxLoadMap;
	}

	public void setComboboxLoadMap(Map<String, String> comboboxLoadMap) {
		this.comboboxLoadMap = comboboxLoadMap;
	}

	public void loadComboboxQuerymap() {
		comboboxLoadMap.put("type", "query");
	}

}
