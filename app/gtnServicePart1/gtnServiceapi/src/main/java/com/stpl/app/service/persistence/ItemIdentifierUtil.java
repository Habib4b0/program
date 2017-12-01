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

import com.stpl.app.model.ItemIdentifier;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the item identifier service. This utility wraps {@link com.stpl.app.service.persistence.impl.ItemIdentifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemIdentifierPersistence
 * @see com.stpl.app.service.persistence.impl.ItemIdentifierPersistenceImpl
 * @generated
 */
@ProviderType
public class ItemIdentifierUtil {
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
	public static void clearCache(ItemIdentifier itemIdentifier) {
		getPersistence().clearCache(itemIdentifier);
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
	public static List<ItemIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ItemIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ItemIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ItemIdentifier update(ItemIdentifier itemIdentifier) {
		return getPersistence().update(itemIdentifier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ItemIdentifier update(ItemIdentifier itemIdentifier,
		ServiceContext serviceContext) {
		return getPersistence().update(itemIdentifier, serviceContext);
	}

	/**
	* Returns all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @return the matching item identifiers
	*/
	public static List<ItemIdentifier> findByItemIrtIdentifier(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate) {
		return getPersistence()
				   .findByItemIrtIdentifier(itemIdentifierValue,
			itemQualifierSid, identifierStatus, startDate);
	}

	/**
	* Returns a range of all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param start the lower bound of the range of item identifiers
	* @param end the upper bound of the range of item identifiers (not inclusive)
	* @return the range of matching item identifiers
	*/
	public static List<ItemIdentifier> findByItemIrtIdentifier(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate, int start, int end) {
		return getPersistence()
				   .findByItemIrtIdentifier(itemIdentifierValue,
			itemQualifierSid, identifierStatus, startDate, start, end);
	}

	/**
	* Returns an ordered range of all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param start the lower bound of the range of item identifiers
	* @param end the upper bound of the range of item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item identifiers
	*/
	public static List<ItemIdentifier> findByItemIrtIdentifier(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate, int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator) {
		return getPersistence()
				   .findByItemIrtIdentifier(itemIdentifierValue,
			itemQualifierSid, identifierStatus, startDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param start the lower bound of the range of item identifiers
	* @param end the upper bound of the range of item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item identifiers
	*/
	public static List<ItemIdentifier> findByItemIrtIdentifier(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate, int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemIrtIdentifier(itemIdentifierValue,
			itemQualifierSid, identifierStatus, startDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item identifier
	* @throws NoSuchItemIdentifierException if a matching item identifier could not be found
	*/
	public static ItemIdentifier findByItemIrtIdentifier_First(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<ItemIdentifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemIdentifierException {
		return getPersistence()
				   .findByItemIrtIdentifier_First(itemIdentifierValue,
			itemQualifierSid, identifierStatus, startDate, orderByComparator);
	}

	/**
	* Returns the first item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item identifier, or <code>null</code> if a matching item identifier could not be found
	*/
	public static ItemIdentifier fetchByItemIrtIdentifier_First(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<ItemIdentifier> orderByComparator) {
		return getPersistence()
				   .fetchByItemIrtIdentifier_First(itemIdentifierValue,
			itemQualifierSid, identifierStatus, startDate, orderByComparator);
	}

	/**
	* Returns the last item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item identifier
	* @throws NoSuchItemIdentifierException if a matching item identifier could not be found
	*/
	public static ItemIdentifier findByItemIrtIdentifier_Last(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<ItemIdentifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemIdentifierException {
		return getPersistence()
				   .findByItemIrtIdentifier_Last(itemIdentifierValue,
			itemQualifierSid, identifierStatus, startDate, orderByComparator);
	}

	/**
	* Returns the last item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item identifier, or <code>null</code> if a matching item identifier could not be found
	*/
	public static ItemIdentifier fetchByItemIrtIdentifier_Last(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<ItemIdentifier> orderByComparator) {
		return getPersistence()
				   .fetchByItemIrtIdentifier_Last(itemIdentifierValue,
			itemQualifierSid, identifierStatus, startDate, orderByComparator);
	}

	/**
	* Returns the item identifiers before and after the current item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param itemIdentifierSid the primary key of the current item identifier
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item identifier
	* @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	*/
	public static ItemIdentifier[] findByItemIrtIdentifier_PrevAndNext(
		int itemIdentifierSid, java.lang.String itemIdentifierValue,
		int itemQualifierSid, int identifierStatus, Date startDate,
		OrderByComparator<ItemIdentifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemIdentifierException {
		return getPersistence()
				   .findByItemIrtIdentifier_PrevAndNext(itemIdentifierSid,
			itemIdentifierValue, itemQualifierSid, identifierStatus, startDate,
			orderByComparator);
	}

	/**
	* Removes all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63; from the database.
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	*/
	public static void removeByItemIrtIdentifier(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate) {
		getPersistence()
			.removeByItemIrtIdentifier(itemIdentifierValue, itemQualifierSid,
			identifierStatus, startDate);
	}

	/**
	* Returns the number of item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @return the number of matching item identifiers
	*/
	public static int countByItemIrtIdentifier(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate) {
		return getPersistence()
				   .countByItemIrtIdentifier(itemIdentifierValue,
			itemQualifierSid, identifierStatus, startDate);
	}

	/**
	* Returns all the item identifiers where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @return the matching item identifiers
	*/
	public static List<ItemIdentifier> findByItemIrtIdentifierDetails(
		int itemMasterSid) {
		return getPersistence().findByItemIrtIdentifierDetails(itemMasterSid);
	}

	/**
	* Returns a range of all the item identifiers where itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of item identifiers
	* @param end the upper bound of the range of item identifiers (not inclusive)
	* @return the range of matching item identifiers
	*/
	public static List<ItemIdentifier> findByItemIrtIdentifierDetails(
		int itemMasterSid, int start, int end) {
		return getPersistence()
				   .findByItemIrtIdentifierDetails(itemMasterSid, start, end);
	}

	/**
	* Returns an ordered range of all the item identifiers where itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of item identifiers
	* @param end the upper bound of the range of item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item identifiers
	*/
	public static List<ItemIdentifier> findByItemIrtIdentifierDetails(
		int itemMasterSid, int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator) {
		return getPersistence()
				   .findByItemIrtIdentifierDetails(itemMasterSid, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the item identifiers where itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of item identifiers
	* @param end the upper bound of the range of item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item identifiers
	*/
	public static List<ItemIdentifier> findByItemIrtIdentifierDetails(
		int itemMasterSid, int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemIrtIdentifierDetails(itemMasterSid, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first item identifier in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item identifier
	* @throws NoSuchItemIdentifierException if a matching item identifier could not be found
	*/
	public static ItemIdentifier findByItemIrtIdentifierDetails_First(
		int itemMasterSid, OrderByComparator<ItemIdentifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemIdentifierException {
		return getPersistence()
				   .findByItemIrtIdentifierDetails_First(itemMasterSid,
			orderByComparator);
	}

	/**
	* Returns the first item identifier in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item identifier, or <code>null</code> if a matching item identifier could not be found
	*/
	public static ItemIdentifier fetchByItemIrtIdentifierDetails_First(
		int itemMasterSid, OrderByComparator<ItemIdentifier> orderByComparator) {
		return getPersistence()
				   .fetchByItemIrtIdentifierDetails_First(itemMasterSid,
			orderByComparator);
	}

	/**
	* Returns the last item identifier in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item identifier
	* @throws NoSuchItemIdentifierException if a matching item identifier could not be found
	*/
	public static ItemIdentifier findByItemIrtIdentifierDetails_Last(
		int itemMasterSid, OrderByComparator<ItemIdentifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemIdentifierException {
		return getPersistence()
				   .findByItemIrtIdentifierDetails_Last(itemMasterSid,
			orderByComparator);
	}

	/**
	* Returns the last item identifier in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item identifier, or <code>null</code> if a matching item identifier could not be found
	*/
	public static ItemIdentifier fetchByItemIrtIdentifierDetails_Last(
		int itemMasterSid, OrderByComparator<ItemIdentifier> orderByComparator) {
		return getPersistence()
				   .fetchByItemIrtIdentifierDetails_Last(itemMasterSid,
			orderByComparator);
	}

	/**
	* Returns the item identifiers before and after the current item identifier in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemIdentifierSid the primary key of the current item identifier
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item identifier
	* @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	*/
	public static ItemIdentifier[] findByItemIrtIdentifierDetails_PrevAndNext(
		int itemIdentifierSid, int itemMasterSid,
		OrderByComparator<ItemIdentifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemIdentifierException {
		return getPersistence()
				   .findByItemIrtIdentifierDetails_PrevAndNext(itemIdentifierSid,
			itemMasterSid, orderByComparator);
	}

	/**
	* Removes all the item identifiers where itemMasterSid = &#63; from the database.
	*
	* @param itemMasterSid the item master sid
	*/
	public static void removeByItemIrtIdentifierDetails(int itemMasterSid) {
		getPersistence().removeByItemIrtIdentifierDetails(itemMasterSid);
	}

	/**
	* Returns the number of item identifiers where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @return the number of matching item identifiers
	*/
	public static int countByItemIrtIdentifierDetails(int itemMasterSid) {
		return getPersistence().countByItemIrtIdentifierDetails(itemMasterSid);
	}

	/**
	* Caches the item identifier in the entity cache if it is enabled.
	*
	* @param itemIdentifier the item identifier
	*/
	public static void cacheResult(ItemIdentifier itemIdentifier) {
		getPersistence().cacheResult(itemIdentifier);
	}

	/**
	* Caches the item identifiers in the entity cache if it is enabled.
	*
	* @param itemIdentifiers the item identifiers
	*/
	public static void cacheResult(List<ItemIdentifier> itemIdentifiers) {
		getPersistence().cacheResult(itemIdentifiers);
	}

	/**
	* Creates a new item identifier with the primary key. Does not add the item identifier to the database.
	*
	* @param itemIdentifierSid the primary key for the new item identifier
	* @return the new item identifier
	*/
	public static ItemIdentifier create(int itemIdentifierSid) {
		return getPersistence().create(itemIdentifierSid);
	}

	/**
	* Removes the item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemIdentifierSid the primary key of the item identifier
	* @return the item identifier that was removed
	* @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	*/
	public static ItemIdentifier remove(int itemIdentifierSid)
		throws com.stpl.app.exception.NoSuchItemIdentifierException {
		return getPersistence().remove(itemIdentifierSid);
	}

	public static ItemIdentifier updateImpl(ItemIdentifier itemIdentifier) {
		return getPersistence().updateImpl(itemIdentifier);
	}

	/**
	* Returns the item identifier with the primary key or throws a {@link NoSuchItemIdentifierException} if it could not be found.
	*
	* @param itemIdentifierSid the primary key of the item identifier
	* @return the item identifier
	* @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	*/
	public static ItemIdentifier findByPrimaryKey(int itemIdentifierSid)
		throws com.stpl.app.exception.NoSuchItemIdentifierException {
		return getPersistence().findByPrimaryKey(itemIdentifierSid);
	}

	/**
	* Returns the item identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemIdentifierSid the primary key of the item identifier
	* @return the item identifier, or <code>null</code> if a item identifier with the primary key could not be found
	*/
	public static ItemIdentifier fetchByPrimaryKey(int itemIdentifierSid) {
		return getPersistence().fetchByPrimaryKey(itemIdentifierSid);
	}

	public static java.util.Map<java.io.Serializable, ItemIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the item identifiers.
	*
	* @return the item identifiers
	*/
	public static List<ItemIdentifier> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item identifiers
	* @param end the upper bound of the range of item identifiers (not inclusive)
	* @return the range of item identifiers
	*/
	public static List<ItemIdentifier> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item identifiers
	* @param end the upper bound of the range of item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of item identifiers
	*/
	public static List<ItemIdentifier> findAll(int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item identifiers
	* @param end the upper bound of the range of item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of item identifiers
	*/
	public static List<ItemIdentifier> findAll(int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the item identifiers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of item identifiers.
	*
	* @return the number of item identifiers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ItemIdentifierPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ItemIdentifierPersistence, ItemIdentifierPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ItemIdentifierPersistence.class);
}