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
 * Provides a wrapper for {@link WfMailConfigLocalService}.
 *
 * @author
 * @see WfMailConfigLocalService
 * @generated
 */
@ProviderType
public class WfMailConfigLocalServiceWrapper implements WfMailConfigLocalService,
	ServiceWrapper<WfMailConfigLocalService> {
	public WfMailConfigLocalServiceWrapper(
		WfMailConfigLocalService wfMailConfigLocalService) {
		_wfMailConfigLocalService = wfMailConfigLocalService;
	}

	/**
	* Adds the wf mail config to the database. Also notifies the appropriate model listeners.
	*
	* @param wfMailConfig the wf mail config
	* @return the wf mail config that was added
	*/
	@Override
	public com.stpl.app.model.WfMailConfig addWfMailConfig(
		com.stpl.app.model.WfMailConfig wfMailConfig) {
		return _wfMailConfigLocalService.addWfMailConfig(wfMailConfig);
	}

	/**
	* Creates a new wf mail config with the primary key. Does not add the wf mail config to the database.
	*
	* @param wfMailConfigSid the primary key for the new wf mail config
	* @return the new wf mail config
	*/
	@Override
	public com.stpl.app.model.WfMailConfig createWfMailConfig(
		int wfMailConfigSid) {
		return _wfMailConfigLocalService.createWfMailConfig(wfMailConfigSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wfMailConfigLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the wf mail config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wfMailConfigSid the primary key of the wf mail config
	* @return the wf mail config that was removed
	* @throws PortalException if a wf mail config with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.WfMailConfig deleteWfMailConfig(
		int wfMailConfigSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wfMailConfigLocalService.deleteWfMailConfig(wfMailConfigSid);
	}

	/**
	* Deletes the wf mail config from the database. Also notifies the appropriate model listeners.
	*
	* @param wfMailConfig the wf mail config
	* @return the wf mail config that was removed
	*/
	@Override
	public com.stpl.app.model.WfMailConfig deleteWfMailConfig(
		com.stpl.app.model.WfMailConfig wfMailConfig) {
		return _wfMailConfigLocalService.deleteWfMailConfig(wfMailConfig);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _wfMailConfigLocalService.dynamicQuery();
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
		return _wfMailConfigLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _wfMailConfigLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _wfMailConfigLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _wfMailConfigLocalService.dynamicQueryCount(dynamicQuery);
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
		return _wfMailConfigLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.WfMailConfig fetchWfMailConfig(
		int wfMailConfigSid) {
		return _wfMailConfigLocalService.fetchWfMailConfig(wfMailConfigSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _wfMailConfigLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _wfMailConfigLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _wfMailConfigLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wfMailConfigLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the wf mail config with the primary key.
	*
	* @param wfMailConfigSid the primary key of the wf mail config
	* @return the wf mail config
	* @throws PortalException if a wf mail config with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.WfMailConfig getWfMailConfig(int wfMailConfigSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wfMailConfigLocalService.getWfMailConfig(wfMailConfigSid);
	}

	/**
	* Returns a range of all the wf mail configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of wf mail configs
	* @param end the upper bound of the range of wf mail configs (not inclusive)
	* @return the range of wf mail configs
	*/
	@Override
	public java.util.List<com.stpl.app.model.WfMailConfig> getWfMailConfigs(
		int start, int end) {
		return _wfMailConfigLocalService.getWfMailConfigs(start, end);
	}

	/**
	* Returns the number of wf mail configs.
	*
	* @return the number of wf mail configs
	*/
	@Override
	public int getWfMailConfigsCount() {
		return _wfMailConfigLocalService.getWfMailConfigsCount();
	}

	/**
	* Updates the wf mail config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wfMailConfig the wf mail config
	* @return the wf mail config that was updated
	*/
	@Override
	public com.stpl.app.model.WfMailConfig updateWfMailConfig(
		com.stpl.app.model.WfMailConfig wfMailConfig) {
		return _wfMailConfigLocalService.updateWfMailConfig(wfMailConfig);
	}

	@Override
	public WfMailConfigLocalService getWrappedService() {
		return _wfMailConfigLocalService;
	}

	@Override
	public void setWrappedService(
		WfMailConfigLocalService wfMailConfigLocalService) {
		_wfMailConfigLocalService = wfMailConfigLocalService;
	}

	private WfMailConfigLocalService _wfMailConfigLocalService;
}