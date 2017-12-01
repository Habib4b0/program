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
 * Provides a wrapper for {@link StNmActualDiscountLocalService}.
 *
 * @author
 * @see StNmActualDiscountLocalService
 * @generated
 */
@ProviderType
public class StNmActualDiscountLocalServiceWrapper
	implements StNmActualDiscountLocalService,
		ServiceWrapper<StNmActualDiscountLocalService> {
	public StNmActualDiscountLocalServiceWrapper(
		StNmActualDiscountLocalService stNmActualDiscountLocalService) {
		_stNmActualDiscountLocalService = stNmActualDiscountLocalService;
	}

	/**
	* Adds the st nm actual discount to the database. Also notifies the appropriate model listeners.
	*
	* @param stNmActualDiscount the st nm actual discount
	* @return the st nm actual discount that was added
	*/
	@Override
	public com.stpl.app.model.StNmActualDiscount addStNmActualDiscount(
		com.stpl.app.model.StNmActualDiscount stNmActualDiscount) {
		return _stNmActualDiscountLocalService.addStNmActualDiscount(stNmActualDiscount);
	}

	/**
	* Creates a new st nm actual discount with the primary key. Does not add the st nm actual discount to the database.
	*
	* @param stNmActualDiscountPK the primary key for the new st nm actual discount
	* @return the new st nm actual discount
	*/
	@Override
	public com.stpl.app.model.StNmActualDiscount createStNmActualDiscount(
		com.stpl.app.service.persistence.StNmActualDiscountPK stNmActualDiscountPK) {
		return _stNmActualDiscountLocalService.createStNmActualDiscount(stNmActualDiscountPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmActualDiscountLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st nm actual discount from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmActualDiscount the st nm actual discount
	* @return the st nm actual discount that was removed
	*/
	@Override
	public com.stpl.app.model.StNmActualDiscount deleteStNmActualDiscount(
		com.stpl.app.model.StNmActualDiscount stNmActualDiscount) {
		return _stNmActualDiscountLocalService.deleteStNmActualDiscount(stNmActualDiscount);
	}

	/**
	* Deletes the st nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmActualDiscountPK the primary key of the st nm actual discount
	* @return the st nm actual discount that was removed
	* @throws PortalException if a st nm actual discount with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmActualDiscount deleteStNmActualDiscount(
		com.stpl.app.service.persistence.StNmActualDiscountPK stNmActualDiscountPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmActualDiscountLocalService.deleteStNmActualDiscount(stNmActualDiscountPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stNmActualDiscountLocalService.dynamicQuery();
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
		return _stNmActualDiscountLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmActualDiscountLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmActualDiscountLocalService.dynamicQuery(dynamicQuery,
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
		return _stNmActualDiscountLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stNmActualDiscountLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StNmActualDiscount fetchStNmActualDiscount(
		com.stpl.app.service.persistence.StNmActualDiscountPK stNmActualDiscountPK) {
		return _stNmActualDiscountLocalService.fetchStNmActualDiscount(stNmActualDiscountPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stNmActualDiscountLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stNmActualDiscountLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stNmActualDiscountLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmActualDiscountLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st nm actual discount with the primary key.
	*
	* @param stNmActualDiscountPK the primary key of the st nm actual discount
	* @return the st nm actual discount
	* @throws PortalException if a st nm actual discount with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmActualDiscount getStNmActualDiscount(
		com.stpl.app.service.persistence.StNmActualDiscountPK stNmActualDiscountPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmActualDiscountLocalService.getStNmActualDiscount(stNmActualDiscountPK);
	}

	/**
	* Returns a range of all the st nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual discounts
	* @param end the upper bound of the range of st nm actual discounts (not inclusive)
	* @return the range of st nm actual discounts
	*/
	@Override
	public java.util.List<com.stpl.app.model.StNmActualDiscount> getStNmActualDiscounts(
		int start, int end) {
		return _stNmActualDiscountLocalService.getStNmActualDiscounts(start, end);
	}

	/**
	* Returns the number of st nm actual discounts.
	*
	* @return the number of st nm actual discounts
	*/
	@Override
	public int getStNmActualDiscountsCount() {
		return _stNmActualDiscountLocalService.getStNmActualDiscountsCount();
	}

	/**
	* Updates the st nm actual discount in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stNmActualDiscount the st nm actual discount
	* @return the st nm actual discount that was updated
	*/
	@Override
	public com.stpl.app.model.StNmActualDiscount updateStNmActualDiscount(
		com.stpl.app.model.StNmActualDiscount stNmActualDiscount) {
		return _stNmActualDiscountLocalService.updateStNmActualDiscount(stNmActualDiscount);
	}

	@Override
	public StNmActualDiscountLocalService getWrappedService() {
		return _stNmActualDiscountLocalService;
	}

	@Override
	public void setWrappedService(
		StNmActualDiscountLocalService stNmActualDiscountLocalService) {
		_stNmActualDiscountLocalService = stNmActualDiscountLocalService;
	}

	private StNmActualDiscountLocalService _stNmActualDiscountLocalService;
}