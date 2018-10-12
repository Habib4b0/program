/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.dataselection.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohamed.hameed
 */
public class CompanyDdlbDto {

    private String companyName = StringUtils.EMPTY;
    private int companyMasterSid;
    private String rsNo;
    private int rsModelSid;

    public CompanyDdlbDto(int rsModelSid, String rsNo) {
        this.rsNo = rsNo;
        this.rsModelSid = rsModelSid;
    }

    public CompanyDdlbDto() {
    }
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public String getRsNo() {
        return rsNo;
    }

    public void setRsNo(String rsNo) {
        this.rsNo = rsNo;
    }

    public int getRsModelSid() {
        return rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        this.rsModelSid = rsModelSid;
    }
}
