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
 * Provides a wrapper for {@link HierarchyLevelDefinitionLocalService}.
 *
 * @author
 * @see HierarchyLevelDefinitionLocalService
 * @generated
 */
@ProviderType
public class HierarchyLevelDefinitionLocalServiceWrapper
	implements HierarchyLevelDefinitionLocalService,
		ServiceWrapper<HierarchyLevelDefinitionLocalService> {
	public HierarchyLevelDefinitionLocalServiceWrapper(
		HierarchyLevelDefinitionLocalService hierarchyLevelDefinitionLocalService) {
		_hierarchyLevelDefinitionLocalService = hierarchyLevelDefinitionLocalService;
	}

	/**
	* Adds the hierarchy level definition to the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelDefinition the hierarchy level definition
	* @return the hierarchy level definition that was added
	*/
	@Override
	public com.stpl.app.model.HierarchyLevelDefinition addHierarchyLevelDefinition(
		com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition) {
		return _hierarchyLevelDefinitionLocalService.addHierarchyLevelDefinition(hierarchyLevelDefinition);
	}

	/**
	* Creates a new hierarchy level definition with the primary key. Does not add the hierarchy level definition to the database.
	*
	* @param hierarchyLevelDefinitionSid the primary key for the new hierarchy level definition
	* @return the new hierarchy level definition
	*/
	@Override
	public com.stpl.app.model.HierarchyLevelDefinition createHierarchyLevelDefinition(
		int hierarchyLevelDefinitionSid) {
		return _hierarchyLevelDefinitionLocalService.createHierarchyLevelDefinition(hierarchyLevelDefinitionSid);
	}

	/**
	* Deletes the hierarchy level definition from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelDefinition the hierarchy level definition
	* @return the hierarchy level definition that was removed
	*/
	@Override
	public com.stpl.app.model.HierarchyLevelDefinition deleteHierarchyLevelDefinition(
		com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition) {
		return _hierarchyLevelDefinitionLocalService.deleteHierarchyLevelDefinition(hierarchyLevelDefinition);
	}

	/**
	* Deletes the hierarchy level definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	* @return the hierarchy level definition that was removed
	* @throws PortalException if a hierarchy level definition with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HierarchyLevelDefinition deleteHierarchyLevelDefinition(
		int hierarchyLevelDefinitionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hierarchyLevelDefinitionLocalService.deleteHierarchyLevelDefinition(hierarchyLevelDefinitionSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hierarchyLevelDefinitionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _hierarchyLevelDefinitionLocalService.dynamicQuery();
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
		return _hierarchyLevelDefinitionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _hierarchyLevelDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _hierarchyLevelDefinitionLocalService.dynamicQuery(dynamicQuery,
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
		return _hierarchyLevelDefinitionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _hierarchyLevelDefinitionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.HierarchyLevelDefinition fetchHierarchyLevelDefinition(
		int hierarchyLevelDefinitionSid) {
		return _hierarchyLevelDefinitionLocalService.fetchHierarchyLevelDefinition(hierarchyLevelDefinitionSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _hierarchyLevelDefinitionLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the hierarchy level definition with the primary key.
	*
	* @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	* @return the hierarchy level definition
	* @throws PortalException if a hierarchy level definition with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HierarchyLevelDefinition getHierarchyLevelDefinition(
		int hierarchyLevelDefinitionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hierarchyLevelDefinitionLocalService.getHierarchyLevelDefinition(hierarchyLevelDefinitionSid);
	}

	/**
	* Returns a range of all the hierarchy level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level definitions
	* @param end the upper bound of the range of hierarchy level definitions (not inclusive)
	* @return the range of hierarchy level definitions
	*/
	@Override
	public java.util.List<com.stpl.app.model.HierarchyLevelDefinition> getHierarchyLevelDefinitions(
		int start, int end) {
		return _hierarchyLevelDefinitionLocalService.getHierarchyLevelDefinitions(start,
			end);
	}

	/**
	* Returns the number of hierarchy level definitions.
	*
	* @return the number of hierarchy level definitions
	*/
	@Override
	public int getHierarchyLevelDefinitionsCount() {
		return _hierarchyLevelDefinitionLocalService.getHierarchyLevelDefinitionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _hierarchyLevelDefinitionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _hierarchyLevelDefinitionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hierarchyLevelDefinitionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the hierarchy level definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelDefinition the hierarchy level definition
	* @return the hierarchy level definition that was updated
	*/
	@Override
	public com.stpl.app.model.HierarchyLevelDefinition updateHierarchyLevelDefinition(
		com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition) {
		return _hierarchyLevelDefinitionLocalService.updateHierarchyLevelDefinition(hierarchyLevelDefinition);
	}

	@Override
	public HierarchyLevelDefinitionLocalService getWrappedService() {
		return _hierarchyLevelDefinitionLocalService;
	}

	@Override
	public void setWrappedService(
		HierarchyLevelDefinitionLocalService hierarchyLevelDefinitionLocalService) {
		_hierarchyLevelDefinitionLocalService = hierarchyLevelDefinitionLocalService;
	}

	private HierarchyLevelDefinitionLocalService _hierarchyLevelDefinitionLocalService;
}