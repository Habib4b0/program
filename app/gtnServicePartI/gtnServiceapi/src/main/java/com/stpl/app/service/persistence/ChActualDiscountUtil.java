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

import com.stpl.app.model.ChActualDiscount;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ch actual discount service. This utility wraps {@link com.stpl.app.service.persistence.impl.ChActualDiscountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChActualDiscountPersistence
 * @see com.stpl.app.service.persistence.impl.ChActualDiscountPersistenceImpl
 * @generated
 */
@ProviderType
public class ChActualDiscountUtil {
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
	public static void clearCache(ChActualDiscount chActualDiscount) {
		getPersistence().clearCache(chActualDiscount);
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
	public static List<ChActualDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChActualDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChActualDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ChActualDiscount> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ChActualDiscount update(ChActualDiscount chActualDiscount) {
		return getPersistence().update(chActualDiscount);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ChActualDiscount update(ChActualDiscount chActualDiscount,
		ServiceContext serviceContext) {
		return getPersistence().update(chActualDiscount, serviceContext);
	}

	/**
	* Caches the ch actual discount in the entity cache if it is enabled.
	*
	* @param chActualDiscount the ch actual discount
	*/
	public static void cacheResult(ChActualDiscount chActualDiscount) {
		getPersistence().cacheResult(chActualDiscount);
	}

	/**
	* Caches the ch actual discounts in the entity cache if it is enabled.
	*
	* @param chActualDiscounts the ch actual discounts
	*/
	public static void cacheResult(List<ChActualDiscount> chActualDiscounts) {
		getPersistence().cacheResult(chActualDiscounts);
	}

	/**
	* Creates a new ch actual discount with the primary key. Does not add the ch actual discount to the database.
	*
	* @param chActualDiscountPK the primary key for the new ch actual discount
	* @return the new ch actual discount
	*/
	public static ChActualDiscount create(ChActualDiscountPK chActualDiscountPK) {
		return getPersistence().create(chActualDiscountPK);
	}

	/**
	* Removes the ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chActualDiscountPK the primary key of the ch actual discount
	* @return the ch actual discount that was removed
	* @throws NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
	*/
	public static ChActualDiscount remove(ChActualDiscountPK chActualDiscountPK)
		throws com.stpl.app.exception.NoSuchChActualDiscountException {
		return getPersistence().remove(chActualDiscountPK);
	}

	public static ChActualDiscount updateImpl(ChActualDiscount chActualDiscount) {
		return getPersistence().updateImpl(chActualDiscount);
	}

	/**
	* Returns the ch actual discount with the primary key or throws a {@link NoSuchChActualDiscountException} if it could not be found.
	*
	* @param chActualDiscountPK the primary key of the ch actual discount
	* @return the ch actual discount
	* @throws NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
	*/
	public static ChActualDiscount findByPrimaryKey(
		ChActualDiscountPK chActualDiscountPK)
		throws com.stpl.app.exception.NoSuchChActualDiscountException {
		return getPersistence().findByPrimaryKey(chActualDiscountPK);
	}

	/**
	* Returns the ch actual discount with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chActualDiscountPK the primary key of the ch actual discount
	* @return the ch actual discount, or <code>null</code> if a ch actual discount with the primary key could not be found
	*/
	public static ChActualDiscount fetchByPrimaryKey(
		ChActualDiscountPK chActualDiscountPK) {
		return getPersistence().fetchByPrimaryKey(chActualDiscountPK);
	}

	public static java.util.Map<java.io.Serializable, ChActualDiscount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ch actual discounts.
	*
	* @return the ch actual discounts
	*/
	public static List<ChActualDiscount> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ch actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch actual discounts
	* @param end the upper bound of the range of ch actual discounts (not inclusive)
	* @return the range of ch actual discounts
	*/
	public static List<ChActualDiscount> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ch actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch actual discounts
	* @param end the upper bound of the range of ch actual discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ch actual discounts
	*/
	public static List<ChActualDiscount> findAll(int start, int end,
		OrderByComparator<ChActualDiscount> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ch actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch actual discounts
	* @param end the upper bound of the range of ch actual discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ch actual discounts
	*/
	public static List<ChActualDiscount> findAll(int start, int end,
		OrderByComparator<ChActualDiscount> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ch actual discounts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ch actual discounts.
	*
	* @return the number of ch actual discounts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ChActualDiscountPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ChActualDiscountPersistence, ChActualDiscountPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ChActualDiscountPersistence.class);
}