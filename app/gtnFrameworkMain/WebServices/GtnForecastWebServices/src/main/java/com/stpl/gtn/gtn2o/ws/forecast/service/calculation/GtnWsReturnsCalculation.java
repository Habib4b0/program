/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.calculation;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;

/**
 *
 * @author STPL
 */
public interface GtnWsReturnsCalculation {

	public void calculations(final GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException;
}
