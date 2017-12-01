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
 * The base model interface for the RsDetailsFr service. Represents a row in the &quot;RS_DETAILS_FR&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.RsDetailsFrModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.RsDetailsFrImpl}.
 * </p>
 *
 * @author
 * @see RsDetailsFr
 * @see com.stpl.app.model.impl.RsDetailsFrImpl
 * @see com.stpl.app.model.impl.RsDetailsFrModelImpl
 * @generated
 */
@ProviderType
public interface RsDetailsFrModel extends BaseModel<RsDetailsFr> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a rs details fr model instance should use the {@link RsDetailsFr} interface instead.
	 */

	/**
	 * Returns the primary key of this rs details fr.
	 *
	 * @return the primary key of this rs details fr
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this rs details fr.
	 *
	 * @param primaryKey the primary key of this rs details fr
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the record lock status of this rs details fr.
	 *
	 * @return the record lock status of this rs details fr
	 */
	public boolean getRecordLockStatus();

	/**
	 * Returns <code>true</code> if this rs details fr is record lock status.
	 *
	 * @return <code>true</code> if this rs details fr is record lock status; <code>false</code> otherwise
	 */
	public boolean isRecordLockStatus();

	/**
	 * Sets whether this rs details fr is record lock status.
	 *
	 * @param recordLockStatus the record lock status of this rs details fr
	 */
	public void setRecordLockStatus(boolean recordLockStatus);

	/**
	 * Returns the created date of this rs details fr.
	 *
	 * @return the created date of this rs details fr
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this rs details fr.
	 *
	 * @param createdDate the created date of this rs details fr
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the created by of this rs details fr.
	 *
	 * @return the created by of this rs details fr
	 */
	public int getCreatedBy();

	/**
	 * Sets the created by of this rs details fr.
	 *
	 * @param createdBy the created by of this rs details fr
	 */
	public void setCreatedBy(int createdBy);

	/**
	 * Returns the source of this rs details fr.
	 *
	 * @return the source of this rs details fr
	 */
	@AutoEscape
	public String getSource();

	/**
	 * Sets the source of this rs details fr.
	 *
	 * @param source the source of this rs details fr
	 */
	public void setSource(String source);

	/**
	 * Returns the formula method ID of this rs details fr.
	 *
	 * @return the formula method ID of this rs details fr
	 */
	@AutoEscape
	public String getFormulaMethodId();

	/**
	 * Sets the formula method ID of this rs details fr.
	 *
	 * @param formulaMethodId the formula method ID of this rs details fr
	 */
	public void setFormulaMethodId(String formulaMethodId);

	/**
	 * Returns the batch ID of this rs details fr.
	 *
	 * @return the batch ID of this rs details fr
	 */
	@AutoEscape
	public String getBatchId();

	/**
	 * Sets the batch ID of this rs details fr.
	 *
	 * @param batchId the batch ID of this rs details fr
	 */
	public void setBatchId(String batchId);

	/**
	 * Returns the modified by of this rs details fr.
	 *
	 * @return the modified by of this rs details fr
	 */
	public int getModifiedBy();

	/**
	 * Sets the modified by of this rs details fr.
	 *
	 * @param modifiedBy the modified by of this rs details fr
	 */
	public void setModifiedBy(int modifiedBy);

	/**
	 * Returns the inbound status of this rs details fr.
	 *
	 * @return the inbound status of this rs details fr
	 */
	@AutoEscape
	public String getInboundStatus();

	/**
	 * Sets the inbound status of this rs details fr.
	 *
	 * @param inboundStatus the inbound status of this rs details fr
	 */
	public void setInboundStatus(String inboundStatus);

	/**
	 * Returns the formula ID of this rs details fr.
	 *
	 * @return the formula ID of this rs details fr
	 */
	public int getFormulaId();

	/**
	 * Sets the formula ID of this rs details fr.
	 *
	 * @param formulaId the formula ID of this rs details fr
	 */
	public void setFormulaId(int formulaId);

	/**
	 * Returns the item master sid of this rs details fr.
	 *
	 * @return the item master sid of this rs details fr
	 */
	public int getItemMasterSid();

	/**
	 * Sets the item master sid of this rs details fr.
	 *
	 * @param itemMasterSid the item master sid of this rs details fr
	 */
	public void setItemMasterSid(int itemMasterSid);

	/**
	 * Returns the rs details sid of this rs details fr.
	 *
	 * @return the rs details sid of this rs details fr
	 */
	public int getRsDetailsSid();

	/**
	 * Sets the rs details sid of this rs details fr.
	 *
	 * @param rsDetailsSid the rs details sid of this rs details fr
	 */
	public void setRsDetailsSid(int rsDetailsSid);

	/**
	 * Returns the modified date of this rs details fr.
	 *
	 * @return the modified date of this rs details fr
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this rs details fr.
	 *
	 * @param modifiedDate the modified date of this rs details fr
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the rs details fr sid of this rs details fr.
	 *
	 * @return the rs details fr sid of this rs details fr
	 */
	public int getRsDetailsFrSid();

	/**
	 * Sets the rs details fr sid of this rs details fr.
	 *
	 * @param rsDetailsFrSid the rs details fr sid of this rs details fr
	 */
	public void setRsDetailsFrSid(int rsDetailsFrSid);

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
	public int compareTo(RsDetailsFr rsDetailsFr);

	@Override
	public int hashCode();

	@Override
	public CacheModel<RsDetailsFr> toCacheModel();

	@Override
	public RsDetailsFr toEscapedModel();

	@Override
	public RsDetailsFr toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}