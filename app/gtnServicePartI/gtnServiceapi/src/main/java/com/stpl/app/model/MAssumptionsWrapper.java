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
 * This class is a wrapper for {@link MAssumptions}.
 * </p>
 *
 * @author
 * @see MAssumptions
 * @generated
 */
@ProviderType
public class MAssumptionsWrapper implements MAssumptions,
	ModelWrapper<MAssumptions> {
	public MAssumptionsWrapper(MAssumptions mAssumptions) {
		_mAssumptions = mAssumptions;
	}

	@Override
	public Class<?> getModelClass() {
		return MAssumptions.class;
	}

	@Override
	public String getModelClassName() {
		return MAssumptions.class.getName();
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
		attributes.put("grossSalesProjected", getGrossSalesProjected());
		attributes.put("totalDiscountPercentChange",
			getTotalDiscountPercentChange());
		attributes.put("totalDiscountPercentPrior",
			getTotalDiscountPercentPrior());
		attributes.put("netSalesPercentChange", getNetSalesPercentChange());
		attributes.put("parent", getParent());
		attributes.put("projectionPeriod", getProjectionPeriod());
		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("netSalesPrior", getNetSalesPrior());
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
		return new MAssumptionsWrapper((MAssumptions)_mAssumptions.clone());
	}

	@Override
	public int compareTo(MAssumptions mAssumptions) {
		return _mAssumptions.compareTo(mAssumptions);
	}

	/**
	* Returns the cam ID of this m assumptions.
	*
	* @return the cam ID of this m assumptions
	*/
	@Override
	public int getCamId() {
		return _mAssumptions.getCamId();
	}

	/**
	* Returns the commentary of this m assumptions.
	*
	* @return the commentary of this m assumptions
	*/
	@Override
	public java.lang.String getCommentary() {
		return _mAssumptions.getCommentary();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mAssumptions.getExpandoBridge();
	}

	/**
	* Returns the gross sales percent change of this m assumptions.
	*
	* @return the gross sales percent change of this m assumptions
	*/
	@Override
	public double getGrossSalesPercentChange() {
		return _mAssumptions.getGrossSalesPercentChange();
	}

	/**
	* Returns the gross sales prior of this m assumptions.
	*
	* @return the gross sales prior of this m assumptions
	*/
	@Override
	public double getGrossSalesPrior() {
		return _mAssumptions.getGrossSalesPrior();
	}

	/**
	* Returns the gross sales projected of this m assumptions.
	*
	* @return the gross sales projected of this m assumptions
	*/
	@Override
	public double getGrossSalesProjected() {
		return _mAssumptions.getGrossSalesProjected();
	}

	/**
	* Returns the m assumptions sid of this m assumptions.
	*
	* @return the m assumptions sid of this m assumptions
	*/
	@Override
	public int getMAssumptionsSid() {
		return _mAssumptions.getMAssumptionsSid();
	}

	/**
	* Returns the net sales percent change of this m assumptions.
	*
	* @return the net sales percent change of this m assumptions
	*/
	@Override
	public double getNetSalesPercentChange() {
		return _mAssumptions.getNetSalesPercentChange();
	}

	/**
	* Returns the net sales prior of this m assumptions.
	*
	* @return the net sales prior of this m assumptions
	*/
	@Override
	public double getNetSalesPrior() {
		return _mAssumptions.getNetSalesPrior();
	}

	/**
	* Returns the net sales projected of this m assumptions.
	*
	* @return the net sales projected of this m assumptions
	*/
	@Override
	public double getNetSalesProjected() {
		return _mAssumptions.getNetSalesProjected();
	}

	/**
	* Returns the parent of this m assumptions.
	*
	* @return the parent of this m assumptions
	*/
	@Override
	public boolean getParent() {
		return _mAssumptions.getParent();
	}

	/**
	* Returns the primary key of this m assumptions.
	*
	* @return the primary key of this m assumptions
	*/
	@Override
	public int getPrimaryKey() {
		return _mAssumptions.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mAssumptions.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this m assumptions.
	*
	* @return the projection details sid of this m assumptions
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _mAssumptions.getProjectionDetailsSid();
	}

	/**
	* Returns the projection period of this m assumptions.
	*
	* @return the projection period of this m assumptions
	*/
	@Override
	public int getProjectionPeriod() {
		return _mAssumptions.getProjectionPeriod();
	}

	/**
	* Returns the proj year of this m assumptions.
	*
	* @return the proj year of this m assumptions
	*/
	@Override
	public int getProjYear() {
		return _mAssumptions.getProjYear();
	}

	/**
	* Returns the reason codes of this m assumptions.
	*
	* @return the reason codes of this m assumptions
	*/
	@Override
	public java.lang.String getReasonCodes() {
		return _mAssumptions.getReasonCodes();
	}

	/**
	* Returns the total discount percent change of this m assumptions.
	*
	* @return the total discount percent change of this m assumptions
	*/
	@Override
	public double getTotalDiscountPercentChange() {
		return _mAssumptions.getTotalDiscountPercentChange();
	}

	/**
	* Returns the total discount percent prior of this m assumptions.
	*
	* @return the total discount percent prior of this m assumptions
	*/
	@Override
	public double getTotalDiscountPercentPrior() {
		return _mAssumptions.getTotalDiscountPercentPrior();
	}

	/**
	* Returns the total discount percent projected of this m assumptions.
	*
	* @return the total discount percent projected of this m assumptions
	*/
	@Override
	public double getTotalDiscountPercentProjected() {
		return _mAssumptions.getTotalDiscountPercentProjected();
	}

	@Override
	public int hashCode() {
		return _mAssumptions.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _mAssumptions.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _mAssumptions.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _mAssumptions.isNew();
	}

	/**
	* Returns <code>true</code> if this m assumptions is parent.
	*
	* @return <code>true</code> if this m assumptions is parent; <code>false</code> otherwise
	*/
	@Override
	public boolean isParent() {
		return _mAssumptions.isParent();
	}

	@Override
	public void persist() {
		_mAssumptions.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mAssumptions.setCachedModel(cachedModel);
	}

	/**
	* Sets the cam ID of this m assumptions.
	*
	* @param camId the cam ID of this m assumptions
	*/
	@Override
	public void setCamId(int camId) {
		_mAssumptions.setCamId(camId);
	}

	/**
	* Sets the commentary of this m assumptions.
	*
	* @param commentary the commentary of this m assumptions
	*/
	@Override
	public void setCommentary(java.lang.String commentary) {
		_mAssumptions.setCommentary(commentary);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_mAssumptions.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_mAssumptions.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_mAssumptions.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gross sales percent change of this m assumptions.
	*
	* @param grossSalesPercentChange the gross sales percent change of this m assumptions
	*/
	@Override
	public void setGrossSalesPercentChange(double grossSalesPercentChange) {
		_mAssumptions.setGrossSalesPercentChange(grossSalesPercentChange);
	}

	/**
	* Sets the gross sales prior of this m assumptions.
	*
	* @param grossSalesPrior the gross sales prior of this m assumptions
	*/
	@Override
	public void setGrossSalesPrior(double grossSalesPrior) {
		_mAssumptions.setGrossSalesPrior(grossSalesPrior);
	}

	/**
	* Sets the gross sales projected of this m assumptions.
	*
	* @param grossSalesProjected the gross sales projected of this m assumptions
	*/
	@Override
	public void setGrossSalesProjected(double grossSalesProjected) {
		_mAssumptions.setGrossSalesProjected(grossSalesProjected);
	}

	/**
	* Sets the m assumptions sid of this m assumptions.
	*
	* @param mAssumptionsSid the m assumptions sid of this m assumptions
	*/
	@Override
	public void setMAssumptionsSid(int mAssumptionsSid) {
		_mAssumptions.setMAssumptionsSid(mAssumptionsSid);
	}

	/**
	* Sets the net sales percent change of this m assumptions.
	*
	* @param netSalesPercentChange the net sales percent change of this m assumptions
	*/
	@Override
	public void setNetSalesPercentChange(double netSalesPercentChange) {
		_mAssumptions.setNetSalesPercentChange(netSalesPercentChange);
	}

	/**
	* Sets the net sales prior of this m assumptions.
	*
	* @param netSalesPrior the net sales prior of this m assumptions
	*/
	@Override
	public void setNetSalesPrior(double netSalesPrior) {
		_mAssumptions.setNetSalesPrior(netSalesPrior);
	}

	/**
	* Sets the net sales projected of this m assumptions.
	*
	* @param netSalesProjected the net sales projected of this m assumptions
	*/
	@Override
	public void setNetSalesProjected(double netSalesProjected) {
		_mAssumptions.setNetSalesProjected(netSalesProjected);
	}

	@Override
	public void setNew(boolean n) {
		_mAssumptions.setNew(n);
	}

	/**
	* Sets whether this m assumptions is parent.
	*
	* @param parent the parent of this m assumptions
	*/
	@Override
	public void setParent(boolean parent) {
		_mAssumptions.setParent(parent);
	}

	/**
	* Sets the primary key of this m assumptions.
	*
	* @param primaryKey the primary key of this m assumptions
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_mAssumptions.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_mAssumptions.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this m assumptions.
	*
	* @param projectionDetailsSid the projection details sid of this m assumptions
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_mAssumptions.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the projection period of this m assumptions.
	*
	* @param projectionPeriod the projection period of this m assumptions
	*/
	@Override
	public void setProjectionPeriod(int projectionPeriod) {
		_mAssumptions.setProjectionPeriod(projectionPeriod);
	}

	/**
	* Sets the proj year of this m assumptions.
	*
	* @param projYear the proj year of this m assumptions
	*/
	@Override
	public void setProjYear(int projYear) {
		_mAssumptions.setProjYear(projYear);
	}

	/**
	* Sets the reason codes of this m assumptions.
	*
	* @param reasonCodes the reason codes of this m assumptions
	*/
	@Override
	public void setReasonCodes(java.lang.String reasonCodes) {
		_mAssumptions.setReasonCodes(reasonCodes);
	}

	/**
	* Sets the total discount percent change of this m assumptions.
	*
	* @param totalDiscountPercentChange the total discount percent change of this m assumptions
	*/
	@Override
	public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
		_mAssumptions.setTotalDiscountPercentChange(totalDiscountPercentChange);
	}

	/**
	* Sets the total discount percent prior of this m assumptions.
	*
	* @param totalDiscountPercentPrior the total discount percent prior of this m assumptions
	*/
	@Override
	public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
		_mAssumptions.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
	}

	/**
	* Sets the total discount percent projected of this m assumptions.
	*
	* @param totalDiscountPercentProjected the total discount percent projected of this m assumptions
	*/
	@Override
	public void setTotalDiscountPercentProjected(
		double totalDiscountPercentProjected) {
		_mAssumptions.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MAssumptions> toCacheModel() {
		return _mAssumptions.toCacheModel();
	}

	@Override
	public MAssumptions toEscapedModel() {
		return new MAssumptionsWrapper(_mAssumptions.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _mAssumptions.toString();
	}

	@Override
	public MAssumptions toUnescapedModel() {
		return new MAssumptionsWrapper(_mAssumptions.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _mAssumptions.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MAssumptionsWrapper)) {
			return false;
		}

		MAssumptionsWrapper mAssumptionsWrapper = (MAssumptionsWrapper)obj;

		if (Objects.equals(_mAssumptions, mAssumptionsWrapper._mAssumptions)) {
			return true;
		}

		return false;
	}

	@Override
	public MAssumptions getWrappedModel() {
		return _mAssumptions;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _mAssumptions.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _mAssumptions.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_mAssumptions.resetOriginalValues();
	}

	private final MAssumptions _mAssumptions;
}