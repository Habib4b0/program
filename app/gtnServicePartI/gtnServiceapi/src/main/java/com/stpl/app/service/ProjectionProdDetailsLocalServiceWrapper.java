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
 * Provides a wrapper for {@link ProjectionProdDetailsLocalService}.
 *
 * @author
 * @see ProjectionProdDetailsLocalService
 * @generated
 */
@ProviderType
public class ProjectionProdDetailsLocalServiceWrapper
	implements ProjectionProdDetailsLocalService,
		ServiceWrapper<ProjectionProdDetailsLocalService> {
	public ProjectionProdDetailsLocalServiceWrapper(
		ProjectionProdDetailsLocalService projectionProdDetailsLocalService) {
		_projectionProdDetailsLocalService = projectionProdDetailsLocalService;
	}

	/**
	* Adds the projection prod details to the database. Also notifies the appropriate model listeners.
	*
	* @param projectionProdDetails the projection prod details
	* @return the projection prod details that was added
	*/
	@Override
	public com.stpl.app.model.ProjectionProdDetails addProjectionProdDetails(
		com.stpl.app.model.ProjectionProdDetails projectionProdDetails) {
		return _projectionProdDetailsLocalService.addProjectionProdDetails(projectionProdDetails);
	}

	/**
	* Creates a new projection prod details with the primary key. Does not add the projection prod details to the database.
	*
	* @param productDetailsId the primary key for the new projection prod details
	* @return the new projection prod details
	*/
	@Override
	public com.stpl.app.model.ProjectionProdDetails createProjectionProdDetails(
		int productDetailsId) {
		return _projectionProdDetailsLocalService.createProjectionProdDetails(productDetailsId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionProdDetailsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the projection prod details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param productDetailsId the primary key of the projection prod details
	* @return the projection prod details that was removed
	* @throws PortalException if a projection prod details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ProjectionProdDetails deleteProjectionProdDetails(
		int productDetailsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionProdDetailsLocalService.deleteProjectionProdDetails(productDetailsId);
	}

	/**
	* Deletes the projection prod details from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionProdDetails the projection prod details
	* @return the projection prod details that was removed
	*/
	@Override
	public com.stpl.app.model.ProjectionProdDetails deleteProjectionProdDetails(
		com.stpl.app.model.ProjectionProdDetails projectionProdDetails) {
		return _projectionProdDetailsLocalService.deleteProjectionProdDetails(projectionProdDetails);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _projectionProdDetailsLocalService.dynamicQuery();
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
		return _projectionProdDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectionProdDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectionProdDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _projectionProdDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _projectionProdDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ProjectionProdDetails fetchProjectionProdDetails(
		int productDetailsId) {
		return _projectionProdDetailsLocalService.fetchProjectionProdDetails(productDetailsId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _projectionProdDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _projectionProdDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _projectionProdDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionProdDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the projection prod details with the primary key.
	*
	* @param productDetailsId the primary key of the projection prod details
	* @return the projection prod details
	* @throws PortalException if a projection prod details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ProjectionProdDetails getProjectionProdDetails(
		int productDetailsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionProdDetailsLocalService.getProjectionProdDetails(productDetailsId);
	}

	/**
	* Returns a range of all the projection prod detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection prod detailses
	* @param end the upper bound of the range of projection prod detailses (not inclusive)
	* @return the range of projection prod detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.ProjectionProdDetails> getProjectionProdDetailses(
		int start, int end) {
		return _projectionProdDetailsLocalService.getProjectionProdDetailses(start,
			end);
	}

	/**
	* Returns the number of projection prod detailses.
	*
	* @return the number of projection prod detailses
	*/
	@Override
	public int getProjectionProdDetailsesCount() {
		return _projectionProdDetailsLocalService.getProjectionProdDetailsesCount();
	}

	/**
	* Updates the projection prod details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectionProdDetails the projection prod details
	* @return the projection prod details that was updated
	*/
	@Override
	public com.stpl.app.model.ProjectionProdDetails updateProjectionProdDetails(
		com.stpl.app.model.ProjectionProdDetails projectionProdDetails) {
		return _projectionProdDetailsLocalService.updateProjectionProdDetails(projectionProdDetails);
	}

	@Override
	public ProjectionProdDetailsLocalService getWrappedService() {
		return _projectionProdDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		ProjectionProdDetailsLocalService projectionProdDetailsLocalService) {
		_projectionProdDetailsLocalService = projectionProdDetailsLocalService;
	}

	private ProjectionProdDetailsLocalService _projectionProdDetailsLocalService;
}