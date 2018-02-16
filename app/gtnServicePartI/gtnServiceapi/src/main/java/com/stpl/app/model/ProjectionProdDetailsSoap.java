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
public class ProjectionProdDetailsSoap implements Serializable {
	public static ProjectionProdDetailsSoap toSoapModel(
		ProjectionProdDetails model) {
		ProjectionProdDetailsSoap soapModel = new ProjectionProdDetailsSoap();

		soapModel.setProductName(model.getProductName());
		soapModel.setCostCenter(model.getCostCenter());
		soapModel.setProductNo(model.getProductNo());
		soapModel.setSubLedgerCode(model.getSubLedgerCode());
		soapModel.setProductDetailsId(model.getProductDetailsId());
		soapModel.setBrandName(model.getBrandName());
		soapModel.setProjectionId(model.getProjectionId());

		return soapModel;
	}

	public static ProjectionProdDetailsSoap[] toSoapModels(
		ProjectionProdDetails[] models) {
		ProjectionProdDetailsSoap[] soapModels = new ProjectionProdDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProjectionProdDetailsSoap[][] toSoapModels(
		ProjectionProdDetails[][] models) {
		ProjectionProdDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProjectionProdDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProjectionProdDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProjectionProdDetailsSoap[] toSoapModels(
		List<ProjectionProdDetails> models) {
		List<ProjectionProdDetailsSoap> soapModels = new ArrayList<ProjectionProdDetailsSoap>(models.size());

		for (ProjectionProdDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProjectionProdDetailsSoap[soapModels.size()]);
	}

	public ProjectionProdDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _productDetailsId;
	}

	public void setPrimaryKey(int pk) {
		setProductDetailsId(pk);
	}

	public String getProductName() {
		return _productName;
	}

	public void setProductName(String productName) {
		_productName = productName;
	}

	public String getCostCenter() {
		return _costCenter;
	}

	public void setCostCenter(String costCenter) {
		_costCenter = costCenter;
	}

	public String getProductNo() {
		return _productNo;
	}

	public void setProductNo(String productNo) {
		_productNo = productNo;
	}

	public String getSubLedgerCode() {
		return _subLedgerCode;
	}

	public void setSubLedgerCode(String subLedgerCode) {
		_subLedgerCode = subLedgerCode;
	}

	public int getProductDetailsId() {
		return _productDetailsId;
	}

	public void setProductDetailsId(int productDetailsId) {
		_productDetailsId = productDetailsId;
	}

	public String getBrandName() {
		return _brandName;
	}

	public void setBrandName(String brandName) {
		_brandName = brandName;
	}

	public int getProjectionId() {
		return _projectionId;
	}

	public void setProjectionId(int projectionId) {
		_projectionId = projectionId;
	}

	private String _productName;
	private String _costCenter;
	private String _productNo;
	private String _subLedgerCode;
	private int _productDetailsId;
	private String _brandName;
	private int _projectionId;
}