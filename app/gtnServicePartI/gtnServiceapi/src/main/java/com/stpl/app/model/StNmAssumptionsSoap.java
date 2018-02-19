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

import com.stpl.app.service.persistence.StNmAssumptionsPK;

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
public class StNmAssumptionsSoap implements Serializable {
	public static StNmAssumptionsSoap toSoapModel(StNmAssumptions model) {
		StNmAssumptionsSoap soapModel = new StNmAssumptionsSoap();

		soapModel.setLastModifiedDate(model.getLastModifiedDate());
		soapModel.setParent(model.getParent());
		soapModel.setProjectionPeriod(model.getProjectionPeriod());
		soapModel.setCommentary(model.getCommentary());
		soapModel.setNmAssumptionsSid(model.getNmAssumptionsSid());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setNetSalesPrior(model.getNetSalesPrior());
		soapModel.setUserId(model.getUserId());
		soapModel.setGrossSalesPercentChange(model.getGrossSalesPercentChange());
		soapModel.setTotalDiscountPercentChange(model.getTotalDiscountPercentChange());
		soapModel.setReasonCodes(model.getReasonCodes());
		soapModel.setTotalDiscountPercentProjected(model.getTotalDiscountPercentProjected());
		soapModel.setTotalDiscountPercentPrior(model.getTotalDiscountPercentPrior());
		soapModel.setNetSalesProjected(model.getNetSalesProjected());
		soapModel.setStNmAssumptionsSid(model.getStNmAssumptionsSid());
		soapModel.setGrossSalesProjected(model.getGrossSalesProjected());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setGrossSalesPrior(model.getGrossSalesPrior());
		soapModel.setIsChecked(model.getIsChecked());
		soapModel.setCamId(model.getCamId());
		soapModel.setNetSalesPercentChange(model.getNetSalesPercentChange());
		soapModel.setSegment(model.getSegment());

		return soapModel;
	}

	public static StNmAssumptionsSoap[] toSoapModels(StNmAssumptions[] models) {
		StNmAssumptionsSoap[] soapModels = new StNmAssumptionsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StNmAssumptionsSoap[][] toSoapModels(
		StNmAssumptions[][] models) {
		StNmAssumptionsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StNmAssumptionsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StNmAssumptionsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StNmAssumptionsSoap[] toSoapModels(
		List<StNmAssumptions> models) {
		List<StNmAssumptionsSoap> soapModels = new ArrayList<StNmAssumptionsSoap>(models.size());

		for (StNmAssumptions model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StNmAssumptionsSoap[soapModels.size()]);
	}

	public StNmAssumptionsSoap() {
	}

	public StNmAssumptionsPK getPrimaryKey() {
		return new StNmAssumptionsPK(_projectionPeriod, _nmAssumptionsSid,
			_projectionDetailsSid, _userId, _stNmAssumptionsSid, _sessionId);
	}

	public void setPrimaryKey(StNmAssumptionsPK pk) {
		setProjectionPeriod(pk.projectionPeriod);
		setNmAssumptionsSid(pk.nmAssumptionsSid);
		setProjectionDetailsSid(pk.projectionDetailsSid);
		setUserId(pk.userId);
		setStNmAssumptionsSid(pk.stNmAssumptionsSid);
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

	public int getProjectionPeriod() {
		return _projectionPeriod;
	}

	public void setProjectionPeriod(int projectionPeriod) {
		_projectionPeriod = projectionPeriod;
	}

	public String getCommentary() {
		return _commentary;
	}

	public void setCommentary(String commentary) {
		_commentary = commentary;
	}

	public int getNmAssumptionsSid() {
		return _nmAssumptionsSid;
	}

	public void setNmAssumptionsSid(int nmAssumptionsSid) {
		_nmAssumptionsSid = nmAssumptionsSid;
	}

	public int getProjectionDetailsSid() {
		return _projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetailsSid = projectionDetailsSid;
	}

	public double getNetSalesPrior() {
		return _netSalesPrior;
	}

	public void setNetSalesPrior(double netSalesPrior) {
		_netSalesPrior = netSalesPrior;
	}

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	public double getGrossSalesPercentChange() {
		return _grossSalesPercentChange;
	}

	public void setGrossSalesPercentChange(double grossSalesPercentChange) {
		_grossSalesPercentChange = grossSalesPercentChange;
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

	public double getNetSalesProjected() {
		return _netSalesProjected;
	}

	public void setNetSalesProjected(double netSalesProjected) {
		_netSalesProjected = netSalesProjected;
	}

	public String getStNmAssumptionsSid() {
		return _stNmAssumptionsSid;
	}

	public void setStNmAssumptionsSid(String stNmAssumptionsSid) {
		_stNmAssumptionsSid = stNmAssumptionsSid;
	}

	public double getGrossSalesProjected() {
		return _grossSalesProjected;
	}

	public void setGrossSalesProjected(double grossSalesProjected) {
		_grossSalesProjected = grossSalesProjected;
	}

	public int getSessionId() {
		return _sessionId;
	}

	public void setSessionId(int sessionId) {
		_sessionId = sessionId;
	}

	public double getGrossSalesPrior() {
		return _grossSalesPrior;
	}

	public void setGrossSalesPrior(double grossSalesPrior) {
		_grossSalesPrior = grossSalesPrior;
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

	public double getNetSalesPercentChange() {
		return _netSalesPercentChange;
	}

	public void setNetSalesPercentChange(double netSalesPercentChange) {
		_netSalesPercentChange = netSalesPercentChange;
	}

	public String getSegment() {
		return _segment;
	}

	public void setSegment(String segment) {
		_segment = segment;
	}

	private Date _lastModifiedDate;
	private boolean _parent;
	private int _projectionPeriod;
	private String _commentary;
	private int _nmAssumptionsSid;
	private int _projectionDetailsSid;
	private double _netSalesPrior;
	private int _userId;
	private double _grossSalesPercentChange;
	private double _totalDiscountPercentChange;
	private String _reasonCodes;
	private double _totalDiscountPercentProjected;
	private double _totalDiscountPercentPrior;
	private double _netSalesProjected;
	private String _stNmAssumptionsSid;
	private double _grossSalesProjected;
	private int _sessionId;
	private double _grossSalesPrior;
	private boolean _isChecked;
	private int _camId;
	private double _netSalesPercentChange;
	private String _segment;
}