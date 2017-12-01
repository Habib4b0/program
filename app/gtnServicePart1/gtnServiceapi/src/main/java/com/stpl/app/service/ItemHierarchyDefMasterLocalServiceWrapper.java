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
 * Provides a wrapper for {@link ItemHierarchyDefMasterLocalService}.
 *
 * @author
 * @see ItemHierarchyDefMasterLocalService
 * @generated
 */
@ProviderType
public class ItemHierarchyDefMasterLocalServiceWrapper
	implements ItemHierarchyDefMasterLocalService,
		ServiceWrapper<ItemHierarchyDefMasterLocalService> {
	public ItemHierarchyDefMasterLocalServiceWrapper(
		ItemHierarchyDefMasterLocalService itemHierarchyDefMasterLocalService) {
		_itemHierarchyDefMasterLocalService = itemHierarchyDefMasterLocalService;
	}

	/**
	* Adds the item hierarchy def master to the database. Also notifies the appropriate model listeners.
	*
	* @param itemHierarchyDefMaster the item hierarchy def master
	* @return the item hierarchy def master that was added
	*/
	@Override
	public com.stpl.app.model.ItemHierarchyDefMaster addItemHierarchyDefMaster(
		com.stpl.app.model.ItemHierarchyDefMaster itemHierarchyDefMaster) {
		return _itemHierarchyDefMasterLocalService.addItemHierarchyDefMaster(itemHierarchyDefMaster);
	}

	/**
	* Creates a new item hierarchy def master with the primary key. Does not add the item hierarchy def master to the database.
	*
	* @param itemHierarchyDefMasterSid the primary key for the new item hierarchy def master
	* @return the new item hierarchy def master
	*/
	@Override
	public com.stpl.app.model.ItemHierarchyDefMaster createItemHierarchyDefMaster(
		int itemHierarchyDefMasterSid) {
		return _itemHierarchyDefMasterLocalService.createItemHierarchyDefMaster(itemHierarchyDefMasterSid);
	}

	/**
	* Deletes the item hierarchy def master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
	* @return the item hierarchy def master that was removed
	* @throws PortalException if a item hierarchy def master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemHierarchyDefMaster deleteItemHierarchyDefMaster(
		int itemHierarchyDefMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemHierarchyDefMasterLocalService.deleteItemHierarchyDefMaster(itemHierarchyDefMasterSid);
	}

	/**
	* Deletes the item hierarchy def master from the database. Also notifies the appropriate model listeners.
	*
	* @param itemHierarchyDefMaster the item hierarchy def master
	* @return the item hierarchy def master that was removed
	*/
	@Override
	public com.stpl.app.model.ItemHierarchyDefMaster deleteItemHierarchyDefMaster(
		com.stpl.app.model.ItemHierarchyDefMaster itemHierarchyDefMaster) {
		return _itemHierarchyDefMasterLocalService.deleteItemHierarchyDefMaster(itemHierarchyDefMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemHierarchyDefMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemHierarchyDefMasterLocalService.dynamicQuery();
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
		return _itemHierarchyDefMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemHierarchyDefMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemHierarchyDefMasterLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _itemHierarchyDefMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _itemHierarchyDefMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ItemHierarchyDefMaster fetchItemHierarchyDefMaster(
		int itemHierarchyDefMasterSid) {
		return _itemHierarchyDefMasterLocalService.fetchItemHierarchyDefMaster(itemHierarchyDefMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _itemHierarchyDefMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _itemHierarchyDefMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the item hierarchy def master with the primary key.
	*
	* @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
	* @return the item hierarchy def master
	* @throws PortalException if a item hierarchy def master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemHierarchyDefMaster getItemHierarchyDefMaster(
		int itemHierarchyDefMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemHierarchyDefMasterLocalService.getItemHierarchyDefMaster(itemHierarchyDefMasterSid);
	}

	/**
	* Returns a range of all the item hierarchy def masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @return the range of item hierarchy def masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> getItemHierarchyDefMasters(
		int start, int end) {
		return _itemHierarchyDefMasterLocalService.getItemHierarchyDefMasters(start,
			end);
	}

	/**
	* Returns the number of item hierarchy def masters.
	*
	* @return the number of item hierarchy def masters
	*/
	@Override
	public int getItemHierarchyDefMastersCount() {
		return _itemHierarchyDefMasterLocalService.getItemHierarchyDefMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _itemHierarchyDefMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemHierarchyDefMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the item hierarchy def master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param itemHierarchyDefMaster the item hierarchy def master
	* @return the item hierarchy def master that was updated
	*/
	@Override
	public com.stpl.app.model.ItemHierarchyDefMaster updateItemHierarchyDefMaster(
		com.stpl.app.model.ItemHierarchyDefMaster itemHierarchyDefMaster) {
		return _itemHierarchyDefMasterLocalService.updateItemHierarchyDefMaster(itemHierarchyDefMaster);
	}

	@Override
	public ItemHierarchyDefMasterLocalService getWrappedService() {
		return _itemHierarchyDefMasterLocalService;
	}

	@Override
	public void setWrappedService(
		ItemHierarchyDefMasterLocalService itemHierarchyDefMasterLocalService) {
		_itemHierarchyDefMasterLocalService = itemHierarchyDefMasterLocalService;
	}

	private ItemHierarchyDefMasterLocalService _itemHierarchyDefMasterLocalService;
}