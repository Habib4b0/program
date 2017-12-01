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
 * Provides a wrapper for {@link ProjectionCustHierarchyLocalService}.
 *
 * @author
 * @see ProjectionCustHierarchyLocalService
 * @generated
 */
@ProviderType
public class ProjectionCustHierarchyLocalServiceWrapper
	implements ProjectionCustHierarchyLocalService,
		ServiceWrapper<ProjectionCustHierarchyLocalService> {
	public ProjectionCustHierarchyLocalServiceWrapper(
		ProjectionCustHierarchyLocalService projectionCustHierarchyLocalService) {
		_projectionCustHierarchyLocalService = projectionCustHierarchyLocalService;
	}

	/**
	* Adds the projection cust hierarchy to the database. Also notifies the appropriate model listeners.
	*
	* @param projectionCustHierarchy the projection cust hierarchy
	* @return the projection cust hierarchy that was added
	*/
	@Override
	public com.stpl.app.model.ProjectionCustHierarchy addProjectionCustHierarchy(
		com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy) {
		return _projectionCustHierarchyLocalService.addProjectionCustHierarchy(projectionCustHierarchy);
	}

	/**
	* Creates a new projection cust hierarchy with the primary key. Does not add the projection cust hierarchy to the database.
	*
	* @param projectionCustHierarchySid the primary key for the new projection cust hierarchy
	* @return the new projection cust hierarchy
	*/
	@Override
	public com.stpl.app.model.ProjectionCustHierarchy createProjectionCustHierarchy(
		int projectionCustHierarchySid) {
		return _projectionCustHierarchyLocalService.createProjectionCustHierarchy(projectionCustHierarchySid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionCustHierarchyLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the projection cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionCustHierarchySid the primary key of the projection cust hierarchy
	* @return the projection cust hierarchy that was removed
	* @throws PortalException if a projection cust hierarchy with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ProjectionCustHierarchy deleteProjectionCustHierarchy(
		int projectionCustHierarchySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionCustHierarchyLocalService.deleteProjectionCustHierarchy(projectionCustHierarchySid);
	}

	/**
	* Deletes the projection cust hierarchy from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionCustHierarchy the projection cust hierarchy
	* @return the projection cust hierarchy that was removed
	*/
	@Override
	public com.stpl.app.model.ProjectionCustHierarchy deleteProjectionCustHierarchy(
		com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy) {
		return _projectionCustHierarchyLocalService.deleteProjectionCustHierarchy(projectionCustHierarchy);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _projectionCustHierarchyLocalService.dynamicQuery();
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
		return _projectionCustHierarchyLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _projectionCustHierarchyLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _projectionCustHierarchyLocalService.dynamicQuery(dynamicQuery,
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
		return _projectionCustHierarchyLocalService.dynamicQueryCount(dynamicQuery);
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
		return _projectionCustHierarchyLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ProjectionCustHierarchy fetchProjectionCustHierarchy(
		int projectionCustHierarchySid) {
		return _projectionCustHierarchyLocalService.fetchProjectionCustHierarchy(projectionCustHierarchySid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _projectionCustHierarchyLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _projectionCustHierarchyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _projectionCustHierarchyLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionCustHierarchyLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public java.util.List<com.stpl.app.model.ProjectionCustHierarchy> getProjectionCustHierarchies(
		int start, int end) {
		return _projectionCustHierarchyLocalService.getProjectionCustHierarchies(start,
			end);
	}

	/**
	* Returns the number of projection cust hierarchies.
	*
	* @return the number of projection cust hierarchies
	*/
	@Override
	public int getProjectionCustHierarchiesCount() {
		return _projectionCustHierarchyLocalService.getProjectionCustHierarchiesCount();
	}

	/**
	* Returns the projection cust hierarchy with the primary key.
	*
	* @param projectionCustHierarchySid the primary key of the projection cust hierarchy
	* @return the projection cust hierarchy
	* @throws PortalException if a projection cust hierarchy with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ProjectionCustHierarchy getProjectionCustHierarchy(
		int projectionCustHierarchySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionCustHierarchyLocalService.getProjectionCustHierarchy(projectionCustHierarchySid);
	}

	/**
	* Updates the projection cust hierarchy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectionCustHierarchy the projection cust hierarchy
	* @return the projection cust hierarchy that was updated
	*/
	@Override
	public com.stpl.app.model.ProjectionCustHierarchy updateProjectionCustHierarchy(
		com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy) {
		return _projectionCustHierarchyLocalService.updateProjectionCustHierarchy(projectionCustHierarchy);
	}

	@Override
	public ProjectionCustHierarchyLocalService getWrappedService() {
		return _projectionCustHierarchyLocalService;
	}

	@Override
	public void setWrappedService(
		ProjectionCustHierarchyLocalService projectionCustHierarchyLocalService) {
		_projectionCustHierarchyLocalService = projectionCustHierarchyLocalService;
	}

	private ProjectionCustHierarchyLocalService _projectionCustHierarchyLocalService;
}