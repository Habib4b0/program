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

import com.stpl.app.parttwo.model.IvldItemPricing;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld item pricing service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.IvldItemPricingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemPricingPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldItemPricingPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldItemPricingUtil {
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
	public static void clearCache(IvldItemPricing ivldItemPricing) {
		getPersistence().clearCache(ivldItemPricing);
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
	public static List<IvldItemPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldItemPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldItemPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldItemPricing> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldItemPricing update(IvldItemPricing ivldItemPricing) {
		return getPersistence().update(ivldItemPricing);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldItemPricing update(IvldItemPricing ivldItemPricing,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldItemPricing, serviceContext);
	}

	/**
	* Caches the ivld item pricing in the entity cache if it is enabled.
	*
	* @param ivldItemPricing the ivld item pricing
	*/
	public static void cacheResult(IvldItemPricing ivldItemPricing) {
		getPersistence().cacheResult(ivldItemPricing);
	}

	/**
	* Caches the ivld item pricings in the entity cache if it is enabled.
	*
	* @param ivldItemPricings the ivld item pricings
	*/
	public static void cacheResult(List<IvldItemPricing> ivldItemPricings) {
		getPersistence().cacheResult(ivldItemPricings);
	}

	/**
	* Creates a new ivld item pricing with the primary key. Does not add the ivld item pricing to the database.
	*
	* @param ivldItemPricingSid the primary key for the new ivld item pricing
	* @return the new ivld item pricing
	*/
	public static IvldItemPricing create(int ivldItemPricingSid) {
		return getPersistence().create(ivldItemPricingSid);
	}

	/**
	* Removes the ivld item pricing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemPricingSid the primary key of the ivld item pricing
	* @return the ivld item pricing that was removed
	* @throws NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
	*/
	public static IvldItemPricing remove(int ivldItemPricingSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldItemPricingException {
		return getPersistence().remove(ivldItemPricingSid);
	}

	public static IvldItemPricing updateImpl(IvldItemPricing ivldItemPricing) {
		return getPersistence().updateImpl(ivldItemPricing);
	}

	/**
	* Returns the ivld item pricing with the primary key or throws a {@link NoSuchIvldItemPricingException} if it could not be found.
	*
	* @param ivldItemPricingSid the primary key of the ivld item pricing
	* @return the ivld item pricing
	* @throws NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
	*/
	public static IvldItemPricing findByPrimaryKey(int ivldItemPricingSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldItemPricingException {
		return getPersistence().findByPrimaryKey(ivldItemPricingSid);
	}

	/**
	* Returns the ivld item pricing with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldItemPricingSid the primary key of the ivld item pricing
	* @return the ivld item pricing, or <code>null</code> if a ivld item pricing with the primary key could not be found
	*/
	public static IvldItemPricing fetchByPrimaryKey(int ivldItemPricingSid) {
		return getPersistence().fetchByPrimaryKey(ivldItemPricingSid);
	}

	public static java.util.Map<java.io.Serializable, IvldItemPricing> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld item pricings.
	*
	* @return the ivld item pricings
	*/
	public static List<IvldItemPricing> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item pricings
	* @param end the upper bound of the range of ivld item pricings (not inclusive)
	* @return the range of ivld item pricings
	*/
	public static List<IvldItemPricing> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item pricings
	* @param end the upper bound of the range of ivld item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld item pricings
	*/
	public static List<IvldItemPricing> findAll(int start, int end,
		OrderByComparator<IvldItemPricing> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item pricings
	* @param end the upper bound of the range of ivld item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld item pricings
	*/
	public static List<IvldItemPricing> findAll(int start, int end,
		OrderByComparator<IvldItemPricing> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld item pricings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld item pricings.
	*
	* @return the number of ivld item pricings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldItemPricingPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldItemPricingPersistence, IvldItemPricingPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldItemPricingPersistence.class);
}