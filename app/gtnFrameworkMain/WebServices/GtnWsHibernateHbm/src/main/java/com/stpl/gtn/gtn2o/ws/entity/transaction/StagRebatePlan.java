package com.stpl.gtn.gtn2o.ws.entity.transaction;

import java.util.Date;

@SuppressWarnings("serial")
public class StagRebatePlan implements java.io.Serializable {

	private int stagRebatePlanSid;
	private String rebatePlanIntfid;
	private String rebatePlanId;
	private String rebatePlanNo;
	private String rebatePlanName;
	private String rebatePlanStatus;
	private String rebatePlanType;
	private String internalNotes;
	private String formulaType;
	private String bogoEligible;
	private String selfGrowthIndicator;
	private String selfGrowthReference;
	private String selfGrowthFrom;
	private String selfGrowthTo;
	private String marketShareIndicator;
	private String marketShareReference;
	private String marketShareFrom;
	private String marketShareTo;
	private String tierFormulaId;
	private String tierFormulaNo;
	private String tierFormulaName;
	private String rebateStructure;
	private String rebateRangeBasedOn;
	private String rebateBasedOn;
	private String rebateRule;
	private String rebatePlanTierId;
	private String tierFrom;
	private String tierTo;
	private String tierLevel;
	private String tierOperator;
	private String tierValue;
	private String freeAmount;
	private String tierTolerance;
	private String secondaryRebatePlanId;
	private String secondaryRebatePlanNo;
	private String secondaryRebatePlanName;
	private String contractId;
	private String cfpId;
	private String mfpId;
	private String ifpId;
	private String priceScheduleId;
	private String rebateScheduleId;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private String batchId;
	private String source;
	private String addChgDelIndicator;

	public StagRebatePlan() {
	}

	public StagRebatePlan(int stagRebatePlanSid, String rebatePlanIntfid, String rebatePlanId, String rebatePlanStatus,
			String rebateStructure, String rebateRangeBasedOn, String rebateBasedOn, String rebatePlanTierId,
			String tierFrom, String tierLevel, String tierOperator, String tierValue, String contractId, String ifpId,
			String priceScheduleId, String rebateScheduleId, String batchId, String addChgDelIndicator) {
		this.stagRebatePlanSid = stagRebatePlanSid;
		this.rebatePlanIntfid = rebatePlanIntfid;
		this.rebatePlanId = rebatePlanId;
		this.rebatePlanStatus = rebatePlanStatus;
		this.rebateStructure = rebateStructure;
		this.rebateRangeBasedOn = rebateRangeBasedOn;
		this.rebateBasedOn = rebateBasedOn;
		this.rebatePlanTierId = rebatePlanTierId;
		this.tierFrom = tierFrom;
		this.tierLevel = tierLevel;
		this.tierOperator = tierOperator;
		this.tierValue = tierValue;
		this.contractId = contractId;
		this.ifpId = ifpId;
		this.priceScheduleId = priceScheduleId;
		this.rebateScheduleId = rebateScheduleId;
		this.batchId = batchId;
		this.addChgDelIndicator = addChgDelIndicator;
	}

	public StagRebatePlan(int stagRebatePlanSid, String rebatePlanIntfid, String rebatePlanId, String rebatePlanNo,
			String rebatePlanName, String rebatePlanStatus, String rebatePlanType, String internalNotes,
			String formulaType, String bogoEligible, String selfGrowthIndicator, String selfGrowthReference,
			String selfGrowthFrom, String selfGrowthTo, String marketShareIndicator, String marketShareReference,
			String marketShareFrom, String marketShareTo, String tierFormulaId, String tierFormulaNo,
			String tierFormulaName, String rebateStructure, String rebateRangeBasedOn, String rebateBasedOn,
			String rebateRule, String rebatePlanTierId, String tierFrom, String tierTo, String tierLevel,
			String tierOperator, String tierValue, String freeAmount, String tierTolerance,
			String secondaryRebatePlanId, String secondaryRebatePlanNo, String secondaryRebatePlanName,
			String contractId, String cfpId, String mfpId, String ifpId, String priceScheduleId,
			String rebateScheduleId, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate,
			String batchId, String source, String addChgDelIndicator) {
		this.stagRebatePlanSid = stagRebatePlanSid;
		this.rebatePlanIntfid = rebatePlanIntfid;
		this.rebatePlanId = rebatePlanId;
		this.rebatePlanNo = rebatePlanNo;
		this.rebatePlanName = rebatePlanName;
		this.rebatePlanStatus = rebatePlanStatus;
		this.rebatePlanType = rebatePlanType;
		this.internalNotes = internalNotes;
		this.formulaType = formulaType;
		this.bogoEligible = bogoEligible;
		this.selfGrowthIndicator = selfGrowthIndicator;
		this.selfGrowthReference = selfGrowthReference;
		this.selfGrowthFrom = selfGrowthFrom;
		this.selfGrowthTo = selfGrowthTo;
		this.marketShareIndicator = marketShareIndicator;
		this.marketShareReference = marketShareReference;
		this.marketShareFrom = marketShareFrom;
		this.marketShareTo = marketShareTo;
		this.tierFormulaId = tierFormulaId;
		this.tierFormulaNo = tierFormulaNo;
		this.tierFormulaName = tierFormulaName;
		this.rebateStructure = rebateStructure;
		this.rebateRangeBasedOn = rebateRangeBasedOn;
		this.rebateBasedOn = rebateBasedOn;
		this.rebateRule = rebateRule;
		this.rebatePlanTierId = rebatePlanTierId;
		this.tierFrom = tierFrom;
		this.tierTo = tierTo;
		this.tierLevel = tierLevel;
		this.tierOperator = tierOperator;
		this.tierValue = tierValue;
		this.freeAmount = freeAmount;
		this.tierTolerance = tierTolerance;
		this.secondaryRebatePlanId = secondaryRebatePlanId;
		this.secondaryRebatePlanNo = secondaryRebatePlanNo;
		this.secondaryRebatePlanName = secondaryRebatePlanName;
		this.contractId = contractId;
		this.cfpId = cfpId;
		this.mfpId = mfpId;
		this.ifpId = ifpId;
		this.priceScheduleId = priceScheduleId;
		this.rebateScheduleId = rebateScheduleId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.batchId = batchId;
		this.source = source;
		this.addChgDelIndicator = addChgDelIndicator;
	}

