package com.stpl.gtn.gtn2o.querygenerator;

public enum GtnFrameworkOperatorType {

	EQUAL_TO(" = "), NOT_EQUAL_TO(" <> "), IN(" in ("), NOT_IN(" not in ("), BETWEEN(" BETWEEN "), LIKE(
			" LIKE "), GREATERTHAN(" > "), LESSTHAN(" < "), GREATERTHANOREQUALTO(" >= ");

	private String operaterType;

	private GtnFrameworkOperatorType(String operaterType) {
		this.operaterType = operaterType;
	}

	public String getOperaterType() {
		return operaterType;
	}

	public static GtnFrameworkOperatorType getOperaterType(String type) {
		GtnFrameworkOperatorType[] valus = values();
		for (GtnFrameworkOperatorType gtnFrameworkOperatorType : valus) {
			if (gtnFrameworkOperatorType.getOperaterType().trim().equals(type))
				return gtnFrameworkOperatorType;
		}
		return EQUAL_TO;
	}

}
