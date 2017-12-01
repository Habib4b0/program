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
 * This class is a wrapper for {@link ProjectionProdDetails}.
 * </p>
 *
 * @author
 * @see ProjectionProdDetails
 * @generated
 */
@ProviderType
public class ProjectionProdDetailsWrapper implements ProjectionProdDetails,
	ModelWrapper<ProjectionProdDetails> {
	public ProjectionProdDetailsWrapper(
		ProjectionProdDetails projectionProdDetails) {
		_projectionProdDetails = projectionProdDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return ProjectionProdDetails.class;
	}

	@Override
	public String getModelClassName() {
		return ProjectionProdDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("productName", getProductName());
		attributes.put("costCenter", getCostCenter());
		attributes.put("productNo", getProductNo());
		attributes.put("subLedgerCode", getSubLedgerCode());
		attributes.put("productDetailsId", getProductDetailsId());
		attributes.put("brandName", getBrandName());
		attributes.put("projectionId", getProjectionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String productName = (String)attributes.get("productName");

		if (productName != null) {
			setProductName(productName);
		}

		String costCenter = (String)attributes.get("costCenter");

		if (costCenter != null) {
			setCostCenter(costCenter);
		}

		String productNo = (String)attributes.get("productNo");

		if (productNo != null) {
			setProductNo(productNo);
		}

		String subLedgerCode = (String)attributes.get("subLedgerCode");

		if (subLedgerCode != null) {
			setSubLedgerCode(subLedgerCode);
		}

		Integer productDetailsId = (Integer)attributes.get("productDetailsId");

		if (productDetailsId != null) {
			setProductDetailsId(productDetailsId);
		}

		String brandName = (String)attributes.get("brandName");

		if (brandName != null) {
			setBrandName(brandName);
		}

		Integer projectionId = (Integer)attributes.get("projectionId");

		if (projectionId != null) {
			setProjectionId(projectionId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectionProdDetailsWrapper((ProjectionProdDetails)_projectionProdDetails.clone());
	}

	@Override
	public int compareTo(ProjectionProdDetails projectionProdDetails) {
		return _projectionProdDetails.compareTo(projectionProdDetails);
	}

	/**
	* Returns the brand name of this projection prod details.
	*
	* @return the brand name of this projection prod details
	*/
	@Override
	public java.lang.String getBrandName() {
		return _projectionProdDetails.getBrandName();
	}

	/**
	* Returns the cost center of this projection prod details.
	*
	* @return the cost center of this projection prod details
	*/
	@Override
	public java.lang.String getCostCenter() {
		return _projectionProdDetails.getCostCenter();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _projectionProdDetails.getExpandoBridge();
	}

	/**
	* Returns the primary key of this projection prod details.
	*
	* @return the primary key of this projection prod details
	*/
	@Override
	public int getPrimaryKey() {
		return _projectionProdDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectionProdDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the product details ID of this projection prod details.
	*
	* @return the product details ID of this projection prod details
	*/
	@Override
	public int getProductDetailsId() {
		return _projectionProdDetails.getProductDetailsId();
	}

	/**
	* Returns the product name of this projection prod details.
	*
	* @return the product name of this projection prod details
	*/
	@Override
	public java.lang.String getProductName() {
		return _projectionProdDetails.getProductName();
	}

	/**
	* Returns the product no of this projection prod details.
	*
	* @return the product no of this projection prod details
	*/
	@Override
	public java.lang.String getProductNo() {
		return _projectionProdDetails.getProductNo();
	}

	/**
	* Returns the projection ID of this projection prod details.
	*
	* @return the projection ID of this projection prod details
	*/
	@Override
	public int getProjectionId() {
		return _projectionProdDetails.getProjectionId();
	}

	/**
	* Returns the sub ledger code of this projection prod details.
	*
	* @return the sub ledger code of this projection prod details
	*/
	@Override
	public java.lang.String getSubLedgerCode() {
		return _projectionProdDetails.getSubLedgerCode();
	}

	@Override
	public int hashCode() {
		return _projectionProdDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _projectionProdDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _projectionProdDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _projectionProdDetails.isNew();
	}

	@Override
	public void persist() {
		_projectionProdDetails.persist();
	}

	/**
	* Sets the brand name of this projection prod details.
	*
	* @param brandName the brand name of this projection prod details
	*/
	@Override
	public void setBrandName(java.lang.String brandName) {
		_projectionProdDetails.setBrandName(brandName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_projectionProdDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the cost center of this projection prod details.
	*
	* @param costCenter the cost center of this projection prod details
	*/
	@Override
	public void setCostCenter(java.lang.String costCenter) {
		_projectionProdDetails.setCostCenter(costCenter);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_projectionProdDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_projectionProdDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_projectionProdDetails.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_projectionProdDetails.setNew(n);
	}

	/**
	* Sets the primary key of this projection prod details.
	*
	* @param primaryKey the primary key of this projection prod details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_projectionProdDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_projectionProdDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product details ID of this projection prod details.
	*
	* @param productDetailsId the product details ID of this projection prod details
	*/
	@Override
	public void setProductDetailsId(int productDetailsId) {
		_projectionProdDetails.setProductDetailsId(productDetailsId);
	}

	/**
	* Sets the product name of this projection prod details.
	*
	* @param productName the product name of this projection prod details
	*/
	@Override
	public void setProductName(java.lang.String productName) {
		_projectionProdDetails.setProductName(productName);
	}

	/**
	* Sets the product no of this projection prod details.
	*
	* @param productNo the product no of this projection prod details
	*/
	@Override
	public void setProductNo(java.lang.String productNo) {
		_projectionProdDetails.setProductNo(productNo);
	}

	/**
	* Sets the projection ID of this projection prod details.
	*
	* @param projectionId the projection ID of this projection prod details
	*/
	@Override
	public void setProjectionId(int projectionId) {
		_projectionProdDetails.setProjectionId(projectionId);
	}

	/**
	* Sets the sub ledger code of this projection prod details.
	*
	* @param subLedgerCode the sub ledger code of this projection prod details
	*/
	@Override
	public void setSubLedgerCode(java.lang.String subLedgerCode) {
		_projectionProdDetails.setSubLedgerCode(subLedgerCode);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProjectionProdDetails> toCacheModel() {
		return _projectionProdDetails.toCacheModel();
	}

	@Override
	public ProjectionProdDetails toEscapedModel() {
		return new ProjectionProdDetailsWrapper(_projectionProdDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _projectionProdDetails.toString();
	}

	@Override
	public ProjectionProdDetails toUnescapedModel() {
		return new ProjectionProdDetailsWrapper(_projectionProdDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _projectionProdDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectionProdDetailsWrapper)) {
			return false;
		}

		ProjectionProdDetailsWrapper projectionProdDetailsWrapper = (ProjectionProdDetailsWrapper)obj;

		if (Objects.equals(_projectionProdDetails,
					projectionProdDetailsWrapper._projectionProdDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public ProjectionProdDetails getWrappedModel() {
		return _projectionProdDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _projectionProdDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _projectionProdDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_projectionProdDetails.resetOriginalValues();
	}

	private final ProjectionProdDetails _projectionProdDetails;
}