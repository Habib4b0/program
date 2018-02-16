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

import com.stpl.app.parttwo.model.AccClosureMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the acc closure master service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.AccClosureMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccClosureMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.AccClosureMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class AccClosureMasterUtil {
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
	public static void clearCache(AccClosureMaster accClosureMaster) {
		getPersistence().clearCache(accClosureMaster);
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
	public static List<AccClosureMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccClosureMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccClosureMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccClosureMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccClosureMaster update(AccClosureMaster accClosureMaster) {
		return getPersistence().update(accClosureMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccClosureMaster update(AccClosureMaster accClosureMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(accClosureMaster, serviceContext);
	}

	/**
	* Caches the acc closure master in the entity cache if it is enabled.
	*
	* @param accClosureMaster the acc closure master
	*/
	public static void cacheResult(AccClosureMaster accClosureMaster) {
		getPersistence().cacheResult(accClosureMaster);
	}

	/**
	* Caches the acc closure masters in the entity cache if it is enabled.
	*
	* @param accClosureMasters the acc closure masters
	*/
	public static void cacheResult(List<AccClosureMaster> accClosureMasters) {
		getPersistence().cacheResult(accClosureMasters);
	}

	/**
	* Creates a new acc closure master with the primary key. Does not add the acc closure master to the database.
	*
	* @param accClosureMasterSid the primary key for the new acc closure master
	* @return the new acc closure master
	*/
	public static AccClosureMaster create(int accClosureMasterSid) {
		return getPersistence().create(accClosureMasterSid);
	}

	/**
	* Removes the acc closure master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureMasterSid the primary key of the acc closure master
	* @return the acc closure master that was removed
	* @throws NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
	*/
	public static AccClosureMaster remove(int accClosureMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchAccClosureMasterException {
		return getPersistence().remove(accClosureMasterSid);
	}

	public static AccClosureMaster updateImpl(AccClosureMaster accClosureMaster) {
		return getPersistence().updateImpl(accClosureMaster);
	}

	/**
	* Returns the acc closure master with the primary key or throws a {@link NoSuchAccClosureMasterException} if it could not be found.
	*
	* @param accClosureMasterSid the primary key of the acc closure master
	* @return the acc closure master
	* @throws NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
	*/
	public static AccClosureMaster findByPrimaryKey(int accClosureMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchAccClosureMasterException {
		return getPersistence().findByPrimaryKey(accClosureMasterSid);
	}

	/**
	* Returns the acc closure master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accClosureMasterSid the primary key of the acc closure master
	* @return the acc closure master, or <code>null</code> if a acc closure master with the primary key could not be found
	*/
	public static AccClosureMaster fetchByPrimaryKey(int accClosureMasterSid) {
		return getPersistence().fetchByPrimaryKey(accClosureMasterSid);
	}

	public static java.util.Map<java.io.Serializable, AccClosureMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the acc closure masters.
	*
	* @return the acc closure masters
	*/
	public static List<AccClosureMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the acc closure masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure masters
	* @param end the upper bound of the range of acc closure masters (not inclusive)
	* @return the range of acc closure masters
	*/
	public static List<AccClosureMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the acc closure masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure masters
	* @param end the upper bound of the range of acc closure masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of acc closure masters
	*/
	public static List<AccClosureMaster> findAll(int start, int end,
		OrderByComparator<AccClosureMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the acc closure masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure masters
	* @param end the upper bound of the range of acc closure masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of acc closure masters
	*/
	public static List<AccClosureMaster> findAll(int start, int end,
		OrderByComparator<AccClosureMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the acc closure masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of acc closure masters.
	*
	* @return the number of acc closure masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AccClosureMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AccClosureMasterPersistence, AccClosureMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AccClosureMasterPersistence.class);
}