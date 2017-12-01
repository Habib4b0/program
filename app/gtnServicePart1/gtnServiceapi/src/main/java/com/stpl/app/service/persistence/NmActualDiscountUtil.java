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

import com.stpl.app.model.NmActualDiscount;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the nm actual discount service. This utility wraps {@link com.stpl.app.service.persistence.impl.NmActualDiscountPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmActualDiscountPersistence
 * @see com.stpl.app.service.persistence.impl.NmActualDiscountPersistenceImpl
 * @generated
 */
@ProviderType
public class NmActualDiscountUtil {
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
	public static void clearCache(NmActualDiscount nmActualDiscount) {
		getPersistence().clearCache(nmActualDiscount);
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
	public static List<NmActualDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NmActualDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NmActualDiscount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NmActualDiscount> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NmActualDiscount update(NmActualDiscount nmActualDiscount) {
		return getPersistence().update(nmActualDiscount);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NmActualDiscount update(NmActualDiscount nmActualDiscount,
		ServiceContext serviceContext) {
		return getPersistence().update(nmActualDiscount, serviceContext);
	}

	/**
	* Caches the nm actual discount in the entity cache if it is enabled.
	*
	* @param nmActualDiscount the nm actual discount
	*/
	public static void cacheResult(NmActualDiscount nmActualDiscount) {
		getPersistence().cacheResult(nmActualDiscount);
	}

	/**
	* Caches the nm actual discounts in the entity cache if it is enabled.
	*
	* @param nmActualDiscounts the nm actual discounts
	*/
	public static void cacheResult(List<NmActualDiscount> nmActualDiscounts) {
		getPersistence().cacheResult(nmActualDiscounts);
	}

	/**
	* Creates a new nm actual discount with the primary key. Does not add the nm actual discount to the database.
	*
	* @param nmActualDiscountPK the primary key for the new nm actual discount
	* @return the new nm actual discount
	*/
	public static NmActualDiscount create(NmActualDiscountPK nmActualDiscountPK) {
		return getPersistence().create(nmActualDiscountPK);
	}

	/**
	* Removes the nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmActualDiscountPK the primary key of the nm actual discount
	* @return the nm actual discount that was removed
	* @throws NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
	*/
	public static NmActualDiscount remove(NmActualDiscountPK nmActualDiscountPK)
		throws com.stpl.app.exception.NoSuchNmActualDiscountException {
		return getPersistence().remove(nmActualDiscountPK);
	}

	public static NmActualDiscount updateImpl(NmActualDiscount nmActualDiscount) {
		return getPersistence().updateImpl(nmActualDiscount);
	}

	/**
	* Returns the nm actual discount with the primary key or throws a {@link NoSuchNmActualDiscountException} if it could not be found.
	*
	* @param nmActualDiscountPK the primary key of the nm actual discount
	* @return the nm actual discount
	* @throws NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
	*/
	public static NmActualDiscount findByPrimaryKey(
		NmActualDiscountPK nmActualDiscountPK)
		throws com.stpl.app.exception.NoSuchNmActualDiscountException {
		return getPersistence().findByPrimaryKey(nmActualDiscountPK);
	}

	/**
	* Returns the nm actual discount with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nmActualDiscountPK the primary key of the nm actual discount
	* @return the nm actual discount, or <code>null</code> if a nm actual discount with the primary key could not be found
	*/
	public static NmActualDiscount fetchByPrimaryKey(
		NmActualDiscountPK nmActualDiscountPK) {
		return getPersistence().fetchByPrimaryKey(nmActualDiscountPK);
	}

	public static java.util.Map<java.io.Serializable, NmActualDiscount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the nm actual discounts.
	*
	* @return the nm actual discounts
	*/
	public static List<NmActualDiscount> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm actual discounts
	* @param end the upper bound of the range of nm actual discounts (not inclusive)
	* @return the range of nm actual discounts
	*/
	public static List<NmActualDiscount> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm actual discounts
	* @param end the upper bound of the range of nm actual discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm actual discounts
	*/
	public static List<NmActualDiscount> findAll(int start, int end,
		OrderByComparator<NmActualDiscount> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm actual discounts
	* @param end the upper bound of the range of nm actual discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm actual discounts
	*/
	public static List<NmActualDiscount> findAll(int start, int end,
		OrderByComparator<NmActualDiscount> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the nm actual discounts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of nm actual discounts.
	*
	* @return the number of nm actual discounts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NmActualDiscountPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NmActualDiscountPersistence, NmActualDiscountPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NmActualDiscountPersistence.class);
}