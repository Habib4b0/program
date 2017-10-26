/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class GroupDTO {

    private String customerGroupSid = StringUtils.EMPTY;

    private String productGroupSid = StringUtils.EMPTY;

    private String customerGroupVersionNo = StringUtils.EMPTY;

    private String productGroupVersionNo = StringUtils.EMPTY;

    private String customerGroupName = StringUtils.EMPTY;

    private String customerGroupNo = StringUtils.EMPTY;

    private String productGroupName = StringUtils.EMPTY;

    private String productGroupNo = StringUtils.EMPTY;

    private String company = StringUtils.EMPTY;

    private String indicator = StringUtils.EMPTY;

    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private String customerGroupDesc = StringUtils.EMPTY;
    private String productGroupDesc = StringUtils.EMPTY;

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(final String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public String getCustomerGroupNo() {
        return customerGroupNo;
    }

    public void setCustomerGroupNo(final String customerGroupNo) {
        this.customerGroupNo = customerGroupNo;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(final String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public String getProductGroupNo() {
        return productGroupNo;
    }

    public void setProductGroupNo(final String productGroupNo) {
        this.productGroupNo = productGroupNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(final String company) {
        this.company = company;
    }

    public String getCustomerGroupSid() {
        return customerGroupSid;
    }

    public void setCustomerGroupSid(String customerGroupSid) {
        this.customerGroupSid = customerGroupSid;
    }

    public String getProductGroupSid() {
        return productGroupSid;
    }

    public void setProductGroupSid(String productGroupSid) {
        this.productGroupSid = productGroupSid;
    }

    public String getCustomerGroupVersionNo() {
        return customerGroupVersionNo;
    }

    public void setCustomerGroupVersionNo(String customerGroupVersionNo) {
        this.customerGroupVersionNo = customerGroupVersionNo;
    }

    public String getProductGroupVersionNo() {
        return productGroupVersionNo;
    }

    public void setProductGroupVersionNo(String productGroupVersionNo) {
        this.productGroupVersionNo = productGroupVersionNo;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public String getCustomerGroupDesc() {
        return customerGroupDesc;
    }

    public void setCustomerGroupDesc(String customerGroupDesc) {
        this.customerGroupDesc = customerGroupDesc;
    }

    public String getProductGroupDesc() {
        return productGroupDesc;
    }

    public void setProductGroupDesc(String productGroupDesc) {
        this.productGroupDesc = productGroupDesc;
    }

}
