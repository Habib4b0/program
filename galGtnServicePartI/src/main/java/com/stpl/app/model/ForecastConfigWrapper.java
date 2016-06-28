package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ForecastConfig}.
 * </p>
 *
 * @author
 * @see ForecastConfig
 * @generated
 */
public class ForecastConfigWrapper implements ForecastConfig,
    ModelWrapper<ForecastConfig> {
    private ForecastConfig _forecastConfig;

    public ForecastConfigWrapper(ForecastConfig forecastConfig) {
        _forecastConfig = forecastConfig;
    }

    @Override
    public Class<?> getModelClass() {
        return ForecastConfig.class;
    }

    @Override
    public String getModelClassName() {
        return ForecastConfig.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("processType", getProcessType());
        attributes.put("toDate", getToDate());
        attributes.put("versionNo", getVersionNo());
        attributes.put("forecastConfigSid", getForecastConfigSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("fromDate", getFromDate());
        attributes.put("projValue", getProjValue());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("projFreq", getProjFreq());
        attributes.put("histValue", getHistValue());
        attributes.put("businessProcessType", getBusinessProcessType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("histFreq", getHistFreq());
        attributes.put("activeStartDate", getActiveStartDate());
        attributes.put("activeEndDate", getActiveEndDate());
        attributes.put("processMode", getProcessMode());
        attributes.put("historicalDataIntervalFrom",
            getHistoricalDataIntervalFrom());
        attributes.put("historicalTimePeriodFrom", getHistoricalTimePeriodFrom());
        attributes.put("projHistFreq", getProjHistFreq());
        attributes.put("futureTimePeriodFrom", getFutureTimePeriodFrom());
        attributes.put("historicalDataIntervalTo", getHistoricalDataIntervalTo());
        attributes.put("projHistValue", getProjHistValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean processType = (Boolean) attributes.get("processType");

        if (processType != null) {
            setProcessType(processType);
        }

        Date toDate = (Date) attributes.get("toDate");

        if (toDate != null) {
            setToDate(toDate);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer forecastConfigSid = (Integer) attributes.get(
                "forecastConfigSid");

        if (forecastConfigSid != null) {
            setForecastConfigSid(forecastConfigSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date fromDate = (Date) attributes.get("fromDate");

        if (fromDate != null) {
            setFromDate(fromDate);
        }

        Integer projValue = (Integer) attributes.get("projValue");

        if (projValue != null) {
            setProjValue(projValue);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer projFreq = (Integer) attributes.get("projFreq");

        if (projFreq != null) {
            setProjFreq(projFreq);
        }

        Integer histValue = (Integer) attributes.get("histValue");

        if (histValue != null) {
            setHistValue(histValue);
        }

        Integer businessProcessType = (Integer) attributes.get(
                "businessProcessType");

        if (businessProcessType != null) {
            setBusinessProcessType(businessProcessType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer histFreq = (Integer) attributes.get("histFreq");

        if (histFreq != null) {
            setHistFreq(histFreq);
        }

        Date activeStartDate = (Date) attributes.get("activeStartDate");

        if (activeStartDate != null) {
            setActiveStartDate(activeStartDate);
        }

        Date activeEndDate = (Date) attributes.get("activeEndDate");

        if (activeEndDate != null) {
            setActiveEndDate(activeEndDate);
        }

        Boolean processMode = (Boolean) attributes.get("processMode");

        if (processMode != null) {
            setProcessMode(processMode);
        }

        Date historicalDataIntervalFrom = (Date) attributes.get(
                "historicalDataIntervalFrom");

        if (historicalDataIntervalFrom != null) {
            setHistoricalDataIntervalFrom(historicalDataIntervalFrom);
        }

        Date historicalTimePeriodFrom = (Date) attributes.get(
                "historicalTimePeriodFrom");

        if (historicalTimePeriodFrom != null) {
            setHistoricalTimePeriodFrom(historicalTimePeriodFrom);
        }

        Integer projHistFreq = (Integer) attributes.get("projHistFreq");

        if (projHistFreq != null) {
            setProjHistFreq(projHistFreq);
        }

        Date futureTimePeriodFrom = (Date) attributes.get(
                "futureTimePeriodFrom");

        if (futureTimePeriodFrom != null) {
            setFutureTimePeriodFrom(futureTimePeriodFrom);
        }

        Date historicalDataIntervalTo = (Date) attributes.get(
                "historicalDataIntervalTo");

        if (historicalDataIntervalTo != null) {
            setHistoricalDataIntervalTo(historicalDataIntervalTo);
        }

        Integer projHistValue = (Integer) attributes.get("projHistValue");

        if (projHistValue != null) {
            setProjHistValue(projHistValue);
        }
    }

    /**
    * Returns the primary key of this forecast config.
    *
    * @return the primary key of this forecast config
    */
    @Override
    public int getPrimaryKey() {
        return _forecastConfig.getPrimaryKey();
    }

    /**
    * Sets the primary key of this forecast config.
    *
    * @param primaryKey the primary key of this forecast config
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _forecastConfig.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the process type of this forecast config.
    *
    * @return the process type of this forecast config
    */
    @Override
    public boolean getProcessType() {
        return _forecastConfig.getProcessType();
    }

    /**
    * Returns <code>true</code> if this forecast config is process type.
    *
    * @return <code>true</code> if this forecast config is process type; <code>false</code> otherwise
    */
    @Override
    public boolean isProcessType() {
        return _forecastConfig.isProcessType();
    }

    /**
    * Sets whether this forecast config is process type.
    *
    * @param processType the process type of this forecast config
    */
    @Override
    public void setProcessType(boolean processType) {
        _forecastConfig.setProcessType(processType);
    }

    /**
    * Returns the to date of this forecast config.
    *
    * @return the to date of this forecast config
    */
    @Override
    public java.util.Date getToDate() {
        return _forecastConfig.getToDate();
    }

    /**
    * Sets the to date of this forecast config.
    *
    * @param toDate the to date of this forecast config
    */
    @Override
    public void setToDate(java.util.Date toDate) {
        _forecastConfig.setToDate(toDate);
    }

    /**
    * Returns the version no of this forecast config.
    *
    * @return the version no of this forecast config
    */
    @Override
    public int getVersionNo() {
        return _forecastConfig.getVersionNo();
    }

    /**
    * Sets the version no of this forecast config.
    *
    * @param versionNo the version no of this forecast config
    */
    @Override
    public void setVersionNo(int versionNo) {
        _forecastConfig.setVersionNo(versionNo);
    }

    /**
    * Returns the forecast config sid of this forecast config.
    *
    * @return the forecast config sid of this forecast config
    */
    @Override
    public int getForecastConfigSid() {
        return _forecastConfig.getForecastConfigSid();
    }

    /**
    * Sets the forecast config sid of this forecast config.
    *
    * @param forecastConfigSid the forecast config sid of this forecast config
    */
    @Override
    public void setForecastConfigSid(int forecastConfigSid) {
        _forecastConfig.setForecastConfigSid(forecastConfigSid);
    }

    /**
    * Returns the modified date of this forecast config.
    *
    * @return the modified date of this forecast config
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _forecastConfig.getModifiedDate();
    }

    /**
    * Sets the modified date of this forecast config.
    *
    * @param modifiedDate the modified date of this forecast config
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _forecastConfig.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the from date of this forecast config.
    *
    * @return the from date of this forecast config
    */
    @Override
    public java.util.Date getFromDate() {
        return _forecastConfig.getFromDate();
    }

    /**
    * Sets the from date of this forecast config.
    *
    * @param fromDate the from date of this forecast config
    */
    @Override
    public void setFromDate(java.util.Date fromDate) {
        _forecastConfig.setFromDate(fromDate);
    }

    /**
    * Returns the proj value of this forecast config.
    *
    * @return the proj value of this forecast config
    */
    @Override
    public int getProjValue() {
        return _forecastConfig.getProjValue();
    }

    /**
    * Sets the proj value of this forecast config.
    *
    * @param projValue the proj value of this forecast config
    */
    @Override
    public void setProjValue(int projValue) {
        _forecastConfig.setProjValue(projValue);
    }

    /**
    * Returns the created by of this forecast config.
    *
    * @return the created by of this forecast config
    */
    @Override
    public int getCreatedBy() {
        return _forecastConfig.getCreatedBy();
    }

    /**
    * Sets the created by of this forecast config.
    *
    * @param createdBy the created by of this forecast config
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _forecastConfig.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this forecast config.
    *
    * @return the created date of this forecast config
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _forecastConfig.getCreatedDate();
    }

    /**
    * Sets the created date of this forecast config.
    *
    * @param createdDate the created date of this forecast config
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _forecastConfig.setCreatedDate(createdDate);
    }

    /**
    * Returns the proj freq of this forecast config.
    *
    * @return the proj freq of this forecast config
    */
    @Override
    public int getProjFreq() {
        return _forecastConfig.getProjFreq();
    }

    /**
    * Sets the proj freq of this forecast config.
    *
    * @param projFreq the proj freq of this forecast config
    */
    @Override
    public void setProjFreq(int projFreq) {
        _forecastConfig.setProjFreq(projFreq);
    }

    /**
    * Returns the hist value of this forecast config.
    *
    * @return the hist value of this forecast config
    */
    @Override
    public int getHistValue() {
        return _forecastConfig.getHistValue();
    }

    /**
    * Sets the hist value of this forecast config.
    *
    * @param histValue the hist value of this forecast config
    */
    @Override
    public void setHistValue(int histValue) {
        _forecastConfig.setHistValue(histValue);
    }

    /**
    * Returns the business process type of this forecast config.
    *
    * @return the business process type of this forecast config
    */
    @Override
    public int getBusinessProcessType() {
        return _forecastConfig.getBusinessProcessType();
    }

    /**
    * Sets the business process type of this forecast config.
    *
    * @param businessProcessType the business process type of this forecast config
    */
    @Override
    public void setBusinessProcessType(int businessProcessType) {
        _forecastConfig.setBusinessProcessType(businessProcessType);
    }

    /**
    * Returns the modified by of this forecast config.
    *
    * @return the modified by of this forecast config
    */
    @Override
    public int getModifiedBy() {
        return _forecastConfig.getModifiedBy();
    }

    /**
    * Sets the modified by of this forecast config.
    *
    * @param modifiedBy the modified by of this forecast config
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _forecastConfig.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the hist freq of this forecast config.
    *
    * @return the hist freq of this forecast config
    */
    @Override
    public int getHistFreq() {
        return _forecastConfig.getHistFreq();
    }

    /**
    * Sets the hist freq of this forecast config.
    *
    * @param histFreq the hist freq of this forecast config
    */
    @Override
    public void setHistFreq(int histFreq) {
        _forecastConfig.setHistFreq(histFreq);
    }

    /**
    * Returns the active start date of this forecast config.
    *
    * @return the active start date of this forecast config
    */
    @Override
    public java.util.Date getActiveStartDate() {
        return _forecastConfig.getActiveStartDate();
    }

    /**
    * Sets the active start date of this forecast config.
    *
    * @param activeStartDate the active start date of this forecast config
    */
    @Override
    public void setActiveStartDate(java.util.Date activeStartDate) {
        _forecastConfig.setActiveStartDate(activeStartDate);
    }

    /**
    * Returns the active end date of this forecast config.
    *
    * @return the active end date of this forecast config
    */
    @Override
    public java.util.Date getActiveEndDate() {
        return _forecastConfig.getActiveEndDate();
    }

    /**
    * Sets the active end date of this forecast config.
    *
    * @param activeEndDate the active end date of this forecast config
    */
    @Override
    public void setActiveEndDate(java.util.Date activeEndDate) {
        _forecastConfig.setActiveEndDate(activeEndDate);
    }

    /**
    * Returns the process mode of this forecast config.
    *
    * @return the process mode of this forecast config
    */
    @Override
    public boolean getProcessMode() {
        return _forecastConfig.getProcessMode();
    }

    /**
    * Returns <code>true</code> if this forecast config is process mode.
    *
    * @return <code>true</code> if this forecast config is process mode; <code>false</code> otherwise
    */
    @Override
    public boolean isProcessMode() {
        return _forecastConfig.isProcessMode();
    }

    /**
    * Sets whether this forecast config is process mode.
    *
    * @param processMode the process mode of this forecast config
    */
    @Override
    public void setProcessMode(boolean processMode) {
        _forecastConfig.setProcessMode(processMode);
    }

    /**
    * Returns the historical data interval from of this forecast config.
    *
    * @return the historical data interval from of this forecast config
    */
    @Override
    public java.util.Date getHistoricalDataIntervalFrom() {
        return _forecastConfig.getHistoricalDataIntervalFrom();
    }

    /**
    * Sets the historical data interval from of this forecast config.
    *
    * @param historicalDataIntervalFrom the historical data interval from of this forecast config
    */
    @Override
    public void setHistoricalDataIntervalFrom(
        java.util.Date historicalDataIntervalFrom) {
        _forecastConfig.setHistoricalDataIntervalFrom(historicalDataIntervalFrom);
    }

    /**
    * Returns the historical time period from of this forecast config.
    *
    * @return the historical time period from of this forecast config
    */
    @Override
    public java.util.Date getHistoricalTimePeriodFrom() {
        return _forecastConfig.getHistoricalTimePeriodFrom();
    }

    /**
    * Sets the historical time period from of this forecast config.
    *
    * @param historicalTimePeriodFrom the historical time period from of this forecast config
    */
    @Override
    public void setHistoricalTimePeriodFrom(
        java.util.Date historicalTimePeriodFrom) {
        _forecastConfig.setHistoricalTimePeriodFrom(historicalTimePeriodFrom);
    }

    /**
    * Returns the proj hist freq of this forecast config.
    *
    * @return the proj hist freq of this forecast config
    */
    @Override
    public int getProjHistFreq() {
        return _forecastConfig.getProjHistFreq();
    }

    /**
    * Sets the proj hist freq of this forecast config.
    *
    * @param projHistFreq the proj hist freq of this forecast config
    */
    @Override
    public void setProjHistFreq(int projHistFreq) {
        _forecastConfig.setProjHistFreq(projHistFreq);
    }

    /**
    * Returns the future time period from of this forecast config.
    *
    * @return the future time period from of this forecast config
    */
    @Override
    public java.util.Date getFutureTimePeriodFrom() {
        return _forecastConfig.getFutureTimePeriodFrom();
    }

    /**
    * Sets the future time period from of this forecast config.
    *
    * @param futureTimePeriodFrom the future time period from of this forecast config
    */
    @Override
    public void setFutureTimePeriodFrom(java.util.Date futureTimePeriodFrom) {
        _forecastConfig.setFutureTimePeriodFrom(futureTimePeriodFrom);
    }

    /**
    * Returns the historical data interval to of this forecast config.
    *
    * @return the historical data interval to of this forecast config
    */
    @Override
    public java.util.Date getHistoricalDataIntervalTo() {
        return _forecastConfig.getHistoricalDataIntervalTo();
    }

    /**
    * Sets the historical data interval to of this forecast config.
    *
    * @param historicalDataIntervalTo the historical data interval to of this forecast config
    */
    @Override
    public void setHistoricalDataIntervalTo(
        java.util.Date historicalDataIntervalTo) {
        _forecastConfig.setHistoricalDataIntervalTo(historicalDataIntervalTo);
    }

    /**
    * Returns the proj hist value of this forecast config.
    *
    * @return the proj hist value of this forecast config
    */
    @Override
    public int getProjHistValue() {
        return _forecastConfig.getProjHistValue();
    }

    /**
    * Sets the proj hist value of this forecast config.
    *
    * @param projHistValue the proj hist value of this forecast config
    */
    @Override
    public void setProjHistValue(int projHistValue) {
        _forecastConfig.setProjHistValue(projHistValue);
    }

    @Override
    public boolean isNew() {
        return _forecastConfig.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _forecastConfig.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _forecastConfig.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _forecastConfig.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _forecastConfig.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _forecastConfig.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _forecastConfig.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _forecastConfig.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _forecastConfig.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _forecastConfig.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _forecastConfig.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ForecastConfigWrapper((ForecastConfig) _forecastConfig.clone());
    }

    @Override
    public int compareTo(ForecastConfig forecastConfig) {
        return _forecastConfig.compareTo(forecastConfig);
    }

    @Override
    public int hashCode() {
        return _forecastConfig.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ForecastConfig> toCacheModel() {
        return _forecastConfig.toCacheModel();
    }

    @Override
    public ForecastConfig toEscapedModel() {
        return new ForecastConfigWrapper(_forecastConfig.toEscapedModel());
    }

    @Override
    public ForecastConfig toUnescapedModel() {
        return new ForecastConfigWrapper(_forecastConfig.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _forecastConfig.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _forecastConfig.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _forecastConfig.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ForecastConfigWrapper)) {
            return false;
        }

        ForecastConfigWrapper forecastConfigWrapper = (ForecastConfigWrapper) obj;

        if (Validator.equals(_forecastConfig,
                    forecastConfigWrapper._forecastConfig)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ForecastConfig getWrappedForecastConfig() {
        return _forecastConfig;
    }

    @Override
    public ForecastConfig getWrappedModel() {
        return _forecastConfig;
    }

    @Override
    public void resetOriginalValues() {
        _forecastConfig.resetOriginalValues();
    }
}
