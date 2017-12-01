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
 * Provides the local service utility for NetSalesFormulaMaster. This utility wraps
 * {@link com.stpl.app.service.impl.NetSalesFormulaMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see NetSalesFormulaMasterLocalService
 * @see com.stpl.app.service.base.NetSalesFormulaMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.NetSalesFormulaMasterLocalServiceImpl
 * @generated
 */
@ProviderType
public class NetSalesFormulaMasterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.NetSalesFormulaMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the net sales formula master to the database. Also notifies the appropriate model listeners.
	*
	* @param netSalesFormulaMaster the net sales formula master
	* @return the net sales formula master that was added
	*/
	public static com.stpl.app.model.NetSalesFormulaMaster addNetSalesFormulaMaster(
		com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster) {
		return getService().addNetSalesFormulaMaster(netSalesFormulaMaster);
	}

	/**
	* Creates a new net sales formula master with the primary key. Does not add the net sales formula master to the database.
	*
	* @param netSalesFormulaMasterSid the primary key for the new net sales formula master
	* @return the new net sales formula master
	*/
	public static com.stpl.app.model.NetSalesFormulaMaster createNetSalesFormulaMaster(
		int netSalesFormulaMasterSid) {
		return getService().createNetSalesFormulaMaster(netSalesFormulaMasterSid);
	}

	/**
	* Deletes the net sales formula master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param netSalesFormulaMasterSid the primary key of the net sales formula master
	* @return the net sales formula master that was removed
	* @throws PortalException if a net sales formula master with the primary key could not be found
	*/
	public static com.stpl.app.model.NetSalesFormulaMaster deleteNetSalesFormulaMaster(
		int netSalesFormulaMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteNetSalesFormulaMaster(netSalesFormulaMasterSid);
	}

	/**
	* Deletes the net sales formula master from the database. Also notifies the appropriate model listeners.
	*
	* @param netSalesFormulaMaster the net sales formula master
	* @return the net sales formula master that was removed
	*/
	public static com.stpl.app.model.NetSalesFormulaMaster deleteNetSalesFormulaMaster(
		com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster) {
		return getService().deleteNetSalesFormulaMaster(netSalesFormulaMaster);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.NetSalesFormulaMaster fetchNetSalesFormulaMaster(
		int netSalesFormulaMasterSid) {
		return getService().fetchNetSalesFormulaMaster(netSalesFormulaMasterSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the net sales formula master with the primary key.
	*
	* @param netSalesFormulaMasterSid the primary key of the net sales formula master
	* @return the net sales formula master
	* @throws PortalException if a net sales formula master with the primary key could not be found
	*/
	public static com.stpl.app.model.NetSalesFormulaMaster getNetSalesFormulaMaster(
		int netSalesFormulaMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getNetSalesFormulaMaster(netSalesFormulaMasterSid);
	}

	/**
	* Returns a range of all the net sales formula masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of net sales formula masters
	* @param end the upper bound of the range of net sales formula masters (not inclusive)
	* @return the range of net sales formula masters
	*/
	public static java.util.List<com.stpl.app.model.NetSalesFormulaMaster> getNetSalesFormulaMasters(
		int start, int end) {
		return getService().getNetSalesFormulaMasters(start, end);
	}

	/**
	* Returns the number of net sales formula masters.
	*
	* @return the number of net sales formula masters
	*/
	public static int getNetSalesFormulaMastersCount() {
		return getService().getNetSalesFormulaMastersCount();
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
	* Updates the net sales formula master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param netSalesFormulaMaster the net sales formula master
	* @return the net sales formula master that was updated
	*/
	public static com.stpl.app.model.NetSalesFormulaMaster updateNetSalesFormulaMaster(
		com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster) {
		return getService().updateNetSalesFormulaMaster(netSalesFormulaMaster);
	}

	public static NetSalesFormulaMasterLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NetSalesFormulaMasterLocalService, NetSalesFormulaMasterLocalService> _serviceTracker =
		ServiceTrackerFactory.open(NetSalesFormulaMasterLocalService.class);
}