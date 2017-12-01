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
 * This class is a wrapper for {@link StMAssumptions}.
 * </p>
 *
 * @author
 * @see StMAssumptions
 * @generated
 */
@ProviderType
public class StMAssumptionsWrapper implements StMAssumptions,
	ModelWrapper<StMAssumptions> {
	public StMAssumptionsWrapper(StMAssumptions stMAssumptions) {
		_stMAssumptions = stMAssumptions;
	}

	@Override
	public Class<?> getModelClass() {
		return StMAssumptions.class;
	}

	@Override
	public String getModelClassName() {
		return StMAssumptions.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("grossSalesPercentChange", getGrossSalesPercentChange());
		attributes.put("grossSalesPrior", getGrossSalesPrior());
		attributes.put("projYear", getProjYear());
		attributes.put("totalDiscountPercentProjected",
			getTotalDiscountPercentProjected());
		attributes.put("camId", getCamId());
		attributes.put("commentary", getCommentary());
		attributes.put("isChecked", getIsChecked());
		attributes.put("userId", getUserId());
		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("grossSalesProjected", getGrossSalesProjected());
		attributes.put("totalDiscountPercentChange",
			getTotalDiscountPercentChange());
		attributes.put("totalDiscountPercentPrior",
			getTotalDiscountPercentPrior());
		attributes.put("netSalesPercentChange", getNetSalesPercentChange());
		attributes.put("parent", getParent());
		attributes.put("stMAssumptionsSid", getStMAssumptionsSid());
		attributes.put("projectionPeriod", getProjectionPeriod());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("netSalesPrior", getNetSalesPrior());
		attributes.put("sessionId", getSessionId());
		attributes.put("netSalesProjected", getNetSalesProjected());
		attributes.put("reasonCodes", getReasonCodes());
		attributes.put("mAssumptionsSid", getMAssumptionsSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double grossSalesPercentChange = (Double)attributes.get(
				"grossSalesPercentChange");

		if (grossSalesPercentChange != null) {
			setGrossSalesPercentChange(grossSalesPercentChange);
		}

		Double grossSalesPrior = (Double)attributes.get("grossSalesPrior");

		if (grossSalesPrior != null) {
			setGrossSalesPrior(grossSalesPrior);
		}

		Integer projYear = (Integer)attributes.get("projYear");

		if (projYear != null) {
			setProjYear(projYear);
		}

		Double totalDiscountPercentProjected = (Double)attributes.get(
				"totalDiscountPercentProjected");

		if (totalDiscountPercentProjected != null) {
			setTotalDiscountPercentProjected(totalDiscountPercentProjected);
		}

		Integer camId = (Integer)attributes.get("camId");

		if (camId != null) {
			setCamId(camId);
		}

		String commentary = (String)attributes.get("commentary");

		if (commentary != null) {
			setCommentary(commentary);
		}

		Boolean isChecked = (Boolean)attributes.get("isChecked");

		if (isChecked != null) {
			setIsChecked(isChecked);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		Double grossSalesProjected = (Double)attributes.get(
				"grossSalesProjected");

		if (grossSalesProjected != null) {
			setGrossSalesProjected(grossSalesProjected);
		}

		Double totalDiscountPercentChange = (Double)attributes.get(
				"totalDiscountPercentChange");

		if (totalDiscountPercentChange != null) {
			setTotalDiscountPercentChange(totalDiscountPercentChange);
		}

		Double totalDiscountPercentPrior = (Double)attributes.get(
				"totalDiscountPercentPrior");

		if (totalDiscountPercentPrior != null) {
			setTotalDiscountPercentPrior(totalDiscountPercentPrior);
		}

		Double netSalesPercentChange = (Double)attributes.get(
				"netSalesPercentChange");

		if (netSalesPercentChange != null) {
			setNetSalesPercentChange(netSalesPercentChange);
		}

		Boolean parent = (Boolean)attributes.get("parent");

		if (parent != null) {
			setParent(parent);
		}

		String stMAssumptionsSid = (String)attributes.get("stMAssumptionsSid");

		if (stMAssumptionsSid != null) {
			setStMAssumptionsSid(stMAssumptionsSid);
		}

		Integer projectionPeriod = (Integer)attributes.get("projectionPeriod");

		if (projectionPeriod != null) {
			setProjectionPeriod(projectionPeriod);
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

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Double netSalesProjected = (Double)attributes.get("netSalesProjected");

		if (netSalesProjected != null) {
			setNetSalesProjected(netSalesProjected);
		}

		String reasonCodes = (String)attributes.get("reasonCodes");

		if (reasonCodes != null) {
			setReasonCodes(reasonCodes);
		}

		Integer mAssumptionsSid = (Integer)attributes.get("mAssumptionsSid");

		if (mAssumptionsSid != null) {
			setMAssumptionsSid(mAssumptionsSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StMAssumptionsWrapper((StMAssumptions)_stMAssumptions.clone());
	}

	@Override
	public int compareTo(StMAssumptions stMAssumptions) {
		return _stMAssumptions.compareTo(stMAssumptions);
	}

	/**
	* Returns the cam ID of this st m assumptions.
	*
	* @return the cam ID of this st m assumptions
	*/
	@Override
	public int getCamId() {
		return _stMAssumptions.getCamId();
	}

	/**
	* Returns the commentary of this st m assumptions.
	*
	* @return the commentary of this st m assumptions
	*/
	@Override
	public java.lang.String getCommentary() {
		return _stMAssumptions.getCommentary();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stMAssumptions.getExpandoBridge();
	}

	/**
	* Returns the gross sales percent change of this st m assumptions.
	*
	* @return the gross sales percent change of this st m assumptions
	*/
	@Override
	public double getGrossSalesPercentChange() {
		return _stMAssumptions.getGrossSalesPercentChange();
	}

	/**
	* Returns the gross sales prior of this st m assumptions.
	*
	* @return the gross sales prior of this st m assumptions
	*/
	@Override
	public double getGrossSalesPrior() {
		return _stMAssumptions.getGrossSalesPrior();
	}

	/**
	* Returns the gross sales projected of this st m assumptions.
	*
	* @return the gross sales projected of this st m assumptions
	*/
	@Override
	public double getGrossSalesProjected() {
		return _stMAssumptions.getGrossSalesProjected();
	}

	/**
	* Returns the is checked of this st m assumptions.
	*
	* @return the is checked of this st m assumptions
	*/
	@Override
	public boolean getIsChecked() {
		return _stMAssumptions.getIsChecked();
	}

	/**
	* Returns the last modified date of this st m assumptions.
	*
	* @return the last modified date of this st m assumptions
	*/
	@Override
	public Date getLastModifiedDate() {
		return _stMAssumptions.getLastModifiedDate();
	}

	/**
	* Returns the m assumptions sid of this st m assumptions.
	*
	* @return the m assumptions sid of this st m assumptions
	*/
	@Override
	public int getMAssumptionsSid() {
		return _stMAssumptions.getMAssumptionsSid();
	}

	/**
	* Returns the net sales percent change of this st m assumptions.
	*
	* @return the net sales percent change of this st m assumptions
	*/
	@Override
	public double getNetSalesPercentChange() {
		return _stMAssumptions.getNetSalesPercentChange();
	}

	/**
	* Returns the net sales prior of this st m assumptions.
	*
	* @return the net sales prior of this st m assumptions
	*/
	@Override
	public double getNetSalesPrior() {
		return _stMAssumptions.getNetSalesPrior();
	}

	/**
	* Returns the net sales projected of this st m assumptions.
	*
	* @return the net sales projected of this st m assumptions
	*/
	@Override
	public double getNetSalesProjected() {
		return _stMAssumptions.getNetSalesProjected();
	}

	/**
	* Returns the parent of this st m assumptions.
	*
	* @return the parent of this st m assumptions
	*/
	@Override
	public boolean getParent() {
		return _stMAssumptions.getParent();
	}

	/**
	* Returns the primary key of this st m assumptions.
	*
	* @return the primary key of this st m assumptions
	*/
	@Override
	public com.stpl.app.service.persistence.StMAssumptionsPK getPrimaryKey() {
		return _stMAssumptions.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stMAssumptions.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this st m assumptions.
	*
	* @return the projection details sid of this st m assumptions
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _stMAssumptions.getProjectionDetailsSid();
	}

	/**
	* Returns the projection period of this st m assumptions.
	*
	* @return the projection period of this st m assumptions
	*/
	@Override
	public int getProjectionPeriod() {
		return _stMAssumptions.getProjectionPeriod();
	}

	/**
	* Returns the proj year of this st m assumptions.
	*
	* @return the proj year of this st m assumptions
	*/
	@Override
	public int getProjYear() {
		return _stMAssumptions.getProjYear();
	}

	/**
	* Returns the reason codes of this st m assumptions.
	*
	* @return the reason codes of this st m assumptions
	*/
	@Override
	public java.lang.String getReasonCodes() {
		return _stMAssumptions.getReasonCodes();
	}

	/**
	* Returns the session ID of this st m assumptions.
	*
	* @return the session ID of this st m assumptions
	*/
	@Override
	public int getSessionId() {
		return _stMAssumptions.getSessionId();
	}

	/**
	* Returns the st m assumptions sid of this st m assumptions.
	*
	* @return the st m assumptions sid of this st m assumptions
	*/
	@Override
	public java.lang.String getStMAssumptionsSid() {
		return _stMAssumptions.getStMAssumptionsSid();
	}

	/**
	* Returns the total discount percent change of this st m assumptions.
	*
	* @return the total discount percent change of this st m assumptions
	*/
	@Override
	public double getTotalDiscountPercentChange() {
		return _stMAssumptions.getTotalDiscountPercentChange();
	}

	/**
	* Returns the total discount percent prior of this st m assumptions.
	*
	* @return the total discount percent prior of this st m assumptions
	*/
	@Override
	public double getTotalDiscountPercentPrior() {
		return _stMAssumptions.getTotalDiscountPercentPrior();
	}

	/**
	* Returns the total discount percent projected of this st m assumptions.
	*
	* @return the total discount percent projected of this st m assumptions
	*/
	@Override
	public double getTotalDiscountPercentProjected() {
		return _stMAssumptions.getTotalDiscountPercentProjected();
	}

	/**
	* Returns the user ID of this st m assumptions.
	*
	* @return the user ID of this st m assumptions
	*/
	@Override
	public int getUserId() {
		return _stMAssumptions.getUserId();
	}

	@Override
	public int hashCode() {
		return _stMAssumptions.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stMAssumptions.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stMAssumptions.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this st m assumptions is is checked.
	*
	* @return <code>true</code> if this st m assumptions is is checked; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsChecked() {
		return _stMAssumptions.isIsChecked();
	}

	@Override
	public boolean isNew() {
		return _stMAssumptions.isNew();
	}

	/**
	* Returns <code>true</code> if this st m assumptions is parent.
	*
	* @return <code>true</code> if this st m assumptions is parent; <code>false</code> otherwise
	*/
	@Override
	public boolean isParent() {
		return _stMAssumptions.isParent();
	}

	@Override
	public void persist() {
		_stMAssumptions.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stMAssumptions.setCachedModel(cachedModel);
	}

	/**
	* Sets the cam ID of this st m assumptions.
	*
	* @param camId the cam ID of this st m assumptions
	*/
	@Override
	public void setCamId(int camId) {
		_stMAssumptions.setCamId(camId);
	}

	/**
	* Sets the commentary of this st m assumptions.
	*
	* @param commentary the commentary of this st m assumptions
	*/
	@Override
	public void setCommentary(java.lang.String commentary) {
		_stMAssumptions.setCommentary(commentary);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stMAssumptions.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stMAssumptions.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stMAssumptions.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gross sales percent change of this st m assumptions.
	*
	* @param grossSalesPercentChange the gross sales percent change of this st m assumptions
	*/
	@Override
	public void setGrossSalesPercentChange(double grossSalesPercentChange) {
		_stMAssumptions.setGrossSalesPercentChange(grossSalesPercentChange);
	}

	/**
	* Sets the gross sales prior of this st m assumptions.
	*
	* @param grossSalesPrior the gross sales prior of this st m assumptions
	*/
	@Override
	public void setGrossSalesPrior(double grossSalesPrior) {
		_stMAssumptions.setGrossSalesPrior(grossSalesPrior);
	}

	/**
	* Sets the gross sales projected of this st m assumptions.
	*
	* @param grossSalesProjected the gross sales projected of this st m assumptions
	*/
	@Override
	public void setGrossSalesProjected(double grossSalesProjected) {
		_stMAssumptions.setGrossSalesProjected(grossSalesProjected);
	}

	/**
	* Sets whether this st m assumptions is is checked.
	*
	* @param isChecked the is checked of this st m assumptions
	*/
	@Override
	public void setIsChecked(boolean isChecked) {
		_stMAssumptions.setIsChecked(isChecked);
	}

	/**
	* Sets the last modified date of this st m assumptions.
	*
	* @param lastModifiedDate the last modified date of this st m assumptions
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_stMAssumptions.setLastModifiedDate(lastModifiedDate);
	}

	/**
	* Sets the m assumptions sid of this st m assumptions.
	*
	* @param mAssumptionsSid the m assumptions sid of this st m assumptions
	*/
	@Override
	public void setMAssumptionsSid(int mAssumptionsSid) {
		_stMAssumptions.setMAssumptionsSid(mAssumptionsSid);
	}

	/**
	* Sets the net sales percent change of this st m assumptions.
	*
	* @param netSalesPercentChange the net sales percent change of this st m assumptions
	*/
	@Override
	public void setNetSalesPercentChange(double netSalesPercentChange) {
		_stMAssumptions.setNetSalesPercentChange(netSalesPercentChange);
	}

	/**
	* Sets the net sales prior of this st m assumptions.
	*
	* @param netSalesPrior the net sales prior of this st m assumptions
	*/
	@Override
	public void setNetSalesPrior(double netSalesPrior) {
		_stMAssumptions.setNetSalesPrior(netSalesPrior);
	}

	/**
	* Sets the net sales projected of this st m assumptions.
	*
	* @param netSalesProjected the net sales projected of this st m assumptions
	*/
	@Override
	public void setNetSalesProjected(double netSalesProjected) {
		_stMAssumptions.setNetSalesProjected(netSalesProjected);
	}

	@Override
	public void setNew(boolean n) {
		_stMAssumptions.setNew(n);
	}

	/**
	* Sets whether this st m assumptions is parent.
	*
	* @param parent the parent of this st m assumptions
	*/
	@Override
	public void setParent(boolean parent) {
		_stMAssumptions.setParent(parent);
	}

	/**
	* Sets the primary key of this st m assumptions.
	*
	* @param primaryKey the primary key of this st m assumptions
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.StMAssumptionsPK primaryKey) {
		_stMAssumptions.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stMAssumptions.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this st m assumptions.
	*
	* @param projectionDetailsSid the projection details sid of this st m assumptions
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_stMAssumptions.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the projection period of this st m assumptions.
	*
	* @param projectionPeriod the projection period of this st m assumptions
	*/
	@Override
	public void setProjectionPeriod(int projectionPeriod) {
		_stMAssumptions.setProjectionPeriod(projectionPeriod);
	}

	/**
	* Sets the proj year of this st m assumptions.
	*
	* @param projYear the proj year of this st m assumptions
	*/
	@Override
	public void setProjYear(int projYear) {
		_stMAssumptions.setProjYear(projYear);
	}

	/**
	* Sets the reason codes of this st m assumptions.
	*
	* @param reasonCodes the reason codes of this st m assumptions
	*/
	@Override
	public void setReasonCodes(java.lang.String reasonCodes) {
		_stMAssumptions.setReasonCodes(reasonCodes);
	}

	/**
	* Sets the session ID of this st m assumptions.
	*
	* @param sessionId the session ID of this st m assumptions
	*/
	@Override
	public void setSessionId(int sessionId) {
		_stMAssumptions.setSessionId(sessionId);
	}

	/**
	* Sets the st m assumptions sid of this st m assumptions.
	*
	* @param stMAssumptionsSid the st m assumptions sid of this st m assumptions
	*/
	@Override
	public void setStMAssumptionsSid(java.lang.String stMAssumptionsSid) {
		_stMAssumptions.setStMAssumptionsSid(stMAssumptionsSid);
	}

	/**
	* Sets the total discount percent change of this st m assumptions.
	*
	* @param totalDiscountPercentChange the total discount percent change of this st m assumptions
	*/
	@Override
	public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
		_stMAssumptions.setTotalDiscountPercentChange(totalDiscountPercentChange);
	}

	/**
	* Sets the total discount percent prior of this st m assumptions.
	*
	* @param totalDiscountPercentPrior the total discount percent prior of this st m assumptions
	*/
	@Override
	public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
		_stMAssumptions.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
	}

	/**
	* Sets the total discount percent projected of this st m assumptions.
	*
	* @param totalDiscountPercentProjected the total discount percent projected of this st m assumptions
	*/
	@Override
	public void setTotalDiscountPercentProjected(
		double totalDiscountPercentProjected) {
		_stMAssumptions.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
	}

	/**
	* Sets the user ID of this st m assumptions.
	*
	* @param userId the user ID of this st m assumptions
	*/
	@Override
	public void setUserId(int userId) {
		_stMAssumptions.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StMAssumptions> toCacheModel() {
		return _stMAssumptions.toCacheModel();
	}

	@Override
	public StMAssumptions toEscapedModel() {
		return new StMAssumptionsWrapper(_stMAssumptions.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stMAssumptions.toString();
	}

	@Override
	public StMAssumptions toUnescapedModel() {
		return new StMAssumptionsWrapper(_stMAssumptions.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stMAssumptions.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StMAssumptionsWrapper)) {
			return false;
		}

		StMAssumptionsWrapper stMAssumptionsWrapper = (StMAssumptionsWrapper)obj;

		if (Objects.equals(_stMAssumptions,
					stMAssumptionsWrapper._stMAssumptions)) {
			return true;
		}

		return false;
	}

	@Override
	public StMAssumptions getWrappedModel() {
		return _stMAssumptions;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stMAssumptions.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stMAssumptions.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stMAssumptions.resetOriginalValues();
	}

	private final StMAssumptions _stMAssumptions;
}