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

import com.stpl.app.model.IvldForecastSales;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld forecast sales service. This utility wraps {@link com.stpl.app.service.persistence.impl.IvldForecastSalesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldForecastSalesPersistence
 * @see com.stpl.app.service.persistence.impl.IvldForecastSalesPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldForecastSalesUtil {
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
	public static void clearCache(IvldForecastSales ivldForecastSales) {
		getPersistence().clearCache(ivldForecastSales);
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
	public static List<IvldForecastSales> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldForecastSales> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldForecastSales> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldForecastSales> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldForecastSales update(IvldForecastSales ivldForecastSales) {
		return getPersistence().update(ivldForecastSales);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldForecastSales update(
		IvldForecastSales ivldForecastSales, ServiceContext serviceContext) {
		return getPersistence().update(ivldForecastSales, serviceContext);
	}

	/**
	* Caches the ivld forecast sales in the entity cache if it is enabled.
	*
	* @param ivldForecastSales the ivld forecast sales
	*/
	public static void cacheResult(IvldForecastSales ivldForecastSales) {
		getPersistence().cacheResult(ivldForecastSales);
	}

	/**
	* Caches the ivld forecast saleses in the entity cache if it is enabled.
	*
	* @param ivldForecastSaleses the ivld forecast saleses
	*/
	public static void cacheResult(List<IvldForecastSales> ivldForecastSaleses) {
		getPersistence().cacheResult(ivldForecastSaleses);
	}

	/**
	* Creates a new ivld forecast sales with the primary key. Does not add the ivld forecast sales to the database.
	*
	* @param ivldForecastSalesSid the primary key for the new ivld forecast sales
	* @return the new ivld forecast sales
	*/
	public static IvldForecastSales create(int ivldForecastSalesSid) {
		return getPersistence().create(ivldForecastSalesSid);
	}

	/**
	* Removes the ivld forecast sales with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldForecastSalesSid the primary key of the ivld forecast sales
	* @return the ivld forecast sales that was removed
	* @throws NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
	*/
	public static IvldForecastSales remove(int ivldForecastSalesSid)
		throws com.stpl.app.exception.NoSuchIvldForecastSalesException {
		return getPersistence().remove(ivldForecastSalesSid);
	}

	public static IvldForecastSales updateImpl(
		IvldForecastSales ivldForecastSales) {
		return getPersistence().updateImpl(ivldForecastSales);
	}

	/**
	* Returns the ivld forecast sales with the primary key or throws a {@link NoSuchIvldForecastSalesException} if it could not be found.
	*
	* @param ivldForecastSalesSid the primary key of the ivld forecast sales
	* @return the ivld forecast sales
	* @throws NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
	*/
	public static IvldForecastSales findByPrimaryKey(int ivldForecastSalesSid)
		throws com.stpl.app.exception.NoSuchIvldForecastSalesException {
		return getPersistence().findByPrimaryKey(ivldForecastSalesSid);
	}

	/**
	* Returns the ivld forecast sales with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldForecastSalesSid the primary key of the ivld forecast sales
	* @return the ivld forecast sales, or <code>null</code> if a ivld forecast sales with the primary key could not be found
	*/
	public static IvldForecastSales fetchByPrimaryKey(int ivldForecastSalesSid) {
		return getPersistence().fetchByPrimaryKey(ivldForecastSalesSid);
	}

	public static java.util.Map<java.io.Serializable, IvldForecastSales> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld forecast saleses.
	*
	* @return the ivld forecast saleses
	*/
	public static List<IvldForecastSales> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld forecast saleses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld forecast saleses
	* @param end the upper bound of the range of ivld forecast saleses (not inclusive)
	* @return the range of ivld forecast saleses
	*/
	public static List<IvldForecastSales> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld forecast saleses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld forecast saleses
	* @param end the upper bound of the range of ivld forecast saleses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld forecast saleses
	*/
	public static List<IvldForecastSales> findAll(int start, int end,
		OrderByComparator<IvldForecastSales> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld forecast saleses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld forecast saleses
	* @param end the upper bound of the range of ivld forecast saleses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld forecast saleses
	*/
	public static List<IvldForecastSales> findAll(int start, int end,
		OrderByComparator<IvldForecastSales> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld forecast saleses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld forecast saleses.
	*
	* @return the number of ivld forecast saleses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldForecastSalesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldForecastSalesPersistence, IvldForecastSalesPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldForecastSalesPersistence.class);
}