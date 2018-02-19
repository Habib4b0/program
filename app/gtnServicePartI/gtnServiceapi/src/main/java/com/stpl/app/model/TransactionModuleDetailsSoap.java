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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class TransactionModuleDetailsSoap implements Serializable {
	public static TransactionModuleDetailsSoap toSoapModel(
		TransactionModuleDetails model) {
		TransactionModuleDetailsSoap soapModel = new TransactionModuleDetailsSoap();

		soapModel.setPropertyIndex(model.getPropertyIndex());
		soapModel.setDisplayName(model.getDisplayName());
		soapModel.setTransactionModuleMasterSid(model.getTransactionModuleMasterSid());
		soapModel.setCategoryName(model.getCategoryName());
		soapModel.setValidation(model.getValidation());
		soapModel.setPropertyName(model.getPropertyName());
		soapModel.setFlag(model.getFlag());
		soapModel.setTransactionModuleDetailsSid(model.getTransactionModuleDetailsSid());

		return soapModel;
	}

	public static TransactionModuleDetailsSoap[] toSoapModels(
		TransactionModuleDetails[] models) {
		TransactionModuleDetailsSoap[] soapModels = new TransactionModuleDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TransactionModuleDetailsSoap[][] toSoapModels(
		TransactionModuleDetails[][] models) {
		TransactionModuleDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TransactionModuleDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TransactionModuleDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TransactionModuleDetailsSoap[] toSoapModels(
		List<TransactionModuleDetails> models) {
		List<TransactionModuleDetailsSoap> soapModels = new ArrayList<TransactionModuleDetailsSoap>(models.size());

		for (TransactionModuleDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TransactionModuleDetailsSoap[soapModels.size()]);
	}

	public TransactionModuleDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _transactionModuleDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setTransactionModuleDetailsSid(pk);
	}

	public double getPropertyIndex() {
		return _propertyIndex;
	}

	public void setPropertyIndex(double propertyIndex) {
		_propertyIndex = propertyIndex;
	}

	public String getDisplayName() {
		return _displayName;
	}

	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	public int getTransactionModuleMasterSid() {
		return _transactionModuleMasterSid;
	}

	public void setTransactionModuleMasterSid(int transactionModuleMasterSid) {
		_transactionModuleMasterSid = transactionModuleMasterSid;
	}

	public String getCategoryName() {
		return _categoryName;
	}

	public void setCategoryName(String categoryName) {
		_categoryName = categoryName;
	}

	public String getValidation() {
		return _validation;
	}

	public void setValidation(String validation) {
		_validation = validation;
	}

	public String getPropertyName() {
		return _propertyName;
	}

	public void setPropertyName(String propertyName) {
		_propertyName = propertyName;
	}

	public String getFlag() {
		return _flag;
	}

	public void setFlag(String flag) {
		_flag = flag;
	}

	public int getTransactionModuleDetailsSid() {
		return _transactionModuleDetailsSid;
	}

	public void setTransactionModuleDetailsSid(int transactionModuleDetailsSid) {
		_transactionModuleDetailsSid = transactionModuleDetailsSid;
	}

	private double _propertyIndex;
	private String _displayName;
	private int _transactionModuleMasterSid;
	private String _categoryName;
	private String _validation;
	private String _propertyName;
	private String _flag;
	private int _transactionModuleDetailsSid;
}