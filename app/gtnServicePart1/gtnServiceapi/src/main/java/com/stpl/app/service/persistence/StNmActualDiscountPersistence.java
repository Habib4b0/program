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

import com.stpl.app.exception.NoSuchStNmActualDiscountException;
import com.stpl.app.model.StNmActualDiscount;

/**
 * The persistence interface for the st nm actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StNmActualDiscountPersistenceImpl
 * @see StNmActualDiscountUtil
 * @generated
 */
@ProviderType
public interface StNmActualDiscountPersistence extends BasePersistence<StNmActualDiscount> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StNmActualDiscountUtil} to access the st nm actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st nm actual discount in the entity cache if it is enabled.
	*
	* @param stNmActualDiscount the st nm actual discount
	*/
	public void cacheResult(StNmActualDiscount stNmActualDiscount);

	/**
	* Caches the st nm actual discounts in the entity cache if it is enabled.
	*
	* @param stNmActualDiscounts the st nm actual discounts
	*/
	public void cacheResult(
		java.util.List<StNmActualDiscount> stNmActualDiscounts);

	/**
	* Creates a new st nm actual discount with the primary key. Does not add the st nm actual discount to the database.
	*
	* @param stNmActualDiscountPK the primary key for the new st nm actual discount
	* @return the new st nm actual discount
	*/
	public StNmActualDiscount create(StNmActualDiscountPK stNmActualDiscountPK);

	/**
	* Removes the st nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmActualDiscountPK the primary key of the st nm actual discount
	* @return the st nm actual discount that was removed
	* @throws NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
	*/
	public StNmActualDiscount remove(StNmActualDiscountPK stNmActualDiscountPK)
		throws NoSuchStNmActualDiscountException;

	public StNmActualDiscount updateImpl(StNmActualDiscount stNmActualDiscount);

	/**
	* Returns the st nm actual discount with the primary key or throws a {@link NoSuchStNmActualDiscountException} if it could not be found.
	*
	* @param stNmActualDiscountPK the primary key of the st nm actual discount
	* @return the st nm actual discount
	* @throws NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
	*/
	public StNmActualDiscount findByPrimaryKey(
		StNmActualDiscountPK stNmActualDiscountPK)
		throws NoSuchStNmActualDiscountException;

	/**
	* Returns the st nm actual discount with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmActualDiscountPK the primary key of the st nm actual discount
	* @return the st nm actual discount, or <code>null</code> if a st nm actual discount with the primary key could not be found
	*/
	public StNmActualDiscount fetchByPrimaryKey(
		StNmActualDiscountPK stNmActualDiscountPK);

	@Override
	public java.util.Map<java.io.Serializable, StNmActualDiscount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st nm actual discounts.
	*
	* @return the st nm actual discounts
	*/
	public java.util.List<StNmActualDiscount> findAll();

	/**
	* Returns a range of all the st nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual discounts
	* @param end the upper bound of the range of st nm actual discounts (not inclusive)
	* @return the range of st nm actual discounts
	*/
	public java.util.List<StNmActualDiscount> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual discounts
	* @param end the upper bound of the range of st nm actual discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm actual discounts
	*/
	public java.util.List<StNmActualDiscount> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmActualDiscount> orderByComparator);

	/**
	* Returns an ordered range of all the st nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual discounts
	* @param end the upper bound of the range of st nm actual discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm actual discounts
	*/
	public java.util.List<StNmActualDiscount> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmActualDiscount> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st nm actual discounts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st nm actual discounts.
	*
	* @return the number of st nm actual discounts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}