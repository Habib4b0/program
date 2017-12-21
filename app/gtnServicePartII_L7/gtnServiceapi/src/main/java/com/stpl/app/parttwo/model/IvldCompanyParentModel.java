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

package com.stpl.app.parttwo.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the IvldCompanyParent service. Represents a row in the &quot;IVLD_COMPANY_PARENT&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.parttwo.model.impl.IvldCompanyParentImpl}.
 * </p>
 *
 * @author
 * @see IvldCompanyParent
 * @see com.stpl.app.parttwo.model.impl.IvldCompanyParentImpl
 * @see com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl
 * @generated
 */
@ProviderType
public interface IvldCompanyParentModel extends BaseModel<IvldCompanyParent>,
	ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ivld company parent model instance should use the {@link IvldCompanyParent} interface instead.
	 */

	/**
	 * Returns the primary key of this ivld company parent.
	 *
	 * @return the primary key of this ivld company parent
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this ivld company parent.
	 *
	 * @param primaryKey the primary key of this ivld company parent
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the parentcompany ID of this ivld company parent.
	 *
	 * @return the parentcompany ID of this ivld company parent
	 */
	@AutoEscape
	public String getParentcompanyId();

	/**
	 * Sets the parentcompany ID of this ivld company parent.
	 *
	 * @param parentcompanyId the parentcompany ID of this ivld company parent
	 */
	public void setParentcompanyId(String parentcompanyId);

	/**
	 * Returns the prior parentcompany ID of this ivld company parent.
	 *
	 * @return the prior parentcompany ID of this ivld company parent
	 */
	@AutoEscape
	public String getPriorParentcompanyId();

	/**
	 * Sets the prior parentcompany ID of this ivld company parent.
	 *
	 * @param priorParentcompanyId the prior parentcompany ID of this ivld company parent
	 */
	public void setPriorParentcompanyId(String priorParentcompanyId);

	/**
	 * Returns the reason for failure of this ivld company parent.
	 *
	 * @return the reason for failure of this ivld company parent
	 */
	@AutoEscape
	public String getReasonForFailure();

	/**
	 * Sets the reason for failure of this ivld company parent.
	 *
	 * @param reasonForFailure the reason for failure of this ivld company parent
	 */
	public void setReasonForFailure(String reasonForFailure);

	/**
	 * Returns the company ID of this ivld company parent.
	 *
	 * @return the company ID of this ivld company parent
	 */
	@AutoEscape
	@Override
	public String getCompanyId();

	/**
	 * Sets the company ID of this ivld company parent.
	 *
	 * @param companyId the company ID of this ivld company parent
	 */
	@Override
	public void setCompanyId(String companyId);

	/**
	 * Returns the last updated date of this ivld company parent.
	 *
	 * @return the last updated date of this ivld company parent
	 */
	@AutoEscape
	public String getLastUpdatedDate();

	/**
	 * Sets the last updated date of this ivld company parent.
	 *
	 * @param lastUpdatedDate the last updated date of this ivld company parent
	 */
	public void setLastUpdatedDate(String lastUpdatedDate);

	/**
	 * Returns the parent end date of this ivld company parent.
	 *
	 * @return the parent end date of this ivld company parent
	 */
	@AutoEscape
	public String getParentEndDate();

	/**
	 * Sets the parent end date of this ivld company parent.
	 *
	 * @param parentEndDate the parent end date of this ivld company parent
	 */
	public void setParentEndDate(String parentEndDate);

	/**
	 * Returns the modified date of this ivld company parent.
	 *
	 * @return the modified date of this ivld company parent
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this ivld company parent.
	 *
	 * @param modifiedDate the modified date of this ivld company parent
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the parent details intfid of this ivld company parent.
	 *
	 * @return the parent details intfid of this ivld company parent
	 */
	@AutoEscape
	public String getParentDetailsIntfid();

	/**
	 * Sets the parent details intfid of this ivld company parent.
	 *
	 * @param parentDetailsIntfid the parent details intfid of this ivld company parent
	 */
	public void setParentDetailsIntfid(String parentDetailsIntfid);

	/**
	 * Returns the prior parent start date of this ivld company parent.
	 *
	 * @return the prior parent start date of this ivld company parent
	 */
	@AutoEscape
	public String getPriorParentStartDate();

	/**
	 * Sets the prior parent start date of this ivld company parent.
	 *
	 * @param priorParentStartDate the prior parent start date of this ivld company parent
	 */
	public void setPriorParentStartDate(String priorParentStartDate);

	/**
	 * Returns the source of this ivld company parent.
	 *
	 * @return the source of this ivld company parent
	 */
	@AutoEscape
	public String getSource();

	/**
	 * Sets the source of this ivld company parent.
	 *
	 * @param source the source of this ivld company parent
	 */
	public void setSource(String source);

	/**
	 * Returns the created by of this ivld company parent.
	 *
	 * @return the created by of this ivld company parent
	 */
	@AutoEscape
	public String getCreatedBy();

	/**
	 * Sets the created by of this ivld company parent.
	 *
	 * @param createdBy the created by of this ivld company parent
	 */
	public void setCreatedBy(String createdBy);

	/**
	 * Returns the created date of this ivld company parent.
	 *
	 * @return the created date of this ivld company parent
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this ivld company parent.
	 *
	 * @param createdDate the created date of this ivld company parent
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the add chg del indicator of this ivld company parent.
	 *
	 * @return the add chg del indicator of this ivld company parent
	 */
	@AutoEscape
	public String getAddChgDelIndicator();

	/**
	 * Sets the add chg del indicator of this ivld company parent.
	 *
	 * @param addChgDelIndicator the add chg del indicator of this ivld company parent
	 */
	public void setAddChgDelIndicator(String addChgDelIndicator);

	/**
	 * Returns the batch ID of this ivld company parent.
	 *
	 * @return the batch ID of this ivld company parent
	 */
	@AutoEscape
	public String getBatchId();

	/**
	 * Sets the batch ID of this ivld company parent.
	 *
	 * @param batchId the batch ID of this ivld company parent
	 */
	public void setBatchId(String batchId);

	/**
	 * Returns the ivld company parent sid of this ivld company parent.
	 *
	 * @return the ivld company parent sid of this ivld company parent
	 */
	public int getIvldCompanyParentSid();

	/**
	 * Sets the ivld company parent sid of this ivld company parent.
	 *
	 * @param ivldCompanyParentSid the ivld company parent sid of this ivld company parent
	 */
	public void setIvldCompanyParentSid(int ivldCompanyParentSid);

	/**
	 * Returns the error field of this ivld company parent.
	 *
	 * @return the error field of this ivld company parent
	 */
	@AutoEscape
	public String getErrorField();

	/**
	 * Sets the error field of this ivld company parent.
	 *
	 * @param errorField the error field of this ivld company parent
	 */
	public void setErrorField(String errorField);

	/**
	 * Returns the error code of this ivld company parent.
	 *
	 * @return the error code of this ivld company parent
	 */
	@AutoEscape
	public String getErrorCode();

	/**
	 * Sets the error code of this ivld company parent.
	 *
	 * @param errorCode the error code of this ivld company parent
	 */
	public void setErrorCode(String errorCode);

	/**
	 * Returns the intf inserted date of this ivld company parent.
	 *
	 * @return the intf inserted date of this ivld company parent
	 */
	public Date getIntfInsertedDate();

	/**
	 * Sets the intf inserted date of this ivld company parent.
	 *
	 * @param intfInsertedDate the intf inserted date of this ivld company parent
	 */
	public void setIntfInsertedDate(Date intfInsertedDate);

	/**
	 * Returns the modified by of this ivld company parent.
	 *
	 * @return the modified by of this ivld company parent
	 */
	@AutoEscape
	public String getModifiedBy();

	/**
	 * Sets the modified by of this ivld company parent.
	 *
	 * @param modifiedBy the modified by of this ivld company parent
	 */
	public void setModifiedBy(String modifiedBy);

	/**
	 * Returns the reprocessed flag of this ivld company parent.
	 *
	 * @return the reprocessed flag of this ivld company parent
	 */
	@AutoEscape
	public String getReprocessedFlag();

	/**
	 * Sets the reprocessed flag of this ivld company parent.
	 *
	 * @param reprocessedFlag the reprocessed flag of this ivld company parent
	 */
	public void setReprocessedFlag(String reprocessedFlag);

	/**
	 * Returns the parent start date of this ivld company parent.
	 *
	 * @return the parent start date of this ivld company parent
	 */
	@AutoEscape
	public String getParentStartDate();

	/**
	 * Sets the parent start date of this ivld company parent.
	 *
	 * @param parentStartDate the parent start date of this ivld company parent
	 */
	public void setParentStartDate(String parentStartDate);

	/**
	 * Returns the check record of this ivld company parent.
	 *
	 * @return the check record of this ivld company parent
	 */
	public boolean getCheckRecord();

	/**
	 * Returns <code>true</code> if this ivld company parent is check record.
	 *
	 * @return <code>true</code> if this ivld company parent is check record; <code>false</code> otherwise
	 */
	public boolean isCheckRecord();

	/**
	 * Sets whether this ivld company parent is check record.
	 *
	 * @param checkRecord the check record of this ivld company parent
	 */
	public void setCheckRecord(boolean checkRecord);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(IvldCompanyParent ivldCompanyParent);

	@Override
	public int hashCode();

	@Override
	public CacheModel<IvldCompanyParent> toCacheModel();

	@Override
	public IvldCompanyParent toEscapedModel();

	@Override
	public IvldCompanyParent toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}