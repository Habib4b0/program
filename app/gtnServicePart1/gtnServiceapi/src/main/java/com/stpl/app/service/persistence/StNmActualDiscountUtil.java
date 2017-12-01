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

import com.stpl.app.model.StNmActualDiscount;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st nm actual discount service. This utility wraps {@link com.stpl.app.service.persistence.impl.StNmActualDiscountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmActualDiscountPersistence
 * @see com.stpl.app.service.persistence.impl.StNmActualDiscountPersistenceImpl
 * @generated
 */
@ProviderType
public class StNmActualDiscountUtil {
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
	public static void clearCache(StNmActualDiscount stNmActualDiscount) {
		getPersistence().clearCache(stNmActualDiscount);
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
	public static List<StNmActualDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StNmActualDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StNmActualDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StNmActualDiscount> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StNmActualDiscount update(
		StNmActualDiscount stNmActualDiscount) {
		return getPersistence().update(stNmActualDiscount);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StNmActualDiscount update(
		StNmActualDiscount stNmActualDiscount, ServiceContext serviceContext) {
		return getPersistence().update(stNmActualDiscount, serviceContext);
	}

	/**
	* Caches the st nm actual discount in the entity cache if it is enabled.
	*
	* @param stNmActualDiscount the st nm actual discount
	*/
	public static void cacheResult(StNmActualDiscount stNmActualDiscount) {
		getPersistence().cacheResult(stNmActualDiscount);
	}

	/**
	* Caches the st nm actual discounts in the entity cache if it is enabled.
	*
	* @param stNmActualDiscounts the st nm actual discounts
	*/
	public static void cacheResult(List<StNmActualDiscount> stNmActualDiscounts) {
		getPersistence().cacheResult(stNmActualDiscounts);
	}

	/**
	* Creates a new st nm actual discount with the primary key. Does not add the st nm actual discount to the database.
	*
	* @param stNmActualDiscountPK the primary key for the new st nm actual discount
	* @return the new st nm actual discount
	*/
	public static StNmActualDiscount create(
		StNmActualDiscountPK stNmActualDiscountPK) {
		return getPersistence().create(stNmActualDiscountPK);
	}

	/**
	* Removes the st nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmActualDiscountPK the primary key of the st nm actual discount
	* @return the st nm actual discount that was removed
	* @throws NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
	*/
	public static StNmActualDiscount remove(
		StNmActualDiscountPK stNmActualDiscountPK)
		throws com.stpl.app.exception.NoSuchStNmActualDiscountException {
		return getPersistence().remove(stNmActualDiscountPK);
	}

	public static StNmActualDiscount updateImpl(
		StNmActualDiscount stNmActualDiscount) {
		return getPersistence().updateImpl(stNmActualDiscount);
	}

	/**
	* Returns the st nm actual discount with the primary key or throws a {@link NoSuchStNmActualDiscountException} if it could not be found.
	*
	* @param stNmActualDiscountPK the primary key of the st nm actual discount
	* @return the st nm actual discount
	* @throws NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
	*/
	public static StNmActualDiscount findByPrimaryKey(
		StNmActualDiscountPK stNmActualDiscountPK)
		throws com.stpl.app.exception.NoSuchStNmActualDiscountException {
		return getPersistence().findByPrimaryKey(stNmActualDiscountPK);
	}

	/**
	* Returns the st nm actual discount with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmActualDiscountPK the primary key of the st nm actual discount
	* @return the st nm actual discount, or <code>null</code> if a st nm actual discount with the primary key could not be found
	*/
	public static StNmActualDiscount fetchByPrimaryKey(
		StNmActualDiscountPK stNmActualDiscountPK) {
		return getPersistence().fetchByPrimaryKey(stNmActualDiscountPK);
	}

	public static java.util.Map<java.io.Serializable, StNmActualDiscount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st nm actual discounts.
	*
	* @return the st nm actual discounts
	*/
	public static List<StNmActualDiscount> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual discounts
	* @param end the upper bound of the range of st nm actual discounts (not inclusive)
	* @return the range of st nm actual discounts
	*/
	public static List<StNmActualDiscount> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual discounts
	* @param end the upper bound of the range of st nm actual discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm actual discounts
	*/
	public static List<StNmActualDiscount> findAll(int start, int end,
		OrderByComparator<StNmActualDiscount> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual discounts
	* @param end the upper bound of the range of st nm actual discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm actual discounts
	*/
	public static List<StNmActualDiscount> findAll(int start, int end,
		OrderByComparator<StNmActualDiscount> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st nm actual discounts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st nm actual discounts.
	*
	* @return the number of st nm actual discounts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StNmActualDiscountPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StNmActualDiscountPersistence, StNmActualDiscountPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StNmActualDiscountPersistence.class);
}