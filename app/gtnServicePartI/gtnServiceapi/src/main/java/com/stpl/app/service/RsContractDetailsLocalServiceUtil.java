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
 * Provides the local service utility for RsContractDetails. This utility wraps
 * {@link com.stpl.app.service.impl.RsContractDetailsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see RsContractDetailsLocalService
 * @see com.stpl.app.service.base.RsContractDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.RsContractDetailsLocalServiceImpl
 * @generated
 */
@ProviderType
public class RsContractDetailsLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.RsContractDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the rs contract details to the database. Also notifies the appropriate model listeners.
	*
	* @param rsContractDetails the rs contract details
	* @return the rs contract details that was added
	*/
	public static com.stpl.app.model.RsContractDetails addRsContractDetails(
		com.stpl.app.model.RsContractDetails rsContractDetails) {
		return getService().addRsContractDetails(rsContractDetails);
	}

	/**
	* Creates a new rs contract details with the primary key. Does not add the rs contract details to the database.
	*
	* @param rsContractDetailsSid the primary key for the new rs contract details
	* @return the new rs contract details
	*/
	public static com.stpl.app.model.RsContractDetails createRsContractDetails(
		int rsContractDetailsSid) {
		return getService().createRsContractDetails(rsContractDetailsSid);
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
	* Deletes the rs contract details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsContractDetailsSid the primary key of the rs contract details
	* @return the rs contract details that was removed
	* @throws PortalException if a rs contract details with the primary key could not be found
	*/
	public static com.stpl.app.model.RsContractDetails deleteRsContractDetails(
		int rsContractDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRsContractDetails(rsContractDetailsSid);
	}

	/**
	* Deletes the rs contract details from the database. Also notifies the appropriate model listeners.
	*
	* @param rsContractDetails the rs contract details
	* @return the rs contract details that was removed
	*/
	public static com.stpl.app.model.RsContractDetails deleteRsContractDetails(
		com.stpl.app.model.RsContractDetails rsContractDetails) {
		return getService().deleteRsContractDetails(rsContractDetails);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.RsContractDetails fetchRsContractDetails(
		int rsContractDetailsSid) {
		return getService().fetchRsContractDetails(rsContractDetailsSid);
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
	* Returns the rs contract details with the primary key.
	*
	* @param rsContractDetailsSid the primary key of the rs contract details
	* @return the rs contract details
	* @throws PortalException if a rs contract details with the primary key could not be found
	*/
	public static com.stpl.app.model.RsContractDetails getRsContractDetails(
		int rsContractDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRsContractDetails(rsContractDetailsSid);
	}

	/**
	* Returns a range of all the rs contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @return the range of rs contract detailses
	*/
	public static java.util.List<com.stpl.app.model.RsContractDetails> getRsContractDetailses(
		int start, int end) {
		return getService().getRsContractDetailses(start, end);
	}

	/**
	* Returns the number of rs contract detailses.
	*
	* @return the number of rs contract detailses
	*/
	public static int getRsContractDetailsesCount() {
		return getService().getRsContractDetailsesCount();
	}

	/**
	* Updates the rs contract details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rsContractDetails the rs contract details
	* @return the rs contract details that was updated
	*/
	public static com.stpl.app.model.RsContractDetails updateRsContractDetails(
		com.stpl.app.model.RsContractDetails rsContractDetails) {
		return getService().updateRsContractDetails(rsContractDetails);
	}

	public static RsContractDetailsLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RsContractDetailsLocalService, RsContractDetailsLocalService> _serviceTracker =
		ServiceTrackerFactory.open(RsContractDetailsLocalService.class);
}