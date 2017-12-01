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

import com.stpl.app.model.HistCompanyGroup;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the hist company group service. This utility wraps {@link com.stpl.app.service.persistence.impl.HistCompanyGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistCompanyGroupPersistence
 * @see com.stpl.app.service.persistence.impl.HistCompanyGroupPersistenceImpl
 * @generated
 */
@ProviderType
public class HistCompanyGroupUtil {
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
	public static void clearCache(HistCompanyGroup histCompanyGroup) {
		getPersistence().clearCache(histCompanyGroup);
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
	public static List<HistCompanyGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HistCompanyGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HistCompanyGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HistCompanyGroup> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HistCompanyGroup update(HistCompanyGroup histCompanyGroup) {
		return getPersistence().update(histCompanyGroup);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HistCompanyGroup update(HistCompanyGroup histCompanyGroup,
		ServiceContext serviceContext) {
		return getPersistence().update(histCompanyGroup, serviceContext);
	}

	/**
	* Caches the hist company group in the entity cache if it is enabled.
	*
	* @param histCompanyGroup the hist company group
	*/
	public static void cacheResult(HistCompanyGroup histCompanyGroup) {
		getPersistence().cacheResult(histCompanyGroup);
	}

	/**
	* Caches the hist company groups in the entity cache if it is enabled.
	*
	* @param histCompanyGroups the hist company groups
	*/
	public static void cacheResult(List<HistCompanyGroup> histCompanyGroups) {
		getPersistence().cacheResult(histCompanyGroups);
	}

	/**
	* Creates a new hist company group with the primary key. Does not add the hist company group to the database.
	*
	* @param histCompanyGroupPK the primary key for the new hist company group
	* @return the new hist company group
	*/
	public static HistCompanyGroup create(HistCompanyGroupPK histCompanyGroupPK) {
		return getPersistence().create(histCompanyGroupPK);
	}

	/**
	* Removes the hist company group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histCompanyGroupPK the primary key of the hist company group
	* @return the hist company group that was removed
	* @throws NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
	*/
	public static HistCompanyGroup remove(HistCompanyGroupPK histCompanyGroupPK)
		throws com.stpl.app.exception.NoSuchHistCompanyGroupException {
		return getPersistence().remove(histCompanyGroupPK);
	}

	public static HistCompanyGroup updateImpl(HistCompanyGroup histCompanyGroup) {
		return getPersistence().updateImpl(histCompanyGroup);
	}

	/**
	* Returns the hist company group with the primary key or throws a {@link NoSuchHistCompanyGroupException} if it could not be found.
	*
	* @param histCompanyGroupPK the primary key of the hist company group
	* @return the hist company group
	* @throws NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
	*/
	public static HistCompanyGroup findByPrimaryKey(
		HistCompanyGroupPK histCompanyGroupPK)
		throws com.stpl.app.exception.NoSuchHistCompanyGroupException {
		return getPersistence().findByPrimaryKey(histCompanyGroupPK);
	}

	/**
	* Returns the hist company group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param histCompanyGroupPK the primary key of the hist company group
	* @return the hist company group, or <code>null</code> if a hist company group with the primary key could not be found
	*/
	public static HistCompanyGroup fetchByPrimaryKey(
		HistCompanyGroupPK histCompanyGroupPK) {
		return getPersistence().fetchByPrimaryKey(histCompanyGroupPK);
	}

	public static java.util.Map<java.io.Serializable, HistCompanyGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the hist company groups.
	*
	* @return the hist company groups
	*/
	public static List<HistCompanyGroup> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the hist company groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist company groups
	* @param end the upper bound of the range of hist company groups (not inclusive)
	* @return the range of hist company groups
	*/
	public static List<HistCompanyGroup> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the hist company groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist company groups
	* @param end the upper bound of the range of hist company groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist company groups
	*/
	public static List<HistCompanyGroup> findAll(int start, int end,
		OrderByComparator<HistCompanyGroup> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the hist company groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist company groups
	* @param end the upper bound of the range of hist company groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist company groups
	*/
	public static List<HistCompanyGroup> findAll(int start, int end,
		OrderByComparator<HistCompanyGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the hist company groups from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of hist company groups.
	*
	* @return the number of hist company groups
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HistCompanyGroupPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistCompanyGroupPersistence, HistCompanyGroupPersistence> _serviceTracker =
		ServiceTrackerFactory.open(HistCompanyGroupPersistence.class);
}