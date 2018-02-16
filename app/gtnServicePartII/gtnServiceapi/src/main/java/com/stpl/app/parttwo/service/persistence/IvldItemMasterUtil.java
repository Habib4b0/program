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

import com.stpl.app.parttwo.model.IvldItemMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld item master service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.IvldItemMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldItemMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldItemMasterUtil {
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
	public static void clearCache(IvldItemMaster ivldItemMaster) {
		getPersistence().clearCache(ivldItemMaster);
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
	public static List<IvldItemMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldItemMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldItemMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldItemMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldItemMaster update(IvldItemMaster ivldItemMaster) {
		return getPersistence().update(ivldItemMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldItemMaster update(IvldItemMaster ivldItemMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldItemMaster, serviceContext);
	}

	/**
	* Caches the ivld item master in the entity cache if it is enabled.
	*
	* @param ivldItemMaster the ivld item master
	*/
	public static void cacheResult(IvldItemMaster ivldItemMaster) {
		getPersistence().cacheResult(ivldItemMaster);
	}

	/**
	* Caches the ivld item masters in the entity cache if it is enabled.
	*
	* @param ivldItemMasters the ivld item masters
	*/
	public static void cacheResult(List<IvldItemMaster> ivldItemMasters) {
		getPersistence().cacheResult(ivldItemMasters);
	}

	/**
	* Creates a new ivld item master with the primary key. Does not add the ivld item master to the database.
	*
	* @param ivldItemMasterSid the primary key for the new ivld item master
	* @return the new ivld item master
	*/
	public static IvldItemMaster create(int ivldItemMasterSid) {
		return getPersistence().create(ivldItemMasterSid);
	}

	/**
	* Removes the ivld item master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemMasterSid the primary key of the ivld item master
	* @return the ivld item master that was removed
	* @throws NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
	*/
	public static IvldItemMaster remove(int ivldItemMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldItemMasterException {
		return getPersistence().remove(ivldItemMasterSid);
	}

	public static IvldItemMaster updateImpl(IvldItemMaster ivldItemMaster) {
		return getPersistence().updateImpl(ivldItemMaster);
	}

	/**
	* Returns the ivld item master with the primary key or throws a {@link NoSuchIvldItemMasterException} if it could not be found.
	*
	* @param ivldItemMasterSid the primary key of the ivld item master
	* @return the ivld item master
	* @throws NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
	*/
	public static IvldItemMaster findByPrimaryKey(int ivldItemMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldItemMasterException {
		return getPersistence().findByPrimaryKey(ivldItemMasterSid);
	}

	/**
	* Returns the ivld item master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldItemMasterSid the primary key of the ivld item master
	* @return the ivld item master, or <code>null</code> if a ivld item master with the primary key could not be found
	*/
	public static IvldItemMaster fetchByPrimaryKey(int ivldItemMasterSid) {
		return getPersistence().fetchByPrimaryKey(ivldItemMasterSid);
	}

	public static java.util.Map<java.io.Serializable, IvldItemMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld item masters.
	*
	* @return the ivld item masters
	*/
	public static List<IvldItemMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item masters
	* @param end the upper bound of the range of ivld item masters (not inclusive)
	* @return the range of ivld item masters
	*/
	public static List<IvldItemMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item masters
	* @param end the upper bound of the range of ivld item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld item masters
	*/
	public static List<IvldItemMaster> findAll(int start, int end,
		OrderByComparator<IvldItemMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item masters
	* @param end the upper bound of the range of ivld item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld item masters
	*/
	public static List<IvldItemMaster> findAll(int start, int end,
		OrderByComparator<IvldItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld item masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld item masters.
	*
	* @return the number of ivld item masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldItemMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldItemMasterPersistence, IvldItemMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldItemMasterPersistence.class);
}