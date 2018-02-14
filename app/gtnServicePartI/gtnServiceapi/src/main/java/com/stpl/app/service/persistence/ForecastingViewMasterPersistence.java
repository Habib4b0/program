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

package com.stpl.app.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.exception.NoSuchForecastingViewMasterException;
import com.stpl.app.model.ForecastingViewMaster;

/**
 * The persistence interface for the forecasting view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ForecastingViewMasterPersistenceImpl
 * @see ForecastingViewMasterUtil
 * @generated
 */
@ProviderType
public interface ForecastingViewMasterPersistence extends BasePersistence<ForecastingViewMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ForecastingViewMasterUtil} to access the forecasting view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the forecasting view master in the entity cache if it is enabled.
	*
	* @param forecastingViewMaster the forecasting view master
	*/
	public void cacheResult(ForecastingViewMaster forecastingViewMaster);

	/**
	* Caches the forecasting view masters in the entity cache if it is enabled.
	*
	* @param forecastingViewMasters the forecasting view masters
	*/
	public void cacheResult(
		java.util.List<ForecastingViewMaster> forecastingViewMasters);

	/**
	* Creates a new forecasting view master with the primary key. Does not add the forecasting view master to the database.
	*
	* @param viewId the primary key for the new forecasting view master
	* @return the new forecasting view master
	*/
	public ForecastingViewMaster create(int viewId);

	/**
	* Removes the forecasting view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param viewId the primary key of the forecasting view master
	* @return the forecasting view master that was removed
	* @throws NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
	*/
	public ForecastingViewMaster remove(int viewId)
		throws NoSuchForecastingViewMasterException;

	public ForecastingViewMaster updateImpl(
		ForecastingViewMaster forecastingViewMaster);

	/**
	* Returns the forecasting view master with the primary key or throws a {@link NoSuchForecastingViewMasterException} if it could not be found.
	*
	* @param viewId the primary key of the forecasting view master
	* @return the forecasting view master
	* @throws NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
	*/
	public ForecastingViewMaster findByPrimaryKey(int viewId)
		throws NoSuchForecastingViewMasterException;

	/**
	* Returns the forecasting view master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param viewId the primary key of the forecasting view master
	* @return the forecasting view master, or <code>null</code> if a forecasting view master with the primary key could not be found
	*/
	public ForecastingViewMaster fetchByPrimaryKey(int viewId);

	@Override
	public java.util.Map<java.io.Serializable, ForecastingViewMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the forecasting view masters.
	*
	* @return the forecasting view masters
	*/
	public java.util.List<ForecastingViewMaster> findAll();

	/**
	* Returns a range of all the forecasting view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting view masters
	* @param end the upper bound of the range of forecasting view masters (not inclusive)
	* @return the range of forecasting view masters
	*/
	public java.util.List<ForecastingViewMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the forecasting view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting view masters
	* @param end the upper bound of the range of forecasting view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of forecasting view masters
	*/
	public java.util.List<ForecastingViewMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingViewMaster> orderByComparator);

	/**
	* Returns an ordered range of all the forecasting view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting view masters
	* @param end the upper bound of the range of forecasting view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of forecasting view masters
	*/
	public java.util.List<ForecastingViewMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingViewMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the forecasting view masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of forecasting view masters.
	*
	* @return the number of forecasting view masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}