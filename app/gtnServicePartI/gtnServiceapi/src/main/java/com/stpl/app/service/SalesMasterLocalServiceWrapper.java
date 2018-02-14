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
 * Provides a wrapper for {@link SalesMasterLocalService}.
 *
 * @author
 * @see SalesMasterLocalService
 * @generated
 */
@ProviderType
public class SalesMasterLocalServiceWrapper implements SalesMasterLocalService,
	ServiceWrapper<SalesMasterLocalService> {
	public SalesMasterLocalServiceWrapper(
		SalesMasterLocalService salesMasterLocalService) {
		_salesMasterLocalService = salesMasterLocalService;
	}

	/**
	* Adds the sales master to the database. Also notifies the appropriate model listeners.
	*
	* @param salesMaster the sales master
	* @return the sales master that was added
	*/
	@Override
	public com.stpl.app.model.SalesMaster addSalesMaster(
		com.stpl.app.model.SalesMaster salesMaster) {
		return _salesMasterLocalService.addSalesMaster(salesMaster);
	}

	/**
	* Creates a new sales master with the primary key. Does not add the sales master to the database.
	*
	* @param salesMasterSid the primary key for the new sales master
	* @return the new sales master
	*/
	@Override
	public com.stpl.app.model.SalesMaster createSalesMaster(int salesMasterSid) {
		return _salesMasterLocalService.createSalesMaster(salesMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _salesMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the sales master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param salesMasterSid the primary key of the sales master
	* @return the sales master that was removed
	* @throws PortalException if a sales master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.SalesMaster deleteSalesMaster(int salesMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _salesMasterLocalService.deleteSalesMaster(salesMasterSid);
	}

	/**
	* Deletes the sales master from the database. Also notifies the appropriate model listeners.
	*
	* @param salesMaster the sales master
	* @return the sales master that was removed
	*/
	@Override
	public com.stpl.app.model.SalesMaster deleteSalesMaster(
		com.stpl.app.model.SalesMaster salesMaster) {
		return _salesMasterLocalService.deleteSalesMaster(salesMaster);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _salesMasterLocalService.dynamicQuery();
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
		return _salesMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _salesMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _salesMasterLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _salesMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _salesMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.SalesMaster fetchSalesMaster(int salesMasterSid) {
		return _salesMasterLocalService.fetchSalesMaster(salesMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _salesMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _salesMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _salesMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _salesMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the sales master with the primary key.
	*
	* @param salesMasterSid the primary key of the sales master
	* @return the sales master
	* @throws PortalException if a sales master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.SalesMaster getSalesMaster(int salesMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _salesMasterLocalService.getSalesMaster(salesMasterSid);
	}

	/**
	* Returns a range of all the sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of sales masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.SalesMaster> getSalesMasters(
		int start, int end) {
		return _salesMasterLocalService.getSalesMasters(start, end);
	}

	/**
	* Returns the number of sales masters.
	*
	* @return the number of sales masters
	*/
	@Override
	public int getSalesMastersCount() {
		return _salesMasterLocalService.getSalesMastersCount();
	}

	/**
	* Updates the sales master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param salesMaster the sales master
	* @return the sales master that was updated
	*/
	@Override
	public com.stpl.app.model.SalesMaster updateSalesMaster(
		com.stpl.app.model.SalesMaster salesMaster) {
		return _salesMasterLocalService.updateSalesMaster(salesMaster);
	}

	@Override
	public SalesMasterLocalService getWrappedService() {
		return _salesMasterLocalService;
	}

	@Override
	public void setWrappedService(
		SalesMasterLocalService salesMasterLocalService) {
		_salesMasterLocalService = salesMasterLocalService;
	}

	private SalesMasterLocalService _salesMasterLocalService;
}