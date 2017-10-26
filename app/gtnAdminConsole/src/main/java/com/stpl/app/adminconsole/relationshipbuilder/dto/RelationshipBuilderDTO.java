/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.relationshipbuilder.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.stpl.ifs.util.HelperDTO;


/**
 * The Class RelationshipBuilderDTO.
 *
 * @author nisanthan
 */
public class RelationshipBuilderDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -2317670574657124525L;
    /**
     * The creation date from.
     */
    private Date creationDateFrom;
    /**
     * The start date to.
     */
    private Date startDateTo;
    /**
     * The creation date to.
     */
    private Date creationDateTo;
    /**
     * The result rs name.
     */
    private String resultRSName;
    /**
     * The result rs desc.
     */
    private String resultRSDesc;
    /**
     * The result rs type.
     */
    private String resultRSType;
    /**
     * The result rs hierarchy name.
     */
    private String resultRSHierarchyName;
    /**
     * The result start date.
     */
    private String resultStartDate;
    /**
     * The result creation date.
     */
    private String resultCreationDate;
    /**
     * The result modified date.
     */
    private String resultModifiedDate;
    /**
     * The result created by.
     */
    private String resultCreatedBy;
    /**
     * The rb system id.
     */
    private int rbSystemId;
    /**
     * The relationship name.
     */
    private String relationshipName = StringUtils.EMPTY;
    /**
     * The relationship desc.
     */
    private String relationshipDesc = StringUtils.EMPTY;
    /**
     * The hierarchy name.
     */
    private String hierarchyName = StringUtils.EMPTY;
    /**
     * The hierarchy.
     */
    private String hierarchy = StringUtils.EMPTY;
    /**
     * The start date.
     */
    private Date startDate;
    /**
     * The relationship type.
     */
    private String relationshipType;
    /**
     * The created date.
     */
    private Date createdDate;
    /**
     * The modified date.
     */
    private Date modifiedDate;
    /**
     * The created by.
     */
    private int createdBy;
    /**
     * The modified by.
     */
    private int modifiedBy;
    /**
     * The hierarchy name ddlb.
     */
    private HelperDTO hierarchyNameDdlb;
    /**
     * The From start date.
     */
    private Date startDateFrom;
    /**
     * The Versioin No.
     */
    private int versionNo;
    
    
    /** The hierarchy version no. */
    private int hierarchyVersionNo;
    
    
    private String buildType;
    
    public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

    /**
     * Gets the version no.
     *
     * @return the version no
     */
    public int getVersionNo() {
        return versionNo;
    }

    /**
     * Sets the version no.
     *
     * @param versionNo the new version no
     */
    public void setVersionNo(final int versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * Gets the creation date from.
     *
     * @return the creation date from
     */
    public Date getCreationDateFrom() {
        return creationDateFrom;
    }

    /**
     * Sets the creation date from.
     *
     * @param creationDateFrom the new creation date from
     */
    public void setCreationDateFrom(final Date creationDateFrom) {
        this.creationDateFrom = creationDateFrom;
    }

    /**
     * Gets the start date to.
     *
     * @return the start date to
     */
    public Date getStartDateTo() {
        return startDateTo;
    }

    /**
     * Sets the start date to.
     *
     * @param startDateTo the new start date to
     */
    public void setStartDateTo(final Date startDateTo) {
        this.startDateTo = startDateTo;
    }

    /**
     * Gets the creation date to.
     *
     * @return the creation date to
     */
    public Date getCreationDateTo() {
        return creationDateTo;
    }

    /**
     * Sets the creation date to.
     *
     * @param creationDateTo the new creation date to
     */
    public void setCreationDateTo(final Date creationDateTo) {
        this.creationDateTo = creationDateTo;
    }

    /**
     * Gets the result rs name.
     *
     * @return the result rs name
     */
    public String getResultRSName() {
        return resultRSName;
    }

    /**
     * Sets the result rs name.
     *
     * @param resultRSName the new result rs name
     */
    public void setResultRSName(final String resultRSName) {
        this.resultRSName = resultRSName;
    }

    /**
     * Gets the result rs desc.
     *
     * @return the result rs desc
     */
    public String getResultRSDesc() {
        return resultRSDesc;
    }

    /**
     * Sets the result rs desc.
     *
     * @param resultRSDesc the new result rs desc
     */
    public void setResultRSDesc(final String resultRSDesc) {
        this.resultRSDesc = resultRSDesc;
    }

    /**
     * Gets the result rs type.
     *
     * @return the result rs type
     */
    public String getResultRSType() {
        return resultRSType;
    }

    /**
     * Sets the result rs type.
     *
     * @param resultRSType the new result rs type
     */
    public void setResultRSType(final String resultRSType) {
        this.resultRSType = resultRSType;
    }

    /**
     * Gets the result rs hierarchy name.
     *
     * @return the result rs hierarchy name
     */
    public String getResultRSHierarchyName() {
        return resultRSHierarchyName;
    }

    /**
     * Sets the result rs hierarchy name.
     *
     * @param resultRSHierarchyName the new result rs hierarchy name
     */
    public void setResultRSHierarchyName(final String resultRSHierarchyName) {
        this.resultRSHierarchyName = resultRSHierarchyName;
    }

    /**
     * Gets the result start date.
     *
     * @return the result start date
     */
    public String getResultStartDate() {
        return resultStartDate;
    }

    /**
     * Sets the result start date.
     *
     * @param resultStartDate the new result start date
     */
    public void setResultStartDate(final String resultStartDate) {
        this.resultStartDate = resultStartDate;
    }

    /**
     * Gets the result creation date.
     *
     * @return the result creation date
     */
    public String getResultCreationDate() {
        return resultCreationDate;
    }

    /**
     * Sets the result creation date.
     *
     * @param resultCreationDate the new result creation date
     */
    public void setResultCreationDate(final String resultCreationDate) {
        this.resultCreationDate = resultCreationDate;
    }

    /**
     * Gets the result modified date.
     *
     * @return the result modified date
     */
    public String getResultModifiedDate() {
        return resultModifiedDate;
    }

    /**
     * Sets the result modified date.
     *
     * @param resultModifiedDate the new result modified date
     */
    public void setResultModifiedDate(final String resultModifiedDate) {
        this.resultModifiedDate = resultModifiedDate;
    }

    /**
     * Gets the result created by.
     *
     * @return the result created by
     */
    public String getResultCreatedBy() {
        return resultCreatedBy;
    }

    /**
     * Sets the result created by.
     *
     * @param resultCreatedBy the new result created by
     */
    public void setResultCreatedBy(final String resultCreatedBy) {
        this.resultCreatedBy = resultCreatedBy;
    }

    /**
     * Gets the rb system id.
     *
     * @return the rb system id
     */
    public int getRbSystemId() {
        return rbSystemId;
    }

    /**
     * Sets the rb system id.
     *
     * @param rbSystemId the new rb system id
     */
    public void setRbSystemId(final int rbSystemId) {
        this.rbSystemId = rbSystemId;
    }

    /**
     * Gets the relationship name.
     *
     * @return the relationship name
     */
    public String getRelationshipName() {
        return relationshipName;
    }

    /**
     * Sets the relationship name.
     *
     * @param relationshipName the new relationship name
     */
    public void setRelationshipName(final String relationshipName) {
        this.relationshipName = relationshipName;
    }

    /**
     * Gets the relationship desc.
     *
     * @return the relationship desc
     */
    public String getRelationshipDesc() {
        return relationshipDesc;
    }

    /**
     * Sets the relationship desc.
     *
     * @param relationshipDesc the new relationship desc
     */
    public void setRelationshipDesc(final String relationshipDesc) {
        this.relationshipDesc = relationshipDesc;
    }

    /**
     * Gets the hierarchy name.
     *
     * @return the hierarchy name
     */
    public String getHierarchyName() {
        return hierarchyName;
    }

    /**
     * Sets the hierarchy name.
     *
     * @param hierarchyName the new hierarchy name
     */
    public void setHierarchyName(final String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    /**
     * Gets the hierarchy.
     *
     * @return the hierarchy
     */
    public String getHierarchy() {
        return hierarchy;
    }

    /**
     * Sets the hierarchy.
     *
     * @param hierarchy the new hierarchy
     */
    public void setHierarchy(final String hierarchy) {
        this.hierarchy = hierarchy;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the new start date
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the relationship type.
     *
     * @return the relationship type
     */
    public String getRelationshipType() {
        return relationshipType;
    }

    /**
     * Sets the relationship type.
     *
     * @param relationshipType the new relationship type
     */
    public void setRelationshipType(final String relationshipType) {
        this.relationshipType = relationshipType;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the new created date
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the new modified date
     */
    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public int getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the new created by
     */
    public void setCreatedBy(final int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the modified by.
     *
     * @return the modified by
     */
    public int getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the modified by.
     *
     * @param modifiedBy the new modified by
     */
    public void setModifiedBy(final int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Gets the hierarchy name ddlb.
     *
     * @return the hierarchy name ddlb
     */
    public HelperDTO getHierarchyNameDdlb() {
        return hierarchyNameDdlb;
    }

    /**
     * Sets the hierarchy name ddlb.
     *
     * @param hierarchyNameDdlb the new hierarchy name ddlb
     */
    public void setHierarchyNameDdlb(final HelperDTO hierarchyNameDdlb) {
        this.hierarchyNameDdlb = hierarchyNameDdlb;
    }

    /**
     * Gets the from start date.
     *
     * @return the start date from
     */
    public Date getStartDateFrom() {
        return startDateFrom;
    }

    /**
     * Sets the from start date.
     *
     * @param startDateFrom the from start date
     */
    public void setStartDateFrom(final Date startDateFrom) {
        this.startDateFrom = startDateFrom;
    }

    /**
     * Gets the hierarchy version no.
     *
     * @return the hierarchy version no
     */
    public int getHierarchyVersionNo() {
        return hierarchyVersionNo;
    }

    /**
     * Sets the hierarchy version no.
     *
     * @param hierarchyVersionNo the new hierarchy version no
     */
    public void setHierarchyVersionNo(final int hierarchyVersionNo) {
        this.hierarchyVersionNo = hierarchyVersionNo;
    }
    
    
}
