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

import com.stpl.app.exception.NoSuchHistRelationshipLevelDefnException;
import com.stpl.app.model.HistRelationshipLevelDefn;

/**
 * The persistence interface for the hist relationship level defn service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.HistRelationshipLevelDefnPersistenceImpl
 * @see HistRelationshipLevelDefnUtil
 * @generated
 */
@ProviderType
public interface HistRelationshipLevelDefnPersistence extends BasePersistence<HistRelationshipLevelDefn> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HistRelationshipLevelDefnUtil} to access the hist relationship level defn persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the hist relationship level defn in the entity cache if it is enabled.
	*
	* @param histRelationshipLevelDefn the hist relationship level defn
	*/
	public void cacheResult(HistRelationshipLevelDefn histRelationshipLevelDefn);

	/**
	* Caches the hist relationship level defns in the entity cache if it is enabled.
	*
	* @param histRelationshipLevelDefns the hist relationship level defns
	*/
	public void cacheResult(
		java.util.List<HistRelationshipLevelDefn> histRelationshipLevelDefns);

	/**
	* Creates a new hist relationship level defn with the primary key. Does not add the hist relationship level defn to the database.
	*
	* @param histRelationshipLevelDefnPK the primary key for the new hist relationship level defn
	* @return the new hist relationship level defn
	*/
	public HistRelationshipLevelDefn create(
		HistRelationshipLevelDefnPK histRelationshipLevelDefnPK);

	/**
	* Removes the hist relationship level defn with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
	* @return the hist relationship level defn that was removed
	* @throws NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
	*/
	public HistRelationshipLevelDefn remove(
		HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
		throws NoSuchHistRelationshipLevelDefnException;

	public HistRelationshipLevelDefn updateImpl(
		HistRelationshipLevelDefn histRelationshipLevelDefn);

	/**
	* Returns the hist relationship level defn with the primary key or throws a {@link NoSuchHistRelationshipLevelDefnException} if it could not be found.
	*
	* @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
	* @return the hist relationship level defn
	* @throws NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
	*/
	public HistRelationshipLevelDefn findByPrimaryKey(
		HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
		throws NoSuchHistRelationshipLevelDefnException;

	/**
	* Returns the hist relationship level defn with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
	* @return the hist relationship level defn, or <code>null</code> if a hist relationship level defn with the primary key could not be found
	*/
	public HistRelationshipLevelDefn fetchByPrimaryKey(
		HistRelationshipLevelDefnPK histRelationshipLevelDefnPK);

	@Override
	public java.util.Map<java.io.Serializable, HistRelationshipLevelDefn> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the hist relationship level defns.
	*
	* @return the hist relationship level defns
	*/
	public java.util.List<HistRelationshipLevelDefn> findAll();

	/**
	* Returns a range of all the hist relationship level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist relationship level defns
	* @param end the upper bound of the range of hist relationship level defns (not inclusive)
	* @return the range of hist relationship level defns
	*/
	public java.util.List<HistRelationshipLevelDefn> findAll(int start, int end);

	/**
	* Returns an ordered range of all the hist relationship level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist relationship level defns
	* @param end the upper bound of the range of hist relationship level defns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist relationship level defns
	*/
	public java.util.List<HistRelationshipLevelDefn> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistRelationshipLevelDefn> orderByComparator);

	/**
	* Returns an ordered range of all the hist relationship level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist relationship level defns
	* @param end the upper bound of the range of hist relationship level defns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist relationship level defns
	*/
	public java.util.List<HistRelationshipLevelDefn> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistRelationshipLevelDefn> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the hist relationship level defns from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of hist relationship level defns.
	*
	* @return the number of hist relationship level defns
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}