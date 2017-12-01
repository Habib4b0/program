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
 * Provides a wrapper for {@link WorkflowProcessInfoLocalService}.
 *
 * @author
 * @see WorkflowProcessInfoLocalService
 * @generated
 */
@ProviderType
public class WorkflowProcessInfoLocalServiceWrapper
	implements WorkflowProcessInfoLocalService,
		ServiceWrapper<WorkflowProcessInfoLocalService> {
	public WorkflowProcessInfoLocalServiceWrapper(
		WorkflowProcessInfoLocalService workflowProcessInfoLocalService) {
		_workflowProcessInfoLocalService = workflowProcessInfoLocalService;
	}

	/**
	* Adds the workflow process info to the database. Also notifies the appropriate model listeners.
	*
	* @param workflowProcessInfo the workflow process info
	* @return the workflow process info that was added
	*/
	@Override
	public com.stpl.app.model.WorkflowProcessInfo addWorkflowProcessInfo(
		com.stpl.app.model.WorkflowProcessInfo workflowProcessInfo) {
		return _workflowProcessInfoLocalService.addWorkflowProcessInfo(workflowProcessInfo);
	}

	/**
	* Creates a new workflow process info with the primary key. Does not add the workflow process info to the database.
	*
	* @param workflowProcessInfoSid the primary key for the new workflow process info
	* @return the new workflow process info
	*/
	@Override
	public com.stpl.app.model.WorkflowProcessInfo createWorkflowProcessInfo(
		int workflowProcessInfoSid) {
		return _workflowProcessInfoLocalService.createWorkflowProcessInfo(workflowProcessInfoSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowProcessInfoLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the workflow process info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowProcessInfoSid the primary key of the workflow process info
	* @return the workflow process info that was removed
	* @throws PortalException if a workflow process info with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.WorkflowProcessInfo deleteWorkflowProcessInfo(
		int workflowProcessInfoSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowProcessInfoLocalService.deleteWorkflowProcessInfo(workflowProcessInfoSid);
	}

	/**
	* Deletes the workflow process info from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowProcessInfo the workflow process info
	* @return the workflow process info that was removed
	*/
	@Override
	public com.stpl.app.model.WorkflowProcessInfo deleteWorkflowProcessInfo(
		com.stpl.app.model.WorkflowProcessInfo workflowProcessInfo) {
		return _workflowProcessInfoLocalService.deleteWorkflowProcessInfo(workflowProcessInfo);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _workflowProcessInfoLocalService.dynamicQuery();
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
		return _workflowProcessInfoLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _workflowProcessInfoLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _workflowProcessInfoLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _workflowProcessInfoLocalService.dynamicQueryCount(dynamicQuery);
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
		return _workflowProcessInfoLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.WorkflowProcessInfo fetchWorkflowProcessInfo(
		int workflowProcessInfoSid) {
		return _workflowProcessInfoLocalService.fetchWorkflowProcessInfo(workflowProcessInfoSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _workflowProcessInfoLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _workflowProcessInfoLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _workflowProcessInfoLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowProcessInfoLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the workflow process info with the primary key.
	*
	* @param workflowProcessInfoSid the primary key of the workflow process info
	* @return the workflow process info
	* @throws PortalException if a workflow process info with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.WorkflowProcessInfo getWorkflowProcessInfo(
		int workflowProcessInfoSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowProcessInfoLocalService.getWorkflowProcessInfo(workflowProcessInfoSid);
	}

	/**
	* Returns a range of all the workflow process infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow process infos
	* @param end the upper bound of the range of workflow process infos (not inclusive)
	* @return the range of workflow process infos
	*/
	@Override
	public java.util.List<com.stpl.app.model.WorkflowProcessInfo> getWorkflowProcessInfos(
		int start, int end) {
		return _workflowProcessInfoLocalService.getWorkflowProcessInfos(start,
			end);
	}

	/**
	* Returns the number of workflow process infos.
	*
	* @return the number of workflow process infos
	*/
	@Override
	public int getWorkflowProcessInfosCount() {
		return _workflowProcessInfoLocalService.getWorkflowProcessInfosCount();
	}

	/**
	* Updates the workflow process info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workflowProcessInfo the workflow process info
	* @return the workflow process info that was updated
	*/
	@Override
	public com.stpl.app.model.WorkflowProcessInfo updateWorkflowProcessInfo(
		com.stpl.app.model.WorkflowProcessInfo workflowProcessInfo) {
		return _workflowProcessInfoLocalService.updateWorkflowProcessInfo(workflowProcessInfo);
	}

	@Override
	public WorkflowProcessInfoLocalService getWrappedService() {
		return _workflowProcessInfoLocalService;
	}

	@Override
	public void setWrappedService(
		WorkflowProcessInfoLocalService workflowProcessInfoLocalService) {
		_workflowProcessInfoLocalService = workflowProcessInfoLocalService;
	}

	private WorkflowProcessInfoLocalService _workflowProcessInfoLocalService;
}