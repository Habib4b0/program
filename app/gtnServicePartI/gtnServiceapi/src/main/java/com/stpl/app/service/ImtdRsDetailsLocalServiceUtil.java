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
 * Provides the local service utility for ImtdRsDetails. This utility wraps
 * {@link com.stpl.app.service.impl.ImtdRsDetailsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ImtdRsDetailsLocalService
 * @see com.stpl.app.service.base.ImtdRsDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ImtdRsDetailsLocalServiceImpl
 * @generated
 */
@ProviderType
public class ImtdRsDetailsLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ImtdRsDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the imtd rs details to the database. Also notifies the appropriate model listeners.
	*
	* @param imtdRsDetails the imtd rs details
	* @return the imtd rs details that was added
	*/
	public static com.stpl.app.model.ImtdRsDetails addImtdRsDetails(
		com.stpl.app.model.ImtdRsDetails imtdRsDetails) {
		return getService().addImtdRsDetails(imtdRsDetails);
	}

	/**
	* Creates a new imtd rs details with the primary key. Does not add the imtd rs details to the database.
	*
	* @param imtdRsDetailsSid the primary key for the new imtd rs details
	* @return the new imtd rs details
	*/
	public static com.stpl.app.model.ImtdRsDetails createImtdRsDetails(
		int imtdRsDetailsSid) {
		return getService().createImtdRsDetails(imtdRsDetailsSid);
	}

	/**
	* Deletes the imtd rs details from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdRsDetails the imtd rs details
	* @return the imtd rs details that was removed
	*/
	public static com.stpl.app.model.ImtdRsDetails deleteImtdRsDetails(
		com.stpl.app.model.ImtdRsDetails imtdRsDetails) {
		return getService().deleteImtdRsDetails(imtdRsDetails);
	}

	/**
	* Deletes the imtd rs details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdRsDetailsSid the primary key of the imtd rs details
	* @return the imtd rs details that was removed
	* @throws PortalException if a imtd rs details with the primary key could not be found
	*/
	public static com.stpl.app.model.ImtdRsDetails deleteImtdRsDetails(
		int imtdRsDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteImtdRsDetails(imtdRsDetailsSid);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.ImtdRsDetails fetchImtdRsDetails(
		int imtdRsDetailsSid) {
		return getService().fetchImtdRsDetails(imtdRsDetailsSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the imtd rs details with the primary key.
	*
	* @param imtdRsDetailsSid the primary key of the imtd rs details
	* @return the imtd rs details
	* @throws PortalException if a imtd rs details with the primary key could not be found
	*/
	public static com.stpl.app.model.ImtdRsDetails getImtdRsDetails(
		int imtdRsDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getImtdRsDetails(imtdRsDetailsSid);
	}

	/**
	* Returns a range of all the imtd rs detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd rs detailses
	* @param end the upper bound of the range of imtd rs detailses (not inclusive)
	* @return the range of imtd rs detailses
	*/
	public static java.util.List<com.stpl.app.model.ImtdRsDetails> getImtdRsDetailses(
		int start, int end) {
		return getService().getImtdRsDetailses(start, end);
	}

	/**
	* Returns the number of imtd rs detailses.
	*
	* @return the number of imtd rs detailses
	*/
	public static int getImtdRsDetailsesCount() {
		return getService().getImtdRsDetailsesCount();
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
	* Updates the imtd rs details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param imtdRsDetails the imtd rs details
	* @return the imtd rs details that was updated
	*/
	public static com.stpl.app.model.ImtdRsDetails updateImtdRsDetails(
		com.stpl.app.model.ImtdRsDetails imtdRsDetails) {
		return getService().updateImtdRsDetails(imtdRsDetails);
	}

	public static ImtdRsDetailsLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImtdRsDetailsLocalService, ImtdRsDetailsLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ImtdRsDetailsLocalService.class);
}