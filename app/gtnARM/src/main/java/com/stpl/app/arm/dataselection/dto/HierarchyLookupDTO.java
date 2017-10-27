/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sathyaseelan
 */
public class HierarchyLookupDTO {

    public HierarchyLookupDTO() {
        /*

        EMPTY CONSTRUTOR
         */
    }

    private String hierarchyName;
    private String highestLevel;
    private String lowestLevel;
    private String createdDate;
    private String modifiedDate;
    private int hierarchyId = 0;
    private int versionNo = 0;
    private String hierarchySearchType = StringUtils.EMPTY;
    private String hierarchySearchName = StringUtils.EMPTY;
    private String lookupSearchName = StringUtils.EMPTY;

    public String getHierarchyName() {
        return hierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    public String getHighestLevel() {
        return highestLevel;
    }

    public void setHighestLevel(String highestLevel) {
        this.highestLevel = highestLevel;
    }

    public String getLowestLevel() {
        return lowestLevel;
    }

    public void setLowestLevel(String lowestLevel) {
        this.lowestLevel = lowestLevel;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(int hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public String getHierarchySearchType() {
        return hierarchySearchType;
    }

    public void setHierarchySearchType(String hierarchySearchType) {
        this.hierarchySearchType = hierarchySearchType;
    }

    public String getHierarchySearchName() {
        return hierarchySearchName;
    }

    public void setHierarchySearchName(String hierarchySearchName) {
        this.hierarchySearchName = hierarchySearchName;
    }

    public String getLookupSearchName() {
        return lookupSearchName;
    }

    public void setLookupSearchName(String lookupSearchName) {
        this.lookupSearchName = lookupSearchName;
    }

}
