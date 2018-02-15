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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.model.ProjectionNameConfig;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the projection name config service. This utility wraps {@link com.stpl.app.service.persistence.impl.ProjectionNameConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionNameConfigPersistence
 * @see com.stpl.app.service.persistence.impl.ProjectionNameConfigPersistenceImpl
 * @generated
 */
@ProviderType
public class ProjectionNameConfigUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ProjectionNameConfig projectionNameConfig) {
		getPersistence().clearCache(projectionNameConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProjectionNameConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProjectionNameConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProjectionNameConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProjectionNameConfig> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProjectionNameConfig update(
		ProjectionNameConfig projectionNameConfig) {
		return getPersistence().update(projectionNameConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProjectionNameConfig update(
		ProjectionNameConfig projectionNameConfig, ServiceContext serviceContext) {
		return getPersistence().update(projectionNameConfig, serviceContext);
	}

	/**
	* Caches the projection name config in the entity cache if it is enabled.
	*
	* @param projectionNameConfig the projection name config
	*/
	public static void cacheResult(ProjectionNameConfig projectionNameConfig) {
		getPersistence().cacheResult(projectionNameConfig);
	}

	/**
	* Caches the projection name configs in the entity cache if it is enabled.
	*
	* @param projectionNameConfigs the projection name configs
	*/
	public static void cacheResult(
		List<ProjectionNameConfig> projectionNameConfigs) {
		getPersistence().cacheResult(projectionNameConfigs);
	}

	/**
	* Creates a new projection name config with the primary key. Does not add the projection name config to the database.
	*
	* @param projectionNameConfigSid the primary key for the new projection name config
	* @return the new projection name config
	*/
	public static ProjectionNameConfig create(int projectionNameConfigSid) {
		return getPersistence().create(projectionNameConfigSid);
	}

	/**
	* Removes the projection name config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionNameConfigSid the primary key of the projection name config
	* @return the projection name config that was removed
	* @throws NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
	*/
	public static ProjectionNameConfig remove(int projectionNameConfigSid)
		throws com.stpl.app.exception.NoSuchProjectionNameConfigException {
		return getPersistence().remove(projectionNameConfigSid);
	}

	public static ProjectionNameConfig updateImpl(
		ProjectionNameConfig projectionNameConfig) {
		return getPersistence().updateImpl(projectionNameConfig);
	}

	/**
	* Returns the projection name config with the primary key or throws a {@link NoSuchProjectionNameConfigException} if it could not be found.
	*
	* @param projectionNameConfigSid the primary key of the projection name config
	* @return the projection name config
	* @throws NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
	*/
	public static ProjectionNameConfig findByPrimaryKey(
		int projectionNameConfigSid)
		throws com.stpl.app.exception.NoSuchProjectionNameConfigException {
		return getPersistence().findByPrimaryKey(projectionNameConfigSid);
	}

	/**
	* Returns the projection name config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionNameConfigSid the primary key of the projection name config
	* @return the projection name config, or <code>null</code> if a projection name config with the primary key could not be found
	*/
	public static ProjectionNameConfig fetchByPrimaryKey(
		int projectionNameConfigSid) {
		return getPersistence().fetchByPrimaryKey(projectionNameConfigSid);
	}

	public static java.util.Map<java.io.Serializable, ProjectionNameConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the projection name configs.
	*
	* @return the projection name configs
	*/
	public static List<ProjectionNameConfig> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ProjectionNameConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ProjectionNameConfig> findAll(int start, int end,
		OrderByComparator<ProjectionNameConfig> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ProjectionNameConfig> findAll(int start, int end,
		OrderByComparator<ProjectionNameConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the projection name configs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of projection name configs.
	*
	* @return the number of projection name configs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProjectionNameConfigPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProjectionNameConfigPersistence, ProjectionNameConfigPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ProjectionNameConfigPersistence.class);
}