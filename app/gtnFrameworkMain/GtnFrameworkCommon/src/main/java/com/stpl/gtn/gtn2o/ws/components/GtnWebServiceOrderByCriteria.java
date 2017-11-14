package com.stpl.gtn.gtn2o.ws.components;

public class GtnWebServiceOrderByCriteria {

	private String propertyId;
	private String orderByCriteria;

	public GtnWebServiceOrderByCriteria(String propertyId, String orderByCriteria) {
		this.propertyId = propertyId;
		this.orderByCriteria = orderByCriteria;
	}

	public GtnWebServiceOrderByCriteria() {
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getOrderByCriteria() {
		return orderByCriteria;
	}

	public void setOrderByCriteria(String orderByCriteria) {
		this.orderByCriteria = orderByCriteria;
	}

}
