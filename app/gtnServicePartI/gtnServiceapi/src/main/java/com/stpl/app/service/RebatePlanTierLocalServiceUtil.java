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
 * Provides the local service utility for RebatePlanTier. This utility wraps
 * {@link com.stpl.app.service.impl.RebatePlanTierLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see RebatePlanTierLocalService
 * @see com.stpl.app.service.base.RebatePlanTierLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.RebatePlanTierLocalServiceImpl
 * @generated
 */
@ProviderType
public class RebatePlanTierLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.RebatePlanTierLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the rebate plan tier to the database. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanTier the rebate plan tier
	* @return the rebate plan tier that was added
	*/
	public static com.stpl.app.model.RebatePlanTier addRebatePlanTier(
		com.stpl.app.model.RebatePlanTier rebatePlanTier) {
		return getService().addRebatePlanTier(rebatePlanTier);
	}

	/**
	* Creates a new rebate plan tier with the primary key. Does not add the rebate plan tier to the database.
	*
	* @param rebatePlanTierSid the primary key for the new rebate plan tier
	* @return the new rebate plan tier
	*/
	public static com.stpl.app.model.RebatePlanTier createRebatePlanTier(
		int rebatePlanTierSid) {
		return getService().createRebatePlanTier(rebatePlanTierSid);
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
	* Deletes the rebate plan tier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanTierSid the primary key of the rebate plan tier
	* @return the rebate plan tier that was removed
	* @throws PortalException if a rebate plan tier with the primary key could not be found
	*/
	public static com.stpl.app.model.RebatePlanTier deleteRebatePlanTier(
		int rebatePlanTierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRebatePlanTier(rebatePlanTierSid);
	}

	/**
	* Deletes the rebate plan tier from the database. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanTier the rebate plan tier
	* @return the rebate plan tier that was removed
	*/
	public static com.stpl.app.model.RebatePlanTier deleteRebatePlanTier(
		com.stpl.app.model.RebatePlanTier rebatePlanTier) {
		return getService().deleteRebatePlanTier(rebatePlanTier);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.RebatePlanTier fetchRebatePlanTier(
		int rebatePlanTierSid) {
		return getService().fetchRebatePlanTier(rebatePlanTierSid);
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
	* Returns the rebate plan tier with the primary key.
	*
	* @param rebatePlanTierSid the primary key of the rebate plan tier
	* @return the rebate plan tier
	* @throws PortalException if a rebate plan tier with the primary key could not be found
	*/
	public static com.stpl.app.model.RebatePlanTier getRebatePlanTier(
		int rebatePlanTierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRebatePlanTier(rebatePlanTierSid);
	}

	/**
	* Returns a range of all the rebate plan tiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rebate plan tiers
	* @param end the upper bound of the range of rebate plan tiers (not inclusive)
	* @return the range of rebate plan tiers
	*/
	public static java.util.List<com.stpl.app.model.RebatePlanTier> getRebatePlanTiers(
		int start, int end) {
		return getService().getRebatePlanTiers(start, end);
	}

	/**
	* Returns the number of rebate plan tiers.
	*
	* @return the number of rebate plan tiers
	*/
	public static int getRebatePlanTiersCount() {
		return getService().getRebatePlanTiersCount();
	}

	/**
	* Updates the rebate plan tier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanTier the rebate plan tier
	* @return the rebate plan tier that was updated
	*/
	public static com.stpl.app.model.RebatePlanTier updateRebatePlanTier(
		com.stpl.app.model.RebatePlanTier rebatePlanTier) {
		return getService().updateRebatePlanTier(rebatePlanTier);
	}

	public static RebatePlanTierLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RebatePlanTierLocalService, RebatePlanTierLocalService> _serviceTracker =
		ServiceTrackerFactory.open(RebatePlanTierLocalService.class);
}