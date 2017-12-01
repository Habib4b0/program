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
 * The base model interface for the MasterDataFiles service. Represents a row in the &quot;MASTER_DATA_FILES&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.MasterDataFilesModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.MasterDataFilesImpl}.
 * </p>
 *
 * @author
 * @see MasterDataFiles
 * @see com.stpl.app.model.impl.MasterDataFilesImpl
 * @see com.stpl.app.model.impl.MasterDataFilesModelImpl
 * @generated
 */
@ProviderType
public interface MasterDataFilesModel extends BaseModel<MasterDataFiles> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a master data files model instance should use the {@link MasterDataFiles} interface instead.
	 */

	/**
	 * Returns the primary key of this master data files.
	 *
	 * @return the primary key of this master data files
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this master data files.
	 *
	 * @param primaryKey the primary key of this master data files
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the master table sid of this master data files.
	 *
	 * @return the master table sid of this master data files
	 */
	public int getMasterTableSid();

	/**
	 * Sets the master table sid of this master data files.
	 *
	 * @param masterTableSid the master table sid of this master data files
	 */
	public void setMasterTableSid(int masterTableSid);

	/**
	 * Returns the master data files sid of this master data files.
	 *
	 * @return the master data files sid of this master data files
	 */
	public int getMasterDataFilesSid();

	/**
	 * Sets the master data files sid of this master data files.
	 *
	 * @param masterDataFilesSid the master data files sid of this master data files
	 */
	public void setMasterDataFilesSid(int masterDataFilesSid);

	/**
	 * Returns the master table name of this master data files.
	 *
	 * @return the master table name of this master data files
	 */
	@AutoEscape
	public String getMasterTableName();

	/**
	 * Sets the master table name of this master data files.
	 *
	 * @param masterTableName the master table name of this master data files
	 */
	public void setMasterTableName(String masterTableName);

	/**
	 * Returns the file path of this master data files.
	 *
	 * @return the file path of this master data files
	 */
	@AutoEscape
	public String getFilePath();

	/**
	 * Sets the file path of this master data files.
	 *
	 * @param filePath the file path of this master data files
	 */
	public void setFilePath(String filePath);

	/**
	 * Returns the created date of this master data files.
	 *
	 * @return the created date of this master data files
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this master data files.
	 *
	 * @param createdDate the created date of this master data files
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the created by of this master data files.
	 *
	 * @return the created by of this master data files
	 */
	public int getCreatedBy();

	/**
	 * Sets the created by of this master data files.
	 *
	 * @param createdBy the created by of this master data files
	 */
	public void setCreatedBy(int createdBy);

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
	public int compareTo(MasterDataFiles masterDataFiles);

	@Override
	public int hashCode();

	@Override
	public CacheModel<MasterDataFiles> toCacheModel();

	@Override
	public MasterDataFiles toEscapedModel();

	@Override
	public MasterDataFiles toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}