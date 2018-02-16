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

import com.stpl.app.model.VwDemandForecastActual;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw demand forecast actual service. This utility wraps {@link com.stpl.app.service.persistence.impl.VwDemandForecastActualPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwDemandForecastActualPersistence
 * @see com.stpl.app.service.persistence.impl.VwDemandForecastActualPersistenceImpl
 * @generated
 */
@ProviderType
public class VwDemandForecastActualUtil {
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
	public static void clearCache(VwDemandForecastActual vwDemandForecastActual) {
		getPersistence().clearCache(vwDemandForecastActual);
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
	public static List<VwDemandForecastActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwDemandForecastActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwDemandForecastActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwDemandForecastActual> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwDemandForecastActual update(
		VwDemandForecastActual vwDemandForecastActual) {
		return getPersistence().update(vwDemandForecastActual);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwDemandForecastActual update(
		VwDemandForecastActual vwDemandForecastActual,
		ServiceContext serviceContext) {
		return getPersistence().update(vwDemandForecastActual, serviceContext);
	}

	/**
	* Caches the vw demand forecast actual in the entity cache if it is enabled.
	*
	* @param vwDemandForecastActual the vw demand forecast actual
	*/
	public static void cacheResult(
		VwDemandForecastActual vwDemandForecastActual) {
		getPersistence().cacheResult(vwDemandForecastActual);
	}

	/**
	* Caches the vw demand forecast actuals in the entity cache if it is enabled.
	*
	* @param vwDemandForecastActuals the vw demand forecast actuals
	*/
	public static void cacheResult(
		List<VwDemandForecastActual> vwDemandForecastActuals) {
		getPersistence().cacheResult(vwDemandForecastActuals);
	}

	/**
	* Creates a new vw demand forecast actual with the primary key. Does not add the vw demand forecast actual to the database.
	*
	* @param demandForecastActualSid the primary key for the new vw demand forecast actual
	* @return the new vw demand forecast actual
	*/
	public static VwDemandForecastActual create(int demandForecastActualSid) {
		return getPersistence().create(demandForecastActualSid);
	}

	/**
	* Removes the vw demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param demandForecastActualSid the primary key of the vw demand forecast actual
	* @return the vw demand forecast actual that was removed
	* @throws NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
	*/
	public static VwDemandForecastActual remove(int demandForecastActualSid)
		throws com.stpl.app.exception.NoSuchVwDemandForecastActualException {
		return getPersistence().remove(demandForecastActualSid);
	}

	public static VwDemandForecastActual updateImpl(
		VwDemandForecastActual vwDemandForecastActual) {
		return getPersistence().updateImpl(vwDemandForecastActual);
	}

	/**
	* Returns the vw demand forecast actual with the primary key or throws a {@link NoSuchVwDemandForecastActualException} if it could not be found.
	*
	* @param demandForecastActualSid the primary key of the vw demand forecast actual
	* @return the vw demand forecast actual
	* @throws NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
	*/
	public static VwDemandForecastActual findByPrimaryKey(
		int demandForecastActualSid)
		throws com.stpl.app.exception.NoSuchVwDemandForecastActualException {
		return getPersistence().findByPrimaryKey(demandForecastActualSid);
	}

	/**
	* Returns the vw demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param demandForecastActualSid the primary key of the vw demand forecast actual
	* @return the vw demand forecast actual, or <code>null</code> if a vw demand forecast actual with the primary key could not be found
	*/
	public static VwDemandForecastActual fetchByPrimaryKey(
		int demandForecastActualSid) {
		return getPersistence().fetchByPrimaryKey(demandForecastActualSid);
	}

	public static java.util.Map<java.io.Serializable, VwDemandForecastActual> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw demand forecast actuals.
	*
	* @return the vw demand forecast actuals
	*/
	public static List<VwDemandForecastActual> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw demand forecast actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw demand forecast actuals
	* @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
	* @return the range of vw demand forecast actuals
	*/
	public static List<VwDemandForecastActual> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw demand forecast actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw demand forecast actuals
	* @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw demand forecast actuals
	*/
	public static List<VwDemandForecastActual> findAll(int start, int end,
		OrderByComparator<VwDemandForecastActual> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw demand forecast actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw demand forecast actuals
	* @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw demand forecast actuals
	*/
	public static List<VwDemandForecastActual> findAll(int start, int end,
		OrderByComparator<VwDemandForecastActual> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw demand forecast actuals from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw demand forecast actuals.
	*
	* @return the number of vw demand forecast actuals
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwDemandForecastActualPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwDemandForecastActualPersistence, VwDemandForecastActualPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwDemandForecastActualPersistence.class);
}