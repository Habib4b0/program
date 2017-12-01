/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the IvldDemandForecast service. Represents a row in the &quot;IVLD_DEMAND_FORECAST&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.IvldDemandForecastModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.IvldDemandForecastImpl}.
 * </p>
 *
 * @author
 * @see IvldDemandForecast
 * @see com.stpl.app.model.impl.IvldDemandForecastImpl
 * @see com.stpl.app.model.impl.IvldDemandForecastModelImpl
 * @generated
 */
@ProviderType
public interface IvldDemandForecastModel extends BaseModel<IvldDemandForecast> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ivld demand forecast model instance should use the {@link IvldDemandForecast} interface instead.
	 */

	/**
	 * Returns the primary key of this ivld demand forecast.
	 *
	 * @return the primary key of this ivld demand forecast
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this ivld demand forecast.
	 *
	 * @param primaryKey the primary key of this ivld demand forecast
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the forecast year of this ivld demand forecast.
	 *
	 * @return the forecast year of this ivld demand forecast
	 */
	@AutoEscape
	public String getForecastYear();

	/**
	 * Sets the forecast year of this ivld demand forecast.
	 *
	 * @param forecastYear the forecast year of this ivld demand forecast
	 */
	public void setForecastYear(String forecastYear);

	/**
	 * Returns the gross units of this ivld demand forecast.
	 *
	 * @return the gross units of this ivld demand forecast
	 */
	@AutoEscape
	public String getGrossUnits();

	/**
	 * Sets the gross units of this ivld demand forecast.
	 *
	 * @param grossUnits the gross units of this ivld demand forecast
	 */
	public void setGrossUnits(String grossUnits);

	/**
	 * Returns the total demand units of this ivld demand forecast.
	 *
	 * @return the total demand units of this ivld demand forecast
	 */
	@AutoEscape
	public String getTotalDemandUnits();

	/**
	 * Sets the total demand units of this ivld demand forecast.
	 *
	 * @param totalDemandUnits the total demand units of this ivld demand forecast
	 */
	public void setTotalDemandUnits(String totalDemandUnits);

	/**
	 * Returns the item ID of this ivld demand forecast.
	 *
	 * @return the item ID of this ivld demand forecast
	 */
	@AutoEscape
	public String getItemId();

	/**
	 * Sets the item ID of this ivld demand forecast.
	 *
	 * @param itemId the item ID of this ivld demand forecast
	 */
	public void setItemId(String itemId);

	/**
	 * Returns the modified date of this ivld demand forecast.
	 *
	 * @return the modified date of this ivld demand forecast
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this ivld demand forecast.
	 *
	 * @param modifiedDate the modified date of this ivld demand forecast
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the organization key of this ivld demand forecast.
	 *
	 * @return the organization key of this ivld demand forecast
	 */
	@AutoEscape
	public String getOrganizationKey();

	/**
	 * Sets the organization key of this ivld demand forecast.
	 *
	 * @param organizationKey the organization key of this ivld demand forecast
	 */
	public void setOrganizationKey(String organizationKey);

	/**
	 * Returns the ivld demand forecast sid of this ivld demand forecast.
	 *
	 * @return the ivld demand forecast sid of this ivld demand forecast
	 */
	public int getIvldDemandForecastSid();

	/**
	 * Sets the ivld demand forecast sid of this ivld demand forecast.
	 *
	 * @param ivldDemandForecastSid the ivld demand forecast sid of this ivld demand forecast
	 */
	public void setIvldDemandForecastSid(int ivldDemandForecastSid);

	/**
	 * Returns the source of this ivld demand forecast.
	 *
	 * @return the source of this ivld demand forecast
	 */
	@AutoEscape
	public String getSource();

	/**
	 * Sets the source of this ivld demand forecast.
	 *
	 * @param source the source of this ivld demand forecast
	 */
	public void setSource(String source);

	/**
	 * Returns the market share ratio of this ivld demand forecast.
	 *
	 * @return the market share ratio of this ivld demand forecast
	 */
	@AutoEscape
	public String getMarketShareRatio();

	/**
	 * Sets the market share ratio of this ivld demand forecast.
	 *
	 * @param marketShareRatio the market share ratio of this ivld demand forecast
	 */
	public void setMarketShareRatio(String marketShareRatio);

	/**
	 * Returns the created by of this ivld demand forecast.
	 *
	 * @return the created by of this ivld demand forecast
	 */
	@AutoEscape
	public String getCreatedBy();

	/**
	 * Sets the created by of this ivld demand forecast.
	 *
	 * @param createdBy the created by of this ivld demand forecast
	 */
	public void setCreatedBy(String createdBy);

	/**
	 * Returns the created date of this ivld demand forecast.
	 *
	 * @return the created date of this ivld demand forecast
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this ivld demand forecast.
	 *
	 * @param createdDate the created date of this ivld demand forecast
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the add chg del indicator of this ivld demand forecast.
	 *
	 * @return the add chg del indicator of this ivld demand forecast
	 */
	@AutoEscape
	public String getAddChgDelIndicator();

	/**
	 * Sets the add chg del indicator of this ivld demand forecast.
	 *
	 * @param addChgDelIndicator the add chg del indicator of this ivld demand forecast
	 */
	public void setAddChgDelIndicator(String addChgDelIndicator);

	/**
	 * Returns the item identifier of this ivld demand forecast.
	 *
	 * @return the item identifier of this ivld demand forecast
	 */
	@AutoEscape
	public String getItemIdentifier();

	/**
	 * Sets the item identifier of this ivld demand forecast.
	 *
	 * @param itemIdentifier the item identifier of this ivld demand forecast
	 */
	public void setItemIdentifier(String itemIdentifier);

	/**
	 * Returns the error code of this ivld demand forecast.
	 *
	 * @return the error code of this ivld demand forecast
	 */
	@AutoEscape
	public String getErrorCode();

	/**
	 * Sets the error code of this ivld demand forecast.
	 *
	 * @param errorCode the error code of this ivld demand forecast
	 */
	public void setErrorCode(String errorCode);

	/**
	 * Returns the intf inserted date of this ivld demand forecast.
	 *
	 * @return the intf inserted date of this ivld demand forecast
	 */
	@AutoEscape
	public String getIntfInsertedDate();

	/**
	 * Sets the intf inserted date of this ivld demand forecast.
	 *
	 * @param intfInsertedDate the intf inserted date of this ivld demand forecast
	 */
	public void setIntfInsertedDate(String intfInsertedDate);

	/**
	 * Returns the modified by of this ivld demand forecast.
	 *
	 * @return the modified by of this ivld demand forecast
	 */
	@AutoEscape
	public String getModifiedBy();

	/**
	 * Sets the modified by of this ivld demand forecast.
	 *
	 * @param modifiedBy the modified by of this ivld demand forecast
	 */
	public void setModifiedBy(String modifiedBy);

	/**
	 * Returns the market share units of this ivld demand forecast.
	 *
	 * @return the market share units of this ivld demand forecast
	 */
	@AutoEscape
	public String getMarketShareUnits();

	/**
	 * Sets the market share units of this ivld demand forecast.
	 *
	 * @param marketShareUnits the market share units of this ivld demand forecast
	 */
	public void setMarketShareUnits(String marketShareUnits);

	/**
	 * Returns the inventory unit change of this ivld demand forecast.
	 *
	 * @return the inventory unit change of this ivld demand forecast
	 */
	@AutoEscape
	public String getInventoryUnitChange();

	/**
	 * Sets the inventory unit change of this ivld demand forecast.
	 *
	 * @param inventoryUnitChange the inventory unit change of this ivld demand forecast
	 */
	public void setInventoryUnitChange(String inventoryUnitChange);

	/**
	 * Returns the reprocessed flag of this ivld demand forecast.
	 *
	 * @return the reprocessed flag of this ivld demand forecast
	 */
	@AutoEscape
	public String getReprocessedFlag();

	/**
	 * Sets the reprocessed flag of this ivld demand forecast.
	 *
	 * @param reprocessedFlag the reprocessed flag of this ivld demand forecast
	 */
	public void setReprocessedFlag(String reprocessedFlag);

	/**
	 * Returns the uncaptured units ratio of this ivld demand forecast.
	 *
	 * @return the uncaptured units ratio of this ivld demand forecast
	 */
	@AutoEscape
	public String getUncapturedUnitsRatio();

	/**
	 * Sets the uncaptured units ratio of this ivld demand forecast.
	 *
	 * @param uncapturedUnitsRatio the uncaptured units ratio of this ivld demand forecast
	 */
	public void setUncapturedUnitsRatio(String uncapturedUnitsRatio);

	/**
	 * Returns the reason for failure of this ivld demand forecast.
	 *
	 * @return the reason for failure of this ivld demand forecast
	 */
	@AutoEscape
	public String getReasonForFailure();

	/**
	 * Sets the reason for failure of this ivld demand forecast.
	 *
	 * @param reasonForFailure the reason for failure of this ivld demand forecast
	 */
	public void setReasonForFailure(String reasonForFailure);

	/**
	 * Returns the country of this ivld demand forecast.
	 *
	 * @return the country of this ivld demand forecast
	 */
	@AutoEscape
	public String getCountry();

	/**
	 * Sets the country of this ivld demand forecast.
	 *
	 * @param country the country of this ivld demand forecast
	 */
	public void setCountry(String country);

	/**
	 * Returns the forecast type of this ivld demand forecast.
	 *
	 * @return the forecast type of this ivld demand forecast
	 */
	@AutoEscape
	public String getForecastType();

	/**
	 * Sets the forecast type of this ivld demand forecast.
	 *
	 * @param forecastType the forecast type of this ivld demand forecast
	 */
	public void setForecastType(String forecastType);

	/**
	 * Returns the brand ID of this ivld demand forecast.
	 *
	 * @return the brand ID of this ivld demand forecast
	 */
	@AutoEscape
	public String getBrandId();

	/**
	 * Sets the brand ID of this ivld demand forecast.
	 *
	 * @param brandId the brand ID of this ivld demand forecast
	 */
	public void setBrandId(String brandId);

	/**
	 * Returns the demand forecast interface ID of this ivld demand forecast.
	 *
	 * @return the demand forecast interface ID of this ivld demand forecast
	 */
	@AutoEscape
	public String getDemandForecastInterfaceId();

	/**
	 * Sets the demand forecast interface ID of this ivld demand forecast.
	 *
	 * @param demandForecastInterfaceId the demand forecast interface ID of this ivld demand forecast
	 */
	public void setDemandForecastInterfaceId(String demandForecastInterfaceId);

	/**
	 * Returns the uncaptured units of this ivld demand forecast.
	 *
	 * @return the uncaptured units of this ivld demand forecast
	 */
	@AutoEscape
	public String getUncapturedUnits();

	/**
	 * Sets the uncaptured units of this ivld demand forecast.
	 *
	 * @param uncapturedUnits the uncaptured units of this ivld demand forecast
	 */
	public void setUncapturedUnits(String uncapturedUnits);

	/**
	 * Returns the gross price of this ivld demand forecast.
	 *
	 * @return the gross price of this ivld demand forecast
	 */
	@AutoEscape
	public String getGrossPrice();

	/**
	 * Sets the gross price of this ivld demand forecast.
	 *
	 * @param grossPrice the gross price of this ivld demand forecast
	 */
	public void setGrossPrice(String grossPrice);

	/**
	 * Returns the gross amount of this ivld demand forecast.
	 *
	 * @return the gross amount of this ivld demand forecast
	 */
	@AutoEscape
	public String getGrossAmount();

	/**
	 * Sets the gross amount of this ivld demand forecast.
	 *
	 * @param grossAmount the gross amount of this ivld demand forecast
	 */
	public void setGrossAmount(String grossAmount);

	/**
	 * Returns the item identifier code qualifier of this ivld demand forecast.
	 *
	 * @return the item identifier code qualifier of this ivld demand forecast
	 */
	@AutoEscape
	public String getItemIdentifierCodeQualifier();

	/**
	 * Sets the item identifier code qualifier of this ivld demand forecast.
	 *
	 * @param itemIdentifierCodeQualifier the item identifier code qualifier of this ivld demand forecast
	 */
	public void setItemIdentifierCodeQualifier(
		String itemIdentifierCodeQualifier);

	/**
	 * Returns the forecast ver of this ivld demand forecast.
	 *
	 * @return the forecast ver of this ivld demand forecast
	 */
	@AutoEscape
	public String getForecastVer();

	/**
	 * Sets the forecast ver of this ivld demand forecast.
	 *
	 * @param forecastVer the forecast ver of this ivld demand forecast
	 */
	public void setForecastVer(String forecastVer);

	/**
	 * Returns the batch ID of this ivld demand forecast.
	 *
	 * @return the batch ID of this ivld demand forecast
	 */
	@AutoEscape
	public String getBatchId();

	/**
	 * Sets the batch ID of this ivld demand forecast.
	 *
	 * @param batchId the batch ID of this ivld demand forecast
	 */
	public void setBatchId(String batchId);

	/**
	 * Returns the forecast month of this ivld demand forecast.
	 *
	 * @return the forecast month of this ivld demand forecast
	 */
	@AutoEscape
	public String getForecastMonth();

	/**
	 * Sets the forecast month of this ivld demand forecast.
	 *
	 * @param forecastMonth the forecast month of this ivld demand forecast
	 */
	public void setForecastMonth(String forecastMonth);

	/**
	 * Returns the error field of this ivld demand forecast.
	 *
	 * @return the error field of this ivld demand forecast
	 */
	@AutoEscape
	public String getErrorField();

	/**
	 * Sets the error field of this ivld demand forecast.
	 *
	 * @param errorField the error field of this ivld demand forecast
	 */
	public void setErrorField(String errorField);

	/**
	 * Returns the net sales price of this ivld demand forecast.
	 *
	 * @return the net sales price of this ivld demand forecast
	 */
	@AutoEscape
	public String getNetSalesPrice();

	/**
	 * Sets the net sales price of this ivld demand forecast.
	 *
	 * @param netSalesPrice the net sales price of this ivld demand forecast
	 */
	public void setNetSalesPrice(String netSalesPrice);

	/**
	 * Returns the net sales amount of this ivld demand forecast.
	 *
	 * @return the net sales amount of this ivld demand forecast
	 */
	@AutoEscape
	public String getNetSalesAmount();

	/**
	 * Sets the net sales amount of this ivld demand forecast.
	 *
	 * @param netSalesAmount the net sales amount of this ivld demand forecast
	 */
	public void setNetSalesAmount(String netSalesAmount);

	/**
	 * Returns the segment of this ivld demand forecast.
	 *
	 * @return the segment of this ivld demand forecast
	 */
	@AutoEscape
	public String getSegment();

	/**
	 * Sets the segment of this ivld demand forecast.
	 *
	 * @param segment the segment of this ivld demand forecast
	 */
	public void setSegment(String segment);

	/**
	 * Returns the total demand amount of this ivld demand forecast.
	 *
	 * @return the total demand amount of this ivld demand forecast
	 */
	@AutoEscape
	public String getTotalDemandAmount();

	/**
	 * Sets the total demand amount of this ivld demand forecast.
	 *
	 * @param totalDemandAmount the total demand amount of this ivld demand forecast
	 */
	public void setTotalDemandAmount(String totalDemandAmount);

	/**
	 * Returns the forecast name of this ivld demand forecast.
	 *
	 * @return the forecast name of this ivld demand forecast
	 */
	@AutoEscape
	public String getForecastName();

	/**
	 * Sets the forecast name of this ivld demand forecast.
	 *
	 * @param forecastName the forecast name of this ivld demand forecast
	 */
	public void setForecastName(String forecastName);

	/**
	 * Returns the market size units of this ivld demand forecast.
	 *
	 * @return the market size units of this ivld demand forecast
	 */
	@AutoEscape
	public String getMarketSizeUnits();

	/**
	 * Sets the market size units of this ivld demand forecast.
	 *
	 * @param marketSizeUnits the market size units of this ivld demand forecast
	 */
	public void setMarketSizeUnits(String marketSizeUnits);

	/**
	 * Returns the check record of this ivld demand forecast.
	 *
	 * @return the check record of this ivld demand forecast
	 */
	public boolean getCheckRecord();

	/**
	 * Returns <code>true</code> if this ivld demand forecast is check record.
	 *
	 * @return <code>true</code> if this ivld demand forecast is check record; <code>false</code> otherwise
	 */
	public boolean isCheckRecord();

	/**
	 * Sets whether this ivld demand forecast is check record.
	 *
	 * @param checkRecord the check record of this ivld demand forecast
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
	public int compareTo(IvldDemandForecast ivldDemandForecast);

	@Override
	public int hashCode();

	@Override
	public CacheModel<IvldDemandForecast> toCacheModel();

	@Override
	public IvldDemandForecast toEscapedModel();

	@Override
	public IvldDemandForecast toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}