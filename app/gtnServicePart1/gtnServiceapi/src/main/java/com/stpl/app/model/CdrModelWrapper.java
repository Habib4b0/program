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
 * This class is a wrapper for {@link CdrModel}.
 * </p>
 *
 * @author
 * @see CdrModel
 * @generated
 */
@ProviderType
public class CdrModelWrapper implements CdrModel, ModelWrapper<CdrModel> {
	public CdrModelWrapper(CdrModel cdrModel) {
		_cdrModel = cdrModel;
	}

	@Override
	public Class<?> getModelClass() {
		return CdrModel.class;
	}

	@Override
	public String getModelClassName() {
		return CdrModel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("ruleCategory", getRuleCategory());
		attributes.put("ruleType", getRuleType());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("internalNotes", getInternalNotes());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("ruleName", getRuleName());
		attributes.put("cdrModelSid", getCdrModelSid());
		attributes.put("ruleNo", getRuleNo());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer ruleCategory = (Integer)attributes.get("ruleCategory");

		if (ruleCategory != null) {
			setRuleCategory(ruleCategory);
		}

		Integer ruleType = (Integer)attributes.get("ruleType");

		if (ruleType != null) {
			setRuleType(ruleType);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String internalNotes = (String)attributes.get("internalNotes");

		if (internalNotes != null) {
			setInternalNotes(internalNotes);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String ruleName = (String)attributes.get("ruleName");

		if (ruleName != null) {
			setRuleName(ruleName);
		}

		Integer cdrModelSid = (Integer)attributes.get("cdrModelSid");

		if (cdrModelSid != null) {
			setCdrModelSid(cdrModelSid);
		}

		String ruleNo = (String)attributes.get("ruleNo");

		if (ruleNo != null) {
			setRuleNo(ruleNo);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CdrModelWrapper((CdrModel)_cdrModel.clone());
	}

	@Override
	public int compareTo(CdrModel cdrModel) {
		return _cdrModel.compareTo(cdrModel);
	}

	/**
	* Returns the cdr model sid of this cdr model.
	*
	* @return the cdr model sid of this cdr model
	*/
	@Override
	public int getCdrModelSid() {
		return _cdrModel.getCdrModelSid();
	}

	/**
	* Returns the created by of this cdr model.
	*
	* @return the created by of this cdr model
	*/
	@Override
	public int getCreatedBy() {
		return _cdrModel.getCreatedBy();
	}

	/**
	* Returns the created date of this cdr model.
	*
	* @return the created date of this cdr model
	*/
	@Override
	public Date getCreatedDate() {
		return _cdrModel.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cdrModel.getExpandoBridge();
	}

	/**
	* Returns the internal notes of this cdr model.
	*
	* @return the internal notes of this cdr model
	*/
	@Override
	public java.lang.String getInternalNotes() {
		return _cdrModel.getInternalNotes();
	}

	/**
	* Returns the modified by of this cdr model.
	*
	* @return the modified by of this cdr model
	*/
	@Override
	public int getModifiedBy() {
		return _cdrModel.getModifiedBy();
	}

	/**
	* Returns the modified date of this cdr model.
	*
	* @return the modified date of this cdr model
	*/
	@Override
	public Date getModifiedDate() {
		return _cdrModel.getModifiedDate();
	}

	/**
	* Returns the primary key of this cdr model.
	*
	* @return the primary key of this cdr model
	*/
	@Override
	public int getPrimaryKey() {
		return _cdrModel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cdrModel.getPrimaryKeyObj();
	}

	/**
	* Returns the rule category of this cdr model.
	*
	* @return the rule category of this cdr model
	*/
	@Override
	public int getRuleCategory() {
		return _cdrModel.getRuleCategory();
	}

	/**
	* Returns the rule name of this cdr model.
	*
	* @return the rule name of this cdr model
	*/
	@Override
	public java.lang.String getRuleName() {
		return _cdrModel.getRuleName();
	}

	/**
	* Returns the rule no of this cdr model.
	*
	* @return the rule no of this cdr model
	*/
	@Override
	public java.lang.String getRuleNo() {
		return _cdrModel.getRuleNo();
	}

	/**
	* Returns the rule type of this cdr model.
	*
	* @return the rule type of this cdr model
	*/
	@Override
	public int getRuleType() {
		return _cdrModel.getRuleType();
	}

	@Override
	public int hashCode() {
		return _cdrModel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cdrModel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cdrModel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cdrModel.isNew();
	}

	@Override
	public void persist() {
		_cdrModel.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cdrModel.setCachedModel(cachedModel);
	}

	/**
	* Sets the cdr model sid of this cdr model.
	*
	* @param cdrModelSid the cdr model sid of this cdr model
	*/
	@Override
	public void setCdrModelSid(int cdrModelSid) {
		_cdrModel.setCdrModelSid(cdrModelSid);
	}

	/**
	* Sets the created by of this cdr model.
	*
	* @param createdBy the created by of this cdr model
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_cdrModel.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this cdr model.
	*
	* @param createdDate the created date of this cdr model
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_cdrModel.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cdrModel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cdrModel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cdrModel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the internal notes of this cdr model.
	*
	* @param internalNotes the internal notes of this cdr model
	*/
	@Override
	public void setInternalNotes(java.lang.String internalNotes) {
		_cdrModel.setInternalNotes(internalNotes);
	}

	/**
	* Sets the modified by of this cdr model.
	*
	* @param modifiedBy the modified by of this cdr model
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_cdrModel.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this cdr model.
	*
	* @param modifiedDate the modified date of this cdr model
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cdrModel.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cdrModel.setNew(n);
	}

	/**
	* Sets the primary key of this cdr model.
	*
	* @param primaryKey the primary key of this cdr model
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cdrModel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cdrModel.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rule category of this cdr model.
	*
	* @param ruleCategory the rule category of this cdr model
	*/
	@Override
	public void setRuleCategory(int ruleCategory) {
		_cdrModel.setRuleCategory(ruleCategory);
	}

	/**
	* Sets the rule name of this cdr model.
	*
	* @param ruleName the rule name of this cdr model
	*/
	@Override
	public void setRuleName(java.lang.String ruleName) {
		_cdrModel.setRuleName(ruleName);
	}

	/**
	* Sets the rule no of this cdr model.
	*
	* @param ruleNo the rule no of this cdr model
	*/
	@Override
	public void setRuleNo(java.lang.String ruleNo) {
		_cdrModel.setRuleNo(ruleNo);
	}

	/**
	* Sets the rule type of this cdr model.
	*
	* @param ruleType the rule type of this cdr model
	*/
	@Override
	public void setRuleType(int ruleType) {
		_cdrModel.setRuleType(ruleType);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CdrModel> toCacheModel() {
		return _cdrModel.toCacheModel();
	}

	@Override
	public CdrModel toEscapedModel() {
		return new CdrModelWrapper(_cdrModel.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cdrModel.toString();
	}

	@Override
	public CdrModel toUnescapedModel() {
		return new CdrModelWrapper(_cdrModel.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cdrModel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CdrModelWrapper)) {
			return false;
		}

		CdrModelWrapper cdrModelWrapper = (CdrModelWrapper)obj;

		if (Objects.equals(_cdrModel, cdrModelWrapper._cdrModel)) {
			return true;
		}

		return false;
	}

	@Override
	public CdrModel getWrappedModel() {
		return _cdrModel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cdrModel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cdrModel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cdrModel.resetOriginalValues();
	}

	private final CdrModel _cdrModel;
}