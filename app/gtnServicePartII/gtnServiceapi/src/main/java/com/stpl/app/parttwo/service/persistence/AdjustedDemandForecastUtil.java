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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.parttwo.model.AdjustedDemandForecast;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the adjusted demand forecast service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.AdjustedDemandForecastPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AdjustedDemandForecastPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.AdjustedDemandForecastPersistenceImpl
 * @generated
 */
@ProviderType
public class AdjustedDemandForecastUtil {
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
	public static void clearCache(AdjustedDemandForecast adjustedDemandForecast) {
		getPersistence().clearCache(adjustedDemandForecast);
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
	public static List<AdjustedDemandForecast> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AdjustedDemandForecast> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AdjustedDemandForecast> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AdjustedDemandForecast> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AdjustedDemandForecast update(
		AdjustedDemandForecast adjustedDemandForecast) {
		return getPersistence().update(adjustedDemandForecast);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AdjustedDemandForecast update(
		AdjustedDemandForecast adjustedDemandForecast,
		ServiceContext serviceContext) {
		return getPersistence().update(adjustedDemandForecast, serviceContext);
	}

	/**
	* Caches the adjusted demand forecast in the entity cache if it is enabled.
	*
	* @param adjustedDemandForecast the adjusted demand forecast
	*/
	public static void cacheResult(
		AdjustedDemandForecast adjustedDemandForecast) {
		getPersistence().cacheResult(adjustedDemandForecast);
	}

	/**
	* Caches the adjusted demand forecasts in the entity cache if it is enabled.
	*
	* @param adjustedDemandForecasts the adjusted demand forecasts
	*/
	public static void cacheResult(
		List<AdjustedDemandForecast> adjustedDemandForecasts) {
		getPersistence().cacheResult(adjustedDemandForecasts);
	}

	/**
	* Creates a new adjusted demand forecast with the primary key. Does not add the adjusted demand forecast to the database.
	*
	* @param adjustedDemandForecastSid the primary key for the new adjusted demand forecast
	* @return the new adjusted demand forecast
	*/
	public static AdjustedDemandForecast create(int adjustedDemandForecastSid) {
		return getPersistence().create(adjustedDemandForecastSid);
	}

	/**
	* Removes the adjusted demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
	* @return the adjusted demand forecast that was removed
	* @throws NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
	*/
	public static AdjustedDemandForecast remove(int adjustedDemandForecastSid)
		throws com.stpl.app.parttwo.exception.NoSuchAdjustedDemandForecastException {
		return getPersistence().remove(adjustedDemandForecastSid);
	}

	public static AdjustedDemandForecast updateImpl(
		AdjustedDemandForecast adjustedDemandForecast) {
		return getPersistence().updateImpl(adjustedDemandForecast);
	}

	/**
	* Returns the adjusted demand forecast with the primary key or throws a {@link NoSuchAdjustedDemandForecastException} if it could not be found.
	*
	* @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
	* @return the adjusted demand forecast
	* @throws NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
	*/
	public static AdjustedDemandForecast findByPrimaryKey(
		int adjustedDemandForecastSid)
		throws com.stpl.app.parttwo.exception.NoSuchAdjustedDemandForecastException {
		return getPersistence().findByPrimaryKey(adjustedDemandForecastSid);
	}

	/**
	* Returns the adjusted demand forecast with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
	* @return the adjusted demand forecast, or <code>null</code> if a adjusted demand forecast with the primary key could not be found
	*/
	public static AdjustedDemandForecast fetchByPrimaryKey(
		int adjustedDemandForecastSid) {
		return getPersistence().fetchByPrimaryKey(adjustedDemandForecastSid);
	}

	public static java.util.Map<java.io.Serializable, AdjustedDemandForecast> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the adjusted demand forecasts.
	*
	* @return the adjusted demand forecasts
	*/
	public static List<AdjustedDemandForecast> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the adjusted demand forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of adjusted demand forecasts
	* @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
	* @return the range of adjusted demand forecasts
	*/
	public static List<AdjustedDemandForecast> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the adjusted demand forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of adjusted demand forecasts
	* @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of adjusted demand forecasts
	*/
	public static List<AdjustedDemandForecast> findAll(int start, int end,
		OrderByComparator<AdjustedDemandForecast> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the adjusted demand forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of adjusted demand forecasts
	* @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of adjusted demand forecasts
	*/
	public static List<AdjustedDemandForecast> findAll(int start, int end,
		OrderByComparator<AdjustedDemandForecast> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the adjusted demand forecasts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of adjusted demand forecasts.
	*
	* @return the number of adjusted demand forecasts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AdjustedDemandForecastPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AdjustedDemandForecastPersistence, AdjustedDemandForecastPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AdjustedDemandForecastPersistence.class);
}