	public int getStagRebatePlanSid() {
		return this.stagRebatePlanSid;
	}

	public void setStagRebatePlanSid(int stagRebatePlanSid) {
		this.stagRebatePlanSid = stagRebatePlanSid;
	}

	public String getRebatePlanIntfid() {
		return this.rebatePlanIntfid;
	}

	public void setRebatePlanIntfid(String rebatePlanIntfid) {
		this.rebatePlanIntfid = rebatePlanIntfid;
	}

	public String getRebatePlanId() {
		return this.rebatePlanId;
	}

	public void setRebatePlanId(String rebatePlanId) {
		this.rebatePlanId = rebatePlanId;
	}

	public String getRebatePlanNo() {
		return this.rebatePlanNo;
	}

	public void setRebatePlanNo(String rebatePlanNo) {
		this.rebatePlanNo = rebatePlanNo;
	}

	public String getRebatePlanName() {
		return this.rebatePlanName;
	}

	public void setRebatePlanName(String rebatePlanName) {
		this.rebatePlanName = rebatePlanName;
	}

	public String getRebatePlanStatus() {
		return this.rebatePlanStatus;
	}

	public void setRebatePlanStatus(String rebatePlanStatus) {
		this.rebatePlanStatus = rebatePlanStatus;
	}

	public String getRebatePlanType() {
		return this.rebatePlanType;
	}

	public void setRebatePlanType(String rebatePlanType) {
		this.rebatePlanType = rebatePlanType;
	}

	public String getInternalNotes() {
		return this.internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		this.internalNotes = internalNotes;
	}

	public String getFormulaType() {
		return this.formulaType;
	}

	public void setFormulaType(String formulaType) {
		this.formulaType = formulaType;
	}

	public String getBogoEligible() {
		return this.bogoEligible;
	}

	public void setBogoEligible(String bogoEligible) {
		this.bogoEligible = bogoEligible;
	}

	public String getSelfGrowthIndicator() {
		return this.selfGrowthIndicator;
	}

	public void setSelfGrowthIndicator(String selfGrowthIndicator) {
		this.selfGrowthIndicator = selfGrowthIndicator;
	}

	public String getSelfGrowthReference() {
		return this.selfGrowthReference;
	}

	public void setSelfGrowthReference(String selfGrowthReference) {
		this.selfGrowthReference = selfGrowthReference;
	}

	public String getSelfGrowthFrom() {
		return this.selfGrowthFrom;
	}

	public void setSelfGrowthFrom(String selfGrowthFrom) {
		this.selfGrowthFrom = selfGrowthFrom;
	}

	public String getSelfGrowthTo() {
		return this.selfGrowthTo;
	}

	public void setSelfGrowthTo(String selfGrowthTo) {
		this.selfGrowthTo = selfGrowthTo;
	}

	public String getMarketShareIndicator() {
		return this.marketShareIndicator;
	}

	public void setMarketShareIndicator(String marketShareIndicator) {
		this.marketShareIndicator = marketShareIndicator;
	}

	public String getMarketShareReference() {
		return this.marketShareReference;
	}

	public void setMarketShareReference(String marketShareReference) {
		this.marketShareReference = marketShareReference;
	}

