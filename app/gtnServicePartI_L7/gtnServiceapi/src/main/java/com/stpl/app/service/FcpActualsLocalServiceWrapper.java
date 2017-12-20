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
 * Provides a wrapper for {@link FcpActualsLocalService}.
 *
 * @author
 * @see FcpActualsLocalService
 * @generated
 */
@ProviderType
public class FcpActualsLocalServiceWrapper implements FcpActualsLocalService,
	ServiceWrapper<FcpActualsLocalService> {
	public FcpActualsLocalServiceWrapper(
		FcpActualsLocalService fcpActualsLocalService) {
		_fcpActualsLocalService = fcpActualsLocalService;
	}

	/**
	* Adds the fcp actuals to the database. Also notifies the appropriate model listeners.
	*
	* @param fcpActuals the fcp actuals
	* @return the fcp actuals that was added
	*/
	@Override
	public com.stpl.app.model.FcpActuals addFcpActuals(
		com.stpl.app.model.FcpActuals fcpActuals) {
		return _fcpActualsLocalService.addFcpActuals(fcpActuals);
	}

	/**
	* Creates a new fcp actuals with the primary key. Does not add the fcp actuals to the database.
	*
	* @param fcpActualsPK the primary key for the new fcp actuals
	* @return the new fcp actuals
	*/
	@Override
	public com.stpl.app.model.FcpActuals createFcpActuals(
		com.stpl.app.service.persistence.FcpActualsPK fcpActualsPK) {
		return _fcpActualsLocalService.createFcpActuals(fcpActualsPK);
	}

	/**
	* Deletes the fcp actuals from the database. Also notifies the appropriate model listeners.
	*
	* @param fcpActuals the fcp actuals
	* @return the fcp actuals that was removed
	*/
	@Override
	public com.stpl.app.model.FcpActuals deleteFcpActuals(
		com.stpl.app.model.FcpActuals fcpActuals) {
		return _fcpActualsLocalService.deleteFcpActuals(fcpActuals);
	}

	/**
	* Deletes the fcp actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fcpActualsPK the primary key of the fcp actuals
	* @return the fcp actuals that was removed
	* @throws PortalException if a fcp actuals with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.FcpActuals deleteFcpActuals(
		com.stpl.app.service.persistence.FcpActualsPK fcpActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fcpActualsLocalService.deleteFcpActuals(fcpActualsPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fcpActualsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fcpActualsLocalService.dynamicQuery();
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
		return _fcpActualsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fcpActualsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fcpActualsLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _fcpActualsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _fcpActualsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.FcpActuals fetchFcpActuals(
		com.stpl.app.service.persistence.FcpActualsPK fcpActualsPK) {
		return _fcpActualsLocalService.fetchFcpActuals(fcpActualsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _fcpActualsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the fcp actuals with the primary key.
	*
	* @param fcpActualsPK the primary key of the fcp actuals
	* @return the fcp actuals
	* @throws PortalException if a fcp actuals with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.FcpActuals getFcpActuals(
		com.stpl.app.service.persistence.FcpActualsPK fcpActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fcpActualsLocalService.getFcpActuals(fcpActualsPK);
	}

	/**
	* Returns a range of all the fcp actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fcp actualses
	* @param end the upper bound of the range of fcp actualses (not inclusive)
	* @return the range of fcp actualses
	*/
	@Override
	public java.util.List<com.stpl.app.model.FcpActuals> getFcpActualses(
		int start, int end) {
		return _fcpActualsLocalService.getFcpActualses(start, end);
	}

	/**
	* Returns the number of fcp actualses.
	*
	* @return the number of fcp actualses
	*/
	@Override
	public int getFcpActualsesCount() {
		return _fcpActualsLocalService.getFcpActualsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _fcpActualsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _fcpActualsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fcpActualsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the fcp actuals in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param fcpActuals the fcp actuals
	* @return the fcp actuals that was updated
	*/
	@Override
	public com.stpl.app.model.FcpActuals updateFcpActuals(
		com.stpl.app.model.FcpActuals fcpActuals) {
		return _fcpActualsLocalService.updateFcpActuals(fcpActuals);
	}

	@Override
	public FcpActualsLocalService getWrappedService() {
		return _fcpActualsLocalService;
	}

	@Override
	public void setWrappedService(FcpActualsLocalService fcpActualsLocalService) {
		_fcpActualsLocalService = fcpActualsLocalService;
	}

	private FcpActualsLocalService _fcpActualsLocalService;
}