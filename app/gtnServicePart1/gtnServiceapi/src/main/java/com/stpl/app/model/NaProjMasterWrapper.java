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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link NaProjMaster}.
 * </p>
 *
 * @author
 * @see NaProjMaster
 * @generated
 */
@ProviderType
public class NaProjMasterWrapper implements NaProjMaster,
	ModelWrapper<NaProjMaster> {
	public NaProjMasterWrapper(NaProjMaster naProjMaster) {
		_naProjMaster = naProjMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return NaProjMaster.class;
	}

	@Override
	public String getModelClassName() {
		return NaProjMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("naProjName", getNaProjName());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("saveFlag", getSaveFlag());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("naProjMasterSid", getNaProjMasterSid());
		attributes.put("itemGroupSid", getItemGroupSid());
		attributes.put("therapeuticClass", getTherapeuticClass());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("businessUnit", getBusinessUnit());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String naProjName = (String)attributes.get("naProjName");

		if (naProjName != null) {
			setNaProjName(naProjName);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Boolean saveFlag = (Boolean)attributes.get("saveFlag");

		if (saveFlag != null) {
			setSaveFlag(saveFlag);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer naProjMasterSid = (Integer)attributes.get("naProjMasterSid");

		if (naProjMasterSid != null) {
			setNaProjMasterSid(naProjMasterSid);
		}

		Integer itemGroupSid = (Integer)attributes.get("itemGroupSid");

		if (itemGroupSid != null) {
			setItemGroupSid(itemGroupSid);
		}

		Integer therapeuticClass = (Integer)attributes.get("therapeuticClass");

		if (therapeuticClass != null) {
			setTherapeuticClass(therapeuticClass);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}

		Integer businessUnit = (Integer)attributes.get("businessUnit");

		if (businessUnit != null) {
			setBusinessUnit(businessUnit);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new NaProjMasterWrapper((NaProjMaster)_naProjMaster.clone());
	}

	@Override
	public int compareTo(NaProjMaster naProjMaster) {
		return _naProjMaster.compareTo(naProjMaster);
	}

	/**
	* Returns the business unit of this na proj master.
	*
	* @return the business unit of this na proj master
	*/
	@Override
	public int getBusinessUnit() {
		return _naProjMaster.getBusinessUnit();
	}

	/**
	* Returns the company master sid of this na proj master.
	*
	* @return the company master sid of this na proj master
	*/
	@Override
	public int getCompanyMasterSid() {
		return _naProjMaster.getCompanyMasterSid();
	}

	/**
	* Returns the created by of this na proj master.
	*
	* @return the created by of this na proj master
	*/
	@Override
	public int getCreatedBy() {
		return _naProjMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this na proj master.
	*
	* @return the created date of this na proj master
	*/
	@Override
	public Date getCreatedDate() {
		return _naProjMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _naProjMaster.getExpandoBridge();
	}

	/**
	* Returns the item group sid of this na proj master.
	*
	* @return the item group sid of this na proj master
	*/
	@Override
	public int getItemGroupSid() {
		return _naProjMaster.getItemGroupSid();
	}

	/**
	* Returns the modified by of this na proj master.
	*
	* @return the modified by of this na proj master
	*/
	@Override
	public int getModifiedBy() {
		return _naProjMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this na proj master.
	*
	* @return the modified date of this na proj master
	*/
	@Override
	public Date getModifiedDate() {
		return _naProjMaster.getModifiedDate();
	}

	/**
	* Returns the na proj master sid of this na proj master.
	*
	* @return the na proj master sid of this na proj master
	*/
	@Override
	public int getNaProjMasterSid() {
		return _naProjMaster.getNaProjMasterSid();
	}

	/**
	* Returns the na proj name of this na proj master.
	*
	* @return the na proj name of this na proj master
	*/
	@Override
	public java.lang.String getNaProjName() {
		return _naProjMaster.getNaProjName();
	}

	/**
	* Returns the primary key of this na proj master.
	*
	* @return the primary key of this na proj master
	*/
	@Override
	public int getPrimaryKey() {
		return _naProjMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _naProjMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the save flag of this na proj master.
	*
	* @return the save flag of this na proj master
	*/
	@Override
	public boolean getSaveFlag() {
		return _naProjMaster.getSaveFlag();
	}

	/**
	* Returns the therapeutic class of this na proj master.
	*
	* @return the therapeutic class of this na proj master
	*/
	@Override
	public int getTherapeuticClass() {
		return _naProjMaster.getTherapeuticClass();
	}

	@Override
	public int hashCode() {
		return _naProjMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _naProjMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _naProjMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _naProjMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this na proj master is save flag.
	*
	* @return <code>true</code> if this na proj master is save flag; <code>false</code> otherwise
	*/
	@Override
	public boolean isSaveFlag() {
		return _naProjMaster.isSaveFlag();
	}

	@Override
	public void persist() {
		_naProjMaster.persist();
	}

	/**
	* Sets the business unit of this na proj master.
	*
	* @param businessUnit the business unit of this na proj master
	*/
	@Override
	public void setBusinessUnit(int businessUnit) {
		_naProjMaster.setBusinessUnit(businessUnit);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_naProjMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the company master sid of this na proj master.
	*
	* @param companyMasterSid the company master sid of this na proj master
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_naProjMaster.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the created by of this na proj master.
	*
	* @param createdBy the created by of this na proj master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_naProjMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this na proj master.
	*
	* @param createdDate the created date of this na proj master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_naProjMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_naProjMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_naProjMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_naProjMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item group sid of this na proj master.
	*
	* @param itemGroupSid the item group sid of this na proj master
	*/
	@Override
	public void setItemGroupSid(int itemGroupSid) {
		_naProjMaster.setItemGroupSid(itemGroupSid);
	}

	/**
	* Sets the modified by of this na proj master.
	*
	* @param modifiedBy the modified by of this na proj master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_naProjMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this na proj master.
	*
	* @param modifiedDate the modified date of this na proj master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_naProjMaster.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the na proj master sid of this na proj master.
	*
	* @param naProjMasterSid the na proj master sid of this na proj master
	*/
	@Override
	public void setNaProjMasterSid(int naProjMasterSid) {
		_naProjMaster.setNaProjMasterSid(naProjMasterSid);
	}

	/**
	* Sets the na proj name of this na proj master.
	*
	* @param naProjName the na proj name of this na proj master
	*/
	@Override
	public void setNaProjName(java.lang.String naProjName) {
		_naProjMaster.setNaProjName(naProjName);
	}

	@Override
	public void setNew(boolean n) {
		_naProjMaster.setNew(n);
	}

	/**
	* Sets the primary key of this na proj master.
	*
	* @param primaryKey the primary key of this na proj master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_naProjMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_naProjMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this na proj master is save flag.
	*
	* @param saveFlag the save flag of this na proj master
	*/
	@Override
	public void setSaveFlag(boolean saveFlag) {
		_naProjMaster.setSaveFlag(saveFlag);
	}

	/**
	* Sets the therapeutic class of this na proj master.
	*
	* @param therapeuticClass the therapeutic class of this na proj master
	*/
	@Override
	public void setTherapeuticClass(int therapeuticClass) {
		_naProjMaster.setTherapeuticClass(therapeuticClass);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NaProjMaster> toCacheModel() {
		return _naProjMaster.toCacheModel();
	}

	@Override
	public NaProjMaster toEscapedModel() {
		return new NaProjMasterWrapper(_naProjMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _naProjMaster.toString();
	}

	@Override
	public NaProjMaster toUnescapedModel() {
		return new NaProjMasterWrapper(_naProjMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _naProjMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NaProjMasterWrapper)) {
			return false;
		}

		NaProjMasterWrapper naProjMasterWrapper = (NaProjMasterWrapper)obj;

		if (Objects.equals(_naProjMaster, naProjMasterWrapper._naProjMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public NaProjMaster getWrappedModel() {
		return _naProjMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _naProjMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _naProjMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_naProjMaster.resetOriginalValues();
	}

	private final NaProjMaster _naProjMaster;
}