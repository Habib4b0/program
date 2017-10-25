/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.serviceUtils.Constants;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import com.stpl.ifs.util.HelperDTO;

/**
 *
 * @author Asha
 */
public class PriceScheduleDto {
    
     /** The priceScheduleId
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
    private HelperDTO priceScheduleStatus  = new HelperDTO(0, Constants.SELECT_ONE);
    
     /**
     * The priceScheduleStartDate
     */
    private Date priceScheduleStartDate;
    
     /**
     * The priceScheduleEndDate
     */
    private Date priceScheduleEndDate;
    
     /**
     * The priceScheduleDesignation
     */
    private  HelperDTO priceScheduleDesignation  = new HelperDTO(0, Constants.SELECT_ONE);
    
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
    private HelperDTO priceScheduleType  = new HelperDTO(0, Constants.SELECT_ONE);
    
     /**
     * The priceScheduleCategory
     */
    private HelperDTO priceScheduleCategory  = new HelperDTO(0, Constants.SELECT_ONE);
    
     /**
     * The tradeClass
     */
    private HelperDTO tradeClass = new HelperDTO(0, Constants.SELECT_ONE);
    
     /**
     * The parentPriceScheduleName
     */
    private String priceScheduleSystemId = StringUtils.EMPTY;
    
    /**
     * Modified Date Field
     */
    private Date modifiedDate;
    
     /**
     * Created Date Field
     */
    private Date createdDate;
    /**
     * Created By Field for user Id
     */
    private String createdBy;
    
    /**
     * ModifiedBy Filed to store User Id
     */
    private String modifiedBy;

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

   
    public Date getPriceScheduleStartDate() {
        return priceScheduleStartDate;
    }

    public void setPriceScheduleStartDate(Date priceScheduleStartDate) {
        this.priceScheduleStartDate = priceScheduleStartDate;
    }

    public Date getPriceScheduleEndDate() {
        return priceScheduleEndDate;
    }

    public void setPriceScheduleEndDate(Date priceScheduleEndDate) {
        this.priceScheduleEndDate = priceScheduleEndDate;
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

    public HelperDTO getPriceScheduleStatus() {
        return priceScheduleStatus;
    }

    public void setPriceScheduleStatus(HelperDTO priceScheduleStatus) {
        this.priceScheduleStatus = priceScheduleStatus;
    }

    public HelperDTO getPriceScheduleType() {
        return priceScheduleType;
    }

    public void setPriceScheduleType(HelperDTO priceScheduleType) {
        this.priceScheduleType = priceScheduleType;
    }

    public HelperDTO getPriceScheduleCategory() {
        return priceScheduleCategory;
    }

    public void setPriceScheduleCategory(HelperDTO priceScheduleCategory) {
        this.priceScheduleCategory = priceScheduleCategory;
    }

    public HelperDTO getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(HelperDTO tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    public void setPriceScheduleSystemId(String priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public HelperDTO getPriceScheduleDesignation() {
        return priceScheduleDesignation;
    }

    public void setPriceScheduleDesignation(HelperDTO priceScheduleDesignation) {
        this.priceScheduleDesignation = priceScheduleDesignation;
    }

   
    
    
    
}
