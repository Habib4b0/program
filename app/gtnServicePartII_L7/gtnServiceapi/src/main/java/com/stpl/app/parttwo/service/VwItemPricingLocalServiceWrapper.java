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

package com.stpl.app.parttwo.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwItemPricingLocalService}.
 *
 * @author
 * @see VwItemPricingLocalService
 * @generated
 */
@ProviderType
public class VwItemPricingLocalServiceWrapper
	implements VwItemPricingLocalService,
		ServiceWrapper<VwItemPricingLocalService> {
	public VwItemPricingLocalServiceWrapper(
		VwItemPricingLocalService vwItemPricingLocalService) {
		_vwItemPricingLocalService = vwItemPricingLocalService;
	}

	/**
	* Adds the vw item pricing to the database. Also notifies the appropriate model listeners.
	*
	* @param vwItemPricing the vw item pricing
	* @return the vw item pricing that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.VwItemPricing addVwItemPricing(
		com.stpl.app.parttwo.model.VwItemPricing vwItemPricing) {
		return _vwItemPricingLocalService.addVwItemPricing(vwItemPricing);
	}

	/**
	* Creates a new vw item pricing with the primary key. Does not add the vw item pricing to the database.
	*
	* @param itemPricingSid the primary key for the new vw item pricing
	* @return the new vw item pricing
	*/
	@Override
	public com.stpl.app.parttwo.model.VwItemPricing createVwItemPricing(
		int itemPricingSid) {
		return _vwItemPricingLocalService.createVwItemPricing(itemPricingSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwItemPricingLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the vw item pricing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemPricingSid the primary key of the vw item pricing
	* @return the vw item pricing that was removed
	* @throws PortalException if a vw item pricing with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.VwItemPricing deleteVwItemPricing(
		int itemPricingSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwItemPricingLocalService.deleteVwItemPricing(itemPricingSid);
	}

	/**
	* Deletes the vw item pricing from the database. Also notifies the appropriate model listeners.
	*
	* @param vwItemPricing the vw item pricing
	* @return the vw item pricing that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.VwItemPricing deleteVwItemPricing(
		com.stpl.app.parttwo.model.VwItemPricing vwItemPricing) {
		return _vwItemPricingLocalService.deleteVwItemPricing(vwItemPricing);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _vwItemPricingLocalService.dynamicQuery();
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
		return _vwItemPricingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwItemPricingLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwItemPricingLocalService.dynamicQuery(dynamicQuery, start,
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
		return _vwItemPricingLocalService.dynamicQueryCount(dynamicQuery);
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
		return _vwItemPricingLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.VwItemPricing fetchVwItemPricing(
		int itemPricingSid) {
		return _vwItemPricingLocalService.fetchVwItemPricing(itemPricingSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _vwItemPricingLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _vwItemPricingLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _vwItemPricingLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwItemPricingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the vw item pricing with the primary key.
	*
	* @param itemPricingSid the primary key of the vw item pricing
	* @return the vw item pricing
	* @throws PortalException if a vw item pricing with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.VwItemPricing getVwItemPricing(
		int itemPricingSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwItemPricingLocalService.getVwItemPricing(itemPricingSid);
	}

	/**
	* Returns a range of all the vw item pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item pricings
	* @param end the upper bound of the range of vw item pricings (not inclusive)
	* @return the range of vw item pricings
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.VwItemPricing> getVwItemPricings(
		int start, int end) {
		return _vwItemPricingLocalService.getVwItemPricings(start, end);
	}

	/**
	* Returns the number of vw item pricings.
	*
	* @return the number of vw item pricings
	*/
	@Override
	public int getVwItemPricingsCount() {
		return _vwItemPricingLocalService.getVwItemPricingsCount();
	}

	/**
	* Updates the vw item pricing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param vwItemPricing the vw item pricing
	* @return the vw item pricing that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.VwItemPricing updateVwItemPricing(
		com.stpl.app.parttwo.model.VwItemPricing vwItemPricing) {
		return _vwItemPricingLocalService.updateVwItemPricing(vwItemPricing);
	}

	@Override
	public VwItemPricingLocalService getWrappedService() {
		return _vwItemPricingLocalService;
	}

	@Override
	public void setWrappedService(
		VwItemPricingLocalService vwItemPricingLocalService) {
		_vwItemPricingLocalService = vwItemPricingLocalService;
	}

	private VwItemPricingLocalService _vwItemPricingLocalService;
}