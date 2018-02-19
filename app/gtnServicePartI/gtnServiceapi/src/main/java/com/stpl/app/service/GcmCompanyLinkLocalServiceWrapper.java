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
 * Provides a wrapper for {@link GcmCompanyLinkLocalService}.
 *
 * @author
 * @see GcmCompanyLinkLocalService
 * @generated
 */
@ProviderType
public class GcmCompanyLinkLocalServiceWrapper
	implements GcmCompanyLinkLocalService,
		ServiceWrapper<GcmCompanyLinkLocalService> {
	public GcmCompanyLinkLocalServiceWrapper(
		GcmCompanyLinkLocalService gcmCompanyLinkLocalService) {
		_gcmCompanyLinkLocalService = gcmCompanyLinkLocalService;
	}

	/**
	* Adds the gcm company link to the database. Also notifies the appropriate model listeners.
	*
	* @param gcmCompanyLink the gcm company link
	* @return the gcm company link that was added
	*/
	@Override
	public com.stpl.app.model.GcmCompanyLink addGcmCompanyLink(
		com.stpl.app.model.GcmCompanyLink gcmCompanyLink) {
		return _gcmCompanyLinkLocalService.addGcmCompanyLink(gcmCompanyLink);
	}

	/**
	* Creates a new gcm company link with the primary key. Does not add the gcm company link to the database.
	*
	* @param gcmCompanyLinkSid the primary key for the new gcm company link
	* @return the new gcm company link
	*/
	@Override
	public com.stpl.app.model.GcmCompanyLink createGcmCompanyLink(
		int gcmCompanyLinkSid) {
		return _gcmCompanyLinkLocalService.createGcmCompanyLink(gcmCompanyLinkSid);
	}

	/**
	* Deletes the gcm company link from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmCompanyLink the gcm company link
	* @return the gcm company link that was removed
	*/
	@Override
	public com.stpl.app.model.GcmCompanyLink deleteGcmCompanyLink(
		com.stpl.app.model.GcmCompanyLink gcmCompanyLink) {
		return _gcmCompanyLinkLocalService.deleteGcmCompanyLink(gcmCompanyLink);
	}

	/**
	* Deletes the gcm company link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmCompanyLinkSid the primary key of the gcm company link
	* @return the gcm company link that was removed
	* @throws PortalException if a gcm company link with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.GcmCompanyLink deleteGcmCompanyLink(
		int gcmCompanyLinkSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _gcmCompanyLinkLocalService.deleteGcmCompanyLink(gcmCompanyLinkSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _gcmCompanyLinkLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _gcmCompanyLinkLocalService.dynamicQuery();
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
		return _gcmCompanyLinkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _gcmCompanyLinkLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _gcmCompanyLinkLocalService.dynamicQuery(dynamicQuery, start,
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
		return _gcmCompanyLinkLocalService.dynamicQueryCount(dynamicQuery);
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
		return _gcmCompanyLinkLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.GcmCompanyLink fetchGcmCompanyLink(
		int gcmCompanyLinkSid) {
		return _gcmCompanyLinkLocalService.fetchGcmCompanyLink(gcmCompanyLinkSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _gcmCompanyLinkLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the gcm company link with the primary key.
	*
	* @param gcmCompanyLinkSid the primary key of the gcm company link
	* @return the gcm company link
	* @throws PortalException if a gcm company link with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.GcmCompanyLink getGcmCompanyLink(
		int gcmCompanyLinkSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _gcmCompanyLinkLocalService.getGcmCompanyLink(gcmCompanyLinkSid);
	}

	/**
	* Returns a range of all the gcm company links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm company links
	* @param end the upper bound of the range of gcm company links (not inclusive)
	* @return the range of gcm company links
	*/
	@Override
	public java.util.List<com.stpl.app.model.GcmCompanyLink> getGcmCompanyLinks(
		int start, int end) {
		return _gcmCompanyLinkLocalService.getGcmCompanyLinks(start, end);
	}

	/**
	* Returns the number of gcm company links.
	*
	* @return the number of gcm company links
	*/
	@Override
	public int getGcmCompanyLinksCount() {
		return _gcmCompanyLinkLocalService.getGcmCompanyLinksCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _gcmCompanyLinkLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _gcmCompanyLinkLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _gcmCompanyLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the gcm company link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param gcmCompanyLink the gcm company link
	* @return the gcm company link that was updated
	*/
	@Override
	public com.stpl.app.model.GcmCompanyLink updateGcmCompanyLink(
		com.stpl.app.model.GcmCompanyLink gcmCompanyLink) {
		return _gcmCompanyLinkLocalService.updateGcmCompanyLink(gcmCompanyLink);
	}

	@Override
	public GcmCompanyLinkLocalService getWrappedService() {
		return _gcmCompanyLinkLocalService;
	}

	@Override
	public void setWrappedService(
		GcmCompanyLinkLocalService gcmCompanyLinkLocalService) {
		_gcmCompanyLinkLocalService = gcmCompanyLinkLocalService;
	}

	private GcmCompanyLinkLocalService _gcmCompanyLinkLocalService;
}