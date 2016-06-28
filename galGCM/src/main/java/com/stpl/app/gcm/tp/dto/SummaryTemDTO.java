/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.dto;

import java.util.Date;

/**
 *
 * @author Maheshj
 */
public class SummaryTemDTO {

    int contractSid = 0;
    int companySid = 0;
    int month = 0;
    int year = 0;
    int projectionDetSid = 0;
    Date companyStartDate = null;
    Date companyEndDate = null;

    public int getContractSid() {
        return contractSid;
    }

    public void setContractSid(int contractSid) {
        this.contractSid = contractSid;
    }

    public int getCompanySid() {
        return companySid;
    }

    public void setCompanySid(int companySid) {
        this.companySid = companySid;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getProjectionDetSid() {
        return projectionDetSid;
    }

    public void setProjectionDetSid(int projectionDetSid) {
        this.projectionDetSid = projectionDetSid;
    }

    public Date getCompanyStartDate() {
        return companyStartDate;
    }

    public void setCompanyStartDate(Date companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    public Date getCompanyEndDate() {
        return companyEndDate;
    }

    public void setCompanyEndDate(Date companyEndDate) {
        this.companyEndDate = companyEndDate;
    }

}
