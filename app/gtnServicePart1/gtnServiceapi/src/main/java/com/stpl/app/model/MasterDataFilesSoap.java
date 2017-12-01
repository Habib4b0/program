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
public class MasterDataFilesSoap implements Serializable {
	public static MasterDataFilesSoap toSoapModel(MasterDataFiles model) {
		MasterDataFilesSoap soapModel = new MasterDataFilesSoap();

		soapModel.setMasterTableSid(model.getMasterTableSid());
		soapModel.setMasterDataFilesSid(model.getMasterDataFilesSid());
		soapModel.setMasterTableName(model.getMasterTableName());
		soapModel.setFilePath(model.getFilePath());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());

		return soapModel;
	}

	public static MasterDataFilesSoap[] toSoapModels(MasterDataFiles[] models) {
		MasterDataFilesSoap[] soapModels = new MasterDataFilesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MasterDataFilesSoap[][] toSoapModels(
		MasterDataFiles[][] models) {
		MasterDataFilesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MasterDataFilesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MasterDataFilesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MasterDataFilesSoap[] toSoapModels(
		List<MasterDataFiles> models) {
		List<MasterDataFilesSoap> soapModels = new ArrayList<MasterDataFilesSoap>(models.size());

		for (MasterDataFiles model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MasterDataFilesSoap[soapModels.size()]);
	}

	public MasterDataFilesSoap() {
	}

	public int getPrimaryKey() {
		return _masterDataFilesSid;
	}

	public void setPrimaryKey(int pk) {
		setMasterDataFilesSid(pk);
	}

	public int getMasterTableSid() {
		return _masterTableSid;
	}

	public void setMasterTableSid(int masterTableSid) {
		_masterTableSid = masterTableSid;
	}

	public int getMasterDataFilesSid() {
		return _masterDataFilesSid;
	}

	public void setMasterDataFilesSid(int masterDataFilesSid) {
		_masterDataFilesSid = masterDataFilesSid;
	}

	public String getMasterTableName() {
		return _masterTableName;
	}

	public void setMasterTableName(String masterTableName) {
		_masterTableName = masterTableName;
	}

	public String getFilePath() {
		return _filePath;
	}

	public void setFilePath(String filePath) {
		_filePath = filePath;
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

	private int _masterTableSid;
	private int _masterDataFilesSid;
	private String _masterTableName;
	private String _filePath;
	private Date _createdDate;
	private int _createdBy;
}