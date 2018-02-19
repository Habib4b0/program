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
 * Provides a wrapper for {@link HistWorkflowMasterLocalService}.
 *
 * @author
 * @see HistWorkflowMasterLocalService
 * @generated
 */
@ProviderType
public class HistWorkflowMasterLocalServiceWrapper
	implements HistWorkflowMasterLocalService,
		ServiceWrapper<HistWorkflowMasterLocalService> {
	public HistWorkflowMasterLocalServiceWrapper(
		HistWorkflowMasterLocalService histWorkflowMasterLocalService) {
		_histWorkflowMasterLocalService = histWorkflowMasterLocalService;
	}

	/**
	* Adds the hist workflow master to the database. Also notifies the appropriate model listeners.
	*
	* @param histWorkflowMaster the hist workflow master
	* @return the hist workflow master that was added
	*/
	@Override
	public com.stpl.app.model.HistWorkflowMaster addHistWorkflowMaster(
		com.stpl.app.model.HistWorkflowMaster histWorkflowMaster) {
		return _histWorkflowMasterLocalService.addHistWorkflowMaster(histWorkflowMaster);
	}

	/**
	* Creates a new hist workflow master with the primary key. Does not add the hist workflow master to the database.
	*
	* @param workflowMasterSid the primary key for the new hist workflow master
	* @return the new hist workflow master
	*/
	@Override
	public com.stpl.app.model.HistWorkflowMaster createHistWorkflowMaster(
		int workflowMasterSid) {
		return _histWorkflowMasterLocalService.createHistWorkflowMaster(workflowMasterSid);
	}

	/**
	* Deletes the hist workflow master from the database. Also notifies the appropriate model listeners.
	*
	* @param histWorkflowMaster the hist workflow master
	* @return the hist workflow master that was removed
	*/
	@Override
	public com.stpl.app.model.HistWorkflowMaster deleteHistWorkflowMaster(
		com.stpl.app.model.HistWorkflowMaster histWorkflowMaster) {
		return _histWorkflowMasterLocalService.deleteHistWorkflowMaster(histWorkflowMaster);
	}

	/**
	* Deletes the hist workflow master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowMasterSid the primary key of the hist workflow master
	* @return the hist workflow master that was removed
	* @throws PortalException if a hist workflow master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HistWorkflowMaster deleteHistWorkflowMaster(
		int workflowMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histWorkflowMasterLocalService.deleteHistWorkflowMaster(workflowMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histWorkflowMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _histWorkflowMasterLocalService.dynamicQuery();
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
		return _histWorkflowMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _histWorkflowMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _histWorkflowMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _histWorkflowMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _histWorkflowMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.HistWorkflowMaster fetchHistWorkflowMaster(
		int workflowMasterSid) {
		return _histWorkflowMasterLocalService.fetchHistWorkflowMaster(workflowMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _histWorkflowMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the hist workflow master with the primary key.
	*
	* @param workflowMasterSid the primary key of the hist workflow master
	* @return the hist workflow master
	* @throws PortalException if a hist workflow master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HistWorkflowMaster getHistWorkflowMaster(
		int workflowMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histWorkflowMasterLocalService.getHistWorkflowMaster(workflowMasterSid);
	}

	/**
	* Returns a range of all the hist workflow masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist workflow masters
	* @param end the upper bound of the range of hist workflow masters (not inclusive)
	* @return the range of hist workflow masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.HistWorkflowMaster> getHistWorkflowMasters(
		int start, int end) {
		return _histWorkflowMasterLocalService.getHistWorkflowMasters(start, end);
	}

	/**
	* Returns the number of hist workflow masters.
	*
	* @return the number of hist workflow masters
	*/
	@Override
	public int getHistWorkflowMastersCount() {
		return _histWorkflowMasterLocalService.getHistWorkflowMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _histWorkflowMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _histWorkflowMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histWorkflowMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the hist workflow master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param histWorkflowMaster the hist workflow master
	* @return the hist workflow master that was updated
	*/
	@Override
	public com.stpl.app.model.HistWorkflowMaster updateHistWorkflowMaster(
		com.stpl.app.model.HistWorkflowMaster histWorkflowMaster) {
		return _histWorkflowMasterLocalService.updateHistWorkflowMaster(histWorkflowMaster);
	}

	@Override
	public HistWorkflowMasterLocalService getWrappedService() {
		return _histWorkflowMasterLocalService;
	}

	@Override
	public void setWrappedService(
		HistWorkflowMasterLocalService histWorkflowMasterLocalService) {
		_histWorkflowMasterLocalService = histWorkflowMasterLocalService;
	}

	private HistWorkflowMasterLocalService _histWorkflowMasterLocalService;
}