package com.stpl.gtn.gtn2o.ws.entity.rebateplan;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;

public class RebatePlanTier implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rebatePlanTierSid;
	private HelperTable helperTable;
	private RebatePlanMaster rebatePlanMaster;
	private String rebatePlanTierId;
	private BigDecimal tierFrom;
	private BigDecimal tierTo;
	private String tierLevel;
	private BigDecimal tierValue;
	private BigDecimal freeAmount;
	private BigDecimal tierTolerance;
	private char inboundStatus;
	private boolean recordLockStatus;
	private String batchId;
	private String source;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;
	private String formulaNo;
	private String formulaName;
	private String secondaryRebatePlanNo;
	private String secondaryRebatePlanName;
	private Integer secondaryRebatePlanSid;
	private String itemPricingQualifierSid;
	private String formulaCalculation;
	private HelperTable helperTableReturnRateSid;

	public RebatePlanTier() {
	}

	public RebatePlanTier(int rebatePlanTierSid, HelperTable helperTable, RebatePlanMaster rebatePlanMaster,
			String rebatePlanTierId, BigDecimal tierFrom, String tierLevel, BigDecimal tierValue, char inboundStatus,
			boolean recordLockStatus, int createdBy, Date createdDate, int modifiedBy, Date modifiedDate) {
		this.rebatePlanTierSid = rebatePlanTierSid;
		this.helperTable = helperTable;
		this.rebatePlanMaster = rebatePlanMaster;
		this.rebatePlanTierId = rebatePlanTierId;
		this.tierFrom = tierFrom;
		this.tierLevel = tierLevel;
		this.tierValue = tierValue;
		this.inboundStatus = inboundStatus;
		this.recordLockStatus = recordLockStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public RebatePlanTier(int rebatePlanTierSid, HelperTable helperTable, RebatePlanMaster rebatePlanMaster,
			String rebatePlanTierId, BigDecimal tierFrom, BigDecimal tierTo, String tierLevel, BigDecimal tierValue,
			BigDecimal freeAmount, BigDecimal tierTolerance, char inboundStatus, boolean recordLockStatus,
			String batchId, String source, int createdBy, Date createdDate, int modifiedBy, Date modifiedDate,
			String formulaNo, String formulaName, String secondaryRebatePlanNo, String secondaryRebatePlanName,
			Integer secondaryRebatePlanSid, String itemPricingQualifierSid, HelperTable helperTableReturnRateSid,String formulaCalculation) {
		this.rebatePlanTierSid = rebatePlanTierSid;
		this.helperTable = helperTable;
		this.rebatePlanMaster = rebatePlanMaster;
		this.rebatePlanTierId = rebatePlanTierId;
		this.tierFrom = tierFrom;
		this.tierTo = tierTo;
		this.tierLevel = tierLevel;
		this.tierValue = tierValue;
		this.freeAmount = freeAmount;
		this.tierTolerance = tierTolerance;
		this.inboundStatus = inboundStatus;
		this.recordLockStatus = recordLockStatus;
		this.batchId = batchId;
		this.source = source;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.formulaNo = formulaNo;
		this.formulaName = formulaName;
		this.secondaryRebatePlanNo = secondaryRebatePlanNo;
		this.secondaryRebatePlanName = secondaryRebatePlanName;
		this.secondaryRebatePlanSid = secondaryRebatePlanSid;
		this.itemPricingQualifierSid = itemPricingQualifierSid;
		this.formulaCalculation = formulaCalculation;
		this.helperTableReturnRateSid = helperTableReturnRateSid;
	}

	public int getRebatePlanTierSid() {
		return this.rebatePlanTierSid;
	}

	public void setRebatePlanTierSid(int rebatePlanTierSid) {
		this.rebatePlanTierSid = rebatePlanTierSid;
	}

	public HelperTable getHelperTable() {
		return this.helperTable;
	}

	public void setHelperTable(HelperTable helperTable) {
		this.helperTable = helperTable;
	}

	public RebatePlanMaster getRebatePlanMaster() {
		return this.rebatePlanMaster;
	}

	public void setRebatePlanMaster(RebatePlanMaster rebatePlanMaster) {
		this.rebatePlanMaster = rebatePlanMaster;
	}

	public String getRebatePlanTierId() {
		return this.rebatePlanTierId;
	}

	public void setRebatePlanTierId(String rebatePlanTierId) {
		this.rebatePlanTierId = rebatePlanTierId;
	}

	public BigDecimal getTierFrom() {
		return this.tierFrom;
	}

	public void setTierFrom(BigDecimal tierFrom) {
		this.tierFrom = tierFrom;
	}

	public BigDecimal getTierTo() {
		return this.tierTo;
	}

	public void setTierTo(BigDecimal tierTo) {
		this.tierTo = tierTo;
	}

	public String getTierLevel() {
		return this.tierLevel;
	}

	public void setTierLevel(String tierLevel) {
		this.tierLevel = tierLevel;
	}

	public BigDecimal getTierValue() {
		return this.tierValue;
	}

	public void setTierValue(BigDecimal tierValue) {
		this.tierValue = tierValue;
	}

	public BigDecimal getFreeAmount() {
		return this.freeAmount;
	}

	public void setFreeAmount(BigDecimal freeAmount) {
		this.freeAmount = freeAmount;
	}

	public BigDecimal getTierTolerance() {
		return this.tierTolerance;
	}

	public void setTierTolerance(BigDecimal tierTolerance) {
		this.tierTolerance = tierTolerance;
	}

	public char getInboundStatus() {
		return this.inboundStatus;
	}

	public void setInboundStatus(char inboundStatus) {
		this.inboundStatus = inboundStatus;
	}

	public boolean isRecordLockStatus() {
		return this.recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
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

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getFormulaNo() {
		return this.formulaNo;
	}

	public void setFormulaNo(String formulaNo) {
		this.formulaNo = formulaNo;
	}

	public String getFormulaName() {
		return this.formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
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

	public Integer getSecondaryRebatePlanSid() {
		return this.secondaryRebatePlanSid;
	}

	public void setSecondaryRebatePlanSid(Integer secondaryRebatePlanSid) {
		this.secondaryRebatePlanSid = secondaryRebatePlanSid;
	}

	public String getItemPricingQualifierSid() {
		return itemPricingQualifierSid;
	}

	public void setItemPricingQualifierSid(String itemPricingQualifierSid) {
		this.itemPricingQualifierSid = itemPricingQualifierSid;
	}
        
	public String getFormulaCalculation() {
		return formulaCalculation;
	}

	public void setFormulaCalculation(String formulaCalculation) {
		this.formulaCalculation = formulaCalculation;
	}

	public HelperTable getHelperTableReturnRateSid() {
		return helperTableReturnRateSid;
	}

	public void setHelperTableReturnRateSid(HelperTable helperTableReturnRateSid) {
		this.helperTableReturnRateSid = helperTableReturnRateSid;
	}

}