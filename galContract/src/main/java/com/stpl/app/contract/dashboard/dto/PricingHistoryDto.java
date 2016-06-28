/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Asha
 */
public class PricingHistoryDto implements Serializable {
    
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1096846833899176373L;
    /**
     * The priceScheduleId
     */
    private String priceScheduleId = StringUtils.EMPTY;
    /**
     * The priceScheduleNo
     */
    private String priceScheduleNo = StringUtils.EMPTY;
    /**
     * The priceScheduleName
     */
    private String priceScheduleName = StringUtils.EMPTY;
    /**
     * The priceScheduleStatus
     */
    private String priceScheduleStatus = StringUtils.EMPTY;
    
    /**
     * The priceScheduleStartDate
     */
    private String priceScheduleStartDate = StringUtils.EMPTY;
    
    /**
     * The priceScheduleEndDate
     */
    private String priceScheduleEndDate = StringUtils.EMPTY;
    
    /**
     * The priceScheduleDesignation
     */
    private String priceScheduleDesignation = StringUtils.EMPTY;
    
    /**
     * The parentPriceScheduleId
     */
    private String parentPriceScheduleId = StringUtils.EMPTY;
    
    /**
     * The parentPriceScheduleName
     */
    private String parentPriceScheduleName = StringUtils.EMPTY;
    
    /**
     * The priceScheduleType
     */
    private String priceScheduleType = StringUtils.EMPTY;
    
    /**
     * The createdBy
     */
    private String createdBy = StringUtils.EMPTY;
    
    /**
     * The createdDate
     */
    private String createdDate = StringUtils.EMPTY;
    
    /**
     * The priceScheduleCategory
     */
    private String priceScheduleCategory = StringUtils.EMPTY;
    
    /**
     * The modifiedBy
     */
    private String modifiedBy = StringUtils.EMPTY;
    
    /**
     * The modifiedDate
     */
    private String modifiedDate = StringUtils.EMPTY;
    
    /**
     * The priceScheduleTradeClass
     */
    private String priceScheduleTradeClass = StringUtils.EMPTY;

    public String getPriceScheduleId() {
        return priceScheduleId;
    }

    public void setPriceScheduleId(String priceScheduleId) {
        this.priceScheduleId = priceScheduleId;
    }

    public String getPriceScheduleNo() {
        return priceScheduleNo;
    }

    public void setPriceScheduleNo(String priceScheduleNo) {
        this.priceScheduleNo = priceScheduleNo;
    }

    public String getPriceScheduleName() {
        return priceScheduleName;
    }

    public void setPriceScheduleName(String priceScheduleName) {
        this.priceScheduleName = priceScheduleName;
    }

    public String getPriceScheduleStatus() {
        return priceScheduleStatus;
    }

    public void setPriceScheduleStatus(String priceScheduleStatus) {
        this.priceScheduleStatus = priceScheduleStatus;
    }

    public String getPriceScheduleStartDate() {
        return priceScheduleStartDate;
    }

    public void setPriceScheduleStartDate(String priceScheduleStartDate) {
        this.priceScheduleStartDate = priceScheduleStartDate;
    }

    public String getPriceScheduleEndDate() {
        return priceScheduleEndDate;
    }

    public void setPriceScheduleEndDate(String priceScheduleEndDate) {
        this.priceScheduleEndDate = priceScheduleEndDate;
    }

    public String getPriceScheduleDesignation() {
        return priceScheduleDesignation;
    }

    public void setPriceScheduleDesignation(String priceScheduleDesignation) {
        this.priceScheduleDesignation = priceScheduleDesignation;
    }

    public String getParentPriceScheduleId() {
        return parentPriceScheduleId;
    }

    public void setParentPriceScheduleId(String parentPriceScheduleId) {
        this.parentPriceScheduleId = parentPriceScheduleId;
    }

    public String getParentPriceScheduleName() {
        return parentPriceScheduleName;
    }

    public void setParentPriceScheduleName(String parentPriceScheduleName) {
        this.parentPriceScheduleName = parentPriceScheduleName;
    }

    public String getPriceScheduleType() {
        return priceScheduleType;
    }

    public void setPriceScheduleType(String priceScheduleType) {
        this.priceScheduleType = priceScheduleType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPriceScheduleCategory() {
        return priceScheduleCategory;
    }

    public void setPriceScheduleCategory(String priceScheduleCategory) {
        this.priceScheduleCategory = priceScheduleCategory;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getPriceScheduleTradeClass() {
        return priceScheduleTradeClass;
    }

    public void setPriceScheduleTradeClass(String priceScheduleTradeClass) {
        this.priceScheduleTradeClass = priceScheduleTradeClass;
    }
    
    
    
}
