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
 * Provides the local service utility for VwUserTables. This utility wraps
 * {@link com.stpl.app.service.impl.VwUserTablesLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see VwUserTablesLocalService
 * @see com.stpl.app.service.base.VwUserTablesLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.VwUserTablesLocalServiceImpl
 * @generated
 */
@ProviderType
public class VwUserTablesLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.VwUserTablesLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the vw user tables to the database. Also notifies the appropriate model listeners.
	*
	* @param vwUserTables the vw user tables
	* @return the vw user tables that was added
	*/
	public static com.stpl.app.model.VwUserTables addVwUserTables(
		com.stpl.app.model.VwUserTables vwUserTables) {
		return getService().addVwUserTables(vwUserTables);
	}

	/**
	* Creates a new vw user tables with the primary key. Does not add the vw user tables to the database.
	*
	* @param uniqueId the primary key for the new vw user tables
	* @return the new vw user tables
	*/
	public static com.stpl.app.model.VwUserTables createVwUserTables(
		int uniqueId) {
		return getService().createVwUserTables(uniqueId);
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
	* Deletes the vw user tables with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param uniqueId the primary key of the vw user tables
	* @return the vw user tables that was removed
	* @throws PortalException if a vw user tables with the primary key could not be found
	*/
	public static com.stpl.app.model.VwUserTables deleteVwUserTables(
		int uniqueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteVwUserTables(uniqueId);
	}

	/**
	* Deletes the vw user tables from the database. Also notifies the appropriate model listeners.
	*
	* @param vwUserTables the vw user tables
	* @return the vw user tables that was removed
	*/
	public static com.stpl.app.model.VwUserTables deleteVwUserTables(
		com.stpl.app.model.VwUserTables vwUserTables) {
		return getService().deleteVwUserTables(vwUserTables);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.VwUserTables fetchVwUserTables(
		int uniqueId) {
		return getService().fetchVwUserTables(uniqueId);
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
	* Returns the vw user tables with the primary key.
	*
	* @param uniqueId the primary key of the vw user tables
	* @return the vw user tables
	* @throws PortalException if a vw user tables with the primary key could not be found
	*/
	public static com.stpl.app.model.VwUserTables getVwUserTables(int uniqueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getVwUserTables(uniqueId);
	}

	/**
	* Returns a range of all the vw user tableses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw user tableses
	* @param end the upper bound of the range of vw user tableses (not inclusive)
	* @return the range of vw user tableses
	*/
	public static java.util.List<com.stpl.app.model.VwUserTables> getVwUserTableses(
		int start, int end) {
		return getService().getVwUserTableses(start, end);
	}

	/**
	* Returns the number of vw user tableses.
	*
	* @return the number of vw user tableses
	*/
	public static int getVwUserTablesesCount() {
		return getService().getVwUserTablesesCount();
	}

	/**
	* Updates the vw user tables in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param vwUserTables the vw user tables
	* @return the vw user tables that was updated
	*/
	public static com.stpl.app.model.VwUserTables updateVwUserTables(
		com.stpl.app.model.VwUserTables vwUserTables) {
		return getService().updateVwUserTables(vwUserTables);
	}

	public static VwUserTablesLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwUserTablesLocalService, VwUserTablesLocalService> _serviceTracker =
		ServiceTrackerFactory.open(VwUserTablesLocalService.class);
}