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
 * Provides the local service utility for NationalAssumptionsActuals. This utility wraps
 * {@link com.stpl.app.service.impl.NationalAssumptionsActualsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see NationalAssumptionsActualsLocalService
 * @see com.stpl.app.service.base.NationalAssumptionsActualsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.NationalAssumptionsActualsLocalServiceImpl
 * @generated
 */
@ProviderType
public class NationalAssumptionsActualsLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.NationalAssumptionsActualsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the national assumptions actuals to the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActuals the national assumptions actuals
	* @return the national assumptions actuals that was added
	*/
	public static com.stpl.app.model.NationalAssumptionsActuals addNationalAssumptionsActuals(
		com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals) {
		return getService()
				   .addNationalAssumptionsActuals(nationalAssumptionsActuals);
	}

	/**
	* Creates a new national assumptions actuals with the primary key. Does not add the national assumptions actuals to the database.
	*
	* @param nationalAssumptionsActualsPK the primary key for the new national assumptions actuals
	* @return the new national assumptions actuals
	*/
	public static com.stpl.app.model.NationalAssumptionsActuals createNationalAssumptionsActuals(
		com.stpl.app.service.persistence.NationalAssumptionsActualsPK nationalAssumptionsActualsPK) {
		return getService()
				   .createNationalAssumptionsActuals(nationalAssumptionsActualsPK);
	}

	/**
	* Deletes the national assumptions actuals from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActuals the national assumptions actuals
	* @return the national assumptions actuals that was removed
	*/
	public static com.stpl.app.model.NationalAssumptionsActuals deleteNationalAssumptionsActuals(
		com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals) {
		return getService()
				   .deleteNationalAssumptionsActuals(nationalAssumptionsActuals);
	}

	/**
	* Deletes the national assumptions actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
	* @return the national assumptions actuals that was removed
	* @throws PortalException if a national assumptions actuals with the primary key could not be found
	*/
	public static com.stpl.app.model.NationalAssumptionsActuals deleteNationalAssumptionsActuals(
		com.stpl.app.service.persistence.NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteNationalAssumptionsActuals(nationalAssumptionsActualsPK);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.NationalAssumptionsActuals fetchNationalAssumptionsActuals(
		com.stpl.app.service.persistence.NationalAssumptionsActualsPK nationalAssumptionsActualsPK) {
		return getService()
				   .fetchNationalAssumptionsActuals(nationalAssumptionsActualsPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the national assumptions actuals with the primary key.
	*
	* @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
	* @return the national assumptions actuals
	* @throws PortalException if a national assumptions actuals with the primary key could not be found
	*/
	public static com.stpl.app.model.NationalAssumptionsActuals getNationalAssumptionsActuals(
		com.stpl.app.service.persistence.NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getNationalAssumptionsActuals(nationalAssumptionsActualsPK);
	}

	/**
	* Returns a range of all the national assumptions actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptions actualses
	* @param end the upper bound of the range of national assumptions actualses (not inclusive)
	* @return the range of national assumptions actualses
	*/
	public static java.util.List<com.stpl.app.model.NationalAssumptionsActuals> getNationalAssumptionsActualses(
		int start, int end) {
		return getService().getNationalAssumptionsActualses(start, end);
	}

	/**
	* Returns the number of national assumptions actualses.
	*
	* @return the number of national assumptions actualses
	*/
	public static int getNationalAssumptionsActualsesCount() {
		return getService().getNationalAssumptionsActualsesCount();
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
	* Updates the national assumptions actuals in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActuals the national assumptions actuals
	* @return the national assumptions actuals that was updated
	*/
	public static com.stpl.app.model.NationalAssumptionsActuals updateNationalAssumptionsActuals(
		com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals) {
		return getService()
				   .updateNationalAssumptionsActuals(nationalAssumptionsActuals);
	}

	public static NationalAssumptionsActualsLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NationalAssumptionsActualsLocalService, NationalAssumptionsActualsLocalService> _serviceTracker =
		ServiceTrackerFactory.open(NationalAssumptionsActualsLocalService.class);
}