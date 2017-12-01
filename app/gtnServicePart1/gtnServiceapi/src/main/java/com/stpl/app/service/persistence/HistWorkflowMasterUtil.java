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

import com.stpl.app.model.HistWorkflowMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the hist workflow master service. This utility wraps {@link com.stpl.app.service.persistence.impl.HistWorkflowMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistWorkflowMasterPersistence
 * @see com.stpl.app.service.persistence.impl.HistWorkflowMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class HistWorkflowMasterUtil {
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
	public static void clearCache(HistWorkflowMaster histWorkflowMaster) {
		getPersistence().clearCache(histWorkflowMaster);
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
	public static List<HistWorkflowMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HistWorkflowMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HistWorkflowMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HistWorkflowMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HistWorkflowMaster update(
		HistWorkflowMaster histWorkflowMaster) {
		return getPersistence().update(histWorkflowMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HistWorkflowMaster update(
		HistWorkflowMaster histWorkflowMaster, ServiceContext serviceContext) {
		return getPersistence().update(histWorkflowMaster, serviceContext);
	}

	/**
	* Caches the hist workflow master in the entity cache if it is enabled.
	*
	* @param histWorkflowMaster the hist workflow master
	*/
	public static void cacheResult(HistWorkflowMaster histWorkflowMaster) {
		getPersistence().cacheResult(histWorkflowMaster);
	}

	/**
	* Caches the hist workflow masters in the entity cache if it is enabled.
	*
	* @param histWorkflowMasters the hist workflow masters
	*/
	public static void cacheResult(List<HistWorkflowMaster> histWorkflowMasters) {
		getPersistence().cacheResult(histWorkflowMasters);
	}

	/**
	* Creates a new hist workflow master with the primary key. Does not add the hist workflow master to the database.
	*
	* @param workflowMasterSid the primary key for the new hist workflow master
	* @return the new hist workflow master
	*/
	public static HistWorkflowMaster create(int workflowMasterSid) {
		return getPersistence().create(workflowMasterSid);
	}

	/**
	* Removes the hist workflow master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowMasterSid the primary key of the hist workflow master
	* @return the hist workflow master that was removed
	* @throws NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
	*/
	public static HistWorkflowMaster remove(int workflowMasterSid)
		throws com.stpl.app.exception.NoSuchHistWorkflowMasterException {
		return getPersistence().remove(workflowMasterSid);
	}

	public static HistWorkflowMaster updateImpl(
		HistWorkflowMaster histWorkflowMaster) {
		return getPersistence().updateImpl(histWorkflowMaster);
	}

	/**
	* Returns the hist workflow master with the primary key or throws a {@link NoSuchHistWorkflowMasterException} if it could not be found.
	*
	* @param workflowMasterSid the primary key of the hist workflow master
	* @return the hist workflow master
	* @throws NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
	*/
	public static HistWorkflowMaster findByPrimaryKey(int workflowMasterSid)
		throws com.stpl.app.exception.NoSuchHistWorkflowMasterException {
		return getPersistence().findByPrimaryKey(workflowMasterSid);
	}

	/**
	* Returns the hist workflow master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workflowMasterSid the primary key of the hist workflow master
	* @return the hist workflow master, or <code>null</code> if a hist workflow master with the primary key could not be found
	*/
	public static HistWorkflowMaster fetchByPrimaryKey(int workflowMasterSid) {
		return getPersistence().fetchByPrimaryKey(workflowMasterSid);
	}

	public static java.util.Map<java.io.Serializable, HistWorkflowMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the hist workflow masters.
	*
	* @return the hist workflow masters
	*/
	public static List<HistWorkflowMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the hist workflow masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist workflow masters
	* @param end the upper bound of the range of hist workflow masters (not inclusive)
	* @return the range of hist workflow masters
	*/
	public static List<HistWorkflowMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the hist workflow masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist workflow masters
	* @param end the upper bound of the range of hist workflow masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist workflow masters
	*/
	public static List<HistWorkflowMaster> findAll(int start, int end,
		OrderByComparator<HistWorkflowMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the hist workflow masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist workflow masters
	* @param end the upper bound of the range of hist workflow masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist workflow masters
	*/
	public static List<HistWorkflowMaster> findAll(int start, int end,
		OrderByComparator<HistWorkflowMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the hist workflow masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of hist workflow masters.
	*
	* @return the number of hist workflow masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HistWorkflowMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistWorkflowMasterPersistence, HistWorkflowMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(HistWorkflowMasterPersistence.class);
}