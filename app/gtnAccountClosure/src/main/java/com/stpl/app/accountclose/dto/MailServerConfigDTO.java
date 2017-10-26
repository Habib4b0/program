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
public class MailServerConfigDTO {

    private String smtp = StringUtils.EMPTY;
    private String hostName = StringUtils.EMPTY;
    private String emailAddr = StringUtils.EMPTY;
    private String password = StringUtils.EMPTY;
    private String portNo = StringUtils.EMPTY;
    private String testMailAddr = StringUtils.EMPTY;

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortNo() {
        return portNo;
    }

    public void setPortNo(String portNo) {
        this.portNo = portNo;
    }

    public String getTestMailAddr() {
        return testMailAddr;
    }

    public void setTestMailAddr(String testMailAddr) {
        this.testMailAddr = testMailAddr;
    }
}
