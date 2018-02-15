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

package com.stpl.app.parttwo.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class CffMasterSoap implements Serializable {
	public static CffMasterSoap toSoapModel(CffMaster model) {
		CffMasterSoap soapModel = new CffMasterSoap();

		soapModel.setProductHierarchyLevel(model.getProductHierarchyLevel());
		soapModel.setActiveFromDate(model.getActiveFromDate());
		soapModel.setCffType(model.getCffType());
		soapModel.setCffOfficial(model.getCffOfficial());
		soapModel.setCffMasterSid(model.getCffMasterSid());
		soapModel.setProductHierVersionNo(model.getProductHierVersionNo());
		soapModel.setActiveToDate(model.getActiveToDate());
		soapModel.setCustomerHierVersionNo(model.getCustomerHierVersionNo());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCustomerHierarchyLevel(model.getCustomerHierarchyLevel());
		soapModel.setProductHierarchySid(model.getProductHierarchySid());
		soapModel.setCffName(model.getCffName());
		soapModel.setCustomerHierarchyInnerLevel(model.getCustomerHierarchyInnerLevel());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCustomerHierarchySid(model.getCustomerHierarchySid());
		soapModel.setCompanyGroupSid(model.getCompanyGroupSid());
		soapModel.setProdRelationshipBuilderSid(model.getProdRelationshipBuilderSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setProductHierarchyInnerLevel(model.getProductHierarchyInnerLevel());
		soapModel.setItemGroupSid(model.getItemGroupSid());
		soapModel.setCustRelationshipBuilderSid(model.getCustRelationshipBuilderSid());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());

		return soapModel;
	}

	public static CffMasterSoap[] toSoapModels(CffMaster[] models) {
		CffMasterSoap[] soapModels = new CffMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CffMasterSoap[][] toSoapModels(CffMaster[][] models) {
		CffMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CffMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CffMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CffMasterSoap[] toSoapModels(List<CffMaster> models) {
		List<CffMasterSoap> soapModels = new ArrayList<CffMasterSoap>(models.size());

		for (CffMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CffMasterSoap[soapModels.size()]);
	}

	public CffMasterSoap() {
	}

	public int getPrimaryKey() {
		return _cffMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setCffMasterSid(pk);
	}

	public int getProductHierarchyLevel() {
		return _productHierarchyLevel;
	}

	public void setProductHierarchyLevel(int productHierarchyLevel) {
		_productHierarchyLevel = productHierarchyLevel;
	}

	public Date getActiveFromDate() {
		return _activeFromDate;
	}

	public void setActiveFromDate(Date activeFromDate) {
		_activeFromDate = activeFromDate;
	}

	public int getCffType() {
		return _cffType;
	}

	public void setCffType(int cffType) {
		_cffType = cffType;
	}

	public boolean getCffOfficial() {
		return _cffOfficial;
	}

	public boolean isCffOfficial() {
		return _cffOfficial;
	}

	public void setCffOfficial(boolean cffOfficial) {
		_cffOfficial = cffOfficial;
	}

	public int getCffMasterSid() {
		return _cffMasterSid;
	}

	public void setCffMasterSid(int cffMasterSid) {
		_cffMasterSid = cffMasterSid;
	}

	public int getProductHierVersionNo() {
		return _productHierVersionNo;
	}

	public void setProductHierVersionNo(int productHierVersionNo) {
		_productHierVersionNo = productHierVersionNo;
	}

	public Date getActiveToDate() {
		return _activeToDate;
	}

	public void setActiveToDate(Date activeToDate) {
		_activeToDate = activeToDate;
	}

	public int getCustomerHierVersionNo() {
		return _customerHierVersionNo;
	}

	public void setCustomerHierVersionNo(int customerHierVersionNo) {
		_customerHierVersionNo = customerHierVersionNo;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getCustomerHierarchyLevel() {
		return _customerHierarchyLevel;
	}

	public void setCustomerHierarchyLevel(int customerHierarchyLevel) {
		_customerHierarchyLevel = customerHierarchyLevel;
	}

	public int getProductHierarchySid() {
		return _productHierarchySid;
	}

	public void setProductHierarchySid(int productHierarchySid) {
		_productHierarchySid = productHierarchySid;
	}

	public String getCffName() {
		return _cffName;
	}

	public void setCffName(String cffName) {
		_cffName = cffName;
	}

	public int getCustomerHierarchyInnerLevel() {
		return _customerHierarchyInnerLevel;
	}

	public void setCustomerHierarchyInnerLevel(int customerHierarchyInnerLevel) {
		_customerHierarchyInnerLevel = customerHierarchyInnerLevel;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getCustomerHierarchySid() {
		return _customerHierarchySid;
	}

	public void setCustomerHierarchySid(int customerHierarchySid) {
		_customerHierarchySid = customerHierarchySid;
	}

	public int getCompanyGroupSid() {
		return _companyGroupSid;
	}

	public void setCompanyGroupSid(int companyGroupSid) {
		_companyGroupSid = companyGroupSid;
	}

	public int getProdRelationshipBuilderSid() {
		return _prodRelationshipBuilderSid;
	}

	public void setProdRelationshipBuilderSid(int prodRelationshipBuilderSid) {
		_prodRelationshipBuilderSid = prodRelationshipBuilderSid;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public int getProductHierarchyInnerLevel() {
		return _productHierarchyInnerLevel;
	}

	public void setProductHierarchyInnerLevel(int productHierarchyInnerLevel) {
		_productHierarchyInnerLevel = productHierarchyInnerLevel;
	}

	public int getItemGroupSid() {
		return _itemGroupSid;
	}

	public void setItemGroupSid(int itemGroupSid) {
		_itemGroupSid = itemGroupSid;
	}

	public int getCustRelationshipBuilderSid() {
		return _custRelationshipBuilderSid;
	}

	public void setCustRelationshipBuilderSid(int custRelationshipBuilderSid) {
		_custRelationshipBuilderSid = custRelationshipBuilderSid;
	}

	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
	}

	private int _productHierarchyLevel;
	private Date _activeFromDate;
	private int _cffType;
	private boolean _cffOfficial;
	private int _cffMasterSid;
	private int _productHierVersionNo;
	private Date _activeToDate;
	private int _customerHierVersionNo;
	private Date _modifiedDate;
	private int _customerHierarchyLevel;
	private int _productHierarchySid;
	private String _cffName;
	private int _customerHierarchyInnerLevel;
	private Date _createdDate;
	private int _createdBy;
	private int _customerHierarchySid;
	private int _companyGroupSid;
	private int _prodRelationshipBuilderSid;
	private int _modifiedBy;
	private String _inboundStatus;
	private int _productHierarchyInnerLevel;
	private int _itemGroupSid;
	private int _custRelationshipBuilderSid;
	private int _companyMasterSid;
}