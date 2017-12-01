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

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the NaProjDetails service. Represents a row in the &quot;NA_PROJ_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.NaProjDetailsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.NaProjDetailsImpl}.
 * </p>
 *
 * @author
 * @see NaProjDetails
 * @see com.stpl.app.model.impl.NaProjDetailsImpl
 * @see com.stpl.app.model.impl.NaProjDetailsModelImpl
 * @generated
 */
@ProviderType
public interface NaProjDetailsModel extends BaseModel<NaProjDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a na proj details model instance should use the {@link NaProjDetails} interface instead.
	 */

	/**
	 * Returns the primary key of this na proj details.
	 *
	 * @return the primary key of this na proj details
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this na proj details.
	 *
	 * @param primaryKey the primary key of this na proj details
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the item master sid of this na proj details.
	 *
	 * @return the item master sid of this na proj details
	 */
	public int getItemMasterSid();

	/**
	 * Sets the item master sid of this na proj details.
	 *
	 * @param itemMasterSid the item master sid of this na proj details
	 */
	public void setItemMasterSid(int itemMasterSid);

	/**
	 * Returns the na proj master sid of this na proj details.
	 *
	 * @return the na proj master sid of this na proj details
	 */
	public int getNaProjMasterSid();

	/**
	 * Sets the na proj master sid of this na proj details.
	 *
	 * @param naProjMasterSid the na proj master sid of this na proj details
	 */
	public void setNaProjMasterSid(int naProjMasterSid);

	/**
	 * Returns the na proj details sid of this na proj details.
	 *
	 * @return the na proj details sid of this na proj details
	 */
	public int getNaProjDetailsSid();

	/**
	 * Sets the na proj details sid of this na proj details.
	 *
	 * @param naProjDetailsSid the na proj details sid of this na proj details
	 */
	public void setNaProjDetailsSid(int naProjDetailsSid);

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
	public int compareTo(NaProjDetails naProjDetails);

	@Override
	public int hashCode();

	@Override
	public CacheModel<NaProjDetails> toCacheModel();

	@Override
	public NaProjDetails toEscapedModel();

	@Override
	public NaProjDetails toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}