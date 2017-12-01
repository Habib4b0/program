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
 * Provides a wrapper for {@link ForecastingViewMasterLocalService}.
 *
 * @author
 * @see ForecastingViewMasterLocalService
 * @generated
 */
@ProviderType
public class ForecastingViewMasterLocalServiceWrapper
	implements ForecastingViewMasterLocalService,
		ServiceWrapper<ForecastingViewMasterLocalService> {
	public ForecastingViewMasterLocalServiceWrapper(
		ForecastingViewMasterLocalService forecastingViewMasterLocalService) {
		_forecastingViewMasterLocalService = forecastingViewMasterLocalService;
	}

	/**
	* Adds the forecasting view master to the database. Also notifies the appropriate model listeners.
	*
	* @param forecastingViewMaster the forecasting view master
	* @return the forecasting view master that was added
	*/
	@Override
	public com.stpl.app.model.ForecastingViewMaster addForecastingViewMaster(
		com.stpl.app.model.ForecastingViewMaster forecastingViewMaster) {
		return _forecastingViewMasterLocalService.addForecastingViewMaster(forecastingViewMaster);
	}

	/**
	* Creates a new forecasting view master with the primary key. Does not add the forecasting view master to the database.
	*
	* @param viewId the primary key for the new forecasting view master
	* @return the new forecasting view master
	*/
	@Override
	public com.stpl.app.model.ForecastingViewMaster createForecastingViewMaster(
		int viewId) {
		return _forecastingViewMasterLocalService.createForecastingViewMaster(viewId);
	}

	/**
	* Deletes the forecasting view master from the database. Also notifies the appropriate model listeners.
	*
	* @param forecastingViewMaster the forecasting view master
	* @return the forecasting view master that was removed
	*/
	@Override
	public com.stpl.app.model.ForecastingViewMaster deleteForecastingViewMaster(
		com.stpl.app.model.ForecastingViewMaster forecastingViewMaster) {
		return _forecastingViewMasterLocalService.deleteForecastingViewMaster(forecastingViewMaster);
	}

	/**
	* Deletes the forecasting view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param viewId the primary key of the forecasting view master
	* @return the forecasting view master that was removed
	* @throws PortalException if a forecasting view master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ForecastingViewMaster deleteForecastingViewMaster(
		int viewId) throws com.liferay.portal.kernel.exception.PortalException {
		return _forecastingViewMasterLocalService.deleteForecastingViewMaster(viewId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _forecastingViewMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _forecastingViewMasterLocalService.dynamicQuery();
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
		return _forecastingViewMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _forecastingViewMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _forecastingViewMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _forecastingViewMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _forecastingViewMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ForecastingViewMaster fetchForecastingViewMaster(
		int viewId) {
		return _forecastingViewMasterLocalService.fetchForecastingViewMaster(viewId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _forecastingViewMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the forecasting view master with the primary key.
	*
	* @param viewId the primary key of the forecasting view master
	* @return the forecasting view master
	* @throws PortalException if a forecasting view master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ForecastingViewMaster getForecastingViewMaster(
		int viewId) throws com.liferay.portal.kernel.exception.PortalException {
		return _forecastingViewMasterLocalService.getForecastingViewMaster(viewId);
	}

	/**
	* Returns a range of all the forecasting view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting view masters
	* @param end the upper bound of the range of forecasting view masters (not inclusive)
	* @return the range of forecasting view masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.ForecastingViewMaster> getForecastingViewMasters(
		int start, int end) {
		return _forecastingViewMasterLocalService.getForecastingViewMasters(start,
			end);
	}

	/**
	* Returns the number of forecasting view masters.
	*
	* @return the number of forecasting view masters
	*/
	@Override
	public int getForecastingViewMastersCount() {
		return _forecastingViewMasterLocalService.getForecastingViewMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _forecastingViewMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _forecastingViewMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _forecastingViewMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the forecasting view master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param forecastingViewMaster the forecasting view master
	* @return the forecasting view master that was updated
	*/
	@Override
	public com.stpl.app.model.ForecastingViewMaster updateForecastingViewMaster(
		com.stpl.app.model.ForecastingViewMaster forecastingViewMaster) {
		return _forecastingViewMasterLocalService.updateForecastingViewMaster(forecastingViewMaster);
	}

	@Override
	public ForecastingViewMasterLocalService getWrappedService() {
		return _forecastingViewMasterLocalService;
	}

	@Override
	public void setWrappedService(
		ForecastingViewMasterLocalService forecastingViewMasterLocalService) {
		_forecastingViewMasterLocalService = forecastingViewMasterLocalService;
	}

	private ForecastingViewMasterLocalService _forecastingViewMasterLocalService;
}