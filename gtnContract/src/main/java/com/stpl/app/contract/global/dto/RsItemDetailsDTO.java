package com.stpl.app.contract.global.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.model.RsContract;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.ifs.util.HelperDTO;


public class RsItemDetailsDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -3559879142770440174L;
    /**
     * The item familyplan system id.
     */
    private String itemFamilyplanSystemId;
    /**
     * The company familyplan system id.
     */
    private String companyFamilyplanSystemId;
    /**
     * The price schedule system id.
     */
    private String priceScheduleSystemId;
    /**
     * The rebate schedule system id.
     */
    private String rebateScheduleSystemId;
    /**
     * The rs details system id.
     */
    private String rsDetailsSystemId;
    
    private String bundleNo = StringUtils.EMPTY;
     
    /**
     * The contract system id.
     */
    private String contractSystemId;
    /**
     * The item no.
     */
    private String itemNo;
    /**
     * The item id.
     */
    private String itemId;
    /**
     * The item name.
     */
    private String itemName;
    /**
     * The item type.
     */
    private String itemType = StringUtils.EMPTY;
    /**
     * The rebate plan name.
     */
    private String rebatePlanName = StringUtils.EMPTY;
    /**
     * The rebate amount.
     */
    private String rebateAmount = StringUtils.EMPTY;
    /**
     * The revision date.
     */
    private String revisionStartDate = StringUtils.EMPTY;
    
	/** The formula id. */
	private String formulaId = StringUtils.EMPTY;
    /**
     * The formula name.
     */
    private String formulaName = StringUtils.EMPTY;
    /**
     * The item system id.
     */
    private String itemSystemId;
    /**
     * The start date.
     */
    private Date startDate;
    /**
     * The end date.
     */
    private Date endDate;
    /**
     * The rebate schedule type.
     */
    private HelperDTO rebateScheduleType;
    /**
     * The rebate program type.
     */
    private HelperDTO rebateProgramType;
    /**
     * The checkbox.
     */
    private Boolean checkbox = false;
    /**
     * The rs list.
     */
    private List<RsItemDetailsDTO> rsList = new ArrayList<RsItemDetailsDTO>();
    /**
     * The rs master attached.
     */
    private RsContract rsMasterAttached = RsContractLocalServiceUtil.createRsContract(0);
    /**
     * The udc1.
     */
    private HelperDTO udc1;
    /**
     * The udc2.
     */
    private HelperDTO udc2;
    /**
     * The udc3.
     */
    private HelperDTO udc3;
    /**
     * The udc4.
     */
    private HelperDTO udc4;
    /**
     * The udc5.
     */
    private HelperDTO udc5;
    /**
     * The udc6.
     */
    private HelperDTO udc6;
    
    private String tempItemPriceRebateSystemId;

    
    private String rebateScheduleId = StringUtils.EMPTY;
    
    private String rebateScheduleNo = StringUtils.EMPTY;
    
    private String rebateScheduleName = StringUtils.EMPTY;
     private HelperDTO rebateScheduleStatus;

    private HelperDTO rebateScheduleCategory;
    
    private HelperDTO tradeClass;
    
    private String rebateScheduleTransRefNo = StringUtils.EMPTY;
    
    private String rebateScheduleTransRefName = StringUtils.EMPTY;
    
    private String rebateScheduleAlias = StringUtils.EMPTY;
    
    private HelperDTO rebateScheduleDesignation = new HelperDTO(0, "-Select One-");
    
    private String parentRebateScheduleId = StringUtils.EMPTY;
    
    private String parentRebateScheduleName = StringUtils.EMPTY;
    private HelperDTO interestBearingBasis = new HelperDTO(0, Constants.SELECT_ONE);	
    
    private HelperDTO interestBearingIndicatorddlb = new HelperDTO(0, Constants.SELECT_ONE);	
    
    private HelperDTO interestBearingIndicator = new HelperDTO(0, Constants.SELECT_ONE);	
    
    private HelperDTO deductionInclusion = new HelperDTO(0, Constants.SELECT_ONE);
    
    private HelperDTO rebateProcessingType = new HelperDTO(0, Constants.SELECT_ONE);	
    
    private HelperDTO validationProfile = new HelperDTO(0, Constants.SELECT_ONE);
    
    private HelperDTO rebateRuleType = new HelperDTO(0, Constants.SELECT_ONE);
    
    private HelperDTO rebatePlanLevel = new HelperDTO(0, Constants.SELECT_ONE);
    
    private HelperDTO paymentLevel = new HelperDTO(0, Constants.SELECT_ONE);
    
    private String rebateRuleAssociation = StringUtils.EMPTY;
    
    private Date itemRebateStartDate;
    
    private Date itemRebateEndDate;
    
    private Date rebateStartDate;
    
    private Date rebateEndDate;
    
    private HelperDTO paymentMethod;
    
     private HelperDTO paymentFrequency;
     
      private HelperDTO paymentTerms;
      
      private HelperDTO rebateFrequency;
            
       private HelperDTO calendar = new HelperDTO(0, Constants.SELECT_ONE);
       
        private String paymentGracePeriod = StringUtils.EMPTY;

    private Date rebateRevisionDate;
    
    private HelperDTO calculationType = new HelperDTO(0, Constants.SELECT_ONE);
    
    private HelperDTO calculationLevel = new HelperDTO(0, Constants.SELECT_ONE);
    
    private HelperDTO evaluationRuleType = new HelperDTO(0, Constants.SELECT_ONE);
    
    private HelperDTO calculationRuleLevel = new HelperDTO(0, Constants.SELECT_ONE);
    
    private HelperDTO evaluationRuleLevel = new HelperDTO(0, Constants.SELECT_ONE);
    
    private String evaluationRuleAssociation = StringUtils.EMPTY;
    
    private String calculationRule = StringUtils.EMPTY;
    private String calculationSystemId = StringUtils.EMPTY;
    private String evaluationSystemId = StringUtils.EMPTY;
    
    private String ruleNo = StringUtils.EMPTY;
    private String ruleName = StringUtils.EMPTY;
    private String ruleVersion = StringUtils.EMPTY;
    
    private Date modifiedDate;

    public HelperDTO getInterestBearingIndicatorddlb() {
        return interestBearingIndicatorddlb;
    }

    public void setInterestBearingIndicatorddlb(HelperDTO interestBearingIndicatorddlb) {
        this.interestBearingIndicatorddlb = interestBearingIndicatorddlb;
    }

    public Date getRebateRevisionDate() {
        return rebateRevisionDate;
    }

    public void setRebateRevisionDate(Date rebateRevisionDate) {
        this.rebateRevisionDate = rebateRevisionDate;
    }

    public Date getRebateStartDate() {
        return rebateStartDate;
    }

    public void setRebateStartDate(Date rebateStartDate) {
        this.rebateStartDate = rebateStartDate;
    }

    public Date getRebateEndDate() {
        return rebateEndDate;
    }

    public void setRebateEndDate(Date rebateEndDate) {
        this.rebateEndDate = rebateEndDate;
    }

    public HelperDTO getInterestBearingIndicator() {
        return interestBearingIndicator;
    }

    public void setInterestBearingIndicator(HelperDTO interestBearingIndicator) {
        this.interestBearingIndicator = interestBearingIndicator;
    }

    public HelperDTO getDeductionInclusion() {
        return deductionInclusion;
    }

    public void setDeductionInclusion(HelperDTO deductionInclusion) {
        this.deductionInclusion = deductionInclusion;
    }

    public HelperDTO getRebateProcessingType() {
        return rebateProcessingType;
    }

    public void setRebateProcessingType(HelperDTO rebateProcessingType) {
        this.rebateProcessingType = rebateProcessingType;
    }

    public HelperDTO getPaymentLevel() {
        return paymentLevel;
    }

    public void setPaymentLevel(HelperDTO paymentLevel) {
        this.paymentLevel = paymentLevel;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleVersion() {
        return ruleVersion;
    }

    public void setRuleVersion(String ruleVersion) {
        this.ruleVersion = ruleVersion;
    }
    
    public String getTempItemPriceRebateSystemId() {
        return tempItemPriceRebateSystemId;
    }

    public void setTempItemPriceRebateSystemId(String tempItemPriceRebateSystemId) {
        this.tempItemPriceRebateSystemId = tempItemPriceRebateSystemId;
    }

    /**
     * The Constructor.
     */
    public RsItemDetailsDTO() {
        // default constructor
    }

    /**
     * The Constructor.
     *
     * @param itemFamilyplanSystemId the item familyplan system id
     * @param companyFamilyplanSystemId the company familyplan system id
     * @param priceScheduleSystemId the price schedule system id
     * @param rebateScheduleSystemId the rebate schedule system id
     * @param contractSystemId the contract system id
     * @param itemId the item id
     * @param itemNo the item no
     * @param itemName the item name
     * @param itemSystemId the item system id
     */
    public RsItemDetailsDTO(final String itemFamilyplanSystemId, final String companyFamilyplanSystemId, final String priceScheduleSystemId, final String rebateScheduleSystemId,
            final String contractSystemId, final String itemId, final String itemNo, final String itemName, final String itemSystemId) {
        super();
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
        this.companyFamilyplanSystemId = companyFamilyplanSystemId;
        this.priceScheduleSystemId = priceScheduleSystemId;
        this.rebateScheduleSystemId = rebateScheduleSystemId;
        this.contractSystemId = contractSystemId;
        this.itemId = itemId;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.itemSystemId = itemSystemId;
    }

    /**
     * Gets the rebate schedule type.
     *
     * @return the rebate schedule type
     */
    public HelperDTO getRebateScheduleType() {
        return rebateScheduleType;
    }

    /**
     * Sets the rebate schedule type.
     *
     * @param rebateScheduleType the rebate schedule type
     */
    public void setRebateScheduleType(final HelperDTO rebateScheduleType) {
        this.rebateScheduleType = rebateScheduleType;
    }

    /**
     * Gets the rebate program type.
     *
     * @return the rebate program type
     */
    public HelperDTO getRebateProgramType() {
        return rebateProgramType;
    }

    /**
     * Sets the rebate program type.
     *
     * @param rebateProgramType the rebate program type
     */
    public void setRebateProgramType(final HelperDTO rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }

    /**
     * Gets the item id.
     *
     * @return the item id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Sets the item id.
     *
     * @param itemId the item id
     */
    public void setItemId(final String itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets the formula id.
     *
     * @return the formula id
     */
    public String getFormulaId() {
        return formulaId;
    }

    /**
     * Sets the formula id.
     *
     * @param formulaId the formula id
     */
    public void setFormulaId(final String formulaId) {
        this.formulaId = formulaId;
    }

    /**
     * Gets the formula name.
     *
     * @return the formula name
     */
    public String getFormulaName() {
        return formulaName;
    }

    /**
     * Sets the formula name.
     *
     * @param formulaName the formula name
     */
    public void setFormulaName(final String formulaName) {
        this.formulaName = formulaName;
    }

    /**
     * Gets the item familyplan system id.
     *
     * @return the item familyplan system id
     */
    public String getItemFamilyplanSystemId() {
        return itemFamilyplanSystemId;
    }

    /**
     * Sets the item familyplan system id.
     *
     * @param itemFamilyplanSystemId the item familyplan system id
     */
    public void setItemFamilyplanSystemId(final String itemFamilyplanSystemId) {
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
    }

    /**
     * Gets the company familyplan system id.
     *
     * @return the company familyplan system id
     */
    public String getCompanyFamilyplanSystemId() {
        return companyFamilyplanSystemId;
    }

    /**
     * Sets the company familyplan system id.
     *
     * @param companyFamilyplanSystemId the company familyplan system id
     */
    public void setCompanyFamilyplanSystemId(final String companyFamilyplanSystemId) {
        this.companyFamilyplanSystemId = companyFamilyplanSystemId;
    }

    /**
     * Gets the price schedule system id.
     *
     * @return the price schedule system id
     */
    public String getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    /**
     * Sets the price schedule system id.
     *
     * @param priceScheduleSystemId the price schedule system id
     */
    public void setPriceScheduleSystemId(final String priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    /**
     * Gets the rebate schedule system id.
     *
     * @return the rebate schedule system id
     */
    public String getRebateScheduleSystemId() {
        return rebateScheduleSystemId;
    }

    /**
     * Sets the rebate schedule system id.
     *
     * @param rebateScheduleSystemId the rebate schedule system id
     */
    public void setRebateScheduleSystemId(final String rebateScheduleSystemId) {
        this.rebateScheduleSystemId = rebateScheduleSystemId;
    }

    /**
     * Gets the contract system id.
     *
     * @return the contract system id
     */
    public String getContractSystemId() {
        return contractSystemId;
    }

    /**
     * Sets the contract system id.
     *
     * @param contractSystemId the contract system id
     */
    public void setContractSystemId(final String contractSystemId) {
        this.contractSystemId = contractSystemId;
    }

    /**
     * Gets the item no.
     *
     * @return the item no
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * Sets the item no.
     *
     * @param itemNo the item no
     */
    public void setItemNo(final String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * Gets the item name.
     *
     * @return the item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the item name.
     *
     * @param itemName the item name
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the item type.
     *
     * @return the item type
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Sets the item type.
     *
     * @param itemType the item type
     */
    public void setItemType(final String itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets the rebate plan name.
     *
     * @return the rebate plan name
     */
    public String getRebatePlanName() {
        return rebatePlanName;
    }

    /**
     * Sets the rebate plan name.
     *
     * @param rebatePlanName the rebate plan name
     */
    public void setRebatePlanName(final String rebatePlanName) {
        this.rebatePlanName = rebatePlanName;
    }

    /**
     * Gets the rebate amount.
     *
     * @return the rebate amount
     */
    public String getRebateAmount() {
        return rebateAmount;
    }

    /**
     * Sets the rebate amount.
     *
     * @param rebateAmount the rebate amount
     */
    public void setRebateAmount(final String rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    /**
     * Gets the revision date.
     *
     * @return the revision date
     */
    public String getRevisionStartDate() {
        return revisionStartDate;
    }

    /**
     * Sets the revision date.
     *
     * @param revisionDate the revision date
     */
    public void setRevisionStartDate(final String revisionStartDate) {
        this.revisionStartDate = revisionStartDate;
    }

    /**
     * Gets the item system id.
     *
     * @return the item system id
     */
    public String getItemSystemId() {
        return itemSystemId;
    }

    /**
     * Sets the item system id.
     *
     * @param itemSystemId the item system id
     */
    public void setItemSystemId(final String itemSystemId) {
        this.itemSystemId = itemSystemId;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the checkbox.
     *
     * @return the checkbox
     */
    public Boolean getCheckbox() {
        return checkbox;
    }

    /**
     * Sets the checkbox.
     *
     * @param checkbox the checkbox
     */
    public void setCheckbox(final Boolean checkbox) {
        this.checkbox = checkbox;
    }

    /**
     * Gets the rs list.
     *
     * @return the rs list
     */
    public List<RsItemDetailsDTO> getRsList() {
        return rsList;
    }

    /**
     * Sets the rs list.
     *
     * @param rsList the rs list
     */
    public void setRsList(final List<RsItemDetailsDTO> rsList) {
        this.rsList = rsList;
    }

    /**
     * Gets the rs master attached.
     *
     * @return the rs master attached
     */
    public RsContract getRsMasterAttached() {
        return rsMasterAttached;
    }

    /**
     * Sets the rebate schedule master.
     *
     * @param rsMasterAttached the rebate schedule master
     */
    public void setRebateScheduleMaster(final RsContract rsMasterAttached) {
        this.rsMasterAttached = rsMasterAttached;
    }

    /**
     * Gets the rs details system id.
     *
     * @return the rs details system id
     */
    public String getRsDetailsSystemId() {
        return rsDetailsSystemId;
    }

    /**
     * Sets the rs details system id.
     *
     * @param rsDetailsSystemId the rs details system id
     */
    public void setRsDetailsSystemId(final String rsDetailsSystemId) {
        this.rsDetailsSystemId = rsDetailsSystemId;
    }

    /**
     * Gets the udc1.
     *
     * @return the udc1
     */
    public HelperDTO getUdc1() {
        return udc1;
    }

    /**
     * Sets the udc1.
     *
     * @param udc1 the udc1
     */
    public void setUdc1(final HelperDTO udc1) {
        this.udc1 = udc1;
    }

    /**
     * Gets the udc2.
     *
     * @return the udc2
     */
    public HelperDTO getUdc2() {
        return udc2;
    }

    /**
     * Sets the udc2.
     *
     * @param udc2 the udc2
     */
    public void setUdc2(final HelperDTO udc2) {
        this.udc2 = udc2;
    }

    /**
     * Gets the udc3.
     *
     * @return the udc3
     */
    public HelperDTO getUdc3() {
        return udc3;
    }

    /**
     * Sets the udc3.
     *
     * @param udc3 the udc3
     */
    public void setUdc3(final HelperDTO udc3) {
        this.udc3 = udc3;
    }

    /**
     * Gets the udc4.
     *
     * @return the udc4
     */
    public HelperDTO getUdc4() {
        return udc4;
    }

    /**
     * Sets the udc4.
     *
     * @param udc4 the udc4
     */
    public void setUdc4(final HelperDTO udc4) {
        this.udc4 = udc4;
    }

    /**
     * Gets the udc5.
     *
     * @return the udc5
     */
    public HelperDTO getUdc5() {
        return udc5;
    }

    /**
     * Sets the udc5.
     *
     * @param udc5 the udc5
     */
    public void setUdc5(final HelperDTO udc5) {
        this.udc5 = udc5;
    }

    /**
     * Gets the udc6.
     *
     * @return the udc6
     */
    public HelperDTO getUdc6() {
        return udc6;
    }

    /**
     * Sets the udc6.
     *
     * @param udc6 the udc6
     */
    public void setUdc6(final HelperDTO udc6) {
        this.udc6 = udc6;
    }

    /**
     * Checks if is checkbox.
     *
     * @return true, if checks if is checkbox
     */
    public Boolean isCheckbox() {
        return checkbox;
    }

    /**
     * Sets the rs master attached.
     *
     * @param rsMasterAttached the rs master attached
     */
    public void setRsMasterAttached(final RsContract rsMasterAttached) {
        this.rsMasterAttached = rsMasterAttached;
    }

    public String getRebateScheduleId() {
        return rebateScheduleId;
    }

    public void setRebateScheduleId(String rebateScheduleId) {
        this.rebateScheduleId = rebateScheduleId;
    }

    public String getRebateScheduleNo() {
        return rebateScheduleNo;
    }

    public void setRebateScheduleNo(String rebateScheduleNo) {
        this.rebateScheduleNo = rebateScheduleNo;
    }

    public String getRebateScheduleName() {
        return rebateScheduleName;
    }

    public void setRebateScheduleName(String rebateScheduleName) {
        this.rebateScheduleName = rebateScheduleName;
    }

    public String getRebateScheduleTransRefNo() {
        return rebateScheduleTransRefNo;
    }

    public void setRebateScheduleTransRefNo(String rebateScheduleTransRefNo) {
        this.rebateScheduleTransRefNo = rebateScheduleTransRefNo;
    }

    public String getRebateScheduleTransRefName() {
        return rebateScheduleTransRefName;
    }

    public void setRebateScheduleTransRefName(String rebateScheduleTransRefName) {
        this.rebateScheduleTransRefName = rebateScheduleTransRefName;
    }

    public String getRebateScheduleAlias() {
        return rebateScheduleAlias;
    }

    public void setRebateScheduleAlias(String rebateScheduleAlias) {
        this.rebateScheduleAlias = rebateScheduleAlias;
    }

    public String getParentRebateScheduleId() {
        return parentRebateScheduleId;
    }

    public void setParentRebateScheduleId(String parentRebateScheduleId) {
        this.parentRebateScheduleId = parentRebateScheduleId;
    }

    public String getParentRebateScheduleName() {
        return parentRebateScheduleName;
    }

    public void setParentRebateScheduleName(String parentRebateScheduleName) {
        this.parentRebateScheduleName = parentRebateScheduleName;
    }

    public HelperDTO getRebateRuleType() {
        return rebateRuleType;
    }

    public void setRebateRuleType(HelperDTO rebateRuleType) {
        this.rebateRuleType = rebateRuleType;
    }

    public String getRebateRuleAssociation() {
        return rebateRuleAssociation;
    }

    public void setRebateRuleAssociation(String rebateRuleAssociation) {
        this.rebateRuleAssociation = rebateRuleAssociation;
    }

    public Date getItemRebateStartDate() {
        return itemRebateStartDate;
    }

    public void setItemRebateStartDate(Date itemRebateStartDate) {
        this.itemRebateStartDate = itemRebateStartDate;
    }

    public Date getItemRebateEndDate() {
        return itemRebateEndDate;
    }

    public void setItemRebateEndDate(Date itemRebateEndDate) {
        this.itemRebateEndDate = itemRebateEndDate;
    }

    public String getBundleNo() {
        return bundleNo;
    }

    public void setBundleNo(String bundleNo) {
        this.bundleNo = bundleNo;
    }

    public HelperDTO getRebateScheduleStatus() {
        return rebateScheduleStatus;
    }

    public void setRebateScheduleStatus(HelperDTO rebateScheduleStatus) {
        this.rebateScheduleStatus = rebateScheduleStatus;
    }

    public HelperDTO getRebateScheduleCategory() {
        return rebateScheduleCategory;
    }

    public void setRebateScheduleCategory(HelperDTO rebateScheduleCategory) {
        this.rebateScheduleCategory = rebateScheduleCategory;
    }

    public HelperDTO getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(HelperDTO tradeClass) {
        this.tradeClass = tradeClass;
    }

    public HelperDTO getRebateScheduleDesignation() {
        return rebateScheduleDesignation;
    }

    public void setRebateScheduleDesignation(HelperDTO rebateScheduleDesignation) {
        this.rebateScheduleDesignation = rebateScheduleDesignation;
    }

    public HelperDTO getValidationProfile() {
        return validationProfile;
    }

    public void setValidationProfile(HelperDTO validationProfile) {
        this.validationProfile = validationProfile;
    }

    public HelperDTO getRebatePlanLevel() {
        return rebatePlanLevel;
    }

    public void setRebatePlanLevel(HelperDTO rebatePlanLevel) {
        this.rebatePlanLevel = rebatePlanLevel;
    }

    public HelperDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(HelperDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public HelperDTO getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(HelperDTO paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public HelperDTO getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(HelperDTO paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public HelperDTO getRebateFrequency() {
        return rebateFrequency;
    }

    public void setRebateFrequency(HelperDTO rebateFrequency) {
        this.rebateFrequency = rebateFrequency;
    }

    public HelperDTO getCalendar() {
        return calendar;
    }

    public void setCalendar(HelperDTO calendar) {
        this.calendar = calendar;
    }

    public String getPaymentGracePeriod() {
        return paymentGracePeriod;
    }

    public void setPaymentGracePeriod(String paymentGracePeriod) {
        this.paymentGracePeriod = paymentGracePeriod;
    }

    public HelperDTO getInterestBearingBasis() {
        return interestBearingBasis;
    }

    public void setInterestBearingBasis(HelperDTO interestBearingBasis) {
        this.interestBearingBasis = interestBearingBasis;
    }

    public HelperDTO getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(HelperDTO calculationType) {
        this.calculationType = calculationType;
    }

    public HelperDTO getCalculationLevel() {
        return calculationLevel;
    }

    public void setCalculationLevel(HelperDTO calculationLevel) {
        this.calculationLevel = calculationLevel;
    }

    public HelperDTO getEvaluationRuleType() {
        return evaluationRuleType;
    }

    public void setEvaluationRuleType(HelperDTO evaluationRuleType) {
        this.evaluationRuleType = evaluationRuleType;
    }

    public HelperDTO getCalculationRuleLevel() {
        return calculationRuleLevel;
    }

    public void setCalculationRuleLevel(HelperDTO calculationRuleLevel) {
        this.calculationRuleLevel = calculationRuleLevel;
    }

    public HelperDTO getEvaluationRuleLevel() {
        return evaluationRuleLevel;
    }

    public void setEvaluationRuleLevel(HelperDTO evaluationRuleLevel) {
        this.evaluationRuleLevel = evaluationRuleLevel;
    }

    public String getEvaluationRuleAssociation() {
        return evaluationRuleAssociation;
    }

    public void setEvaluationRuleAssociation(String evaluationRuleAssociation) {
        this.evaluationRuleAssociation = evaluationRuleAssociation;
    }

    public String getCalculationRule() {
        return calculationRule;
    }

    public void setCalculationRule(String calculationRule) {
        this.calculationRule = calculationRule;
    }

    public String getCalculationSystemId() {
        return calculationSystemId;
    }

    public void setCalculationSystemId(String calculationSystemId) {
        this.calculationSystemId = calculationSystemId;
    }

    public String getEvaluationSystemId() {
        return evaluationSystemId;
    }

    public void setEvaluationSystemId(String evaluationSystemId) {
        this.evaluationSystemId = evaluationSystemId;
    }
    
}
