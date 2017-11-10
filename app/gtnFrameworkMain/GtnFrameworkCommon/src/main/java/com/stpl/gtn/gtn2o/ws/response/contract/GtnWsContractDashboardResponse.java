/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.contract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractWorkflowBean;
import com.stpl.gtn.gtn2o.ws.response.GtnWSResponseData;

public class GtnWsContractDashboardResponse implements GtnWSResponseData {

	public GtnWsContractDashboardResponse() {
		super();
	}

	private int count;

	private boolean success;

	private String message;

	private String messageType;

	private String messageHeader;

	private GtnWsRecordBean tableBean;
	private GtnWsRecordBean priceInfoBean;
	private GtnWsRecordBean rebateInfoBean;
	private GtnWsRecordBean treeBean;

	private List<Object> values;

	private GtnWsRecordBean contractInfoBean;
	private GtnWsRecordBean companyInfoBean;
	private GtnWsRecordBean itemInfoBean;

	private GtnWsContractWorkflowBean workflowBean;
	private int[] splitedContractStructure;
	private List<GtnUIFrameworkDataRow> contractAliasRecordList;
	private List<GtnUIFrameworkDataRow> notesTabRecordList;
	private String memberLevel;
	private String processLevel;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(String messageHeader) {
		this.messageHeader = messageHeader;
	}

	public GtnWsRecordBean getTableBean() {
		return tableBean;
	}

	public void setTableBean(GtnWsRecordBean tableBean) {
		this.tableBean = tableBean;
	}

	public GtnWsRecordBean getTreeBean() {
		return treeBean;
	}

	public void setTreeBean(GtnWsRecordBean treeBean) {
		this.treeBean = treeBean;
	}

	public List<Object> getValues() {
		return values != null ? new ArrayList<>(values) : values;
	}

	public void setValues(List<Object> values) {
		this.values = values != null ? new ArrayList<>(values) : values;
	}

	public GtnWsRecordBean getContractInfoBean() {
		return contractInfoBean;
	}

	public void setContractInfoBean(GtnWsRecordBean contractInfoBean) {
		this.contractInfoBean = contractInfoBean;
	}

	public GtnWsRecordBean getCompanyInfoBean() {
		return companyInfoBean;
	}

	public void setCompanyInfoBean(GtnWsRecordBean companyInfoBean) {
		this.companyInfoBean = companyInfoBean;
	}

	public GtnWsRecordBean getItemInfoBean() {
		return itemInfoBean;
	}

	public void setItemInfoBean(GtnWsRecordBean itemInfoBean) {
		this.itemInfoBean = itemInfoBean;
	}

	public GtnWsRecordBean getPriceInfoBean() {
		return priceInfoBean;
	}

	public void setPriceInfoBean(GtnWsRecordBean priceInfoBean) {
		this.priceInfoBean = priceInfoBean;
	}

	public GtnWsRecordBean getRebateInfoBean() {
		return rebateInfoBean;
	}

	public void setRebateInfoBean(GtnWsRecordBean rebateInfoBean) {
		this.rebateInfoBean = rebateInfoBean;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<GtnUIFrameworkDataRow> getContractAliasRecordList() {
		return contractAliasRecordList != null ? Collections.unmodifiableList(contractAliasRecordList)
				: contractAliasRecordList;
	}

	public void setContractAliasRecordList(List<GtnUIFrameworkDataRow> contractAliasRecordList) {
		this.contractAliasRecordList = contractAliasRecordList != null ? new ArrayList<>(contractAliasRecordList)
				: contractAliasRecordList;
	}

	public void addContractAliasRecordList(GtnUIFrameworkDataRow gtnUIFrameworkDataRow) {
		contractAliasRecordList.add(gtnUIFrameworkDataRow);
	}

	public List<GtnUIFrameworkDataRow> getNotesTabRecordList() {
		return notesTabRecordList != null ? Collections.unmodifiableList(notesTabRecordList) : notesTabRecordList;
	}

	public void addNotesTabRecordList(GtnUIFrameworkDataRow gtnUIFrameworkDataRow) {
		notesTabRecordList.add(gtnUIFrameworkDataRow);
	}

	public void setNotesTabRecordList(List<GtnUIFrameworkDataRow> notesTabRecordList) {
		this.notesTabRecordList = notesTabRecordList != null ? new ArrayList<>(notesTabRecordList) : notesTabRecordList;
	}

	public GtnWsContractWorkflowBean getWorkflowBean() {
		return workflowBean;
	}

	public void setWorkflowBean(GtnWsContractWorkflowBean workflowBean) {
		this.workflowBean = workflowBean;
	}

	public int[] getSplitedContractStructure() {
		return splitedContractStructure != null ? splitedContractStructure.clone() : splitedContractStructure;
	}

	public void setSplitedContractStructure(int[] splitedContractStructure) {
		this.splitedContractStructure = splitedContractStructure != null ? splitedContractStructure.clone()
				: splitedContractStructure;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getProcessLevel() {
		return processLevel;
	}

	public void setProcessLevel(String processLevel) {
		this.processLevel = processLevel;
	}

}
