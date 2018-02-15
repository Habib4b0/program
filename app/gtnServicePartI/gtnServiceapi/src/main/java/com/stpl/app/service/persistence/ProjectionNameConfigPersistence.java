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

package com.stpl.app.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.exception.NoSuchProjectionNameConfigException;
import com.stpl.app.model.ProjectionNameConfig;

/**
 * The persistence interface for the projection name config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ProjectionNameConfigPersistenceImpl
 * @see ProjectionNameConfigUtil
 * @generated
 */
@ProviderType
public interface ProjectionNameConfigPersistence extends BasePersistence<ProjectionNameConfig> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectionNameConfigUtil} to access the projection name config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the projection name config in the entity cache if it is enabled.
	*
	* @param projectionNameConfig the projection name config
	*/
	public void cacheResult(ProjectionNameConfig projectionNameConfig);

	/**
	* Caches the projection name configs in the entity cache if it is enabled.
	*
	* @param projectionNameConfigs the projection name configs
	*/
	public void cacheResult(
		java.util.List<ProjectionNameConfig> projectionNameConfigs);

	/**
	* Creates a new projection name config with the primary key. Does not add the projection name config to the database.
	*
	* @param projectionNameConfigSid the primary key for the new projection name config
	* @return the new projection name config
	*/
	public ProjectionNameConfig create(int projectionNameConfigSid);

	/**
	* Removes the projection name config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionNameConfigSid the primary key of the projection name config
	* @return the projection name config that was removed
	* @throws NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
	*/
	public ProjectionNameConfig remove(int projectionNameConfigSid)
		throws NoSuchProjectionNameConfigException;

	public ProjectionNameConfig updateImpl(
		ProjectionNameConfig projectionNameConfig);

	/**
	* Returns the projection name config with the primary key or throws a {@link NoSuchProjectionNameConfigException} if it could not be found.
	*
	* @param projectionNameConfigSid the primary key of the projection name config
	* @return the projection name config
	* @throws NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
	*/
	public ProjectionNameConfig findByPrimaryKey(int projectionNameConfigSid)
		throws NoSuchProjectionNameConfigException;

	/**
	* Returns the projection name config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionNameConfigSid the primary key of the projection name config
	* @return the projection name config, or <code>null</code> if a projection name config with the primary key could not be found
	*/
	public ProjectionNameConfig fetchByPrimaryKey(int projectionNameConfigSid);

	@Override
	public java.util.Map<java.io.Serializable, ProjectionNameConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the projection name configs.
	*
	* @return the projection name configs
	*/
	public java.util.List<ProjectionNameConfig> findAll();

	/**
	* Returns a range of all the projection name configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection name configs
	* @param end the upper bound of the range of projection name configs (not inclusive)
	* @return the range of projection name configs
	*/
	public java.util.List<ProjectionNameConfig> findAll(int start, int end);

	/**
	* Returns an ordered range of all the projection name configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection name configs
	* @param end the upper bound of the range of projection name configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projection name configs
	*/
	public java.util.List<ProjectionNameConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionNameConfig> orderByComparator);

	/**
	* Returns an ordered range of all the projection name configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection name configs
	* @param end the upper bound of the range of projection name configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of projection name configs
	*/
	public java.util.List<ProjectionNameConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionNameConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the projection name configs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of projection name configs.
	*
	* @return the number of projection name configs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}