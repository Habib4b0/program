package com.stpl.gtn.gtn2o.ws.udc.bean;

import java.io.Serializable;

public class GtnWsUdcInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnWsUdcInfoBean() {
		super();
	}

	private String udcCategory;
	private String udcValue;

	public String getUdcCategory() {
		return udcCategory;
	}

	public void setUdcCategory(String udcCategory) {
		this.udcCategory = udcCategory;
	}

	public String getUdcValue() {
		return udcValue;
	}

	public void setUdcValue(String udcValue) {
		this.udcValue = udcValue;
	}

}
