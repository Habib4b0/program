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
 * Provides the local service utility for IvldInventoryWdActualMas. This utility wraps
 * {@link com.stpl.app.service.impl.IvldInventoryWdActualMasLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see IvldInventoryWdActualMasLocalService
 * @see com.stpl.app.service.base.IvldInventoryWdActualMasLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.IvldInventoryWdActualMasLocalServiceImpl
 * @generated
 */
@ProviderType
public class IvldInventoryWdActualMasLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.IvldInventoryWdActualMasLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ivld inventory wd actual mas to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldInventoryWdActualMas the ivld inventory wd actual mas
	* @return the ivld inventory wd actual mas that was added
	*/
	public static com.stpl.app.model.IvldInventoryWdActualMas addIvldInventoryWdActualMas(
		com.stpl.app.model.IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		return getService().addIvldInventoryWdActualMas(ivldInventoryWdActualMas);
	}

	/**
	* Creates a new ivld inventory wd actual mas with the primary key. Does not add the ivld inventory wd actual mas to the database.
	*
	* @param ivldInventoryWdActualMasSid the primary key for the new ivld inventory wd actual mas
	* @return the new ivld inventory wd actual mas
	*/
	public static com.stpl.app.model.IvldInventoryWdActualMas createIvldInventoryWdActualMas(
		int ivldInventoryWdActualMasSid) {
		return getService()
				   .createIvldInventoryWdActualMas(ivldInventoryWdActualMasSid);
	}

	/**
	* Deletes the ivld inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldInventoryWdActualMasSid the primary key of the ivld inventory wd actual mas
	* @return the ivld inventory wd actual mas that was removed
	* @throws PortalException if a ivld inventory wd actual mas with the primary key could not be found
	*/
	public static com.stpl.app.model.IvldInventoryWdActualMas deleteIvldInventoryWdActualMas(
		int ivldInventoryWdActualMasSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteIvldInventoryWdActualMas(ivldInventoryWdActualMasSid);
	}

	/**
	* Deletes the ivld inventory wd actual mas from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldInventoryWdActualMas the ivld inventory wd actual mas
	* @return the ivld inventory wd actual mas that was removed
	*/
	public static com.stpl.app.model.IvldInventoryWdActualMas deleteIvldInventoryWdActualMas(
		com.stpl.app.model.IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		return getService()
				   .deleteIvldInventoryWdActualMas(ivldInventoryWdActualMas);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.IvldInventoryWdActualMas fetchIvldInventoryWdActualMas(
		int ivldInventoryWdActualMasSid) {
		return getService()
				   .fetchIvldInventoryWdActualMas(ivldInventoryWdActualMasSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld inventory wd actual mas with the primary key.
	*
	* @param ivldInventoryWdActualMasSid the primary key of the ivld inventory wd actual mas
	* @return the ivld inventory wd actual mas
	* @throws PortalException if a ivld inventory wd actual mas with the primary key could not be found
	*/
	public static com.stpl.app.model.IvldInventoryWdActualMas getIvldInventoryWdActualMas(
		int ivldInventoryWdActualMasSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getIvldInventoryWdActualMas(ivldInventoryWdActualMasSid);
	}

	/**
	* Returns a range of all the ivld inventory wd actual mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld inventory wd actual mases
	* @param end the upper bound of the range of ivld inventory wd actual mases (not inclusive)
	* @return the range of ivld inventory wd actual mases
	*/
	public static java.util.List<com.stpl.app.model.IvldInventoryWdActualMas> getIvldInventoryWdActualMases(
		int start, int end) {
		return getService().getIvldInventoryWdActualMases(start, end);
	}

	/**
	* Returns the number of ivld inventory wd actual mases.
	*
	* @return the number of ivld inventory wd actual mases
	*/
	public static int getIvldInventoryWdActualMasesCount() {
		return getService().getIvldInventoryWdActualMasesCount();
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
	* Updates the ivld inventory wd actual mas in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldInventoryWdActualMas the ivld inventory wd actual mas
	* @return the ivld inventory wd actual mas that was updated
	*/
	public static com.stpl.app.model.IvldInventoryWdActualMas updateIvldInventoryWdActualMas(
		com.stpl.app.model.IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		return getService()
				   .updateIvldInventoryWdActualMas(ivldInventoryWdActualMas);
	}

	public static IvldInventoryWdActualMasLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldInventoryWdActualMasLocalService, IvldInventoryWdActualMasLocalService> _serviceTracker =
		ServiceTrackerFactory.open(IvldInventoryWdActualMasLocalService.class);
}