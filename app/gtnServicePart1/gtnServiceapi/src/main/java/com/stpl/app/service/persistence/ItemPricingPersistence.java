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

import com.stpl.app.exception.NoSuchItemPricingException;
import com.stpl.app.model.ItemPricing;

import java.util.Date;

/**
 * The persistence interface for the item pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ItemPricingPersistenceImpl
 * @see ItemPricingUtil
 * @generated
 */
@ProviderType
public interface ItemPricingPersistence extends BasePersistence<ItemPricing> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItemPricingUtil} to access the item pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param itemUom the item uom
	* @param itemPricingQualifierSid the item pricing qualifier sid
	* @param pricingCodeStatus the pricing code status
	* @param startDate the start date
	* @return the matching item pricings
	*/
	public java.util.List<ItemPricing> findByItemPricing(int itemMasterSid,
		int itemUom, int itemPricingQualifierSid, int pricingCodeStatus,
		Date startDate);

	/**
	* Returns a range of all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemMasterSid the item master sid
	* @param itemUom the item uom
	* @param itemPricingQualifierSid the item pricing qualifier sid
	* @param pricingCodeStatus the pricing code status
	* @param startDate the start date
	* @param start the lower bound of the range of item pricings
	* @param end the upper bound of the range of item pricings (not inclusive)
	* @return the range of matching item pricings
	*/
	public java.util.List<ItemPricing> findByItemPricing(int itemMasterSid,
		int itemUom, int itemPricingQualifierSid, int pricingCodeStatus,
		Date startDate, int start, int end);

	/**
	* Returns an ordered range of all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemMasterSid the item master sid
	* @param itemUom the item uom
	* @param itemPricingQualifierSid the item pricing qualifier sid
	* @param pricingCodeStatus the pricing code status
	* @param startDate the start date
	* @param start the lower bound of the range of item pricings
	* @param end the upper bound of the range of item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item pricings
	*/
	public java.util.List<ItemPricing> findByItemPricing(int itemMasterSid,
		int itemUom, int itemPricingQualifierSid, int pricingCodeStatus,
		Date startDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator);

	/**
	* Returns an ordered range of all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemMasterSid the item master sid
	* @param itemUom the item uom
	* @param itemPricingQualifierSid the item pricing qualifier sid
	* @param pricingCodeStatus the pricing code status
	* @param startDate the start date
	* @param start the lower bound of the range of item pricings
	* @param end the upper bound of the range of item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item pricings
	*/
	public java.util.List<ItemPricing> findByItemPricing(int itemMasterSid,
		int itemUom, int itemPricingQualifierSid, int pricingCodeStatus,
		Date startDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param itemUom the item uom
	* @param itemPricingQualifierSid the item pricing qualifier sid
	* @param pricingCodeStatus the pricing code status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item pricing
	* @throws NoSuchItemPricingException if a matching item pricing could not be found
	*/
	public ItemPricing findByItemPricing_First(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator)
		throws NoSuchItemPricingException;

	/**
	* Returns the first item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param itemUom the item uom
	* @param itemPricingQualifierSid the item pricing qualifier sid
	* @param pricingCodeStatus the pricing code status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item pricing, or <code>null</code> if a matching item pricing could not be found
	*/
	public ItemPricing fetchByItemPricing_First(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator);

	/**
	* Returns the last item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param itemUom the item uom
	* @param itemPricingQualifierSid the item pricing qualifier sid
	* @param pricingCodeStatus the pricing code status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item pricing
	* @throws NoSuchItemPricingException if a matching item pricing could not be found
	*/
	public ItemPricing findByItemPricing_Last(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator)
		throws NoSuchItemPricingException;

	/**
	* Returns the last item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param itemUom the item uom
	* @param itemPricingQualifierSid the item pricing qualifier sid
	* @param pricingCodeStatus the pricing code status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item pricing, or <code>null</code> if a matching item pricing could not be found
	*/
	public ItemPricing fetchByItemPricing_Last(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator);

	/**
	* Returns the item pricings before and after the current item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	*
	* @param itemPricingSid the primary key of the current item pricing
	* @param itemMasterSid the item master sid
	* @param itemUom the item uom
	* @param itemPricingQualifierSid the item pricing qualifier sid
	* @param pricingCodeStatus the pricing code status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item pricing
	* @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	*/
	public ItemPricing[] findByItemPricing_PrevAndNext(int itemPricingSid,
		int itemMasterSid, int itemUom, int itemPricingQualifierSid,
		int pricingCodeStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator)
		throws NoSuchItemPricingException;

	/**
	* Removes all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63; from the database.
	*
	* @param itemMasterSid the item master sid
	* @param itemUom the item uom
	* @param itemPricingQualifierSid the item pricing qualifier sid
	* @param pricingCodeStatus the pricing code status
	* @param startDate the start date
	*/
	public void removeByItemPricing(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate);

	/**
	* Returns the number of item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param itemUom the item uom
	* @param itemPricingQualifierSid the item pricing qualifier sid
	* @param pricingCodeStatus the pricing code status
	* @param startDate the start date
	* @return the number of matching item pricings
	*/
	public int countByItemPricing(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate);

	/**
	* Returns all the item pricings where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @return the matching item pricings
	*/
	public java.util.List<ItemPricing> findByItemPricingDetails(
		int itemMasterSid);

	/**
	* Returns a range of all the item pricings where itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of item pricings
	* @param end the upper bound of the range of item pricings (not inclusive)
	* @return the range of matching item pricings
	*/
	public java.util.List<ItemPricing> findByItemPricingDetails(
		int itemMasterSid, int start, int end);

	/**
	* Returns an ordered range of all the item pricings where itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of item pricings
	* @param end the upper bound of the range of item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item pricings
	*/
	public java.util.List<ItemPricing> findByItemPricingDetails(
		int itemMasterSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator);

	/**
	* Returns an ordered range of all the item pricings where itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of item pricings
	* @param end the upper bound of the range of item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item pricings
	*/
	public java.util.List<ItemPricing> findByItemPricingDetails(
		int itemMasterSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item pricing in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item pricing
	* @throws NoSuchItemPricingException if a matching item pricing could not be found
	*/
	public ItemPricing findByItemPricingDetails_First(int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator)
		throws NoSuchItemPricingException;

	/**
	* Returns the first item pricing in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item pricing, or <code>null</code> if a matching item pricing could not be found
	*/
	public ItemPricing fetchByItemPricingDetails_First(int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator);

	/**
	* Returns the last item pricing in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item pricing
	* @throws NoSuchItemPricingException if a matching item pricing could not be found
	*/
	public ItemPricing findByItemPricingDetails_Last(int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator)
		throws NoSuchItemPricingException;

	/**
	* Returns the last item pricing in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item pricing, or <code>null</code> if a matching item pricing could not be found
	*/
	public ItemPricing fetchByItemPricingDetails_Last(int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator);

	/**
	* Returns the item pricings before and after the current item pricing in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemPricingSid the primary key of the current item pricing
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item pricing
	* @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	*/
	public ItemPricing[] findByItemPricingDetails_PrevAndNext(
		int itemPricingSid, int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator)
		throws NoSuchItemPricingException;

	/**
	* Removes all the item pricings where itemMasterSid = &#63; from the database.
	*
	* @param itemMasterSid the item master sid
	*/
	public void removeByItemPricingDetails(int itemMasterSid);

	/**
	* Returns the number of item pricings where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @return the number of matching item pricings
	*/
	public int countByItemPricingDetails(int itemMasterSid);

	/**
	* Caches the item pricing in the entity cache if it is enabled.
	*
	* @param itemPricing the item pricing
	*/
	public void cacheResult(ItemPricing itemPricing);

	/**
	* Caches the item pricings in the entity cache if it is enabled.
	*
	* @param itemPricings the item pricings
	*/
	public void cacheResult(java.util.List<ItemPricing> itemPricings);

	/**
	* Creates a new item pricing with the primary key. Does not add the item pricing to the database.
	*
	* @param itemPricingSid the primary key for the new item pricing
	* @return the new item pricing
	*/
	public ItemPricing create(int itemPricingSid);

	/**
	* Removes the item pricing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricingSid the primary key of the item pricing
	* @return the item pricing that was removed
	* @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	*/
	public ItemPricing remove(int itemPricingSid)
		throws NoSuchItemPricingException;

	public ItemPricing updateImpl(ItemPricing itemPricing);

	/**
	* Returns the item pricing with the primary key or throws a {@link NoSuchItemPricingException} if it could not be found.
	*
	* @param itemPricingSid the primary key of the item pricing
	* @return the item pricing
	* @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	*/
	public ItemPricing findByPrimaryKey(int itemPricingSid)
		throws NoSuchItemPricingException;

	/**
	* Returns the item pricing with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemPricingSid the primary key of the item pricing
	* @return the item pricing, or <code>null</code> if a item pricing with the primary key could not be found
	*/
	public ItemPricing fetchByPrimaryKey(int itemPricingSid);

	@Override
	public java.util.Map<java.io.Serializable, ItemPricing> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the item pricings.
	*
	* @return the item pricings
	*/
	public java.util.List<ItemPricing> findAll();

	/**
	* Returns a range of all the item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item pricings
	* @param end the upper bound of the range of item pricings (not inclusive)
	* @return the range of item pricings
	*/
	public java.util.List<ItemPricing> findAll(int start, int end);

	/**
	* Returns an ordered range of all the item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item pricings
	* @param end the upper bound of the range of item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of item pricings
	*/
	public java.util.List<ItemPricing> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator);

	/**
	* Returns an ordered range of all the item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item pricings
	* @param end the upper bound of the range of item pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of item pricings
	*/
	public java.util.List<ItemPricing> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemPricing> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the item pricings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of item pricings.
	*
	* @return the number of item pricings
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}