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

import com.stpl.app.model.CustomViewMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the custom view master service. This utility wraps {@link com.stpl.app.service.persistence.impl.CustomViewMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomViewMasterPersistence
 * @see com.stpl.app.service.persistence.impl.CustomViewMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class CustomViewMasterUtil {
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
	public static void clearCache(CustomViewMaster customViewMaster) {
		getPersistence().clearCache(customViewMaster);
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
	public static List<CustomViewMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CustomViewMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CustomViewMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CustomViewMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CustomViewMaster update(CustomViewMaster customViewMaster) {
		return getPersistence().update(customViewMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CustomViewMaster update(CustomViewMaster customViewMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(customViewMaster, serviceContext);
	}

	/**
	* Caches the custom view master in the entity cache if it is enabled.
	*
	* @param customViewMaster the custom view master
	*/
	public static void cacheResult(CustomViewMaster customViewMaster) {
		getPersistence().cacheResult(customViewMaster);
	}

	/**
	* Caches the custom view masters in the entity cache if it is enabled.
	*
	* @param customViewMasters the custom view masters
	*/
	public static void cacheResult(List<CustomViewMaster> customViewMasters) {
		getPersistence().cacheResult(customViewMasters);
	}

	/**
	* Creates a new custom view master with the primary key. Does not add the custom view master to the database.
	*
	* @param customViewMasterSid the primary key for the new custom view master
	* @return the new custom view master
	*/
	public static CustomViewMaster create(int customViewMasterSid) {
		return getPersistence().create(customViewMasterSid);
	}

	/**
	* Removes the custom view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customViewMasterSid the primary key of the custom view master
	* @return the custom view master that was removed
	* @throws NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
	*/
	public static CustomViewMaster remove(int customViewMasterSid)
		throws com.stpl.app.exception.NoSuchCustomViewMasterException {
		return getPersistence().remove(customViewMasterSid);
	}

	public static CustomViewMaster updateImpl(CustomViewMaster customViewMaster) {
		return getPersistence().updateImpl(customViewMaster);
	}

	/**
	* Returns the custom view master with the primary key or throws a {@link NoSuchCustomViewMasterException} if it could not be found.
	*
	* @param customViewMasterSid the primary key of the custom view master
	* @return the custom view master
	* @throws NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
	*/
	public static CustomViewMaster findByPrimaryKey(int customViewMasterSid)
		throws com.stpl.app.exception.NoSuchCustomViewMasterException {
		return getPersistence().findByPrimaryKey(customViewMasterSid);
	}

	/**
	* Returns the custom view master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param customViewMasterSid the primary key of the custom view master
	* @return the custom view master, or <code>null</code> if a custom view master with the primary key could not be found
	*/
	public static CustomViewMaster fetchByPrimaryKey(int customViewMasterSid) {
		return getPersistence().fetchByPrimaryKey(customViewMasterSid);
	}

	public static java.util.Map<java.io.Serializable, CustomViewMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the custom view masters.
	*
	* @return the custom view masters
	*/
	public static List<CustomViewMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the custom view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view masters
	* @param end the upper bound of the range of custom view masters (not inclusive)
	* @return the range of custom view masters
	*/
	public static List<CustomViewMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the custom view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view masters
	* @param end the upper bound of the range of custom view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of custom view masters
	*/
	public static List<CustomViewMaster> findAll(int start, int end,
		OrderByComparator<CustomViewMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the custom view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view masters
	* @param end the upper bound of the range of custom view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of custom view masters
	*/
	public static List<CustomViewMaster> findAll(int start, int end,
		OrderByComparator<CustomViewMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the custom view masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of custom view masters.
	*
	* @return the number of custom view masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CustomViewMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CustomViewMasterPersistence, CustomViewMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CustomViewMasterPersistence.class);
}