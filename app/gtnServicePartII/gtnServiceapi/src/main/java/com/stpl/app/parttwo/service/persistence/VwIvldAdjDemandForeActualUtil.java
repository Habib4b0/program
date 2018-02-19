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

import com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw ivld adj demand fore actual service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.VwIvldAdjDemandForeActualPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldAdjDemandForeActualPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.VwIvldAdjDemandForeActualPersistenceImpl
 * @generated
 */
@ProviderType
public class VwIvldAdjDemandForeActualUtil {
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
	public static void clearCache(
		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
		getPersistence().clearCache(vwIvldAdjDemandForeActual);
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
	public static List<VwIvldAdjDemandForeActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwIvldAdjDemandForeActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwIvldAdjDemandForeActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwIvldAdjDemandForeActual> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwIvldAdjDemandForeActual update(
		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
		return getPersistence().update(vwIvldAdjDemandForeActual);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwIvldAdjDemandForeActual update(
		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual,
		ServiceContext serviceContext) {
		return getPersistence().update(vwIvldAdjDemandForeActual, serviceContext);
	}

	/**
	* Caches the vw ivld adj demand fore actual in the entity cache if it is enabled.
	*
	* @param vwIvldAdjDemandForeActual the vw ivld adj demand fore actual
	*/
	public static void cacheResult(
		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
		getPersistence().cacheResult(vwIvldAdjDemandForeActual);
	}

	/**
	* Caches the vw ivld adj demand fore actuals in the entity cache if it is enabled.
	*
	* @param vwIvldAdjDemandForeActuals the vw ivld adj demand fore actuals
	*/
	public static void cacheResult(
		List<VwIvldAdjDemandForeActual> vwIvldAdjDemandForeActuals) {
		getPersistence().cacheResult(vwIvldAdjDemandForeActuals);
	}

	/**
	* Creates a new vw ivld adj demand fore actual with the primary key. Does not add the vw ivld adj demand fore actual to the database.
	*
	* @param ivldAdjustedDemandForecastSid the primary key for the new vw ivld adj demand fore actual
	* @return the new vw ivld adj demand fore actual
	*/
	public static VwIvldAdjDemandForeActual create(
		int ivldAdjustedDemandForecastSid) {
		return getPersistence().create(ivldAdjustedDemandForecastSid);
	}

	/**
	* Removes the vw ivld adj demand fore actual with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
	* @return the vw ivld adj demand fore actual that was removed
	* @throws NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
	*/
	public static VwIvldAdjDemandForeActual remove(
		int ivldAdjustedDemandForecastSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwIvldAdjDemandForeActualException {
		return getPersistence().remove(ivldAdjustedDemandForecastSid);
	}

	public static VwIvldAdjDemandForeActual updateImpl(
		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
		return getPersistence().updateImpl(vwIvldAdjDemandForeActual);
	}

	/**
	* Returns the vw ivld adj demand fore actual with the primary key or throws a {@link NoSuchVwIvldAdjDemandForeActualException} if it could not be found.
	*
	* @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
	* @return the vw ivld adj demand fore actual
	* @throws NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
	*/
	public static VwIvldAdjDemandForeActual findByPrimaryKey(
		int ivldAdjustedDemandForecastSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwIvldAdjDemandForeActualException {
		return getPersistence().findByPrimaryKey(ivldAdjustedDemandForecastSid);
	}

	/**
	* Returns the vw ivld adj demand fore actual with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
	* @return the vw ivld adj demand fore actual, or <code>null</code> if a vw ivld adj demand fore actual with the primary key could not be found
	*/
	public static VwIvldAdjDemandForeActual fetchByPrimaryKey(
		int ivldAdjustedDemandForecastSid) {
		return getPersistence().fetchByPrimaryKey(ivldAdjustedDemandForecastSid);
	}

	public static java.util.Map<java.io.Serializable, VwIvldAdjDemandForeActual> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw ivld adj demand fore actuals.
	*
	* @return the vw ivld adj demand fore actuals
	*/
	public static List<VwIvldAdjDemandForeActual> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw ivld adj demand fore actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw ivld adj demand fore actuals
	* @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
	* @return the range of vw ivld adj demand fore actuals
	*/
	public static List<VwIvldAdjDemandForeActual> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw ivld adj demand fore actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw ivld adj demand fore actuals
	* @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw ivld adj demand fore actuals
	*/
	public static List<VwIvldAdjDemandForeActual> findAll(int start, int end,
		OrderByComparator<VwIvldAdjDemandForeActual> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw ivld adj demand fore actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw ivld adj demand fore actuals
	* @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw ivld adj demand fore actuals
	*/
	public static List<VwIvldAdjDemandForeActual> findAll(int start, int end,
		OrderByComparator<VwIvldAdjDemandForeActual> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw ivld adj demand fore actuals from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw ivld adj demand fore actuals.
	*
	* @return the number of vw ivld adj demand fore actuals
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwIvldAdjDemandForeActualPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwIvldAdjDemandForeActualPersistence, VwIvldAdjDemandForeActualPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwIvldAdjDemandForeActualPersistence.class);
}