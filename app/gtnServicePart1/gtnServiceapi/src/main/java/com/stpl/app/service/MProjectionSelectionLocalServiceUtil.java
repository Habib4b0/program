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
 * Provides the local service utility for MProjectionSelection. This utility wraps
 * {@link com.stpl.app.service.impl.MProjectionSelectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see MProjectionSelectionLocalService
 * @see com.stpl.app.service.base.MProjectionSelectionLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.MProjectionSelectionLocalServiceImpl
 * @generated
 */
@ProviderType
public class MProjectionSelectionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.MProjectionSelectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the m projection selection to the database. Also notifies the appropriate model listeners.
	*
	* @param mProjectionSelection the m projection selection
	* @return the m projection selection that was added
	*/
	public static com.stpl.app.model.MProjectionSelection addMProjectionSelection(
		com.stpl.app.model.MProjectionSelection mProjectionSelection) {
		return getService().addMProjectionSelection(mProjectionSelection);
	}

	/**
	* Creates a new m projection selection with the primary key. Does not add the m projection selection to the database.
	*
	* @param mProjectionSelectionSid the primary key for the new m projection selection
	* @return the new m projection selection
	*/
	public static com.stpl.app.model.MProjectionSelection createMProjectionSelection(
		int mProjectionSelectionSid) {
		return getService().createMProjectionSelection(mProjectionSelectionSid);
	}

	/**
	* Deletes the m projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mProjectionSelectionSid the primary key of the m projection selection
	* @return the m projection selection that was removed
	* @throws PortalException if a m projection selection with the primary key could not be found
	*/
	public static com.stpl.app.model.MProjectionSelection deleteMProjectionSelection(
		int mProjectionSelectionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMProjectionSelection(mProjectionSelectionSid);
	}

	/**
	* Deletes the m projection selection from the database. Also notifies the appropriate model listeners.
	*
	* @param mProjectionSelection the m projection selection
	* @return the m projection selection that was removed
	*/
	public static com.stpl.app.model.MProjectionSelection deleteMProjectionSelection(
		com.stpl.app.model.MProjectionSelection mProjectionSelection) {
		return getService().deleteMProjectionSelection(mProjectionSelection);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.MProjectionSelection fetchMProjectionSelection(
		int mProjectionSelectionSid) {
		return getService().fetchMProjectionSelection(mProjectionSelectionSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the m projection selection with the primary key.
	*
	* @param mProjectionSelectionSid the primary key of the m projection selection
	* @return the m projection selection
	* @throws PortalException if a m projection selection with the primary key could not be found
	*/
	public static com.stpl.app.model.MProjectionSelection getMProjectionSelection(
		int mProjectionSelectionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMProjectionSelection(mProjectionSelectionSid);
	}

	/**
	* Returns a range of all the m projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m projection selections
	* @param end the upper bound of the range of m projection selections (not inclusive)
	* @return the range of m projection selections
	*/
	public static java.util.List<com.stpl.app.model.MProjectionSelection> getMProjectionSelections(
		int start, int end) {
		return getService().getMProjectionSelections(start, end);
	}

	/**
	* Returns the number of m projection selections.
	*
	* @return the number of m projection selections
	*/
	public static int getMProjectionSelectionsCount() {
		return getService().getMProjectionSelectionsCount();
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
	* Updates the m projection selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mProjectionSelection the m projection selection
	* @return the m projection selection that was updated
	*/
	public static com.stpl.app.model.MProjectionSelection updateMProjectionSelection(
		com.stpl.app.model.MProjectionSelection mProjectionSelection) {
		return getService().updateMProjectionSelection(mProjectionSelection);
	}

	public static MProjectionSelectionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MProjectionSelectionLocalService, MProjectionSelectionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(MProjectionSelectionLocalService.class);
}