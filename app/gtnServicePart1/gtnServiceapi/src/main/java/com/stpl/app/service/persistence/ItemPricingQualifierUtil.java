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

import com.stpl.app.model.ItemPricingQualifier;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the item pricing qualifier service. This utility wraps {@link com.stpl.app.service.persistence.impl.ItemPricingQualifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemPricingQualifierPersistence
 * @see com.stpl.app.service.persistence.impl.ItemPricingQualifierPersistenceImpl
 * @generated
 */
@ProviderType
public class ItemPricingQualifierUtil {
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
	public static void clearCache(ItemPricingQualifier itemPricingQualifier) {
		getPersistence().clearCache(itemPricingQualifier);
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
	public static List<ItemPricingQualifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ItemPricingQualifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ItemPricingQualifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ItemPricingQualifier> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ItemPricingQualifier update(
		ItemPricingQualifier itemPricingQualifier) {
		return getPersistence().update(itemPricingQualifier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ItemPricingQualifier update(
		ItemPricingQualifier itemPricingQualifier, ServiceContext serviceContext) {
		return getPersistence().update(itemPricingQualifier, serviceContext);
	}

	/**
	* Returns the item pricing qualifier where itemPricingQualifierName = &#63; or throws a {@link NoSuchItemPricingQualifierException} if it could not be found.
	*
	* @param itemPricingQualifierName the item pricing qualifier name
	* @return the matching item pricing qualifier
	* @throws NoSuchItemPricingQualifierException if a matching item pricing qualifier could not be found
	*/
	public static ItemPricingQualifier findByitemPricingCodeQualifierByName(
		java.lang.String itemPricingQualifierName)
		throws com.stpl.app.exception.NoSuchItemPricingQualifierException {
		return getPersistence()
				   .findByitemPricingCodeQualifierByName(itemPricingQualifierName);
	}

	/**
	* Returns the item pricing qualifier where itemPricingQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param itemPricingQualifierName the item pricing qualifier name
	* @return the matching item pricing qualifier, or <code>null</code> if a matching item pricing qualifier could not be found
	*/
	public static ItemPricingQualifier fetchByitemPricingCodeQualifierByName(
		java.lang.String itemPricingQualifierName) {
		return getPersistence()
				   .fetchByitemPricingCodeQualifierByName(itemPricingQualifierName);
	}

	/**
	* Returns the item pricing qualifier where itemPricingQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param itemPricingQualifierName the item pricing qualifier name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching item pricing qualifier, or <code>null</code> if a matching item pricing qualifier could not be found
	*/
	public static ItemPricingQualifier fetchByitemPricingCodeQualifierByName(
		java.lang.String itemPricingQualifierName, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByitemPricingCodeQualifierByName(itemPricingQualifierName,
			retrieveFromCache);
	}

	/**
	* Removes the item pricing qualifier where itemPricingQualifierName = &#63; from the database.
	*
	* @param itemPricingQualifierName the item pricing qualifier name
	* @return the item pricing qualifier that was removed
	*/
	public static ItemPricingQualifier removeByitemPricingCodeQualifierByName(
		java.lang.String itemPricingQualifierName)
		throws com.stpl.app.exception.NoSuchItemPricingQualifierException {
		return getPersistence()
				   .removeByitemPricingCodeQualifierByName(itemPricingQualifierName);
	}

	/**
	* Returns the number of item pricing qualifiers where itemPricingQualifierName = &#63;.
	*
	* @param itemPricingQualifierName the item pricing qualifier name
	* @return the number of matching item pricing qualifiers
	*/
	public static int countByitemPricingCodeQualifierByName(
		java.lang.String itemPricingQualifierName) {
		return getPersistence()
				   .countByitemPricingCodeQualifierByName(itemPricingQualifierName);
	}

	/**
	* Caches the item pricing qualifier in the entity cache if it is enabled.
	*
	* @param itemPricingQualifier the item pricing qualifier
	*/
	public static void cacheResult(ItemPricingQualifier itemPricingQualifier) {
		getPersistence().cacheResult(itemPricingQualifier);
	}

	/**
	* Caches the item pricing qualifiers in the entity cache if it is enabled.
	*
	* @param itemPricingQualifiers the item pricing qualifiers
	*/
	public static void cacheResult(
		List<ItemPricingQualifier> itemPricingQualifiers) {
		getPersistence().cacheResult(itemPricingQualifiers);
	}

	/**
	* Creates a new item pricing qualifier with the primary key. Does not add the item pricing qualifier to the database.
	*
	* @param itemPricingQualifierSid the primary key for the new item pricing qualifier
	* @return the new item pricing qualifier
	*/
	public static ItemPricingQualifier create(int itemPricingQualifierSid) {
		return getPersistence().create(itemPricingQualifierSid);
	}

	/**
	* Removes the item pricing qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricingQualifierSid the primary key of the item pricing qualifier
	* @return the item pricing qualifier that was removed
	* @throws NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
	*/
	public static ItemPricingQualifier remove(int itemPricingQualifierSid)
		throws com.stpl.app.exception.NoSuchItemPricingQualifierException {
		return getPersistence().remove(itemPricingQualifierSid);
	}

	public static ItemPricingQualifier updateImpl(
		ItemPricingQualifier itemPricingQualifier) {
		return getPersistence().updateImpl(itemPricingQualifier);
	}

	/**
	* Returns the item pricing qualifier with the primary key or throws a {@link NoSuchItemPricingQualifierException} if it could not be found.
	*
	* @param itemPricingQualifierSid the primary key of the item pricing qualifier
	* @return the item pricing qualifier
	* @throws NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
	*/
	public static ItemPricingQualifier findByPrimaryKey(
		int itemPricingQualifierSid)
		throws com.stpl.app.exception.NoSuchItemPricingQualifierException {
		return getPersistence().findByPrimaryKey(itemPricingQualifierSid);
	}

	/**
	* Returns the item pricing qualifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemPricingQualifierSid the primary key of the item pricing qualifier
	* @return the item pricing qualifier, or <code>null</code> if a item pricing qualifier with the primary key could not be found
	*/
	public static ItemPricingQualifier fetchByPrimaryKey(
		int itemPricingQualifierSid) {
		return getPersistence().fetchByPrimaryKey(itemPricingQualifierSid);
	}

	public static java.util.Map<java.io.Serializable, ItemPricingQualifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the item pricing qualifiers.
	*
	* @return the item pricing qualifiers
	*/
	public static List<ItemPricingQualifier> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the item pricing qualifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item pricing qualifiers
	* @param end the upper bound of the range of item pricing qualifiers (not inclusive)
	* @return the range of item pricing qualifiers
	*/
	public static List<ItemPricingQualifier> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the item pricing qualifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item pricing qualifiers
	* @param end the upper bound of the range of item pricing qualifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of item pricing qualifiers
	*/
	public static List<ItemPricingQualifier> findAll(int start, int end,
		OrderByComparator<ItemPricingQualifier> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item pricing qualifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item pricing qualifiers
	* @param end the upper bound of the range of item pricing qualifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of item pricing qualifiers
	*/
	public static List<ItemPricingQualifier> findAll(int start, int end,
		OrderByComparator<ItemPricingQualifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the item pricing qualifiers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of item pricing qualifiers.
	*
	* @return the number of item pricing qualifiers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ItemPricingQualifierPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ItemPricingQualifierPersistence, ItemPricingQualifierPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ItemPricingQualifierPersistence.class);
}