package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

public enum GtnWsReportVariablesType {
	GTS("gts"), EX_FACTORY("ExFactory");

	private final String type;

	private GtnWsReportVariablesType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}

	public static GtnWsReportVariablesType fromString(String type) {
		for (GtnWsReportVariablesType hierarchyType : values()) {
			if (hierarchyType.type.equals(type)) {
				return hierarchyType;
			}
		}
		throw new IllegalArgumentException("No type found for " + type);
	}
}
