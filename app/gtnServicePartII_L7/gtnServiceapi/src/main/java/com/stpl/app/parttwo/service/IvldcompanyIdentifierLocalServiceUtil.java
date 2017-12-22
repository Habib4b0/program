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
 * Provides the local service utility for IvldCompanyIdentifier. This utility wraps
 * {@link com.stpl.app.parttwo.service.impl.IvldCompanyIdentifierLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see IvldCompanyIdentifierLocalService
 * @see com.stpl.app.parttwo.service.base.IvldCompanyIdentifierLocalServiceBaseImpl
 * @see com.stpl.app.parttwo.service.impl.IvldCompanyIdentifierLocalServiceImpl
 * @generated
 */
@ProviderType
public class IvldCompanyIdentifierLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.parttwo.service.impl.IvldCompanyIdentifierLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ivld company identifier to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyIdentifier the ivld company identifier
	* @return the ivld company identifier that was added
	*/
	public static com.stpl.app.parttwo.model.IvldCompanyIdentifier addIvldCompanyIdentifier(
		com.stpl.app.parttwo.model.IvldCompanyIdentifier ivldCompanyIdentifier) {
		return getService().addIvldCompanyIdentifier(ivldCompanyIdentifier);
	}

	/**
	* Creates a new ivld company identifier with the primary key. Does not add the ivld company identifier to the database.
	*
	* @param ivldCompanyIdentifierSid the primary key for the new ivld company identifier
	* @return the new ivld company identifier
	*/
	public static com.stpl.app.parttwo.model.IvldCompanyIdentifier createIvldCompanyIdentifier(
		int ivldCompanyIdentifierSid) {
		return getService().createIvldCompanyIdentifier(ivldCompanyIdentifierSid);
	}

	/**
	* Deletes the ivld company identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	* @return the ivld company identifier that was removed
	* @throws PortalException if a ivld company identifier with the primary key could not be found
	*/
	public static com.stpl.app.parttwo.model.IvldCompanyIdentifier deleteIvldCompanyIdentifier(
		int ivldCompanyIdentifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteIvldCompanyIdentifier(ivldCompanyIdentifierSid);
	}

	/**
	* Deletes the ivld company identifier from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyIdentifier the ivld company identifier
	* @return the ivld company identifier that was removed
	*/
	public static com.stpl.app.parttwo.model.IvldCompanyIdentifier deleteIvldCompanyIdentifier(
		com.stpl.app.parttwo.model.IvldCompanyIdentifier ivldCompanyIdentifier) {
		return getService().deleteIvldCompanyIdentifier(ivldCompanyIdentifier);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.parttwo.model.IvldCompanyIdentifier fetchIvldCompanyIdentifier(
		int ivldCompanyIdentifierSid) {
		return getService().fetchIvldCompanyIdentifier(ivldCompanyIdentifierSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld company identifier with the primary key.
	*
	* @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	* @return the ivld company identifier
	* @throws PortalException if a ivld company identifier with the primary key could not be found
	*/
	public static com.stpl.app.parttwo.model.IvldCompanyIdentifier getIvldCompanyIdentifier(
		int ivldCompanyIdentifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getIvldCompanyIdentifier(ivldCompanyIdentifierSid);
	}

	/**
	* Returns a range of all the ivld company identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company identifiers
	* @param end the upper bound of the range of ivld company identifiers (not inclusive)
	* @return the range of ivld company identifiers
	*/
	public static java.util.List<com.stpl.app.parttwo.model.IvldCompanyIdentifier> getIvldCompanyIdentifiers(
		int start, int end) {
		return getService().getIvldCompanyIdentifiers(start, end);
	}

	/**
	* Returns the number of ivld company identifiers.
	*
	* @return the number of ivld company identifiers
	*/
	public static int getIvldCompanyIdentifiersCount() {
		return getService().getIvldCompanyIdentifiersCount();
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
	* Updates the ivld company identifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyIdentifier the ivld company identifier
	* @return the ivld company identifier that was updated
	*/
	public static com.stpl.app.parttwo.model.IvldCompanyIdentifier updateIvldCompanyIdentifier(
		com.stpl.app.parttwo.model.IvldCompanyIdentifier ivldCompanyIdentifier) {
		return getService().updateIvldCompanyIdentifier(ivldCompanyIdentifier);
	}

	public static IvldCompanyIdentifierLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldCompanyIdentifierLocalService, IvldCompanyIdentifierLocalService> _serviceTracker =
		ServiceTrackerFactory.open(IvldCompanyIdentifierLocalService.class);
}