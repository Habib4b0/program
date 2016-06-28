/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * The Class CustomerDetailsDTO.
 *
 * @author vishalakshi
 */
public class CustomerDetailsDTO implements Serializable {

    private static final long serialVersionUID = 7647329711079909681L;

    private String organizationKey = StringUtils.EMPTY;

    private String customerId = StringUtils.EMPTY;

    private String customerNo = StringUtils.EMPTY;

    private String customerName = StringUtils.EMPTY;

    private String tradeClass = StringUtils.EMPTY;

    private Date tradeClassStartDate;

    private Date tradeClassEndDate;

    private String customerType = StringUtils.EMPTY;

    private String customerStatus = StringUtils.EMPTY;

    private String lives = StringUtils.EMPTY;

    private Date customerEndDate;

    private String udc1 = StringUtils.EMPTY;

    private String udc2 = StringUtils.EMPTY;

    private String udc3 = StringUtils.EMPTY;

    private String udc4 = StringUtils.EMPTY;

    private String udc5 = StringUtils.EMPTY;

    private String udc6 = StringUtils.EMPTY;

    private String customerGroup = StringUtils.EMPTY;

    private String financialSystem = StringUtils.EMPTY;

    private String address1 = StringUtils.EMPTY;

    private String address2 = StringUtils.EMPTY;

    private String city = StringUtils.EMPTY;

    private String state = StringUtils.EMPTY;

    private String zipCode = StringUtils.EMPTY;

    private String country = StringUtils.EMPTY;

    private String regionCode = StringUtils.EMPTY;

    private String parentCustomerNo = StringUtils.EMPTY;

    private Date parentStartDate;

    private Date parentEndDate;

    private Date customerStartDate;

    private Date priorParentStartDate;

    private String priorParentCustomerNo = StringUtils.EMPTY;

    private int companySystemId;

    private Date createdDate;

    private Date modifiedDate;

    private int createdBy;

    private int modifiedBy;

    private int tradeClassSysId;

    private int parentSysId;

    public String getOrganizationKey() {
        return organizationKey;
    }

    public void setOrganizationKey(final String organizationKey) {
        this.organizationKey = organizationKey;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(final String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(final String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public Date getTradeClassStartDate() {
        return tradeClassStartDate;
    }

    public void setTradeClassStartDate(final Date tradeClassStartDate) {
        this.tradeClassStartDate = tradeClassStartDate;
    }

    public Date getTradeClassEndDate() {
        return tradeClassEndDate;
    }

    public void setTradeClassEndDate(final Date tradeClassEndDate) {
        this.tradeClassEndDate = tradeClassEndDate;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(final String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(final String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getLives() {
        return lives;
    }

    public void setLives(final String lives) {
        this.lives = lives;
    }

    public Date getCustomerEndDate() {
        return customerEndDate;
    }

    public void setCustomerEndDate(final Date customerEndDate) {
        this.customerEndDate = customerEndDate;
    }

    public String getUdc1() {
        return udc1;
    }

    public void setUdc1(final String udc1) {
        this.udc1 = udc1;
    }

    public String getUdc2() {
        return udc2;
    }

    public void setUdc2(final String udc2) {
        this.udc2 = udc2;
    }

    public String getUdc3() {
        return udc3;
    }

    public void setUdc3(final String udc3) {
        this.udc3 = udc3;
    }

    public String getUdc4() {
        return udc4;
    }

    public void setUdc4(final String udc4) {
        this.udc4 = udc4;
    }

    public String getUdc5() {
        return udc5;
    }

    public void setUdc5(final String udc5) {
        this.udc5 = udc5;
    }

    public String getUdc6() {
        return udc6;
    }

    public void setUdc6(final String udc6) {
        this.udc6 = udc6;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(final String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public String getFinancialSystem() {
        return financialSystem;
    }

    public void setFinancialSystem(final String financialSystem) {
        this.financialSystem = financialSystem;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(final String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(final String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(final String regionCode) {
        this.regionCode = regionCode;
    }

    public String getParentCustomerNo() {
        return parentCustomerNo;
    }

    public void setParentCustomerNo(final String parentCustomerNo) {
        this.parentCustomerNo = parentCustomerNo;
    }

    public Date getParentStartDate() {
        return parentStartDate;
    }

    public void setParentStartDate(final Date parentStartDate) {
        this.parentStartDate = parentStartDate;
    }

    public Date getParentEndDate() {
        return parentEndDate;
    }

    public void setParentEndDate(final Date parentEndDate) {
        this.parentEndDate = parentEndDate;
    }

    public Date getCustomerStartDate() {
        return customerStartDate;
    }

    public void setCustomerStartDate(final Date customerStartDate) {
        this.customerStartDate = customerStartDate;
    }

    public Date getPriorParentStartDate() {
        return priorParentStartDate;
    }

    public void setPriorParentStartDate(final Date priorParentStartDate) {
        this.priorParentStartDate = priorParentStartDate;
    }

    public String getPriorParentCustomerNo() {
        return priorParentCustomerNo;
    }

    public void setPriorParentCustomerNo(final String priorParentCustomerNo) {
        this.priorParentCustomerNo = priorParentCustomerNo;
    }

    public int getCompanySystemId() {
        return companySystemId;
    }

    public void setCompanySystemId(final int companySystemId) {
        this.companySystemId = companySystemId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getTradeClassSysId() {
        return tradeClassSysId;
    }

    public void setTradeClassSysId(final int tradeClassSysId) {
        this.tradeClassSysId = tradeClassSysId;
    }

    public int getParentSysId() {
        return parentSysId;
    }

    public void setParentSysId(final int parentSysId) {
        this.parentSysId = parentSysId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final int createdBy) {
        this.createdBy = createdBy;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(final int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
