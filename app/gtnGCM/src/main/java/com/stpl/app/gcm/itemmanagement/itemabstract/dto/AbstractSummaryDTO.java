/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.dto;

import org.asi.container.ExtMapDTO;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohamed.hameed
 */
public class AbstractSummaryDTO extends ExtMapDTO {

    private String levelValue = StringUtils.EMPTY;
    private Integer parent;
    private String parentLevel = StringUtils.EMPTY;
    private Integer contractMasterSid;
    private Integer companyMasterSid;
    private Integer brandMasterSid;
    private Integer itemMasterSid;
    private Integer rebateProgramType;

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getParentLevel() {
        return parentLevel;
    }

    public void setParentLevel(String parentLevel) {
        this.parentLevel = parentLevel;
    }

    public Integer getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(Integer contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public Integer getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(Integer companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public Integer getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(Integer brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }

    public Integer getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(Integer itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public Integer getRebateProgramType() {
        return rebateProgramType;
    }

    public void setRebateProgramType(Integer rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }

}
