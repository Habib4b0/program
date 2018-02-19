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

import com.stpl.app.model.Udcs;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Udcs in entity cache.
 *
 * @author
 * @see Udcs
 * @generated
 */
@ProviderType
public class UdcsCacheModel implements CacheModel<Udcs>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UdcsCacheModel)) {
			return false;
		}

		UdcsCacheModel udcsCacheModel = (UdcsCacheModel)obj;

		if (udcsSid == udcsCacheModel.udcsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, udcsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{udc1=");
		sb.append(udc1);
		sb.append(", udc2=");
		sb.append(udc2);
		sb.append(", masterType=");
		sb.append(masterType);
		sb.append(", udc3=");
		sb.append(udc3);
		sb.append(", udc12=");
		sb.append(udc12);
		sb.append(", udc11=");
		sb.append(udc11);
		sb.append(", udcsSid=");
		sb.append(udcsSid);
		sb.append(", masterSid=");
		sb.append(masterSid);
		sb.append(", udc10=");
		sb.append(udc10);
		sb.append(", udc9=");
		sb.append(udc9);
		sb.append(", udc8=");
		sb.append(udc8);
		sb.append(", udc7=");
		sb.append(udc7);
		sb.append(", udc6=");
		sb.append(udc6);
		sb.append(", udc5=");
		sb.append(udc5);
		sb.append(", udc4=");
		sb.append(udc4);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Udcs toEntityModel() {
		UdcsImpl udcsImpl = new UdcsImpl();

		udcsImpl.setUdc1(udc1);
		udcsImpl.setUdc2(udc2);

		if (masterType == null) {
			udcsImpl.setMasterType(StringPool.BLANK);
		}
		else {
			udcsImpl.setMasterType(masterType);
		}

		udcsImpl.setUdc3(udc3);
		udcsImpl.setUdc12(udc12);
		udcsImpl.setUdc11(udc11);
		udcsImpl.setUdcsSid(udcsSid);
		udcsImpl.setMasterSid(masterSid);
		udcsImpl.setUdc10(udc10);
		udcsImpl.setUdc9(udc9);
		udcsImpl.setUdc8(udc8);
		udcsImpl.setUdc7(udc7);
		udcsImpl.setUdc6(udc6);
		udcsImpl.setUdc5(udc5);
		udcsImpl.setUdc4(udc4);

		udcsImpl.resetOriginalValues();

		return udcsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		udc1 = objectInput.readInt();

		udc2 = objectInput.readInt();
		masterType = objectInput.readUTF();

		udc3 = objectInput.readInt();

		udc12 = objectInput.readInt();

		udc11 = objectInput.readInt();

		udcsSid = objectInput.readInt();

		masterSid = objectInput.readInt();

		udc10 = objectInput.readInt();

		udc9 = objectInput.readInt();

		udc8 = objectInput.readInt();

		udc7 = objectInput.readInt();

		udc6 = objectInput.readInt();

		udc5 = objectInput.readInt();

		udc4 = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(udc1);

		objectOutput.writeInt(udc2);

		if (masterType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(masterType);
		}

		objectOutput.writeInt(udc3);

		objectOutput.writeInt(udc12);

		objectOutput.writeInt(udc11);

		objectOutput.writeInt(udcsSid);

		objectOutput.writeInt(masterSid);

		objectOutput.writeInt(udc10);

		objectOutput.writeInt(udc9);

		objectOutput.writeInt(udc8);

		objectOutput.writeInt(udc7);

		objectOutput.writeInt(udc6);

		objectOutput.writeInt(udc5);

		objectOutput.writeInt(udc4);
	}

	public int udc1;
	public int udc2;
	public String masterType;
	public int udc3;
	public int udc12;
	public int udc11;
	public int udcsSid;
	public int masterSid;
	public int udc10;
	public int udc9;
	public int udc8;
	public int udc7;
	public int udc6;
	public int udc5;
	public int udc4;
}