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

import com.stpl.app.model.ItemMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the item master service. This utility wraps {@link com.stpl.app.service.persistence.impl.ItemMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemMasterPersistence
 * @see com.stpl.app.service.persistence.impl.ItemMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class ItemMasterUtil {
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
	public static void clearCache(ItemMaster itemMaster) {
		getPersistence().clearCache(itemMaster);
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
	public static List<ItemMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ItemMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ItemMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ItemMaster update(ItemMaster itemMaster) {
		return getPersistence().update(itemMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ItemMaster update(ItemMaster itemMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(itemMaster, serviceContext);
	}

	/**
	* Returns all the item masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the matching item masters
	*/
	public static List<ItemMaster> findByItemNo(java.lang.String itemNo) {
		return getPersistence().findByItemNo(itemNo);
	}

	/**
	* Returns a range of all the item masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @return the range of matching item masters
	*/
	public static List<ItemMaster> findByItemNo(java.lang.String itemNo,
		int start, int end) {
		return getPersistence().findByItemNo(itemNo, start, end);
	}

	/**
	* Returns an ordered range of all the item masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByItemNo(java.lang.String itemNo,
		int start, int end, OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .findByItemNo(itemNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByItemNo(java.lang.String itemNo,
		int start, int end, OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemNo(itemNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByItemNo_First(java.lang.String itemNo,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByItemNo_First(itemNo, orderByComparator);
	}

	/**
	* Returns the first item master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByItemNo_First(java.lang.String itemNo,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence().fetchByItemNo_First(itemNo, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByItemNo_Last(java.lang.String itemNo,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByItemNo_Last(itemNo, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByItemNo_Last(java.lang.String itemNo,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence().fetchByItemNo_Last(itemNo, orderByComparator);
	}

	/**
	* Returns the item masters before and after the current item master in the ordered set where itemNo = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public static ItemMaster[] findByItemNo_PrevAndNext(int itemMasterSid,
		java.lang.String itemNo, OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByItemNo_PrevAndNext(itemMasterSid, itemNo,
			orderByComparator);
	}

	/**
	* Removes all the item masters where itemNo = &#63; from the database.
	*
	* @param itemNo the item no
	*/
	public static void removeByItemNo(java.lang.String itemNo) {
		getPersistence().removeByItemNo(itemNo);
	}

	/**
	* Returns the number of item masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the number of matching item masters
	*/
	public static int countByItemNo(java.lang.String itemNo) {
		return getPersistence().countByItemNo(itemNo);
	}

	/**
	* Returns all the item masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the matching item masters
	*/
	public static List<ItemMaster> findByItemId(java.lang.String itemId) {
		return getPersistence().findByItemId(itemId);
	}

	/**
	* Returns a range of all the item masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @return the range of matching item masters
	*/
	public static List<ItemMaster> findByItemId(java.lang.String itemId,
		int start, int end) {
		return getPersistence().findByItemId(itemId, start, end);
	}

	/**
	* Returns an ordered range of all the item masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByItemId(java.lang.String itemId,
		int start, int end, OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .findByItemId(itemId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByItemId(java.lang.String itemId,
		int start, int end, OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemId(itemId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByItemId_First(java.lang.String itemId,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByItemId_First(itemId, orderByComparator);
	}

	/**
	* Returns the first item master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByItemId_First(java.lang.String itemId,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence().fetchByItemId_First(itemId, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByItemId_Last(java.lang.String itemId,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByItemId_Last(itemId, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByItemId_Last(java.lang.String itemId,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence().fetchByItemId_Last(itemId, orderByComparator);
	}

	/**
	* Returns the item masters before and after the current item master in the ordered set where itemId = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public static ItemMaster[] findByItemId_PrevAndNext(int itemMasterSid,
		java.lang.String itemId, OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByItemId_PrevAndNext(itemMasterSid, itemId,
			orderByComparator);
	}

	/**
	* Removes all the item masters where itemId = &#63; from the database.
	*
	* @param itemId the item ID
	*/
	public static void removeByItemId(java.lang.String itemId) {
		getPersistence().removeByItemId(itemId);
	}

	/**
	* Returns the number of item masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the number of matching item masters
	*/
	public static int countByItemId(java.lang.String itemId) {
		return getPersistence().countByItemId(itemId);
	}

	/**
	* Returns all the item masters where itemName = &#63;.
	*
	* @param itemName the item name
	* @return the matching item masters
	*/
	public static List<ItemMaster> findByItemName(java.lang.String itemName) {
		return getPersistence().findByItemName(itemName);
	}

	/**
	* Returns a range of all the item masters where itemName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemName the item name
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @return the range of matching item masters
	*/
	public static List<ItemMaster> findByItemName(java.lang.String itemName,
		int start, int end) {
		return getPersistence().findByItemName(itemName, start, end);
	}

	/**
	* Returns an ordered range of all the item masters where itemName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemName the item name
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByItemName(java.lang.String itemName,
		int start, int end, OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .findByItemName(itemName, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item masters where itemName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemName the item name
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByItemName(java.lang.String itemName,
		int start, int end, OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemName(itemName, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item master in the ordered set where itemName = &#63;.
	*
	* @param itemName the item name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByItemName_First(java.lang.String itemName,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByItemName_First(itemName, orderByComparator);
	}

	/**
	* Returns the first item master in the ordered set where itemName = &#63;.
	*
	* @param itemName the item name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByItemName_First(java.lang.String itemName,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .fetchByItemName_First(itemName, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where itemName = &#63;.
	*
	* @param itemName the item name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByItemName_Last(java.lang.String itemName,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByItemName_Last(itemName, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where itemName = &#63;.
	*
	* @param itemName the item name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByItemName_Last(java.lang.String itemName,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence().fetchByItemName_Last(itemName, orderByComparator);
	}

	/**
	* Returns the item masters before and after the current item master in the ordered set where itemName = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param itemName the item name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public static ItemMaster[] findByItemName_PrevAndNext(int itemMasterSid,
		java.lang.String itemName,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByItemName_PrevAndNext(itemMasterSid, itemName,
			orderByComparator);
	}

	/**
	* Removes all the item masters where itemName = &#63; from the database.
	*
	* @param itemName the item name
	*/
	public static void removeByItemName(java.lang.String itemName) {
		getPersistence().removeByItemName(itemName);
	}

	/**
	* Returns the number of item masters where itemName = &#63;.
	*
	* @param itemName the item name
	* @return the number of matching item masters
	*/
	public static int countByItemName(java.lang.String itemName) {
		return getPersistence().countByItemName(itemName);
	}

	/**
	* Returns all the item masters where itemType = &#63;.
	*
	* @param itemType the item type
	* @return the matching item masters
	*/
	public static List<ItemMaster> findByItemType(int itemType) {
		return getPersistence().findByItemType(itemType);
	}

	/**
	* Returns a range of all the item masters where itemType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemType the item type
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @return the range of matching item masters
	*/
	public static List<ItemMaster> findByItemType(int itemType, int start,
		int end) {
		return getPersistence().findByItemType(itemType, start, end);
	}

	/**
	* Returns an ordered range of all the item masters where itemType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemType the item type
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByItemType(int itemType, int start,
		int end, OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .findByItemType(itemType, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item masters where itemType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemType the item type
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByItemType(int itemType, int start,
		int end, OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemType(itemType, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item master in the ordered set where itemType = &#63;.
	*
	* @param itemType the item type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByItemType_First(int itemType,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByItemType_First(itemType, orderByComparator);
	}

	/**
	* Returns the first item master in the ordered set where itemType = &#63;.
	*
	* @param itemType the item type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByItemType_First(int itemType,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .fetchByItemType_First(itemType, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where itemType = &#63;.
	*
	* @param itemType the item type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByItemType_Last(int itemType,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByItemType_Last(itemType, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where itemType = &#63;.
	*
	* @param itemType the item type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByItemType_Last(int itemType,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence().fetchByItemType_Last(itemType, orderByComparator);
	}

	/**
	* Returns the item masters before and after the current item master in the ordered set where itemType = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param itemType the item type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public static ItemMaster[] findByItemType_PrevAndNext(int itemMasterSid,
		int itemType, OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByItemType_PrevAndNext(itemMasterSid, itemType,
			orderByComparator);
	}

	/**
	* Removes all the item masters where itemType = &#63; from the database.
	*
	* @param itemType the item type
	*/
	public static void removeByItemType(int itemType) {
		getPersistence().removeByItemType(itemType);
	}

	/**
	* Returns the number of item masters where itemType = &#63;.
	*
	* @param itemType the item type
	* @return the number of matching item masters
	*/
	public static int countByItemType(int itemType) {
		return getPersistence().countByItemType(itemType);
	}

	/**
	* Returns all the item masters where itemStatus = &#63;.
	*
	* @param itemStatus the item status
	* @return the matching item masters
	*/
	public static List<ItemMaster> findByItemStatus(int itemStatus) {
		return getPersistence().findByItemStatus(itemStatus);
	}

	/**
	* Returns a range of all the item masters where itemStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemStatus the item status
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @return the range of matching item masters
	*/
	public static List<ItemMaster> findByItemStatus(int itemStatus, int start,
		int end) {
		return getPersistence().findByItemStatus(itemStatus, start, end);
	}

	/**
	* Returns an ordered range of all the item masters where itemStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemStatus the item status
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByItemStatus(int itemStatus, int start,
		int end, OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .findByItemStatus(itemStatus, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item masters where itemStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemStatus the item status
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByItemStatus(int itemStatus, int start,
		int end, OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemStatus(itemStatus, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item master in the ordered set where itemStatus = &#63;.
	*
	* @param itemStatus the item status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByItemStatus_First(int itemStatus,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByItemStatus_First(itemStatus, orderByComparator);
	}

	/**
	* Returns the first item master in the ordered set where itemStatus = &#63;.
	*
	* @param itemStatus the item status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByItemStatus_First(int itemStatus,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .fetchByItemStatus_First(itemStatus, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where itemStatus = &#63;.
	*
	* @param itemStatus the item status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByItemStatus_Last(int itemStatus,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByItemStatus_Last(itemStatus, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where itemStatus = &#63;.
	*
	* @param itemStatus the item status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByItemStatus_Last(int itemStatus,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .fetchByItemStatus_Last(itemStatus, orderByComparator);
	}

	/**
	* Returns the item masters before and after the current item master in the ordered set where itemStatus = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param itemStatus the item status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public static ItemMaster[] findByItemStatus_PrevAndNext(int itemMasterSid,
		int itemStatus, OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByItemStatus_PrevAndNext(itemMasterSid, itemStatus,
			orderByComparator);
	}

	/**
	* Removes all the item masters where itemStatus = &#63; from the database.
	*
	* @param itemStatus the item status
	*/
	public static void removeByItemStatus(int itemStatus) {
		getPersistence().removeByItemStatus(itemStatus);
	}

	/**
	* Returns the number of item masters where itemStatus = &#63;.
	*
	* @param itemStatus the item status
	* @return the number of matching item masters
	*/
	public static int countByItemStatus(int itemStatus) {
		return getPersistence().countByItemStatus(itemStatus);
	}

	/**
	* Returns all the item masters where manufacturerId = &#63;.
	*
	* @param manufacturerId the manufacturer ID
	* @return the matching item masters
	*/
	public static List<ItemMaster> findByManufacturerNo(
		java.lang.String manufacturerId) {
		return getPersistence().findByManufacturerNo(manufacturerId);
	}

	/**
	* Returns a range of all the item masters where manufacturerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param manufacturerId the manufacturer ID
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @return the range of matching item masters
	*/
	public static List<ItemMaster> findByManufacturerNo(
		java.lang.String manufacturerId, int start, int end) {
		return getPersistence().findByManufacturerNo(manufacturerId, start, end);
	}

	/**
	* Returns an ordered range of all the item masters where manufacturerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param manufacturerId the manufacturer ID
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByManufacturerNo(
		java.lang.String manufacturerId, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .findByManufacturerNo(manufacturerId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the item masters where manufacturerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param manufacturerId the manufacturer ID
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByManufacturerNo(
		java.lang.String manufacturerId, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByManufacturerNo(manufacturerId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first item master in the ordered set where manufacturerId = &#63;.
	*
	* @param manufacturerId the manufacturer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByManufacturerNo_First(
		java.lang.String manufacturerId,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByManufacturerNo_First(manufacturerId, orderByComparator);
	}

	/**
	* Returns the first item master in the ordered set where manufacturerId = &#63;.
	*
	* @param manufacturerId the manufacturer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByManufacturerNo_First(
		java.lang.String manufacturerId,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .fetchByManufacturerNo_First(manufacturerId,
			orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where manufacturerId = &#63;.
	*
	* @param manufacturerId the manufacturer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByManufacturerNo_Last(
		java.lang.String manufacturerId,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByManufacturerNo_Last(manufacturerId, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where manufacturerId = &#63;.
	*
	* @param manufacturerId the manufacturer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByManufacturerNo_Last(
		java.lang.String manufacturerId,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .fetchByManufacturerNo_Last(manufacturerId, orderByComparator);
	}

	/**
	* Returns the item masters before and after the current item master in the ordered set where manufacturerId = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param manufacturerId the manufacturer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public static ItemMaster[] findByManufacturerNo_PrevAndNext(
		int itemMasterSid, java.lang.String manufacturerId,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByManufacturerNo_PrevAndNext(itemMasterSid,
			manufacturerId, orderByComparator);
	}

	/**
	* Removes all the item masters where manufacturerId = &#63; from the database.
	*
	* @param manufacturerId the manufacturer ID
	*/
	public static void removeByManufacturerNo(java.lang.String manufacturerId) {
		getPersistence().removeByManufacturerNo(manufacturerId);
	}

	/**
	* Returns the number of item masters where manufacturerId = &#63;.
	*
	* @param manufacturerId the manufacturer ID
	* @return the number of matching item masters
	*/
	public static int countByManufacturerNo(java.lang.String manufacturerId) {
		return getPersistence().countByManufacturerNo(manufacturerId);
	}

	/**
	* Returns all the item masters where form = &#63;.
	*
	* @param form the form
	* @return the matching item masters
	*/
	public static List<ItemMaster> findByForm(int form) {
		return getPersistence().findByForm(form);
	}

	/**
	* Returns a range of all the item masters where form = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param form the form
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @return the range of matching item masters
	*/
	public static List<ItemMaster> findByForm(int form, int start, int end) {
		return getPersistence().findByForm(form, start, end);
	}

	/**
	* Returns an ordered range of all the item masters where form = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param form the form
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByForm(int form, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence().findByForm(form, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item masters where form = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param form the form
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByForm(int form, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByForm(form, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item master in the ordered set where form = &#63;.
	*
	* @param form the form
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByForm_First(int form,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByForm_First(form, orderByComparator);
	}

	/**
	* Returns the first item master in the ordered set where form = &#63;.
	*
	* @param form the form
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByForm_First(int form,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence().fetchByForm_First(form, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where form = &#63;.
	*
	* @param form the form
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByForm_Last(int form,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByForm_Last(form, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where form = &#63;.
	*
	* @param form the form
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByForm_Last(int form,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence().fetchByForm_Last(form, orderByComparator);
	}

	/**
	* Returns the item masters before and after the current item master in the ordered set where form = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param form the form
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public static ItemMaster[] findByForm_PrevAndNext(int itemMasterSid,
		int form, OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByForm_PrevAndNext(itemMasterSid, form,
			orderByComparator);
	}

	/**
	* Removes all the item masters where form = &#63; from the database.
	*
	* @param form the form
	*/
	public static void removeByForm(int form) {
		getPersistence().removeByForm(form);
	}

	/**
	* Returns the number of item masters where form = &#63;.
	*
	* @param form the form
	* @return the number of matching item masters
	*/
	public static int countByForm(int form) {
		return getPersistence().countByForm(form);
	}

	/**
	* Returns all the item masters where strength = &#63;.
	*
	* @param strength the strength
	* @return the matching item masters
	*/
	public static List<ItemMaster> findByStrength(int strength) {
		return getPersistence().findByStrength(strength);
	}

	/**
	* Returns a range of all the item masters where strength = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param strength the strength
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @return the range of matching item masters
	*/
	public static List<ItemMaster> findByStrength(int strength, int start,
		int end) {
		return getPersistence().findByStrength(strength, start, end);
	}

	/**
	* Returns an ordered range of all the item masters where strength = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param strength the strength
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByStrength(int strength, int start,
		int end, OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .findByStrength(strength, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item masters where strength = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param strength the strength
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByStrength(int strength, int start,
		int end, OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByStrength(strength, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item master in the ordered set where strength = &#63;.
	*
	* @param strength the strength
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByStrength_First(int strength,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByStrength_First(strength, orderByComparator);
	}

	/**
	* Returns the first item master in the ordered set where strength = &#63;.
	*
	* @param strength the strength
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByStrength_First(int strength,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .fetchByStrength_First(strength, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where strength = &#63;.
	*
	* @param strength the strength
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByStrength_Last(int strength,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByStrength_Last(strength, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where strength = &#63;.
	*
	* @param strength the strength
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByStrength_Last(int strength,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence().fetchByStrength_Last(strength, orderByComparator);
	}

	/**
	* Returns the item masters before and after the current item master in the ordered set where strength = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param strength the strength
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public static ItemMaster[] findByStrength_PrevAndNext(int itemMasterSid,
		int strength, OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByStrength_PrevAndNext(itemMasterSid, strength,
			orderByComparator);
	}

	/**
	* Removes all the item masters where strength = &#63; from the database.
	*
	* @param strength the strength
	*/
	public static void removeByStrength(int strength) {
		getPersistence().removeByStrength(strength);
	}

	/**
	* Returns the number of item masters where strength = &#63;.
	*
	* @param strength the strength
	* @return the number of matching item masters
	*/
	public static int countByStrength(int strength) {
		return getPersistence().countByStrength(strength);
	}

	/**
	* Returns all the item masters where primaryUom = &#63;.
	*
	* @param primaryUom the primary uom
	* @return the matching item masters
	*/
	public static List<ItemMaster> findByPrimaryUom(int primaryUom) {
		return getPersistence().findByPrimaryUom(primaryUom);
	}

	/**
	* Returns a range of all the item masters where primaryUom = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param primaryUom the primary uom
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @return the range of matching item masters
	*/
	public static List<ItemMaster> findByPrimaryUom(int primaryUom, int start,
		int end) {
		return getPersistence().findByPrimaryUom(primaryUom, start, end);
	}

	/**
	* Returns an ordered range of all the item masters where primaryUom = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param primaryUom the primary uom
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByPrimaryUom(int primaryUom, int start,
		int end, OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .findByPrimaryUom(primaryUom, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item masters where primaryUom = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param primaryUom the primary uom
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item masters
	*/
	public static List<ItemMaster> findByPrimaryUom(int primaryUom, int start,
		int end, OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPrimaryUom(primaryUom, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item master in the ordered set where primaryUom = &#63;.
	*
	* @param primaryUom the primary uom
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByPrimaryUom_First(int primaryUom,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByPrimaryUom_First(primaryUom, orderByComparator);
	}

	/**
	* Returns the first item master in the ordered set where primaryUom = &#63;.
	*
	* @param primaryUom the primary uom
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByPrimaryUom_First(int primaryUom,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .fetchByPrimaryUom_First(primaryUom, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where primaryUom = &#63;.
	*
	* @param primaryUom the primary uom
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public static ItemMaster findByPrimaryUom_Last(int primaryUom,
		OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByPrimaryUom_Last(primaryUom, orderByComparator);
	}

	/**
	* Returns the last item master in the ordered set where primaryUom = &#63;.
	*
	* @param primaryUom the primary uom
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public static ItemMaster fetchByPrimaryUom_Last(int primaryUom,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence()
				   .fetchByPrimaryUom_Last(primaryUom, orderByComparator);
	}

	/**
	* Returns the item masters before and after the current item master in the ordered set where primaryUom = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param primaryUom the primary uom
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public static ItemMaster[] findByPrimaryUom_PrevAndNext(int itemMasterSid,
		int primaryUom, OrderByComparator<ItemMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence()
				   .findByPrimaryUom_PrevAndNext(itemMasterSid, primaryUom,
			orderByComparator);
	}

	/**
	* Removes all the item masters where primaryUom = &#63; from the database.
	*
	* @param primaryUom the primary uom
	*/
	public static void removeByPrimaryUom(int primaryUom) {
		getPersistence().removeByPrimaryUom(primaryUom);
	}

	/**
	* Returns the number of item masters where primaryUom = &#63;.
	*
	* @param primaryUom the primary uom
	* @return the number of matching item masters
	*/
	public static int countByPrimaryUom(int primaryUom) {
		return getPersistence().countByPrimaryUom(primaryUom);
	}

	/**
	* Caches the item master in the entity cache if it is enabled.
	*
	* @param itemMaster the item master
	*/
	public static void cacheResult(ItemMaster itemMaster) {
		getPersistence().cacheResult(itemMaster);
	}

	/**
	* Caches the item masters in the entity cache if it is enabled.
	*
	* @param itemMasters the item masters
	*/
	public static void cacheResult(List<ItemMaster> itemMasters) {
		getPersistence().cacheResult(itemMasters);
	}

	/**
	* Creates a new item master with the primary key. Does not add the item master to the database.
	*
	* @param itemMasterSid the primary key for the new item master
	* @return the new item master
	*/
	public static ItemMaster create(int itemMasterSid) {
		return getPersistence().create(itemMasterSid);
	}

	/**
	* Removes the item master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemMasterSid the primary key of the item master
	* @return the item master that was removed
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public static ItemMaster remove(int itemMasterSid)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().remove(itemMasterSid);
	}

	public static ItemMaster updateImpl(ItemMaster itemMaster) {
		return getPersistence().updateImpl(itemMaster);
	}

	/**
	* Returns the item master with the primary key or throws a {@link NoSuchItemMasterException} if it could not be found.
	*
	* @param itemMasterSid the primary key of the item master
	* @return the item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public static ItemMaster findByPrimaryKey(int itemMasterSid)
		throws com.stpl.app.exception.NoSuchItemMasterException {
		return getPersistence().findByPrimaryKey(itemMasterSid);
	}

	/**
	* Returns the item master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemMasterSid the primary key of the item master
	* @return the item master, or <code>null</code> if a item master with the primary key could not be found
	*/
	public static ItemMaster fetchByPrimaryKey(int itemMasterSid) {
		return getPersistence().fetchByPrimaryKey(itemMasterSid);
	}

	public static java.util.Map<java.io.Serializable, ItemMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the item masters.
	*
	* @return the item masters
	*/
	public static List<ItemMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @return the range of item masters
	*/
	public static List<ItemMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of item masters
	*/
	public static List<ItemMaster> findAll(int start, int end,
		OrderByComparator<ItemMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of item masters
	*/
	public static List<ItemMaster> findAll(int start, int end,
		OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the item masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of item masters.
	*
	* @return the number of item masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ItemMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ItemMasterPersistence, ItemMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ItemMasterPersistence.class);
}