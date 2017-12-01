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

import com.stpl.app.exception.NoSuchItemHierarchyMasterException;
import com.stpl.app.model.ItemHierarchyMaster;

/**
 * The persistence interface for the item hierarchy master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ItemHierarchyMasterPersistenceImpl
 * @see ItemHierarchyMasterUtil
 * @generated
 */
@ProviderType
public interface ItemHierarchyMasterPersistence extends BasePersistence<ItemHierarchyMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItemHierarchyMasterUtil} to access the item hierarchy master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the item hierarchy masters where level0 = &#63;.
	*
	* @param level0 the level0
	* @return the matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByLevel0(
		java.lang.String level0);

	/**
	* Returns a range of all the item hierarchy masters where level0 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0 the level0
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @return the range of matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByLevel0(
		java.lang.String level0, int start, int end);

	/**
	* Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0 the level0
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByLevel0(
		java.lang.String level0, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0 the level0
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByLevel0(
		java.lang.String level0, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster findByLevel0_First(java.lang.String level0,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Returns the first item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster fetchByLevel0_First(java.lang.String level0,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns the last item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster findByLevel0_Last(java.lang.String level0,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Returns the last item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster fetchByLevel0_Last(java.lang.String level0,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param itemHierarchyMasterSid the primary key of the current item hierarchy master
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	*/
	public ItemHierarchyMaster[] findByLevel0_PrevAndNext(
		int itemHierarchyMasterSid, java.lang.String level0,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Removes all the item hierarchy masters where level0 = &#63; from the database.
	*
	* @param level0 the level0
	*/
	public void removeByLevel0(java.lang.String level0);

	/**
	* Returns the number of item hierarchy masters where level0 = &#63;.
	*
	* @param level0 the level0
	* @return the number of matching item hierarchy masters
	*/
	public int countByLevel0(java.lang.String level0);

	/**
	* Returns all the item hierarchy masters where level0Alias = &#63;.
	*
	* @param level0Alias the level0 alias
	* @return the matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByLevel0Alias(
		java.lang.String level0Alias);

	/**
	* Returns a range of all the item hierarchy masters where level0Alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0Alias the level0 alias
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @return the range of matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByLevel0Alias(
		java.lang.String level0Alias, int start, int end);

	/**
	* Returns an ordered range of all the item hierarchy masters where level0Alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0Alias the level0 alias
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByLevel0Alias(
		java.lang.String level0Alias, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the item hierarchy masters where level0Alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0Alias the level0 alias
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByLevel0Alias(
		java.lang.String level0Alias, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item hierarchy master in the ordered set where level0Alias = &#63;.
	*
	* @param level0Alias the level0 alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster findByLevel0Alias_First(
		java.lang.String level0Alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Returns the first item hierarchy master in the ordered set where level0Alias = &#63;.
	*
	* @param level0Alias the level0 alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster fetchByLevel0Alias_First(
		java.lang.String level0Alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns the last item hierarchy master in the ordered set where level0Alias = &#63;.
	*
	* @param level0Alias the level0 alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster findByLevel0Alias_Last(
		java.lang.String level0Alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Returns the last item hierarchy master in the ordered set where level0Alias = &#63;.
	*
	* @param level0Alias the level0 alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster fetchByLevel0Alias_Last(
		java.lang.String level0Alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0Alias = &#63;.
	*
	* @param itemHierarchyMasterSid the primary key of the current item hierarchy master
	* @param level0Alias the level0 alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	*/
	public ItemHierarchyMaster[] findByLevel0Alias_PrevAndNext(
		int itemHierarchyMasterSid, java.lang.String level0Alias,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Removes all the item hierarchy masters where level0Alias = &#63; from the database.
	*
	* @param level0Alias the level0 alias
	*/
	public void removeByLevel0Alias(java.lang.String level0Alias);

	/**
	* Returns the number of item hierarchy masters where level0Alias = &#63;.
	*
	* @param level0Alias the level0 alias
	* @return the number of matching item hierarchy masters
	*/
	public int countByLevel0Alias(java.lang.String level0Alias);

	/**
	* Returns all the item hierarchy masters where level0Tag = &#63;.
	*
	* @param level0Tag the level0 tag
	* @return the matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByLevel0Tag(
		java.lang.String level0Tag);

	/**
	* Returns a range of all the item hierarchy masters where level0Tag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0Tag the level0 tag
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @return the range of matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByLevel0Tag(
		java.lang.String level0Tag, int start, int end);

	/**
	* Returns an ordered range of all the item hierarchy masters where level0Tag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0Tag the level0 tag
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByLevel0Tag(
		java.lang.String level0Tag, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the item hierarchy masters where level0Tag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0Tag the level0 tag
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByLevel0Tag(
		java.lang.String level0Tag, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item hierarchy master in the ordered set where level0Tag = &#63;.
	*
	* @param level0Tag the level0 tag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster findByLevel0Tag_First(
		java.lang.String level0Tag,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Returns the first item hierarchy master in the ordered set where level0Tag = &#63;.
	*
	* @param level0Tag the level0 tag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster fetchByLevel0Tag_First(
		java.lang.String level0Tag,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns the last item hierarchy master in the ordered set where level0Tag = &#63;.
	*
	* @param level0Tag the level0 tag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster findByLevel0Tag_Last(
		java.lang.String level0Tag,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Returns the last item hierarchy master in the ordered set where level0Tag = &#63;.
	*
	* @param level0Tag the level0 tag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster fetchByLevel0Tag_Last(
		java.lang.String level0Tag,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0Tag = &#63;.
	*
	* @param itemHierarchyMasterSid the primary key of the current item hierarchy master
	* @param level0Tag the level0 tag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	*/
	public ItemHierarchyMaster[] findByLevel0Tag_PrevAndNext(
		int itemHierarchyMasterSid, java.lang.String level0Tag,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Removes all the item hierarchy masters where level0Tag = &#63; from the database.
	*
	* @param level0Tag the level0 tag
	*/
	public void removeByLevel0Tag(java.lang.String level0Tag);

	/**
	* Returns the number of item hierarchy masters where level0Tag = &#63;.
	*
	* @param level0Tag the level0 tag
	* @return the number of matching item hierarchy masters
	*/
	public int countByLevel0Tag(java.lang.String level0Tag);

	/**
	* Returns all the item hierarchy masters where level0 = &#63;.
	*
	* @param level0 the level0
	* @return the matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByItemHierarchyUnique(
		java.lang.String level0);

	/**
	* Returns a range of all the item hierarchy masters where level0 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0 the level0
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @return the range of matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByItemHierarchyUnique(
		java.lang.String level0, int start, int end);

	/**
	* Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0 the level0
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByItemHierarchyUnique(
		java.lang.String level0, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0 the level0
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findByItemHierarchyUnique(
		java.lang.String level0, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster findByItemHierarchyUnique_First(
		java.lang.String level0,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Returns the first item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster fetchByItemHierarchyUnique_First(
		java.lang.String level0,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns the last item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster findByItemHierarchyUnique_Last(
		java.lang.String level0,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Returns the last item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public ItemHierarchyMaster fetchByItemHierarchyUnique_Last(
		java.lang.String level0,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param itemHierarchyMasterSid the primary key of the current item hierarchy master
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	*/
	public ItemHierarchyMaster[] findByItemHierarchyUnique_PrevAndNext(
		int itemHierarchyMasterSid, java.lang.String level0,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Removes all the item hierarchy masters where level0 = &#63; from the database.
	*
	* @param level0 the level0
	*/
	public void removeByItemHierarchyUnique(java.lang.String level0);

	/**
	* Returns the number of item hierarchy masters where level0 = &#63;.
	*
	* @param level0 the level0
	* @return the number of matching item hierarchy masters
	*/
	public int countByItemHierarchyUnique(java.lang.String level0);

	/**
	* Caches the item hierarchy master in the entity cache if it is enabled.
	*
	* @param itemHierarchyMaster the item hierarchy master
	*/
	public void cacheResult(ItemHierarchyMaster itemHierarchyMaster);

	/**
	* Caches the item hierarchy masters in the entity cache if it is enabled.
	*
	* @param itemHierarchyMasters the item hierarchy masters
	*/
	public void cacheResult(
		java.util.List<ItemHierarchyMaster> itemHierarchyMasters);

	/**
	* Creates a new item hierarchy master with the primary key. Does not add the item hierarchy master to the database.
	*
	* @param itemHierarchyMasterSid the primary key for the new item hierarchy master
	* @return the new item hierarchy master
	*/
	public ItemHierarchyMaster create(int itemHierarchyMasterSid);

	/**
	* Removes the item hierarchy master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemHierarchyMasterSid the primary key of the item hierarchy master
	* @return the item hierarchy master that was removed
	* @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	*/
	public ItemHierarchyMaster remove(int itemHierarchyMasterSid)
		throws NoSuchItemHierarchyMasterException;

	public ItemHierarchyMaster updateImpl(
		ItemHierarchyMaster itemHierarchyMaster);

	/**
	* Returns the item hierarchy master with the primary key or throws a {@link NoSuchItemHierarchyMasterException} if it could not be found.
	*
	* @param itemHierarchyMasterSid the primary key of the item hierarchy master
	* @return the item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	*/
	public ItemHierarchyMaster findByPrimaryKey(int itemHierarchyMasterSid)
		throws NoSuchItemHierarchyMasterException;

	/**
	* Returns the item hierarchy master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemHierarchyMasterSid the primary key of the item hierarchy master
	* @return the item hierarchy master, or <code>null</code> if a item hierarchy master with the primary key could not be found
	*/
	public ItemHierarchyMaster fetchByPrimaryKey(int itemHierarchyMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, ItemHierarchyMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the item hierarchy masters.
	*
	* @return the item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findAll();

	/**
	* Returns a range of all the item hierarchy masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @return the range of item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the item hierarchy masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the item hierarchy masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of item hierarchy masters
	*/
	public java.util.List<ItemHierarchyMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the item hierarchy masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of item hierarchy masters.
	*
	* @return the number of item hierarchy masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}