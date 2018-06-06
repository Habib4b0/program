/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.contractdashboard.beans;

import java.util.SortedSet;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsContractDashboardProcessBean {

	public GtnWsContractDashboardProcessBean() {
		super();
	}

	private String sessionId;
	private String userId;
	private int contractId;
	private int cfpContractId;
	private int ifpContractId;
	private int psContractId;
	private int rsContractId;
	private SortedSet<String> visibleTabIndexSet;
	private String[] hierarchyNo;
	private GtnWsRecordBean processBean;
	private String sessionDate;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public int getCfpContractId() {
		return cfpContractId;
	}

	public void setIfpContractId(int ifpContractId) {
		this.ifpContractId = ifpContractId;
	}

	public String[] getHierarchyNo() {
		return hierarchyNo != null ? hierarchyNo.clone() : null;
	}

	public void setHierarchyNo(String[] hierarchyNo) {
		this.hierarchyNo = hierarchyNo != null ? hierarchyNo.clone() : null;
	}

	public int getRsContractId() {
		return rsContractId;
	}

	public void setCfpContractId(int cfpContractId) {
		this.cfpContractId = cfpContractId;
	}

	public int getIfpContractId() {
		return ifpContractId;
	}

	public void setRsContractId(int rsContractId) {
		this.rsContractId = rsContractId;
	}

	public int getPsContractId() {
		return psContractId;
	}

	public void setPsContractId(int psContractId) {
		this.psContractId = psContractId;
	}

	public GtnWsRecordBean getProcessBean() {
		return processBean;
	}

	public void setProcessBean(GtnWsRecordBean processBean) {
		this.processBean = processBean;
	}

	public String getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}

	public SortedSet<String> getVisibleTabIndexSet() {
		return visibleTabIndexSet;
	}

	public void setVisibleTabIndexSet(SortedSet<String> visibleTabIndexSet) {
		this.visibleTabIndexSet = visibleTabIndexSet;
	}

}
