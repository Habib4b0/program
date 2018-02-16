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

import com.stpl.app.exception.NoSuchItemMasterException;
import com.stpl.app.model.ItemMaster;

/**
 * The persistence interface for the item master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ItemMasterPersistenceImpl
 * @see ItemMasterUtil
 * @generated
 */
@ProviderType
public interface ItemMasterPersistence extends BasePersistence<ItemMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItemMasterUtil} to access the item master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the item masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the matching item masters
	*/
	public java.util.List<ItemMaster> findByItemNo(java.lang.String itemNo);

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
	public java.util.List<ItemMaster> findByItemNo(java.lang.String itemNo,
		int start, int end);

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
	public java.util.List<ItemMaster> findByItemNo(java.lang.String itemNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

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
	public java.util.List<ItemMaster> findByItemNo(java.lang.String itemNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByItemNo_First(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the first item master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByItemNo_First(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the last item master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByItemNo_Last(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the last item master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByItemNo_Last(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the item masters before and after the current item master in the ordered set where itemNo = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public ItemMaster[] findByItemNo_PrevAndNext(int itemMasterSid,
		java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Removes all the item masters where itemNo = &#63; from the database.
	*
	* @param itemNo the item no
	*/
	public void removeByItemNo(java.lang.String itemNo);

	/**
	* Returns the number of item masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the number of matching item masters
	*/
	public int countByItemNo(java.lang.String itemNo);

	/**
	* Returns all the item masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the matching item masters
	*/
	public java.util.List<ItemMaster> findByItemId(java.lang.String itemId);

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
	public java.util.List<ItemMaster> findByItemId(java.lang.String itemId,
		int start, int end);

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
	public java.util.List<ItemMaster> findByItemId(java.lang.String itemId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

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
	public java.util.List<ItemMaster> findByItemId(java.lang.String itemId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the first item master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the last item master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the last item master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the item masters before and after the current item master in the ordered set where itemId = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public ItemMaster[] findByItemId_PrevAndNext(int itemMasterSid,
		java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Removes all the item masters where itemId = &#63; from the database.
	*
	* @param itemId the item ID
	*/
	public void removeByItemId(java.lang.String itemId);

	/**
	* Returns the number of item masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the number of matching item masters
	*/
	public int countByItemId(java.lang.String itemId);

	/**
	* Returns all the item masters where itemName = &#63;.
	*
	* @param itemName the item name
	* @return the matching item masters
	*/
	public java.util.List<ItemMaster> findByItemName(java.lang.String itemName);

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
	public java.util.List<ItemMaster> findByItemName(
		java.lang.String itemName, int start, int end);

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
	public java.util.List<ItemMaster> findByItemName(
		java.lang.String itemName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

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
	public java.util.List<ItemMaster> findByItemName(
		java.lang.String itemName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item master in the ordered set where itemName = &#63;.
	*
	* @param itemName the item name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByItemName_First(java.lang.String itemName,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the first item master in the ordered set where itemName = &#63;.
	*
	* @param itemName the item name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByItemName_First(java.lang.String itemName,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the last item master in the ordered set where itemName = &#63;.
	*
	* @param itemName the item name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByItemName_Last(java.lang.String itemName,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the last item master in the ordered set where itemName = &#63;.
	*
	* @param itemName the item name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByItemName_Last(java.lang.String itemName,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the item masters before and after the current item master in the ordered set where itemName = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param itemName the item name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public ItemMaster[] findByItemName_PrevAndNext(int itemMasterSid,
		java.lang.String itemName,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Removes all the item masters where itemName = &#63; from the database.
	*
	* @param itemName the item name
	*/
	public void removeByItemName(java.lang.String itemName);

	/**
	* Returns the number of item masters where itemName = &#63;.
	*
	* @param itemName the item name
	* @return the number of matching item masters
	*/
	public int countByItemName(java.lang.String itemName);

	/**
	* Returns all the item masters where itemType = &#63;.
	*
	* @param itemType the item type
	* @return the matching item masters
	*/
	public java.util.List<ItemMaster> findByItemType(int itemType);

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
	public java.util.List<ItemMaster> findByItemType(int itemType, int start,
		int end);

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
	public java.util.List<ItemMaster> findByItemType(int itemType, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

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
	public java.util.List<ItemMaster> findByItemType(int itemType, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item master in the ordered set where itemType = &#63;.
	*
	* @param itemType the item type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByItemType_First(int itemType,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the first item master in the ordered set where itemType = &#63;.
	*
	* @param itemType the item type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByItemType_First(int itemType,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the last item master in the ordered set where itemType = &#63;.
	*
	* @param itemType the item type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByItemType_Last(int itemType,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the last item master in the ordered set where itemType = &#63;.
	*
	* @param itemType the item type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByItemType_Last(int itemType,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the item masters before and after the current item master in the ordered set where itemType = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param itemType the item type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public ItemMaster[] findByItemType_PrevAndNext(int itemMasterSid,
		int itemType,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Removes all the item masters where itemType = &#63; from the database.
	*
	* @param itemType the item type
	*/
	public void removeByItemType(int itemType);

	/**
	* Returns the number of item masters where itemType = &#63;.
	*
	* @param itemType the item type
	* @return the number of matching item masters
	*/
	public int countByItemType(int itemType);

	/**
	* Returns all the item masters where itemStatus = &#63;.
	*
	* @param itemStatus the item status
	* @return the matching item masters
	*/
	public java.util.List<ItemMaster> findByItemStatus(int itemStatus);

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
	public java.util.List<ItemMaster> findByItemStatus(int itemStatus,
		int start, int end);

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
	public java.util.List<ItemMaster> findByItemStatus(int itemStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

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
	public java.util.List<ItemMaster> findByItemStatus(int itemStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item master in the ordered set where itemStatus = &#63;.
	*
	* @param itemStatus the item status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByItemStatus_First(int itemStatus,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the first item master in the ordered set where itemStatus = &#63;.
	*
	* @param itemStatus the item status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByItemStatus_First(int itemStatus,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the last item master in the ordered set where itemStatus = &#63;.
	*
	* @param itemStatus the item status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByItemStatus_Last(int itemStatus,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the last item master in the ordered set where itemStatus = &#63;.
	*
	* @param itemStatus the item status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByItemStatus_Last(int itemStatus,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the item masters before and after the current item master in the ordered set where itemStatus = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param itemStatus the item status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public ItemMaster[] findByItemStatus_PrevAndNext(int itemMasterSid,
		int itemStatus,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Removes all the item masters where itemStatus = &#63; from the database.
	*
	* @param itemStatus the item status
	*/
	public void removeByItemStatus(int itemStatus);

	/**
	* Returns the number of item masters where itemStatus = &#63;.
	*
	* @param itemStatus the item status
	* @return the number of matching item masters
	*/
	public int countByItemStatus(int itemStatus);

	/**
	* Returns all the item masters where manufacturerId = &#63;.
	*
	* @param manufacturerId the manufacturer ID
	* @return the matching item masters
	*/
	public java.util.List<ItemMaster> findByManufacturerNo(
		java.lang.String manufacturerId);

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
	public java.util.List<ItemMaster> findByManufacturerNo(
		java.lang.String manufacturerId, int start, int end);

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
	public java.util.List<ItemMaster> findByManufacturerNo(
		java.lang.String manufacturerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

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
	public java.util.List<ItemMaster> findByManufacturerNo(
		java.lang.String manufacturerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item master in the ordered set where manufacturerId = &#63;.
	*
	* @param manufacturerId the manufacturer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByManufacturerNo_First(
		java.lang.String manufacturerId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the first item master in the ordered set where manufacturerId = &#63;.
	*
	* @param manufacturerId the manufacturer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByManufacturerNo_First(
		java.lang.String manufacturerId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the last item master in the ordered set where manufacturerId = &#63;.
	*
	* @param manufacturerId the manufacturer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByManufacturerNo_Last(
		java.lang.String manufacturerId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the last item master in the ordered set where manufacturerId = &#63;.
	*
	* @param manufacturerId the manufacturer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByManufacturerNo_Last(
		java.lang.String manufacturerId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the item masters before and after the current item master in the ordered set where manufacturerId = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param manufacturerId the manufacturer ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public ItemMaster[] findByManufacturerNo_PrevAndNext(int itemMasterSid,
		java.lang.String manufacturerId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Removes all the item masters where manufacturerId = &#63; from the database.
	*
	* @param manufacturerId the manufacturer ID
	*/
	public void removeByManufacturerNo(java.lang.String manufacturerId);

	/**
	* Returns the number of item masters where manufacturerId = &#63;.
	*
	* @param manufacturerId the manufacturer ID
	* @return the number of matching item masters
	*/
	public int countByManufacturerNo(java.lang.String manufacturerId);

	/**
	* Returns all the item masters where form = &#63;.
	*
	* @param form the form
	* @return the matching item masters
	*/
	public java.util.List<ItemMaster> findByForm(int form);

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
	public java.util.List<ItemMaster> findByForm(int form, int start, int end);

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
	public java.util.List<ItemMaster> findByForm(int form, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

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
	public java.util.List<ItemMaster> findByForm(int form, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item master in the ordered set where form = &#63;.
	*
	* @param form the form
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByForm_First(int form,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the first item master in the ordered set where form = &#63;.
	*
	* @param form the form
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByForm_First(int form,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the last item master in the ordered set where form = &#63;.
	*
	* @param form the form
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByForm_Last(int form,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the last item master in the ordered set where form = &#63;.
	*
	* @param form the form
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByForm_Last(int form,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the item masters before and after the current item master in the ordered set where form = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param form the form
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public ItemMaster[] findByForm_PrevAndNext(int itemMasterSid, int form,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Removes all the item masters where form = &#63; from the database.
	*
	* @param form the form
	*/
	public void removeByForm(int form);

	/**
	* Returns the number of item masters where form = &#63;.
	*
	* @param form the form
	* @return the number of matching item masters
	*/
	public int countByForm(int form);

	/**
	* Returns all the item masters where strength = &#63;.
	*
	* @param strength the strength
	* @return the matching item masters
	*/
	public java.util.List<ItemMaster> findByStrength(int strength);

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
	public java.util.List<ItemMaster> findByStrength(int strength, int start,
		int end);

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
	public java.util.List<ItemMaster> findByStrength(int strength, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

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
	public java.util.List<ItemMaster> findByStrength(int strength, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item master in the ordered set where strength = &#63;.
	*
	* @param strength the strength
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByStrength_First(int strength,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the first item master in the ordered set where strength = &#63;.
	*
	* @param strength the strength
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByStrength_First(int strength,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the last item master in the ordered set where strength = &#63;.
	*
	* @param strength the strength
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByStrength_Last(int strength,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the last item master in the ordered set where strength = &#63;.
	*
	* @param strength the strength
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByStrength_Last(int strength,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the item masters before and after the current item master in the ordered set where strength = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param strength the strength
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public ItemMaster[] findByStrength_PrevAndNext(int itemMasterSid,
		int strength,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Removes all the item masters where strength = &#63; from the database.
	*
	* @param strength the strength
	*/
	public void removeByStrength(int strength);

	/**
	* Returns the number of item masters where strength = &#63;.
	*
	* @param strength the strength
	* @return the number of matching item masters
	*/
	public int countByStrength(int strength);

	/**
	* Returns all the item masters where primaryUom = &#63;.
	*
	* @param primaryUom the primary uom
	* @return the matching item masters
	*/
	public java.util.List<ItemMaster> findByPrimaryUom(int primaryUom);

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
	public java.util.List<ItemMaster> findByPrimaryUom(int primaryUom,
		int start, int end);

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
	public java.util.List<ItemMaster> findByPrimaryUom(int primaryUom,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

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
	public java.util.List<ItemMaster> findByPrimaryUom(int primaryUom,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item master in the ordered set where primaryUom = &#63;.
	*
	* @param primaryUom the primary uom
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByPrimaryUom_First(int primaryUom,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the first item master in the ordered set where primaryUom = &#63;.
	*
	* @param primaryUom the primary uom
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByPrimaryUom_First(int primaryUom,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the last item master in the ordered set where primaryUom = &#63;.
	*
	* @param primaryUom the primary uom
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master
	* @throws NoSuchItemMasterException if a matching item master could not be found
	*/
	public ItemMaster findByPrimaryUom_Last(int primaryUom,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Returns the last item master in the ordered set where primaryUom = &#63;.
	*
	* @param primaryUom the primary uom
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item master, or <code>null</code> if a matching item master could not be found
	*/
	public ItemMaster fetchByPrimaryUom_Last(int primaryUom,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

	/**
	* Returns the item masters before and after the current item master in the ordered set where primaryUom = &#63;.
	*
	* @param itemMasterSid the primary key of the current item master
	* @param primaryUom the primary uom
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public ItemMaster[] findByPrimaryUom_PrevAndNext(int itemMasterSid,
		int primaryUom,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException;

	/**
	* Removes all the item masters where primaryUom = &#63; from the database.
	*
	* @param primaryUom the primary uom
	*/
	public void removeByPrimaryUom(int primaryUom);

	/**
	* Returns the number of item masters where primaryUom = &#63;.
	*
	* @param primaryUom the primary uom
	* @return the number of matching item masters
	*/
	public int countByPrimaryUom(int primaryUom);

	/**
	* Caches the item master in the entity cache if it is enabled.
	*
	* @param itemMaster the item master
	*/
	public void cacheResult(ItemMaster itemMaster);

	/**
	* Caches the item masters in the entity cache if it is enabled.
	*
	* @param itemMasters the item masters
	*/
	public void cacheResult(java.util.List<ItemMaster> itemMasters);

	/**
	* Creates a new item master with the primary key. Does not add the item master to the database.
	*
	* @param itemMasterSid the primary key for the new item master
	* @return the new item master
	*/
	public ItemMaster create(int itemMasterSid);

	/**
	* Removes the item master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemMasterSid the primary key of the item master
	* @return the item master that was removed
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public ItemMaster remove(int itemMasterSid)
		throws NoSuchItemMasterException;

	public ItemMaster updateImpl(ItemMaster itemMaster);

	/**
	* Returns the item master with the primary key or throws a {@link NoSuchItemMasterException} if it could not be found.
	*
	* @param itemMasterSid the primary key of the item master
	* @return the item master
	* @throws NoSuchItemMasterException if a item master with the primary key could not be found
	*/
	public ItemMaster findByPrimaryKey(int itemMasterSid)
		throws NoSuchItemMasterException;

	/**
	* Returns the item master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemMasterSid the primary key of the item master
	* @return the item master, or <code>null</code> if a item master with the primary key could not be found
	*/
	public ItemMaster fetchByPrimaryKey(int itemMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, ItemMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the item masters.
	*
	* @return the item masters
	*/
	public java.util.List<ItemMaster> findAll();

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
	public java.util.List<ItemMaster> findAll(int start, int end);

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
	public java.util.List<ItemMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator);

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
	public java.util.List<ItemMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the item masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of item masters.
	*
	* @return the number of item masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}