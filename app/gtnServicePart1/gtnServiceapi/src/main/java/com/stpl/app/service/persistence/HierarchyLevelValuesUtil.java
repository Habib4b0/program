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

import com.stpl.app.model.HierarchyLevelValues;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the hierarchy level values service. This utility wraps {@link com.stpl.app.service.persistence.impl.HierarchyLevelValuesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyLevelValuesPersistence
 * @see com.stpl.app.service.persistence.impl.HierarchyLevelValuesPersistenceImpl
 * @generated
 */
@ProviderType
public class HierarchyLevelValuesUtil {
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
	public static void clearCache(HierarchyLevelValues hierarchyLevelValues) {
		getPersistence().clearCache(hierarchyLevelValues);
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
	public static List<HierarchyLevelValues> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HierarchyLevelValues> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HierarchyLevelValues> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HierarchyLevelValues> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HierarchyLevelValues update(
		HierarchyLevelValues hierarchyLevelValues) {
		return getPersistence().update(hierarchyLevelValues);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HierarchyLevelValues update(
		HierarchyLevelValues hierarchyLevelValues, ServiceContext serviceContext) {
		return getPersistence().update(hierarchyLevelValues, serviceContext);
	}

	/**
	* Caches the hierarchy level values in the entity cache if it is enabled.
	*
	* @param hierarchyLevelValues the hierarchy level values
	*/
	public static void cacheResult(HierarchyLevelValues hierarchyLevelValues) {
		getPersistence().cacheResult(hierarchyLevelValues);
	}

	/**
	* Caches the hierarchy level valueses in the entity cache if it is enabled.
	*
	* @param hierarchyLevelValueses the hierarchy level valueses
	*/
	public static void cacheResult(
		List<HierarchyLevelValues> hierarchyLevelValueses) {
		getPersistence().cacheResult(hierarchyLevelValueses);
	}

	/**
	* Creates a new hierarchy level values with the primary key. Does not add the hierarchy level values to the database.
	*
	* @param hierarchyLevelValuesSid the primary key for the new hierarchy level values
	* @return the new hierarchy level values
	*/
	public static HierarchyLevelValues create(int hierarchyLevelValuesSid) {
		return getPersistence().create(hierarchyLevelValuesSid);
	}

	/**
	* Removes the hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	* @return the hierarchy level values that was removed
	* @throws NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
	*/
	public static HierarchyLevelValues remove(int hierarchyLevelValuesSid)
		throws com.stpl.app.exception.NoSuchHierarchyLevelValuesException {
		return getPersistence().remove(hierarchyLevelValuesSid);
	}

	public static HierarchyLevelValues updateImpl(
		HierarchyLevelValues hierarchyLevelValues) {
		return getPersistence().updateImpl(hierarchyLevelValues);
	}

	/**
	* Returns the hierarchy level values with the primary key or throws a {@link NoSuchHierarchyLevelValuesException} if it could not be found.
	*
	* @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	* @return the hierarchy level values
	* @throws NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
	*/
	public static HierarchyLevelValues findByPrimaryKey(
		int hierarchyLevelValuesSid)
		throws com.stpl.app.exception.NoSuchHierarchyLevelValuesException {
		return getPersistence().findByPrimaryKey(hierarchyLevelValuesSid);
	}

	/**
	* Returns the hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	* @return the hierarchy level values, or <code>null</code> if a hierarchy level values with the primary key could not be found
	*/
	public static HierarchyLevelValues fetchByPrimaryKey(
		int hierarchyLevelValuesSid) {
		return getPersistence().fetchByPrimaryKey(hierarchyLevelValuesSid);
	}

	public static java.util.Map<java.io.Serializable, HierarchyLevelValues> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the hierarchy level valueses.
	*
	* @return the hierarchy level valueses
	*/
	public static List<HierarchyLevelValues> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the hierarchy level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level valueses
	* @param end the upper bound of the range of hierarchy level valueses (not inclusive)
	* @return the range of hierarchy level valueses
	*/
	public static List<HierarchyLevelValues> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the hierarchy level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level valueses
	* @param end the upper bound of the range of hierarchy level valueses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hierarchy level valueses
	*/
	public static List<HierarchyLevelValues> findAll(int start, int end,
		OrderByComparator<HierarchyLevelValues> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the hierarchy level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level valueses
	* @param end the upper bound of the range of hierarchy level valueses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hierarchy level valueses
	*/
	public static List<HierarchyLevelValues> findAll(int start, int end,
		OrderByComparator<HierarchyLevelValues> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the hierarchy level valueses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of hierarchy level valueses.
	*
	* @return the number of hierarchy level valueses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HierarchyLevelValuesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HierarchyLevelValuesPersistence, HierarchyLevelValuesPersistence> _serviceTracker =
		ServiceTrackerFactory.open(HierarchyLevelValuesPersistence.class);
}