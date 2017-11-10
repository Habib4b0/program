package com.stpl.gtn.gtn2o.ws.contractdashboard.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author STPL
 */
public class GtnWsContractWorkflowBean implements Serializable {

	private static final long serialVersionUID = -1L;

	public GtnWsContractWorkflowBean() {
		super();
	}

	private int workflowMasterSystemId;
	private Date createdDate;
	private int createdBy;
	private int approvedBy;
	private int contractId;
	private int workflowStatus;
	private String workflowStatusValue;
	private String workflowId;
	private String contractStructure;
	private String notes;
	private int noOfApprovals;
	private int approvalLevel;
	private String fileName;
	private String workflowDescription;
	private Date approvedDate;
	private String userType;
	private int modifiedBy;
	public static final String PATH = "..";

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	/**
	 * @return the workflowMasterSystemId
	 */
	public int getWorkflowMasterSystemId() {
		return workflowMasterSystemId;
	}

	/**
	 * @param workflowMasterSystemId
	 *            the workflowMasterSystemId to set
	 */
	public void setWorkflowMasterSystemId(int workflowMasterSystemId) {
		this.workflowMasterSystemId = workflowMasterSystemId;
	}

	/**
	 * @return the approvedBy
	 */
	public int getApprovedBy() {
		return approvedBy;
	}

	/**
	 * @param approvedBy
	 *            the approvedBy to set
	 */
	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the workflowStatus
	 */
	public int getWorkflowStatus() {
		return workflowStatus;
	}

	/**
	 * @param workflowStatus
	 *            the workflowStatus to set
	 */
	public void setWorkflowStatus(int workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	/**
	 * @return the workflowId
	 */
	public String getWorkflowId() {
		return workflowId;
	}

	/**
	 * @param workflowId
	 *            the workflowId to set
	 */
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public int getNoOfApprovals() {
		return noOfApprovals;
	}

	public void setNoOfApprovals(int noOfApprovals) {
		this.noOfApprovals = noOfApprovals;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getWorkflowDescription() {
		return workflowDescription;
	}

	public void setWorkflowDescription(String workflowDescription) {
		this.workflowDescription = workflowDescription;
	}

	public int getApprovalLevel() {
		return approvalLevel;
	}

	public void setApprovalLevel(int approvalLevel) {
		this.approvalLevel = approvalLevel;
	}

	public Date getApprovedDate() {
		return approvedDate == null ? null : (Date) approvedDate.clone();
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate == null ? null : (Date) approvedDate.clone();
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public String getWorkflowStatusValue() {
		return workflowStatusValue;
	}

	public void setWorkflowStatusValue(String workflowStatusValue) {
		this.workflowStatusValue = workflowStatusValue;
	}

	public String getContractStructure() {
		return contractStructure;
	}

	public void setContractStructure(String contractStructure) {
		this.contractStructure = contractStructure;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}