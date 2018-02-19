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
 * Provides a wrapper for {@link IvldDemandActualLocalService}.
 *
 * @author
 * @see IvldDemandActualLocalService
 * @generated
 */
@ProviderType
public class IvldDemandActualLocalServiceWrapper
	implements IvldDemandActualLocalService,
		ServiceWrapper<IvldDemandActualLocalService> {
	public IvldDemandActualLocalServiceWrapper(
		IvldDemandActualLocalService ivldDemandActualLocalService) {
		_ivldDemandActualLocalService = ivldDemandActualLocalService;
	}

	/**
	* Adds the ivld demand actual to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldDemandActual the ivld demand actual
	* @return the ivld demand actual that was added
	*/
	@Override
	public com.stpl.app.model.IvldDemandActual addIvldDemandActual(
		com.stpl.app.model.IvldDemandActual ivldDemandActual) {
		return _ivldDemandActualLocalService.addIvldDemandActual(ivldDemandActual);
	}

	/**
	* Creates a new ivld demand actual with the primary key. Does not add the ivld demand actual to the database.
	*
	* @param ivldDemandActualSid the primary key for the new ivld demand actual
	* @return the new ivld demand actual
	*/
	@Override
	public com.stpl.app.model.IvldDemandActual createIvldDemandActual(
		int ivldDemandActualSid) {
		return _ivldDemandActualLocalService.createIvldDemandActual(ivldDemandActualSid);
	}

	/**
	* Deletes the ivld demand actual with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldDemandActualSid the primary key of the ivld demand actual
	* @return the ivld demand actual that was removed
	* @throws PortalException if a ivld demand actual with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldDemandActual deleteIvldDemandActual(
		int ivldDemandActualSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldDemandActualLocalService.deleteIvldDemandActual(ivldDemandActualSid);
	}

	/**
	* Deletes the ivld demand actual from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldDemandActual the ivld demand actual
	* @return the ivld demand actual that was removed
	*/
	@Override
	public com.stpl.app.model.IvldDemandActual deleteIvldDemandActual(
		com.stpl.app.model.IvldDemandActual ivldDemandActual) {
		return _ivldDemandActualLocalService.deleteIvldDemandActual(ivldDemandActual);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldDemandActualLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldDemandActualLocalService.dynamicQuery();
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
		return _ivldDemandActualLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldDemandActualLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldDemandActualLocalService.dynamicQuery(dynamicQuery, start,
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
		return _ivldDemandActualLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldDemandActualLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.IvldDemandActual fetchIvldDemandActual(
		int ivldDemandActualSid) {
		return _ivldDemandActualLocalService.fetchIvldDemandActual(ivldDemandActualSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldDemandActualLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldDemandActualLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld demand actual with the primary key.
	*
	* @param ivldDemandActualSid the primary key of the ivld demand actual
	* @return the ivld demand actual
	* @throws PortalException if a ivld demand actual with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldDemandActual getIvldDemandActual(
		int ivldDemandActualSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldDemandActualLocalService.getIvldDemandActual(ivldDemandActualSid);
	}

	/**
	* Returns a range of all the ivld demand actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld demand actuals
	* @param end the upper bound of the range of ivld demand actuals (not inclusive)
	* @return the range of ivld demand actuals
	*/
	@Override
	public java.util.List<com.stpl.app.model.IvldDemandActual> getIvldDemandActuals(
		int start, int end) {
		return _ivldDemandActualLocalService.getIvldDemandActuals(start, end);
	}

	/**
	* Returns the number of ivld demand actuals.
	*
	* @return the number of ivld demand actuals
	*/
	@Override
	public int getIvldDemandActualsCount() {
		return _ivldDemandActualLocalService.getIvldDemandActualsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldDemandActualLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldDemandActualLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld demand actual in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldDemandActual the ivld demand actual
	* @return the ivld demand actual that was updated
	*/
	@Override
	public com.stpl.app.model.IvldDemandActual updateIvldDemandActual(
		com.stpl.app.model.IvldDemandActual ivldDemandActual) {
		return _ivldDemandActualLocalService.updateIvldDemandActual(ivldDemandActual);
	}

	@Override
	public IvldDemandActualLocalService getWrappedService() {
		return _ivldDemandActualLocalService;
	}

	@Override
	public void setWrappedService(
		IvldDemandActualLocalService ivldDemandActualLocalService) {
		_ivldDemandActualLocalService = ivldDemandActualLocalService;
	}

	private IvldDemandActualLocalService _ivldDemandActualLocalService;
}