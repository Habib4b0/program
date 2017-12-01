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
 * Provides a wrapper for {@link HistHierarchyDefinitionLocalService}.
 *
 * @author
 * @see HistHierarchyDefinitionLocalService
 * @generated
 */
@ProviderType
public class HistHierarchyDefinitionLocalServiceWrapper
	implements HistHierarchyDefinitionLocalService,
		ServiceWrapper<HistHierarchyDefinitionLocalService> {
	public HistHierarchyDefinitionLocalServiceWrapper(
		HistHierarchyDefinitionLocalService histHierarchyDefinitionLocalService) {
		_histHierarchyDefinitionLocalService = histHierarchyDefinitionLocalService;
	}

	/**
	* Adds the hist hierarchy definition to the database. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyDefinition the hist hierarchy definition
	* @return the hist hierarchy definition that was added
	*/
	@Override
	public com.stpl.app.model.HistHierarchyDefinition addHistHierarchyDefinition(
		com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition) {
		return _histHierarchyDefinitionLocalService.addHistHierarchyDefinition(histHierarchyDefinition);
	}

	/**
	* Creates a new hist hierarchy definition with the primary key. Does not add the hist hierarchy definition to the database.
	*
	* @param histHierarchyDefinitionPK the primary key for the new hist hierarchy definition
	* @return the new hist hierarchy definition
	*/
	@Override
	public com.stpl.app.model.HistHierarchyDefinition createHistHierarchyDefinition(
		com.stpl.app.service.persistence.HistHierarchyDefinitionPK histHierarchyDefinitionPK) {
		return _histHierarchyDefinitionLocalService.createHistHierarchyDefinition(histHierarchyDefinitionPK);
	}

	/**
	* Deletes the hist hierarchy definition from the database. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyDefinition the hist hierarchy definition
	* @return the hist hierarchy definition that was removed
	*/
	@Override
	public com.stpl.app.model.HistHierarchyDefinition deleteHistHierarchyDefinition(
		com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition) {
		return _histHierarchyDefinitionLocalService.deleteHistHierarchyDefinition(histHierarchyDefinition);
	}

	/**
	* Deletes the hist hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
	* @return the hist hierarchy definition that was removed
	* @throws PortalException if a hist hierarchy definition with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HistHierarchyDefinition deleteHistHierarchyDefinition(
		com.stpl.app.service.persistence.HistHierarchyDefinitionPK histHierarchyDefinitionPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histHierarchyDefinitionLocalService.deleteHistHierarchyDefinition(histHierarchyDefinitionPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histHierarchyDefinitionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _histHierarchyDefinitionLocalService.dynamicQuery();
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
		return _histHierarchyDefinitionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _histHierarchyDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _histHierarchyDefinitionLocalService.dynamicQuery(dynamicQuery,
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
		return _histHierarchyDefinitionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _histHierarchyDefinitionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.HistHierarchyDefinition fetchHistHierarchyDefinition(
		com.stpl.app.service.persistence.HistHierarchyDefinitionPK histHierarchyDefinitionPK) {
		return _histHierarchyDefinitionLocalService.fetchHistHierarchyDefinition(histHierarchyDefinitionPK);
	}

	/**
	* Returns the hist hierarchy definition with the primary key.
	*
	* @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
	* @return the hist hierarchy definition
	* @throws PortalException if a hist hierarchy definition with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HistHierarchyDefinition getHistHierarchyDefinition(
		com.stpl.app.service.persistence.HistHierarchyDefinitionPK histHierarchyDefinitionPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histHierarchyDefinitionLocalService.getHistHierarchyDefinition(histHierarchyDefinitionPK);
	}

	/**
	* Returns a range of all the hist hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy definitions
	* @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
	* @return the range of hist hierarchy definitions
	*/
	@Override
	public java.util.List<com.stpl.app.model.HistHierarchyDefinition> getHistHierarchyDefinitions(
		int start, int end) {
		return _histHierarchyDefinitionLocalService.getHistHierarchyDefinitions(start,
			end);
	}

	/**
	* Returns the number of hist hierarchy definitions.
	*
	* @return the number of hist hierarchy definitions
	*/
	@Override
	public int getHistHierarchyDefinitionsCount() {
		return _histHierarchyDefinitionLocalService.getHistHierarchyDefinitionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _histHierarchyDefinitionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histHierarchyDefinitionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the hist hierarchy definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyDefinition the hist hierarchy definition
	* @return the hist hierarchy definition that was updated
	*/
	@Override
	public com.stpl.app.model.HistHierarchyDefinition updateHistHierarchyDefinition(
		com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition) {
		return _histHierarchyDefinitionLocalService.updateHistHierarchyDefinition(histHierarchyDefinition);
	}

	@Override
	public HistHierarchyDefinitionLocalService getWrappedService() {
		return _histHierarchyDefinitionLocalService;
	}

	@Override
	public void setWrappedService(
		HistHierarchyDefinitionLocalService histHierarchyDefinitionLocalService) {
		_histHierarchyDefinitionLocalService = histHierarchyDefinitionLocalService;
	}

	private HistHierarchyDefinitionLocalService _histHierarchyDefinitionLocalService;
}