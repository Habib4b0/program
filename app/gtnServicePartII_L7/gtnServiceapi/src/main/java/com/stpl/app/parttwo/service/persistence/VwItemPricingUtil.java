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

import com.stpl.app.parttwo.model.VwItemPricing;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw item pricing service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.VwItemPricingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemPricingPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.VwItemPricingPersistenceImpl
 * @generated
 */
@ProviderType
public class VwItemPricingUtil {
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
	public static void clearCache(VwItemPricing vwItemPricing) {
		getPersistence().clearCache(vwItemPricing);
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
	public static List<VwItemPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwItemPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwItemPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwItemPricing> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwItemPricing update(VwItemPricing vwItemPricing) {
		return getPersistence().update(vwItemPricing);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwItemPricing update(VwItemPricing vwItemPricing,
		ServiceContext serviceContext) {
		return getPersistence().update(vwItemPricing, serviceContext);
	}

	/**
	* Caches the vw item pricing in the entity cache if it is enabled.
	*
	* @param vwItemPricing the vw item pricing
	*/
	public static void cacheResult(VwItemPricing vwItemPricing) {
		getPersistence().cacheResult(vwItemPricing);
	}

	/**
	* Caches the vw item pricings in the entity cache if it is enabled.
	*
	* @param vwItemPricings the vw item pricings
	*/
	public static void cacheResult(List<VwItemPricing> vwItemPricings) {
		getPersistence().cacheResult(vwItemPricings);
	}

	/**
	* Creates a new vw item pricing with the primary key. Does not add the vw item pricing to the database.
	*
	* @param itemPricingSid the primary key for the new vw item pricing
	* @return the new vw item pricing
	*/
	public static VwItemPricing create(int itemPricingSid) {
		return getPersistence().create(itemPricingSid);
	}

	/**
	* Removes the vw item pricing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricingSid the primary key of the vw item pricing
	* @return the vw item pricing that was removed
	* @throws NoSuchVwItemPricingException if a vw item pricing with the primary key could not be found
	*/
	public static VwItemPricing remove(int itemPricingSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwItemPricingException {
		return getPersistence().remove(itemPricingSid);
	}

	public static VwItemPricing updateImpl(VwItemPricing vwItemPricing) {
		return getPersistence().updateImpl(vwItemPricing);
	}

	/**
	* Returns the vw item pricing with the primary key or throws a {@link NoSuchVwItemPricingException} if it could not be found.
	*
	* @param itemPricingSid the primary key of the vw item pricing
	* @return the vw item pricing
	* @throws NoSuchVwItemPricingException if a vw item pricing with the primary key could not be found
	*/
	public static VwItemPricing findByPrimaryKey(int itemPricingSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwItemPricingException {
		return getPersistence().findByPrimaryKey(itemPricingSid);
	}

	/**
	* Returns the vw item pricing with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemPricingSid the primary key of the vw item pricing
	* @return the vw item pricing, or <code>null</code> if a vw item pricing with the primary key could not be found
	*/
	public static VwItemPricing fetchByPrimaryKey(int itemPricingSid) {
		return getPersistence().fetchByPrimaryKey(itemPricingSid);
	}

	public static java.util.Map<java.io.Serializable, VwItemPricing> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw item pricings.
	*
	* @return the vw item pricings
	*/
	public static List<VwItemPricing> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item pricings
	* @param end the upper bound of the range of vw item pricings (not inclusive)
	* @return the range of vw item pricings
	*/
	public static List<VwItemPricing> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item pricings
	* @param end the upper bound of the range of vw item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw item pricings
	*/
	public static List<VwItemPricing> findAll(int start, int end,
		OrderByComparator<VwItemPricing> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item pricings
	* @param end the upper bound of the range of vw item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw item pricings
	*/
	public static List<VwItemPricing> findAll(int start, int end,
		OrderByComparator<VwItemPricing> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw item pricings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw item pricings.
	*
	* @return the number of vw item pricings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwItemPricingPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwItemPricingPersistence, VwItemPricingPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwItemPricingPersistence.class);
}