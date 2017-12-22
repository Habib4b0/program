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

import com.stpl.app.parttwo.model.VwCustomerGtsForecast;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw customer gts forecast service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.VwCustomerGtsForecastPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCustomerGtsForecastPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.VwCustomerGtsForecastPersistenceImpl
 * @generated
 */
@ProviderType
public class VwCustomerGtsForecastUtil {
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
	public static void clearCache(VwCustomerGtsForecast vwCustomerGtsForecast) {
		getPersistence().clearCache(vwCustomerGtsForecast);
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
	public static List<VwCustomerGtsForecast> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwCustomerGtsForecast> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwCustomerGtsForecast> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwCustomerGtsForecast> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwCustomerGtsForecast update(
		VwCustomerGtsForecast vwCustomerGtsForecast) {
		return getPersistence().update(vwCustomerGtsForecast);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwCustomerGtsForecast update(
		VwCustomerGtsForecast vwCustomerGtsForecast,
		ServiceContext serviceContext) {
		return getPersistence().update(vwCustomerGtsForecast, serviceContext);
	}

	/**
	* Caches the vw customer gts forecast in the entity cache if it is enabled.
	*
	* @param vwCustomerGtsForecast the vw customer gts forecast
	*/
	public static void cacheResult(VwCustomerGtsForecast vwCustomerGtsForecast) {
		getPersistence().cacheResult(vwCustomerGtsForecast);
	}

	/**
	* Caches the vw customer gts forecasts in the entity cache if it is enabled.
	*
	* @param vwCustomerGtsForecasts the vw customer gts forecasts
	*/
	public static void cacheResult(
		List<VwCustomerGtsForecast> vwCustomerGtsForecasts) {
		getPersistence().cacheResult(vwCustomerGtsForecasts);
	}

	/**
	* Creates a new vw customer gts forecast with the primary key. Does not add the vw customer gts forecast to the database.
	*
	* @param customerGtsForecastSid the primary key for the new vw customer gts forecast
	* @return the new vw customer gts forecast
	*/
	public static VwCustomerGtsForecast create(int customerGtsForecastSid) {
		return getPersistence().create(customerGtsForecastSid);
	}

	/**
	* Removes the vw customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customerGtsForecastSid the primary key of the vw customer gts forecast
	* @return the vw customer gts forecast that was removed
	* @throws NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
	*/
	public static VwCustomerGtsForecast remove(int customerGtsForecastSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwCustomerGtsForecastException {
		return getPersistence().remove(customerGtsForecastSid);
	}

	public static VwCustomerGtsForecast updateImpl(
		VwCustomerGtsForecast vwCustomerGtsForecast) {
		return getPersistence().updateImpl(vwCustomerGtsForecast);
	}

	/**
	* Returns the vw customer gts forecast with the primary key or throws a {@link NoSuchVwCustomerGtsForecastException} if it could not be found.
	*
	* @param customerGtsForecastSid the primary key of the vw customer gts forecast
	* @return the vw customer gts forecast
	* @throws NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
	*/
	public static VwCustomerGtsForecast findByPrimaryKey(
		int customerGtsForecastSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwCustomerGtsForecastException {
		return getPersistence().findByPrimaryKey(customerGtsForecastSid);
	}

	/**
	* Returns the vw customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param customerGtsForecastSid the primary key of the vw customer gts forecast
	* @return the vw customer gts forecast, or <code>null</code> if a vw customer gts forecast with the primary key could not be found
	*/
	public static VwCustomerGtsForecast fetchByPrimaryKey(
		int customerGtsForecastSid) {
		return getPersistence().fetchByPrimaryKey(customerGtsForecastSid);
	}

	public static java.util.Map<java.io.Serializable, VwCustomerGtsForecast> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw customer gts forecasts.
	*
	* @return the vw customer gts forecasts
	*/
	public static List<VwCustomerGtsForecast> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw customer gts forecasts
	* @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
	* @return the range of vw customer gts forecasts
	*/
	public static List<VwCustomerGtsForecast> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw customer gts forecasts
	* @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw customer gts forecasts
	*/
	public static List<VwCustomerGtsForecast> findAll(int start, int end,
		OrderByComparator<VwCustomerGtsForecast> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw customer gts forecasts
	* @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw customer gts forecasts
	*/
	public static List<VwCustomerGtsForecast> findAll(int start, int end,
		OrderByComparator<VwCustomerGtsForecast> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw customer gts forecasts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw customer gts forecasts.
	*
	* @return the number of vw customer gts forecasts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwCustomerGtsForecastPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwCustomerGtsForecastPersistence, VwCustomerGtsForecastPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwCustomerGtsForecastPersistence.class);
}