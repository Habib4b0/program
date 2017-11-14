/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.rebateschedule;

import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import java.util.Collections;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsRebateScheduleInfoBean {

	public GtnWsRebateScheduleInfoBean() {
		super();
	}

	private String rebateScheduleId;
	private String rebateScheduleNo;
	private String rebateScheduleName;
	private String rebateScheduleAliasId;
	private String parentRebateScheduleID;
	private String rsTransactionRefId;
	private String parentRebateScheduleName;
	private String rsTransactionRefName;
	private String paymentGracePeriod;
	private String evaluationRuleAssociation;
	private String calculationRule;

	private Integer rebateScheduleStatus;
	private Integer rebateScheduleType;
	private Integer rebateProgramType;
	private Integer rebateScheduleCategory;
	private Integer rebateScheduleTradeClass;
	private Integer rebateScheduleDesignation;
	private Integer rsUDC1;
	private Integer rsUDC4;
	private Integer rsUDC2;
	private Integer rsUDC5;
	private Integer rsUDC3;
	private Integer rsUDC6;
	private Integer rsDeductionInclusion;
	private Integer rsCalendar;
	private Integer rebateFrequency;
	private Integer paymentLevel;
	private Integer paymentFrequency;
	private Integer paymentTerms;
	private Integer paymentMethod;
	private Integer interestBearingBasis;
	private Integer evaluationRuleLevel;
	private Integer evaluationRuleType;
	private Integer interestBearingIndicator;
	private Integer calculationRuleLevel;
	private Integer calculationType;
	private Integer calculationLevel;
	private Integer rebateRuleType;

	//
	private Integer parentRebateScheduleSid;
	private Integer rsTransactionReSid;
	private Integer evaluationRuleAssociationSid;
	private Integer calculationRuleSid;

	private Date rebateScheduleStartDate;
	private Date rebateScheduleEndDate;

	private Integer systemId;
	private String internalNotes;
	private String columnId;
	private Object value;

	private List<NotesTabBean> noteBeanList;

	private List<Object> ifpDataList;
	private boolean checkAll;

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

	public String getRebateScheduleAliasId() {
		return rebateScheduleAliasId;
	}

	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	public void setRebateScheduleAliasId(String rebateScheduleAliasId) {
		this.rebateScheduleAliasId = rebateScheduleAliasId;
	}

	public String getParentRebateScheduleID() {
		return parentRebateScheduleID;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public void setParentRebateScheduleID(String parentRebateScheduleID) {
		this.parentRebateScheduleID = parentRebateScheduleID;
	}

	public String getRsTransactionRefId() {
		return rsTransactionRefId;
	}

	public void setRsTransactionRefId(String rsTransactionRefId) {
		this.rsTransactionRefId = rsTransactionRefId;
	}

	public String getParentRebateScheduleName() {
		return parentRebateScheduleName;
	}

	public void setParentRebateScheduleName(String parentRebateScheduleName) {
		this.parentRebateScheduleName = parentRebateScheduleName;
	}

	public String getRsTransactionRefName() {
		return rsTransactionRefName;
	}

	public void setRsTransactionRefName(String rsTransactionRefName) {
		this.rsTransactionRefName = rsTransactionRefName;
	}

	public String getPaymentGracePeriod() {
		return paymentGracePeriod;
	}

	public void setPaymentGracePeriod(String paymentGracePeriod) {
		this.paymentGracePeriod = paymentGracePeriod;
	}

	public String getEvaluationRuleAssociation() {
		return evaluationRuleAssociation;
	}

	public void setEvaluationRuleAssociation(String evaluationRuleAssociation) {
		this.evaluationRuleAssociation = evaluationRuleAssociation;
	}

	public Integer getRebateScheduleStatus() {
		return rebateScheduleStatus;
	}

	public void setRebateScheduleStatus(Integer rebateScheduleStatus) {
		this.rebateScheduleStatus = rebateScheduleStatus;
	}

	public Integer getRebateScheduleType() {
		return rebateScheduleType;
	}

	public void setRebateScheduleType(Integer rebateScheduleType) {
		this.rebateScheduleType = rebateScheduleType;
	}

	public Integer getRebateProgramType() {
		return rebateProgramType;
	}

	public void setRebateProgramType(Integer rebateProgramType) {
		this.rebateProgramType = rebateProgramType;
	}

	public Integer getRebateScheduleCategory() {
		return rebateScheduleCategory;
	}

	public void setRebateScheduleCategory(Integer rebateScheduleCategory) {
		this.rebateScheduleCategory = rebateScheduleCategory;
	}

	public Integer getRebateScheduleTradeClass() {
		return rebateScheduleTradeClass;
	}

	public void setRebateScheduleTradeClass(Integer rebateScheduleTradeClass) {
		this.rebateScheduleTradeClass = rebateScheduleTradeClass;
	}

	public Integer getRebateScheduleDesignation() {
		return rebateScheduleDesignation;
	}

	public void setRebateScheduleDesignation(Integer rebateScheduleDesignation) {
		this.rebateScheduleDesignation = rebateScheduleDesignation;
	}

	public Integer getRsUDC1() {
		return rsUDC1;
	}

	public void setRsUDC1(Integer rsUDC1) {
		this.rsUDC1 = rsUDC1;
	}

	public Integer getRsUDC4() {
		return rsUDC4;
	}

	public void setRsUDC4(Integer rsUDC4) {
		this.rsUDC4 = rsUDC4;
	}

	public Integer getRsUDC2() {
		return rsUDC2;
	}

	public void setRsUDC2(Integer rsUDC2) {
		this.rsUDC2 = rsUDC2;
	}

	public Integer getRsUDC5() {
		return rsUDC5;
	}

	public void setRsUDC5(Integer rsUDC5) {
		this.rsUDC5 = rsUDC5;
	}

	public Integer getRsUDC3() {
		return rsUDC3;
	}

	public void setRsUDC3(Integer rsUDC3) {
		this.rsUDC3 = rsUDC3;
	}

	public Integer getRsUDC6() {
		return rsUDC6;
	}

	public void setRsUDC6(Integer rsUDC6) {
		this.rsUDC6 = rsUDC6;
	}

	public Integer getRsDeductionInclusion() {
		return rsDeductionInclusion;
	}

	public void setRsDeductionInclusion(Integer rsDeductionInclusion) {
		this.rsDeductionInclusion = rsDeductionInclusion;
	}

	public Integer getRsCalendar() {
		return rsCalendar;
	}

	public void setRsCalendar(Integer rsCalendar) {
		this.rsCalendar = rsCalendar;
	}

	public Integer getRebateFrequency() {
		return rebateFrequency;
	}

	public void setRebateFrequency(Integer rebateFrequency) {
		this.rebateFrequency = rebateFrequency;
	}

	public Integer getPaymentLevel() {
		return paymentLevel;
	}

	public void setPaymentLevel(Integer paymentLevel) {
		this.paymentLevel = paymentLevel;
	}

	public Integer getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(Integer paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public Integer getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(Integer paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getInterestBearingBasis() {
		return interestBearingBasis;
	}

	public void setInterestBearingBasis(Integer interestBearingBasis) {
		this.interestBearingBasis = interestBearingBasis;
	}

	public Integer getEvaluationRuleLevel() {
		return evaluationRuleLevel;
	}

	public void setEvaluationRuleLevel(Integer evaluationRuleLevel) {
		this.evaluationRuleLevel = evaluationRuleLevel;
	}

	public Integer getEvaluationRuleType() {
		return evaluationRuleType;
	}

	public void setEvaluationRuleType(Integer evaluationRuleType) {
		this.evaluationRuleType = evaluationRuleType;
	}

	public Integer getInterestBearingIndicator() {
		return interestBearingIndicator;
	}

	public void setInterestBearingIndicator(Integer interestBearingIndicator) {
		this.interestBearingIndicator = interestBearingIndicator;
	}

	public Integer getCalculationRuleLevel() {
		return calculationRuleLevel;
	}

	public void setCalculationRuleLevel(Integer calculationRuleLevel) {
		this.calculationRuleLevel = calculationRuleLevel;
	}

	public Integer getCalculationType() {
		return calculationType;
	}

	public void setCalculationType(Integer calculationType) {
		this.calculationType = calculationType;
	}

	public Integer getCalculationLevel() {
		return calculationLevel;
	}

	public void setCalculationLevel(Integer calculationLevel) {
		this.calculationLevel = calculationLevel;
	}

	public Integer getRebateRuleType() {
		return rebateRuleType;
	}

	public void setRebateRuleType(Integer rebateRuleType) {
		this.rebateRuleType = rebateRuleType;
	}

	public Integer getParentRebateScheduleSid() {
		return parentRebateScheduleSid;
	}

	public void setParentRebateScheduleSid(Integer parentRebateScheduleSid) {
		this.parentRebateScheduleSid = parentRebateScheduleSid;
	}

	public Integer getRsTransactionReSid() {
		return rsTransactionReSid;
	}

	public void setRsTransactionReSid(Integer rsTransactionReSid) {
		this.rsTransactionReSid = rsTransactionReSid;
	}

	public Integer getEvaluationRuleAssociationSid() {
		return evaluationRuleAssociationSid;
	}

	public void setEvaluationRuleAssociationSid(Integer evaluationRuleAssociationSid) {
		this.evaluationRuleAssociationSid = evaluationRuleAssociationSid;
	}

	public Integer getCalculationRuleSid() {
		return calculationRuleSid;
	}

	public void setCalculationRuleSid(Integer calculationRuleSid) {
		this.calculationRuleSid = calculationRuleSid;
	}

	public Date getRebateScheduleStartDate() {
		return  rebateScheduleStartDate==null ? null :(Date)rebateScheduleStartDate.clone();
	}

	public void setRebateScheduleStartDate(Date rebateScheduleStartDate) {
		this.rebateScheduleStartDate = rebateScheduleStartDate==null ? null :(Date)rebateScheduleStartDate.clone();
	}

	public Date getRebateScheduleEndDate() {
		return  rebateScheduleEndDate==null ? null :(Date)rebateScheduleEndDate.clone();
	}

	public void setRebateScheduleEndDate(Date rebateScheduleEndDate) {
		this.rebateScheduleEndDate = rebateScheduleEndDate==null ? null :(Date)rebateScheduleEndDate.clone();
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public List<NotesTabBean> getNoteBeanList() {
		return noteBeanList != null ? Collections.unmodifiableList(noteBeanList) : noteBeanList;
	}

	public void setNoteBeanList(List<NotesTabBean> noteBeanList) {
		this.noteBeanList = (noteBeanList != null ? Collections.unmodifiableList(noteBeanList) : noteBeanList);
	}

	public String getInternalNotes() {
		return internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		this.internalNotes = internalNotes;
	}

	public String getCalculationRule() {
		return calculationRule;
	}

	public void setCalculationRule(String calculationRule) {
		this.calculationRule = calculationRule;
	}

	public List<Object> getIfpDataList() {
		return ifpDataList != null ? Collections.unmodifiableList(ifpDataList) : ifpDataList;
	}

	public void setIfpDataList(List<Object> ifpDataList) {
		this.ifpDataList = ifpDataList != null ? Collections.unmodifiableList(ifpDataList) : ifpDataList;
	}

}
