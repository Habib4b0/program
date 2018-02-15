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

import com.stpl.app.model.CcpMap;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ccp map service. This utility wraps {@link com.stpl.app.service.persistence.impl.CcpMapPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CcpMapPersistence
 * @see com.stpl.app.service.persistence.impl.CcpMapPersistenceImpl
 * @generated
 */
@ProviderType
public class CcpMapUtil {
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
	public static void clearCache(CcpMap ccpMap) {
		getPersistence().clearCache(ccpMap);
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
	public static List<CcpMap> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CcpMap> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CcpMap> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<CcpMap> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CcpMap update(CcpMap ccpMap) {
		return getPersistence().update(ccpMap);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CcpMap update(CcpMap ccpMap, ServiceContext serviceContext) {
		return getPersistence().update(ccpMap, serviceContext);
	}

	/**
	* Caches the ccp map in the entity cache if it is enabled.
	*
	* @param ccpMap the ccp map
	*/
	public static void cacheResult(CcpMap ccpMap) {
		getPersistence().cacheResult(ccpMap);
	}

	/**
	* Caches the ccp maps in the entity cache if it is enabled.
	*
	* @param ccpMaps the ccp maps
	*/
	public static void cacheResult(List<CcpMap> ccpMaps) {
		getPersistence().cacheResult(ccpMaps);
	}

	/**
	* Creates a new ccp map with the primary key. Does not add the ccp map to the database.
	*
	* @param ccpMapSid the primary key for the new ccp map
	* @return the new ccp map
	*/
	public static CcpMap create(int ccpMapSid) {
		return getPersistence().create(ccpMapSid);
	}

	/**
	* Removes the ccp map with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ccpMapSid the primary key of the ccp map
	* @return the ccp map that was removed
	* @throws NoSuchCcpMapException if a ccp map with the primary key could not be found
	*/
	public static CcpMap remove(int ccpMapSid)
		throws com.stpl.app.exception.NoSuchCcpMapException {
		return getPersistence().remove(ccpMapSid);
	}

	public static CcpMap updateImpl(CcpMap ccpMap) {
		return getPersistence().updateImpl(ccpMap);
	}

	/**
	* Returns the ccp map with the primary key or throws a {@link NoSuchCcpMapException} if it could not be found.
	*
	* @param ccpMapSid the primary key of the ccp map
	* @return the ccp map
	* @throws NoSuchCcpMapException if a ccp map with the primary key could not be found
	*/
	public static CcpMap findByPrimaryKey(int ccpMapSid)
		throws com.stpl.app.exception.NoSuchCcpMapException {
		return getPersistence().findByPrimaryKey(ccpMapSid);
	}

	/**
	* Returns the ccp map with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ccpMapSid the primary key of the ccp map
	* @return the ccp map, or <code>null</code> if a ccp map with the primary key could not be found
	*/
	public static CcpMap fetchByPrimaryKey(int ccpMapSid) {
		return getPersistence().fetchByPrimaryKey(ccpMapSid);
	}

	public static java.util.Map<java.io.Serializable, CcpMap> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ccp maps.
	*
	* @return the ccp maps
	*/
	public static List<CcpMap> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ccp maps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ccp maps
	* @param end the upper bound of the range of ccp maps (not inclusive)
	* @return the range of ccp maps
	*/
	public static List<CcpMap> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ccp maps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ccp maps
	* @param end the upper bound of the range of ccp maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ccp maps
	*/
	public static List<CcpMap> findAll(int start, int end,
		OrderByComparator<CcpMap> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ccp maps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ccp maps
	* @param end the upper bound of the range of ccp maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ccp maps
	*/
	public static List<CcpMap> findAll(int start, int end,
		OrderByComparator<CcpMap> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ccp maps from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ccp maps.
	*
	* @return the number of ccp maps
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CcpMapPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CcpMapPersistence, CcpMapPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CcpMapPersistence.class);
}