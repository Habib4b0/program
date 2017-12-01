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

import com.stpl.app.model.NmActualPpa;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the nm actual ppa service. This utility wraps {@link com.stpl.app.service.persistence.impl.NmActualPpaPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmActualPpaPersistence
 * @see com.stpl.app.service.persistence.impl.NmActualPpaPersistenceImpl
 * @generated
 */
@ProviderType
public class NmActualPpaUtil {
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
	public static void clearCache(NmActualPpa nmActualPpa) {
		getPersistence().clearCache(nmActualPpa);
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
	public static List<NmActualPpa> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NmActualPpa> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NmActualPpa> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NmActualPpa> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NmActualPpa update(NmActualPpa nmActualPpa) {
		return getPersistence().update(nmActualPpa);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NmActualPpa update(NmActualPpa nmActualPpa,
		ServiceContext serviceContext) {
		return getPersistence().update(nmActualPpa, serviceContext);
	}

	/**
	* Caches the nm actual ppa in the entity cache if it is enabled.
	*
	* @param nmActualPpa the nm actual ppa
	*/
	public static void cacheResult(NmActualPpa nmActualPpa) {
		getPersistence().cacheResult(nmActualPpa);
	}

	/**
	* Caches the nm actual ppas in the entity cache if it is enabled.
	*
	* @param nmActualPpas the nm actual ppas
	*/
	public static void cacheResult(List<NmActualPpa> nmActualPpas) {
		getPersistence().cacheResult(nmActualPpas);
	}

	/**
	* Creates a new nm actual ppa with the primary key. Does not add the nm actual ppa to the database.
	*
	* @param nmActualPpaPK the primary key for the new nm actual ppa
	* @return the new nm actual ppa
	*/
	public static NmActualPpa create(NmActualPpaPK nmActualPpaPK) {
		return getPersistence().create(nmActualPpaPK);
	}

	/**
	* Removes the nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmActualPpaPK the primary key of the nm actual ppa
	* @return the nm actual ppa that was removed
	* @throws NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
	*/
	public static NmActualPpa remove(NmActualPpaPK nmActualPpaPK)
		throws com.stpl.app.exception.NoSuchNmActualPpaException {
		return getPersistence().remove(nmActualPpaPK);
	}

	public static NmActualPpa updateImpl(NmActualPpa nmActualPpa) {
		return getPersistence().updateImpl(nmActualPpa);
	}

	/**
	* Returns the nm actual ppa with the primary key or throws a {@link NoSuchNmActualPpaException} if it could not be found.
	*
	* @param nmActualPpaPK the primary key of the nm actual ppa
	* @return the nm actual ppa
	* @throws NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
	*/
	public static NmActualPpa findByPrimaryKey(NmActualPpaPK nmActualPpaPK)
		throws com.stpl.app.exception.NoSuchNmActualPpaException {
		return getPersistence().findByPrimaryKey(nmActualPpaPK);
	}

	/**
	* Returns the nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nmActualPpaPK the primary key of the nm actual ppa
	* @return the nm actual ppa, or <code>null</code> if a nm actual ppa with the primary key could not be found
	*/
	public static NmActualPpa fetchByPrimaryKey(NmActualPpaPK nmActualPpaPK) {
		return getPersistence().fetchByPrimaryKey(nmActualPpaPK);
	}

	public static java.util.Map<java.io.Serializable, NmActualPpa> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the nm actual ppas.
	*
	* @return the nm actual ppas
	*/
	public static List<NmActualPpa> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the nm actual ppas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm actual ppas
	* @param end the upper bound of the range of nm actual ppas (not inclusive)
	* @return the range of nm actual ppas
	*/
	public static List<NmActualPpa> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the nm actual ppas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm actual ppas
	* @param end the upper bound of the range of nm actual ppas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm actual ppas
	*/
	public static List<NmActualPpa> findAll(int start, int end,
		OrderByComparator<NmActualPpa> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the nm actual ppas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm actual ppas
	* @param end the upper bound of the range of nm actual ppas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm actual ppas
	*/
	public static List<NmActualPpa> findAll(int start, int end,
		OrderByComparator<NmActualPpa> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the nm actual ppas from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of nm actual ppas.
	*
	* @return the number of nm actual ppas
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NmActualPpaPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NmActualPpaPersistence, NmActualPpaPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NmActualPpaPersistence.class);
}