package com.stpl.gtn.gtn2o.ws.entity.periodconfiguration;

import java.util.Date;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;

public class PeriodConfigDetails implements java.io.Serializable {

	private int periodConfigDetailsSid;
	private HelperTable helperTableByFromFrequency;
	private HelperTable helperTableByToDefaultFrequerncy;
	private HelperTable helperTableByToFrequency;
	private HelperTable helperTableByPeriodView;
	private HelperTable helperTableByFromDefaultMode;
	private HelperTable helperTableByFromDefaultFrequerncy;
	private HelperTable helperTableByToDefaultMode;
	private HelperTable helperTableByToMode;
	private HelperTable helperTableByFromMode;
	private PeriodConfigMaster periodConfigMaster;
	private String fromPeriod;
	private Date fromPeriodDate;
	private String fromDefaultPeriod;
	private Date fromDefaultPeriodDate;
	private String toPeriod;
	private Date toPeriodDate;
	private String toDefaultPeriod;
	private Date toDefualtPeriodDate;
	private Integer versionNo;
	private int createdBy;

	public PeriodConfigDetails() {
	}

	public PeriodConfigDetails(int periodConfigDetailsSid, HelperTable helperTableByFromDefaultMode,
			HelperTable helperTableByFromMode, PeriodConfigMaster periodConfigMaster, int createdBy) {
		this.periodConfigDetailsSid = periodConfigDetailsSid;
		this.helperTableByFromDefaultMode = helperTableByFromDefaultMode;
		this.helperTableByFromMode = helperTableByFromMode;
		this.periodConfigMaster = periodConfigMaster;
		this.createdBy = createdBy;
	}

	public PeriodConfigDetails(int periodConfigDetailsSid, HelperTable helperTableByFromFrequency,
			HelperTable helperTableByToDefaultFrequerncy, HelperTable helperTableByToFrequency,
			HelperTable helperTableByPeriodView, HelperTable helperTableByFromDefaultMode,
			HelperTable helperTableByFromDefaultFrequerncy, HelperTable helperTableByToDefaultMode,
			HelperTable helperTableByToMode, HelperTable helperTableByFromMode, PeriodConfigMaster periodConfigMaster,
			String fromPeriod, Date fromPeriodDate, String fromDefaultPeriod, Date fromDefaultPeriodDate,
			String toPeriod, Date toPeriodDate, String toDefaultPeriod, Date toDefualtPeriodDate, Integer versionNo,
			int createdBy) {
		this.periodConfigDetailsSid = periodConfigDetailsSid;
		this.helperTableByFromFrequency = helperTableByFromFrequency;
		this.helperTableByToDefaultFrequerncy = helperTableByToDefaultFrequerncy;
		this.helperTableByToFrequency = helperTableByToFrequency;
		this.helperTableByPeriodView = helperTableByPeriodView;
		this.helperTableByFromDefaultMode = helperTableByFromDefaultMode;
		this.helperTableByFromDefaultFrequerncy = helperTableByFromDefaultFrequerncy;
		this.helperTableByToDefaultMode = helperTableByToDefaultMode;
		this.helperTableByToMode = helperTableByToMode;
		this.helperTableByFromMode = helperTableByFromMode;
		this.periodConfigMaster = periodConfigMaster;
		this.fromPeriod = fromPeriod;
		this.fromPeriodDate = fromPeriodDate;
		this.fromDefaultPeriod = fromDefaultPeriod;
		this.fromDefaultPeriodDate = fromDefaultPeriodDate;
		this.toPeriod = toPeriod;
		this.toPeriodDate = toPeriodDate;
		this.toDefaultPeriod = toDefaultPeriod;
		this.toDefualtPeriodDate = toDefualtPeriodDate;
		this.versionNo = versionNo;
		this.createdBy = createdBy;
	}

	public int getPeriodConfigDetailsSid() {
		return this.periodConfigDetailsSid;
	}

	public void setPeriodConfigDetailsSid(int periodConfigDetailsSid) {
		this.periodConfigDetailsSid = periodConfigDetailsSid;
	}

	public HelperTable getHelperTableByFromFrequency() {
		return this.helperTableByFromFrequency;
	}

	public void setHelperTableByFromFrequency(HelperTable helperTableByFromFrequency) {
		this.helperTableByFromFrequency = helperTableByFromFrequency;
	}

