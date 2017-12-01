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

import com.stpl.app.model.HistRelationshipLevelDefn;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the hist relationship level defn service. This utility wraps {@link com.stpl.app.service.persistence.impl.HistRelationshipLevelDefnPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistRelationshipLevelDefnPersistence
 * @see com.stpl.app.service.persistence.impl.HistRelationshipLevelDefnPersistenceImpl
 * @generated
 */
@ProviderType
public class HistRelationshipLevelDefnUtil {
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
		HistRelationshipLevelDefn histRelationshipLevelDefn) {
		getPersistence().clearCache(histRelationshipLevelDefn);
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
	public static List<HistRelationshipLevelDefn> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HistRelationshipLevelDefn> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HistRelationshipLevelDefn> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HistRelationshipLevelDefn> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HistRelationshipLevelDefn update(
		HistRelationshipLevelDefn histRelationshipLevelDefn) {
		return getPersistence().update(histRelationshipLevelDefn);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HistRelationshipLevelDefn update(
		HistRelationshipLevelDefn histRelationshipLevelDefn,
		ServiceContext serviceContext) {
		return getPersistence().update(histRelationshipLevelDefn, serviceContext);
	}

	/**
	* Caches the hist relationship level defn in the entity cache if it is enabled.
	*
	* @param histRelationshipLevelDefn the hist relationship level defn
	*/
	public static void cacheResult(
		HistRelationshipLevelDefn histRelationshipLevelDefn) {
		getPersistence().cacheResult(histRelationshipLevelDefn);
	}

	/**
	* Caches the hist relationship level defns in the entity cache if it is enabled.
	*
	* @param histRelationshipLevelDefns the hist relationship level defns
	*/
	public static void cacheResult(
		List<HistRelationshipLevelDefn> histRelationshipLevelDefns) {
		getPersistence().cacheResult(histRelationshipLevelDefns);
	}

	/**
	* Creates a new hist relationship level defn with the primary key. Does not add the hist relationship level defn to the database.
	*
	* @param histRelationshipLevelDefnPK the primary key for the new hist relationship level defn
	* @return the new hist relationship level defn
	*/
	public static HistRelationshipLevelDefn create(
		HistRelationshipLevelDefnPK histRelationshipLevelDefnPK) {
		return getPersistence().create(histRelationshipLevelDefnPK);
	}

	/**
	* Removes the hist relationship level defn with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
	* @return the hist relationship level defn that was removed
	* @throws NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
	*/
	public static HistRelationshipLevelDefn remove(
		HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
		throws com.stpl.app.exception.NoSuchHistRelationshipLevelDefnException {
		return getPersistence().remove(histRelationshipLevelDefnPK);
	}

	public static HistRelationshipLevelDefn updateImpl(
		HistRelationshipLevelDefn histRelationshipLevelDefn) {
		return getPersistence().updateImpl(histRelationshipLevelDefn);
	}

	/**
	* Returns the hist relationship level defn with the primary key or throws a {@link NoSuchHistRelationshipLevelDefnException} if it could not be found.
	*
	* @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
	* @return the hist relationship level defn
	* @throws NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
	*/
	public static HistRelationshipLevelDefn findByPrimaryKey(
		HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
		throws com.stpl.app.exception.NoSuchHistRelationshipLevelDefnException {
		return getPersistence().findByPrimaryKey(histRelationshipLevelDefnPK);
	}

	/**
	* Returns the hist relationship level defn with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
	* @return the hist relationship level defn, or <code>null</code> if a hist relationship level defn with the primary key could not be found
	*/
	public static HistRelationshipLevelDefn fetchByPrimaryKey(
		HistRelationshipLevelDefnPK histRelationshipLevelDefnPK) {
		return getPersistence().fetchByPrimaryKey(histRelationshipLevelDefnPK);
	}

	public static java.util.Map<java.io.Serializable, HistRelationshipLevelDefn> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the hist relationship level defns.
	*
	* @return the hist relationship level defns
	*/
	public static List<HistRelationshipLevelDefn> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the hist relationship level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist relationship level defns
	* @param end the upper bound of the range of hist relationship level defns (not inclusive)
	* @return the range of hist relationship level defns
	*/
	public static List<HistRelationshipLevelDefn> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the hist relationship level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist relationship level defns
	* @param end the upper bound of the range of hist relationship level defns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist relationship level defns
	*/
	public static List<HistRelationshipLevelDefn> findAll(int start, int end,
		OrderByComparator<HistRelationshipLevelDefn> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the hist relationship level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist relationship level defns
	* @param end the upper bound of the range of hist relationship level defns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist relationship level defns
	*/
	public static List<HistRelationshipLevelDefn> findAll(int start, int end,
		OrderByComparator<HistRelationshipLevelDefn> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the hist relationship level defns from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of hist relationship level defns.
	*
	* @return the number of hist relationship level defns
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HistRelationshipLevelDefnPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistRelationshipLevelDefnPersistence, HistRelationshipLevelDefnPersistence> _serviceTracker =
		ServiceTrackerFactory.open(HistRelationshipLevelDefnPersistence.class);
}