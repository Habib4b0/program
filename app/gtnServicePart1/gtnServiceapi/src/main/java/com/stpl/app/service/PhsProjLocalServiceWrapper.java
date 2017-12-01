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
 * Provides a wrapper for {@link PhsProjLocalService}.
 *
 * @author
 * @see PhsProjLocalService
 * @generated
 */
@ProviderType
public class PhsProjLocalServiceWrapper implements PhsProjLocalService,
	ServiceWrapper<PhsProjLocalService> {
	public PhsProjLocalServiceWrapper(PhsProjLocalService phsProjLocalService) {
		_phsProjLocalService = phsProjLocalService;
	}

	/**
	* Adds the phs proj to the database. Also notifies the appropriate model listeners.
	*
	* @param phsProj the phs proj
	* @return the phs proj that was added
	*/
	@Override
	public com.stpl.app.model.PhsProj addPhsProj(
		com.stpl.app.model.PhsProj phsProj) {
		return _phsProjLocalService.addPhsProj(phsProj);
	}

	/**
	* Creates a new phs proj with the primary key. Does not add the phs proj to the database.
	*
	* @param phsProjPK the primary key for the new phs proj
	* @return the new phs proj
	*/
	@Override
	public com.stpl.app.model.PhsProj createPhsProj(
		com.stpl.app.service.persistence.PhsProjPK phsProjPK) {
		return _phsProjLocalService.createPhsProj(phsProjPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phsProjLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the phs proj from the database. Also notifies the appropriate model listeners.
	*
	* @param phsProj the phs proj
	* @return the phs proj that was removed
	*/
	@Override
	public com.stpl.app.model.PhsProj deletePhsProj(
		com.stpl.app.model.PhsProj phsProj) {
		return _phsProjLocalService.deletePhsProj(phsProj);
	}

	/**
	* Deletes the phs proj with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param phsProjPK the primary key of the phs proj
	* @return the phs proj that was removed
	* @throws PortalException if a phs proj with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PhsProj deletePhsProj(
		com.stpl.app.service.persistence.PhsProjPK phsProjPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phsProjLocalService.deletePhsProj(phsProjPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _phsProjLocalService.dynamicQuery();
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
		return _phsProjLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _phsProjLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _phsProjLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _phsProjLocalService.dynamicQueryCount(dynamicQuery);
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
		return _phsProjLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.stpl.app.model.PhsProj fetchPhsProj(
		com.stpl.app.service.persistence.PhsProjPK phsProjPK) {
		return _phsProjLocalService.fetchPhsProj(phsProjPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _phsProjLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _phsProjLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _phsProjLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phsProjLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the phs proj with the primary key.
	*
	* @param phsProjPK the primary key of the phs proj
	* @return the phs proj
	* @throws PortalException if a phs proj with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PhsProj getPhsProj(
		com.stpl.app.service.persistence.PhsProjPK phsProjPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phsProjLocalService.getPhsProj(phsProjPK);
	}

	/**
	* Returns a range of all the phs projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phs projs
	* @param end the upper bound of the range of phs projs (not inclusive)
	* @return the range of phs projs
	*/
	@Override
	public java.util.List<com.stpl.app.model.PhsProj> getPhsProjs(int start,
		int end) {
		return _phsProjLocalService.getPhsProjs(start, end);
	}

	/**
	* Returns the number of phs projs.
	*
	* @return the number of phs projs
	*/
	@Override
	public int getPhsProjsCount() {
		return _phsProjLocalService.getPhsProjsCount();
	}

	/**
	* Updates the phs proj in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param phsProj the phs proj
	* @return the phs proj that was updated
	*/
	@Override
	public com.stpl.app.model.PhsProj updatePhsProj(
		com.stpl.app.model.PhsProj phsProj) {
		return _phsProjLocalService.updatePhsProj(phsProj);
	}

	@Override
	public PhsProjLocalService getWrappedService() {
		return _phsProjLocalService;
	}

	@Override
	public void setWrappedService(PhsProjLocalService phsProjLocalService) {
		_phsProjLocalService = phsProjLocalService;
	}

	private PhsProjLocalService _phsProjLocalService;
}