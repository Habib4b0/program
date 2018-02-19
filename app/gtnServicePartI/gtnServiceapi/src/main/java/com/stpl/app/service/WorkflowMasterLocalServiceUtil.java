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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for WorkflowMaster. This utility wraps
 * {@link com.stpl.app.service.impl.WorkflowMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see WorkflowMasterLocalService
 * @see com.stpl.app.service.base.WorkflowMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.WorkflowMasterLocalServiceImpl
 * @generated
 */
@ProviderType
public class WorkflowMasterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.WorkflowMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the workflow master to the database. Also notifies the appropriate model listeners.
	*
	* @param workflowMaster the workflow master
	* @return the workflow master that was added
	*/
	public static com.stpl.app.model.WorkflowMaster addWorkflowMaster(
		com.stpl.app.model.WorkflowMaster workflowMaster) {
		return getService().addWorkflowMaster(workflowMaster);
	}

	/**
	* Creates a new workflow master with the primary key. Does not add the workflow master to the database.
	*
	* @param workflowMasterSid the primary key for the new workflow master
	* @return the new workflow master
	*/
	public static com.stpl.app.model.WorkflowMaster createWorkflowMaster(
		int workflowMasterSid) {
		return getService().createWorkflowMaster(workflowMasterSid);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the workflow master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowMasterSid the primary key of the workflow master
	* @return the workflow master that was removed
	* @throws PortalException if a workflow master with the primary key could not be found
	*/
	public static com.stpl.app.model.WorkflowMaster deleteWorkflowMaster(
		int workflowMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWorkflowMaster(workflowMasterSid);
	}

	/**
	* Deletes the workflow master from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowMaster the workflow master
	* @return the workflow master that was removed
	*/
	public static com.stpl.app.model.WorkflowMaster deleteWorkflowMaster(
		com.stpl.app.model.WorkflowMaster workflowMaster) {
		return getService().deleteWorkflowMaster(workflowMaster);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.stpl.app.model.WorkflowMaster fetchWorkflowMaster(
		int workflowMasterSid) {
		return getService().fetchWorkflowMaster(workflowMasterSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the workflow master with the primary key.
	*
	* @param workflowMasterSid the primary key of the workflow master
	* @return the workflow master
	* @throws PortalException if a workflow master with the primary key could not be found
	*/
	public static com.stpl.app.model.WorkflowMaster getWorkflowMaster(
		int workflowMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWorkflowMaster(workflowMasterSid);
	}

	/**
	* Returns a range of all the workflow masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow masters
	* @param end the upper bound of the range of workflow masters (not inclusive)
	* @return the range of workflow masters
	*/
	public static java.util.List<com.stpl.app.model.WorkflowMaster> getWorkflowMasters(
		int start, int end) {
		return getService().getWorkflowMasters(start, end);
	}

	/**
	* Returns the number of workflow masters.
	*
	* @return the number of workflow masters
	*/
	public static int getWorkflowMastersCount() {
		return getService().getWorkflowMastersCount();
	}

	/**
	* Updates the workflow master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workflowMaster the workflow master
	* @return the workflow master that was updated
	*/
	public static com.stpl.app.model.WorkflowMaster updateWorkflowMaster(
		com.stpl.app.model.WorkflowMaster workflowMaster) {
		return getService().updateWorkflowMaster(workflowMaster);
	}

	public static WorkflowMasterLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WorkflowMasterLocalService, WorkflowMasterLocalService> _serviceTracker =
		ServiceTrackerFactory.open(WorkflowMasterLocalService.class);
}