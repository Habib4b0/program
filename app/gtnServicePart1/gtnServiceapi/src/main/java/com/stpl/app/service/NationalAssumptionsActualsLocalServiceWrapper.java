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
 * Provides a wrapper for {@link NationalAssumptionsActualsLocalService}.
 *
 * @author
 * @see NationalAssumptionsActualsLocalService
 * @generated
 */
@ProviderType
public class NationalAssumptionsActualsLocalServiceWrapper
	implements NationalAssumptionsActualsLocalService,
		ServiceWrapper<NationalAssumptionsActualsLocalService> {
	public NationalAssumptionsActualsLocalServiceWrapper(
		NationalAssumptionsActualsLocalService nationalAssumptionsActualsLocalService) {
		_nationalAssumptionsActualsLocalService = nationalAssumptionsActualsLocalService;
	}

	/**
	* Adds the national assumptions actuals to the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActuals the national assumptions actuals
	* @return the national assumptions actuals that was added
	*/
	@Override
	public com.stpl.app.model.NationalAssumptionsActuals addNationalAssumptionsActuals(
		com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals) {
		return _nationalAssumptionsActualsLocalService.addNationalAssumptionsActuals(nationalAssumptionsActuals);
	}

	/**
	* Creates a new national assumptions actuals with the primary key. Does not add the national assumptions actuals to the database.
	*
	* @param nationalAssumptionsActualsPK the primary key for the new national assumptions actuals
	* @return the new national assumptions actuals
	*/
	@Override
	public com.stpl.app.model.NationalAssumptionsActuals createNationalAssumptionsActuals(
		com.stpl.app.service.persistence.NationalAssumptionsActualsPK nationalAssumptionsActualsPK) {
		return _nationalAssumptionsActualsLocalService.createNationalAssumptionsActuals(nationalAssumptionsActualsPK);
	}

	/**
	* Deletes the national assumptions actuals from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActuals the national assumptions actuals
	* @return the national assumptions actuals that was removed
	*/
	@Override
	public com.stpl.app.model.NationalAssumptionsActuals deleteNationalAssumptionsActuals(
		com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals) {
		return _nationalAssumptionsActualsLocalService.deleteNationalAssumptionsActuals(nationalAssumptionsActuals);
	}

	/**
	* Deletes the national assumptions actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
	* @return the national assumptions actuals that was removed
	* @throws PortalException if a national assumptions actuals with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NationalAssumptionsActuals deleteNationalAssumptionsActuals(
		com.stpl.app.service.persistence.NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nationalAssumptionsActualsLocalService.deleteNationalAssumptionsActuals(nationalAssumptionsActualsPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nationalAssumptionsActualsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _nationalAssumptionsActualsLocalService.dynamicQuery();
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
		return _nationalAssumptionsActualsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nationalAssumptionsActualsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nationalAssumptionsActualsLocalService.dynamicQuery(dynamicQuery,
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
		return _nationalAssumptionsActualsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _nationalAssumptionsActualsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.NationalAssumptionsActuals fetchNationalAssumptionsActuals(
		com.stpl.app.service.persistence.NationalAssumptionsActualsPK nationalAssumptionsActualsPK) {
		return _nationalAssumptionsActualsLocalService.fetchNationalAssumptionsActuals(nationalAssumptionsActualsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _nationalAssumptionsActualsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _nationalAssumptionsActualsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the national assumptions actuals with the primary key.
	*
	* @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
	* @return the national assumptions actuals
	* @throws PortalException if a national assumptions actuals with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NationalAssumptionsActuals getNationalAssumptionsActuals(
		com.stpl.app.service.persistence.NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nationalAssumptionsActualsLocalService.getNationalAssumptionsActuals(nationalAssumptionsActualsPK);
	}

	/**
	* Returns a range of all the national assumptions actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptions actualses
	* @param end the upper bound of the range of national assumptions actualses (not inclusive)
	* @return the range of national assumptions actualses
	*/
	@Override
	public java.util.List<com.stpl.app.model.NationalAssumptionsActuals> getNationalAssumptionsActualses(
		int start, int end) {
		return _nationalAssumptionsActualsLocalService.getNationalAssumptionsActualses(start,
			end);
	}

	/**
	* Returns the number of national assumptions actualses.
	*
	* @return the number of national assumptions actualses
	*/
	@Override
	public int getNationalAssumptionsActualsesCount() {
		return _nationalAssumptionsActualsLocalService.getNationalAssumptionsActualsesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _nationalAssumptionsActualsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nationalAssumptionsActualsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the national assumptions actuals in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActuals the national assumptions actuals
	* @return the national assumptions actuals that was updated
	*/
	@Override
	public com.stpl.app.model.NationalAssumptionsActuals updateNationalAssumptionsActuals(
		com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals) {
		return _nationalAssumptionsActualsLocalService.updateNationalAssumptionsActuals(nationalAssumptionsActuals);
	}

	@Override
	public NationalAssumptionsActualsLocalService getWrappedService() {
		return _nationalAssumptionsActualsLocalService;
	}

	@Override
	public void setWrappedService(
		NationalAssumptionsActualsLocalService nationalAssumptionsActualsLocalService) {
		_nationalAssumptionsActualsLocalService = nationalAssumptionsActualsLocalService;
	}

	private NationalAssumptionsActualsLocalService _nationalAssumptionsActualsLocalService;
}