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
public class ProjectionCustDetailsSoap implements Serializable {
	public static ProjectionCustDetailsSoap toSoapModel(
		ProjectionCustDetails model) {
		ProjectionCustDetailsSoap soapModel = new ProjectionCustDetailsSoap();

		soapModel.setContractName(model.getContractName());
		soapModel.setCustomerName(model.getCustomerName());
		soapModel.setCustomerDetailsId(model.getCustomerDetailsId());
		soapModel.setCostCenter(model.getCostCenter());
		soapModel.setCustomerAlias(model.getCustomerAlias());
		soapModel.setSubLedgerCode(model.getSubLedgerCode());
		soapModel.setProjectionId(model.getProjectionId());
		soapModel.setMarketType(model.getMarketType());
		soapModel.setContractNo(model.getContractNo());

		return soapModel;
	}

	public static ProjectionCustDetailsSoap[] toSoapModels(
		ProjectionCustDetails[] models) {
		ProjectionCustDetailsSoap[] soapModels = new ProjectionCustDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProjectionCustDetailsSoap[][] toSoapModels(
		ProjectionCustDetails[][] models) {
		ProjectionCustDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProjectionCustDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProjectionCustDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProjectionCustDetailsSoap[] toSoapModels(
		List<ProjectionCustDetails> models) {
		List<ProjectionCustDetailsSoap> soapModels = new ArrayList<ProjectionCustDetailsSoap>(models.size());

		for (ProjectionCustDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProjectionCustDetailsSoap[soapModels.size()]);
	}

	public ProjectionCustDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _customerDetailsId;
	}

	public void setPrimaryKey(int pk) {
		setCustomerDetailsId(pk);
	}

	public String getContractName() {
		return _contractName;
	}

	public void setContractName(String contractName) {
		_contractName = contractName;
	}

	public String getCustomerName() {
		return _customerName;
	}

	public void setCustomerName(String customerName) {
		_customerName = customerName;
	}

	public int getCustomerDetailsId() {
		return _customerDetailsId;
	}

	public void setCustomerDetailsId(int customerDetailsId) {
		_customerDetailsId = customerDetailsId;
	}

	public String getCostCenter() {
		return _costCenter;
	}

	public void setCostCenter(String costCenter) {
		_costCenter = costCenter;
	}

	public String getCustomerAlias() {
		return _customerAlias;
	}

	public void setCustomerAlias(String customerAlias) {
		_customerAlias = customerAlias;
	}

	public String getSubLedgerCode() {
		return _subLedgerCode;
	}

	public void setSubLedgerCode(String subLedgerCode) {
		_subLedgerCode = subLedgerCode;
	}

	public int getProjectionId() {
		return _projectionId;
	}

	public void setProjectionId(int projectionId) {
		_projectionId = projectionId;
	}

	public String getMarketType() {
		return _marketType;
	}

	public void setMarketType(String marketType) {
		_marketType = marketType;
	}

	public String getContractNo() {
		return _contractNo;
	}

	public void setContractNo(String contractNo) {
		_contractNo = contractNo;
	}

	private String _contractName;
	private String _customerName;
	private int _customerDetailsId;
	private String _costCenter;
	private String _customerAlias;
	private String _subLedgerCode;
	private int _projectionId;
	private String _marketType;
	private String _contractNo;
}