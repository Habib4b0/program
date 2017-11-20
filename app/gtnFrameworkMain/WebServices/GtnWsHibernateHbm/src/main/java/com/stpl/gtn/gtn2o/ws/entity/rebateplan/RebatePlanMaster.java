package com.stpl.gtn.gtn2o.ws.entity.rebateplan;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.cdr.CdrModel;
import com.stpl.gtn.gtn2o.ws.entity.netsalesformula.NetSalesFormulaMaster;

public class RebatePlanMaster implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rebatePlanMasterSid;
	private HelperTable helperTableByRebateRangeBasedOn;
	private NetSalesFormulaMaster netSalesFormulaMaster;
	private HelperTable helperTableByRebatePlanStatus;
	private HelperTable helperTableByRebateBasedOn;
	private HelperTable helperTableByFormulaType;
	private CdrModel cdrModel;
	private HelperTable helperTableByRebatePlanType;
	private HelperTable helperTableByRebateStructure;
	private String rebatePlanId;
	private String rebatePlanNo;
	private String rebatePlanName;
	private String rebateRule;
	private Integer manfCompanyMasterSid;
	private Character bogoEligible;
	private Character selfGrowthIndicator;
	private String selfGrowthReference;
	private Date selfGrowthFrom;
	private Date selfGrowthTo;
	private Character marketShareIndicator;
	private String marketShareReference;
	private Date marketShareFrom;
	private Date marketShareTo;
	private String secondaryRebatePlanId;
	private String secondaryRebatePlanNo;
	private String secondaryRebatePlanName;
	private String internalNotes;
	private char inboundStatus;
	private boolean recordLockStatus;
	private String batchId;
	private String source;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;
	private Set rsDetailses = new HashSet(0);
	private Set rebatePlanTiers = new HashSet(0);
	private Set rsContractDetailses = new HashSet(0);

	public RebatePlanMaster() {
	}

	public RebatePlanMaster(int rebatePlanMasterSid, HelperTable helperTableByRebateRangeBasedOn,
			HelperTable helperTableByRebatePlanStatus, HelperTable helperTableByRebateBasedOn,
			HelperTable helperTableByRebateStructure, String rebatePlanId, String rebatePlanNo, char inboundStatus,
			boolean recordLockStatus, int createdBy, Date createdDate, int modifiedBy, Date modifiedDate) {
		this.rebatePlanMasterSid = rebatePlanMasterSid;
		this.helperTableByRebateRangeBasedOn = helperTableByRebateRangeBasedOn;
		this.helperTableByRebatePlanStatus = helperTableByRebatePlanStatus;
		this.helperTableByRebateBasedOn = helperTableByRebateBasedOn;
		this.helperTableByRebateStructure = helperTableByRebateStructure;
		this.rebatePlanId = rebatePlanId;
		this.rebatePlanNo = rebatePlanNo;
		this.inboundStatus = inboundStatus;
		this.recordLockStatus = recordLockStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public RebatePlanMaster(int rebatePlanMasterSid, HelperTable helperTableByRebateRangeBasedOn,
			NetSalesFormulaMaster netSalesFormulaMaster, HelperTable helperTableByRebatePlanStatus,
			HelperTable helperTableByRebateBasedOn, HelperTable helperTableByFormulaType, CdrModel cdrModel,
			HelperTable helperTableByRebatePlanType, HelperTable helperTableByRebateStructure, String rebatePlanId,
			String rebatePlanNo, String rebatePlanName, String rebateRule, Integer manfCompanyMasterSid,
			Character bogoEligible, Character selfGrowthIndicator, String selfGrowthReference, Date selfGrowthFrom,
			Date selfGrowthTo, Character marketShareIndicator, String marketShareReference, Date marketShareFrom,
			Date marketShareTo, String secondaryRebatePlanId, String secondaryRebatePlanNo,
			String secondaryRebatePlanName, String internalNotes, char inboundStatus, boolean recordLockStatus,
			String batchId, String source, int createdBy, Date createdDate, int modifiedBy, Date modifiedDate,
			Set rsDetailses, Set rebatePlanTiers, Set rsContractDetailses) {
		this.rebatePlanMasterSid = rebatePlanMasterSid;
		this.helperTableByRebateRangeBasedOn = helperTableByRebateRangeBasedOn;
		this.netSalesFormulaMaster = netSalesFormulaMaster;
		this.helperTableByRebatePlanStatus = helperTableByRebatePlanStatus;
		this.helperTableByRebateBasedOn = helperTableByRebateBasedOn;
		this.helperTableByFormulaType = helperTableByFormulaType;
		this.cdrModel = cdrModel;
		this.helperTableByRebatePlanType = helperTableByRebatePlanType;
		this.helperTableByRebateStructure = helperTableByRebateStructure;
		this.rebatePlanId = rebatePlanId;
		this.rebatePlanNo = rebatePlanNo;
		this.rebatePlanName = rebatePlanName;
		this.rebateRule = rebateRule;
		this.manfCompanyMasterSid = manfCompanyMasterSid;
		this.bogoEligible = bogoEligible;
		this.selfGrowthIndicator = selfGrowthIndicator;
		this.selfGrowthReference = selfGrowthReference;
		this.selfGrowthFrom = selfGrowthFrom;
		this.selfGrowthTo = selfGrowthTo;
		this.marketShareIndicator = marketShareIndicator;
		this.marketShareReference = marketShareReference;
		this.marketShareFrom = marketShareFrom;
		this.marketShareTo = marketShareTo;
		this.secondaryRebatePlanId = secondaryRebatePlanId;
		this.secondaryRebatePlanNo = secondaryRebatePlanNo;
		this.secondaryRebatePlanName = secondaryRebatePlanName;
		this.internalNotes = internalNotes;
		this.inboundStatus = inboundStatus;
		this.recordLockStatus = recordLockStatus;
		this.batchId = batchId;
		this.source = source;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.rsDetailses = rsDetailses;
		this.rebatePlanTiers = rebatePlanTiers;
		this.rsContractDetailses = rsContractDetailses;
	}

	public int getRebatePlanMasterSid() {
		return this.rebatePlanMasterSid;
	}

	public void setRebatePlanMasterSid(int rebatePlanMasterSid) {
		this.rebatePlanMasterSid = rebatePlanMasterSid;
	}

	public HelperTable getHelperTableByRebateRangeBasedOn() {
		return this.helperTableByRebateRangeBasedOn;
	}

	public void setHelperTableByRebateRangeBasedOn(HelperTable helperTableByRebateRangeBasedOn) {
		this.helperTableByRebateRangeBasedOn = helperTableByRebateRangeBasedOn;
	}

	public NetSalesFormulaMaster getNetSalesFormulaMaster() {
		return this.netSalesFormulaMaster;
	}

	public void setNetSalesFormulaMaster(NetSalesFormulaMaster netSalesFormulaMaster) {
		this.netSalesFormulaMaster = netSalesFormulaMaster;
	}

	public HelperTable getHelperTableByRebatePlanStatus() {
		return this.helperTableByRebatePlanStatus;
	}

	public void setHelperTableByRebatePlanStatus(HelperTable helperTableByRebatePlanStatus) {
		this.helperTableByRebatePlanStatus = helperTableByRebatePlanStatus;
	}

	public HelperTable getHelperTableByRebateBasedOn() {
		return this.helperTableByRebateBasedOn;
	}

	public void setHelperTableByRebateBasedOn(HelperTable helperTableByRebateBasedOn) {
		this.helperTableByRebateBasedOn = helperTableByRebateBasedOn;
	}

	public HelperTable getHelperTableByFormulaType() {
		return this.helperTableByFormulaType;
	}

	public void setHelperTableByFormulaType(HelperTable helperTableByFormulaType) {
		this.helperTableByFormulaType = helperTableByFormulaType;
	}

	public CdrModel getCdrModel() {
		return this.cdrModel;
	}

	public void setCdrModel(CdrModel cdrModel) {
		this.cdrModel = cdrModel;
	}

	public HelperTable getHelperTableByRebatePlanType() {
		return this.helperTableByRebatePlanType;
	}

	public void setHelperTableByRebatePlanType(HelperTable helperTableByRebatePlanType) {
		this.helperTableByRebatePlanType = helperTableByRebatePlanType;
	}

	public HelperTable getHelperTableByRebateStructure() {
		return this.helperTableByRebateStructure;
	}

	public void setHelperTableByRebateStructure(HelperTable helperTableByRebateStructure) {
		this.helperTableByRebateStructure = helperTableByRebateStructure;
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

	public String getRebateRule() {
		return this.rebateRule;
	}

	public void setRebateRule(String rebateRule) {
		this.rebateRule = rebateRule;
	}

	public Integer getManfCompanyMasterSid() {
		return this.manfCompanyMasterSid;
	}

	public void setManfCompanyMasterSid(Integer manfCompanyMasterSid) {
		this.manfCompanyMasterSid = manfCompanyMasterSid;
	}

	public Character getBogoEligible() {
		return this.bogoEligible;
	}

	public void setBogoEligible(Character bogoEligible) {
		this.bogoEligible = bogoEligible;
	}

	public Character getSelfGrowthIndicator() {
		return this.selfGrowthIndicator;
	}

	public void setSelfGrowthIndicator(Character selfGrowthIndicator) {
		this.selfGrowthIndicator = selfGrowthIndicator;
	}

	public String getSelfGrowthReference() {
		return this.selfGrowthReference;
	}

	public void setSelfGrowthReference(String selfGrowthReference) {
		this.selfGrowthReference = selfGrowthReference;
	}

	public Date getSelfGrowthFrom() {
		return this.selfGrowthFrom;
	}

	public void setSelfGrowthFrom(Date selfGrowthFrom) {
		this.selfGrowthFrom = selfGrowthFrom;
	}

	public Date getSelfGrowthTo() {
		return this.selfGrowthTo;
	}

	public void setSelfGrowthTo(Date selfGrowthTo) {
		this.selfGrowthTo = selfGrowthTo;
	}

	public Character getMarketShareIndicator() {
		return this.marketShareIndicator;
	}

	public void setMarketShareIndicator(Character marketShareIndicator) {
		this.marketShareIndicator = marketShareIndicator;
	}

	public String getMarketShareReference() {
		return this.marketShareReference;
	}

	public void setMarketShareReference(String marketShareReference) {
		this.marketShareReference = marketShareReference;
	}

	public Date getMarketShareFrom() {
		return this.marketShareFrom;
	}

	public void setMarketShareFrom(Date marketShareFrom) {
		this.marketShareFrom = marketShareFrom;
	}

	public Date getMarketShareTo() {
		return this.marketShareTo;
	}

	public void setMarketShareTo(Date marketShareTo) {
		this.marketShareTo = marketShareTo;
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

	public String getInternalNotes() {
		return this.internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		this.internalNotes = internalNotes;
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

	public Set getRsDetailses() {
		return this.rsDetailses;
	}

	public void setRsDetailses(Set rsDetailses) {
		this.rsDetailses = rsDetailses;
	}

	public Set getRebatePlanTiers() {
		return this.rebatePlanTiers;
	}

	public void setRebatePlanTiers(Set rebatePlanTiers) {
		this.rebatePlanTiers = rebatePlanTiers;
	}

	public Set getRsContractDetailses() {
		return rsContractDetailses;
	}

	public void setRsContractDetailses(Set rsContractDetailses) {
		this.rsContractDetailses = rsContractDetailses;
	}

}
