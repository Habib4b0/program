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

import com.stpl.app.model.ForecastConfig;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the forecast config service. This utility wraps {@link com.stpl.app.service.persistence.impl.ForecastConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastConfigPersistence
 * @see com.stpl.app.service.persistence.impl.ForecastConfigPersistenceImpl
 * @generated
 */
@ProviderType
public class ForecastConfigUtil {
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
	public static void clearCache(ForecastConfig forecastConfig) {
		getPersistence().clearCache(forecastConfig);
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
	public static List<ForecastConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ForecastConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ForecastConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ForecastConfig> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ForecastConfig update(ForecastConfig forecastConfig) {
		return getPersistence().update(forecastConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ForecastConfig update(ForecastConfig forecastConfig,
		ServiceContext serviceContext) {
		return getPersistence().update(forecastConfig, serviceContext);
	}

	/**
	* Caches the forecast config in the entity cache if it is enabled.
	*
	* @param forecastConfig the forecast config
	*/
	public static void cacheResult(ForecastConfig forecastConfig) {
		getPersistence().cacheResult(forecastConfig);
	}

	/**
	* Caches the forecast configs in the entity cache if it is enabled.
	*
	* @param forecastConfigs the forecast configs
	*/
	public static void cacheResult(List<ForecastConfig> forecastConfigs) {
		getPersistence().cacheResult(forecastConfigs);
	}

	/**
	* Creates a new forecast config with the primary key. Does not add the forecast config to the database.
	*
	* @param forecastConfigSid the primary key for the new forecast config
	* @return the new forecast config
	*/
	public static ForecastConfig create(int forecastConfigSid) {
		return getPersistence().create(forecastConfigSid);
	}

	/**
	* Removes the forecast config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param forecastConfigSid the primary key of the forecast config
	* @return the forecast config that was removed
	* @throws NoSuchForecastConfigException if a forecast config with the primary key could not be found
	*/
	public static ForecastConfig remove(int forecastConfigSid)
		throws com.stpl.app.exception.NoSuchForecastConfigException {
		return getPersistence().remove(forecastConfigSid);
	}

	public static ForecastConfig updateImpl(ForecastConfig forecastConfig) {
		return getPersistence().updateImpl(forecastConfig);
	}

	/**
	* Returns the forecast config with the primary key or throws a {@link NoSuchForecastConfigException} if it could not be found.
	*
	* @param forecastConfigSid the primary key of the forecast config
	* @return the forecast config
	* @throws NoSuchForecastConfigException if a forecast config with the primary key could not be found
	*/
	public static ForecastConfig findByPrimaryKey(int forecastConfigSid)
		throws com.stpl.app.exception.NoSuchForecastConfigException {
		return getPersistence().findByPrimaryKey(forecastConfigSid);
	}

	/**
	* Returns the forecast config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param forecastConfigSid the primary key of the forecast config
	* @return the forecast config, or <code>null</code> if a forecast config with the primary key could not be found
	*/
	public static ForecastConfig fetchByPrimaryKey(int forecastConfigSid) {
		return getPersistence().fetchByPrimaryKey(forecastConfigSid);
	}

	public static java.util.Map<java.io.Serializable, ForecastConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the forecast configs.
	*
	* @return the forecast configs
	*/
	public static List<ForecastConfig> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the forecast configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecast configs
	* @param end the upper bound of the range of forecast configs (not inclusive)
	* @return the range of forecast configs
	*/
	public static List<ForecastConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the forecast configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecast configs
	* @param end the upper bound of the range of forecast configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of forecast configs
	*/
	public static List<ForecastConfig> findAll(int start, int end,
		OrderByComparator<ForecastConfig> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the forecast configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecast configs
	* @param end the upper bound of the range of forecast configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of forecast configs
	*/
	public static List<ForecastConfig> findAll(int start, int end,
		OrderByComparator<ForecastConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the forecast configs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of forecast configs.
	*
	* @return the number of forecast configs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ForecastConfigPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ForecastConfigPersistence, ForecastConfigPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ForecastConfigPersistence.class);
}