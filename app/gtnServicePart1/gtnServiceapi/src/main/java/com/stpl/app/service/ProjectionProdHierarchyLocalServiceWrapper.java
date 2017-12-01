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
 * Provides a wrapper for {@link ProjectionProdHierarchyLocalService}.
 *
 * @author
 * @see ProjectionProdHierarchyLocalService
 * @generated
 */
@ProviderType
public class ProjectionProdHierarchyLocalServiceWrapper
	implements ProjectionProdHierarchyLocalService,
		ServiceWrapper<ProjectionProdHierarchyLocalService> {
	public ProjectionProdHierarchyLocalServiceWrapper(
		ProjectionProdHierarchyLocalService projectionProdHierarchyLocalService) {
		_projectionProdHierarchyLocalService = projectionProdHierarchyLocalService;
	}

	/**
	* Adds the projection prod hierarchy to the database. Also notifies the appropriate model listeners.
	*
	* @param projectionProdHierarchy the projection prod hierarchy
	* @return the projection prod hierarchy that was added
	*/
	@Override
	public com.stpl.app.model.ProjectionProdHierarchy addProjectionProdHierarchy(
		com.stpl.app.model.ProjectionProdHierarchy projectionProdHierarchy) {
		return _projectionProdHierarchyLocalService.addProjectionProdHierarchy(projectionProdHierarchy);
	}

	/**
	* Creates a new projection prod hierarchy with the primary key. Does not add the projection prod hierarchy to the database.
	*
	* @param projectionProdHierarchySid the primary key for the new projection prod hierarchy
	* @return the new projection prod hierarchy
	*/
	@Override
	public com.stpl.app.model.ProjectionProdHierarchy createProjectionProdHierarchy(
		int projectionProdHierarchySid) {
		return _projectionProdHierarchyLocalService.createProjectionProdHierarchy(projectionProdHierarchySid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionProdHierarchyLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the projection prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionProdHierarchySid the primary key of the projection prod hierarchy
	* @return the projection prod hierarchy that was removed
	* @throws PortalException if a projection prod hierarchy with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ProjectionProdHierarchy deleteProjectionProdHierarchy(
		int projectionProdHierarchySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionProdHierarchyLocalService.deleteProjectionProdHierarchy(projectionProdHierarchySid);
	}

	/**
	* Deletes the projection prod hierarchy from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionProdHierarchy the projection prod hierarchy
	* @return the projection prod hierarchy that was removed
	*/
	@Override
	public com.stpl.app.model.ProjectionProdHierarchy deleteProjectionProdHierarchy(
		com.stpl.app.model.ProjectionProdHierarchy projectionProdHierarchy) {
		return _projectionProdHierarchyLocalService.deleteProjectionProdHierarchy(projectionProdHierarchy);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _projectionProdHierarchyLocalService.dynamicQuery();
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
		return _projectionProdHierarchyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectionProdHierarchyLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectionProdHierarchyLocalService.dynamicQuery(dynamicQuery,
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
		return _projectionProdHierarchyLocalService.dynamicQueryCount(dynamicQuery);
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
		return _projectionProdHierarchyLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ProjectionProdHierarchy fetchProjectionProdHierarchy(
		int projectionProdHierarchySid) {
		return _projectionProdHierarchyLocalService.fetchProjectionProdHierarchy(projectionProdHierarchySid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _projectionProdHierarchyLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _projectionProdHierarchyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _projectionProdHierarchyLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionProdHierarchyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the projection prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection prod hierarchies
	* @param end the upper bound of the range of projection prod hierarchies (not inclusive)
	* @return the range of projection prod hierarchies
	*/
	@Override
	public java.util.List<com.stpl.app.model.ProjectionProdHierarchy> getProjectionProdHierarchies(
		int start, int end) {
		return _projectionProdHierarchyLocalService.getProjectionProdHierarchies(start,
			end);
	}

	/**
	* Returns the number of projection prod hierarchies.
	*
	* @return the number of projection prod hierarchies
	*/
	@Override
	public int getProjectionProdHierarchiesCount() {
		return _projectionProdHierarchyLocalService.getProjectionProdHierarchiesCount();
	}

	/**
	* Returns the projection prod hierarchy with the primary key.
	*
	* @param projectionProdHierarchySid the primary key of the projection prod hierarchy
	* @return the projection prod hierarchy
	* @throws PortalException if a projection prod hierarchy with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ProjectionProdHierarchy getProjectionProdHierarchy(
		int projectionProdHierarchySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionProdHierarchyLocalService.getProjectionProdHierarchy(projectionProdHierarchySid);
	}

	/**
	* Updates the projection prod hierarchy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectionProdHierarchy the projection prod hierarchy
	* @return the projection prod hierarchy that was updated
	*/
	@Override
	public com.stpl.app.model.ProjectionProdHierarchy updateProjectionProdHierarchy(
		com.stpl.app.model.ProjectionProdHierarchy projectionProdHierarchy) {
		return _projectionProdHierarchyLocalService.updateProjectionProdHierarchy(projectionProdHierarchy);
	}

	@Override
	public ProjectionProdHierarchyLocalService getWrappedService() {
		return _projectionProdHierarchyLocalService;
	}

	@Override
	public void setWrappedService(
		ProjectionProdHierarchyLocalService projectionProdHierarchyLocalService) {
		_projectionProdHierarchyLocalService = projectionProdHierarchyLocalService;
	}

	private ProjectionProdHierarchyLocalService _projectionProdHierarchyLocalService;
}