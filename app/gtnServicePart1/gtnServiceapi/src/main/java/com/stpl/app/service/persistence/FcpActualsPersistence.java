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

import com.stpl.app.exception.NoSuchFcpActualsException;
import com.stpl.app.model.FcpActuals;

/**
 * The persistence interface for the fcp actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.FcpActualsPersistenceImpl
 * @see FcpActualsUtil
 * @generated
 */
@ProviderType
public interface FcpActualsPersistence extends BasePersistence<FcpActuals> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FcpActualsUtil} to access the fcp actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the fcp actuals in the entity cache if it is enabled.
	*
	* @param fcpActuals the fcp actuals
	*/
	public void cacheResult(FcpActuals fcpActuals);

	/**
	* Caches the fcp actualses in the entity cache if it is enabled.
	*
	* @param fcpActualses the fcp actualses
	*/
	public void cacheResult(java.util.List<FcpActuals> fcpActualses);

	/**
	* Creates a new fcp actuals with the primary key. Does not add the fcp actuals to the database.
	*
	* @param fcpActualsPK the primary key for the new fcp actuals
	* @return the new fcp actuals
	*/
	public FcpActuals create(FcpActualsPK fcpActualsPK);

	/**
	* Removes the fcp actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fcpActualsPK the primary key of the fcp actuals
	* @return the fcp actuals that was removed
	* @throws NoSuchFcpActualsException if a fcp actuals with the primary key could not be found
	*/
	public FcpActuals remove(FcpActualsPK fcpActualsPK)
		throws NoSuchFcpActualsException;

	public FcpActuals updateImpl(FcpActuals fcpActuals);

	/**
	* Returns the fcp actuals with the primary key or throws a {@link NoSuchFcpActualsException} if it could not be found.
	*
	* @param fcpActualsPK the primary key of the fcp actuals
	* @return the fcp actuals
	* @throws NoSuchFcpActualsException if a fcp actuals with the primary key could not be found
	*/
	public FcpActuals findByPrimaryKey(FcpActualsPK fcpActualsPK)
		throws NoSuchFcpActualsException;

	/**
	* Returns the fcp actuals with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fcpActualsPK the primary key of the fcp actuals
	* @return the fcp actuals, or <code>null</code> if a fcp actuals with the primary key could not be found
	*/
	public FcpActuals fetchByPrimaryKey(FcpActualsPK fcpActualsPK);

	@Override
	public java.util.Map<java.io.Serializable, FcpActuals> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the fcp actualses.
	*
	* @return the fcp actualses
	*/
	public java.util.List<FcpActuals> findAll();

	/**
	* Returns a range of all the fcp actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fcp actualses
	* @param end the upper bound of the range of fcp actualses (not inclusive)
	* @return the range of fcp actualses
	*/
	public java.util.List<FcpActuals> findAll(int start, int end);

	/**
	* Returns an ordered range of all the fcp actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fcp actualses
	* @param end the upper bound of the range of fcp actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of fcp actualses
	*/
	public java.util.List<FcpActuals> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FcpActuals> orderByComparator);

	/**
	* Returns an ordered range of all the fcp actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fcp actualses
	* @param end the upper bound of the range of fcp actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of fcp actualses
	*/
	public java.util.List<FcpActuals> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FcpActuals> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the fcp actualses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of fcp actualses.
	*
	* @return the number of fcp actualses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}