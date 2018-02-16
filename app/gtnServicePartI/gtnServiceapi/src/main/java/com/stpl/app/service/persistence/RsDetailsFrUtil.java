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

import com.stpl.app.model.RsDetailsFr;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the rs details fr service. This utility wraps {@link com.stpl.app.service.persistence.impl.RsDetailsFrPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsDetailsFrPersistence
 * @see com.stpl.app.service.persistence.impl.RsDetailsFrPersistenceImpl
 * @generated
 */
@ProviderType
public class RsDetailsFrUtil {
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
	public static void clearCache(RsDetailsFr rsDetailsFr) {
		getPersistence().clearCache(rsDetailsFr);
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
	public static List<RsDetailsFr> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RsDetailsFr> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RsDetailsFr> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RsDetailsFr> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RsDetailsFr update(RsDetailsFr rsDetailsFr) {
		return getPersistence().update(rsDetailsFr);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RsDetailsFr update(RsDetailsFr rsDetailsFr,
		ServiceContext serviceContext) {
		return getPersistence().update(rsDetailsFr, serviceContext);
	}

	/**
	* Caches the rs details fr in the entity cache if it is enabled.
	*
	* @param rsDetailsFr the rs details fr
	*/
	public static void cacheResult(RsDetailsFr rsDetailsFr) {
		getPersistence().cacheResult(rsDetailsFr);
	}

	/**
	* Caches the rs details frs in the entity cache if it is enabled.
	*
	* @param rsDetailsFrs the rs details frs
	*/
	public static void cacheResult(List<RsDetailsFr> rsDetailsFrs) {
		getPersistence().cacheResult(rsDetailsFrs);
	}

	/**
	* Creates a new rs details fr with the primary key. Does not add the rs details fr to the database.
	*
	* @param rsDetailsFrSid the primary key for the new rs details fr
	* @return the new rs details fr
	*/
	public static RsDetailsFr create(int rsDetailsFrSid) {
		return getPersistence().create(rsDetailsFrSid);
	}

	/**
	* Removes the rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsDetailsFrSid the primary key of the rs details fr
	* @return the rs details fr that was removed
	* @throws NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
	*/
	public static RsDetailsFr remove(int rsDetailsFrSid)
		throws com.stpl.app.exception.NoSuchRsDetailsFrException {
		return getPersistence().remove(rsDetailsFrSid);
	}

	public static RsDetailsFr updateImpl(RsDetailsFr rsDetailsFr) {
		return getPersistence().updateImpl(rsDetailsFr);
	}

	/**
	* Returns the rs details fr with the primary key or throws a {@link NoSuchRsDetailsFrException} if it could not be found.
	*
	* @param rsDetailsFrSid the primary key of the rs details fr
	* @return the rs details fr
	* @throws NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
	*/
	public static RsDetailsFr findByPrimaryKey(int rsDetailsFrSid)
		throws com.stpl.app.exception.NoSuchRsDetailsFrException {
		return getPersistence().findByPrimaryKey(rsDetailsFrSid);
	}

	/**
	* Returns the rs details fr with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rsDetailsFrSid the primary key of the rs details fr
	* @return the rs details fr, or <code>null</code> if a rs details fr with the primary key could not be found
	*/
	public static RsDetailsFr fetchByPrimaryKey(int rsDetailsFrSid) {
		return getPersistence().fetchByPrimaryKey(rsDetailsFrSid);
	}

	public static java.util.Map<java.io.Serializable, RsDetailsFr> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the rs details frs.
	*
	* @return the rs details frs
	*/
	public static List<RsDetailsFr> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the rs details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs details frs
	* @param end the upper bound of the range of rs details frs (not inclusive)
	* @return the range of rs details frs
	*/
	public static List<RsDetailsFr> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the rs details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs details frs
	* @param end the upper bound of the range of rs details frs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rs details frs
	*/
	public static List<RsDetailsFr> findAll(int start, int end,
		OrderByComparator<RsDetailsFr> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the rs details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs details frs
	* @param end the upper bound of the range of rs details frs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rs details frs
	*/
	public static List<RsDetailsFr> findAll(int start, int end,
		OrderByComparator<RsDetailsFr> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the rs details frs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of rs details frs.
	*
	* @return the number of rs details frs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RsDetailsFrPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RsDetailsFrPersistence, RsDetailsFrPersistence> _serviceTracker =
		ServiceTrackerFactory.open(RsDetailsFrPersistence.class);
}