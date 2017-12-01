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
 * Provides a wrapper for {@link NationalAssumptionsProjLocalService}.
 *
 * @author
 * @see NationalAssumptionsProjLocalService
 * @generated
 */
@ProviderType
public class NationalAssumptionsProjLocalServiceWrapper
	implements NationalAssumptionsProjLocalService,
		ServiceWrapper<NationalAssumptionsProjLocalService> {
	public NationalAssumptionsProjLocalServiceWrapper(
		NationalAssumptionsProjLocalService nationalAssumptionsProjLocalService) {
		_nationalAssumptionsProjLocalService = nationalAssumptionsProjLocalService;
	}

	/**
	* Adds the national assumptions proj to the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsProj the national assumptions proj
	* @return the national assumptions proj that was added
	*/
	@Override
	public com.stpl.app.model.NationalAssumptionsProj addNationalAssumptionsProj(
		com.stpl.app.model.NationalAssumptionsProj nationalAssumptionsProj) {
		return _nationalAssumptionsProjLocalService.addNationalAssumptionsProj(nationalAssumptionsProj);
	}

	/**
	* Creates a new national assumptions proj with the primary key. Does not add the national assumptions proj to the database.
	*
	* @param nationalAssumptionsProjPK the primary key for the new national assumptions proj
	* @return the new national assumptions proj
	*/
	@Override
	public com.stpl.app.model.NationalAssumptionsProj createNationalAssumptionsProj(
		com.stpl.app.service.persistence.NationalAssumptionsProjPK nationalAssumptionsProjPK) {
		return _nationalAssumptionsProjLocalService.createNationalAssumptionsProj(nationalAssumptionsProjPK);
	}

	/**
	* Deletes the national assumptions proj from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsProj the national assumptions proj
	* @return the national assumptions proj that was removed
	*/
	@Override
	public com.stpl.app.model.NationalAssumptionsProj deleteNationalAssumptionsProj(
		com.stpl.app.model.NationalAssumptionsProj nationalAssumptionsProj) {
		return _nationalAssumptionsProjLocalService.deleteNationalAssumptionsProj(nationalAssumptionsProj);
	}

	/**
	* Deletes the national assumptions proj with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsProjPK the primary key of the national assumptions proj
	* @return the national assumptions proj that was removed
	* @throws PortalException if a national assumptions proj with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NationalAssumptionsProj deleteNationalAssumptionsProj(
		com.stpl.app.service.persistence.NationalAssumptionsProjPK nationalAssumptionsProjPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nationalAssumptionsProjLocalService.deleteNationalAssumptionsProj(nationalAssumptionsProjPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nationalAssumptionsProjLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _nationalAssumptionsProjLocalService.dynamicQuery();
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
		return _nationalAssumptionsProjLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nationalAssumptionsProjLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nationalAssumptionsProjLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _nationalAssumptionsProjLocalService.dynamicQueryCount(dynamicQuery);
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
		return _nationalAssumptionsProjLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.NationalAssumptionsProj fetchNationalAssumptionsProj(
		com.stpl.app.service.persistence.NationalAssumptionsProjPK nationalAssumptionsProjPK) {
		return _nationalAssumptionsProjLocalService.fetchNationalAssumptionsProj(nationalAssumptionsProjPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _nationalAssumptionsProjLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _nationalAssumptionsProjLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the national assumptions proj with the primary key.
	*
	* @param nationalAssumptionsProjPK the primary key of the national assumptions proj
	* @return the national assumptions proj
	* @throws PortalException if a national assumptions proj with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NationalAssumptionsProj getNationalAssumptionsProj(
		com.stpl.app.service.persistence.NationalAssumptionsProjPK nationalAssumptionsProjPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nationalAssumptionsProjLocalService.getNationalAssumptionsProj(nationalAssumptionsProjPK);
	}

	/**
	* Returns a range of all the national assumptions projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptions projs
	* @param end the upper bound of the range of national assumptions projs (not inclusive)
	* @return the range of national assumptions projs
	*/
	@Override
	public java.util.List<com.stpl.app.model.NationalAssumptionsProj> getNationalAssumptionsProjs(
		int start, int end) {
		return _nationalAssumptionsProjLocalService.getNationalAssumptionsProjs(start,
			end);
	}

	/**
	* Returns the number of national assumptions projs.
	*
	* @return the number of national assumptions projs
	*/
	@Override
	public int getNationalAssumptionsProjsCount() {
		return _nationalAssumptionsProjLocalService.getNationalAssumptionsProjsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _nationalAssumptionsProjLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nationalAssumptionsProjLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the national assumptions proj in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsProj the national assumptions proj
	* @return the national assumptions proj that was updated
	*/
	@Override
	public com.stpl.app.model.NationalAssumptionsProj updateNationalAssumptionsProj(
		com.stpl.app.model.NationalAssumptionsProj nationalAssumptionsProj) {
		return _nationalAssumptionsProjLocalService.updateNationalAssumptionsProj(nationalAssumptionsProj);
	}

	@Override
	public NationalAssumptionsProjLocalService getWrappedService() {
		return _nationalAssumptionsProjLocalService;
	}

	@Override
	public void setWrappedService(
		NationalAssumptionsProjLocalService nationalAssumptionsProjLocalService) {
		_nationalAssumptionsProjLocalService = nationalAssumptionsProjLocalService;
	}

	private NationalAssumptionsProjLocalService _nationalAssumptionsProjLocalService;
}