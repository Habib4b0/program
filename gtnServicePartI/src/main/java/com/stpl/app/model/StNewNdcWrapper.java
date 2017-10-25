package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StNewNdc}.
 * </p>
 *
 * @author
 * @see StNewNdc
 * @generated
 */
public class StNewNdcWrapper implements StNewNdc, ModelWrapper<StNewNdc> {
    private StNewNdc _stNewNdc;

    public StNewNdcWrapper(StNewNdc stNewNdc) {
        _stNewNdc = stNewNdc;
    }

    @Override
    public Class<?> getModelClass() {
        return StNewNdc.class;
    }

    @Override
    public String getModelClassName() {
        return StNewNdc.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("forecastAmp", getForecastAmp());
        attributes.put("forecastBestprice", getForecastBestprice());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());
        attributes.put("baseYearCpi", getBaseYearCpi());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("itemMasterSid", getItemMasterSid());
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

        Integer naProjDetailsSid = (Integer) attributes.get("naProjDetailsSid");

        if (naProjDetailsSid != null) {
            setNaProjDetailsSid(naProjDetailsSid);
        }

        Double baseYearCpi = (Double) attributes.get("baseYearCpi");

        if (baseYearCpi != null) {
            setBaseYearCpi(baseYearCpi);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
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
    * Returns the primary key of this st new ndc.
    *
    * @return the primary key of this st new ndc
    */
    @Override
    public com.stpl.app.service.persistence.StNewNdcPK getPrimaryKey() {
        return _stNewNdc.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st new ndc.
    *
    * @param primaryKey the primary key of this st new ndc
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StNewNdcPK primaryKey) {
        _stNewNdc.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the forecast amp of this st new ndc.
    *
    * @return the forecast amp of this st new ndc
    */
    @Override
    public double getForecastAmp() {
        return _stNewNdc.getForecastAmp();
    }

    /**
    * Sets the forecast amp of this st new ndc.
    *
    * @param forecastAmp the forecast amp of this st new ndc
    */
    @Override
    public void setForecastAmp(double forecastAmp) {
        _stNewNdc.setForecastAmp(forecastAmp);
    }

    /**
    * Returns the forecast bestprice of this st new ndc.
    *
    * @return the forecast bestprice of this st new ndc
    */
    @Override
    public double getForecastBestprice() {
        return _stNewNdc.getForecastBestprice();
    }

    /**
    * Sets the forecast bestprice of this st new ndc.
    *
    * @param forecastBestprice the forecast bestprice of this st new ndc
    */
    @Override
    public void setForecastBestprice(double forecastBestprice) {
        _stNewNdc.setForecastBestprice(forecastBestprice);
    }

    /**
    * Returns the na proj details sid of this st new ndc.
    *
    * @return the na proj details sid of this st new ndc
    */
    @Override
    public int getNaProjDetailsSid() {
        return _stNewNdc.getNaProjDetailsSid();
    }

    /**
    * Sets the na proj details sid of this st new ndc.
    *
    * @param naProjDetailsSid the na proj details sid of this st new ndc
    */
    @Override
    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _stNewNdc.setNaProjDetailsSid(naProjDetailsSid);
    }

    /**
    * Returns the base year cpi of this st new ndc.
    *
    * @return the base year cpi of this st new ndc
    */
    @Override
    public double getBaseYearCpi() {
        return _stNewNdc.getBaseYearCpi();
    }

    /**
    * Sets the base year cpi of this st new ndc.
    *
    * @param baseYearCpi the base year cpi of this st new ndc
    */
    @Override
    public void setBaseYearCpi(double baseYearCpi) {
        _stNewNdc.setBaseYearCpi(baseYearCpi);
    }

    /**
    * Returns the user ID of this st new ndc.
    *
    * @return the user ID of this st new ndc
    */
    @Override
    public int getUserId() {
        return _stNewNdc.getUserId();
    }

    /**
    * Sets the user ID of this st new ndc.
    *
    * @param userId the user ID of this st new ndc
    */
    @Override
    public void setUserId(int userId) {
        _stNewNdc.setUserId(userId);
    }

    /**
    * Returns the last modified date of this st new ndc.
    *
    * @return the last modified date of this st new ndc
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stNewNdc.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st new ndc.
    *
    * @param lastModifiedDate the last modified date of this st new ndc
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stNewNdc.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the item master sid of this st new ndc.
    *
    * @return the item master sid of this st new ndc
    */
    @Override
    public int getItemMasterSid() {
        return _stNewNdc.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this st new ndc.
    *
    * @param itemMasterSid the item master sid of this st new ndc
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _stNewNdc.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the wac price of this st new ndc.
    *
    * @return the wac price of this st new ndc
    */
    @Override
    public double getWacPrice() {
        return _stNewNdc.getWacPrice();
    }

    /**
    * Sets the wac price of this st new ndc.
    *
    * @param wacPrice the wac price of this st new ndc
    */
    @Override
    public void setWacPrice(double wacPrice) {
        _stNewNdc.setWacPrice(wacPrice);
    }

    /**
    * Returns the base year amp of this st new ndc.
    *
    * @return the base year amp of this st new ndc
    */
    @Override
    public double getBaseYearAmp() {
        return _stNewNdc.getBaseYearAmp();
    }

    /**
    * Sets the base year amp of this st new ndc.
    *
    * @param baseYearAmp the base year amp of this st new ndc
    */
    @Override
    public void setBaseYearAmp(double baseYearAmp) {
        _stNewNdc.setBaseYearAmp(baseYearAmp);
    }

    /**
    * Returns the session ID of this st new ndc.
    *
    * @return the session ID of this st new ndc
    */
    @Override
    public int getSessionId() {
        return _stNewNdc.getSessionId();
    }

    /**
    * Sets the session ID of this st new ndc.
    *
    * @param sessionId the session ID of this st new ndc
    */
    @Override
    public void setSessionId(int sessionId) {
        _stNewNdc.setSessionId(sessionId);
    }

    @Override
    public boolean isNew() {
        return _stNewNdc.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stNewNdc.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stNewNdc.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stNewNdc.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stNewNdc.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stNewNdc.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stNewNdc.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stNewNdc.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stNewNdc.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stNewNdc.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stNewNdc.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StNewNdcWrapper((StNewNdc) _stNewNdc.clone());
    }

    @Override
    public int compareTo(StNewNdc stNewNdc) {
        return _stNewNdc.compareTo(stNewNdc);
    }

    @Override
    public int hashCode() {
        return _stNewNdc.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StNewNdc> toCacheModel() {
        return _stNewNdc.toCacheModel();
    }

    @Override
    public StNewNdc toEscapedModel() {
        return new StNewNdcWrapper(_stNewNdc.toEscapedModel());
    }

    @Override
    public StNewNdc toUnescapedModel() {
        return new StNewNdcWrapper(_stNewNdc.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stNewNdc.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stNewNdc.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stNewNdc.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNewNdcWrapper)) {
            return false;
        }

        StNewNdcWrapper stNewNdcWrapper = (StNewNdcWrapper) obj;

        if (Validator.equals(_stNewNdc, stNewNdcWrapper._stNewNdc)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StNewNdc getWrappedStNewNdc() {
        return _stNewNdc;
    }

    @Override
    public StNewNdc getWrappedModel() {
        return _stNewNdc;
    }

    @Override
    public void resetOriginalValues() {
        _stNewNdc.resetOriginalValues();
    }
}
