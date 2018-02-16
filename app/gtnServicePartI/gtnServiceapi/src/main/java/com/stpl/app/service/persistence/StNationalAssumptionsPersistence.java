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

import com.stpl.app.exception.NoSuchStNationalAssumptionsException;
import com.stpl.app.model.StNationalAssumptions;

/**
 * The persistence interface for the st national assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StNationalAssumptionsPersistenceImpl
 * @see StNationalAssumptionsUtil
 * @generated
 */
@ProviderType
public interface StNationalAssumptionsPersistence extends BasePersistence<StNationalAssumptions> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StNationalAssumptionsUtil} to access the st national assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st national assumptions in the entity cache if it is enabled.
	*
	* @param stNationalAssumptions the st national assumptions
	*/
	public void cacheResult(StNationalAssumptions stNationalAssumptions);

	/**
	* Caches the st national assumptionses in the entity cache if it is enabled.
	*
	* @param stNationalAssumptionses the st national assumptionses
	*/
	public void cacheResult(
		java.util.List<StNationalAssumptions> stNationalAssumptionses);

	/**
	* Creates a new st national assumptions with the primary key. Does not add the st national assumptions to the database.
	*
	* @param stNationalAssumptionsPK the primary key for the new st national assumptions
	* @return the new st national assumptions
	*/
	public StNationalAssumptions create(
		StNationalAssumptionsPK stNationalAssumptionsPK);

	/**
	* Removes the st national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNationalAssumptionsPK the primary key of the st national assumptions
	* @return the st national assumptions that was removed
	* @throws NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
	*/
	public StNationalAssumptions remove(
		StNationalAssumptionsPK stNationalAssumptionsPK)
		throws NoSuchStNationalAssumptionsException;

	public StNationalAssumptions updateImpl(
		StNationalAssumptions stNationalAssumptions);

	/**
	* Returns the st national assumptions with the primary key or throws a {@link NoSuchStNationalAssumptionsException} if it could not be found.
	*
	* @param stNationalAssumptionsPK the primary key of the st national assumptions
	* @return the st national assumptions
	* @throws NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
	*/
	public StNationalAssumptions findByPrimaryKey(
		StNationalAssumptionsPK stNationalAssumptionsPK)
		throws NoSuchStNationalAssumptionsException;

	/**
	* Returns the st national assumptions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNationalAssumptionsPK the primary key of the st national assumptions
	* @return the st national assumptions, or <code>null</code> if a st national assumptions with the primary key could not be found
	*/
	public StNationalAssumptions fetchByPrimaryKey(
		StNationalAssumptionsPK stNationalAssumptionsPK);

	@Override
	public java.util.Map<java.io.Serializable, StNationalAssumptions> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st national assumptionses.
	*
	* @return the st national assumptionses
	*/
	public java.util.List<StNationalAssumptions> findAll();

	/**
	* Returns a range of all the st national assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st national assumptionses
	* @param end the upper bound of the range of st national assumptionses (not inclusive)
	* @return the range of st national assumptionses
	*/
	public java.util.List<StNationalAssumptions> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st national assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st national assumptionses
	* @param end the upper bound of the range of st national assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st national assumptionses
	*/
	public java.util.List<StNationalAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNationalAssumptions> orderByComparator);

	/**
	* Returns an ordered range of all the st national assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st national assumptionses
	* @param end the upper bound of the range of st national assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st national assumptionses
	*/
	public java.util.List<StNationalAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNationalAssumptions> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st national assumptionses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st national assumptionses.
	*
	* @return the number of st national assumptionses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}