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
 * Provides a wrapper for {@link NmActualDiscountLocalService}.
 *
 * @author
 * @see NmActualDiscountLocalService
 * @generated
 */
@ProviderType
public class NmActualDiscountLocalServiceWrapper
	implements NmActualDiscountLocalService,
		ServiceWrapper<NmActualDiscountLocalService> {
	public NmActualDiscountLocalServiceWrapper(
		NmActualDiscountLocalService nmActualDiscountLocalService) {
		_nmActualDiscountLocalService = nmActualDiscountLocalService;
	}

	/**
	* Adds the nm actual discount to the database. Also notifies the appropriate model listeners.
	*
	* @param nmActualDiscount the nm actual discount
	* @return the nm actual discount that was added
	*/
	@Override
	public com.stpl.app.model.NmActualDiscount addNmActualDiscount(
		com.stpl.app.model.NmActualDiscount nmActualDiscount) {
		return _nmActualDiscountLocalService.addNmActualDiscount(nmActualDiscount);
	}

	/**
	* Creates a new nm actual discount with the primary key. Does not add the nm actual discount to the database.
	*
	* @param nmActualDiscountPK the primary key for the new nm actual discount
	* @return the new nm actual discount
	*/
	@Override
	public com.stpl.app.model.NmActualDiscount createNmActualDiscount(
		com.stpl.app.service.persistence.NmActualDiscountPK nmActualDiscountPK) {
		return _nmActualDiscountLocalService.createNmActualDiscount(nmActualDiscountPK);
	}

	/**
	* Deletes the nm actual discount from the database. Also notifies the appropriate model listeners.
	*
	* @param nmActualDiscount the nm actual discount
	* @return the nm actual discount that was removed
	*/
	@Override
	public com.stpl.app.model.NmActualDiscount deleteNmActualDiscount(
		com.stpl.app.model.NmActualDiscount nmActualDiscount) {
		return _nmActualDiscountLocalService.deleteNmActualDiscount(nmActualDiscount);
	}

	/**
	* Deletes the nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmActualDiscountPK the primary key of the nm actual discount
	* @return the nm actual discount that was removed
	* @throws PortalException if a nm actual discount with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NmActualDiscount deleteNmActualDiscount(
		com.stpl.app.service.persistence.NmActualDiscountPK nmActualDiscountPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmActualDiscountLocalService.deleteNmActualDiscount(nmActualDiscountPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmActualDiscountLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _nmActualDiscountLocalService.dynamicQuery();
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
		return _nmActualDiscountLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nmActualDiscountLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nmActualDiscountLocalService.dynamicQuery(dynamicQuery, start,
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
		return _nmActualDiscountLocalService.dynamicQueryCount(dynamicQuery);
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
		return _nmActualDiscountLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.NmActualDiscount fetchNmActualDiscount(
		com.stpl.app.service.persistence.NmActualDiscountPK nmActualDiscountPK) {
		return _nmActualDiscountLocalService.fetchNmActualDiscount(nmActualDiscountPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _nmActualDiscountLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _nmActualDiscountLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the nm actual discount with the primary key.
	*
	* @param nmActualDiscountPK the primary key of the nm actual discount
	* @return the nm actual discount
	* @throws PortalException if a nm actual discount with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NmActualDiscount getNmActualDiscount(
		com.stpl.app.service.persistence.NmActualDiscountPK nmActualDiscountPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmActualDiscountLocalService.getNmActualDiscount(nmActualDiscountPK);
	}

	/**
	* Returns a range of all the nm actual discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm actual discounts
	* @param end the upper bound of the range of nm actual discounts (not inclusive)
	* @return the range of nm actual discounts
	*/
	@Override
	public java.util.List<com.stpl.app.model.NmActualDiscount> getNmActualDiscounts(
		int start, int end) {
		return _nmActualDiscountLocalService.getNmActualDiscounts(start, end);
	}

	/**
	* Returns the number of nm actual discounts.
	*
	* @return the number of nm actual discounts
	*/
	@Override
	public int getNmActualDiscountsCount() {
		return _nmActualDiscountLocalService.getNmActualDiscountsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _nmActualDiscountLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmActualDiscountLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the nm actual discount in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nmActualDiscount the nm actual discount
	* @return the nm actual discount that was updated
	*/
	@Override
	public com.stpl.app.model.NmActualDiscount updateNmActualDiscount(
		com.stpl.app.model.NmActualDiscount nmActualDiscount) {
		return _nmActualDiscountLocalService.updateNmActualDiscount(nmActualDiscount);
	}

	@Override
	public NmActualDiscountLocalService getWrappedService() {
		return _nmActualDiscountLocalService;
	}

	@Override
	public void setWrappedService(
		NmActualDiscountLocalService nmActualDiscountLocalService) {
		_nmActualDiscountLocalService = nmActualDiscountLocalService;
	}

	private NmActualDiscountLocalService _nmActualDiscountLocalService;
}