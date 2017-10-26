/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class CompanyTypeDdlbDTO {

    private String companyType = StringUtils.EMPTY;
    private int companySystemId;

    public CompanyTypeDdlbDTO() {
    }

    public CompanyTypeDdlbDTO(int companySystemId, String companyType) {
        this.companySystemId = companySystemId;
        this.companyType = companyType;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public int getCompanySystemId() {
        return companySystemId;
    }

    public void setCompanySystemId(int companySystemId) {
        this.companySystemId = companySystemId;
    }
}
