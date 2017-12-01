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
 * This class is a wrapper for {@link TransactionModuleMaster}.
 * </p>
 *
 * @author
 * @see TransactionModuleMaster
 * @generated
 */
@ProviderType
public class TransactionModuleMasterWrapper implements TransactionModuleMaster,
	ModelWrapper<TransactionModuleMaster> {
	public TransactionModuleMasterWrapper(
		TransactionModuleMaster transactionModuleMaster) {
		_transactionModuleMaster = transactionModuleMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return TransactionModuleMaster.class;
	}

	@Override
	public String getModelClassName() {
		return TransactionModuleMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("transactionModuleMasterSid",
			getTransactionModuleMasterSid());
		attributes.put("invalidTableName", getInvalidTableName());
		attributes.put("tableName", getTableName());
		attributes.put("moduleName", getModuleName());
		attributes.put("tabName", getTabName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer transactionModuleMasterSid = (Integer)attributes.get(
				"transactionModuleMasterSid");

		if (transactionModuleMasterSid != null) {
			setTransactionModuleMasterSid(transactionModuleMasterSid);
		}

		String invalidTableName = (String)attributes.get("invalidTableName");

		if (invalidTableName != null) {
			setInvalidTableName(invalidTableName);
		}

		String tableName = (String)attributes.get("tableName");

		if (tableName != null) {
			setTableName(tableName);
		}

		String moduleName = (String)attributes.get("moduleName");

		if (moduleName != null) {
			setModuleName(moduleName);
		}

		String tabName = (String)attributes.get("tabName");

		if (tabName != null) {
			setTabName(tabName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new TransactionModuleMasterWrapper((TransactionModuleMaster)_transactionModuleMaster.clone());
	}

	@Override
	public int compareTo(TransactionModuleMaster transactionModuleMaster) {
		return _transactionModuleMaster.compareTo(transactionModuleMaster);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _transactionModuleMaster.getExpandoBridge();
	}

	/**
	* Returns the invalid table name of this transaction module master.
	*
	* @return the invalid table name of this transaction module master
	*/
	@Override
	public java.lang.String getInvalidTableName() {
		return _transactionModuleMaster.getInvalidTableName();
	}

	/**
	* Returns the module name of this transaction module master.
	*
	* @return the module name of this transaction module master
	*/
	@Override
	public java.lang.String getModuleName() {
		return _transactionModuleMaster.getModuleName();
	}

	/**
	* Returns the primary key of this transaction module master.
	*
	* @return the primary key of this transaction module master
	*/
	@Override
	public int getPrimaryKey() {
		return _transactionModuleMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _transactionModuleMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the table name of this transaction module master.
	*
	* @return the table name of this transaction module master
	*/
	@Override
	public java.lang.String getTableName() {
		return _transactionModuleMaster.getTableName();
	}

	/**
	* Returns the tab name of this transaction module master.
	*
	* @return the tab name of this transaction module master
	*/
	@Override
	public java.lang.String getTabName() {
		return _transactionModuleMaster.getTabName();
	}

	/**
	* Returns the transaction module master sid of this transaction module master.
	*
	* @return the transaction module master sid of this transaction module master
	*/
	@Override
	public int getTransactionModuleMasterSid() {
		return _transactionModuleMaster.getTransactionModuleMasterSid();
	}

	@Override
	public int hashCode() {
		return _transactionModuleMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _transactionModuleMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _transactionModuleMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _transactionModuleMaster.isNew();
	}

	@Override
	public void persist() {
		_transactionModuleMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_transactionModuleMaster.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_transactionModuleMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_transactionModuleMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_transactionModuleMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the invalid table name of this transaction module master.
	*
	* @param invalidTableName the invalid table name of this transaction module master
	*/
	@Override
	public void setInvalidTableName(java.lang.String invalidTableName) {
		_transactionModuleMaster.setInvalidTableName(invalidTableName);
	}

	/**
	* Sets the module name of this transaction module master.
	*
	* @param moduleName the module name of this transaction module master
	*/
	@Override
	public void setModuleName(java.lang.String moduleName) {
		_transactionModuleMaster.setModuleName(moduleName);
	}

	@Override
	public void setNew(boolean n) {
		_transactionModuleMaster.setNew(n);
	}

	/**
	* Sets the primary key of this transaction module master.
	*
	* @param primaryKey the primary key of this transaction module master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_transactionModuleMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_transactionModuleMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the table name of this transaction module master.
	*
	* @param tableName the table name of this transaction module master
	*/
	@Override
	public void setTableName(java.lang.String tableName) {
		_transactionModuleMaster.setTableName(tableName);
	}

	/**
	* Sets the tab name of this transaction module master.
	*
	* @param tabName the tab name of this transaction module master
	*/
	@Override
	public void setTabName(java.lang.String tabName) {
		_transactionModuleMaster.setTabName(tabName);
	}

	/**
	* Sets the transaction module master sid of this transaction module master.
	*
	* @param transactionModuleMasterSid the transaction module master sid of this transaction module master
	*/
	@Override
	public void setTransactionModuleMasterSid(int transactionModuleMasterSid) {
		_transactionModuleMaster.setTransactionModuleMasterSid(transactionModuleMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TransactionModuleMaster> toCacheModel() {
		return _transactionModuleMaster.toCacheModel();
	}

	@Override
	public TransactionModuleMaster toEscapedModel() {
		return new TransactionModuleMasterWrapper(_transactionModuleMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _transactionModuleMaster.toString();
	}

	@Override
	public TransactionModuleMaster toUnescapedModel() {
		return new TransactionModuleMasterWrapper(_transactionModuleMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _transactionModuleMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TransactionModuleMasterWrapper)) {
			return false;
		}

		TransactionModuleMasterWrapper transactionModuleMasterWrapper = (TransactionModuleMasterWrapper)obj;

		if (Objects.equals(_transactionModuleMaster,
					transactionModuleMasterWrapper._transactionModuleMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public TransactionModuleMaster getWrappedModel() {
		return _transactionModuleMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _transactionModuleMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _transactionModuleMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_transactionModuleMaster.resetOriginalValues();
	}

	private final TransactionModuleMaster _transactionModuleMaster;
}