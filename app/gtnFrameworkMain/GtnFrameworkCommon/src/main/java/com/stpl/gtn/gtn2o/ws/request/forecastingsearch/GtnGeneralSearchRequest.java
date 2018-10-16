/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.forecastingsearch;

import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastInputBean;
import com.stpl.gtn.gtn2o.ws.generalsearch.GtnGeneralSearchBean;

/**
 *
 * @author anandh.karuppusamy
 */
public class GtnGeneralSearchRequest {
	private GtnGeneralSearchBean gtnGeneralSearchBean;
	private GtnFrameworkForecastInputBean inputBean;

	public GtnGeneralSearchBean getGtnGeneralSearchBean() {
		return gtnGeneralSearchBean;
	}

	public void setGtnGeneralSearchBean(GtnGeneralSearchBean gtnGeneralSearchBean) {
		this.gtnGeneralSearchBean = gtnGeneralSearchBean;
	}

	public GtnFrameworkForecastInputBean getInputBean() {
		return inputBean;
	}

	public void setInputBean(GtnFrameworkForecastInputBean inputBean) {
		this.inputBean = inputBean;
	}

}
