/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.periodconfig;

import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsPeriodConfigurationResponse implements GtnWSRequestData {

	private Integer userId;

	private String mode;
	private String businessProcess;
	private String company;
	private String businessUnit;
	private String periodView;
	private String modeFrom;
	private String defaultModeFrom;
	private String frequencyFrom;
	private String defaultFrequencyFrom;
	private String periodFrom;
	private String defaultPeriodFrom;
	private String periodFromTextBox;
	private String defaultPeriodFromTextBox;
	private String dateFrom;
	private String defaultDateFrom;

	private String modeTo;
	private String defaultModeTo;
	private String frequencyTo;
	private String defaultFrequencyTo;
	private String periodTo;
	private String periodToTextBox;
	private String defaultPeriodTo;
	private String defaultPeriodToTextBox;
	private String dateTo;
	private String defaultDateTo;
	private boolean success;
	private String message;

	public GtnWsPeriodConfigurationResponse() {
		super();
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getBusinessProcess() {
		return businessProcess;
	}

	public void setBusinessProcess(String businessProcess) {
		this.businessProcess = businessProcess;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getPeriodView() {
		return periodView;
	}

	public void setPeriodView(String periodView) {
		this.periodView = periodView;
	}

	public String getModeFrom() {
		return modeFrom;
	}

	public void setModeFrom(String modeFrom) {
		this.modeFrom = modeFrom;
	}

	public String getDefaultModeFrom() {
		return defaultModeFrom;
	}

	public void setDefaultModeFrom(String defaultModeFrom) {
		this.defaultModeFrom = defaultModeFrom;
	}

	public String getFrequencyFrom() {
		return frequencyFrom;
	}

	public void setFrequencyFrom(String frequencyFrom) {
		this.frequencyFrom = frequencyFrom;
	}

	public String getDefaultFrequencyFrom() {
		return defaultFrequencyFrom;
	}

	public void setDefaultFrequencyFrom(String defaultFrequencyFrom) {
		this.defaultFrequencyFrom = defaultFrequencyFrom;
	}

	public String getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}

	public String getDefaultPeriodFrom() {
		return defaultPeriodFrom;
	}

	public void setDefaultPeriodFrom(String defaultPeriodFrom) {
		this.defaultPeriodFrom = defaultPeriodFrom;
	}

	public String getDefaultPeriodFromTextBox() {
		return defaultPeriodFromTextBox;
	}

	public void setDefaultPeriodFromTextBox(String defaultPeriodFromTextBox) {
		this.defaultPeriodFromTextBox = defaultPeriodFromTextBox;
	}

	public String getPeriodFromTextBox() {
		return periodFromTextBox;
	}

	public void setPeriodFromTextBox(String periodFromTextBox) {
		this.periodFromTextBox = periodFromTextBox;
	}

	public String getDefaultDateFrom() {
		return defaultDateFrom;
	}

	public void setDefaultDateFrom(String defaultDateFrom) {
		this.defaultDateFrom = defaultDateFrom;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getModeTo() {
		return modeTo;
	}

	public void setModeTo(String modeTo) {
		this.modeTo = modeTo;
	}

	public String getDefaultModeTo() {
		return defaultModeTo;
	}

	public void setDefaultModeTo(String defaultModeTo) {
		this.defaultModeTo = defaultModeTo;
	}

	public String getFrequencyTo() {
		return frequencyTo;
	}

	public void setFrequencyTo(String frequencyTo) {
		this.frequencyTo = frequencyTo;
	}

	public String getDefaultFrequencyTo() {
		return defaultFrequencyTo;
	}

	public void setDefaultFrequencyTo(String defaultFrequencyTo) {
		this.defaultFrequencyTo = defaultFrequencyTo;
	}

	public String getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}

	public String getPeriodToTextBox() {
		return periodToTextBox;
	}

	public void setPeriodToTextBox(String periodToTextBox) {
		this.periodToTextBox = periodToTextBox;
	}

	public String getDefaultPeriodTo() {
		return defaultPeriodTo;
	}

	public void setDefaultPeriodTo(String defaultPeriodTo) {
		this.defaultPeriodTo = defaultPeriodTo;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getDefaultPeriodToTextBox() {
		return defaultPeriodToTextBox;
	}

	public void setDefaultPeriodToTextBox(String defaultPeriodToTextBox) {
		this.defaultPeriodToTextBox = defaultPeriodToTextBox;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDefaultDateTo() {
		return defaultDateTo;
	}

	public void setDefaultDateTo(String defaultDateTo) {
		this.defaultDateTo = defaultDateTo;
	}
}
