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

import com.stpl.app.model.IvldBestPrice;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld best price service. This utility wraps {@link com.stpl.app.service.persistence.impl.IvldBestPricePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldBestPricePersistence
 * @see com.stpl.app.service.persistence.impl.IvldBestPricePersistenceImpl
 * @generated
 */
@ProviderType
public class IvldBestPriceUtil {
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
	public static void clearCache(IvldBestPrice ivldBestPrice) {
		getPersistence().clearCache(ivldBestPrice);
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
	public static List<IvldBestPrice> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldBestPrice> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldBestPrice> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldBestPrice> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldBestPrice update(IvldBestPrice ivldBestPrice) {
		return getPersistence().update(ivldBestPrice);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldBestPrice update(IvldBestPrice ivldBestPrice,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldBestPrice, serviceContext);
	}

	/**
	* Caches the ivld best price in the entity cache if it is enabled.
	*
	* @param ivldBestPrice the ivld best price
	*/
	public static void cacheResult(IvldBestPrice ivldBestPrice) {
		getPersistence().cacheResult(ivldBestPrice);
	}

	/**
	* Caches the ivld best prices in the entity cache if it is enabled.
	*
	* @param ivldBestPrices the ivld best prices
	*/
	public static void cacheResult(List<IvldBestPrice> ivldBestPrices) {
		getPersistence().cacheResult(ivldBestPrices);
	}

	/**
	* Creates a new ivld best price with the primary key. Does not add the ivld best price to the database.
	*
	* @param ivldBestPriceSid the primary key for the new ivld best price
	* @return the new ivld best price
	*/
	public static IvldBestPrice create(int ivldBestPriceSid) {
		return getPersistence().create(ivldBestPriceSid);
	}

	/**
	* Removes the ivld best price with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldBestPriceSid the primary key of the ivld best price
	* @return the ivld best price that was removed
	* @throws NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
	*/
	public static IvldBestPrice remove(int ivldBestPriceSid)
		throws com.stpl.app.exception.NoSuchIvldBestPriceException {
		return getPersistence().remove(ivldBestPriceSid);
	}

	public static IvldBestPrice updateImpl(IvldBestPrice ivldBestPrice) {
		return getPersistence().updateImpl(ivldBestPrice);
	}

	/**
	* Returns the ivld best price with the primary key or throws a {@link NoSuchIvldBestPriceException} if it could not be found.
	*
	* @param ivldBestPriceSid the primary key of the ivld best price
	* @return the ivld best price
	* @throws NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
	*/
	public static IvldBestPrice findByPrimaryKey(int ivldBestPriceSid)
		throws com.stpl.app.exception.NoSuchIvldBestPriceException {
		return getPersistence().findByPrimaryKey(ivldBestPriceSid);
	}

	/**
	* Returns the ivld best price with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldBestPriceSid the primary key of the ivld best price
	* @return the ivld best price, or <code>null</code> if a ivld best price with the primary key could not be found
	*/
	public static IvldBestPrice fetchByPrimaryKey(int ivldBestPriceSid) {
		return getPersistence().fetchByPrimaryKey(ivldBestPriceSid);
	}

	public static java.util.Map<java.io.Serializable, IvldBestPrice> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld best prices.
	*
	* @return the ivld best prices
	*/
	public static List<IvldBestPrice> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld best prices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld best prices
	* @param end the upper bound of the range of ivld best prices (not inclusive)
	* @return the range of ivld best prices
	*/
	public static List<IvldBestPrice> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld best prices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld best prices
	* @param end the upper bound of the range of ivld best prices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld best prices
	*/
	public static List<IvldBestPrice> findAll(int start, int end,
		OrderByComparator<IvldBestPrice> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld best prices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld best prices
	* @param end the upper bound of the range of ivld best prices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld best prices
	*/
	public static List<IvldBestPrice> findAll(int start, int end,
		OrderByComparator<IvldBestPrice> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld best prices from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld best prices.
	*
	* @return the number of ivld best prices
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldBestPricePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldBestPricePersistence, IvldBestPricePersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldBestPricePersistence.class);
}