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
 * Provides the local service utility for ModuleProperties. This utility wraps
 * {@link com.stpl.app.service.impl.ModulePropertiesLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ModulePropertiesLocalService
 * @see com.stpl.app.service.base.ModulePropertiesLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ModulePropertiesLocalServiceImpl
 * @generated
 */
@ProviderType
public class ModulePropertiesLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ModulePropertiesLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the module properties to the database. Also notifies the appropriate model listeners.
	*
	* @param moduleProperties the module properties
	* @return the module properties that was added
	*/
	public static com.stpl.app.model.ModuleProperties addModuleProperties(
		com.stpl.app.model.ModuleProperties moduleProperties) {
		return getService().addModuleProperties(moduleProperties);
	}

	/**
	* Creates a new module properties with the primary key. Does not add the module properties to the database.
	*
	* @param modulePropertySid the primary key for the new module properties
	* @return the new module properties
	*/
	public static com.stpl.app.model.ModuleProperties createModuleProperties(
		int modulePropertySid) {
		return getService().createModuleProperties(modulePropertySid);
	}

	/**
	* Deletes the module properties with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param modulePropertySid the primary key of the module properties
	* @return the module properties that was removed
	* @throws PortalException if a module properties with the primary key could not be found
	*/
	public static com.stpl.app.model.ModuleProperties deleteModuleProperties(
		int modulePropertySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteModuleProperties(modulePropertySid);
	}

	/**
	* Deletes the module properties from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleProperties the module properties
	* @return the module properties that was removed
	*/
	public static com.stpl.app.model.ModuleProperties deleteModuleProperties(
		com.stpl.app.model.ModuleProperties moduleProperties) {
		return getService().deleteModuleProperties(moduleProperties);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.ModuleProperties fetchModuleProperties(
		int modulePropertySid) {
		return getService().fetchModuleProperties(modulePropertySid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the module properties with the primary key.
	*
	* @param modulePropertySid the primary key of the module properties
	* @return the module properties
	* @throws PortalException if a module properties with the primary key could not be found
	*/
	public static com.stpl.app.model.ModuleProperties getModuleProperties(
		int modulePropertySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getModuleProperties(modulePropertySid);
	}

	/**
	* Returns a range of all the module propertieses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module propertieses
	* @param end the upper bound of the range of module propertieses (not inclusive)
	* @return the range of module propertieses
	*/
	public static java.util.List<com.stpl.app.model.ModuleProperties> getModulePropertieses(
		int start, int end) {
		return getService().getModulePropertieses(start, end);
	}

	/**
	* Returns the number of module propertieses.
	*
	* @return the number of module propertieses
	*/
	public static int getModulePropertiesesCount() {
		return getService().getModulePropertiesesCount();
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
	* Updates the module properties in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param moduleProperties the module properties
	* @return the module properties that was updated
	*/
	public static com.stpl.app.model.ModuleProperties updateModuleProperties(
		com.stpl.app.model.ModuleProperties moduleProperties) {
		return getService().updateModuleProperties(moduleProperties);
	}

	public static ModulePropertiesLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ModulePropertiesLocalService, ModulePropertiesLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ModulePropertiesLocalService.class);
}