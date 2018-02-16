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

import com.stpl.app.exception.NoSuchNmActualDiscountException;
import com.stpl.app.model.NmActualDiscount;

/**
 * The persistence interface for the nm actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.NmActualDiscountPersistenceImpl
 * @see NmActualDiscountUtil
 * @generated
 */
@ProviderType
public interface NmActualDiscountPersistence extends BasePersistence<NmActualDiscount> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NmActualDiscountUtil} to access the nm actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the nm actual discount in the entity cache if it is enabled.
	*
	* @param nmActualDiscount the nm actual discount
	*/
	public void cacheResult(NmActualDiscount nmActualDiscount);

	/**
	* Caches the nm actual discounts in the entity cache if it is enabled.
	*
	* @param nmActualDiscounts the nm actual discounts
	*/
	public void cacheResult(java.util.List<NmActualDiscount> nmActualDiscounts);

	/**
	* Creates a new nm actual discount with the primary key. Does not add the nm actual discount to the database.
	*
	* @param nmActualDiscountPK the primary key for the new nm actual discount
	* @return the new nm actual discount
	*/
	public NmActualDiscount create(NmActualDiscountPK nmActualDiscountPK);

	/**
	* Removes the nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmActualDiscountPK the primary key of the nm actual discount
	* @return the nm actual discount that was removed
	* @throws NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
	*/
	public NmActualDiscount remove(NmActualDiscountPK nmActualDiscountPK)
		throws NoSuchNmActualDiscountException;

	public NmActualDiscount updateImpl(NmActualDiscount nmActualDiscount);

	/**
	* Returns the nm actual discount with the primary key or throws a {@link NoSuchNmActualDiscountException} if it could not be found.
	*
	* @param nmActualDiscountPK the primary key of the nm actual discount
	* @return the nm actual discount
	* @throws NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
	*/
	public NmActualDiscount findByPrimaryKey(
		NmActualDiscountPK nmActualDiscountPK)
		throws NoSuchNmActualDiscountException;

	/**
	* Returns the nm actual discount with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nmActualDiscountPK the primary key of the nm actual discount
	* @return the nm actual discount, or <code>null</code> if a nm actual discount with the primary key could not be found
	*/
	public NmActualDiscount fetchByPrimaryKey(
		NmActualDiscountPK nmActualDiscountPK);

	@Override
	public java.util.Map<java.io.Serializable, NmActualDiscount> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the nm actual discounts.
	*
	* @return the nm actual discounts
	*/
	public java.util.List<NmActualDiscount> findAll();

	/**
	* Returns a range of all the nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm actual discounts
	* @param end the upper bound of the range of nm actual discounts (not inclusive)
	* @return the range of nm actual discounts
	*/
	public java.util.List<NmActualDiscount> findAll(int start, int end);

	/**
	* Returns an ordered range of all the nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm actual discounts
	* @param end the upper bound of the range of nm actual discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm actual discounts
	*/
	public java.util.List<NmActualDiscount> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NmActualDiscount> orderByComparator);

	/**
	* Returns an ordered range of all the nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm actual discounts
	* @param end the upper bound of the range of nm actual discounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm actual discounts
	*/
	public java.util.List<NmActualDiscount> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NmActualDiscount> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the nm actual discounts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of nm actual discounts.
	*
	* @return the number of nm actual discounts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}