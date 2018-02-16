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
 * Provides the local service utility for NmProjectionSelection. This utility wraps
 * {@link com.stpl.app.service.impl.NmProjectionSelectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see NmProjectionSelectionLocalService
 * @see com.stpl.app.service.base.NmProjectionSelectionLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.NmProjectionSelectionLocalServiceImpl
 * @generated
 */
@ProviderType
public class NmProjectionSelectionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.NmProjectionSelectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the nm projection selection to the database. Also notifies the appropriate model listeners.
	*
	* @param nmProjectionSelection the nm projection selection
	* @return the nm projection selection that was added
	*/
	public static com.stpl.app.model.NmProjectionSelection addNmProjectionSelection(
		com.stpl.app.model.NmProjectionSelection nmProjectionSelection) {
		return getService().addNmProjectionSelection(nmProjectionSelection);
	}

	/**
	* Creates a new nm projection selection with the primary key. Does not add the nm projection selection to the database.
	*
	* @param nmProjectionSelectionSid the primary key for the new nm projection selection
	* @return the new nm projection selection
	*/
	public static com.stpl.app.model.NmProjectionSelection createNmProjectionSelection(
		int nmProjectionSelectionSid) {
		return getService().createNmProjectionSelection(nmProjectionSelectionSid);
	}

	/**
	* Deletes the nm projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmProjectionSelectionSid the primary key of the nm projection selection
	* @return the nm projection selection that was removed
	* @throws PortalException if a nm projection selection with the primary key could not be found
	*/
	public static com.stpl.app.model.NmProjectionSelection deleteNmProjectionSelection(
		int nmProjectionSelectionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteNmProjectionSelection(nmProjectionSelectionSid);
	}

	/**
	* Deletes the nm projection selection from the database. Also notifies the appropriate model listeners.
	*
	* @param nmProjectionSelection the nm projection selection
	* @return the nm projection selection that was removed
	*/
	public static com.stpl.app.model.NmProjectionSelection deleteNmProjectionSelection(
		com.stpl.app.model.NmProjectionSelection nmProjectionSelection) {
		return getService().deleteNmProjectionSelection(nmProjectionSelection);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.NmProjectionSelection fetchNmProjectionSelection(
		int nmProjectionSelectionSid) {
		return getService().fetchNmProjectionSelection(nmProjectionSelectionSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the nm projection selection with the primary key.
	*
	* @param nmProjectionSelectionSid the primary key of the nm projection selection
	* @return the nm projection selection
	* @throws PortalException if a nm projection selection with the primary key could not be found
	*/
	public static com.stpl.app.model.NmProjectionSelection getNmProjectionSelection(
		int nmProjectionSelectionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getNmProjectionSelection(nmProjectionSelectionSid);
	}

	/**
	* Returns a range of all the nm projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm projection selections
	* @param end the upper bound of the range of nm projection selections (not inclusive)
	* @return the range of nm projection selections
	*/
	public static java.util.List<com.stpl.app.model.NmProjectionSelection> getNmProjectionSelections(
		int start, int end) {
		return getService().getNmProjectionSelections(start, end);
	}

	/**
	* Returns the number of nm projection selections.
	*
	* @return the number of nm projection selections
	*/
	public static int getNmProjectionSelectionsCount() {
		return getService().getNmProjectionSelectionsCount();
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
	* Updates the nm projection selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nmProjectionSelection the nm projection selection
	* @return the nm projection selection that was updated
	*/
	public static com.stpl.app.model.NmProjectionSelection updateNmProjectionSelection(
		com.stpl.app.model.NmProjectionSelection nmProjectionSelection) {
		return getService().updateNmProjectionSelection(nmProjectionSelection);
	}

	public static NmProjectionSelectionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NmProjectionSelectionLocalService, NmProjectionSelectionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(NmProjectionSelectionLocalService.class);
}