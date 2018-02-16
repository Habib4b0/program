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
 * Provides the local service utility for RelationshipBuilder. This utility wraps
 * {@link com.stpl.app.service.impl.RelationshipBuilderLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see RelationshipBuilderLocalService
 * @see com.stpl.app.service.base.RelationshipBuilderLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.RelationshipBuilderLocalServiceImpl
 * @generated
 */
@ProviderType
public class RelationshipBuilderLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.RelationshipBuilderLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the relationship builder to the database. Also notifies the appropriate model listeners.
	*
	* @param relationshipBuilder the relationship builder
	* @return the relationship builder that was added
	*/
	public static com.stpl.app.model.RelationshipBuilder addRelationshipBuilder(
		com.stpl.app.model.RelationshipBuilder relationshipBuilder) {
		return getService().addRelationshipBuilder(relationshipBuilder);
	}

	/**
	* Creates a new relationship builder with the primary key. Does not add the relationship builder to the database.
	*
	* @param relationshipBuilderSid the primary key for the new relationship builder
	* @return the new relationship builder
	*/
	public static com.stpl.app.model.RelationshipBuilder createRelationshipBuilder(
		int relationshipBuilderSid) {
		return getService().createRelationshipBuilder(relationshipBuilderSid);
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
	* Deletes the relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param relationshipBuilderSid the primary key of the relationship builder
	* @return the relationship builder that was removed
	* @throws PortalException if a relationship builder with the primary key could not be found
	*/
	public static com.stpl.app.model.RelationshipBuilder deleteRelationshipBuilder(
		int relationshipBuilderSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRelationshipBuilder(relationshipBuilderSid);
	}

	/**
	* Deletes the relationship builder from the database. Also notifies the appropriate model listeners.
	*
	* @param relationshipBuilder the relationship builder
	* @return the relationship builder that was removed
	*/
	public static com.stpl.app.model.RelationshipBuilder deleteRelationshipBuilder(
		com.stpl.app.model.RelationshipBuilder relationshipBuilder) {
		return getService().deleteRelationshipBuilder(relationshipBuilder);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.RelationshipBuilder fetchRelationshipBuilder(
		int relationshipBuilderSid) {
		return getService().fetchRelationshipBuilder(relationshipBuilderSid);
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
	* Returns the relationship builder with the primary key.
	*
	* @param relationshipBuilderSid the primary key of the relationship builder
	* @return the relationship builder
	* @throws PortalException if a relationship builder with the primary key could not be found
	*/
	public static com.stpl.app.model.RelationshipBuilder getRelationshipBuilder(
		int relationshipBuilderSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRelationshipBuilder(relationshipBuilderSid);
	}

	/**
	* Returns a range of all the relationship builders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship builders
	* @param end the upper bound of the range of relationship builders (not inclusive)
	* @return the range of relationship builders
	*/
	public static java.util.List<com.stpl.app.model.RelationshipBuilder> getRelationshipBuilders(
		int start, int end) {
		return getService().getRelationshipBuilders(start, end);
	}

	/**
	* Returns the number of relationship builders.
	*
	* @return the number of relationship builders
	*/
	public static int getRelationshipBuildersCount() {
		return getService().getRelationshipBuildersCount();
	}

	/**
	* Updates the relationship builder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param relationshipBuilder the relationship builder
	* @return the relationship builder that was updated
	*/
	public static com.stpl.app.model.RelationshipBuilder updateRelationshipBuilder(
		com.stpl.app.model.RelationshipBuilder relationshipBuilder) {
		return getService().updateRelationshipBuilder(relationshipBuilder);
	}

	public static RelationshipBuilderLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RelationshipBuilderLocalService, RelationshipBuilderLocalService> _serviceTracker =
		ServiceTrackerFactory.open(RelationshipBuilderLocalService.class);
}