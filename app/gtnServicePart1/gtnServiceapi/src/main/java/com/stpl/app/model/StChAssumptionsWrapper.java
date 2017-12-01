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
 * This class is a wrapper for {@link StChAssumptions}.
 * </p>
 *
 * @author
 * @see StChAssumptions
 * @generated
 */
@ProviderType
public class StChAssumptionsWrapper implements StChAssumptions,
	ModelWrapper<StChAssumptions> {
	public StChAssumptionsWrapper(StChAssumptions stChAssumptions) {
		_stChAssumptions = stChAssumptions;
	}

	@Override
	public Class<?> getModelClass() {
		return StChAssumptions.class;
	}

	@Override
	public String getModelClassName() {
		return StChAssumptions.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("parent", getParent());
		attributes.put("commentary", getCommentary());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("userId", getUserId());
		attributes.put("quarter", getQuarter());
		attributes.put("totalDiscountPercentChange",
			getTotalDiscountPercentChange());
		attributes.put("reasonCodes", getReasonCodes());
		attributes.put("year", getYear());
		attributes.put("totalDiscountPercentProjected",
			getTotalDiscountPercentProjected());
		attributes.put("totalDiscountPercentPrior",
			getTotalDiscountPercentPrior());
		attributes.put("stChAssumptionsSid", getStChAssumptionsSid());
		attributes.put("chAssumptionsSid", getChAssumptionsSid());
		attributes.put("totalDiscountChange", getTotalDiscountChange());
		attributes.put("sessionId", getSessionId());
		attributes.put("totalDiscountProjected", getTotalDiscountProjected());
		attributes.put("isChecked", getIsChecked());
		attributes.put("camId", getCamId());
		attributes.put("grossTradeSales", getGrossTradeSales());
		attributes.put("totalDiscountPrior", getTotalDiscountPrior());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		Boolean parent = (Boolean)attributes.get("parent");

		if (parent != null) {
			setParent(parent);
		}

		String commentary = (String)attributes.get("commentary");

		if (commentary != null) {
			setCommentary(commentary);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
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

		String stChAssumptionsSid = (String)attributes.get("stChAssumptionsSid");

		if (stChAssumptionsSid != null) {
			setStChAssumptionsSid(stChAssumptionsSid);
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

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Double totalDiscountProjected = (Double)attributes.get(
				"totalDiscountProjected");

		if (totalDiscountProjected != null) {
			setTotalDiscountProjected(totalDiscountProjected);
		}

		Boolean isChecked = (Boolean)attributes.get("isChecked");

		if (isChecked != null) {
			setIsChecked(isChecked);
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
		return new StChAssumptionsWrapper((StChAssumptions)_stChAssumptions.clone());
	}

	@Override
	public int compareTo(StChAssumptions stChAssumptions) {
		return _stChAssumptions.compareTo(stChAssumptions);
	}

	/**
	* Returns the cam ID of this st ch assumptions.
	*
	* @return the cam ID of this st ch assumptions
	*/
	@Override
	public int getCamId() {
		return _stChAssumptions.getCamId();
	}

	/**
	* Returns the ch assumptions sid of this st ch assumptions.
	*
	* @return the ch assumptions sid of this st ch assumptions
	*/
	@Override
	public int getChAssumptionsSid() {
		return _stChAssumptions.getChAssumptionsSid();
	}

	/**
	* Returns the commentary of this st ch assumptions.
	*
	* @return the commentary of this st ch assumptions
	*/
	@Override
	public java.lang.String getCommentary() {
		return _stChAssumptions.getCommentary();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stChAssumptions.getExpandoBridge();
	}

	/**
	* Returns the gross trade sales of this st ch assumptions.
	*
	* @return the gross trade sales of this st ch assumptions
	*/
	@Override
	public double getGrossTradeSales() {
		return _stChAssumptions.getGrossTradeSales();
	}

	/**
	* Returns the is checked of this st ch assumptions.
	*
	* @return the is checked of this st ch assumptions
	*/
	@Override
	public boolean getIsChecked() {
		return _stChAssumptions.getIsChecked();
	}

	/**
	* Returns the last modified date of this st ch assumptions.
	*
	* @return the last modified date of this st ch assumptions
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stChAssumptions.getLastModifiedDate();
	}

	/**
	* Returns the parent of this st ch assumptions.
	*
	* @return the parent of this st ch assumptions
	*/
	@Override
	public boolean getParent() {
		return _stChAssumptions.getParent();
	}

	/**
	* Returns the primary key of this st ch assumptions.
	*
	* @return the primary key of this st ch assumptions
	*/
	@Override
	public com.stpl.app.service.persistence.StChAssumptionsPK getPrimaryKey() {
		return _stChAssumptions.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stChAssumptions.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this st ch assumptions.
	*
	* @return the projection details sid of this st ch assumptions
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _stChAssumptions.getProjectionDetailsSid();
	}

	/**
	* Returns the quarter of this st ch assumptions.
	*
	* @return the quarter of this st ch assumptions
	*/
	@Override
	public int getQuarter() {
		return _stChAssumptions.getQuarter();
	}

	/**
	* Returns the reason codes of this st ch assumptions.
	*
	* @return the reason codes of this st ch assumptions
	*/
	@Override
	public java.lang.String getReasonCodes() {
		return _stChAssumptions.getReasonCodes();
	}

	/**
	* Returns the session ID of this st ch assumptions.
	*
	* @return the session ID of this st ch assumptions
	*/
	@Override
	public int getSessionId() {
		return _stChAssumptions.getSessionId();
	}

	/**
	* Returns the st ch assumptions sid of this st ch assumptions.
	*
	* @return the st ch assumptions sid of this st ch assumptions
	*/
	@Override
	public java.lang.String getStChAssumptionsSid() {
		return _stChAssumptions.getStChAssumptionsSid();
	}

	/**
	* Returns the total discount change of this st ch assumptions.
	*
	* @return the total discount change of this st ch assumptions
	*/
	@Override
	public double getTotalDiscountChange() {
		return _stChAssumptions.getTotalDiscountChange();
	}

	/**
	* Returns the total discount percent change of this st ch assumptions.
	*
	* @return the total discount percent change of this st ch assumptions
	*/
	@Override
	public double getTotalDiscountPercentChange() {
		return _stChAssumptions.getTotalDiscountPercentChange();
	}

	/**
	* Returns the total discount percent prior of this st ch assumptions.
	*
	* @return the total discount percent prior of this st ch assumptions
	*/
	@Override
	public double getTotalDiscountPercentPrior() {
		return _stChAssumptions.getTotalDiscountPercentPrior();
	}

	/**
	* Returns the total discount percent projected of this st ch assumptions.
	*
	* @return the total discount percent projected of this st ch assumptions
	*/
	@Override
	public double getTotalDiscountPercentProjected() {
		return _stChAssumptions.getTotalDiscountPercentProjected();
	}

	/**
	* Returns the total discount prior of this st ch assumptions.
	*
	* @return the total discount prior of this st ch assumptions
	*/
	@Override
	public double getTotalDiscountPrior() {
		return _stChAssumptions.getTotalDiscountPrior();
	}

	/**
	* Returns the total discount projected of this st ch assumptions.
	*
	* @return the total discount projected of this st ch assumptions
	*/
	@Override
	public double getTotalDiscountProjected() {
		return _stChAssumptions.getTotalDiscountProjected();
	}

	/**
	* Returns the user ID of this st ch assumptions.
	*
	* @return the user ID of this st ch assumptions
	*/
	@Override
	public int getUserId() {
		return _stChAssumptions.getUserId();
	}

	/**
	* Returns the year of this st ch assumptions.
	*
	* @return the year of this st ch assumptions
	*/
	@Override
	public int getYear() {
		return _stChAssumptions.getYear();
	}

	@Override
	public int hashCode() {
		return _stChAssumptions.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stChAssumptions.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stChAssumptions.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this st ch assumptions is is checked.
	*
	* @return <code>true</code> if this st ch assumptions is is checked; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsChecked() {
		return _stChAssumptions.isIsChecked();
	}

	@Override
	public boolean isNew() {
		return _stChAssumptions.isNew();
	}

	/**
	* Returns <code>true</code> if this st ch assumptions is parent.
	*
	* @return <code>true</code> if this st ch assumptions is parent; <code>false</code> otherwise
	*/
	@Override
	public boolean isParent() {
		return _stChAssumptions.isParent();
	}

	@Override
	public void persist() {
		_stChAssumptions.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stChAssumptions.setCachedModel(cachedModel);
	}

	/**
	* Sets the cam ID of this st ch assumptions.
	*
	* @param camId the cam ID of this st ch assumptions
	*/
	@Override
	public void setCamId(int camId) {
		_stChAssumptions.setCamId(camId);
	}

	/**
	* Sets the ch assumptions sid of this st ch assumptions.
	*
	* @param chAssumptionsSid the ch assumptions sid of this st ch assumptions
	*/
	@Override
	public void setChAssumptionsSid(int chAssumptionsSid) {
		_stChAssumptions.setChAssumptionsSid(chAssumptionsSid);
	}

	/**
	* Sets the commentary of this st ch assumptions.
	*
	* @param commentary the commentary of this st ch assumptions
	*/
	@Override
	public void setCommentary(java.lang.String commentary) {
		_stChAssumptions.setCommentary(commentary);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stChAssumptions.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stChAssumptions.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stChAssumptions.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gross trade sales of this st ch assumptions.
	*
	* @param grossTradeSales the gross trade sales of this st ch assumptions
	*/
	@Override
	public void setGrossTradeSales(double grossTradeSales) {
		_stChAssumptions.setGrossTradeSales(grossTradeSales);
	}

	/**
	* Sets whether this st ch assumptions is is checked.
	*
	* @param isChecked the is checked of this st ch assumptions
	*/
	@Override
	public void setIsChecked(boolean isChecked) {
		_stChAssumptions.setIsChecked(isChecked);
	}

	/**
	* Sets the last modified date of this st ch assumptions.
	*
	* @param lastModifiedDate the last modified date of this st ch assumptions
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stChAssumptions.setLastModifiedDate(lastModifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_stChAssumptions.setNew(n);
	}

	/**
	* Sets whether this st ch assumptions is parent.
	*
	* @param parent the parent of this st ch assumptions
	*/
	@Override
	public void setParent(boolean parent) {
		_stChAssumptions.setParent(parent);
	}

	/**
	* Sets the primary key of this st ch assumptions.
	*
	* @param primaryKey the primary key of this st ch assumptions
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StChAssumptionsPK primaryKey) {
		_stChAssumptions.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stChAssumptions.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this st ch assumptions.
	*
	* @param projectionDetailsSid the projection details sid of this st ch assumptions
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_stChAssumptions.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the quarter of this st ch assumptions.
	*
	* @param quarter the quarter of this st ch assumptions
	*/
	@Override
	public void setQuarter(int quarter) {
		_stChAssumptions.setQuarter(quarter);
	}

	/**
	* Sets the reason codes of this st ch assumptions.
	*
	* @param reasonCodes the reason codes of this st ch assumptions
	*/
	@Override
	public void setReasonCodes(java.lang.String reasonCodes) {
		_stChAssumptions.setReasonCodes(reasonCodes);
	}

	/**
	* Sets the session ID of this st ch assumptions.
	*
	* @param sessionId the session ID of this st ch assumptions
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stChAssumptions.setSessionId(sessionId);
	}

	/**
	* Sets the st ch assumptions sid of this st ch assumptions.
	*
	* @param stChAssumptionsSid the st ch assumptions sid of this st ch assumptions
	*/
	@Override
	public void setStChAssumptionsSid(java.lang.String stChAssumptionsSid) {
		_stChAssumptions.setStChAssumptionsSid(stChAssumptionsSid);
	}

	/**
	* Sets the total discount change of this st ch assumptions.
	*
	* @param totalDiscountChange the total discount change of this st ch assumptions
	*/
	@Override
	public void setTotalDiscountChange(double totalDiscountChange) {
		_stChAssumptions.setTotalDiscountChange(totalDiscountChange);
	}

	/**
	* Sets the total discount percent change of this st ch assumptions.
	*
	* @param totalDiscountPercentChange the total discount percent change of this st ch assumptions
	*/
	@Override
	public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
		_stChAssumptions.setTotalDiscountPercentChange(totalDiscountPercentChange);
	}

	/**
	* Sets the total discount percent prior of this st ch assumptions.
	*
	* @param totalDiscountPercentPrior the total discount percent prior of this st ch assumptions
	*/
	@Override
	public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
		_stChAssumptions.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
	}

	/**
	* Sets the total discount percent projected of this st ch assumptions.
	*
	* @param totalDiscountPercentProjected the total discount percent projected of this st ch assumptions
	*/
	@Override
	public void setTotalDiscountPercentProjected(
		double totalDiscountPercentProjected) {
		_stChAssumptions.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
	}

	/**
	* Sets the total discount prior of this st ch assumptions.
	*
	* @param totalDiscountPrior the total discount prior of this st ch assumptions
	*/
	@Override
	public void setTotalDiscountPrior(double totalDiscountPrior) {
		_stChAssumptions.setTotalDiscountPrior(totalDiscountPrior);
	}

	/**
	* Sets the total discount projected of this st ch assumptions.
	*
	* @param totalDiscountProjected the total discount projected of this st ch assumptions
	*/
	@Override
	public void setTotalDiscountProjected(double totalDiscountProjected) {
		_stChAssumptions.setTotalDiscountProjected(totalDiscountProjected);
	}

	/**
	* Sets the user ID of this st ch assumptions.
	*
	* @param userId the user ID of this st ch assumptions
	*/
	@Override
	public void setUserId(int userId) {
		_stChAssumptions.setUserId(userId);
	}

	/**
	* Sets the year of this st ch assumptions.
	*
	* @param year the year of this st ch assumptions
	*/
	@Override
	public void setYear(int year) {
		_stChAssumptions.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StChAssumptions> toCacheModel() {
		return _stChAssumptions.toCacheModel();
	}

	@Override
	public StChAssumptions toEscapedModel() {
		return new StChAssumptionsWrapper(_stChAssumptions.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stChAssumptions.toString();
	}

	@Override
	public StChAssumptions toUnescapedModel() {
		return new StChAssumptionsWrapper(_stChAssumptions.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stChAssumptions.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StChAssumptionsWrapper)) {
			return false;
		}

		StChAssumptionsWrapper stChAssumptionsWrapper = (StChAssumptionsWrapper)obj;

		if (Objects.equals(_stChAssumptions,
					stChAssumptionsWrapper._stChAssumptions)) {
			return true;
		}

		return false;
	}

	@Override
	public StChAssumptions getWrappedModel() {
		return _stChAssumptions;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stChAssumptions.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stChAssumptions.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stChAssumptions.resetOriginalValues();
	}

	private final StChAssumptions _stChAssumptions;
}