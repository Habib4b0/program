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

import com.stpl.app.model.AccrualMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the accrual master service. This utility wraps {@link com.stpl.app.service.persistence.impl.AccrualMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccrualMasterPersistence
 * @see com.stpl.app.service.persistence.impl.AccrualMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class AccrualMasterUtil {
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
	public static void clearCache(AccrualMaster accrualMaster) {
		getPersistence().clearCache(accrualMaster);
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
	public static List<AccrualMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccrualMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccrualMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccrualMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccrualMaster update(AccrualMaster accrualMaster) {
		return getPersistence().update(accrualMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccrualMaster update(AccrualMaster accrualMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(accrualMaster, serviceContext);
	}

	/**
	* Caches the accrual master in the entity cache if it is enabled.
	*
	* @param accrualMaster the accrual master
	*/
	public static void cacheResult(AccrualMaster accrualMaster) {
		getPersistence().cacheResult(accrualMaster);
	}

	/**
	* Caches the accrual masters in the entity cache if it is enabled.
	*
	* @param accrualMasters the accrual masters
	*/
	public static void cacheResult(List<AccrualMaster> accrualMasters) {
		getPersistence().cacheResult(accrualMasters);
	}

	/**
	* Creates a new accrual master with the primary key. Does not add the accrual master to the database.
	*
	* @param accrualMasterSid the primary key for the new accrual master
	* @return the new accrual master
	*/
	public static AccrualMaster create(int accrualMasterSid) {
		return getPersistence().create(accrualMasterSid);
	}

	/**
	* Removes the accrual master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accrualMasterSid the primary key of the accrual master
	* @return the accrual master that was removed
	* @throws NoSuchAccrualMasterException if a accrual master with the primary key could not be found
	*/
	public static AccrualMaster remove(int accrualMasterSid)
		throws com.stpl.app.exception.NoSuchAccrualMasterException {
		return getPersistence().remove(accrualMasterSid);
	}

	public static AccrualMaster updateImpl(AccrualMaster accrualMaster) {
		return getPersistence().updateImpl(accrualMaster);
	}

	/**
	* Returns the accrual master with the primary key or throws a {@link NoSuchAccrualMasterException} if it could not be found.
	*
	* @param accrualMasterSid the primary key of the accrual master
	* @return the accrual master
	* @throws NoSuchAccrualMasterException if a accrual master with the primary key could not be found
	*/
	public static AccrualMaster findByPrimaryKey(int accrualMasterSid)
		throws com.stpl.app.exception.NoSuchAccrualMasterException {
		return getPersistence().findByPrimaryKey(accrualMasterSid);
	}

	/**
	* Returns the accrual master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accrualMasterSid the primary key of the accrual master
	* @return the accrual master, or <code>null</code> if a accrual master with the primary key could not be found
	*/
	public static AccrualMaster fetchByPrimaryKey(int accrualMasterSid) {
		return getPersistence().fetchByPrimaryKey(accrualMasterSid);
	}

	public static java.util.Map<java.io.Serializable, AccrualMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the accrual masters.
	*
	* @return the accrual masters
	*/
	public static List<AccrualMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the accrual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of accrual masters
	* @param end the upper bound of the range of accrual masters (not inclusive)
	* @return the range of accrual masters
	*/
	public static List<AccrualMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the accrual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of accrual masters
	* @param end the upper bound of the range of accrual masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of accrual masters
	*/
	public static List<AccrualMaster> findAll(int start, int end,
		OrderByComparator<AccrualMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the accrual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of accrual masters
	* @param end the upper bound of the range of accrual masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of accrual masters
	*/
	public static List<AccrualMaster> findAll(int start, int end,
		OrderByComparator<AccrualMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the accrual masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of accrual masters.
	*
	* @return the number of accrual masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AccrualMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AccrualMasterPersistence, AccrualMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AccrualMasterPersistence.class);
}