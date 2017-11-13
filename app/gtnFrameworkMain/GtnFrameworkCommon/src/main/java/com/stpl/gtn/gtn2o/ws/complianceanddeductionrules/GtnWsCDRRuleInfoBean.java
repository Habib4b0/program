/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.complianceanddeductionrules;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import java.util.ArrayList;
import java.util.List;

public class GtnWsCDRRuleInfoBean {

	public GtnWsCDRRuleInfoBean() {
		super();
	}

	private Integer userId;
	private Integer ruleType;
	private Integer ruleCategory;
	private String ruleNo;
	private String ruleName;
	private String internalNotes;
	private Integer systemId;

	private boolean updateFlag;

	private List<GtnWsCDRRuleDetailBean> ruleDetailBeanList;

	private List<NotesTabBean> noteBeanList;

	public Integer getRuleType() {
		return ruleType;
	}

	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
	}

	public Integer getRuleCategory() {
		return ruleCategory;
	}

	public void setRuleCategory(Integer ruleCategory) {
		this.ruleCategory = ruleCategory;
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

	public List<GtnWsCDRRuleDetailBean> getRuleDetailBeanList() {
		   return ruleDetailBeanList != null ? new ArrayList<>(ruleDetailBeanList) : null;
	}

	public void setRuleDetailBeanList(List<GtnWsCDRRuleDetailBean> ruleDetailBeanList) {
		this.ruleDetailBeanList = ruleDetailBeanList != null ? new ArrayList<>(ruleDetailBeanList) : null;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getInternalNotes() {
		return internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		this.internalNotes = internalNotes;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public List<NotesTabBean> getNoteBeanList() {
		return noteBeanList != null ? new ArrayList<>(noteBeanList) : null;
	}

	public void setNoteBeanList(List<NotesTabBean> noteBeanList) {
		this.noteBeanList = noteBeanList != null ? new ArrayList<>(noteBeanList) : null;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
