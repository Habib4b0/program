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

import com.stpl.app.exception.NoSuchItemPricingQualifierException;
import com.stpl.app.model.ItemPricingQualifier;

/**
 * The persistence interface for the item pricing qualifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ItemPricingQualifierPersistenceImpl
 * @see ItemPricingQualifierUtil
 * @generated
 */
@ProviderType
public interface ItemPricingQualifierPersistence extends BasePersistence<ItemPricingQualifier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItemPricingQualifierUtil} to access the item pricing qualifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the item pricing qualifier where itemPricingQualifierName = &#63; or throws a {@link NoSuchItemPricingQualifierException} if it could not be found.
	*
	* @param itemPricingQualifierName the item pricing qualifier name
	* @return the matching item pricing qualifier
	* @throws NoSuchItemPricingQualifierException if a matching item pricing qualifier could not be found
	*/
	public ItemPricingQualifier findByitemPricingCodeQualifierByName(
		java.lang.String itemPricingQualifierName)
		throws NoSuchItemPricingQualifierException;

	/**
	* Returns the item pricing qualifier where itemPricingQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param itemPricingQualifierName the item pricing qualifier name
	* @return the matching item pricing qualifier, or <code>null</code> if a matching item pricing qualifier could not be found
	*/
	public ItemPricingQualifier fetchByitemPricingCodeQualifierByName(
		java.lang.String itemPricingQualifierName);

	/**
	* Returns the item pricing qualifier where itemPricingQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param itemPricingQualifierName the item pricing qualifier name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching item pricing qualifier, or <code>null</code> if a matching item pricing qualifier could not be found
	*/
	public ItemPricingQualifier fetchByitemPricingCodeQualifierByName(
		java.lang.String itemPricingQualifierName, boolean retrieveFromCache);

	/**
	* Removes the item pricing qualifier where itemPricingQualifierName = &#63; from the database.
	*
	* @param itemPricingQualifierName the item pricing qualifier name
	* @return the item pricing qualifier that was removed
	*/
	public ItemPricingQualifier removeByitemPricingCodeQualifierByName(
		java.lang.String itemPricingQualifierName)
		throws NoSuchItemPricingQualifierException;

	/**
	* Returns the number of item pricing qualifiers where itemPricingQualifierName = &#63;.
	*
	* @param itemPricingQualifierName the item pricing qualifier name
	* @return the number of matching item pricing qualifiers
	*/
	public int countByitemPricingCodeQualifierByName(
		java.lang.String itemPricingQualifierName);

	/**
	* Caches the item pricing qualifier in the entity cache if it is enabled.
	*
	* @param itemPricingQualifier the item pricing qualifier
	*/
	public void cacheResult(ItemPricingQualifier itemPricingQualifier);

	/**
	* Caches the item pricing qualifiers in the entity cache if it is enabled.
	*
	* @param itemPricingQualifiers the item pricing qualifiers
	*/
	public void cacheResult(
		java.util.List<ItemPricingQualifier> itemPricingQualifiers);

	/**
	* Creates a new item pricing qualifier with the primary key. Does not add the item pricing qualifier to the database.
	*
	* @param itemPricingQualifierSid the primary key for the new item pricing qualifier
	* @return the new item pricing qualifier
	*/
	public ItemPricingQualifier create(int itemPricingQualifierSid);

	/**
	* Removes the item pricing qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricingQualifierSid the primary key of the item pricing qualifier
	* @return the item pricing qualifier that was removed
	* @throws NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
	*/
	public ItemPricingQualifier remove(int itemPricingQualifierSid)
		throws NoSuchItemPricingQualifierException;

	public ItemPricingQualifier updateImpl(
		ItemPricingQualifier itemPricingQualifier);

	/**
	* Returns the item pricing qualifier with the primary key or throws a {@link NoSuchItemPricingQualifierException} if it could not be found.
	*
	* @param itemPricingQualifierSid the primary key of the item pricing qualifier
	* @return the item pricing qualifier
	* @throws NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
	*/
	public ItemPricingQualifier findByPrimaryKey(int itemPricingQualifierSid)
		throws NoSuchItemPricingQualifierException;

	/**
	* Returns the item pricing qualifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemPricingQualifierSid the primary key of the item pricing qualifier
	* @return the item pricing qualifier, or <code>null</code> if a item pricing qualifier with the primary key could not be found
	*/
	public ItemPricingQualifier fetchByPrimaryKey(int itemPricingQualifierSid);

	@Override
	public java.util.Map<java.io.Serializable, ItemPricingQualifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the item pricing qualifiers.
	*
	* @return the item pricing qualifiers
	*/
	public java.util.List<ItemPricingQualifier> findAll();

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
	public java.util.List<ItemPricingQualifier> findAll(int start, int end);

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
	public java.util.List<ItemPricingQualifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricingQualifier> orderByComparator);

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
	public java.util.List<ItemPricingQualifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricingQualifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the item pricing qualifiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of item pricing qualifiers.
	*
	* @return the number of item pricing qualifiers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}