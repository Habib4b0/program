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
 * Provides a wrapper for {@link CustomViewMasterLocalService}.
 *
 * @author
 * @see CustomViewMasterLocalService
 * @generated
 */
@ProviderType
public class CustomViewMasterLocalServiceWrapper
	implements CustomViewMasterLocalService,
		ServiceWrapper<CustomViewMasterLocalService> {
	public CustomViewMasterLocalServiceWrapper(
		CustomViewMasterLocalService customViewMasterLocalService) {
		_customViewMasterLocalService = customViewMasterLocalService;
	}

	/**
	* Adds the custom view master to the database. Also notifies the appropriate model listeners.
	*
	* @param customViewMaster the custom view master
	* @return the custom view master that was added
	*/
	@Override
	public com.stpl.app.model.CustomViewMaster addCustomViewMaster(
		com.stpl.app.model.CustomViewMaster customViewMaster) {
		return _customViewMasterLocalService.addCustomViewMaster(customViewMaster);
	}

	/**
	* Creates a new custom view master with the primary key. Does not add the custom view master to the database.
	*
	* @param customViewMasterSid the primary key for the new custom view master
	* @return the new custom view master
	*/
	@Override
	public com.stpl.app.model.CustomViewMaster createCustomViewMaster(
		int customViewMasterSid) {
		return _customViewMasterLocalService.createCustomViewMaster(customViewMasterSid);
	}

	/**
	* Deletes the custom view master from the database. Also notifies the appropriate model listeners.
	*
	* @param customViewMaster the custom view master
	* @return the custom view master that was removed
	*/
	@Override
	public com.stpl.app.model.CustomViewMaster deleteCustomViewMaster(
		com.stpl.app.model.CustomViewMaster customViewMaster) {
		return _customViewMasterLocalService.deleteCustomViewMaster(customViewMaster);
	}

	/**
	* Deletes the custom view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customViewMasterSid the primary key of the custom view master
	* @return the custom view master that was removed
	* @throws PortalException if a custom view master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CustomViewMaster deleteCustomViewMaster(
		int customViewMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customViewMasterLocalService.deleteCustomViewMaster(customViewMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customViewMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _customViewMasterLocalService.dynamicQuery();
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
		return _customViewMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _customViewMasterLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _customViewMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _customViewMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _customViewMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.CustomViewMaster fetchCustomViewMaster(
		int customViewMasterSid) {
		return _customViewMasterLocalService.fetchCustomViewMaster(customViewMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _customViewMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the custom view master with the primary key.
	*
	* @param customViewMasterSid the primary key of the custom view master
	* @return the custom view master
	* @throws PortalException if a custom view master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CustomViewMaster getCustomViewMaster(
		int customViewMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customViewMasterLocalService.getCustomViewMaster(customViewMasterSid);
	}

	/**
	* Returns a range of all the custom view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view masters
	* @param end the upper bound of the range of custom view masters (not inclusive)
	* @return the range of custom view masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.CustomViewMaster> getCustomViewMasters(
		int start, int end) {
		return _customViewMasterLocalService.getCustomViewMasters(start, end);
	}

	/**
	* Returns the number of custom view masters.
	*
	* @return the number of custom view masters
	*/
	@Override
	public int getCustomViewMastersCount() {
		return _customViewMasterLocalService.getCustomViewMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _customViewMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _customViewMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customViewMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the custom view master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param customViewMaster the custom view master
	* @return the custom view master that was updated
	*/
	@Override
	public com.stpl.app.model.CustomViewMaster updateCustomViewMaster(
		com.stpl.app.model.CustomViewMaster customViewMaster) {
		return _customViewMasterLocalService.updateCustomViewMaster(customViewMaster);
	}

	@Override
	public CustomViewMasterLocalService getWrappedService() {
		return _customViewMasterLocalService;
	}

	@Override
	public void setWrappedService(
		CustomViewMasterLocalService customViewMasterLocalService) {
		_customViewMasterLocalService = customViewMasterLocalService;
	}

	private CustomViewMasterLocalService _customViewMasterLocalService;
}