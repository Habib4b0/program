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
 * Provides a wrapper for {@link StArpOutboundLocalService}.
 *
 * @author
 * @see StArpOutboundLocalService
 * @generated
 */
@ProviderType
public class StArpOutboundLocalServiceWrapper
	implements StArpOutboundLocalService,
		ServiceWrapper<StArpOutboundLocalService> {
	public StArpOutboundLocalServiceWrapper(
		StArpOutboundLocalService stArpOutboundLocalService) {
		_stArpOutboundLocalService = stArpOutboundLocalService;
	}

	/**
	* Adds the st arp outbound to the database. Also notifies the appropriate model listeners.
	*
	* @param stArpOutbound the st arp outbound
	* @return the st arp outbound that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.StArpOutbound addStArpOutbound(
		com.stpl.app.parttwo.model.StArpOutbound stArpOutbound) {
		return _stArpOutboundLocalService.addStArpOutbound(stArpOutbound);
	}

	/**
	* Creates a new st arp outbound with the primary key. Does not add the st arp outbound to the database.
	*
	* @param stArpOutboundPK the primary key for the new st arp outbound
	* @return the new st arp outbound
	*/
	@Override
	public com.stpl.app.parttwo.model.StArpOutbound createStArpOutbound(
		com.stpl.app.parttwo.service.persistence.StArpOutboundPK stArpOutboundPK) {
		return _stArpOutboundLocalService.createStArpOutbound(stArpOutboundPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stArpOutboundLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st arp outbound from the database. Also notifies the appropriate model listeners.
	*
	* @param stArpOutbound the st arp outbound
	* @return the st arp outbound that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.StArpOutbound deleteStArpOutbound(
		com.stpl.app.parttwo.model.StArpOutbound stArpOutbound) {
		return _stArpOutboundLocalService.deleteStArpOutbound(stArpOutbound);
	}

	/**
	* Deletes the st arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stArpOutboundPK the primary key of the st arp outbound
	* @return the st arp outbound that was removed
	* @throws PortalException if a st arp outbound with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.StArpOutbound deleteStArpOutbound(
		com.stpl.app.parttwo.service.persistence.StArpOutboundPK stArpOutboundPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stArpOutboundLocalService.deleteStArpOutbound(stArpOutboundPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stArpOutboundLocalService.dynamicQuery();
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
		return _stArpOutboundLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stArpOutboundLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stArpOutboundLocalService.dynamicQuery(dynamicQuery, start,
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
		return _stArpOutboundLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stArpOutboundLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.StArpOutbound fetchStArpOutbound(
		com.stpl.app.parttwo.service.persistence.StArpOutboundPK stArpOutboundPK) {
		return _stArpOutboundLocalService.fetchStArpOutbound(stArpOutboundPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stArpOutboundLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stArpOutboundLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stArpOutboundLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stArpOutboundLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st arp outbound with the primary key.
	*
	* @param stArpOutboundPK the primary key of the st arp outbound
	* @return the st arp outbound
	* @throws PortalException if a st arp outbound with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.StArpOutbound getStArpOutbound(
		com.stpl.app.parttwo.service.persistence.StArpOutboundPK stArpOutboundPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stArpOutboundLocalService.getStArpOutbound(stArpOutboundPK);
	}

	/**
	* Returns a range of all the st arp outbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st arp outbounds
	* @param end the upper bound of the range of st arp outbounds (not inclusive)
	* @return the range of st arp outbounds
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.StArpOutbound> getStArpOutbounds(
		int start, int end) {
		return _stArpOutboundLocalService.getStArpOutbounds(start, end);
	}

	/**
	* Returns the number of st arp outbounds.
	*
	* @return the number of st arp outbounds
	*/
	@Override
	public int getStArpOutboundsCount() {
		return _stArpOutboundLocalService.getStArpOutboundsCount();
	}

	/**
	* Updates the st arp outbound in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stArpOutbound the st arp outbound
	* @return the st arp outbound that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.StArpOutbound updateStArpOutbound(
		com.stpl.app.parttwo.model.StArpOutbound stArpOutbound) {
		return _stArpOutboundLocalService.updateStArpOutbound(stArpOutbound);
	}

	@Override
	public StArpOutboundLocalService getWrappedService() {
		return _stArpOutboundLocalService;
	}

	@Override
	public void setWrappedService(
		StArpOutboundLocalService stArpOutboundLocalService) {
		_stArpOutboundLocalService = stArpOutboundLocalService;
	}

	private StArpOutboundLocalService _stArpOutboundLocalService;
}