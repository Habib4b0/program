package com.stpl.app.galworkflow.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author satheesh
 */
public class InboxDashboardDTO implements Serializable {

    private int noOfApprovals;
    private int approvalLevel;
    private int masterSid = 0;
    private String viewName = StringUtils.EMPTY;
    private String viewType = StringUtils.EMPTY;
    private Date modifiedDate = null;
    private Date creationDateFrom = null;
    private Date creationDateTo = null;
    private String privateView = StringUtils.EMPTY;
    private String publicView = StringUtils.EMPTY;
    private String contractId = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private HelperDTO contractType = new HelperDTO();
    private String companyId = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private String companyNo = StringUtils.EMPTY;
    private String itemId = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String workflowId = StringUtils.EMPTY;
    private String workflowName = StringUtils.EMPTY;
    private String workflowDescription = StringUtils.EMPTY;
    private String businessProcess = StringUtils.EMPTY;
    private HelperDTO status;
    private String createdBy = StringUtils.EMPTY;
    private String approvedBy = StringUtils.EMPTY;
    private String modifiedBy = StringUtils.EMPTY;
    private Date creationDate = null;
    private Date approvedDate = null;
    private String createdById = StringUtils.EMPTY;
    private String approvedById = StringUtils.EMPTY;
    private Date creationDateRangeFrom = null;
    private Date creationDateRangeTo = null;
    private Date approvedDateRangeFrom = null;
    private Date approvedDateRangeTo = null;
    private int workflowMasterSystemId = 0;
    private int workflowInboxSystemId = 0;
    private int workflowInboxCreatorId = 0;
    private String workFlowStatus;
    private HelperDTO deductionValue = new HelperDTO();
    private String deductionLevel = StringUtils.EMPTY;
    private String contractStructure = StringUtils.EMPTY;

    public int getNoOfApprovals() {
        return noOfApprovals;
    }

    public void setNoOfApprovals(int noOfApprovals) {
        this.noOfApprovals = noOfApprovals;
    }

    public int getApprovalLevel() {
        return approvalLevel;
    }

    public void setApprovalLevel(int approvalLevel) {
        this.approvalLevel = approvalLevel;
    }

    public int getMasterSid() {
        return masterSid;
    }

    public void setMasterSid(int masterSid) {
        this.masterSid = masterSid;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getPrivateView() {
        return privateView;
    }

    public void setPrivateView(String privateView) {
        this.privateView = privateView;
    }

    public String getPublicView() {
        return publicView;
    }

    public void setPublicView(String publicView) {
        this.publicView = publicView;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public HelperDTO getContractType() {
        return contractType;
    }

    public void setContractType(HelperDTO contractType) {
        this.contractType = contractType;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getWorkflowDescription() {
        return workflowDescription;
    }

    public void setWorkflowDescription(String workflowDescription) {
        this.workflowDescription = workflowDescription;
    }

    public String getBusinessProcess() {
        return businessProcess;
    }

    public void setBusinessProcess(String businessProcess) {
        this.businessProcess = businessProcess;
    }

    public HelperDTO getStatus() {
        return status;
    }

    public void setStatus(HelperDTO status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getApprovedById() {
        return approvedById;
    }

    public void setApprovedById(String approvedById) {
        this.approvedById = approvedById;
    }

    public Date getCreationDateRangeFrom() {
        return creationDateRangeFrom;
    }

    public void setCreationDateRangeFrom(Date creationDateRangeFrom) {
        this.creationDateRangeFrom = creationDateRangeFrom;
    }

    public Date getCreationDateRangeTo() {
        return creationDateRangeTo;
    }

    public void setCreationDateRangeTo(Date creationDateRangeTo) {
        this.creationDateRangeTo = creationDateRangeTo;
    }

    public Date getApprovedDateRangeFrom() {
        return approvedDateRangeFrom;
    }

    public void setApprovedDateRangeFrom(Date approvedDateRangeFrom) {
        this.approvedDateRangeFrom = approvedDateRangeFrom;
    }

    public Date getApprovedDateRangeTo() {
        return approvedDateRangeTo;
    }

    public void setApprovedDateRangeTo(Date approvedDateRangeTo) {
        this.approvedDateRangeTo = approvedDateRangeTo;
    }

    public int getWorkflowMasterSystemId() {
        return workflowMasterSystemId;
    }

    public void setWorkflowMasterSystemId(int workflowMasterSystemId) {
        this.workflowMasterSystemId = workflowMasterSystemId;
    }

    public String getWorkFlowStatus() {
        return workFlowStatus;
    }

    public void setWorkFlowStatus(String workFlowStatus) {
        this.workFlowStatus = workFlowStatus;
    }

    public HelperDTO getDeductionValue() {
        return deductionValue;
    }

    public void setDeductionValue(HelperDTO deductionValue) {
        this.deductionValue = deductionValue;
    }

    public String getDeductionLevel() {
        return deductionLevel;
    }

    public void setDeductionLevel(String deductionLevel) {
        this.deductionLevel = deductionLevel;
    }

    public int getWorkflowInboxSystemId() {
        return workflowInboxSystemId;
    }

    public void setWorkflowInboxSystemId(int workflowInboxSystemId) {
        this.workflowInboxSystemId = workflowInboxSystemId;
    }

    public int getWorkflowInboxCreatorId() {
        return workflowInboxCreatorId;
    }

    public void setWorkflowInboxCreatorId(int workflowInboxCreatorId) {
        this.workflowInboxCreatorId = workflowInboxCreatorId;
    }

    public String getContractStructure() {
        return contractStructure;
    }

    public void setContractStructure(String contractStructure) {
        this.contractStructure = contractStructure;
    }

}
