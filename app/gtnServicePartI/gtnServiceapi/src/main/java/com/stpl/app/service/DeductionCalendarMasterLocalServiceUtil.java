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
 * Provides the local service utility for DeductionCalendarMaster. This utility wraps
 * {@link com.stpl.app.service.impl.DeductionCalendarMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see DeductionCalendarMasterLocalService
 * @see com.stpl.app.service.base.DeductionCalendarMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.DeductionCalendarMasterLocalServiceImpl
 * @generated
 */
@ProviderType
public class DeductionCalendarMasterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.DeductionCalendarMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the deduction calendar master to the database. Also notifies the appropriate model listeners.
	*
	* @param deductionCalendarMaster the deduction calendar master
	* @return the deduction calendar master that was added
	*/
	public static com.stpl.app.model.DeductionCalendarMaster addDeductionCalendarMaster(
		com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster) {
		return getService().addDeductionCalendarMaster(deductionCalendarMaster);
	}

	/**
	* Creates a new deduction calendar master with the primary key. Does not add the deduction calendar master to the database.
	*
	* @param deductionCalendarMasterSid the primary key for the new deduction calendar master
	* @return the new deduction calendar master
	*/
	public static com.stpl.app.model.DeductionCalendarMaster createDeductionCalendarMaster(
		int deductionCalendarMasterSid) {
		return getService()
				   .createDeductionCalendarMaster(deductionCalendarMasterSid);
	}

	/**
	* Deletes the deduction calendar master from the database. Also notifies the appropriate model listeners.
	*
	* @param deductionCalendarMaster the deduction calendar master
	* @return the deduction calendar master that was removed
	*/
	public static com.stpl.app.model.DeductionCalendarMaster deleteDeductionCalendarMaster(
		com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster) {
		return getService()
				   .deleteDeductionCalendarMaster(deductionCalendarMaster);
	}

	/**
	* Deletes the deduction calendar master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deductionCalendarMasterSid the primary key of the deduction calendar master
	* @return the deduction calendar master that was removed
	* @throws PortalException if a deduction calendar master with the primary key could not be found
	*/
	public static com.stpl.app.model.DeductionCalendarMaster deleteDeductionCalendarMaster(
		int deductionCalendarMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteDeductionCalendarMaster(deductionCalendarMasterSid);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.DeductionCalendarMaster fetchDeductionCalendarMaster(
		int deductionCalendarMasterSid) {
		return getService()
				   .fetchDeductionCalendarMaster(deductionCalendarMasterSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the deduction calendar master with the primary key.
	*
	* @param deductionCalendarMasterSid the primary key of the deduction calendar master
	* @return the deduction calendar master
	* @throws PortalException if a deduction calendar master with the primary key could not be found
	*/
	public static com.stpl.app.model.DeductionCalendarMaster getDeductionCalendarMaster(
		int deductionCalendarMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getDeductionCalendarMaster(deductionCalendarMasterSid);
	}

	/**
	* Returns a range of all the deduction calendar masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction calendar masters
	* @param end the upper bound of the range of deduction calendar masters (not inclusive)
	* @return the range of deduction calendar masters
	*/
	public static java.util.List<com.stpl.app.model.DeductionCalendarMaster> getDeductionCalendarMasters(
		int start, int end) {
		return getService().getDeductionCalendarMasters(start, end);
	}

	/**
	* Returns the number of deduction calendar masters.
	*
	* @return the number of deduction calendar masters
	*/
	public static int getDeductionCalendarMastersCount() {
		return getService().getDeductionCalendarMastersCount();
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
	* Updates the deduction calendar master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param deductionCalendarMaster the deduction calendar master
	* @return the deduction calendar master that was updated
	*/
	public static com.stpl.app.model.DeductionCalendarMaster updateDeductionCalendarMaster(
		com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster) {
		return getService()
				   .updateDeductionCalendarMaster(deductionCalendarMaster);
	}

	public static DeductionCalendarMasterLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DeductionCalendarMasterLocalService, DeductionCalendarMasterLocalService> _serviceTracker =
		ServiceTrackerFactory.open(DeductionCalendarMasterLocalService.class);
}