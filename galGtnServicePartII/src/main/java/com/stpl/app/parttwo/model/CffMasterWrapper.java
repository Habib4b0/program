package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CffMaster}.
 * </p>
 *
 * @author
 * @see CffMaster
 * @generated
 */
public class CffMasterWrapper implements CffMaster, ModelWrapper<CffMaster> {
    private CffMaster _cffMaster;

    public CffMasterWrapper(CffMaster cffMaster) {
        _cffMaster = cffMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return CffMaster.class;
    }

    @Override
    public String getModelClassName() {
        return CffMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("productHierarchyLevel", getProductHierarchyLevel());
        attributes.put("activeFromDate", getActiveFromDate());
        attributes.put("cffType", getCffType());
        attributes.put("cffOfficial", getCffOfficial());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("productHierVersionNo", getProductHierVersionNo());
        attributes.put("activeToDate", getActiveToDate());
        attributes.put("customerHierVersionNo", getCustomerHierVersionNo());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("customerHierarchyLevel", getCustomerHierarchyLevel());
        attributes.put("productHierarchySid", getProductHierarchySid());
        attributes.put("cffName", getCffName());
        attributes.put("customerHierarchyInnerLevel",
            getCustomerHierarchyInnerLevel());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("customerHierarchySid", getCustomerHierarchySid());
        attributes.put("companyGroupSid", getCompanyGroupSid());
        attributes.put("prodRelationshipBuilderSid",
            getProdRelationshipBuilderSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("productHierarchyInnerLevel",
            getProductHierarchyInnerLevel());
        attributes.put("itemGroupSid", getItemGroupSid());
        attributes.put("custRelationshipBuilderSid",
            getCustRelationshipBuilderSid());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer productHierarchyLevel = (Integer) attributes.get(
                "productHierarchyLevel");

        if (productHierarchyLevel != null) {
            setProductHierarchyLevel(productHierarchyLevel);
        }

        Date activeFromDate = (Date) attributes.get("activeFromDate");

        if (activeFromDate != null) {
            setActiveFromDate(activeFromDate);
        }

        Integer cffType = (Integer) attributes.get("cffType");

        if (cffType != null) {
            setCffType(cffType);
        }

        Boolean cffOfficial = (Boolean) attributes.get("cffOfficial");

        if (cffOfficial != null) {
            setCffOfficial(cffOfficial);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer productHierVersionNo = (Integer) attributes.get(
                "productHierVersionNo");

        if (productHierVersionNo != null) {
            setProductHierVersionNo(productHierVersionNo);
        }

        Date activeToDate = (Date) attributes.get("activeToDate");

        if (activeToDate != null) {
            setActiveToDate(activeToDate);
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

        Integer productHierarchySid = (Integer) attributes.get(
                "productHierarchySid");

        if (productHierarchySid != null) {
            setProductHierarchySid(productHierarchySid);
        }

        String cffName = (String) attributes.get("cffName");

        if (cffName != null) {
            setCffName(cffName);
        }

        Integer customerHierarchyInnerLevel = (Integer) attributes.get(
                "customerHierarchyInnerLevel");

        if (customerHierarchyInnerLevel != null) {
            setCustomerHierarchyInnerLevel(customerHierarchyInnerLevel);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer customerHierarchySid = (Integer) attributes.get(
                "customerHierarchySid");

        if (customerHierarchySid != null) {
            setCustomerHierarchySid(customerHierarchySid);
        }

        Integer companyGroupSid = (Integer) attributes.get("companyGroupSid");

        if (companyGroupSid != null) {
            setCompanyGroupSid(companyGroupSid);
        }

        Integer prodRelationshipBuilderSid = (Integer) attributes.get(
                "prodRelationshipBuilderSid");

        if (prodRelationshipBuilderSid != null) {
            setProdRelationshipBuilderSid(prodRelationshipBuilderSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer productHierarchyInnerLevel = (Integer) attributes.get(
                "productHierarchyInnerLevel");

        if (productHierarchyInnerLevel != null) {
            setProductHierarchyInnerLevel(productHierarchyInnerLevel);
        }

        Integer itemGroupSid = (Integer) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
        }

        Integer custRelationshipBuilderSid = (Integer) attributes.get(
                "custRelationshipBuilderSid");

        if (custRelationshipBuilderSid != null) {
            setCustRelationshipBuilderSid(custRelationshipBuilderSid);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
    }

    /**
    * Returns the primary key of this cff master.
    *
    * @return the primary key of this cff master
    */
    @Override
    public int getPrimaryKey() {
        return _cffMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cff master.
    *
    * @param primaryKey the primary key of this cff master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cffMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the product hierarchy level of this cff master.
    *
    * @return the product hierarchy level of this cff master
    */
    @Override
    public int getProductHierarchyLevel() {
        return _cffMaster.getProductHierarchyLevel();
    }

    /**
    * Sets the product hierarchy level of this cff master.
    *
    * @param productHierarchyLevel the product hierarchy level of this cff master
    */
    @Override
    public void setProductHierarchyLevel(int productHierarchyLevel) {
        _cffMaster.setProductHierarchyLevel(productHierarchyLevel);
    }

    /**
    * Returns the active from date of this cff master.
    *
    * @return the active from date of this cff master
    */
    @Override
    public java.util.Date getActiveFromDate() {
        return _cffMaster.getActiveFromDate();
    }

    /**
    * Sets the active from date of this cff master.
    *
    * @param activeFromDate the active from date of this cff master
    */
    @Override
    public void setActiveFromDate(java.util.Date activeFromDate) {
        _cffMaster.setActiveFromDate(activeFromDate);
    }

    /**
    * Returns the cff type of this cff master.
    *
    * @return the cff type of this cff master
    */
    @Override
    public int getCffType() {
        return _cffMaster.getCffType();
    }

    /**
    * Sets the cff type of this cff master.
    *
    * @param cffType the cff type of this cff master
    */
    @Override
    public void setCffType(int cffType) {
        _cffMaster.setCffType(cffType);
    }

    /**
    * Returns the cff official of this cff master.
    *
    * @return the cff official of this cff master
    */
    @Override
    public boolean getCffOfficial() {
        return _cffMaster.getCffOfficial();
    }

    /**
    * Returns <code>true</code> if this cff master is cff official.
    *
    * @return <code>true</code> if this cff master is cff official; <code>false</code> otherwise
    */
    @Override
    public boolean isCffOfficial() {
        return _cffMaster.isCffOfficial();
    }

    /**
    * Sets whether this cff master is cff official.
    *
    * @param cffOfficial the cff official of this cff master
    */
    @Override
    public void setCffOfficial(boolean cffOfficial) {
        _cffMaster.setCffOfficial(cffOfficial);
    }

    /**
    * Returns the cff master sid of this cff master.
    *
    * @return the cff master sid of this cff master
    */
    @Override
    public int getCffMasterSid() {
        return _cffMaster.getCffMasterSid();
    }

    /**
    * Sets the cff master sid of this cff master.
    *
    * @param cffMasterSid the cff master sid of this cff master
    */
    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffMaster.setCffMasterSid(cffMasterSid);
    }

    /**
    * Returns the product hier version no of this cff master.
    *
    * @return the product hier version no of this cff master
    */
    @Override
    public int getProductHierVersionNo() {
        return _cffMaster.getProductHierVersionNo();
    }

    /**
    * Sets the product hier version no of this cff master.
    *
    * @param productHierVersionNo the product hier version no of this cff master
    */
    @Override
    public void setProductHierVersionNo(int productHierVersionNo) {
        _cffMaster.setProductHierVersionNo(productHierVersionNo);
    }

    /**
    * Returns the active to date of this cff master.
    *
    * @return the active to date of this cff master
    */
    @Override
    public java.util.Date getActiveToDate() {
        return _cffMaster.getActiveToDate();
    }

    /**
    * Sets the active to date of this cff master.
    *
    * @param activeToDate the active to date of this cff master
    */
    @Override
    public void setActiveToDate(java.util.Date activeToDate) {
        _cffMaster.setActiveToDate(activeToDate);
    }

    /**
    * Returns the customer hier version no of this cff master.
    *
    * @return the customer hier version no of this cff master
    */
    @Override
    public int getCustomerHierVersionNo() {
        return _cffMaster.getCustomerHierVersionNo();
    }

    /**
    * Sets the customer hier version no of this cff master.
    *
    * @param customerHierVersionNo the customer hier version no of this cff master
    */
    @Override
    public void setCustomerHierVersionNo(int customerHierVersionNo) {
        _cffMaster.setCustomerHierVersionNo(customerHierVersionNo);
    }

    /**
    * Returns the modified date of this cff master.
    *
    * @return the modified date of this cff master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _cffMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this cff master.
    *
    * @param modifiedDate the modified date of this cff master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _cffMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the customer hierarchy level of this cff master.
    *
    * @return the customer hierarchy level of this cff master
    */
    @Override
    public int getCustomerHierarchyLevel() {
        return _cffMaster.getCustomerHierarchyLevel();
    }

    /**
    * Sets the customer hierarchy level of this cff master.
    *
    * @param customerHierarchyLevel the customer hierarchy level of this cff master
    */
    @Override
    public void setCustomerHierarchyLevel(int customerHierarchyLevel) {
        _cffMaster.setCustomerHierarchyLevel(customerHierarchyLevel);
    }

    /**
    * Returns the product hierarchy sid of this cff master.
    *
    * @return the product hierarchy sid of this cff master
    */
    @Override
    public int getProductHierarchySid() {
        return _cffMaster.getProductHierarchySid();
    }

    /**
    * Sets the product hierarchy sid of this cff master.
    *
    * @param productHierarchySid the product hierarchy sid of this cff master
    */
    @Override
    public void setProductHierarchySid(int productHierarchySid) {
        _cffMaster.setProductHierarchySid(productHierarchySid);
    }

    /**
    * Returns the cff name of this cff master.
    *
    * @return the cff name of this cff master
    */
    @Override
    public java.lang.String getCffName() {
        return _cffMaster.getCffName();
    }

    /**
    * Sets the cff name of this cff master.
    *
    * @param cffName the cff name of this cff master
    */
    @Override
    public void setCffName(java.lang.String cffName) {
        _cffMaster.setCffName(cffName);
    }

    /**
    * Returns the customer hierarchy inner level of this cff master.
    *
    * @return the customer hierarchy inner level of this cff master
    */
    @Override
    public int getCustomerHierarchyInnerLevel() {
        return _cffMaster.getCustomerHierarchyInnerLevel();
    }

    /**
    * Sets the customer hierarchy inner level of this cff master.
    *
    * @param customerHierarchyInnerLevel the customer hierarchy inner level of this cff master
    */
    @Override
    public void setCustomerHierarchyInnerLevel(int customerHierarchyInnerLevel) {
        _cffMaster.setCustomerHierarchyInnerLevel(customerHierarchyInnerLevel);
    }

    /**
    * Returns the created date of this cff master.
    *
    * @return the created date of this cff master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _cffMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this cff master.
    *
    * @param createdDate the created date of this cff master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _cffMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this cff master.
    *
    * @return the created by of this cff master
    */
    @Override
    public int getCreatedBy() {
        return _cffMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this cff master.
    *
    * @param createdBy the created by of this cff master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _cffMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the customer hierarchy sid of this cff master.
    *
    * @return the customer hierarchy sid of this cff master
    */
    @Override
    public int getCustomerHierarchySid() {
        return _cffMaster.getCustomerHierarchySid();
    }

    /**
    * Sets the customer hierarchy sid of this cff master.
    *
    * @param customerHierarchySid the customer hierarchy sid of this cff master
    */
    @Override
    public void setCustomerHierarchySid(int customerHierarchySid) {
        _cffMaster.setCustomerHierarchySid(customerHierarchySid);
    }

    /**
    * Returns the company group sid of this cff master.
    *
    * @return the company group sid of this cff master
    */
    @Override
    public int getCompanyGroupSid() {
        return _cffMaster.getCompanyGroupSid();
    }

    /**
    * Sets the company group sid of this cff master.
    *
    * @param companyGroupSid the company group sid of this cff master
    */
    @Override
    public void setCompanyGroupSid(int companyGroupSid) {
        _cffMaster.setCompanyGroupSid(companyGroupSid);
    }

    /**
    * Returns the prod relationship builder sid of this cff master.
    *
    * @return the prod relationship builder sid of this cff master
    */
    @Override
    public int getProdRelationshipBuilderSid() {
        return _cffMaster.getProdRelationshipBuilderSid();
    }

    /**
    * Sets the prod relationship builder sid of this cff master.
    *
    * @param prodRelationshipBuilderSid the prod relationship builder sid of this cff master
    */
    @Override
    public void setProdRelationshipBuilderSid(int prodRelationshipBuilderSid) {
        _cffMaster.setProdRelationshipBuilderSid(prodRelationshipBuilderSid);
    }

    /**
    * Returns the modified by of this cff master.
    *
    * @return the modified by of this cff master
    */
    @Override
    public int getModifiedBy() {
        return _cffMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this cff master.
    *
    * @param modifiedBy the modified by of this cff master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _cffMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this cff master.
    *
    * @return the inbound status of this cff master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _cffMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this cff master.
    *
    * @param inboundStatus the inbound status of this cff master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _cffMaster.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the product hierarchy inner level of this cff master.
    *
    * @return the product hierarchy inner level of this cff master
    */
    @Override
    public int getProductHierarchyInnerLevel() {
        return _cffMaster.getProductHierarchyInnerLevel();
    }

    /**
    * Sets the product hierarchy inner level of this cff master.
    *
    * @param productHierarchyInnerLevel the product hierarchy inner level of this cff master
    */
    @Override
    public void setProductHierarchyInnerLevel(int productHierarchyInnerLevel) {
        _cffMaster.setProductHierarchyInnerLevel(productHierarchyInnerLevel);
    }

    /**
    * Returns the item group sid of this cff master.
    *
    * @return the item group sid of this cff master
    */
    @Override
    public int getItemGroupSid() {
        return _cffMaster.getItemGroupSid();
    }

    /**
    * Sets the item group sid of this cff master.
    *
    * @param itemGroupSid the item group sid of this cff master
    */
    @Override
    public void setItemGroupSid(int itemGroupSid) {
        _cffMaster.setItemGroupSid(itemGroupSid);
    }

    /**
    * Returns the cust relationship builder sid of this cff master.
    *
    * @return the cust relationship builder sid of this cff master
    */
    @Override
    public int getCustRelationshipBuilderSid() {
        return _cffMaster.getCustRelationshipBuilderSid();
    }

    /**
    * Sets the cust relationship builder sid of this cff master.
    *
    * @param custRelationshipBuilderSid the cust relationship builder sid of this cff master
    */
    @Override
    public void setCustRelationshipBuilderSid(int custRelationshipBuilderSid) {
        _cffMaster.setCustRelationshipBuilderSid(custRelationshipBuilderSid);
    }

    /**
    * Returns the company master sid of this cff master.
    *
    * @return the company master sid of this cff master
    */
    @Override
    public int getCompanyMasterSid() {
        return _cffMaster.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this cff master.
    *
    * @param companyMasterSid the company master sid of this cff master
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _cffMaster.setCompanyMasterSid(companyMasterSid);
    }

    @Override
    public boolean isNew() {
        return _cffMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cffMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cffMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cffMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cffMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cffMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cffMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cffMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cffMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cffMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cffMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CffMasterWrapper((CffMaster) _cffMaster.clone());
    }

    @Override
    public int compareTo(CffMaster cffMaster) {
        return _cffMaster.compareTo(cffMaster);
    }

    @Override
    public int hashCode() {
        return _cffMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CffMaster> toCacheModel() {
        return _cffMaster.toCacheModel();
    }

    @Override
    public CffMaster toEscapedModel() {
        return new CffMasterWrapper(_cffMaster.toEscapedModel());
    }

    @Override
    public CffMaster toUnescapedModel() {
        return new CffMasterWrapper(_cffMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cffMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cffMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cffMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CffMasterWrapper)) {
            return false;
        }

        CffMasterWrapper cffMasterWrapper = (CffMasterWrapper) obj;

        if (Validator.equals(_cffMaster, cffMasterWrapper._cffMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CffMaster getWrappedCffMaster() {
        return _cffMaster;
    }

    @Override
    public CffMaster getWrappedModel() {
        return _cffMaster;
    }

    @Override
    public void resetOriginalValues() {
        _cffMaster.resetOriginalValues();
    }
}
