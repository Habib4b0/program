/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class PromoteTpToChDto implements Serializable {

    private String process = StringUtils.EMPTY;
    private String updateType = StringUtils.EMPTY;
    private String customer = StringUtils.EMPTY;
    private String mode = StringUtils.EMPTY;
    private String companyId = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private String companyCategory = StringUtils.EMPTY;
    private String identifierType = StringUtils.EMPTY;
    private String companyType = StringUtils.EMPTY;
    private String companyNo = StringUtils.EMPTY;
    private String tradeClass = StringUtils.EMPTY;
    private String identifier = StringUtils.EMPTY;
    private String companySystemId = StringUtils.EMPTY;
    private String address1 = StringUtils.EMPTY;
    private String address2 = StringUtils.EMPTY;
    private String city = StringUtils.EMPTY;
    private String state = StringUtils.EMPTY;
    private String zip = StringUtils.EMPTY;
    private Boolean reset = false;
    private HelperDTO tradeClass_DTO;
    public Boolean getReset() {
        return reset;
    }

    public void setReset(Boolean reset) {
        this.reset = reset;
    }

    public String getCompanySystemId() {
        return companySystemId;
    }

    public void setCompanySystemId(String companySystemId) {
        this.companySystemId = companySystemId;
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

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public HelperDTO getTradeClass_DTO() {
        return tradeClass_DTO;
    }

    public void setTradeClass_DTO(HelperDTO tradeClass_DTO) {
        this.tradeClass_DTO = tradeClass_DTO;
    }
    
}
