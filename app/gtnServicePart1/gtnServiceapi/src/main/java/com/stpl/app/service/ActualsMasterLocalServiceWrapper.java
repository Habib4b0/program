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
 * Provides a wrapper for {@link ActualsMasterLocalService}.
 *
 * @author
 * @see ActualsMasterLocalService
 * @generated
 */
@ProviderType
public class ActualsMasterLocalServiceWrapper
	implements ActualsMasterLocalService,
		ServiceWrapper<ActualsMasterLocalService> {
	public ActualsMasterLocalServiceWrapper(
		ActualsMasterLocalService actualsMasterLocalService) {
		_actualsMasterLocalService = actualsMasterLocalService;
	}

	/**
	* Adds the actuals master to the database. Also notifies the appropriate model listeners.
	*
	* @param actualsMaster the actuals master
	* @return the actuals master that was added
	*/
	@Override
	public com.stpl.app.model.ActualsMaster addActualsMaster(
		com.stpl.app.model.ActualsMaster actualsMaster) {
		return _actualsMasterLocalService.addActualsMaster(actualsMaster);
	}

	/**
	* Creates a new actuals master with the primary key. Does not add the actuals master to the database.
	*
	* @param actualsMasterSid the primary key for the new actuals master
	* @return the new actuals master
	*/
	@Override
	public com.stpl.app.model.ActualsMaster createActualsMaster(
		int actualsMasterSid) {
		return _actualsMasterLocalService.createActualsMaster(actualsMasterSid);
	}

	/**
	* Deletes the actuals master from the database. Also notifies the appropriate model listeners.
	*
	* @param actualsMaster the actuals master
	* @return the actuals master that was removed
	*/
	@Override
	public com.stpl.app.model.ActualsMaster deleteActualsMaster(
		com.stpl.app.model.ActualsMaster actualsMaster) {
		return _actualsMasterLocalService.deleteActualsMaster(actualsMaster);
	}

	/**
	* Deletes the actuals master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param actualsMasterSid the primary key of the actuals master
	* @return the actuals master that was removed
	* @throws PortalException if a actuals master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ActualsMaster deleteActualsMaster(
		int actualsMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _actualsMasterLocalService.deleteActualsMaster(actualsMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _actualsMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _actualsMasterLocalService.dynamicQuery();
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
		return _actualsMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _actualsMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _actualsMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _actualsMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _actualsMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ActualsMaster fetchActualsMaster(
		int actualsMasterSid) {
		return _actualsMasterLocalService.fetchActualsMaster(actualsMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _actualsMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the actuals master with the primary key.
	*
	* @param actualsMasterSid the primary key of the actuals master
	* @return the actuals master
	* @throws PortalException if a actuals master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ActualsMaster getActualsMaster(
		int actualsMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _actualsMasterLocalService.getActualsMaster(actualsMasterSid);
	}

	/**
	* Returns a range of all the actuals masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of actuals masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.ActualsMaster> getActualsMasters(
		int start, int end) {
		return _actualsMasterLocalService.getActualsMasters(start, end);
	}

	/**
	* Returns the number of actuals masters.
	*
	* @return the number of actuals masters
	*/
	@Override
	public int getActualsMastersCount() {
		return _actualsMasterLocalService.getActualsMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _actualsMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _actualsMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _actualsMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the actuals master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param actualsMaster the actuals master
	* @return the actuals master that was updated
	*/
	@Override
	public com.stpl.app.model.ActualsMaster updateActualsMaster(
		com.stpl.app.model.ActualsMaster actualsMaster) {
		return _actualsMasterLocalService.updateActualsMaster(actualsMaster);
	}

	@Override
	public ActualsMasterLocalService getWrappedService() {
		return _actualsMasterLocalService;
	}

	@Override
	public void setWrappedService(
		ActualsMasterLocalService actualsMasterLocalService) {
		_actualsMasterLocalService = actualsMasterLocalService;
	}

	private ActualsMasterLocalService _actualsMasterLocalService;
}