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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MedicaidNewNdcLocalService}.
 *
 * @author
 * @see MedicaidNewNdcLocalService
 * @generated
 */
@ProviderType
public class MedicaidNewNdcLocalServiceWrapper
	implements MedicaidNewNdcLocalService,
		ServiceWrapper<MedicaidNewNdcLocalService> {
	public MedicaidNewNdcLocalServiceWrapper(
		MedicaidNewNdcLocalService medicaidNewNdcLocalService) {
		_medicaidNewNdcLocalService = medicaidNewNdcLocalService;
	}

	/**
	* Adds the medicaid new ndc to the database. Also notifies the appropriate model listeners.
	*
	* @param medicaidNewNdc the medicaid new ndc
	* @return the medicaid new ndc that was added
	*/
	@Override
	public com.stpl.app.model.MedicaidNewNdc addMedicaidNewNdc(
		com.stpl.app.model.MedicaidNewNdc medicaidNewNdc) {
		return _medicaidNewNdcLocalService.addMedicaidNewNdc(medicaidNewNdc);
	}

	/**
	* Creates a new medicaid new ndc with the primary key. Does not add the medicaid new ndc to the database.
	*
	* @param ndc9 the primary key for the new medicaid new ndc
	* @return the new medicaid new ndc
	*/
	@Override
	public com.stpl.app.model.MedicaidNewNdc createMedicaidNewNdc(
		java.lang.String ndc9) {
		return _medicaidNewNdcLocalService.createMedicaidNewNdc(ndc9);
	}

	/**
	* Deletes the medicaid new ndc from the database. Also notifies the appropriate model listeners.
	*
	* @param medicaidNewNdc the medicaid new ndc
	* @return the medicaid new ndc that was removed
	*/
	@Override
	public com.stpl.app.model.MedicaidNewNdc deleteMedicaidNewNdc(
		com.stpl.app.model.MedicaidNewNdc medicaidNewNdc) {
		return _medicaidNewNdcLocalService.deleteMedicaidNewNdc(medicaidNewNdc);
	}

	/**
	* Deletes the medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ndc9 the primary key of the medicaid new ndc
	* @return the medicaid new ndc that was removed
	* @throws PortalException if a medicaid new ndc with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MedicaidNewNdc deleteMedicaidNewNdc(
		java.lang.String ndc9)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _medicaidNewNdcLocalService.deleteMedicaidNewNdc(ndc9);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _medicaidNewNdcLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _medicaidNewNdcLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _medicaidNewNdcLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _medicaidNewNdcLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _medicaidNewNdcLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _medicaidNewNdcLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _medicaidNewNdcLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.MedicaidNewNdc fetchMedicaidNewNdc(
		java.lang.String ndc9) {
		return _medicaidNewNdcLocalService.fetchMedicaidNewNdc(ndc9);
	}

	/**
	* Returns the medicaid new ndc with the primary key.
	*
	* @param ndc9 the primary key of the medicaid new ndc
	* @return the medicaid new ndc
	* @throws PortalException if a medicaid new ndc with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MedicaidNewNdc getMedicaidNewNdc(
		java.lang.String ndc9)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _medicaidNewNdcLocalService.getMedicaidNewNdc(ndc9);
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
	@Override
	public java.util.List<com.stpl.app.model.MedicaidNewNdc> getMedicaidNewNdcs(
		int start, int end) {
		return _medicaidNewNdcLocalService.getMedicaidNewNdcs(start, end);
	}

	/**
	* Returns the number of medicaid new ndcs.
	*
	* @return the number of medicaid new ndcs
	*/
	@Override
	public int getMedicaidNewNdcsCount() {
		return _medicaidNewNdcLocalService.getMedicaidNewNdcsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _medicaidNewNdcLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _medicaidNewNdcLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the medicaid new ndc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param medicaidNewNdc the medicaid new ndc
	* @return the medicaid new ndc that was updated
	*/
	@Override
	public com.stpl.app.model.MedicaidNewNdc updateMedicaidNewNdc(
		com.stpl.app.model.MedicaidNewNdc medicaidNewNdc) {
		return _medicaidNewNdcLocalService.updateMedicaidNewNdc(medicaidNewNdc);
	}

	@Override
	public MedicaidNewNdcLocalService getWrappedService() {
		return _medicaidNewNdcLocalService;
	}

	@Override
	public void setWrappedService(
		MedicaidNewNdcLocalService medicaidNewNdcLocalService) {
		_medicaidNewNdcLocalService = medicaidNewNdcLocalService;
	}

	private MedicaidNewNdcLocalService _medicaidNewNdcLocalService;
}