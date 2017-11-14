package com.stpl.gtn.gtn2o.querygenerator;

public enum GtnFrameworkOperatorType {

	EQUAL_TO(" = "), NOT_EQUAL_TO(" <> "), IN(" in ("), NOT_IN(" not in ("), BETWEEN(" BETWEEN "), LIKE(
			" LIKE "), GREATERTHAN(" > "), LESSTHAN(" < ");

	private String operaterType;

	private GtnFrameworkOperatorType(String operaterType) {
		this.operaterType = operaterType;
	}

	public String getOperaterType() {
		return operaterType;
	}
}
