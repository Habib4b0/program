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

import com.stpl.app.exception.NoSuchStMAssumptionsException;
import com.stpl.app.model.StMAssumptions;

/**
 * The persistence interface for the st m assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StMAssumptionsPersistenceImpl
 * @see StMAssumptionsUtil
 * @generated
 */
@ProviderType
public interface StMAssumptionsPersistence extends BasePersistence<StMAssumptions> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StMAssumptionsUtil} to access the st m assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st m assumptions in the entity cache if it is enabled.
	*
	* @param stMAssumptions the st m assumptions
	*/
	public void cacheResult(StMAssumptions stMAssumptions);

	/**
	* Caches the st m assumptionses in the entity cache if it is enabled.
	*
	* @param stMAssumptionses the st m assumptionses
	*/
	public void cacheResult(java.util.List<StMAssumptions> stMAssumptionses);

	/**
	* Creates a new st m assumptions with the primary key. Does not add the st m assumptions to the database.
	*
	* @param stMAssumptionsPK the primary key for the new st m assumptions
	* @return the new st m assumptions
	*/
	public StMAssumptions create(StMAssumptionsPK stMAssumptionsPK);

	/**
	* Removes the st m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stMAssumptionsPK the primary key of the st m assumptions
	* @return the st m assumptions that was removed
	* @throws NoSuchStMAssumptionsException if a st m assumptions with the primary key could not be found
	*/
	public StMAssumptions remove(StMAssumptionsPK stMAssumptionsPK)
		throws NoSuchStMAssumptionsException;

	public StMAssumptions updateImpl(StMAssumptions stMAssumptions);

	/**
	* Returns the st m assumptions with the primary key or throws a {@link NoSuchStMAssumptionsException} if it could not be found.
	*
	* @param stMAssumptionsPK the primary key of the st m assumptions
	* @return the st m assumptions
	* @throws NoSuchStMAssumptionsException if a st m assumptions with the primary key could not be found
	*/
	public StMAssumptions findByPrimaryKey(StMAssumptionsPK stMAssumptionsPK)
		throws NoSuchStMAssumptionsException;

	/**
	* Returns the st m assumptions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stMAssumptionsPK the primary key of the st m assumptions
	* @return the st m assumptions, or <code>null</code> if a st m assumptions with the primary key could not be found
	*/
	public StMAssumptions fetchByPrimaryKey(StMAssumptionsPK stMAssumptionsPK);

	@Override
	public java.util.Map<java.io.Serializable, StMAssumptions> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st m assumptionses.
	*
	* @return the st m assumptionses
	*/
	public java.util.List<StMAssumptions> findAll();

	/**
	* Returns a range of all the st m assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m assumptionses
	* @param end the upper bound of the range of st m assumptionses (not inclusive)
	* @return the range of st m assumptionses
	*/
	public java.util.List<StMAssumptions> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st m assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m assumptionses
	* @param end the upper bound of the range of st m assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st m assumptionses
	*/
	public java.util.List<StMAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StMAssumptions> orderByComparator);

	/**
	* Returns an ordered range of all the st m assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m assumptionses
	* @param end the upper bound of the range of st m assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st m assumptionses
	*/
	public java.util.List<StMAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StMAssumptions> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st m assumptionses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st m assumptionses.
	*
	* @return the number of st m assumptionses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}