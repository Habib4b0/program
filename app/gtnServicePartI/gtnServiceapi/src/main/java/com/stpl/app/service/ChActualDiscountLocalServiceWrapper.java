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
 * Provides a wrapper for {@link ChActualDiscountLocalService}.
 *
 * @author
 * @see ChActualDiscountLocalService
 * @generated
 */
@ProviderType
public class ChActualDiscountLocalServiceWrapper
	implements ChActualDiscountLocalService,
		ServiceWrapper<ChActualDiscountLocalService> {
	public ChActualDiscountLocalServiceWrapper(
		ChActualDiscountLocalService chActualDiscountLocalService) {
		_chActualDiscountLocalService = chActualDiscountLocalService;
	}

	/**
	* Adds the ch actual discount to the database. Also notifies the appropriate model listeners.
	*
	* @param chActualDiscount the ch actual discount
	* @return the ch actual discount that was added
	*/
	@Override
	public com.stpl.app.model.ChActualDiscount addChActualDiscount(
		com.stpl.app.model.ChActualDiscount chActualDiscount) {
		return _chActualDiscountLocalService.addChActualDiscount(chActualDiscount);
	}

	/**
	* Creates a new ch actual discount with the primary key. Does not add the ch actual discount to the database.
	*
	* @param chActualDiscountPK the primary key for the new ch actual discount
	* @return the new ch actual discount
	*/
	@Override
	public com.stpl.app.model.ChActualDiscount createChActualDiscount(
		com.stpl.app.service.persistence.ChActualDiscountPK chActualDiscountPK) {
		return _chActualDiscountLocalService.createChActualDiscount(chActualDiscountPK);
	}

	/**
	* Deletes the ch actual discount from the database. Also notifies the appropriate model listeners.
	*
	* @param chActualDiscount the ch actual discount
	* @return the ch actual discount that was removed
	*/
	@Override
	public com.stpl.app.model.ChActualDiscount deleteChActualDiscount(
		com.stpl.app.model.ChActualDiscount chActualDiscount) {
		return _chActualDiscountLocalService.deleteChActualDiscount(chActualDiscount);
	}

	/**
	* Deletes the ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chActualDiscountPK the primary key of the ch actual discount
	* @return the ch actual discount that was removed
	* @throws PortalException if a ch actual discount with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ChActualDiscount deleteChActualDiscount(
		com.stpl.app.service.persistence.ChActualDiscountPK chActualDiscountPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chActualDiscountLocalService.deleteChActualDiscount(chActualDiscountPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chActualDiscountLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _chActualDiscountLocalService.dynamicQuery();
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
		return _chActualDiscountLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _chActualDiscountLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _chActualDiscountLocalService.dynamicQuery(dynamicQuery, start,
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
		return _chActualDiscountLocalService.dynamicQueryCount(dynamicQuery);
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
		return _chActualDiscountLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ChActualDiscount fetchChActualDiscount(
		com.stpl.app.service.persistence.ChActualDiscountPK chActualDiscountPK) {
		return _chActualDiscountLocalService.fetchChActualDiscount(chActualDiscountPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _chActualDiscountLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the ch actual discount with the primary key.
	*
	* @param chActualDiscountPK the primary key of the ch actual discount
	* @return the ch actual discount
	* @throws PortalException if a ch actual discount with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ChActualDiscount getChActualDiscount(
		com.stpl.app.service.persistence.ChActualDiscountPK chActualDiscountPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chActualDiscountLocalService.getChActualDiscount(chActualDiscountPK);
	}

	/**
	* Returns a range of all the ch actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch actual discounts
	* @param end the upper bound of the range of ch actual discounts (not inclusive)
	* @return the range of ch actual discounts
	*/
	@Override
	public java.util.List<com.stpl.app.model.ChActualDiscount> getChActualDiscounts(
		int start, int end) {
		return _chActualDiscountLocalService.getChActualDiscounts(start, end);
	}

	/**
	* Returns the number of ch actual discounts.
	*
	* @return the number of ch actual discounts
	*/
	@Override
	public int getChActualDiscountsCount() {
		return _chActualDiscountLocalService.getChActualDiscountsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _chActualDiscountLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _chActualDiscountLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chActualDiscountLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ch actual discount in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param chActualDiscount the ch actual discount
	* @return the ch actual discount that was updated
	*/
	@Override
	public com.stpl.app.model.ChActualDiscount updateChActualDiscount(
		com.stpl.app.model.ChActualDiscount chActualDiscount) {
		return _chActualDiscountLocalService.updateChActualDiscount(chActualDiscount);
	}

	@Override
	public ChActualDiscountLocalService getWrappedService() {
		return _chActualDiscountLocalService;
	}

	@Override
	public void setWrappedService(
		ChActualDiscountLocalService chActualDiscountLocalService) {
		_chActualDiscountLocalService = chActualDiscountLocalService;
	}

	private ChActualDiscountLocalService _chActualDiscountLocalService;
}