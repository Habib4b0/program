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
 * Provides a wrapper for {@link FederalNewNdcLocalService}.
 *
 * @author
 * @see FederalNewNdcLocalService
 * @generated
 */
@ProviderType
public class FederalNewNdcLocalServiceWrapper
	implements FederalNewNdcLocalService,
		ServiceWrapper<FederalNewNdcLocalService> {
	public FederalNewNdcLocalServiceWrapper(
		FederalNewNdcLocalService federalNewNdcLocalService) {
		_federalNewNdcLocalService = federalNewNdcLocalService;
	}

	/**
	* Adds the federal new ndc to the database. Also notifies the appropriate model listeners.
	*
	* @param federalNewNdc the federal new ndc
	* @return the federal new ndc that was added
	*/
	@Override
	public com.stpl.app.model.FederalNewNdc addFederalNewNdc(
		com.stpl.app.model.FederalNewNdc federalNewNdc) {
		return _federalNewNdcLocalService.addFederalNewNdc(federalNewNdc);
	}

	/**
	* Creates a new federal new ndc with the primary key. Does not add the federal new ndc to the database.
	*
	* @param itemMasterSid the primary key for the new federal new ndc
	* @return the new federal new ndc
	*/
	@Override
	public com.stpl.app.model.FederalNewNdc createFederalNewNdc(
		int itemMasterSid) {
		return _federalNewNdcLocalService.createFederalNewNdc(itemMasterSid);
	}

	/**
	* Deletes the federal new ndc from the database. Also notifies the appropriate model listeners.
	*
	* @param federalNewNdc the federal new ndc
	* @return the federal new ndc that was removed
	*/
	@Override
	public com.stpl.app.model.FederalNewNdc deleteFederalNewNdc(
		com.stpl.app.model.FederalNewNdc federalNewNdc) {
		return _federalNewNdcLocalService.deleteFederalNewNdc(federalNewNdc);
	}

	/**
	* Deletes the federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemMasterSid the primary key of the federal new ndc
	* @return the federal new ndc that was removed
	* @throws PortalException if a federal new ndc with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.FederalNewNdc deleteFederalNewNdc(
		int itemMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _federalNewNdcLocalService.deleteFederalNewNdc(itemMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _federalNewNdcLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _federalNewNdcLocalService.dynamicQuery();
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
		return _federalNewNdcLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _federalNewNdcLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _federalNewNdcLocalService.dynamicQuery(dynamicQuery, start,
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
		return _federalNewNdcLocalService.dynamicQueryCount(dynamicQuery);
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
		return _federalNewNdcLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.FederalNewNdc fetchFederalNewNdc(
		int itemMasterSid) {
		return _federalNewNdcLocalService.fetchFederalNewNdc(itemMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _federalNewNdcLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the federal new ndc with the primary key.
	*
	* @param itemMasterSid the primary key of the federal new ndc
	* @return the federal new ndc
	* @throws PortalException if a federal new ndc with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.FederalNewNdc getFederalNewNdc(int itemMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _federalNewNdcLocalService.getFederalNewNdc(itemMasterSid);
	}

	/**
	* Returns a range of all the federal new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of federal new ndcs
	* @param end the upper bound of the range of federal new ndcs (not inclusive)
	* @return the range of federal new ndcs
	*/
	@Override
	public java.util.List<com.stpl.app.model.FederalNewNdc> getFederalNewNdcs(
		int start, int end) {
		return _federalNewNdcLocalService.getFederalNewNdcs(start, end);
	}

	/**
	* Returns the number of federal new ndcs.
	*
	* @return the number of federal new ndcs
	*/
	@Override
	public int getFederalNewNdcsCount() {
		return _federalNewNdcLocalService.getFederalNewNdcsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _federalNewNdcLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _federalNewNdcLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _federalNewNdcLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the federal new ndc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param federalNewNdc the federal new ndc
	* @return the federal new ndc that was updated
	*/
	@Override
	public com.stpl.app.model.FederalNewNdc updateFederalNewNdc(
		com.stpl.app.model.FederalNewNdc federalNewNdc) {
		return _federalNewNdcLocalService.updateFederalNewNdc(federalNewNdc);
	}

	@Override
	public FederalNewNdcLocalService getWrappedService() {
		return _federalNewNdcLocalService;
	}

	@Override
	public void setWrappedService(
		FederalNewNdcLocalService federalNewNdcLocalService) {
		_federalNewNdcLocalService = federalNewNdcLocalService;
	}

	private FederalNewNdcLocalService _federalNewNdcLocalService;
}