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
 * This class is a wrapper for {@link CdrDetails}.
 * </p>
 *
 * @author
 * @see CdrDetails
 * @generated
 */
@ProviderType
public class CdrDetailsWrapper implements CdrDetails, ModelWrapper<CdrDetails> {
	public CdrDetailsWrapper(CdrDetails cdrDetails) {
		_cdrDetails = cdrDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return CdrDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CdrDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("lineType", getLineType());
		attributes.put("keyword", getKeyword());
		attributes.put("itemGroupMsAssociation", getItemGroupMsAssociation());
		attributes.put("value", getValue());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("logicalOperator", getLogicalOperator());
		attributes.put("operator", getOperator());
		attributes.put("cdrDetailsSid", getCdrDetailsSid());
		attributes.put("cdrModelSid", getCdrModelSid());
		attributes.put("comparison", getComparison());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String lineType = (String)attributes.get("lineType");

		if (lineType != null) {
			setLineType(lineType);
		}

		String keyword = (String)attributes.get("keyword");

		if (keyword != null) {
			setKeyword(keyword);
		}

		String itemGroupMsAssociation = (String)attributes.get(
				"itemGroupMsAssociation");

		if (itemGroupMsAssociation != null) {
			setItemGroupMsAssociation(itemGroupMsAssociation);
		}

		Double value = (Double)attributes.get("value");

		if (value != null) {
			setValue(value);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String logicalOperator = (String)attributes.get("logicalOperator");

		if (logicalOperator != null) {
			setLogicalOperator(logicalOperator);
		}

		String operator = (String)attributes.get("operator");

		if (operator != null) {
			setOperator(operator);
		}

		Integer cdrDetailsSid = (Integer)attributes.get("cdrDetailsSid");

		if (cdrDetailsSid != null) {
			setCdrDetailsSid(cdrDetailsSid);
		}

		Integer cdrModelSid = (Integer)attributes.get("cdrModelSid");

		if (cdrModelSid != null) {
			setCdrModelSid(cdrModelSid);
		}

		String comparison = (String)attributes.get("comparison");

		if (comparison != null) {
			setComparison(comparison);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CdrDetailsWrapper((CdrDetails)_cdrDetails.clone());
	}

	@Override
	public int compareTo(CdrDetails cdrDetails) {
		return _cdrDetails.compareTo(cdrDetails);
	}

	/**
	* Returns the cdr details sid of this cdr details.
	*
	* @return the cdr details sid of this cdr details
	*/
	@Override
	public int getCdrDetailsSid() {
		return _cdrDetails.getCdrDetailsSid();
	}

	/**
	* Returns the cdr model sid of this cdr details.
	*
	* @return the cdr model sid of this cdr details
	*/
	@Override
	public int getCdrModelSid() {
		return _cdrDetails.getCdrModelSid();
	}

	/**
	* Returns the comparison of this cdr details.
	*
	* @return the comparison of this cdr details
	*/
	@Override
	public java.lang.String getComparison() {
		return _cdrDetails.getComparison();
	}

	/**
	* Returns the created by of this cdr details.
	*
	* @return the created by of this cdr details
	*/
	@Override
	public int getCreatedBy() {
		return _cdrDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this cdr details.
	*
	* @return the created date of this cdr details
	*/
	@Override
	public Date getCreatedDate() {
		return _cdrDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cdrDetails.getExpandoBridge();
	}

	/**
	* Returns the item group ms association of this cdr details.
	*
	* @return the item group ms association of this cdr details
	*/
	@Override
	public java.lang.String getItemGroupMsAssociation() {
		return _cdrDetails.getItemGroupMsAssociation();
	}

	/**
	* Returns the keyword of this cdr details.
	*
	* @return the keyword of this cdr details
	*/
	@Override
	public java.lang.String getKeyword() {
		return _cdrDetails.getKeyword();
	}

	/**
	* Returns the line type of this cdr details.
	*
	* @return the line type of this cdr details
	*/
	@Override
	public java.lang.String getLineType() {
		return _cdrDetails.getLineType();
	}

	/**
	* Returns the logical operator of this cdr details.
	*
	* @return the logical operator of this cdr details
	*/
	@Override
	public java.lang.String getLogicalOperator() {
		return _cdrDetails.getLogicalOperator();
	}

	/**
	* Returns the modified by of this cdr details.
	*
	* @return the modified by of this cdr details
	*/
	@Override
	public int getModifiedBy() {
		return _cdrDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this cdr details.
	*
	* @return the modified date of this cdr details
	*/
	@Override
	public Date getModifiedDate() {
		return _cdrDetails.getModifiedDate();
	}

	/**
	* Returns the operator of this cdr details.
	*
	* @return the operator of this cdr details
	*/
	@Override
	public java.lang.String getOperator() {
		return _cdrDetails.getOperator();
	}

	/**
	* Returns the primary key of this cdr details.
	*
	* @return the primary key of this cdr details
	*/
	@Override
	public int getPrimaryKey() {
		return _cdrDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cdrDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the value of this cdr details.
	*
	* @return the value of this cdr details
	*/
	@Override
	public double getValue() {
		return _cdrDetails.getValue();
	}

	@Override
	public int hashCode() {
		return _cdrDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cdrDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cdrDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cdrDetails.isNew();
	}

	@Override
	public void persist() {
		_cdrDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cdrDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the cdr details sid of this cdr details.
	*
	* @param cdrDetailsSid the cdr details sid of this cdr details
	*/
	@Override
	public void setCdrDetailsSid(int cdrDetailsSid) {
		_cdrDetails.setCdrDetailsSid(cdrDetailsSid);
	}

	/**
	* Sets the cdr model sid of this cdr details.
	*
	* @param cdrModelSid the cdr model sid of this cdr details
	*/
	@Override
	public void setCdrModelSid(int cdrModelSid) {
		_cdrDetails.setCdrModelSid(cdrModelSid);
	}

	/**
	* Sets the comparison of this cdr details.
	*
	* @param comparison the comparison of this cdr details
	*/
	@Override
	public void setComparison(java.lang.String comparison) {
		_cdrDetails.setComparison(comparison);
	}

	/**
	* Sets the created by of this cdr details.
	*
	* @param createdBy the created by of this cdr details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_cdrDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this cdr details.
	*
	* @param createdDate the created date of this cdr details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_cdrDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cdrDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cdrDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cdrDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item group ms association of this cdr details.
	*
	* @param itemGroupMsAssociation the item group ms association of this cdr details
	*/
	@Override
	public void setItemGroupMsAssociation(
		java.lang.String itemGroupMsAssociation) {
		_cdrDetails.setItemGroupMsAssociation(itemGroupMsAssociation);
	}

	/**
	* Sets the keyword of this cdr details.
	*
	* @param keyword the keyword of this cdr details
	*/
	@Override
	public void setKeyword(java.lang.String keyword) {
		_cdrDetails.setKeyword(keyword);
	}

	/**
	* Sets the line type of this cdr details.
	*
	* @param lineType the line type of this cdr details
	*/
	@Override
	public void setLineType(java.lang.String lineType) {
		_cdrDetails.setLineType(lineType);
	}

	/**
	* Sets the logical operator of this cdr details.
	*
	* @param logicalOperator the logical operator of this cdr details
	*/
	@Override
	public void setLogicalOperator(java.lang.String logicalOperator) {
		_cdrDetails.setLogicalOperator(logicalOperator);
	}

	/**
	* Sets the modified by of this cdr details.
	*
	* @param modifiedBy the modified by of this cdr details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_cdrDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this cdr details.
	*
	* @param modifiedDate the modified date of this cdr details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cdrDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cdrDetails.setNew(n);
	}

	/**
	* Sets the operator of this cdr details.
	*
	* @param operator the operator of this cdr details
	*/
	@Override
	public void setOperator(java.lang.String operator) {
		_cdrDetails.setOperator(operator);
	}

	/**
	* Sets the primary key of this cdr details.
	*
	* @param primaryKey the primary key of this cdr details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cdrDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cdrDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the value of this cdr details.
	*
	* @param value the value of this cdr details
	*/
	@Override
	public void setValue(double value) {
		_cdrDetails.setValue(value);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CdrDetails> toCacheModel() {
		return _cdrDetails.toCacheModel();
	}

	@Override
	public CdrDetails toEscapedModel() {
		return new CdrDetailsWrapper(_cdrDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cdrDetails.toString();
	}

	@Override
	public CdrDetails toUnescapedModel() {
		return new CdrDetailsWrapper(_cdrDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cdrDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CdrDetailsWrapper)) {
			return false;
		}

		CdrDetailsWrapper cdrDetailsWrapper = (CdrDetailsWrapper)obj;

		if (Objects.equals(_cdrDetails, cdrDetailsWrapper._cdrDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public CdrDetails getWrappedModel() {
		return _cdrDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cdrDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cdrDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cdrDetails.resetOriginalValues();
	}

	private final CdrDetails _cdrDetails;
}