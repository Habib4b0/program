/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Sibi
 */
public class RebatePlanDTO implements Serializable{
        
    private String rebatePlanSystemId;
    
    private String rebatePlanId = StringUtils.EMPTY;
    
    private String rebatePlanNo = StringUtils.EMPTY;
    
    private String rebatePlanName = StringUtils.EMPTY;
    
    private HelperDTO rebatePlanStatus = new HelperDTO(0, Constants.SELECT_ONE);
    
    private HelperDTO rebatePlanType = new HelperDTO(0, Constants.SELECT_ONE);
  
    private HelperDTO rebateStructure;
    
    private HelperDTO rangeBasedOn;
    
    private String netSalesFormula=StringUtils.EMPTY;
     
    private String netSalesRule=StringUtils.EMPTY;
    
    private HelperDTO rebateBasedOn;
    
    /**
     * The created date.
     */
    private Date createdDate;
    /**
     * The created by.
     */
    private String createdBy = StringUtils.EMPTY;
        
    /**
     * The created date.
     */
    private Date modifiedDate;
    /**
     * The created by.
     */
    private String modifiedBy = StringUtils.EMPTY;
    
    private String createdDateString;
    
    private String modifiedDateString;

    public String getRebatePlanSystemId() {
        return rebatePlanSystemId;
    }

    public void setRebatePlanSystemId(String rebatePlanSystemId) {
        this.rebatePlanSystemId = rebatePlanSystemId;
    }

    public String getRebatePlanId() {
        return rebatePlanId;
    }

    public void setRebatePlanId(String rebatePlanId) {
        this.rebatePlanId = rebatePlanId;
    }

    public String getRebatePlanNo() {
        return rebatePlanNo;
    }

    public void setRebatePlanNo(String rebatePlanNo) {
        this.rebatePlanNo = rebatePlanNo;
    }

    public String getRebatePlanName() {
        return rebatePlanName;
    }

    public void setRebatePlanName(String rebatePlanName) {
        this.rebatePlanName = rebatePlanName;
    }

    public HelperDTO getRebatePlanStatus() {
        return rebatePlanStatus;
    }

    public void setRebatePlanStatus(HelperDTO rebatePlanStatus) {
        this.rebatePlanStatus = rebatePlanStatus;
    }

    public HelperDTO getRebatePlanType() {
        return rebatePlanType;
    }

    public void setRebatePlanType(HelperDTO rebatePlanType) {
        this.rebatePlanType = rebatePlanType;
    }    

    public HelperDTO getRebateStructure() {
        return rebateStructure;
    }

    public void setRebateStructure(HelperDTO rebateStructure) {
        this.rebateStructure = rebateStructure;
    }

    public HelperDTO getRangeBasedOn() {
        return rangeBasedOn;
    }

    public void setRangeBasedOn(HelperDTO rangeBasedOn) {
        this.rangeBasedOn = rangeBasedOn;
    }

    public String getNetSalesFormula() {
        return netSalesFormula;
    }

    public void setNetSalesFormula(String netSalesFormula) {
        this.netSalesFormula = netSalesFormula;
    }

    public String getNetSalesRule() {
        return netSalesRule;
    }

    public void setNetSalesRule(String netSalesRule) {
        this.netSalesRule = netSalesRule;
    }

    public HelperDTO getRebateBasedOn() {
        return rebateBasedOn;
    }

    public void setRebateBasedOn(HelperDTO rebateBasedOn) {
        this.rebateBasedOn = rebateBasedOn;
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

    public String getCreatedDateString() {
        return createdDateString;
    }

    public void setCreatedDateString(String createdDateString) {
        this.createdDateString = createdDateString;
    }

    public String getModifiedDateString() {
        return modifiedDateString;
    }

    public void setModifiedDateString(String modifiedDateString) {
        this.modifiedDateString = modifiedDateString;
    }
    
}
