package com.stpl.gtn.gtn2o.ws.entity.periodconfiguration;

import java.util.HashSet;
import java.util.Set;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;

public class PeriodConfigMaster implements java.io.Serializable {

	private int periodConfigMasterSid;
	private CompanyMaster companyMasterByCompanyMasterSid;
	private CompanyMaster companyMasterByBuCompanyMasterSid;
	private HelperTable helperTable;
	private int businessProcessType;
	private Set periodConfigDetailses = new HashSet(0);

	public PeriodConfigMaster() {
	}

	public PeriodConfigMaster(CompanyMaster companyMasterByCompanyMasterSid,
			CompanyMaster companyMasterByBuCompanyMasterSid, HelperTable helperTable, int businessProcessType) {
		super();
		this.companyMasterByCompanyMasterSid = companyMasterByCompanyMasterSid;
		this.companyMasterByBuCompanyMasterSid = companyMasterByBuCompanyMasterSid;
		this.helperTable = helperTable;
		this.businessProcessType = businessProcessType;
	}

	public PeriodConfigMaster(int periodConfigMasterSid, CompanyMaster companyMasterByCompanyMasterSid,
			CompanyMaster companyMasterByBuCompanyMasterSid, HelperTable helperTable, int businessProcessType) {
		this.periodConfigMasterSid = periodConfigMasterSid;
		this.companyMasterByCompanyMasterSid = companyMasterByCompanyMasterSid;
		this.companyMasterByBuCompanyMasterSid = companyMasterByBuCompanyMasterSid;
		this.helperTable = helperTable;
		this.businessProcessType = businessProcessType;
	}

	public PeriodConfigMaster(int periodConfigMasterSid, CompanyMaster companyMasterByCompanyMasterSid,
			CompanyMaster companyMasterByBuCompanyMasterSid, HelperTable helperTable, int businessProcessType,
			Set periodConfigDetailses) {
		this.periodConfigMasterSid = periodConfigMasterSid;
		this.companyMasterByCompanyMasterSid = companyMasterByCompanyMasterSid;
		this.companyMasterByBuCompanyMasterSid = companyMasterByBuCompanyMasterSid;
		this.helperTable = helperTable;
		this.businessProcessType = businessProcessType;
		this.periodConfigDetailses = periodConfigDetailses;
	}

	public int getPeriodConfigMasterSid() {
		return this.periodConfigMasterSid;
	}

	public void setPeriodConfigMasterSid(int periodConfigMasterSid) {
		this.periodConfigMasterSid = periodConfigMasterSid;
	}

	public CompanyMaster getCompanyMasterByCompanyMasterSid() {
		return this.companyMasterByCompanyMasterSid;
	}

	public void setCompanyMasterByCompanyMasterSid(CompanyMaster companyMasterByCompanyMasterSid) {
		this.companyMasterByCompanyMasterSid = companyMasterByCompanyMasterSid;
	}

	public CompanyMaster getCompanyMasterByBuCompanyMasterSid() {
		return this.companyMasterByBuCompanyMasterSid;
	}

	public void setCompanyMasterByBuCompanyMasterSid(CompanyMaster companyMasterByBuCompanyMasterSid) {
		this.companyMasterByBuCompanyMasterSid = companyMasterByBuCompanyMasterSid;
	}

	public HelperTable getHelperTable() {
		return this.helperTable;
	}

	public void setHelperTable(HelperTable helperTable) {
		this.helperTable = helperTable;
	}

	public int getBusinessProcessType() {
		return this.businessProcessType;
	}

	public void setBusinessProcessType(int businessProcessType) {
		this.businessProcessType = businessProcessType;
	}

	public Set getPeriodConfigDetailses() {
		return this.periodConfigDetailses;
	}

	public void setPeriodConfigDetailses(Set periodConfigDetailses) {
		this.periodConfigDetailses = periodConfigDetailses;
	}

}
