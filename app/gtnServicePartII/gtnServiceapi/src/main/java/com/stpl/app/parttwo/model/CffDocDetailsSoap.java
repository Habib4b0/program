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
public class CffDocDetailsSoap implements Serializable {
	public static CffDocDetailsSoap toSoapModel(CffDocDetails model) {
		CffDocDetailsSoap soapModel = new CffDocDetailsSoap();

		soapModel.setFileName(model.getFileName());
		soapModel.setUploadDate(model.getUploadDate());
		soapModel.setFileType(model.getFileType());
		soapModel.setUploadBy(model.getUploadBy());
		soapModel.setCffMasterSid(model.getCffMasterSid());
		soapModel.setCffDocDetailsSid(model.getCffDocDetailsSid());
		soapModel.setFileSize(model.getFileSize());

		return soapModel;
	}

	public static CffDocDetailsSoap[] toSoapModels(CffDocDetails[] models) {
		CffDocDetailsSoap[] soapModels = new CffDocDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CffDocDetailsSoap[][] toSoapModels(CffDocDetails[][] models) {
		CffDocDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CffDocDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CffDocDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CffDocDetailsSoap[] toSoapModels(List<CffDocDetails> models) {
		List<CffDocDetailsSoap> soapModels = new ArrayList<CffDocDetailsSoap>(models.size());

		for (CffDocDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CffDocDetailsSoap[soapModels.size()]);
	}

	public CffDocDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _cffDocDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setCffDocDetailsSid(pk);
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public Date getUploadDate() {
		return _uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		_uploadDate = uploadDate;
	}

	public String getFileType() {
		return _fileType;
	}

	public void setFileType(String fileType) {
		_fileType = fileType;
	}

	public int getUploadBy() {
		return _uploadBy;
	}

	public void setUploadBy(int uploadBy) {
		_uploadBy = uploadBy;
	}

	public int getCffMasterSid() {
		return _cffMasterSid;
	}

	public void setCffMasterSid(int cffMasterSid) {
		_cffMasterSid = cffMasterSid;
	}

	public int getCffDocDetailsSid() {
		return _cffDocDetailsSid;
	}

	public void setCffDocDetailsSid(int cffDocDetailsSid) {
		_cffDocDetailsSid = cffDocDetailsSid;
	}

	public String getFileSize() {
		return _fileSize;
	}

	public void setFileSize(String fileSize) {
		_fileSize = fileSize;
	}

	private String _fileName;
	private Date _uploadDate;
	private String _fileType;
	private int _uploadBy;
	private int _cffMasterSid;
	private int _cffDocDetailsSid;
	private String _fileSize;
}