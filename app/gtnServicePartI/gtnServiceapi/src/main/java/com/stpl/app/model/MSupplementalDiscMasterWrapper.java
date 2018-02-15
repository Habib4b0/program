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
 * This class is a wrapper for {@link MSupplementalDiscMaster}.
 * </p>
 *
 * @author
 * @see MSupplementalDiscMaster
 * @generated
 */
@ProviderType
public class MSupplementalDiscMasterWrapper implements MSupplementalDiscMaster,
	ModelWrapper<MSupplementalDiscMaster> {
	public MSupplementalDiscMasterWrapper(
		MSupplementalDiscMaster mSupplementalDiscMaster) {
		_mSupplementalDiscMaster = mSupplementalDiscMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return MSupplementalDiscMaster.class;
	}

	@Override
	public String getModelClassName() {
		return MSupplementalDiscMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("actualDiscountRate2", getActualDiscountRate2());
		attributes.put("actualDiscountRate1", getActualDiscountRate1());
		attributes.put("marketType", getMarketType());
		attributes.put("actualMethodology", getActualMethodology());
		attributes.put("actualContractPrice", getActualContractPrice());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("actualDiscount", getActualDiscount());
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

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Double actualDiscount = (Double)attributes.get("actualDiscount");

		if (actualDiscount != null) {
			setActualDiscount(actualDiscount);
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
		return new MSupplementalDiscMasterWrapper((MSupplementalDiscMaster)_mSupplementalDiscMaster.clone());
	}

	@Override
	public int compareTo(MSupplementalDiscMaster mSupplementalDiscMaster) {
		return _mSupplementalDiscMaster.compareTo(mSupplementalDiscMaster);
	}

	/**
	* Returns the actual contract price of this m supplemental disc master.
	*
	* @return the actual contract price of this m supplemental disc master
	*/
	@Override
	public double getActualContractPrice() {
		return _mSupplementalDiscMaster.getActualContractPrice();
	}

	/**
	* Returns the actual discount of this m supplemental disc master.
	*
	* @return the actual discount of this m supplemental disc master
	*/
	@Override
	public double getActualDiscount() {
		return _mSupplementalDiscMaster.getActualDiscount();
	}

	/**
	* Returns the actual discount rate1 of this m supplemental disc master.
	*
	* @return the actual discount rate1 of this m supplemental disc master
	*/
	@Override
	public double getActualDiscountRate1() {
		return _mSupplementalDiscMaster.getActualDiscountRate1();
	}

	/**
	* Returns the actual discount rate2 of this m supplemental disc master.
	*
	* @return the actual discount rate2 of this m supplemental disc master
	*/
	@Override
	public double getActualDiscountRate2() {
		return _mSupplementalDiscMaster.getActualDiscountRate2();
	}

	/**
	* Returns the actual methodology of this m supplemental disc master.
	*
	* @return the actual methodology of this m supplemental disc master
	*/
	@Override
	public java.lang.String getActualMethodology() {
		return _mSupplementalDiscMaster.getActualMethodology();
	}

	/**
	* Returns the check record of this m supplemental disc master.
	*
	* @return the check record of this m supplemental disc master
	*/
	@Override
	public int getCheckRecord() {
		return _mSupplementalDiscMaster.getCheckRecord();
	}

	/**
	* Returns the contract end date of this m supplemental disc master.
	*
	* @return the contract end date of this m supplemental disc master
	*/
	@Override
	public Date getContractEndDate() {
		return _mSupplementalDiscMaster.getContractEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mSupplementalDiscMaster.getExpandoBridge();
	}

	/**
	* Returns the market type of this m supplemental disc master.
	*
	* @return the market type of this m supplemental disc master
	*/
	@Override
	public java.lang.String getMarketType() {
		return _mSupplementalDiscMaster.getMarketType();
	}

	/**
	* Returns the primary key of this m supplemental disc master.
	*
	* @return the primary key of this m supplemental disc master
	*/
	@Override
	public int getPrimaryKey() {
		return _mSupplementalDiscMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mSupplementalDiscMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this m supplemental disc master.
	*
	* @return the projection details sid of this m supplemental disc master
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _mSupplementalDiscMaster.getProjectionDetailsSid();
	}

	@Override
	public int hashCode() {
		return _mSupplementalDiscMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _mSupplementalDiscMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _mSupplementalDiscMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _mSupplementalDiscMaster.isNew();
	}

	@Override
	public void persist() {
		_mSupplementalDiscMaster.persist();
	}

	/**
	* Sets the actual contract price of this m supplemental disc master.
	*
	* @param actualContractPrice the actual contract price of this m supplemental disc master
	*/
	@Override
	public void setActualContractPrice(double actualContractPrice) {
		_mSupplementalDiscMaster.setActualContractPrice(actualContractPrice);
	}

	/**
	* Sets the actual discount of this m supplemental disc master.
	*
	* @param actualDiscount the actual discount of this m supplemental disc master
	*/
	@Override
	public void setActualDiscount(double actualDiscount) {
		_mSupplementalDiscMaster.setActualDiscount(actualDiscount);
	}

	/**
	* Sets the actual discount rate1 of this m supplemental disc master.
	*
	* @param actualDiscountRate1 the actual discount rate1 of this m supplemental disc master
	*/
	@Override
	public void setActualDiscountRate1(double actualDiscountRate1) {
		_mSupplementalDiscMaster.setActualDiscountRate1(actualDiscountRate1);
	}

	/**
	* Sets the actual discount rate2 of this m supplemental disc master.
	*
	* @param actualDiscountRate2 the actual discount rate2 of this m supplemental disc master
	*/
	@Override
	public void setActualDiscountRate2(double actualDiscountRate2) {
		_mSupplementalDiscMaster.setActualDiscountRate2(actualDiscountRate2);
	}

	/**
	* Sets the actual methodology of this m supplemental disc master.
	*
	* @param actualMethodology the actual methodology of this m supplemental disc master
	*/
	@Override
	public void setActualMethodology(java.lang.String actualMethodology) {
		_mSupplementalDiscMaster.setActualMethodology(actualMethodology);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mSupplementalDiscMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the check record of this m supplemental disc master.
	*
	* @param checkRecord the check record of this m supplemental disc master
	*/
	@Override
	public void setCheckRecord(int checkRecord) {
		_mSupplementalDiscMaster.setCheckRecord(checkRecord);
	}

	/**
	* Sets the contract end date of this m supplemental disc master.
	*
	* @param contractEndDate the contract end date of this m supplemental disc master
	*/
	@Override
	public void setContractEndDate(Date contractEndDate) {
		_mSupplementalDiscMaster.setContractEndDate(contractEndDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_mSupplementalDiscMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_mSupplementalDiscMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_mSupplementalDiscMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the market type of this m supplemental disc master.
	*
	* @param marketType the market type of this m supplemental disc master
	*/
	@Override
	public void setMarketType(java.lang.String marketType) {
		_mSupplementalDiscMaster.setMarketType(marketType);
	}

	@Override
	public void setNew(boolean n) {
		_mSupplementalDiscMaster.setNew(n);
	}

	/**
	* Sets the primary key of this m supplemental disc master.
	*
	* @param primaryKey the primary key of this m supplemental disc master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_mSupplementalDiscMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_mSupplementalDiscMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this m supplemental disc master.
	*
	* @param projectionDetailsSid the projection details sid of this m supplemental disc master
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_mSupplementalDiscMaster.setProjectionDetailsSid(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MSupplementalDiscMaster> toCacheModel() {
		return _mSupplementalDiscMaster.toCacheModel();
	}

	@Override
	public MSupplementalDiscMaster toEscapedModel() {
		return new MSupplementalDiscMasterWrapper(_mSupplementalDiscMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _mSupplementalDiscMaster.toString();
	}

	@Override
	public MSupplementalDiscMaster toUnescapedModel() {
		return new MSupplementalDiscMasterWrapper(_mSupplementalDiscMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _mSupplementalDiscMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MSupplementalDiscMasterWrapper)) {
			return false;
		}

		MSupplementalDiscMasterWrapper mSupplementalDiscMasterWrapper = (MSupplementalDiscMasterWrapper)obj;

		if (Objects.equals(_mSupplementalDiscMaster,
					mSupplementalDiscMasterWrapper._mSupplementalDiscMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public MSupplementalDiscMaster getWrappedModel() {
		return _mSupplementalDiscMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _mSupplementalDiscMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _mSupplementalDiscMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_mSupplementalDiscMaster.resetOriginalValues();
	}

	private final MSupplementalDiscMaster _mSupplementalDiscMaster;
}