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
 * Provides a wrapper for {@link IfpModelLocalService}.
 *
 * @author
 * @see IfpModelLocalService
 * @generated
 */
@ProviderType
public class IfpModelLocalServiceWrapper implements IfpModelLocalService,
	ServiceWrapper<IfpModelLocalService> {
	public IfpModelLocalServiceWrapper(
		IfpModelLocalService ifpModelLocalService) {
		_ifpModelLocalService = ifpModelLocalService;
	}

	/**
	* Adds the ifp model to the database. Also notifies the appropriate model listeners.
	*
	* @param ifpModel the ifp model
	* @return the ifp model that was added
	*/
	@Override
	public com.stpl.app.model.IfpModel addIfpModel(
		com.stpl.app.model.IfpModel ifpModel) {
		return _ifpModelLocalService.addIfpModel(ifpModel);
	}

	/**
	* Creates a new ifp model with the primary key. Does not add the ifp model to the database.
	*
	* @param ifpModelSid the primary key for the new ifp model
	* @return the new ifp model
	*/
	@Override
	public com.stpl.app.model.IfpModel createIfpModel(int ifpModelSid) {
		return _ifpModelLocalService.createIfpModel(ifpModelSid);
	}

	/**
	* Deletes the ifp model from the database. Also notifies the appropriate model listeners.
	*
	* @param ifpModel the ifp model
	* @return the ifp model that was removed
	*/
	@Override
	public com.stpl.app.model.IfpModel deleteIfpModel(
		com.stpl.app.model.IfpModel ifpModel) {
		return _ifpModelLocalService.deleteIfpModel(ifpModel);
	}

	/**
	* Deletes the ifp model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ifpModelSid the primary key of the ifp model
	* @return the ifp model that was removed
	* @throws PortalException if a ifp model with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IfpModel deleteIfpModel(int ifpModelSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ifpModelLocalService.deleteIfpModel(ifpModelSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ifpModelLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ifpModelLocalService.dynamicQuery();
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
		return _ifpModelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ifpModelLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ifpModelLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _ifpModelLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ifpModelLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.stpl.app.model.IfpModel fetchIfpModel(int ifpModelSid) {
		return _ifpModelLocalService.fetchIfpModel(ifpModelSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ifpModelLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the ifp model with the primary key.
	*
	* @param ifpModelSid the primary key of the ifp model
	* @return the ifp model
	* @throws PortalException if a ifp model with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IfpModel getIfpModel(int ifpModelSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ifpModelLocalService.getIfpModel(ifpModelSid);
	}

	/**
	* Returns a range of all the ifp models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of ifp models
	*/
	@Override
	public java.util.List<com.stpl.app.model.IfpModel> getIfpModels(int start,
		int end) {
		return _ifpModelLocalService.getIfpModels(start, end);
	}

	/**
	* Returns the number of ifp models.
	*
	* @return the number of ifp models
	*/
	@Override
	public int getIfpModelsCount() {
		return _ifpModelLocalService.getIfpModelsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ifpModelLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ifpModelLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ifpModelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ifp model in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ifpModel the ifp model
	* @return the ifp model that was updated
	*/
	@Override
	public com.stpl.app.model.IfpModel updateIfpModel(
		com.stpl.app.model.IfpModel ifpModel) {
		return _ifpModelLocalService.updateIfpModel(ifpModel);
	}

	@Override
	public IfpModelLocalService getWrappedService() {
		return _ifpModelLocalService;
	}

	@Override
	public void setWrappedService(IfpModelLocalService ifpModelLocalService) {
		_ifpModelLocalService = ifpModelLocalService;
	}

	private IfpModelLocalService _ifpModelLocalService;
}