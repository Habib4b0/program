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

import com.stpl.app.exception.NoSuchPhsActualsException;
import com.stpl.app.model.PhsActuals;

/**
 * The persistence interface for the phs actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.PhsActualsPersistenceImpl
 * @see PhsActualsUtil
 * @generated
 */
@ProviderType
public interface PhsActualsPersistence extends BasePersistence<PhsActuals> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PhsActualsUtil} to access the phs actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the phs actuals in the entity cache if it is enabled.
	*
	* @param phsActuals the phs actuals
	*/
	public void cacheResult(PhsActuals phsActuals);

	/**
	* Caches the phs actualses in the entity cache if it is enabled.
	*
	* @param phsActualses the phs actualses
	*/
	public void cacheResult(java.util.List<PhsActuals> phsActualses);

	/**
	* Creates a new phs actuals with the primary key. Does not add the phs actuals to the database.
	*
	* @param phsActualsPK the primary key for the new phs actuals
	* @return the new phs actuals
	*/
	public PhsActuals create(PhsActualsPK phsActualsPK);

	/**
	* Removes the phs actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param phsActualsPK the primary key of the phs actuals
	* @return the phs actuals that was removed
	* @throws NoSuchPhsActualsException if a phs actuals with the primary key could not be found
	*/
	public PhsActuals remove(PhsActualsPK phsActualsPK)
		throws NoSuchPhsActualsException;

	public PhsActuals updateImpl(PhsActuals phsActuals);

	/**
	* Returns the phs actuals with the primary key or throws a {@link NoSuchPhsActualsException} if it could not be found.
	*
	* @param phsActualsPK the primary key of the phs actuals
	* @return the phs actuals
	* @throws NoSuchPhsActualsException if a phs actuals with the primary key could not be found
	*/
	public PhsActuals findByPrimaryKey(PhsActualsPK phsActualsPK)
		throws NoSuchPhsActualsException;

	/**
	* Returns the phs actuals with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param phsActualsPK the primary key of the phs actuals
	* @return the phs actuals, or <code>null</code> if a phs actuals with the primary key could not be found
	*/
	public PhsActuals fetchByPrimaryKey(PhsActualsPK phsActualsPK);

	@Override
	public java.util.Map<java.io.Serializable, PhsActuals> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the phs actualses.
	*
	* @return the phs actualses
	*/
	public java.util.List<PhsActuals> findAll();

	/**
	* Returns a range of all the phs actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phs actualses
	* @param end the upper bound of the range of phs actualses (not inclusive)
	* @return the range of phs actualses
	*/
	public java.util.List<PhsActuals> findAll(int start, int end);

	/**
	* Returns an ordered range of all the phs actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phs actualses
	* @param end the upper bound of the range of phs actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of phs actualses
	*/
	public java.util.List<PhsActuals> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PhsActuals> orderByComparator);

	/**
	* Returns an ordered range of all the phs actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phs actualses
	* @param end the upper bound of the range of phs actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of phs actualses
	*/
	public java.util.List<PhsActuals> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PhsActuals> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the phs actualses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of phs actualses.
	*
	* @return the number of phs actualses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}