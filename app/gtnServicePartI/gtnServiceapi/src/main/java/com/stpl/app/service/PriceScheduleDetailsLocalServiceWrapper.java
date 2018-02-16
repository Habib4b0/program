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
 * Provides a wrapper for {@link PriceScheduleDetailsLocalService}.
 *
 * @author
 * @see PriceScheduleDetailsLocalService
 * @generated
 */
@ProviderType
public class PriceScheduleDetailsLocalServiceWrapper
	implements PriceScheduleDetailsLocalService,
		ServiceWrapper<PriceScheduleDetailsLocalService> {
	public PriceScheduleDetailsLocalServiceWrapper(
		PriceScheduleDetailsLocalService priceScheduleDetailsLocalService) {
		_priceScheduleDetailsLocalService = priceScheduleDetailsLocalService;
	}

	/**
	* Adds the price schedule details to the database. Also notifies the appropriate model listeners.
	*
	* @param priceScheduleDetails the price schedule details
	* @return the price schedule details that was added
	*/
	@Override
	public com.stpl.app.model.PriceScheduleDetails addPriceScheduleDetails(
		com.stpl.app.model.PriceScheduleDetails priceScheduleDetails) {
		return _priceScheduleDetailsLocalService.addPriceScheduleDetails(priceScheduleDetails);
	}

	/**
	* Creates a new price schedule details with the primary key. Does not add the price schedule details to the database.
	*
	* @param psDetailsSystemId the primary key for the new price schedule details
	* @return the new price schedule details
	*/
	@Override
	public com.stpl.app.model.PriceScheduleDetails createPriceScheduleDetails(
		int psDetailsSystemId) {
		return _priceScheduleDetailsLocalService.createPriceScheduleDetails(psDetailsSystemId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceScheduleDetailsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the price schedule details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param psDetailsSystemId the primary key of the price schedule details
	* @return the price schedule details that was removed
	* @throws PortalException if a price schedule details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PriceScheduleDetails deletePriceScheduleDetails(
		int psDetailsSystemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceScheduleDetailsLocalService.deletePriceScheduleDetails(psDetailsSystemId);
	}

	/**
	* Deletes the price schedule details from the database. Also notifies the appropriate model listeners.
	*
	* @param priceScheduleDetails the price schedule details
	* @return the price schedule details that was removed
	*/
	@Override
	public com.stpl.app.model.PriceScheduleDetails deletePriceScheduleDetails(
		com.stpl.app.model.PriceScheduleDetails priceScheduleDetails) {
		return _priceScheduleDetailsLocalService.deletePriceScheduleDetails(priceScheduleDetails);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _priceScheduleDetailsLocalService.dynamicQuery();
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
		return _priceScheduleDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _priceScheduleDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _priceScheduleDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _priceScheduleDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _priceScheduleDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.PriceScheduleDetails fetchPriceScheduleDetails(
		int psDetailsSystemId) {
		return _priceScheduleDetailsLocalService.fetchPriceScheduleDetails(psDetailsSystemId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _priceScheduleDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _priceScheduleDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _priceScheduleDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceScheduleDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the price schedule details with the primary key.
	*
	* @param psDetailsSystemId the primary key of the price schedule details
	* @return the price schedule details
	* @throws PortalException if a price schedule details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PriceScheduleDetails getPriceScheduleDetails(
		int psDetailsSystemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceScheduleDetailsLocalService.getPriceScheduleDetails(psDetailsSystemId);
	}

	/**
	* Returns a range of all the price schedule detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @return the range of price schedule detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.PriceScheduleDetails> getPriceScheduleDetailses(
		int start, int end) {
		return _priceScheduleDetailsLocalService.getPriceScheduleDetailses(start,
			end);
	}

	/**
	* Returns the number of price schedule detailses.
	*
	* @return the number of price schedule detailses
	*/
	@Override
	public int getPriceScheduleDetailsesCount() {
		return _priceScheduleDetailsLocalService.getPriceScheduleDetailsesCount();
	}

	/**
	* Updates the price schedule details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param priceScheduleDetails the price schedule details
	* @return the price schedule details that was updated
	*/
	@Override
	public com.stpl.app.model.PriceScheduleDetails updatePriceScheduleDetails(
		com.stpl.app.model.PriceScheduleDetails priceScheduleDetails) {
		return _priceScheduleDetailsLocalService.updatePriceScheduleDetails(priceScheduleDetails);
	}

	@Override
	public PriceScheduleDetailsLocalService getWrappedService() {
		return _priceScheduleDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		PriceScheduleDetailsLocalService priceScheduleDetailsLocalService) {
		_priceScheduleDetailsLocalService = priceScheduleDetailsLocalService;
	}

	private PriceScheduleDetailsLocalService _priceScheduleDetailsLocalService;
}