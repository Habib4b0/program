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
 * Provides a wrapper for {@link StNmActualPpaLocalService}.
 *
 * @author
 * @see StNmActualPpaLocalService
 * @generated
 */
@ProviderType
public class StNmActualPpaLocalServiceWrapper
	implements StNmActualPpaLocalService,
		ServiceWrapper<StNmActualPpaLocalService> {
	public StNmActualPpaLocalServiceWrapper(
		StNmActualPpaLocalService stNmActualPpaLocalService) {
		_stNmActualPpaLocalService = stNmActualPpaLocalService;
	}

	/**
	* Adds the st nm actual ppa to the database. Also notifies the appropriate model listeners.
	*
	* @param stNmActualPpa the st nm actual ppa
	* @return the st nm actual ppa that was added
	*/
	@Override
	public com.stpl.app.model.StNmActualPpa addStNmActualPpa(
		com.stpl.app.model.StNmActualPpa stNmActualPpa) {
		return _stNmActualPpaLocalService.addStNmActualPpa(stNmActualPpa);
	}

	/**
	* Creates a new st nm actual ppa with the primary key. Does not add the st nm actual ppa to the database.
	*
	* @param stNmActualPpaPK the primary key for the new st nm actual ppa
	* @return the new st nm actual ppa
	*/
	@Override
	public com.stpl.app.model.StNmActualPpa createStNmActualPpa(
		com.stpl.app.service.persistence.StNmActualPpaPK stNmActualPpaPK) {
		return _stNmActualPpaLocalService.createStNmActualPpa(stNmActualPpaPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmActualPpaLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st nm actual ppa from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmActualPpa the st nm actual ppa
	* @return the st nm actual ppa that was removed
	*/
	@Override
	public com.stpl.app.model.StNmActualPpa deleteStNmActualPpa(
		com.stpl.app.model.StNmActualPpa stNmActualPpa) {
		return _stNmActualPpaLocalService.deleteStNmActualPpa(stNmActualPpa);
	}

	/**
	* Deletes the st nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmActualPpaPK the primary key of the st nm actual ppa
	* @return the st nm actual ppa that was removed
	* @throws PortalException if a st nm actual ppa with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmActualPpa deleteStNmActualPpa(
		com.stpl.app.service.persistence.StNmActualPpaPK stNmActualPpaPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmActualPpaLocalService.deleteStNmActualPpa(stNmActualPpaPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stNmActualPpaLocalService.dynamicQuery();
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
		return _stNmActualPpaLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmActualPpaLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmActualPpaLocalService.dynamicQuery(dynamicQuery, start,
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
		return _stNmActualPpaLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stNmActualPpaLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StNmActualPpa fetchStNmActualPpa(
		com.stpl.app.service.persistence.StNmActualPpaPK stNmActualPpaPK) {
		return _stNmActualPpaLocalService.fetchStNmActualPpa(stNmActualPpaPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stNmActualPpaLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stNmActualPpaLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stNmActualPpaLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmActualPpaLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st nm actual ppa with the primary key.
	*
	* @param stNmActualPpaPK the primary key of the st nm actual ppa
	* @return the st nm actual ppa
	* @throws PortalException if a st nm actual ppa with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmActualPpa getStNmActualPpa(
		com.stpl.app.service.persistence.StNmActualPpaPK stNmActualPpaPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmActualPpaLocalService.getStNmActualPpa(stNmActualPpaPK);
	}

	/**
	* Returns a range of all the st nm actual ppas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual ppas
	* @param end the upper bound of the range of st nm actual ppas (not inclusive)
	* @return the range of st nm actual ppas
	*/
	@Override
	public java.util.List<com.stpl.app.model.StNmActualPpa> getStNmActualPpas(
		int start, int end) {
		return _stNmActualPpaLocalService.getStNmActualPpas(start, end);
	}

	/**
	* Returns the number of st nm actual ppas.
	*
	* @return the number of st nm actual ppas
	*/
	@Override
	public int getStNmActualPpasCount() {
		return _stNmActualPpaLocalService.getStNmActualPpasCount();
	}

	/**
	* Updates the st nm actual ppa in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stNmActualPpa the st nm actual ppa
	* @return the st nm actual ppa that was updated
	*/
	@Override
	public com.stpl.app.model.StNmActualPpa updateStNmActualPpa(
		com.stpl.app.model.StNmActualPpa stNmActualPpa) {
		return _stNmActualPpaLocalService.updateStNmActualPpa(stNmActualPpa);
	}

	@Override
	public StNmActualPpaLocalService getWrappedService() {
		return _stNmActualPpaLocalService;
	}

	@Override
	public void setWrappedService(
		StNmActualPpaLocalService stNmActualPpaLocalService) {
		_stNmActualPpaLocalService = stNmActualPpaLocalService;
	}

	private StNmActualPpaLocalService _stNmActualPpaLocalService;
}