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
 * This class is a wrapper for {@link GcmContractDetails}.
 * </p>
 *
 * @author
 * @see GcmContractDetails
 * @generated
 */
@ProviderType
public class GcmContractDetailsWrapper implements GcmContractDetails,
	ModelWrapper<GcmContractDetails> {
	public GcmContractDetailsWrapper(GcmContractDetails gcmContractDetails) {
		_gcmContractDetails = gcmContractDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return GcmContractDetails.class;
	}

	@Override
	public String getModelClassName() {
		return GcmContractDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("paymentMethod", getPaymentMethod());
		attributes.put("userId", getUserId());
		attributes.put("endDate", getEndDate());
		attributes.put("paymentFrequency", getPaymentFrequency());
		attributes.put("gcmContractDetailsSid", getGcmContractDetailsSid());
		attributes.put("componentId", getComponentId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("componentName", getComponentName());
		attributes.put("rsCalendar", getRsCalendar());
		attributes.put("fileName", getFileName());
		attributes.put("startDate", getStartDate());
		attributes.put("planLevel", getPlanLevel());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("componentNo", getComponentNo());
		attributes.put("programType", getProgramType());
		attributes.put("sessionId", getSessionId());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("componentStatus", getComponentStatus());
		attributes.put("componentType", getComponentType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String paymentMethod = (String)attributes.get("paymentMethod");

		if (paymentMethod != null) {
			setPaymentMethod(paymentMethod);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String paymentFrequency = (String)attributes.get("paymentFrequency");

		if (paymentFrequency != null) {
			setPaymentFrequency(paymentFrequency);
		}

		Integer gcmContractDetailsSid = (Integer)attributes.get(
				"gcmContractDetailsSid");

		if (gcmContractDetailsSid != null) {
			setGcmContractDetailsSid(gcmContractDetailsSid);
		}

		String componentId = (String)attributes.get("componentId");

		if (componentId != null) {
			setComponentId(componentId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String componentName = (String)attributes.get("componentName");

		if (componentName != null) {
			setComponentName(componentName);
		}

		String rsCalendar = (String)attributes.get("rsCalendar");

		if (rsCalendar != null) {
			setRsCalendar(rsCalendar);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		String planLevel = (String)attributes.get("planLevel");

		if (planLevel != null) {
			setPlanLevel(planLevel);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String componentNo = (String)attributes.get("componentNo");

		if (componentNo != null) {
			setComponentNo(componentNo);
		}

		String programType = (String)attributes.get("programType");

		if (programType != null) {
			setProgramType(programType);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String componentStatus = (String)attributes.get("componentStatus");

		if (componentStatus != null) {
			setComponentStatus(componentStatus);
		}

		String componentType = (String)attributes.get("componentType");

		if (componentType != null) {
			setComponentType(componentType);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new GcmContractDetailsWrapper((GcmContractDetails)_gcmContractDetails.clone());
	}

	@Override
	public int compareTo(GcmContractDetails gcmContractDetails) {
		return _gcmContractDetails.compareTo(gcmContractDetails);
	}

	/**
	* Returns the component ID of this gcm contract details.
	*
	* @return the component ID of this gcm contract details
	*/
	@Override
	public java.lang.String getComponentId() {
		return _gcmContractDetails.getComponentId();
	}

	/**
	* Returns the component name of this gcm contract details.
	*
	* @return the component name of this gcm contract details
	*/
	@Override
	public java.lang.String getComponentName() {
		return _gcmContractDetails.getComponentName();
	}

	/**
	* Returns the component no of this gcm contract details.
	*
	* @return the component no of this gcm contract details
	*/
	@Override
	public java.lang.String getComponentNo() {
		return _gcmContractDetails.getComponentNo();
	}

	/**
	* Returns the component status of this gcm contract details.
	*
	* @return the component status of this gcm contract details
	*/
	@Override
	public java.lang.String getComponentStatus() {
		return _gcmContractDetails.getComponentStatus();
	}

	/**
	* Returns the component type of this gcm contract details.
	*
	* @return the component type of this gcm contract details
	*/
	@Override
	public java.lang.String getComponentType() {
		return _gcmContractDetails.getComponentType();
	}

	/**
	* Returns the created by of this gcm contract details.
	*
	* @return the created by of this gcm contract details
	*/
	@Override
	public int getCreatedBy() {
		return _gcmContractDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this gcm contract details.
	*
	* @return the created date of this gcm contract details
	*/
	@Override
	public Date getCreatedDate() {
		return _gcmContractDetails.getCreatedDate();
	}

	/**
	* Returns the end date of this gcm contract details.
	*
	* @return the end date of this gcm contract details
	*/
	@Override
	public Date getEndDate() {
		return _gcmContractDetails.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _gcmContractDetails.getExpandoBridge();
	}

	/**
	* Returns the file name of this gcm contract details.
	*
	* @return the file name of this gcm contract details
	*/
	@Override
	public java.lang.String getFileName() {
		return _gcmContractDetails.getFileName();
	}

	/**
	* Returns the gcm contract details sid of this gcm contract details.
	*
	* @return the gcm contract details sid of this gcm contract details
	*/
	@Override
	public int getGcmContractDetailsSid() {
		return _gcmContractDetails.getGcmContractDetailsSid();
	}

	/**
	* Returns the modified by of this gcm contract details.
	*
	* @return the modified by of this gcm contract details
	*/
	@Override
	public int getModifiedBy() {
		return _gcmContractDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this gcm contract details.
	*
	* @return the modified date of this gcm contract details
	*/
	@Override
	public Date getModifiedDate() {
		return _gcmContractDetails.getModifiedDate();
	}

	/**
	* Returns the payment frequency of this gcm contract details.
	*
	* @return the payment frequency of this gcm contract details
	*/
	@Override
	public java.lang.String getPaymentFrequency() {
		return _gcmContractDetails.getPaymentFrequency();
	}

	/**
	* Returns the payment method of this gcm contract details.
	*
	* @return the payment method of this gcm contract details
	*/
	@Override
	public java.lang.String getPaymentMethod() {
		return _gcmContractDetails.getPaymentMethod();
	}

	/**
	* Returns the plan level of this gcm contract details.
	*
	* @return the plan level of this gcm contract details
	*/
	@Override
	public java.lang.String getPlanLevel() {
		return _gcmContractDetails.getPlanLevel();
	}

	/**
	* Returns the primary key of this gcm contract details.
	*
	* @return the primary key of this gcm contract details
	*/
	@Override
	public int getPrimaryKey() {
		return _gcmContractDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _gcmContractDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the program type of this gcm contract details.
	*
	* @return the program type of this gcm contract details
	*/
	@Override
	public java.lang.String getProgramType() {
		return _gcmContractDetails.getProgramType();
	}

	/**
	* Returns the rs calendar of this gcm contract details.
	*
	* @return the rs calendar of this gcm contract details
	*/
	@Override
	public java.lang.String getRsCalendar() {
		return _gcmContractDetails.getRsCalendar();
	}

	/**
	* Returns the session ID of this gcm contract details.
	*
	* @return the session ID of this gcm contract details
	*/
	@Override
	public java.lang.String getSessionId() {
		return _gcmContractDetails.getSessionId();
	}

	/**
	* Returns the start date of this gcm contract details.
	*
	* @return the start date of this gcm contract details
	*/
	@Override
	public Date getStartDate() {
		return _gcmContractDetails.getStartDate();
	}

	/**
	* Returns the user ID of this gcm contract details.
	*
	* @return the user ID of this gcm contract details
	*/
	@Override
	public int getUserId() {
		return _gcmContractDetails.getUserId();
	}

	@Override
	public int hashCode() {
		return _gcmContractDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _gcmContractDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _gcmContractDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _gcmContractDetails.isNew();
	}

	@Override
	public void persist() {
		_gcmContractDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_gcmContractDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the component ID of this gcm contract details.
	*
	* @param componentId the component ID of this gcm contract details
	*/
	@Override
	public void setComponentId(java.lang.String componentId) {
		_gcmContractDetails.setComponentId(componentId);
	}

	/**
	* Sets the component name of this gcm contract details.
	*
	* @param componentName the component name of this gcm contract details
	*/
	@Override
	public void setComponentName(java.lang.String componentName) {
		_gcmContractDetails.setComponentName(componentName);
	}

	/**
	* Sets the component no of this gcm contract details.
	*
	* @param componentNo the component no of this gcm contract details
	*/
	@Override
	public void setComponentNo(java.lang.String componentNo) {
		_gcmContractDetails.setComponentNo(componentNo);
	}

	/**
	* Sets the component status of this gcm contract details.
	*
	* @param componentStatus the component status of this gcm contract details
	*/
	@Override
	public void setComponentStatus(java.lang.String componentStatus) {
		_gcmContractDetails.setComponentStatus(componentStatus);
	}

	/**
	* Sets the component type of this gcm contract details.
	*
	* @param componentType the component type of this gcm contract details
	*/
	@Override
	public void setComponentType(java.lang.String componentType) {
		_gcmContractDetails.setComponentType(componentType);
	}

	/**
	* Sets the created by of this gcm contract details.
	*
	* @param createdBy the created by of this gcm contract details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_gcmContractDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this gcm contract details.
	*
	* @param createdDate the created date of this gcm contract details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_gcmContractDetails.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this gcm contract details.
	*
	* @param endDate the end date of this gcm contract details
	*/
	@Override
	public void setEndDate(Date endDate) {
		_gcmContractDetails.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_gcmContractDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_gcmContractDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_gcmContractDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file name of this gcm contract details.
	*
	* @param fileName the file name of this gcm contract details
	*/
	@Override
	public void setFileName(java.lang.String fileName) {
		_gcmContractDetails.setFileName(fileName);
	}

	/**
	* Sets the gcm contract details sid of this gcm contract details.
	*
	* @param gcmContractDetailsSid the gcm contract details sid of this gcm contract details
	*/
	@Override
	public void setGcmContractDetailsSid(int gcmContractDetailsSid) {
		_gcmContractDetails.setGcmContractDetailsSid(gcmContractDetailsSid);
	}

	/**
	* Sets the modified by of this gcm contract details.
	*
	* @param modifiedBy the modified by of this gcm contract details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_gcmContractDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this gcm contract details.
	*
	* @param modifiedDate the modified date of this gcm contract details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_gcmContractDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_gcmContractDetails.setNew(n);
	}

	/**
	* Sets the payment frequency of this gcm contract details.
	*
	* @param paymentFrequency the payment frequency of this gcm contract details
	*/
	@Override
	public void setPaymentFrequency(java.lang.String paymentFrequency) {
		_gcmContractDetails.setPaymentFrequency(paymentFrequency);
	}

	/**
	* Sets the payment method of this gcm contract details.
	*
	* @param paymentMethod the payment method of this gcm contract details
	*/
	@Override
	public void setPaymentMethod(java.lang.String paymentMethod) {
		_gcmContractDetails.setPaymentMethod(paymentMethod);
	}

	/**
	* Sets the plan level of this gcm contract details.
	*
	* @param planLevel the plan level of this gcm contract details
	*/
	@Override
	public void setPlanLevel(java.lang.String planLevel) {
		_gcmContractDetails.setPlanLevel(planLevel);
	}

	/**
	* Sets the primary key of this gcm contract details.
	*
	* @param primaryKey the primary key of this gcm contract details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_gcmContractDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_gcmContractDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the program type of this gcm contract details.
	*
	* @param programType the program type of this gcm contract details
	*/
	@Override
	public void setProgramType(java.lang.String programType) {
		_gcmContractDetails.setProgramType(programType);
	}

	/**
	* Sets the rs calendar of this gcm contract details.
	*
	* @param rsCalendar the rs calendar of this gcm contract details
	*/
	@Override
	public void setRsCalendar(java.lang.String rsCalendar) {
		_gcmContractDetails.setRsCalendar(rsCalendar);
	}

	/**
	* Sets the session ID of this gcm contract details.
	*
	* @param sessionId the session ID of this gcm contract details
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_gcmContractDetails.setSessionId(sessionId);
	}

	/**
	* Sets the start date of this gcm contract details.
	*
	* @param startDate the start date of this gcm contract details
	*/
	@Override
	public void setStartDate(Date startDate) {
		_gcmContractDetails.setStartDate(startDate);
	}

	/**
	* Sets the user ID of this gcm contract details.
	*
	* @param userId the user ID of this gcm contract details
	*/
	@Override
	public void setUserId(int userId) {
		_gcmContractDetails.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<GcmContractDetails> toCacheModel() {
		return _gcmContractDetails.toCacheModel();
	}

	@Override
	public GcmContractDetails toEscapedModel() {
		return new GcmContractDetailsWrapper(_gcmContractDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _gcmContractDetails.toString();
	}

	@Override
	public GcmContractDetails toUnescapedModel() {
		return new GcmContractDetailsWrapper(_gcmContractDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _gcmContractDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GcmContractDetailsWrapper)) {
			return false;
		}

		GcmContractDetailsWrapper gcmContractDetailsWrapper = (GcmContractDetailsWrapper)obj;

		if (Objects.equals(_gcmContractDetails,
					gcmContractDetailsWrapper._gcmContractDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public GcmContractDetails getWrappedModel() {
		return _gcmContractDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _gcmContractDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _gcmContractDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_gcmContractDetails.resetOriginalValues();
	}

	private final GcmContractDetails _gcmContractDetails;
}