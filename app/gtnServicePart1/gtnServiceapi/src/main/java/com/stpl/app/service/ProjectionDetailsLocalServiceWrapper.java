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
 * Provides a wrapper for {@link ProjectionDetailsLocalService}.
 *
 * @author
 * @see ProjectionDetailsLocalService
 * @generated
 */
@ProviderType
public class ProjectionDetailsLocalServiceWrapper
	implements ProjectionDetailsLocalService,
		ServiceWrapper<ProjectionDetailsLocalService> {
	public ProjectionDetailsLocalServiceWrapper(
		ProjectionDetailsLocalService projectionDetailsLocalService) {
		_projectionDetailsLocalService = projectionDetailsLocalService;
	}

	/**
	* Adds the projection details to the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetails the projection details
	* @return the projection details that was added
	*/
	@Override
	public com.stpl.app.model.ProjectionDetails addProjectionDetails(
		com.stpl.app.model.ProjectionDetails projectionDetails) {
		return _projectionDetailsLocalService.addProjectionDetails(projectionDetails);
	}

	/**
	* Creates a new projection details with the primary key. Does not add the projection details to the database.
	*
	* @param projectionDetailsSid the primary key for the new projection details
	* @return the new projection details
	*/
	@Override
	public com.stpl.app.model.ProjectionDetails createProjectionDetails(
		int projectionDetailsSid) {
		return _projectionDetailsLocalService.createProjectionDetails(projectionDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionDetailsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the projection details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the projection details
	* @return the projection details that was removed
	* @throws PortalException if a projection details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ProjectionDetails deleteProjectionDetails(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionDetailsLocalService.deleteProjectionDetails(projectionDetailsSid);
	}

	/**
	* Deletes the projection details from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetails the projection details
	* @return the projection details that was removed
	*/
	@Override
	public com.stpl.app.model.ProjectionDetails deleteProjectionDetails(
		com.stpl.app.model.ProjectionDetails projectionDetails) {
		return _projectionDetailsLocalService.deleteProjectionDetails(projectionDetails);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _projectionDetailsLocalService.dynamicQuery();
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
		return _projectionDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectionDetailsLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectionDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
		return _projectionDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _projectionDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ProjectionDetails fetchProjectionDetails(
		int projectionDetailsSid) {
		return _projectionDetailsLocalService.fetchProjectionDetails(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _projectionDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _projectionDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _projectionDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the projection details with the primary key.
	*
	* @param projectionDetailsSid the primary key of the projection details
	* @return the projection details
	* @throws PortalException if a projection details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ProjectionDetails getProjectionDetails(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionDetailsLocalService.getProjectionDetails(projectionDetailsSid);
	}

	/**
	* Returns a range of all the projection detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection detailses
	* @param end the upper bound of the range of projection detailses (not inclusive)
	* @return the range of projection detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.ProjectionDetails> getProjectionDetailses(
		int start, int end) {
		return _projectionDetailsLocalService.getProjectionDetailses(start, end);
	}

	/**
	* Returns the number of projection detailses.
	*
	* @return the number of projection detailses
	*/
	@Override
	public int getProjectionDetailsesCount() {
		return _projectionDetailsLocalService.getProjectionDetailsesCount();
	}

	/**
	* Updates the projection details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectionDetails the projection details
	* @return the projection details that was updated
	*/
	@Override
	public com.stpl.app.model.ProjectionDetails updateProjectionDetails(
		com.stpl.app.model.ProjectionDetails projectionDetails) {
		return _projectionDetailsLocalService.updateProjectionDetails(projectionDetails);
	}

	@Override
	public ProjectionDetailsLocalService getWrappedService() {
		return _projectionDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		ProjectionDetailsLocalService projectionDetailsLocalService) {
		_projectionDetailsLocalService = projectionDetailsLocalService;
	}

	private ProjectionDetailsLocalService _projectionDetailsLocalService;
}