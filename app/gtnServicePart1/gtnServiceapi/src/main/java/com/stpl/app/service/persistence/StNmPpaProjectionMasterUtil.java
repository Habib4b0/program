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

import com.stpl.app.model.StNmPpaProjectionMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st nm ppa projection master service. This utility wraps {@link com.stpl.app.service.persistence.impl.StNmPpaProjectionMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmPpaProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.impl.StNmPpaProjectionMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class StNmPpaProjectionMasterUtil {
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
	public static void clearCache(
		StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		getPersistence().clearCache(stNmPpaProjectionMaster);
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
	public static List<StNmPpaProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StNmPpaProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StNmPpaProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StNmPpaProjectionMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StNmPpaProjectionMaster update(
		StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		return getPersistence().update(stNmPpaProjectionMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StNmPpaProjectionMaster update(
		StNmPpaProjectionMaster stNmPpaProjectionMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(stNmPpaProjectionMaster, serviceContext);
	}

	/**
	* Caches the st nm ppa projection master in the entity cache if it is enabled.
	*
	* @param stNmPpaProjectionMaster the st nm ppa projection master
	*/
	public static void cacheResult(
		StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		getPersistence().cacheResult(stNmPpaProjectionMaster);
	}

	/**
	* Caches the st nm ppa projection masters in the entity cache if it is enabled.
	*
	* @param stNmPpaProjectionMasters the st nm ppa projection masters
	*/
	public static void cacheResult(
		List<StNmPpaProjectionMaster> stNmPpaProjectionMasters) {
		getPersistence().cacheResult(stNmPpaProjectionMasters);
	}

	/**
	* Creates a new st nm ppa projection master with the primary key. Does not add the st nm ppa projection master to the database.
	*
	* @param stNmPpaProjectionMasterPK the primary key for the new st nm ppa projection master
	* @return the new st nm ppa projection master
	*/
	public static StNmPpaProjectionMaster create(
		StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK) {
		return getPersistence().create(stNmPpaProjectionMasterPK);
	}

	/**
	* Removes the st nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
	* @return the st nm ppa projection master that was removed
	* @throws NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
	*/
	public static StNmPpaProjectionMaster remove(
		StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
		throws com.stpl.app.exception.NoSuchStNmPpaProjectionMasterException {
		return getPersistence().remove(stNmPpaProjectionMasterPK);
	}

	public static StNmPpaProjectionMaster updateImpl(
		StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		return getPersistence().updateImpl(stNmPpaProjectionMaster);
	}

	/**
	* Returns the st nm ppa projection master with the primary key or throws a {@link NoSuchStNmPpaProjectionMasterException} if it could not be found.
	*
	* @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
	* @return the st nm ppa projection master
	* @throws NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
	*/
	public static StNmPpaProjectionMaster findByPrimaryKey(
		StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
		throws com.stpl.app.exception.NoSuchStNmPpaProjectionMasterException {
		return getPersistence().findByPrimaryKey(stNmPpaProjectionMasterPK);
	}

	/**
	* Returns the st nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
	* @return the st nm ppa projection master, or <code>null</code> if a st nm ppa projection master with the primary key could not be found
	*/
	public static StNmPpaProjectionMaster fetchByPrimaryKey(
		StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK) {
		return getPersistence().fetchByPrimaryKey(stNmPpaProjectionMasterPK);
	}

	public static java.util.Map<java.io.Serializable, StNmPpaProjectionMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st nm ppa projection masters.
	*
	* @return the st nm ppa projection masters
	*/
	public static List<StNmPpaProjectionMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm ppa projection masters
	* @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
	* @return the range of st nm ppa projection masters
	*/
	public static List<StNmPpaProjectionMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm ppa projection masters
	* @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm ppa projection masters
	*/
	public static List<StNmPpaProjectionMaster> findAll(int start, int end,
		OrderByComparator<StNmPpaProjectionMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm ppa projection masters
	* @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm ppa projection masters
	*/
	public static List<StNmPpaProjectionMaster> findAll(int start, int end,
		OrderByComparator<StNmPpaProjectionMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st nm ppa projection masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st nm ppa projection masters.
	*
	* @return the number of st nm ppa projection masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StNmPpaProjectionMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StNmPpaProjectionMasterPersistence, StNmPpaProjectionMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StNmPpaProjectionMasterPersistence.class);
}