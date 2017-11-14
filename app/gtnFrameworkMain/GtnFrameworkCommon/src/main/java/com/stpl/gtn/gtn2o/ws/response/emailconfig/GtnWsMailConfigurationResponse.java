/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.emailconfig;

import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsMailConfigurationResponse implements GtnWSRequestData {

	public GtnWsMailConfigurationResponse() {
		super();
	}

	private GtnWsEMailConfigurationBean eMailConfigurationBean;
	private boolean success;
	private String message;
	private boolean notificationSuccess;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public GtnWsEMailConfigurationBean geteMailConfigurationBean() {
		return eMailConfigurationBean;
	}

	public void seteMailConfigurationBean(GtnWsEMailConfigurationBean eMailConfigurationBean) {
		this.eMailConfigurationBean = eMailConfigurationBean;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isNotificationSuccess() {
		return notificationSuccess;
	}

	public void setNotificationSuccess(boolean notificationSuccess) {
		this.notificationSuccess = notificationSuccess;
	}

}
