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

import com.stpl.app.parttwo.exception.NoSuchVwCustomerGtsForecastException;
import com.stpl.app.parttwo.model.VwCustomerGtsForecast;

/**
 * The persistence interface for the vw customer gts forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.VwCustomerGtsForecastPersistenceImpl
 * @see VwCustomerGtsForecastUtil
 * @generated
 */
@ProviderType
public interface VwCustomerGtsForecastPersistence extends BasePersistence<VwCustomerGtsForecast> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwCustomerGtsForecastUtil} to access the vw customer gts forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw customer gts forecast in the entity cache if it is enabled.
	*
	* @param vwCustomerGtsForecast the vw customer gts forecast
	*/
	public void cacheResult(VwCustomerGtsForecast vwCustomerGtsForecast);

	/**
	* Caches the vw customer gts forecasts in the entity cache if it is enabled.
	*
	* @param vwCustomerGtsForecasts the vw customer gts forecasts
	*/
	public void cacheResult(
		java.util.List<VwCustomerGtsForecast> vwCustomerGtsForecasts);

	/**
	* Creates a new vw customer gts forecast with the primary key. Does not add the vw customer gts forecast to the database.
	*
	* @param customerGtsForecastSid the primary key for the new vw customer gts forecast
	* @return the new vw customer gts forecast
	*/
	public VwCustomerGtsForecast create(int customerGtsForecastSid);

	/**
	* Removes the vw customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customerGtsForecastSid the primary key of the vw customer gts forecast
	* @return the vw customer gts forecast that was removed
	* @throws NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
	*/
	public VwCustomerGtsForecast remove(int customerGtsForecastSid)
		throws NoSuchVwCustomerGtsForecastException;

	public VwCustomerGtsForecast updateImpl(
		VwCustomerGtsForecast vwCustomerGtsForecast);

	/**
	* Returns the vw customer gts forecast with the primary key or throws a {@link NoSuchVwCustomerGtsForecastException} if it could not be found.
	*
	* @param customerGtsForecastSid the primary key of the vw customer gts forecast
	* @return the vw customer gts forecast
	* @throws NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
	*/
	public VwCustomerGtsForecast findByPrimaryKey(int customerGtsForecastSid)
		throws NoSuchVwCustomerGtsForecastException;

	/**
	* Returns the vw customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param customerGtsForecastSid the primary key of the vw customer gts forecast
	* @return the vw customer gts forecast, or <code>null</code> if a vw customer gts forecast with the primary key could not be found
	*/
	public VwCustomerGtsForecast fetchByPrimaryKey(int customerGtsForecastSid);

	@Override
	public java.util.Map<java.io.Serializable, VwCustomerGtsForecast> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw customer gts forecasts.
	*
	* @return the vw customer gts forecasts
	*/
	public java.util.List<VwCustomerGtsForecast> findAll();

	/**
	* Returns a range of all the vw customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw customer gts forecasts
	* @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
	* @return the range of vw customer gts forecasts
	*/
	public java.util.List<VwCustomerGtsForecast> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vw customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw customer gts forecasts
	* @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw customer gts forecasts
	*/
	public java.util.List<VwCustomerGtsForecast> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwCustomerGtsForecast> orderByComparator);

	/**
	* Returns an ordered range of all the vw customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw customer gts forecasts
	* @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw customer gts forecasts
	*/
	public java.util.List<VwCustomerGtsForecast> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwCustomerGtsForecast> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw customer gts forecasts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw customer gts forecasts.
	*
	* @return the number of vw customer gts forecasts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}