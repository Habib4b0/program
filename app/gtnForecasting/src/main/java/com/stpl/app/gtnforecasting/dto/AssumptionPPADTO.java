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
public class AssumptionPPADTO {
    private String projectionPeriod;
    private String tradingPartner;
    private String customerID;
    private String group;
    private String brand;
    private String ppaCap;
    private String ppaDiscountDollar;
    private String ppaDiscountPer;

    public String getProjectionPeriod() {
        return projectionPeriod;
    }

    public void setProjectionPeriod(String projectionPeriod) {
        this.projectionPeriod = projectionPeriod;
    }

    public String getTradingPartner() {
        return tradingPartner;
    }

    public void setTradingPartner(String tradingPartner) {
        this.tradingPartner = tradingPartner;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPpaCap() {
        return ppaCap;
    }

    public void setPpaCap(String ppaCap) {
        this.ppaCap = ppaCap;
    }

    public String getPpaDiscountDollar() {
        return ppaDiscountDollar;
    }

    public void setPpaDiscountDollar(String ppaDiscountDollar) {
        this.ppaDiscountDollar = ppaDiscountDollar;
    }

    public String getPpaDiscountPer() {
        return ppaDiscountPer;
    }

    public void setPpaDiscountPer(String ppaDiscountPer) {
        this.ppaDiscountPer = ppaDiscountPer;
    }
    
}

