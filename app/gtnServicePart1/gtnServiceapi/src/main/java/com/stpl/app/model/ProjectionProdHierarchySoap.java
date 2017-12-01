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
public class ProjectionProdHierarchySoap implements Serializable {
	public static ProjectionProdHierarchySoap toSoapModel(
		ProjectionProdHierarchy model) {
		ProjectionProdHierarchySoap soapModel = new ProjectionProdHierarchySoap();

		soapModel.setProjectionMasterSid(model.getProjectionMasterSid());
		soapModel.setProjectionProdHierarchySid(model.getProjectionProdHierarchySid());
		soapModel.setRelationshipLevelSid(model.getRelationshipLevelSid());

		return soapModel;
	}

	public static ProjectionProdHierarchySoap[] toSoapModels(
		ProjectionProdHierarchy[] models) {
		ProjectionProdHierarchySoap[] soapModels = new ProjectionProdHierarchySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProjectionProdHierarchySoap[][] toSoapModels(
		ProjectionProdHierarchy[][] models) {
		ProjectionProdHierarchySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProjectionProdHierarchySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProjectionProdHierarchySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProjectionProdHierarchySoap[] toSoapModels(
		List<ProjectionProdHierarchy> models) {
		List<ProjectionProdHierarchySoap> soapModels = new ArrayList<ProjectionProdHierarchySoap>(models.size());

		for (ProjectionProdHierarchy model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProjectionProdHierarchySoap[soapModels.size()]);
	}

	public ProjectionProdHierarchySoap() {
	}

	public int getPrimaryKey() {
		return _projectionProdHierarchySid;
	}

	public void setPrimaryKey(int pk) {
		setProjectionProdHierarchySid(pk);
	}

	public int getProjectionMasterSid() {
		return _projectionMasterSid;
	}

	public void setProjectionMasterSid(int projectionMasterSid) {
		_projectionMasterSid = projectionMasterSid;
	}

	public int getProjectionProdHierarchySid() {
		return _projectionProdHierarchySid;
	}

	public void setProjectionProdHierarchySid(int projectionProdHierarchySid) {
		_projectionProdHierarchySid = projectionProdHierarchySid;
	}

	public int getRelationshipLevelSid() {
		return _relationshipLevelSid;
	}

	public void setRelationshipLevelSid(int relationshipLevelSid) {
		_relationshipLevelSid = relationshipLevelSid;
	}

	private int _projectionMasterSid;
	private int _projectionProdHierarchySid;
	private int _relationshipLevelSid;
}