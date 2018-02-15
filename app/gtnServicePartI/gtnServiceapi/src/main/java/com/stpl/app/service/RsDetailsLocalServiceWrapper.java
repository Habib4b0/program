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
 * Provides a wrapper for {@link RsDetailsLocalService}.
 *
 * @author
 * @see RsDetailsLocalService
 * @generated
 */
@ProviderType
public class RsDetailsLocalServiceWrapper implements RsDetailsLocalService,
	ServiceWrapper<RsDetailsLocalService> {
	public RsDetailsLocalServiceWrapper(
		RsDetailsLocalService rsDetailsLocalService) {
		_rsDetailsLocalService = rsDetailsLocalService;
	}

	/**
	* Adds the rs details to the database. Also notifies the appropriate model listeners.
	*
	* @param rsDetails the rs details
	* @return the rs details that was added
	*/
	@Override
	public com.stpl.app.model.RsDetails addRsDetails(
		com.stpl.app.model.RsDetails rsDetails) {
		return _rsDetailsLocalService.addRsDetails(rsDetails);
	}

	/**
	* Creates a new rs details with the primary key. Does not add the rs details to the database.
	*
	* @param rsDetailsSid the primary key for the new rs details
	* @return the new rs details
	*/
	@Override
	public com.stpl.app.model.RsDetails createRsDetails(int rsDetailsSid) {
		return _rsDetailsLocalService.createRsDetails(rsDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsDetailsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the rs details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsDetailsSid the primary key of the rs details
	* @return the rs details that was removed
	* @throws PortalException if a rs details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RsDetails deleteRsDetails(int rsDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsDetailsLocalService.deleteRsDetails(rsDetailsSid);
	}

	/**
	* Deletes the rs details from the database. Also notifies the appropriate model listeners.
	*
	* @param rsDetails the rs details
	* @return the rs details that was removed
	*/
	@Override
	public com.stpl.app.model.RsDetails deleteRsDetails(
		com.stpl.app.model.RsDetails rsDetails) {
		return _rsDetailsLocalService.deleteRsDetails(rsDetails);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _rsDetailsLocalService.dynamicQuery();
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
		return _rsDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _rsDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _rsDetailsLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _rsDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _rsDetailsLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.stpl.app.model.RsDetails fetchRsDetails(int rsDetailsSid) {
		return _rsDetailsLocalService.fetchRsDetails(rsDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _rsDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _rsDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _rsDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the rs details with the primary key.
	*
	* @param rsDetailsSid the primary key of the rs details
	* @return the rs details
	* @throws PortalException if a rs details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RsDetails getRsDetails(int rsDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsDetailsLocalService.getRsDetails(rsDetailsSid);
	}

	/**
	* Returns a range of all the rs detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs detailses
	* @param end the upper bound of the range of rs detailses (not inclusive)
	* @return the range of rs detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.RsDetails> getRsDetailses(
		int start, int end) {
		return _rsDetailsLocalService.getRsDetailses(start, end);
	}

	/**
	* Returns the number of rs detailses.
	*
	* @return the number of rs detailses
	*/
	@Override
	public int getRsDetailsesCount() {
		return _rsDetailsLocalService.getRsDetailsesCount();
	}

	/**
	* Updates the rs details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rsDetails the rs details
	* @return the rs details that was updated
	*/
	@Override
	public com.stpl.app.model.RsDetails updateRsDetails(
		com.stpl.app.model.RsDetails rsDetails) {
		return _rsDetailsLocalService.updateRsDetails(rsDetails);
	}

	@Override
	public RsDetailsLocalService getWrappedService() {
		return _rsDetailsLocalService;
	}

	@Override
	public void setWrappedService(RsDetailsLocalService rsDetailsLocalService) {
		_rsDetailsLocalService = rsDetailsLocalService;
	}

	private RsDetailsLocalService _rsDetailsLocalService;
}