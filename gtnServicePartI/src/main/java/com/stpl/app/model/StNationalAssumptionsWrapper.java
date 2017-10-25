package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StNationalAssumptions}.
 * </p>
 *
 * @author
 * @see StNationalAssumptions
 * @generated
 */
public class StNationalAssumptionsWrapper implements StNationalAssumptions,
    ModelWrapper<StNationalAssumptions> {
    private StNationalAssumptions _stNationalAssumptions;

    public StNationalAssumptionsWrapper(
        StNationalAssumptions stNationalAssumptions) {
        _stNationalAssumptions = stNationalAssumptions;
    }

    @Override
    public Class<?> getModelClass() {
        return StNationalAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return StNationalAssumptions.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("baselinePeriod", getBaselinePeriod());
        attributes.put("startPeriod", getStartPeriod());
        attributes.put("frequency", getFrequency());
        attributes.put("userId", getUserId());
        attributes.put("endPeriod", getEndPeriod());
        attributes.put("naProjMasterSid", getNaProjMasterSid());
        attributes.put("rollingPeriod", getRollingPeriod());
        attributes.put("forecastMethodology", getForecastMethodology());
        attributes.put("priceType", getPriceType());
        attributes.put("priceBasis", getPriceBasis());
        attributes.put("sessionId", getSessionId());
        attributes.put("baselineMethodology", getBaselineMethodology());
        attributes.put("growthRate", getGrowthRate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        String baselinePeriod = (String) attributes.get("baselinePeriod");

        if (baselinePeriod != null) {
            setBaselinePeriod(baselinePeriod);
        }

        String startPeriod = (String) attributes.get("startPeriod");

        if (startPeriod != null) {
            setStartPeriod(startPeriod);
        }

        String frequency = (String) attributes.get("frequency");

        if (frequency != null) {
            setFrequency(frequency);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String endPeriod = (String) attributes.get("endPeriod");

        if (endPeriod != null) {
            setEndPeriod(endPeriod);
        }

        Integer naProjMasterSid = (Integer) attributes.get("naProjMasterSid");

        if (naProjMasterSid != null) {
            setNaProjMasterSid(naProjMasterSid);
        }

        String rollingPeriod = (String) attributes.get("rollingPeriod");

        if (rollingPeriod != null) {
            setRollingPeriod(rollingPeriod);
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

        String priceBasis = (String) attributes.get("priceBasis");

        if (priceBasis != null) {
            setPriceBasis(priceBasis);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
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
    * Returns the primary key of this st national assumptions.
    *
    * @return the primary key of this st national assumptions
    */
    @Override
    public com.stpl.app.service.persistence.StNationalAssumptionsPK getPrimaryKey() {
        return _stNationalAssumptions.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st national assumptions.
    *
    * @param primaryKey the primary key of this st national assumptions
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StNationalAssumptionsPK primaryKey) {
        _stNationalAssumptions.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the last modified date of this st national assumptions.
    *
    * @return the last modified date of this st national assumptions
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stNationalAssumptions.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st national assumptions.
    *
    * @param lastModifiedDate the last modified date of this st national assumptions
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stNationalAssumptions.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the baseline period of this st national assumptions.
    *
    * @return the baseline period of this st national assumptions
    */
    @Override
    public java.lang.String getBaselinePeriod() {
        return _stNationalAssumptions.getBaselinePeriod();
    }

    /**
    * Sets the baseline period of this st national assumptions.
    *
    * @param baselinePeriod the baseline period of this st national assumptions
    */
    @Override
    public void setBaselinePeriod(java.lang.String baselinePeriod) {
        _stNationalAssumptions.setBaselinePeriod(baselinePeriod);
    }

    /**
    * Returns the start period of this st national assumptions.
    *
    * @return the start period of this st national assumptions
    */
    @Override
    public java.lang.String getStartPeriod() {
        return _stNationalAssumptions.getStartPeriod();
    }

    /**
    * Sets the start period of this st national assumptions.
    *
    * @param startPeriod the start period of this st national assumptions
    */
    @Override
    public void setStartPeriod(java.lang.String startPeriod) {
        _stNationalAssumptions.setStartPeriod(startPeriod);
    }

    /**
    * Returns the frequency of this st national assumptions.
    *
    * @return the frequency of this st national assumptions
    */
    @Override
    public java.lang.String getFrequency() {
        return _stNationalAssumptions.getFrequency();
    }

    /**
    * Sets the frequency of this st national assumptions.
    *
    * @param frequency the frequency of this st national assumptions
    */
    @Override
    public void setFrequency(java.lang.String frequency) {
        _stNationalAssumptions.setFrequency(frequency);
    }

    /**
    * Returns the user ID of this st national assumptions.
    *
    * @return the user ID of this st national assumptions
    */
    @Override
    public int getUserId() {
        return _stNationalAssumptions.getUserId();
    }

    /**
    * Sets the user ID of this st national assumptions.
    *
    * @param userId the user ID of this st national assumptions
    */
    @Override
    public void setUserId(int userId) {
        _stNationalAssumptions.setUserId(userId);
    }

    /**
    * Returns the end period of this st national assumptions.
    *
    * @return the end period of this st national assumptions
    */
    @Override
    public java.lang.String getEndPeriod() {
        return _stNationalAssumptions.getEndPeriod();
    }

    /**
    * Sets the end period of this st national assumptions.
    *
    * @param endPeriod the end period of this st national assumptions
    */
    @Override
    public void setEndPeriod(java.lang.String endPeriod) {
        _stNationalAssumptions.setEndPeriod(endPeriod);
    }

    /**
    * Returns the na proj master sid of this st national assumptions.
    *
    * @return the na proj master sid of this st national assumptions
    */
    @Override
    public int getNaProjMasterSid() {
        return _stNationalAssumptions.getNaProjMasterSid();
    }

    /**
    * Sets the na proj master sid of this st national assumptions.
    *
    * @param naProjMasterSid the na proj master sid of this st national assumptions
    */
    @Override
    public void setNaProjMasterSid(int naProjMasterSid) {
        _stNationalAssumptions.setNaProjMasterSid(naProjMasterSid);
    }

    /**
    * Returns the rolling period of this st national assumptions.
    *
    * @return the rolling period of this st national assumptions
    */
    @Override
    public java.lang.String getRollingPeriod() {
        return _stNationalAssumptions.getRollingPeriod();
    }

    /**
    * Sets the rolling period of this st national assumptions.
    *
    * @param rollingPeriod the rolling period of this st national assumptions
    */
    @Override
    public void setRollingPeriod(java.lang.String rollingPeriod) {
        _stNationalAssumptions.setRollingPeriod(rollingPeriod);
    }

    /**
    * Returns the forecast methodology of this st national assumptions.
    *
    * @return the forecast methodology of this st national assumptions
    */
    @Override
    public java.lang.String getForecastMethodology() {
        return _stNationalAssumptions.getForecastMethodology();
    }

    /**
    * Sets the forecast methodology of this st national assumptions.
    *
    * @param forecastMethodology the forecast methodology of this st national assumptions
    */
    @Override
    public void setForecastMethodology(java.lang.String forecastMethodology) {
        _stNationalAssumptions.setForecastMethodology(forecastMethodology);
    }

    /**
    * Returns the price type of this st national assumptions.
    *
    * @return the price type of this st national assumptions
    */
    @Override
    public java.lang.String getPriceType() {
        return _stNationalAssumptions.getPriceType();
    }

    /**
    * Sets the price type of this st national assumptions.
    *
    * @param priceType the price type of this st national assumptions
    */
    @Override
    public void setPriceType(java.lang.String priceType) {
        _stNationalAssumptions.setPriceType(priceType);
    }

    /**
    * Returns the price basis of this st national assumptions.
    *
    * @return the price basis of this st national assumptions
    */
    @Override
    public java.lang.String getPriceBasis() {
        return _stNationalAssumptions.getPriceBasis();
    }

    /**
    * Sets the price basis of this st national assumptions.
    *
    * @param priceBasis the price basis of this st national assumptions
    */
    @Override
    public void setPriceBasis(java.lang.String priceBasis) {
        _stNationalAssumptions.setPriceBasis(priceBasis);
    }

    /**
    * Returns the session ID of this st national assumptions.
    *
    * @return the session ID of this st national assumptions
    */
    @Override
    public int getSessionId() {
        return _stNationalAssumptions.getSessionId();
    }

    /**
    * Sets the session ID of this st national assumptions.
    *
    * @param sessionId the session ID of this st national assumptions
    */
    @Override
    public void setSessionId(int sessionId) {
        _stNationalAssumptions.setSessionId(sessionId);
    }

    /**
    * Returns the baseline methodology of this st national assumptions.
    *
    * @return the baseline methodology of this st national assumptions
    */
    @Override
    public java.lang.String getBaselineMethodology() {
        return _stNationalAssumptions.getBaselineMethodology();
    }

    /**
    * Sets the baseline methodology of this st national assumptions.
    *
    * @param baselineMethodology the baseline methodology of this st national assumptions
    */
    @Override
    public void setBaselineMethodology(java.lang.String baselineMethodology) {
        _stNationalAssumptions.setBaselineMethodology(baselineMethodology);
    }

    /**
    * Returns the growth rate of this st national assumptions.
    *
    * @return the growth rate of this st national assumptions
    */
    @Override
    public double getGrowthRate() {
        return _stNationalAssumptions.getGrowthRate();
    }

    /**
    * Sets the growth rate of this st national assumptions.
    *
    * @param growthRate the growth rate of this st national assumptions
    */
    @Override
    public void setGrowthRate(double growthRate) {
        _stNationalAssumptions.setGrowthRate(growthRate);
    }

    @Override
    public boolean isNew() {
        return _stNationalAssumptions.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stNationalAssumptions.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stNationalAssumptions.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stNationalAssumptions.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stNationalAssumptions.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stNationalAssumptions.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stNationalAssumptions.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stNationalAssumptions.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stNationalAssumptions.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stNationalAssumptions.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stNationalAssumptions.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StNationalAssumptionsWrapper((StNationalAssumptions) _stNationalAssumptions.clone());
    }

    @Override
    public int compareTo(StNationalAssumptions stNationalAssumptions) {
        return _stNationalAssumptions.compareTo(stNationalAssumptions);
    }

    @Override
    public int hashCode() {
        return _stNationalAssumptions.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StNationalAssumptions> toCacheModel() {
        return _stNationalAssumptions.toCacheModel();
    }

    @Override
    public StNationalAssumptions toEscapedModel() {
        return new StNationalAssumptionsWrapper(_stNationalAssumptions.toEscapedModel());
    }

    @Override
    public StNationalAssumptions toUnescapedModel() {
        return new StNationalAssumptionsWrapper(_stNationalAssumptions.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stNationalAssumptions.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stNationalAssumptions.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stNationalAssumptions.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNationalAssumptionsWrapper)) {
            return false;
        }

        StNationalAssumptionsWrapper stNationalAssumptionsWrapper = (StNationalAssumptionsWrapper) obj;

        if (Validator.equals(_stNationalAssumptions,
                    stNationalAssumptionsWrapper._stNationalAssumptions)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StNationalAssumptions getWrappedStNationalAssumptions() {
        return _stNationalAssumptions;
    }

    @Override
    public StNationalAssumptions getWrappedModel() {
        return _stNationalAssumptions;
    }

    @Override
    public void resetOriginalValues() {
        _stNationalAssumptions.resetOriginalValues();
    }
}
