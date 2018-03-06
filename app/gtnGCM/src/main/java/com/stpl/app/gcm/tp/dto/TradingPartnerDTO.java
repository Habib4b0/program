/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author santanukumar
 */
public class TradingPartnerDTO implements Serializable {

    private String systemId = StringUtils.EMPTY;
    private String companyId = StringUtils.EMPTY;
    private String companyNo = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private String companyType = StringUtils.EMPTY;
    private String companyCategory = StringUtils.EMPTY;
    private String tradeClass = StringUtils.EMPTY;
    private String address1 = StringUtils.EMPTY;
    private String address2 = StringUtils.EMPTY;
    private String city = StringUtils.EMPTY;
    private String state = StringUtils.EMPTY;
    private String zip = StringUtils.EMPTY;
    private String parentNo = StringUtils.EMPTY;
    private String parentName = StringUtils.EMPTY;
    private String companyStatus = StringUtils.EMPTY;
    private String companyStartDate = StringUtils.EMPTY;
    private String companyEndDate = StringUtils.EMPTY;
    private String tradeClassStartDate = StringUtils.EMPTY;
    private String tradeClassEndDate = StringUtils.EMPTY;
    private String lives = StringUtils.EMPTY;
    private String companyGroup = StringUtils.EMPTY;
    private String organization = StringUtils.EMPTY;
    private String financialSystem = StringUtils.EMPTY;
    private String parentStartDate = StringUtils.EMPTY;
    private String parentEndDate = StringUtils.EMPTY;
    private String priorParentCompanyNo = StringUtils.EMPTY;
    private String priorParentStartDate = StringUtils.EMPTY;
    private String regionCode = StringUtils.EMPTY;
    private String udc1 = StringUtils.EMPTY;
    private String udc2 = StringUtils.EMPTY;
    private String udc3 = StringUtils.EMPTY;
    private String udc4 = StringUtils.EMPTY;
    private String udc5 = StringUtils.EMPTY;
    private String udc6 = StringUtils.EMPTY;
    private String companySystemId = StringUtils.EMPTY;
    private String identifier = StringUtils.EMPTY;
    private String identifierType = StringUtils.EMPTY;
    private boolean check = false;
    private boolean reset = false;
    private List<String> companyMasterSids = new ArrayList<>();
    private String companyRestrictionSessionId = StringUtils.EMPTY;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(String companyCategory) {
        this.companyCategory = companyCategory;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getCompanyStartDate() {
        return companyStartDate;
    }

    public void setCompanyStartDate(String companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    public String getCompanyEndDate() {
        return companyEndDate;
    }

    public void setCompanyEndDate(String companyEndDate) {
        this.companyEndDate = companyEndDate;
    }

    public String getTradeClassStartDate() {
        return tradeClassStartDate;
    }

    public void setTradeClassStartDate(String tradeClassStartDate) {
        this.tradeClassStartDate = tradeClassStartDate;
    }

    public String getTradeClassEndDate() {
        return tradeClassEndDate;
    }

    public void setTradeClassEndDate(String tradeClassEndDate) {
        this.tradeClassEndDate = tradeClassEndDate;
    }

    public String getLives() {
        return lives;
    }

    public void setLives(String lives) {
        this.lives = lives;
    }

    public String getCompanyGroup() {
        return companyGroup;
    }

    public void setCompanyGroup(String companyGroup) {
        this.companyGroup = companyGroup;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getFinancialSystem() {
        return financialSystem;
    }

    public void setFinancialSystem(String financialSystem) {
        this.financialSystem = financialSystem;
    }

    public String getParentStartDate() {
        return parentStartDate;
    }

    public void setParentStartDate(String parentStartDate) {
        this.parentStartDate = parentStartDate;
    }

    public String getParentEndDate() {
        return parentEndDate;
    }

    public void setParentEndDate(String parentEndDate) {
        this.parentEndDate = parentEndDate;
    }

    public String getPriorParentCompanyNo() {
        return priorParentCompanyNo;
    }

    public void setPriorParentCompanyNo(String priorParentCompanyNo) {
        this.priorParentCompanyNo = priorParentCompanyNo;
    }

    public String getPriorParentStartDate() {
        return priorParentStartDate;
    }

    public void setPriorParentStartDate(String priorParentStartDate) {
        this.priorParentStartDate = priorParentStartDate;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getUdc1() {
        return udc1;
    }

    public void setUdc1(String udc1) {
        this.udc1 = udc1;
    }

    public String getUdc2() {
        return udc2;
    }

    public void setUdc2(String udc2) {
        this.udc2 = udc2;
    }

    public String getUdc3() {
        return udc3;
    }

    public void setUdc3(String udc3) {
        this.udc3 = udc3;
    }

    public String getUdc4() {
        return udc4;
    }

    public void setUdc4(String udc4) {
        this.udc4 = udc4;
    }

    public String getUdc5() {
        return udc5;
    }

    public void setUdc5(String udc5) {
        this.udc5 = udc5;
    }

    public String getUdc6() {
        return udc6;
    }

    public void setUdc6(String udc6) {
        this.udc6 = udc6;
    }

    public String getCompanySystemId() {
        return companySystemId;
    }

    public void setCompanySystemId(String companySystemId) {
        this.companySystemId = companySystemId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public Boolean getReset() {
        return reset;
    }

    public void setReset(Boolean reset) {
        this.reset = reset;
    }

    public List<String> getCompanyMasterSids() {
        return companyMasterSids == null ? companyMasterSids : new ArrayList<>(companyMasterSids);
    }

    public void setCompanyMasterSids(List<String> companyMasterSids) {
        this.companyMasterSids = companyMasterSids == null ? companyMasterSids : new ArrayList<>(companyMasterSids);
    }

    public String getCompanyRestrictionSessionId() {
        return companyRestrictionSessionId;
    }

    public void setCompanyRestrictionSessionId(String companyRestrictionSessionId) {
        this.companyRestrictionSessionId = companyRestrictionSessionId;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    }
