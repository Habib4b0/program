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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.parttwo.exception.NoSuchVwItemPricingException;
import com.stpl.app.parttwo.model.VwItemPricing;

/**
 * The persistence interface for the vw item pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.VwItemPricingPersistenceImpl
 * @see VwItemPricingUtil
 * @generated
 */
@ProviderType
public interface VwItemPricingPersistence extends BasePersistence<VwItemPricing> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwItemPricingUtil} to access the vw item pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw item pricing in the entity cache if it is enabled.
	*
	* @param vwItemPricing the vw item pricing
	*/
	public void cacheResult(VwItemPricing vwItemPricing);

	/**
	* Caches the vw item pricings in the entity cache if it is enabled.
	*
	* @param vwItemPricings the vw item pricings
	*/
	public void cacheResult(java.util.List<VwItemPricing> vwItemPricings);

	/**
	* Creates a new vw item pricing with the primary key. Does not add the vw item pricing to the database.
	*
	* @param itemPricingSid the primary key for the new vw item pricing
	* @return the new vw item pricing
	*/
	public VwItemPricing create(int itemPricingSid);

	/**
	* Removes the vw item pricing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricingSid the primary key of the vw item pricing
	* @return the vw item pricing that was removed
	* @throws NoSuchVwItemPricingException if a vw item pricing with the primary key could not be found
	*/
	public VwItemPricing remove(int itemPricingSid)
		throws NoSuchVwItemPricingException;

	public VwItemPricing updateImpl(VwItemPricing vwItemPricing);

	/**
	* Returns the vw item pricing with the primary key or throws a {@link NoSuchVwItemPricingException} if it could not be found.
	*
	* @param itemPricingSid the primary key of the vw item pricing
	* @return the vw item pricing
	* @throws NoSuchVwItemPricingException if a vw item pricing with the primary key could not be found
	*/
	public VwItemPricing findByPrimaryKey(int itemPricingSid)
		throws NoSuchVwItemPricingException;

	/**
	* Returns the vw item pricing with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemPricingSid the primary key of the vw item pricing
	* @return the vw item pricing, or <code>null</code> if a vw item pricing with the primary key could not be found
	*/
	public VwItemPricing fetchByPrimaryKey(int itemPricingSid);

	@Override
	public java.util.Map<java.io.Serializable, VwItemPricing> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw item pricings.
	*
	* @return the vw item pricings
	*/
	public java.util.List<VwItemPricing> findAll();

	/**
	* Returns a range of all the vw item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item pricings
	* @param end the upper bound of the range of vw item pricings (not inclusive)
	* @return the range of vw item pricings
	*/
	public java.util.List<VwItemPricing> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vw item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item pricings
	* @param end the upper bound of the range of vw item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw item pricings
	*/
	public java.util.List<VwItemPricing> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwItemPricing> orderByComparator);

	/**
	* Returns an ordered range of all the vw item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item pricings
	* @param end the upper bound of the range of vw item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw item pricings
	*/
	public java.util.List<VwItemPricing> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwItemPricing> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw item pricings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw item pricings.
	*
	* @return the number of vw item pricings
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}