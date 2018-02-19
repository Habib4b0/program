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
 * Provides a wrapper for {@link ProjectionCustDetailsLocalService}.
 *
 * @author
 * @see ProjectionCustDetailsLocalService
 * @generated
 */
@ProviderType
public class ProjectionCustDetailsLocalServiceWrapper
	implements ProjectionCustDetailsLocalService,
		ServiceWrapper<ProjectionCustDetailsLocalService> {
	public ProjectionCustDetailsLocalServiceWrapper(
		ProjectionCustDetailsLocalService projectionCustDetailsLocalService) {
		_projectionCustDetailsLocalService = projectionCustDetailsLocalService;
	}

	/**
	* Adds the projection cust details to the database. Also notifies the appropriate model listeners.
	*
	* @param projectionCustDetails the projection cust details
	* @return the projection cust details that was added
	*/
	@Override
	public com.stpl.app.model.ProjectionCustDetails addProjectionCustDetails(
		com.stpl.app.model.ProjectionCustDetails projectionCustDetails) {
		return _projectionCustDetailsLocalService.addProjectionCustDetails(projectionCustDetails);
	}

	/**
	* Creates a new projection cust details with the primary key. Does not add the projection cust details to the database.
	*
	* @param customerDetailsId the primary key for the new projection cust details
	* @return the new projection cust details
	*/
	@Override
	public com.stpl.app.model.ProjectionCustDetails createProjectionCustDetails(
		int customerDetailsId) {
		return _projectionCustDetailsLocalService.createProjectionCustDetails(customerDetailsId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionCustDetailsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the projection cust details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customerDetailsId the primary key of the projection cust details
	* @return the projection cust details that was removed
	* @throws PortalException if a projection cust details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ProjectionCustDetails deleteProjectionCustDetails(
		int customerDetailsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionCustDetailsLocalService.deleteProjectionCustDetails(customerDetailsId);
	}

	/**
	* Deletes the projection cust details from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionCustDetails the projection cust details
	* @return the projection cust details that was removed
	*/
	@Override
	public com.stpl.app.model.ProjectionCustDetails deleteProjectionCustDetails(
		com.stpl.app.model.ProjectionCustDetails projectionCustDetails) {
		return _projectionCustDetailsLocalService.deleteProjectionCustDetails(projectionCustDetails);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _projectionCustDetailsLocalService.dynamicQuery();
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
		return _projectionCustDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectionCustDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectionCustDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _projectionCustDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _projectionCustDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ProjectionCustDetails fetchProjectionCustDetails(
		int customerDetailsId) {
		return _projectionCustDetailsLocalService.fetchProjectionCustDetails(customerDetailsId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _projectionCustDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _projectionCustDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _projectionCustDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionCustDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the projection cust details with the primary key.
	*
	* @param customerDetailsId the primary key of the projection cust details
	* @return the projection cust details
	* @throws PortalException if a projection cust details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ProjectionCustDetails getProjectionCustDetails(
		int customerDetailsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionCustDetailsLocalService.getProjectionCustDetails(customerDetailsId);
	}

	/**
	* Returns a range of all the projection cust detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection cust detailses
	* @param end the upper bound of the range of projection cust detailses (not inclusive)
	* @return the range of projection cust detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.ProjectionCustDetails> getProjectionCustDetailses(
		int start, int end) {
		return _projectionCustDetailsLocalService.getProjectionCustDetailses(start,
			end);
	}

	/**
	* Returns the number of projection cust detailses.
	*
	* @return the number of projection cust detailses
	*/
	@Override
	public int getProjectionCustDetailsesCount() {
		return _projectionCustDetailsLocalService.getProjectionCustDetailsesCount();
	}

	/**
	* Updates the projection cust details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectionCustDetails the projection cust details
	* @return the projection cust details that was updated
	*/
	@Override
	public com.stpl.app.model.ProjectionCustDetails updateProjectionCustDetails(
		com.stpl.app.model.ProjectionCustDetails projectionCustDetails) {
		return _projectionCustDetailsLocalService.updateProjectionCustDetails(projectionCustDetails);
	}

	@Override
	public ProjectionCustDetailsLocalService getWrappedService() {
		return _projectionCustDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		ProjectionCustDetailsLocalService projectionCustDetailsLocalService) {
		_projectionCustDetailsLocalService = projectionCustDetailsLocalService;
	}

	private ProjectionCustDetailsLocalService _projectionCustDetailsLocalService;
}