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
 * Provides the local service utility for ProjectionCustHierarchy. This utility wraps
 * {@link com.stpl.app.service.impl.ProjectionCustHierarchyLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ProjectionCustHierarchyLocalService
 * @see com.stpl.app.service.base.ProjectionCustHierarchyLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ProjectionCustHierarchyLocalServiceImpl
 * @generated
 */
@ProviderType
public class ProjectionCustHierarchyLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ProjectionCustHierarchyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the projection cust hierarchy to the database. Also notifies the appropriate model listeners.
	*
	* @param projectionCustHierarchy the projection cust hierarchy
	* @return the projection cust hierarchy that was added
	*/
	public static com.stpl.app.model.ProjectionCustHierarchy addProjectionCustHierarchy(
		com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy) {
		return getService().addProjectionCustHierarchy(projectionCustHierarchy);
	}

	/**
	* Creates a new projection cust hierarchy with the primary key. Does not add the projection cust hierarchy to the database.
	*
	* @param projectionCustHierarchySid the primary key for the new projection cust hierarchy
	* @return the new projection cust hierarchy
	*/
	public static com.stpl.app.model.ProjectionCustHierarchy createProjectionCustHierarchy(
		int projectionCustHierarchySid) {
		return getService()
				   .createProjectionCustHierarchy(projectionCustHierarchySid);
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
	* Deletes the projection cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionCustHierarchySid the primary key of the projection cust hierarchy
	* @return the projection cust hierarchy that was removed
	* @throws PortalException if a projection cust hierarchy with the primary key could not be found
	*/
	public static com.stpl.app.model.ProjectionCustHierarchy deleteProjectionCustHierarchy(
		int projectionCustHierarchySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteProjectionCustHierarchy(projectionCustHierarchySid);
	}

	/**
	* Deletes the projection cust hierarchy from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionCustHierarchy the projection cust hierarchy
	* @return the projection cust hierarchy that was removed
	*/
	public static com.stpl.app.model.ProjectionCustHierarchy deleteProjectionCustHierarchy(
		com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy) {
		return getService()
				   .deleteProjectionCustHierarchy(projectionCustHierarchy);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.ProjectionCustHierarchy fetchProjectionCustHierarchy(
		int projectionCustHierarchySid) {
		return getService()
				   .fetchProjectionCustHierarchy(projectionCustHierarchySid);
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
	* Returns a range of all the projection cust hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection cust hierarchies
	* @param end the upper bound of the range of projection cust hierarchies (not inclusive)
	* @return the range of projection cust hierarchies
	*/
	public static java.util.List<com.stpl.app.model.ProjectionCustHierarchy> getProjectionCustHierarchies(
		int start, int end) {
		return getService().getProjectionCustHierarchies(start, end);
	}

	/**
	* Returns the number of projection cust hierarchies.
	*
	* @return the number of projection cust hierarchies
	*/
	public static int getProjectionCustHierarchiesCount() {
		return getService().getProjectionCustHierarchiesCount();
	}

	/**
	* Returns the projection cust hierarchy with the primary key.
	*
	* @param projectionCustHierarchySid the primary key of the projection cust hierarchy
	* @return the projection cust hierarchy
	* @throws PortalException if a projection cust hierarchy with the primary key could not be found
	*/
	public static com.stpl.app.model.ProjectionCustHierarchy getProjectionCustHierarchy(
		int projectionCustHierarchySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getProjectionCustHierarchy(projectionCustHierarchySid);
	}

	/**
	* Updates the projection cust hierarchy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectionCustHierarchy the projection cust hierarchy
	* @return the projection cust hierarchy that was updated
	*/
	public static com.stpl.app.model.ProjectionCustHierarchy updateProjectionCustHierarchy(
		com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy) {
		return getService()
				   .updateProjectionCustHierarchy(projectionCustHierarchy);
	}

	public static ProjectionCustHierarchyLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProjectionCustHierarchyLocalService, ProjectionCustHierarchyLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ProjectionCustHierarchyLocalService.class);
}