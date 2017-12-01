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
 * Provides a wrapper for {@link IvldBestPriceLocalService}.
 *
 * @author
 * @see IvldBestPriceLocalService
 * @generated
 */
@ProviderType
public class IvldBestPriceLocalServiceWrapper
	implements IvldBestPriceLocalService,
		ServiceWrapper<IvldBestPriceLocalService> {
	public IvldBestPriceLocalServiceWrapper(
		IvldBestPriceLocalService ivldBestPriceLocalService) {
		_ivldBestPriceLocalService = ivldBestPriceLocalService;
	}

	/**
	* Adds the ivld best price to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldBestPrice the ivld best price
	* @return the ivld best price that was added
	*/
	@Override
	public com.stpl.app.model.IvldBestPrice addIvldBestPrice(
		com.stpl.app.model.IvldBestPrice ivldBestPrice) {
		return _ivldBestPriceLocalService.addIvldBestPrice(ivldBestPrice);
	}

	/**
	* Creates a new ivld best price with the primary key. Does not add the ivld best price to the database.
	*
	* @param ivldBestPriceSid the primary key for the new ivld best price
	* @return the new ivld best price
	*/
	@Override
	public com.stpl.app.model.IvldBestPrice createIvldBestPrice(
		int ivldBestPriceSid) {
		return _ivldBestPriceLocalService.createIvldBestPrice(ivldBestPriceSid);
	}

	/**
	* Deletes the ivld best price with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldBestPriceSid the primary key of the ivld best price
	* @return the ivld best price that was removed
	* @throws PortalException if a ivld best price with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldBestPrice deleteIvldBestPrice(
		int ivldBestPriceSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldBestPriceLocalService.deleteIvldBestPrice(ivldBestPriceSid);
	}

	/**
	* Deletes the ivld best price from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldBestPrice the ivld best price
	* @return the ivld best price that was removed
	*/
	@Override
	public com.stpl.app.model.IvldBestPrice deleteIvldBestPrice(
		com.stpl.app.model.IvldBestPrice ivldBestPrice) {
		return _ivldBestPriceLocalService.deleteIvldBestPrice(ivldBestPrice);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldBestPriceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldBestPriceLocalService.dynamicQuery();
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
		return _ivldBestPriceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldBestPriceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldBestPriceLocalService.dynamicQuery(dynamicQuery, start,
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
		return _ivldBestPriceLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldBestPriceLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.IvldBestPrice fetchIvldBestPrice(
		int ivldBestPriceSid) {
		return _ivldBestPriceLocalService.fetchIvldBestPrice(ivldBestPriceSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldBestPriceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldBestPriceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld best price with the primary key.
	*
	* @param ivldBestPriceSid the primary key of the ivld best price
	* @return the ivld best price
	* @throws PortalException if a ivld best price with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldBestPrice getIvldBestPrice(
		int ivldBestPriceSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldBestPriceLocalService.getIvldBestPrice(ivldBestPriceSid);
	}

	/**
	* Returns a range of all the ivld best prices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld best prices
	* @param end the upper bound of the range of ivld best prices (not inclusive)
	* @return the range of ivld best prices
	*/
	@Override
	public java.util.List<com.stpl.app.model.IvldBestPrice> getIvldBestPrices(
		int start, int end) {
		return _ivldBestPriceLocalService.getIvldBestPrices(start, end);
	}

	/**
	* Returns the number of ivld best prices.
	*
	* @return the number of ivld best prices
	*/
	@Override
	public int getIvldBestPricesCount() {
		return _ivldBestPriceLocalService.getIvldBestPricesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldBestPriceLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldBestPriceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld best price in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldBestPrice the ivld best price
	* @return the ivld best price that was updated
	*/
	@Override
	public com.stpl.app.model.IvldBestPrice updateIvldBestPrice(
		com.stpl.app.model.IvldBestPrice ivldBestPrice) {
		return _ivldBestPriceLocalService.updateIvldBestPrice(ivldBestPrice);
	}

	@Override
	public IvldBestPriceLocalService getWrappedService() {
		return _ivldBestPriceLocalService;
	}

	@Override
	public void setWrappedService(
		IvldBestPriceLocalService ivldBestPriceLocalService) {
		_ivldBestPriceLocalService = ivldBestPriceLocalService;
	}

	private IvldBestPriceLocalService _ivldBestPriceLocalService;
}