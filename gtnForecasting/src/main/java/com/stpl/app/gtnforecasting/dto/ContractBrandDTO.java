/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sibi
 */
public class ContractBrandDTO {
    
    
    /** The brandSearch. */
    private String brand;

    /** The customer. */
    private String customer;

    /** The contract number. */
    private String contractNumber;

    /** The contract name. */
    private String contractName;

    /** The customer id. */
    private String customerId;  
    
    private String contractHolder;
    
    private int contractSid=0;
    
    private String brandSid=StringUtils.EMPTY;
    
    private int contractRelationshipSid = 0;
    
    private int brandRelationshipSid = 0;
    
    private String contractHierarchyNo = StringUtils.EMPTY;
    
    private String brandHierarchyNo = StringUtils.EMPTY;
    
    private int brandMasterSid=0;

    public String getBrandSid() {
        return brandSid;
    }

    public void setBrandSid(String brandSid) {
        this.brandSid = brandSid;
    }
    

    public int getContractSid() {
        return contractSid;
    }

    public void setContractSid(int contractSid) {
        this.contractSid = contractSid;
    }

    /**
     * Gets the brandSearch.
     *
     * @return the brandSearch
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brandSearch.
     *
     * @param brand
     */
    public void setBrand(final String brand) {
        this.brand = brand;
    }

    /**
     * Gets the customer.
     *
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Sets the customer.
     *
     * @param customer the customer
     */
    public void setCustomer(final String customer) {
        this.customer = customer;
    }

    /**
     * Gets the contract number.
     *
     * @return the contract number
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     * Sets the contract number.
     *
     * @param contractNumber the contract number
     */
    public void setContractNumber(final String contractNumber) {
        this.contractNumber = contractNumber;
    }

    /**
     * Gets the contract name.
     *
     * @return the contract name
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * Sets the contract name.
     *
     * @param contractName the contract name
     */
    public void setContractName(final String contractName) {
        this.contractName = contractName;
    }

    /**
     * Gets the customer id.
     *
     * @return the customer id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer id.
     *
     * @param customerId the customer id
     */
    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }

    public int getContractRelationshipSid() {
        return contractRelationshipSid;
    }

    public void setContractRelationshipSid(int contractRelationshipSid) {
        this.contractRelationshipSid = contractRelationshipSid;
    }

    public int getBrandRelationshipSid() {
        return brandRelationshipSid;
    }

    public void setBrandRelationshipSid(int brandRelationshipSid) {
        this.brandRelationshipSid = brandRelationshipSid;
    }

    public String getContractHierarchyNo() {
        return contractHierarchyNo;
    }

    public void setContractHierarchyNo(String contractHierarchyNo) {
        this.contractHierarchyNo = contractHierarchyNo;
    }

    public String getBrandHierarchyNo() {
        return brandHierarchyNo;
    }

    public void setBrandHierarchyNo(String brandHierarchyNo) {
        this.brandHierarchyNo = brandHierarchyNo;
    }

    public int getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(int brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }
     public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
    }
    }
