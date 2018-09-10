package com.stpl.gtn.gtn2o.ws.report.controller;

import java.util.HashMap;
import java.util.Map;

public class GtnWsReportAllListConfig {

	private Map<String, String> comboboxLoadMap = new HashMap<>();

	public GtnWsReportAllListConfig() {
		this.loadComboboxQuerymap();
	}

	public Map<String, String> getComboboxLoadMap() {
		return comboboxLoadMap;
	}

	public void setComboboxLoadMap(Map<String, String> comboboxLoadMap) {
		this.comboboxLoadMap = comboboxLoadMap;
	}

	public final void loadComboboxQuerymap() {
		comboboxLoadMap.put("timePeriodForReportFromDate", "FROM");
		comboboxLoadMap.put("timePeriodForReportToDate", "TO");
	}

}
