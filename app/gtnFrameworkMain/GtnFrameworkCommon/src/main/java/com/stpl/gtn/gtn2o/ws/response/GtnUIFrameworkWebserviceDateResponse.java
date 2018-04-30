package com.stpl.gtn.gtn2o.ws.response;

import java.time.LocalDate;

public class GtnUIFrameworkWebserviceDateResponse implements GtnWSResponseData {

	public GtnUIFrameworkWebserviceDateResponse() {
		super();
	}

	private LocalDate resultValue;

	public LocalDate getResultValue() {
		return resultValue;
	}

	public void setResultValue(LocalDate resultValue) {
		this.resultValue = resultValue;
	}

}
