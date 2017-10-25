/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.dto;

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
    private String projectionDescription = StringUtils.EMPTY;

    /**
     * The from period.
     */
    private Date fromPeriod;

    /**
     * The to period.
     */
    private Date toPeriod;

    /**
     * The customer hierarchy.
     */
    private String customerHierarchyName = StringUtils.EMPTY;

    /**
     * The customer level.
     */
    private String customerHierarchyLevel = StringUtils.EMPTY;

    /**
     * The company.
     */
    private String companyName = StringUtils.EMPTY;

    /**
     * The product hierarchy.
     */
    private String productHierarchyName = StringUtils.EMPTY;

    /**
     * The product level.
     */
    private String productHierarchyLevel = StringUtils.EMPTY;

    /**
     * The view id.
     */
    private String viewId = StringUtils.EMPTY;

    /**
     * Projection ID
     */
    private String projectionId = StringUtils.EMPTY;

    private String customerHierarchySid;
    private String productHierarchySid;

    private String custRelationshipBuilderSid;
    private String prodRelationshipBuilderSid;
    private String adjustmentType = StringUtils.EMPTY;

    private int gl_companyMasterSid;
    private int bu_companyMasterSid, deductionLevel;
    private int companyMasterSid;
    private int customerHierVerNo = 0;
    private String customerHierHL = StringUtils.EMPTY;
    private int productHierVerNo = 0;
    private String productHierHL = StringUtils.EMPTY;
    private int viewCreatedBy = 0;
    private String viewType = StringUtils.EMPTY;
    private String bu_CompanyName = StringUtils.EMPTY;

    private String customerRelationship = StringUtils.EMPTY;
    private String productRelationship = StringUtils.EMPTY;
    private Date createdDate;
    private Date modifiedDate;
    private String createdByString = StringUtils.EMPTY;
    private String deductionLevels = StringUtils.EMPTY;
    private int adjustmentId=0;
    private String calculationProfileMasterSid= StringUtils.EMPTY;
    private String calculationProfileName;
    private String calculationProfileDescrition;
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getProjectionDescription() {
        return projectionDescription;
    }

    public void setProjectionDescription(String projectionDescription) {
        this.projectionDescription = projectionDescription;
    }

    public Date getFromPeriod() {
        return fromPeriod;
    }

    public void setFromPeriod(Date fromPeriod) {
        this.fromPeriod = fromPeriod;
    }

    public Date getToPeriod() {
        return toPeriod;
    }

    public void setToPeriod(Date toPeriod) {
        this.toPeriod = toPeriod;
    }

    public String getCustomerHierarchyName() {
        return customerHierarchyName;
    }

    public void setCustomerHierarchyName(String customerHierarchyName) {
        this.customerHierarchyName = customerHierarchyName;
    }

    public String getCustomerHierarchyLevel() {
        return customerHierarchyLevel;
    }

    public void setCustomerHierarchyLevel(String customerHierarchyLevel) {
        this.customerHierarchyLevel = customerHierarchyLevel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductHierarchyName() {
        return productHierarchyName;
    }

    public void setProductHierarchyName(String productHierarchyName) {
        this.productHierarchyName = productHierarchyName;
    }

    public String getProductHierarchyLevel() {
        return productHierarchyLevel;
    }

    public void setProductHierarchyLevel(String productHierarchyLevel) {
        this.productHierarchyLevel = productHierarchyLevel;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(String projectionId) {
        this.projectionId = projectionId;
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

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public int getGl_companyMasterSid() {
        return gl_companyMasterSid;
    }

    public void setGl_companyMasterSid(int gl_companyMasterSid) {
        this.gl_companyMasterSid = gl_companyMasterSid;
    }

    public int getBu_companyMasterSid() {
        return bu_companyMasterSid;
    }

    public void setBu_companyMasterSid(int bu_companyMasterSid) {
        this.bu_companyMasterSid = bu_companyMasterSid;
    }

    public int getDeductionLevel() {
        return deductionLevel;
    }

    public void setDeductionLevel(int deductionLevel) {
        this.deductionLevel = deductionLevel;
    }

    public int getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public int getCustomerHierVers() {
        return customerHierVerNo;
    }

    public void setCustomerHierVers(int customerHierVers) {
        this.customerHierVerNo = customerHierVers;
    }

    public String getCustomerHierHL() {
        return customerHierHL;
    }

    public void setCustomerHierHL(String customerHierHL) {
        this.customerHierHL = customerHierHL;
    }

    public int getProductHierVers() {
        return productHierVerNo;
    }

    public void setProductHierVers(int productHierVers) {
        this.productHierVerNo = productHierVers;
    }

    public String getProductHierHL() {
        return productHierHL;
    }

    public void setProductHierHL(String productHierHL) {
        this.productHierHL = productHierHL;
    }

    public int getCustomerHierVerNo() {
        return customerHierVerNo;
    }

    public void setCustomerHierVerNo(int customerHierVerNo) {
        this.customerHierVerNo = customerHierVerNo;
    }

    public int getProductHierVerNo() {
        return productHierVerNo;
    }

    public void setProductHierVerNo(int productHierVerNo) {
        this.productHierVerNo = productHierVerNo;
    }

    public int getViewCreatedBy() {
        return viewCreatedBy;
    }

    public void setViewCreatedBy(int viewCreatedBy) {
        this.viewCreatedBy = viewCreatedBy;
    }

    

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getBu_CompanyName() {
        return bu_CompanyName;
    }

    public void setBu_CompanyName(String bu_CompanyName) {
        this.bu_CompanyName = bu_CompanyName;
    }

    public String getCustomerRelationship() {
        return customerRelationship;
    }

    public void setCustomerRelationship(String customerRelationship) {
        this.customerRelationship = customerRelationship;
    }

    public String getProductRelationship() {
        return productRelationship;
    }

    public void setProductRelationship(String productRelationship) {
        this.productRelationship = productRelationship;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByString() {
        return createdByString;
    }

    public void setCreatedByString(String createdByString) {
        this.createdByString = createdByString;
    }

    public String getDeductionLevels() {
        return deductionLevels;
    }

    public void setDeductionLevels(String deductionLevels) {
        this.deductionLevels = deductionLevels;
    }

    public int getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(int adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getCalculationProfileName() {
        return calculationProfileName;
    }

    public void setCalculationProfileName(String calculationProfileName) {
        this.calculationProfileName = calculationProfileName;
    }

    public String getCalculationProfileDescrition() {
        return calculationProfileDescrition;
    }

    public void setCalculationProfileDescrition(String calculationProfileDescrition) {
        this.calculationProfileDescrition = calculationProfileDescrition;
    }

    public String getCalculationProfileMasterSid() {
        return calculationProfileMasterSid;
    }

    public void setCalculationProfileMasterSid(String calculationProfileMasterSid) {
        this.calculationProfileMasterSid = calculationProfileMasterSid;
    }

    
    
}
