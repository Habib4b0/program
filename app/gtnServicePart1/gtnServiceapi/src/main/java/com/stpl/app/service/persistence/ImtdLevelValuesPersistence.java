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

import com.stpl.app.exception.NoSuchImtdLevelValuesException;
import com.stpl.app.model.ImtdLevelValues;

/**
 * The persistence interface for the imtd level values service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ImtdLevelValuesPersistenceImpl
 * @see ImtdLevelValuesUtil
 * @generated
 */
@ProviderType
public interface ImtdLevelValuesPersistence extends BasePersistence<ImtdLevelValues> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImtdLevelValuesUtil} to access the imtd level values persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the imtd level values in the entity cache if it is enabled.
	*
	* @param imtdLevelValues the imtd level values
	*/
	public void cacheResult(ImtdLevelValues imtdLevelValues);

	/**
	* Caches the imtd level valueses in the entity cache if it is enabled.
	*
	* @param imtdLevelValueses the imtd level valueses
	*/
	public void cacheResult(java.util.List<ImtdLevelValues> imtdLevelValueses);

	/**
	* Creates a new imtd level values with the primary key. Does not add the imtd level values to the database.
	*
	* @param imtdLevelValuesSid the primary key for the new imtd level values
	* @return the new imtd level values
	*/
	public ImtdLevelValues create(int imtdLevelValuesSid);

	/**
	* Removes the imtd level values with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdLevelValuesSid the primary key of the imtd level values
	* @return the imtd level values that was removed
	* @throws NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
	*/
	public ImtdLevelValues remove(int imtdLevelValuesSid)
		throws NoSuchImtdLevelValuesException;

	public ImtdLevelValues updateImpl(ImtdLevelValues imtdLevelValues);

	/**
	* Returns the imtd level values with the primary key or throws a {@link NoSuchImtdLevelValuesException} if it could not be found.
	*
	* @param imtdLevelValuesSid the primary key of the imtd level values
	* @return the imtd level values
	* @throws NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
	*/
	public ImtdLevelValues findByPrimaryKey(int imtdLevelValuesSid)
		throws NoSuchImtdLevelValuesException;

	/**
	* Returns the imtd level values with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdLevelValuesSid the primary key of the imtd level values
	* @return the imtd level values, or <code>null</code> if a imtd level values with the primary key could not be found
	*/
	public ImtdLevelValues fetchByPrimaryKey(int imtdLevelValuesSid);

	@Override
	public java.util.Map<java.io.Serializable, ImtdLevelValues> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the imtd level valueses.
	*
	* @return the imtd level valueses
	*/
	public java.util.List<ImtdLevelValues> findAll();

	/**
	* Returns a range of all the imtd level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd level valueses
	* @param end the upper bound of the range of imtd level valueses (not inclusive)
	* @return the range of imtd level valueses
	*/
	public java.util.List<ImtdLevelValues> findAll(int start, int end);

	/**
	* Returns an ordered range of all the imtd level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd level valueses
	* @param end the upper bound of the range of imtd level valueses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd level valueses
	*/
	public java.util.List<ImtdLevelValues> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdLevelValues> orderByComparator);

	/**
	* Returns an ordered range of all the imtd level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd level valueses
	* @param end the upper bound of the range of imtd level valueses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd level valueses
	*/
	public java.util.List<ImtdLevelValues> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdLevelValues> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the imtd level valueses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of imtd level valueses.
	*
	* @return the number of imtd level valueses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}