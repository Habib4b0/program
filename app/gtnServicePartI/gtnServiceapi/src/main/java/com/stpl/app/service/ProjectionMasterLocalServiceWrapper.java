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
 * Provides a wrapper for {@link ProjectionMasterLocalService}.
 *
 * @author
 * @see ProjectionMasterLocalService
 * @generated
 */
@ProviderType
public class ProjectionMasterLocalServiceWrapper
	implements ProjectionMasterLocalService,
		ServiceWrapper<ProjectionMasterLocalService> {
	public ProjectionMasterLocalServiceWrapper(
		ProjectionMasterLocalService projectionMasterLocalService) {
		_projectionMasterLocalService = projectionMasterLocalService;
	}

	/**
	* Adds the projection master to the database. Also notifies the appropriate model listeners.
	*
	* @param projectionMaster the projection master
	* @return the projection master that was added
	*/
	@Override
	public com.stpl.app.model.ProjectionMaster addProjectionMaster(
		com.stpl.app.model.ProjectionMaster projectionMaster) {
		return _projectionMasterLocalService.addProjectionMaster(projectionMaster);
	}

	/**
	* Creates a new projection master with the primary key. Does not add the projection master to the database.
	*
	* @param projectionMasterSid the primary key for the new projection master
	* @return the new projection master
	*/
	@Override
	public com.stpl.app.model.ProjectionMaster createProjectionMaster(
		int projectionMasterSid) {
		return _projectionMasterLocalService.createProjectionMaster(projectionMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionMasterSid the primary key of the projection master
	* @return the projection master that was removed
	* @throws PortalException if a projection master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ProjectionMaster deleteProjectionMaster(
		int projectionMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionMasterLocalService.deleteProjectionMaster(projectionMasterSid);
	}

	/**
	* Deletes the projection master from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionMaster the projection master
	* @return the projection master that was removed
	*/
	@Override
	public com.stpl.app.model.ProjectionMaster deleteProjectionMaster(
		com.stpl.app.model.ProjectionMaster projectionMaster) {
		return _projectionMasterLocalService.deleteProjectionMaster(projectionMaster);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _projectionMasterLocalService.dynamicQuery();
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
		return _projectionMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectionMasterLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectionMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _projectionMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _projectionMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ProjectionMaster fetchProjectionMaster(
		int projectionMasterSid) {
		return _projectionMasterLocalService.fetchProjectionMaster(projectionMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _projectionMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _projectionMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _projectionMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the projection master with the primary key.
	*
	* @param projectionMasterSid the primary key of the projection master
	* @return the projection master
	* @throws PortalException if a projection master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ProjectionMaster getProjectionMaster(
		int projectionMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectionMasterLocalService.getProjectionMaster(projectionMasterSid);
	}

	/**
	* Returns a range of all the projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection masters
	* @param end the upper bound of the range of projection masters (not inclusive)
	* @return the range of projection masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.ProjectionMaster> getProjectionMasters(
		int start, int end) {
		return _projectionMasterLocalService.getProjectionMasters(start, end);
	}

	/**
	* Returns the number of projection masters.
	*
	* @return the number of projection masters
	*/
	@Override
	public int getProjectionMastersCount() {
		return _projectionMasterLocalService.getProjectionMastersCount();
	}

	/**
	* Updates the projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectionMaster the projection master
	* @return the projection master that was updated
	*/
	@Override
	public com.stpl.app.model.ProjectionMaster updateProjectionMaster(
		com.stpl.app.model.ProjectionMaster projectionMaster) {
		return _projectionMasterLocalService.updateProjectionMaster(projectionMaster);
	}

	@Override
	public ProjectionMasterLocalService getWrappedService() {
		return _projectionMasterLocalService;
	}

	@Override
	public void setWrappedService(
		ProjectionMasterLocalService projectionMasterLocalService) {
		_projectionMasterLocalService = projectionMasterLocalService;
	}

	private ProjectionMasterLocalService _projectionMasterLocalService;
}