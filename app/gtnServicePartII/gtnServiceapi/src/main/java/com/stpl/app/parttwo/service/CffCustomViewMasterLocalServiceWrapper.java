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
 * Provides a wrapper for {@link CffCustomViewMasterLocalService}.
 *
 * @author
 * @see CffCustomViewMasterLocalService
 * @generated
 */
@ProviderType
public class CffCustomViewMasterLocalServiceWrapper
	implements CffCustomViewMasterLocalService,
		ServiceWrapper<CffCustomViewMasterLocalService> {
	public CffCustomViewMasterLocalServiceWrapper(
		CffCustomViewMasterLocalService cffCustomViewMasterLocalService) {
		_cffCustomViewMasterLocalService = cffCustomViewMasterLocalService;
	}

	/**
	* Adds the cff custom view master to the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustomViewMaster the cff custom view master
	* @return the cff custom view master that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustomViewMaster addCffCustomViewMaster(
		com.stpl.app.parttwo.model.CffCustomViewMaster cffCustomViewMaster) {
		return _cffCustomViewMasterLocalService.addCffCustomViewMaster(cffCustomViewMaster);
	}

	/**
	* Creates a new cff custom view master with the primary key. Does not add the cff custom view master to the database.
	*
	* @param cffCustomViewMasterSid the primary key for the new cff custom view master
	* @return the new cff custom view master
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustomViewMaster createCffCustomViewMaster(
		int cffCustomViewMasterSid) {
		return _cffCustomViewMasterLocalService.createCffCustomViewMaster(cffCustomViewMasterSid);
	}

	/**
	* Deletes the cff custom view master from the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustomViewMaster the cff custom view master
	* @return the cff custom view master that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustomViewMaster deleteCffCustomViewMaster(
		com.stpl.app.parttwo.model.CffCustomViewMaster cffCustomViewMaster) {
		return _cffCustomViewMasterLocalService.deleteCffCustomViewMaster(cffCustomViewMaster);
	}

	/**
	* Deletes the cff custom view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustomViewMasterSid the primary key of the cff custom view master
	* @return the cff custom view master that was removed
	* @throws PortalException if a cff custom view master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustomViewMaster deleteCffCustomViewMaster(
		int cffCustomViewMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffCustomViewMasterLocalService.deleteCffCustomViewMaster(cffCustomViewMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffCustomViewMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cffCustomViewMasterLocalService.dynamicQuery();
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
		return _cffCustomViewMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffCustomViewMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffCustomViewMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _cffCustomViewMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cffCustomViewMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.CffCustomViewMaster fetchCffCustomViewMaster(
		int cffCustomViewMasterSid) {
		return _cffCustomViewMasterLocalService.fetchCffCustomViewMaster(cffCustomViewMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cffCustomViewMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cff custom view master with the primary key.
	*
	* @param cffCustomViewMasterSid the primary key of the cff custom view master
	* @return the cff custom view master
	* @throws PortalException if a cff custom view master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustomViewMaster getCffCustomViewMaster(
		int cffCustomViewMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffCustomViewMasterLocalService.getCffCustomViewMaster(cffCustomViewMasterSid);
	}

	/**
	* Returns a range of all the cff custom view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff custom view masters
	* @param end the upper bound of the range of cff custom view masters (not inclusive)
	* @return the range of cff custom view masters
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.CffCustomViewMaster> getCffCustomViewMasters(
		int start, int end) {
		return _cffCustomViewMasterLocalService.getCffCustomViewMasters(start,
			end);
	}

	/**
	* Returns the number of cff custom view masters.
	*
	* @return the number of cff custom view masters
	*/
	@Override
	public int getCffCustomViewMastersCount() {
		return _cffCustomViewMasterLocalService.getCffCustomViewMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cffCustomViewMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cffCustomViewMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffCustomViewMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cff custom view master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cffCustomViewMaster the cff custom view master
	* @return the cff custom view master that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustomViewMaster updateCffCustomViewMaster(
		com.stpl.app.parttwo.model.CffCustomViewMaster cffCustomViewMaster) {
		return _cffCustomViewMasterLocalService.updateCffCustomViewMaster(cffCustomViewMaster);
	}

	@Override
	public CffCustomViewMasterLocalService getWrappedService() {
		return _cffCustomViewMasterLocalService;
	}

	@Override
	public void setWrappedService(
		CffCustomViewMasterLocalService cffCustomViewMasterLocalService) {
		_cffCustomViewMasterLocalService = cffCustomViewMasterLocalService;
	}

	private CffCustomViewMasterLocalService _cffCustomViewMasterLocalService;
}