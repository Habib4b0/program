/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author soundarrajan.l
 */
public class CompanyDdlbDto {
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDdlbDto.class);
    
    private String companyName = StringUtils.EMPTY;
    private int companyMasterSid;
  private String rsNo;
    private int rsModelSid;
    
     public CompanyDdlbDto(int rsModelSid, String rsNo,boolean flag) {
         this.rsNo = rsNo;
         this.rsModelSid = rsModelSid;
    }
    public CompanyDdlbDto() {
        
    }
    
    public CompanyDdlbDto(int companyMasterSid, String companyName) {
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
