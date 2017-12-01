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
 * Provides a wrapper for {@link StNmDiscountProjMasterLocalService}.
 *
 * @author
 * @see StNmDiscountProjMasterLocalService
 * @generated
 */
@ProviderType
public class StNmDiscountProjMasterLocalServiceWrapper
	implements StNmDiscountProjMasterLocalService,
		ServiceWrapper<StNmDiscountProjMasterLocalService> {
	public StNmDiscountProjMasterLocalServiceWrapper(
		StNmDiscountProjMasterLocalService stNmDiscountProjMasterLocalService) {
		_stNmDiscountProjMasterLocalService = stNmDiscountProjMasterLocalService;
	}

	/**
	* Adds the st nm discount proj master to the database. Also notifies the appropriate model listeners.
	*
	* @param stNmDiscountProjMaster the st nm discount proj master
	* @return the st nm discount proj master that was added
	*/
	@Override
	public com.stpl.app.model.StNmDiscountProjMaster addStNmDiscountProjMaster(
		com.stpl.app.model.StNmDiscountProjMaster stNmDiscountProjMaster) {
		return _stNmDiscountProjMasterLocalService.addStNmDiscountProjMaster(stNmDiscountProjMaster);
	}

	/**
	* Creates a new st nm discount proj master with the primary key. Does not add the st nm discount proj master to the database.
	*
	* @param stNmDiscountProjMasterPK the primary key for the new st nm discount proj master
	* @return the new st nm discount proj master
	*/
	@Override
	public com.stpl.app.model.StNmDiscountProjMaster createStNmDiscountProjMaster(
		com.stpl.app.service.persistence.StNmDiscountProjMasterPK stNmDiscountProjMasterPK) {
		return _stNmDiscountProjMasterLocalService.createStNmDiscountProjMaster(stNmDiscountProjMasterPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmDiscountProjMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st nm discount proj master from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmDiscountProjMaster the st nm discount proj master
	* @return the st nm discount proj master that was removed
	*/
	@Override
	public com.stpl.app.model.StNmDiscountProjMaster deleteStNmDiscountProjMaster(
		com.stpl.app.model.StNmDiscountProjMaster stNmDiscountProjMaster) {
		return _stNmDiscountProjMasterLocalService.deleteStNmDiscountProjMaster(stNmDiscountProjMaster);
	}

	/**
	* Deletes the st nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
	* @return the st nm discount proj master that was removed
	* @throws PortalException if a st nm discount proj master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmDiscountProjMaster deleteStNmDiscountProjMaster(
		com.stpl.app.service.persistence.StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmDiscountProjMasterLocalService.deleteStNmDiscountProjMaster(stNmDiscountProjMasterPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stNmDiscountProjMasterLocalService.dynamicQuery();
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
		return _stNmDiscountProjMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _stNmDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stNmDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StNmDiscountProjMaster fetchStNmDiscountProjMaster(
		com.stpl.app.service.persistence.StNmDiscountProjMasterPK stNmDiscountProjMasterPK) {
		return _stNmDiscountProjMasterLocalService.fetchStNmDiscountProjMaster(stNmDiscountProjMasterPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stNmDiscountProjMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stNmDiscountProjMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stNmDiscountProjMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmDiscountProjMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st nm discount proj master with the primary key.
	*
	* @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
	* @return the st nm discount proj master
	* @throws PortalException if a st nm discount proj master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmDiscountProjMaster getStNmDiscountProjMaster(
		com.stpl.app.service.persistence.StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmDiscountProjMasterLocalService.getStNmDiscountProjMaster(stNmDiscountProjMasterPK);
	}

	/**
	* Returns a range of all the st nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount proj masters
	* @param end the upper bound of the range of st nm discount proj masters (not inclusive)
	* @return the range of st nm discount proj masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.StNmDiscountProjMaster> getStNmDiscountProjMasters(
		int start, int end) {
		return _stNmDiscountProjMasterLocalService.getStNmDiscountProjMasters(start,
			end);
	}

	/**
	* Returns the number of st nm discount proj masters.
	*
	* @return the number of st nm discount proj masters
	*/
	@Override
	public int getStNmDiscountProjMastersCount() {
		return _stNmDiscountProjMasterLocalService.getStNmDiscountProjMastersCount();
	}

	/**
	* Updates the st nm discount proj master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stNmDiscountProjMaster the st nm discount proj master
	* @return the st nm discount proj master that was updated
	*/
	@Override
	public com.stpl.app.model.StNmDiscountProjMaster updateStNmDiscountProjMaster(
		com.stpl.app.model.StNmDiscountProjMaster stNmDiscountProjMaster) {
		return _stNmDiscountProjMasterLocalService.updateStNmDiscountProjMaster(stNmDiscountProjMaster);
	}

	@Override
	public StNmDiscountProjMasterLocalService getWrappedService() {
		return _stNmDiscountProjMasterLocalService;
	}

	@Override
	public void setWrappedService(
		StNmDiscountProjMasterLocalService stNmDiscountProjMasterLocalService) {
		_stNmDiscountProjMasterLocalService = stNmDiscountProjMasterLocalService;
	}

	private StNmDiscountProjMasterLocalService _stNmDiscountProjMasterLocalService;
}