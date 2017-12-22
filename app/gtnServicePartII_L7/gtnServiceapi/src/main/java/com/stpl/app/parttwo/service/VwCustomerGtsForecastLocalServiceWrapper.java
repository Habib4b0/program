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
 * Provides a wrapper for {@link VwCustomerGtsForecastLocalService}.
 *
 * @author
 * @see VwCustomerGtsForecastLocalService
 * @generated
 */
@ProviderType
public class VwCustomerGtsForecastLocalServiceWrapper
	implements VwCustomerGtsForecastLocalService,
		ServiceWrapper<VwCustomerGtsForecastLocalService> {
	public VwCustomerGtsForecastLocalServiceWrapper(
		VwCustomerGtsForecastLocalService vwCustomerGtsForecastLocalService) {
		_vwCustomerGtsForecastLocalService = vwCustomerGtsForecastLocalService;
	}

	/**
	* Adds the vw customer gts forecast to the database. Also notifies the appropriate model listeners.
	*
	* @param vwCustomerGtsForecast the vw customer gts forecast
	* @return the vw customer gts forecast that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCustomerGtsForecast addVwCustomerGtsForecast(
		com.stpl.app.parttwo.model.VwCustomerGtsForecast vwCustomerGtsForecast) {
		return _vwCustomerGtsForecastLocalService.addVwCustomerGtsForecast(vwCustomerGtsForecast);
	}

	/**
	* Creates a new vw customer gts forecast with the primary key. Does not add the vw customer gts forecast to the database.
	*
	* @param customerGtsForecastSid the primary key for the new vw customer gts forecast
	* @return the new vw customer gts forecast
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCustomerGtsForecast createVwCustomerGtsForecast(
		int customerGtsForecastSid) {
		return _vwCustomerGtsForecastLocalService.createVwCustomerGtsForecast(customerGtsForecastSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCustomerGtsForecastLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the vw customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customerGtsForecastSid the primary key of the vw customer gts forecast
	* @return the vw customer gts forecast that was removed
	* @throws PortalException if a vw customer gts forecast with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCustomerGtsForecast deleteVwCustomerGtsForecast(
		int customerGtsForecastSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCustomerGtsForecastLocalService.deleteVwCustomerGtsForecast(customerGtsForecastSid);
	}

	/**
	* Deletes the vw customer gts forecast from the database. Also notifies the appropriate model listeners.
	*
	* @param vwCustomerGtsForecast the vw customer gts forecast
	* @return the vw customer gts forecast that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCustomerGtsForecast deleteVwCustomerGtsForecast(
		com.stpl.app.parttwo.model.VwCustomerGtsForecast vwCustomerGtsForecast) {
		return _vwCustomerGtsForecastLocalService.deleteVwCustomerGtsForecast(vwCustomerGtsForecast);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _vwCustomerGtsForecastLocalService.dynamicQuery();
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
		return _vwCustomerGtsForecastLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwCustomerGtsForecastLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwCustomerGtsForecastLocalService.dynamicQuery(dynamicQuery,
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
		return _vwCustomerGtsForecastLocalService.dynamicQueryCount(dynamicQuery);
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
		return _vwCustomerGtsForecastLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.VwCustomerGtsForecast fetchVwCustomerGtsForecast(
		int customerGtsForecastSid) {
		return _vwCustomerGtsForecastLocalService.fetchVwCustomerGtsForecast(customerGtsForecastSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _vwCustomerGtsForecastLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _vwCustomerGtsForecastLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _vwCustomerGtsForecastLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCustomerGtsForecastLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the vw customer gts forecast with the primary key.
	*
	* @param customerGtsForecastSid the primary key of the vw customer gts forecast
	* @return the vw customer gts forecast
	* @throws PortalException if a vw customer gts forecast with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCustomerGtsForecast getVwCustomerGtsForecast(
		int customerGtsForecastSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCustomerGtsForecastLocalService.getVwCustomerGtsForecast(customerGtsForecastSid);
	}

	/**
	* Returns a range of all the vw customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw customer gts forecasts
	* @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
	* @return the range of vw customer gts forecasts
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.VwCustomerGtsForecast> getVwCustomerGtsForecasts(
		int start, int end) {
		return _vwCustomerGtsForecastLocalService.getVwCustomerGtsForecasts(start,
			end);
	}

	/**
	* Returns the number of vw customer gts forecasts.
	*
	* @return the number of vw customer gts forecasts
	*/
	@Override
	public int getVwCustomerGtsForecastsCount() {
		return _vwCustomerGtsForecastLocalService.getVwCustomerGtsForecastsCount();
	}

	/**
	* Updates the vw customer gts forecast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param vwCustomerGtsForecast the vw customer gts forecast
	* @return the vw customer gts forecast that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCustomerGtsForecast updateVwCustomerGtsForecast(
		com.stpl.app.parttwo.model.VwCustomerGtsForecast vwCustomerGtsForecast) {
		return _vwCustomerGtsForecastLocalService.updateVwCustomerGtsForecast(vwCustomerGtsForecast);
	}

	@Override
	public VwCustomerGtsForecastLocalService getWrappedService() {
		return _vwCustomerGtsForecastLocalService;
	}

	@Override
	public void setWrappedService(
		VwCustomerGtsForecastLocalService vwCustomerGtsForecastLocalService) {
		_vwCustomerGtsForecastLocalService = vwCustomerGtsForecastLocalService;
	}

	private VwCustomerGtsForecastLocalService _vwCustomerGtsForecastLocalService;
}