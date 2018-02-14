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
 * Provides a wrapper for {@link NetSalesFormulaMasterLocalService}.
 *
 * @author
 * @see NetSalesFormulaMasterLocalService
 * @generated
 */
@ProviderType
public class NetSalesFormulaMasterLocalServiceWrapper
	implements NetSalesFormulaMasterLocalService,
		ServiceWrapper<NetSalesFormulaMasterLocalService> {
	public NetSalesFormulaMasterLocalServiceWrapper(
		NetSalesFormulaMasterLocalService netSalesFormulaMasterLocalService) {
		_netSalesFormulaMasterLocalService = netSalesFormulaMasterLocalService;
	}

	/**
	* Adds the net sales formula master to the database. Also notifies the appropriate model listeners.
	*
	* @param netSalesFormulaMaster the net sales formula master
	* @return the net sales formula master that was added
	*/
	@Override
	public com.stpl.app.model.NetSalesFormulaMaster addNetSalesFormulaMaster(
		com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster) {
		return _netSalesFormulaMasterLocalService.addNetSalesFormulaMaster(netSalesFormulaMaster);
	}

	/**
	* Creates a new net sales formula master with the primary key. Does not add the net sales formula master to the database.
	*
	* @param netSalesFormulaMasterSid the primary key for the new net sales formula master
	* @return the new net sales formula master
	*/
	@Override
	public com.stpl.app.model.NetSalesFormulaMaster createNetSalesFormulaMaster(
		int netSalesFormulaMasterSid) {
		return _netSalesFormulaMasterLocalService.createNetSalesFormulaMaster(netSalesFormulaMasterSid);
	}

	/**
	* Deletes the net sales formula master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param netSalesFormulaMasterSid the primary key of the net sales formula master
	* @return the net sales formula master that was removed
	* @throws PortalException if a net sales formula master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NetSalesFormulaMaster deleteNetSalesFormulaMaster(
		int netSalesFormulaMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _netSalesFormulaMasterLocalService.deleteNetSalesFormulaMaster(netSalesFormulaMasterSid);
	}

	/**
	* Deletes the net sales formula master from the database. Also notifies the appropriate model listeners.
	*
	* @param netSalesFormulaMaster the net sales formula master
	* @return the net sales formula master that was removed
	*/
	@Override
	public com.stpl.app.model.NetSalesFormulaMaster deleteNetSalesFormulaMaster(
		com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster) {
		return _netSalesFormulaMasterLocalService.deleteNetSalesFormulaMaster(netSalesFormulaMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _netSalesFormulaMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _netSalesFormulaMasterLocalService.dynamicQuery();
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
		return _netSalesFormulaMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _netSalesFormulaMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _netSalesFormulaMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _netSalesFormulaMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _netSalesFormulaMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.NetSalesFormulaMaster fetchNetSalesFormulaMaster(
		int netSalesFormulaMasterSid) {
		return _netSalesFormulaMasterLocalService.fetchNetSalesFormulaMaster(netSalesFormulaMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _netSalesFormulaMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _netSalesFormulaMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the net sales formula master with the primary key.
	*
	* @param netSalesFormulaMasterSid the primary key of the net sales formula master
	* @return the net sales formula master
	* @throws PortalException if a net sales formula master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NetSalesFormulaMaster getNetSalesFormulaMaster(
		int netSalesFormulaMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _netSalesFormulaMasterLocalService.getNetSalesFormulaMaster(netSalesFormulaMasterSid);
	}

	/**
	* Returns a range of all the net sales formula masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of net sales formula masters
	* @param end the upper bound of the range of net sales formula masters (not inclusive)
	* @return the range of net sales formula masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.NetSalesFormulaMaster> getNetSalesFormulaMasters(
		int start, int end) {
		return _netSalesFormulaMasterLocalService.getNetSalesFormulaMasters(start,
			end);
	}

	/**
	* Returns the number of net sales formula masters.
	*
	* @return the number of net sales formula masters
	*/
	@Override
	public int getNetSalesFormulaMastersCount() {
		return _netSalesFormulaMasterLocalService.getNetSalesFormulaMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _netSalesFormulaMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _netSalesFormulaMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the net sales formula master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param netSalesFormulaMaster the net sales formula master
	* @return the net sales formula master that was updated
	*/
	@Override
	public com.stpl.app.model.NetSalesFormulaMaster updateNetSalesFormulaMaster(
		com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster) {
		return _netSalesFormulaMasterLocalService.updateNetSalesFormulaMaster(netSalesFormulaMaster);
	}

	@Override
	public NetSalesFormulaMasterLocalService getWrappedService() {
		return _netSalesFormulaMasterLocalService;
	}

	@Override
	public void setWrappedService(
		NetSalesFormulaMasterLocalService netSalesFormulaMasterLocalService) {
		_netSalesFormulaMasterLocalService = netSalesFormulaMasterLocalService;
	}

	private NetSalesFormulaMasterLocalService _netSalesFormulaMasterLocalService;
}