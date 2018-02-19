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

import com.stpl.app.exception.NoSuchNationalAssumptionsException;
import com.stpl.app.model.NationalAssumptions;

/**
 * The persistence interface for the national assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.NationalAssumptionsPersistenceImpl
 * @see NationalAssumptionsUtil
 * @generated
 */
@ProviderType
public interface NationalAssumptionsPersistence extends BasePersistence<NationalAssumptions> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NationalAssumptionsUtil} to access the national assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the national assumptions in the entity cache if it is enabled.
	*
	* @param nationalAssumptions the national assumptions
	*/
	public void cacheResult(NationalAssumptions nationalAssumptions);

	/**
	* Caches the national assumptionses in the entity cache if it is enabled.
	*
	* @param nationalAssumptionses the national assumptionses
	*/
	public void cacheResult(
		java.util.List<NationalAssumptions> nationalAssumptionses);

	/**
	* Creates a new national assumptions with the primary key. Does not add the national assumptions to the database.
	*
	* @param nationalAssumptionsPK the primary key for the new national assumptions
	* @return the new national assumptions
	*/
	public NationalAssumptions create(
		NationalAssumptionsPK nationalAssumptionsPK);

	/**
	* Removes the national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsPK the primary key of the national assumptions
	* @return the national assumptions that was removed
	* @throws NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
	*/
	public NationalAssumptions remove(
		NationalAssumptionsPK nationalAssumptionsPK)
		throws NoSuchNationalAssumptionsException;

	public NationalAssumptions updateImpl(
		NationalAssumptions nationalAssumptions);

	/**
	* Returns the national assumptions with the primary key or throws a {@link NoSuchNationalAssumptionsException} if it could not be found.
	*
	* @param nationalAssumptionsPK the primary key of the national assumptions
	* @return the national assumptions
	* @throws NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
	*/
	public NationalAssumptions findByPrimaryKey(
		NationalAssumptionsPK nationalAssumptionsPK)
		throws NoSuchNationalAssumptionsException;

	/**
	* Returns the national assumptions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nationalAssumptionsPK the primary key of the national assumptions
	* @return the national assumptions, or <code>null</code> if a national assumptions with the primary key could not be found
	*/
	public NationalAssumptions fetchByPrimaryKey(
		NationalAssumptionsPK nationalAssumptionsPK);

	@Override
	public java.util.Map<java.io.Serializable, NationalAssumptions> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the national assumptionses.
	*
	* @return the national assumptionses
	*/
	public java.util.List<NationalAssumptions> findAll();

	/**
	* Returns a range of all the national assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptionses
	* @param end the upper bound of the range of national assumptionses (not inclusive)
	* @return the range of national assumptionses
	*/
	public java.util.List<NationalAssumptions> findAll(int start, int end);

	/**
	* Returns an ordered range of all the national assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptionses
	* @param end the upper bound of the range of national assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of national assumptionses
	*/
	public java.util.List<NationalAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NationalAssumptions> orderByComparator);

	/**
	* Returns an ordered range of all the national assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptionses
	* @param end the upper bound of the range of national assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of national assumptionses
	*/
	public java.util.List<NationalAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NationalAssumptions> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the national assumptionses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of national assumptionses.
	*
	* @return the number of national assumptionses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}