/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.dto;

import org.apache.commons.lang.StringUtils;


/**
 *
 * @author srithar
 */
public class BigFiveDTO {
    
    String product=StringUtils.EMPTY;
    String company=StringUtils.EMPTY;
    String sales=StringUtils.EMPTY;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }
    
}
