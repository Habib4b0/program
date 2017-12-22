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
public class CffApprovalDetailsSoap implements Serializable {
	public static CffApprovalDetailsSoap toSoapModel(CffApprovalDetails model) {
		CffApprovalDetailsSoap soapModel = new CffApprovalDetailsSoap();

		soapModel.setApprovalSequence(model.getApprovalSequence());
		soapModel.setApprovedDate(model.getApprovedDate());
		soapModel.setApprovedBy(model.getApprovedBy());
		soapModel.setApprovalStatus(model.getApprovalStatus());
		soapModel.setCffMasterSid(model.getCffMasterSid());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setCffApprovalDetailsSid(model.getCffApprovalDetailsSid());

		return soapModel;
	}

	public static CffApprovalDetailsSoap[] toSoapModels(
		CffApprovalDetails[] models) {
		CffApprovalDetailsSoap[] soapModels = new CffApprovalDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CffApprovalDetailsSoap[][] toSoapModels(
		CffApprovalDetails[][] models) {
		CffApprovalDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CffApprovalDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CffApprovalDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CffApprovalDetailsSoap[] toSoapModels(
		List<CffApprovalDetails> models) {
		List<CffApprovalDetailsSoap> soapModels = new ArrayList<CffApprovalDetailsSoap>(models.size());

		for (CffApprovalDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CffApprovalDetailsSoap[soapModels.size()]);
	}

	public CffApprovalDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _cffApprovalDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setCffApprovalDetailsSid(pk);
	}

	public int getApprovalSequence() {
		return _approvalSequence;
	}

	public void setApprovalSequence(int approvalSequence) {
		_approvalSequence = approvalSequence;
	}

	public Date getApprovedDate() {
		return _approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		_approvedDate = approvedDate;
	}

	public int getApprovedBy() {
		return _approvedBy;
	}

	public void setApprovedBy(int approvedBy) {
		_approvedBy = approvedBy;
	}

	public int getApprovalStatus() {
		return _approvalStatus;
	}

	public void setApprovalStatus(int approvalStatus) {
		_approvalStatus = approvalStatus;
	}

	public int getCffMasterSid() {
		return _cffMasterSid;
	}

	public void setCffMasterSid(int cffMasterSid) {
		_cffMasterSid = cffMasterSid;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public int getCffApprovalDetailsSid() {
		return _cffApprovalDetailsSid;
	}

	public void setCffApprovalDetailsSid(int cffApprovalDetailsSid) {
		_cffApprovalDetailsSid = cffApprovalDetailsSid;
	}

	private int _approvalSequence;
	private Date _approvedDate;
	private int _approvedBy;
	private int _approvalStatus;
	private int _cffMasterSid;
	private String _inboundStatus;
	private int _cffApprovalDetailsSid;
}