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
 * Provides a wrapper for {@link ForecastingMasterLocalService}.
 *
 * @author
 * @see ForecastingMasterLocalService
 * @generated
 */
@ProviderType
public class ForecastingMasterLocalServiceWrapper
	implements ForecastingMasterLocalService,
		ServiceWrapper<ForecastingMasterLocalService> {
	public ForecastingMasterLocalServiceWrapper(
		ForecastingMasterLocalService forecastingMasterLocalService) {
		_forecastingMasterLocalService = forecastingMasterLocalService;
	}

	/**
	* Adds the forecasting master to the database. Also notifies the appropriate model listeners.
	*
	* @param forecastingMaster the forecasting master
	* @return the forecasting master that was added
	*/
	@Override
	public com.stpl.app.model.ForecastingMaster addForecastingMaster(
		com.stpl.app.model.ForecastingMaster forecastingMaster) {
		return _forecastingMasterLocalService.addForecastingMaster(forecastingMaster);
	}

	/**
	* Creates a new forecasting master with the primary key. Does not add the forecasting master to the database.
	*
	* @param forecastMasterSid the primary key for the new forecasting master
	* @return the new forecasting master
	*/
	@Override
	public com.stpl.app.model.ForecastingMaster createForecastingMaster(
		int forecastMasterSid) {
		return _forecastingMasterLocalService.createForecastingMaster(forecastMasterSid);
	}

	/**
	* Deletes the forecasting master from the database. Also notifies the appropriate model listeners.
	*
	* @param forecastingMaster the forecasting master
	* @return the forecasting master that was removed
	*/
	@Override
	public com.stpl.app.model.ForecastingMaster deleteForecastingMaster(
		com.stpl.app.model.ForecastingMaster forecastingMaster) {
		return _forecastingMasterLocalService.deleteForecastingMaster(forecastingMaster);
	}

	/**
	* Deletes the forecasting master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param forecastMasterSid the primary key of the forecasting master
	* @return the forecasting master that was removed
	* @throws PortalException if a forecasting master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ForecastingMaster deleteForecastingMaster(
		int forecastMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _forecastingMasterLocalService.deleteForecastingMaster(forecastMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _forecastingMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _forecastingMasterLocalService.dynamicQuery();
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
		return _forecastingMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _forecastingMasterLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _forecastingMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _forecastingMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _forecastingMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ForecastingMaster fetchForecastingMaster(
		int forecastMasterSid) {
		return _forecastingMasterLocalService.fetchForecastingMaster(forecastMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _forecastingMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the forecasting master with the primary key.
	*
	* @param forecastMasterSid the primary key of the forecasting master
	* @return the forecasting master
	* @throws PortalException if a forecasting master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ForecastingMaster getForecastingMaster(
		int forecastMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _forecastingMasterLocalService.getForecastingMaster(forecastMasterSid);
	}

	/**
	* Returns a range of all the forecasting masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @return the range of forecasting masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.ForecastingMaster> getForecastingMasters(
		int start, int end) {
		return _forecastingMasterLocalService.getForecastingMasters(start, end);
	}

	/**
	* Returns the number of forecasting masters.
	*
	* @return the number of forecasting masters
	*/
	@Override
	public int getForecastingMastersCount() {
		return _forecastingMasterLocalService.getForecastingMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _forecastingMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _forecastingMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _forecastingMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the forecasting master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param forecastingMaster the forecasting master
	* @return the forecasting master that was updated
	*/
	@Override
	public com.stpl.app.model.ForecastingMaster updateForecastingMaster(
		com.stpl.app.model.ForecastingMaster forecastingMaster) {
		return _forecastingMasterLocalService.updateForecastingMaster(forecastingMaster);
	}

	@Override
	public ForecastingMasterLocalService getWrappedService() {
		return _forecastingMasterLocalService;
	}

	@Override
	public void setWrappedService(
		ForecastingMasterLocalService forecastingMasterLocalService) {
		_forecastingMasterLocalService = forecastingMasterLocalService;
	}

	private ForecastingMasterLocalService _forecastingMasterLocalService;
}