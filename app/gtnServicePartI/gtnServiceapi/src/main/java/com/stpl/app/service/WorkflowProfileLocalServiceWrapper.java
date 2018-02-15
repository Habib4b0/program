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

package com.stpl.app.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WorkflowProfileLocalService}.
 *
 * @author
 * @see WorkflowProfileLocalService
 * @generated
 */
@ProviderType
public class WorkflowProfileLocalServiceWrapper
	implements WorkflowProfileLocalService,
		ServiceWrapper<WorkflowProfileLocalService> {
	public WorkflowProfileLocalServiceWrapper(
		WorkflowProfileLocalService workflowProfileLocalService) {
		_workflowProfileLocalService = workflowProfileLocalService;
	}

	/**
	* Adds the workflow profile to the database. Also notifies the appropriate model listeners.
	*
	* @param workflowProfile the workflow profile
	* @return the workflow profile that was added
	*/
	@Override
	public com.stpl.app.model.WorkflowProfile addWorkflowProfile(
		com.stpl.app.model.WorkflowProfile workflowProfile) {
		return _workflowProfileLocalService.addWorkflowProfile(workflowProfile);
	}

	/**
	* Creates a new workflow profile with the primary key. Does not add the workflow profile to the database.
	*
	* @param processSid the primary key for the new workflow profile
	* @return the new workflow profile
	*/
	@Override
	public com.stpl.app.model.WorkflowProfile createWorkflowProfile(
		int processSid) {
		return _workflowProfileLocalService.createWorkflowProfile(processSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowProfileLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the workflow profile with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processSid the primary key of the workflow profile
	* @return the workflow profile that was removed
	* @throws PortalException if a workflow profile with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.WorkflowProfile deleteWorkflowProfile(
		int processSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowProfileLocalService.deleteWorkflowProfile(processSid);
	}

	/**
	* Deletes the workflow profile from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowProfile the workflow profile
	* @return the workflow profile that was removed
	*/
	@Override
	public com.stpl.app.model.WorkflowProfile deleteWorkflowProfile(
		com.stpl.app.model.WorkflowProfile workflowProfile) {
		return _workflowProfileLocalService.deleteWorkflowProfile(workflowProfile);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _workflowProfileLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _workflowProfileLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _workflowProfileLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _workflowProfileLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _workflowProfileLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _workflowProfileLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.WorkflowProfile fetchWorkflowProfile(
		int processSid) {
		return _workflowProfileLocalService.fetchWorkflowProfile(processSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _workflowProfileLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _workflowProfileLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _workflowProfileLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowProfileLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the workflow profile with the primary key.
	*
	* @param processSid the primary key of the workflow profile
	* @return the workflow profile
	* @throws PortalException if a workflow profile with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.WorkflowProfile getWorkflowProfile(int processSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowProfileLocalService.getWorkflowProfile(processSid);
	}

	/**
	* Returns a range of all the workflow profiles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow profiles
	* @param end the upper bound of the range of workflow profiles (not inclusive)
	* @return the range of workflow profiles
	*/
	@Override
	public java.util.List<com.stpl.app.model.WorkflowProfile> getWorkflowProfiles(
		int start, int end) {
		return _workflowProfileLocalService.getWorkflowProfiles(start, end);
	}

	/**
	* Returns the number of workflow profiles.
	*
	* @return the number of workflow profiles
	*/
	@Override
	public int getWorkflowProfilesCount() {
		return _workflowProfileLocalService.getWorkflowProfilesCount();
	}

	/**
	* Updates the workflow profile in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workflowProfile the workflow profile
	* @return the workflow profile that was updated
	*/
	@Override
	public com.stpl.app.model.WorkflowProfile updateWorkflowProfile(
		com.stpl.app.model.WorkflowProfile workflowProfile) {
		return _workflowProfileLocalService.updateWorkflowProfile(workflowProfile);
	}

	@Override
	public WorkflowProfileLocalService getWrappedService() {
		return _workflowProfileLocalService;
	}

	@Override
	public void setWrappedService(
		WorkflowProfileLocalService workflowProfileLocalService) {
		_workflowProfileLocalService = workflowProfileLocalService;
	}

	private WorkflowProfileLocalService _workflowProfileLocalService;
}