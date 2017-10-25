package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the IvldCustomerGtsForecast service. Represents a row in the &quot;IVLD_CUSTOMER_GTS_FORECAST&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastImpl}.
 * </p>
 *
 * @author
 * @see IvldCustomerGtsForecast
 * @see com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastImpl
 * @see com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastModelImpl
 * @generated
 */
public interface IvldCustomerGtsForecastModel extends BaseModel<IvldCustomerGtsForecast> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a ivld customer gts forecast model instance should use the {@link IvldCustomerGtsForecast} interface instead.
     */

    /**
     * Returns the primary key of this ivld customer gts forecast.
     *
     * @return the primary key of this ivld customer gts forecast
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this ivld customer gts forecast.
     *
     * @param primaryKey the primary key of this ivld customer gts forecast
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the price of this ivld customer gts forecast.
     *
     * @return the price of this ivld customer gts forecast
     */
    @AutoEscape
    public String getPrice();

    /**
     * Sets the price of this ivld customer gts forecast.
     *
     * @param price the price of this ivld customer gts forecast
     */
    public void setPrice(String price);

    /**
     * Returns the forecast year of this ivld customer gts forecast.
     *
     * @return the forecast year of this ivld customer gts forecast
     */
    @AutoEscape
    public String getForecastYear();

    /**
     * Sets the forecast year of this ivld customer gts forecast.
     *
     * @param forecastYear the forecast year of this ivld customer gts forecast
     */
    public void setForecastYear(String forecastYear);

    /**
     * Returns the deduction amount of this ivld customer gts forecast.
     *
     * @return the deduction amount of this ivld customer gts forecast
     */
    @AutoEscape
    public String getDeductionAmount();

    /**
     * Sets the deduction amount of this ivld customer gts forecast.
     *
     * @param deductionAmount the deduction amount of this ivld customer gts forecast
     */
    public void setDeductionAmount(String deductionAmount);

    /**
     * Returns the ivld customer gts forecast sid of this ivld customer gts forecast.
     *
     * @return the ivld customer gts forecast sid of this ivld customer gts forecast
     */
    public int getIvldCustomerGtsForecastSid();

    /**
     * Sets the ivld customer gts forecast sid of this ivld customer gts forecast.
     *
     * @param ivldCustomerGtsForecastSid the ivld customer gts forecast sid of this ivld customer gts forecast
     */
    public void setIvldCustomerGtsForecastSid(int ivldCustomerGtsForecastSid);

    /**
     * Returns the deduction ID of this ivld customer gts forecast.
     *
     * @return the deduction ID of this ivld customer gts forecast
     */
    @AutoEscape
    public String getDeductionId();

    /**
     * Sets the deduction ID of this ivld customer gts forecast.
     *
     * @param deductionId the deduction ID of this ivld customer gts forecast
     */
    public void setDeductionId(String deductionId);

    /**
     * Returns the forecast date of this ivld customer gts forecast.
     *
     * @return the forecast date of this ivld customer gts forecast
     */
    public Date getForecastDate();

    /**
     * Sets the forecast date of this ivld customer gts forecast.
     *
     * @param forecastDate the forecast date of this ivld customer gts forecast
     */
    public void setForecastDate(Date forecastDate);

    /**
     * Returns the item ID of this ivld customer gts forecast.
     *
     * @return the item ID of this ivld customer gts forecast
     */
    @AutoEscape
    public String getItemId();

    /**
     * Sets the item ID of this ivld customer gts forecast.
     *
     * @param itemId the item ID of this ivld customer gts forecast
     */
    public void setItemId(String itemId);

    /**
     * Returns the modified date of this ivld customer gts forecast.
     *
     * @return the modified date of this ivld customer gts forecast
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this ivld customer gts forecast.
     *
     * @param modifiedDate the modified date of this ivld customer gts forecast
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the source of this ivld customer gts forecast.
     *
     * @return the source of this ivld customer gts forecast
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this ivld customer gts forecast.
     *
     * @param source the source of this ivld customer gts forecast
     */
    public void setSource(String source);

    /**
     * Returns the created date of this ivld customer gts forecast.
     *
     * @return the created date of this ivld customer gts forecast
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this ivld customer gts forecast.
     *
     * @param createdDate the created date of this ivld customer gts forecast
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the created by of this ivld customer gts forecast.
     *
     * @return the created by of this ivld customer gts forecast
     */
    @AutoEscape
    public String getCreatedBy();

    /**
     * Sets the created by of this ivld customer gts forecast.
     *
     * @param createdBy the created by of this ivld customer gts forecast
     */
    public void setCreatedBy(String createdBy);

    /**
     * Returns the add chg del indicator of this ivld customer gts forecast.
     *
     * @return the add chg del indicator of this ivld customer gts forecast
     */
    @AutoEscape
    public String getAddChgDelIndicator();

    /**
     * Sets the add chg del indicator of this ivld customer gts forecast.
     *
     * @param addChgDelIndicator the add chg del indicator of this ivld customer gts forecast
     */
    public void setAddChgDelIndicator(String addChgDelIndicator);

    /**
     * Returns the error code of this ivld customer gts forecast.
     *
     * @return the error code of this ivld customer gts forecast
     */
    @AutoEscape
    public String getErrorCode();

    /**
     * Sets the error code of this ivld customer gts forecast.
     *
     * @param errorCode the error code of this ivld customer gts forecast
     */
    public void setErrorCode(String errorCode);

    /**
     * Returns the intf inserted date of this ivld customer gts forecast.
     *
     * @return the intf inserted date of this ivld customer gts forecast
     */
    public Date getIntfInsertedDate();

    /**
     * Sets the intf inserted date of this ivld customer gts forecast.
     *
     * @param intfInsertedDate the intf inserted date of this ivld customer gts forecast
     */
    public void setIntfInsertedDate(Date intfInsertedDate);

    /**
     * Returns the modified by of this ivld customer gts forecast.
     *
     * @return the modified by of this ivld customer gts forecast
     */
    @AutoEscape
    public String getModifiedBy();

    /**
     * Sets the modified by of this ivld customer gts forecast.
     *
     * @param modifiedBy the modified by of this ivld customer gts forecast
     */
    public void setModifiedBy(String modifiedBy);

    /**
     * Returns the sales amount of this ivld customer gts forecast.
     *
     * @return the sales amount of this ivld customer gts forecast
     */
    @AutoEscape
    public String getSalesAmount();

    /**
     * Sets the sales amount of this ivld customer gts forecast.
     *
     * @param salesAmount the sales amount of this ivld customer gts forecast
     */
    public void setSalesAmount(String salesAmount);

    /**
     * Returns the reprocessed flag of this ivld customer gts forecast.
     *
     * @return the reprocessed flag of this ivld customer gts forecast
     */
    @AutoEscape
    public String getReprocessedFlag();

    /**
     * Sets the reprocessed flag of this ivld customer gts forecast.
     *
     * @param reprocessedFlag the reprocessed flag of this ivld customer gts forecast
     */
    public void setReprocessedFlag(String reprocessedFlag);

    /**
     * Returns the udc6 of this ivld customer gts forecast.
     *
     * @return the udc6 of this ivld customer gts forecast
     */
    @AutoEscape
    public String getUdc6();

    /**
     * Sets the udc6 of this ivld customer gts forecast.
     *
     * @param udc6 the udc6 of this ivld customer gts forecast
     */
    public void setUdc6(String udc6);

    /**
     * Returns the udc5 of this ivld customer gts forecast.
     *
     * @return the udc5 of this ivld customer gts forecast
     */
    @AutoEscape
    public String getUdc5();

    /**
     * Sets the udc5 of this ivld customer gts forecast.
     *
     * @param udc5 the udc5 of this ivld customer gts forecast
     */
    public void setUdc5(String udc5);

    /**
     * Returns the deduction type of this ivld customer gts forecast.
     *
     * @return the deduction type of this ivld customer gts forecast
     */
    @AutoEscape
    public String getDeductionType();

    /**
     * Sets the deduction type of this ivld customer gts forecast.
     *
     * @param deductionType the deduction type of this ivld customer gts forecast
     */
    public void setDeductionType(String deductionType);

    /**
     * Returns the udc4 of this ivld customer gts forecast.
     *
     * @return the udc4 of this ivld customer gts forecast
     */
    @AutoEscape
    public String getUdc4();

    /**
     * Sets the udc4 of this ivld customer gts forecast.
     *
     * @param udc4 the udc4 of this ivld customer gts forecast
     */
    public void setUdc4(String udc4);

    /**
     * Returns the udc1 of this ivld customer gts forecast.
     *
     * @return the udc1 of this ivld customer gts forecast
     */
    @AutoEscape
    public String getUdc1();

    /**
     * Sets the udc1 of this ivld customer gts forecast.
     *
     * @param udc1 the udc1 of this ivld customer gts forecast
     */
    public void setUdc1(String udc1);

    /**
     * Returns the units of this ivld customer gts forecast.
     *
     * @return the units of this ivld customer gts forecast
     */
    @AutoEscape
    public String getUnits();

    /**
     * Sets the units of this ivld customer gts forecast.
     *
     * @param units the units of this ivld customer gts forecast
     */
    public void setUnits(String units);

    /**
     * Returns the deduction rate of this ivld customer gts forecast.
     *
     * @return the deduction rate of this ivld customer gts forecast
     */
    @AutoEscape
    public String getDeductionRate();

    /**
     * Sets the deduction rate of this ivld customer gts forecast.
     *
     * @param deductionRate the deduction rate of this ivld customer gts forecast
     */
    public void setDeductionRate(String deductionRate);

    /**
     * Returns the udc2 of this ivld customer gts forecast.
     *
     * @return the udc2 of this ivld customer gts forecast
     */
    @AutoEscape
    public String getUdc2();

    /**
     * Sets the udc2 of this ivld customer gts forecast.
     *
     * @param udc2 the udc2 of this ivld customer gts forecast
     */
    public void setUdc2(String udc2);

    /**
     * Returns the udc3 of this ivld customer gts forecast.
     *
     * @return the udc3 of this ivld customer gts forecast
     */
    @AutoEscape
    public String getUdc3();

    /**
     * Sets the udc3 of this ivld customer gts forecast.
     *
     * @param udc3 the udc3 of this ivld customer gts forecast
     */
    public void setUdc3(String udc3);

    /**
     * Returns the reason for failure of this ivld customer gts forecast.
     *
     * @return the reason for failure of this ivld customer gts forecast
     */
    @AutoEscape
    public String getReasonForFailure();

    /**
     * Sets the reason for failure of this ivld customer gts forecast.
     *
     * @param reasonForFailure the reason for failure of this ivld customer gts forecast
     */
    public void setReasonForFailure(String reasonForFailure);

    /**
     * Returns the country of this ivld customer gts forecast.
     *
     * @return the country of this ivld customer gts forecast
     */
    @AutoEscape
    public String getCountry();

    /**
     * Sets the country of this ivld customer gts forecast.
     *
     * @param country the country of this ivld customer gts forecast
     */
    public void setCountry(String country);

    /**
     * Returns the company ID of this ivld customer gts forecast.
     *
     * @return the company ID of this ivld customer gts forecast
     */
    @AutoEscape
    public String getCompanyId();

    /**
     * Sets the company ID of this ivld customer gts forecast.
     *
     * @param companyId the company ID of this ivld customer gts forecast
     */
    public void setCompanyId(String companyId);

    /**
     * Returns the forecast value type of this ivld customer gts forecast.
     *
     * @return the forecast value type of this ivld customer gts forecast
     */
    @AutoEscape
    public String getForecastValueType();

    /**
     * Sets the forecast value type of this ivld customer gts forecast.
     *
     * @param forecastValueType the forecast value type of this ivld customer gts forecast
     */
    public void setForecastValueType(String forecastValueType);

    /**
     * Returns the deduction category of this ivld customer gts forecast.
     *
     * @return the deduction category of this ivld customer gts forecast
     */
    @AutoEscape
    public String getDeductionCategory();

    /**
     * Sets the deduction category of this ivld customer gts forecast.
     *
     * @param deductionCategory the deduction category of this ivld customer gts forecast
     */
    public void setDeductionCategory(String deductionCategory);

    /**
     * Returns the adjustment code of this ivld customer gts forecast.
     *
     * @return the adjustment code of this ivld customer gts forecast
     */
    @AutoEscape
    public String getAdjustmentCode();

    /**
     * Sets the adjustment code of this ivld customer gts forecast.
     *
     * @param adjustmentCode the adjustment code of this ivld customer gts forecast
     */
    public void setAdjustmentCode(String adjustmentCode);

    /**
     * Returns the deduction program type of this ivld customer gts forecast.
     *
     * @return the deduction program type of this ivld customer gts forecast
     */
    @AutoEscape
    public String getDeductionProgramType();

    /**
     * Sets the deduction program type of this ivld customer gts forecast.
     *
     * @param deductionProgramType the deduction program type of this ivld customer gts forecast
     */
    public void setDeductionProgramType(String deductionProgramType);

    /**
     * Returns the customer gts forecast intf ID of this ivld customer gts forecast.
     *
     * @return the customer gts forecast intf ID of this ivld customer gts forecast
     */
    public int getCustomerGtsForecastIntfId();

    /**
     * Sets the customer gts forecast intf ID of this ivld customer gts forecast.
     *
     * @param customerGtsForecastIntfId the customer gts forecast intf ID of this ivld customer gts forecast
     */
    public void setCustomerGtsForecastIntfId(int customerGtsForecastIntfId);

    /**
     * Returns the sales inclusion of this ivld customer gts forecast.
     *
     * @return the sales inclusion of this ivld customer gts forecast
     */
    @AutoEscape
    public String getSalesInclusion();

    /**
     * Sets the sales inclusion of this ivld customer gts forecast.
     *
     * @param salesInclusion the sales inclusion of this ivld customer gts forecast
     */
    public void setSalesInclusion(String salesInclusion);

    /**
     * Returns the forecast ver of this ivld customer gts forecast.
     *
     * @return the forecast ver of this ivld customer gts forecast
     */
    @AutoEscape
    public String getForecastVer();

    /**
     * Sets the forecast ver of this ivld customer gts forecast.
     *
     * @param forecastVer the forecast ver of this ivld customer gts forecast
     */
    public void setForecastVer(String forecastVer);

    /**
     * Returns the batch ID of this ivld customer gts forecast.
     *
     * @return the batch ID of this ivld customer gts forecast
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this ivld customer gts forecast.
     *
     * @param batchId the batch ID of this ivld customer gts forecast
     */
    public void setBatchId(String batchId);

    /**
     * Returns the price type of this ivld customer gts forecast.
     *
     * @return the price type of this ivld customer gts forecast
     */
    @AutoEscape
    public String getPriceType();

    /**
     * Sets the price type of this ivld customer gts forecast.
     *
     * @param priceType the price type of this ivld customer gts forecast
     */
    public void setPriceType(String priceType);

    /**
     * Returns the forecast month of this ivld customer gts forecast.
     *
     * @return the forecast month of this ivld customer gts forecast
     */
    @AutoEscape
    public String getForecastMonth();

    /**
     * Sets the forecast month of this ivld customer gts forecast.
     *
     * @param forecastMonth the forecast month of this ivld customer gts forecast
     */
    public void setForecastMonth(String forecastMonth);

    /**
     * Returns the deduction inclusion of this ivld customer gts forecast.
     *
     * @return the deduction inclusion of this ivld customer gts forecast
     */
    @AutoEscape
    public String getDeductionInclusion();

    /**
     * Sets the deduction inclusion of this ivld customer gts forecast.
     *
     * @param deductionInclusion the deduction inclusion of this ivld customer gts forecast
     */
    public void setDeductionInclusion(String deductionInclusion);

    /**
     * Returns the error field of this ivld customer gts forecast.
     *
     * @return the error field of this ivld customer gts forecast
     */
    @AutoEscape
    public String getErrorField();

    /**
     * Sets the error field of this ivld customer gts forecast.
     *
     * @param errorField the error field of this ivld customer gts forecast
     */
    public void setErrorField(String errorField);

    /**
     * Returns the segment of this ivld customer gts forecast.
     *
     * @return the segment of this ivld customer gts forecast
     */
    @AutoEscape
    public String getSegment();

    /**
     * Sets the segment of this ivld customer gts forecast.
     *
     * @param segment the segment of this ivld customer gts forecast
     */
    public void setSegment(String segment);

    /**
     * Returns the brand of this ivld customer gts forecast.
     *
     * @return the brand of this ivld customer gts forecast
     */
    @AutoEscape
    public String getBrand();

    /**
     * Sets the brand of this ivld customer gts forecast.
     *
     * @param brand the brand of this ivld customer gts forecast
     */
    public void setBrand(String brand);

    /**
     * Returns the forecast name of this ivld customer gts forecast.
     *
     * @return the forecast name of this ivld customer gts forecast
     */
    @AutoEscape
    public String getForecastName();

    /**
     * Sets the forecast name of this ivld customer gts forecast.
     *
     * @param forecastName the forecast name of this ivld customer gts forecast
     */
    public void setForecastName(String forecastName);

    /**
     * Returns the check record of this ivld customer gts forecast.
     *
     * @return the check record of this ivld customer gts forecast
     */
    public boolean getCheckRecord();

    /**
     * Returns <code>true</code> if this ivld customer gts forecast is check record.
     *
     * @return <code>true</code> if this ivld customer gts forecast is check record; <code>false</code> otherwise
     */
    public boolean isCheckRecord();

    /**
     * Sets whether this ivld customer gts forecast is check record.
     *
     * @param checkRecord the check record of this ivld customer gts forecast
     */
    public void setCheckRecord(boolean checkRecord);

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
    public int compareTo(IvldCustomerGtsForecast ivldCustomerGtsForecast);

    @Override
    public int hashCode();

    @Override
    public CacheModel<IvldCustomerGtsForecast> toCacheModel();

    @Override
    public IvldCustomerGtsForecast toEscapedModel();

    @Override
    public IvldCustomerGtsForecast toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
