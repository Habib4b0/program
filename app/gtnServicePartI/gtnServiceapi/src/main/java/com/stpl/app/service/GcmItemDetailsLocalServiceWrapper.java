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
 * Provides a wrapper for {@link GcmItemDetailsLocalService}.
 *
 * @author
 * @see GcmItemDetailsLocalService
 * @generated
 */
@ProviderType
public class GcmItemDetailsLocalServiceWrapper
	implements GcmItemDetailsLocalService,
		ServiceWrapper<GcmItemDetailsLocalService> {
	public GcmItemDetailsLocalServiceWrapper(
		GcmItemDetailsLocalService gcmItemDetailsLocalService) {
		_gcmItemDetailsLocalService = gcmItemDetailsLocalService;
	}

	/**
	* Adds the gcm item details to the database. Also notifies the appropriate model listeners.
	*
	* @param gcmItemDetails the gcm item details
	* @return the gcm item details that was added
	*/
	@Override
	public com.stpl.app.model.GcmItemDetails addGcmItemDetails(
		com.stpl.app.model.GcmItemDetails gcmItemDetails) {
		return _gcmItemDetailsLocalService.addGcmItemDetails(gcmItemDetails);
	}

	/**
	* Creates a new gcm item details with the primary key. Does not add the gcm item details to the database.
	*
	* @param gcmItemDetailsSid the primary key for the new gcm item details
	* @return the new gcm item details
	*/
	@Override
	public com.stpl.app.model.GcmItemDetails createGcmItemDetails(
		int gcmItemDetailsSid) {
		return _gcmItemDetailsLocalService.createGcmItemDetails(gcmItemDetailsSid);
	}

	/**
	* Deletes the gcm item details from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmItemDetails the gcm item details
	* @return the gcm item details that was removed
	*/
	@Override
	public com.stpl.app.model.GcmItemDetails deleteGcmItemDetails(
		com.stpl.app.model.GcmItemDetails gcmItemDetails) {
		return _gcmItemDetailsLocalService.deleteGcmItemDetails(gcmItemDetails);
	}

	/**
	* Deletes the gcm item details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmItemDetailsSid the primary key of the gcm item details
	* @return the gcm item details that was removed
	* @throws PortalException if a gcm item details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.GcmItemDetails deleteGcmItemDetails(
		int gcmItemDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _gcmItemDetailsLocalService.deleteGcmItemDetails(gcmItemDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _gcmItemDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _gcmItemDetailsLocalService.dynamicQuery();
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
		return _gcmItemDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _gcmItemDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _gcmItemDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
		return _gcmItemDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _gcmItemDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.GcmItemDetails fetchGcmItemDetails(
		int gcmItemDetailsSid) {
		return _gcmItemDetailsLocalService.fetchGcmItemDetails(gcmItemDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _gcmItemDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the gcm item details with the primary key.
	*
	* @param gcmItemDetailsSid the primary key of the gcm item details
	* @return the gcm item details
	* @throws PortalException if a gcm item details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.GcmItemDetails getGcmItemDetails(
		int gcmItemDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _gcmItemDetailsLocalService.getGcmItemDetails(gcmItemDetailsSid);
	}

	/**
	* Returns a range of all the gcm item detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm item detailses
	* @param end the upper bound of the range of gcm item detailses (not inclusive)
	* @return the range of gcm item detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.GcmItemDetails> getGcmItemDetailses(
		int start, int end) {
		return _gcmItemDetailsLocalService.getGcmItemDetailses(start, end);
	}

	/**
	* Returns the number of gcm item detailses.
	*
	* @return the number of gcm item detailses
	*/
	@Override
	public int getGcmItemDetailsesCount() {
		return _gcmItemDetailsLocalService.getGcmItemDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _gcmItemDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _gcmItemDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _gcmItemDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the gcm item details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param gcmItemDetails the gcm item details
	* @return the gcm item details that was updated
	*/
	@Override
	public com.stpl.app.model.GcmItemDetails updateGcmItemDetails(
		com.stpl.app.model.GcmItemDetails gcmItemDetails) {
		return _gcmItemDetailsLocalService.updateGcmItemDetails(gcmItemDetails);
	}

	@Override
	public GcmItemDetailsLocalService getWrappedService() {
		return _gcmItemDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		GcmItemDetailsLocalService gcmItemDetailsLocalService) {
		_gcmItemDetailsLocalService = gcmItemDetailsLocalService;
	}

	private GcmItemDetailsLocalService _gcmItemDetailsLocalService;
}