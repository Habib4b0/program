/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.emailconfig;

import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsMailConfigurationRequest implements GtnWSRequestData {

	private GtnWsEMailConfigurationBean configurationBean;

	public GtnWsMailConfigurationRequest() {
		super();
	}

	public GtnWsEMailConfigurationBean getConfigurationBean() {
		return configurationBean;
	}

	public void setConfigurationBean(GtnWsEMailConfigurationBean configurationBean) {
		this.configurationBean = configurationBean;
	}

}
