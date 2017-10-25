/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui.forecastds.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
public class ViewDTO {

    /**
     * The view name.
     */
    private String viewName = StringUtils.EMPTY;

    /**
     * The description.
     */
    private String description = StringUtils.EMPTY;

    /**
     * The from period.
     */
    private String fromPeriod = StringUtils.EMPTY;

    /**
     * The to period.
     */
    private String toPeriod = StringUtils.EMPTY;

    /**
     * The customer hierarchy.
     */
    private String customerHierarchy = StringUtils.EMPTY;

    /**
     * The customer level.
     */
    private String customerLevel = StringUtils.EMPTY;

    /**
     * The customer group.
     */
    private String customerGroup = StringUtils.EMPTY;

    /**
     * The company.
     */
    private String company = StringUtils.EMPTY;

    /**
     * The brand type.
     */
    private String brandType = StringUtils.EMPTY;

    /**
     * The product hierarchy.
     */
    private String productHierarchy = StringUtils.EMPTY;

    /**
     * The product level.
     */
    private String productLevel = StringUtils.EMPTY;

    /**
     * The product group.
     */
    private String productGroup = StringUtils.EMPTY;

    /**
     * The created date.
     */
    private String createdDate = StringUtils.EMPTY;

    /**
     * The modified date.
     */
    private String modifiedDate = StringUtils.EMPTY;

    /**
     * The created by.
     */
    private String createdBy = StringUtils.EMPTY;

    /**
     * The view id.
     */
    private String viewId = StringUtils.EMPTY;

    /**
     * Projection ID
     */
    private String projectionId = StringUtils.EMPTY;

    /**
     * Projection Name
     */
    private String projectionName = StringUtils.EMPTY;

    private String companyGroupSid;
    private String productGroupSid;
    private String customerHierarchySid;
    private String productHierarchySid;
    private String companyMasterSid;

    private String customerInnerLevel = StringUtils.EMPTY;
    private String productInnerLevel = StringUtils.EMPTY;

    private String custRelationshipBuilderSid;
    private String prodRelationshipBuilderSid;
    private Date modifiedDateSearch;
    private Date createdDateSearch;
    private String createdUserid = StringUtils.EMPTY;
    private Date fromDate;
    private Date toDate;
    private String deductionLevel = StringUtils.EMPTY;
    private String deductionValue = StringUtils.EMPTY;
    private Integer deductionValueId;
    private String adjustmentType = StringUtils.EMPTY;
    private Integer businessUnitSystemId;
    private String businessUnitSystemName = StringUtils.EMPTY;

