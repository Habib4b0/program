/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.forecastconfiguration;

import java.util.Date;

import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsForecastConfigurationRequest implements GtnWSRequestData {

	public GtnWsForecastConfigurationRequest() {
		super();
	}

	private int historyFrequency;
	private String historyInterval;
	private int futureFrequency;
	private String futureInterval;
	private int businessProcess;
	private String mode;
	private String processType;
	private Date fromDate;
	private Date toDate;
	private int userId;
	private boolean count;

	public String getHistoryInterval() {
		return historyInterval;
	}

	public void setHistoryInterval(String historyInterval) {
		this.historyInterval = historyInterval;
	}

	public boolean isCount() {
		return count;
	}

	public void setCount(boolean count) {
		this.count = count;
	}

	public String getFutureInterval() {
		return futureInterval;
	}

	public void setFutureInterval(String futureInterval) {
		this.futureInterval = futureInterval;
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

	public int getBusinessProcess() {
		return businessProcess;
	}

	public void setBusinessProcess(int businessProcess) {
		this.businessProcess = businessProcess;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getHistoryFrequency() {
		return historyFrequency;
	}

	public void setHistoryFrequency(int historyFrequency) {
		this.historyFrequency = historyFrequency;
	}

	public int getFutureFrequency() {
		return futureFrequency;
	}

	public void setFutureFrequency(int futureFrequency) {
		this.futureFrequency = futureFrequency;
	}

	@Override
	public String toString() {
		return "historyFrequency=" + historyFrequency + "\nhistoryInterval=" + historyInterval + "\nfutureFrequency="
				+ futureFrequency + "\nfutureInterval=" + futureInterval + "\nbusinessProcess=" + businessProcess
				+ "\nmode=" + mode + "\nprocessType=" + processType + "\nfromDate=" + fromDate + "\ntoDate=" + toDate
				+ "\nuserId=" + userId;
	}

}
