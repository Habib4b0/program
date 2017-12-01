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

import com.stpl.app.exception.NoSuchItemQualifierException;
import com.stpl.app.model.ItemQualifier;

/**
 * The persistence interface for the item qualifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ItemQualifierPersistenceImpl
 * @see ItemQualifierUtil
 * @generated
 */
@ProviderType
public interface ItemQualifierPersistence extends BasePersistence<ItemQualifier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItemQualifierUtil} to access the item qualifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the item qualifiers where itemQualifierValue = &#63;.
	*
	* @param itemQualifierValue the item qualifier value
	* @return the matching item qualifiers
	*/
	public java.util.List<ItemQualifier> findByItemIrtQualifierName(
		java.lang.String itemQualifierValue);

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
	public java.util.List<ItemQualifier> findByItemIrtQualifierName(
		java.lang.String itemQualifierValue, int start, int end);

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
	public java.util.List<ItemQualifier> findByItemIrtQualifierName(
		java.lang.String itemQualifierValue, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemQualifier> orderByComparator);

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
	public java.util.List<ItemQualifier> findByItemIrtQualifierName(
		java.lang.String itemQualifierValue, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemQualifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item qualifier in the ordered set where itemQualifierValue = &#63;.
	*
	* @param itemQualifierValue the item qualifier value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item qualifier
	* @throws NoSuchItemQualifierException if a matching item qualifier could not be found
	*/
	public ItemQualifier findByItemIrtQualifierName_First(
		java.lang.String itemQualifierValue,
		com.liferay.portal.kernel.util.OrderByComparator<ItemQualifier> orderByComparator)
		throws NoSuchItemQualifierException;

	/**
	* Returns the first item qualifier in the ordered set where itemQualifierValue = &#63;.
	*
	* @param itemQualifierValue the item qualifier value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
	*/
	public ItemQualifier fetchByItemIrtQualifierName_First(
		java.lang.String itemQualifierValue,
		com.liferay.portal.kernel.util.OrderByComparator<ItemQualifier> orderByComparator);

	/**
	* Returns the last item qualifier in the ordered set where itemQualifierValue = &#63;.
	*
	* @param itemQualifierValue the item qualifier value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item qualifier
	* @throws NoSuchItemQualifierException if a matching item qualifier could not be found
	*/
	public ItemQualifier findByItemIrtQualifierName_Last(
		java.lang.String itemQualifierValue,
		com.liferay.portal.kernel.util.OrderByComparator<ItemQualifier> orderByComparator)
		throws NoSuchItemQualifierException;

	/**
	* Returns the last item qualifier in the ordered set where itemQualifierValue = &#63;.
	*
	* @param itemQualifierValue the item qualifier value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
	*/
	public ItemQualifier fetchByItemIrtQualifierName_Last(
		java.lang.String itemQualifierValue,
		com.liferay.portal.kernel.util.OrderByComparator<ItemQualifier> orderByComparator);

	/**
	* Returns the item qualifiers before and after the current item qualifier in the ordered set where itemQualifierValue = &#63;.
	*
	* @param itemQualifierSid the primary key of the current item qualifier
	* @param itemQualifierValue the item qualifier value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item qualifier
	* @throws NoSuchItemQualifierException if a item qualifier with the primary key could not be found
	*/
	public ItemQualifier[] findByItemIrtQualifierName_PrevAndNext(
		int itemQualifierSid, java.lang.String itemQualifierValue,
		com.liferay.portal.kernel.util.OrderByComparator<ItemQualifier> orderByComparator)
		throws NoSuchItemQualifierException;

	/**
	* Removes all the item qualifiers where itemQualifierValue = &#63; from the database.
	*
	* @param itemQualifierValue the item qualifier value
	*/
	public void removeByItemIrtQualifierName(
		java.lang.String itemQualifierValue);

	/**
	* Returns the number of item qualifiers where itemQualifierValue = &#63;.
	*
	* @param itemQualifierValue the item qualifier value
	* @return the number of matching item qualifiers
	*/
	public int countByItemIrtQualifierName(java.lang.String itemQualifierValue);

	/**
	* Returns the item qualifier where itemQualifierName = &#63; or throws a {@link NoSuchItemQualifierException} if it could not be found.
	*
	* @param itemQualifierName the item qualifier name
	* @return the matching item qualifier
	* @throws NoSuchItemQualifierException if a matching item qualifier could not be found
	*/
	public ItemQualifier findByItemIrtQualifierByName(
		java.lang.String itemQualifierName) throws NoSuchItemQualifierException;

	/**
	* Returns the item qualifier where itemQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param itemQualifierName the item qualifier name
	* @return the matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
	*/
	public ItemQualifier fetchByItemIrtQualifierByName(
		java.lang.String itemQualifierName);

	/**
	* Returns the item qualifier where itemQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param itemQualifierName the item qualifier name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
	*/
	public ItemQualifier fetchByItemIrtQualifierByName(
		java.lang.String itemQualifierName, boolean retrieveFromCache);

	/**
	* Removes the item qualifier where itemQualifierName = &#63; from the database.
	*
	* @param itemQualifierName the item qualifier name
	* @return the item qualifier that was removed
	*/
	public ItemQualifier removeByItemIrtQualifierByName(
		java.lang.String itemQualifierName) throws NoSuchItemQualifierException;

	/**
	* Returns the number of item qualifiers where itemQualifierName = &#63;.
	*
	* @param itemQualifierName the item qualifier name
	* @return the number of matching item qualifiers
	*/
	public int countByItemIrtQualifierByName(java.lang.String itemQualifierName);

	/**
	* Caches the item qualifier in the entity cache if it is enabled.
	*
	* @param itemQualifier the item qualifier
	*/
	public void cacheResult(ItemQualifier itemQualifier);

	/**
	* Caches the item qualifiers in the entity cache if it is enabled.
	*
	* @param itemQualifiers the item qualifiers
	*/
	public void cacheResult(java.util.List<ItemQualifier> itemQualifiers);

	/**
	* Creates a new item qualifier with the primary key. Does not add the item qualifier to the database.
	*
	* @param itemQualifierSid the primary key for the new item qualifier
	* @return the new item qualifier
	*/
	public ItemQualifier create(int itemQualifierSid);

	/**
	* Removes the item qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemQualifierSid the primary key of the item qualifier
	* @return the item qualifier that was removed
	* @throws NoSuchItemQualifierException if a item qualifier with the primary key could not be found
	*/
	public ItemQualifier remove(int itemQualifierSid)
		throws NoSuchItemQualifierException;

	public ItemQualifier updateImpl(ItemQualifier itemQualifier);

	/**
	* Returns the item qualifier with the primary key or throws a {@link NoSuchItemQualifierException} if it could not be found.
	*
	* @param itemQualifierSid the primary key of the item qualifier
	* @return the item qualifier
	* @throws NoSuchItemQualifierException if a item qualifier with the primary key could not be found
	*/
	public ItemQualifier findByPrimaryKey(int itemQualifierSid)
		throws NoSuchItemQualifierException;

	/**
	* Returns the item qualifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemQualifierSid the primary key of the item qualifier
	* @return the item qualifier, or <code>null</code> if a item qualifier with the primary key could not be found
	*/
	public ItemQualifier fetchByPrimaryKey(int itemQualifierSid);

	@Override
	public java.util.Map<java.io.Serializable, ItemQualifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the item qualifiers.
	*
	* @return the item qualifiers
	*/
	public java.util.List<ItemQualifier> findAll();

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
	public java.util.List<ItemQualifier> findAll(int start, int end);

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
	public java.util.List<ItemQualifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemQualifier> orderByComparator);

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
	public java.util.List<ItemQualifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemQualifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the item qualifiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of item qualifiers.
	*
	* @return the number of item qualifiers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}