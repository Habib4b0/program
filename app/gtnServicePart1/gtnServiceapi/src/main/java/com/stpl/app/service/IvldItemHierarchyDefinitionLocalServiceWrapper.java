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
 * Provides a wrapper for {@link IvldItemHierarchyDefinitionLocalService}.
 *
 * @author
 * @see IvldItemHierarchyDefinitionLocalService
 * @generated
 */
@ProviderType
public class IvldItemHierarchyDefinitionLocalServiceWrapper
	implements IvldItemHierarchyDefinitionLocalService,
		ServiceWrapper<IvldItemHierarchyDefinitionLocalService> {
	public IvldItemHierarchyDefinitionLocalServiceWrapper(
		IvldItemHierarchyDefinitionLocalService ivldItemHierarchyDefinitionLocalService) {
		_ivldItemHierarchyDefinitionLocalService = ivldItemHierarchyDefinitionLocalService;
	}

	/**
	* Adds the ivld item hierarchy definition to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemHierarchyDefinition the ivld item hierarchy definition
	* @return the ivld item hierarchy definition that was added
	*/
	@Override
	public com.stpl.app.model.IvldItemHierarchyDefinition addIvldItemHierarchyDefinition(
		com.stpl.app.model.IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
		return _ivldItemHierarchyDefinitionLocalService.addIvldItemHierarchyDefinition(ivldItemHierarchyDefinition);
	}

	/**
	* Creates a new ivld item hierarchy definition with the primary key. Does not add the ivld item hierarchy definition to the database.
	*
	* @param ivldItemHierarchyDefinitionSid the primary key for the new ivld item hierarchy definition
	* @return the new ivld item hierarchy definition
	*/
	@Override
	public com.stpl.app.model.IvldItemHierarchyDefinition createIvldItemHierarchyDefinition(
		int ivldItemHierarchyDefinitionSid) {
		return _ivldItemHierarchyDefinitionLocalService.createIvldItemHierarchyDefinition(ivldItemHierarchyDefinitionSid);
	}

	/**
	* Deletes the ivld item hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
	* @return the ivld item hierarchy definition that was removed
	* @throws PortalException if a ivld item hierarchy definition with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldItemHierarchyDefinition deleteIvldItemHierarchyDefinition(
		int ivldItemHierarchyDefinitionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldItemHierarchyDefinitionLocalService.deleteIvldItemHierarchyDefinition(ivldItemHierarchyDefinitionSid);
	}

	/**
	* Deletes the ivld item hierarchy definition from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemHierarchyDefinition the ivld item hierarchy definition
	* @return the ivld item hierarchy definition that was removed
	*/
	@Override
	public com.stpl.app.model.IvldItemHierarchyDefinition deleteIvldItemHierarchyDefinition(
		com.stpl.app.model.IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
		return _ivldItemHierarchyDefinitionLocalService.deleteIvldItemHierarchyDefinition(ivldItemHierarchyDefinition);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldItemHierarchyDefinitionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldItemHierarchyDefinitionLocalService.dynamicQuery();
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
		return _ivldItemHierarchyDefinitionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldItemHierarchyDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldItemHierarchyDefinitionLocalService.dynamicQuery(dynamicQuery,
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
		return _ivldItemHierarchyDefinitionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldItemHierarchyDefinitionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.IvldItemHierarchyDefinition fetchIvldItemHierarchyDefinition(
		int ivldItemHierarchyDefinitionSid) {
		return _ivldItemHierarchyDefinitionLocalService.fetchIvldItemHierarchyDefinition(ivldItemHierarchyDefinitionSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldItemHierarchyDefinitionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldItemHierarchyDefinitionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld item hierarchy definition with the primary key.
	*
	* @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
	* @return the ivld item hierarchy definition
	* @throws PortalException if a ivld item hierarchy definition with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldItemHierarchyDefinition getIvldItemHierarchyDefinition(
		int ivldItemHierarchyDefinitionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldItemHierarchyDefinitionLocalService.getIvldItemHierarchyDefinition(ivldItemHierarchyDefinitionSid);
	}

	/**
	* Returns a range of all the ivld item hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item hierarchy definitions
	* @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
	* @return the range of ivld item hierarchy definitions
	*/
	@Override
	public java.util.List<com.stpl.app.model.IvldItemHierarchyDefinition> getIvldItemHierarchyDefinitions(
		int start, int end) {
		return _ivldItemHierarchyDefinitionLocalService.getIvldItemHierarchyDefinitions(start,
			end);
	}

	/**
	* Returns the number of ivld item hierarchy definitions.
	*
	* @return the number of ivld item hierarchy definitions
	*/
	@Override
	public int getIvldItemHierarchyDefinitionsCount() {
		return _ivldItemHierarchyDefinitionLocalService.getIvldItemHierarchyDefinitionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldItemHierarchyDefinitionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldItemHierarchyDefinitionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld item hierarchy definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldItemHierarchyDefinition the ivld item hierarchy definition
	* @return the ivld item hierarchy definition that was updated
	*/
	@Override
	public com.stpl.app.model.IvldItemHierarchyDefinition updateIvldItemHierarchyDefinition(
		com.stpl.app.model.IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
		return _ivldItemHierarchyDefinitionLocalService.updateIvldItemHierarchyDefinition(ivldItemHierarchyDefinition);
	}

	@Override
	public IvldItemHierarchyDefinitionLocalService getWrappedService() {
		return _ivldItemHierarchyDefinitionLocalService;
	}

	@Override
	public void setWrappedService(
		IvldItemHierarchyDefinitionLocalService ivldItemHierarchyDefinitionLocalService) {
		_ivldItemHierarchyDefinitionLocalService = ivldItemHierarchyDefinitionLocalService;
	}

	private IvldItemHierarchyDefinitionLocalService _ivldItemHierarchyDefinitionLocalService;
}