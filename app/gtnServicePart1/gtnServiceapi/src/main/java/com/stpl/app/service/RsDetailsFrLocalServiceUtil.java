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
 * Provides the local service utility for RsDetailsFr. This utility wraps
 * {@link com.stpl.app.service.impl.RsDetailsFrLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see RsDetailsFrLocalService
 * @see com.stpl.app.service.base.RsDetailsFrLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.RsDetailsFrLocalServiceImpl
 * @generated
 */
@ProviderType
public class RsDetailsFrLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.RsDetailsFrLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the rs details fr to the database. Also notifies the appropriate model listeners.
	*
	* @param rsDetailsFr the rs details fr
	* @return the rs details fr that was added
	*/
	public static com.stpl.app.model.RsDetailsFr addRsDetailsFr(
		com.stpl.app.model.RsDetailsFr rsDetailsFr) {
		return getService().addRsDetailsFr(rsDetailsFr);
	}

	/**
	* Creates a new rs details fr with the primary key. Does not add the rs details fr to the database.
	*
	* @param rsDetailsFrSid the primary key for the new rs details fr
	* @return the new rs details fr
	*/
	public static com.stpl.app.model.RsDetailsFr createRsDetailsFr(
		int rsDetailsFrSid) {
		return getService().createRsDetailsFr(rsDetailsFrSid);
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
	* Deletes the rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsDetailsFrSid the primary key of the rs details fr
	* @return the rs details fr that was removed
	* @throws PortalException if a rs details fr with the primary key could not be found
	*/
	public static com.stpl.app.model.RsDetailsFr deleteRsDetailsFr(
		int rsDetailsFrSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRsDetailsFr(rsDetailsFrSid);
	}

	/**
	* Deletes the rs details fr from the database. Also notifies the appropriate model listeners.
	*
	* @param rsDetailsFr the rs details fr
	* @return the rs details fr that was removed
	*/
	public static com.stpl.app.model.RsDetailsFr deleteRsDetailsFr(
		com.stpl.app.model.RsDetailsFr rsDetailsFr) {
		return getService().deleteRsDetailsFr(rsDetailsFr);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.RsDetailsFr fetchRsDetailsFr(
		int rsDetailsFrSid) {
		return getService().fetchRsDetailsFr(rsDetailsFrSid);
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
	* Returns the rs details fr with the primary key.
	*
	* @param rsDetailsFrSid the primary key of the rs details fr
	* @return the rs details fr
	* @throws PortalException if a rs details fr with the primary key could not be found
	*/
	public static com.stpl.app.model.RsDetailsFr getRsDetailsFr(
		int rsDetailsFrSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRsDetailsFr(rsDetailsFrSid);
	}

	/**
	* Returns a range of all the rs details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs details frs
	* @param end the upper bound of the range of rs details frs (not inclusive)
	* @return the range of rs details frs
	*/
	public static java.util.List<com.stpl.app.model.RsDetailsFr> getRsDetailsFrs(
		int start, int end) {
		return getService().getRsDetailsFrs(start, end);
	}

	/**
	* Returns the number of rs details frs.
	*
	* @return the number of rs details frs
	*/
	public static int getRsDetailsFrsCount() {
		return getService().getRsDetailsFrsCount();
	}

	/**
	* Updates the rs details fr in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rsDetailsFr the rs details fr
	* @return the rs details fr that was updated
	*/
	public static com.stpl.app.model.RsDetailsFr updateRsDetailsFr(
		com.stpl.app.model.RsDetailsFr rsDetailsFr) {
		return getService().updateRsDetailsFr(rsDetailsFr);
	}

	public static RsDetailsFrLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RsDetailsFrLocalService, RsDetailsFrLocalService> _serviceTracker =
		ServiceTrackerFactory.open(RsDetailsFrLocalService.class);
}