package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ChSalesProjection}.
 * </p>
 *
 * @author
 * @see ChSalesProjection
 * @generated
 */
public class ChSalesProjectionWrapper implements ChSalesProjection,
    ModelWrapper<ChSalesProjection> {
    private ChSalesProjection _chSalesProjection;

    public ChSalesProjectionWrapper(ChSalesProjection chSalesProjection) {
        _chSalesProjection = chSalesProjection;
    }

    @Override
    public Class<?> getModelClass() {
        return ChSalesProjection.class;
    }

    @Override
    public String getModelClassName() {
        return ChSalesProjection.class.getName();
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
    * Returns the primary key of this ch sales projection.
    *
    * @return the primary key of this ch sales projection
    */
    @Override
    public com.stpl.app.service.persistence.ChSalesProjectionPK getPrimaryKey() {
        return _chSalesProjection.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ch sales projection.
    *
    * @param primaryKey the primary key of this ch sales projection
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.ChSalesProjectionPK primaryKey) {
        _chSalesProjection.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the contract units of this ch sales projection.
    *
    * @return the contract units of this ch sales projection
    */
    @Override
    public double getContractUnits() {
        return _chSalesProjection.getContractUnits();
    }

    /**
    * Sets the contract units of this ch sales projection.
    *
    * @param contractUnits the contract units of this ch sales projection
    */
    @Override
    public void setContractUnits(double contractUnits) {
        _chSalesProjection.setContractUnits(contractUnits);
    }

    /**
    * Returns the per of business of this ch sales projection.
    *
    * @return the per of business of this ch sales projection
    */
    @Override
    public double getPerOfBusiness() {
        return _chSalesProjection.getPerOfBusiness();
    }

    /**
    * Sets the per of business of this ch sales projection.
    *
    * @param perOfBusiness the per of business of this ch sales projection
    */
    @Override
    public void setPerOfBusiness(double perOfBusiness) {
        _chSalesProjection.setPerOfBusiness(perOfBusiness);
    }

    /**
    * Returns the period sid of this ch sales projection.
    *
    * @return the period sid of this ch sales projection
    */
    @Override
    public int getPeriodSid() {
        return _chSalesProjection.getPeriodSid();
    }

    /**
    * Sets the period sid of this ch sales projection.
    *
    * @param periodSid the period sid of this ch sales projection
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _chSalesProjection.setPeriodSid(periodSid);
    }

    /**
    * Returns the contract sales of this ch sales projection.
    *
    * @return the contract sales of this ch sales projection
    */
    @Override
    public double getContractSales() {
        return _chSalesProjection.getContractSales();
    }

    /**
    * Sets the contract sales of this ch sales projection.
    *
    * @param contractSales the contract sales of this ch sales projection
    */
    @Override
    public void setContractSales(double contractSales) {
        _chSalesProjection.setContractSales(contractSales);
    }

    /**
    * Returns the projection details sid of this ch sales projection.
    *
    * @return the projection details sid of this ch sales projection
    */
    @Override
    public int getProjectionDetailsSid() {
        return _chSalesProjection.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this ch sales projection.
    *
    * @param projectionDetailsSid the projection details sid of this ch sales projection
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _chSalesProjection.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the gts sales of this ch sales projection.
    *
    * @return the gts sales of this ch sales projection
    */
    @Override
    public double getGtsSales() {
        return _chSalesProjection.getGtsSales();
    }

    /**
    * Sets the gts sales of this ch sales projection.
    *
    * @param gtsSales the gts sales of this ch sales projection
    */
    @Override
    public void setGtsSales(double gtsSales) {
        _chSalesProjection.setGtsSales(gtsSales);
    }

    @Override
    public boolean isNew() {
        return _chSalesProjection.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _chSalesProjection.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _chSalesProjection.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _chSalesProjection.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _chSalesProjection.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _chSalesProjection.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _chSalesProjection.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _chSalesProjection.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _chSalesProjection.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _chSalesProjection.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _chSalesProjection.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ChSalesProjectionWrapper((ChSalesProjection) _chSalesProjection.clone());
    }

    @Override
    public int compareTo(ChSalesProjection chSalesProjection) {
        return _chSalesProjection.compareTo(chSalesProjection);
    }

    @Override
    public int hashCode() {
        return _chSalesProjection.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ChSalesProjection> toCacheModel() {
        return _chSalesProjection.toCacheModel();
    }

    @Override
    public ChSalesProjection toEscapedModel() {
        return new ChSalesProjectionWrapper(_chSalesProjection.toEscapedModel());
    }

    @Override
    public ChSalesProjection toUnescapedModel() {
        return new ChSalesProjectionWrapper(_chSalesProjection.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _chSalesProjection.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _chSalesProjection.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _chSalesProjection.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChSalesProjectionWrapper)) {
            return false;
        }

        ChSalesProjectionWrapper chSalesProjectionWrapper = (ChSalesProjectionWrapper) obj;

        if (Validator.equals(_chSalesProjection,
                    chSalesProjectionWrapper._chSalesProjection)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ChSalesProjection getWrappedChSalesProjection() {
        return _chSalesProjection;
    }

    @Override
    public ChSalesProjection getWrappedModel() {
        return _chSalesProjection;
    }

    @Override
    public void resetOriginalValues() {
        _chSalesProjection.resetOriginalValues();
    }
}
