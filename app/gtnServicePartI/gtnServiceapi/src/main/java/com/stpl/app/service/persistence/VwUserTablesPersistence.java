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

import com.stpl.app.exception.NoSuchVwUserTablesException;
import com.stpl.app.model.VwUserTables;

/**
 * The persistence interface for the vw user tables service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.VwUserTablesPersistenceImpl
 * @see VwUserTablesUtil
 * @generated
 */
@ProviderType
public interface VwUserTablesPersistence extends BasePersistence<VwUserTables> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwUserTablesUtil} to access the vw user tables persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw user tables in the entity cache if it is enabled.
	*
	* @param vwUserTables the vw user tables
	*/
	public void cacheResult(VwUserTables vwUserTables);

	/**
	* Caches the vw user tableses in the entity cache if it is enabled.
	*
	* @param vwUserTableses the vw user tableses
	*/
	public void cacheResult(java.util.List<VwUserTables> vwUserTableses);

	/**
	* Creates a new vw user tables with the primary key. Does not add the vw user tables to the database.
	*
	* @param uniqueId the primary key for the new vw user tables
	* @return the new vw user tables
	*/
	public VwUserTables create(int uniqueId);

	/**
	* Removes the vw user tables with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param uniqueId the primary key of the vw user tables
	* @return the vw user tables that was removed
	* @throws NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
	*/
	public VwUserTables remove(int uniqueId) throws NoSuchVwUserTablesException;

	public VwUserTables updateImpl(VwUserTables vwUserTables);

	/**
	* Returns the vw user tables with the primary key or throws a {@link NoSuchVwUserTablesException} if it could not be found.
	*
	* @param uniqueId the primary key of the vw user tables
	* @return the vw user tables
	* @throws NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
	*/
	public VwUserTables findByPrimaryKey(int uniqueId)
		throws NoSuchVwUserTablesException;

	/**
	* Returns the vw user tables with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param uniqueId the primary key of the vw user tables
	* @return the vw user tables, or <code>null</code> if a vw user tables with the primary key could not be found
	*/
	public VwUserTables fetchByPrimaryKey(int uniqueId);

	@Override
	public java.util.Map<java.io.Serializable, VwUserTables> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw user tableses.
	*
	* @return the vw user tableses
	*/
	public java.util.List<VwUserTables> findAll();

	/**
	* Returns a range of all the vw user tableses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw user tableses
	* @param end the upper bound of the range of vw user tableses (not inclusive)
	* @return the range of vw user tableses
	*/
	public java.util.List<VwUserTables> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vw user tableses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw user tableses
	* @param end the upper bound of the range of vw user tableses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw user tableses
	*/
	public java.util.List<VwUserTables> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwUserTables> orderByComparator);

	/**
	* Returns an ordered range of all the vw user tableses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw user tableses
	* @param end the upper bound of the range of vw user tableses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw user tableses
	*/
	public java.util.List<VwUserTables> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwUserTables> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw user tableses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw user tableses.
	*
	* @return the number of vw user tableses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}