package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProjectionProdDetails}.
 * </p>
 *
 * @author
 * @see ProjectionProdDetails
 * @generated
 */
public class ProjectionProdDetailsWrapper implements ProjectionProdDetails,
    ModelWrapper<ProjectionProdDetails> {
    private ProjectionProdDetails _projectionProdDetails;

    public ProjectionProdDetailsWrapper(
        ProjectionProdDetails projectionProdDetails) {
        _projectionProdDetails = projectionProdDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionProdDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionProdDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("productName", getProductName());
        attributes.put("costCenter", getCostCenter());
        attributes.put("productNo", getProductNo());
        attributes.put("subLedgerCode", getSubLedgerCode());
        attributes.put("productDetailsId", getProductDetailsId());
        attributes.put("brandName", getBrandName());
        attributes.put("projectionId", getProjectionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String productName = (String) attributes.get("productName");

        if (productName != null) {
            setProductName(productName);
        }

        String costCenter = (String) attributes.get("costCenter");

        if (costCenter != null) {
            setCostCenter(costCenter);
        }

        String productNo = (String) attributes.get("productNo");

        if (productNo != null) {
            setProductNo(productNo);
        }

        String subLedgerCode = (String) attributes.get("subLedgerCode");

        if (subLedgerCode != null) {
            setSubLedgerCode(subLedgerCode);
        }

        Integer productDetailsId = (Integer) attributes.get("productDetailsId");

        if (productDetailsId != null) {
            setProductDetailsId(productDetailsId);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Integer projectionId = (Integer) attributes.get("projectionId");

        if (projectionId != null) {
            setProjectionId(projectionId);
        }
    }

    /**
    * Returns the primary key of this projection prod details.
    *
    * @return the primary key of this projection prod details
    */
    @Override
    public int getPrimaryKey() {
        return _projectionProdDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this projection prod details.
    *
    * @param primaryKey the primary key of this projection prod details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _projectionProdDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the product name of this projection prod details.
    *
    * @return the product name of this projection prod details
    */
    @Override
    public java.lang.String getProductName() {
        return _projectionProdDetails.getProductName();
    }

    /**
    * Sets the product name of this projection prod details.
    *
    * @param productName the product name of this projection prod details
    */
    @Override
    public void setProductName(java.lang.String productName) {
        _projectionProdDetails.setProductName(productName);
    }

    /**
    * Returns the cost center of this projection prod details.
    *
    * @return the cost center of this projection prod details
    */
    @Override
    public java.lang.String getCostCenter() {
        return _projectionProdDetails.getCostCenter();
    }

    /**
    * Sets the cost center of this projection prod details.
    *
    * @param costCenter the cost center of this projection prod details
    */
    @Override
    public void setCostCenter(java.lang.String costCenter) {
        _projectionProdDetails.setCostCenter(costCenter);
    }

    /**
    * Returns the product no of this projection prod details.
    *
    * @return the product no of this projection prod details
    */
    @Override
    public java.lang.String getProductNo() {
        return _projectionProdDetails.getProductNo();
    }

    /**
    * Sets the product no of this projection prod details.
    *
    * @param productNo the product no of this projection prod details
    */
    @Override
    public void setProductNo(java.lang.String productNo) {
        _projectionProdDetails.setProductNo(productNo);
    }

    /**
    * Returns the sub ledger code of this projection prod details.
    *
    * @return the sub ledger code of this projection prod details
    */
    @Override
    public java.lang.String getSubLedgerCode() {
        return _projectionProdDetails.getSubLedgerCode();
    }

    /**
    * Sets the sub ledger code of this projection prod details.
    *
    * @param subLedgerCode the sub ledger code of this projection prod details
    */
    @Override
    public void setSubLedgerCode(java.lang.String subLedgerCode) {
        _projectionProdDetails.setSubLedgerCode(subLedgerCode);
    }

    /**
    * Returns the product details ID of this projection prod details.
    *
    * @return the product details ID of this projection prod details
    */
    @Override
    public int getProductDetailsId() {
        return _projectionProdDetails.getProductDetailsId();
    }

    /**
    * Sets the product details ID of this projection prod details.
    *
    * @param productDetailsId the product details ID of this projection prod details
    */
    @Override
    public void setProductDetailsId(int productDetailsId) {
        _projectionProdDetails.setProductDetailsId(productDetailsId);
    }

    /**
    * Returns the brand name of this projection prod details.
    *
    * @return the brand name of this projection prod details
    */
    @Override
    public java.lang.String getBrandName() {
        return _projectionProdDetails.getBrandName();
    }

    /**
    * Sets the brand name of this projection prod details.
    *
    * @param brandName the brand name of this projection prod details
    */
    @Override
    public void setBrandName(java.lang.String brandName) {
        _projectionProdDetails.setBrandName(brandName);
    }

    /**
    * Returns the projection ID of this projection prod details.
    *
    * @return the projection ID of this projection prod details
    */
    @Override
    public int getProjectionId() {
        return _projectionProdDetails.getProjectionId();
    }

    /**
    * Sets the projection ID of this projection prod details.
    *
    * @param projectionId the projection ID of this projection prod details
    */
    @Override
    public void setProjectionId(int projectionId) {
        _projectionProdDetails.setProjectionId(projectionId);
    }

    @Override
    public boolean isNew() {
        return _projectionProdDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _projectionProdDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _projectionProdDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _projectionProdDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _projectionProdDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _projectionProdDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _projectionProdDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _projectionProdDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _projectionProdDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _projectionProdDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _projectionProdDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProjectionProdDetailsWrapper((ProjectionProdDetails) _projectionProdDetails.clone());
    }

    @Override
    public int compareTo(ProjectionProdDetails projectionProdDetails) {
        return _projectionProdDetails.compareTo(projectionProdDetails);
    }

    @Override
    public int hashCode() {
        return _projectionProdDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ProjectionProdDetails> toCacheModel() {
        return _projectionProdDetails.toCacheModel();
    }

    @Override
    public ProjectionProdDetails toEscapedModel() {
        return new ProjectionProdDetailsWrapper(_projectionProdDetails.toEscapedModel());
    }

    @Override
    public ProjectionProdDetails toUnescapedModel() {
        return new ProjectionProdDetailsWrapper(_projectionProdDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _projectionProdDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _projectionProdDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _projectionProdDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProjectionProdDetailsWrapper)) {
            return false;
        }

        ProjectionProdDetailsWrapper projectionProdDetailsWrapper = (ProjectionProdDetailsWrapper) obj;

        if (Validator.equals(_projectionProdDetails,
                    projectionProdDetailsWrapper._projectionProdDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProjectionProdDetails getWrappedProjectionProdDetails() {
        return _projectionProdDetails;
    }

    @Override
    public ProjectionProdDetails getWrappedModel() {
        return _projectionProdDetails;
    }

    @Override
    public void resetOriginalValues() {
        _projectionProdDetails.resetOriginalValues();
    }
}
