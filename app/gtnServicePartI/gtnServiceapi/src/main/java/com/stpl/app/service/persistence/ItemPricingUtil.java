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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.model.ItemPricing;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the item pricing service. This utility wraps {@link com.stpl.app.service.persistence.impl.ItemPricingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemPricingPersistence
 * @see com.stpl.app.service.persistence.impl.ItemPricingPersistenceImpl
 * @generated
 */
@ProviderType
public class ItemPricingUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ItemPricing itemPricing) {
		getPersistence().clearCache(itemPricing);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ItemPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ItemPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ItemPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ItemPricing> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ItemPricing update(ItemPricing itemPricing) {
		return getPersistence().update(itemPricing);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ItemPricing update(ItemPricing itemPricing,
		ServiceContext serviceContext) {
		return getPersistence().update(itemPricing, serviceContext);
	}

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
	public static List<ItemPricing> findByItemPricing(int itemMasterSid,
		int itemUom, int itemPricingQualifierSid, int pricingCodeStatus,
		Date startDate) {
		return getPersistence()
				   .findByItemPricing(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate);
	}

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
	public static List<ItemPricing> findByItemPricing(int itemMasterSid,
		int itemUom, int itemPricingQualifierSid, int pricingCodeStatus,
		Date startDate, int start, int end) {
		return getPersistence()
				   .findByItemPricing(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate, start, end);
	}

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
	public static List<ItemPricing> findByItemPricing(int itemMasterSid,
		int itemUom, int itemPricingQualifierSid, int pricingCodeStatus,
		Date startDate, int start, int end,
		OrderByComparator<ItemPricing> orderByComparator) {
		return getPersistence()
				   .findByItemPricing(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate, start, end,
			orderByComparator);
	}

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
	public static List<ItemPricing> findByItemPricing(int itemMasterSid,
		int itemUom, int itemPricingQualifierSid, int pricingCodeStatus,
		Date startDate, int start, int end,
		OrderByComparator<ItemPricing> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemPricing(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate, start, end,
			orderByComparator, retrieveFromCache);
	}

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
	public static ItemPricing findByItemPricing_First(int itemMasterSid,
		int itemUom, int itemPricingQualifierSid, int pricingCodeStatus,
		Date startDate, OrderByComparator<ItemPricing> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemPricingException {
		return getPersistence()
				   .findByItemPricing_First(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate,
			orderByComparator);
	}

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
	public static ItemPricing fetchByItemPricing_First(int itemMasterSid,
		int itemUom, int itemPricingQualifierSid, int pricingCodeStatus,
		Date startDate, OrderByComparator<ItemPricing> orderByComparator) {
		return getPersistence()
				   .fetchByItemPricing_First(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate,
			orderByComparator);
	}

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
	public static ItemPricing findByItemPricing_Last(int itemMasterSid,
		int itemUom, int itemPricingQualifierSid, int pricingCodeStatus,
		Date startDate, OrderByComparator<ItemPricing> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemPricingException {
		return getPersistence()
				   .findByItemPricing_Last(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate,
			orderByComparator);
	}

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
	public static ItemPricing fetchByItemPricing_Last(int itemMasterSid,
		int itemUom, int itemPricingQualifierSid, int pricingCodeStatus,
		Date startDate, OrderByComparator<ItemPricing> orderByComparator) {
		return getPersistence()
				   .fetchByItemPricing_Last(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate,
			orderByComparator);
	}

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
	public static ItemPricing[] findByItemPricing_PrevAndNext(
		int itemPricingSid, int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		OrderByComparator<ItemPricing> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemPricingException {
		return getPersistence()
				   .findByItemPricing_PrevAndNext(itemPricingSid,
			itemMasterSid, itemUom, itemPricingQualifierSid, pricingCodeStatus,
			startDate, orderByComparator);
	}

	/**
	* Removes all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63; from the database.
	*
	* @param itemMasterSid the item master sid
	* @param itemUom the item uom
	* @param itemPricingQualifierSid the item pricing qualifier sid
	* @param pricingCodeStatus the pricing code status
	* @param startDate the start date
	*/
	public static void removeByItemPricing(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate) {
		getPersistence()
			.removeByItemPricing(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate);
	}

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
	public static int countByItemPricing(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate) {
		return getPersistence()
				   .countByItemPricing(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate);
	}

	/**
	* Returns all the item pricings where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @return the matching item pricings
	*/
	public static List<ItemPricing> findByItemPricingDetails(int itemMasterSid) {
		return getPersistence().findByItemPricingDetails(itemMasterSid);
	}

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
	public static List<ItemPricing> findByItemPricingDetails(
		int itemMasterSid, int start, int end) {
		return getPersistence()
				   .findByItemPricingDetails(itemMasterSid, start, end);
	}

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
	public static List<ItemPricing> findByItemPricingDetails(
		int itemMasterSid, int start, int end,
		OrderByComparator<ItemPricing> orderByComparator) {
		return getPersistence()
				   .findByItemPricingDetails(itemMasterSid, start, end,
			orderByComparator);
	}

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
	public static List<ItemPricing> findByItemPricingDetails(
		int itemMasterSid, int start, int end,
		OrderByComparator<ItemPricing> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemPricingDetails(itemMasterSid, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first item pricing in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item pricing
	* @throws NoSuchItemPricingException if a matching item pricing could not be found
	*/
	public static ItemPricing findByItemPricingDetails_First(
		int itemMasterSid, OrderByComparator<ItemPricing> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemPricingException {
		return getPersistence()
				   .findByItemPricingDetails_First(itemMasterSid,
			orderByComparator);
	}

	/**
	* Returns the first item pricing in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item pricing, or <code>null</code> if a matching item pricing could not be found
	*/
	public static ItemPricing fetchByItemPricingDetails_First(
		int itemMasterSid, OrderByComparator<ItemPricing> orderByComparator) {
		return getPersistence()
				   .fetchByItemPricingDetails_First(itemMasterSid,
			orderByComparator);
	}

	/**
	* Returns the last item pricing in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item pricing
	* @throws NoSuchItemPricingException if a matching item pricing could not be found
	*/
	public static ItemPricing findByItemPricingDetails_Last(int itemMasterSid,
		OrderByComparator<ItemPricing> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemPricingException {
		return getPersistence()
				   .findByItemPricingDetails_Last(itemMasterSid,
			orderByComparator);
	}

	/**
	* Returns the last item pricing in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item pricing, or <code>null</code> if a matching item pricing could not be found
	*/
	public static ItemPricing fetchByItemPricingDetails_Last(
		int itemMasterSid, OrderByComparator<ItemPricing> orderByComparator) {
		return getPersistence()
				   .fetchByItemPricingDetails_Last(itemMasterSid,
			orderByComparator);
	}

	/**
	* Returns the item pricings before and after the current item pricing in the ordered set where itemMasterSid = &#63;.
	*
	* @param itemPricingSid the primary key of the current item pricing
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item pricing
	* @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	*/
	public static ItemPricing[] findByItemPricingDetails_PrevAndNext(
		int itemPricingSid, int itemMasterSid,
		OrderByComparator<ItemPricing> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemPricingException {
		return getPersistence()
				   .findByItemPricingDetails_PrevAndNext(itemPricingSid,
			itemMasterSid, orderByComparator);
	}

	/**
	* Removes all the item pricings where itemMasterSid = &#63; from the database.
	*
	* @param itemMasterSid the item master sid
	*/
	public static void removeByItemPricingDetails(int itemMasterSid) {
		getPersistence().removeByItemPricingDetails(itemMasterSid);
	}

	/**
	* Returns the number of item pricings where itemMasterSid = &#63;.
	*
	* @param itemMasterSid the item master sid
	* @return the number of matching item pricings
	*/
	public static int countByItemPricingDetails(int itemMasterSid) {
		return getPersistence().countByItemPricingDetails(itemMasterSid);
	}

	/**
	* Caches the item pricing in the entity cache if it is enabled.
	*
	* @param itemPricing the item pricing
	*/
	public static void cacheResult(ItemPricing itemPricing) {
		getPersistence().cacheResult(itemPricing);
	}

	/**
	* Caches the item pricings in the entity cache if it is enabled.
	*
	* @param itemPricings the item pricings
	*/
	public static void cacheResult(List<ItemPricing> itemPricings) {
		getPersistence().cacheResult(itemPricings);
	}

	/**
	* Creates a new item pricing with the primary key. Does not add the item pricing to the database.
	*
	* @param itemPricingSid the primary key for the new item pricing
	* @return the new item pricing
	*/
	public static ItemPricing create(int itemPricingSid) {
		return getPersistence().create(itemPricingSid);
	}

	/**
	* Removes the item pricing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricingSid the primary key of the item pricing
	* @return the item pricing that was removed
	* @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	*/
	public static ItemPricing remove(int itemPricingSid)
		throws com.stpl.app.exception.NoSuchItemPricingException {
		return getPersistence().remove(itemPricingSid);
	}

	public static ItemPricing updateImpl(ItemPricing itemPricing) {
		return getPersistence().updateImpl(itemPricing);
	}

	/**
	* Returns the item pricing with the primary key or throws a {@link NoSuchItemPricingException} if it could not be found.
	*
	* @param itemPricingSid the primary key of the item pricing
	* @return the item pricing
	* @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	*/
	public static ItemPricing findByPrimaryKey(int itemPricingSid)
		throws com.stpl.app.exception.NoSuchItemPricingException {
		return getPersistence().findByPrimaryKey(itemPricingSid);
	}

	/**
	* Returns the item pricing with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemPricingSid the primary key of the item pricing
	* @return the item pricing, or <code>null</code> if a item pricing with the primary key could not be found
	*/
	public static ItemPricing fetchByPrimaryKey(int itemPricingSid) {
		return getPersistence().fetchByPrimaryKey(itemPricingSid);
	}

	public static java.util.Map<java.io.Serializable, ItemPricing> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the item pricings.
	*
	* @return the item pricings
	*/
	public static List<ItemPricing> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ItemPricing> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ItemPricing> findAll(int start, int end,
		OrderByComparator<ItemPricing> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ItemPricing> findAll(int start, int end,
		OrderByComparator<ItemPricing> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the item pricings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of item pricings.
	*
	* @return the number of item pricings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ItemPricingPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ItemPricingPersistence, ItemPricingPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ItemPricingPersistence.class);
}