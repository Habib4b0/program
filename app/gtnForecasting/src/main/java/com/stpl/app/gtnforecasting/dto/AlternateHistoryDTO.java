/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

import java.util.Collections;
// TODO: Auto-generated Javadoc
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtMapDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class AlternateHistoryDTO.
 *
 * @author soundarrajan
 */
public class AlternateHistoryDTO extends ExtMapDTO{

    /**
     * The brandSearch.
     */
    private String brandSearch = StringUtils.EMPTY;

    /**
     * The customer.
     */
    private String customer= StringUtils.EMPTY;

    /**
     * The contract number.
     */
    private String contractNumber= StringUtils.EMPTY;

    /**
     * The contract name.
     */
    private String contractName= StringUtils.EMPTY;

    /**
     * The customer id.
     */
    private String customerId = StringUtils.EMPTY;

    /**
     * The contract holder.
     */
    private String contractHolder = StringUtils.EMPTY;

    private Integer contractSid = 0;

    private Integer brandSid = 0;

    /**
     * The Check.
     */
    private Boolean check=false;

    /**
     * The Market Type.
     */
    private String marketType = StringUtils.EMPTY;

    /**
     * The Contract No.
     */
    private String contractNo = StringUtils.EMPTY;

    /**
     * The customer No.
     */
    private String customerNo = StringUtils.EMPTY;

    /**
     * The customer Name.
     */
    private String customerName = StringUtils.EMPTY;

    private Boolean reset = false;

    private Integer contractMasterSid = 0;

    private Integer companymasterSid = 0;

    /**
     * The Item Name.
     */
    private String itemName = StringUtils.EMPTY;

    /**
     * The Item Name.
     */
    private String screenName = StringUtils.EMPTY;

    private String companyIds = StringUtils.EMPTY;

    private String businessUnitNo = StringUtils.EMPTY;

    private String businessUnitName = StringUtils.EMPTY;

    private String theraputicClass = StringUtils.EMPTY;

    private String brand = StringUtils.EMPTY;

    private String itemNo = StringUtils.EMPTY;

    private String itemIdentifierType = StringUtils.EMPTY;

    private String itemIdentifier = StringUtils.EMPTY;

    private Integer itemMasterSid = 0;
    
    private String ccpDetailsId=StringUtils.EMPTY;
    
    private Integer parent=0;
    
    private String frequency=StringUtils.EMPTY;
    

    private String startDate=StringUtils.EMPTY;
    
    private String endDate=StringUtils.EMPTY;

    private String actualsOrProjections = StringUtils.EMPTY;
    
  
    private Integer projDetailSid=new Integer(0);

    private String temptableSid=StringUtils.EMPTY;
    
    private final Set<String> selectedCustomerSet = new HashSet();
    
    private final Set<String> selectedProductSet = new HashSet();
    
    private Boolean checkRecord=false;
    
    public String getActualsOrProjections() {
        return actualsOrProjections;
    }

    public void setActualsOrProjections(String actualsOrProjections) {
        this.actualsOrProjections = actualsOrProjections;
    }
    
    public String getBrandSearch() {
        return brandSearch;
    }

    public void setBrandSearch(String brandSearch) {
        this.brandSearch = brandSearch;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
    }

    public Integer getContractSid() {
        return contractSid;
    }

    public void setContractSid(Integer contractSid) {
        this.contractSid = contractSid;
    }

    public Integer getBrandSid() {
        return brandSid;
    }

    public void setBrandSid(Integer brandSid) {
        this.brandSid = brandSid;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Boolean getReset() {
        return reset;
    }

    public void setReset(Boolean reset) {
        this.reset = reset;
    }

    public Integer getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(Integer contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public Integer getCompanymasterSid() {
        return companymasterSid;
    }

    public void setCompanymasterSid(Integer companymasterSid) {
        this.companymasterSid = companymasterSid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(String companyIds) {
        this.companyIds = companyIds;
    }

    public String getBusinessUnitNo() {
        return businessUnitNo;
    }

    public void setBusinessUnitNo(String businessUnitNo) {
        this.businessUnitNo = businessUnitNo;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public String getTheraputicClass() {
        return theraputicClass;
    }

    public void setTheraputicClass(String theraputicClass) {
        this.theraputicClass = theraputicClass;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemIdentifierType() {
        return itemIdentifierType;
    }

    public void setItemIdentifierType(String itemIdentifierType) {
        this.itemIdentifierType = itemIdentifierType;
    }

    public String getItemIdentifier() {
        return itemIdentifier;
    }

    public void setItemIdentifier(String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    public Integer getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(Integer itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public String getCcpDetailsId() {
        return ccpDetailsId;
    }

    public void setCcpDetailsId(String ccpDetailsId) {
        this.ccpDetailsId = ccpDetailsId;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public Integer getProjDetailSid() {
        return projDetailSid;
    }

    public void setProjDetailSid(Integer projDetailSid) {
        this.projDetailSid = projDetailSid;
    }


    public String getTemptableSid() {
        return temptableSid;
    }

    public void setTemptableSid(String temptableSid) {
        this.temptableSid = temptableSid;
    }

    public Set<String> getSelectedCustomerSet() {
        return selectedCustomerSet == null ? selectedCustomerSet : Collections.unmodifiableSet(selectedCustomerSet);
    }

    public Set<String> getSelectedProductSet() {
        return selectedProductSet == null ? selectedCustomerSet : Collections.unmodifiableSet(selectedCustomerSet);
    }

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }
    
}
