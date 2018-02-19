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
 * Provides the local service utility for PhsActuals. This utility wraps
 * {@link com.stpl.app.service.impl.PhsActualsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see PhsActualsLocalService
 * @see com.stpl.app.service.base.PhsActualsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.PhsActualsLocalServiceImpl
 * @generated
 */
@ProviderType
public class PhsActualsLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.PhsActualsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the phs actuals to the database. Also notifies the appropriate model listeners.
	*
	* @param phsActuals the phs actuals
	* @return the phs actuals that was added
	*/
	public static com.stpl.app.model.PhsActuals addPhsActuals(
		com.stpl.app.model.PhsActuals phsActuals) {
		return getService().addPhsActuals(phsActuals);
	}

	/**
	* Creates a new phs actuals with the primary key. Does not add the phs actuals to the database.
	*
	* @param phsActualsPK the primary key for the new phs actuals
	* @return the new phs actuals
	*/
	public static com.stpl.app.model.PhsActuals createPhsActuals(
		com.stpl.app.service.persistence.PhsActualsPK phsActualsPK) {
		return getService().createPhsActuals(phsActualsPK);
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
	* Deletes the phs actuals from the database. Also notifies the appropriate model listeners.
	*
	* @param phsActuals the phs actuals
	* @return the phs actuals that was removed
	*/
	public static com.stpl.app.model.PhsActuals deletePhsActuals(
		com.stpl.app.model.PhsActuals phsActuals) {
		return getService().deletePhsActuals(phsActuals);
	}

	/**
	* Deletes the phs actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param phsActualsPK the primary key of the phs actuals
	* @return the phs actuals that was removed
	* @throws PortalException if a phs actuals with the primary key could not be found
	*/
	public static com.stpl.app.model.PhsActuals deletePhsActuals(
		com.stpl.app.service.persistence.PhsActualsPK phsActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePhsActuals(phsActualsPK);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.PhsActuals fetchPhsActuals(
		com.stpl.app.service.persistence.PhsActualsPK phsActualsPK) {
		return getService().fetchPhsActuals(phsActualsPK);
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
	* Returns the phs actuals with the primary key.
	*
	* @param phsActualsPK the primary key of the phs actuals
	* @return the phs actuals
	* @throws PortalException if a phs actuals with the primary key could not be found
	*/
	public static com.stpl.app.model.PhsActuals getPhsActuals(
		com.stpl.app.service.persistence.PhsActualsPK phsActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPhsActuals(phsActualsPK);
	}

	/**
	* Returns a range of all the phs actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phs actualses
	* @param end the upper bound of the range of phs actualses (not inclusive)
	* @return the range of phs actualses
	*/
	public static java.util.List<com.stpl.app.model.PhsActuals> getPhsActualses(
		int start, int end) {
		return getService().getPhsActualses(start, end);
	}

	/**
	* Returns the number of phs actualses.
	*
	* @return the number of phs actualses
	*/
	public static int getPhsActualsesCount() {
		return getService().getPhsActualsesCount();
	}

	/**
	* Updates the phs actuals in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param phsActuals the phs actuals
	* @return the phs actuals that was updated
	*/
	public static com.stpl.app.model.PhsActuals updatePhsActuals(
		com.stpl.app.model.PhsActuals phsActuals) {
		return getService().updatePhsActuals(phsActuals);
	}

	public static PhsActualsLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PhsActualsLocalService, PhsActualsLocalService> _serviceTracker =
		ServiceTrackerFactory.open(PhsActualsLocalService.class);
}