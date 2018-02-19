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
 * Provides the local service utility for NmDiscountProjection. This utility wraps
 * {@link com.stpl.app.service.impl.NmDiscountProjectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see NmDiscountProjectionLocalService
 * @see com.stpl.app.service.base.NmDiscountProjectionLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.NmDiscountProjectionLocalServiceImpl
 * @generated
 */
@ProviderType
public class NmDiscountProjectionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.NmDiscountProjectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the nm discount projection to the database. Also notifies the appropriate model listeners.
	*
	* @param nmDiscountProjection the nm discount projection
	* @return the nm discount projection that was added
	*/
	public static com.stpl.app.model.NmDiscountProjection addNmDiscountProjection(
		com.stpl.app.model.NmDiscountProjection nmDiscountProjection) {
		return getService().addNmDiscountProjection(nmDiscountProjection);
	}

	/**
	* Creates a new nm discount projection with the primary key. Does not add the nm discount projection to the database.
	*
	* @param nmDiscountProjectionPK the primary key for the new nm discount projection
	* @return the new nm discount projection
	*/
	public static com.stpl.app.model.NmDiscountProjection createNmDiscountProjection(
		com.stpl.app.service.persistence.NmDiscountProjectionPK nmDiscountProjectionPK) {
		return getService().createNmDiscountProjection(nmDiscountProjectionPK);
	}

	/**
	* Deletes the nm discount projection from the database. Also notifies the appropriate model listeners.
	*
	* @param nmDiscountProjection the nm discount projection
	* @return the nm discount projection that was removed
	*/
	public static com.stpl.app.model.NmDiscountProjection deleteNmDiscountProjection(
		com.stpl.app.model.NmDiscountProjection nmDiscountProjection) {
		return getService().deleteNmDiscountProjection(nmDiscountProjection);
	}

	/**
	* Deletes the nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmDiscountProjectionPK the primary key of the nm discount projection
	* @return the nm discount projection that was removed
	* @throws PortalException if a nm discount projection with the primary key could not be found
	*/
	public static com.stpl.app.model.NmDiscountProjection deleteNmDiscountProjection(
		com.stpl.app.service.persistence.NmDiscountProjectionPK nmDiscountProjectionPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteNmDiscountProjection(nmDiscountProjectionPK);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.NmDiscountProjection fetchNmDiscountProjection(
		com.stpl.app.service.persistence.NmDiscountProjectionPK nmDiscountProjectionPK) {
		return getService().fetchNmDiscountProjection(nmDiscountProjectionPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the nm discount projection with the primary key.
	*
	* @param nmDiscountProjectionPK the primary key of the nm discount projection
	* @return the nm discount projection
	* @throws PortalException if a nm discount projection with the primary key could not be found
	*/
	public static com.stpl.app.model.NmDiscountProjection getNmDiscountProjection(
		com.stpl.app.service.persistence.NmDiscountProjectionPK nmDiscountProjectionPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getNmDiscountProjection(nmDiscountProjectionPK);
	}

	/**
	* Returns a range of all the nm discount projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm discount projections
	* @param end the upper bound of the range of nm discount projections (not inclusive)
	* @return the range of nm discount projections
	*/
	public static java.util.List<com.stpl.app.model.NmDiscountProjection> getNmDiscountProjections(
		int start, int end) {
		return getService().getNmDiscountProjections(start, end);
	}

	/**
	* Returns the number of nm discount projections.
	*
	* @return the number of nm discount projections
	*/
	public static int getNmDiscountProjectionsCount() {
		return getService().getNmDiscountProjectionsCount();
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
	* Updates the nm discount projection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nmDiscountProjection the nm discount projection
	* @return the nm discount projection that was updated
	*/
	public static com.stpl.app.model.NmDiscountProjection updateNmDiscountProjection(
		com.stpl.app.model.NmDiscountProjection nmDiscountProjection) {
		return getService().updateNmDiscountProjection(nmDiscountProjection);
	}

	public static NmDiscountProjectionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NmDiscountProjectionLocalService, NmDiscountProjectionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(NmDiscountProjectionLocalService.class);
}