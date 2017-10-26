package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NationalAssumptions}.
 * </p>
 *
 * @author
 * @see NationalAssumptions
 * @generated
 */
public class NationalAssumptionsWrapper implements NationalAssumptions,
    ModelWrapper<NationalAssumptions> {
    private NationalAssumptions _nationalAssumptions;

    public NationalAssumptionsWrapper(NationalAssumptions nationalAssumptions) {
        _nationalAssumptions = nationalAssumptions;
    }

    @Override
    public Class<?> getModelClass() {
        return NationalAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return NationalAssumptions.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("baselinePeriod", getBaselinePeriod());
        attributes.put("frequency", getFrequency());
        attributes.put("startPeriod", getStartPeriod());
        attributes.put("forecastMethodology", getForecastMethodology());
        attributes.put("priceType", getPriceType());
        attributes.put("endPeriod", getEndPeriod());
        attributes.put("priceBasis", getPriceBasis());
        attributes.put("naProjMasterSid", getNaProjMasterSid());
        attributes.put("rollingPeriod", getRollingPeriod());
        attributes.put("baselineMethodology", getBaselineMethodology());
        attributes.put("growthRate", getGrowthRate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String baselinePeriod = (String) attributes.get("baselinePeriod");

        if (baselinePeriod != null) {
            setBaselinePeriod(baselinePeriod);
        }

        String frequency = (String) attributes.get("frequency");

        if (frequency != null) {
            setFrequency(frequency);
        }

        String startPeriod = (String) attributes.get("startPeriod");

        if (startPeriod != null) {
            setStartPeriod(startPeriod);
        }

        String forecastMethodology = (String) attributes.get(
                "forecastMethodology");

        if (forecastMethodology != null) {
            setForecastMethodology(forecastMethodology);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        String endPeriod = (String) attributes.get("endPeriod");

        if (endPeriod != null) {
            setEndPeriod(endPeriod);
        }

        String priceBasis = (String) attributes.get("priceBasis");

        if (priceBasis != null) {
            setPriceBasis(priceBasis);
        }

        Integer naProjMasterSid = (Integer) attributes.get("naProjMasterSid");

        if (naProjMasterSid != null) {
            setNaProjMasterSid(naProjMasterSid);
        }

        String rollingPeriod = (String) attributes.get("rollingPeriod");

        if (rollingPeriod != null) {
            setRollingPeriod(rollingPeriod);
        }

        String baselineMethodology = (String) attributes.get(
                "baselineMethodology");

        if (baselineMethodology != null) {
            setBaselineMethodology(baselineMethodology);
        }

        Double growthRate = (Double) attributes.get("growthRate");

        if (growthRate != null) {
            setGrowthRate(growthRate);
        }
    }

    /**
    * Returns the primary key of this national assumptions.
    *
    * @return the primary key of this national assumptions
    */
    @Override
    public com.stpl.app.service.persistence.NationalAssumptionsPK getPrimaryKey() {
        return _nationalAssumptions.getPrimaryKey();
    }

    /**
    * Sets the primary key of this national assumptions.
    *
    * @param primaryKey the primary key of this national assumptions
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.NationalAssumptionsPK primaryKey) {
        _nationalAssumptions.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the baseline period of this national assumptions.
    *
    * @return the baseline period of this national assumptions
    */
    @Override
    public java.lang.String getBaselinePeriod() {
        return _nationalAssumptions.getBaselinePeriod();
    }

    /**
    * Sets the baseline period of this national assumptions.
    *
    * @param baselinePeriod the baseline period of this national assumptions
    */
    @Override
    public void setBaselinePeriod(java.lang.String baselinePeriod) {
        _nationalAssumptions.setBaselinePeriod(baselinePeriod);
    }

    /**
    * Returns the frequency of this national assumptions.
    *
    * @return the frequency of this national assumptions
    */
    @Override
    public java.lang.String getFrequency() {
        return _nationalAssumptions.getFrequency();
    }

    /**
    * Sets the frequency of this national assumptions.
    *
    * @param frequency the frequency of this national assumptions
    */
    @Override
    public void setFrequency(java.lang.String frequency) {
        _nationalAssumptions.setFrequency(frequency);
    }

    /**
    * Returns the start period of this national assumptions.
    *
    * @return the start period of this national assumptions
    */
    @Override
    public java.lang.String getStartPeriod() {
        return _nationalAssumptions.getStartPeriod();
    }

    /**
    * Sets the start period of this national assumptions.
    *
    * @param startPeriod the start period of this national assumptions
    */
    @Override
    public void setStartPeriod(java.lang.String startPeriod) {
        _nationalAssumptions.setStartPeriod(startPeriod);
    }

    /**
    * Returns the forecast methodology of this national assumptions.
    *
    * @return the forecast methodology of this national assumptions
    */
    @Override
    public java.lang.String getForecastMethodology() {
        return _nationalAssumptions.getForecastMethodology();
    }

    /**
    * Sets the forecast methodology of this national assumptions.
    *
    * @param forecastMethodology the forecast methodology of this national assumptions
    */
    @Override
    public void setForecastMethodology(java.lang.String forecastMethodology) {
        _nationalAssumptions.setForecastMethodology(forecastMethodology);
    }

    /**
    * Returns the price type of this national assumptions.
    *
    * @return the price type of this national assumptions
    */
    @Override
    public java.lang.String getPriceType() {
        return _nationalAssumptions.getPriceType();
    }

    /**
    * Sets the price type of this national assumptions.
    *
    * @param priceType the price type of this national assumptions
    */
    @Override
    public void setPriceType(java.lang.String priceType) {
        _nationalAssumptions.setPriceType(priceType);
    }

    /**
    * Returns the end period of this national assumptions.
    *
    * @return the end period of this national assumptions
    */
    @Override
    public java.lang.String getEndPeriod() {
        return _nationalAssumptions.getEndPeriod();
    }

    /**
    * Sets the end period of this national assumptions.
    *
    * @param endPeriod the end period of this national assumptions
    */
    @Override
    public void setEndPeriod(java.lang.String endPeriod) {
        _nationalAssumptions.setEndPeriod(endPeriod);
    }

    /**
    * Returns the price basis of this national assumptions.
    *
    * @return the price basis of this national assumptions
    */
    @Override
    public java.lang.String getPriceBasis() {
        return _nationalAssumptions.getPriceBasis();
    }

    /**
    * Sets the price basis of this national assumptions.
    *
    * @param priceBasis the price basis of this national assumptions
    */
    @Override
    public void setPriceBasis(java.lang.String priceBasis) {
        _nationalAssumptions.setPriceBasis(priceBasis);
    }

    /**
    * Returns the na proj master sid of this national assumptions.
    *
    * @return the na proj master sid of this national assumptions
    */
    @Override
    public int getNaProjMasterSid() {
        return _nationalAssumptions.getNaProjMasterSid();
    }

    /**
    * Sets the na proj master sid of this national assumptions.
    *
    * @param naProjMasterSid the na proj master sid of this national assumptions
    */
    @Override
    public void setNaProjMasterSid(int naProjMasterSid) {
        _nationalAssumptions.setNaProjMasterSid(naProjMasterSid);
    }

    /**
    * Returns the rolling period of this national assumptions.
    *
    * @return the rolling period of this national assumptions
    */
    @Override
    public java.lang.String getRollingPeriod() {
        return _nationalAssumptions.getRollingPeriod();
    }

    /**
    * Sets the rolling period of this national assumptions.
    *
    * @param rollingPeriod the rolling period of this national assumptions
    */
    @Override
    public void setRollingPeriod(java.lang.String rollingPeriod) {
        _nationalAssumptions.setRollingPeriod(rollingPeriod);
    }

    /**
    * Returns the baseline methodology of this national assumptions.
    *
    * @return the baseline methodology of this national assumptions
    */
    @Override
    public java.lang.String getBaselineMethodology() {
        return _nationalAssumptions.getBaselineMethodology();
    }

    /**
    * Sets the baseline methodology of this national assumptions.
    *
    * @param baselineMethodology the baseline methodology of this national assumptions
    */
    @Override
    public void setBaselineMethodology(java.lang.String baselineMethodology) {
        _nationalAssumptions.setBaselineMethodology(baselineMethodology);
    }

    /**
    * Returns the growth rate of this national assumptions.
    *
    * @return the growth rate of this national assumptions
    */
    @Override
    public double getGrowthRate() {
        return _nationalAssumptions.getGrowthRate();
    }

    /**
    * Sets the growth rate of this national assumptions.
    *
    * @param growthRate the growth rate of this national assumptions
    */
    @Override
    public void setGrowthRate(double growthRate) {
        _nationalAssumptions.setGrowthRate(growthRate);
    }

    @Override
    public boolean isNew() {
        return _nationalAssumptions.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _nationalAssumptions.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _nationalAssumptions.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _nationalAssumptions.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _nationalAssumptions.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _nationalAssumptions.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _nationalAssumptions.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _nationalAssumptions.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _nationalAssumptions.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _nationalAssumptions.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _nationalAssumptions.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NationalAssumptionsWrapper((NationalAssumptions) _nationalAssumptions.clone());
    }

    @Override
    public int compareTo(NationalAssumptions nationalAssumptions) {
        return _nationalAssumptions.compareTo(nationalAssumptions);
    }

    @Override
    public int hashCode() {
        return _nationalAssumptions.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NationalAssumptions> toCacheModel() {
        return _nationalAssumptions.toCacheModel();
    }

    @Override
    public NationalAssumptions toEscapedModel() {
        return new NationalAssumptionsWrapper(_nationalAssumptions.toEscapedModel());
    }

    @Override
    public NationalAssumptions toUnescapedModel() {
        return new NationalAssumptionsWrapper(_nationalAssumptions.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _nationalAssumptions.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _nationalAssumptions.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _nationalAssumptions.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NationalAssumptionsWrapper)) {
            return false;
        }

        NationalAssumptionsWrapper nationalAssumptionsWrapper = (NationalAssumptionsWrapper) obj;

        if (Validator.equals(_nationalAssumptions,
                    nationalAssumptionsWrapper._nationalAssumptions)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NationalAssumptions getWrappedNationalAssumptions() {
        return _nationalAssumptions;
    }

    @Override
    public NationalAssumptions getWrappedModel() {
        return _nationalAssumptions;
    }

    @Override
    public void resetOriginalValues() {
        _nationalAssumptions.resetOriginalValues();
    }
}
