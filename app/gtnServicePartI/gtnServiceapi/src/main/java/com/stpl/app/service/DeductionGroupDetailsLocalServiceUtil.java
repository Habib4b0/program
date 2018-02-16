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
 * Provides the local service utility for DeductionGroupDetails. This utility wraps
 * {@link com.stpl.app.service.impl.DeductionGroupDetailsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see DeductionGroupDetailsLocalService
 * @see com.stpl.app.service.base.DeductionGroupDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.DeductionGroupDetailsLocalServiceImpl
 * @generated
 */
@ProviderType
public class DeductionGroupDetailsLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.DeductionGroupDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the deduction group details to the database. Also notifies the appropriate model listeners.
	*
	* @param deductionGroupDetails the deduction group details
	* @return the deduction group details that was added
	*/
	public static com.stpl.app.model.DeductionGroupDetails addDeductionGroupDetails(
		com.stpl.app.model.DeductionGroupDetails deductionGroupDetails) {
		return getService().addDeductionGroupDetails(deductionGroupDetails);
	}

	/**
	* Creates a new deduction group details with the primary key. Does not add the deduction group details to the database.
	*
	* @param deductionGroupDetailsSid the primary key for the new deduction group details
	* @return the new deduction group details
	*/
	public static com.stpl.app.model.DeductionGroupDetails createDeductionGroupDetails(
		int deductionGroupDetailsSid) {
		return getService().createDeductionGroupDetails(deductionGroupDetailsSid);
	}

	/**
	* Deletes the deduction group details from the database. Also notifies the appropriate model listeners.
	*
	* @param deductionGroupDetails the deduction group details
	* @return the deduction group details that was removed
	*/
	public static com.stpl.app.model.DeductionGroupDetails deleteDeductionGroupDetails(
		com.stpl.app.model.DeductionGroupDetails deductionGroupDetails) {
		return getService().deleteDeductionGroupDetails(deductionGroupDetails);
	}

	/**
	* Deletes the deduction group details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deductionGroupDetailsSid the primary key of the deduction group details
	* @return the deduction group details that was removed
	* @throws PortalException if a deduction group details with the primary key could not be found
	*/
	public static com.stpl.app.model.DeductionGroupDetails deleteDeductionGroupDetails(
		int deductionGroupDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDeductionGroupDetails(deductionGroupDetailsSid);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.DeductionGroupDetails fetchDeductionGroupDetails(
		int deductionGroupDetailsSid) {
		return getService().fetchDeductionGroupDetails(deductionGroupDetailsSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the deduction group details with the primary key.
	*
	* @param deductionGroupDetailsSid the primary key of the deduction group details
	* @return the deduction group details
	* @throws PortalException if a deduction group details with the primary key could not be found
	*/
	public static com.stpl.app.model.DeductionGroupDetails getDeductionGroupDetails(
		int deductionGroupDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDeductionGroupDetails(deductionGroupDetailsSid);
	}

	/**
	* Returns a range of all the deduction group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction group detailses
	* @param end the upper bound of the range of deduction group detailses (not inclusive)
	* @return the range of deduction group detailses
	*/
	public static java.util.List<com.stpl.app.model.DeductionGroupDetails> getDeductionGroupDetailses(
		int start, int end) {
		return getService().getDeductionGroupDetailses(start, end);
	}

	/**
	* Returns the number of deduction group detailses.
	*
	* @return the number of deduction group detailses
	*/
	public static int getDeductionGroupDetailsesCount() {
		return getService().getDeductionGroupDetailsesCount();
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
	* Updates the deduction group details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param deductionGroupDetails the deduction group details
	* @return the deduction group details that was updated
	*/
	public static com.stpl.app.model.DeductionGroupDetails updateDeductionGroupDetails(
		com.stpl.app.model.DeductionGroupDetails deductionGroupDetails) {
		return getService().updateDeductionGroupDetails(deductionGroupDetails);
	}

	public static DeductionGroupDetailsLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DeductionGroupDetailsLocalService, DeductionGroupDetailsLocalService> _serviceTracker =
		ServiceTrackerFactory.open(DeductionGroupDetailsLocalService.class);
}