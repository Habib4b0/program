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
 * Provides a wrapper for {@link RsContractDetailsLocalService}.
 *
 * @author
 * @see RsContractDetailsLocalService
 * @generated
 */
@ProviderType
public class RsContractDetailsLocalServiceWrapper
	implements RsContractDetailsLocalService,
		ServiceWrapper<RsContractDetailsLocalService> {
	public RsContractDetailsLocalServiceWrapper(
		RsContractDetailsLocalService rsContractDetailsLocalService) {
		_rsContractDetailsLocalService = rsContractDetailsLocalService;
	}

	/**
	* Adds the rs contract details to the database. Also notifies the appropriate model listeners.
	*
	* @param rsContractDetails the rs contract details
	* @return the rs contract details that was added
	*/
	@Override
	public com.stpl.app.model.RsContractDetails addRsContractDetails(
		com.stpl.app.model.RsContractDetails rsContractDetails) {
		return _rsContractDetailsLocalService.addRsContractDetails(rsContractDetails);
	}

	/**
	* Creates a new rs contract details with the primary key. Does not add the rs contract details to the database.
	*
	* @param rsContractDetailsSid the primary key for the new rs contract details
	* @return the new rs contract details
	*/
	@Override
	public com.stpl.app.model.RsContractDetails createRsContractDetails(
		int rsContractDetailsSid) {
		return _rsContractDetailsLocalService.createRsContractDetails(rsContractDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsContractDetailsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the rs contract details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsContractDetailsSid the primary key of the rs contract details
	* @return the rs contract details that was removed
	* @throws PortalException if a rs contract details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RsContractDetails deleteRsContractDetails(
		int rsContractDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsContractDetailsLocalService.deleteRsContractDetails(rsContractDetailsSid);
	}

	/**
	* Deletes the rs contract details from the database. Also notifies the appropriate model listeners.
	*
	* @param rsContractDetails the rs contract details
	* @return the rs contract details that was removed
	*/
	@Override
	public com.stpl.app.model.RsContractDetails deleteRsContractDetails(
		com.stpl.app.model.RsContractDetails rsContractDetails) {
		return _rsContractDetailsLocalService.deleteRsContractDetails(rsContractDetails);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _rsContractDetailsLocalService.dynamicQuery();
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
		return _rsContractDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _rsContractDetailsLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _rsContractDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
		return _rsContractDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _rsContractDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.RsContractDetails fetchRsContractDetails(
		int rsContractDetailsSid) {
		return _rsContractDetailsLocalService.fetchRsContractDetails(rsContractDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _rsContractDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _rsContractDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _rsContractDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsContractDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the rs contract details with the primary key.
	*
	* @param rsContractDetailsSid the primary key of the rs contract details
	* @return the rs contract details
	* @throws PortalException if a rs contract details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RsContractDetails getRsContractDetails(
		int rsContractDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsContractDetailsLocalService.getRsContractDetails(rsContractDetailsSid);
	}

	/**
	* Returns a range of all the rs contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @return the range of rs contract detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.RsContractDetails> getRsContractDetailses(
		int start, int end) {
		return _rsContractDetailsLocalService.getRsContractDetailses(start, end);
	}

	/**
	* Returns the number of rs contract detailses.
	*
	* @return the number of rs contract detailses
	*/
	@Override
	public int getRsContractDetailsesCount() {
		return _rsContractDetailsLocalService.getRsContractDetailsesCount();
	}

	/**
	* Updates the rs contract details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rsContractDetails the rs contract details
	* @return the rs contract details that was updated
	*/
	@Override
	public com.stpl.app.model.RsContractDetails updateRsContractDetails(
		com.stpl.app.model.RsContractDetails rsContractDetails) {
		return _rsContractDetailsLocalService.updateRsContractDetails(rsContractDetails);
	}

	@Override
	public RsContractDetailsLocalService getWrappedService() {
		return _rsContractDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		RsContractDetailsLocalService rsContractDetailsLocalService) {
		_rsContractDetailsLocalService = rsContractDetailsLocalService;
	}

	private RsContractDetailsLocalService _rsContractDetailsLocalService;
}