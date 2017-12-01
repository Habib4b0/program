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

import com.stpl.app.model.MParityLookup;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the m parity lookup service. This utility wraps {@link com.stpl.app.service.persistence.impl.MParityLookupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MParityLookupPersistence
 * @see com.stpl.app.service.persistence.impl.MParityLookupPersistenceImpl
 * @generated
 */
@ProviderType
public class MParityLookupUtil {
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
	public static void clearCache(MParityLookup mParityLookup) {
		getPersistence().clearCache(mParityLookup);
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
	public static List<MParityLookup> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MParityLookup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MParityLookup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MParityLookup> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MParityLookup update(MParityLookup mParityLookup) {
		return getPersistence().update(mParityLookup);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MParityLookup update(MParityLookup mParityLookup,
		ServiceContext serviceContext) {
		return getPersistence().update(mParityLookup, serviceContext);
	}

	/**
	* Caches the m parity lookup in the entity cache if it is enabled.
	*
	* @param mParityLookup the m parity lookup
	*/
	public static void cacheResult(MParityLookup mParityLookup) {
		getPersistence().cacheResult(mParityLookup);
	}

	/**
	* Caches the m parity lookups in the entity cache if it is enabled.
	*
	* @param mParityLookups the m parity lookups
	*/
	public static void cacheResult(List<MParityLookup> mParityLookups) {
		getPersistence().cacheResult(mParityLookups);
	}

	/**
	* Creates a new m parity lookup with the primary key. Does not add the m parity lookup to the database.
	*
	* @param mParityLookupSid the primary key for the new m parity lookup
	* @return the new m parity lookup
	*/
	public static MParityLookup create(int mParityLookupSid) {
		return getPersistence().create(mParityLookupSid);
	}

	/**
	* Removes the m parity lookup with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mParityLookupSid the primary key of the m parity lookup
	* @return the m parity lookup that was removed
	* @throws NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
	*/
	public static MParityLookup remove(int mParityLookupSid)
		throws com.stpl.app.exception.NoSuchMParityLookupException {
		return getPersistence().remove(mParityLookupSid);
	}

	public static MParityLookup updateImpl(MParityLookup mParityLookup) {
		return getPersistence().updateImpl(mParityLookup);
	}

	/**
	* Returns the m parity lookup with the primary key or throws a {@link NoSuchMParityLookupException} if it could not be found.
	*
	* @param mParityLookupSid the primary key of the m parity lookup
	* @return the m parity lookup
	* @throws NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
	*/
	public static MParityLookup findByPrimaryKey(int mParityLookupSid)
		throws com.stpl.app.exception.NoSuchMParityLookupException {
		return getPersistence().findByPrimaryKey(mParityLookupSid);
	}

	/**
	* Returns the m parity lookup with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mParityLookupSid the primary key of the m parity lookup
	* @return the m parity lookup, or <code>null</code> if a m parity lookup with the primary key could not be found
	*/
	public static MParityLookup fetchByPrimaryKey(int mParityLookupSid) {
		return getPersistence().fetchByPrimaryKey(mParityLookupSid);
	}

	public static java.util.Map<java.io.Serializable, MParityLookup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the m parity lookups.
	*
	* @return the m parity lookups
	*/
	public static List<MParityLookup> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the m parity lookups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m parity lookups
	* @param end the upper bound of the range of m parity lookups (not inclusive)
	* @return the range of m parity lookups
	*/
	public static List<MParityLookup> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the m parity lookups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m parity lookups
	* @param end the upper bound of the range of m parity lookups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of m parity lookups
	*/
	public static List<MParityLookup> findAll(int start, int end,
		OrderByComparator<MParityLookup> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the m parity lookups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m parity lookups
	* @param end the upper bound of the range of m parity lookups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of m parity lookups
	*/
	public static List<MParityLookup> findAll(int start, int end,
		OrderByComparator<MParityLookup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the m parity lookups from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of m parity lookups.
	*
	* @return the number of m parity lookups
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MParityLookupPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MParityLookupPersistence, MParityLookupPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MParityLookupPersistence.class);
}