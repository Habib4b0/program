package com.stpl.gtn.gtn2o.ws.response;

import java.util.Date;

public class GtnUIFrameworkWebserviceDateResponse implements GtnWSResponseData {

	public GtnUIFrameworkWebserviceDateResponse() {
		super();
	}

	private Date resultValue;

	public Date getResultValue() {
		return resultValue;
	}

	public void setResultValue(Date resultValue) {
		this.resultValue = resultValue;
	}

}
