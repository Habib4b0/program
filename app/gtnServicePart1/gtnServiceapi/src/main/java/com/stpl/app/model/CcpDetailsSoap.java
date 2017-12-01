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
public class CcpDetailsSoap implements Serializable {
	public static CcpDetailsSoap toSoapModel(CcpDetails model) {
		CcpDetailsSoap soapModel = new CcpDetailsSoap();

		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setContractMasterSid(model.getContractMasterSid());
		soapModel.setCcpDetailsSid(model.getCcpDetailsSid());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());

		return soapModel;
	}

	public static CcpDetailsSoap[] toSoapModels(CcpDetails[] models) {
		CcpDetailsSoap[] soapModels = new CcpDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CcpDetailsSoap[][] toSoapModels(CcpDetails[][] models) {
		CcpDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CcpDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CcpDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CcpDetailsSoap[] toSoapModels(List<CcpDetails> models) {
		List<CcpDetailsSoap> soapModels = new ArrayList<CcpDetailsSoap>(models.size());

		for (CcpDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CcpDetailsSoap[soapModels.size()]);
	}

	public CcpDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _ccpDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setCcpDetailsSid(pk);
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public int getContractMasterSid() {
		return _contractMasterSid;
	}

	public void setContractMasterSid(int contractMasterSid) {
		_contractMasterSid = contractMasterSid;
	}

	public int getCcpDetailsSid() {
		return _ccpDetailsSid;
	}

	public void setCcpDetailsSid(int ccpDetailsSid) {
		_ccpDetailsSid = ccpDetailsSid;
	}

	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
	}

	private int _itemMasterSid;
	private int _contractMasterSid;
	private int _ccpDetailsSid;
	private int _companyMasterSid;
}