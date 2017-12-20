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

import com.stpl.app.model.StNmActualPpa;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st nm actual ppa service. This utility wraps {@link com.stpl.app.service.persistence.impl.StNmActualPpaPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmActualPpaPersistence
 * @see com.stpl.app.service.persistence.impl.StNmActualPpaPersistenceImpl
 * @generated
 */
@ProviderType
public class StNmActualPpaUtil {
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
	public static void clearCache(StNmActualPpa stNmActualPpa) {
		getPersistence().clearCache(stNmActualPpa);
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
	public static List<StNmActualPpa> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StNmActualPpa> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StNmActualPpa> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StNmActualPpa> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StNmActualPpa update(StNmActualPpa stNmActualPpa) {
		return getPersistence().update(stNmActualPpa);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StNmActualPpa update(StNmActualPpa stNmActualPpa,
		ServiceContext serviceContext) {
		return getPersistence().update(stNmActualPpa, serviceContext);
	}

	/**
	* Caches the st nm actual ppa in the entity cache if it is enabled.
	*
	* @param stNmActualPpa the st nm actual ppa
	*/
	public static void cacheResult(StNmActualPpa stNmActualPpa) {
		getPersistence().cacheResult(stNmActualPpa);
	}

	/**
	* Caches the st nm actual ppas in the entity cache if it is enabled.
	*
	* @param stNmActualPpas the st nm actual ppas
	*/
	public static void cacheResult(List<StNmActualPpa> stNmActualPpas) {
		getPersistence().cacheResult(stNmActualPpas);
	}

	/**
	* Creates a new st nm actual ppa with the primary key. Does not add the st nm actual ppa to the database.
	*
	* @param stNmActualPpaPK the primary key for the new st nm actual ppa
	* @return the new st nm actual ppa
	*/
	public static StNmActualPpa create(StNmActualPpaPK stNmActualPpaPK) {
		return getPersistence().create(stNmActualPpaPK);
	}

	/**
	* Removes the st nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmActualPpaPK the primary key of the st nm actual ppa
	* @return the st nm actual ppa that was removed
	* @throws NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
	*/
	public static StNmActualPpa remove(StNmActualPpaPK stNmActualPpaPK)
		throws com.stpl.app.exception.NoSuchStNmActualPpaException {
		return getPersistence().remove(stNmActualPpaPK);
	}

	public static StNmActualPpa updateImpl(StNmActualPpa stNmActualPpa) {
		return getPersistence().updateImpl(stNmActualPpa);
	}

	/**
	* Returns the st nm actual ppa with the primary key or throws a {@link NoSuchStNmActualPpaException} if it could not be found.
	*
	* @param stNmActualPpaPK the primary key of the st nm actual ppa
	* @return the st nm actual ppa
	* @throws NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
	*/
	public static StNmActualPpa findByPrimaryKey(
		StNmActualPpaPK stNmActualPpaPK)
		throws com.stpl.app.exception.NoSuchStNmActualPpaException {
		return getPersistence().findByPrimaryKey(stNmActualPpaPK);
	}

	/**
	* Returns the st nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmActualPpaPK the primary key of the st nm actual ppa
	* @return the st nm actual ppa, or <code>null</code> if a st nm actual ppa with the primary key could not be found
	*/
	public static StNmActualPpa fetchByPrimaryKey(
		StNmActualPpaPK stNmActualPpaPK) {
		return getPersistence().fetchByPrimaryKey(stNmActualPpaPK);
	}

	public static java.util.Map<java.io.Serializable, StNmActualPpa> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st nm actual ppas.
	*
	* @return the st nm actual ppas
	*/
	public static List<StNmActualPpa> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st nm actual ppas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual ppas
	* @param end the upper bound of the range of st nm actual ppas (not inclusive)
	* @return the range of st nm actual ppas
	*/
	public static List<StNmActualPpa> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st nm actual ppas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual ppas
	* @param end the upper bound of the range of st nm actual ppas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm actual ppas
	*/
	public static List<StNmActualPpa> findAll(int start, int end,
		OrderByComparator<StNmActualPpa> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st nm actual ppas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual ppas
	* @param end the upper bound of the range of st nm actual ppas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm actual ppas
	*/
	public static List<StNmActualPpa> findAll(int start, int end,
		OrderByComparator<StNmActualPpa> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st nm actual ppas from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st nm actual ppas.
	*
	* @return the number of st nm actual ppas
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StNmActualPpaPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StNmActualPpaPersistence, StNmActualPpaPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StNmActualPpaPersistence.class);
}