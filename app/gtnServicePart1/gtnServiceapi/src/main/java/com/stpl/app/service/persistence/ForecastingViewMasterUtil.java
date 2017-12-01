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

import com.stpl.app.model.ForecastingViewMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the forecasting view master service. This utility wraps {@link com.stpl.app.service.persistence.impl.ForecastingViewMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingViewMasterPersistence
 * @see com.stpl.app.service.persistence.impl.ForecastingViewMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class ForecastingViewMasterUtil {
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
	public static void clearCache(ForecastingViewMaster forecastingViewMaster) {
		getPersistence().clearCache(forecastingViewMaster);
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
	public static List<ForecastingViewMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ForecastingViewMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ForecastingViewMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ForecastingViewMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ForecastingViewMaster update(
		ForecastingViewMaster forecastingViewMaster) {
		return getPersistence().update(forecastingViewMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ForecastingViewMaster update(
		ForecastingViewMaster forecastingViewMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(forecastingViewMaster, serviceContext);
	}

	/**
	* Caches the forecasting view master in the entity cache if it is enabled.
	*
	* @param forecastingViewMaster the forecasting view master
	*/
	public static void cacheResult(ForecastingViewMaster forecastingViewMaster) {
		getPersistence().cacheResult(forecastingViewMaster);
	}

	/**
	* Caches the forecasting view masters in the entity cache if it is enabled.
	*
	* @param forecastingViewMasters the forecasting view masters
	*/
	public static void cacheResult(
		List<ForecastingViewMaster> forecastingViewMasters) {
		getPersistence().cacheResult(forecastingViewMasters);
	}

	/**
	* Creates a new forecasting view master with the primary key. Does not add the forecasting view master to the database.
	*
	* @param viewId the primary key for the new forecasting view master
	* @return the new forecasting view master
	*/
	public static ForecastingViewMaster create(int viewId) {
		return getPersistence().create(viewId);
	}

	/**
	* Removes the forecasting view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param viewId the primary key of the forecasting view master
	* @return the forecasting view master that was removed
	* @throws NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
	*/
	public static ForecastingViewMaster remove(int viewId)
		throws com.stpl.app.exception.NoSuchForecastingViewMasterException {
		return getPersistence().remove(viewId);
	}

	public static ForecastingViewMaster updateImpl(
		ForecastingViewMaster forecastingViewMaster) {
		return getPersistence().updateImpl(forecastingViewMaster);
	}

	/**
	* Returns the forecasting view master with the primary key or throws a {@link NoSuchForecastingViewMasterException} if it could not be found.
	*
	* @param viewId the primary key of the forecasting view master
	* @return the forecasting view master
	* @throws NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
	*/
	public static ForecastingViewMaster findByPrimaryKey(int viewId)
		throws com.stpl.app.exception.NoSuchForecastingViewMasterException {
		return getPersistence().findByPrimaryKey(viewId);
	}

	/**
	* Returns the forecasting view master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param viewId the primary key of the forecasting view master
	* @return the forecasting view master, or <code>null</code> if a forecasting view master with the primary key could not be found
	*/
	public static ForecastingViewMaster fetchByPrimaryKey(int viewId) {
		return getPersistence().fetchByPrimaryKey(viewId);
	}

	public static java.util.Map<java.io.Serializable, ForecastingViewMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the forecasting view masters.
	*
	* @return the forecasting view masters
	*/
	public static List<ForecastingViewMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the forecasting view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting view masters
	* @param end the upper bound of the range of forecasting view masters (not inclusive)
	* @return the range of forecasting view masters
	*/
	public static List<ForecastingViewMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the forecasting view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting view masters
	* @param end the upper bound of the range of forecasting view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of forecasting view masters
	*/
	public static List<ForecastingViewMaster> findAll(int start, int end,
		OrderByComparator<ForecastingViewMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the forecasting view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting view masters
	* @param end the upper bound of the range of forecasting view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of forecasting view masters
	*/
	public static List<ForecastingViewMaster> findAll(int start, int end,
		OrderByComparator<ForecastingViewMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the forecasting view masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of forecasting view masters.
	*
	* @return the number of forecasting view masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ForecastingViewMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ForecastingViewMasterPersistence, ForecastingViewMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ForecastingViewMasterPersistence.class);
}