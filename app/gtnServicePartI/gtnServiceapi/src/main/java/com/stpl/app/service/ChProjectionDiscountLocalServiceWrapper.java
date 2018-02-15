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
 * Provides a wrapper for {@link ChProjectionDiscountLocalService}.
 *
 * @author
 * @see ChProjectionDiscountLocalService
 * @generated
 */
@ProviderType
public class ChProjectionDiscountLocalServiceWrapper
	implements ChProjectionDiscountLocalService,
		ServiceWrapper<ChProjectionDiscountLocalService> {
	public ChProjectionDiscountLocalServiceWrapper(
		ChProjectionDiscountLocalService chProjectionDiscountLocalService) {
		_chProjectionDiscountLocalService = chProjectionDiscountLocalService;
	}

	/**
	* Adds the ch projection discount to the database. Also notifies the appropriate model listeners.
	*
	* @param chProjectionDiscount the ch projection discount
	* @return the ch projection discount that was added
	*/
	@Override
	public com.stpl.app.model.ChProjectionDiscount addChProjectionDiscount(
		com.stpl.app.model.ChProjectionDiscount chProjectionDiscount) {
		return _chProjectionDiscountLocalService.addChProjectionDiscount(chProjectionDiscount);
	}

	/**
	* Creates a new ch projection discount with the primary key. Does not add the ch projection discount to the database.
	*
	* @param chProjectionDiscountPK the primary key for the new ch projection discount
	* @return the new ch projection discount
	*/
	@Override
	public com.stpl.app.model.ChProjectionDiscount createChProjectionDiscount(
		com.stpl.app.service.persistence.ChProjectionDiscountPK chProjectionDiscountPK) {
		return _chProjectionDiscountLocalService.createChProjectionDiscount(chProjectionDiscountPK);
	}

	/**
	* Deletes the ch projection discount from the database. Also notifies the appropriate model listeners.
	*
	* @param chProjectionDiscount the ch projection discount
	* @return the ch projection discount that was removed
	*/
	@Override
	public com.stpl.app.model.ChProjectionDiscount deleteChProjectionDiscount(
		com.stpl.app.model.ChProjectionDiscount chProjectionDiscount) {
		return _chProjectionDiscountLocalService.deleteChProjectionDiscount(chProjectionDiscount);
	}

	/**
	* Deletes the ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chProjectionDiscountPK the primary key of the ch projection discount
	* @return the ch projection discount that was removed
	* @throws PortalException if a ch projection discount with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ChProjectionDiscount deleteChProjectionDiscount(
		com.stpl.app.service.persistence.ChProjectionDiscountPK chProjectionDiscountPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chProjectionDiscountLocalService.deleteChProjectionDiscount(chProjectionDiscountPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chProjectionDiscountLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _chProjectionDiscountLocalService.dynamicQuery();
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
		return _chProjectionDiscountLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _chProjectionDiscountLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _chProjectionDiscountLocalService.dynamicQuery(dynamicQuery,
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
		return _chProjectionDiscountLocalService.dynamicQueryCount(dynamicQuery);
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
		return _chProjectionDiscountLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ChProjectionDiscount fetchChProjectionDiscount(
		com.stpl.app.service.persistence.ChProjectionDiscountPK chProjectionDiscountPK) {
		return _chProjectionDiscountLocalService.fetchChProjectionDiscount(chProjectionDiscountPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _chProjectionDiscountLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the ch projection discount with the primary key.
	*
	* @param chProjectionDiscountPK the primary key of the ch projection discount
	* @return the ch projection discount
	* @throws PortalException if a ch projection discount with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ChProjectionDiscount getChProjectionDiscount(
		com.stpl.app.service.persistence.ChProjectionDiscountPK chProjectionDiscountPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chProjectionDiscountLocalService.getChProjectionDiscount(chProjectionDiscountPK);
	}

	/**
	* Returns a range of all the ch projection discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection discounts
	* @param end the upper bound of the range of ch projection discounts (not inclusive)
	* @return the range of ch projection discounts
	*/
	@Override
	public java.util.List<com.stpl.app.model.ChProjectionDiscount> getChProjectionDiscounts(
		int start, int end) {
		return _chProjectionDiscountLocalService.getChProjectionDiscounts(start,
			end);
	}

	/**
	* Returns the number of ch projection discounts.
	*
	* @return the number of ch projection discounts
	*/
	@Override
	public int getChProjectionDiscountsCount() {
		return _chProjectionDiscountLocalService.getChProjectionDiscountsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _chProjectionDiscountLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _chProjectionDiscountLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chProjectionDiscountLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ch projection discount in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param chProjectionDiscount the ch projection discount
	* @return the ch projection discount that was updated
	*/
	@Override
	public com.stpl.app.model.ChProjectionDiscount updateChProjectionDiscount(
		com.stpl.app.model.ChProjectionDiscount chProjectionDiscount) {
		return _chProjectionDiscountLocalService.updateChProjectionDiscount(chProjectionDiscount);
	}

	@Override
	public ChProjectionDiscountLocalService getWrappedService() {
		return _chProjectionDiscountLocalService;
	}

	@Override
	public void setWrappedService(
		ChProjectionDiscountLocalService chProjectionDiscountLocalService) {
		_chProjectionDiscountLocalService = chProjectionDiscountLocalService;
	}

	private ChProjectionDiscountLocalService _chProjectionDiscountLocalService;
}