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

import com.stpl.app.model.PhsActuals;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the phs actuals service. This utility wraps {@link com.stpl.app.service.persistence.impl.PhsActualsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PhsActualsPersistence
 * @see com.stpl.app.service.persistence.impl.PhsActualsPersistenceImpl
 * @generated
 */
@ProviderType
public class PhsActualsUtil {
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
	public static void clearCache(PhsActuals phsActuals) {
		getPersistence().clearCache(phsActuals);
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
	public static List<PhsActuals> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PhsActuals> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PhsActuals> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PhsActuals> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PhsActuals update(PhsActuals phsActuals) {
		return getPersistence().update(phsActuals);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PhsActuals update(PhsActuals phsActuals,
		ServiceContext serviceContext) {
		return getPersistence().update(phsActuals, serviceContext);
	}

	/**
	* Caches the phs actuals in the entity cache if it is enabled.
	*
	* @param phsActuals the phs actuals
	*/
	public static void cacheResult(PhsActuals phsActuals) {
		getPersistence().cacheResult(phsActuals);
	}

	/**
	* Caches the phs actualses in the entity cache if it is enabled.
	*
	* @param phsActualses the phs actualses
	*/
	public static void cacheResult(List<PhsActuals> phsActualses) {
		getPersistence().cacheResult(phsActualses);
	}

	/**
	* Creates a new phs actuals with the primary key. Does not add the phs actuals to the database.
	*
	* @param phsActualsPK the primary key for the new phs actuals
	* @return the new phs actuals
	*/
	public static PhsActuals create(PhsActualsPK phsActualsPK) {
		return getPersistence().create(phsActualsPK);
	}

	/**
	* Removes the phs actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param phsActualsPK the primary key of the phs actuals
	* @return the phs actuals that was removed
	* @throws NoSuchPhsActualsException if a phs actuals with the primary key could not be found
	*/
	public static PhsActuals remove(PhsActualsPK phsActualsPK)
		throws com.stpl.app.exception.NoSuchPhsActualsException {
		return getPersistence().remove(phsActualsPK);
	}

	public static PhsActuals updateImpl(PhsActuals phsActuals) {
		return getPersistence().updateImpl(phsActuals);
	}

	/**
	* Returns the phs actuals with the primary key or throws a {@link NoSuchPhsActualsException} if it could not be found.
	*
	* @param phsActualsPK the primary key of the phs actuals
	* @return the phs actuals
	* @throws NoSuchPhsActualsException if a phs actuals with the primary key could not be found
	*/
	public static PhsActuals findByPrimaryKey(PhsActualsPK phsActualsPK)
		throws com.stpl.app.exception.NoSuchPhsActualsException {
		return getPersistence().findByPrimaryKey(phsActualsPK);
	}

	/**
	* Returns the phs actuals with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param phsActualsPK the primary key of the phs actuals
	* @return the phs actuals, or <code>null</code> if a phs actuals with the primary key could not be found
	*/
	public static PhsActuals fetchByPrimaryKey(PhsActualsPK phsActualsPK) {
		return getPersistence().fetchByPrimaryKey(phsActualsPK);
	}

	public static java.util.Map<java.io.Serializable, PhsActuals> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the phs actualses.
	*
	* @return the phs actualses
	*/
	public static List<PhsActuals> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the phs actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phs actualses
	* @param end the upper bound of the range of phs actualses (not inclusive)
	* @return the range of phs actualses
	*/
	public static List<PhsActuals> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the phs actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phs actualses
	* @param end the upper bound of the range of phs actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of phs actualses
	*/
	public static List<PhsActuals> findAll(int start, int end,
		OrderByComparator<PhsActuals> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the phs actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phs actualses
	* @param end the upper bound of the range of phs actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of phs actualses
	*/
	public static List<PhsActuals> findAll(int start, int end,
		OrderByComparator<PhsActuals> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the phs actualses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of phs actualses.
	*
	* @return the number of phs actualses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PhsActualsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PhsActualsPersistence, PhsActualsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(PhsActualsPersistence.class);
}