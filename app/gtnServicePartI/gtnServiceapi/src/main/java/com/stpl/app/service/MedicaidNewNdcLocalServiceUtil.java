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
 * Provides the local service utility for MedicaidNewNdc. This utility wraps
 * {@link com.stpl.app.service.impl.MedicaidNewNdcLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see MedicaidNewNdcLocalService
 * @see com.stpl.app.service.base.MedicaidNewNdcLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.MedicaidNewNdcLocalServiceImpl
 * @generated
 */
@ProviderType
public class MedicaidNewNdcLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.MedicaidNewNdcLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the medicaid new ndc to the database. Also notifies the appropriate model listeners.
	*
	* @param medicaidNewNdc the medicaid new ndc
	* @return the medicaid new ndc that was added
	*/
	public static com.stpl.app.model.MedicaidNewNdc addMedicaidNewNdc(
		com.stpl.app.model.MedicaidNewNdc medicaidNewNdc) {
		return getService().addMedicaidNewNdc(medicaidNewNdc);
	}

	/**
	* Creates a new medicaid new ndc with the primary key. Does not add the medicaid new ndc to the database.
	*
	* @param ndc9 the primary key for the new medicaid new ndc
	* @return the new medicaid new ndc
	*/
	public static com.stpl.app.model.MedicaidNewNdc createMedicaidNewNdc(
		java.lang.String ndc9) {
		return getService().createMedicaidNewNdc(ndc9);
	}

	/**
	* Deletes the medicaid new ndc from the database. Also notifies the appropriate model listeners.
	*
	* @param medicaidNewNdc the medicaid new ndc
	* @return the medicaid new ndc that was removed
	*/
	public static com.stpl.app.model.MedicaidNewNdc deleteMedicaidNewNdc(
		com.stpl.app.model.MedicaidNewNdc medicaidNewNdc) {
		return getService().deleteMedicaidNewNdc(medicaidNewNdc);
	}

	/**
	* Deletes the medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ndc9 the primary key of the medicaid new ndc
	* @return the medicaid new ndc that was removed
	* @throws PortalException if a medicaid new ndc with the primary key could not be found
	*/
	public static com.stpl.app.model.MedicaidNewNdc deleteMedicaidNewNdc(
		java.lang.String ndc9)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMedicaidNewNdc(ndc9);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.stpl.app.model.MedicaidNewNdc fetchMedicaidNewNdc(
		java.lang.String ndc9) {
		return getService().fetchMedicaidNewNdc(ndc9);
	}

	/**
	* Returns the medicaid new ndc with the primary key.
	*
	* @param ndc9 the primary key of the medicaid new ndc
	* @return the medicaid new ndc
	* @throws PortalException if a medicaid new ndc with the primary key could not be found
	*/
	public static com.stpl.app.model.MedicaidNewNdc getMedicaidNewNdc(
		java.lang.String ndc9)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMedicaidNewNdc(ndc9);
	}

	/**
	* Returns a range of all the medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of medicaid new ndcs
	* @param end the upper bound of the range of medicaid new ndcs (not inclusive)
	* @return the range of medicaid new ndcs
	*/
	public static java.util.List<com.stpl.app.model.MedicaidNewNdc> getMedicaidNewNdcs(
		int start, int end) {
		return getService().getMedicaidNewNdcs(start, end);
	}

	/**
	* Returns the number of medicaid new ndcs.
	*
	* @return the number of medicaid new ndcs
	*/
	public static int getMedicaidNewNdcsCount() {
		return getService().getMedicaidNewNdcsCount();
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
	* Updates the medicaid new ndc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param medicaidNewNdc the medicaid new ndc
	* @return the medicaid new ndc that was updated
	*/
	public static com.stpl.app.model.MedicaidNewNdc updateMedicaidNewNdc(
		com.stpl.app.model.MedicaidNewNdc medicaidNewNdc) {
		return getService().updateMedicaidNewNdc(medicaidNewNdc);
	}

	public static MedicaidNewNdcLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MedicaidNewNdcLocalService, MedicaidNewNdcLocalService> _serviceTracker =
		ServiceTrackerFactory.open(MedicaidNewNdcLocalService.class);
}