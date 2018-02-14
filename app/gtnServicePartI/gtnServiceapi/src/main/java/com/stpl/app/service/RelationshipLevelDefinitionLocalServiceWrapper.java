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
 * Provides a wrapper for {@link RelationshipLevelDefinitionLocalService}.
 *
 * @author
 * @see RelationshipLevelDefinitionLocalService
 * @generated
 */
@ProviderType
public class RelationshipLevelDefinitionLocalServiceWrapper
	implements RelationshipLevelDefinitionLocalService,
		ServiceWrapper<RelationshipLevelDefinitionLocalService> {
	public RelationshipLevelDefinitionLocalServiceWrapper(
		RelationshipLevelDefinitionLocalService relationshipLevelDefinitionLocalService) {
		_relationshipLevelDefinitionLocalService = relationshipLevelDefinitionLocalService;
	}

	/**
	* Adds the relationship level definition to the database. Also notifies the appropriate model listeners.
	*
	* @param relationshipLevelDefinition the relationship level definition
	* @return the relationship level definition that was added
	*/
	@Override
	public com.stpl.app.model.RelationshipLevelDefinition addRelationshipLevelDefinition(
		com.stpl.app.model.RelationshipLevelDefinition relationshipLevelDefinition) {
		return _relationshipLevelDefinitionLocalService.addRelationshipLevelDefinition(relationshipLevelDefinition);
	}

	/**
	* Creates a new relationship level definition with the primary key. Does not add the relationship level definition to the database.
	*
	* @param relationshipLevelSid the primary key for the new relationship level definition
	* @return the new relationship level definition
	*/
	@Override
	public com.stpl.app.model.RelationshipLevelDefinition createRelationshipLevelDefinition(
		int relationshipLevelSid) {
		return _relationshipLevelDefinitionLocalService.createRelationshipLevelDefinition(relationshipLevelSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _relationshipLevelDefinitionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the relationship level definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param relationshipLevelSid the primary key of the relationship level definition
	* @return the relationship level definition that was removed
	* @throws PortalException if a relationship level definition with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RelationshipLevelDefinition deleteRelationshipLevelDefinition(
		int relationshipLevelSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _relationshipLevelDefinitionLocalService.deleteRelationshipLevelDefinition(relationshipLevelSid);
	}

	/**
	* Deletes the relationship level definition from the database. Also notifies the appropriate model listeners.
	*
	* @param relationshipLevelDefinition the relationship level definition
	* @return the relationship level definition that was removed
	*/
	@Override
	public com.stpl.app.model.RelationshipLevelDefinition deleteRelationshipLevelDefinition(
		com.stpl.app.model.RelationshipLevelDefinition relationshipLevelDefinition) {
		return _relationshipLevelDefinitionLocalService.deleteRelationshipLevelDefinition(relationshipLevelDefinition);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _relationshipLevelDefinitionLocalService.dynamicQuery();
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
		return _relationshipLevelDefinitionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _relationshipLevelDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _relationshipLevelDefinitionLocalService.dynamicQuery(dynamicQuery,
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
		return _relationshipLevelDefinitionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _relationshipLevelDefinitionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.RelationshipLevelDefinition fetchRelationshipLevelDefinition(
		int relationshipLevelSid) {
		return _relationshipLevelDefinitionLocalService.fetchRelationshipLevelDefinition(relationshipLevelSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _relationshipLevelDefinitionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _relationshipLevelDefinitionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _relationshipLevelDefinitionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _relationshipLevelDefinitionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the relationship level definition with the primary key.
	*
	* @param relationshipLevelSid the primary key of the relationship level definition
	* @return the relationship level definition
	* @throws PortalException if a relationship level definition with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RelationshipLevelDefinition getRelationshipLevelDefinition(
		int relationshipLevelSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _relationshipLevelDefinitionLocalService.getRelationshipLevelDefinition(relationshipLevelSid);
	}

	/**
	* Returns a range of all the relationship level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship level definitions
	* @param end the upper bound of the range of relationship level definitions (not inclusive)
	* @return the range of relationship level definitions
	*/
	@Override
	public java.util.List<com.stpl.app.model.RelationshipLevelDefinition> getRelationshipLevelDefinitions(
		int start, int end) {
		return _relationshipLevelDefinitionLocalService.getRelationshipLevelDefinitions(start,
			end);
	}

	/**
	* Returns the number of relationship level definitions.
	*
	* @return the number of relationship level definitions
	*/
	@Override
	public int getRelationshipLevelDefinitionsCount() {
		return _relationshipLevelDefinitionLocalService.getRelationshipLevelDefinitionsCount();
	}

	/**
	* Updates the relationship level definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param relationshipLevelDefinition the relationship level definition
	* @return the relationship level definition that was updated
	*/
	@Override
	public com.stpl.app.model.RelationshipLevelDefinition updateRelationshipLevelDefinition(
		com.stpl.app.model.RelationshipLevelDefinition relationshipLevelDefinition) {
		return _relationshipLevelDefinitionLocalService.updateRelationshipLevelDefinition(relationshipLevelDefinition);
	}

	@Override
	public RelationshipLevelDefinitionLocalService getWrappedService() {
		return _relationshipLevelDefinitionLocalService;
	}

	@Override
	public void setWrappedService(
		RelationshipLevelDefinitionLocalService relationshipLevelDefinitionLocalService) {
		_relationshipLevelDefinitionLocalService = relationshipLevelDefinitionLocalService;
	}

	private RelationshipLevelDefinitionLocalService _relationshipLevelDefinitionLocalService;
}