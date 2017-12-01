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
 * Provides a wrapper for {@link CpiIndexMasterLocalService}.
 *
 * @author
 * @see CpiIndexMasterLocalService
 * @generated
 */
@ProviderType
public class CpiIndexMasterLocalServiceWrapper
	implements CpiIndexMasterLocalService,
		ServiceWrapper<CpiIndexMasterLocalService> {
	public CpiIndexMasterLocalServiceWrapper(
		CpiIndexMasterLocalService cpiIndexMasterLocalService) {
		_cpiIndexMasterLocalService = cpiIndexMasterLocalService;
	}

	/**
	* Adds the cpi index master to the database. Also notifies the appropriate model listeners.
	*
	* @param cpiIndexMaster the cpi index master
	* @return the cpi index master that was added
	*/
	@Override
	public com.stpl.app.model.CpiIndexMaster addCpiIndexMaster(
		com.stpl.app.model.CpiIndexMaster cpiIndexMaster) {
		return _cpiIndexMasterLocalService.addCpiIndexMaster(cpiIndexMaster);
	}

	/**
	* Creates a new cpi index master with the primary key. Does not add the cpi index master to the database.
	*
	* @param cpiIndexMasterSid the primary key for the new cpi index master
	* @return the new cpi index master
	*/
	@Override
	public com.stpl.app.model.CpiIndexMaster createCpiIndexMaster(
		int cpiIndexMasterSid) {
		return _cpiIndexMasterLocalService.createCpiIndexMaster(cpiIndexMasterSid);
	}

	/**
	* Deletes the cpi index master from the database. Also notifies the appropriate model listeners.
	*
	* @param cpiIndexMaster the cpi index master
	* @return the cpi index master that was removed
	*/
	@Override
	public com.stpl.app.model.CpiIndexMaster deleteCpiIndexMaster(
		com.stpl.app.model.CpiIndexMaster cpiIndexMaster) {
		return _cpiIndexMasterLocalService.deleteCpiIndexMaster(cpiIndexMaster);
	}

	/**
	* Deletes the cpi index master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cpiIndexMasterSid the primary key of the cpi index master
	* @return the cpi index master that was removed
	* @throws PortalException if a cpi index master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CpiIndexMaster deleteCpiIndexMaster(
		int cpiIndexMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpiIndexMasterLocalService.deleteCpiIndexMaster(cpiIndexMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpiIndexMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cpiIndexMasterLocalService.dynamicQuery();
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
		return _cpiIndexMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cpiIndexMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cpiIndexMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _cpiIndexMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cpiIndexMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.CpiIndexMaster fetchCpiIndexMaster(
		int cpiIndexMasterSid) {
		return _cpiIndexMasterLocalService.fetchCpiIndexMaster(cpiIndexMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cpiIndexMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cpi index master with the primary key.
	*
	* @param cpiIndexMasterSid the primary key of the cpi index master
	* @return the cpi index master
	* @throws PortalException if a cpi index master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CpiIndexMaster getCpiIndexMaster(
		int cpiIndexMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpiIndexMasterLocalService.getCpiIndexMaster(cpiIndexMasterSid);
	}

	/**
	* Returns a range of all the cpi index masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of cpi index masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.CpiIndexMaster> getCpiIndexMasters(
		int start, int end) {
		return _cpiIndexMasterLocalService.getCpiIndexMasters(start, end);
	}

	/**
	* Returns the number of cpi index masters.
	*
	* @return the number of cpi index masters
	*/
	@Override
	public int getCpiIndexMastersCount() {
		return _cpiIndexMasterLocalService.getCpiIndexMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cpiIndexMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cpiIndexMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpiIndexMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cpi index master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cpiIndexMaster the cpi index master
	* @return the cpi index master that was updated
	*/
	@Override
	public com.stpl.app.model.CpiIndexMaster updateCpiIndexMaster(
		com.stpl.app.model.CpiIndexMaster cpiIndexMaster) {
		return _cpiIndexMasterLocalService.updateCpiIndexMaster(cpiIndexMaster);
	}

	@Override
	public CpiIndexMasterLocalService getWrappedService() {
		return _cpiIndexMasterLocalService;
	}

	@Override
	public void setWrappedService(
		CpiIndexMasterLocalService cpiIndexMasterLocalService) {
		_cpiIndexMasterLocalService = cpiIndexMasterLocalService;
	}

	private CpiIndexMasterLocalService _cpiIndexMasterLocalService;
}