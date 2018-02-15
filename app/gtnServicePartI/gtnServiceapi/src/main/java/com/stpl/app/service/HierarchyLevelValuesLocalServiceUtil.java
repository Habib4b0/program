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
 * Provides the local service utility for HierarchyLevelValues. This utility wraps
 * {@link com.stpl.app.service.impl.HierarchyLevelValuesLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see HierarchyLevelValuesLocalService
 * @see com.stpl.app.service.base.HierarchyLevelValuesLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.HierarchyLevelValuesLocalServiceImpl
 * @generated
 */
@ProviderType
public class HierarchyLevelValuesLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.HierarchyLevelValuesLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the hierarchy level values to the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelValues the hierarchy level values
	* @return the hierarchy level values that was added
	*/
	public static com.stpl.app.model.HierarchyLevelValues addHierarchyLevelValues(
		com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues) {
		return getService().addHierarchyLevelValues(hierarchyLevelValues);
	}

	/**
	* Creates a new hierarchy level values with the primary key. Does not add the hierarchy level values to the database.
	*
	* @param hierarchyLevelValuesSid the primary key for the new hierarchy level values
	* @return the new hierarchy level values
	*/
	public static com.stpl.app.model.HierarchyLevelValues createHierarchyLevelValues(
		int hierarchyLevelValuesSid) {
		return getService().createHierarchyLevelValues(hierarchyLevelValuesSid);
	}

	/**
	* Deletes the hierarchy level values from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelValues the hierarchy level values
	* @return the hierarchy level values that was removed
	*/
	public static com.stpl.app.model.HierarchyLevelValues deleteHierarchyLevelValues(
		com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues) {
		return getService().deleteHierarchyLevelValues(hierarchyLevelValues);
	}

	/**
	* Deletes the hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	* @return the hierarchy level values that was removed
	* @throws PortalException if a hierarchy level values with the primary key could not be found
	*/
	public static com.stpl.app.model.HierarchyLevelValues deleteHierarchyLevelValues(
		int hierarchyLevelValuesSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteHierarchyLevelValues(hierarchyLevelValuesSid);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.HierarchyLevelValues fetchHierarchyLevelValues(
		int hierarchyLevelValuesSid) {
		return getService().fetchHierarchyLevelValues(hierarchyLevelValuesSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the hierarchy level values with the primary key.
	*
	* @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	* @return the hierarchy level values
	* @throws PortalException if a hierarchy level values with the primary key could not be found
	*/
	public static com.stpl.app.model.HierarchyLevelValues getHierarchyLevelValues(
		int hierarchyLevelValuesSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getHierarchyLevelValues(hierarchyLevelValuesSid);
	}

	/**
	* Returns a range of all the hierarchy level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level valueses
	* @param end the upper bound of the range of hierarchy level valueses (not inclusive)
	* @return the range of hierarchy level valueses
	*/
	public static java.util.List<com.stpl.app.model.HierarchyLevelValues> getHierarchyLevelValueses(
		int start, int end) {
		return getService().getHierarchyLevelValueses(start, end);
	}

	/**
	* Returns the number of hierarchy level valueses.
	*
	* @return the number of hierarchy level valueses
	*/
	public static int getHierarchyLevelValuesesCount() {
		return getService().getHierarchyLevelValuesesCount();
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
	* Updates the hierarchy level values in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelValues the hierarchy level values
	* @return the hierarchy level values that was updated
	*/
	public static com.stpl.app.model.HierarchyLevelValues updateHierarchyLevelValues(
		com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues) {
		return getService().updateHierarchyLevelValues(hierarchyLevelValues);
	}

	public static HierarchyLevelValuesLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HierarchyLevelValuesLocalService, HierarchyLevelValuesLocalService> _serviceTracker =
		ServiceTrackerFactory.open(HierarchyLevelValuesLocalService.class);
}