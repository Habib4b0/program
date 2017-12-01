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

import com.stpl.app.exception.NoSuchHistHierarchyLevelDefnException;
import com.stpl.app.model.HistHierarchyLevelDefn;

/**
 * The persistence interface for the hist hierarchy level defn service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.HistHierarchyLevelDefnPersistenceImpl
 * @see HistHierarchyLevelDefnUtil
 * @generated
 */
@ProviderType
public interface HistHierarchyLevelDefnPersistence extends BasePersistence<HistHierarchyLevelDefn> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HistHierarchyLevelDefnUtil} to access the hist hierarchy level defn persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the hist hierarchy level defn in the entity cache if it is enabled.
	*
	* @param histHierarchyLevelDefn the hist hierarchy level defn
	*/
	public void cacheResult(HistHierarchyLevelDefn histHierarchyLevelDefn);

	/**
	* Caches the hist hierarchy level defns in the entity cache if it is enabled.
	*
	* @param histHierarchyLevelDefns the hist hierarchy level defns
	*/
	public void cacheResult(
		java.util.List<HistHierarchyLevelDefn> histHierarchyLevelDefns);

	/**
	* Creates a new hist hierarchy level defn with the primary key. Does not add the hist hierarchy level defn to the database.
	*
	* @param histHierarchyLevelDefnPK the primary key for the new hist hierarchy level defn
	* @return the new hist hierarchy level defn
	*/
	public HistHierarchyLevelDefn create(
		HistHierarchyLevelDefnPK histHierarchyLevelDefnPK);

	/**
	* Removes the hist hierarchy level defn with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
	* @return the hist hierarchy level defn that was removed
	* @throws NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
	*/
	public HistHierarchyLevelDefn remove(
		HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
		throws NoSuchHistHierarchyLevelDefnException;

	public HistHierarchyLevelDefn updateImpl(
		HistHierarchyLevelDefn histHierarchyLevelDefn);

	/**
	* Returns the hist hierarchy level defn with the primary key or throws a {@link NoSuchHistHierarchyLevelDefnException} if it could not be found.
	*
	* @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
	* @return the hist hierarchy level defn
	* @throws NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
	*/
	public HistHierarchyLevelDefn findByPrimaryKey(
		HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
		throws NoSuchHistHierarchyLevelDefnException;

	/**
	* Returns the hist hierarchy level defn with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
	* @return the hist hierarchy level defn, or <code>null</code> if a hist hierarchy level defn with the primary key could not be found
	*/
	public HistHierarchyLevelDefn fetchByPrimaryKey(
		HistHierarchyLevelDefnPK histHierarchyLevelDefnPK);

	@Override
	public java.util.Map<java.io.Serializable, HistHierarchyLevelDefn> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the hist hierarchy level defns.
	*
	* @return the hist hierarchy level defns
	*/
	public java.util.List<HistHierarchyLevelDefn> findAll();

	/**
	* Returns a range of all the hist hierarchy level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy level defns
	* @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
	* @return the range of hist hierarchy level defns
	*/
	public java.util.List<HistHierarchyLevelDefn> findAll(int start, int end);

	/**
	* Returns an ordered range of all the hist hierarchy level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy level defns
	* @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist hierarchy level defns
	*/
	public java.util.List<HistHierarchyLevelDefn> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistHierarchyLevelDefn> orderByComparator);

	/**
	* Returns an ordered range of all the hist hierarchy level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy level defns
	* @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist hierarchy level defns
	*/
	public java.util.List<HistHierarchyLevelDefn> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistHierarchyLevelDefn> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the hist hierarchy level defns from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of hist hierarchy level defns.
	*
	* @return the number of hist hierarchy level defns
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}