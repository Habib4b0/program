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
 * Provides a wrapper for {@link ItemPricingLocalService}.
 *
 * @author
 * @see ItemPricingLocalService
 * @generated
 */
@ProviderType
public class ItemPricingLocalServiceWrapper implements ItemPricingLocalService,
	ServiceWrapper<ItemPricingLocalService> {
	public ItemPricingLocalServiceWrapper(
		ItemPricingLocalService itemPricingLocalService) {
		_itemPricingLocalService = itemPricingLocalService;
	}

	/**
	* Adds the item pricing to the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricing the item pricing
	* @return the item pricing that was added
	*/
	@Override
	public com.stpl.app.model.ItemPricing addItemPricing(
		com.stpl.app.model.ItemPricing itemPricing) {
		return _itemPricingLocalService.addItemPricing(itemPricing);
	}

	/**
	* Creates a new item pricing with the primary key. Does not add the item pricing to the database.
	*
	* @param itemPricingSid the primary key for the new item pricing
	* @return the new item pricing
	*/
	@Override
	public com.stpl.app.model.ItemPricing createItemPricing(int itemPricingSid) {
		return _itemPricingLocalService.createItemPricing(itemPricingSid);
	}

	/**
	* Deletes the item pricing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricingSid the primary key of the item pricing
	* @return the item pricing that was removed
	* @throws PortalException if a item pricing with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemPricing deleteItemPricing(int itemPricingSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemPricingLocalService.deleteItemPricing(itemPricingSid);
	}

	/**
	* Deletes the item pricing from the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricing the item pricing
	* @return the item pricing that was removed
	*/
	@Override
	public com.stpl.app.model.ItemPricing deleteItemPricing(
		com.stpl.app.model.ItemPricing itemPricing) {
		return _itemPricingLocalService.deleteItemPricing(itemPricing);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemPricingLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itemPricingLocalService.dynamicQuery();
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
		return _itemPricingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemPricingLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _itemPricingLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _itemPricingLocalService.dynamicQueryCount(dynamicQuery);
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
		return _itemPricingLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ItemPricing fetchItemPricing(int itemPricingSid) {
		return _itemPricingLocalService.fetchItemPricing(itemPricingSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _itemPricingLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _itemPricingLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the item pricing with the primary key.
	*
	* @param itemPricingSid the primary key of the item pricing
	* @return the item pricing
	* @throws PortalException if a item pricing with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ItemPricing getItemPricing(int itemPricingSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemPricingLocalService.getItemPricing(itemPricingSid);
	}

	/**
	* Returns a range of all the item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item pricings
	* @param end the upper bound of the range of item pricings (not inclusive)
	* @return the range of item pricings
	*/
	@Override
	public java.util.List<com.stpl.app.model.ItemPricing> getItemPricings(
		int start, int end) {
		return _itemPricingLocalService.getItemPricings(start, end);
	}

	/**
	* Returns the number of item pricings.
	*
	* @return the number of item pricings
	*/
	@Override
	public int getItemPricingsCount() {
		return _itemPricingLocalService.getItemPricingsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _itemPricingLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _itemPricingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the item pricing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param itemPricing the item pricing
	* @return the item pricing that was updated
	*/
	@Override
	public com.stpl.app.model.ItemPricing updateItemPricing(
		com.stpl.app.model.ItemPricing itemPricing) {
		return _itemPricingLocalService.updateItemPricing(itemPricing);
	}

	@Override
	public ItemPricingLocalService getWrappedService() {
		return _itemPricingLocalService;
	}

	@Override
	public void setWrappedService(
		ItemPricingLocalService itemPricingLocalService) {
		_itemPricingLocalService = itemPricingLocalService;
	}

	private ItemPricingLocalService _itemPricingLocalService;
}