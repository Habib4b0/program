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

import com.stpl.app.exception.NoSuchWorkflowMasterException;
import com.stpl.app.model.WorkflowMaster;

/**
 * The persistence interface for the workflow master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.WorkflowMasterPersistenceImpl
 * @see WorkflowMasterUtil
 * @generated
 */
@ProviderType
public interface WorkflowMasterPersistence extends BasePersistence<WorkflowMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WorkflowMasterUtil} to access the workflow master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the workflow master in the entity cache if it is enabled.
	*
	* @param workflowMaster the workflow master
	*/
	public void cacheResult(WorkflowMaster workflowMaster);

	/**
	* Caches the workflow masters in the entity cache if it is enabled.
	*
	* @param workflowMasters the workflow masters
	*/
	public void cacheResult(java.util.List<WorkflowMaster> workflowMasters);

	/**
	* Creates a new workflow master with the primary key. Does not add the workflow master to the database.
	*
	* @param workflowMasterSid the primary key for the new workflow master
	* @return the new workflow master
	*/
	public WorkflowMaster create(int workflowMasterSid);

	/**
	* Removes the workflow master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowMasterSid the primary key of the workflow master
	* @return the workflow master that was removed
	* @throws NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
	*/
	public WorkflowMaster remove(int workflowMasterSid)
		throws NoSuchWorkflowMasterException;

	public WorkflowMaster updateImpl(WorkflowMaster workflowMaster);

	/**
	* Returns the workflow master with the primary key or throws a {@link NoSuchWorkflowMasterException} if it could not be found.
	*
	* @param workflowMasterSid the primary key of the workflow master
	* @return the workflow master
	* @throws NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
	*/
	public WorkflowMaster findByPrimaryKey(int workflowMasterSid)
		throws NoSuchWorkflowMasterException;

	/**
	* Returns the workflow master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workflowMasterSid the primary key of the workflow master
	* @return the workflow master, or <code>null</code> if a workflow master with the primary key could not be found
	*/
	public WorkflowMaster fetchByPrimaryKey(int workflowMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, WorkflowMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the workflow masters.
	*
	* @return the workflow masters
	*/
	public java.util.List<WorkflowMaster> findAll();

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
	public java.util.List<WorkflowMaster> findAll(int start, int end);

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
	public java.util.List<WorkflowMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkflowMaster> orderByComparator);

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
	public java.util.List<WorkflowMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WorkflowMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the workflow masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of workflow masters.
	*
	* @return the number of workflow masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}