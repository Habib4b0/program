/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Harlin
 */
public class TransferFromDTO {

    private String category = StringUtils.EMPTY;
    private String id = StringUtils.EMPTY;
    private String number = StringUtils.EMPTY;
    private String name = StringUtils.EMPTY;
    private String contractSid = StringUtils.EMPTY;
    private String cfpContractSid = StringUtils.EMPTY;
    private String ifpContractSid = StringUtils.EMPTY;
    private String psContractSid = StringUtils.EMPTY;
    private String rsContractSid = StringUtils.EMPTY;
    private Integer level;

    public TransferFromDTO() {
        super();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getContractSid() {
        return contractSid;
    }

    public void setContractSid(String contractSid) {
        this.contractSid = contractSid;
    }

    public String getCfpContractSid() {
        return cfpContractSid;
    }

    public void setCfpContractSid(String cfpContractSid) {
        this.cfpContractSid = cfpContractSid;
    }

    public String getIfpContractSid() {
        return ifpContractSid;
    }

    public void setIfpContractSid(String ifpContractSid) {
        this.ifpContractSid = ifpContractSid;
    }

    public String getPsContractSid() {
        return psContractSid;
    }

    public void setPsContractSid(String psContractSid) {
        this.psContractSid = psContractSid;
    }

    public String getRsContractSid() {
        return rsContractSid;
    }

    public void setRsContractSid(String rsContractSid) {
        this.rsContractSid = rsContractSid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

}
