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

import com.stpl.app.exception.NoSuchNationalAssumptionsActualsException;
import com.stpl.app.model.NationalAssumptionsActuals;

/**
 * The persistence interface for the national assumptions actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.NationalAssumptionsActualsPersistenceImpl
 * @see NationalAssumptionsActualsUtil
 * @generated
 */
@ProviderType
public interface NationalAssumptionsActualsPersistence extends BasePersistence<NationalAssumptionsActuals> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NationalAssumptionsActualsUtil} to access the national assumptions actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the national assumptions actuals in the entity cache if it is enabled.
	*
	* @param nationalAssumptionsActuals the national assumptions actuals
	*/
	public void cacheResult(
		NationalAssumptionsActuals nationalAssumptionsActuals);

	/**
	* Caches the national assumptions actualses in the entity cache if it is enabled.
	*
	* @param nationalAssumptionsActualses the national assumptions actualses
	*/
	public void cacheResult(
		java.util.List<NationalAssumptionsActuals> nationalAssumptionsActualses);

	/**
	* Creates a new national assumptions actuals with the primary key. Does not add the national assumptions actuals to the database.
	*
	* @param nationalAssumptionsActualsPK the primary key for the new national assumptions actuals
	* @return the new national assumptions actuals
	*/
	public NationalAssumptionsActuals create(
		NationalAssumptionsActualsPK nationalAssumptionsActualsPK);

	/**
	* Removes the national assumptions actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
	* @return the national assumptions actuals that was removed
	* @throws NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
	*/
	public NationalAssumptionsActuals remove(
		NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
		throws NoSuchNationalAssumptionsActualsException;

	public NationalAssumptionsActuals updateImpl(
		NationalAssumptionsActuals nationalAssumptionsActuals);

	/**
	* Returns the national assumptions actuals with the primary key or throws a {@link NoSuchNationalAssumptionsActualsException} if it could not be found.
	*
	* @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
	* @return the national assumptions actuals
	* @throws NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
	*/
	public NationalAssumptionsActuals findByPrimaryKey(
		NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
		throws NoSuchNationalAssumptionsActualsException;

	/**
	* Returns the national assumptions actuals with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
	* @return the national assumptions actuals, or <code>null</code> if a national assumptions actuals with the primary key could not be found
	*/
	public NationalAssumptionsActuals fetchByPrimaryKey(
		NationalAssumptionsActualsPK nationalAssumptionsActualsPK);

	@Override
	public java.util.Map<java.io.Serializable, NationalAssumptionsActuals> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the national assumptions actualses.
	*
	* @return the national assumptions actualses
	*/
	public java.util.List<NationalAssumptionsActuals> findAll();

	/**
	* Returns a range of all the national assumptions actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptions actualses
	* @param end the upper bound of the range of national assumptions actualses (not inclusive)
	* @return the range of national assumptions actualses
	*/
	public java.util.List<NationalAssumptionsActuals> findAll(int start, int end);

	/**
	* Returns an ordered range of all the national assumptions actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptions actualses
	* @param end the upper bound of the range of national assumptions actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of national assumptions actualses
	*/
	public java.util.List<NationalAssumptionsActuals> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<NationalAssumptionsActuals> orderByComparator);

	/**
	* Returns an ordered range of all the national assumptions actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptions actualses
	* @param end the upper bound of the range of national assumptions actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of national assumptions actualses
	*/
	public java.util.List<NationalAssumptionsActuals> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<NationalAssumptionsActuals> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the national assumptions actualses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of national assumptions actualses.
	*
	* @return the number of national assumptions actualses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}