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
public class FixedDollarGroupDTO {

    private String customerGroupSid = StringUtils.EMPTY;

    private String productGroupSid = StringUtils.EMPTY;

    private String customerGroupVersionNo = StringUtils.EMPTY;

    private String productGroupVersionNo = StringUtils.EMPTY;

    private String customerGroupName = StringUtils.EMPTY;

    private String customerGroupNo = StringUtils.EMPTY;

    private String productGroupName = StringUtils.EMPTY;

    private String productGroupNo = StringUtils.EMPTY;

    private String company = StringUtils.EMPTY;

    private String segmentGroup = StringUtils.EMPTY;

    private String segment = StringUtils.EMPTY;

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

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public String getCustomerGroupNo() {
        return customerGroupNo;
    }

    public void setCustomerGroupNo(String customerGroupNo) {
        this.customerGroupNo = customerGroupNo;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public String getProductGroupNo() {
        return productGroupNo;
    }

    public void setProductGroupNo(String productGroupNo) {
        this.productGroupNo = productGroupNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSegmentGroup() {
        return segmentGroup;
    }

    public void setSegmentGroup(String segmentGroup) {
        this.segmentGroup = segmentGroup;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

}
