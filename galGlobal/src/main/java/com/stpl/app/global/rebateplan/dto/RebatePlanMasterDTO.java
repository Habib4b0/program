package com.stpl.app.global.rebateplan.dto;

import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class RebatePlanMasterDTO.
 */
public class RebatePlanMasterDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 4670854563399832410L;
    /**
     * The company familyplan system id.
     */
    private int companyFamilyplanSystemId;
    /**
     * The self growth indicator.
     */
    private String selfGrowthIndicator = StringUtils.EMPTY;
    /**
     * The rebate structure.
     */
    private HelperDTO rebateStructure = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
     public Integer manufacturer;

    public Integer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Integer manufacturer) {
        this.manufacturer = manufacturer;
    }
    /**
     * The rebate structure value.
     */
    private String rebateStructureValue;
    /**
     * The market share from.
     */
    private Date marketShareFrom;
    /**
     * The rebate schedule id.
     */
    private int rebateScheduleId;
    /**
     * The secondary rebate plan no.
     */
    private String secondaryRebatePlanNo = StringUtils.EMPTY;
    /**
     * The modified date.
     */
    private Date modifiedDate;
    /**
     * The rebate range based on.
     */
    private HelperDTO rebateRangeBasedOn = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * The rebate range based on value.
     */
    private String rebateRangeBasedOnValue;
    /**
     * The price schedule system id.
     */
    private int priceScheduleSystemId;
    /**
     * The item familyplan system id.
     */
    private int itemFamilyplanSystemId;
    /**
     * The rebate rule.
     */
    private String rebateRule = StringUtils.EMPTY;
    /**
     * The created date.
     */
    private Date createdDate;
    /**
     * The created by.
     */
    private String createdBy = StringUtils.EMPTY;
    /**
     * The inbound status.
     */
    private String inboundStatus = StringUtils.EMPTY;
    /**
     * The rebate based on.
     */
    private HelperDTO rebateBasedOn = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * The rebate based on Value.
     */
    private String rebateBasedOnValue;
    /**
     * The rebate plan type.
     */
    private HelperDTO rebatePlanType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * The rebate plan type Value.
     */
    private String rebatePlanTypeValue;
    /**
     * The rebate plan id.
     */
    private String rebatePlanId = StringUtils.EMPTY;
    /**
     * The modified by.
     */
    private String modifiedBy = StringUtils.EMPTY;
    /**
     * The secondary rebate plan id.
     */
    private String secondaryRebatePlanId = StringUtils.EMPTY;
    /**
     * The market share indicator.
     */
    private String marketShareIndicator = StringUtils.EMPTY;
    /**
     * The contract system id.
     */
    private int contractSystemId;
    /**
     * The tier formula id.
     */
    private String tierFormulaId;
    /**
     * The bogo eligible.
     */
    private String bogoEligible = StringUtils.EMPTY;
    /**
     * The market share to.
     */
    private Date marketShareTo;
    /**
     * The member familyplan system id.
     */
    private int memberFamilyplanSystemId;
    /**
     * The rebate plan status.
     */
    private HelperDTO rebatePlanStatus = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * The rebate plan status.
     */
    private String rebatePlanStatusValue ;
    /**
     * The rebate plan tier system id.
     */
    private int rebatePlanTierSystemId;
    /**
     * The market share reference.
     */
    private String marketShareReference = StringUtils.EMPTY;
    /**
     * The self growth from.
     */
    private Date selfGrowthFrom;
    /**
     * The internal notes.
     */
    private String internalNotes = StringUtils.EMPTY;
    /**
     * The secondary rebate plan name.
     */
    private String secondaryRebatePlanName = StringUtils.EMPTY;
    /**
     * The rebate plan system id.
     */
    private int rebatePlanSystemId;
    /**
     * The record lock status.
     */
    private boolean recordLockStatus;
    /**
     * The rebate plan name.
     */
    private String rebatePlanName = StringUtils.EMPTY;
    /**
     * The self growth reference.
     */
    private String selfGrowthReference = StringUtils.EMPTY;
    /**
     * The batch id.
     */
    private String batchId = StringUtils.EMPTY;
    /**
     * The formula type.
     */
    private String formulaType = StringUtils.EMPTY;
    /**
     * The self growth to.
     */
    private Date selfGrowthTo;
    /**
     * The rebate plan no.
     */
    private String rebatePlanNo = StringUtils.EMPTY;
    /**
     * The tier formula no.
     */
    private String tierFormulaNo;
    /**
     * The tier formula name.
     */
    private String tierFormulaName;
    /**
     * The tier formula system id.
     */
    private String tierFormulaSystemId = StringUtils.EMPTY;
    
    /**
     * The rebatePlanTierId
     */
    private String rebatePlanTierId = StringUtils.EMPTY;
    
    /**
     * The freeAmount
     */
    private String freeAmount = StringUtils.EMPTY;
    
     /**
     * The tierTolerance
     */
    private String tierTolerance = StringUtils.EMPTY;
    /**
     * The tierFrom
     */
    private String tierFrom = StringUtils.EMPTY;
    /**
     * The tierTo
     */
    private String tierTo = StringUtils.EMPTY;
    
     /**
     * The tierValue
     */
    private String tierValue = StringUtils.EMPTY;
    
    /**
     * The tierOperator
     */
    private HelperDTO tierOperator = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    /**
     * The tier level.
     */
    private Integer tierLevel;
    
    private String netSalesFormulaName=StringUtils.EMPTY;
    
    int netSalesFormulaSid=0;
    
    private String netSalesRule=StringUtils.EMPTY; 
    
    private int netSalesRuleSid = 0;
    
   private String tierFormula=StringUtils.EMPTY;   
   
   private Map<Integer,String> itemPricingQualifierMap = new HashMap<>();

    /**
     * The Constructor.
     *
     * @param tierFormulaSystemId the tier formula system id
     * @param tierFormulaId the tier formula id
     * @param tierFormulaNo the tier formula no
     * @param tierFormulaName the tier formula name
     */
    public RebatePlanMasterDTO(final String tierFormulaSystemId,
            final String tierFormulaId, final String tierFormulaNo, final String tierFormulaName) {
        this.tierFormulaSystemId = tierFormulaSystemId;
        this.tierFormulaId = tierFormulaId;
        this.tierFormulaNo = tierFormulaNo;
        this.tierFormulaName = tierFormulaName;
    }

    public String getNetSalesFormulaName() {
        return netSalesFormulaName;
    }

    public void setNetSalesFormulaName(String netSalesFormulaName) {
        this.netSalesFormulaName = netSalesFormulaName;
    }

    public int getNetSalesFormulaSid() {
        return netSalesFormulaSid;
    }

    public void setNetSalesFormulaSid(int netSalesFormulaSid) {
        this.netSalesFormulaSid = netSalesFormulaSid;
    }

    /**
     * The Constructor.
     */
    public RebatePlanMasterDTO() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Gets the company familyplan system id.
     *
     * @return the companyFamilyplanSystemId
     */
    public int getCompanyFamilyplanSystemId() {
        return companyFamilyplanSystemId;
    }

    /**
     * Sets the company familyplan system id.
     *
     * @param companyFamilyplanSystemId the companyFamilyplanSystemId to set
     */
    public void setCompanyFamilyplanSystemId(final int companyFamilyplanSystemId) {
        this.companyFamilyplanSystemId = companyFamilyplanSystemId;
    }

    /**
     * Gets the self growth indicator.
     *
     * @return the selfGrowthIndicator
     */
    public String getSelfGrowthIndicator() {
        return selfGrowthIndicator;
    }

    /**
     * Sets the self growth indicator.
     *
     * @param selfGrowthIndicator the selfGrowthIndicator to set
     */
    public void setSelfGrowthIndicator(final String selfGrowthIndicator) {
        this.selfGrowthIndicator = selfGrowthIndicator;
    }

    /**
     * Gets the rebate structure.
     *
     * @return the rebateStructure
     */
    public HelperDTO getRebateStructure() {
        return rebateStructure;
    }

    /**
     * Sets the rebate structure.
     *
     * @param rebateStructure the rebateStructure to set
     */
    public void setRebateStructure(final HelperDTO rebateStructure) {
        this.rebateStructure = rebateStructure;
    }

    /**
     * Gets the market share from.
     *
     * @return the marketShareFrom
     */
    public Date getMarketShareFrom() {
        return marketShareFrom;
    }

    /**
     * Sets the market share from.
     *
     * @param marketShareFrom the marketShareFrom to set
     */
    public void setMarketShareFrom(final Date marketShareFrom) {
        this.marketShareFrom = marketShareFrom;
    }

    /**
     * Gets the rebate schedule id.
     *
     * @return the rebateScheduleId
     */
    public int getRebateScheduleId() {
        return rebateScheduleId;
    }

    /**
     * Sets the rebate schedule id.
     *
     * @param rebateScheduleId the rebateScheduleId to set
     */
    public void setRebateScheduleId(final int rebateScheduleId) {
        this.rebateScheduleId = rebateScheduleId;
    }

    /**
     * Gets the secondary rebate plan no.
     *
     * @return the secondaryRebatePlanNo
     */
    public String getSecondaryRebatePlanNo() {
        return secondaryRebatePlanNo;
    }

    /**
     * Sets the secondary rebate plan no.
     *
     * @param secondaryRebatePlanNo the secondaryRebatePlanNo to set
     */
    public void setSecondaryRebatePlanNo(final String secondaryRebatePlanNo) {
        this.secondaryRebatePlanNo = secondaryRebatePlanNo;
    }

    /**
     * Gets the modified date.
     *
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the rebate range based on.
     *
     * @return the rebateRangeBasedOn
     */
    public HelperDTO getRebateRangeBasedOn() {
        return rebateRangeBasedOn;
    }

    /**
     * Sets the rebate range based on.
     *
     * @param rebateRangeBasedOn the rebateRangeBasedOn to set
     */
    public void setRebateRangeBasedOn(final HelperDTO rebateRangeBasedOn) {
        this.rebateRangeBasedOn = rebateRangeBasedOn;
    }

    /**
     * Gets the price schedule system id.
     *
     * @return the priceScheduleSystemId
     */
    public int getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    /**
     * Sets the price schedule system id.
     *
     * @param priceScheduleSystemId the priceScheduleSystemId to set
     */
    public void setPriceScheduleSystemId(final int priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    /**
     * Gets the item familyplan system id.
     *
     * @return the itemFamilyplanSystemId
     */
    public int getItemFamilyplanSystemId() {
        return itemFamilyplanSystemId;
    }

    /**
     * Sets the item familyplan system id.
     *
     * @param itemFamilyplanSystemId the itemFamilyplanSystemId to set
     */
    public void setItemFamilyplanSystemId(final int itemFamilyplanSystemId) {
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
    }

    /**
     * Gets the rebate rule.
     *
     * @return the rebateRule
     */
    public String getRebateRule() {
        return rebateRule;
    }

    /**
     * Sets the rebate rule.
     *
     * @param rebateRule the rebateRule to set
     */
    public void setRebateRule(final String rebateRule) {
        this.rebateRule = rebateRule;
    }

    /**
     * Gets the created date.
     *
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the created by.
     *
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the inbound status.
     *
     * @return the inboundStatus
     */
    public String getInboundStatus() {
        return inboundStatus;
    }

    /**
     * Sets the inbound status.
     *
     * @param inboundStatus the inboundStatus to set
     */
    public void setInboundStatus(final String inboundStatus) {
        this.inboundStatus = inboundStatus;
    }

    /**
     * Gets the rebate based on.
     *
     * @return the rebateBasedOn
     */
    public HelperDTO getRebateBasedOn() {
        return rebateBasedOn;
    }

    /**
     * Sets the rebate based on.
     *
     * @param rebateBasedOn the rebateBasedOn to set
     */
    public void setRebateBasedOn(final HelperDTO rebateBasedOn) {
        this.rebateBasedOn = rebateBasedOn;
    }

    /**
     * Gets the rebate plan type.
     *
     * @return the rebatePlanType
     */
    public HelperDTO getRebatePlanType() {
        return rebatePlanType;
    }

    /**
     * Sets the rebate plan type.
     *
     * @param rebatePlanType the rebatePlanType to set
     */
    public void setRebatePlanType(final HelperDTO rebatePlanType) {
        this.rebatePlanType = rebatePlanType;
    }

    /**
     * Gets the rebate plan id.
     *
     * @return the rebatePlanId
     */
    public String getRebatePlanId() {
        return rebatePlanId;
    }

    /**
     * Sets the rebate plan id.
     *
     * @param rebatePlanId the rebatePlanId to set
     */
    public void setRebatePlanId(final String rebatePlanId) {
        this.rebatePlanId = rebatePlanId;
    }

    /**
     * Gets the modified by.
     *
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the modified by.
     *
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(final String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Gets the secondary rebate plan id.
     *
     * @return the secondaryRebatePlanId
     */
    public String getSecondaryRebatePlanId() {
        return secondaryRebatePlanId;
    }

    /**
     * Sets the secondary rebate plan id.
     *
     * @param secondaryRebatePlanId the secondaryRebatePlanId to set
     */
    public void setSecondaryRebatePlanId(final String secondaryRebatePlanId) {
        this.secondaryRebatePlanId = secondaryRebatePlanId;
    }

    /**
     * Gets the market share indicator.
     *
     * @return the marketShareIndicator
     */
    public String getMarketShareIndicator() {
        return marketShareIndicator;
    }

    /**
     * Sets the market share indicator.
     *
     * @param marketShareIndicator the marketShareIndicator to set
     */
    public void setMarketShareIndicator(final String marketShareIndicator) {
        this.marketShareIndicator = marketShareIndicator;
    }

    /**
     * Gets the contract system id.
     *
     * @return the contractSystemId
     */
    public int getContractSystemId() {
        return contractSystemId;
    }

    /**
     * Sets the contract system id.
     *
     * @param contractSystemId the contractSystemId to set
     */
    public void setContractSystemId(final int contractSystemId) {
        this.contractSystemId = contractSystemId;
    }

    /**
     * Gets the tier formula id.
     *
     * @return the tierFormulaId
     */
    public String getTierFormulaId() {
        return tierFormulaId;
    }

    /**
     * Sets the tier formula id.
     *
     * @param tierFormulaId the tierFormulaId to set
     */
    public void setTierFormulaId(final String tierFormulaId) {
        this.tierFormulaId = tierFormulaId;
    }

    /**
     * Gets the bogo eligible.
     *
     * @return the bogoEligible
     */
    public String getBogoEligible() {
        return bogoEligible;
    }

    /**
     * Sets the bogo eligible.
     *
     * @param bogoEligible the bogoEligible to set
     */
    public void setBogoEligible(final String bogoEligible) {
        this.bogoEligible = bogoEligible;
    }

    /**
     * Gets the market share to.
     *
     * @return the marketShareTo
     */
    public Date getMarketShareTo() {
        return marketShareTo;
    }

    /**
     * Sets the market share to.
     *
     * @param marketShareTo the marketShareTo to set
     */
    public void setMarketShareTo(final Date marketShareTo) {
        this.marketShareTo = marketShareTo;
    }

    /**
     * Gets the member familyplan system id.
     *
     * @return the memberFamilyplanSystemId
     */
    public int getMemberFamilyplanSystemId() {
        return memberFamilyplanSystemId;
    }

    /**
     * Sets the member familyplan system id.
     *
     * @param memberFamilyplanSystemId the memberFamilyplanSystemId to set
     */
    public void setMemberFamilyplanSystemId(final int memberFamilyplanSystemId) {
        this.memberFamilyplanSystemId = memberFamilyplanSystemId;
    }

    /**
     * Gets the rebate plan status.
     *
     * @return the rebatePlanStatus
     */
    public HelperDTO getRebatePlanStatus() {
        return rebatePlanStatus;
    }

    /**
     * Sets the rebate plan status.
     *
     * @param rebatePlanStatus the rebatePlanStatus to set
     */
    public void setRebatePlanStatus(final HelperDTO rebatePlanStatus) {
        this.rebatePlanStatus = rebatePlanStatus;
    }

    /**
     * Gets the rebate plan tier system id.
     *
     * @return the rebatePlanTierSystemId
     */
    public int getRebatePlanTierSystemId() {
        return rebatePlanTierSystemId;
    }

    /**
     * Sets the rebate plan tier system id.
     *
     * @param rebatePlanTierSystemId the rebatePlanTierSystemId to set
     */
    public void setRebatePlanTierSystemId(final int rebatePlanTierSystemId) {
        this.rebatePlanTierSystemId = rebatePlanTierSystemId;
    }

    /**
     * Gets the market share reference.
     *
     * @return the marketShareReference
     */
    public String getMarketShareReference() {
        return marketShareReference;
    }

    /**
     * Sets the market share reference.
     *
     * @param marketShareReference the marketShareReference to set
     */
    public void setMarketShareReference(final String marketShareReference) {
        this.marketShareReference = marketShareReference;
    }

    /**
     * Gets the self growth from.
     *
     * @return the selfGrowthFrom
     */
    public Date getSelfGrowthFrom() {
        return selfGrowthFrom;
    }

    /**
     * Sets the self growth from.
     *
     * @param selfGrowthFrom the selfGrowthFrom to set
     */
    public void setSelfGrowthFrom(final Date selfGrowthFrom) {
        this.selfGrowthFrom = selfGrowthFrom;
    }

    /**
     * Gets the internal notes.
     *
     * @return the internalNotes
     */
    public String getInternalNotes() {
        return internalNotes;
    }

    /**
     * Sets the internal notes.
     *
     * @param internalNotes the internalNotes to set
     */
    public void setInternalNotes(final String internalNotes) {
        this.internalNotes = internalNotes;
    }

    /**
     * Gets the secondary rebate plan name.
     *
     * @return the secondaryRebatePlanName
     */
    public String getSecondaryRebatePlanName() {
        return secondaryRebatePlanName;
    }

    /**
     * Sets the secondary rebate plan name.
     *
     * @param secondaryRebatePlanName the secondaryRebatePlanName to set
     */
    public void setSecondaryRebatePlanName(final String secondaryRebatePlanName) {
        this.secondaryRebatePlanName = secondaryRebatePlanName;
    }

    /**
     * Gets the rebate plan system id.
     *
     * @return the rebatePlanSystemId
     */
    public int getRebatePlanSystemId() {
        return rebatePlanSystemId;
    }

    /**
     * Sets the rebate plan system id.
     *
     * @param rebatePlanSystemId the rebatePlanSystemId to set
     */
    public void setRebatePlanSystemId(final int rebatePlanSystemId) {
        this.rebatePlanSystemId = rebatePlanSystemId;
    }

    /**
     * Gets the record lock status.
     *
     * @return the recordLockStatus
     */
    public boolean getRecordLockStatus() {
        return recordLockStatus;
    }

    /**
     * Sets the record lock status.
     *
     * @param recordLockStatus the recordLockStatus to set
     */
    public void setRecordLockStatus(final boolean recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    /**
     * Gets the rebate plan name.
     *
     * @return the rebatePlanName
     */
    public String getRebatePlanName() {
        return rebatePlanName;
    }

    /**
     * Sets the rebate plan name.
     *
     * @param rebatePlanName the rebatePlanName to set
     */
    public void setRebatePlanName(final String rebatePlanName) {
        this.rebatePlanName = rebatePlanName;
    }

    /**
     * Gets the self growth reference.
     *
     * @return the selfGrowthReference
     */
    public String getSelfGrowthReference() {
        return selfGrowthReference;
    }

    /**
     * Sets the self growth reference.
     *
     * @param selfGrowthReference the selfGrowthReference to set
     */
    public void setSelfGrowthReference(final String selfGrowthReference) {
        this.selfGrowthReference = selfGrowthReference;
    }

    /**
     * Gets the batch id.
     *
     * @return the batchId
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * Sets the batch id.
     *
     * @param batchId the batchId to set
     */
    public void setBatchId(final String batchId) {
        this.batchId = batchId;
    }

    /**
     * Gets the formula type.
     *
     * @return the formulaType
     */
    public String getFormulaType() {
        return formulaType;
    }

    /**
     * Sets the formula type.
     *
     * @param formulaType the formulaType to set
     */
    public void setFormulaType(final String formulaType) {
        this.formulaType = formulaType;
    }

    /**
     * Gets the self growth to.
     *
     * @return the selfGrowthTo
     */
    public Date getSelfGrowthTo() {
        return selfGrowthTo;
    }

    /**
     * Sets the self growth to.
     *
     * @param selfGrowthTo the selfGrowthTo to set
     */
    public void setSelfGrowthTo(final Date selfGrowthTo) {
        this.selfGrowthTo = selfGrowthTo;
    }

    /**
     * Gets the rebate plan no.
     *
     * @return the rebatePlanNo
     */
    public String getRebatePlanNo() {
        return rebatePlanNo;
    }

    /**
     * Sets the rebate plan no.
     *
     * @param rebatePlanNo the rebatePlanNo to set
     */
    public void setRebatePlanNo(final String rebatePlanNo) {
        this.rebatePlanNo = rebatePlanNo;
    }

    /**
     * Gets the tier formula no.
     *
     * @return the tierFormulaNo
     */
    public String getTierFormulaNo() {
        return tierFormulaNo;
    }

    /**
     * Sets the tier formula no.
     *
     * @param tierFormulaNo the tierFormulaNo to set
     */
    public void setTierFormulaNo(final String tierFormulaNo) {
        this.tierFormulaNo = tierFormulaNo;
    }

    /**
     * Gets the tier formula name.
     *
     * @return the tierFormulaName
     */
    public String getTierFormulaName() {
        return tierFormulaName;
    }

    /**
     * Sets the tier formula name.
     *
     * @param tierFormulaName the tierFormulaName to set
     */
    public void setTierFormulaName(final String tierFormulaName) {
        this.tierFormulaName = tierFormulaName;
    }

    /**
     * Gets the tier formula system id.
     *
     * @return the tierFormulaSystemId
     */
    public String getTierFormulaSystemId() {
        return tierFormulaSystemId;
    }

    /**
     * Sets the tier formula system id.
     *
     * @param tierFormulaSystemId the tierFormulaSystemId to set
     */
    public void setTierFormulaSystemId(final String tierFormulaSystemId) {
        this.tierFormulaSystemId = tierFormulaSystemId;
    }

    public String getRebateStructureValue() {
        return rebateStructureValue;
    }

    public void setRebateStructureValue(String rebateStructureValue) {
        this.rebateStructureValue = rebateStructureValue;
    }

    public String getRebateRangeBasedOnValue() {
        return rebateRangeBasedOnValue;
    }

    public void setRebateRangeBasedOnValue(String rebateRangeBasedOnValue) {
        this.rebateRangeBasedOnValue = rebateRangeBasedOnValue;
    }

    public String getRebateBasedOnValue() {
        return rebateBasedOnValue;
    }

    public void setRebateBasedOnValue(String rebateBasedOnValue) {
        this.rebateBasedOnValue = rebateBasedOnValue;
    }

    public String getRebatePlanTypeValue() {
        return rebatePlanTypeValue;
    }

    public void setRebatePlanTypeValue(String rebatePlanTypeValue) {
        this.rebatePlanTypeValue = rebatePlanTypeValue;
    }

    public String getRebatePlanStatusValue() {
        return rebatePlanStatusValue;
    }

    public void setRebatePlanStatusValue(String rebatePlanStatusValue) {
        this.rebatePlanStatusValue = rebatePlanStatusValue;
    }

    public String getRebatePlanTierId() {
        return rebatePlanTierId;
    }

    public void setRebatePlanTierId(String rebatePlanTierId) {
        this.rebatePlanTierId = rebatePlanTierId;
    }

    public String getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(String freeAmount) {
        this.freeAmount = freeAmount;
    }

    public String getTierTolerance() {
        return tierTolerance;
    }

    public void setTierTolerance(String tierTolerance) {
        this.tierTolerance = tierTolerance;
    }

    public String getTierFrom() {
        return tierFrom;
    }

    public void setTierFrom(String tierFrom) {
        this.tierFrom = tierFrom;
    }

    public String getTierTo() {
        return tierTo;
    }

    public void setTierTo(String tierTo) {
        this.tierTo = tierTo;
    }

    public String getTierValue() {
        return tierValue;
    }

    public void setTierValue(String tierValue) {
        this.tierValue = tierValue;
    }

    public HelperDTO getTierOperator() {
        return tierOperator;
    }

    public void setTierOperator(HelperDTO tierOperator) {
        this.tierOperator = tierOperator;
    }

    public Integer getTierLevel() {
        return tierLevel;
    }

    public void setTierLevel(Integer tierLevel) {
        this.tierLevel = tierLevel;
    }

    public String getNetSalesRule() {
        return netSalesRule;
    }

    public void setNetSalesRule(String netSalesRule) {
        this.netSalesRule = netSalesRule;
    }

    public String getTierFormula() {
        return tierFormula;
    }

    public void setTierFormula(String tierFormula) {
        this.tierFormula = tierFormula;
    }

    public int getNetSalesRuleSid() {
        return netSalesRuleSid;
    }

    public void setNetSalesRuleSid(int netSalesRuleSid) {
        this.netSalesRuleSid = netSalesRuleSid;
    }  

    public Map<Integer, String> getItemPricingQualifierMap() {
        return itemPricingQualifierMap;
    }
    
    public void setItemPricingQualifierMap(Map<Integer, String> itemPricingQualifierMap) {
        this.itemPricingQualifierMap = itemPricingQualifierMap;
    }
    
}
