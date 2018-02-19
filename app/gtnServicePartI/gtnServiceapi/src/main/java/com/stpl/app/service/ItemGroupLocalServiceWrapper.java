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

package com.stpl.app.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ItemGroupLocalService}.
 *
 * @author
 * @see ItemGroupLocalService
 * @generated
 */
@ProviderType
public class ItemGroupLocalServiceWrapper implements ItemGroupLocalService,
	ServiceWrapper<ItemGroupLocalService> {
	public ItemGroupLocalServiceWrapper(
		ItemGroupLocalService itemGroupLocalService) {
		_itemGroupLocalService = itemGroupLocalService;
	}

	/**
	* Adds the item group to the database. Also notifies the appropriate model listeners.
	*
	* @param itemGroup the item group
	* @return the item group that was added
	*/
	@Override
	public com.stpl.app.model.ItemGroup addItemGroup(
		com.stpl.app.model.ItemGroup itemGroup) {
		return _itemGroupLocalService.addItemGroup(itemGroup);
	}

	/**
	* Creates a new item group with the primary key. Does not add the item group to the database.
	*
	* @param itemGroupSid the primary key for the new item group
	* @return the new item group
	*/
	@Override
	public com.stpl.app.model.ItemGroup createItemGroup(int itemGroupSid) {
		return _itemGroupLocalService.createItemGroup(itemGroupSid);
	}

	/**
	* Deletes the item group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemGroupSid the primary key of the item group
	* @return the item group that was removed
	* @throws PortalException if a item group with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemGroup deleteItemGroup(int itemGroupSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemGroupLocalService.deleteItemGroup(itemGroupSid);
	}

	/**
	* Deletes the item group from the database. Also notifies the appropriate model listeners.
	*
	* @param itemGroup the item group
	* @return the item group that was removed
	*/
	@Override
	public com.stpl.app.model.ItemGroup deleteItemGroup(
		com.stpl.app.model.ItemGroup itemGroup) {
		return _itemGroupLocalService.deleteItemGroup(itemGroup);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemGroupLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemGroupLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _itemGroupLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _itemGroupLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _itemGroupLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _itemGroupLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _itemGroupLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.stpl.app.model.ItemGroup fetchItemGroup(int itemGroupSid) {
		return _itemGroupLocalService.fetchItemGroup(itemGroupSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _itemGroupLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _itemGroupLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the item group with the primary key.
	*
	* @param itemGroupSid the primary key of the item group
	* @return the item group
	* @throws PortalException if a item group with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemGroup getItemGroup(int itemGroupSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemGroupLocalService.getItemGroup(itemGroupSid);
	}

	/**
	* Returns a range of all the item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item groups
	* @param end the upper bound of the range of item groups (not inclusive)
	* @return the range of item groups
	*/
	@Override
	public java.util.List<com.stpl.app.model.ItemGroup> getItemGroups(
		int start, int end) {
		return _itemGroupLocalService.getItemGroups(start, end);
	}

	/**
	* Returns the number of item groups.
	*
	* @return the number of item groups
	*/
	@Override
	public int getItemGroupsCount() {
		return _itemGroupLocalService.getItemGroupsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _itemGroupLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemGroupLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the item group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param itemGroup the item group
	* @return the item group that was updated
	*/
	@Override
	public com.stpl.app.model.ItemGroup updateItemGroup(
		com.stpl.app.model.ItemGroup itemGroup) {
		return _itemGroupLocalService.updateItemGroup(itemGroup);
	}

	@Override
	public ItemGroupLocalService getWrappedService() {
		return _itemGroupLocalService;
	}

	@Override
	public void setWrappedService(ItemGroupLocalService itemGroupLocalService) {
		_itemGroupLocalService = itemGroupLocalService;
	}

	private ItemGroupLocalService _itemGroupLocalService;
}