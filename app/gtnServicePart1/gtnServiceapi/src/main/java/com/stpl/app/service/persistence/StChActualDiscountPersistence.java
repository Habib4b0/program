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

import com.stpl.app.exception.NoSuchStChActualDiscountException;
import com.stpl.app.model.StChActualDiscount;

/**
 * The persistence interface for the st ch actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StChActualDiscountPersistenceImpl
 * @see StChActualDiscountUtil
 * @generated
 */
@ProviderType
public interface StChActualDiscountPersistence extends BasePersistence<StChActualDiscount> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StChActualDiscountUtil} to access the st ch actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st ch actual discount in the entity cache if it is enabled.
	*
	* @param stChActualDiscount the st ch actual discount
	*/
	public void cacheResult(StChActualDiscount stChActualDiscount);

	/**
	* Caches the st ch actual discounts in the entity cache if it is enabled.
	*
	* @param stChActualDiscounts the st ch actual discounts
	*/
	public void cacheResult(
		java.util.List<StChActualDiscount> stChActualDiscounts);

	/**
	* Creates a new st ch actual discount with the primary key. Does not add the st ch actual discount to the database.
	*
	* @param stChActualDiscountPK the primary key for the new st ch actual discount
	* @return the new st ch actual discount
	*/
	public StChActualDiscount create(StChActualDiscountPK stChActualDiscountPK);

	/**
	* Removes the st ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stChActualDiscountPK the primary key of the st ch actual discount
	* @return the st ch actual discount that was removed
	* @throws NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
	*/
	public StChActualDiscount remove(StChActualDiscountPK stChActualDiscountPK)
		throws NoSuchStChActualDiscountException;

	public StChActualDiscount updateImpl(StChActualDiscount stChActualDiscount);

	/**
	* Returns the st ch actual discount with the primary key or throws a {@link NoSuchStChActualDiscountException} if it could not be found.
	*
	* @param stChActualDiscountPK the primary key of the st ch actual discount
	* @return the st ch actual discount
	* @throws NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
	*/
	public StChActualDiscount findByPrimaryKey(
		StChActualDiscountPK stChActualDiscountPK)
		throws NoSuchStChActualDiscountException;

	/**
	* Returns the st ch actual discount with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stChActualDiscountPK the primary key of the st ch actual discount
	* @return the st ch actual discount, or <code>null</code> if a st ch actual discount with the primary key could not be found
	*/
	public StChActualDiscount fetchByPrimaryKey(
		StChActualDiscountPK stChActualDiscountPK);

	@Override
	public java.util.Map<java.io.Serializable, StChActualDiscount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st ch actual discounts.
	*
	* @return the st ch actual discounts
	*/
	public java.util.List<StChActualDiscount> findAll();

	/**
	* Returns a range of all the st ch actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch actual discounts
	* @param end the upper bound of the range of st ch actual discounts (not inclusive)
	* @return the range of st ch actual discounts
	*/
	public java.util.List<StChActualDiscount> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st ch actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch actual discounts
	* @param end the upper bound of the range of st ch actual discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st ch actual discounts
	*/
	public java.util.List<StChActualDiscount> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StChActualDiscount> orderByComparator);

	/**
	* Returns an ordered range of all the st ch actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch actual discounts
	* @param end the upper bound of the range of st ch actual discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st ch actual discounts
	*/
	public java.util.List<StChActualDiscount> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StChActualDiscount> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st ch actual discounts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st ch actual discounts.
	*
	* @return the number of st ch actual discounts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}