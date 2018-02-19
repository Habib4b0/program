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
 * Provides a wrapper for {@link StNmDiscountProjectionLocalService}.
 *
 * @author
 * @see StNmDiscountProjectionLocalService
 * @generated
 */
@ProviderType
public class StNmDiscountProjectionLocalServiceWrapper
	implements StNmDiscountProjectionLocalService,
		ServiceWrapper<StNmDiscountProjectionLocalService> {
	public StNmDiscountProjectionLocalServiceWrapper(
		StNmDiscountProjectionLocalService stNmDiscountProjectionLocalService) {
		_stNmDiscountProjectionLocalService = stNmDiscountProjectionLocalService;
	}

	/**
	* Adds the st nm discount projection to the database. Also notifies the appropriate model listeners.
	*
	* @param stNmDiscountProjection the st nm discount projection
	* @return the st nm discount projection that was added
	*/
	@Override
	public com.stpl.app.model.StNmDiscountProjection addStNmDiscountProjection(
		com.stpl.app.model.StNmDiscountProjection stNmDiscountProjection) {
		return _stNmDiscountProjectionLocalService.addStNmDiscountProjection(stNmDiscountProjection);
	}

	/**
	* Creates a new st nm discount projection with the primary key. Does not add the st nm discount projection to the database.
	*
	* @param stNmDiscountProjectionPK the primary key for the new st nm discount projection
	* @return the new st nm discount projection
	*/
	@Override
	public com.stpl.app.model.StNmDiscountProjection createStNmDiscountProjection(
		com.stpl.app.service.persistence.StNmDiscountProjectionPK stNmDiscountProjectionPK) {
		return _stNmDiscountProjectionLocalService.createStNmDiscountProjection(stNmDiscountProjectionPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmDiscountProjectionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st nm discount projection from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmDiscountProjection the st nm discount projection
	* @return the st nm discount projection that was removed
	*/
	@Override
	public com.stpl.app.model.StNmDiscountProjection deleteStNmDiscountProjection(
		com.stpl.app.model.StNmDiscountProjection stNmDiscountProjection) {
		return _stNmDiscountProjectionLocalService.deleteStNmDiscountProjection(stNmDiscountProjection);
	}

	/**
	* Deletes the st nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmDiscountProjectionPK the primary key of the st nm discount projection
	* @return the st nm discount projection that was removed
	* @throws PortalException if a st nm discount projection with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmDiscountProjection deleteStNmDiscountProjection(
		com.stpl.app.service.persistence.StNmDiscountProjectionPK stNmDiscountProjectionPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmDiscountProjectionLocalService.deleteStNmDiscountProjection(stNmDiscountProjectionPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stNmDiscountProjectionLocalService.dynamicQuery();
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
		return _stNmDiscountProjectionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmDiscountProjectionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmDiscountProjectionLocalService.dynamicQuery(dynamicQuery,
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
		return _stNmDiscountProjectionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stNmDiscountProjectionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StNmDiscountProjection fetchStNmDiscountProjection(
		com.stpl.app.service.persistence.StNmDiscountProjectionPK stNmDiscountProjectionPK) {
		return _stNmDiscountProjectionLocalService.fetchStNmDiscountProjection(stNmDiscountProjectionPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stNmDiscountProjectionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stNmDiscountProjectionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stNmDiscountProjectionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmDiscountProjectionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st nm discount projection with the primary key.
	*
	* @param stNmDiscountProjectionPK the primary key of the st nm discount projection
	* @return the st nm discount projection
	* @throws PortalException if a st nm discount projection with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmDiscountProjection getStNmDiscountProjection(
		com.stpl.app.service.persistence.StNmDiscountProjectionPK stNmDiscountProjectionPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmDiscountProjectionLocalService.getStNmDiscountProjection(stNmDiscountProjectionPK);
	}

	/**
	* Returns a range of all the st nm discount projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount projections
	* @param end the upper bound of the range of st nm discount projections (not inclusive)
	* @return the range of st nm discount projections
	*/
	@Override
	public java.util.List<com.stpl.app.model.StNmDiscountProjection> getStNmDiscountProjections(
		int start, int end) {
		return _stNmDiscountProjectionLocalService.getStNmDiscountProjections(start,
			end);
	}

	/**
	* Returns the number of st nm discount projections.
	*
	* @return the number of st nm discount projections
	*/
	@Override
	public int getStNmDiscountProjectionsCount() {
		return _stNmDiscountProjectionLocalService.getStNmDiscountProjectionsCount();
	}

	/**
	* Updates the st nm discount projection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stNmDiscountProjection the st nm discount projection
	* @return the st nm discount projection that was updated
	*/
	@Override
	public com.stpl.app.model.StNmDiscountProjection updateStNmDiscountProjection(
		com.stpl.app.model.StNmDiscountProjection stNmDiscountProjection) {
		return _stNmDiscountProjectionLocalService.updateStNmDiscountProjection(stNmDiscountProjection);
	}

	@Override
	public StNmDiscountProjectionLocalService getWrappedService() {
		return _stNmDiscountProjectionLocalService;
	}

	@Override
	public void setWrappedService(
		StNmDiscountProjectionLocalService stNmDiscountProjectionLocalService) {
		_stNmDiscountProjectionLocalService = stNmDiscountProjectionLocalService;
	}

	private StNmDiscountProjectionLocalService _stNmDiscountProjectionLocalService;
}