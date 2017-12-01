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
 * Provides a wrapper for {@link StFederalNewNdcLocalService}.
 *
 * @author
 * @see StFederalNewNdcLocalService
 * @generated
 */
@ProviderType
public class StFederalNewNdcLocalServiceWrapper
	implements StFederalNewNdcLocalService,
		ServiceWrapper<StFederalNewNdcLocalService> {
	public StFederalNewNdcLocalServiceWrapper(
		StFederalNewNdcLocalService stFederalNewNdcLocalService) {
		_stFederalNewNdcLocalService = stFederalNewNdcLocalService;
	}

	/**
	* Adds the st federal new ndc to the database. Also notifies the appropriate model listeners.
	*
	* @param stFederalNewNdc the st federal new ndc
	* @return the st federal new ndc that was added
	*/
	@Override
	public com.stpl.app.model.StFederalNewNdc addStFederalNewNdc(
		com.stpl.app.model.StFederalNewNdc stFederalNewNdc) {
		return _stFederalNewNdcLocalService.addStFederalNewNdc(stFederalNewNdc);
	}

	/**
	* Creates a new st federal new ndc with the primary key. Does not add the st federal new ndc to the database.
	*
	* @param stFederalNewNdcPK the primary key for the new st federal new ndc
	* @return the new st federal new ndc
	*/
	@Override
	public com.stpl.app.model.StFederalNewNdc createStFederalNewNdc(
		com.stpl.app.service.persistence.StFederalNewNdcPK stFederalNewNdcPK) {
		return _stFederalNewNdcLocalService.createStFederalNewNdc(stFederalNewNdcPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stFederalNewNdcLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st federal new ndc from the database. Also notifies the appropriate model listeners.
	*
	* @param stFederalNewNdc the st federal new ndc
	* @return the st federal new ndc that was removed
	*/
	@Override
	public com.stpl.app.model.StFederalNewNdc deleteStFederalNewNdc(
		com.stpl.app.model.StFederalNewNdc stFederalNewNdc) {
		return _stFederalNewNdcLocalService.deleteStFederalNewNdc(stFederalNewNdc);
	}

	/**
	* Deletes the st federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stFederalNewNdcPK the primary key of the st federal new ndc
	* @return the st federal new ndc that was removed
	* @throws PortalException if a st federal new ndc with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StFederalNewNdc deleteStFederalNewNdc(
		com.stpl.app.service.persistence.StFederalNewNdcPK stFederalNewNdcPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stFederalNewNdcLocalService.deleteStFederalNewNdc(stFederalNewNdcPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stFederalNewNdcLocalService.dynamicQuery();
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
		return _stFederalNewNdcLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stFederalNewNdcLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stFederalNewNdcLocalService.dynamicQuery(dynamicQuery, start,
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
		return _stFederalNewNdcLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stFederalNewNdcLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StFederalNewNdc fetchStFederalNewNdc(
		com.stpl.app.service.persistence.StFederalNewNdcPK stFederalNewNdcPK) {
		return _stFederalNewNdcLocalService.fetchStFederalNewNdc(stFederalNewNdcPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stFederalNewNdcLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stFederalNewNdcLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stFederalNewNdcLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stFederalNewNdcLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st federal new ndc with the primary key.
	*
	* @param stFederalNewNdcPK the primary key of the st federal new ndc
	* @return the st federal new ndc
	* @throws PortalException if a st federal new ndc with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StFederalNewNdc getStFederalNewNdc(
		com.stpl.app.service.persistence.StFederalNewNdcPK stFederalNewNdcPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stFederalNewNdcLocalService.getStFederalNewNdc(stFederalNewNdcPK);
	}

	/**
	* Returns a range of all the st federal new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st federal new ndcs
	* @param end the upper bound of the range of st federal new ndcs (not inclusive)
	* @return the range of st federal new ndcs
	*/
	@Override
	public java.util.List<com.stpl.app.model.StFederalNewNdc> getStFederalNewNdcs(
		int start, int end) {
		return _stFederalNewNdcLocalService.getStFederalNewNdcs(start, end);
	}

	/**
	* Returns the number of st federal new ndcs.
	*
	* @return the number of st federal new ndcs
	*/
	@Override
	public int getStFederalNewNdcsCount() {
		return _stFederalNewNdcLocalService.getStFederalNewNdcsCount();
	}

	/**
	* Updates the st federal new ndc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stFederalNewNdc the st federal new ndc
	* @return the st federal new ndc that was updated
	*/
	@Override
	public com.stpl.app.model.StFederalNewNdc updateStFederalNewNdc(
		com.stpl.app.model.StFederalNewNdc stFederalNewNdc) {
		return _stFederalNewNdcLocalService.updateStFederalNewNdc(stFederalNewNdc);
	}

	@Override
	public StFederalNewNdcLocalService getWrappedService() {
		return _stFederalNewNdcLocalService;
	}

	@Override
	public void setWrappedService(
		StFederalNewNdcLocalService stFederalNewNdcLocalService) {
		_stFederalNewNdcLocalService = stFederalNewNdcLocalService;
	}

	private StFederalNewNdcLocalService _stFederalNewNdcLocalService;
}