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

package com.stpl.app.parttwo.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StCffOutboundMasterLocalService}.
 *
 * @author
 * @see StCffOutboundMasterLocalService
 * @generated
 */
@ProviderType
public class StCffOutboundMasterLocalServiceWrapper
	implements StCffOutboundMasterLocalService,
		ServiceWrapper<StCffOutboundMasterLocalService> {
	public StCffOutboundMasterLocalServiceWrapper(
		StCffOutboundMasterLocalService stCffOutboundMasterLocalService) {
		_stCffOutboundMasterLocalService = stCffOutboundMasterLocalService;
	}

	/**
	* Adds the st cff outbound master to the database. Also notifies the appropriate model listeners.
	*
	* @param stCffOutboundMaster the st cff outbound master
	* @return the st cff outbound master that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.StCffOutboundMaster addStCffOutboundMaster(
		com.stpl.app.parttwo.model.StCffOutboundMaster stCffOutboundMaster) {
		return _stCffOutboundMasterLocalService.addStCffOutboundMaster(stCffOutboundMaster);
	}

	/**
	* Creates a new st cff outbound master with the primary key. Does not add the st cff outbound master to the database.
	*
	* @param stCffOutboundMasterPK the primary key for the new st cff outbound master
	* @return the new st cff outbound master
	*/
	@Override
	public com.stpl.app.parttwo.model.StCffOutboundMaster createStCffOutboundMaster(
		com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK stCffOutboundMasterPK) {
		return _stCffOutboundMasterLocalService.createStCffOutboundMaster(stCffOutboundMasterPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stCffOutboundMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st cff outbound master from the database. Also notifies the appropriate model listeners.
	*
	* @param stCffOutboundMaster the st cff outbound master
	* @return the st cff outbound master that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.StCffOutboundMaster deleteStCffOutboundMaster(
		com.stpl.app.parttwo.model.StCffOutboundMaster stCffOutboundMaster) {
		return _stCffOutboundMasterLocalService.deleteStCffOutboundMaster(stCffOutboundMaster);
	}

	/**
	* Deletes the st cff outbound master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stCffOutboundMasterPK the primary key of the st cff outbound master
	* @return the st cff outbound master that was removed
	* @throws PortalException if a st cff outbound master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.StCffOutboundMaster deleteStCffOutboundMaster(
		com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK stCffOutboundMasterPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stCffOutboundMasterLocalService.deleteStCffOutboundMaster(stCffOutboundMasterPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stCffOutboundMasterLocalService.dynamicQuery();
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
		return _stCffOutboundMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stCffOutboundMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stCffOutboundMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _stCffOutboundMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stCffOutboundMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.StCffOutboundMaster fetchStCffOutboundMaster(
		com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK stCffOutboundMasterPK) {
		return _stCffOutboundMasterLocalService.fetchStCffOutboundMaster(stCffOutboundMasterPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stCffOutboundMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stCffOutboundMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stCffOutboundMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stCffOutboundMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st cff outbound master with the primary key.
	*
	* @param stCffOutboundMasterPK the primary key of the st cff outbound master
	* @return the st cff outbound master
	* @throws PortalException if a st cff outbound master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.StCffOutboundMaster getStCffOutboundMaster(
		com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK stCffOutboundMasterPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stCffOutboundMasterLocalService.getStCffOutboundMaster(stCffOutboundMasterPK);
	}

	/**
	* Returns a range of all the st cff outbound masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st cff outbound masters
	* @param end the upper bound of the range of st cff outbound masters (not inclusive)
	* @return the range of st cff outbound masters
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.StCffOutboundMaster> getStCffOutboundMasters(
		int start, int end) {
		return _stCffOutboundMasterLocalService.getStCffOutboundMasters(start,
			end);
	}

	/**
	* Returns the number of st cff outbound masters.
	*
	* @return the number of st cff outbound masters
	*/
	@Override
	public int getStCffOutboundMastersCount() {
		return _stCffOutboundMasterLocalService.getStCffOutboundMastersCount();
	}

	/**
	* Updates the st cff outbound master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stCffOutboundMaster the st cff outbound master
	* @return the st cff outbound master that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.StCffOutboundMaster updateStCffOutboundMaster(
		com.stpl.app.parttwo.model.StCffOutboundMaster stCffOutboundMaster) {
		return _stCffOutboundMasterLocalService.updateStCffOutboundMaster(stCffOutboundMaster);
	}

	@Override
	public StCffOutboundMasterLocalService getWrappedService() {
		return _stCffOutboundMasterLocalService;
	}

	@Override
	public void setWrappedService(
		StCffOutboundMasterLocalService stCffOutboundMasterLocalService) {
		_stCffOutboundMasterLocalService = stCffOutboundMasterLocalService;
	}

	private StCffOutboundMasterLocalService _stCffOutboundMasterLocalService;
}