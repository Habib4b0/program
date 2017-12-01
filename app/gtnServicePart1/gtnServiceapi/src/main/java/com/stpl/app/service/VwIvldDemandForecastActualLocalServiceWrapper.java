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
 * Provides a wrapper for {@link VwIvldDemandForecastActualLocalService}.
 *
 * @author
 * @see VwIvldDemandForecastActualLocalService
 * @generated
 */
@ProviderType
public class VwIvldDemandForecastActualLocalServiceWrapper
	implements VwIvldDemandForecastActualLocalService,
		ServiceWrapper<VwIvldDemandForecastActualLocalService> {
	public VwIvldDemandForecastActualLocalServiceWrapper(
		VwIvldDemandForecastActualLocalService vwIvldDemandForecastActualLocalService) {
		_vwIvldDemandForecastActualLocalService = vwIvldDemandForecastActualLocalService;
	}

	/**
	* Adds the vw ivld demand forecast actual to the database. Also notifies the appropriate model listeners.
	*
	* @param vwIvldDemandForecastActual the vw ivld demand forecast actual
	* @return the vw ivld demand forecast actual that was added
	*/
	@Override
	public com.stpl.app.model.VwIvldDemandForecastActual addVwIvldDemandForecastActual(
		com.stpl.app.model.VwIvldDemandForecastActual vwIvldDemandForecastActual) {
		return _vwIvldDemandForecastActualLocalService.addVwIvldDemandForecastActual(vwIvldDemandForecastActual);
	}

	/**
	* Creates a new vw ivld demand forecast actual with the primary key. Does not add the vw ivld demand forecast actual to the database.
	*
	* @param ivldDemandActualForecastSid the primary key for the new vw ivld demand forecast actual
	* @return the new vw ivld demand forecast actual
	*/
	@Override
	public com.stpl.app.model.VwIvldDemandForecastActual createVwIvldDemandForecastActual(
		int ivldDemandActualForecastSid) {
		return _vwIvldDemandForecastActualLocalService.createVwIvldDemandForecastActual(ivldDemandActualForecastSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwIvldDemandForecastActualLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the vw ivld demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
	* @return the vw ivld demand forecast actual that was removed
	* @throws PortalException if a vw ivld demand forecast actual with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.VwIvldDemandForecastActual deleteVwIvldDemandForecastActual(
		int ivldDemandActualForecastSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwIvldDemandForecastActualLocalService.deleteVwIvldDemandForecastActual(ivldDemandActualForecastSid);
	}

	/**
	* Deletes the vw ivld demand forecast actual from the database. Also notifies the appropriate model listeners.
	*
	* @param vwIvldDemandForecastActual the vw ivld demand forecast actual
	* @return the vw ivld demand forecast actual that was removed
	*/
	@Override
	public com.stpl.app.model.VwIvldDemandForecastActual deleteVwIvldDemandForecastActual(
		com.stpl.app.model.VwIvldDemandForecastActual vwIvldDemandForecastActual) {
		return _vwIvldDemandForecastActualLocalService.deleteVwIvldDemandForecastActual(vwIvldDemandForecastActual);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _vwIvldDemandForecastActualLocalService.dynamicQuery();
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
		return _vwIvldDemandForecastActualLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwIvldDemandForecastActualLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwIvldDemandForecastActualLocalService.dynamicQuery(dynamicQuery,
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
		return _vwIvldDemandForecastActualLocalService.dynamicQueryCount(dynamicQuery);
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
		return _vwIvldDemandForecastActualLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.VwIvldDemandForecastActual fetchVwIvldDemandForecastActual(
		int ivldDemandActualForecastSid) {
		return _vwIvldDemandForecastActualLocalService.fetchVwIvldDemandForecastActual(ivldDemandActualForecastSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _vwIvldDemandForecastActualLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _vwIvldDemandForecastActualLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _vwIvldDemandForecastActualLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwIvldDemandForecastActualLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the vw ivld demand forecast actual with the primary key.
	*
	* @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
	* @return the vw ivld demand forecast actual
	* @throws PortalException if a vw ivld demand forecast actual with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.VwIvldDemandForecastActual getVwIvldDemandForecastActual(
		int ivldDemandActualForecastSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwIvldDemandForecastActualLocalService.getVwIvldDemandForecastActual(ivldDemandActualForecastSid);
	}

	/**
	* Returns a range of all the vw ivld demand forecast actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw ivld demand forecast actuals
	* @param end the upper bound of the range of vw ivld demand forecast actuals (not inclusive)
	* @return the range of vw ivld demand forecast actuals
	*/
	@Override
	public java.util.List<com.stpl.app.model.VwIvldDemandForecastActual> getVwIvldDemandForecastActuals(
		int start, int end) {
		return _vwIvldDemandForecastActualLocalService.getVwIvldDemandForecastActuals(start,
			end);
	}

	/**
	* Returns the number of vw ivld demand forecast actuals.
	*
	* @return the number of vw ivld demand forecast actuals
	*/
	@Override
	public int getVwIvldDemandForecastActualsCount() {
		return _vwIvldDemandForecastActualLocalService.getVwIvldDemandForecastActualsCount();
	}

	/**
	* Updates the vw ivld demand forecast actual in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param vwIvldDemandForecastActual the vw ivld demand forecast actual
	* @return the vw ivld demand forecast actual that was updated
	*/
	@Override
	public com.stpl.app.model.VwIvldDemandForecastActual updateVwIvldDemandForecastActual(
		com.stpl.app.model.VwIvldDemandForecastActual vwIvldDemandForecastActual) {
		return _vwIvldDemandForecastActualLocalService.updateVwIvldDemandForecastActual(vwIvldDemandForecastActual);
	}

	@Override
	public VwIvldDemandForecastActualLocalService getWrappedService() {
		return _vwIvldDemandForecastActualLocalService;
	}

	@Override
	public void setWrappedService(
		VwIvldDemandForecastActualLocalService vwIvldDemandForecastActualLocalService) {
		_vwIvldDemandForecastActualLocalService = vwIvldDemandForecastActualLocalService;
	}

	private VwIvldDemandForecastActualLocalService _vwIvldDemandForecastActualLocalService;
}