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

    private int contractSid = 0;
    private int companySid = 0;
    private int month = 0;
    private int year = 0;
    private int projectionDetSid = 0;
    private Date companyStartDate = null;
    private Date companyEndDate = null;

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
        return companyStartDate == null ? null : (Date) companyStartDate.clone();
    }

    public void setCompanyStartDate(Date companyStartDate) {
        this.companyStartDate = companyStartDate == null ? null : (Date) companyStartDate.clone();
    }

    public Date getCompanyEndDate() {
        return companyEndDate == null ? null : (Date) companyEndDate.clone();
    }

    public void setCompanyEndDate(Date companyEndDate) {
        this.companyEndDate = companyEndDate == null ? null : (Date) companyEndDate.clone();
    }

}
