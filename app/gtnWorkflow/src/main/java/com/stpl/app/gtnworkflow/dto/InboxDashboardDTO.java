package com.stpl.app.gtnworkflow.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    private HelperDTO workflowStatusValue = new HelperDTO();
    private String brandId = StringUtils.EMPTY;
    private String brandName = StringUtils.EMPTY;
    private Date glDate = null;
    private String deductionNo = StringUtils.EMPTY;
    private String deductionName = StringUtils.EMPTY;
    private int companyValue;
    private int businessUnitValue;
    private List adjustmentType;
    private String adjustmentTypeValue = StringUtils.EMPTY;
    //Name of the adjustmentType
    private String adjustmentTypeName = StringUtils.EMPTY;
    private String configurationType = StringUtils.EMPTY;
    private String businessUnit = StringUtils.EMPTY;
    private String company = StringUtils.EMPTY;
    private String businessUnitNo;
    private String businessUnitId;
    private String businessUnitName;
    private boolean viewFlag = false;
    
    private String customerHierSid = StringUtils.EMPTY;
    private String customerHierarchyLevel = StringUtils.EMPTY;
    private String custRelationshipBuilderSid = StringUtils.EMPTY;
    private String productHierSid = StringUtils.EMPTY;
    private String productHierarchyLevel = StringUtils.EMPTY;
    private String prodRelationshipBuilderSid = StringUtils.EMPTY;
    
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

    public Date getCreationDateFrom() {
        return creationDateFrom;
    }

    public void setCreationDateFrom(Date creationDateFrom) {
        this.creationDateFrom = creationDateFrom;
    }

    public Date getCreationDateTo() {
        return creationDateTo;
    }

    public void setCreationDateTo(Date creationDateTo) {
        this.creationDateTo = creationDateTo;
    }

    public HelperDTO getWorkflowStatusValue() {
        return workflowStatusValue;
    }

    public void setWorkflowStatusValue(HelperDTO workflowStatusValue) {
        this.workflowStatusValue = workflowStatusValue;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Date getGlDate() {
        return glDate;
    }

    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }

    public String getDeductionNo() {
        return deductionNo;
    }

    public void setDeductionNo(String deductionNo) {
        this.deductionNo = deductionNo;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public void setDeductionName(String deductionName) {
        this.deductionName = deductionName;
    }

    public int getCompanyValue() {
        return companyValue;
    }

    public void setCompanyValue(int companyValue) {
        this.companyValue = companyValue;
    }

    public int getBusinessUnitValue() {
        return businessUnitValue;
    }

    public void setBusinessUnitValue(int businessUnitValue) {
        this.businessUnitValue = businessUnitValue;
    }

    public List getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(List adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getAdjustmentTypeValue() {
        return adjustmentTypeValue;
    }

    public void setAdjustmentTypeValue(String adjustmentTypeValue) {
        this.adjustmentTypeValue = adjustmentTypeValue;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getAdjustmentTypeName() {
        return adjustmentTypeName;
    }

    public void setAdjustmentTypeName(String adjustmentTypeName) {
        this.adjustmentTypeName = adjustmentTypeName;
    }

    public String getBusinessUnitNo() {
        return businessUnitNo;
    }

    public void setBusinessUnitNo(String businessUnitNo) {
        this.businessUnitNo = businessUnitNo;
    }

    public String getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(String businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public boolean isViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(boolean viewFlag) {
        this.viewFlag = viewFlag;
    }
    
    public String getConfigurationType() {
        return configurationType;
}

    public void setConfigurationType(String configurationType) {
        this.configurationType = configurationType;
    }

    public String getCustomerHierSid() {
        return customerHierSid;
    }

    public void setCustomerHierSid(String customerHierSid) {
        this.customerHierSid = customerHierSid;
    }

    public String getCustomerHierarchyLevel() {
        return customerHierarchyLevel;
    }

    public void setCustomerHierarchyLevel(String customerHierarchyLevel) {
        this.customerHierarchyLevel = customerHierarchyLevel;
    }

    public String getCustRelationshipBuilderSid() {
        return custRelationshipBuilderSid;
    }

    public void setCustRelationshipBuilderSid(String custRelationshipBuilderSid) {
        this.custRelationshipBuilderSid = custRelationshipBuilderSid;
    }

    public String getProductHierarchyLevel() {
        return productHierarchyLevel;
    }

    public void setProductHierarchyLevel(String productHierarchyLevel) {
        this.productHierarchyLevel = productHierarchyLevel;
    }

    public String getProdRelationshipBuilderSid() {
        return prodRelationshipBuilderSid;
    }

    public void setProdRelationshipBuilderSid(String prodRelationshipBuilderSid) {
        this.prodRelationshipBuilderSid = prodRelationshipBuilderSid;
    }

    public String getProductHierSid() {
        return productHierSid;
    }

    public void setProductHierSid(String productHierSid) {
        this.productHierSid = productHierSid;
    }
}
