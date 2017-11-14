/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.rebateplan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import java.util.Collections;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsRebatePlanInfoBean {

	public GtnWsRebatePlanInfoBean() {
		super();
	}

	private Integer systemId;

	private String rebatePlanId = GtnFrameworkCommonStringConstants.STRING_EMPTY;

	private String rebatePlanName = GtnFrameworkCommonStringConstants.STRING_EMPTY;

	private String rebatePlanNo = GtnFrameworkCommonStringConstants.STRING_EMPTY;

	private Integer formulaTypeData;

	private String formulaType = GtnFrameworkCommonStringConstants.STRING_EMPTY;

	private Integer rebatePlanType;

	private Integer rebatePlanStatus;

	private Integer rebateStructure;

	private Integer rangeBasedOn;

	private Integer netSalesFormula;

	private Integer netSalesRule;

	private Integer rebateBasedOn;

	private String selfGrowthIndicator = GtnFrameworkCommonStringConstants.STRING_EMPTY;

	private String selfGrowthReference = GtnFrameworkCommonStringConstants.STRING_EMPTY;

	private Date selfGrowthFrom;

	private Date selfGrowthTo;

	private String marketShareIndicator = GtnFrameworkCommonStringConstants.STRING_EMPTY;

	private String marketShareReference = GtnFrameworkCommonStringConstants.STRING_EMPTY;

	private Date marketShareFrom;

	private Date marketShareTo;

	private String internalNotes = GtnFrameworkCommonStringConstants.STRING_EMPTY;

	private List<GtnWsRebatePlanRuleDetailBean> rebatePlanRuleDetailBean = new ArrayList<>();

	private List<NotesTabBean> noteBeanList = new ArrayList<>();

	private Integer userId;

	private Integer sessionId;

	private String netSalesFormulaData = GtnFrameworkCommonStringConstants.STRING_EMPTY;

	private String netSalesRuleData = GtnFrameworkCommonStringConstants.STRING_EMPTY;

	private boolean rebatePlanIdAlreadyExist;

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getRebatePlanId() {
		return rebatePlanId;
	}

	public void setRebatePlanId(String rebatePlanId) {
		this.rebatePlanId = rebatePlanId;
	}

	public String getRebatePlanName() {
		return rebatePlanName;
	}

	public void setRebatePlanName(String rebatePlanName) {
		this.rebatePlanName = rebatePlanName;
	}

	public String getRebatePlanNo() {
		return rebatePlanNo;
	}

	public void setRebatePlanNo(String rebatePlanNo) {
		this.rebatePlanNo = rebatePlanNo;
	}

	public Integer getRebatePlanType() {
		return rebatePlanType;
	}

	public void setRebatePlanType(Integer rebatePlanType) {
		this.rebatePlanType = rebatePlanType;
	}

	public Integer getRebatePlanStatus() {
		return rebatePlanStatus;
	}

	public void setRebatePlanStatus(Integer rebatePlanStatus) {
		this.rebatePlanStatus = rebatePlanStatus;
	}

	public Integer getRebateStructure() {
		return rebateStructure;
	}

	public void setRebateStructure(Integer rebateStructure) {
		this.rebateStructure = rebateStructure;
	}

	public Integer getRangeBasedOn() {
		return rangeBasedOn;
	}

	public void setRangeBasedOn(Integer rangeBasedOn) {
		this.rangeBasedOn = rangeBasedOn;
	}

	public Integer getNetSalesFormula() {
		return netSalesFormula;
	}

	public void setNetSalesFormula(Integer netSalesFormula) {
		this.netSalesFormula = netSalesFormula;
	}

	public Integer getNetSalesRule() {
		return netSalesRule;
	}

	public void setNetSalesRule(Integer netSalesRule) {
		this.netSalesRule = netSalesRule;
	}

	public Integer getRebateBasedOn() {
		return rebateBasedOn;
	}

	public void setRebateBasedOn(Integer rebateBasedOn) {
		this.rebateBasedOn = rebateBasedOn;
	}

	public Date getSelfGrowthFrom() {
		return cloneDate(selfGrowthFrom);
	}

	public void setSelfGrowthFrom(Date selfGrowthFrom) {
		this.selfGrowthFrom = cloneDate(selfGrowthFrom);
	}

	public Date getSelfGrowthTo() {
		return cloneDate(selfGrowthTo);
	}

	public void setSelfGrowthTo(Date selfGrowthTo) {
		this.selfGrowthTo = cloneDate(selfGrowthTo);
	}

	public String getSelfGrowthReference() {
		return selfGrowthReference;
	}

	public void setSelfGrowthReference(String selfGrowthReference) {
		this.selfGrowthReference = selfGrowthReference;
	}

	public String getMarketShareReference() {
		return marketShareReference;
	}

	public void setMarketShareReference(String marketShareReference) {
		this.marketShareReference = marketShareReference;
	}

	public Date getMarketShareFrom() {
		return cloneDate(marketShareFrom);
	}

	public void setMarketShareFrom(Date marketShareFrom) {
		this.marketShareFrom = cloneDate(marketShareFrom);
	}

	public Date getMarketShareTo() {
		return cloneDate(marketShareTo);
	}

	public void setMarketShareTo(Date marketShareTo) {
		this.marketShareTo = cloneDate(marketShareTo);
	}

	public String getInternalNotes() {
		return internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		this.internalNotes = internalNotes;
	}

	public List<GtnWsRebatePlanRuleDetailBean> getRebatePlanRuleDetailBean() {
		return rebatePlanRuleDetailBean != null ? Collections.unmodifiableList(rebatePlanRuleDetailBean) : rebatePlanRuleDetailBean;
	}

	public void setRebatePlanRuleDetailBean(List<GtnWsRebatePlanRuleDetailBean> rebatePlanRuleDetailBean) {
		this.rebatePlanRuleDetailBean = rebatePlanRuleDetailBean != null ? Collections.unmodifiableList(rebatePlanRuleDetailBean) : rebatePlanRuleDetailBean;
	}

	public List<NotesTabBean> getNoteBeanList() {
		return noteBeanList != null ? Collections.unmodifiableList(noteBeanList) : noteBeanList;
	}

	public void setNoteBeanList(List<NotesTabBean> noteBeanList) {
		this.noteBeanList = noteBeanList != null ? Collections.unmodifiableList(noteBeanList) : noteBeanList;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getSelfGrowthIndicator() {
		return selfGrowthIndicator;
	}

	public void setSelfGrowthIndicator(String selfGrowthIndicator) {
		this.selfGrowthIndicator = selfGrowthIndicator;
	}

	public String getMarketShareIndicator() {
		return marketShareIndicator;
	}

	public void setMarketShareIndicator(String marketShareIndicator) {
		this.marketShareIndicator = marketShareIndicator;
	}

	public String getNetSalesFormulaData() {
		return netSalesFormulaData;
	}

	public void setNetSalesFormulaData(String netSalesFormulaData) {
		this.netSalesFormulaData = netSalesFormulaData;
	}

	public String getNetSalesRuleData() {
		return netSalesRuleData;
	}

	public void setNetSalesRuleData(String netSalesRuleData) {
		this.netSalesRuleData = netSalesRuleData;
	}

	public boolean isRebatePlanIdAlreadyExist() {
		return rebatePlanIdAlreadyExist;
	}

	public void setRebatePlanIdAlreadyExist(boolean rebatePlanIdAlreadyExist) {
		this.rebatePlanIdAlreadyExist = rebatePlanIdAlreadyExist;
	}

	public Integer getFormulaTypeData() {
		return formulaTypeData;
	}

	public void setFormulaTypeData(Integer formulaTypeData) {
		this.formulaTypeData = formulaTypeData;
	}

	public String getFormulaType() {
		return formulaType;
	}

	public void setFormulaType(String formulaType) {
		this.formulaType = formulaType;
	}
        
        private Date cloneDate(Date date){
                return date != null ? (Date) date.clone() : date;
        } 
        
}