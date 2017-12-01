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
 * The base model interface for the HierarchyLevelValues service. Represents a row in the &quot;HIERARCHY_LEVEL_VALUES&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.HierarchyLevelValuesImpl}.
 * </p>
 *
 * @author
 * @see HierarchyLevelValues
 * @see com.stpl.app.model.impl.HierarchyLevelValuesImpl
 * @see com.stpl.app.model.impl.HierarchyLevelValuesModelImpl
 * @generated
 */
@ProviderType
public interface HierarchyLevelValuesModel extends BaseModel<HierarchyLevelValues> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a hierarchy level values model instance should use the {@link HierarchyLevelValues} interface instead.
	 */

	/**
	 * Returns the primary key of this hierarchy level values.
	 *
	 * @return the primary key of this hierarchy level values
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this hierarchy level values.
	 *
	 * @param primaryKey the primary key of this hierarchy level values
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the level values of this hierarchy level values.
	 *
	 * @return the level values of this hierarchy level values
	 */
	@AutoEscape
	public String getLevelValues();

	/**
	 * Sets the level values of this hierarchy level values.
	 *
	 * @param levelValues the level values of this hierarchy level values
	 */
	public void setLevelValues(String levelValues);

	/**
	 * Returns the hierarchy level values sid of this hierarchy level values.
	 *
	 * @return the hierarchy level values sid of this hierarchy level values
	 */
	public int getHierarchyLevelValuesSid();

	/**
	 * Sets the hierarchy level values sid of this hierarchy level values.
	 *
	 * @param hierarchyLevelValuesSid the hierarchy level values sid of this hierarchy level values
	 */
	public void setHierarchyLevelValuesSid(int hierarchyLevelValuesSid);

	/**
	 * Returns the created date of this hierarchy level values.
	 *
	 * @return the created date of this hierarchy level values
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this hierarchy level values.
	 *
	 * @param createdDate the created date of this hierarchy level values
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the created by of this hierarchy level values.
	 *
	 * @return the created by of this hierarchy level values
	 */
	public int getCreatedBy();

	/**
	 * Sets the created by of this hierarchy level values.
	 *
	 * @param createdBy the created by of this hierarchy level values
	 */
	public void setCreatedBy(int createdBy);

	/**
	 * Returns the hierarchy level definition sid of this hierarchy level values.
	 *
	 * @return the hierarchy level definition sid of this hierarchy level values
	 */
	public int getHierarchyLevelDefinitionSid();

	/**
	 * Sets the hierarchy level definition sid of this hierarchy level values.
	 *
	 * @param hierarchyLevelDefinitionSid the hierarchy level definition sid of this hierarchy level values
	 */
	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid);

	/**
	 * Returns the version no of this hierarchy level values.
	 *
	 * @return the version no of this hierarchy level values
	 */
	public int getVersionNo();

	/**
	 * Sets the version no of this hierarchy level values.
	 *
	 * @param versionNo the version no of this hierarchy level values
	 */
	public void setVersionNo(int versionNo);

	/**
	 * Returns the modified by of this hierarchy level values.
	 *
	 * @return the modified by of this hierarchy level values
	 */
	public int getModifiedBy();

	/**
	 * Sets the modified by of this hierarchy level values.
	 *
	 * @param modifiedBy the modified by of this hierarchy level values
	 */
	public void setModifiedBy(int modifiedBy);

	/**
	 * Returns the modified date of this hierarchy level values.
	 *
	 * @return the modified date of this hierarchy level values
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this hierarchy level values.
	 *
	 * @param modifiedDate the modified date of this hierarchy level values
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
	public int compareTo(HierarchyLevelValues hierarchyLevelValues);

	@Override
	public int hashCode();

	@Override
	public CacheModel<HierarchyLevelValues> toCacheModel();

	@Override
	public HierarchyLevelValues toEscapedModel();

	@Override
	public HierarchyLevelValues toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}