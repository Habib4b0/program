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
 * Provides a wrapper for {@link AccrualDetailsLocalService}.
 *
 * @author
 * @see AccrualDetailsLocalService
 * @generated
 */
@ProviderType
public class AccrualDetailsLocalServiceWrapper
	implements AccrualDetailsLocalService,
		ServiceWrapper<AccrualDetailsLocalService> {
	public AccrualDetailsLocalServiceWrapper(
		AccrualDetailsLocalService accrualDetailsLocalService) {
		_accrualDetailsLocalService = accrualDetailsLocalService;
	}

	/**
	* Adds the accrual details to the database. Also notifies the appropriate model listeners.
	*
	* @param accrualDetails the accrual details
	* @return the accrual details that was added
	*/
	@Override
	public com.stpl.app.model.AccrualDetails addAccrualDetails(
		com.stpl.app.model.AccrualDetails accrualDetails) {
		return _accrualDetailsLocalService.addAccrualDetails(accrualDetails);
	}

	/**
	* Creates a new accrual details with the primary key. Does not add the accrual details to the database.
	*
	* @param accrualDetailsSid the primary key for the new accrual details
	* @return the new accrual details
	*/
	@Override
	public com.stpl.app.model.AccrualDetails createAccrualDetails(
		int accrualDetailsSid) {
		return _accrualDetailsLocalService.createAccrualDetails(accrualDetailsSid);
	}

	/**
	* Deletes the accrual details from the database. Also notifies the appropriate model listeners.
	*
	* @param accrualDetails the accrual details
	* @return the accrual details that was removed
	*/
	@Override
	public com.stpl.app.model.AccrualDetails deleteAccrualDetails(
		com.stpl.app.model.AccrualDetails accrualDetails) {
		return _accrualDetailsLocalService.deleteAccrualDetails(accrualDetails);
	}

	/**
	* Deletes the accrual details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accrualDetailsSid the primary key of the accrual details
	* @return the accrual details that was removed
	* @throws PortalException if a accrual details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.AccrualDetails deleteAccrualDetails(
		int accrualDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accrualDetailsLocalService.deleteAccrualDetails(accrualDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accrualDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accrualDetailsLocalService.dynamicQuery();
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
		return _accrualDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accrualDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accrualDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
		return _accrualDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _accrualDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.AccrualDetails fetchAccrualDetails(
		int accrualDetailsSid) {
		return _accrualDetailsLocalService.fetchAccrualDetails(accrualDetailsSid);
	}

	/**
	* Returns the accrual details with the primary key.
	*
	* @param accrualDetailsSid the primary key of the accrual details
	* @return the accrual details
	* @throws PortalException if a accrual details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.AccrualDetails getAccrualDetails(
		int accrualDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accrualDetailsLocalService.getAccrualDetails(accrualDetailsSid);
	}

	/**
	* Returns a range of all the accrual detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of accrual detailses
	* @param end the upper bound of the range of accrual detailses (not inclusive)
	* @return the range of accrual detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.AccrualDetails> getAccrualDetailses(
		int start, int end) {
		return _accrualDetailsLocalService.getAccrualDetailses(start, end);
	}

	/**
	* Returns the number of accrual detailses.
	*
	* @return the number of accrual detailses
	*/
	@Override
	public int getAccrualDetailsesCount() {
		return _accrualDetailsLocalService.getAccrualDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _accrualDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _accrualDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accrualDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accrualDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the accrual details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accrualDetails the accrual details
	* @return the accrual details that was updated
	*/
	@Override
	public com.stpl.app.model.AccrualDetails updateAccrualDetails(
		com.stpl.app.model.AccrualDetails accrualDetails) {
		return _accrualDetailsLocalService.updateAccrualDetails(accrualDetails);
	}

	@Override
	public AccrualDetailsLocalService getWrappedService() {
		return _accrualDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		AccrualDetailsLocalService accrualDetailsLocalService) {
		_accrualDetailsLocalService = accrualDetailsLocalService;
	}

	private AccrualDetailsLocalService _accrualDetailsLocalService;
}