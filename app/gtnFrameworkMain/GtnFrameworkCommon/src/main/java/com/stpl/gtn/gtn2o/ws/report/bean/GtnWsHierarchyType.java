package com.stpl.gtn.gtn2o.ws.report.bean;

public enum GtnWsHierarchyType {
	CUSTOMER("customer"), PRODUCT("product"), DEDUCTION("deduction"), VARIABLES("variable");

	private final String type;

	private GtnWsHierarchyType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
