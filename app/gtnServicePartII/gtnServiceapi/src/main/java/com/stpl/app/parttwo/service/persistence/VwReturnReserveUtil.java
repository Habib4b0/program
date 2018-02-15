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

import com.stpl.app.parttwo.model.VwReturnReserve;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw return reserve service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.VwReturnReservePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwReturnReservePersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.VwReturnReservePersistenceImpl
 * @generated
 */
@ProviderType
public class VwReturnReserveUtil {
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
	public static void clearCache(VwReturnReserve vwReturnReserve) {
		getPersistence().clearCache(vwReturnReserve);
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
	public static List<VwReturnReserve> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwReturnReserve> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwReturnReserve> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwReturnReserve> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwReturnReserve update(VwReturnReserve vwReturnReserve) {
		return getPersistence().update(vwReturnReserve);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwReturnReserve update(VwReturnReserve vwReturnReserve,
		ServiceContext serviceContext) {
		return getPersistence().update(vwReturnReserve, serviceContext);
	}

	/**
	* Caches the vw return reserve in the entity cache if it is enabled.
	*
	* @param vwReturnReserve the vw return reserve
	*/
	public static void cacheResult(VwReturnReserve vwReturnReserve) {
		getPersistence().cacheResult(vwReturnReserve);
	}

	/**
	* Caches the vw return reserves in the entity cache if it is enabled.
	*
	* @param vwReturnReserves the vw return reserves
	*/
	public static void cacheResult(List<VwReturnReserve> vwReturnReserves) {
		getPersistence().cacheResult(vwReturnReserves);
	}

	/**
	* Creates a new vw return reserve with the primary key. Does not add the vw return reserve to the database.
	*
	* @param returnReserveSid the primary key for the new vw return reserve
	* @return the new vw return reserve
	*/
	public static VwReturnReserve create(int returnReserveSid) {
		return getPersistence().create(returnReserveSid);
	}

	/**
	* Removes the vw return reserve with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param returnReserveSid the primary key of the vw return reserve
	* @return the vw return reserve that was removed
	* @throws NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
	*/
	public static VwReturnReserve remove(int returnReserveSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwReturnReserveException {
		return getPersistence().remove(returnReserveSid);
	}

	public static VwReturnReserve updateImpl(VwReturnReserve vwReturnReserve) {
		return getPersistence().updateImpl(vwReturnReserve);
	}

	/**
	* Returns the vw return reserve with the primary key or throws a {@link NoSuchVwReturnReserveException} if it could not be found.
	*
	* @param returnReserveSid the primary key of the vw return reserve
	* @return the vw return reserve
	* @throws NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
	*/
	public static VwReturnReserve findByPrimaryKey(int returnReserveSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwReturnReserveException {
		return getPersistence().findByPrimaryKey(returnReserveSid);
	}

	/**
	* Returns the vw return reserve with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param returnReserveSid the primary key of the vw return reserve
	* @return the vw return reserve, or <code>null</code> if a vw return reserve with the primary key could not be found
	*/
	public static VwReturnReserve fetchByPrimaryKey(int returnReserveSid) {
		return getPersistence().fetchByPrimaryKey(returnReserveSid);
	}

	public static java.util.Map<java.io.Serializable, VwReturnReserve> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw return reserves.
	*
	* @return the vw return reserves
	*/
	public static List<VwReturnReserve> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw return reserves.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw return reserves
	* @param end the upper bound of the range of vw return reserves (not inclusive)
	* @return the range of vw return reserves
	*/
	public static List<VwReturnReserve> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw return reserves.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw return reserves
	* @param end the upper bound of the range of vw return reserves (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw return reserves
	*/
	public static List<VwReturnReserve> findAll(int start, int end,
		OrderByComparator<VwReturnReserve> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw return reserves.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw return reserves
	* @param end the upper bound of the range of vw return reserves (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw return reserves
	*/
	public static List<VwReturnReserve> findAll(int start, int end,
		OrderByComparator<VwReturnReserve> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw return reserves from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw return reserves.
	*
	* @return the number of vw return reserves
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwReturnReservePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwReturnReservePersistence, VwReturnReservePersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwReturnReservePersistence.class);
}