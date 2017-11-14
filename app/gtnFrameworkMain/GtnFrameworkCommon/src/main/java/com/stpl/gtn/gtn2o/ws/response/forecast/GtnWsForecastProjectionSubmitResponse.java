/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.forecast;

import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;

/**
 *
 * @author STPL
 */
public class GtnWsForecastProjectionSubmitResponse {

	public GtnWsForecastProjectionSubmitResponse() {
		super();
	}

	private GtnWsForecastProjectionSubmitBean gtnWsForecastProjectionSubmitBean;

	public GtnWsForecastProjectionSubmitBean getGtnWsForecastProjectionSubmitBean() {
		return gtnWsForecastProjectionSubmitBean;
	}

	public void setGtnWsForecastProjectionSubmitBean(
			GtnWsForecastProjectionSubmitBean gtnWsForecastProjectionSubmitBean) {
		this.gtnWsForecastProjectionSubmitBean = gtnWsForecastProjectionSubmitBean;
	}
}
