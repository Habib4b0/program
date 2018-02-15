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
 * Provides a wrapper for {@link IvldForecastSalesLocalService}.
 *
 * @author
 * @see IvldForecastSalesLocalService
 * @generated
 */
@ProviderType
public class IvldForecastSalesLocalServiceWrapper
	implements IvldForecastSalesLocalService,
		ServiceWrapper<IvldForecastSalesLocalService> {
	public IvldForecastSalesLocalServiceWrapper(
		IvldForecastSalesLocalService ivldForecastSalesLocalService) {
		_ivldForecastSalesLocalService = ivldForecastSalesLocalService;
	}

	/**
	* Adds the ivld forecast sales to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldForecastSales the ivld forecast sales
	* @return the ivld forecast sales that was added
	*/
	@Override
	public com.stpl.app.model.IvldForecastSales addIvldForecastSales(
		com.stpl.app.model.IvldForecastSales ivldForecastSales) {
		return _ivldForecastSalesLocalService.addIvldForecastSales(ivldForecastSales);
	}

	/**
	* Creates a new ivld forecast sales with the primary key. Does not add the ivld forecast sales to the database.
	*
	* @param ivldForecastSalesSid the primary key for the new ivld forecast sales
	* @return the new ivld forecast sales
	*/
	@Override
	public com.stpl.app.model.IvldForecastSales createIvldForecastSales(
		int ivldForecastSalesSid) {
		return _ivldForecastSalesLocalService.createIvldForecastSales(ivldForecastSalesSid);
	}

	/**
	* Deletes the ivld forecast sales with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldForecastSalesSid the primary key of the ivld forecast sales
	* @return the ivld forecast sales that was removed
	* @throws PortalException if a ivld forecast sales with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldForecastSales deleteIvldForecastSales(
		int ivldForecastSalesSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldForecastSalesLocalService.deleteIvldForecastSales(ivldForecastSalesSid);
	}

	/**
	* Deletes the ivld forecast sales from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldForecastSales the ivld forecast sales
	* @return the ivld forecast sales that was removed
	*/
	@Override
	public com.stpl.app.model.IvldForecastSales deleteIvldForecastSales(
		com.stpl.app.model.IvldForecastSales ivldForecastSales) {
		return _ivldForecastSalesLocalService.deleteIvldForecastSales(ivldForecastSales);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldForecastSalesLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldForecastSalesLocalService.dynamicQuery();
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
		return _ivldForecastSalesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldForecastSalesLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldForecastSalesLocalService.dynamicQuery(dynamicQuery, start,
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
		return _ivldForecastSalesLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldForecastSalesLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.IvldForecastSales fetchIvldForecastSales(
		int ivldForecastSalesSid) {
		return _ivldForecastSalesLocalService.fetchIvldForecastSales(ivldForecastSalesSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldForecastSalesLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldForecastSalesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld forecast sales with the primary key.
	*
	* @param ivldForecastSalesSid the primary key of the ivld forecast sales
	* @return the ivld forecast sales
	* @throws PortalException if a ivld forecast sales with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldForecastSales getIvldForecastSales(
		int ivldForecastSalesSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldForecastSalesLocalService.getIvldForecastSales(ivldForecastSalesSid);
	}

	/**
	* Returns a range of all the ivld forecast saleses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld forecast saleses
	* @param end the upper bound of the range of ivld forecast saleses (not inclusive)
	* @return the range of ivld forecast saleses
	*/
	@Override
	public java.util.List<com.stpl.app.model.IvldForecastSales> getIvldForecastSaleses(
		int start, int end) {
		return _ivldForecastSalesLocalService.getIvldForecastSaleses(start, end);
	}

	/**
	* Returns the number of ivld forecast saleses.
	*
	* @return the number of ivld forecast saleses
	*/
	@Override
	public int getIvldForecastSalesesCount() {
		return _ivldForecastSalesLocalService.getIvldForecastSalesesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldForecastSalesLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldForecastSalesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld forecast sales in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldForecastSales the ivld forecast sales
	* @return the ivld forecast sales that was updated
	*/
	@Override
	public com.stpl.app.model.IvldForecastSales updateIvldForecastSales(
		com.stpl.app.model.IvldForecastSales ivldForecastSales) {
		return _ivldForecastSalesLocalService.updateIvldForecastSales(ivldForecastSales);
	}

	@Override
	public IvldForecastSalesLocalService getWrappedService() {
		return _ivldForecastSalesLocalService;
	}

	@Override
	public void setWrappedService(
		IvldForecastSalesLocalService ivldForecastSalesLocalService) {
		_ivldForecastSalesLocalService = ivldForecastSalesLocalService;
	}

	private IvldForecastSalesLocalService _ivldForecastSalesLocalService;
}