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
 * Provides the local service utility for ChSalesProjection. This utility wraps
 * {@link com.stpl.app.service.impl.ChSalesProjectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ChSalesProjectionLocalService
 * @see com.stpl.app.service.base.ChSalesProjectionLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ChSalesProjectionLocalServiceImpl
 * @generated
 */
@ProviderType
public class ChSalesProjectionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ChSalesProjectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ch sales projection to the database. Also notifies the appropriate model listeners.
	*
	* @param chSalesProjection the ch sales projection
	* @return the ch sales projection that was added
	*/
	public static com.stpl.app.model.ChSalesProjection addChSalesProjection(
		com.stpl.app.model.ChSalesProjection chSalesProjection) {
		return getService().addChSalesProjection(chSalesProjection);
	}

	/**
	* Creates a new ch sales projection with the primary key. Does not add the ch sales projection to the database.
	*
	* @param chSalesProjectionPK the primary key for the new ch sales projection
	* @return the new ch sales projection
	*/
	public static com.stpl.app.model.ChSalesProjection createChSalesProjection(
		com.stpl.app.service.persistence.ChSalesProjectionPK chSalesProjectionPK) {
		return getService().createChSalesProjection(chSalesProjectionPK);
	}

	/**
	* Deletes the ch sales projection from the database. Also notifies the appropriate model listeners.
	*
	* @param chSalesProjection the ch sales projection
	* @return the ch sales projection that was removed
	*/
	public static com.stpl.app.model.ChSalesProjection deleteChSalesProjection(
		com.stpl.app.model.ChSalesProjection chSalesProjection) {
		return getService().deleteChSalesProjection(chSalesProjection);
	}

	/**
	* Deletes the ch sales projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chSalesProjectionPK the primary key of the ch sales projection
	* @return the ch sales projection that was removed
	* @throws PortalException if a ch sales projection with the primary key could not be found
	*/
	public static com.stpl.app.model.ChSalesProjection deleteChSalesProjection(
		com.stpl.app.service.persistence.ChSalesProjectionPK chSalesProjectionPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteChSalesProjection(chSalesProjectionPK);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.ChSalesProjection fetchChSalesProjection(
		com.stpl.app.service.persistence.ChSalesProjectionPK chSalesProjectionPK) {
		return getService().fetchChSalesProjection(chSalesProjectionPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the ch sales projection with the primary key.
	*
	* @param chSalesProjectionPK the primary key of the ch sales projection
	* @return the ch sales projection
	* @throws PortalException if a ch sales projection with the primary key could not be found
	*/
	public static com.stpl.app.model.ChSalesProjection getChSalesProjection(
		com.stpl.app.service.persistence.ChSalesProjectionPK chSalesProjectionPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getChSalesProjection(chSalesProjectionPK);
	}

	/**
	* Returns a range of all the ch sales projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch sales projections
	* @param end the upper bound of the range of ch sales projections (not inclusive)
	* @return the range of ch sales projections
	*/
	public static java.util.List<com.stpl.app.model.ChSalesProjection> getChSalesProjections(
		int start, int end) {
		return getService().getChSalesProjections(start, end);
	}

	/**
	* Returns the number of ch sales projections.
	*
	* @return the number of ch sales projections
	*/
	public static int getChSalesProjectionsCount() {
		return getService().getChSalesProjectionsCount();
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
	* Updates the ch sales projection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param chSalesProjection the ch sales projection
	* @return the ch sales projection that was updated
	*/
	public static com.stpl.app.model.ChSalesProjection updateChSalesProjection(
		com.stpl.app.model.ChSalesProjection chSalesProjection) {
		return getService().updateChSalesProjection(chSalesProjection);
	}

	public static ChSalesProjectionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ChSalesProjectionLocalService, ChSalesProjectionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ChSalesProjectionLocalService.class);
}