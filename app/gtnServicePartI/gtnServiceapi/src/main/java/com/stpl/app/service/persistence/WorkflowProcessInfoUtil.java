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

import com.stpl.app.model.WorkflowProcessInfo;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the workflow process info service. This utility wraps {@link com.stpl.app.service.persistence.impl.WorkflowProcessInfoPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowProcessInfoPersistence
 * @see com.stpl.app.service.persistence.impl.WorkflowProcessInfoPersistenceImpl
 * @generated
 */
@ProviderType
public class WorkflowProcessInfoUtil {
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
	public static void clearCache(WorkflowProcessInfo workflowProcessInfo) {
		getPersistence().clearCache(workflowProcessInfo);
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
	public static List<WorkflowProcessInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WorkflowProcessInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WorkflowProcessInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WorkflowProcessInfo> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WorkflowProcessInfo update(
		WorkflowProcessInfo workflowProcessInfo) {
		return getPersistence().update(workflowProcessInfo);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WorkflowProcessInfo update(
		WorkflowProcessInfo workflowProcessInfo, ServiceContext serviceContext) {
		return getPersistence().update(workflowProcessInfo, serviceContext);
	}

	/**
	* Caches the workflow process info in the entity cache if it is enabled.
	*
	* @param workflowProcessInfo the workflow process info
	*/
	public static void cacheResult(WorkflowProcessInfo workflowProcessInfo) {
		getPersistence().cacheResult(workflowProcessInfo);
	}

	/**
	* Caches the workflow process infos in the entity cache if it is enabled.
	*
	* @param workflowProcessInfos the workflow process infos
	*/
	public static void cacheResult(
		List<WorkflowProcessInfo> workflowProcessInfos) {
		getPersistence().cacheResult(workflowProcessInfos);
	}

	/**
	* Creates a new workflow process info with the primary key. Does not add the workflow process info to the database.
	*
	* @param workflowProcessInfoSid the primary key for the new workflow process info
	* @return the new workflow process info
	*/
	public static WorkflowProcessInfo create(int workflowProcessInfoSid) {
		return getPersistence().create(workflowProcessInfoSid);
	}

	/**
	* Removes the workflow process info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowProcessInfoSid the primary key of the workflow process info
	* @return the workflow process info that was removed
	* @throws NoSuchWorkflowProcessInfoException if a workflow process info with the primary key could not be found
	*/
	public static WorkflowProcessInfo remove(int workflowProcessInfoSid)
		throws com.stpl.app.exception.NoSuchWorkflowProcessInfoException {
		return getPersistence().remove(workflowProcessInfoSid);
	}

	public static WorkflowProcessInfo updateImpl(
		WorkflowProcessInfo workflowProcessInfo) {
		return getPersistence().updateImpl(workflowProcessInfo);
	}

	/**
	* Returns the workflow process info with the primary key or throws a {@link NoSuchWorkflowProcessInfoException} if it could not be found.
	*
	* @param workflowProcessInfoSid the primary key of the workflow process info
	* @return the workflow process info
	* @throws NoSuchWorkflowProcessInfoException if a workflow process info with the primary key could not be found
	*/
	public static WorkflowProcessInfo findByPrimaryKey(
		int workflowProcessInfoSid)
		throws com.stpl.app.exception.NoSuchWorkflowProcessInfoException {
		return getPersistence().findByPrimaryKey(workflowProcessInfoSid);
	}

	/**
	* Returns the workflow process info with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workflowProcessInfoSid the primary key of the workflow process info
	* @return the workflow process info, or <code>null</code> if a workflow process info with the primary key could not be found
	*/
	public static WorkflowProcessInfo fetchByPrimaryKey(
		int workflowProcessInfoSid) {
		return getPersistence().fetchByPrimaryKey(workflowProcessInfoSid);
	}

	public static java.util.Map<java.io.Serializable, WorkflowProcessInfo> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the workflow process infos.
	*
	* @return the workflow process infos
	*/
	public static List<WorkflowProcessInfo> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the workflow process infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow process infos
	* @param end the upper bound of the range of workflow process infos (not inclusive)
	* @return the range of workflow process infos
	*/
	public static List<WorkflowProcessInfo> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the workflow process infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow process infos
	* @param end the upper bound of the range of workflow process infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of workflow process infos
	*/
	public static List<WorkflowProcessInfo> findAll(int start, int end,
		OrderByComparator<WorkflowProcessInfo> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the workflow process infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow process infos
	* @param end the upper bound of the range of workflow process infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of workflow process infos
	*/
	public static List<WorkflowProcessInfo> findAll(int start, int end,
		OrderByComparator<WorkflowProcessInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the workflow process infos from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of workflow process infos.
	*
	* @return the number of workflow process infos
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WorkflowProcessInfoPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WorkflowProcessInfoPersistence, WorkflowProcessInfoPersistence> _serviceTracker =
		ServiceTrackerFactory.open(WorkflowProcessInfoPersistence.class);
}