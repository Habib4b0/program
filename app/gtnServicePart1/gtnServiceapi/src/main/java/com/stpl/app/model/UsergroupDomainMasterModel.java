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
 * The base model interface for the UsergroupDomainMaster service. Represents a row in the &quot;USERGROUP_DOMAIN_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.UsergroupDomainMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.UsergroupDomainMasterImpl}.
 * </p>
 *
 * @author
 * @see UsergroupDomainMaster
 * @see com.stpl.app.model.impl.UsergroupDomainMasterImpl
 * @see com.stpl.app.model.impl.UsergroupDomainMasterModelImpl
 * @generated
 */
@ProviderType
public interface UsergroupDomainMasterModel extends BaseModel<UsergroupDomainMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a usergroup domain master model instance should use the {@link UsergroupDomainMaster} interface instead.
	 */

	/**
	 * Returns the primary key of this usergroup domain master.
	 *
	 * @return the primary key of this usergroup domain master
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this usergroup domain master.
	 *
	 * @param primaryKey the primary key of this usergroup domain master
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the created by of this usergroup domain master.
	 *
	 * @return the created by of this usergroup domain master
	 */
	public int getCreatedBy();

	/**
	 * Sets the created by of this usergroup domain master.
	 *
	 * @param createdBy the created by of this usergroup domain master
	 */
	public void setCreatedBy(int createdBy);

	/**
	 * Returns the usergroup domain sid of this usergroup domain master.
	 *
	 * @return the usergroup domain sid of this usergroup domain master
	 */
	public int getUsergroupDomainSid();

	/**
	 * Sets the usergroup domain sid of this usergroup domain master.
	 *
	 * @param usergroupDomainSid the usergroup domain sid of this usergroup domain master
	 */
	public void setUsergroupDomainSid(int usergroupDomainSid);

	/**
	 * Returns the users sid of this usergroup domain master.
	 *
	 * @return the users sid of this usergroup domain master
	 */
	public int getUsersSid();

	/**
	 * Sets the users sid of this usergroup domain master.
	 *
	 * @param usersSid the users sid of this usergroup domain master
	 */
	public void setUsersSid(int usersSid);

	/**
	 * Returns the modified by of this usergroup domain master.
	 *
	 * @return the modified by of this usergroup domain master
	 */
	public int getModifiedBy();

	/**
	 * Sets the modified by of this usergroup domain master.
	 *
	 * @param modifiedBy the modified by of this usergroup domain master
	 */
	public void setModifiedBy(int modifiedBy);

	/**
	 * Returns the created date of this usergroup domain master.
	 *
	 * @return the created date of this usergroup domain master
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this usergroup domain master.
	 *
	 * @param createdDate the created date of this usergroup domain master
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the domain ID of this usergroup domain master.
	 *
	 * @return the domain ID of this usergroup domain master
	 */
	public int getDomainId();

	/**
	 * Sets the domain ID of this usergroup domain master.
	 *
	 * @param domainId the domain ID of this usergroup domain master
	 */
	public void setDomainId(int domainId);

	/**
	 * Returns the processed of this usergroup domain master.
	 *
	 * @return the processed of this usergroup domain master
	 */
	@AutoEscape
	public String getProcessed();

	/**
	 * Sets the processed of this usergroup domain master.
	 *
	 * @param processed the processed of this usergroup domain master
	 */
	public void setProcessed(String processed);

	/**
	 * Returns the usergroup ID of this usergroup domain master.
	 *
	 * @return the usergroup ID of this usergroup domain master
	 */
	public int getUsergroupId();

	/**
	 * Sets the usergroup ID of this usergroup domain master.
	 *
	 * @param usergroupId the usergroup ID of this usergroup domain master
	 */
	public void setUsergroupId(int usergroupId);

	/**
	 * Returns the version no of this usergroup domain master.
	 *
	 * @return the version no of this usergroup domain master
	 */
	public int getVersionNo();

	/**
	 * Sets the version no of this usergroup domain master.
	 *
	 * @param versionNo the version no of this usergroup domain master
	 */
	public void setVersionNo(int versionNo);

	/**
	 * Returns the is active of this usergroup domain master.
	 *
	 * @return the is active of this usergroup domain master
	 */
	@AutoEscape
	public String getIsActive();

	/**
	 * Sets the is active of this usergroup domain master.
	 *
	 * @param isActive the is active of this usergroup domain master
	 */
	public void setIsActive(String isActive);

	/**
	 * Returns the modified date of this usergroup domain master.
	 *
	 * @return the modified date of this usergroup domain master
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this usergroup domain master.
	 *
	 * @param modifiedDate the modified date of this usergroup domain master
	 */
	public void setModifiedDate(Date modifiedDate);

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
	public int compareTo(UsergroupDomainMaster usergroupDomainMaster);

	@Override
	public int hashCode();

	@Override
	public CacheModel<UsergroupDomainMaster> toCacheModel();

	@Override
	public UsergroupDomainMaster toEscapedModel();

	@Override
	public UsergroupDomainMaster toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}