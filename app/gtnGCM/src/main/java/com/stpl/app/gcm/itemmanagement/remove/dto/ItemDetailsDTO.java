/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.remove.dto;

import org.drools.core.util.StringUtils;

/**
 * private String rarcategory  private String status 
 *
 *
 * @author mohamed.hameed
 */
public class ItemDetailsDTO {

    private String contractholder = StringUtils.EMPTY;
    private String contractno = StringUtils.EMPTY;
    private String contractname = StringUtils.EMPTY;
    private String markettype = StringUtils.EMPTY;
    private String startdate = StringUtils.EMPTY;
    private String enddate = StringUtils.EMPTY;
    private String cfpname = StringUtils.EMPTY;
    private String ifpname = StringUtils.EMPTY;
    private String psname = StringUtils.EMPTY;
    private String rsname =StringUtils.EMPTY;
    private String rarcategory = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private String itemstartdate = StringUtils.EMPTY;
    private String itemenddate =StringUtils.EMPTY;

    public String getContractholder() {
        return contractholder;
    }

    public void setContractholder(String contractholder) {
        this.contractholder = contractholder;
    }

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno;
    }

    public String getContractname() {
        return contractname;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public String getMarkettype() {
        return markettype;
    }

    public void setMarkettype(String markettype) {
        this.markettype = markettype;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getCfpname() {
        return cfpname;
    }

    public void setCfpname(String cfpname) {
        this.cfpname = cfpname;
    }

    public String getIfpname() {
        return ifpname;
    }

    public void setIfpname(String ifpname) {
        this.ifpname = ifpname;
    }

    public String getPsname() {
        return psname;
    }

    public void setPsname(String psname) {
        this.psname = psname;
    }

    public String getRsname() {
        return rsname;
    }

    public void setRsname(String rsname) {
        this.rsname = rsname;
    }

    public String getRarcategory() {
        return rarcategory;
    }

    public void setRarcategory(String rarcategory) {
        this.rarcategory = rarcategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemstartdate() {
        return itemstartdate;
    }

    public void setItemstartdate(String itemstartdate) {
        this.itemstartdate = itemstartdate;
    }

    public String getItemenddate() {
        return itemenddate;
    }

    public void setItemenddate(String itemenddate) {
        this.itemenddate = itemenddate;
    }

}
