/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.dto;

import java.io.Serializable;

/**
 *
 * @author nandhakumar
 */
public class TaskManagementDTO implements Serializable{
    
    private String forecastName;
    private String forecastDesc;
    private String customerHierarchy;
    private String productHierarchy;
    private String createdDate;
    private String modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private String customerLevel;
    private String productLevel;
    private String status;
    private String forecastType;
    private int projectionId = 0;

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }
    
    public String getForecastName() {
        return forecastName;
    }

    public void setForecastName(String forecastName) {
        this.forecastName = forecastName;
    }

    public String getForecastDesc() {
        return forecastDesc;
    }

    public void setForecastDesc(String forecastDesc) {
        this.forecastDesc = forecastDesc;
    }

    public String getCustomerHierarchy() {
        return customerHierarchy;
    }

    public void setCustomerHierarchy(String customerHierarchy) {
        this.customerHierarchy = customerHierarchy;
    }

    public String getProductHierarchy() {
        return productHierarchy;
    }

    public void setProductHierarchy(String productHierarchy) {
        this.productHierarchy = productHierarchy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(String productLevel) {
        this.productLevel = productLevel;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getForecastType() {
        return forecastType;
    }

    public void setForecastType(String forecastType) {
        this.forecastType = forecastType;
    }
          
}
