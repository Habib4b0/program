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

import com.stpl.app.exception.NoSuchHierarchyLevelValuesException;
import com.stpl.app.model.HierarchyLevelValues;

/**
 * The persistence interface for the hierarchy level values service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.HierarchyLevelValuesPersistenceImpl
 * @see HierarchyLevelValuesUtil
 * @generated
 */
@ProviderType
public interface HierarchyLevelValuesPersistence extends BasePersistence<HierarchyLevelValues> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HierarchyLevelValuesUtil} to access the hierarchy level values persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the hierarchy level values in the entity cache if it is enabled.
	*
	* @param hierarchyLevelValues the hierarchy level values
	*/
	public void cacheResult(HierarchyLevelValues hierarchyLevelValues);

	/**
	* Caches the hierarchy level valueses in the entity cache if it is enabled.
	*
	* @param hierarchyLevelValueses the hierarchy level valueses
	*/
	public void cacheResult(
		java.util.List<HierarchyLevelValues> hierarchyLevelValueses);

	/**
	* Creates a new hierarchy level values with the primary key. Does not add the hierarchy level values to the database.
	*
	* @param hierarchyLevelValuesSid the primary key for the new hierarchy level values
	* @return the new hierarchy level values
	*/
	public HierarchyLevelValues create(int hierarchyLevelValuesSid);

	/**
	* Removes the hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	* @return the hierarchy level values that was removed
	* @throws NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
	*/
	public HierarchyLevelValues remove(int hierarchyLevelValuesSid)
		throws NoSuchHierarchyLevelValuesException;

	public HierarchyLevelValues updateImpl(
		HierarchyLevelValues hierarchyLevelValues);

	/**
	* Returns the hierarchy level values with the primary key or throws a {@link NoSuchHierarchyLevelValuesException} if it could not be found.
	*
	* @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	* @return the hierarchy level values
	* @throws NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
	*/
	public HierarchyLevelValues findByPrimaryKey(int hierarchyLevelValuesSid)
		throws NoSuchHierarchyLevelValuesException;

	/**
	* Returns the hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	* @return the hierarchy level values, or <code>null</code> if a hierarchy level values with the primary key could not be found
	*/
	public HierarchyLevelValues fetchByPrimaryKey(int hierarchyLevelValuesSid);

	@Override
	public java.util.Map<java.io.Serializable, HierarchyLevelValues> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the hierarchy level valueses.
	*
	* @return the hierarchy level valueses
	*/
	public java.util.List<HierarchyLevelValues> findAll();

	/**
	* Returns a range of all the hierarchy level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level valueses
	* @param end the upper bound of the range of hierarchy level valueses (not inclusive)
	* @return the range of hierarchy level valueses
	*/
	public java.util.List<HierarchyLevelValues> findAll(int start, int end);

	/**
	* Returns an ordered range of all the hierarchy level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level valueses
	* @param end the upper bound of the range of hierarchy level valueses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hierarchy level valueses
	*/
	public java.util.List<HierarchyLevelValues> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HierarchyLevelValues> orderByComparator);

	/**
	* Returns an ordered range of all the hierarchy level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level valueses
	* @param end the upper bound of the range of hierarchy level valueses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hierarchy level valueses
	*/
	public java.util.List<HierarchyLevelValues> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HierarchyLevelValues> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the hierarchy level valueses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of hierarchy level valueses.
	*
	* @return the number of hierarchy level valueses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}