/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.accrualrateprojection.dto;

import org.asi.container.ExtListDTO;

/**
 *
 * @author sibi
 */
public class AccrualRateProjectionDTO extends ExtListDTO{

    private String product;

    private String group;

    public AccrualRateProjectionDTO() {
    }
  
 public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

}
