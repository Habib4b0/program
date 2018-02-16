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

import com.stpl.app.exception.NoSuchHistHierarchyLevelValuesException;
import com.stpl.app.model.HistHierarchyLevelValues;

/**
 * The persistence interface for the hist hierarchy level values service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.HistHierarchyLevelValuesPersistenceImpl
 * @see HistHierarchyLevelValuesUtil
 * @generated
 */
@ProviderType
public interface HistHierarchyLevelValuesPersistence extends BasePersistence<HistHierarchyLevelValues> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HistHierarchyLevelValuesUtil} to access the hist hierarchy level values persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the hist hierarchy level values in the entity cache if it is enabled.
	*
	* @param histHierarchyLevelValues the hist hierarchy level values
	*/
	public void cacheResult(HistHierarchyLevelValues histHierarchyLevelValues);

	/**
	* Caches the hist hierarchy level valueses in the entity cache if it is enabled.
	*
	* @param histHierarchyLevelValueses the hist hierarchy level valueses
	*/
	public void cacheResult(
		java.util.List<HistHierarchyLevelValues> histHierarchyLevelValueses);

	/**
	* Creates a new hist hierarchy level values with the primary key. Does not add the hist hierarchy level values to the database.
	*
	* @param histHierarchyLevelValuesPK the primary key for the new hist hierarchy level values
	* @return the new hist hierarchy level values
	*/
	public HistHierarchyLevelValues create(
		HistHierarchyLevelValuesPK histHierarchyLevelValuesPK);

	/**
	* Removes the hist hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyLevelValuesPK the primary key of the hist hierarchy level values
	* @return the hist hierarchy level values that was removed
	* @throws NoSuchHistHierarchyLevelValuesException if a hist hierarchy level values with the primary key could not be found
	*/
	public HistHierarchyLevelValues remove(
		HistHierarchyLevelValuesPK histHierarchyLevelValuesPK)
		throws NoSuchHistHierarchyLevelValuesException;

	public HistHierarchyLevelValues updateImpl(
		HistHierarchyLevelValues histHierarchyLevelValues);

	/**
	* Returns the hist hierarchy level values with the primary key or throws a {@link NoSuchHistHierarchyLevelValuesException} if it could not be found.
	*
	* @param histHierarchyLevelValuesPK the primary key of the hist hierarchy level values
	* @return the hist hierarchy level values
	* @throws NoSuchHistHierarchyLevelValuesException if a hist hierarchy level values with the primary key could not be found
	*/
	public HistHierarchyLevelValues findByPrimaryKey(
		HistHierarchyLevelValuesPK histHierarchyLevelValuesPK)
		throws NoSuchHistHierarchyLevelValuesException;

	/**
	* Returns the hist hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param histHierarchyLevelValuesPK the primary key of the hist hierarchy level values
	* @return the hist hierarchy level values, or <code>null</code> if a hist hierarchy level values with the primary key could not be found
	*/
	public HistHierarchyLevelValues fetchByPrimaryKey(
		HistHierarchyLevelValuesPK histHierarchyLevelValuesPK);

	@Override
	public java.util.Map<java.io.Serializable, HistHierarchyLevelValues> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the hist hierarchy level valueses.
	*
	* @return the hist hierarchy level valueses
	*/
	public java.util.List<HistHierarchyLevelValues> findAll();

	/**
	* Returns a range of all the hist hierarchy level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy level valueses
	* @param end the upper bound of the range of hist hierarchy level valueses (not inclusive)
	* @return the range of hist hierarchy level valueses
	*/
	public java.util.List<HistHierarchyLevelValues> findAll(int start, int end);

	/**
	* Returns an ordered range of all the hist hierarchy level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy level valueses
	* @param end the upper bound of the range of hist hierarchy level valueses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist hierarchy level valueses
	*/
	public java.util.List<HistHierarchyLevelValues> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistHierarchyLevelValues> orderByComparator);

	/**
	* Returns an ordered range of all the hist hierarchy level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy level valueses
	* @param end the upper bound of the range of hist hierarchy level valueses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist hierarchy level valueses
	*/
	public java.util.List<HistHierarchyLevelValues> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistHierarchyLevelValues> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the hist hierarchy level valueses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of hist hierarchy level valueses.
	*
	* @return the number of hist hierarchy level valueses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}