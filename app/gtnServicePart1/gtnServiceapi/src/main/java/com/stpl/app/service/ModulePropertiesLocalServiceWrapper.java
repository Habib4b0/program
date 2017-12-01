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
 * Provides a wrapper for {@link ModulePropertiesLocalService}.
 *
 * @author
 * @see ModulePropertiesLocalService
 * @generated
 */
@ProviderType
public class ModulePropertiesLocalServiceWrapper
	implements ModulePropertiesLocalService,
		ServiceWrapper<ModulePropertiesLocalService> {
	public ModulePropertiesLocalServiceWrapper(
		ModulePropertiesLocalService modulePropertiesLocalService) {
		_modulePropertiesLocalService = modulePropertiesLocalService;
	}

	/**
	* Adds the module properties to the database. Also notifies the appropriate model listeners.
	*
	* @param moduleProperties the module properties
	* @return the module properties that was added
	*/
	@Override
	public com.stpl.app.model.ModuleProperties addModuleProperties(
		com.stpl.app.model.ModuleProperties moduleProperties) {
		return _modulePropertiesLocalService.addModuleProperties(moduleProperties);
	}

	/**
	* Creates a new module properties with the primary key. Does not add the module properties to the database.
	*
	* @param modulePropertySid the primary key for the new module properties
	* @return the new module properties
	*/
	@Override
	public com.stpl.app.model.ModuleProperties createModuleProperties(
		int modulePropertySid) {
		return _modulePropertiesLocalService.createModuleProperties(modulePropertySid);
	}

	/**
	* Deletes the module properties with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param modulePropertySid the primary key of the module properties
	* @return the module properties that was removed
	* @throws PortalException if a module properties with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ModuleProperties deleteModuleProperties(
		int modulePropertySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _modulePropertiesLocalService.deleteModuleProperties(modulePropertySid);
	}

	/**
	* Deletes the module properties from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleProperties the module properties
	* @return the module properties that was removed
	*/
	@Override
	public com.stpl.app.model.ModuleProperties deleteModuleProperties(
		com.stpl.app.model.ModuleProperties moduleProperties) {
		return _modulePropertiesLocalService.deleteModuleProperties(moduleProperties);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _modulePropertiesLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _modulePropertiesLocalService.dynamicQuery();
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
		return _modulePropertiesLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _modulePropertiesLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _modulePropertiesLocalService.dynamicQuery(dynamicQuery, start,
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
		return _modulePropertiesLocalService.dynamicQueryCount(dynamicQuery);
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
		return _modulePropertiesLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ModuleProperties fetchModuleProperties(
		int modulePropertySid) {
		return _modulePropertiesLocalService.fetchModuleProperties(modulePropertySid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _modulePropertiesLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _modulePropertiesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the module properties with the primary key.
	*
	* @param modulePropertySid the primary key of the module properties
	* @return the module properties
	* @throws PortalException if a module properties with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ModuleProperties getModuleProperties(
		int modulePropertySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _modulePropertiesLocalService.getModuleProperties(modulePropertySid);
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
	@Override
	public java.util.List<com.stpl.app.model.ModuleProperties> getModulePropertieses(
		int start, int end) {
		return _modulePropertiesLocalService.getModulePropertieses(start, end);
	}

	/**
	* Returns the number of module propertieses.
	*
	* @return the number of module propertieses
	*/
	@Override
	public int getModulePropertiesesCount() {
		return _modulePropertiesLocalService.getModulePropertiesesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _modulePropertiesLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _modulePropertiesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the module properties in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param moduleProperties the module properties
	* @return the module properties that was updated
	*/
	@Override
	public com.stpl.app.model.ModuleProperties updateModuleProperties(
		com.stpl.app.model.ModuleProperties moduleProperties) {
		return _modulePropertiesLocalService.updateModuleProperties(moduleProperties);
	}

	@Override
	public ModulePropertiesLocalService getWrappedService() {
		return _modulePropertiesLocalService;
	}

	@Override
	public void setWrappedService(
		ModulePropertiesLocalService modulePropertiesLocalService) {
		_modulePropertiesLocalService = modulePropertiesLocalService;
	}

	private ModulePropertiesLocalService _modulePropertiesLocalService;
}