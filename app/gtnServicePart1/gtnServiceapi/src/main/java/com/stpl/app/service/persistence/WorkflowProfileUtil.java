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

import com.stpl.app.model.WorkflowProfile;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the workflow profile service. This utility wraps {@link com.stpl.app.service.persistence.impl.WorkflowProfilePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowProfilePersistence
 * @see com.stpl.app.service.persistence.impl.WorkflowProfilePersistenceImpl
 * @generated
 */
@ProviderType
public class WorkflowProfileUtil {
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
	public static void clearCache(WorkflowProfile workflowProfile) {
		getPersistence().clearCache(workflowProfile);
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
	public static List<WorkflowProfile> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WorkflowProfile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WorkflowProfile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WorkflowProfile> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WorkflowProfile update(WorkflowProfile workflowProfile) {
		return getPersistence().update(workflowProfile);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WorkflowProfile update(WorkflowProfile workflowProfile,
		ServiceContext serviceContext) {
		return getPersistence().update(workflowProfile, serviceContext);
	}

	/**
	* Caches the workflow profile in the entity cache if it is enabled.
	*
	* @param workflowProfile the workflow profile
	*/
	public static void cacheResult(WorkflowProfile workflowProfile) {
		getPersistence().cacheResult(workflowProfile);
	}

	/**
	* Caches the workflow profiles in the entity cache if it is enabled.
	*
	* @param workflowProfiles the workflow profiles
	*/
	public static void cacheResult(List<WorkflowProfile> workflowProfiles) {
		getPersistence().cacheResult(workflowProfiles);
	}

	/**
	* Creates a new workflow profile with the primary key. Does not add the workflow profile to the database.
	*
	* @param processSid the primary key for the new workflow profile
	* @return the new workflow profile
	*/
	public static WorkflowProfile create(int processSid) {
		return getPersistence().create(processSid);
	}

	/**
	* Removes the workflow profile with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processSid the primary key of the workflow profile
	* @return the workflow profile that was removed
	* @throws NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
	*/
	public static WorkflowProfile remove(int processSid)
		throws com.stpl.app.exception.NoSuchWorkflowProfileException {
		return getPersistence().remove(processSid);
	}

	public static WorkflowProfile updateImpl(WorkflowProfile workflowProfile) {
		return getPersistence().updateImpl(workflowProfile);
	}

	/**
	* Returns the workflow profile with the primary key or throws a {@link NoSuchWorkflowProfileException} if it could not be found.
	*
	* @param processSid the primary key of the workflow profile
	* @return the workflow profile
	* @throws NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
	*/
	public static WorkflowProfile findByPrimaryKey(int processSid)
		throws com.stpl.app.exception.NoSuchWorkflowProfileException {
		return getPersistence().findByPrimaryKey(processSid);
	}

	/**
	* Returns the workflow profile with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processSid the primary key of the workflow profile
	* @return the workflow profile, or <code>null</code> if a workflow profile with the primary key could not be found
	*/
	public static WorkflowProfile fetchByPrimaryKey(int processSid) {
		return getPersistence().fetchByPrimaryKey(processSid);
	}

	public static java.util.Map<java.io.Serializable, WorkflowProfile> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the workflow profiles.
	*
	* @return the workflow profiles
	*/
	public static List<WorkflowProfile> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the workflow profiles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow profiles
	* @param end the upper bound of the range of workflow profiles (not inclusive)
	* @return the range of workflow profiles
	*/
	public static List<WorkflowProfile> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the workflow profiles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow profiles
	* @param end the upper bound of the range of workflow profiles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of workflow profiles
	*/
	public static List<WorkflowProfile> findAll(int start, int end,
		OrderByComparator<WorkflowProfile> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the workflow profiles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow profiles
	* @param end the upper bound of the range of workflow profiles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of workflow profiles
	*/
	public static List<WorkflowProfile> findAll(int start, int end,
		OrderByComparator<WorkflowProfile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the workflow profiles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of workflow profiles.
	*
	* @return the number of workflow profiles
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WorkflowProfilePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WorkflowProfilePersistence, WorkflowProfilePersistence> _serviceTracker =
		ServiceTrackerFactory.open(WorkflowProfilePersistence.class);
}