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
 * Provides a wrapper for {@link ItemQualifierLocalService}.
 *
 * @author
 * @see ItemQualifierLocalService
 * @generated
 */
@ProviderType
public class ItemQualifierLocalServiceWrapper
	implements ItemQualifierLocalService,
		ServiceWrapper<ItemQualifierLocalService> {
	public ItemQualifierLocalServiceWrapper(
		ItemQualifierLocalService itemQualifierLocalService) {
		_itemQualifierLocalService = itemQualifierLocalService;
	}

	/**
	* Adds the item qualifier to the database. Also notifies the appropriate model listeners.
	*
	* @param itemQualifier the item qualifier
	* @return the item qualifier that was added
	*/
	@Override
	public com.stpl.app.model.ItemQualifier addItemQualifier(
		com.stpl.app.model.ItemQualifier itemQualifier) {
		return _itemQualifierLocalService.addItemQualifier(itemQualifier);
	}

	/**
	* Creates a new item qualifier with the primary key. Does not add the item qualifier to the database.
	*
	* @param itemQualifierSid the primary key for the new item qualifier
	* @return the new item qualifier
	*/
	@Override
	public com.stpl.app.model.ItemQualifier createItemQualifier(
		int itemQualifierSid) {
		return _itemQualifierLocalService.createItemQualifier(itemQualifierSid);
	}

	/**
	* Deletes the item qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemQualifierSid the primary key of the item qualifier
	* @return the item qualifier that was removed
	* @throws PortalException if a item qualifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemQualifier deleteItemQualifier(
		int itemQualifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemQualifierLocalService.deleteItemQualifier(itemQualifierSid);
	}

	/**
	* Deletes the item qualifier from the database. Also notifies the appropriate model listeners.
	*
	* @param itemQualifier the item qualifier
	* @return the item qualifier that was removed
	*/
	@Override
	public com.stpl.app.model.ItemQualifier deleteItemQualifier(
		com.stpl.app.model.ItemQualifier itemQualifier) {
		return _itemQualifierLocalService.deleteItemQualifier(itemQualifier);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemQualifierLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemQualifierLocalService.dynamicQuery();
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
		return _itemQualifierLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemQualifierLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemQualifierLocalService.dynamicQuery(dynamicQuery, start,
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
		return _itemQualifierLocalService.dynamicQueryCount(dynamicQuery);
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
		return _itemQualifierLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ItemQualifier fetchItemQualifier(
		int itemQualifierSid) {
		return _itemQualifierLocalService.fetchItemQualifier(itemQualifierSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _itemQualifierLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _itemQualifierLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the item qualifier with the primary key.
	*
	* @param itemQualifierSid the primary key of the item qualifier
	* @return the item qualifier
	* @throws PortalException if a item qualifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemQualifier getItemQualifier(
		int itemQualifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemQualifierLocalService.getItemQualifier(itemQualifierSid);
	}

	/**
	* Returns a range of all the item qualifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item qualifiers
	* @param end the upper bound of the range of item qualifiers (not inclusive)
	* @return the range of item qualifiers
	*/
	@Override
	public java.util.List<com.stpl.app.model.ItemQualifier> getItemQualifiers(
		int start, int end) {
		return _itemQualifierLocalService.getItemQualifiers(start, end);
	}

	/**
	* Returns the number of item qualifiers.
	*
	* @return the number of item qualifiers
	*/
	@Override
	public int getItemQualifiersCount() {
		return _itemQualifierLocalService.getItemQualifiersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _itemQualifierLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemQualifierLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the item qualifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param itemQualifier the item qualifier
	* @return the item qualifier that was updated
	*/
	@Override
	public com.stpl.app.model.ItemQualifier updateItemQualifier(
		com.stpl.app.model.ItemQualifier itemQualifier) {
		return _itemQualifierLocalService.updateItemQualifier(itemQualifier);
	}

	@Override
	public ItemQualifierLocalService getWrappedService() {
		return _itemQualifierLocalService;
	}

	@Override
	public void setWrappedService(
		ItemQualifierLocalService itemQualifierLocalService) {
		_itemQualifierLocalService = itemQualifierLocalService;
	}

	private ItemQualifierLocalService _itemQualifierLocalService;
}