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

	public static GtnWsHierarchyType fromString(String type) {
		for (GtnWsHierarchyType hierarchyType : values()) {
			if (hierarchyType.type.equals(type)) {
				return hierarchyType;
			}
		}
		throw new IllegalArgumentException("No type found for " + type);
	}

	public String getType() {
		return type;
	}
}
