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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for WfMailConfig. This utility wraps
 * {@link com.stpl.app.service.impl.WfMailConfigLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see WfMailConfigLocalService
 * @see com.stpl.app.service.base.WfMailConfigLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.WfMailConfigLocalServiceImpl
 * @generated
 */
@ProviderType
public class WfMailConfigLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.WfMailConfigLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the wf mail config to the database. Also notifies the appropriate model listeners.
	*
	* @param wfMailConfig the wf mail config
	* @return the wf mail config that was added
	*/
	public static com.stpl.app.model.WfMailConfig addWfMailConfig(
		com.stpl.app.model.WfMailConfig wfMailConfig) {
		return getService().addWfMailConfig(wfMailConfig);
	}

	/**
	* Creates a new wf mail config with the primary key. Does not add the wf mail config to the database.
	*
	* @param wfMailConfigSid the primary key for the new wf mail config
	* @return the new wf mail config
	*/
	public static com.stpl.app.model.WfMailConfig createWfMailConfig(
		int wfMailConfigSid) {
		return getService().createWfMailConfig(wfMailConfigSid);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the wf mail config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wfMailConfigSid the primary key of the wf mail config
	* @return the wf mail config that was removed
	* @throws PortalException if a wf mail config with the primary key could not be found
	*/
	public static com.stpl.app.model.WfMailConfig deleteWfMailConfig(
		int wfMailConfigSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWfMailConfig(wfMailConfigSid);
	}

	/**
	* Deletes the wf mail config from the database. Also notifies the appropriate model listeners.
	*
	* @param wfMailConfig the wf mail config
	* @return the wf mail config that was removed
	*/
	public static com.stpl.app.model.WfMailConfig deleteWfMailConfig(
		com.stpl.app.model.WfMailConfig wfMailConfig) {
		return getService().deleteWfMailConfig(wfMailConfig);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.stpl.app.model.WfMailConfig fetchWfMailConfig(
		int wfMailConfigSid) {
		return getService().fetchWfMailConfig(wfMailConfigSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the wf mail config with the primary key.
	*
	* @param wfMailConfigSid the primary key of the wf mail config
	* @return the wf mail config
	* @throws PortalException if a wf mail config with the primary key could not be found
	*/
	public static com.stpl.app.model.WfMailConfig getWfMailConfig(
		int wfMailConfigSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWfMailConfig(wfMailConfigSid);
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
	public static java.util.List<com.stpl.app.model.WfMailConfig> getWfMailConfigs(
		int start, int end) {
		return getService().getWfMailConfigs(start, end);
	}

	/**
	* Returns the number of wf mail configs.
	*
	* @return the number of wf mail configs
	*/
	public static int getWfMailConfigsCount() {
		return getService().getWfMailConfigsCount();
	}

	/**
	* Updates the wf mail config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wfMailConfig the wf mail config
	* @return the wf mail config that was updated
	*/
	public static com.stpl.app.model.WfMailConfig updateWfMailConfig(
		com.stpl.app.model.WfMailConfig wfMailConfig) {
		return getService().updateWfMailConfig(wfMailConfig);
	}

	public static WfMailConfigLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WfMailConfigLocalService, WfMailConfigLocalService> _serviceTracker =
		ServiceTrackerFactory.open(WfMailConfigLocalService.class);
}