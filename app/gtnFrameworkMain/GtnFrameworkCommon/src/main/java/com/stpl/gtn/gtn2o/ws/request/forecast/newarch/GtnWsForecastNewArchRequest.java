package com.stpl.gtn.gtn2o.ws.request.forecast.newarch;


import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;

public class GtnWsForecastNewArchRequest 


{
	public GtnWsForecastNewArchRequest() {
		super();
	}

	private GtnFrameworkForecastDataSelectionBean dataSelectionBean;
	
	
	public GtnFrameworkForecastDataSelectionBean getDataSelectionBean() {
		return dataSelectionBean;
	}

	public void setDataSelectionBean(GtnFrameworkForecastDataSelectionBean dataSelectionBean) {
		this.dataSelectionBean = dataSelectionBean;
	}
}
