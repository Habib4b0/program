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
 * Provides a wrapper for {@link StNmPpaProjectionLocalService}.
 *
 * @author
 * @see StNmPpaProjectionLocalService
 * @generated
 */
@ProviderType
public class StNmPpaProjectionLocalServiceWrapper
	implements StNmPpaProjectionLocalService,
		ServiceWrapper<StNmPpaProjectionLocalService> {
	public StNmPpaProjectionLocalServiceWrapper(
		StNmPpaProjectionLocalService stNmPpaProjectionLocalService) {
		_stNmPpaProjectionLocalService = stNmPpaProjectionLocalService;
	}

	/**
	* Adds the st nm ppa projection to the database. Also notifies the appropriate model listeners.
	*
	* @param stNmPpaProjection the st nm ppa projection
	* @return the st nm ppa projection that was added
	*/
	@Override
	public com.stpl.app.model.StNmPpaProjection addStNmPpaProjection(
		com.stpl.app.model.StNmPpaProjection stNmPpaProjection) {
		return _stNmPpaProjectionLocalService.addStNmPpaProjection(stNmPpaProjection);
	}

	/**
	* Creates a new st nm ppa projection with the primary key. Does not add the st nm ppa projection to the database.
	*
	* @param stNmPpaProjectionPK the primary key for the new st nm ppa projection
	* @return the new st nm ppa projection
	*/
	@Override
	public com.stpl.app.model.StNmPpaProjection createStNmPpaProjection(
		com.stpl.app.service.persistence.StNmPpaProjectionPK stNmPpaProjectionPK) {
		return _stNmPpaProjectionLocalService.createStNmPpaProjection(stNmPpaProjectionPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmPpaProjectionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st nm ppa projection from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmPpaProjection the st nm ppa projection
	* @return the st nm ppa projection that was removed
	*/
	@Override
	public com.stpl.app.model.StNmPpaProjection deleteStNmPpaProjection(
		com.stpl.app.model.StNmPpaProjection stNmPpaProjection) {
		return _stNmPpaProjectionLocalService.deleteStNmPpaProjection(stNmPpaProjection);
	}

	/**
	* Deletes the st nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmPpaProjectionPK the primary key of the st nm ppa projection
	* @return the st nm ppa projection that was removed
	* @throws PortalException if a st nm ppa projection with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmPpaProjection deleteStNmPpaProjection(
		com.stpl.app.service.persistence.StNmPpaProjectionPK stNmPpaProjectionPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmPpaProjectionLocalService.deleteStNmPpaProjection(stNmPpaProjectionPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stNmPpaProjectionLocalService.dynamicQuery();
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
		return _stNmPpaProjectionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmPpaProjectionLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmPpaProjectionLocalService.dynamicQuery(dynamicQuery, start,
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
		return _stNmPpaProjectionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stNmPpaProjectionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StNmPpaProjection fetchStNmPpaProjection(
		com.stpl.app.service.persistence.StNmPpaProjectionPK stNmPpaProjectionPK) {
		return _stNmPpaProjectionLocalService.fetchStNmPpaProjection(stNmPpaProjectionPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stNmPpaProjectionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stNmPpaProjectionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stNmPpaProjectionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmPpaProjectionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st nm ppa projection with the primary key.
	*
	* @param stNmPpaProjectionPK the primary key of the st nm ppa projection
	* @return the st nm ppa projection
	* @throws PortalException if a st nm ppa projection with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmPpaProjection getStNmPpaProjection(
		com.stpl.app.service.persistence.StNmPpaProjectionPK stNmPpaProjectionPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmPpaProjectionLocalService.getStNmPpaProjection(stNmPpaProjectionPK);
	}

	/**
	* Returns a range of all the st nm ppa projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm ppa projections
	* @param end the upper bound of the range of st nm ppa projections (not inclusive)
	* @return the range of st nm ppa projections
	*/
	@Override
	public java.util.List<com.stpl.app.model.StNmPpaProjection> getStNmPpaProjections(
		int start, int end) {
		return _stNmPpaProjectionLocalService.getStNmPpaProjections(start, end);
	}

	/**
	* Returns the number of st nm ppa projections.
	*
	* @return the number of st nm ppa projections
	*/
	@Override
	public int getStNmPpaProjectionsCount() {
		return _stNmPpaProjectionLocalService.getStNmPpaProjectionsCount();
	}

	/**
	* Updates the st nm ppa projection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stNmPpaProjection the st nm ppa projection
	* @return the st nm ppa projection that was updated
	*/
	@Override
	public com.stpl.app.model.StNmPpaProjection updateStNmPpaProjection(
		com.stpl.app.model.StNmPpaProjection stNmPpaProjection) {
		return _stNmPpaProjectionLocalService.updateStNmPpaProjection(stNmPpaProjection);
	}

	@Override
	public StNmPpaProjectionLocalService getWrappedService() {
		return _stNmPpaProjectionLocalService;
	}

	@Override
	public void setWrappedService(
		StNmPpaProjectionLocalService stNmPpaProjectionLocalService) {
		_stNmPpaProjectionLocalService = stNmPpaProjectionLocalService;
	}

	private StNmPpaProjectionLocalService _stNmPpaProjectionLocalService;
}