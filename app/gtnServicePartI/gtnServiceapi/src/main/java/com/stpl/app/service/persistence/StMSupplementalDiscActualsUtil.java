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

import com.stpl.app.model.StMSupplementalDiscActuals;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st m supplemental disc actuals service. This utility wraps {@link com.stpl.app.service.persistence.impl.StMSupplementalDiscActualsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMSupplementalDiscActualsPersistence
 * @see com.stpl.app.service.persistence.impl.StMSupplementalDiscActualsPersistenceImpl
 * @generated
 */
@ProviderType
public class StMSupplementalDiscActualsUtil {
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
		StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		getPersistence().clearCache(stMSupplementalDiscActuals);
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
	public static List<StMSupplementalDiscActuals> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StMSupplementalDiscActuals> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StMSupplementalDiscActuals> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StMSupplementalDiscActuals> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StMSupplementalDiscActuals update(
		StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		return getPersistence().update(stMSupplementalDiscActuals);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StMSupplementalDiscActuals update(
		StMSupplementalDiscActuals stMSupplementalDiscActuals,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(stMSupplementalDiscActuals, serviceContext);
	}

	/**
	* Caches the st m supplemental disc actuals in the entity cache if it is enabled.
	*
	* @param stMSupplementalDiscActuals the st m supplemental disc actuals
	*/
	public static void cacheResult(
		StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		getPersistence().cacheResult(stMSupplementalDiscActuals);
	}

	/**
	* Caches the st m supplemental disc actualses in the entity cache if it is enabled.
	*
	* @param stMSupplementalDiscActualses the st m supplemental disc actualses
	*/
	public static void cacheResult(
		List<StMSupplementalDiscActuals> stMSupplementalDiscActualses) {
		getPersistence().cacheResult(stMSupplementalDiscActualses);
	}

	/**
	* Creates a new st m supplemental disc actuals with the primary key. Does not add the st m supplemental disc actuals to the database.
	*
	* @param stMSupplementalDiscActualsPK the primary key for the new st m supplemental disc actuals
	* @return the new st m supplemental disc actuals
	*/
	public static StMSupplementalDiscActuals create(
		StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK) {
		return getPersistence().create(stMSupplementalDiscActualsPK);
	}

	/**
	* Removes the st m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
	* @return the st m supplemental disc actuals that was removed
	* @throws NoSuchStMSupplementalDiscActualsException if a st m supplemental disc actuals with the primary key could not be found
	*/
	public static StMSupplementalDiscActuals remove(
		StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK)
		throws com.stpl.app.exception.NoSuchStMSupplementalDiscActualsException {
		return getPersistence().remove(stMSupplementalDiscActualsPK);
	}

	public static StMSupplementalDiscActuals updateImpl(
		StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		return getPersistence().updateImpl(stMSupplementalDiscActuals);
	}

	/**
	* Returns the st m supplemental disc actuals with the primary key or throws a {@link NoSuchStMSupplementalDiscActualsException} if it could not be found.
	*
	* @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
	* @return the st m supplemental disc actuals
	* @throws NoSuchStMSupplementalDiscActualsException if a st m supplemental disc actuals with the primary key could not be found
	*/
	public static StMSupplementalDiscActuals findByPrimaryKey(
		StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK)
		throws com.stpl.app.exception.NoSuchStMSupplementalDiscActualsException {
		return getPersistence().findByPrimaryKey(stMSupplementalDiscActualsPK);
	}

	/**
	* Returns the st m supplemental disc actuals with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
	* @return the st m supplemental disc actuals, or <code>null</code> if a st m supplemental disc actuals with the primary key could not be found
	*/
	public static StMSupplementalDiscActuals fetchByPrimaryKey(
		StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK) {
		return getPersistence().fetchByPrimaryKey(stMSupplementalDiscActualsPK);
	}

	public static java.util.Map<java.io.Serializable, StMSupplementalDiscActuals> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st m supplemental disc actualses.
	*
	* @return the st m supplemental disc actualses
	*/
	public static List<StMSupplementalDiscActuals> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st m supplemental disc actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m supplemental disc actualses
	* @param end the upper bound of the range of st m supplemental disc actualses (not inclusive)
	* @return the range of st m supplemental disc actualses
	*/
	public static List<StMSupplementalDiscActuals> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st m supplemental disc actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m supplemental disc actualses
	* @param end the upper bound of the range of st m supplemental disc actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st m supplemental disc actualses
	*/
	public static List<StMSupplementalDiscActuals> findAll(int start, int end,
		OrderByComparator<StMSupplementalDiscActuals> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st m supplemental disc actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m supplemental disc actualses
	* @param end the upper bound of the range of st m supplemental disc actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st m supplemental disc actualses
	*/
	public static List<StMSupplementalDiscActuals> findAll(int start, int end,
		OrderByComparator<StMSupplementalDiscActuals> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st m supplemental disc actualses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st m supplemental disc actualses.
	*
	* @return the number of st m supplemental disc actualses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StMSupplementalDiscActualsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StMSupplementalDiscActualsPersistence, StMSupplementalDiscActualsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StMSupplementalDiscActualsPersistence.class);
}