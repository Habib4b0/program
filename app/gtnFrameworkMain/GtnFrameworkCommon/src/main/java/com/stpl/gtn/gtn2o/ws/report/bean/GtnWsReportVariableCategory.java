package com.stpl.gtn.gtn2o.ws.report.bean;

public enum GtnWsReportVariableCategory {
	VALUE("Value"), VARIANCE("Variance"), PER_CHANGE("% Change"), ACTUALS("Actuals"), ACCRUALS("Accruals"), VOLUME(
			"Volume"), RATE("Rate"), CHANGE_IN_CHANGE("Change in Change");

	private final String type;

	private GtnWsReportVariableCategory(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
