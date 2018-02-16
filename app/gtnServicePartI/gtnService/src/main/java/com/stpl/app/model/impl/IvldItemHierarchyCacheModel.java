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

import com.stpl.app.model.IvldItemHierarchy;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldItemHierarchy in entity cache.
 *
 * @author
 * @see IvldItemHierarchy
 * @generated
 */
@ProviderType
public class IvldItemHierarchyCacheModel implements CacheModel<IvldItemHierarchy>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldItemHierarchyCacheModel)) {
			return false;
		}

		IvldItemHierarchyCacheModel ivldItemHierarchyCacheModel = (IvldItemHierarchyCacheModel)obj;

		if (ivldItemHierarchySid == ivldItemHierarchyCacheModel.ivldItemHierarchySid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ivldItemHierarchySid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(123);

		sb.append("{level1Alias=");
		sb.append(level1Alias);
		sb.append(", level9Alias=");
		sb.append(level9Alias);
		sb.append(", level3Alias=");
		sb.append(level3Alias);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", level13Alias=");
		sb.append(level13Alias);
		sb.append(", gtnCompany=");
		sb.append(gtnCompany);
		sb.append(", source=");
		sb.append(source);
		sb.append(", level15Alias=");
		sb.append(level15Alias);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", ivldItemHierarchySid=");
		sb.append(ivldItemHierarchySid);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", level6Alias=");
		sb.append(level6Alias);
		sb.append(", reasonForFailure=");
		sb.append(reasonForFailure);
		sb.append(", level10Alias=");
		sb.append(level10Alias);
		sb.append(", itemHierarchyIntfid=");
		sb.append(itemHierarchyIntfid);
		sb.append(", level5Alias=");
		sb.append(level5Alias);
		sb.append(", level18Alias=");
		sb.append(level18Alias);
		sb.append(", errorField=");
		sb.append(errorField);
		sb.append(", gtnTherapeuticClass=");
		sb.append(gtnTherapeuticClass);
		sb.append(", level8=");
		sb.append(level8);
		sb.append(", level9=");
		sb.append(level9);
		sb.append(", level11Alias=");
		sb.append(level11Alias);
		sb.append(", level20=");
		sb.append(level20);
		sb.append(", level4=");
		sb.append(level4);
		sb.append(", level5=");
		sb.append(level5);
		sb.append(", level6=");
		sb.append(level6);
		sb.append(", level7=");
		sb.append(level7);
		sb.append(", level16Alias=");
		sb.append(level16Alias);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", gtnBrand=");
		sb.append(gtnBrand);
		sb.append(", level2Alias=");
		sb.append(level2Alias);
		sb.append(", level1=");
		sb.append(level1);
		sb.append(", level0=");
		sb.append(level0);
		sb.append(", errorCode=");
		sb.append(errorCode);
		sb.append(", level3=");
		sb.append(level3);
		sb.append(", level17Alias=");
		sb.append(level17Alias);
		sb.append(", level20Alias=");
		sb.append(level20Alias);
		sb.append(", intfInsertedDate=");
		sb.append(intfInsertedDate);
		sb.append(", level7Alias=");
		sb.append(level7Alias);
		sb.append(", level2=");
		sb.append(level2);
		sb.append(", reprocessedFlag=");
		sb.append(reprocessedFlag);
		sb.append(", level8Alias=");
		sb.append(level8Alias);
		sb.append(", level10=");
		sb.append(level10);
		sb.append(", level4Alias=");
		sb.append(level4Alias);
		sb.append(", level12=");
		sb.append(level12);
		sb.append(", level11=");
		sb.append(level11);
		sb.append(", level14=");
		sb.append(level14);
		sb.append(", level0Tag=");
		sb.append(level0Tag);
		sb.append(", level13=");
		sb.append(level13);
		sb.append(", level16=");
		sb.append(level16);
		sb.append(", level15=");
		sb.append(level15);
		sb.append(", level18=");
		sb.append(level18);
		sb.append(", level17=");
		sb.append(level17);
		sb.append(", level19=");
		sb.append(level19);
		sb.append(", level12Alias=");
		sb.append(level12Alias);
		sb.append(", level19Alias=");
		sb.append(level19Alias);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", level0Alias=");
		sb.append(level0Alias);
		sb.append(", level14Alias=");
		sb.append(level14Alias);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IvldItemHierarchy toEntityModel() {
		IvldItemHierarchyImpl ivldItemHierarchyImpl = new IvldItemHierarchyImpl();

		if (level1Alias == null) {
			ivldItemHierarchyImpl.setLevel1Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel1Alias(level1Alias);
		}

		if (level9Alias == null) {
			ivldItemHierarchyImpl.setLevel9Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel9Alias(level9Alias);
		}

		if (level3Alias == null) {
			ivldItemHierarchyImpl.setLevel3Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel3Alias(level3Alias);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ivldItemHierarchyImpl.setModifiedDate(null);
		}
		else {
			ivldItemHierarchyImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (level13Alias == null) {
			ivldItemHierarchyImpl.setLevel13Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel13Alias(level13Alias);
		}

		if (gtnCompany == null) {
			ivldItemHierarchyImpl.setGtnCompany(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setGtnCompany(gtnCompany);
		}

		if (source == null) {
			ivldItemHierarchyImpl.setSource(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setSource(source);
		}

		if (level15Alias == null) {
			ivldItemHierarchyImpl.setLevel15Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel15Alias(level15Alias);
		}

		if (addChgDelIndicator == null) {
			ivldItemHierarchyImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		ivldItemHierarchyImpl.setIvldItemHierarchySid(ivldItemHierarchySid);

		if (modifiedBy == null) {
			ivldItemHierarchyImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setModifiedBy(modifiedBy);
		}

		if (level6Alias == null) {
			ivldItemHierarchyImpl.setLevel6Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel6Alias(level6Alias);
		}

		if (reasonForFailure == null) {
			ivldItemHierarchyImpl.setReasonForFailure(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setReasonForFailure(reasonForFailure);
		}

		if (level10Alias == null) {
			ivldItemHierarchyImpl.setLevel10Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel10Alias(level10Alias);
		}

		if (itemHierarchyIntfid == null) {
			ivldItemHierarchyImpl.setItemHierarchyIntfid(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setItemHierarchyIntfid(itemHierarchyIntfid);
		}

		if (level5Alias == null) {
			ivldItemHierarchyImpl.setLevel5Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel5Alias(level5Alias);
		}

		if (level18Alias == null) {
			ivldItemHierarchyImpl.setLevel18Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel18Alias(level18Alias);
		}

		if (errorField == null) {
			ivldItemHierarchyImpl.setErrorField(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setErrorField(errorField);
		}

		if (gtnTherapeuticClass == null) {
			ivldItemHierarchyImpl.setGtnTherapeuticClass(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setGtnTherapeuticClass(gtnTherapeuticClass);
		}

		if (level8 == null) {
			ivldItemHierarchyImpl.setLevel8(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel8(level8);
		}

		if (level9 == null) {
			ivldItemHierarchyImpl.setLevel9(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel9(level9);
		}

		if (level11Alias == null) {
			ivldItemHierarchyImpl.setLevel11Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel11Alias(level11Alias);
		}

		if (level20 == null) {
			ivldItemHierarchyImpl.setLevel20(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel20(level20);
		}

		if (level4 == null) {
			ivldItemHierarchyImpl.setLevel4(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel4(level4);
		}

		if (level5 == null) {
			ivldItemHierarchyImpl.setLevel5(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel5(level5);
		}

		if (level6 == null) {
			ivldItemHierarchyImpl.setLevel6(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel6(level6);
		}

		if (level7 == null) {
			ivldItemHierarchyImpl.setLevel7(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel7(level7);
		}

		if (level16Alias == null) {
			ivldItemHierarchyImpl.setLevel16Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel16Alias(level16Alias);
		}

		if (createdDate == Long.MIN_VALUE) {
			ivldItemHierarchyImpl.setCreatedDate(null);
		}
		else {
			ivldItemHierarchyImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			ivldItemHierarchyImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setCreatedBy(createdBy);
		}

		if (gtnBrand == null) {
			ivldItemHierarchyImpl.setGtnBrand(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setGtnBrand(gtnBrand);
		}

		if (level2Alias == null) {
			ivldItemHierarchyImpl.setLevel2Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel2Alias(level2Alias);
		}

		if (level1 == null) {
			ivldItemHierarchyImpl.setLevel1(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel1(level1);
		}

		if (level0 == null) {
			ivldItemHierarchyImpl.setLevel0(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel0(level0);
		}

		if (errorCode == null) {
			ivldItemHierarchyImpl.setErrorCode(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setErrorCode(errorCode);
		}

		if (level3 == null) {
			ivldItemHierarchyImpl.setLevel3(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel3(level3);
		}

		if (level17Alias == null) {
			ivldItemHierarchyImpl.setLevel17Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel17Alias(level17Alias);
		}

		if (level20Alias == null) {
			ivldItemHierarchyImpl.setLevel20Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel20Alias(level20Alias);
		}

		if (intfInsertedDate == Long.MIN_VALUE) {
			ivldItemHierarchyImpl.setIntfInsertedDate(null);
		}
		else {
			ivldItemHierarchyImpl.setIntfInsertedDate(new Date(intfInsertedDate));
		}

		if (level7Alias == null) {
			ivldItemHierarchyImpl.setLevel7Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel7Alias(level7Alias);
		}

		if (level2 == null) {
			ivldItemHierarchyImpl.setLevel2(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel2(level2);
		}

		if (reprocessedFlag == null) {
			ivldItemHierarchyImpl.setReprocessedFlag(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setReprocessedFlag(reprocessedFlag);
		}

		if (level8Alias == null) {
			ivldItemHierarchyImpl.setLevel8Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel8Alias(level8Alias);
		}

		if (level10 == null) {
			ivldItemHierarchyImpl.setLevel10(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel10(level10);
		}

		if (level4Alias == null) {
			ivldItemHierarchyImpl.setLevel4Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel4Alias(level4Alias);
		}

		if (level12 == null) {
			ivldItemHierarchyImpl.setLevel12(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel12(level12);
		}

		if (level11 == null) {
			ivldItemHierarchyImpl.setLevel11(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel11(level11);
		}

		if (level14 == null) {
			ivldItemHierarchyImpl.setLevel14(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel14(level14);
		}

		if (level0Tag == null) {
			ivldItemHierarchyImpl.setLevel0Tag(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel0Tag(level0Tag);
		}

		if (level13 == null) {
			ivldItemHierarchyImpl.setLevel13(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel13(level13);
		}

		if (level16 == null) {
			ivldItemHierarchyImpl.setLevel16(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel16(level16);
		}

		if (level15 == null) {
			ivldItemHierarchyImpl.setLevel15(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel15(level15);
		}

		if (level18 == null) {
			ivldItemHierarchyImpl.setLevel18(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel18(level18);
		}

		if (level17 == null) {
			ivldItemHierarchyImpl.setLevel17(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel17(level17);
		}

		if (level19 == null) {
			ivldItemHierarchyImpl.setLevel19(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel19(level19);
		}

		if (level12Alias == null) {
			ivldItemHierarchyImpl.setLevel12Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel12Alias(level12Alias);
		}

		if (level19Alias == null) {
			ivldItemHierarchyImpl.setLevel19Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel19Alias(level19Alias);
		}

		if (batchId == null) {
			ivldItemHierarchyImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setBatchId(batchId);
		}

		if (level0Alias == null) {
			ivldItemHierarchyImpl.setLevel0Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel0Alias(level0Alias);
		}

		if (level14Alias == null) {
			ivldItemHierarchyImpl.setLevel14Alias(StringPool.BLANK);
		}
		else {
			ivldItemHierarchyImpl.setLevel14Alias(level14Alias);
		}

		ivldItemHierarchyImpl.setCheckRecord(checkRecord);

		ivldItemHierarchyImpl.resetOriginalValues();

		return ivldItemHierarchyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		level1Alias = objectInput.readUTF();
		level9Alias = objectInput.readUTF();
		level3Alias = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		level13Alias = objectInput.readUTF();
		gtnCompany = objectInput.readUTF();
		source = objectInput.readUTF();
		level15Alias = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();

		ivldItemHierarchySid = objectInput.readInt();
		modifiedBy = objectInput.readUTF();
		level6Alias = objectInput.readUTF();
		reasonForFailure = objectInput.readUTF();
		level10Alias = objectInput.readUTF();
		itemHierarchyIntfid = objectInput.readUTF();
		level5Alias = objectInput.readUTF();
		level18Alias = objectInput.readUTF();
		errorField = objectInput.readUTF();
		gtnTherapeuticClass = objectInput.readUTF();
		level8 = objectInput.readUTF();
		level9 = objectInput.readUTF();
		level11Alias = objectInput.readUTF();
		level20 = objectInput.readUTF();
		level4 = objectInput.readUTF();
		level5 = objectInput.readUTF();
		level6 = objectInput.readUTF();
		level7 = objectInput.readUTF();
		level16Alias = objectInput.readUTF();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		gtnBrand = objectInput.readUTF();
		level2Alias = objectInput.readUTF();
		level1 = objectInput.readUTF();
		level0 = objectInput.readUTF();
		errorCode = objectInput.readUTF();
		level3 = objectInput.readUTF();
		level17Alias = objectInput.readUTF();
		level20Alias = objectInput.readUTF();
		intfInsertedDate = objectInput.readLong();
		level7Alias = objectInput.readUTF();
		level2 = objectInput.readUTF();
		reprocessedFlag = objectInput.readUTF();
		level8Alias = objectInput.readUTF();
		level10 = objectInput.readUTF();
		level4Alias = objectInput.readUTF();
		level12 = objectInput.readUTF();
		level11 = objectInput.readUTF();
		level14 = objectInput.readUTF();
		level0Tag = objectInput.readUTF();
		level13 = objectInput.readUTF();
		level16 = objectInput.readUTF();
		level15 = objectInput.readUTF();
		level18 = objectInput.readUTF();
		level17 = objectInput.readUTF();
		level19 = objectInput.readUTF();
		level12Alias = objectInput.readUTF();
		level19Alias = objectInput.readUTF();
		batchId = objectInput.readUTF();
		level0Alias = objectInput.readUTF();
		level14Alias = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (level1Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level1Alias);
		}

		if (level9Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level9Alias);
		}

		if (level3Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level3Alias);
		}

		objectOutput.writeLong(modifiedDate);

		if (level13Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level13Alias);
		}

		if (gtnCompany == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(gtnCompany);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (level15Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level15Alias);
		}

		if (addChgDelIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addChgDelIndicator);
		}

		objectOutput.writeInt(ivldItemHierarchySid);

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		if (level6Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level6Alias);
		}

		if (reasonForFailure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reasonForFailure);
		}

		if (level10Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level10Alias);
		}

		if (itemHierarchyIntfid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemHierarchyIntfid);
		}

		if (level5Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level5Alias);
		}

		if (level18Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level18Alias);
		}

		if (errorField == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorField);
		}

		if (gtnTherapeuticClass == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(gtnTherapeuticClass);
		}

		if (level8 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level8);
		}

		if (level9 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level9);
		}

		if (level11Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level11Alias);
		}

		if (level20 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level20);
		}

		if (level4 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level4);
		}

		if (level5 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level5);
		}

		if (level6 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level6);
		}

		if (level7 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level7);
		}

		if (level16Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level16Alias);
		}

		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (gtnBrand == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(gtnBrand);
		}

		if (level2Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level2Alias);
		}

		if (level1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level1);
		}

		if (level0 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level0);
		}

		if (errorCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorCode);
		}

		if (level3 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level3);
		}

		if (level17Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level17Alias);
		}

		if (level20Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level20Alias);
		}

		objectOutput.writeLong(intfInsertedDate);

		if (level7Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level7Alias);
		}

		if (level2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level2);
		}

		if (reprocessedFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reprocessedFlag);
		}

		if (level8Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level8Alias);
		}

		if (level10 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level10);
		}

		if (level4Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level4Alias);
		}

		if (level12 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level12);
		}

		if (level11 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level11);
		}

		if (level14 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level14);
		}

		if (level0Tag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level0Tag);
		}

		if (level13 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level13);
		}

		if (level16 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level16);
		}

		if (level15 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level15);
		}

		if (level18 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level18);
		}

		if (level17 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level17);
		}

		if (level19 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level19);
		}

		if (level12Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level12Alias);
		}

		if (level19Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level19Alias);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (level0Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level0Alias);
		}

		if (level14Alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level14Alias);
		}

		objectOutput.writeBoolean(checkRecord);
	}

	public String level1Alias;
	public String level9Alias;
	public String level3Alias;
	public long modifiedDate;
	public String level13Alias;
	public String gtnCompany;
	public String source;
	public String level15Alias;
	public String addChgDelIndicator;
	public int ivldItemHierarchySid;
	public String modifiedBy;
	public String level6Alias;
	public String reasonForFailure;
	public String level10Alias;
	public String itemHierarchyIntfid;
	public String level5Alias;
	public String level18Alias;
	public String errorField;
	public String gtnTherapeuticClass;
	public String level8;
	public String level9;
	public String level11Alias;
	public String level20;
	public String level4;
	public String level5;
	public String level6;
	public String level7;
	public String level16Alias;
	public long createdDate;
	public String createdBy;
	public String gtnBrand;
	public String level2Alias;
	public String level1;
	public String level0;
	public String errorCode;
	public String level3;
	public String level17Alias;
	public String level20Alias;
	public long intfInsertedDate;
	public String level7Alias;
	public String level2;
	public String reprocessedFlag;
	public String level8Alias;
	public String level10;
	public String level4Alias;
	public String level12;
	public String level11;
	public String level14;
	public String level0Tag;
	public String level13;
	public String level16;
	public String level15;
	public String level18;
	public String level17;
	public String level19;
	public String level12Alias;
	public String level19Alias;
	public String batchId;
	public String level0Alias;
	public String level14Alias;
	public boolean checkRecord;
}