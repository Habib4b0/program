/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.global.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 * The Class RsDeductionLookupDto.
 * @author vinodhini
 */
public class RsDeductionLookupDto implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -9084529327026132883L;

    private String deductionSystemId = StringUtils.EMPTY;
    private String deductionNo = StringUtils.EMPTY;
    private String deductionName = StringUtils.EMPTY;
    private String deductionDesc = StringUtils.EMPTY;
    private HelperDTO category;
    private Date creationDate;
    private String createdDate;
    private Date modifiedDate;
    private String modifyDate;
    private String createdBy = StringUtils.EMPTY;
    private String modifiedBy = StringUtils.EMPTY;
    private String deductionCategory = StringUtils.EMPTY;

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

    public String getDeductionDesc() {
        return deductionDesc;
    }

    public void setDeductionDesc(String deductionDesc) {
        this.deductionDesc = deductionDesc;
    }

    public HelperDTO getCategory() {
        return category;
    }

    public void setCategory(HelperDTO category) {
        this.category = category;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getDeductionSystemId() {
        return deductionSystemId;
    }

    public void setDeductionSystemId(String deductionSystemId) {
        this.deductionSystemId = deductionSystemId;
    }

    public String getDeductionCategory() {
        return deductionCategory;
    }

    public void setDeductionCategory(String deductionCategory) {
        this.deductionCategory = deductionCategory;
    }

}
