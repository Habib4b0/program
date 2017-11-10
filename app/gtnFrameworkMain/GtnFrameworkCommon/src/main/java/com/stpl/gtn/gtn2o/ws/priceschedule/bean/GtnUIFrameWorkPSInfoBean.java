/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.priceschedule.bean;

import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameWorkPSInfoBean {

	public GtnUIFrameWorkPSInfoBean() {
		super();
	}

	private String psName;
	private String psNo;
	private String psId;
	private Integer psType;
	private Integer psCategory;
	private Integer psStatus;
	private Integer psDesignation;
	private Date psStartDate;
	private Date psEndDate;

	private Integer psTradeClass;

	private String parentpSNo;
	private String parentPsId;
	private String internalNotes;

	private String createdBy;
	private String modifiedBy;

	private String parentPsSid;
	private Integer systemId;

	private List<NotesTabBean> noteBeanList;

	private List<Object> ifpDataList;

	public String getPsName() {
		return psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	public String getPsNo() {
		return psNo;
	}

	public void setPsNo(String psNo) {
		this.psNo = psNo;
	}

	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}

	public Integer getPsType() {
		return psType;
	}

	public void setPsType(Integer psType) {
		this.psType = psType;
	}

	public Integer getPsCategory() {
		return psCategory;
	}

	public void setPsCategory(Integer psCategory) {
		this.psCategory = psCategory;
	}

	public Integer getPsStatus() {
		return psStatus;
	}

	public void setPsStatus(Integer psStatus) {
		this.psStatus = psStatus;
	}

	public Integer getPsDesignation() {
		return psDesignation;
	}

	public void setPsDesignation(Integer psDesignation) {
		this.psDesignation = psDesignation;
	}

	public Date getPsStartDate() {
		return  psStartDate==null ? null :(Date)psStartDate.clone();
	}

	public void setPsStartDate(Date psStartDate) {
		this.psStartDate = psStartDate==null ? null :(Date)psStartDate.clone();
	}

	public Date getPsEndDate() {
		return  psEndDate==null ? null :(Date)psEndDate.clone();
	}

	public void setPsEndDate(Date psEndDate) {
		this.psEndDate = psEndDate==null ? null :(Date)psEndDate.clone();
	}

	public Integer getPsTradeClass() {
		return psTradeClass;
	}

	public void setPsTradeClass(Integer psTradeClass) {
		this.psTradeClass = psTradeClass;
	}

	public String getParentpSNo() {
		return parentpSNo;
	}

	public void setParentpSNo(String parentpSNo) {
		this.parentpSNo = parentpSNo;
	}

	public String getParentPsId() {
		return parentPsId;
	}

	public void setParentPsId(String parentPsId) {
		this.parentPsId = parentPsId;
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
		return noteBeanList != null ? new ArrayList<>(noteBeanList) : noteBeanList;
	}

	public void setNoteBeanList(List<NotesTabBean> noteBeanList) {
		this.noteBeanList = (noteBeanList != null ? new ArrayList<>(noteBeanList) : noteBeanList);
	}

	public String getParentPsSid() {
		return parentPsSid;
	}

	public void setParentPsSid(String parentPsSid) {
		this.parentPsSid = parentPsSid;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public List<Object> getIfpDataList() {
		return ifpDataList != null ? Collections.unmodifiableList(ifpDataList) : ifpDataList;
	}

	public void setIfpDataList(List<Object> ifpDataList) {
		this.ifpDataList = (ifpDataList != null ? Collections.unmodifiableList(ifpDataList) : ifpDataList);
	}

}
