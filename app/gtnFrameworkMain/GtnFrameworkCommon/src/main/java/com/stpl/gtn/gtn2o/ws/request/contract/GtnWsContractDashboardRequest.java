/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.contract;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsContractDashboardRequest implements GtnWSRequestData {

	private GtnWsContractDashboardSessionBean contractDashboardBean;

	public GtnWsContractDashboardRequest() {
		super();
	}

	private GtnWsRecordBean tableBean;

	private GtnWsRecordBean treeBean;

	private List<GtnWsRecordBean> recordBeanList;

	private List<Object> values;

	private String userId;
	private String sessionId;

	private int contractId;
	private int cfpContractId;
	private int ifpContractId;
	private int rsModelId;
	private int psContractId;
	private int rsContractId;
	private int cfpModelId;
	private int ifpModelId;
	private int systemId;
	private int psModelId;
	private int workflowMasterId;
	private String populateField;
	private Object populateValue;
	private String sessionDate;
	private String contractStructure;

	public GtnWsRecordBean getTableBean() {
		return tableBean;
	}

	public void setTableBean(GtnWsRecordBean tableBean) {
		this.tableBean = tableBean;
	}

	public List<Object> getValues() {
		return values != null ? new ArrayList<>(values) : values;
	}

	public void setValues(List<Object> values) {
		this.values = values != null ? new ArrayList<>(values) : values;
	}

	public List<GtnWsRecordBean> getRecordBeanList() {
		return recordBeanList != null ? new ArrayList<>(recordBeanList) : recordBeanList;
	}

	public void setRecordBeanList(List<GtnWsRecordBean> recordBeanList) {
		this.recordBeanList = recordBeanList != null ? new ArrayList<>(recordBeanList) : recordBeanList;
	}

	public int getCfpContractId() {
		return cfpContractId;
	}

	public void setCfpContractId(int cfpContractId) {
		this.cfpContractId = cfpContractId;
	}

	public GtnWsRecordBean getTreeBean() {
		return treeBean;
	}

	public void setTreeBean(GtnWsRecordBean treeBean) {
		this.treeBean = treeBean;
	}

	public int getIfpContractId() {
		return ifpContractId;
	}

	public void setIfpContractId(int ifpContractId) {
		this.ifpContractId = ifpContractId;
	}

	public int getPsContractId() {
		return psContractId;
	}

	public void setPsContractId(int psContractId) {
		this.psContractId = psContractId;
	}

	public int getRsContractId() {
		return rsContractId;
	}

	public void setRsContractId(int rsContractId) {
		this.rsContractId = rsContractId;
	}

	public String getPopulateField() {
		return populateField;
	}

	public void setPopulateField(String populateField) {
		this.populateField = populateField;
	}

	public Object getPopulateValue() {
		return populateValue;
	}

	public void setPopulateValue(Object populateValue) {
		this.populateValue = populateValue;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public int getCfpModelId() {
		return cfpModelId;
	}

	public void setCfpModelId(int cfpModelId) {
		this.cfpModelId = cfpModelId;
	}

	public int getIfpModelId() {
		return ifpModelId;
	}

	public void setIfpModelId(int ifpModelId) {
		this.ifpModelId = ifpModelId;
	}

	public int getPsModelId() {
		return psModelId;
	}

	public void setPsModelId(int psModelId) {
		this.psModelId = psModelId;
	}

	public int getRsModelId() {
		return rsModelId;
	}

	public void setRsModelId(int rsModelId) {
		this.rsModelId = rsModelId;
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public String getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public GtnWsContractDashboardSessionBean getContractDashboardBean() {
		return contractDashboardBean;
	}

	public void setContractDashboardBean(GtnWsContractDashboardSessionBean contractDashboardBean) {
		this.contractDashboardBean = contractDashboardBean;
	}

	public int getWorkflowMasterId() {
		return workflowMasterId;
	}

	public void setWorkflowMasterId(int workflowMasterId) {
		this.workflowMasterId = workflowMasterId;
	}

	public String getContractStructure() {
		return contractStructure;
	}

	public void setContractStructure(String contractStructure) {
		this.contractStructure = contractStructure;
	}
        private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}
}
