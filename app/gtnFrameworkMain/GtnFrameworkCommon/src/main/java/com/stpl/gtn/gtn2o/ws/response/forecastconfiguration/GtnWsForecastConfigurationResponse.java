/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.forecastconfiguration;

import java.util.Date;

import com.stpl.gtn.gtn2o.ws.response.GtnWSResponseData;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsForecastConfigurationResponse implements GtnWSResponseData {

	public GtnWsForecastConfigurationResponse() {
		super();
	}

	private String historyFrequency;
	private String historyInterval;
	private String historyPeriod;
	private String forecastPeriod;
	private Date fromDate;
	private Date toDate;
	private boolean errorMessage;
	private boolean success;
	private String message;
	private String messageType;

	public String getHistoryFrequency() {
		return historyFrequency;
	}

	public void setHistoryFrequency(String historyFrequency) {
		this.historyFrequency = historyFrequency;
	}

	public String getHistoryInterval() {
		return historyInterval;
	}

	public void setHistoryInterval(String historyInterval) {
		this.historyInterval = historyInterval;
	}

	public String getHistoryPeriod() {
		return historyPeriod;
	}

	public void setHistoryPeriod(String historyPeriod) {
		this.historyPeriod = historyPeriod;
	}

	public boolean isErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(boolean errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setForecastPeriod(String forecastPeriod) {
		this.forecastPeriod = forecastPeriod;
	}

	public String getForecastPeriod() {
		return forecastPeriod;
	}

	public Date getFromDate() {
		return  fromDate==null ? null :(Date)fromDate.clone();
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate==null ? null :(Date)fromDate.clone();
	}

	public Date getToDate() {
		return  toDate==null ? null :(Date)toDate.clone();
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate==null ? null :(Date)toDate.clone();
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}
