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
 * This class is a wrapper for {@link AuditMasterInbound}.
 * </p>
 *
 * @author
 * @see AuditMasterInbound
 * @generated
 */
@ProviderType
public class AuditMasterInboundWrapper implements AuditMasterInbound,
	ModelWrapper<AuditMasterInbound> {
	public AuditMasterInboundWrapper(AuditMasterInbound auditMasterInbound) {
		_auditMasterInbound = auditMasterInbound;
	}

	@Override
	public Class<?> getModelClass() {
		return AuditMasterInbound.class;
	}

	@Override
	public String getModelClassName() {
		return AuditMasterInbound.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("receivedRecordAmountAttr", getReceivedRecordAmountAttr());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("interfaceRunEndDate", getInterfaceRunEndDate());
		attributes.put("applicationProcess", getApplicationProcess());
		attributes.put("discrepancyAmount", getDiscrepancyAmount());
		attributes.put("batchId", getBatchId());
		attributes.put("fileName", getFileName());
		attributes.put("sentRecordAmountAttribute",
			getSentRecordAmountAttribute());
		attributes.put("status", getStatus());
		attributes.put("receivedRecordAmount", getReceivedRecordAmount());
		attributes.put("validRecordAmount", getValidRecordAmount());
		attributes.put("invalidRecordCount", getInvalidRecordCount());
		attributes.put("receivedRecordCount", getReceivedRecordCount());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("changeCount", getChangeCount());
		attributes.put("unprocessedRecords", getUnprocessedRecords());
		attributes.put("deleteCount", getDeleteCount());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("auditInboundSid", getAuditInboundSid());
		attributes.put("sentRecordAmount", getSentRecordAmount());
		attributes.put("sentRecordCount", getSentRecordCount());
		attributes.put("addCount", getAddCount());
		attributes.put("source", getSource());
		attributes.put("invalidRecordAmount", getInvalidRecordAmount());
		attributes.put("interfaceRunStartDate", getInterfaceRunStartDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String receivedRecordAmountAttr = (String)attributes.get(
				"receivedRecordAmountAttr");

		if (receivedRecordAmountAttr != null) {
			setReceivedRecordAmountAttr(receivedRecordAmountAttr);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Date interfaceRunEndDate = (Date)attributes.get("interfaceRunEndDate");

		if (interfaceRunEndDate != null) {
			setInterfaceRunEndDate(interfaceRunEndDate);
		}

		String applicationProcess = (String)attributes.get("applicationProcess");

		if (applicationProcess != null) {
			setApplicationProcess(applicationProcess);
		}

		Double discrepancyAmount = (Double)attributes.get("discrepancyAmount");

		if (discrepancyAmount != null) {
			setDiscrepancyAmount(discrepancyAmount);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		String sentRecordAmountAttribute = (String)attributes.get(
				"sentRecordAmountAttribute");

		if (sentRecordAmountAttribute != null) {
			setSentRecordAmountAttribute(sentRecordAmountAttribute);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Double receivedRecordAmount = (Double)attributes.get(
				"receivedRecordAmount");

		if (receivedRecordAmount != null) {
			setReceivedRecordAmount(receivedRecordAmount);
		}

		Double validRecordAmount = (Double)attributes.get("validRecordAmount");

		if (validRecordAmount != null) {
			setValidRecordAmount(validRecordAmount);
		}

		Integer invalidRecordCount = (Integer)attributes.get(
				"invalidRecordCount");

		if (invalidRecordCount != null) {
			setInvalidRecordCount(invalidRecordCount);
		}

		Integer receivedRecordCount = (Integer)attributes.get(
				"receivedRecordCount");

		if (receivedRecordCount != null) {
			setReceivedRecordCount(receivedRecordCount);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer changeCount = (Integer)attributes.get("changeCount");

		if (changeCount != null) {
			setChangeCount(changeCount);
		}

		String unprocessedRecords = (String)attributes.get("unprocessedRecords");

		if (unprocessedRecords != null) {
			setUnprocessedRecords(unprocessedRecords);
		}

		Integer deleteCount = (Integer)attributes.get("deleteCount");

		if (deleteCount != null) {
			setDeleteCount(deleteCount);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer auditInboundSid = (Integer)attributes.get("auditInboundSid");

		if (auditInboundSid != null) {
			setAuditInboundSid(auditInboundSid);
		}

		Double sentRecordAmount = (Double)attributes.get("sentRecordAmount");

		if (sentRecordAmount != null) {
			setSentRecordAmount(sentRecordAmount);
		}

		Integer sentRecordCount = (Integer)attributes.get("sentRecordCount");

		if (sentRecordCount != null) {
			setSentRecordCount(sentRecordCount);
		}

		Integer addCount = (Integer)attributes.get("addCount");

		if (addCount != null) {
			setAddCount(addCount);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Double invalidRecordAmount = (Double)attributes.get(
				"invalidRecordAmount");

		if (invalidRecordAmount != null) {
			setInvalidRecordAmount(invalidRecordAmount);
		}

		Date interfaceRunStartDate = (Date)attributes.get(
				"interfaceRunStartDate");

		if (interfaceRunStartDate != null) {
			setInterfaceRunStartDate(interfaceRunStartDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AuditMasterInboundWrapper((AuditMasterInbound)_auditMasterInbound.clone());
	}

	@Override
	public int compareTo(AuditMasterInbound auditMasterInbound) {
		return _auditMasterInbound.compareTo(auditMasterInbound);
	}

	/**
	* Returns the add count of this audit master inbound.
	*
	* @return the add count of this audit master inbound
	*/
	@Override
	public int getAddCount() {
		return _auditMasterInbound.getAddCount();
	}

	/**
	* Returns the application process of this audit master inbound.
	*
	* @return the application process of this audit master inbound
	*/
	@Override
	public java.lang.String getApplicationProcess() {
		return _auditMasterInbound.getApplicationProcess();
	}

	/**
	* Returns the audit inbound sid of this audit master inbound.
	*
	* @return the audit inbound sid of this audit master inbound
	*/
	@Override
	public int getAuditInboundSid() {
		return _auditMasterInbound.getAuditInboundSid();
	}

	/**
	* Returns the batch ID of this audit master inbound.
	*
	* @return the batch ID of this audit master inbound
	*/
	@Override
	public java.lang.String getBatchId() {
		return _auditMasterInbound.getBatchId();
	}

	/**
	* Returns the change count of this audit master inbound.
	*
	* @return the change count of this audit master inbound
	*/
	@Override
	public int getChangeCount() {
		return _auditMasterInbound.getChangeCount();
	}

	/**
	* Returns the created by of this audit master inbound.
	*
	* @return the created by of this audit master inbound
	*/
	@Override
	public int getCreatedBy() {
		return _auditMasterInbound.getCreatedBy();
	}

	/**
	* Returns the created date of this audit master inbound.
	*
	* @return the created date of this audit master inbound
	*/
	@Override
	public Date getCreatedDate() {
		return _auditMasterInbound.getCreatedDate();
	}

	/**
	* Returns the delete count of this audit master inbound.
	*
	* @return the delete count of this audit master inbound
	*/
	@Override
	public int getDeleteCount() {
		return _auditMasterInbound.getDeleteCount();
	}

	/**
	* Returns the discrepancy amount of this audit master inbound.
	*
	* @return the discrepancy amount of this audit master inbound
	*/
	@Override
	public double getDiscrepancyAmount() {
		return _auditMasterInbound.getDiscrepancyAmount();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _auditMasterInbound.getExpandoBridge();
	}

	/**
	* Returns the file name of this audit master inbound.
	*
	* @return the file name of this audit master inbound
	*/
	@Override
	public java.lang.String getFileName() {
		return _auditMasterInbound.getFileName();
	}

	/**
	* Returns the interface run end date of this audit master inbound.
	*
	* @return the interface run end date of this audit master inbound
	*/
	@Override
	public Date getInterfaceRunEndDate() {
		return _auditMasterInbound.getInterfaceRunEndDate();
	}

	/**
	* Returns the interface run start date of this audit master inbound.
	*
	* @return the interface run start date of this audit master inbound
	*/
	@Override
	public Date getInterfaceRunStartDate() {
		return _auditMasterInbound.getInterfaceRunStartDate();
	}

	/**
	* Returns the invalid record amount of this audit master inbound.
	*
	* @return the invalid record amount of this audit master inbound
	*/
	@Override
	public double getInvalidRecordAmount() {
		return _auditMasterInbound.getInvalidRecordAmount();
	}

	/**
	* Returns the invalid record count of this audit master inbound.
	*
	* @return the invalid record count of this audit master inbound
	*/
	@Override
	public int getInvalidRecordCount() {
		return _auditMasterInbound.getInvalidRecordCount();
	}

	/**
	* Returns the modified by of this audit master inbound.
	*
	* @return the modified by of this audit master inbound
	*/
	@Override
	public int getModifiedBy() {
		return _auditMasterInbound.getModifiedBy();
	}

	/**
	* Returns the modified date of this audit master inbound.
	*
	* @return the modified date of this audit master inbound
	*/
	@Override
	public Date getModifiedDate() {
		return _auditMasterInbound.getModifiedDate();
	}

	/**
	* Returns the primary key of this audit master inbound.
	*
	* @return the primary key of this audit master inbound
	*/
	@Override
	public int getPrimaryKey() {
		return _auditMasterInbound.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _auditMasterInbound.getPrimaryKeyObj();
	}

	/**
	* Returns the received record amount of this audit master inbound.
	*
	* @return the received record amount of this audit master inbound
	*/
	@Override
	public double getReceivedRecordAmount() {
		return _auditMasterInbound.getReceivedRecordAmount();
	}

	/**
	* Returns the received record amount attr of this audit master inbound.
	*
	* @return the received record amount attr of this audit master inbound
	*/
	@Override
	public java.lang.String getReceivedRecordAmountAttr() {
		return _auditMasterInbound.getReceivedRecordAmountAttr();
	}

	/**
	* Returns the received record count of this audit master inbound.
	*
	* @return the received record count of this audit master inbound
	*/
	@Override
	public int getReceivedRecordCount() {
		return _auditMasterInbound.getReceivedRecordCount();
	}

	/**
	* Returns the sent record amount of this audit master inbound.
	*
	* @return the sent record amount of this audit master inbound
	*/
	@Override
	public double getSentRecordAmount() {
		return _auditMasterInbound.getSentRecordAmount();
	}

	/**
	* Returns the sent record amount attribute of this audit master inbound.
	*
	* @return the sent record amount attribute of this audit master inbound
	*/
	@Override
	public java.lang.String getSentRecordAmountAttribute() {
		return _auditMasterInbound.getSentRecordAmountAttribute();
	}

	/**
	* Returns the sent record count of this audit master inbound.
	*
	* @return the sent record count of this audit master inbound
	*/
	@Override
	public int getSentRecordCount() {
		return _auditMasterInbound.getSentRecordCount();
	}

	/**
	* Returns the source of this audit master inbound.
	*
	* @return the source of this audit master inbound
	*/
	@Override
	public java.lang.String getSource() {
		return _auditMasterInbound.getSource();
	}

	/**
	* Returns the status of this audit master inbound.
	*
	* @return the status of this audit master inbound
	*/
	@Override
	public java.lang.String getStatus() {
		return _auditMasterInbound.getStatus();
	}

	/**
	* Returns the unprocessed records of this audit master inbound.
	*
	* @return the unprocessed records of this audit master inbound
	*/
	@Override
	public java.lang.String getUnprocessedRecords() {
		return _auditMasterInbound.getUnprocessedRecords();
	}

	/**
	* Returns the valid record amount of this audit master inbound.
	*
	* @return the valid record amount of this audit master inbound
	*/
	@Override
	public double getValidRecordAmount() {
		return _auditMasterInbound.getValidRecordAmount();
	}

	@Override
	public int hashCode() {
		return _auditMasterInbound.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _auditMasterInbound.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _auditMasterInbound.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _auditMasterInbound.isNew();
	}

	@Override
	public void persist() {
		_auditMasterInbound.persist();
	}

	/**
	* Sets the add count of this audit master inbound.
	*
	* @param addCount the add count of this audit master inbound
	*/
	@Override
	public void setAddCount(int addCount) {
		_auditMasterInbound.setAddCount(addCount);
	}

	/**
	* Sets the application process of this audit master inbound.
	*
	* @param applicationProcess the application process of this audit master inbound
	*/
	@Override
	public void setApplicationProcess(java.lang.String applicationProcess) {
		_auditMasterInbound.setApplicationProcess(applicationProcess);
	}

	/**
	* Sets the audit inbound sid of this audit master inbound.
	*
	* @param auditInboundSid the audit inbound sid of this audit master inbound
	*/
	@Override
	public void setAuditInboundSid(int auditInboundSid) {
		_auditMasterInbound.setAuditInboundSid(auditInboundSid);
	}

	/**
	* Sets the batch ID of this audit master inbound.
	*
	* @param batchId the batch ID of this audit master inbound
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_auditMasterInbound.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_auditMasterInbound.setCachedModel(cachedModel);
	}

	/**
	* Sets the change count of this audit master inbound.
	*
	* @param changeCount the change count of this audit master inbound
	*/
	@Override
	public void setChangeCount(int changeCount) {
		_auditMasterInbound.setChangeCount(changeCount);
	}

	/**
	* Sets the created by of this audit master inbound.
	*
	* @param createdBy the created by of this audit master inbound
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_auditMasterInbound.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this audit master inbound.
	*
	* @param createdDate the created date of this audit master inbound
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_auditMasterInbound.setCreatedDate(createdDate);
	}

	/**
	* Sets the delete count of this audit master inbound.
	*
	* @param deleteCount the delete count of this audit master inbound
	*/
	@Override
	public void setDeleteCount(int deleteCount) {
		_auditMasterInbound.setDeleteCount(deleteCount);
	}

	/**
	* Sets the discrepancy amount of this audit master inbound.
	*
	* @param discrepancyAmount the discrepancy amount of this audit master inbound
	*/
	@Override
	public void setDiscrepancyAmount(double discrepancyAmount) {
		_auditMasterInbound.setDiscrepancyAmount(discrepancyAmount);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_auditMasterInbound.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_auditMasterInbound.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_auditMasterInbound.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file name of this audit master inbound.
	*
	* @param fileName the file name of this audit master inbound
	*/
	@Override
	public void setFileName(java.lang.String fileName) {
		_auditMasterInbound.setFileName(fileName);
	}

	/**
	* Sets the interface run end date of this audit master inbound.
	*
	* @param interfaceRunEndDate the interface run end date of this audit master inbound
	*/
	@Override
	public void setInterfaceRunEndDate(Date interfaceRunEndDate) {
		_auditMasterInbound.setInterfaceRunEndDate(interfaceRunEndDate);
	}

	/**
	* Sets the interface run start date of this audit master inbound.
	*
	* @param interfaceRunStartDate the interface run start date of this audit master inbound
	*/
	@Override
	public void setInterfaceRunStartDate(Date interfaceRunStartDate) {
		_auditMasterInbound.setInterfaceRunStartDate(interfaceRunStartDate);
	}

	/**
	* Sets the invalid record amount of this audit master inbound.
	*
	* @param invalidRecordAmount the invalid record amount of this audit master inbound
	*/
	@Override
	public void setInvalidRecordAmount(double invalidRecordAmount) {
		_auditMasterInbound.setInvalidRecordAmount(invalidRecordAmount);
	}

	/**
	* Sets the invalid record count of this audit master inbound.
	*
	* @param invalidRecordCount the invalid record count of this audit master inbound
	*/
	@Override
	public void setInvalidRecordCount(int invalidRecordCount) {
		_auditMasterInbound.setInvalidRecordCount(invalidRecordCount);
	}

	/**
	* Sets the modified by of this audit master inbound.
	*
	* @param modifiedBy the modified by of this audit master inbound
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_auditMasterInbound.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this audit master inbound.
	*
	* @param modifiedDate the modified date of this audit master inbound
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_auditMasterInbound.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_auditMasterInbound.setNew(n);
	}

	/**
	* Sets the primary key of this audit master inbound.
	*
	* @param primaryKey the primary key of this audit master inbound
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_auditMasterInbound.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_auditMasterInbound.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the received record amount of this audit master inbound.
	*
	* @param receivedRecordAmount the received record amount of this audit master inbound
	*/
	@Override
	public void setReceivedRecordAmount(double receivedRecordAmount) {
		_auditMasterInbound.setReceivedRecordAmount(receivedRecordAmount);
	}

	/**
	* Sets the received record amount attr of this audit master inbound.
	*
	* @param receivedRecordAmountAttr the received record amount attr of this audit master inbound
	*/
	@Override
	public void setReceivedRecordAmountAttr(
		java.lang.String receivedRecordAmountAttr) {
		_auditMasterInbound.setReceivedRecordAmountAttr(receivedRecordAmountAttr);
	}

	/**
	* Sets the received record count of this audit master inbound.
	*
	* @param receivedRecordCount the received record count of this audit master inbound
	*/
	@Override
	public void setReceivedRecordCount(int receivedRecordCount) {
		_auditMasterInbound.setReceivedRecordCount(receivedRecordCount);
	}

	/**
	* Sets the sent record amount of this audit master inbound.
	*
	* @param sentRecordAmount the sent record amount of this audit master inbound
	*/
	@Override
	public void setSentRecordAmount(double sentRecordAmount) {
		_auditMasterInbound.setSentRecordAmount(sentRecordAmount);
	}

	/**
	* Sets the sent record amount attribute of this audit master inbound.
	*
	* @param sentRecordAmountAttribute the sent record amount attribute of this audit master inbound
	*/
	@Override
	public void setSentRecordAmountAttribute(
		java.lang.String sentRecordAmountAttribute) {
		_auditMasterInbound.setSentRecordAmountAttribute(sentRecordAmountAttribute);
	}

	/**
	* Sets the sent record count of this audit master inbound.
	*
	* @param sentRecordCount the sent record count of this audit master inbound
	*/
	@Override
	public void setSentRecordCount(int sentRecordCount) {
		_auditMasterInbound.setSentRecordCount(sentRecordCount);
	}

	/**
	* Sets the source of this audit master inbound.
	*
	* @param source the source of this audit master inbound
	*/
	@Override
	public void setSource(java.lang.String source) {
		_auditMasterInbound.setSource(source);
	}

	/**
	* Sets the status of this audit master inbound.
	*
	* @param status the status of this audit master inbound
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_auditMasterInbound.setStatus(status);
	}

	/**
	* Sets the unprocessed records of this audit master inbound.
	*
	* @param unprocessedRecords the unprocessed records of this audit master inbound
	*/
	@Override
	public void setUnprocessedRecords(java.lang.String unprocessedRecords) {
		_auditMasterInbound.setUnprocessedRecords(unprocessedRecords);
	}

	/**
	* Sets the valid record amount of this audit master inbound.
	*
	* @param validRecordAmount the valid record amount of this audit master inbound
	*/
	@Override
	public void setValidRecordAmount(double validRecordAmount) {
		_auditMasterInbound.setValidRecordAmount(validRecordAmount);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AuditMasterInbound> toCacheModel() {
		return _auditMasterInbound.toCacheModel();
	}

	@Override
	public AuditMasterInbound toEscapedModel() {
		return new AuditMasterInboundWrapper(_auditMasterInbound.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _auditMasterInbound.toString();
	}

	@Override
	public AuditMasterInbound toUnescapedModel() {
		return new AuditMasterInboundWrapper(_auditMasterInbound.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _auditMasterInbound.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditMasterInboundWrapper)) {
			return false;
		}

		AuditMasterInboundWrapper auditMasterInboundWrapper = (AuditMasterInboundWrapper)obj;

		if (Objects.equals(_auditMasterInbound,
					auditMasterInboundWrapper._auditMasterInbound)) {
			return true;
		}

		return false;
	}

	@Override
	public AuditMasterInbound getWrappedModel() {
		return _auditMasterInbound;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _auditMasterInbound.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _auditMasterInbound.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_auditMasterInbound.resetOriginalValues();
	}

	private final AuditMasterInbound _auditMasterInbound;
}