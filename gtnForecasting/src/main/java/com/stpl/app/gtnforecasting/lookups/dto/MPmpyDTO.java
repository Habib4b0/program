/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.lookups.dto;

import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtMapDTO;

/**
 *
 * @author sibi
 */
public class MPmpyDTO extends ExtMapDTO{
    
    /** Product Number */
    private String productNo;
    
    /** Product Name */
    private String productName;
    
    /** The actuals */
    private String actuals = StringUtils.EMPTY; 
	 
    /** The projections */
    private String projections = StringUtils.EMPTY;
    
    private String productSid;
    
  
    /**
     * The sales.
     */
    private String sales = StringUtils.EMPTY; 
    /**
     * The sales.
     */
    private String lives = StringUtils.EMPTY; 
    
    /**
     * The sales.
     */
    private String totalLives = StringUtils.EMPTY; 
    

    /**
     * The value per life.
     */
    private String valuePerLife = StringUtils.EMPTY;   
    

    /**
     * The total sales.
     */
    private String totalSales = StringUtils.EMPTY;

    /**
     * The projection period total.
     */
    private String projectionPeriodTotal = StringUtils.EMPTY;
    
    /**
     * The Constant COMMA.
     */
    public static final String COMMA = ",";
    
    private String firstColumn = StringUtils.EMPTY;
    
    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getActuals() {
        return actuals;
    }

    public void setActuals(String actuals) {
        this.actuals = actuals;
    }

    public String getProjections() {
        return projections;
    }

    public void setProjections(String projections) {
        this.projections = projections;
    }

    public String getProductSid() {
        return productSid;
    }

    public void setProductSid(String productSid) {
        this.productSid = productSid;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getLives() {
        return lives;
    }

    public void setLives(String lives) {
        this.lives = lives;
    }

    public String getTotalLives() {
        return totalLives;
    }

    public void setTotalLives(String totalLives) {
        this.totalLives = totalLives;
    }

    public String getValuePerLife() {
        return valuePerLife;
    }

    public void setValuePerLife(String valuePerLife) {
        this.valuePerLife = valuePerLife;
    }

    public String getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(String totalSales) {
        this.totalSales = totalSales;
    }

    public String getProjectionPeriodTotal() {
        return projectionPeriodTotal;
    }

    public void setProjectionPeriodTotal(String projectionPeriodTotal) {
        this.projectionPeriodTotal = projectionPeriodTotal;
    }

    public String getFirstColumn() {
        return firstColumn;
    }

    public void setFirstColumn(String firstColumn) {
        this.firstColumn = firstColumn;
    }    
    
}
