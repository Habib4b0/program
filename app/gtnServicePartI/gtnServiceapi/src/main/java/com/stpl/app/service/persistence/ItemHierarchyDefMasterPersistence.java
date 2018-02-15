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

import com.stpl.app.exception.NoSuchItemHierarchyDefMasterException;
import com.stpl.app.model.ItemHierarchyDefMaster;

/**
 * The persistence interface for the item hierarchy def master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ItemHierarchyDefMasterPersistenceImpl
 * @see ItemHierarchyDefMasterUtil
 * @generated
 */
@ProviderType
public interface ItemHierarchyDefMasterPersistence extends BasePersistence<ItemHierarchyDefMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItemHierarchyDefMasterUtil} to access the item hierarchy def master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the item hierarchy def masters where member = &#63;.
	*
	* @param member the member
	* @return the matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByMember(
		java.lang.String member);

	/**
	* Returns a range of all the item hierarchy def masters where member = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param member the member
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @return the range of matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByMember(
		java.lang.String member, int start, int end);

	/**
	* Returns an ordered range of all the item hierarchy def masters where member = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param member the member
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByMember(
		java.lang.String member, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns an ordered range of all the item hierarchy def masters where member = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param member the member
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByMember(
		java.lang.String member, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item hierarchy def master in the ordered set where member = &#63;.
	*
	* @param member the member
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster findByMember_First(java.lang.String member,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Returns the first item hierarchy def master in the ordered set where member = &#63;.
	*
	* @param member the member
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster fetchByMember_First(java.lang.String member,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns the last item hierarchy def master in the ordered set where member = &#63;.
	*
	* @param member the member
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster findByMember_Last(java.lang.String member,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Returns the last item hierarchy def master in the ordered set where member = &#63;.
	*
	* @param member the member
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster fetchByMember_Last(java.lang.String member,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where member = &#63;.
	*
	* @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
	* @param member the member
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	*/
	public ItemHierarchyDefMaster[] findByMember_PrevAndNext(
		int itemHierarchyDefMasterSid, java.lang.String member,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Removes all the item hierarchy def masters where member = &#63; from the database.
	*
	* @param member the member
	*/
	public void removeByMember(java.lang.String member);

	/**
	* Returns the number of item hierarchy def masters where member = &#63;.
	*
	* @param member the member
	* @return the number of matching item hierarchy def masters
	*/
	public int countByMember(java.lang.String member);

	/**
	* Returns all the item hierarchy def masters where alias = &#63;.
	*
	* @param alias the alias
	* @return the matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByAlias(
		java.lang.String alias);

	/**
	* Returns a range of all the item hierarchy def masters where alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param alias the alias
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @return the range of matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByAlias(
		java.lang.String alias, int start, int end);

	/**
	* Returns an ordered range of all the item hierarchy def masters where alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param alias the alias
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByAlias(
		java.lang.String alias, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns an ordered range of all the item hierarchy def masters where alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param alias the alias
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByAlias(
		java.lang.String alias, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item hierarchy def master in the ordered set where alias = &#63;.
	*
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster findByAlias_First(java.lang.String alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Returns the first item hierarchy def master in the ordered set where alias = &#63;.
	*
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster fetchByAlias_First(java.lang.String alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns the last item hierarchy def master in the ordered set where alias = &#63;.
	*
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster findByAlias_Last(java.lang.String alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Returns the last item hierarchy def master in the ordered set where alias = &#63;.
	*
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster fetchByAlias_Last(java.lang.String alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where alias = &#63;.
	*
	* @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	*/
	public ItemHierarchyDefMaster[] findByAlias_PrevAndNext(
		int itemHierarchyDefMasterSid, java.lang.String alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Removes all the item hierarchy def masters where alias = &#63; from the database.
	*
	* @param alias the alias
	*/
	public void removeByAlias(java.lang.String alias);

	/**
	* Returns the number of item hierarchy def masters where alias = &#63;.
	*
	* @param alias the alias
	* @return the number of matching item hierarchy def masters
	*/
	public int countByAlias(java.lang.String alias);

	/**
	* Returns all the item hierarchy def masters where bpiLvl = &#63;.
	*
	* @param bpiLvl the bpi lvl
	* @return the matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByBpiLvl(
		java.lang.String bpiLvl);

	/**
	* Returns a range of all the item hierarchy def masters where bpiLvl = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bpiLvl the bpi lvl
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @return the range of matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByBpiLvl(
		java.lang.String bpiLvl, int start, int end);

	/**
	* Returns an ordered range of all the item hierarchy def masters where bpiLvl = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bpiLvl the bpi lvl
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByBpiLvl(
		java.lang.String bpiLvl, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns an ordered range of all the item hierarchy def masters where bpiLvl = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bpiLvl the bpi lvl
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByBpiLvl(
		java.lang.String bpiLvl, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item hierarchy def master in the ordered set where bpiLvl = &#63;.
	*
	* @param bpiLvl the bpi lvl
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster findByBpiLvl_First(java.lang.String bpiLvl,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Returns the first item hierarchy def master in the ordered set where bpiLvl = &#63;.
	*
	* @param bpiLvl the bpi lvl
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster fetchByBpiLvl_First(java.lang.String bpiLvl,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns the last item hierarchy def master in the ordered set where bpiLvl = &#63;.
	*
	* @param bpiLvl the bpi lvl
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster findByBpiLvl_Last(java.lang.String bpiLvl,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Returns the last item hierarchy def master in the ordered set where bpiLvl = &#63;.
	*
	* @param bpiLvl the bpi lvl
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster fetchByBpiLvl_Last(java.lang.String bpiLvl,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where bpiLvl = &#63;.
	*
	* @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
	* @param bpiLvl the bpi lvl
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	*/
	public ItemHierarchyDefMaster[] findByBpiLvl_PrevAndNext(
		int itemHierarchyDefMasterSid, java.lang.String bpiLvl,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Removes all the item hierarchy def masters where bpiLvl = &#63; from the database.
	*
	* @param bpiLvl the bpi lvl
	*/
	public void removeByBpiLvl(java.lang.String bpiLvl);

	/**
	* Returns the number of item hierarchy def masters where bpiLvl = &#63;.
	*
	* @param bpiLvl the bpi lvl
	* @return the number of matching item hierarchy def masters
	*/
	public int countByBpiLvl(java.lang.String bpiLvl);

	/**
	* Returns all the item hierarchy def masters where member = &#63; and alias = &#63;.
	*
	* @param member the member
	* @param alias the alias
	* @return the matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
		java.lang.String member, java.lang.String alias);

	/**
	* Returns a range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param member the member
	* @param alias the alias
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @return the range of matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
		java.lang.String member, java.lang.String alias, int start, int end);

	/**
	* Returns an ordered range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param member the member
	* @param alias the alias
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
		java.lang.String member, java.lang.String alias, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns an ordered range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param member the member
	* @param alias the alias
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
		java.lang.String member, java.lang.String alias, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	*
	* @param member the member
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster findByItemHierarchyDefUnique_First(
		java.lang.String member, java.lang.String alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Returns the first item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	*
	* @param member the member
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster fetchByItemHierarchyDefUnique_First(
		java.lang.String member, java.lang.String alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns the last item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	*
	* @param member the member
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster findByItemHierarchyDefUnique_Last(
		java.lang.String member, java.lang.String alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Returns the last item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	*
	* @param member the member
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public ItemHierarchyDefMaster fetchByItemHierarchyDefUnique_Last(
		java.lang.String member, java.lang.String alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	*
	* @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
	* @param member the member
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	*/
	public ItemHierarchyDefMaster[] findByItemHierarchyDefUnique_PrevAndNext(
		int itemHierarchyDefMasterSid, java.lang.String member,
		java.lang.String alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Removes all the item hierarchy def masters where member = &#63; and alias = &#63; from the database.
	*
	* @param member the member
	* @param alias the alias
	*/
	public void removeByItemHierarchyDefUnique(java.lang.String member,
		java.lang.String alias);

	/**
	* Returns the number of item hierarchy def masters where member = &#63; and alias = &#63;.
	*
	* @param member the member
	* @param alias the alias
	* @return the number of matching item hierarchy def masters
	*/
	public int countByItemHierarchyDefUnique(java.lang.String member,
		java.lang.String alias);

	/**
	* Caches the item hierarchy def master in the entity cache if it is enabled.
	*
	* @param itemHierarchyDefMaster the item hierarchy def master
	*/
	public void cacheResult(ItemHierarchyDefMaster itemHierarchyDefMaster);

	/**
	* Caches the item hierarchy def masters in the entity cache if it is enabled.
	*
	* @param itemHierarchyDefMasters the item hierarchy def masters
	*/
	public void cacheResult(
		java.util.List<ItemHierarchyDefMaster> itemHierarchyDefMasters);

	/**
	* Creates a new item hierarchy def master with the primary key. Does not add the item hierarchy def master to the database.
	*
	* @param itemHierarchyDefMasterSid the primary key for the new item hierarchy def master
	* @return the new item hierarchy def master
	*/
	public ItemHierarchyDefMaster create(int itemHierarchyDefMasterSid);

	/**
	* Removes the item hierarchy def master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
	* @return the item hierarchy def master that was removed
	* @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	*/
	public ItemHierarchyDefMaster remove(int itemHierarchyDefMasterSid)
		throws NoSuchItemHierarchyDefMasterException;

	public ItemHierarchyDefMaster updateImpl(
		ItemHierarchyDefMaster itemHierarchyDefMaster);

	/**
	* Returns the item hierarchy def master with the primary key or throws a {@link NoSuchItemHierarchyDefMasterException} if it could not be found.
	*
	* @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
	* @return the item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	*/
	public ItemHierarchyDefMaster findByPrimaryKey(
		int itemHierarchyDefMasterSid)
		throws NoSuchItemHierarchyDefMasterException;

	/**
	* Returns the item hierarchy def master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
	* @return the item hierarchy def master, or <code>null</code> if a item hierarchy def master with the primary key could not be found
	*/
	public ItemHierarchyDefMaster fetchByPrimaryKey(
		int itemHierarchyDefMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, ItemHierarchyDefMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the item hierarchy def masters.
	*
	* @return the item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findAll();

	/**
	* Returns a range of all the item hierarchy def masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @return the range of item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the item hierarchy def masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator);

	/**
	* Returns an ordered range of all the item hierarchy def masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of item hierarchy def masters
	*/
	public java.util.List<ItemHierarchyDefMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the item hierarchy def masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of item hierarchy def masters.
	*
	* @return the number of item hierarchy def masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}