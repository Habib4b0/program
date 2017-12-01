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

import com.stpl.app.model.StNmDiscountProjMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st nm discount proj master service. This utility wraps {@link com.stpl.app.service.persistence.impl.StNmDiscountProjMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmDiscountProjMasterPersistence
 * @see com.stpl.app.service.persistence.impl.StNmDiscountProjMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class StNmDiscountProjMasterUtil {
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
	public static void clearCache(StNmDiscountProjMaster stNmDiscountProjMaster) {
		getPersistence().clearCache(stNmDiscountProjMaster);
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
	public static List<StNmDiscountProjMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StNmDiscountProjMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StNmDiscountProjMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StNmDiscountProjMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StNmDiscountProjMaster update(
		StNmDiscountProjMaster stNmDiscountProjMaster) {
		return getPersistence().update(stNmDiscountProjMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StNmDiscountProjMaster update(
		StNmDiscountProjMaster stNmDiscountProjMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(stNmDiscountProjMaster, serviceContext);
	}

	/**
	* Caches the st nm discount proj master in the entity cache if it is enabled.
	*
	* @param stNmDiscountProjMaster the st nm discount proj master
	*/
	public static void cacheResult(
		StNmDiscountProjMaster stNmDiscountProjMaster) {
		getPersistence().cacheResult(stNmDiscountProjMaster);
	}

	/**
	* Caches the st nm discount proj masters in the entity cache if it is enabled.
	*
	* @param stNmDiscountProjMasters the st nm discount proj masters
	*/
	public static void cacheResult(
		List<StNmDiscountProjMaster> stNmDiscountProjMasters) {
		getPersistence().cacheResult(stNmDiscountProjMasters);
	}

	/**
	* Creates a new st nm discount proj master with the primary key. Does not add the st nm discount proj master to the database.
	*
	* @param stNmDiscountProjMasterPK the primary key for the new st nm discount proj master
	* @return the new st nm discount proj master
	*/
	public static StNmDiscountProjMaster create(
		StNmDiscountProjMasterPK stNmDiscountProjMasterPK) {
		return getPersistence().create(stNmDiscountProjMasterPK);
	}

	/**
	* Removes the st nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
	* @return the st nm discount proj master that was removed
	* @throws NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
	*/
	public static StNmDiscountProjMaster remove(
		StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
		throws com.stpl.app.exception.NoSuchStNmDiscountProjMasterException {
		return getPersistence().remove(stNmDiscountProjMasterPK);
	}

	public static StNmDiscountProjMaster updateImpl(
		StNmDiscountProjMaster stNmDiscountProjMaster) {
		return getPersistence().updateImpl(stNmDiscountProjMaster);
	}

	/**
	* Returns the st nm discount proj master with the primary key or throws a {@link NoSuchStNmDiscountProjMasterException} if it could not be found.
	*
	* @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
	* @return the st nm discount proj master
	* @throws NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
	*/
	public static StNmDiscountProjMaster findByPrimaryKey(
		StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
		throws com.stpl.app.exception.NoSuchStNmDiscountProjMasterException {
		return getPersistence().findByPrimaryKey(stNmDiscountProjMasterPK);
	}

	/**
	* Returns the st nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
	* @return the st nm discount proj master, or <code>null</code> if a st nm discount proj master with the primary key could not be found
	*/
	public static StNmDiscountProjMaster fetchByPrimaryKey(
		StNmDiscountProjMasterPK stNmDiscountProjMasterPK) {
		return getPersistence().fetchByPrimaryKey(stNmDiscountProjMasterPK);
	}

	public static java.util.Map<java.io.Serializable, StNmDiscountProjMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st nm discount proj masters.
	*
	* @return the st nm discount proj masters
	*/
	public static List<StNmDiscountProjMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount proj masters
	* @param end the upper bound of the range of st nm discount proj masters (not inclusive)
	* @return the range of st nm discount proj masters
	*/
	public static List<StNmDiscountProjMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount proj masters
	* @param end the upper bound of the range of st nm discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm discount proj masters
	*/
	public static List<StNmDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<StNmDiscountProjMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount proj masters
	* @param end the upper bound of the range of st nm discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm discount proj masters
	*/
	public static List<StNmDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<StNmDiscountProjMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st nm discount proj masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st nm discount proj masters.
	*
	* @return the number of st nm discount proj masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StNmDiscountProjMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StNmDiscountProjMasterPersistence, StNmDiscountProjMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StNmDiscountProjMasterPersistence.class);
}