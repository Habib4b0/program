/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author srithar
 */
public class SaveDTO {

    private String propertyId = StringUtils.EMPTY;
    private Object value = null;
    private String hirarechyNo = StringUtils.EMPTY;
    private String customerHierarchyNo = StringUtils.EMPTY;
    private String productHierarchyNo = StringUtils.EMPTY;
    private int treeLevelNo = 0;
    private String hierarchyIndicator = StringUtils.EMPTY;
    private String group = StringUtils.EMPTY;
    private String header = StringUtils.EMPTY;
    private String refreshName = StringUtils.EMPTY;
    /**
     * right or left table 
     */
    private String table=StringUtils.EMPTY;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * To store the period Number. 4 for 4 quarters 2 for 2 semi annuals 4 for
     * march month
     */
    private int periodNo = 0;

    /**
     * To store the year such as 2014
     */
    private int year = 0;

    /**
     * To store the discount names if available.
     */
    private String discountName = StringUtils.EMPTY;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getHirarechyNo() {
        return hirarechyNo;
    }

    public void setHirarechyNo(String hirarechyNo) {
        this.hirarechyNo = hirarechyNo;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public int getPeriodNo() {
        return periodNo;
    }

    public void setPeriodNo(int periodNo) {
        this.periodNo = periodNo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getCustomerHierarchyNo() {
        return customerHierarchyNo;
    }

    public void setCustomerHierarchyNo(String customerHierarchyNo) {
        this.customerHierarchyNo = customerHierarchyNo;
    }

    public String getProductHierarchyNo() {
        return productHierarchyNo;
    }

    public void setProductHierarchyNo(String productHierarchyNo) {
        this.productHierarchyNo = productHierarchyNo;
    }

    public int getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(int treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
    }
    
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getRefreshName() {
        return refreshName;
    }

    public void setRefreshName(String refreshName) {
        this.refreshName = refreshName;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    
}
