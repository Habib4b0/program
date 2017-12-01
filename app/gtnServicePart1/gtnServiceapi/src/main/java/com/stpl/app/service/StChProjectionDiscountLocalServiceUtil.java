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
 * Provides the local service utility for StChProjectionDiscount. This utility wraps
 * {@link com.stpl.app.service.impl.StChProjectionDiscountLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see StChProjectionDiscountLocalService
 * @see com.stpl.app.service.base.StChProjectionDiscountLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.StChProjectionDiscountLocalServiceImpl
 * @generated
 */
@ProviderType
public class StChProjectionDiscountLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.StChProjectionDiscountLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the st ch projection discount to the database. Also notifies the appropriate model listeners.
	*
	* @param stChProjectionDiscount the st ch projection discount
	* @return the st ch projection discount that was added
	*/
	public static com.stpl.app.model.StChProjectionDiscount addStChProjectionDiscount(
		com.stpl.app.model.StChProjectionDiscount stChProjectionDiscount) {
		return getService().addStChProjectionDiscount(stChProjectionDiscount);
	}

	/**
	* Creates a new st ch projection discount with the primary key. Does not add the st ch projection discount to the database.
	*
	* @param stChProjectionDiscountPK the primary key for the new st ch projection discount
	* @return the new st ch projection discount
	*/
	public static com.stpl.app.model.StChProjectionDiscount createStChProjectionDiscount(
		com.stpl.app.service.persistence.StChProjectionDiscountPK stChProjectionDiscountPK) {
		return getService()
				   .createStChProjectionDiscount(stChProjectionDiscountPK);
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
	* Deletes the st ch projection discount from the database. Also notifies the appropriate model listeners.
	*
	* @param stChProjectionDiscount the st ch projection discount
	* @return the st ch projection discount that was removed
	*/
	public static com.stpl.app.model.StChProjectionDiscount deleteStChProjectionDiscount(
		com.stpl.app.model.StChProjectionDiscount stChProjectionDiscount) {
		return getService().deleteStChProjectionDiscount(stChProjectionDiscount);
	}

	/**
	* Deletes the st ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stChProjectionDiscountPK the primary key of the st ch projection discount
	* @return the st ch projection discount that was removed
	* @throws PortalException if a st ch projection discount with the primary key could not be found
	*/
	public static com.stpl.app.model.StChProjectionDiscount deleteStChProjectionDiscount(
		com.stpl.app.service.persistence.StChProjectionDiscountPK stChProjectionDiscountPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteStChProjectionDiscount(stChProjectionDiscountPK);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.StChProjectionDiscount fetchStChProjectionDiscount(
		com.stpl.app.service.persistence.StChProjectionDiscountPK stChProjectionDiscountPK) {
		return getService().fetchStChProjectionDiscount(stChProjectionDiscountPK);
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
	* Returns the st ch projection discount with the primary key.
	*
	* @param stChProjectionDiscountPK the primary key of the st ch projection discount
	* @return the st ch projection discount
	* @throws PortalException if a st ch projection discount with the primary key could not be found
	*/
	public static com.stpl.app.model.StChProjectionDiscount getStChProjectionDiscount(
		com.stpl.app.service.persistence.StChProjectionDiscountPK stChProjectionDiscountPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStChProjectionDiscount(stChProjectionDiscountPK);
	}

	/**
	* Returns a range of all the st ch projection discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch projection discounts
	* @param end the upper bound of the range of st ch projection discounts (not inclusive)
	* @return the range of st ch projection discounts
	*/
	public static java.util.List<com.stpl.app.model.StChProjectionDiscount> getStChProjectionDiscounts(
		int start, int end) {
		return getService().getStChProjectionDiscounts(start, end);
	}

	/**
	* Returns the number of st ch projection discounts.
	*
	* @return the number of st ch projection discounts
	*/
	public static int getStChProjectionDiscountsCount() {
		return getService().getStChProjectionDiscountsCount();
	}

	/**
	* Updates the st ch projection discount in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stChProjectionDiscount the st ch projection discount
	* @return the st ch projection discount that was updated
	*/
	public static com.stpl.app.model.StChProjectionDiscount updateStChProjectionDiscount(
		com.stpl.app.model.StChProjectionDiscount stChProjectionDiscount) {
		return getService().updateStChProjectionDiscount(stChProjectionDiscount);
	}

	public static StChProjectionDiscountLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StChProjectionDiscountLocalService, StChProjectionDiscountLocalService> _serviceTracker =
		ServiceTrackerFactory.open(StChProjectionDiscountLocalService.class);
}