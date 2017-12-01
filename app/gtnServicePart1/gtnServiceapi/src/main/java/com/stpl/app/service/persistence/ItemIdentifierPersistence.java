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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.exception.NoSuchItemIdentifierException;
import com.stpl.app.model.ItemIdentifier;

import java.util.Date;

/**
 * The persistence interface for the item identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ItemIdentifierPersistenceImpl
 * @see ItemIdentifierUtil
 * @generated
 */
@ProviderType
public interface ItemIdentifierPersistence extends BasePersistence<ItemIdentifier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItemIdentifierUtil} to access the item identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @return the matching item identifiers
	*/
	public java.util.List<ItemIdentifier> findByItemIrtIdentifier(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate);

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
	public java.util.List<ItemIdentifier> findByItemIrtIdentifier(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate, int start, int end);

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
	public java.util.List<ItemIdentifier> findByItemIrtIdentifier(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator);

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
	public java.util.List<ItemIdentifier> findByItemIrtIdentifier(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator,
		boolean retrieveFromCache);

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
	public ItemIdentifier findByItemIrtIdentifier_First(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator)
		throws NoSuchItemIdentifierException;

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
	public ItemIdentifier fetchByItemIrtIdentifier_First(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator);

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
	public ItemIdentifier findByItemIrtIdentifier_Last(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator)
		throws NoSuchItemIdentifierException;

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
	public ItemIdentifier fetchByItemIrtIdentifier_Last(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator);

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
	public ItemIdentifier[] findByItemIrtIdentifier_PrevAndNext(
		int itemIdentifierSid, java.lang.String itemIdentifierValue,
		int itemQualifierSid, int identifierStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator)
		throws NoSuchItemIdentifierException;

	/**
	* Removes all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63; from the database.
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	*/
	public void removeByItemIrtIdentifier(
		java.lang.String itemIdentifierValue, int itemQualifierSid,
		int identifierStatus, Date startDate);

	/**
	* Returns the number of item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param itemIdentifierValue the item identifier value
	* @param itemQualifierSid the item qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @return the number of matching item identifiers
	*/
	public int countByItemIrtIdentifier(java.lang.String itemIdentifierValue,
		int itemQualifierSid, int identifierStatus, Date startDate);

	/**
	* Returns all the item identifiers where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @return the matching item identifiers
	*/
	public java.util.List<ItemIdentifier> findByItemIrtIdentifierDetails(
		int itemMasterSid);

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
	public java.util.List<ItemIdentifier> findByItemIrtIdentifierDetails(
		int itemMasterSid, int start, int end);

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
	public java.util.List<ItemIdentifier> findByItemIrtIdentifierDetails(
		int itemMasterSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator);

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
	public java.util.List<ItemIdentifier> findByItemIrtIdentifierDetails(
		int itemMasterSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item identifier in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item identifier
	* @throws NoSuchItemIdentifierException if a matching item identifier could not be found
	*/
	public ItemIdentifier findByItemIrtIdentifierDetails_First(
		int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator)
		throws NoSuchItemIdentifierException;

	/**
	* Returns the first item identifier in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item identifier, or <code>null</code> if a matching item identifier could not be found
	*/
	public ItemIdentifier fetchByItemIrtIdentifierDetails_First(
		int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator);

	/**
	* Returns the last item identifier in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item identifier
	* @throws NoSuchItemIdentifierException if a matching item identifier could not be found
	*/
	public ItemIdentifier findByItemIrtIdentifierDetails_Last(
		int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator)
		throws NoSuchItemIdentifierException;

	/**
	* Returns the last item identifier in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item identifier, or <code>null</code> if a matching item identifier could not be found
	*/
	public ItemIdentifier fetchByItemIrtIdentifierDetails_Last(
		int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator);

	/**
	* Returns the item identifiers before and after the current item identifier in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemIdentifierSid the primary key of the current item identifier
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item identifier
	* @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	*/
	public ItemIdentifier[] findByItemIrtIdentifierDetails_PrevAndNext(
		int itemIdentifierSid, int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator)
		throws NoSuchItemIdentifierException;

	/**
	* Removes all the item identifiers where itemMasterSid = &#63; from the database.
	*
	* @param itemMasterSid the item master sid
	*/
	public void removeByItemIrtIdentifierDetails(int itemMasterSid);

	/**
	* Returns the number of item identifiers where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @return the number of matching item identifiers
	*/
	public int countByItemIrtIdentifierDetails(int itemMasterSid);

	/**
	* Caches the item identifier in the entity cache if it is enabled.
	*
	* @param itemIdentifier the item identifier
	*/
	public void cacheResult(ItemIdentifier itemIdentifier);

	/**
	* Caches the item identifiers in the entity cache if it is enabled.
	*
	* @param itemIdentifiers the item identifiers
	*/
	public void cacheResult(java.util.List<ItemIdentifier> itemIdentifiers);

	/**
	* Creates a new item identifier with the primary key. Does not add the item identifier to the database.
	*
	* @param itemIdentifierSid the primary key for the new item identifier
	* @return the new item identifier
	*/
	public ItemIdentifier create(int itemIdentifierSid);

	/**
	* Removes the item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemIdentifierSid the primary key of the item identifier
	* @return the item identifier that was removed
	* @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	*/
	public ItemIdentifier remove(int itemIdentifierSid)
		throws NoSuchItemIdentifierException;

	public ItemIdentifier updateImpl(ItemIdentifier itemIdentifier);

	/**
	* Returns the item identifier with the primary key or throws a {@link NoSuchItemIdentifierException} if it could not be found.
	*
	* @param itemIdentifierSid the primary key of the item identifier
	* @return the item identifier
	* @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	*/
	public ItemIdentifier findByPrimaryKey(int itemIdentifierSid)
		throws NoSuchItemIdentifierException;

	/**
	* Returns the item identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemIdentifierSid the primary key of the item identifier
	* @return the item identifier, or <code>null</code> if a item identifier with the primary key could not be found
	*/
	public ItemIdentifier fetchByPrimaryKey(int itemIdentifierSid);

	@Override
	public java.util.Map<java.io.Serializable, ItemIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the item identifiers.
	*
	* @return the item identifiers
	*/
	public java.util.List<ItemIdentifier> findAll();

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
	public java.util.List<ItemIdentifier> findAll(int start, int end);

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
	public java.util.List<ItemIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator);

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
	public java.util.List<ItemIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemIdentifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the item identifiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of item identifiers.
	*
	* @return the number of item identifiers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}