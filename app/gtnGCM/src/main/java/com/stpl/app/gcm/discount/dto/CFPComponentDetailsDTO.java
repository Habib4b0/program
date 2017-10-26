/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author santanu
 */
public class CFPComponentDetailsDTO {

    private String companyNo = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private String tpContractNo = StringUtils.EMPTY;
    private String startDate = StringUtils.EMPTY;
    
    private String endDate = StringUtils.EMPTY;
    
    private String status = StringUtils.EMPTY;

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTpContractNo() {
        return tpContractNo;
    }

    public void setTpContractNo(String tpContractNo) {
        this.tpContractNo = tpContractNo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
