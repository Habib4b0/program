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

import com.stpl.app.model.NmSalesProjectionMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the nm sales projection master service. This utility wraps {@link com.stpl.app.service.persistence.impl.NmSalesProjectionMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmSalesProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.impl.NmSalesProjectionMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class NmSalesProjectionMasterUtil {
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
		NmSalesProjectionMaster nmSalesProjectionMaster) {
		getPersistence().clearCache(nmSalesProjectionMaster);
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
	public static List<NmSalesProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NmSalesProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NmSalesProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NmSalesProjectionMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NmSalesProjectionMaster update(
		NmSalesProjectionMaster nmSalesProjectionMaster) {
		return getPersistence().update(nmSalesProjectionMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NmSalesProjectionMaster update(
		NmSalesProjectionMaster nmSalesProjectionMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(nmSalesProjectionMaster, serviceContext);
	}

	/**
	* Caches the nm sales projection master in the entity cache if it is enabled.
	*
	* @param nmSalesProjectionMaster the nm sales projection master
	*/
	public static void cacheResult(
		NmSalesProjectionMaster nmSalesProjectionMaster) {
		getPersistence().cacheResult(nmSalesProjectionMaster);
	}

	/**
	* Caches the nm sales projection masters in the entity cache if it is enabled.
	*
	* @param nmSalesProjectionMasters the nm sales projection masters
	*/
	public static void cacheResult(
		List<NmSalesProjectionMaster> nmSalesProjectionMasters) {
		getPersistence().cacheResult(nmSalesProjectionMasters);
	}

	/**
	* Creates a new nm sales projection master with the primary key. Does not add the nm sales projection master to the database.
	*
	* @param projectionDetailsSid the primary key for the new nm sales projection master
	* @return the new nm sales projection master
	*/
	public static NmSalesProjectionMaster create(int projectionDetailsSid) {
		return getPersistence().create(projectionDetailsSid);
	}

	/**
	* Removes the nm sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the nm sales projection master
	* @return the nm sales projection master that was removed
	* @throws NoSuchNmSalesProjectionMasterException if a nm sales projection master with the primary key could not be found
	*/
	public static NmSalesProjectionMaster remove(int projectionDetailsSid)
		throws com.stpl.app.exception.NoSuchNmSalesProjectionMasterException {
		return getPersistence().remove(projectionDetailsSid);
	}

	public static NmSalesProjectionMaster updateImpl(
		NmSalesProjectionMaster nmSalesProjectionMaster) {
		return getPersistence().updateImpl(nmSalesProjectionMaster);
	}

	/**
	* Returns the nm sales projection master with the primary key or throws a {@link NoSuchNmSalesProjectionMasterException} if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the nm sales projection master
	* @return the nm sales projection master
	* @throws NoSuchNmSalesProjectionMasterException if a nm sales projection master with the primary key could not be found
	*/
	public static NmSalesProjectionMaster findByPrimaryKey(
		int projectionDetailsSid)
		throws com.stpl.app.exception.NoSuchNmSalesProjectionMasterException {
		return getPersistence().findByPrimaryKey(projectionDetailsSid);
	}

	/**
	* Returns the nm sales projection master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the nm sales projection master
	* @return the nm sales projection master, or <code>null</code> if a nm sales projection master with the primary key could not be found
	*/
	public static NmSalesProjectionMaster fetchByPrimaryKey(
		int projectionDetailsSid) {
		return getPersistence().fetchByPrimaryKey(projectionDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, NmSalesProjectionMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the nm sales projection masters.
	*
	* @return the nm sales projection masters
	*/
	public static List<NmSalesProjectionMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the nm sales projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm sales projection masters
	* @param end the upper bound of the range of nm sales projection masters (not inclusive)
	* @return the range of nm sales projection masters
	*/
	public static List<NmSalesProjectionMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the nm sales projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm sales projection masters
	* @param end the upper bound of the range of nm sales projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm sales projection masters
	*/
	public static List<NmSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<NmSalesProjectionMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the nm sales projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm sales projection masters
	* @param end the upper bound of the range of nm sales projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm sales projection masters
	*/
	public static List<NmSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<NmSalesProjectionMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the nm sales projection masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of nm sales projection masters.
	*
	* @return the number of nm sales projection masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NmSalesProjectionMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NmSalesProjectionMasterPersistence, NmSalesProjectionMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NmSalesProjectionMasterPersistence.class);
}