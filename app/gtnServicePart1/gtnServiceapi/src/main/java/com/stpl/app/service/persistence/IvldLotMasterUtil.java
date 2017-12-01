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

import com.stpl.app.model.IvldLotMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld lot master service. This utility wraps {@link com.stpl.app.service.persistence.impl.IvldLotMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldLotMasterPersistence
 * @see com.stpl.app.service.persistence.impl.IvldLotMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldLotMasterUtil {
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
	public static void clearCache(IvldLotMaster ivldLotMaster) {
		getPersistence().clearCache(ivldLotMaster);
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
	public static List<IvldLotMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldLotMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldLotMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldLotMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldLotMaster update(IvldLotMaster ivldLotMaster) {
		return getPersistence().update(ivldLotMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldLotMaster update(IvldLotMaster ivldLotMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldLotMaster, serviceContext);
	}

	/**
	* Caches the ivld lot master in the entity cache if it is enabled.
	*
	* @param ivldLotMaster the ivld lot master
	*/
	public static void cacheResult(IvldLotMaster ivldLotMaster) {
		getPersistence().cacheResult(ivldLotMaster);
	}

	/**
	* Caches the ivld lot masters in the entity cache if it is enabled.
	*
	* @param ivldLotMasters the ivld lot masters
	*/
	public static void cacheResult(List<IvldLotMaster> ivldLotMasters) {
		getPersistence().cacheResult(ivldLotMasters);
	}

	/**
	* Creates a new ivld lot master with the primary key. Does not add the ivld lot master to the database.
	*
	* @param ivldLotMasterSid the primary key for the new ivld lot master
	* @return the new ivld lot master
	*/
	public static IvldLotMaster create(int ivldLotMasterSid) {
		return getPersistence().create(ivldLotMasterSid);
	}

	/**
	* Removes the ivld lot master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldLotMasterSid the primary key of the ivld lot master
	* @return the ivld lot master that was removed
	* @throws NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
	*/
	public static IvldLotMaster remove(int ivldLotMasterSid)
		throws com.stpl.app.exception.NoSuchIvldLotMasterException {
		return getPersistence().remove(ivldLotMasterSid);
	}

	public static IvldLotMaster updateImpl(IvldLotMaster ivldLotMaster) {
		return getPersistence().updateImpl(ivldLotMaster);
	}

	/**
	* Returns the ivld lot master with the primary key or throws a {@link NoSuchIvldLotMasterException} if it could not be found.
	*
	* @param ivldLotMasterSid the primary key of the ivld lot master
	* @return the ivld lot master
	* @throws NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
	*/
	public static IvldLotMaster findByPrimaryKey(int ivldLotMasterSid)
		throws com.stpl.app.exception.NoSuchIvldLotMasterException {
		return getPersistence().findByPrimaryKey(ivldLotMasterSid);
	}

	/**
	* Returns the ivld lot master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldLotMasterSid the primary key of the ivld lot master
	* @return the ivld lot master, or <code>null</code> if a ivld lot master with the primary key could not be found
	*/
	public static IvldLotMaster fetchByPrimaryKey(int ivldLotMasterSid) {
		return getPersistence().fetchByPrimaryKey(ivldLotMasterSid);
	}

	public static java.util.Map<java.io.Serializable, IvldLotMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld lot masters.
	*
	* @return the ivld lot masters
	*/
	public static List<IvldLotMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld lot masters
	* @param end the upper bound of the range of ivld lot masters (not inclusive)
	* @return the range of ivld lot masters
	*/
	public static List<IvldLotMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld lot masters
	* @param end the upper bound of the range of ivld lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld lot masters
	*/
	public static List<IvldLotMaster> findAll(int start, int end,
		OrderByComparator<IvldLotMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld lot masters
	* @param end the upper bound of the range of ivld lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld lot masters
	*/
	public static List<IvldLotMaster> findAll(int start, int end,
		OrderByComparator<IvldLotMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld lot masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld lot masters.
	*
	* @return the number of ivld lot masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldLotMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldLotMasterPersistence, IvldLotMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldLotMasterPersistence.class);
}