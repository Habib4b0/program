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
 * This class is a wrapper for {@link ProjectionCustDetails}.
 * </p>
 *
 * @author
 * @see ProjectionCustDetails
 * @generated
 */
@ProviderType
public class ProjectionCustDetailsWrapper implements ProjectionCustDetails,
	ModelWrapper<ProjectionCustDetails> {
	public ProjectionCustDetailsWrapper(
		ProjectionCustDetails projectionCustDetails) {
		_projectionCustDetails = projectionCustDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return ProjectionCustDetails.class;
	}

	@Override
	public String getModelClassName() {
		return ProjectionCustDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contractName", getContractName());
		attributes.put("customerName", getCustomerName());
		attributes.put("customerDetailsId", getCustomerDetailsId());
		attributes.put("costCenter", getCostCenter());
		attributes.put("customerAlias", getCustomerAlias());
		attributes.put("subLedgerCode", getSubLedgerCode());
		attributes.put("projectionId", getProjectionId());
		attributes.put("marketType", getMarketType());
		attributes.put("contractNo", getContractNo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String contractName = (String)attributes.get("contractName");

		if (contractName != null) {
			setContractName(contractName);
		}

		String customerName = (String)attributes.get("customerName");

		if (customerName != null) {
			setCustomerName(customerName);
		}

		Integer customerDetailsId = (Integer)attributes.get("customerDetailsId");

		if (customerDetailsId != null) {
			setCustomerDetailsId(customerDetailsId);
		}

		String costCenter = (String)attributes.get("costCenter");

		if (costCenter != null) {
			setCostCenter(costCenter);
		}

		String customerAlias = (String)attributes.get("customerAlias");

		if (customerAlias != null) {
			setCustomerAlias(customerAlias);
		}

		String subLedgerCode = (String)attributes.get("subLedgerCode");

		if (subLedgerCode != null) {
			setSubLedgerCode(subLedgerCode);
		}

		Integer projectionId = (Integer)attributes.get("projectionId");

		if (projectionId != null) {
			setProjectionId(projectionId);
		}

		String marketType = (String)attributes.get("marketType");

		if (marketType != null) {
			setMarketType(marketType);
		}

		String contractNo = (String)attributes.get("contractNo");

		if (contractNo != null) {
			setContractNo(contractNo);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectionCustDetailsWrapper((ProjectionCustDetails)_projectionCustDetails.clone());
	}

	@Override
	public int compareTo(ProjectionCustDetails projectionCustDetails) {
		return _projectionCustDetails.compareTo(projectionCustDetails);
	}

	/**
	* Returns the contract name of this projection cust details.
	*
	* @return the contract name of this projection cust details
	*/
	@Override
	public java.lang.String getContractName() {
		return _projectionCustDetails.getContractName();
	}

	/**
	* Returns the contract no of this projection cust details.
	*
	* @return the contract no of this projection cust details
	*/
	@Override
	public java.lang.String getContractNo() {
		return _projectionCustDetails.getContractNo();
	}

	/**
	* Returns the cost center of this projection cust details.
	*
	* @return the cost center of this projection cust details
	*/
	@Override
	public java.lang.String getCostCenter() {
		return _projectionCustDetails.getCostCenter();
	}

	/**
	* Returns the customer alias of this projection cust details.
	*
	* @return the customer alias of this projection cust details
	*/
	@Override
	public java.lang.String getCustomerAlias() {
		return _projectionCustDetails.getCustomerAlias();
	}

	/**
	* Returns the customer details ID of this projection cust details.
	*
	* @return the customer details ID of this projection cust details
	*/
	@Override
	public int getCustomerDetailsId() {
		return _projectionCustDetails.getCustomerDetailsId();
	}

	/**
	* Returns the customer name of this projection cust details.
	*
	* @return the customer name of this projection cust details
	*/
	@Override
	public java.lang.String getCustomerName() {
		return _projectionCustDetails.getCustomerName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _projectionCustDetails.getExpandoBridge();
	}

	/**
	* Returns the market type of this projection cust details.
	*
	* @return the market type of this projection cust details
	*/
	@Override
	public java.lang.String getMarketType() {
		return _projectionCustDetails.getMarketType();
	}

	/**
	* Returns the primary key of this projection cust details.
	*
	* @return the primary key of this projection cust details
	*/
	@Override
	public int getPrimaryKey() {
		return _projectionCustDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectionCustDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the projection ID of this projection cust details.
	*
	* @return the projection ID of this projection cust details
	*/
	@Override
	public int getProjectionId() {
		return _projectionCustDetails.getProjectionId();
	}

	/**
	* Returns the sub ledger code of this projection cust details.
	*
	* @return the sub ledger code of this projection cust details
	*/
	@Override
	public java.lang.String getSubLedgerCode() {
		return _projectionCustDetails.getSubLedgerCode();
	}

	@Override
	public int hashCode() {
		return _projectionCustDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _projectionCustDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _projectionCustDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _projectionCustDetails.isNew();
	}

	@Override
	public void persist() {
		_projectionCustDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_projectionCustDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the contract name of this projection cust details.
	*
	* @param contractName the contract name of this projection cust details
	*/
	@Override
	public void setContractName(java.lang.String contractName) {
		_projectionCustDetails.setContractName(contractName);
	}

	/**
	* Sets the contract no of this projection cust details.
	*
	* @param contractNo the contract no of this projection cust details
	*/
	@Override
	public void setContractNo(java.lang.String contractNo) {
		_projectionCustDetails.setContractNo(contractNo);
	}

	/**
	* Sets the cost center of this projection cust details.
	*
	* @param costCenter the cost center of this projection cust details
	*/
	@Override
	public void setCostCenter(java.lang.String costCenter) {
		_projectionCustDetails.setCostCenter(costCenter);
	}

	/**
	* Sets the customer alias of this projection cust details.
	*
	* @param customerAlias the customer alias of this projection cust details
	*/
	@Override
	public void setCustomerAlias(java.lang.String customerAlias) {
		_projectionCustDetails.setCustomerAlias(customerAlias);
	}

	/**
	* Sets the customer details ID of this projection cust details.
	*
	* @param customerDetailsId the customer details ID of this projection cust details
	*/
	@Override
	public void setCustomerDetailsId(int customerDetailsId) {
		_projectionCustDetails.setCustomerDetailsId(customerDetailsId);
	}

	/**
	* Sets the customer name of this projection cust details.
	*
	* @param customerName the customer name of this projection cust details
	*/
	@Override
	public void setCustomerName(java.lang.String customerName) {
		_projectionCustDetails.setCustomerName(customerName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_projectionCustDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_projectionCustDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_projectionCustDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the market type of this projection cust details.
	*
	* @param marketType the market type of this projection cust details
	*/
	@Override
	public void setMarketType(java.lang.String marketType) {
		_projectionCustDetails.setMarketType(marketType);
	}

	@Override
	public void setNew(boolean n) {
		_projectionCustDetails.setNew(n);
	}

	/**
	* Sets the primary key of this projection cust details.
	*
	* @param primaryKey the primary key of this projection cust details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_projectionCustDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_projectionCustDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection ID of this projection cust details.
	*
	* @param projectionId the projection ID of this projection cust details
	*/
	@Override
	public void setProjectionId(int projectionId) {
		_projectionCustDetails.setProjectionId(projectionId);
	}

	/**
	* Sets the sub ledger code of this projection cust details.
	*
	* @param subLedgerCode the sub ledger code of this projection cust details
	*/
	@Override
	public void setSubLedgerCode(java.lang.String subLedgerCode) {
		_projectionCustDetails.setSubLedgerCode(subLedgerCode);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProjectionCustDetails> toCacheModel() {
		return _projectionCustDetails.toCacheModel();
	}

	@Override
	public ProjectionCustDetails toEscapedModel() {
		return new ProjectionCustDetailsWrapper(_projectionCustDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _projectionCustDetails.toString();
	}

	@Override
	public ProjectionCustDetails toUnescapedModel() {
		return new ProjectionCustDetailsWrapper(_projectionCustDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _projectionCustDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectionCustDetailsWrapper)) {
			return false;
		}

		ProjectionCustDetailsWrapper projectionCustDetailsWrapper = (ProjectionCustDetailsWrapper)obj;

		if (Objects.equals(_projectionCustDetails,
					projectionCustDetailsWrapper._projectionCustDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public ProjectionCustDetails getWrappedModel() {
		return _projectionCustDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _projectionCustDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _projectionCustDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_projectionCustDetails.resetOriginalValues();
	}

	private final ProjectionCustDetails _projectionCustDetails;
}