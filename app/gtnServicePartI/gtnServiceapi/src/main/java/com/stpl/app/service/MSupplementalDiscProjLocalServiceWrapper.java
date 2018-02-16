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
 * Provides a wrapper for {@link MSupplementalDiscProjLocalService}.
 *
 * @author
 * @see MSupplementalDiscProjLocalService
 * @generated
 */
@ProviderType
public class MSupplementalDiscProjLocalServiceWrapper
	implements MSupplementalDiscProjLocalService,
		ServiceWrapper<MSupplementalDiscProjLocalService> {
	public MSupplementalDiscProjLocalServiceWrapper(
		MSupplementalDiscProjLocalService mSupplementalDiscProjLocalService) {
		_mSupplementalDiscProjLocalService = mSupplementalDiscProjLocalService;
	}

	/**
	* Adds the m supplemental disc proj to the database. Also notifies the appropriate model listeners.
	*
	* @param mSupplementalDiscProj the m supplemental disc proj
	* @return the m supplemental disc proj that was added
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscProj addMSupplementalDiscProj(
		com.stpl.app.model.MSupplementalDiscProj mSupplementalDiscProj) {
		return _mSupplementalDiscProjLocalService.addMSupplementalDiscProj(mSupplementalDiscProj);
	}

	/**
	* Creates a new m supplemental disc proj with the primary key. Does not add the m supplemental disc proj to the database.
	*
	* @param projectionDetailsSid the primary key for the new m supplemental disc proj
	* @return the new m supplemental disc proj
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscProj createMSupplementalDiscProj(
		int projectionDetailsSid) {
		return _mSupplementalDiscProjLocalService.createMSupplementalDiscProj(projectionDetailsSid);
	}

	/**
	* Deletes the m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the m supplemental disc proj
	* @return the m supplemental disc proj that was removed
	* @throws PortalException if a m supplemental disc proj with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscProj deleteMSupplementalDiscProj(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mSupplementalDiscProjLocalService.deleteMSupplementalDiscProj(projectionDetailsSid);
	}

	/**
	* Deletes the m supplemental disc proj from the database. Also notifies the appropriate model listeners.
	*
	* @param mSupplementalDiscProj the m supplemental disc proj
	* @return the m supplemental disc proj that was removed
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscProj deleteMSupplementalDiscProj(
		com.stpl.app.model.MSupplementalDiscProj mSupplementalDiscProj) {
		return _mSupplementalDiscProjLocalService.deleteMSupplementalDiscProj(mSupplementalDiscProj);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mSupplementalDiscProjLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mSupplementalDiscProjLocalService.dynamicQuery();
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
		return _mSupplementalDiscProjLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mSupplementalDiscProjLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mSupplementalDiscProjLocalService.dynamicQuery(dynamicQuery,
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
		return _mSupplementalDiscProjLocalService.dynamicQueryCount(dynamicQuery);
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
		return _mSupplementalDiscProjLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.MSupplementalDiscProj fetchMSupplementalDiscProj(
		int projectionDetailsSid) {
		return _mSupplementalDiscProjLocalService.fetchMSupplementalDiscProj(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _mSupplementalDiscProjLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _mSupplementalDiscProjLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the m supplemental disc proj with the primary key.
	*
	* @param projectionDetailsSid the primary key of the m supplemental disc proj
	* @return the m supplemental disc proj
	* @throws PortalException if a m supplemental disc proj with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscProj getMSupplementalDiscProj(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mSupplementalDiscProjLocalService.getMSupplementalDiscProj(projectionDetailsSid);
	}

	/**
	* Returns a range of all the m supplemental disc projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m supplemental disc projs
	* @param end the upper bound of the range of m supplemental disc projs (not inclusive)
	* @return the range of m supplemental disc projs
	*/
	@Override
	public java.util.List<com.stpl.app.model.MSupplementalDiscProj> getMSupplementalDiscProjs(
		int start, int end) {
		return _mSupplementalDiscProjLocalService.getMSupplementalDiscProjs(start,
			end);
	}

	/**
	* Returns the number of m supplemental disc projs.
	*
	* @return the number of m supplemental disc projs
	*/
	@Override
	public int getMSupplementalDiscProjsCount() {
		return _mSupplementalDiscProjLocalService.getMSupplementalDiscProjsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _mSupplementalDiscProjLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mSupplementalDiscProjLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the m supplemental disc proj in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mSupplementalDiscProj the m supplemental disc proj
	* @return the m supplemental disc proj that was updated
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscProj updateMSupplementalDiscProj(
		com.stpl.app.model.MSupplementalDiscProj mSupplementalDiscProj) {
		return _mSupplementalDiscProjLocalService.updateMSupplementalDiscProj(mSupplementalDiscProj);
	}

	@Override
	public MSupplementalDiscProjLocalService getWrappedService() {
		return _mSupplementalDiscProjLocalService;
	}

	@Override
	public void setWrappedService(
		MSupplementalDiscProjLocalService mSupplementalDiscProjLocalService) {
		_mSupplementalDiscProjLocalService = mSupplementalDiscProjLocalService;
	}

	private MSupplementalDiscProjLocalService _mSupplementalDiscProjLocalService;
}