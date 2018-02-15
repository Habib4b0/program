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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.model.HelperTable;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the helper table service. This utility wraps {@link com.stpl.app.service.persistence.impl.HelperTablePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HelperTablePersistence
 * @see com.stpl.app.service.persistence.impl.HelperTablePersistenceImpl
 * @generated
 */
@ProviderType
public class HelperTableUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(HelperTable helperTable) {
		getPersistence().clearCache(helperTable);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HelperTable> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HelperTable> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HelperTable> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HelperTable> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HelperTable update(HelperTable helperTable) {
		return getPersistence().update(helperTable);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HelperTable update(HelperTable helperTable,
		ServiceContext serviceContext) {
		return getPersistence().update(helperTable, serviceContext);
	}

	/**
	* Returns all the helper tables where listName = &#63;.
	*
	* @param listName the list name
	* @return the matching helper tables
	*/
	public static List<HelperTable> findByHelperTableDetails(
		java.lang.String listName) {
		return getPersistence().findByHelperTableDetails(listName);
	}

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
	public static List<HelperTable> findByHelperTableDetails(
		java.lang.String listName, int start, int end) {
		return getPersistence().findByHelperTableDetails(listName, start, end);
	}

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
	public static List<HelperTable> findByHelperTableDetails(
		java.lang.String listName, int start, int end,
		OrderByComparator<HelperTable> orderByComparator) {
		return getPersistence()
				   .findByHelperTableDetails(listName, start, end,
			orderByComparator);
	}

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
	public static List<HelperTable> findByHelperTableDetails(
		java.lang.String listName, int start, int end,
		OrderByComparator<HelperTable> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByHelperTableDetails(listName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first helper table in the ordered set where listName = &#63;.
	*
	* @param listName the list name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching helper table
	* @throws NoSuchHelperTableException if a matching helper table could not be found
	*/
	public static HelperTable findByHelperTableDetails_First(
		java.lang.String listName,
		OrderByComparator<HelperTable> orderByComparator)
		throws com.stpl.app.exception.NoSuchHelperTableException {
		return getPersistence()
				   .findByHelperTableDetails_First(listName, orderByComparator);
	}

	/**
	* Returns the first helper table in the ordered set where listName = &#63;.
	*
	* @param listName the list name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public static HelperTable fetchByHelperTableDetails_First(
		java.lang.String listName,
		OrderByComparator<HelperTable> orderByComparator) {
		return getPersistence()
				   .fetchByHelperTableDetails_First(listName, orderByComparator);
	}

	/**
	* Returns the last helper table in the ordered set where listName = &#63;.
	*
	* @param listName the list name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching helper table
	* @throws NoSuchHelperTableException if a matching helper table could not be found
	*/
	public static HelperTable findByHelperTableDetails_Last(
		java.lang.String listName,
		OrderByComparator<HelperTable> orderByComparator)
		throws com.stpl.app.exception.NoSuchHelperTableException {
		return getPersistence()
				   .findByHelperTableDetails_Last(listName, orderByComparator);
	}

	/**
	* Returns the last helper table in the ordered set where listName = &#63;.
	*
	* @param listName the list name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public static HelperTable fetchByHelperTableDetails_Last(
		java.lang.String listName,
		OrderByComparator<HelperTable> orderByComparator) {
		return getPersistence()
				   .fetchByHelperTableDetails_Last(listName, orderByComparator);
	}

	/**
	* Returns the helper tables before and after the current helper table in the ordered set where listName = &#63;.
	*
	* @param helperTableSid the primary key of the current helper table
	* @param listName the list name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next helper table
	* @throws NoSuchHelperTableException if a helper table with the primary key could not be found
	*/
	public static HelperTable[] findByHelperTableDetails_PrevAndNext(
		int helperTableSid, java.lang.String listName,
		OrderByComparator<HelperTable> orderByComparator)
		throws com.stpl.app.exception.NoSuchHelperTableException {
		return getPersistence()
				   .findByHelperTableDetails_PrevAndNext(helperTableSid,
			listName, orderByComparator);
	}

	/**
	* Removes all the helper tables where listName = &#63; from the database.
	*
	* @param listName the list name
	*/
	public static void removeByHelperTableDetails(java.lang.String listName) {
		getPersistence().removeByHelperTableDetails(listName);
	}

	/**
	* Returns the number of helper tables where listName = &#63;.
	*
	* @param listName the list name
	* @return the number of matching helper tables
	*/
	public static int countByHelperTableDetails(java.lang.String listName) {
		return getPersistence().countByHelperTableDetails(listName);
	}

	/**
	* Returns the helper table where helperTableSid = &#63; or throws a {@link NoSuchHelperTableException} if it could not be found.
	*
	* @param helperTableSid the helper table sid
	* @return the matching helper table
	* @throws NoSuchHelperTableException if a matching helper table could not be found
	*/
	public static HelperTable findByHelperTableDetailsByHelperTableSid(
		int helperTableSid)
		throws com.stpl.app.exception.NoSuchHelperTableException {
		return getPersistence()
				   .findByHelperTableDetailsByHelperTableSid(helperTableSid);
	}

	/**
	* Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param helperTableSid the helper table sid
	* @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public static HelperTable fetchByHelperTableDetailsByHelperTableSid(
		int helperTableSid) {
		return getPersistence()
				   .fetchByHelperTableDetailsByHelperTableSid(helperTableSid);
	}

	/**
	* Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param helperTableSid the helper table sid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public static HelperTable fetchByHelperTableDetailsByHelperTableSid(
		int helperTableSid, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByHelperTableDetailsByHelperTableSid(helperTableSid,
			retrieveFromCache);
	}

	/**
	* Removes the helper table where helperTableSid = &#63; from the database.
	*
	* @param helperTableSid the helper table sid
	* @return the helper table that was removed
	*/
	public static HelperTable removeByHelperTableDetailsByHelperTableSid(
		int helperTableSid)
		throws com.stpl.app.exception.NoSuchHelperTableException {
		return getPersistence()
				   .removeByHelperTableDetailsByHelperTableSid(helperTableSid);
	}

	/**
	* Returns the number of helper tables where helperTableSid = &#63;.
	*
	* @param helperTableSid the helper table sid
	* @return the number of matching helper tables
	*/
	public static int countByHelperTableDetailsByHelperTableSid(
		int helperTableSid) {
		return getPersistence()
				   .countByHelperTableDetailsByHelperTableSid(helperTableSid);
	}

	/**
	* Returns the helper table where description = &#63; or throws a {@link NoSuchHelperTableException} if it could not be found.
	*
	* @param description the description
	* @return the matching helper table
	* @throws NoSuchHelperTableException if a matching helper table could not be found
	*/
	public static HelperTable findByHelperTableDetailsByDesc(
		java.lang.String description)
		throws com.stpl.app.exception.NoSuchHelperTableException {
		return getPersistence().findByHelperTableDetailsByDesc(description);
	}

	/**
	* Returns the helper table where description = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param description the description
	* @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public static HelperTable fetchByHelperTableDetailsByDesc(
		java.lang.String description) {
		return getPersistence().fetchByHelperTableDetailsByDesc(description);
	}

	/**
	* Returns the helper table where description = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param description the description
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public static HelperTable fetchByHelperTableDetailsByDesc(
		java.lang.String description, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByHelperTableDetailsByDesc(description,
			retrieveFromCache);
	}

	/**
	* Removes the helper table where description = &#63; from the database.
	*
	* @param description the description
	* @return the helper table that was removed
	*/
	public static HelperTable removeByHelperTableDetailsByDesc(
		java.lang.String description)
		throws com.stpl.app.exception.NoSuchHelperTableException {
		return getPersistence().removeByHelperTableDetailsByDesc(description);
	}

	/**
	* Returns the number of helper tables where description = &#63;.
	*
	* @param description the description
	* @return the number of matching helper tables
	*/
	public static int countByHelperTableDetailsByDesc(
		java.lang.String description) {
		return getPersistence().countByHelperTableDetailsByDesc(description);
	}

	/**
	* Returns the helper table where helperTableSid = &#63; or throws a {@link NoSuchHelperTableException} if it could not be found.
	*
	* @param helperTableSid the helper table sid
	* @return the matching helper table
	* @throws NoSuchHelperTableException if a matching helper table could not be found
	*/
	public static HelperTable findByHelperTableDetailsByCode(int helperTableSid)
		throws com.stpl.app.exception.NoSuchHelperTableException {
		return getPersistence().findByHelperTableDetailsByCode(helperTableSid);
	}

	/**
	* Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param helperTableSid the helper table sid
	* @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public static HelperTable fetchByHelperTableDetailsByCode(
		int helperTableSid) {
		return getPersistence().fetchByHelperTableDetailsByCode(helperTableSid);
	}

	/**
	* Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param helperTableSid the helper table sid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	*/
	public static HelperTable fetchByHelperTableDetailsByCode(
		int helperTableSid, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByHelperTableDetailsByCode(helperTableSid,
			retrieveFromCache);
	}

	/**
	* Removes the helper table where helperTableSid = &#63; from the database.
	*
	* @param helperTableSid the helper table sid
	* @return the helper table that was removed
	*/
	public static HelperTable removeByHelperTableDetailsByCode(
		int helperTableSid)
		throws com.stpl.app.exception.NoSuchHelperTableException {
		return getPersistence().removeByHelperTableDetailsByCode(helperTableSid);
	}

	/**
	* Returns the number of helper tables where helperTableSid = &#63;.
	*
	* @param helperTableSid the helper table sid
	* @return the number of matching helper tables
	*/
	public static int countByHelperTableDetailsByCode(int helperTableSid) {
		return getPersistence().countByHelperTableDetailsByCode(helperTableSid);
	}

	/**
	* Caches the helper table in the entity cache if it is enabled.
	*
	* @param helperTable the helper table
	*/
	public static void cacheResult(HelperTable helperTable) {
		getPersistence().cacheResult(helperTable);
	}

	/**
	* Caches the helper tables in the entity cache if it is enabled.
	*
	* @param helperTables the helper tables
	*/
	public static void cacheResult(List<HelperTable> helperTables) {
		getPersistence().cacheResult(helperTables);
	}

	/**
	* Creates a new helper table with the primary key. Does not add the helper table to the database.
	*
	* @param helperTableSid the primary key for the new helper table
	* @return the new helper table
	*/
	public static HelperTable create(int helperTableSid) {
		return getPersistence().create(helperTableSid);
	}

	/**
	* Removes the helper table with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param helperTableSid the primary key of the helper table
	* @return the helper table that was removed
	* @throws NoSuchHelperTableException if a helper table with the primary key could not be found
	*/
	public static HelperTable remove(int helperTableSid)
		throws com.stpl.app.exception.NoSuchHelperTableException {
		return getPersistence().remove(helperTableSid);
	}

	public static HelperTable updateImpl(HelperTable helperTable) {
		return getPersistence().updateImpl(helperTable);
	}

	/**
	* Returns the helper table with the primary key or throws a {@link NoSuchHelperTableException} if it could not be found.
	*
	* @param helperTableSid the primary key of the helper table
	* @return the helper table
	* @throws NoSuchHelperTableException if a helper table with the primary key could not be found
	*/
	public static HelperTable findByPrimaryKey(int helperTableSid)
		throws com.stpl.app.exception.NoSuchHelperTableException {
		return getPersistence().findByPrimaryKey(helperTableSid);
	}

	/**
	* Returns the helper table with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param helperTableSid the primary key of the helper table
	* @return the helper table, or <code>null</code> if a helper table with the primary key could not be found
	*/
	public static HelperTable fetchByPrimaryKey(int helperTableSid) {
		return getPersistence().fetchByPrimaryKey(helperTableSid);
	}

	public static java.util.Map<java.io.Serializable, HelperTable> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the helper tables.
	*
	* @return the helper tables
	*/
	public static List<HelperTable> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<HelperTable> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<HelperTable> findAll(int start, int end,
		OrderByComparator<HelperTable> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<HelperTable> findAll(int start, int end,
		OrderByComparator<HelperTable> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the helper tables from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of helper tables.
	*
	* @return the number of helper tables
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HelperTablePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HelperTablePersistence, HelperTablePersistence> _serviceTracker =
		ServiceTrackerFactory.open(HelperTablePersistence.class);
}