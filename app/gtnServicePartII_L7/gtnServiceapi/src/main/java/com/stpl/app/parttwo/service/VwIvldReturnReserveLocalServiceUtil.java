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

package com.stpl.app.parttwo.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for VwIvldReturnReserve. This utility wraps
 * {@link com.stpl.app.parttwo.service.impl.VwIvldReturnReserveLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see VwIvldReturnReserveLocalService
 * @see com.stpl.app.parttwo.service.base.VwIvldReturnReserveLocalServiceBaseImpl
 * @see com.stpl.app.parttwo.service.impl.VwIvldReturnReserveLocalServiceImpl
 * @generated
 */
@ProviderType
public class VwIvldReturnReserveLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.parttwo.service.impl.VwIvldReturnReserveLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the vw ivld return reserve to the database. Also notifies the appropriate model listeners.
	*
	* @param vwIvldReturnReserve the vw ivld return reserve
	* @return the vw ivld return reserve that was added
	*/
	public static com.stpl.app.parttwo.model.VwIvldReturnReserve addVwIvldReturnReserve(
		com.stpl.app.parttwo.model.VwIvldReturnReserve vwIvldReturnReserve) {
		return getService().addVwIvldReturnReserve(vwIvldReturnReserve);
	}

	/**
	* Creates a new vw ivld return reserve with the primary key. Does not add the vw ivld return reserve to the database.
	*
	* @param ivldReturnReserveSid the primary key for the new vw ivld return reserve
	* @return the new vw ivld return reserve
	*/
	public static com.stpl.app.parttwo.model.VwIvldReturnReserve createVwIvldReturnReserve(
		int ivldReturnReserveSid) {
		return getService().createVwIvldReturnReserve(ivldReturnReserveSid);
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
	* Deletes the vw ivld return reserve with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldReturnReserveSid the primary key of the vw ivld return reserve
	* @return the vw ivld return reserve that was removed
	* @throws PortalException if a vw ivld return reserve with the primary key could not be found
	*/
	public static com.stpl.app.parttwo.model.VwIvldReturnReserve deleteVwIvldReturnReserve(
		int ivldReturnReserveSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteVwIvldReturnReserve(ivldReturnReserveSid);
	}

	/**
	* Deletes the vw ivld return reserve from the database. Also notifies the appropriate model listeners.
	*
	* @param vwIvldReturnReserve the vw ivld return reserve
	* @return the vw ivld return reserve that was removed
	*/
	public static com.stpl.app.parttwo.model.VwIvldReturnReserve deleteVwIvldReturnReserve(
		com.stpl.app.parttwo.model.VwIvldReturnReserve vwIvldReturnReserve) {
		return getService().deleteVwIvldReturnReserve(vwIvldReturnReserve);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.parttwo.model.VwIvldReturnReserve fetchVwIvldReturnReserve(
		int ivldReturnReserveSid) {
		return getService().fetchVwIvldReturnReserve(ivldReturnReserveSid);
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
	* Returns the vw ivld return reserve with the primary key.
	*
	* @param ivldReturnReserveSid the primary key of the vw ivld return reserve
	* @return the vw ivld return reserve
	* @throws PortalException if a vw ivld return reserve with the primary key could not be found
	*/
	public static com.stpl.app.parttwo.model.VwIvldReturnReserve getVwIvldReturnReserve(
		int ivldReturnReserveSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getVwIvldReturnReserve(ivldReturnReserveSid);
	}

	/**
	* Returns a range of all the vw ivld return reserves.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw ivld return reserves
	* @param end the upper bound of the range of vw ivld return reserves (not inclusive)
	* @return the range of vw ivld return reserves
	*/
	public static java.util.List<com.stpl.app.parttwo.model.VwIvldReturnReserve> getVwIvldReturnReserves(
		int start, int end) {
		return getService().getVwIvldReturnReserves(start, end);
	}

	/**
	* Returns the number of vw ivld return reserves.
	*
	* @return the number of vw ivld return reserves
	*/
	public static int getVwIvldReturnReservesCount() {
		return getService().getVwIvldReturnReservesCount();
	}

	/**
	* Updates the vw ivld return reserve in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param vwIvldReturnReserve the vw ivld return reserve
	* @return the vw ivld return reserve that was updated
	*/
	public static com.stpl.app.parttwo.model.VwIvldReturnReserve updateVwIvldReturnReserve(
		com.stpl.app.parttwo.model.VwIvldReturnReserve vwIvldReturnReserve) {
		return getService().updateVwIvldReturnReserve(vwIvldReturnReserve);
	}

	public static VwIvldReturnReserveLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwIvldReturnReserveLocalService, VwIvldReturnReserveLocalService> _serviceTracker =
		ServiceTrackerFactory.open(VwIvldReturnReserveLocalService.class);
}