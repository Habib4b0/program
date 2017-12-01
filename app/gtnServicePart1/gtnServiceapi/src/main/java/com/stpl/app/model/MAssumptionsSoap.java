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
public class MAssumptionsSoap implements Serializable {
	public static MAssumptionsSoap toSoapModel(MAssumptions model) {
		MAssumptionsSoap soapModel = new MAssumptionsSoap();

		soapModel.setGrossSalesPercentChange(model.getGrossSalesPercentChange());
		soapModel.setGrossSalesPrior(model.getGrossSalesPrior());
		soapModel.setProjYear(model.getProjYear());
		soapModel.setTotalDiscountPercentProjected(model.getTotalDiscountPercentProjected());
		soapModel.setCamId(model.getCamId());
		soapModel.setCommentary(model.getCommentary());
		soapModel.setGrossSalesProjected(model.getGrossSalesProjected());
		soapModel.setTotalDiscountPercentChange(model.getTotalDiscountPercentChange());
		soapModel.setTotalDiscountPercentPrior(model.getTotalDiscountPercentPrior());
		soapModel.setNetSalesPercentChange(model.getNetSalesPercentChange());
		soapModel.setParent(model.getParent());
		soapModel.setProjectionPeriod(model.getProjectionPeriod());
		soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
		soapModel.setNetSalesPrior(model.getNetSalesPrior());
		soapModel.setNetSalesProjected(model.getNetSalesProjected());
		soapModel.setReasonCodes(model.getReasonCodes());
		soapModel.setMAssumptionsSid(model.getMAssumptionsSid());

		return soapModel;
	}

	public static MAssumptionsSoap[] toSoapModels(MAssumptions[] models) {
		MAssumptionsSoap[] soapModels = new MAssumptionsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MAssumptionsSoap[][] toSoapModels(MAssumptions[][] models) {
		MAssumptionsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MAssumptionsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MAssumptionsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MAssumptionsSoap[] toSoapModels(List<MAssumptions> models) {
		List<MAssumptionsSoap> soapModels = new ArrayList<MAssumptionsSoap>(models.size());

		for (MAssumptions model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MAssumptionsSoap[soapModels.size()]);
	}

	public MAssumptionsSoap() {
	}

	public int getPrimaryKey() {
		return _mAssumptionsSid;
	}

	public void setPrimaryKey(int pk) {
		setMAssumptionsSid(pk);
	}

	public double getGrossSalesPercentChange() {
		return _grossSalesPercentChange;
	}

	public void setGrossSalesPercentChange(double grossSalesPercentChange) {
		_grossSalesPercentChange = grossSalesPercentChange;
	}

	public double getGrossSalesPrior() {
		return _grossSalesPrior;
	}

	public void setGrossSalesPrior(double grossSalesPrior) {
		_grossSalesPrior = grossSalesPrior;
	}

	public int getProjYear() {
		return _projYear;
	}

	public void setProjYear(int projYear) {
		_projYear = projYear;
	}

	public double getTotalDiscountPercentProjected() {
		return _totalDiscountPercentProjected;
	}

	public void setTotalDiscountPercentProjected(
		double totalDiscountPercentProjected) {
		_totalDiscountPercentProjected = totalDiscountPercentProjected;
	}

	public int getCamId() {
		return _camId;
	}

	public void setCamId(int camId) {
		_camId = camId;
	}

	public String getCommentary() {
		return _commentary;
	}

	public void setCommentary(String commentary) {
		_commentary = commentary;
	}

	public double getGrossSalesProjected() {
		return _grossSalesProjected;
	}

	public void setGrossSalesProjected(double grossSalesProjected) {
		_grossSalesProjected = grossSalesProjected;
	}

	public double getTotalDiscountPercentChange() {
		return _totalDiscountPercentChange;
	}

	public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
		_totalDiscountPercentChange = totalDiscountPercentChange;
	}

	public double getTotalDiscountPercentPrior() {
		return _totalDiscountPercentPrior;
	}

	public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
		_totalDiscountPercentPrior = totalDiscountPercentPrior;
	}

	public double getNetSalesPercentChange() {
		return _netSalesPercentChange;
	}

	public void setNetSalesPercentChange(double netSalesPercentChange) {
		_netSalesPercentChange = netSalesPercentChange;
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

	public double getNetSalesProjected() {
		return _netSalesProjected;
	}

	public void setNetSalesProjected(double netSalesProjected) {
		_netSalesProjected = netSalesProjected;
	}

	public String getReasonCodes() {
		return _reasonCodes;
	}

	public void setReasonCodes(String reasonCodes) {
		_reasonCodes = reasonCodes;
	}

	public int getMAssumptionsSid() {
		return _mAssumptionsSid;
	}

	public void setMAssumptionsSid(int mAssumptionsSid) {
		_mAssumptionsSid = mAssumptionsSid;
	}

	private double _grossSalesPercentChange;
	private double _grossSalesPrior;
	private int _projYear;
	private double _totalDiscountPercentProjected;
	private int _camId;
	private String _commentary;
	private double _grossSalesProjected;
	private double _totalDiscountPercentChange;
	private double _totalDiscountPercentPrior;
	private double _netSalesPercentChange;
	private boolean _parent;
	private int _projectionPeriod;
	private int _projectionDetailsSid;
	private double _netSalesPrior;
	private double _netSalesProjected;
	private String _reasonCodes;
	private int _mAssumptionsSid;
}