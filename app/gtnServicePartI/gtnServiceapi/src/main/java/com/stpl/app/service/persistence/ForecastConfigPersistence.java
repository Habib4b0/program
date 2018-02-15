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

import com.stpl.app.exception.NoSuchForecastConfigException;
import com.stpl.app.model.ForecastConfig;

/**
 * The persistence interface for the forecast config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ForecastConfigPersistenceImpl
 * @see ForecastConfigUtil
 * @generated
 */
@ProviderType
public interface ForecastConfigPersistence extends BasePersistence<ForecastConfig> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ForecastConfigUtil} to access the forecast config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the forecast config in the entity cache if it is enabled.
	*
	* @param forecastConfig the forecast config
	*/
	public void cacheResult(ForecastConfig forecastConfig);

	/**
	* Caches the forecast configs in the entity cache if it is enabled.
	*
	* @param forecastConfigs the forecast configs
	*/
	public void cacheResult(java.util.List<ForecastConfig> forecastConfigs);

	/**
	* Creates a new forecast config with the primary key. Does not add the forecast config to the database.
	*
	* @param forecastConfigSid the primary key for the new forecast config
	* @return the new forecast config
	*/
	public ForecastConfig create(int forecastConfigSid);

	/**
	* Removes the forecast config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param forecastConfigSid the primary key of the forecast config
	* @return the forecast config that was removed
	* @throws NoSuchForecastConfigException if a forecast config with the primary key could not be found
	*/
	public ForecastConfig remove(int forecastConfigSid)
		throws NoSuchForecastConfigException;

	public ForecastConfig updateImpl(ForecastConfig forecastConfig);

	/**
	* Returns the forecast config with the primary key or throws a {@link NoSuchForecastConfigException} if it could not be found.
	*
	* @param forecastConfigSid the primary key of the forecast config
	* @return the forecast config
	* @throws NoSuchForecastConfigException if a forecast config with the primary key could not be found
	*/
	public ForecastConfig findByPrimaryKey(int forecastConfigSid)
		throws NoSuchForecastConfigException;

	/**
	* Returns the forecast config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param forecastConfigSid the primary key of the forecast config
	* @return the forecast config, or <code>null</code> if a forecast config with the primary key could not be found
	*/
	public ForecastConfig fetchByPrimaryKey(int forecastConfigSid);

	@Override
	public java.util.Map<java.io.Serializable, ForecastConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the forecast configs.
	*
	* @return the forecast configs
	*/
	public java.util.List<ForecastConfig> findAll();

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
	public java.util.List<ForecastConfig> findAll(int start, int end);

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
	public java.util.List<ForecastConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastConfig> orderByComparator);

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
	public java.util.List<ForecastConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the forecast configs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of forecast configs.
	*
	* @return the number of forecast configs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}