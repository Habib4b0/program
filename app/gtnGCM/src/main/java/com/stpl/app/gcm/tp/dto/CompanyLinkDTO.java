/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Sriram
 */
public class CompanyLinkDTO {

    private String fromCompanyMasterSid = StringUtils.EMPTY;
    private String fromCompanyId = StringUtils.EMPTY;
    private String fromCompanyNo = StringUtils.EMPTY;
    private String fromCompanyName = StringUtils.EMPTY;
    private String toCompanyMasterSid = StringUtils.EMPTY;
    private String toCompanyId = StringUtils.EMPTY;
    private String toCompanyNo = StringUtils.EMPTY;
    private String toCompanyName = StringUtils.EMPTY;
    private Boolean check = false;

    public String getFromCompanyId() {
        return fromCompanyId;
    }

    public void setFromCompanyId(String fromCompanyId) {
        this.fromCompanyId = fromCompanyId;
    }

    public String getFromCompanyNo() {
        return fromCompanyNo;
    }

    public void setFromCompanyNo(String fromCompanyNo) {
        this.fromCompanyNo = fromCompanyNo;
    }

    public String getFromCompanyName() {
        return fromCompanyName;
    }

    public void setFromCompanyName(String fromCompanyName) {
        this.fromCompanyName = fromCompanyName;
    }

    public String getToCompanyId() {
        return toCompanyId;
    }

    public void setToCompanyId(String toCompanyId) {
        this.toCompanyId = toCompanyId;
    }

    public String getToCompanyNo() {
        return toCompanyNo;
    }

    public void setToCompanyNo(String toCompanyNo) {
        this.toCompanyNo = toCompanyNo;
    }

    public String getToCompanyName() {
        return toCompanyName;
    }

    public void setToCompanyName(String toCompanyName) {
        this.toCompanyName = toCompanyName;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getFromCompanyMasterSid() {
        return fromCompanyMasterSid;
    }

    public void setFromCompanyMasterSid(String fromCompanyMasterSid) {
        this.fromCompanyMasterSid = fromCompanyMasterSid;
    }

    public String getToCompanyMasterSid() {
        return toCompanyMasterSid;
    }

    public void setToCompanyMasterSid(String toCompanyMasterSid) {
        this.toCompanyMasterSid = toCompanyMasterSid;
    }

}
