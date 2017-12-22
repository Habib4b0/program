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

import com.stpl.app.parttwo.exception.NoSuchAdjustedDemandForecastException;
import com.stpl.app.parttwo.model.AdjustedDemandForecast;

/**
 * The persistence interface for the adjusted demand forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.AdjustedDemandForecastPersistenceImpl
 * @see AdjustedDemandForecastUtil
 * @generated
 */
@ProviderType
public interface AdjustedDemandForecastPersistence extends BasePersistence<AdjustedDemandForecast> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AdjustedDemandForecastUtil} to access the adjusted demand forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the adjusted demand forecast in the entity cache if it is enabled.
	*
	* @param adjustedDemandForecast the adjusted demand forecast
	*/
	public void cacheResult(AdjustedDemandForecast adjustedDemandForecast);

	/**
	* Caches the adjusted demand forecasts in the entity cache if it is enabled.
	*
	* @param adjustedDemandForecasts the adjusted demand forecasts
	*/
	public void cacheResult(
		java.util.List<AdjustedDemandForecast> adjustedDemandForecasts);

	/**
	* Creates a new adjusted demand forecast with the primary key. Does not add the adjusted demand forecast to the database.
	*
	* @param adjustedDemandForecastSid the primary key for the new adjusted demand forecast
	* @return the new adjusted demand forecast
	*/
	public AdjustedDemandForecast create(int adjustedDemandForecastSid);

	/**
	* Removes the adjusted demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
	* @return the adjusted demand forecast that was removed
	* @throws NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
	*/
	public AdjustedDemandForecast remove(int adjustedDemandForecastSid)
		throws NoSuchAdjustedDemandForecastException;

	public AdjustedDemandForecast updateImpl(
		AdjustedDemandForecast adjustedDemandForecast);

	/**
	* Returns the adjusted demand forecast with the primary key or throws a {@link NoSuchAdjustedDemandForecastException} if it could not be found.
	*
	* @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
	* @return the adjusted demand forecast
	* @throws NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
	*/
	public AdjustedDemandForecast findByPrimaryKey(
		int adjustedDemandForecastSid)
		throws NoSuchAdjustedDemandForecastException;

	/**
	* Returns the adjusted demand forecast with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
	* @return the adjusted demand forecast, or <code>null</code> if a adjusted demand forecast with the primary key could not be found
	*/
	public AdjustedDemandForecast fetchByPrimaryKey(
		int adjustedDemandForecastSid);

	@Override
	public java.util.Map<java.io.Serializable, AdjustedDemandForecast> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the adjusted demand forecasts.
	*
	* @return the adjusted demand forecasts
	*/
	public java.util.List<AdjustedDemandForecast> findAll();

	/**
	* Returns a range of all the adjusted demand forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of adjusted demand forecasts
	* @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
	* @return the range of adjusted demand forecasts
	*/
	public java.util.List<AdjustedDemandForecast> findAll(int start, int end);

	/**
	* Returns an ordered range of all the adjusted demand forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of adjusted demand forecasts
	* @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of adjusted demand forecasts
	*/
	public java.util.List<AdjustedDemandForecast> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AdjustedDemandForecast> orderByComparator);

	/**
	* Returns an ordered range of all the adjusted demand forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of adjusted demand forecasts
	* @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of adjusted demand forecasts
	*/
	public java.util.List<AdjustedDemandForecast> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AdjustedDemandForecast> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the adjusted demand forecasts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of adjusted demand forecasts.
	*
	* @return the number of adjusted demand forecasts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}