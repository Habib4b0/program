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

import com.stpl.app.exception.NoSuchHelperTableException;
import com.stpl.app.model.HelperTable;

/**
 * The persistence interface for the helper table service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.HelperTablePersistenceImpl
 * @see HelperTableUtil
 * @generated
 */
@ProviderType
public interface HelperTablePersistence extends BasePersistence<HelperTable> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HelperTableUtil} to access the helper table persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the helper tables where listName = &#63;.
	*
	* @param listName the list name
	* @return the matching helper tables
	*/
	public java.util.List<HelperTable> findByHelperTableDetails(
		java.lang.String listName);

	/**
	* Returns a range of all the helper tables where listName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param listName the list name
	* @param start the lower bound of the range of helper tables
	* @param end the upper bound of the range of helper tables (not inclusive)
	* @return the range of matching helper tables
	*/
	public java.util.List<HelperTable> findByHelperTableDetails(
		java.lang.String listName, int start, int end);

	/**
	* Returns an ordered range of all the helper tables where listName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param listName the list name
	* @param start the lower bound of the range of helper tables
	* @param end the upper bound of the range of helper tables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching helper tables
	*/
	public java.util.List<HelperTable> findByHelperTableDetails(
		java.lang.String listName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelperTable> orderByComparator);

	/**
	* Returns an ordered range of all the helper tables where listName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param listName the list name
	* @param start the lower bound of the range of helper tables
	* @param end the upper bound of the range of helper tables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching helper tables
	*/
	public java.util.List<HelperTable> findByHelperTableDetails(
		java.lang.String listName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelperTable> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first helper table in the ordered set where listName = &#63;.
	*
	* @param listName the list name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching helper table
	* @throws NoSuchHelperTableException if a matching helper table could not be found
	*/
	public HelperTable findByHelperTableDetails_First(
		java.lang.String listName,
		com.liferay.portal.kernel.util.OrderByComparator<HelperTable> orderByComparator)
		throws NoSuchHelperTableException;

	/**
	* Returns the first helper table in the ordered set where listName = &#63;.
	*
	* @param listName the list name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public HelperTable fetchByHelperTableDetails_First(
		java.lang.String listName,
		com.liferay.portal.kernel.util.OrderByComparator<HelperTable> orderByComparator);

	/**
	* Returns the last helper table in the ordered set where listName = &#63;.
	*
	* @param listName the list name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching helper table
	* @throws NoSuchHelperTableException if a matching helper table could not be found
	*/
	public HelperTable findByHelperTableDetails_Last(
		java.lang.String listName,
		com.liferay.portal.kernel.util.OrderByComparator<HelperTable> orderByComparator)
		throws NoSuchHelperTableException;

	/**
	* Returns the last helper table in the ordered set where listName = &#63;.
	*
	* @param listName the list name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public HelperTable fetchByHelperTableDetails_Last(
		java.lang.String listName,
		com.liferay.portal.kernel.util.OrderByComparator<HelperTable> orderByComparator);

	/**
	* Returns the helper tables before and after the current helper table in the ordered set where listName = &#63;.
	*
	* @param helperTableSid the primary key of the current helper table
	* @param listName the list name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next helper table
	* @throws NoSuchHelperTableException if a helper table with the primary key could not be found
	*/
	public HelperTable[] findByHelperTableDetails_PrevAndNext(
		int helperTableSid, java.lang.String listName,
		com.liferay.portal.kernel.util.OrderByComparator<HelperTable> orderByComparator)
		throws NoSuchHelperTableException;

	/**
	* Removes all the helper tables where listName = &#63; from the database.
	*
	* @param listName the list name
	*/
	public void removeByHelperTableDetails(java.lang.String listName);

	/**
	* Returns the number of helper tables where listName = &#63;.
	*
	* @param listName the list name
	* @return the number of matching helper tables
	*/
	public int countByHelperTableDetails(java.lang.String listName);

	/**
	* Returns the helper table where helperTableSid = &#63; or throws a {@link NoSuchHelperTableException} if it could not be found.
	*
	* @param helperTableSid the helper table sid
	* @return the matching helper table
	* @throws NoSuchHelperTableException if a matching helper table could not be found
	*/
	public HelperTable findByHelperTableDetailsByHelperTableSid(
		int helperTableSid) throws NoSuchHelperTableException;

	/**
	* Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param helperTableSid the helper table sid
	* @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public HelperTable fetchByHelperTableDetailsByHelperTableSid(
		int helperTableSid);

	/**
	* Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param helperTableSid the helper table sid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public HelperTable fetchByHelperTableDetailsByHelperTableSid(
		int helperTableSid, boolean retrieveFromCache);

	/**
	* Removes the helper table where helperTableSid = &#63; from the database.
	*
	* @param helperTableSid the helper table sid
	* @return the helper table that was removed
	*/
	public HelperTable removeByHelperTableDetailsByHelperTableSid(
		int helperTableSid) throws NoSuchHelperTableException;

	/**
	* Returns the number of helper tables where helperTableSid = &#63;.
	*
	* @param helperTableSid the helper table sid
	* @return the number of matching helper tables
	*/
	public int countByHelperTableDetailsByHelperTableSid(int helperTableSid);

	/**
	* Returns the helper table where description = &#63; or throws a {@link NoSuchHelperTableException} if it could not be found.
	*
	* @param description the description
	* @return the matching helper table
	* @throws NoSuchHelperTableException if a matching helper table could not be found
	*/
	public HelperTable findByHelperTableDetailsByDesc(
		java.lang.String description) throws NoSuchHelperTableException;

	/**
	* Returns the helper table where description = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param description the description
	* @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public HelperTable fetchByHelperTableDetailsByDesc(
		java.lang.String description);

	/**
	* Returns the helper table where description = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param description the description
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public HelperTable fetchByHelperTableDetailsByDesc(
		java.lang.String description, boolean retrieveFromCache);

	/**
	* Removes the helper table where description = &#63; from the database.
	*
	* @param description the description
	* @return the helper table that was removed
	*/
	public HelperTable removeByHelperTableDetailsByDesc(
		java.lang.String description) throws NoSuchHelperTableException;

	/**
	* Returns the number of helper tables where description = &#63;.
	*
	* @param description the description
	* @return the number of matching helper tables
	*/
	public int countByHelperTableDetailsByDesc(java.lang.String description);

	/**
	* Returns the helper table where helperTableSid = &#63; or throws a {@link NoSuchHelperTableException} if it could not be found.
	*
	* @param helperTableSid the helper table sid
	* @return the matching helper table
	* @throws NoSuchHelperTableException if a matching helper table could not be found
	*/
	public HelperTable findByHelperTableDetailsByCode(int helperTableSid)
		throws NoSuchHelperTableException;

	/**
	* Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param helperTableSid the helper table sid
	* @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public HelperTable fetchByHelperTableDetailsByCode(int helperTableSid);

	/**
	* Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param helperTableSid the helper table sid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public HelperTable fetchByHelperTableDetailsByCode(int helperTableSid,
		boolean retrieveFromCache);

	/**
	* Removes the helper table where helperTableSid = &#63; from the database.
	*
	* @param helperTableSid the helper table sid
	* @return the helper table that was removed
	*/
	public HelperTable removeByHelperTableDetailsByCode(int helperTableSid)
		throws NoSuchHelperTableException;

	/**
	* Returns the number of helper tables where helperTableSid = &#63;.
	*
	* @param helperTableSid the helper table sid
	* @return the number of matching helper tables
	*/
	public int countByHelperTableDetailsByCode(int helperTableSid);

	/**
	* Caches the helper table in the entity cache if it is enabled.
	*
	* @param helperTable the helper table
	*/
	public void cacheResult(HelperTable helperTable);

	/**
	* Caches the helper tables in the entity cache if it is enabled.
	*
	* @param helperTables the helper tables
	*/
	public void cacheResult(java.util.List<HelperTable> helperTables);

	/**
	* Creates a new helper table with the primary key. Does not add the helper table to the database.
	*
	* @param helperTableSid the primary key for the new helper table
	* @return the new helper table
	*/
	public HelperTable create(int helperTableSid);

	/**
	* Removes the helper table with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param helperTableSid the primary key of the helper table
	* @return the helper table that was removed
	* @throws NoSuchHelperTableException if a helper table with the primary key could not be found
	*/
	public HelperTable remove(int helperTableSid)
		throws NoSuchHelperTableException;

	public HelperTable updateImpl(HelperTable helperTable);

	/**
	* Returns the helper table with the primary key or throws a {@link NoSuchHelperTableException} if it could not be found.
	*
	* @param helperTableSid the primary key of the helper table
	* @return the helper table
	* @throws NoSuchHelperTableException if a helper table with the primary key could not be found
	*/
	public HelperTable findByPrimaryKey(int helperTableSid)
		throws NoSuchHelperTableException;

	/**
	* Returns the helper table with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param helperTableSid the primary key of the helper table
	* @return the helper table, or <code>null</code> if a helper table with the primary key could not be found
	*/
	public HelperTable fetchByPrimaryKey(int helperTableSid);

	@Override
	public java.util.Map<java.io.Serializable, HelperTable> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the helper tables.
	*
	* @return the helper tables
	*/
	public java.util.List<HelperTable> findAll();

	/**
	* Returns a range of all the helper tables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of helper tables
	* @param end the upper bound of the range of helper tables (not inclusive)
	* @return the range of helper tables
	*/
	public java.util.List<HelperTable> findAll(int start, int end);

	/**
	* Returns an ordered range of all the helper tables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of helper tables
	* @param end the upper bound of the range of helper tables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of helper tables
	*/
	public java.util.List<HelperTable> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelperTable> orderByComparator);

	/**
	* Returns an ordered range of all the helper tables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of helper tables
	* @param end the upper bound of the range of helper tables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of helper tables
	*/
	public java.util.List<HelperTable> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelperTable> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the helper tables from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of helper tables.
	*
	* @return the number of helper tables
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}