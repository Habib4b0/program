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
 * The base model interface for the ProjectionDetails service. Represents a row in the &quot;PROJECTION_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.ProjectionDetailsImpl}.
 * </p>
 *
 * @author
 * @see ProjectionDetails
 * @see com.stpl.app.model.impl.ProjectionDetailsImpl
 * @see com.stpl.app.model.impl.ProjectionDetailsModelImpl
 * @generated
 */
@ProviderType
public interface ProjectionDetailsModel extends BaseModel<ProjectionDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a projection details model instance should use the {@link ProjectionDetails} interface instead.
	 */

	/**
	 * Returns the primary key of this projection details.
	 *
	 * @return the primary key of this projection details
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this projection details.
	 *
	 * @param primaryKey the primary key of this projection details
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the projection details sid of this projection details.
	 *
	 * @return the projection details sid of this projection details
	 */
	public int getProjectionDetailsSid();

	/**
	 * Sets the projection details sid of this projection details.
	 *
	 * @param projectionDetailsSid the projection details sid of this projection details
	 */
	public void setProjectionDetailsSid(int projectionDetailsSid);

	/**
	 * Returns the ccp details sid of this projection details.
	 *
	 * @return the ccp details sid of this projection details
	 */
	public int getCcpDetailsSid();

	/**
	 * Sets the ccp details sid of this projection details.
	 *
	 * @param ccpDetailsSid the ccp details sid of this projection details
	 */
	public void setCcpDetailsSid(int ccpDetailsSid);

	/**
	 * Returns the projection master sid of this projection details.
	 *
	 * @return the projection master sid of this projection details
	 */
	public int getProjectionMasterSid();

	/**
	 * Sets the projection master sid of this projection details.
	 *
	 * @param projectionMasterSid the projection master sid of this projection details
	 */
	public void setProjectionMasterSid(int projectionMasterSid);

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
	public int compareTo(ProjectionDetails projectionDetails);

	@Override
	public int hashCode();

	@Override
	public CacheModel<ProjectionDetails> toCacheModel();

	@Override
	public ProjectionDetails toEscapedModel();

	@Override
	public ProjectionDetails toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}