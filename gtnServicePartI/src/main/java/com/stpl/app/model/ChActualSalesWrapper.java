package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ChActualSales}.
 * </p>
 *
 * @author
 * @see ChActualSales
 * @generated
 */
public class ChActualSalesWrapper implements ChActualSales,
    ModelWrapper<ChActualSales> {
    private ChActualSales _chActualSales;

    public ChActualSalesWrapper(ChActualSales chActualSales) {
        _chActualSales = chActualSales;
    }

    @Override
    public Class<?> getModelClass() {
        return ChActualSales.class;
    }

    @Override
    public String getModelClassName() {
        return ChActualSales.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("contractUnits", getContractUnits());
        attributes.put("perOfBusiness", getPerOfBusiness());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("contractSales", getContractSales());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("gtsSales", getGtsSales());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double contractUnits = (Double) attributes.get("contractUnits");

        if (contractUnits != null) {
            setContractUnits(contractUnits);
        }

        Double perOfBusiness = (Double) attributes.get("perOfBusiness");

        if (perOfBusiness != null) {
            setPerOfBusiness(perOfBusiness);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double contractSales = (Double) attributes.get("contractSales");

        if (contractSales != null) {
            setContractSales(contractSales);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double gtsSales = (Double) attributes.get("gtsSales");

        if (gtsSales != null) {
            setGtsSales(gtsSales);
        }
    }

    /**
    * Returns the primary key of this ch actual sales.
    *
    * @return the primary key of this ch actual sales
    */
    @Override
    public com.stpl.app.service.persistence.ChActualSalesPK getPrimaryKey() {
        return _chActualSales.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ch actual sales.
    *
    * @param primaryKey the primary key of this ch actual sales
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.ChActualSalesPK primaryKey) {
        _chActualSales.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the contract units of this ch actual sales.
    *
    * @return the contract units of this ch actual sales
    */
    @Override
    public double getContractUnits() {
        return _chActualSales.getContractUnits();
    }

    /**
    * Sets the contract units of this ch actual sales.
    *
    * @param contractUnits the contract units of this ch actual sales
    */
    @Override
    public void setContractUnits(double contractUnits) {
        _chActualSales.setContractUnits(contractUnits);
    }

    /**
    * Returns the per of business of this ch actual sales.
    *
    * @return the per of business of this ch actual sales
    */
    @Override
    public double getPerOfBusiness() {
        return _chActualSales.getPerOfBusiness();
    }

    /**
    * Sets the per of business of this ch actual sales.
    *
    * @param perOfBusiness the per of business of this ch actual sales
    */
    @Override
    public void setPerOfBusiness(double perOfBusiness) {
        _chActualSales.setPerOfBusiness(perOfBusiness);
    }

    /**
    * Returns the period sid of this ch actual sales.
    *
    * @return the period sid of this ch actual sales
    */
    @Override
    public int getPeriodSid() {
        return _chActualSales.getPeriodSid();
    }

    /**
    * Sets the period sid of this ch actual sales.
    *
    * @param periodSid the period sid of this ch actual sales
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _chActualSales.setPeriodSid(periodSid);
    }

    /**
    * Returns the contract sales of this ch actual sales.
    *
    * @return the contract sales of this ch actual sales
    */
    @Override
    public double getContractSales() {
        return _chActualSales.getContractSales();
    }

    /**
    * Sets the contract sales of this ch actual sales.
    *
    * @param contractSales the contract sales of this ch actual sales
    */
    @Override
    public void setContractSales(double contractSales) {
        _chActualSales.setContractSales(contractSales);
    }

    /**
    * Returns the projection details sid of this ch actual sales.
    *
    * @return the projection details sid of this ch actual sales
    */
    @Override
    public int getProjectionDetailsSid() {
        return _chActualSales.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this ch actual sales.
    *
    * @param projectionDetailsSid the projection details sid of this ch actual sales
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _chActualSales.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the gts sales of this ch actual sales.
    *
    * @return the gts sales of this ch actual sales
    */
    @Override
    public double getGtsSales() {
        return _chActualSales.getGtsSales();
    }

    /**
    * Sets the gts sales of this ch actual sales.
    *
    * @param gtsSales the gts sales of this ch actual sales
    */
    @Override
    public void setGtsSales(double gtsSales) {
        _chActualSales.setGtsSales(gtsSales);
    }

    @Override
    public boolean isNew() {
        return _chActualSales.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _chActualSales.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _chActualSales.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _chActualSales.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _chActualSales.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _chActualSales.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _chActualSales.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _chActualSales.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _chActualSales.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _chActualSales.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _chActualSales.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ChActualSalesWrapper((ChActualSales) _chActualSales.clone());
    }

    @Override
    public int compareTo(ChActualSales chActualSales) {
        return _chActualSales.compareTo(chActualSales);
    }

    @Override
    public int hashCode() {
        return _chActualSales.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ChActualSales> toCacheModel() {
        return _chActualSales.toCacheModel();
    }

    @Override
    public ChActualSales toEscapedModel() {
        return new ChActualSalesWrapper(_chActualSales.toEscapedModel());
    }

    @Override
    public ChActualSales toUnescapedModel() {
        return new ChActualSalesWrapper(_chActualSales.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _chActualSales.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _chActualSales.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _chActualSales.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChActualSalesWrapper)) {
            return false;
        }

        ChActualSalesWrapper chActualSalesWrapper = (ChActualSalesWrapper) obj;

        if (Validator.equals(_chActualSales, chActualSalesWrapper._chActualSales)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ChActualSales getWrappedChActualSales() {
        return _chActualSales;
    }

    @Override
    public ChActualSales getWrappedModel() {
        return _chActualSales;
    }

    @Override
    public void resetOriginalValues() {
        _chActualSales.resetOriginalValues();
    }
}
