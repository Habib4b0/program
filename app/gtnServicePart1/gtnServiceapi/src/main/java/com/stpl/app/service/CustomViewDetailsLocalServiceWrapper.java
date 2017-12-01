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
 * Provides a wrapper for {@link CustomViewDetailsLocalService}.
 *
 * @author
 * @see CustomViewDetailsLocalService
 * @generated
 */
@ProviderType
public class CustomViewDetailsLocalServiceWrapper
	implements CustomViewDetailsLocalService,
		ServiceWrapper<CustomViewDetailsLocalService> {
	public CustomViewDetailsLocalServiceWrapper(
		CustomViewDetailsLocalService customViewDetailsLocalService) {
		_customViewDetailsLocalService = customViewDetailsLocalService;
	}

	/**
	* Adds the custom view details to the database. Also notifies the appropriate model listeners.
	*
	* @param customViewDetails the custom view details
	* @return the custom view details that was added
	*/
	@Override
	public com.stpl.app.model.CustomViewDetails addCustomViewDetails(
		com.stpl.app.model.CustomViewDetails customViewDetails) {
		return _customViewDetailsLocalService.addCustomViewDetails(customViewDetails);
	}

	/**
	* Creates a new custom view details with the primary key. Does not add the custom view details to the database.
	*
	* @param customViewDetailsSid the primary key for the new custom view details
	* @return the new custom view details
	*/
	@Override
	public com.stpl.app.model.CustomViewDetails createCustomViewDetails(
		int customViewDetailsSid) {
		return _customViewDetailsLocalService.createCustomViewDetails(customViewDetailsSid);
	}

	/**
	* Deletes the custom view details from the database. Also notifies the appropriate model listeners.
	*
	* @param customViewDetails the custom view details
	* @return the custom view details that was removed
	*/
	@Override
	public com.stpl.app.model.CustomViewDetails deleteCustomViewDetails(
		com.stpl.app.model.CustomViewDetails customViewDetails) {
		return _customViewDetailsLocalService.deleteCustomViewDetails(customViewDetails);
	}

	/**
	* Deletes the custom view details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customViewDetailsSid the primary key of the custom view details
	* @return the custom view details that was removed
	* @throws PortalException if a custom view details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CustomViewDetails deleteCustomViewDetails(
		int customViewDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customViewDetailsLocalService.deleteCustomViewDetails(customViewDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customViewDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _customViewDetailsLocalService.dynamicQuery();
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
		return _customViewDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _customViewDetailsLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _customViewDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
		return _customViewDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _customViewDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.CustomViewDetails fetchCustomViewDetails(
		int customViewDetailsSid) {
		return _customViewDetailsLocalService.fetchCustomViewDetails(customViewDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _customViewDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the custom view details with the primary key.
	*
	* @param customViewDetailsSid the primary key of the custom view details
	* @return the custom view details
	* @throws PortalException if a custom view details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CustomViewDetails getCustomViewDetails(
		int customViewDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customViewDetailsLocalService.getCustomViewDetails(customViewDetailsSid);
	}

	/**
	* Returns a range of all the custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view detailses
	* @param end the upper bound of the range of custom view detailses (not inclusive)
	* @return the range of custom view detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.CustomViewDetails> getCustomViewDetailses(
		int start, int end) {
		return _customViewDetailsLocalService.getCustomViewDetailses(start, end);
	}

	/**
	* Returns the number of custom view detailses.
	*
	* @return the number of custom view detailses
	*/
	@Override
	public int getCustomViewDetailsesCount() {
		return _customViewDetailsLocalService.getCustomViewDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _customViewDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _customViewDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customViewDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the custom view details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param customViewDetails the custom view details
	* @return the custom view details that was updated
	*/
	@Override
	public com.stpl.app.model.CustomViewDetails updateCustomViewDetails(
		com.stpl.app.model.CustomViewDetails customViewDetails) {
		return _customViewDetailsLocalService.updateCustomViewDetails(customViewDetails);
	}

	@Override
	public CustomViewDetailsLocalService getWrappedService() {
		return _customViewDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		CustomViewDetailsLocalService customViewDetailsLocalService) {
		_customViewDetailsLocalService = customViewDetailsLocalService;
	}

	private CustomViewDetailsLocalService _customViewDetailsLocalService;
}