/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.abstractsearch.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author rohitvignesh.s
 */
public class SearchResultsDTO implements Serializable {

    public String getCompanyGroupName() {
        return companyGroupName;
    }

    public void setCompanyGroupName(String companyGroupName) {
        this.companyGroupName = companyGroupName;
    }

    public String getCompanyGroupNo() {
        return companyGroupNo;
    }

    public void setCompanyGroupNo(String companyGroupNo) {
        this.companyGroupNo = companyGroupNo;
    }

    public String getCompanyGroupDescription() {
        return companyGroupDescription;
    }

    public void setCompanyGroupDescription(String companyGroupDescription) {
        this.companyGroupDescription = companyGroupDescription;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public String getItemGroupNo() {
        return itemGroupNo;
    }

    public void setItemGroupNo(String itemGroupNo) {
        this.itemGroupNo = itemGroupNo;
    }

    public String getItemGroupDesc() {
        return itemGroupDesc;
    }

    public void setItemGroupDesc(String itemGroupDesc) {
        this.itemGroupDesc = itemGroupDesc;
    }

    public String getHierarchyName() {
        return hierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    public String getHierarchyType() {
        return hierarchyType;
    }

    public void setHierarchyType(String hierarchyType) {
        this.hierarchyType = hierarchyType;
    }

    public String getLevelValues() {
        return levelValues;
    }

    public void setLevelValues(String levelValues) {
        this.levelValues = levelValues;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Date getCreatedDateFrom() {
        return createdDateFrom;
    }

    public void setCreatedDateFrom(Date createdDateFrom) {
        this.createdDateFrom = createdDateFrom;
    }

    public Date getCreatedDateTo() {
        return createdDateTo;
    }

    public void setCreatedDateTo(Date createdDateTo) {
        this.createdDateTo = createdDateTo;
    }

    public String getHierarchyCategory() {
        return hierarchyCategory;
    }

    public void setHierarchyCategory(String hierarchyCategory) {
        this.hierarchyCategory = hierarchyCategory;
    }

    public int getNoOfLevels() {
        return noOfLevels;
    }

    public void setNoOfLevels(int noOfLevels) {
        this.noOfLevels = noOfLevels;
    }
    
    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public HelperDTO getHierarchyNameDdlb() {
        return hierarchyNameDdlb;
    }

    public void setHierarchyNameDdlb(HelperDTO hierarchyNameDdlb) {
        this.hierarchyNameDdlb = hierarchyNameDdlb;
    }

    public String getRelationshipDesc() {
        return relationshipDesc;
    }

    public void setRelationshipDesc(String relationshipDesc) {
        this.relationshipDesc = relationshipDesc;
    }

    public Date getStartDateTo() {
        return startDateTo;
    }

    public void setStartDateTo(Date startDateTo) {
        this.startDateTo = startDateTo;
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

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountNo() {
        return discountNo;
    }

    public void setDiscountNo(String discountNo) {
        this.discountNo = discountNo;
    }

    public String getDiscountDesc() {
        return discountDesc;
    }

    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getCustomerGroupSystemId() {
        return customerGroupSystemId;
    }

    public void setCustomerGroupSystemId(int customerGroupSystemId) {
        this.customerGroupSystemId = customerGroupSystemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHierarchyDefinitionSystemId() {
        return hierarchyDefinitionSystemId;
    }

    public void setHierarchyDefinitionSystemId(int hierarchyDefinitionSystemId) {
        this.hierarchyDefinitionSystemId = hierarchyDefinitionSystemId;
    }

    public String getHierarchyCategoryInString() {
        return hierarchyCategoryInString;
    }

    public void setHierarchyCategoryInString(String hierarchyCategoryInString) {
        this.hierarchyCategoryInString = hierarchyCategoryInString;
    }

    public String getResultRSName() {
        return resultRSName;
    }

    public void setResultRSName(String resultRSName) {
        this.resultRSName = resultRSName;
    }

    public String getResultRSDesc() {
        return resultRSDesc;
    }

    public void setResultRSDesc(String resultRSDesc) {
        this.resultRSDesc = resultRSDesc;
    }

    public String getResultRSType() {
        return resultRSType;
    }

    public void setResultRSType(String resultRSType) {
        this.resultRSType = resultRSType;
    }

    public String getResultRSHierarchyName() {
        return resultRSHierarchyName;
    }

    public void setResultRSHierarchyName(String resultRSHierarchyName) {
        this.resultRSHierarchyName = resultRSHierarchyName;
    }

    public int getHierarchyVersionNo() {
        return hierarchyVersionNo;
    }

    public void setHierarchyVersionNo(int hierarchyVersionNo) {
        this.hierarchyVersionNo = hierarchyVersionNo;
    }

    public String getResultCreatedBy() {
        return resultCreatedBy;
    }

    public void setResultCreatedBy(String resultCreatedBy) {
        this.resultCreatedBy = resultCreatedBy;
    }

    public int getRbSystemId() {
        return rbSystemId;
    }

    public void setRbSystemId(int rbSystemId) {
        this.rbSystemId = rbSystemId;
    }

    public int getItemGroupSystemId() {
        return itemGroupSystemId;
    }

    public void setItemGroupSystemId(int itemGroupSystemId) {
        this.itemGroupSystemId = itemGroupSystemId;
    }

    public HelperDTO getCompanyDdlb() {
        return companyDdlb;
    }

    public void setCompanyDdlb(HelperDTO companyDdlb) {
        this.companyDdlb = companyDdlb;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public boolean isRecordLockStatus() {
        return recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
       this.recordLockStatus = recordLockStatus;
    }

    public int getDeductionGroupSid() {
        return deductionGroupSid;
    }

    public void setDeductionGroupSid(int deductionGroupSid) {
        this.deductionGroupSid = deductionGroupSid;
    }
    
    private String systemID = StringUtils.EMPTY;
    private boolean recordLockStatus;
    
    //Customer Group Master
    private String companyGroupName = StringUtils.EMPTY;
    private String companyGroupNo = StringUtils.EMPTY;
    private String companyGroupDescription = StringUtils.EMPTY;
    private int versionNo;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private int customerGroupSystemId;
    private String status;
  
    //Item Group Master
    private String itemGroupName = StringUtils.EMPTY;
    private String itemGroupNo = StringUtils.EMPTY;
    private String itemGroupDesc = StringUtils.EMPTY;     
    private int itemGroupSystemId;
    private HelperDTO companyDdlb;
 
    //Hierarchy Definition   
    private String hierarchyName = StringUtils.EMPTY;
    private String hierarchyType = StringUtils.EMPTY;
    private String levelValues = StringUtils.EMPTY;     
    private String levelName = StringUtils.EMPTY;  
    private Date createdDateFrom;  
    private Date createdDateTo; 
    private String hierarchyCategory = StringUtils.EMPTY; 
    private int noOfLevels;
    private int hierarchyDefinitionSystemId;
    private String hierarchyCategoryInString= StringUtils.EMPTY;
    
    
    //Relationship Builder  
    private String relationshipName = StringUtils.EMPTY;
    private HelperDTO hierarchyNameDdlb;
    private String relationshipDesc = StringUtils.EMPTY;     
    private Date startDateTo;
    private Date creationDateFrom;  
    private Date creationDateTo;  
    private String relationshipType;
    private Date startDate;
    private String resultRSName;
    private String resultRSDesc;
    private String resultRSType;
    private String resultRSHierarchyName;
    private int hierarchyVersionNo;
    private String resultCreatedBy;
    private int rbSystemId;
    
   //Deduction Grouping
    private String discountName = StringUtils.EMPTY;
    private String discountNo = StringUtils.EMPTY;
    private String discountDesc = StringUtils.EMPTY;     
    private int version;
    private int deductionGroupSid;
}