    /**
     * Gets the view name.
     *
     * @return the view name
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the view name.
     *
     * @param viewName the view name
     */
    public void setViewName(final String viewName) {
        this.viewName = viewName;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the from.
     *
     * @return the from
     */
    public String getFrom() {
        return fromPeriod;
    }

    /**
     * Sets the from.
     *
     * @param from the from
     */
    public void setFrom(final String from) {
        this.fromPeriod = from;
    }

    /**
     * Gets the to.
     *
     * @return the to
     */
    public String getTo() {
        return toPeriod;
    }

    /**
     * Sets the to.
     *
     * @param toPeriod the to
     */
    public void setTo(final String toPeriod) {
        this.toPeriod = toPeriod;
    }

    /**
     * Gets the customer hierarchy.
     *
     * @return the customer hierarchy
     */
    public String getCustomerHierarchy() {
        return customerHierarchy;
    }

    /**
     * Sets the customer hierarchy.
     *
     * @param customerHierarchy the customer hierarchy
     */
    public void setCustomerHierarchy(final String customerHierarchy) {
        this.customerHierarchy = customerHierarchy;
    }

    /**
     * Gets the customer level.
     *
     * @return the customer level
     */
    public String getCustomerLevel() {
        return customerLevel;
    }

    /**
     * Sets the customer level.
     *
     * @param customerLevel the customer level
     */
    public void setCustomerLevel(final String customerLevel) {
        this.customerLevel = customerLevel;
    }

    /**
     * Gets the customer group.
     *
     * @return the customer group
     */
    public String getCustomerGroup() {
        return customerGroup;
    }

    /**
     * Sets the customer group.
     *
     * @param customerGroup the customer group
     */
    public void setCustomerGroup(final String customerGroup) {
        this.customerGroup = customerGroup;
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
     * Gets the brand type.
     *
     * @return the brand type
     */
    public String getBrandType() {
        return brandType;
    }

    /**
     * Sets the brand type.
     *
     * @param brandType the brand type
     */
    public void setBrandType(final String brandType) {
        this.brandType = brandType;
    }

    /**
     * Gets the product hierarchy.
     *
     * @return the product hierarchy
     */
    public String getProductHierarchy() {
        return productHierarchy;
    }

    /**
     * Sets the product hierarchy.
     *
     * @param productHierarchy the product hierarchy
     */
    public void setProductHierarchy(final String productHierarchy) {
        this.productHierarchy = productHierarchy;
    }

    /**
     * Gets the product level.
     *
     * @return the product level
     */
    public String getProductLevel() {
        return productLevel;
    }

    /**
     * Sets the product level.
     *
     * @param productLevel the product level
     */
    public void setProductLevel(final String productLevel) {
        this.productLevel = productLevel;
    }

    /**
     * Gets the product group.
     *
     * @return the product group
     */
    public String getProductGroup() {
        return productGroup;
    }

    /**
     * Sets the product group.
     *
     * @param productGroup the product group
     */
    public void setProductGroup(final String productGroup) {
        this.productGroup = productGroup;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public String getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the modified date
     */
    public void setModifiedDate(final String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the created by
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the view id.
     *
     * @return the view id
     */
    public String getViewId() {
        return viewId;
    }

    /**
     * Sets the view id.
     *
     * @param viewId the view id
     */
    public void setViewId(final String viewId) {
        this.viewId = viewId;
    }

    public String getFromPeriod() {
        return fromPeriod;
    }

    public void setFromPeriod(String fromPeriod) {
        this.fromPeriod = fromPeriod;
    }

    public String getToPeriod() {
        return toPeriod;
    }

    public void setToPeriod(String toPeriod) {
        this.toPeriod = toPeriod;
    }

    public String getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(String projectionId) {
        this.projectionId = projectionId;
    }

    public String getProjectionName() {
        return projectionName;
    }

    public void setProjectionName(String projectionName) {
        this.projectionName = projectionName;
    }

    public String getCompanyGroupSid() {
        return companyGroupSid;
    }

    public void setCompanyGroupSid(String companyGroupSid) {
        this.companyGroupSid = companyGroupSid;
    }

    public String getProductGroupSid() {
        return productGroupSid;
    }

    public void setProductGroupSid(String productGroupSid) {
        this.productGroupSid = productGroupSid;
    }

    public String getCustomerHierarchySid() {
        return customerHierarchySid;
    }

    public void setCustomerHierarchySid(String customerHierarchySid) {
        this.customerHierarchySid = customerHierarchySid;
    }

    public String getProductHierarchySid() {
        return productHierarchySid;
    }

    public void setProductHierarchySid(String productHierarchySid) {
        this.productHierarchySid = productHierarchySid;
    }

    public String getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(String companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public String getCustomerInnerLevel() {
        return customerInnerLevel;
    }

    public void setCustomerInnerLevel(String customerInnerLevel) {
        this.customerInnerLevel = customerInnerLevel;
    }

    public String getProductInnerLevel() {
        return productInnerLevel;
    }

    public void setProductInnerLevel(String productInnerLevel) {
        this.productInnerLevel = productInnerLevel;
    }

    public String getCustRelationshipBuilderSid() {
        return custRelationshipBuilderSid;
    }

    public void setCustRelationshipBuilderSid(String custRelationshipBuilderSid) {
        this.custRelationshipBuilderSid = custRelationshipBuilderSid;
    }

    public String getProdRelationshipBuilderSid() {
        return prodRelationshipBuilderSid;
    }

    public void setProdRelationshipBuilderSid(String prodRelationshipBuilderSid) {
        this.prodRelationshipBuilderSid = prodRelationshipBuilderSid;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getModifiedDateSearch() {
        return modifiedDateSearch;
    }

    public void setModifiedDateSearch(Date modifiedDateSearch) {
        this.modifiedDateSearch = modifiedDateSearch;
    }

    public Date getCreatedDateSearch() {
        return createdDateSearch;
    }

    public void setCreatedDateSearch(Date createdDateSearch) {
        this.createdDateSearch = createdDateSearch;
    }

    public String getCreatedUserid() {
        return createdUserid;
    }

    public void setCreatedUserid(String createdUserid) {
        this.createdUserid = createdUserid;
    }

    public String getDeductionLevel() {
        return deductionLevel;
    }

    public void setDeductionLevel(String deductionLevel) {
        this.deductionLevel = deductionLevel;
    }

    public String getDeductionValue() {
        return deductionValue;
    }

    public void setDeductionValue(String deductionValue) {
        this.deductionValue = deductionValue;
    }

    public Integer getDeductionValueId() {
        return deductionValueId;
    }

    public void setDeductionValueId(Integer deductionValueId) {
        this.deductionValueId = deductionValueId;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public Integer getBusinessUnitSystemId() {
        return businessUnitSystemId;
    }

    public void setBusinessUnitSystemId(Integer businessUnitSystemId) {
        this.businessUnitSystemId = businessUnitSystemId;
    }

    public String getBusinessUnitSystemName() {
        return businessUnitSystemName;
    }

    public void setBusinessUnitSystemName(String businessUnitSystemName) {
        this.businessUnitSystemName = businessUnitSystemName;
    }
    
}
