package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProjectionMaster}.
 * </p>
 *
 * @author
 * @see ProjectionMaster
 * @generated
 */
public class ProjectionMasterWrapper implements ProjectionMaster,
    ModelWrapper<ProjectionMaster> {
    private ProjectionMaster _projectionMaster;

    public ProjectionMasterWrapper(ProjectionMaster projectionMaster) {
        _projectionMaster = projectionMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("productHierarchyLevel", getProductHierarchyLevel());
        attributes.put("saveFlag", getSaveFlag());
        attributes.put("projectionName", getProjectionName());
        attributes.put("toDate", getToDate());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("forecastingType", getForecastingType());
        attributes.put("productHierVersionNo", getProductHierVersionNo());
        attributes.put("customerHierVersionNo", getCustomerHierVersionNo());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("customerHierarchyLevel", getCustomerHierarchyLevel());
        attributes.put("fromDate", getFromDate());
        attributes.put("productHierarchySid", getProductHierarchySid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("customerHierarchySid", getCustomerHierarchySid());
        attributes.put("companyGroupSid", getCompanyGroupSid());
        attributes.put("brandType", getBrandType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("projectionDescription", getProjectionDescription());
        attributes.put("isApproved", getIsApproved());
        attributes.put("itemGroupSid", getItemGroupSid());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("customerHierarchyInnerLevel",
            getCustomerHierarchyInnerLevel());
        attributes.put("productHierarchyInnerLevel",
            getProductHierarchyInnerLevel());
        attributes.put("custRelationshipBuilderSid",
            getCustRelationshipBuilderSid());
        attributes.put("prodRelationshipBuilderSid",
            getProdRelationshipBuilderSid());
        attributes.put("discountType", getDiscountType());
        attributes.put("businessUnit", getBusinessUnit());
        attributes.put("deductionHierarchySid", getDeductionHierarchySid());
        attributes.put("dedRelationshipBuilderSid",
            getDedRelationshipBuilderSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer productHierarchyLevel = (Integer) attributes.get(
                "productHierarchyLevel");

        if (productHierarchyLevel != null) {
            setProductHierarchyLevel(productHierarchyLevel);
        }

        Boolean saveFlag = (Boolean) attributes.get("saveFlag");

        if (saveFlag != null) {
            setSaveFlag(saveFlag);
        }

        String projectionName = (String) attributes.get("projectionName");

        if (projectionName != null) {
            setProjectionName(projectionName);
        }

        Date toDate = (Date) attributes.get("toDate");

        if (toDate != null) {
            setToDate(toDate);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        String forecastingType = (String) attributes.get("forecastingType");

        if (forecastingType != null) {
            setForecastingType(forecastingType);
        }

        Integer productHierVersionNo = (Integer) attributes.get(
                "productHierVersionNo");

        if (productHierVersionNo != null) {
            setProductHierVersionNo(productHierVersionNo);
        }

        Integer customerHierVersionNo = (Integer) attributes.get(
                "customerHierVersionNo");

        if (customerHierVersionNo != null) {
            setCustomerHierVersionNo(customerHierVersionNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer customerHierarchyLevel = (Integer) attributes.get(
                "customerHierarchyLevel");

        if (customerHierarchyLevel != null) {
            setCustomerHierarchyLevel(customerHierarchyLevel);
        }

        Date fromDate = (Date) attributes.get("fromDate");

        if (fromDate != null) {
            setFromDate(fromDate);
        }

        String productHierarchySid = (String) attributes.get(
                "productHierarchySid");

        if (productHierarchySid != null) {
            setProductHierarchySid(productHierarchySid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String customerHierarchySid = (String) attributes.get(
                "customerHierarchySid");

        if (customerHierarchySid != null) {
            setCustomerHierarchySid(customerHierarchySid);
        }

        String companyGroupSid = (String) attributes.get("companyGroupSid");

        if (companyGroupSid != null) {
            setCompanyGroupSid(companyGroupSid);
        }

        Boolean brandType = (Boolean) attributes.get("brandType");

        if (brandType != null) {
            setBrandType(brandType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String projectionDescription = (String) attributes.get(
                "projectionDescription");

        if (projectionDescription != null) {
            setProjectionDescription(projectionDescription);
        }

        String isApproved = (String) attributes.get("isApproved");

        if (isApproved != null) {
            setIsApproved(isApproved);
        }

        String itemGroupSid = (String) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
        }

        String companyMasterSid = (String) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Integer customerHierarchyInnerLevel = (Integer) attributes.get(
                "customerHierarchyInnerLevel");

        if (customerHierarchyInnerLevel != null) {
            setCustomerHierarchyInnerLevel(customerHierarchyInnerLevel);
        }

        Integer productHierarchyInnerLevel = (Integer) attributes.get(
                "productHierarchyInnerLevel");

        if (productHierarchyInnerLevel != null) {
            setProductHierarchyInnerLevel(productHierarchyInnerLevel);
        }

        String custRelationshipBuilderSid = (String) attributes.get(
                "custRelationshipBuilderSid");

        if (custRelationshipBuilderSid != null) {
            setCustRelationshipBuilderSid(custRelationshipBuilderSid);
        }

        String prodRelationshipBuilderSid = (String) attributes.get(
                "prodRelationshipBuilderSid");

        if (prodRelationshipBuilderSid != null) {
            setProdRelationshipBuilderSid(prodRelationshipBuilderSid);
        }

        Integer discountType = (Integer) attributes.get("discountType");

        if (discountType != null) {
            setDiscountType(discountType);
        }

        Integer businessUnit = (Integer) attributes.get("businessUnit");

        if (businessUnit != null) {
            setBusinessUnit(businessUnit);
        }

        String deductionHierarchySid = (String) attributes.get(
                "deductionHierarchySid");

        if (deductionHierarchySid != null) {
            setDeductionHierarchySid(deductionHierarchySid);
        }

        String dedRelationshipBuilderSid = (String) attributes.get(
                "dedRelationshipBuilderSid");

        if (dedRelationshipBuilderSid != null) {
            setDedRelationshipBuilderSid(dedRelationshipBuilderSid);
        }
    }

    /**
    * Returns the primary key of this projection master.
    *
    * @return the primary key of this projection master
    */
    @Override
    public int getPrimaryKey() {
        return _projectionMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this projection master.
    *
    * @param primaryKey the primary key of this projection master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _projectionMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the product hierarchy level of this projection master.
    *
    * @return the product hierarchy level of this projection master
    */
    @Override
    public int getProductHierarchyLevel() {
        return _projectionMaster.getProductHierarchyLevel();
    }

    /**
    * Sets the product hierarchy level of this projection master.
    *
    * @param productHierarchyLevel the product hierarchy level of this projection master
    */
    @Override
    public void setProductHierarchyLevel(int productHierarchyLevel) {
        _projectionMaster.setProductHierarchyLevel(productHierarchyLevel);
    }

    /**
    * Returns the save flag of this projection master.
    *
    * @return the save flag of this projection master
    */
    @Override
    public boolean getSaveFlag() {
        return _projectionMaster.getSaveFlag();
    }

    /**
    * Returns <code>true</code> if this projection master is save flag.
    *
    * @return <code>true</code> if this projection master is save flag; <code>false</code> otherwise
    */
    @Override
    public boolean isSaveFlag() {
        return _projectionMaster.isSaveFlag();
    }

    /**
    * Sets whether this projection master is save flag.
    *
    * @param saveFlag the save flag of this projection master
    */
    @Override
    public void setSaveFlag(boolean saveFlag) {
        _projectionMaster.setSaveFlag(saveFlag);
    }

    /**
    * Returns the projection name of this projection master.
    *
    * @return the projection name of this projection master
    */
    @Override
    public java.lang.String getProjectionName() {
        return _projectionMaster.getProjectionName();
    }

    /**
    * Sets the projection name of this projection master.
    *
    * @param projectionName the projection name of this projection master
    */
    @Override
    public void setProjectionName(java.lang.String projectionName) {
        _projectionMaster.setProjectionName(projectionName);
    }

    /**
    * Returns the to date of this projection master.
    *
    * @return the to date of this projection master
    */
    @Override
    public java.util.Date getToDate() {
        return _projectionMaster.getToDate();
    }

    /**
    * Sets the to date of this projection master.
    *
    * @param toDate the to date of this projection master
    */
    @Override
    public void setToDate(java.util.Date toDate) {
        _projectionMaster.setToDate(toDate);
    }

    /**
    * Returns the projection master sid of this projection master.
    *
    * @return the projection master sid of this projection master
    */
    @Override
    public int getProjectionMasterSid() {
        return _projectionMaster.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this projection master.
    *
    * @param projectionMasterSid the projection master sid of this projection master
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMaster.setProjectionMasterSid(projectionMasterSid);
    }

    /**
    * Returns the forecasting type of this projection master.
    *
    * @return the forecasting type of this projection master
    */
    @Override
    public java.lang.String getForecastingType() {
        return _projectionMaster.getForecastingType();
    }

    /**
    * Sets the forecasting type of this projection master.
    *
    * @param forecastingType the forecasting type of this projection master
    */
    @Override
    public void setForecastingType(java.lang.String forecastingType) {
        _projectionMaster.setForecastingType(forecastingType);
    }

    /**
    * Returns the product hier version no of this projection master.
    *
    * @return the product hier version no of this projection master
    */
    @Override
    public int getProductHierVersionNo() {
        return _projectionMaster.getProductHierVersionNo();
    }

    /**
    * Sets the product hier version no of this projection master.
    *
    * @param productHierVersionNo the product hier version no of this projection master
    */
    @Override
    public void setProductHierVersionNo(int productHierVersionNo) {
        _projectionMaster.setProductHierVersionNo(productHierVersionNo);
    }

    /**
    * Returns the customer hier version no of this projection master.
    *
    * @return the customer hier version no of this projection master
    */
    @Override
    public int getCustomerHierVersionNo() {
        return _projectionMaster.getCustomerHierVersionNo();
    }

    /**
    * Sets the customer hier version no of this projection master.
    *
    * @param customerHierVersionNo the customer hier version no of this projection master
    */
    @Override
    public void setCustomerHierVersionNo(int customerHierVersionNo) {
        _projectionMaster.setCustomerHierVersionNo(customerHierVersionNo);
    }

    /**
    * Returns the modified date of this projection master.
    *
    * @return the modified date of this projection master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _projectionMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this projection master.
    *
    * @param modifiedDate the modified date of this projection master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _projectionMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the customer hierarchy level of this projection master.
    *
    * @return the customer hierarchy level of this projection master
    */
    @Override
    public int getCustomerHierarchyLevel() {
        return _projectionMaster.getCustomerHierarchyLevel();
    }

    /**
    * Sets the customer hierarchy level of this projection master.
    *
    * @param customerHierarchyLevel the customer hierarchy level of this projection master
    */
    @Override
    public void setCustomerHierarchyLevel(int customerHierarchyLevel) {
        _projectionMaster.setCustomerHierarchyLevel(customerHierarchyLevel);
    }

    /**
    * Returns the from date of this projection master.
    *
    * @return the from date of this projection master
    */
    @Override
    public java.util.Date getFromDate() {
        return _projectionMaster.getFromDate();
    }

    /**
    * Sets the from date of this projection master.
    *
    * @param fromDate the from date of this projection master
    */
    @Override
    public void setFromDate(java.util.Date fromDate) {
        _projectionMaster.setFromDate(fromDate);
    }

    /**
    * Returns the product hierarchy sid of this projection master.
    *
    * @return the product hierarchy sid of this projection master
    */
    @Override
    public java.lang.String getProductHierarchySid() {
        return _projectionMaster.getProductHierarchySid();
    }

    /**
    * Sets the product hierarchy sid of this projection master.
    *
    * @param productHierarchySid the product hierarchy sid of this projection master
    */
    @Override
    public void setProductHierarchySid(java.lang.String productHierarchySid) {
        _projectionMaster.setProductHierarchySid(productHierarchySid);
    }

    /**
    * Returns the created date of this projection master.
    *
    * @return the created date of this projection master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _projectionMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this projection master.
    *
    * @param createdDate the created date of this projection master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _projectionMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this projection master.
    *
    * @return the created by of this projection master
    */
    @Override
    public int getCreatedBy() {
        return _projectionMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this projection master.
    *
    * @param createdBy the created by of this projection master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _projectionMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the customer hierarchy sid of this projection master.
    *
    * @return the customer hierarchy sid of this projection master
    */
    @Override
    public java.lang.String getCustomerHierarchySid() {
        return _projectionMaster.getCustomerHierarchySid();
    }

    /**
    * Sets the customer hierarchy sid of this projection master.
    *
    * @param customerHierarchySid the customer hierarchy sid of this projection master
    */
    @Override
    public void setCustomerHierarchySid(java.lang.String customerHierarchySid) {
        _projectionMaster.setCustomerHierarchySid(customerHierarchySid);
    }

    /**
    * Returns the company group sid of this projection master.
    *
    * @return the company group sid of this projection master
    */
    @Override
    public java.lang.String getCompanyGroupSid() {
        return _projectionMaster.getCompanyGroupSid();
    }

    /**
    * Sets the company group sid of this projection master.
    *
    * @param companyGroupSid the company group sid of this projection master
    */
    @Override
    public void setCompanyGroupSid(java.lang.String companyGroupSid) {
        _projectionMaster.setCompanyGroupSid(companyGroupSid);
    }

    /**
    * Returns the brand type of this projection master.
    *
    * @return the brand type of this projection master
    */
    @Override
    public boolean getBrandType() {
        return _projectionMaster.getBrandType();
    }

    /**
    * Returns <code>true</code> if this projection master is brand type.
    *
    * @return <code>true</code> if this projection master is brand type; <code>false</code> otherwise
    */
    @Override
    public boolean isBrandType() {
        return _projectionMaster.isBrandType();
    }

    /**
    * Sets whether this projection master is brand type.
    *
    * @param brandType the brand type of this projection master
    */
    @Override
    public void setBrandType(boolean brandType) {
        _projectionMaster.setBrandType(brandType);
    }

    /**
    * Returns the modified by of this projection master.
    *
    * @return the modified by of this projection master
    */
    @Override
    public int getModifiedBy() {
        return _projectionMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this projection master.
    *
    * @param modifiedBy the modified by of this projection master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _projectionMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the projection description of this projection master.
    *
    * @return the projection description of this projection master
    */
    @Override
    public java.lang.String getProjectionDescription() {
        return _projectionMaster.getProjectionDescription();
    }

    /**
    * Sets the projection description of this projection master.
    *
    * @param projectionDescription the projection description of this projection master
    */
    @Override
    public void setProjectionDescription(java.lang.String projectionDescription) {
        _projectionMaster.setProjectionDescription(projectionDescription);
    }

    /**
    * Returns the is approved of this projection master.
    *
    * @return the is approved of this projection master
    */
    @Override
    public java.lang.String getIsApproved() {
        return _projectionMaster.getIsApproved();
    }

    /**
    * Sets the is approved of this projection master.
    *
    * @param isApproved the is approved of this projection master
    */
    @Override
    public void setIsApproved(java.lang.String isApproved) {
        _projectionMaster.setIsApproved(isApproved);
    }

    /**
    * Returns the item group sid of this projection master.
    *
    * @return the item group sid of this projection master
    */
    @Override
    public java.lang.String getItemGroupSid() {
        return _projectionMaster.getItemGroupSid();
    }

    /**
    * Sets the item group sid of this projection master.
    *
    * @param itemGroupSid the item group sid of this projection master
    */
    @Override
    public void setItemGroupSid(java.lang.String itemGroupSid) {
        _projectionMaster.setItemGroupSid(itemGroupSid);
    }

    /**
    * Returns the company master sid of this projection master.
    *
    * @return the company master sid of this projection master
    */
    @Override
    public java.lang.String getCompanyMasterSid() {
        return _projectionMaster.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this projection master.
    *
    * @param companyMasterSid the company master sid of this projection master
    */
    @Override
    public void setCompanyMasterSid(java.lang.String companyMasterSid) {
        _projectionMaster.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the customer hierarchy inner level of this projection master.
    *
    * @return the customer hierarchy inner level of this projection master
    */
    @Override
    public int getCustomerHierarchyInnerLevel() {
        return _projectionMaster.getCustomerHierarchyInnerLevel();
    }

    /**
    * Sets the customer hierarchy inner level of this projection master.
    *
    * @param customerHierarchyInnerLevel the customer hierarchy inner level of this projection master
    */
    @Override
    public void setCustomerHierarchyInnerLevel(int customerHierarchyInnerLevel) {
        _projectionMaster.setCustomerHierarchyInnerLevel(customerHierarchyInnerLevel);
    }

    /**
    * Returns the product hierarchy inner level of this projection master.
    *
    * @return the product hierarchy inner level of this projection master
    */
    @Override
    public int getProductHierarchyInnerLevel() {
        return _projectionMaster.getProductHierarchyInnerLevel();
    }

    /**
    * Sets the product hierarchy inner level of this projection master.
    *
    * @param productHierarchyInnerLevel the product hierarchy inner level of this projection master
    */
    @Override
    public void setProductHierarchyInnerLevel(int productHierarchyInnerLevel) {
        _projectionMaster.setProductHierarchyInnerLevel(productHierarchyInnerLevel);
    }

    /**
    * Returns the cust relationship builder sid of this projection master.
    *
    * @return the cust relationship builder sid of this projection master
    */
    @Override
    public java.lang.String getCustRelationshipBuilderSid() {
        return _projectionMaster.getCustRelationshipBuilderSid();
    }

    /**
    * Sets the cust relationship builder sid of this projection master.
    *
    * @param custRelationshipBuilderSid the cust relationship builder sid of this projection master
    */
    @Override
    public void setCustRelationshipBuilderSid(
        java.lang.String custRelationshipBuilderSid) {
        _projectionMaster.setCustRelationshipBuilderSid(custRelationshipBuilderSid);
    }

    /**
    * Returns the prod relationship builder sid of this projection master.
    *
    * @return the prod relationship builder sid of this projection master
    */
    @Override
    public java.lang.String getProdRelationshipBuilderSid() {
        return _projectionMaster.getProdRelationshipBuilderSid();
    }

    /**
    * Sets the prod relationship builder sid of this projection master.
    *
    * @param prodRelationshipBuilderSid the prod relationship builder sid of this projection master
    */
    @Override
    public void setProdRelationshipBuilderSid(
        java.lang.String prodRelationshipBuilderSid) {
        _projectionMaster.setProdRelationshipBuilderSid(prodRelationshipBuilderSid);
    }

    /**
    * Returns the discount type of this projection master.
    *
    * @return the discount type of this projection master
    */
    @Override
    public int getDiscountType() {
        return _projectionMaster.getDiscountType();
    }

    /**
    * Sets the discount type of this projection master.
    *
    * @param discountType the discount type of this projection master
    */
    @Override
    public void setDiscountType(int discountType) {
        _projectionMaster.setDiscountType(discountType);
    }

    /**
    * Returns the business unit of this projection master.
    *
    * @return the business unit of this projection master
    */
    @Override
    public int getBusinessUnit() {
        return _projectionMaster.getBusinessUnit();
    }

    /**
    * Sets the business unit of this projection master.
    *
    * @param businessUnit the business unit of this projection master
    */
    @Override
    public void setBusinessUnit(int businessUnit) {
        _projectionMaster.setBusinessUnit(businessUnit);
    }

    /**
    * Returns the deduction hierarchy sid of this projection master.
    *
    * @return the deduction hierarchy sid of this projection master
    */
    @Override
    public java.lang.String getDeductionHierarchySid() {
        return _projectionMaster.getDeductionHierarchySid();
    }

    /**
    * Sets the deduction hierarchy sid of this projection master.
    *
    * @param deductionHierarchySid the deduction hierarchy sid of this projection master
    */
    @Override
    public void setDeductionHierarchySid(java.lang.String deductionHierarchySid) {
        _projectionMaster.setDeductionHierarchySid(deductionHierarchySid);
    }

    /**
    * Returns the ded relationship builder sid of this projection master.
    *
    * @return the ded relationship builder sid of this projection master
    */
    @Override
    public java.lang.String getDedRelationshipBuilderSid() {
        return _projectionMaster.getDedRelationshipBuilderSid();
    }

    /**
    * Sets the ded relationship builder sid of this projection master.
    *
    * @param dedRelationshipBuilderSid the ded relationship builder sid of this projection master
    */
    @Override
    public void setDedRelationshipBuilderSid(
        java.lang.String dedRelationshipBuilderSid) {
        _projectionMaster.setDedRelationshipBuilderSid(dedRelationshipBuilderSid);
    }

    @Override
    public boolean isNew() {
        return _projectionMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _projectionMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _projectionMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _projectionMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _projectionMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _projectionMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _projectionMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _projectionMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _projectionMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _projectionMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _projectionMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProjectionMasterWrapper((ProjectionMaster) _projectionMaster.clone());
    }

    @Override
    public int compareTo(ProjectionMaster projectionMaster) {
        return _projectionMaster.compareTo(projectionMaster);
    }

    @Override
    public int hashCode() {
        return _projectionMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ProjectionMaster> toCacheModel() {
        return _projectionMaster.toCacheModel();
    }

    @Override
    public ProjectionMaster toEscapedModel() {
        return new ProjectionMasterWrapper(_projectionMaster.toEscapedModel());
    }

    @Override
    public ProjectionMaster toUnescapedModel() {
        return new ProjectionMasterWrapper(_projectionMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _projectionMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _projectionMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _projectionMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProjectionMasterWrapper)) {
            return false;
        }

        ProjectionMasterWrapper projectionMasterWrapper = (ProjectionMasterWrapper) obj;

        if (Validator.equals(_projectionMaster,
                    projectionMasterWrapper._projectionMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProjectionMaster getWrappedProjectionMaster() {
        return _projectionMaster;
    }

    @Override
    public ProjectionMaster getWrappedModel() {
        return _projectionMaster;
    }

    @Override
    public void resetOriginalValues() {
        _projectionMaster.resetOriginalValues();
    }
}
