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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.model.HistWorkflowMaster;
import com.stpl.app.model.HistWorkflowMasterModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the HistWorkflowMaster service. Represents a row in the &quot;HIST_WORKFLOW_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link HistWorkflowMasterModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link HistWorkflowMasterImpl}.
 * </p>
 *
 * @author
 * @see HistWorkflowMasterImpl
 * @see HistWorkflowMaster
 * @see HistWorkflowMasterModel
 * @generated
 */
@ProviderType
public class HistWorkflowMasterModelImpl extends BaseModelImpl<HistWorkflowMaster>
	implements HistWorkflowMasterModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a hist workflow master model instance should use the {@link HistWorkflowMaster} interface instead.
	 */
	public static final String TABLE_NAME = "HIST_WORKFLOW_MASTER";
	public static final Object[][] TABLE_COLUMNS = {
			{ "CREATED_BY", Types.INTEGER },
			{ "FILE_SIZE", Types.VARCHAR },
			{ "ACTION_DATE", Types.TIMESTAMP },
			{ "WORKFLOW_STATUS_ID", Types.INTEGER },
			{ "ACTION_FLAG", Types.VARCHAR },
			{ "MODIFIED_BY", Types.INTEGER },
			{ "CREATED_DATE", Types.TIMESTAMP },
			{ "APPROVAL_LEVEL", Types.INTEGER },
			{ "NO_OF_APPROVAL", Types.INTEGER },
			{ "FILE_NAME", Types.VARCHAR },
			{ "UPLOADED_BY", Types.VARCHAR },
			{ "MODIFIED_DATE", Types.TIMESTAMP },
			{ "ACC_CLOSURE_MASTER_SID", Types.INTEGER },
			{ "NOTES", Types.VARCHAR },
			{ "WORKFLOW_MASTER_SID", Types.INTEGER },
			{ "WORKFLOW_ID", Types.VARCHAR },
			{ "PROJECTION_MASTER_SID", Types.INTEGER },
			{ "UPLOADED_DATE", Types.TIMESTAMP },
			{ "FILE_TYPE", Types.VARCHAR },
			{ "APPROVED_BY", Types.INTEGER },
			{ "WORKFLOW_DESCRPTION", Types.VARCHAR },
			{ "APPROVED_DATE", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CREATED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("FILE_SIZE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ACTION_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("WORKFLOW_STATUS_ID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("ACTION_FLAG", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("MODIFIED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("CREATED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("APPROVAL_LEVEL", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("NO_OF_APPROVAL", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("FILE_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("UPLOADED_BY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ACC_CLOSURE_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("NOTES", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("WORKFLOW_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("WORKFLOW_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("PROJECTION_MASTER_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("UPLOADED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("FILE_TYPE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("APPROVED_BY", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("WORKFLOW_DESCRPTION", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("APPROVED_DATE", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table HIST_WORKFLOW_MASTER (CREATED_BY INTEGER,FILE_SIZE VARCHAR(75) null,ACTION_DATE DATE null,WORKFLOW_STATUS_ID INTEGER,ACTION_FLAG VARCHAR(75) null,MODIFIED_BY INTEGER,CREATED_DATE DATE null,APPROVAL_LEVEL INTEGER,NO_OF_APPROVAL INTEGER,FILE_NAME VARCHAR(75) null,UPLOADED_BY VARCHAR(75) null,MODIFIED_DATE DATE null,ACC_CLOSURE_MASTER_SID INTEGER,NOTES VARCHAR(75) null,WORKFLOW_MASTER_SID INTEGER not null primary key,WORKFLOW_ID VARCHAR(75) null,PROJECTION_MASTER_SID INTEGER,UPLOADED_DATE DATE null,FILE_TYPE VARCHAR(75) null,APPROVED_BY INTEGER,WORKFLOW_DESCRPTION VARCHAR(75) null,APPROVED_DATE DATE null)";
	public static final String TABLE_SQL_DROP = "drop table HIST_WORKFLOW_MASTER";
	public static final String ORDER_BY_JPQL = " ORDER BY histWorkflowMaster.workflowMasterSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY HIST_WORKFLOW_MASTER.WORKFLOW_MASTER_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.HistWorkflowMaster"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.HistWorkflowMaster"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.HistWorkflowMaster"));

	public HistWorkflowMasterModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _workflowMasterSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setWorkflowMasterSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _workflowMasterSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Class<?> getModelClass() {
		return HistWorkflowMaster.class;
	}

	@Override
	public String getModelClassName() {
		return HistWorkflowMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("fileSize", getFileSize());
		attributes.put("actionDate", getActionDate());
		attributes.put("workflowStatusId", getWorkflowStatusId());
		attributes.put("actionFlag", getActionFlag());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("approvalLevel", getApprovalLevel());
		attributes.put("noOfApproval", getNoOfApproval());
		attributes.put("fileName", getFileName());
		attributes.put("uploadedBy", getUploadedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accClosureMasterSid", getAccClosureMasterSid());
		attributes.put("notes", getNotes());
		attributes.put("workflowMasterSid", getWorkflowMasterSid());
		attributes.put("workflowId", getWorkflowId());
		attributes.put("projectionMasterSid", getProjectionMasterSid());
		attributes.put("uploadedDate", getUploadedDate());
		attributes.put("fileType", getFileType());
		attributes.put("approvedBy", getApprovedBy());
		attributes.put("workflowDescrption", getWorkflowDescrption());
		attributes.put("approvedDate", getApprovedDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String fileSize = (String)attributes.get("fileSize");

		if (fileSize != null) {
			setFileSize(fileSize);
		}

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		Integer workflowStatusId = (Integer)attributes.get("workflowStatusId");

		if (workflowStatusId != null) {
			setWorkflowStatusId(workflowStatusId);
		}

		String actionFlag = (String)attributes.get("actionFlag");

		if (actionFlag != null) {
			setActionFlag(actionFlag);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer approvalLevel = (Integer)attributes.get("approvalLevel");

		if (approvalLevel != null) {
			setApprovalLevel(approvalLevel);
		}

		Integer noOfApproval = (Integer)attributes.get("noOfApproval");

		if (noOfApproval != null) {
			setNoOfApproval(noOfApproval);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		String uploadedBy = (String)attributes.get("uploadedBy");

		if (uploadedBy != null) {
			setUploadedBy(uploadedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer accClosureMasterSid = (Integer)attributes.get(
				"accClosureMasterSid");

		if (accClosureMasterSid != null) {
			setAccClosureMasterSid(accClosureMasterSid);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}

		Integer workflowMasterSid = (Integer)attributes.get("workflowMasterSid");

		if (workflowMasterSid != null) {
			setWorkflowMasterSid(workflowMasterSid);
		}

		String workflowId = (String)attributes.get("workflowId");

		if (workflowId != null) {
			setWorkflowId(workflowId);
		}

		Integer projectionMasterSid = (Integer)attributes.get(
				"projectionMasterSid");

		if (projectionMasterSid != null) {
			setProjectionMasterSid(projectionMasterSid);
		}

		Date uploadedDate = (Date)attributes.get("uploadedDate");

		if (uploadedDate != null) {
			setUploadedDate(uploadedDate);
		}

		String fileType = (String)attributes.get("fileType");

		if (fileType != null) {
			setFileType(fileType);
		}

		Integer approvedBy = (Integer)attributes.get("approvedBy");

		if (approvedBy != null) {
			setApprovedBy(approvedBy);
		}

		String workflowDescrption = (String)attributes.get("workflowDescrption");

		if (workflowDescrption != null) {
			setWorkflowDescrption(workflowDescrption);
		}

		Date approvedDate = (Date)attributes.get("approvedDate");

		if (approvedDate != null) {
			setApprovedDate(approvedDate);
		}
	}

	@Override
	public int getCreatedBy() {
		return _createdBy;
	}

	@Override
	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	@Override
	public String getFileSize() {
		if (_fileSize == null) {
			return StringPool.BLANK;
		}
		else {
			return _fileSize;
		}
	}

	@Override
	public void setFileSize(String fileSize) {
		_fileSize = fileSize;
	}

	@Override
	public Date getActionDate() {
		return _actionDate;
	}

	@Override
	public void setActionDate(Date actionDate) {
		_actionDate = actionDate;
	}

	@Override
	public int getWorkflowStatusId() {
		return _workflowStatusId;
	}

	@Override
	public void setWorkflowStatusId(int workflowStatusId) {
		_workflowStatusId = workflowStatusId;
	}

	@Override
	public String getActionFlag() {
		if (_actionFlag == null) {
			return StringPool.BLANK;
		}
		else {
			return _actionFlag;
		}
	}

	@Override
	public void setActionFlag(String actionFlag) {
		_actionFlag = actionFlag;
	}

	@Override
	public int getModifiedBy() {
		return _modifiedBy;
	}

	@Override
	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	@Override
	public Date getCreatedDate() {
		return _createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	@Override
	public int getApprovalLevel() {
		return _approvalLevel;
	}

	@Override
	public void setApprovalLevel(int approvalLevel) {
		_approvalLevel = approvalLevel;
	}

	@Override
	public int getNoOfApproval() {
		return _noOfApproval;
	}

	@Override
	public void setNoOfApproval(int noOfApproval) {
		_noOfApproval = noOfApproval;
	}

	@Override
	public String getFileName() {
		if (_fileName == null) {
			return StringPool.BLANK;
		}
		else {
			return _fileName;
		}
	}

	@Override
	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	@Override
	public String getUploadedBy() {
		if (_uploadedBy == null) {
			return StringPool.BLANK;
		}
		else {
			return _uploadedBy;
		}
	}

	@Override
	public void setUploadedBy(String uploadedBy) {
		_uploadedBy = uploadedBy;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public int getAccClosureMasterSid() {
		return _accClosureMasterSid;
	}

	@Override
	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_accClosureMasterSid = accClosureMasterSid;
	}

	@Override
	public String getNotes() {
		if (_notes == null) {
			return StringPool.BLANK;
		}
		else {
			return _notes;
		}
	}

	@Override
	public void setNotes(String notes) {
		_notes = notes;
	}

	@Override
	public int getWorkflowMasterSid() {
		return _workflowMasterSid;
	}

	@Override
	public void setWorkflowMasterSid(int workflowMasterSid) {
		_workflowMasterSid = workflowMasterSid;
	}

	@Override
	public String getWorkflowId() {
		if (_workflowId == null) {
			return StringPool.BLANK;
		}
		else {
			return _workflowId;
		}
	}

	@Override
	public void setWorkflowId(String workflowId) {
		_workflowId = workflowId;
	}

	@Override
	public int getProjectionMasterSid() {
		return _projectionMasterSid;
	}

	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_projectionMasterSid = projectionMasterSid;
	}

	@Override
	public Date getUploadedDate() {
		return _uploadedDate;
	}

	@Override
	public void setUploadedDate(Date uploadedDate) {
		_uploadedDate = uploadedDate;
	}

	@Override
	public String getFileType() {
		if (_fileType == null) {
			return StringPool.BLANK;
		}
		else {
			return _fileType;
		}
	}

	@Override
	public void setFileType(String fileType) {
		_fileType = fileType;
	}

	@Override
	public int getApprovedBy() {
		return _approvedBy;
	}

	@Override
	public void setApprovedBy(int approvedBy) {
		_approvedBy = approvedBy;
	}

	@Override
	public String getWorkflowDescrption() {
		if (_workflowDescrption == null) {
			return StringPool.BLANK;
		}
		else {
			return _workflowDescrption;
		}
	}

	@Override
	public void setWorkflowDescrption(String workflowDescrption) {
		_workflowDescrption = workflowDescrption;
	}

	@Override
	public Date getApprovedDate() {
		return _approvedDate;
	}

	@Override
	public void setApprovedDate(Date approvedDate) {
		_approvedDate = approvedDate;
	}

	@Override
	public HistWorkflowMaster toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (HistWorkflowMaster)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		HistWorkflowMasterImpl histWorkflowMasterImpl = new HistWorkflowMasterImpl();

		histWorkflowMasterImpl.setCreatedBy(getCreatedBy());
		histWorkflowMasterImpl.setFileSize(getFileSize());
		histWorkflowMasterImpl.setActionDate(getActionDate());
		histWorkflowMasterImpl.setWorkflowStatusId(getWorkflowStatusId());
		histWorkflowMasterImpl.setActionFlag(getActionFlag());
		histWorkflowMasterImpl.setModifiedBy(getModifiedBy());
		histWorkflowMasterImpl.setCreatedDate(getCreatedDate());
		histWorkflowMasterImpl.setApprovalLevel(getApprovalLevel());
		histWorkflowMasterImpl.setNoOfApproval(getNoOfApproval());
		histWorkflowMasterImpl.setFileName(getFileName());
		histWorkflowMasterImpl.setUploadedBy(getUploadedBy());
		histWorkflowMasterImpl.setModifiedDate(getModifiedDate());
		histWorkflowMasterImpl.setAccClosureMasterSid(getAccClosureMasterSid());
		histWorkflowMasterImpl.setNotes(getNotes());
		histWorkflowMasterImpl.setWorkflowMasterSid(getWorkflowMasterSid());
		histWorkflowMasterImpl.setWorkflowId(getWorkflowId());
		histWorkflowMasterImpl.setProjectionMasterSid(getProjectionMasterSid());
		histWorkflowMasterImpl.setUploadedDate(getUploadedDate());
		histWorkflowMasterImpl.setFileType(getFileType());
		histWorkflowMasterImpl.setApprovedBy(getApprovedBy());
		histWorkflowMasterImpl.setWorkflowDescrption(getWorkflowDescrption());
		histWorkflowMasterImpl.setApprovedDate(getApprovedDate());

		histWorkflowMasterImpl.resetOriginalValues();

		return histWorkflowMasterImpl;
	}

	@Override
	public int compareTo(HistWorkflowMaster histWorkflowMaster) {
		int primaryKey = histWorkflowMaster.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistWorkflowMaster)) {
			return false;
		}

		HistWorkflowMaster histWorkflowMaster = (HistWorkflowMaster)obj;

		int primaryKey = histWorkflowMaster.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<HistWorkflowMaster> toCacheModel() {
		HistWorkflowMasterCacheModel histWorkflowMasterCacheModel = new HistWorkflowMasterCacheModel();

		histWorkflowMasterCacheModel.createdBy = getCreatedBy();

		histWorkflowMasterCacheModel.fileSize = getFileSize();

		String fileSize = histWorkflowMasterCacheModel.fileSize;

		if ((fileSize != null) && (fileSize.length() == 0)) {
			histWorkflowMasterCacheModel.fileSize = null;
		}

		Date actionDate = getActionDate();

		if (actionDate != null) {
			histWorkflowMasterCacheModel.actionDate = actionDate.getTime();
		}
		else {
			histWorkflowMasterCacheModel.actionDate = Long.MIN_VALUE;
		}

		histWorkflowMasterCacheModel.workflowStatusId = getWorkflowStatusId();

		histWorkflowMasterCacheModel.actionFlag = getActionFlag();

		String actionFlag = histWorkflowMasterCacheModel.actionFlag;

		if ((actionFlag != null) && (actionFlag.length() == 0)) {
			histWorkflowMasterCacheModel.actionFlag = null;
		}

		histWorkflowMasterCacheModel.modifiedBy = getModifiedBy();

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			histWorkflowMasterCacheModel.createdDate = createdDate.getTime();
		}
		else {
			histWorkflowMasterCacheModel.createdDate = Long.MIN_VALUE;
		}

		histWorkflowMasterCacheModel.approvalLevel = getApprovalLevel();

		histWorkflowMasterCacheModel.noOfApproval = getNoOfApproval();

		histWorkflowMasterCacheModel.fileName = getFileName();

		String fileName = histWorkflowMasterCacheModel.fileName;

		if ((fileName != null) && (fileName.length() == 0)) {
			histWorkflowMasterCacheModel.fileName = null;
		}

		histWorkflowMasterCacheModel.uploadedBy = getUploadedBy();

		String uploadedBy = histWorkflowMasterCacheModel.uploadedBy;

		if ((uploadedBy != null) && (uploadedBy.length() == 0)) {
			histWorkflowMasterCacheModel.uploadedBy = null;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			histWorkflowMasterCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			histWorkflowMasterCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		histWorkflowMasterCacheModel.accClosureMasterSid = getAccClosureMasterSid();

		histWorkflowMasterCacheModel.notes = getNotes();

		String notes = histWorkflowMasterCacheModel.notes;

		if ((notes != null) && (notes.length() == 0)) {
			histWorkflowMasterCacheModel.notes = null;
		}

		histWorkflowMasterCacheModel.workflowMasterSid = getWorkflowMasterSid();

		histWorkflowMasterCacheModel.workflowId = getWorkflowId();

		String workflowId = histWorkflowMasterCacheModel.workflowId;

		if ((workflowId != null) && (workflowId.length() == 0)) {
			histWorkflowMasterCacheModel.workflowId = null;
		}

		histWorkflowMasterCacheModel.projectionMasterSid = getProjectionMasterSid();

		Date uploadedDate = getUploadedDate();

		if (uploadedDate != null) {
			histWorkflowMasterCacheModel.uploadedDate = uploadedDate.getTime();
		}
		else {
			histWorkflowMasterCacheModel.uploadedDate = Long.MIN_VALUE;
		}

		histWorkflowMasterCacheModel.fileType = getFileType();

		String fileType = histWorkflowMasterCacheModel.fileType;

		if ((fileType != null) && (fileType.length() == 0)) {
			histWorkflowMasterCacheModel.fileType = null;
		}

		histWorkflowMasterCacheModel.approvedBy = getApprovedBy();

		histWorkflowMasterCacheModel.workflowDescrption = getWorkflowDescrption();

		String workflowDescrption = histWorkflowMasterCacheModel.workflowDescrption;

		if ((workflowDescrption != null) && (workflowDescrption.length() == 0)) {
			histWorkflowMasterCacheModel.workflowDescrption = null;
		}

		Date approvedDate = getApprovedDate();

		if (approvedDate != null) {
			histWorkflowMasterCacheModel.approvedDate = approvedDate.getTime();
		}
		else {
			histWorkflowMasterCacheModel.approvedDate = Long.MIN_VALUE;
		}

		return histWorkflowMasterCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{createdBy=");
		sb.append(getCreatedBy());
		sb.append(", fileSize=");
		sb.append(getFileSize());
		sb.append(", actionDate=");
		sb.append(getActionDate());
		sb.append(", workflowStatusId=");
		sb.append(getWorkflowStatusId());
		sb.append(", actionFlag=");
		sb.append(getActionFlag());
		sb.append(", modifiedBy=");
		sb.append(getModifiedBy());
		sb.append(", createdDate=");
		sb.append(getCreatedDate());
		sb.append(", approvalLevel=");
		sb.append(getApprovalLevel());
		sb.append(", noOfApproval=");
		sb.append(getNoOfApproval());
		sb.append(", fileName=");
		sb.append(getFileName());
		sb.append(", uploadedBy=");
		sb.append(getUploadedBy());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", accClosureMasterSid=");
		sb.append(getAccClosureMasterSid());
		sb.append(", notes=");
		sb.append(getNotes());
		sb.append(", workflowMasterSid=");
		sb.append(getWorkflowMasterSid());
		sb.append(", workflowId=");
		sb.append(getWorkflowId());
		sb.append(", projectionMasterSid=");
		sb.append(getProjectionMasterSid());
		sb.append(", uploadedDate=");
		sb.append(getUploadedDate());
		sb.append(", fileType=");
		sb.append(getFileType());
		sb.append(", approvedBy=");
		sb.append(getApprovedBy());
		sb.append(", workflowDescrption=");
		sb.append(getWorkflowDescrption());
		sb.append(", approvedDate=");
		sb.append(getApprovedDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(70);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.HistWorkflowMaster");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>createdBy</column-name><column-value><![CDATA[");
		sb.append(getCreatedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileSize</column-name><column-value><![CDATA[");
		sb.append(getFileSize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actionDate</column-name><column-value><![CDATA[");
		sb.append(getActionDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowStatusId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowStatusId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actionFlag</column-name><column-value><![CDATA[");
		sb.append(getActionFlag());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
		sb.append(getModifiedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdDate</column-name><column-value><![CDATA[");
		sb.append(getCreatedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>approvalLevel</column-name><column-value><![CDATA[");
		sb.append(getApprovalLevel());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>noOfApproval</column-name><column-value><![CDATA[");
		sb.append(getNoOfApproval());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileName</column-name><column-value><![CDATA[");
		sb.append(getFileName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>uploadedBy</column-name><column-value><![CDATA[");
		sb.append(getUploadedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accClosureMasterSid</column-name><column-value><![CDATA[");
		sb.append(getAccClosureMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notes</column-name><column-value><![CDATA[");
		sb.append(getNotes());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowMasterSid</column-name><column-value><![CDATA[");
		sb.append(getWorkflowMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectionMasterSid</column-name><column-value><![CDATA[");
		sb.append(getProjectionMasterSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>uploadedDate</column-name><column-value><![CDATA[");
		sb.append(getUploadedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileType</column-name><column-value><![CDATA[");
		sb.append(getFileType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>approvedBy</column-name><column-value><![CDATA[");
		sb.append(getApprovedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowDescrption</column-name><column-value><![CDATA[");
		sb.append(getWorkflowDescrption());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>approvedDate</column-name><column-value><![CDATA[");
		sb.append(getApprovedDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = HistWorkflowMaster.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			HistWorkflowMaster.class
		};
	private int _createdBy;
	private String _fileSize;
	private Date _actionDate;
	private int _workflowStatusId;
	private String _actionFlag;
	private int _modifiedBy;
	private Date _createdDate;
	private int _approvalLevel;
	private int _noOfApproval;
	private String _fileName;
	private String _uploadedBy;
	private Date _modifiedDate;
	private int _accClosureMasterSid;
	private String _notes;
	private int _workflowMasterSid;
	private String _workflowId;
	private int _projectionMasterSid;
	private Date _uploadedDate;
	private String _fileType;
	private int _approvedBy;
	private String _workflowDescrption;
	private Date _approvedDate;
	private HistWorkflowMaster _escapedModel;
}