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
public class UdcsSoap implements Serializable {
	public static UdcsSoap toSoapModel(Udcs model) {
		UdcsSoap soapModel = new UdcsSoap();

		soapModel.setUdc1(model.getUdc1());
		soapModel.setUdc2(model.getUdc2());
		soapModel.setMasterType(model.getMasterType());
		soapModel.setUdc3(model.getUdc3());
		soapModel.setUdc12(model.getUdc12());
		soapModel.setUdc11(model.getUdc11());
		soapModel.setUdcsSid(model.getUdcsSid());
		soapModel.setMasterSid(model.getMasterSid());
		soapModel.setUdc10(model.getUdc10());
		soapModel.setUdc9(model.getUdc9());
		soapModel.setUdc8(model.getUdc8());
		soapModel.setUdc7(model.getUdc7());
		soapModel.setUdc6(model.getUdc6());
		soapModel.setUdc5(model.getUdc5());
		soapModel.setUdc4(model.getUdc4());

		return soapModel;
	}

	public static UdcsSoap[] toSoapModels(Udcs[] models) {
		UdcsSoap[] soapModels = new UdcsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UdcsSoap[][] toSoapModels(Udcs[][] models) {
		UdcsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UdcsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UdcsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UdcsSoap[] toSoapModels(List<Udcs> models) {
		List<UdcsSoap> soapModels = new ArrayList<UdcsSoap>(models.size());

		for (Udcs model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UdcsSoap[soapModels.size()]);
	}

	public UdcsSoap() {
	}

	public int getPrimaryKey() {
		return _udcsSid;
	}

	public void setPrimaryKey(int pk) {
		setUdcsSid(pk);
	}

	public int getUdc1() {
		return _udc1;
	}

	public void setUdc1(int udc1) {
		_udc1 = udc1;
	}

	public int getUdc2() {
		return _udc2;
	}

	public void setUdc2(int udc2) {
		_udc2 = udc2;
	}

	public String getMasterType() {
		return _masterType;
	}

	public void setMasterType(String masterType) {
		_masterType = masterType;
	}

	public int getUdc3() {
		return _udc3;
	}

	public void setUdc3(int udc3) {
		_udc3 = udc3;
	}

	public int getUdc12() {
		return _udc12;
	}

	public void setUdc12(int udc12) {
		_udc12 = udc12;
	}

	public int getUdc11() {
		return _udc11;
	}

	public void setUdc11(int udc11) {
		_udc11 = udc11;
	}

	public int getUdcsSid() {
		return _udcsSid;
	}

	public void setUdcsSid(int udcsSid) {
		_udcsSid = udcsSid;
	}

	public int getMasterSid() {
		return _masterSid;
	}

	public void setMasterSid(int masterSid) {
		_masterSid = masterSid;
	}

	public int getUdc10() {
		return _udc10;
	}

	public void setUdc10(int udc10) {
		_udc10 = udc10;
	}

	public int getUdc9() {
		return _udc9;
	}

	public void setUdc9(int udc9) {
		_udc9 = udc9;
	}

	public int getUdc8() {
		return _udc8;
	}

	public void setUdc8(int udc8) {
		_udc8 = udc8;
	}

	public int getUdc7() {
		return _udc7;
	}

	public void setUdc7(int udc7) {
		_udc7 = udc7;
	}

	public int getUdc6() {
		return _udc6;
	}

	public void setUdc6(int udc6) {
		_udc6 = udc6;
	}

	public int getUdc5() {
		return _udc5;
	}

	public void setUdc5(int udc5) {
		_udc5 = udc5;
	}

	public int getUdc4() {
		return _udc4;
	}

	public void setUdc4(int udc4) {
		_udc4 = udc4;
	}

	private int _udc1;
	private int _udc2;
	private String _masterType;
	private int _udc3;
	private int _udc12;
	private int _udc11;
	private int _udcsSid;
	private int _masterSid;
	private int _udc10;
	private int _udc9;
	private int _udc8;
	private int _udc7;
	private int _udc6;
	private int _udc5;
	private int _udc4;
}