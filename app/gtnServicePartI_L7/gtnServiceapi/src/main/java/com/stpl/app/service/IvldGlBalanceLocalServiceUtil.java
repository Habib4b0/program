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
 * Provides the local service utility for IvldGlBalance. This utility wraps
 * {@link com.stpl.app.service.impl.IvldGlBalanceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see IvldGlBalanceLocalService
 * @see com.stpl.app.service.base.IvldGlBalanceLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.IvldGlBalanceLocalServiceImpl
 * @generated
 */
@ProviderType
public class IvldGlBalanceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.IvldGlBalanceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ivld gl balance to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldGlBalance the ivld gl balance
	* @return the ivld gl balance that was added
	*/
	public static com.stpl.app.model.IvldGlBalance addIvldGlBalance(
		com.stpl.app.model.IvldGlBalance ivldGlBalance) {
		return getService().addIvldGlBalance(ivldGlBalance);
	}

	/**
	* Creates a new ivld gl balance with the primary key. Does not add the ivld gl balance to the database.
	*
	* @param ivldGlBalanceSid the primary key for the new ivld gl balance
	* @return the new ivld gl balance
	*/
	public static com.stpl.app.model.IvldGlBalance createIvldGlBalance(
		int ivldGlBalanceSid) {
		return getService().createIvldGlBalance(ivldGlBalanceSid);
	}

	/**
	* Deletes the ivld gl balance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldGlBalanceSid the primary key of the ivld gl balance
	* @return the ivld gl balance that was removed
	* @throws PortalException if a ivld gl balance with the primary key could not be found
	*/
	public static com.stpl.app.model.IvldGlBalance deleteIvldGlBalance(
		int ivldGlBalanceSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteIvldGlBalance(ivldGlBalanceSid);
	}

	/**
	* Deletes the ivld gl balance from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldGlBalance the ivld gl balance
	* @return the ivld gl balance that was removed
	*/
	public static com.stpl.app.model.IvldGlBalance deleteIvldGlBalance(
		com.stpl.app.model.IvldGlBalance ivldGlBalance) {
		return getService().deleteIvldGlBalance(ivldGlBalance);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.IvldGlBalance fetchIvldGlBalance(
		int ivldGlBalanceSid) {
		return getService().fetchIvldGlBalance(ivldGlBalanceSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld gl balance with the primary key.
	*
	* @param ivldGlBalanceSid the primary key of the ivld gl balance
	* @return the ivld gl balance
	* @throws PortalException if a ivld gl balance with the primary key could not be found
	*/
	public static com.stpl.app.model.IvldGlBalance getIvldGlBalance(
		int ivldGlBalanceSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getIvldGlBalance(ivldGlBalanceSid);
	}

	/**
	* Returns a range of all the ivld gl balances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld gl balances
	* @param end the upper bound of the range of ivld gl balances (not inclusive)
	* @return the range of ivld gl balances
	*/
	public static java.util.List<com.stpl.app.model.IvldGlBalance> getIvldGlBalances(
		int start, int end) {
		return getService().getIvldGlBalances(start, end);
	}

	/**
	* Returns the number of ivld gl balances.
	*
	* @return the number of ivld gl balances
	*/
	public static int getIvldGlBalancesCount() {
		return getService().getIvldGlBalancesCount();
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
	* Updates the ivld gl balance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldGlBalance the ivld gl balance
	* @return the ivld gl balance that was updated
	*/
	public static com.stpl.app.model.IvldGlBalance updateIvldGlBalance(
		com.stpl.app.model.IvldGlBalance ivldGlBalance) {
		return getService().updateIvldGlBalance(ivldGlBalance);
	}

	public static IvldGlBalanceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldGlBalanceLocalService, IvldGlBalanceLocalService> _serviceTracker =
		ServiceTrackerFactory.open(IvldGlBalanceLocalService.class);
}