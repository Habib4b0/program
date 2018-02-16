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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ChAssumptions}.
 * </p>
 *
 * @author
 * @see ChAssumptions
 * @generated
 */
@ProviderType
public class ChAssumptionsWrapper implements ChAssumptions,
	ModelWrapper<ChAssumptions> {
	public ChAssumptionsWrapper(ChAssumptions chAssumptions) {
		_chAssumptions = chAssumptions;
	}

	@Override
	public Class<?> getModelClass() {
		return ChAssumptions.class;
	}

	@Override
	public String getModelClassName() {
		return ChAssumptions.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("parent", getParent());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("commentary", getCommentary());
		attributes.put("quarter", getQuarter());
		attributes.put("totalDiscountPercentChange",
			getTotalDiscountPercentChange());
		attributes.put("reasonCodes", getReasonCodes());
		attributes.put("year", getYear());
		attributes.put("totalDiscountPercentProjected",
			getTotalDiscountPercentProjected());
		attributes.put("totalDiscountPercentPrior",
			getTotalDiscountPercentPrior());
		attributes.put("chAssumptionsSid", getChAssumptionsSid());
		attributes.put("totalDiscountChange", getTotalDiscountChange());
		attributes.put("totalDiscountProjected", getTotalDiscountProjected());
		attributes.put("camId", getCamId());
		attributes.put("grossTradeSales", getGrossTradeSales());
		attributes.put("totalDiscountPrior", getTotalDiscountPrior());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Boolean parent = (Boolean)attributes.get("parent");

		if (parent != null) {
			setParent(parent);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		String commentary = (String)attributes.get("commentary");

		if (commentary != null) {
			setCommentary(commentary);
		}

		Integer quarter = (Integer)attributes.get("quarter");

		if (quarter != null) {
			setQuarter(quarter);
		}

		Double totalDiscountPercentChange = (Double)attributes.get(
				"totalDiscountPercentChange");

		if (totalDiscountPercentChange != null) {
			setTotalDiscountPercentChange(totalDiscountPercentChange);
		}

		String reasonCodes = (String)attributes.get("reasonCodes");

		if (reasonCodes != null) {
			setReasonCodes(reasonCodes);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		Double totalDiscountPercentProjected = (Double)attributes.get(
				"totalDiscountPercentProjected");

		if (totalDiscountPercentProjected != null) {
			setTotalDiscountPercentProjected(totalDiscountPercentProjected);
		}

		Double totalDiscountPercentPrior = (Double)attributes.get(
				"totalDiscountPercentPrior");

		if (totalDiscountPercentPrior != null) {
			setTotalDiscountPercentPrior(totalDiscountPercentPrior);
		}

		Integer chAssumptionsSid = (Integer)attributes.get("chAssumptionsSid");

		if (chAssumptionsSid != null) {
			setChAssumptionsSid(chAssumptionsSid);
		}

		Double totalDiscountChange = (Double)attributes.get(
				"totalDiscountChange");

		if (totalDiscountChange != null) {
			setTotalDiscountChange(totalDiscountChange);
		}

		Double totalDiscountProjected = (Double)attributes.get(
				"totalDiscountProjected");

		if (totalDiscountProjected != null) {
			setTotalDiscountProjected(totalDiscountProjected);
		}

		Integer camId = (Integer)attributes.get("camId");

		if (camId != null) {
			setCamId(camId);
		}

		Double grossTradeSales = (Double)attributes.get("grossTradeSales");

		if (grossTradeSales != null) {
			setGrossTradeSales(grossTradeSales);
		}

		Double totalDiscountPrior = (Double)attributes.get("totalDiscountPrior");

		if (totalDiscountPrior != null) {
			setTotalDiscountPrior(totalDiscountPrior);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ChAssumptionsWrapper((ChAssumptions)_chAssumptions.clone());
	}

	@Override
	public int compareTo(ChAssumptions chAssumptions) {
		return _chAssumptions.compareTo(chAssumptions);
	}

	/**
	* Returns the cam ID of this ch assumptions.
	*
	* @return the cam ID of this ch assumptions
	*/
	@Override
	public int getCamId() {
		return _chAssumptions.getCamId();
	}

	/**
	* Returns the ch assumptions sid of this ch assumptions.
	*
	* @return the ch assumptions sid of this ch assumptions
	*/
	@Override
	public int getChAssumptionsSid() {
		return _chAssumptions.getChAssumptionsSid();
	}

	/**
	* Returns the commentary of this ch assumptions.
	*
	* @return the commentary of this ch assumptions
	*/
	@Override
	public java.lang.String getCommentary() {
		return _chAssumptions.getCommentary();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _chAssumptions.getExpandoBridge();
	}

	/**
	* Returns the gross trade sales of this ch assumptions.
	*
	* @return the gross trade sales of this ch assumptions
	*/
	@Override
	public double getGrossTradeSales() {
		return _chAssumptions.getGrossTradeSales();
	}

	/**
	* Returns the parent of this ch assumptions.
	*
	* @return the parent of this ch assumptions
	*/
	@Override
	public boolean getParent() {
		return _chAssumptions.getParent();
	}

	/**
	* Returns the primary key of this ch assumptions.
	*
	* @return the primary key of this ch assumptions
	*/
	@Override
	public int getPrimaryKey() {
		return _chAssumptions.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _chAssumptions.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this ch assumptions.
	*
	* @return the projection details sid of this ch assumptions
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _chAssumptions.getProjectionDetailsSid();
	}

	/**
	* Returns the quarter of this ch assumptions.
	*
	* @return the quarter of this ch assumptions
	*/
	@Override
	public int getQuarter() {
		return _chAssumptions.getQuarter();
	}

	/**
	* Returns the reason codes of this ch assumptions.
	*
	* @return the reason codes of this ch assumptions
	*/
	@Override
	public java.lang.String getReasonCodes() {
		return _chAssumptions.getReasonCodes();
	}

	/**
	* Returns the total discount change of this ch assumptions.
	*
	* @return the total discount change of this ch assumptions
	*/
	@Override
	public double getTotalDiscountChange() {
		return _chAssumptions.getTotalDiscountChange();
	}

	/**
	* Returns the total discount percent change of this ch assumptions.
	*
	* @return the total discount percent change of this ch assumptions
	*/
	@Override
	public double getTotalDiscountPercentChange() {
		return _chAssumptions.getTotalDiscountPercentChange();
	}

	/**
	* Returns the total discount percent prior of this ch assumptions.
	*
	* @return the total discount percent prior of this ch assumptions
	*/
	@Override
	public double getTotalDiscountPercentPrior() {
		return _chAssumptions.getTotalDiscountPercentPrior();
	}

	/**
	* Returns the total discount percent projected of this ch assumptions.
	*
	* @return the total discount percent projected of this ch assumptions
	*/
	@Override
	public double getTotalDiscountPercentProjected() {
		return _chAssumptions.getTotalDiscountPercentProjected();
	}

	/**
	* Returns the total discount prior of this ch assumptions.
	*
	* @return the total discount prior of this ch assumptions
	*/
	@Override
	public double getTotalDiscountPrior() {
		return _chAssumptions.getTotalDiscountPrior();
	}

	/**
	* Returns the total discount projected of this ch assumptions.
	*
	* @return the total discount projected of this ch assumptions
	*/
	@Override
	public double getTotalDiscountProjected() {
		return _chAssumptions.getTotalDiscountProjected();
	}

	/**
	* Returns the year of this ch assumptions.
	*
	* @return the year of this ch assumptions
	*/
	@Override
	public int getYear() {
		return _chAssumptions.getYear();
	}

	@Override
	public int hashCode() {
		return _chAssumptions.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _chAssumptions.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _chAssumptions.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _chAssumptions.isNew();
	}

	/**
	* Returns <code>true</code> if this ch assumptions is parent.
	*
	* @return <code>true</code> if this ch assumptions is parent; <code>false</code> otherwise
	*/
	@Override
	public boolean isParent() {
		return _chAssumptions.isParent();
	}

	@Override
	public void persist() {
		_chAssumptions.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_chAssumptions.setCachedModel(cachedModel);
	}

	/**
	* Sets the cam ID of this ch assumptions.
	*
	* @param camId the cam ID of this ch assumptions
	*/
	@Override
	public void setCamId(int camId) {
		_chAssumptions.setCamId(camId);
	}

	/**
	* Sets the ch assumptions sid of this ch assumptions.
	*
	* @param chAssumptionsSid the ch assumptions sid of this ch assumptions
	*/
	@Override
	public void setChAssumptionsSid(int chAssumptionsSid) {
		_chAssumptions.setChAssumptionsSid(chAssumptionsSid);
	}

	/**
	* Sets the commentary of this ch assumptions.
	*
	* @param commentary the commentary of this ch assumptions
	*/
	@Override
	public void setCommentary(java.lang.String commentary) {
		_chAssumptions.setCommentary(commentary);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_chAssumptions.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_chAssumptions.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_chAssumptions.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gross trade sales of this ch assumptions.
	*
	* @param grossTradeSales the gross trade sales of this ch assumptions
	*/
	@Override
	public void setGrossTradeSales(double grossTradeSales) {
		_chAssumptions.setGrossTradeSales(grossTradeSales);
	}

	@Override
	public void setNew(boolean n) {
		_chAssumptions.setNew(n);
	}

	/**
	* Sets whether this ch assumptions is parent.
	*
	* @param parent the parent of this ch assumptions
	*/
	@Override
	public void setParent(boolean parent) {
		_chAssumptions.setParent(parent);
	}

	/**
	* Sets the primary key of this ch assumptions.
	*
	* @param primaryKey the primary key of this ch assumptions
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_chAssumptions.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_chAssumptions.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this ch assumptions.
	*
	* @param projectionDetailsSid the projection details sid of this ch assumptions
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_chAssumptions.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the quarter of this ch assumptions.
	*
	* @param quarter the quarter of this ch assumptions
	*/
	@Override
	public void setQuarter(int quarter) {
		_chAssumptions.setQuarter(quarter);
	}

	/**
	* Sets the reason codes of this ch assumptions.
	*
	* @param reasonCodes the reason codes of this ch assumptions
	*/
	@Override
	public void setReasonCodes(java.lang.String reasonCodes) {
		_chAssumptions.setReasonCodes(reasonCodes);
	}

	/**
	* Sets the total discount change of this ch assumptions.
	*
	* @param totalDiscountChange the total discount change of this ch assumptions
	*/
	@Override
	public void setTotalDiscountChange(double totalDiscountChange) {
		_chAssumptions.setTotalDiscountChange(totalDiscountChange);
	}

	/**
	* Sets the total discount percent change of this ch assumptions.
	*
	* @param totalDiscountPercentChange the total discount percent change of this ch assumptions
	*/
	@Override
	public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
		_chAssumptions.setTotalDiscountPercentChange(totalDiscountPercentChange);
	}

	/**
	* Sets the total discount percent prior of this ch assumptions.
	*
	* @param totalDiscountPercentPrior the total discount percent prior of this ch assumptions
	*/
	@Override
	public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
		_chAssumptions.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
	}

	/**
	* Sets the total discount percent projected of this ch assumptions.
	*
	* @param totalDiscountPercentProjected the total discount percent projected of this ch assumptions
	*/
	@Override
	public void setTotalDiscountPercentProjected(
		double totalDiscountPercentProjected) {
		_chAssumptions.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
	}

	/**
	* Sets the total discount prior of this ch assumptions.
	*
	* @param totalDiscountPrior the total discount prior of this ch assumptions
	*/
	@Override
	public void setTotalDiscountPrior(double totalDiscountPrior) {
		_chAssumptions.setTotalDiscountPrior(totalDiscountPrior);
	}

	/**
	* Sets the total discount projected of this ch assumptions.
	*
	* @param totalDiscountProjected the total discount projected of this ch assumptions
	*/
	@Override
	public void setTotalDiscountProjected(double totalDiscountProjected) {
		_chAssumptions.setTotalDiscountProjected(totalDiscountProjected);
	}

	/**
	* Sets the year of this ch assumptions.
	*
	* @param year the year of this ch assumptions
	*/
	@Override
	public void setYear(int year) {
		_chAssumptions.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ChAssumptions> toCacheModel() {
		return _chAssumptions.toCacheModel();
	}

	@Override
	public ChAssumptions toEscapedModel() {
		return new ChAssumptionsWrapper(_chAssumptions.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _chAssumptions.toString();
	}

	@Override
	public ChAssumptions toUnescapedModel() {
		return new ChAssumptionsWrapper(_chAssumptions.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _chAssumptions.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChAssumptionsWrapper)) {
			return false;
		}

		ChAssumptionsWrapper chAssumptionsWrapper = (ChAssumptionsWrapper)obj;

		if (Objects.equals(_chAssumptions, chAssumptionsWrapper._chAssumptions)) {
			return true;
		}

		return false;
	}

	@Override
	public ChAssumptions getWrappedModel() {
		return _chAssumptions;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _chAssumptions.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _chAssumptions.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_chAssumptions.resetOriginalValues();
	}

	private final ChAssumptions _chAssumptions;
}