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

import com.stpl.app.parttwo.model.SlaCalendarMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the sla calendar master service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.SlaCalendarMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SlaCalendarMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.SlaCalendarMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class SlaCalendarMasterUtil {
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
	public static void clearCache(SlaCalendarMaster slaCalendarMaster) {
		getPersistence().clearCache(slaCalendarMaster);
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
	public static List<SlaCalendarMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SlaCalendarMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SlaCalendarMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SlaCalendarMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SlaCalendarMaster update(SlaCalendarMaster slaCalendarMaster) {
		return getPersistence().update(slaCalendarMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SlaCalendarMaster update(
		SlaCalendarMaster slaCalendarMaster, ServiceContext serviceContext) {
		return getPersistence().update(slaCalendarMaster, serviceContext);
	}

	/**
	* Caches the sla calendar master in the entity cache if it is enabled.
	*
	* @param slaCalendarMaster the sla calendar master
	*/
	public static void cacheResult(SlaCalendarMaster slaCalendarMaster) {
		getPersistence().cacheResult(slaCalendarMaster);
	}

	/**
	* Caches the sla calendar masters in the entity cache if it is enabled.
	*
	* @param slaCalendarMasters the sla calendar masters
	*/
	public static void cacheResult(List<SlaCalendarMaster> slaCalendarMasters) {
		getPersistence().cacheResult(slaCalendarMasters);
	}

	/**
	* Creates a new sla calendar master with the primary key. Does not add the sla calendar master to the database.
	*
	* @param slaCalendarMasterSid the primary key for the new sla calendar master
	* @return the new sla calendar master
	*/
	public static SlaCalendarMaster create(int slaCalendarMasterSid) {
		return getPersistence().create(slaCalendarMasterSid);
	}

	/**
	* Removes the sla calendar master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param slaCalendarMasterSid the primary key of the sla calendar master
	* @return the sla calendar master that was removed
	* @throws NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
	*/
	public static SlaCalendarMaster remove(int slaCalendarMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchSlaCalendarMasterException {
		return getPersistence().remove(slaCalendarMasterSid);
	}

	public static SlaCalendarMaster updateImpl(
		SlaCalendarMaster slaCalendarMaster) {
		return getPersistence().updateImpl(slaCalendarMaster);
	}

	/**
	* Returns the sla calendar master with the primary key or throws a {@link NoSuchSlaCalendarMasterException} if it could not be found.
	*
	* @param slaCalendarMasterSid the primary key of the sla calendar master
	* @return the sla calendar master
	* @throws NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
	*/
	public static SlaCalendarMaster findByPrimaryKey(int slaCalendarMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchSlaCalendarMasterException {
		return getPersistence().findByPrimaryKey(slaCalendarMasterSid);
	}

	/**
	* Returns the sla calendar master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param slaCalendarMasterSid the primary key of the sla calendar master
	* @return the sla calendar master, or <code>null</code> if a sla calendar master with the primary key could not be found
	*/
	public static SlaCalendarMaster fetchByPrimaryKey(int slaCalendarMasterSid) {
		return getPersistence().fetchByPrimaryKey(slaCalendarMasterSid);
	}

	public static java.util.Map<java.io.Serializable, SlaCalendarMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sla calendar masters.
	*
	* @return the sla calendar masters
	*/
	public static List<SlaCalendarMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the sla calendar masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar masters
	* @param end the upper bound of the range of sla calendar masters (not inclusive)
	* @return the range of sla calendar masters
	*/
	public static List<SlaCalendarMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the sla calendar masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar masters
	* @param end the upper bound of the range of sla calendar masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sla calendar masters
	*/
	public static List<SlaCalendarMaster> findAll(int start, int end,
		OrderByComparator<SlaCalendarMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sla calendar masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar masters
	* @param end the upper bound of the range of sla calendar masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sla calendar masters
	*/
	public static List<SlaCalendarMaster> findAll(int start, int end,
		OrderByComparator<SlaCalendarMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sla calendar masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sla calendar masters.
	*
	* @return the number of sla calendar masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SlaCalendarMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SlaCalendarMasterPersistence, SlaCalendarMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(SlaCalendarMasterPersistence.class);
}