	public String getMarketShareFrom() {
		return this.marketShareFrom;
	}

	public void setMarketShareFrom(String marketShareFrom) {
		this.marketShareFrom = marketShareFrom;
	}

	public String getMarketShareTo() {
		return this.marketShareTo;
	}

	public void setMarketShareTo(String marketShareTo) {
		this.marketShareTo = marketShareTo;
	}

	public String getTierFormulaId() {
		return this.tierFormulaId;
	}

	public void setTierFormulaId(String tierFormulaId) {
		this.tierFormulaId = tierFormulaId;
	}

	public String getTierFormulaNo() {
		return this.tierFormulaNo;
	}

	public void setTierFormulaNo(String tierFormulaNo) {
		this.tierFormulaNo = tierFormulaNo;
	}

	public String getTierFormulaName() {
		return this.tierFormulaName;
	}

	public void setTierFormulaName(String tierFormulaName) {
		this.tierFormulaName = tierFormulaName;
	}

	public String getRebateStructure() {
		return this.rebateStructure;
	}

	public void setRebateStructure(String rebateStructure) {
		this.rebateStructure = rebateStructure;
	}

	public String getRebateRangeBasedOn() {
		return this.rebateRangeBasedOn;
	}

	public void setRebateRangeBasedOn(String rebateRangeBasedOn) {
		this.rebateRangeBasedOn = rebateRangeBasedOn;
	}

	public String getRebateBasedOn() {
		return this.rebateBasedOn;
	}

	public void setRebateBasedOn(String rebateBasedOn) {
		this.rebateBasedOn = rebateBasedOn;
	}

	public String getRebateRule() {
		return this.rebateRule;
	}

	public void setRebateRule(String rebateRule) {
		this.rebateRule = rebateRule;
	}

	public String getRebatePlanTierId() {
		return this.rebatePlanTierId;
	}

	public void setRebatePlanTierId(String rebatePlanTierId) {
		this.rebatePlanTierId = rebatePlanTierId;
	}

	public String getTierFrom() {
		return this.tierFrom;
	}

	public void setTierFrom(String tierFrom) {
		this.tierFrom = tierFrom;
	}

	public String getTierTo() {
		return this.tierTo;
	}

	public void setTierTo(String tierTo) {
		this.tierTo = tierTo;
	}

	public String getTierLevel() {
		return this.tierLevel;
	}

	public void setTierLevel(String tierLevel) {
		this.tierLevel = tierLevel;
	}

	public String getTierOperator() {
		return this.tierOperator;
	}

	public void setTierOperator(String tierOperator) {
		this.tierOperator = tierOperator;
	}

	public String getTierValue() {
		return this.tierValue;
	}

	public void setTierValue(String tierValue) {
		this.tierValue = tierValue;
	}

	public String getFreeAmount() {
		return this.freeAmount;
	}

	public void setFreeAmount(String freeAmount) {
		this.freeAmount = freeAmount;
	}

	public String getTierTolerance() {
		return this.tierTolerance;
	}

	public void setTierTolerance(String tierTolerance) {
		this.tierTolerance = tierTolerance;
	}

	public String getSecondaryRebatePlanId() {
		return this.secondaryRebatePlanId;
	}

	public void setSecondaryRebatePlanId(String secondaryRebatePlanId) {
		this.secondaryRebatePlanId = secondaryRebatePlanId;
	}

	public String getSecondaryRebatePlanNo() {
		return this.secondaryRebatePlanNo;
	}

	public void setSecondaryRebatePlanNo(String secondaryRebatePlanNo) {
		this.secondaryRebatePlanNo = secondaryRebatePlanNo;
	}

	public String getSecondaryRebatePlanName() {
		return this.secondaryRebatePlanName;
	}

	public void setSecondaryRebatePlanName(String secondaryRebatePlanName) {
		this.secondaryRebatePlanName = secondaryRebatePlanName;
	}

	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getCfpId() {
		return this.cfpId;
	}

	public void setCfpId(String cfpId) {
		this.cfpId = cfpId;
	}

	public String getMfpId() {
		return this.mfpId;
	}

	public void setMfpId(String mfpId) {
		this.mfpId = mfpId;
	}

	public String getIfpId() {
		return this.ifpId;
	}

	public void setIfpId(String ifpId) {
		this.ifpId = ifpId;
	}

	public String getPriceScheduleId() {
		return this.priceScheduleId;
	}

	public void setPriceScheduleId(String priceScheduleId) {
		this.priceScheduleId = priceScheduleId;
	}

	public String getRebateScheduleId() {
		return this.rebateScheduleId;
	}

	public void setRebateScheduleId(String rebateScheduleId) {
		this.rebateScheduleId = rebateScheduleId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAddChgDelIndicator() {
		return this.addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		this.addChgDelIndicator = addChgDelIndicator;
	}

}
