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

import com.stpl.app.exception.NoSuchChAssumptionsException;
import com.stpl.app.model.ChAssumptions;

/**
 * The persistence interface for the ch assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ChAssumptionsPersistenceImpl
 * @see ChAssumptionsUtil
 * @generated
 */
@ProviderType
public interface ChAssumptionsPersistence extends BasePersistence<ChAssumptions> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChAssumptionsUtil} to access the ch assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ch assumptions in the entity cache if it is enabled.
	*
	* @param chAssumptions the ch assumptions
	*/
	public void cacheResult(ChAssumptions chAssumptions);

	/**
	* Caches the ch assumptionses in the entity cache if it is enabled.
	*
	* @param chAssumptionses the ch assumptionses
	*/
	public void cacheResult(java.util.List<ChAssumptions> chAssumptionses);

	/**
	* Creates a new ch assumptions with the primary key. Does not add the ch assumptions to the database.
	*
	* @param chAssumptionsSid the primary key for the new ch assumptions
	* @return the new ch assumptions
	*/
	public ChAssumptions create(int chAssumptionsSid);

	/**
	* Removes the ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chAssumptionsSid the primary key of the ch assumptions
	* @return the ch assumptions that was removed
	* @throws NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
	*/
	public ChAssumptions remove(int chAssumptionsSid)
		throws NoSuchChAssumptionsException;

	public ChAssumptions updateImpl(ChAssumptions chAssumptions);

	/**
	* Returns the ch assumptions with the primary key or throws a {@link NoSuchChAssumptionsException} if it could not be found.
	*
	* @param chAssumptionsSid the primary key of the ch assumptions
	* @return the ch assumptions
	* @throws NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
	*/
	public ChAssumptions findByPrimaryKey(int chAssumptionsSid)
		throws NoSuchChAssumptionsException;

	/**
	* Returns the ch assumptions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chAssumptionsSid the primary key of the ch assumptions
	* @return the ch assumptions, or <code>null</code> if a ch assumptions with the primary key could not be found
	*/
	public ChAssumptions fetchByPrimaryKey(int chAssumptionsSid);

	@Override
	public java.util.Map<java.io.Serializable, ChAssumptions> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ch assumptionses.
	*
	* @return the ch assumptionses
	*/
	public java.util.List<ChAssumptions> findAll();

	/**
	* Returns a range of all the ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch assumptionses
	* @param end the upper bound of the range of ch assumptionses (not inclusive)
	* @return the range of ch assumptionses
	*/
	public java.util.List<ChAssumptions> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch assumptionses
	* @param end the upper bound of the range of ch assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ch assumptionses
	*/
	public java.util.List<ChAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChAssumptions> orderByComparator);

	/**
	* Returns an ordered range of all the ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch assumptionses
	* @param end the upper bound of the range of ch assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ch assumptionses
	*/
	public java.util.List<ChAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChAssumptions> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ch assumptionses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ch assumptionses.
	*
	* @return the number of ch assumptionses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}