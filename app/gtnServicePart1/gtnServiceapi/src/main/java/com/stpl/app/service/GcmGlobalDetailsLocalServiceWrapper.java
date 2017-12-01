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
 * Provides a wrapper for {@link GcmGlobalDetailsLocalService}.
 *
 * @author
 * @see GcmGlobalDetailsLocalService
 * @generated
 */
@ProviderType
public class GcmGlobalDetailsLocalServiceWrapper
	implements GcmGlobalDetailsLocalService,
		ServiceWrapper<GcmGlobalDetailsLocalService> {
	public GcmGlobalDetailsLocalServiceWrapper(
		GcmGlobalDetailsLocalService gcmGlobalDetailsLocalService) {
		_gcmGlobalDetailsLocalService = gcmGlobalDetailsLocalService;
	}

	/**
	* Adds the gcm global details to the database. Also notifies the appropriate model listeners.
	*
	* @param gcmGlobalDetails the gcm global details
	* @return the gcm global details that was added
	*/
	@Override
	public com.stpl.app.model.GcmGlobalDetails addGcmGlobalDetails(
		com.stpl.app.model.GcmGlobalDetails gcmGlobalDetails) {
		return _gcmGlobalDetailsLocalService.addGcmGlobalDetails(gcmGlobalDetails);
	}

	/**
	* Creates a new gcm global details with the primary key. Does not add the gcm global details to the database.
	*
	* @param gcmGlobalDetailsSid the primary key for the new gcm global details
	* @return the new gcm global details
	*/
	@Override
	public com.stpl.app.model.GcmGlobalDetails createGcmGlobalDetails(
		int gcmGlobalDetailsSid) {
		return _gcmGlobalDetailsLocalService.createGcmGlobalDetails(gcmGlobalDetailsSid);
	}

	/**
	* Deletes the gcm global details from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmGlobalDetails the gcm global details
	* @return the gcm global details that was removed
	*/
	@Override
	public com.stpl.app.model.GcmGlobalDetails deleteGcmGlobalDetails(
		com.stpl.app.model.GcmGlobalDetails gcmGlobalDetails) {
		return _gcmGlobalDetailsLocalService.deleteGcmGlobalDetails(gcmGlobalDetails);
	}

	/**
	* Deletes the gcm global details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmGlobalDetailsSid the primary key of the gcm global details
	* @return the gcm global details that was removed
	* @throws PortalException if a gcm global details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.GcmGlobalDetails deleteGcmGlobalDetails(
		int gcmGlobalDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _gcmGlobalDetailsLocalService.deleteGcmGlobalDetails(gcmGlobalDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _gcmGlobalDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _gcmGlobalDetailsLocalService.dynamicQuery();
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
		return _gcmGlobalDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmGlobalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _gcmGlobalDetailsLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmGlobalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _gcmGlobalDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
		return _gcmGlobalDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _gcmGlobalDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.GcmGlobalDetails fetchGcmGlobalDetails(
		int gcmGlobalDetailsSid) {
		return _gcmGlobalDetailsLocalService.fetchGcmGlobalDetails(gcmGlobalDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _gcmGlobalDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the gcm global details with the primary key.
	*
	* @param gcmGlobalDetailsSid the primary key of the gcm global details
	* @return the gcm global details
	* @throws PortalException if a gcm global details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.GcmGlobalDetails getGcmGlobalDetails(
		int gcmGlobalDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _gcmGlobalDetailsLocalService.getGcmGlobalDetails(gcmGlobalDetailsSid);
	}

	/**
	* Returns a range of all the gcm global detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmGlobalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm global detailses
	* @param end the upper bound of the range of gcm global detailses (not inclusive)
	* @return the range of gcm global detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.GcmGlobalDetails> getGcmGlobalDetailses(
		int start, int end) {
		return _gcmGlobalDetailsLocalService.getGcmGlobalDetailses(start, end);
	}

	/**
	* Returns the number of gcm global detailses.
	*
	* @return the number of gcm global detailses
	*/
	@Override
	public int getGcmGlobalDetailsesCount() {
		return _gcmGlobalDetailsLocalService.getGcmGlobalDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _gcmGlobalDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _gcmGlobalDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _gcmGlobalDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the gcm global details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param gcmGlobalDetails the gcm global details
	* @return the gcm global details that was updated
	*/
	@Override
	public com.stpl.app.model.GcmGlobalDetails updateGcmGlobalDetails(
		com.stpl.app.model.GcmGlobalDetails gcmGlobalDetails) {
		return _gcmGlobalDetailsLocalService.updateGcmGlobalDetails(gcmGlobalDetails);
	}

	@Override
	public GcmGlobalDetailsLocalService getWrappedService() {
		return _gcmGlobalDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		GcmGlobalDetailsLocalService gcmGlobalDetailsLocalService) {
		_gcmGlobalDetailsLocalService = gcmGlobalDetailsLocalService;
	}

	private GcmGlobalDetailsLocalService _gcmGlobalDetailsLocalService;
}