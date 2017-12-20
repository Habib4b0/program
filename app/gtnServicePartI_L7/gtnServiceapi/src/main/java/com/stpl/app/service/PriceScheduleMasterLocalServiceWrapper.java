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
 * Provides a wrapper for {@link PriceScheduleMasterLocalService}.
 *
 * @author
 * @see PriceScheduleMasterLocalService
 * @generated
 */
@ProviderType
public class PriceScheduleMasterLocalServiceWrapper
	implements PriceScheduleMasterLocalService,
		ServiceWrapper<PriceScheduleMasterLocalService> {
	public PriceScheduleMasterLocalServiceWrapper(
		PriceScheduleMasterLocalService priceScheduleMasterLocalService) {
		_priceScheduleMasterLocalService = priceScheduleMasterLocalService;
	}

	/**
	* Adds the price schedule master to the database. Also notifies the appropriate model listeners.
	*
	* @param priceScheduleMaster the price schedule master
	* @return the price schedule master that was added
	*/
	@Override
	public com.stpl.app.model.PriceScheduleMaster addPriceScheduleMaster(
		com.stpl.app.model.PriceScheduleMaster priceScheduleMaster) {
		return _priceScheduleMasterLocalService.addPriceScheduleMaster(priceScheduleMaster);
	}

	/**
	* Creates a new price schedule master with the primary key. Does not add the price schedule master to the database.
	*
	* @param priceScheduleSystemId the primary key for the new price schedule master
	* @return the new price schedule master
	*/
	@Override
	public com.stpl.app.model.PriceScheduleMaster createPriceScheduleMaster(
		int priceScheduleSystemId) {
		return _priceScheduleMasterLocalService.createPriceScheduleMaster(priceScheduleSystemId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceScheduleMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the price schedule master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param priceScheduleSystemId the primary key of the price schedule master
	* @return the price schedule master that was removed
	* @throws PortalException if a price schedule master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PriceScheduleMaster deletePriceScheduleMaster(
		int priceScheduleSystemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceScheduleMasterLocalService.deletePriceScheduleMaster(priceScheduleSystemId);
	}

	/**
	* Deletes the price schedule master from the database. Also notifies the appropriate model listeners.
	*
	* @param priceScheduleMaster the price schedule master
	* @return the price schedule master that was removed
	*/
	@Override
	public com.stpl.app.model.PriceScheduleMaster deletePriceScheduleMaster(
		com.stpl.app.model.PriceScheduleMaster priceScheduleMaster) {
		return _priceScheduleMasterLocalService.deletePriceScheduleMaster(priceScheduleMaster);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _priceScheduleMasterLocalService.dynamicQuery();
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
		return _priceScheduleMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _priceScheduleMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _priceScheduleMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _priceScheduleMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _priceScheduleMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.PriceScheduleMaster fetchPriceScheduleMaster(
		int priceScheduleSystemId) {
		return _priceScheduleMasterLocalService.fetchPriceScheduleMaster(priceScheduleSystemId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _priceScheduleMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _priceScheduleMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _priceScheduleMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceScheduleMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the price schedule master with the primary key.
	*
	* @param priceScheduleSystemId the primary key of the price schedule master
	* @return the price schedule master
	* @throws PortalException if a price schedule master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PriceScheduleMaster getPriceScheduleMaster(
		int priceScheduleSystemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceScheduleMasterLocalService.getPriceScheduleMaster(priceScheduleSystemId);
	}

	/**
	* Returns a range of all the price schedule masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of price schedule masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.PriceScheduleMaster> getPriceScheduleMasters(
		int start, int end) {
		return _priceScheduleMasterLocalService.getPriceScheduleMasters(start,
			end);
	}

	/**
	* Returns the number of price schedule masters.
	*
	* @return the number of price schedule masters
	*/
	@Override
	public int getPriceScheduleMastersCount() {
		return _priceScheduleMasterLocalService.getPriceScheduleMastersCount();
	}

	/**
	* Updates the price schedule master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param priceScheduleMaster the price schedule master
	* @return the price schedule master that was updated
	*/
	@Override
	public com.stpl.app.model.PriceScheduleMaster updatePriceScheduleMaster(
		com.stpl.app.model.PriceScheduleMaster priceScheduleMaster) {
		return _priceScheduleMasterLocalService.updatePriceScheduleMaster(priceScheduleMaster);
	}

	@Override
	public PriceScheduleMasterLocalService getWrappedService() {
		return _priceScheduleMasterLocalService;
	}

	@Override
	public void setWrappedService(
		PriceScheduleMasterLocalService priceScheduleMasterLocalService) {
		_priceScheduleMasterLocalService = priceScheduleMasterLocalService;
	}

	private PriceScheduleMasterLocalService _priceScheduleMasterLocalService;
}