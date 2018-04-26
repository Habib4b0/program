package com.stpl.gtn.gtn2o.ws.report.bean;

public enum GtnWsReportDeductionType {
	RS_CATEGORY("Rebate Schedule Category"), RS_TYPE("Rebate Schedule Type"), RP_TYPE("Rebate Program Type"), RS_UDC1(
			"Rebate Schedule UDC 1"), RS_UDC2("Rebate Schedule UDC 2"), RS_UDC3("Rebate Schedule UDC 3"), RS_UDC4(
					"Rebate Schedule UDC 4"), RS_UDC5(
							"Rebate Schedule UDC 5"), RS_UDC6("Rebate Schedule UDC 6"), RS_ID("Rebate Schedule ID");

	private final String type;

	private GtnWsReportDeductionType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}

}
