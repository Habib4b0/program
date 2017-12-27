/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.dto;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.HelperUtils;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author satheesh.n
 */
public class RelationshipOutboundDTO implements Serializable {

    // To search the fileds from UI
    private String relationshipName = HelperUtils.EMPTY;
    private String relationshipDescription = HelperUtils.EMPTY;
    private HelperDTO hierarchyNameDDLB = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    private Date startDateFrom;
    private Date startDateTo;
    private Date creationDateFrom;
    private Date creationDateTo;
    private String option1 = HelperUtils.EMPTY;
    // to load table
    private HelperDTO hierarchyNameDdlb;
    private String relationshipDesc = StringUtils.EMPTY;
    private String relationshipType;
    private Date startDate;
    private String resultRSName;
    private String resultRSDesc;
    private String resultRSType;
    private String resultRSHierarchyName;
    private int hierarchyVersionNo;
    private String resultCreatedBy;
    private String rbSystemId = StringUtils.EMPTY;
    private int SystemID;
    private boolean check;

    public int getSystemID() {
        return SystemID;
    }

    public void setSystemID(int SystemID) {
        this.SystemID = SystemID;
    }
    private Date createdDate;
    private Date modifiedDate;

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

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public String getHierarchyName() {
        return hierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }
    private String createdBy;
    private int versionNo;
    private String hierarchyName = StringUtils.EMPTY;

    public String getRelationshipName() {
        return relationshipName;
    }

    public String getRelationshipDescription() {
        return relationshipDescription;
    }

    public HelperDTO getHierarchyNameDDLB() {
        return hierarchyNameDDLB;
    }

    public Date getStartDateFrom() {
        return startDateFrom;
    }

    public Date getStartDateTo() {
        return startDateTo;
    }

    public Date getCreationDateFrom() {
        return creationDateFrom;
    }

    public Date getCreationDateTo() {
        return creationDateTo;
    }

    public String getOption1() {
        return option1;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public void setRelationshipDescription(String relationshipDescription) {
        this.relationshipDescription = relationshipDescription;
    }

    public void setHierarchyNameDDLB(HelperDTO hierarchyNameDDLB) {
        this.hierarchyNameDDLB = hierarchyNameDDLB;
    }

    public void setStartDateFrom(Date startDateFrom) {
        this.startDateFrom = startDateFrom;
    }

    public void setStartDateTo(Date startDateTo) {
        this.startDateTo = startDateTo;
    }

    public void setCreationDateFrom(Date creationDateFrom) {
        this.creationDateFrom = creationDateFrom;
    }

    public void setCreationDateTo(Date creationDateTo) {
        this.creationDateTo = creationDateTo;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getRbSystemId() {
        return rbSystemId;
    }

    public void setRbSystemId(String rbSystemId) {
        this.rbSystemId = rbSystemId;
    }
    
}
