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
 * This class is a wrapper for {@link TransactionModuleDetails}.
 * </p>
 *
 * @author
 * @see TransactionModuleDetails
 * @generated
 */
@ProviderType
public class TransactionModuleDetailsWrapper implements TransactionModuleDetails,
	ModelWrapper<TransactionModuleDetails> {
	public TransactionModuleDetailsWrapper(
		TransactionModuleDetails transactionModuleDetails) {
		_transactionModuleDetails = transactionModuleDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return TransactionModuleDetails.class;
	}

	@Override
	public String getModelClassName() {
		return TransactionModuleDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("propertyIndex", getPropertyIndex());
		attributes.put("displayName", getDisplayName());
		attributes.put("transactionModuleMasterSid",
			getTransactionModuleMasterSid());
		attributes.put("categoryName", getCategoryName());
		attributes.put("validation", getValidation());
		attributes.put("propertyName", getPropertyName());
		attributes.put("flag", getFlag());
		attributes.put("transactionModuleDetailsSid",
			getTransactionModuleDetailsSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double propertyIndex = (Double)attributes.get("propertyIndex");

		if (propertyIndex != null) {
			setPropertyIndex(propertyIndex);
		}

		String displayName = (String)attributes.get("displayName");

		if (displayName != null) {
			setDisplayName(displayName);
		}

		Integer transactionModuleMasterSid = (Integer)attributes.get(
				"transactionModuleMasterSid");

		if (transactionModuleMasterSid != null) {
			setTransactionModuleMasterSid(transactionModuleMasterSid);
		}

		String categoryName = (String)attributes.get("categoryName");

		if (categoryName != null) {
			setCategoryName(categoryName);
		}

		String validation = (String)attributes.get("validation");

		if (validation != null) {
			setValidation(validation);
		}

		String propertyName = (String)attributes.get("propertyName");

		if (propertyName != null) {
			setPropertyName(propertyName);
		}

		String flag = (String)attributes.get("flag");

		if (flag != null) {
			setFlag(flag);
		}

		Integer transactionModuleDetailsSid = (Integer)attributes.get(
				"transactionModuleDetailsSid");

		if (transactionModuleDetailsSid != null) {
			setTransactionModuleDetailsSid(transactionModuleDetailsSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new TransactionModuleDetailsWrapper((TransactionModuleDetails)_transactionModuleDetails.clone());
	}

	@Override
	public int compareTo(TransactionModuleDetails transactionModuleDetails) {
		return _transactionModuleDetails.compareTo(transactionModuleDetails);
	}

	/**
	* Returns the category name of this transaction module details.
	*
	* @return the category name of this transaction module details
	*/
	@Override
	public java.lang.String getCategoryName() {
		return _transactionModuleDetails.getCategoryName();
	}

	/**
	* Returns the display name of this transaction module details.
	*
	* @return the display name of this transaction module details
	*/
	@Override
	public java.lang.String getDisplayName() {
		return _transactionModuleDetails.getDisplayName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _transactionModuleDetails.getExpandoBridge();
	}

	/**
	* Returns the flag of this transaction module details.
	*
	* @return the flag of this transaction module details
	*/
	@Override
	public java.lang.String getFlag() {
		return _transactionModuleDetails.getFlag();
	}

	/**
	* Returns the primary key of this transaction module details.
	*
	* @return the primary key of this transaction module details
	*/
	@Override
	public int getPrimaryKey() {
		return _transactionModuleDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _transactionModuleDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the property index of this transaction module details.
	*
	* @return the property index of this transaction module details
	*/
	@Override
	public double getPropertyIndex() {
		return _transactionModuleDetails.getPropertyIndex();
	}

	/**
	* Returns the property name of this transaction module details.
	*
	* @return the property name of this transaction module details
	*/
	@Override
	public java.lang.String getPropertyName() {
		return _transactionModuleDetails.getPropertyName();
	}

	/**
	* Returns the transaction module details sid of this transaction module details.
	*
	* @return the transaction module details sid of this transaction module details
	*/
	@Override
	public int getTransactionModuleDetailsSid() {
		return _transactionModuleDetails.getTransactionModuleDetailsSid();
	}

	/**
	* Returns the transaction module master sid of this transaction module details.
	*
	* @return the transaction module master sid of this transaction module details
	*/
	@Override
	public int getTransactionModuleMasterSid() {
		return _transactionModuleDetails.getTransactionModuleMasterSid();
	}

	/**
	* Returns the validation of this transaction module details.
	*
	* @return the validation of this transaction module details
	*/
	@Override
	public java.lang.String getValidation() {
		return _transactionModuleDetails.getValidation();
	}

	@Override
	public int hashCode() {
		return _transactionModuleDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _transactionModuleDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _transactionModuleDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _transactionModuleDetails.isNew();
	}

	@Override
	public void persist() {
		_transactionModuleDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_transactionModuleDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the category name of this transaction module details.
	*
	* @param categoryName the category name of this transaction module details
	*/
	@Override
	public void setCategoryName(java.lang.String categoryName) {
		_transactionModuleDetails.setCategoryName(categoryName);
	}

	/**
	* Sets the display name of this transaction module details.
	*
	* @param displayName the display name of this transaction module details
	*/
	@Override
	public void setDisplayName(java.lang.String displayName) {
		_transactionModuleDetails.setDisplayName(displayName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_transactionModuleDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_transactionModuleDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_transactionModuleDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the flag of this transaction module details.
	*
	* @param flag the flag of this transaction module details
	*/
	@Override
	public void setFlag(java.lang.String flag) {
		_transactionModuleDetails.setFlag(flag);
	}

	@Override
	public void setNew(boolean n) {
		_transactionModuleDetails.setNew(n);
	}

	/**
	* Sets the primary key of this transaction module details.
	*
	* @param primaryKey the primary key of this transaction module details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_transactionModuleDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_transactionModuleDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the property index of this transaction module details.
	*
	* @param propertyIndex the property index of this transaction module details
	*/
	@Override
	public void setPropertyIndex(double propertyIndex) {
		_transactionModuleDetails.setPropertyIndex(propertyIndex);
	}

	/**
	* Sets the property name of this transaction module details.
	*
	* @param propertyName the property name of this transaction module details
	*/
	@Override
	public void setPropertyName(java.lang.String propertyName) {
		_transactionModuleDetails.setPropertyName(propertyName);
	}

	/**
	* Sets the transaction module details sid of this transaction module details.
	*
	* @param transactionModuleDetailsSid the transaction module details sid of this transaction module details
	*/
	@Override
	public void setTransactionModuleDetailsSid(int transactionModuleDetailsSid) {
		_transactionModuleDetails.setTransactionModuleDetailsSid(transactionModuleDetailsSid);
	}

	/**
	* Sets the transaction module master sid of this transaction module details.
	*
	* @param transactionModuleMasterSid the transaction module master sid of this transaction module details
	*/
	@Override
	public void setTransactionModuleMasterSid(int transactionModuleMasterSid) {
		_transactionModuleDetails.setTransactionModuleMasterSid(transactionModuleMasterSid);
	}

	/**
	* Sets the validation of this transaction module details.
	*
	* @param validation the validation of this transaction module details
	*/
	@Override
	public void setValidation(java.lang.String validation) {
		_transactionModuleDetails.setValidation(validation);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TransactionModuleDetails> toCacheModel() {
		return _transactionModuleDetails.toCacheModel();
	}

	@Override
	public TransactionModuleDetails toEscapedModel() {
		return new TransactionModuleDetailsWrapper(_transactionModuleDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _transactionModuleDetails.toString();
	}

	@Override
	public TransactionModuleDetails toUnescapedModel() {
		return new TransactionModuleDetailsWrapper(_transactionModuleDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _transactionModuleDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TransactionModuleDetailsWrapper)) {
			return false;
		}

		TransactionModuleDetailsWrapper transactionModuleDetailsWrapper = (TransactionModuleDetailsWrapper)obj;

		if (Objects.equals(_transactionModuleDetails,
					transactionModuleDetailsWrapper._transactionModuleDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public TransactionModuleDetails getWrappedModel() {
		return _transactionModuleDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _transactionModuleDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _transactionModuleDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_transactionModuleDetails.resetOriginalValues();
	}

	private final TransactionModuleDetails _transactionModuleDetails;
}