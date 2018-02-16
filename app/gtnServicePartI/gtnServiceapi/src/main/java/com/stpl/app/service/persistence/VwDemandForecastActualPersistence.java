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

import com.stpl.app.exception.NoSuchVwDemandForecastActualException;
import com.stpl.app.model.VwDemandForecastActual;

/**
 * The persistence interface for the vw demand forecast actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.VwDemandForecastActualPersistenceImpl
 * @see VwDemandForecastActualUtil
 * @generated
 */
@ProviderType
public interface VwDemandForecastActualPersistence extends BasePersistence<VwDemandForecastActual> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwDemandForecastActualUtil} to access the vw demand forecast actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw demand forecast actual in the entity cache if it is enabled.
	*
	* @param vwDemandForecastActual the vw demand forecast actual
	*/
	public void cacheResult(VwDemandForecastActual vwDemandForecastActual);

	/**
	* Caches the vw demand forecast actuals in the entity cache if it is enabled.
	*
	* @param vwDemandForecastActuals the vw demand forecast actuals
	*/
	public void cacheResult(
		java.util.List<VwDemandForecastActual> vwDemandForecastActuals);

	/**
	* Creates a new vw demand forecast actual with the primary key. Does not add the vw demand forecast actual to the database.
	*
	* @param demandForecastActualSid the primary key for the new vw demand forecast actual
	* @return the new vw demand forecast actual
	*/
	public VwDemandForecastActual create(int demandForecastActualSid);

	/**
	* Removes the vw demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param demandForecastActualSid the primary key of the vw demand forecast actual
	* @return the vw demand forecast actual that was removed
	* @throws NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
	*/
	public VwDemandForecastActual remove(int demandForecastActualSid)
		throws NoSuchVwDemandForecastActualException;

	public VwDemandForecastActual updateImpl(
		VwDemandForecastActual vwDemandForecastActual);

	/**
	* Returns the vw demand forecast actual with the primary key or throws a {@link NoSuchVwDemandForecastActualException} if it could not be found.
	*
	* @param demandForecastActualSid the primary key of the vw demand forecast actual
	* @return the vw demand forecast actual
	* @throws NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
	*/
	public VwDemandForecastActual findByPrimaryKey(int demandForecastActualSid)
		throws NoSuchVwDemandForecastActualException;

	/**
	* Returns the vw demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param demandForecastActualSid the primary key of the vw demand forecast actual
	* @return the vw demand forecast actual, or <code>null</code> if a vw demand forecast actual with the primary key could not be found
	*/
	public VwDemandForecastActual fetchByPrimaryKey(int demandForecastActualSid);

	@Override
	public java.util.Map<java.io.Serializable, VwDemandForecastActual> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw demand forecast actuals.
	*
	* @return the vw demand forecast actuals
	*/
	public java.util.List<VwDemandForecastActual> findAll();

	/**
	* Returns a range of all the vw demand forecast actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw demand forecast actuals
	* @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
	* @return the range of vw demand forecast actuals
	*/
	public java.util.List<VwDemandForecastActual> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vw demand forecast actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw demand forecast actuals
	* @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw demand forecast actuals
	*/
	public java.util.List<VwDemandForecastActual> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwDemandForecastActual> orderByComparator);

	/**
	* Returns an ordered range of all the vw demand forecast actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw demand forecast actuals
	* @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw demand forecast actuals
	*/
	public java.util.List<VwDemandForecastActual> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwDemandForecastActual> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw demand forecast actuals from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw demand forecast actuals.
	*
	* @return the number of vw demand forecast actuals
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}