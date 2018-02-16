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

import com.stpl.app.exception.NoSuchStNmAssumptionsException;
import com.stpl.app.model.StNmAssumptions;

/**
 * The persistence interface for the st nm assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StNmAssumptionsPersistenceImpl
 * @see StNmAssumptionsUtil
 * @generated
 */
@ProviderType
public interface StNmAssumptionsPersistence extends BasePersistence<StNmAssumptions> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StNmAssumptionsUtil} to access the st nm assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st nm assumptions in the entity cache if it is enabled.
	*
	* @param stNmAssumptions the st nm assumptions
	*/
	public void cacheResult(StNmAssumptions stNmAssumptions);

	/**
	* Caches the st nm assumptionses in the entity cache if it is enabled.
	*
	* @param stNmAssumptionses the st nm assumptionses
	*/
	public void cacheResult(java.util.List<StNmAssumptions> stNmAssumptionses);

	/**
	* Creates a new st nm assumptions with the primary key. Does not add the st nm assumptions to the database.
	*
	* @param stNmAssumptionsPK the primary key for the new st nm assumptions
	* @return the new st nm assumptions
	*/
	public StNmAssumptions create(StNmAssumptionsPK stNmAssumptionsPK);

	/**
	* Removes the st nm assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmAssumptionsPK the primary key of the st nm assumptions
	* @return the st nm assumptions that was removed
	* @throws NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
	*/
	public StNmAssumptions remove(StNmAssumptionsPK stNmAssumptionsPK)
		throws NoSuchStNmAssumptionsException;

	public StNmAssumptions updateImpl(StNmAssumptions stNmAssumptions);

	/**
	* Returns the st nm assumptions with the primary key or throws a {@link NoSuchStNmAssumptionsException} if it could not be found.
	*
	* @param stNmAssumptionsPK the primary key of the st nm assumptions
	* @return the st nm assumptions
	* @throws NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
	*/
	public StNmAssumptions findByPrimaryKey(StNmAssumptionsPK stNmAssumptionsPK)
		throws NoSuchStNmAssumptionsException;

	/**
	* Returns the st nm assumptions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmAssumptionsPK the primary key of the st nm assumptions
	* @return the st nm assumptions, or <code>null</code> if a st nm assumptions with the primary key could not be found
	*/
	public StNmAssumptions fetchByPrimaryKey(
		StNmAssumptionsPK stNmAssumptionsPK);

	@Override
	public java.util.Map<java.io.Serializable, StNmAssumptions> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st nm assumptionses.
	*
	* @return the st nm assumptionses
	*/
	public java.util.List<StNmAssumptions> findAll();

	/**
	* Returns a range of all the st nm assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm assumptionses
	* @param end the upper bound of the range of st nm assumptionses (not inclusive)
	* @return the range of st nm assumptionses
	*/
	public java.util.List<StNmAssumptions> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st nm assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm assumptionses
	* @param end the upper bound of the range of st nm assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm assumptionses
	*/
	public java.util.List<StNmAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmAssumptions> orderByComparator);

	/**
	* Returns an ordered range of all the st nm assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm assumptionses
	* @param end the upper bound of the range of st nm assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm assumptionses
	*/
	public java.util.List<StNmAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmAssumptions> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st nm assumptionses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st nm assumptionses.
	*
	* @return the number of st nm assumptionses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}