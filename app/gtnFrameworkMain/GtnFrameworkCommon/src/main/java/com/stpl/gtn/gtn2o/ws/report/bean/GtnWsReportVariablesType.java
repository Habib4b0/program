package com.stpl.gtn.gtn2o.ws.report.bean;

public enum GtnWsReportVariablesType {
	EX_FACTORY_SALES("Ex-Factory Sales"), GCS_EX_FACTORY("Gross Contract Sales % of Ex-Factory"), GCS(
			"Gross Contract Sales"), CONTRACT_UNITS("Contract Units"), CS_TOTAL_CONTRACT_SALES(
					"Contract Sales % of Total Contract Sales"), DEDUCTION_DOLLAR(
							"Deduction $"), DEDUCTION_PERCENTAGE("Deduction %"), RPU("RPU"), DEDUCTION_EX_FACTORY(
									"Deduction % of Ex-Factory"), NET_CONTRACT_SALES(
											"Net Contract Sales"), NCS_EX_FACTORY(
													"Net Contract Sales % of Ex-Factory"), NET_EXFACTORY_SALES(
															"Net Ex-Factory Sales"), NEF_TOTAL_EXFACTORY_SALES(
																	"Net Ex-Factory Sales % of Total Ex-Factory Sales"), WEIGHTED_GTN_CONTRIBUTION(
																			"Weighted GTN Contribution"), VARIABLES(
																					"Variables");

	private final String type;

	private GtnWsReportVariablesType(String type) {
		this.type = type;
	}

	public static GtnWsReportVariablesType fromString(String type) {
		for (GtnWsReportVariablesType variableType : values()) {
			if (variableType.type.equals(type)) {
				return variableType;
			}
		}
		throw new IllegalArgumentException("No type found for " + type);
	}

	@Override
	public String toString() {
		return type;
	}
}
