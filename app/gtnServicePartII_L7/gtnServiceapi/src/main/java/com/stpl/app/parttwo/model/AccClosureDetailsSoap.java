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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class AccClosureDetailsSoap implements Serializable {
	public static AccClosureDetailsSoap toSoapModel(AccClosureDetails model) {
		AccClosureDetailsSoap soapModel = new AccClosureDetailsSoap();

		soapModel.setAccClosureDetailsSid(model.getAccClosureDetailsSid());
		soapModel.setCcpDetailsSid(model.getCcpDetailsSid());
		soapModel.setAccClosureMasterSid(model.getAccClosureMasterSid());
		soapModel.setRsModelSid(model.getRsModelSid());

		return soapModel;
	}

	public static AccClosureDetailsSoap[] toSoapModels(
		AccClosureDetails[] models) {
		AccClosureDetailsSoap[] soapModels = new AccClosureDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccClosureDetailsSoap[][] toSoapModels(
		AccClosureDetails[][] models) {
		AccClosureDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccClosureDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccClosureDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccClosureDetailsSoap[] toSoapModels(
		List<AccClosureDetails> models) {
		List<AccClosureDetailsSoap> soapModels = new ArrayList<AccClosureDetailsSoap>(models.size());

		for (AccClosureDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccClosureDetailsSoap[soapModels.size()]);
	}

	public AccClosureDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _accClosureDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setAccClosureDetailsSid(pk);
	}

	public int getAccClosureDetailsSid() {
		return _accClosureDetailsSid;
	}

	public void setAccClosureDetailsSid(int accClosureDetailsSid) {
		_accClosureDetailsSid = accClosureDetailsSid;
	}

	public int getCcpDetailsSid() {
		return _ccpDetailsSid;
	}

	public void setCcpDetailsSid(int ccpDetailsSid) {
		_ccpDetailsSid = ccpDetailsSid;
	}

	public int getAccClosureMasterSid() {
		return _accClosureMasterSid;
	}

	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_accClosureMasterSid = accClosureMasterSid;
	}

	public int getRsModelSid() {
		return _rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		_rsModelSid = rsModelSid;
	}

	private int _accClosureDetailsSid;
	private int _ccpDetailsSid;
	private int _accClosureMasterSid;
	private int _rsModelSid;
}