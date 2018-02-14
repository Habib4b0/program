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

import com.stpl.app.model.NmPpaProjectionMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the nm ppa projection master service. This utility wraps {@link com.stpl.app.service.persistence.impl.NmPpaProjectionMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmPpaProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.impl.NmPpaProjectionMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class NmPpaProjectionMasterUtil {
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
	public static void clearCache(NmPpaProjectionMaster nmPpaProjectionMaster) {
		getPersistence().clearCache(nmPpaProjectionMaster);
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
	public static List<NmPpaProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NmPpaProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NmPpaProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NmPpaProjectionMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NmPpaProjectionMaster update(
		NmPpaProjectionMaster nmPpaProjectionMaster) {
		return getPersistence().update(nmPpaProjectionMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NmPpaProjectionMaster update(
		NmPpaProjectionMaster nmPpaProjectionMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(nmPpaProjectionMaster, serviceContext);
	}

	/**
	* Caches the nm ppa projection master in the entity cache if it is enabled.
	*
	* @param nmPpaProjectionMaster the nm ppa projection master
	*/
	public static void cacheResult(NmPpaProjectionMaster nmPpaProjectionMaster) {
		getPersistence().cacheResult(nmPpaProjectionMaster);
	}

	/**
	* Caches the nm ppa projection masters in the entity cache if it is enabled.
	*
	* @param nmPpaProjectionMasters the nm ppa projection masters
	*/
	public static void cacheResult(
		List<NmPpaProjectionMaster> nmPpaProjectionMasters) {
		getPersistence().cacheResult(nmPpaProjectionMasters);
	}

	/**
	* Creates a new nm ppa projection master with the primary key. Does not add the nm ppa projection master to the database.
	*
	* @param projectionDetailsSid the primary key for the new nm ppa projection master
	* @return the new nm ppa projection master
	*/
	public static NmPpaProjectionMaster create(int projectionDetailsSid) {
		return getPersistence().create(projectionDetailsSid);
	}

	/**
	* Removes the nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the nm ppa projection master
	* @return the nm ppa projection master that was removed
	* @throws NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
	*/
	public static NmPpaProjectionMaster remove(int projectionDetailsSid)
		throws com.stpl.app.exception.NoSuchNmPpaProjectionMasterException {
		return getPersistence().remove(projectionDetailsSid);
	}

	public static NmPpaProjectionMaster updateImpl(
		NmPpaProjectionMaster nmPpaProjectionMaster) {
		return getPersistence().updateImpl(nmPpaProjectionMaster);
	}

	/**
	* Returns the nm ppa projection master with the primary key or throws a {@link NoSuchNmPpaProjectionMasterException} if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the nm ppa projection master
	* @return the nm ppa projection master
	* @throws NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
	*/
	public static NmPpaProjectionMaster findByPrimaryKey(
		int projectionDetailsSid)
		throws com.stpl.app.exception.NoSuchNmPpaProjectionMasterException {
		return getPersistence().findByPrimaryKey(projectionDetailsSid);
	}

	/**
	* Returns the nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the nm ppa projection master
	* @return the nm ppa projection master, or <code>null</code> if a nm ppa projection master with the primary key could not be found
	*/
	public static NmPpaProjectionMaster fetchByPrimaryKey(
		int projectionDetailsSid) {
		return getPersistence().fetchByPrimaryKey(projectionDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, NmPpaProjectionMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the nm ppa projection masters.
	*
	* @return the nm ppa projection masters
	*/
	public static List<NmPpaProjectionMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projection masters
	* @param end the upper bound of the range of nm ppa projection masters (not inclusive)
	* @return the range of nm ppa projection masters
	*/
	public static List<NmPpaProjectionMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projection masters
	* @param end the upper bound of the range of nm ppa projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm ppa projection masters
	*/
	public static List<NmPpaProjectionMaster> findAll(int start, int end,
		OrderByComparator<NmPpaProjectionMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projection masters
	* @param end the upper bound of the range of nm ppa projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm ppa projection masters
	*/
	public static List<NmPpaProjectionMaster> findAll(int start, int end,
		OrderByComparator<NmPpaProjectionMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the nm ppa projection masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of nm ppa projection masters.
	*
	* @return the number of nm ppa projection masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NmPpaProjectionMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NmPpaProjectionMasterPersistence, NmPpaProjectionMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NmPpaProjectionMasterPersistence.class);
}