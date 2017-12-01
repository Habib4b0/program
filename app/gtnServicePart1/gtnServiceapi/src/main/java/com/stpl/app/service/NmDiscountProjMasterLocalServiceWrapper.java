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
 * Provides a wrapper for {@link NmDiscountProjMasterLocalService}.
 *
 * @author
 * @see NmDiscountProjMasterLocalService
 * @generated
 */
@ProviderType
public class NmDiscountProjMasterLocalServiceWrapper
	implements NmDiscountProjMasterLocalService,
		ServiceWrapper<NmDiscountProjMasterLocalService> {
	public NmDiscountProjMasterLocalServiceWrapper(
		NmDiscountProjMasterLocalService nmDiscountProjMasterLocalService) {
		_nmDiscountProjMasterLocalService = nmDiscountProjMasterLocalService;
	}

	/**
	* Adds the nm discount proj master to the database. Also notifies the appropriate model listeners.
	*
	* @param nmDiscountProjMaster the nm discount proj master
	* @return the nm discount proj master that was added
	*/
	@Override
	public com.stpl.app.model.NmDiscountProjMaster addNmDiscountProjMaster(
		com.stpl.app.model.NmDiscountProjMaster nmDiscountProjMaster) {
		return _nmDiscountProjMasterLocalService.addNmDiscountProjMaster(nmDiscountProjMaster);
	}

	/**
	* Creates a new nm discount proj master with the primary key. Does not add the nm discount proj master to the database.
	*
	* @param projectionDetailsSid the primary key for the new nm discount proj master
	* @return the new nm discount proj master
	*/
	@Override
	public com.stpl.app.model.NmDiscountProjMaster createNmDiscountProjMaster(
		int projectionDetailsSid) {
		return _nmDiscountProjMasterLocalService.createNmDiscountProjMaster(projectionDetailsSid);
	}

	/**
	* Deletes the nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the nm discount proj master
	* @return the nm discount proj master that was removed
	* @throws PortalException if a nm discount proj master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NmDiscountProjMaster deleteNmDiscountProjMaster(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmDiscountProjMasterLocalService.deleteNmDiscountProjMaster(projectionDetailsSid);
	}

	/**
	* Deletes the nm discount proj master from the database. Also notifies the appropriate model listeners.
	*
	* @param nmDiscountProjMaster the nm discount proj master
	* @return the nm discount proj master that was removed
	*/
	@Override
	public com.stpl.app.model.NmDiscountProjMaster deleteNmDiscountProjMaster(
		com.stpl.app.model.NmDiscountProjMaster nmDiscountProjMaster) {
		return _nmDiscountProjMasterLocalService.deleteNmDiscountProjMaster(nmDiscountProjMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmDiscountProjMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _nmDiscountProjMasterLocalService.dynamicQuery();
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
		return _nmDiscountProjMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nmDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nmDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _nmDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _nmDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.NmDiscountProjMaster fetchNmDiscountProjMaster(
		int projectionDetailsSid) {
		return _nmDiscountProjMasterLocalService.fetchNmDiscountProjMaster(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _nmDiscountProjMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _nmDiscountProjMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the nm discount proj master with the primary key.
	*
	* @param projectionDetailsSid the primary key of the nm discount proj master
	* @return the nm discount proj master
	* @throws PortalException if a nm discount proj master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NmDiscountProjMaster getNmDiscountProjMaster(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmDiscountProjMasterLocalService.getNmDiscountProjMaster(projectionDetailsSid);
	}

	/**
	* Returns a range of all the nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm discount proj masters
	* @param end the upper bound of the range of nm discount proj masters (not inclusive)
	* @return the range of nm discount proj masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.NmDiscountProjMaster> getNmDiscountProjMasters(
		int start, int end) {
		return _nmDiscountProjMasterLocalService.getNmDiscountProjMasters(start,
			end);
	}

	/**
	* Returns the number of nm discount proj masters.
	*
	* @return the number of nm discount proj masters
	*/
	@Override
	public int getNmDiscountProjMastersCount() {
		return _nmDiscountProjMasterLocalService.getNmDiscountProjMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _nmDiscountProjMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmDiscountProjMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the nm discount proj master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nmDiscountProjMaster the nm discount proj master
	* @return the nm discount proj master that was updated
	*/
	@Override
	public com.stpl.app.model.NmDiscountProjMaster updateNmDiscountProjMaster(
		com.stpl.app.model.NmDiscountProjMaster nmDiscountProjMaster) {
		return _nmDiscountProjMasterLocalService.updateNmDiscountProjMaster(nmDiscountProjMaster);
	}

	@Override
	public NmDiscountProjMasterLocalService getWrappedService() {
		return _nmDiscountProjMasterLocalService;
	}

	@Override
	public void setWrappedService(
		NmDiscountProjMasterLocalService nmDiscountProjMasterLocalService) {
		_nmDiscountProjMasterLocalService = nmDiscountProjMasterLocalService;
	}

	private NmDiscountProjMasterLocalService _nmDiscountProjMasterLocalService;
}