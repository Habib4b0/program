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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link StMSupplementalDiscMaster}.
 * </p>
 *
 * @author
 * @see StMSupplementalDiscMaster
 * @generated
 */
@ProviderType
public class StMSupplementalDiscMasterWrapper
	implements StMSupplementalDiscMaster,
		ModelWrapper<StMSupplementalDiscMaster> {
	public StMSupplementalDiscMasterWrapper(
		StMSupplementalDiscMaster stMSupplementalDiscMaster) {
		_stMSupplementalDiscMaster = stMSupplementalDiscMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return StMSupplementalDiscMaster.class;
	}

	@Override
	public String getModelClassName() {
		return StMSupplementalDiscMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("actualDiscountRate2", getActualDiscountRate2());
		attributes.put("actualDiscountRate1", getActualDiscountRate1());
		attributes.put("marketType", getMarketType());
		attributes.put("actualMethodology", getActualMethodology());
		attributes.put("actualContractPrice", getActualContractPrice());
		attributes.put("userId", getUserId());
		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("actualDiscount", getActualDiscount());
		attributes.put("sessionId", getSessionId());
		attributes.put("checkRecord", getCheckRecord());
		attributes.put("contractEndDate", getContractEndDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double actualDiscountRate2 = (Double)attributes.get(
				"actualDiscountRate2");

		if (actualDiscountRate2 != null) {
			setActualDiscountRate2(actualDiscountRate2);
		}

		Double actualDiscountRate1 = (Double)attributes.get(
				"actualDiscountRate1");

		if (actualDiscountRate1 != null) {
			setActualDiscountRate1(actualDiscountRate1);
		}

		String marketType = (String)attributes.get("marketType");

		if (marketType != null) {
			setMarketType(marketType);
		}

		String actualMethodology = (String)attributes.get("actualMethodology");

		if (actualMethodology != null) {
			setActualMethodology(actualMethodology);
		}

		Double actualContractPrice = (Double)attributes.get(
				"actualContractPrice");

		if (actualContractPrice != null) {
			setActualContractPrice(actualContractPrice);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Double actualDiscount = (Double)attributes.get("actualDiscount");

		if (actualDiscount != null) {
			setActualDiscount(actualDiscount);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Integer checkRecord = (Integer)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		Date contractEndDate = (Date)attributes.get("contractEndDate");

		if (contractEndDate != null) {
			setContractEndDate(contractEndDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StMSupplementalDiscMasterWrapper((StMSupplementalDiscMaster)_stMSupplementalDiscMaster.clone());
	}

	@Override
	public int compareTo(StMSupplementalDiscMaster stMSupplementalDiscMaster) {
		return _stMSupplementalDiscMaster.compareTo(stMSupplementalDiscMaster);
	}

	/**
	* Returns the actual contract price of this st m supplemental disc master.
	*
	* @return the actual contract price of this st m supplemental disc master
	*/
	@Override
	public double getActualContractPrice() {
		return _stMSupplementalDiscMaster.getActualContractPrice();
	}

	/**
	* Returns the actual discount of this st m supplemental disc master.
	*
	* @return the actual discount of this st m supplemental disc master
	*/
	@Override
	public double getActualDiscount() {
		return _stMSupplementalDiscMaster.getActualDiscount();
	}

	/**
	* Returns the actual discount rate1 of this st m supplemental disc master.
	*
	* @return the actual discount rate1 of this st m supplemental disc master
	*/
	@Override
	public double getActualDiscountRate1() {
		return _stMSupplementalDiscMaster.getActualDiscountRate1();
	}

	/**
	* Returns the actual discount rate2 of this st m supplemental disc master.
	*
	* @return the actual discount rate2 of this st m supplemental disc master
	*/
	@Override
	public double getActualDiscountRate2() {
		return _stMSupplementalDiscMaster.getActualDiscountRate2();
	}

	/**
	* Returns the actual methodology of this st m supplemental disc master.
	*
	* @return the actual methodology of this st m supplemental disc master
	*/
	@Override
	public java.lang.String getActualMethodology() {
		return _stMSupplementalDiscMaster.getActualMethodology();
	}

	/**
	* Returns the check record of this st m supplemental disc master.
	*
	* @return the check record of this st m supplemental disc master
	*/
	@Override
	public int getCheckRecord() {
		return _stMSupplementalDiscMaster.getCheckRecord();
	}

	/**
	* Returns the contract end date of this st m supplemental disc master.
	*
	* @return the contract end date of this st m supplemental disc master
	*/
	@Override
	public Date getContractEndDate() {
		return _stMSupplementalDiscMaster.getContractEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stMSupplementalDiscMaster.getExpandoBridge();
	}

	/**
	* Returns the last modified date of this st m supplemental disc master.
	*
	* @return the last modified date of this st m supplemental disc master
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stMSupplementalDiscMaster.getLastModifiedDate();
	}

	/**
	* Returns the market type of this st m supplemental disc master.
	*
	* @return the market type of this st m supplemental disc master
	*/
	@Override
	public java.lang.String getMarketType() {
		return _stMSupplementalDiscMaster.getMarketType();
	}

	/**
	* Returns the primary key of this st m supplemental disc master.
	*
	* @return the primary key of this st m supplemental disc master
	*/
	@Override
	public com.stpl.app.service.persistence.StMSupplementalDiscMasterPK getPrimaryKey() {
		return _stMSupplementalDiscMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stMSupplementalDiscMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this st m supplemental disc master.
	*
	* @return the projection details sid of this st m supplemental disc master
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _stMSupplementalDiscMaster.getProjectionDetailsSid();
	}

	/**
	* Returns the session ID of this st m supplemental disc master.
	*
	* @return the session ID of this st m supplemental disc master
	*/
	@Override
	public int getSessionId() {
		return _stMSupplementalDiscMaster.getSessionId();
	}

	/**
	* Returns the user ID of this st m supplemental disc master.
	*
	* @return the user ID of this st m supplemental disc master
	*/
	@Override
	public int getUserId() {
		return _stMSupplementalDiscMaster.getUserId();
	}

	@Override
	public int hashCode() {
		return _stMSupplementalDiscMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stMSupplementalDiscMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stMSupplementalDiscMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stMSupplementalDiscMaster.isNew();
	}

	@Override
	public void persist() {
		_stMSupplementalDiscMaster.persist();
	}

	/**
	* Sets the actual contract price of this st m supplemental disc master.
	*
	* @param actualContractPrice the actual contract price of this st m supplemental disc master
	*/
	@Override
	public void setActualContractPrice(double actualContractPrice) {
		_stMSupplementalDiscMaster.setActualContractPrice(actualContractPrice);
	}

	/**
	* Sets the actual discount of this st m supplemental disc master.
	*
	* @param actualDiscount the actual discount of this st m supplemental disc master
	*/
	@Override
	public void setActualDiscount(double actualDiscount) {
		_stMSupplementalDiscMaster.setActualDiscount(actualDiscount);
	}

	/**
	* Sets the actual discount rate1 of this st m supplemental disc master.
	*
	* @param actualDiscountRate1 the actual discount rate1 of this st m supplemental disc master
	*/
	@Override
	public void setActualDiscountRate1(double actualDiscountRate1) {
		_stMSupplementalDiscMaster.setActualDiscountRate1(actualDiscountRate1);
	}

	/**
	* Sets the actual discount rate2 of this st m supplemental disc master.
	*
	* @param actualDiscountRate2 the actual discount rate2 of this st m supplemental disc master
	*/
	@Override
	public void setActualDiscountRate2(double actualDiscountRate2) {
		_stMSupplementalDiscMaster.setActualDiscountRate2(actualDiscountRate2);
	}

	/**
	* Sets the actual methodology of this st m supplemental disc master.
	*
	* @param actualMethodology the actual methodology of this st m supplemental disc master
	*/
	@Override
	public void setActualMethodology(java.lang.String actualMethodology) {
		_stMSupplementalDiscMaster.setActualMethodology(actualMethodology);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stMSupplementalDiscMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the check record of this st m supplemental disc master.
	*
	* @param checkRecord the check record of this st m supplemental disc master
	*/
	@Override
	public void setCheckRecord(int checkRecord) {
		_stMSupplementalDiscMaster.setCheckRecord(checkRecord);
	}

	/**
	* Sets the contract end date of this st m supplemental disc master.
	*
	* @param contractEndDate the contract end date of this st m supplemental disc master
	*/
	@Override
	public void setContractEndDate(Date contractEndDate) {
		_stMSupplementalDiscMaster.setContractEndDate(contractEndDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stMSupplementalDiscMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stMSupplementalDiscMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stMSupplementalDiscMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last modified date of this st m supplemental disc master.
	*
	* @param lastModifiedDate the last modified date of this st m supplemental disc master
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stMSupplementalDiscMaster.setLastModifiedDate(lastModifiedDate);
	}

	/**
	* Sets the market type of this st m supplemental disc master.
	*
	* @param marketType the market type of this st m supplemental disc master
	*/
	@Override
	public void setMarketType(java.lang.String marketType) {
		_stMSupplementalDiscMaster.setMarketType(marketType);
	}

	@Override
	public void setNew(boolean n) {
		_stMSupplementalDiscMaster.setNew(n);
	}

	/**
	* Sets the primary key of this st m supplemental disc master.
	*
	* @param primaryKey the primary key of this st m supplemental disc master
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StMSupplementalDiscMasterPK primaryKey) {
		_stMSupplementalDiscMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stMSupplementalDiscMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this st m supplemental disc master.
	*
	* @param projectionDetailsSid the projection details sid of this st m supplemental disc master
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_stMSupplementalDiscMaster.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the session ID of this st m supplemental disc master.
	*
	* @param sessionId the session ID of this st m supplemental disc master
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stMSupplementalDiscMaster.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st m supplemental disc master.
	*
	* @param userId the user ID of this st m supplemental disc master
	*/
	@Override
	public void setUserId(int userId) {
		_stMSupplementalDiscMaster.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StMSupplementalDiscMaster> toCacheModel() {
		return _stMSupplementalDiscMaster.toCacheModel();
	}

	@Override
	public StMSupplementalDiscMaster toEscapedModel() {
		return new StMSupplementalDiscMasterWrapper(_stMSupplementalDiscMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stMSupplementalDiscMaster.toString();
	}

	@Override
	public StMSupplementalDiscMaster toUnescapedModel() {
		return new StMSupplementalDiscMasterWrapper(_stMSupplementalDiscMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stMSupplementalDiscMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StMSupplementalDiscMasterWrapper)) {
			return false;
		}

		StMSupplementalDiscMasterWrapper stMSupplementalDiscMasterWrapper = (StMSupplementalDiscMasterWrapper)obj;

		if (Objects.equals(_stMSupplementalDiscMaster,
					stMSupplementalDiscMasterWrapper._stMSupplementalDiscMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public StMSupplementalDiscMaster getWrappedModel() {
		return _stMSupplementalDiscMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stMSupplementalDiscMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stMSupplementalDiscMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stMSupplementalDiscMaster.resetOriginalValues();
	}

	private final StMSupplementalDiscMaster _stMSupplementalDiscMaster;
}