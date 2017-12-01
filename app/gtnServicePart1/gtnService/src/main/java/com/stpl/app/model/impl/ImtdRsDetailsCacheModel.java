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

import com.stpl.app.model.ImtdRsDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImtdRsDetails in entity cache.
 *
 * @author
 * @see ImtdRsDetails
 * @generated
 */
@ProviderType
public class ImtdRsDetailsCacheModel implements CacheModel<ImtdRsDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdRsDetailsCacheModel)) {
			return false;
		}

		ImtdRsDetailsCacheModel imtdRsDetailsCacheModel = (ImtdRsDetailsCacheModel)obj;

		if (imtdRsDetailsSid == imtdRsDetailsCacheModel.imtdRsDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, imtdRsDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(113);

		sb.append("{rsDetailsModifiedDate=");
		sb.append(rsDetailsModifiedDate);
		sb.append(", rsDetailsBundleNo=");
		sb.append(rsDetailsBundleNo);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", imtdRsDetailsSid=");
		sb.append(imtdRsDetailsSid);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", rsDetailsFormulaMethodId=");
		sb.append(rsDetailsFormulaMethodId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", usersSid=");
		sb.append(usersSid);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", rsDetailsFormulaId=");
		sb.append(rsDetailsFormulaId);
		sb.append(", imtdCreatedDate=");
		sb.append(imtdCreatedDate);
		sb.append(", psModelSid=");
		sb.append(psModelSid);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", rsDetailsCreatedDate=");
		sb.append(rsDetailsCreatedDate);
		sb.append(", itemNo=");
		sb.append(itemNo);
		sb.append(", rsDetailsFormulaName=");
		sb.append(rsDetailsFormulaName);
		sb.append(", udc6=");
		sb.append(udc6);
		sb.append(", rsDetailsCreatedBy=");
		sb.append(rsDetailsCreatedBy);
		sb.append(", udc5=");
		sb.append(udc5);
		sb.append(", ifpModelSid=");
		sb.append(ifpModelSid);
		sb.append(", udc4=");
		sb.append(udc4);
		sb.append(", rsDetailsFormulaNo=");
		sb.append(rsDetailsFormulaNo);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", rsId=");
		sb.append(rsId);
		sb.append(", udc1=");
		sb.append(udc1);
		sb.append(", rsDetailsRebateAmount=");
		sb.append(rsDetailsRebateAmount);
		sb.append(", udc2=");
		sb.append(udc2);
		sb.append(", rsDetailsModifiedBy=");
		sb.append(rsDetailsModifiedBy);
		sb.append(", udc3=");
		sb.append(udc3);
		sb.append(", rebatePlanMasterSid=");
		sb.append(rebatePlanMasterSid);
		sb.append(", rsDetailsAttachedDate=");
		sb.append(rsDetailsAttachedDate);
		sb.append(", itemRebateEndDate=");
		sb.append(itemRebateEndDate);
		sb.append(", rsDetailsRebatePlanName=");
		sb.append(rsDetailsRebatePlanName);
		sb.append(", itemRebateStartDate=");
		sb.append(itemRebateStartDate);
		sb.append(", rsDetailsFormulaType=");
		sb.append(rsDetailsFormulaType);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", operation=");
		sb.append(operation);
		sb.append(", cfpModelSid=");
		sb.append(cfpModelSid);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", rsDetailsSid=");
		sb.append(rsDetailsSid);
		sb.append(", rsDetailsAttachedStatus=");
		sb.append(rsDetailsAttachedStatus);
		sb.append(", rsDetailsNetSalesFormulaNo=");
		sb.append(rsDetailsNetSalesFormulaNo);
		sb.append(", rsDetailsNetSalesFormulaName=");
		sb.append(rsDetailsNetSalesFormulaName);
		sb.append(", rsDetailsDeductionCalendarNo=");
		sb.append(rsDetailsDeductionCalendarNo);
		sb.append(", rsDetailsDeductionCalendarName=");
		sb.append(rsDetailsDeductionCalendarName);
		sb.append(", deductionCalendarMasterSid=");
		sb.append(deductionCalendarMasterSid);
		sb.append(", netSalesFormulaMasterSid=");
		sb.append(netSalesFormulaMasterSid);
		sb.append(", evaluationRule=");
		sb.append(evaluationRule);
		sb.append(", netSalesRule=");
		sb.append(netSalesRule);
		sb.append(", formulaType=");
		sb.append(formulaType);
		sb.append(", calculationRule=");
		sb.append(calculationRule);
		sb.append(", calculationRuleBundle=");
		sb.append(calculationRuleBundle);
		sb.append(", evaluationRuleBundle=");
		sb.append(evaluationRuleBundle);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ImtdRsDetails toEntityModel() {
		ImtdRsDetailsImpl imtdRsDetailsImpl = new ImtdRsDetailsImpl();

		if (rsDetailsModifiedDate == Long.MIN_VALUE) {
			imtdRsDetailsImpl.setRsDetailsModifiedDate(null);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsModifiedDate(new Date(
					rsDetailsModifiedDate));
		}

		if (rsDetailsBundleNo == null) {
			imtdRsDetailsImpl.setRsDetailsBundleNo(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsBundleNo(rsDetailsBundleNo);
		}

		imtdRsDetailsImpl.setItemMasterSid(itemMasterSid);
		imtdRsDetailsImpl.setImtdRsDetailsSid(imtdRsDetailsSid);

		if (itemId == null) {
			imtdRsDetailsImpl.setItemId(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setItemId(itemId);
		}

		if (rsDetailsFormulaMethodId == null) {
			imtdRsDetailsImpl.setRsDetailsFormulaMethodId(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsFormulaMethodId(rsDetailsFormulaMethodId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			imtdRsDetailsImpl.setModifiedDate(null);
		}
		else {
			imtdRsDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (createdDate == Long.MIN_VALUE) {
			imtdRsDetailsImpl.setCreatedDate(null);
		}
		else {
			imtdRsDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		imtdRsDetailsImpl.setCreatedBy(createdBy);

		if (usersSid == null) {
			imtdRsDetailsImpl.setUsersSid(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setUsersSid(usersSid);
		}

		imtdRsDetailsImpl.setContractMasterSid(contractMasterSid);

		if (rsDetailsFormulaId == null) {
			imtdRsDetailsImpl.setRsDetailsFormulaId(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsFormulaId(rsDetailsFormulaId);
		}

		if (imtdCreatedDate == Long.MIN_VALUE) {
			imtdRsDetailsImpl.setImtdCreatedDate(null);
		}
		else {
			imtdRsDetailsImpl.setImtdCreatedDate(new Date(imtdCreatedDate));
		}

		imtdRsDetailsImpl.setPsModelSid(psModelSid);
		imtdRsDetailsImpl.setModifiedBy(modifiedBy);

		if (rsDetailsCreatedDate == Long.MIN_VALUE) {
			imtdRsDetailsImpl.setRsDetailsCreatedDate(null);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsCreatedDate(new Date(
					rsDetailsCreatedDate));
		}

		if (itemNo == null) {
			imtdRsDetailsImpl.setItemNo(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setItemNo(itemNo);
		}

		if (rsDetailsFormulaName == null) {
			imtdRsDetailsImpl.setRsDetailsFormulaName(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsFormulaName(rsDetailsFormulaName);
		}

		if (udc6 == null) {
			imtdRsDetailsImpl.setUdc6(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setUdc6(udc6);
		}

		if (rsDetailsCreatedBy == null) {
			imtdRsDetailsImpl.setRsDetailsCreatedBy(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsCreatedBy(rsDetailsCreatedBy);
		}

		if (udc5 == null) {
			imtdRsDetailsImpl.setUdc5(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setUdc5(udc5);
		}

		imtdRsDetailsImpl.setIfpModelSid(ifpModelSid);

		if (udc4 == null) {
			imtdRsDetailsImpl.setUdc4(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setUdc4(udc4);
		}

		if (rsDetailsFormulaNo == null) {
			imtdRsDetailsImpl.setRsDetailsFormulaNo(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsFormulaNo(rsDetailsFormulaNo);
		}

		imtdRsDetailsImpl.setCheckRecord(checkRecord);

		if (rsId == null) {
			imtdRsDetailsImpl.setRsId(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsId(rsId);
		}

		if (udc1 == null) {
			imtdRsDetailsImpl.setUdc1(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setUdc1(udc1);
		}

		imtdRsDetailsImpl.setRsDetailsRebateAmount(rsDetailsRebateAmount);

		if (udc2 == null) {
			imtdRsDetailsImpl.setUdc2(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setUdc2(udc2);
		}

		if (rsDetailsModifiedBy == null) {
			imtdRsDetailsImpl.setRsDetailsModifiedBy(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsModifiedBy(rsDetailsModifiedBy);
		}

		if (udc3 == null) {
			imtdRsDetailsImpl.setUdc3(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setUdc3(udc3);
		}

		if (rebatePlanMasterSid == null) {
			imtdRsDetailsImpl.setRebatePlanMasterSid(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRebatePlanMasterSid(rebatePlanMasterSid);
		}

		if (rsDetailsAttachedDate == Long.MIN_VALUE) {
			imtdRsDetailsImpl.setRsDetailsAttachedDate(null);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsAttachedDate(new Date(
					rsDetailsAttachedDate));
		}

		if (itemRebateEndDate == Long.MIN_VALUE) {
			imtdRsDetailsImpl.setItemRebateEndDate(null);
		}
		else {
			imtdRsDetailsImpl.setItemRebateEndDate(new Date(itemRebateEndDate));
		}

		if (rsDetailsRebatePlanName == null) {
			imtdRsDetailsImpl.setRsDetailsRebatePlanName(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsRebatePlanName(rsDetailsRebatePlanName);
		}

		if (itemRebateStartDate == Long.MIN_VALUE) {
			imtdRsDetailsImpl.setItemRebateStartDate(null);
		}
		else {
			imtdRsDetailsImpl.setItemRebateStartDate(new Date(
					itemRebateStartDate));
		}

		if (rsDetailsFormulaType == null) {
			imtdRsDetailsImpl.setRsDetailsFormulaType(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsFormulaType(rsDetailsFormulaType);
		}

		if (sessionId == null) {
			imtdRsDetailsImpl.setSessionId(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setSessionId(sessionId);
		}

		if (itemName == null) {
			imtdRsDetailsImpl.setItemName(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setItemName(itemName);
		}

		if (operation == null) {
			imtdRsDetailsImpl.setOperation(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setOperation(operation);
		}

		imtdRsDetailsImpl.setCfpModelSid(cfpModelSid);
		imtdRsDetailsImpl.setRsModelSid(rsModelSid);
		imtdRsDetailsImpl.setRsDetailsSid(rsDetailsSid);
		imtdRsDetailsImpl.setRsDetailsAttachedStatus(rsDetailsAttachedStatus);

		if (rsDetailsNetSalesFormulaNo == null) {
			imtdRsDetailsImpl.setRsDetailsNetSalesFormulaNo(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsNetSalesFormulaNo(rsDetailsNetSalesFormulaNo);
		}

		if (rsDetailsNetSalesFormulaName == null) {
			imtdRsDetailsImpl.setRsDetailsNetSalesFormulaName(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsNetSalesFormulaName(rsDetailsNetSalesFormulaName);
		}

		if (rsDetailsDeductionCalendarNo == null) {
			imtdRsDetailsImpl.setRsDetailsDeductionCalendarNo(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsDeductionCalendarNo(rsDetailsDeductionCalendarNo);
		}

		if (rsDetailsDeductionCalendarName == null) {
			imtdRsDetailsImpl.setRsDetailsDeductionCalendarName(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setRsDetailsDeductionCalendarName(rsDetailsDeductionCalendarName);
		}

		imtdRsDetailsImpl.setDeductionCalendarMasterSid(deductionCalendarMasterSid);
		imtdRsDetailsImpl.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);

		if (evaluationRule == null) {
			imtdRsDetailsImpl.setEvaluationRule(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setEvaluationRule(evaluationRule);
		}

		if (netSalesRule == null) {
			imtdRsDetailsImpl.setNetSalesRule(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setNetSalesRule(netSalesRule);
		}

		if (formulaType == null) {
			imtdRsDetailsImpl.setFormulaType(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setFormulaType(formulaType);
		}

		if (calculationRule == null) {
			imtdRsDetailsImpl.setCalculationRule(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setCalculationRule(calculationRule);
		}

		if (calculationRuleBundle == null) {
			imtdRsDetailsImpl.setCalculationRuleBundle(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setCalculationRuleBundle(calculationRuleBundle);
		}

		if (evaluationRuleBundle == null) {
			imtdRsDetailsImpl.setEvaluationRuleBundle(StringPool.BLANK);
		}
		else {
			imtdRsDetailsImpl.setEvaluationRuleBundle(evaluationRuleBundle);
		}

		imtdRsDetailsImpl.resetOriginalValues();

		return imtdRsDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		rsDetailsModifiedDate = objectInput.readLong();
		rsDetailsBundleNo = objectInput.readUTF();

		itemMasterSid = objectInput.readInt();

		imtdRsDetailsSid = objectInput.readInt();
		itemId = objectInput.readUTF();
		rsDetailsFormulaMethodId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		usersSid = objectInput.readUTF();

		contractMasterSid = objectInput.readInt();
		rsDetailsFormulaId = objectInput.readUTF();
		imtdCreatedDate = objectInput.readLong();

		psModelSid = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		rsDetailsCreatedDate = objectInput.readLong();
		itemNo = objectInput.readUTF();
		rsDetailsFormulaName = objectInput.readUTF();
		udc6 = objectInput.readUTF();
		rsDetailsCreatedBy = objectInput.readUTF();
		udc5 = objectInput.readUTF();

		ifpModelSid = objectInput.readInt();
		udc4 = objectInput.readUTF();
		rsDetailsFormulaNo = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();
		rsId = objectInput.readUTF();
		udc1 = objectInput.readUTF();

		rsDetailsRebateAmount = objectInput.readDouble();
		udc2 = objectInput.readUTF();
		rsDetailsModifiedBy = objectInput.readUTF();
		udc3 = objectInput.readUTF();
		rebatePlanMasterSid = objectInput.readUTF();
		rsDetailsAttachedDate = objectInput.readLong();
		itemRebateEndDate = objectInput.readLong();
		rsDetailsRebatePlanName = objectInput.readUTF();
		itemRebateStartDate = objectInput.readLong();
		rsDetailsFormulaType = objectInput.readUTF();
		sessionId = objectInput.readUTF();
		itemName = objectInput.readUTF();
		operation = objectInput.readUTF();

		cfpModelSid = objectInput.readInt();

		rsModelSid = objectInput.readInt();

		rsDetailsSid = objectInput.readInt();

		rsDetailsAttachedStatus = objectInput.readInt();
		rsDetailsNetSalesFormulaNo = objectInput.readUTF();
		rsDetailsNetSalesFormulaName = objectInput.readUTF();
		rsDetailsDeductionCalendarNo = objectInput.readUTF();
		rsDetailsDeductionCalendarName = objectInput.readUTF();

		deductionCalendarMasterSid = objectInput.readInt();

		netSalesFormulaMasterSid = objectInput.readInt();
		evaluationRule = objectInput.readUTF();
		netSalesRule = objectInput.readUTF();
		formulaType = objectInput.readUTF();
		calculationRule = objectInput.readUTF();
		calculationRuleBundle = objectInput.readUTF();
		evaluationRuleBundle = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(rsDetailsModifiedDate);

		if (rsDetailsBundleNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsBundleNo);
		}

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(imtdRsDetailsSid);

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		if (rsDetailsFormulaMethodId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsFormulaMethodId);
		}

		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (usersSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(usersSid);
		}

		objectOutput.writeInt(contractMasterSid);

		if (rsDetailsFormulaId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsFormulaId);
		}

		objectOutput.writeLong(imtdCreatedDate);

		objectOutput.writeInt(psModelSid);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(rsDetailsCreatedDate);

		if (itemNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemNo);
		}

		if (rsDetailsFormulaName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsFormulaName);
		}

		if (udc6 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc6);
		}

		if (rsDetailsCreatedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsCreatedBy);
		}

		if (udc5 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc5);
		}

		objectOutput.writeInt(ifpModelSid);

		if (udc4 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc4);
		}

		if (rsDetailsFormulaNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsFormulaNo);
		}

		objectOutput.writeBoolean(checkRecord);

		if (rsId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsId);
		}

		if (udc1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc1);
		}

		objectOutput.writeDouble(rsDetailsRebateAmount);

		if (udc2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc2);
		}

		if (rsDetailsModifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsModifiedBy);
		}

		if (udc3 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc3);
		}

		if (rebatePlanMasterSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rebatePlanMasterSid);
		}

		objectOutput.writeLong(rsDetailsAttachedDate);
		objectOutput.writeLong(itemRebateEndDate);

		if (rsDetailsRebatePlanName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsRebatePlanName);
		}

		objectOutput.writeLong(itemRebateStartDate);

		if (rsDetailsFormulaType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsFormulaType);
		}

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		if (operation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operation);
		}

		objectOutput.writeInt(cfpModelSid);

		objectOutput.writeInt(rsModelSid);

		objectOutput.writeInt(rsDetailsSid);

		objectOutput.writeInt(rsDetailsAttachedStatus);

		if (rsDetailsNetSalesFormulaNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsNetSalesFormulaNo);
		}

		if (rsDetailsNetSalesFormulaName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsNetSalesFormulaName);
		}

		if (rsDetailsDeductionCalendarNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsDeductionCalendarNo);
		}

		if (rsDetailsDeductionCalendarName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDetailsDeductionCalendarName);
		}

		objectOutput.writeInt(deductionCalendarMasterSid);

		objectOutput.writeInt(netSalesFormulaMasterSid);

		if (evaluationRule == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(evaluationRule);
		}

		if (netSalesRule == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netSalesRule);
		}

		if (formulaType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formulaType);
		}

		if (calculationRule == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(calculationRule);
		}

		if (calculationRuleBundle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(calculationRuleBundle);
		}

		if (evaluationRuleBundle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(evaluationRuleBundle);
		}
	}

	public long rsDetailsModifiedDate;
	public String rsDetailsBundleNo;
	public int itemMasterSid;
	public int imtdRsDetailsSid;
	public String itemId;
	public String rsDetailsFormulaMethodId;
	public long modifiedDate;
	public long createdDate;
	public int createdBy;
	public String usersSid;
	public int contractMasterSid;
	public String rsDetailsFormulaId;
	public long imtdCreatedDate;
	public int psModelSid;
	public int modifiedBy;
	public long rsDetailsCreatedDate;
	public String itemNo;
	public String rsDetailsFormulaName;
	public String udc6;
	public String rsDetailsCreatedBy;
	public String udc5;
	public int ifpModelSid;
	public String udc4;
	public String rsDetailsFormulaNo;
	public boolean checkRecord;
	public String rsId;
	public String udc1;
	public double rsDetailsRebateAmount;
	public String udc2;
	public String rsDetailsModifiedBy;
	public String udc3;
	public String rebatePlanMasterSid;
	public long rsDetailsAttachedDate;
	public long itemRebateEndDate;
	public String rsDetailsRebatePlanName;
	public long itemRebateStartDate;
	public String rsDetailsFormulaType;
	public String sessionId;
	public String itemName;
	public String operation;
	public int cfpModelSid;
	public int rsModelSid;
	public int rsDetailsSid;
	public int rsDetailsAttachedStatus;
	public String rsDetailsNetSalesFormulaNo;
	public String rsDetailsNetSalesFormulaName;
	public String rsDetailsDeductionCalendarNo;
	public String rsDetailsDeductionCalendarName;
	public int deductionCalendarMasterSid;
	public int netSalesFormulaMasterSid;
	public String evaluationRule;
	public String netSalesRule;
	public String formulaType;
	public String calculationRule;
	public String calculationRuleBundle;
	public String evaluationRuleBundle;
}