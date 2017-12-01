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

import com.stpl.app.model.RsDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RsDetails in entity cache.
 *
 * @author
 * @see RsDetails
 * @generated
 */
@ProviderType
public class RsDetailsCacheModel implements CacheModel<RsDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RsDetailsCacheModel)) {
			return false;
		}

		RsDetailsCacheModel rsDetailsCacheModel = (RsDetailsCacheModel)obj;

		if (rsDetailsSid == rsDetailsCacheModel.rsDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, rsDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(59);

		sb.append("{rebateAmount=");
		sb.append(rebateAmount);
		sb.append(", itemRsAttachedStatus=");
		sb.append(itemRsAttachedStatus);
		sb.append(", formulaMethodId=");
		sb.append(formulaMethodId);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", rebatePlanMasterSid=");
		sb.append(rebatePlanMasterSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", bundleNo=");
		sb.append(bundleNo);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", source=");
		sb.append(source);
		sb.append(", itemRebateEndDate=");
		sb.append(itemRebateEndDate);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", itemRebateStartDate=");
		sb.append(itemRebateStartDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", rsDetailsSid=");
		sb.append(rsDetailsSid);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", formulaId=");
		sb.append(formulaId);
		sb.append(", itemRsAttachedDate=");
		sb.append(itemRsAttachedDate);
		sb.append(", ifpModelSid=");
		sb.append(ifpModelSid);
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
	public RsDetails toEntityModel() {
		RsDetailsImpl rsDetailsImpl = new RsDetailsImpl();

		rsDetailsImpl.setRebateAmount(rebateAmount);
		rsDetailsImpl.setItemRsAttachedStatus(itemRsAttachedStatus);

		if (formulaMethodId == null) {
			rsDetailsImpl.setFormulaMethodId(StringPool.BLANK);
		}
		else {
			rsDetailsImpl.setFormulaMethodId(formulaMethodId);
		}

		rsDetailsImpl.setItemMasterSid(itemMasterSid);
		rsDetailsImpl.setRebatePlanMasterSid(rebatePlanMasterSid);

		if (modifiedDate == Long.MIN_VALUE) {
			rsDetailsImpl.setModifiedDate(null);
		}
		else {
			rsDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (bundleNo == null) {
			rsDetailsImpl.setBundleNo(StringPool.BLANK);
		}
		else {
			rsDetailsImpl.setBundleNo(bundleNo);
		}

		rsDetailsImpl.setRecordLockStatus(recordLockStatus);

		if (createdDate == Long.MIN_VALUE) {
			rsDetailsImpl.setCreatedDate(null);
		}
		else {
			rsDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		rsDetailsImpl.setCreatedBy(createdBy);

		if (source == null) {
			rsDetailsImpl.setSource(StringPool.BLANK);
		}
		else {
			rsDetailsImpl.setSource(source);
		}

		if (itemRebateEndDate == Long.MIN_VALUE) {
			rsDetailsImpl.setItemRebateEndDate(null);
		}
		else {
			rsDetailsImpl.setItemRebateEndDate(new Date(itemRebateEndDate));
		}

		if (batchId == null) {
			rsDetailsImpl.setBatchId(StringPool.BLANK);
		}
		else {
			rsDetailsImpl.setBatchId(batchId);
		}

		if (itemRebateStartDate == Long.MIN_VALUE) {
			rsDetailsImpl.setItemRebateStartDate(null);
		}
		else {
			rsDetailsImpl.setItemRebateStartDate(new Date(itemRebateStartDate));
		}

		rsDetailsImpl.setModifiedBy(modifiedBy);

		if (inboundStatus == null) {
			rsDetailsImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			rsDetailsImpl.setInboundStatus(inboundStatus);
		}

		rsDetailsImpl.setRsDetailsSid(rsDetailsSid);
		rsDetailsImpl.setRsModelSid(rsModelSid);
		rsDetailsImpl.setFormulaId(formulaId);

		if (itemRsAttachedDate == Long.MIN_VALUE) {
			rsDetailsImpl.setItemRsAttachedDate(null);
		}
		else {
			rsDetailsImpl.setItemRsAttachedDate(new Date(itemRsAttachedDate));
		}

		rsDetailsImpl.setIfpModelSid(ifpModelSid);
		rsDetailsImpl.setDeductionCalendarMasterSid(deductionCalendarMasterSid);
		rsDetailsImpl.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);

		if (evaluationRule == null) {
			rsDetailsImpl.setEvaluationRule(StringPool.BLANK);
		}
		else {
			rsDetailsImpl.setEvaluationRule(evaluationRule);
		}

		if (netSalesRule == null) {
			rsDetailsImpl.setNetSalesRule(StringPool.BLANK);
		}
		else {
			rsDetailsImpl.setNetSalesRule(netSalesRule);
		}

		if (formulaType == null) {
			rsDetailsImpl.setFormulaType(StringPool.BLANK);
		}
		else {
			rsDetailsImpl.setFormulaType(formulaType);
		}

		if (calculationRule == null) {
			rsDetailsImpl.setCalculationRule(StringPool.BLANK);
		}
		else {
			rsDetailsImpl.setCalculationRule(calculationRule);
		}

		if (calculationRuleBundle == null) {
			rsDetailsImpl.setCalculationRuleBundle(StringPool.BLANK);
		}
		else {
			rsDetailsImpl.setCalculationRuleBundle(calculationRuleBundle);
		}

		if (evaluationRuleBundle == null) {
			rsDetailsImpl.setEvaluationRuleBundle(StringPool.BLANK);
		}
		else {
			rsDetailsImpl.setEvaluationRuleBundle(evaluationRuleBundle);
		}

		rsDetailsImpl.resetOriginalValues();

		return rsDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		rebateAmount = objectInput.readDouble();

		itemRsAttachedStatus = objectInput.readInt();
		formulaMethodId = objectInput.readUTF();

		itemMasterSid = objectInput.readInt();

		rebatePlanMasterSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		bundleNo = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		source = objectInput.readUTF();
		itemRebateEndDate = objectInput.readLong();
		batchId = objectInput.readUTF();
		itemRebateStartDate = objectInput.readLong();

		modifiedBy = objectInput.readInt();
		inboundStatus = objectInput.readUTF();

		rsDetailsSid = objectInput.readInt();

		rsModelSid = objectInput.readInt();

		formulaId = objectInput.readInt();
		itemRsAttachedDate = objectInput.readLong();

		ifpModelSid = objectInput.readInt();

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
		objectOutput.writeDouble(rebateAmount);

		objectOutput.writeInt(itemRsAttachedStatus);

		if (formulaMethodId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formulaMethodId);
		}

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(rebatePlanMasterSid);
		objectOutput.writeLong(modifiedDate);

		if (bundleNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(bundleNo);
		}

		objectOutput.writeBoolean(recordLockStatus);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeLong(itemRebateEndDate);

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeLong(itemRebateStartDate);

		objectOutput.writeInt(modifiedBy);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		objectOutput.writeInt(rsDetailsSid);

		objectOutput.writeInt(rsModelSid);

		objectOutput.writeInt(formulaId);
		objectOutput.writeLong(itemRsAttachedDate);

		objectOutput.writeInt(ifpModelSid);

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

	public double rebateAmount;
	public int itemRsAttachedStatus;
	public String formulaMethodId;
	public int itemMasterSid;
	public int rebatePlanMasterSid;
	public long modifiedDate;
	public String bundleNo;
	public boolean recordLockStatus;
	public long createdDate;
	public int createdBy;
	public String source;
	public long itemRebateEndDate;
	public String batchId;
	public long itemRebateStartDate;
	public int modifiedBy;
	public String inboundStatus;
	public int rsDetailsSid;
	public int rsModelSid;
	public int formulaId;
	public long itemRsAttachedDate;
	public int ifpModelSid;
	public int deductionCalendarMasterSid;
	public int netSalesFormulaMasterSid;
	public String evaluationRule;
	public String netSalesRule;
	public String formulaType;
	public String calculationRule;
	public String calculationRuleBundle;
	public String evaluationRuleBundle;
}