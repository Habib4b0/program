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
 * Provides a wrapper for {@link CustomerGtsForecastLocalService}.
 *
 * @author
 * @see CustomerGtsForecastLocalService
 * @generated
 */
@ProviderType
public class CustomerGtsForecastLocalServiceWrapper
	implements CustomerGtsForecastLocalService,
		ServiceWrapper<CustomerGtsForecastLocalService> {
	public CustomerGtsForecastLocalServiceWrapper(
		CustomerGtsForecastLocalService customerGtsForecastLocalService) {
		_customerGtsForecastLocalService = customerGtsForecastLocalService;
	}

	/**
	* Adds the customer gts forecast to the database. Also notifies the appropriate model listeners.
	*
	* @param customerGtsForecast the customer gts forecast
	* @return the customer gts forecast that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.CustomerGtsForecast addCustomerGtsForecast(
		com.stpl.app.parttwo.model.CustomerGtsForecast customerGtsForecast) {
		return _customerGtsForecastLocalService.addCustomerGtsForecast(customerGtsForecast);
	}

	/**
	* Creates a new customer gts forecast with the primary key. Does not add the customer gts forecast to the database.
	*
	* @param customerGtsForecastSid the primary key for the new customer gts forecast
	* @return the new customer gts forecast
	*/
	@Override
	public com.stpl.app.parttwo.model.CustomerGtsForecast createCustomerGtsForecast(
		int customerGtsForecastSid) {
		return _customerGtsForecastLocalService.createCustomerGtsForecast(customerGtsForecastSid);
	}

	/**
	* Deletes the customer gts forecast from the database. Also notifies the appropriate model listeners.
	*
	* @param customerGtsForecast the customer gts forecast
	* @return the customer gts forecast that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.CustomerGtsForecast deleteCustomerGtsForecast(
		com.stpl.app.parttwo.model.CustomerGtsForecast customerGtsForecast) {
		return _customerGtsForecastLocalService.deleteCustomerGtsForecast(customerGtsForecast);
	}

	/**
	* Deletes the customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customerGtsForecastSid the primary key of the customer gts forecast
	* @return the customer gts forecast that was removed
	* @throws PortalException if a customer gts forecast with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CustomerGtsForecast deleteCustomerGtsForecast(
		int customerGtsForecastSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customerGtsForecastLocalService.deleteCustomerGtsForecast(customerGtsForecastSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customerGtsForecastLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _customerGtsForecastLocalService.dynamicQuery();
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
		return _customerGtsForecastLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _customerGtsForecastLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _customerGtsForecastLocalService.dynamicQuery(dynamicQuery,
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
		return _customerGtsForecastLocalService.dynamicQueryCount(dynamicQuery);
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
		return _customerGtsForecastLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.CustomerGtsForecast fetchCustomerGtsForecast(
		int customerGtsForecastSid) {
		return _customerGtsForecastLocalService.fetchCustomerGtsForecast(customerGtsForecastSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _customerGtsForecastLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the customer gts forecast with the primary key.
	*
	* @param customerGtsForecastSid the primary key of the customer gts forecast
	* @return the customer gts forecast
	* @throws PortalException if a customer gts forecast with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CustomerGtsForecast getCustomerGtsForecast(
		int customerGtsForecastSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customerGtsForecastLocalService.getCustomerGtsForecast(customerGtsForecastSid);
	}

	/**
	* Returns a range of all the customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of customer gts forecasts
	* @param end the upper bound of the range of customer gts forecasts (not inclusive)
	* @return the range of customer gts forecasts
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.CustomerGtsForecast> getCustomerGtsForecasts(
		int start, int end) {
		return _customerGtsForecastLocalService.getCustomerGtsForecasts(start,
			end);
	}

	/**
	* Returns the number of customer gts forecasts.
	*
	* @return the number of customer gts forecasts
	*/
	@Override
	public int getCustomerGtsForecastsCount() {
		return _customerGtsForecastLocalService.getCustomerGtsForecastsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _customerGtsForecastLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _customerGtsForecastLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customerGtsForecastLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the customer gts forecast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param customerGtsForecast the customer gts forecast
	* @return the customer gts forecast that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.CustomerGtsForecast updateCustomerGtsForecast(
		com.stpl.app.parttwo.model.CustomerGtsForecast customerGtsForecast) {
		return _customerGtsForecastLocalService.updateCustomerGtsForecast(customerGtsForecast);
	}

	@Override
	public CustomerGtsForecastLocalService getWrappedService() {
		return _customerGtsForecastLocalService;
	}

	@Override
	public void setWrappedService(
		CustomerGtsForecastLocalService customerGtsForecastLocalService) {
		_customerGtsForecastLocalService = customerGtsForecastLocalService;
	}

	private CustomerGtsForecastLocalService _customerGtsForecastLocalService;
}