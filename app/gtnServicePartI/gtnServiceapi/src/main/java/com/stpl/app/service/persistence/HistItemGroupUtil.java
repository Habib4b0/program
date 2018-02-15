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

import com.stpl.app.model.HistItemGroup;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the hist item group service. This utility wraps {@link com.stpl.app.service.persistence.impl.HistItemGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistItemGroupPersistence
 * @see com.stpl.app.service.persistence.impl.HistItemGroupPersistenceImpl
 * @generated
 */
@ProviderType
public class HistItemGroupUtil {
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
	public static void clearCache(HistItemGroup histItemGroup) {
		getPersistence().clearCache(histItemGroup);
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
	public static List<HistItemGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HistItemGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HistItemGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HistItemGroup> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HistItemGroup update(HistItemGroup histItemGroup) {
		return getPersistence().update(histItemGroup);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HistItemGroup update(HistItemGroup histItemGroup,
		ServiceContext serviceContext) {
		return getPersistence().update(histItemGroup, serviceContext);
	}

	/**
	* Caches the hist item group in the entity cache if it is enabled.
	*
	* @param histItemGroup the hist item group
	*/
	public static void cacheResult(HistItemGroup histItemGroup) {
		getPersistence().cacheResult(histItemGroup);
	}

	/**
	* Caches the hist item groups in the entity cache if it is enabled.
	*
	* @param histItemGroups the hist item groups
	*/
	public static void cacheResult(List<HistItemGroup> histItemGroups) {
		getPersistence().cacheResult(histItemGroups);
	}

	/**
	* Creates a new hist item group with the primary key. Does not add the hist item group to the database.
	*
	* @param histItemGroupPK the primary key for the new hist item group
	* @return the new hist item group
	*/
	public static HistItemGroup create(HistItemGroupPK histItemGroupPK) {
		return getPersistence().create(histItemGroupPK);
	}

	/**
	* Removes the hist item group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histItemGroupPK the primary key of the hist item group
	* @return the hist item group that was removed
	* @throws NoSuchHistItemGroupException if a hist item group with the primary key could not be found
	*/
	public static HistItemGroup remove(HistItemGroupPK histItemGroupPK)
		throws com.stpl.app.exception.NoSuchHistItemGroupException {
		return getPersistence().remove(histItemGroupPK);
	}

	public static HistItemGroup updateImpl(HistItemGroup histItemGroup) {
		return getPersistence().updateImpl(histItemGroup);
	}

	/**
	* Returns the hist item group with the primary key or throws a {@link NoSuchHistItemGroupException} if it could not be found.
	*
	* @param histItemGroupPK the primary key of the hist item group
	* @return the hist item group
	* @throws NoSuchHistItemGroupException if a hist item group with the primary key could not be found
	*/
	public static HistItemGroup findByPrimaryKey(
		HistItemGroupPK histItemGroupPK)
		throws com.stpl.app.exception.NoSuchHistItemGroupException {
		return getPersistence().findByPrimaryKey(histItemGroupPK);
	}

	/**
	* Returns the hist item group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param histItemGroupPK the primary key of the hist item group
	* @return the hist item group, or <code>null</code> if a hist item group with the primary key could not be found
	*/
	public static HistItemGroup fetchByPrimaryKey(
		HistItemGroupPK histItemGroupPK) {
		return getPersistence().fetchByPrimaryKey(histItemGroupPK);
	}

	public static java.util.Map<java.io.Serializable, HistItemGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the hist item groups.
	*
	* @return the hist item groups
	*/
	public static List<HistItemGroup> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the hist item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist item groups
	* @param end the upper bound of the range of hist item groups (not inclusive)
	* @return the range of hist item groups
	*/
	public static List<HistItemGroup> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the hist item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist item groups
	* @param end the upper bound of the range of hist item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist item groups
	*/
	public static List<HistItemGroup> findAll(int start, int end,
		OrderByComparator<HistItemGroup> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the hist item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist item groups
	* @param end the upper bound of the range of hist item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist item groups
	*/
	public static List<HistItemGroup> findAll(int start, int end,
		OrderByComparator<HistItemGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the hist item groups from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of hist item groups.
	*
	* @return the number of hist item groups
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HistItemGroupPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistItemGroupPersistence, HistItemGroupPersistence> _serviceTracker =
		ServiceTrackerFactory.open(HistItemGroupPersistence.class);
}