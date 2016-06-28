/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.mailconfiguration.dto;

import com.stpl.ifs.util.HelperUtils;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author manikandaprabu.g
 */
public class MailConfigurationDTO implements Serializable{
    private String usesmtp;
    private String inboundstatus;
    private String createdBy = HelperUtils.EMPTY;
    private String modifiedBy = HelperUtils.EMPTY;
   

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
    
    public String getInboundstatus() {
        return inboundstatus;
    }

    public void setInboundstatus(String inboundstatus) {
        this.inboundstatus = inboundstatus;
    }

    public String getUsesmtp() {
        return usesmtp;
    }

    public void setUsesmtp(String usesmtp) {
        this.usesmtp = usesmtp;
    }

    private String hostname=StringUtils.EMPTY;
    private String email=StringUtils.EMPTY;
    private String password=StringUtils.EMPTY;
    private String portno=StringUtils.EMPTY;
    private String testmailadd=StringUtils.EMPTY;


 
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortno() {
        return portno;
    }

    public void setPortno(String portno) {
        this.portno = portno;
    }

    public String getTestmailadd() {
        return testmailadd;
    }

    public void setTestmailadd(String testmailadd) {
        this.testmailadd = testmailadd;
    }
    
    
    
}
