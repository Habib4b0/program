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
 * Provides a wrapper for {@link StMSupplementalDiscActualsLocalService}.
 *
 * @author
 * @see StMSupplementalDiscActualsLocalService
 * @generated
 */
@ProviderType
public class StMSupplementalDiscActualsLocalServiceWrapper
	implements StMSupplementalDiscActualsLocalService,
		ServiceWrapper<StMSupplementalDiscActualsLocalService> {
	public StMSupplementalDiscActualsLocalServiceWrapper(
		StMSupplementalDiscActualsLocalService stMSupplementalDiscActualsLocalService) {
		_stMSupplementalDiscActualsLocalService = stMSupplementalDiscActualsLocalService;
	}

	/**
	* Adds the st m supplemental disc actuals to the database. Also notifies the appropriate model listeners.
	*
	* @param stMSupplementalDiscActuals the st m supplemental disc actuals
	* @return the st m supplemental disc actuals that was added
	*/
	@Override
	public com.stpl.app.model.StMSupplementalDiscActuals addStMSupplementalDiscActuals(
		com.stpl.app.model.StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		return _stMSupplementalDiscActualsLocalService.addStMSupplementalDiscActuals(stMSupplementalDiscActuals);
	}

	/**
	* Creates a new st m supplemental disc actuals with the primary key. Does not add the st m supplemental disc actuals to the database.
	*
	* @param stMSupplementalDiscActualsPK the primary key for the new st m supplemental disc actuals
	* @return the new st m supplemental disc actuals
	*/
	@Override
	public com.stpl.app.model.StMSupplementalDiscActuals createStMSupplementalDiscActuals(
		com.stpl.app.service.persistence.StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK) {
		return _stMSupplementalDiscActualsLocalService.createStMSupplementalDiscActuals(stMSupplementalDiscActualsPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stMSupplementalDiscActualsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st m supplemental disc actuals from the database. Also notifies the appropriate model listeners.
	*
	* @param stMSupplementalDiscActuals the st m supplemental disc actuals
	* @return the st m supplemental disc actuals that was removed
	*/
	@Override
	public com.stpl.app.model.StMSupplementalDiscActuals deleteStMSupplementalDiscActuals(
		com.stpl.app.model.StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		return _stMSupplementalDiscActualsLocalService.deleteStMSupplementalDiscActuals(stMSupplementalDiscActuals);
	}

	/**
	* Deletes the st m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
	* @return the st m supplemental disc actuals that was removed
	* @throws PortalException if a st m supplemental disc actuals with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StMSupplementalDiscActuals deleteStMSupplementalDiscActuals(
		com.stpl.app.service.persistence.StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stMSupplementalDiscActualsLocalService.deleteStMSupplementalDiscActuals(stMSupplementalDiscActualsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stMSupplementalDiscActualsLocalService.dynamicQuery();
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
		return _stMSupplementalDiscActualsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stMSupplementalDiscActualsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stMSupplementalDiscActualsLocalService.dynamicQuery(dynamicQuery,
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
		return _stMSupplementalDiscActualsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stMSupplementalDiscActualsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StMSupplementalDiscActuals fetchStMSupplementalDiscActuals(
		com.stpl.app.service.persistence.StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK) {
		return _stMSupplementalDiscActualsLocalService.fetchStMSupplementalDiscActuals(stMSupplementalDiscActualsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stMSupplementalDiscActualsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stMSupplementalDiscActualsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stMSupplementalDiscActualsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stMSupplementalDiscActualsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st m supplemental disc actuals with the primary key.
	*
	* @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
	* @return the st m supplemental disc actuals
	* @throws PortalException if a st m supplemental disc actuals with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StMSupplementalDiscActuals getStMSupplementalDiscActuals(
		com.stpl.app.service.persistence.StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stMSupplementalDiscActualsLocalService.getStMSupplementalDiscActuals(stMSupplementalDiscActualsPK);
	}

	/**
	* Returns a range of all the st m supplemental disc actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m supplemental disc actualses
	* @param end the upper bound of the range of st m supplemental disc actualses (not inclusive)
	* @return the range of st m supplemental disc actualses
	*/
	@Override
	public java.util.List<com.stpl.app.model.StMSupplementalDiscActuals> getStMSupplementalDiscActualses(
		int start, int end) {
		return _stMSupplementalDiscActualsLocalService.getStMSupplementalDiscActualses(start,
			end);
	}

	/**
	* Returns the number of st m supplemental disc actualses.
	*
	* @return the number of st m supplemental disc actualses
	*/
	@Override
	public int getStMSupplementalDiscActualsesCount() {
		return _stMSupplementalDiscActualsLocalService.getStMSupplementalDiscActualsesCount();
	}

	/**
	* Updates the st m supplemental disc actuals in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stMSupplementalDiscActuals the st m supplemental disc actuals
	* @return the st m supplemental disc actuals that was updated
	*/
	@Override
	public com.stpl.app.model.StMSupplementalDiscActuals updateStMSupplementalDiscActuals(
		com.stpl.app.model.StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		return _stMSupplementalDiscActualsLocalService.updateStMSupplementalDiscActuals(stMSupplementalDiscActuals);
	}

	@Override
	public StMSupplementalDiscActualsLocalService getWrappedService() {
		return _stMSupplementalDiscActualsLocalService;
	}

	@Override
	public void setWrappedService(
		StMSupplementalDiscActualsLocalService stMSupplementalDiscActualsLocalService) {
		_stMSupplementalDiscActualsLocalService = stMSupplementalDiscActualsLocalService;
	}

	private StMSupplementalDiscActualsLocalService _stMSupplementalDiscActualsLocalService;
}