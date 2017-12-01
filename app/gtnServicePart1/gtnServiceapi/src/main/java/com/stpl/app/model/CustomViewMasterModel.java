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
 * The base model interface for the CustomViewMaster service. Represents a row in the &quot;CUSTOM_VIEW_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.CustomViewMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.CustomViewMasterImpl}.
 * </p>
 *
 * @author
 * @see CustomViewMaster
 * @see com.stpl.app.model.impl.CustomViewMasterImpl
 * @see com.stpl.app.model.impl.CustomViewMasterModelImpl
 * @generated
 */
@ProviderType
public interface CustomViewMasterModel extends BaseModel<CustomViewMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a custom view master model instance should use the {@link CustomViewMaster} interface instead.
	 */

	/**
	 * Returns the primary key of this custom view master.
	 *
	 * @return the primary key of this custom view master
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this custom view master.
	 *
	 * @param primaryKey the primary key of this custom view master
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the custom view master sid of this custom view master.
	 *
	 * @return the custom view master sid of this custom view master
	 */
	public int getCustomViewMasterSid();

	/**
	 * Sets the custom view master sid of this custom view master.
	 *
	 * @param customViewMasterSid the custom view master sid of this custom view master
	 */
	public void setCustomViewMasterSid(int customViewMasterSid);

	/**
	 * Returns the created date of this custom view master.
	 *
	 * @return the created date of this custom view master
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this custom view master.
	 *
	 * @param createdDate the created date of this custom view master
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the created by of this custom view master.
	 *
	 * @return the created by of this custom view master
	 */
	public int getCreatedBy();

	/**
	 * Sets the created by of this custom view master.
	 *
	 * @param createdBy the created by of this custom view master
	 */
	public void setCreatedBy(int createdBy);

	/**
	 * Returns the projection master sid of this custom view master.
	 *
	 * @return the projection master sid of this custom view master
	 */
	public int getProjectionMasterSid();

	/**
	 * Sets the projection master sid of this custom view master.
	 *
	 * @param projectionMasterSid the projection master sid of this custom view master
	 */
	public void setProjectionMasterSid(int projectionMasterSid);

	/**
	 * Returns the modified by of this custom view master.
	 *
	 * @return the modified by of this custom view master
	 */
	public int getModifiedBy();

	/**
	 * Sets the modified by of this custom view master.
	 *
	 * @param modifiedBy the modified by of this custom view master
	 */
	public void setModifiedBy(int modifiedBy);

	/**
	 * Returns the modified date of this custom view master.
	 *
	 * @return the modified date of this custom view master
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this custom view master.
	 *
	 * @param modifiedDate the modified date of this custom view master
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the view name of this custom view master.
	 *
	 * @return the view name of this custom view master
	 */
	@AutoEscape
	public String getViewName();

	/**
	 * Sets the view name of this custom view master.
	 *
	 * @param viewName the view name of this custom view master
	 */
	public void setViewName(String viewName);

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
	public int compareTo(CustomViewMaster customViewMaster);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CustomViewMaster> toCacheModel();

	@Override
	public CustomViewMaster toEscapedModel();

	@Override
	public CustomViewMaster toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}