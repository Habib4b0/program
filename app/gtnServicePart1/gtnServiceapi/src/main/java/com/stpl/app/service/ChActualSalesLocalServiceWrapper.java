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
 * Provides a wrapper for {@link ChActualSalesLocalService}.
 *
 * @author
 * @see ChActualSalesLocalService
 * @generated
 */
@ProviderType
public class ChActualSalesLocalServiceWrapper
	implements ChActualSalesLocalService,
		ServiceWrapper<ChActualSalesLocalService> {
	public ChActualSalesLocalServiceWrapper(
		ChActualSalesLocalService chActualSalesLocalService) {
		_chActualSalesLocalService = chActualSalesLocalService;
	}

	/**
	* Adds the ch actual sales to the database. Also notifies the appropriate model listeners.
	*
	* @param chActualSales the ch actual sales
	* @return the ch actual sales that was added
	*/
	@Override
	public com.stpl.app.model.ChActualSales addChActualSales(
		com.stpl.app.model.ChActualSales chActualSales) {
		return _chActualSalesLocalService.addChActualSales(chActualSales);
	}

	/**
	* Creates a new ch actual sales with the primary key. Does not add the ch actual sales to the database.
	*
	* @param chActualSalesPK the primary key for the new ch actual sales
	* @return the new ch actual sales
	*/
	@Override
	public com.stpl.app.model.ChActualSales createChActualSales(
		com.stpl.app.service.persistence.ChActualSalesPK chActualSalesPK) {
		return _chActualSalesLocalService.createChActualSales(chActualSalesPK);
	}

	/**
	* Deletes the ch actual sales from the database. Also notifies the appropriate model listeners.
	*
	* @param chActualSales the ch actual sales
	* @return the ch actual sales that was removed
	*/
	@Override
	public com.stpl.app.model.ChActualSales deleteChActualSales(
		com.stpl.app.model.ChActualSales chActualSales) {
		return _chActualSalesLocalService.deleteChActualSales(chActualSales);
	}

	/**
	* Deletes the ch actual sales with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chActualSalesPK the primary key of the ch actual sales
	* @return the ch actual sales that was removed
	* @throws PortalException if a ch actual sales with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ChActualSales deleteChActualSales(
		com.stpl.app.service.persistence.ChActualSalesPK chActualSalesPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chActualSalesLocalService.deleteChActualSales(chActualSalesPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chActualSalesLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _chActualSalesLocalService.dynamicQuery();
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
		return _chActualSalesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _chActualSalesLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _chActualSalesLocalService.dynamicQuery(dynamicQuery, start,
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
		return _chActualSalesLocalService.dynamicQueryCount(dynamicQuery);
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
		return _chActualSalesLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ChActualSales fetchChActualSales(
		com.stpl.app.service.persistence.ChActualSalesPK chActualSalesPK) {
		return _chActualSalesLocalService.fetchChActualSales(chActualSalesPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _chActualSalesLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the ch actual sales with the primary key.
	*
	* @param chActualSalesPK the primary key of the ch actual sales
	* @return the ch actual sales
	* @throws PortalException if a ch actual sales with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ChActualSales getChActualSales(
		com.stpl.app.service.persistence.ChActualSalesPK chActualSalesPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chActualSalesLocalService.getChActualSales(chActualSalesPK);
	}

	/**
	* Returns a range of all the ch actual saleses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch actual saleses
	* @param end the upper bound of the range of ch actual saleses (not inclusive)
	* @return the range of ch actual saleses
	*/
	@Override
	public java.util.List<com.stpl.app.model.ChActualSales> getChActualSaleses(
		int start, int end) {
		return _chActualSalesLocalService.getChActualSaleses(start, end);
	}

	/**
	* Returns the number of ch actual saleses.
	*
	* @return the number of ch actual saleses
	*/
	@Override
	public int getChActualSalesesCount() {
		return _chActualSalesLocalService.getChActualSalesesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _chActualSalesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _chActualSalesLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chActualSalesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ch actual sales in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param chActualSales the ch actual sales
	* @return the ch actual sales that was updated
	*/
	@Override
	public com.stpl.app.model.ChActualSales updateChActualSales(
		com.stpl.app.model.ChActualSales chActualSales) {
		return _chActualSalesLocalService.updateChActualSales(chActualSales);
	}

	@Override
	public ChActualSalesLocalService getWrappedService() {
		return _chActualSalesLocalService;
	}

	@Override
	public void setWrappedService(
		ChActualSalesLocalService chActualSalesLocalService) {
		_chActualSalesLocalService = chActualSalesLocalService;
	}

	private ChActualSalesLocalService _chActualSalesLocalService;
}