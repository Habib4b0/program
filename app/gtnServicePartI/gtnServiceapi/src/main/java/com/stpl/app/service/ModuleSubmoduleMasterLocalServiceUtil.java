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
 * Provides the local service utility for ModuleSubmoduleMaster. This utility wraps
 * {@link com.stpl.app.service.impl.ModuleSubmoduleMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ModuleSubmoduleMasterLocalService
 * @see com.stpl.app.service.base.ModuleSubmoduleMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ModuleSubmoduleMasterLocalServiceImpl
 * @generated
 */
@ProviderType
public class ModuleSubmoduleMasterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ModuleSubmoduleMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the module submodule master to the database. Also notifies the appropriate model listeners.
	*
	* @param moduleSubmoduleMaster the module submodule master
	* @return the module submodule master that was added
	*/
	public static com.stpl.app.model.ModuleSubmoduleMaster addModuleSubmoduleMaster(
		com.stpl.app.model.ModuleSubmoduleMaster moduleSubmoduleMaster) {
		return getService().addModuleSubmoduleMaster(moduleSubmoduleMaster);
	}

	/**
	* Creates a new module submodule master with the primary key. Does not add the module submodule master to the database.
	*
	* @param moduleSubmoduleSid the primary key for the new module submodule master
	* @return the new module submodule master
	*/
	public static com.stpl.app.model.ModuleSubmoduleMaster createModuleSubmoduleMaster(
		int moduleSubmoduleSid) {
		return getService().createModuleSubmoduleMaster(moduleSubmoduleSid);
	}

	/**
	* Deletes the module submodule master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleSubmoduleSid the primary key of the module submodule master
	* @return the module submodule master that was removed
	* @throws PortalException if a module submodule master with the primary key could not be found
	*/
	public static com.stpl.app.model.ModuleSubmoduleMaster deleteModuleSubmoduleMaster(
		int moduleSubmoduleSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteModuleSubmoduleMaster(moduleSubmoduleSid);
	}

	/**
	* Deletes the module submodule master from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleSubmoduleMaster the module submodule master
	* @return the module submodule master that was removed
	*/
	public static com.stpl.app.model.ModuleSubmoduleMaster deleteModuleSubmoduleMaster(
		com.stpl.app.model.ModuleSubmoduleMaster moduleSubmoduleMaster) {
		return getService().deleteModuleSubmoduleMaster(moduleSubmoduleMaster);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.ModuleSubmoduleMaster fetchModuleSubmoduleMaster(
		int moduleSubmoduleSid) {
		return getService().fetchModuleSubmoduleMaster(moduleSubmoduleSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the module submodule master with the primary key.
	*
	* @param moduleSubmoduleSid the primary key of the module submodule master
	* @return the module submodule master
	* @throws PortalException if a module submodule master with the primary key could not be found
	*/
	public static com.stpl.app.model.ModuleSubmoduleMaster getModuleSubmoduleMaster(
		int moduleSubmoduleSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getModuleSubmoduleMaster(moduleSubmoduleSid);
	}

	/**
	* Returns a range of all the module submodule masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module submodule masters
	* @param end the upper bound of the range of module submodule masters (not inclusive)
	* @return the range of module submodule masters
	*/
	public static java.util.List<com.stpl.app.model.ModuleSubmoduleMaster> getModuleSubmoduleMasters(
		int start, int end) {
		return getService().getModuleSubmoduleMasters(start, end);
	}

	/**
	* Returns the number of module submodule masters.
	*
	* @return the number of module submodule masters
	*/
	public static int getModuleSubmoduleMastersCount() {
		return getService().getModuleSubmoduleMastersCount();
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
	* Updates the module submodule master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param moduleSubmoduleMaster the module submodule master
	* @return the module submodule master that was updated
	*/
	public static com.stpl.app.model.ModuleSubmoduleMaster updateModuleSubmoduleMaster(
		com.stpl.app.model.ModuleSubmoduleMaster moduleSubmoduleMaster) {
		return getService().updateModuleSubmoduleMaster(moduleSubmoduleMaster);
	}

	public static ModuleSubmoduleMasterLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ModuleSubmoduleMasterLocalService, ModuleSubmoduleMasterLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ModuleSubmoduleMasterLocalService.class);
}