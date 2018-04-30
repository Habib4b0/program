package com.stpl.gtn.gtn2o.ws.report.bean;

public enum GtnWsReportDeductionType {
	RS_CATEGORY("Rebate Schedule Category", 1), RS_TYPE("Rebate Schedule Type", 2), RP_TYPE("Rebate Program Type",
			3), RS_UDC1("Rebate Schedule UDC 1", 4), RS_UDC2("Rebate Schedule UDC 2", 5), RS_UDC3(
					"Rebate Schedule UDC 3", 6), RS_UDC4("Rebate Schedule UDC 4", 7), RS_UDC5("Rebate Schedule UDC 5",
							8), RS_UDC6("Rebate Schedule UDC 6", 9), RS_ID("Rebate Schedule ID", 10);

	private final String type;
	private final int levelNo;

	private GtnWsReportDeductionType(String type, int levelNo) {
		this.type = type;
		this.levelNo = levelNo;
	}

	@Override
	public String toString() {
		return type;
	}

	public int getLevelNo() {
		return levelNo;
	}

}
