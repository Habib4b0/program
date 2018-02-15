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

import com.stpl.app.model.ChProjectionDiscount;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ch projection discount service. This utility wraps {@link com.stpl.app.service.persistence.impl.ChProjectionDiscountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChProjectionDiscountPersistence
 * @see com.stpl.app.service.persistence.impl.ChProjectionDiscountPersistenceImpl
 * @generated
 */
@ProviderType
public class ChProjectionDiscountUtil {
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
	public static void clearCache(ChProjectionDiscount chProjectionDiscount) {
		getPersistence().clearCache(chProjectionDiscount);
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
	public static List<ChProjectionDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChProjectionDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChProjectionDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ChProjectionDiscount> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ChProjectionDiscount update(
		ChProjectionDiscount chProjectionDiscount) {
		return getPersistence().update(chProjectionDiscount);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ChProjectionDiscount update(
		ChProjectionDiscount chProjectionDiscount, ServiceContext serviceContext) {
		return getPersistence().update(chProjectionDiscount, serviceContext);
	}

	/**
	* Caches the ch projection discount in the entity cache if it is enabled.
	*
	* @param chProjectionDiscount the ch projection discount
	*/
	public static void cacheResult(ChProjectionDiscount chProjectionDiscount) {
		getPersistence().cacheResult(chProjectionDiscount);
	}

	/**
	* Caches the ch projection discounts in the entity cache if it is enabled.
	*
	* @param chProjectionDiscounts the ch projection discounts
	*/
	public static void cacheResult(
		List<ChProjectionDiscount> chProjectionDiscounts) {
		getPersistence().cacheResult(chProjectionDiscounts);
	}

	/**
	* Creates a new ch projection discount with the primary key. Does not add the ch projection discount to the database.
	*
	* @param chProjectionDiscountPK the primary key for the new ch projection discount
	* @return the new ch projection discount
	*/
	public static ChProjectionDiscount create(
		ChProjectionDiscountPK chProjectionDiscountPK) {
		return getPersistence().create(chProjectionDiscountPK);
	}

	/**
	* Removes the ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chProjectionDiscountPK the primary key of the ch projection discount
	* @return the ch projection discount that was removed
	* @throws NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
	*/
	public static ChProjectionDiscount remove(
		ChProjectionDiscountPK chProjectionDiscountPK)
		throws com.stpl.app.exception.NoSuchChProjectionDiscountException {
		return getPersistence().remove(chProjectionDiscountPK);
	}

	public static ChProjectionDiscount updateImpl(
		ChProjectionDiscount chProjectionDiscount) {
		return getPersistence().updateImpl(chProjectionDiscount);
	}

	/**
	* Returns the ch projection discount with the primary key or throws a {@link NoSuchChProjectionDiscountException} if it could not be found.
	*
	* @param chProjectionDiscountPK the primary key of the ch projection discount
	* @return the ch projection discount
	* @throws NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
	*/
	public static ChProjectionDiscount findByPrimaryKey(
		ChProjectionDiscountPK chProjectionDiscountPK)
		throws com.stpl.app.exception.NoSuchChProjectionDiscountException {
		return getPersistence().findByPrimaryKey(chProjectionDiscountPK);
	}

	/**
	* Returns the ch projection discount with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chProjectionDiscountPK the primary key of the ch projection discount
	* @return the ch projection discount, or <code>null</code> if a ch projection discount with the primary key could not be found
	*/
	public static ChProjectionDiscount fetchByPrimaryKey(
		ChProjectionDiscountPK chProjectionDiscountPK) {
		return getPersistence().fetchByPrimaryKey(chProjectionDiscountPK);
	}

	public static java.util.Map<java.io.Serializable, ChProjectionDiscount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ch projection discounts.
	*
	* @return the ch projection discounts
	*/
	public static List<ChProjectionDiscount> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ch projection discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection discounts
	* @param end the upper bound of the range of ch projection discounts (not inclusive)
	* @return the range of ch projection discounts
	*/
	public static List<ChProjectionDiscount> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ch projection discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection discounts
	* @param end the upper bound of the range of ch projection discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ch projection discounts
	*/
	public static List<ChProjectionDiscount> findAll(int start, int end,
		OrderByComparator<ChProjectionDiscount> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ch projection discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection discounts
	* @param end the upper bound of the range of ch projection discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ch projection discounts
	*/
	public static List<ChProjectionDiscount> findAll(int start, int end,
		OrderByComparator<ChProjectionDiscount> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ch projection discounts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ch projection discounts.
	*
	* @return the number of ch projection discounts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ChProjectionDiscountPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ChProjectionDiscountPersistence, ChProjectionDiscountPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ChProjectionDiscountPersistence.class);
}