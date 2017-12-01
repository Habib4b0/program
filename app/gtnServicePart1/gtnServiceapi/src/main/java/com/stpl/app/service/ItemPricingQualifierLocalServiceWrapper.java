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
 * Provides a wrapper for {@link ItemPricingQualifierLocalService}.
 *
 * @author
 * @see ItemPricingQualifierLocalService
 * @generated
 */
@ProviderType
public class ItemPricingQualifierLocalServiceWrapper
	implements ItemPricingQualifierLocalService,
		ServiceWrapper<ItemPricingQualifierLocalService> {
	public ItemPricingQualifierLocalServiceWrapper(
		ItemPricingQualifierLocalService itemPricingQualifierLocalService) {
		_itemPricingQualifierLocalService = itemPricingQualifierLocalService;
	}

	/**
	* Adds the item pricing qualifier to the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricingQualifier the item pricing qualifier
	* @return the item pricing qualifier that was added
	*/
	@Override
	public com.stpl.app.model.ItemPricingQualifier addItemPricingQualifier(
		com.stpl.app.model.ItemPricingQualifier itemPricingQualifier) {
		return _itemPricingQualifierLocalService.addItemPricingQualifier(itemPricingQualifier);
	}

	/**
	* Creates a new item pricing qualifier with the primary key. Does not add the item pricing qualifier to the database.
	*
	* @param itemPricingQualifierSid the primary key for the new item pricing qualifier
	* @return the new item pricing qualifier
	*/
	@Override
	public com.stpl.app.model.ItemPricingQualifier createItemPricingQualifier(
		int itemPricingQualifierSid) {
		return _itemPricingQualifierLocalService.createItemPricingQualifier(itemPricingQualifierSid);
	}

	/**
	* Deletes the item pricing qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricingQualifierSid the primary key of the item pricing qualifier
	* @return the item pricing qualifier that was removed
	* @throws PortalException if a item pricing qualifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemPricingQualifier deleteItemPricingQualifier(
		int itemPricingQualifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemPricingQualifierLocalService.deleteItemPricingQualifier(itemPricingQualifierSid);
	}

	/**
	* Deletes the item pricing qualifier from the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricingQualifier the item pricing qualifier
	* @return the item pricing qualifier that was removed
	*/
	@Override
	public com.stpl.app.model.ItemPricingQualifier deleteItemPricingQualifier(
		com.stpl.app.model.ItemPricingQualifier itemPricingQualifier) {
		return _itemPricingQualifierLocalService.deleteItemPricingQualifier(itemPricingQualifier);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemPricingQualifierLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemPricingQualifierLocalService.dynamicQuery();
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
		return _itemPricingQualifierLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemPricingQualifierLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemPricingQualifierLocalService.dynamicQuery(dynamicQuery,
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
		return _itemPricingQualifierLocalService.dynamicQueryCount(dynamicQuery);
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
		return _itemPricingQualifierLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ItemPricingQualifier fetchItemPricingQualifier(
		int itemPricingQualifierSid) {
		return _itemPricingQualifierLocalService.fetchItemPricingQualifier(itemPricingQualifierSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _itemPricingQualifierLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _itemPricingQualifierLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the item pricing qualifier with the primary key.
	*
	* @param itemPricingQualifierSid the primary key of the item pricing qualifier
	* @return the item pricing qualifier
	* @throws PortalException if a item pricing qualifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemPricingQualifier getItemPricingQualifier(
		int itemPricingQualifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemPricingQualifierLocalService.getItemPricingQualifier(itemPricingQualifierSid);
	}

	/**
	* Returns a range of all the item pricing qualifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item pricing qualifiers
	* @param end the upper bound of the range of item pricing qualifiers (not inclusive)
	* @return the range of item pricing qualifiers
	*/
	@Override
	public java.util.List<com.stpl.app.model.ItemPricingQualifier> getItemPricingQualifiers(
		int start, int end) {
		return _itemPricingQualifierLocalService.getItemPricingQualifiers(start,
			end);
	}

	/**
	* Returns the number of item pricing qualifiers.
	*
	* @return the number of item pricing qualifiers
	*/
	@Override
	public int getItemPricingQualifiersCount() {
		return _itemPricingQualifierLocalService.getItemPricingQualifiersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _itemPricingQualifierLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemPricingQualifierLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the item pricing qualifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param itemPricingQualifier the item pricing qualifier
	* @return the item pricing qualifier that was updated
	*/
	@Override
	public com.stpl.app.model.ItemPricingQualifier updateItemPricingQualifier(
		com.stpl.app.model.ItemPricingQualifier itemPricingQualifier) {
		return _itemPricingQualifierLocalService.updateItemPricingQualifier(itemPricingQualifier);
	}

	@Override
	public ItemPricingQualifierLocalService getWrappedService() {
		return _itemPricingQualifierLocalService;
	}

	@Override
	public void setWrappedService(
		ItemPricingQualifierLocalService itemPricingQualifierLocalService) {
		_itemPricingQualifierLocalService = itemPricingQualifierLocalService;
	}

	private ItemPricingQualifierLocalService _itemPricingQualifierLocalService;
}