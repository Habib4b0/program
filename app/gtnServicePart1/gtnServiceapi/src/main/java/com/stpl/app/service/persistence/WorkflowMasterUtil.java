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

import com.stpl.app.model.WorkflowMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the workflow master service. This utility wraps {@link com.stpl.app.service.persistence.impl.WorkflowMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowMasterPersistence
 * @see com.stpl.app.service.persistence.impl.WorkflowMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class WorkflowMasterUtil {
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
	public static void clearCache(WorkflowMaster workflowMaster) {
		getPersistence().clearCache(workflowMaster);
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
	public static List<WorkflowMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WorkflowMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WorkflowMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WorkflowMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WorkflowMaster update(WorkflowMaster workflowMaster) {
		return getPersistence().update(workflowMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WorkflowMaster update(WorkflowMaster workflowMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(workflowMaster, serviceContext);
	}

	/**
	* Caches the workflow master in the entity cache if it is enabled.
	*
	* @param workflowMaster the workflow master
	*/
	public static void cacheResult(WorkflowMaster workflowMaster) {
		getPersistence().cacheResult(workflowMaster);
	}

	/**
	* Caches the workflow masters in the entity cache if it is enabled.
	*
	* @param workflowMasters the workflow masters
	*/
	public static void cacheResult(List<WorkflowMaster> workflowMasters) {
		getPersistence().cacheResult(workflowMasters);
	}

	/**
	* Creates a new workflow master with the primary key. Does not add the workflow master to the database.
	*
	* @param workflowMasterSid the primary key for the new workflow master
	* @return the new workflow master
	*/
	public static WorkflowMaster create(int workflowMasterSid) {
		return getPersistence().create(workflowMasterSid);
	}

	/**
	* Removes the workflow master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowMasterSid the primary key of the workflow master
	* @return the workflow master that was removed
	* @throws NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
	*/
	public static WorkflowMaster remove(int workflowMasterSid)
		throws com.stpl.app.exception.NoSuchWorkflowMasterException {
		return getPersistence().remove(workflowMasterSid);
	}

	public static WorkflowMaster updateImpl(WorkflowMaster workflowMaster) {
		return getPersistence().updateImpl(workflowMaster);
	}

	/**
	* Returns the workflow master with the primary key or throws a {@link NoSuchWorkflowMasterException} if it could not be found.
	*
	* @param workflowMasterSid the primary key of the workflow master
	* @return the workflow master
	* @throws NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
	*/
	public static WorkflowMaster findByPrimaryKey(int workflowMasterSid)
		throws com.stpl.app.exception.NoSuchWorkflowMasterException {
		return getPersistence().findByPrimaryKey(workflowMasterSid);
	}

	/**
	* Returns the workflow master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workflowMasterSid the primary key of the workflow master
	* @return the workflow master, or <code>null</code> if a workflow master with the primary key could not be found
	*/
	public static WorkflowMaster fetchByPrimaryKey(int workflowMasterSid) {
		return getPersistence().fetchByPrimaryKey(workflowMasterSid);
	}

	public static java.util.Map<java.io.Serializable, WorkflowMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the workflow masters.
	*
	* @return the workflow masters
	*/
	public static List<WorkflowMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the workflow masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow masters
	* @param end the upper bound of the range of workflow masters (not inclusive)
	* @return the range of workflow masters
	*/
	public static List<WorkflowMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the workflow masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow masters
	* @param end the upper bound of the range of workflow masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of workflow masters
	*/
	public static List<WorkflowMaster> findAll(int start, int end,
		OrderByComparator<WorkflowMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the workflow masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow masters
	* @param end the upper bound of the range of workflow masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of workflow masters
	*/
	public static List<WorkflowMaster> findAll(int start, int end,
		OrderByComparator<WorkflowMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the workflow masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of workflow masters.
	*
	* @return the number of workflow masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WorkflowMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WorkflowMasterPersistence, WorkflowMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(WorkflowMasterPersistence.class);
}