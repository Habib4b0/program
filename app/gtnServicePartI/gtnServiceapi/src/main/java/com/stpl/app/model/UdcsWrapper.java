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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Udcs}.
 * </p>
 *
 * @author
 * @see Udcs
 * @generated
 */
@ProviderType
public class UdcsWrapper implements Udcs, ModelWrapper<Udcs> {
	public UdcsWrapper(Udcs udcs) {
		_udcs = udcs;
	}

	@Override
	public Class<?> getModelClass() {
		return Udcs.class;
	}

	@Override
	public String getModelClassName() {
		return Udcs.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("udc1", getUdc1());
		attributes.put("udc2", getUdc2());
		attributes.put("masterType", getMasterType());
		attributes.put("udc3", getUdc3());
		attributes.put("udc12", getUdc12());
		attributes.put("udc11", getUdc11());
		attributes.put("udcsSid", getUdcsSid());
		attributes.put("masterSid", getMasterSid());
		attributes.put("udc10", getUdc10());
		attributes.put("udc9", getUdc9());
		attributes.put("udc8", getUdc8());
		attributes.put("udc7", getUdc7());
		attributes.put("udc6", getUdc6());
		attributes.put("udc5", getUdc5());
		attributes.put("udc4", getUdc4());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer udc1 = (Integer)attributes.get("udc1");

		if (udc1 != null) {
			setUdc1(udc1);
		}

		Integer udc2 = (Integer)attributes.get("udc2");

		if (udc2 != null) {
			setUdc2(udc2);
		}

		String masterType = (String)attributes.get("masterType");

		if (masterType != null) {
			setMasterType(masterType);
		}

		Integer udc3 = (Integer)attributes.get("udc3");

		if (udc3 != null) {
			setUdc3(udc3);
		}

		Integer udc12 = (Integer)attributes.get("udc12");

		if (udc12 != null) {
			setUdc12(udc12);
		}

		Integer udc11 = (Integer)attributes.get("udc11");

		if (udc11 != null) {
			setUdc11(udc11);
		}

		Integer udcsSid = (Integer)attributes.get("udcsSid");

		if (udcsSid != null) {
			setUdcsSid(udcsSid);
		}

		Integer masterSid = (Integer)attributes.get("masterSid");

		if (masterSid != null) {
			setMasterSid(masterSid);
		}

		Integer udc10 = (Integer)attributes.get("udc10");

		if (udc10 != null) {
			setUdc10(udc10);
		}

		Integer udc9 = (Integer)attributes.get("udc9");

		if (udc9 != null) {
			setUdc9(udc9);
		}

		Integer udc8 = (Integer)attributes.get("udc8");

		if (udc8 != null) {
			setUdc8(udc8);
		}

		Integer udc7 = (Integer)attributes.get("udc7");

		if (udc7 != null) {
			setUdc7(udc7);
		}

		Integer udc6 = (Integer)attributes.get("udc6");

		if (udc6 != null) {
			setUdc6(udc6);
		}

		Integer udc5 = (Integer)attributes.get("udc5");

		if (udc5 != null) {
			setUdc5(udc5);
		}

		Integer udc4 = (Integer)attributes.get("udc4");

		if (udc4 != null) {
			setUdc4(udc4);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new UdcsWrapper((Udcs)_udcs.clone());
	}

	@Override
	public int compareTo(Udcs udcs) {
		return _udcs.compareTo(udcs);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _udcs.getExpandoBridge();
	}

	/**
	* Returns the master sid of this udcs.
	*
	* @return the master sid of this udcs
	*/
	@Override
	public int getMasterSid() {
		return _udcs.getMasterSid();
	}

	/**
	* Returns the master type of this udcs.
	*
	* @return the master type of this udcs
	*/
	@Override
	public java.lang.String getMasterType() {
		return _udcs.getMasterType();
	}

	/**
	* Returns the primary key of this udcs.
	*
	* @return the primary key of this udcs
	*/
	@Override
	public int getPrimaryKey() {
		return _udcs.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _udcs.getPrimaryKeyObj();
	}

	/**
	* Returns the udc1 of this udcs.
	*
	* @return the udc1 of this udcs
	*/
	@Override
	public int getUdc1() {
		return _udcs.getUdc1();
	}

	/**
	* Returns the udc10 of this udcs.
	*
	* @return the udc10 of this udcs
	*/
	@Override
	public int getUdc10() {
		return _udcs.getUdc10();
	}

	/**
	* Returns the udc11 of this udcs.
	*
	* @return the udc11 of this udcs
	*/
	@Override
	public int getUdc11() {
		return _udcs.getUdc11();
	}

	/**
	* Returns the udc12 of this udcs.
	*
	* @return the udc12 of this udcs
	*/
	@Override
	public int getUdc12() {
		return _udcs.getUdc12();
	}

	/**
	* Returns the udc2 of this udcs.
	*
	* @return the udc2 of this udcs
	*/
	@Override
	public int getUdc2() {
		return _udcs.getUdc2();
	}

	/**
	* Returns the udc3 of this udcs.
	*
	* @return the udc3 of this udcs
	*/
	@Override
	public int getUdc3() {
		return _udcs.getUdc3();
	}

	/**
	* Returns the udc4 of this udcs.
	*
	* @return the udc4 of this udcs
	*/
	@Override
	public int getUdc4() {
		return _udcs.getUdc4();
	}

	/**
	* Returns the udc5 of this udcs.
	*
	* @return the udc5 of this udcs
	*/
	@Override
	public int getUdc5() {
		return _udcs.getUdc5();
	}

	/**
	* Returns the udc6 of this udcs.
	*
	* @return the udc6 of this udcs
	*/
	@Override
	public int getUdc6() {
		return _udcs.getUdc6();
	}

	/**
	* Returns the udc7 of this udcs.
	*
	* @return the udc7 of this udcs
	*/
	@Override
	public int getUdc7() {
		return _udcs.getUdc7();
	}

	/**
	* Returns the udc8 of this udcs.
	*
	* @return the udc8 of this udcs
	*/
	@Override
	public int getUdc8() {
		return _udcs.getUdc8();
	}

	/**
	* Returns the udc9 of this udcs.
	*
	* @return the udc9 of this udcs
	*/
	@Override
	public int getUdc9() {
		return _udcs.getUdc9();
	}

	/**
	* Returns the udcs sid of this udcs.
	*
	* @return the udcs sid of this udcs
	*/
	@Override
	public int getUdcsSid() {
		return _udcs.getUdcsSid();
	}

	@Override
	public int hashCode() {
		return _udcs.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _udcs.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _udcs.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _udcs.isNew();
	}

	@Override
	public void persist() {
		_udcs.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_udcs.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_udcs.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_udcs.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_udcs.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the master sid of this udcs.
	*
	* @param masterSid the master sid of this udcs
	*/
	@Override
	public void setMasterSid(int masterSid) {
		_udcs.setMasterSid(masterSid);
	}

	/**
	* Sets the master type of this udcs.
	*
	* @param masterType the master type of this udcs
	*/
	@Override
	public void setMasterType(java.lang.String masterType) {
		_udcs.setMasterType(masterType);
	}

	@Override
	public void setNew(boolean n) {
		_udcs.setNew(n);
	}

	/**
	* Sets the primary key of this udcs.
	*
	* @param primaryKey the primary key of this udcs
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_udcs.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_udcs.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the udc1 of this udcs.
	*
	* @param udc1 the udc1 of this udcs
	*/
	@Override
	public void setUdc1(int udc1) {
		_udcs.setUdc1(udc1);
	}

	/**
	* Sets the udc10 of this udcs.
	*
	* @param udc10 the udc10 of this udcs
	*/
	@Override
	public void setUdc10(int udc10) {
		_udcs.setUdc10(udc10);
	}

	/**
	* Sets the udc11 of this udcs.
	*
	* @param udc11 the udc11 of this udcs
	*/
	@Override
	public void setUdc11(int udc11) {
		_udcs.setUdc11(udc11);
	}

	/**
	* Sets the udc12 of this udcs.
	*
	* @param udc12 the udc12 of this udcs
	*/
	@Override
	public void setUdc12(int udc12) {
		_udcs.setUdc12(udc12);
	}

	/**
	* Sets the udc2 of this udcs.
	*
	* @param udc2 the udc2 of this udcs
	*/
	@Override
	public void setUdc2(int udc2) {
		_udcs.setUdc2(udc2);
	}

	/**
	* Sets the udc3 of this udcs.
	*
	* @param udc3 the udc3 of this udcs
	*/
	@Override
	public void setUdc3(int udc3) {
		_udcs.setUdc3(udc3);
	}

	/**
	* Sets the udc4 of this udcs.
	*
	* @param udc4 the udc4 of this udcs
	*/
	@Override
	public void setUdc4(int udc4) {
		_udcs.setUdc4(udc4);
	}

	/**
	* Sets the udc5 of this udcs.
	*
	* @param udc5 the udc5 of this udcs
	*/
	@Override
	public void setUdc5(int udc5) {
		_udcs.setUdc5(udc5);
	}

	/**
	* Sets the udc6 of this udcs.
	*
	* @param udc6 the udc6 of this udcs
	*/
	@Override
	public void setUdc6(int udc6) {
		_udcs.setUdc6(udc6);
	}

	/**
	* Sets the udc7 of this udcs.
	*
	* @param udc7 the udc7 of this udcs
	*/
	@Override
	public void setUdc7(int udc7) {
		_udcs.setUdc7(udc7);
	}

	/**
	* Sets the udc8 of this udcs.
	*
	* @param udc8 the udc8 of this udcs
	*/
	@Override
	public void setUdc8(int udc8) {
		_udcs.setUdc8(udc8);
	}

	/**
	* Sets the udc9 of this udcs.
	*
	* @param udc9 the udc9 of this udcs
	*/
	@Override
	public void setUdc9(int udc9) {
		_udcs.setUdc9(udc9);
	}

	/**
	* Sets the udcs sid of this udcs.
	*
	* @param udcsSid the udcs sid of this udcs
	*/
	@Override
	public void setUdcsSid(int udcsSid) {
		_udcs.setUdcsSid(udcsSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Udcs> toCacheModel() {
		return _udcs.toCacheModel();
	}

	@Override
	public Udcs toEscapedModel() {
		return new UdcsWrapper(_udcs.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _udcs.toString();
	}

	@Override
	public Udcs toUnescapedModel() {
		return new UdcsWrapper(_udcs.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _udcs.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UdcsWrapper)) {
			return false;
		}

		UdcsWrapper udcsWrapper = (UdcsWrapper)obj;

		if (Objects.equals(_udcs, udcsWrapper._udcs)) {
			return true;
		}

		return false;
	}

	@Override
	public Udcs getWrappedModel() {
		return _udcs;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _udcs.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _udcs.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_udcs.resetOriginalValues();
	}

	private final Udcs _udcs;
}