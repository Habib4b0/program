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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.parttwo.model.VwItemMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw item master service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.VwItemMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.VwItemMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class VwItemMasterUtil {
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
	public static void clearCache(VwItemMaster vwItemMaster) {
		getPersistence().clearCache(vwItemMaster);
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
	public static List<VwItemMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwItemMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwItemMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwItemMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwItemMaster update(VwItemMaster vwItemMaster) {
		return getPersistence().update(vwItemMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwItemMaster update(VwItemMaster vwItemMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(vwItemMaster, serviceContext);
	}

	/**
	* Caches the vw item master in the entity cache if it is enabled.
	*
	* @param vwItemMaster the vw item master
	*/
	public static void cacheResult(VwItemMaster vwItemMaster) {
		getPersistence().cacheResult(vwItemMaster);
	}

	/**
	* Caches the vw item masters in the entity cache if it is enabled.
	*
	* @param vwItemMasters the vw item masters
	*/
	public static void cacheResult(List<VwItemMaster> vwItemMasters) {
		getPersistence().cacheResult(vwItemMasters);
	}

	/**
	* Creates a new vw item master with the primary key. Does not add the vw item master to the database.
	*
	* @param itemMasterSid the primary key for the new vw item master
	* @return the new vw item master
	*/
	public static VwItemMaster create(int itemMasterSid) {
		return getPersistence().create(itemMasterSid);
	}

	/**
	* Removes the vw item master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemMasterSid the primary key of the vw item master
	* @return the vw item master that was removed
	* @throws NoSuchVwItemMasterException if a vw item master with the primary key could not be found
	*/
	public static VwItemMaster remove(int itemMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwItemMasterException {
		return getPersistence().remove(itemMasterSid);
	}

	public static VwItemMaster updateImpl(VwItemMaster vwItemMaster) {
		return getPersistence().updateImpl(vwItemMaster);
	}

	/**
	* Returns the vw item master with the primary key or throws a {@link NoSuchVwItemMasterException} if it could not be found.
	*
	* @param itemMasterSid the primary key of the vw item master
	* @return the vw item master
	* @throws NoSuchVwItemMasterException if a vw item master with the primary key could not be found
	*/
	public static VwItemMaster findByPrimaryKey(int itemMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwItemMasterException {
		return getPersistence().findByPrimaryKey(itemMasterSid);
	}

	/**
	* Returns the vw item master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemMasterSid the primary key of the vw item master
	* @return the vw item master, or <code>null</code> if a vw item master with the primary key could not be found
	*/
	public static VwItemMaster fetchByPrimaryKey(int itemMasterSid) {
		return getPersistence().fetchByPrimaryKey(itemMasterSid);
	}

	public static java.util.Map<java.io.Serializable, VwItemMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw item masters.
	*
	* @return the vw item masters
	*/
	public static List<VwItemMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item masters
	* @param end the upper bound of the range of vw item masters (not inclusive)
	* @return the range of vw item masters
	*/
	public static List<VwItemMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item masters
	* @param end the upper bound of the range of vw item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw item masters
	*/
	public static List<VwItemMaster> findAll(int start, int end,
		OrderByComparator<VwItemMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item masters
	* @param end the upper bound of the range of vw item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw item masters
	*/
	public static List<VwItemMaster> findAll(int start, int end,
		OrderByComparator<VwItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw item masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw item masters.
	*
	* @return the number of vw item masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwItemMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwItemMasterPersistence, VwItemMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwItemMasterPersistence.class);
}