/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.netsales.bean;

/**
 *
 * @author STPL
 */
public class GtnUIFrameworkNsfSelectedDeductionsBean {

	private Integer deductionType;
	private Integer deductionSubType;
	private Integer deductionCategory;
	private Integer contractMasterSid;
	private Integer rsContractSid;
	private Boolean contract;
	private String contractNo;
	private String contractName;
	private String deductionNo;
	private String deductionName;

	public GtnUIFrameworkNsfSelectedDeductionsBean() {
		super();
	}

	public Integer getDeductionType() {
		return deductionType;
	}

	public void setDeductionType(Integer deductionType) {
		this.deductionType = deductionType;
	}

	public Integer getDeductionSubType() {
		return deductionSubType;
	}

	public void setDeductionSubType(Integer deductionSubType) {
		this.deductionSubType = deductionSubType;
	}

	public Integer getDeductionCategory() {
		return deductionCategory;
	}

	public void setDeductionCategory(Integer deductionCategory) {
		this.deductionCategory = deductionCategory;
	}

	public Integer getContractMasterSid() {
		return contractMasterSid;
	}

	public void setContractMasterSid(Integer contractMasterSid) {
		this.contractMasterSid = contractMasterSid;
	}

	public Integer getRsContractSid() {
		return rsContractSid;
	}

	public void setRsContractSid(Integer rsContractSid) {
		this.rsContractSid = rsContractSid;
	}

	public Boolean isContract() {
		return contract;
	}

	public void setContract(Boolean contract) {
		this.contract = contract;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getDeductionNo() {
		return deductionNo;
	}

	public void setDeductionNo(String deductionNo) {
		this.deductionNo = deductionNo;
	}

	public String getDeductionName() {
		return deductionName;
	}

	public void setDeductionName(String deductionName) {
		this.deductionName = deductionName;
	}

}
