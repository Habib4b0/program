/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.netsales.bean;

/**
 *
 * @author Mahesh.James
 */
public class GtnUiFrameworkNsfSelectedCustomerBean {

	public GtnUiFrameworkNsfSelectedCustomerBean() {
		super();
	}

	private String contractName;
	private String contractNo;
	private String cfpNo;
	private String cfpName;
	private String customerNo;
	private String customerName;
	private Integer contractMasterSid;
	private Integer contractDetSid;

	private Integer ruleSid;
	private Integer systemId;

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getCfpNo() {
		return cfpNo;
	}

	public void setCfpNo(String cfpNo) {
		this.cfpNo = cfpNo;
	}

	public String getCfpName() {
		return cfpName;
	}

	public void setCfpName(String cfpName) {
		this.cfpName = cfpName;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getContractMasterSid() {
		return contractMasterSid;
	}

	public void setContractMasterSid(Integer contractMasterSid) {
		this.contractMasterSid = contractMasterSid;
	}

	public Integer getContractDetSid() {
		return contractDetSid;
	}

	public void setContractDetSid(Integer contractDetSid) {
		this.contractDetSid = contractDetSid;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public Integer getRuleSid() {
		return ruleSid;
	}

	public void setRuleSid(Integer ruleSid) {
		this.ruleSid = ruleSid;
	}

}
