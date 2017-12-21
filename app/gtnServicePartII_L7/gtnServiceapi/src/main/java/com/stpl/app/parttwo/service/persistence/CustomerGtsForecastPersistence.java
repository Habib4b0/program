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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.parttwo.exception.NoSuchCustomerGtsForecastException;
import com.stpl.app.parttwo.model.CustomerGtsForecast;

/**
 * The persistence interface for the customer gts forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.CustomerGtsForecastPersistenceImpl
 * @see CustomerGtsForecastUtil
 * @generated
 */
@ProviderType
public interface CustomerGtsForecastPersistence extends BasePersistence<CustomerGtsForecast> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CustomerGtsForecastUtil} to access the customer gts forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the customer gts forecast in the entity cache if it is enabled.
	*
	* @param customerGtsForecast the customer gts forecast
	*/
	public void cacheResult(CustomerGtsForecast customerGtsForecast);

	/**
	* Caches the customer gts forecasts in the entity cache if it is enabled.
	*
	* @param customerGtsForecasts the customer gts forecasts
	*/
	public void cacheResult(
		java.util.List<CustomerGtsForecast> customerGtsForecasts);

	/**
	* Creates a new customer gts forecast with the primary key. Does not add the customer gts forecast to the database.
	*
	* @param customerGtsForecastSid the primary key for the new customer gts forecast
	* @return the new customer gts forecast
	*/
	public CustomerGtsForecast create(int customerGtsForecastSid);

	/**
	* Removes the customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customerGtsForecastSid the primary key of the customer gts forecast
	* @return the customer gts forecast that was removed
	* @throws NoSuchCustomerGtsForecastException if a customer gts forecast with the primary key could not be found
	*/
	public CustomerGtsForecast remove(int customerGtsForecastSid)
		throws NoSuchCustomerGtsForecastException;

	public CustomerGtsForecast updateImpl(
		CustomerGtsForecast customerGtsForecast);

	/**
	* Returns the customer gts forecast with the primary key or throws a {@link NoSuchCustomerGtsForecastException} if it could not be found.
	*
	* @param customerGtsForecastSid the primary key of the customer gts forecast
	* @return the customer gts forecast
	* @throws NoSuchCustomerGtsForecastException if a customer gts forecast with the primary key could not be found
	*/
	public CustomerGtsForecast findByPrimaryKey(int customerGtsForecastSid)
		throws NoSuchCustomerGtsForecastException;

	/**
	* Returns the customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param customerGtsForecastSid the primary key of the customer gts forecast
	* @return the customer gts forecast, or <code>null</code> if a customer gts forecast with the primary key could not be found
	*/
	public CustomerGtsForecast fetchByPrimaryKey(int customerGtsForecastSid);

	@Override
	public java.util.Map<java.io.Serializable, CustomerGtsForecast> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the customer gts forecasts.
	*
	* @return the customer gts forecasts
	*/
	public java.util.List<CustomerGtsForecast> findAll();

	/**
	* Returns a range of all the customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of customer gts forecasts
	* @param end the upper bound of the range of customer gts forecasts (not inclusive)
	* @return the range of customer gts forecasts
	*/
	public java.util.List<CustomerGtsForecast> findAll(int start, int end);

	/**
	* Returns an ordered range of all the customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of customer gts forecasts
	* @param end the upper bound of the range of customer gts forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of customer gts forecasts
	*/
	public java.util.List<CustomerGtsForecast> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomerGtsForecast> orderByComparator);

	/**
	* Returns an ordered range of all the customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of customer gts forecasts
	* @param end the upper bound of the range of customer gts forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of customer gts forecasts
	*/
	public java.util.List<CustomerGtsForecast> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomerGtsForecast> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the customer gts forecasts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of customer gts forecasts.
	*
	* @return the number of customer gts forecasts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}