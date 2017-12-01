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
 * Provides a wrapper for {@link FcpProjLocalService}.
 *
 * @author
 * @see FcpProjLocalService
 * @generated
 */
@ProviderType
public class FcpProjLocalServiceWrapper implements FcpProjLocalService,
	ServiceWrapper<FcpProjLocalService> {
	public FcpProjLocalServiceWrapper(FcpProjLocalService fcpProjLocalService) {
		_fcpProjLocalService = fcpProjLocalService;
	}

	/**
	* Adds the fcp proj to the database. Also notifies the appropriate model listeners.
	*
	* @param fcpProj the fcp proj
	* @return the fcp proj that was added
	*/
	@Override
	public com.stpl.app.model.FcpProj addFcpProj(
		com.stpl.app.model.FcpProj fcpProj) {
		return _fcpProjLocalService.addFcpProj(fcpProj);
	}

	/**
	* Creates a new fcp proj with the primary key. Does not add the fcp proj to the database.
	*
	* @param fcpProjPK the primary key for the new fcp proj
	* @return the new fcp proj
	*/
	@Override
	public com.stpl.app.model.FcpProj createFcpProj(
		com.stpl.app.service.persistence.FcpProjPK fcpProjPK) {
		return _fcpProjLocalService.createFcpProj(fcpProjPK);
	}

	/**
	* Deletes the fcp proj from the database. Also notifies the appropriate model listeners.
	*
	* @param fcpProj the fcp proj
	* @return the fcp proj that was removed
	*/
	@Override
	public com.stpl.app.model.FcpProj deleteFcpProj(
		com.stpl.app.model.FcpProj fcpProj) {
		return _fcpProjLocalService.deleteFcpProj(fcpProj);
	}

	/**
	* Deletes the fcp proj with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fcpProjPK the primary key of the fcp proj
	* @return the fcp proj that was removed
	* @throws PortalException if a fcp proj with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.FcpProj deleteFcpProj(
		com.stpl.app.service.persistence.FcpProjPK fcpProjPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fcpProjLocalService.deleteFcpProj(fcpProjPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fcpProjLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fcpProjLocalService.dynamicQuery();
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
		return _fcpProjLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fcpProjLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fcpProjLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _fcpProjLocalService.dynamicQueryCount(dynamicQuery);
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
		return _fcpProjLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.stpl.app.model.FcpProj fetchFcpProj(
		com.stpl.app.service.persistence.FcpProjPK fcpProjPK) {
		return _fcpProjLocalService.fetchFcpProj(fcpProjPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _fcpProjLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the fcp proj with the primary key.
	*
	* @param fcpProjPK the primary key of the fcp proj
	* @return the fcp proj
	* @throws PortalException if a fcp proj with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.FcpProj getFcpProj(
		com.stpl.app.service.persistence.FcpProjPK fcpProjPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fcpProjLocalService.getFcpProj(fcpProjPK);
	}

	/**
	* Returns a range of all the fcp projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fcp projs
	* @param end the upper bound of the range of fcp projs (not inclusive)
	* @return the range of fcp projs
	*/
	@Override
	public java.util.List<com.stpl.app.model.FcpProj> getFcpProjs(int start,
		int end) {
		return _fcpProjLocalService.getFcpProjs(start, end);
	}

	/**
	* Returns the number of fcp projs.
	*
	* @return the number of fcp projs
	*/
	@Override
	public int getFcpProjsCount() {
		return _fcpProjLocalService.getFcpProjsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _fcpProjLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _fcpProjLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fcpProjLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the fcp proj in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param fcpProj the fcp proj
	* @return the fcp proj that was updated
	*/
	@Override
	public com.stpl.app.model.FcpProj updateFcpProj(
		com.stpl.app.model.FcpProj fcpProj) {
		return _fcpProjLocalService.updateFcpProj(fcpProj);
	}

	@Override
	public FcpProjLocalService getWrappedService() {
		return _fcpProjLocalService;
	}

	@Override
	public void setWrappedService(FcpProjLocalService fcpProjLocalService) {
		_fcpProjLocalService = fcpProjLocalService;
	}

	private FcpProjLocalService _fcpProjLocalService;
}