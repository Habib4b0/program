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
 * Provides a wrapper for {@link ItemMasterLocalService}.
 *
 * @author
 * @see ItemMasterLocalService
 * @generated
 */
@ProviderType
public class ItemMasterLocalServiceWrapper implements ItemMasterLocalService,
	ServiceWrapper<ItemMasterLocalService> {
	public ItemMasterLocalServiceWrapper(
		ItemMasterLocalService itemMasterLocalService) {
		_itemMasterLocalService = itemMasterLocalService;
	}

	/**
	* Adds the item master to the database. Also notifies the appropriate model listeners.
	*
	* @param itemMaster the item master
	* @return the item master that was added
	*/
	@Override
	public com.stpl.app.model.ItemMaster addItemMaster(
		com.stpl.app.model.ItemMaster itemMaster) {
		return _itemMasterLocalService.addItemMaster(itemMaster);
	}

	/**
	* Creates a new item master with the primary key. Does not add the item master to the database.
	*
	* @param itemMasterSid the primary key for the new item master
	* @return the new item master
	*/
	@Override
	public com.stpl.app.model.ItemMaster createItemMaster(int itemMasterSid) {
		return _itemMasterLocalService.createItemMaster(itemMasterSid);
	}

	/**
	* Deletes the item master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemMasterSid the primary key of the item master
	* @return the item master that was removed
	* @throws PortalException if a item master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemMaster deleteItemMaster(int itemMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemMasterLocalService.deleteItemMaster(itemMasterSid);
	}

	/**
	* Deletes the item master from the database. Also notifies the appropriate model listeners.
	*
	* @param itemMaster the item master
	* @return the item master that was removed
	*/
	@Override
	public com.stpl.app.model.ItemMaster deleteItemMaster(
		com.stpl.app.model.ItemMaster itemMaster) {
		return _itemMasterLocalService.deleteItemMaster(itemMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemMasterLocalService.dynamicQuery();
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
		return _itemMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemMasterLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _itemMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _itemMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ItemMaster fetchItemMaster(int itemMasterSid) {
		return _itemMasterLocalService.fetchItemMaster(itemMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _itemMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _itemMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the item master with the primary key.
	*
	* @param itemMasterSid the primary key of the item master
	* @return the item master
	* @throws PortalException if a item master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemMaster getItemMaster(int itemMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemMasterLocalService.getItemMaster(itemMasterSid);
	}

	/**
	* Returns a range of all the item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item masters
	* @param end the upper bound of the range of item masters (not inclusive)
	* @return the range of item masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.ItemMaster> getItemMasters(
		int start, int end) {
		return _itemMasterLocalService.getItemMasters(start, end);
	}

	/**
	* Returns the number of item masters.
	*
	* @return the number of item masters
	*/
	@Override
	public int getItemMastersCount() {
		return _itemMasterLocalService.getItemMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _itemMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the item master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param itemMaster the item master
	* @return the item master that was updated
	*/
	@Override
	public com.stpl.app.model.ItemMaster updateItemMaster(
		com.stpl.app.model.ItemMaster itemMaster) {
		return _itemMasterLocalService.updateItemMaster(itemMaster);
	}

	@Override
	public ItemMasterLocalService getWrappedService() {
		return _itemMasterLocalService;
	}

	@Override
	public void setWrappedService(ItemMasterLocalService itemMasterLocalService) {
		_itemMasterLocalService = itemMasterLocalService;
	}

	private ItemMasterLocalService _itemMasterLocalService;
}