package com.stpl.gtn.gtn2o.ws.components;

public class GtnWebServiceSearchCriteria {

	private String fieldId;
	private String filterValue1;
	private String filterValue2;
	private Object filterValue3;
	private String expression;
	private boolean filter = false;

	public GtnWebServiceSearchCriteria(String fieldId, String filterValue1, String filterValue2, String expression) {
		this.fieldId = fieldId;
		this.filterValue1 = filterValue1;
		this.filterValue2 = filterValue2;
		this.expression = expression;
	}

	public GtnWebServiceSearchCriteria(String fieldId, String filterValue1, String expression) {
		this.fieldId = fieldId;
		this.filterValue1 = filterValue1;
		this.expression = expression;
	}

	public GtnWebServiceSearchCriteria() {
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getFilterValue1() {
		return filterValue1;
	}

	public void setFilterValue1(String filterValue1) {
		this.filterValue1 = filterValue1;
	}

	public String getFilterValue2() {
		return filterValue2;
	}

	public void setFilterValue2(String filterValue2) {
		this.filterValue2 = filterValue2;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public boolean isFilter() {
		return filter;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	public Object getFilterValue3() {
		return filterValue3;
	}

	public void setFilterValue3(Object filterValue3) {
		this.filterValue3 = filterValue3;
	}

}
