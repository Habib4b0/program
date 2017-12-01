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
 * Provides a wrapper for {@link StNmPpaProjectionMasterLocalService}.
 *
 * @author
 * @see StNmPpaProjectionMasterLocalService
 * @generated
 */
@ProviderType
public class StNmPpaProjectionMasterLocalServiceWrapper
	implements StNmPpaProjectionMasterLocalService,
		ServiceWrapper<StNmPpaProjectionMasterLocalService> {
	public StNmPpaProjectionMasterLocalServiceWrapper(
		StNmPpaProjectionMasterLocalService stNmPpaProjectionMasterLocalService) {
		_stNmPpaProjectionMasterLocalService = stNmPpaProjectionMasterLocalService;
	}

	/**
	* Adds the st nm ppa projection master to the database. Also notifies the appropriate model listeners.
	*
	* @param stNmPpaProjectionMaster the st nm ppa projection master
	* @return the st nm ppa projection master that was added
	*/
	@Override
	public com.stpl.app.model.StNmPpaProjectionMaster addStNmPpaProjectionMaster(
		com.stpl.app.model.StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		return _stNmPpaProjectionMasterLocalService.addStNmPpaProjectionMaster(stNmPpaProjectionMaster);
	}

	/**
	* Creates a new st nm ppa projection master with the primary key. Does not add the st nm ppa projection master to the database.
	*
	* @param stNmPpaProjectionMasterPK the primary key for the new st nm ppa projection master
	* @return the new st nm ppa projection master
	*/
	@Override
	public com.stpl.app.model.StNmPpaProjectionMaster createStNmPpaProjectionMaster(
		com.stpl.app.service.persistence.StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK) {
		return _stNmPpaProjectionMasterLocalService.createStNmPpaProjectionMaster(stNmPpaProjectionMasterPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmPpaProjectionMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st nm ppa projection master from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmPpaProjectionMaster the st nm ppa projection master
	* @return the st nm ppa projection master that was removed
	*/
	@Override
	public com.stpl.app.model.StNmPpaProjectionMaster deleteStNmPpaProjectionMaster(
		com.stpl.app.model.StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		return _stNmPpaProjectionMasterLocalService.deleteStNmPpaProjectionMaster(stNmPpaProjectionMaster);
	}

	/**
	* Deletes the st nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
	* @return the st nm ppa projection master that was removed
	* @throws PortalException if a st nm ppa projection master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmPpaProjectionMaster deleteStNmPpaProjectionMaster(
		com.stpl.app.service.persistence.StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmPpaProjectionMasterLocalService.deleteStNmPpaProjectionMaster(stNmPpaProjectionMasterPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stNmPpaProjectionMasterLocalService.dynamicQuery();
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
		return _stNmPpaProjectionMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmPpaProjectionMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmPpaProjectionMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _stNmPpaProjectionMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stNmPpaProjectionMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StNmPpaProjectionMaster fetchStNmPpaProjectionMaster(
		com.stpl.app.service.persistence.StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK) {
		return _stNmPpaProjectionMasterLocalService.fetchStNmPpaProjectionMaster(stNmPpaProjectionMasterPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stNmPpaProjectionMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stNmPpaProjectionMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stNmPpaProjectionMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmPpaProjectionMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st nm ppa projection master with the primary key.
	*
	* @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
	* @return the st nm ppa projection master
	* @throws PortalException if a st nm ppa projection master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmPpaProjectionMaster getStNmPpaProjectionMaster(
		com.stpl.app.service.persistence.StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmPpaProjectionMasterLocalService.getStNmPpaProjectionMaster(stNmPpaProjectionMasterPK);
	}

	/**
	* Returns a range of all the st nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm ppa projection masters
	* @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
	* @return the range of st nm ppa projection masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.StNmPpaProjectionMaster> getStNmPpaProjectionMasters(
		int start, int end) {
		return _stNmPpaProjectionMasterLocalService.getStNmPpaProjectionMasters(start,
			end);
	}

	/**
	* Returns the number of st nm ppa projection masters.
	*
	* @return the number of st nm ppa projection masters
	*/
	@Override
	public int getStNmPpaProjectionMastersCount() {
		return _stNmPpaProjectionMasterLocalService.getStNmPpaProjectionMastersCount();
	}

	/**
	* Updates the st nm ppa projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stNmPpaProjectionMaster the st nm ppa projection master
	* @return the st nm ppa projection master that was updated
	*/
	@Override
	public com.stpl.app.model.StNmPpaProjectionMaster updateStNmPpaProjectionMaster(
		com.stpl.app.model.StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		return _stNmPpaProjectionMasterLocalService.updateStNmPpaProjectionMaster(stNmPpaProjectionMaster);
	}

	@Override
	public StNmPpaProjectionMasterLocalService getWrappedService() {
		return _stNmPpaProjectionMasterLocalService;
	}

	@Override
	public void setWrappedService(
		StNmPpaProjectionMasterLocalService stNmPpaProjectionMasterLocalService) {
		_stNmPpaProjectionMasterLocalService = stNmPpaProjectionMasterLocalService;
	}

	private StNmPpaProjectionMasterLocalService _stNmPpaProjectionMasterLocalService;
}