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

import com.stpl.app.model.ItemQualifier;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the item qualifier service. This utility wraps {@link com.stpl.app.service.persistence.impl.ItemQualifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemQualifierPersistence
 * @see com.stpl.app.service.persistence.impl.ItemQualifierPersistenceImpl
 * @generated
 */
@ProviderType
public class ItemQualifierUtil {
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
	public static void clearCache(ItemQualifier itemQualifier) {
		getPersistence().clearCache(itemQualifier);
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
	public static List<ItemQualifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ItemQualifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ItemQualifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ItemQualifier> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ItemQualifier update(ItemQualifier itemQualifier) {
		return getPersistence().update(itemQualifier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ItemQualifier update(ItemQualifier itemQualifier,
		ServiceContext serviceContext) {
		return getPersistence().update(itemQualifier, serviceContext);
	}

	/**
	* Returns all the item qualifiers where itemQualifierValue = &#63;.
	*
	* @param itemQualifierValue the item qualifier value
	* @return the matching item qualifiers
	*/
	public static List<ItemQualifier> findByItemIrtQualifierName(
		java.lang.String itemQualifierValue) {
		return getPersistence().findByItemIrtQualifierName(itemQualifierValue);
	}

	/**
	* Returns a range of all the item qualifiers where itemQualifierValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemQualifierValue the item qualifier value
	* @param start the lower bound of the range of item qualifiers
	* @param end the upper bound of the range of item qualifiers (not inclusive)
	* @return the range of matching item qualifiers
	*/
	public static List<ItemQualifier> findByItemIrtQualifierName(
		java.lang.String itemQualifierValue, int start, int end) {
		return getPersistence()
				   .findByItemIrtQualifierName(itemQualifierValue, start, end);
	}

	/**
	* Returns an ordered range of all the item qualifiers where itemQualifierValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemQualifierValue the item qualifier value
	* @param start the lower bound of the range of item qualifiers
	* @param end the upper bound of the range of item qualifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item qualifiers
	*/
	public static List<ItemQualifier> findByItemIrtQualifierName(
		java.lang.String itemQualifierValue, int start, int end,
		OrderByComparator<ItemQualifier> orderByComparator) {
		return getPersistence()
				   .findByItemIrtQualifierName(itemQualifierValue, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the item qualifiers where itemQualifierValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemQualifierValue the item qualifier value
	* @param start the lower bound of the range of item qualifiers
	* @param end the upper bound of the range of item qualifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item qualifiers
	*/
	public static List<ItemQualifier> findByItemIrtQualifierName(
		java.lang.String itemQualifierValue, int start, int end,
		OrderByComparator<ItemQualifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemIrtQualifierName(itemQualifierValue, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first item qualifier in the ordered set where itemQualifierValue = &#63;.
	*
	* @param itemQualifierValue the item qualifier value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item qualifier
	* @throws NoSuchItemQualifierException if a matching item qualifier could not be found
	*/
	public static ItemQualifier findByItemIrtQualifierName_First(
		java.lang.String itemQualifierValue,
		OrderByComparator<ItemQualifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemQualifierException {
		return getPersistence()
				   .findByItemIrtQualifierName_First(itemQualifierValue,
			orderByComparator);
	}

	/**
	* Returns the first item qualifier in the ordered set where itemQualifierValue = &#63;.
	*
	* @param itemQualifierValue the item qualifier value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
	*/
	public static ItemQualifier fetchByItemIrtQualifierName_First(
		java.lang.String itemQualifierValue,
		OrderByComparator<ItemQualifier> orderByComparator) {
		return getPersistence()
				   .fetchByItemIrtQualifierName_First(itemQualifierValue,
			orderByComparator);
	}

	/**
	* Returns the last item qualifier in the ordered set where itemQualifierValue = &#63;.
	*
	* @param itemQualifierValue the item qualifier value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item qualifier
	* @throws NoSuchItemQualifierException if a matching item qualifier could not be found
	*/
	public static ItemQualifier findByItemIrtQualifierName_Last(
		java.lang.String itemQualifierValue,
		OrderByComparator<ItemQualifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemQualifierException {
		return getPersistence()
				   .findByItemIrtQualifierName_Last(itemQualifierValue,
			orderByComparator);
	}

	/**
	* Returns the last item qualifier in the ordered set where itemQualifierValue = &#63;.
	*
	* @param itemQualifierValue the item qualifier value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
	*/
	public static ItemQualifier fetchByItemIrtQualifierName_Last(
		java.lang.String itemQualifierValue,
		OrderByComparator<ItemQualifier> orderByComparator) {
		return getPersistence()
				   .fetchByItemIrtQualifierName_Last(itemQualifierValue,
			orderByComparator);
	}

	/**
	* Returns the item qualifiers before and after the current item qualifier in the ordered set where itemQualifierValue = &#63;.
	*
	* @param itemQualifierSid the primary key of the current item qualifier
	* @param itemQualifierValue the item qualifier value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item qualifier
	* @throws NoSuchItemQualifierException if a item qualifier with the primary key could not be found
	*/
	public static ItemQualifier[] findByItemIrtQualifierName_PrevAndNext(
		int itemQualifierSid, java.lang.String itemQualifierValue,
		OrderByComparator<ItemQualifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemQualifierException {
		return getPersistence()
				   .findByItemIrtQualifierName_PrevAndNext(itemQualifierSid,
			itemQualifierValue, orderByComparator);
	}

	/**
	* Removes all the item qualifiers where itemQualifierValue = &#63; from the database.
	*
	* @param itemQualifierValue the item qualifier value
	*/
	public static void removeByItemIrtQualifierName(
		java.lang.String itemQualifierValue) {
		getPersistence().removeByItemIrtQualifierName(itemQualifierValue);
	}

	/**
	* Returns the number of item qualifiers where itemQualifierValue = &#63;.
	*
	* @param itemQualifierValue the item qualifier value
	* @return the number of matching item qualifiers
	*/
	public static int countByItemIrtQualifierName(
		java.lang.String itemQualifierValue) {
		return getPersistence().countByItemIrtQualifierName(itemQualifierValue);
	}

	/**
	* Returns the item qualifier where itemQualifierName = &#63; or throws a {@link NoSuchItemQualifierException} if it could not be found.
	*
	* @param itemQualifierName the item qualifier name
	* @return the matching item qualifier
	* @throws NoSuchItemQualifierException if a matching item qualifier could not be found
	*/
	public static ItemQualifier findByItemIrtQualifierByName(
		java.lang.String itemQualifierName)
		throws com.stpl.app.exception.NoSuchItemQualifierException {
		return getPersistence().findByItemIrtQualifierByName(itemQualifierName);
	}

	/**
	* Returns the item qualifier where itemQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param itemQualifierName the item qualifier name
	* @return the matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
	*/
	public static ItemQualifier fetchByItemIrtQualifierByName(
		java.lang.String itemQualifierName) {
		return getPersistence().fetchByItemIrtQualifierByName(itemQualifierName);
	}

	/**
	* Returns the item qualifier where itemQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param itemQualifierName the item qualifier name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
	*/
	public static ItemQualifier fetchByItemIrtQualifierByName(
		java.lang.String itemQualifierName, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByItemIrtQualifierByName(itemQualifierName,
			retrieveFromCache);
	}

	/**
	* Removes the item qualifier where itemQualifierName = &#63; from the database.
	*
	* @param itemQualifierName the item qualifier name
	* @return the item qualifier that was removed
	*/
	public static ItemQualifier removeByItemIrtQualifierByName(
		java.lang.String itemQualifierName)
		throws com.stpl.app.exception.NoSuchItemQualifierException {
		return getPersistence().removeByItemIrtQualifierByName(itemQualifierName);
	}

	/**
	* Returns the number of item qualifiers where itemQualifierName = &#63;.
	*
	* @param itemQualifierName the item qualifier name
	* @return the number of matching item qualifiers
	*/
	public static int countByItemIrtQualifierByName(
		java.lang.String itemQualifierName) {
		return getPersistence().countByItemIrtQualifierByName(itemQualifierName);
	}

	/**
	* Caches the item qualifier in the entity cache if it is enabled.
	*
	* @param itemQualifier the item qualifier
	*/
	public static void cacheResult(ItemQualifier itemQualifier) {
		getPersistence().cacheResult(itemQualifier);
	}

	/**
	* Caches the item qualifiers in the entity cache if it is enabled.
	*
	* @param itemQualifiers the item qualifiers
	*/
	public static void cacheResult(List<ItemQualifier> itemQualifiers) {
		getPersistence().cacheResult(itemQualifiers);
	}

	/**
	* Creates a new item qualifier with the primary key. Does not add the item qualifier to the database.
	*
	* @param itemQualifierSid the primary key for the new item qualifier
	* @return the new item qualifier
	*/
	public static ItemQualifier create(int itemQualifierSid) {
		return getPersistence().create(itemQualifierSid);
	}

	/**
	* Removes the item qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemQualifierSid the primary key of the item qualifier
	* @return the item qualifier that was removed
	* @throws NoSuchItemQualifierException if a item qualifier with the primary key could not be found
	*/
	public static ItemQualifier remove(int itemQualifierSid)
		throws com.stpl.app.exception.NoSuchItemQualifierException {
		return getPersistence().remove(itemQualifierSid);
	}

	public static ItemQualifier updateImpl(ItemQualifier itemQualifier) {
		return getPersistence().updateImpl(itemQualifier);
	}

	/**
	* Returns the item qualifier with the primary key or throws a {@link NoSuchItemQualifierException} if it could not be found.
	*
	* @param itemQualifierSid the primary key of the item qualifier
	* @return the item qualifier
	* @throws NoSuchItemQualifierException if a item qualifier with the primary key could not be found
	*/
	public static ItemQualifier findByPrimaryKey(int itemQualifierSid)
		throws com.stpl.app.exception.NoSuchItemQualifierException {
		return getPersistence().findByPrimaryKey(itemQualifierSid);
	}

	/**
	* Returns the item qualifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemQualifierSid the primary key of the item qualifier
	* @return the item qualifier, or <code>null</code> if a item qualifier with the primary key could not be found
	*/
	public static ItemQualifier fetchByPrimaryKey(int itemQualifierSid) {
		return getPersistence().fetchByPrimaryKey(itemQualifierSid);
	}

	public static java.util.Map<java.io.Serializable, ItemQualifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the item qualifiers.
	*
	* @return the item qualifiers
	*/
	public static List<ItemQualifier> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the item qualifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item qualifiers
	* @param end the upper bound of the range of item qualifiers (not inclusive)
	* @return the range of item qualifiers
	*/
	public static List<ItemQualifier> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the item qualifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item qualifiers
	* @param end the upper bound of the range of item qualifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of item qualifiers
	*/
	public static List<ItemQualifier> findAll(int start, int end,
		OrderByComparator<ItemQualifier> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item qualifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item qualifiers
	* @param end the upper bound of the range of item qualifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of item qualifiers
	*/
	public static List<ItemQualifier> findAll(int start, int end,
		OrderByComparator<ItemQualifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the item qualifiers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of item qualifiers.
	*
	* @return the number of item qualifiers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ItemQualifierPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ItemQualifierPersistence, ItemQualifierPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ItemQualifierPersistence.class);
}