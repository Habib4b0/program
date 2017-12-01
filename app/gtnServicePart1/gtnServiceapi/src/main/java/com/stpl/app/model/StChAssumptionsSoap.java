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

import com.stpl.app.service.persistence.StChAssumptionsPK;

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
public class StChAssumptionsSoap implements Serializable {
	public static StChAssumptionsSoap toSoapModel(StChAssumptions model) {
		StChAssumptionsSoap soapModel = new StChAssumptionsSoap();

		soapModel.setLastModifiedDate(model.getLastModifiedDate());
		soapModel.setParent(model.getParent());
		soapModel.setCommentary(model.getCommentary());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setUserId(model.getUserId());
		soapModel.setQuarter(model.getQuarter());
		soapModel.setTotalDiscountPercentChange(model.getTotalDiscountPercentChange());
		soapModel.setReasonCodes(model.getReasonCodes());
		soapModel.setYear(model.getYear());
		soapModel.setTotalDiscountPercentProjected(model.getTotalDiscountPercentProjected());
		soapModel.setTotalDiscountPercentPrior(model.getTotalDiscountPercentPrior());
		soapModel.setStChAssumptionsSid(model.getStChAssumptionsSid());
		soapModel.setChAssumptionsSid(model.getChAssumptionsSid());
		soapModel.setTotalDiscountChange(model.getTotalDiscountChange());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setTotalDiscountProjected(model.getTotalDiscountProjected());
		soapModel.setIsChecked(model.getIsChecked());
		soapModel.setCamId(model.getCamId());
		soapModel.setGrossTradeSales(model.getGrossTradeSales());
		soapModel.setTotalDiscountPrior(model.getTotalDiscountPrior());

		return soapModel;
	}

	public static StChAssumptionsSoap[] toSoapModels(StChAssumptions[] models) {
		StChAssumptionsSoap[] soapModels = new StChAssumptionsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StChAssumptionsSoap[][] toSoapModels(
		StChAssumptions[][] models) {
		StChAssumptionsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StChAssumptionsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StChAssumptionsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StChAssumptionsSoap[] toSoapModels(
		List<StChAssumptions> models) {
		List<StChAssumptionsSoap> soapModels = new ArrayList<StChAssumptionsSoap>(models.size());

		for (StChAssumptions model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StChAssumptionsSoap[soapModels.size()]);
	}

	public StChAssumptionsSoap() {
	}

	public StChAssumptionsPK getPrimaryKey() {
		return new StChAssumptionsPK(_projectionDetailsSid, _userId, _quarter,
			_year, _stChAssumptionsSid, _chAssumptionsSid, _sessionId);
	}

	public void setPrimaryKey(StChAssumptionsPK pk) {
		setProjectionDetailsSid(pk.projectionDetailsSid);
		setUserId(pk.userId);
		setQuarter(pk.quarter);
		setYear(pk.year);
		setStChAssumptionsSid(pk.stChAssumptionsSid);
		setChAssumptionsSid(pk.chAssumptionsSid);
		setSessionId(pk.sessionId);
	}

	public Date getLastModifiedDate() {
		return _lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		_lastModifiedDate = lastModifiedDate;
	}

	public boolean getParent() {
		return _parent;
	}

	public boolean isParent() {
		return _parent;
	}

	public void setParent(boolean parent) {
		_parent = parent;
	}

	public String getCommentary() {
		return _commentary;
	}

	public void setCommentary(String commentary) {
		_commentary = commentary;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	public int getQuarter() {
		return _quarter;
	}

	public void setQuarter(int quarter) {
		_quarter = quarter;
	}

	public double getTotalDiscountPercentChange() {
		return _totalDiscountPercentChange;
	}

	public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
		_totalDiscountPercentChange = totalDiscountPercentChange;
	}

	public String getReasonCodes() {
		return _reasonCodes;
	}

	public void setReasonCodes(String reasonCodes) {
		_reasonCodes = reasonCodes;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;
	}

	public double getTotalDiscountPercentProjected() {
		return _totalDiscountPercentProjected;
	}

	public void setTotalDiscountPercentProjected(
		double totalDiscountPercentProjected) {
		_totalDiscountPercentProjected = totalDiscountPercentProjected;
	}

	public double getTotalDiscountPercentPrior() {
		return _totalDiscountPercentPrior;
	}

	public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
		_totalDiscountPercentPrior = totalDiscountPercentPrior;
	}

	public String getStChAssumptionsSid() {
		return _stChAssumptionsSid;
	}

	public void setStChAssumptionsSid(String stChAssumptionsSid) {
		_stChAssumptionsSid = stChAssumptionsSid;
	}

	public int getChAssumptionsSid() {
		return _chAssumptionsSid;
	}

	public void setChAssumptionsSid(int chAssumptionsSid) {
		_chAssumptionsSid = chAssumptionsSid;
	}

	public double getTotalDiscountChange() {
		return _totalDiscountChange;
	}

	public void setTotalDiscountChange(double totalDiscountChange) {
		_totalDiscountChange = totalDiscountChange;
	}

	public int getSessionId() {
		return _sessionId;
	}

	public void setSessionId(int sessionId) {
		_sessionId = sessionId;
	}

	public double getTotalDiscountProjected() {
		return _totalDiscountProjected;
	}

	public void setTotalDiscountProjected(double totalDiscountProjected) {
		_totalDiscountProjected = totalDiscountProjected;
	}

	public boolean getIsChecked() {
		return _isChecked;
	}

	public boolean isIsChecked() {
		return _isChecked;
	}

	public void setIsChecked(boolean isChecked) {
		_isChecked = isChecked;
	}

	public int getCamId() {
		return _camId;
	}

	public void setCamId(int camId) {
		_camId = camId;
	}

	public double getGrossTradeSales() {
		return _grossTradeSales;
	}

	public void setGrossTradeSales(double grossTradeSales) {
		_grossTradeSales = grossTradeSales;
	}

	public double getTotalDiscountPrior() {
		return _totalDiscountPrior;
	}

	public void setTotalDiscountPrior(double totalDiscountPrior) {
		_totalDiscountPrior = totalDiscountPrior;
	}

	private Date _lastModifiedDate;
	private boolean _parent;
	private String _commentary;
	private int _projectionDetailsSid;
	private int _userId;
	private int _quarter;
	private double _totalDiscountPercentChange;
	private String _reasonCodes;
	private int _year;
	private double _totalDiscountPercentProjected;
	private double _totalDiscountPercentPrior;
	private String _stChAssumptionsSid;
	private int _chAssumptionsSid;
	private double _totalDiscountChange;
	private int _sessionId;
	private double _totalDiscountProjected;
	private boolean _isChecked;
	private int _camId;
	private double _grossTradeSales;
	private double _totalDiscountPrior;
}