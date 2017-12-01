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
 * Provides a wrapper for {@link MedicaidUraProjLocalService}.
 *
 * @author
 * @see MedicaidUraProjLocalService
 * @generated
 */
@ProviderType
public class MedicaidUraProjLocalServiceWrapper
	implements MedicaidUraProjLocalService,
		ServiceWrapper<MedicaidUraProjLocalService> {
	public MedicaidUraProjLocalServiceWrapper(
		MedicaidUraProjLocalService medicaidUraProjLocalService) {
		_medicaidUraProjLocalService = medicaidUraProjLocalService;
	}

	/**
	* Adds the medicaid ura proj to the database. Also notifies the appropriate model listeners.
	*
	* @param medicaidUraProj the medicaid ura proj
	* @return the medicaid ura proj that was added
	*/
	@Override
	public com.stpl.app.model.MedicaidUraProj addMedicaidUraProj(
		com.stpl.app.model.MedicaidUraProj medicaidUraProj) {
		return _medicaidUraProjLocalService.addMedicaidUraProj(medicaidUraProj);
	}

	/**
	* Creates a new medicaid ura proj with the primary key. Does not add the medicaid ura proj to the database.
	*
	* @param medicaidUraProjPK the primary key for the new medicaid ura proj
	* @return the new medicaid ura proj
	*/
	@Override
	public com.stpl.app.model.MedicaidUraProj createMedicaidUraProj(
		com.stpl.app.service.persistence.MedicaidUraProjPK medicaidUraProjPK) {
		return _medicaidUraProjLocalService.createMedicaidUraProj(medicaidUraProjPK);
	}

	/**
	* Deletes the medicaid ura proj from the database. Also notifies the appropriate model listeners.
	*
	* @param medicaidUraProj the medicaid ura proj
	* @return the medicaid ura proj that was removed
	*/
	@Override
	public com.stpl.app.model.MedicaidUraProj deleteMedicaidUraProj(
		com.stpl.app.model.MedicaidUraProj medicaidUraProj) {
		return _medicaidUraProjLocalService.deleteMedicaidUraProj(medicaidUraProj);
	}

	/**
	* Deletes the medicaid ura proj with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param medicaidUraProjPK the primary key of the medicaid ura proj
	* @return the medicaid ura proj that was removed
	* @throws PortalException if a medicaid ura proj with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MedicaidUraProj deleteMedicaidUraProj(
		com.stpl.app.service.persistence.MedicaidUraProjPK medicaidUraProjPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _medicaidUraProjLocalService.deleteMedicaidUraProj(medicaidUraProjPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _medicaidUraProjLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _medicaidUraProjLocalService.dynamicQuery();
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
		return _medicaidUraProjLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _medicaidUraProjLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _medicaidUraProjLocalService.dynamicQuery(dynamicQuery, start,
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
		return _medicaidUraProjLocalService.dynamicQueryCount(dynamicQuery);
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
		return _medicaidUraProjLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.MedicaidUraProj fetchMedicaidUraProj(
		com.stpl.app.service.persistence.MedicaidUraProjPK medicaidUraProjPK) {
		return _medicaidUraProjLocalService.fetchMedicaidUraProj(medicaidUraProjPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _medicaidUraProjLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _medicaidUraProjLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the medicaid ura proj with the primary key.
	*
	* @param medicaidUraProjPK the primary key of the medicaid ura proj
	* @return the medicaid ura proj
	* @throws PortalException if a medicaid ura proj with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MedicaidUraProj getMedicaidUraProj(
		com.stpl.app.service.persistence.MedicaidUraProjPK medicaidUraProjPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _medicaidUraProjLocalService.getMedicaidUraProj(medicaidUraProjPK);
	}

	/**
	* Returns a range of all the medicaid ura projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of medicaid ura projs
	* @param end the upper bound of the range of medicaid ura projs (not inclusive)
	* @return the range of medicaid ura projs
	*/
	@Override
	public java.util.List<com.stpl.app.model.MedicaidUraProj> getMedicaidUraProjs(
		int start, int end) {
		return _medicaidUraProjLocalService.getMedicaidUraProjs(start, end);
	}

	/**
	* Returns the number of medicaid ura projs.
	*
	* @return the number of medicaid ura projs
	*/
	@Override
	public int getMedicaidUraProjsCount() {
		return _medicaidUraProjLocalService.getMedicaidUraProjsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _medicaidUraProjLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _medicaidUraProjLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the medicaid ura proj in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param medicaidUraProj the medicaid ura proj
	* @return the medicaid ura proj that was updated
	*/
	@Override
	public com.stpl.app.model.MedicaidUraProj updateMedicaidUraProj(
		com.stpl.app.model.MedicaidUraProj medicaidUraProj) {
		return _medicaidUraProjLocalService.updateMedicaidUraProj(medicaidUraProj);
	}

	@Override
	public MedicaidUraProjLocalService getWrappedService() {
		return _medicaidUraProjLocalService;
	}

	@Override
	public void setWrappedService(
		MedicaidUraProjLocalService medicaidUraProjLocalService) {
		_medicaidUraProjLocalService = medicaidUraProjLocalService;
	}

	private MedicaidUraProjLocalService _medicaidUraProjLocalService;
}