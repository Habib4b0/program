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
 * Provides a wrapper for {@link ItemHierarchyMasterLocalService}.
 *
 * @author
 * @see ItemHierarchyMasterLocalService
 * @generated
 */
@ProviderType
public class ItemHierarchyMasterLocalServiceWrapper
	implements ItemHierarchyMasterLocalService,
		ServiceWrapper<ItemHierarchyMasterLocalService> {
	public ItemHierarchyMasterLocalServiceWrapper(
		ItemHierarchyMasterLocalService itemHierarchyMasterLocalService) {
		_itemHierarchyMasterLocalService = itemHierarchyMasterLocalService;
	}

	/**
	* Adds the item hierarchy master to the database. Also notifies the appropriate model listeners.
	*
	* @param itemHierarchyMaster the item hierarchy master
	* @return the item hierarchy master that was added
	*/
	@Override
	public com.stpl.app.model.ItemHierarchyMaster addItemHierarchyMaster(
		com.stpl.app.model.ItemHierarchyMaster itemHierarchyMaster) {
		return _itemHierarchyMasterLocalService.addItemHierarchyMaster(itemHierarchyMaster);
	}

	/**
	* Creates a new item hierarchy master with the primary key. Does not add the item hierarchy master to the database.
	*
	* @param itemHierarchyMasterSid the primary key for the new item hierarchy master
	* @return the new item hierarchy master
	*/
	@Override
	public com.stpl.app.model.ItemHierarchyMaster createItemHierarchyMaster(
		int itemHierarchyMasterSid) {
		return _itemHierarchyMasterLocalService.createItemHierarchyMaster(itemHierarchyMasterSid);
	}

	/**
	* Deletes the item hierarchy master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemHierarchyMasterSid the primary key of the item hierarchy master
	* @return the item hierarchy master that was removed
	* @throws PortalException if a item hierarchy master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemHierarchyMaster deleteItemHierarchyMaster(
		int itemHierarchyMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemHierarchyMasterLocalService.deleteItemHierarchyMaster(itemHierarchyMasterSid);
	}

	/**
	* Deletes the item hierarchy master from the database. Also notifies the appropriate model listeners.
	*
	* @param itemHierarchyMaster the item hierarchy master
	* @return the item hierarchy master that was removed
	*/
	@Override
	public com.stpl.app.model.ItemHierarchyMaster deleteItemHierarchyMaster(
		com.stpl.app.model.ItemHierarchyMaster itemHierarchyMaster) {
		return _itemHierarchyMasterLocalService.deleteItemHierarchyMaster(itemHierarchyMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemHierarchyMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemHierarchyMasterLocalService.dynamicQuery();
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
		return _itemHierarchyMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemHierarchyMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemHierarchyMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _itemHierarchyMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _itemHierarchyMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ItemHierarchyMaster fetchItemHierarchyMaster(
		int itemHierarchyMasterSid) {
		return _itemHierarchyMasterLocalService.fetchItemHierarchyMaster(itemHierarchyMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _itemHierarchyMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _itemHierarchyMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the item hierarchy master with the primary key.
	*
	* @param itemHierarchyMasterSid the primary key of the item hierarchy master
	* @return the item hierarchy master
	* @throws PortalException if a item hierarchy master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemHierarchyMaster getItemHierarchyMaster(
		int itemHierarchyMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemHierarchyMasterLocalService.getItemHierarchyMaster(itemHierarchyMasterSid);
	}

	/**
	* Returns a range of all the item hierarchy masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @return the range of item hierarchy masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.ItemHierarchyMaster> getItemHierarchyMasters(
		int start, int end) {
		return _itemHierarchyMasterLocalService.getItemHierarchyMasters(start,
			end);
	}

	/**
	* Returns the number of item hierarchy masters.
	*
	* @return the number of item hierarchy masters
	*/
	@Override
	public int getItemHierarchyMastersCount() {
		return _itemHierarchyMasterLocalService.getItemHierarchyMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _itemHierarchyMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemHierarchyMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the item hierarchy master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param itemHierarchyMaster the item hierarchy master
	* @return the item hierarchy master that was updated
	*/
	@Override
	public com.stpl.app.model.ItemHierarchyMaster updateItemHierarchyMaster(
		com.stpl.app.model.ItemHierarchyMaster itemHierarchyMaster) {
		return _itemHierarchyMasterLocalService.updateItemHierarchyMaster(itemHierarchyMaster);
	}

	@Override
	public ItemHierarchyMasterLocalService getWrappedService() {
		return _itemHierarchyMasterLocalService;
	}

	@Override
	public void setWrappedService(
		ItemHierarchyMasterLocalService itemHierarchyMasterLocalService) {
		_itemHierarchyMasterLocalService = itemHierarchyMasterLocalService;
	}

	private ItemHierarchyMasterLocalService _itemHierarchyMasterLocalService;
}