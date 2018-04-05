/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

/**
 *
 * @author harlin
 */
public class AssumptionCIDTDTO {
    
    private String contractHolderOrTP;
    private String customerID;
    private String segment;
    private String marketType;
    private String movedFromname;
    private String movedFromCustomerID;
    private String movedToname;
    private String movedToCustomerID;
    private String effectiveDate;
    private String annualGrossSales;

    public AssumptionCIDTDTO() {
        super();
    }

    public String getContractHolderOrTP() {
        return contractHolderOrTP;
    }

    public void setContractHolderOrTP(String contractHolderOrTP) {
        this.contractHolderOrTP = contractHolderOrTP;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getMovedFromname() {
        return movedFromname;
    }

    public void setMovedFromname(String movedFromname) {
        this.movedFromname = movedFromname;
    }

    public String getMovedFromCustomerID() {
        return movedFromCustomerID;
    }

    public void setMovedFromCustomerID(String movedFromCustomerID) {
        this.movedFromCustomerID = movedFromCustomerID;
    }

    public String getMovedToname() {
        return movedToname;
    }

    public void setMovedToname(String movedToname) {
        this.movedToname = movedToname;
    }

    public String getMovedToCustomerID() {
        return movedToCustomerID;
    }

    public void setMovedToCustomerID(String movedToCustomerID) {
        this.movedToCustomerID = movedToCustomerID;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getAnnualGrossSales() {
        return annualGrossSales;
    }

    public void setAnnualGrossSales(String annualGrossSales) {
        this.annualGrossSales = annualGrossSales;
    }
        
}


