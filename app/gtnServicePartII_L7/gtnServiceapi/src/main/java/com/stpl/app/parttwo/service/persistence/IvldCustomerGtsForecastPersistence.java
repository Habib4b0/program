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

import com.stpl.app.parttwo.exception.NoSuchIvldCustomerGtsForecastException;
import com.stpl.app.parttwo.model.IvldCustomerGtsForecast;

/**
 * The persistence interface for the ivld customer gts forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldCustomerGtsForecastPersistenceImpl
 * @see IvldCustomerGtsForecastUtil
 * @generated
 */
@ProviderType
public interface IvldCustomerGtsForecastPersistence extends BasePersistence<IvldCustomerGtsForecast> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldCustomerGtsForecastUtil} to access the ivld customer gts forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld customer gts forecast in the entity cache if it is enabled.
	*
	* @param ivldCustomerGtsForecast the ivld customer gts forecast
	*/
	public void cacheResult(IvldCustomerGtsForecast ivldCustomerGtsForecast);

	/**
	* Caches the ivld customer gts forecasts in the entity cache if it is enabled.
	*
	* @param ivldCustomerGtsForecasts the ivld customer gts forecasts
	*/
	public void cacheResult(
		java.util.List<IvldCustomerGtsForecast> ivldCustomerGtsForecasts);

	/**
	* Creates a new ivld customer gts forecast with the primary key. Does not add the ivld customer gts forecast to the database.
	*
	* @param ivldCustomerGtsForecastSid the primary key for the new ivld customer gts forecast
	* @return the new ivld customer gts forecast
	*/
	public IvldCustomerGtsForecast create(int ivldCustomerGtsForecastSid);

	/**
	* Removes the ivld customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
	* @return the ivld customer gts forecast that was removed
	* @throws NoSuchIvldCustomerGtsForecastException if a ivld customer gts forecast with the primary key could not be found
	*/
	public IvldCustomerGtsForecast remove(int ivldCustomerGtsForecastSid)
		throws NoSuchIvldCustomerGtsForecastException;

	public IvldCustomerGtsForecast updateImpl(
		IvldCustomerGtsForecast ivldCustomerGtsForecast);

	/**
	* Returns the ivld customer gts forecast with the primary key or throws a {@link NoSuchIvldCustomerGtsForecastException} if it could not be found.
	*
	* @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
	* @return the ivld customer gts forecast
	* @throws NoSuchIvldCustomerGtsForecastException if a ivld customer gts forecast with the primary key could not be found
	*/
	public IvldCustomerGtsForecast findByPrimaryKey(
		int ivldCustomerGtsForecastSid)
		throws NoSuchIvldCustomerGtsForecastException;

	/**
	* Returns the ivld customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
	* @return the ivld customer gts forecast, or <code>null</code> if a ivld customer gts forecast with the primary key could not be found
	*/
	public IvldCustomerGtsForecast fetchByPrimaryKey(
		int ivldCustomerGtsForecastSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldCustomerGtsForecast> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld customer gts forecasts.
	*
	* @return the ivld customer gts forecasts
	*/
	public java.util.List<IvldCustomerGtsForecast> findAll();

	/**
	* Returns a range of all the ivld customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld customer gts forecasts
	* @param end the upper bound of the range of ivld customer gts forecasts (not inclusive)
	* @return the range of ivld customer gts forecasts
	*/
	public java.util.List<IvldCustomerGtsForecast> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld customer gts forecasts
	* @param end the upper bound of the range of ivld customer gts forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld customer gts forecasts
	*/
	public java.util.List<IvldCustomerGtsForecast> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldCustomerGtsForecast> orderByComparator);

	/**
	* Returns an ordered range of all the ivld customer gts forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld customer gts forecasts
	* @param end the upper bound of the range of ivld customer gts forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld customer gts forecasts
	*/
	public java.util.List<IvldCustomerGtsForecast> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldCustomerGtsForecast> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld customer gts forecasts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld customer gts forecasts.
	*
	* @return the number of ivld customer gts forecasts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}