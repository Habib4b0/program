/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui.forecastds.dto;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc

public class GroupDTO {

    /** customerGroupSid */
    private String customerGroupSid = StringUtils.EMPTY;
    
    /** productGroupSid */
    private String productGroupSid = StringUtils.EMPTY;
    
    /** customerGroupVersionNo */
    private String customerGroupVersionNo = StringUtils.EMPTY;
    
    /** productGroupVersionNo */
    private String productGroupVersionNo = StringUtils.EMPTY;
    
    /** The customer group name. */
    private String customerGroupName = StringUtils.EMPTY;
    
    /** The customer group no. */
    private String customerGroupNo = StringUtils.EMPTY;
    
    /** The product group name. */
    private String productGroupName = StringUtils.EMPTY;
    
    /** The product group no. */
    private String productGroupNo = StringUtils.EMPTY;
    
    /** The company. */
    private String company = StringUtils.EMPTY;
    
    /** The segment. */
    private String segment = StringUtils.EMPTY;

    /**
     * The segment group.
     */
    private String segmentGroup = StringUtils.EMPTY;

    private String customergroupDescription = StringUtils.EMPTY;

    private String productgroupDescription = StringUtils.EMPTY;

    /**
     * Gets the customer group name.
     *
     * @return the customer group name
     */
    public String getCustomerGroupName() {
        return customerGroupName;
    }

    /**
     * Sets the customer group name.
     *
     * @param customerGroupName the customer group name
     */
    public void setCustomerGroupName(final String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    /**
     * Gets the customer group no.
     *
     * @return the customer group no
     */
    public String getCustomerGroupNo() {
        return customerGroupNo;
    }

    /**
     * Sets the customer group no.
     *
     * @param customerGroupNo the customer group no
     */
    public void setCustomerGroupNo(final String customerGroupNo) {
        this.customerGroupNo = customerGroupNo;
    }

    /**
     * Gets the product group name.
     *
     * @return the product group name
     */
    public String getProductGroupName() {
        return productGroupName;
    }

    /**
     * Sets the product group name.
     *
     * @param productGroupName the product group name
     */
    public void setProductGroupName(final String productGroupName) {
        this.productGroupName = productGroupName;
    }

    /**
     * Gets the product group no.
     *
     * @return the product group no
     */
    public String getProductGroupNo() {
        return productGroupNo;
    }

    /**
     * Sets the product group no.
     *
     * @param productGroupNo the product group no
     */
    public void setProductGroupNo(final String productGroupNo) {
        this.productGroupNo = productGroupNo;
    }

    /**
     * Gets the company.
     *
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the company.
     *
     * @param company the company
     */
    public void setCompany(final String company) {
        this.company = company;
    }

    /**
     * Gets the segment.
     *
     * @return the segment
     */
    public String getSegment() {
        return segment;
    }

    /**
     * Sets the segment.
     *
     * @param segment the segment
     */
    public void setSegment(final String segment) {
        this.segment = segment;
    }

    /**
     * Gets the segment group.
     *
     * @return the segment group
     */
    public String getSegmentGroup() {
        return segmentGroup;
    }

    /**
     * Sets the segment group.
     *
     * @param segmentGroup the segment group
     */
    public void setSegmentGroup(final String segmentGroup) {
        this.segmentGroup = segmentGroup;
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

    public String getCustomergroupDescription() {
        return customergroupDescription;
    }

    public void setCustomergroupDescription(String customergroupDescription) {
        this.customergroupDescription = customergroupDescription;
    }

    public String getProductgroupDescription() {
        return productgroupDescription;
    }

    public void setProductgroupDescription(String productgroupDescription) {
        this.productgroupDescription = productgroupDescription;
    }

}
