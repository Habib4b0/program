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

import com.stpl.app.exception.NoSuchMAssumptionsException;
import com.stpl.app.model.MAssumptions;

/**
 * The persistence interface for the m assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.MAssumptionsPersistenceImpl
 * @see MAssumptionsUtil
 * @generated
 */
@ProviderType
public interface MAssumptionsPersistence extends BasePersistence<MAssumptions> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MAssumptionsUtil} to access the m assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the m assumptions in the entity cache if it is enabled.
	*
	* @param mAssumptions the m assumptions
	*/
	public void cacheResult(MAssumptions mAssumptions);

	/**
	* Caches the m assumptionses in the entity cache if it is enabled.
	*
	* @param mAssumptionses the m assumptionses
	*/
	public void cacheResult(java.util.List<MAssumptions> mAssumptionses);

	/**
	* Creates a new m assumptions with the primary key. Does not add the m assumptions to the database.
	*
	* @param mAssumptionsSid the primary key for the new m assumptions
	* @return the new m assumptions
	*/
	public MAssumptions create(int mAssumptionsSid);

	/**
	* Removes the m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mAssumptionsSid the primary key of the m assumptions
	* @return the m assumptions that was removed
	* @throws NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
	*/
	public MAssumptions remove(int mAssumptionsSid)
		throws NoSuchMAssumptionsException;

	public MAssumptions updateImpl(MAssumptions mAssumptions);

	/**
	* Returns the m assumptions with the primary key or throws a {@link NoSuchMAssumptionsException} if it could not be found.
	*
	* @param mAssumptionsSid the primary key of the m assumptions
	* @return the m assumptions
	* @throws NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
	*/
	public MAssumptions findByPrimaryKey(int mAssumptionsSid)
		throws NoSuchMAssumptionsException;

	/**
	* Returns the m assumptions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mAssumptionsSid the primary key of the m assumptions
	* @return the m assumptions, or <code>null</code> if a m assumptions with the primary key could not be found
	*/
	public MAssumptions fetchByPrimaryKey(int mAssumptionsSid);

	@Override
	public java.util.Map<java.io.Serializable, MAssumptions> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the m assumptionses.
	*
	* @return the m assumptionses
	*/
	public java.util.List<MAssumptions> findAll();

	/**
	* Returns a range of all the m assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m assumptionses
	* @param end the upper bound of the range of m assumptionses (not inclusive)
	* @return the range of m assumptionses
	*/
	public java.util.List<MAssumptions> findAll(int start, int end);

	/**
	* Returns an ordered range of all the m assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m assumptionses
	* @param end the upper bound of the range of m assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of m assumptionses
	*/
	public java.util.List<MAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MAssumptions> orderByComparator);

	/**
	* Returns an ordered range of all the m assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m assumptionses
	* @param end the upper bound of the range of m assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of m assumptionses
	*/
	public java.util.List<MAssumptions> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MAssumptions> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the m assumptionses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of m assumptionses.
	*
	* @return the number of m assumptionses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}