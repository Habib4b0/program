/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Asha
 */
public class NetSalesFormulaDTO implements Serializable {
    
    private HelperDTO netSalesFormulaType;
    private String netSalesFormulaId = StringUtils.EMPTY;
    private String netSalesFormulaNo = StringUtils.EMPTY;
    private String netSalesFormulaName = StringUtils.EMPTY;
    private String nsfcreateDate= StringUtils.EMPTY;
    private String nsfcreatedBy = StringUtils.EMPTY;
    private Date nsfmodifiedDate;
    private Date nsfcreatedDate;
    private String nsfmodifyDate= StringUtils.EMPTY;
    private String  nsfmodifiedBy = StringUtils.EMPTY;
    private String systemID = StringUtils.EMPTY;

    public HelperDTO getNetSalesFormulaType() {
        return netSalesFormulaType;
    }

    public void setNetSalesFormulaType(HelperDTO netSalesFormulaType) {
        this.netSalesFormulaType = netSalesFormulaType;
    }

    public String getNetSalesFormulaId() {
        return netSalesFormulaId;
    }

    public void setNetSalesFormulaId(String netSalesFormulaId) {
        this.netSalesFormulaId = netSalesFormulaId;
    }

    public String getNetSalesFormulaNo() {
        return netSalesFormulaNo;
    }

    public void setNetSalesFormulaNo(String netSalesFormulaNo) {
        this.netSalesFormulaNo = netSalesFormulaNo;
    }

    public String getNetSalesFormulaName() {
        return netSalesFormulaName;
    }

    public void setNetSalesFormulaName(String netSalesFormulaName) {
        this.netSalesFormulaName = netSalesFormulaName;
    }

    public String getNsfcreateDate() {
        return nsfcreateDate;
    }

    public void setNsfcreateDate(String nsfcreateDate) {
        this.nsfcreateDate = nsfcreateDate;
    }

    public String getNsfcreatedBy() {
        return nsfcreatedBy;
    }

    public void setNsfcreatedBy(String nsfcreatedBy) {
        this.nsfcreatedBy = nsfcreatedBy;
    }

    public Date getNsfmodifiedDate() {
        return nsfmodifiedDate;
    }

    public void setNsfmodifiedDate(Date nsfmodifiedDate) {
        this.nsfmodifiedDate = nsfmodifiedDate;
    }

    public String getNsfmodifyDate() {
        return nsfmodifyDate;
    }

    public void setNsfmodifyDate(String nsfmodifyDate) {
        this.nsfmodifyDate = nsfmodifyDate;
    }

    public String getNsfmodifiedBy() {
        return nsfmodifiedBy;
    }

    public void setNsfmodifiedBy(String nsfmodifiedBy) {
        this.nsfmodifiedBy = nsfmodifiedBy;
    }

    public Date getNsfcreatedDate() {
        return nsfcreatedDate;
    }

    public void setNsfcreatedDate(Date nsfcreatedDate) {
        this.nsfcreatedDate = nsfcreatedDate;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    }
