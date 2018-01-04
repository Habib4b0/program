/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class RebatePlanDTO implements Serializable {

    private String rebatePlanId = StringUtils.EMPTY;
    private String rebatePlanNo = StringUtils.EMPTY;
    private String rebatePlanName = StringUtils.EMPTY;
    private String rebatePlanStatus = StringUtils.EMPTY;
    private String rebatePlanType = StringUtils.EMPTY;

    public String getRebatePlanId() {
        return rebatePlanId;
    }

    public void setRebatePlanId(String rebatePlanId) {
        this.rebatePlanId = rebatePlanId;
    }

    public String getRebatePlanNo() {
        return rebatePlanNo;
    }

    public void setRebatePlanNo(String rebatePlanNo) {
        this.rebatePlanNo = rebatePlanNo;
    }

    public String getRebatePlanName() {
        return rebatePlanName;
    }

    public void setRebatePlanName(String rebatePlanName) {
        this.rebatePlanName = rebatePlanName;
    }

    public String getRebatePlanStatus() {
        return rebatePlanStatus;
    }

    public void setRebatePlanStatus(String rebatePlanStatus) {
        this.rebatePlanStatus = rebatePlanStatus;
    }

    public String getRebatePlanType() {
        return rebatePlanType;
    }

    public void setRebatePlanType(String rebatePlanType) {
        this.rebatePlanType = rebatePlanType;
    }
}
