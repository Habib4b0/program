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

import com.stpl.app.model.VwUserTables;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw user tables service. This utility wraps {@link com.stpl.app.service.persistence.impl.VwUserTablesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwUserTablesPersistence
 * @see com.stpl.app.service.persistence.impl.VwUserTablesPersistenceImpl
 * @generated
 */
@ProviderType
public class VwUserTablesUtil {
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
	public static void clearCache(VwUserTables vwUserTables) {
		getPersistence().clearCache(vwUserTables);
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
	public static List<VwUserTables> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwUserTables> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwUserTables> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwUserTables> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwUserTables update(VwUserTables vwUserTables) {
		return getPersistence().update(vwUserTables);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwUserTables update(VwUserTables vwUserTables,
		ServiceContext serviceContext) {
		return getPersistence().update(vwUserTables, serviceContext);
	}

	/**
	* Caches the vw user tables in the entity cache if it is enabled.
	*
	* @param vwUserTables the vw user tables
	*/
	public static void cacheResult(VwUserTables vwUserTables) {
		getPersistence().cacheResult(vwUserTables);
	}

	/**
	* Caches the vw user tableses in the entity cache if it is enabled.
	*
	* @param vwUserTableses the vw user tableses
	*/
	public static void cacheResult(List<VwUserTables> vwUserTableses) {
		getPersistence().cacheResult(vwUserTableses);
	}

	/**
	* Creates a new vw user tables with the primary key. Does not add the vw user tables to the database.
	*
	* @param uniqueId the primary key for the new vw user tables
	* @return the new vw user tables
	*/
	public static VwUserTables create(int uniqueId) {
		return getPersistence().create(uniqueId);
	}

	/**
	* Removes the vw user tables with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param uniqueId the primary key of the vw user tables
	* @return the vw user tables that was removed
	* @throws NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
	*/
	public static VwUserTables remove(int uniqueId)
		throws com.stpl.app.exception.NoSuchVwUserTablesException {
		return getPersistence().remove(uniqueId);
	}

	public static VwUserTables updateImpl(VwUserTables vwUserTables) {
		return getPersistence().updateImpl(vwUserTables);
	}

	/**
	* Returns the vw user tables with the primary key or throws a {@link NoSuchVwUserTablesException} if it could not be found.
	*
	* @param uniqueId the primary key of the vw user tables
	* @return the vw user tables
	* @throws NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
	*/
	public static VwUserTables findByPrimaryKey(int uniqueId)
		throws com.stpl.app.exception.NoSuchVwUserTablesException {
		return getPersistence().findByPrimaryKey(uniqueId);
	}

	/**
	* Returns the vw user tables with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param uniqueId the primary key of the vw user tables
	* @return the vw user tables, or <code>null</code> if a vw user tables with the primary key could not be found
	*/
	public static VwUserTables fetchByPrimaryKey(int uniqueId) {
		return getPersistence().fetchByPrimaryKey(uniqueId);
	}

	public static java.util.Map<java.io.Serializable, VwUserTables> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw user tableses.
	*
	* @return the vw user tableses
	*/
	public static List<VwUserTables> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw user tableses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw user tableses
	* @param end the upper bound of the range of vw user tableses (not inclusive)
	* @return the range of vw user tableses
	*/
	public static List<VwUserTables> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw user tableses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw user tableses
	* @param end the upper bound of the range of vw user tableses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw user tableses
	*/
	public static List<VwUserTables> findAll(int start, int end,
		OrderByComparator<VwUserTables> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw user tableses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw user tableses
	* @param end the upper bound of the range of vw user tableses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw user tableses
	*/
	public static List<VwUserTables> findAll(int start, int end,
		OrderByComparator<VwUserTables> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw user tableses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw user tableses.
	*
	* @return the number of vw user tableses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwUserTablesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwUserTablesPersistence, VwUserTablesPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwUserTablesPersistence.class);
}