/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class CompanyDdlbDTO {
     private String companyName = StringUtils.EMPTY;
    private int companyMasterSid;

    public CompanyDdlbDTO() {
        
    }
    
    public CompanyDdlbDTO(int companyMasterSid, String companyName) {
        this.companyMasterSid = companyMasterSid;
        this.companyName = companyName;
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

}
