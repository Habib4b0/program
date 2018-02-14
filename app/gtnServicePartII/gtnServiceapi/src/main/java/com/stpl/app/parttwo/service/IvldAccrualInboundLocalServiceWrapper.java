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
 * Provides a wrapper for {@link IvldAccrualInboundLocalService}.
 *
 * @author
 * @see IvldAccrualInboundLocalService
 * @generated
 */
@ProviderType
public class IvldAccrualInboundLocalServiceWrapper
	implements IvldAccrualInboundLocalService,
		ServiceWrapper<IvldAccrualInboundLocalService> {
	public IvldAccrualInboundLocalServiceWrapper(
		IvldAccrualInboundLocalService ivldAccrualInboundLocalService) {
		_ivldAccrualInboundLocalService = ivldAccrualInboundLocalService;
	}

	/**
	* Adds the ivld accrual inbound to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldAccrualInbound the ivld accrual inbound
	* @return the ivld accrual inbound that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldAccrualInbound addIvldAccrualInbound(
		com.stpl.app.parttwo.model.IvldAccrualInbound ivldAccrualInbound) {
		return _ivldAccrualInboundLocalService.addIvldAccrualInbound(ivldAccrualInbound);
	}

	/**
	* Creates a new ivld accrual inbound with the primary key. Does not add the ivld accrual inbound to the database.
	*
	* @param ivldAccrualInboundSid the primary key for the new ivld accrual inbound
	* @return the new ivld accrual inbound
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldAccrualInbound createIvldAccrualInbound(
		int ivldAccrualInboundSid) {
		return _ivldAccrualInboundLocalService.createIvldAccrualInbound(ivldAccrualInboundSid);
	}

	/**
	* Deletes the ivld accrual inbound with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
	* @return the ivld accrual inbound that was removed
	* @throws PortalException if a ivld accrual inbound with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldAccrualInbound deleteIvldAccrualInbound(
		int ivldAccrualInboundSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldAccrualInboundLocalService.deleteIvldAccrualInbound(ivldAccrualInboundSid);
	}

	/**
	* Deletes the ivld accrual inbound from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldAccrualInbound the ivld accrual inbound
	* @return the ivld accrual inbound that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldAccrualInbound deleteIvldAccrualInbound(
		com.stpl.app.parttwo.model.IvldAccrualInbound ivldAccrualInbound) {
		return _ivldAccrualInboundLocalService.deleteIvldAccrualInbound(ivldAccrualInbound);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldAccrualInboundLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldAccrualInboundLocalService.dynamicQuery();
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
		return _ivldAccrualInboundLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldAccrualInboundLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldAccrualInboundLocalService.dynamicQuery(dynamicQuery,
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
		return _ivldAccrualInboundLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldAccrualInboundLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.IvldAccrualInbound fetchIvldAccrualInbound(
		int ivldAccrualInboundSid) {
		return _ivldAccrualInboundLocalService.fetchIvldAccrualInbound(ivldAccrualInboundSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldAccrualInboundLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldAccrualInboundLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld accrual inbound with the primary key.
	*
	* @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
	* @return the ivld accrual inbound
	* @throws PortalException if a ivld accrual inbound with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldAccrualInbound getIvldAccrualInbound(
		int ivldAccrualInboundSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldAccrualInboundLocalService.getIvldAccrualInbound(ivldAccrualInboundSid);
	}

	/**
	* Returns a range of all the ivld accrual inbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld accrual inbounds
	* @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
	* @return the range of ivld accrual inbounds
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.IvldAccrualInbound> getIvldAccrualInbounds(
		int start, int end) {
		return _ivldAccrualInboundLocalService.getIvldAccrualInbounds(start, end);
	}

	/**
	* Returns the number of ivld accrual inbounds.
	*
	* @return the number of ivld accrual inbounds
	*/
	@Override
	public int getIvldAccrualInboundsCount() {
		return _ivldAccrualInboundLocalService.getIvldAccrualInboundsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldAccrualInboundLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldAccrualInboundLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld accrual inbound in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldAccrualInbound the ivld accrual inbound
	* @return the ivld accrual inbound that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldAccrualInbound updateIvldAccrualInbound(
		com.stpl.app.parttwo.model.IvldAccrualInbound ivldAccrualInbound) {
		return _ivldAccrualInboundLocalService.updateIvldAccrualInbound(ivldAccrualInbound);
	}

	@Override
	public IvldAccrualInboundLocalService getWrappedService() {
		return _ivldAccrualInboundLocalService;
	}

	@Override
	public void setWrappedService(
		IvldAccrualInboundLocalService ivldAccrualInboundLocalService) {
		_ivldAccrualInboundLocalService = ivldAccrualInboundLocalService;
	}

	private IvldAccrualInboundLocalService _ivldAccrualInboundLocalService;
}