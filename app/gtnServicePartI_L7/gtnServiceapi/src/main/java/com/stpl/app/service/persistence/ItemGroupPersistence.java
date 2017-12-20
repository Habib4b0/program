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

import com.stpl.app.exception.NoSuchItemGroupException;
import com.stpl.app.model.ItemGroup;

/**
 * The persistence interface for the item group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ItemGroupPersistenceImpl
 * @see ItemGroupUtil
 * @generated
 */
@ProviderType
public interface ItemGroupPersistence extends BasePersistence<ItemGroup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItemGroupUtil} to access the item group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the item group in the entity cache if it is enabled.
	*
	* @param itemGroup the item group
	*/
	public void cacheResult(ItemGroup itemGroup);

	/**
	* Caches the item groups in the entity cache if it is enabled.
	*
	* @param itemGroups the item groups
	*/
	public void cacheResult(java.util.List<ItemGroup> itemGroups);

	/**
	* Creates a new item group with the primary key. Does not add the item group to the database.
	*
	* @param itemGroupSid the primary key for the new item group
	* @return the new item group
	*/
	public ItemGroup create(int itemGroupSid);

	/**
	* Removes the item group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemGroupSid the primary key of the item group
	* @return the item group that was removed
	* @throws NoSuchItemGroupException if a item group with the primary key could not be found
	*/
	public ItemGroup remove(int itemGroupSid) throws NoSuchItemGroupException;

	public ItemGroup updateImpl(ItemGroup itemGroup);

	/**
	* Returns the item group with the primary key or throws a {@link NoSuchItemGroupException} if it could not be found.
	*
	* @param itemGroupSid the primary key of the item group
	* @return the item group
	* @throws NoSuchItemGroupException if a item group with the primary key could not be found
	*/
	public ItemGroup findByPrimaryKey(int itemGroupSid)
		throws NoSuchItemGroupException;

	/**
	* Returns the item group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemGroupSid the primary key of the item group
	* @return the item group, or <code>null</code> if a item group with the primary key could not be found
	*/
	public ItemGroup fetchByPrimaryKey(int itemGroupSid);

	@Override
	public java.util.Map<java.io.Serializable, ItemGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the item groups.
	*
	* @return the item groups
	*/
	public java.util.List<ItemGroup> findAll();

	/**
	* Returns a range of all the item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item groups
	* @param end the upper bound of the range of item groups (not inclusive)
	* @return the range of item groups
	*/
	public java.util.List<ItemGroup> findAll(int start, int end);

	/**
	* Returns an ordered range of all the item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item groups
	* @param end the upper bound of the range of item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of item groups
	*/
	public java.util.List<ItemGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemGroup> orderByComparator);

	/**
	* Returns an ordered range of all the item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item groups
	* @param end the upper bound of the range of item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of item groups
	*/
	public java.util.List<ItemGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the item groups from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of item groups.
	*
	* @return the number of item groups
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}