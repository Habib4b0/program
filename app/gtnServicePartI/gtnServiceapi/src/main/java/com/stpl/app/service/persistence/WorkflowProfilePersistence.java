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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.exception.NoSuchWorkflowProfileException;
import com.stpl.app.model.WorkflowProfile;

/**
 * The persistence interface for the workflow profile service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.WorkflowProfilePersistenceImpl
 * @see WorkflowProfileUtil
 * @generated
 */
@ProviderType
public interface WorkflowProfilePersistence extends BasePersistence<WorkflowProfile> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WorkflowProfileUtil} to access the workflow profile persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the workflow profile in the entity cache if it is enabled.
	*
	* @param workflowProfile the workflow profile
	*/
	public void cacheResult(WorkflowProfile workflowProfile);

	/**
	* Caches the workflow profiles in the entity cache if it is enabled.
	*
	* @param workflowProfiles the workflow profiles
	*/
	public void cacheResult(java.util.List<WorkflowProfile> workflowProfiles);

	/**
	* Creates a new workflow profile with the primary key. Does not add the workflow profile to the database.
	*
	* @param processSid the primary key for the new workflow profile
	* @return the new workflow profile
	*/
	public WorkflowProfile create(int processSid);

	/**
	* Removes the workflow profile with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processSid the primary key of the workflow profile
	* @return the workflow profile that was removed
	* @throws NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
	*/
	public WorkflowProfile remove(int processSid)
		throws NoSuchWorkflowProfileException;

	public WorkflowProfile updateImpl(WorkflowProfile workflowProfile);

	/**
	* Returns the workflow profile with the primary key or throws a {@link NoSuchWorkflowProfileException} if it could not be found.
	*
	* @param processSid the primary key of the workflow profile
	* @return the workflow profile
	* @throws NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
	*/
	public WorkflowProfile findByPrimaryKey(int processSid)
		throws NoSuchWorkflowProfileException;

	/**
	* Returns the workflow profile with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processSid the primary key of the workflow profile
	* @return the workflow profile, or <code>null</code> if a workflow profile with the primary key could not be found
	*/
	public WorkflowProfile fetchByPrimaryKey(int processSid);

	@Override
	public java.util.Map<java.io.Serializable, WorkflowProfile> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the workflow profiles.
	*
	* @return the workflow profiles
	*/
	public java.util.List<WorkflowProfile> findAll();

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
	public java.util.List<WorkflowProfile> findAll(int start, int end);

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
	public java.util.List<WorkflowProfile> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkflowProfile> orderByComparator);

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
	public java.util.List<WorkflowProfile> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkflowProfile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the workflow profiles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of workflow profiles.
	*
	* @return the number of workflow profiles
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}