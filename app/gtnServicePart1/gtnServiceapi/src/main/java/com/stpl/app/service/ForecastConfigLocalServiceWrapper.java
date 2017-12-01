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
 * Provides a wrapper for {@link ForecastConfigLocalService}.
 *
 * @author
 * @see ForecastConfigLocalService
 * @generated
 */
@ProviderType
public class ForecastConfigLocalServiceWrapper
	implements ForecastConfigLocalService,
		ServiceWrapper<ForecastConfigLocalService> {
	public ForecastConfigLocalServiceWrapper(
		ForecastConfigLocalService forecastConfigLocalService) {
		_forecastConfigLocalService = forecastConfigLocalService;
	}

	/**
	* Adds the forecast config to the database. Also notifies the appropriate model listeners.
	*
	* @param forecastConfig the forecast config
	* @return the forecast config that was added
	*/
	@Override
	public com.stpl.app.model.ForecastConfig addForecastConfig(
		com.stpl.app.model.ForecastConfig forecastConfig) {
		return _forecastConfigLocalService.addForecastConfig(forecastConfig);
	}

	/**
	* Creates a new forecast config with the primary key. Does not add the forecast config to the database.
	*
	* @param forecastConfigSid the primary key for the new forecast config
	* @return the new forecast config
	*/
	@Override
	public com.stpl.app.model.ForecastConfig createForecastConfig(
		int forecastConfigSid) {
		return _forecastConfigLocalService.createForecastConfig(forecastConfigSid);
	}

	/**
	* Deletes the forecast config from the database. Also notifies the appropriate model listeners.
	*
	* @param forecastConfig the forecast config
	* @return the forecast config that was removed
	*/
	@Override
	public com.stpl.app.model.ForecastConfig deleteForecastConfig(
		com.stpl.app.model.ForecastConfig forecastConfig) {
		return _forecastConfigLocalService.deleteForecastConfig(forecastConfig);
	}

	/**
	* Deletes the forecast config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param forecastConfigSid the primary key of the forecast config
	* @return the forecast config that was removed
	* @throws PortalException if a forecast config with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ForecastConfig deleteForecastConfig(
		int forecastConfigSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _forecastConfigLocalService.deleteForecastConfig(forecastConfigSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _forecastConfigLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _forecastConfigLocalService.dynamicQuery();
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
		return _forecastConfigLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _forecastConfigLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _forecastConfigLocalService.dynamicQuery(dynamicQuery, start,
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
		return _forecastConfigLocalService.dynamicQueryCount(dynamicQuery);
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
		return _forecastConfigLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ForecastConfig fetchForecastConfig(
		int forecastConfigSid) {
		return _forecastConfigLocalService.fetchForecastConfig(forecastConfigSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _forecastConfigLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the forecast config with the primary key.
	*
	* @param forecastConfigSid the primary key of the forecast config
	* @return the forecast config
	* @throws PortalException if a forecast config with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ForecastConfig getForecastConfig(
		int forecastConfigSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _forecastConfigLocalService.getForecastConfig(forecastConfigSid);
	}

	/**
	* Returns a range of all the forecast configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecast configs
	* @param end the upper bound of the range of forecast configs (not inclusive)
	* @return the range of forecast configs
	*/
	@Override
	public java.util.List<com.stpl.app.model.ForecastConfig> getForecastConfigs(
		int start, int end) {
		return _forecastConfigLocalService.getForecastConfigs(start, end);
	}

	/**
	* Returns the number of forecast configs.
	*
	* @return the number of forecast configs
	*/
	@Override
	public int getForecastConfigsCount() {
		return _forecastConfigLocalService.getForecastConfigsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _forecastConfigLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _forecastConfigLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _forecastConfigLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the forecast config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param forecastConfig the forecast config
	* @return the forecast config that was updated
	*/
	@Override
	public com.stpl.app.model.ForecastConfig updateForecastConfig(
		com.stpl.app.model.ForecastConfig forecastConfig) {
		return _forecastConfigLocalService.updateForecastConfig(forecastConfig);
	}

	@Override
	public ForecastConfigLocalService getWrappedService() {
		return _forecastConfigLocalService;
	}

	@Override
	public void setWrappedService(
		ForecastConfigLocalService forecastConfigLocalService) {
		_forecastConfigLocalService = forecastConfigLocalService;
	}

	private ForecastConfigLocalService _forecastConfigLocalService;
}