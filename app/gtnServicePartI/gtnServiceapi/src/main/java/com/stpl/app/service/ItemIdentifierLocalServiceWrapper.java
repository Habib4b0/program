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
 * Provides a wrapper for {@link ItemIdentifierLocalService}.
 *
 * @author
 * @see ItemIdentifierLocalService
 * @generated
 */
@ProviderType
public class ItemIdentifierLocalServiceWrapper
	implements ItemIdentifierLocalService,
		ServiceWrapper<ItemIdentifierLocalService> {
	public ItemIdentifierLocalServiceWrapper(
		ItemIdentifierLocalService itemIdentifierLocalService) {
		_itemIdentifierLocalService = itemIdentifierLocalService;
	}

	/**
	* Adds the item identifier to the database. Also notifies the appropriate model listeners.
	*
	* @param itemIdentifier the item identifier
	* @return the item identifier that was added
	*/
	@Override
	public com.stpl.app.model.ItemIdentifier addItemIdentifier(
		com.stpl.app.model.ItemIdentifier itemIdentifier) {
		return _itemIdentifierLocalService.addItemIdentifier(itemIdentifier);
	}

	/**
	* Creates a new item identifier with the primary key. Does not add the item identifier to the database.
	*
	* @param itemIdentifierSid the primary key for the new item identifier
	* @return the new item identifier
	*/
	@Override
	public com.stpl.app.model.ItemIdentifier createItemIdentifier(
		int itemIdentifierSid) {
		return _itemIdentifierLocalService.createItemIdentifier(itemIdentifierSid);
	}

	/**
	* Deletes the item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemIdentifierSid the primary key of the item identifier
	* @return the item identifier that was removed
	* @throws PortalException if a item identifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemIdentifier deleteItemIdentifier(
		int itemIdentifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemIdentifierLocalService.deleteItemIdentifier(itemIdentifierSid);
	}

	/**
	* Deletes the item identifier from the database. Also notifies the appropriate model listeners.
	*
	* @param itemIdentifier the item identifier
	* @return the item identifier that was removed
	*/
	@Override
	public com.stpl.app.model.ItemIdentifier deleteItemIdentifier(
		com.stpl.app.model.ItemIdentifier itemIdentifier) {
		return _itemIdentifierLocalService.deleteItemIdentifier(itemIdentifier);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemIdentifierLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemIdentifierLocalService.dynamicQuery();
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
		return _itemIdentifierLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemIdentifierLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemIdentifierLocalService.dynamicQuery(dynamicQuery, start,
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
		return _itemIdentifierLocalService.dynamicQueryCount(dynamicQuery);
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
		return _itemIdentifierLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ItemIdentifier fetchItemIdentifier(
		int itemIdentifierSid) {
		return _itemIdentifierLocalService.fetchItemIdentifier(itemIdentifierSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _itemIdentifierLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _itemIdentifierLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the item identifier with the primary key.
	*
	* @param itemIdentifierSid the primary key of the item identifier
	* @return the item identifier
	* @throws PortalException if a item identifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemIdentifier getItemIdentifier(
		int itemIdentifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemIdentifierLocalService.getItemIdentifier(itemIdentifierSid);
	}

	/**
	* Returns a range of all the item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item identifiers
	* @param end the upper bound of the range of item identifiers (not inclusive)
	* @return the range of item identifiers
	*/
	@Override
	public java.util.List<com.stpl.app.model.ItemIdentifier> getItemIdentifiers(
		int start, int end) {
		return _itemIdentifierLocalService.getItemIdentifiers(start, end);
	}

	/**
	* Returns the number of item identifiers.
	*
	* @return the number of item identifiers
	*/
	@Override
	public int getItemIdentifiersCount() {
		return _itemIdentifierLocalService.getItemIdentifiersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _itemIdentifierLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemIdentifierLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the item identifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param itemIdentifier the item identifier
	* @return the item identifier that was updated
	*/
	@Override
	public com.stpl.app.model.ItemIdentifier updateItemIdentifier(
		com.stpl.app.model.ItemIdentifier itemIdentifier) {
		return _itemIdentifierLocalService.updateItemIdentifier(itemIdentifier);
	}

	@Override
	public ItemIdentifierLocalService getWrappedService() {
		return _itemIdentifierLocalService;
	}

	@Override
	public void setWrappedService(
		ItemIdentifierLocalService itemIdentifierLocalService) {
		_itemIdentifierLocalService = itemIdentifierLocalService;
	}

	private ItemIdentifierLocalService _itemIdentifierLocalService;
}