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

import com.stpl.app.model.HistHierarchyLevelDefn;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the hist hierarchy level defn service. This utility wraps {@link com.stpl.app.service.persistence.impl.HistHierarchyLevelDefnPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyLevelDefnPersistence
 * @see com.stpl.app.service.persistence.impl.HistHierarchyLevelDefnPersistenceImpl
 * @generated
 */
@ProviderType
public class HistHierarchyLevelDefnUtil {
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
	public static void clearCache(HistHierarchyLevelDefn histHierarchyLevelDefn) {
		getPersistence().clearCache(histHierarchyLevelDefn);
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
	public static List<HistHierarchyLevelDefn> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HistHierarchyLevelDefn> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HistHierarchyLevelDefn> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HistHierarchyLevelDefn> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HistHierarchyLevelDefn update(
		HistHierarchyLevelDefn histHierarchyLevelDefn) {
		return getPersistence().update(histHierarchyLevelDefn);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HistHierarchyLevelDefn update(
		HistHierarchyLevelDefn histHierarchyLevelDefn,
		ServiceContext serviceContext) {
		return getPersistence().update(histHierarchyLevelDefn, serviceContext);
	}

	/**
	* Caches the hist hierarchy level defn in the entity cache if it is enabled.
	*
	* @param histHierarchyLevelDefn the hist hierarchy level defn
	*/
	public static void cacheResult(
		HistHierarchyLevelDefn histHierarchyLevelDefn) {
		getPersistence().cacheResult(histHierarchyLevelDefn);
	}

	/**
	* Caches the hist hierarchy level defns in the entity cache if it is enabled.
	*
	* @param histHierarchyLevelDefns the hist hierarchy level defns
	*/
	public static void cacheResult(
		List<HistHierarchyLevelDefn> histHierarchyLevelDefns) {
		getPersistence().cacheResult(histHierarchyLevelDefns);
	}

	/**
	* Creates a new hist hierarchy level defn with the primary key. Does not add the hist hierarchy level defn to the database.
	*
	* @param histHierarchyLevelDefnPK the primary key for the new hist hierarchy level defn
	* @return the new hist hierarchy level defn
	*/
	public static HistHierarchyLevelDefn create(
		HistHierarchyLevelDefnPK histHierarchyLevelDefnPK) {
		return getPersistence().create(histHierarchyLevelDefnPK);
	}

	/**
	* Removes the hist hierarchy level defn with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
	* @return the hist hierarchy level defn that was removed
	* @throws NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
	*/
	public static HistHierarchyLevelDefn remove(
		HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
		throws com.stpl.app.exception.NoSuchHistHierarchyLevelDefnException {
		return getPersistence().remove(histHierarchyLevelDefnPK);
	}

	public static HistHierarchyLevelDefn updateImpl(
		HistHierarchyLevelDefn histHierarchyLevelDefn) {
		return getPersistence().updateImpl(histHierarchyLevelDefn);
	}

	/**
	* Returns the hist hierarchy level defn with the primary key or throws a {@link NoSuchHistHierarchyLevelDefnException} if it could not be found.
	*
	* @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
	* @return the hist hierarchy level defn
	* @throws NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
	*/
	public static HistHierarchyLevelDefn findByPrimaryKey(
		HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
		throws com.stpl.app.exception.NoSuchHistHierarchyLevelDefnException {
		return getPersistence().findByPrimaryKey(histHierarchyLevelDefnPK);
	}

	/**
	* Returns the hist hierarchy level defn with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
	* @return the hist hierarchy level defn, or <code>null</code> if a hist hierarchy level defn with the primary key could not be found
	*/
	public static HistHierarchyLevelDefn fetchByPrimaryKey(
		HistHierarchyLevelDefnPK histHierarchyLevelDefnPK) {
		return getPersistence().fetchByPrimaryKey(histHierarchyLevelDefnPK);
	}

	public static java.util.Map<java.io.Serializable, HistHierarchyLevelDefn> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the hist hierarchy level defns.
	*
	* @return the hist hierarchy level defns
	*/
	public static List<HistHierarchyLevelDefn> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the hist hierarchy level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy level defns
	* @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
	* @return the range of hist hierarchy level defns
	*/
	public static List<HistHierarchyLevelDefn> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the hist hierarchy level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy level defns
	* @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist hierarchy level defns
	*/
	public static List<HistHierarchyLevelDefn> findAll(int start, int end,
		OrderByComparator<HistHierarchyLevelDefn> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the hist hierarchy level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy level defns
	* @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist hierarchy level defns
	*/
	public static List<HistHierarchyLevelDefn> findAll(int start, int end,
		OrderByComparator<HistHierarchyLevelDefn> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the hist hierarchy level defns from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of hist hierarchy level defns.
	*
	* @return the number of hist hierarchy level defns
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HistHierarchyLevelDefnPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistHierarchyLevelDefnPersistence, HistHierarchyLevelDefnPersistence> _serviceTracker =
		ServiceTrackerFactory.open(HistHierarchyLevelDefnPersistence.class);
}