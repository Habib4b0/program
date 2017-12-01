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
public class CdrDetailsSoap implements Serializable {
	public static CdrDetailsSoap toSoapModel(CdrDetails model) {
		CdrDetailsSoap soapModel = new CdrDetailsSoap();

		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setLineType(model.getLineType());
		soapModel.setKeyword(model.getKeyword());
		soapModel.setItemGroupMsAssociation(model.getItemGroupMsAssociation());
		soapModel.setValue(model.getValue());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLogicalOperator(model.getLogicalOperator());
		soapModel.setOperator(model.getOperator());
		soapModel.setCdrDetailsSid(model.getCdrDetailsSid());
		soapModel.setCdrModelSid(model.getCdrModelSid());
		soapModel.setComparison(model.getComparison());

		return soapModel;
	}

	public static CdrDetailsSoap[] toSoapModels(CdrDetails[] models) {
		CdrDetailsSoap[] soapModels = new CdrDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CdrDetailsSoap[][] toSoapModels(CdrDetails[][] models) {
		CdrDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CdrDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CdrDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CdrDetailsSoap[] toSoapModels(List<CdrDetails> models) {
		List<CdrDetailsSoap> soapModels = new ArrayList<CdrDetailsSoap>(models.size());

		for (CdrDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CdrDetailsSoap[soapModels.size()]);
	}

	public CdrDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _cdrDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setCdrDetailsSid(pk);
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getLineType() {
		return _lineType;
	}

	public void setLineType(String lineType) {
		_lineType = lineType;
	}

	public String getKeyword() {
		return _keyword;
	}

	public void setKeyword(String keyword) {
		_keyword = keyword;
	}

	public String getItemGroupMsAssociation() {
		return _itemGroupMsAssociation;
	}

	public void setItemGroupMsAssociation(String itemGroupMsAssociation) {
		_itemGroupMsAssociation = itemGroupMsAssociation;
	}

	public double getValue() {
		return _value;
	}

	public void setValue(double value) {
		_value = value;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getLogicalOperator() {
		return _logicalOperator;
	}

	public void setLogicalOperator(String logicalOperator) {
		_logicalOperator = logicalOperator;
	}

	public String getOperator() {
		return _operator;
	}

	public void setOperator(String operator) {
		_operator = operator;
	}

	public int getCdrDetailsSid() {
		return _cdrDetailsSid;
	}

	public void setCdrDetailsSid(int cdrDetailsSid) {
		_cdrDetailsSid = cdrDetailsSid;
	}

	public int getCdrModelSid() {
		return _cdrModelSid;
	}

	public void setCdrModelSid(int cdrModelSid) {
		_cdrModelSid = cdrModelSid;
	}

	public String getComparison() {
		return _comparison;
	}

	public void setComparison(String comparison) {
		_comparison = comparison;
	}

	private int _createdBy;
	private int _modifiedBy;
	private Date _createdDate;
	private String _lineType;
	private String _keyword;
	private String _itemGroupMsAssociation;
	private double _value;
	private Date _modifiedDate;
	private String _logicalOperator;
	private String _operator;
	private int _cdrDetailsSid;
	private int _cdrModelSid;
	private String _comparison;
}