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

import com.stpl.app.model.IvldSalesMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld sales master service. This utility wraps {@link com.stpl.app.service.persistence.impl.IvldSalesMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldSalesMasterPersistence
 * @see com.stpl.app.service.persistence.impl.IvldSalesMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldSalesMasterUtil {
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
	public static void clearCache(IvldSalesMaster ivldSalesMaster) {
		getPersistence().clearCache(ivldSalesMaster);
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
	public static List<IvldSalesMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldSalesMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldSalesMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldSalesMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldSalesMaster update(IvldSalesMaster ivldSalesMaster) {
		return getPersistence().update(ivldSalesMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldSalesMaster update(IvldSalesMaster ivldSalesMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldSalesMaster, serviceContext);
	}

	/**
	* Caches the ivld sales master in the entity cache if it is enabled.
	*
	* @param ivldSalesMaster the ivld sales master
	*/
	public static void cacheResult(IvldSalesMaster ivldSalesMaster) {
		getPersistence().cacheResult(ivldSalesMaster);
	}

	/**
	* Caches the ivld sales masters in the entity cache if it is enabled.
	*
	* @param ivldSalesMasters the ivld sales masters
	*/
	public static void cacheResult(List<IvldSalesMaster> ivldSalesMasters) {
		getPersistence().cacheResult(ivldSalesMasters);
	}

	/**
	* Creates a new ivld sales master with the primary key. Does not add the ivld sales master to the database.
	*
	* @param ivldSalesMasterSid the primary key for the new ivld sales master
	* @return the new ivld sales master
	*/
	public static IvldSalesMaster create(int ivldSalesMasterSid) {
		return getPersistence().create(ivldSalesMasterSid);
	}

	/**
	* Removes the ivld sales master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldSalesMasterSid the primary key of the ivld sales master
	* @return the ivld sales master that was removed
	* @throws NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
	*/
	public static IvldSalesMaster remove(int ivldSalesMasterSid)
		throws com.stpl.app.exception.NoSuchIvldSalesMasterException {
		return getPersistence().remove(ivldSalesMasterSid);
	}

	public static IvldSalesMaster updateImpl(IvldSalesMaster ivldSalesMaster) {
		return getPersistence().updateImpl(ivldSalesMaster);
	}

	/**
	* Returns the ivld sales master with the primary key or throws a {@link NoSuchIvldSalesMasterException} if it could not be found.
	*
	* @param ivldSalesMasterSid the primary key of the ivld sales master
	* @return the ivld sales master
	* @throws NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
	*/
	public static IvldSalesMaster findByPrimaryKey(int ivldSalesMasterSid)
		throws com.stpl.app.exception.NoSuchIvldSalesMasterException {
		return getPersistence().findByPrimaryKey(ivldSalesMasterSid);
	}

	/**
	* Returns the ivld sales master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldSalesMasterSid the primary key of the ivld sales master
	* @return the ivld sales master, or <code>null</code> if a ivld sales master with the primary key could not be found
	*/
	public static IvldSalesMaster fetchByPrimaryKey(int ivldSalesMasterSid) {
		return getPersistence().fetchByPrimaryKey(ivldSalesMasterSid);
	}

	public static java.util.Map<java.io.Serializable, IvldSalesMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld sales masters.
	*
	* @return the ivld sales masters
	*/
	public static List<IvldSalesMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld sales masters
	* @param end the upper bound of the range of ivld sales masters (not inclusive)
	* @return the range of ivld sales masters
	*/
	public static List<IvldSalesMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld sales masters
	* @param end the upper bound of the range of ivld sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld sales masters
	*/
	public static List<IvldSalesMaster> findAll(int start, int end,
		OrderByComparator<IvldSalesMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld sales masters
	* @param end the upper bound of the range of ivld sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld sales masters
	*/
	public static List<IvldSalesMaster> findAll(int start, int end,
		OrderByComparator<IvldSalesMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld sales masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld sales masters.
	*
	* @return the number of ivld sales masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldSalesMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldSalesMasterPersistence, IvldSalesMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldSalesMasterPersistence.class);
}