package com.stpl.app.model;

import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ForecastConfig service. Represents a row in the &quot;FORECAST_CONFIG&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.ForecastConfigModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.ForecastConfigImpl}.
 * </p>
 *
 * @author
 * @see ForecastConfig
 * @see com.stpl.app.model.impl.ForecastConfigImpl
 * @see com.stpl.app.model.impl.ForecastConfigModelImpl
 * @generated
 */
public interface ForecastConfigModel extends BaseModel<ForecastConfig> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a forecast config model instance should use the {@link ForecastConfig} interface instead.
     */

    /**
     * Returns the primary key of this forecast config.
     *
     * @return the primary key of this forecast config
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this forecast config.
     *
     * @param primaryKey the primary key of this forecast config
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the process type of this forecast config.
     *
     * @return the process type of this forecast config
     */
    public boolean getProcessType();

    /**
     * Returns <code>true</code> if this forecast config is process type.
     *
     * @return <code>true</code> if this forecast config is process type; <code>false</code> otherwise
     */
    public boolean isProcessType();

    /**
     * Sets whether this forecast config is process type.
     *
     * @param processType the process type of this forecast config
     */
    public void setProcessType(boolean processType);

    /**
     * Returns the to date of this forecast config.
     *
     * @return the to date of this forecast config
     */
    public Date getToDate();

    /**
     * Sets the to date of this forecast config.
     *
     * @param toDate the to date of this forecast config
     */
    public void setToDate(Date toDate);

    /**
     * Returns the version no of this forecast config.
     *
     * @return the version no of this forecast config
     */
    public int getVersionNo();

    /**
     * Sets the version no of this forecast config.
     *
     * @param versionNo the version no of this forecast config
     */
    public void setVersionNo(int versionNo);

    /**
     * Returns the forecast config sid of this forecast config.
     *
     * @return the forecast config sid of this forecast config
     */
    public int getForecastConfigSid();

    /**
     * Sets the forecast config sid of this forecast config.
     *
     * @param forecastConfigSid the forecast config sid of this forecast config
     */
    public void setForecastConfigSid(int forecastConfigSid);

    /**
     * Returns the modified date of this forecast config.
     *
     * @return the modified date of this forecast config
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this forecast config.
     *
     * @param modifiedDate the modified date of this forecast config
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the from date of this forecast config.
     *
     * @return the from date of this forecast config
     */
    public Date getFromDate();

    /**
     * Sets the from date of this forecast config.
     *
     * @param fromDate the from date of this forecast config
     */
    public void setFromDate(Date fromDate);

    /**
     * Returns the proj value of this forecast config.
     *
     * @return the proj value of this forecast config
     */
    public int getProjValue();

    /**
     * Sets the proj value of this forecast config.
     *
     * @param projValue the proj value of this forecast config
     */
    public void setProjValue(int projValue);

    /**
     * Returns the created by of this forecast config.
     *
     * @return the created by of this forecast config
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this forecast config.
     *
     * @param createdBy the created by of this forecast config
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the created date of this forecast config.
     *
     * @return the created date of this forecast config
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this forecast config.
     *
     * @param createdDate the created date of this forecast config
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the proj freq of this forecast config.
     *
     * @return the proj freq of this forecast config
     */
    public int getProjFreq();

    /**
     * Sets the proj freq of this forecast config.
     *
     * @param projFreq the proj freq of this forecast config
     */
    public void setProjFreq(int projFreq);

    /**
     * Returns the hist value of this forecast config.
     *
     * @return the hist value of this forecast config
     */
    public int getHistValue();

    /**
     * Sets the hist value of this forecast config.
     *
     * @param histValue the hist value of this forecast config
     */
    public void setHistValue(int histValue);

    /**
     * Returns the business process type of this forecast config.
     *
     * @return the business process type of this forecast config
     */
    public int getBusinessProcessType();

    /**
     * Sets the business process type of this forecast config.
     *
     * @param businessProcessType the business process type of this forecast config
     */
    public void setBusinessProcessType(int businessProcessType);

    /**
     * Returns the modified by of this forecast config.
     *
     * @return the modified by of this forecast config
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this forecast config.
     *
     * @param modifiedBy the modified by of this forecast config
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the hist freq of this forecast config.
     *
     * @return the hist freq of this forecast config
     */
    public int getHistFreq();

    /**
     * Sets the hist freq of this forecast config.
     *
     * @param histFreq the hist freq of this forecast config
     */
    public void setHistFreq(int histFreq);

    /**
     * Returns the active start date of this forecast config.
     *
     * @return the active start date of this forecast config
     */
    public Date getActiveStartDate();

    /**
     * Sets the active start date of this forecast config.
     *
     * @param activeStartDate the active start date of this forecast config
     */
    public void setActiveStartDate(Date activeStartDate);

    /**
     * Returns the active end date of this forecast config.
     *
     * @return the active end date of this forecast config
     */
    public Date getActiveEndDate();

    /**
     * Sets the active end date of this forecast config.
     *
     * @param activeEndDate the active end date of this forecast config
     */
    public void setActiveEndDate(Date activeEndDate);

    /**
     * Returns the process mode of this forecast config.
     *
     * @return the process mode of this forecast config
     */
    public boolean getProcessMode();

    /**
     * Returns <code>true</code> if this forecast config is process mode.
     *
     * @return <code>true</code> if this forecast config is process mode; <code>false</code> otherwise
     */
    public boolean isProcessMode();

    /**
     * Sets whether this forecast config is process mode.
     *
     * @param processMode the process mode of this forecast config
     */
    public void setProcessMode(boolean processMode);

    /**
     * Returns the historical data interval from of this forecast config.
     *
     * @return the historical data interval from of this forecast config
     */
    public Date getHistoricalDataIntervalFrom();

    /**
     * Sets the historical data interval from of this forecast config.
     *
     * @param historicalDataIntervalFrom the historical data interval from of this forecast config
     */
    public void setHistoricalDataIntervalFrom(Date historicalDataIntervalFrom);

    /**
     * Returns the historical time period from of this forecast config.
     *
     * @return the historical time period from of this forecast config
     */
    public Date getHistoricalTimePeriodFrom();

    /**
     * Sets the historical time period from of this forecast config.
     *
     * @param historicalTimePeriodFrom the historical time period from of this forecast config
     */
    public void setHistoricalTimePeriodFrom(Date historicalTimePeriodFrom);

    /**
     * Returns the proj hist freq of this forecast config.
     *
     * @return the proj hist freq of this forecast config
     */
    public int getProjHistFreq();

    /**
     * Sets the proj hist freq of this forecast config.
     *
     * @param projHistFreq the proj hist freq of this forecast config
     */
    public void setProjHistFreq(int projHistFreq);

    /**
     * Returns the future time period from of this forecast config.
     *
     * @return the future time period from of this forecast config
     */
    public Date getFutureTimePeriodFrom();

    /**
     * Sets the future time period from of this forecast config.
     *
     * @param futureTimePeriodFrom the future time period from of this forecast config
     */
    public void setFutureTimePeriodFrom(Date futureTimePeriodFrom);

    /**
     * Returns the historical data interval to of this forecast config.
     *
     * @return the historical data interval to of this forecast config
     */
    public Date getHistoricalDataIntervalTo();

    /**
     * Sets the historical data interval to of this forecast config.
     *
     * @param historicalDataIntervalTo the historical data interval to of this forecast config
     */
    public void setHistoricalDataIntervalTo(Date historicalDataIntervalTo);

    /**
     * Returns the proj hist value of this forecast config.
     *
     * @return the proj hist value of this forecast config
     */
    public int getProjHistValue();

    /**
     * Sets the proj hist value of this forecast config.
     *
     * @param projHistValue the proj hist value of this forecast config
     */
    public void setProjHistValue(int projHistValue);

    @Override
    public boolean isNew();

    @Override
    public void setNew(boolean n);

    @Override
    public boolean isCachedModel();

    @Override
    public void setCachedModel(boolean cachedModel);

    @Override
    public boolean isEscapedModel();

    @Override
    public Serializable getPrimaryKeyObj();

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    @Override
    public ExpandoBridge getExpandoBridge();

    @Override
    public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

    @Override
    public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    @Override
    public Object clone();

    @Override
    public int compareTo(ForecastConfig forecastConfig);

    @Override
    public int hashCode();

    @Override
    public CacheModel<ForecastConfig> toCacheModel();

    @Override
    public ForecastConfig toEscapedModel();

    @Override
    public ForecastConfig toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
