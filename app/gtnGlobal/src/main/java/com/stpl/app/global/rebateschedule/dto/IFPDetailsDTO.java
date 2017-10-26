/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.rebateschedule.dto;

import java.util.Date;

public class IFPDetailsDTO {

    private String ifpNo;
    private String ifpName;
    private String ifpType;
    private Date ifpStartDate;
    private Date ifpEndDate;
    private int itemFamilyplanSystemId;
    
    private String ifpId;
    private String ifpStatus;
    private String ifpCategory;

    public String getIfpId() {
        return ifpId;
    }

    public void setIfpId(String ifpId) {
        this.ifpId = ifpId;
    }

    public String getIfpStatus() {
        return ifpStatus;
    }

    public void setIfpStatus(String ifpStatus) {
        this.ifpStatus = ifpStatus;
    }

    public String getIfpCategory() {
        return ifpCategory;
    }

    public void setIfpCategory(String ifpCategory) {
        this.ifpCategory = ifpCategory;
    }

    
    
    public String getIfpNo() {
        return ifpNo;
    }

    public void setIfpNo(String ifpNo) {
        this.ifpNo = ifpNo;
    }

    public String getIfpName() {
        return ifpName;
    }

    public void setIfpName(String ifpName) {
        this.ifpName = ifpName;
    }

    public String getIfpType() {
        return ifpType;
    }

    public void setIfpType(String ifpType) {
        this.ifpType = ifpType;
    }

    public Date getIfpStartDate() {
        return ifpStartDate;
    }

    public void setIfpStartDate(Date ifpStartDate) {
        this.ifpStartDate = ifpStartDate;
    }

    public Date getIfpEndDate() {
        return ifpEndDate;
    }

    public void setIfpEndDate(Date ifpEndDate) {
        this.ifpEndDate = ifpEndDate;
    }

    public int getItemFamilyplanSystemId() {
        return itemFamilyplanSystemId;
    }

    public void setItemFamilyplanSystemId(int itemFamilyplanSystemId) {
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
    }

    
}
