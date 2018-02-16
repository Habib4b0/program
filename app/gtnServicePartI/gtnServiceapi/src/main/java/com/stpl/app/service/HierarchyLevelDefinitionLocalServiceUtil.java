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
 * Provides the local service utility for HierarchyLevelDefinition. This utility wraps
 * {@link com.stpl.app.service.impl.HierarchyLevelDefinitionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see HierarchyLevelDefinitionLocalService
 * @see com.stpl.app.service.base.HierarchyLevelDefinitionLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.HierarchyLevelDefinitionLocalServiceImpl
 * @generated
 */
@ProviderType
public class HierarchyLevelDefinitionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.HierarchyLevelDefinitionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the hierarchy level definition to the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelDefinition the hierarchy level definition
	* @return the hierarchy level definition that was added
	*/
	public static com.stpl.app.model.HierarchyLevelDefinition addHierarchyLevelDefinition(
		com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition) {
		return getService().addHierarchyLevelDefinition(hierarchyLevelDefinition);
	}

	/**
	* Creates a new hierarchy level definition with the primary key. Does not add the hierarchy level definition to the database.
	*
	* @param hierarchyLevelDefinitionSid the primary key for the new hierarchy level definition
	* @return the new hierarchy level definition
	*/
	public static com.stpl.app.model.HierarchyLevelDefinition createHierarchyLevelDefinition(
		int hierarchyLevelDefinitionSid) {
		return getService()
				   .createHierarchyLevelDefinition(hierarchyLevelDefinitionSid);
	}

	/**
	* Deletes the hierarchy level definition from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelDefinition the hierarchy level definition
	* @return the hierarchy level definition that was removed
	*/
	public static com.stpl.app.model.HierarchyLevelDefinition deleteHierarchyLevelDefinition(
		com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition) {
		return getService()
				   .deleteHierarchyLevelDefinition(hierarchyLevelDefinition);
	}

	/**
	* Deletes the hierarchy level definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	* @return the hierarchy level definition that was removed
	* @throws PortalException if a hierarchy level definition with the primary key could not be found
	*/
	public static com.stpl.app.model.HierarchyLevelDefinition deleteHierarchyLevelDefinition(
		int hierarchyLevelDefinitionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteHierarchyLevelDefinition(hierarchyLevelDefinitionSid);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.HierarchyLevelDefinition fetchHierarchyLevelDefinition(
		int hierarchyLevelDefinitionSid) {
		return getService()
				   .fetchHierarchyLevelDefinition(hierarchyLevelDefinitionSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the hierarchy level definition with the primary key.
	*
	* @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	* @return the hierarchy level definition
	* @throws PortalException if a hierarchy level definition with the primary key could not be found
	*/
	public static com.stpl.app.model.HierarchyLevelDefinition getHierarchyLevelDefinition(
		int hierarchyLevelDefinitionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getHierarchyLevelDefinition(hierarchyLevelDefinitionSid);
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
	public static java.util.List<com.stpl.app.model.HierarchyLevelDefinition> getHierarchyLevelDefinitions(
		int start, int end) {
		return getService().getHierarchyLevelDefinitions(start, end);
	}

	/**
	* Returns the number of hierarchy level definitions.
	*
	* @return the number of hierarchy level definitions
	*/
	public static int getHierarchyLevelDefinitionsCount() {
		return getService().getHierarchyLevelDefinitionsCount();
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
	* Updates the hierarchy level definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelDefinition the hierarchy level definition
	* @return the hierarchy level definition that was updated
	*/
	public static com.stpl.app.model.HierarchyLevelDefinition updateHierarchyLevelDefinition(
		com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition) {
		return getService()
				   .updateHierarchyLevelDefinition(hierarchyLevelDefinition);
	}

	public static HierarchyLevelDefinitionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HierarchyLevelDefinitionLocalService, HierarchyLevelDefinitionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(HierarchyLevelDefinitionLocalService.class);
}