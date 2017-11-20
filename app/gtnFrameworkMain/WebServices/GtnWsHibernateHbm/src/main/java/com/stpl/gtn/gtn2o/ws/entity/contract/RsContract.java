package com.stpl.gtn.gtn2o.ws.entity.contract;
// Generated Mar 30, 2017 9:06:03 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.rebateschedule.RsModel;

/**
 * RsContract generated by hbm2java
 */
public class RsContract implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6876262742223807311L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rsContractSid;
	private CfpContract cfpContract;
	private ContractMaster contractMaster;
	private HelperTable helperTableByState;
	private HelperTable helperTableByRsValidationProfile;
	private HelperTable helperTableByRebateProgramType;
	private HelperTable helperTableByRebateProcessingType;
	private HelperTable helperTableByPaymentTerms;
	private HelperTable helperTableByRebatePlanLevel;
	private HelperTable helperTableByPaymentFrequency;
	private HelperTable helperTableByRsCalendar;
	private HelperTable helperTableByRebateFrequency;
	private HelperTable helperTableByRsContractAttachedStatus;
	private HelperTable helperTableByRsDesignation;
	private HelperTable helperTableByRsCategory;
	private HelperTable helperTableByInterestBearingIndicator;
	private HelperTable helperTableByRebateRuleType;
	private HelperTable helperTableByRsStatus;
	private HelperTable helperTableByPaymentMethod;
	private HelperTable helperTableByRsTradeClass;
	private HelperTable helperTableByInterestBearingBasis;
	private HelperTable helperTableByRsType;
	private IfpContract ifpContract;
	private PsContract psContract;
	private RsModel rsModel;
	private String rsId;
	private String rsNo;
	private String rsName;
	private Date rsStartDate;
	private Date rsEndDate;
	private String parentRsId;
	private String parentRsName;
	private Date rsContractAttachedDate;
	private String rsTransRefId;
	private String rsTransRefNo;
	private String rsTransRefName;
	private String rebateRuleAssociation;
	private String paymentGracePeriod;
	private String makePayableTo;
	private String address1;
	private String address2;
	private String city;
	private String zipCode;
	private String rsAlias;
	private String formulaMethodId;
	private char inboundStatus;
	private boolean recordLockStatus;
	private String batchId;
	private String source;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;
	private Integer deductionInclusion;
	private Integer calculationType;
	private Integer calculationLevel;
	private Integer calculationRule;
	private Integer calculationRuleLevel;
	private Integer evaluationRuleType;
	private Integer evaluationRuleLevel;
	private Integer evaluationRuleOrAssociation;
	private Set rsContractDetailses = new HashSet(0);

	public RsContract() {
	}

	public RsContract(int rsContractSid, ContractMaster contractMaster, HelperTable helperTableByRebateProgramType,
			HelperTable helperTableByRebateFrequency, HelperTable helperTableByRsType, RsModel rsModel, String rsId,
			String rsNo, Date rsStartDate, char inboundStatus, boolean recordLockStatus, int createdBy,
			Date createdDate, int modifiedBy, Date modifiedDate) {
		this.rsContractSid = rsContractSid;
		this.contractMaster = contractMaster;
		this.helperTableByRebateProgramType = helperTableByRebateProgramType;
		this.helperTableByRebateFrequency = helperTableByRebateFrequency;
		this.helperTableByRsType = helperTableByRsType;
		this.rsModel = rsModel;
		this.rsId = rsId;
		this.rsNo = rsNo;
		this.rsStartDate = rsStartDate;
		this.inboundStatus = inboundStatus;
		this.recordLockStatus = recordLockStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public RsContract(int rsContractSid, CfpContract cfpContract, ContractMaster contractMaster,
			HelperTable helperTableByState, HelperTable helperTableByRsValidationProfile,
			HelperTable helperTableByRebateProgramType, HelperTable helperTableByRebateProcessingType,
			HelperTable helperTableByPaymentTerms, HelperTable helperTableByRebatePlanLevel,
			HelperTable helperTableByPaymentFrequency, HelperTable helperTableByRsCalendar,
			HelperTable helperTableByRebateFrequency, HelperTable helperTableByRsContractAttachedStatus,
			HelperTable helperTableByRsDesignation, HelperTable helperTableByRsCategory,
			HelperTable helperTableByInterestBearingIndicator, HelperTable helperTableByRebateRuleType,
			HelperTable helperTableByRsStatus, HelperTable helperTableByPaymentMethod,
			HelperTable helperTableByRsTradeClass, HelperTable helperTableByInterestBearingBasis,
			HelperTable helperTableByRsType, IfpContract ifpContract, PsContract psContract, RsModel rsModel,
			String rsId, String rsNo, String rsName, Date rsStartDate, Date rsEndDate, String parentRsId,
			String parentRsName, Date rsContractAttachedDate, String rsTransRefId, String rsTransRefNo,
			String rsTransRefName, String rebateRuleAssociation, String paymentGracePeriod, String makePayableTo,
			String address1, String address2, String city, String zipCode, String rsAlias, String formulaMethodId,
			char inboundStatus, boolean recordLockStatus, String batchId, String source, int createdBy,
			Date createdDate, int modifiedBy, Date modifiedDate, Integer deductionInclusion, Integer calculationType,
			Integer calculationLevel, Integer calculationRule, Integer calculationRuleLevel, Integer evaluationRuleType,
			Integer evaluationRuleLevel, Integer evaluationRuleOrAssociation, Set rsContractDetailses) {
		this.rsContractSid = rsContractSid;
		this.cfpContract = cfpContract;
		this.contractMaster = contractMaster;
		this.helperTableByState = helperTableByState;
		this.helperTableByRsValidationProfile = helperTableByRsValidationProfile;
		this.helperTableByRebateProgramType = helperTableByRebateProgramType;
		this.helperTableByRebateProcessingType = helperTableByRebateProcessingType;
		this.helperTableByPaymentTerms = helperTableByPaymentTerms;
		this.helperTableByRebatePlanLevel = helperTableByRebatePlanLevel;
		this.helperTableByPaymentFrequency = helperTableByPaymentFrequency;
		this.helperTableByRsCalendar = helperTableByRsCalendar;
		this.helperTableByRebateFrequency = helperTableByRebateFrequency;
		this.helperTableByRsContractAttachedStatus = helperTableByRsContractAttachedStatus;
		this.helperTableByRsDesignation = helperTableByRsDesignation;
		this.helperTableByRsCategory = helperTableByRsCategory;
		this.helperTableByInterestBearingIndicator = helperTableByInterestBearingIndicator;
		this.helperTableByRebateRuleType = helperTableByRebateRuleType;
		this.helperTableByRsStatus = helperTableByRsStatus;
		this.helperTableByPaymentMethod = helperTableByPaymentMethod;
		this.helperTableByRsTradeClass = helperTableByRsTradeClass;
		this.helperTableByInterestBearingBasis = helperTableByInterestBearingBasis;
		this.helperTableByRsType = helperTableByRsType;
		this.ifpContract = ifpContract;
		this.psContract = psContract;
		this.rsModel = rsModel;
		this.rsId = rsId;
		this.rsNo = rsNo;
		this.rsName = rsName;
		this.rsStartDate = rsStartDate;
		this.rsEndDate = rsEndDate;
		this.parentRsId = parentRsId;
		this.parentRsName = parentRsName;
		this.rsContractAttachedDate = rsContractAttachedDate;
		this.rsTransRefId = rsTransRefId;
		this.rsTransRefNo = rsTransRefNo;
		this.rsTransRefName = rsTransRefName;
		this.rebateRuleAssociation = rebateRuleAssociation;
		this.paymentGracePeriod = paymentGracePeriod;
		this.makePayableTo = makePayableTo;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.zipCode = zipCode;
		this.rsAlias = rsAlias;
		this.formulaMethodId = formulaMethodId;
		this.inboundStatus = inboundStatus;
		this.recordLockStatus = recordLockStatus;
		this.batchId = batchId;
		this.source = source;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.deductionInclusion = deductionInclusion;
		this.calculationType = calculationType;
		this.calculationLevel = calculationLevel;
		this.calculationRule = calculationRule;
		this.calculationRuleLevel = calculationRuleLevel;
		this.evaluationRuleType = evaluationRuleType;
		this.evaluationRuleLevel = evaluationRuleLevel;
		this.evaluationRuleOrAssociation = evaluationRuleOrAssociation;
		this.rsContractDetailses = rsContractDetailses;
	}

	public int getRsContractSid() {
		return this.rsContractSid;
	}

	public void setRsContractSid(int rsContractSid) {
		this.rsContractSid = rsContractSid;
	}

	public CfpContract getCfpContract() {
		return this.cfpContract;
	}

	public void setCfpContract(CfpContract cfpContract) {
		this.cfpContract = cfpContract;
	}

	public ContractMaster getContractMaster() {
		return this.contractMaster;
	}

	public void setContractMaster(ContractMaster contractMaster) {
		this.contractMaster = contractMaster;
	}

	public HelperTable getHelperTableByState() {
		return this.helperTableByState;
	}

	public void setHelperTableByState(HelperTable helperTableByState) {
		this.helperTableByState = helperTableByState;
	}

	public HelperTable getHelperTableByRsValidationProfile() {
		return this.helperTableByRsValidationProfile;
	}

	public void setHelperTableByRsValidationProfile(HelperTable helperTableByRsValidationProfile) {
		this.helperTableByRsValidationProfile = helperTableByRsValidationProfile;
	}

	public HelperTable getHelperTableByRebateProgramType() {
		return this.helperTableByRebateProgramType;
	}

	public void setHelperTableByRebateProgramType(HelperTable helperTableByRebateProgramType) {
		this.helperTableByRebateProgramType = helperTableByRebateProgramType;
	}

	public HelperTable getHelperTableByRebateProcessingType() {
		return this.helperTableByRebateProcessingType;
	}

	public void setHelperTableByRebateProcessingType(HelperTable helperTableByRebateProcessingType) {
		this.helperTableByRebateProcessingType = helperTableByRebateProcessingType;
	}

	public HelperTable getHelperTableByPaymentTerms() {
		return this.helperTableByPaymentTerms;
	}

	public void setHelperTableByPaymentTerms(HelperTable helperTableByPaymentTerms) {
		this.helperTableByPaymentTerms = helperTableByPaymentTerms;
	}

	public HelperTable getHelperTableByRebatePlanLevel() {
		return this.helperTableByRebatePlanLevel;
	}

	public void setHelperTableByRebatePlanLevel(HelperTable helperTableByRebatePlanLevel) {
		this.helperTableByRebatePlanLevel = helperTableByRebatePlanLevel;
	}

	public HelperTable getHelperTableByPaymentFrequency() {
		return this.helperTableByPaymentFrequency;
	}

	public void setHelperTableByPaymentFrequency(HelperTable helperTableByPaymentFrequency) {
		this.helperTableByPaymentFrequency = helperTableByPaymentFrequency;
	}

	public HelperTable getHelperTableByRsCalendar() {
		return this.helperTableByRsCalendar;
	}

	public void setHelperTableByRsCalendar(HelperTable helperTableByRsCalendar) {
		this.helperTableByRsCalendar = helperTableByRsCalendar;
	}

	public HelperTable getHelperTableByRebateFrequency() {
		return this.helperTableByRebateFrequency;
	}

	public void setHelperTableByRebateFrequency(HelperTable helperTableByRebateFrequency) {
		this.helperTableByRebateFrequency = helperTableByRebateFrequency;
	}

	public HelperTable getHelperTableByRsContractAttachedStatus() {
		return this.helperTableByRsContractAttachedStatus;
	}

	public void setHelperTableByRsContractAttachedStatus(HelperTable helperTableByRsContractAttachedStatus) {
		this.helperTableByRsContractAttachedStatus = helperTableByRsContractAttachedStatus;
	}

	public HelperTable getHelperTableByRsDesignation() {
		return this.helperTableByRsDesignation;
	}

	public void setHelperTableByRsDesignation(HelperTable helperTableByRsDesignation) {
		this.helperTableByRsDesignation = helperTableByRsDesignation;
	}

	public HelperTable getHelperTableByRsCategory() {
		return this.helperTableByRsCategory;
	}

	public void setHelperTableByRsCategory(HelperTable helperTableByRsCategory) {
		this.helperTableByRsCategory = helperTableByRsCategory;
	}

	public HelperTable getHelperTableByInterestBearingIndicator() {
		return this.helperTableByInterestBearingIndicator;
	}

	public void setHelperTableByInterestBearingIndicator(HelperTable helperTableByInterestBearingIndicator) {
		this.helperTableByInterestBearingIndicator = helperTableByInterestBearingIndicator;
	}

	public HelperTable getHelperTableByRebateRuleType() {
		return this.helperTableByRebateRuleType;
	}

	public void setHelperTableByRebateRuleType(HelperTable helperTableByRebateRuleType) {
		this.helperTableByRebateRuleType = helperTableByRebateRuleType;
	}

	public HelperTable getHelperTableByRsStatus() {
		return this.helperTableByRsStatus;
	}

	public void setHelperTableByRsStatus(HelperTable helperTableByRsStatus) {
		this.helperTableByRsStatus = helperTableByRsStatus;
	}

	public HelperTable getHelperTableByPaymentMethod() {
		return this.helperTableByPaymentMethod;
	}

	public void setHelperTableByPaymentMethod(HelperTable helperTableByPaymentMethod) {
		this.helperTableByPaymentMethod = helperTableByPaymentMethod;
	}

	public HelperTable getHelperTableByRsTradeClass() {
		return this.helperTableByRsTradeClass;
	}

	public void setHelperTableByRsTradeClass(HelperTable helperTableByRsTradeClass) {
		this.helperTableByRsTradeClass = helperTableByRsTradeClass;
	}

	public HelperTable getHelperTableByInterestBearingBasis() {
		return this.helperTableByInterestBearingBasis;
	}

	public void setHelperTableByInterestBearingBasis(HelperTable helperTableByInterestBearingBasis) {
		this.helperTableByInterestBearingBasis = helperTableByInterestBearingBasis;
	}

	public HelperTable getHelperTableByRsType() {
		return this.helperTableByRsType;
	}

	public void setHelperTableByRsType(HelperTable helperTableByRsType) {
		this.helperTableByRsType = helperTableByRsType;
	}

	public IfpContract getIfpContract() {
		return this.ifpContract;
	}

	public void setIfpContract(IfpContract ifpContract) {
		this.ifpContract = ifpContract;
	}

	public PsContract getPsContract() {
		return this.psContract;
	}

	public void setPsContract(PsContract psContract) {
		this.psContract = psContract;
	}

	public RsModel getRsModel() {
		return this.rsModel;
	}

	public void setRsModel(RsModel rsModel) {
		this.rsModel = rsModel;
	}

	public String getRsId() {
		return this.rsId;
	}

	public void setRsId(String rsId) {
		this.rsId = rsId;
	}

	public String getRsNo() {
		return this.rsNo;
	}

	public void setRsNo(String rsNo) {
		this.rsNo = rsNo;
	}

	public String getRsName() {
		return this.rsName;
	}

	public void setRsName(String rsName) {
		this.rsName = rsName;
	}

	public Date getRsStartDate() {
		return this.rsStartDate;
	}

	public void setRsStartDate(Date rsStartDate) {
		this.rsStartDate = rsStartDate;
	}

	public Date getRsEndDate() {
		return this.rsEndDate;
	}

	public void setRsEndDate(Date rsEndDate) {
		this.rsEndDate = rsEndDate;
	}

	public String getParentRsId() {
		return this.parentRsId;
	}

	public void setParentRsId(String parentRsId) {
		this.parentRsId = parentRsId;
	}

	public String getParentRsName() {
		return this.parentRsName;
	}

	public void setParentRsName(String parentRsName) {
		this.parentRsName = parentRsName;
	}

	public Date getRsContractAttachedDate() {
		return this.rsContractAttachedDate;
	}

	public void setRsContractAttachedDate(Date rsContractAttachedDate) {
		this.rsContractAttachedDate = rsContractAttachedDate;
	}

	public String getRsTransRefId() {
		return this.rsTransRefId;
	}

	public void setRsTransRefId(String rsTransRefId) {
		this.rsTransRefId = rsTransRefId;
	}

	public String getRsTransRefNo() {
		return this.rsTransRefNo;
	}

	public void setRsTransRefNo(String rsTransRefNo) {
		this.rsTransRefNo = rsTransRefNo;
	}

	public String getRsTransRefName() {
		return this.rsTransRefName;
	}

	public void setRsTransRefName(String rsTransRefName) {
		this.rsTransRefName = rsTransRefName;
	}

	public String getRebateRuleAssociation() {
		return this.rebateRuleAssociation;
	}

	public void setRebateRuleAssociation(String rebateRuleAssociation) {
		this.rebateRuleAssociation = rebateRuleAssociation;
	}

	public String getPaymentGracePeriod() {
		return this.paymentGracePeriod;
	}

	public void setPaymentGracePeriod(String paymentGracePeriod) {
		this.paymentGracePeriod = paymentGracePeriod;
	}

	public String getMakePayableTo() {
		return this.makePayableTo;
	}

	public void setMakePayableTo(String makePayableTo) {
		this.makePayableTo = makePayableTo;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getRsAlias() {
		return this.rsAlias;
	}

	public void setRsAlias(String rsAlias) {
		this.rsAlias = rsAlias;
	}

	public String getFormulaMethodId() {
		return this.formulaMethodId;
	}

	public void setFormulaMethodId(String formulaMethodId) {
		this.formulaMethodId = formulaMethodId;
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

	public Integer getDeductionInclusion() {
		return this.deductionInclusion;
	}

	public void setDeductionInclusion(Integer deductionInclusion) {
		this.deductionInclusion = deductionInclusion;
	}

	public Integer getCalculationType() {
		return this.calculationType;
	}

	public void setCalculationType(Integer calculationType) {
		this.calculationType = calculationType;
	}

	public Integer getCalculationLevel() {
		return this.calculationLevel;
	}

	public void setCalculationLevel(Integer calculationLevel) {
		this.calculationLevel = calculationLevel;
	}

	public Integer getCalculationRule() {
		return this.calculationRule;
	}

	public void setCalculationRule(Integer calculationRule) {
		this.calculationRule = calculationRule;
	}

	public Integer getCalculationRuleLevel() {
		return this.calculationRuleLevel;
	}

	public void setCalculationRuleLevel(Integer calculationRuleLevel) {
		this.calculationRuleLevel = calculationRuleLevel;
	}

	public Integer getEvaluationRuleType() {
		return this.evaluationRuleType;
	}

	public void setEvaluationRuleType(Integer evaluationRuleType) {
		this.evaluationRuleType = evaluationRuleType;
	}

	public Integer getEvaluationRuleLevel() {
		return this.evaluationRuleLevel;
	}

	public void setEvaluationRuleLevel(Integer evaluationRuleLevel) {
		this.evaluationRuleLevel = evaluationRuleLevel;
	}

	public Integer getEvaluationRuleOrAssociation() {
		return this.evaluationRuleOrAssociation;
	}

	public void setEvaluationRuleOrAssociation(Integer evaluationRuleOrAssociation) {
		this.evaluationRuleOrAssociation = evaluationRuleOrAssociation;
	}

	public Set getRsContractDetailses() {
		return this.rsContractDetailses;
	}

	public void setRsContractDetailses(Set rsContractDetailses) {
		this.rsContractDetailses = rsContractDetailses;
	}

}
