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
 * This class is a wrapper for {@link StNmAssumptions}.
 * </p>
 *
 * @author
 * @see StNmAssumptions
 * @generated
 */
@ProviderType
public class StNmAssumptionsWrapper implements StNmAssumptions,
	ModelWrapper<StNmAssumptions> {
	public StNmAssumptionsWrapper(StNmAssumptions stNmAssumptions) {
		_stNmAssumptions = stNmAssumptions;
	}

	@Override
	public Class<?> getModelClass() {
		return StNmAssumptions.class;
	}

	@Override
	public String getModelClassName() {
		return StNmAssumptions.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("parent", getParent());
		attributes.put("projectionPeriod", getProjectionPeriod());
		attributes.put("commentary", getCommentary());
		attributes.put("nmAssumptionsSid", getNmAssumptionsSid());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("netSalesPrior", getNetSalesPrior());
		attributes.put("userId", getUserId());
		attributes.put("grossSalesPercentChange", getGrossSalesPercentChange());
		attributes.put("totalDiscountPercentChange",
			getTotalDiscountPercentChange());
		attributes.put("reasonCodes", getReasonCodes());
		attributes.put("totalDiscountPercentProjected",
			getTotalDiscountPercentProjected());
		attributes.put("totalDiscountPercentPrior",
			getTotalDiscountPercentPrior());
		attributes.put("netSalesProjected", getNetSalesProjected());
		attributes.put("stNmAssumptionsSid", getStNmAssumptionsSid());
		attributes.put("grossSalesProjected", getGrossSalesProjected());
		attributes.put("sessionId", getSessionId());
		attributes.put("grossSalesPrior", getGrossSalesPrior());
		attributes.put("isChecked", getIsChecked());
		attributes.put("camId", getCamId());
		attributes.put("netSalesPercentChange", getNetSalesPercentChange());
		attributes.put("segment", getSegment());

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

		Integer projectionPeriod = (Integer)attributes.get("projectionPeriod");

		if (projectionPeriod != null) {
			setProjectionPeriod(projectionPeriod);
		}

		String commentary = (String)attributes.get("commentary");

		if (commentary != null) {
			setCommentary(commentary);
		}

		Integer nmAssumptionsSid = (Integer)attributes.get("nmAssumptionsSid");

		if (nmAssumptionsSid != null) {
			setNmAssumptionsSid(nmAssumptionsSid);
		}

		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Double netSalesPrior = (Double)attributes.get("netSalesPrior");

		if (netSalesPrior != null) {
			setNetSalesPrior(netSalesPrior);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Double grossSalesPercentChange = (Double)attributes.get(
				"grossSalesPercentChange");

		if (grossSalesPercentChange != null) {
			setGrossSalesPercentChange(grossSalesPercentChange);
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

		Double netSalesProjected = (Double)attributes.get("netSalesProjected");

		if (netSalesProjected != null) {
			setNetSalesProjected(netSalesProjected);
		}

		String stNmAssumptionsSid = (String)attributes.get("stNmAssumptionsSid");

		if (stNmAssumptionsSid != null) {
			setStNmAssumptionsSid(stNmAssumptionsSid);
		}

		Double grossSalesProjected = (Double)attributes.get(
				"grossSalesProjected");

		if (grossSalesProjected != null) {
			setGrossSalesProjected(grossSalesProjected);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Double grossSalesPrior = (Double)attributes.get("grossSalesPrior");

		if (grossSalesPrior != null) {
			setGrossSalesPrior(grossSalesPrior);
		}

		Boolean isChecked = (Boolean)attributes.get("isChecked");

		if (isChecked != null) {
			setIsChecked(isChecked);
		}

		Integer camId = (Integer)attributes.get("camId");

		if (camId != null) {
			setCamId(camId);
		}

		Double netSalesPercentChange = (Double)attributes.get(
				"netSalesPercentChange");

		if (netSalesPercentChange != null) {
			setNetSalesPercentChange(netSalesPercentChange);
		}

		String segment = (String)attributes.get("segment");

		if (segment != null) {
			setSegment(segment);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StNmAssumptionsWrapper((StNmAssumptions)_stNmAssumptions.clone());
	}

	@Override
	public int compareTo(StNmAssumptions stNmAssumptions) {
		return _stNmAssumptions.compareTo(stNmAssumptions);
	}

	/**
	* Returns the cam ID of this st nm assumptions.
	*
	* @return the cam ID of this st nm assumptions
	*/
	@Override
	public int getCamId() {
		return _stNmAssumptions.getCamId();
	}

	/**
	* Returns the commentary of this st nm assumptions.
	*
	* @return the commentary of this st nm assumptions
	*/
	@Override
	public java.lang.String getCommentary() {
		return _stNmAssumptions.getCommentary();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stNmAssumptions.getExpandoBridge();
	}

	/**
	* Returns the gross sales percent change of this st nm assumptions.
	*
	* @return the gross sales percent change of this st nm assumptions
	*/
	@Override
	public double getGrossSalesPercentChange() {
		return _stNmAssumptions.getGrossSalesPercentChange();
	}

	/**
	* Returns the gross sales prior of this st nm assumptions.
	*
	* @return the gross sales prior of this st nm assumptions
	*/
	@Override
	public double getGrossSalesPrior() {
		return _stNmAssumptions.getGrossSalesPrior();
	}

	/**
	* Returns the gross sales projected of this st nm assumptions.
	*
	* @return the gross sales projected of this st nm assumptions
	*/
	@Override
	public double getGrossSalesProjected() {
		return _stNmAssumptions.getGrossSalesProjected();
	}

	/**
	* Returns the is checked of this st nm assumptions.
	*
	* @return the is checked of this st nm assumptions
	*/
	@Override
	public boolean getIsChecked() {
		return _stNmAssumptions.getIsChecked();
	}

	/**
	* Returns the last modified date of this st nm assumptions.
	*
	* @return the last modified date of this st nm assumptions
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stNmAssumptions.getLastModifiedDate();
	}

	/**
	* Returns the net sales percent change of this st nm assumptions.
	*
	* @return the net sales percent change of this st nm assumptions
	*/
	@Override
	public double getNetSalesPercentChange() {
		return _stNmAssumptions.getNetSalesPercentChange();
	}

	/**
	* Returns the net sales prior of this st nm assumptions.
	*
	* @return the net sales prior of this st nm assumptions
	*/
	@Override
	public double getNetSalesPrior() {
		return _stNmAssumptions.getNetSalesPrior();
	}

	/**
	* Returns the net sales projected of this st nm assumptions.
	*
	* @return the net sales projected of this st nm assumptions
	*/
	@Override
	public double getNetSalesProjected() {
		return _stNmAssumptions.getNetSalesProjected();
	}

	/**
	* Returns the nm assumptions sid of this st nm assumptions.
	*
	* @return the nm assumptions sid of this st nm assumptions
	*/
	@Override
	public int getNmAssumptionsSid() {
		return _stNmAssumptions.getNmAssumptionsSid();
	}

	/**
	* Returns the parent of this st nm assumptions.
	*
	* @return the parent of this st nm assumptions
	*/
	@Override
	public boolean getParent() {
		return _stNmAssumptions.getParent();
	}

	/**
	* Returns the primary key of this st nm assumptions.
	*
	* @return the primary key of this st nm assumptions
	*/
	@Override
	public com.stpl.app.service.persistence.StNmAssumptionsPK getPrimaryKey() {
		return _stNmAssumptions.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stNmAssumptions.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this st nm assumptions.
	*
	* @return the projection details sid of this st nm assumptions
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _stNmAssumptions.getProjectionDetailsSid();
	}

	/**
	* Returns the projection period of this st nm assumptions.
	*
	* @return the projection period of this st nm assumptions
	*/
	@Override
	public int getProjectionPeriod() {
		return _stNmAssumptions.getProjectionPeriod();
	}

	/**
	* Returns the reason codes of this st nm assumptions.
	*
	* @return the reason codes of this st nm assumptions
	*/
	@Override
	public java.lang.String getReasonCodes() {
		return _stNmAssumptions.getReasonCodes();
	}

	/**
	* Returns the segment of this st nm assumptions.
	*
	* @return the segment of this st nm assumptions
	*/
	@Override
	public java.lang.String getSegment() {
		return _stNmAssumptions.getSegment();
	}

	/**
	* Returns the session ID of this st nm assumptions.
	*
	* @return the session ID of this st nm assumptions
	*/
	@Override
	public int getSessionId() {
		return _stNmAssumptions.getSessionId();
	}

	/**
	* Returns the st nm assumptions sid of this st nm assumptions.
	*
	* @return the st nm assumptions sid of this st nm assumptions
	*/
	@Override
	public java.lang.String getStNmAssumptionsSid() {
		return _stNmAssumptions.getStNmAssumptionsSid();
	}

	/**
	* Returns the total discount percent change of this st nm assumptions.
	*
	* @return the total discount percent change of this st nm assumptions
	*/
	@Override
	public double getTotalDiscountPercentChange() {
		return _stNmAssumptions.getTotalDiscountPercentChange();
	}

	/**
	* Returns the total discount percent prior of this st nm assumptions.
	*
	* @return the total discount percent prior of this st nm assumptions
	*/
	@Override
	public double getTotalDiscountPercentPrior() {
		return _stNmAssumptions.getTotalDiscountPercentPrior();
	}

	/**
	* Returns the total discount percent projected of this st nm assumptions.
	*
	* @return the total discount percent projected of this st nm assumptions
	*/
	@Override
	public double getTotalDiscountPercentProjected() {
		return _stNmAssumptions.getTotalDiscountPercentProjected();
	}

	/**
	* Returns the user ID of this st nm assumptions.
	*
	* @return the user ID of this st nm assumptions
	*/
	@Override
	public int getUserId() {
		return _stNmAssumptions.getUserId();
	}

	@Override
	public int hashCode() {
		return _stNmAssumptions.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stNmAssumptions.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stNmAssumptions.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this st nm assumptions is is checked.
	*
	* @return <code>true</code> if this st nm assumptions is is checked; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsChecked() {
		return _stNmAssumptions.isIsChecked();
	}

	@Override
	public boolean isNew() {
		return _stNmAssumptions.isNew();
	}

	/**
	* Returns <code>true</code> if this st nm assumptions is parent.
	*
	* @return <code>true</code> if this st nm assumptions is parent; <code>false</code> otherwise
	*/
	@Override
	public boolean isParent() {
		return _stNmAssumptions.isParent();
	}

	@Override
	public void persist() {
		_stNmAssumptions.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stNmAssumptions.setCachedModel(cachedModel);
	}

	/**
	* Sets the cam ID of this st nm assumptions.
	*
	* @param camId the cam ID of this st nm assumptions
	*/
	@Override
	public void setCamId(int camId) {
		_stNmAssumptions.setCamId(camId);
	}

	/**
	* Sets the commentary of this st nm assumptions.
	*
	* @param commentary the commentary of this st nm assumptions
	*/
	@Override
	public void setCommentary(java.lang.String commentary) {
		_stNmAssumptions.setCommentary(commentary);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stNmAssumptions.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stNmAssumptions.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stNmAssumptions.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gross sales percent change of this st nm assumptions.
	*
	* @param grossSalesPercentChange the gross sales percent change of this st nm assumptions
	*/
	@Override
	public void setGrossSalesPercentChange(double grossSalesPercentChange) {
		_stNmAssumptions.setGrossSalesPercentChange(grossSalesPercentChange);
	}

	/**
	* Sets the gross sales prior of this st nm assumptions.
	*
	* @param grossSalesPrior the gross sales prior of this st nm assumptions
	*/
	@Override
	public void setGrossSalesPrior(double grossSalesPrior) {
		_stNmAssumptions.setGrossSalesPrior(grossSalesPrior);
	}

	/**
	* Sets the gross sales projected of this st nm assumptions.
	*
	* @param grossSalesProjected the gross sales projected of this st nm assumptions
	*/
	@Override
	public void setGrossSalesProjected(double grossSalesProjected) {
		_stNmAssumptions.setGrossSalesProjected(grossSalesProjected);
	}

	/**
	* Sets whether this st nm assumptions is is checked.
	*
	* @param isChecked the is checked of this st nm assumptions
	*/
	@Override
	public void setIsChecked(boolean isChecked) {
		_stNmAssumptions.setIsChecked(isChecked);
	}

	/**
	* Sets the last modified date of this st nm assumptions.
	*
	* @param lastModifiedDate the last modified date of this st nm assumptions
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stNmAssumptions.setLastModifiedDate(lastModifiedDate);
	}

	/**
	* Sets the net sales percent change of this st nm assumptions.
	*
	* @param netSalesPercentChange the net sales percent change of this st nm assumptions
	*/
	@Override
	public void setNetSalesPercentChange(double netSalesPercentChange) {
		_stNmAssumptions.setNetSalesPercentChange(netSalesPercentChange);
	}

	/**
	* Sets the net sales prior of this st nm assumptions.
	*
	* @param netSalesPrior the net sales prior of this st nm assumptions
	*/
	@Override
	public void setNetSalesPrior(double netSalesPrior) {
		_stNmAssumptions.setNetSalesPrior(netSalesPrior);
	}

	/**
	* Sets the net sales projected of this st nm assumptions.
	*
	* @param netSalesProjected the net sales projected of this st nm assumptions
	*/
	@Override
	public void setNetSalesProjected(double netSalesProjected) {
		_stNmAssumptions.setNetSalesProjected(netSalesProjected);
	}

	@Override
	public void setNew(boolean n) {
		_stNmAssumptions.setNew(n);
	}

	/**
	* Sets the nm assumptions sid of this st nm assumptions.
	*
	* @param nmAssumptionsSid the nm assumptions sid of this st nm assumptions
	*/
	@Override
	public void setNmAssumptionsSid(int nmAssumptionsSid) {
		_stNmAssumptions.setNmAssumptionsSid(nmAssumptionsSid);
	}

	/**
	* Sets whether this st nm assumptions is parent.
	*
	* @param parent the parent of this st nm assumptions
	*/
	@Override
	public void setParent(boolean parent) {
		_stNmAssumptions.setParent(parent);
	}

	/**
	* Sets the primary key of this st nm assumptions.
	*
	* @param primaryKey the primary key of this st nm assumptions
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StNmAssumptionsPK primaryKey) {
		_stNmAssumptions.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stNmAssumptions.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this st nm assumptions.
	*
	* @param projectionDetailsSid the projection details sid of this st nm assumptions
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_stNmAssumptions.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the projection period of this st nm assumptions.
	*
	* @param projectionPeriod the projection period of this st nm assumptions
	*/
	@Override
	public void setProjectionPeriod(int projectionPeriod) {
		_stNmAssumptions.setProjectionPeriod(projectionPeriod);
	}

	/**
	* Sets the reason codes of this st nm assumptions.
	*
	* @param reasonCodes the reason codes of this st nm assumptions
	*/
	@Override
	public void setReasonCodes(java.lang.String reasonCodes) {
		_stNmAssumptions.setReasonCodes(reasonCodes);
	}

	/**
	* Sets the segment of this st nm assumptions.
	*
	* @param segment the segment of this st nm assumptions
	*/
	@Override
	public void setSegment(java.lang.String segment) {
		_stNmAssumptions.setSegment(segment);
	}

	/**
	* Sets the session ID of this st nm assumptions.
	*
	* @param sessionId the session ID of this st nm assumptions
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stNmAssumptions.setSessionId(sessionId);
	}

	/**
	* Sets the st nm assumptions sid of this st nm assumptions.
	*
	* @param stNmAssumptionsSid the st nm assumptions sid of this st nm assumptions
	*/
	@Override
	public void setStNmAssumptionsSid(java.lang.String stNmAssumptionsSid) {
		_stNmAssumptions.setStNmAssumptionsSid(stNmAssumptionsSid);
	}

	/**
	* Sets the total discount percent change of this st nm assumptions.
	*
	* @param totalDiscountPercentChange the total discount percent change of this st nm assumptions
	*/
	@Override
	public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
		_stNmAssumptions.setTotalDiscountPercentChange(totalDiscountPercentChange);
	}

	/**
	* Sets the total discount percent prior of this st nm assumptions.
	*
	* @param totalDiscountPercentPrior the total discount percent prior of this st nm assumptions
	*/
	@Override
	public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
		_stNmAssumptions.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
	}

	/**
	* Sets the total discount percent projected of this st nm assumptions.
	*
	* @param totalDiscountPercentProjected the total discount percent projected of this st nm assumptions
	*/
	@Override
	public void setTotalDiscountPercentProjected(
		double totalDiscountPercentProjected) {
		_stNmAssumptions.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
	}

	/**
	* Sets the user ID of this st nm assumptions.
	*
	* @param userId the user ID of this st nm assumptions
	*/
	@Override
	public void setUserId(int userId) {
		_stNmAssumptions.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StNmAssumptions> toCacheModel() {
		return _stNmAssumptions.toCacheModel();
	}

	@Override
	public StNmAssumptions toEscapedModel() {
		return new StNmAssumptionsWrapper(_stNmAssumptions.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stNmAssumptions.toString();
	}

	@Override
	public StNmAssumptions toUnescapedModel() {
		return new StNmAssumptionsWrapper(_stNmAssumptions.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stNmAssumptions.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNmAssumptionsWrapper)) {
			return false;
		}

		StNmAssumptionsWrapper stNmAssumptionsWrapper = (StNmAssumptionsWrapper)obj;

		if (Objects.equals(_stNmAssumptions,
					stNmAssumptionsWrapper._stNmAssumptions)) {
			return true;
		}

		return false;
	}

	@Override
	public StNmAssumptions getWrappedModel() {
		return _stNmAssumptions;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stNmAssumptions.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stNmAssumptions.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stNmAssumptions.resetOriginalValues();
	}

	private final StNmAssumptions _stNmAssumptions;
}