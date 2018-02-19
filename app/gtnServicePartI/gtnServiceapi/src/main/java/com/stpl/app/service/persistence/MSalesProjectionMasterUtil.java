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

import com.stpl.app.model.MSalesProjectionMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the m sales projection master service. This utility wraps {@link com.stpl.app.service.persistence.impl.MSalesProjectionMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSalesProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.impl.MSalesProjectionMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class MSalesProjectionMasterUtil {
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
	public static void clearCache(MSalesProjectionMaster mSalesProjectionMaster) {
		getPersistence().clearCache(mSalesProjectionMaster);
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
	public static List<MSalesProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MSalesProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MSalesProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MSalesProjectionMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MSalesProjectionMaster update(
		MSalesProjectionMaster mSalesProjectionMaster) {
		return getPersistence().update(mSalesProjectionMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MSalesProjectionMaster update(
		MSalesProjectionMaster mSalesProjectionMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(mSalesProjectionMaster, serviceContext);
	}

	/**
	* Caches the m sales projection master in the entity cache if it is enabled.
	*
	* @param mSalesProjectionMaster the m sales projection master
	*/
	public static void cacheResult(
		MSalesProjectionMaster mSalesProjectionMaster) {
		getPersistence().cacheResult(mSalesProjectionMaster);
	}

	/**
	* Caches the m sales projection masters in the entity cache if it is enabled.
	*
	* @param mSalesProjectionMasters the m sales projection masters
	*/
	public static void cacheResult(
		List<MSalesProjectionMaster> mSalesProjectionMasters) {
		getPersistence().cacheResult(mSalesProjectionMasters);
	}

	/**
	* Creates a new m sales projection master with the primary key. Does not add the m sales projection master to the database.
	*
	* @param projectionDetailsSid the primary key for the new m sales projection master
	* @return the new m sales projection master
	*/
	public static MSalesProjectionMaster create(int projectionDetailsSid) {
		return getPersistence().create(projectionDetailsSid);
	}

	/**
	* Removes the m sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the m sales projection master
	* @return the m sales projection master that was removed
	* @throws NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
	*/
	public static MSalesProjectionMaster remove(int projectionDetailsSid)
		throws com.stpl.app.exception.NoSuchMSalesProjectionMasterException {
		return getPersistence().remove(projectionDetailsSid);
	}

	public static MSalesProjectionMaster updateImpl(
		MSalesProjectionMaster mSalesProjectionMaster) {
		return getPersistence().updateImpl(mSalesProjectionMaster);
	}

	/**
	* Returns the m sales projection master with the primary key or throws a {@link NoSuchMSalesProjectionMasterException} if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the m sales projection master
	* @return the m sales projection master
	* @throws NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
	*/
	public static MSalesProjectionMaster findByPrimaryKey(
		int projectionDetailsSid)
		throws com.stpl.app.exception.NoSuchMSalesProjectionMasterException {
		return getPersistence().findByPrimaryKey(projectionDetailsSid);
	}

	/**
	* Returns the m sales projection master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the m sales projection master
	* @return the m sales projection master, or <code>null</code> if a m sales projection master with the primary key could not be found
	*/
	public static MSalesProjectionMaster fetchByPrimaryKey(
		int projectionDetailsSid) {
		return getPersistence().fetchByPrimaryKey(projectionDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, MSalesProjectionMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the m sales projection masters.
	*
	* @return the m sales projection masters
	*/
	public static List<MSalesProjectionMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the m sales projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m sales projection masters
	* @param end the upper bound of the range of m sales projection masters (not inclusive)
	* @return the range of m sales projection masters
	*/
	public static List<MSalesProjectionMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the m sales projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m sales projection masters
	* @param end the upper bound of the range of m sales projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of m sales projection masters
	*/
	public static List<MSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<MSalesProjectionMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the m sales projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m sales projection masters
	* @param end the upper bound of the range of m sales projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of m sales projection masters
	*/
	public static List<MSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<MSalesProjectionMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the m sales projection masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of m sales projection masters.
	*
	* @return the number of m sales projection masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MSalesProjectionMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MSalesProjectionMasterPersistence, MSalesProjectionMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MSalesProjectionMasterPersistence.class);
}