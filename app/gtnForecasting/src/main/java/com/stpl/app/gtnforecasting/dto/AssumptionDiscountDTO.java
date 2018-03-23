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
public class AssumptionDiscountDTO {
  
    private String projectionPeriod;
    private String tradingPartner;
    private String customerID;
    private String group;
    private String brand;

    public AssumptionDiscountDTO() {
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
    
}

