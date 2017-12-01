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

import com.stpl.app.exception.NoSuchStChAssumptionsException;
import com.stpl.app.model.StChAssumptions;

/**
 * The persistence interface for the st ch assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StChAssumptionsPersistenceImpl
 * @see StChAssumptionsUtil
 * @generated
 */
@ProviderType
public interface StChAssumptionsPersistence extends BasePersistence<StChAssumptions> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StChAssumptionsUtil} to access the st ch assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st ch assumptions in the entity cache if it is enabled.
	*
	* @param stChAssumptions the st ch assumptions
	*/
	public void cacheResult(StChAssumptions stChAssumptions);

	/**
	* Caches the st ch assumptionses in the entity cache if it is enabled.
	*
	* @param stChAssumptionses the st ch assumptionses
	*/
	public void cacheResult(java.util.List<StChAssumptions> stChAssumptionses);

	/**
	* Creates a new st ch assumptions with the primary key. Does not add the st ch assumptions to the database.
	*
	* @param stChAssumptionsPK the primary key for the new st ch assumptions
	* @return the new st ch assumptions
	*/
	public StChAssumptions create(StChAssumptionsPK stChAssumptionsPK);

	/**
	* Removes the st ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stChAssumptionsPK the primary key of the st ch assumptions
	* @return the st ch assumptions that was removed
	* @throws NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
	*/
	public StChAssumptions remove(StChAssumptionsPK stChAssumptionsPK)
		throws NoSuchStChAssumptionsException;

	public StChAssumptions updateImpl(StChAssumptions stChAssumptions);

	/**
	* Returns the st ch assumptions with the primary key or throws a {@link NoSuchStChAssumptionsException} if it could not be found.
	*
	* @param stChAssumptionsPK the primary key of the st ch assumptions
	* @return the st ch assumptions
	* @throws NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
	*/
	public StChAssumptions findByPrimaryKey(StChAssumptionsPK stChAssumptionsPK)
		throws NoSuchStChAssumptionsException;

	/**
	* Returns the st ch assumptions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stChAssumptionsPK the primary key of the st ch assumptions
	* @return the st ch assumptions, or <code>null</code> if a st ch assumptions with the primary key could not be found
	*/
	public StChAssumptions fetchByPrimaryKey(
		StChAssumptionsPK stChAssumptionsPK);

	@Override
	public java.util.Map<java.io.Serializable, StChAssumptions> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st ch assumptionses.
	*
	* @return the st ch assumptionses
	*/
	public java.util.List<StChAssumptions> findAll();

	/**
	* Returns a range of all the st ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch assumptionses
	* @param end the upper bound of the range of st ch assumptionses (not inclusive)
	* @return the range of st ch assumptionses
	*/
	public java.util.List<StChAssumptions> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch assumptionses
	* @param end the upper bound of the range of st ch assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st ch assumptionses
	*/
	public java.util.List<StChAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StChAssumptions> orderByComparator);

	/**
	* Returns an ordered range of all the st ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch assumptionses
	* @param end the upper bound of the range of st ch assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st ch assumptionses
	*/
	public java.util.List<StChAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StChAssumptions> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st ch assumptionses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st ch assumptionses.
	*
	* @return the number of st ch assumptionses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}