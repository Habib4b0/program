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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class DeductionGroupDetailsSoap implements Serializable {
	public static DeductionGroupDetailsSoap toSoapModel(
		DeductionGroupDetails model) {
		DeductionGroupDetailsSoap soapModel = new DeductionGroupDetailsSoap();

		soapModel.setDeductionGroupDetailsSid(model.getDeductionGroupDetailsSid());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setDeductionGroupSid(model.getDeductionGroupSid());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setRsModelSid(model.getRsModelSid());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static DeductionGroupDetailsSoap[] toSoapModels(
		DeductionGroupDetails[] models) {
		DeductionGroupDetailsSoap[] soapModels = new DeductionGroupDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DeductionGroupDetailsSoap[][] toSoapModels(
		DeductionGroupDetails[][] models) {
		DeductionGroupDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DeductionGroupDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DeductionGroupDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DeductionGroupDetailsSoap[] toSoapModels(
		List<DeductionGroupDetails> models) {
		List<DeductionGroupDetailsSoap> soapModels = new ArrayList<DeductionGroupDetailsSoap>(models.size());

		for (DeductionGroupDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DeductionGroupDetailsSoap[soapModels.size()]);
	}

	public DeductionGroupDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _deductionGroupDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setDeductionGroupDetailsSid(pk);
	}

	public int getDeductionGroupDetailsSid() {
		return _deductionGroupDetailsSid;
	}

	public void setDeductionGroupDetailsSid(int deductionGroupDetailsSid) {
		_deductionGroupDetailsSid = deductionGroupDetailsSid;
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

	public int getDeductionGroupSid() {
		return _deductionGroupSid;
	}

	public void setDeductionGroupSid(int deductionGroupSid) {
		_deductionGroupSid = deductionGroupSid;
	}

	public int getVersionNo() {
		return _versionNo;
	}

	public void setVersionNo(int versionNo) {
		_versionNo = versionNo;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public int getRsModelSid() {
		return _rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		_rsModelSid = rsModelSid;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private int _deductionGroupDetailsSid;
	private Date _createdDate;
	private int _createdBy;
	private int _deductionGroupSid;
	private int _versionNo;
	private int _modifiedBy;
	private int _rsModelSid;
	private Date _modifiedDate;
}