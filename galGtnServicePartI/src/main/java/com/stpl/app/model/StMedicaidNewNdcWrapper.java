package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StMedicaidNewNdc}.
 * </p>
 *
 * @author
 * @see StMedicaidNewNdc
 * @generated
 */
public class StMedicaidNewNdcWrapper implements StMedicaidNewNdc,
    ModelWrapper<StMedicaidNewNdc> {
    private StMedicaidNewNdc _stMedicaidNewNdc;

    public StMedicaidNewNdcWrapper(StMedicaidNewNdc stMedicaidNewNdc) {
        _stMedicaidNewNdc = stMedicaidNewNdc;
    }

    @Override
    public Class<?> getModelClass() {
        return StMedicaidNewNdc.class;
    }

    @Override
    public String getModelClassName() {
        return StMedicaidNewNdc.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("forecastAmp", getForecastAmp());
        attributes.put("forecastBestprice", getForecastBestprice());
        attributes.put("baseYearCpi", getBaseYearCpi());
        attributes.put("ndc9", getNdc9());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("wacPrice", getWacPrice());
        attributes.put("baseYearAmp", getBaseYearAmp());
        attributes.put("sessionId", getSessionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double forecastAmp = (Double) attributes.get("forecastAmp");

        if (forecastAmp != null) {
            setForecastAmp(forecastAmp);
        }

        Double forecastBestprice = (Double) attributes.get("forecastBestprice");

        if (forecastBestprice != null) {
            setForecastBestprice(forecastBestprice);
        }

        Double baseYearCpi = (Double) attributes.get("baseYearCpi");

        if (baseYearCpi != null) {
            setBaseYearCpi(baseYearCpi);
        }

        String ndc9 = (String) attributes.get("ndc9");

        if (ndc9 != null) {
            setNdc9(ndc9);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Double wacPrice = (Double) attributes.get("wacPrice");

        if (wacPrice != null) {
            setWacPrice(wacPrice);
        }

        Double baseYearAmp = (Double) attributes.get("baseYearAmp");

        if (baseYearAmp != null) {
            setBaseYearAmp(baseYearAmp);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }
    }

    /**
    * Returns the primary key of this st medicaid new ndc.
    *
    * @return the primary key of this st medicaid new ndc
    */
    @Override
    public com.stpl.app.service.persistence.StMedicaidNewNdcPK getPrimaryKey() {
        return _stMedicaidNewNdc.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st medicaid new ndc.
    *
    * @param primaryKey the primary key of this st medicaid new ndc
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StMedicaidNewNdcPK primaryKey) {
        _stMedicaidNewNdc.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the forecast amp of this st medicaid new ndc.
    *
    * @return the forecast amp of this st medicaid new ndc
    */
    @Override
    public double getForecastAmp() {
        return _stMedicaidNewNdc.getForecastAmp();
    }

    /**
    * Sets the forecast amp of this st medicaid new ndc.
    *
    * @param forecastAmp the forecast amp of this st medicaid new ndc
    */
    @Override
    public void setForecastAmp(double forecastAmp) {
        _stMedicaidNewNdc.setForecastAmp(forecastAmp);
    }

    /**
    * Returns the forecast bestprice of this st medicaid new ndc.
    *
    * @return the forecast bestprice of this st medicaid new ndc
    */
    @Override
    public double getForecastBestprice() {
        return _stMedicaidNewNdc.getForecastBestprice();
    }

    /**
    * Sets the forecast bestprice of this st medicaid new ndc.
    *
    * @param forecastBestprice the forecast bestprice of this st medicaid new ndc
    */
    @Override
    public void setForecastBestprice(double forecastBestprice) {
        _stMedicaidNewNdc.setForecastBestprice(forecastBestprice);
    }

    /**
    * Returns the base year cpi of this st medicaid new ndc.
    *
    * @return the base year cpi of this st medicaid new ndc
    */
    @Override
    public double getBaseYearCpi() {
        return _stMedicaidNewNdc.getBaseYearCpi();
    }

    /**
    * Sets the base year cpi of this st medicaid new ndc.
    *
    * @param baseYearCpi the base year cpi of this st medicaid new ndc
    */
    @Override
    public void setBaseYearCpi(double baseYearCpi) {
        _stMedicaidNewNdc.setBaseYearCpi(baseYearCpi);
    }

    /**
    * Returns the ndc9 of this st medicaid new ndc.
    *
    * @return the ndc9 of this st medicaid new ndc
    */
    @Override
    public java.lang.String getNdc9() {
        return _stMedicaidNewNdc.getNdc9();
    }

    /**
    * Sets the ndc9 of this st medicaid new ndc.
    *
    * @param ndc9 the ndc9 of this st medicaid new ndc
    */
    @Override
    public void setNdc9(java.lang.String ndc9) {
        _stMedicaidNewNdc.setNdc9(ndc9);
    }

    /**
    * Returns the user ID of this st medicaid new ndc.
    *
    * @return the user ID of this st medicaid new ndc
    */
    @Override
    public int getUserId() {
        return _stMedicaidNewNdc.getUserId();
    }

    /**
    * Sets the user ID of this st medicaid new ndc.
    *
    * @param userId the user ID of this st medicaid new ndc
    */
    @Override
    public void setUserId(int userId) {
        _stMedicaidNewNdc.setUserId(userId);
    }

    /**
    * Returns the last modified date of this st medicaid new ndc.
    *
    * @return the last modified date of this st medicaid new ndc
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stMedicaidNewNdc.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st medicaid new ndc.
    *
    * @param lastModifiedDate the last modified date of this st medicaid new ndc
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stMedicaidNewNdc.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the wac price of this st medicaid new ndc.
    *
    * @return the wac price of this st medicaid new ndc
    */
    @Override
    public double getWacPrice() {
        return _stMedicaidNewNdc.getWacPrice();
    }

    /**
    * Sets the wac price of this st medicaid new ndc.
    *
    * @param wacPrice the wac price of this st medicaid new ndc
    */
    @Override
    public void setWacPrice(double wacPrice) {
        _stMedicaidNewNdc.setWacPrice(wacPrice);
    }

    /**
    * Returns the base year amp of this st medicaid new ndc.
    *
    * @return the base year amp of this st medicaid new ndc
    */
    @Override
    public double getBaseYearAmp() {
        return _stMedicaidNewNdc.getBaseYearAmp();
    }

    /**
    * Sets the base year amp of this st medicaid new ndc.
    *
    * @param baseYearAmp the base year amp of this st medicaid new ndc
    */
    @Override
    public void setBaseYearAmp(double baseYearAmp) {
        _stMedicaidNewNdc.setBaseYearAmp(baseYearAmp);
    }

    /**
    * Returns the session ID of this st medicaid new ndc.
    *
    * @return the session ID of this st medicaid new ndc
    */
    @Override
    public int getSessionId() {
        return _stMedicaidNewNdc.getSessionId();
    }

    /**
    * Sets the session ID of this st medicaid new ndc.
    *
    * @param sessionId the session ID of this st medicaid new ndc
    */
    @Override
    public void setSessionId(int sessionId) {
        _stMedicaidNewNdc.setSessionId(sessionId);
    }

    @Override
    public boolean isNew() {
        return _stMedicaidNewNdc.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stMedicaidNewNdc.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stMedicaidNewNdc.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stMedicaidNewNdc.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stMedicaidNewNdc.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stMedicaidNewNdc.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stMedicaidNewNdc.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stMedicaidNewNdc.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stMedicaidNewNdc.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stMedicaidNewNdc.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stMedicaidNewNdc.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StMedicaidNewNdcWrapper((StMedicaidNewNdc) _stMedicaidNewNdc.clone());
    }

    @Override
    public int compareTo(StMedicaidNewNdc stMedicaidNewNdc) {
        return _stMedicaidNewNdc.compareTo(stMedicaidNewNdc);
    }

    @Override
    public int hashCode() {
        return _stMedicaidNewNdc.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StMedicaidNewNdc> toCacheModel() {
        return _stMedicaidNewNdc.toCacheModel();
    }

    @Override
    public StMedicaidNewNdc toEscapedModel() {
        return new StMedicaidNewNdcWrapper(_stMedicaidNewNdc.toEscapedModel());
    }

    @Override
    public StMedicaidNewNdc toUnescapedModel() {
        return new StMedicaidNewNdcWrapper(_stMedicaidNewNdc.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stMedicaidNewNdc.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stMedicaidNewNdc.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stMedicaidNewNdc.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StMedicaidNewNdcWrapper)) {
            return false;
        }

        StMedicaidNewNdcWrapper stMedicaidNewNdcWrapper = (StMedicaidNewNdcWrapper) obj;

        if (Validator.equals(_stMedicaidNewNdc,
                    stMedicaidNewNdcWrapper._stMedicaidNewNdc)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StMedicaidNewNdc getWrappedStMedicaidNewNdc() {
        return _stMedicaidNewNdc;
    }

    @Override
    public StMedicaidNewNdc getWrappedModel() {
        return _stMedicaidNewNdc;
    }

    @Override
    public void resetOriginalValues() {
        _stMedicaidNewNdc.resetOriginalValues();
    }
}
