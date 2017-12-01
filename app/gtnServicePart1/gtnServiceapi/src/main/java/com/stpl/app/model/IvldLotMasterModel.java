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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the IvldLotMaster service. Represents a row in the &quot;IVLD_LOT_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.IvldLotMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.IvldLotMasterImpl}.
 * </p>
 *
 * @author
 * @see IvldLotMaster
 * @see com.stpl.app.model.impl.IvldLotMasterImpl
 * @see com.stpl.app.model.impl.IvldLotMasterModelImpl
 * @generated
 */
@ProviderType
public interface IvldLotMasterModel extends BaseModel<IvldLotMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ivld lot master model instance should use the {@link IvldLotMaster} interface instead.
	 */

	/**
	 * Returns the primary key of this ivld lot master.
	 *
	 * @return the primary key of this ivld lot master
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this ivld lot master.
	 *
	 * @param primaryKey the primary key of this ivld lot master
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the reason for failure of this ivld lot master.
	 *
	 * @return the reason for failure of this ivld lot master
	 */
	@AutoEscape
	public String getReasonForFailure();

	/**
	 * Sets the reason for failure of this ivld lot master.
	 *
	 * @param reasonForFailure the reason for failure of this ivld lot master
	 */
	public void setReasonForFailure(String reasonForFailure);

	/**
	 * Returns the item ID of this ivld lot master.
	 *
	 * @return the item ID of this ivld lot master
	 */
	@AutoEscape
	public String getItemId();

	/**
	 * Sets the item ID of this ivld lot master.
	 *
	 * @param itemId the item ID of this ivld lot master
	 */
	public void setItemId(String itemId);

	/**
	 * Returns the ivld lot master sid of this ivld lot master.
	 *
	 * @return the ivld lot master sid of this ivld lot master
	 */
	public int getIvldLotMasterSid();

	/**
	 * Sets the ivld lot master sid of this ivld lot master.
	 *
	 * @param ivldLotMasterSid the ivld lot master sid of this ivld lot master
	 */
	public void setIvldLotMasterSid(int ivldLotMasterSid);

	/**
	 * Returns the modified date of this ivld lot master.
	 *
	 * @return the modified date of this ivld lot master
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this ivld lot master.
	 *
	 * @param modifiedDate the modified date of this ivld lot master
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the created by of this ivld lot master.
	 *
	 * @return the created by of this ivld lot master
	 */
	@AutoEscape
	public String getCreatedBy();

	/**
	 * Sets the created by of this ivld lot master.
	 *
	 * @param createdBy the created by of this ivld lot master
	 */
	public void setCreatedBy(String createdBy);

	/**
	 * Returns the created date of this ivld lot master.
	 *
	 * @return the created date of this ivld lot master
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this ivld lot master.
	 *
	 * @param createdDate the created date of this ivld lot master
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the source of this ivld lot master.
	 *
	 * @return the source of this ivld lot master
	 */
	@AutoEscape
	public String getSource();

	/**
	 * Sets the source of this ivld lot master.
	 *
	 * @param source the source of this ivld lot master
	 */
	public void setSource(String source);

	/**
	 * Returns the last lot sold date of this ivld lot master.
	 *
	 * @return the last lot sold date of this ivld lot master
	 */
	@AutoEscape
	public String getLastLotSoldDate();

	/**
	 * Sets the last lot sold date of this ivld lot master.
	 *
	 * @param lastLotSoldDate the last lot sold date of this ivld lot master
	 */
	public void setLastLotSoldDate(String lastLotSoldDate);

	/**
	 * Returns the lot expiration date of this ivld lot master.
	 *
	 * @return the lot expiration date of this ivld lot master
	 */
	@AutoEscape
	public String getLotExpirationDate();

	/**
	 * Sets the lot expiration date of this ivld lot master.
	 *
	 * @param lotExpirationDate the lot expiration date of this ivld lot master
	 */
	public void setLotExpirationDate(String lotExpirationDate);

	/**
	 * Returns the batch ID of this ivld lot master.
	 *
	 * @return the batch ID of this ivld lot master
	 */
	@AutoEscape
	public String getBatchId();

	/**
	 * Sets the batch ID of this ivld lot master.
	 *
	 * @param batchId the batch ID of this ivld lot master
	 */
	public void setBatchId(String batchId);

	/**
	 * Returns the add chg del indicator of this ivld lot master.
	 *
	 * @return the add chg del indicator of this ivld lot master
	 */
	@AutoEscape
	public String getAddChgDelIndicator();

	/**
	 * Sets the add chg del indicator of this ivld lot master.
	 *
	 * @param addChgDelIndicator the add chg del indicator of this ivld lot master
	 */
	public void setAddChgDelIndicator(String addChgDelIndicator);

	/**
	 * Returns the error field of this ivld lot master.
	 *
	 * @return the error field of this ivld lot master
	 */
	@AutoEscape
	public String getErrorField();

	/**
	 * Sets the error field of this ivld lot master.
	 *
	 * @param errorField the error field of this ivld lot master
	 */
	public void setErrorField(String errorField);

	/**
	 * Returns the error code of this ivld lot master.
	 *
	 * @return the error code of this ivld lot master
	 */
	@AutoEscape
	public String getErrorCode();

	/**
	 * Sets the error code of this ivld lot master.
	 *
	 * @param errorCode the error code of this ivld lot master
	 */
	public void setErrorCode(String errorCode);

	/**
	 * Returns the intf inserted date of this ivld lot master.
	 *
	 * @return the intf inserted date of this ivld lot master
	 */
	public Date getIntfInsertedDate();

	/**
	 * Sets the intf inserted date of this ivld lot master.
	 *
	 * @param intfInsertedDate the intf inserted date of this ivld lot master
	 */
	public void setIntfInsertedDate(Date intfInsertedDate);

	/**
	 * Returns the modified by of this ivld lot master.
	 *
	 * @return the modified by of this ivld lot master
	 */
	@AutoEscape
	public String getModifiedBy();

	/**
	 * Sets the modified by of this ivld lot master.
	 *
	 * @param modifiedBy the modified by of this ivld lot master
	 */
	public void setModifiedBy(String modifiedBy);

	/**
	 * Returns the lot no of this ivld lot master.
	 *
	 * @return the lot no of this ivld lot master
	 */
	@AutoEscape
	public String getLotNo();

	/**
	 * Sets the lot no of this ivld lot master.
	 *
	 * @param lotNo the lot no of this ivld lot master
	 */
	public void setLotNo(String lotNo);

	/**
	 * Returns the reprocessed flag of this ivld lot master.
	 *
	 * @return the reprocessed flag of this ivld lot master
	 */
	@AutoEscape
	public String getReprocessedFlag();

	/**
	 * Sets the reprocessed flag of this ivld lot master.
	 *
	 * @param reprocessedFlag the reprocessed flag of this ivld lot master
	 */
	public void setReprocessedFlag(String reprocessedFlag);

	/**
	 * Returns the lot master intfid of this ivld lot master.
	 *
	 * @return the lot master intfid of this ivld lot master
	 */
	@AutoEscape
	public String getLotMasterIntfid();

	/**
	 * Sets the lot master intfid of this ivld lot master.
	 *
	 * @param lotMasterIntfid the lot master intfid of this ivld lot master
	 */
	public void setLotMasterIntfid(String lotMasterIntfid);

	/**
	 * Returns the check record of this ivld lot master.
	 *
	 * @return the check record of this ivld lot master
	 */
	public boolean getCheckRecord();

	/**
	 * Returns <code>true</code> if this ivld lot master is check record.
	 *
	 * @return <code>true</code> if this ivld lot master is check record; <code>false</code> otherwise
	 */
	public boolean isCheckRecord();

	/**
	 * Sets whether this ivld lot master is check record.
	 *
	 * @param checkRecord the check record of this ivld lot master
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
	public int compareTo(IvldLotMaster ivldLotMaster);

	@Override
	public int hashCode();

	@Override
	public CacheModel<IvldLotMaster> toCacheModel();

	@Override
	public IvldLotMaster toEscapedModel();

	@Override
	public IvldLotMaster toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}