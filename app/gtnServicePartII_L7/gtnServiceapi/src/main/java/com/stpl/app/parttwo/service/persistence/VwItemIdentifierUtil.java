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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.parttwo.model.VwItemIdentifier;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw item identifier service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.VwItemIdentifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemIdentifierPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.VwItemIdentifierPersistenceImpl
 * @generated
 */
@ProviderType
public class VwItemIdentifierUtil {
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
	public static void clearCache(VwItemIdentifier vwItemIdentifier) {
		getPersistence().clearCache(vwItemIdentifier);
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
	public static List<VwItemIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwItemIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwItemIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwItemIdentifier> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwItemIdentifier update(VwItemIdentifier vwItemIdentifier) {
		return getPersistence().update(vwItemIdentifier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwItemIdentifier update(VwItemIdentifier vwItemIdentifier,
		ServiceContext serviceContext) {
		return getPersistence().update(vwItemIdentifier, serviceContext);
	}

	/**
	* Caches the vw item identifier in the entity cache if it is enabled.
	*
	* @param vwItemIdentifier the vw item identifier
	*/
	public static void cacheResult(VwItemIdentifier vwItemIdentifier) {
		getPersistence().cacheResult(vwItemIdentifier);
	}

	/**
	* Caches the vw item identifiers in the entity cache if it is enabled.
	*
	* @param vwItemIdentifiers the vw item identifiers
	*/
	public static void cacheResult(List<VwItemIdentifier> vwItemIdentifiers) {
		getPersistence().cacheResult(vwItemIdentifiers);
	}

	/**
	* Creates a new vw item identifier with the primary key. Does not add the vw item identifier to the database.
	*
	* @param itemIdentifierSid the primary key for the new vw item identifier
	* @return the new vw item identifier
	*/
	public static VwItemIdentifier create(int itemIdentifierSid) {
		return getPersistence().create(itemIdentifierSid);
	}

	/**
	* Removes the vw item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemIdentifierSid the primary key of the vw item identifier
	* @return the vw item identifier that was removed
	* @throws NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
	*/
	public static VwItemIdentifier remove(int itemIdentifierSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwItemIdentifierException {
		return getPersistence().remove(itemIdentifierSid);
	}

	public static VwItemIdentifier updateImpl(VwItemIdentifier vwItemIdentifier) {
		return getPersistence().updateImpl(vwItemIdentifier);
	}

	/**
	* Returns the vw item identifier with the primary key or throws a {@link NoSuchVwItemIdentifierException} if it could not be found.
	*
	* @param itemIdentifierSid the primary key of the vw item identifier
	* @return the vw item identifier
	* @throws NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
	*/
	public static VwItemIdentifier findByPrimaryKey(int itemIdentifierSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwItemIdentifierException {
		return getPersistence().findByPrimaryKey(itemIdentifierSid);
	}

	/**
	* Returns the vw item identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemIdentifierSid the primary key of the vw item identifier
	* @return the vw item identifier, or <code>null</code> if a vw item identifier with the primary key could not be found
	*/
	public static VwItemIdentifier fetchByPrimaryKey(int itemIdentifierSid) {
		return getPersistence().fetchByPrimaryKey(itemIdentifierSid);
	}

	public static java.util.Map<java.io.Serializable, VwItemIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw item identifiers.
	*
	* @return the vw item identifiers
	*/
	public static List<VwItemIdentifier> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item identifiers
	* @param end the upper bound of the range of vw item identifiers (not inclusive)
	* @return the range of vw item identifiers
	*/
	public static List<VwItemIdentifier> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item identifiers
	* @param end the upper bound of the range of vw item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw item identifiers
	*/
	public static List<VwItemIdentifier> findAll(int start, int end,
		OrderByComparator<VwItemIdentifier> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item identifiers
	* @param end the upper bound of the range of vw item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw item identifiers
	*/
	public static List<VwItemIdentifier> findAll(int start, int end,
		OrderByComparator<VwItemIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw item identifiers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw item identifiers.
	*
	* @return the number of vw item identifiers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwItemIdentifierPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwItemIdentifierPersistence, VwItemIdentifierPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwItemIdentifierPersistence.class);
}