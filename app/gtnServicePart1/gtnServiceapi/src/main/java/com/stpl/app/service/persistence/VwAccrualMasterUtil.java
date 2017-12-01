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

import com.stpl.app.model.VwAccrualMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw accrual master service. This utility wraps {@link com.stpl.app.service.persistence.impl.VwAccrualMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwAccrualMasterPersistence
 * @see com.stpl.app.service.persistence.impl.VwAccrualMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class VwAccrualMasterUtil {
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
	public static void clearCache(VwAccrualMaster vwAccrualMaster) {
		getPersistence().clearCache(vwAccrualMaster);
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
	public static List<VwAccrualMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwAccrualMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwAccrualMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwAccrualMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwAccrualMaster update(VwAccrualMaster vwAccrualMaster) {
		return getPersistence().update(vwAccrualMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwAccrualMaster update(VwAccrualMaster vwAccrualMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(vwAccrualMaster, serviceContext);
	}

	/**
	* Caches the vw accrual master in the entity cache if it is enabled.
	*
	* @param vwAccrualMaster the vw accrual master
	*/
	public static void cacheResult(VwAccrualMaster vwAccrualMaster) {
		getPersistence().cacheResult(vwAccrualMaster);
	}

	/**
	* Caches the vw accrual masters in the entity cache if it is enabled.
	*
	* @param vwAccrualMasters the vw accrual masters
	*/
	public static void cacheResult(List<VwAccrualMaster> vwAccrualMasters) {
		getPersistence().cacheResult(vwAccrualMasters);
	}

	/**
	* Creates a new vw accrual master with the primary key. Does not add the vw accrual master to the database.
	*
	* @param accrualMasterSid the primary key for the new vw accrual master
	* @return the new vw accrual master
	*/
	public static VwAccrualMaster create(int accrualMasterSid) {
		return getPersistence().create(accrualMasterSid);
	}

	/**
	* Removes the vw accrual master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accrualMasterSid the primary key of the vw accrual master
	* @return the vw accrual master that was removed
	* @throws NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
	*/
	public static VwAccrualMaster remove(int accrualMasterSid)
		throws com.stpl.app.exception.NoSuchVwAccrualMasterException {
		return getPersistence().remove(accrualMasterSid);
	}

	public static VwAccrualMaster updateImpl(VwAccrualMaster vwAccrualMaster) {
		return getPersistence().updateImpl(vwAccrualMaster);
	}

	/**
	* Returns the vw accrual master with the primary key or throws a {@link NoSuchVwAccrualMasterException} if it could not be found.
	*
	* @param accrualMasterSid the primary key of the vw accrual master
	* @return the vw accrual master
	* @throws NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
	*/
	public static VwAccrualMaster findByPrimaryKey(int accrualMasterSid)
		throws com.stpl.app.exception.NoSuchVwAccrualMasterException {
		return getPersistence().findByPrimaryKey(accrualMasterSid);
	}

	/**
	* Returns the vw accrual master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accrualMasterSid the primary key of the vw accrual master
	* @return the vw accrual master, or <code>null</code> if a vw accrual master with the primary key could not be found
	*/
	public static VwAccrualMaster fetchByPrimaryKey(int accrualMasterSid) {
		return getPersistence().fetchByPrimaryKey(accrualMasterSid);
	}

	public static java.util.Map<java.io.Serializable, VwAccrualMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw accrual masters.
	*
	* @return the vw accrual masters
	*/
	public static List<VwAccrualMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw accrual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw accrual masters
	* @param end the upper bound of the range of vw accrual masters (not inclusive)
	* @return the range of vw accrual masters
	*/
	public static List<VwAccrualMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw accrual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw accrual masters
	* @param end the upper bound of the range of vw accrual masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw accrual masters
	*/
	public static List<VwAccrualMaster> findAll(int start, int end,
		OrderByComparator<VwAccrualMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw accrual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw accrual masters
	* @param end the upper bound of the range of vw accrual masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw accrual masters
	*/
	public static List<VwAccrualMaster> findAll(int start, int end,
		OrderByComparator<VwAccrualMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw accrual masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw accrual masters.
	*
	* @return the number of vw accrual masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwAccrualMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwAccrualMasterPersistence, VwAccrualMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwAccrualMasterPersistence.class);
}