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
public class ProjectionDetailsSoap implements Serializable {
	public static ProjectionDetailsSoap toSoapModel(ProjectionDetails model) {
		ProjectionDetailsSoap soapModel = new ProjectionDetailsSoap();

		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setCcpDetailsSid(model.getCcpDetailsSid());
		soapModel.setProjectionMasterSid(model.getProjectionMasterSid());

		return soapModel;
	}

	public static ProjectionDetailsSoap[] toSoapModels(
		ProjectionDetails[] models) {
		ProjectionDetailsSoap[] soapModels = new ProjectionDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProjectionDetailsSoap[][] toSoapModels(
		ProjectionDetails[][] models) {
		ProjectionDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProjectionDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProjectionDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProjectionDetailsSoap[] toSoapModels(
		List<ProjectionDetails> models) {
		List<ProjectionDetailsSoap> soapModels = new ArrayList<ProjectionDetailsSoap>(models.size());

		for (ProjectionDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProjectionDetailsSoap[soapModels.size()]);
	}

	public ProjectionDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _projectionDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setProjectionDetailsSid(pk);
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public int getCcpDetailsSid() {
		return _ccpDetailsSid;
	}

	public void setCcpDetailsSid(int ccpDetailsSid) {
		_ccpDetailsSid = ccpDetailsSid;
	}

	public int getProjectionMasterSid() {
		return _projectionMasterSid;
	}

	public void setProjectionMasterSid(int projectionMasterSid) {
		_projectionMasterSid = projectionMasterSid;
	}

	private int _projectionDetailsSid;
	private int _ccpDetailsSid;
	private int _projectionMasterSid;
}