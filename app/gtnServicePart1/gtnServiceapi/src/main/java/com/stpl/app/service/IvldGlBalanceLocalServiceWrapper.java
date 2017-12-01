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
 * Provides a wrapper for {@link IvldGlBalanceLocalService}.
 *
 * @author
 * @see IvldGlBalanceLocalService
 * @generated
 */
@ProviderType
public class IvldGlBalanceLocalServiceWrapper
	implements IvldGlBalanceLocalService,
		ServiceWrapper<IvldGlBalanceLocalService> {
	public IvldGlBalanceLocalServiceWrapper(
		IvldGlBalanceLocalService ivldGlBalanceLocalService) {
		_ivldGlBalanceLocalService = ivldGlBalanceLocalService;
	}

	/**
	* Adds the ivld gl balance to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldGlBalance the ivld gl balance
	* @return the ivld gl balance that was added
	*/
	@Override
	public com.stpl.app.model.IvldGlBalance addIvldGlBalance(
		com.stpl.app.model.IvldGlBalance ivldGlBalance) {
		return _ivldGlBalanceLocalService.addIvldGlBalance(ivldGlBalance);
	}

	/**
	* Creates a new ivld gl balance with the primary key. Does not add the ivld gl balance to the database.
	*
	* @param ivldGlBalanceSid the primary key for the new ivld gl balance
	* @return the new ivld gl balance
	*/
	@Override
	public com.stpl.app.model.IvldGlBalance createIvldGlBalance(
		int ivldGlBalanceSid) {
		return _ivldGlBalanceLocalService.createIvldGlBalance(ivldGlBalanceSid);
	}

	/**
	* Deletes the ivld gl balance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldGlBalanceSid the primary key of the ivld gl balance
	* @return the ivld gl balance that was removed
	* @throws PortalException if a ivld gl balance with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldGlBalance deleteIvldGlBalance(
		int ivldGlBalanceSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldGlBalanceLocalService.deleteIvldGlBalance(ivldGlBalanceSid);
	}

	/**
	* Deletes the ivld gl balance from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldGlBalance the ivld gl balance
	* @return the ivld gl balance that was removed
	*/
	@Override
	public com.stpl.app.model.IvldGlBalance deleteIvldGlBalance(
		com.stpl.app.model.IvldGlBalance ivldGlBalance) {
		return _ivldGlBalanceLocalService.deleteIvldGlBalance(ivldGlBalance);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldGlBalanceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldGlBalanceLocalService.dynamicQuery();
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
		return _ivldGlBalanceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldGlBalanceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldGlBalanceLocalService.dynamicQuery(dynamicQuery, start,
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
		return _ivldGlBalanceLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldGlBalanceLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.IvldGlBalance fetchIvldGlBalance(
		int ivldGlBalanceSid) {
		return _ivldGlBalanceLocalService.fetchIvldGlBalance(ivldGlBalanceSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldGlBalanceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldGlBalanceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld gl balance with the primary key.
	*
	* @param ivldGlBalanceSid the primary key of the ivld gl balance
	* @return the ivld gl balance
	* @throws PortalException if a ivld gl balance with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldGlBalance getIvldGlBalance(
		int ivldGlBalanceSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldGlBalanceLocalService.getIvldGlBalance(ivldGlBalanceSid);
	}

	/**
	* Returns a range of all the ivld gl balances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld gl balances
	* @param end the upper bound of the range of ivld gl balances (not inclusive)
	* @return the range of ivld gl balances
	*/
	@Override
	public java.util.List<com.stpl.app.model.IvldGlBalance> getIvldGlBalances(
		int start, int end) {
		return _ivldGlBalanceLocalService.getIvldGlBalances(start, end);
	}

	/**
	* Returns the number of ivld gl balances.
	*
	* @return the number of ivld gl balances
	*/
	@Override
	public int getIvldGlBalancesCount() {
		return _ivldGlBalanceLocalService.getIvldGlBalancesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldGlBalanceLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldGlBalanceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld gl balance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldGlBalance the ivld gl balance
	* @return the ivld gl balance that was updated
	*/
	@Override
	public com.stpl.app.model.IvldGlBalance updateIvldGlBalance(
		com.stpl.app.model.IvldGlBalance ivldGlBalance) {
		return _ivldGlBalanceLocalService.updateIvldGlBalance(ivldGlBalance);
	}

	@Override
	public IvldGlBalanceLocalService getWrappedService() {
		return _ivldGlBalanceLocalService;
	}

	@Override
	public void setWrappedService(
		IvldGlBalanceLocalService ivldGlBalanceLocalService) {
		_ivldGlBalanceLocalService = ivldGlBalanceLocalService;
	}

	private IvldGlBalanceLocalService _ivldGlBalanceLocalService;
}