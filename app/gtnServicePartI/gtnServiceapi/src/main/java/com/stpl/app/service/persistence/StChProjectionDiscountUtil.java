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

import com.stpl.app.model.StChProjectionDiscount;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st ch projection discount service. This utility wraps {@link com.stpl.app.service.persistence.impl.StChProjectionDiscountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChProjectionDiscountPersistence
 * @see com.stpl.app.service.persistence.impl.StChProjectionDiscountPersistenceImpl
 * @generated
 */
@ProviderType
public class StChProjectionDiscountUtil {
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
	public static void clearCache(StChProjectionDiscount stChProjectionDiscount) {
		getPersistence().clearCache(stChProjectionDiscount);
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
	public static List<StChProjectionDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StChProjectionDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StChProjectionDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StChProjectionDiscount> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StChProjectionDiscount update(
		StChProjectionDiscount stChProjectionDiscount) {
		return getPersistence().update(stChProjectionDiscount);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StChProjectionDiscount update(
		StChProjectionDiscount stChProjectionDiscount,
		ServiceContext serviceContext) {
		return getPersistence().update(stChProjectionDiscount, serviceContext);
	}

	/**
	* Caches the st ch projection discount in the entity cache if it is enabled.
	*
	* @param stChProjectionDiscount the st ch projection discount
	*/
	public static void cacheResult(
		StChProjectionDiscount stChProjectionDiscount) {
		getPersistence().cacheResult(stChProjectionDiscount);
	}

	/**
	* Caches the st ch projection discounts in the entity cache if it is enabled.
	*
	* @param stChProjectionDiscounts the st ch projection discounts
	*/
	public static void cacheResult(
		List<StChProjectionDiscount> stChProjectionDiscounts) {
		getPersistence().cacheResult(stChProjectionDiscounts);
	}

	/**
	* Creates a new st ch projection discount with the primary key. Does not add the st ch projection discount to the database.
	*
	* @param stChProjectionDiscountPK the primary key for the new st ch projection discount
	* @return the new st ch projection discount
	*/
	public static StChProjectionDiscount create(
		StChProjectionDiscountPK stChProjectionDiscountPK) {
		return getPersistence().create(stChProjectionDiscountPK);
	}

	/**
	* Removes the st ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stChProjectionDiscountPK the primary key of the st ch projection discount
	* @return the st ch projection discount that was removed
	* @throws NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
	*/
	public static StChProjectionDiscount remove(
		StChProjectionDiscountPK stChProjectionDiscountPK)
		throws com.stpl.app.exception.NoSuchStChProjectionDiscountException {
		return getPersistence().remove(stChProjectionDiscountPK);
	}

	public static StChProjectionDiscount updateImpl(
		StChProjectionDiscount stChProjectionDiscount) {
		return getPersistence().updateImpl(stChProjectionDiscount);
	}

	/**
	* Returns the st ch projection discount with the primary key or throws a {@link NoSuchStChProjectionDiscountException} if it could not be found.
	*
	* @param stChProjectionDiscountPK the primary key of the st ch projection discount
	* @return the st ch projection discount
	* @throws NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
	*/
	public static StChProjectionDiscount findByPrimaryKey(
		StChProjectionDiscountPK stChProjectionDiscountPK)
		throws com.stpl.app.exception.NoSuchStChProjectionDiscountException {
		return getPersistence().findByPrimaryKey(stChProjectionDiscountPK);
	}

	/**
	* Returns the st ch projection discount with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stChProjectionDiscountPK the primary key of the st ch projection discount
	* @return the st ch projection discount, or <code>null</code> if a st ch projection discount with the primary key could not be found
	*/
	public static StChProjectionDiscount fetchByPrimaryKey(
		StChProjectionDiscountPK stChProjectionDiscountPK) {
		return getPersistence().fetchByPrimaryKey(stChProjectionDiscountPK);
	}

	public static java.util.Map<java.io.Serializable, StChProjectionDiscount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st ch projection discounts.
	*
	* @return the st ch projection discounts
	*/
	public static List<StChProjectionDiscount> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st ch projection discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch projection discounts
	* @param end the upper bound of the range of st ch projection discounts (not inclusive)
	* @return the range of st ch projection discounts
	*/
	public static List<StChProjectionDiscount> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st ch projection discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch projection discounts
	* @param end the upper bound of the range of st ch projection discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st ch projection discounts
	*/
	public static List<StChProjectionDiscount> findAll(int start, int end,
		OrderByComparator<StChProjectionDiscount> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st ch projection discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch projection discounts
	* @param end the upper bound of the range of st ch projection discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st ch projection discounts
	*/
	public static List<StChProjectionDiscount> findAll(int start, int end,
		OrderByComparator<StChProjectionDiscount> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st ch projection discounts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st ch projection discounts.
	*
	* @return the number of st ch projection discounts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StChProjectionDiscountPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StChProjectionDiscountPersistence, StChProjectionDiscountPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StChProjectionDiscountPersistence.class);
}