	public HelperTable getHelperTableByToDefaultFrequerncy() {
		return this.helperTableByToDefaultFrequerncy;
	}

	public void setHelperTableByToDefaultFrequerncy(HelperTable helperTableByToDefaultFrequerncy) {
		this.helperTableByToDefaultFrequerncy = helperTableByToDefaultFrequerncy;
	}

	public HelperTable getHelperTableByToFrequency() {
		return this.helperTableByToFrequency;
	}

	public void setHelperTableByToFrequency(HelperTable helperTableByToFrequency) {
		this.helperTableByToFrequency = helperTableByToFrequency;
	}

	public HelperTable getHelperTableByPeriodView() {
		return this.helperTableByPeriodView;
	}

	public void setHelperTableByPeriodView(HelperTable helperTableByPeriodView) {
		this.helperTableByPeriodView = helperTableByPeriodView;
	}

	public HelperTable getHelperTableByFromDefaultMode() {
		return this.helperTableByFromDefaultMode;
	}

	public void setHelperTableByFromDefaultMode(HelperTable helperTableByFromDefaultMode) {
		this.helperTableByFromDefaultMode = helperTableByFromDefaultMode;
	}

	public HelperTable getHelperTableByFromDefaultFrequerncy() {
		return this.helperTableByFromDefaultFrequerncy;
	}

	public void setHelperTableByFromDefaultFrequerncy(HelperTable helperTableByFromDefaultFrequerncy) {
		this.helperTableByFromDefaultFrequerncy = helperTableByFromDefaultFrequerncy;
	}

	public HelperTable getHelperTableByToDefaultMode() {
		return this.helperTableByToDefaultMode;
	}

	public void setHelperTableByToDefaultMode(HelperTable helperTableByToDefaultMode) {
		this.helperTableByToDefaultMode = helperTableByToDefaultMode;
	}

	public HelperTable getHelperTableByToMode() {
		return this.helperTableByToMode;
	}

	public void setHelperTableByToMode(HelperTable helperTableByToMode) {
		this.helperTableByToMode = helperTableByToMode;
	}

	public HelperTable getHelperTableByFromMode() {
		return this.helperTableByFromMode;
	}

	public void setHelperTableByFromMode(HelperTable helperTableByFromMode) {
		this.helperTableByFromMode = helperTableByFromMode;
	}

	public PeriodConfigMaster getPeriodConfigMaster() {
		return this.periodConfigMaster;
	}

	public void setPeriodConfigMaster(PeriodConfigMaster periodConfigMaster) {
		this.periodConfigMaster = periodConfigMaster;
	}

	public String getFromPeriod() {
		return this.fromPeriod;
	}

	public void setFromPeriod(String fromPeriod) {
		this.fromPeriod = fromPeriod;
	}

	public Date getFromPeriodDate() {
		return this.fromPeriodDate;
	}

	public void setFromPeriodDate(Date fromPeriodDate) {
		this.fromPeriodDate = fromPeriodDate;
	}

	public String getFromDefaultPeriod() {
		return this.fromDefaultPeriod;
	}

	public void setFromDefaultPeriod(String fromDefaultPeriod) {
		this.fromDefaultPeriod = fromDefaultPeriod;
	}

	public Date getFromDefaultPeriodDate() {
		return this.fromDefaultPeriodDate;
	}

	public void setFromDefaultPeriodDate(Date fromDefaultPeriodDate) {
		this.fromDefaultPeriodDate = fromDefaultPeriodDate;
	}

	public String getToPeriod() {
		return this.toPeriod;
	}

	public void setToPeriod(String toPeriod) {
		this.toPeriod = toPeriod;
	}

	public Date getToPeriodDate() {
		return this.toPeriodDate;
	}

	public void setToPeriodDate(Date toPeriodDate) {
		this.toPeriodDate = toPeriodDate;
	}

	public String getToDefaultPeriod() {
		return this.toDefaultPeriod;
	}

	public void setToDefaultPeriod(String toDefaultPeriod) {
		this.toDefaultPeriod = toDefaultPeriod;
	}

	public Date getToDefualtPeriodDate() {
		return this.toDefualtPeriodDate;
	}

	public void setToDefualtPeriodDate(Date toDefualtPeriodDate) {
		this.toDefualtPeriodDate = toDefualtPeriodDate;
	}

	public Integer getVersionNo() {
		return this.versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

}
