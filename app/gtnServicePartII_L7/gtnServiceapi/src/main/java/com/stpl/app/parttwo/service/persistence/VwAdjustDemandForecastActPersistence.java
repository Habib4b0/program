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

import com.stpl.app.parttwo.exception.NoSuchVwAdjustDemandForecastActException;
import com.stpl.app.parttwo.model.VwAdjustDemandForecastAct;

/**
 * The persistence interface for the vw adjust demand forecast act service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.VwAdjustDemandForecastActPersistenceImpl
 * @see VwAdjustDemandForecastActUtil
 * @generated
 */
@ProviderType
public interface VwAdjustDemandForecastActPersistence extends BasePersistence<VwAdjustDemandForecastAct> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwAdjustDemandForecastActUtil} to access the vw adjust demand forecast act persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw adjust demand forecast act in the entity cache if it is enabled.
	*
	* @param vwAdjustDemandForecastAct the vw adjust demand forecast act
	*/
	public void cacheResult(VwAdjustDemandForecastAct vwAdjustDemandForecastAct);

	/**
	* Caches the vw adjust demand forecast acts in the entity cache if it is enabled.
	*
	* @param vwAdjustDemandForecastActs the vw adjust demand forecast acts
	*/
	public void cacheResult(
		java.util.List<VwAdjustDemandForecastAct> vwAdjustDemandForecastActs);

	/**
	* Creates a new vw adjust demand forecast act with the primary key. Does not add the vw adjust demand forecast act to the database.
	*
	* @param adjustedDemandForecastId the primary key for the new vw adjust demand forecast act
	* @return the new vw adjust demand forecast act
	*/
	public VwAdjustDemandForecastAct create(int adjustedDemandForecastId);

	/**
	* Removes the vw adjust demand forecast act with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
	* @return the vw adjust demand forecast act that was removed
	* @throws NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
	*/
	public VwAdjustDemandForecastAct remove(int adjustedDemandForecastId)
		throws NoSuchVwAdjustDemandForecastActException;

	public VwAdjustDemandForecastAct updateImpl(
		VwAdjustDemandForecastAct vwAdjustDemandForecastAct);

	/**
	* Returns the vw adjust demand forecast act with the primary key or throws a {@link NoSuchVwAdjustDemandForecastActException} if it could not be found.
	*
	* @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
	* @return the vw adjust demand forecast act
	* @throws NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
	*/
	public VwAdjustDemandForecastAct findByPrimaryKey(
		int adjustedDemandForecastId)
		throws NoSuchVwAdjustDemandForecastActException;

	/**
	* Returns the vw adjust demand forecast act with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
	* @return the vw adjust demand forecast act, or <code>null</code> if a vw adjust demand forecast act with the primary key could not be found
	*/
	public VwAdjustDemandForecastAct fetchByPrimaryKey(
		int adjustedDemandForecastId);

	@Override
	public java.util.Map<java.io.Serializable, VwAdjustDemandForecastAct> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw adjust demand forecast acts.
	*
	* @return the vw adjust demand forecast acts
	*/
	public java.util.List<VwAdjustDemandForecastAct> findAll();

	/**
	* Returns a range of all the vw adjust demand forecast acts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw adjust demand forecast acts
	* @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
	* @return the range of vw adjust demand forecast acts
	*/
	public java.util.List<VwAdjustDemandForecastAct> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vw adjust demand forecast acts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw adjust demand forecast acts
	* @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw adjust demand forecast acts
	*/
	public java.util.List<VwAdjustDemandForecastAct> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwAdjustDemandForecastAct> orderByComparator);

	/**
	* Returns an ordered range of all the vw adjust demand forecast acts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw adjust demand forecast acts
	* @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw adjust demand forecast acts
	*/
	public java.util.List<VwAdjustDemandForecastAct> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwAdjustDemandForecastAct> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw adjust demand forecast acts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw adjust demand forecast acts.
	*
	* @return the number of vw adjust demand forecast acts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}