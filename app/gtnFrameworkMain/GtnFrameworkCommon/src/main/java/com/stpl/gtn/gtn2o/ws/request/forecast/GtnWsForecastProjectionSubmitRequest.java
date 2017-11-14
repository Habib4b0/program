/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.forecast;

import java.io.Serializable;

import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;

/**
 *
 * @author STPL
 */
public class GtnWsForecastProjectionSubmitRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	public GtnWsForecastProjectionSubmitRequest() {
		super();
	}

	private GtnWsForecastProjectionSubmitBean gtnWsForecastProjectionSubmitBean;

	private GtnWsGeneralRequest gtnWsGeneralRequest;

	public GtnWsForecastProjectionSubmitBean getGtnWsForecastProjectionSubmitBean() {
		return gtnWsForecastProjectionSubmitBean;
	}

	public void setGtnWsForecastProjectionSubmitBean(
			GtnWsForecastProjectionSubmitBean gtnWsForecastProjectionSubmitBean) {
		this.gtnWsForecastProjectionSubmitBean = gtnWsForecastProjectionSubmitBean;
	}

	public GtnWsGeneralRequest getGtnWsGeneralRequest() {
		return gtnWsGeneralRequest;
	}

	public void setGtnWsGeneralRequest(GtnWsGeneralRequest gtnWsGeneralRequest) {
		this.gtnWsGeneralRequest = gtnWsGeneralRequest;
	}
}
