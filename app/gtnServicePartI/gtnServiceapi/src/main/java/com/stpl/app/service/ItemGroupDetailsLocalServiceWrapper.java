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
 * Provides a wrapper for {@link ItemGroupDetailsLocalService}.
 *
 * @author
 * @see ItemGroupDetailsLocalService
 * @generated
 */
@ProviderType
public class ItemGroupDetailsLocalServiceWrapper
	implements ItemGroupDetailsLocalService,
		ServiceWrapper<ItemGroupDetailsLocalService> {
	public ItemGroupDetailsLocalServiceWrapper(
		ItemGroupDetailsLocalService itemGroupDetailsLocalService) {
		_itemGroupDetailsLocalService = itemGroupDetailsLocalService;
	}

	/**
	* Adds the item group details to the database. Also notifies the appropriate model listeners.
	*
	* @param itemGroupDetails the item group details
	* @return the item group details that was added
	*/
	@Override
	public com.stpl.app.model.ItemGroupDetails addItemGroupDetails(
		com.stpl.app.model.ItemGroupDetails itemGroupDetails) {
		return _itemGroupDetailsLocalService.addItemGroupDetails(itemGroupDetails);
	}

	/**
	* Creates a new item group details with the primary key. Does not add the item group details to the database.
	*
	* @param itemGroupDetailsSid the primary key for the new item group details
	* @return the new item group details
	*/
	@Override
	public com.stpl.app.model.ItemGroupDetails createItemGroupDetails(
		int itemGroupDetailsSid) {
		return _itemGroupDetailsLocalService.createItemGroupDetails(itemGroupDetailsSid);
	}

	/**
	* Deletes the item group details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemGroupDetailsSid the primary key of the item group details
	* @return the item group details that was removed
	* @throws PortalException if a item group details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemGroupDetails deleteItemGroupDetails(
		int itemGroupDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemGroupDetailsLocalService.deleteItemGroupDetails(itemGroupDetailsSid);
	}

	/**
	* Deletes the item group details from the database. Also notifies the appropriate model listeners.
	*
	* @param itemGroupDetails the item group details
	* @return the item group details that was removed
	*/
	@Override
	public com.stpl.app.model.ItemGroupDetails deleteItemGroupDetails(
		com.stpl.app.model.ItemGroupDetails itemGroupDetails) {
		return _itemGroupDetailsLocalService.deleteItemGroupDetails(itemGroupDetails);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemGroupDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemGroupDetailsLocalService.dynamicQuery();
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
		return _itemGroupDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemGroupDetailsLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemGroupDetailsLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _itemGroupDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _itemGroupDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ItemGroupDetails fetchItemGroupDetails(
		int itemGroupDetailsSid) {
		return _itemGroupDetailsLocalService.fetchItemGroupDetails(itemGroupDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _itemGroupDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _itemGroupDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the item group details with the primary key.
	*
	* @param itemGroupDetailsSid the primary key of the item group details
	* @return the item group details
	* @throws PortalException if a item group details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemGroupDetails getItemGroupDetails(
		int itemGroupDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemGroupDetailsLocalService.getItemGroupDetails(itemGroupDetailsSid);
	}

	/**
	* Returns a range of all the item group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item group detailses
	* @param end the upper bound of the range of item group detailses (not inclusive)
	* @return the range of item group detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.ItemGroupDetails> getItemGroupDetailses(
		int start, int end) {
		return _itemGroupDetailsLocalService.getItemGroupDetailses(start, end);
	}

	/**
	* Returns the number of item group detailses.
	*
	* @return the number of item group detailses
	*/
	@Override
	public int getItemGroupDetailsesCount() {
		return _itemGroupDetailsLocalService.getItemGroupDetailsesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _itemGroupDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemGroupDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the item group details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param itemGroupDetails the item group details
	* @return the item group details that was updated
	*/
	@Override
	public com.stpl.app.model.ItemGroupDetails updateItemGroupDetails(
		com.stpl.app.model.ItemGroupDetails itemGroupDetails) {
		return _itemGroupDetailsLocalService.updateItemGroupDetails(itemGroupDetails);
	}

	@Override
	public ItemGroupDetailsLocalService getWrappedService() {
		return _itemGroupDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		ItemGroupDetailsLocalService itemGroupDetailsLocalService) {
		_itemGroupDetailsLocalService = itemGroupDetailsLocalService;
	}

	private ItemGroupDetailsLocalService _itemGroupDetailsLocalService;
}