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

package com.stpl.app.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.model.ForecastingFormula;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ForecastingFormula in entity cache.
 *
 * @author
 * @see ForecastingFormula
 * @generated
 */
@ProviderType
public class ForecastingFormulaCacheModel implements CacheModel<ForecastingFormula>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ForecastingFormulaCacheModel)) {
			return false;
		}

		ForecastingFormulaCacheModel forecastingFormulaCacheModel = (ForecastingFormulaCacheModel)obj;

		if (forecastingFormulaSid == forecastingFormulaCacheModel.forecastingFormulaSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, forecastingFormulaSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{createdDate=");
		sb.append(createdDate);
		sb.append(", formulaType=");
		sb.append(formulaType);
		sb.append(", forecastingFormulaSid=");
		sb.append(forecastingFormulaSid);
		sb.append(", formula=");
		sb.append(formula);
		sb.append(", formulaNo=");
		sb.append(formulaNo);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", isActive=");
		sb.append(isActive);
		sb.append(", formulaName=");
		sb.append(formulaName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ForecastingFormula toEntityModel() {
		ForecastingFormulaImpl forecastingFormulaImpl = new ForecastingFormulaImpl();

		if (createdDate == Long.MIN_VALUE) {
			forecastingFormulaImpl.setCreatedDate(null);
		}
		else {
			forecastingFormulaImpl.setCreatedDate(new Date(createdDate));
		}

		forecastingFormulaImpl.setFormulaType(formulaType);
		forecastingFormulaImpl.setForecastingFormulaSid(forecastingFormulaSid);

		if (formula == null) {
			forecastingFormulaImpl.setFormula(StringPool.BLANK);
		}
		else {
			forecastingFormulaImpl.setFormula(formula);
		}

		if (formulaNo == null) {
			forecastingFormulaImpl.setFormulaNo(StringPool.BLANK);
		}
		else {
			forecastingFormulaImpl.setFormulaNo(formulaNo);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			forecastingFormulaImpl.setModifiedDate(null);
		}
		else {
			forecastingFormulaImpl.setModifiedDate(new Date(modifiedDate));
		}

		forecastingFormulaImpl.setIsActive(isActive);

		if (formulaName == null) {
			forecastingFormulaImpl.setFormulaName(StringPool.BLANK);
		}
		else {
			forecastingFormulaImpl.setFormulaName(formulaName);
		}

		forecastingFormulaImpl.resetOriginalValues();

		return forecastingFormulaImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdDate = objectInput.readLong();

		formulaType = objectInput.readInt();

		forecastingFormulaSid = objectInput.readInt();
		formula = objectInput.readUTF();
		formulaNo = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		isActive = objectInput.readBoolean();
		formulaName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(formulaType);

		objectOutput.writeInt(forecastingFormulaSid);

		if (formula == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formula);
		}

		if (formulaNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formulaNo);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeBoolean(isActive);

		if (formulaName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formulaName);
		}
	}

	public long createdDate;
	public int formulaType;
	public int forecastingFormulaSid;
	public String formula;
	public String formulaNo;
	public long modifiedDate;
	public boolean isActive;
	public String formulaName;
}