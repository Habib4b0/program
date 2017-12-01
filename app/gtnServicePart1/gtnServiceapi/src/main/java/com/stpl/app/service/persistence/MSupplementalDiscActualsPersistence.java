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

import com.stpl.app.exception.NoSuchMSupplementalDiscActualsException;
import com.stpl.app.model.MSupplementalDiscActuals;

/**
 * The persistence interface for the m supplemental disc actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.MSupplementalDiscActualsPersistenceImpl
 * @see MSupplementalDiscActualsUtil
 * @generated
 */
@ProviderType
public interface MSupplementalDiscActualsPersistence extends BasePersistence<MSupplementalDiscActuals> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MSupplementalDiscActualsUtil} to access the m supplemental disc actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the m supplemental disc actuals in the entity cache if it is enabled.
	*
	* @param mSupplementalDiscActuals the m supplemental disc actuals
	*/
	public void cacheResult(MSupplementalDiscActuals mSupplementalDiscActuals);

	/**
	* Caches the m supplemental disc actualses in the entity cache if it is enabled.
	*
	* @param mSupplementalDiscActualses the m supplemental disc actualses
	*/
	public void cacheResult(
		java.util.List<MSupplementalDiscActuals> mSupplementalDiscActualses);

	/**
	* Creates a new m supplemental disc actuals with the primary key. Does not add the m supplemental disc actuals to the database.
	*
	* @param mSupplementalDiscActualsPK the primary key for the new m supplemental disc actuals
	* @return the new m supplemental disc actuals
	*/
	public MSupplementalDiscActuals create(
		MSupplementalDiscActualsPK mSupplementalDiscActualsPK);

	/**
	* Removes the m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
	* @return the m supplemental disc actuals that was removed
	* @throws NoSuchMSupplementalDiscActualsException if a m supplemental disc actuals with the primary key could not be found
	*/
	public MSupplementalDiscActuals remove(
		MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
		throws NoSuchMSupplementalDiscActualsException;

	public MSupplementalDiscActuals updateImpl(
		MSupplementalDiscActuals mSupplementalDiscActuals);

	/**
	* Returns the m supplemental disc actuals with the primary key or throws a {@link NoSuchMSupplementalDiscActualsException} if it could not be found.
	*
	* @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
	* @return the m supplemental disc actuals
	* @throws NoSuchMSupplementalDiscActualsException if a m supplemental disc actuals with the primary key could not be found
	*/
	public MSupplementalDiscActuals findByPrimaryKey(
		MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
		throws NoSuchMSupplementalDiscActualsException;

	/**
	* Returns the m supplemental disc actuals with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
	* @return the m supplemental disc actuals, or <code>null</code> if a m supplemental disc actuals with the primary key could not be found
	*/
	public MSupplementalDiscActuals fetchByPrimaryKey(
		MSupplementalDiscActualsPK mSupplementalDiscActualsPK);

	@Override
	public java.util.Map<java.io.Serializable, MSupplementalDiscActuals> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the m supplemental disc actualses.
	*
	* @return the m supplemental disc actualses
	*/
	public java.util.List<MSupplementalDiscActuals> findAll();

	/**
	* Returns a range of all the m supplemental disc actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m supplemental disc actualses
	* @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
	* @return the range of m supplemental disc actualses
	*/
	public java.util.List<MSupplementalDiscActuals> findAll(int start, int end);

	/**
	* Returns an ordered range of all the m supplemental disc actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m supplemental disc actualses
	* @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of m supplemental disc actualses
	*/
	public java.util.List<MSupplementalDiscActuals> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSupplementalDiscActuals> orderByComparator);

	/**
	* Returns an ordered range of all the m supplemental disc actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m supplemental disc actualses
	* @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of m supplemental disc actualses
	*/
	public java.util.List<MSupplementalDiscActuals> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSupplementalDiscActuals> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the m supplemental disc actualses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of m supplemental disc actualses.
	*
	* @return the number of m supplemental disc actualses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}