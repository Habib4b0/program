/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.periodconfig;

import java.util.Date;

import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsPeriodConfigurationRequest implements GtnWSRequestData {

	private static final long serialVersionUID = -7231037678710574930L;

	private Integer userId;

	private int businessProcess;
	private int company;
	private int businessUnit;
	private String periodView;
	private int modeFrom;
	private int defaultModeFrom;
	private int frequencyFrom;
	private int defaultFrequencyFrom;
	private Date periodFrom;
	private Date defaultPeriodFrom;
	private String periodFromTextBox;
	private String defaultPeriodFromTextBox;
	private String dateFrom;
	private String defaultDateFrom;

	private int modeTo;
	private int defaultModeTo;
	private int frequencyTo;
	private int defaultFrequencyTo;
	private Date periodTo;
	private String periodToTextBox;
	private Date defaultPeriodTo;
	private String defaultPeriodToTextBox;
	private String dateTo;
	private String defaultDateTo;

	private String defaultFrequencyFromValue;
	private String defaultFrequencyToValue;
	private String defaultModeFromValue;
	private String defaultModeToValue;
	private String frequencyFromValue;
	private String frequencyToValue;
	private String modeFromValue;
	private String modeToValue;
	private int module;

	public GtnWsPeriodConfigurationRequest() {
		super();
	}

	public int getBusinessProcess() {
		return businessProcess;
	}

	public void setBusinessProcess(int businessProcess) {
		this.businessProcess = businessProcess;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public int getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(int businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getPeriodView() {
		return periodView;
	}

	public void setPeriodView(String periodView) {
		this.periodView = periodView;
	}

	public int getModeFrom() {
		return modeFrom;
	}

	public void setModeFrom(int modeFrom) {
		this.modeFrom = modeFrom;
	}

	public int getDefaultModeFrom() {
		return defaultModeFrom;
	}

	public void setDefaultModeFrom(int defaultModeFrom) {
		this.defaultModeFrom = defaultModeFrom;
	}

	public int getFrequencyFrom() {
		return frequencyFrom;
	}

	public void setFrequencyFrom(int frequencyFrom) {
		this.frequencyFrom = frequencyFrom;
	}

	public int getDefaultFrequencyFrom() {
		return defaultFrequencyFrom;
	}

	public void setDefaultFrequencyFrom(int defaultFrequencyFrom) {
		this.defaultFrequencyFrom = defaultFrequencyFrom;
	}

	public Date getPeriodFrom() {
		return periodFrom != null ? new Date(periodFrom.getTime()) : null;
	}

	public void setPeriodFrom(Date periodFrom) {
		this.periodFrom = periodFrom != null ? new Date(periodFrom.getTime()) : null;
	}

	public Date getDefaultPeriodFrom() {
		return defaultPeriodFrom != null ? new Date(defaultPeriodFrom.getTime()) : null;
	}

	public void setDefaultPeriodFrom(Date defaultPeriodFrom) {
		this.defaultPeriodFrom = defaultPeriodFrom != null ? new Date(defaultPeriodFrom.getTime()) : null;
	}

	public String getPeriodFromTextBox() {
		return periodFromTextBox;
	}

	public void setPeriodFromTextBox(String periodFromTextBox) {
		this.periodFromTextBox = periodFromTextBox;
	}

	public String getDefaultPeriodFromTextBox() {
		return defaultPeriodFromTextBox;
	}

	public void setDefaultPeriodFromTextBox(String defaultPeriodFromTextBox) {
		this.defaultPeriodFromTextBox = defaultPeriodFromTextBox;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDefaultDateFrom() {
		return defaultDateFrom;
	}

	public void setDefaultDateFrom(String defaultDateFrom) {
		this.defaultDateFrom = defaultDateFrom;
	}

	public int getModeTo() {
		return modeTo;
	}

	public void setModeTo(int modeTo) {
		this.modeTo = modeTo;
	}

	public int getDefaultModeTo() {
		return defaultModeTo;
	}

	public void setDefaultModeTo(int defaultModeTo) {
		this.defaultModeTo = defaultModeTo;
	}

	public int getFrequencyTo() {
		return frequencyTo;
	}

	public void setFrequencyTo(int frequencyTo) {
		this.frequencyTo = frequencyTo;
	}

	public int getDefaultFrequencyTo() {
		return defaultFrequencyTo;
	}

	public void setDefaultFrequencyTo(int defaultFrequencyTo) {
		this.defaultFrequencyTo = defaultFrequencyTo;
	}

	public Date getPeriodTo() {
		return periodTo != null ? new Date(periodTo.getTime()) : null;
	}

	public void setPeriodTo(Date periodTo) {
		this.periodTo = periodTo != null ? new Date(periodTo.getTime()) : null;
	}

	public String getPeriodToTextBox() {
		return periodToTextBox;
	}

	public void setPeriodToTextBox(String periodToTextBox) {
		this.periodToTextBox = periodToTextBox;
	}

	public Date getDefaultPeriodTo() {
		return defaultPeriodTo != null ? new Date(defaultPeriodTo.getTime()) : null;
	}

	public void setDefaultPeriodTo(Date defaultPeriodTo) {
		this.defaultPeriodTo = defaultPeriodTo != null ? new Date(defaultPeriodTo.getTime()) : null;
	}

	public String getDefaultPeriodToTextBox() {
		return defaultPeriodToTextBox;
	}

	public void setDefaultPeriodToTextBox(String defaultPeriodToTextBox) {
		this.defaultPeriodToTextBox = defaultPeriodToTextBox;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getDefaultDateTo() {
		return defaultDateTo;
	}

	public void setDefaultDateTo(String defaultDateTo) {
		this.defaultDateTo = defaultDateTo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDefaultFrequencyFromValue() {
		return defaultFrequencyFromValue;
	}

	public void setDefaultFrequencyFromValue(String defaultFrequencyFromValue) {
		this.defaultFrequencyFromValue = defaultFrequencyFromValue;
	}

	public String getDefaultFrequencyToValue() {
		return defaultFrequencyToValue;
	}

	public void setDefaultFrequencyToValue(String defaultFrequencyToValue) {
		this.defaultFrequencyToValue = defaultFrequencyToValue;
	}

	public String getDefaultModeFromValue() {
		return defaultModeFromValue;
	}

	public void setDefaultModeFromValue(String defaultModeFromValue) {
		this.defaultModeFromValue = defaultModeFromValue;
	}

	public String getDefaultModeToValue() {
		return defaultModeToValue;
	}

	public void setDefaultModeToValue(String defaultModeToValue) {
		this.defaultModeToValue = defaultModeToValue;
	}

	public String getFrequencyFromValue() {
		return frequencyFromValue;
	}

	public void setFrequencyFromValue(String frequencyFromValue) {
		this.frequencyFromValue = frequencyFromValue;
	}

	public String getFrequencyToValue() {
		return frequencyToValue;
	}

	public void setFrequencyToValue(String frequencyToValue) {
		this.frequencyToValue = frequencyToValue;
	}

	public String getModeFromValue() {
		return modeFromValue;
	}

	public void setModeFromValue(String modeFromValue) {
		this.modeFromValue = modeFromValue;
	}

	public String getModeToValue() {
		return modeToValue;
	}

	public void setModeToValue(String modeToValue) {
		this.modeToValue = modeToValue;
	}

	public int getModule() {
		return module;
	}

	public void setModule(int module) {
		this.module = module;
	}

}
