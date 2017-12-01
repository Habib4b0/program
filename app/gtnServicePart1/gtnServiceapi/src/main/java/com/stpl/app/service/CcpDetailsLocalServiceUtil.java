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
 * Provides the local service utility for CcpDetails. This utility wraps
 * {@link com.stpl.app.service.impl.CcpDetailsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see CcpDetailsLocalService
 * @see com.stpl.app.service.base.CcpDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.CcpDetailsLocalServiceImpl
 * @generated
 */
@ProviderType
public class CcpDetailsLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.CcpDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ccp details to the database. Also notifies the appropriate model listeners.
	*
	* @param ccpDetails the ccp details
	* @return the ccp details that was added
	*/
	public static com.stpl.app.model.CcpDetails addCcpDetails(
		com.stpl.app.model.CcpDetails ccpDetails) {
		return getService().addCcpDetails(ccpDetails);
	}

	/**
	* Creates a new ccp details with the primary key. Does not add the ccp details to the database.
	*
	* @param ccpDetailsSid the primary key for the new ccp details
	* @return the new ccp details
	*/
	public static com.stpl.app.model.CcpDetails createCcpDetails(
		int ccpDetailsSid) {
		return getService().createCcpDetails(ccpDetailsSid);
	}

	/**
	* Deletes the ccp details from the database. Also notifies the appropriate model listeners.
	*
	* @param ccpDetails the ccp details
	* @return the ccp details that was removed
	*/
	public static com.stpl.app.model.CcpDetails deleteCcpDetails(
		com.stpl.app.model.CcpDetails ccpDetails) {
		return getService().deleteCcpDetails(ccpDetails);
	}

	/**
	* Deletes the ccp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ccpDetailsSid the primary key of the ccp details
	* @return the ccp details that was removed
	* @throws PortalException if a ccp details with the primary key could not be found
	*/
	public static com.stpl.app.model.CcpDetails deleteCcpDetails(
		int ccpDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCcpDetails(ccpDetailsSid);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.CcpDetails fetchCcpDetails(
		int ccpDetailsSid) {
		return getService().fetchCcpDetails(ccpDetailsSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the ccp details with the primary key.
	*
	* @param ccpDetailsSid the primary key of the ccp details
	* @return the ccp details
	* @throws PortalException if a ccp details with the primary key could not be found
	*/
	public static com.stpl.app.model.CcpDetails getCcpDetails(int ccpDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCcpDetails(ccpDetailsSid);
	}

	/**
	* Returns a range of all the ccp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ccp detailses
	* @param end the upper bound of the range of ccp detailses (not inclusive)
	* @return the range of ccp detailses
	*/
	public static java.util.List<com.stpl.app.model.CcpDetails> getCcpDetailses(
		int start, int end) {
		return getService().getCcpDetailses(start, end);
	}

	/**
	* Returns the number of ccp detailses.
	*
	* @return the number of ccp detailses
	*/
	public static int getCcpDetailsesCount() {
		return getService().getCcpDetailsesCount();
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
	* Updates the ccp details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ccpDetails the ccp details
	* @return the ccp details that was updated
	*/
	public static com.stpl.app.model.CcpDetails updateCcpDetails(
		com.stpl.app.model.CcpDetails ccpDetails) {
		return getService().updateCcpDetails(ccpDetails);
	}

	public static CcpDetailsLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CcpDetailsLocalService, CcpDetailsLocalService> _serviceTracker =
		ServiceTrackerFactory.open(CcpDetailsLocalService.class);
}