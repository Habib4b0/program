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
 * Provides a wrapper for {@link IfpContractDetailsLocalService}.
 *
 * @author
 * @see IfpContractDetailsLocalService
 * @generated
 */
@ProviderType
public class IfpContractDetailsLocalServiceWrapper
	implements IfpContractDetailsLocalService,
		ServiceWrapper<IfpContractDetailsLocalService> {
	public IfpContractDetailsLocalServiceWrapper(
		IfpContractDetailsLocalService ifpContractDetailsLocalService) {
		_ifpContractDetailsLocalService = ifpContractDetailsLocalService;
	}

	/**
	* Adds the ifp contract details to the database. Also notifies the appropriate model listeners.
	*
	* @param ifpContractDetails the ifp contract details
	* @return the ifp contract details that was added
	*/
	@Override
	public com.stpl.app.model.IfpContractDetails addIfpContractDetails(
		com.stpl.app.model.IfpContractDetails ifpContractDetails) {
		return _ifpContractDetailsLocalService.addIfpContractDetails(ifpContractDetails);
	}

	/**
	* Creates a new ifp contract details with the primary key. Does not add the ifp contract details to the database.
	*
	* @param ifpContractDetailsSid the primary key for the new ifp contract details
	* @return the new ifp contract details
	*/
	@Override
	public com.stpl.app.model.IfpContractDetails createIfpContractDetails(
		int ifpContractDetailsSid) {
		return _ifpContractDetailsLocalService.createIfpContractDetails(ifpContractDetailsSid);
	}

	/**
	* Deletes the ifp contract details from the database. Also notifies the appropriate model listeners.
	*
	* @param ifpContractDetails the ifp contract details
	* @return the ifp contract details that was removed
	*/
	@Override
	public com.stpl.app.model.IfpContractDetails deleteIfpContractDetails(
		com.stpl.app.model.IfpContractDetails ifpContractDetails) {
		return _ifpContractDetailsLocalService.deleteIfpContractDetails(ifpContractDetails);
	}

	/**
	* Deletes the ifp contract details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ifpContractDetailsSid the primary key of the ifp contract details
	* @return the ifp contract details that was removed
	* @throws PortalException if a ifp contract details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IfpContractDetails deleteIfpContractDetails(
		int ifpContractDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ifpContractDetailsLocalService.deleteIfpContractDetails(ifpContractDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ifpContractDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ifpContractDetailsLocalService.dynamicQuery();
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
		return _ifpContractDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ifpContractDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ifpContractDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _ifpContractDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ifpContractDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.IfpContractDetails fetchIfpContractDetails(
		int ifpContractDetailsSid) {
		return _ifpContractDetailsLocalService.fetchIfpContractDetails(ifpContractDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ifpContractDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the ifp contract details with the primary key.
	*
	* @param ifpContractDetailsSid the primary key of the ifp contract details
	* @return the ifp contract details
	* @throws PortalException if a ifp contract details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IfpContractDetails getIfpContractDetails(
		int ifpContractDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ifpContractDetailsLocalService.getIfpContractDetails(ifpContractDetailsSid);
	}

	/**
	* Returns a range of all the ifp contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp contract detailses
	* @param end the upper bound of the range of ifp contract detailses (not inclusive)
	* @return the range of ifp contract detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.IfpContractDetails> getIfpContractDetailses(
		int start, int end) {
		return _ifpContractDetailsLocalService.getIfpContractDetailses(start,
			end);
	}

	/**
	* Returns the number of ifp contract detailses.
	*
	* @return the number of ifp contract detailses
	*/
	@Override
	public int getIfpContractDetailsesCount() {
		return _ifpContractDetailsLocalService.getIfpContractDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ifpContractDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ifpContractDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ifpContractDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ifp contract details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ifpContractDetails the ifp contract details
	* @return the ifp contract details that was updated
	*/
	@Override
	public com.stpl.app.model.IfpContractDetails updateIfpContractDetails(
		com.stpl.app.model.IfpContractDetails ifpContractDetails) {
		return _ifpContractDetailsLocalService.updateIfpContractDetails(ifpContractDetails);
	}

	@Override
	public IfpContractDetailsLocalService getWrappedService() {
		return _ifpContractDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		IfpContractDetailsLocalService ifpContractDetailsLocalService) {
		_ifpContractDetailsLocalService = ifpContractDetailsLocalService;
	}

	private IfpContractDetailsLocalService _ifpContractDetailsLocalService;
}