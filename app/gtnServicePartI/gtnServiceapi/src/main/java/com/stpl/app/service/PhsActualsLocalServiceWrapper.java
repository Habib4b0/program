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
 * Provides a wrapper for {@link PhsActualsLocalService}.
 *
 * @author
 * @see PhsActualsLocalService
 * @generated
 */
@ProviderType
public class PhsActualsLocalServiceWrapper implements PhsActualsLocalService,
	ServiceWrapper<PhsActualsLocalService> {
	public PhsActualsLocalServiceWrapper(
		PhsActualsLocalService phsActualsLocalService) {
		_phsActualsLocalService = phsActualsLocalService;
	}

	/**
	* Adds the phs actuals to the database. Also notifies the appropriate model listeners.
	*
	* @param phsActuals the phs actuals
	* @return the phs actuals that was added
	*/
	@Override
	public com.stpl.app.model.PhsActuals addPhsActuals(
		com.stpl.app.model.PhsActuals phsActuals) {
		return _phsActualsLocalService.addPhsActuals(phsActuals);
	}

	/**
	* Creates a new phs actuals with the primary key. Does not add the phs actuals to the database.
	*
	* @param phsActualsPK the primary key for the new phs actuals
	* @return the new phs actuals
	*/
	@Override
	public com.stpl.app.model.PhsActuals createPhsActuals(
		com.stpl.app.service.persistence.PhsActualsPK phsActualsPK) {
		return _phsActualsLocalService.createPhsActuals(phsActualsPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phsActualsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the phs actuals from the database. Also notifies the appropriate model listeners.
	*
	* @param phsActuals the phs actuals
	* @return the phs actuals that was removed
	*/
	@Override
	public com.stpl.app.model.PhsActuals deletePhsActuals(
		com.stpl.app.model.PhsActuals phsActuals) {
		return _phsActualsLocalService.deletePhsActuals(phsActuals);
	}

	/**
	* Deletes the phs actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param phsActualsPK the primary key of the phs actuals
	* @return the phs actuals that was removed
	* @throws PortalException if a phs actuals with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PhsActuals deletePhsActuals(
		com.stpl.app.service.persistence.PhsActualsPK phsActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phsActualsLocalService.deletePhsActuals(phsActualsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _phsActualsLocalService.dynamicQuery();
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
		return _phsActualsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _phsActualsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _phsActualsLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _phsActualsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _phsActualsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.PhsActuals fetchPhsActuals(
		com.stpl.app.service.persistence.PhsActualsPK phsActualsPK) {
		return _phsActualsLocalService.fetchPhsActuals(phsActualsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _phsActualsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _phsActualsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _phsActualsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phsActualsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the phs actuals with the primary key.
	*
	* @param phsActualsPK the primary key of the phs actuals
	* @return the phs actuals
	* @throws PortalException if a phs actuals with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PhsActuals getPhsActuals(
		com.stpl.app.service.persistence.PhsActualsPK phsActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phsActualsLocalService.getPhsActuals(phsActualsPK);
	}

	/**
	* Returns a range of all the phs actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phs actualses
	* @param end the upper bound of the range of phs actualses (not inclusive)
	* @return the range of phs actualses
	*/
	@Override
	public java.util.List<com.stpl.app.model.PhsActuals> getPhsActualses(
		int start, int end) {
		return _phsActualsLocalService.getPhsActualses(start, end);
	}

	/**
	* Returns the number of phs actualses.
	*
	* @return the number of phs actualses
	*/
	@Override
	public int getPhsActualsesCount() {
		return _phsActualsLocalService.getPhsActualsesCount();
	}

	/**
	* Updates the phs actuals in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param phsActuals the phs actuals
	* @return the phs actuals that was updated
	*/
	@Override
	public com.stpl.app.model.PhsActuals updatePhsActuals(
		com.stpl.app.model.PhsActuals phsActuals) {
		return _phsActualsLocalService.updatePhsActuals(phsActuals);
	}

	@Override
	public PhsActualsLocalService getWrappedService() {
		return _phsActualsLocalService;
	}

	@Override
	public void setWrappedService(PhsActualsLocalService phsActualsLocalService) {
		_phsActualsLocalService = phsActualsLocalService;
	}

	private PhsActualsLocalService _phsActualsLocalService;
}