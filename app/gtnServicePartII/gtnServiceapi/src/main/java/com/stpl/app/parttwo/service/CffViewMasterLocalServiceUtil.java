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
 * Provides the local service utility for CffViewMaster. This utility wraps
 * {@link com.stpl.app.parttwo.service.impl.CffViewMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see CffViewMasterLocalService
 * @see com.stpl.app.parttwo.service.base.CffViewMasterLocalServiceBaseImpl
 * @see com.stpl.app.parttwo.service.impl.CffViewMasterLocalServiceImpl
 * @generated
 */
@ProviderType
public class CffViewMasterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.parttwo.service.impl.CffViewMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the cff view master to the database. Also notifies the appropriate model listeners.
	*
	* @param cffViewMaster the cff view master
	* @return the cff view master that was added
	*/
	public static com.stpl.app.parttwo.model.CffViewMaster addCffViewMaster(
		com.stpl.app.parttwo.model.CffViewMaster cffViewMaster) {
		return getService().addCffViewMaster(cffViewMaster);
	}

	/**
	* Creates a new cff view master with the primary key. Does not add the cff view master to the database.
	*
	* @param cffViewMasterSid the primary key for the new cff view master
	* @return the new cff view master
	*/
	public static com.stpl.app.parttwo.model.CffViewMaster createCffViewMaster(
		int cffViewMasterSid) {
		return getService().createCffViewMaster(cffViewMasterSid);
	}

	/**
	* Deletes the cff view master from the database. Also notifies the appropriate model listeners.
	*
	* @param cffViewMaster the cff view master
	* @return the cff view master that was removed
	*/
	public static com.stpl.app.parttwo.model.CffViewMaster deleteCffViewMaster(
		com.stpl.app.parttwo.model.CffViewMaster cffViewMaster) {
		return getService().deleteCffViewMaster(cffViewMaster);
	}

	/**
	* Deletes the cff view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffViewMasterSid the primary key of the cff view master
	* @return the cff view master that was removed
	* @throws PortalException if a cff view master with the primary key could not be found
	*/
	public static com.stpl.app.parttwo.model.CffViewMaster deleteCffViewMaster(
		int cffViewMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCffViewMaster(cffViewMasterSid);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.parttwo.model.CffViewMaster fetchCffViewMaster(
		int cffViewMasterSid) {
		return getService().fetchCffViewMaster(cffViewMasterSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the cff view master with the primary key.
	*
	* @param cffViewMasterSid the primary key of the cff view master
	* @return the cff view master
	* @throws PortalException if a cff view master with the primary key could not be found
	*/
	public static com.stpl.app.parttwo.model.CffViewMaster getCffViewMaster(
		int cffViewMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCffViewMaster(cffViewMasterSid);
	}

	/**
	* Returns a range of all the cff view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff view masters
	* @param end the upper bound of the range of cff view masters (not inclusive)
	* @return the range of cff view masters
	*/
	public static java.util.List<com.stpl.app.parttwo.model.CffViewMaster> getCffViewMasters(
		int start, int end) {
		return getService().getCffViewMasters(start, end);
	}

	/**
	* Returns the number of cff view masters.
	*
	* @return the number of cff view masters
	*/
	public static int getCffViewMastersCount() {
		return getService().getCffViewMastersCount();
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
	* Updates the cff view master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cffViewMaster the cff view master
	* @return the cff view master that was updated
	*/
	public static com.stpl.app.parttwo.model.CffViewMaster updateCffViewMaster(
		com.stpl.app.parttwo.model.CffViewMaster cffViewMaster) {
		return getService().updateCffViewMaster(cffViewMaster);
	}

	public static CffViewMasterLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CffViewMasterLocalService, CffViewMasterLocalService> _serviceTracker =
		ServiceTrackerFactory.open(CffViewMasterLocalService.class);
}