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

import com.stpl.app.parttwo.exception.NoSuchIvldItemPricingException;
import com.stpl.app.parttwo.model.IvldItemPricing;

/**
 * The persistence interface for the ivld item pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldItemPricingPersistenceImpl
 * @see IvldItemPricingUtil
 * @generated
 */
@ProviderType
public interface IvldItemPricingPersistence extends BasePersistence<IvldItemPricing> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldItemPricingUtil} to access the ivld item pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld item pricing in the entity cache if it is enabled.
	*
	* @param ivldItemPricing the ivld item pricing
	*/
	public void cacheResult(IvldItemPricing ivldItemPricing);

	/**
	* Caches the ivld item pricings in the entity cache if it is enabled.
	*
	* @param ivldItemPricings the ivld item pricings
	*/
	public void cacheResult(java.util.List<IvldItemPricing> ivldItemPricings);

	/**
	* Creates a new ivld item pricing with the primary key. Does not add the ivld item pricing to the database.
	*
	* @param ivldItemPricingSid the primary key for the new ivld item pricing
	* @return the new ivld item pricing
	*/
	public IvldItemPricing create(int ivldItemPricingSid);

	/**
	* Removes the ivld item pricing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemPricingSid the primary key of the ivld item pricing
	* @return the ivld item pricing that was removed
	* @throws NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
	*/
	public IvldItemPricing remove(int ivldItemPricingSid)
		throws NoSuchIvldItemPricingException;

	public IvldItemPricing updateImpl(IvldItemPricing ivldItemPricing);

	/**
	* Returns the ivld item pricing with the primary key or throws a {@link NoSuchIvldItemPricingException} if it could not be found.
	*
	* @param ivldItemPricingSid the primary key of the ivld item pricing
	* @return the ivld item pricing
	* @throws NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
	*/
	public IvldItemPricing findByPrimaryKey(int ivldItemPricingSid)
		throws NoSuchIvldItemPricingException;

	/**
	* Returns the ivld item pricing with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldItemPricingSid the primary key of the ivld item pricing
	* @return the ivld item pricing, or <code>null</code> if a ivld item pricing with the primary key could not be found
	*/
	public IvldItemPricing fetchByPrimaryKey(int ivldItemPricingSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldItemPricing> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld item pricings.
	*
	* @return the ivld item pricings
	*/
	public java.util.List<IvldItemPricing> findAll();

	/**
	* Returns a range of all the ivld item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item pricings
	* @param end the upper bound of the range of ivld item pricings (not inclusive)
	* @return the range of ivld item pricings
	*/
	public java.util.List<IvldItemPricing> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item pricings
	* @param end the upper bound of the range of ivld item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld item pricings
	*/
	public java.util.List<IvldItemPricing> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldItemPricing> orderByComparator);

	/**
	* Returns an ordered range of all the ivld item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item pricings
	* @param end the upper bound of the range of ivld item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld item pricings
	*/
	public java.util.List<IvldItemPricing> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldItemPricing> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld item pricings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld item pricings.
	*
	* @return the number of ivld item pricings
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}