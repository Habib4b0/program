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
 * Provides a wrapper for {@link HierarchyDefinitionLocalService}.
 *
 * @author
 * @see HierarchyDefinitionLocalService
 * @generated
 */
@ProviderType
public class HierarchyDefinitionLocalServiceWrapper
	implements HierarchyDefinitionLocalService,
		ServiceWrapper<HierarchyDefinitionLocalService> {
	public HierarchyDefinitionLocalServiceWrapper(
		HierarchyDefinitionLocalService hierarchyDefinitionLocalService) {
		_hierarchyDefinitionLocalService = hierarchyDefinitionLocalService;
	}

	/**
	* Adds the hierarchy definition to the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyDefinition the hierarchy definition
	* @return the hierarchy definition that was added
	*/
	@Override
	public com.stpl.app.model.HierarchyDefinition addHierarchyDefinition(
		com.stpl.app.model.HierarchyDefinition hierarchyDefinition) {
		return _hierarchyDefinitionLocalService.addHierarchyDefinition(hierarchyDefinition);
	}

	/**
	* Creates a new hierarchy definition with the primary key. Does not add the hierarchy definition to the database.
	*
	* @param hierarchyDefinitionSid the primary key for the new hierarchy definition
	* @return the new hierarchy definition
	*/
	@Override
	public com.stpl.app.model.HierarchyDefinition createHierarchyDefinition(
		int hierarchyDefinitionSid) {
		return _hierarchyDefinitionLocalService.createHierarchyDefinition(hierarchyDefinitionSid);
	}

	/**
	* Deletes the hierarchy definition from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyDefinition the hierarchy definition
	* @return the hierarchy definition that was removed
	*/
	@Override
	public com.stpl.app.model.HierarchyDefinition deleteHierarchyDefinition(
		com.stpl.app.model.HierarchyDefinition hierarchyDefinition) {
		return _hierarchyDefinitionLocalService.deleteHierarchyDefinition(hierarchyDefinition);
	}

	/**
	* Deletes the hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyDefinitionSid the primary key of the hierarchy definition
	* @return the hierarchy definition that was removed
	* @throws PortalException if a hierarchy definition with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HierarchyDefinition deleteHierarchyDefinition(
		int hierarchyDefinitionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hierarchyDefinitionLocalService.deleteHierarchyDefinition(hierarchyDefinitionSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hierarchyDefinitionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _hierarchyDefinitionLocalService.dynamicQuery();
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
		return _hierarchyDefinitionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _hierarchyDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _hierarchyDefinitionLocalService.dynamicQuery(dynamicQuery,
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
		return _hierarchyDefinitionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _hierarchyDefinitionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.HierarchyDefinition fetchHierarchyDefinition(
		int hierarchyDefinitionSid) {
		return _hierarchyDefinitionLocalService.fetchHierarchyDefinition(hierarchyDefinitionSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _hierarchyDefinitionLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the hierarchy definition with the primary key.
	*
	* @param hierarchyDefinitionSid the primary key of the hierarchy definition
	* @return the hierarchy definition
	* @throws PortalException if a hierarchy definition with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HierarchyDefinition getHierarchyDefinition(
		int hierarchyDefinitionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hierarchyDefinitionLocalService.getHierarchyDefinition(hierarchyDefinitionSid);
	}

	/**
	* Returns a range of all the hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy definitions
	* @param end the upper bound of the range of hierarchy definitions (not inclusive)
	* @return the range of hierarchy definitions
	*/
	@Override
	public java.util.List<com.stpl.app.model.HierarchyDefinition> getHierarchyDefinitions(
		int start, int end) {
		return _hierarchyDefinitionLocalService.getHierarchyDefinitions(start,
			end);
	}

	/**
	* Returns the number of hierarchy definitions.
	*
	* @return the number of hierarchy definitions
	*/
	@Override
	public int getHierarchyDefinitionsCount() {
		return _hierarchyDefinitionLocalService.getHierarchyDefinitionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _hierarchyDefinitionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _hierarchyDefinitionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hierarchyDefinitionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the hierarchy definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param hierarchyDefinition the hierarchy definition
	* @return the hierarchy definition that was updated
	*/
	@Override
	public com.stpl.app.model.HierarchyDefinition updateHierarchyDefinition(
		com.stpl.app.model.HierarchyDefinition hierarchyDefinition) {
		return _hierarchyDefinitionLocalService.updateHierarchyDefinition(hierarchyDefinition);
	}

	@Override
	public HierarchyDefinitionLocalService getWrappedService() {
		return _hierarchyDefinitionLocalService;
	}

	@Override
	public void setWrappedService(
		HierarchyDefinitionLocalService hierarchyDefinitionLocalService) {
		_hierarchyDefinitionLocalService = hierarchyDefinitionLocalService;
	}

	private HierarchyDefinitionLocalService _hierarchyDefinitionLocalService;
}