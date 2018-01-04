/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.add.dto;

import org.asi.container.ExtMapDTO;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author porchelvi.g
 */
public class ContractDashboardDTO extends ExtMapDTO {

    private String component = StringUtils.EMPTY;
    private String id = StringUtils.EMPTY;
    private String number = StringUtils.EMPTY;
    private String name = StringUtils.EMPTY;
    private String contractSid = StringUtils.EMPTY;
    private String cfpSid = StringUtils.EMPTY;
    private String ifpSid = StringUtils.EMPTY;
    private String psSid = StringUtils.EMPTY;
    private String rsSid = StringUtils.EMPTY;
    private String masterSid = StringUtils.EMPTY;
    private Integer levelNo = 0;
    private String levelValue = StringUtils.EMPTY;
    private Integer parent = 0;

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getContractSid() {
        return contractSid;
    }

    public void setContractSid(String contractSid) {
        this.contractSid = contractSid;
    }

    public String getCfpSid() {
        return cfpSid;
    }

    public void setCfpSid(String cfpSid) {
        this.cfpSid = cfpSid;
    }

    public String getIfpSid() {
        return ifpSid;
    }

    public void setIfpSid(String ifpSid) {
        this.ifpSid = ifpSid;
    }

    public String getPsSid() {
        return psSid;
    }

    public void setPsSid(String psSid) {
        this.psSid = psSid;
    }

    public String getRsSid() {
        return rsSid;
    }

    public void setRsSid(String rsSid) {
        this.rsSid = rsSid;
    }

    public String getMasterSid() {
        return masterSid;
    }

    public void setMasterSid(String masterSid) {
        this.masterSid = masterSid;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }
}
