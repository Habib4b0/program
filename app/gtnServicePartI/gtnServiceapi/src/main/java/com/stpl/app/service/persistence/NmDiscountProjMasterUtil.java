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

import com.stpl.app.model.NmDiscountProjMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the nm discount proj master service. This utility wraps {@link com.stpl.app.service.persistence.impl.NmDiscountProjMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmDiscountProjMasterPersistence
 * @see com.stpl.app.service.persistence.impl.NmDiscountProjMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class NmDiscountProjMasterUtil {
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
	public static void clearCache(NmDiscountProjMaster nmDiscountProjMaster) {
		getPersistence().clearCache(nmDiscountProjMaster);
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
	public static List<NmDiscountProjMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NmDiscountProjMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NmDiscountProjMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NmDiscountProjMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NmDiscountProjMaster update(
		NmDiscountProjMaster nmDiscountProjMaster) {
		return getPersistence().update(nmDiscountProjMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NmDiscountProjMaster update(
		NmDiscountProjMaster nmDiscountProjMaster, ServiceContext serviceContext) {
		return getPersistence().update(nmDiscountProjMaster, serviceContext);
	}

	/**
	* Caches the nm discount proj master in the entity cache if it is enabled.
	*
	* @param nmDiscountProjMaster the nm discount proj master
	*/
	public static void cacheResult(NmDiscountProjMaster nmDiscountProjMaster) {
		getPersistence().cacheResult(nmDiscountProjMaster);
	}

	/**
	* Caches the nm discount proj masters in the entity cache if it is enabled.
	*
	* @param nmDiscountProjMasters the nm discount proj masters
	*/
	public static void cacheResult(
		List<NmDiscountProjMaster> nmDiscountProjMasters) {
		getPersistence().cacheResult(nmDiscountProjMasters);
	}

	/**
	* Creates a new nm discount proj master with the primary key. Does not add the nm discount proj master to the database.
	*
	* @param projectionDetailsSid the primary key for the new nm discount proj master
	* @return the new nm discount proj master
	*/
	public static NmDiscountProjMaster create(int projectionDetailsSid) {
		return getPersistence().create(projectionDetailsSid);
	}

	/**
	* Removes the nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the nm discount proj master
	* @return the nm discount proj master that was removed
	* @throws NoSuchNmDiscountProjMasterException if a nm discount proj master with the primary key could not be found
	*/
	public static NmDiscountProjMaster remove(int projectionDetailsSid)
		throws com.stpl.app.exception.NoSuchNmDiscountProjMasterException {
		return getPersistence().remove(projectionDetailsSid);
	}

	public static NmDiscountProjMaster updateImpl(
		NmDiscountProjMaster nmDiscountProjMaster) {
		return getPersistence().updateImpl(nmDiscountProjMaster);
	}

	/**
	* Returns the nm discount proj master with the primary key or throws a {@link NoSuchNmDiscountProjMasterException} if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the nm discount proj master
	* @return the nm discount proj master
	* @throws NoSuchNmDiscountProjMasterException if a nm discount proj master with the primary key could not be found
	*/
	public static NmDiscountProjMaster findByPrimaryKey(
		int projectionDetailsSid)
		throws com.stpl.app.exception.NoSuchNmDiscountProjMasterException {
		return getPersistence().findByPrimaryKey(projectionDetailsSid);
	}

	/**
	* Returns the nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the nm discount proj master
	* @return the nm discount proj master, or <code>null</code> if a nm discount proj master with the primary key could not be found
	*/
	public static NmDiscountProjMaster fetchByPrimaryKey(
		int projectionDetailsSid) {
		return getPersistence().fetchByPrimaryKey(projectionDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, NmDiscountProjMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the nm discount proj masters.
	*
	* @return the nm discount proj masters
	*/
	public static List<NmDiscountProjMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm discount proj masters
	* @param end the upper bound of the range of nm discount proj masters (not inclusive)
	* @return the range of nm discount proj masters
	*/
	public static List<NmDiscountProjMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm discount proj masters
	* @param end the upper bound of the range of nm discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm discount proj masters
	*/
	public static List<NmDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<NmDiscountProjMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm discount proj masters
	* @param end the upper bound of the range of nm discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm discount proj masters
	*/
	public static List<NmDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<NmDiscountProjMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the nm discount proj masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of nm discount proj masters.
	*
	* @return the number of nm discount proj masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NmDiscountProjMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NmDiscountProjMasterPersistence, NmDiscountProjMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NmDiscountProjMasterPersistence.class);
}