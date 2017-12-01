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
 * Provides the local service utility for HistRelationshipBuilder. This utility wraps
 * {@link com.stpl.app.service.impl.HistRelationshipBuilderLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see HistRelationshipBuilderLocalService
 * @see com.stpl.app.service.base.HistRelationshipBuilderLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.HistRelationshipBuilderLocalServiceImpl
 * @generated
 */
@ProviderType
public class HistRelationshipBuilderLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.HistRelationshipBuilderLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the hist relationship builder to the database. Also notifies the appropriate model listeners.
	*
	* @param histRelationshipBuilder the hist relationship builder
	* @return the hist relationship builder that was added
	*/
	public static com.stpl.app.model.HistRelationshipBuilder addHistRelationshipBuilder(
		com.stpl.app.model.HistRelationshipBuilder histRelationshipBuilder) {
		return getService().addHistRelationshipBuilder(histRelationshipBuilder);
	}

	/**
	* Creates a new hist relationship builder with the primary key. Does not add the hist relationship builder to the database.
	*
	* @param histRelationshipBuilderPK the primary key for the new hist relationship builder
	* @return the new hist relationship builder
	*/
	public static com.stpl.app.model.HistRelationshipBuilder createHistRelationshipBuilder(
		com.stpl.app.service.persistence.HistRelationshipBuilderPK histRelationshipBuilderPK) {
		return getService()
				   .createHistRelationshipBuilder(histRelationshipBuilderPK);
	}

	/**
	* Deletes the hist relationship builder from the database. Also notifies the appropriate model listeners.
	*
	* @param histRelationshipBuilder the hist relationship builder
	* @return the hist relationship builder that was removed
	*/
	public static com.stpl.app.model.HistRelationshipBuilder deleteHistRelationshipBuilder(
		com.stpl.app.model.HistRelationshipBuilder histRelationshipBuilder) {
		return getService()
				   .deleteHistRelationshipBuilder(histRelationshipBuilder);
	}

	/**
	* Deletes the hist relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histRelationshipBuilderPK the primary key of the hist relationship builder
	* @return the hist relationship builder that was removed
	* @throws PortalException if a hist relationship builder with the primary key could not be found
	*/
	public static com.stpl.app.model.HistRelationshipBuilder deleteHistRelationshipBuilder(
		com.stpl.app.service.persistence.HistRelationshipBuilderPK histRelationshipBuilderPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteHistRelationshipBuilder(histRelationshipBuilderPK);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.HistRelationshipBuilder fetchHistRelationshipBuilder(
		com.stpl.app.service.persistence.HistRelationshipBuilderPK histRelationshipBuilderPK) {
		return getService()
				   .fetchHistRelationshipBuilder(histRelationshipBuilderPK);
	}

	/**
	* Returns the hist relationship builder with the primary key.
	*
	* @param histRelationshipBuilderPK the primary key of the hist relationship builder
	* @return the hist relationship builder
	* @throws PortalException if a hist relationship builder with the primary key could not be found
	*/
	public static com.stpl.app.model.HistRelationshipBuilder getHistRelationshipBuilder(
		com.stpl.app.service.persistence.HistRelationshipBuilderPK histRelationshipBuilderPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getHistRelationshipBuilder(histRelationshipBuilderPK);
	}

	/**
	* Returns a range of all the hist relationship builders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist relationship builders
	* @param end the upper bound of the range of hist relationship builders (not inclusive)
	* @return the range of hist relationship builders
	*/
	public static java.util.List<com.stpl.app.model.HistRelationshipBuilder> getHistRelationshipBuilders(
		int start, int end) {
		return getService().getHistRelationshipBuilders(start, end);
	}

	/**
	* Returns the number of hist relationship builders.
	*
	* @return the number of hist relationship builders
	*/
	public static int getHistRelationshipBuildersCount() {
		return getService().getHistRelationshipBuildersCount();
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
	* Updates the hist relationship builder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param histRelationshipBuilder the hist relationship builder
	* @return the hist relationship builder that was updated
	*/
	public static com.stpl.app.model.HistRelationshipBuilder updateHistRelationshipBuilder(
		com.stpl.app.model.HistRelationshipBuilder histRelationshipBuilder) {
		return getService()
				   .updateHistRelationshipBuilder(histRelationshipBuilder);
	}

	public static HistRelationshipBuilderLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistRelationshipBuilderLocalService, HistRelationshipBuilderLocalService> _serviceTracker =
		ServiceTrackerFactory.open(HistRelationshipBuilderLocalService.class);
}