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

import com.stpl.app.model.ChActualSales;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ch actual sales service. This utility wraps {@link com.stpl.app.service.persistence.impl.ChActualSalesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChActualSalesPersistence
 * @see com.stpl.app.service.persistence.impl.ChActualSalesPersistenceImpl
 * @generated
 */
@ProviderType
public class ChActualSalesUtil {
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
	public static void clearCache(ChActualSales chActualSales) {
		getPersistence().clearCache(chActualSales);
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
	public static List<ChActualSales> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChActualSales> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChActualSales> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ChActualSales> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ChActualSales update(ChActualSales chActualSales) {
		return getPersistence().update(chActualSales);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ChActualSales update(ChActualSales chActualSales,
		ServiceContext serviceContext) {
		return getPersistence().update(chActualSales, serviceContext);
	}

	/**
	* Caches the ch actual sales in the entity cache if it is enabled.
	*
	* @param chActualSales the ch actual sales
	*/
	public static void cacheResult(ChActualSales chActualSales) {
		getPersistence().cacheResult(chActualSales);
	}

	/**
	* Caches the ch actual saleses in the entity cache if it is enabled.
	*
	* @param chActualSaleses the ch actual saleses
	*/
	public static void cacheResult(List<ChActualSales> chActualSaleses) {
		getPersistence().cacheResult(chActualSaleses);
	}

	/**
	* Creates a new ch actual sales with the primary key. Does not add the ch actual sales to the database.
	*
	* @param chActualSalesPK the primary key for the new ch actual sales
	* @return the new ch actual sales
	*/
	public static ChActualSales create(ChActualSalesPK chActualSalesPK) {
		return getPersistence().create(chActualSalesPK);
	}

	/**
	* Removes the ch actual sales with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chActualSalesPK the primary key of the ch actual sales
	* @return the ch actual sales that was removed
	* @throws NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
	*/
	public static ChActualSales remove(ChActualSalesPK chActualSalesPK)
		throws com.stpl.app.exception.NoSuchChActualSalesException {
		return getPersistence().remove(chActualSalesPK);
	}

	public static ChActualSales updateImpl(ChActualSales chActualSales) {
		return getPersistence().updateImpl(chActualSales);
	}

	/**
	* Returns the ch actual sales with the primary key or throws a {@link NoSuchChActualSalesException} if it could not be found.
	*
	* @param chActualSalesPK the primary key of the ch actual sales
	* @return the ch actual sales
	* @throws NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
	*/
	public static ChActualSales findByPrimaryKey(
		ChActualSalesPK chActualSalesPK)
		throws com.stpl.app.exception.NoSuchChActualSalesException {
		return getPersistence().findByPrimaryKey(chActualSalesPK);
	}

	/**
	* Returns the ch actual sales with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chActualSalesPK the primary key of the ch actual sales
	* @return the ch actual sales, or <code>null</code> if a ch actual sales with the primary key could not be found
	*/
	public static ChActualSales fetchByPrimaryKey(
		ChActualSalesPK chActualSalesPK) {
		return getPersistence().fetchByPrimaryKey(chActualSalesPK);
	}

	public static java.util.Map<java.io.Serializable, ChActualSales> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ch actual saleses.
	*
	* @return the ch actual saleses
	*/
	public static List<ChActualSales> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ch actual saleses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch actual saleses
	* @param end the upper bound of the range of ch actual saleses (not inclusive)
	* @return the range of ch actual saleses
	*/
	public static List<ChActualSales> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ch actual saleses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch actual saleses
	* @param end the upper bound of the range of ch actual saleses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ch actual saleses
	*/
	public static List<ChActualSales> findAll(int start, int end,
		OrderByComparator<ChActualSales> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ch actual saleses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch actual saleses
	* @param end the upper bound of the range of ch actual saleses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ch actual saleses
	*/
	public static List<ChActualSales> findAll(int start, int end,
		OrderByComparator<ChActualSales> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ch actual saleses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ch actual saleses.
	*
	* @return the number of ch actual saleses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ChActualSalesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ChActualSalesPersistence, ChActualSalesPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ChActualSalesPersistence.class);
}