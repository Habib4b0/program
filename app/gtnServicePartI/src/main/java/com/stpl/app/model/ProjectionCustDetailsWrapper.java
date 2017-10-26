package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProjectionCustDetails}.
 * </p>
 *
 * @author
 * @see ProjectionCustDetails
 * @generated
 */
public class ProjectionCustDetailsWrapper implements ProjectionCustDetails,
    ModelWrapper<ProjectionCustDetails> {
    private ProjectionCustDetails _projectionCustDetails;

    public ProjectionCustDetailsWrapper(
        ProjectionCustDetails projectionCustDetails) {
        _projectionCustDetails = projectionCustDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionCustDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionCustDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("contractName", getContractName());
        attributes.put("customerName", getCustomerName());
        attributes.put("customerDetailsId", getCustomerDetailsId());
        attributes.put("costCenter", getCostCenter());
        attributes.put("customerAlias", getCustomerAlias());
        attributes.put("subLedgerCode", getSubLedgerCode());
        attributes.put("projectionId", getProjectionId());
        attributes.put("marketType", getMarketType());
        attributes.put("contractNo", getContractNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        String customerName = (String) attributes.get("customerName");

        if (customerName != null) {
            setCustomerName(customerName);
        }

        Integer customerDetailsId = (Integer) attributes.get(
                "customerDetailsId");

        if (customerDetailsId != null) {
            setCustomerDetailsId(customerDetailsId);
        }

        String costCenter = (String) attributes.get("costCenter");

        if (costCenter != null) {
            setCostCenter(costCenter);
        }

        String customerAlias = (String) attributes.get("customerAlias");

        if (customerAlias != null) {
            setCustomerAlias(customerAlias);
        }

        String subLedgerCode = (String) attributes.get("subLedgerCode");

        if (subLedgerCode != null) {
            setSubLedgerCode(subLedgerCode);
        }

        Integer projectionId = (Integer) attributes.get("projectionId");

        if (projectionId != null) {
            setProjectionId(projectionId);
        }

        String marketType = (String) attributes.get("marketType");

        if (marketType != null) {
            setMarketType(marketType);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }
    }

    /**
    * Returns the primary key of this projection cust details.
    *
    * @return the primary key of this projection cust details
    */
    @Override
    public int getPrimaryKey() {
        return _projectionCustDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this projection cust details.
    *
    * @param primaryKey the primary key of this projection cust details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _projectionCustDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the contract name of this projection cust details.
    *
    * @return the contract name of this projection cust details
    */
    @Override
    public java.lang.String getContractName() {
        return _projectionCustDetails.getContractName();
    }

    /**
    * Sets the contract name of this projection cust details.
    *
    * @param contractName the contract name of this projection cust details
    */
    @Override
    public void setContractName(java.lang.String contractName) {
        _projectionCustDetails.setContractName(contractName);
    }

    /**
    * Returns the customer name of this projection cust details.
    *
    * @return the customer name of this projection cust details
    */
    @Override
    public java.lang.String getCustomerName() {
        return _projectionCustDetails.getCustomerName();
    }

    /**
    * Sets the customer name of this projection cust details.
    *
    * @param customerName the customer name of this projection cust details
    */
    @Override
    public void setCustomerName(java.lang.String customerName) {
        _projectionCustDetails.setCustomerName(customerName);
    }

    /**
    * Returns the customer details ID of this projection cust details.
    *
    * @return the customer details ID of this projection cust details
    */
    @Override
    public int getCustomerDetailsId() {
        return _projectionCustDetails.getCustomerDetailsId();
    }

    /**
    * Sets the customer details ID of this projection cust details.
    *
    * @param customerDetailsId the customer details ID of this projection cust details
    */
    @Override
    public void setCustomerDetailsId(int customerDetailsId) {
        _projectionCustDetails.setCustomerDetailsId(customerDetailsId);
    }

    /**
    * Returns the cost center of this projection cust details.
    *
    * @return the cost center of this projection cust details
    */
    @Override
    public java.lang.String getCostCenter() {
        return _projectionCustDetails.getCostCenter();
    }

    /**
    * Sets the cost center of this projection cust details.
    *
    * @param costCenter the cost center of this projection cust details
    */
    @Override
    public void setCostCenter(java.lang.String costCenter) {
        _projectionCustDetails.setCostCenter(costCenter);
    }

    /**
    * Returns the customer alias of this projection cust details.
    *
    * @return the customer alias of this projection cust details
    */
    @Override
    public java.lang.String getCustomerAlias() {
        return _projectionCustDetails.getCustomerAlias();
    }

    /**
    * Sets the customer alias of this projection cust details.
    *
    * @param customerAlias the customer alias of this projection cust details
    */
    @Override
    public void setCustomerAlias(java.lang.String customerAlias) {
        _projectionCustDetails.setCustomerAlias(customerAlias);
    }

    /**
    * Returns the sub ledger code of this projection cust details.
    *
    * @return the sub ledger code of this projection cust details
    */
    @Override
    public java.lang.String getSubLedgerCode() {
        return _projectionCustDetails.getSubLedgerCode();
    }

    /**
    * Sets the sub ledger code of this projection cust details.
    *
    * @param subLedgerCode the sub ledger code of this projection cust details
    */
    @Override
    public void setSubLedgerCode(java.lang.String subLedgerCode) {
        _projectionCustDetails.setSubLedgerCode(subLedgerCode);
    }

    /**
    * Returns the projection ID of this projection cust details.
    *
    * @return the projection ID of this projection cust details
    */
    @Override
    public int getProjectionId() {
        return _projectionCustDetails.getProjectionId();
    }

    /**
    * Sets the projection ID of this projection cust details.
    *
    * @param projectionId the projection ID of this projection cust details
    */
    @Override
    public void setProjectionId(int projectionId) {
        _projectionCustDetails.setProjectionId(projectionId);
    }

    /**
    * Returns the market type of this projection cust details.
    *
    * @return the market type of this projection cust details
    */
    @Override
    public java.lang.String getMarketType() {
        return _projectionCustDetails.getMarketType();
    }

    /**
    * Sets the market type of this projection cust details.
    *
    * @param marketType the market type of this projection cust details
    */
    @Override
    public void setMarketType(java.lang.String marketType) {
        _projectionCustDetails.setMarketType(marketType);
    }

    /**
    * Returns the contract no of this projection cust details.
    *
    * @return the contract no of this projection cust details
    */
    @Override
    public java.lang.String getContractNo() {
        return _projectionCustDetails.getContractNo();
    }

    /**
    * Sets the contract no of this projection cust details.
    *
    * @param contractNo the contract no of this projection cust details
    */
    @Override
    public void setContractNo(java.lang.String contractNo) {
        _projectionCustDetails.setContractNo(contractNo);
    }

    @Override
    public boolean isNew() {
        return _projectionCustDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _projectionCustDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _projectionCustDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _projectionCustDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _projectionCustDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _projectionCustDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _projectionCustDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _projectionCustDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _projectionCustDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _projectionCustDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _projectionCustDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProjectionCustDetailsWrapper((ProjectionCustDetails) _projectionCustDetails.clone());
    }

    @Override
    public int compareTo(ProjectionCustDetails projectionCustDetails) {
        return _projectionCustDetails.compareTo(projectionCustDetails);
    }

    @Override
    public int hashCode() {
        return _projectionCustDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ProjectionCustDetails> toCacheModel() {
        return _projectionCustDetails.toCacheModel();
    }

    @Override
    public ProjectionCustDetails toEscapedModel() {
        return new ProjectionCustDetailsWrapper(_projectionCustDetails.toEscapedModel());
    }

    @Override
    public ProjectionCustDetails toUnescapedModel() {
        return new ProjectionCustDetailsWrapper(_projectionCustDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _projectionCustDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _projectionCustDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _projectionCustDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProjectionCustDetailsWrapper)) {
            return false;
        }

        ProjectionCustDetailsWrapper projectionCustDetailsWrapper = (ProjectionCustDetailsWrapper) obj;

        if (Validator.equals(_projectionCustDetails,
                    projectionCustDetailsWrapper._projectionCustDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProjectionCustDetails getWrappedProjectionCustDetails() {
        return _projectionCustDetails;
    }

    @Override
    public ProjectionCustDetails getWrappedModel() {
        return _projectionCustDetails;
    }

    @Override
    public void resetOriginalValues() {
        _projectionCustDetails.resetOriginalValues();
    }
}
