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

import com.stpl.app.model.NetSalesFormulaMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the net sales formula master service. This utility wraps {@link com.stpl.app.service.persistence.impl.NetSalesFormulaMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NetSalesFormulaMasterPersistence
 * @see com.stpl.app.service.persistence.impl.NetSalesFormulaMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class NetSalesFormulaMasterUtil {
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
	public static void clearCache(NetSalesFormulaMaster netSalesFormulaMaster) {
		getPersistence().clearCache(netSalesFormulaMaster);
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
	public static List<NetSalesFormulaMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NetSalesFormulaMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NetSalesFormulaMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NetSalesFormulaMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NetSalesFormulaMaster update(
		NetSalesFormulaMaster netSalesFormulaMaster) {
		return getPersistence().update(netSalesFormulaMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NetSalesFormulaMaster update(
		NetSalesFormulaMaster netSalesFormulaMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(netSalesFormulaMaster, serviceContext);
	}

	/**
	* Caches the net sales formula master in the entity cache if it is enabled.
	*
	* @param netSalesFormulaMaster the net sales formula master
	*/
	public static void cacheResult(NetSalesFormulaMaster netSalesFormulaMaster) {
		getPersistence().cacheResult(netSalesFormulaMaster);
	}

	/**
	* Caches the net sales formula masters in the entity cache if it is enabled.
	*
	* @param netSalesFormulaMasters the net sales formula masters
	*/
	public static void cacheResult(
		List<NetSalesFormulaMaster> netSalesFormulaMasters) {
		getPersistence().cacheResult(netSalesFormulaMasters);
	}

	/**
	* Creates a new net sales formula master with the primary key. Does not add the net sales formula master to the database.
	*
	* @param netSalesFormulaMasterSid the primary key for the new net sales formula master
	* @return the new net sales formula master
	*/
	public static NetSalesFormulaMaster create(int netSalesFormulaMasterSid) {
		return getPersistence().create(netSalesFormulaMasterSid);
	}

	/**
	* Removes the net sales formula master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param netSalesFormulaMasterSid the primary key of the net sales formula master
	* @return the net sales formula master that was removed
	* @throws NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
	*/
	public static NetSalesFormulaMaster remove(int netSalesFormulaMasterSid)
		throws com.stpl.app.exception.NoSuchNetSalesFormulaMasterException {
		return getPersistence().remove(netSalesFormulaMasterSid);
	}

	public static NetSalesFormulaMaster updateImpl(
		NetSalesFormulaMaster netSalesFormulaMaster) {
		return getPersistence().updateImpl(netSalesFormulaMaster);
	}

	/**
	* Returns the net sales formula master with the primary key or throws a {@link NoSuchNetSalesFormulaMasterException} if it could not be found.
	*
	* @param netSalesFormulaMasterSid the primary key of the net sales formula master
	* @return the net sales formula master
	* @throws NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
	*/
	public static NetSalesFormulaMaster findByPrimaryKey(
		int netSalesFormulaMasterSid)
		throws com.stpl.app.exception.NoSuchNetSalesFormulaMasterException {
		return getPersistence().findByPrimaryKey(netSalesFormulaMasterSid);
	}

	/**
	* Returns the net sales formula master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param netSalesFormulaMasterSid the primary key of the net sales formula master
	* @return the net sales formula master, or <code>null</code> if a net sales formula master with the primary key could not be found
	*/
	public static NetSalesFormulaMaster fetchByPrimaryKey(
		int netSalesFormulaMasterSid) {
		return getPersistence().fetchByPrimaryKey(netSalesFormulaMasterSid);
	}

	public static java.util.Map<java.io.Serializable, NetSalesFormulaMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the net sales formula masters.
	*
	* @return the net sales formula masters
	*/
	public static List<NetSalesFormulaMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the net sales formula masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of net sales formula masters
	* @param end the upper bound of the range of net sales formula masters (not inclusive)
	* @return the range of net sales formula masters
	*/
	public static List<NetSalesFormulaMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the net sales formula masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of net sales formula masters
	* @param end the upper bound of the range of net sales formula masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of net sales formula masters
	*/
	public static List<NetSalesFormulaMaster> findAll(int start, int end,
		OrderByComparator<NetSalesFormulaMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the net sales formula masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of net sales formula masters
	* @param end the upper bound of the range of net sales formula masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of net sales formula masters
	*/
	public static List<NetSalesFormulaMaster> findAll(int start, int end,
		OrderByComparator<NetSalesFormulaMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the net sales formula masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of net sales formula masters.
	*
	* @return the number of net sales formula masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NetSalesFormulaMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NetSalesFormulaMasterPersistence, NetSalesFormulaMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NetSalesFormulaMasterPersistence.class);
}