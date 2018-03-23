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
public class AssumptionSalesDTO {
    private String projectionPeriod;
    private String tradingPartner;
    private String customerID;
    private String group;
    private String brand;
    private String productGrowth;
    private String accountGrowth;

    public AssumptionSalesDTO() {
    }

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

    public String getProductGrowth() {
        return productGrowth;
    }

    public void setProductGrowth(String productGrowth) {
        this.productGrowth = productGrowth;
    }

    public String getAccountGrowth() {
        return accountGrowth;
    }

    public void setAccountGrowth(String accountGrowth) {
        this.accountGrowth = accountGrowth;
    }
    
}


