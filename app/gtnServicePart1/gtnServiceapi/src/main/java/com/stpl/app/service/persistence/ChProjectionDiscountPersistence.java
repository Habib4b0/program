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

import com.stpl.app.exception.NoSuchChProjectionDiscountException;
import com.stpl.app.model.ChProjectionDiscount;

/**
 * The persistence interface for the ch projection discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ChProjectionDiscountPersistenceImpl
 * @see ChProjectionDiscountUtil
 * @generated
 */
@ProviderType
public interface ChProjectionDiscountPersistence extends BasePersistence<ChProjectionDiscount> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChProjectionDiscountUtil} to access the ch projection discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ch projection discount in the entity cache if it is enabled.
	*
	* @param chProjectionDiscount the ch projection discount
	*/
	public void cacheResult(ChProjectionDiscount chProjectionDiscount);

	/**
	* Caches the ch projection discounts in the entity cache if it is enabled.
	*
	* @param chProjectionDiscounts the ch projection discounts
	*/
	public void cacheResult(
		java.util.List<ChProjectionDiscount> chProjectionDiscounts);

	/**
	* Creates a new ch projection discount with the primary key. Does not add the ch projection discount to the database.
	*
	* @param chProjectionDiscountPK the primary key for the new ch projection discount
	* @return the new ch projection discount
	*/
	public ChProjectionDiscount create(
		ChProjectionDiscountPK chProjectionDiscountPK);

	/**
	* Removes the ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chProjectionDiscountPK the primary key of the ch projection discount
	* @return the ch projection discount that was removed
	* @throws NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
	*/
	public ChProjectionDiscount remove(
		ChProjectionDiscountPK chProjectionDiscountPK)
		throws NoSuchChProjectionDiscountException;

	public ChProjectionDiscount updateImpl(
		ChProjectionDiscount chProjectionDiscount);

	/**
	* Returns the ch projection discount with the primary key or throws a {@link NoSuchChProjectionDiscountException} if it could not be found.
	*
	* @param chProjectionDiscountPK the primary key of the ch projection discount
	* @return the ch projection discount
	* @throws NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
	*/
	public ChProjectionDiscount findByPrimaryKey(
		ChProjectionDiscountPK chProjectionDiscountPK)
		throws NoSuchChProjectionDiscountException;

	/**
	* Returns the ch projection discount with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chProjectionDiscountPK the primary key of the ch projection discount
	* @return the ch projection discount, or <code>null</code> if a ch projection discount with the primary key could not be found
	*/
	public ChProjectionDiscount fetchByPrimaryKey(
		ChProjectionDiscountPK chProjectionDiscountPK);

	@Override
	public java.util.Map<java.io.Serializable, ChProjectionDiscount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ch projection discounts.
	*
	* @return the ch projection discounts
	*/
	public java.util.List<ChProjectionDiscount> findAll();

	/**
	* Returns a range of all the ch projection discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection discounts
	* @param end the upper bound of the range of ch projection discounts (not inclusive)
	* @return the range of ch projection discounts
	*/
	public java.util.List<ChProjectionDiscount> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ch projection discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection discounts
	* @param end the upper bound of the range of ch projection discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ch projection discounts
	*/
	public java.util.List<ChProjectionDiscount> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChProjectionDiscount> orderByComparator);

	/**
	* Returns an ordered range of all the ch projection discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection discounts
	* @param end the upper bound of the range of ch projection discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ch projection discounts
	*/
	public java.util.List<ChProjectionDiscount> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChProjectionDiscount> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ch projection discounts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ch projection discounts.
	*
	* @return the number of ch projection discounts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}