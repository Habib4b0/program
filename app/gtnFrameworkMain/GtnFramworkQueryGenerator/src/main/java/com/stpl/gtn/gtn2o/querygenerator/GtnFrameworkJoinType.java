package com.stpl.gtn.gtn2o.querygenerator;

public enum GtnFrameworkJoinType {

	LEFT_JOIN(" LEFT JOIN "), JOIN(" JOIN "), RIGHT_JOIN(" RIGHT JOIN "), INNER_JOIN(" INNER JOIN ");

	private String joinType;

	private GtnFrameworkJoinType(String joinType) {
		this.joinType = joinType;
	}

	public String getJoinType() {
		return joinType;
	}
}
